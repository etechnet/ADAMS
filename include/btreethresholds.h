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

//***************************************************************************
//                          btreenetworkrt.h  -  description
//                            -------------------
//    begin                : Wed Feb 7 2001
//    copyright            : (C) 2001 by Piergiorgio Betti
//    email                : piergiorgio.betti@e.tech.net
// ***[ History ]*************************************************************
//
//   - Date - - By ---------------- - What -----------------------------------
// ***************************************************************************/

#ifndef BTREETHRESHOLDS_H
#define BTREETHRESHOLDS_H

// #include "ntmmap.h"
#include "nodo.h"
#include <adams_limits.h>
#include <applogger.h>
#include <common_alarm_typedef.h>

#include <Qt/qmutex.h>
#include <Qt/qlist.h>
#include <Qt/qmap.h>


class ThresholdStatusNode
{
public:
	
	unsigned char valid_record;
	unsigned long alarm_id;
	unsigned long alarm_type;
	unsigned char alarm_status;
	unsigned char status_changed;
	QList< QList <UnionValue> > qc_list;
	QList< UnionValue > alarm_values;
	unsigned char sw_nodes[MAX_FEP];
	Nodo * nodo;

	ThresholdStatusNode() : alarm_id(0), nodo(0), valid_record(0)
	{
		memset(sw_nodes, 0, MAX_FEP);
	}

	ThresholdStatusNode(Nodo * nn) : alarm_id(0), nodo(nn), valid_record(0)
	{
		memset(sw_nodes, 0, MAX_FEP);
	}

	~ThresholdStatusNode() {
		if (nodo) delete nodo;
	}

	ThresholdStatusNode& operator=(const ThresholdStatusNode& o) {
		this->valid_record = o.valid_record;
		this->alarm_id = o.alarm_id;
		this->alarm_type = o.alarm_type;
		this->alarm_status = o.alarm_status;
		this->status_changed = o.status_changed;
		this->qc_list = o.qc_list;
		this->alarm_values = o.alarm_values;
		for (int i = 0; i < MAX_FEP; i++) this->sw_nodes[i] = o.sw_nodes[i];
		if (o.nodo) {
			int n_int = 0, n_dbl = 0;
			o.nodo->getCountersSize_r(n_int, n_dbl);
			this->nodo = new Nodo(n_int, n_dbl);
			this->nodo->setupCounters();
			*this->nodo = *o.nodo;
		}
		else {
			if (this->nodo) delete this->nodo;
			this->nodo = 0;
		}
	}	
};

typedef QMap<QString, ThresholdStatusNode> BtreeThreshold;
typedef QMap<QString, ThresholdStatusNode>::iterator BtreeThresholdIterator;

#define btree_threshold_key_rel_id( key_str )	( QString(key_str).left(THRESHOLD_RELATION_INKEY_DIGITS).toInt() )

/**
* Classe che estende la @ref ADAMSMap class per
* ottenere internamente ad  un albero binario un semaforo per accesso da parte di diversi thread.
* La classe fornisce inoltre un contatore interno thread-safe utilizzato per tenere traccia
* dei nodi che hanno riversato i propri dati con successo.
* @short Versione multithread della @ref ADAMSMap
* @author Piergiorgio Betti
*/

class BtreeThresholdNet : public BtreeThreshold
{
public:
	// use default constr/destr
// 	BtreeThresholdNet();
// 	~BtreeThresholdNet();
	/**
	  *Metodo per riservare l'accesso all'albero binario
	  *da parte del thread.
	  */
	inline void lock() {
		mutex.lock();
	};
	/**
	  *Metodo per liberare l'accesso all'albero binario
	  *da parte del thread.
	  */
	inline void unLock() {
		mutex.unlock();
	};
	/**
	  * Restituisce il numero di centrali eseguite.
	  */
	inline int getNumCen() {
		return iNumCen;
	};
	/** Azzermento contatore nodi */
	inline void resetNumCen() {
		iNumCen = 0;
	};
	/** Viene incrementato il contatore dei nodi completati */
	inline void incNumCen() {
		incmtx.lock();
		iNumCen++;
		incmtx.unlock();
	}
	
private:
	QMutex mutex;
	QMutex incmtx;
	int iNumCen;
};


#endif // BTREETHRESHOLDS_H
