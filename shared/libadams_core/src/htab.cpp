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
                          htab.cpp  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/
//------------------------------------
//  htab.cpp
//  (c) Bartosz Milewski, 1994
//------------------------------------

#include "htab.h"

#include <assert.h>
#include <string.h>

// Find the list in hash table that may contain
// the id of the string we are looking for

List const & HTable::Find (char const * str) const
{
    int i = hash (str);
    assert (i >= 0 && i < _size);
    return _aList [i];
}

void HTable::Add (char const * str, int id)
{
    int i = hash (str);
    assert (i >= 0 && i < _size);
    _aList [i].Add (id);
}

// Private hashing function

int HTable::hash (char const * str) const
{
    // must be unsigned, hash should return positive number
    unsigned h = str [0];
	assert (h != 0); // no empty strings, please
    for (int i = 1; str [i] != 0; ++i)
        h = (h << 4) + str [i];
    return h % _size;  // small positive integer
}

