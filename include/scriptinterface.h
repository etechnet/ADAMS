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

#ifndef SCRIPTINTERFACE_H
#define SCRIPTINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <script.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#define SCRIPT_HEADERTAG		"SCRIPT_HEADER"
#define SCRIPT_MAXFIELDS		307

typedef QMultiHash<QString, Script *>::iterator		ScriptIterator;
typedef QMultiHash<QString, Script *>::const_iterator	ScriptConstIterator;

/**Implementa le necessarie funzionalit� di interfaccia per la
manipolazione del testo delle script @ref AdamsBasic.
  *@author Piergiorgio Betti
  */

class ScriptInterface : public QObject
{
	Q_OBJECT
public:
	ScriptInterface();
	~ScriptInterface();

	/** Inserisce un record nella hash table. */
	bool add ( Script * newScript );
	/** Elimina un record nella hash table. */
	bool remove ( int ScriptNum );
	/** Estrae un record dalla hush table (per indirizzo) */
	Script * get ( int ScriptNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	Script * getByTag ( const QString & srcTag );
	/** Ritorna il tag identificativo della sezione plr nel file di configurazione */
	const char * getHeaderTag();
	/** Returns the number of Scripts in the dictionary */
	inline int count() {
		return Scripts->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( Script * s, *Scripts ) {
			if ( s->data.idScript > uniq ) uniq = s->data.idScript;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	ScriptIterator getIterator() {
		assert ( Scripts != 0 );
		return Scripts->begin();
	}
	/** Ritorna un iteratore alla lista */
	ScriptIterator hashEnd() {
		assert ( Scripts != 0 );
		return Scripts->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Scripts );
		Scripts->clear();
		ScriptsNumber = uniq = 0;
	}

	ScriptInterface & operator= (const ScriptInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (Script * d, *in.Scripts) {
			this->add(d);
		}

		return *this;
	}

	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( ScriptSeq * seqptr );
	void fillFromCorba ( const ScriptSeq * seqptr );

private:
	QMultiHash<QString, Script *> * Scripts;		/* Elementi hush table */
	int ScriptsNumber;
	char pathname[PATH_MAX];				/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
