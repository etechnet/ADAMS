@echo off
rem call java interpreter
java -Djava.endorsed.dirs="/home/pbetti/adams/3rdparty/jacorb/jacorb-3.1/lib" -Djacorb.home="/home/pbetti/adams/3rdparty/jacorb/jacorb-3.1" -Dorg.omg.CORBA.ORBClass=org.jacorb.orb.ORB -Dorg.omg.CORBA.ORBSingletonClass=org.jacorb.orb.ORBSingleton -classpath "%CLASSPATH%" %*
