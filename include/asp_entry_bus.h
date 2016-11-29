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

#ifndef asp_entry_bus_H
#define asp_entry_bus_H

#include <Qt/qobject.h>
#include <Qt/qdatetime.h>
#include <Qt/qstring.h>
#include <Qt/qhash.h>
#include <Qt/qfile.h>
#include <ltextstream.h>
#include <unistd.h>


class asp_entry_bus : public QObject
{
	Q_OBJECT
	Q_CLASSINFO ( "D-Bus Interface", "net.etech.adams.asp_entry_bus" )

public:
	asp_entry_bus();
	virtual ~asp_entry_bus();

public Q_SLOTS:
	QDateTime getCurrentDateTime();

Q_SIGNALS:
	void configurationChanged();
	void iniChanged();

};


#endif // asp_entry_bus_H