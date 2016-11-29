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

#include <funtab.h>
#include <symtab.h>

#include <assert.h>
#include <string.h>
#include <math.h>
#include <Qt/qstring.h>

namespace Function
{
double CoTan ( double x )
{
	double y = tan ( x );
	if ( y == 0 ) {
		QString str;
		str.sprintf ( "cotan of %f undefined", x );
		MathParser::errorPipe ( qPrintable ( str ) );
		return HUGE_VAL;
	}
	return 1.0 / y;
}

class Entry
{
public:
	PFun pFun;
	const char * strFun;
};

Entry Array [] = {
	log,  "log",
	log10, "log10",
	exp,  "exp",
	sqrt, "sqrt",
	sin,  "sin",
	cos,  "cos",
	tan,  "tan",
	CoTan, "cotan",
	sinh, "sinh",
	cosh, "cosh",
	tanh, "tanh",
	asin, "asin",
	acos, "acos",
	atan, "atan",
};

Table::Table ( SymbolTable & symTab )
	: _size ( sizeof Array / sizeof Array [0] )
{
	_pFun = new PFun [_size];
	for ( int i = 0; i < _size; ++i ) {
		int len =  strlen ( Array [i].strFun );
		_pFun [i] = Array [i].pFun;
		int j = symTab.ForceAdd ( Array[i].strFun );
		assert ( i == j );
	}
}

}

