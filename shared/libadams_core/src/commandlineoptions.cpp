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


#include <commandlineoptions.h>

#include <iostream>
// #include <qstring.h>
// #include <qstringlist.h>

/*	Evaluate command line options.
	@author Piergiorgio Betti <pbetti@lpconsul.net>
*/

int CommandLineOptions::get_cmdline_options ()
{
	int i, j, c;
	for ( i = 1; i < argc; i++ ) {
		if ( *argv[ i ] != '-' ) {
			/* something that is not an option */
			for ( j = 0; j < args.size(); j++ )
				if ( args[ j ].char_opt == ' ' &&
				                ( ( args[ j ].short_opt ) ? ( string ( args[ j ].short_opt ) == "" ) : true ) &&
				                ( ( args[ j ].long_opt ) ? ( string ( args[ j ].long_opt ) == "" ) : true )
				   ) {
					args[ j ].strs->push_back ( string ( argv[i] ) );
					goto cont;
				}
			return i;
		}
		c = 0;
		while ( ++c > 0 ) {
			/* try each letter in a combined option eg 'tar -xvzf' */
			for ( j = 0; j < args.size(); j++ ) {
				if ( !strcmp ( args[ j ].long_opt, argv[ i ] ) || !strcmp ( args[ j ].short_opt, argv[ i ] ) ) {
					c = -1;	/* not a combined option */
					goto valid_opt;
				}
				if ( argv[ i ][ 0 ] == '-' && argv[ i ][ c ] == args[ j ].char_opt ) {
					if ( !argv[ i ][ c + 1 ] )  	/* this must be the last letter in the combined option */
						c = -1;
					goto valid_opt;
				}
				continue;

			valid_opt:
				switch ( args[ j ].type ) {
					case ARG_SET: {
							bool * t;
							t = ( bool * ) args[ j ].option;
							*t = true;
							goto next;
						}
					case ARG_ADD: {
							int *t;
							t = ( int * ) args[ j ].option;
							( *t ) ++;
							goto next;
						}
					case ARG_CLEAR: {
							int *t;
							t = ( int * ) args[ j ].option;
							*t = 0;
							goto next;
						}
					case ARG_IGNORE:
						/* do nothing with this option */
						goto next;
				}

				if ( i + 1 != argc && argv[ i + 1 ]
				                && c < 0	/* must be the last option if a combined option */
				   ) {
					++i;
					switch ( args[ j ].type ) {
							int * t;
							double *f;
						case ARG_ON_OFF:
							if ( strcmp ( argv[ i ], "on" ) == 0 ) {
								t = ( int * ) args[ j ].option;
								*t = 1;
							} else if ( strcmp ( argv[ ++i ], "off" ) == 0 ) {
								t = ( int * ) args[ j ].option;
								*t = 0;
							} else
								return i;
							goto next;
						case ARG_YES_NO:
							if ( strcmp ( argv[ i ], "yes" ) == 0 ) {
								t = ( int * ) args[ j ].option;
								*t = 1;
							} else if ( strcmp ( argv[ ++i ], "no" ) == 0 ) {
								t = ( int * ) args[ j ].option;
								*t = 0;
							} else
								return i;
							goto next;
						case ARG_CALLBACK: {
								int ( *callb ) ( int, char * );
								callb = ( int ( * ) ( int, char * ) ) args[ j ].option;
								if ( ( *callb ) ( ( int ) args[ j ].char_opt, argv[ i ] ) )
									return i - 1;
								goto next;
							}
						case ARG_STRING:
							( *args[ j ].str ) = argv[ i ];
							goto next;
						case ARG_STRINGS: {
								/* get all argv's after this option until we reach another option */
								while ( i < argc && *argv[ i ] != '-' ) {
									args[ j ].strs->push_back ( string ( argv[ i ] ) );
									i++;
								}
								i--;	/* will be incremented at end of loop */
								goto next;
							}
						case ARG_INT:
							t = ( int * ) args[ j ].option;
							*t = atoi ( argv[ i ] );
							goto next;
						case ARG_DOUBLE:
							f = ( double * ) args[ j ].option;
							*f = atof ( argv[ i ] );
							goto next;
					}
					i--;
				}
				return i;	/* option parameter not found */
			}			/* j */
			return i;		/* option not found */
		next:
			;
		}			/* c */
	cont:
		;
	}
	return 0;
}

void CommandLineOptions::addOption ( const char char_opt, const char * short_opt, const char * long_opt, int type,
                                     string * str, string_vector * strs, void * option, const char * help )
{
	prog_options o;
	o.char_opt = char_opt;
	if ( short_opt ) o.short_opt = strdup ( short_opt );
	if ( long_opt ) o.long_opt = strdup ( long_opt );
	o.type = type;
	o.str = str;
	o.strs = strs;
	o.option = option;
	if ( help ) o.help = strdup ( help );

	args.push_back ( o );
}

#define SHO_W	6		// how to display help ??
#define LNG_W	18
#define DSC_W	55

void CommandLineOptions::showUsage ( const char * usage_text )
{
	cerr << usage_text << endl;
	cerr.width ( SHO_W );
	cerr << left << "Short";
	cerr.width ( LNG_W );
	cerr << left << "Long";
	cerr.width ( DSC_W );
	cerr << left << "Description";
	cerr << endl;
	cerr.width ( SHO_W - 1 );
	cerr.fill ( '-' );
	cerr << "" << " ";
	cerr.width ( LNG_W - 1 );
	cerr.fill ( '-' );
	cerr << "" << " ";
	cerr.width ( DSC_W - 1 );
	cerr.fill ( '-' );
	cerr << "" << " ";
	cerr << endl;
	cerr.fill ( ' ' );
	for ( int j = 0; j < args.size(); j++ ) {
		if ( args[j].char_opt != ' ' ||
		                ( ( args[ j ].short_opt ) ? ( string ( args[ j ].short_opt ) != "" ) : true ) ||
		                ( ( args[ j ].long_opt ) ? ( string ( args[ j ].long_opt ) != "" ) : true )
		   ) {
			string shopt = ( args[j].char_opt != ' ' ) ? string ( "-" ) + args[j].char_opt : "";
			cerr.width ( SHO_W );
			cerr << left << shopt.c_str();
			if ( string ( args[j].short_opt ).size() > 0 ) {
				if ( args[j].long_opt )
					shopt = string ( args[j].short_opt ) + " or";
				else
					shopt = args[j].short_opt;
				cerr.width ( LNG_W );
				cerr << left << shopt;
				if ( args[j].long_opt ) {
					cerr << endl;
					cerr.width ( SHO_W );
					cerr << "";
				}

			}
			if ( args[j].long_opt )
				cerr.width ( LNG_W );
			cerr << left << args[j].long_opt;
		}
		if ( args[j].help ) {
			cerr.width ( DSC_W );
			cerr << left << args[j].help;
		} else if ( args[j].char_opt != ' ' ) {
			cerr.width ( DSC_W );
			cerr << left << "(undocumented).";
		}
		cerr << endl;
	}

	cerr << endl;
}

