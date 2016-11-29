#
#++
#
#
#                $$$$$$$$\                  $$\
#                \__$$  __|                 $$ |
#  $$$$$$\          $$ | $$$$$$\   $$$$$$$\ $$$$$$$\
# $$  __$$\ $$$$$$\ $$ |$$  __$$\ $$  _____|$$  __$$\
# $$$$$$$$ |\______|$$ |$$$$$$$$ |$$ /      $$ |  $$ |
# $$   ____|        $$ |$$   ____|$$ |      $$ |  $$ |
# \$$$$$$$\         $$ |\$$$$$$$\ \$$$$$$$\ $$ |  $$ |
#  \_______|        \__| \_______| \_______|\__|  \__|
#
#  MODULE DESCRIPTION:
#  3rdparty Makefile
#
#  AUTHORS:
#  Piergiorgio Betti <piergiorgio.betti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  30.11.12 P. Betti            Creation date
#--
#


ifeq ($(TOP_MAKE),)
ADAMS_HOME ?= $(CURDIR)/../..
include $(ADAMS_HOME)/make/build.mk
TOP_MAKE=true
endif

#
# 3rd party package
#

VERS=3.1

LIBDIR=
PKGNAME=jacorb-$(VERS)

SOURCES =
OBJECTS =
LIBS=
SHLIBS=
HEADERS= 

#
# local flags
#

LOCAL_DEFINE =
LOCAL_INCLUDE =
LOCAL_CFLAGS =
LOCAL_LFLAGS =


all: $(PKGNAME)/build.xml 
	cd $(PKGNAME); \
	ant

$(PKGNAME)/build.xml:
	unzip $(PKGNAME)-source.zip


depend:
	@echo "Depencies are generated at compile time."


clean:
	@echo "Do nothing here: " $(CURDIR)

realclean:
	rm -rf $(PKGNAME)

##

include $(ADAMS_HOME)/make/common_rules.mk

