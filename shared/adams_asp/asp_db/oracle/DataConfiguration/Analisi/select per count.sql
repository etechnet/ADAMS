select count(a.TIPO_DI_CONFIGURAZIONE||b.IDREPORT) 
	from TAB_TIPO_ANALISI a, table (a.LISTA_REPORT) b 
	where a.TIPO_DI_CONFIGURAZIONE='N.T.M.-Voce-Standard';
