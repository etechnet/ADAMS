
cmake_minimum_required(VERSION 2.8.6)
find_package(Java)
include(UseJava)

project(mdm_client Java)

include ($ENV{ADAMS_HOME}/make/build.cmake)

set(SRC_MDM
	MDMclient.java
	MDM_exportReport.java
	JD_Message.java
	SimpleRunner.java
	Counters.java
	Reports.java
	MDM_JF_Frame.java
	MDM_JF_LoginStart_1.java
	MDM_JD_ChangePWD.java
	MDM_JP_RestrictionList.java
	MDM_JP_RestrictionsHelp.java
	MDM_JP_SelectAnalisys.java
	MDM_JP_Calendar.java
	MDM_JP_SelectLevel.java
	MDM_JP_SelectRelations.java
	MDM_JD_RelationBuild.java
	MDM_JP_SelectRestrictions.java
	MDM_JP_SelectSort.java
	MDM_JP_RestrictionDimension.java
	MDM_JP_RestrictionMode.java
	Analisi.java
	jpRestrictions.java
	IconPool.java
	JListIcon.java
	JTextFieldFilter.java
	Nodo.java
	getConfigFiltro.java
	insiemi.java
	staticLib.java
	TrafficElement.java
	Relation.java
	MDM_JP_Summary.java
	MDM_JP_CellSummary.java
	MDM_JP_Centrali.java
	MDM_JP_ProgBar.java
	MDM_JP_Jobs.java
	paintTrafficElement.java
	BufferRestrizioni.java
	elementoBase.java
	connCORBA.java
	OBJpool.java
	nodoSummaryEXT.java
	SampleData.java
	SampleTreeCellRenderer.java
	JComboIcon.java
	Eccezioni.java
	HelpDescVector.java
	HelpDescBASE.java
	MDM_JF_Pivot.java
	MDM_JD_InitPivot.java
	MDM_JD_Request.java
	TableSorter.java
	JF_Search.java
	JF_MathExpression.java
	jP_ExpressionParser.java
	MDM_PrintWebPivot.java
	MDM_JD_FontChooser.java
	HttpImpl.java
	MDM_JP_wait.java
	MDM_JF_TE_search.java
	AppletAspetto3.java
	DOMTreeModel.java
	Directory.java
	FileHtml.java
)

set(SRC_PLUGINS_JAVA
		plugins/NTM_OracleConnection.java
		plugins/plugin_base.java
		plugins/plugin_info.java
		plugins/plugin_numu/plugin_numu.java
		plugins/plugin_numu/JD_plugin_numu.java
		plugins/plugin_numu/JD_plugin_numu_info.java
		plugins/plugin_numu/JD_addmod.java
		plugins/plugin_numu/JD_pswd.java
		plugins/plugin_numu/elementoLista.java
		plugins/plugin_numu/elementoListaBase.java
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
	adams_loadconfig.jar
	adams_ior.jar
)

EXPAND_JAR_PACKAGES(${jar_PACKAGES})


add_custom_command(
	OUTPUT UNTAR_MDM_PACKAGES.done
	COMMAND tar xf ${CMAKE_CURRENT_SOURCE_DIR}/com_lib.tar
	COMMAND tar xf ${CMAKE_CURRENT_SOURCE_DIR}/jxl_lib.tar
	COMMAND tar xf ${CMAKE_CURRENT_SOURCE_DIR}/nfunk_lib.tar
	COMMAND touch UNTAR_MDM_PACKAGES.done
	WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
	COMMENT "Expanding packages"
)


add_custom_target(UNTAR_MDM_PACKAGES
	DEPENDS UNTAR_MDM_PACKAGES.done
)


FILE (GLOB additional_jar_resources RELATIVE ${CMAKE_CURRENT_SOURCE_DIR} "images/*" "images/buttons/*" "images/brends/*")

add_jar(adams_mdm
	${SRC_PLUGINS_JAVA}
	${SRC_MDM}
	${additional_jar_resources}
)

ADD_PACKAGES_TO_JAR()


add_dependencies(adams_mdm JAVA_STUBS_GENERATION_TARGET)
add_dependencies(adams_mdm UNTAR_MDM_PACKAGES)
add_dependencies(adams_mdm EXPAND_JAR_PACKAGES_TARGET)

add_custom_command(TARGET ${CMAKE_PROJECT_NAME}
	POST_BUILD
	COMMAND ${Java_JAR_EXECUTABLE} uf ${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_PROJECT_NAME}.jar com org jxl
	WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
	COMMENT "Adding packages classes to jar..."
)

install_jar(adams_mdm ${ADAMS_WEB_INSTALL}/adams_mdm)


# ADD_DB_DRIVER_TO_JAR()

