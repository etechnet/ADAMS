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


#ifndef _ADAMS_PLUGIN_REGISTER
#define _ADAMS_PLUGIN_REGISTER

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>


typedef enum {
	Adams_CounterHandler,				// gestore contatori ADAMS
	Adams_CDRReader,				// lettore cartellini ADAMS
	Adams_ElementHandler,				// gestore elemento di traffico ADAMS
	Monitor_PlTest,                                 // test plugin per Monitor
	Monitor_Plugin,                                 // plugin per Monitor
	Common_PlugInHelp,				// gestore lista help per selezioni utente.
	Adams_DescriptionHelper,			// gestore help/descrizioni ADAMS
	Adams_ReportHandler,				// gestore stampe a formattazione fissa
	Adams_AlarmCondition,				// gestione condizioni contatori allarmi
	Adams_AlarmGenerator,				// generatore allarmi
	Adams_ThresholdGenerator,			// generatore soglie
	adamsNumRegisteredPluginsType
} ADAMSPluginType;


/** Struttura utilizzata per fornire al caller le informazioni identificative
  * del plugin
  */

typedef struct {
	ADAMSPluginType globalTypeID;
	QString name;
	QString description;
	int majorVersionNumber;
	int minorVersionNumber;
	QDateTime releaseDate;
	QString vendorName;
	QString author;
} PluginInfo;




#endif /* _ADAMS_PLUGIN_REGISTER */
