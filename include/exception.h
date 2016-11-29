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
                          exception.h  -  description
                             -------------------
    begin                : Tue Apr 24 2001
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef EXCEPTION_H
#define EXCEPTION_H

#include <adams_limits.h>

static const char * actionsTypeDesc[] = {
		"Active",
		"Inactive",
		"Get/Set"
	};



/** Questa classe definisce per ogni restrizione dei trigger attivati a fronte dell'attivazione
    da parte dell'utente di altre eventuali restrizioni. L'intecettazione di un determinato evento implica
    la attivazione/disattivazione dell'elemento di traffico padre come specificato dal campo "action".
    @short Eccezioni su restrizioni
    @author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class Exception {
public:

	enum actionsType
	{
		Active,
		Inactive,
		SetGetValue,
		actionsTypeNumber
        };


	Exception() { memset((void*)&data, 0, sizeof(DATA)); }
	~Exception() {}

		/** Metodo statico utilizzato per avere la dimensione in byte della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int 	idException;			// identificativo univoco
		char	tag[SHORT_DESC_LEN];		// descrizione breve (tag)
		char	description[LONG_DESC_LEN];	// descrizione dettagliata
		int	idTriggerRestriction;		// identificativo restrizione trigger
		int	triggeredStatus;		// stato in trigger
		char	triggeredValue[ASCII_REST_LEN];	// valore in trigger
		int	action;				// azione (true=abilita, false=disabilita)
		char	targetValue[ASCII_REST_LEN];	// valore in set
		int	idAggregateException;		// ulteriore eccezione (opzionale)
	} DATA;

	DATA data;
	bool Delete;

};

#endif
