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

#include <exceptioninterface.h>
#include <applogger.h>

ExceptionInterface::ExceptionInterface() : QObject(), uniq ( 0 )
{
	Exceptions = new QMultiHash<QString, Exception *>;
	ExceptionsNumber = 0;
}
ExceptionInterface::~ExceptionInterface()
{
	if ( Exceptions ) {
		clear();
		delete Exceptions;
	}
}
/**
  * Inserisce una nuova eccezione nel hashtable
  * ed incrementa il numero di eccezioni.
**/
bool ExceptionInterface::add ( Exception * newException )
{
	Exceptions->insert ( QString::number ( newException->data.idException ), newException );
	ExceptionsNumber++;
	return false;
}
/**
  * Rimouve una eccezione dal hashtable
  * ed decrementa il numero di eccezioni.
**/
bool ExceptionInterface::remove ( int ExceptionNum )
{
	Exception *exc = Exceptions->find ( QString::number ( ExceptionNum ) ).value() ;
	if ( exc == ( Exception * ) 0 ) {
		return true;
	}
	Exceptions->remove ( QString::number ( ExceptionNum ) );
	delete exc;
	ExceptionsNumber--;
	return false;
}
/**
  * Restituisce un specifico elemento.
**/
Exception * ExceptionInterface::get ( int ExceptionNum )
{
	return Exceptions->find ( QString::number ( ExceptionNum ) ).value();
}


/**
  * Ritorna il Tag relativo alle eccezioni.
**/
const char * ExceptionInterface::getHeaderTag()
{
	static const char *ht = EXCEPTION_HEADERTAG;

	return ht;
}

// get by tag

Exception * ExceptionInterface::getByTag ( const QString & srcTag )
{
	if ( Exceptions->count() > 0 ) {
		foreach ( Exception * e, *Exceptions ) {
			if ( e->data.tag == srcTag )
				return e;
		}
	}
	return ( Exception * ) 0;
}

// get tag list

TagList ExceptionInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( Exceptions->count() > 0 ) {
		foreach ( Exception * e, *Exceptions ) {
			tagList.values.append ( QString::number ( e->data.idException ) );
			tagList.labels.append ( QString ( e->data.tag ) );
		}
	}

	return tagList;
}




// setup i/e tags

void ExceptionInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void ExceptionInterface::ieExport ( ImportExport & ie )
{
	if ( Exceptions->count() > 0 ) {
		foreach ( Exception * e, *Exceptions ) {
			ie.initWriteRecord ( EXCEPTION_HEADERTAG );

			ie.setWriteTag ( "idException" );
			ie.addWriteRecord ( e->data.idException );
			ie.setWriteTag ( "tag" );
			ie.addWriteRecord ( e->data.tag );
			ie.setWriteTag ( "description" );
			ie.addWriteRecord ( e->data.description );
			ie.setWriteTag ( "idTriggerRestriction" );
			ie.addWriteRecord ( e->data.idTriggerRestriction );
			ie.setWriteTag ( "triggeredStatus" );
			ie.addWriteRecord ( e->data.triggeredStatus );
			ie.setWriteTag ( "triggeredValue" );
			ie.addWriteRecord ( e->data.triggeredValue );
			ie.setWriteTag ( "action" );
			ie.addWriteRecord ( e->data.action );
			ie.setWriteTag ( "targetValue" );
			ie.addWriteRecord ( e->data.targetValue );
			ie.setWriteTag ( "idAggregateException" );
			ie.addWriteRecord ( e->data.idAggregateException );

			ie.flushWriteRecord();
		}
	}
}


// import a record from ie stream

void ExceptionInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != EXCEPTION_HEADERTAG )		// not a record of mine
		return;

	Exception *fld = new Exception;

	fld->data.idException = ieptr->getIntToken ( "idException" );
	ieptr->getStrToken ( fld->data.tag, "tag", SHORT_DESC_LEN );
	ieptr->getStrToken ( fld->data.description, "description", LONG_DESC_LEN );
	fld->data.idTriggerRestriction = ieptr->getIntToken ( "idTriggerRestriction" );
	fld->data.triggeredStatus = ieptr->getIntToken ( "triggeredStatus" );
	ieptr->getStrToken ( fld->data.triggeredValue, "triggeredValue", ASCII_REST_LEN );
	fld->data.action = ieptr->getIntToken ( "action" );
	ieptr->getStrToken ( fld->data.targetValue, "targetValue", ASCII_REST_LEN );
	fld->data.idAggregateException = ieptr->getIntToken ( "idAggregateException" );

	add ( fld );
	return;
}

void ExceptionInterface::copyToCorba ( ExceptionSeq * seqptr )
{
	DATA_EXCEPTIONS rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( Exception * e, *Exceptions ) {
		rec.idException = e->data.idException;
		c_qstrncpy ( rec.tag, e->data.tag, SHORT_DESC_LEN );
		c_qstrncpy ( rec.description, e->data.description, LONG_DESC_LEN );
		rec.idTriggerRestriction = e->data.idTriggerRestriction;
		rec.triggeredStatus = e->data.triggeredStatus;
		c_qstrncpy ( rec.triggeredValue, e->data.triggeredValue, ASCII_REST_LEN );
		rec.action = e->data.action;
		c_qstrncpy ( rec.targetValue, e->data.targetValue, ASCII_REST_LEN );
		rec.idAggregateException = e->data.idAggregateException;

		( *seqptr ) [seqidx++] = rec;
	}
}

void ExceptionInterface::fillFromCorba ( const ExceptionSeq * seqptr )
{
	Exception * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new Exception;

		rec->data.idException = ( *seqptr ) [cnt].idException;
		c_qstrncpy ( rec->data.tag, ( *seqptr ) [cnt].tag, SHORT_DESC_LEN );
		c_qstrncpy ( rec->data.description, ( *seqptr ) [cnt].description, LONG_DESC_LEN );
		rec->data.idTriggerRestriction = ( *seqptr ) [cnt].idTriggerRestriction;
		rec->data.triggeredStatus = ( *seqptr ) [cnt].triggeredStatus;
		c_qstrncpy ( rec->data.triggeredValue, ( *seqptr ) [cnt].triggeredValue, ASCII_REST_LEN );
		rec->data.action = ( *seqptr ) [cnt].action;
		c_qstrncpy ( rec->data.targetValue, ( *seqptr ) [cnt].targetValue, ASCII_REST_LEN );
		rec->data.idAggregateException = ( *seqptr ) [cnt].idAggregateException;

		add ( rec );
	}

	ExceptionsNumber = count();
}

