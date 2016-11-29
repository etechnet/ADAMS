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

ADAMS_LOGGER_PID_FILE=$ADAMS_INSTALL_DIR/tmp/.adams-ssm_scheduler-pid-$node_id

if [ -f $ADAMS_LOGGER_PID_FILE ]; then

	scheduler_pid=`cat $ADAMS_LOGGER_PID_FILE`

	if [ -n "$scheduler_pid" ]; then
		kill $scheduler_pid
		retcode=$?
		rm $ADAMS_LOGGER_PID_FILE
		echo "Killed SSM Scheduler pid $scheduler_pid"
	fi
fi;

if [ $retcode -ne 0 ]; then
	echo "An error with SSM Scheduler ($retcode)."
fi

exit $retcode

#---
