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

#ifndef BUILDER_OPER_H
#define BUILDER_OPER_H

#include <Qt/qstring.h>
#include <Qt/qhash.h>
#include <simplevector.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <adamsbasic.h>

class BuildOper;

typedef SimpleVector<long> LongSVector;			// used as local type fast vectors
typedef SimpleVector<QString> QStringSVector;
typedef SimpleVector<BuildOper> OperSVector;
#ifndef _SHAREDTYPES
#define _SHAREDTYPES
typedef QHash<int, BuildOper *> OperDirect;
#endif
typedef QHash<int, BuildOper *>::iterator OperDirectIterator;

class BuildOper {
public:
	BuildOper() :	elemId ( 0 ),
		type ( tAuto ),
		oper ( poNOCOMP ),
		priority ( 0 ),
		hasPlugin ( false ),
		numV ( 0 ),
		strV ( 0 ),
		plugin ( 0 ),
		filterDisabled ( false ) {
		memset ( ( void * ) scripts, 0, sizeof ( scripts ) );
	}
	~BuildOper() {}
	inline void setPluginUsage ( const DEHandlerWorkerAPIData & api ) {
		for ( int i = 0; i < teOp_numOps; i++ ) useFor[i] = api.useFor[i];
	}

	int elemId;
	QString name;
	SimpleTypesEnum type;
	UsrParamOperator oper;
	int priority;
	bool hasPlugin;
	bool useFor[teOp_numOps];
	bool filterDisabled;

	LongSVector * numV;
	QStringSVector * strV;
	AdamsBasic * scripts[Script::sa_Number];
	PluginImpl * plugin;
private:
};

#endif
