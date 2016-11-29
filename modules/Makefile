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
#  Top level Makefile
#
#  AUTHORS:
#  Piergiorgio Betti <piergiorgio.betti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  26.11.12 P. Betti            Creation date
#--
#

ifeq ($(TOP_MAKE),)
ADAMS_HOME ?= $(CURDIR)/..
include $(ADAMS_HOME)/make/build.mk
TOP_MAKE=true
endif

include subdirs.mk

#

all: native_qt
	@for i in $(SUBDIRS); do \
		cd $$i; \
		make all; \
	done

#global:
#

clean:
	for i in $(SUBDIRS); do \
		if [ -f $$i/Makefile ]; then \
			cd $$i; make clean; \
		fi; \
	done

realclean:
	rm -rf build
	for i in $(SUBDIRS); do \
		if [ -f $$i/Makefile ]; then \
			cd $$i; make realclean; \
		fi; \
		cd $$i; \
		rm -rf build; \
		cd ..; \
	done

depend:
	@echo "Depencies are generated at compile time."


include $(ADAMS_HOME)/make/common_rules.mk
