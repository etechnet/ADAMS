#
# cmake for ssm_db
#

cmake_minimum_required(VERSION 2.8)

SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})

project(ssm_db)

include ($ENV{ADAMS_HOME}/make/build.cmake)

include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(ssm_db_SRCS
	apidb_ior.cpp
	apidb_process.cpp
	apidb_node.cpp
	apidb_node_process.cpp
	apidb_status.cpp
	apidb_log_logger.cpp
)


set (ssm_db_MOC_HDRS
# 	api_db_adams.h
)

qt4_add_dbus_interface(ssm_db_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

add_library(ssm_db SHARED ${ssm_db_SRCS})

install(TARGETS ssm_db LIBRARY DESTINATION ${INSTALL_LIB_DIR})
