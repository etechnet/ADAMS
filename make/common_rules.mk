#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$\ $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#


# other commons

include $(ADAMS_HOME)/make/native_qt.mk

# Clean sparse trash files (to be a depend fo "realclean")

CLEAN_OBJECTS += \*.o \*.lo \*.o? \*.a \*.uid \*~ \*.bak core \*.class \*.jar \*.lis \*.so \*.a \*.jks \*.cert
CLEAN_DIRS +=

trash_clean:
	@echo "Cleaning trash objects..."
	@for tgt in $(CLEAN_OBJECTS); do \
		find ./ -name '$$tgt' -exec rm -rf {} \; ; \
	done
	@echo "Cleaning trash dirs..."
	@for tgt in $(CLEAN_DIRS); do \
		rm -rf `find ./ -type d -name $$tgt`; \
	done
	@if [ -n "$(INSTALL_DIR)" ]; then \
		echo "Cleaning install directory..."; \
		rm -rf $(INSTALL_DIR); \
	fi
