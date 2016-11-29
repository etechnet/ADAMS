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

#ifndef ANALYSIS_H
#define ANALYSIS_H

#include <adams_limits.h>


class Analysis {
public:
	Analysis();
	~Analysis();

		/** Metodo statico utilizzato per determinare la dimensione della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		unsigned long idAnalysis;			// identificativo univoco
		bool FlagArchiveData;   		// Flag per l'attivazione del tab relativo ai Dati
		bool FlagCentrale;			// Flag per l'attivazione del tab relativo alle centrali
		bool FlagDate;	        		// Flag per l'attivazione del tab relativo alle date
		bool FlagOutputType;			// Flag per l'attivazione del tab relativo tipo di output
		bool FlagTimeRes;			// Flag per l'attivazione del tab relativo restrizione oraria
		bool FlagTrafficType;   		// Flag per l'attivazione del tab relativo tipo di traffico
		bool FlagSort;          		// Flag per l'attivazione del tab relativo all'ordinamento
		bool FlagCumulativeData;		// Flag per la generazione di analysis a cumulazione dei dati
		char LongDescription[LONG_DESC_LEN];   	// Descrizione dettagliata
		char ShortDescription[SHORT_DESC_LEN]; 	// Descrizione breve
		int  relations[MAX_RELATION];	       	// Relazioni
		int countersKitId;			// Id del kit contatori abbinatoa questa analysis
		int  reportsId[MAX_ANALYSIS_REPORTS];	// id reports abilitati
	} DATA;

	DATA data;
	bool Delete;

};

#endif
