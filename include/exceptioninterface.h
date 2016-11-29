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

#ifndef EXCEPTIONINTERFACE_H
#define EXCEPTIONINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <exception.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#define EXCEPTION_HEADERTAG			"EXCEPTIONS"
#define EXCEPTION_MAXFIELDS			307

/**Questa classe definisce l'interfaccia verso la classe Exception.
  *@author Piergiorgio Betti.
  */

typedef QMultiHash<QString, Exception *>::iterator		ExceptionIterator;
typedef QMultiHash<QString, Exception *>::const_iterator	ExceptionConstIterator;


class ExceptionInterface : public QObject
{
	Q_OBJECT
public:
	ExceptionInterface();
	~ExceptionInterface();
	/**
	  *  Inserisce una eccezione nella hash table.
	**/
	bool add ( Exception * newException );
	/**
	  *  Elimina una eccezione nella hash table.
	**/
	bool remove ( int ExceptionNum );
	/**
	  * Estrae una eccezione dalla hush table (per indirizzo)
	**/
	Exception * get ( int ExceptionNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();

	/** Estrae un campo dalla hush table (per tag) */
	Exception * getByTag ( const QString & srcTag );
	/**
	  *  Ritorna il tag identificativo della sezione eccezioni nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of exceptions in the dictionary
	**/
	inline int count() {
		return Exceptions->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach (Exception * e, *Exceptions) {
			if ( e->data.idException > uniq ) uniq = e->data.idException;
		}
		return uniq + 1;
	}

	/** Ritorna un iteratore alla lista */
	ExceptionIterator getIterator() {
		assert ( Exceptions != 0 );
		return Exceptions->begin();
	}
	/** Ritorna un iteratore alla lista */
	ExceptionIterator hashEnd() {
		assert ( Exceptions != 0 );
		return Exceptions->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Exceptions );
		Exceptions->clear();
		ExceptionsNumber = uniq = 0;
	}

	ExceptionInterface & operator= (const ExceptionInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (Exception * d, *in.Exceptions) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( ExceptionSeq * seqptr );
	void fillFromCorba ( const ExceptionSeq * seqptr );

private:
	QMultiHash<QString, Exception *> * Exceptions;		/* Elementi hush table */
	int ExceptionsNumber;
	char pathname[PATH_MAX];				/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
