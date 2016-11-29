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
                          definerelation.h  -  description
                             -------------------
    begin                : Thu Feb 8 2001
    copyright            : (C) 2001 by Salvatore Sannino
    email                : pbettio@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef DEFINERELATION_H
#define DEFINERELATION_H

#include <stdlib.h>
#include <qstring.h>
#include "configuretypedefs.h"
#include <adams_limits.h>
// #include "ntmbasic.h"
// #include "pluginbase.h"

#define FIRST_ELEMENT	0
#define SECOND_ELEMENT	1

class AdamsBasic;
class PluginImpl;

/* Questa struttura viene utilizzata per eseguire un accesso ottimizzato alle componenti
  * (elementi di traffico) di una relazione.
  */

typedef struct {
	int CDRLink;
	int id;
	QString desc;
	int length;
	int hasShifter;
	SHIFTRANGE * shiftRanges;
	AdamsBasic * scriptHandler;
	PluginImpl * pluginHandler;
} RelationComponent;


/** La classe DefineRelation viene utilizzata per esplodere le componenti di una relazione, ovvero gli
  * elementi di traffico, riassumendo le infomazioni essenziali (operative) in una struttura ottimizzata
  * di @ref RelationComponent. Le informazioni cos� decomposte vengono utilizzate essenzialmente (mo non unicamente) all'interno
  * della classe @ref builder del ntmdataserver.
  * @short Decodifica componenti di una relazione di traffico.
  * @author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class DefineRelation {
public:
	DefineRelation();
	~DefineRelation();
	/** Inizializzazione della classe relativamente ad una data relazione di traffico.
	  * @param RelationForma Dizionario relazioni di traffico
	  * @param ElementFormat Dizionario elementi di traffico
	  * @param Rel Relazione di traffico da decomporre
	  */
	void setElementInRelation ( const AdamsCompleteConfig* ncc, int Rel, const int* ffVector = 0);
	/** Ritorna un array di @ref RelationComponent relativi agli elementi di traffico della relazione */
	inline RelationComponent * getRelationComponentsArray() {
		return aiElemenInRelation;
	};
	/** Ritorna uno specifico @ref RelationComponent relativo agli elementi di traffico della relazione */
	inline RelationComponent * getRelationComponent ( unsigned int id ) {
		if ( invert && id == 0 )
			id = 1;
		else if ( invert && id == 1 )
			id = 0;
		return ( id < Dimension ) ? &aiElemenInRelation[id] : 0;
	};
	/** Ritorna il numero di elementi di traffico che compongono la relazione */
	inline int getDimension() {
		return Dimension;
	};
	/** Metodo similare al @ref GetElementInRelation, ma torna un array di idDataElement e non di @ref ELE.
	  * Tale method inoltre opera su una relazione arbitraria fornita in argomento.
	  *@param relid Id della relazione da decomporre
	  *@param RelInterface Dizionario relazioni di traffico
	  */
	unsigned long * decodeRelToVector ( int relid, RelationInterface* RelInterface, const int* ffVector = 0);
	/** Metodo per l'attivazione dell'accesso inverso ai componenti di primo livello della relazione */
	inline void setInvertAccess() {
		invert = true;
	}
private:
	RelationComponent aiElemenInRelation[MAX_DIMENSION];
	int Dimension;
	bool invert;
	/** Duplicazione di un vettore di unsigned long */
	inline unsigned long * dupVector ( unsigned long * vec1 ) {
		unsigned long * newvect = new unsigned long [MAX_DIMENSION];
		if ( newvect )
			for ( int i = 0; i < MAX_DIMENSION; i++ )
				newvect[i] = vec1[i];
		return newvect;
	}
	/** Conteggio degli id allocati in un vettore */
	inline int vecCount ( unsigned long * vec ) {
		int i = 0;
		while ( vec[i] > 0 && i < MAX_DIMENSION )
			++i;
		return i;
	}
};

#endif
