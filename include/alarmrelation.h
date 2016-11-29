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
                          relation.h  -  description
                             -------------------
    begin                : 2010-09-09
    copyright            : (C) 2010 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef ALARMRELATION_H
#define ALARMRELATION_H

#include <adams_limits.h>


/** Questa classe implementa la definizione e i metodi per la gestione delle direttrici (relazioni)
  * di allarme.
   @short Definizione oggetto relazione allarmi per la configurazione ADAMS
  *@author Piergiorgio Betti
  */

class alarmrelation {
public:
	alarmrelation();
	~alarmrelation();

		/** Metodo statico utilizzato per determinare la dimensione (in bytes) della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int 	idAlarmRelation;				// identificativo univoco
		char	description[LONG_DESC_LEN];			// descrizione
		int	relationElements[MAX_DIMENSION];		// id elementi di traffico direttrice
		int	relationElementsEnabled[MAX_DIMENSION];		// abilitazione elementi di traffico della relazione
		int	alarmHandlers[MAX_ALARM_GENERATOR];		// lista generatori allarme
		int	timeFractionElementId;				// traffic element di rottura oraria
		int	familyId;					// alarm family (type) aggregation code
		int	countersKitId;					// tag del kit contatori abbinato
		int	idConditionScript;				// script condizioni valutazione contatori
		int	idConditionPlugin;				// plugin condizioni valutazione contatori
	} DATA;

	DATA data;
	bool Delete;

};

#endif
