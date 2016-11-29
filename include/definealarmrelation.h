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
                          alarmdefinerelation.h  -  description
                             -------------------
    begin                : 2011-02-09
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : piergiorgio.betti@e-tech.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef DEFINERELATION_H
#define DEFINERELATION_H

#include <stdlib.h>
#include <Qt/qstring.h>
#include "configuretypedefs.h"
#include <adams_limits.h>
#include "alarmrelationinterface.h"

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
} AlarmRelationComponent;


/** La classe DefineAlarmRelation viene utilizzata per esplodere le componenti di una relazione, ovvero gli
  * elementi di traffico, riassumendo le infomazioni essenziali (operative) in una struttura ottimizzata
  * di @ref AlarmRelationComponent. Le informazioni cos� decomposte vengono utilizzate essenzialmente (ma non unicamente) all'interno
  * della classe @ref builder del alarmdataserver.
  * @short Decodifica componenti di una relazione di traffico.
  * @author Piergiorgio Betti <pbetti@lpconsul.net>
  */

class DefineAlarmRelation
{
public:
	DefineAlarmRelation();
	~DefineAlarmRelation();
	/** Inizializzazione della classe relativamente ad una data relazione di traffico.
	  * @param RelationForma Dizionario relazioni di traffico
	  * @param ElementFormat Dizionario elementi di traffico
	  * @param Rel Relazione di traffico da decomporre
	  */
	void setElementInRelation ( const AdamsCompleteConfig* ncc, int Rel );
	/** Ritorna un array di @ref AlarmRelationComponent relativi agli elementi di traffico della relazione */
	inline AlarmRelationComponent * getAlarmRelationComponentsArray() {
		return aiElementInRelation;
	};
	/** Ritorna uno specifico @ref AlarmRelationComponent relativo agli elementi di traffico della relazione */
	inline AlarmRelationComponent * getAlarmRelationComponent ( unsigned int id ) {
		return ( id < Dimension ) ? &aiElementInRelation[id] : 0;
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
	unsigned long * decodeRelToVector ( int relid, AlarmRelationInterface* RelInterface );
	/** Formatta una chiave di relazione in una stringa
	  *@param rel_string Stringa della relazione
	  *@param fixed_len formatta (fill) i campi alla loro dimensione predefinita
	  */
	QString formatRelationToString ( const AdamsCompleteConfig * ncc, const QString & rel_string, const QString & sep, bool fixed_len = true );

private:
	AlarmRelationComponent aiElementInRelation[MAX_DIMENSION];
	int Dimension;

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

	inline bool pure_digits ( const QString & str ) {
		for ( int i = 0; i < str.length(); i++ )
			if ( ! str.at ( i ).isDigit() )
				return false;
		return true;
	}

	inline int number_clean ( QString & s ) {
		QString n = QString::number(s.toLong());
		s = n;
		return s.length();
	}

	inline int double_clean ( QString & s ) {
		QString n = QString::number(s.toDouble());
		s = n;
		return s.length();
	}

	inline int ascii_clean ( QString & s ) {
		QString n = s.simplified();
		s = n;
		return s.length();
	}

};

#endif
// kate: indent-mode cstyle; replace-tabs off; tab-width 8; 
