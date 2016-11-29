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
  ===                     SIMPLE .INI FILE PARSER                          ===
  ===                                                                      ===
  === The following methods extract configuration values from .ini files.  ===
  === All values are organized in sections and a section can contain two   ===
  === kinds of values: lists or argument/value couples                     ===
  ===                                                                      ===
  === Syntax rules:                                                        ===
  === .1 - Comments are identified by token '#' or ';'                     ===
  === .2 - Blank lines are allowed but ignored                             ===
  === .3 - Sections are identified by a token enclosed between squared     ===
  ===      parenthesis (Ex.: [My_Section]) without spaces                  ===
  === .4 - Values in a list are collected as simple strings. Leading or    ===
  ===      trailing spaces are deleted/ignored                             ===
  === .5 - Argument/value couples are separated by an '=' sign.            ===
  ===      Argument token can't contain spaces.                            ===
  ===      Leading or trailing spaces in values are deleted/ignored        ===
  ===                                                                      ===
  ===  EXAMPLE:                                                            ===
  ===                                                                      ===
  ===  # This is an example comment                                        ===
  ===  ; This is another example comment                                   ===
  ===                                                                      ===
  ===  [Section_One]                                                       ===
  ===                                                                      ===
  ===  An argument line.                                                   ===
  ===  Another argument line.                                              ===
  ===                                                                      ===
  ===  [Section_Two]                                                       ===
  ===                                                                      ===
  ===  Arg_id_One = a value for the first argument                         ===
  ===  Arg_id_Two = a value                                                ===
  ===                                                                      ===
 ***************************************************************************/

#include <configparser.h>
#include <applogger.h>

#include <Qt/qchar.h>
#include <Qt/qfile.h>
#include <Qt/qdir.h>
#include <Qt/qcache.h>
#include <Qt/qmutex.h>
#include <Qt/qstringbuilder.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

static QCache<QString, QStringList> value_lists_cache;
static QCache<QString, QString> value_cache;
static QMutex cache_access;
static QStringList marker_tag_list;

ConfigParser::ConfigParser()
{
	txtDelimiters = parDELIMITERS;

	// Fill list of compiled path + known paths

#ifdef ADAMS_RUNTIME_SHARED_PATH
	adams_ini_known_paths << ADAMS_RUNTIME_SHARED_PATH;
#endif
#ifdef ADAMS_PREFIX
	adams_ini_known_paths << ADAMS_PREFIX;
#endif

	adams_ini_known_paths <<
	                      "/usr/share/adams" <<
	                      "/opt/adams/share" <<
	                      "~/adams/conf" <<
	                      "~/conf" <<
	                      "~/.adams" <<
	                      "/usr/adams/share" <<
	                      "/usr/lib/adams" <<
	                      "./";

	adams_ini_name = ADAMSINIFILE;

}

ConfigParser::~ConfigParser()
{
}

QString ConfigParser::tilde_expand ( const QString & path )
{
	QString expanded = path;

	if ( path.mid ( 0, 1 ) == "~" ) {				// home relative path
		QString homedir = getenv ( "HOME" );
		if ( ! homedir.isEmpty() ) {
			expanded.replace ( 0, 1, homedir );
		}
	}

	return expanded;
}


bool ConfigParser::locateFile ()
{
	QString path;
	bool ini_found = false;

	foreach ( path, adams_ini_known_paths ) {
		lout3 << "locateFile() trying: " << path << endl;
		path = tilde_expand ( path );
		if ( QDir ( path ).exists() ) {
			path += "/";
			if ( QFile::exists ( path + adams_ini_name ) ) {
				ini_found = true;
				adams_ini_file = path % adams_ini_name;
				lout3 << "locateFile() found: " << adams_ini_file << endl;
				break;
			}
		}
	}

	return ini_found;
}

QString ConfigParser::locateUserFile ( const QString & file_name )
{
	QString path;

	foreach ( path, adams_ini_known_paths ) {
		lout3 << "locateFile() trying: " << path << endl;
		path = tilde_expand ( path );
		if ( QDir ( path ).exists() ) {
			path += "/";
			if ( QFile::exists ( path + file_name ) ) {
				lout3 << "locateFile() found: " << file_name << endl;
				return ( path % file_name );
			}
		}
	}

	return "";
}

bool ConfigParser::locateFile ( QString exename )
{
	int slashpos = exename.lastIndexOf ( '/' );		// try with executable name
	if ( slashpos >= 0 ) {
		QString p = exename.left ( slashpos );
		adams_ini_known_paths << p;
		lout3 << "locateFile() adding search path: " << p << endl;
	}

	return locateFile();
}


QStringList ConfigParser::parQTxtGetSection_private ( const QString & sectnam )
{
	FILE *fTxtData = 0;
	QStringList reqdata;
	char buf[BUFSIZ + 1];
	bool flagRetrievingData = false;
	char *strptr = 0;

	fTxtData = fopen ( qPrintable ( adams_ini_file ), "r" );
	if ( fTxtData == ( FILE * ) NULL ) {
		return reqdata;		/* no file found */
	}

	bool do_next_line = true;

	while ( do_next_line ) {
		if ( feof ( fTxtData ) ) {
			do_next_line = false;
			break;
		}
		if ( fgets ( buf, BUFSIZ, fTxtData ) == ( char * ) NULL ) {
			do_next_line = false;
			break;
		}

		strptr = buf + strlen ( buf );
		/* remove linefeeds retrieved from file */
		while ( strptr >= buf ) {
			if ( *strptr == '\n' || *strptr == '\r' )
				*strptr = '\0';
			--strptr;
		}
		unleadch ( buf ); 					/* remove leading spaces */
		untrailch ( buf );					/* remove trailing spaces */
		if ( strlen ( buf ) == 0 )				/* is a null line */
			continue;
		switch ( buf[0] ) {
			case '[':					/* is a section identifier */
				if ( flagRetrievingData ) {		/* found another section: exit */
					do_next_line = false;
					break;
				}
				if ( TestSection ( buf, qPrintable ( sectnam ) ) )	/* found it ! */
					flagRetrievingData = true;
				continue;
			case '#':					/* is a comment line */
			case ';':					/* is a comment line */
				continue;				/* so ignore it */
			default:					/* this SHOULD be a data line */
				if ( !flagRetrievingData )		/* still searching the section */
					continue;
				if ( !isalnum ( buf[0] ) &&		/* paranoia check */
				                !ispunct ( buf[0] ) )
					continue;
				reqdata.append ( buf );
				continue;
		}

		//		 continue;			/* unreachable */
	}

	if ( fTxtData != NULL ) {
		fclose ( fTxtData );
	}
	return reqdata;
}

QString ConfigParser::parQTxtGetValue_private ( const QString& valueid, const QString& sectnam, const QStringList * s_list_ptr )
{
	char * token;
	char * saveptr;
	int idx;

	QStringList SectionValues;
	if (s_list_ptr)
		SectionValues = *s_list_ptr;
	else
		SectionValues = parQTxtGetSection_private ( sectnam );

	idx = 0;

	if ( SectionValues.count() == 0 ) {			/* empty section */
		return "";
	}
	while ( true ) {					/* main loop: decompose strings & seek the array */
		QStringList tokens = SectionValues.at ( idx ).simplified().split ( "=" );

		if ( tokens.count() != 2 ) {			/* null arg or syntax error */
			return "";
		}

		QString tok_left = tokens.at ( 0 ).simplified();

		if ( tok_left != valueid ) {				/* test if the desired value */
			if ( idx < SectionValues.count() - 1 ) {	/* it isn't */
				++idx;
				continue;
			}
			else {
				return "";
			}
		}

		/* found the id: now return right part of the "=" sign */
		return tokens.at ( 1 ).simplified();

	}
}


void ConfigParser::addMarkerTagPath ( const QString & marker_string )
{
	marker_tag_list.clear();
	marker_tag_list = marker_string.split ( '.', QString::SkipEmptyParts );
}


QStringList ConfigParser::parQTxtGetSection ( const QString & sectnam )
{
	QStringList valid_data_list;

	// --- Try for cached values lists

	QStringList found_data_list;
	QString marked_section = sectnam;

	cache_access.lock();

	if ( value_lists_cache.contains ( marked_section ) ) {
		valid_data_list = *value_lists_cache.object ( marked_section );
	}

	for ( int i = 0; i < marker_tag_list.size(); i++ ) {
		marked_section += '.';
		marked_section += marker_tag_list.at ( i );
		found_data_list.clear();

		if ( value_lists_cache.contains ( marked_section ) )
			found_data_list = *value_lists_cache.object ( marked_section );
		if ( ! found_data_list.isEmpty() ) {
			valid_data_list = found_data_list;
		}
	}

	cache_access.unlock();

	if ( ! valid_data_list.isEmpty() ) {
		return valid_data_list;
	}


	// --- Retrieve uncached values lists

	// Acquire default values (if any)
	marked_section = sectnam;
	valid_data_list = parQTxtGetSection_private ( sectnam );
	QString valid_key = sectnam;

	for ( int i = 0; i < marker_tag_list.size(); i++ ) {
		marked_section += '.';
		marked_section += marker_tag_list.at ( i );

		found_data_list = parQTxtGetSection_private ( marked_section );
		if ( ! found_data_list.isEmpty() ) {
			valid_data_list = found_data_list;
			valid_key = marked_section;
		}
	}

	if ( ! valid_data_list.isEmpty() ) {
		QStringList * cache_object = new QStringList ( valid_data_list );
		cache_access.lock();
		value_lists_cache.insert ( valid_key, cache_object );
		cache_access.unlock();
	}

	return valid_data_list;
}

QString ConfigParser::parQTxtGetValue ( const QString & valueid, const QString & sectnam, ConfValueType value_type )
{
	QString value;

	// --- Try for cached value

	QString complete_key = sectnam % QChar ( '_' ) % valueid % QChar ( '.' ) % marker_tag_list.join ( "." );

	cache_access.lock();

	for ( int i = 0; i < marker_tag_list.size() + 1; i++ ) {
		QString key = complete_key;
		if ( value_cache.contains ( key ) ) {
			value = *value_cache.object ( key );
			cache_access.unlock();
			return finalizeValue ( value, value_type );
		}

		int last_mark = complete_key.lastIndexOf ( "." );
		complete_key.remove ( last_mark, complete_key.size() );
	}

	cache_access.unlock();

	QStringList found_data_list;
	QString marked_section = sectnam;
	QString tmp_value;
	QString valid_key;

	if ( value_lists_cache.contains ( sectnam ) ) {
		found_data_list = *value_lists_cache.object ( sectnam );
	}
	else {
		found_data_list = parQTxtGetSection_private ( sectnam );
		if ( ! found_data_list.isEmpty() )
			putSectionInCache(sectnam, found_data_list);
	}

	if ( ! found_data_list.isEmpty() )
		tmp_value = getValueLoop(valueid, sectnam, &valid_key, &found_data_list);
	if ( ! tmp_value.isEmpty())
		value = tmp_value;

	for ( int i = 0; i < marker_tag_list.size(); i++ ) {
		marked_section += '.';
		marked_section += marker_tag_list.at ( i );

		if ( value_lists_cache.contains ( marked_section ) ) {
			found_data_list = *value_lists_cache.object ( marked_section );
		}
		else {
			found_data_list = parQTxtGetSection_private ( marked_section );
			if ( ! found_data_list.isEmpty() )
				putSectionInCache(marked_section, found_data_list);
		}

		if ( ! found_data_list.isEmpty() ) {
			tmp_value = getValueLoop(valueid, sectnam, &valid_key, &found_data_list);
			if ( ! tmp_value.isEmpty()) {
				value = tmp_value;
			}
		}
	}

	if ( ! value.isEmpty() ) {

		parseVariables ( value, sectnam );

		QString * cache_object = new QString ( value );
		QString cache_key = sectnam % QChar ( '_' ) % valid_key;
		cache_access.lock();
		value_cache.insert ( cache_key, cache_object );
		cache_access.unlock();
	}

	return finalizeValue ( value, value_type );
}

/**
 ** Utility functions
 **/

void ConfigParser::putSectionInCache ( const QString & cache_key, const QStringList & cache_list )
{
	if ( ! value_lists_cache.contains ( cache_key ) ) {
		QStringList * l_cache_object = new QStringList ( cache_list );
		cache_access.lock();
		value_lists_cache.insert ( cache_key, l_cache_object );
		cache_access.unlock();
	}
}

QString ConfigParser::getValueLoop ( const QString & valueid, const QString & sectnam, QString * valid_key, const QStringList * s_list_ptr )
{
	QString value;
	QString found_value;
	QString marked_tag = valueid;

	// Acquire default values (if any)
	value = parQTxtGetValue_private ( valueid, sectnam, s_list_ptr );
	*valid_key = valueid;

	for ( int i = 0; i < marker_tag_list.size(); i++ ) {
		marked_tag += '.';
		marked_tag += marker_tag_list.at ( i );

		found_value = parQTxtGetValue_private ( marked_tag, sectnam );
		if ( ! found_value.isEmpty() ) {
			value = found_value;
			*valid_key = marked_tag;
		}
	}

	return value;
}



void ConfigParser::parseVariables ( QString & in, const QString & sectnam )
{
	// Search and replace environmental values
	QRegExp re_env ( "@\\{([A-Za-z0-9_\\./\\-\\s]*)\\}" );
	QRegExp re_var ( "%\\{([A-Za-z0-9_\\./\\-\\|\\s]*)\\}" );
	int pos = 0;

	while ( ( pos = re_env.indexIn ( in, pos ) ) != -1 ) {
		QString var_full = re_env.cap ( 0 );
		QString var_text = re_env.cap ( 1 );

		QString e_value = ::getenv ( qPrintable ( var_text ) );

		if ( e_value.isEmpty() ) {
			lout << "ConfigParser : request for environ value \"" << var_full << "\" failed." << endl;
			pos += re_env.matchedLength();
			continue;
		}

		in.replace ( pos, re_env.matchedLength(), e_value );
		pos = 0;
	}

	pos = 0;

	while ( ( pos = re_var.indexIn ( in, pos ) ) != -1 ) {
		QString var_full = re_var.cap ( 0 );
		QString var_text = re_var.cap ( 1 );

		QStringList v_comp = var_text.split ( "|" );	// is in the form: "section|tag_name"

		QString v_section;
		QString v_tag;
		if (v_comp.size() > 1) {
			v_section = v_comp.at ( 0 );
			v_tag = v_comp.at ( 1 );
		}
		else {				// empty section, use current
			v_tag = v_comp.at ( 0 );
			v_section = sectnam;
		}

		QString v_value = parQTxtGetValue ( v_tag, v_section, ConfigParser::GenericValue );

		if ( v_value.isEmpty() ) {
			lout << "ConfigParser : request for variable \"" << var_full << "\" failed." << endl;
			pos += re_var.matchedLength();
			break;
		}

		in.replace ( pos, re_var.matchedLength(), v_value );
		pos = 0;

	}
}

QString ConfigParser::finalizeValue ( const QString & value, ConfValueType value_type )
{
	switch ( value_type ) {
		case PathValue:
			return tilde_expand ( value );
		case GenericValue:
			return value;
	}

	return value;
}

void ConfigParser::invalidateCache()
{
	value_cache.clear();
	value_lists_cache.clear();
}


void ConfigParser::unleadch ( char *s )
{
	char *cs;
	char *malladdr = ( char * ) 0;
	char locbuf[1024 + 1];

	if ( strlen ( s ) > 1024 ) {
		cs = new char [strlen ( s ) + 1];
		malladdr = cs;
	}
	else {
		cs = locbuf;
	}
	if ( cs == ( char * ) 0 )
		return;
	strcpy ( cs, s );
	while ( *cs == ' ' || *cs == '0' || *cs == '\t' )
		cs++;
	strcpy ( s, cs );
	if ( malladdr != ( char * ) 0 )
		delete malladdr;
}

void ConfigParser::untrailch ( char *s )
{
	char *p;

	p = s + strlen ( s ) - 1;
	while ( *p == ' ' || *p == '\t' ) --p;
	++p;
	*p = '\0';
}

bool ConfigParser::TestSection ( char *tData, const char *fSectid )
{
	char *strptr;

	strptr = tData + strlen ( tData );
	while ( strptr >= tData ) {
		if ( strptr > tData ) {
			--strptr;
			if ( *strptr == ']' ) {
				*strptr = '\0';
				break;
			}
		}
		else {
			return ( false );		/* no ending "]": syntax error */
		}
	}
	++tData;				/* skip starting "[" */
	return ( ( !strcmp ( tData, fSectid ) ) ? true : false );	/* Test the name and return */
}


/*
 * __findenv --
 *	Returns pointer to value associated with name, if any, else NULL.
 *	Sets offset to be the offset of the name/value combination in the
 *	environmental array, for use by setenv(3) and unsetenv(3).
 *	Explicitly removes '=' in argument name.
 *
 *	This routine *should* be a static; don't use it.
 */
char * ConfigParser::__findenv ( const char *name, int *offset )
{
	extern char **environ;
	int len, i;
	const char *np;
	char **p, *cp;

	if ( name == NULL || environ == NULL )
		return ( NULL );
	for ( np = name; *np && *np != '='; ++np )
		;
	len = np - name;
	for ( p = environ; ( cp = *p ) != NULL; ++p ) {
		for ( np = name, i = len; i && *cp; i-- )
			if ( *cp++ != *np++ )
				break;
		if ( i == 0 && *cp++ == '=' ) {
			*offset = p - environ;
			return ( cp );
		}
	}
	return ( NULL );
}

/*
 * getenv --
 *	Returns ptr to value associated with name, if any, else NULL.
 */
char * ConfigParser::getenv ( const char *name )
{
	int offset;

	return ( __findenv ( name, &offset ) );
}


/*---[ Eof ]---*/
