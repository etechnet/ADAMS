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

#include <pluginbase.h>
#include <applogger.h>
#include <Qt/qfile.h>

PluginBase::PluginBase()
{
	handle = 0;
	plWorker = 0;
	plSetup = 0;
}

PluginBase::PluginBase ( const QString & pl_path )
{
	handle = 0;
	plWorker = 0;
	plSetup = 0;
	setPluginHome ( pl_path );
}

PluginBase::~PluginBase()
{
	if ( handle )
		dlclose ( handle );
}

//
// attach and register plugin. Return true in case of error
//

bool PluginBase::registerPlugin ( const QString& plName, const QString& plPath, bool verbose )
{
	if ( plName.isEmpty() ) {
		lout << "PluginBase: Null plugin name." << endl;
		return true;
	}
	if ( !plPath.isEmpty() )
		pluginSoName = plPath + QString ( "/" ) + plName;
	else
		pluginSoName = plName;

	if ( pluginSoName.mid ( 0, 1 ) == "~" ) {				// home relative path
		char * homedir = getenv ( "HOME" );
		if ( homedir ) {
			pluginSoName.replace ( 0, 1, homedir );
		} else {
			if ( pluginPrivateHome.length() > 0 )
				pluginSoName.replace ( 0, 1, pluginPrivateHome );
		}
	}

	if ( !QFile::exists ( pluginSoName ) ) {
		if ( verbose ) lout << "PluginBase:: Non-existent .so file : (" << pluginSoName << ")" << endl;
		return true;
	}
	// now the real try
	handle = dlopen ( qPrintable ( pluginSoName ), RTLD_LAZY | RTLD_GLOBAL ); // this could be s.o. dependent ....

	if ( handle == ( void * ) 0 ) {
		if ( verbose ) lout << "PluginBase: Error dlopening plugin. dlopen() says: " << dlerror() << endl;
		return true;
	}

	dl_ptr = ( PluginImpl * ( * ) () ) dlsym ( handle, "pluginActivate" );
	if ( !dl_ptr ) {
		lout << "PluginBase: Error resolving basic symbol ""pluginActivate"" from " << pluginSoName << "." << endl;
		return true;
	}

	// ok
	return false;
}

PluginImpl * PluginBase::getInstance()
{
	return ( dl_ptr ) ();
}
