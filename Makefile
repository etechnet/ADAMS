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

ADAMS_HOME ?= $(CURDIR)
include $(ADAMS_HOME)/make/build.mk
TOP_MAKE=true

SUBDIRS = shared

#

all:
	@for i in $(SUBDIRS); do \
		cd $$i; \
		make all; \
		cd ..; \
	done

global:
ifeq ($(USE_NATIVE_QT),)
	@if [ ! -d "$(QTDIR)" ]; then \
		mkdir -p $(ADAMS_LIB_PATH)/QT; \
		cd $(ADAMS_LIB_PATH)/QT; \
		bzip2 -d -c $(ADAMS_3RDPATY_PATH)/qt-package/qt-devel-$(CXX)-$(ADAMS_OPSYS).tar.bz2 | tar xvf -; \
	fi
endif
	@for i in $(SUBDIRS); do \
		cd $$i; \
		if [ ! -f CMakeLists.txt ]; then \
			make; \
			cd ..; \
			continue; \
		fi; \
 		if [ ! -f build/Makefile ]; then \
			mkdir -p build; \
			cd build; \
			cmake ../;\
			cd ..; \
		fi; \
		cd build; make; \
		cd ../..; \
	done

clean:
	for i in $(SUBDIRS); do \
		if [ -f $$i/Makefile ]; then \
			cd $$i; make clean; cd ..; \
		fi; \
	done

realclean: trash_clean
	for i in $(SUBDIRS); do \
		if [ -f $$i/Makefile ]; then \
			cd $$i; make realclean; cd ..; \
		fi; \
	done

depend:
	@echo "Depencies are generated at compile time."


include $(ADAMS_HOME)/make/common_rules.mk
