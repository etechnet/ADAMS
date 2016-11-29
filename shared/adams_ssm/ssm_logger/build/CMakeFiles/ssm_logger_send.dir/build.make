# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 2.8

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The program to use to edit the cache.
CMAKE_EDIT_COMMAND = /usr/bin/ccmake

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/pbetti/adams/shared/adams_ssm/ssm_logger

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/pbetti/adams/shared/adams_ssm/ssm_logger/build

# Include any dependencies generated for this target.
include CMakeFiles/ssm_logger_send.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/ssm_logger_send.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/ssm_logger_send.dir/flags.make

ssm_logger_if.moc: ssm_logger_if.h
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating ssm_logger_if.moc"
	/usr/bin/moc -I/usr/include -I/usr/include/QtDBus -I/usr/include/QtXml -I/usr/include/QtSql -I/usr/include/QtNetwork -I/usr/include/QtCore -I/usr/include/QtDesigner -I/usr/include/QtDeclarative -I/usr/include/QtScriptTools -I/usr/include/QtDBus -I/usr/include/QtDesigner -I/usr/include/QtXml -I/usr/include/QtSql -I/usr/include/QtOpenGL -I/usr/include/QtMultimedia -I/usr/include/QtNetwork -I/usr/include/phonon -I/usr/include/QtXmlPatterns -I/usr/include/QtWebKit -I/usr/include/QtHelp -I/usr/include/QtUiTools -I/usr/include/QtTest -I/usr/include/QtScript -I/usr/include/QtSvg -I/usr/include/Qt3Support -I/usr/include/QtGui -I/usr/include/QtCore -I/usr/share/qt4/mkspecs/default -I/usr/include -I/usr/include/QtCore -I/home/pbetti/adams/shared/adams_ssm/ssm_logger/build -I/home/pbetti/adams/include -I/home/pbetti/adams/include/generated -I/home/pbetti/adams/shared/adams_ssm/ssm_logger -DQT_NO_DEBUG -DQT_DBUS_LIB -DQT_XML_LIB -DQT_SQL_LIB -DQT_NETWORK_LIB -DQT_CORE_LIB -o /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.moc /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.h

ssm_logger_if.cpp: /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating ssm_logger_if.cpp, ssm_logger_if.h"
	/usr/bin/qdbusxml2cpp -m -p ssm_logger_if /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml

ssm_logger_if.h: ssm_logger_if.cpp

ssmC.cpp: /home/pbetti/adams/idl/ssm.idl
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_3)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Compiling ssm.idl"
	cpp -P -C -I/home/pbetti/adams/include -I/home/pbetti/adams/idl /home/pbetti/adams/idl/ssm.idl ssm.idl
	/usr/bin/tao_idl -t /tmp -as -in -Sa -St -Wb,pre_include=ace/pre.h -Wb,post_include=ace/post.h -I/home/pbetti/adams/include -I/home/pbetti/adams/idl ssm.idl
	mv ssmC.h ssmS.h /home/pbetti/adams/include/generated

ssmS.cpp: ssmC.cpp

/home/pbetti/adams/include/generated/ssmC.h: ssmC.cpp

/home/pbetti/adams/include/generated/ssmS.h: ssmC.cpp

/home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml: /home/pbetti/adams/include/ssm_logger.h
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_4)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml"
	/usr/bin/qdbuscpp2xml /home/pbetti/adams/include/ssm_logger.h -o /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml

CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o: CMakeFiles/ssm_logger_send.dir/flags.make
CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o: ../ssm_logger_send.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_5)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger_send.cpp

CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger_send.cpp > CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.i

CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger_send.cpp -o CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.s

CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.requires

CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.provides: CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger_send.dir/build.make CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.provides

CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.provides.build: CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o

CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o: CMakeFiles/ssm_logger_send.dir/flags.make
CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o: ssm_logger_if.cpp
CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o: ssm_logger_if.moc
CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o: ssm_logger_if.moc
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_6)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.cpp

CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.cpp > CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.i

CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.cpp -o CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.s

CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.requires

CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.provides: CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger_send.dir/build.make CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.provides

CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.provides.build: CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o

CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o: CMakeFiles/ssm_logger_send.dir/flags.make
CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o: ssmC.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_7)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmC.cpp

CMakeFiles/ssm_logger_send.dir/ssmC.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger_send.dir/ssmC.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmC.cpp > CMakeFiles/ssm_logger_send.dir/ssmC.cpp.i

CMakeFiles/ssm_logger_send.dir/ssmC.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger_send.dir/ssmC.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmC.cpp -o CMakeFiles/ssm_logger_send.dir/ssmC.cpp.s

CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.requires

CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.provides: CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger_send.dir/build.make CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.provides

CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.provides.build: CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o

CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o: CMakeFiles/ssm_logger_send.dir/flags.make
CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o: ssmS.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_8)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmS.cpp

CMakeFiles/ssm_logger_send.dir/ssmS.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger_send.dir/ssmS.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmS.cpp > CMakeFiles/ssm_logger_send.dir/ssmS.cpp.i

CMakeFiles/ssm_logger_send.dir/ssmS.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger_send.dir/ssmS.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmS.cpp -o CMakeFiles/ssm_logger_send.dir/ssmS.cpp.s

CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.requires

CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.provides: CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger_send.dir/build.make CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.provides

CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.provides.build: CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o

# Object files for target ssm_logger_send
ssm_logger_send_OBJECTS = \
"CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o" \
"CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o" \
"CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o" \
"CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o"

# External object files for target ssm_logger_send
ssm_logger_send_EXTERNAL_OBJECTS =

ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o
ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o
ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o
ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o
ssm_logger_send: CMakeFiles/ssm_logger_send.dir/build.make
ssm_logger_send: /usr/lib64/libQtDBus.so
ssm_logger_send: /usr/lib64/libQtXml.so
ssm_logger_send: /usr/lib64/libQtSql.so
ssm_logger_send: /usr/lib64/libQtNetwork.so
ssm_logger_send: /usr/lib64/libQtCore.so
ssm_logger_send: CMakeFiles/ssm_logger_send.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable ssm_logger_send"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ssm_logger_send.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/ssm_logger_send.dir/build: ssm_logger_send
.PHONY : CMakeFiles/ssm_logger_send.dir/build

# Object files for target ssm_logger_send
ssm_logger_send_OBJECTS = \
"CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o" \
"CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o" \
"CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o" \
"CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o"

# External object files for target ssm_logger_send
ssm_logger_send_EXTERNAL_OBJECTS =

CMakeFiles/CMakeRelink.dir/ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger_send: CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger_send: CMakeFiles/ssm_logger_send.dir/build.make
CMakeFiles/CMakeRelink.dir/ssm_logger_send: /usr/lib64/libQtDBus.so
CMakeFiles/CMakeRelink.dir/ssm_logger_send: /usr/lib64/libQtXml.so
CMakeFiles/CMakeRelink.dir/ssm_logger_send: /usr/lib64/libQtSql.so
CMakeFiles/CMakeRelink.dir/ssm_logger_send: /usr/lib64/libQtNetwork.so
CMakeFiles/CMakeRelink.dir/ssm_logger_send: /usr/lib64/libQtCore.so
CMakeFiles/CMakeRelink.dir/ssm_logger_send: CMakeFiles/ssm_logger_send.dir/relink.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable CMakeFiles/CMakeRelink.dir/ssm_logger_send"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ssm_logger_send.dir/relink.txt --verbose=$(VERBOSE)

# Rule to relink during preinstall.
CMakeFiles/ssm_logger_send.dir/preinstall: CMakeFiles/CMakeRelink.dir/ssm_logger_send
.PHONY : CMakeFiles/ssm_logger_send.dir/preinstall

CMakeFiles/ssm_logger_send.dir/requires: CMakeFiles/ssm_logger_send.dir/ssm_logger_send.cpp.o.requires
CMakeFiles/ssm_logger_send.dir/requires: CMakeFiles/ssm_logger_send.dir/ssm_logger_if.cpp.o.requires
CMakeFiles/ssm_logger_send.dir/requires: CMakeFiles/ssm_logger_send.dir/ssmC.cpp.o.requires
CMakeFiles/ssm_logger_send.dir/requires: CMakeFiles/ssm_logger_send.dir/ssmS.cpp.o.requires
.PHONY : CMakeFiles/ssm_logger_send.dir/requires

CMakeFiles/ssm_logger_send.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/ssm_logger_send.dir/cmake_clean.cmake
.PHONY : CMakeFiles/ssm_logger_send.dir/clean

CMakeFiles/ssm_logger_send.dir/depend: ssm_logger_if.moc
CMakeFiles/ssm_logger_send.dir/depend: ssm_logger_if.cpp
CMakeFiles/ssm_logger_send.dir/depend: ssm_logger_if.h
CMakeFiles/ssm_logger_send.dir/depend: ssmC.cpp
CMakeFiles/ssm_logger_send.dir/depend: ssmS.cpp
CMakeFiles/ssm_logger_send.dir/depend: /home/pbetti/adams/include/generated/ssmC.h
CMakeFiles/ssm_logger_send.dir/depend: /home/pbetti/adams/include/generated/ssmS.h
CMakeFiles/ssm_logger_send.dir/depend: /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml
	cd /home/pbetti/adams/shared/adams_ssm/ssm_logger/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/pbetti/adams/shared/adams_ssm/ssm_logger /home/pbetti/adams/shared/adams_ssm/ssm_logger /home/pbetti/adams/shared/adams_ssm/ssm_logger/build /home/pbetti/adams/shared/adams_ssm/ssm_logger/build /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles/ssm_logger_send.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/ssm_logger_send.dir/depend

