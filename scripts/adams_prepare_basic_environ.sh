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

ADAMS_INSTALL_DIR="/opt/adams"
TRY_INSTALL_DIR=""


setup_profile()
{
echo "

# ADAMS auto generated .profile section
# do not touch comment below

#ADAMS install

ADAMS_INSTALL_DIR=$ADAMS_INSTALL_DIR
export ADAMS_INSTALL_DIR

export PATH=\$ADAMS_INSTALL_DIR/bin:\$ADAMS_INSTALL_DIR/scripts:\$ADAMS_INSTALL_DIR/session:\$PATH

if [ -d "\$ADAMS_INSTALL_DIR/lib" ]; then
	export LD_LIBRARY_PATH=\$ADAMS_INSTALL_DIR/lib:\$LD_LIBRARY_PATH
fi

if [ -d "\$ADAMS_INSTALL_DIR/lib64" ]; then
	export LD_LIBRARY_PATH=\$ADAMS_INSTALL_DIR/lib64:\$LD_LIBRARY_PATH
fi

# uncomment below if you want BEP services autostart
# adams_session.sh start backend BEP

#ADAMS install end

" >> $homedir/.profile

}

check_install_path()
{
	if [ ! -f $TRY_INSTALL_DIR/bin/adams-utils-iniparser ]; then
		echo "Invalid install path, or $TRY_INSTALL_DIR/bin is empty."
		exit 1
	fi
}

# try to find temporary install dir locally
TRY_INSTALL_DIR=$1
if [ -z "$TRY_INSTALL_DIR" ]; then
	echo "Specify path to adams install dir"
	exit 1
fi

check_install_path

# who we are ?
ADAMS_USERID=$(id -u)
ADAMS_USERNAME=$(id -n -u)

if [ "$ADAMS_USERID" -ne "0" ]; then
	echo "You must be root to run this script..."
	exit 1
fi

if [ -n "$2" ]; then
	ADAMS_USER_HOME=$2
fi

# export ADAMS_USERID
# export ADAMS_USERNAME

groupadd -f adams
if [ -n "$ADAMS_USER_HOME" ]; then
	useradd --home $ADAMS_USER_HOME --gid adams --groups users --create-home --shell /bin/bash adams
else
	useradd --gid adams --groups users --create-home --shell /bin/bash adams
fi

echo adams:adams | chpasswd 2> /dev/null

userproperties=$(getent passwd | grep -m 1 -E "^adams")
homedir=$(echo $userproperties | cut -d ":" -f 6);
gidnr=$(echo $userproperties | cut -d ":" -f 4);
uidnr=$(echo $userproperties | cut -d ":" -f 3);

echo "ADAMS user homedir: $homedir"

mkdir -p $homedir/logs
mkdir -p $homedir/data
mkdir -p $homedir/.adams
mkdir -p $homedir/conf

chown -R adams:adams $homedir

PROFILE_READY=`grep '#ADAMS install' $homedir/.profile`

if [ -z "$PROFILE_READY" ]; then
	setup_profile
fi

# ----- TEMP TRICK to simulate install

ln -s $TRY_INSTALL_DIR /opt/adams
ln -s $TRY_INSTALL_DIR/../scripts /opt/adams/scripts
ln -s $TRY_INSTALL_DIR/../scripts/session /opt/adams/session
ln -s $TRY_INSTALL_DIR/../conf /opt/adams/share

chmod 777 /opt/adams/tmp

# ----- TEMP TRICK to simulate install


#---



