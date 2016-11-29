
cmake_minimum_required(VERSION 2.8)

project(utils)

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

set(adams-utils-iniparser_SRCS
	adams-utils-iniparser.cpp
)

# set(ssm_logger_MOC_HDRS
# )
# 
# MOC_HDRS_FROM_TOP_INCLUDE(${ssm_logger_MOC_HDRS})

# qt4_add_dbus_adaptor(ssm_logger_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml
#                      ${ADAMS_INCLUDE_PATH}/ssm_logger.h ssm_logger)
# QT_WRAP_CPP(ssm_logger ssm_logger_MOC_SRCS ${MOC_HDRS})


qt4_add_dbus_interface(adams-utils-iniparser_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)
# qt4_add_dbus_interface(ssm_logger_SRCS ${ADAMS_XML_PATH}/net.etech.adams.ssm_logger.xml ssm_logger_if)

# set (source_IDL
# 	ssm.idl
# )
# 
# GENERATE_CXX_IDL_STUBS(${source_IDL})

add_executable(adams-utils-iniparser ${adams-utils-iniparser_SRCS})
target_link_libraries(adams-utils-iniparser ${QT_LIBRARIES} ${CORBA_LIBS} adams_core scriba adams_qtcorba corbautil adams_logger ssm_db)


install(TARGETS adams-utils-iniparser RUNTIME DESTINATION bin)

