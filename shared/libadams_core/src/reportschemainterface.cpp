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
#include <reportschemainterface.h>
#include <applogger.h>

ReportSchemaInterface::ReportSchemaInterface() : uniq ( 0 )
{
	ReportSchemas = new QMultiHash<QString, ReportSchema *>; // (REPORTSCHEMA_MAXFIELDS , true);
	ReportSchemasNumber = 0;
}

ReportSchemaInterface::~ReportSchemaInterface()
{
	if ( ReportSchemas ) {
		clear();
		delete ReportSchemas;
	}
}

/**
  * Inserisce una nuovo schema nel hashtable
  * ed incrementa il numero di relazioni.
**/
bool ReportSchemaInterface::add ( ReportSchema * newRS )
{
	ReportSchemas->insert ( QString::number ( newRS->idReportSchema ), newRS );
	ReportSchemasNumber++;
	return false;
}
/**
  * Rimouve una nuova relazione dal hashtable
  * ed decrementa il numero di relazioni.
**/
bool ReportSchemaInterface::remove ( int RSNum )
{
	ReportSchema *lr = ( *ReportSchemas->find ( QString::number ( RSNum ) ) );
	if ( lr == ( ReportSchema * ) 0 ) {
		return true;
	}
	ReportSchemas->remove ( QString::number ( RSNum ) );
	delete lr;
	ReportSchemasNumber--;
	return false;
}
/**
  * Restituisce una specifica relazione.
**/
ReportSchema * ReportSchemaInterface::get ( int RSNum )
{
	return ( *ReportSchemas->find ( QString::number ( RSNum ) ) );
}

// get by tag

ReportSchema * ReportSchemaInterface::getByTag ( const QString & srcTag )
{
	if ( ReportSchemas->count() > 0 ) {
		ReportSchema * rs;
		foreach ( rs, *ReportSchemas ) {
			if ( rs->objO.data.u.object.tag == srcTag )
				return rs;
		}
	}
	return ( ReportSchema * ) 0;
}




// crea lista per tag

TagList ReportSchemaInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( ReportSchemas->count() > 0 ) {
		ReportSchema * rs;
		foreach ( rs, *ReportSchemas ) {
			tagList.values.append ( QString::number ( rs->idReportSchema ) );
			tagList.labels.append ( rs->objO.data.u.object.tag );
		}
	}

	return tagList;
}

/**
  * Ritorna il Tag relativo alle relazioni.
**/
const char * ReportSchemaInterface::getHeaderTag()
{
	static const char *ht = REPORTSCHEMA_HEADERTAG;

	return ht;
}

// setup i/e tags

void ReportSchemaInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void ReportSchemaInterface::ieExport ( ImportExport & ie )
{
	if ( ReportSchemas->count() > 0 ) {
		ReportSchema * rs;
		foreach ( rs, *ReportSchemas ) {
			int idRep = rs->idReportSchema;
			// objO
			ieHandleObjWrite ( ie, ReportSchemaObj::ObjectT, idRep, rs->objO );
			// hdrV
			for ( RSOVectorIterator i = rs->hdrV.begin(); i != rs->hdrV.end(); ++i ) {
				ieHandleObjWrite ( ie, ReportSchemaObj::HeaderT, idRep, ( *i ) );
			}
			// bdyV
			for ( RSOVectorIterator i = rs->bdyV.begin(); i != rs->bdyV.end(); ++i ) {
				ieHandleObjWrite ( ie, ReportSchemaObj::BodyT, idRep, ( *i ) );
			}
			// totV
			for ( RSOVectorIterator i = rs->totV.begin(); i != rs->totV.end(); ++i ) {
				ieHandleObjWrite ( ie, ReportSchemaObj::TotalizerT, idRep, ( *i ) );
			}
			// fooV
			for ( RSOVectorIterator i = rs->fooV.begin(); i != rs->fooV.end(); ++i ) {
				ieHandleObjWrite ( ie, ReportSchemaObj::FooterT, idRep, ( *i ) );
			}
		}
	}
}

// do export common task

void ReportSchemaInterface::ieHandleObjWrite ( ImportExport & ie, ReportSchemaObj::CellObject t, int id, ReportSchemaObj & d )
{
	ie.initWriteRecord ( REPORTSCHEMA_HEADERTAG );

	ie.setWriteTag ( "idReportSchema" );
	ie.addWriteRecord ( id );
	ie.setWriteTag ( "CELLOBJECT" );
	ie.addWriteRecord ( t );
	ie.setWriteTag ( "idObject" );
	ie.addWriteRecord ( d.data.idObject );
	ie.setWriteTag ( "TAG" );
	ie.addWriteRecord ( d.data.tag );
	ie.setWriteTag ( "html.vSize" );
	ie.addWriteRecord ( d.data.html.vSize );
	ie.setWriteTag ( "html.hSize" );
	ie.addWriteRecord ( d.data.html.hSize );
	ie.setWriteTag ( "html.vAlign" );
	ie.addWriteRecord ( d.data.html.vAlign );
	ie.setWriteTag ( "html.hAlign" );
	ie.addWriteRecord ( d.data.html.hAlign );
	ie.setWriteTag ( "html.wrap" );
	ie.addWriteRecord ( d.data.html.wrap );
	ie.setWriteTag ( "html.foreground.r" );
	ie.addWriteRecord ( d.data.html.foreground.r );
	ie.setWriteTag ( "html.foreground.g" );
	ie.addWriteRecord ( d.data.html.foreground.g );
	ie.setWriteTag ( "html.foreground.b" );
	ie.addWriteRecord ( d.data.html.foreground.b );
	ie.setWriteTag ( "html.background.r" );
	ie.addWriteRecord ( d.data.html.background.r );
	ie.setWriteTag ( "html.background.g" );
	ie.addWriteRecord ( d.data.html.background.g );
	ie.setWriteTag ( "html.background.b" );
	ie.addWriteRecord ( d.data.html.background.b );
	ie.setWriteTag ( "html.style" );
	ie.addWriteRecord ( d.data.html.style );
	ie.setWriteTag ( "html.fontSize" );
	ie.addWriteRecord ( d.data.html.fontSize );
	ie.setWriteTag ( "html.bold" );
	ie.addWriteRecord ( d.data.html.bold );
	ie.setWriteTag ( "html.italic" );
	ie.addWriteRecord ( d.data.html.italic );
	ie.setWriteTag ( "html.underline" );
	ie.addWriteRecord ( d.data.html.underline );
	switch ( t ) {
		case ReportSchemaObj::ObjectT:
			ie.setWriteTag ( "object.tag" );
			ie.addWriteRecord ( d.data.u.object.tag );
			ie.setWriteTag ( "object.hasBorder" );
			ie.addWriteRecord ( d.data.u.object.hasBorder );
			ie.setWriteTag ( "object.excelCSV" );
			ie.addWriteRecord ( d.data.u.object.excelCSV );
			ie.setWriteTag ( "object.simpleBody" );
			ie.addWriteRecord ( d.data.u.object.simpleBody );
			ie.setWriteTag ( "object.idAnalisys" );
			ie.addWriteRecord ( d.data.u.object.idAnalisys );
			ie.setWriteTag ( "object.usePlugin" );
			ie.addWriteRecord ( d.data.u.object.usePlugin );
			ie.setWriteTag ( "object.pluginName" );
			ie.addWriteRecord ( d.data.u.object.pluginName );
			ie.setWriteTag ( "object.defaultForeground.r" );
			ie.addWriteRecord ( d.data.u.object.defaultForeground.r );
			ie.setWriteTag ( "object.defaultForeground.g" );
			ie.addWriteRecord ( d.data.u.object.defaultForeground.g );
			ie.setWriteTag ( "object.defaultForeground.b" );
			ie.addWriteRecord ( d.data.u.object.defaultForeground.b );
			ie.setWriteTag ( "object.defaultBackground.r" );
			ie.addWriteRecord ( d.data.u.object.defaultBackground.r );
			ie.setWriteTag ( "object.defaultBackground.g" );
			ie.addWriteRecord ( d.data.u.object.defaultBackground.g );
			ie.setWriteTag ( "object.defaultBackground.b" );
			ie.addWriteRecord ( d.data.u.object.defaultBackground.b );
			break;
		case ReportSchemaObj::HeaderT:
			ie.setWriteTag ( "header.userLabel" );
			ie.addWriteRecord ( d.data.u.header.userLabel );
			ie.setWriteTag ( "header.value" );
			ie.addWriteRecord ( d.data.u.header.value );
			ie.setWriteTag ( "header.userValue" );
			ie.addWriteRecord ( d.data.u.header.userValue );
			ie.setWriteTag ( "header.isUrl" );
			ie.addWriteRecord ( d.data.u.header.isUrl );
			ie.setWriteTag ( "header.line" );
			ie.addWriteRecord ( d.data.u.header.line );
			break;
		case ReportSchemaObj::BodyT:
			ie.setWriteTag ( "body.source" );
			ie.addWriteRecord ( d.data.u.body.source );
			ie.setWriteTag ( "body.idKey" );
			ie.addWriteRecord ( d.data.u.body.idKey );
			ie.setWriteTag ( "body.idCounter" );
			ie.addWriteRecord ( d.data.u.body.idCounter );
			ie.setWriteTag ( "body.idScript" );
			ie.addWriteRecord ( d.data.u.body.idScript );
			ie.setWriteTag ( "body.idPlugin" );
			ie.addWriteRecord ( d.data.u.body.idPlugin );
			ie.setWriteTag ( "body.userValue" );
			ie.addWriteRecord ( d.data.u.body.userValue );
			ie.setWriteTag ( "body.isUrl" );
			ie.addWriteRecord ( d.data.u.body.isUrl );
			ie.setWriteTag ( "body.noTotals" );
			ie.addWriteRecord ( d.data.u.body.noTotals );
			ie.setWriteTag ( "body.repeatKey" );
			ie.addWriteRecord ( d.data.u.body.repeatKey );
			ie.setWriteTag ( "body.decimals" );
			ie.addWriteRecord ( d.data.u.body.decimals );
			ie.setWriteTag ( "body.line" );
			ie.addWriteRecord ( d.data.u.body.line );
			break;
		case ReportSchemaObj::TotalizerT:
			ie.setWriteTag ( "totalizer.trigger" );
			ie.addWriteRecord ( d.data.u.totalizer.trigger );
			ie.setWriteTag ( "totalizer.label" );
			ie.addWriteRecord ( d.data.u.totalizer.label );
			ie.setWriteTag ( "totalizer.redrawHeader" );
			ie.addWriteRecord ( d.data.u.totalizer.redrawHeader );
			ie.setWriteTag ( "totalizer.border" );
			ie.addWriteRecord ( d.data.u.totalizer.border );
			ie.setWriteTag ( "totalizer.line" );
			ie.addWriteRecord ( d.data.u.totalizer.line );
			ie.setWriteTag ( "totalizer.useScript" );
			ie.addWriteRecord ( d.data.u.totalizer.useScript );
			ie.setWriteTag ( "totalizer.idScript" );
			ie.addWriteRecord ( d.data.u.totalizer.idScript );
			break;
		case ReportSchemaObj::FooterT:
			ie.setWriteTag ( "footer.source" );
			ie.addWriteRecord ( d.data.u.footer.source );
			ie.setWriteTag ( "footer.label" );
			ie.addWriteRecord ( d.data.u.footer.label );
			ie.setWriteTag ( "footer.userValue" );
			ie.addWriteRecord ( d.data.u.footer.userValue );
			ie.setWriteTag ( "footer.isUrl" );
			ie.addWriteRecord ( d.data.u.footer.isUrl );
			ie.setWriteTag ( "footer.line" );
			ie.addWriteRecord ( d.data.u.footer.line );
			break;
	}

	ie.flushWriteRecord();
}

// import a record from ie stream

void ReportSchemaInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != REPORTSCHEMA_HEADERTAG )		// not a record of mine
		return;

	ReportSchema * rs;
	ReportSchemaObj rso;

	rso.data.idReportSchema = ieptr->getIntToken ( "idReportSchema" );
	rso.data.idObject = ieptr->getIntToken ( "idObject" );
	rso.data.type = ( ReportSchemaObj::CellObject ) ieptr->getIntToken ( "CELLOBJECT" );
	ieptr->getStrToken ( rso.data.tag, "TAG", SHORT_DESC_LEN );


	rso.data.html.vSize = ieptr->getIntToken ( "html.vSize" );
	rso.data.html.hSize = ieptr->getIntToken ( "html.hSize" );
	rso.data.html.vAlign = ( ReportSchemaObj::VertAlign ) ieptr->getIntToken ( "html.vAlign" );
	rso.data.html.hAlign = ( ReportSchemaObj::HoriAlign ) ieptr->getIntToken ( "html.hAlign" );
	rso.data.html.wrap = ieptr->getBoolToken ( "html.wrap" );
	rso.data.html.foreground.r = ieptr->getIntToken ( "html.foreground.r" );
	rso.data.html.foreground.g = ieptr->getIntToken ( "html.foreground.g" );
	rso.data.html.foreground.b = ieptr->getIntToken ( "html.foreground.b" );
	rso.data.html.background.r = ieptr->getIntToken ( "html.background.r" );
	rso.data.html.background.g = ieptr->getIntToken ( "html.background.g" );
	rso.data.html.background.b = ieptr->getIntToken ( "html.background.b" );
	rso.data.html.style = ( ReportSchemaObj::FontStyle ) ieptr->getIntToken ( "html.style" );
	rso.data.html.fontSize = ieptr->getIntToken ( "html.fontSize" );
	rso.data.html.bold = ieptr->getBoolToken ( "html.bold" );
	rso.data.html.italic = ieptr->getBoolToken ( "html.italic" );
	rso.data.html.underline = ieptr->getBoolToken ( "html.underline" );
	switch ( rso.data.type ) {
		case ReportSchemaObj::ObjectT:
			ieptr->getStrToken ( rso.data.u.object.tag, "object.tag", SHORT_DESC_LEN );
			rso.data.u.object.hasBorder = ieptr->getBoolToken ( "object.hasBorder" );
			rso.data.u.object.excelCSV = ieptr->getIntToken ( "object.excelCSV" );
			rso.data.u.object.simpleBody = ieptr->getBoolToken ( "object.simpleBody" );
			rso.data.u.object.idAnalisys = ieptr->getIntToken ( "object.idAnalisys" );
			rso.data.u.object.usePlugin = ieptr->getBoolToken ( "object.usePlugin" );
			ieptr->getStrToken ( rso.data.u.object.pluginName, "object.pluginName", SHORT_PLUGIN_NAME );
			rso.data.u.object.defaultForeground.r = ieptr->getIntToken ( "object.defaultForeground.r" );
			rso.data.u.object.defaultForeground.g = ieptr->getIntToken ( "object.defaultForeground.g" );
			rso.data.u.object.defaultForeground.b = ieptr->getIntToken ( "object.defaultForeground.b" );
			rso.data.u.object.defaultBackground.r = ieptr->getIntToken ( "object.defaultBackground.r" );
			rso.data.u.object.defaultBackground.g = ieptr->getIntToken ( "object.defaultBackground.g" );
			rso.data.u.object.defaultBackground.b = ieptr->getIntToken ( "object.defaultBackground.b" );
			break;
		case ReportSchemaObj::HeaderT:
			ieptr->getStrToken ( rso.data.u.header.userLabel, "header.userLabel", REPO_LABEL_LEN );
			rso.data.u.header.value = ( ReportSchemaObj::HeaderValue ) ieptr->getIntToken ( "header.value" );
			ieptr->getStrToken ( rso.data.u.header.userValue, "header.userValue", REPO_USER_LEN );
			rso.data.u.header.isUrl = ieptr->getBoolToken ( "header.isUrl" );
			rso.data.u.header.line = ( ReportSchemaObj::BreakingType ) ieptr->getIntToken ( "header.line" );
			break;
		case ReportSchemaObj::BodyT:
			rso.data.u.body.source = ( ReportSchemaObj::DataSource ) ieptr->getIntToken ( "body.source" );
			rso.data.u.body.idKey = ieptr->getIntToken ( "body.idKey" );
			rso.data.u.body.idCounter = ieptr->getIntToken ( "body.idCounter" );
			rso.data.u.body.idScript = ieptr->getIntToken ( "body.idScript" );
			rso.data.u.body.idPlugin = ieptr->getIntToken ( "body.idPlugin" );
			ieptr->getStrToken ( rso.data.u.body.userValue, "body.userValue", REPO_USER_LEN );
			rso.data.u.body.isUrl = ieptr->getBoolToken ( "body.isUrl" );
			rso.data.u.body.noTotals = ieptr->getBoolToken ( "body.noTotals" );
			rso.data.u.body.repeatKey = ieptr->getBoolToken ( "body.repeatKey" );
			rso.data.u.body.decimals = ieptr->getIntToken ( "body.decimals" );
			rso.data.u.body.line = ( ReportSchemaObj::BreakingType ) ieptr->getIntToken ( "body.line" );
			break;
		case ReportSchemaObj::TotalizerT:
			rso.data.u.totalizer.trigger = ieptr->getIntToken ( "totalizer.trigger" );
			ieptr->getStrToken ( rso.data.u.totalizer.label, "totalizer.label", REPO_LABEL_LEN );
			rso.data.u.totalizer.redrawHeader = ieptr->getBoolToken ( "totalizer.redrawHeader" );
			rso.data.u.totalizer.border = ieptr->getBoolToken ( "totalizer.border" );
			rso.data.u.totalizer.line = ( ReportSchemaObj::BreakingType ) ieptr->getIntToken ( "totalizer.line" );
			rso.data.u.totalizer.useScript = ieptr->getBoolToken ( "totalizer.useScript" );
			rso.data.u.totalizer.idScript = ieptr->getIntToken ( "totalizer.idScript" );
			break;
		case ReportSchemaObj::FooterT:
			rso.data.u.footer.source = ieptr->getIntToken ( "footer.source" );
			ieptr->getStrToken ( rso.data.u.footer.label, "footer.label", REPO_LABEL_LEN );
			ieptr->getStrToken ( rso.data.u.footer.userValue, "footer.userValue", REPO_USER_LEN );
			rso.data.u.footer.isUrl = ieptr->getBoolToken ( "footer.isUrl" );
			rso.data.u.footer.line = ( ReportSchemaObj::BreakingType ) ieptr->getIntToken ( "footer.line" );
			break;
	}

	if ( ( rs = get ( rso.data.idReportSchema ) ) == 0 ) {		// found new rso series: so allocate a new ReportSchema
		rs = new ReportSchema();
		rs->idReportSchema = rso.data.idReportSchema;
		add ( rs );
	}
	switch ( rso.data.type ) {
		case ReportSchemaObj::ObjectT:
			rs->objO = rso;
			break;
		case ReportSchemaObj::HeaderT:
			rs->hdrV << rso;
			break;
		case ReportSchemaObj::BodyT:
			rs->bdyV << rso;
			break;
		case ReportSchemaObj::TotalizerT:
			rs->totV << rso;
			break;
		case ReportSchemaObj::FooterT:
			rs->fooV << rso;
			break;
	}

	return;
}

void ReportSchemaInterface::copyToCorba ( ReportSchemaSeq * seqptr )
{
	DATA_REPORTSCHEMA rec;

	seqptr->length ( count() );
	int seqidx = 0;
	ReportSchema * rsp;

	foreach ( rsp, *ReportSchemas ) {
		rec.idReportSchema = rsp->idReportSchema;
		c_qstrncpy ( rec.tag, rsp->objO.data.u.object.tag, SHORT_DESC_LEN );
		copyToCorbaObject ( rec.objO, rsp->objO );

		rec.hdrV.length ( rsp->hdrV.count() );
		int i = 0;
		for ( RSOVectorIterator ito = rsp->hdrV.begin(); ito != rsp->hdrV.end(); ++ito ) {
			copyToCorbaHeader ( rec.hdrV[i], ( *ito ) );
			++i;
		}

		rec.bdyV.length ( rsp->bdyV.count() );
		i = 0;
		for ( RSOVectorIterator ito = rsp->bdyV.begin(); ito != rsp->bdyV.end(); ++ito ) {
			copyToCorbaBody ( rec.bdyV[i], ( *ito ) );
			++i;
		}

		rec.totV.length ( rsp->totV.count() );
		i = 0;
		for ( RSOVectorIterator ito = rsp->totV.begin(); ito != rsp->totV.end(); ++ito ) {
			copyToCorbaTotalizer ( rec.totV[i], ( *ito ) );
			++i;
		}

		rec.fooV.length ( rsp->fooV.count() );
		i = 0;
		for ( RSOVectorIterator ito = rsp->fooV.begin(); ito != rsp->fooV.end(); ++ito ) {
			copyToCorbaFooter ( rec.fooV[i], ( *ito ) );
			++i;
		}

		( *seqptr ) [seqidx++] = rec;
	}
}

void ReportSchemaInterface::copyToCorbaCommon ( ReportSchemaObj & rso, DATA_RS_COMMON & c, DATA_RS_HTML & html )
{
	c.idReportSchema = rso.data.idReportSchema;
	c.idObject = rso.data.idObject;
	c.type = rso.data.type;
	c_qstrncpy ( c.tag, rso.data.tag, SHORT_DESC_LEN );

	html.vSize = rso.data.html.vSize;
	html.hSize = rso.data.html.hSize;
	html.vAlign = rso.data.html.vAlign;
	html.hAlign = rso.data.html.hAlign;
	html.wrap = rso.data.html.wrap;
	html.foreground_r = rso.data.html.foreground.r;
	html.foreground_g = rso.data.html.foreground.g;
	html.foreground_b = rso.data.html.foreground.b;
	html.background_r = rso.data.html.background.r;
	html.background_g = rso.data.html.background.g;
	html.background_b = rso.data.html.background.b;
	html.style = rso.data.html.style;
	html.fontSize = rso.data.html.fontSize;
	html.bold = rso.data.html.bold;
	html.italic = rso.data.html.italic;
	html.underline = rso.data.html.underline;
}

void ReportSchemaInterface::copyToCorbaObject ( DATA_RSO_OBJECT & objO, ReportSchemaObj & rso )
{
	copyToCorbaCommon ( rso, objO.c, objO.html );

	c_qstrncpy ( objO.u.tag, rso.data.u.object.tag, SHORT_DESC_LEN );
	objO.u.hasBorder = rso.data.u.object.hasBorder;
	objO.u.excelCSV = rso.data.u.object.excelCSV;
	objO.u.simpleBody = rso.data.u.object.simpleBody;
	objO.u.idAnalisys = rso.data.u.object.idAnalisys;
	objO.u.usePlugin = rso.data.u.object.usePlugin;
	c_qstrncpy ( objO.u.pluginName, rso.data.u.object.pluginName, SHORT_PLUGIN_NAME );
	objO.u.defaultForeground_r = rso.data.u.object.defaultForeground.r;
	objO.u.defaultForeground_g = rso.data.u.object.defaultForeground.g;
	objO.u.defaultForeground_b = rso.data.u.object.defaultForeground.b;
	objO.u.defaultBackground_r = rso.data.u.object.defaultBackground.r;
	objO.u.defaultBackground_g = rso.data.u.object.defaultBackground.g;
	objO.u.defaultBackground_b = rso.data.u.object.defaultBackground.b;
}

void ReportSchemaInterface::copyToCorbaHeader ( DATA_RSO_HEADER & hdrO, ReportSchemaObj & rso )
{
	copyToCorbaCommon ( rso, hdrO.c, hdrO.html );

	c_qstrncpy ( hdrO.u.userLabel, rso.data.u.header.userLabel, REPO_LABEL_LEN );
	hdrO.u.value = rso.data.u.header.value;
	c_qstrncpy ( hdrO.u.userValue, rso.data.u.header.userValue, REPO_USER_LEN );
	hdrO.u.isUrl = rso.data.u.header.isUrl;
	hdrO.u.line = rso.data.u.header.line;
}

void ReportSchemaInterface::copyToCorbaBody ( DATA_RSO_BODY & bdyO, ReportSchemaObj & rso )
{
	copyToCorbaCommon ( rso, bdyO.c, bdyO.html );

	bdyO.u.source = rso.data.u.body.source;
	bdyO.u.idKey = rso.data.u.body.idKey;
	bdyO.u.idCounter = rso.data.u.body.idCounter;
	bdyO.u.idScript = rso.data.u.body.idScript;
	bdyO.u.idPlugin = rso.data.u.body.idPlugin;
	c_qstrncpy ( bdyO.u.userValue, rso.data.u.body.userValue, REPO_USER_LEN );
	bdyO.u.isUrl = rso.data.u.body.isUrl;
	bdyO.u.noTotals = rso.data.u.body.noTotals;
	bdyO.u.repeatKey = rso.data.u.body.repeatKey;
	bdyO.u.decimals = rso.data.u.body.decimals;
	bdyO.u.line = rso.data.u.body.line;
}

void ReportSchemaInterface::copyToCorbaTotalizer ( DATA_RSO_TOTALIZER & totO, ReportSchemaObj & rso )
{
	copyToCorbaCommon ( rso, totO.c, totO.html );

	totO.u.trigger = rso.data.u.totalizer.trigger;
	c_qstrncpy ( totO.u.label, rso.data.u.totalizer.label, REPO_LABEL_LEN );
	totO.u.redrawHeader = rso.data.u.totalizer.redrawHeader;
	totO.u.border = rso.data.u.totalizer.border;
	totO.u.line = rso.data.u.totalizer.line;
	totO.u.useScript = rso.data.u.totalizer.useScript;
	totO.u.idScript = rso.data.u.totalizer.idScript;
}

void ReportSchemaInterface::copyToCorbaFooter ( DATA_RSO_FOOTER & fooO, ReportSchemaObj & rso )
{
	copyToCorbaCommon ( rso, fooO.c, fooO.html );

	fooO.u.source = rso.data.u.footer.source;
	c_qstrncpy ( fooO.u.label, rso.data.u.footer.label, REPO_LABEL_LEN );
	c_qstrncpy ( fooO.u.userValue, rso.data.u.footer.userValue, REPO_USER_LEN );
	fooO.u.isUrl = rso.data.u.footer.isUrl;
	fooO.u.line = rso.data.u.footer.line;
}

void ReportSchemaInterface::fillFromCorba ( const ReportSchemaSeq * seqptr )
{
	ReportSchemaObj rso;
	ReportSchema * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new ReportSchema;

		rec->idReportSchema = ( *seqptr ) [cnt].idReportSchema;
		c_qstrncpy ( rec->objO.data.u.object.tag, ( *seqptr ) [cnt].tag, SHORT_DESC_LEN );

		fillFromCorbaObject ( ( *seqptr ) [cnt].objO, rec->objO );

		for ( int i = 0; i < ( *seqptr ) [cnt].hdrV.length(); i++ ) {
			fillFromCorbaHeader ( ( *seqptr ) [cnt].hdrV[i], rso );
			rec->hdrV << rso;
		}

		for ( int i = 0; i < ( *seqptr ) [cnt].bdyV.length(); i++ ) {
			fillFromCorbaBody ( ( *seqptr ) [cnt].bdyV[i], rso );
			rec->bdyV << rso;
		}

		for ( int i = 0; i < ( *seqptr ) [cnt].totV.length(); i++ ) {
			fillFromCorbaTotalizer ( ( *seqptr ) [cnt].totV[i], rso );
			rec->hdrV << rso;
		}

		for ( int i = 0; i < ( *seqptr ) [cnt].fooV.length(); i++ ) {
			fillFromCorbaFooter ( ( *seqptr ) [cnt].fooV[i], rso );
			rec->hdrV << rso;
		}

		add ( rec );
	}

	ReportSchemasNumber = count();
}

void ReportSchemaInterface::fillFromCorbaCommon ( ReportSchemaObj & rso, const DATA_RS_COMMON & c, const DATA_RS_HTML & html )
{
	rso.data.idReportSchema = c.idReportSchema;
	rso.data.idObject = c.idObject;
	rso.data.type = ( ReportSchemaObj::CellObject ) c.type;
	c_qstrncpy ( rso.data.tag, c.tag, SHORT_DESC_LEN );
	rso.data.html.vSize = html.vSize;
	rso.data.html.hSize = html.hSize;
	rso.data.html.vAlign = ( ReportSchemaObj::VertAlign ) html.vAlign;
	rso.data.html.hAlign = ( ReportSchemaObj::HoriAlign ) html.hAlign;
	rso.data.html.wrap = html.wrap;
	rso.data.html.foreground.r = html.foreground_r;
	rso.data.html.foreground.g = html.foreground_g;
	rso.data.html.foreground.b = html.foreground_b;
	rso.data.html.background.r = html.background_r;
	rso.data.html.background.g = html.background_g;
	rso.data.html.background.b = html.background_b;
	rso.data.html.style = ( ReportSchemaObj::FontStyle ) html.style;
	rso.data.html.fontSize = html.fontSize;
	rso.data.html.bold = html.bold;
	rso.data.html.italic = html.italic;
	rso.data.html.underline = html.underline;
}

void ReportSchemaInterface::fillFromCorbaObject ( const DATA_RSO_OBJECT & objO, ReportSchemaObj & rso )
{
	fillFromCorbaCommon ( rso, objO.c, objO.html );

	c_qstrncpy ( rso.data.u.object.tag, objO.u.tag, SHORT_DESC_LEN );
	rso.data.u.object.hasBorder = objO.u.hasBorder;
	rso.data.u.object.excelCSV = objO.u.excelCSV;
	rso.data.u.object.simpleBody = objO.u.simpleBody;
	rso.data.u.object.idAnalisys = objO.u.idAnalisys;
	rso.data.u.object.usePlugin = objO.u.usePlugin;
	c_qstrncpy ( rso.data.u.object.pluginName, objO.u.pluginName, SHORT_PLUGIN_NAME );
	rso.data.u.object.defaultForeground.r = objO.u.defaultForeground_r;
	rso.data.u.object.defaultForeground.g = objO.u.defaultForeground_g;
	rso.data.u.object.defaultForeground.b = objO.u.defaultForeground_b;
	rso.data.u.object.defaultBackground.r = objO.u.defaultBackground_r;
	rso.data.u.object.defaultBackground.g = objO.u.defaultBackground_g;
	rso.data.u.object.defaultBackground.b = objO.u.defaultBackground_b;
}

void ReportSchemaInterface::fillFromCorbaHeader ( const DATA_RSO_HEADER & hdrO, ReportSchemaObj & rso )
{
	fillFromCorbaCommon ( rso, hdrO.c, hdrO.html );

	c_qstrncpy ( rso.data.u.header.userLabel, hdrO.u.userLabel, REPO_LABEL_LEN );
	rso.data.u.header.value = ( ReportSchemaObj::HeaderValue ) hdrO.u.value;
	c_qstrncpy ( rso.data.u.header.userValue, hdrO.u.userValue, REPO_USER_LEN );
	rso.data.u.header.isUrl = hdrO.u.isUrl;
	rso.data.u.header.line = ( ReportSchemaObj::BreakingType ) hdrO.u.line;
}

void ReportSchemaInterface::fillFromCorbaBody ( const DATA_RSO_BODY & bdyO, ReportSchemaObj & rso )
{
	fillFromCorbaCommon ( rso, bdyO.c, bdyO.html );

	rso.data.u.body.source = ( ReportSchemaObj::DataSource ) bdyO.u.source;
	rso.data.u.body.idKey = bdyO.u.idKey;
	rso.data.u.body.idCounter = bdyO.u.idCounter;
	rso.data.u.body.idScript = bdyO.u.idScript;
	rso.data.u.body.idPlugin = bdyO.u.idPlugin;
	c_qstrncpy ( rso.data.u.body.userValue, bdyO.u.userValue, REPO_USER_LEN );
	rso.data.u.body.isUrl = bdyO.u.isUrl;
	rso.data.u.body.noTotals = bdyO.u.noTotals;
	rso.data.u.body.repeatKey = bdyO.u.repeatKey;
	rso.data.u.body.decimals = bdyO.u.decimals;
	rso.data.u.body.line = ( ReportSchemaObj::BreakingType ) bdyO.u.line;
}

void ReportSchemaInterface::fillFromCorbaTotalizer ( const DATA_RSO_TOTALIZER & totO, ReportSchemaObj & rso )
{
	fillFromCorbaCommon ( rso, totO.c, totO.html );

	rso.data.u.totalizer.trigger = totO.u.trigger;
	c_qstrncpy ( rso.data.u.totalizer.label, totO.u.label, REPO_LABEL_LEN );
	rso.data.u.totalizer.redrawHeader = totO.u.redrawHeader;
	rso.data.u.totalizer.border = totO.u.border;
	rso.data.u.totalizer.line = ( ReportSchemaObj::BreakingType ) totO.u.line;
	rso.data.u.totalizer.useScript = totO.u.useScript;
	rso.data.u.totalizer.idScript = totO.u.idScript;
}

void ReportSchemaInterface::fillFromCorbaFooter ( const DATA_RSO_FOOTER & fooO, ReportSchemaObj & rso )
{
	fillFromCorbaCommon ( rso, fooO.c, fooO.html );

	rso.data.u.footer.source = fooO.u.source;
	c_qstrncpy ( rso.data.u.footer.label, fooO.u.label, REPO_LABEL_LEN );
	c_qstrncpy ( rso.data.u.footer.userValue, fooO.u.userValue, REPO_USER_LEN );
	rso.data.u.footer.isUrl = fooO.u.isUrl;
	rso.data.u.footer.line = ( ReportSchemaObj::BreakingType ) fooO.u.line;
}

