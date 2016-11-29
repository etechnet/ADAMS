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

#include <pluginregistryinterface.h>
#include <applogger.h>

PluginRegistryInterface::PluginRegistryInterface() : QObject(), uniq ( 0 ), PluginRegistrys ( 0 )
{
	PluginRegistrys = new QMultiHash<QString, PluginRegistry *>;
	PluginRegistrysNumber = 0;
}

PluginRegistryInterface::~PluginRegistryInterface()
{
	if ( PluginRegistrys ) {
		clear();
		delete PluginRegistrys;
	}
}

/**
  * Inserisce una nuova eccezione nel hashtable
  * ed incrementa il numero di eccezioni.
**/
bool PluginRegistryInterface::add ( PluginRegistry * newPluginRegistry )
{
	PluginRegistrys->insert ( QString::number ( newPluginRegistry->data.idPlugin ), newPluginRegistry );
	PluginRegistrysNumber++;
	return false;
}
/**
  * Rimouve una eccezione dal hashtable
  * ed decrementa il numero di eccezioni.
**/
bool PluginRegistryInterface::remove ( int PluginRegistryNum )
{
	PluginRegistry *plr = ( *PluginRegistrys->find ( QString::number ( PluginRegistryNum ) ) );
	if ( plr == ( PluginRegistry * ) 0 ) {
		return true;
	}
	PluginRegistrys->remove ( QString::number ( PluginRegistryNum ) );
	delete plr;
	PluginRegistrysNumber--;
	return false;
}
/**
  * Restituisce un specifico elemento.
**/
PluginRegistry * PluginRegistryInterface::get ( int PluginRegistryNum )
{
	return ( *PluginRegistrys->find ( QString::number ( PluginRegistryNum ) ) );
}

/**
  * Ritorna il Tag relativo alle eccezioni.
**/
const char * PluginRegistryInterface::getHeaderTag()
{
	static const char *ht = PLUGINREGISTRY_HEADERTAG;

	return ht;
}

// get by tag

PluginRegistry * PluginRegistryInterface::getByTag ( const QString & srcTag )
{
	if ( PluginRegistrys->count() > 0 ) {
		foreach ( PluginRegistry * p, *PluginRegistrys ) {
			if ( p->data.tag == srcTag )
				return p;
		}
	}
	return ( PluginRegistry * ) 0;
}

// get tag list

TagList PluginRegistryInterface::getTagList()
{
	TagList tagList;

	tagList.values << "0";
	tagList.labels << "<none>";
	if ( PluginRegistrys->count() > 0 ) {
		foreach ( PluginRegistry * p, *PluginRegistrys ) {
			tagList.values.append ( QString::number ( p->data.idPlugin ) );
			tagList.labels.append ( QString ( p->data.tag ) );
		}
	}

	return tagList;
}




// setup i/e tags

void PluginRegistryInterface::ieInit()
{
}

// do the export of the cdr config onto the ImportExport stream

void PluginRegistryInterface::ieExport ( ImportExport & ie )
{
	if ( PluginRegistrys->count() > 0 ) {
		foreach ( PluginRegistry * p, *PluginRegistrys ) {
			ie.initWriteRecord ( PLUGINREGISTRY_HEADERTAG );

			ie.setWriteTag ( "idPlugin" );
			ie.addWriteRecord ( p->data.idPlugin );
			ie.setWriteTag ( "tag" );
			ie.addWriteRecord ( p->data.tag );
			ie.setWriteTag ( "pluginName" );
			ie.addWriteRecord ( p->data.pluginName );
			ie.setWriteTag ( "usage" );
			ie.addWriteRecord ( p->data.usage );
			ie.setWriteTag ( "enabled" );
			ie.addWriteRecord ( p->data.enabled );

			ie.flushWriteRecord();
		}
	}
}


// import a record from ie stream

void PluginRegistryInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag != PLUGINREGISTRY_HEADERTAG )		// not a record of mine
		return;

	PluginRegistry *fld = new PluginRegistry;

	fld->data.idPlugin = ieptr->getIntToken ( "idPlugin" );
	ieptr->getStrToken ( fld->data.tag, "tag", SHORT_DESC_LEN );
	ieptr->getStrToken ( fld->data.pluginName, "pluginName", PLR_PLUGINNAME_LEN );
	fld->data.usage = ( PluginRegistry::plrUsage ) ieptr->getIntToken ( "usage" );
	fld->data.enabled = ieptr->getBoolToken ( "enabled" );

	add ( fld );
	return;
}

void PluginRegistryInterface::copyToCorba ( PluginRegistrySeq * seqptr )
{
	DATA_PLUGINREGISTRY rec;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( PluginRegistry * p, *PluginRegistrys ) {
		rec.idPlugin = p->data.idPlugin;
		c_qstrncpy ( rec.tag, p->data.tag, SHORT_DESC_LEN );
		c_qstrncpy ( rec.pluginName, p->data.pluginName, PLR_PLUGINNAME_LEN );
		rec.usage = p->data.usage;
		rec.enabled = p->data.enabled;

		( *seqptr ) [seqidx++] = rec;
	}
}

void PluginRegistryInterface::fillFromCorba ( const PluginRegistrySeq * seqptr )
{
	PluginRegistry * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new PluginRegistry;

		rec->data.idPlugin = ( *seqptr ) [cnt].idPlugin;
		c_qstrncpy ( rec->data.tag, ( *seqptr ) [cnt].tag, SHORT_DESC_LEN );
		c_qstrncpy ( rec->data.pluginName, ( *seqptr ) [cnt].pluginName, PLR_PLUGINNAME_LEN );
		rec->data.usage = ( PluginRegistry::plrUsage ) ( *seqptr ) [cnt].usage;
		rec->data.enabled = ( *seqptr ) [cnt].enabled;

		add ( rec );
	}

	PluginRegistrysNumber = count();
}


