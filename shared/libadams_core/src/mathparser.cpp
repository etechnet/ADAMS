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

#include <mathparser.h>

MathParser::MathParser ( QObject *parent ) : QObject ( ), maxBuf ( MAXBUFFERLEN ),
	maxSymbols ( MAXSYMBOLS ), parseresult ( 0.0 )
{
	symTab = new SymbolTable ( maxSymbols );
	funTab = new Function::Table ( *symTab );
	store = new Store ( maxSymbols, *symTab );
}

MathParser::~MathParser()
{
}

/** Pieps errors from subclasses */

void MathParser::errorPipe ( const char * message )
{
	errorqueue.append ( QString ( message ) );
}

/** The active part: parse / store / calculate */

bool MathParser::parse ( const QString & formula )
{
	Scanner scanner ( qPrintable ( formula ) );
	if ( !scanner.IsEmpty () ) {
		Parser parser ( scanner, *store, *funTab, *symTab );
		status = parser.Parse ();
		if ( status == stError )
			return true;		// got some error
		else if ( status == stOk ) {
			parseresult = parser.Calculate();
			return false;
		}
		parseresult = 0.0;
		return false;
	}
	parseresult = 0.0;
	return false;
}

/** do the work of adding a symbol */

bool MathParser::initSymbol ( QString & symbol, double value )
{
	QString str;

	str = symbol + "=" + QString::number ( value );
	return parse ( str );
}





