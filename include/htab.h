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
                          htab.h  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/
#if !defined HTAB_H
#define HTAB_H
//------------------------------------
//  htab.h
//  (c) Bartosz Milewski, 1994
//------------------------------------
#include "vlist.h"
#include <string.h>

// Hash table of strings

/** Classe ad uso interno del parser matematico. Implemente una hash table (stringhe) per l'accesso ai nomi simbolici.
  * @short Hash table. USO INTERNO.
  * @author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class HTable
{
public:
    explicit HTable (int size): _size(size)
    {
        _aList = new List[size];
    }

    ~HTable ()
    {
        delete [] _aList;
    }

    void Add (char const * str, int id);
public:
	class Seq: public List::Seq
	{
	public:
		Seq (HTable const & htab, char const * str)
			: List::Seq (htab.Find (str)) {}
	};

// 	friend Seq;
private:
    List const & Find (char const * str) const;
    int hash (char const * str) const;

    List * _aList;
    int    _size;
};

#endif
