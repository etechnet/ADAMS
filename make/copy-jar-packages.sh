#!/bin/sh

INSTALL_DIR=$1

shift

for jarfile in $*
do
	cp -f $ADAMS_BUILD_INSTALL/$ADAMS_INSTALL_JAR_PACKAGES_DIR/$jarfile $INSTALL_DIR
done
