package net.etech.loadconfig;
/*
 * ADAMS_TAB_CONFIG.java
 *
 * Created on 12 maggio 2005, 11.10
 */

/**
 *
 * @author  root
 * @version 
 */
 
import net.etech.ASP.*;
import java.util.Vector;
import java.sql.*;
public class ADAMS_TAB_CONFIG {

    
    /** Creates new ADAMS_TAB_CONFIG */
    public ADAMS_TAB_CONFIG() 
    {
        TIPO_DI_CONFIGURAZIONE      = new String();     // - NOT NULL   -- max length 30
        POSIZIONE_ELEMENTO          = -1;               // - NOT NULL   -- int
        POSIZIONE_CAMPO_DR         = -1;               // - NOT NULL   -- int
        TIPO_CAMPO                  = 0;               // - NOT NULL   -- int    
        SIZE_CAMPO_DR              = 0;               // - NOT NULL   -- int
        FLAG_ARRAY                  = ' ';              // - NOT NULL   -- char
        NUMERO_ELEM_ARRAY           = 0;               // - NOT NULL   -- int
        LABEL_DR_NORMALIZZ         = new String();     // - NOT NULL   -- max length 15       
        TIPO_OGGETTO_GUI            = -1;               // - int
        ICONA                       = new String();     // - max length 30
        RANGE_VAL                   = new String();     // - max length 15
        V_LISTA_VALORI              = new Vector();     // ------- Vettore coniene ADAMS_TAB_VALORI_ELEMENTO
        VALORE_DEFAULT              = 0;               // - int
        RAPPRESENTAZ_ESADECIMALE    = ' ';              // -- char
        PRIORITA                    = 0;               // - int 
        V_AGGREGAZ_ELEM_RESTRIZ     = new Vector();     // ------- Vettore contiene Integer - AGGREG_RESTRICTION -
        OPERATORI_RESTRIZIONE       = -1;               // - int
        LUNG_ELEMENTO_CHIAVE        = 0;               // - int
        V_LISTA_VALORI_SOSTITUZ     = new Vector();       // ------- Vettore contiene String - Valori Sostituzioni -
        TAG                         = new String();     // - max length 30
        DESCRIPTION                 = new String();     // - max length 160
        PLUGIN                      = new String();     // - max length 15
        PLUGIN_PATH                 = new String();     // - max length 40
        PLUGIN_GUI                  = new String();     // - max length 15
        FLAG_ABILITAZIONE_KEY       = ' ';               // char
        FLAG_CONTATORE              = ' ';               // char
        FLAG_RESTRIZIONE            = ' ';               // char
        FLAG_COMBINAZ_ANALISI       = ' ';               // char
        V_LISTA_RELAZIONI           = new Vector();     // ------- Vettore coniene ADAMS_TAB_RELAZIONI_ELEMENTO
        V_LISTA_EXCEPTION           = new Vector();     // ------- Vettore coniene ADAMS_TAB_EXCEPTION
        DEFAULT_RESTRICTION         = ' ';              // char
        FFENABLED                   = ' ';              // char Free format Relation
        ALWAYS_SHOW_REPORT          = ' ';              // char
        LINK_TE                     = -1;               //number
        V_LISTA_SCRIPTS             = new Vector();     // ------- Vettore contiene String - SCRIPT -
        SE_INDICE                   = false;            //boolean
        VALORE_AGGREGAZIONE_DEF     = -1;               //number
        VALORE_AGGREGAZIONE         = -1;               //number
        LENGTH_VALORE_START         = 0;                //number
        SUFF_VALORE_INDICE          = new String();     // - max length 10                 
        DATA_INIZIO_INDICE          = new String();     // Date
        DATA_FINE_INDICE            = new String();     // Date
    }
      
    public void read_LISTA_VALORI()
    {      
        V_LISTA_VALORI.removeAllElements();
        
        String[] nomeCampi      = {"COD_VALORE","DESC_VALORE"};
        String tableConfig      = "tab_value_type";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
            {
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            }
            else
            {
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
            }
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+"TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento+" ORDER BY COD_VALORE";
        
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("sel ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    if(ADAMS_GlobalParam.DEBUG)
                    {
                        for(int i=0;i<2;i++)
                        {
                            if(i==0)
                                System.out.println(""+nomeCampi[i]+" ->"+rs.getInt(nomeCampi[i]));
                            if(i==1)
                                System.out.println(""+nomeCampi[i]+" ->"+rs.getString(nomeCampi[i]));
                        }
                    }                  
                    ADAMS_TAB_VALORI_ELEMENTO valoriElemento = new ADAMS_TAB_VALORI_ELEMENTO(rs.getInt(nomeCampi[0]),rs.getString(nomeCampi[1]));
                    V_LISTA_VALORI.addElement(valoriElemento);
                    //add_LISTA_VALORI(valoriElemento);
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_CONFIG-1) SQL Operation " + exc.toString());
        }
    }
      
    public void read_AGGREGAZ_ELEM_RESTRIZ() 
    {      
        V_AGGREGAZ_ELEM_RESTRIZ.removeAllElements();
        
        String[] nomeCampi      = {"AGGREG_RESTRICTION"}; 
        String tableConfig      = "tab_aggregate_restrictions";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            else
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+"TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento+" ORDER BY AGGREG_RESTRICTION";
        
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("read_AGGREGAZ_ELEM_RESTRIZ() select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) )
                {
                    V_AGGREGAZ_ELEM_RESTRIZ.addElement(new Integer(rs.getInt(nomeCampi[0])));
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs == null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_CONFIG-2) SQL Operation " + exc.toString());
        }
        
        //System.out.println("V_AGGREGAZ_ELEM_RESTRIZ.size() "+V_AGGREGAZ_ELEM_RESTRIZ.size());
    }
  
    public void read_LISTA_VALORI_SOSTITUZ() 
    {      
        V_LISTA_VALORI_SOSTITUZ.removeAllElements();
        
        String[] nomeCampi      = {"VALORI_SOSTITUZIONE"};
        String tableConfig  = "tab_subst_values";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            else
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+"TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento+" ORDER BY VALORI_SOSTITUZIONE";
        
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("read_LISTA_VALORI_SOSTITUZ() select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    V_LISTA_VALORI_SOSTITUZ.addElement((String)(rs.getString(nomeCampi[0]))); 
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs == null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_CONFIG-3) SQL Operation " + exc.toString());
        }
        //System.out.println("V_LISTA_VALORI_SOSTITUZ.size() "+V_LISTA_VALORI_SOSTITUZ.size());
    }
    
    public void read_LISTA_SCRIPTS()
    {
        V_LISTA_SCRIPTS.removeAllElements();
        
        String[] nomeCampi      = {"SCRIPT","SCRIPT_TYPE"};
        String tableConfig      = "tab_list_script";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            else
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+"TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento+" ORDER BY SCRIPT";
        
       if(ADAMS_GlobalParam.DEBUG)
            System.out.println("read_LISTA_SCRIPTS() select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) )
                {
                    int v1=rs.getInt(nomeCampi[0]);
                    int v2=rs.getInt(nomeCampi[1]);
                    if(ADAMS_GlobalParam.DEBUG)
                    {
                        System.out.println("read --> ID_SCRIPT ="+v1);
                        System.out.println("read --> TYPE_SCRIPT ="+v2);
                    }
                    ADAMS_TAB_SCRIPTS_LISTA appo=new ADAMS_TAB_SCRIPTS_LISTA(v1,v2);
                    V_LISTA_SCRIPTS.addElement(appo);
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs == null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_CONFIG-4) SQL Operation " + exc.toString());
        }
    }
    
    public void read_LISTA_EXCEPTION() 
    {      
        V_LISTA_EXCEPTION.removeAllElements();
        
        String[] nomeCampi      = {"IDEXCEPTION"};
        String tableConfig      = "tab_list_exception";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            else
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+"TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento+" ORDER BY IDEXCEPTION";
        
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("read_LISTA_EXCEPTION() select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) )
                {
                    V_LISTA_EXCEPTION.addElement(new Integer(rs.getInt(nomeCampi[0])));
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs == null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_CONFIG-5) SQL Operation " + exc.toString());
        }
        //System.out.println("V_LISTA_EXCEPTION.size() "+V_LISTA_EXCEPTION.size());
    }
    
    public void read_LISTA_RELAZIONI()
    {
        V_LISTA_RELAZIONI.removeAllElements();

        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;

        //Costruzione Stringa
        String sel_ConfigNtm = "select ";
        sel_ConfigNtm = sel_ConfigNtm+ " RELATION_NAME, DIREZIONE, HEXOUTPUT, NETWORK, GHOST";
        sel_ConfigNtm = sel_ConfigNtm+ " from tab_list_relation ";
        sel_ConfigNtm = sel_ConfigNtm+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento+" order by RELATION_NAME";
          
        System.out.println("read_LISTA_RELAZIONI ----> "+sel_ConfigNtm); //nello
        //Query_rs_Statement usato per non perdere il resulset
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ConfigNtm,SQLStatement);

        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {              
                    String RelationName = rs_simple.getString("RELATION_NAME");
                    
                    ADAMS_TAB_RELAZIONI_ELEMENTO relazioni_elemento = new ADAMS_TAB_RELAZIONI_ELEMENTO(RelationName);
                    
                    relazioni_elemento.RELATION_NAME    = RelationName;
                    relazioni_elemento.DIREZIONE        = rs_simple.getInt("DIREZIONE");
                    relazioni_elemento.HEXOUTPUT        = rs_simple.getInt("HEXOUTPUT");
                    relazioni_elemento.NETWORK          = rs_simple.getInt("NETWORK");
                    relazioni_elemento.GHOST            = rs_simple.getInt("GHOST");
                                   
               //// IDANALISI
                    String sel_ConfigNtm2 = "select ";
                    sel_ConfigNtm2 = sel_ConfigNtm2+ " IDANALISI";
                    sel_ConfigNtm2 = sel_ConfigNtm2+ " from tab_list_analysis ";
                    sel_ConfigNtm2 = sel_ConfigNtm2+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento;
                    sel_ConfigNtm2 = sel_ConfigNtm2+ " and RELATION_NAME ='"+relazioni_elemento.RELATION_NAME+"' ORDER BY IDANALISI";
                    
                    //System.out.println("readAnalisi_relazioni ----> "+sel_ConfigNtm2);
                    Statement SQLStatement_2 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    java.sql.ResultSet rs_nested_2  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm2,SQLStatement_2);
                    
                    if(rs_nested_2 != null)
                    {
                        while (rs_nested_2.next())
                        {
                            Integer valueAnalisi = new Integer(rs_nested_2.getInt("IDANALISI"));
                            if(valueAnalisi.intValue() >= 0)
                                relazioni_elemento.V_LISTA_ANALISI.addElement(valueAnalisi);
                        }
                    }
                    try
                    {
                        SQLStatement_2.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("(ADAMS_TAB_CONFIG-7) SQL Operation " + exc.toString());
                    }
                    
                    
               //// ELEMENTI_RESTRIZIONE
                    String sel_ConfigNtm3 = "select ";
                    sel_ConfigNtm3 = sel_ConfigNtm3+ " ELEMENTI_RESTRIZIONE ";
                    sel_ConfigNtm3 = sel_ConfigNtm3+ " from tab_list_restriction ";
                    sel_ConfigNtm3 = sel_ConfigNtm3+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento;
                    sel_ConfigNtm3 = sel_ConfigNtm3+ " and RELATION_NAME ='"+relazioni_elemento.RELATION_NAME+"'";
                    
                    //System.out.println("sel ELEMENTI_RESTRIZIONE ----> "+sel_ConfigNtm3);
                    Statement SQLStatement_3 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    java.sql.ResultSet rs_nested_3  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm3,SQLStatement_3);
                    
                    if(rs_nested_3 != null)
                    {
                        while (rs_nested_3.next())
                            relazioni_elemento.V_RESTRIZIONI.addElement(new Integer(rs_nested_3.getInt("ELEMENTI_RESTRIZIONE")));
                    }
                    
                    try
                    {
                        SQLStatement_3.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("(ADAMS_TAB_CONFIG-8) SQL Operation " + exc.toString());
                    }
                    
                    
               //// ELEMENTI_RESTRIZIONE_OBBL
                    String sel_ConfigNtm4 = "select ";
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " ELEMENTI_RESTRIZIONE_OBBL ";
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " from tab_list_restriction_ob";
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento;
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " and RELATION_NAME ='"+relazioni_elemento.RELATION_NAME+"'";
                    
                    //System.out.println("sel ELEMENTI_RESTRIZIONE_OBBL  ----> "+sel_ConfigNtm4);
                    Statement SQLStatement_4 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    java.sql.ResultSet rs_nested_4  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm4,SQLStatement_4);
                    if(rs_nested_4 != null)
                    {
                        while (rs_nested_4.next())
                            relazioni_elemento.V_RESTRIZIONI_OBBL.addElement(new Integer(rs_nested_4.getInt("ELEMENTI_RESTRIZIONE_OBBL")));
                    }
                    try
                    {
                        SQLStatement_4.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("(ADAMS_TAB_CONFIG-9) SQL Operation " + exc.toString());
                    }
                    
                    V_LISTA_RELAZIONI.addElement(relazioni_elemento);
                }
                
                if(ADAMS_GlobalParam.DEBUG)
        	{
	                System.out.println();
	                System.out.println("V_LISTA_RELAZIONI.size() -> "+V_LISTA_RELAZIONI.size());
	                for(int i=0; i<V_LISTA_RELAZIONI.size(); i++)
	                {
	                    ADAMS_TAB_RELAZIONI_ELEMENTO rel_elemento = (ADAMS_TAB_RELAZIONI_ELEMENTO)V_LISTA_RELAZIONI.elementAt(i);
	                    
	                    System.out.println("RELATION_NAME ->"+rel_elemento.RELATION_NAME);
	                    System.out.println("DIREZIONE   ->"+rel_elemento.DIREZIONE);
	                    System.out.println("HEXOUTPUT   ->"+rel_elemento.HEXOUTPUT);
	                    System.out.println("NETWORK     ->"+rel_elemento.NETWORK);
	                    System.out.println("GHOST       ->"+rel_elemento.GHOST); 
	                    
	                    
	                    for(int x=0;x<rel_elemento.V_LISTA_ANALISI.size(); x++)
	                        System.out.println("index="+x+" Analisi     ->"+rel_elemento.V_LISTA_ANALISI.elementAt(x));
	                    for(int x=0;x<rel_elemento.V_RESTRIZIONI.size(); x++)
	                        System.out.println("index="+x+" Restrizioni ->"+rel_elemento.V_RESTRIZIONI.elementAt(x));
	                    for(int x=0;x<rel_elemento.V_RESTRIZIONI_OBBL.size(); x++)
	                        System.out.println("index="+x+" Restrizioni_obbl ->"+rel_elemento.V_RESTRIZIONI_OBBL.elementAt(x));
	                }
	        }
                
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_CONFIG-6) SQL Operation " + exc.toString());
        }
    }
   

    /*public boolean add_LISTA_SCRIPTS(int SCRIPT,int SCRIPT_TYPE)
    {
        int SIZE = V_LISTA_SCRIPTS.size();
        
        if(SIZE == 0)
        {
            ADAMS_TAB_SCRIPTS_LISTA appo1=new ADAMS_TAB_SCRIPTS_LISTA(SCRIPT,SCRIPT_TYPE);
            V_LISTA_SCRIPTS.addElement(appo1); // max length 30
            return true;
        }
        
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_SCRIPTS_LISTA appo2=(ADAMS_TAB_SCRIPTS_LISTA)(V_LISTA_SCRIPTS.elementAt(i));
            int idScript=appo2.get_SCRIPT();
            
            
            if(idScript==SCRIPT )
            {
                //System.out.println("SCRIPTS giï¿½ esistente");
                return false;
            }                
        }
        ADAMS_TAB_SCRIPTS_LISTA appo3=new ADAMS_TAB_SCRIPTS_LISTA(SCRIPT,SCRIPT_TYPE);
        V_LISTA_SCRIPTS.addElement(appo3);
        return true;        
    }
    
    public boolean delete_LISTA_SCRIPTS(int SCRIPT_ID,int SCRIPT_TYPE)
    {
        
        int SIZE = V_LISTA_SCRIPTS.size();
        if(SIZE == 0)
        {
            return false;
        }
        
        int pos=-1;
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_SCRIPTS_LISTA appo = (ADAMS_TAB_SCRIPTS_LISTA)(V_LISTA_SCRIPTS.elementAt(i));
            int elem=appo.get_SCRIPT();
            
            if(elem == SCRIPT_ID )
            {
                //System.out.println("SCRIPTS giï¿½ esistente alla pos="+i);
                pos=i;
                break;
            }                
        }
        boolean flag=false;
        if(pos!=-1)
        {
            V_LISTA_SCRIPTS.removeElementAt(pos);
            flag=true; 
        }else
        {
            flag=false;      
        }
        
        return flag;

    }
    
    public Vector get_LISTA_SCRIPTS()
    {
        return V_LISTA_SCRIPTS;
    }*/
    
    
 // Campi della Tabellatab_config
    public String      TIPO_DI_CONFIGURAZIONE;           
    public int         POSIZIONE_ELEMENTO;
    public int         POSIZIONE_CAMPO_DR;                      
    public int         TIPO_CAMPO;                               
    public int         SIZE_CAMPO_DR;
    public char        FLAG_ARRAY;                                
    public int         NUMERO_ELEM_ARRAY;                        
    public String      LABEL_DR_NORMALIZZ;
    public int         TIPO_OGGETTO_GUI;
    public String      ICONA; 
    public String      RANGE_VAL;
    public Vector      V_LISTA_VALORI;
    public int         VALORE_DEFAULT;
    public char        RAPPRESENTAZ_ESADECIMALE;
    public int         PRIORITA;
    public Vector      V_AGGREGAZ_ELEM_RESTRIZ; 
    public int         OPERATORI_RESTRIZIONE;
    public int         LUNG_ELEMENTO_CHIAVE;
    public Vector      V_LISTA_VALORI_SOSTITUZ;
    public String      TAG;
    public String      DESCRIPTION;
    public Vector      V_LISTA_SCRIPTS;
    public String      PLUGIN;
    public String      PLUGIN_PATH;
    public String      PLUGIN_GUI;
    public char        FLAG_ABILITAZIONE_KEY;
    public char        FLAG_CONTATORE;
    public char        FLAG_RESTRIZIONE; 
    public char        FLAG_COMBINAZ_ANALISI;
    public Vector      V_LISTA_RELAZIONI;
    public Vector      V_LISTA_EXCEPTION;
    public char        DEFAULT_RESTRICTION;
    public char        FFENABLED;
    public char        ALWAYS_SHOW_REPORT;
    public int         LINK_TE;
    public boolean        SE_INDICE;
    public int         VALORE_AGGREGAZIONE_DEF;
    public int         VALORE_AGGREGAZIONE;
    public int         LENGTH_VALORE_START;
    public String      SUFF_VALORE_INDICE;
    public String      DATA_INIZIO_INDICE;
    public String      DATA_FINE_INDICE;
    
  
}

