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

#ifndef COLLECTIONTHREAD_H
#define COLLECTIONTHREAD_H

#include <Qt/qthread.h>
#include "datacollector.h"


class CollectionThread : public QThread  {
public:
	CollectionThread() : ProgressPercentage(0), jobComplete(false) {};
	~CollectionThread();
	/** Avvia il thread di lancio dei threads giornalieri */
	virtual void run();
	/** Questo metodo torna un puntatore alla struttura dati elaborata */
	BtreeSeq * getBtree(){return LocalData;}
	/** Questo metodo torna un booleano che indica il completamento dell'analysis.
	  @return True: analysis completata
	  */
	inline bool complete() { return jobComplete; }
	/** Ritorna un puntatore alla variabile intera che contiene la percentuale di completamento
	    dell'analysis cosi' come viena valutata dalla somma delle attivita' dei threads giornalieri
	   */
	inline const int * getProgress() { return ProgressPercentage; }
private:
	HashDict Btree;
	BtreeSeq *LocalData;
	int * ProgressPercentage;
	bool jobComplete;
};

#endif
