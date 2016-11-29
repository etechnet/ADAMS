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

#include <relationinterface.h>
#include <applogger.h>

RelationInterface::RelationInterface() : uniq ( 0 ), teReference ( 0 )
{
	Relations = new QMultiHash<QString, relation *>;
	RelationsNumber = 0;
}

RelationInterface::~RelationInterface()
{
	if ( Relations ) {
		clear();
		delete Relations;
	}
}

/**
  * Inserisce una nuova relazione nel hashtable
  * ed incrementa il numero di relazioni.
**/
bool RelationInterface::add ( relation * newRelation )
{
	Relations->insert ( QString::number ( newRelation->data.idRelation ), newRelation );
	RelationsNumber++;
	return false;
}
/**
  * Rimouve una nuova relazione dal hashtable
  * ed decrementa il numero di relazioni.
**/
bool RelationInterface::remove ( int RelationNum )
{
	relation *lr = Relations->find ( QString::number ( RelationNum ) ).value();
	if ( lr == ( relation * ) 0 ) {
		return true;
	}
	Relations->remove ( QString::number ( RelationNum ) );
	delete lr;
	RelationsNumber--;
	return false;
}
/**
  * Restituisce una specifica relazione.
**/
relation * RelationInterface::get ( int RelationNum )
{
	return Relations->find ( QString::number ( RelationNum ) ).value();
}

// get by tag

relation * RelationInterface::getByTag ( const QString & srcTag )
{
	if ( Relations->count() > 0 ) {
		foreach ( relation * r, *Relations ) {
			if ( decodeRel ( r->data.idRelation ) == srcTag )
				return r;
		}
	}
	return ( relation * ) 0;
}




// crea lista per tag

TagList RelationInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( Relations->count() > 0 ) {
		foreach ( relation * r, *Relations ) {
			tagList.values.append ( QString::number ( r->data.idRelation ) );
			tagList.labels.append ( decodeRel ( r->data.idRelation ) );
		}
	}

	return tagList;
}


QString RelationInterface::decodeRel ( int relid, const int * ffVector )
{
	QString parentDesc = "";
	bool hasParent = false;
	relation *rel = get ( relid );

	// handle free-format case
	if ( relid == RELATION_FREEFORMAT_ID ) {
		if ( !ffVector )
			return QString ( "" );

		int i = 0;
		while ( ffVector[i] && i < MAX_DIMENSION ) {
			DataElement * ptr1 = teReference->get ( ffVector[i] );

			if ( ptr1 )
				parentDesc += QString ( ptr1->data.shortDescription );
			else
				parentDesc += "UNKNOWN";

			if ( ffVector[i + 1] )
				parentDesc += "::";

			++i;
		}

		return parentDesc;
	} else {
		if ( rel == 0 || teReference == 0 )
			return QString ( "" );

		if ( rel->data.idParentRelation ) {
			parentDesc = decodeRel ( rel->data.idParentRelation );
			if ( parentDesc.length() > 0 )
				hasParent = true;
		}

		DataElement * ptr1 = teReference->get ( rel->data.idFirstElement );
		DataElement * ptr2 = teReference->get ( rel->data.idSecondElement );

		if ( hasParent )
			parentDesc += QString ( "::" );
		if ( ptr1 )
			parentDesc += QString ( ptr1->data.shortDescription );
		if ( ptr2 ) {
			if ( ptr1 )
				parentDesc += QString ( "::" );
			parentDesc += QString ( ptr2->data.shortDescription );
		}

		return parentDesc;
	}
}

/**
  * Ritorna il Tag relativo alle relazioni.
**/
const char * RelationInterface::getHeaderTag()
{
	static const char *ht = RELATION_HEADERTAG;

	return ht;
}

// setup i/e tags

void RelationInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void RelationInterface::ieExport ( ImportExport & ie )
{
	if ( Relations->count() > 0 ) {
		foreach ( relation * r, *Relations ) {
			ie.initWriteRecord ( RELATION_HEADERTAG );

			ie.setWriteTag ( "idRelation" );
			ie.addWriteRecord ( r->data.idRelation );
			ie.setWriteTag ( "idParentRelation" );
			ie.addWriteRecord ( r->data.idParentRelation );
			ie.setWriteTag ( "idFirstElement" );
			ie.addWriteRecord ( r->data.idFirstElement );
			ie.setWriteTag ( "idSecondElement" );
			ie.addWriteRecord ( r->data.idSecondElement );
			ie.setWriteTag ( "admittedDirections" );
			ie.addWriteRecord ( r->data.admittedDirections );
			ie.setWriteTag ( "admitHexValues" );
			ie.addWriteRecord ( r->data.admitHexValues );
			ie.setWriteTag ( "admitNetworkAnalisys" );
			ie.addWriteRecord ( r->data.admitNetworkAnalisys );
			ie.setWriteTag ( "ghostRelation" );
			ie.addWriteRecord ( r->data.ghostRelation );
			ie.setWriteTag ( "nextLevelRelations" );
			ie.addArrayWriteRecord ( r->data.nextLevelRelations, NEXTLEVEL_RELATIONS );
			ie.setWriteTag ( "restrictions" );
			ie.addArrayWriteRecord ( r->data.restrictions, MAX_RESTRICTIONS );
			ie.setWriteTag ( "tiedRestrictions" );
			ie.addArrayWriteRecord ( r->data.tiedRestrictions, MAX_RESTRICTIONS );

			ie.flushWriteRecord();
		}
	}
}

// import a record from ie stream

void RelationInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != RELATION_HEADERTAG )		// not a record of mine
		return;

	relation *fld = new relation;

	fld->data.idRelation = ieptr->getIntToken ( "idRelation" );
	fld->data.idParentRelation = ieptr->getIntToken ( "idParentRelation" );
	fld->data.idFirstElement = ieptr->getIntToken ( "idFirstElement" );
	fld->data.idSecondElement = ieptr->getIntToken ( "idSecondElement" );
	fld->data.admittedDirections = ieptr->getIntToken ( "admittedDirections" );
	fld->data.admitHexValues = ieptr->getBoolToken ( "admitHexValues" );
	fld->data.admitNetworkAnalisys = ieptr->getBoolToken ( "admitNetworkAnalisys" );
	fld->data.ghostRelation = ieptr->getBoolToken ( "ghostRelation" );
	ieptr->getIntArrToken ( fld->data.nextLevelRelations, "nextLevelRelations", NEXTLEVEL_RELATIONS );
	ieptr->getIntArrToken ( fld->data.restrictions, "restrictions", MAX_RESTRICTIONS );
	ieptr->getIntArrToken ( fld->data.tiedRestrictions, "tiedRestrictions", MAX_RESTRICTIONS );

	add ( fld );
	return;
}

void RelationInterface::copyToCorba ( RelationSeq * seqptr )
{
	DATA_RELATIONS rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( relation * r, *Relations ) {
		rec.idRelation = r->data.idRelation;
		rec.idParentRelation = r->data.idParentRelation;
		rec.idFirstElement = r->data.idFirstElement;
		rec.idSecondElement = r->data.idSecondElement;
		rec.admittedDirections = r->data.admittedDirections;
		rec.admitHexValues = r->data.admitHexValues;
		rec.admitNetworkAnalisys = r->data.admitNetworkAnalisys;
		rec.ghostRelation = r->data.ghostRelation;
		for ( int i = 0; i < NEXTLEVEL_RELATIONS; i++ )
			rec.nextLevelRelations[i] = r->data.nextLevelRelations[i];
		for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
			rec.restrictions[i] = r->data.restrictions[i];
		for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
			rec.tiedRestrictions[i] = r->data.tiedRestrictions[i];

		( *seqptr ) [seqidx++] = rec;
	}
}

void RelationInterface::fillFromCorba ( const RelationSeq * seqptr )
{
	relation * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new relation;

		rec->data.idRelation = ( *seqptr ) [cnt].idRelation;
		rec->data.idParentRelation = ( *seqptr ) [cnt].idParentRelation;
		rec->data.idFirstElement = ( *seqptr ) [cnt].idFirstElement;
		rec->data.idSecondElement = ( *seqptr ) [cnt].idSecondElement;
		rec->data.admittedDirections = ( *seqptr ) [cnt].admittedDirections;
		rec->data.admitHexValues = ( *seqptr ) [cnt].admitHexValues;
		rec->data.admitNetworkAnalisys = ( *seqptr ) [cnt].admitNetworkAnalisys;
		rec->data.ghostRelation = ( *seqptr ) [cnt].ghostRelation;
		for ( int i = 0; i < NEXTLEVEL_RELATIONS; i++ )
			rec->data.nextLevelRelations[i] = ( *seqptr ) [cnt].nextLevelRelations[i];
		for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
			rec->data.restrictions[i] = ( *seqptr ) [cnt].restrictions[i];
		for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
			rec->data.tiedRestrictions[i] = ( *seqptr ) [cnt].tiedRestrictions[i];

		add ( rec );
	}

	RelationsNumber = count();
}

