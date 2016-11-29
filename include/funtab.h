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
                          funtab.h  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/
#if !defined FUNTAB_H
#define FUNTAB_H

#include "mathparser.h"

class SymbolTable;

typedef double (*PFun) (double);

namespace Function
{
		/** Classe ad uso interno del parser matematico. Esegue la mappatura dei simboli
		  * @short Mappa simboli del parser matematico. USO INTERNO.
		  * @author Piergiorgio Betti <pbetti@lpconsul.net>
		  */
	class Table
	{
	public:
		Table (SymbolTable & symTab);
		~Table () { delete []_pFun; }
		int Size () const { return _size; }
		PFun GetFun (int id) { return _pFun [id]; }
	private:
		PFun * _pFun;
		int    _size;
	};
}

#endif
