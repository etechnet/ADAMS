
cmake_minimum_required(VERSION 2.8)

project(mdm_server)

SET(QT_USE_QTSQL 1)
SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
SET(QT_DONT_USE_QTGUI 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})


include ($ENV{ADAMS_HOME}/make/build.cmake)

include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
include_directories(${ADAMS_HOME}/3rdparty/scriptbasic)
include_directories(${CMAKE_CURRENT_SOURCE_DIR})
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE})
link_directories(${ADAMS_LIB})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(mdm_server_SRCS
	main.cpp
	mdm_server_server_impl.cpp
	executionthread.cpp
	pivotfile.cpp
	reportformatter.cpp
)

# set(mdm_server_MOC_HDRS
# 	mdm_server_bus.h
# )
#
# MOC_HDRS_FROM_TOP_INCLUDE(${mdm_server_MOC_HDRS})
#
# qt4_generate_dbus_interface(${ADAMS_INCLUDE_PATH}/mdm_server_bus.h ${ADAMS_XML_PATH}/net.etech.adams.mdm_server_bus.xml)
#
# qt4_add_dbus_adaptor(mdm_server_SRCS ${ADAMS_XML_PATH}/net.etech.adams.mdm_server_bus.xml
#                      ${ADAMS_INCLUDE_PATH}/mdm_server_bus.h mdm_server_bus)
#
# QT_WRAP_CPP(mdm_server mdm_server_MOC_SRCS ${MOC_HDRS})

qt4_add_dbus_interface(mdm_server_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

set (source_IDL
	mdm.idl
)

GENERATE_CXX_IDL_STUBS(${source_IDL})

add_executable(mdm_server ${mdm_server_SRCS} ${mdm_server_MOC_SRCS} ${IDL_GEN_CPP_FILES})
target_link_libraries(mdm_server ${QT_LIBRARIES} ${CORBA_LIBS} adams_core scriba adams_qtcorba corbautil adams_logger ssm_db asp_db)

install(TARGETS mdm_server RUNTIME DESTINATION bin)

