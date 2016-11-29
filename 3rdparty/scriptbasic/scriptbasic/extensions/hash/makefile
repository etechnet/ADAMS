


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/hash.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/hash.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_hash.texi


/home/riveraa/tmp/scriptbasic/bin/mod/lib/hash.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/hash.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/hash.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/hash.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/interface.o 

/home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/hash/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_hash.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_hash.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_hash.texi


