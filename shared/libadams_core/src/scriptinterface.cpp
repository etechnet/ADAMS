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

#include <scriptinterface.h>
#include <applogger.h>


ScriptInterface::ScriptInterface() : QObject(), uniq(0), Scripts(0)
{
	Scripts = new QMultiHash<QString, Script *>;
	ScriptsNumber = 0;
}

ScriptInterface::~ScriptInterface()
{
	if (Scripts) {
		clear();
		delete Scripts;
	}
}

/**
  * Inserisce una nuova eccezione nel hashtable
  * ed incrementa il numero di eccezioni.
**/
bool ScriptInterface::add(Script * newScript)
{
     Scripts->insert(QString::number(newScript->data.idScript),newScript);
     ScriptsNumber++;
     return false;
}
/**
  * Rimouve una eccezione dal hashtable
  * ed decrementa il numero di eccezioni.
**/
bool ScriptInterface::remove(int ScriptNum)
{
	Script *scp = ( *Scripts->find(QString::number(ScriptNum)));
	if (scp == (Script *)0)
	{
		return true;
	}
	Scripts->remove(QString::number(ScriptNum));
	delete scp;
	ScriptsNumber--;
	return false;
}
/**
  * Restituisce un specifico elemento.
**/
Script * ScriptInterface::get(int ScriptNum)
{
	return ( *Scripts->find(QString::number(ScriptNum)) );
}

/**
  * Ritorna il Tag relativo alle eccezioni.
**/
const char * ScriptInterface::getHeaderTag()
{
	static const char *ht = SCRIPT_HEADERTAG;

	return ht;
}

// get by tag

Script * ScriptInterface::getByTag(const QString & srcTag)
{
	if (Scripts->count() > 0) {
		foreach (Script * s, *Scripts) {
			if (s->data.tag == srcTag)
				return s;
		}
	}
	return (Script *)0;
}

// get tag list

TagList ScriptInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if (Scripts->count() > 0) {
		foreach (Script * s, *Scripts) {
			tagList.values.append(QString::number(s->data.idScript));
			tagList.labels.append(QString(s->data.tag));
		}
	}

	return tagList;
}




// setup i/e tags

void ScriptInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void ScriptInterface::ieExport(ImportExport & ie)
{
	if (Scripts->count() > 0) {
		foreach (Script * s, *Scripts) {
			ie.initWriteRecord(SCRIPT_HEADERTAG);

			ie.setWriteTag("idScript"); ie.addWriteRecord(s->data.idScript);
			ie.setWriteTag("tag"); ie.addWriteRecord(s->data.tag);
			ie.setWriteTag("dataOrigin"); ie.addWriteRecord(s->data.dataOrigin);
			ie.setWriteTag("variables"); ie.addWriteRecord(s->data.variables);
			ie.setWriteTag("scriptText"); ie.addWriteRecord(s->data.scriptText);
			ie.setWriteTag("resultVariableName"); ie.addWriteRecord(qPrintable(s->data.resultVariableName));
			ie.setWriteTag("resultType"); ie.addWriteRecord(s->data.resultType);

			ie.flushWriteRecord();
		}
	}
}


// import a record from ie stream

void ScriptInterface::ieImport(QString & ieRecordTag, ImportExport * ieptr)
{
	if (ieRecordTag != SCRIPT_HEADERTAG)		// not a record of mine
		return;

	Script *fld = new Script;

	fld->data.idScript = ieptr->getIntToken("idScript");
	ieptr->getStrToken(fld->data.tag, "tag", SHORT_DESC_LEN);
	fld->data.dataOrigin = (Script::scriptDataOrigin)ieptr->getIntToken("dataOrigin");
	ieptr->getQStringListToken(fld->data.variables, "variables");
	ieptr->getQStringListToken(fld->data.scriptText, "scriptText");
	fld->data.resultVariableName = ieptr->getQStringToken("resultVariableName");
	fld->data.resultType = (SimpleTypesEnum)ieptr->getIntToken("resultType");

	add(fld);
	return;
}

void ScriptInterface::copyToCorba(ScriptSeq * seqptr)
{
	DATA_SCRIPT rec;

	seqptr->length(count());
	int seqidx = 0;

	foreach (Script * s, *Scripts) {
		rec.idScript = s->data.idScript;
		c_qstrncpy(rec.tag, s->data.tag, SHORT_DESC_LEN);
		rec.dataOrigin = s->data.dataOrigin;

		rec.variables.length(s->data.variables.count());
		for (int i = 0; i < s->data.variables.count(); i++) {
			c_qstrncpy(rec.variables[i].d, qPrintable(s->data.variables[i]), SCRIPT_VARNAME_LEN);
		}

		rec.scriptText.length(s->data.scriptText.count());
		for (int i = 0; i < s->data.scriptText.count(); i++) {
			c_qstrncpy(rec.scriptText[i].d, qPrintable(s->data.scriptText[i]), SCRIPT_TEXT_LEN);
		}

		c_qstrncpy(rec.resultVariableName, qPrintable(s->data.resultVariableName), SCRIPT_VARNAME_LEN);
		rec.resultType = s->data.resultType;

		(*seqptr)[seqidx++] = rec;
	}
}

void ScriptInterface::fillFromCorba(const ScriptSeq * seqptr)
{
	Script * rec;
	clear();

	for (int cnt = 0; cnt < seqptr->length(); cnt++) {
		rec = new Script;

		rec->data.idScript = (*seqptr)[cnt].idScript;
		c_qstrncpy(rec->data.tag, (*seqptr)[cnt].tag, SHORT_DESC_LEN);
		rec->data.dataOrigin = (Script::scriptDataOrigin)(*seqptr)[cnt].dataOrigin;

		for (int i = 0; i < (*seqptr)[cnt].variables.length(); i++) {
			rec->data.variables << (*seqptr)[cnt].variables[i].d;
		}

		for (int i = 0; i < (*seqptr)[cnt].scriptText.length(); i++) {
			rec->data.scriptText << (*seqptr)[cnt].scriptText[i].d;
		}

		rec->data.resultVariableName = (*seqptr)[cnt].resultVariableName;
		rec->data.resultType = (SimpleTypesEnum)(*seqptr)[cnt].resultType;

		add(rec);
	}

	ScriptsNumber = count();
}

