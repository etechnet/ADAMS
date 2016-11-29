/***************************************************************************
                          api_database.ecpp  -  description
                             -------------------
    begin                : Thu Nov 15 2001
    copyright            : (C) 2001 by Raffaele Ficcadenti
    email                : rficcad@tin.it
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/



/*#include <stdlib.h>
#include <string.h>*/

#include "api_database.h"
#include <Qt/qdatetime.h>

#include <Global_Define.h>

#define DEBUG_APIDB_HR	1



API_DataBase_alarmmasterserver::API_DataBase_alarmmasterserver ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
{
	bool noDbError = false;
	last_result_db = last_result_query = 0;



	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;

	db = QSqlDatabase::addDatabase ( "QOCI" );

	db.setDatabaseName ( strDBNAME );
	db.setUserName ( strUSER );
	db.setPassword ( strPSWD );

	noDbError = db.open();
	setError ( db );

	if ( noDbError == false ) { // error check
		showError ( "API_DataBase_alarmmasterserver", db );

	}
	else {
		lout1 << "CONNECTED TO ORACLE." << endl;
	}


	return ;

}

API_DataBase_alarmmasterserver::~API_DataBase_alarmmasterserver()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

void API_DataBase_alarmmasterserver::setError ( QSqlQuery &queryError )
{
	last_result_query = queryError.lastError().number();
}

void API_DataBase_alarmmasterserver::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void API_DataBase_alarmmasterserver::showError ( QString metodo, QSqlQuery &queryError )
{
	lout << "ERRORE-ORACLE-QUERY(" + metodo + ") [" << last_result_query << ":" << qPrintable ( queryError.lastError().text() ) << "]" << endl;
}

void API_DataBase_alarmmasterserver::showError ( QString metodo, QSqlDatabase &dbError )
{
	lout << "ERRORE-ORACLE-DB(" + metodo + ") [" << last_result_db << ":" << qPrintable ( dbError.lastError().text() ) << "]" << endl;
}


void API_DataBase_alarmmasterserver::getAlarmPolicy ( ALARM_POLICY * alpo, int alarm_type )
{
	bool noDbError = false;


	strSelect = "";
	strSelect += "SELECT DISTINCT TRAFF_ID, POL_ID, INTERVALLO, NVL(PERSISTENZA,0), COEFF ";
	strSelect += "FROM T_POLICY ";
	strSelect += "WHERE TRAFF_ID =" + QString::number ( alarm_type );

	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );


	if ( noDbError == false ) {
		showError ( "getAlarmPolicy()", query );
	}
	else {
		while ( query.next() ) {
			QString name = query.value ( 0 ).toString();
			int salary = query.value ( 1 ).toInt();


			alpo->iTraffId 		= query.value ( 0 ).toInt();
			alpo->cNewPolicy 	= query.value ( 1 ).toString().at ( 0 ).toLatin1();
			alpo->iInterval 	= query.value ( 2 ).toInt();
			alpo->iPersistence 	= query.value ( 3 ).toInt();
			alpo->coeff 		= query.value ( 4 ).toFloat();
		}
	}

}

int API_DataBase_alarmmasterserver::checkWorkingDay ( QDateTime & cur_date )
{
	bool noDbError = false;

	/* first check for holyday days of the week */
	if ( cur_date.date().dayOfWeek() == 6 /* Saturday */ ||
	                cur_date.date().dayOfWeek() == 7 /* Sunday */
	   )
		return 1;


	strSelect = "";
	strSelect += "SELECT COUNT(1) ";
	strSelect += "FROM T_FESTIVI ";
	strSelect += "WHERE gg=to_date(" + cur_date.date().toString ( "yyyyMMdd" ) + "'yyyymmdd')";

	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );
	query.next();
	int numRows = query.value ( 0 ).toInt();


	if ( noDbError == false ) {
		return 0; /* not found: is a working day */
	}
	else {
		if ( numRows > 0 ) {
			return 1; /* holyday */
		}
		else {
			return 0; /* not found: is a working day */
		}

	}


}

QString API_DataBase_alarmmasterserver::loadConfiguration ( int idServer ) /* OK */
{
	lout3 << "API_DataBase_alarmmasterserver::loadConfiguration(" << idServer << ")" << endl;
	bool noDbError = false;
	QString configName = "";

	strSelect = "select distinct b.FILE_NAME_CONF from TAB_ALARM_SERVER_PRODUCTION a,TAB_INFO_CONFIG_NTM b where a.SERVER_ID=" + QString::number ( idServer
	                                                                                                                                             ) + " and b.TIPO_DI_CONFIGURAZIONE=a.TIPO_DI_CONFIGURAZIONE";
	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );


	if ( noDbError == true ) {
		if ( query.next() == true ) {
			configName = query.value ( 0 ).toString();
		}
	}
	else {
		showError ( "loadConfiguration()", query );
	}

	return configName;
}

bool API_DataBase_alarmmasterserver::checkIdRelationOnServer ( int idServer, int idRel ) /* OK */
{
	//lout3 << "API_DataBase_alarmmasterserver::checkIdRelationOnServer(" << idServer << "," << idRel << ") START" << endl;

	bool noDbError = false;
	bool trovato = false;

	strSelect = "";
	strSelect += "SELECT COUNT(1) ";
	strSelect += "FROM TAB_ALARM_SERVER_PRODUCTION ";
	strSelect += "WHERE SERVER_ID=" + QString::number ( idServer ) + " AND ";
	strSelect += " ID_ALARM_RELATION=" + QString::number ( idRel );

	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );
	query.next();
	int numRows = query.value ( 0 ).toInt();

	if ( noDbError == true ) {
		if ( numRows > 0 ) {
			trovato = true;
			//lout3 << "    trovato #:" << numRows << endl;
		}
		else {
			trovato = false;
			//lout3 << "    NON TROVATO." << endl;
		}
	}
	else {
		trovato = false;
		showError ( "checkIdRelationOnServer()", query );
	}

	//lout3 << "checkIdRelationOnServer(" << idServer << "," << idRel << ") END" << endl;
	return trovato;

}


void API_DataBase_alarmmasterserver::getAllThresholds ( QMap<QString, THRESHOLD_VALUE> & thresholds, QDateTime & cur_date, int *idAlarm, int num_alarms, NtmCompleteConfig & ntm )
{
	bool noDbError = false;
	int recordScritti = 0;
	QString strIdAlarm = "";

	QDateTime now = QDateTime::currentDateTime();
	lout2 << "API_DataBase_alarmmasterserver::getAllThresholds(" << cur_date.toString ( "yyyy-MM-dd" ) << ") - " << now.toString ( "yyyyMMdd-hh:mm:ss" ) << " START" << endl;

	for ( int i = 0; i < num_alarms - 1; i++ ) {
		strIdAlarm += QString::number ( idAlarm[i] ) + ",";
	}

	strIdAlarm += QString::number ( idAlarm[num_alarms - 1] );


	strSelect = "";
	strSelect += "SELECT COUNT(1) ";
	strSelect += " FROM T_THR_ALARM ";
	strSelect += "WHERE DATA_SOGLIA=TO_DATE ('" + cur_date.date().toString ( "dd-MM-yyyy" ) + "', 'DD-MM-YYYY') AND ";
	strSelect += "ID_RELATION in (" + strIdAlarm + ")";

	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );
	query.next();

	if ( noDbError == true ) {
		lout2 << query.value ( 0 ).toInt() << " rows to read." << endl;
	}

	strSelect = "";
	strSelect += "SELECT RELATION_KEY,DAYLY_HOUR,ID_THRESHOLD,VALUE ";
	strSelect += " FROM T_THR_ALARM ";
	strSelect += "WHERE DATA_SOGLIA=TO_DATE ('" + cur_date.date().toString ( "dd-MM-yyyy" ) + "', 'DD-MM-YYYY') AND ";
	strSelect += "ID_RELATION in (" + strIdAlarm + ")";

	query.clear();
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );

	QString hour_str;
	QString th_key;
	QString th_tag;
	int th_id;

	if ( noDbError == true ) {
		while ( query.next() ) {
			hour_str.sprintf ( "%02d", query.value ( 1 ).toInt() );
			th_id = query.value ( 2 ).toInt();
			if ( ntm.thresholdGeneratorInterface->get ( th_id ) )
				th_tag = ntm.thresholdGeneratorInterface->get ( th_id )->data.description;
			else
				th_tag = "";
			th_key = query.value ( 0 ).toString()
			         + hour_str
			         + QChar ( '_' )
			         + th_tag;
			THRESHOLD_VALUE ts;
			ts.kc = -1.0;
			ts.value = query.value ( 3 ).toFloat();
			thresholds.insert ( th_key, ts );
			recordScritti++;

			if ( ( recordScritti % 100000 ) == 0 ) {
				lout3 << "+++ Inserted " << recordScritti << " records." << endl;
			}
		}
	}
	else {
		showError ( "getAllThresholds()", query );
	}

	loadOvverride ( thresholds, idAlarm, num_alarms );

	now = QDateTime::currentDateTime();
	lout1 << "API_DataBase_alarmmasterserver::getAllThresholds(" << cur_date.toString ( "yyyy-MM-dd" ) << ") - " << now.toString ( "yyyyMMdd-hh:mm:ss" ) << " END" << endl;

	return;
}

void API_DataBase_alarmmasterserver::loadOvverride ( QMap<QString, THRESHOLD_VALUE> & thresholds, int *idAlarm, int num_alarms )
{
	if ( thresholds.count() == 0 ) {
		lout2 << "API_DataBase_alarmmasterserver::loadOvverride--> No thresholds loaded." << endl;
		return;
	}

	bool noDbError = false;
	QString strIdAlarm = "";
	QString strRecordOverride = "";

	QDateTime now = QDateTime::currentDateTime();
	int type_day = checkWorkingDay ( now );

	if ( type_day == 1 ) { // Giorno Festivo o Prefestivo
		strRecordOverride = "OVERRIDE_SOGLIA_FESTIVO";
	}
	else {
		strRecordOverride = "OVERRIDE_SOGLIA";
	}


	for ( int i = 0; i < num_alarms - 1; i++ ) {
		strIdAlarm = strIdAlarm + QString::number ( idAlarm[i] ) + ",";
	}
	strIdAlarm = strIdAlarm + QString::number ( idAlarm[num_alarms - 1] );


	strSelect = "";
	strSelect += "SELECT RELATION_KEY,DAYLY_HOUR,ID_THRESHOLD,OVERRIDE_COEFFICENTE," + strRecordOverride;
	strSelect += " FROM T_THR_OVERRIDE ";
	strSelect += " WHERE ID_RELATION in (" + strIdAlarm + ")";

	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();
	setError ( query );

	float override_kfactor = -1.0;
	float override_thr = 0.0;

	if ( noDbError == true ) {
		while ( query.next() ) {
			QString hour_str = "";
			hour_str.sprintf ( "%02d", query.value ( 1 ).toInt() );
			QString th_key = query.value ( 0 ).toString() + hour_str + QChar ( '_' ) + QString::number ( query.value ( 2 ).toInt() );

			if ( thresholds.contains ( th_key ) ) {
				THRESHOLD_VALUE ( thresholds.value ( th_key ) ).kc = query.value ( 3 ).toFloat(); // OVERRIDE_COEFFICENTE;
				THRESHOLD_VALUE ( thresholds.value ( th_key ) ).value = query.value ( 4 ).toFloat(); // OVERRIDE_SOGLIA

			}
			else {
				THRESHOLD_VALUE ts;
				ts.kc = query.value ( 3 ).toFloat(); // OVERRIDE_COEFFICENTE
				ts.value = query.value ( 4 ).toFloat(); // OVERRIDE_SOGLIA
				thresholds.insert ( th_key, ts );
			}
		}
	}
	else {
		showError ( "loadOvverride()", query );
	}
}


THRESHOLD_VALUE API_DataBase_alarmmasterserver::getThresholds ( int type_day, int relation_id, QDateTime & cur_date, const QString & relation_key,
                int gen_id )
{
	bool noDbError = false;
	char no_overrides = 0;
	THRESHOLD_VALUE ts;
	QString strValue = "";

	int max_day_gap_count = 0;

	ts.kc = -1.0;
	ts.value = -1.0;
// 	ts.working_day = type_day;

	if ( type_day == 1 ) { // Giorno Festivo o Prefestivo
		strValue = "OVERRIDE_SOGLIA_FESTIVO";
	}
	else {
		strValue = "OVERRIDE_SOGLIA";
	}

	strSelect = "";
	strSelect += "SELECT " + strValue;
	strSelect += " FROM T_THR_OVERRIDE ";
	strSelect += "WHERE ID_RELATION=" + QString::number ( relation_id ) + " AND ";
	strSelect += "RELATION_KEY='" + relation_key + "' AND ";
	strSelect += "ID_THRESHOLD=" + QString::number ( gen_id );

// 	lout1 << strSelect << endl;

	QSqlQuery query ( db );
	query.prepare ( strSelect );
	noDbError = query.exec();

	setError ( query );

	if ( ( noDbError == true ) && ( query.next() == true ) ) {
		ts.value = query.value ( 0 ).toFloat();
	}
	else { // non ci sono override
		do {
			if ( max_day_gap_count ) {
				cur_date.addDays ( -max_day_gap_count );
			}
			strSelect = "";
			strSelect += "SELECT VALUE ";
			strSelect += " FROM T_THR_ALARM ";
			strSelect += "WHERE ID_RELATION=" + QString::number ( relation_id ) + " AND ";
			strSelect += "DATA_SOGLIA=TO_DATE ('" + cur_date.date().toString ( "dd-MM-yyyy" ) + "', 'DD-MM-YYYY') AND ";
			strSelect += "DAYLY_HOUR=" + QString::number ( cur_date.time().hour() ) + " AND ";
			strSelect += "RELATION_KEY='" + relation_key + "' AND ";
			strSelect += "ID_THRESHOLD=" + QString::number ( gen_id );

			//lout1 << strSelect << endl;

			//QSqlQuery query(db);
			query.clear();
			query.prepare ( strSelect );
			noDbError = query.exec();
			setError ( query );

			if ( noDbError == true ) {
				if ( query.next() == true ) {
					ts.value = query.value ( 0 ).toFloat();
				}
			}
			else {
				showError ( "getThresholds()", query );
				lout1 << "Query statement: " << endl;
				lout1 << "[" << strSelect << "]" << endl;
			}
		}
		while ( ++max_day_gap_count < MAX_DAY_GAP && noDbError == false );
	}

	return ts;

}

QString API_DataBase_alarmmasterserver::formatValue ( int itType, QString value )
{
	QString str = "";
	switch ( itType ) {
		case T_STRING: {
				str += "'" + value.replace ( "'", " " ) + "'";
			}
			break;

		case T_NUMBER: {
				str += value;
			}
			break;

		case T_DATE: {
				str += "to_date('" + value + "','yyyymmdd')";
			}
			break;

		case T_DATETIME: {
				str += "to_date('" + value + "','yyyymmdd-HH24:MI:SS')";
			}
			break;
	}

	return str;
}


bool API_DataBase_alarmmasterserver::insertRecordInTable_BLOC()
{
	bool noDbError = false;
	//lout1 << "-----> API_DataBase_Sts::insertRecordInTable_BLOC("<< strTableName<< ") START" << endl;


	QSqlQuery query ( db );
	query.prepare ( strInsert );
	noDbError = query.exec();
	setError ( query );

	//lout1 << "-----> API_DataBase_Sts::strInsert("<< strInsert<< ")" << endl;

	if ( noDbError == false ) {
		showError ( "insertRecordInTable_BLOC()", query );
	}

	strValues.clear();


	//lout1 << "-----> API_DataBase_Sts::insertRecordInTable_BLOC("<< strTableName<< ") END" << endl;

	return !noDbError;
}

bool API_DataBase_alarmmasterserver::writeData ( int type )
{
	bool bRet2 = insertRecordInTable_BLOC();
}

bool API_DataBase_alarmmasterserver::creaInsert ( int numOccorrenze )
{
	unsigned long i = 0;

	QString values = "";
	strInsert = "";

	QStringList::Iterator itVal = strValues.begin();

	strInsert += "INSERT ALL ";

	for ( int i = 0; i < numOccorrenze; i++ ) {
		Q3ValueList<int>::Iterator itType = strType.begin();

		strInsert += "INTO ";
		strInsert += strTableName;

		values = " VALUES(";
		for ( int k = 0; k < numCampi; k++ ) {
			values += formatValue ( *itType, *itVal );
			//lout3 << " valore("<< k << "," << numCampi<< ")=" << *itVal << ", " << formatValue(*itType,*itVal) << ","<< *itType << endl;
			if ( k != numCampi - 1 ) {
				values += ",";
			}
			++itVal;
			++itType;
		}
		values += ") ";
		strInsert += values;
	}

	strInsert += " SELECT * from DUAL";

//         lout3 << strInsert << endl;
//         lout3 << " len stringa= " << QString(strInsert).length() << endl;
}


QString API_DataBase_alarmmasterserver::getTableName ( int t )
{
	QString appo = "UNDEFINED";

	switch ( t ) {
		case INSERT_INTO_T_ALARM: {
				appo = "T_ALARM";
			}
			break;

		case INSERT_INTO_T_ALARM_KPI: {
				appo = "T_ALARM_KPI";
			}
			break;

		case INSERT_INTO_T_ALARM_KPI_SOG: {
				appo = "T_ALARM_KPI_SOG";
			}
			break;

		case INSERT_INTO_T_ALARM_COUNTER: {
				appo = "T_ALARM_COUNTER";
			}
			break;

		default:
			{} break;
	}

	return appo;
}

void API_DataBase_alarmmasterserver::preparaInsert ( int type ) /* OK */
{

	strCampi.clear();
	strType.clear();

	strInsert = "";

	switch ( type ) {
		case INSERT_INTO_T_ALARM: { // insert into INSERT_INTO_T_ALARM
				strTableName = getTableName ( type );

				strCampi.append ( "LINK_ALLARME" );
				strType.append ( T_NUMBER );
				strCampi.append ( "ID_RELATION" );
				strType.append ( T_NUMBER );
				strCampi.append ( "ID_KPI" );
				strType.append ( T_NUMBER );
				strCampi.append ( "RELATION_KEY_VALUE" );
				strType.append ( T_STRING );
				strCampi.append ( "RELATION_KEY_DESC" );
				strType.append ( T_STRING );
				strCampi.append ( "DATA_ALLARME" );
				strType.append ( T_DATETIME );
				strCampi.append ( "DAYLY_HOUR" );
				strType.append ( T_NUMBER );
				strCampi.append ( "COEFF" );
				strType.append ( T_NUMBER );
				strCampi.append ( "POL_ID" );
				strType.append ( T_STRING );
				strCampi.append ( "INTERVALLO" );
				strType.append ( T_NUMBER );
				strCampi.append ( "PERSISTENZA" );
				strType.append ( T_NUMBER );
				strCampi.append ( "STATO" );
				strType.append ( T_NUMBER );


			}
			break;

		case INSERT_INTO_T_ALARM_KPI: { // per INSERT_INTO_T_ALARM_KPI
				strTableName = getTableName ( type );

				strCampi.append ( "LINK_ALLARME" );
				strType.append ( T_NUMBER );
				strCampi.append ( "DATA_ALLARME" );
				strType.append ( T_DATETIME );
				strCampi.append ( "ID_KPI" );
				strType.append ( T_NUMBER );
				strCampi.append ( "DESCRIZIONE" );
				strType.append ( T_STRING );
				strCampi.append ( "ALARM_VALUE" );
				strType.append ( T_NUMBER );

			}
			break;

		case INSERT_INTO_T_ALARM_KPI_SOG: { // per INSERT_INTO_T_ALARM_KPI_SOG
				strTableName = getTableName ( type );

				strCampi.append ( "LINK_ALLARME" );
				strType.append ( T_NUMBER );
				strCampi.append ( "DATA_ALLARME" );
				strType.append ( T_DATETIME );
				strCampi.append ( "ID_KPI" );
				strType.append ( T_NUMBER );
				strCampi.append ( "DESCRIZIONE" );
				strType.append ( T_STRING );
				strCampi.append ( "SOG_VALUE" );
				strType.append ( T_NUMBER );

			}
			break;

		case INSERT_INTO_T_ALARM_COUNTER: { // per INSERT_INTO_T_ALARM_COUNTER
				strTableName = getTableName ( type );

				strCampi.append ( "LINK_ALLARME" );
				strType.append ( T_NUMBER );
				strCampi.append ( "DATA_ALLARME" );
				strType.append ( T_DATETIME );
				strCampi.append ( "ID_KPI" );
				strType.append ( T_NUMBER );
				strCampi.append ( "DESCRIZIONE" );
				strType.append ( T_STRING );
				strCampi.append ( "COUNTER_VALUE" );

			}
			break;

		default: {
			} break;
	}

	//lout << "preparaInsert()= "<< strInsert << "," << strTableName  << endl;

	numCampi = strCampi.count();
}


bool API_DataBase_alarmmasterserver::eliminaAllarmiStorici ( int finestra )
{
	QString strInsert = "DELETE T_ALARM WHERE DATA_ALLARME<to_date(SYSDATE-" + QString::number ( finestra ) + ",'DD-MM-YY')";
//         lout3 << strInsert << endl;
	bool bRet = deleteRecordInTable ( strInsert );

	strInsert = "DELETE T_ALARM_KPI WHERE DATA_ALLARME<to_date(SYSDATE-" + QString::number ( finestra ) + ",'DD-MM-YY')";
//         lout3 << strInsert << endl;
	bRet = deleteRecordInTable ( strInsert );

	strInsert = "DELETE T_ALARM_KPI_SOG WHERE DATA_ALLARME<to_date(SYSDATE-" + QString::number ( finestra ) + ",'DD-MM-YY')";
//         lout3 << strInsert << endl;
	bRet = deleteRecordInTable ( strInsert );

	strInsert = "DELETE T_ALARM_COUNTER WHERE DATA_ALLARME<to_date(SYSDATE-" + QString::number ( finestra ) + ",'DD-MM-YY')";
//         lout3 << strInsert << endl;
	bRet = deleteRecordInTable ( strInsert );

	return bRet;
}

bool API_DataBase_alarmmasterserver::deleteRecordInTable ( const QString strDelete ) /* OK */
{

//         lout3 << "-----> API_DataBase_alarmmasterserver::deleteRecordInTable() START" << endl;

	bool noDbError = false;

	QSqlQuery query ( db );
	query.prepare ( strDelete );
	noDbError = query.exec();
	setError ( query );

	if ( noDbError == false ) {
		showError ( "deleteRecordInTable", query );
	}

//         lout3 << "-----> API_DataBase_alarmmasterserver::deleteRecordInTable() END" << endl;

	return true;
}
