#
# cmake for adams_logger
#

cmake_minimum_required(VERSION 2.8)

SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})

project(libadams_logger)

include ($ENV{ADAMS_HOME}/make/build.cmake)

include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(adams_logger_SRCS
	ltextstream.cpp
	applogger.cpp
)
qt4_add_dbus_interface(adams_logger_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

set (adams_logger_MOC_HDRS
	ltextstream.h
)

MOC_HDRS_FROM_TOP_INCLUDE(${adams_logger_MOC_HDRS})

QT_WRAP_CPP(adams_logger adams_logger_MOC_SRCS ${MOC_HDRS})

add_library(adams_logger SHARED ${adams_logger_SRCS} ${adams_logger_MOC_SRCS} ${IDL_GEN_CPP_FILES})

install(TARGETS adams_logger LIBRARY DESTINATION ${INSTALL_LIB_DIR})
