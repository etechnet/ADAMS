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

#include <userinterface.h>
#include <applogger.h>
#include <unistd.h>
#include <pwd.h>



UsersInterface::UsersInterface() : QObject(), loadUsr ( 0 ), uniq ( 0 )
{
	Users = new QMultiHash<QString, User *>;
	UsersNumber = 0;
}

UsersInterface::~UsersInterface()
{
	if ( Users ) {
		clear();
		delete Users;
	}
}

/**
  * Inserisce un nuov utente nellla hashtable
  * ed incrementa il numero di utenti.
**/
bool UsersInterface::add ( User * inusr )
{
	Users->insert ( inusr->data.login, inusr );
	UsersNumber++;
	return false;
}

/**
  * Rimouve una nuova analisi dal hashtable
  * ed decrementa il numero di analisi.
**/
bool UsersInterface::remove ( const QString & userid )
{
	User *lu = Users->find ( userid ).value();
	if ( lu == ( User * ) 0 ) {
		return true;
	}
	Users->remove ( userid );
	delete lu;
	UsersNumber--;
	return false;
}

/**
  * Restituisce una specifica analisi.
**/
User * UsersInterface::get ( const QString & userid )
{
	return Users->find ( userid ).value();
}

// get tag list

TagList UsersInterface::getTagList()
{
	TagList tagList;

	tagList.values << "<none>";
	tagList.labels << "<none>";
	if ( Users->count() > 0 ) {
		foreach (User * u, *Users) {
			tagList.values.append ( u->data.login );
			tagList.labels.append ( u->data.login );
		}
	}

	return tagList;
}

// get by tag

User * UsersInterface::getByTag ( const QString & srcTag )
{
	return get ( srcTag );
}



/* return record tag */

const char * UsersInterface::getHeaderTag()
{
	static const char *ht = USERS_HEADERTAG;

	return ht;
}

// setup i/e tags

void UsersInterface::ieInit()
{
	loadUsr = 0;
}

// do the export of the cdr config onto the ImportExport stream

void UsersInterface::ieExport ( ImportExport & ie )
{
	if ( Users->count() > 0 ) {
		foreach (User * u, *Users) {
			ie.initWriteRecord ( USERS_HEADERTAG );

			ie.setWriteTag ( "login" );
			ie.addWriteRecord ( u->data.login );
			ie.setWriteTag ( "passwd" );
			ie.addWriteRecord ( u->data.passwd );
			ie.setWriteTag ( "userAdmin" );
			ie.addWriteRecord ( u->data.userAdmin );
			ie.setWriteTag ( "configAdmin" );
			ie.addWriteRecord ( u->data.configAdmin );
			ie.setWriteTag ( "enabledConfigurations" );
			ie.addArrayWriteRecord ( ( char * ) u->data.enabledConfigurations, MAX_ENABLE_CONFIGS, MAX_CONFIG_FILENAME );

			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void UsersInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != USERS_HEADERTAG )
		return;

	User * fld = new User;

	ieptr->getStrToken ( fld->data.login, "login", USR_LOGIN_LEN );
	ieptr->getStrToken ( fld->data.passwd, "passwd", USR_PASSWD_LEN );
	fld->data.userAdmin = ieptr->getBoolToken ( "userAdmin" );
	fld->data.configAdmin = ieptr->getBoolToken ( "configAdmin" );
	ieptr->getStrArrToken ( ( char * ) fld->data.enabledConfigurations, "enabledConfigurations", MAX_ENABLE_CONFIGS, MAX_CONFIG_FILENAME );

	add ( fld );
	return;
}

UsersInterface & UsersInterface::operator= ( UsersInterface & usr )
{
	Users = usr.Users;
	UsersNumber = count();

	return *this;
}

/** Questo method effettua la criptazione secondo lo standard DES della password utente
    upass fornita in chiaro */

char * UsersInterface::cryptPassword ( const QString & upass )
{
// 	char salt[3];
//
// 	qstrcpy(salt, upass.left(2).latin1());
// 	return crypt(upass.latin1(), salt);
	return 0;
}

bool UsersInterface::passwdCompare ( const QString & p1, const QString & p2 )
{
	char buf1[80], buf2[80];
	qstrcpy ( buf1, cryptPassword ( p1 ) );
	qstrcpy ( buf2, cryptPassword ( p2 ) );
	return ( qstrcmp ( buf1, buf2 ) ) ? true : false;
}



void UsersInterface::copyToCorba ( AdamsUserSeq * usrseqptr )
{
	ADAMS_USER usr;

	usrseqptr->length ( count() );
	int seqptr = 0;

	foreach (User * u, *Users) {
		c_qstrncpy ( usr.login, u->data.login, USR_LOGIN_LEN );
		c_qstrncpy ( usr.passwd, u->data.passwd, USR_PASSWD_LEN );
		usr.userAdmin = u->data.userAdmin;
		usr.configAdmin = u->data.configAdmin;
		for ( int i = 0; i < MAX_ENABLE_CONFIGS; i++ ) {
			c_qstrncpy ( usr.enabledConfigurations[i], u->data.enabledConfigurations[i], MAX_CONFIG_FILENAME );
		}

		( *usrseqptr ) [seqptr++] = usr;
	}
}

void UsersInterface::fillFromCorba ( const AdamsUserSeq * usrseqptr )
{
	User * u;
	clear();

	for ( int cnt = 0; cnt < usrseqptr->length(); cnt++ ) {
		u = new User;

		c_qstrncpy ( u->data.login, ( *usrseqptr ) [cnt].login, USR_LOGIN_LEN );
		c_qstrncpy ( u->data.passwd, ( *usrseqptr ) [cnt].passwd, USR_PASSWD_LEN );
		u->data.userAdmin = ( *usrseqptr ) [cnt].userAdmin;
		u->data.configAdmin = ( *usrseqptr ) [cnt].configAdmin;
		for ( int i = 0; i < MAX_ENABLE_CONFIGS; i++ ) {
			c_qstrncpy ( u->data.enabledConfigurations[i], ( *usrseqptr ) [cnt].enabledConfigurations[i], MAX_CONFIG_FILENAME );
		}

		add ( u );
	}
	UsersNumber = count();
}

