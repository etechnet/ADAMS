#!/bin/sh
#
#++
#
#
#                $$$$$$$$\                  $$\
#                \__$$  __|                 $$ |
#  $$$$$$\          $$ | $$$$$$\   $$$$$$$\ $$$$$$$\
# $$  __$$\ $$$$$$\ $$ |$$  __$$\ $$  _____|$$  __$$\
# $$$$$$$$ |\______|$$ |$$$$$$$$ |$$ /      $$ |  $$ |
# $$   ____|        $$ |$$   ____|$$ |      $$ |  $$ |
# \$$$$$$$\         $$ |\$$$$$$$\ \$$$$$$$\ $$ |  $$ |
#  \_______|        \__| \_______| \_______|\__|  \__|
#
#  MODULE DESCRIPTION:
#  Adams libraries build utility
#
#  AUTHORS:
#  Piergiorgio Betti <piergiorgio.betti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  30.07.13 P. Betti            Creation date
#--
#

last_exec_status=

try() {

	"$@"

	# Check if command failed and update $STEP_OK if so.
	local EXIT_CODE=$?

	last_exec_status=$EXIT_CODE
	return $EXIT_CODE
}

BUILD_HOME=${ADAMS_HOME:=`dirname $0`/..}
if [ "$BUILD_HOME" = "." ]; then
	BUILD_HOME=`pwd`/..
fi

rebuild=0
if [ "$1" = "rebuild" ]; then
	rebuild=1
fi

BUILD_LIB_DIRS="3rdparty/corbautil \
		3rdparty/scriptbasic \
		shared/libadams_core \
		shared/libadams_qtcorba \
		shared/libadams_logger \
		shared/libadams_db \
		shared/java_db"


echo "Will build from $BUILD_HOME"
echo
echo

for d in $BUILD_LIB_DIRS
do
	echo; echo "++++++"
	echo "Working in $BUILD_HOME/$d"
	echo "++++++"; echo
	
	if [ "$rebuild" = "1" ]; then
		echo "Cleaning $BUILD_HOME/$d"
		cd $BUILD_HOME/$d; make realclean
	fi
	
	cd $BUILD_HOME/$d; try make
	cd - >& /dev/null
	echo "Build result: $last_exec_status"
	
	if [ "$last_exec_status" -ne "0" ]; then
		exit $last_exec_status
	fi
	
	cd $BUILD_HOME/$d; make install
	cd - >& /dev/null

	echo; echo "======"
	echo "Done in $BUILD_HOME/$d"
	echo "======"; echo
done

