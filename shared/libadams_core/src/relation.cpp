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
                          relation.cpp  -  description
                             -------------------
    begin                : Tue Oct 31 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "relation.h"
#include <string.h>


relation::relation():Delete(false)
{
  	memset((void *)&data, 0, sizeof(DATA));
}
relation::~relation(){
}
