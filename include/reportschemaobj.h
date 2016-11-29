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

/***************************************************************************
                          reportschemaobj.h  -  description
                             -------------------
    begin                :
    copyright            : (C) 2003 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

 - Date --- - By ---------------- - What -----------------------------------
 20050915   Piergiorgio Betti     Aggiunto numero decimali in gestione body
 ***************************************************************************/

#ifndef _REPORTSCHEMA_H_
#define _REPORTSCHEMA_H_

#include <string.h>

#define RSO_FOOTER_NKEY_VAL		-2		// preset value for number of keys on footer totals
#define RSO_FOOTER_USER_VAL		-3		// preset value for user value string on footer totals

class ReportSchemaObjPrivate
{

public:
	ReportSchemaObjPrivate() {}
	~ReportSchemaObjPrivate() {}

	enum VertAlign {
		VCenter,
		Upper,
		Lower,
		numVertAlign
	};

	enum HoriAlign {
		HCenter,
		Left,
		Right,
		Justified,
		numHoriAlign
	};

	enum FontStyle {
		BodyText,
		Paragraph,
		Title1,
		Title2,
		Title3,
		Title4,
		Title5,
		Title6,
		Address,
		Preformatted,
		numFontStyle
	};

	enum HeaderValue {
		hvUndefined,
		Logo,
		Title,
		ElaborationDate,
		Nodes,
		ReportName,
		NumberKeys,
		AnalisysName,
		DataFormat,
		SortType,
		DataType,
		Network,
		RelationName,
		RestrictionsList,
		UserDefined,
		numHeaderValue
	};

	enum DataSource {
		dsUndefined,
		KeyPart,
		CounterKit,
		Script,
		Plugin,
		User,
		numDataSource

	};

	enum TotalizerSource {
		tsUndefined,
		ProcessedKeys,
		UserData,
		Other,
		numTotalizerSource
	};

	enum BreakingType {
		NoBreak,
		BlankLine,
		BreakLine,
		LineLine
	};

	typedef struct {
		int r;
		int g;
		int b;
	} RGB;

	typedef struct {
		char tag[SHORT_DESC_LEN];
		bool hasBorder;
		int excelCSV;
		bool simpleBody;
		int idAnalisys;
		bool usePlugin;
		char pluginName[SHORT_PLUGIN_NAME];
		RGB defaultForeground;
		RGB defaultBackground;
	} OBJECTDATA;

	typedef struct {
		int vSize;
		int hSize;
		VertAlign vAlign;
		HoriAlign hAlign;
		bool wrap;
		RGB foreground;
		RGB background;
		FontStyle style;
		int fontSize;
		bool bold;
		bool italic;
		bool underline;
	} HTMLDATA;

	typedef struct {
		char userLabel[REPO_LABEL_LEN];
		HeaderValue value;
		char userValue[REPO_USER_LEN];
		bool isUrl;
		BreakingType line;
	} HEADERDATA;

	typedef struct {
		DataSource source;
		int idKey;
		int idCounter;
		int idScript;
		int idPlugin;
		char userValue[REPO_USER_LEN];
		bool isUrl;
		bool noTotals;
		bool repeatKey;
		int decimals;
		BreakingType line;
	} BODYDATA;

	typedef struct {
		int trigger;
		char label[REPO_LABEL_LEN];
		bool redrawHeader;
		bool border;
		BreakingType line;
		bool useScript;
		int idScript;
	} TOTALIZERDATA;

	typedef struct {
		int source;
		char label[REPO_LABEL_LEN];
		char userValue[REPO_USER_LEN];
		bool isUrl;
		BreakingType line;
	} FOOTERDATA;

	typedef union {
		OBJECTDATA object;
		HEADERDATA header;
		BODYDATA body;
		TOTALIZERDATA totalizer;
		FOOTERDATA footer;
	} REPOCELLUNION;
};

/**
 * Questa classe definisce l'implementazione dei dati necessari a definire uno schema di report programmabile
   @short Struttura dati di definizione di una componente di uno schema report
 * @author Piergiorgio Betti
 **/
class ReportSchemaObj : public ReportSchemaObjPrivate
{

public:
	enum CellObject {
		ObjectT,
		HeaderT,
		BodyT,
		TotalizerT,
		FooterT
	};

	typedef struct {
		int idObject;
		int idReportSchema;
		CellObject type;
		char tag[REPO_LABEL_LEN];
		HTMLDATA html;
		REPOCELLUNION u;
	} DATA;

	DATA data;
	void * extHook;			// simple link to external hooks (i.e. widgets)

	ReportSchemaObj() : extHook(0) { _init(); }
	ReportSchemaObj(CellObject t) : extHook(0) { _init(); init(t); }
	~ReportSchemaObj() {}

	static int dataSize() { return sizeof(DATA); }
	inline void _init() { memset((void*)&data, 0, sizeof(DATA)); setForeground(); setBackground(); }
	inline void init(CellObject t) { switch (t) {
	                                   case ObjectT:    data.u.object.defaultForeground.r=data.u.object.defaultForeground.g=data.u.object.defaultForeground.b=0;data.u.object.defaultBackground.r=data.u.object.defaultBackground.g=data.u.object.defaultBackground.b=255;break;
					   case HeaderT:    break;
					   case BodyT:      data.u.body.idKey = data.u.body.idCounter = data.u.body.idScript = data.u.body.idPlugin = -1; break;
					   case TotalizerT: data.u.totalizer.trigger = -1; break;
					   case FooterT:    break;
	                                 }
					 data.type = t;
				       }
	inline bool operator== (const ReportSchemaObj & t) { return (t.data.idObject==data.idObject && t.data.idReportSchema==data.idReportSchema && t.data.type==data.type); }
	inline ReportSchemaObj & operator= (const ReportSchemaObj & t) { data = t.data; extHook = t.extHook; return *this;  }
	inline RGB & foreground() { return data.html.foreground; }
	inline RGB & background() { return data.html.background; }
	inline void setForeground(int ar = -1, int ag = -1, int ab = -1) { data.html.foreground.r = ar; data.html.foreground.g = ag; data.html.foreground.b = ab; }
	inline void setBackground(int ar = -1, int ag = -1, int ab = -1) { data.html.background.r = ar; data.html.background.g = ag; data.html.background.b = ab; }
	inline bool isBreaker() {
		switch (data.type) {
		  case HeaderT: return (data.u.header.line != NoBreak);
		  case BodyT: return (data.u.body.line != NoBreak);
		  case TotalizerT: return (data.u.totalizer.line != NoBreak);
		  case FooterT: return (data.u.footer.line != NoBreak);
		}
		return false;
	}
};

#endif
