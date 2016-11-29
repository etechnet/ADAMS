
cmake_minimum_required(VERSION 2.8)

project(etss_server_trapper)

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

set(etss_server_trapper_SRCS
	main.cpp
	etss_server_trapper_bus.cpp
	etss_server_trapper_server_impl.cpp
	etss_server_trapper.cpp
	${ADAMS_XML_PATH}/net.etech.adams.etss_server_trapper_bus.xml
)

set(etss_server_trapper_MOC_HDRS
	etss_server_trapper_bus.h
)

MOC_HDRS_FROM_TOP_INCLUDE(${etss_server_trapper_MOC_HDRS})

qt4_generate_dbus_interface(${ADAMS_INCLUDE_PATH}/etss_server_trapper_bus.h ${ADAMS_XML_PATH}/net.etech.adams.etss_server_trapper_bus.xml)

qt4_add_dbus_adaptor(etss_server_trapper_SRCS ${ADAMS_XML_PATH}/net.etech.adams.etss_server_trapper_bus.xml
                     ${ADAMS_INCLUDE_PATH}/etss_server_trapper_bus.h etss_server_trapper_bus)

QT_WRAP_CPP(etss_server_trapper etss_server_trapper_MOC_SRCS ${MOC_HDRS})

qt4_add_dbus_interface(etss_server_trapper_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

set (source_IDL
	etss.idl
)

GENERATE_CXX_IDL_STUBS(${source_IDL})

add_executable(etss_server_trapper ${etss_server_trapper_SRCS} ${etss_server_trapper_MOC_SRCS} ${IDL_GEN_CPP_FILES})
target_link_libraries(etss_server_trapper ${QT_LIBRARIES} ${CORBA_LIBS} adams_core scriba adams_qtcorba corbautil adams_logger ssm_db)

install(TARGETS etss_server_trapper RUNTIME DESTINATION bin)

