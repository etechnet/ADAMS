@echo off
java -classpath "/home/pbetti/adams/3rdparty/jacorb/jacorb-3.1\lib\idl.jar;%CLASSPATH%" org.jacorb.idl.parser %*
