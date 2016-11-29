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

#ifndef MATHPARSER_H
#define MATHPARSER_H

#include <Qt/qobject.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>

#include <scan.h>
#include <parse.h>
#include <symtab.h>
#include <store.h>
#include <funtab.h>

#define MAXBUFFERLEN	1024
#define MAXSYMBOLS	100

static QStringList errorqueue;

/** Implementa un parser matematico utilizzato per il calcolo dinamico
  * delle componenti di traffico non direttamente dervivate da campi del
  * cartellino come ad es. contatori (BIDS). Questa classe effettua genericamente la
  * decodifica e successiva valutazione di una espressione matematica. I valori simbolici non
  * definiti dall'utente vengono richiesti al chiamate tramite il signal @ref Parser::askForSymbolValue.
  *@short Motore parser matematico
  *@author Piergiorgio Betti
  */

class MathParser : public QObject
{
	Q_OBJECT
public:
	MathParser ( QObject *parent = 0 );
	~MathParser();
	/** Pipe statica per la ricezione degli errori dalle
	  * sottoclassi del parser
	  */
	static void errorPipe ( const char * message );
	/** Utilizzato per ricevere la coda dei messaggi di errore generati dal parser */
	inline const QStringList * getErrorQueue() {
		return & errorqueue;
	}
	/** Reset della coda errori del parser */
	inline void clearError() {
		errorqueue.clear();
	}
	/** Questo method restituisce il risultato calcolato dal parser */
	inline double result() {
		return parseresult;
	}
	/** Esegue il parsing dell'espressione in argomento. Il method ritorna un valore true in caso
	  * di errore
	  */
	bool parse ( const QString & formula );
	/** Aggiunge una nuova variabile simbolica valorizzata */
	bool initSymbol ( QString & symbol, double value = 0.0 );

private:
//	static QStringList errorqueue;
	const int maxBuf;
	const int maxSymbols;
	int status;
	SymbolTable *symTab;
	Function::Table *funTab;
	Store *store;
	double parseresult;

};

#endif
