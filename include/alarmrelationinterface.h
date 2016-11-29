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

#ifndef ALARMRELATIONINTERFACE_H
#define ALARMRELATIONINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <dataelementinterface.h>
#include <alarmrelation.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

#define ALARMRELATION_HEADERTAG			"ALARMRELATIONS"
#define ALARMRELATION_MAXFIELDS			211

typedef QMultiHash<QString, alarmrelation *>::iterator		AlarmRelationsIterator;
typedef QMultiHash<QString, alarmrelation *>::const_iterator	AlarmRelationsConstIterator;

/**Questa classe implementa l'interfaccia verso le classe
  *Relazione Allarmi.
   @short Definizione della direttrici di allarme
  *@author Piergiorgio Betti
  */

class AlarmRelationInterface : public QObject
{
	Q_OBJECT
public:
	AlarmRelationInterface();
	~AlarmRelationInterface();

	/**
	  *  Inserisce una relazione nella hash table.
	**/
	bool add ( alarmrelation * newRelation );
	/**
	  *  Elimina una relazione nella hash table.
	**/
	bool remove ( int RelationNum );
	/**
	  * Estrae una relazione dalla hush table (per indirizzo)
	**/
	alarmrelation * get ( int RelationNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	alarmrelation * getByTag ( const QString & srcTag );

	/**
	  *  Ritorna il tag identificativo della sezione Relazioni nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of relations in the dictionary
	**/
	inline int count() {
		return AlarmRelations->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( alarmrelation * ar, *AlarmRelations ) {
			if ( ar->data.idAlarmRelation > uniq ) uniq = ar->data.idAlarmRelation;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	AlarmRelationsIterator getIterator() {
		assert ( AlarmRelations != 0 );
		return AlarmRelations->begin();
	}
	/** Ritorna un iteratore alla lista */
	AlarmRelationsIterator hashEnd() {
		assert ( AlarmRelations != 0 );
		return AlarmRelations->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *AlarmRelations );
		AlarmRelations->clear();
		AlarmRelationsNumber = uniq = 0;
	}

	AlarmRelationInterface & operator= (const AlarmRelationInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (alarmrelation * d, *in.AlarmRelations) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );
	/** Questo method viene utilizzato per impostare la @ref DataElementeInterface utilizzata per estrapolare
	  * i tag descrittivi della relazione (elementi di traffico)
	  */
	inline void setDataElementReference ( DataElementInterface * teRef ) {
		teReference = teRef;
	}
	/** Metodo utilizzato per costruire il tag identificativo della relazione */
	QString decodeRel ( int relid, const char * sep = 0 );

	void copyToCorba ( AlarmRelationSeq * seqptr );
	void fillFromCorba ( const AlarmRelationSeq * seqptr );

private:
	QMultiHash<QString, alarmrelation *> * AlarmRelations;				/* Relazioni hush table */
	int AlarmRelationsNumber;
	char pathname[PATH_MAX];						/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;
	DataElementInterface * teReference;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
