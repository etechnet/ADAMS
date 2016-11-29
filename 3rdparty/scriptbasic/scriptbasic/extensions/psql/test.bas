	include psql.bas
	PGSQL::PGconndefaults(cd)
	print "conndefaults=(", isarray(cd), ")\n"
	print "escapeBytea: ", PGSQL::PGescapeBytea("a'\000b")="a\\'\\\\000b", "\n"
	print "escapeString: ", PGSQL::PGescapeString("a'\000b")="a''", "\n"
