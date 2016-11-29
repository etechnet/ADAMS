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

#ifndef DATACOLLECTOR_H
#define DATACOLLECTOR_H

#include <Qt/qthread.h>
#include <Qt/qstring.h>
#include <Qt/qhash.h>

#include <adams_limits.h>
#include <configuretypedefs.h>
#include <storeconf.h>
#include <definerelation.h>
#include <converter.h>
#include <drimagehandler.h>
#include <simplevector.h>
#include <buildoper.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <nodo.h>

#ifndef _AHASHDICT
#define _AHASHDICT
typedef QHash<QString, Nodo *> HashDict;			// used as local type (fast) binary tree
typedef QHashIterator<QString, Nodo *> HashDictIterator;
#endif

typedef QHash<int, PluginBase *> PluginFactory;
typedef QHash<int, PluginImpl *> PluginImplFactory;

#define	MAX_CONCURRENT			7		// max concurrent builder threads
#define INDEXED_PREALLOC_SIZE		2000
#define SEQUENTIAL_PREALLOC_SIZE	10000


class DataCollector : public QThread {
public:

	enum dcPluginMsgType {
	        UnknownType,
	        WrongType,
	        numDcPluginMsgType
	};

	DataCollector();
	~DataCollector();

	inline HashDict * getBtree() {
		return Btreep;
	}

	inline void setBtree ( HashDict * BtreeP ) {
		Btreep = BtreeP;
	}

	inline long getAllocationPolicySize() {
		return preallocPolicySize;
	}

	inline void setProgressTeller ( int * paddr ) {
		localProgress = paddr;
	}

	void startExec ( QString Day, int daycount, int totalDays );

	inline bool completionStatus() {
		return m_ret;
	}

	inline bool isStarted() {
		return started;
	}
	virtual void run();

private:
	PluginImpl * dataRecordReaderPlugin;
	PluginImpl * countersPlugin;
	HashDict * Btreep;
	SortedRest sortedFilters;
	OperSVector opers;
	OperDirect opersDirect;
	converter conv;
	QString m_day;
	int m_daycount;
	int m_totalDays;
	bool m_ret;
	bool started;
	int * localProgress;
	int cntKitId;
	Analysis * analysis;
	Counters * counters;
	char afBuf[MAX_KEY_LENGTH];
	QString qafBuf;
	NodoFastMem * nfmPtr;
	long preallocPolicySize;
	PluginImplFactory pluginImplFactory;		// a proxy factory for traffic elements plugins instances
	QString pluginHome;

	int linkTable ( int elemid );
	bool initOper ( QueryParams::Rest * r, BuildOper & bo );
	bool initRelation ( DefineRelation * dr );
	bool callBasic_InitPhase ( QueryParams::Rest * r, DataElement * te, Script * s );
	bool callBasic_BuildKey ( char *& cdrKey, DRImageHandler & CDRr, RelationComponent & rParts );
	PluginImpl * getHandlerPluginInstance ( DataElement * te );
	AdamsBasic * getAdamsBasicInstance ( Script * s );
	bool BuildBtree ( QString Day,int daycount, int totalDays );

	inline bool evalKey ( DRImageHandler & CDRr, RelationComponent * rParts, char * cdrKey, int numRelComp ) {
		int fld_offset;
		long valshift;
		int p;
		char filler = ' ';
		DEHandlerWorkerAPIData pld;

		for ( int iElem = 0; iElem < numRelComp; iElem++ ) {
			if ( rParts[iElem].scriptHandler ) {
				if ( callBasic_BuildKey ( cdrKey, CDRr, rParts[iElem] ) == 0 ) {
					return false;
				}
				cdrKey += rParts[iElem].length;
				continue;
			}
			if ( rParts[iElem].pluginHandler ) {
				pld.operation = teOp_KeyBuilding;
				pld.dayIndex = m_daycount;
				pld.CDRr = &CDRr;
				pld.offset = rParts[iElem].CDRLink;
				pld.convert = &conv;
				pld.configuredKeyLenght = rParts[iElem].length;
				pld.inKeyValue = cdrKey;
				rParts[iElem].pluginHandler->pluginWorker ( ( void * ) &pld );
				cdrKey += rParts[iElem].length;
				continue;
			}
			fld_offset = rParts[iElem].CDRLink;
			if ( fld_offset < 0 )
				continue;
			switch ( StoreConf::config()->drInterface->fastget ( fld_offset )->data.fieldtype ) {
				case DRF_unsigned_int:
				case DRF_int:
					if ( rParts[iElem].hasShifter ) {
						valshift = applyShift ( CDRr.field ( fld_offset ).fvUnsignedInt, rParts[iElem].shiftRanges, rParts[iElem].hasShifter );
						if ( valshift < 0 )
							return false;
						conv.setLong ( valshift, rParts[iElem].length, cdrKey );
						cdrKey += rParts[iElem].length;
					}
					else {
						conv.setLong ( CDRr.field ( fld_offset ).fvUnsignedInt, rParts[iElem].length, cdrKey );
						cdrKey += rParts[iElem].length;
					}
					break;
				case DRF_unsigned_char:
				case DRF_char:
					if ( StoreConf::config()->drInterface->fastget ( fld_offset )->data.is_array ) {
						qstrncpy ( cdrKey, ( const char * ) CDRr.field ( fld_offset ).fvArrayOfChar, rParts[iElem].length+1 );
						if ( *cdrKey == '\0' )
							qstrncpy ( cdrKey, "N/A", rParts[iElem].length+1 );
						for ( p = qstrlen ( cdrKey ); p < rParts[iElem].length; p++ )
							cdrKey[p] = filler;
						cdrKey += rParts[iElem].length;
					}
					else {
						if ( rParts[iElem].hasShifter ) {
							valshift = applyShift ( int ( CDRr.field ( fld_offset ).fvUnsignedChar ), rParts[iElem].shiftRanges, rParts[iElem].hasShifter );
							if ( valshift < 0 )
								return false;
							conv.setLong ( valshift, rParts[iElem].length, cdrKey );
							cdrKey += rParts[iElem].length;
						}
						else {
							conv.setLong ( int ( CDRr.field ( fld_offset ).fvUnsignedChar ), rParts[iElem].length, cdrKey );
							cdrKey += rParts[iElem].length;
						}
					}
					break;
				case DRF_BCD:
				case DRF_String:
					qstrncpy ( cdrKey, ( const char * ) CDRr.field ( fld_offset ).fvArrayOfChar, rParts[iElem].length+1 );
					if ( *cdrKey == '\0' )
						qstrncpy ( cdrKey, "N/A", rParts[iElem].length+1 );
					for ( p = qstrlen ( cdrKey ); p < rParts[iElem].length; p++ )
						cdrKey[p] = filler;
					cdrKey += rParts[iElem].length;
					break;
				case DRF_CString:
					qstrncpy ( cdrKey, ( const char * ) CDRr.field ( fld_offset ).fvArrayOfChar, rParts[iElem].length );
					if ( *cdrKey == '\0' )
						qstrncpy ( cdrKey, "N/A", rParts[iElem].length );
					for ( p = qstrlen ( cdrKey ); p < rParts[iElem].length; p++ )
						cdrKey[p] = filler;
					cdrKey += rParts[iElem].length;
					break;
				case DRF_unsigned_short:
				case DRF_short:
					if ( rParts[iElem].hasShifter ) {
						valshift = applyShift ( CDRr.field ( fld_offset ).fvUnsignedShort, rParts[iElem].shiftRanges, rParts[iElem].hasShifter );
						if ( valshift < 0 )
							return false;
						conv.setLong ( valshift, rParts[iElem].length, cdrKey );
						cdrKey += rParts[iElem].length;
					}
					else {
						conv.setLong ( CDRr.field ( fld_offset ).fvUnsignedShort, rParts[iElem].length, cdrKey );
						cdrKey += rParts[iElem].length;
					}
					break;
				case DRF_unsigned_long:
				case DRF_long:
					if ( rParts[iElem].hasShifter ) {
						valshift = applyShift ( CDRr.field ( fld_offset ).fvUnsignedLong, rParts[iElem].shiftRanges, rParts[iElem].hasShifter );
						if ( valshift < 0 )
							return false;
						conv.setLong ( valshift, rParts[iElem].length, cdrKey );
						cdrKey += rParts[iElem].length;
					}
					else {
						conv.setLong ( CDRr.field ( fld_offset ).fvUnsignedLong, rParts[iElem].length, cdrKey );
						cdrKey += rParts[iElem].length;
					}
					break;
				case DRF_float:
					conv.setDouble ( CDRr.field ( fld_offset ).fvFloat, rParts[iElem].length, cdrKey );
					cdrKey += rParts[iElem].length;
					break;
				case DRF_double:
					conv.setDouble ( CDRr.field ( fld_offset ).fvDouble, rParts[iElem].length, cdrKey );
					cdrKey += rParts[iElem].length;
					break;
			}
		}
		*cdrKey = '\0';
		return true;
	}

	/** Questo metodo viene invocato per effettuare l'applicazione di tutti i filtri non-indicizzati impostati dall'utente
	  * ad ogni ciclo di lettura dei dati dei cartellini.
	  *@return true	dati validi
	  *@return false cartellino respinto (dati incongruenti con le selezioni utente)
	  */
	inline bool applyFilter ( DRImageHandler * CDRr, int daycount );
	/** Metodo specializzato per il treffico voce. Effettua la discriminazione sul traffico di raccordo ove
	  * richiesto dalle selezioni utente
	  */
	inline long applyShift ( long val, SHIFTRANGE * shifter, int rangecount ) {
		for ( int i = 0; i < rangecount; i++ ) {
			if ( val >= shifter[i].from && val <= shifter[i].to ) {
				return shifter[i].shiftVal;
			}
		}
		return val;
	}
	
	QString pluginDescription(int pl_id);	

	/** Questo metodo emette un messaggio di errore appropriato relativamente ad errori su plugin */
	void debugPluginError ( const QString & caller, const QString & requested, dcPluginMsgType type, PluginImpl * pli = 0 );
	/** Esegue l'attach del(dei) plugin richiesti per l'elaborazione */
	bool attachPlugins();
	/** Questo metodo effettua l'aggiornamento del toolkit di contatori standard della matrice di traffico */
	void UpdateCounters ( QueryParams * counterlist, DRImageHandler * CDRr, int dayindex );
	/** Questo metodo viene utilizzato per determinare il successivo numero primo rispetto
	    alla dimensione fornita in argomento */
	long long findNextPrime ( long long ptst );
	/** Questo metodo viene utilizzato per determinare se l'intero fornito in argomento e' un numero primo */
	bool isPrime ( long long ptst );

};

#endif
