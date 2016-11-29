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

#include <apidb_ior.h>
#include <applogger.h>
#include <Qt/qstringbuilder.h>

#define DEBUG_APIDB_SSM_HR                                  1


APIDB_IorTable::APIDB_IorTable ( )
	: m_connection ( "APIDB_IorTable" ),
	  connected ( false )
{
}

bool APIDB_IorTable::openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD )
{
	method ( "openDB(QMYSQL)" );

	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;
	connected = true;

	db = QSqlDatabase::addDatabase ( "QMYSQL", m_connection ); // QMYSQL -> MySql Database
	db.setDatabaseName ( strDBNAME );
	db.setUserName ( strUSER );
	db.setPassword ( strPSWD );

	try {
		db_open ( db );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		connected = false;
	}

	return connected;
}

void APIDB_IorTable::closeDB()
{
	if ( !connected )
		return;

	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
	connected = false;
}


APIDB_IorTable::~APIDB_IorTable()
{
	closeDB();
}

QString APIDB_IorTable::fmtNumber ( float num )
{
	QString buf;
	buf.sprintf ( "%10.2f", num );
	return buf;
}

bool APIDB_IorTable::insertCreate ( int occurrenceNum )
{
	unsigned long i = 0;

	QString values = "";
	strInsert = "";

	QStringList::Iterator itVal = strValues.begin();

	strInsert = "INSERT INTO " % strTableName % " VALUES";

	for ( int i = 0; i < occurrenceNum; i++ ) {
		QList<int>::Iterator itType = strType.begin();

		values = " (";
		for ( int k = 0; k < fieldsNum; k++ ) {
			values += formatValue ( *itType, *itVal );
			//lout3 << " valore("<< k << "," << fieldsNum<< ")=" << *itVal << ", " << formatValue(*itType,*itVal) << ","<< *itType << endl;
			if ( k != fieldsNum - 1 ) {
				values += ",";
			}
			++itVal;
			++itType;
		}
		if ( i == occurrenceNum - 1 )
			values += ")";
		else
			values += "),";
		strInsert += values;
	}
}

bool APIDB_IorTable::insertRecordInTable_BLOC()
{
	open_connection();
	method ( "insertRecordInTable_BLOC" );

	try {
		QSqlQuery query ( db );
		query.prepare ( strInsert );
		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		closeDB();
		return false;
	}

	strValues.clear();
	closeDB();
	return true;
}

bool APIDB_IorTable::writeData ( int type )
{
	return insertRecordInTable_BLOC();
}

QString APIDB_IorTable::formatValue ( int itType, const QString & value )
{
	QString str = "";
	switch ( itType ) {
		case T_STRING: {
			str += "'" + value + "'";
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
	}

	return str;
}


QString APIDB_IorTable::getTableName ( int t )
{
	QString appo = "UNDEFINED";

	switch ( t ) {
		case INSERT_INTO_T_IORS: {
			appo = "t_iors";
		}
		break;
	}

	return appo;
}

void APIDB_IorTable::insertPrepare ( int type )
{
	strFields.clear();
	strType.clear();
	strInsert.clear();

	switch ( type ) {
		case INSERT_INTO_T_IORS: { // insert into T_IORS

			strTableName = getTableName ( type );

			strFields.append ( "node_id" );
			strType.append ( T_NUMBER );
			strFields.append ( "process_name" );
			strType.append ( T_STRING );
			strFields.append ( "ior" );
			strType.append ( T_STRING );

		}
		break;
	}

	fieldsNum = strFields.count();
}


bool APIDB_IorTable::deleteIor ( const QString & processName, const QString & node )
{
	open_connection();
	int node_id = getNodeId ( node, true );
	method ( "deleteIor" );

	if ( node_id > 0 ) {
		QString strDelete = "DELETE from adams_ssm.t_iors where process_name='" % processName % "'" %
		                    " AND node_id = '" % QString::number ( node_id ) % "'";
		try {
			QSqlQuery query ( db );
			query.prepare ( strDelete );
			query_exec ( query );
		}
		catch ( A_DBException & de ) {
			lout << de.preface() << de.explain() << endl;
			closeDB();
			return false;
		}
	}

	closeDB();

	return true;
}


int APIDB_IorTable::getNodeId ( const QString & node, bool already_connected )
{
	int nodeId = -1;

	if ( ! already_connected ) {
		open_connection();
	}

	method ( "getNodeId(" + node + "): " );
	strSelect = "SELECT node_id FROM adams_ssm.t_node where name = '" % node % "'";

	try {

		QSqlQuery query ( db );
		query.prepare ( strSelect );
		query_exec ( query );

		if ( query.next() == true ) {
			nodeId = query.value ( 0 ).toInt();
		}
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
	}

	if ( ! already_connected )
		closeDB();

	return nodeId;

}

bool APIDB_IorTable::putIor ( const QString & processName, const QString & node, const QString &  ior )
{
	int node_id = 0;
	open_connection();

	IorTable * ior_table = getIor ( processName, node, true );
	method ( "putIor" );

	if ( ior_table ) {
		if ( ior == ior_table->ior ) {	// unchanged
			closeDB();
			lout3 << "Unchanged ior" << endl;
			return true;
		}

		// in table but different
		lout3 << "ior is changed" << endl;

		QString strDelete = "DELETE from adams_ssm.t_iors where process_name='" % processName %
		                    "' AND node_id='" % QString::number ( ior_table->node_id ) % "'";

		try {
			QSqlQuery query ( db );
			query.prepare ( strDelete );
			query_exec ( query );
		}
		catch ( A_DBException & de ) {
			lout << de.preface() << de.explain() << endl;
			closeDB();
			return false;
		}

		node_id = ior_table->node_id;
	}

	if ( !node_id )
		node_id = getNodeId ( node, true );

	if ( node_id < 0 ) {
		closeDB();
		return false;
	}

	insertPrepare ( INSERT_INTO_T_IORS );
	strValues.append ( QString::number ( node_id ) );
	strValues.append ( processName );
	strValues.append ( ior );

	insertCreate ( 1 );
	return writeData ( INSERT_INTO_T_IORS );
}

IorTable * APIDB_IorTable::getIor ( const QString & processName, const QString & node, bool already_connected )
{
	if ( ! already_connected ) {
		open_connection();
	}

	int node_id = getNodeId ( node, true );
	if ( node_id < 0 ) {
		return 0;
	}

	method ( "getIor(" + processName + ")" );

	IorTable * iortable = 0;
	strSelect = "SELECT node_id,process_name,ior FROM adams_ssm.t_iors where process_name='" %
	            processName % "' AND node_id='" % QString::number ( node_id ) % "'";

	try {
		QSqlQuery query ( db );
		query.prepare ( strSelect );
		query_exec(query);

		if ( query.next() == true ) {
			iortable = new IorTable();
			iortable->node_id = query.value ( 0 ).toInt();
			iortable->process_name = query.value ( 1 ).toString();
			iortable->ior = query.value ( 2 ).toString();
		}
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
	}

	if ( ! already_connected )
		closeDB();

	return iortable;
}

void APIDB_IorTable::debugIor ( IorTable *ior )
{
	if ( ior != 0 ) {
		lout3 << "node_id       = " << ior->node_id << endl;
		lout3 << "process_name  = " << ior->process_name << endl;
		lout3 << "ior           = " << ior->ior << endl;
	}
	else {
		lout3 << "IOR is 0" << endl;
	}
}





