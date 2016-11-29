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

#ifndef RELATIONINTERFACE_H
#define RELATIONINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <dataelementinterface.h>
#include <relation.h>
#include <importexport.h>

#include <server_stub_safe_include.h>

#define RELATION_HEADERTAG			"RELATIONS"
#define RELATION_MAXFIELDS			211

typedef QMultiHash<QString, relation *>::iterator	RelationIterator;
typedef QMultiHash<QString, relation *>::const_iterator	RelationConstIterator;

/**Questa classe implementa l'interfaccia verso le classi
  *Relazione ed elemento di traffico.
   @short Definizione della matrice delle relazioni
  *@author Piergiorgio Betti
  */

class RelationInterface : public QObject
{
	Q_OBJECT
public:
	RelationInterface();
	~RelationInterface();

	/**
	  *  Inserisce una relazione nella hash table.
	**/
	bool add ( relation * newRelation );
	/**
	  *  Elimina una relazione nella hash table.
	**/
	bool remove ( int RelationNum );
	/**
	  * Estrae una relazione dalla hush table (per indirizzo)
	**/
	relation * get ( int RelationNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	relation * getByTag ( const QString & srcTag );

	/**
	  *  Ritorna il tag identificativo della sezione Relazioni nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of relations in the dictionary
	**/
	inline int count() {
		return Relations->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( relation * r, *Relations ) {
			if ( r->data.idRelation > uniq ) uniq = r->data.idRelation;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	RelationIterator getIterator() {
		assert ( Relations != 0 );
		return Relations->begin();
	}
	/** Ritorna un iteratore alla lista */
	RelationIterator hashEnd() {
		assert ( Relations != 0 );
		return Relations->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Relations );
		Relations->clear();
		RelationsNumber = uniq = 0;
	}

	RelationInterface & operator= (const RelationInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (relation * d, *in.Relations) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );
	/** Questo method viene utilizzato per impostare la @ref DataElementeInterface utilizzata per estrapolare
	  * i tag descrittivi della relazione (primo::secondo elemento di traffico)
	  */
	inline void setDataElementReference ( DataElementInterface * teRef ) {
		teReference = teRef;
	}
	/** Metodo utilizzato per costruire il tag identificativo della relazione */
	QString decodeRel ( int relid, const int* ffVector = 0 );

	void copyToCorba ( RelationSeq * seqptr );
	void fillFromCorba ( const RelationSeq * seqptr );

private:
	QMultiHash<QString, relation *>	* Relations;		/* Relazioni hush table */
	int RelationsNumber;
	char pathname[PATH_MAX];				/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;
	DataElementInterface * teReference;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
