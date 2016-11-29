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

#ifndef ANALYSISINTERFACE_H
#define ANALYSISINTERFACE_H

#include <cnfglobals.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <analysis.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#ifdef ADAMS_HPUX
#undef PATH_MAX
#define PATH_MAX	_XOPEN_PATH_MAX
#endif

/**Questa classe definisce l'interfaccia verso
  *la classe analisi.
  */

#define ANALYSIS_HEADERTAG			"ANALYSIS"
#define ANALYSIS_MAXFIELDS			300

typedef QMultiHash<QString, Analysis *>::iterator	AnalysisIterator;
typedef QMultiHash<QString, Analysis *>::const_iterator	AnalysisConstIterator;

class AnalysisInterface : public QObject
{
	Q_OBJECT
public:
	AnalysisInterface();
	~AnalysisInterface();

	/**
	  *  Inserisce una Analysis nella hash table.
	**/
	bool add ( Analysis * newAnalysis );
	/**
	  *  Elimina una Analysis nella hash table.
	**/
	bool remove ( int AnalysisNum );
	/**
	  * Estrae una Analysis dalla hush table (per indirizzo)
	**/
	Analysis * get ( int AnalysisNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();

	/** Estrae un campo dalla hush table (per tag) */
	Analysis * getByTag ( const QString & srcTag );
	/**
	  *  Ritorna il tag identificativo della sezione Analysis nel file di configurazione
	**/
	const char * getHeaderTag();
	/**
	  *  Returns the number of analysis in the dictionary
	**/
	inline int count() {
		return Analyzes->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach (Analysis * a, *Analyzes) {
			if ( a->data.idAnalysis > uniq ) uniq = a->data.idAnalysis;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	AnalysisIterator getIterator() {
		assert ( Analyzes != 0 );
		return Analyzes->begin();
	}
	/** Ritorna un iteratore alla lista */
	AnalysisIterator hashEnd() {
		assert ( Analyzes != 0 );
		return Analyzes->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Analyzes );
		Analyzes->clear();
		AnalysisNumber = uniq = 0;
	}

	AnalysisInterface & operator= (const AnalysisInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (Analysis * d, *in.Analyzes) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	void copyToCorba ( AnalisysSeq * seqptr );
	void fillFromCorba ( const AnalisysSeq * seqptr );

private:
	QMultiHash<QString, Analysis *> * Analyzes;		/* Analysis hush table */
	int AnalysisNumber;
	char pathname[PATH_MAX];				/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );

};

#endif
