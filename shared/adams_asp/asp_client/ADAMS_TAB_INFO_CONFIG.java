/*
 * ADAMS_TAB_INFO_CONFIG.java
 *
 * Created on 10 giugno 2005, 10.09
 */

/**
 *
 * @author  root
 * @version 
 */

import java.util.Vector;
import java.sql.*;

public class ADAMS_TAB_INFO_CONFIG {

    
    public ADAMS_TAB_INFO_CONFIG(String name_typeConf)
    {
        DR_USE_PLUGIN              = 0;
        DR_PLUGIN_NAME             = new String();
        DR_REC_LEN                 = 0;
        DR_FLAG_USE_PATH           = 0;
        DR_SPECIFY_PL_PATH         = new String();
        TIPO_DI_CONFIGURAZIONE      = new String(name_typeConf.trim());
        GLB_DEFAULT_PATH_PL         = new String();
        AUTHOR                      = new String();
        LAST_MODIFICATION_TIME      = new String();
        BOUNDARY                    = 1;
        CONF_DEFAULT                = 'N';
        ID_INDICE                   = -1;

         // necessita nel caso di update dello stesso campo (clausula where)
        TIPO_DI_CONFIGURAZIONE_COPY = new String(name_typeConf.trim());
        
        indexFileName = new String();
    }
    
    
    
    private String InsertString_ItemValue()
    {
        //String itemName  = "DR_USE_PLUGIN, DR_PLUGIN_NAME, DR_REC_LEN, DR_FLAG_USE_PATH, DR_SPECIFY_PL_PATH, TIPO_DI_CONFIGURAZIONE, GLB_DEFAULT_PATH_PL, AUTHOR, LAST_MODIFICATION_TIME,BOUNDARY,CONF_DEFAULT,ID_INDICE";
        String itemValue = new String (+DR_USE_PLUGIN+",");
        itemValue += "'"+DR_PLUGIN_NAME+"',";
        itemValue += DR_REC_LEN+",";
        itemValue += DR_FLAG_USE_PATH+",";
        itemValue += "'"+DR_SPECIFY_PL_PATH+"',";
        itemValue += "'"+TIPO_DI_CONFIGURAZIONE+"',";
        itemValue += "'"+GLB_DEFAULT_PATH_PL+"',";
        itemValue += "'"+AUTHOR+"',";
        itemValue += "(select  DATE_FORMAT(CURRENT_TIMESTAMP, '%a %d-%M-%Y %H:%i:%S')  from dual),";
        itemValue += BOUNDARY+",";
        itemValue += "'"+CONF_DEFAULT+"',";
        itemValue += ID_INDICE;
        
        return itemValue;
    }
    
    private String UpdateString_ItemValue()
    {
        //String itemName  = "DR_USE_PLUGIN, DR_PLUGIN_NAME, DR_REC_LEN, DR_FLAG_USE_PATH, DR_SPECIFY_PL_PATH, TIPO_DI_CONFIGURAZIONE, GLB_DEFAULT_PATH_PL, AUTHOR, LAST_MODIFICATION_TIME,BOUNDARY,CONF_DEFAULT,ID_INDICE";
        String itemValue = new String("DR_USE_PLUGIN="+DR_USE_PLUGIN+",");        
        itemValue += "DR_PLUGIN_NAME='"+DR_PLUGIN_NAME+"',";
        itemValue += "DR_REC_LEN="+DR_REC_LEN+",";
        itemValue += "DR_FLAG_USE_PATH="+DR_FLAG_USE_PATH+",";
        itemValue += "DR_SPECIFY_PL_PATH='"+DR_SPECIFY_PL_PATH+"',";
        itemValue += "TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"',";
        itemValue += "GLB_DEFAULT_PATH_PL='"+GLB_DEFAULT_PATH_PL+"',";
        itemValue += "AUTHOR='"+AUTHOR+"',";
        itemValue += "LAST_MODIFICATION_TIME = (select  DATE_FORMAT(CURRENT_TIMESTAMP, '%a %d-%M-%Y %H:%i:%S')  from dual),";
        itemValue += "BOUNDARY="+BOUNDARY+",";
        itemValue += "CONF_DEFAULT='"+CONF_DEFAULT+"',";
        itemValue += "ID_INDICE="+ID_INDICE;
        
        return(itemValue);
    }
    
    public void InfoConfigName(String name_typeConf)
    {
        //System.out.println("NEW RS InfoConfigName()");
        
        String sel_ConfName = "select "+itemName+" from tab_info_config"+
                                " where TIPO_DI_CONFIGURAZIONE = '"+name_typeConf+"' ";
        
        //System.out.println("sel_ConfName ----> "+sel_ConfName);
                               
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfName,SQLStatement);
        if(rs != null)
        {
            try
            {
                while (rs.next()) 
                {                    
                    DR_USE_PLUGIN  = rs.getInt("DR_USE_PLUGIN");
                    
                    if(rs.getString ("DR_PLUGIN_NAME") != null)
                        DR_PLUGIN_NAME = rs.getString("DR_PLUGIN_NAME");
                    
                    DR_REC_LEN         = rs.getInt("DR_REC_LEN");
                    DR_FLAG_USE_PATH   = rs.getInt("DR_FLAG_USE_PATH");
                    
                    if(rs.getString ("DR_SPECIFY_PL_PATH") != null)
                        DR_SPECIFY_PL_PATH = rs.getString("DR_SPECIFY_PL_PATH");
                    
                    if(rs.getString ("TIPO_DI_CONFIGURAZIONE") != null)
                        TIPO_DI_CONFIGURAZIONE  = rs.getString("TIPO_DI_CONFIGURAZIONE");
                    
                    if(rs.getString ("GLB_DEFAULT_PATH_PL") != null)
                        GLB_DEFAULT_PATH_PL = rs.getString("GLB_DEFAULT_PATH_PL");
                    
                    if(rs.getString ("AUTHOR") != null)
                        AUTHOR  = rs.getString("AUTHOR");
                    
                    if(rs.getString ("LAST_MODIFICATION_TIME") != null)
                        LAST_MODIFICATION_TIME  = rs.getString("LAST_MODIFICATION_TIME");
                    
                    BOUNDARY = rs.getInt("BOUNDARY");
                    
                    if(rs.getString ("CONF_DEFAULT") != null)
                        CONF_DEFAULT = rs.getString("CONF_DEFAULT").charAt(0);
                    
                    ID_INDICE = rs.getInt("ID_INDICE");
                    
                    
                     // ***  NAME_FILE INDEX ***
                    String sel_fileNameINDEX = "select NOME_FILE from  l_index_lib where ID_INDICE = "+ID_INDICE+""; 
                    indexFileName = ADAMS_GlobalParam.connect_Oracle.Query_s(sel_fileNameINDEX);
                    // ***  NAME_FILE INDEX ***
                    
                    if(DEBUG)
                    {
                        System.out.println("--- INFO_CONFIG  ---");
                        System.out.println("DR_USE_PLUGIN          ->"+DR_USE_PLUGIN);
                        System.out.println("DR_PLUGIN_NAME         ->"+DR_PLUGIN_NAME);
                        System.out.println("DR_REC_LEN             ->"+DR_REC_LEN);
                        System.out.println("DR_FLAG_USE_PATH       ->"+DR_FLAG_USE_PATH);   
                        System.out.println("DR_SPECIFY_PL_PATH     ->"+DR_SPECIFY_PL_PATH);
                        System.out.println("TIPO_DI_CONFIGURAZIONE  ->"+TIPO_DI_CONFIGURAZIONE);          
                        System.out.println("GLB_DEFAULT_PATH_PL     ->"+GLB_DEFAULT_PATH_PL);               
                        System.out.println("AUTHOR                  ->"+AUTHOR);
                        System.out.println("LAST_MODIFICATION_TIME  ->"+LAST_MODIFICATION_TIME);
                        System.out.println("BOUNDARY                ->"+BOUNDARY);
                        System.out.println("CONF_DEFAULT            ->"+CONF_DEFAULT);
                        System.out.println("ID_INDICE               ->"+ID_INDICE);
                        System.out.println();
                        System.out.println("File Indice Assegnato   ->"+indexFileName);
                        System.out.println();
                    }
                    
                }
            }catch (Exception ex) 
            { 
                System.out.println(ex);
            }  
        }
        else
            System.out.println("ResultSet rs "+rs);
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_INFO_CONFIG-1) SQL Operation " + exc.toString());
        }
    }
    
    public int insertInfoConfig()
    {
        String str_itemValue = InsertString_ItemValue();
        String str_Insert = ("insert intotab_info_config ("+itemName+") values ( "+str_itemValue+" )");
        //System.out.println(str_Insert);
        
        /*if(CONF_DEFAULT == 'Y')
        {
            resetAll_CONF_DEFAULT();
        }*/
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);

        return Answer_Ins;
    }
    
    public int updateInfoConfig()
    {
        /*TAB_INFO_CONFIG
       tab_analysis_type
       tab_config
       tab_report
        TAB_SCRIPT
       tab_help*/
        
       /* if(CONF_DEFAULT == 'Y')
        {
           resetAll_CONF_DEFAULT();
        }*/
        
        String name_TAB_INFO_CONFIG = "tab_info_config";
        String str_itemValue = UpdateString_ItemValue();
        String str_update = ("update "+name_TAB_INFO_CONFIG+" set "+str_itemValue+" where TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIGURAZIONE_COPY+"'");

        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
        
         //Nome Configurazione modificato -- Modifica in tutte le tabelle collegate
        if(TIPO_DI_CONFIGURAZIONE.compareTo(TIPO_DI_CONFIGURAZIONE_COPY) != 0)
        {
            //System.out.println("Modifica in tutte le tabelle collegate");
            if(Answer_update >= 0)
            {            
                for(int i=0; i<ALL_TABLE_update_Delete.length; i++)
                {
                    if( ((String)ALL_TABLE_update_Delete[i]).compareTo(name_TAB_INFO_CONFIG) != 0 )
                    {
                        String str_updateALL = ("update "+ALL_TABLE_update_Delete[i]+" set TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' where TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIGURAZIONE_COPY+"'");
                        Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_updateALL);
                    }
                    //else
                        //System.out.println(name_TAB_INFO_CONFIG +" già fatto update");

                    if(Answer_update < 0)
                    {
                       System.out.println("Error Table ->"+ALL_TABLE_update_Delete[i]+" ");
                       break;
                    }
                    /*else if(Answer_update == 0)
                        System.out.println("La tabella "+ALL_TABLE_update_Delete[i]+" non ha righe con il campo TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIGURAZIONE+"'");
                    else
                        System.out.println("UPDATE nella tabella "+ALL_TABLE_update_Delete[i]+" n."+Answer_update+" righe.");*/
                }
            }
        }
        //else
           //System.out.println("** Nome Configurazione NON MODIFICATO -- NO UPDATE tabelle collegate"); 
        
        try
        {         
            if(Answer_update >= 0)
            {                
                TIPO_DI_CONFIGURAZIONE_COPY = new String(TIPO_DI_CONFIGURAZIONE);
                
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
            System.out.println("Errore ADAMS_TAB_INFO_CONFIG.java 1");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
             catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_INFO_CONFIG.java 1a");}
        }
        

        return Answer_update;
    }
    
    // se >= 0 OK --- -1 Errore 
    public int deleteInfoConfig()
    {
        int Answer_Del = 0;
       
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        //System.out.println("Cancella tutte le tabelle collegate.");
        for(int i=0; i<ALL_TABLE_update_Delete.length; i++)
        {
            String str_delete = ("delete from "+ALL_TABLE_update_Delete[i]+" where TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIGURAZIONE+"'");
            Answer_Del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);

            if(Answer_Del < 0)
            {
               System.out.println("Delete Error Table -> "+ALL_TABLE_update_Delete[i]+" ");
               break;
            }
            /*else if(Answer_Del == 0)
                System.out.println("La tabella "+ALL_TABLE_update_Delete[i]+" non ha righe con il campo TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIGURAZIONE+"'");
            else
                System.out.println("Cancellate nella tabella "+ALL_TABLE_update_Delete[i]+" n."+Answer_Del+" righe.");*/
        }
        
        try
        {         
            if(Answer_Del >= 0)
            {                                
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
            System.out.println("Errore ADAMS_TAB_INFO_CONFIG.java 1");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
             catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_INFO_CONFIG.java 1a");}
        }
        
        return Answer_Del; // se >= 0 OK --- -1 Errore
    }

   /* private int resetAll_CONF_DEFAULT()
    {
        System.out.println("resetAll_CONF_DEFAULT");
     
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        String name_TAB_INFO_CONFIG = "tab_info_config";
        String str_update_CONF_DEFAUL = ("update "+name_TAB_INFO_CONFIG+" set CONF_DEFAULT='N'");
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update_CONF_DEFAUL);
        return Answer_update;
    }*/
    
    public int      DR_USE_PLUGIN;
    public String   DR_PLUGIN_NAME;
    public int      DR_REC_LEN;
    public int      DR_FLAG_USE_PATH;
    public String   DR_SPECIFY_PL_PATH;
    public String   TIPO_DI_CONFIGURAZIONE;
    public String   GLB_DEFAULT_PATH_PL;
    public String   AUTHOR;
    public String   LAST_MODIFICATION_TIME;
    public int      BOUNDARY;
    public char     CONF_DEFAULT;
    public int      ID_INDICE;
    
    private String TIPO_DI_CONFIGURAZIONE_COPY; // necessita nel caso di update dello stesso campo (clausula where)
    
    //Tutti i campi della tabellatab_info_config
    private String itemName  = "DR_USE_PLUGIN,DR_PLUGIN_NAME,DR_REC_LEN, DR_FLAG_USE_PATH,DR_SPECIFY_PL_PATH,TIPO_DI_CONFIGURAZIONE,GLB_DEFAULT_PATH_PL,AUTHOR,LAST_MODIFICATION_TIME,BOUNDARY,CONF_DEFAULT,ID_INDICE ";
    
    // Tutte le tabelle da modificare.
    private String[] ALL_TABLE_update_Delete = {
		"tab_info_config",
		"tab_help",
		"tab_report",
		"l_plugin_library",
		"tab_gui_events",
		"tab_analysis_type","tab_list_report",
		"tab_config","tab_value_type","tab_aggregate_restrictions","tab_subst_values","tab_list_exception","tab_list_script",
		"tab_list_relation","tab_list_restriction","tab_list_analysis","tab_list_restriction_ob",
		"tab_scripts","tab_script_var1","tab_script_var2","tab_script_text",
		"tab_counters_kit","tab_type_counters",
		"tab_alarm_generator"/*NON era inserita*/,
		"tab_alarm_generator_thresholds"/*NON era inserita*/,
		"tab_alarm_relation"/*NON era inserita*/,
		"tab_alarm_relation_elements"/*NON era inserita*/,
		"tab_alarm_relation_handlers"/*NON era inserita*/,
		"tab_alarm_server_master",
		"tab_alarm_server_production",
		"tab_alarm_threshold_generator"/*NON era inserita*/};
    private boolean DEBUG = false;
    
    public String indexFileName = ""; // Nome del file INDEX ricavato dal campo ID_INDICE con congruenza nella tabella  l_index_lib
}

/*
   tab_info_config
 *
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 DR_USE_PLUGIN                                     NUMBER
 DR_PLUGIN_NAME                                    VARCHAR2(256)
 DR_REC_LEN                                        NUMBER
 DR_FLAG_USE_PATH                                  NUMBER
 DR_SPECIFY_PL_PATH                                VARCHAR2(1536)
 TIPO_DI_CONFIGURAZIONE                    NOT NULL VARCHAR2(30)
 GLB_DEFAULT_PATH_PL                                VARCHAR2(1536)
 AUTHOR                                             VARCHAR2(160)
 LAST_MODIFICATION_TIME                             VARCHAR2(40)
 BOUNDARY                                           NUMBER
 LOCK_TABLE                                         VARCHAR2(40)
 FILE_NAME_CONF                                     VARCHAR2(160)
 CONF_DEFAULT                                       CHAR(1)
 ID_INDICE                                          NUMBER(2)


*/