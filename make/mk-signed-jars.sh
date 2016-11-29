#!/bin/sh

if [ "$1" = "install" ]; then

	cp -f $2/jacorb.jar $3
	cp -f $2/$ADAMS_JAVA_DB_DRIVER_CANONICAL_NAME $3

else

	cp $ADAMS_JAVA_ORB_LIB $1
	cp $ADAMS_JAVA_DB_DRIVER_LIB $1/$ADAMS_JAVA_DB_DRIVER_CANONICAL_NAME
	$ADAMS_JARSIGNER -keystore $4.jks -storepass $2 -keypass $3 $1/jacorb.jar $4
	$ADAMS_JARSIGNER -keystore $4.jks -storepass $2 -keypass $3 $1/$ADAMS_JAVA_DB_DRIVER_CANONICAL_NAME $4

fi