/* Copyright Abandoned 1996 TCX DataKonsult AB & Monty Program KB & Detron HB
   This file is public domain and comes with NO WARRANTY of any kind */

/* Error messages for mysql clients */
/* error messages for the demon is in share/language/errmsg.sys */

void	init_client_errs(void);
extern char *client_errors[];		/* Error messages */

#define CR_MIN_ERROR		2000	/* For easier client code */
#define CR_MAX_ERROR		2999
#define ER(X) client_errors[(X)-CR_MIN_ERROR]
#define CLIENT_ERRMAP		2	/* Errormap used by my_error() */

#define CR_UNKNOWN_ERROR	2000
#define CR_SOCKET_CREATE_ERROR	2001
#define CR_CONNECTION_ERROR	2002
#define CR_CONN_HOST_ERROR	2003
#define CR_IPSOCK_ERROR		2004
#define CR_UNKNOWN_HOST		2005
#define CR_SERVER_GONE_ERROR	2006
#define CR_VERSION_ERROR	2007
#define CR_OUT_OF_MEMORY	2008
#define CR_WRONG_HOST_INFO	2009
#define CR_LOCALHOST_CONNECTION 2010
#define CR_TCP_CONNECTION	2011
#define CR_SERVER_HANDSHAKE_ERR 2012
#define CR_SERVER_LOST		2013
#define CR_COMMANDS_OUT_OF_SYNC 2014
