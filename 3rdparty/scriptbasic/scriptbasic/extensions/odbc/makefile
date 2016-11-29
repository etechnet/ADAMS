


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/odbc.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/odbc.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_odbc.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/odbc.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/odbc.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/odbc.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/odbc.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/interface.o -liodbc

/home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/odbc/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_odbc.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_odbc.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_odbc.texi


