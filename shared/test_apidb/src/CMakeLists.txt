
cmake_minimum_required(VERSION 2.8)

project(test_db)

SET(QT_USE_QTSQL 1)
SET(QT_USE_QTNETWORK 1)
SET(QT_USE_QTXML 1)
SET(QT_USE_QTDBUS 1)
SET(QT_DONT_USE_QTGUI 1)
FIND_PACKAGE(Qt4 REQUIRED)
INCLUDE(${QT_USE_FILE})


include ($ENV{ADAMS_HOME}/make/build.cmake)


include_directories(${QT_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR} ${ADAMS_INCLUDE_PATH} ${CORBA_GENERATED_HH_PATH})
include_directories(${CMAKE_CURRENT_SOURCE_DIR})
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE} -DADAMS_RUNTIME_SHARED_PATH="${ADAMS_RUNTIME_SHARED_PATH}" -DADAMS_PREFIX="${ADAMS_PREFIX}")
link_directories(${ADAMS_LIB})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(test_apidb_SRCS
	main.cpp
)

set (test_apidb_HDRS
# 	api_db_adams.h
)

set (source_IDL
	mdm.idl
)


qt4_add_dbus_interface(test_apidb ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)
GENERATE_CXX_IDL_STUBS(${source_IDL})

qt4_add_dbus_interface(test_apidb_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

add_executable(test_apidb ${test_apidb_SRCS} ${IDL_GEN_CPP_FILES})
target_link_libraries(test_apidb ${QT_LIBRARIES} ${CORBA_LIBS} adams_core scriba adams_qtcorba corbautil adams_logger ssm_db asp_db)


install(TARGETS test_apidb RUNTIME DESTINATION bin)

