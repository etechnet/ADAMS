// Struttura da restituire al MS dopo che l'utente ha popolato i vari campi 
// interagendo con l'interfaccia grafica.
// il metodo da invocare ul MS è:mdm_server_serverRef.runMDMServerBlocking(<STRUCT_PARAMS>,<Url>);
// dove:	STRUCT_PARAMS = struttura riempita
//		Url = URL dove il MS restituirà il report sottoforma di HTML

//package corbalib;

public final class STRUCT_PARAMS_Local 
{
    /*private STRUCT_PARAMS localParam=new STRUCT_PARAMS();
    
    public STRUCT_PARAMS returnSTRUCT_PARAMS()
    {
        reurn (STRUCT_PARAMS)localParam;
    }*/
    
    public STRUCT_PARAMS_Local() {}
    
    //Riempie in un sol colpo tutta la struttura dei parametri;
    /*public STRUCT_PARAMS_Local(int TipoAnalisi,int NetworkRealTime,int TipoDati,int TipoOutput,char[][] ElaborationData,int[] Centrali,boolean RestrizioneOraria,short OraInizio,short OraFine,int Relazione,int DirezioneRelazione,Rest[] Restrizioni,int DistribuzioneOraria,boolean Distribuzione15Min,boolean OppositeRestriction,boolean FlagSort,int ElementToSort,boolean Ascendente,boolean Reserved,boolean SingleRelation,int NumeroCifreUtente,boolean NoLines,boolean IsPercent) {
    {
        localParam.STRUCT_PARAMS(TipoAnalisi,NetworkRealTime,TipoDati,TipoOutput,ElaborationData,Centrali,RestrizioneOraria,OraInizio,OraFine,Relazione,DirezioneRelazione,Restrizioni,DistribuzioneOraria,Distribuzione15Min,OppositeRestriction,FlagSort,ElementToSort,Ascendente,Reserved,SingleRelation,NumeroCifreUtente,NoLines,IsPercent);
    }
    */
    
    /********************  Situazione temporanea per lavorare in locale ********************/
    public int TipoAnalisi;             //OK
    public int NetworkRealTime;
    public int TipoDati;                
    public int TipoOutput;              //OK
    public char[][] ElaborationData;
    public int[] Centrali;              //OK
    public boolean RestrizioneOraria;
    public short OraInizio;
    public short OraFine;
    public int Relazione;               //OK
    public int DirezioneRelazione;      //OK
    public REST_INFO[] Restrizioni;
    public int DistribuzioneOraria;
    public boolean Distribuzione15Min;
    public boolean OppositeRestriction;
    public boolean FlagSort;            //OK
    public int ElementToSort;           //OK
    
    public boolean Ascendente;
    public boolean Reserved;
    public boolean SingleRelation;
    public int NumeroCifreUtente;
    public boolean NoLines;
    public boolean IsPercent;           //OK
    
    /******************** metodi per settaggio delle variabili di STRUCT_PARAMS **************/
    
    public void setTipoAnalisi(int tipoAnalisi)
    {
    	this.TipoAnalisi=tipoAnalisi;
        ////System.out.println(">> STRUCT_PARAMS_Local-> TipoAnalisi: "+TipoAnalisi);
    }
    
    public void setNetworkRealTime(int networkRealTime)
    {
    	this.NetworkRealTime=networkRealTime;
    }
    
    public void setTipoDati(int tipoDati)
    {
    	this.TipoDati=tipoDati;
    }
    
    public void setTipoOutput(int tipoOutput)
    {
    	this.TipoOutput=tipoOutput;
        ////System.out.println(">> STRUCT_PARAMS_Local-> Output: "+TipoOutput);
    }
    
    public void setElaborationData(char[][] elaborationData)
    {
    	this.ElaborationData=elaborationData;
    }
    
    public void setCentrali(int[] centrali)
    {
    	this.Centrali=centrali;
        System.out.print(">> STRUCT_PARAMS_Local-> Centrali: [ ");
        for(int i=0;i<Centrali.length;i++)
            System.out.print(Centrali[i]+" ");
        ////System.out.println("]");
        
    }
    
    public void setRestrizioneOraria(boolean val)
    {
    	this.RestrizioneOraria=val;
    }
    
    public void setOraInizio(short oraInizio)
    {
    	this.OraInizio=oraInizio;
    }
    
    public void setOraFine(short oraFine)
    {
    	this.OraFine=oraFine;
    }
    
    public void setRelazione(int relazione)
    {
    	this.Relazione=relazione;
        ////System.out.println(">>  STRUCT_PARAMS_Local-> IdRelation: "+Relazione);
    }
    
    public void setDirezioneRelazione(int direzioneRelazione)
    {
    	this.DirezioneRelazione=direzioneRelazione;
        ////System.out.println(">> STRUCT_PARAMS_Local-> Direzione: "+DirezioneRelazione);
    }
    
    public void setRestrizioni(REST_INFO[] restrizioni)
    {
    	this.Restrizioni=restrizioni;
    }
    
    public void setDistribuzioneOraria(int distribuzioneOraria)
    {
    	this.DistribuzioneOraria=distribuzioneOraria;
    }
    
    public void setDistribuzione15Min(boolean val)
    {
    	this.Distribuzione15Min=val;
    }
    
    public void setOppositeRestriction(boolean val)
    {
    	this.OppositeRestriction=val;
    }
    
    public void setFlagSort(boolean val)
    {
    	this.FlagSort=val;
        ////System.out.println(">> STRUCT_PARAMS_Local-> FlagSort: "+FlagSort);
    }
    
    public void setElementToSort(int elementToSort)
    {
    	this.ElementToSort=elementToSort;
        ////System.out.println(">> STRUCT_PARAMS_Local-> ElementToSort: "+elementToSort);
    }
      
    public void setAscendente(boolean val)
    {
    	this.Ascendente=val;
    }
    
    public void setReserved(boolean val)
    {
    	this.Reserved=val;
    }
    
    public void setSingleRelation(boolean val)
    {
    	this.SingleRelation=val;
    }
    
    public void setNumeroCifreUtente(int numeroCifreUtente)
    {
    	this.NumeroCifreUtente=numeroCifreUtente;
    }
    
    public void setNoLines(boolean val)
    {
    	this.NoLines=val;
    }
    
    public void setIsPercent(boolean val)
    {
    	this.IsPercent=val;
        ////System.out.println(">> STRUCT_PARAMS_Local-> IsPercent: "+IsPercent);
    }
}
