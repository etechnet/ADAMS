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

#ifndef BTREENETWORKRT_H
#define BTREENETWORKRT_H

#include <Qt/qmap.h>
#include <nodo.h>
#include <Qt/qthread.h>
#include <Qt/qmutex.h>

typedef QMap<QString, Nodo> BtreeMap;
typedef QMap<QString, Nodo>::iterator BtreeMapIterator;

/**
 * Extends QMap to have Adams's @ref Node better management in tree building
* @author Piergiorgio Betti
*/

class BTreeNetworkRT : public BtreeMap
{
public:
	BTreeNetworkRT();
	~BTreeNetworkRT();

	inline int getDoneInputsN() {
		return num_inputs_nodes;
	};

	inline void resetDoneInputsN() {
		num_inputs_nodes = 0;
	};

	void incDoneInputsN();

	void insertOrSum ( const QString & in_key, Nodo & in_nodo );

private:
	volatile int num_inputs_nodes;
};

#endif
