


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/zlib.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/zlib.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_zlib.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/zlib.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/zlib.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/zlib.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/zlib.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/interface.o -lc -lz

/home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/interface.o : interface.c zlib.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/s_interface.o : interface.c zlib.h 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/zlib/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_zlib.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_zlib.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_zlib.texi


