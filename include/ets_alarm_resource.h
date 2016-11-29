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

#ifndef _AMS_ALARM_RESOURCE_H
#define _AMS_ALARM_RESOURCE_H

#include <pluginbase.h>
#include <pluginsapi.h>
#include <definealarmrelation.h>
#include <common_alarm_typedef.h>

#include <Qt/qstring.h>
#include <Qt/qmap.h>


class AMS_ALARM_RESOURCE {
public:
	AMS_ALARM_RESOURCE() : id(0), counters(0), defRel(0),
	n_int(0), n_dbl(0), userDataSize(0),
	policy_loaded(false)
	{
		for (int i = 0; i < MAX_ALARM_GENERATOR; i++) {
			handler[i].id = 0;
			handler[i].plugin = 0;
		}
		for (int i = 0; i < MAX_DIMENSION; i++) {
			help_present[i] = false;
		}
	}

	int id;
	struct {
		int id;
		PluginImpl * plugin;
	} handler[MAX_ALARM_GENERATOR];
	QMap<QString, int> threshold_ids;
	Counters * counters;
	DefineAlarmRelation * defRel;
	int userDataSize;
	int n_int;
	int n_dbl;
	ALARM_POLICY policy;
	bool policy_loaded;
	bool help_present[MAX_DIMENSION];
};


#endif	// _AMS_ALARM_RESOURCE_H