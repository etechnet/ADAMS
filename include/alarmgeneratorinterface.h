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

#ifndef ALARMGENERATORINTERFACE_H
#define ALARMGENERATORINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <dataelementinterface.h>
#include <alarmgenerator.h>
#include <importexport.h>

#include <server_stub_safe_include.h>

#define ALARMGENERATOR_HEADERTAG			"ALARMGENERATORS"
#define ALARMGENERATOR_MAXFIELDS			211

typedef QMultiHash<QString, alarmgenerator *>::iterator		AlarmGeneratorIterator;
typedef QMultiHash<QString, alarmgenerator *>::const_iterator	AlarmGeneratorConstIterator;

/**Questa classe implementa l'interfaccia verso le classe
  *Generatori Allarmi.
   @short Definizione dei generator di allarme
  *@author Piergiorgio Betti
  */

class AlarmGeneratorInterface : public QObject
{
	Q_OBJECT
public:
	AlarmGeneratorInterface();
	~AlarmGeneratorInterface();

	/**
	  *  Inserisce un generatore nella hash table.
	**/
	bool add ( alarmgenerator * newHandler );
	/**
	  *  Elimina un generatore nella hash table.
	**/
	bool remove ( int handlerNum );
	/**
	  * Estrae un generatore dalla hush table (per indirizzo)
	**/
	alarmgenerator * get ( int handlerNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	alarmgenerator * getByTag ( const QString & srcTag );

	/**
	  *  Ritorna il tag identificativo della sezione Relazioni nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of relations in the dictionary
	**/
	inline int count() {
		return AlarmGenerator->count();
	}
	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( alarmgenerator * ag, *AlarmGenerator ) {
			if ( ag->data.idAlarmGenerator > uniq ) uniq = ag->data.idAlarmGenerator;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	AlarmGeneratorIterator getIterator() {
		assert ( AlarmGenerator != 0 );
		return AlarmGenerator->begin();
	}
	/** Ritorna un iteratore alla lista */
	AlarmGeneratorIterator hashEnd() {
		assert ( AlarmGenerator != 0 );
		return AlarmGenerator->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *AlarmGenerator );
		AlarmGenerator->clear();
		AlarmGeneratorNumber = uniq = 0;
	}

	AlarmGeneratorInterface & operator= (const AlarmGeneratorInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (alarmgenerator * d, *in.AlarmGenerator) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( AlarmGeneratorSeq * seqptr );
	void fillFromCorba ( const AlarmGeneratorSeq * seqptr );

private:
	QMultiHash<QString, alarmgenerator *> * AlarmGenerator;			/* Relazioni hush table */
	int AlarmGeneratorNumber;
	char pathname[PATH_MAX];						/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
