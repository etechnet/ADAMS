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

#ifndef MDM_SERVER_JOB_H
#define MDM_SERVER_JOB_H

#include <Qt/qthread.h>

class mdm_server_job :  public QThread
{
public:
	explicit mdm_server_job ( QObject* parent = 0 );
	virtual ~mdm_server_job();

	virtual void run();

private:
	void serverjob();
};



#endif // MDM_SERVER_JOB_H
