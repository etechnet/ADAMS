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

#ifndef SCRIPT_H
#define SCRIPT_H

#include <adams_limits.h>
#include "commontypes.h"
#include <qstringlist.h>

/**In questa classe vienhe definita la struttura di base per trattare
  *la configurazione dello scripting.
  *@author Piergiorgio Betti
  */

class Script {
public:
	enum scriptDataOrigin {
		sdoFromCDR,
		sdoFromCounters,
		sdoFromBoth,
		sdo_Number
	};

	enum scriptApplication {
		saInitPhase,
		saFieldEvaluation,
		saKeyBuilding,
		saFiltering,
		saReportProduction,
		saAlarmCondition,
		saCustom,
		sa_Number
	};

	Script() { data.idScript = 0; data.tag[0] = '\0'; data.dataOrigin = sdo_Number; data.variables.clear();
	           data.scriptText.clear(); data.resultVariableName = ""; data.resultType = t_Number; }
	~Script() {}

		/** Metodo statico utilizzato per avere la dimensione in byte della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int idScript;					// unique script id
		char tag[SHORT_DESC_LEN];			// script name
		scriptDataOrigin dataOrigin;			// where to valorize external global variables
		QStringList variables;				// global variable list
		QStringList scriptText;				// ntmbasic script text
		QString resultVariableName;			// script valorized result variable name
		SimpleTypesEnum resultType;			// kind of result variable
	} DATA;

	typedef struct {
		int idScript;
		char tag[SHORT_DESC_LEN];
		scriptDataOrigin dataOrigin;
		int numVariables;
		int numScriptText;
		char variables[SCRIPT_MAX_VAR][SCRIPT_VARNAME_LEN];
		char scriptText[SCRIPT_MAX_TEXT][SCRIPT_TEXT_LEN];
		char resultVariableName[SCRIPT_VARNAME_LEN];
		SimpleTypesEnum resultType;
	} DATA_LEGACY_RECORD;

	DATA data;

};


#endif
