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
#  D-Bus session start
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
# export ADAMS_DBUS_SESSION_FILE

echo "SSM Scheduler starting."

#
# do a check whether dbus-daemon file is available
# dbus-daemon NEEDS to be started for every ADAMS node/session
#

if [ ! -f $ADAMS_DBUS_SESSION_FILE ]; then
	retcode=1
	echo "$0: dbus-daemon session file is not available."
	exit $retcode
fi

. $ADAMS_DBUS_SESSION_FILE

# launch the scheduler

scheduler_port=`$ADAMS_INSTALL_DIR/bin/adams-utils-ssmparser -p ssm_scheduler -n $node_id -t t_node_process -v assigned_port`
if [ -z "$scheduler_port" ]; then
	echo "Warning: unassigned listen port number for ssm_scheduler."
else
	port_opt="--port $scheduler_port"
fi

$ADAMS_INSTALL_DIR/bin/ssm_scheduler --node $node_id --dbus $DBUS_SESSION_BUS_ADDRESS $port_opt --daemon
retcode=$?

if [ $retcode -ne 0 ]; then
	echo "An error with SSM Scheduler ($retcode)."
fi

exit $retcode
