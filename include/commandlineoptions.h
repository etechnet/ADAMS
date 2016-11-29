/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */

#ifndef COMMANDLINEOPTIONS_H
#define COMMANDLINEOPTIONS_H

#include <vector>
#include <string>
#include <stdlib.h>
#include <string.h>

enum __arg_types {
        ARG_ON_OFF = 1,
        ARG_STRING,
        ARG_STRINGS,
        ARG_SET,
        ARG_CLEAR,
        ARG_YES_NO,
        ARG_IGNORE,
        ARG_INT,
        ARG_DOUBLE,
        ARG_ADD,
        ARG_CALLBACK
};

// class string;
// class string_vector;

using namespace std;

typedef vector<string>	string_vector;

/**	Evaluate command line options.
	@author Piergiorgio Betti <pbetti@lpconsul.net>

	The kind of options it processes are summaries as follows:

	1. on-off switch: -X|--longX on|off
		eg
		--sound on
		--sound off
		-s on
		-s off

	2. yes-no switch: (same as on-off)

	3. Ordinary option: -X|--longX
		eg
		--verbose
		-v

	4. String options: -X|--longX <some_string>
		eg
		--file program.c
		-f program.c

	5. Integer or float options: -X|--longX N
		eg
		--count 20
		-C 20

	6. String list options: -X|--longX <file1> <file2> <file3> ...
		eg
		--files hello.c whereis.c prog.c
		-f hello.c whereis.c prog.c


	Example:

	bool appendmode = false;
	bool kibozefnames = false;
	bool googleimport = false;
	string active_newsgroup = "";
	string_vector articles_list;
	string start_time = "";
	string end_time = "";
	string msg_id = "";

	// 	argc = myargc;
	// 	argv = myargv;

	// evaluate command line
	CommandLineOptions cmdopt(argc, argv);
	cmdopt.addOptionSet('a', "", "--append", &appendmode, "Append news articles to database.");
	cmdopt.addOptionSet('k', "", "--kiboze-names", &kibozefnames, "Assume input files are produced by kiboze.");
	cmdopt.addOptionSet('g', "", "--from-google", &googleimport, "Import articles from Google download.");
	cmdopt.addOptionString('n', "-ng", "--newsgroup", &active_newsgroup, "Processed newsgroup name (mandatory).");
	cmdopt.addOptionString(' ', "", "--start-time", &start_time, "Limit output beginning from this articles date.");
	cmdopt.addOptionString(' ', "", "--end-time", &end_time, "Limit output up to this articles date.");
	cmdopt.addOptionString('i', "-id", "--msg-id", &msg_id, "Get article by message id.");
	cmdopt.addOtherArgs( &articles_list );

	bool eval_opts = cmdopt.get_cmdline_options();

*/


class CommandLineOptions
{
public:
	CommandLineOptions ( int ac, char **av ) : arg_count ( 0 ), argc ( ac ), argv ( av ) {}

	~CommandLineOptions() {}

	int get_cmdline_options ();
	void addOption ( const char char_opt, const char * short_opt, const char * long_opt, int type,
	                 string * str = 0, string_vector * strs = 0, void * option = 0, const char * help = 0 );
	void showUsage ( const char * usage_text );

	inline void addOptionStrings ( const char char_opt, const char * short_opt, const char * long_opt, string_vector * strs = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_STRINGS, 0, strs, 0, help );
	}

	inline void addOptionString ( const char char_opt, const char * short_opt, const char * long_opt, string * str = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_STRING, str, 0, 0, help );
	}

	inline void addOptionSet ( const char char_opt, const char * short_opt, const char * long_opt, void * option = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_SET, 0, 0, option, help );
	}

	inline void addOptionClear ( const char char_opt, const char * short_opt, const char * long_opt, void * option = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_CLEAR, 0, 0, option, help );
	}

	inline void addOptionOnOff ( const char char_opt, const char * short_opt, const char * long_opt, void * option = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_ON_OFF, 0, 0, option, help );
	}

	inline void addOptionInt ( const char char_opt, const char * short_opt, const char * long_opt, void * option = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_INT, 0, 0, option, help );
	}

	inline void addOptionDouble ( const char char_opt, const char * short_opt, const char * long_opt, void * option = 0, const char * help = 0 ) {
		addOption ( char_opt, short_opt, long_opt, ARG_DOUBLE, 0, 0, option, help );
	}

	inline void addOtherArgs ( string_vector * strs, char * help = 0 ) {
		addOption ( ' ', "", "", ARG_STRINGS, 0, strs, 0, help );
	}

private:
	class prog_options
	{
	public:
		char char_opt;
		char * short_opt;
		char * long_opt;
		int type;		/* one of the #define's above */
		string * str;		/* pointer to a single string */
		string_vector * strs;	/* pointer to an array of strings */
		void * option;		/* an integer or double */
		char * help;		/* help line */

		prog_options() {
			short_opt = 0;
			long_opt = 0;
			help = 0;
		}

		prog_options ( const prog_options & in ) {
			if ( in.short_opt ) short_opt = strdup ( in.short_opt );
			if ( in.long_opt ) long_opt = strdup ( in.long_opt );
			if ( in.help ) help = strdup ( in.help );
			char_opt = in.char_opt;
			type = in.type;
			str = in.str;
			strs = in.strs;
			option = in.option;
		}

		~prog_options() {
			if ( short_opt ) free ( short_opt );
			if ( long_opt ) free ( long_opt );
			if ( help ) free ( help );
		}

		prog_options & operator= ( const prog_options & in ) {
			if ( in.short_opt ) short_opt = strdup ( in.short_opt );
			if ( in.long_opt ) long_opt = strdup ( in.long_opt );
			if ( in.help ) help = strdup ( in.help );
			char_opt = in.char_opt;
			type = in.type;
			str = in.str;
			strs = in.strs;
			option = in.option;

			return *this;
		}
	};

	vector<prog_options> args;
	int arg_count;
	int argc;
	char **argv;
};

#endif

