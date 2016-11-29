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

#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <math.h>

#include "datacollector.h"
#include <adamsbasic.h>
#include <applogger.h>
#include <configparser.h>

#include <Qt/qbytearray.h>
#include <Qt/qmutex.h>

static PluginBase * countersPluginBase = 0;		// reference Counter Handler Plugin
static PluginBase * drReaderPluginBase = 0;		// reference CDR reader Plugin
// static PluginImpl * countersPlugin = 0;		// just one instance for all threads

static PluginFactory pluginFactory;			// a proxy factory for traffic elements plugins

static bool IniError = false;				// prevent multiple init errors across threads

static QMutex mutex_ini, mutex_ini2, mutex_srt, mutex_key;
// static QMutex mutex_tree, mutex_cnt;
static QMutex plugMutex;

/*
** Binary Tree
*/

DataCollector::DataCollector()
	: started ( false ),
	  dataRecordReaderPlugin ( 0 ),
	  countersPlugin ( 0 ),
	  localProgress ( 0 ),
	  preallocPolicySize ( 0 ) {
	mutex_ini.lock();

	ConfigParser cnfparser;
	cnfparser.locateFile();

	pluginHome = cnfparser.parQTxtGetValue ( "DefaultPluginsPath", "ADAMS_Globals", ConfigParser::PathValue );

	if ( attachPlugins() ) {
		lout << "DataCollector: plugin(s) required but failed to attach: fatal error." << endl;
		::exit ( 1 );
	}
	else
		lout2 << "DataCollector: plugin(s) attached successfully." << endl;

	nfmPtr = new NodoFastMem;
	dataRecordReaderPlugin->pluginSetupConfig ( ( void * ) StoreConf::config(), ( void * ) StoreConf::params() );

	mutex_ini.unlock();
}

DataCollector::~DataCollector() {
	if ( dataRecordReaderPlugin ) delete dataRecordReaderPlugin;
	if ( countersPlugin ) delete countersPlugin;
}

int DataCollector::linkTable ( int elemid ) {
	DataElement * de = StoreConf::config()->dataElementInterface->get ( elemid );
	return ( de == 0 ) ? 0 : de->data.idDRLink;
}

void DataCollector::debugPluginError ( const QString & caller, const QString & requested, dcPluginMsgType type, PluginImpl * pli ) {
	switch ( type ) {
		case WrongType:
			lout << caller << " : " << requested << " : loaded wrong plugin type." << endl;
			break;
		default:
			lout << caller << " : Generic error." << endl;
	}

	lout << "Plugin Info: " << endl;
	if ( pli ) {
		lout << pli->getPluginInfo().name << " :: Type " << pli->getPluginInfo().globalTypeID << endl;
		lout << "++ Description    : " << pli->getPluginInfo().description << endl;
		lout << "++ Release        : " << pli->getPluginInfo().majorVersionNumber << "." << pli->getPluginInfo().minorVersionNumber << endl;
		lout << "++ Release Date   : " << pli->getPluginInfo().releaseDate.toString() << endl;
		lout << "++ Author/Vendor  : " << pli->getPluginInfo().author << " (" << pli->getPluginInfo().vendorName << ")" << endl;
	}
	else {
		lout << "Unloaded plugin." << endl;
	}
}


/** Attach plugins */

bool DataCollector::attachPlugins() {
	// contatori
	analysis = StoreConf::config()->analysisInterface->get ( StoreConf::params()->data.AnalysisType );
	if ( analysis ) {
		cntKitId = analysis->data.countersKitId;
	}
	else {
		lout << "DataCollector::attachPlugins : cannot get analisys #" << StoreConf::params()->data.AnalysisType << endl;
		return true;	// some error
	}
	counters = StoreConf::config()->counterInterface->get ( cntKitId );
	if ( counters == 0 ) {
		lout << "DataCollector::attachPlugins : cannot get counters kit #" << cntKitId << endl;
		return true;
	}
	if ( counters->data.usePlugin && !countersPluginBase ) {
		countersPluginBase = new PluginBase ( pluginHome );
		if ( countersPluginBase->registerPlugin ( counters->data.pluginName,
		                ( counters->data.usePath ) ? counters->data.pathName :
		                StoreConf::config()->drInterface->globalOpt()->data.glbDefaultPluginPath, true ) ) {
			return true;			// plugin required but failed to attach
		}
	}

	if ( counters->data.usePlugin ) {
		countersPlugin = countersPluginBase->getInstance();
		if ( !countersPlugin->verifyType ( Adams_CounterHandler ) ) {
			debugPluginError ( "DataCollector::attachPlugins", "CounterHandler", WrongType, countersPlugin );
			return true;
		}
		countersPlugin->pluginSetupConfig ( ( void * ) StoreConf::config(), ( void * ) StoreConf::params() );
	}

	// cdr reader
	if ( !drReaderPluginBase ) {
		drReaderPluginBase = new PluginBase ( pluginHome );
		// StoreConf::config()->drInterface->globalOpt()->data.cdrUsePlugin  !!N.B. avoid test since plugin usage is mandatory
		if ( drReaderPluginBase->registerPlugin ( StoreConf::config()->drInterface->globalOpt()->data.drPluginName,
		                ( StoreConf::config()->drInterface->globalOpt()->data.drUsePath ) ? StoreConf::config()->drInterface->globalOpt()->data.drPathName : 0, false ) ) {
			// if here means locally or user specified attach failed. We'll try on global path
			if ( QString ( StoreConf::config()->drInterface->globalOpt()->data.glbDefaultPluginPath ).length() > 0 ) {
				if ( drReaderPluginBase->registerPlugin ( StoreConf::config()->drInterface->globalOpt()->data.drPluginName,
				                StoreConf::config()->drInterface->globalOpt()->data.glbDefaultPluginPath, true ) ) {
					return true;	// ok. i'm unable to find it >8-(
				}
			}
		}
	}

	dataRecordReaderPlugin = drReaderPluginBase->getInstance();
	if ( !dataRecordReaderPlugin->verifyType ( Adams_CDRReader ) ) {
		debugPluginError ( "DataCollector::attachPlugins", "CDRReader", WrongType, dataRecordReaderPlugin );
		return true;
	}

	return false;
}

void DataCollector::startExec ( QString Day, int daycount, int totalDays ) {
	started = true;

	m_day = Day;
	m_daycount = daycount;
	m_totalDays = totalDays;
	m_ret = true;

	start();
}

void DataCollector::run() {
	m_ret =  BuildBtree ( m_day, m_daycount, m_totalDays );
}

// evaluate restrictions items

QString DataCollector::pluginDescription ( int pl_id ) {
	PluginRegistry * p = StoreConf::config()->pluginRegistryInterface->get ( pl_id );
	return ( p ) ? p->data.pluginName : QString::number ( pl_id );
}


bool DataCollector::initOper ( QueryParams::Rest * r, BuildOper & bo ) {
	DataElement * te = StoreConf::config()->dataElementInterface->get ( r->Element );
	if ( te == 0 ) {
		lout << "mdm_agent : DataCollector::initOper : Unable to initialize restriction element: " << r->Element << endl;
		return false;
	}

	QString deName = te->data.shortDescription;

	bo.elemId = r->Element;
	bo.name = te->data.shortDescription;
	bo.oper = ( UsrParamOperator ) r->Operator;
	bo.priority = r->Priority;

	if ( linkTable ( bo.elemId ) < 0 ) {			// this te has no link to cdr fields
		bo.type = tNumber;			// TODO: forced now. Should find a way to evaluate exact type
	}
	else {
		DRField * Campo = StoreConf::config()->drInterface->fastget ( linkTable ( bo.elemId ) );
		if ( Campo->data.fieldtype == DRF_CString ||
		                Campo->data.fieldtype == DRF_String  ||
		                Campo->data.fieldtype == DRF_BCD     ||
		                ( ( Campo->data.fieldtype == DRF_unsigned_char || Campo->data.fieldtype == DRF_char ) && Campo->data.is_array ) ) {
			bo.type = tString;
		}
		else {
			bo.type = tNumber;
		}
	}

	if ( te->data.idPlugin > 0 ) {
		// handled by plugin, so recall it
		bo.plugin = getHandlerPluginInstance ( te );
		if ( bo.plugin == 0 )
			return false;
		bo.hasPlugin = true;
		DEHandlerWorkerAPIData pld;
		if ( !bo.plugin->startup ( ( void * ) &pld ) ) {
			lout << "mdm_agent : DataCollector::initOper : Cannot startup plugin " <<
			     pluginDescription ( te->data.idPlugin ) << " needed from Element " << deName << endl;
			return 0;
		}
		bo.setPluginUsage ( pld );
		// call plugin to setup restriction (eventually)
		pld.element = te;
		pld.rest = r;
		pld.oper = &bo;
		pld.dayIndex = m_daycount;
		pld.operation = teOp_InitPhase;
		if ( pld.useFor[teOp_InitPhase] ) {
			bo.plugin->pluginWorker ( ( void * ) &pld );
			if ( pld.status == teStatus_Error ) {
				lout << "mdm_agent : DataCollector::initOper : Plugin execution (InitPhase) was unsucessfull for element " <<  deName << endl;
				return false;
			}
			if ( pld.status != teStatus_UnmanagedOperation ) // unless plugin unamanged init phase we terminate here
				return true;
		}
	}
	else {	// setup scripts (scripts and plugins are mutually exclusive)
		if ( te->data.scripts[Script::saInitPhase] > 0 ) { 		// if te has an init script call it once now
			Script * inis = StoreConf::config()->scriptInterface->get ( te->data.scripts[Script::saInitPhase] );
			if ( !inis ) {
				lout << "mdm_agent : DataCollector::initOper : Cannot get script #" << te->data.scripts[Script::saInitPhase] << " needed from Element " << deName << endl;
				return false;
			}
			if ( !callBasic_InitPhase ( r, te, inis ) ) {
				lout << "mdm_agent : DataCollector::initOper : Execution of script #" << te->data.scripts[Script::saInitPhase] << " failed." << endl;
				return false;
			}

		}
		// link to element remaining scripts
		if ( te->data.scripts[Script::saFieldEvaluation] > 0 ) {
			Script * inis = StoreConf::config()->scriptInterface->get ( te->data.scripts[Script::saFieldEvaluation] );
			if ( !inis ) {
				lout << "mdm_agent : DataCollector::initOper : Cannot get script #" << te->data.scripts[Script::saFieldEvaluation] << " needed from Element " << deName << endl;
				return false;
			}
			bo.scripts[Script::saFieldEvaluation] = getAdamsBasicInstance ( inis );
			if ( bo.scripts[Script::saFieldEvaluation] == 0 ) {
				return false;
			}
		}
		if ( te->data.scripts[Script::saFiltering] > 0 ) {
			Script * inis = StoreConf::config()->scriptInterface->get ( te->data.scripts[Script::saFiltering] );
			if ( !inis ) {
				lout << "mdm_agent : DataCollector::initOper : Cannot get script #" << te->data.scripts[Script::saFiltering] << " needed from Element " << deName << endl;
				return false;
			}
			bo.scripts[Script::saFieldEvaluation] = getAdamsBasicInstance ( inis );
			if ( bo.scripts[Script::saFieldEvaluation] == 0 ) {
				return false;
			}
		}
	}
	if ( te->data.scripts[Script::saInitPhase] > 0 )
		return true;
	// if nor plugin nor scripts alter user params (values) we take care of them here
	if ( bo.type == tNumber ) {
		bo.numV = new LongSVector;
		for ( int i = 0; i < MAX_N_VALUE; i++ ) {
			if ( r->Value[i] < 0 )
				break;
			bo.numV->add ( new long ( r->Value[i] ) );
		}
	}
	else {
		bo.strV = new QStringSVector;
		for ( int i = 0; i < MAX_N_VALUE; i++ ) {
			if ( QString ( r->AsciiValue[i] ).length() == 0 )
				break;
			bo.strV->add ( new QString ( r->AsciiValue[i] ) );
		}
	}

	return true;
}

PluginImpl * DataCollector::getHandlerPluginInstance ( DataElement * te ) {
	PluginImpl * plugin;

	PluginRegistry * p = StoreConf::config()->pluginRegistryInterface->get ( te->data.idPlugin );
	if ( p == 0 ) {
		lout << "mdm_agent : DataCollector::getHandlerPluginInstance : Cannot get plugin " <<
		     pluginDescription ( te->data.idPlugin ) << " needed from Element " << te->data.shortDescription << endl;
		return 0;
	}
	// first check if there is an instance already setup ?
	plugin = pluginImplFactory[te->data.idElement];
	if ( plugin ) {
		return plugin;
	}
	// now check if a pluginbase already exists for this te
	PluginBase * plBase = pluginFactory[te->data.idElement];
	if ( plBase == 0 ) {		// new one
		plBase = new PluginBase ( pluginHome );
		if ( plBase->registerPlugin ( p->data.pluginName, StoreConf::config()->drInterface->globalOpt()->data.glbDefaultPluginPath, true ) ) {
			lout << "mdm_agent : DataCollector::getHandlerPluginInstance : Failed to register plugin " <<
			     pluginDescription ( te->data.idPlugin ) << " needed from Element " << te->data.shortDescription << endl;
			return 0;			// plugin required but failed to attach
		}

		pluginFactory.insert ( te->data.idElement, plBase );
	}
	plugin = plBase->getInstance();
	if ( !plugin->verifyType ( Adams_ElementHandler ) ) {
		debugPluginError ( "mdm_agent : DataCollector::getHandlerPluginInstance", "DataElementHandler", WrongType, plugin );
		delete plugin;
		return 0;
	}
	plugin->pluginSetupConfig ( ( void * ) StoreConf::config(), ( void * ) StoreConf::params() );
	pluginImplFactory.insert ( te->data.idElement, plugin );
	return plugin;
}

bool DataCollector::initRelation ( DefineRelation * dr ) {
	if ( dr == 0 ) {
		return false;
	}
	for ( int r = 0; r < dr->getDimension(); r++ ) {
		RelationComponent * c = dr->getRelationComponent ( r );
		if ( c == 0 ) {
			lout << "mdm_agent : DataCollector::initRelation : Malformed RelationComponents !!" << endl;
			return false;
		}
		DataElement * te = StoreConf::config()->dataElementInterface->get ( c->id );
		if ( te == 0 ) {
			lout << "mdm_agent : DataCollector::initRelation : Unable to initialize relation component: " << c->id << endl;
			return false;
		}

		if ( te->data.idPlugin > 0 ) {
			c->pluginHandler = getHandlerPluginInstance ( te );
			if ( c->pluginHandler ) {
				DEHandlerWorkerAPIData pld;
				if ( !c->pluginHandler->startup ( ( void * ) &pld ) ) {
					lout << "mdm_agent : DataCollector::initOper : Cannot startup plugin " <<
					     pluginDescription ( te->data.idPlugin ) << " needed from Element " << te->data.shortDescription << endl;
					return 0;
				}
				if ( !pld.useFor[teOp_KeyBuilding] ) {
					delete c->pluginHandler;		// not useful for us
					c->pluginHandler = 0;
				}
				continue;
			}
			else
				return false;
		}
		else if ( te->data.scripts[Script::saKeyBuilding] > 0 ) {
			Script * inis = StoreConf::config()->scriptInterface->get ( te->data.scripts[Script::saKeyBuilding] );
			if ( !inis ) {
				lout << "mdm_agent : DataCollector::initRelation : Cannot get script #" <<
				     te->data.scripts[Script::saKeyBuilding] << " needed from Element " << te->data.shortDescription << endl;
				return false;
			}
			c->scriptHandler = getAdamsBasicInstance ( inis );
			if ( c->scriptHandler == 0 )
				return false;
		}
	}

	return true;
}

AdamsBasic * DataCollector::getAdamsBasicInstance ( Script * s ) {
	AdamsBasic * nb = new AdamsBasic;
	nb->setReportMode ( AdamsBasic::nbToLogFile );

	if ( nb->getStatus() != AdamsBasic::nbSuccess ) {
		delete nb;
		return 0;	// error
	}
	// set vars & text
	if ( !nb->setProgramScript ( *s ) ) {
		delete nb;
		return 0;
	}

	return nb;
}

bool DataCollector::callBasic_InitPhase ( QueryParams::Rest * r, DataElement * te, Script * s ) {
	AdamsBasic * nb = new AdamsBasic;
	nb->setReportMode ( AdamsBasic::nbToLogFile );

	if ( nb->getStatus() != AdamsBasic::nbSuccess )
		return false;	// error
	// Restriction Variable declaration
	nb->addInitVariable ( QString ( te->data.shortDescription ).toLower(), DRF_long );
	QStringList restrVals;
	QString qstr;
	for ( int i = 0; i < MAX_N_VALUE; i++ ) {
		qstr = r->AsciiValue[i];
		if ( qstr.length() == 0 )
			break;
		restrVals << qstr;
	}
	nb->setValue ( QString ( te->data.shortDescription ).toLower(), restrVals );
	// set vars & text
	if ( !nb->setProgramScript ( *s ) ) {
		return false;
	}
	// Execute
	if ( !nb->execute() ) {
		return false;
	}
	// Get result
	// ????????? come per un array ??
	delete nb;
	return true;
}

bool DataCollector::callBasic_BuildKey ( char *& cdrKey, DRImageHandler & CDRr, RelationComponent & rParts ) {
	AdamsBasic * nb = rParts.scriptHandler;
	if ( nb == 0 ) {
		lout << "mdm_agent : DataCollector::callBasic_BuildKey : Called with non-instantiated AdamsBasic reference." << endl;
		return false;
	}
	const Script * scr = nb->getOriginatingScript();
	if ( scr == 0 ) {
		lout << "mdm_agent : DataCollector::callBasic_BuildKey : Called with no reference to originating script." << endl;
		return false;
	}
	// valorize var list
	bool justifyAtLeft = false;
	DataElement * te;
	for ( QStringList::ConstIterator it = scr->data.variables.begin(); it != scr->data.variables.end(); ++it ) {
		te = StoreConf::config()->dataElementInterface->getByTag ( ( *it ) );
		if ( te == 0 ) {
			lout << "mdm_agent : DataCollector::callBasic_BuildKey : Cannot get DataElement : " << ( *it ) << endl;
			return false;
		}
		if ( te->data.idDRLink >= 0 ) {	// take from CDR
			switch ( StoreConf::config()->drInterface->fastget ( te->data.idDRLink )->data.fieldtype ) {
				case DRF_unsigned_int:
					nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvUnsignedInt );
					break;
				case DRF_int:
					nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvInt );
					break;
				case DRF_unsigned_char:
				case DRF_char:
					if ( StoreConf::config()->drInterface->fastget ( te->data.idDRLink )->data.is_array ) {
						nb->setValue ( ( *it ).toLower(), ( char * ) CDRr.field ( te->data.idDRLink ).fvArrayOfChar );
						justifyAtLeft = true;
					}
					else {
						nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvUnsignedChar );
					}
					break;
				case DRF_unsigned_short:
					nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvUnsignedShort );
					break;
				case DRF_short:
					nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvShort );
					break;
				case DRF_unsigned_long:
					nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvUnsignedLong );
					break;
				case DRF_long:
					nb->setValue ( ( *it ).toLower(), ( long ) CDRr.field ( te->data.idDRLink ).fvLong );
					break;
				case DRF_float:
					nb->setValue ( ( *it ).toLower(), ( double ) CDRr.field ( te->data.idDRLink ).fvFloat );
					break;
				case DRF_double:
					nb->setValue ( ( *it ).toLower(), ( double ) CDRr.field ( te->data.idDRLink ).fvDouble );
					break;
			}
		}
		else {		// operates on user restrictions directly
			BuildOper * bo = opersDirect.find ( te->data.idElement ).value();
			if ( bo ) {
				if ( bo->type == tNumber ) {
					if ( bo->numV == 0 )
						continue;
					if ( bo->numV->count() == 0 )
						continue;
					nb->setValue ( ( *it ).toLower(), *bo->numV->toHead() );
				}
				else {
					if ( bo->strV == 0 )
						continue;
					if ( bo->strV->count() == 0 )
						continue;
					nb->setValue ( ( *it ).toLower(), ( *bo->strV->toHead() ) );
				}
			}
		}
	}
	// Execute
	if ( !nb->execute() ) {
		return false;
	}
	// Get result
	QString result;
	if ( !nb->getResult ( result ) )
		return false;
	if ( justifyAtLeft )
		qstrcpy ( cdrKey, qPrintable ( result.leftJustified ( rParts.length, ' ', true ) ) );
	else
		qstrcpy ( cdrKey, qPrintable ( result.rightJustified ( rParts.length, '0', true ) ) );
	return true;
}

// init, build, analize, construct binary tree

bool DataCollector::BuildBtree ( QString Day, int daycount, int totalDays ) {
	DRImageHandler CDRr ( StoreConf::config()->drInterface );
	DefineRelation DefRel;
	int iSize = StoreConf::config()->drInterface->getDRRecordSize();
	CountersPluginAPIData countersPluginAPIData;
	int restrictionNumber;
	bool zeroFilters = false;
	HashDict & Btree = *Btreep;
	long reallocStep = 0;

	if ( localProgress == 0 )
		localProgress = new int;

	if ( !opersDirect.isEmpty() ) {
		opersDirect.clear();
	}


	bool usePluginNodeHandler = false;
	bool transferCDR = false;
	int userDataSize = 0;

	if ( countersPlugin ) {
		countersPluginAPIData.dayIndex = daycount;
		countersPluginAPIData.transferCDR = transferCDR;
		countersPluginAPIData.usePluginNodeHandler = usePluginNodeHandler;
		countersPluginAPIData.userDataSize = userDataSize;

		countersPlugin->startup ( ( void * ) &countersPluginAPIData );

		transferCDR = countersPluginAPIData.transferCDR;
		usePluginNodeHandler = countersPluginAPIData.usePluginNodeHandler;
		userDataSize = countersPluginAPIData.userDataSize;
		Nodo::setupUserDataSize ( userDataSize );
	}

	// copy user restrictions in (priority) sorted list
	// N.B. indexing params are always take from the highest priority TE. Is plugin responsibilty to treat
	//      other cases.
	mutex_srt.lock();
	if ( !CDRr.validate ( StoreConf::config()->drInterface ) )
		::abort();		// first of all check for CDR config -> CDR handler compatibility

	if ( !sortedFilters.isEmpty() )
		sortedFilters.clear();

	restrictionNumber = StoreConf::params()->data.Filters.count();
	if ( restrictionNumber == 1 ) {
		if ( StoreConf::params()->data.Filters.first().Element == 0 )
			restrictionNumber = 0;
	}
	if ( restrictionNumber == 0 )
		zeroFilters = true;
	else {

		for ( int i = 0; i < StoreConf::params()->data.Filters.size(); ++i ) {
			sortedFilters.append ( StoreConf::params()->data.Filters.at ( i ) );
		}
		qSort ( sortedFilters.begin(), sortedFilters.end(), SortedRest::compareItems );
		for ( int i = 0; i <  sortedFilters.size(); ++i ) {
			BuildOper * bo = new BuildOper;
			QueryParams::Rest r = sortedFilters.value ( i );
			if ( initOper ( &r, *bo ) ) {
				opers.add ( bo );
				opersDirect.insert ( bo->elemId, bo );
			}
			else {
				delete bo;
				lout << "DataCollector::BuildBtree : Cannot allocate BuildOper for TE #" << r.Element << endl;
				return false;
			}
		}
	}
	mutex_srt.unlock();

	// analize relation
	mutex_ini2.lock();
	int usingRelationID = StoreConf::params()->data.Relation;
	bool usingGhostRelation = StoreConf::config()->relationInterface->get ( usingRelationID )->data.ghostRelation;
	if ( usePluginNodeHandler )
		usingGhostRelation = true;	// forced
	DefRel.setElementInRelation ( StoreConf::config(), StoreConf::params()->data.Relation, StoreConf::params()->data.ffRelation );
	RelationComponent * relcomponents = DefRel.getRelationComponentsArray();
	int relparts = DefRel.getDimension();
	if ( !initRelation ( &DefRel ) ) {	// hook plugins or scripts
		lout << "DataCollector::BuildBtree : Cannot initialize relation handlers." << endl;
		return false;
	}
// 	if (StoreConf::params()->data.SingleRelation)
// 		relparts = 1;
	mutex_ini2.unlock();

	// init cdr reader
	CdrReaderStartupAPIData crsad;

	mutex_key.lock();
	crsad.operation = cdrInitOp_globalStartup;
	if ( zeroFilters ) {
		crsad.useIndex = false;
		crsad.keyName = "";
		crsad.operList = 0;
	}
	else {
		crsad.useIndex = ( sortedFilters.first().Priority > 0 );
		crsad.keyName = StoreConf::config()->dataElementInterface->get ( sortedFilters.first().Element )->data.Suffix;
		if ( crsad.keyName.isEmpty() )
			crsad.keyName = StoreConf::config()->dataElementInterface->get ( sortedFilters.first().Element )->data.shortDescription;
		crsad.keyList = opers.toHead();
		crsad.operList = &opersDirect;
	}
	crsad.processDate = Day;
	crsad.dayIndex = daycount;
	crsad.dataType = StoreConf::params()->data.DataType;
	crsad.forceFiltering = false;
	crsad.startOffset = 0;		// not used here
	crsad.endOffset = 0;		// not used here
	// now determine the pre-allocation policy
	if ( !countersPlugin ) {		// counters plugin set couters sizing
		int n_int = 0, n_dbl = 0;
		StoreConf::config()->counterInterface->getConfiguredCounters ( counters, n_int, n_dbl );
		Nodo::setCountersSize ( n_int, n_dbl );
	}
	preallocPolicySize = ( crsad.useIndex ? INDEXED_PREALLOC_SIZE : SEQUENTIAL_PREALLOC_SIZE );
	reallocStep = ( long ) findNextPrime ( preallocPolicySize );
// 	reallocStep = preallocPolicySize;
	Btree.reserve ( reallocStep );
	Nodo::setNodePreAllocBuffer ( nfmPtr, preallocPolicySize );
	Nodo::setPreAllocElements ( preallocPolicySize );

	mutex_key.unlock();

	CdrReaderWorkerAPIData cdrData;
	cdrData.progress = localProgress;
	*cdrData.progress = 0;			// moved here to avoid crash on failed plugin startup

	// do a first call to CDR reader to initialize and wakeup reader thread (if implemented in plugin code)
	if ( !dataRecordReaderPlugin->startup ( ( void * ) &crsad ) ) {
		lout << "DataCollector::BuildBtree : cdrReader initialization failure !" << endl;
		return false;
	}

	// init complete, now starts the real work...
	cdrData.cdr = &CDRr;
	char cdrKey[MAX_KEY_LENGTH];
	Nodo * nodo;
	int allocCount = 0;
	if ( analysis->data.FlagCumulativeData )
		qstrcpy ( cdrKey, "--SINGLEKEY--" );
	bool skipKeyEvaluation = ( usingGhostRelation || analysis->data.FlagCumulativeData );
//  	int letti = 0;

	do {
		if ( !dataRecordReaderPlugin->pluginWorker ( ( void * ) &cdrData ) )
			break;						// unrecoverable error

		if ( cdrData.status == cdrStatus_EOF_NoRecords )
			break;

		if ( applyFilter ( &CDRr, daycount ) == false ) {
			continue;
		}


		if ( ( ( skipKeyEvaluation ) ? true : evalKey ( CDRr, relcomponents, cdrKey, relparts ) ) ) {
//  			lout3 << "\""<<cdrKey<<"\"" << endl;
// 			mutex_tree.lock();
//  			++letti;
//  			if ((letti % 10) == 0) lout << "D3 ricevuti="<<letti<<endl;
			if ( usingGhostRelation ) {
				countersPluginAPIData.operation = cntKeyHandle;
				countersPluginAPIData.cdrKey = cdrKey;
				countersPlugin->pluginWorker ( ( void * ) &countersPluginAPIData );
			}

			if ( ! Btree.contains(cdrKey) ) {
// 				lout3 << "new nodo" << endl;
				if ( !usePluginNodeHandler ) {
					nodo = Nodo::fastAlloc ( nfmPtr );
					nodo->setupCounters_fast ( nfmPtr );
// 					lout3 << "fast" << endl;
				}
				else
					nodo = new Nodo();

				if ( nodo == 0 ) {
					lout << "DataCollector::BuildBtree : th[" << daycount << "] out of memory !!" << endl;
					::exit ( 1 );
				}

				++allocCount;
				if ( allocCount > reallocStep ) {
// 					lout << "DSERVER: oldsize = " << Btree.size() << endl;
					reallocStep += preallocPolicySize;
					reallocStep = ( long ) findNextPrime ( reallocStep );
					if ( Btree.size() <= reallocStep )
						Btree.reserve ( reallocStep );
// 					lout << "DSERVER: newsize = " << Btree.size() << endl;
				}

				Btree.insert ( cdrKey, nodo );
			}
//			mutex_tree.unlock();
// 			lout3 << "nodo mem addr: 0x" << hex << (unsigned long) nodo->GetLista()->int_counters << dec << endl;

			if ( countersPlugin ) {
				countersPluginAPIData.operation = cntCountersEvaluation;
				countersPluginAPIData.countersNode = nodo;
				countersPluginAPIData.cdr = &CDRr;
// 				mutex_cnt.lock();
				countersPlugin->pluginWorker ( ( void * ) &countersPluginAPIData );
// 				mutex_cnt.unlock();
			}
			else {	// standard toolkit
				int n_int, n_dbl;
				nodo->getCountersSize ( n_int, n_dbl );

				StoreConf::config()->counterInterface->updateCounters (
				        StoreConf::config(),
				        StoreConf::params(),
				        counters,
				        nodo,
				        &CDRr,
				        n_int,
				        n_dbl );
			}
		}

		if ( cdrData.status == cdrStatus_EOF )
			break;

	}
	while ( cdrData.status == cdrStatus_Success );

	if ( countersPlugin ) {
		countersPluginAPIData.operation = cntShutdown;
		countersPluginAPIData.Btree = &Btree;
// 		mutex_cnt.lock();
		countersPlugin->shutdown ( ( void * ) &countersPluginAPIData );
// 		mutex_cnt.unlock();
	}

	dataRecordReaderPlugin->shutdown ( 0 );	// give reader time to halt
	lout << "[" << daycount << "]Elaborate " << Btree.count() << " chiavi" << endl;

	return true;
}


//
// Aggiornamento del toolkit standard dei contatori
//

void DataCollector::UpdateCounters ( QueryParams * counterlist, DRImageHandler * CDRr, int dayindex ) {
	return;
}

/*
** Questa funzione applica eventuali filtri impostati dall'utente.
*/

bool DataCollector::applyFilter ( DRImageHandler * CDRr, int daycount ) {
	int linkCdr;
	DEHandlerWorkerAPIData pld;
	DRField * campo;
	bool bOwned = true;
	bool globTest = true;
	double ValueField;

	DataElement * tep;
	int iCount = 0;

	for ( BuildOper * bo = opers.toHead(); bo != 0; bo = opers.next() ) {

		linkCdr = linkTable ( bo->elemId );

		if ( bo->filterDisabled )					// data filter disabled
			continue;

		if ( bo->scripts[Script::saFiltering] > 0 ) {		// restriction apply delegated to script
			// globTest &= callBasic_Filtering();
//			lout << "BASIC 	globTest="<<globTest<<endl;
			if ( !globTest )
				break;
			else
				continue;
		}


		if ( bo->hasPlugin && bo->useFor[teOp_Filtering] ) {	// restriction apply delegated to plugin
			pld.operation = teOp_Filtering;
			pld.dayIndex = daycount;
			pld.CDRr = CDRr;
			pld.oper = bo;
//			lout << "PLUGIN te:" << bo->name << " name = " << bo->plugin->getPluginInfo().name;
//			if (bo->type == tNumber) {
//				lout << " num rcount:" << bo->numV->count() << endl;
//			}
//			else {
//				lout << " str rcount:" << bo->strV->count() << endl;
//			}
			bo->plugin->pluginWorker ( ( void * ) &pld );

			globTest &= pld.accept;
//			lout << "PLUGIN globTest="<<globTest<<endl;
			if ( !globTest )
				break;
			else
				continue;
		}

		if ( linkCdr < 0 ) continue;				// unlinked & unmanaged: ignore

		campo = StoreConf::config()->drInterface->fastget ( linkCdr );

// 		lout << "------>esamino RES:" << bo->name << " linkCDR:" << linkCdr << " bo->elemId:" << bo->elemId << endl;
		if ( campo->data.is_array ) {
			if ( bo->strV == 0 )
				continue;				// res vector uninitialized
			if ( bo->strV->count() == 0 )
				continue;				// no res.
			switch ( campo->data.fieldtype ) {
				case DRF_unsigned_char:
				case DRF_char:
				case DRF_String:
				case DRF_BCD:
					afBuf[0] = '\0';
					qstrncpy ( afBuf, ( const char * ) CDRr->field ( linkCdr ).fvArrayOfChar, campo->data.array_size + 1 );
					break;
				case DRF_CString:
					afBuf[0] = '\0';
					qstrncpy ( afBuf, ( const char * ) CDRr->field ( linkCdr ).fvArrayOfChar, campo->data.array_size );
					break;
				default:
					lout << "Unsupported array type: " << campo->data.fieldtype << endl;
					continue;
			}

			for ( QString * s = bo->strV->toHead(); s != 0; s = bo->strV->next() ) {
				/*				if ((*s).length() < campo->data.array_size)
									afBuf[(*s).length()] = '\0';	// keep string lengths consistents*/
				switch ( bo->oper ) {
					case poEQUAL:
						bOwned = ( *s ) == afBuf;
// 						lout << "ck: ["<< (*s) << "]==[" << afBuf << "]" << ((bOwned)?"*":"") << endl;
						break;
					case poGREATER:
						bOwned = ( *s ) > afBuf;
						break;
					case poGREATER_OR_EQUAL:
						bOwned = ( *s ) >= afBuf;
						break;
					case poLESSER:
						bOwned = ( *s ) < afBuf;
						break;
					case poLESSER_OR_EQUAL:
						bOwned = ( *s ) <= afBuf;
						break;
					default:
						lout << "WRONG OPERATOR VALUE FOR " << StoreConf::config()->dataElementInterface->get ( bo->elemId )->data.longDescription <<
						     " oper.: " << bo->oper << endl;
						break;
				}
// 				lout << "TESTATO ["<<(*s)<<"] con ["<<afBuf<<"] bOwned:" << bOwned << endl;
				if ( bOwned ) break;
			}
		}
		else { // not an array
			if ( bo->numV == 0 )
				continue;				// res vector uninitialized
			if ( bo->numV->count() == 0 )
				continue;				// no res.
			switch ( campo->data.fieldtype ) {
				case DRF_unsigned_int:
					ValueField = CDRr->field ( linkCdr ).fvUnsignedInt;
					break;
				case DRF_int:
					ValueField = CDRr->field ( linkCdr ).fvInt;
					break;
				case DRF_char:
				case DRF_unsigned_char:
					ValueField = CDRr->field ( linkCdr ).fvUnsignedChar;
					break;
				case DRF_unsigned_short:
					ValueField = CDRr->field ( linkCdr ).fvUnsignedShort;
					break;
				case DRF_short:
					ValueField = CDRr->field ( linkCdr ).fvShort;
					break;
				case DRF_unsigned_long:
					ValueField = CDRr->field ( linkCdr ).fvUnsignedLong;
					break;
				case DRF_long:
					ValueField = CDRr->field ( linkCdr ).fvLong;
					break;
				case DRF_float:
					ValueField = CDRr->field ( linkCdr ).fvFloat;
					break;
				case DRF_double:
					ValueField = CDRr->field ( linkCdr ).fvDouble;
					break;
			}

			for ( long * l = bo->numV->toHead(); l != 0; l = bo->numV->next() ) {
				switch ( bo->oper ) {
					case poEQUAL:
						bOwned = *l == ValueField;
						break;
					case poGREATER:
						bOwned = *l < ValueField;
						break;
					case poLESSER:
						bOwned = *l > ValueField;
						break;
					case poGREATER_OR_EQUAL:
						bOwned = *l <= ValueField;
						break;
					case poLESSER_OR_EQUAL:
						bOwned = *l >= ValueField;
						break;
					default:
						lout << "WRONG OPERATOR VALUE FOR " << StoreConf::config()->dataElementInterface->get ( bo->elemId )->data.longDescription <<
						     " oper.: " << bo->oper << endl;
						break;
				}
//				lout << "------>valutato RES:" << bo->name << " valore  = " << *l << " ValueField = " << ValueField
//				     << " oper.: " << bo->oper << " bOwned="<< bOwned<< endl;
				if ( bOwned ) break;
			}
		}

		globTest &= bOwned;
		if ( !globTest )
			break;
	}
//	lout << "STATO FINALE globTest:" << globTest << endl;
	/*
	** Verifica se � stata richiesta
	** una elaborazione negata.
	*/
	if ( StoreConf::params()->data.OppositeRestriction )
		return !globTest;
	else
		return globTest;
}


long long DataCollector::findNextPrime ( long long ptst ) {

	if ( isPrime ( ptst ) ) {
		return ptst;
	}

	if ( ( ptst % 2 ) == 0 )
		ptst++;
	while ( !isPrime ( ptst ) )
		ptst += 2;
	return ptst;
}


bool DataCollector::isPrime ( long long ptst ) {
	long pmaxseek, a;
	bool prim_found = true;

	if ( ( ptst % 2 ) == 0 )
		return false;

	pmaxseek = ( long ) sqrt ( ( double ) ptst ) + 1;

	for ( a = 3; a <= pmaxseek; a++, a++ ) {
		if ( ! ( ptst % a ) ) {
			prim_found = false;
			break;
		}
	}

	return prim_found;
}

