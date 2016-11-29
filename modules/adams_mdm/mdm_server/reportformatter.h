/***************************************************************************
                          reportformatter.h  -  description
                             -------------------
    begin                : Thu Mar 23 2003
    copyright            : (C) 2003 by Piergiorgio Betti
    email                : pbetti@lpconsul.net>
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef _REPORTFORMATTER_H_
#define _REPORTFORMATTER_H_

#include <Qt/qstring.h>
#include <Qt/qtextstream.h>
#include <Qt/qthread.h>
#include <Qt/qhash.h>

#include <configuretypedefs.h>
#include <queryparams.h>
#include <nodeconfighandler.h>
#include <definerelation.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <httpimpl.h>
#include <btreenetworkrt.h>
#include <timeout.h>
#include <adamsserver.h>

#define INDENT_CHARS				2		// number of spaces to use for indentation
#define TOTALS_POOL_SIZE			100
#define FORCE_BODY_HDR_ROWS			60

/**
 * Questa classe implementa la gestione della creazione,
 * formattazione parametrica (come da configurazione utente) ed invio al server HTTP dei report NTM.
 * @short Gestione report parametrici
 * @author Piergiorgio Betti
 **/

class ReportFormatter
{
public:
	enum FormatterStatus {
		NotInitialized,
		Initialized,
		MediaInitialized,
		FullInitialized,
		Processing,
		InErrorWhileProcessing,
		InErrorWhileSending,
		numFormatterStatus
	};

	enum OutputMedia {
		HttpServer,
		LocalFile,
		DataExport,
		numOutputMedia
	};

	/** Costruttore della classe. Esegue le funzionalita' di prima inizializzazione della classe.
		@param configI Puntatore alle informazioni di configurazione NTM
		@param paramsI Puntatore alla struttura selezioni utente
	*/
	ReportFormatter(AdamsCompleteConfig & configI, QueryParams & paramsI, TimeOut * tmout = 0);
	~ReportFormatter();
	/**
	Esegue l'inizializzazione della classe. Imposta le principali referenze esterne.
		@param directTreeI BtreeMap con i dati dell'analysis in chiave diretta (zero = no dati)
		@param inverseTreeI BtreeMap con i dati dell'analysis in chiave inversa (zero = no dati)
		@param nodeIndexI Indice nodo (FEP) referenziato nel report (-1 = network)
	*/
	bool init(BtreeMap  * directTreeI = 0, BtreeMap * inverseTreeI = 0, int nodeIndexI = -1);
	/**
	Esegue l'inizializzazione finale della classe. Imposta le principali referenze esterne.
	Overload del metodo precedente utilizzato specificatamente nella gestione delle analysis di tipo network.
		@param directTreeI BtreeMap con i dati dell'analysis in chiave diretta (zero = no dati)
		@param inverseTreeI BtreeMap con i dati dell'analysis in chiave inversa (zero = no dati)
	*/
	bool init(BTreeNetworkRT  * directTreeI, BTreeNetworkRT * inverseTreeI);
	/**
	Imposta il nome del file di output. Questo viene utilizzato solo in caso di OutputMedia == LocalFile.
	Se questo metodo non viene utilizzato verra' assegnato un nome di default (alla directory tmp)
		@param name Nome asseganto al file
	*/
	inline void setFileName(const QString & name) { mediaName = name; }
	/**
	Imposta il media di output su cui verra' prodotto il report.
		@param media Tipo media
		@param name Nome asseganto al file di output se il media e' un file
	*/
	inline bool setOutputMedia(OutputMedia media, const char * name = 0) { myMedia = media; if (name) setFileName(name); return initOutputMedia(); }
	/**
	Ritorna il media di output su cui verra' prodotto il report.
	*/
	inline QString getMediaName() { return mediaName; }
	/**
	Ritorna il tipo di media di output su cui verra' prodotto il report.
	*/
	inline OutputMedia getMediaType() { return myMedia; }
	/**
	Ritorna lo stato della istanza.
	*/
	inline FormatterStatus getStatus() { return myStatus; }
	/**
	Elabora il report
	*/
	void exec();
	/**
	Invia una stringa sul tipo di media selezionato. Le stringhe sono automaticamente terminate in base al tipo di media.
		@param output Stringa da inviare in output
	*/
	void send(const QString & line, bool terminate = true);
	/**
	Chiude il canale di output.
	*/
	void closeMedia();
	/**
	Imposta il la referenza al dizionario help/descrizioni
		@param diz Dizionario help
	*/
	inline void setHelperDict(HelpDictionary * diz) { helper = diz; }


protected:
	/** Questa classe viene utilizzata internamente al ReportFormatter per gestire le informazioni dei totalizzatori tra i vari metodi
	  * private della classe.
	  @short Gestione dati totalizzatori (USO INTERNO)
	  */
	class TotalData {
	public:
		TotalData() : triggered(false), rsoMap(0) { reset(); }
		~TotalData() { for (int i=0; i<TOTALS_POOL_SIZE; i++) if (pool[i]) delete pool[i];
			       for (int i=0; i<TOTALS_POOL_SIZE; i++) if (percentCross[i]) delete percentCross[i];
			       if (rsoMap) delete [] rsoMap;
		             }

		inline void check(const QString & v) { if (v != store) { triggered = true; store = v; } }
		inline void check(long lv) { QString v(QString::number(lv)); if (v != store) { triggered = true; store = v; } }
		inline void check(double dv) { QString v(QString::number(dv)); if (v != store) { triggered = true; store = v; } }

		inline void update(double v = 0, int idx = 0) { if (pool[idx] == 0) { pool[idx] = new double; *pool[idx] = 0; } *pool[idx] += v; }
		inline void updateReference(double v = 0, int idx = 0) { if (percentCross[idx] == 0) { percentCross[idx] = new double; *percentCross[idx] = 0; } *percentCross[idx] += v; }
		inline void reset() { memset(&pool, 0, sizeof(double *)*TOTALS_POOL_SIZE); memset(&percentCross, 0, sizeof(double *)*TOTALS_POOL_SIZE); triggered = false; rowCount = 0; }
		inline void setRsoMapTo(int rsoId, int idx) { if (rsoMap==0) initRsoMap(); rsoMap[idx] = rsoId; }
		inline int getRsoColumn(int rsoId) { for (int i=0; i<TOTALS_POOL_SIZE; i++) if (rsoMap[i]==rsoId) return i; return 0; }

		ReportSchemaObj * rso;
		double * pool[TOTALS_POOL_SIZE];
		double * percentCross[TOTALS_POOL_SIZE];
		int * rsoMap;
		QString store;
		bool triggered;
		int rowCount;
		AdamsBasic * sHndl;
	private:
		inline void initRsoMap() { rsoMap = new int [TOTALS_POOL_SIZE]; for (int i=0; i<TOTALS_POOL_SIZE; i++) rsoMap[i]=0; }
	};

	/** Questa classe viene utilizzata internamente al ReportFormatter per gestire la manipolazione delle scripts sulle righe
	  * del corpo.
	  @short Gestione scripts righe (USO INTERNO)
	  */
	class BodyScripts {
	public:
		BodyScripts() : rso(0), sHndl(0) { for (int i = 0; i < CNT_NUM; i++) varsIndex[i] = -1;
		                                   for (int i = 0; i < CNT_NUM; i++) tagsIndex[i] = -1; }

		ReportSchemaObj * rso;
		AdamsBasic * sHndl;
		int varsIndex[CNT_NUM];
		int tagsIndex[CNT_NUM];
	};

private:
	AdamsCompleteConfig & config;
	QueryParams & params;
	FormatterStatus myStatus;
	OutputMedia myMedia;
	BtreeMap  * directTree;
	BtreeMap * inverseTree;
	int nodeIndex;
	QString mediaName;
	HttpImpl * http;
	QTextStream stream;
	ReportSchema * schema;
	Analysis * analysis;
	Counters * kit;
	int indentLevel;
	NodeConfigHandler nodeConfigHandler;
	bool doBodyHdr;
	DefineRelation defineRelation;
	int relationSize;
	int numDblCounters;
	int numIntCounters;
	ParameterList * countersData;
	int * keyLengths;
	QString * keyStore;
	QString * cellStore;
	QString currentKey;
	TotalData * subTotals;
	TotalData grandTotal;
	HelpDictionary * helper;
	bool * keyHelperPresent;
	bool * hexKey;
	bool needHtmlSetOnRow;
	int plainHdrLineSize;
	int plainKeyFldSize;
	int plainCntFldSize;
	char plainHdrSepChar;
	char plainBdySepChar;
	char plainLineChar;
	char altPlainLineChar;
	AdamsBasic ** keyScript;
	PluginImpl ** keyPlugin;
	int forceBodyHdr;
	bool * hiddenKey;
	bool ghostRelation;
	PluginBase * pluginBase;
	PluginImpl * pluginImpl;
	ReportHandlerAPIData reportHandlerAPIData;
	bool fmt3d;
	bool triggeredChangeOnFirstKey;
	TimeOut * watchDog;
	bool noGrand;
	BodyScripts * bdyScripts;
	bool firstHdrDrawed;
	bool fmtPlainBody;
	bool * keyAlwaysShow;
	DEHandlerWorkerAPIData pld;
	ConfigParser cnfparser;

		/** Tipologie di messaggio per debugPluginError() */
	enum pluginMsgType {
		UnknownType,
		WrongType,
		numBuilderMsgType
	};

	/**
	Inposta la spaziatura in ragione del livello di indentazione.
	*/
	inline QString indent() { QString s; s.fill(' ',indentLevel*INDENT_CHARS); return s; }

	/**
	Esegue il setup del media di output come selzionato dall'utente.
	Il valore di ritorno false indica l'impossibilta' di eseguire l'operazione.
	*/
	bool initOutputMedia();
	/**
	Emette i tag html do chiusura documento
	*/
	void htmlEndSequence();
	/**
	Emette le righe di intestazione standard (<head>) del documento html ed inizializza la sequenza del corpo.
	*/
	void htmlStartSequence(const ReportSchemaObj & o);
	/**
	Emette la sequenza di apertura di una tabella HTML
		@param border Flag abilitazione bordo (fisso a 1 pixel)
		@param width Ampiezza percentuale
		@param padding Marginatura cella in pixel
		@param spacing Spaziatura inter-cella in pixel
		@param other Stringa aggiuntiva passata direttamente nella sequenza di inizializzazione
	*/
	void openTable(bool border = false, int width = 100, int padding = 2, int spacing = 0,  const char * other = 0);
	/**
	Emette la sequenza di chiusura di una Tabella
	*/
	void closeTable();
	/**
	Emette la sequenza di chiusura di una riga di tabella.
	*/
	void closeRow();
	/**
	Emette la sequenza HTML per lo start di una nuova riga in tabella.
	*/
	void openRow();
	/**
	Emette la sequenza di chiusura di una cella
	*/
	void closeCell(const ReportSchemaObj * o);
	/**
	Ritorna la sequenza di chiusura di una cella (solo tag singoli)
	*/
	QString closeCellHtml(const ReportSchemaObj * o);
	/**
	Produce l'output necessario all'interpretazione degli eventi header programmati dall'utente per il report.
		@param o Ogetto/evento da trattare
	*/
	void handleHeaderEvents(const ReportSchemaObj &  o);
	/**
	Emette la sequenza di apertura di una cella. I parametri di visualizzazione soono gestiti da configurazione.
		@param o Oggetto contenuto da cui prelevare la modalita' di rappresentazione
		@param txt Testo cella (ozionale)
		@param closeit Richiesta anche la sequenza di chiusura cella
		@param header La cella e' di intestazione
	*/
	void openCell(const ReportSchemaObj * o = 0, const QString & txt = 0, bool closeit = true, bool header = false);
	/**
	Valuta la Url utente per determinare se e' riferita ad una immagine
		@param utente URL utente
	*/
	bool checkForImageUrl(const QString & url);
	/**
	Ritorna la sequenza HTML necessaria a trattare una URL.
		@param utente URL utente
	*/
	QString placeUrl(const QString & url);
	/**
	Usato per espandere le descrizioni associate ad OptionList.
		@param utente Indice selezione utente
	*/
	QString expandRestrictionDescription(QueryParams::Rest * r);
	/**
	Gestione degli eventi di break sulle righe del report
	*/
	void handleBreakerEvents(const ReportSchemaObj &  o);
	/**
	In questo metodo sono inserite le funzionalita' per la realizzazione delle righe (corpo) del report.
	*/
	void bodyLoop(BtreeMap  * bTree);
	/**
	Gestore stampa header di riga (corpo)
	*/
	void handleBodyHdr();
	/**
	Gestore riga corpo
	*/
	void handleBodyRow();
	/**
	Estrae il valore di un contatore dal kit corrente
		@param idx Indice del contatore
		@param cntRow Usa questa struttura contatori per l'estrazione del valore
	*/
	double getCounterValue(int idx, ParameterList * cntRow = 0);
	/**
	Ritorna il tipo del contatore in argomento
		@param idx Indice contatore
	*/
	inline int getCounterType(int idx) { if (idx > 0 && idx <= MAX_INT_COUNTERS) return INT_TYPE;
					     else if (idx > MAX_INT_COUNTERS && idx <= (MAX_INT_COUNTERS+MAX_DBL_COUNTERS)) return DOUBLE_TYPE;
					     else return -1; }
	/** Deriva l'indice del contatore a partire dal suo tag
	    @param cntTag Tag (nome) del contatore
	*/
	int getCounterIndex(const QString & cntTag, int & tagi);
	/**
	Gestore riga (sub)totalizzatore.
	*/
	void handleSubTotalRow(TotalData & td);
	/**
	Pre-valutazione delle celle di riga e triggering dei subtotalizzatori
	*/
	void checkRowTriggers();
	/**
	Gestione degli eventi sul piede del report
	*/
	void handleFooterEvents(const ReportSchemaObj &  o);
	/**
	Segnala all'utente il report nullo
	*/
	void adviceForNullReport();
	/**
	Crea una message bos sul report
	*/
	void adviceUser(const QString & mesg);
	/**
	Richiama/interfaccia le funzionalita' di scripting nella gestione delle celle chiave
	*/
	bool callBasic_TE(const QString & keyPart, const QString & currentKey, AdamsBasic * nb, QString & result);
	/**
	Richiama/interfaccia le funzionalita' di scripting nella gestione delle diciture celle subtotali
	*/
	bool callBasic_TOT(const TotalData &  o, int idKey, QString & result);
	/**
	Richiama/interfaccia le funzionalita' di scripting nella gestione delle celle corpo
	*/
	bool callBasic_BODY(int bdyidx, ParameterList * cntRow, QString & result, int ndec);
	/**
	Setup delle parti dipendenti dal formato relazione
	*/
	void setupRelationParts(int relationSize);
	/**
	Gestione caricamento tipologia plugin errata
	*/
	void debugPluginError(const QString & caller, const QString & requested, pluginMsgType type, PluginImpl * pli);
	/** Questo metodo tenta di travare una descrizione per quegli elementi che non hanno associato uno specifico plugin
	    di help per le descrizioni, ma invece hannoi associata una OptionList interna alla configuirazione NTM */
	QString checkInternalHelp(const QString & tag, const QString & id);
	/** Crea un istanza del plugin associato al traffic element passato in argomento */
	PluginImpl * getHandlerPluginInstance(DataElement * te);

	QString tempFileName(const QString & prefix);

	inline void expand_plugin_dir ( QString & plug_dir ) {
		if ( plug_dir.mid ( 0, 1 ) == "~" ) {				// home relative path
			QString homedir = ::getenv ( "HOME" );
			if ( !homedir.isEmpty() ) {
				plug_dir.replace ( 0, 1, homedir );
			}
		}
		else if ( plug_dir.mid ( 0, 1 ) != "/" ) {			// not an absolute path
			plug_dir = AdamsServer::getInstallPath() % "/" % plug_dir;
		}

	}

};

#endif

