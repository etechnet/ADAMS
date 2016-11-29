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

#ifndef NODEPROCESSTABLE_H
#define NODEPROCESSTABLE_H

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <Qt/qlist.h>

typedef struct {
	int node_id;
	QString process_name;
	int process_type;
	int process_id;
	int active_flag;
	int schedule_ignore;
	QTime wake_time;
	QString start_cmd;
	QDateTime last_restart;
	int num_restart;
	int pid;
	int log_level;
	QString log_output;
	QString log_pipe_cmd;
	int assigned_port;

} NodeProcessTable;

typedef QList<NodeProcessTable> NodeProcessList;

#endif
