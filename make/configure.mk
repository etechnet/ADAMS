#
# Basic settings for the build environment
#

# where the sources are placed
ADAMS_HOME=$(HOME)/adams

# where is the base for system installation path
ADAMS_PREFIX=/opt/adams

# lib or lib64 libraries path for target
ADAMS_LIBDIR_NAME=lib64

# Operating system type
ADAMS_OPSYS=LINUX

# ADAMS build mode (testing vs. production) TEST_PLAN or PRODUCTION
ADAMS_MODE=TEST_PLAN

# Database used by ADAMS
ADAMS_DB_TYPE=MYSQL

# Force the use of non native QT when set to "no"
USE_NATIVE_QT=yes

# Force the use of non native JACORB when set to "no"
USE_NATIVE_JACORB=no

# Set this depending on how ACE/TAO has been compiled/installed
HAS_ACE_VERSIONED_NAMESPACE=no

# JDK installation base, to be set ONLY if cmake fail to find it
#JAVA_HOME=/usr/lib64/jvm/java-sun/
#export JAVA_HOME

# Other common parameters

ADAMS_JACORB_VERSION=3.1

export ADAMS_HOME
export ADAMS_PREFIX
export ADAMS_OPSYS
export ADAMS_MODE
export ADAMS_DB_TYPE

#

CC=gcc
CXX=g++
ABI=64
CFLAGS +=
CXXFLAGS +=
EXTRABIN +=
GCC_TARGET=$($shell g++ -v 2>&1 | grep Target: | cut -d' ' -f 2)


export CC
export CXX
export ABI
export CFLAGS
export CXXFLAGS
export EXTRABIN
export GCC_TARGET

