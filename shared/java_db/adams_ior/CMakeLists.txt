
cmake_minimum_required(VERSION 2.8.6)
find_package(Java)
include(UseJava)

project(adams_ior Java)

include ($ENV{ADAMS_HOME}/make/build.cmake)

set(SRC_LOAD_IOR
		ADAMS_IOR.java
		ADAMS_IOR_DBConnection.java
		ADAMS_IOR_GlobalParam.java
		ADAMS_IOR_JD_Message.java
)


SETUP_JAVA_DB_DRIVER()

add_jar(adams_ior
	${SRC_LOAD_IOR}
)

add_dependencies(adams_ior JAVA_STUBS_GENERATION_TARGET)

install_jar(adams_ior ${ADAMS_INSTALL_JAR_PACKAGES_DIR})

# ADD_DB_DRIVER_TO_JAR()

