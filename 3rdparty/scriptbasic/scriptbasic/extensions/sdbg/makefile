


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/sdbg.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/sdbg.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_sdbg.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/sdbg.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/sdbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/sdbg.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/sdbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/sdbg.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/sdbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/sdbg.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/sdbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/sdbg_con.o : sdbg_con.c sdbg.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/sdbg_con.o sdbg_con.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/interface.o : interface.c sdbg_comm.h sdbg.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/s_interface.o : interface.c sdbg_comm.h sdbg.h 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/sdbg/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_sdbg.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_sdbg.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_sdbg.texi


