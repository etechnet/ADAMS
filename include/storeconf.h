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

#ifndef STORECONF_H
#define STORECONF_H

#include <configuretypedefs.h>
#include <queryparams.h>

/** Questa classe fornisce un semplice meccanisco di accesso ai dati di configurazione ADAMS
    tramite metodi statici.
    Si utilizza per avere un accesso interclasse alla AdamsCompleteConfig senza l'utilizzo di
    dichiarazioni "extern".
  *@author Piergiorgio Betti
  */

class StoreConf {
public:
	StoreConf();
	~StoreConf() {}

	static AdamsCompleteConfig * config();
	static QueryParams * params();
};

#endif
