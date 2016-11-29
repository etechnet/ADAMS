/*
 * ADAMS_Plugin.java
 *
 * Created on 30 novembre 2005, 11.45
 */

/**
 *
 * @author  Raffo
 * @version 
 */

import java.util.Vector;
import java.sql.*;

public class ADAMS_Plugin extends Vector{

    private int                     idPlugin;
    private boolean                 DEBUG = false;
    public static class plugin
    {
        String NOME_PLUGIN;
        String TIPO_DI_CONFIGURAZIONE;
        String DESCRIZIONE;
        int ID;
        
        public plugin(String NOME_PLUGIN,String TIPO_DI_CONFIGURAZIONE,String DESCRIZIONE,int ID)
        {
            this.NOME_PLUGIN=NOME_PLUGIN;
            this.TIPO_DI_CONFIGURAZIONE=TIPO_DI_CONFIGURAZIONE;
            this.DESCRIZIONE=DESCRIZIONE;
            this.ID=ID;
        }
        
        public plugin()
        {}
    }
     public ADAMS_Plugin() {
        new Vector();
     }
    /** Creates new ADAMS_Plugin */
    public ADAMS_Plugin(String config_NAME_ALIAS) {
        new Vector();
        
        String strSelect="select * from  l_plugin_library where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"'";;
        
        if(DEBUG)
            System.out.println("ADAMS_Plugin [readAllPlugin] ----> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    String NOME_PLUGIN=rs.getString("NOME_PLUGIN");
                    if(NOME_PLUGIN==null)
                        NOME_PLUGIN="";
                    
                    String TIPO_DI_CONFIGURAZIONE=rs.getString("TIPO_DI_CONFIGURAZIONE");
                    if(TIPO_DI_CONFIGURAZIONE==null)
                        TIPO_DI_CONFIGURAZIONE="";
                    
                    String DESCRIZIONE=rs.getString("DESCRIZIONE");
                    if(DESCRIZIONE==null)
                        DESCRIZIONE="";
                    
                    if(DEBUG)
                    {
                        System.out.println("NOME_PLUGIN="+NOME_PLUGIN);
                        System.out.println("   TIPO_DI_CONFIGURAZIONE="+TIPO_DI_CONFIGURAZIONE);
                        System.out.println("   DESCRIZIONE="+DESCRIZIONE);
                        System.out.println("\n");
                    }
                    
                    if(isPresentPlugin(NOME_PLUGIN,TIPO_DI_CONFIGURAZIONE,DESCRIZIONE)==-1)
                    {
                        addPlugin(NOME_PLUGIN,TIPO_DI_CONFIGURAZIONE,DESCRIZIONE);
                    }
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }else
        {
            System.out.println("ADAMS_Plugin [readAllPlugin] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ADAMS_Plugin [readAllPlugin] ----> ERROR-SQL Operation " + exc.toString());
        }
    }
    
    public int addPlugin(String NOME_PLUGIN,String TIPO_DI_CONFIGURAZIONE,String DESCRIZIONE)
    {
        idPlugin=idPlugin+1;
        plugin pl=new plugin(NOME_PLUGIN,TIPO_DI_CONFIGURAZIONE,DESCRIZIONE,idPlugin);
        addElement(pl);
        return idPlugin;
    }
    
    public int isPresentPlugin(String NOME_PLUGIN,String TIPO_DI_CONFIGURAZIONE,String DESCRIZIONE)
    {
        int len=size();
        for(int i=0;i<len;i++)
        {
            plugin pl=(plugin)elementAt(i);
            if (pl.NOME_PLUGIN.equals(NOME_PLUGIN) && (pl.TIPO_DI_CONFIGURAZIONE.equals(TIPO_DI_CONFIGURAZIONE)) )
            {
                return pl.ID; 
            }
        }
        return -1;
    }
    
       
    public Vector getVPlugin()
    {
        Vector appo=new Vector();
        for(int i=0;i<size();i++)
        {
            appo.addElement((plugin)elementAt(i));
        }
        return appo;
    }
}
