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

#ifndef QUERY_PARAMS_H
#define QUERY_PARAMS_H

#include <Qt/qlist.h>
#include <Qt/qstring.h>
#include <Qt/qdatastream.h>

#include <cnfglobals.h>
#include <adams_limits.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#define MAX_FEP     10
#define MAX_N_VALUE 105

enum UsrParamOperator {
	poNOCOMP,
	poEQUAL,
	poGREATER,
	poLESSER,
	poGREATER_OR_EQUAL,
	poLESSER_OR_EQUAL,
	usrOperator_numOperator
};

enum UsrParamOutputType {
	usrOutputType_Web,
	usrOutputType_Pivot,
	usrOutputType_Export,
	usrOutputType_numOutputType
};

enum UsrParamsRelationKind {
	usrRelationKind_Direct,
	usrRelationKind_Inverse,
	usrRelationKind_Both,
	usrRelationKind_numRelationKind
};

enum UsrParamDataType {
	usrDataType_current,
	usrDataType_archive,
	usrDataType_numTypes
};



class QueryParams {
public:

	friend QDataStream &operator<<(QDataStream &out, const QueryParams & query_params);
	friend QDataStream &operator>>(QDataStream &in, QueryParams & query_params);

	QueryParams();
	~QueryParams();

	typedef struct {
		int Level;
		int Element;
		int Value[MAX_N_VALUE];
		char AsciiValue[MAX_N_VALUE][ASCII_REST_LEN];
		int Operator;
		int Priority;
	} Rest;

	inline void clear() { data.ElaborationData.clear(); data.Filters.clear(); data.Fep.clear(); }

	void init_fake(const QString & my_node, const char * ini_filename = 0, int diff_day = 0);

	typedef struct {
		char Name[MAX_CONFIG_FILENAME];			//  Nome configurazione in uso
		int AnalysisType;				//  Indica il tipo di analisi. Valori Possibili:vedi eTipoAnalisi.
		bool NetworkRealTime;				//  Indica se � una elaborazione Network RealTime.
		UsrParamDataType DataType;			//  Indica il tipo di dati. Valori Possibili: vedi UsrParamDataType.
		UsrParamOutputType OutputType;			//  Indica il tipo di output.
		QStringList ElaborationData;			//  Indica le date da elaborare.
		QList<int> Fep;					//  Indica le centrali prescelte.
		int Relation;					//  Identificativo della relazione
		UsrParamsRelationKind RelationDirection;	//  Indica la direzione della relazione.
		QList<Rest> Filters;				//  Lista Filters
		bool OppositeRestriction;			//  Indica se e stata richista una restrizione negata.
		bool FlagSort;					//  Indica se � stato richiesto un ordinamento.
		int ElementToSort;				//  Indica rispetto a quale elemento eseguire l'ordinamento.
		bool Ascendent;					//  Indica il tipo di ordinamento.
		bool Reserved;					//  Indica se possono essere abilitate le funzioni riservate;
		bool SingleRelation;				//  Indica che si vuole visualizzare solo il primo elemento della relazione.
		bool IsPercent;					//  Indica se si vuole visualizzare il report con valori percentuali.
		int HexValue;					//  Specifica la visualizzaione del report con valori esadecimali
		bool isScheduled;				//  Attivo se il report viene gestito dallo schedulatore
		int idUser;					//  id utente per job activator
		int idJob;					//  id del job
		char Description[JQ_DESC_SIZE];			//  Descrizione del Job
		int idReportSchema;				//  id del ReportShema selezionato... Necessario al masterserver
		char user[USR_LOGIN_LEN];			//  utente relativo alla richiesta
		char user_ip[IP_STRING_LEN];			//  ip utente
		bool freeFormat;				// utilizza relazione free-format
		int ffRelation[MAX_DIMENSION];			// definizione relazione free-format
	} DATA;

	DATA data;

	void copyToCorba(STRUCT_PARAMS * ptr);
	void fillFromCorba(const STRUCT_PARAMS * ptr);

protected:
	QDataStream & read(QDataStream & ds);
	QDataStream & write(QDataStream & ds) const;
};

/** Specific class for Rest list of restrictions to obtain a sort based on priority level */

class SortedRest : public QList<QueryParams::Rest>
{

public:
	static bool compareItems( QueryParams::Rest left, QueryParams::Rest right ) { return  left.Priority > right.Priority; }
};

extern QDataStream &operator<<(QDataStream &out, const QueryParams & query_params);
extern QDataStream &operator>>(QDataStream &in, QueryParams & query_params);


#endif
