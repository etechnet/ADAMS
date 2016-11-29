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
#  10.03.14 P. Betti            Creation date
#--
#

ifeq ($(TOP_MAKE),)
ADAMS_HOME ?= $(CURDIR)/../..
include $(ADAMS_HOME)/make/build.mk
TOP_MAKE=true
endif

# SETUP THIS
MODULE            = adams_mdm
CLIENT            = mdm_client
#--

JAR_FILE          = $(MODULE).jar
CLIENT_SOURCE_DIR = $(CLIENT)
JAR_BINARY_DIR    = build/$(CLIENT)
INSTALL_DIR       = $(ADAMS_WEB_INSTALL)/$(MODULE)
CLEAN_DIRS        += com org

all: build_target #.signed_jar .signed_orb 

install: build_target .signed_jar .signed_orb
	cd build; make install
# Custom part	
	@mkdir -p $(INSTALL_DIR)
	@cp -f $(ADAMS_JAVA_SLF4J_LIB) $(INSTALL_DIR)
	@cp -f $(CLIENT_SOURCE_DIR)/adams-mdm.html $(INSTALL_DIR)
	@cp -f $(JAR_BINARY_DIR)/$(ADAMS_JAVA_DB_DRIVER_CANONICAL_NAME) $(INSTALL_DIR)
	$(ADAMS_MAKE_PATH)/mk-signed-jars.sh install $(JAR_BINARY_DIR) $(INSTALL_DIR)


.signed_jar: $(JAR_BINARY_DIR)/$(JAR_FILE)
	rm -f *.cert *.jks
	$(CLIENT_SOURCE_DIR)/create_mdm_signer_key.sh
	$(ADAMS_JARSIGNER) -keystore adams_mdm.jks -storepass mdm_signer_pw_2 -keypass mdm_signer_pw_1 $(JAR_BINARY_DIR)/$(JAR_FILE) adams_mdm
# 	$(ADAMS_JARSIGNER) -verify $(JAR_BINARY_DIR)/$(JAR_FILE)
	@touch .signed_jar

.signed_orb: $(JAR_BINARY_DIR)/$(JAR_FILE)
	cp $(ADAMS_JAVA_ORB_LIB) $(JAR_BINARY_DIR)
	@cp $(ADAMS_JAVA_DB_DRIVER_LIB) $(JAR_BINARY_DIR)/$(ADAMS_JAVA_DB_DRIVER_CANONICAL_NAME)
	$(ADAMS_JARSIGNER) -keystore adams_mdm.jks -storepass mdm_signer_pw_2 -keypass mdm_signer_pw_1 $(JAR_BINARY_DIR)/jacorb.jar adams_mdm
	$(ADAMS_JARSIGNER) -keystore adams_mdm.jks -storepass mdm_signer_pw_2 -keypass mdm_signer_pw_1 $(JAR_BINARY_DIR)/$(ADAMS_JAVA_DB_DRIVER_CANONICAL_NAME) adams_mdm
	@touch .signed_orb
	
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

