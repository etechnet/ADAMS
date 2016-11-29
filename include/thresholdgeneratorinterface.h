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

#ifndef THRESHOLDGENERATORINTERFACE_H
#define THRESHOLDGENERATORINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <thresholdgenerator.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#define THRESHOLDGENERATOR_HEADERTAG			"THRESHOLDGENERATORS"
#define THRESHOLDGENERATOR_MAXFIELDS			211

typedef QMultiHash<QString, thresholdgenerator *>::iterator		ThresholdGeneratorIterator;
typedef QMultiHash<QString, thresholdgenerator *>::const_iterator	ThresholdGeneratorConstIterator;

/**Questa classe implementa l'interfaccia verso le classe
  *Generatori Soglie Allarmi.
   @short Definizione dei generatore di soglie di allarme
  *@author Piergiorgio Betti
  */

class ThresholdGeneratorInterface : public QObject
{
	Q_OBJECT
public:
	ThresholdGeneratorInterface();
	~ThresholdGeneratorInterface();

	/**
	  *  Inserisce un generatore nella hash table.
	**/
	bool add ( thresholdgenerator * newHandler );
	/**
	  *  Elimina un generatore nella hash table.
	**/
	bool remove ( int handlerNum );
	/**
	  * Estrae un generatore dalla hush table (per indirizzo)
	**/
	thresholdgenerator * get ( int handlerNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	thresholdgenerator * getByTag ( const QString & srcTag );

	/**
	  *  Ritorna il tag identificativo della sezione Relazioni nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of relations in the dictionary
	**/
	inline int count() {
		return ThresholdGenerator->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( thresholdgenerator * tg, *ThresholdGenerator ) {
			if ( tg->data.idThresholdGenerator > uniq ) uniq = tg->data.idThresholdGenerator;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	ThresholdGeneratorIterator getIterator() {
		assert ( ThresholdGenerator != 0 );
		return ThresholdGenerator->begin();
	}
	/** Ritorna un iteratore alla lista */
	ThresholdGeneratorIterator hashEnd() {
		assert ( ThresholdGenerator != 0 );
		return ThresholdGenerator->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *ThresholdGenerator );
		ThresholdGenerator->clear();
		ThresholdGeneratorNumber = uniq = 0;
	}

	ThresholdGeneratorInterface & operator= (const ThresholdGeneratorInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (thresholdgenerator * d, *in.ThresholdGenerator) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( ThresholdGeneratorSeq * seqptr );
	void fillFromCorba ( const ThresholdGeneratorSeq * seqptr );

private:
	QMultiHash<QString, thresholdgenerator *> * ThresholdGenerator;		/* Relazioni hush table */
	int ThresholdGeneratorNumber;
	char pathname[PATH_MAX];						/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
