///***************************************************************************
//                          executionthread.h  -  description
//                             -------------------
//    begin                : Wed Jan 31 2001
//    copyright            : (C) 2001 by Piergiorgio Betti
//    email                : pbetti@lpconsul.net
// ***[ History ]*************************************************************
//
//   - Date - - By ---------------- - What -----------------------------------
// ***************************************************************************/
#ifndef EXECUTIONTHREAD_H
#define EXECUTIONTHREAD_H

#include <Qt/qthread.h>
#include <adams_limits.h>
#include <nodo.h>
#include <configuretypedefs.h>
#include <mdmS.h>
#include <queryparams.h>
#include <btreenetworkrt.h>


using namespace net::etech;
using namespace net::etech::MDM;


class PivotFile;

typedef struct {
	int percent[MAX_FEP];
	char desc[MAX_FEP][512];
	char url[MAX_FEP][512];
} MDMAgentCallStatus;

/** Implementa i thread e le operazioni necessarie per l'esecuzione delle analysis multinodo e
  * realtime network. Le funzionalita' di questa classe sono attivate da un shell esterna con funzionalita'
  * di listener CORBA. A Fronte di una richiesta di report i cui parametri sono contenuti in una struttura STRUCT_PARAMS
  * viene generata una richiesta, ovvero inovcato un metodo remoto CORBA, ad un processo ntmdataserver che avra' il compito
  * di eseguire materialmente l'elaborazione dei dati.
  * Tutte le operazioni di invocazione remota, ricezione e controllo dei dati sono effettuate all'interno di un thread: e' infatti
  * prevista l'istanza di piu' oggetti ExecutionThread ognuno dei quali provvede alla richiesta verso uno specifico nodo
  * target. Nel caso di analysis di tipo network gli alberi binari ricevuti vengono accodati da ogni istanza in una BtreeMap
  * referenziata esternamente.
  *@short Attivazione server remoti
  *@author Piergiorgio Betti <pbetti@lpconsul.net>
  */


class ExecutionThread : public  QThread
{
public:
		/** Definizione dei possibili stati del thread esecutore */
	enum etStatus {
		Starting,
		Running,
		Success,
		Failure
	};

	/** Costruttore della classe. Esegue il setup (copia) delle configurazioni NTM ed i parametri di esecuzione
	  * della richiesta.
	  *@param U_Input Struttura di comando.
	  *@param Cen Nome della centrale oggetto della elaborazione.
	  *@param iTipoA Tipo dell'analysis.
	  *@param DRF Configurazione cartellino.
	  *@param RelFormat Configurazione relazioni.
	  *@param EleFormat Configurazione elementi.
	  *@param cntFormat Configurazione contatori traffico
	  *@param BtreeNetRT_D BTreeNetworkRT esterna con chiavi dirette
	  *@param BtreeNetRT_I BTreeNetworkRT esterna con chiavi inverse
	  */
	ExecutionThread(STRUCT_PARAMS U_Input,
	                QString Cen,
	                QueryParams & up,
	                AdamsCompleteConfig * ntmp,
	                BTreeNetworkRT * BtreeNetRT_I,
	                PivotFile * pivotF = 0);
	~ExecutionThread();

	/** Porzione attiva del thread. Questo metodo istanzia un ntmdataserver sul nodo target e rimane in attesa
	  * del completamento della richiesta. L'esecuzione della richiesta remota avviene in modalita' asincrona.
	  * Lo stato di anavnzamento viene monitorato ad intervalli regolari eseguendo un poll sul remoto di richiesta della
	  * percentuale di avanzamento. Percentuali di avanzamento ricevute >= 900 assumono significati particolari, ovvero:

	  * 999	- Analisi completata.

	  * 998 - Errore di protocollo: richiesta una percentuale di avanzamento senza attivazione di una analysis.

	  * 997 - Errore nei dati.

	  * In ogni caso terminata la ricezione dei dati viene generata una richiesta di shutdown al server remoto.
	  */
	virtual  void run();
		/** Questo metodo esegue le operazioni di post-processing necessarie dopo la ricezione dei dati da parte del
		  * server remoto. In sostanza la chiave ed il pacchetto di contatori di ogni viene ricevuto dal data server
		  * sotto forma di una unbounded sequence CORBA, e' quindi necessario ricomporre questi dati in una NTMMap
		  * (albero binario). Vengono inoltre eseguite per questo tipo di analysis alcune ulteriori operazioni che sono:

		  * - la generazione delle chiavi in modalita' inversa. (Scambio delle due sottochiavi di primo e secondo livello).

		  * - il sort dell'albero in base alla selezione utente ed alla configurazione dei contatori
		  */
	void CopyBtreeStandardReport();
		/** Questo metodo dovrebbe effettuare operazioni di post-processing sui dati delle analysis di tipo
		  * call monitoring. Tuttavia al momento non e' prevista alcune ulteriore elaborazione: i dati ricevuti vengono
		  * direttamente ritornati al chiamante. Riservato per uso futuro.
		  */
	inline BtreeMap * getBtreeDirect(){return &BtreeDirect;}
		/** Ritorna una referenza alla NTMMap (chiavi inverse) contenente i dati dell'analysis effettuata */
	inline BtreeMap * getBtreeInverse(){return &BtreeInverse;}
		/** Ritorna lo stato real-time dell'esecuzione del thread come definiti in @ref etStatus */
	inline int getStatus() { return status; }
		/** Utilizzato dal chiamante per definire il nodo target sul quale eseguire l'analysis
		  * @param idx Indice della centrale come definito in CENTRALI.DAT
		  */
	inline void setIndex(int idx) { fepIndex = idx; }
		/** Questo metodo imposta la referenza esetrna ai contatori di avanzamento */
	inline void useProgressCounters(MDMAgentCallStatus * p) { global_AgentsRunStatus = p; }
		/** Metodo utilizzato per restituire il file pivot attualmente in uso */
	inline PivotFile * getPivotFile() { return pivotFile; }


private:
	QString FepName;
	int analisys_type;
	AdamsCompleteConfig * acc;
	STRUCT_PARAMS UserInput;
	BtreeSeq * BtreeDataSt;
	BtreeID Btree_id;
	int status;
	int fepIndex;
	MDMAgentCallStatus * global_AgentsRunStatus;
	QueryParams UserParameter;

	BTreeNetworkRT BtreeDirect;    	// map of Btree
	BTreeNetworkRT BtreeInverse;    	// map of Btree
	BTreeNetworkRT * BtreeNetRT_Unsort;
	PivotFile * pivotFile;
};

#endif

