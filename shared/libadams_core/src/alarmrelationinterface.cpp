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

#include <alarmrelationinterface.h>
#include <applogger.h>

AlarmRelationInterface::AlarmRelationInterface() : uniq ( 0 ), teReference ( 0 )
{
	AlarmRelations = new QMultiHash<QString, alarmrelation *>;
	AlarmRelationsNumber = 0;
}

AlarmRelationInterface::~AlarmRelationInterface()
{
	if ( AlarmRelations ) {
		clear();
		delete AlarmRelations;
	}
}

/**
  * Inserisce una nuova relazione nel hashtable
  * ed incrementa il numero di relazioni.
**/
bool AlarmRelationInterface::add ( alarmrelation * newRelation )
{
	AlarmRelations->insert ( QString::number ( newRelation->data.idAlarmRelation ), newRelation );
	AlarmRelationsNumber++;
	return false;
}
/**
  * Rimouve una nuova relazione dal hashtable
  * e decrementa il numero di relazioni.
**/
bool AlarmRelationInterface::remove ( int RelationNum )
{
	alarmrelation *lr = AlarmRelations->find ( QString::number ( RelationNum ) ).value();
	if ( lr == ( alarmrelation * ) 0 ) {
		return true;
	}
	AlarmRelations->remove ( QString::number ( RelationNum ) );
	delete lr;
	AlarmRelationsNumber--;
	return false;
}
/**
  * Restituisce una specifica relazione.
**/
alarmrelation * AlarmRelationInterface::get ( int RelationNum )
{
	return AlarmRelations->find ( QString::number ( RelationNum ) ).value();
}

// get by tag

alarmrelation * AlarmRelationInterface::getByTag ( const QString & srcTag )
{
	if ( AlarmRelations->count() > 0 ) {
		foreach ( alarmrelation * ar, *AlarmRelations ) {
			if ( decodeRel ( ar->data.idAlarmRelation ) == srcTag )
				return ar;
		}
	}
	return ( alarmrelation * ) 0;
}




// crea lista per tag

TagList AlarmRelationInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( AlarmRelations->count() > 0 ) {
		foreach ( alarmrelation * ar, *AlarmRelations ) {
			tagList.values.append ( QString::number ( ar->data.idAlarmRelation ) );
			tagList.labels.append ( decodeRel ( ar->data.idAlarmRelation ) );
		}
	}

	return tagList;
}


QString AlarmRelationInterface::decodeRel ( int relid, const char * sep )
{
	QString relation_desc;
	alarmrelation *rel = get ( relid );
	QString q_sep = ( sep ) ? sep : "::";

	if ( rel == 0 || teReference == 0 )
		return QString ( "" );

	DataElement * ptr1;
	int i = 0;
	while ( rel->data.relationElements[i] > 0 && i < MAX_DIMENSION ) {

		ptr1 = teReference->get ( rel->data.relationElements[i] );

		if ( ptr1 )
			relation_desc += QString ( ptr1->data.shortDescription );
		if ( rel->data.relationElements[i + 1] > 0 && i + 1 < MAX_DIMENSION ) {
			if ( ptr1 )
				relation_desc += q_sep;
		}
		++i;
	}

	return relation_desc;
}


/**
  * Ritorna il Tag relativo alle relazioni.
**/
const char * AlarmRelationInterface::getHeaderTag()
{
	static const char *ht = ALARMRELATION_HEADERTAG;

	return ht;
}

// setup i/e tags

void AlarmRelationInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void AlarmRelationInterface::ieExport ( ImportExport & ie )
{
	if ( AlarmRelations->count() > 0 ) {
		foreach ( alarmrelation * ar, *AlarmRelations ) {
			ie.initWriteRecord ( ALARMRELATION_HEADERTAG );

			ie.setWriteTag ( "idAlarmRelation" );
			ie.addWriteRecord ( ar->data.idAlarmRelation );
			ie.setWriteTag ( "description" );
			ie.addWriteRecord ( ar->data.description );
			ie.setWriteTag ( "relationElements" );
			ie.addArrayWriteRecord ( ar->data.relationElements, MAX_DIMENSION );
			ie.setWriteTag ( "relationElementsEnabled" );
			ie.addArrayWriteRecord ( ar->data.relationElementsEnabled, MAX_DIMENSION );
			ie.setWriteTag ( "alarmHandlers" );
			ie.addArrayWriteRecord ( ar->data.alarmHandlers, MAX_ALARM_GENERATOR );
			ie.setWriteTag ( "timeFractionElementId" );
			ie.addWriteRecord ( ar->data.timeFractionElementId );
			ie.setWriteTag ( "familyId" );
			ie.addWriteRecord ( ar->data.familyId );
			ie.setWriteTag ( "countersKitId" );
			ie.addWriteRecord ( ar->data.countersKitId );
			ie.setWriteTag ( "idConditionScript" );
			ie.addWriteRecord ( ar->data.idConditionScript );
			ie.setWriteTag ( "idConditionPlugin" );
			ie.addWriteRecord ( ar->data.idConditionPlugin );

			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void AlarmRelationInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != ALARMRELATION_HEADERTAG )		// not a record of mine
		return;

	alarmrelation *fld = new alarmrelation;

	fld->data.idAlarmRelation = ieptr->getIntToken ( "idAlarmRelation" );
	ieptr->getStrToken ( fld->data.description, "description", LONG_DESC_LEN );
	ieptr->getIntArrToken ( fld->data.relationElements, "relationElements", MAX_DIMENSION );
	ieptr->getIntArrToken ( fld->data.relationElementsEnabled, "relationElementsEnabled", MAX_DIMENSION );
	ieptr->getIntArrToken ( fld->data.alarmHandlers, "alarmHandlers", MAX_ALARM_GENERATOR );
	fld->data.timeFractionElementId = ieptr->getIntToken ( "timeFractionElementId" );
	fld->data.familyId = ieptr->getIntToken ( "familyId" );
	fld->data.countersKitId = ieptr->getIntToken ( "countersKitId" );
	fld->data.idConditionScript = ieptr->getIntToken ( "idConditionScript" );
	fld->data.idConditionPlugin = ieptr->getIntToken ( "idConditionPlugin" );

	add ( fld );
	return;
}

void AlarmRelationInterface::copyToCorba ( AlarmRelationSeq * seqptr )
{
	ALARM_RELATION rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( alarmrelation * ar, *AlarmRelations ) {
		rec.idAlarmRelation = ar->data.idAlarmRelation;
		c_qstrncpy ( rec.description, ar->data.description, LONG_DESC_LEN );
		for ( int i = 0; i < MAX_DIMENSION; i++ )
			rec.relationElements[i] = ar->data.relationElements[i];
		for ( int i = 0; i < MAX_DIMENSION; i++ )
			rec.relationElementsEnabled[i] = ar->data.relationElementsEnabled[i];
		for ( int i = 0; i < MAX_ALARM_GENERATOR; i++ )
			rec.alarmHandlers[i] = ar->data.alarmHandlers[i];
		rec.timeFractionElementId = ar->data.timeFractionElementId;
		rec.familyId = ar->data.familyId;
		rec.countersKitId = ar->data.countersKitId;
		rec.idConditionScript = ar->data.idConditionScript;
		rec.idConditionPlugin = ar->data.idConditionPlugin;

		( *seqptr ) [seqidx++] = rec;
	}
}

void AlarmRelationInterface::fillFromCorba ( const AlarmRelationSeq * seqptr )
{
	alarmrelation * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new alarmrelation;

		rec->data.idAlarmRelation = ( *seqptr ) [cnt].idAlarmRelation;
		c_qstrncpy ( rec->data.description, ( *seqptr ) [cnt].description, LONG_DESC_LEN );
		for ( int i = 0; i < MAX_DIMENSION; i++ )
			rec->data.relationElements[i] = ( *seqptr ) [cnt].relationElements[i];
		for ( int i = 0; i < MAX_DIMENSION; i++ )
			rec->data.relationElementsEnabled[i] = ( *seqptr ) [cnt].relationElementsEnabled[i];
		for ( int i = 0; i < MAX_ALARM_GENERATOR; i++ )
			rec->data.alarmHandlers[i] = ( *seqptr ) [cnt].alarmHandlers[i];
		rec->data.timeFractionElementId = ( *seqptr ) [cnt].timeFractionElementId;
		rec->data.familyId = ( *seqptr ) [cnt].familyId;
		rec->data.countersKitId = ( *seqptr ) [cnt].countersKitId;
		rec->data.idConditionScript = ( *seqptr ) [cnt].idConditionScript;
		rec->data.idConditionPlugin = ( *seqptr ) [cnt].idConditionPlugin;

		add ( rec );
	}

	AlarmRelationsNumber = count();
}



