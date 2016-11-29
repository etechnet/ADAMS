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

#ifndef DATAELEMENTINTERFACE_H
#define DATAELEMENTINTERFACE_H

#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <limits.h>
#include <importexport.h>
#include <dataelement.h>
#include <cnfglobals.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#define ELEMENT_HEADERTAG			"ELEMENTS"
#define TRAFFICELEMENT_MAXFIELDS		307
#ifdef ADAMS_HPUX
#undef PATH_MAX
#define PATH_MAX	_XOPEN_PATH_MAX
#endif

typedef struct {
	long shiftVal;
	long from;
	long to;
} SHIFTRANGE;


typedef QMultiHash<QString, DataElement *>::iterator		DataElementIterator;
typedef QMultiHash<QString, DataElement *>::const_iterator	DataElementConstIterator;

/**Questa classe definisce l'interfaccia per la classe DataElement.
  *@author Piergiorgio Betti.
  */

class DataElementInterface : public QObject
{
	Q_OBJECT
public:
	DataElementInterface();
	~DataElementInterface();

	/**
	  *  Inserisce un elemento nella hash table.
	**/
	bool add ( DataElement * newElement );
	/**
	  *  Elimina un elemento nella hash table.
	**/
	bool remove ( int ElementNum );
	/**
	  * Estrae un elemento dalla hush table (per indirizzo)
	**/
	DataElement * get ( int ElementNum );

	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();

	/** Estrae un campo dalla hush table (per tag) */
	DataElement * getByTag ( const QString & srcTag );
	/**
	  *  Ritorna il tag identificativo della sezione Elementi nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of elements in the dictionary
	**/
	inline int count() {
		return Elements->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( DataElement * e, *Elements ) {
			if ( e->data.idElement > uniq ) uniq = e->data.idElement;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	DataElementIterator getIterator() {
		assert ( Elements != 0 );
		return Elements->begin();
	}
	/** Ritorna un iteratore alla lista */
	DataElementIterator hashEnd() {
		assert ( Elements != 0 );
		return Elements->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Elements );
		Elements->clear();
		ElementsNumber = uniq = 0;
	}

	DataElementInterface & operator= (const DataElementInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (DataElement * d, *in.Elements) {
			this->add(d);
		}

		return *this;
	}
	/** Testa per la presenza di shifter validi sull'elemento, ritornando il numero di range
	  * in caso positivo.
	   */
	int hasShifter ( int id );
	/** Estrae i valori dello shifter in forma numerica */
	SHIFTRANGE * getShifters ( int id );
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( ElementSeq * seqptr );
	void fillFromCorba ( const ElementSeq * seqptr );

private:
	QMultiHash<QString, DataElement *> * Elements;		/* Elementi hush table */
	int ElementsNumber;
	unsigned long uniq;
	char pathname[PATH_MAX];				/* file name + path */
	QDateTime currentVersionDate;
	QString sectionDescription;
	char shifterTestBuf[ ( VALSHIFTER_LEN * 3 ) + 1];

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
