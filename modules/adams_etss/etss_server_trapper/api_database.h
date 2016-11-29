/***************************************************************************
                          api_database.h  -  description
                             -------------------
    begin                : Mon Nov 12 2001
    copyright            : (C) 2001 by Raffaele Ficcadenti
    email                : rficcad@tin.it
 ***[ History ]*************************************************************/
#ifndef API_DATABASE_H
#define API_DATABASE_H


#include <Qt/qstring.h>
#include <applogger.h>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qvariant.h>
#include <Qt/qstringlist.h>
#include <Qt/qmutex.h>
#include "ntmshared/ams_alarm_resource.h"
#include <common_alarm_typedef.h>
//Added by qt3to4:
#include <Qt/q3valuelist.h>

/* COSTANTI PER LA DEFINIZIONE DELLE TABELLE DEGLI ALLARMI STORICI */
#define INSERT_INTO_T_ALARM		0
#define INSERT_INTO_T_ALARM_KPI		1
#define INSERT_INTO_T_ALARM_KPI_SOG	2
#define INSERT_INTO_T_ALARM_COUNTER	3

#define LEN_STR_SELECT                  4000
#define NUM_INSERT_PARALLELE            20
#define T_NUMBER                        1
#define T_STRING                        2
#define T_DATE                          3
#define T_FLOAT                         4
#define T_DATETIME			5
#define FINESTRA_GG_ALLARMI_STORICI	60
#define DB_NOERROR			-1


class QDateTime;

/**
 * Classe gestione connesione con Oracle
 */
class API_DataBase_alarmmasterserver
{
public:
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al dB Oracle con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Oracle.
	 */
	API_DataBase_alarmmasterserver ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD );

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il db Oracle.
	 */
	~API_DataBase_alarmmasterserver();




	inline int getExitStatusDB() {
		return last_result_db;
	}
	inline int getExitStatusQUERY() {
		return last_result_query;
	}
	void showError ( QString metodo, QSqlQuery &queryError );
	void showError ( QString metodo, QSqlDatabase &dbError );
	void setError ( QSqlQuery &queryError );
	void setError ( QSqlDatabase &dbError );

	bool checkIdRelationOnServer ( int idServer, int idRel );
	void getAlarmPolicy ( ALARM_POLICY * alpo, int alarm_type );
	int checkWorkingDay ( QDateTime & cur_date );
	THRESHOLD_VALUE getThresholds ( int type_day, int relation_id, QDateTime & cur_date, const QString & relation_key,
	                                int gen_id );

	void getAllThresholds ( QMap<QString, THRESHOLD_VALUE> & thresholds, QDateTime & cur_date, int *idAlarm, int num_alarms, NtmCompleteConfig & ntm );
	void loadOvverride ( QMap<QString, THRESHOLD_VALUE> & thresholds, int *idAlarm, int num_alarms );
	inline QString fmtNumber ( double num )  {
		QString s;
		s.sprintf ( "%10.2f\0", num );
		return s;
	}
	QString loadConfiguration ( int idServer );

	bool insertRecordInTable_BLOC();
	bool writeData ( int type );
	bool creaInsert ( int numOccorrenze );
	QString getTableName ( int t );
	void preparaInsert ( int type );
	QString formatValue ( int itType, QString value );
	bool eliminaAllarmiStorici ( int finestra );
	bool deleteRecordInTable ( const QString strDelete );


	/* campi per soglie soriche e giornaliere */
	QStringList strCampi;
	QStringList strValoriFissi;
	QStringList strValues;
	QString     strInsert;
	QString     strSelect;
	Q3ValueList<int> strType;

	int numCampi;

	/* per costruzione insert */
	QString strTableName;

private:
	QSqlDatabase 	db;
	int 		last_result_db;
	int 		last_result_query;
	int 		last_result;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;
	QMutex 		m_global;

	// metodo che stampa sullo standar output l'errore verificatosu durante la gestione delle tabelle oracle.

};

#endif
