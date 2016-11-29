package net.etech.loadconfig;
/*
 * ADAMS_TAB_KIT_CONTATORI.java
 *
 * Created on 5 ottobre 2005, 12.01
 */

/**
 *
 * @author  luca
 * @version 
 */
import java.util.Vector;
import java.sql.*;
public class ADAMS_TAB_KIT_CONTATORI {

    /** Creates new ADAMS_TAB_KIT_CONTATORI */
    public ADAMS_TAB_KIT_CONTATORI() 
    {
        TIPO_DI_CONFIGURAZIONE  = new String();     //NOT NULL VARCHAR2(30)
        IDCOUNTER               = -1;               //NOT NULL NUMBER
        TAG                     = new String();     //VARCHAR2(30)
        USEPLUGIN               = 'N';              //NOT NULL CHAR(1)
        PLUGINNAME              = new String();     //VARCHAR2(256)
        USEPATH                 = 'N';              //NOT NULL CHAR(1)
        PATHNAME                = new String();     //VARCHAR2(1540)
        V_COUNTERKIT            = new Vector();     //TAB_COUNTERKIT -- il vettore contiene oggetti di tipo ADAMS_TAB_COUNTERKIT
    }

    public boolean ctrl_V_COUNTERKIT_TAG_IsPresent(ADAMS_TAB_COUNTERKIT COUNTERKIT)
    {
        for(int i=0; i<V_COUNTERKIT.size(); i++)
        {
            ADAMS_TAB_COUNTERKIT stCounter = (ADAMS_TAB_COUNTERKIT)(V_COUNTERKIT.elementAt(i));
            if((stCounter.TAG.toUpperCase().compareTo(COUNTERKIT.TAG.toUpperCase()) == 0)&&(stCounter != COUNTERKIT))
                return true;
        }
        return false;    
    }
   
    public boolean ctrl_V_COUNTERKIT_Counter_IsPresent(ADAMS_TAB_COUNTERKIT COUNTERKIT)
    {
        for(int i=0; i<V_COUNTERKIT.size(); i++)
        {
            ADAMS_TAB_COUNTERKIT stCounter = (ADAMS_TAB_COUNTERKIT)(V_COUNTERKIT.elementAt(i));
            if((stCounter.COUNTERINDEX == COUNTERKIT.COUNTERINDEX)&&(stCounter != COUNTERKIT))
                return true;
        }
        return false;    
    }
    
    public boolean readCounterKit()
    {
        
        V_COUNTERKIT.removeAllElements();
        
        String sel_ReadCounters = "select "+item_KIT_CONTATORI+" from "+name_TabCounters+
                                        " where TIPO_DI_CONFIGURAZIONE = '"+this.TIPO_DI_CONFIGURAZIONE+"'"+
                                        " and IDCOUNTER = "+IDCOUNTER;

        //System.out.println("sel_ReadCounters --> "+sel_ReadCounters);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ReadCounters,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                   
                //*** Campi Contatore -- alcuni campi sono NOT Null sul DB ***//
                    this.IDCOUNTER  = rs.getInt("IDCOUNTER");          //not null sil DB

                    String tagCounter = rs.getString("TAG");
                    if(tagCounter != null)
                        this.TAG = tagCounter;

                    this.USEPLUGIN = rs.getString("USEPLUGIN").charAt(0);      //not null sil DB

                    String plname = rs.getString("PLUGINNAME");
                    if(plname != null)
                        this.PLUGINNAME = plname;                        

                    this.USEPATH   = rs.getString("USEPATH").charAt(0);        //not null sil DB                        

                    String pathName = rs.getString("PATHNAME");
                    if(pathName != null)
                        this.PATHNAME   = pathName;                

                    
                    String[] nomeCampi = {"TRIGGERFIELD","TRIGGERVALUE","COUNTERINDEX","COUNTERTYPE","PERCENTOF","TRIGGERCOUNTER","TAG","DESCRIPTION"}; 

                    String linkTable  = "tab_type_counters";                        
                    String selectReadNested = "select TIPO_DI_CONFIGURAZIONE,";

                    for(int i=0;i<nomeCampi.length;i++)
                    {
                        if(i!=nomeCampi.length-1)
                            selectReadNested += nomeCampi[i]+",";
                        else
                            selectReadNested += nomeCampi[i]+" ";  
                    }

                    selectReadNested += " from " + linkTable;
                    selectReadNested += " where ";
                    selectReadNested += " TIPO_DI_CONFIGURAZIONE='"+this.TIPO_DI_CONFIGURAZIONE+"' and IDCOUNTER="+this.IDCOUNTER+" order by TAG";

                    //System.out.println(" --> selectReadNested_counters : "+selectReadNested);
                    Statement SQLStatement_1 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rsNested  = ADAMS_GlobalParam.connect_Oracle.Query_RS(selectReadNested,SQLStatement_1);
                    if(rsNested != null)
                    {
                        try
                        {
                            while (rsNested.next())
                            {
                                ADAMS_TAB_COUNTERKIT counterKit = new ADAMS_TAB_COUNTERKIT();
                                counterKit.TRIGGERFIELD = rsNested.getInt(nomeCampi[0]); //TRIGGERFIELD
                                counterKit.TRIGGERVALUE = rsNested.getInt(nomeCampi[1]); //TRIGGERVALUE
                                counterKit.COUNTERINDEX = rsNested.getInt(nomeCampi[2]); //COUNTERINDEX
                                counterKit.COUNTERTYPE  = rsNested.getInt(nomeCampi[3]); //COUNTERTYPE
                                counterKit.PERCENTOF    = rsNested.getInt(nomeCampi[4]); //PERCENTOF
                                counterKit.TRIGGERCOUNTER    = rsNested.getInt(nomeCampi[5]); //TRIGGERCOUNTER

                                String tag = rsNested.getString(nomeCampi[6]);    //TAG
                                if(tag != null)
                                    counterKit.TAG      = tag;

                                String desc = rsNested.getString(nomeCampi[7]);   //DESCRIPTION
                                if(desc != null)
                                    counterKit.DESCRIPTION  = desc;

                                this.V_COUNTERKIT.addElement(counterKit);

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
                        SQLStatement_1.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("(ADAMS_TAB_KIT_CONTATORI-2) SQL Operation " + exc.toString());
                    }
                }
            }catch (Exception ex) 
            { 
                System.out.println(ex.toString());
                return false;
            }
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_TAB_KIT_CONTATORI-1) SQL Operation " + exc.toString());
        }
        
        //System.out.println("Counter SIZE "+V_COUNTERKIT.size());
        return true;
    }
    
    
    //Nome tabella
    private final String name_TabCounters = "tab_counters_kit";
    private final String item_KIT_CONTATORI = "IDCOUNTER,TAG,USEPLUGIN,PLUGINNAME,USEPATH,PATHNAME"; 
     
    public String  TIPO_DI_CONFIGURAZIONE; //NOT NULL VARCHAR2(30)
    public int     IDCOUNTER;              //NOT NULL NUMBER
    public String  TAG;                    //VARCHAR2(30)
    public char    USEPLUGIN;              //NOT NULL CHAR(1)
    public String  PLUGINNAME;             //VARCHAR2(256)
    public char    USEPATH;                //NOT NULL CHAR(1)
    public String  PATHNAME;               //VARCHAR2(1540)
    public Vector  V_COUNTERKIT;             //TAB_COUNTERKIT
   
    
    
}