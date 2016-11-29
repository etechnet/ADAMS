


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/dbg.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/dbg.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_dbg.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/dbg.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/dbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/dbg.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/dbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/dbg.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/dbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/dbg.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/dbg_con.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/dbg_con.o : dbg_con.c dbg.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/dbg_con.o dbg_con.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/interface.o : interface.c dbg_comm.h dbg.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/s_interface.o : interface.c dbg_comm.h dbg.h 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/dbg/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_dbg.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_dbg.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_dbg.texi


