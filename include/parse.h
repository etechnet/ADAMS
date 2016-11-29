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

#if !defined PARSE_H
#define PARSE_H

#include <tree.h>
#include <Qt/qobject.h>

enum Status
{
    stOk,
    stQuit,
    stError
};

class Node; // forward declaration
class Scanner;
class Store;
class SymbolTable;
namespace Function
{
	class Table;
}

/** In questa classe vengono implementate le funzionalita di valutazione di una espressione matematica
  * @short Valutazione (parsing) di un'espressione
  * @author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class Parser : public QObject
{
	Q_OBJECT
public:
    Parser (Scanner & scanner,
            Store & store,
            Function::Table & funTab,
            SymbolTable & symTab );
    ~Parser ();
    Status Parse ();
    double Calculate () const;
private:
    Node * Expr();
    Node * Term();
    Node * Factor();
    void Execute ();

    Scanner &    _scanner;
    Node *       _pTree;
    Status       _status;
    Store &      _store;
    Function::Table &  _funTab;
    SymbolTable &    _symTab;
signals: // Signals
  			/** Signal emesso per richiedere la valorizzazione in sincrono di una variabile */
    void askForSymbolValue(char *, int, Store &);
};

#endif
