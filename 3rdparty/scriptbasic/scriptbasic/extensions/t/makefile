


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/t.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/t.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_t.texi


/home/riveraa/tmp/scriptbasic/bin/mod/lib/t.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/t/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/t.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/t/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/t.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/t/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/t.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/t/interface.o 

/home/riveraa/tmp/scriptbasic/bin/mod/obj/t/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/t/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/t/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/t/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_t.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_t.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_t.texi


