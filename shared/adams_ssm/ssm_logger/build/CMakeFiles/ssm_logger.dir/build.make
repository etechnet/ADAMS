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
include CMakeFiles/ssm_logger.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/ssm_logger.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/ssm_logger.dir/flags.make

ssm_loggeradaptor.moc: ssm_loggeradaptor.h
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating ssm_loggeradaptor.moc"
	/usr/bin/moc -I/usr/include -I/usr/include/QtDBus -I/usr/include/QtXml -I/usr/include/QtSql -I/usr/include/QtNetwork -I/usr/include/QtCore -I/usr/include/QtDesigner -I/usr/include/QtDeclarative -I/usr/include/QtScriptTools -I/usr/include/QtDBus -I/usr/include/QtDesigner -I/usr/include/QtXml -I/usr/include/QtSql -I/usr/include/QtOpenGL -I/usr/include/QtMultimedia -I/usr/include/QtNetwork -I/usr/include/phonon -I/usr/include/QtXmlPatterns -I/usr/include/QtWebKit -I/usr/include/QtHelp -I/usr/include/QtUiTools -I/usr/include/QtTest -I/usr/include/QtScript -I/usr/include/QtSvg -I/usr/include/Qt3Support -I/usr/include/QtGui -I/usr/include/QtCore -I/usr/share/qt4/mkspecs/default -I/usr/include -I/usr/include/QtCore -I/home/pbetti/adams/shared/adams_ssm/ssm_logger/build -I/home/pbetti/adams/include -I/home/pbetti/adams/include/generated -I/home/pbetti/adams/shared/adams_ssm/ssm_logger -DQT_NO_DEBUG -DQT_DBUS_LIB -DQT_XML_LIB -DQT_SQL_LIB -DQT_NETWORK_LIB -DQT_CORE_LIB -o /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_loggeradaptor.moc /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_loggeradaptor.h

ssm_loggeradaptor.cpp: /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating ssm_loggeradaptor.cpp, ssm_loggeradaptor.h"
	/usr/bin/qdbusxml2cpp -m -a ssm_loggeradaptor -i /home/pbetti/adams/include/ssm_logger.h -l ssm_logger /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml

ssm_loggeradaptor.h: ssm_loggeradaptor.cpp

ssm_logger_if.moc: ssm_logger_if.h
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_3)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating ssm_logger_if.moc"
	/usr/bin/moc -I/usr/include -I/usr/include/QtDBus -I/usr/include/QtXml -I/usr/include/QtSql -I/usr/include/QtNetwork -I/usr/include/QtCore -I/usr/include/QtDesigner -I/usr/include/QtDeclarative -I/usr/include/QtScriptTools -I/usr/include/QtDBus -I/usr/include/QtDesigner -I/usr/include/QtXml -I/usr/include/QtSql -I/usr/include/QtOpenGL -I/usr/include/QtMultimedia -I/usr/include/QtNetwork -I/usr/include/phonon -I/usr/include/QtXmlPatterns -I/usr/include/QtWebKit -I/usr/include/QtHelp -I/usr/include/QtUiTools -I/usr/include/QtTest -I/usr/include/QtScript -I/usr/include/QtSvg -I/usr/include/Qt3Support -I/usr/include/QtGui -I/usr/include/QtCore -I/usr/share/qt4/mkspecs/default -I/usr/include -I/usr/include/QtCore -I/home/pbetti/adams/shared/adams_ssm/ssm_logger/build -I/home/pbetti/adams/include -I/home/pbetti/adams/include/generated -I/home/pbetti/adams/shared/adams_ssm/ssm_logger -DQT_NO_DEBUG -DQT_DBUS_LIB -DQT_XML_LIB -DQT_SQL_LIB -DQT_NETWORK_LIB -DQT_CORE_LIB -o /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.moc /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.h

ssm_logger_if.cpp: /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_4)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating ssm_logger_if.cpp, ssm_logger_if.h"
	/usr/bin/qdbusxml2cpp -m -p ssm_logger_if /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml

ssm_logger_if.h: ssm_logger_if.cpp

moc_ssm_logger.cxx: /usr/bin/moc
moc_ssm_logger.cxx: /home/pbetti/adams/include/ssm_logger.h
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_5)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Qt Wrapped File"
	/usr/bin/moc -o /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/moc_ssm_logger.cxx /home/pbetti/adams/include/ssm_logger.h

ssmC.cpp: /home/pbetti/adams/idl/ssm.idl
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_6)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Compiling ssm.idl"
	cpp -P -C -I/home/pbetti/adams/include -I/home/pbetti/adams/idl /home/pbetti/adams/idl/ssm.idl ssm.idl
	/usr/bin/tao_idl -t /tmp -as -in -Sa -St -Wb,pre_include=ace/pre.h -Wb,post_include=ace/post.h -I/home/pbetti/adams/include -I/home/pbetti/adams/idl ssm.idl
	mv ssmC.h ssmS.h /home/pbetti/adams/include/generated

ssmS.cpp: ssmC.cpp

/home/pbetti/adams/include/generated/ssmC.h: ssmC.cpp

/home/pbetti/adams/include/generated/ssmS.h: ssmC.cpp

/home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml: /home/pbetti/adams/include/ssm_logger.h
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_7)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Generating /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml"
	/usr/bin/qdbuscpp2xml /home/pbetti/adams/include/ssm_logger.h -o /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml

CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o: ../ssm_logger.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_8)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger.cpp

CMakeFiles/ssm_logger.dir/ssm_logger.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/ssm_logger.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger.cpp > CMakeFiles/ssm_logger.dir/ssm_logger.cpp.i

CMakeFiles/ssm_logger.dir/ssm_logger.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/ssm_logger.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger.cpp -o CMakeFiles/ssm_logger.dir/ssm_logger.cpp.s

CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.requires

CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.provides: CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.provides

CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o

CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o: ../ssm_logger_server_impl.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_9)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger_server_impl.cpp

CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger_server_impl.cpp > CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.i

CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/ssm_logger_server_impl.cpp -o CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.s

CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.requires

CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.provides: CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.provides

CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o

CMakeFiles/ssm_logger.dir/main.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/main.cpp.o: ../main.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_10)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/main.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/main.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/main.cpp

CMakeFiles/ssm_logger.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/main.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/main.cpp > CMakeFiles/ssm_logger.dir/main.cpp.i

CMakeFiles/ssm_logger.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/main.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/main.cpp -o CMakeFiles/ssm_logger.dir/main.cpp.s

CMakeFiles/ssm_logger.dir/main.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/main.cpp.o.requires

CMakeFiles/ssm_logger.dir/main.cpp.o.provides: CMakeFiles/ssm_logger.dir/main.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/main.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/main.cpp.o.provides

CMakeFiles/ssm_logger.dir/main.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/main.cpp.o

CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o: ssm_loggeradaptor.cpp
CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o: ssm_loggeradaptor.moc
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_11)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_loggeradaptor.cpp

CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_loggeradaptor.cpp > CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.i

CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_loggeradaptor.cpp -o CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.s

CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.requires

CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.provides: CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.provides

CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o

CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o: ssm_logger_if.cpp
CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o: ssm_logger_if.moc
CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o: ssm_logger_if.moc
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_12)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.cpp

CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.cpp > CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.i

CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssm_logger_if.cpp -o CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.s

CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.requires

CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.provides: CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.provides

CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o

CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o: moc_ssm_logger.cxx
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_13)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/moc_ssm_logger.cxx

CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/moc_ssm_logger.cxx > CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.i

CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/moc_ssm_logger.cxx -o CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.s

CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.requires

CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.provides: CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.provides

CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.provides.build: CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o

CMakeFiles/ssm_logger.dir/ssmC.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/ssmC.cpp.o: ssmC.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_14)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/ssmC.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/ssmC.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmC.cpp

CMakeFiles/ssm_logger.dir/ssmC.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/ssmC.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmC.cpp > CMakeFiles/ssm_logger.dir/ssmC.cpp.i

CMakeFiles/ssm_logger.dir/ssmC.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/ssmC.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmC.cpp -o CMakeFiles/ssm_logger.dir/ssmC.cpp.s

CMakeFiles/ssm_logger.dir/ssmC.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/ssmC.cpp.o.requires

CMakeFiles/ssm_logger.dir/ssmC.cpp.o.provides: CMakeFiles/ssm_logger.dir/ssmC.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/ssmC.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/ssmC.cpp.o.provides

CMakeFiles/ssm_logger.dir/ssmC.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/ssmC.cpp.o

CMakeFiles/ssm_logger.dir/ssmS.cpp.o: CMakeFiles/ssm_logger.dir/flags.make
CMakeFiles/ssm_logger.dir/ssmS.cpp.o: ssmS.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles $(CMAKE_PROGRESS_15)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/ssm_logger.dir/ssmS.cpp.o"
	/usr/bin/g++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/ssm_logger.dir/ssmS.cpp.o -c /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmS.cpp

CMakeFiles/ssm_logger.dir/ssmS.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ssm_logger.dir/ssmS.cpp.i"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmS.cpp > CMakeFiles/ssm_logger.dir/ssmS.cpp.i

CMakeFiles/ssm_logger.dir/ssmS.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ssm_logger.dir/ssmS.cpp.s"
	/usr/bin/g++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/ssmS.cpp -o CMakeFiles/ssm_logger.dir/ssmS.cpp.s

CMakeFiles/ssm_logger.dir/ssmS.cpp.o.requires:
.PHONY : CMakeFiles/ssm_logger.dir/ssmS.cpp.o.requires

CMakeFiles/ssm_logger.dir/ssmS.cpp.o.provides: CMakeFiles/ssm_logger.dir/ssmS.cpp.o.requires
	$(MAKE) -f CMakeFiles/ssm_logger.dir/build.make CMakeFiles/ssm_logger.dir/ssmS.cpp.o.provides.build
.PHONY : CMakeFiles/ssm_logger.dir/ssmS.cpp.o.provides

CMakeFiles/ssm_logger.dir/ssmS.cpp.o.provides.build: CMakeFiles/ssm_logger.dir/ssmS.cpp.o

# Object files for target ssm_logger
ssm_logger_OBJECTS = \
"CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o" \
"CMakeFiles/ssm_logger.dir/main.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o" \
"CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o" \
"CMakeFiles/ssm_logger.dir/ssmC.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssmS.cpp.o"

# External object files for target ssm_logger
ssm_logger_EXTERNAL_OBJECTS =

ssm_logger: CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/main.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o
ssm_logger: CMakeFiles/ssm_logger.dir/ssmC.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/ssmS.cpp.o
ssm_logger: CMakeFiles/ssm_logger.dir/build.make
ssm_logger: /usr/lib64/libQtDBus.so
ssm_logger: /usr/lib64/libQtXml.so
ssm_logger: /usr/lib64/libQtSql.so
ssm_logger: /usr/lib64/libQtNetwork.so
ssm_logger: /usr/lib64/libQtCore.so
ssm_logger: CMakeFiles/ssm_logger.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable ssm_logger"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ssm_logger.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/ssm_logger.dir/build: ssm_logger
.PHONY : CMakeFiles/ssm_logger.dir/build

# Object files for target ssm_logger
ssm_logger_OBJECTS = \
"CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o" \
"CMakeFiles/ssm_logger.dir/main.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o" \
"CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o" \
"CMakeFiles/ssm_logger.dir/ssmC.cpp.o" \
"CMakeFiles/ssm_logger.dir/ssmS.cpp.o"

# External object files for target ssm_logger
ssm_logger_EXTERNAL_OBJECTS =

CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/main.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/ssmC.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/ssmS.cpp.o
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/build.make
CMakeFiles/CMakeRelink.dir/ssm_logger: /usr/lib64/libQtDBus.so
CMakeFiles/CMakeRelink.dir/ssm_logger: /usr/lib64/libQtXml.so
CMakeFiles/CMakeRelink.dir/ssm_logger: /usr/lib64/libQtSql.so
CMakeFiles/CMakeRelink.dir/ssm_logger: /usr/lib64/libQtNetwork.so
CMakeFiles/CMakeRelink.dir/ssm_logger: /usr/lib64/libQtCore.so
CMakeFiles/CMakeRelink.dir/ssm_logger: CMakeFiles/ssm_logger.dir/relink.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable CMakeFiles/CMakeRelink.dir/ssm_logger"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ssm_logger.dir/relink.txt --verbose=$(VERBOSE)

# Rule to relink during preinstall.
CMakeFiles/ssm_logger.dir/preinstall: CMakeFiles/CMakeRelink.dir/ssm_logger
.PHONY : CMakeFiles/ssm_logger.dir/preinstall

CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/ssm_logger.cpp.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/ssm_logger_server_impl.cpp.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/main.cpp.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/ssm_loggeradaptor.cpp.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/ssm_logger_if.cpp.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/moc_ssm_logger.cxx.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/ssmC.cpp.o.requires
CMakeFiles/ssm_logger.dir/requires: CMakeFiles/ssm_logger.dir/ssmS.cpp.o.requires
.PHONY : CMakeFiles/ssm_logger.dir/requires

CMakeFiles/ssm_logger.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/ssm_logger.dir/cmake_clean.cmake
.PHONY : CMakeFiles/ssm_logger.dir/clean

CMakeFiles/ssm_logger.dir/depend: ssm_loggeradaptor.moc
CMakeFiles/ssm_logger.dir/depend: ssm_loggeradaptor.cpp
CMakeFiles/ssm_logger.dir/depend: ssm_loggeradaptor.h
CMakeFiles/ssm_logger.dir/depend: ssm_logger_if.moc
CMakeFiles/ssm_logger.dir/depend: ssm_logger_if.cpp
CMakeFiles/ssm_logger.dir/depend: ssm_logger_if.h
CMakeFiles/ssm_logger.dir/depend: moc_ssm_logger.cxx
CMakeFiles/ssm_logger.dir/depend: ssmC.cpp
CMakeFiles/ssm_logger.dir/depend: ssmS.cpp
CMakeFiles/ssm_logger.dir/depend: /home/pbetti/adams/include/generated/ssmC.h
CMakeFiles/ssm_logger.dir/depend: /home/pbetti/adams/include/generated/ssmS.h
CMakeFiles/ssm_logger.dir/depend: /home/pbetti/adams/xml/net.etech.adams.ssm_logger.xml
	cd /home/pbetti/adams/shared/adams_ssm/ssm_logger/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/pbetti/adams/shared/adams_ssm/ssm_logger /home/pbetti/adams/shared/adams_ssm/ssm_logger /home/pbetti/adams/shared/adams_ssm/ssm_logger/build /home/pbetti/adams/shared/adams_ssm/ssm_logger/build /home/pbetti/adams/shared/adams_ssm/ssm_logger/build/CMakeFiles/ssm_logger.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/ssm_logger.dir/depend

