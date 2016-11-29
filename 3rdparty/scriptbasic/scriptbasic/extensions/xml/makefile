


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/xml.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/xml.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_xml.texi




/home/riveraa/tmp/scriptbasic/bin/mod/lib/xml.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/s_interface.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/xml.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/s_interface.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/xml.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/interface.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/xml.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/interface.o -lxml2

/home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/interface.o : interface.c 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/s_interface.o : interface.c 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/xml/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_xml.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_xml.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_xml.texi


