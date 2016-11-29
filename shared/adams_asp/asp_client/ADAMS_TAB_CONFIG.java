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
    
//********* OPERAZIONI DB INPUTDATA **********//
    public int insert_InputData()
    {
        String itemName_InputData  =("TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,POSIZIONE_CAMPO_DR,TIPO_CAMPO,SIZE_CAMPO_DR,FLAG_ARRAY,NUMERO_ELEM_ARRAY,LABEL_DR_NORMALIZZ,TAG ");
                                        //"SE_INDICE,VALORE_AGGREGAZIONE_DEF,VALORE_AGGREGAZIONE,LENGTH_VALORE_START,SUFF_VALORE_INDICE,DATA_INIZIO_INDICE,DATA_FINE_INDICE "); // Campi identificativi per funzionalitÃ¨ l'indice   
        
        String itemValue_InputData = "'"+TIPO_DI_CONFIGURAZIONE+"',";
                itemValue_InputData += "POSIZIONE_ELEMENTO_seq.nextVal,";
                itemValue_InputData += POSIZIONE_CAMPO_DR+",";
                itemValue_InputData += TIPO_CAMPO+",";
                itemValue_InputData += SIZE_CAMPO_DR+",";
                itemValue_InputData += "'"+FLAG_ARRAY+"',";
                itemValue_InputData += NUMERO_ELEM_ARRAY+",";
                itemValue_InputData += "'"+LABEL_DR_NORMALIZZ+"',";
                itemValue_InputData += "'"+LABEL_DR_NORMALIZZ+"' "; //Nel Campo TAG copio LABEL_DR_NORMALIZZ
               
        String str_Insert = ("insert intotab_config ("+itemName_InputData+") values ( "+itemValue_InputData+" )");
       // System.out.println("Insert_InputData --> "+str_Insert);
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);
        if(Answer_Ins == 1)
            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
        return Answer_Ins;
    }
    
    public int update_InputData()
    {
        //System.out.println("TIPO_DI_CONFIGURAZIONE "+TIPO_DI_CONFIGURAZIONE);
        
        char is_indice = 'N';
        if(SE_INDICE == true)
            is_indice = 'Y';
        
        String str_update = "update tab_config set POSIZIONE_CAMPO_DR="+POSIZIONE_CAMPO_DR+","+
                            "TIPO_CAMPO="+TIPO_CAMPO+","+
                            "SIZE_CAMPO_DR="+SIZE_CAMPO_DR+","+
                            "FLAG_ARRAY='"+FLAG_ARRAY+"',"+
                            "NUMERO_ELEM_ARRAY="+NUMERO_ELEM_ARRAY+","+
                            "LABEL_DR_NORMALIZZ='"+LABEL_DR_NORMALIZZ+"',"+
                            "TAG='"+LABEL_DR_NORMALIZZ+"',"+   //Nel Campo TAG copio LABEL_DR_NORMALIZZ
                            "SE_INDICE='"+is_indice+"',"+                 
                            "VALORE_AGGREGAZIONE_DEF="+VALORE_AGGREGAZIONE_DEF+","+
                            "VALORE_AGGREGAZIONE="+VALORE_AGGREGAZIONE+","+
                            "LENGTH_VALORE_START="+LENGTH_VALORE_START+","+
                            "SUFF_VALORE_INDICE='"+SUFF_VALORE_INDICE+"',"+
                            "DATA_INIZIO_INDICE= to_date('"+DATA_INIZIO_INDICE+"','YYYYMMDD'),"+
                            "DATA_FINE_INDICE= to_date('"+DATA_FINE_INDICE+"','YYYYMMDD') "+
                            "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                            "POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
        
    //System.out.println("Answer_update "+str_update);
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
        
        //System.out.println("esito update= "+Answer_update+" "+str_update);
        
        if(Answer_update > 0)
            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
        return Answer_update;
    }
    
    public int delete_ROW()
    {        
        System.out.println("delete_ROW 1 -- cancello la righa con POSIZIONE_ELEMENTO ="+POSIZIONE_ELEMENTO); 
        String str_delete = "delete from tab_config where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO+" and POSIZIONE_CAMPO_DR="+POSIZIONE_CAMPO_DR;
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
        
        System.out.println("delete_ROW 2 -- resetto nella tabellatab_gui_events le righe con IDTRIGGERRESTRICTION ="+POSIZIONE_ELEMENTO);
        String str_update_GE =  "update tab_gui_events set IDTRIGGERRESTRICTION = -1 "+
                                "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                                "IDTRIGGERRESTRICTION="+POSIZIONE_ELEMENTO;

        if (Answer_del >= 0)
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_update_GE);
        
        //System.out.println("esito delete= "+Answer_del+" "+str_delete);
                
        try
        {         
            if(Answer_del >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_CONFIG.java 1");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
             catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_CONFIG.java 1a");}
        }
        
        
        { // Delete tab_value_type
	        str_delete = "delete from tab_value_type where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_value_type (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_value_type (2).");
	            }
	        }
	}
	
	{ // Delete tab_aggregate_restrictions
	        str_delete = "delete from tab_aggregate_restrictions where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_aggregate_restrictions (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_aggregate_restrictions (2)");
	            }
	        }
	}
	
	{ // Delete tab_subst_values
	        str_delete = "delete from tab_subst_values where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_subst_values (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_subst_values (2)");
	            }
	        }
	}
	
	{ // Delete tab_list_script
	        str_delete = "delete from tab_list_script where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_list_script (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_list_script (2)");
	            }
	        }
	}
	
	{ // Delete tab_list_exception
	        str_delete = "delete from tab_list_exception where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_list_exception (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_list_exception (2)");
	            }
	        }
	}
        
        { // Delete tab_list_relation
	        str_delete = "delete from tab_list_relation where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_list_relation (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_list_relation (2)");
	            }
	        }
	}

        { // Delete tab_list_restriction
	        str_delete = "delete from tab_list_restriction where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_list_restriction (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_list_restriction (2)");
	            }
	        }
	}
	
	{ // Delete tab_list_restriction_ob
	        str_delete = "delete from tab_list_restriction_ob where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_list_restriction_ob (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_list_restriction_ob (2)");
	            }
	        }
	}
	
	{ // Delete tab_list_analysis
	        str_delete = "delete from tab_list_analysis where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	            //System.out.println("setAutoCommit(false)");
	        }catch(java.sql.SQLException exc){}
	        
	        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
	        
	        try
	        {         
	            if(Answer_del >= 0)
	            {                
	                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                System.out.println("Commit OK");
	            }
	            else
	            {
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                System.out.println("rollback()");
	            }
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }
	        catch(java.sql.SQLException exc)
	        {
	            System.out.println("Errore delete tab_list_analysis (1).");
	            try
	            { 
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }catch(java.sql.SQLException exc1)
	            {
	            	System.out.println("Errore tab_list_analysis (2)");
	            }
	        }
	}
        
        return Answer_del;
    }    
//*******************************************************// 
    
//********* OPERAZIONI DB TRAFFIC ELEMENT **********//
    public int insert_TRAFFIC_ELEMENT(boolean insert_TEGhost)
    {   
        if(insert_TEGhost == true)
        {
            POSIZIONE_ELEMENTO = ADAMS_GlobalParam.TE_GHOST_POSIZIONE_ELEMENTO;
            String ctrl_Ghost = ADAMS_GlobalParam.connect_Oracle.Query_s("select POSIZIONE_ELEMENTO from tab_config where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO); 
            //System.out.println("ctrl_Ghost "+ctrl_Ghost);
            if(ctrl_Ghost.length() != 0)
                if(new Integer(ctrl_Ghost).intValue() == ADAMS_GlobalParam.TE_GHOST_POSIZIONE_ELEMENTO)
                    return 1;
        }
        else
        {
            POSIZIONE_ELEMENTO = ADAMS_GlobalParam.connect_Oracle.Query_int("select POSIZIONE_ELEMENTO_seq.nextVal from dual");
            //System.out.println("Insert POSIZIONE_ELEMENTO.nextVal "+POSIZIONE_ELEMENTO);
            if(POSIZIONE_ELEMENTO < 0)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed [Error: POSIZIONE_ELEMENTO_seq.nextVal].","ERROR",1);
                return -1;
            }
        }
        
        String itemName_TE  =("TIPO_DI_CONFIGURAZIONE,"+
                                "POSIZIONE_ELEMENTO,"+
                                "POSIZIONE_CAMPO_DR,"+
                                "TIPO_CAMPO,"+
                                "SIZE_CAMPO_DR,"+
                                "FLAG_ARRAY,"+
                                "NUMERO_ELEM_ARRAY,"+
                                "LABEL_DR_NORMALIZZ,"+    
                                "TAG,"+
                                "DESCRIPTION,"+
                                "RANGE_VAL,"+
                                "VALORE_DEFAULT,"+
                                "ICONA,"+
                                "PRIORITA,"+
                                "LUNG_ELEMENTO_CHIAVE,"+
                                "RAPPRESENTAZ_ESADECIMALE,"+
                                "TIPO_OGGETTO_GUI,"+
                                "OPERATORI_RESTRIZIONE,"+
                                "PLUGIN,"+
                                "PLUGIN_PATH,"+
                                "PLUGIN_GUI,"+
                                "DEFAULT_RESTRICTION,"+
                                "FFENABLED,"+
                                "ALWAYS_SHOW_REPORT,"+
                                "LINK_TE"); 
                
        
        String itemValue_TE = "'"+TIPO_DI_CONFIGURAZIONE+"',";
                itemValue_TE += POSIZIONE_ELEMENTO+",";
                itemValue_TE += "-1,"; //ATTENZIONE NEW TRAFFIC ELEMENT -- E' identificato SOLO come un TRAFFIC ELEMENT POSIZIONE_CAMPO_DR -1
                itemValue_TE += "0,";
                itemValue_TE += "0,";
                itemValue_TE += "'N',";
                itemValue_TE += "0,";
                itemValue_TE += "'NO DR',";
                itemValue_TE += "'"+TAG+"',";
                itemValue_TE += "'"+DESCRIPTION+"',";
                itemValue_TE += "'"+RANGE_VAL+"',";
                itemValue_TE += VALORE_DEFAULT+",";
                itemValue_TE += "'"+ICONA+"',";
                itemValue_TE += PRIORITA+",";
                itemValue_TE += LUNG_ELEMENTO_CHIAVE+",";
                itemValue_TE += "'"+RAPPRESENTAZ_ESADECIMALE+"',";
                itemValue_TE += TIPO_OGGETTO_GUI+",";
                itemValue_TE += OPERATORI_RESTRIZIONE+",";
                itemValue_TE += "'"+PLUGIN+"',";
                itemValue_TE += "'"+PLUGIN_PATH+"',";
                itemValue_TE += "'"+PLUGIN_GUI+"',";            
                itemValue_TE += "'"+DEFAULT_RESTRICTION+"',";
                itemValue_TE += "'"+FFENABLED+"',";                
                itemValue_TE += "'"+ALWAYS_SHOW_REPORT+"',";
                itemValue_TE += LINK_TE;
                

        String str_Insert_TE = ("insert intotab_config ("+itemName_TE+") values ( "+itemValue_TE+" )");
        //System.out.println("Insert_InputData --> "+str_Insert_TE);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_TE);
        
        if(insert_TEGhost == false)
        {
            //Nested LISTA_VALORI - TAB_VALORI_ELEMENTO
            if (Answer_Ins >= 0)
                Answer_Ins = update_LISTA_VALORI();

            //Nested AGGREGAZ_ELEM_RESTRIZ  - TAB_AGGREGAZ_ELEMENTO            
            if (Answer_Ins >= 0)
                Answer_Ins = update_AGGREGAZ_ELEM_RESTRIZ();

            //Nested LISTA_VALORI_SOSTITUZ  - TAB_VALORI_SOSTITUZIONE
            if (Answer_Ins >= 0)
                Answer_Ins = update_LISTA_VALORI_SOSTITUZ();

            //Nested LISTA_LISTA_EXCEPTION  - TAB_EXCEPTION
            if (Answer_Ins >= 0)
                Answer_Ins = update_LISTA_EXCEPTION();
        }
        //else 
            //System.out.println("E' una Ghost non faccio update delle nested");
                
        try
        {         
            if(Answer_Ins >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_CONFIG.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
             catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_CONFIG.java 2a");}
        }
        
        
        return Answer_Ins;
    }
    
    public int update_TRAFFIC_ELEMENT()
    {        
        String str_update = "update tab_config set TAG='"+TAG+"',"+
                            "DESCRIPTION='"+DESCRIPTION+"',"+
                            "RANGE_VAL='"+RANGE_VAL+"',"+
                            "VALORE_DEFAULT="+VALORE_DEFAULT+","+
                            "ICONA='"+ICONA+"',"+
                            "PRIORITA="+PRIORITA+","+
                            "LUNG_ELEMENTO_CHIAVE="+LUNG_ELEMENTO_CHIAVE+","+
                            "RAPPRESENTAZ_ESADECIMALE='"+RAPPRESENTAZ_ESADECIMALE+"',"+
                            "TIPO_OGGETTO_GUI="+TIPO_OGGETTO_GUI+","+
                            "OPERATORI_RESTRIZIONE="+OPERATORI_RESTRIZIONE+","+
                            "PLUGIN='"+PLUGIN+"',"+
                            "PLUGIN_PATH='"+PLUGIN_PATH+"',"+
                            "PLUGIN_GUI='"+PLUGIN_GUI+"',"+
                            "DEFAULT_RESTRICTION='"+DEFAULT_RESTRICTION+"',"+
                            "FFENABLED='"+FFENABLED+"',"+
                            "ALWAYS_SHOW_REPORT='"+ALWAYS_SHOW_REPORT+"',"+
                            "LINK_TE="+LINK_TE+" "+
                            "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                            "POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
        
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);

        //Nested LISTA_VALORI TAB_VALORI_ELEMENTO
        if (Answer_update >= 0) 
            Answer_update = update_LISTA_VALORI();
        //Nested V_AGGREGAZ_ELEM_RESTRIZ - TAB_AGGREGAZ_ELEMENTO
        if (Answer_update >= 0)
            Answer_update = update_AGGREGAZ_ELEM_RESTRIZ();
        //Nested LISTA_VALORI_SOSTITUZ  - TAB_VALORI_SOSTITUZIONE
        if (Answer_update >= 0)
            Answer_update = update_LISTA_VALORI_SOSTITUZ();
        //Nested LISTA_LISTA_EXCEPTION  - TAB_EXCEPTION
        if (Answer_update >= 0)
            Answer_update = update_LISTA_EXCEPTION();
        //Nested LISTA_RELAZIONI  - TAB_RELAZIONI_ELEMENTO
        if (Answer_update >= 0)
            Answer_update = update_LISTA_RELAZIONI(false);
        
        try
        {         
            if(Answer_update >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_CONFIG.java *");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
             catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_CONFIG.java **");}
        }
        
        return Answer_update;
    }
    
    
    public void read_LISTA_VALORI()
    {      
        V_LISTA_VALORI.removeAllElements();
        
        boolean DEBUG = true;
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
        
        if(DEBUG)
            System.out.println("select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    if(DEBUG)
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
    
    private int update_LISTA_VALORI()
    {
        System.out.println("update_LISTA_VALORI");
        
	int rowCount 		= V_LISTA_VALORI.size();
        String tableConfig      = "tab_value_type";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
	String strSelect	= "";
	int Answer_update_VE	= 1;
	String strDelete	= "";
	
	{ // DELETE before INSERT (TAB_LISTA_VALORI)
		strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_LISTA_VALORI: "+exc);
	        }
	        
	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
	       	
	       	try
	        {
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("rollback()");
			}
	        }
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_LISTA_VALORI::rollback(): "+exc);
	        }
	           
	       	try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_LISTA_VALORI: "+exc);	
	        }
	}
	if(rowCount>0)
	{
	        strSelect="INSERT ALL ";
	        for(int i=0;i<rowCount;i++)
	        {
			strSelect=strSelect+" into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,COD_VALORE,DESC_VALORE) values ";
			ADAMS_TAB_VALORI_ELEMENTO Val_Elem = (ADAMS_TAB_VALORI_ELEMENTO)(V_LISTA_VALORI.elementAt(i));
			
			int idNew       = Val_Elem.get_COD_VALORE();
			String descNew  = Val_Elem.get_DESC_VALORE();
			
			strSelect=strSelect+" ('"+typeConfig+"',"+posElemento+","+idNew+",'"+descNew+"') ";
	        }
	        strSelect=strSelect+" SELECT * from DUAL";
	        
	        System.out.println("strInsert  ="+strSelect);
	        Answer_update_VE = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect); 
	        System.out.println("esito update LISTA_VALORI = "+Answer_update_VE);
	        
        }
        else
        {
        	System.out.println("la V_LISTA_VALORI Ã¨ vuota!!!");	
        }
        
        return Answer_update_VE;
    }
    
    
    public void read_AGGREGAZ_ELEM_RESTRIZ() 
    {      
        V_AGGREGAZ_ELEM_RESTRIZ.removeAllElements();
        
        boolean DEBUG = true;
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
        
        if(DEBUG)
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
    
    public int update_AGGREGAZ_ELEM_RESTRIZ() 
    {
        System.out.println("update_AGGREGAZ_ELEM_RESTRIZ POS_ELE ="+POSIZIONE_ELEMENTO);
	int rowCount 		= V_AGGREGAZ_ELEM_RESTRIZ.size();
        String tableConfig      = "tab_aggregate_restrictions";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
	String strSelect	= "";
	int Answer_update_VE	= 1;
        String strDelete	= "";
	
	strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_AGGREGAZ_ELEM_RESTRIZ: "+exc);	
        }
        
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
       	
       	try
       	{
	       	if(Answer_del >= 0)
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
		}
		else
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
			System.out.println("rollback()");
		}
	}
        catch(java.sql.SQLException exc)
        {
        	System.out.println("update_AGGREGAZ_ELEM_RESTRIZ::rollback(): "+exc);
        }
            
       	try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_AGGREGAZ_ELEM_RESTRIZ: "+exc);	
        }
        if(rowCount>0)
	{
	        strSelect="INSERT ALL ";
	        for(int i=0;i<rowCount;i++)
	        {
	        	strSelect=strSelect+"into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,AGGREG_RESTRICTION) values ";
			int ID_AggRest = ((Integer)V_AGGREGAZ_ELEM_RESTRIZ.elementAt(i)).intValue();
			        
			strSelect=strSelect+"('"+typeConfig+"',"+posElemento+","+ID_AggRest+") ";
	        }
	        strSelect=strSelect+" SELECT * FROM DUAL";
	        
	        System.out.println("strInsert AGGREGAZ_ELEM_RESTRIZ ="+strSelect);
	        Answer_update_VE = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect); 
	        //System.out.println("esito update AGGREGAZ_ELEM_RESTRIZ = "+Answer_update_VE);	    
        }
        else
        {
        	System.out.println("la V_AGGREGAZ_ELEM_RESTRIZ Ã¨ vuota!!!");	
        }
        return Answer_update_VE;
    }

    //*****************
    
    public void read_LISTA_VALORI_SOSTITUZ() 
    {      
        V_LISTA_VALORI_SOSTITUZ.removeAllElements();
        boolean DEBUG = true;
        
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
        
        if(DEBUG)
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
    
    private int update_LISTA_VALORI_SOSTITUZ()
    {
        System.out.println("update_LISTA_VALORI_SOSTITUZ");
        
	int rowCount 		= V_LISTA_VALORI_SOSTITUZ.size();
        String tableConfig      = "tab_subst_values";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
	String strSelect	= "";
	int Answer_update_VE	= 1;
        String strDelete	= "";
	
	strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_VALORI_SOSTITUZ: "+exc);	
        }
        
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
       	
       	try
       	{
	       	if(Answer_del >= 0)
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
		}
		else
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
			System.out.println("rollback()");
		}
	}
        catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_VALORI_SOSTITUZ::rollback(): "+exc);
        }
            
       	try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_VALORI_SOSTITUZ: "+exc);	
        }
        if(rowCount>0)
	{
	        strSelect="INSERT ALL ";
	        
	        for(int i=0;i<rowCount;i++)
	        {
			strSelect=strSelect+"into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,VALORI_SOSTITUZIONE) values ";
			
			String ID_Sost = ((String)V_LISTA_VALORI_SOSTITUZ.elementAt(i));
			
			strSelect=strSelect+"('"+typeConfig+"',"+posElemento+","+ID_Sost+") ";    
	        }
	        strSelect=strSelect+" SELECT * FROM DUAL";
	        
	        System.out.println("update_LISTA_VALORI_SOSTITUZ strInsert="+strSelect);
	        Answer_update_VE = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect); 
	        
	        //System.out.println("esito update LISTA_VALORI_SOSTITUZ = "+Answer_update_VE);
	}
	else
	{
		System.out.println("la V_LISTA_VALORI_SOSTITUZ Ã¨ vuota!!!");	
	}        
        return Answer_update_VE;
    }
    
    
    public void read_LISTA_SCRIPTS()
    {
        V_LISTA_SCRIPTS.removeAllElements();
        
        boolean DEBUG = true;
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
        
       if(DEBUG)
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
                    if(DEBUG)
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
    
    public int update_LISTA_SCRIPTS()
    {
    	System.out.println("update_LISTA_SCRIPTS");
    	
        boolean DEBUG		= true;
        int rowCount 		= V_LISTA_SCRIPTS.size();
        String tableConfig      = "tab_list_script";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
	String strSelect	= "";
	int Answer_update_VE	= 1;
	String strDelete	= "";
	
	strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_SCRIPTS: "+exc);	
        }
        
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
       	
       	try
       	{
	       	if(Answer_del >= 0)
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
		}
		else
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
			System.out.println("rollback()");
		}
	}
        catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_SCRIPTS::rollback(): "+exc);
        }
            
       	try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_SCRIPTS: "+exc);	
        }
	if(rowCount>0)
	{
	        strSelect="INSERT ALL ";
	
	        for(int i=0;i<rowCount;i++)
	        {
			strSelect=strSelect+"into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,SCRIPT,SCRIPT_TYPE) values ";
			
			ADAMS_TAB_SCRIPTS_LISTA script=(ADAMS_TAB_SCRIPTS_LISTA)(V_LISTA_SCRIPTS.elementAt(i));
			int s               = script.get_SCRIPT();
			int s_type          = script.get_SCRIPT_TYPE();
			
			strSelect=strSelect+"('"+typeConfig+"',"+posElemento+","+s+","+s_type+") ";                      
	        }
	        strSelect=strSelect+" SELECT * FROM DUAL";
	        
	        if(DEBUG)
	            System.out.println("strSelect update_LISTA_SCRIPTS = "+strSelect);
	        Answer_update_VE = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect); 
	        //System.out.println("esito update AGGREGAZ_ELEM_RESTRIZ = "+Answer_update_VE);
        }
        else
        {
        	System.out.println("la V_LISTA_SCRIPTS Ã¨ vuota!!!");	
        }
        return Answer_update_VE;
    }
   
    
    public void read_LISTA_EXCEPTION() 
    {      
        V_LISTA_EXCEPTION.removeAllElements();
        
        boolean DEBUG = false;
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
        
        if(DEBUG)
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
    
    public int update_LISTA_EXCEPTION()
    {
        System.out.println("update_LISTA_EXCEPTION");
	int rowCount 		= V_LISTA_EXCEPTION.size();
        String tableConfig      = "tab_list_exception";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        String strSelect	= "";
	int Answer_update_VE	= 1;
	String strDelete	= "";
	
	strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_EXCEPTION: "+exc);	
        }
        
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
       	
       	try
       	{
	       	if(Answer_del >= 0)
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
		}
		else
		{
			ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
			System.out.println("rollback()");
		}
	}
        catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_EXCEPTION::rollback(): "+exc);
        }
            
       	try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }catch(java.sql.SQLException exc)
        {
        	System.out.println("update_LISTA_EXCEPTION: "+exc);	
        }
	if(rowCount>0)
	{
	        strSelect="INSERT ALL ";
	        
	        for(int i=0;i<rowCount;i++)
	        {
	        	strSelect=strSelect+"into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,IDEXCEPTION) values ";
	            	
	            	int IDException = ((Integer)V_LISTA_EXCEPTION.elementAt(i)).intValue();
	                
	                strSelect=strSelect+"('"+typeConfig+"',"+posElemento+","+IDException+") ";    
	        }
	        strSelect=strSelect+" SELECT * FROM DUAL";
	        
	        System.out.println("strSelect AGGREGAZ_ELEM_RESTRIZ ="+strSelect);
	        Answer_update_VE = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect); 
	        //System.out.println("esito update AGGREGAZ_ELEM_RESTRIZ = "+Answer_update_VE);
        }
        else
        {
        	System.out.println("la V_LISTA_EXCEPTION Ã¨ vuota!!!");	
        }
        return Answer_update_VE;
    }
    
    //********************
 
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
          
        //System.out.println("read_LISTA_RELAZIONI ----> "+sel_ConfigNtm); //nello
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
   
    public int update_LISTA_RELAZIONI(boolean rec_time)
    {
        System.out.println("update_LISTA_RELAZIONI");
        
	int rowCount 		= V_LISTA_RELAZIONI.size();
	String linkTable0  	= "tab_list_relation";
        String linkTable1  	= "tab_list_analysis";
        String linkTable2  	= "tab_list_restriction";
        String linkTable3  	= "tab_list_restriction_ob";
        String typeConfig       = TIPO_DI_CONFIGURAZIONE;
        int posElemento         = POSIZIONE_ELEMENTO;
        String strSelect	= "";
        String strSelect0	= "";
        int Answer_update	= 1;
        String strDelete	= "";
	
	{ // DELETE tab_list_relation
		strDelete="delete from "+linkTable0+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-1) update_LISTA_RELAZIONI(delete("+linkTable0+")): "+exc);	
	        }
	        
	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
	       	
	       	try
	       	{
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("(EXC-2) rollback(delete("+linkTable0+"))");
			}
		}
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-3) update_LISTA_RELAZIONI::rollback(delete("+linkTable0+")): "+exc);
	        }
	            
	       	try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-4) update_LISTA_RELAZIONI(delete("+linkTable0+")): "+exc);	
	        }
	}
	
	{ // DELETE TAB_LISTA_ANALISI
		strDelete="delete from "+linkTable1+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-1) update_LISTA_RELAZIONI(delete("+linkTable1+")): "+exc);	
	        }
	        
	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
	       	
	       	try
	       	{
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("(EXC-2) rollback(delete("+linkTable1+"))");
			}
		}
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-3) update_LISTA_RELAZIONI::rollback(delete("+linkTable1+")): "+exc);
	        }
	            
	       	try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-4) update_LISTA_RELAZIONI(delete("+linkTable1+")): "+exc);	
	        }
	}
	
	{ // DELETE TAB_RESTRIZIONI
		strDelete="delete from "+linkTable2+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-1) update_LISTA_RELAZIONI(delete("+linkTable2+")): "+exc);	
	        }
	        
	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
	       	
	       	try
	       	{
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("(EXC-2) rollback(delete("+linkTable2+"))");
			}
		}
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-3) update_LISTA_RELAZIONI::rollback(delete("+linkTable2+")): "+exc);
	        }
	            
	       	try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-4) update_LISTA_RELAZIONI(delete("+linkTable2+")): "+exc);	
	        }
	}
	
	{ // DELETE TAB_RESTRIZIONI_OBBL
		strDelete="delete from "+linkTable3+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and POSIZIONE_ELEMENTO="+POSIZIONE_ELEMENTO;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-1) update_LISTA_RELAZIONI(delete("+linkTable3+")): "+exc);	
	        }
	        
	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
	       	
	       	try
	       	{
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("(EXC-2) rollback(delete("+linkTable3+"))");
			}
		}
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-3) update_LISTA_RELAZIONI::rollback(delete("+linkTable3+")): "+exc);
	        }
	            
	       	try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("(EXC-4) update_LISTA_RELAZIONI(delete("+linkTable3+")): "+exc);	
	        }
	}
	  
        if(rowCount>0)
        {
	        strSelect="INSERT ALL ";
	        strSelect0="INSERT ALL ";
	        
	        for(int i=0;i<rowCount;i++)
	        {
			ADAMS_TAB_RELAZIONI_ELEMENTO appoRELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(V_LISTA_RELAZIONI.elementAt(i));

			// *** NAME_RELATION
			String nameRelation 	= appoRELAZIONI_ELEMENTO.RELATION_NAME;            
			int iDirezione		= appoRELAZIONI_ELEMENTO.DIREZIONE;
			int iHex		= appoRELAZIONI_ELEMENTO.HEXOUTPUT;
			int iNet		= appoRELAZIONI_ELEMENTO.NETWORK;
			int iGhost		= appoRELAZIONI_ELEMENTO.GHOST;    
			
			// *** tab_list_relation
			{
				
				
				strSelect0=strSelect0+" INSERT INTO "+linkTable0+"(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,RELATION_NAME,DIREZIONE,HEXOUTPUT,NETWORK,GHOST) values ";
				strSelect0=strSelect0+" ('"+typeConfig+"',"+posElemento+",'"+nameRelation+"',"+iDirezione+","+iHex+","+iNet+","+iGhost+") ";        
			}
			   
			// *** TAB_LISTA_ANALISI
			{
				int row_An = appoRELAZIONI_ELEMENTO.V_LISTA_ANALISI.size();
				
				if(row_An == 0)
				{
					// DO NOTHING
				}    
				else
				{
					for(int x=0; x<row_An; x++)
					{
						strSelect=strSelect+" into "+ linkTable1 + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,RELATION_NAME,IDANALISI) values ";
					    	int ID_An = ((Integer)appoRELAZIONI_ELEMENTO.V_LISTA_ANALISI.elementAt(x)).intValue();	
					    	strSelect=strSelect+" ('"+typeConfig+"',"+posElemento+",'"+nameRelation+"',"+ID_An+") ";    
					}
				}            
			}
			    
			// *** TAB_RESTRIZIONI
			{
				int row_Rest = appoRELAZIONI_ELEMENTO.V_RESTRIZIONI.size();
				
				if(row_Rest == 0)
				{
					// DO NOTHING
				} 
				else
				{       
					for(int x=0; x<row_Rest; x++)
					{
						strSelect=strSelect+" into "+ linkTable2 + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,RELATION_NAME,ELEMENTI_RESTRIZIONE) values ";
						int ID_Rest = ((Integer)appoRELAZIONI_ELEMENTO.V_RESTRIZIONI.elementAt(x)).intValue();
						strSelect=strSelect+" ('"+typeConfig+"',"+posElemento+",'"+nameRelation+"',"+ID_Rest+") "; 
					}
				}            
			}    
			
			// *** TAB_RESTRIZIONI_OBBL
			{
				int row_Rest_obb = appoRELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.size();
				
				if(row_Rest_obb == 0)
				{
					// DO NOTHING
				} 
				else
				{
					for(int x=0; x<row_Rest_obb; x++)
					{
						strSelect=strSelect+" into "+ linkTable2 + "(TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,RELATION_NAME,ELEMENTI_RESTRIZIONE_OBBL) values ";
					    	int ID_Rest_obb = ((Integer)appoRELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.elementAt(x)).intValue();
						strSelect=strSelect+" ('"+typeConfig+"',"+posElemento+",'"+nameRelation+"',"+ID_Rest_obb+") ";
					}
				}            
			}	   	            
	        }
	        
	        strSelect=strSelect+" SELECT * FROM DUAL";
	        strSelect0=strSelect0+" SELECT * FROM DUAL";
	        
	        System.out.println("update_LISTA_RELAZIONI strSelect == "+strSelect);	     
	        Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect);         
	        
	        if((Answer_update > 0)&&(rec_time))
	            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(typeConfig);
	        
	        System.out.println("update_LISTA_RELAZIONI strSelect0 == "+strSelect0);    
	        Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect0);         
	        
	        if((Answer_update > 0)&&(rec_time))
	            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(typeConfig);
        }else
        {
        	System.out.println("la V_LISTA_RELAZIONI Ã¨ vuota!!!");	
        }
        
        return Answer_update;
    }
    

    
    //****************
    
    public boolean add_LISTA_SCRIPTS(int SCRIPT,int SCRIPT_TYPE)
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
                //System.out.println("SCRIPTS giÃ¨ esistente");
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
                //System.out.println("SCRIPTS giÃ¨ esistente alla pos="+i);
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
    }
    
    
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
    public boolean     SE_INDICE;
    public int         VALORE_AGGREGAZIONE_DEF;
    public int         VALORE_AGGREGAZIONE;
    public int         LENGTH_VALORE_START;
    public String      SUFF_VALORE_INDICE;
    public String      DATA_INIZIO_INDICE;
    public String      DATA_FINE_INDICE;
    
  
}

