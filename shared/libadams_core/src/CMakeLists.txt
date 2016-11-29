#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$\ $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#

#
# cmake for adams_core
#

cmake_minimum_required(VERSION 2.8)

# SET(QT_USE_QT3SUPPORT 1)
SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})

project(libadams_core)

include ($ENV{ADAMS_HOME}/make/build.cmake)

include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
include_directories(${ADAMS_HOME}/3rdparty/scriptbasic)
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE} -DADAMS_RUNTIME_SHARED_PATH="${ADAMS_RUNTIME_SHARED_PATH}" -DADAMS_PREFIX="${ADAMS_PREFIX}")
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set (adams_core_SRCS
	nodeconfighandler.cpp
	poahierarchy.cpp
	address.cpp
	socket.cpp
	reportschemainterface.cpp
	jobqueue.cpp
	scriptinterface.cpp
	pluginregistryinterface.cpp
	adamsbasic.cpp
	drimagehandler.cpp
	userinterface.cpp
	configparser.cpp
	simplevector.cpp
	user.cpp
	importexport.cpp
	pluginbase.cpp
	counterinterface.cpp
	adamsserver.cpp
	httpimpl.cpp
	definerelation.cpp
	definealarmrelation.cpp
	node.cpp
	queryparams.cpp
	analysisinterface.cpp
	analysis.cpp
	dataelementinterface.cpp
	relationinterface.cpp
	exceptioninterface.cpp
	mathparser.cpp
	vlist.cpp
	tree.cpp
	symtab.cpp
	store.cpp
	scan.cpp
	parse.cpp
	htab.cpp
	funtab.cpp
	relation.cpp
	dataelement.cpp
	drinterface.cpp
	alarmrelation.cpp
	alarmrelationinterface.cpp
	alarmgenerator.cpp
	alarmgeneratorinterface.cpp
	thresholdgenerator.cpp
	thresholdgeneratorinterface.cpp
	storeconf.cpp
	timeout.cpp
	btreenetworkrt.cpp
	btreealarms.cpp
	xmlio.cpp
	commandlineoptions.cpp
	signalhandler.cpp
	signalhandlers.cpp
	namedsharedpointer.cpp
)

set (adams_core_UIS
)

set (adams_core_MOC_HDRS
	drinterface.h
	counterinterface.h
	exceptioninterface.h
	importexport.h
	mathparser.h
	adamsbasic.h
	parse.h
	pluginregistryinterface.h
	relationinterface.h
	reportschemainterface.h
	scriptinterface.h
	dataelementinterface.h
	userinterface.h
	xmlio.h
	analysisinterface.h
	alarmrelationinterface.h
	alarmgeneratorinterface.h
	thresholdgeneratorinterface.h
	adamsserver.h
)

set (source_IDL
	mdm.idl
	asp.idl
)

qt4_add_dbus_interface(adams_core_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)
qt4_add_dbus_interface(adams_core_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_scheduler_bus.xml ssm_scheduler_bus_if)
GENERATE_CXX_IDL_STUBS(${source_IDL})
MOC_HDRS_FROM_TOP_INCLUDE(${adams_core_MOC_HDRS})

QT_WRAP_CPP(adams_core adams_core_MOC_SRCS ${MOC_HDRS})

add_library(adams_core SHARED ${adams_core_SRCS} ${adams_core_MOC_SRCS} ${adams_core_UI_SRCS} ${IDL_GEN_CPP_FILES})

install(TARGETS adams_core LIBRARY DESTINATION ${INSTALL_LIB_DIR})


