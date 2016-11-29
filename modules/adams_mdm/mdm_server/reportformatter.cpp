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

#include "reportformatter.h"

#include <adamsbasic.h>
#include <applogger.h>
#include <Qt/qlist.h>

#define	MAXSPACEDESCRIPTION	26	// some size for preformatted reports
#define	MAXPARAMDESCR		8
#define FORCE_PLAIN_LIMIT	1500	// number of nodes (maximum) to switch to plain format

typedef QHash<int, PluginBase *> PluginFactory;

static const char * VertAlignTranslate[] = { "middle", "top", "bottom" };
static const char * HoriAlignTranslate[] = { "center", "left", "right", "justify" };
static const char * descDataType[] = { "Current" , "Archive" };
static const char * descRelationDirection[] = { "Direct", "Inverse", "Both Directions" };
static PluginFactory pluginFactory;	// a proxy factory for traffic elements plugins

ReportFormatter::ReportFormatter(AdamsCompleteConfig & configI, QueryParams & paramsI, TimeOut * tmout)
	: config(configI),
	  params(paramsI),
	  myStatus(NotInitialized),
	  myMedia(HttpServer),
	  helper(0),
	  watchDog(tmout),
	  noGrand(false),
	  firstHdrDrawed(false),
	  http(0)
{
	myStatus = Initialized;
	plainHdrLineSize = -1;		// size of separator lines in chars
	plainKeyFldSize = MAXSPACEDESCRIPTION+1;
	plainCntFldSize = MAXPARAMDESCR+1;
	plainHdrSepChar = '|';
	plainBdySepChar = ' ';
	plainLineChar = '-';
	altPlainLineChar = '.';
}


ReportFormatter::~ReportFormatter()
{
	if (myMedia == LocalFile && myStatus >= MediaInitialized)
		delete stream.device();
// 	if (http) delete http;
}

/**
Esegue l'inizializzazione della classe. Imposta le principali referenze esterne.
	@param http Canale di comunicazione con il server HTTP.
	@param config Puntatore ai dati di configurazione
*/
bool ReportFormatter::init(BtreeMap  * directTreeI, BtreeMap * inverseTreeI, int nodeIndexI)
{
	directTree = directTreeI;
	inverseTree = inverseTreeI;
	nodeIndex = nodeIndexI;
	if (myStatus == Initialized) {
		if (!initOutputMedia())
			return false;
	}
	myStatus = FullInitialized;

	return true;
}

/**
Esegue l'inizializzazione della classe. Imposta le principali referenze esterne.
Overload del metodo precedente utilizzato nella gestione delle analysis di tipo network.
	@param http Canale di comunicazione con il server HTTP.
	@param config Puntatore ai dati di configurazione
*/
bool ReportFormatter::init(BTreeNetworkRT  * directTreeI, BTreeNetworkRT * inverseTreeI)
{
	directTree = directTreeI;
	inverseTree = inverseTreeI;
	nodeIndex = -1;
	if (myStatus == Initialized) {
		if (!initOutputMedia())
			return false;
	}
	myStatus = FullInitialized;

	return true;
}

/**
Esegue il setup del media di output come selzionato dall'utente.
Il valore di ritorno false indica l'impossibilta' di eseguire l'operazione.
*/
bool ReportFormatter::initOutputMedia()
{
	if (myStatus == FullInitialized) {
		lout << "ReportFormatter::initOutputMedia : WARNING : Trying to change media type after it has been already opened. [OPERATION REFUSED]" << endl;
		return true;
	}

	QString rdir = cnfparser.parQTxtGetValue("Report_Dir", "MDM");

	if (myMedia == LocalFile) {
		if (mediaName.isEmpty()) {
			mediaName = tempFileName( "MDM_REPORT_" ) + ".txt";
		}

		QFile * file = new QFile(mediaName);
		if (file->open(QIODevice::WriteOnly)) {
			stream.setDevice(file);
		}
		else {
			lout << "ReportFormatter::initOutputMedia : Cannot open output media (LocalFile) : " << mediaName << endl;
			return false;
		}
	}
	else if (myMedia == HttpServer) {
		mediaName = tempFileName( "MDM_REPORT_" ) + ".html";

		if ( params.data.isScheduled ) {
			http = new HttpImpl(params.data.idUser, params.data.idJob, params.data.isScheduled, params.data.Description);
			http->initialize(mediaName, false, true);
			delete http;
		}
		http = new HttpImpl(params.data.idUser, params.data.idJob, params.data.isScheduled, params.data.Description);
		http->initialize(mediaName, false, false);
	}
	else {
		lout << "ReportFormatter::initOutputMedia : unknown media type #" << myMedia << endl;
		return false;
	}

	myStatus = MediaInitialized;
	return true;
}

/**
Invia una stringa sul tipo di media selezionato. Le stringhe sono automaticamente terminate in base al tipo di media.
	@param output Stringa da inviare in output
*/
void ReportFormatter::send(const QString & line, bool terminate)
{
	switch (myMedia) {
		case LocalFile:
			stream << line + ((terminate) ? "\n" : "");
			break;
		case HttpServer:
			http->send(line + ((terminate) ? "\r\n" : ""));
			break;
		default:
			break;
	}
}

/**
Chiude il canale di output.
*/
void ReportFormatter::closeMedia()
{
	switch (myMedia) {
		case LocalFile:
			stream.device()->close();
			break;
		case HttpServer:
			http->close();
			break;
		default:
			break;
	}
}

/**
Elabora il report
*/

void ReportFormatter::exec()
{		// retrieve data from config and user selection
	int cntKitId;
	myStatus = Processing;

	analysis = config.analysisInterface->get(params.data.AnalysisType);
	if (analysis) {
		cntKitId = analysis->data.countersKitId;
	}
	else {
		lout << "ReportFormatter::exec : cannot get analisys #" << params.data.AnalysisType << endl;
		myStatus = InErrorWhileProcessing;
		return;	// some error
	}
	kit = config.counterInterface->get(cntKitId);
	if (kit == 0) {
		lout << "ReportFormatter::exec : cannot get counters kit #" << cntKitId << endl;
		myStatus = InErrorWhileProcessing;
		return;
	}
	schema = config.reportInterface->get(params.data.idReportSchema);
	if (schema == 0) {
		lout << "ReportFormatter::exec : cannot get schema #" << params.data.idReportSchema << endl;
		myStatus = InErrorWhileProcessing;
		return;
	}
	config.relationInterface->setDataElementReference(config.dataElementInterface);
	relation * r = config.relationInterface->get(params.data.Relation);
	if (r == 0) {
		lout << "ReportFormatter::exec : Invalid user params : NO relation." << endl;
		return;
	}
	ghostRelation = false;
	if (r->data.ghostRelation) {
		relationSize = 1;
		ghostRelation = true;
	}
	else {
		defineRelation.setElementInRelation(&config, params.data.Relation, params.data.ffRelation);
		relationSize = (params.data.SingleRelation) ? 1 : defineRelation.getDimension();
	}

	Nodo::getCountersSize(numIntCounters, numDblCounters);

	keyHelperPresent = new bool [relationSize];
	keyStore = new QString [relationSize];
	keyLengths = new int [relationSize];
	keyScript = new AdamsBasic * [relationSize];
	keyPlugin = new PluginImpl * [relationSize];
	hiddenKey = new bool [relationSize];
	hexKey = new bool [relationSize];
	keyAlwaysShow = new bool [relationSize];
	for (int i = 0; i < relationSize; i++) {	// clean dynamics
		keyScript[i] = 0;
		keyPlugin[i] = 0;
		keyHelperPresent[i] = false;
		keyLengths[i] = 0;
		hiddenKey[i] = false;
		hexKey[i] = false;
		keyAlwaysShow[i] = false;
	}

	fmtPlainBody = schema->objO.data.u.object.simpleBody; // we want a private flag for this

	setupRelationParts(relationSize);

	if (schema->objO.data.u.object.usePlugin) {	// plugin handled
		QString PlugIn_dir = config.drInterface->globalOpt()->data.glbDefaultPluginPath;

		expand_plugin_dir(PlugIn_dir);

		pluginBase = new PluginBase;
		if (pluginBase->registerPlugin(schema->objO.data.u.object.pluginName, PlugIn_dir, true)) {
			delete pluginBase;
			return;
		}

		pluginImpl = pluginBase->getInstance();
		if (!pluginImpl->verifyType(Adams_ReportHandler)) {
			debugPluginError("ReportFormatter::exec", "Adams_ReportHandler", WrongType, pluginImpl);
			delete pluginImpl;
			delete pluginBase;
			return;
		}

		reportHandlerAPIData.directTree = directTree;
		reportHandlerAPIData.inverseTree = inverseTree;
		reportHandlerAPIData.nodeIndex = nodeIndex;
		reportHandlerAPIData.http = http;
		reportHandlerAPIData.stream = &stream;
		reportHandlerAPIData.mediaType = myMedia;
		reportHandlerAPIData.haveHeaderManagement = false;
		reportHandlerAPIData.helper = helper;

		pluginImpl->pluginSetupConfig(&config, &params);
		pluginImpl->startup(&reportHandlerAPIData);

// 		lout << "Startup report handler " << schema->objO.data.u.object.pluginName << ": ok" << endl;
	}

	if (schema->bdyV.count() > 0)
		cellStore = new QString [schema->bdyV.count()];

	if (schema->objO.data.u.object.excelCSV == rfex_CSV) {	// EXCEL CSV
		plainHdrSepChar = ';';
		plainBdySepChar = ';';
		schema->objO.data.u.object.hasBorder = true;
		fmtPlainBody = true;
	}


//  lout << "AVVIO STAMPA" << endl;
		// start doc
	indentLevel = 0;
	if (schema->objO.data.u.object.excelCSV == rfex_None)
		htmlStartSequence(schema->objO);

	if ((!schema->objO.data.u.object.usePlugin || (schema->objO.data.u.object.usePlugin && !reportHandlerAPIData.haveHeaderManagement))
	    && schema->objO.data.u.object.excelCSV == rfex_None && schema->hdrV.count() > 0) {
			// create header: first logo then title
		for (RSOVectorIterator it = schema->hdrV.begin(); it != schema->hdrV.end(); ++it ) {
			if ((*it).data.u.header.value == ReportSchemaObj::Logo) {
				ReportSchemaObj o = (*it);
				QString cellText;
				o.data.html.hSize = 100;
				if (o.data.u.header.isUrl) {
					cellText = placeUrl(o.data.u.header.userValue);
				}
				else
					cellText = QString(o.data.u.header.userValue);
				openTable(false);
				openRow(); openCell(&o,cellText); closeRow();
				closeTable();
			}
		}
		for (RSOVectorIterator it = schema->hdrV.begin(); it != schema->hdrV.end(); ++it ) {
			if ((*it).data.u.header.value == ReportSchemaObj::Title) {
				ReportSchemaObj o = (*it);
				QString cellText;
				o.data.html.hSize = 100;
				if (o.data.u.header.isUrl) {
					cellText = placeUrl(o.data.u.header.userValue);
				}
				else
					cellText = QString(o.data.u.header.userValue);
				openTable(false);
				openRow(); openCell(&o,cellText); closeRow();
				closeTable();
			}
		}

		openTable(true);	// header has a particula style: no line row but a border around all
		openRow();
		openCell(0,0,false);
		openTable(false);
		openRow();

		for (RSOVectorIterator it = schema->hdrV.begin(); it != schema->hdrV.end(); ++it ) {
			handleHeaderEvents((*it));
		}

		closeRow();
		closeTable();
		closeCell(0);
		closeRow();
		closeTable();
		send(indent() + "<br>");
		send(indent() + "<br>");
		send(indent() + "<br>");

//  	lout << "FINE HEADER" << endl;
	}

	if (schema->objO.data.u.object.usePlugin) {	// rest of the job is done by the plugin
		pluginImpl->pluginWorker(&reportHandlerAPIData);
// 		lout << "*** Report handler " << schema->objO.data.u.object.pluginName << ": job completed." << endl;
		delete pluginImpl;
		return;
	}

		// enter body loop
	doBodyHdr = true;
	triggeredChangeOnFirstKey = true;

		// Forza adozione schema plain (non-HTML) se il numero di chiavi e'
		// maggiore di un dato limite
		// Modifica richiesta M.Trimarchi 2003/07/11
	if (directTree) {
		if (directTree->count() > FORCE_PLAIN_LIMIT)
			fmtPlainBody = true;
	}
	if (inverseTree) {
		if (inverseTree->count() > FORCE_PLAIN_LIMIT)
			fmtPlainBody = true;
	}

	if (directTree) {
		if (inverseTree)
			adviceUser("Relation Direction: DIRECT");
		if (directTree->count() > 0) {
			bodyLoop(directTree);
			noGrand = true;
		}
		else
			directTree = 0;
	}

	doBodyHdr = true;
	triggeredChangeOnFirstKey = true;
	if (inverseTree) {
		if (directTree)
			adviceUser("Relation Direction: INVERSE");
		if (inverseTree->count() > 0) {
			defineRelation.setInvertAccess();
			setupRelationParts(relationSize);
			bodyLoop(inverseTree);
		}
		else
			inverseTree = 0;
	}

	if (inverseTree == 0 && directTree == 0) {	// totally null
		adviceForNullReport();
		return;
	}
//  lout << "FINE BODY" << endl;
		// now the grand totals
	if (schema->objO.data.u.object.excelCSV == rfex_CSV && schema->fooV.count() == 0) {
		return;
	}
	send(indent() + "<br>");
	send(indent() + "<br>");
	send(indent() + "<br>");
	openTable(true, 50, 2, 0, "align=\"center\"");	// footer has a particular style: no line row but a border around all
	openRow();
	openCell(0,0,false);
	openTable(false);
	for (RSOVectorIterator it = schema->fooV.begin(); it != schema->fooV.end(); ++it ) {
		handleFooterEvents((*it));
	}
	closeTable();
	closeCell(0);
	closeRow();
	closeTable();
	send(indent() + "<br>");
	send(indent() + "<br>");
	send(indent() + "<br>");
//  lout << "FINE FOOTER" << endl;

	return;
}
//
// debug su plugins
//

void ReportFormatter::debugPluginError(const QString & caller, const QString & requested, pluginMsgType type, PluginImpl * pli)
{
	switch (type) {
		case WrongType:
			lout << caller << " : " << requested << " : loaded wrong plugin type." << endl;
			break;
		default:
			lout << caller << " : Generic error." << endl;
	}

	lout << "Plugin Info: " << endl;
	if (pli) {
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

PluginImpl * ReportFormatter::getHandlerPluginInstance(DataElement * te)
{
	PluginImpl * plugin;

	PluginRegistry * p = config.pluginRegistryInterface->get(te->data.idPlugin);
	if (p == 0) {
		lout << "NtmMasterServer : ReportFormatter::getHandlerPluginInstance : Cannot get plugin #" <<
		        te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
		return 0;
	}
	//####### This piece of code is needed under HP-UX since server invocation upon a shell
	//        (as the serverfactory does) completely blank user environment.......
	QString PlugIn_dir = config.drInterface->globalOpt()->data.glbDefaultPluginPath;

	expand_plugin_dir(PlugIn_dir);
	//#######

	// first check if a pluginbase already exists for this te
	PluginBase * plBase = pluginFactory[te->data.idElement];
	if (plBase == 0) {		// new one
		plBase = new PluginBase;
		if (plBase->registerPlugin(p->data.pluginName, PlugIn_dir, true)) {
			lout << "NtmMasterServer : ReportFormatter::getHandlerPluginInstance : Failed to register plugin " <<
			        p->data.pluginName << " needed from Element #" << te->data.idElement << endl;
			return 0;			// plugin required but failed to attach
		}

		pluginFactory.insert(te->data.idElement, plBase);
	}
	plugin = plBase->getInstance();
	if (!plugin->verifyType(Adams_ElementHandler)) {
		debugPluginError("NtmMasterServer : ReportFormatter::getHandlerPluginInstance", "DataElementHandler", WrongType, plugin);
		delete plugin;
		return 0;
	}
	plugin->pluginSetupConfig(&config, &params);

	return plugin;
}


void ReportFormatter::setupRelationParts(int relationSize)
{
	if (ghostRelation)
		return;
	for (int i = 0; i < relationSize; i++) {	// clean dynamics
		if (keyScript[i] > 0)
			delete keyScript[i];
		keyScript[i] = 0;
		if (keyPlugin[i] > 0)
			delete keyPlugin[i];
		keyPlugin[i] = 0;
	}
		// try optimization of report size by skipping (key) columns valorized
		// with restricted TE. This apply only for one value restrictions.
	fmt3d = (relationSize == 3);
	QList<QueryParams::Rest> restrs(params.data.Filters);
	for (int i = relationSize - 1; i > 0; i--) {		// i >= 0 to include leftmost key too
		hiddenKey[i] = false;
// 		for (const QueryParams::Rest r = restrs.first(); r; r = restrs.next()) {
		for (int ir = 0; ir < restrs.size(); ++ir) {
			QueryParams::Rest r = restrs.at(ir);
			if (r.Element == defineRelation.getRelationComponent(i)->id &&
			    r.Operator == poEQUAL) { 						// found a res. for the rel. part
				if (r.Value[1] == -1 && (relationSize >= 3)) {			// ... and it has just one value
					hiddenKey[i] = true;
					break;
				}
			}
		}
		if (hiddenKey[i] == true) {	// stop at the first one
// 			fmt3d = false;
			break;
		}
	}

	if (fmtPlainBody == false)
		fmt3d = false;

	for (int i = 0; i < relationSize; i++) {
		keyLengths[i] = defineRelation.getRelationComponent(i)->length;
		keyStore[i] = "";
			// setup helpers
		if (helper) {
			DescHelperAPIData * hstr = (*helper)[defineRelation.getRelationComponent(i)->desc];
			if (hstr) {
				hstr->helpTag = defineRelation.getRelationComponent(i)->desc;
// 				lout1 << "setup help for [" << defineRelation.getRelationComponent(i)->desc << "]" << endl;
				hstr->nodeIndex = nodeIndex;
				hstr->handler->pluginWorker(hstr);
				keyHelperPresent[i] = hstr->loaded;
			}
			else {
// 				lout1 << "NO helper for [" << defineRelation.getRelationComponent(i)->desc << "]" << endl;
				keyHelperPresent[i] = false;
			}
		}
		else
			keyHelperPresent[i] = false;
			// setup scripts & key show
		DataElement * te = config.dataElementInterface->get(defineRelation.getRelationComponent(i)->id);
		Script * inis = 0;
		bool hasPlugin = false;
		if (te) {
			keyAlwaysShow[i] = te->data.repeatKey;	// key show done !
			if (keyAlwaysShow[i])
				hiddenKey[i] = false;		// disable key column hiding too...
// 			if (keyAlwaysShow[i]) lout << "RPF: te " << te->data.idElement << " on relpos " << i << " want key always show." << endl;
			if (te->data.scripts[Script::saReportProduction] > 0)
				inis = config.scriptInterface->get(te->data.scripts[Script::saReportProduction]);
			hasPlugin = (te->data.idPlugin > 0);
		}
		if (hasPlugin) {
// 			lout << "element " << te->data.idElement << " has plugin" << endl;
			// handled by plugin, so recall it
			PluginRegistry * p = config.pluginRegistryInterface->get(te->data.idPlugin);
			keyPlugin[i] = getHandlerPluginInstance(te);
			if (keyPlugin[i] > 0) {
// 				lout << "got plugin instance for key " << i << endl;
				if (!keyPlugin[i]->startup((void *)&pld)) {
					lout << "NtmMasterServer : ReportFormatter : Cannot startup plugin " <<
						p->data.pluginName << " needed from Element #" << te->data.idElement << endl;
					delete keyPlugin[i];
					keyPlugin[i] = 0;
				}
				else {
					if (pld.useFor[teOp_ReportProduction] == false) {
						// unsupported by plugin's functionality
// 						lout << "element " << te->data.idElement << " report usage unsupported" << endl;
						delete keyPlugin[i];
						keyPlugin[i] = 0;
						hasPlugin = false;
					}
					else {
						// call plugin to setup it (eventually)
						pld.element = te;
						pld.operation = teOp_InitPhase;
						keyPlugin[i]->pluginWorker((void *)&pld);
						if (pld.status == teStatus_Error) {
							lout << "NtmMasterServer : ReportFormatter : Plugin execution (InitPhase) was unsucessfull for element #" <<  te->data.idElement << endl;
							delete keyPlugin[i];
							keyPlugin[i] = 0;
						}
					}
				}
			}
		}
		if (inis && !hasPlugin) {	// plugins always have precedence
			AdamsBasic * nb = new AdamsBasic;
			nb->setReportMode(AdamsBasic::nbToLogFile);

			if (nb->getStatus() != AdamsBasic::nbSuccess) {
				delete nb;
				keyScript[i] = 0;	// error
			}
			else if (!nb->setProgramScript(*inis)) {
				delete nb;
				keyScript[i] = 0;	// error
			}
			else
				keyScript[i] = nb;
		}
// 		else
// 			keyScript[i] = 0;

		hexKey[i] = te->data.acceptHex;
	}
}

/**
Segnala all'utente il report nullo
*/
void ReportFormatter::adviceForNullReport()
{
	ReportSchemaObj guideO;
	guideO.data.html.hSize = 100;
	guideO.data.html.wrap = true;

	QString cellText = QString("                      *** GOT AN EMPTY REPORT ***                     <br>") +
			   QString("                                                                      <br>") +
			   QString("         This probably means you requested incorrect/mismatched       <br>") +
			   QString("           restrictions for this node -or- traffic data relation.     <br>");

	send(indent() + "<br>");
	send(indent() + "<br>");
	send(indent() + "<br>");
	openTable(true, 100, 2, 0, "align=\"center\"");
	openRow();
	openCell(&guideO, cellText);
	closeRow();
	closeTable();
	send(indent() + "<br>");
	send(indent() + "<br>");
	send(indent() + "<br>");
}

/**
Crea una message box sul report
*/
void ReportFormatter::adviceUser(const QString & mesg)
{
	ReportSchemaObj guideO;
	guideO.data.html.hSize = 100;
	guideO.data.html.wrap = true;

	QString cellText = mesg;

	send(indent() + "<br>");
	send(indent() + "<br>");
	send(indent() + "<br>");
	openTable(true, 100, 2, 0, "align=\"center\"");
	openRow();
	openCell(&guideO, cellText);
	closeRow();
	closeTable();
	send(indent() + "<br>");
	send(indent() + "<br>");
	send(indent() + "<br>");
}

/**
Produce l'output necessario all'interpretazione degli eventi footer programmati dall'utente per il report.
	@param trattare Ogetto/evento da trattare
*/
void ReportFormatter::handleFooterEvents(const ReportSchemaObj &  o)
{
		// is a breaker: call handler
	if (o.data.u.footer.line != ReportSchemaObj::NoBreak) {
		handleBreakerEvents(o);
		return;
	}

	QString cellText = "", labelText;
	ReportSchemaObj guideO = o;

	labelText = QString(o.data.u.footer.label);

	if (o.data.u.footer.source == 0) {		// unused
		return;
	}
	else if (o.data.u.footer.source == RSO_FOOTER_NKEY_VAL) {
		if (directTree)
			cellText += QString::number(directTree->count()) + QString(" directs keys");
		if (directTree && inverseTree) {
			cellText += "<br>";
			guideO.data.html.wrap = true;
		}
		if (inverseTree)
			cellText += QString::number(inverseTree->count()) + QString(" backwards keys");
	}
	else if (o.data.u.footer.source == RSO_FOOTER_USER_VAL) {
		if (o.data.u.footer.isUrl) {
			cellText = placeUrl(o.data.u.footer.userValue);
		}
		else
			cellText = QString(o.data.u.footer.userValue);
	}
	else {
		ReportSchemaObj bdyO;

		for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it ) {	// find body object
			if ((*it).data.idObject == o.data.u.footer.source) {
				bdyO = (*it);
				break;
			}
		}
		if (bdyO.data.idObject == 0) {
			lout << "ReportFormatter:: handleFooterEvents: misconfigured Footer " << o.data.tag << endl;
			return;
		}

		int columnIndex = grandTotal.getRsoColumn(bdyO.data.idObject);
		if (grandTotal.pool[columnIndex] == 0) {		// unused total counter
			cellText = "&nbsp;";
		}
		else {
			int prec = bdyO.data.u.body.decimals;
/*			if (bdyScripts[columnIndex].sHndl)
				prec = 2;
			else
				prec = (getCounterType(kit->data.counterKit[bdyO.data.u.body.idCounter-1].counterIndex) == INT_TYPE) ? 0 : 2;*/
			if (grandTotal.percentCross[columnIndex] != 0) {
				if (*grandTotal.percentCross[columnIndex] != 0)
					cellText = QString::number(((*grandTotal.pool[columnIndex] * 100.0) / *grandTotal.percentCross[columnIndex]), 'f', 2);
			}
			else
				cellText = QString::number(*grandTotal.pool[columnIndex], 'f', prec);
		}
		guideO.data.html.hAlign = ReportSchemaObj::Right;
	}
	openRow();
	openCell(&o, labelText); openCell(&guideO, cellText);
	closeRow();

}


/**
In questo metodo sono inserite le funzionalita' per la realizzazione delle righe (corpo) del report.
*/
void ReportFormatter::bodyLoop(BtreeMap * bTree)
{
		// Prior to eneter the real loop we need to initialize all the totalizers configured
	if (schema->totV.count() > 0) {
		subTotals = new TotalData [schema->totV.count()];	// how many ?
		int idx = 0;
		for (RSOVectorIterator tot = schema->totV.begin(); tot != schema->totV.end(); ++tot ) {
			subTotals[idx].rso = &(*tot);
				// check for scripting
			Script * inis = 0;
			if (subTotals[idx].rso->data.u.totalizer.useScript && subTotals[idx].rso->data.u.totalizer.idScript > 0)
				inis = config.scriptInterface->get(subTotals[idx].rso->data.u.totalizer.idScript);
			if (inis) {
				AdamsBasic * nb = new AdamsBasic;
				nb->setReportMode(AdamsBasic::nbToLogFile);

				if (nb->getStatus() != AdamsBasic::nbSuccess) {
					delete nb;
					subTotals[idx].sHndl = 0;	// error
				}
				else if (!nb->setProgramScript(*inis)) {
					delete nb;
					subTotals[idx].sHndl = 0;	// error
				}
				else
					subTotals[idx].sHndl = nb;
			}
			else
				subTotals[idx].sHndl = 0;

			++idx;
		}
	}

	if (schema->bdyV.count() > 0) {
		bdyScripts = new BodyScripts [schema->bdyV.count()];	// how many ?
		int idx = 0;
		for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it) {
			// some init ?
			Script * inis = 0;
			if ((*it).data.u.body.source == ReportSchemaObj::Script && (*it).data.u.body.idScript > 0)
				inis = config.scriptInterface->get((*it).data.u.body.idScript);
			if (inis) {
				AdamsBasic * nb = new AdamsBasic;
				nb->setReportMode(AdamsBasic::nbToLogFile);

				if (nb->getStatus() != AdamsBasic::nbSuccess) {
					delete nb;
					bdyScripts[idx].sHndl = 0;	// error
				}
				else if (!nb->setProgramScript(*inis)) {
					delete nb;
					bdyScripts[idx].sHndl = 0;	// error
				}
				else
					bdyScripts[idx].sHndl = nb;
				// presets variables indexes
				if (bdyScripts[idx].sHndl) {
					int bsi = 0, vari = 0;
					bdyScripts[idx].rso = &(*it);
					for ( QStringList::ConstIterator var = inis->data.variables.begin(); var != inis->data.variables.end(); ++var ) {
						if ((vari = getCounterIndex((*var), bdyScripts[idx].tagsIndex[bsi])) >= 0)
							bdyScripts[idx].varsIndex[bsi++] = vari;
					}
				}
			}
			else
				bdyScripts[idx].sHndl = 0;

			++idx;
		}
	}

		// now goes on...
	BtreeMap::Iterator it, itstart, itend;
	itstart = bTree->begin();
	itend = bTree->end();

	if(params.data.FlagSort && !params.data.Ascendent) {
		itstart = bTree->end();
		itend = bTree->begin();
		--itstart;
		--itend;
	}

	forceBodyHdr = 0;
	needHtmlSetOnRow = true;
	bool totalHandlePerformed = false;

	if (fmtPlainBody) {
		send("<pre>");
	}
	else
		openTable(schema->objO.data.u.object.hasBorder);

	for ( it = itstart; it != itend; ) {
		if (watchDog)
			watchDog->setiActive(true);	// reset watchdog
			// handle rows
		countersData = it.value().GetLista();
		currentKey = it.key();

		checkRowTriggers();
// 		if (doBodyHdr) {
// 			handleBodyHdr();
// 		}

		for (int i = 0; i < schema->totV.count(); i++) {
			if (subTotals[i].triggered) {
				handleSubTotalRow(subTotals[i]);
				subTotals[i].reset();
				needHtmlSetOnRow = true;
				totalHandlePerformed = true;
			}
		}
		if (!fmt3d && forceBodyHdr >= FORCE_BODY_HDR_ROWS && totalHandlePerformed) {
			doBodyHdr = true;
			totalHandlePerformed = false;
		}
		else if (fmt3d && totalHandlePerformed && triggeredChangeOnFirstKey) {
			doBodyHdr = true;
			totalHandlePerformed = false;
		}
		else {
			totalHandlePerformed = false;
		}

		if (doBodyHdr) {
			handleBodyHdr();
			needHtmlSetOnRow = true;
		}
		handleBodyRow();
		++forceBodyHdr;

			// esegue incremento/decremento
		if (params.data.FlagSort && !params.data.Ascendent)
			--it;
		else
			++it;
	}
	for (int i = 0; i < schema->totV.count(); i++) {	// flush subtotalizers at the end of the report
		handleSubTotalRow(subTotals[i]);
		subTotals[i].reset();
	}
	if (fmtPlainBody) {
		send("</pre>");
	}
	else
		closeTable();

}

/**
Gestore stampa header di riga (corpo)
*/
void ReportFormatter::handleBodyHdr()
{
	QString cellText;
	ReportSchemaObj o;
	int printedKParts = 0;
	int size;
	bool justLeft = false;
	bool kp0Ok = false;
	bool kp1Ok = false;

	if (schema->objO.data.u.object.excelCSV == rfex_CSV && firstHdrDrawed) {
		return;
	}
	firstHdrDrawed = true;
	forceBodyHdr = 0;
	if (plainHdrLineSize <= 0) {
		plainHdrLineSize = 0;
		int kcnt = 0;
		int columncnt = 0;
		for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it ) {
			o = (*it);
			if (o.data.u.body.source == ReportSchemaObj::dsUndefined) {
				continue;
			}
			else if (o.data.u.body.source == ReportSchemaObj::KeyPart) {
				if (o.data.u.body.idKey >= ((fmt3d)?(relationSize-1):relationSize))	// do not place key parts id greater then relation size
					continue;
				if (hiddenKey[kcnt]) {
					++kcnt;
					continue;
				}
				if (kcnt == 0 && columncnt == 0)
					kp0Ok = true;
				if (kcnt == 1 && columncnt == 1)
					kp1Ok = true;
				++kcnt;
				plainHdrLineSize += plainKeyFldSize+1;
			}
			else if (o.data.u.body.source == ReportSchemaObj::User) {
// 					plainHdrLineSize += QString(o.data.u.body.userValue).length() + 1;
			}
			else {
				plainHdrLineSize += plainCntFldSize+1;
			}
			++columncnt;
		}
		plainHdrLineSize++;
		if (kp0Ok == false || kp1Ok == false) {
			if (fmt3d)
				plainHdrLineSize += plainKeyFldSize+1;
			fmt3d = false;
		}
	}
	if (schema->objO.data.u.object.excelCSV == rfex_None) {
		if (fmtPlainBody) {
			if (!fmt3d || (fmt3d && (!triggeredChangeOnFirstKey))) {
				cellText.fill(plainLineChar, plainHdrLineSize);
				send(cellText);
				send(QChar(plainHdrSepChar), false);
			}
		}
		else
			openRow();
	}

	for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it ) {
		o = (*it);
		justLeft = false;
		if (o.data.u.body.source == ReportSchemaObj::dsUndefined) {
			continue;
		}
		else if (o.data.u.body.source == ReportSchemaObj::KeyPart) {
			if (o.data.u.body.idKey >= relationSize)	// do not place key parts id greater then relation size
				continue;
			if (o.data.u.body.idKey < 0) {
				lout << "ReportFormatter::handleBodyHdr : found header key part with id < 0 !!" << endl;
				continue;
			}
			if (hiddenKey[printedKParts]) {
				++printedKParts;
				continue;
			}

			DataElement * te = 0;
			QString d;
			if (defineRelation.getRelationComponent(o.data.u.body.idKey))
				te = config.dataElementInterface->get(defineRelation.getRelationComponent(o.data.u.body.idKey)->id);
			if (te)
				d = te->data.longDescription;
			else
				d = defineRelation.getRelationComponent(o.data.u.body.idKey)->desc;
			if (fmt3d && printedKParts == 0 && triggeredChangeOnFirstKey) {
				cellText.fill(altPlainLineChar, plainHdrLineSize);
				QString t = QString("[ ") + d + " ]";
				cellText.replace(4, t.length(), t);
				send(cellText);
				cellText = QString(" ") + cellStore[0];
				send(cellText);
				cellText.fill(plainLineChar, plainHdrLineSize);
				send(cellText);
				++printedKParts;
				continue;
			}
			else {
				cellText = d;
			}
			triggeredChangeOnFirstKey = false;
			size = plainKeyFldSize;
			justLeft = true;
			++printedKParts;
		}
		else if (o.data.u.body.source == ReportSchemaObj::User) {
			if (o.data.u.body.isUrl) {
				cellText = placeUrl(o.data.u.body.userValue);
			}
			else
				cellText = QString(o.data.u.body.userValue);
			size = cellText.length() + 1;
		}
		else {
			cellText = o.data.tag;
			size = plainCntFldSize;
		}

		if (schema->objO.data.u.object.excelCSV == rfex_CSV) {
			cellText = QString("\"") + cellText + "\"";
		}

		if (schema->objO.data.u.object.excelCSV == rfex_CSV) {
			send(cellText+QString(QChar(plainBdySepChar)), false);
		} else if (fmtPlainBody) {
			if (justLeft)
				send(cellText.leftJustified(size, ' ', true)+QString(QChar(plainHdrSepChar)), false);
			else
				send(cellText.rightJustified(size, ' ', true)+QString(QChar(plainHdrSepChar)), false);
		}
		else
			openCell(&(*it), cellText, true, true);
	}
	if (schema->objO.data.u.object.excelCSV == rfex_None) {
		if (fmtPlainBody) {
			send("");
			cellText.fill(plainLineChar, plainHdrLineSize);
			send(cellText);
		}
		else
			closeRow();
	}
	else {
			send("");
	}
	doBodyHdr = false;
}



/**
Gestore riga corpo
*/
void ReportFormatter::handleBodyRow()
{
	QString cellText;
	int printedKParts = 0;
	int columnIndex = -1;
	int size;
	bool justLeft = false;

	if (schema->objO.data.u.object.excelCSV == rfex_None) {
		if (fmtPlainBody)
			send(QChar(plainBdySepChar), false);
		else
			openRow();
	}
	for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it) {
		++columnIndex;
		justLeft = false;
		if ((*it).data.u.body.source == ReportSchemaObj::dsUndefined) {
			continue;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::KeyPart) {
			if ((*it).data.u.body.idKey >= relationSize)	// do not place key parts id greater then relation size
				continue;
			if (fmt3d && printedKParts == 0) {
				++printedKParts;
				continue;
			}
			cellText = cellStore[columnIndex];
			if (hiddenKey[printedKParts]) {
				++printedKParts;
				continue;
			}
			++printedKParts;
			size = plainKeyFldSize;
			justLeft = true;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::User) {
			if ((*it).data.u.body.isUrl) {
				cellText = placeUrl((*it).data.u.body.userValue);
			}
			else
				cellText = QString((*it).data.u.body.userValue);
			size = cellText.length() + 1;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::Script && bdyScripts[columnIndex].sHndl) {
			QString result;
			double cellValue = 0.0;
			if (!callBasic_BODY(columnIndex, 0, result, (*it).data.u.body.decimals)) {
// 				cellText = "0.00";
				cellText = "!S";
			}
			else {
				cellValue = result.toDouble();
// 				cellText = QString::number(cellValue, 'f', 2);
				cellText = result.toUpper();
			}
				// manage totalizers
			if (!(*it).data.u.body.noTotals) { // user want totals?
				for (int i = 0; i < schema->totV.count(); i++) {
					subTotals[i].update(cellValue, columnIndex);
				}
				if (!noGrand) {
					grandTotal.update(cellValue, columnIndex);
					grandTotal.setRsoMapTo((*it).data.idObject, columnIndex);
				}
			}
			size = plainCntFldSize;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::CounterKit && (*it).data.u.body.idCounter > 0) {
			double cellValue = getCounterValue(kit->data.counterKit[(*it).data.u.body.idCounter-1].counterIndex);
			if (kit->data.counterKit[(*it).data.u.body.idCounter-1].percentOf > 0 && params.data.IsPercent) {	// percent or absolute ?
				double valueRef = getCounterValue(kit->data.counterKit[(*it).data.u.body.idCounter-1].percentOf);
				if (valueRef != 0)
					cellText = QString::number(((cellValue * 100.0) / valueRef), 'f', 2);
				for (int i = 0; i < schema->totV.count(); i++) {
					subTotals[i].updateReference(valueRef, columnIndex);
				}
				if (!noGrand)
					grandTotal.updateReference(valueRef, columnIndex);
			}
			else {
// 				int prec = (getCounterType(kit->data.counterKit[(*it).data.u.body.idCounter-1].counterIndex) == INT_TYPE) ? 0 : 2;
				int prec = (*it).data.u.body.decimals;
				cellText = QString::number(cellValue, 'f', prec);
			}
				// manage totalizers
			if (!(*it).data.u.body.noTotals) { // user want totals?
				for (int i = 0; i < schema->totV.count(); i++) {
					subTotals[i].update(cellValue, columnIndex);
				}
				if (!noGrand) {
					grandTotal.update(cellValue, columnIndex);
					grandTotal.setRsoMapTo((*it).data.idObject, columnIndex);
				}
			}
			size = plainCntFldSize;
		}
		if (schema->objO.data.u.object.excelCSV == rfex_CSV) {
			send(cellText+QString(QChar(plainBdySepChar)), false);
		} else if (fmtPlainBody) {
			if (justLeft)
				send(cellText.leftJustified(size, ' ', true)+QString(QChar(plainBdySepChar)), false);
			else
				send(cellText.rightJustified(size, ' ', true)+QString(QChar(plainBdySepChar)), false);
		}
		else
			openCell((needHtmlSetOnRow) ? &(*it) : 0, cellText);
	}
	if (schema->objO.data.u.object.excelCSV == rfex_None) {
		if (fmtPlainBody) {
			send("");
		}
		else
			closeRow();
	}
	else {
			send("");
	}
		// manage totalizers row counters
	for (int i = 0; i < schema->totV.count(); i++) {
		subTotals[i].rowCount++;
	}
}

void ReportFormatter::checkRowTriggers()
{
	int printedKParts = 0;
	int printedKLengths = 0;
	int columnIndex = -1;

	triggeredChangeOnFirstKey = false;
	for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it) {
		++columnIndex;
		if ((*it).data.u.body.source == ReportSchemaObj::dsUndefined) {
			continue;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::KeyPart) {
			if ((*it).data.u.body.idKey >= relationSize)	// do not place key parts id greater then relation size
				continue;
			QString part = currentKey.mid(printedKLengths, keyLengths[printedKParts]);
			printedKLengths += keyLengths[printedKParts];
// 			lout << "KPART#" << printedKParts << endl;
			if (printedKParts == 0)
				printedKLengths += (params.data.FlagSort) ? SORT_SHIFT : 0;

			// hide or show key values ??
			if (!keyAlwaysShow[printedKParts] && (*it).data.u.body.repeatKey) keyAlwaysShow[printedKParts] = true;

			if (hiddenKey[printedKParts]) {
				++printedKParts;
				continue;
			}
// 			lout << "key: " << currentKey << " part: " << part << " printedKLengths: " << printedKLengths << " klen:"<< keyLengths[printedKParts]<<endl;

			// key evaluation is a: not-so-simple-task (tm)...
			// key value needs to be evaluated for key change and for always show requests too, but the two operations differs.
			// so how to solve ? well, we enter in the next if in both cases then we add a flag to make differences. this
			// makes the code a little "dirty" but this seems the only way...
			bool isChanged = (part != keyStore[printedKParts]);
			if (isChanged || keyAlwaysShow[printedKParts]) {

/*				lout << "-- PRIMA ---------------------------" << endl;
				lout << "helper k1=" << defineRelation.getRelationComponent(printedKParts)->desc << " k2="<< part << endl;
				lout << "keyStore " << keyStore[printedKParts] << " printedKParts "<<printedKParts<<endl;
// 				lout << "cellStore " << cellStore[columnIndex] << " columnIndex "<<columnIndex<<endl;
				lout << "------------------------------------" << endl;*/
				int triggeredTotalizerIndex = -1;

				if (isChanged) {	// is really a key change ??

					if (printedKParts == 0 && fmt3d)
						triggeredChangeOnFirstKey = true;
					// manage totalizers
					for (int i = 0; i < schema->totV.count(); i++) {
						if (subTotals[i].rso->data.u.totalizer.trigger == (*it).data.idObject && keyStore[printedKParts] != "") {
	// 						lout << "TRIGGERED! on subtotal " << i << " id " << subTotals[i].rso->data.u.totalizer.trigger << endl;
							subTotals[i].triggered = true;
							triggeredTotalizerIndex = i;
						}
					}
					keyStore[printedKParts] = part;
				}

				if (keyPlugin[printedKParts] > 0) {		// managed by plugin
					QString result;
					char ikbuf[129];

					qstrncpy(ikbuf, qPrintable(part), 128);
					pld.operation = teOp_ReportProduction;
					pld.inKeyValue = ikbuf;
					pld.offset = defineRelation.getRelationComponent(printedKParts)->id;
					pld.CDRr = (DRImageHandler *)&currentKey;
					keyPlugin[printedKParts]->pluginWorker((void *)&pld);

					if (pld.status == teStatus_Error)
						result = part + "(P!)";
					else
						result = pld.inKeyValue;

					cellStore[columnIndex] = result;
				}
				else if (keyScript[printedKParts] > 0) {		// managed by script
					QString result;
					if (!callBasic_TE(part, currentKey, keyScript[printedKParts], result))
						result = part + "(S!)";
					cellStore[columnIndex] = result;
				}
				else if (params.data.HexValue && hexKey[printedKParts]) {
					if ((printedKParts == 0 && (params.data.HexValue == 1 || params.data.HexValue == 3)) ||
					    (printedKParts > 0 && (params.data.HexValue == 2 || params.data.HexValue == 3)) ) {
						cellStore[columnIndex] = cellStore[columnIndex].setNum(part.toULong(), 16).toUpper();
					}
					else
						cellStore[columnIndex] = part;
				}
				else
					cellStore[columnIndex] = part;
				if (keyHelperPresent[printedKParts]) {
					QString * htxt = (*helper)[defineRelation.getRelationComponent(printedKParts)->desc]->items[part];
					if (htxt) {
						cellStore[columnIndex] = (*htxt);
					}
				}
				else {
					QString itxt = checkInternalHelp(defineRelation.getRelationComponent(printedKParts)->desc, part);
					if (itxt.length() > 0)
						cellStore[columnIndex] = itxt;
				}

//				lout << "-- DOPO ---------------------------" << endl;
//				lout << "helper =" << defineRelation.getRelationComponent(printedKParts)->desc << " k='"<< part <<"'"<< endl;
//				lout << "keyStore " << keyStore[printedKParts] << " printedKParts "<<printedKParts<<endl;
// 				lout << "cellStore " << cellStore[columnIndex] << " columnIndex "<<columnIndex<<endl;
//				lout << "------------------------------------" << endl;

				// reset next level
				if (isChanged) {	// this too is required just on key change...
					if (printedKParts + 1 < relationSize) {
						keyStore[printedKParts + 1] = "";
	/*					for (int i = 0; i < schema->totV.count(); i++) {	// flush subtotalizers at the end of the report
							if (i > triggeredTotalizerIndex) {
								handleSubTotalRow(subTotals[i]);
								lout << "**** FORZO SUBTOTALE " << i << endl;
								subTotals[i].reset();
							}
						}*/
					}
				}
			}
			else if (schema->objO.data.u.object.excelCSV == rfex_None) {
				if (!fmtPlainBody)
					cellStore[columnIndex] = "&nbsp;";
				else
					cellStore[columnIndex] = "";
			}
			++printedKParts;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::User) {
			continue;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::CounterKit && (*it).data.u.body.idCounter > 0) {
			double cellValue = getCounterValue(kit->data.counterKit[(*it).data.u.body.idCounter-1].counterIndex);
			for (int i = 0; i < schema->totV.count(); i++) {
				if (subTotals[i].rso->data.u.totalizer.trigger == (*it).data.idObject) {
					subTotals[i].check(cellValue);
				}
			}
		}
	}
}

QString ReportFormatter::checkInternalHelp(const QString & tag, const QString & id)
{
	DataElement * te = config.dataElementInterface->getByTag(tag);
	if (te == 0) {
		return QString("");
	}
	if (te->data.LengthInRelation == 0 || (te->data.guiObject != DataElement::ValueList && te->data.guiObject != DataElement::OptionList)) {
		return QString("");
	}

	QString key;
// 	QString fmt = QString("%0") + QString::number(te->data.LengthInRelation) + QString("d");

	for (int i = 0; i < MAX_OPTIONS; i++) {
		if (qstrlen(te->data.valueListLabel[i]) > 0) {
// 			key.sprintf(fmt, QString::number(te->data.valueList[i]).toInt());
			key = QString("%1").arg(QString::number(te->data.valueList[i]), te->data.LengthInRelation, '0' );
			if (key == id) {
				return QString(te->data.valueListLabel[i]);
			}
		}
		else
			break;
	}

	return QString("");
}


bool ReportFormatter::callBasic_TE(const QString & keyPart, const QString & currentKey, AdamsBasic * nb, QString & result)
{
	if (nb == 0) {
		lout << "NtmMasterServer : ReportFormatter::callBasic_TE : Called with non-instantiated AdamsBasic reference." << endl;
		return false;
	}
	const Script * scr = nb->getOriginatingScript();
	if (scr == 0) {
		lout << "NtmMasterServer : ReportFormatter::callBasic_TE : Called with no reference to originating script." << endl;
		return false;
	}
		// valorize var list
	bool justifyAtLeft = false;

	nb->setValue("keypart", keyPart);
	nb->setValue("currentkey", currentKey);
	nb->setValue("pivot_running", (long)0);
			// Execute
	if (!nb->execute()) {
		return false;
	}
			// Get result
	if (!nb->getResult(result))
		return false;

	return true;
}

bool ReportFormatter::callBasic_TOT(const TotalData &  o, int idKey, QString & result)
{
	if (o.sHndl == 0) {
		lout << "NtmMasterServer : ReportFormatter::callBasic_TOT : Called with non-instantiated AdamsBasic reference." << endl;
		return false;
	}
// 	const Script * scr = o.sHndl->getOriginatingScript();
// 	if (scr == 0) {
// 		lout << "NtmMasterServer : ReportFormatter::callBasic_TOT : Called with no reference to originating script." << endl;
// 		return false;
// 	}
// 		// valorize var list
// 	bool justifyAtLeft = false;
	DataElement * te = 0;
	QString d;
	if (defineRelation.getRelationComponent(idKey))
		te = config.dataElementInterface->get(defineRelation.getRelationComponent(idKey)->id);
	if (te)
		d = te->data.shortDescription;
	else
		d = defineRelation.getRelationComponent(idKey)->desc;

	QString relstr = config.relationInterface->decodeRel(params.data.Relation, params.data.ffRelation);
	o.sHndl->setValue("ttot_refid", d.toLower());
	o.sHndl->setValue("tnum_rows", (long)o.rowCount);
	o.sHndl->setValue("trelation_str", relstr.toLower());
	o.sHndl->setValue("pivot_running", (long)0);
			// Execute
	if (!o.sHndl->execute()) {
		return false;
	}
			// Get result
	if (!o.sHndl->getResult(result)) {
		return false;
	}

	return true;
}


void ReportFormatter::handleSubTotalRow(TotalData & td)
{
	QString cellText;
	int printedKParts = 0;
	int columnIndex = -1;
	int size;
	bool justLeft;
	ReportSchemaObj trso = *td.rso, o;
	int tbox = (fmt3d) ? 1 : 0;

	if (td.rowCount < 2) {
		if (fmtPlainBody) {
			cellText.fill(plainLineChar, plainHdrLineSize);
			send(cellText);
		}
		else if (schema->objO.data.u.object.excelCSV == rfex_None) {
			openRow();
			openCell(0,"&nbsp;");
			closeRow();
		}
		return;
	}

	if (fmtPlainBody) {
		cellText.fill(plainLineChar, plainHdrLineSize);
		send(cellText);
		send(QChar(plainHdrSepChar), false);
	}
	else
		openRow();

	for (RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it) {
		++columnIndex;
		justLeft = false;
		o = (*it);
		if (o.data.u.body.source == ReportSchemaObj::dsUndefined) {
			continue;
		}
		else if (o.data.u.body.source == ReportSchemaObj::KeyPart) {
			if (o.data.u.body.idKey >= relationSize)	// do not place key parts id greater then relation size
				continue;
			if (fmt3d && printedKParts == 0){
				++printedKParts;
				continue;
			}

			if (printedKParts == tbox) {
				if (td.sHndl > 0) {		// managed by script
					QString result;
					if (!callBasic_TOT(td, o.data.u.body.idKey, result))
						result = "SCRIPT TOTAL (S!)";
					cellText = result;
				}
				else
					cellText = trso.data.u.totalizer.label;
			}
			else {
				cellText = "&nbsp;";
			}
			if (hiddenKey[printedKParts]) {
				++printedKParts;
				continue;
			}
			++printedKParts;
			size = plainKeyFldSize;
			justLeft = true;
		}
		else if (o.data.u.body.source == ReportSchemaObj::User) {
			cellText = "&nbsp;";
			size = cellText.length() + 1;
		}
		else if ((*it).data.u.body.source == ReportSchemaObj::Script && bdyScripts[columnIndex].sHndl) {
			trso.data.html.hAlign = ReportSchemaObj::Right;
				// manage totalizers
			if (td.pool[columnIndex] == 0) {		// unused total counter
				cellText = "&nbsp;";
			}
			else {
				cellText = QString::number(*td.pool[columnIndex], 'f', (*it).data.u.body.decimals);
			}
			size = plainCntFldSize;
		}
		else if (o.data.u.body.source == ReportSchemaObj::CounterKit && o.data.u.body.idCounter > 0) {
			trso.data.html.hAlign = ReportSchemaObj::Right;
				// manage totalizers
			if (td.pool[columnIndex] == 0) {		// unused total counter
				cellText = "&nbsp;";
			}
			else {
// 				int prec = (getCounterType(kit->data.counterKit[o.data.u.body.idCounter-1].counterIndex) == INT_TYPE) ? 0 : 2;
				int prec = (*it).data.u.body.decimals;
				if (td.percentCross[columnIndex] != 0) {
					if (*td.percentCross[columnIndex] != 0)
						cellText = QString::number(((*td.pool[columnIndex] * 100.0) / *td.percentCross[columnIndex]), 'f', 2);
				}
				else
					cellText = QString::number(*td.pool[columnIndex], 'f', prec);
			}
			size = plainCntFldSize;
		}
			// user don't want subtotals for this column
		if (o.data.u.body.noTotals) {
			cellText = "&nbsp;";
		}
			// evaluate how to draw the cell
		if (fmtPlainBody) {
			if (cellText == "&nbsp;")
				cellText = "";
			if (justLeft)
				send(cellText.leftJustified(size, ' ', true)+QString(QChar(plainHdrSepChar)), false);
			else
				send(cellText.rightJustified(size, ' ', true)+QString(QChar(plainHdrSepChar)), false);
		}
		else {
			if (schema->objO.data.u.object.hasBorder == false && trso.data.u.totalizer.border) {
				openCell(0,0,false); openTable(true); openRow();
				openCell(&trso, cellText);
				closeRow(); closeTable(); closeCell(0);
			}
			else
				openCell(&trso, cellText);
		}
	}
	if (fmtPlainBody) {
		send("");
		cellText.fill(plainLineChar, plainHdrLineSize);
		send(cellText);
	}
	else
		closeRow();
	if (trso.data.u.totalizer.redrawHeader)
		doBodyHdr = true;
}


/**
Estrae il valore di un contatore dal kit corrente
	@param contatore Indice del contatore
*/
double ReportFormatter::getCounterValue(int idx, ParameterList * cntRow)
{
	if (cntRow == 0)
		cntRow = countersData;
	switch (getCounterType(idx)) {
		case INT_TYPE:
			if (idx > numIntCounters) {
				lout << "ReportFormatter::getCounterValue : WARNING : Out of INTEGERS counters bounds index: " << idx << endl;
				return -1.0;
			}
			else
				return (double)cntRow->int_counters[idx-1];
		case DOUBLE_TYPE:
			idx -= MAX_INT_COUNTERS;
			if (idx > numDblCounters) {
				lout << "ReportFormatter::getCounterValue : WARNING : Out of FLOATING POINT counters bounds index: " << idx << endl;
				return -1.0;
			}
			else
				return cntRow->dbl_counters[idx-1];
		default:
			lout << "ReportFormatter::getCounterValue : WARNING : Misaligned counter index: " << idx << endl;
			return -1.0;
	}
}

/** Deriva l'indice del contatore sulla base del tag */

int ReportFormatter::getCounterIndex(const QString & cntTag, int & tagi)
{
	for (int i = 0; i < CNT_NUM; i++) {
		if (qstrlen(kit->data.counterKit[i].tag) == 0)
			break;
		else if (cntTag == kit->data.counterKit[i].tag) {
			tagi = i;
			return kit->data.counterKit[i].counterIndex;
		}
	}

	return -1;
}

bool ReportFormatter::callBasic_BODY(int bdyidx, ParameterList * cntRow, QString & result, int ndec)
{
	if (bdyScripts[bdyidx].sHndl == 0) {
		lout << "NtmMasterServer : ReportFormatter::callBasic_BODY : Called with non-instantiated AdamsBasic reference." << endl;
		return false;
	}

	int vari = 0, i;
	while((i = bdyScripts[bdyidx].varsIndex[vari]) >= 0) {
		int t = bdyScripts[bdyidx].tagsIndex[vari];
		bdyScripts[bdyidx].sHndl->setValue(QString(kit->data.counterKit[t].tag).toLower(),
		                                   (double)getCounterValue(i, cntRow));
//		lout << "setto valore: " << QString(kit->data.counterKit[t].tag).toLower() << " a " << (double)getCounterValue(i, cntRow) << endl;
		++vari;
	}
	bdyScripts[bdyidx].sHndl->setValue("pivot_running", (long)0);
			// Execute
	if (!bdyScripts[bdyidx].sHndl->execute()) {
		lout << "ERRORE EXEC BASIC" << endl;
		return false;
	}
			// Get result
	long iresult;
	double dresult;
	if (bdyScripts[bdyidx].sHndl->getResult(dresult)) {
		result = QString::number(dresult, 'f', ndec);
		return true;
	}
	else if (bdyScripts[bdyidx].sHndl->getResult(iresult)) {
		result = QString::number(iresult);
		return true;
	}
	else if (!bdyScripts[bdyidx].sHndl->getResult(result)) {
//		lout << "ERRORE GETRESULT BASIC" << endl;
		return false;
	}

	return true;
}


/**
Emette le righe di intestazione standard (<head>) del documento html ed inizializza la sequenza del corpo.
*/
void ReportFormatter::htmlStartSequence(const ReportSchemaObj & o)
{
	send("<html>");
	send("<head>");
	send("  <title></title>");
	send("  <meta http-equiv=\"content-type\"");
	send("  content=\"text/html; charset=ISO-8859-1\">");
	send(QString("  <meta name=\"generator\" content=\"ADAMS MDM Module"));
	send("</head>");
	if (o.data.type == ReportSchemaObj::ObjectT) {
		if (o.data.u.object.defaultForeground.r < 0)
			send("<body>");
		else {
			send(QString("<body text=\"#") +
			     QString::number(o.data.u.object.defaultForeground.r, 16).rightJustified(2, '0') +
			     QString::number(o.data.u.object.defaultForeground.g, 16).rightJustified(2, '0') +
			     QString::number(o.data.u.object.defaultForeground.b, 16).rightJustified(2, '0') +
			     QString("\" bgcolor=\"#") +
			     QString::number(o.data.u.object.defaultBackground.r, 16).rightJustified(2, '0') +
			     QString::number(o.data.u.object.defaultBackground.g, 16).rightJustified(2, '0') +
			     QString::number(o.data.u.object.defaultBackground.b, 16).rightJustified(2, '0') +
			     QString("\">"));
		}
	}
	else
		send("<body>");
	send("<br>");

}

/**
Emette i tag html do chiusura documento
*/
void ReportFormatter::htmlEndSequence()
{
	send("</body>");
	send("</html>");
}

/**
Emette la sequenza di apertura di una tabella HTML
	@param border Flag abilitazione bordo (fisso a 1 pixel)
	@param indentLevel Livello indentazione codice HTML
*/
void ReportFormatter::openTable(bool border, int width, int padding, int spacing, const char * other)
{
	++indentLevel;

	QString user = (other == 0) ? QString("") : QString(other);

	send(indent() +
	     QString("<table ") +
	     user +
	     QString(" cellpadding=\"") +
	     QString::number(padding) +
	     QString("\" cellspacing=\"") +
	     QString::number(spacing) +
	     QString("\" border=\"") +
	     QString::number(border) +
	     QString("\" width=\"") +
	     QString::number(width) +
	     QString("%\">"));
	++indentLevel;
	send(indent() + "<tbody>");
}

/**
Emette la sequenza di chiusura di una Tabella

*/
void ReportFormatter::closeTable()
{
	send(indent() + "</tbody>");
	--indentLevel;
	send(indent() + "</table>");
	--indentLevel;
}

/**
Emette la sequenza HTML per lo start di una nuova riga in tabella.
*/
void ReportFormatter::openRow()
{
	++indentLevel;
	send(indent() + "<tr>");
}

/**
Emette la sequenza di chiusura di una riga di tabella.
*/
void ReportFormatter::closeRow()
{
	send(indent() + "</tr>");
	--indentLevel;
}

/**
Emette la sequenza di apertura di una cella. I parametri di visualizzazione soono gestiti da configurazione.
	@param rappresentazione Oggetto contenuto da cui prelevare la modalita' di rappresentazione
*/
void ReportFormatter::openCell( const ReportSchemaObj* o, const QString & txt, bool closeit, bool header )
{
	QString s;

	++indentLevel;
	s = (header) ? "<th" : "<td";
	if (o) {
		if (o->data.html.vSize) {
			s += QString(" height=\"") + QString::number(o->data.html.vSize) + "\"";
		}
		if (o->data.html.hSize) {
			s += QString(" width=\"") + QString::number(o->data.html.hSize) + "%\"";
		}
		s += QString(" valign=\"") + QString(VertAlignTranslate[o->data.html.vAlign]) + "\"";
		s += QString(" align=\"") + QString(HoriAlignTranslate[o->data.html.hAlign]) + "\"";
		if (o->data.html.wrap == false) {
			s += QString(" nowrap=\"nowrap\"");
		}
		if (o->data.html.background.r >= 0) {
			s += QString(" bgcolor=\"#") +
			     QString::number(o->data.html.background.r, 16).rightJustified(2, '0') +
			     QString::number(o->data.html.background.g, 16).rightJustified(2, '0') +
			     QString::number(o->data.html.background.b, 16).rightJustified(2, '0') +
			     QString("\"");
		}
		s += ">"; // single tag switches follow
		if (o->data.html.foreground.r >= 0) {
			s += QString("<font color=\"#") +
			     QString::number(o->data.html.foreground.r, 16).rightJustified(2, '0') +
			     QString::number(o->data.html.foreground.g, 16).rightJustified(2, '0') +
			     QString::number(o->data.html.foreground.b, 16).rightJustified(2, '0') +
			     QString("\">");
		}
		if (o->data.html.style != ReportSchemaObj::BodyText) {
			switch (o->data.html.style) {
				case ReportSchemaObj::Paragraph: s += "<p>"; break;
				case ReportSchemaObj::Title1: s += "<h1>"; break;
				case ReportSchemaObj::Title2: s += "<h2>"; break;
				case ReportSchemaObj::Title3: s += "<h3>"; break;
				case ReportSchemaObj::Title4: s += "<h4>"; break;
				case ReportSchemaObj::Title5: s += "<h5>"; break;
				case ReportSchemaObj::Title6: s += "<h6>"; break;
				case ReportSchemaObj::Address: s += "<address>"; break;
				case ReportSchemaObj::Preformatted: s += "<pre>"; break;
				default: break;
			}
		}
		if (o->data.html.fontSize) {
			if (o->data.html.fontSize < 0) {
				for (int i = 0; i < -o->data.html.fontSize; i++)
					s += "<small>";
			}
			else {
				for (int i = 0; i < o->data.html.fontSize; i++)
					s += "<big>";
			}
		}
		if (o->data.html.bold) {
			s += "<b>";
		}
		if (o->data.html.italic) {
			s += "<i>";
		}
		if (o->data.html.underline) {
			s += "<u>";
		}
	}
	else
		s += ">";
	if (!txt.isEmpty()) {
		s += txt;
	}
	if (closeit) {
		s += closeCellHtml(o);
	}
	if (closeit) {
		send(indent() + s + "</td>");
		--indentLevel;
	}
	else {
		send(indent() + s);
	}
}

/**
Emette la sequenza di chiusura di una cella
*/
void ReportFormatter::closeCell(const ReportSchemaObj * o)
{
	QString s = closeCellHtml(o);

	send(indent() + s + "</td>");
	--indentLevel;
}

/**
Ritorna le sequenza di chiusura necessarie a terminare gli attrubuti a singolo tag di una cella.
*/
QString ReportFormatter::closeCellHtml(const ReportSchemaObj * o)
{
	QString s = "";

	if (o == 0)
		return s;

	if (o) {
		if (o->data.html.underline) {
			s += "</u>";
		}
		if (o->data.html.italic) {
			s += "</i>";
		}
		if (o->data.html.bold) {
			s += "</b>";
		}
		if (o->data.html.fontSize) {
			if (o->data.html.fontSize < 0) {
				for (int i = 0; i < -o->data.html.fontSize; i++)
					s += "</small>";
			}
			else {
				for (int i = 0; i < o->data.html.fontSize; i++)
					s += "</big>";
			}
		}
		if (o->data.html.style != ReportSchemaObj::BodyText) {
			switch (o->data.html.style) {
				case ReportSchemaObj::Paragraph: s += "</p>"; break;
				case ReportSchemaObj::Title1: s += "</h1>"; break;
				case ReportSchemaObj::Title2: s += "</h2>"; break;
				case ReportSchemaObj::Title3: s += "</h3>"; break;
				case ReportSchemaObj::Title4: s += "</h4>"; break;
				case ReportSchemaObj::Title5: s += "</h5>"; break;
				case ReportSchemaObj::Title6: s += "</h6>"; break;
				case ReportSchemaObj::Address: s += "</address>"; break;
				case ReportSchemaObj::Preformatted: s += "</pre>"; break;
				default: break;
			}
		}
		if (o->data.html.foreground.r >= 0) {
			s += QString("</font>");
		}
	}
	return s;
}

/**
Produce l'output necessario all'interpretazione degli eventi header programmati dall'utente per il report.
	@param trattare Ogetto/evento da trattare
*/
void ReportFormatter::handleHeaderEvents(const ReportSchemaObj &  o)
{
		// Fixed header events (logo / title) are not managed here
	if (o.data.u.header.value == ReportSchemaObj::Logo || o.data.u.header.value == ReportSchemaObj::Title)
		return;
		// else...
	ReportSchemaObj guideO = o;
	// is a breaker: call handler
	if (o.data.u.header.line != ReportSchemaObj::NoBreak) {
		handleBreakerEvents(o);
		return;
	}
		// open container cell with a table embedded if user setup a label
	QString cellText;
	if (!QString(o.data.u.header.userLabel).isEmpty()) {
		guideO.data.html.hSize = 50;
		openCell(&o, 0, false);
		openTable(false);
		openRow();
			// label (50% of space)
		cellText = QString(o.data.u.header.userLabel);
		openCell(&guideO, cellText);
	}
	cellText = "";
		// value (50% of space)
	switch (o.data.u.header.value) {
		case ReportSchemaObj::UserDefined:
			if (o.data.u.header.isUrl) {
				cellText = placeUrl(o.data.u.header.userValue);
			}
			else
				cellText = QString(o.data.u.header.userValue);
			break;
		case ReportSchemaObj::RestrictionsList:
			{
				if (params.data.Filters.isEmpty())
					break;
				if (params.data.Filters.count() == 0)
					break;
				foreach ( QueryParams::Rest r, params.data.Filters ) {
					if (r.Value[0] < 0)
						break;
					DataElement * te = config.dataElementInterface->get(r.Element);
					if (te == 0)
						break;
					cellText += QString(te->data.longDescription)
					          + QString(" : ")
					          + expandRestrictionDescription(&r);
					int p = 1;
					while (r.Value[p] >= 0) {
						cellText += QString(", ") + QString(r.AsciiValue[p]);
						++p;
					}
					cellText += QString("<br>");
				}
			}
			guideO.data.html.wrap = true;
			break;
		case ReportSchemaObj::RelationName:
			cellText = config.relationInterface->decodeRel(params.data.Relation, params.data.ffRelation);
			if (params.data.RelationDirection < usrRelationKind_numRelationKind)
				cellText += QString("<br>[") + QString(descRelationDirection[params.data.RelationDirection]) + "]";
			guideO.data.html.wrap = true;
			break;
		case ReportSchemaObj::Network:
			cellText = (params.data.NetworkRealTime) ? "Yes" : "No";
			break;
		case ReportSchemaObj::DataType:
			if (params.data.DataType < usrDataType_numTypes)
				cellText = QString(descDataType[params.data.DataType]);
			else
				cellText = "UNKNOWN";
			break;
		case ReportSchemaObj::SortType:
			if (params.data.FlagSort) {
				cellText = (params.data.Ascendent) ? "Ascending" : "Descending";
				cellText += QString(" on ") + QString(kit->data.counterKit[params.data.ElementToSort - 1].description);
			}
			else
				cellText = "None";
			break;
		case ReportSchemaObj::DataFormat:
			cellText = (params.data.IsPercent) ? "Percentage" : "Absolute";
			break;
		case ReportSchemaObj::AnalisysName:
			cellText = analysis->data.LongDescription;
			break;
		case ReportSchemaObj::NumberKeys:
			if (directTree)
				cellText = QString::number(directTree->count()) + QString(" directs");
			if (directTree && inverseTree) {
				cellText += "<br>";
				guideO.data.html.wrap = true;
			}
			if (inverseTree)
				cellText += QString::number(inverseTree->count()) + QString(" inverses");
			break;
		case ReportSchemaObj::ReportName:
			cellText = QString(schema->objO.data.u.object.tag) + QString("<br>[") + QTime::currentTime().toString() + "]";
			guideO.data.html.wrap = true;
			break;
		case ReportSchemaObj::Nodes:
			if (nodeIndex < 0) { // network
				for (int i = 0; i < MAX_FEP; i++)
					if (params.data.Fep[i])
						cellText += QString(nodeConfigHandler.getInputNodeList().at(i).acName) + QString(" ");
				guideO.data.html.wrap = true;
			}
			else { // just one
				cellText = nodeConfigHandler.getInputNodeList().at(nodeIndex).acName;
			}
			break;
		case ReportSchemaObj::ElaborationDate:
			for (QStringList::ConstIterator de = params.data.ElaborationData.begin(); de != params.data.ElaborationData.end(); ++de) {
				cellText += (*de).mid( 6, 2 ) + QString("/") + (*de).mid( 4, 2 ) + QString("/") + (*de).mid( 0, 4 ) + QString(" ");
			}
			guideO.data.html.wrap = true;
			break;
		default:
			cellText = QString("Unmanaged Field!");
			break;
	}
	if (!QString(o.data.u.header.userLabel).isEmpty()) {
		openCell(&guideO, cellText);
			// now close container cell
		closeRow();
		closeTable();
		closeCell(&o);
	}
	else
		openCell(&guideO, cellText);
}

/**
Valuta la Url utente per determinare se e' riferita ad una immagine
	@param utente URL utente
*/
bool ReportFormatter::checkForImageUrl(const QString & url)
{
	if (url.toLower().endsWith(".gif") ||
	    url.toLower().endsWith(".png") ||
	    url.toLower().endsWith(".jpg") ||
	    url.toLower().endsWith(".jpeg"))
		return true;
	else
		return false;
}

/**
Ritorna la sequenza HTML necessaria a trattare una URL.
	@param utente URL utente
*/
QString ReportFormatter::placeUrl(const QString & url)
{
	QString cellText;
	if (checkForImageUrl(url))
		cellText = QString("<img src=\"") + url + QString("\" alt=\"\" border=\"0\">");
	else
		cellText = QString("<a href=\"") + url + QString("\">") + url;
	return cellText;
}

/**
Usato per espandere le descrizioni associate ad OptionList.
	@param utente Indice selezione utente
*/
QString ReportFormatter::expandRestrictionDescription(QueryParams::Rest * r)
{
	if (config.dataElementInterface->get(r->Element)->data.guiObject == DataElement::OptionList) {
		int i = 0;
		while (i < MAX_OPTIONS) {
			if (config.dataElementInterface->get(r->Element)->data.valueList[i] == r->Value[0])
				return QString(config.dataElementInterface->get(r->Element)->data.valueListLabel[i]);
			++i;
		}
	}
	return QString(r->AsciiValue[0]);
}

/**
Gestione degli eventi di break sulle righe del report
*/
void ReportFormatter::handleBreakerEvents(const ReportSchemaObj &  o)
{
	ReportSchemaObj::BreakingType brkType;

	if (o.data.type == ReportSchemaObj::HeaderT)
		brkType = o.data.u.header.line;
	else if (o.data.type == ReportSchemaObj::BodyT)
		brkType = o.data.u.body.line;
	else if (o.data.type == ReportSchemaObj::TotalizerT)
		brkType = o.data.u.totalizer.line;
	else if (o.data.type == ReportSchemaObj::FooterT)
		brkType = o.data.u.footer.line;
	else // unknown
		return;

	switch (brkType) {
		case ReportSchemaObj::BreakLine:
			closeRow();
			//send(indent() + "<br>");
			openRow();
			break;
		case ReportSchemaObj::BlankLine:
			if (o.data.type == ReportSchemaObj::HeaderT) {
				closeRow();
				closeTable();
				send(indent() + "<br>");
				openTable(false);
				openRow();
			}
			else if (o.data.type == ReportSchemaObj::FooterT) {
				openRow();
				openCell(0,"&nbsp;");
				openCell(0,"&nbsp;");
				closeRow();
			}
			else {
				closeRow();
				send(indent() + "<br>");
				openRow();
			}
			break;
		case ReportSchemaObj::LineLine:
			if (o.data.type == ReportSchemaObj::HeaderT) {
				closeRow();
				closeTable();
				send(indent() + "<hr width=\"100%\" size=\"2\">");
				openTable(false);
				openRow();
			}
			else if (o.data.type == ReportSchemaObj::FooterT) {
				openRow();
				openCell(0,0,false);
				send(indent() + "<hr width=\"100%\" size=\"2\">");
				closeCell(0);
				openCell(0,0,false);
				send(indent() + "<hr width=\"100%\" size=\"2\">");
				closeCell(0);
				closeRow();
			}
			else {
				closeRow();
				send(indent() + "<hr width=\"100%\" size=\"2\">");
				openRow();
			}
			break;
		default:
			break;
	}
}

QString ReportFormatter::tempFileName(const QString & prefix)
{
	QString name;

// 	name = QString( tempnam( "/tmp/", "" ) ).remove( "/tmp/" );
	name = QString( tempnam( "/tmp/", "" ) );
	int slpos = name.lastIndexOf ( '/' );

	if ( slpos < 0 ) {
		return prefix + name;
	}
	else {
		return prefix + name.right ( name.length() - slpos - 1 );

	}
}

