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


#include "collectionthread.h"
#include <applogger.h>


CollectionThread::~CollectionThread() {
}

void CollectionThread::run() {
	bool bRet;
	int iRelation;

	int nconcurrent = MAX_CONCURRENT;

	DataCollector * Build[MAX_CONCURRENT];	// La classe DataCollector deve essere istanziata dopo aver ottenuto la configurazione
	HashDict * Trees[MAX_CONCURRENT];

	Trees[0] = &Btree;
	Build[0] = new DataCollector;
	Build[0]->setBtree ( Trees[0] );

	for ( int i = 1; i < nconcurrent; i++ ) {	// ... next threads on their own btrees
		Trees[i] = new HashDict;
		Build[i] = new DataCollector;
		Build[i]->setBtree ( Trees[i] );
	}

	/*
	** Binary tree construction...
	*/

	QStringList WorkDays = StoreConf::params()->data.ElaborationData;
	int daycount = 0;
	int numdays = WorkDays.count();
	bool assigned;
	ProgressPercentage = new int [numdays];
	for ( int i = 0; i < numdays; i++ )
		ProgressPercentage[i] = 0;			// ensure clean percentages

	for ( QStringList::Iterator Data = WorkDays.begin(); Data != WorkDays.end(); ++Data ) {
		assigned = false;
		do {
			for ( int i = 0; i < nconcurrent; i++ ) {
				if ( Build[i]->isRunning() ) {
					;	// do nothing - already busy
				}
				else {
					lout << "Starting analysis for day #" << daycount << " on thread " << i << endl;
					ProgressPercentage[daycount] = 0;
					Build[i]->setProgressTeller ( &ProgressPercentage[daycount] );
					Build[i]->startExec ( *Data, daycount, numdays );
					++daycount;
					assigned = true;
					break;
				}
			}
			if ( !assigned ) {
				sleep ( 1 );	// wait for completion
			}
		}
		while ( !assigned );
		sleep ( 3 );
	}
	// scan the builder threads, waiting for every running one
	bool allfinish;
	do {
		allfinish = true;
		for ( int i = 0; i < nconcurrent; i++ ) {
			if ( !Build[i]->isStarted() )
				continue;
			if ( !Build[i]->isFinished() ) {
				allfinish = false;
				break;
			}
		}
		if ( !allfinish ) {
			sleep ( 1 );
		}
	}
	while ( !allfinish );

	lout << "Tree merge..." << endl;
	for ( int i = 1; i < nconcurrent; i++ ) {		// combine all subtrees into one (the our)
		HashDictIterator it ( *Trees[i] );
		Nodo * nodo;
		while ( it.hasNext() ) {
			it.next();
// 			if (!(nodo = Btree[it.currentKey()])) {
			if ( ! Btree.contains ( it.key() ) ) {
				Btree.insert ( it.key(), it.value() );
			}
			else {
// 				(*nodo) += (*(it.current()));
				( *Btree.find ( it.key() ).value() ) += ( *it.value() );
			}
		}
	}
// 	lout << "DSERVER: btree size after merge = " << Btree.count() << " out of " << Btree.size() << endl;

//	if (Btree.count() < 10) { 		// delay process to be sure we keep in sync with an
// 		lout << "empty tree... delaying" << endl;
//		sleep(5);			// heavy loaded masterserver
//	}

	/*
	** Copia dell'albero binario
	** nella struttura IDL.
	*/

	LocalData = new BtreeSeq;
	int iCount = 0;
	int dbl_n;
	int int_n;
	ParameterList * ptrPList;
	HashDictIterator it ( Btree );
	Nodo::getCountersSize ( int_n, dbl_n );
	bool isUserManaged = Nodo::haveUserData();
	int dataSize = Nodo::userDataSize();

	// dim counters in case of no plugin handling
	if ( dbl_n == 0 && int_n == 0 ) {			// no known size

		Analysis * analysis = StoreConf::config()->analysisInterface->get ( StoreConf::params()->data.AnalysisType );
		int cntKitId = ( analysis ) ? analysis->data.countersKitId : 0;
		Counters * counters = StoreConf::config()->counterInterface->get ( cntKitId );

		if ( !counters->data.usePlugin ) {

			StoreConf::config()->counterInterface->getConfiguredCounters ( counters, int_n, dbl_n );
		}
	}

	LocalData->length ( Btree.count() + 1 );					// number of keys plus one for counter config

	if ( isUserManaged ) {
		strcpy ( ( *LocalData ) [iCount].Key, "--USERNODES--" );		// write back counters config
		( *LocalData ) [iCount].ParamSeq.length ( 1 );
		( *LocalData ) [iCount].ParamSeq[0].Type = 0;
		( *LocalData ) [iCount].ParamSeq[0].Value.Integer ( dataSize );

		++iCount;
		while ( it.hasNext() ) {					// copy the whole tree
			it.next();
			unsigned char * userPtr = ( unsigned char * ) it.value()->GetLista()->usr_data;
			memset ( ( *LocalData ) [iCount].Key, 0, MAX_KEY_LENGTH );
			strcpy ( ( *LocalData ) [iCount].Key, qPrintable(it.key().left ( MAX_KEY_LENGTH - 1 )) );
			( *LocalData ) [iCount].userPool.length ( dataSize );
			for ( int i = 0; i < dataSize; i++ ) {
				( *LocalData ) [iCount].userPool[i] = userPtr[i];
			}
			++iCount;
		}

		/*		lout << "Copiati " << iCount << " USER BLOCKS" << endl;
				lout << "of size: " << dataSize << endl;*/
		jobComplete = true;	// means: end of job
		return;

	}
	else {
		strcpy ( ( *LocalData ) [iCount].Key, "--COUNTERS--" );		// write back counters config
		( *LocalData ) [iCount].ParamSeq.length ( 2 );
		( *LocalData ) [iCount].ParamSeq[0].Type = 0;
		( *LocalData ) [iCount].ParamSeq[0].Value.Integer ( int_n );
		( *LocalData ) [iCount].ParamSeq[1].Type = 0;
		( *LocalData ) [iCount].ParamSeq[1].Value.Integer ( dbl_n );
		++iCount;
		while ( it.hasNext() ) {					// copy the whole tree
			it.next();
			ptrPList = it.value()->GetLista();
			memset ( ( *LocalData ) [iCount].Key, 0, MAX_KEY_LENGTH );
			strcpy ( ( *LocalData ) [iCount].Key, qPrintable(it.key().left ( MAX_KEY_LENGTH - 1 )) );
			( *LocalData ) [iCount].ParamSeq.length ( int_n + dbl_n );
			int start_ptr = 0;
			for ( int i = 0; i < int_n; i++ ) {
				( *LocalData ) [iCount].ParamSeq[i].Type = 0;
				( *LocalData ) [iCount].ParamSeq[i].Value.Integer ( ptrPList->int_counters[i] );
				start_ptr = i;
			}
			++start_ptr;
			for ( int i = 0; i < dbl_n; i++ ) {
				( *LocalData ) [iCount].ParamSeq[i + start_ptr].Type = 1;
				( *LocalData ) [iCount].ParamSeq[i + start_ptr].Value.Decimal ( ptrPList->dbl_counters[i] );
			}
			++iCount;
		}

		lout << "Queued " << iCount - 1 << " cdrs of " << ( int_n + dbl_n ) << " counters." << endl;
// 		lout << "LocalData->length: " << LocalData->length() << endl;
		jobComplete = true;	// means: end of job

// 		if (LocalData->length() < 20) {
// 			for (int i = 0; i < LocalData->length(); i++) {
// 				lout << "KEY#" << i << "["  << (*LocalData)[i].Chiave << "]" << endl;
// 			}
// 		}

		return;
	}
}

