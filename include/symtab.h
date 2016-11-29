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
                          symtab.h  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/
#if !defined SYMTAB_H
#define SYMTAB_H
//------------------------------------
//  symtab.h
//  (c) Bartosz Milewski, 1994
//------------------------------------
#include "htab.h"
#include <string.h>

// String table maps strings to ints
// and ints to strings

class SymbolTable
{
public:
	enum { idNotFound = -1 };    

	explicit SymbolTable (int size);
    ~SymbolTable ();
    int ForceAdd (char const * str);
    int Find (char const * str) const;
    char const * GetString (int id) const;
private:
    HTable  _htab;
    int *   _offStr; // offsets of strings in buffer
    int     _size;
    int     _curId;
    char *  _strBuf;
    int     _bufSize;
    int     _curStrOff;
};
#endif
