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

#include "executionthread.h"
#include <definerelation.h>
#include <applogger.h>
#include <corbaclient.h>
#include "pivotfile.h"
#include <Qt/qstringlist.h>

ExecutionThread::ExecutionThread(STRUCT_PARAMS U_Input,
                                 QString Fep,
                                 QueryParams & up,
                                 AdamsCompleteConfig * acc_p,
                                 BTreeNetworkRT * BtreeNetRT_I,
                                 PivotFile * pivotF)
{
	FepName = Fep;
	analisys_type = up.data.AnalysisType;
	UserInput = U_Input;
	acc = acc_p;
	UserParameter = up;
	BtreeNetRT_Unsort = BtreeNetRT_I;
	pivotFile = pivotF;

	status = Starting;
	global_AgentsRunStatus = 0;

}

ExecutionThread::~ExecutionThread()
{
}

void ExecutionThread::run()
{
	if (global_AgentsRunStatus == 0)
		global_AgentsRunStatus = new MDMAgentCallStatus;

	try {
		CorbaClient<mdm_agent_factory_server> agent_factory;
		if ( !agent_factory.connect ( "mdm_agent_factory", FepName ) ) {
			lout << FepName << ": agent connection failed." << endl;
			return;
		}

		sleep(2);

		ADAMS_COMPLETE_wrapper crbntm;
		crbntm.fillFrom(acc);

		int iPercent;
		int nRetry = 4;
		mdm_agent_server_var agent;
		bool inretry = false;

		while (nRetry > 0) {
			try {
				agent = agent_factory->get_server(inretry);

				if (CORBA::is_nil(agent)) {
					lout << "Invalid connection to " << FepName << " agent." << endl;
					status = Failure;
					return;
				}
				else
					lout1 << "Got " << FepName << " agent reference" << endl;

				agent->startMDMAgent(UserInput, *crbntm.reference());
				nRetry = 0;
			}
			catch (const CORBA::TRANSIENT & lf) {
				lout << "Got CORBA::TRANSIENT for " << FepName << " reason:" << lf._info().c_str() << endl;
				inretry = true;
				if (--nRetry <= 0)
					return ;
			}
			catch (const CORBA::OBJECT_NOT_EXIST & lf) {
				lout << "Got CORBA::OBJECT_NOT_EXIST for " << FepName << " reason:" << lf._info().c_str() << endl;
				inretry = true;
				if (--nRetry <= 0)
					return ;
			}
		}

		status = Running;
		sleep(5);

		do {
			iPercent = agent->getProgressPercentage(Btree_id, BtreeDataSt);
// 			lout3 << FepName << ": progress " << iPercent << "%" << endl;
			global_AgentsRunStatus->percent[fepIndex] = (iPercent > 100) ? 100 : iPercent;
			if (iPercent == 998) {		// 998 fatal error received
				agent->shutDown();
				status = Failure;
				global_AgentsRunStatus->percent[fepIndex] = -1;
				return;
			}
			sleep(5);
		} while(iPercent != 999);

		agent->shutDown();
		CopyBtreeStandardReport();
		status = Success;
		return;
	}
	catch (const mdm_agent_factory_server::LaunchFailure & lf) {
		lout << "+++ Agent launch failure: " << lf.reason << endl;
		status = Failure;
		return;
	}
	catch (const CORBA::SystemException &e) {
		lout << "+++ Unexpected CORBA SystemException:" << e._info().c_str() << endl;
		status = Failure;
		return ;
	}
	catch (const CORBA::Exception &e) {
		lout << "+++ Unexpected exception: " << e._name() << endl;
		status = Failure;
		return ;
	}
	catch (...) {
		lout << "+++ Unexpected exception: general failure" << endl;
		status = Failure;
		return ;
	}
}


void ExecutionThread::CopyBtreeStandardReport()
{
	char DirectKey[MAX_KEY_LENGTH];
	char InverseKey[MAX_KEY_LENGTH];
	char FirstKey[MAX_KEY_LENGTH];
	char SecondKey[MAX_KEY_LENGTH];
	char SortKey[MAX_KEY_LENGTH];
	int sortIndex = UserParameter.data.ElementToSort - 1;
	int percIndex = -1;
	int percType = -1;
	BtreeMap::Iterator it;
	DefineRelation DefRel;
	RelationComponent * aiElemenInRelation;
	bool isUserManaged = false;
	int userDataSize = 0;
	int int_n, dbl_n;
	int int_cnt = 0;
	int dbl_cnt = 0;
	bool ghostRelation = false;
	int klen1 = 0;
	int klen2 = 0;

	lout << "*** Btree completed: file_data.length(): " << BtreeDataSt->length() << endl;

	bool isPivot = (UserParameter.data.OutputType == usrOutputType_Pivot || UserParameter.data.OutputType == usrOutputType_Export);
	if (isPivot) lout << "ANALISI PIVOT" << endl;

	relation * r = acc->relationInterface->get(UserParameter.data.Relation);
	if (r == 0) {
		lout << "ExecutionThread::CopyBtreeStandardReport : Invalid user params : NO relation." << endl;
		return;
	}

	if (r->data.ghostRelation) {
		ghostRelation = true;
	}

	if (ghostRelation == false) {
		DefRel.setElementInRelation(acc, UserParameter.data.Relation, UserParameter.data.ffRelation);
		aiElemenInRelation = DefRel.getRelationComponentsArray();

			// copia in locale

		klen1 = aiElemenInRelation[0].length;		// lunghezza chiavi rel. primo livello
		klen2 = aiElemenInRelation[1].length;
	}

	if (qstrcmp((*BtreeDataSt)[0].Key, "--USERNODES--") == 0)
		isUserManaged = true;

	if (isUserManaged) {
		userDataSize = (*BtreeDataSt)[0].ParamSeq[0].Value.Integer();
		Nodo::setupUserDataSize(userDataSize);
// 		lout <<"USER BLOCK SIZE "<<userDataSize<<endl;
	}
	else {
		int_n = (*BtreeDataSt)[0].ParamSeq[0].Value.Integer();		// config. contatori
		dbl_n = (*BtreeDataSt)[0].ParamSeq[1].Value.Integer();
		Nodo::setCountersSize(int_n, dbl_n);
		lout3 <<"counters dim i("<<int_n<<") d("<<dbl_n<<")"<<endl;

		Counters * cnts = 0;

		if (Analysis * an = acc->analysisInterface->get(analisys_type)) {
			cnts = acc->counterInterface->get(an->data.countersKitId);
			if (cnts == 0) {
				lout << "ExecutionThread::CopyBtreeStandardReport : Cannot get counters kit #" << an->data.countersKitId <<
					" from Analisys #" << analisys_type << endl;
				return;
			}
		}
		else {
			lout << "ExecutionThread::CopyBtreeStandardReport : Cannot get Analisys #" << analisys_type << endl;
			return;
		}


		if (sortIndex >= 0)
			percIndex = acc->counterInterface->getPercentOf(*cnts, sortIndex);

		if (sortIndex >= MAX_INT_COUNTERS)
			sortIndex = sortIndex - MAX_INT_COUNTERS + int_n + 1;

		if (percIndex >= 0 && UserParameter.data.IsPercent) {				// ordinamento richiesto
			if (percIndex >= int_n && percIndex < (int_n+dbl_n)) {
				percType = 0;		// double
			}
			else if (percIndex >= 0 && sortIndex < int_n) {
				percType = 1;		// integer
			}
		}
	}

	Nodo * nodo = new Nodo;
	nodo->setupCounters();
	unsigned char * userArea = (unsigned char *)nodo->GetLista()->usr_data;
	QStringVector pivotKeys(MAX_PIVOT_KEYS);

	if (isPivot && (!UserParameter.data.NetworkRealTime)) {		// store analysis lenght & fixed params

		pivotFile->setInfoLenght( BtreeDataSt->length() - 1 );
		pivotFile->setInfoInts( int_n );
		pivotFile->setInfoDbls( dbl_n );
		pivotFile->setInfoName( UserParameter.data.Name );
		pivotFile->setInfoRelation( UserParameter.data.Relation );
		pivotFile->setInfoRelationSize( DefRel.getDimension() );
		pivotFile->setInfoSwitching( fepIndex );
		pivotFile->setInfoDate( UserParameter.data.ElaborationData.last() );
		pivotFile->setInfoSchema( UserParameter.data.idReportSchema );
		pivotFile->setInfoAnalysis( UserParameter.data.AnalysisType );
		pivotFile->setInfoIsPercent( UserParameter.data.IsPercent );
		pivotFile->setInfoWithPreSort( (sortIndex >= 0 && (!UserParameter.data.NetworkRealTime)) );
		pivotFile->setInfoReversePreSort( pivotFile->getInfoWithPreSort() && !UserParameter.data.Ascendent );
		pivotFile->setInfoSortDataCol( sortIndex );
		pivotFile->setInfoSortPercCol( percIndex );
		pivotFile->setInfoRestrictions(UserParameter.data.Filters);
		pivotFile->setFfRelation(UserParameter.data.ffRelation);
		pivotFile->setReserved( UserParameter.data.Reserved );

		if ( isUserManaged ) {
			pivotFile->setUserData( true );
			pivotFile->setUserDataSize( userDataSize );
		}
		else {
			pivotFile->setUserData( false );
			pivotFile->setUserDataSize( 0 );
		}

		// fill key description
		int k_offset = 0;
		int klen0 = 0;
		if (ghostRelation) {
			// single unmanaged key
			pivotFile->setInfoKey(0, "UserKey", MAX_KEY_LENGTH, 0);
		}
		else {
			for (int i = 0; i < DefRel.getDimension(); i++) {
				pivotFile->setInfoKey(i, aiElemenInRelation[i].desc, aiElemenInRelation[i].length, k_offset);
				k_offset += aiElemenInRelation[i].length;
			}
		}

		if (pivotFile->writeInfo())
			return;

		if (pivotFile->openForWrite()) {
			return;
		}
	}

	for(int i = 1; i < BtreeDataSt->length(); i++) {			// copia albero
		if (ghostRelation) {
			qstrncpy(FirstKey, (*BtreeDataSt)[i].Key, MAX_KEY_LENGTH);
			SecondKey[0] = '\0';
			SortKey[0] = '\0';
		}
		else {
			qstrncpy(FirstKey, (*BtreeDataSt)[i].Key, klen1+1);
			qstrncpy(SecondKey, (*BtreeDataSt)[i].Key+klen1, klen2+1);
			FirstKey[klen1] = '\0';
			SecondKey[klen2] = '\0';
			SortKey[0] = '\0';
		}

		if (isUserManaged) {
			for (int c = 0; c < userDataSize; c++) {
				userArea[c] = (*BtreeDataSt)[i].userPool[c];
			}
		}
		else {
			int_cnt = 0;
			dbl_cnt = 0;
												// copia contatori del nodo
			for (int j = 0; j < (*BtreeDataSt)[i].ParamSeq.length(); j++) {
				switch ((*BtreeDataSt)[i].ParamSeq[j].Type) {
					case 0:
						nodo->GetLista()->int_counters[int_cnt++] = (*BtreeDataSt)[i].ParamSeq[j].Value.Integer();
						break;
					case 1:
						nodo->GetLista()->dbl_counters[dbl_cnt++] = (*BtreeDataSt)[i].ParamSeq[j].Value.Decimal();
						break;
					default:	// ???
						break;
				}
			}									// inser. chiave + contatori
			if (sortIndex >= 0 && (!UserParameter.data.NetworkRealTime)) {		// ordinamento richiesto
				if (sortIndex >= int_n && sortIndex < (int_n+dbl_n)) {
					switch (percType) {
						case 0:	// double referrer
							sprintf(SortKey, "%08.2f", (nodo->GetLista()->dbl_counters[sortIndex-int_n]*100.0)/nodo->GetLista()->dbl_counters[percIndex-int_n]);
							break;
						case 1: // int referrer
							sprintf(SortKey, "%08.2f", (nodo->GetLista()->dbl_counters[sortIndex-int_n]*100.0)/(double)nodo->GetLista()->int_counters[percIndex]);
							break;
						default:
							sprintf(SortKey, "%08.2f", nodo->GetLista()->dbl_counters[sortIndex-int_n]);
					}
				}
				else if (sortIndex >= 0 && sortIndex < int_n) {
					switch (percType) {
						case 0:	// double referrer
							sprintf(SortKey, "%08.2f", (nodo->GetLista()->int_counters[sortIndex]*100.0)/nodo->GetLista()->dbl_counters[percIndex-int_n]);
							break;
						case 1: // int referrer
							sprintf(SortKey, "%08.2f", (nodo->GetLista()->int_counters[sortIndex]*100.0)/(double)nodo->GetLista()->int_counters[percIndex]);
							break;
						default:
							sprintf(SortKey, "%08d", nodo->GetLista()->int_counters[sortIndex]);
					}
				}
			}
		}

		if (isPivot && (!UserParameter.data.NetworkRealTime)) {
			if (ghostRelation) {
				pivotKeys[0] = FirstKey;
			}
			else {
				int koffs = 0;
				for (int k = 0; k < DefRel.getDimension(); k++) {
					int klen = aiElemenInRelation[k].length;
					pivotKeys[k] = QString((*BtreeDataSt)[i].Key).mid(koffs,klen);
					koffs += klen;
				}
			}
		}
		else if (ghostRelation && !isPivot) {
			qstrcpy(DirectKey, FirstKey);
			InverseKey[0] = '\0';
		}
		else {
			qstrcpy(DirectKey, FirstKey);
			strcat(DirectKey, SortKey);
			if (!UserParameter.data.SingleRelation) {
				strcat(DirectKey, SecondKey);
				if (DefRel.getDimension() > 2)
					strcat(DirectKey, (*BtreeDataSt)[i].Key+klen1+klen2);
			}

			qstrcpy(InverseKey, SecondKey);
			strcat(InverseKey, SortKey);
			if (!UserParameter.data.SingleRelation) {
				strcat(InverseKey, FirstKey);
				if (DefRel.getDimension() > 2)
					strcat(InverseKey, (*BtreeDataSt)[i].Key+klen1+klen2);
			}
		}

		if (isPivot && (!UserParameter.data.NetworkRealTime)) {
			pivotFile->writeNode(pivotKeys, nodo);
		}
		else if (isUserManaged && !isPivot) {
			if (UserParameter.data.NetworkRealTime) {
				BtreeNetRT_Unsort->insert(DirectKey,*nodo);
			}
			else {
				BtreeDirect.insert(DirectKey,*nodo);
			}
		}
		else if (UserParameter.data.NetworkRealTime) {
			BtreeNetRT_Unsort->insertOrSum(DirectKey,*nodo);
		}
		else {
			if (UserParameter.data.RelationDirection == usrRelationKind_Direct || UserParameter.data.RelationDirection == usrRelationKind_Both || isPivot) {
				BtreeDirect.insertOrSum(DirectKey,*nodo);
			}
			if ((UserParameter.data.RelationDirection == usrRelationKind_Inverse || UserParameter.data.RelationDirection == usrRelationKind_Both) && !isPivot) {
				BtreeInverse.insertOrSum(InverseKey,*nodo);
			}
		}
	}
		// update switching node counters for network analysis
	if (UserParameter.data.NetworkRealTime) {
			BtreeNetRT_Unsort->incDoneInputsN();
	}
	if (isPivot && (!UserParameter.data.NetworkRealTime)) {
		pivotFile->close();
	}
	delete nodo;
	return;
}

