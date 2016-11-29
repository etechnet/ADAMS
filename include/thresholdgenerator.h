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
                          thresholdgenerator.h  -  description
                             -------------------
    begin                : 2010-09-09
    copyright            : (C) 2010 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef THRESHOLDGENERATOR_H
#define THRESHOLDGENERATOR_H

#include <adams_limits.h>


/** Questa classe implementa la definizione e i metodi per la gestione dei
  * generatori di soglie allarmi (implementati come plugins).
   @short Definizione oggetto generatore soglie allarmi per la configurazione ADAMS
  *@author Piergiorgio Betti
  */

class thresholdgenerator {
public:
	thresholdgenerator();
	~thresholdgenerator();

		/** Metodo statico utilizzato per determinare la dimensione (in bytes) della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int	idThresholdGenerator;				// identificativo univoco
		char	description[LONG_DESC_LEN];			// descrizione
		int	typeThreshold;					// abilita soglie massime
		bool	enableHolydayThreshold;				// abilita soglie festive
		int	idPlugin;					// id of the handler plugin
		int	thresholdPersistence;				// persistenza dello storico soglie
		int	hoursAggregate;					// Numere ore di aggregazione delle soglie
	} DATA;

	DATA data;
	bool Delete;

};

#endif
