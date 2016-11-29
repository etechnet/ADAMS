


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/mysql.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/mysql.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_mysql.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/mysql.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/mysql.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/mysql.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/mysql.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/interface.o -L/usr/lib/mysql -lmysqlclient

/home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/mysql/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_mysql.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_mysql.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_mysql.texi


