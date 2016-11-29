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

#ifndef CONFIGPARSER_H
#define CONFIGPARSER_H

#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>

#include <Qt/qstring.h>
#include <Qt/qstringlist.h>

#define ADAMSINIFILE		"adams.ini"

#define parDELIMITERS		" \t"			/* Delimitatori ammessi */
#define parARGSEPARATOR		"="			/* Separatore id /valore */

#define MAXITEMSPERSECTION	1024			/* max admitted items per section */


class ConfigParser
{
public:

	typedef enum {
	        GenericValue,
	        PathValue
	} ConfValueType;

	ConfigParser();
	~ConfigParser();
	/** This methos returns the content (tipically a list) of definitions contained into a section */
	QStringList parQTxtGetSection ( const QString & sectnam );
	/** This method return tha value of "tag=value" couple located in the specified section */
	QString parQTxtGetValue ( const QString & valueid, const QString & sectnam, ConfValueType value_type = ConfigParser::GenericValue );
	/** Try to locate ADAMS .ini file from a list of known paths */
	bool locateFile ();
	/** Overloaded method. Try to locate ADAMS .ini file from a list of known paths adding executable path to the list */
	bool locateFile ( QString exename );
	/** Overloaded method. Try to locate ADAMS generic configuration file from a list of known paths */
	QString locateUserFile ( const QString & file_name );
	/** Set/force .ini filename */
	inline void setConfigFileName ( const QString & filename ) {
		adams_ini_name = filename;
	}
	/** Get .ini filename */
	inline QString getConfigFileName() {
		return adams_ini_name;
	}
	/** Get .ini filename and path */
	inline QString getConfigFilePath() {
		return adams_ini_file;
	}
	/** Add a new path to the list used by @ref locateFile() */
	inline void addSearchPath ( const QString & new_path ) {
		adams_ini_known_paths << new_path;
	}
	/** Add specialization mark tags to section search algorithm.
	 * Tag marker can be concatenaed using "." as tag separator. Example : "this.mark.mysection"
	 */
	static void addMarkerTagPath ( const QString & marker_string );
	/** Invalidate cached data (i.e. for an update of the .ini file) */
	static void invalidateCache();
	/** Try to correct value depending on its ConfValueType */
	QString finalizeValue ( const QString & value, ConfValueType value_type );
	/** Substitute (expands) variables in values */
	void parseVariables ( QString & in, const QString & sectnam );

private:
	const char * txtDelimiters;
	QString adams_ini_name;
	QString adams_ini_file;
	QStringList adams_ini_known_paths;

	/** Deletes leading blanks */
	void unleadch ( char *s );
	/** Deletes trailing blanks */
	void untrailch ( char *s );
	/** Find section header */
	bool TestSection ( char *tData, const char *fSectid );
	/** libc __findenv replica */
	char *__findenv ( const char *name, int *offset );
	/** libc getenv replica */
	char * getenv ( const char *name );
	QString tilde_expand ( const QString & path );
	QStringList parQTxtGetSection_private ( const QString & sectnam );
	QString parQTxtGetValue_private ( const QString & valueid, const QString & sectnam, const QStringList * s_list_ptr = 0 );
	QString getValueLoop ( const QString & valueid, const QString & sectnam, QString * valid_key, const QStringList * s_list_ptr );
	void putSectionInCache ( const QString & cache_key, const QStringList & cache_list );
};

#endif
