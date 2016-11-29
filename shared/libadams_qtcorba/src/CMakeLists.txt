#
# cmake for adams_qtcorba
#

cmake_minimum_required(VERSION 2.8)

SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})

project(adams_qtcorba)

include ($ENV{ADAMS_HOME}/make/build.cmake)

include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(adams_qtcorba_SRCS
	QtCoreReactor.cpp
	QtCoreResource_Factory.cpp
	QtCoreResource_Loader.cpp
)


set (adams_qtcorba_MOC_HDRS
	qtcorereactor.h
)

MOC_HDRS_FROM_TOP_INCLUDE(${adams_qtcorba_MOC_HDRS})

QT_WRAP_CPP(adams_qtcorba adams_qtcorba_MOC_SRCS ${MOC_HDRS})

add_library(adams_qtcorba SHARED ${adams_qtcorba_SRCS} ${adams_qtcorba_MOC_SRCS} ${IDL_GEN_CPP_FILES})

install(TARGETS adams_qtcorba LIBRARY DESTINATION ${INSTALL_LIB_DIR})
