
cmake_minimum_required(VERSION 2.8.6)
find_package(Java)
include(UseJava)

project(asp_client Java)

include ($ENV{ADAMS_HOME}/make/build.cmake)

set(SRC_ASP
	JTextFieldFilter.java
	ADAMS_JD_ChangePWD.java
	ADAMS_JF_LoginStart.java
	ADAMSConf.java
	ADAMS_OracleConnection.java
	ADAMS_GlobalParam.java
	ADAMS_JF_Wizard.java
	ADAMS_JF_AddMod_conf.java
	ADAMS_JP_InputData.java
	ADAMS_TAB_CONFIG.java
	ADAMS_TAB_VALORI_ELEMENTO.java
	ADAMS_TAB_RELAZIONI_ELEMENTO.java
	ADAMS_TAB_INFO_CONFIG.java
	ADAMS_TAB_TYPE_ANALISI.java
	ADAMS_JD_Message.java
	JListIcon.java
	CORBAConnection.java
	IconPool.java
	ADAMS_JF_ConfigHelp.java
	ADAMS_JP_DataElement.java
	TableSorter.java
	ADAMS_JF_ListValue.java
	JListChooser.java
	VectorHelp.java
	ADAMS_JF_ListaValoriSostituzione.java
	ADAMS_JTab_TE_EXC_REL.java
	ADAMS_JP_Exceptions.java
	ADAMS_JP_ReportSchema.java
	ADAMS_JF_WIZARDBASE.java
	ADAMS_JP_ReportObjectEditor.java
	ADAMS_JP_Relations.java
	ADAMS_JD_ViewRelations.java
	ADAMS_JD_ViewRelWithRestriction.java
	ADAMS_JD_setPlugin.java
	ADAMS_JD_ViewSetImage.java
	ADAMS_JD_RegisterPlugin_DB.java
	ADAMS_JP_Ghost_Relations.java
	ADAMS_JP_Analisi.java
	ADAMS_JP_Counters.java
	ADAMS_JD_ADD_DEL_CounterKit.java
	ADAMS_JP_ReportObject_HeaderFieldContent.java
	ADAMS_JP_ReportObject_BodyCell.java
	ADAMS_JP_ReportObject_FooterRow.java
	ADAMS_JP_ReportObject_TotalizerRow.java
	ADAMS_JF_Report.java
	ADAMS_TAB_KIT_CONTATORI.java
	ADAMS_TAB_COUNTERKIT.java
	nodoSummaryEXT.java
	SampleData.java
	SampleTreeCellRenderer.java
	ReportObject.java
	ADAMS_JP_Report_b1.java
	ADAMS_JP_Report_b2.java
	ADAMS_JD_ReportClone.java
	ADAMS_JP_Script_p1.java
	ADAMS_JP_Script_p2.java
	ADAMS_JP_Script_p3.java
	ADAMS_JP_Script_p4.java
	ADAMS_JP_Script_p5.java
	ADAMS_JP_Script_p6.java
	ADAMS_JF_HelpBasic.java
	ScriptObject.java
	ADAMS_storeConfig.java
	ADAMS_JF_TransferConf.java
	ADAMS_TAB_SCRIPTS_LISTA.java
	ADAMS_Plugin.java
	ADAMS_Relation.java
	ADAMS_typeList.java
	ADAMS_JD_BackupRestore.java
	ADAMS_JD_Configuration_Clone.java
	ADAMS_JD_VerifyLimits.java
	ADAMS_Calendar.java
	ADAMS_JP_Cal.java
	TText.java
	ADAMS_JP_Alarm.java
	ADAMS_JP_Relations_Alarm.java
	ADAMS_JD_RelationBuild.java
	ADAMS_TAB_ALARM_RELATION.java
	ADAMS_TAB_ALARM_GENERATOR.java
	ADAMS_TAB_ALARM_THRESHOLD.java
	ADAMS_JD_Threshold.java
	ADAMS_JD_RelationBuildProperties.java
	ADAMS_JD_ALARM.java
	ADAMS_T_POLITICHE.java
	ADAMS_JD_RegisterServerAlarm_DB.java
	ADAMS_JF_ViewServerAlarm_DB.java
	ADAMS_Vector_TAB_CONFIG.java
	ADAMSBasicFuntionList.java
)

set(SRC_ASP_CONFIG
	STS_Conf.java
	conf_GlobalParam.java
	conf_JTextFieldFilter.java
	conf_JListIcon.java
	conf_IconPool.java
	conf_GlobalParam.java
	conf_CORBAConnection.java
	conf_JF_Global.java
	conf_JP_AccessManager.java	
	conf_JD_Message.java
	mappaIndici.java
	ImagePool.java
	makeID.java
	JComboIcon.java
	JComboCheck.java
	cella.java
)



set (source_IDL
	common_datatypes.idl
	mdm.idl
	ssm.idl
	asp.idl
)

GENERATE_JAVA_IDL_STUBS(${source_IDL})
SETUP_JAVA_DB_DRIVER()

set (jar_PACKAGES
	adams_ior.jar
)

EXPAND_JAR_PACKAGES(${jar_PACKAGES})


FILE (GLOB additional_jar_resources RELATIVE ${CMAKE_CURRENT_SOURCE_DIR} "images/*" "images_conf/*")

add_jar(adams_asp
	${SRC_ASP}
	${SRC_ASP_CONFIG}
	${additional_jar_resources}
)

ADD_PACKAGES_TO_JAR()


add_dependencies(adams_asp JAVA_STUBS_GENERATION_TARGET)
add_dependencies(adams_asp EXPAND_JAR_PACKAGES_TARGET)

install_jar(adams_asp ${ADAMS_WEB_INSTALL}/adams_asp)
# ADD_DB_DRIVER_TO_JAR()

