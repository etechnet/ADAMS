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

VERS=2.1_RC1_Linux

LIBDIR=
PKGNAME=SB_$(VERS)

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


all: scriptbasic/Makefile
	cp -f Makefile.adams scriptbasic; \
	cd scriptbasic; \
	$(MAKE) -f Makefile.adams

scriptbasic/Makefile:
	tar zxvf $(PKGNAME).tar.gz; \
	chmod -R ug+w scriptbasic
	cp -f lmt_adams.def scriptbasic; \
	cp -f scriba.conf.adams.lsp scriptbasic; \
	cp -f Makefile.adams scriptbasic; \
	cd scriptbasic; \
	$(MAKE) -f Makefile.adams clean

install:
	cp -f scriptbasic/bin/lib/libscriba.so $(ADAMS_BUILD_INSTALL)/$(ADAMS_INSTALL_LIB_NAME)

depend:
	@echo "Depencies are generated at compile time."


clean:
	cd scriptbasic; \
	$(MAKE) -f Makefile.adams clean

realclean:
	rm -rf scriptbasic

##

include $(ADAMS_HOME)/make/common_rules.mk

