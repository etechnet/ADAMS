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

#ifndef COUNTERINTERFACE_H
#define COUNTERINTERFACE_H

#include <cnfglobals.h>
#include <adams_limits.h>
#include <Qt/qobject.h>
#include <Qt/qdatetime.h>
#include <nodo.h>
#include <counters.h>
#include <importexport.h>
#include <drimagehandler.h>

#include <server_stub_safe_include.h>

#ifdef ADAMS_HPUX
#undef PATH_MAX
#define PATH_MAX	_XOPEN_PATH_MAX
#endif

#define CNT_HEADERTAG			"RECORD_COUNTER"
#define CNTKIT_HEADERTAG		"COUNTERKIT"
#define COUNTERS_MAXKITS		101

typedef QMultiHash<QString, Counters *>::iterator		CountersIterator;
typedef QMultiHash<QString, Counters *>::const_iterator		CountersConstIterator;

static const char * operatorTypeDesc[] = {
	"Unmanaged",
	"FieldLink",
	"SimpleCounter",
	"SumCounter",
	"SimpleAverageCounter",
	"SumAverageCounter,"
	"FormulaField"
};

class PluginImpl;
class AdamsCompleteConfig;
class QueryParams;
class DataElement;

/** Gestione dei record contatori sia per la fasi operative che nella gestione dell'i/o.
  * Nella struttura dati viene gestita la programmazione dei contatori di traffico.
  *@short Contatori traffico.
  *@author Piergiorgio Betti
  */

class CounterInterface : public QObject
{
	Q_OBJECT
public:

	enum operatorType {
	        Unmanaged,
	        FieldLink,
	        SimpleCounter,
	        SumCounter,
	        SimpleAverageCounter,
	        SumAverageCounter,
	        FormulaField,
	        operatorTypeNumber
	};

	CounterInterface();
	~CounterInterface();

	/** Inserisce un record nella hash table. */
	bool add ( Counters * newCounters );
	/** Elimina un record nella hash table. */
	bool remove ( int CountersNum );
	/** Estrae un record dalla hush table (per indirizzo) */
	Counters * get ( int CountersNum );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Estrae un campo dalla hush table (per tag) */
	Counters * getByTag ( const QString & srcTag );
	/** Ritorna il tag identificativo della sezione plr nel file di configurazione */
	const char * getHeaderTag();
	/** Returns the number of Counterss in the dictionary */
	inline int count() {
		return Counterss->count();
	}

	/** Ritorna uno unique id valido per questa classe */
	unsigned long getUnique() {
		foreach ( Counters * c, *Counterss ) {
			if ( c->data.idCounter > uniq ) uniq = c->data.idCounter;
		}
		return uniq + 1;
	}
	/** Ritorna un iteratore alla lista */
	CountersIterator getIterator() {
		assert ( Counterss != 0 );
		return Counterss->begin();
	}
	/** Ritorna un iteratore alla lista */
	CountersIterator hashEnd() {
		assert ( Counterss != 0 );
		return Counterss->end();
	}
	/** Azzera la lista (non distruttivo degli elementi) */
	void clear() {
		qDeleteAll ( *Counterss );
		Counterss->clear();
		CounterssNumber = uniq = 0;
	}

	CounterInterface & operator= (const CounterInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (Counters * d, *in.Counterss) {
			this->add(d);
		}

		return *this;
	}

	/** Overload dell'operatore "=" */
	CounterInterface & operator= ( CounterInterface & cnt );
	/** Calcola l'indice al contatore di riferimento per il calcolo della percentuale
	  * del contatore passato in argomento
	  */
	inline int getPercentOf ( const Counters & counters, unsigned int idx ) {
		if ( idx >= CNT_NUM ) return -1;
		return counters.data.counterKit[idx].percentOf - 1;
	}
	/**
	Estrae il valore di un contatore dal kit corrente
	      @param kit Kit contatori
	      @param idx Indice del contatore
	      @param nodo Nodo dati
	*/
	double getCounterValue ( Counters * kit, int idx, Nodo * nodo, int n_int = 0, int n_dbl = 0 );
	/**
	Ritorna il tipo del contatore in argomento
		@param idx Indice contatore
	*/
	int getCounterType ( int idx );

	/** Deriva l'indice del contatore a partire dal suo tag
		@param kit Kit contatori
		@param cntTag Tag (nome) del contatore
		@param tagi Ritorna l'offset del contatore all'interno del vettore
	*/
	int getCounterIndex ( Counters * kit, const QString & cntTag, int & tagi );

	/** Ritorna il numeri di contatori configurati nel kit
		@param kit Kit contatori
		@param n_int Ritorna il numero di contatori interi
		@param n_dbl Ritorna il numero di contatori double
	*/
	bool getConfiguredCounters ( Counters * kit, int & n_int, int & n_dbl );
	/** Ritorna il numeri di contatori double configurati nel kit
		@param kit Kit contatori
	*/
	int getConfiguredDoubleCounters ( Counters * kit );
	/** Ritorna il numeri di contatori interi configurati nel kit
		@param kit Kit contatori
	*/
	int getConfiguredIntegerCounters ( Counters * kit );
	/**
	 * Ritorna i dati del contatore
		@param kit Kit contatori
		@param idx Indice contatore
	 */
	Counters::COUNTERKIT * getCounterKitDataByIndex ( Counters * kit, int idx );
	/**
	 * Ritorna i dati del contatore
		@param kit Kit contatori
		@param offset Offset del contatore nel vettore counterKit
	 */
	Counters::COUNTERKIT * getCounterKitDataByOffset ( Counters * kit, int offset );
	/** Esegue l'aggiornamento dei contatori del kit sulla base della configurazione
	 */
	bool updateCounters ( AdamsCompleteConfig * ncc, QueryParams * params, Counters * kit, Nodo * nodo, DRImageHandler * CDRr, int n_int = 0, int n_dbl = 0 );

	/** Esegue l'aggiornamento finale dei contatori (ovvero con i dati cumulativi)
	 */
	bool closeCounterUpdate ( AdamsCompleteConfig * ncc, QueryParams * params, Counters* kit, Nodo * nodo, int n_int, int n_dbl );

	/** Ritorna la stringa (descrittiva) corrispondente al tipo operatore fornito in argomento */
	inline static const char * getOperatorString ( unsigned int idx ) {
		if ( idx < operatorTypeNumber )
			return operatorTypeDesc[idx];
		else
			return 0;

	}

	/** Incrementa il contatore */
	inline void incCounterVal ( ParameterList * cntRow, int idx ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				++cntRow->int_counters[idx - 1];
				return;
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				++cntRow->dbl_counters[idx - 1];
				return;
		}
		lout << "incCounterVal: ERROR unknown counter type..." << endl;
		return;
	}

	/** Somma il contatore */
	inline void sumCounterVal ( ParameterList * cntRow, int idx, double val ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				cntRow->int_counters[idx - 1] += ( unsigned long ) val;
				return;
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				cntRow->dbl_counters[idx - 1] += val;
				return;
		}
		lout << "sumCounterVal: ERROR unknown counter type..." << endl;
		return;
	}

	inline void sumCounterVal ( ParameterList * cntRow, int idx, unsigned long val ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				cntRow->int_counters[idx - 1] += val;
				return;
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				cntRow->dbl_counters[idx - 1] += ( double ) val;
				return;
		}
		lout << "sumCounterVal: ERROR unknown counter type..." << endl;
		return;
	}

	/** Valore medio contatore */
	inline void averageCounterVal ( ParameterList * cntRow, int idx, double val, double den ) {
		if ( den == 0.0 )
			return;

		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				cntRow->int_counters[idx - 1] = ( unsigned long ) ( val / den );
				return;
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				cntRow->dbl_counters[idx - 1] = val / den;
				return;
		}
		lout << "averageCounterVal: ERROR unknown counter type..." << endl;
		return;
	}

	/** Setta il contatore */
	inline void setCounterVal ( ParameterList * cntRow, int idx, double val ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				cntRow->int_counters[idx - 1] = ( unsigned long ) val;
				return;
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				cntRow->dbl_counters[idx - 1] = val;
				return;
		}
		lout << "setCounterVal: ERROR unknown counter type..." << endl;
		return;
	}

	inline void setCounterVal ( ParameterList * cntRow, int idx, unsigned long val ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				cntRow->int_counters[idx - 1] = val;
				return;
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				cntRow->dbl_counters[idx - 1] = ( double ) val;
				return;
		}
		lout << "setCounterVal: ERROR unknown counter type..." << endl;
		return;
	}

	/** Ritorna il valore del contatore (cast double)
	 */
	inline double getDoubleCounterVal ( ParameterList * cntRow, int idx ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				return ( double ) cntRow->int_counters[idx - 1];
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				return cntRow->dbl_counters[idx - 1];
		}
		lout << "getDoubleCounterVal: ERROR unknown counter type..." << endl;
		return 0.0;
	}

	inline unsigned long getIntCounterVal ( ParameterList * cntRow, int idx ) {
		switch ( getCounterType ( idx ) ) {
			case INT_TYPE:
				return cntRow->int_counters[idx - 1];
			case DOUBLE_TYPE:
				idx -= MAX_INT_COUNTERS;
				return ( unsigned long ) cntRow->dbl_counters[idx - 1];
		}
		lout << "getIntCounterVal: ERROR unknown counter type..." << endl;
		return 0.0;
	}

	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione CDR */
	void ieExport ( ImportExport & ie );

	inline void setPluginHome ( const QString & ph ) {
		pluginHome = ph;
	}

	void copyToCorba ( CountersSeq * ptr );
	void fillFromCorba ( const CountersSeq * ptr );

private:
	QMultiHash<QString, Counters *> * Counterss;		/* Elementi hush table */
	int CounterssNumber;
	char pathname[PATH_MAX];				/* file name + path */
	unsigned long uniq;
	QDateTime currentVersionDate;
	QString sectionDescription;
	int loadCnt;
	QString pluginHome;

	/** Ritorna il valore del campo richiesto (ristretto a valori numeri interi/virgola mobile)
	 */
	unsigned long getCDRIntValue ( AdamsCompleteConfig * ncc, QueryParams * params, DRImageHandler * CDRr, int te_offset );
	double getCDRDoubleValue ( AdamsCompleteConfig * ncc, QueryParams * params, DRImageHandler * CDRr, int te_offset );
	PluginImpl * getHandlerPluginInstance ( AdamsCompleteConfig * ncc, QueryParams * params, DataElement * te );

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};

#endif

// kate: indent-mode cstyle; indent-width 8; replace-tabs off; tab-width 8;
