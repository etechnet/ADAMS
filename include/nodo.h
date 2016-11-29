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

#ifndef NODO_H
#define NODO_H
#include <string.h>
#include <Qt/qlist.h>

#include <adams_limits.h>

#define INT_TYPE		0
#define DOUBLE_TYPE		1
#define SORT_SHIFT		8		// used in derived class for sort ops.

/* Struttura di base utilizzata per l'incapsulamento dei contatori */

typedef struct  {
	unsigned long * int_counters;
	double * dbl_counters;
	void * usr_data;
} ParameterList;

class NodoFastMem;

/*
** Definizione dell'oggetto che rappresenta il singolo elemento dell'albero binario utilizzato dalla
 * matrice di traffico per la costruzione delle relazioni.
 * In questa classe vengono gestiti in modalita' dinamica i contatori di traffico. Il dimensionamento
 * dei contatori puo' avvenire in maniera esplicita (@ref setCountersSize) o tramite costruttore
 * di copia.
 * @short Contatori di traffico dinamici.
 * @author Piergiorgio Betti <pbetti@lpconsul.net>
*/

class Nodo
{
public:
	/** Costruttore di default della classe. Crea una struttura NON dimensionata dei contatori */
	Nodo();
	/** Costruttore di copia. Inizializza i contatori duplicando l'argomento */
	Nodo ( ParameterList LParametri );
	/** Costruttore per modalita' rientrante e dati nei contatori
	  * @param n_int Numero contatori interi
	  * @param n_dbl Numero contatori in virgola mobile
	  */
	Nodo ( int n_int, int n_dbl );
	/** Costruttore per modalita' rientrante e dati area utente
	  * @param size Dimensione area utente
	  */
	Nodo ( int size );

	~Nodo();
	/** Metodo di accesso alla struttura dei contatori */
	inline ParameterList *GetLista() {
		return &ListaParametri;
	}
	inline const ParameterList *ConstGetLista() const {
		return ( const ParameterList * ) &ListaParametri;
	}
	/** Forza la modalit� reentrant */
	void setReentrant();
	/** Somma due set di contatori */
	Nodo& operator+= ( Nodo& );
	/** OPeratore copia specializzato per la classe */
	Nodo& operator= ( Nodo& );
	/** OPeratore copia specializzato per la classe */
	Nodo& operator= ( const Nodo& );
	/** Definisce la dimensione dei contatori senza effettuare la relativa allocazione. Vedi @ref setupCounters */
	static void setCountersSize ( int n_int, int n_dbl );
	void setCountersSize_r ( int n_int, int n_dbl );
	/** Definisce la dimensione del buffer di pre-allocazione per le versioni fast dei metodi
	  * @param NFM Instanza @ref NodoFastMem di riferimento
	  * @param bufsiz Dimensione del buffer (in numero nodi)
	  */
	static void setNodePreAllocBuffer ( NodoFastMem * NFM, int bufsiz );
	/** Definisce il numero di elementi in preallocazione per i metodi "fast"
	  * @param nel Numero elementi in preallocazione
	  */
	static void setPreAllocElements ( int nel );
	/** Metodo di allocazione fast da utilizzarsi congiuntamente ad operazioni si grandi volumi di alberi binari
	  * @param NFM Instanza @ref NodoFastMem di riferimento
	  */
	static Nodo * fastAlloc ( NodoFastMem * NFM );
	/** Dimensione dei contatori in utilizzo dal nodo
	  * @param n_int Numero contatori interi
	  * @param n_dbl Numero contatori in virgola mobile
	  */
	static void getCountersSize ( int & n_int, int & n_dbl );
	/** Dimensione dei contatori in utilizzo dal nodo
	  * @param n_int Numero contatori interi
	  * @param n_dbl Numero contatori in virgola mobile
	  */
	void getCountersSize_r ( int & n_int, int & n_dbl );
	/** Effettua l'allocazione dei contatori in numero come definito da @ref setCountersSize */
	void setupCounters();
	/** Effettua l'allocazione dei contatori in numero come definito da @ref setCountersSize
	  * @param NFM Instanza @ref NodoFastMem di riferimento
	  * @param nel Valore opzionale di riallocazione
	  */
	void setupCounters_fast ( NodoFastMem * NFM, int nel = 0 );
	/** Test sullo stato di allocazione dei contatori del nodo */
	inline bool isUnallocated() const {
		return this->unallocated;
	}
	/** Imposta i parametri per la gestione user data */
	static void setupUserDataSize ( int size );
	/** Ritorna i dati sui parametri utente */
	static bool haveUserData();
	/** Ritorna i dati sui parametri utente */
	static int userDataSize();
	/** Verifica uitilizzo reentrant */
	inline bool isReentrant() {
		return m_reentrant;
	}
	/** Ritorna i dati sui parametri utente */
	bool haveUserData_r();
	/** Ritorna i dati sui parametri utente */
	int userDataSize_r();
	/** Azzeramento dei contatori */
	void reset();

private:
	ParameterList ListaParametri;
	bool unallocated;
	// re-entrant members
	bool m_reentrant;
	int m_num_ints;
	int m_num_dbls;
	int m_preAlloc_Size;
	bool m_userData;
	int m_usrDataSize;

};

/** Questa classe implementa un gestore interno della memoria per le operazioni sui contatori (classe @ref Nodo)
 *  in modalit� "fast".
 *  @short Gestione memoria per contatori di traffico dinamici.
 *  @author Piergiorgio Betti <pbetti@lpconsul.net>
*/
class NodoFastMem
{
public:
	NodoFastMem() :
		preAllocCount ( 0 ),
		mem_cursor ( 0 ),
		nodePreAlloc_Size ( 0 ),
		nodePreAllocCount ( 0 ),
		node_mem_cursor ( 0 ),
		nodePreAllocDone ( false ) {}
	~NodoFastMem() {}

	int preAllocCount;
	char * mem_cursor;

	int nodePreAlloc_Size;
	int nodePreAllocCount;
	Nodo * node_mem_cursor;
	bool nodePreAllocDone;

};


#endif
