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

#ifndef ssm_scheduler_bus_H
#define ssm_scheduler_bus_H

#include <Qt/qobject.h>
#include <Qt/qdatetime.h>
#include <Qt/qstring.h>
#include <Qt/qhash.h>
#include <Qt/qfile.h>
#include <ltextstream.h>
#include <unistd.h>


class ssm_scheduler_bus : public QObject
{
	Q_OBJECT
	Q_CLASSINFO ( "D-Bus Interface", "net.etech.adams.ssm_scheduler_bus" )

public:
	ssm_scheduler_bus();
	virtual ~ssm_scheduler_bus();

	void emit_configurationChanged() {
		emit configurationChanged();
	}

	void emit_iniChanged() {
		emit iniChanged();
	}

public Q_SLOTS:
	QDateTime getCurrentDateTime();

Q_SIGNALS:
	void configurationChanged();
	void iniChanged();

};


#endif // ssm_scheduler_bus_H
