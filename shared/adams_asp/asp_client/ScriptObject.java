/*
 * ScriptObject.java
 *
 * Created on 27 ottobre 2005, 10.28
 */

/**
 *
 * @author  Raffaele Ficcadenti raffaele.ficcadenti@e-tech.net
 * @version 
 */
import java.util.*;
import java.sql.*;
   
public class ScriptObject {

    
    public static class scriptBase
    {
        String TIPO_DI_CONFIGURAZIONE;
        int IDSCRIPT;
        String TAG;
        int NUMVARIABLE1;
        int NUMVARIABLE2;
        int NUMSCRIPTTEXT;
        Vector VARIABLES1;
        Vector VARIABLES2;
        Vector SCRIPTTEXT;
        String RESULTVARNAME;
        int RESULTTYPE;
        int FLAG_VALIDATE;
    }
    
    public scriptBase                       sc      = null;
    private boolean                         DEBUG   = false;
    /** Creates new ScriptObject */
    public ScriptObject() {
    }
    
    //public boolean readScript(String nameScript,String nameConfig,int idScript)
    public boolean readScript(String nameConfig,int idScript)
    {
        boolean flag=false;
        String strSelect="select * from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' and IDSCRIPT="+idScript;//+" and TAG='"+nameScript+"'";
        if(DEBUG)
           System.out.println("ScriptObject [readScript] ----> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        int cont=0;
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    sc=new scriptBase();
                    sc.TIPO_DI_CONFIGURAZIONE=rs.getString("TIPO_DI_CONFIGURAZIONE");
                    if(sc.TIPO_DI_CONFIGURAZIONE==null)
                        sc.TIPO_DI_CONFIGURAZIONE="";
                    sc.IDSCRIPT=rs.getInt("IDSCRIPT");
                    sc.TAG=rs.getString("TAG");
                    if(sc.TAG==null)
                        sc.TAG="";
                    sc.NUMVARIABLE1=rs.getInt("NUMVARIABLE1");
                    sc.NUMVARIABLE2=rs.getInt("NUMVARIABLE2");
                    sc.NUMSCRIPTTEXT=rs.getInt("NUMSCRIPTTEXT");
                    sc.RESULTVARNAME=rs.getString("RESULTVARNAME");
                    if(sc.RESULTVARNAME==null)
                        sc.RESULTVARNAME="";
                    sc.RESULTTYPE=rs.getInt("RESULTTYPE");
                    sc.FLAG_VALIDATE=rs.getInt("FLAG_VALIDATE");
                    
                    if(DEBUG)
                    {
                        System.out.println("sc.TIPO_DI_CONFIGURAZIONE="+sc.TIPO_DI_CONFIGURAZIONE);
                        System.out.println("sc.IDSCRIPT="+sc.IDSCRIPT);
                        System.out.println("sc.TAG="+sc.TAG);
                        System.out.println("sc.NUMVARIABLE1="+sc.NUMVARIABLE1);
                        System.out.println("sc.NUMVARIABLE2="+sc.NUMVARIABLE2);
                        System.out.println("sc.NUMSCRIPTTEXT="+sc.NUMSCRIPTTEXT);
                        System.out.println("sc.RESULTVARNAME="+sc.RESULTVARNAME);
                        System.out.println("sc.RESULTTYPE="+sc.RESULTTYPE);
                        System.out.println("sc.FLAG_VALIDATE="+sc.FLAG_VALIDATE);
                    }
                    cont++;
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            } 
            
            if(cont>0)
            {
                sc.VARIABLES1=new Vector();
                sc.VARIABLES2=new Vector();
                sc.SCRIPTTEXT=new Vector();
            
                if(sc.NUMVARIABLE1>0)
                {
                    read_VARIABLES_SCRIPTS("",nameConfig,idScript,"tab_script_var1");
                }
                if(sc.NUMVARIABLE2>0)
                {
                    read_VARIABLES_SCRIPTS("",nameConfig,idScript,"tab_script_var2");
                }
                if(sc.NUMSCRIPTTEXT>0)
                { 
                    read_TEXT_SCRIPTS("",nameConfig,idScript);
                }
                flag=true;
            }else 
            {
                flag=false;
            }
        }else
        {
            System.out.println("ScriptObject [readScript] ----> rs==null");
            flag=false;
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
        
        return flag;
    }
    
    private void read_VARIABLES_SCRIPTS(String nameScript,String nameConfig,int idScript,String link)
    {
        if(link.equals("VARIABLES1"))
            sc.VARIABLES1.removeAllElements();
        else if(link.equals("VARIABLES2"))
            sc.VARIABLES1.removeAllElements();
        
        String[] nomeCampi      = {"VRBNAME"};
        String tableConfig      = link;
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            else
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig ;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+" TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' and IDSCRIPT="+idScript+" ORDER BY VRBNAME";
        if(DEBUG)
            System.out.println("read_VARIABLES_SCRIPTS() select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) )
                {
                    if(link.equals("tab_script_var1"))
                        sc.VARIABLES1.addElement(new String(rs.getString(nomeCampi[0])));
                    else if(link.equals("tab_script_var2"))
                        sc.VARIABLES2.addElement(new String(rs.getString(nomeCampi[0])));
                    
                    if(DEBUG)
                    {
                        System.out.println(""+link+"="+rs.getString(nomeCampi[0]));
                    }
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
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
    }
    
    
    
    private void read_TEXT_SCRIPTS(String nameScript,String nameConfig,int idScript)
    {
        sc.SCRIPTTEXT.removeAllElements();
        
        String[] nomeCampi      = {"VRBTEXT"};
        String tableConfig      = "tab_script_text";
        
        String sel_ConfigNtm = "select ";
        
        for(int i=0;i<nomeCampi.length;i++)
        {
            if(i!=nomeCampi.length-1)
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
            else
                sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
        }
        sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig ;
        sel_ConfigNtm=sel_ConfigNtm+" where ";
        sel_ConfigNtm=sel_ConfigNtm+"TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' and IDSCRIPT="+idScript+" ORDER BY VRBTEXT";
        if(DEBUG)
            System.out.println("read_VARIABLES_SCRIPTS() select ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) )
                {
                    sc.SCRIPTTEXT.addElement(new String(rs.getString(nomeCampi[0])));
                    if(DEBUG)
                    {
                        System.out.println("SCRIPTTEXT="+rs.getString(nomeCampi[0]));
                    }
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
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
    }

}
