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
#  Author Name <raffale.ficcadenti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--08-10-13 Raffaele Ficcadenti
#
*/

#ifndef APIDB_ASP_LOG_H
#define APIDB_ASP_LOG_H

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <QtSql/QSqlDriver>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qvariant.h>
#include <Qt/qlist.h>
#include <Qt/qstringlist.h>

#include <aspS.h>

#include <apidb_define.h>
#include <apidb_base.h>

using namespace net::etech;

class APIDB_AspLog: public APIDB_Base
{
public:
	APIDB_AspLog();
	~APIDB_AspLog();

	bool writeOnDB ( const S_APP_LOG &sAppLog );
	bool debug ( const S_APP_LOG &sAppLog );

	bool openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD );
	void closeDB();
	bool isConnect();
	void setDBNameCnnection (QString str);

private:
	void setError ( QSqlDatabase &dbError );
	inline int getExitStatusDB() {
		return last_result_db;
	}

	bool insertRecordInTable_BLOC();
	void insertPrepare ( int type );
	bool insertCreate ( int occurrenceNum );
	bool writeData ( int type );
	QString getTableName ( int t );
	QString formatValue ( int itType, QString value );
	


	QSqlDatabase 	db;
	QString 	m_connection;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	/* campi generici per costruzione insert */
	QString 	strTableName;
	QStringList 	strFields;
	QStringList 	strValues;
	QString     	strInsert;
	QString     	strSelect;
	QList<int>	strType;
	int 		fieldsNum;



};

#endif //APIDB_ASP_LOG_H
