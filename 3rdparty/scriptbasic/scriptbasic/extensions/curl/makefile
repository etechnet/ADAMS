


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/curl.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/curl.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_curl.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/curl.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/curl.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/curl.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/curl.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/interface.o -lcurl -lssl -lcrypto -ldl -lc

/home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/curl/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_curl.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_curl.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_curl.texi


