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
                          address.cpp  -  description
                             -------------------
    begin                : Mar 30 2009
    copyright            : (C) 200 by Piergiorgio Betti
    email                : piergiorgio.betti@e-tech.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/


#include <address.h>

#include <cstdlib>
#include <unistd.h>
#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>

// Mutex IPV4Address::mutex;

IPV4Host IPV4Host::_host_;

const IPV4MulticastValidator IPV4Multicast::validator;

void IPV4MulticastValidator::operator() ( const in_addr address ) const
{
}

IPV4Address::IPV4Address ( const IPV4Validator *_validator )
		: validator ( _validator ), ipaddr ( NULL ), addr_count ( 0 ), hostname ( NULL )
{
	*this = ( long unsigned int ) INADDR_ANY;
}

IPV4Address::IPV4Address ( const char *address, const IPV4Validator *_validator ) :
		validator ( _validator ), ipaddr ( NULL ), addr_count ( 0 ), hostname ( NULL )
{
	if ( this->validator )
		this->validator = validator;
	if ( address == 0 || !strcmp ( address, "*" ) )
		setAddress ( NULL );
	else
		setAddress ( address );
}

IPV4Address::IPV4Address ( struct in_addr addr, const IPV4Validator *_validator ) :
		validator ( _validator ), ipaddr ( NULL ), hostname ( NULL )
{
	if ( this->validator ) {
		this->validator = validator;
		( *validator ) ( addr );
	}
	addr_count = 1;
	ipaddr = new struct in_addr[1];
	ipaddr[0] = addr;
}

IPV4Address::IPV4Address ( const IPV4Address &rhs ) :
		validator ( rhs.validator ), addr_count ( rhs.addr_count ), hostname ( NULL )
{
	ipaddr = new struct in_addr[addr_count];
	memcpy ( ipaddr, rhs.ipaddr, sizeof ( struct in_addr ) * addr_count );
}

IPV4Address::~IPV4Address()
{
	if ( ipaddr ) {
		delete[] ipaddr;
		ipaddr = NULL;
	}
	if ( hostname ) {
		delete[] hostname;
		hostname = NULL;
	}
}

struct in_addr IPV4Address::getAddress ( void ) const {
		return ipaddr[0];
	}

struct in_addr IPV4Address::getAddress ( size_t i ) const {
		return ( i < addr_count ? ipaddr[i] : ipaddr[0] );
	}

bool IPV4Address::isInetAddress ( void ) const
{
	struct in_addr addr;
	memset ( &addr, 0, sizeof ( addr ) );
	if ( memcmp ( &addr, &ipaddr[0], sizeof ( addr ) ) )
		return true;
	return false;
}

IPV4Address &IPV4Address::operator= ( const char * str )
{
	if ( str == 0 || !strcmp ( str, "*" ) )
		str = "0.0.0.0";

	setAddress ( str );

	return *this;
}

IPV4Address &IPV4Address::operator= ( struct in_addr addr )
{
	if ( ipaddr )
		delete[] ipaddr;
	if ( validator )
		( *validator ) ( addr );
	addr_count = 1;
	ipaddr = new struct in_addr[1];
	ipaddr[0] = addr;
	if ( hostname )
		delete[] hostname;
	hostname = NULL;
	return *this;
}

IPV4Address &IPV4Address::operator= ( unsigned long addr )
{
	void *vp = ( void * ) & addr;
	struct in_addr *aptr = ( struct in_addr * ) vp;

	if ( validator )
		( *validator ) ( *aptr );

	if ( ipaddr )
		delete[] ipaddr;

	addr_count = 1;
	ipaddr = new struct in_addr[1];
	memcpy ( ipaddr, &addr, sizeof ( struct in_addr ) );
	if ( hostname )
		delete[] hostname;
	hostname = NULL;
	return *this;
}

IPV4Address &IPV4Address::operator= ( const IPV4Address & rhs )
{
	if ( this == &rhs ) return *this;

	addr_count = rhs.addr_count;
	if ( ipaddr )
		delete[] ipaddr;
	ipaddr = new struct in_addr[addr_count];
	memcpy ( ipaddr, rhs.ipaddr, sizeof ( struct in_addr ) * addr_count );
	validator = rhs.validator;
	if ( hostname )
		delete[] hostname;
	hostname = NULL;

	return *this;
}

bool IPV4Address::operator== ( const IPV4Address &a ) const
{
	const IPV4Address *smaller, *larger;
	size_t s, l;

	if ( addr_count > a.addr_count ) {
		smaller = &a;
		larger  = this;
	} else {
		smaller = this;
		larger  = &a;
	}

	// Loop through all addr's in the smaller and make sure
	// that they are all in the larger
	for ( s = 0; s < smaller->addr_count; s++ ) {
		// bool found = false;
		for ( l = 0; l < larger->addr_count &&
		                memcmp ( ( char * ) &ipaddr[s], ( char * ) &a.ipaddr[l], sizeof ( struct in_addr ) ); l++ );
		if ( l == larger->addr_count ) return false;
	}
	return true;
}

bool IPV4Address::operator!= ( const IPV4Address &a ) const
{
	// Impliment in terms of operator==
	return ( *this == a ? false : true );
}

IPV4Host &IPV4Host::operator&= ( const IPV4Mask & ma )
{
	for ( size_t i = 0; i < addr_count; i++ ) {
		struct in_addr mask = ma.getAddress();
		unsigned char *a = ( unsigned char * ) & ipaddr[i];
		unsigned char *m = ( unsigned char * ) & mask;

		for ( size_t j = 0; j < sizeof ( struct in_addr ); ++j )
			* ( a++ ) &= * ( m++ );
	}
	if ( hostname )
		delete[] hostname;
	hostname = NULL;

	return *this;
}

IPV4Host::IPV4Host ( struct in_addr addr ) :
		IPV4Address ( addr ) {}

IPV4Host::IPV4Host ( const char *host ) :
		IPV4Address ( host )
{
	char namebuf[256];

	if ( !host ) {
		if ( this == &_host_ ) {
			gethostname ( namebuf, 256 );
			setAddress ( namebuf );
		} else
			*this = _host_;
	}
}

bool IPV4Address::setIPAddress ( const char *host )
{
	if ( !host )
		return false;

	struct in_addr l_addr;

	int ok = inet_aton ( host, &l_addr );
	if ( validator )
		( *validator ) ( l_addr );
	if ( !ok )
		return false;
	*this = l_addr;

	return true;
}

void IPV4Address::setAddress ( const char *host )
{
	if ( hostname )
		delete[] hostname;
	hostname = NULL;

	if ( !host ) { // The way this is currently used, this can never happen
		*this = ( long unsigned int ) htonl ( INADDR_ANY );
		return;
	}

	if ( !setIPAddress ( host ) ) {
		struct hostent *hp;
		struct in_addr **bptr;
#if defined(__GLIBC__)
		char   hbuf[8192];
		struct hostent hb;
		int    rtn;

		if ( gethostbyname_r ( host, &hb, hbuf, sizeof ( hbuf ), &hp, &rtn ) )
			hp = NULL;
#elif defined(sun)
		char   hbuf[8192];
		struct hostent hb;
		int    rtn;

		hp = gethostbyname_r ( host, &hb, hbuf, sizeof ( hbuf ), &rtn );
#elif (defined(__osf__) || defined(WIN32))
		hp = gethostbyname ( host );
#else
// 		mutex.enterMutex();
		hp = gethostbyname ( host );
// 		mutex.leaveMutex();
#endif
		if ( !hp ) {
			if ( ipaddr )
				delete[] ipaddr;
			ipaddr = new struct in_addr[1];
			memset ( ( void * ) &ipaddr[0], 0, sizeof ( ipaddr ) );
			return;
		}

		// Count the number of IP addresses returned
		addr_count = 0;
		for ( bptr = ( struct in_addr ** ) hp->h_addr_list; *bptr != NULL; bptr++ ) {
			addr_count++;
		}

		// Allocate enough memory
		if ( ipaddr )
			delete[] ipaddr;	// Cause this was allocated in base
		ipaddr = new struct in_addr[addr_count];

		// Now go through the list again assigning to
		// the member ipaddr;
		bptr = ( struct in_addr ** ) hp->h_addr_list;
		for ( unsigned int i = 0; i < addr_count; i++ ) {
			if ( validator )
				( *validator ) ( *bptr[i] );
			ipaddr[i] = *bptr[i];
		}
	}
}

IPV4Broadcast::IPV4Broadcast ( const char *net ) :
		IPV4Address ( net )
{
}

IPV4Mask::IPV4Mask ( const char *mask )
{
	unsigned long x = 0xffffffff;
	int l = 32 - atoi ( mask );

	if ( setIPAddress ( mask ) )
		return;

	if ( l < 1 || l > 32 ) {
		return;
	}

	*this = htonl ( x << l );
}

const char *IPV4Address::getHostname ( void ) const
{
	struct hostent *hp = NULL;
	struct in_addr addr0;

	memset ( &addr0, 0, sizeof ( addr0 ) );
	if ( !memcmp ( &addr0, &ipaddr[0], sizeof ( addr0 ) ) )
		return NULL;

#if defined(__GLIBC__)
	char   hbuf[8192];
	struct hostent hb;
	int    rtn;
	if ( gethostbyaddr_r ( ( char * ) &ipaddr[0], sizeof ( addr0 ), AF_INET, &hb, hbuf, sizeof ( hbuf ), &hp, &rtn ) )
		hp = NULL;
#elif defined(sun)
	char   hbuf[8192];
	struct hostent hb;
	int    rtn;
	hp = gethostbyaddr_r ( ( char * ) & ipaddr[0], ( int ) sizeof ( addr0 ), ( int ) AF_INET, &hb, hbuf, ( int ) sizeof ( hbuf ), &rtn );
#elif defined(__osf__) || defined(WIN32)
	hp = gethostbyaddr ( ( char * ) & ipaddr[0], sizeof ( addr0 ), AF_INET );
#else
// 	mutex.enterMutex();
	hp = gethostbyaddr ( ( char * ) & ipaddr[0], sizeof ( addr0 ), AF_INET );
// 	mutex.leaveMutex();
#endif
	if ( hp ) {
		if ( hostname )
			delete[] hostname;
		hostname = new char[ ( strlen ( hp->h_name ) + 1 ) ];
		strcpy( hostname, hp->h_name );
		return hostname;
	} else {
		return inet_ntoa ( ipaddr[0] );
	}
}

IPV4Host operator& ( const IPV4Host &addr, const IPV4Mask &mask )
{
	IPV4Host temp = addr;
	temp &= mask;
	return temp;
}

IPV4Multicast::IPV4Multicast() :
		IPV4Address ( &validator )
{ }

IPV4Multicast::IPV4Multicast ( const struct in_addr address ) :
		IPV4Address ( address, &validator )
{ }

IPV4Multicast::IPV4Multicast ( const char *address ) :
		IPV4Address ( address, &validator )
{ }

