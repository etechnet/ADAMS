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

#ifndef MDM_AGENT_FACTORY_H
#define MDM_AGENT_FACTORY_H

#include <Qt/qthread.h>

class mdm_agent_factory :  public QThread
{
public:
	explicit mdm_agent_factory ( QObject* parent = 0 );
	virtual ~mdm_agent_factory();

	virtual void run();

private:
	void server_factory();
};



#endif // MDM_AGENT_FACTORY_H
