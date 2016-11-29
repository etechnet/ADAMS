/*
 * IMG_T_POLITICHE.java
 *
 * Created on 29 maggio 2009, 8.44
 */

/**
 *
 * @author  luca
 * @version 
 */
 
 /**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> NTM Configurator Client </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *
 *La classe è strutturata in modo specifico, per l'interfacciamento delle tabelle Oracle contenenti i dati per la gestione
 *delle politiche e relazioni di traffico degli allarmi per dell'applicativo NTM Configurator, di seguito le tabelle d'interesse: 
 *<pre> 
 *
 *
 *</pre>
 * Inoltre la classe è implementata con altrettanti metodi per la l'inserimento, modifica e cancellazione dei dati delle
 * suddette tabelle.
 *
 */
public class ADAMS_T_POLITICHE {

    /** Costruttore di default della classe */
    public ADAMS_T_POLITICHE(ADAMS_OracleConnection oracleConnection) 
    {
        TRAFF_ID    = -1;       //NOT NULL NUMBER(2)
        POL_ID      = 'M';      //NOT NULL CHAR(1)
        INTERVALLO  = -1;       //NOT NULL NUMBER(2)
        PERSISTENZA = -1;       //NUMBER(2)
        COEFF       = 1.0F;     //NOT NULL NUMBER(4,2)
        ID_KPI      = -1;       //NUMBER(6)
        DESCR       ="";        //NOT NULL VARCHAR2(50)
        DESC_COMP   ="Not Defined";
        TIPO_DI_CONFIGURAZIONE =    ""; //VARCHAR2(30)  
        
        this.local_OracleConnection = oracleConnection;
    }
    
    public ADAMS_T_POLITICHE() 
    {
        TRAFF_ID    = -1;       //NOT NULL NUMBER(2)
        POL_ID      = 'M';      //NOT NULL CHAR(1)
        INTERVALLO  = -1;       //NOT NULL NUMBER(2)
        PERSISTENZA = -1;       //NUMBER(2)
        COEFF       = 1.0F;     //NOT NULL NUMBER(4,2)
        ID_KPI      = -1;       //NUMBER(6)
        DESCR       ="";        //NOT NULL VARCHAR2(50)
        DESC_COMP   ="Not Defined";
        TIPO_DI_CONFIGURAZIONE =    ""; //VARCHAR2(30)  
        
        this.local_OracleConnection = null;
    }
    
    public void set_OracleConnection(ADAMS_OracleConnection oracleConnection)
    {
        this.local_OracleConnection = oracleConnection;
    }
    
    
    public void DebugPrintData()
    {
        System.out.println();
        System.out.println("TRAFF_ID    = "+TRAFF_ID);    
        System.out.println("POL_ID      = "+POL_ID);  
        System.out.println("INTERVALLO  = "+INTERVALLO);  
        System.out.println("PERSISTENZA = "+PERSISTENZA);  
        System.out.println("COEFF       = "+COEFF);  
        System.out.println("ID_KPI      = "+ID_KPI);  
        System.out.println("DESCR       = "+DESCR);  
        System.out.println("DESC_COMP   = "+DESC_COMP);  
        System.out.println("TIPO_DI_CONFIGURAZIONE = "+TIPO_DI_CONFIGURAZIONE);
        System.out.println();
    }
    /**
     * Metodo per l'inserimento dei dati delle tabelle T_TRAF_REL e T_POLITICHE, il metodo utilizza l'istanza della classe
     * IMG_OracleConnection per interfacciare il Database mediante driver ojdbc.
     *
     *see IMG_OracleConnection
     */
    public int insert_T_POLITICHE()
    {        
        if(TRAFF_ID == -1)
        {
            ADAMS_GlobalParam.optionPanel((javax.swing.JFrame)null,"Operation failed [Error: invalid TRAFF_ID <"+TRAFF_ID+"> ].","ERROR",1);
            return -1;
        }

        String itemName_T_TRAF_REL = ("TRAFF_ID,DESCR,DESC_COMP,ID_KPI,TIPO_DI_CONFIGURAZIONE");                      // campi T_TRAF_REL        
        String itemValue_T_TRAF_REL = (+TRAFF_ID+",'"+DESCR+"','"+DESC_COMP+"',"+ID_KPI+",'"+TIPO_DI_CONFIGURAZIONE+"'");   // valori T_TRAF_REL
        
        String str_Insert_T_TRAF_REL = ("insert into "+name_T_TRAF_REL+" ("+itemName_T_TRAF_REL+") values ( "+itemValue_T_TRAF_REL+" )");
        //System.out.println("str_Insert_T_TRAF_REL --> "+str_Insert_T_TRAF_REL);
                       
        try
        {        
            this.local_OracleConnection.DBConnection.setAutoCommit(false); 
            //System.out.println("setAutoCommit(false)");
        }
        catch(java.sql.SQLException exc){}        
        
        int Answer_Ins = this.local_OracleConnection.Operation(str_Insert_T_TRAF_REL);
        
        if(Answer_Ins >= 0 ) // Se operazione su T_TRAF_REL OK procedo con T_POLITICHE
        {
            //System.out.println("OK Insert su T_TRAF_REL --> "+Answer_Ins);
            
            String itemName_T_POLITICHE = ("TRAFF_ID,POL_ID,INTERVALLO,PERSISTENZA,COEFF,ID_KPI");  // Campi T_POLITICHE        
            
            String str_Persistenza = "null";
            if(PERSISTENZA != -1)
                str_Persistenza = ""+PERSISTENZA;
            
            String itemValue_T_POLITICHE = (+TRAFF_ID+",'"+POL_ID+"',"+INTERVALLO+","+str_Persistenza+","+COEFF+","+ID_KPI);   // Valori T_POLITICHE   
            
            String str_Insert_T_POLITICHE = ("insert into "+name_T_POLITICHE+" ("+itemName_T_POLITICHE+") values ( "+itemValue_T_POLITICHE+" )");
            //System.out.println("str_Insert_T_POLITICHE --> "+str_Insert_T_POLITICHE);
            
            Answer_Ins = this.local_OracleConnection.Operation(str_Insert_T_POLITICHE);
             
           
            try
            {         
                if(Answer_Ins >= 0)
                {                
                    this.local_OracleConnection.DBConnection.commit();
                    //System.out.println("Commit OK");
                }
                else
                {
                    this.local_OracleConnection.DBConnection.rollback();
                    //System.out.println("rollback()");
                }

                this.local_OracleConnection.DBConnection.setAutoCommit(true); 
            }
            catch(java.sql.SQLException exc)
            {
                System.out.println("Errore insert_T_POLITICHE");
                try
                { 
                    this.local_OracleConnection.DBConnection.setAutoCommit(true);
                }
                catch(java.sql.SQLException exc1){System.out.println("Errore insert_T_POLITICHE A");}
            }
                    
        }   
        return Answer_Ins;
    }
    
    // MODIFICA in due tabelle T_TRAF_REL e T_POLITICHE 
    /**
     * Metodo per la modifica dei dati delle tabelle T_TRAF_REL e T_POLITICHE, il metodo utilizza l'istanza della classe
     * IMG_OracleConnection per interfacciare il Database mediante driver ojdbc.
     *
     *see IMG_OracleConnection
     */
    public int update_T_TRAF_REL()
    {
                                 
        // campi  e valori T_TRAF_REL  
        String str_update_T_TRAF_REL =  "update "+name_T_TRAF_REL+" "+
                                            "set DESCR='"+DESCR+"' "+                                            
                                            "where TRAFF_ID="+TRAFF_ID+" and TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'";        
        
        //System.out.println("STRINGA str_update_T_TRAF_REL = "+str_update_T_TRAF_REL);
        
        try
        {        
            this.local_OracleConnection.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        

        int Answer_update = this.local_OracleConnection.Operation(str_update_T_TRAF_REL);
        
        try
        {         
            if(Answer_update >= 0)
            {                
                this.local_OracleConnection.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                this.local_OracleConnection.DBConnection.rollback();
                //System.out.println("rollback()");
            }

            this.local_OracleConnection.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore update_T_TRAF_REL ");
            try
            { 
                this.local_OracleConnection.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore update_T_TRAF_REL 2A");}
        }        
        return Answer_update;
    }
    
     // CANCELLA in due tabelle T_POLITICHE e T_TRAF_REL
     
     /**
     * Metodo per la ccellazione dei dati delle tabelle T_TRAF_REL e T_POLITICHE, il metodo utilizza l'istanza della classe
     * IMG_OracleConnection per interfacciare il Database mediante driver ojdbc.
     *
     *see IMG_OracleConnection
     */
    /*public int delete_T_POLITICHE()
    {                        
                    
        String str_delete_T_POLITICHE = "delete from "+name_T_POLITICHE+" where TRAFF_ID="+TRAFF_ID+" and ID_KPI="+ID_KPI;
        //System.out.println("STRINGA str_delete_T_POLITICHE = "+str_delete_T_POLITICHE);
        
        try
        {        
            this.local_OracleConnection.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_del = this.local_OracleConnection.Operation(str_delete_T_POLITICHE);
        //System.out.println("ESITO str_delete_T_POLITICHE = "+Answer_del);
                    
        
        if(Answer_del >= 0)
        {                
            String str_delete_T_TRAF_REL = "delete from "+name_T_TRAF_REL+" where TRAFF_ID="+TRAFF_ID+"and ID_KPI="+ID_KPI;
            //System.out.println("STRINGA str_delete_T_TRAF_REL = "+str_delete_T_TRAF_REL);

            Answer_del = this.local_OracleConnection.Operation(str_delete_T_TRAF_REL);
            //System.out.println("ESITO str_delete_T_TRAF_REL = "+Answer_del);
        }        
        
        try
        {         
            if(Answer_del >= 0)
            {            
                this.local_OracleConnection.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                this.local_OracleConnection.DBConnection.rollback();
                //System.out.println("rollback()");
            }

            this.local_OracleConnection.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore delete_T_POLITICHE");
            try
            { 
                this.local_OracleConnection.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore delete_T_POLITICHE A");}
        }      
                    
        return Answer_del;
    }    */
        
    
    /** Indice Identificatico contenente il valore TRAFF_ID delle tabelle Oracle T_POLITICHE e T_TRAF_REL. */
    public int      TRAFF_ID;       //NOT NULL NUMBER(2)    --> T_POLITICHE
    
    /** Indice Identificatico contenente il valore POL_ID delle tabelle Oracle T_POLITICHE e T_TRAF_REL. */
    public char     POL_ID;         //NOT NULL CHAR(1)      --> T_POLITICHE
   
    /** Variabile contenete il valore del campo INTERVALLO della tabella Oracle T_POLITICHE. */
    public int      INTERVALLO;     //NOT NULL NUMBER(2)    --> T_POLITICHE
    
    /** Variabile contenete il valore del campo PERSISTENZA della tabella Oracle T_POLITICHE. */
    public int      PERSISTENZA;    //NUMBER(2)             --> T_POLITICHE
    
    /** Variabile contenete il valore del campo COEFF della tabella Oracle T_POLITICHE. */
    public float    COEFF;          //NOT NULL NUMBER(4,2)  --> T_POLITICHE
    
    /** Variabile contenete l'dentificativo del campo ID_KPI della tabella Oracle T_POLITICHE. */
    public int ID_KPI;  

    public String   DESCR;          //NOT NULL VARCHAR2(50) -->T_TRAF_REL
    public String   DESC_COMP;      //VARCHAR2(255)         ->T_TRAF_REL
    public String   TIPO_DI_CONFIGURAZIONE;
    
    //Nome tabella
    /** Variabile contenete il nome effettivo della tabella Oracle contenente i dati relativi alla gestione della politica degli allarmi. */
    private final String name_T_POLITICHE = "T_POLICY";
    /** Variabile contenete il nome effettivo della tabella Oracle contenente i dati relativi alla gestione delle relazioni di traffico degli allarmi. */
    private final String name_T_TRAF_REL = "T_TRAFFIC_RELATION";
    
    
    private ADAMS_OracleConnection local_OracleConnection = null;
    
   // public java.util.Vector V_REL_ELEMENTS = null; // indentificativi degli elementi della relazione.
}