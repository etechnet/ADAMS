
cmake_minimum_required(VERSION 2.8.6)
find_package(Java)
include(UseJava)

project(ssm_client Java)

include ($ENV{ADAMS_HOME}/make/build.cmake)

set(SRC_SSM_CLIENT
    SSM_GlobalParam.java
    SSM_JF_LoginStart.java
    SSM_D_ChangePWD.java
    SystemServiceManager.java
    SSM_JListIcon.java
    SSM_IconPool.java
    SSM_JF_MonitoringOutput.java
    SSM_JF_MonitoringSetup.java
    SSM_JP_ProcessRow.java
    SSM_JTextFieldFilter.java
    SSM_CORBAConnection.java
    SSM_JF_textArea.java
    SSM_JF_ConfigSetup.java
    SSM_JF_LoggerRow.java
    SSM_JF_LoggerOutput.java
    SSM_JF_ConfigLog.java
    SSM_JD_SelectBlock.java
    SSM_JF_ACQPebMgr.java
    JD_Message.java
    SSM_JF_GarbageMonitor.java
    checkSystem.java
    SSM_JF_MonitoringOrbix.java
    TableSorter.java
    SSM_ProcessRowNew.java
    SSM_JF_ManageOrbix.java
    SSM_JF_Global.java
    SSM_JP_ProcessConfiguration.java
    SSM_TAB_T_PROCESS.java
    SSM_Vector_TAB_T_PROCESS.java
    SSM_DBConnection.java
    SSM_JP_NodeConfiguration.java
    SSM_JP_ProcessGroupConfiguration.java
    SSM_JCheckBoxList.java
    SSM_TAB_T_NODE.java
    SSM_Vector_TAB_T_NODE.java
    SSM_TAB_PROCESS_GROUPS.java
    SSM_Vector_TAB_PROCESS_GROUPS.java
    SSM_TAB_PROCESS_GROUP_MEMBERS.java
    SSM_Vector_TAB_PROCESS_GROUP_MEMBERS.java
    makeID.java
)



set (source_IDL
	common_datatypes.idl
	mdm.idl
	ssm.idl
	asp.idl
)

GENERATE_JAVA_IDL_STUBS(${source_IDL})

set (jar_PACKAGES
	adams_loadconfig.jar
)

EXPAND_JAR_PACKAGES(${jar_PACKAGES})

FILE (GLOB additional_jar_resources RELATIVE ${CMAKE_CURRENT_SOURCE_DIR} "images/*" "images/buttons/*" "images/brands/*")

add_jar(adams_ssm
	${SRC_SSM_CLIENT}
        ${additional_jar_resources}
)

ADD_PACKAGES_TO_JAR()

add_dependencies(adams_ssm JAVA_STUBS_GENERATION_TARGET)
add_dependencies(adams_ssm EXPAND_JAR_PACKAGES_TARGET)

install_jar(adams_ssm ${ADAMS_WEB_INSTALL}/adams_ssm)
