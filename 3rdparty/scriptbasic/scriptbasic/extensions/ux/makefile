


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/ux.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/ux.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_ux.texi


/home/riveraa/tmp/scriptbasic/bin/mod/lib/ux.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/ux.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/ux.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/ux.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/interface.o 

/home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/ux/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_ux.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_ux.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_ux.texi


