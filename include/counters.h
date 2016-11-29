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
                          counters.h  -  description
                             -------------------
    begin                : Mon Jan 20 2003
    copyright            : (C) 2003 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************
  
   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef COUNTERS_H
#define COUNTERS_H

#include <adams_limits.h>

/** Gestione dei record contatori sia per la fasi operative che nella gestione dell'i/o.
  * Nella struttura dati viene gestita la programmazione dei contatori di traffico.
  *@short Contatori traffico.
  *@author Piergiorgio Betti
  */

class Counters {
public:
	
	Counters() { memset((void*)&data, 0, sizeof(DATA)); }
	~Counters() {}
	
		/** Metodo statico utilizzato per avere la dimensione in byte della struttura dati */
	static int dataSize() { return sizeof(DATA); }
	
	typedef struct {
		int triggerField;
		long triggerValue;
		int counterIndex;
		int counterType;
		int percentOf;
		int triggerCounter;
		char tag[CNT_TAG_LEN];
		char description[CNT_DESC_LEN];
	} COUNTERKIT;	
	
	typedef struct {
		int idCounter;
		char tag[SHORT_DESC_LEN];
		bool usePlugin;
		char pluginName[CNT_PLUGINNAME_LEN];
		bool usePath;
		char pathName[CNT_PATH_LEN];
		COUNTERKIT counterKit[CNT_NUM];
	} DATA;
	
	DATA data;
	bool Delete;
	
};

#endif
