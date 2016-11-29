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

#include <btreenetworkrt.h>

static QMutex mutex;
static QMutex incmtx;


BTreeNetworkRT::BTreeNetworkRT()
	: num_inputs_nodes ( 0 )
{
}

BTreeNetworkRT::~BTreeNetworkRT()
{
}

void BTreeNetworkRT::incDoneInputsN()
{
	incmtx.lock();
	num_inputs_nodes++;
	incmtx.unlock();
}

void BTreeNetworkRT::insertOrSum ( const QString & in_key, Nodo & in_nodo )
{
	mutex.lock();

	BtreeMapIterator i = find ( in_key );

	if ( i == end() ) {
		Nodo n;
		n.setupCounters();
		n += in_nodo;
		insert ( in_key, n );
	}
	else {
		i.value() += in_nodo;
	}

	mutex.unlock();
}
