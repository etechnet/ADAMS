/***************************************************************************
                          executionthread.cpp  -  description
                             -------------------
    begin                : Wed Jan 31 2001
    copyright            : (C) 2001 by Salvatore Sannino
    email                : salvatore.sannino@j-linux.it
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "executionthread.h"
#include "ntmshared/definerelation.h"
#include "applogger.h"
#include "ntmshared/ntmglobals.h"
#if defined(P_USE_ORBIX)
#include <omg/CosNaming.hh>
#elif defined(P_USE_TAO)
#include <orbsvcs/CosNamingC.h>
#else
#error "You must #define P_USE_TAO or P_USE_ORBIX"
#endif
#include "globalobjs.h"
#include <Qt/qstringlist.h>
#include <Qt/qmap.h>

// static QSemaphore running_coarse(MAX_CEN);

extern bool globalTerminationRequest;


ExecutionThread::ExecutionThread ( QString Cen,
                                   NtmCompleteConfig * ntmp,
                                   AMS_ALARM_RESOURCE * alm_res,
                                   int n_alm,
                                   SafeCounter * a_safe_counter,
				   int sid
                                 )
		: Centrale ( Cen ),
		ntm ( ntmp ),
		alarm_resource ( alm_res ),
		num_alarms ( n_alm ),
		safe_counter ( a_safe_counter ),
		serverID(sid)
{
	status = Starting;
	mainInvokeStatus = 0;

}

ExecutionThread::~ExecutionThread()
{
}

void ExecutionThread::run()
{
	if ( mainInvokeStatus == 0 )
		mainInvokeStatus = new LOC_INVOKE_STATUS;
	try {
		lout << "Calling AlarmDataServer for " << Centrale << endl;

		int argc = 0;		// fake parameters
		char **argv = 0;	//
		CORBA::Object_var obj;


		CosNaming::NamingContext_var rootContext;

		CORBA::Object_var objVar;

		try {
			lout2 << "Obtaining reference to the NameService." << endl;
			objVar = GlobalObjs::orb()->resolve_initial_references ( "NameService" );
			rootContext = CosNaming::NamingContext::_narrow ( objVar );
		}
		catch ( CORBA::SystemException &sysEx ) {
			lout << "Unexpected system exception during resolve_initial_references:"
			<< endl;
//			lout << sysEx;
			return;
		}
		catch ( ... ) {
			lout << "Operation resolve_initial_references() failed." << endl;
			lout << "Unexpected exception." << endl;
			return;
		}

		if ( CORBA::is_nil ( rootContext.in() ) ) {
			lout << "CosNaming::NamingContext::_narrow returned a nil object reference" << endl;
			return;
		}

		CosNaming::Name_var tmpName;
		tmpName = new CosNaming::Name ( 4 );
		QString dserver_name = QString("AlarmDataServer") + QString::number(serverID);

		try {
			tmpName->length ( 4 );

			tmpName[0].id = CORBA::string_dup ( "STS" );
			tmpName[0].kind = CORBA::string_dup ( "" );
			tmpName[1].id = CORBA::string_dup ( Centrale.latin1() );
			tmpName[1].kind = CORBA::string_dup ( "" );
			tmpName[2].id = CORBA::string_dup ( "IMAGE" );
			tmpName[2].kind = CORBA::string_dup ( "" );
			tmpName[3].id = CORBA::string_dup ( qPrintable(dserver_name) );
			tmpName[3].kind = CORBA::string_dup ( "" );
			obj = rootContext->resolve ( tmpName );

		}
		catch ( CosNaming::NamingContext::NotFound& ) {
			lout << "Could not resolve "
			<< tmpName[0].id << "/"
			<< tmpName[1].id << "/"
			<< tmpName[2].id << "/"
			<< tmpName[3].id
			<< endl;
		}
		catch ( CosNaming::NamingContext::InvalidName& ) {
			lout << "Invalid name on CosNaming::NamingContext::bind." << endl;
		}
		catch ( CORBA::SystemException &sysEx ) {
			lout << "Unexpected System Exception:" << endl;
//			lout << sysEx;
		}
		catch ( ... ) {
			lout << "Unexpected exception." << endl;
		}

		lout2 << "Narrowing reference to factory" << endl;
		AlarmDataServer_var almDS = AlarmDataServer::_narrow ( obj );

		sleep ( 2 );

		NTM_COMPLETE_wrapper crbntm;
		crbntm.fillFrom ( ntm );

		ALARM_PARAMS ds_params;
		memset(ds_params.relationList, 0, sizeof(ds_params.relationList));

		for (int i = 0; i < num_alarms; i++) {
			ds_params.relationList[i] = alarm_resource[i].id;
		}
		ds_params.masterServerID = serverID;

		int iPercent;
		int nRetry = 4;
		bool inretry = false;

		while ( nRetry > 0 ) {
			try {
				if ( CORBA::is_nil ( almDS ) ) {
					lout << "Object \"STS/" << Centrale << "/NTM/DataServer\" in the naming service"
					<< endl << "\tis not of the expected type."
					<< endl;
					status = Failure;
					return;
				}
				almDS->runDataServer ( * crbntm.reference(), ds_params );
				nRetry = 0;
			}
			catch ( const CORBA::TRANSIENT & lf ) {
				lout << "+++ GOT CORBA::TRANSIENT invoking DataServer...: " << endl
				<< "minor : " << lf.minor() << endl
				<< "name  : " << lf._name() << endl
				<< "compl.: " << lf.completed() << endl
#ifdef P_USE_TAO
				<< "info: " << lf._info().c_str() << endl;
#else
				<< "reason: " << lf._it_reason_text() << endl;
#endif
				inretry = true;
				if ( --nRetry <= 0 )
					return ;
			}
			catch ( const CORBA::OBJECT_NOT_EXIST & lf ) {
				lout << "+++ GOT CORBA::OBJECT_NOT_EXIST invoking DataServer...: " << endl
				<< "minor : " << lf.minor() << endl
				<< "name  : " << lf._name() << endl
				<< "compl.: " << lf.completed() << endl
#ifdef P_USE_TAO
				<< "info: " << lf._info().c_str() << endl;
#else
				<< "reason: " << lf._it_reason_text() << endl;
#endif
				inretry = true;
				if ( --nRetry <= 0 )
					return ;
			}
		}

		status = Running;
		sleep ( 5 );

		do {
			if ( globalTerminationRequest ) {
				status = Success;
				return;
			}
			iPercent = almDS->getProgressPercentage ( Btree_id, BtreeDataSt.out() );
			lout1 << "Percentuale di avanzamento: " << iPercent << endl;
			mainInvokeStatus->percent[cenIndex] = ( iPercent > 100 ) ? 100 : iPercent;
			if ( iPercent == 998 ) {		// 998 significa errore grave: nessun thread in esecuzione
				almDS->ShutDown();
				status = Failure;
				mainInvokeStatus->percent[cenIndex] = -1;
				return;
			}
			sleep ( 5 );
		}
		while ( iPercent != 999 );

		almDS->ShutDown();
		copyBtree();
		status = Success;
		return;
	}
	catch ( const CORBA::SystemException &e ) {
		lout << "+++ Unexpected SystemException of recognized type: " << endl
		<< "minor : " << e.minor() << endl
		<< "name  : " << e._name() << endl
		<< "compl.: " << e.completed() << endl
#ifdef P_USE_TAO
		<< "info: " << e._info().c_str() << endl;
#else
		<< "reason: " << e._it_reason_text() << endl;
#endif
		status = Failure;
		return ;
	}
	catch ( const CORBA::Exception &e ) {
		lout << "+++ Unexpected exception of recognized type " << e._name() << endl;
		status = Failure;
		return ;
	}
	catch ( ... ) {
		lout << "+++ Unexpected exception: general failure" << endl;
		status = Failure;
		return ;
	}
}


void ExecutionThread::copyBtree()
{
	bool isUserManaged = false;
	int userDataSize = 0;
	int int_n, dbl_n;
	int int_cnt = 0;
	int dbl_cnt = 0;

	dout1 << "*** Ho ricevuto l'albero binario da "
	<< Centrale << ": file_data.length(): " << BtreeDataSt->length() << endl;

	QMap<int, int> id_to_vect;
	RTNodeMap node_map;
	RTNodeMapIterator node_map_iterator;

	for ( int i = 0; i < num_alarms; i++ ) {
		if ( id_to_vect.find ( alarm_resource[i].id ) == id_to_vect.end() ) {  // avoid dups
			id_to_vect.insert ( alarm_resource[i].id, i );
			lout3 << i << " -> " << alarm_resource[i].id << endl;
		}
		else
			continue;
	}

	AlarmStatusNode * al_node;
	Nodo * nodo = 0;
	unsigned char * userArea;

	// constructs local alarm map for new data

	for ( int i = 0; i < BtreeDataSt->length(); i++ ) {			// copia albero

		QString DirectKey = ( *BtreeDataSt ) [i].Chiave;
// 		lout3 << "DirectKey=[" << DirectKey << "]" << endl;

		if ( DirectKey.isNull() || DirectKey.isEmpty() )
			continue;

		int rel_id = btree_alarm_key_rel_id ( DirectKey );
		int a_idx = id_to_vect.value ( rel_id );

		if ( a_idx >= num_alarms ) {
			lout << "Misconfigured node relation id " << rel_id << " @ position " << a_idx
			<< ". (size = " << id_to_vect.size() << ") Skipped."
			<< endl;
			continue;
		}

		isUserManaged = ( alarm_resource[a_idx].userDataSize > 0 );

		if ( isUserManaged ) {
			//TODO: make userdata functional...
// 			userDataSize = alarm_resource[a_idx].userDataSize;
// 			nodo = new Nodo( userDataSize );
// 			userArea = ( unsigned char * ) nodo->GetLista()->usr_data;

			lout1 << "USER BLOCK SIZE " << userDataSize << endl;
		}
		else {
			int_n = alarm_resource[a_idx].n_int;		// config. contatori
			dbl_n = alarm_resource[a_idx].n_dbl;
			nodo = new Nodo ( int_n, dbl_n );
			nodo->setupCounters();
		}

		if ( isUserManaged ) {
			//TODO: make userdata functional...
			lout << "*** user data not supported... Skipping" << endl;

			if ( nodo ) delete nodo;
			break;

// 			for ( int c = 0; c < userDataSize; c++ ) {
// 				userArea[c] = ( *BtreeDataSt ) [i].userPool[c];
// 			}
		}
		else {
			// copia contatori del nodo
			if ( ( *BtreeDataSt ) [i].ParamSeq.length() != ( int_n + dbl_n ) ) {
				lout << "Misconfigured node relation id " << rel_id << ": received "
				<< ( *BtreeDataSt ) [i].ParamSeq.length() << " counters instead of "
				<< ( int_n + dbl_n ) << "expected. Skipped."
				<< endl;
				break;
			}

			int_cnt = 0;
			dbl_cnt = 0;
			for ( int j = 0; j < ( *BtreeDataSt ) [i].ParamSeq.length(); j++ ) {
				switch ( ( *BtreeDataSt ) [i].ParamSeq[j].Tipo ) {
					case 0:
						nodo->GetLista()->int_counters[int_cnt++] = ( *BtreeDataSt ) [i].ParamSeq[j].Valore.Intero();
						break;
					case 1:
						nodo->GetLista()->dbl_counters[dbl_cnt++] = ( *BtreeDataSt ) [i].ParamSeq[j].Valore.Decimale();
						break;
					default:	// ???
						break;
				}
			}									// inser. chiave + contatori
		}

		node_map_iterator = node_map.find ( DirectKey );

		if ( node_map_iterator == node_map.end() ) {
			Nodo * m_nodo = new Nodo ( int_n, dbl_n );
			m_nodo->setupCounters();
			*m_nodo += *nodo;
			node_map.insert ( DirectKey, m_nodo );
			lout3 << "rt summed" << endl;
		}
		else {
			Nodo * m_nodo = node_map_iterator.value();
			*m_nodo += *nodo;
			lout3 << "rt added" << endl;
		}

		delete nodo;
		nodo = 0;

	}

	int current_relation = -1;
	node_map_iterator = node_map.begin();
	RTNodeMapIterator m_itr;
	RTNodeMap rtmap;
	unsigned long num_nodes = 0;
	int m_rel_id;

	m_mutex.lock();

	// merge local data with global alarm map and save it on file
	// (serialized operation)

	while ( node_map_iterator != node_map.end() ) {

		QString DirectKey = node_map_iterator.key();
		m_rel_id = btree_alarm_key_rel_id ( DirectKey );
// 		lout3 << "Merging=[" << DirectKey << "]" << endl;
		
		if ( m_rel_id != current_relation ) {
			if ( current_relation != -1 ) {
				dout2 << Centrale << ": merge complete " << rtmap.size() << " nodes processed." << endl;
				num_nodes += rtmap.size();

				rtmap.unloadRTRelationMap ( current_relation, alarm_resource, num_alarms );
			}
			current_relation = m_rel_id;
			lout2 << Centrale << ": Updating alarms btree for rel " << m_rel_id
			<< " " << ntm->alarmRelationInterface->decodeRel ( m_rel_id ) << endl;
			rtmap.loadRTRelationMap ( m_rel_id, alarm_resource, num_alarms );
		}

		m_itr = rtmap.find ( DirectKey );

		if ( m_itr == rtmap.end() ) {
			Nodo * nodo = new Nodo ( int_n, dbl_n );
			nodo->setupCounters();
			* ( nodo ) = *node_map_iterator.value();
			rtmap.insert ( DirectKey, nodo );
		}
		else {
			*m_itr.value() += *node_map_iterator.value();
		}

		++node_map_iterator;
	}

	dout2 << Centrale << ": merge complete " << rtmap.size() << " nodes processed." << endl;
	num_nodes += rtmap.size();

	rtmap.unloadRTRelationMap ( current_relation, alarm_resource, num_alarms );

	m_mutex.unlock();

	dout2 << "*** " << Centrale << " full merge complete: " << num_nodes << " nodes total." << endl;

	// update switching node counters for network analysis
	safe_counter->increment();

	foreach (Nodo * nodo, node_map)
		delete nodo;
	node_map.clear();

	return;
}


