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

#include <thresholdgeneratorinterface.h>
#include <applogger.h>

ThresholdGeneratorInterface::ThresholdGeneratorInterface() : uniq ( 0 )
{
	ThresholdGenerator = new QMultiHash<QString, thresholdgenerator *>;
	ThresholdGeneratorNumber = 0;
}

ThresholdGeneratorInterface::~ThresholdGeneratorInterface()
{
	if ( ThresholdGenerator ) {
		clear();
		delete ThresholdGenerator;
	}
}

/**
  * Inserisce un nuovo generator nel hashtable
  * ed incrementa il numero di elementi.
**/
bool ThresholdGeneratorInterface::add ( thresholdgenerator * newHandler )
{
	ThresholdGenerator->insert ( QString::number ( newHandler->data.idThresholdGenerator ), newHandler );
	ThresholdGeneratorNumber++;
	return false;
}
/**
  * Rimouve un generatorre dal hashtable
  * e decrementa il numero di elementi.
**/
bool ThresholdGeneratorInterface::remove ( int handlerNum )
{
	thresholdgenerator *lr = ThresholdGenerator->find ( QString::number ( handlerNum ) ).value();
	if ( lr == ( thresholdgenerator * ) 0 ) {
		return true;
	}
	ThresholdGenerator->remove ( QString::number ( handlerNum ) );
	delete lr;
	ThresholdGeneratorNumber--;
	return false;
}
/**
  * Restituisce uno specific generatore.
**/
thresholdgenerator * ThresholdGeneratorInterface::get ( int handlerNum )
{
	return ThresholdGenerator->find ( QString::number ( handlerNum ) ).value();
}

// get by tag

thresholdgenerator * ThresholdGeneratorInterface::getByTag ( const QString & srcTag )
{
	if ( ThresholdGenerator->count() > 0 ) {
		foreach ( thresholdgenerator * tg, *ThresholdGenerator ) {
			if ( tg->data.description == srcTag )
				return tg;
		}
	}
	return ( thresholdgenerator * ) 0;
}




// crea lista per tag

TagList ThresholdGeneratorInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( ThresholdGenerator->count() > 0 ) {
		foreach ( thresholdgenerator * tg, *ThresholdGenerator ) {
			tagList.values.append ( QString::number ( tg->data.idThresholdGenerator ) );
			tagList.labels.append ( QString ( tg->data.description ) );
		}
	}

	return tagList;
}

/**
  * Ritorna il Tag relativo ali generatori.
**/
const char * ThresholdGeneratorInterface::getHeaderTag()
{
	static const char *ht = THRESHOLDGENERATOR_HEADERTAG;

	return ht;
}

// setup i/e tags

void ThresholdGeneratorInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void ThresholdGeneratorInterface::ieExport ( ImportExport & ie )
{
	if ( ThresholdGenerator->count() > 0 ) {
		foreach ( thresholdgenerator * tg, *ThresholdGenerator ) {
			ie.initWriteRecord ( THRESHOLDGENERATOR_HEADERTAG );

			ie.setWriteTag ( "idThresholdGenerator" );
			ie.addWriteRecord ( tg->data.idThresholdGenerator );
			ie.setWriteTag ( "description" );
			ie.addWriteRecord ( tg->data.description );
			ie.setWriteTag ( "typeThreshold" );
			ie.addWriteRecord ( tg->data.typeThreshold );
			ie.setWriteTag ( "enableHolydayThreshold" );
			ie.addWriteRecord ( tg->data.enableHolydayThreshold );
			ie.setWriteTag ( "idPlugin" );
			ie.addWriteRecord ( tg->data.idPlugin );
			ie.setWriteTag ( "thresholdPersistence" );
			ie.addWriteRecord ( tg->data.thresholdPersistence );
			ie.setWriteTag ( "hoursAggregate" );
			ie.addWriteRecord ( tg->data.hoursAggregate );

			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void ThresholdGeneratorInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != THRESHOLDGENERATOR_HEADERTAG )		// not a record of mine
		return;

	thresholdgenerator *fld = new thresholdgenerator;

	fld->data.idThresholdGenerator = ieptr->getIntToken ( "idThresholdGenerator" );
	ieptr->getStrToken ( fld->data.description, "description", LONG_DESC_LEN );
	fld->data.typeThreshold = ieptr->getIntToken ( "typeThreshold" );
	fld->data.enableHolydayThreshold = ieptr->getBoolToken ( "enableHolydayThreshold" );
	fld->data.idPlugin = ieptr->getIntToken ( "idPlugin" );
	fld->data.thresholdPersistence = ieptr->getIntToken ( "thresholdPersistence" );
	fld->data.hoursAggregate = ieptr->getIntToken ( "hoursAggregate" );

	add ( fld );
	return;
}

void ThresholdGeneratorInterface::copyToCorba ( ThresholdGeneratorSeq * seqptr )
{
	THRESHOLD_GENERATOR rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( thresholdgenerator * tg, *ThresholdGenerator ) {
		rec.idThresholdGenerator = tg->data.idThresholdGenerator;
		c_qstrncpy ( rec.description, tg->data.description, LONG_DESC_LEN );
		rec.typeThreshold = tg->data.typeThreshold;
		rec.enableHolydayThreshold = tg->data.enableHolydayThreshold;
		rec.idPlugin = tg->data.idPlugin;
		rec.thresholdPersistence = tg->data.thresholdPersistence;
		rec.hoursAggregate = tg->data.hoursAggregate;

		( *seqptr ) [seqidx++] = rec;
	}
}

void ThresholdGeneratorInterface::fillFromCorba ( const ThresholdGeneratorSeq * seqptr )
{
	thresholdgenerator * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new thresholdgenerator;

		rec->data.idThresholdGenerator = ( *seqptr ) [cnt].idThresholdGenerator;
		c_qstrncpy ( rec->data.description, ( *seqptr ) [cnt].description, LONG_DESC_LEN );
		rec->data.typeThreshold = ( *seqptr ) [cnt].typeThreshold;
		rec->data.enableHolydayThreshold = ( *seqptr ) [cnt].enableHolydayThreshold;
		rec->data.idPlugin = ( *seqptr ) [cnt].idPlugin;
		rec->data.thresholdPersistence = ( *seqptr ) [cnt].thresholdPersistence;
		rec->data.hoursAggregate = ( *seqptr ) [cnt].hoursAggregate;

		add ( rec );
	}

	ThresholdGeneratorNumber = count();
}


