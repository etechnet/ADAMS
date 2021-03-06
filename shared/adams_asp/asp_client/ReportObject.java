/*
 * ReportObject.java
 *
 * Created on 9 agosto 2005, 14.47
 */

/**
 *
 * @author  Raffaele Ficcadenti
 * @version 
 */
import java.util.*;
import java.sql.*;

public class ReportObject {

    public boolean                  DEBUG                   = false;
    public static final int         ELEMENTO_COMUNE         = 0;
    public static final int         HEADER                  = 1;
    public static final int         BODY                    = 2;
    public static final int         TOTALIZER               = 3;
    public static final int         FOOTER                  = 4;
    public static final int         LINE_BREAK              = 5; //2
    public static final int         LINE_BLANK              = 6; //1
    public static final int         LINE_SEPARETOR          = 7; //3
    public static final String      objectDesc[]            = {"REPORT","HeaderCell","BodyCell","TotalizerCell","FooterRow"};
    private Vector                  vElementoBase           = new Vector();
    private Vector                  vElementoBaseTOREMOVE   = new Vector();
    private Vector                  vReport                 = new Vector();
    private Vector                  vHeaderReport           = new Vector();
    
    private String                  config_NAME_ALIAS       = "";
    private String                  NOME_SCHEMA             = "";
    private int                     ID_SCHEMA               = -1;
    public  int                     modificato              = 0;
    private Vector                  V_Analysis              = null;
    
    public static class elementoBase
    {
        int         idGUI;
        String      TIPO_DI_CONFIGURAZIONE; //ok ***
        int         IDREPORTSCHEMA; //ok --> dalla sequenz
        int         IDOBJECT; //ok --> dalla sequenz
        int         TYPE; //ok ***
        int         SUB_TYPE; //ok ***
        String      TAG; //OK ***
        int         HTML_VSIZE; //ok ***
        int         HTML_HSIZE; //ok ***
        int         HTML_VALIGN; //ok ***
        int         HTML_HALIGN; //ok ***
        int         HTML_WRAP; //ok ***
        int         HTML_FOREGROUND; //ok ***
        int         HTML_FOREGROUND_R; //ok ***
        int         HTML_FOREGROUND_G; //ok ***
        int         HTML_FOREGROUND_B; //ok ***
        int         HTML_BACKGROUND; //ok  ***
        int         HTML_BACKGROUND_R; //ok ***
        int         HTML_BACKGROUND_G; //ok ***
        int         HTML_BACKGROUND_B; //ok ***
        int         HTML_STYLE; //ok ***
        int         HTML_FONTSIZE; //ok ***
        int         HTML_BOLD; //ok ***
        int         HTML_ITALIC; //ok ***
        int         HTML_UNDERLINE; //ok ***
        String      OBJECT_TAG; //ok ***
        int         OBJECT_HASBORDER; //ok ***
        int         OBJECT_SIMPLEBODY; //ok ***
        int         OBJECT_EXCELCSV; //ok ***
        int         OBJECT_IDANALISYS; //ok ***
        int         OBJECT_USEPLUGIN; //ok ***
        String      OBJECT_PLUGINNAME; //ok ***
        int         OBJECT_DEFAULT_FOREGROUND_R; //ok ***
        int         OBJECT_DEFAULT_FOREGROUND_G; //ok ***
        int         OBJECT_DEFAULT_FOREGROUND_B; //ok ***
        int         OBJECT_DEFAULT_BACKGROUND_R; //ok ***
        int         OBJECT_DEFAULT_BACKGROUND_G; //ok ***
        int         OBJECT_DEFAULT_BACKGROUND_B; //ok ***
        String      HEADER_USERLABEL; //ok ***
        int         HEADER_VALUE; //ok ***
        String      HEADER_USERVALUE; //ok ***
        int         HEADER_ISURL; //ok ***
        int         HEADER_LINE;
        int         BODY_SOURCE; //ok ***
        int         BODY_IDKEY; //ok ***
        int         BODY_REPEATKEY;
        int         BODY_IDCOUNTER; // attualmente non usato
        String      BODY_TAGCOUNTER; // ok ***
        int         BODY_IDSCRIPT;
        String      BODY_PLUGIN_TAG; //ok ***
        String      BODY_PLUGIN_NAME; //ok ***
        String      BODY_USERVALUE; //ok ***
        int         BODY_ISURL; //ok ***
        int         BODY_TOTALIZER; //ok ***
        int         BODY_LINE;
        int         BODY_MAXDECIMALDIGIT;
        int         TOTALIZER_TRIGGER; //ok ***
        String      TOTALIZER_LABEL; //ok ***
        int         TOTALIZER_REDRAWHEADER; //ok ***
        int         TOTALIZER_BORDERAROUND; //ok ***
        int         TOTALIZER_LINE;
        int         TOTALIZER_USESCRIPT; //ok ***
        int         TOTALIZER_IDSCRIPT;
        int         FOOTER_SOURCE; //ok
        String      FOOTER_LABEL; //ok
        String      FOOTER_USERVALUE; //ok
        int         FOOTER_ISURL; //ok
        int         FOOTER_LINE;
        int         POSIZIONE;
        //ADAMS_JF_WIZARDBASE scriptBODY;
        //int         flagCOMMIT_BODY;
        //int         flagCOMMIT_TOTALIZER;
        //ADAMS_JF_WIZARDBASE scriptTOTALIZER;
    
        public elementoBase()
        {}
        
    }
    
    public void creaHeaderReport()
    {
            elementoBase hrNUOVO=new elementoBase();
            vHeaderReport.addElement(hrNUOVO);
    }
    
    public void readHeaderReport()
    {
        String strSelect="select * from(select DISTINCT TAG,TIPO_DI_CONFIGURAZIONE,IDREPORTSCHEMA,IDOBJECT,TYPE,SUB_TYPE,OBJECT_IDANALISYS,OBJECT_PLUGINNAME,OBJECT_USEPLUGIN,OBJECT_SIMPLEBODY,OBJECT_HASBORDER,OBJECT_EXCELCSV,OBJECT_DEFAULT_BACKGROUND_R,OBJECT_DEFAULT_BACKGROUND_G,OBJECT_DEFAULT_BACKGROUND_B,OBJECT_DEFAULT_FOREGROUND_R,OBJECT_DEFAULT_FOREGROUND_G,OBJECT_DEFAULT_FOREGROUND_B from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND TAG='"+NOME_SCHEMA+"') as SEL_REPO LIMIT 1;";
         if(DEBUG)
            System.out.println("ReportObject [readHeaderReport] ----> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        /*elementoBase hrTEST=new elementoBase();
        hrTEST.TAG="STANDARD";
        hrTEST.OBJECT_DEFAULT_FOREGROUND_R=10;
        hrTEST.OBJECT_DEFAULT_FOREGROUND_G=90;
        hrTEST.OBJECT_DEFAULT_FOREGROUND_B=100;
        hrTEST.OBJECT_DEFAULT_BACKGROUND_R=1;
        hrTEST.OBJECT_DEFAULT_BACKGROUND_G=110;
        hrTEST.OBJECT_DEFAULT_BACKGROUND_B=10;
        hrTEST.OBJECT_SIMPLEBODY=1;
        hrTEST.OBJECT_IDANALISYS=30;
        hrTEST.OBJECT_USEPLUGIN=0;
        hrTEST.OBJECT_PLUGINNAME="";
        vHeaderReport.addElement(hrTEST);
        */        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    
                    elementoBase hr=new elementoBase();
                    hr.TIPO_DI_CONFIGURAZIONE=rs.getString("TIPO_DI_CONFIGURAZIONE");
                    if(hr.TIPO_DI_CONFIGURAZIONE==null)
                        hr.TIPO_DI_CONFIGURAZIONE="";
                    hr.IDREPORTSCHEMA=rs.getInt("IDREPORTSCHEMA");
                    hr.IDOBJECT=rs.getInt("IDOBJECT");
                    hr.TYPE=rs.getInt("TYPE");
                    hr.SUB_TYPE=rs.getInt("SUB_TYPE");
                    hr.TAG=rs.getString("TAG");
                    if(hr.TAG==null)
                        hr.TAG="";
                    hr.OBJECT_HASBORDER=rs.getInt("OBJECT_HASBORDER");
                    hr.OBJECT_SIMPLEBODY=rs.getInt("OBJECT_SIMPLEBODY");
                    hr.OBJECT_EXCELCSV=rs.getInt("OBJECT_EXCELCSV");
                    hr.OBJECT_IDANALISYS=rs.getInt("OBJECT_IDANALISYS");
                    hr.OBJECT_USEPLUGIN=rs.getInt("OBJECT_USEPLUGIN");
                    hr.OBJECT_PLUGINNAME=rs.getString("OBJECT_PLUGINNAME");
                    if(hr.OBJECT_PLUGINNAME==null)
                        hr.OBJECT_PLUGINNAME="";
                    hr.OBJECT_DEFAULT_FOREGROUND_R=rs.getInt("OBJECT_DEFAULT_FOREGROUND_R");
                    hr.OBJECT_DEFAULT_FOREGROUND_G=rs.getInt("OBJECT_DEFAULT_FOREGROUND_G");
                    hr.OBJECT_DEFAULT_FOREGROUND_B=rs.getInt("OBJECT_DEFAULT_FOREGROUND_B");
                    hr.OBJECT_DEFAULT_BACKGROUND_R=rs.getInt("OBJECT_DEFAULT_BACKGROUND_R");
                    hr.OBJECT_DEFAULT_BACKGROUND_G=rs.getInt("OBJECT_DEFAULT_BACKGROUND_G");
                    hr.OBJECT_DEFAULT_BACKGROUND_B=rs.getInt("OBJECT_DEFAULT_BACKGROUND_B");
                    
                    if(DEBUG)
                        System.out.println("ReportObject [hr.TAG] ----> "+hr.TAG);
                    
                    vHeaderReport.addElement(hr);
                    config_NAME_ALIAS=hr.TIPO_DI_CONFIGURAZIONE;
                    NOME_SCHEMA=hr.TAG;
                    ID_SCHEMA=hr.IDREPORTSCHEMA;
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ReportObject [readHeaderReport] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
        
        //System.out.println("ReportObject [readHeaderReport] ----> "+vHeaderReport.size());
                    
    }
    
    public void readAllObject()
    {
        String strSelect="select * from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND TAG='"+NOME_SCHEMA+"' AND IDOBJECT>0 ORDER BY TYPE,POSIZIONE";
        
        if(DEBUG)
            System.out.println("ReportObject [readAllObject] ----> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        /*elementoBase ebTEST=new elementoBase();
        ebTEST.IDOBJECT=10;
        ebTEST.TYPE=HEADER;
        ebTEST.OBJECT_TAG="Label 1 - header";
        vElementoBase.addElement(ebTEST);
        
        ebTEST=new elementoBase();
        ebTEST.IDOBJECT=11;
        ebTEST.TYPE=HEADER;
        ebTEST.OBJECT_TAG="Label 2 - header";
        vElementoBase.addElement(ebTEST);
        
        ebTEST=new elementoBase();
        ebTEST.IDOBJECT=12;
        ebTEST.TYPE=FOOTER;
        ebTEST.OBJECT_TAG="Label 1 - footer";
        ebTEST.FOOTER_LABEL="prova";
        ebTEST.FOOTER_SOURCE=-3;
        ebTEST.FOOTER_USERVALUE="dshdsuyd";
        ebTEST.FOOTER_ISURL=1;
        vElementoBase.addElement(ebTEST);
        
        ebTEST=new elementoBase();
        ebTEST.IDOBJECT=13;
        ebTEST.TYPE=TOTALIZER;
        ebTEST.TOTALIZER_TRIGGER=-1;
        ebTEST.OBJECT_TAG="Label 1 - totalizer";
        ebTEST.TOTALIZER_LABEL="dtsdsy";
        ebTEST.TOTALIZER_REDRAWHEADER=1;
        ebTEST.TOTALIZER_USESCRIPT=1;
        vElementoBase.addElement(ebTEST);
        
        ebTEST=new elementoBase();
        ebTEST.IDOBJECT=14;
        ebTEST.TYPE=BODY;
        ebTEST.OBJECT_TAG="Label 1 - body";
        ebTEST.BODY_SOURCE=0;
        ebTEST.BODY_IDKEY=43;
        vElementoBase.addElement(ebTEST);*/
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    
                    elementoBase eb=new elementoBase();
                    eb.TIPO_DI_CONFIGURAZIONE=rs.getString("TIPO_DI_CONFIGURAZIONE");
                    if(eb.TIPO_DI_CONFIGURAZIONE==null)
                        eb.TIPO_DI_CONFIGURAZIONE="";
                    eb.IDREPORTSCHEMA=rs.getInt("IDREPORTSCHEMA");
                    eb.IDOBJECT=rs.getInt("IDOBJECT");
                    eb.idGUI=eb.IDOBJECT;
                    eb.TYPE=rs.getInt("TYPE");
                    eb.SUB_TYPE=rs.getInt("SUB_TYPE");
                    eb.TAG=rs.getString("TAG");
                    if(eb.TAG==null)
                        eb.TAG="";
                    eb.HTML_VSIZE=rs.getInt("HTML_VSIZE");
                    eb.HTML_HSIZE=rs.getInt("HTML_HSIZE");
                    eb.HTML_VALIGN=rs.getInt("HTML_VALIGN");
                    eb.HTML_HALIGN=rs.getInt("HTML_HALIGN");
                    eb.HTML_WRAP=rs.getInt("HTML_WRAP");
                    eb.HTML_FOREGROUND=rs.getInt("HTML_FOREGROUND");
                    eb.HTML_FOREGROUND_R=rs.getInt("HTML_FOREGROUND_R");
                    eb.HTML_FOREGROUND_G=rs.getInt("HTML_FOREGROUND_G");
                    eb.HTML_FOREGROUND_B=rs.getInt("HTML_FOREGROUND_B");
                    eb.HTML_BACKGROUND=rs.getInt("HTML_BACKGROUND");
                    eb.HTML_BACKGROUND_R=rs.getInt("HTML_BACKGROUND_R");
                    eb.HTML_BACKGROUND_G=rs.getInt("HTML_BACKGROUND_G");
                    eb.HTML_BACKGROUND_B=rs.getInt("HTML_BACKGROUND_B");
                    eb.HTML_STYLE=rs.getInt("HTML_STYLE");
                    eb.HTML_FONTSIZE=rs.getInt("HTML_FONTSIZE");
                    eb.HTML_BOLD=rs.getInt("HTML_BOLD");
                    eb.HTML_ITALIC=rs.getInt("HTML_ITALIC");
                    eb.HTML_UNDERLINE=rs.getInt("HTML_UNDERLINE");
                    eb.OBJECT_TAG=rs.getString("OBJECT_TAG");
                    eb.OBJECT_HASBORDER=rs.getInt("OBJECT_HASBORDER");
                    eb.OBJECT_SIMPLEBODY=rs.getInt("OBJECT_SIMPLEBODY");
                    eb.OBJECT_EXCELCSV=rs.getInt("OBJECT_EXCELCSV");
                    eb.OBJECT_IDANALISYS=rs.getInt("OBJECT_IDANALISYS");
                    eb.OBJECT_USEPLUGIN=rs.getInt("OBJECT_USEPLUGIN");
                    eb.OBJECT_PLUGINNAME=rs.getString("OBJECT_PLUGINNAME");
                    if(eb.OBJECT_PLUGINNAME==null)
                        eb.OBJECT_PLUGINNAME="";
                    eb.OBJECT_DEFAULT_FOREGROUND_R=rs.getInt("OBJECT_DEFAULT_FOREGROUND_R");
                    eb.OBJECT_DEFAULT_FOREGROUND_G=rs.getInt("OBJECT_DEFAULT_FOREGROUND_G");
                    eb.OBJECT_DEFAULT_FOREGROUND_B=rs.getInt("OBJECT_DEFAULT_FOREGROUND_B");
                    eb.OBJECT_DEFAULT_BACKGROUND_R=rs.getInt("OBJECT_DEFAULT_BACKGROUND_R");
                    eb.OBJECT_DEFAULT_BACKGROUND_G=rs.getInt("OBJECT_DEFAULT_BACKGROUND_G");
                    eb.OBJECT_DEFAULT_BACKGROUND_B=rs.getInt("OBJECT_DEFAULT_BACKGROUND_B");
                    eb.HEADER_USERLABEL=rs.getString("HEADER_USERLABEL");
                    if(eb.HEADER_USERLABEL==null)
                        eb.HEADER_USERLABEL="";
                    eb.HEADER_VALUE=rs.getInt("HEADER_VALUE");
                    eb.HEADER_USERVALUE=rs.getString("HEADER_USERVALUE");
                    if(eb.HEADER_USERVALUE==null)
                        eb.HEADER_USERVALUE="";
                    eb.HEADER_ISURL=rs.getInt("HEADER_ISURL");
                    eb.HEADER_LINE=rs.getInt("HEADER_LINE");
                    eb.BODY_SOURCE=rs.getInt("BODY_SOURCE");
                    eb.BODY_IDKEY=rs.getInt("BODY_IDKEY");
                    eb.BODY_REPEATKEY=rs.getInt("BODY_REPEATKEY");
                    eb.BODY_IDCOUNTER=rs.getInt("BODY_IDCOUNTER");
                    eb.BODY_TAGCOUNTER=rs.getString("BODY_TAGCOUNTER");
                    if(eb.BODY_TAGCOUNTER==null)
                        eb.BODY_TAGCOUNTER="";
                    eb.BODY_IDSCRIPT=rs.getInt("BODY_IDSCRIPT");
                    eb.BODY_PLUGIN_TAG=rs.getString("BODY_PLUGIN_TAG");
                    if(eb.BODY_PLUGIN_TAG==null)
                        eb.BODY_PLUGIN_TAG="";
                    eb.BODY_PLUGIN_NAME=rs.getString("BODY_PLUGIN_NAME");
                    if(eb.BODY_PLUGIN_NAME==null)
                        eb.BODY_PLUGIN_NAME="";
                    eb.BODY_USERVALUE=rs.getString("BODY_USERVALUE");
                    if(eb.BODY_USERVALUE==null)
                        eb.BODY_USERVALUE="";
                    eb.BODY_ISURL=rs.getInt("BODY_ISURL");
                    eb.BODY_TOTALIZER=rs.getInt("BODY_TOTALIZER");
                    eb.BODY_LINE=rs.getInt("BODY_LINE");
                    eb.BODY_MAXDECIMALDIGIT=rs.getInt("BODY_MAXDECIMALDIGIT");
                    eb.TOTALIZER_TRIGGER=rs.getInt("TOTALIZER_TRIGGER");
                    eb.TOTALIZER_LABEL=rs.getString("TOTALIZER_LABEL");
                    if(eb.TOTALIZER_LABEL==null)
                        eb.TOTALIZER_LABEL="";
                    eb.TOTALIZER_REDRAWHEADER=rs.getInt("TOTALIZER_REDRAWHEADER");
                    eb.TOTALIZER_BORDERAROUND=rs.getInt("TOTALIZER_BORDERAROUND");
                    eb.TOTALIZER_LINE=rs.getInt("TOTALIZER_LINE");
                    eb.TOTALIZER_USESCRIPT=rs.getInt("TOTALIZER_USESCRIPT");
                    eb.TOTALIZER_IDSCRIPT=rs.getInt("TOTALIZER_IDSCRIPT");
                    eb.FOOTER_SOURCE=rs.getInt("FOOTER_SOURCE");
                    eb.FOOTER_LABEL=rs.getString("FOOTER_LABEL");
                    if(eb.FOOTER_LABEL==null)
                        eb.FOOTER_LABEL="";
                    eb.FOOTER_USERVALUE=rs.getString("FOOTER_USERVALUE");
                    if(eb.FOOTER_USERVALUE==null)
                        eb.FOOTER_USERVALUE="";
                    eb.FOOTER_ISURL=rs.getInt("FOOTER_ISURL");
                    eb.FOOTER_LINE=rs.getInt("FOOTER_LINE");
                    //eb.scriptBODY=null;
                    //eb.flagCOMMIT_BODY=0;
                    //eb.flagCOMMIT_TOTALIZER=0;
                    //eb.scriptTOTALIZER=null;
                    eb.POSIZIONE=rs.getInt("POSIZIONE");
                    
                    vElementoBase.addElement(eb);
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ReportObject [readAllObject] ----> rs==null");
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
    
    public void readAllTypeReport()
    {
        String strSelect="select distinct TAG,TIPO_DI_CONFIGURAZIONE,IDREPORTSCHEMA from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' ORDER BY TAG"; //IDREPORTSCHEMA,IDOBJECT,TYPE
        
        if(DEBUG)
            System.out.println("ReportObject [readAllTypeReport] ----> "+strSelect);

        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        elementoBase e=new elementoBase();
        e.TIPO_DI_CONFIGURAZIONE=config_NAME_ALIAS;
        e.IDREPORTSCHEMA=-2;
        e.IDOBJECT=-2;
        e.TYPE=-1;
        e.SUB_TYPE=-1;
        e.TAG="<none selected>";            
        vReport.add(e);
        
        e=new elementoBase();
        e.TIPO_DI_CONFIGURAZIONE=config_NAME_ALIAS;
        e.IDREPORTSCHEMA=-1;
        e.IDOBJECT=-1;
        e.TYPE=-1;
        e.SUB_TYPE=-1;
        e.TAG="<Add new Report Schema>";            
        vReport.add(e);
        
        /*e=new elementoBase();
        e.TIPO_DI_CONFIGURAZIONE=config_NAME_ALIAS;
        e.IDREPORTSCHEMA=-1;
        e.IDOBJECT=-1;
        e.TYPE=-1;
        e.TAG="STANDARD";            
        vReport.add(e);
        */
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {

                    e=new elementoBase();
                    e.TIPO_DI_CONFIGURAZIONE=config_NAME_ALIAS;
                    e.IDREPORTSCHEMA=rs.getInt("IDREPORTSCHEMA");
                    //e.IDOBJECT=rs.getInt("IDOBJECT");
                    //e.TYPE=rs.getInt("TYPE");
                    e.TAG=rs.getString("TAG");
                    
                    vReport.add(e);
                    
                    if(DEBUG)
                    {
                        System.out.println("TIPO_DI_CONFIGURAZIONE ->"+e.TIPO_DI_CONFIGURAZIONE);
                        System.out.println("IDREPORTSCHEMA ->"+e.IDREPORTSCHEMA);
                        System.out.println("IDOBJECT ->"+e.IDOBJECT);
                        System.out.println("TYPE ->"+e.TYPE);
                        System.out.println("TAG ->"+e.TAG);
                        System.out.println();
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
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
        
    }
    
    public ReportObject(String config_NAME_ALIAS) 
    {    
        //System.out.println("[ReportObject_1]-ReportObject-config_NAME_ALIAS ("+config_NAME_ALIAS+")");
        this.config_NAME_ALIAS=config_NAME_ALIAS;
    }
    
    public ReportObject(String config_NAME_ALIAS,String NOME_SCHEMA) 
    {    
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        this.NOME_SCHEMA=NOME_SCHEMA;
        //System.out.println("[ReportObject_2]-ReportObject-config_NAME_ALIAS ("+config_NAME_ALIAS+")");
        //System.out.println("[ReportObject_2]-ReportObject-NOME_SCHEMA ("+NOME_SCHEMA+")");
    }
    
    public void addElement(elementoBase e)
    {
        vElementoBase.addElement(e);
    }
    
    public void addElementTOREMOVE(elementoBase e)
    {
        vElementoBaseTOREMOVE.addElement(e);
    }
    
    
    public int isPresentReport(String str)
    {
        int len=vReport.size();
              
        for(int i=0;i<len;i++)
        {
            elementoBase e=(elementoBase)vReport.elementAt(i);
            if(e.TAG.equals(str))
            {
                return e.IDREPORTSCHEMA;
            }
        }
        return -1;
    }
    
    public void setVectorObject(Vector v)
    {
        for(int i=0;i<v.size();i++)
        {
            vElementoBase.addElement((elementoBase)v.elementAt(i));
        }
    }
    
    public void setReport(elementoBase e)
    {
        vReport.addElement(e);
    }
    
    public boolean commit(int idSchema)
    {
        /************************** DELETE **********************/
        int lenREM=vElementoBaseTOREMOVE.size();
        for(int i=0;i<lenREM;i++)
        {
            elementoBase ebTOREMOVE=(elementoBase)vElementoBaseTOREMOVE.elementAt(i);
            
            String strSelectTOREMOVE="delete from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND IDREPORTSCHEMA="+ebTOREMOVE.IDREPORTSCHEMA+" and IDOBJECT="+ebTOREMOVE.IDOBJECT;
            int iRetTOREMOVE = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelectTOREMOVE);

            if(DEBUG)
            {
                System.out.println("strSelectTOREMOVE= "+strSelectTOREMOVE);
                System.out.println("iRetTOREMOVE= "+iRetTOREMOVE);
            }
        }
        
        vElementoBaseTOREMOVE.removeAllElements();
        
        
        /************************** INSERT/MODIFY **********************/

        //boolean DEBUG=true;
        //se sto qui vuol dire che tutt il ciborio è andato a meraviglia.
        int len=vElementoBase.size();
        //System.out.println("LEN---->"+len);
        String strSelect="";
        
        elementoBase hr=(elementoBase)vHeaderReport.elementAt(0);

        //System.out.println("deleteReport---->"+hr.TIPO_DI_CONFIGURAZIONE+","+ADAMS_GlobalParam.lastReportName);
        //deleteReport(hr.TIPO_DI_CONFIGURAZIONE,ADAMS_GlobalParam.lastReportName); //hr.TAG;
        //
        
        
        if(len==0)
                len=1;
        
        int _IDREPORTSCHEMA=-1;
        int _IDOBJECT=-1;
        
        if(idSchema==-1)
        {
            String strSelectSeq="select IDREPORTSCHEMA_seq.nextVal from dual";
            _IDREPORTSCHEMA = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectSeq);
        }else
        {
            _IDREPORTSCHEMA=idSchema;
            
        }
        
        String strSelectDeleteNULL="delete tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND IDREPORTSCHEMA="+_IDREPORTSCHEMA+" AND OBJECT_TAG='null'";
        if(DEBUG)
        {
            System.out.println("strSelectDeleteNULL= "+strSelectDeleteNULL);
        }
        int rRetNULL = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelectDeleteNULL);
            
        //System.out.println("Len= "+len);   
        for(int i=0;i<len;i++)
        {
            elementoBase eb=null;
            int type=-1;
            if(vElementoBase.size()==0)
            {
                eb=new elementoBase();
                type=ELEMENTO_COMUNE;
            }else
            {
                eb=(elementoBase)vElementoBase.elementAt(i);
                type=eb.TYPE;
            }
            

            //System.out.println("fuori eb.OBJECT_TAG=["+eb.OBJECT_TAG+"]");
            if(eb.OBJECT_TAG!=null)
            {
                //System.out.println("dentro eb.OBJECT_TAG=["+eb.OBJECT_TAG+"]");
                if(eb.OBJECT_TAG.equals("null"))
                {
                    //System.out.println("continue");
                    continue;
                }
            }
            
            
            strSelect="update tab_scripts set FLAG_VALIDATE=0 where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+eb.BODY_IDSCRIPT;
            int iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);

            if(iRet<=0)
            {
                if(DEBUG)
                    System.out.println("Lo flad dello SCRIPT_ID(BODY)= "+eb.BODY_IDSCRIPT+" non è stata cambiata.");
            }
            
            strSelect="update tab_scripts set FLAG_VALIDATE=0 where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+eb.TOTALIZER_IDSCRIPT;
            iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);

            if(iRet<=0)
            {
                if(DEBUG)
                    System.out.println("Lo flad dello SCRIPT_ID(TOTALIZER)= "+eb.BODY_IDSCRIPT+" non è stata cambiata.");
            }
            
            boolean esiste=false;
            
            if(DEBUG)
            {
                System.out.println("IDOBJECT= "+eb.IDOBJECT);
            }
            
            eb.IDREPORTSCHEMA=_IDREPORTSCHEMA;
            
            if(eb.IDOBJECT!=-1)
            {
                strSelect="select count(*) from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND IDREPORTSCHEMA="+eb.IDREPORTSCHEMA+" and IDOBJECT="+eb.IDOBJECT;
                int numObj = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);

                if(DEBUG)
                {
                    System.out.println("strSelect= "+strSelect);
                    System.out.println("numObj= "+numObj);
                }
                
                if(numObj>0)
                    esiste=true;
                else
                    esiste=false;
                        
            }else
            {
                esiste=false;
            }
            
             
            if(esiste==false)
            {
                strSelect="select IDOBJECT_seq.nextVal from dual";
                _IDOBJECT  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
                if(DEBUG)
                {
                    System.out.println("Elemento nuovo= "+_IDOBJECT);
                }
                eb.IDOBJECT=_IDOBJECT;
                strSelect="insert intotab_report values ('"+hr.TIPO_DI_CONFIGURAZIONE+"',"+ _IDREPORTSCHEMA +","+_IDOBJECT+","+type +","+eb.SUB_TYPE+",'"+hr.TAG +"',"+ eb.HTML_VSIZE +","+ eb.HTML_HSIZE +","+ eb.HTML_VALIGN +","+ eb.HTML_HALIGN +","+ eb.HTML_WRAP +","+ eb.HTML_FOREGROUND +","+ eb.HTML_FOREGROUND_R +","+ eb.HTML_FOREGROUND_G +","+ eb.HTML_FOREGROUND_B +","+ eb.HTML_BACKGROUND +","+ eb.HTML_BACKGROUND_R +","+ eb.HTML_BACKGROUND_G +","+ eb.HTML_BACKGROUND_B +","+ eb.HTML_STYLE +","+ eb.HTML_FONTSIZE+","+ eb.HTML_BOLD +","+ eb.HTML_ITALIC +","+ eb.HTML_UNDERLINE +",'"+ eb.OBJECT_TAG +"',"+ hr.OBJECT_HASBORDER +","+ hr.OBJECT_SIMPLEBODY +","+ hr.OBJECT_EXCELCSV +","+ hr.OBJECT_IDANALISYS +","+ hr.OBJECT_USEPLUGIN +",'"+ hr.OBJECT_PLUGINNAME +"',"+ hr.OBJECT_DEFAULT_FOREGROUND_R +","+ hr.OBJECT_DEFAULT_FOREGROUND_G +","+ hr.OBJECT_DEFAULT_FOREGROUND_B +","+ hr.OBJECT_DEFAULT_BACKGROUND_R +","+ hr.OBJECT_DEFAULT_BACKGROUND_G +","+ hr.OBJECT_DEFAULT_BACKGROUND_B +",'"+ eb.HEADER_USERLABEL +"',"+ eb.HEADER_VALUE +",'"+ eb.HEADER_USERVALUE +"',"+ eb.HEADER_ISURL +","+ eb.HEADER_LINE +","+ eb.BODY_SOURCE +","+ eb.BODY_IDKEY +","+ eb.BODY_IDCOUNTER +",'"+ eb.BODY_TAGCOUNTER +"',"+ eb.BODY_IDSCRIPT +",'"+ eb.BODY_PLUGIN_TAG +"','"+ eb.BODY_PLUGIN_NAME +"','"+ eb.BODY_USERVALUE +"',"+ eb.BODY_ISURL +","+ eb.BODY_TOTALIZER +","+ eb.BODY_LINE +","+eb.BODY_MAXDECIMALDIGIT+","+ eb.TOTALIZER_TRIGGER +",'"+ eb.TOTALIZER_LABEL +"',"+ eb.TOTALIZER_REDRAWHEADER+","+ eb.TOTALIZER_BORDERAROUND +","+ eb.TOTALIZER_LINE +","+ eb.TOTALIZER_USESCRIPT +","+ eb.TOTALIZER_IDSCRIPT +","+ eb.FOOTER_SOURCE +",'"+ eb.FOOTER_LABEL +"','"+ eb.FOOTER_USERVALUE +"',"+ eb.FOOTER_ISURL +","+ eb.FOOTER_LINE+","+eb.POSIZIONE+","+eb.BODY_REPEATKEY+")";
            }else if(esiste==true)
            {
                strSelect="delete tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND IDREPORTSCHEMA="+eb.IDREPORTSCHEMA+" and IDOBJECT="+eb.IDOBJECT;
                if(DEBUG)
                {
                    System.out.println("Elemento esistente= "+eb.IDOBJECT);
                    System.out.println("strSelecte= "+strSelect);
                }
                int rRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
                //if(rRet>0)
                //{
                    strSelect="insert intotab_report values ('"+hr.TIPO_DI_CONFIGURAZIONE+"',"+ eb.IDREPORTSCHEMA +","+eb.IDOBJECT+","+type +","+eb.SUB_TYPE+",'"+hr.TAG +"',"+ eb.HTML_VSIZE +","+ eb.HTML_HSIZE +","+ eb.HTML_VALIGN +","+ eb.HTML_HALIGN +","+ eb.HTML_WRAP +","+ eb.HTML_FOREGROUND +","+ eb.HTML_FOREGROUND_R +","+ eb.HTML_FOREGROUND_G +","+ eb.HTML_FOREGROUND_B +","+ eb.HTML_BACKGROUND +","+ eb.HTML_BACKGROUND_R +","+ eb.HTML_BACKGROUND_G +","+ eb.HTML_BACKGROUND_B +","+ eb.HTML_STYLE +","+ eb.HTML_FONTSIZE+","+ eb.HTML_BOLD +","+ eb.HTML_ITALIC +","+ eb.HTML_UNDERLINE +",'"+ eb.OBJECT_TAG +"',"+ hr.OBJECT_HASBORDER +","+ hr.OBJECT_SIMPLEBODY +","+ hr.OBJECT_EXCELCSV +","+ hr.OBJECT_IDANALISYS +","+ hr.OBJECT_USEPLUGIN +",'"+ hr.OBJECT_PLUGINNAME +"',"+ hr.OBJECT_DEFAULT_FOREGROUND_R +","+ hr.OBJECT_DEFAULT_FOREGROUND_G +","+ hr.OBJECT_DEFAULT_FOREGROUND_B +","+ hr.OBJECT_DEFAULT_BACKGROUND_R +","+ hr.OBJECT_DEFAULT_BACKGROUND_G +","+ hr.OBJECT_DEFAULT_BACKGROUND_B +",'"+ eb.HEADER_USERLABEL +"',"+ eb.HEADER_VALUE +",'"+ eb.HEADER_USERVALUE +"',"+ eb.HEADER_ISURL +","+ eb.HEADER_LINE +","+ eb.BODY_SOURCE +","+ eb.BODY_IDKEY +","+ eb.BODY_IDCOUNTER +",'"+ eb.BODY_TAGCOUNTER +"',"+ eb.BODY_IDSCRIPT +",'"+ eb.BODY_PLUGIN_TAG +"','"+ eb.BODY_PLUGIN_NAME +"','"+ eb.BODY_USERVALUE +"',"+ eb.BODY_ISURL +","+ eb.BODY_TOTALIZER +","+ eb.BODY_LINE +","+eb.BODY_MAXDECIMALDIGIT+","+ eb.TOTALIZER_TRIGGER +",'"+ eb.TOTALIZER_LABEL +"',"+ eb.TOTALIZER_REDRAWHEADER+","+ eb.TOTALIZER_BORDERAROUND +","+ eb.TOTALIZER_LINE +","+ eb.TOTALIZER_USESCRIPT +","+ eb.TOTALIZER_IDSCRIPT +","+ eb.FOOTER_SOURCE +",'"+ eb.FOOTER_LABEL +"','"+ eb.FOOTER_USERVALUE +"',"+ eb.FOOTER_ISURL +","+ eb.FOOTER_LINE+","+eb.POSIZIONE+","+eb.BODY_REPEATKEY+")";
                    
                //}
            } 
            
            iRet  = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
            
            if(DEBUG)
            {
               System.out.println("eb.BODY_IDSCRIPT= "+eb.BODY_IDSCRIPT+"    TOTALIZER_IDSCRIPT= "+eb.TOTALIZER_IDSCRIPT);
               System.out.println("INSERT "+i+" di "+vElementoBase.size()+": "+strSelect);
               System.out.println("Return "+iRet);
            }

        }
        //"insert intotab_report values()";

        return true;
    }
    
    public void deleteObj(elementoBase ebTrovato)
    {
        boolean flagOK=true;
        if(ebTrovato.BODY_IDSCRIPT>0)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Lo script ["+ebTrovato.BODY_IDSCRIPT+"] associato, verrà cancellato.Vuoi Continuare?","Info",6);
            int Yes_No = op.getAnswer();

            if(Yes_No == 1)
            {

                if(DEBUG)
                {
                    System.out.println("[deleteReport()]-->SCRIPT_ID(BODY)= "+ebTrovato.BODY_IDSCRIPT);
                }

                String strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+ebTrovato.BODY_IDSCRIPT;
                int iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);

                if(iRet<=0)
                {
                    if(DEBUG)
                        System.out.println("Lo SCRIPT_ID(BODY)= "+ebTrovato.BODY_IDSCRIPT+" non è stato cancellato.");
                }
                
                flagOK=true;
            }else
            {
                flagOK=false;
            }
        }
        
        if(ebTrovato.TOTALIZER_IDSCRIPT>0)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Lo script ["+ebTrovato.TOTALIZER_IDSCRIPT+"] associato, verrà cancellato.Vuoi Continuare?","Info",6);
            int Yes_No = op.getAnswer();

            if(Yes_No == 1)
            {
                if(DEBUG)
                {
                    System.out.println("[deleteReport()]-->SCRIPT_ID(TOTALIZER)= "+ebTrovato.TOTALIZER_IDSCRIPT);
                }
                String strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+ebTrovato.TOTALIZER_IDSCRIPT;
                int iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
                if(iRet<=0)
                {
                    if(DEBUG)
                        System.out.println("Lo SCRIPT SCRIPT_ID(TOTALIZER)= "+ebTrovato.TOTALIZER_IDSCRIPT+" non è stato cancellato.");
                }
                flagOK=true;
            }else
            {
                flagOK=false;
            }
        }
        
        if(flagOK)
            vElementoBase.remove((elementoBase)ebTrovato);
        
    }
    
    
    public boolean deleteReport()
    {
        String strSelectID="select distinct IDREPORTSCHEMA from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND TAG='"+NOME_SCHEMA+"'";
        int _IDREPORTSCHEMA  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectID);
        int iRet;
        
        String strSelect="delete from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND TAG='"+NOME_SCHEMA+"'";
        
        if(DEBUG)
        {
            System.out.println("[deleteReport()]-->config_NAME_ALIAS="+config_NAME_ALIAS);
            System.out.println("[deleteReport()]-->NOME_SCHEMA="+NOME_SCHEMA);
            System.out.println("[deleteReport()]-->ReportObject [deleteReport]="+strSelect);
        }
        
        int rs  = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
        
        if(DEBUG)
        {
            System.out.println("[deleteReport()]-->Record deleted= "+rs);
        }
        
        int len0=vElementoBase.size();
        for(int i=0;i<len0;i++)
        {
            elementoBase eb=(elementoBase)vElementoBase.elementAt(i);
            
            if(eb.BODY_IDSCRIPT>0)
            {
                if(DEBUG)
                {
                    System.out.println("[deleteReport()]-->SCRIPT_ID(BODY)= "+eb.BODY_IDSCRIPT);
                }

                strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+eb.BODY_IDSCRIPT;
                iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);

                if(iRet<=0)
                {
                    if(DEBUG)
                        System.out.println("Lo SCRIPT_ID(BODY)= "+eb.BODY_IDSCRIPT+" non è stato cancellato.");
                }
            }
            
            if(eb.TOTALIZER_IDSCRIPT>0)
            {
                if(DEBUG)
                {
                    System.out.println("[deleteReport()]-->SCRIPT_ID(TOTALIZER)= "+eb.TOTALIZER_IDSCRIPT);
                }
                strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+eb.TOTALIZER_IDSCRIPT;
                iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
                if(iRet<=0)
                {
                    if(DEBUG)
                        System.out.println("Lo SCRIPT SCRIPT_ID(TOTALIZER)= "+eb.TOTALIZER_IDSCRIPT+" non è stato cancellato.");
                }
            }
        }
        
        int len1=vReport.size();
        for(int i=0;i<len1;i++)
        {
            elementoBase e=(elementoBase)vReport.elementAt(i);
            if(e.TAG.equals(NOME_SCHEMA))
            {
                vReport.removeElementAt(i);
                break;
            }
        }
        
        getAnalisi();
        int len=V_Analysis.size();
        boolean trovato = false;
        
        
        //System.out.println("[deleteReport()]-->strSelectID="+strSelectID);
        //System.out.println("[deleteReport()]-->Da cercare IDREPORT="+_IDREPORTSCHEMA);
        
        for(int i=0;i<len;i++)
        {
            ADAMS_TAB_TYPE_ANALISI rowAnalisi=(ADAMS_TAB_TYPE_ANALISI)V_Analysis.elementAt(i);
            int len2=rowAnalisi.LISTA_REPORT.size();
            for(int j=0;j<len2;j++)
            {
                int idReport = ((Integer)rowAnalisi.LISTA_REPORT.elementAt(j)).intValue();
                if(_IDREPORTSCHEMA==idReport)
                {
                    rowAnalisi.LISTA_REPORT.removeElementAt(j);
                    trovato=true;
                    rowAnalisi.update_Analysis();
                    break;
                }
            }
        }

        ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(config_NAME_ALIAS);
        return true;
    }
    
    
    public boolean deleteReport(String s1,String s2)
    {
        String strSelect="delete from tab_report where TIPO_DI_CONFIGURAZIONE='"+s1+"' AND TAG='"+s2+"'";
        int iRet;
        
        if(true)
        {
            System.out.println("ReportObject [deleteReport] ---> "+strSelect);
        }
        
        int rs  = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
        

        int len0=vElementoBase.size();
        /*for(int i=0;i<len0;i++)
        {
            elementoBase eb=(elementoBase)vElementoBase.elementAt(i);
            if(DEBUG)
            {
                System.out.println("[deleteReport()]-->SCRIPT_ID(BODY)= "+eb.BODY_IDSCRIPT+"   SCRIPT_ID(TOTALIZER)="+eb.TOTALIZER_IDSCRIPT);
            }
            
            strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+eb.BODY_IDSCRIPT;
            iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
            if(iRet<=0)
            {
                if(DEBUG)
                    System.out.println("Lo SCRIPT BODY-("+eb.BODY_IDSCRIPT+") non è stato cancellato.");
            }
            
            strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+eb.TOTALIZER_IDSCRIPT;
            iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
            if(iRet<=0)
            {
                if(DEBUG)
                    System.out.println("Lo SCRIPT TOTALIZER-("+eb.TOTALIZER_IDSCRIPT+") non è stato cancellato.");
            }
        }*/
        
        int len1=vReport.size();
        for(int i=0;i<len1;i++)
        {
            elementoBase e=(elementoBase)vReport.elementAt(i);
        
            if(e.TAG.equals(NOME_SCHEMA))
            {
        
                vReport.removeElementAt(i);
                break;
            }
        }
        
        if(DEBUG)
        {
            System.out.println("Record deleted---> "+rs);
        }
        
        ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(config_NAME_ALIAS);
        return true;
    }
    
    public boolean cloneReport()
    {
        return true;
    }
    
    public void moveUP(int idElemento, int type)
    {
        int pos=-1;
        
        Vector vAppo=getVectorObject(type);
        int len=vAppo.size();
        
        elementoBase el=null,elPrecedente=null;
        
        for(int i=0;i<len;i++)
        {
            el=(elementoBase)vAppo.elementAt(i);
            if(el.idGUI==idElemento)
            {
                pos=i;
                break;
            }
        }
        
        System.out.println("Elemento trovato alla posizione="+pos);
        if(pos>0)
        {
            elPrecedente=(elementoBase)vAppo.elementAt(pos-1);
            
            if(elPrecedente.TYPE==type)
            {
                int temp=el.POSIZIONE;
                el.POSIZIONE=elPrecedente.POSIZIONE;
                elPrecedente.POSIZIONE=temp;

                int len_1=vElementoBase.size();
                int pos_org=0,pos_dest=0;
                elementoBase el_1=null,elPrecedente_1=null;
                
                for(int i=0;i<len_1;i++)
                {
                    el_1=(elementoBase)vElementoBase.elementAt(i);
                    if(el_1.idGUI==el.idGUI)
                    {
                        pos_org=i;
                        break;
                    }
                }     
                
                for(int i=0;i<len_1;i++)
                {
                    elPrecedente_1=(elementoBase)vElementoBase.elementAt(i);
                    if(elPrecedente_1.idGUI==elPrecedente.idGUI)
                    {
                        pos_dest=i;
                        break;
                    }
                }     
                
                System.out.println("OK, posso fare l'up di una posizione");
                vElementoBase.setElementAt(el,pos_dest);
                vElementoBase.setElementAt(elPrecedente,pos_org);
            }
        }
    }
    
    public void moveDown(int idElemento, int type)
    {
        int pos=-1;
        
        Vector vAppo=getVectorObject(type);
        int len=vAppo.size();
        
        elementoBase el=null,elSuccessivo=null;
        
        for(int i=0;i<len;i++)
        {
            el=(elementoBase)vAppo.elementAt(i);
            if(el.idGUI==idElemento)
            {
                pos=i;
                break;
            }
        }
        
        System.out.println("Elemento trovato alla posizione="+pos);
        if(pos<len-1)
        {
            elSuccessivo=(elementoBase)vAppo.elementAt(pos+1);
            if(elSuccessivo.TYPE==type)
            {
                int temp=el.POSIZIONE;
                el.POSIZIONE=elSuccessivo.POSIZIONE;
                elSuccessivo.POSIZIONE=temp;
                
                int len_1=vElementoBase.size();
                int pos_org=0,pos_dest=0;
                elementoBase el_1=null,elSuccessivo_1=null;
                
                for(int i=0;i<len_1;i++)
                {
                    el_1=(elementoBase)vElementoBase.elementAt(i);
                    if(el_1.idGUI==el.idGUI)
                    {
                        pos_org=i;
                        break;
                    }
                }     
                
                for(int i=0;i<len_1;i++)
                {
                    elSuccessivo_1=(elementoBase)vElementoBase.elementAt(i);
                    if(elSuccessivo_1.idGUI==elSuccessivo.idGUI)
                    {
                        pos_dest=i;
                        break;
                    }
                }     
                
                System.out.println("OK, posso fare l'up di una posizione");
                vElementoBase.setElementAt(el,pos_dest);
                vElementoBase.setElementAt(elSuccessivo,pos_org);
            }
        }
        
        
    }
    
    public Vector getVectorObject(int type)
    {
        int len=vElementoBase.size();
        Vector appo=new Vector();
        for(int i=0;i<len;i++)
        {
            
            elementoBase el=(elementoBase)vElementoBase.elementAt(i);
            //System.out.println("el.TYPE="+el.TYPE);
            if(el.TYPE==type)
            {
                appo.addElement(el);
            }
        }
        
        return appo;
    }
    
    public Vector getReport()
    {
        return vReport;
    }
    
    public elementoBase getHeaderReport()
    {
        if(vHeaderReport.size()!=0)
            return (elementoBase)vHeaderReport.elementAt(0);
        else
            return null;
    }
    
    public void resetAll()
    {
        vReport.removeAllElements();
        vElementoBase.removeAllElements();
    }
    
    public int size()
    {
        return vElementoBase.size();
    }
    
    public elementoBase getElementoBase(int idObject)
    {
        int len=vElementoBase.size();
        for(int i=0;i<len;i++)
        {
            elementoBase el=(elementoBase)vElementoBase.elementAt(i);
            //if(el.IDOBJECT==idObject)
            if(el.idGUI==idObject)
                return el;
        }
        return null;
    }
    
    public void resetObject(int typeObj)
    {
        int len=vElementoBase.size();
        Vector vElementoBaseAPPO=new Vector();
        
        for(int i=0;i<len;i++)
        {
            elementoBase el=(elementoBase)vElementoBase.elementAt(i);
           
            if(el.TYPE==typeObj)
            {
                //vElementoBase.removeElement((elementoBase)el);
            }else
            {
                vElementoBaseAPPO.addElement(el);
            }
        }
        
        vElementoBase=vElementoBaseAPPO;
    }
    
    public boolean presenteQualcheObj()
    {
        for(int i=0;i<vElementoBase.size();i++)
        {
            elementoBase el=(elementoBase)vElementoBase.elementAt(i);
           
            if(el.TYPE!=0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public Vector vGetElementoBase()
    {
        return vElementoBase;
    }
    
    public void getAnalisi()
    {     
      
        String name_TabAnalisi = "tab_analysis_type";
        String item_TAB_TYPE_ANALISI = "IDANALISI,NOMEANALISI,IDCOUNTERKIT,FLAGSORT,FLAGDATA,FLAGCENTRALE,FLAGCUMULAZIONE"; 
        String sel_ReadAnalysis = "select "+item_TAB_TYPE_ANALISI+" from "+name_TabAnalisi+" where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"' order by NOMEANALISI";
        
        V_Analysis=new Vector();

        //System.out.println("sel_ReadAnalysis --> "+sel_ReadAnalysis);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ReadAnalysis,SQLStatement);


        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    ADAMS_TAB_TYPE_ANALISI row_Analysis = new ADAMS_TAB_TYPE_ANALISI();
                    row_Analysis.TIPO_DI_CONFIGURAZIONE = config_NAME_ALIAS;

                    row_Analysis.IDANALISI          = rs.getInt("IDANALISI");                   //not null sil DB

                    String nomeAnalisi = rs.getString("NOMEANALISI");
                    if(nomeAnalisi != null)
                        row_Analysis.NOMEANALISI = nomeAnalisi;

                    row_Analysis.IDCOUNTERKIT       = rs.getInt("IDCOUNTERKIT");
                    row_Analysis.FLAGSORT           = rs.getString("FLAGSORT").charAt(0);       //not null sil DB
                    row_Analysis.FLAGDATA           = rs.getString("FLAGDATA").charAt(0);       //not null sil DB
                    row_Analysis.FLAGCENTRALE       = rs.getString("FLAGCENTRALE").charAt(0);   //not null sil DB
                    row_Analysis.FLAGCUMULAZIONE    = rs.getString("FLAGCUMULAZIONE").charAt(0);//not null sil DB

                    // ------------- Nested LISTA_REPORT ------------- //
                    String[] nomeCampi      = {"IDREPORT"}; 
                    String linkTable  = "tab_list_report";

                    String selectReadNested = "select ";

                    for(int i=0;i<nomeCampi.length;i++)
                    {
                        if(i!=nomeCampi.length-1)
                            selectReadNested += nomeCampi[i]+",";
                        else
                            selectReadNested += nomeCampi[i]+" ";  
                    }

                    selectReadNested += " from " + linkTable;
                    selectReadNested += " where ";
                    selectReadNested += " TIPO_DI_CONFIGURAZIONE='"+row_Analysis.TIPO_DI_CONFIGURAZIONE+"' and IDANALISI="+row_Analysis.IDANALISI;

                    //System.out.println(" --> selectReadNested : "+selectReadNested);
                    Statement SQLStatement1 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rsNested  = ADAMS_GlobalParam.connect_Oracle.Query_RS(selectReadNested,SQLStatement1);
                    if(rsNested != null)
                    {
                        try
                        {
                            while (rsNested.next())
                            {
                                row_Analysis.LISTA_REPORT.addElement(new Integer(rsNested.getInt(nomeCampi[0])));
                            }
                        }catch (Exception ex) 
                        {
                            System.out.println(ex);
                        } 
                    }else
                    {
                        System.out.println("rsNested == null");
                    }
                    try
                    {
                        SQLStatement1.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("ERROR-SQL Operation " + exc.toString());
                    }

                    // ------------- End Nested LISTA_REPORT ------------- //

                    if(DEBUG)
                    {
                        System.out.println();
                        System.out.println("--- Analisi TIPO_DI_CONFIGURAZIONE :"+config_NAME_ALIAS+"---");
                        System.out.println("IDANALISI       ->"+row_Analysis.IDANALISI);
                        System.out.println("NOMEANALISI     ->"+row_Analysis.NOMEANALISI);        
                        System.out.println("IDCOUNTERKIT    ->"+row_Analysis.IDCOUNTERKIT);   
                        System.out.println("FLAGSORT        ->"+row_Analysis.FLAGSORT);
                        System.out.println("FLAGDATA        ->"+row_Analysis.FLAGDATA);          
                        System.out.println("FLAGCENTRALE    ->"+row_Analysis.FLAGCENTRALE);               
                        System.out.println("FLAGCUMULAZIONE ->"+row_Analysis.FLAGCUMULAZIONE);
                        System.out.println();
                    }
                    V_Analysis.addElement(row_Analysis);
                }
            }catch (Exception ex) 
            { 
                System.out.println(ex.toString());
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
    
    
}
