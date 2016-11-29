/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

/***************************************************************************
                          socket.cpp  -  description
                             -------------------
    begin                : Mar 30 2009
    copyright            : (C) 200 by Piergiorgio Betti
    email                : piergiorgio.betti@e-tech.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include <socket.h>
#include <applogger.h>

#include <fcntl.h>
#include <cerrno>
#include <cstdlib>
#include <cstdarg>
#include <cstdio>

#include <unistd.h>
#include <netinet/tcp.h>
#include <sys/ioctl.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <string.h>

#if defined(__hpux) && defined(_XOPEN_SOURCE_EXTENDED)
#undef	_XOPEN_SOURCE_EXTENDED
#endif

#include <netinet/in.h>

#if defined(__hpux)
#define	_XOPEN_SOURCE_EXTENDED
#endif

#include <net/if.h>

#define socket_errno errno
# ifndef  O_NONBLOCK
#  define O_NONBLOCK	O_NDELAY
# endif
# ifdef	IPPROTO_IP
#  ifndef  SOL_IP
#   define SOL_IP	IPPROTO_IP
#  endif // !SOL_IP
# endif	 // IPPROTO_IP

#ifndef	INADDR_LOOPBACK
#define	INADDR_LOOPBACK	(unsigned long)0x7f000001
#endif

# define DUP_SOCK(s,state) dup(s)


#ifdef HAVE_NAT_NETFILTER
# define NAT_SYSCALL "getsockopt"
# define NAT_DEVICE ""
#else
# define NAT_SYSCALL "ioctl"
# if defined(HAVE_NAT_IPF) && defined(IPL_NAT)
#  define NAT_DEVICE IPL_NAT
# else
#  ifdef HAVE_NAT_PF
#   define NAT_DEVICE "/dev/pf"
#  endif
# endif
#endif

#ifndef	NAT_DEVICE
#define	NAT_DEVICE ""
#endif

const char * natmsg[] = {
	"nat lookup successful",
	"nat address not in table",
	"nat not supported/implemented",
	"unable to open device "NAT_DEVICE,
	"unable to get socket name",
	"unable to get peer name",
	"unable to get socket type",
	"unable to lookup, nat "NAT_SYSCALL" failed",
	"unkown nat error code"
};

#ifdef ADAMS_HPUX
using namespace std;
#endif

/**
 * Perform NAT address table lookup for the current socket.
 *
 * @param sfd socket to get nat info from.
 * @param nat sockaddr structure to hold results.
 */
natResult natv4Lookup ( SOCKET sfd, struct sockaddr_in * nat );
#ifdef CCXX_IPV6
natResult natv6Lookup ( SOCKET sfd, struct sockaddr_in6 * nat );
#endif

/**
 * Return descriptive text for the NAT Lookup return error.
 *
 * @param res result from NATLookup function.
 * @return error description.
 */
char * natErrorString ( natResult res );


// Mutex Socket::mutex;

bool Socket::check ( Family fam )
{
	SOCKET so = INVALID_SOCKET;

	switch ( fam ) {
		case IPV4:
			so = socket ( fam, SOCK_DGRAM, IPPROTO_UDP );
			break;
		default:
			return false;
	}

	if ( so == INVALID_SOCKET )
		return false;

	close ( so );
	return true;
}

Socket::Socket()
{
	setSocket();
}

Socket::Socket ( int domain, int type, int protocol )
{
	setSocket();
	so = socket ( domain, type, protocol );
	if ( so == INVALID_SOCKET ) {
		error ( errCreateFailed, ( char * ) "Could not create socket", socket_errno );
		return;
	}
#ifdef	SO_NOSIGPIPE
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_NOSIGPIPE, ( char * ) &opt, sizeof ( opt ) );
#endif
	state = AVAILABLE;
}

Socket::Socket ( SOCKET fd )
{
	setSocket();
	if ( fd == INVALID_SOCKET ) {
		error ( errCreateFailed, ( char * ) "Invalid socket handle passed", 0 );
		return;
	}
	so = fd;
	state = AVAILABLE;
}

Socket::Socket ( const Socket &orig )
{
	setSocket();
	so = DUP_SOCK ( orig.so, orig.state );
	if ( so == INVALID_SOCKET )
		error ( errCopyFailed, ( char * ) "Could not duplicate socket handle", socket_errno );
	state = orig.state;
}

Socket::~Socket()
{
	endSocket();
}

void Socket::setSocket ( void )
{
	flags.thrown    = false;
	flags.broadcast = false;
	flags.route     = true;
	flags.keepalive = false;
	flags.loopback  = true;
	flags.multicast = false;
	flags.linger	= false;
	flags.ttl	= 1;
	errid           = errSuccess;
	errstr          = NULL;
	syserr          = 0;
	state           = INITIAL;
	so              = INVALID_SOCKET;
}

Socket::Error Socket::sendLimit ( int limit )
{
#ifdef	SO_SNDLOWAT
	if ( setsockopt ( so, SOL_SOCKET, SO_SNDLOWAT, ( char * ) &limit, sizeof ( limit ) ) )
		return errInvalidValue;

	return errSuccess;

#else
	return errServiceUnavailable;
#endif
}

Socket::Error Socket::receiveLimit ( int limit )
{
#ifdef  SO_RCVLOWAT
	if ( setsockopt ( so, SOL_SOCKET, SO_RCVLOWAT, ( char * ) &limit, sizeof ( limit ) ) )
		return errInvalidValue;

	return errSuccess;

#else
	return errServiceUnavailable;
#endif
}

Socket::Error Socket::sendTimeout ( timeout_t to )
{
#ifdef  SO_SNDTIMEO
	struct timeval tv;

	tv.tv_sec = to / 1000;
	tv.tv_usec = ( to % 1000 ) * 1000;

	if ( setsockopt ( so, SOL_SOCKET, SO_SNDTIMEO, ( char * ) &tv, sizeof ( tv ) ) )
		return errInvalidValue;

	return errSuccess;

#else
	return errServiceUnavailable;
#endif
}

Socket::Error Socket::receiveTimeout ( timeout_t to )
{
#ifdef  SO_RCVTIMEO
	struct timeval tv;

	tv.tv_sec = to / 1000;
	tv.tv_usec = ( to % 1000 ) * 1000;

	if ( setsockopt ( so, SOL_SOCKET, SO_RCVTIMEO, ( char * ) &tv, sizeof ( tv ) ) )
		return errInvalidValue;

	return errSuccess;
#else
	return errServiceUnavailable;
#endif
}

Socket::Error Socket::sendBuffer ( unsigned bufsize )
{
#ifdef	SO_SNDBUF
	if ( setsockopt ( so, SOL_SOCKET, SO_SNDBUF, ( char * ) &bufsize, sizeof ( bufsize ) ) )
		return errInvalidValue;
	return errSuccess;
#else
	return errServiceUnavailable;
#endif
}

Socket::Error Socket::bufferSize ( unsigned bufsize )
{
	Error err = receiveBuffer ( bufsize );
	if ( err == errSuccess )
		err = sendBuffer ( bufsize );

	return err;
}

Socket::Error Socket::receiveBuffer ( unsigned bufsize )
{
#ifdef  SO_RCVBUF
	if ( setsockopt ( so, SOL_SOCKET, SO_RCVBUF, ( char * ) &bufsize, sizeof ( bufsize ) ) )
		return errInvalidValue;
	return errSuccess;
#else
	return errServiceUnavailable;
#endif
}

ssize_t Socket::readLine ( char *str, size_t request, timeout_t timeout )
{
	bool crlf = false;
	bool nl = false;
	size_t nleft = request - 1; // leave also space for terminator
	int nstat, c;

	if ( request < 1 )
		return 0;

	str[0] = 0;

	while ( nleft && !nl ) {
		if ( timeout ) {
			if ( !isPending ( pendingInput, timeout ) ) {
				error ( errTimeout, ( char * ) "Read timeout", 0 );
				return -1;
			}
		}
		nstat = ::recv ( so, str, _IOLEN64 nleft, MSG_PEEK );
		if ( nstat <= 0 ) {
			error ( errInput, ( char * ) "Could not read from socket", socket_errno );
			return -1;
		}

		// FIXME: if unique char in buffer is '\r' return "\r"
		//        if buffer end in \r try to read another char?
		//        and if timeout ??
		//        remember last \r

		for ( c = 0; c < nstat; ++c ) {
			if ( str[c] == '\n' ) {
				if ( c > 0 && str[c-1] == '\r' )
					crlf = true;
				++c;
				nl = true;
				break;
			}
		}
		nstat = ::recv ( so, str, _IOLEN64 c, 0 );
		// TODO: correct ???
		if ( nstat < 0 )
			break;

		// adjust ending \r\n in \n
		if ( crlf ) {
			--nstat;
			str[nstat - 1] = '\n';
		}

		str += nstat;
		nleft -= nstat;
	}
	*str = 0;
	return ( ssize_t ) ( request - nleft - 1 );
}

ssize_t Socket::readData ( void *Target, size_t Size, char Separator, timeout_t timeout )
{
	if ( ( Separator == 0x0D ) || ( Separator == 0x0A ) )
		return ( readLine ( ( char * ) Target, Size, timeout ) );

	if ( Size < 1 )
		return ( 0 );

	ssize_t nstat;

	if ( Separator == 0 ) {       // Flat-out read for a number of bytes.
		if ( timeout )
			if ( !isPending ( pendingInput, timeout ) ) {
				error ( errTimeout );
				return ( -1 );
			}
		nstat =::recv ( so, ( char * ) Target, _IOLEN64 Size, 0 );

		if ( nstat < 0 ) {
			error ( errInput );
			return ( -1 );
		}
		return ( nstat );
	}
	/////////////////////////////////////////////////////////////
	// Otherwise, we have a special char separator to use
	/////////////////////////////////////////////////////////////
	bool found = false;
	size_t nleft = Size;
	int c;
	char *str = ( char * ) Target;

	memset ( str, 0, Size );

	while ( nleft && !found ) {
		if ( timeout )
			if ( !isPending ( pendingInput, timeout ) ) {
				error ( errTimeout );
				return ( -1 );
			}

		nstat =::recv ( so, str, _IOLEN64 nleft, MSG_PEEK );
		if ( nstat <= 0 ) {
			error ( errInput );
			return ( -1 );
		}

		for ( c = 0; ( c < nstat ) && !found; ++c )
			if ( str[c] == Separator )
				found = true;

		memset ( str, 0, nleft );
		nstat =::recv ( so, str, c, 0 );
		if ( nstat < 0 )
			break;

		str += nstat;
		nleft -= nstat;
	}
	return ( ssize_t ) ( Size - ( ssize_t ) nleft );
}

ssize_t Socket::writeData ( const void *Source, size_t Size, timeout_t timeout )
{
	if ( Size < 1 )
		return ( 0 );

	ssize_t nstat;
	const char *Slide = ( const char * ) Source;

	while ( true ) {
		if ( timeout )
			if ( !isPending ( pendingOutput, timeout ) ) {
				error ( errOutput );
				return ( -1 );
			}

		nstat =::send ( so, Slide, _IOLEN64 Size, MSG_NOSIGNAL );

		if ( nstat <= 0 ) {
			error ( errOutput );
			return ( -1 );
		}
		Size -= nstat;
		Slide += nstat;


		if ( Size <= 0 )
			break;
	}
	return ( nstat );
}

bool Socket::isConnected ( void ) const
{
	return ( Socket::state == CONNECTED ) ? true : false;
}

bool Socket::isActive ( void ) const
{
	return ( state != INITIAL ) ? true : false;
}

bool Socket::operator!() const
{
	return ( Socket::state == INITIAL ) ? true : false;
}

void Socket::endSocket ( void )
{
	if ( Socket::state == STREAM ) {
		state = INITIAL;
		if ( so > -1 ) {
			SOCKET sosave = so;
			so = INVALID_SOCKET;
			close ( sosave );
		}
		return;
	}

	state = INITIAL;
	if ( so == INVALID_SOCKET )
		return;

#ifdef	SO_LINGER
	struct linger linger;

	if ( flags.linger ) {
		linger.l_onoff = 1;
		linger.l_linger = 60;
	} else
		linger.l_onoff = linger.l_linger = 0;
	setsockopt ( so, SOL_SOCKET, SO_LINGER, ( char * ) &linger,
	             ( socklen_t ) sizeof ( linger ) );
#endif
//	shutdown(so, 2);
	close ( so );
	lout1 << "Socket Closed." << endl;
	so = INVALID_SOCKET;
}

Socket::Error Socket::connectError ( void )
{
	char* str = ( char * ) "Could not connect to remote host ";
	switch ( errno ) {
#ifdef	EHOSTUNREACH
		case EHOSTUNREACH:
			lout1 << str << "error: errConnectNoRoute" << endl;
			return error ( errConnectNoRoute, str, socket_errno );
#endif
#ifdef	ENETUNREACH
		case ENETUNREACH:
			lout1 << str << "error: errConnectNoRoute" << endl;
			return error ( errConnectNoRoute, str, socket_errno );
#endif
		case EINPROGRESS:
			lout1 << str << "error: errConnectBusy" << endl;
			return error ( errConnectBusy, str, socket_errno );
#ifdef	EADDRNOTAVAIL
		case EADDRNOTAVAIL:
			lout1 << str << "error: errConnectInvalid" << endl;
			return error ( errConnectInvalid, str, socket_errno );
#endif
		case ECONNREFUSED:
			lout1 << str << "error: errConnectRefused" << endl;
			return error ( errConnectRefused, str, socket_errno );
		case ETIMEDOUT:
			lout1 << str << "error: errConnectTimeout" << endl;
			return error ( errConnectTimeout, str, socket_errno );
		default:
			lout1 << str << "error: errConnectFailed" << endl;
			return error ( errConnectFailed, str, socket_errno );
	}
}

Socket::Error Socket::error ( Error err, char *errs, long systemError ) const
{
	errid  = err;
	errstr = errs;
	syserr = systemError;
	if ( !err )
		return err;

	if ( flags.thrown )
		return err;

	// prevents recursive throws

	flags.thrown = true;
	return err;
}

const char *Socket::getSystemErrorString ( void ) const
{
	return NULL;
}

Socket::Error Socket::setBroadcast ( bool enable )
{
	int opt = ( enable ? 1 : 0 );
	if ( setsockopt ( so, SOL_SOCKET, SO_BROADCAST,
	                  ( char * ) &opt, ( socklen_t ) sizeof ( opt ) ) )
		return error ( errBroadcastDenied, ( char * ) "Could not set socket broadcast option", socket_errno );
	flags.broadcast = enable;
	return errSuccess;
}

Socket::Error Socket::setKeepAlive ( bool enable )
{
	int opt = ( enable ? ~0 : 0 );
#if (defined(SO_KEEPALIVE) || defined(WIN32))
	if ( setsockopt ( so, SOL_SOCKET, SO_KEEPALIVE,
	                  ( char * ) &opt, ( socklen_t ) sizeof ( opt ) ) )
		return error ( errKeepaliveDenied, ( char * ) "Could not set socket keep-alive option", socket_errno );
#endif
	flags.keepalive = enable;
	return errSuccess;
}

Socket::Error Socket::setLinger ( bool linger )
{
#ifdef	SO_LINGER
	flags.linger = linger;
	return errSuccess;
#else
	return error ( errServiceUnavailable, ( char * ) "Socket lingering not supported" );
#endif
}

Socket::Error Socket::setRouting ( bool enable )
{
	int opt = ( enable ? 1 : 0 );

#ifdef	SO_DONTROUTE
	if ( setsockopt ( so, SOL_SOCKET, SO_DONTROUTE,
	                  ( char * ) &opt, ( socklen_t ) sizeof ( opt ) ) )
		return error ( errRoutingDenied, ( char * ) "Could not set dont-route socket option", socket_errno );
#endif
	flags.route = enable;
	return errSuccess;
}

Socket::Error Socket::setNoDelay ( bool enable )
{
	int opt = ( enable ? 1 : 0 );

	if ( setsockopt ( so, IPPROTO_TCP, TCP_NODELAY,
	                  ( char * ) &opt, ( socklen_t ) sizeof ( opt ) ) )
		return error ( errNoDelay, ( char * ) "Could not set tcp-nodelay socket option", socket_errno );


	return errSuccess;
}

Socket::Error Socket::setTypeOfService ( Tos service )
{
#ifdef	SOL_IP
	unsigned char tos;
	switch ( service ) {
#ifdef 	IPTOS_LOWDELAY
		case tosLowDelay:
			tos = IPTOS_LOWDELAY;
			break;
#endif
#ifdef 	IPTOS_THROUGHPUT
		case tosThroughput:
			tos = IPTOS_THROUGHPUT;
			break;
#endif
#ifdef	IPTOS_RELIABILITY
		case tosReliability:
			tos = IPTOS_RELIABILITY;
			break;
#endif
#ifdef	IPTOS_MINCOST
		case tosMinCost:
			tos = IPTOS_MINCOST;
			break;
#endif
		default:
			return error ( errServiceUnavailable, ( char * ) "Unknown type-of-service" );
	}
	if ( setsockopt ( so, SOL_IP, IP_TOS, ( char * ) &tos, ( socklen_t ) sizeof ( tos ) ) )
		return error ( errServiceDenied, ( char * ) "Could not set type-of-service", socket_errno );
	return errSuccess;
#else
	return error ( errServiceUnavailable, ( char * ) "Socket type-of-service not supported", socket_errno );
#endif
}

Socket::Error Socket::setTimeToLiveByFamily ( unsigned char ttl, Family fam )
{
	if ( !flags.multicast )
		return error ( errMulticastDisabled, ( char * ) "Multicast not enabled on socket" );

	switch ( fam ) {
		case IPV4:
#ifdef	IP_MULTICAST_TTL
			flags.ttl = ttl;
			setsockopt ( so, IPPROTO_IP, IP_MULTICAST_TTL, ( char * ) &ttl, sizeof ( ttl ) );
			return errSuccess;
#endif
		default:
			return error ( errServiceUnavailable, ( char * ) "Multicast not supported" );
	}
}

Socket::Error Socket::setLoopbackByFamily ( bool enable, Family family )
{
	unsigned char loop;

	if ( !flags.multicast )
		return error ( errMulticastDisabled, ( char * ) "Multicast not enabled on socket" );

	if ( enable )
		loop = 1;
	else
		loop = 0;
	flags.loopback = enable;

	switch ( family ) {
		case IPV4:
#ifdef  IP_MULTICAST_LOOP
			setsockopt ( so, IPPROTO_IP, IP_MULTICAST_LOOP, ( char * ) &loop, sizeof ( loop ) );
			return errSuccess;
#endif
		default:
			return error ( errServiceUnavailable, ( char * ) "Multicast not supported" );
	}
}

bool Socket::isPending ( Pending pending, timeout_t timeout )
{
	int status;
#ifdef USE_POLL
	struct pollfd pfd;

	pfd.fd = so;
	pfd.revents = 0;

	if ( so == INVALID_SOCKET )
		return true;

	switch ( pending ) {
		case pendingInput:
			pfd.events = POLLIN;
			break;
		case pendingOutput:
			pfd.events = POLLOUT;
			break;
		case pendingError:
			pfd.events = POLLERR | POLLHUP;
			break;
	}

	status = 0;
	while ( status < 1 ) {

		if ( timeout == TIMEOUT_INF )
			status = poll ( &pfd, 1, -1 );
		else
			status = poll ( &pfd, 1, timeout );

		if ( status < 1 ) {
			// don't stop polling because of a simple
			// signal :)
			if ( status == -1 && errno == EINTR )
				continue;

			return false;
		}
	}

	if ( pfd.revents & pfd.events )
		return true;
	return false;
#else
	struct timeval tv;
	struct timeval *tvp = &tv;
	fd_set grp;

	if ( timeout == TIMEOUT_INF )
		tvp = NULL;
	else {
		tv.tv_usec = ( timeout % 1000 ) * 1000;
		tv.tv_sec = timeout / 1000;
	}

	FD_ZERO ( &grp );
	SOCKET sosave = so;
	if ( so == INVALID_SOCKET )
		return true;
	FD_SET ( sosave, &grp );
	switch ( pending ) {
		case pendingInput:
			status = select ( ( int ) so + 1, &grp, NULL, NULL, tvp );
			break;
		case pendingOutput:
			status = select ( ( int ) so + 1, NULL, &grp, NULL, tvp );
			break;
		case pendingError:
			status = select ( ( int ) so + 1, NULL, NULL, &grp, tvp );
			break;
	}
	if ( status < 1 )
		return false;
	if ( FD_ISSET ( so, &grp ) )
		return true;
	return false;
#endif
}

Socket &Socket::operator= ( const Socket & from )
{
	if ( so == from.so )
		return *this;

	if ( state != INITIAL )
		endSocket();

	so = DUP_SOCK ( from.so, from.state );
	if ( so == INVALID_SOCKET ) {
		error ( errCopyFailed, ( char * ) "Could not duplicate socket handle", socket_errno );
		state = INITIAL;
	} else
		state = from.state;

	return *this;
}

void Socket::setCompletion ( bool immediate )
{
	flags.completion = immediate;
	int fflags = fcntl ( so, F_GETFL );

	switch ( immediate ) {
		case false:
			fflags |= O_NONBLOCK;
			fcntl ( so, F_SETFL, fflags );
			break;
		case true:
			fflags &= ~ O_NONBLOCK;
			fcntl ( so, F_SETFL, fflags );
			break;
	}
}

IPV4Host Socket::getIPV4Sender ( tpport_t *port ) const
{
	struct sockaddr_in from;
	char buf;
	socklen_t len = sizeof ( from );
	int rc = ::recvfrom ( so, &buf, 1, MSG_PEEK,
	                      ( struct sockaddr * ) & from, &len );

	if ( rc < 1 ) {
		if ( port )
			*port = 0;

		memset ( ( void* ) &from, 0, sizeof ( from ) );
		error ( errInput, ( char * ) "Could not read from socket", socket_errno );
	} else {
		if ( port )
			*port = ntohs ( from.sin_port );
	}

	return IPV4Host ( from.sin_addr );
}

IPV4Host Socket::getIPV4Local ( tpport_t *port ) const
{
	struct sockaddr_in addr;
	socklen_t len = sizeof ( addr );

	if ( getsockname ( so, ( struct sockaddr * ) &addr, &len ) ) {
		error ( errResourceFailure, ( char * ) "Could not get socket address", socket_errno );
		if ( port )
			*port = 0;
		memset ( &addr.sin_addr, 0, sizeof ( addr.sin_addr ) );
	} else {
		if ( port )
			*port = ntohs ( addr.sin_port );
	}
	return IPV4Host ( addr.sin_addr );
}


IPV4Host Socket::getIPV4NAT ( tpport_t *port ) const
{
	struct sockaddr_in addr;
	natResult res;

	if ( ( res = natv4Lookup ( ( int ) so, &addr ) ) != natOK ) {
		if ( res == natNotSupported )
			error ( errServiceUnavailable, natErrorString ( res ) );
		else if ( res == natSearchErr )
			error ( errSearchErr, natErrorString ( res ) );
		else
			error ( errLookupFail, natErrorString ( res ), socket_errno );
		if ( port )
			*port = 0;
		memset ( &addr.sin_addr, 0, sizeof ( addr.sin_addr ) );
	} else {
		if ( port )
			*port = ntohs ( addr.sin_port );
	}
	return IPV4Host ( addr.sin_addr );
}

IPV4Host Socket::getIPV4Peer ( tpport_t *port ) const
{
	struct sockaddr_in addr;
	socklen_t len = sizeof ( addr );

	if ( getpeername ( so, ( struct sockaddr * ) &addr, &len ) ) {
		if ( errno == ENOTCONN )
			error ( errNotConnected, ( char * ) "Could not get peer address", socket_errno );
		else
			error ( errResourceFailure, ( char * ) "Could not get peer address", socket_errno );
		if ( port )
			*port = 0;
		memset ( &addr.sin_addr, 0, sizeof ( addr.sin_addr ) );
	} else {
		if ( port )
			*port = ntohs ( addr.sin_port );
	}
	return IPV4Host ( addr.sin_addr );
}


Socket::Error Socket::setMulticastByFamily ( bool enable, Family family )
{
	socklen_t len;

	switch ( family ) {
		case IPV4:
#ifdef	IP_MULTICAST_IF
			struct sockaddr_in addr4;
			len = sizeof ( addr4 );

			if ( enable == flags.multicast )
				return errSuccess;

			flags.multicast = enable;
			if ( enable )
				getsockname ( so, ( struct sockaddr * ) &addr4, &len );
			else
				memset ( &addr4.sin_addr, 0, sizeof ( addr4.sin_addr ) );

			setsockopt ( so, IPPROTO_IP, IP_MULTICAST_IF, ( char * ) &addr4.sin_addr, sizeof ( addr4.sin_addr ) );
			return errSuccess;
#endif
		default:
			return error ( errServiceUnavailable, ( char * ) "Multicast not supported" );
	}
}

Socket::Error Socket::join ( const IPV4Multicast &ia )
{
#ifdef	IP_ADD_MEMBERSHIP
	struct ip_mreq group;
	struct sockaddr_in myaddr;
	socklen_t len = sizeof ( myaddr );

	if ( !flags.multicast )
		return error ( errMulticastDisabled, ( char * ) "Multicast not enabled on socket" );

	getsockname ( so, ( struct sockaddr * ) &myaddr, &len );
	memcpy ( &group.imr_interface, &myaddr.sin_addr, sizeof ( &myaddr.sin_addr ) );
	group.imr_multiaddr = getaddress ( ia );
	setsockopt ( so, IPPROTO_IP, IP_ADD_MEMBERSHIP, ( char * ) &group, sizeof ( group ) );
	return errSuccess;
#else
	return error ( errServiceUnavailable, ( char * ) "Multicast not supported" );
#endif
}


Socket::Error Socket::drop ( const IPV4Multicast &ia )
{
#ifdef	IP_DROP_MEMBERSHIP
	struct ip_mreq group;
	struct sockaddr_in myaddr;
	socklen_t len = sizeof ( myaddr );

	if ( !flags.multicast )
		return error ( errMulticastDisabled, ( char * ) "Multicast not enabled on socket" );

	getsockname ( so, ( struct sockaddr * ) &myaddr, &len );
	memcpy ( &group.imr_interface, &myaddr.sin_addr, sizeof ( &myaddr.sin_addr ) );
	group.imr_multiaddr = getaddress ( ia );
	setsockopt ( so, IPPROTO_IP, IP_DROP_MEMBERSHIP, ( char * ) &group, sizeof ( group ) );
	return errSuccess;
#else
	return error ( errServiceUnavailable, ( char * ) "Multicast not supported" );
#endif
}

#ifdef	HAVE_GETADDRINFO

UDPSocket::UDPSocket ( const char *name, Family fam ) :
		Socket ( fam, SOCK_DGRAM, IPPROTO_UDP )
{
	char namebuf[128], *cp;
	struct addrinfo hint, *list = NULL, *first;

	family = fam;

	switch ( fam ) {
		case IPV4:
			peer.ipv4.sin_family = family;
	}

	snprintf ( namebuf, sizeof ( namebuf ), "%s", name );
	cp = strrchr ( namebuf, '/' );
	if ( !cp && family == IPV4 )
		cp = strrchr ( namebuf, ':' );

	if ( !cp ) {
		cp = namebuf;
		name = NULL;
	} else {
		name = namebuf;
		* ( cp++ ) = 0;
		if ( !strcmp ( name, "*" ) )
			name = NULL;
	}

	memset ( &hint, 0, sizeof ( hint ) );

	hint.ai_family = family;
	hint.ai_socktype = SOCK_DGRAM;
	hint.ai_protocol = IPPROTO_UDP;
	hint.ai_flags = AI_PASSIVE;

	if ( getaddrinfo ( name, cp, &hint, &list ) || !list ) {
		error ( errBindingFailed, ( char * ) "Could not find service", errno );
		endSocket();
		return;
	}

#if defined(SO_REUSEADDR)
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_REUSEADDR, ( char * ) &opt,
	             ( socklen_t ) sizeof ( opt ) );
#endif

	first = list;

	while ( list ) {
		if ( !bind ( so, list->ai_addr, ( socklen_t ) list->ai_addrlen ) ) {
			state = BOUND;
			break;
		}
		list = list->ai_next;
	}
	freeaddrinfo ( first );

	if ( state != BOUND ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Count not bind socket", errno );
		return;
	}
}

#else

UDPSocket::UDPSocket ( const char *name, Family fam ) :
		Socket ( fam, SOCK_DGRAM, IPPROTO_UDP )
{
	char namebuf[128], *cp;
	tpport_t port;
	struct servent *svc;
	socklen_t alen;
	struct sockaddr *addr;

	family = fam;

	snprintf ( namebuf, sizeof ( namebuf ), "%s", name );
	cp = strrchr ( namebuf, '/' );
	if ( !cp && family == IPV4 )
		cp = strrchr ( namebuf, ':' );

	if ( !cp ) {
		cp = namebuf;
		name = "*";
	} else {
		name = namebuf;
		* ( cp++ ) = 0;
	}

	if ( isdigit ( *cp ) )
		port = atoi ( cp );
	else {
// 		mutex.enter();
		svc = getservbyname ( cp, "udp" );
		if ( svc )
			port = ntohs ( svc->s_port );
// 		mutex.leave();
		if ( !svc ) {
			error ( errBindingFailed, ( char * ) "Could not find service", errno );
			endSocket();
			return;
		}
	}

	struct sockaddr_in addr4;
	IPV4Address ia4 ( name );

	switch ( family ) {
		case IPV4:
			peer.ipv4.sin_family = family;
			memset ( &addr4, 0, sizeof ( addr4 ) );
			addr4.sin_family = family;
			addr4.sin_addr = getaddress ( ia4 );
			addr4.sin_port = htons ( port );
			alen = sizeof ( &addr4 );
			addr = ( struct sockaddr * ) & addr4;
	}

#if defined(SO_REUSEADDR)
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_REUSEADDR, ( char * ) &opt,
	             ( socklen_t ) sizeof ( opt ) );
#endif

	if ( !bind ( so, addr, alen ) )
		state = BOUND;

	if ( state != BOUND ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Count not bind socket", errno );
		return;
	}
}

#endif

UDPSocket::UDPSocket ( Family fam ) :
		Socket ( fam, SOCK_DGRAM, IPPROTO_UDP )
{
	family = fam;
	memset ( &peer, 0, sizeof ( peer ) );
	switch ( fam ) {
		case IPV4:
			peer.ipv4.sin_family = family;
	}
}

UDPSocket::UDPSocket ( const IPV4Address &ia, tpport_t port ) :
		Socket ( AF_INET, SOCK_DGRAM, IPPROTO_UDP )
{
	family = IPV4;
	memset ( &peer.ipv4, 0, sizeof ( peer ) );
	peer.ipv4.sin_family = AF_INET;
	peer.ipv4.sin_addr = getaddress ( ia );
	peer.ipv4.sin_port = htons ( port );
#if defined(SO_REUSEADDR)
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_REUSEADDR, ( char * ) &opt, ( socklen_t ) sizeof ( opt ) );
#endif
	if ( bind ( so, ( struct sockaddr * ) &peer.ipv4, sizeof ( peer.ipv4 ) ) ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not bind socket", socket_errno );
		return;
	}
	state = BOUND;
}


UDPSocket::~UDPSocket()
{
	endSocket();
}

ssize_t UDPSocket::send ( const void *buf, size_t len )
{
	struct sockaddr *addr = NULL;
	socklen_t alen = 0;

	switch ( family ) {
		case IPV4:
			addr = ( struct sockaddr * ) & peer.ipv4;
			alen = sizeof ( struct sockaddr_in );
			break;
		default:
			return -1;
	}

	if ( isConnected() ) {
		addr = NULL;
		alen = 0;
	}

	return _IORET64 ::sendto ( so, ( const char * ) buf, _IOLEN64 len, MSG_NOSIGNAL, addr, alen );
}

ssize_t UDPSocket::receive ( void *buf, size_t len, bool reply )
{
	struct sockaddr *addr = NULL;
	struct sockaddr_in senderAddress;  // DMC 2/7/05 ADD for use below.
	socklen_t alen = 0;

	switch ( family ) {
		case IPV4:
			addr = ( struct sockaddr * ) & peer.ipv4;
			alen = sizeof ( struct sockaddr_in );
			break;
		default:
			return -1;
	}

	if ( isConnected() || !reply ) {
		// DMC 2/7/05 MOD to use senderAddress instead of NULL, to prevent 10014 error
		// from recvfrom.
		//addr = NULL;
		//alen = 0;
		addr = ( struct sockaddr* ) ( &senderAddress );
		alen = sizeof ( struct sockaddr_in );
	}

	int bytes = ::recvfrom ( so, ( char * ) buf, _IOLEN64 len, 0, addr, &alen );

	return _IORET64 bytes;
}

Socket::Error UDPSocket::join ( const IPV4Multicast &ia, int InterfaceIndex )
{

#if defined(WIN32) && defined(IP_ADD_MEMBERSHIP)

	// DMC 2/7/05: Added WIN32 block.  Win32 does not define the ip_mreqn structure,
	// so we must use ip_mreq with INADDR_ANY.
	struct ip_mreq      group;
	struct sockaddr_in   myaddr;
	socklen_t            len = sizeof ( myaddr );

	if ( !flags.multicast )
		return error ( errMulticastDisabled );

	memset ( &group, 0, sizeof ( group ) );
	getsockname ( so, ( struct sockaddr * ) &myaddr, &len );
	group.imr_multiaddr.s_addr = ia.getAddress().s_addr;
	group.imr_interface.s_addr = INADDR_ANY;
	int retval = setsockopt ( so, IPPROTO_IP, IP_ADD_MEMBERSHIP, ( char * ) & group, sizeof ( group ) );
	return errSuccess;

#elif defined(IP_ADD_MEMBERSHIP) && defined(SIOCGIFINDEX) && !defined(__FreeBSD__) && !defined(__FreeBSD_kernel__) && !defined(_OSF_SOURCE) && !defined(__hpux) && !defined(__GNU__)

	struct ip_mreqn      group;
	struct sockaddr_in   myaddr;
	socklen_t            len = sizeof ( myaddr );

	if ( !flags.multicast )
		return error ( errMulticastDisabled );

	getsockname ( so, ( struct sockaddr * ) &myaddr, &len );
	memset ( &group, 0, sizeof ( group ) );
	memcpy ( &group.imr_address, &myaddr.sin_addr, sizeof ( &myaddr.sin_addr ) );
	group.imr_multiaddr = getaddress ( ia );
	group.imr_ifindex   = InterfaceIndex;
	setsockopt ( so, IPPROTO_IP, IP_ADD_MEMBERSHIP, ( char * ) &group, sizeof ( group ) );
	return errSuccess;
#elif defined(IP_ADD_MEMBERSHIP)
	// if no by index, use INADDR_ANY
	struct ip_mreq group;
	struct sockaddr_in myaddr;
	socklen_t len = sizeof ( myaddr );

	if ( !flags.multicast )
		return error ( errMulticastDisabled );

	getsockname ( so, ( struct sockaddr * ) &myaddr, &len );
	memset ( &group, sizeof ( group ), 0 );
	group.imr_multiaddr.s_addr = ia.getAddress().s_addr;
	group.imr_interface.s_addr = INADDR_ANY;
	setsockopt ( so, IPPROTO_IP, IP_ADD_MEMBERSHIP, ( char * ) &group, sizeof ( group ) );
	return errSuccess;
#else
	return error ( errServiceUnavailable );
#endif
}


Socket::Error UDPSocket::getInterfaceIndex ( const char *DeviceName, int& InterfaceIndex )
{
#ifndef WIN32
#if defined(IP_ADD_MEMBERSHIP) && defined(SIOCGIFINDEX) && !defined(__FreeBSD__) && !defined(__FreeBSD_kernel__) && !defined(_OSF_SOURCE) && !defined(__hpux) && !defined(__GNU__)

	struct ip_mreqn  mreqn;
	struct ifreq     m_ifreq;
	int              i;
	sockaddr*        sa = ( sockaddr* ) & peer.ipv4;

	InterfaceIndex = -1;

	mreqn.imr_multiaddr = * ( struct in_addr * ) & ( sa->sa_data[2] );

	for ( i = 0; i < IFNAMSIZ && DeviceName[i]; ++i )
		m_ifreq.ifr_name[i] = DeviceName[i];
	for ( ; i < IFNAMSIZ; ++i )
		m_ifreq.ifr_name[i] = 0;

	if ( ioctl ( so, SIOCGIFINDEX, &m_ifreq ) )
		return error ( errServiceUnavailable );

#if defined(__FreeBSD__) || defined(__GNU__)
	InterfaceIndex = m_ifreq.ifr_ifru.ifru_index;
#else
	InterfaceIndex = m_ifreq.ifr_ifindex;
#endif
	return errSuccess;
#else
	return error ( errServiceUnavailable );
#endif
#else
	return error ( errServiceUnavailable );
#endif // WIN32
}

#ifdef	AF_UNSPEC
Socket::Error UDPSocket::disconnect ( void )
{
	struct sockaddr_in addr;
	int len = sizeof ( addr );

	if ( so == INVALID_SOCKET )
		return errSuccess;

	Socket::state = BOUND;

	memset ( &addr, 0, len );
	addr.sin_family = AF_UNSPEC;
	if ( ::connect ( so, ( sockaddr * ) &addr, len ) )
		return connectError();
	return errSuccess;
}
#else
Socket::Error UDPSocket::disconnect ( void )
{
	if ( so == INVALID_SOCKET )
		return errSuccess;

	Socket::state = BOUND;
	return connect ( getLocal() );
}
#endif

void UDPSocket::setPeer ( const IPV4Host &ia, tpport_t port )
{
	memset ( &peer.ipv4, 0, sizeof ( peer.ipv4 ) );
	peer.ipv4.sin_family = AF_INET;
	peer.ipv4.sin_addr = getaddress ( ia );
	peer.ipv4.sin_port = htons ( port );
}

void UDPSocket::connect ( const IPV4Host &ia, tpport_t port )
{
	setPeer ( ia, port );
	if ( so == INVALID_SOCKET )
		return;

	if ( !::connect ( so, ( struct sockaddr * ) &peer.ipv4, sizeof ( struct sockaddr_in ) ) )
		Socket::state = CONNECTED;
}


#ifdef  HAVE_GETADDRINFO

void UDPSocket::setPeer ( const char *name )
{
	char namebuf[128], *cp;
	struct addrinfo hint, *list;

	snprintf ( namebuf, sizeof ( namebuf ), "%s", name );
	cp = strrchr ( namebuf, '/' );
	if ( !cp )
		cp = strrchr ( namebuf, ':' );

	if ( !cp )
		return;

	memset ( &hint, 0, sizeof ( hint ) );
	hint.ai_family = family;
	hint.ai_socktype = SOCK_DGRAM;
	hint.ai_protocol = IPPROTO_UDP;

	if ( getaddrinfo ( namebuf, cp, &hint, &list ) || !list )
		return;

	switch ( family ) {
		case IPV4:
			memcpy ( &peer.ipv4, list->ai_addr, sizeof ( peer.ipv4 ) );
			break;
	}

	freeaddrinfo ( list );
}

#else

void UDPSocket::setPeer ( const char *name )
{
	char namebuf[128], *cp;
	struct servent *svc;
	tpport_t port;

	snprintf ( namebuf, sizeof ( namebuf ), "%s", name );
	cp = strrchr ( namebuf, '/' );
	if ( !cp )
		cp = strrchr ( namebuf, ':' );

	if ( !cp )
		return;

	if ( isdigit ( *cp ) )
		port = atoi ( cp );
	else {
// 		mutex.enter();
		svc = getservbyname ( cp, "udp" );
		if ( svc )
			port = ntohs ( svc->s_port );
// 		mutex.leave();
		if ( !svc )
			return;
	}

	memset ( &peer, 0, sizeof ( peer ) );

	switch ( family ) {
		case IPV4:
			setPeer ( IPV4Host ( namebuf ), port );
			break;
	}
}

#endif

void UDPSocket::connect ( const char *service )
{
	int rtn = -1;

	setPeer ( service );

	if ( so == INVALID_SOCKET )
		return;

	switch ( family ) {
		case IPV4:
			rtn = ::connect ( so, ( struct sockaddr * ) & peer.ipv4, sizeof ( struct sockaddr_in ) );
			break;
	}
	if ( !rtn )
		Socket::state = CONNECTED;
}

IPV4Host UDPSocket::getIPV4Peer ( tpport_t *port ) const
{
	// FIXME: insufficient buffer
	//        how to retrieve peer ??
	char buf;
	socklen_t len = sizeof ( peer.ipv4 );
	int rtn = ::recvfrom ( so, &buf, 1, MSG_PEEK, ( struct sockaddr * ) & peer.ipv4, &len );

	if ( rtn < 1 ) {
		if ( port )
			*port = 0;

		memset ( ( void* ) &peer.ipv4, 0, sizeof ( peer.ipv4 ) );
	} else {
		if ( port )
			*port = ntohs ( peer.ipv4.sin_port );
	}
	return IPV4Host ( peer.ipv4.sin_addr );
}


UDPBroadcast::UDPBroadcast ( const IPV4Address &ia, tpport_t port ) :
		UDPSocket ( ia, port )
{
	if ( so != INVALID_SOCKET )
		setBroadcast ( true );
}

void UDPBroadcast::setPeer ( const IPV4Broadcast &ia, tpport_t port )
{
	memset ( &peer.ipv4, 0, sizeof ( peer.ipv4 ) );
	peer.ipv4.sin_family = AF_INET;
	peer.ipv4.sin_addr = getaddress ( ia );
	peer.ipv4.sin_port = htons ( port );
}

void TCPSocket::setSegmentSize ( unsigned mss )
{
#ifdef	TCP_MAXSEG
	if ( mss > 1 )
		setsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &mss, sizeof ( mss ) );
#endif
	segsize = mss;
}

#ifdef	HAVE_GETADDRINFO
TCPSocket::TCPSocket ( const char *name, unsigned backlog, unsigned mss ) :
		Socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP )
{
	char namebuf[128], *cp;
	struct addrinfo hint, *list = NULL, *first;
	snprintf ( namebuf, sizeof ( namebuf ), "%s", name );
	cp = strrchr ( namebuf, '/' );
	if ( !cp )
		cp = strrchr ( namebuf, ':' );

	if ( !cp ) {
		cp = namebuf;
		name = NULL;
	} else {
		name = namebuf;
		* ( cp++ ) = 0;
		if ( !strcmp ( name, "*" ) )
			name = NULL;
	}

	memset ( &hint, 0, sizeof ( hint ) );
	hint.ai_family = AF_INET;
	hint.ai_socktype = SOCK_STREAM;
	hint.ai_protocol = IPPROTO_TCP;
	hint.ai_flags = AI_PASSIVE;

	if ( getaddrinfo ( name, cp, &hint, &list ) || !list ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not find service", errno );
		return;
	}

#if defined(SO_REUSEADDR)
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_REUSEADDR, ( char * ) &opt,
	             ( socklen_t ) sizeof ( opt ) );
#endif

	first = list;
	while ( list ) {
		if ( !bind ( so, list->ai_addr, ( socklen_t ) list->ai_addrlen ) ) {
			state = BOUND;
			break;
		}
		list = list->ai_next;
	}
	freeaddrinfo ( first );
	if ( state != BOUND ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not bind socket", socket_errno );
		return;
	}

	setSegmentSize ( mss );
	if ( listen ( so, backlog ) ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not listen on socket", socket_errno );
		return;
	}
}

#else
TCPSocket::TCPSocket ( const char *name, unsigned backlog, unsigned mss ) :
		Socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP )
{
	char namebuf[128], *cp;
	struct sockaddr_in addr;
	struct servent *svc;

	memset ( &addr, 0, sizeof ( addr ) );
	snprintf ( namebuf, sizeof ( namebuf ), "%s", name );
	cp = strrchr ( namebuf, '/' );
	if ( !cp )
		cp = strrchr ( namebuf, ':' );

	if ( !cp ) {
		cp = namebuf;
		name = "*";
	} else {
		name = namebuf;
		* ( cp++ ) = 0;
	}

	addr.sin_family = AF_INET;
	if ( isdigit ( *cp ) )
		addr.sin_port = htons ( atoi ( cp ) );
	else {
// 		mutex.enter();
		svc = getservbyname ( cp, "tcp" );
		if ( svc )
			addr.sin_port = svc->s_port;
// 		mutex.leave();
		if ( !svc ) {
			endSocket();
			error ( errBindingFailed, "Could not find service", errno );
			return;

		}
	}

	IPV4Address ia ( name );
	addr.sin_addr = getaddress ( ia );

#if defined(SO_REUSEADDR)
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_REUSEADDR, ( char * ) &opt,
	             ( socklen_t ) sizeof ( opt ) );
#endif

	if ( bind ( so, ( struct sockaddr * ) &addr, sizeof ( addr ) ) ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not bind socket", socket_errno );
		return;
	}

	setSegmentSize ( mss );
	if ( listen ( so, backlog ) ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not listen on socket",
		        socket_errno );
		return;
	}
	state = BOUND;
}

#endif

TCPSocket::TCPSocket ( const IPV4Address &ia, tpport_t port, unsigned backlog, unsigned mss ) :
		Socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP )
{
	struct sockaddr_in addr;

	memset ( &addr, 0, sizeof ( addr ) );
	addr.sin_family = AF_INET;
	addr.sin_addr = getaddress ( ia );
	addr.sin_port = htons ( port );

#if defined(SO_REUSEADDR)
	int opt = 1;
	setsockopt ( so, SOL_SOCKET, SO_REUSEADDR, ( char * ) &opt, ( socklen_t ) sizeof ( opt ) );
#endif
	if ( bind ( so, ( struct sockaddr * ) &addr, sizeof ( addr ) ) ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not bind socket", socket_errno );
		return;
	}

	setSegmentSize ( mss );

	if ( listen ( so, backlog ) ) {
		endSocket();
		error ( errBindingFailed, ( char * ) "Could not listen on socket", socket_errno );
		return;
	}
	state = BOUND;
}

bool TCPSocket::onAccept ( const IPV4Host &ia, tpport_t port )
{
	return true;
}

TCPSocket::~TCPSocket()
{
	endSocket();
}


void TCPSocket::reject ( void )
{
	SOCKET rej = accept ( so, NULL, NULL );
	shutdown ( rej, 2 );
	close ( rej );
}

#ifdef ADAMS_LINUX
TCPStream::TCPStream ( TCPSocket &server, bool throwflag, timeout_t to ) :
		Socket ( accept ( server.getSocket(), NULL, NULL ) )
#else
TCPStream::TCPStream ( TCPSocket &server, bool throwflag, timeout_t to ) :
		streambuf(),Socket ( accept ( server.getSocket(), NULL, NULL ) ),
#ifdef	HAVE_OLD_IOSTREAM
		iostream()
#else
		iostream ( ( streambuf * ) this )
#endif
#endif
		, bufsize ( 0 )
		, gbuf ( NULL )
		, pbuf ( NULL )
{
	tpport_t port;
	family = IPV4;

#ifdef	HAVE_OLD_IOSTREAM
	init ( ( streambuf * ) this );
#endif

	timeout = to;
	setError ( throwflag );
	IPV4Host host = getPeer ( &port );
	if ( !server.onAccept ( host, port ) ) {
		endSocket();
		error ( errConnectRejected );
#ifdef ADAMS_LINUX
		clear ( std::ios::failbit | rdstate() );
#else
		clear ( ios::failbit | rdstate() );
#endif
		return;
	}

	segmentBuffering ( server.getSegmentSize() );
	Socket::state = CONNECTED;
}

#ifdef ADAMS_LINUX
TCPStream::TCPStream ( const IPV4Host &host, tpport_t port, unsigned size, bool throwflag, timeout_t to ) :
		Socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP ),
#else
TCPStream::TCPStream ( const IPV4Host &host, tpport_t port, unsigned size, bool throwflag, timeout_t to ) :
		streambuf(), Socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP ),
#ifdef	HAVE_OLD_IOSTREAM
		iostream(),
#else
		iostream ( ( streambuf * ) this ),
#endif
#endif
		bufsize ( 0 ), gbuf ( NULL ), pbuf ( NULL )
{
#ifdef	HAVE_OLD_IOSTREAM
	init ( ( streambuf * ) this );
#endif
	family = IPV4;
	timeout = to;
	setError ( throwflag );
	connect ( host, port, size );
}

TCPStream::~TCPStream()
{
	endStream();
}

#ifdef	HAVE_GETADDRINFO

void TCPStream::connect ( const char *target, unsigned mss )
{
	char namebuf[128];
	char *cp;
	struct addrinfo hint, *list = NULL, *next, *first;
	bool connected = false;

	snprintf ( namebuf, sizeof ( namebuf ), "%s", target );
	cp = strrchr ( namebuf, '/' );
	if ( !cp )
		cp = strrchr ( namebuf, ':' );

	if ( !cp ) {
		endStream();
		connectError();
		return;
	}

	* ( cp++ ) = 0;

	memset ( &hint, 0, sizeof ( hint ) );
	hint.ai_family = family;
	hint.ai_socktype = SOCK_STREAM;
	hint.ai_protocol = IPPROTO_TCP;

	if ( getaddrinfo ( namebuf, cp, &hint, &list ) || !list ) {
		endStream();
		connectError();
		return;
	}

	first = list;

#ifdef	TCP_MAXSEG
	if ( mss )
		setsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &mss, sizeof ( mss ) );
#endif

	while ( list ) {
		if ( !::connect ( so, list->ai_addr, ( socklen_t ) list->ai_addrlen ) ) {
			connected = true;
			break;
		}
		next = list->ai_next;
		list = next;
	}

	freeaddrinfo ( first );

	if ( !connected ) {
		endStream();
		connectError();
		return;
	}

	segmentBuffering ( mss );
	Socket::state = CONNECTED;
}

#else

void TCPStream::connect ( const char *target, unsigned mss )
{
	char namebuf[128];
	char *cp;
	bool connected = false;
	struct servent *svc;
	tpport_t port;

	snprintf ( namebuf, sizeof ( namebuf ), "%s", target );
	cp = strrchr ( namebuf, '/' );
	if ( !cp )
		cp = strrchr ( namebuf, ':' );

	if ( !cp ) {
		endStream();
		connectError();
		return;
	}

	* ( cp++ ) = 0;

	if ( isdigit ( *cp ) )
		port = atoi ( cp );
	else {
// 		mutex.enter();
		svc = getservbyname ( cp, "tcp" );
		if ( svc )
			port = ntohs ( svc->s_port );
// 		mutex.leave();
		if ( !svc ) {
			endStream();
			connectError();
			return;
		}
	}

	switch ( family ) {
		case IPV4:
			connect ( IPV4Host ( namebuf ), port, mss );
			break;
		default:
			endStream();
			connectError();
	}
}

#endif

void TCPStream::connect ( const IPV4Host &host, tpport_t port, unsigned mss )
{
	size_t i;
	fd_set fds;
	struct timeval to;
	bool connected = false;
	int rtn;
	long sockopt;
	socklen_t len = sizeof ( sockopt );

#ifdef	TCP_MAXSEG
	if ( mss )
		setsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &mss, sizeof ( mss ) );
#endif

	for ( i = 0 ; i < host.getAddressCount(); i++ ) {
		struct sockaddr_in addr;
		memset ( &addr, 0, sizeof ( addr ) );
		addr.sin_family = AF_INET;
		addr.sin_addr = host.getAddress ( i );
		addr.sin_port = htons ( port );

		if ( timeout )
			setCompletion ( false );

		// Win32 will crash if you try to connect to INADDR_ANY.
		if ( INADDR_ANY == addr.sin_addr.s_addr )
			addr.sin_addr.s_addr = INADDR_LOOPBACK;
		rtn = ::connect ( so, ( struct sockaddr * ) & addr, ( socklen_t ) sizeof ( addr ) );
		if ( !rtn ) {
			connected = true;
			break;
		}

		if ( errno == EINPROGRESS ) {
			FD_ZERO ( &fds );
			FD_SET ( so, &fds );
			to.tv_sec = timeout / 1000;
			to.tv_usec = timeout % 1000 * 1000;

			// timeout check for connect completion

			if ( ::select ( ( int ) so + 1, NULL, &fds, NULL, &to ) < 1 )
				continue;

			getsockopt ( so, SOL_SOCKET, SO_ERROR, ( char * ) &sockopt, &len );
			if ( !sockopt ) {
				connected = true;
				break;
			}
			endSocket();
			so = socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP );
			if ( so == INVALID_SOCKET )
				break;
		}
	}

	setCompletion ( true );
	if ( !connected ) {
		rtn = errno;
		endStream();
		errno = rtn;
		connectError();
		return;
	}

	segmentBuffering ( mss );
	Socket::state = CONNECTED;
}

#ifdef ADAMS_LINUX
TCPStream::TCPStream ( const char *target, Family fam, unsigned mss, bool throwflag, timeout_t to ) :
		Socket ( PF_INET, SOCK_STREAM, IPPROTO_TCP ),
#else
TCPStream::TCPStream ( const char *target, Family fam, unsigned mss, bool throwflag, timeout_t to ) :
		streambuf(), Socket ( PF_INET, SOCK_STREAM, IPPROTO_TCP ),
#ifdef  HAVE_OLD_IOSTREAM
		iostream(),
#else
		iostream ( ( streambuf * ) this ),
#endif
#endif
		timeout ( to ),
		bufsize ( 0 ), gbuf ( NULL ), pbuf ( NULL )
{
	family = fam;
#ifdef	HAVE_OLD_IOSTREAM
	init ( ( streambuf * ) this );
#endif
	setError ( throwflag );
	connect ( target, mss );
}

#ifdef ADAMS_LINUX
TCPStream::TCPStream ( Family fam, bool throwflag, timeout_t to ) :
		Socket ( PF_INET, SOCK_STREAM, IPPROTO_TCP ),
#else
TCPStream::TCPStream ( Family fam, bool throwflag, timeout_t to ) :
		streambuf(), Socket ( PF_INET, SOCK_STREAM, IPPROTO_TCP ),
#ifdef	HAVE_OLD_IOSTREAM
		iostream(),
#else
		iostream ( ( streambuf * ) this ),
#endif
#endif
		timeout ( to ),
		bufsize ( 0 ), gbuf ( NULL ), pbuf ( NULL )
{
	family = fam;
#ifdef	HAVE_OLD_IOSTREAM
	init ( ( streambuf * ) this );
#endif
	setError ( throwflag );
}

#ifdef ADAMS_LINUX
TCPStream::TCPStream ( const TCPStream &source ) :
		Socket ( DUP_SOCK ( source.so, source.state ) )
#else
TCPStream::TCPStream ( const TCPStream &source ) :
		streambuf(), Socket ( DUP_SOCK ( source.so, source.state ) ),
#ifdef	HAVE_OLD_IOSTREAM
		iostream()
#else
		iostream ( ( streambuf * ) this )
#endif
#endif
{
	family = source.family;
#ifdef	HAVE_OLD_IOSTREAM
	init ( ( streambuf * ) this );
#endif
	bufsize = source.bufsize;
	allocate ( bufsize );
}

void TCPStream::connect ( TCPSocket &tcpip )
{
	tpport_t port;

	endStream();
	family = IPV4;
	so = accept ( tcpip.getSocket(), NULL, NULL );
	if ( so == INVALID_SOCKET )
		return;

	IPV4Host host = getPeer ( &port );
	if ( !tcpip.onAccept ( host, port ) ) {
		endSocket();
#ifdef ADAMS_LINUX
		clear ( std::ios::failbit | rdstate() );
#else
		clear ( ios::failbit | rdstate() );
#endif
		return;
	}

	segmentBuffering ( tcpip.getSegmentSize() );
	Socket::state = CONNECTED;
}

void TCPStream::segmentBuffering ( unsigned mss )
{
	unsigned max = 0;
	socklen_t alen = sizeof ( max );

	if ( mss == 1 ) {	// special interactive
		allocate ( 1 );
		return;
	}

#ifdef	TCP_MAXSEG
	if ( mss )
		setsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &max, sizeof ( max ) );
	getsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &max, &alen );
#endif

	if ( max && max < mss )
		mss = max;

	if ( !mss ) {
		if ( max )
			mss = max;
		else
			mss = 536;
		allocate ( mss );
		return;
	}

#ifdef	TCP_MAXSEG
	setsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &mss, sizeof ( mss ) );
#endif

	if ( mss < 80 )
		mss = 80;

	if ( mss * 7 < 64000 )
		bufferSize ( mss * 7 );
	else if ( mss * 6 < 64000 )
		bufferSize ( mss * 6 );
	else
		bufferSize ( mss * 5 );

	if ( mss < 512 )
		sendLimit ( mss * 4 );

	allocate ( mss );
}

int TCPStream::getSegmentSize ( void )
{
	unsigned mss = 0;
#ifdef	TCP_MAXSEG
	socklen_t alen = sizeof ( mss );

	getsockopt ( so, IPPROTO_TCP, TCP_MAXSEG, ( char * ) &mss, &alen );
#endif
	if ( !mss )
		return ( int ) bufsize;

	return mss;
}

void TCPStream::disconnect ( void )
{
	if ( Socket::state == AVAILABLE )
		return;

	endStream();
	so = socket ( family, SOCK_STREAM, IPPROTO_TCP );
	if ( so != INVALID_SOCKET )
		Socket::state = AVAILABLE;
}

void TCPStream::endStream ( void )
{
	if ( bufsize ) sync();
	if ( gbuf )
		delete[] gbuf;
	if ( pbuf )
		delete[] pbuf;
	gbuf = pbuf = NULL;
	bufsize = 0;
	clear();
	endSocket();
}

void TCPStream::allocate ( size_t size )
{
	if ( size < 2 ) {
		bufsize = 1;
		gbuf = pbuf = 0;
		return;
	}

	gbuf = new char[size];
	pbuf = new char[size];
	if ( !pbuf || !gbuf ) {
		error ( errResourceFailure, ( char * ) "Could not allocate socket stream buffers" );
		return;
	}
	bufsize = size;
	clear();

#if (defined(__GNUC__) && (__GNUC__ < 3)) && !defined(WIN32) && !defined(STLPORT)
	setb ( gbuf, gbuf + size, 0 );
#endif
	setg ( gbuf, gbuf + size, gbuf + size );
	setp ( pbuf, pbuf + size );
}

int TCPStream::doallocate()
{
	if ( bufsize )
		return 0;

	allocate ( 1 );
	return 1;
}

int TCPStream::uflow()
{
	int ret = underflow();

	if ( ret == EOF )
		return EOF;

	if ( bufsize != 1 )
		gbump ( 1 );

	return ret;
}

int TCPStream::underflow()
{
	ssize_t rlen = 1;
	unsigned char ch;

	if ( bufsize == 1 ) {
		if ( Socket::state == STREAM )
			rlen = ::read ( ( int ) so, ( char * ) & ch, 1 );
		else if ( timeout && !Socket::isPending ( pendingInput, timeout ) ) {
#ifdef ADAMS_LINUX
			clear ( std::ios::failbit | rdstate() );
#else
			clear ( ios::failbit | rdstate() );
#endif
			error ( errTimeout, ( char * ) "Socket read timed out", socket_errno );
			return EOF;
		} else
			rlen = readData ( &ch, 1 );
		if ( rlen < 1 ) {
			if ( rlen < 0 ) {
#ifdef ADAMS_LINUX
				clear ( std::ios::failbit | rdstate() );
#else
				clear ( ios::failbit | rdstate() );
#endif
				error ( errInput, ( char * ) "Could not read from socket", socket_errno );
			}
			return EOF;
		}
		return ch;
	}

	if ( !gptr() )
		return EOF;

	if ( gptr() < egptr() )
		return ( unsigned char ) *gptr();

	rlen = ( ssize_t ) ( ( gbuf + bufsize ) - eback() );
	if ( Socket::state == STREAM )
		rlen = ::read ( ( int ) so, ( char * ) eback(), _IOLEN64 rlen );
	else if ( timeout && !Socket::isPending ( pendingInput, timeout ) ) {
#ifdef ADAMS_LINUX
		clear ( std::ios::failbit | rdstate() );
#else
		clear ( ios::failbit | rdstate() );
#endif
		error ( errTimeout, ( char * ) "Socket read timed out", socket_errno );
		return EOF;
	} else
		rlen = readData ( eback(), rlen );
	if ( rlen < 1 ) {
//		clear(ios::failbit | rdstate());
		if ( rlen < 0 )
			error ( errNotConnected, ( char * ) "Connection error", socket_errno );
		else {
			error ( errInput, ( char * ) "Could not read from socket", socket_errno );
#ifdef ADAMS_LINUX
			clear ( std::ios::failbit | rdstate() );
#else
			clear ( ios::failbit | rdstate() );
#endif
		}
		return EOF;
	}
	error ( errSuccess );

	setg ( eback(), eback(), eback() + rlen );
	return ( unsigned char ) *gptr();
}

bool TCPStream::isPending ( Pending pending, timeout_t timer )
{
	if ( pending == pendingInput && in_avail() )
		return true;
	else if ( pending == pendingOutput )
		flush();

	return Socket::isPending ( pending, timer );
}

int TCPStream::sync ( void )
{
	overflow ( EOF );
	setg ( gbuf, gbuf + bufsize, gbuf + bufsize );
	return 0;
}

#ifdef	HAVE_SNPRINTF
size_t TCPStream::printf ( const char *format, ... )
{
	va_list args;
	size_t len;
	char *buf;

	va_start ( args, format );
	overflow ( EOF );
	len = pptr() - pbase();
	buf = pptr();
	vsnprintf ( buf, len, format, args );
	va_end ( args );
	len = strlen ( buf );
	if ( Socket::state == STREAM )
		return ::write ( ( int ) so, buf, _IOLEN64 len );
	else
		return writeData ( buf, len );
}
#endif

int TCPStream::overflow ( int c )
{
	unsigned char ch;
	ssize_t rlen, req;

	if ( bufsize == 1 ) {
		if ( c == EOF )
			return 0;

		ch = ( unsigned char ) ( c );
		if ( Socket::state == STREAM )
			rlen = ::write ( ( int ) so, ( const char * ) & ch, 1 );
		else
			rlen = writeData ( &ch, 1 );
		if ( rlen < 1 ) {
			if ( rlen < 0 ) {
#ifdef ADAMS_LINUX
				clear ( std::ios::failbit | rdstate() );
#else
				clear ( ios::failbit | rdstate() );
#endif
				error ( errOutput, ( char * ) "Could not write to socket", socket_errno );
			}
			return EOF;
		} else
			return c;
	}

	if ( !pbase() )
		return EOF;

	req = ( ssize_t ) ( pptr() - pbase() );
	if ( req ) {
		if ( Socket::state == STREAM )
			rlen = ::write ( ( int ) so, ( const char * ) pbase(), req );
		else
			rlen = writeData ( pbase(), req );
		if ( rlen < 1 ) {
			if ( rlen < 0 ) {
#ifdef ADAMS_LINUX
				clear ( std::ios::failbit | rdstate() );
#else
				clear ( ios::failbit | rdstate() );
#endif
				error ( errOutput, ( char * ) "Could not write to socket", socket_errno );
			}
			return EOF;
		}
		req -= rlen;
	}

	// if write "partial", rebuffer remainder

	if ( req )
//		memmove(pbuf, pptr() + rlen, req);
		memmove ( pbuf, pbuf + rlen, req );
	setp ( pbuf, pbuf + bufsize );
	pbump ( req );

	if ( c != EOF ) {
		*pptr() = ( unsigned char ) c;
		pbump ( 1 );
	}
	return c;
}

TCPSession::TCPSession ( const IPV4Host &ia,
                         tpport_t port, size_t size, int pri, size_t stack ) :
		/*Thread(pri, stack),*/ TCPStream ( IPV4 )
{
	setCompletion ( false );
	setError ( false );
	allocate ( size );

	size_t i;
	for ( i = 0 ; i < ia.getAddressCount(); i++ ) {
		struct sockaddr_in addr;
		memset ( &addr, 0, sizeof ( addr ) );
		addr.sin_family = AF_INET;
		addr.sin_addr = ia.getAddress ( i );
		addr.sin_port = htons ( port );

		// Win32 will crash if you try to connect to INADDR_ANY.
		if ( INADDR_ANY == addr.sin_addr.s_addr )
			addr.sin_addr.s_addr = INADDR_LOOPBACK;
		if ( ::connect ( so, ( struct sockaddr * ) &addr, ( socklen_t ) sizeof ( addr ) ) == 0 )
			break;

		if ( errno == EINPROGRESS ) {
			Socket::state = CONNECTING;
			return;
		}
	}

	if ( i == ia.getAddressCount() ) {
		endSocket();
		Socket::state = INITIAL;
		return;
	}

	setCompletion ( true );
	Socket::state = CONNECTED;
}


TCPSession::TCPSession ( TCPSocket &s, int pri, size_t stack ) :
		/*Thread(pri, stack),*/ TCPStream ( s )
{
	setCompletion ( true );
	setError ( false );
}

TCPSession::~TCPSession()
{
// 	terminate();
	endStream();
}

int TCPSession::waitConnection ( timeout_t timer )
{
	long sockopt = 0;
	socklen_t len = sizeof ( sockopt );

	switch ( Socket::state ) {
		case INITIAL:
			return -1;
		case CONNECTED:
			break;
		case CONNECTING:
			if ( !Socket::isPending ( pendingOutput, timer ) ) {
				endSocket();
				Socket::state = INITIAL;
				return -1;
			}

			getsockopt ( so, SOL_SOCKET, SO_ERROR, ( char * ) &sockopt, &len );
			if ( sockopt ) {
				endSocket();
				Socket::state = INITIAL;
				return -1;
			}
		default:
			break;
	}
	Socket::state = CONNECTED;
	return 0;
}

// void TCPSession::initial(void)
// {
// 	if(waitConnection(60000))
// 		exit();
// 					}

std::ostream& operator<< ( std::ostream &os, const IPV4Address &ia )
{
	os << inet_ntoa ( getaddress ( ia ) );
	return os;
}

// #ifdef CCXX_NAT
// #  ifdef HAVE_SYS_TYPES_H
// #   include <sys/types.h>
// #  endif
// #  ifdef HAVE_SYS_SOCKET_H
// #   include <sys/socket.h>
// #  endif
// # ifdef HAVE_NAT_NETFILTER // Linux
// #  ifdef HAVE_LIMITS_H
// #   include <limits.h>
// #  endif
// #  ifdef HAVE_LINUX_NETFILTER_IPV4_H
// #   include <linux/netfilter_ipv4.h>
// #  endif
// #  ifdef HAVE_LINUX_NETFILTER_IPV6_H
// #   include <linux/netfilter_ipv6.h>
// #  endif
// # else
// #  ifdef HAVE_NET_IP6_H
// #   include <netinet/ip6.h>
// #  endif
// #  ifdef HAVE_NETINET_IN_H
// #   include <netinet/in.h>
// #  endif
// #  ifdef HAVE_NET_IF_H
// #   include <net/if.h>
// #  endif
// #  ifdef HAVE_SYS_IOCTL_H
// #   include <sys/ioctl.h>
// #  endif
// #  ifdef HAVE_IOCTL_H
// #   include <ioctl.h>
// #  endif
// #  ifdef HAVE_UNISTD_H
// #   include <unistd.h>
// #  endif
// #  ifdef HAVE_ERRNO_H
// #   include <errno.h>
// #  endif
// #  ifdef HAVE_NAT_IPF // Solaris, *BSD (except OpenBSD), HP-UX
// #   ifdef HAVE_NETINET_IP_COMPAT_H
// #    include <netinet/ip_compat.h>
// #   endif
// #   ifdef HAVE_IP_COMPAT_H
// #    include <ip_compat.h>
// #   endif
// #   ifdef HAVE_NETINET_IP_FIL_COMPAT_H
// #    include <netinet/ip_fil_compat.h>
// #   endif
// #   ifdef HAVE_IP_FIL_COMPAT_H
// #    include <ip_fil_compat.h>
// #   endif
// #   ifdef HAVE_NETINET_IP_FIL_H
// #    include <netinet/ip_fil.h>
// #   endif
// #   ifdef HAVE_IP_FIL_H
// #    include <ip_fil.h>
// #   endif
// #   ifdef HAVE_NETINET_IP_NAT_H
// #    include <netinet/ip_nat.h>
// #   endif
// #   ifdef HAVE_IP_NAT_H
// #    include <ip_nat.h>
// #   endif
// #  endif
// #  ifdef HAVE_NAT_PF // OpenBSD
// #   ifdef HAVE_NET_PFVAR_H
// #    include <net/pfvar.h>
// #   endif
// #  endif
// # endif
// #endif

#ifdef HAVE_NAT

#ifdef HAVE_NAT_NETFILTER // Linux
natResult natv4Lookup ( SOCKET sfd, struct sockaddr_in * nat )
{

	struct sockaddr_in local;
	socklen_t nlen = sizeof ( *nat ), llen = sizeof ( local );

	if ( getsockname ( sfd, ( struct sockaddr * ) &local, &llen ) )
		return natSocknameErr;

	memset ( &nat->sin_addr.s_addr, 0, sizeof ( nat->sin_addr.s_addr ) );
	if ( getsockopt ( sfd, SOL_IP, SO_ORIGINAL_DST, ( struct sockaddr * ) nat, &nlen ) )
		return natIFaceErr;

	if ( local.sin_addr.s_addr == nat->sin_addr.s_addr )
		return natSearchErr;

	nat->sin_family = local.sin_family;

	return natOK;
}

#endif

#ifdef HAVE_NAT_IPF // Solaris, *BSD (except OpenBSD), HP-UX, etc.
natResult natv4Lookup ( int sfd, struct sockaddr_in * nat )
{

	static int natfd = -1;
	struct sockaddr_in local, peer;
	socklen_t  nlen = sizeof ( *nat ), llen = sizeof ( local ), plen = sizeof ( peer );
	int socktype;
	socklen_t stlen = sizeof ( socktype );
	struct natlookup nlu;
	int nres;

	if ( natfd < 0 )
		if ( ( natfd = open ( NAT_DEVICE, O_RDONLY, 0 ) ) < 0 )
			return natDevUnavail;

	if ( getsockname ( sfd, ( struct sockaddr * ) &local, &llen ) )
		return natSocknameErr;
	if ( getpeername ( sfd, ( struct sockaddr * ) &peer, &plen ) )
		return natPeernameErr;
	if ( getsockopt ( sfd, SOL_SOCKET, SO_TYPE, ( int * ) &socktype, &stlen ) )
		return natSockTypeErr;

	memset ( &nlu.nl_realip.s_addr, 0, sizeof ( nlu.nl_realip.s_addr ) );
	nlu.nl_inip = local.sin_addr;
	nlu.nl_inport = local.sin_port;
	nlu.nl_outip = peer.sin_addr;
	nlu.nl_outport = peer.sin_port;
	nlu.nl_flags = ( socktype == SOCK_STREAM ) ? IPN_TCP : IPN_UDP;

	if ( 63 == ( SIOCGNATL & 0xff ) ) {
		struct natlookup * nlup = &nlu;
		nres = ioctl ( natfd, SIOCGNATL, &nlup );
	} else
		nres = ioctl ( natfd, SIOCGNATL, &nlu );

	if ( nres ) {
		if ( errno != ESRCH ) {
			close ( natfd );
			natfd = -1;
			return natIFaceErr;
		} else
			return natSearchErr;
	}

	if ( local.sin_addr.s_addr == nlu.nl_realip.s_addr )
		return natSearchErr;

	nat->sin_family = local.sin_family;
	nat->sin_port = nlu.nl_realport;
	nat->sin_addr = nlu.nl_realip;

	return natOK;
}

#endif

#ifdef HAVE_NAT_PF // OpenBSD
natResult natv4Lookup ( SOCKET sfd, struct sockaddr_in * nat )
{

	static int natfd = -1;
	struct sockaddr_in local, peer;
	socklen_t  nlen = sizeof ( *nat ), llen = sizeof ( local ), plen = sizeof ( peer );
	int socktype;
	socklen_t stlen = sizeof ( socktype );
	struct pfioc_natlook nlu;
	int nres;

	if ( natfd < 0 )
		if ( ( natfd = open ( NAT_DEVICE, O_RDWR ) ) < 0 )
			return natDevUnavail;

	if ( getsockname ( sfd, ( struct sockaddr * ) &local, &llen ) )
		return natSocknameErr;
	if ( getpeername ( sfd, ( struct sockaddr * ) &peer, &plen ) )
		return natPeernameErr;
	if ( getsockopt ( sfd, SOL_SOCKET, SO_TYPE, ( int * ) &socktype, &stlen ) )
		return natSockTypeErr;

	memset ( &nlu, 0, sizeof ( nlu ) );
	nlu.daddr.v4.s_addr = local.sin_addr.s_addr;
	nlu.dport = local.sin_port;
	nlu.saddr.v4.s_addr = peer.sin_addr.s_addr;
	nlu.sport = peer.sin_port;
	nlu.af = AF_INET;
	nlu.proto = ( socktype == SOCK_STREAM ) ? IPPROTO_TCP : IPROTO_UDP;
	nlu.direction = PF_OUT;

	if ( ioctl ( natfd, DIOCNATLOOK, &nlu ) ) {
		if ( errno != ESRCH ) {
			close ( natfd );
			natfd = -1;
			return natIFaceErr;
		} else
			return natSearchErr;
	}

	if ( local.sin_addr.s_addr == nlu.raddr.v4.s_addr )
		return natSearchErr;

	nat->sin_family = local.sin_family;
	nat->sin_port = nlu.rdport;
	nat->sin_addr = nlu.rdaddr.v4;

	return natOK;
}

#endif

char * natErrorString ( natResult res )
{
	return ( char * ) natmsg[ ( res >= natOK && res <= natIFaceErr ) ? res : natUnkownErr ];
}

#else
natResult natv4Lookup ( SOCKET sfd, struct sockaddr_in * nat )
{
	return natNotSupported;
}


char * natErrorString ( natResult res )
{
	return (char *)"nat support not included";
}

#endif


SimpleTCPStream::SimpleTCPStream ( TCPSocket &server, size_t size )
		: Socket ( accept ( server.getSocket(), NULL, NULL ) )
{
	tpport_t port;
	IPV4Host host = getPeer ( &port );

	if ( ! server.onAccept ( host, port ) ) {
		endSocket();
		error ( errConnectRejected );
		return;
	}

	Socket::state = CONNECTED;
}

SimpleTCPStream::SimpleTCPStream ( const IPV4Host &host, tpport_t port, size_t size )
		: Socket ( AF_INET, SOCK_STREAM, IPPROTO_TCP )
{
	Connect ( host, port, size );
}

SimpleTCPStream::~SimpleTCPStream()
{
	endStream();
}

IPV4Host SimpleTCPStream::getSender ( tpport_t *port ) const
{
	return IPV4Host();
}

void SimpleTCPStream::Connect ( const IPV4Host &host, tpport_t port, size_t size )
{
	size_t i;

	for ( i = 0 ; i < host.getAddressCount(); i++ ) {
		struct sockaddr_in addr;
		memset ( &addr, 0, sizeof ( addr ) );
		addr.sin_family = AF_INET;
		addr.sin_addr = host.getAddress ( i );
		addr.sin_port = htons ( port );

		// Win32 will crash if you try to connect to INADDR_ANY.
		if ( INADDR_ANY == addr.sin_addr.s_addr )
			addr.sin_addr.s_addr = INADDR_LOOPBACK;
		lout1 << "Connecting to: " << host.getHostname() << " port:" << port << endl;
		if ( ::connect ( so, ( struct sockaddr * ) &addr, ( socklen_t ) sizeof ( addr ) ) == 0 )
			break;
	}

	if ( i == host.getAddressCount() ) {
		connectError();
		endSocket();
		return;
	}

	lout1 << "Socket Connected." << endl;

	Socket::state = CONNECTED;
}

SimpleTCPStream::SimpleTCPStream()
		: Socket ( PF_INET, SOCK_STREAM, IPPROTO_TCP )
{
	// Do nothing extra.
}

SimpleTCPStream::SimpleTCPStream ( const SimpleTCPStream &source ) :
		Socket ( dup ( source.so ) )
{
}

void SimpleTCPStream::endStream ( void )
{
	endSocket();
}

ssize_t SimpleTCPStream::read ( char *bytes, size_t length, timeout_t timeout )
{
	// Declare local variables.
	ssize_t rlen = 0;
	size_t totalrecv = 0;
	char *currentpos = bytes;

	// Check for reasonable requested length.
	if ( length < 1 ) {
		return ( ssize_t ) totalrecv;
	}

	while ( totalrecv < length ) {
		// Check for timeout condition.
		if ( timeout ) {
			if ( ! isPending ( pendingInput, timeout ) ) {
				error ( errTimeout );
				return -1;
			}
		}

		// Attempt to read data.
		rlen = _IORET64 ::recv ( so, ( char * ) currentpos, _IOLEN64 ( length - totalrecv ), 0 );
		if ( rlen == 0 || rlen == -1 ) {
			break;
		}
		// cout << "received " << rlen << " bytes, remaining " << length - totalrecv << flush;

		totalrecv += rlen;
		currentpos += rlen;
	}

	// Set error condition if necessary.
	if ( rlen == -1 ) {
		error ( errInput );
	}

	// Return total number of bytes recieved.
	return ( ssize_t ) totalrecv;
}

ssize_t SimpleTCPStream::write ( const char *bytes, size_t length, timeout_t timeout )
{
	// Declare local variables.
	ssize_t rlen = 0;

	// Check for reasonable requested length.
	if ( length < 1 ) {
		return rlen;
	}

	// Check for timeout condition.
	if ( timeout ) {
		if ( ! isPending ( pendingOutput, timeout ) ) {
			error ( errTimeout );
			return -1;
		}
	}

	// Attempt to write data.
	rlen = _IORET64 ::send ( so, ( const char * ) bytes, _IOLEN64 length, MSG_NOSIGNAL );
	if ( rlen == -1 ) {
		error ( errOutput );
	}

	return rlen;
}

ssize_t SimpleTCPStream::peek ( char *bytes, size_t length, timeout_t timeout )
{
	// Declare local variables.
	ssize_t rlen = 0;
	size_t totalrecv = 0;
	char *currentpos = bytes;

	// Check for reasonable requested length.
	if ( length < 1 ) {
		return ( ssize_t ) totalrecv;
	}

	while ( totalrecv < length ) {
		// Check for timeout condition.
		if ( timeout ) {
			if ( ! isPending ( pendingInput, timeout ) ) {
				error ( errTimeout );
				return -1;
			}
		}

		// Attempt to read data.
		rlen = _IORET64 ::recv ( so, ( char * ) currentpos, _IOLEN64 ( length - totalrecv ), MSG_PEEK );
		if ( rlen == 0 || rlen == -1 ) {
			break;
		}
		// cout << "received " << rlen << " bytes, remaining " << length - totalrecv << flush;

		totalrecv += rlen;
		currentpos += rlen;
	}

	// Set error condition if necessary.
	if ( rlen == -1 ) {
		error ( errInput );
	}

	// Return total number of bytes recieved.
	return ( ssize_t ) totalrecv;
}

bool SimpleTCPStream::isPending ( Pending pending, timeout_t timeout )
{
	return Socket::isPending ( pending, timeout );
}

