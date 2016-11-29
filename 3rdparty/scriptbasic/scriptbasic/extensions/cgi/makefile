


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/cgi.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/cgi.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_cgi.texi


/home/riveraa/tmp/scriptbasic/bin/mod/lib/cgi.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/cgi.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/cgi.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/cgi.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/cgi.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/cgi.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/cgi.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/cgi.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/interface.o 

/home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/cgi.o : cgi.c cgi.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/cgi.o cgi.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/interface.o : interface.c cgi.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/s_interface.o : interface.c cgi.h 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/cgi/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_cgi.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_cgi.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_cgi.texi


