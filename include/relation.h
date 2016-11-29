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
    begin                : Tue Oct 31 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef RELATION_H
#define RELATION_H

#include <adams_limits.h>


static const char * directionTypeDesc[] = {
		"Direct",
		"Inverse",
		"Both"
	};

/** Questa classe implementa la definizione e i metodi per la gestione delle relazioni
  * tra gli elementi di traffico.
   @short Definizione oggetto relazione per la configurazione ADAMS
  *@author Piergiorgio Betti
  */

class relation {
public:
	relation();
	~relation();

		/** Metodo statico utilizzato per determinare la dimensione (in bytes) della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	enum directionType {
		Direct,
		Inverse,
		Both,
		directionTypeNumber
	};

	typedef struct {
		int 	idRelation;				// identificativo univoco
		int 	idParentRelation;			// identificativo relazione padre alternativo alla selezione del 2^ el. traf.
		int 	idFirstElement;				// link all'elemento di traffico primario
		int	idSecondElement;			// link all'elemento di traffico secondario
		int	admittedDirections;			// direzioni ammesse (tagDirections)
		bool	admitHexValues;				// ammesse valorizzazioni esadecimali
		bool	admitNetworkAnalisys;			// ammessa analisi network
		bool	ghostRelation;				// relazione fantasma
		int	nextLevelRelations[NEXTLEVEL_RELATIONS];// lista relazioni di livello successivo
		int	restrictions[MAX_RESTRICTIONS];		// lista delle restrizioni
		int	tiedRestrictions[MAX_RESTRICTIONS];	// lista di restrizioni obbligatorie
	} DATA;

	DATA data;
	bool Delete;

};

#endif
