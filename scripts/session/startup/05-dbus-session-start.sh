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

#
# do a check whether dbus-daemon is already running
# dbus-daemon needs to be started for every ADAMS node/session
#

if [ -f $ADAMS_DBUS_SESSION_FILE ]; then

	# do a check the dbus-daemon for this node is running with the pid
	# in the .dbus-session file

	# pid according to the ps command
	ps_dbus_session_pid_list=$(ps aux | grep -E "^$ADAMS_USERNAME.*dbus-daemon.*session.*" \
				| grep -v "grep" | sed 's@[[:space:]][[:space:]]*@ @g' | cut -d " " -f 2)

	# read the pid from the .dbus-session file
	# . $ADAMS_DBUS_SESSION_FILE
	OLD_DBUS_SESSION_BUS_PID=`cat $ADAMS_DBUS_SESSION_FILE | grep DBUS_SESSION_BUS_PID | cut -d'=' -f2 | cut -d';' -f1`

# 	echo "pid list: $ps_dbus_session_pid_list"
# 	echo "OLD_DBUS_SESSION_BUS_PID=$OLD_DBUS_SESSION_BUS_PID"
	# check they are the same
	if [ -z "$ps_dbus_session_pid_list" ]; then

	# dbus for this user/session not running
	rm $ADAMS_DBUS_SESSION_FILE

	else
		for pid in $ps_dbus_session_pid_list; do
			echo "check $pid $OLD_DBUS_SESSION_BUS_PID"
			if [ "$OLD_DBUS_SESSION_BUS_PID" = "$pid" ]; then

				# stop dbus-daemon for this user/session
				# and remove .dbus-session file
				# ignore uknown dbus pids

				echo "ADAMS D-Bus session for us is still running, pid: $pid"
				kill -TERM $pid;
				echo "Killed..."
				break
			fi
		done
	fi
	rm $ADAMS_DBUS_SESSION_FILE
fi

if [ ! -f $ADAMS_DBUS_SESSION_FILE ]; then

# only start a dbus session if .dbus-session file it not found
# in users homedirectory

	dbus-launch --sh-syntax > $ADAMS_DBUS_SESSION_FILE
	retcode=$?
#     echo "launched "

fi

if [ $retcode -ne 0 ]; then
	echo "An error with dbus ($retcode)."
else
	echo "D-Bus Session for $node_type $node_id started."
fi

# Export all
# . $ADAMS_DBUS_SESSION_FILE

exit $retcode