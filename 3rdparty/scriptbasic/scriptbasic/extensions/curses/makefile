


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/curses.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/curses.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_curses.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/curses.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/curses.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/curses.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/curses.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/interface.o -lcurses

/home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/curses/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_curses.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_curses.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_curses.texi


