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

#ifndef mdm_job_scheduler_bus_H
#define mdm_job_scheduler_bus_H

#include <Qt/qobject.h>
#include <Qt/qdatetime.h>
#include <Qt/qstring.h>
#include <Qt/qhash.h>
#include <Qt/qfile.h>
#include <unistd.h>


class mdm_job_scheduler_bus : public QObject
{
	Q_OBJECT
	Q_CLASSINFO ( "D-Bus Interface", "net.etech.adams.mdm_job_scheduler_bus" )

public:
	mdm_job_scheduler_bus();
	virtual ~mdm_job_scheduler_bus();

public Q_SLOTS:
	void dummy();
};


#endif // mdm_job_scheduler_bus_H
