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
                          alarmdefinerelation.cpp  -  description
                             -------------------
    begin                : 2011-02-09
    copyright            : (C) 2011 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "definealarmrelation.h"
#include "alarmrelation.h"

DefineAlarmRelation::DefineAlarmRelation() : Dimension ( 0 )
{
}

DefineAlarmRelation::~DefineAlarmRelation()
{
}


void DefineAlarmRelation::setElementInRelation ( const AdamsCompleteConfig* ncc, int Rel )
{
	unsigned long * teVector = decodeRelToVector ( Rel, ncc->alarmRelationInterface );

	Dimension = vecCount ( teVector );
	alarmrelation * RetRelation = ncc->alarmRelationInterface->get ( Rel );
	for ( int i = 0; i < MAX_DIMENSION; i++ ) {
		if ( teVector[i] == 0 )
			break;
		DataElement * element = ncc->dataElementInterface->get ( teVector[i] );

		aiElementInRelation[i].CDRLink = element->data.idDRLink;
		aiElementInRelation[i].id = teVector[i];
		aiElementInRelation[i].desc = element->data.shortDescription;
		aiElementInRelation[i].length = element->data.LengthInRelation;
		aiElementInRelation[i].hasShifter = ncc->dataElementInterface->hasShifter ( teVector[i] );
		aiElementInRelation[i].shiftRanges = ncc->dataElementInterface->getShifters ( teVector[i] );
		aiElementInRelation[i].scriptHandler = 0;
		aiElementInRelation[i].pluginHandler = 0;
	}

	delete [] teVector;
	return;
}



unsigned long * DefineAlarmRelation::decodeRelToVector ( int relid, AlarmRelationInterface * RelInterface )
{
	int p = 0;
	alarmrelation * rel = RelInterface->get ( relid );
	unsigned long * parentVect = new unsigned long [MAX_DIMENSION];

	for ( int i = 0; i < MAX_DIMENSION; i++ )
		parentVect[i] = 0;

	for ( int i = 0; i < MAX_DIMENSION; i++ )
		parentVect[i] = rel->data.relationElements[i];

	return parentVect;
}

QString DefineAlarmRelation::formatRelationToString( const AdamsCompleteConfig * ncc, const QString & rel_string, const QString & sep, bool fixed_len )
{
	int offset = 0;
	QString fmt_str, s;
	int l;

	for (int iElem = 0; iElem < Dimension; iElem++) {

		s = rel_string.mid(offset, aiElementInRelation[iElem].length);
		if (iElem) fmt_str += sep;

		if (aiElementInRelation[iElem].scriptHandler) {
			// unamanaged for now, just return field as it is
			if (pure_digits(s)) {
				l = number_clean( s );
				if (fixed_len) l = aiElementInRelation[iElem].length;
				fmt_str += s.rightJustified(l, '0');
			}
			else {
				l = ascii_clean( s );
				if (fixed_len) l = aiElementInRelation[iElem].length;
				fmt_str += s.leftJustified(l, ' ');
			}
			offset += aiElementInRelation[iElem].length;
			continue;
		}
		if (aiElementInRelation[iElem].pluginHandler) {
			// unamanaged for now, just return field as it is
			if (pure_digits(s)) {
				l = number_clean( s );
				if (fixed_len) l = aiElementInRelation[iElem].length;
				fmt_str += s.rightJustified(l, '0');
			}
			else {
				l = ascii_clean( s );
				if (fixed_len) l = aiElementInRelation[iElem].length;
				fmt_str += s.leftJustified(l, ' ');
			}
			offset += aiElementInRelation[iElem].length;
			continue;
		}
		if (aiElementInRelation[iElem].CDRLink > 0) {
			switch (ncc->drInterface->get(aiElementInRelation[iElem].CDRLink+1)->data.fieldtype) {
				case DRF_unsigned_int:
				case DRF_int:
				case DRF_unsigned_short:
				case DRF_short:
				case DRF_unsigned_long:
				case DRF_long:
					l = number_clean( s );
					if (fixed_len) l = aiElementInRelation[iElem].length;
					fmt_str += s.rightJustified(l, '0');
					break;
				case DRF_unsigned_char:
				case DRF_char:
				case DRF_BCD:
				case DRF_String:
				case DRF_CString:
					if (ncc->drInterface->get(aiElementInRelation[iElem].CDRLink+1)->data.is_array) {
						l = ascii_clean( s );
						if (fixed_len) l = aiElementInRelation[iElem].length;
						fmt_str += s.leftJustified(l, ' ');
					}
					else {
						l = number_clean( s );
						if (fixed_len) l = aiElementInRelation[iElem].length;
						fmt_str += s.rightJustified(l, '0');
					}
					break;
				case DRF_float:
				case DRF_double:
					l = double_clean( s );
					if (fixed_len) l = aiElementInRelation[iElem].length;
					fmt_str += s.rightJustified(l, '0');
					break;
			}
		}

		offset += aiElementInRelation[iElem].length;
// 		lout1 << "fmt_str" << ":" << fmt_str << endl;

	}

	return fmt_str;
}

