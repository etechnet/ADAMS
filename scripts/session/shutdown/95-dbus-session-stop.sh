#!/bin/bash

#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$\ $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  D-Bus session stop
#
#  AUTHORS:
#  Piergiorgio Betti <piergiorgio.betti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  14.12.12 Piergiorgio Bettie  Creation date
#--
#


retcode=0;
node_type=$1
node_id=$2

ADAMS_DBUS_SESSION_FILE=$ADAMS_INSTALL_DIR/tmp/.adams-dbus-session-$node_type-$node_id
export ADAMS_DBUS_SESSION_FILE


if [ -f $ADAMS_DBUS_SESSION_FILE ]; then

	. $ADAMS_DBUS_SESSION_FILE

	if [ -n "$DBUS_SESSION_BUS_PID" ]; then
		kill $DBUS_SESSION_BUS_PID
		retcode=$?
		rm $ADAMS_DBUS_SESSION_FILE
		echo "Killed D-Bus pid $DBUS_SESSION_BUS_PID"
	fi
fi;

if [ $retcode -ne 0 ]; then
	echo "An error with dbus ($retcode)."
fi

exit $retcode

#---
