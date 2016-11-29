
cmake_minimum_required(VERSION 2.8)

project(asp_server)

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
add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE})
link_directories(${ADAMS_LIB})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set(asp_server_SRCS
	main.cpp
 	asp_entry_bus.cpp
	asp_entry_impl.cpp
	asp_usermanagement_server_impl.cpp
	mdm_configuration_server_impl.cpp
	servicelib.cpp
	asp_server.cpp
	apidb_asp_log.cpp
	apidb_asp_usermgt.cpp
	${ADAMS_XML_PATH}/net.etech.adams.asp_entry_bus.xml
)

set(asp_server_MOC_HDRS
	asp_entry_bus.h
)

MOC_HDRS_FROM_TOP_INCLUDE(${asp_server_MOC_HDRS})

qt4_generate_dbus_interface(${ADAMS_INCLUDE_PATH}/asp_entry_bus.h ${ADAMS_XML_PATH}/net.etech.adams.asp_entry_bus.xml)

qt4_add_dbus_adaptor(asp_server_SRCS ${ADAMS_XML_PATH}/net.etech.adams.asp_entry_bus.xml
                     ${ADAMS_INCLUDE_PATH}/asp_entry_bus.h asp_entry_bus)

QT_WRAP_CPP(asp_server asp_server_MOC_SRCS ${MOC_HDRS})

qt4_add_dbus_interface(asp_server_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

set (source_IDL
	asp.idl
)

GENERATE_CXX_IDL_STUBS(${source_IDL})

add_executable(asp_server ${asp_server_SRCS} ${asp_server_MOC_SRCS} ${IDL_GEN_CPP_FILES})
target_link_libraries(asp_server ${QT_LIBRARIES} ${CORBA_LIBS} adams_core scriba adams_qtcorba corbautil adams_logger ssm_db)

install(TARGETS asp_server RUNTIME DESTINATION bin)

