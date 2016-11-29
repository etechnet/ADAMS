/* FILE: interface.c
 *
 * by pts@fazekas.hu at Wed May  8 17:50:51 CEST 2002
 *
 * This file implements PostgreSQL 7.0 database inteface functionality using
 * the libpq C client library (shipped with the PostgreSQL sources) for the
 * ScriptBasic interpreter as an external module.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

UXLIBS: -lpq
NTLIBS: libpq.lib
 */

/* #define PTS_DEBUG 1 */
#define PTS_MODULE "pgsqlinterf.c"

/* vvv Debian GNU/Linux: /usr/include/postgresql/libpq-fe.h */
#include <postgresql/libpq-fe.h>
#include "../../basext.h"
#include <stdio.h> /* simple debugging */
#include <string.h> /* memcmp() */
#include <stdlib.h> /* free() */

#ifdef PTS_DEBUG
#  define DEBUGMSG(x) x
#  include <assert.h>
#  undef NDEBUG
#else
#  define DEBUGMSG(x)
#  define assert(x)
#  define NDEBUG 1
#endif

#ifndef InvalidOid
#define InvalidOid 0
#endif

/* AIR 09/06/09 Added missing NAMEDATALEN define */
#ifndef NAMEDATALEN
#define NAMEDATALEN 64
#endif

/**
PSQL PostgreSQL database connectivity module

ScriptBasic T<PGSQL::> is an extension for ScriptBasic (http://www.scriptbasic.com/)
that lets you access PostgreSQL (>=7.0) databases from your ScriptBasic
program. You can connect to a PostgreSQL database server running on the
local host or any other machine on the internet, you can run SQL commands
on the server, and retrieve resulting data rows.

ScriptBasic PGSQL:: is written by pts@fazekas.hu in Early June 2002. The program
is free software, and it is licensed under the GNU GPL >=2.0.

To use this module from a BASIC program you have to include the file T<psql.bas> with the line

=verbatim
import psql.bas
=noverbatim

to have all the neccessary function defintiions. This file and the neccessary T<psql.dll> (or T<psql.so>)
are installed in the include and in the modules directories on Windows (or UNIX).

All functions and subroutines belonging to ScriptBasic T<PGSQL::> begin with
T<PGSQL::PG>.

There are two kinds of PostgreSQL objects: connections and resultsets. You
can refer to these objects with handles (small integer numbers), similar to
the way you use files in ScriptBasic. To issue SQL commands to the
PostgreSQL database, you have to connect the database first (and thus you'll
get the handle of the connection object). To retrieve data rows from a query
to the database, you have to use resultset objects. Connection objects are
created with the T<PGSQL::PGopen()> function, resultset objects are created
with the T<PGSQL::PGexec()> function, and both kinds of objects must be
released by calling T<PGSQL::PGclose()>.

Please refer to the PostgreSQL documentation for all non-ScriptBasic
specific aspects of the PostgreSQL RDBMS on the following URL:

=verbatim
	http://www.postgresql.org/users-lounge/docs/7.2/postgres/
=noverbatim

This documentation assumes that you are quite familiar with PostgreSQL, you
understand the concepts and you've already used the `psql'
command line client.

To connect to the database, you have to obtain a connection string from
your database administrator, which contains the following information:

=itemize
=item  server hostname
=item  database name
=item  username
=item  password
=noitemize

An example connection string:

=verbatim
	"host='x.y.org' dbname='suicide' user='john' password='doe'"
=noverbatim

Connecting example:

=verbatim
	c = PGSQL::PGopen("host='x.y.org' dbname='suicide' user='john' password='doe'")
	if isstring(c) then
	  print "Error connecting: (", rtrim(c), ")\n"
	  stop
	end if
=noverbatim

After that, you can issue SQL commands. Example:

=verbatim
	r=PGSQL::PGexec(c, "UPDATE people SET death=NOW() WHERE name=", "john doe", " AND death IS NULL")
	if isstring(r) then
	  print "Error querying: (", rtrim(c), ")\n"
	  stop
	end if
	print "cmdStatus=(", PGSQL::PGcmdStatus(r), ")\n"
	print "cmdTuples=(", PGSQL::PGcmdTuples(r), ")\n"
	PGclose(r)
=noverbatim

Please note that strings in SQL commands are delimited by single quotes, and
you must escape special characters inside strings (such as the backspace and
the single quote mark). This is automatically done when you call
PGSQL::PGexec. The simple rule is: just supply the string to be quoted as a
separate argument to PGSQL::PGexec(). For example,

=verbatim
	PGSQL::PGexec(c, a, b, s, undef, d, e)
=noverbatim

is equivalent to:

=verbatim
	PGSQL::PGexec(c, a & PGSQL::PGescapeString(b) & s & PGSQLL::PGescapeBytea(d) & e )
=noverbatim

And an example for SQL queries that return data:

=verbatim
	r=PGSQL::PGexec(c, "SELECT * FROM people")
	if isstring(r) then
	  print "Error querying: (", rtrim(c), ")\n"
	  stop
	end if
	print "cmdStatus=(", PGSQL::PGcmdStatus(r), ")\n"
	print "cmdTuples=(", PGSQL::PGcmdTuples(r), ")\n"

	print "result fields:"
	nf=PGSQL::PGncols(r)  
	for i=0 to nf-1
	  print " ", PGSQL::PGcol(r,i)
	next
	print ".\n"

	nrows=PGSQL::PGnrows(r)
	for row=0 to nrows-1   
	  print "row ", row, ":"
	  for col=0 to nf-1
	    print " "
	    if PGSQL::PGgetisnull(r,row,col) then
	      print "-"
	    end if
	    print PGSQL::PGgetlength(r,row,col)
	    print "(", PGSQL::PGgetvalue(r,row,col), ")"
	  next
	  print ".\n"
	next
	PGclose(r)
=noverbatim

Finally, you should close the database connection. Example:

=verbatim
	PGclose(c)
=noverbatim

You can guarantee the atomicity of your operations using transactions. Just
issue the queries T<BEGIN>, T<COMMIT> or T<ROLLBACK> with T<PGexec()>.

=H User function overview

The following functions are provided by ScriptBasic T<PGSQL::> :

=itemize
=item  T<PGSQL::PGopen()>: open a new database connection; reconnect after
       broken connection (T<PGSQL::PGok()> returned T<false>)
=item  T<PGSQL::PGclose()>: close a connection or resultset
=item  T<PGSQL::PGconndefaults()>: get default connection properties
=item  T<PGSQL::PGconnget()>: get overall properties of an existing connection
=item  T<PGSQL::PGok()>: verify that a connection or resultset is still functional
=item  T<PGSQL::PGnotified()>: recieve asynchronous notification. See the SQL
       commands T<LISTEN>, T<UNLISTEN> and T<NOTIFY>.
=item  T<PGSQL::PGdumpNotices()>: enable or disable dumping notice (not
       notification!) messages to stderr
=item  T<PGSQL::PGexec()>: execute a query and return a resultset
=item  T<PGSQL::PGresultStatus()>: query the status of the query execution
=item  T<PGSQL::PGmakeEmptyPGresult()>: create and return an empty resultset
=item  T<PGSQL::PGoid()>: return the OID of the last SQL T<INSERT> operation
=item  T<PGSQL::PGescapeString()>: escape a non-binary string to be included into
       an SQL command
=item  T<PGSQL::PGescapeBytea()>: escape a bytea (binary 8-bit string) to be
       included to an SQL command
=item  T<PGSQL::PGnrows()>: get the number of rows of a resultset
=item  T<PGSQL::PGncols()>: get the number of rows of a resultset
=item  T<PGSQL::PGcol()>: get a column index by its name and vice versa
=item  T<PGSQL::PGcoltype()>: get a column's data type
=item  T<PGSQL::PGcolmod()>: get a column's data type modifier
=item  T<PGSQL::PGcolsize()>: get a column's storage size on the server
=item  T<PGSQL::PGgetvalue()>: get a cell value (always a string)
=item  T<PGSQL::PGgetlength()>: get a cell's length
=item  T<PGSQL::PGgetisnull()>: return whether a cell is null
=item  T<PGSQL::PGbinaryTuples()>: return whether cells are returned as binary
=item  T<PGSQL::PGcmdStatus()>: get the status message of the completed query
=item  T<PGSQL::PGcmdTuples()>: get the number of rows affected (not I<returned>)
       by a query
=noitemize

T<libpq> is a PostgreSQL client library that provides almost the same
functionality as ScriptBasic T<PGSQL::>, but for the C language. ScriptBasic T<PGSQL::> uses
T<libpq> to do all operations.

PostgreSQL doc says:

> libpq is thread-safe as of PostgreSQL 7.0, so long as no two threads attempt

> to manipulate the same PGconn object at the same time. In particular, you

> cannot issue concurrent queries from different threads through the same

> connection object. (If you need to run concurrent queries, start up multiple

> connections.)

ScriptBasic T<PGSQL::> is written to be thread-safe, but this hasn't been tested
yet. You don't have to worry about using the same connection object in
multiple threads, since it is inherently impossible due to the ScriptBasic
architecture.

=H Comparison to libpq

T<libpq> and ScriptBasic T<PGSQL::> provide essentially the same functionality with
very similar functions. T<libpq> function begin with T<PQ>, ScriptBasic T<PGSQL::>
functions begin with T<PGSQL::PG>.

The following T<libpq> functions are missing from ScriptBasic T<PGSQL::>, because they
are related to asynchronous, nonblocking operations: T<PQconnectStart()>,
T<PQconnectPoll()>, T<PQresetStart()>, T<PQsetnonblocking()>, T<PQisnonblocking()>,
T<PQsendQuery()>, T<PQgetResult()>, T<PQconsumeInput()>, T<PQisBusy()>, T<PQflush()>,
T<PQsocket()>, T<PQrequestCancel()>.

The following T<libpq> functions are obsolete/deprecated, thus they are not
supported in ScriptBasic T<PGSQL::> 
=itemize
=item T<PQsetdblogin()>: obsoleted by T<PQconnectdb()>
=item T<PQsetdb()>: obsoleted by T<PQconnectdb()>
=item T<PQoidStatus()>: obsoleted by T<PQoidValue()>
=noitemize

The SQL T<COPY> command not supported in ScriptBasic T<PGSQL::>, thus the following
T<libpq> functions have no ScriptBasic T<PGSQL::> counterpart: T<PQgetline()>,
T<PQgetlineAsync()>, T<PQputline()>, T<PQputnbytes()>, T<PQendcopy()>.

Tracing is not supported in ScriptBasic T<PGSQL::>, thus the following
libpq functions have no ScriptBasic T<PGSQL::> counterpart:
T<PQtrace()>, T<PQuntrace()>.

Large objects are not supported in ScriptBasic T<PGSQL::>, thus the following
T<libpq> functions have no ScriptBasic T<PGSQL::> counterpart: T<lo_creat()>, T<lo_import()>,
T<lo_export()>, T<lo_open()>, T<lo_write()>, T<lo_read()>, T<lo_lseek()>, T<lo_close()>,
T<lo_unlink()>.

The T<libpq> T<PQsetNoticeProcessor()> function has been simplified to
T<PGSQL::PGdumpNotices()>.

All other T<libpq> functions have equivalent counterpart in ScriptBasic T<PGSQL::>. See
the function reference for function-specific details.

For more information about libpq, please visit the URL:

=verbatim
	http://www.postgresql.org/users-lounge/docs/7.2/postgres/libpq-connect.html
=noverbatim

=H Function reference

Parameter and return value data types are significant in ScriptBasic T<PGSQL::>.
You should use the ScriptBasic T<isstring()> and T<isundef()> functions to
distinguish between different data types.

For more details, please read the documentation of the corresponding T<libpq>
function, available from the URL:

=verbatim
	http://www.postgresql.org/users-lounge/docs/7.2/postgres/libpq-connect.html
=noverbatim

The ScriptBasic T<PGSQL::> functions:

=item  PGconn|String PGopen(String conns):
   Open a new database connection.
   @libpq PQconnectdb(), PQreset(), PQstatus(), PQerrorMessage(),
     [PQfinish(), PQsetNoticeProcessor()]
   @return new connection handle PGconn or a String describing the error. The
     string usually contains one or more terminating newlines, so you should
     call rtrim() on it

=item  PGconn|String PGopen(PGconn old):
   Reconnect after broken connection (PGSQL::PGok() returned false).
   @libpq PQconnectdb(), PQreset(), PQstatus(), PQerrorMessage(),
     [PQfinish(), PQsetNoticeProcessor()]
   @return same connection handle `old'

=item  void PGclose(PGconn old):
   Close a connection.
   @libpq PQfinish()

=item  void PGclose(PGresult old):
   Close a resultset. 
   @libpq PQclear()

=item  void PGconndefaults(Array &ret):
   Get default connection properties.
   @libpq PQconndefaults(),  PQconninfofree()
   @return new array of assoc arrays. Assoc array keys: "keyword", "envvar",
     "compiled", "val", "label", "dispchar", "dispsize"

=item  String|int|bool PGconnget(PGconn conn, String key):
   Get overall properties of an existing connection.
   @libpq PQuser(), PQpass(), PQhost(), PQport(), PQtty(), PQoptions(),
   PQbackendPID(), PQgetssl()
   @param key one of "db" | "user" | "pass" | "host" | "port" | "tty" |
     "options" | "backendPID" | "SSLused"

=item  bool PGok(PGconn|PGresult conn):
   Verify that a connection or resultset is still alive and functional.
   @libpq PQstatus(), PQresultStatus()
   @return true iff CONNECTION_OK || PGRES_TUPLES_OK || PGRES_COMMAND_OK
           (0 for false, -1 for true)

=item  bool PGnotified(PGconn conn, String& relname, int& other_pid):
   Recieve asynchronous notification. See the SQL
   commands LISTEN, UNLISTEN and NOTIFY.
   @libpq PQconsumeInput(), PQnotifies()
   @return 0 if there are no pending notifications, undef if PQconsumeInput()
     failed; returns 1 otherwise

=item  void PGdumpNotices(PGconn conn, bool disp):
   Enable or disable dumping notice (not notification!) messages to stderr.
   @libpq PQsetNoticeProcessor()
   @param disp when true, libpq notice messages are printed to stderr.
     When false, no notices are printed
   @return undef

=item  PGresult|String PGexec(PGconn conn, String query [,String qarg[, String|int Query_cont [...]]] ):
   Execute a query and return a resultset.
   Ordering: query and every second string is unquoted; quoted strings are
   quoted with PQescapeString() unless they are preceded by undef (with
   the undef, PQescapeBytea() is appled). Quoted strings are surrounded with
   single quotes.
   @libpq PQexec(), PQescapeString(), PQescapeBytea(), PQresultStatus(),
     PQresStatus(), PQresultErrorMessage(), [PQclear()]
   @return new PGresult handle or error message String. The error message is
   the concatenation of a PGRES_* constant, a colon, a space, and a
   descriptive English error message.

=item  String PGresultStatus(PGresult res):
   Query the status of the query execution.
   @libpq PQresultStatus(), PQresStatus(), PQresultErrorMessage()
   @return "" if no error and PGRES_TUPLES_OK;
           "-" if no error and PGRES_COMMAND_OK;
           "PGRES_*: " and error message otherwise

=item  PGresult PGmakeEmptyPGresult(PGconn conn, String resultStatus):
   Create and return an empty resultset.
   @libpq PQmakeEmptyPGresult(), [PQclear()]
   @param conn may be undef
   @param resultStatus one of PGRES_EMPTY_QUERY PGRES_COMMAND_OK
          PGRES_TUPLES_OK PGRES_COPY_OUT PGRES_COPY_IN PGRES_BAD_RESPONSE
          PGRES_NONFATAL_ERROR PGRES_FATAL_ERROR

=item  undef|int PGoid(PGresult):
   Return the OID of the last SQL INSERT operation.
   @libpq PQoidValue()
   @return the object ID of the inserted row, if the SQL command was an
     INSERT that inserted exactly one row into a table that has OIDs.
     Otherwise, returns undef.

=item  String PGescapeString(String s):
   Escape a non-binary string to be included into
   an SQL command.
   Surrounding single quotes not included.
   @libpq PQescapeString()

 String PGescapeBytea(String s):
   Escape a bytea (binary 8-bit string) to be included to an SQL command.
   Surrounding single quotes not included.
   @libpq PQescapeBytea()

=item  int PGnrows(PGresult res):
   Get the number of rows of a resultset.
   An undocumented alias is available for this function: PGntuples()
   @libpq PQntuples()

=item  int PGncols(PGresult res):
   Get the number of rows of a resultset.
   An undocumented alias is available for this function: PGnfields()
   @libpq PQnfields()

=item  String|null PGcol(PGresult res, int idx):
   Convert a column index to a column name.
   @libpq PQfname()
   @param idx >=0

=item  int|undef PGcol(PGresult res, String colname):
   Convert a column name to a column index
   @libpq PQfnumber()
   @return int >=0 or undef

=item  int|undef PGcoltype(PGresult res, int idx):
   Get a column's data type.
   The user should query the system table pg_type.
   @libpq PQftype()
   @return int (Oid), or undef

=item  int|undef PGcoltype(PGresult res, String colname)
   Get a column's data type.
   The user should query the system table pg_type.
   @libpq PQftype(), PQfnumber()
   @return int (Oid), or undef

=item  int|undef PGcolmod(PGresult res, int idx):
   Get a column's data type modifier.
   The user should query the system table pg_mod.
   @libpq PQfmod()
   @return int (Oid), or undef

=item  int|undef PGcolmod(PGresult res, String colname)
   Get a column's data type modifier.
   The user should query the system table pg_mod.
   @libpq PQfmod(), PQfnumber()
   @return int (Oid), or undef

=item  int|undef PGcolsize(PGresult res, int idx):
   Get a column's storage size on the server.
   The user should query the system table pg_size.
   @libpq PQfsize()
   @return int (Oid), or undef

=item  int|undef PGcolsize(PGresult res, String colname):
   Get a column's storage size on the server.
   The user should query the system table pg_size.
   @libpq PQfsize(), PQfnumber()
   @return int (Oid), or undef

=item  String|undef PGgetvalue(PGresult res, int tup_num, int idx):
   Get a cell value (always a string).
   @libpq PQgetvalue()
   @return undef when SQL NULL or invalid column index 

=item  String|undef PGgetvalue(PGresult res, int tup_num, String colname):
   Get a cell value (always a string).
   @libpq PQgetvalue(), PQfnumber()
   @return undef when SQL NULL or invalid column name

=item  int|undef PGgetlength(PGresult res, int tup_num, int idx):
   Get a cell's length.
   @libpq PQgetlength()
   @return int >=0 or undef if invalid column index

=item  int|undef PGgetlength(PGresult res, int tup_num, String colname):
   Get a cell's length.
   @libpq PQgetlength(), PQfnumber()
   @return int >=0 or undef if invalid column name

=item  bool|undef PGgetisnull(PGresult res, int tup_num, int idx):
   Return whether a cell is null.
   @libpq PQgetisnull()
   @return int (bool) or undef if invalid column index

=item  bool|undef PGgetisnull(PGresult res, int tup_num, String colname):
   Return whether a cell is null.
   @libpq PQgetisnull(), PQfnumber()
   @return int (Oid), or undef if invalid column name 

=item  bool PGbinaryTuples(PGresult):
   Return whether cells are returned as binary.
   @libpq PQbinaryTuples()

=item  String PGcmdStatus(PGresult res):
   Get the status message of the completed query.
   @libpq PGcmdStatus()
   @return String: the command status (such as `CREATE' or `UPDATE 1')

=item  int PGcmdTuples(PGresult res):
   Get the number of rows affected (not _returned_) by a query.
   @libpq PGcmdTuples()
   @return int: the number of rows affected for INSERT, UPDATE, DELETE etc.



*/

typedef enum _TY {
  CON=1,
  RES=2,
} TY;

struct _Wrapper;
typedef struct _Wrapper {
  union {
    PGconn   *con;
    PGresult *res;
  } u;
  struct _Wrapper *next, *prev; /* non-circular double-linked list */
  unsigned long handle; /* stupid scriba interface cannot return it */
  TY ty; /* CON or RES */
} Wrapper, *pWrapper;

typedef struct _ModuleGlobal {
  void *ha; /* HandleArray for PGconn and PGresult */
  Wrapper *first;
  VARIABLE s_keyword, s_envvar, s_compiled, s_val, s_label, s_dispchar, s_dispsize;
} ModuleGlobal, *pModuleGlobal;

/* Left value argument is needed for the command */
#define PGSQL_ERROR_CON_EXPECTED  0x00081001
#define PGSQL_ERROR_RES_EXPECTED  0x00081002
#define PGSQL_ERROR_HAN_EXPECTED  0x00081003
#define PGSQL_ERROR_CONNGET_KEY   0x00081004
#define PGSQL_ERROR_LVAL_EXPECTED 0x00081005
#define PGSQL_ERROR_EST_EXPECTED  0x00081006

#define copystr(dst,src) (\
  ((dst)=besNEWSTRING(STRLEN(src))) && \
  (memcpy(STRINGVALUE(dst),STRINGVALUE(src),STRLEN(src)), 1) \
)
#define copystrz(dst,src) (tmplen=strlen(src), \
  ((dst)=besNEWSTRING(tmplen)) && \
  (memcpy(STRINGVALUE(dst),src,tmplen), 1) \
)
/** like copystrz, but converts NULL -> undef */
#define copystry(dst,src) (src ? (tmplen=strlen(src), \
  ((dst)=besNEWSTRING(tmplen)) && \
  (memcpy(STRINGVALUE(dst),src,tmplen), 1) \
) : ((dst)=NULL, 1) )
#define copylong(dst,src) (\
  ((dst)=besNEWLONG) && \
  (LONGVALUE(dst)=src, 1) \
)
#define streq(var,strz) \
  (STRLEN(var)==strlen(strz) && 0==memcmp(STRINGVALUE(var),strz,STRLEN(var)))
#define strbegins(var,strz) \
  (STRLEN(var)>=strlen(strz) && 0==memcmp(STRINGVALUE(var),strz,strlen(strz)))

static void stderrNoticeProcessor(void * arg, const char * message) {
  (void)arg;
  /* Dat: message already contains \n */
  fprintf(stderr, "PGSQL: %s", message);
}
static void silentNoticeProcessor(void * arg, const char * message) {
  (void)arg;
  (void)message;
}

besSUB_ERRMSG
  (void)pSt; (void)ppModuleInternal;
  /* assert(0); */
  DEBUGMSG(fprintf(stderr, PTS_MODULE": iError=%lx.\n", (long)iError););
  switch (iError) {
   case PGSQL_ERROR_CON_EXPECTED: return "PGSQL error: PGSQL Connection handle expected";
   case PGSQL_ERROR_RES_EXPECTED: return "PGSQL error: PGSQL ResultSet handle expected";
   case PGSQL_ERROR_HAN_EXPECTED: return "PGSQL error: PGSQL handle expected";
   case PGSQL_ERROR_CONNGET_KEY:  return "PGSQL error: invalid key specified for PGconnget()";
   case PGSQL_ERROR_LVAL_EXPECTED:return "PGSQL error: left value expected";
   case PGSQL_ERROR_EST_EXPECTED: return "PGSQL error: ExecStatusType expected";
  }
  return NULL;
  /* return "ERRMSG from "PTS_MODULE; */
besEND

besVERSION_NEGOTIATE
  (void)Version; (void)pszVariation; (void)ppModuleInternal;
  return (int)INTERFACE_VERSION;
besEND

besDLL_MAIN

SUPPORT_MULTITHREAD

besSUB_PROCESS_START
  INIT_MULTITHREAD
  (void)lThreadCounter;
  DEBUGMSG(fprintf(stderr, PTS_MODULE ": Process Hi!\n"););
  return 1;
besEND

besSUB_PROCESS_FINISH
  DEBUGMSG(fprintf(stderr, PTS_MODULE ": Process Bye!\n"););
besEND

/* Imp: ?? */
besSUB_KEEP
  return 0;
besEND

static MUTEX mutex;

besSUB_START
  unsigned long tmplen;
  ModuleGlobal *p;
  (void)pEo; (void)pParameters; (void)pReturnValue;
  INITLOCK /* Imp: ez kell?? */
  besInitMutex(&mutex);
  INITUNLO
  if (NULL==(besMODULEPOINTER=besALLOC(sizeof*p))) return COMMAND_ERROR_MEMORY_LOW;
  p=(ModuleGlobal*)besMODULEPOINTER;
  p->ha=NULL;
  p->first=NULL;
  if (0
   || !copystrz(p->s_keyword , "keyword")
   || !copystrz(p->s_envvar  , "envvar")
   || !copystrz(p->s_compiled, "compiled")
   || !copystrz(p->s_val     , "val")
   || !copystrz(p->s_label   , "label")
   || !copystrz(p->s_dispchar, "dispchar")
   || !copystrz(p->s_dispsize, "dispsize")
     ) return COMMAND_ERROR_MEMORY_LOW;
  DEBUGMSG(fprintf(stderr, PTS_MODULE ": Hi %p!\n", p););
besEND

besSUB_FINISH
  ModuleGlobal *p;
  Wrapper *q;
  (void)pEo; (void)pParameters; (void)pReturnValue;
  DEBUGMSG(fprintf(stderr, PTS_MODULE ": Bye!\n"););
  if (NULL!=(p=(ModuleGlobal*)besMODULEPOINTER)) {
    /* Dat: PGconn and PGresult are independent */
    for (q = p->first ; q ; q = q->next) {
      DEBUGMSG(fprintf(stderr, PTS_MODULE ": Finish %p!\n", q););
      switch (q->ty) {
       case CON: PQfinish(q->u.con); break;
       case RES: PQclear (q->u.res); break;
       default:  assert(0);
      }
    }
    besHandleDestroyHandleArray(p->ha);
  }
  #if 0 /* No need to free these because scriba frees them when the memory segment is freed */
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_keyword);
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_envvar);
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_compiled);
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_val);
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_label);
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_dispchar);
    pSt->ReleaseVariable(pSt->pEo->pMo, p->s_dispsize);
  #endif
  return 0;                                                                     
besEND

/** May return NULL */
static Wrapper *alloc_Wrapper(pSupportTable pSt, ModuleGlobal *mg/*, unsigned long *handle_ret*/) {
  Wrapper *w=besALLOC(sizeof*w);
  if (w!=NULL) {
    DEBUGMSG(fprintf(stderr, PTS_MODULE ": Alloc %p -> %p!\n", mg, w););
    besLockMutex(&mutex);
    if (mg->first!=NULL) mg->first->prev=w;
    w->next=mg->first;
    mg->first=w;
    w->prev=NULL;
    /*if (NULL!=handle_ret) *handle_ret=*/w->handle=besHandleGetHandle(mg->ha, w);
    besUnlockMutex(&mutex);
  }
  return w;
}

/** Also calls PGfinish() or PGclear() */
static void delete_Wrapper(pSupportTable pSt, ModuleGlobal *mg, Wrapper *w) {
  if (w!=NULL) {
    switch (w->ty) {
     case CON: PQfinish(w->u.con); break;
     case RES: PQclear (w->u.res); break;
     default:  assert(0);
    }
    besLockMutex(&mutex);
    if (w->prev!=NULL) w->prev->next=w->next;
                  else mg->first=w->next;
    if (w->next!=NULL) w->next->prev=w->prev;
    assert((w->prev=w->next=NULL, 1)); /* play it safe */
    besHandleFreeHandle(mg->ha, w->handle);
    besUnlockMutex(&mutex);
    DEBUGMSG(fprintf(stderr, PTS_MODULE ": Free %p!\n", w););
    besFREE(w);
  } 
}

besFUNCTION(PGopen)
  Wrapper *w=NULL;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)==VTYPE_LONG) { /* PQreset() */
    if (NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
     || w->ty!=CON
       ) return PGSQL_ERROR_CON_EXPECTED;
    if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
    PQreset(w->u.con);
  } else if (TYPE(Argument)==VTYPE_STRING) { /* PQconnectdb() */
    char *specs;
    PGconn *con;
    if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
    besCONVERT2ZCHAR(Argument,specs); /* Dat: no automatic sentinel in scriba :-( */
    con=PQconnectdb(specs);
    /* assert(con!=NULL);: almost always true */
    besFREE(specs);
    if (!con || PQstatus(con)!=CONNECTION_OK) {
      /* printf("conn err=(%s)\n", PQerrorMessage(con)); */
      specs=con ? PQerrorMessage(con) : "PQconnectdb() returned NULL";
      besALLOC_RETURN_STRING(strlen(specs));
      memcpy(STRINGVALUE(besRETURNVALUE),specs,strlen(specs));
      if (con) PQfinish(con); /* free mem ?? */
      return 0;
      /* return COMMAND_ERROR_CHDIR; */
    }
    if (NULL==(w=alloc_Wrapper(pSt, (ModuleGlobal*)besMODULEPOINTER))) {
      PQfinish(con);
      return COMMAND_ERROR_MEMORY_LOW;
    }
    w->ty=CON; w->u.con=con;
    PQsetNoticeProcessor(con, stderrNoticeProcessor, con);
  }
  besALLOC_RETURN_LONG;
  LONGVALUE(besRETURNVALUE)=(long)w->handle;
  /*return NULL;*/ /*notreached*/
besEND

besFUNCTION(PGclose)
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  /* Dat: automatic type conversion is disabled deliberately (author's taste) */
  if (TYPE(Argument)!=VTYPE_LONG || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument))))
    return PGSQL_ERROR_HAN_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  delete_Wrapper(pSt, (ModuleGlobal*)besMODULEPOINTER, w);
  /* besALLOC_RETURN_LONG;  LONGVALUE(besRETURNVALUE)=0; */
  besRETURNVALUE=NULL; /* return undef */
besEND

static int isSSL(PGconn const*c) {
#ifdef USE_SSL
  return (SSL*)0!=PQgetssl(c);
#else
  (void)c;
  return 0;
#endif
}

besFUNCTION(PGconnget)
  char *s=NULL;
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=CON
     ) return PGSQL_ERROR_CON_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besCONVERT2STRING(Argument2);
  if (STRLEN(Argument2)==2 && 0==memcmp("db", STRINGVALUE(Argument2), 2)) s=PQdb(w->u.con);
  else if (STRLEN(Argument2)==4 && 0==memcmp("user", STRINGVALUE(Argument2), 4)) s=PQuser(w->u.con);
  else if (STRLEN(Argument2)==4 && 0==memcmp("pass", STRINGVALUE(Argument2), 4)) s=PQpass(w->u.con);
  else if (STRLEN(Argument2)==4 && 0==memcmp("host", STRINGVALUE(Argument2), 4)) s=PQhost(w->u.con);
  else if (STRLEN(Argument2)==4 && 0==memcmp("port", STRINGVALUE(Argument2), 4)) s=PQport(w->u.con);
  else if (STRLEN(Argument2)==3 && 0==memcmp("tty", STRINGVALUE(Argument2), 3)) s=PQtty(w->u.con);
  else if (STRLEN(Argument2)==7 && 0==memcmp("options", STRINGVALUE(Argument2), 7)) s=PQoptions(w->u.con);
  else {
    int pid;
    if (STRLEN(Argument2)==7 && 0==memcmp("SSLused", STRINGVALUE(Argument2), 7)) pid=-isSSL(w->u.con);
    else if (STRLEN(Argument2)==10 && 0==memcmp("backendPID", STRINGVALUE(Argument2), 10)) pid=PQbackendPID(w->u.con);
    else return PGSQL_ERROR_CONNGET_KEY/*COMMAND_ERROR_ARGUMENT_RANGE*/;
    besALLOC_RETURN_LONG;
    LONGVALUE(besRETURNVALUE)=(long)pid;
    return 0;
  }
  besALLOC_RETURN_STRING(strlen(s));
  memcpy(STRINGVALUE(besRETURNVALUE), s, STRLEN(besRETURNVALUE));
besEND

besFUNCTION(PGok)
  /* char *s=NULL; */
  ExecStatusType est;
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || (w->ty!=CON && w->ty!=RES)
     ) return PGSQL_ERROR_HAN_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besALLOC_RETURN_LONG;
  LONGVALUE(besRETURNVALUE)=-(long)(
    w->ty==CON ? CONNECTION_OK==PQstatus(w->u.con)
               : PGRES_COMMAND_OK==(est=PQresultStatus(w->u.res)) || PGRES_TUPLES_OK==est
  );
besEND

/** argument 1 is result arrayref */
besFUNCTION(PGconndefaults)
  unsigned long tmplen;
  PQconninfoOption *cio, *p, *cioend;
  LEFTVALUE Lval;
  VARIABLE Argument=besARGUMENT(1), v;
  long __refcount_; /* used by besLEFTVALUE */
  (void)pEo;
  (void)ppModuleInternal;
  /* besDEREFERENCE(Argument); */
  /* fprintf(stderr, "%d\n", besARGNR); */
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  /* assert(0); */
  besLEFTVALUE(Argument,Lval);
  if (!Lval) return PGSQL_ERROR_LVAL_EXPECTED;
  besRELEASE(*Lval); *Lval=NULL;

  cio=PQconndefaults();
  cioend=cio; while (cioend->keyword!=NULL) cioend++;
  if (!(*Lval=besNEWARRAY(0,cioend-cio-1))) { /* min idx, max idx */
   outmem:
    PQconninfoFree(cio);
    return COMMAND_ERROR_MEMORY_LOW;
  }
  for (p=cio; p!=cioend-3; p++) {
    /* Imp: free temp alloced memory, even when out of memory */
    /* vvv Imp: really should make refs to ->s_* */
    if (!(v=ARRAYVALUE(*Lval,p-cio)=besNEWARRAY(0,13))
     || !copystr (ARRAYVALUE(v, 0), ((ModuleGlobal*)besMODULEPOINTER)->s_keyword)
     || !copystrz(ARRAYVALUE(v, 1), p->keyword)
     || !copystr (ARRAYVALUE(v, 2), ((ModuleGlobal*)besMODULEPOINTER)->s_envvar)
     || !copystrz(ARRAYVALUE(v, 3), p->envvar)
     || !copystr (ARRAYVALUE(v, 4), ((ModuleGlobal*)besMODULEPOINTER)->s_compiled)
     || !copystry(ARRAYVALUE(v, 5), p->compiled)
     || !copystr (ARRAYVALUE(v, 6), ((ModuleGlobal*)besMODULEPOINTER)->s_val)
     || !copystry(ARRAYVALUE(v, 7), p->val)
     || !copystr (ARRAYVALUE(v, 8), ((ModuleGlobal*)besMODULEPOINTER)->s_label)
     || !copystrz(ARRAYVALUE(v, 9), p->label)
     || !copystr (ARRAYVALUE(v,10), ((ModuleGlobal*)besMODULEPOINTER)->s_dispchar)
     || !copystrz(ARRAYVALUE(v,11), p->dispchar)
     || !copystr (ARRAYVALUE(v,12), ((ModuleGlobal*)besMODULEPOINTER)->s_dispsize)
     || !copylong(ARRAYVALUE(v,13), p->dispsize)
       ) goto outmem;
  }
  PQconninfoFree(cio);
  besRETURNVALUE=NULL; /* return undef */
besEND

besFUNCTION(PGnotified)
  long __refcount_; /* used by besLEFTVALUE */
  unsigned len;
  Wrapper *w;
  LEFTVALUE Lval;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  VARIABLE Argument3=besARGUMENT(3);
  PGnotify *no;
  (void)pEo;
  besDEREFERENCE(Argument);
  /* besDEREFERENCE(Argument2); */
  /* besDEREFERENCE(Argument3); */
  if (besARGNR>3) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument3) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=CON
     ) return PGSQL_ERROR_CON_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;

  if (0==PQconsumeInput(w->u.con)) {
    besRETURNVALUE=NULL; /* return undef */
    return 0;
  }
  if (!(no=PQnotifies(w->u.con))) {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)0;
    return 0;
    besRETURNVALUE=NULL; /* return undef */
  }
  
  besLEFTVALUE(Argument2,Lval);
  len=0; while (len<NAMEDATALEN && no->relname[len]!='\0') len++;
  *Lval=besNEWSTRING(len);
  memcpy(STRINGVALUE(*Lval), no->relname, len);

  besLEFTVALUE(Argument3,Lval);
  *Lval=besNEWLONG;
  LONGVALUE(*Lval)=no->be_pid;
  
  free(no);
  besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)-1;
besEND


besFUNCTION(PGdumpNotices)
  /* char *s=NULL; */
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=CON
     ) return PGSQL_ERROR_CON_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  PQsetNoticeProcessor(w->u.con, besGETLONGVALUE(Argument2) ? stderrNoticeProcessor : silentNoticeProcessor, w->u.con);
  besRETURNVALUE=NULL; /* return undef */
besEND


besFUNCTION(PGexec)
  /* char *s=NULL; */
  ExecStatusType est;
  unsigned long sumlen;
  size_t tmp;
  unsigned i, argi;
  char **subs, *query, *dst, *s;
  Wrapper *w;
  PGresult *res;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  /* if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS; */
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=CON
     ) return PGSQL_ERROR_CON_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (!(subs=besALLOC(besARGNR*sizeof*subs))) return COMMAND_ERROR_MEMORY_LOW;
  /* ^^^ Dat: we allocate a little more */
  for (sumlen=1, i=0, argi=2; argi<=(unsigned)besARGNR; argi++) {
    if ((i&1)==0) { /* unquoted */
      Argument=besCONVERT2STRING(besARGUMENT((int)argi));
      sumlen+=STRLEN(Argument);
      i++;
    } else if (besARGUMENT((int)argi)==NULL/*undef*/ && (int)argi<besARGNR) {
      argi++;
      Argument=besCONVERT2STRING(besARGUMENT((int)argi));
      tmp=STRLEN(Argument);
      if (!(subs[i]=PQescapeBytea(STRINGVALUE(Argument), tmp, &tmp))) return COMMAND_ERROR_MEMORY_LOW;
      /* Dat: SUXX: PQescapeBytea in PostgreSQL 7.2 never returns NULL, but it crashes the process */
      sumlen+=2+strlen(subs[i++]);
    } else {
      Argument=besCONVERT2STRING(besARGUMENT((int)argi));
      if (!(subs[i]=besALLOC(STRLEN(Argument)*2+1))) return COMMAND_ERROR_MEMORY_LOW;
      PQescapeString(subs[i], STRINGVALUE(Argument), STRLEN(Argument));
      sumlen+=2+strlen(subs[i++]);
    }
  }
  /* Dat: this function is binary safe, because PQescape* always return a
   *      null-terminated string
   */
  if (!(query=besALLOC(sumlen))) return COMMAND_ERROR_MEMORY_LOW;
  for (dst=query, i=0, argi=2; (int)argi<=besARGNR; argi++) {
    /* fprintf(stderr, "NOW %u\n", i); */
    if ((i&1)==0) { /* unquoted */
      Argument=besCONVERT2STRING(besARGUMENT((int)argi));
      memcpy(dst, STRINGVALUE(Argument), STRLEN(Argument));
      /* Imp: change \0s ?! */
      dst+=STRLEN(Argument);
      i++;
    } else {
      sumlen=strlen(subs[i]);
      *dst++='\'';
      memcpy(dst, subs[i], sumlen);
      dst+=sumlen;
      *dst++='\'';
      if (besARGUMENT((int)argi)==NULL/*undef*/ && (int)argi<besARGNR) {
        argi++;
        free(subs[i]);
      } else besFREE(subs[i]);
      i++; /* Dat: unsafe inside subs[i] */
    }
  }
  *dst='\0';
  DEBUGMSG(fprintf(stderr, "qry=(%s)\n", query);)
  besFREE(subs);
  res=PQexec(w->u.con, query);
  besFREE(query);
  if (!res) { besRETURNVALUE=NULL; return 0; /* return undef */ }
  /* ^^^ Dat: only on fatal error conditions such as out of memory */
  est=!res ? PGRES_FATAL_ERROR : PQresultStatus(res);
  if (est==PGRES_COMMAND_OK || est==PGRES_TUPLES_OK) {
    if (NULL==(w=alloc_Wrapper(pSt, (ModuleGlobal*)besMODULEPOINTER))) {
      PQclear(res);
      return COMMAND_ERROR_MEMORY_LOW;
    }
    w->ty=RES; w->u.res=res;
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)w->handle;
    return 0;
  }
  s=PQresStatus(est); /* "PGRES_..." */
  dst=res ? PQresultErrorMessage(res) : "PQexec() returned NULL";
  besALLOC_RETURN_STRING(strlen(s)+2+strlen(dst));
  memcpy(STRINGVALUE(besRETURNVALUE), s, strlen(s));
  memcpy(STRINGVALUE(besRETURNVALUE)+strlen(s), ": ", 2);
  memcpy(STRINGVALUE(besRETURNVALUE)+strlen(s)+2, dst, strlen(dst));
  if (res) PQclear(res);
besEND

besFUNCTION(PGresultStatus)
  /* char *s=NULL; */
  ExecStatusType est;
  Wrapper *w;
  char *s, *dst;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;

  assert(NULL!=w->u.res);

  est=!w->u.res ? PGRES_FATAL_ERROR : PQresultStatus(w->u.res);
  if (est==PGRES_COMMAND_OK) {
    besALLOC_RETURN_STRING(1);
    STRINGVALUE(besRETURNVALUE)[0]='-';
  } else if (est==PGRES_TUPLES_OK) {
    besALLOC_RETURN_STRING(0);
  } else {
    s=PQresStatus(est); /* "PGRES_..." */
    dst=w->u.res ? PQresultErrorMessage(w->u.res) : "PQexec() returned NULL";
    besALLOC_RETURN_STRING(strlen(s)+2+strlen(dst));
    memcpy(STRINGVALUE(besRETURNVALUE), s, strlen(s));
    memcpy(STRINGVALUE(besRETURNVALUE)+strlen(s), ": ", 2);
    memcpy(STRINGVALUE(besRETURNVALUE)+strlen(s)+2, dst, strlen(dst));
  }
besEND

besFUNCTION(PGmakeEmptyPGresult)
  ExecStatusType est;
  Wrapper *w;
  PGconn *con=NULL;
  PGresult *res;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (Argument!=NULL) {
    if (TYPE(Argument)!=VTYPE_LONG
     || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
     || w->ty!=CON
       ) return PGSQL_ERROR_CON_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
    con=w->u.con;    
  }
  besCONVERT2STRING(Argument2);

  if (strbegins(Argument2,"PGRES_EMPTY_QUERY")) est=PGRES_EMPTY_QUERY;
  else if (strbegins(Argument2,"PGRES_COMMAND_OK")) est=PGRES_COMMAND_OK;
  else if (strbegins(Argument2,"PGRES_TUPLES_OK")) est=PGRES_TUPLES_OK;
  else if (strbegins(Argument2,"PGRES_COPY_OUT")) est=PGRES_COPY_OUT;
  else if (strbegins(Argument2,"PGRES_COPY_IN")) est=PGRES_COPY_IN;
  else if (strbegins(Argument2,"PGRES_BAD_RESPONSE")) est=PGRES_BAD_RESPONSE;
  else if (strbegins(Argument2,"PGRES_NONFATAL_ERROR")) est=PGRES_NONFATAL_ERROR;
  else if (strbegins(Argument2,"PGRES_FATAL_ERROR")) est=PGRES_FATAL_ERROR;
  else return PGSQL_ERROR_EST_EXPECTED;

  if (!(res=PQmakeEmptyPGresult(con, est))) return COMMAND_ERROR_MEMORY_LOW;
  if (NULL==(w=alloc_Wrapper(pSt, (ModuleGlobal*)besMODULEPOINTER))) {
    PQclear(res);
    return COMMAND_ERROR_MEMORY_LOW;
  }
  w->ty=RES; w->u.res=res;
  besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)w->handle;
besEND

besFUNCTION(PGoid)
  /* char *s=NULL; */
  Oid oid;
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (InvalidOid==(oid=PQoidValue(w->u.res))) {
    besRETURNVALUE=NULL;
  } else {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)oid;
  }
besEND

besFUNCTION(PGescapeString)
  char *s;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  (void)ppModuleInternal;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  besCONVERT2STRING(Argument);
  s=besALLOC(2*STRLEN(Argument)+1);
  PQescapeString(s, STRINGVALUE(Argument), STRLEN(Argument));
  besALLOC_RETURN_STRING(strlen(s));
  memcpy(STRINGVALUE(besRETURNVALUE), s, STRLEN(besRETURNVALUE));
  besFREE(s);
besEND

besFUNCTION(PGescapeBytea)
  char *s;
  size_t tmp;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  (void)ppModuleInternal;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  besCONVERT2STRING(Argument);
  tmp=STRLEN(Argument);
  if (!(s=PQescapeBytea(STRINGVALUE(Argument), tmp, &tmp))) return COMMAND_ERROR_MEMORY_LOW;
  /* ^^^ Dat: SUXX: PQescapeBytea in PostgreSQL 7.2 never returns NULL, but it crashes the process */
  tmp=strlen(s);
  besALLOC_RETURN_STRING(tmp);
  memcpy(STRINGVALUE(besRETURNVALUE), s, STRLEN(besRETURNVALUE));
  free(s);
besEND

besFUNCTION(PGnrows)
  /* char *s=NULL; */
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besALLOC_RETURN_LONG;
  LONGVALUE(besRETURNVALUE)=PQntuples(w->u.res);
besEND

besFUNCTION(PGncols)
  /* char *s=NULL; */
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besALLOC_RETURN_LONG;
  LONGVALUE(besRETURNVALUE)=PQnfields(w->u.res);
besEND

besFUNCTION(PGcol)
  Wrapper *w;
  char *s;
  size_t tmp;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument2)==VTYPE_LONG) {
    if (NULL==(s=PQfname(w->u.res, LONGVALUE(Argument2)))) {
      besRETURNVALUE=NULL;
    } else {
      tmp=strlen(s);
      besALLOC_RETURN_STRING(tmp);
      memcpy(STRINGVALUE(besRETURNVALUE), s, STRLEN(besRETURNVALUE));
    }
  } else {
    int idx;
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    idx=PQfnumber(w->u.res, s);
    besFREE(s);
    if (idx<0) {
      besRETURNVALUE=NULL;
    } else {
      besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)idx;
    }
  }
besEND

besFUNCTION(PGcoltype)
  Oid oid;
  Wrapper *w;
  int idx=-1;
  char *s=NULL;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument2)!=VTYPE_LONG) {
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    idx=PQfnumber(w->u.res, s);
    if (idx<0) { besFREE(s); besRETURNVALUE=NULL; return 0; }
  } else idx=LONGVALUE(Argument2);
  if (InvalidOid==(oid=PQftype(w->u.res, idx))) {
    besRETURNVALUE=NULL; /* should really not hapen */
  } else {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)oid;
  }
  if (s) besFREE(s);
besEND

besFUNCTION(PGcolmod)
  Oid oid;
  Wrapper *w;
  int idx=-1;
  char *s=NULL;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument2)!=VTYPE_LONG) {
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    idx=PQfnumber(w->u.res, s);
    if (idx<0) { besFREE(s); besRETURNVALUE=NULL; return 0; }
  } else idx=LONGVALUE(Argument2);
  if (InvalidOid==(oid=PQfmod(w->u.res, idx))) {
    besRETURNVALUE=NULL; /* should really not hapen */
  } else {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)oid;
  }
  if (s) besFREE(s);
besEND

besFUNCTION(PGcolsize)
  Oid oid;
  Wrapper *w;
  int idx=-1;
  char *s=NULL;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  if (besARGNR>2) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument2) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument2)!=VTYPE_LONG) {
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    DEBUGMSG(fprintf(stderr, "colname=(%s)\n", s);)
    idx=PQfnumber(w->u.res, s);
    if (idx<0) { besFREE(s); besRETURNVALUE=NULL; return 0; }
    DEBUGMSG(fprintf(stderr, "colidx=(%d)\n", idx);)
  } else idx=LONGVALUE(Argument2);
  if (InvalidOid==(oid=PQfsize(w->u.res, idx))) {
    besRETURNVALUE=NULL; /* should really not hapen */
  } else {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=(long)oid;
  }
  if (s) besFREE(s);
besEND

besFUNCTION(PGgetvalue)
  Wrapper *w;
  int idx=-1;
  size_t tmp;
  char *s=NULL, *v;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  VARIABLE Argument3=besARGUMENT(3);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  besDEREFERENCE(Argument3);
  if (besARGNR>3) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument3) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument3)!=VTYPE_LONG) {
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    idx=PQfnumber(w->u.res, s);
    if (idx<0) { besFREE(s); besRETURNVALUE=NULL; return 0; }
  } else idx=LONGVALUE(Argument3);
  besCONVERT2LONG(Argument2);
  /* Dat: memory returned in v is managed by libpq */
  if (!(v=PQgetvalue(w->u.res, LONGVALUE(Argument2), idx))
    || PQgetisnull(w->u.res, LONGVALUE(Argument2), idx)>0) {
    besRETURNVALUE=NULL; /* invalid field index? */
  } else {
    tmp=strlen(v);
    besALLOC_RETURN_STRING(tmp);
    memcpy(STRINGVALUE(besRETURNVALUE), v, STRLEN(besRETURNVALUE));
  }
  if (s) besFREE(s);
besEND

besFUNCTION(PGgetlength)
  Wrapper *w;
  int idx=-1;
  long len;
  char *s=NULL;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  VARIABLE Argument3=besARGUMENT(3);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  besDEREFERENCE(Argument3);
  if (besARGNR>3) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument3) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument3)!=VTYPE_LONG) {
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    idx=PQfnumber(w->u.res, s);
    if (idx<0) { besFREE(s); besRETURNVALUE=NULL; return 0; }
  } else idx=LONGVALUE(Argument3);
  besCONVERT2LONG(Argument2);
  if (0>(len=PQgetlength(w->u.res, LONGVALUE(Argument2), idx))) {
    besRETURNVALUE=NULL; /* should really not hapen */
  } else {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=len;
  }
  if (s) besFREE(s);
besEND

besFUNCTION(PGgetisnull)
  Wrapper *w;
  int idx=-1;
  long len;
  char *s=NULL;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  VARIABLE Argument2=besARGUMENT(2);
  VARIABLE Argument3=besARGUMENT(3);
  (void)pEo;
  besDEREFERENCE(Argument);
  besDEREFERENCE(Argument2);
  besDEREFERENCE(Argument3);
  if (besARGNR>3) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument3) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  if (TYPE(Argument3)!=VTYPE_LONG) {
    besCONVERT2ZCHAR(besCONVERT2STRING(Argument2),s); /* Dat: no automatic sentinel in scriba :-( */
    idx=PQfnumber(w->u.res, s);
    if (idx<0) { besFREE(s); besRETURNVALUE=NULL; return 0; }
  } else idx=LONGVALUE(Argument3);
  besCONVERT2LONG(Argument2);
  if (0>(len=PQgetisnull(w->u.res, LONGVALUE(Argument2), idx))) {
    besRETURNVALUE=NULL; /* should really not hapen */
  } else {
    besALLOC_RETURN_LONG; LONGVALUE(besRETURNVALUE)=len;
  }
  if (s) besFREE(s);
besEND

besFUNCTION(PGbinaryTuples)
  /* char *s=NULL; */
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besALLOC_RETURN_LONG;
  LONGVALUE(besRETURNVALUE)=PQbinaryTuples(w->u.res) ? -1 : 0;
besEND

besFUNCTION(PGcmdStatus)
  char *s;
  size_t tmp;
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besALLOC_RETURN_LONG;
  if (!(s=PQcmdStatus(w->u.res))) { besRETURNVALUE=NULL; return 0; }
  tmp=strlen(s);
  besALLOC_RETURN_STRING(tmp);
  memcpy(STRINGVALUE(besRETURNVALUE), s, STRLEN(besRETURNVALUE));
besEND

besFUNCTION(PGcmdTuples)
  char *s;
  size_t tmp;
  Wrapper *w;
  VARIABLE Argument=besARGUMENT(1); /* 0! */
  (void)pEo;
  besDEREFERENCE(Argument);
  if (besARGNR>1) return EX_ERROR_TOO_MANY_ARGUMENTS;
  if (NULL==Argument) return EX_ERROR_TOO_FEW_ARGUMENTS;
  if (TYPE(Argument)!=VTYPE_LONG
   || NULL==(w=besHandleGetPointer(((ModuleGlobal*)besMODULEPOINTER)->ha, besGETLONGVALUE(Argument)))
   || w->ty!=RES
     ) return PGSQL_ERROR_RES_EXPECTED/*COMMAND_ERROR_ARGUMENT_RANGE*/;
  besALLOC_RETURN_LONG;
  if (!(s=PQcmdTuples(w->u.res))) { besRETURNVALUE=NULL; return 0; }
  tmp=strlen(s);
  besALLOC_RETURN_STRING(tmp);
  memcpy(STRINGVALUE(besRETURNVALUE), s, STRLEN(besRETURNVALUE));
  besRETURNVALUE=besCONVERT2LONG(besRETURNVALUE);
besEND


/* __END__ */
