


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/gd.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/gd.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_gd.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/gd.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/gd.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/gd.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/gd.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/interface.o -lc -lpng -lz -lgd

/home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/interface.o : interface.c gdfontl.h gdfontt.h gd.h gdfontg.h gdfontmb.h gdfonts.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/s_interface.o : interface.c gdfontl.h gdfontt.h gd.h gdfontg.h gdfontmb.h gdfonts.h 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/gd/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_gd.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_gd.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_gd.texi


