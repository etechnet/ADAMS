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
                          drimagehandler.cpp  -  description
                             -------------------
    begin                : Mon Oct 2 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "drimagehandler.h"


//const union UnionFieldValue & DRImageHandler::field(int idx)
//{
//	return getField(idx, RecordImage);
//}

bool DRImageHandler::validate(DRInterface * dri)
{
	if (dri->count() > 0) {
		for ( DRFieldIterator it = dri->getIterator(); it != dri->hashEnd(); it++ ) {
			switch (it.value()->data.fieldtype) {
				case DRF_unsigned_char:
				case DRF_char:
					if (it.value()->data.is_array) {
						if (it.value()->data.array_size > DRIMAGEHANDLER_ARRAY_BUFFER_SIZE) {
							lout << "DRImageHandler::validate : cannot manipulate array fields larger than " << DRIMAGEHANDLER_ARRAY_BUFFER_SIZE << " bytes. Aborting..." << endl;
							return false;
						}
					}
					break;
				default:
					if (it.value()->data.fieldtype >= DRF_NumTypes) {
						lout << "DRImageHandler::validate : got unknown field type " << it.value()->data.fieldtype << endl;
						return false;
					}
			}
		}
	}
	else {
		lout << "DRImageHandler::validate : DRInterface is empty !!" << endl;
		return false;
	}
	return true;
}

