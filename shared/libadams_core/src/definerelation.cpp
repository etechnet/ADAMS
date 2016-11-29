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
                          definerelation.cpp  -  description
                             -------------------
    begin                : Thu Feb 8 2001
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "definerelation.h"

DefineRelation::DefineRelation() : Dimension ( 0 ), invert ( false )
{
}

DefineRelation::~DefineRelation()
{
}


void DefineRelation::setElementInRelation ( const AdamsCompleteConfig* ncc, int Rel, const int* ffVector )
{
	relation * RetRelation;
	unsigned long * teVector = decodeRelToVector ( Rel, ncc->relationInterface, ffVector );

	Dimension = vecCount ( teVector );
	RetRelation = ncc->relationInterface->get ( Rel );
	for ( int i = 0; i < MAX_DIMENSION; i++ ) {
		if ( teVector[i] == 0 )
			break;
		DataElement * element = ncc->dataElementInterface->get ( teVector[i] );

		aiElemenInRelation[i].CDRLink = element->data.idDRLink;
		aiElemenInRelation[i].id = teVector[i];
		aiElemenInRelation[i].desc = element->data.shortDescription;
		aiElemenInRelation[i].length = element->data.LengthInRelation;
		aiElemenInRelation[i].hasShifter = ncc->dataElementInterface->hasShifter ( teVector[i] );
		aiElemenInRelation[i].shiftRanges = ncc->dataElementInterface->getShifters ( teVector[i] );
		aiElemenInRelation[i].scriptHandler = 0;
		aiElemenInRelation[i].pluginHandler = 0;
	}

	delete [] teVector;
	return;
}



unsigned long * DefineRelation::decodeRelToVector ( int relid, RelationInterface * RelInterface, const int * ffVector )
{
	bool hasParent = false;
	int p = 0;
	relation *rel = RelInterface->get ( relid );
	unsigned long * parentVect = new unsigned long [MAX_DIMENSION];

	for ( int i = 0; i < MAX_DIMENSION; i++ )
		parentVect[i] = 0;

	if ( relid == RELATION_FREEFORMAT_ID ) {
		if ( !ffVector )
			return parentVect;

		for ( int i = 0; i < MAX_DIMENSION; i++ )
			parentVect[i] = ffVector[i];

		return parentVect;
	}

	if ( rel == 0 )
		return parentVect;

	if ( rel->data.idParentRelation ) {
		delete [] parentVect;
		parentVect = decodeRelToVector ( rel->data.idParentRelation, RelInterface );
		if ( ( p = vecCount ( parentVect ) ) > 0 )
			hasParent = true;
	}
	if ( rel->data.idFirstElement ) {
		parentVect[p] = rel->data.idFirstElement;
		++p;
	}
	else { // Error: first element is always defined
		for ( int i = 0; i < MAX_DIMENSION; i++ )
			parentVect[i] = 0;
		return parentVect;
	}
	if ( hasParent )
		return parentVect;

	parentVect[p] = rel->data.idSecondElement;

	return parentVect;
}
