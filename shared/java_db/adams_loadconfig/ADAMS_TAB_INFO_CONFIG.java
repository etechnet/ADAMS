package net.etech.loadconfig;
/*
 * ADAMS_TAB_INFO_CONFIG.java
 *
 * Created on 10 giugno 2005, 10.09
 */

/**
 *
 * @author  Raffo
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
                    
                    if(ADAMS_GlobalParam.DEBUG)
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

    public String indexFileName = ""; // Nome del file INDEX ricavato dal campo ID_INDICE con congruenza nella tabella  l_index_lib
}