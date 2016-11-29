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

#ifndef USERINTERFACE_H
#define USERINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qdatetime.h>
#include <Qt/qhash.h>
#include <user.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#define USERS_HEADERTAG			"RECORD_USERS"
#define USERS_MAXFIELDS			307

typedef QMultiHash<QString, User *>::iterator		UserIterator;
typedef QMultiHash<QString, User *>::const_iterator	UserConstIterator;


/**Questa classe contiene le informazioni necessarie a gestire
  *la configurazione degli utenti ADAMS
  *@author Piergiorgio Betti <pbetti@lpconsul.net>
  *@short Configurazione utenti
  */

class UsersInterface : public QObject
{
	Q_OBJECT
public:
	UsersInterface();
	~UsersInterface();

	/** Imposta la struttura dati passata in argomento nell'area locale */
	bool add ( User * inusr );
	/** Elimina l'utente specificato dalla hash table */
	bool remove ( const QString & userid );
	/** Ritorna un puntatore alla struttura dati degli utenti */
	User * get ( const QString & userid );
	/** Fornisce una lista dei tag degli utenti disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	User * getByTag ( const QString & srcTag );
	/** Ritorna il numero di utenti nel dizionario */
	inline int count() {
		return Users->count();
	}
	/** Ritorna il tag identificativo della sezione Users nel file di configurazione */
	const char * getHeaderTag();
	/** Overload dell'operatore "=" */
	UsersInterface & operator= ( UsersInterface & usr );
	/** Ritorna un iteratore alla lista */
	UserIterator getIterator() {
		assert ( Users != 0 );
		return Users->begin();
	}
	/** Ritorna un iteratore alla lista */
	UserIterator hashEnd() {
		assert ( Users != 0 );
		return Users->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Users );
		Users->clear();
		UsersNumber = uniq = 0;
	}

	UsersInterface & operator= (const UsersInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (User * d, *in.Users) {
			this->add(d);
		}

		return *this;
	}
	/** Questo method effettua la criptazione secondo lo standard DES della password utente
	  * upass fornita in chiaro */
	char * cryptPassword ( const QString & upass );
	/** Viene effettuato il test di uguaglianza tra le due password fornite in argomento */
	bool passwdCompare ( const QString & p1, const QString & p2 );
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( AdamsUserSeq * usrseqptr );
	void fillFromCorba ( const AdamsUserSeq * usrseqptr );

private:
	QMultiHash<QString, User *> * Users;				/* Users hush table */
	int UsersNumber;
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;
	int loadUsr;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
