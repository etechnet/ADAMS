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

#include <dataelementinterface.h>
#include <applogger.h>
#include <stdlib.h>

DataElementInterface::DataElementInterface() : QObject(), uniq ( 0 )
{
	Elements = new QMultiHash<QString, DataElement *>;
	ElementsNumber = 0;
	shifterTestBuf[0] = '\0';
	for ( int i = 0; i < VALSHIFTER_LEN * 3; i++ )
		strcat ( shifterTestBuf, "0" );
}

DataElementInterface::~DataElementInterface()
{
	if ( Elements ) {
		clear();
		delete Elements;
	}
}

/**
  * Inserisce un nuovo elemento nel hashtable
  * ed incrementa il numero di elementi.
**/
bool DataElementInterface::add ( DataElement * newElement )
{
	Elements->insert ( QString::number ( newElement->data.idElement ), newElement );
	ElementsNumber++;
	return false;
}

/**
  * Test per la presenza di shifter attivi
  */

int DataElementInterface::hasShifter ( int id )
{
	DataElement *ele = Elements->find ( QString::number ( id ) ).value();
	int cnt = 0;

	if ( ele ) {
		while ( strncmp ( ele->data.valueShifter[cnt], shifterTestBuf, VALSHIFTER_LEN * 3 ) && cnt < VALSHIFTER_MAX ) {
			++cnt;
		}
		return cnt;
	} else
		return 0;
}

/** extract shifter ranges */

SHIFTRANGE * DataElementInterface::getShifters ( int id )
{
	DataElement *ele = Elements->find ( QString::number ( id ) ).value();
	if ( !ele )
		return ( SHIFTRANGE * ) 0;
	SHIFTRANGE * shifter = new SHIFTRANGE [VALSHIFTER_MAX];
	if ( hasShifter ( id ) ) {
		char buf[VALSHIFTER_LEN + 1];
		for ( int i = 0; i < VALSHIFTER_MAX; i++ ) {
			memcpy ( buf, ele->data.valueShifter[i], VALSHIFTER_LEN );
			buf[VALSHIFTER_LEN] = '\0';
			shifter[i].shiftVal = atol ( buf );
			memcpy ( buf, ele->data.valueShifter[i] + VALSHIFTER_LEN, VALSHIFTER_LEN );
			shifter[i].from = atol ( buf );
			memcpy ( buf, ele->data.valueShifter[i] + ( VALSHIFTER_LEN * 2 ), VALSHIFTER_LEN );
			shifter[i].to = atol ( buf );
		}
		return shifter;
	} else
		return ( SHIFTRANGE * ) 0;
}


/**
  * Rimouve un elemento dal hashtable
  * ed decrementa il numero di elementi.
**/
bool DataElementInterface::remove ( int ElementNum )
{
	DataElement *ele = Elements->find ( QString::number ( ElementNum ) ).value();
	if ( ele == ( DataElement * ) 0 ) {
		return true;
	}
	Elements->remove ( QString::number ( ElementNum ) );
	delete ele;
	ElementsNumber--;
	return false;
}
/**
  * Restituisce un specifico elemento.
**/
DataElement * DataElementInterface::get ( int ElementNum )
{
	return Elements->find ( QString::number ( ElementNum ) ).value();
}
/**
  * Ritorna il Tag relativo alle relazioni.
**/
const char * DataElementInterface::getHeaderTag()
{
	static const char *ht = ELEMENT_HEADERTAG;

	return ht;
}

// get by tag

DataElement * DataElementInterface::getByTag ( const QString & srcTag )
{
	if ( Elements->count() > 0 ) {
		foreach ( DataElement * e, *Elements ) {
			if ( e->data.shortDescription == srcTag )
				return e;
		}
	}
	return ( DataElement * ) 0;
}

// get tag list

TagList DataElementInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( Elements->count() > 0 ) {
		foreach ( DataElement * e, *Elements ) {
			tagList.values.append ( QString::number ( e->data.idElement ) );
			tagList.labels.append ( QString ( e->data.shortDescription ) );
		}
	}

	return tagList;
}



// setup i/e tags

void DataElementInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void DataElementInterface::ieExport ( ImportExport & ie )
{
	if ( Elements->count() > 0 ) {
		foreach ( DataElement * e, *Elements ) {
			ie.initWriteRecord ( ELEMENT_HEADERTAG );

			ie.setWriteTag ( "idElement" );
			ie.addWriteRecord ( e->data.idElement );
			ie.setWriteTag ( "shortDescription" );
			ie.addWriteRecord ( e->data.shortDescription );
			ie.setWriteTag ( "longDescription" );
			ie.addWriteRecord ( e->data.longDescription );
			ie.setWriteTag ( "guiObject" );
			ie.addWriteRecord ( e->data.guiObject );
			ie.setWriteTag ( "pixmapFileName" );
			ie.addWriteRecord ( e->data.pixmapFileName );
			ie.setWriteTag ( "valueRange" );
			ie.addWriteRecord ( e->data.valueRange );
			ie.setWriteTag ( "valueList" );
			ie.addArrayWriteRecord ( e->data.valueList, MAX_OPTIONS );
			ie.setWriteTag ( "valueListLabel" );
			ie.addArrayWriteRecord ( ( char * ) e->data.valueListLabel, MAX_OPTIONS, OPTION_LABEL_LEN );
			ie.setWriteTag ( "defaultValue" );
			ie.addWriteRecord ( e->data.defaultValue );
			ie.setWriteTag ( "acceptHex" );
			ie.addWriteRecord ( e->data.acceptHex );
			ie.setWriteTag ( "priority" );
			ie.addWriteRecord ( e->data.priority );
			ie.setWriteTag ( "aggregateRestrictions" );
			ie.addArrayWriteRecord ( e->data.aggregateRestrictions, MAX_AGGREGATE_RESTR );
			ie.setWriteTag ( "exceptions" );
			ie.addWriteRecord ( e->data.exceptions );
			ie.setWriteTag ( "idDRLink" );
			ie.addWriteRecord ( e->data.idDRLink );
			ie.setWriteTag ( "elementType" );
			ie.addWriteRecord ( e->data.elementType );
			ie.setWriteTag ( "compareSelection" );
			ie.addWriteRecord ( e->data.compareSelection );
			ie.setWriteTag ( "Suffix" );
			ie.addWriteRecord ( e->data.Suffix );
			ie.setWriteTag ( "LengthInRelation" );
			ie.addWriteRecord ( e->data.LengthInRelation );
			ie.setWriteTag ( "valueShifter" );
			ie.addArrayWriteRecord ( ( char * ) e->data.valueShifter, VALSHIFTER_MAX, VALSHIFTER_LEN * 3, true );
			ie.setWriteTag ( "scripts" );
			ie.addArrayWriteRecord ( e->data.scripts, MAX_TE_SCRIPTS );
			ie.setWriteTag ( "idPlugin" );
			ie.addWriteRecord ( e->data.idPlugin );
			ie.setWriteTag ( "idPluginGUI" );
			ie.addWriteRecord ( e->data.idPluginGUI );
			ie.setWriteTag ( "repeatKey" );
			ie.addWriteRecord ( e->data.repeatKey );
			ie.setWriteTag ( "ffEnabled" );
			ie.addWriteRecord ( e->data.ffEnabled );

			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void DataElementInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != ELEMENT_HEADERTAG )		// not a record of mine
		return;

	DataElement * fld = new DataElement;

	fld->data.idElement = ieptr->getIntToken ( "idElement" );
	ieptr->getStrToken ( fld->data.shortDescription, "shortDescription", SHORT_DESC_LEN );
	ieptr->getStrToken ( fld->data.longDescription, "longDescription", LONG_DESC_LEN );
	fld->data.guiObject = ieptr->getIntToken ( "guiObject" );
	ieptr->getStrToken ( fld->data.pixmapFileName, "pixmapFileName", PIXMAP_FILENAME_LEN );
	ieptr->getStrToken ( fld->data.valueRange, "valueRange", VALUE_RANGE_LEN );
	ieptr->getDoubleArrToken ( fld->data.valueList, "valueList", MAX_OPTIONS );
	ieptr->getStrArrToken ( ( char * ) fld->data.valueListLabel, "valueListLabel", MAX_OPTIONS, OPTION_LABEL_LEN );
	fld->data.defaultValue = ieptr->getDoubleToken ( "defaultValue" );
	fld->data.acceptHex = ieptr->getBoolToken ( "acceptHex" );
	fld->data.priority = ieptr->getIntToken ( "priority" );
	ieptr->getIntArrToken ( fld->data.aggregateRestrictions, "aggregateRestrictions", MAX_AGGREGATE_RESTR );
	ieptr->getIntArrToken ( fld->data.exceptions, "exceptions", MAX_EXCEPTIONS );
	fld->data.idDRLink = ieptr->getIntToken ( "idDRLink" );
	fld->data.elementType = ieptr->getIntToken ( "elementType" );
	fld->data.compareSelection = ieptr->getIntToken ( "compareSelection" );
	ieptr->getStrToken ( fld->data.Suffix, "Suffix", TE_SUFFIX_LEN );
	fld->data.LengthInRelation = ieptr->getIntToken ( "LengthInRelation" );
	ieptr->getStrArrToken ( ( char * ) fld->data.valueShifter, "valueShifter", VALSHIFTER_MAX, VALSHIFTER_LEN * 3, true );
	ieptr->getIntArrToken ( fld->data.scripts, "scripts", MAX_TE_SCRIPTS );
	fld->data.idPlugin = ieptr->getIntToken ( "idPlugin" );
	fld->data.idPluginGUI = ieptr->getIntToken ( "idPluginGUI" );
	fld->data.repeatKey = ieptr->getBoolToken ( "repeatKey" );
	fld->data.ffEnabled = ieptr->getBoolToken ( "ffEnabled" );

	add ( fld );
	return;
}

void DataElementInterface::copyToCorba ( ElementSeq * seqptr )
{
	DATA_DATAELEMENT rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( DataElement * e, *Elements ) {
		rec.idElement = e->data.idElement;
		c_qstrncpy ( rec.shortDescription, e->data.shortDescription, SHORT_DESC_LEN );
		c_qstrncpy ( rec.longDescription, e->data.longDescription, LONG_DESC_LEN );
		rec.guiObject = e->data.guiObject;
		c_qstrncpy ( rec.pixmapFileName, e->data.pixmapFileName, PIXMAP_FILENAME_LEN );
		c_qstrncpy ( rec.valueRange, e->data.valueRange, VALUE_RANGE_LEN );
		for ( int i = 0; i < MAX_OPTIONS; i++ )
			rec.valueList[i] = e->data.valueList[i];
		for ( int i = 0; i < MAX_OPTIONS; i++ )
			c_qstrncpy ( rec.valueListLabel[i], e->data.valueListLabel[i], OPTION_LABEL_LEN );
		rec.defaultValue = e->data.defaultValue;
		rec.acceptHex = e->data.acceptHex;
		rec.priority = e->data.priority;
		for ( int i = 0; i < MAX_AGGREGATE_RESTR; i++ )
			rec.aggregateRestrictions[i] = e->data.aggregateRestrictions[i];
		for ( int i = 0; i < MAX_EXCEPTIONS; i++ )
			rec.exceptions[i] = e->data.exceptions[i];
		rec.idDRLink = e->data.idDRLink;
		rec.elementType = e->data.elementType;
		rec.compareSelection = e->data.compareSelection;
		c_qstrncpy ( rec.Suffix, e->data.Suffix, TE_SUFFIX_LEN );
		rec.LengthInRelation = e->data.LengthInRelation;
		for ( int i = 0; i < VALSHIFTER_MAX; i++ )
			memcpy ( rec.valueShifter[i], e->data.valueShifter[i], VALSHIFTER_LEN * 3 );
		for ( int i = 0; i < MAX_TE_SCRIPTS; i++ )
			rec.scripts[i] = e->data.scripts[i];
		rec.idPlugin = e->data.idPlugin;
		rec.idPluginGUI = e->data.idPluginGUI;
		rec.repeatKey = e->data.repeatKey;
		rec.ffEnabled = e->data.ffEnabled;

		( *seqptr ) [seqidx++] = rec;
	}
}

void DataElementInterface::fillFromCorba ( const ElementSeq * seqptr )
{
	DataElement * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new DataElement;

		rec->data.idElement = ( *seqptr ) [cnt].idElement;
		c_qstrncpy ( rec->data.shortDescription, ( *seqptr ) [cnt].shortDescription, SHORT_DESC_LEN );
		c_qstrncpy ( rec->data.longDescription, ( *seqptr ) [cnt].longDescription, LONG_DESC_LEN );
		rec->data.guiObject = ( *seqptr ) [cnt].guiObject;
		c_qstrncpy ( rec->data.pixmapFileName, ( *seqptr ) [cnt].pixmapFileName, PIXMAP_FILENAME_LEN );
		c_qstrncpy ( rec->data.valueRange, ( *seqptr ) [cnt].valueRange, VALUE_RANGE_LEN );
		for ( int i = 0; i < MAX_OPTIONS; i++ )
			rec->data.valueList[i] = ( *seqptr ) [cnt].valueList[i];
		for ( int i = 0; i < MAX_OPTIONS; i++ )
			c_qstrncpy ( rec->data.valueListLabel[i], ( *seqptr ) [cnt].valueListLabel[i], OPTION_LABEL_LEN );
		rec->data.defaultValue = ( *seqptr ) [cnt].defaultValue;
		rec->data.acceptHex = ( *seqptr ) [cnt].acceptHex;
		rec->data.priority = ( *seqptr ) [cnt].priority;
		for ( int i = 0; i < MAX_AGGREGATE_RESTR; i++ )
			rec->data.aggregateRestrictions[i] = ( *seqptr ) [cnt].aggregateRestrictions[i];
		for ( int i = 0; i < MAX_EXCEPTIONS; i++ )
			rec->data.exceptions[i] = ( *seqptr ) [cnt].exceptions[i];
		rec->data.idDRLink = ( *seqptr ) [cnt].idDRLink;
		rec->data.elementType = ( *seqptr ) [cnt].elementType;
		rec->data.compareSelection = ( *seqptr ) [cnt].compareSelection;
		c_qstrncpy ( rec->data.Suffix, ( *seqptr ) [cnt].Suffix, TE_SUFFIX_LEN );
		rec->data.LengthInRelation = ( *seqptr ) [cnt].LengthInRelation;
		for ( int i = 0; i < VALSHIFTER_MAX; i++ )
			memcpy ( rec->data.valueShifter[i], ( *seqptr ) [cnt].valueShifter[i], VALSHIFTER_LEN * 3 );
		for ( int i = 0; i < MAX_TE_SCRIPTS; i++ )
			rec->data.scripts[i] = ( *seqptr ) [cnt].scripts[i];
		rec->data.idPlugin = ( *seqptr ) [cnt].idPlugin;
		rec->data.idPluginGUI = ( *seqptr ) [cnt].idPluginGUI;
		rec->data.repeatKey = ( *seqptr ) [cnt].repeatKey;
		rec->data.ffEnabled = ( *seqptr ) [cnt].ffEnabled;

		add ( rec );
	}

	ElementsNumber = count();
}

