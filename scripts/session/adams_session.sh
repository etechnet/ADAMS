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
#  ADAMS session controller script
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

# TODO: The following variable is perfectible. I think we should add a dynamic way
#       to specify where ADAMS binaries and FEP data is installed

ADAMS_INSTALL_DIR=""
TRY_INSTALL_DIR=""

check_install_path()
{
	if [ -f $TRY_INSTALL_DIR/bin/adams-utils-iniparser ]; then
		ADAMS_INSTALL_DIR=$TRY_INSTALL_DIR
		TRY_INSTALL_DIR=`$ADAMS_INSTALL_DIR/bin/adams-utils-iniparser --value ADAMS_Globals --value-tag AdamsBaseInstallPath`
		# check for a configured one
		if [ -n "$TRY_INSTALL_DIR" ]; then
			# double check
			if [ -f $TRY_INSTALL_DIR/bin/adams-utils-iniparser ]; then
				ADAMS_INSTALL_DIR=$TRY_INSTALL_DIR
			else
				echo "Unable to find valid configured base path $TRY_INSTALL_DIR"
				exit 1
			fi
		else
			echo "Unconfigured install base path from adams.ini or adams.ini not found."
		fi
	fi
}

# try to find temporary install dir locally
TRY_INSTALL_DIR=`pwd`
check_install_path

if [ -z "$ADAMS_INSTALL_DIR" ]; then
	# try to find a temporary install dir from arguments
	TRY_INSTALL_DIR=`dirname $0`
	while [ -n "$TRY_INSTALL_DIR" ]
	do
		check_install_path
		if [ -z "$ADAMS_INSTALL_DIR" ]; then
			TRY_INSTALL_DIR=`dirname $TRY_INSTALL_DIR`
		else
			break
		fi
	done
fi

if [ -z "$ADAMS_INSTALL_DIR" ]; then
	echo "Unable to find ADAMS install path ($TRY_INSTALL_DIR)"
	exit 1
fi

export ADAMS_INSTALL_DIR

# who we are ?
ADAMS_USERID=$(id -u)
ADAMS_USERNAME=$(id -n -u)
# userproperties=$(getent passwd | grep -m 1 -E "^$userid")
# homedir=$(echo $userproperties | cut -d ":" -f 6);
# gidnr=$(echo $userproperties | cut -d ":" -f 4);
# uidnr=$(echo $userproperties | cut -d ":" -f 3);

if [ "$ADAMS_USERID" = 0 ]; then
	echo "User root cannot run this script..."
	exit 1
fi

export ADAMS_USERID
export ADAMS_USERNAME


usage()
{
		echo
		echo "Usage: $0 <command> <frontend|backend> <adams_node_id>"
		echo "Available commands for ADAMS session: start, stop, restart, status."
}

# Some useful variables
STARTUP_DIR=$ADAMS_INSTALL_DIR/session/startup
SHUTDOWN_DIR=$ADAMS_INSTALL_DIR/session/shutdown

# Check that command line parameters are as expected
ADAMS_NODE_TYPE=$2
ADAMS_NODE_ID=$3

case $ADAMS_NODE_TYPE in
	backend)
		export $ADAMS_NODE_TYPE
	;;

	frontend)
		export $ADAMS_NODE_TYPE
	;;

	*)
		echo "Wrong node type..."
		usage
		exit 1
	;;
esac

if [ -z "$ADAMS_NODE_ID" ]; then
	echo "A node ID/NAME must be specified..."
	usage
	exit 1
fi
# export $ADAMS_NODE_ID

# ensure a tmp directory exists
if [ ! -d "$ADAMS_INSTALL_DIR/tmp" ]; then
	mkdir -p $ADAMS_INSTALL_DIR/tmp
fi

# Do the job...

case $1 in
	start)
		for script in $STARTUP_DIR/*.sh; do
			if [ -x $script ]; then
				eval $script $ADAMS_NODE_TYPE $ADAMS_NODE_ID
			fi
		done
	;;

	stop)
		for script in $SHUTDOWN_DIR/*.sh; do
			if [ -x $script ]; then
				eval $script $ADAMS_NODE_TYPE $ADAMS_NODE_ID
			fi
		done
	;;

	status)
	;;

	restart)
		# Very simple :-) (for now)
		$0 stop $2 $3
		$0 start $2 $3
		exit 0
	;;

	*)
		echo "Wrong directive..."
		usage
		exit 1
	;;

esac

#---
