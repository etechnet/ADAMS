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

/**
  * Questa classe � utilizzata per tenere traccia degli indici delle varie tabelle,e tramite il metodo <b>generaIndice</b> � possibile generare
  * un nuovo indice.
  */
#include <string.h>
#include "makeid.h"
#include "applogger.h"

#define DEBUG_MAKEID	1

/**
 * Costruttore della classe: non f� altro che impostare la variabile len a 0, la variabile len tiene traccia del numero di indici generati.
 */
makeID::makeID()
{
	len = 0;
}

/**
 * Distruttore.
 */
makeID::~makeID() {}

/**
 * Ordina in maniera crescente gli indici. L'ordinamento � di tipo <b>SelectionSort</b>
 */
bool makeID::ordinaIndici()
{
	/* Ordinamento SelectionSort */
	//lout << "ordinaIndici " << endl;
	for ( int i = 0; i < len - 1; i++ ) {
		int min = i;
		for ( int j = i + 1; j < len; j++ )
			if ( indici[j] < indici[min] )
				min = j;

		int appo = indici[i];
		indici[i] = indici[min];
		indici[min] = appo;

		int appo1 = contIndici[i];
		contIndici[i] = contIndici[min];
		contIndici[min] = appo1;
	}

	return true;
}

/**
 * Genera un nuovo indice. Da considerare che il nuovo indice non � presente negli indici precedentemente generati.
 * Ogni indice � univoco.
 */
int makeID::generaIndice()
{
	ordinaIndici();
	if ( len == 0 )
		return 1;

	if ( indici[0] - 0 > 1 ) {
		return indici[0] - 1;
	}
	for ( int i = 0; i < len - 1; i++ ) {
		if ( indici[i + 1] - indici[i] > 1 ) {
			return indici[i] + 1;
		}
	}
	if ( indici[len - 1] < MAX_ID )
		return indici[len - 1] + 1;
	else
		return -1;
}

/**
 * Metodo di servizio che stampa tutti gli indici presenti nell'array di indici <b>indici</b>
 * @see #indici
 */
void makeID::stampaIndici()
{
	if ( len == 0 ) {
		lout << "NON CI SONO INDICI" << endl;
		return;
	}

	for ( int i = 0; i < len; i++ )
		lout << "Indice: " << indici[i] << "  Occorrenza: " << contIndici[i] << endl;
}

/**
 * Inserisce un nuovo indice nell'array <b>indici</b>.
 * @param id indice da inserire nell'array. il metodo controlla se id e compreso nel range di valori validi [1-MAX_ID].
 */
void makeID::addIndice ( int id )
{
	int id_cont = 0;

	if ( id > MAX_ID || id < 1 ) {
		//lout << "Indice " << id << " fuori range [1-" << MAX_ID << "].\n";
		return;
	}
	if ( len == 0 ) {
		indici = new int[1];
		contIndici = new int[1];
		indici[0] = id;
		contIndici[0] = 1;
		len = 1;
	}
	else {
		id_cont = isPresentID ( id );
		if ( id_cont == -1 ) {
			//lout << "addIndice " << id << endl;
			len = len + 1;
			int *appo = new int[len];
			int *appo1 = new int[len];
			for ( int i = 0; i < len - 1; i++ ) {
				appo[i] = indici[i];
				appo1[i] = contIndici[i];
			}
			appo[len - 1] = id;
			appo1[len - 1] = 1;

			indici = new int[len];
			contIndici = new int[len];
			for ( int i = 0; i < len; i++ ) {
				indici[i] = appo[i];
				contIndici[i] = appo1[i];
			}
			delete ( appo );
			delete ( appo1 );
		}
		else {
			//lout << id << " � presente con occorrenza: " << contIndici[id_cont] <<endl;
			contIndici[id_cont] = contIndici[id_cont] + 1;
		}
		ordinaIndici();
	}
}

/**
 * Controlla se l'indice <b>id</b> � presente nell'array <b>indici</b>.
 * @param id Indice da controllare.
 * @return ritorna <b>true</b> se l'indice <b>id</b> � presente nell'array altrimenti ritorna <b>false</b>.
 */
int makeID::isPresentID ( int id )
{
	for ( int i = 0; i < len; i++ )
		if ( indici[i] == id )
			return i;
	return -1;
}

/**
 * Elimina l'indice <b>id</b> dell'array <b>indici</b>.
 * @param id Indice da eliminare.
 */
void makeID::delIndice ( int id )
{
	int idToDel = -1;
	for ( int i = 0; i < len; i++ )
		if ( indici[i] == id ) {
			if ( contIndici[i] == 1 ) {
				idToDel = i;
			}
			else {
				contIndici[i] = contIndici[i] - 1;
				idToDel = -1;
				return;
			}
			break;
		}

	if ( idToDel != -1 ) {
		int *appo = new int[len - 1];
		int *appo1 = new int[len - 1];

		for ( int i = 0; i < idToDel; i++ ) {
			appo[i] = indici[i];
			appo1[i] = contIndici[i];
		}
		for ( int i = idToDel + 1; i < len; i++ ) {
			appo[i - 1] = indici[i];
			appo1[i - 1] = contIndici[i];
		}

		len = len - 1;

		indici = new int[len];
		contIndici = new int[len];

		for ( int i = 0; i < len; i++ ) {
			indici[i] = appo[i];
			contIndici[i] = appo1[i];
		}

		delete ( appo );
		delete ( appo1 );
	}
	else
		lout << id << " non � presente\n";
}

/**
 * Individua l'indice presente alla posizione<b>pos</b> nell'array <b>indici</b>.
 * @param pos posizione all'interno dell'array <b>indici</b>.
 * @return ritorna l'indice trovato alla posizione <b>pos</b>.
 */
int makeID::getIndex ( int pos )
{
	return indici[pos];
}

/**
 * Individua l'l'occorrenza dell'indice presente alla posizione<b>pos</b> nell'array <b>indici</b>.
 * @param pos posizione all'interno dell'array <b>indici</b>.
 * @return ritorna l'occorrenza dell'indice presente alla posizione <b>pos</b>.
 */
int makeID::getOccor ( int pos )
{
	return contIndici[pos];
}

/**
 * Resetta l'array di indici <b>indici</b>.
 */
void makeID::reset()
{
	len = 0;
}


