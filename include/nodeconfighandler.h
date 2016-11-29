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

#ifndef NODECONFIGHANDLER_H
#define NODECONFIGHANDLER_H

#define DB_NOERROR                 -1

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <QtSql/QSqlDriver>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <apidb_base.h>

#include <Qt/qlist.h>
#include <configparser.h>
/*
`IND_NODE` int(2) DEFAULT NULL,
  `FLAG_ATT` int(1) DEFAULT NULL,
  `PFX_NODE` varchar(3) DEFAULT NULL,
  `SUF_NODE` varchar(3) NOT NULL,
  `NODE` varchar(8) NOT NULL DEFAULT '',
  `NAME_NODE` varchar(12) NOT NULL,
  `TIPO_NODE` varchar(2) DEFAULT NULL,
  `LATITUDE_O` char(1) DEFAULT NULL,
  `LATITUDE_G` float(6,2) DEFAULT NULL,
  `LATITUDE_P` float(6,2) DEFAULT NULL,
  `LATITUDE_S` float(6,2) DEFAULT NULL,
  `LONGITUDE_O` char(1) DEFAULT NULL,
  `LONGITUDE_G` float(6,2) DEFAULT NULL,
  `LONGITUDE_P` float(6,2) DEFAULT NULL,
  `LONGITUDE_S` float(6,2) DEFAULT NULL,
  */
enum orientation {
		Nord,
		Sud,
		East,
		West
	};

typedef struct {
	int  iIndice;
	int  iActive;
	QString acPrefix;
	QString acSuffix;
	QString acName;
	QString acNameNodo;
	QString typeNode;
	orientation latitude_orientation;
	double latitude_degrees;
	double latitude_first;
	double latitude_second;
	orientation longitude_orientation;
	double longitude_degrees;
	double longitude_first;
	double longitude_second;
} PAR_INPUT_NODE;

typedef QList<PAR_INPUT_NODE> InputNodeList;
typedef QList<PAR_INPUT_NODE>::iterator InputNodeList_it;

class NodeConfigHandler: public APIDB_Base
{


public:
	NodeConfigHandler();
	NodeConfigHandler ( const QString & iniref );
	~NodeConfigHandler();

	bool openDB( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();
	bool isConnect();
	void load(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void debug(const PAR_INPUT_NODE *parinputnode_ptr);

	/** Ritorna un accesso all QList dei nodi */
	inline InputNodeList & getInputNodeList() {
		return inputNodeList;
	}
	/** Test per l'avvenuto caricamento dei dati in INPUTNODE.DAT */
	inline bool loaded() {
		return initializedFlag;
	}

private:
	InputNodeList inputNodeList;
	bool initializedFlag;
	QString inifilename;

	int 		last_result_db;
	QSqlDatabase 	db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

};

#endif
