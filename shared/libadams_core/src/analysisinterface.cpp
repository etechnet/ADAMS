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

#include <analysisinterface.h>
#include <applogger.h>

AnalysisInterface::AnalysisInterface() : QObject(), uniq ( 0 )
{
	Analyzes = new QMultiHash<QString, Analysis *>;
	AnalysisNumber = 0;
}

AnalysisInterface::~AnalysisInterface()
{
	if ( Analyzes ) {
		clear();
		delete Analyzes;
	}
}

/**
  * Inserisce una nuova analysis nel hashtable
  * ed incrementa il numero di analysis.
**/
bool AnalysisInterface::add ( Analysis * newAnalysis )
{
	Analyzes->insert ( QString::number ( newAnalysis->data.idAnalysis ), newAnalysis );
	AnalysisNumber++;
	return false;
}
/**
  * Rimouve una nuova analysis dal hashtable
  * ed decrementa il numero di analysis.
**/
bool AnalysisInterface::remove ( int AnalysisNum )
{
	Analysis *la = Analyzes->find ( QString::number ( AnalysisNum ) ).value();
	if ( la == ( Analysis * ) 0 ) {
		return true;
	}
	Analyzes->remove ( QString::number ( AnalysisNum ) );
	delete la;
	AnalysisNumber--;
	return false;
}
/**
  * Restituisce una specifica analysis.
**/
Analysis * AnalysisInterface::get ( int AnalysisNum )
{
	return Analyzes->find ( QString::number ( AnalysisNum ) ).value();
}

// get by tag

Analysis * AnalysisInterface::getByTag ( const QString & srcTag )
{
	if ( Analyzes->count() > 0 ) {
		foreach (Analysis * a, *Analyzes) {
			if ( a->data.ShortDescription == srcTag )
				return a;
		}
	}
	return ( Analysis * ) 0;
}

// get tag list

TagList AnalysisInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( Analyzes->count() > 0 ) {
		foreach (Analysis * a, *Analyzes) {
			tagList.values.append ( QString::number ( a->data.idAnalysis ) );
			tagList.labels.append ( QString ( a->data.ShortDescription ) );
		}
	}

	return tagList;
}

/**
  * Ritorna il Tag relativo alle analysis.
**/
const char * AnalysisInterface::getHeaderTag()
{
	static const char *ht = ANALYSIS_HEADERTAG;

	return ht;
}

// setup i/e tags

void AnalysisInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void AnalysisInterface::ieExport ( ImportExport & ie )
{
	if ( Analyzes->count() > 0 ) {
		foreach (Analysis * a, *Analyzes) {
			ie.initWriteRecord ( ANALYSIS_HEADERTAG );

			ie.setWriteTag ( "idAnalysis" );
			ie.addWriteRecord ( a->data.idAnalysis );
			ie.setWriteTag ( "FlagArchiveData" );
			ie.addWriteRecord ( a->data.FlagArchiveData );
			ie.setWriteTag ( "FlagCentrale" );
			ie.addWriteRecord ( a->data.FlagCentrale );
			ie.setWriteTag ( "FlagDate" );
			ie.addWriteRecord ( a->data.FlagDate );
			ie.setWriteTag ( "FlagOutputType" );
			ie.addWriteRecord ( a->data.FlagOutputType );
			ie.setWriteTag ( "FlagTimeRes" );
			ie.addWriteRecord ( a->data.FlagTimeRes );
			ie.setWriteTag ( "FlagTrafficType" );
			ie.addWriteRecord ( a->data.FlagTrafficType );
			ie.setWriteTag ( "FlagSort" );
			ie.addWriteRecord ( a->data.FlagSort );
			ie.setWriteTag ( "FlagCumulativeData" );
			ie.addWriteRecord ( a->data.FlagCumulativeData );
			ie.setWriteTag ( "LongDescription" );
			ie.addWriteRecord ( a->data.LongDescription );
			ie.setWriteTag ( "ShortDescription" );
			ie.addWriteRecord ( a->data.ShortDescription );
			ie.setWriteTag ( "relations" );
			ie.addArrayWriteRecord ( a->data.relations, MAX_RELATION );
			ie.setWriteTag ( "countersKitId" );
			ie.addWriteRecord ( a->data.countersKitId );
			ie.setWriteTag ( "reportsId" );
			ie.addArrayWriteRecord ( a->data.reportsId, MAX_ANALYSIS_REPORTS );

			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void AnalysisInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != ANALYSIS_HEADERTAG )		// not a record of mine
		return;

	Analysis * fld = new Analysis;

	fld->data.idAnalysis = ieptr->getIntToken ( "idAnalysis" );
	fld->data.FlagArchiveData = ieptr->getBoolToken ( "FlagArchiveData" );
	fld->data.FlagCentrale = ieptr->getBoolToken ( "FlagCentrale" );
	fld->data.FlagDate = ieptr->getBoolToken ( "FlagDate" );
	fld->data.FlagOutputType = ieptr->getBoolToken ( "FlagOutputType" );
	fld->data.FlagTimeRes = ieptr->getBoolToken ( "FlagTimeRes" );
	fld->data.FlagTrafficType = ieptr->getBoolToken ( "FlagTrafficType" );
	fld->data.FlagSort = ieptr->getBoolToken ( "FlagSort" );
	fld->data.FlagCumulativeData = ieptr->getBoolToken ( "FlagCumulativeData" );
	ieptr->getStrToken ( fld->data.LongDescription, "LongDescription", LONG_DESC_LEN );
	ieptr->getStrToken ( fld->data.ShortDescription, "ShortDescription", SHORT_DESC_LEN );
	ieptr->getIntArrToken ( fld->data.relations, "relations", MAX_RELATION );
	fld->data.countersKitId = ieptr->getIntToken ( "countersKitId" );
	ieptr->getIntArrToken ( fld->data.reportsId, "reportsId", MAX_ANALYSIS_REPORTS );

	add ( fld );
	return;
}

void AnalysisInterface::copyToCorba ( AnalisysSeq * seqptr )
{
	DATA_ANALYSISTYPE rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach (Analysis * a, *Analyzes) {

		rec.idAnalysis = a->data.idAnalysis;
		rec.FlagArchiveData = a->data.FlagArchiveData;
		rec.FlagCentrale = a->data.FlagCentrale;
		rec.FlagDate = a->data.FlagDate;
		rec.FlagOutputType = a->data.FlagOutputType;
		rec.FlagTimeRes = a->data.FlagTimeRes;
		rec.FlagTrafficType = a->data.FlagTrafficType;
		rec.FlagSort = a->data.FlagSort;
		rec.FlagCumulativeData = a->data.FlagCumulativeData;
		c_qstrncpy ( rec.LongDescription, a->data.LongDescription, LONG_DESC_LEN );
		c_qstrncpy ( rec.ShortDescription, a->data.ShortDescription, SHORT_DESC_LEN );
		for ( int i = 0; i < MAX_RELATION; i++ )
			rec.relations[i] = a->data.relations[i];
		rec.countersKitId = a->data.countersKitId;
		for ( int i = 0; i < MAX_ANALYSIS_REPORTS; i++ )
			rec.reportsId[i] = a->data.reportsId[i];

		( *seqptr ) [seqidx++] = rec;
	}
}

void AnalysisInterface::fillFromCorba ( const AnalisysSeq * seqptr )
{
	Analysis * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new Analysis;

		rec->data.idAnalysis = ( *seqptr ) [cnt].idAnalysis;
		rec->data.FlagArchiveData = ( *seqptr ) [cnt].FlagArchiveData;
		rec->data.FlagCentrale = ( *seqptr ) [cnt].FlagCentrale;
		rec->data.FlagDate = ( *seqptr ) [cnt].FlagDate;
		rec->data.FlagOutputType = ( *seqptr ) [cnt].FlagOutputType;
		rec->data.FlagTimeRes = ( *seqptr ) [cnt].FlagTimeRes;
		rec->data.FlagTrafficType = ( *seqptr ) [cnt].FlagTrafficType;
		rec->data.FlagSort = ( *seqptr ) [cnt].FlagSort;
		rec->data.FlagCumulativeData = ( *seqptr ) [cnt].FlagCumulativeData;
		c_qstrncpy ( rec->data.LongDescription, ( *seqptr ) [cnt].LongDescription, LONG_DESC_LEN );
		c_qstrncpy ( rec->data.ShortDescription, ( *seqptr ) [cnt].ShortDescription, SHORT_DESC_LEN );
		for ( int i = 0; i < MAX_RELATION; i++ )
			rec->data.relations[i] = ( *seqptr ) [cnt].relations[i];
		rec->data.countersKitId = ( *seqptr ) [cnt].countersKitId;
		for ( int i = 0; i < MAX_ANALYSIS_REPORTS; i++ )
			rec->data.reportsId[i] = ( *seqptr ) [cnt].reportsId[i];

		add ( rec );
	}

	AnalysisNumber = count();
}


