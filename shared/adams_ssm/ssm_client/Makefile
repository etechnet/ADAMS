#
#++
#  FACILITY:MAKEFILE For MonitorClient
#
#  MODULE DESCRIPTION: Monitor Client
#
#  AUTHORS: Ficcadenti Raffaele & Beltrame Luca
#
#  CREATION DATE:   Apr-2004
#  DESIGN ISSUES:
#
#
#   MODIFICATION HISTORY:
#   -[Date]- -[Who]------------- -[What]---------------------------------------
#
#--
#

DIRNAME  = ssm_client
SRCTREE  = GRAPH
#
#
#  INCLUDE FILES
#  - Path
#  - Flag compilazione e link
#

# --
include ../../../../MAKE/make_path.mk
include ../../../../MAKE/orbixjava.config
# --

#
# inclusione direttive locali
#

include $(PATMAK)/make_java.mk

#
# File sorgenti e librerie dell'applicativo
#

SOURCE_JAVA =	SSM_GlobalParam.java\
		SSM_JF_LoginStart.java\
		SSM_D_ChangePWD.java\
		ProcessMonitor.java\
		SSM_JListIcon.java\
		SSM_IconPool.java\
		SSM_JF_MonitoringOutput.java\
		SSM_JF_MonitoringSetup.java\
		SSM_JP_ProcessRow.java\
		SSM_JTextFieldFilter.java\
		SSM_CORBAConnection.java\
		SSM_JF_textArea.java\
		SSM_JF_ConfigSetup.java\
		SSM_JF_LoggerRow.java\
		SSM_JF_LoggerOutput.java\
		SSM_JF_ConfigLog.java\
		SSM_JD_SelectBlock.java\
		SSM_JF_ACQPebMgr.java \
		JD_Message.java\
		SSM_JF_GarbageMonitor.java\
		checkSystem.java\
		SSM_JF_MonitoringOrbix.java\
		TableSorter.java\
		SSM_ProcessRowNew.java\
		SSM_JF_ManageOrbix.java

JAR_LIST = 	$(CLASSES_JAVA)

LOC_JAR_FILE =  processmonitor.jar
JAR_FILE =	$(PATEXE)/$(LOC_JAR_FILE)

IDL_FILE = 		psmonitormasterserver.idl
SRC_IDL_FILE =		../psmonitormasterserver/psmonitormasterserver/$(IDL_FILE)

IDL_FILE1 =		stsconfigserver.idl
SRC_IDL_FILE1 =	../../stsconfig/stsconfigserver/stsconfigserver/$(IDL_FILE1)

IDL_FILE2 =             ntmdataserver.idl
SRC_IDL_FILE2 = ../../Ntm4/ntmshared/$(IDL_FILE2)

IDL_INCLUDE =		psmonitorms.h
SRC_IDL_INCLUDE =	../psmonitormasterserver/psmonitormasterserver/$(IDL_INCLUDE)

IDL_INCLUDE1 =		idlincludes.h
SRC_IDL_INCLUDE1 = 	../../stsconfig/stsconfigserver/stsconfigserver/$(IDL_INCLUDE1)

IDL_INCLUDE2 =          ntmlimits.h
SRC_IDL_INCLUDE2 =      $(STS_HOME)/STS/INCLUDE/ntmshared/$(IDL_INCLUDE2)

EXE = $(JAR_FILE)

EXTRA_DEPENDS = ../../Ntm4/ntmshared/ntmdataserver.idl

CP=cp
MV=mv

#LIBS =		$(PATLIB)/ \
#		$(PATLIBGLB)/

#
# flag locali
#

LOCAL_DEFINE =
LOCAL_INCLUDE =
LOCAL_CFLAGS =
LOCAL_LFLAGS =

# --
include $(PATMAK)/make_comp.mk
# --

#
# Dipendenze moduli
#

all: 		$(JAR_FILE)

distclean: realclean


#
# jar non firmato
#

$(JAR_FILE):	$(SOURCE_JAVA) $(SRC_IDL_FILE) $(SRC_IDL_FILE1) $(SRC_IDL_FILE2) $(SRC_IDL_INCLUDE) $(SRC_IDL_INCLUDE1) $(SRC_IDL_INCLUDE2) $(EXTRA_DEPENDS)
		if [ ! -d java_output ]; then $(MKDIR) java_output; fi;
		$(JAVAIDL) --clone --output-dir java_output -I$(STS_HOME)/STS/INCLUDE -I../../stsconfig/stsconfigserver/stsconfigserver $(SRC_IDL_FILE) $(SRC_IDL_FILE1) $(SRC_IDL_FILE2)
		$(CP) -f java_output/*.java .
		$(CP) -f ./JarDir/*.jar .
		FILES=`find java_output -name \*.java -print` ; \
		$(JAVAC) -classpath .:$(CLASSPATH):./java_output $$FILES
		$(JAVAC) -classpath .:$(CLASSPATH):./java_output $(SOURCE_JAVA)
		FILES=`find java_output -name \*.class -print` ; \
		$(MV) -f $$FILES .
		$(JAR) cf $(JAR_FILE) *.class images
		jarsigner -keystore pmonitor_StoreKey -storepass pmonitor_signer01 -keypass pmonitor_signer02 $(JAR_FILE) STS
		jarsigner -keystore pmonitor_StoreKey -storepass pmonitor_signer01 -keypass pmonitor_signer02 OB.jar STS
		jarsigner -keystore pmonitor_StoreKey -storepass pmonitor_signer01 -keypass pmonitor_signer02 OBNaming.jar STS
		jarsigner -verify $(JAR_FILE)
		jarsigner -verify OB.jar
		jarsigner -verify OBNaming.jar
		$(CP) -f $(JAR_FILE) .
		$(CP) -f *html* $(PATEXE)/.
		$(CP) -f OB.jar $(PATEXE)/OB.jar_ProcMon
		$(CP) -f OBNaming.jar $(PATEXE)/OBNaming.jar_ProcMon
		

$(EXTRA_DEPENDS):
	$(CP) -f ./JarDir/OB.jar_orig ./JarDir/OB.jar
	$(CP) -f ./JarDir/OBNaming.jar_orig ./JarDir/OBNaming.jar
	$(CP) -f ./JarDir/*.jar .

#
# Dipendenze esterne
#
# $(JAVAORB)/bin/idlj -jQ -m interOp  $(IDL_FILE)
#
# $(JAVAORB)/bin/idlj -jQ -m interOp  $(IDL_FILE)
# Regole di compilazione
#
include $(PATMAK)/make_rules.mk

#
# Dipendenze automatiche
#
include $(PATMAK)/make_clean.mk
include $(PATMAK)/make_depend.mk

#DO NOT DELETE
