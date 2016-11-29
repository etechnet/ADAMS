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
#include <Qt/qmutex.h>
#include <Qt/qsemaphore.h>
#include <Qt/qwaitcondition.h>
#include <Qt/qmutex.h>

#include "ntmshared/ntmlimits.h"
#include "ntmshared/nodo.h"
#include "ntmshared/configuretypedefs.h"
#if defined(P_USE_ORBIX)
 #include <ntmshared/ntmdataserverS.hh>
#elif defined(P_USE_TAO)
 #include <ntmshared/ntmdataserverS.h>
#else
 #error "You must #define P_USE_TAO or P_USE_ORBIX"
#endif
#include "ntmshared/ntmparams.h"
#include "ntmshared/ntmmap.h"
#include "ntmshared/nodo.h"
#include "ntmshared/btreealarms.h"
#include "ntmshared/ams_alarm_resource.h"

class PivotFile;

typedef struct {
	int percent[MAX_CEN];
	char desc[MAX_CEN][512];
	char url[MAX_CEN][512];
} LOC_INVOKE_STATUS;

/** Implementa i thread e le operazioni necessarie per l'esecuzione delle analisi multinodo e
  * realtime network. Le funzionalita' di questa classe sono attivate da un shell esterna con funzionalita'
  * di listener CORBA. A Fronte di una richiesta di report i cui parametri sono contenuti in una struttura STRUCT_PARAMS
  * viene generata una richiesta, ovvero inovcato un metodo remoto CORBA, ad un processo ntmdataserver che avra' il compito
  * di eseguire materialmente l'elaborazione dei dati.
  * Tutte le operazioni di invocazione remota, ricezione e controllo dei dati sono effettuate all'interno di un thread: e' infatti
  * prevista l'istanza di piu' oggetti ExecutionThread ognuno dei quali provvede alla richiesta verso uno specifico nodo
  * target. Nel caso di analisi di tipo network gli alberi binari ricevuti vengono accodati da ogni istanza in una BtreeMap
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
	  */
	ExecutionThread(QString Cen,
	                NtmCompleteConfig * ntmp,
			AMS_ALARM_RESOURCE * alm_res,
			int n_alm,
			SafeCounter * a_safe_counter,
			int sid
	                );
	~ExecutionThread();

	/** Porzione attiva del thread. Questo metodo istanzia un ntmdataserver sul nodo target e rimane in attesa
	  * del completamento della richiesta. L'esecuzione della richiesta remota avviene in modalita' asincrona.
	  * Lo stato di anavnzamento viene monitorato ad intervalli regolari eseguendo un poll sul remoto di richiesta della
	  * percentuale di avanzamento. Percentuali di avanzamento ricevute >= 900 assumono significati particolari, ovvero:

	  * 999	- Analisi completata.

	  * 998 - Errore di protocollo: richiesta una percentuale di avanzamento senza attivazione di una analisi.

	  * 997 - Errore nei dati.

	  * In ogni caso terminata la ricezione dei dati viene generata una richiesta di shutdown al server remoto.
	  */
	virtual  void run();
		/** Questo metodo esegue le operazioni di post-processing necessarie dopo la ricezione dei dati da parte del
		  * server remoto. In sostanza la chiave ed il pacchetto di contatori di ogni viene ricevuto dal data server
		  * sotto forma di una unbounded sequence CORBA, e' quindi necessario ricomporre questi dati in una NTMMap
		  * (albero binario). Vengono inoltre eseguite per questo tipo di analisi alcune ulteriori operazioni che sono:

		  * - la generazione delle chiavi in modalita' inversa. (Scambio delle due sottochiavi di primo e secondo livello).

		  * - il sort dell'albero in base alla selezione utente ed alla configurazione dei contatori
		  */
	void copyBtree();
		/** Ritorna lo stato real-time dell'esecuzione del thread come definiti in @ref etStatus */
	inline int getStatus() { return status; }
		/** Utilizzato dal chiamante per definire il nodo target sul quale eseguire l'analisi
		  * @param idx Indice della centrale come definito in CENTRALI.DAT
		  */
	inline void setIndex(int idx) { cenIndex = idx; }
		/** Questo metodo imposta la referenza esetrna ai contatori di avanzamento */
	inline void useProgressCounters(LOC_INVOKE_STATUS * p) { mainInvokeStatus = p; }

private:
	QString Centrale;
	NtmCompleteConfig * ntm;
	BtreeSeq_var BtreeDataSt;
	BtreeID Btree_id;
	int status;
	int cenIndex;
	LOC_INVOKE_STATUS * mainInvokeStatus;
	AMS_ALARM_RESOURCE * alarm_resource;
	int num_alarms;
	QMutex m_mutex;
	SafeCounter * safe_counter;
	int serverID;
};

#endif

