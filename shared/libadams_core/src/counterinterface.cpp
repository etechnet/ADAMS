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

#include <counterinterface.h>
#include <applogger.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <configuretypedefs.h>
#include <stdlib.h>

typedef QHash<long, PluginBase *> CountersPluginFactory;
typedef QHash<long, PluginImpl *> CountersPluginImplFactory;

static CountersPluginFactory countersPluginFactory;			// a proxy factory for traffic elements plugins
static CountersPluginImplFactory countersPluginImplFactory;		// a proxy factory for traffic elements plugins instances
static CountersPluginImplFactory countersDiscardedPluginImplFactory;	// a proxy factory for traffic elements plugins instances

CounterInterface::CounterInterface() : QObject()
{
	Counterss = new QMultiHash<QString, Counters *>;
	CounterssNumber = 0;
	pluginHome = getenv ( "HOME" );
}

CounterInterface::~CounterInterface()
{
	if ( Counterss ) {
		clear();
		delete Counterss;
	}
}

/**
  * Inserisce una nuova eccezione nel hashtable
  * ed incrementa il numero di eccezioni.
**/
bool CounterInterface::add ( Counters * newCounters )
{
	Counterss->insert ( QString::number ( newCounters->data.idCounter ), newCounters );
	CounterssNumber++;
	return false;
}
/**
  * Rimouve una eccezione dal hashtable
  * ed decrementa il numero di eccezioni.
**/
bool CounterInterface::remove ( int CountersNum )
{
	Counters *scp = Counterss->find ( QString::number ( CountersNum ) ).value();
	if ( scp == ( Counters * ) 0 ) {
		return true;
	}
	Counterss->remove ( QString::number ( CountersNum ) );
	delete scp;
	CounterssNumber--;
	return false;
}

/**
  * Restituisce un specifico elemento.
**/
Counters * CounterInterface::get ( int CountersNum )
{
	return Counterss->find ( QString::number ( CountersNum ) ).value();
}

bool CounterInterface::getConfiguredCounters ( Counters * kit, int & n_int, int & n_dbl )
{
	n_dbl = n_int = 0;

	if ( !kit )
		return false;

	for ( int k = 0; k < CNT_NUM; k++ ) {
		if ( qstrlen ( kit->data.counterKit[k].tag ) > 0 ) {
			switch ( getCounterType ( kit->data.counterKit[k].counterIndex ) ) {
				case INT_TYPE:
					++n_int;
					break;
				case DOUBLE_TYPE:
					++n_dbl;
					break;
				default:
					// error ??
					break;
			}
		}
	}
}

int CounterInterface::getConfiguredIntegerCounters ( Counters * kit )
{
	int n_int = 0;

	if ( !kit )
		return -1;

	for ( int k = 0; k < CNT_NUM; k++ ) {
		if ( qstrlen ( kit->data.counterKit[k].tag ) > 0 ) {
			switch ( getCounterType ( kit->data.counterKit[k].counterIndex ) ) {
				case INT_TYPE:
					++n_int;
					break;
				default:
					// error ??
					break;
			}
		}
	}

	return n_int;
}

int CounterInterface::getConfiguredDoubleCounters ( Counters * kit )
{
	int n_dbl = 0;

	if ( !kit )
		return -1;

	for ( int k = 0; k < CNT_NUM; k++ ) {
		if ( qstrlen ( kit->data.counterKit[k].tag ) > 0 ) {
			switch ( getCounterType ( kit->data.counterKit[k].counterIndex ) ) {
				case DOUBLE_TYPE:
					++n_dbl;
					break;
				default:
					// error ??
					break;
			}
		}
	}

	return n_dbl;
}


double CounterInterface::getCounterValue ( Counters * kit, int idx, Nodo * nodo, int n_int, int n_dbl )
{
	if ( !kit )
		return -1.0;

	if ( n_int == 0 )
		getConfiguredIntegerCounters ( kit );
	if ( n_dbl == 0 )
		getConfiguredDoubleCounters ( kit );

	ParameterList * cntRow = nodo->GetLista();

	switch ( getCounterType ( idx ) ) {
		case INT_TYPE:
			if ( idx > n_int ) {
				return -1.0;
			} else
				return ( double ) cntRow->int_counters[idx - 1];
		case DOUBLE_TYPE:
			idx -= MAX_INT_COUNTERS;
			if ( idx > n_dbl ) {
				return -1.0;
			} else
				return cntRow->dbl_counters[idx - 1];
		default:
			lout << "CounterInterface::getCounterValue : WARNING : Misconfigured counter index: " << idx << endl;
			return -1.0;
	}
}

/** Deriva l'indice del contatore sulla base del tag */

int CounterInterface::getCounterIndex ( Counters * kit, const QString & cntTag, int & tagi )
{
	if ( !kit )
		return -1;

	for ( int i = 0; i < CNT_NUM; i++ ) {
		if ( qstrlen ( kit->data.counterKit[i].tag ) == 0 )
			break;
		else if ( cntTag == kit->data.counterKit[i].tag ) {
			tagi = i;
			return kit->data.counterKit[i].counterIndex;
		}
	}

	return -1;
}

Counters::COUNTERKIT * CounterInterface::getCounterKitDataByIndex ( Counters * kit, int idx )
{
	if ( !kit )
		return 0;

	for ( int i = 0; i < CNT_NUM; i++ ) {
		if ( kit->data.counterKit[i].counterIndex == idx ) {
			return &kit->data.counterKit[i];
		}
	}

	return 0;
}

Counters::COUNTERKIT * CounterInterface::getCounterKitDataByOffset ( Counters * kit, int offset )
{
	if ( !kit )
		return 0;

	return &kit->data.counterKit[offset];
}

int CounterInterface::getCounterType ( int idx )
{
	if ( idx > 0 && idx <= MAX_INT_COUNTERS ) return INT_TYPE;
	else if ( idx > MAX_INT_COUNTERS && idx <= ( MAX_INT_COUNTERS + MAX_DBL_COUNTERS ) ) return DOUBLE_TYPE;
	else return -1;
}


bool CounterInterface::updateCounters ( AdamsCompleteConfig * ncc, QueryParams * params, Counters* kit, Nodo * nodo, DRImageHandler* CDRr, int n_int, int n_dbl )
{
	if ( !kit )
		return false;

// 	DataElementInterface * tei = ncc->dataElementInterface;

	if ( n_int == 0 )
		getConfiguredIntegerCounters ( kit );
	if ( n_dbl == 0 )
		getConfiguredDoubleCounters ( kit );

	if ( nodo->isUnallocated() )
		lout << "UNALLOCATED NODE" << endl;

	ParameterList* cntRow = nodo->GetLista();

	for ( int i = 0; i < CNT_NUM; i++ ) {
		// unconfigured
		if ( kit->data.counterKit[i].tag[0] == '\0' )
			continue;
		// unmanaged
		if ( kit->data.counterKit[i].counterType == CounterInterface::Unmanaged )
			continue;

		int idx = kit->data.counterKit[i].counterIndex;
// 		lout << "counterIndex=" << idx << " counterType=" << kit->data.counterKit[i].counterType << endl;

		switch ( kit->data.counterKit[i].counterType ) {
			case CounterInterface::SimpleCounter:
			case CounterInterface::SimpleAverageCounter:
				// conditional increment
				if ( kit->data.counterKit[i].triggerField != 0 &&
				                kit->data.counterKit[i].triggerValue != 0
				   ) {
					switch ( getCounterType ( idx ) ) {
						case INT_TYPE:
							if ( kit->data.counterKit[i].triggerValue ==
							                getCDRIntValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField )
							   ) {
								incCounterVal ( cntRow, idx );
							}
							break;
						case DOUBLE_TYPE:
							if ( ( double ) kit->data.counterKit[i].triggerValue ==
							                getCDRDoubleValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField )
							   ) {
								incCounterVal ( cntRow, idx );
							}
							break;
					}
				} else
					// simple
					incCounterVal ( cntRow, idx );
				break;
			case CounterInterface::SumCounter:
			case CounterInterface::SumAverageCounter:
				// conditional sum
				if ( kit->data.counterKit[i].triggerField != 0 &&
				                kit->data.counterKit[i].triggerValue != 0
				   ) {
					switch ( getCounterType ( idx ) ) {
						case INT_TYPE: {
								unsigned long v = getCDRIntValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField );
								if ( kit->data.counterKit[i].triggerValue == v ) {
									sumCounterVal ( cntRow, idx, v );
								}
							}
							break;
						case DOUBLE_TYPE: {
// 								rlout << "cdrval=" << getCDRDoubleValue(tei, CDRr, kit->data.counterKit[i].triggerField) << endl;
								double v = getCDRDoubleValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField );
								if ( ( double ) kit->data.counterKit[i].triggerValue == v ) {
									sumCounterVal ( cntRow, idx, v );
								}
							}
							break;
					}
				} else
					// simple
					switch ( getCounterType ( idx ) ) {
						case INT_TYPE:
							/*							rlout << "te:" << ncc->dataElementInterface->get(kit->data.counterKit[i].triggerField)->data.shortDescription << endl;
														rlout << "cdrval=" << getCDRIntValue(ncc, params, CDRr, kit->data.counterKit[i].triggerField) << endl;*/
							sumCounterVal ( cntRow, idx,
							                getCDRIntValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField ) );
							break;
						case DOUBLE_TYPE:
// 							rlout << "cdrval=" << getCDRDoubleValue(tei, CDRr, kit->data.counterKit[i].triggerField) << endl;
							sumCounterVal ( cntRow, idx,
							                getCDRDoubleValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField ) );
							break;
					}
				break;
			case CounterInterface::FieldLink:
				// set value
				if ( kit->data.counterKit[i].triggerField != 0 &&
				                kit->data.counterKit[i].triggerValue != 0
				   ) {
					switch ( getCounterType ( idx ) ) {
						case INT_TYPE: {
								unsigned long v = getCDRIntValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField );
								if ( kit->data.counterKit[i].triggerValue == v ) {
									setCounterVal ( cntRow, idx, v );
								}
							}
							break;
						case DOUBLE_TYPE: {
								double v = getCDRDoubleValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField );
								if ( ( double ) kit->data.counterKit[i].triggerValue == v ) {
									setCounterVal ( cntRow, idx, v );
								}
							}
							break;
					}
				} else
					// simple
					switch ( getCounterType ( idx ) ) {
						case INT_TYPE:
							setCounterVal ( cntRow, idx,
							                getCDRIntValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField ) );
							break;
						case DOUBLE_TYPE:
							setCounterVal ( cntRow, idx,
							                getCDRDoubleValue ( ncc, params, CDRr, kit->data.counterKit[i].triggerField ) );
							break;
					}
				break;
		}

		continue;
	}

	return true;
}

bool CounterInterface::closeCounterUpdate ( AdamsCompleteConfig * ncc, QueryParams * params, Counters* kit, Nodo * nodo, int n_int, int n_dbl )
{
	if ( !kit )
		return false;

	if ( n_int == 0 )
		getConfiguredIntegerCounters ( kit );
	if ( n_dbl == 0 )
		getConfiguredDoubleCounters ( kit );

	if ( nodo->isUnallocated() )
		lout << "UNALLOCATED NODE" << endl;

	ParameterList* cntRow = nodo->GetLista();

	for ( int i = 0; i < CNT_NUM; i++ ) {
		// unconfigured
		if ( kit->data.counterKit[i].tag[0] == '\0' )
			continue;
		// unmanaged
		if ( kit->data.counterKit[i].counterType == CounterInterface::Unmanaged )
			continue;

		int idx = kit->data.counterKit[i].counterIndex;
// 		lout << "counterIndex=" << idx << " counterType=" << kit->data.counterKit[i].counterType << endl;

		switch ( kit->data.counterKit[i].counterType ) {
			case CounterInterface::SimpleAverageCounter:
			case CounterInterface::SumAverageCounter:
				// average:
				// triggerValue is ignored.
				// triggerField is MANDATORY. In absence of it counter is zeroed.
				if ( kit->data.counterKit[i].percentOf != 0 ) {
					double v = getCounterValue ( kit, kit->data.counterKit[i].triggerCounter, nodo, n_int, n_dbl );
					double d = getCounterValue ( kit, kit->data.counterKit[i].percentOf, nodo, n_int, n_dbl );
					if ( v < 0.0 ) lout << "Misconfigured average numerator:"
						                    << kit->data.counterKit[i].triggerCounter
						                    << " on counter:"
						                    << idx
						                    << endl;
					if ( d < 0.0 ) lout << "Misconfigured average denominator"
						                    << kit->data.counterKit[i].percentOf
						                    << " on counter:"
						                    << idx
						                    << endl;

					averageCounterVal ( cntRow, idx, v, d );

					/*lout1 << "n=" << kit->data.counterKit[i].triggerCounter
					     << " d=" << kit->data.counterKit[i].percentOf
					     << " -> " << v
					     << "/" << d
					     << " = " << getCounterValue(kit, idx, nodo, n_int, n_dbl)
					     << endl;*/
				} else
					// invalid conf: zero
					switch ( getCounterType ( idx ) ) {
						case INT_TYPE:
							cntRow->int_counters[idx - 1] = 0;
							break;
						case DOUBLE_TYPE:
							idx -= MAX_INT_COUNTERS;
							cntRow->dbl_counters[idx - 1] = 0.0;
							break;
					}
				break;
		}

		continue;
	}

	return true;
}


PluginImpl * CounterInterface::getHandlerPluginInstance ( AdamsCompleteConfig * ncc, QueryParams * params, DataElement * te )
{
	PluginImpl * plugin;

	PluginRegistry * p = ncc->pluginRegistryInterface->get ( te->data.idPlugin );
	if ( p == 0 ) {
		lout << "CounterInterface::getHandlerPluginInstance : Cannot get plugin #" <<
		     te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
		return 0;
	}
	// first check if there is an instance already setup ?
	plugin = countersDiscardedPluginImplFactory[te->data.idElement];
	if ( plugin ) {
		return 0;
	}
	plugin = countersPluginImplFactory[te->data.idElement];
	if ( plugin ) {
		return plugin;
	}
	// now check if a pluginbase already exists for this te
	PluginBase * plBase = countersPluginFactory[te->data.idElement];
	if ( plBase == 0 ) {		// new one
		plBase = new PluginBase ( pluginHome );
		if ( plBase->registerPlugin ( p->data.pluginName, ncc->drInterface->globalOpt()->data.glbDefaultPluginPath, true ) ) {
			lout << "CounterInterface::getHandlerPluginInstance : Failed to register plugin #" <<
			     te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
			return 0;			// plugin required but failed to attach
		}

		countersPluginFactory.insert ( te->data.idElement, plBase );
	}
	plugin = plBase->getInstance();
	if ( !plugin->verifyType ( Adams_ElementHandler ) ) {
		lout << "CounterInterface::getHandlerPluginInstance : Failed to register plugin #" <<
		     te->data.idPlugin << " needed from Element #" << te->data.idElement << ": WRONG TYPE!" << endl;
		delete plugin;
		return 0;
	}
	plugin->pluginSetupConfig ( ncc, params );
	DEHandlerWorkerAPIData pld;
	// call plugin to setup restriction (eventually)
	if ( !plugin->startup ( ( void * ) &pld ) ) {
		lout << "CounterInterface::getHandlerPluginInstance : Cannot startup plugin #" <<
		     te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
		return 0;
	}
	if ( !pld.useFor[teOp_FieldEvaluation] ) {
		countersDiscardedPluginImplFactory.insert ( te->data.idElement, plugin );
		return 0;
	}

	countersPluginImplFactory.insert ( te->data.idElement, plugin );
	lout1 << "CounterInterface::getHandlerPluginInstance: loaded plugin for: " << te->data.shortDescription << endl;
	return plugin;
}


unsigned long CounterInterface::getCDRIntValue ( AdamsCompleteConfig * ncc, QueryParams * params, DRImageHandler* CDRr, int te_offset )
{
	DataElement * te = ncc->dataElementInterface->get ( te_offset );
	if ( !te )
		return 0;

	if ( te->data.idPlugin > 0 ) {
		// handled by plugin, so recall it
// 		lout1 << "CounterInterface::getCDRIntValue: loading plugin for: " << te->data.shortDescription << endl;
		PluginImpl * plugin = getHandlerPluginInstance ( ncc, params, te );

		if ( plugin ) {
			DEHandlerWorkerAPIData pld;

			pld.element = te;
			pld.operation = teOp_FieldEvaluation;
			pld.CDRr = CDRr;
			plugin->pluginWorker ( ( void * ) &pld );
			if ( pld.status == teStatus_Error ) {
				lout << "CounterInterface::getCDRIntValue : Plugin execution (worker) was unsucessfull for element #" <<  te->data.idElement << endl;
				return 0;
			}
			if ( pld.status == teStatus_UnmanagedOperation ) // unless plugin unamanged init phase we terminate here
				return 0;
			if ( pld.valueType == teValue_Integer ) {
				return pld.integerValue;
			} else
				return 0;
		}
	}

	int fld_offset = te->data.idDRLink;
	if ( fld_offset < 0 )
		return 0;

	DRField * Campo = CDRr->getDRInterface()->fastget ( fld_offset );

	switch ( Campo->data.fieldtype ) {
		case DRF_unsigned_int:
		case DRF_int:
			return ( unsigned long ) CDRr->field ( fld_offset ).fvUnsignedInt;
		case DRF_unsigned_long:
		case DRF_long:
			return ( unsigned long ) CDRr->field ( fld_offset ).fvUnsignedLong;
		case DRF_unsigned_char:
		case DRF_char:
		case DRF_String:
		case DRF_CString:
			if ( Campo->data.is_array )
				return ( unsigned long ) 0;
			else
				return ( unsigned long ) CDRr->field ( fld_offset ).fvUnsignedChar;
		case DRF_BCD:
			return ( unsigned long ) 0;
		case DRF_unsigned_short:
		case DRF_short:
			return ( unsigned long ) CDRr->field ( fld_offset ).fvUnsignedShort;
		case DRF_float:
			return ( unsigned long ) CDRr->field ( fld_offset ).fvFloat;
		case DRF_double:
			return ( unsigned long ) CDRr->field ( fld_offset ).fvDouble;
	}
	return ( unsigned long ) 0;
}

double CounterInterface::getCDRDoubleValue ( AdamsCompleteConfig * ncc, QueryParams * params,  DRImageHandler* CDRr, int te_offset )
{
	DataElement * te = ncc->dataElementInterface->get ( te_offset );
	if ( !te )
		return 0.0;

	if ( te->data.idPlugin > 0 ) {
		// handled by plugin, so recall it
// 		lout1 << "CounterInterface::getCDRIntValue: loading plugin for: " << te->data.shortDescription << endl;
		PluginImpl * plugin = getHandlerPluginInstance ( ncc, params, te );

		if ( plugin ) {
			DEHandlerWorkerAPIData pld;

			pld.element = te;
			pld.operation = teOp_FieldEvaluation;
			pld.CDRr = CDRr;
			plugin->pluginWorker ( ( void * ) &pld );
			if ( pld.status == teStatus_Error ) {
				lout << "CounterInterface::getCDRIntValue : Plugin execution (worker) was unsucessfull for element #" <<  te->data.idElement << endl;
				return 0.0;
			}
			if ( pld.status == teStatus_UnmanagedOperation ) // unless plugin unamanged init phase we terminate here
				return 0.0;
			if ( pld.valueType == teValue_Double )
				return pld.doubleValue;
			else
				return 0.0;
		}
	}

	int fld_offset = te->data.idDRLink;
	if ( fld_offset < 0 )
		return 0.0;

	DRField * Campo = CDRr->getDRInterface()->fastget ( fld_offset );

	switch ( Campo->data.fieldtype ) {
		case DRF_unsigned_int:
		case DRF_int:
			return ( double ) CDRr->field ( fld_offset ).fvUnsignedInt;
		case DRF_unsigned_long:
		case DRF_long:
			return ( double ) CDRr->field ( fld_offset ).fvUnsignedLong;
		case DRF_unsigned_char:
		case DRF_char:
		case DRF_String:
		case DRF_CString:
			if ( Campo->data.is_array )
				return ( double ) 0;
			else
				return ( double ) CDRr->field ( fld_offset ).fvUnsignedChar;
		case DRF_BCD:
			return ( double ) 0;
		case DRF_unsigned_short:
		case DRF_short:
			return ( double ) CDRr->field ( fld_offset ).fvUnsignedShort;
		case DRF_float:
			return ( double ) CDRr->field ( fld_offset ).fvFloat;
		case DRF_double:
			return ( double ) CDRr->field ( fld_offset ).fvDouble;
	}
	return ( double ) 0;

}


/* return record tag */

const char * CounterInterface::getHeaderTag()
{
	static const char *ht = CNT_HEADERTAG;

	return ht;
}

// get by tag

Counters * CounterInterface::getByTag ( const QString & srcTag )
{
	if ( Counterss->count() > 0 ) {
		foreach ( Counters * c, *Counterss ) {
			if ( c->data.tag == srcTag )
				return c;
		}
	}
	return ( Counters * ) 0;
}

// get tag list

TagList CounterInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( Counterss->count() > 0 ) {
		foreach ( Counters * c, *Counterss ) {
			tagList.values.append ( QString::number ( c->data.idCounter ) );
			tagList.labels.append ( QString ( c->data.tag ) );
		}
	}

	return tagList;
}

// setup i/e tags

void CounterInterface::ieInit()
{
	loadCnt = 0;
}

// do the export of the cdr config onto the ImportExport stream

void CounterInterface::ieExport ( ImportExport & ie )
{
	if ( Counterss->count() > 0 ) {
		foreach ( Counters * c, *Counterss ) {

			ie.initWriteRecord ( CNT_HEADERTAG );
			ie.setWriteTag ( "idCounter" );
			ie.addWriteRecord ( c->data.idCounter );
			ie.setWriteTag ( "tag" );
			ie.addWriteRecord ( c->data.tag );
			ie.setWriteTag ( "usePlugin" );
			ie.addWriteRecord ( c->data.usePlugin );
			ie.setWriteTag ( "pluginName" );
			ie.addWriteRecord ( c->data.pluginName );
			ie.setWriteTag ( "usePath" );
			ie.addWriteRecord ( c->data.usePath );
			ie.setWriteTag ( "pathName" );
			ie.addWriteRecord ( c->data.pathName );
			ie.flushWriteRecord();

			for ( int i = 0; i < CNT_NUM; i++ ) {
				ie.initWriteRecord ( CNTKIT_HEADERTAG );

				ie.setWriteTag ( "INDEX" );
				ie.addWriteRecord ( c->data.idCounter );
				ie.setWriteTag ( "CNT_IDX" );
				ie.addWriteRecord ( i );
				ie.setWriteTag ( "triggerField" );
				ie.addWriteRecord ( c->data.counterKit[i].triggerField );
				ie.setWriteTag ( "triggerValue" );
				ie.addWriteRecord ( c->data.counterKit[i].triggerValue );
				ie.setWriteTag ( "counterIndex" );
				ie.addWriteRecord ( c->data.counterKit[i].counterIndex );
				ie.setWriteTag ( "counterType" );
				ie.addWriteRecord ( c->data.counterKit[i].counterType );
				ie.setWriteTag ( "percentOf" );
				ie.addWriteRecord ( c->data.counterKit[i].percentOf );
				ie.setWriteTag ( "triggerCounter" );
				ie.addWriteRecord ( c->data.counterKit[i].triggerCounter );
				ie.setWriteTag ( "tag" );
				ie.addWriteRecord ( c->data.counterKit[i].tag );
				ie.setWriteTag ( "description" );
				ie.addWriteRecord ( c->data.counterKit[i].description );

				ie.flushWriteRecord();
			}
		}
	}
}

// import a record from ie stream

void CounterInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag == CNT_HEADERTAG ) {

		Counters * cnt = new Counters;

		cnt->data.idCounter = ieptr->getIntToken ( "idCounter" );
		ieptr->getStrToken ( cnt->data.tag, "tag", SHORT_DESC_LEN );
		cnt->data.usePlugin = ieptr->getBoolToken ( "usePlugin" );
		ieptr->getStrToken ( cnt->data.pluginName, "pluginName", CNT_PLUGINNAME_LEN );
		cnt->data.usePath = ieptr->getBoolToken ( "usePath" );
		ieptr->getStrToken ( cnt->data.pathName, "pathName", CNT_PATH_LEN );

		add ( cnt );
	}

	if ( ieRecordTag == CNTKIT_HEADERTAG ) {
		Counters * cnt = get ( ieptr->getIntToken ( "INDEX" ) );
		int SubIndex = ieptr->getIntToken ( "CNT_IDX" );
		if ( cnt == 0 ) {
			lout << "CounterInterface::ieImport : found unbouded counters kit CNT_IDX=" << SubIndex << endl;
			return;
		}
		cnt->data.counterKit[SubIndex].triggerField = ieptr->getIntToken ( "triggerField" );
		cnt->data.counterKit[SubIndex].triggerValue = ieptr->getLongToken ( "triggerValue" );
		cnt->data.counterKit[SubIndex].counterIndex = ieptr->getIntToken ( "counterIndex" );
		cnt->data.counterKit[SubIndex].counterType = ieptr->getIntToken ( "counterType" );
		cnt->data.counterKit[SubIndex].percentOf = ieptr->getIntToken ( "percentOf" );
		cnt->data.counterKit[SubIndex].triggerCounter = ieptr->getIntToken ( "triggerCounter" );
		ieptr->getStrToken ( cnt->data.counterKit[SubIndex].tag, "tag", CNT_TAG_LEN );
		ieptr->getStrToken ( cnt->data.counterKit[SubIndex].description, "description", CNT_DESC_LEN );
	}

	return;
}

CounterInterface & CounterInterface::operator= ( CounterInterface & cnt )
{
	clear();
	foreach ( Counters * c, *Counterss ) {
		add ( c );
	}
	CounterssNumber = count();
	return *this;
}

void CounterInterface::copyToCorba ( CountersSeq * seqptr )
{
	COUNTERS rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( Counters * c, *Counterss ) {

		rec.idCounter = c->data.idCounter;
		c_qstrncpy ( rec.tag, c->data.tag, SHORT_DESC_LEN );
		rec.usePlugin = c->data.usePlugin;
		c_qstrncpy ( rec.pluginName, c->data.pluginName, CNT_PLUGINNAME_LEN );
		rec.usePath = c->data.usePath;
		c_qstrncpy ( rec.pathName, c->data.pathName, CNT_PATH_LEN );

		rec.counterKit.length ( CNT_NUM );
		COUNTER_KIT akit;

		for ( int i = 0; i < CNT_NUM; i++ ) {

			akit.triggerField = c->data.counterKit[i].triggerField;
			akit.triggerValue = c->data.counterKit[i].triggerValue;
			akit.counterIndex = c->data.counterKit[i].counterIndex;
			akit.counterType = c->data.counterKit[i].counterType;
			akit.percentOf = c->data.counterKit[i].percentOf;
			akit.triggerCounter = c->data.counterKit[i].triggerCounter;
			c_qstrncpy ( akit.tag, c->data.counterKit[i].tag, CNT_TAG_LEN );
			c_qstrncpy ( akit.description, c->data.counterKit[i].description, CNT_DESC_LEN );

			rec.counterKit[i] = akit;
		}
		( *seqptr ) [seqidx++] = rec;
	}
}

void CounterInterface::fillFromCorba ( const CountersSeq * seqptr )
{
	Counters * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new Counters;

		rec->data.idCounter = ( *seqptr ) [cnt].idCounter;
		c_qstrncpy ( rec->data.tag, ( *seqptr ) [cnt].tag, SHORT_DESC_LEN );
		rec->data.usePlugin = ( *seqptr ) [cnt].usePlugin;
		rec->data.usePath = ( *seqptr ) [cnt].usePath;
		c_qstrncpy ( rec->data.pluginName, ( *seqptr ) [cnt].pluginName, CNT_PLUGINNAME_LEN );
		c_qstrncpy ( rec->data.pathName, ( *seqptr ) [cnt].pathName, CNT_PATH_LEN );

		int numel = ( ( *seqptr ) [cnt].counterKit.length() > CNT_NUM ) ? CNT_NUM : ( *seqptr ) [cnt].counterKit.length();

		for ( int i = 0; i < numel; i++ ) {
			rec->data.counterKit[i].triggerField = ( *seqptr ) [cnt].counterKit[i].triggerField;
			rec->data.counterKit[i].triggerValue = ( *seqptr ) [cnt].counterKit[i].triggerValue;
			rec->data.counterKit[i].counterIndex = ( *seqptr ) [cnt].counterKit[i].counterIndex;
			rec->data.counterKit[i].counterType = ( *seqptr ) [cnt].counterKit[i].counterType;
			rec->data.counterKit[i].percentOf = ( *seqptr ) [cnt].counterKit[i].percentOf;
			rec->data.counterKit[i].triggerCounter = ( *seqptr ) [cnt].counterKit[i].triggerCounter;
			c_qstrncpy ( rec->data.counterKit[i].tag, ( *seqptr ) [cnt].counterKit[i].tag, CNT_TAG_LEN );
			c_qstrncpy ( rec->data.counterKit[i].description, ( *seqptr ) [cnt].counterKit[i].description, CNT_DESC_LEN );
		}
		add ( rec );
	}

	CounterssNumber = count();

}


// kate: indent-mode cstyle; indent-width 8; replace-tabs off; tab-width 8;
