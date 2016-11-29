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

#ifndef DRINTERFACE_H
#define DRINTERFACE_H

#include <cnfglobals.h>
#include <adams_limits.h>
#include <Qt/qobject.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <importexport.h>
#include <assert.h>

#include <server_stub_safe_include.h>

using namespace net::etech;

#ifdef ADAMS_HPUX
#undef PATH_MAX
#define PATH_MAX	_XOPEN_PATH_MAX
#endif

#define DR_FIELDSDESCRIPTIONLENGHT	100
#define DR_MAXFIELDS			211	/* nearest integer to 200 as from QT prime number table */
#define DR_HEADERTAG			"RECORD_DR"
#define GLOBAL_HEADERTAG		"RECORD_GLOBALOPT"

enum DRF_Types {
        DRF_char,
        DRF_unsigned_char,
        DRF_short,
        DRF_unsigned_short,
        DRF_int,
        DRF_unsigned_int,
        DRF_long,
        DRF_unsigned_long,
        DRF_float,
        DRF_double,
        DRF_BCD,
        DRF_CString,
        DRF_String,
        DRF_NumTypes
};

/** Tipologie dati campi cartellino
    @short Tipologie dati campi cartellino
  */
typedef struct  {
	int type;
	int size;
	const char * description;
} DR_Field_Type;



/** Questa classe definisce l'oggetto campo nella definizione della base dati della configurazione
    del processo ADAMS. L'insieme di questi oggetti (raggruppati nella @ref DRInterface) definisce
    il record di base, ovvero il cartellino (DR) utilizzato per la eseguire l'analisi.
    @short Definizione campo del record DR
  */

class DRField
{
public:
	DRField() {
		data.position = data.fieldtype = data.offset = data.size = data.type_size = data.array_size = data.description[0] = 0;
		Delete = false, data.is_array = false;
	}
	/** Metodo statico utilizzato per determinare la dimensione (in bytes) della struttura dati */
	static int dataSize() {
		return sizeof ( DATA );
	}

	typedef struct {
		int position;			/* fields sorting */
		int fieldtype;			/* refer to DRFieldTypes */
		int offset;
		int size;
		int type_size;
		bool is_array;
		int array_size;
		char description[DR_FIELDSDESCRIPTIONLENGHT];
		bool isIndex;
		bool indexByPlugin;
		int indexBlockSize;
		int indexRealTimeBlockSize;
		int startSize;
		char indexSuffix[TE_SUFFIX_LEN];
		char startTime[DR_DATE_LEN];
		char endTime[DR_DATE_LEN];
	} DATA;

	DATA data;
	int Delete;
};

typedef QMultiHash<QString, DRField *>::iterator		DRFieldIterator;
typedef QMultiHash<QString, DRField *>::const_iterator		DRFieldConstIterator;

/** In questa classe sono racchiuse le informazioni aggiuntive (di carattere generale) della configurazione non altrimenti assimilabili
  * ad altre sezioni della configurazione. Bench� non vi sia uno stretto rapporto logico con i cartellini di ADAMS, l'interfaccia di gestione
  * � implementata nella @ref DRInterface.
  @short Definizione parametri globali della configurazione ADAMS.
  */

class GlobalOpt
{
public:
	GlobalOpt() {
		memset ( ( void * ) &data, 0, sizeof ( DATA ) );
	}
	~GlobalOpt() {}

	/** Metodo statico utilizzato per determinare la dimensione (in bytes) della struttura dati */
	static int dataSize() {
		return sizeof ( DATA );
	}

	typedef struct {
		bool drUsePlugin;
		char drPluginName[DR_PLUGINNAME_LEN];
		bool drUsePath;
		char drPathName[DR_PATH_LEN];
		char glbDefaultPluginPath[GLB_INFO_LEN];
		char glbAlias[GLB_INFO_LEN];
		char glbAuthor[GLB_INFO_LEN];
		char glbLastModified[GLB_INFO_LEN];
	} DATA;

	DATA data;
	int Delete;
};


/**Implementa la necessaria interfaccia verso i record cartellino (Call Detail Record), ovvero la mappa
  *(virtuale) del record ed i metodi per l'I/O verso il file dei cartellini.
  *Rappresenta il gradino piu' basso nello strato della configurazione ADAMS e definisce sostanzialmente la descrizione
  *dei campi, (@ref DRField) di cui e' composto il record-buffer fornito dal plugin di alimentazione del processo DataServer,
  *e quindi i dati grezzi sulla cui base verranno composti i DataElement.
  *@short Definizione del record DR.
  *@author Piergiorgio Betti
  */

class DRInterface : public QObject
{
	Q_OBJECT
public:
	DRInterface();
	~DRInterface();

	/** Inserisce un nuovo campo nel record. Il campo assume la posizione specificata
	  * o in assenza della posizione ordinale, viene accodato alla lista campi. */
	bool add ( DRField * newfield );
	/** Rimuove il campo specificato dalla hash table. Viene eseguita anche la delete (deallocazione)
	  * del record.
	  */
	bool remove ( int fieldnum );
	/** Reinizializzazione della classe */
	void clear();
	/** Estrae un campo dalla hush table (per indirizzo) */
	DRField * get ( int fieldnum );
	/** Estrae un campo dalla hush table (per tag) */
	DRField * getByTag ( const QString & srcTag );
	/** Estrae un campo dalla hush table (per indirizzo) versione fast */
	inline DRField * fastget ( int fieldnum ) {
		return &fimage[fieldnum];
	}
	/** inizializza la funzionalit� del method fastget() */
	bool initFastget ( bool clear = false );
	/** Fornisce una lista dei tag degli elementi disponibili */
	TagList getTagList();
	/** Ritorna il numero di campi nel record */
	inline int count() {
		return fields->count();
	}
	/** Ritorna il field id pi� elevato */
	inline int highestId() {
		return fieldsnumber;
	}
	/** ritorna la lista dei tipi campo */
	static const QStringList * getFieldTypes();
	/** Ritorna il tag identificativo della sezione DR nel file di configurazione */
	const char * getHeaderTag();
	/** Ritorna il tag identificativo della sezione GLOBALOPT nel file di configurazione */
	const char * getGlobalOptHeaderTag();
	/** Ritrona la dimensione del record DR */
	int getDRRecordSize();
	/** Riporta la dimesione (in byte) del tipo richiesto */
	static const size_t getTypeSize ( int atype );
	/** Percorre la lista dei campi ricalcolando l'offset esatto */
	void recalculateOffset();
	/** Ritorna un iteratore alla lista */
	DRFieldIterator getIterator() {
		assert ( fields != 0 );
		return fields->begin();
	}
	/** Ritorna un iteratore alla lista */
	DRFieldIterator hashEnd() {
		assert ( fields != 0 );
		return fields->end();
	}

	DRInterface & operator= (const DRInterface & in) {
		if (this == &in)
			return *this;
		this->clear();
		foreach (DRField * d, *in.fields) {
			this->add(d);
		}

		return *this;
	}
	/** Metodo utilizzato per effettuare il setup della lista campi per le funzioni di import/export */
	void ieInit();
	/** Metodo utilizzato per eseguire l'export in formato intermedio (ASCII) della configurazione DR */
	void ieExport ( ImportExport & ie );
	/** Questo method consente un accesso diretto (via puntatore) alla struttura GlobalOpt interna */
	inline GlobalOpt * globalOpt() {
		return &glbOpt;
	}

	/** Copia i dati della classe nella corrispondente sequenza CORBA */
	void copyToCorba ( DRSeq * seqptr );
	/** Inizializza o re-inizializza i dati della classe a partire dalla corrispondente sequenza CORBA */
	void fillFromCorba ( const DRSeq * seqptr );
	/** Copia i dati della classe nella corrispondente sequenza CORBA */
	void copyToCorba ( GLOBALOPT * ptr );
	/** Inizializza o re-inizializza i dati della classe a partire dalla corrispondente sequenza CORBA */
	void fillFromCorba ( const GLOBALOPT * ptr );

private:
	QMultiHash<QString, DRField *> * fields;		// DR fields hush table
	GlobalOpt glbOpt;
	int fieldsnumber;
	char pathname[PATH_MAX];				// file name + path
	QDateTime currentVersionDate;
	QString sectionDescription;

	bool firsttime;
	DRField * fimage;

public slots: // Public slots
	/** Esegue l'import di un record nella QDict privata. Richiamato dalla classe ImportExport */
	void ieImport ( QString & ieRecordTag, ImportExport * ieptr );
};



#endif
