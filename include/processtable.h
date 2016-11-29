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
#  Author Name <raffale.ficcadenti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--08-10-13 Raffaele Ficcadenti
#
*/

#ifndef PROCESS_TABLE_H
#define PROCESS_TABLE_H

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <Qt/qlist.h>

typedef struct {
	int process_id;
	QString process_name;
	int process_type;
	int schedule_ignore;
	QString start_cmd;
	QDateTime wake_time;
} ProcessTable;

typedef QList<ProcessTable> ProcessList;
typedef QList<ProcessTable>::iterator ProcessList_it;

#endif
