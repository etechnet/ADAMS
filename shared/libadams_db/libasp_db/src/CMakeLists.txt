#
# cmake for ssm_db
#

cmake_minimum_required(VERSION 2.8)

SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})

project(asp_db)

include ($ENV{ADAMS_HOME}/make/build.cmake)


include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
include_directories(${CMAKE_CURRENT_SOURCE_DIR})
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE} -DADAMS_RUNTIME_SHARED_PATH="${ADAMS_RUNTIME_SHARED_PATH}" -DADAMS_PREFIX="${ADAMS_PREFIX}")
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(asp_db_SRCS
	apidb_config.cpp
	apidb_user.cpp
	apidb_datadr.cpp
	apidb_dataelement.cpp
	apidb_exception.cpp
	apidb_relation.cpp
	apidb_analysis.cpp
	apidb_counter.cpp
	apidb_pluginregistry.cpp
	apidb_script.cpp
	apidb_reportschemas.cpp
	apidb_thresholdgenerator.cpp
	apidb_alarmgenerator.cpp
	apidb_alarmrelation.cpp
)


set (asp_db_MOC_HDRS
	apidb_user.h
	apidb_datadr.h
	apidb_dataelement.h
	apidb_exception.h
	apidb_relation.h
	apidb_analysis.h
	apidb_counter.h
	apidb_pluginregistry.h
	apidb_script.h
	apidb_reportschemas.h
	apidb_thresholdgenerator.h
	apidb_alarmgenerator.h
	apidb_alarmrelation.h
)


set (source_IDL
	mdm.idl
)


qt4_add_dbus_interface(asp_db_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)
GENERATE_CXX_IDL_STUBS(${source_IDL})
MOC_HDRS_FROM_TOP_INCLUDE(${asp_db_MOC_HDRS})

QT_WRAP_CPP(asp_db asp_db_MOC_HDRS ${MOC_HDRS})

add_library(asp_db SHARED ${asp_db_SRCS} ${asp_db_MOC_SRCS} ${asp_db_UI_SRCS} ${IDL_GEN_CPP_FILES})

install(TARGETS asp_db LIBRARY DESTINATION ${INSTALL_LIB_DIR})