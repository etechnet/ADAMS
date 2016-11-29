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

#ifndef DATAELEMENT_H
#define DATAELEMENT_H

#include <adams_limits.h>
#include <commontypes.h>

static const char * guiTypeDesc[] = {
		"Undefine",
		"TextField",
		"ValueList",
		"OptionList",
		"Slider",
		"Plugin"
	};

static const char * selectCompareDesc[] = {
		"Disabled",
		"==",
		">",
		"<",
		">=",
		"<="
	};


class DataElement {
public:
	DataElement();
	~DataElement();

        enum guiType
        {
                Undefined,
                TextField,
                ValueList,
                OptionList,
                Slider,
		Plugin,
                guiTypeNumber
        };

	enum selectCompare
	{
		Disabled,
		Equal,
		Greater,
		Lesser,
		Greater_Or_Equal,
		Lesser_Or_Equal,
		numSelectCompare
	};

	enum scriptUsage {
		inInitPhase,
		inFieldEvaluation,
		inKeyBuilding,
		inFiltering,
		inReportProduction,
		inCustom,
		scriptUsageNumber
	};

	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int	idElement;					// identificativo univoco
		char	shortDescription[SHORT_DESC_LEN];		// descrizione breve (tag)
		char	longDescription[LONG_DESC_LEN];			// descrizione
		int	guiObject;					// tipo di oggetto GUI
		char	pixmapFileName[PIXMAP_FILENAME_LEN];		// pixmap associata
		char	valueRange[VALUE_RANGE_LEN];			// range di valori
		double	valueList[MAX_OPTIONS];				// valori in lista
		char	valueListLabel[MAX_OPTIONS][OPTION_LABEL_LEN];	// etichette associate ai valori (valueList)
		double	defaultValue;					// selezione di default
		bool	acceptHex;					// accetta valori esadecimali
		int	priority;					// livello priorita'
		int	aggregateRestrictions[MAX_AGGREGATE_RESTR];	// sottorestrizioni
		int	exceptions[MAX_EXCEPTIONS];			// eccezioni
		int	idDRLink;					// link base dati
		int	elementType;					// tipologia dati (SOLO se idCDRlink == 0)
		int	compareSelection;				// selezione operatore confronto
		char    Suffix[TE_SUFFIX_LEN];				// suffisso usato per la creazione dei file indici.
		int     LengthInRelation;				// Indica il numero di cifre che occupa in una relazione.
		char	valueShifter[VALSHIFTER_MAX][VALSHIFTER_LEN*3];	// Ranges for values shifter
		int	scripts[MAX_TE_SCRIPTS];			// te Scripts
		int	idPlugin;					// Id of the handler plugin
		int	idPluginGUI;					// Id of the GUI handler plugin
		bool	repeatKey;					// force key show on report
		bool	ffEnabled;					// available for free-format relations
	} DATA;

	DATA data;
	bool Delete;

};

#endif
