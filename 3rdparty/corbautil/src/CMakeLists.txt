#
# cmake for corbautillib
#

cmake_minimum_required(VERSION 2.8)

project(corbautil)

include ($ENV{ADAMS_HOME}/make/build.cmake)

add_definitions(${ADAMS_DEFINE} ${ADAMS_INCLUDE})
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${ADAMS_CXXFLAGS})

set (corbautil_SRCS
	import_export.cpp
	PoaUtility.cpp
	PolicyListParser.cpp
)


add_library(corbautil SHARED ${corbautil_SRCS})

install(TARGETS corbautil LIBRARY DESTINATION ${INSTALL_LIB_DIR})
