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
                          alarmgenerator.h  -  description
                             -------------------
    begin                : 2010-09-09
    copyright            : (C) 2010 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef ALARMGENERATOR_H
#define ALARMGENERATOR_H

#include <adams_limits.h>


/** Questa classe implementa la definizione e i metodi per la gestione dei
  * generatori di allarme (implementati come plugins).
   @short Definizione oggetto generator allarmi per la configurazione ADAMS
  *@author Piergiorgio Betti
  */

class alarmgenerator {
public:
	alarmgenerator();
	~alarmgenerator();

		/** Metodo statico utilizzato per determinare la dimensione (in bytes) della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int	idAlarmGenerator;				// identificativo univoco
		char	shortDescription[SHORT_DESC_LEN];		// descrizione breve (tag)
		char	longDescription[LONG_DESC_LEN];			// descrizione
		int	idPlugin;					// id of the handler plugin
		bool	thresholdManagement;				// abilitazione generazione soglie
		int	idThresholdGenerator[MAX_THRESHOLD_GENERATOR];	// lista id generatori soglie abbinato
	} DATA;

	DATA data;
	bool Delete;

};

#endif
