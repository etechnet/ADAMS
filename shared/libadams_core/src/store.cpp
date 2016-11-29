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
                          store.cpp  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/
//------------------------------------
//  store.cpp
//  (c) Bartosz Milewski, 1994
//------------------------------------

#include "store.h"
#include "symtab.h"
#include <math.h>

Store::Store (int size, SymbolTable & symTab): _size (size)
{
    _cell = new double [size];
    _status = new unsigned char [size];
    for (int i = 0; i < size; ++i)
        _status [i] = stNotInit;

    // add predefined constants
    // Note: if more needed, do a more general job
    int id = symTab.ForceAdd ("e");
    SetValue (id, exp (1.0));
    id = symTab.ForceAdd ("pi");
    SetValue (id, 2 * acos (0.0));
}
