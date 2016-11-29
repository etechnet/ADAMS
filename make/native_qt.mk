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

native_qt:
ifeq ($(USE_NATIVE_QT), no)
	@if [ ! -d "$(QTDIR)" ]; then \
		mkdir -p $(ADAMS_LIB_PATH)/QT; \
		cd $(ADAMS_LIB_PATH)/QT; \
		bzip2 -d -c $(ADAMS_3RDPATY_PATH)/qt-package/qt-devel-$(CXX)-$(ADAMS_OPSYS).tar.bz2 | tar xvf -; \
	fi
endif
	@echo -e "\c"


