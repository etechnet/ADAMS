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
                          scan.h  -  description
                             -------------------
    begin                : Fri Nov 3 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/
#if !defined SCAN_H
#define SCAN_H
//------------------------------------
//  scan.h
//  (c) Bartosz Milewski, 1994
//------------------------------------

#include <assert.h>

enum EToken
{
    tEnd,
    tError,
    tNumber,
    tPlus,
    tMult,
    tMinus,
    tDivide,
    tLParen,
    tRParen,
    tAssign,
    tIdent
};

class Scanner
{
public:
	enum { maxSymLen = 80 };

    Scanner (char const * buf);
	bool IsDone () const { return _buf [_iLook] == '\0'; }
	bool IsEmpty () const { return _buf [0] == '\0'; }
    EToken  Token () { return _token; }
    EToken  Accept ();
    int GetSymbolName (char * strOut, int lenBuf);
    double Number ()
    {
        assert (_token == tNumber);
        return _number;
    }
private:
    void EatWhite ();

    char const * const  _buf;
    int                 _iLook;
    EToken              _token;
    double              _number;
    int                 _iSymbol;
    int                 _lenSymbol;
};
#endif
