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


#ifndef _PLUGINSAPIDATA_H
#define _PLUGINSAPIDATA_H

#include <pluginbase.h>
#include <nodo.h>
#include <drimagehandler.h>
#include <queryparams.h>
#include <simplevector.h>
#include <thresholds_defines.h>
#include <common_alarm_typedef.h>
#include <Qt/qstringlist.h>
#include <Qt/qmap.h>
#include <Qt/qlist.h>
#include <Qt/qpair.h>
#include <Qt/qhash.h>

#ifndef _AHASHDICT
#define _AHASHDICT
typedef QHash<QString, Nodo *> HashDict;			// used as local type (fast) binary tree
typedef QHashIterator<QString, Nodo *> HashDictIterator;
#endif

class DataElement;
class BuildOper;
class converter;
class HttpImpl;
class QTextStream;
class API_DataBase_alarmmasterserver;
class AlarmStatusNode;
class AlarmStatusData;
class ThresholdStatusNode;
class PivotFile;

#ifndef _SHAREDTYPES
#define _SHAREDTYPES

typedef QHash<long, BuildOper *> OperDirect;

#endif



// Plugin Type: Ntm_CounterHandler
// Caller process: ntmdataserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker dei contatori di
//              traffico.
enum CountersPluginOperations {
	cntKeyHandle,
	cntCountersEvaluation,
	cntShutdown,
	cntNetworkEvaluation,
	numCountersPluginOperations
};

typedef struct {
	CountersPluginOperations operation;
	Nodo * countersNode;
	DRImageHandler * cdr;
	int dayIndex;
	char * cdrKey;
	HashDict * Btree;
	bool usePluginNodeHandler;
	bool transferCDR;
	int userDataSize;
	bool accept;
} CountersPluginAPIData;

// Plugin Type: Ntm_CDRReader
// Caller process: ntmdataserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker/startup del lettore

enum CdrReaderInitOp {
	cdrInitOp_globalStartup,
	cdrInitOp_cyclicStartup,
	cdrInitOp_cyclicShutdown,
	cdrInitOp_globalShutdown,
	cdrInitOp_numOps
};

enum CdrReaderOpStatus {
	cdrStatus_Success,
	cdrStatus_Error,
	cdrStatus_EOF,
	cdrStatus_EOF_NoRecords,
	cdrStatus_numOpStatus
};

typedef struct {
	CdrReaderInitOp operation;
	bool useIndex;
	QString keyName;
	BuildOper * keyList;
 	OperDirect * operList;
	QString processDate;
	int dayIndex;
	UsrParamDataType dataType;
	bool forceFiltering;
	long itemsToProcess;
	quint64 startOffset;
	quint64 endOffset;
	CdrReaderOpStatus status;
} CdrReaderStartupAPIData;

typedef struct {
	DRImageHandler * cdr;
	int * progress;
	quint64 offset;
	CdrReaderOpStatus status;
} CdrReaderWorkerAPIData;

// Plugin Type: Adams_ElementHandler
// Caller process: ntmdataserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker dei contatori
//              nella gestione degli elementi di traffico.


enum TEHandlerOp {
	teOp_InitPhase,
	teOp_FieldEvaluation,
	teOp_Filtering,
	teOp_KeyBuilding,
	teOp_ReportProduction,
	teOp_AlarmCondition,
	teOp_Custom,
	teOp_numOps
};

enum TEHandlerOpStatus {
	teStatus_Success,
	teStatus_Error,
	teStatus_UnmanagedOperation,
	teStatus_numOpStatus
};

enum TEHandlerOpValueType {
	teValue_Integer,
	teValue_Double,
	teValue_String,
	teValue_numValues
};

typedef struct {
	TEHandlerOp operation;
	TEHandlerOpStatus status;
	//
	bool useFor[teOp_numOps];
	//
	int dayIndex;
	DataElement * element;
	QueryParams::Rest * rest;
	BuildOper * oper;
	//
	DRImageHandler * CDRr;
	int offset;
	converter * convert;
	int configuredKeyLenght;
	char * inKeyValue;
	//
	TEHandlerOpValueType valueType;
	unsigned long integerValue;
	double doubleValue;
	QString stringValue;
	//
	bool accept;
} DEHandlerWorkerAPIData;

// Plugin Type: Ntm_DescriptionHelper
// Caller process: ntmmasterserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker
//              del plugin di gestione help/descrizioni su codici elementi di traffico

typedef QHash<QString, QString *> 			HelpItems;
typedef QHash<QString, QString *>::iterator		HelpItemsIterator;
typedef QHash<QString, QString *>::const_iterator	HelpItemsConstIterator;

typedef struct {
	QString helpTag;
	QStringList elementMatch;
	int nodeIndex;
	bool loaded;
	PluginImpl * handler;
	HelpItems items;
} DescHelperAPIData;

typedef QHash<QString, DescHelperAPIData *> HelpDictionary;

// Plugin Type: Ntm_ReportHandler
// Caller process: ntmmasterserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker
//              del plugin di gestione report a tracciato fisso

typedef struct {
	QMap<QString,Nodo> * directTree;
	QMap<QString,Nodo> * inverseTree;
	int nodeIndex;
	HttpImpl * http;
	QTextStream * stream;
	int mediaType;
	HelpDictionary * helper;
	//
	bool haveHeaderManagement;
	PivotFile * pivotFile;
	net::etech::ExportDataSeq * exportData;
} ReportHandlerAPIData;

// Plugin Type: Ntm_AlarmGenerator
// Caller process: alarmmasterserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker
//              del plugin di generazione allarmi

enum AlarmHandlerOp {
	alarmHanlerOp_Startup,
	alarmHanlerOp_Evaluate,
	alarmHanlerOp_Describe,
	alarmHanlerOp_AlarmPairs,
	alarmHanlerOp_ThresholdPairs,
	alarmHanlerOp_AlarmValuesDescription,
	alarmHanlerOp_QCDescription,
	alarmHanlerOp_numOps
};

typedef struct {
	AlarmHandlerOp operation;
	int alarm_index;
	QDateTime time;
	int quarter;
	AlarmStatusNode * status_node;
	AlarmStatusData * status_data;
	QString node_key;
	ALARM_POLICY * alarm_policy;
	QMap<QString, int> * threshold_ids;
	QMap<QString, THRESHOLD_VALUE> * thresholds;
	Counters * cnt_kit;
	// output values
	bool in_alarm;
	QString alarmToString;
	QList< QPair<QString, QString> > * infoPairList;
	bool success;
} AlarmGeneratorAPIData;

// Plugin Type: Ntm_ThresholdGenerator
// Caller process: sogmasterserver
// Description: Struttura dati di interfaccia dal chiamante verso il method worker
//              del plugin di generazione soglie

enum ThresholdHandlerOp {
        thresholdHanlerOp_Startup,
        thresholHanlerOp_EvaluateSoglia,
        thresholHanlerOp_EvaluateMedia,
        thresholHanlerOp_numOps
};

typedef struct {
        ThresholdHandlerOp operation;
        QDateTime time;
        ThresholdStatusNode * status_node;
        Counters * cnt_kit;
        QString node_key;
        nodoTreeTHRESHOLD *nodoTHR;
        coeff_corrective *coeffCORRETTIVI;
        bool success;
        float value;
} ThresholdGeneratorAPIData;


#endif /* _ADAMSPLUGINSAPIDATA_H */
