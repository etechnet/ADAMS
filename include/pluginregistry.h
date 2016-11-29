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

/***************************************************************************
                          pluginregistry.h  -  description
                             -------------------
    begin                : Mon Jan 13 2003
    copyright            : (C) 2003 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef PLUGINREGISTRY_H
#define PLUGINREGISTRY_H

#include <adams_limits.h>

/** In questa classe vengono gestite le informazioni
  * di configurazione relative ai plugin di uso "generico" ovvero legati
  * agli elementi di traffico e/o simili come la gestione dei report.
    @short Definizione dati di configurazione plugins
  * @author Piergiorgio Betti
  */

class PluginRegistry {
public:
	enum plrUsage {
		DataElementHandle,
		ReportFieldHandle,
		AlarmGeneratorHandle,
		ThresholdGeneratorHandle,
		GUIHandle,
		plrUsageNumber
        };


	PluginRegistry() { memset((void*)&data, 0, sizeof(DATA)); }
	~PluginRegistry() {}

		/** Metodo statico utilizzato per avere la dimensione in byte della struttura dati */
	static int dataSize() { return sizeof(DATA); }

	typedef struct {
		int idPlugin;					// unique id
		char tag[SHORT_DESC_LEN];			// descrizione breve (tag)
		char pluginName[PLR_PLUGINNAME_LEN];		// plugin (file) name
		plrUsage usage;					// kind of plugin/utlization
		bool enabled;					// active or deletion scheduled
	} DATA;

	DATA data;

};

#endif
