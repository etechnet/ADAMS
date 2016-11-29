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

#ifndef PLUGINREGISTRYINTERFACE_H
#define PLUGINREGISTRYINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <pluginregistry.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#ifdef ADAMS_HPUX
#undef PATH_MAX
#define PATH_MAX	_XOPEN_PATH_MAX
#endif

#define PLUGINREGISTRY_HEADERTAG		"PLUGINREGISTRY"
#define PLUGINREGISTRY_MAXFIELDS		307

typedef QMultiHash<QString, PluginRegistry *>::iterator		PluginRegistryIterator;
typedef QMultiHash<QString, PluginRegistry *>::const_iterator	PluginRegistryConstIterator;

/**Questa classe implementa le necessarie funzionlit� di
interfaccia verso la classe PluginRegistry.
   @short Definizione repositorio plugins
  *@author Piergiorgio Betti
  */

class PluginRegistryInterface : public QObject
{
	Q_OBJECT
public:
	PluginRegistryInterface();
	~PluginRegistryInterface();

	/** Inserisce un record nella hash table. */
	bool add ( PluginRegistry * newPluginRegistry );
	/** Elimina un record nella hash table. */
	bool remove ( int PluginRegistryNum );
	/** Estrae un record dalla hush table (per indirizzo) */
	PluginRegistry * get ( int PluginRegistryNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	PluginRegistry * getByTag ( const QString & srcTag );
	/** Ritorna il tag identificativo della sezione plr nel file di configurazione */
	const char * getHeaderTag();
	/** Returns the number of PluginRegistrys in the dictionary */
	inline int count() {
		return PluginRegistrys->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( PluginRegistry * p, *PluginRegistrys ) {
			if ( p->data.idPlugin > uniq ) uniq = p->data.idPlugin;
		}
		return uniq + 1;
	}

	/** Ritorna un iteratore alla lista */
	PluginRegistryIterator getIterator() {
		assert ( PluginRegistrys != 0 );
		return PluginRegistrys->begin();
	}
	/** Ritorna un iteratore alla lista */
	PluginRegistryIterator hashEnd() {
		assert ( PluginRegistrys != 0 );
		return PluginRegistrys->end();
	}

	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll(*PluginRegistrys);
		PluginRegistrys->clear();
		PluginRegistrysNumber = uniq = 0;
	}

	PluginRegistryInterface & operator= (const PluginRegistryInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (PluginRegistry * d, *in.PluginRegistrys) {
			this->add(d);
		}

		return *this;
	}

	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( PluginRegistrySeq * seqptr );
	void fillFromCorba ( const PluginRegistrySeq * seqptr );

private:
	QMultiHash<QString, PluginRegistry*> * PluginRegistrys;		/* Elementi hush table */
	int PluginRegistrysNumber;
	char pathname[PATH_MAX];					/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif
