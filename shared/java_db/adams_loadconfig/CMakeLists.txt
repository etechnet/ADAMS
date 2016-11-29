
cmake_minimum_required(VERSION 2.8.6)
find_package(Java)
include(UseJava)

project(adams_loadconfig Java)

include ($ENV{ADAMS_HOME}/make/build.cmake)

set(SRC_LOAD_CONFIG
		ADAMS_GlobalParam.java
		ADAMS_JD_Message.java
		ADAMS_OracleConnection.java
		ADAMS_typeList.java
		ADAMS_Vector_TAB_CONFIG.java
		ADAMS_Vector_TAB_EVENTI_GUI.java
		ADAMS_Plugin.java
		ADAMS_Relation.java
		ADAMS_VectorHelp.java
		ADAMS_TAB_CONFIG.java
		ADAMS_TAB_VALORI_ELEMENTO.java
		ADAMS_TAB_COUNTERKIT.java
		ADAMS_TAB_INFO_CONFIG.java
		ADAMS_TAB_TYPE_ANALISI.java
		ADAMS_TAB_SCRIPTS_LISTA.java
		ADAMS_TAB_KIT_CONTATORI.java
		ADAMS_TAB_ALARM_RELATION.java
		ADAMS_TAB_ALARM_GENERATOR.java
		ADAMS_TAB_ALARM_THRESHOLD.java
		ADAMS_TAB_RELAZIONI_ELEMENTO.java
		ADAMS_TAB_EVENTI_GUI.java
		ADAMS_TAB_EXTERNAL.java
		ADAMS_ScriptObject.java
		ADAMS_ReportObject.java
		ADAMS_loadConfig.java
)


set (source_IDL
	common_datatypes.idl
	mdm.idl
	asp.idl
	ssm.idl
)

GENERATE_JAVA_IDL_STUBS(${source_IDL})
SETUP_JAVA_DB_DRIVER()

add_jar(adams_loadconfig
	${SRC_LOAD_CONFIG}
)

add_dependencies(adams_loadconfig JAVA_STUBS_GENERATION_TARGET)

install_jar(adams_loadconfig ${ADAMS_INSTALL_JAR_PACKAGES_DIR})

# ADD_DB_DRIVER_TO_JAR()

