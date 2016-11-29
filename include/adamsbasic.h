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

#ifndef ADAMSBASIC_H
#define ADAMSBASIC_H

#include <stdlib.h>

#include <Qt/qobject.h>
#include <Qt/qthread.h>
#include <Qt/qstringlist.h>
#include <drinterface.h>
#include <script.h>
#include <scriptbasic/scriba.h>
#include <adamsserver.h>

typedef QMultiHash<QString, long *>	nbVarCache;

/** In questa classe viene implementata la completa incapsulazione della libreria ScriptBasic,
  * e sono contenute le interfaccie per l'utilizzo delle funzionalita' di scripting.
   @short Embedded BASIC engine
  *@author Piergiorgio Betti
  */

class AdamsBasic : public QObject
{
	Q_OBJECT
public:
	enum nbStatus {				// possible status values for this instance
	        nbSuccess,
	        nbError,
	        nbFatalError,
	        num_nbStatus
	};

	enum nbReportModes {
	        nbToLogFile,
	        nbToDialogBox,
	        nbToExternal,
	        num_nbReportModes
	};

	AdamsBasic ( QObject *parent = 0, const char *name = 0 );
	~AdamsBasic();

	/** Utilizzato per riabilitare la possibilit� di istanza (bloccata in caso di errori irrecuperabili) */
	void resetStopInstanceInit();
	/** Utilizzato per testare lo stato dell'instanze in chiamate asincrone */
	inline nbStatus getStatus() {
		return status;
	}
	/** Questo method viene invocato per comunicare all'interprete l'immagine del programma (formula/script)
	    da eseguire.
	  */
	bool setProgramString ( const QString & txt );
	/** Questo method viene invocato per comunicare all'interprete l'immagine del programma (formula/script)
	    da eseguire.
	  */
	bool setProgramScript ( const Script & scr );
	/** Esegue la script basic con tutte le impostazioni eseguite dal chiamante */
	bool execute();
	/** Viene impostato il nome della variabile destinata a contenere il risultato della script */
	inline void setResultName ( const QString & vname ) {
		QString _resultName = QString ( "main::" ) + vname;
		resultName = qstrdup ( qPrintable ( _resultName ) );
	}
	/** Controllo e conversione del risultato della script in long */
	bool getResult ( long & rvar );
	/** Overload del precedente method. Opera su tipo double. */
	bool getResult ( double & rvar );
	/** Overload del precedente method. Opera su tipo QString. */
	bool getResult ( QString & rvar );
	/** Torna il tipo di risultato o -1 in caso di errore */
	long probeResultType();
	/** Aggiunge una variabile globale all'immagine della script da eseguire. Viene eseguita l'inizializzazione.
	    L'impostazione delle variabili deve essere eseguita prima del method setProgramString()
	  */
	void addInitVariable ( const QString & vname, DRF_Types vtype );
	/** Imposta il valore di una variabile globale definita in precedenza con addInitVariable(). Notare che questo
	    method non pu� essere invocato prima di una setProgramString() eseguita con successo.
	  */
	bool setValue ( const QString & vname, const QString & vvalue );
	/** Overload del precedente method. Opera su tipo long. */
	bool setValue ( const QString & vname, long vvalue );
	/** Overload del precedente method. Opera su tipo double. */
	bool setValue ( const QString & vname, double vvalue );
	/** Overload del precedente method. Opera su tipo "char *". */
	bool setValue ( const QString & vname, char * vvalue );
	/** Overload del precedente method. Opera su tipo QStringList. */
	bool setValue ( const QString & vname, const QStringList & arr );
	/** Set della modalita di reportistica degli errori (GUI / file) */
	static void setReportMode ( nbReportModes rm );
	/** Ritorna un puntatore alla script di configurazione (se assegnata) */
	inline const Script * getOriginatingScript() {
		return scriptStore;
	}
	/** Imposta testo dell'ultimo errore riscontrato in esecuzione */
	static void setLastErrorMessage ( const QString & msg );
	/** Ritorna al chiamante il testo dell'ultimo errore riscontrato in esecuzione */
	static const QString & getLastErrorMessage();

private:
	pSbProgram sbInst;					// my scriba instance

	// controls
	int sbInstId;
	nbStatus status;
	// functionals
	char * bytebucket;
	int bytebucketsize;
	char cmdBuffer[1024];
	char * resultName;
	pSbData retVal;
	long retValType;
	QStringList variablesList;
	QStringList otherList;
	nbVarCache varCache;
	const Script * scriptStore;


	// internal methods
	/** Metodo di conversione da QString a "non-const char *" */
	char * nonConstCharPtr ( const QString & str );
	/** Utilizzato per estrarre il valore della variabile definite per il valore di ritorno */
	bool getResultVariable();
};

#endif
