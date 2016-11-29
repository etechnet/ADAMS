


all : /home/riveraa/tmp/scriptbasic/bin/mod/lib/re.a /home/riveraa/tmp/scriptbasic/bin/mod/dll/re.so /home/riveraa/tmp/scriptbasic/bin/texi/mod_re.texi


/home/riveraa/tmp/scriptbasic/bin/mod/lib/re.a : /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/s_interface.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regcomp.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regerror.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regexec.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regex_test.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regfree.o 
	ar -r /home/riveraa/tmp/scriptbasic/bin/mod/lib/re.a /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/s_interface.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regcomp.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regerror.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regexec.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regex_test.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regfree.o  

/home/riveraa/tmp/scriptbasic/bin/mod/dll/re.so : /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/interface.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regcomp.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regerror.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regexec.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regex_test.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regfree.o 
	ld -shared -Bstatic -o /home/riveraa/tmp/scriptbasic/bin/mod/dll/re.so /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/interface.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regcomp.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regerror.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regexec.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regex_test.o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regfree.o 

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/interface.o : interface.c regex.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/s_interface.o : interface.c regex.h 
	gcc -w -DSTATIC_LINK=1 -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/s_interface.o interface.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regcomp.o : regcomp.c cclass.h utils.h cname.h regex2.h regex.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regcomp.o regcomp.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regerror.o : regerror.c utils.h regex.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regerror.o regerror.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regexec.o : regexec.c utils.h regex2.h regex.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regexec.o regexec.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regex_test.o : regex_test.c regex.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regex_test.o regex_test.c

/home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regfree.o : regfree.c utils.h regex2.h regex.h 
	gcc -w -c -o /home/riveraa/tmp/scriptbasic/bin/mod/obj/re/regfree.o regfree.c

/home/riveraa/tmp/scriptbasic/bin/texi/mod_re.texi : manual.texi.jam
	perl /home/riveraa/tmp/scriptbasic/jamal.pl manual.texi.jam /home/riveraa/tmp/scriptbasic/bin/texi/mod_re.texi
	perl /home/riveraa/tmp/scriptbasic/t2h.pl /home/riveraa/tmp/scriptbasic/bin/texi/mod_re.texi


