import dyc.bas

a$ = "message"
a$ &= " text"
a$ &= chr$(0)

print dyc::dyc("ms,i,USER32.DLL,MessageBox,PZZL",0,a$,"title",3)
