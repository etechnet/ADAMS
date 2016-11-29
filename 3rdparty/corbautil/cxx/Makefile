#-----------------------------------------------------------------------
#
#-----------------------------------------------------------------------

include $(STS_HOME)/STS/MAKE/make_path.mk
include $(PATMAK)/make_comp.mk
ifeq ($(USE_CORBA), IONA)
include ../Makefile.inc.orbix
endif
ifeq ($(USE_CORBA), ORBACUS)
include ../Makefile.inc.orbacus
endif
ifeq ($(USE_CORBA), TAO)
include ../Makefile.inc.tao
endif

#--------
# Lists of files used by make rules.
#--------

ifeq ($(STS_PLATFORM), STS_TRU64)
LIB_OBJ = PoaUtility/PoaUtility.o \
	  import_export/import_export.o \
	  PoaUtility/cxx_repository/*.o \
	  import_export/cxx_repository/*.o
else
LIB_OBJ = PoaUtility/PoaUtility.o \
	  PolicyListParser/PolicyListParser.o \
	  import_export/import_export.o
endif

#--------
# Rules
#--------

default:	all

all:		../libcorbautil.a

../libcorbautil.a:	make_in_subdirs
		ar cr ../libcorbautil.a $(LIB_OBJ)

make_in_subdirs:
	sh -c "cd PoaUtility; $(MAKE)"
	sh -c "cd PolicyListParser; $(MAKE)"
	sh -c "cd import_export; $(MAKE)"

clean:
	sh -c "cd PoaUtility; $(MAKE) clean"
	sh -c "cd PolicyListParser; $(MAKE) clean"
	sh -c "cd import_export; $(MAKE) clean"
	-rm -f ../*.a

