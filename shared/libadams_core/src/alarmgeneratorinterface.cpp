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

#include <alarmgeneratorinterface.h>
#include <applogger.h>

AlarmGeneratorInterface::AlarmGeneratorInterface() : uniq ( 0 )
{
	AlarmGenerator = new QMultiHash<QString, alarmgenerator *>;
	AlarmGeneratorNumber = 0;
}

AlarmGeneratorInterface::~AlarmGeneratorInterface()
{
	if ( AlarmGenerator ) {
		clear();
		delete AlarmGenerator;
	}
}

/**
  * Inserisce un nuovo generator nel hashtable
  * ed incrementa il numero di elementi.
**/
bool AlarmGeneratorInterface::add ( alarmgenerator * newHandler )
{
	AlarmGenerator->insert ( QString::number ( newHandler->data.idAlarmGenerator ), newHandler );
	AlarmGeneratorNumber++;
	return false;
}
/**
  * Rimouve un generatorre dal hashtable
  * e decrementa il numero di elementi.
**/
bool AlarmGeneratorInterface::remove ( int handlerNum )
{
	alarmgenerator *lr = AlarmGenerator->find ( QString::number ( handlerNum ) ).value();
	if ( lr == ( alarmgenerator * ) 0 ) {
		return true;
	}
	AlarmGenerator->remove ( QString::number ( handlerNum ) );
	delete lr;
	AlarmGeneratorNumber--;
	return false;
}
/**
  * Restituisce uno specific generatore.
**/
alarmgenerator * AlarmGeneratorInterface::get ( int handlerNum )
{
	return AlarmGenerator->find ( QString::number ( handlerNum ) ).value();
}

// get by tag

alarmgenerator * AlarmGeneratorInterface::getByTag ( const QString & srcTag )
{
	if ( AlarmGenerator->count() > 0 ) {
		foreach ( alarmgenerator * ag, *AlarmGenerator ) {
			if ( ag->data.shortDescription == srcTag )
				return ag;
		}
	}
	return ( alarmgenerator * ) 0;
}




// crea lista per tag

TagList AlarmGeneratorInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( AlarmGenerator->count() > 0 ) {
		foreach ( alarmgenerator * ag, *AlarmGenerator ) {
			tagList.values.append ( QString::number ( ag->data.idAlarmGenerator ) );
			tagList.labels.append ( QString ( ag->data.shortDescription ) );
		}
	}

	return tagList;
}


/**
  * Ritorna il Tag relativo ali generatori.
**/
const char * AlarmGeneratorInterface::getHeaderTag()
{
	static const char *ht = ALARMGENERATOR_HEADERTAG;

	return ht;
}

// setup i/e tags

void AlarmGeneratorInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void AlarmGeneratorInterface::ieExport ( ImportExport & ie )
{
	if ( AlarmGenerator->count() > 0 ) {
		foreach ( alarmgenerator * ag, *AlarmGenerator ) {
			ie.initWriteRecord ( ALARMGENERATOR_HEADERTAG );

			ie.setWriteTag ( "idAlarmGenerator" );
			ie.addWriteRecord ( ag->data.idAlarmGenerator );
			ie.setWriteTag ( "shortDescription" );
			ie.addWriteRecord ( ag->data.shortDescription );
			ie.setWriteTag ( "longDescription" );
			ie.addWriteRecord ( ag->data.longDescription );
			ie.setWriteTag ( "idPlugin" );
			ie.addWriteRecord ( ag->data.idPlugin );
			ie.setWriteTag ( "thresholdManagement" );
			ie.addWriteRecord ( ag->data.thresholdManagement );
			ie.setWriteTag ( "idThresholdGenerator" );
			ie.addArrayWriteRecord ( ag->data.idThresholdGenerator, MAX_THRESHOLD_GENERATOR );
			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void AlarmGeneratorInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != ALARMGENERATOR_HEADERTAG )		// not a record of mine
		return;

	alarmgenerator *fld = new alarmgenerator;

	fld->data.idAlarmGenerator = ieptr->getIntToken ( "idAlarmGenerator" );
	ieptr->getStrToken ( fld->data.shortDescription, "shortDescription", SHORT_DESC_LEN );
	ieptr->getStrToken ( fld->data.longDescription, "longDescription", LONG_DESC_LEN );
	fld->data.idPlugin = ieptr->getIntToken ( "idPlugin" );
	fld->data.thresholdManagement = ieptr->getBoolToken ( "thresholdManagement" );
	ieptr->getIntArrToken ( fld->data.idThresholdGenerator, "idThresholdGenerator", MAX_THRESHOLD_GENERATOR );

	add ( fld );
	return;
}

void AlarmGeneratorInterface::copyToCorba ( AlarmGeneratorSeq * seqptr )
{
	ALARM_GENERATOR rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( alarmgenerator * ag, *AlarmGenerator ) {
		rec.idAlarmGenerator = ag->data.idAlarmGenerator;
		c_qstrncpy ( rec.shortDescription, ag->data.shortDescription, SHORT_DESC_LEN );
		c_qstrncpy ( rec.longDescription, ag->data.longDescription, LONG_DESC_LEN );
		rec.idPlugin = ag->data.idPlugin;
		rec.thresholdManagement = ag->data.thresholdManagement;
		for ( int i = 0; i < MAX_THRESHOLD_GENERATOR; i++ )
			rec.idThresholdGenerator[i] = ag->data.idThresholdGenerator[i];

		( *seqptr ) [seqidx++] = rec;
	}
}

void AlarmGeneratorInterface::fillFromCorba ( const AlarmGeneratorSeq * seqptr )
{
	alarmgenerator * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new alarmgenerator;

		rec->data.idAlarmGenerator = ( *seqptr ) [cnt].idAlarmGenerator;
		c_qstrncpy ( rec->data.shortDescription, ( *seqptr ) [cnt].shortDescription, SHORT_DESC_LEN );
		c_qstrncpy ( rec->data.longDescription, ( *seqptr ) [cnt].longDescription, LONG_DESC_LEN );
		rec->data.idPlugin = ( *seqptr ) [cnt].idPlugin;
		rec->data.thresholdManagement = ( *seqptr ) [cnt].thresholdManagement;
		for ( int i = 0; i < MAX_THRESHOLD_GENERATOR; i++ )
			rec->data.idThresholdGenerator[i] = ( *seqptr ) [cnt].idThresholdGenerator[i];

		add ( rec );
	}

	AlarmGeneratorNumber = count();
}



