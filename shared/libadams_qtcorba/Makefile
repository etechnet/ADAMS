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
#  Component Makefile
#
#  AUTHORS:
#  Piergiorgio Betti <piergiorgio.betti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  28.01.13 P. Betti            Creation date
#--
#

ifeq ($(TOP_MAKE),)
ADAMS_HOME ?= $(CURDIR)/../..
include $(ADAMS_HOME)/make/build.mk
TOP_MAKE=true
endif

# SETUP THIS
MODULE            = adams_libqtcorba
#--

INSTALL_DIR       =
CLEAN_DIRS        +=

all: build_target

install: build_target
	cd build; make install
# Custom part


build_target:
	if [ ! -f build/Makefile ]; then \
		mkdir -p build; \
		cd build; \
		cmake ../; \
		cd ..; \
	fi
	cd build; make


clean:
	if [ -f build/Makefile ]; then \
		cd build; make clean; cd ..; \
	fi

realclean: trash_clean
	rm -rf build;

depend:
	@echo "Depencies are generated at compile time."


include $(ADAMS_HOME)/make/common_rules.mk

