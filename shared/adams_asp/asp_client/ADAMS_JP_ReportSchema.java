/*
 * ADAMS_JP_ReportSchema.java
 *
 * Created on 4 agosto 2005, 11.56
 */

/**
 *
 * @author  Raffaele Ficcadenti
 * rficcad@e-tech.net
 */

import com.sun.java.swing.plaf.windows.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import javax.swing.tree.*;
import javax.swing.JTree;
import javax.swing.ImageIcon;
import java.awt.event.InputEvent;
import java.awt.Component;
import java.sql.*;
import javax.swing.JColorChooser;

public class ADAMS_JP_ReportSchema extends javax.swing.JPanel {

    private boolean                                 DEBUG                       = false;
    public int                                      nTest                       = -1;
    private boolean                                 TREE_ENABLED                = true;
    private boolean                                 flagNewReport               = false;
    private String                                  primaSTR                    = "";
    private String                                  dopoSTR                     = "";
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private nodoSummaryEXT                          radice                      = null;
    private JTree                                   jTree        		= new JTree();
    private final Color                             cSfondoTree                 = Color.white;
    private final Color                             cRamo1                      = Color.black;
    private final Color                             cRamo2                      = Color.black;
    private final Color                             cRamo2_sel                  = Color.blue;
    private nodoSummaryEXT                          header                      = null;
    private nodoSummaryEXT                          body                        = null;
    private nodoSummaryEXT                          totalizer                   = null;
    private nodoSummaryEXT                          footer                      = null;
    private int                                     levelSelected               = -1;
    //private TreePath path;          
    //private int pathCount;
    private Vector                                  vHeader                     = new Vector();
    private Vector                                  vBody                       = new Vector();
    private Vector                                  vTot                        = new Vector();
    private Vector                                  vFoot                       = new Vector();
    
    private Vector                                  vReport                     = null;
    public ADAMS_JF_Report                           jf_confReport               = null;
    private javax.swing.JTextArea                   jT_help                     = null;
    private ReportObject                            report                      = null;
    private ReportObject                            schemaAttuale               = null;
    private String                                  config_NAME_ALIAS           = null;
    private String                                  config_NAME_SCHEMA          = null;
    private Vector                                  listaAnalisi                = new Vector();
    private ADAMS_JP_Report_b1                       jpButton                    = null;
    private int                                     idSelezionato               = -1;
    private int                                     idObj                       = 0;
    private java.awt.event.ActionListener           evento                      = null;
    private java.awt.event.ActionListener           evento2                     = null;
    private int                                     idGUI                       = 0;
    public boolean                                  commitFatta                 = true;
    private ReportObject.elementoBase               hrINIZIALE                  = new ReportObject.elementoBase();
    private boolean                                 flagCLEARELEMENTS           = false;
    public  boolean                                 fattaDELETE                 = false;
    
    private class eAnalisi
    {
        public int     idAnalisi       = -1;
        public String  nomeAnalisi     = "";
        public int     pos             = -1;
        
        public eAnalisi(int p,int id,String str)
        {
            pos=p;
            idAnalisi=id;
            nomeAnalisi=str;
        }
    }
    
    public void setButtonPanel(ADAMS_JP_Report_b1 jpButton)
    {
            this.jpButton=jpButton;
            this.jpButton.enableCommit(false);
    }
    
    public void reloadReport()
    {
        report = new ReportObject(config_NAME_ALIAS);
        report.readAllTypeReport();
        vReport=report.getReport();
        
    }
    /** Creates new form ADAMS_JP_ReportSchema */
    public ADAMS_JP_ReportSchema(String config_NAME_ALIAS,String config_NAME_SCHEMA) {
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        this.config_NAME_SCHEMA=config_NAME_SCHEMA;
        initComponents();
        setFont();
        setCursorWidget();
        setTree();
        
        jT_report_name.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,30));
        jT_plugin_name.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,81));
        
        reloadReport();
        
        int len1=vReport.size();
        for(int i=0;i<len1;i++)
        {
            ReportObject.elementoBase e=(ReportObject.elementoBase)vReport.elementAt(i);
        }
        
        /*carico lengthanalisy configurate*/
        getAnalisys();
        
        /* cario i report configurati*/
        jC_report1.removeActionListener(evento2);
        for(int i=0;i<vReport.size();i++)
        {
            ReportObject.elementoBase e=(ReportObject.elementoBase)vReport.elementAt(i);
            jC_report1.addItem(e.TAG);
        }
        
        evento2=(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jC_report1ActionPerformed(evt);
                }
            });
        //jC_report1.addActionListener(evento2);
        
        evento=(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jC_report2ActionPerformed(evt);
                }
            });
        jC_report2.addActionListener(evento);   
        
            
        try{
            jC_report1.setSelectedIndex(0);
        }catch(Exception e){};
        
        ADAMS_GlobalParam.TAG_SELEZIONATO=(String)jC_report1.getSelectedItem();
        jC_report1.addActionListener(evento2);
     
    }
    
    public int getPosAnalisi(int idA)
    {
        int len=listaAnalisi.size();
        for(int i=0;i<len;i++)
        {
            eAnalisi appo=(eAnalisi)listaAnalisi.elementAt(i);
            if(appo.idAnalisi==idA)
            {  
                return i;
            }
        }
        
        
        return -1;
    }
    
    public int getIDAnalisi(int p)
    {
        int len=listaAnalisi.size();
        for(int i=0;i<len;i++)
        {
            eAnalisi appo=(eAnalisi)listaAnalisi.elementAt(i);
            if(appo.pos==p)
            {  
                return appo.idAnalisi;
            }
        }
        
        return -1;
    }
    
    
    public void getAnalisys()
    {
        int pos=1;
        /************** test*****************/
        eAnalisi eATEST;
        
        eATEST=new eAnalisi(0,0,"<None selected>");
        listaAnalisi.addElement(eATEST);
        jC_report2.addItem("<None selected>");
        
        /*eATEST=new eAnalisi(1,10,"STANDARD");
        listaAnalisi.addElement(eATEST);
        jC_report2.addItem("STANDARD");
        
        eATEST=new eAnalisi(2,30,"CALMON");
        listaAnalisi.addElement(eATEST);
        jC_report2.addItem("CALMON");
        
        eATEST=new eAnalisi(3,5,"CALDET");
        listaAnalisi.addElement(eATEST);
        jC_report2.addItem("CALDET");
        
        eATEST=new eAnalisi(4,11,"SEQAN");
        listaAnalisi.addElement(eATEST);
        jC_report2.addItem("SEQAN");*/

        /************************************/
        
        String strSelect="select IDANALISI,NOMEANALISI from tab_analysis_type where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"'";
        
        if(DEBUG)
            System.out.println("ADAMS_JP_ReportSchema [getAnalisys] ----> "+strSelect);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    int idAnalisi=rs.getInt("IDANALISI");
                    String nomeAnalisi=rs.getString("NOMEANALISI");
                    
                    eAnalisi eA=new eAnalisi(pos,idAnalisi,nomeAnalisi);
                    listaAnalisi.addElement(eA);
                    jC_report2.addItem(nomeAnalisi);
                    
                    pos++;
                    
                    if(DEBUG)
                    {
                        System.out.println("IDANALISI ->"+idAnalisi);
                        System.out.println("NOMEANALISI ->"+nomeAnalisi);
                        System.out.println();
                    }
                    
                    
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_JP_ReportSchema [getAnalisys] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_JP_ReportSchema -1) SQL Operation " + exc.toString());
        }
    }
    
    public void addListaReport(String str)
    {
        jC_report1.removeActionListener(evento2);
        jC_report1.addItem((String)str);
        //ordinaIndici();
        jC_report1.addActionListener(evento2);
    }
    
    
    public void resetInterface()
    {
        try{
            jC_report2.setSelectedIndex(0);
        }catch(Exception e){};
        
        jT_report_name.setText("<Add new Report Schema>");
        jR_use_plugin.setSelected(false);
        jLabel4.setEnabled(false);
        jT_plugin_name.setText("");
        jT_plugin_name.setEnabled(false);
        jB_edit.setEnabled(false);
        jR_plain.setSelected(false);
        jR_borderer.setSelected(false);
        //jR_excel.setSelected(false);
        jC_excel_new.setSelectedIndex(0);
        jB_b.setBackground(Color.white);
        jB_f.setBackground(Color.black);
        setTree();
        
        if(schemaAttuale!=null)
            schemaAttuale.resetAll();
        
        jB_b.setBackground(Color.white);
        jB_f.setBackground(Color.black);
    }
    
    public String getReportName()
    {
        //return jT_report_name.getText();
        return ((String)jC_report1.getSelectedItem());
    }
    
    public String getConfigName()
    {
        return config_NAME_ALIAS;
    }
    
    public void getInterface()
    {
        ReportObject.elementoBase hr=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
        hr.TIPO_DI_CONFIGURAZIONE=config_NAME_ALIAS;
        hr.TYPE=ReportObject.ELEMENTO_COMUNE;
        hr.TAG=jT_report_name.getText();
        hr.OBJECT_IDANALISYS=getIDAnalisi(jC_report2.getSelectedIndex());
        
        

        if(jR_use_plugin.isSelected())
        {
            hr.OBJECT_USEPLUGIN=1;
            hr.OBJECT_PLUGINNAME=jT_plugin_name.getText();
        }else
        {
            hr.OBJECT_USEPLUGIN=0;
            hr.OBJECT_PLUGINNAME="";
            jT_plugin_name.setText("");
        }
        
        if(jR_plain.isSelected())
        {
            hr.OBJECT_SIMPLEBODY=1;
        }else
        {
            hr.OBJECT_SIMPLEBODY=0;
        }
        
        if(jR_borderer.isSelected())
        {
            hr.OBJECT_HASBORDER=1;
        }else
        {
            hr.OBJECT_HASBORDER=0;
        }
        
        /*if(jR_excel.isSelected())
        {
            hr.OBJECT_EXCELCSV=1;
        }else
        {
            hr.OBJECT_EXCELCSV=0;
        }*/
        
        hr.OBJECT_EXCELCSV=jC_excel_new.getSelectedIndex();
        
        hr.OBJECT_DEFAULT_BACKGROUND_R=jB_b.getBackground().getRed();
        hr.OBJECT_DEFAULT_BACKGROUND_G=jB_b.getBackground().getGreen();
        hr.OBJECT_DEFAULT_BACKGROUND_B=jB_b.getBackground().getBlue();
        hr.OBJECT_DEFAULT_FOREGROUND_R=jB_f.getBackground().getRed();
        hr.OBJECT_DEFAULT_FOREGROUND_G=jB_f.getBackground().getGreen();
        hr.OBJECT_DEFAULT_FOREGROUND_B=jB_f.getBackground().getBlue();
    }
    
    public void setInterface2()
    {
        this.jpButton.enableCommit(true);
        vHeader=schemaAttuale.getVectorObject(ReportObject.HEADER);
        
        setHeaderCell(ReportObject.HEADER,vHeader);
        
        vBody=schemaAttuale.getVectorObject(ReportObject.BODY);
        setHeaderCell(ReportObject.BODY,vBody);
        
        vTot=schemaAttuale.getVectorObject(ReportObject.TOTALIZER);
        setHeaderCell(ReportObject.TOTALIZER,vTot);
        
        vFoot=schemaAttuale.getVectorObject(ReportObject.FOOTER);
        setHeaderCell(ReportObject.FOOTER,vFoot);
    }
    
    public void setInterface()
    {
        //sulla base di schemaAttuale
        
                
        ReportObject.elementoBase hr=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
        //hrINIZIALE=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
        
        hrINIZIALE.TIPO_DI_CONFIGURAZIONE=hr.TIPO_DI_CONFIGURAZIONE;
        hrINIZIALE.TYPE=hr.TYPE;
        hrINIZIALE.TAG=hr.TAG;
        hrINIZIALE.OBJECT_IDANALISYS=hr.OBJECT_IDANALISYS;
        hrINIZIALE.OBJECT_USEPLUGIN=hr.OBJECT_USEPLUGIN;
        hrINIZIALE.OBJECT_PLUGINNAME=hr.OBJECT_PLUGINNAME;
        hrINIZIALE.OBJECT_SIMPLEBODY=hr.OBJECT_SIMPLEBODY;
        hrINIZIALE.OBJECT_HASBORDER=hr.OBJECT_HASBORDER;
        hrINIZIALE.OBJECT_EXCELCSV=hr.OBJECT_EXCELCSV;
        hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_R=hr.OBJECT_DEFAULT_BACKGROUND_R;
        hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_G=hr.OBJECT_DEFAULT_BACKGROUND_G;
        hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_B=hr.OBJECT_DEFAULT_BACKGROUND_B;
        hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_R=hr.OBJECT_DEFAULT_FOREGROUND_R;
        hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_G=hr.OBJECT_DEFAULT_FOREGROUND_G;
        hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_B=hr.OBJECT_DEFAULT_FOREGROUND_B;
        
        if(DEBUG)
        {
            System.out.println("setInterface()");
            System.out.println("hr.TAG="+hr.TAG);
            System.out.println("hr.OBJECT_USEPLUGIN="+hr.OBJECT_USEPLUGIN);
            System.out.println("hrINIZIALE.TAG="+hrINIZIALE.TAG);
            System.out.println("hrINIZIALE.OBJECT_USEPLUGIN="+hrINIZIALE.OBJECT_USEPLUGIN);
            System.out.println("***************************************");
        }
        
        //ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA=getPosAnalisi(hr.OBJECT_IDANALISYS);
        ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA=hr.OBJECT_IDANALISYS;
        ADAMS_GlobalParam.TAG_SELEZIONATO=hr.TAG;
        
        jC_report2.setSelectedIndex(getPosAnalisi(hr.OBJECT_IDANALISYS));
        jT_report_name.setText(""+hr.TAG);
        ADAMS_GlobalParam.lastReportName=jT_report_name.getText();
        jT_plugin_name.setText(""+hr.OBJECT_PLUGINNAME);
        
        if(hr.OBJECT_SIMPLEBODY==1)
        {
            jR_plain.setSelected(true);
        }
        else
        {
            jR_plain.setSelected(false);
        }
        
        if(hr.OBJECT_HASBORDER==1)
        {
            jR_borderer.setSelected(true);
        }
        else
        {
            jR_borderer.setSelected(false);
        }
        
        /*if(hr.OBJECT_EXCELCSV==1)
        {
            jR_excel.setSelected(true);
        }
        else
        {
            jR_excel.setSelected(false);
        }*/
        
        
        jB_b.setBackground(new Color(hr.OBJECT_DEFAULT_BACKGROUND_R,hr.OBJECT_DEFAULT_BACKGROUND_G,hr.OBJECT_DEFAULT_BACKGROUND_B));
        jB_f.setBackground(new Color(hr.OBJECT_DEFAULT_FOREGROUND_R,hr.OBJECT_DEFAULT_FOREGROUND_G,hr.OBJECT_DEFAULT_FOREGROUND_B));
        
        if(hr.OBJECT_USEPLUGIN==1)
        {
        	
            jR_use_plugin.setSelected(true);
            jLabel4.setEnabled(true);
            jT_plugin_name.setEnabled(true);
            jB_edit.setEnabled(true);
            jTree.setBackground(Color.gray);
            jTree.setEnabled(false);
            //TREE_ENABLED=false;
        }
        else
        {
            jR_use_plugin.setSelected(false);
            jLabel4.setEnabled(false);
            jT_plugin_name.setEnabled(false);
            jB_edit.setEnabled(false);
            jTree.setEnabled(true);
            jTree.setBackground(Color.white);
            TREE_ENABLED=true;
        } 
        
        jC_excel_new.setSelectedIndex(hr.OBJECT_EXCELCSV);
        
        setTree();
        
        vHeader=schemaAttuale.getVectorObject(ReportObject.HEADER);
        
        setHeaderCell(ReportObject.HEADER,vHeader);
        
        vBody=schemaAttuale.getVectorObject(ReportObject.BODY);
        setHeaderCell(ReportObject.BODY,vBody);
        
        vTot=schemaAttuale.getVectorObject(ReportObject.TOTALIZER);
        setHeaderCell(ReportObject.TOTALIZER,vTot);
        
        vFoot=schemaAttuale.getVectorObject(ReportObject.FOOTER);
        setHeaderCell(ReportObject.FOOTER,vFoot);
    }
    
    
    public void setAreaHelp(javax.swing.JTextArea ta)
    {
            jT_help=ta;
    }
    
    public void setHeaderCell(int type,Vector vElement)
    {
        int len=vElement.size();
 
        
        SampleData sd;
        ImageIcon icon;
        nodoSummaryEXT h_cell=null;
        

        switch(type)
        {
            case ReportObject.HEADER:
                {
                    header.removeAllChildren();
                }break;

            case ReportObject.BODY:
                {
                    body.removeAllChildren();
            }break;

            case ReportObject.TOTALIZER:
                {
                    totalizer.removeAllChildren();
            }break;

            case ReportObject.FOOTER:
                {
                    footer.removeAllChildren();
            }break;
        }
        
        for(int i=0;i<len;i++)
        {
            ReportObject.elementoBase h1=(ReportObject.elementoBase)vElement.elementAt(i);
            
            if(DEBUG)
            {
                System.out.println("Size=" +vElement.size()+"   TYPE="+h1.TYPE+"    SUB_TYPE"+h1.SUB_TYPE+"     Desc="+h1.OBJECT_TAG+"  type desc="+ReportObject.objectDesc[h1.TYPE]);
            }
            
            if(h1.SUB_TYPE==-1)
            {
                switch(h1.TYPE)
                {
                    //h1.IDOBJECT è stato tolto dalla label
                    case ReportObject.HEADER:
                    {
                        String str="HeaderCell "+" [" +h1.OBJECT_TAG+"]";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/header_cell.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                        header.add(h_cell);
                    }break;

                    case ReportObject.BODY:
                    {
                        String str="BodyCell "+" [" +h1.OBJECT_TAG+"]";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/body_cell.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                        body.add(h_cell);
                    }break;

                    case ReportObject.TOTALIZER:
                    {
                        String str="TotalizerCell "+" [" +h1.OBJECT_TAG+"]";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/tot_cell.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                        totalizer.add(h_cell);
                    }break;

                    case ReportObject.FOOTER:
                    {
                        String str="FooterRow "+" [" +h1.OBJECT_TAG+"]";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/foot_cell.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                        footer.add(h_cell);
                    }break;
                }
            }else // sto nelle line di break
            {
                switch(h1.SUB_TYPE)
                {
                    case ReportObject.LINE_BREAK:
                    {
                        String str="Line Breaker ";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/line_breaker.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                    }break;

                    case ReportObject.LINE_BLANK:
                    {
                        String str="Line Blank ";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/blank_line.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                    }break;

                    case ReportObject.LINE_SEPARETOR:
                    {
                        String str="Line Separetor ";
                        sd=new SampleData( h1.idGUI,ADAMS_GlobalParam.font_P12,cRamo2,str,ADAMS_GlobalParam.font_B12,cRamo2_sel,str);
                        icon=new ImageIcon(getClass().getResource("/images/separetor_line.png"));
                        sd.setIcon(icon,icon,icon,icon);
                        h_cell=new nodoSummaryEXT(sd);
                    }break;
                }   

               switch(h1.TYPE)
               {
                    case ReportObject.HEADER:
                    {
                        header.add(h_cell);
                    }break;

                    case ReportObject.BODY:
                    {
                        body.add(h_cell);
                    }break;

                    case ReportObject.TOTALIZER:
                    {
                        totalizer.add(h_cell);
                    }break;

                    case ReportObject.FOOTER:
                    {
                        footer.add(h_cell);
                    }break;
               }     
            }
        }
        
        setTreeNEW(jTree);
    }
    
    public void setTree()
    {
        SampleData sd;
        ImageIcon icon,icon_open;
        
        radice=new nodoSummaryEXT(new SampleData( ADAMS_GlobalParam.font_B12,cRamo1,"Report Component"));
        jTree=new JTree(radice);
        
        remove(jTree);    
        
        sd=new SampleData( -1,ADAMS_GlobalParam.font_P12,cRamo2,"Header",ADAMS_GlobalParam.font_B12,cRamo2_sel,"Header");
        icon=new ImageIcon(getClass().getResource("/images/folder_yellow.png"));
        icon_open=new ImageIcon(getClass().getResource("/images/folder_yellow_open.png"));
        sd.setIcon(icon_open,icon,icon_open,icon);
        header=new nodoSummaryEXT(sd);
        radice.add(header);
        
        if(jR_use_plugin.isSelected()==false)
        {
        sd=new SampleData(  -1,ADAMS_GlobalParam.font_P12,cRamo2,"Body",ADAMS_GlobalParam.font_B12,cRamo2_sel,"Body");
        icon=new ImageIcon(getClass().getResource("/images/folder_green.png"));
        icon_open=new ImageIcon(getClass().getResource("/images/folder_green_open.png"));
        sd.setIcon(icon_open,icon,icon_open,icon);
        body=new nodoSummaryEXT(sd);
        radice.add(body);

        sd=new SampleData(  -1,ADAMS_GlobalParam.font_P12,cRamo2,"Totalizer",ADAMS_GlobalParam.font_B12,cRamo2_sel,"Totalizer");
        icon=new ImageIcon(getClass().getResource("/images/folder_red.png"));
        icon_open=new ImageIcon(getClass().getResource("/images/folder_red_open.png"));
        sd.setIcon(icon_open,icon,icon_open,icon);
        totalizer=new nodoSummaryEXT(sd);
        radice.add(totalizer);

        sd=new SampleData(  -1,ADAMS_GlobalParam.font_P12,cRamo2,"Footer",ADAMS_GlobalParam.font_B12,cRamo2_sel,"Footer");
        icon=new ImageIcon(getClass().getResource("/images/folder_violet.png"));
        icon_open=new ImageIcon(getClass().getResource("/images/folder_violet_open.png"));
        sd.setIcon(icon_open,icon,icon_open,icon);
        footer=new nodoSummaryEXT(sd);
        radice.add(footer);
        }
        
        jTree=new JTree(radice);
        setTreeNEW(jTree);
        expandTree(jTree);
        jScrollPane1.setViewportView(jTree);
        
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mousePressed(java.awt.event.MouseEvent evt) {
                  MousePressed(evt);
              }
          }
        );
        
        jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                        jTreeValueChanged(evt);
                }
        }
        );
       
    }
    
    private void MousePressed(java.awt.event.MouseEvent evt) 
    { 
        int selRow = jTree.getRowForLocation(evt.getX(), evt.getY());
        TreePath selPath = jTree.getPathForLocation(evt.getX(), evt.getY());
        idSelezionato=-1;
        
        if(selPath!=null)
        {
            if(DEBUG)
                System.out.println("selPath.getPathCount()="+selPath.getPathCount());
            
            if(selPath.getPathCount()<=1)
                return;
            
            String type=(selPath.getPathComponent(1)).toString();
            idObj=0;
            if(type.equals("Header"))
            {
                idObj=ReportObject.HEADER;
            }
            else if(type.equals("Body"))
            {
                idObj=ReportObject.BODY;
            }
            else if(type.equals("Totalizer"))
            {
                idObj=ReportObject.TOTALIZER;
            }
            else if(type.equals("Footer"))
            {
                idObj=ReportObject.FOOTER;
            }
        }
        
        if(selPath!=null)
        {
            levelSelected = selPath.getPathCount();
            nodoSummaryEXT boo=(nodoSummaryEXT)selPath.getPathComponent(levelSelected-1);
            idSelezionato=boo.id;
        }
        else
            levelSelected=-1;
        
        if(DEBUG)
        {
            System.out.println("levelSelected="+levelSelected);
            System.out.println("selRow="+selRow);
            System.out.println("selPath="+selPath);
            System.out.println("idSelezionato="+idSelezionato);
            System.out.println();
        }
        
        
        if(selRow != -1) 
        {
            if(evt.getClickCount() == 1) 
            {
                if (levelSelected>=0)
                {
                    int mouseID=evt.getModifiers();
                    if(levelSelected==0)
                    {
                        jM_mod.setEnabled(false);
                        jM_del.setEnabled(false);
                        jM_up.setEnabled(false);
                        jM_down.setEnabled(false);
                        jM_action.setEnabled(false);
                    }
                    else if(levelSelected==1)
                    {
                        jM_mod.setEnabled(true);
                        jM_del.setEnabled(true);
                        jM_up.setEnabled(true);
                        jM_down.setEnabled(true);
                        jM_action.setEnabled(true);
                    }

                    if( ((mouseID & InputEvent.BUTTON3_MASK)!=0)&&(jC_report1.getSelectedIndex()>=1))  
                    {
                        if(TREE_ENABLED==true)
                            jPopupMenu1.show((Component)evt.getSource(),evt.getX(),evt.getY());
                        else
                            return;
                        
                        ReportObject.elementoBase ebTrovato=schemaAttuale.getElementoBase(idSelezionato);
                        
                        if(ebTrovato!=null)
                        {
                            jM_up.setEnabled(true);
                            jM_down.setEnabled(true);
                            if(ebTrovato.SUB_TYPE!=-1)
                            {
                                jM_mod.setEnabled(false);
                            }else
                            {
                                jM_mod.setEnabled(true);
                            }
                        }else
                        {
                            jM_up.setEnabled(false);
                            jM_down.setEnabled(false);
                            jM_mod.setEnabled(true);
                        }
                    }
                }
            }
            else if((evt.getClickCount() == 2)&&(TREE_ENABLED))
            {
                if (levelSelected>=2)
                {
                      
                    if(jf_confReport!=null)
                    {
                        jf_confReport.closeFrame(false);
                        jf_confReport=null;
                    }
                    if(levelSelected==2)
                    {
                        /*if(DEBUG)
                            System.out.println("Entro in NUOVO ELELEMNTO");
                        ReportObject.elementoBase ebNUOVO=new ReportObject.elementoBase();
                        ebNUOVO.TYPE=idObj;
                        ebNUOVO.SUB_TYPE=-1;
                        ADAMS_GlobalParam.INSERT_NEW_ELEMENT=1;
                       
                        jf_confReport = new ADAMS_JF_Report(true,"Report Object Editor",config_NAME_ALIAS,"NUOVO",ADAMS_GlobalParam.REPORT_OBJECT_EDITOR,idObj,ebNUOVO,schemaAttuale);
                        
                        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
                        {
                            schemaAttuale.addElement(ebNUOVO);
                            setInterface2();
                            if(DEBUG)
                                System.out.println("Aggiunto un nuovo elemento");
                        }*/
                    }
                    else if(levelSelected==3)
                    {
                        commitFatta=false;
                        ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
                        ReportObject.elementoBase ebTrovato=schemaAttuale.getElementoBase(idSelezionato);
                        ReportObject.elementoBase hr=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
                        
                        if(DEBUG)
                        {
                            System.out.println("Entro in MODIFICA="+ebTrovato.OBJECT_TAG);
                            System.out.println("SUB_TYPE="+ebTrovato.SUB_TYPE);
                        }
                        if(ebTrovato.SUB_TYPE!=-1)
                            return;
                        jf_confReport = new ADAMS_JF_Report(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Report Object Editor",config_NAME_ALIAS,hr.TAG,ADAMS_GlobalParam.REPORT_OBJECT_EDITOR,idObj,ebTrovato,schemaAttuale);
                        
                        if(DEBUG)
                            System.out.println("Entro in MODIFICA="+ebTrovato.OBJECT_TAG);
                        setInterface2();
                    }
                }
            }
        }
    }
    
    private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) 
    {
        /*path=evt.getPath();
        pathCount = path.getPathCount();
        levelSelected=pathCount-2;  */
    }
    
    
    private void expandTree(JTree tree)
    {
            for(int i=1;i<=tree.getRowCount();i++)
            tree.expandRow(i);
    }

    private void collapseTree(JTree tree,java.awt.event.ActionEvent evt)
    {
            int cValue=1;
            
            for(int i=tree.getRowCount(); i>cValue;i--)
                    tree.collapseRow(i);
    }

    private void setTreeNEW(JTree tree)
    {
            SampleTreeCellRenderer renderer1 = new SampleTreeCellRenderer();
            tree.setBackground(cSfondoTree);
            tree.setCellRenderer(renderer1);
            tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tree.setUI(new WindowsTreeUI());
    }
    
    
    public void setFont()
    {
        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jLabel3.setFont(ADAMS_GlobalParam.font_B11);
        jLabel7.setFont(ADAMS_GlobalParam.font_B11);
        jLabel2.setFont(ADAMS_GlobalParam.font_B11);
        jT_report_name.setFont(ADAMS_GlobalParam.font_B11);
        jLabel4.setFont(ADAMS_GlobalParam.font_B11);
        jLabel5.setFont(ADAMS_GlobalParam.font_B11);
        
        jC_report1.setFont(ADAMS_GlobalParam.font_B11);
        jC_report2.setFont(ADAMS_GlobalParam.font_B11);
        
        jT_plugin_name.setFont(ADAMS_GlobalParam.font_B11);
        jR_use_plugin.setFont(ADAMS_GlobalParam.font_B11);
        
        jR_plain.setFont(ADAMS_GlobalParam.font_B11);
        jR_borderer.setFont(ADAMS_GlobalParam.font_B11);
        //jR_excel.setFont(ADAMS_GlobalParam.font_B11);
        jC_excel_new.setFont(ADAMS_GlobalParam.font_B11);
        jB_b.setFont(ADAMS_GlobalParam.font_B11);
        jB_f.setFont(ADAMS_GlobalParam.font_B11);
        
    }
    
    public void setCursorWidget()
    {    
        jC_report1.setCursor(Cur_hand);
        jC_report2.setCursor(Cur_hand);
        jT_plugin_name.setCursor(Cur_hand);
        jR_use_plugin.setCursor(Cur_hand);
        jR_plain.setCursor(Cur_hand);
        jR_borderer.setCursor(Cur_hand);
        //jR_excel.setCursor(Cur_hand);
        jC_excel_new.setCursor(Cur_hand);
        jB_b.setCursor(Cur_hand);
        jB_f.setCursor(Cur_hand);
        jTree.setCursor(Cur_hand);
        jT_report_name.setCursor(Cur_hand);
        jB_edit.setCursor(Cur_hand);
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jM_add = new javax.swing.JMenuItem();
        jM_mod = new javax.swing.JMenuItem();
        jM_del = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jM_up = new javax.swing.JMenuItem();
        jM_down = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jM_action = new javax.swing.JMenu();
        jM_break = new javax.swing.JMenuItem();
        jM_blank = new javax.swing.JMenuItem();
        jM_separetor = new javax.swing.JMenuItem();
        jP_p5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_top = new javax.swing.JPanel();
        jP_p1 = new javax.swing.JPanel();
        jC_report1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jP_p2 = new javax.swing.JPanel();
        jC_report2 = new javax.swing.JComboBox();
        jT_report_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jP_p3 = new javax.swing.JPanel();
        jR_use_plugin = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jT_plugin_name = new javax.swing.JTextField();
        jB_edit = new javax.swing.JButton();
        jP_p4 = new javax.swing.JPanel();
        jR_plain = new javax.swing.JRadioButton();
        jR_borderer = new javax.swing.JRadioButton();
        //jR_excel = new javax.swing.JRadioButton();
        jC_excel_new = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jB_b = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jB_f = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        
        jM_add.setText("Add");
        jM_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_add.png")));
        jM_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jPopupMenu1.add(jM_add);
        jM_mod.setText("Modify");
        jM_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png")));
        jM_mod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jPopupMenu1.add(jM_mod);
        jM_del.setText("Delete");
        jM_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editdelete.png")));
        jM_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jPopupMenu1.add(jM_del);
        jPopupMenu1.add(jSeparator1);
        jM_up.setText("Move Up");
        jM_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up.png")));
        jM_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jPopupMenu1.add(jM_up);
        jM_down.setText("Move Down");
        jM_down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down.png")));
        jM_down.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jPopupMenu1.add(jM_down);
        jPopupMenu1.add(jSeparator2);
        jM_action.setText("Insert Actions");
        jM_action.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/action.png")));
        jM_break.setText("Break Current Line");
        jM_break.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/break.png")));
        jM_break.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_breakActionPerformed(evt);
            }
        });
        
        jM_break.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jM_action.add(jM_break);
        jM_blank.setText("Insert Blank Line");
        jM_blank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blank.png")));
        jM_blank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jM_action.add(jM_blank);
        jM_separetor.setText("Insert Separator Line");
        jM_separetor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/line.png")));
        jM_separetor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jM_addMouseReleased(evt);
            }
        });
        
        jM_action.add(jM_separetor);
        jPopupMenu1.add(jM_action);
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        jP_p5.setLayout(new javax.swing.BoxLayout(jP_p5, javax.swing.BoxLayout.X_AXIS));
        
        jP_p5.setBorder(new javax.swing.border.EtchedBorder());
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 300));
        jScrollPane1.setOpaque(false);
        jP_p5.add(jScrollPane1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_p5, gridBagConstraints1);
        
        jP_top.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.EtchedBorder());
        jC_report1.setBackground(java.awt.Color.white);
        jC_report1.setMaximumSize(new java.awt.Dimension(350, 20));
        jC_report1.setMinimumSize(new java.awt.Dimension(350, 20));
        jC_report1.setPreferredSize(new java.awt.Dimension(350, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jC_report1, gridBagConstraints3);
        
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Report Selection");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        jP_p1.add(jLabel2, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_top.add(jP_p1, gridBagConstraints2);
        
        jP_p2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_p2.setBackground(new java.awt.Color(230, 230, 230));
        jP_p2.setBorder(new javax.swing.border.EtchedBorder());
        jC_report2.setBackground(java.awt.Color.white);
        jC_report2.setMinimumSize(new java.awt.Dimension(100, 20));
        jC_report2.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_p2.add(jC_report2, gridBagConstraints4);
        
        jT_report_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jT_report_name.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jT_report_name.setMaximumSize(new java.awt.Dimension(350, 20));
        jT_report_name.setMinimumSize(new java.awt.Dimension(350, 20));
        jT_report_name.setPreferredSize(new java.awt.Dimension(350, 20));
        jT_report_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_report_nameKeyReleased(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jT_report_name, gridBagConstraints4);
        
        jLabel3.setText("Report Name");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jLabel3, gridBagConstraints4);
        
        jLabel7.setText("Analysis Type");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jLabel7, gridBagConstraints4);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_top.add(jP_p2, gridBagConstraints2);
        
        jP_p3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_p3.setBackground(new java.awt.Color(230, 230, 230));
        jP_p3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Plugin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jR_use_plugin.setText("Use Plugin to Handle");
        jR_use_plugin.setToolTipText("Use Plugin to Handle");
        jR_use_plugin.setContentAreaFilled(false);
        jR_use_plugin.setFocusPainted(false);
        jR_use_plugin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_use_plugin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_use_plugin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_use_plugin.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jR_use_plugin.setMaximumSize(new java.awt.Dimension(345, 20));
        jR_use_plugin.setMinimumSize(new java.awt.Dimension(345, 20));
        jR_use_plugin.setPreferredSize(new java.awt.Dimension(345, 20));
        jR_use_plugin.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_use_plugin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_use_plugin.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_use_plugin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_use_plugin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_use_pluginActionPerformed(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p3.add(jR_use_plugin, gridBagConstraints5);
        
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Plugin Name ");
        jLabel4.setEnabled(false);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p3.add(jLabel4, gridBagConstraints5);
        
        jT_plugin_name.setEditable(false);
        jT_plugin_name.setMaximumSize(new java.awt.Dimension(100, 20));
        jT_plugin_name.setMinimumSize(new java.awt.Dimension(100, 20));
        jT_plugin_name.setPreferredSize(new java.awt.Dimension(100, 20));
        jT_plugin_name.setEnabled(false);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 2;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.weightx = 1.0;
        jP_p3.add(jT_plugin_name, gridBagConstraints5);
        
        jB_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_edit.setToolTipText("Edit Plugin (Active in modification)");
        jB_edit.setBorderPainted(false);
        jB_edit.setContentAreaFilled(false);
        jB_edit.setFocusPainted(false);
        jB_edit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_edit.setMaximumSize(new java.awt.Dimension(85, 22));
        jB_edit.setMinimumSize(new java.awt.Dimension(85, 22));
        jB_edit.setPreferredSize(new java.awt.Dimension(85, 22));
        jB_edit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_edit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_editActionPerformed(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 3;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p3.add(jB_edit, gridBagConstraints5);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_top.add(jP_p3, gridBagConstraints2);
        
        jP_p4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jP_p4.setBackground(new java.awt.Color(230, 230, 230));
        jP_p4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Default Rendering", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jR_plain.setText("Plain Body   ");
        jR_plain.setToolTipText("Direct");
        jR_plain.setContentAreaFilled(false);
        jR_plain.setFocusPainted(false);
        jR_plain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_plain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_plain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_plain.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_plain.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_plain.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_plain.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_plain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_plainActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        jP_p4.add(jR_plain, gridBagConstraints6);
        
        jR_borderer.setText("Borderer Layout   ");
        jR_borderer.setToolTipText("Direct");
        jR_borderer.setContentAreaFilled(false);
        jR_borderer.setFocusPainted(false);
        jR_borderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_borderer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_borderer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_borderer.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_borderer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_borderer.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_borderer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_borderer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_plainActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        jP_p4.add(jR_borderer, gridBagConstraints6);
        
        /*jR_excel.setText("EXCEL CSV      ");
        jR_excel.setToolTipText("Direct");
        jR_excel.setContentAreaFilled(false);
        jR_excel.setFocusPainted(false);
        jR_excel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_excel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_excel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_excel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_excel.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_excel.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_plainActionPerformed(evt);
            }
        });*/
        
        
        jC_excel_new.setToolTipText("Direct");
        jC_excel_new.addItem("NORMAL");
        jC_excel_new.addItem("CSV");
	jC_excel_new.addItem("EXCEL");
	jC_excel_new.setSelectedIndex(0);
  
        jC_excel_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_plainActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints6.weightx = 1.0;
        //jP_p4.add(jR_excel, gridBagConstraints6);
        jP_p4.add(jC_excel_new, gridBagConstraints6);
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Global Background");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints6.weightx = 1.0;
        jP_p4.add(jLabel1, gridBagConstraints6);
        
        jB_b.setBackground(java.awt.Color.white);
        jB_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_bActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints6.weightx = 1.0;
        jP_p4.add(jB_b, gridBagConstraints6);
        
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Foreground");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints6.weightx = 1.0;
        jP_p4.add(jLabel5, gridBagConstraints6);
        
        jB_f.setBackground(java.awt.Color.yellow);
        jB_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_fActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints6.weightx = 1.0;
        jP_p4.add(jB_f, gridBagConstraints6);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_top.add(jP_p4, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_top, gridBagConstraints1);
        
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input_data48.png")));
        jLabel6.setText("REPORT SCHEMA");
        jLabel6.setMinimumSize(new java.awt.Dimension(700, 70));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 600;
        gridBagConstraints1.ipady = 10;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints1.weightx = 1.0;
        add(jLabel6, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jT_report_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_report_nameKeyReleased
        // Add your handling code here:
        this.jpButton.enableCommit(true);
        fattaDELETE=false;
    }//GEN-LAST:event_jT_report_nameKeyReleased

    private void jR_plainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_plainActionPerformed
        // Add your handling code here:
        this.jpButton.enableCommit(true);
        fattaDELETE=false;
    }//GEN-LAST:event_jR_plainActionPerformed

    private void jB_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_editActionPerformed
        // Add your handling code here:
        ADAMS_JD_setPlugin  JD_PluginSelect = new ADAMS_JD_setPlugin(null,true,700,400,config_NAME_ALIAS,jT_plugin_name.getText());     
        JD_PluginSelect.setGuiEnable(true);
        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        jT_plugin_name.setText(JD_PluginSelect.getPluginEnabled());
        this.jpButton.enableCommit(true);
        fattaDELETE=false;
    }//GEN-LAST:event_jB_editActionPerformed

    private void jM_breakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_breakActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jM_breakActionPerformed

    private void jM_addMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jM_addMouseReleased
        // Add your handling code here:
        fattaDELETE=false;
        if (evt.getSource() == jM_del) //insert new element
        {
            //System.out.println("Delete");
            ReportObject.elementoBase ebTrovato=schemaAttuale.getElementoBase(idSelezionato);
            if(ebTrovato!=null)
            {
                schemaAttuale.addElementTOREMOVE(ebTrovato);
                schemaAttuale.deleteObj(ebTrovato);
                setInterface2();
                this.jpButton.enableCommit();
            }
        }else if(evt.getSource() == jM_add)
        {
            String strSelectPos="select POSIZIONE_seq.nextVal from dual";
            int posizione  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectPos);
            
            commitFatta=false;
            //System.out.println("Entro in NUOVO ELELEMNTO="+idObj);
            ReportObject.elementoBase ebNUOVO=new ReportObject.elementoBase();
            ebNUOVO.TYPE=idObj;
            ebNUOVO.SUB_TYPE=-1;
            ebNUOVO.IDOBJECT=-1;
            ebNUOVO.POSIZIONE=posizione;
            
            String strSelect="select IDOBJECT_seq.nextVal from dual";
            idGUI  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
            ebNUOVO.idGUI=idGUI;
            
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=1;

            jf_confReport = new ADAMS_JF_Report(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Report Object Editor",config_NAME_ALIAS,"NUOVO",ADAMS_GlobalParam.REPORT_OBJECT_EDITOR,idObj,ebNUOVO,schemaAttuale);

            //System.out.println("ADAMS_GlobalParam.INSERT_NEW_ELEMENT="+ADAMS_GlobalParam.INSERT_NEW_ELEMENT);
            if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            {
                schemaAttuale.addElement(ebNUOVO);
                setInterface2();
                //System.out.println("Aggiunto un nuovo elemento");
            }
        }else if(evt.getSource() == jM_mod)
        {
            if(levelSelected==2)
                return;
            
            commitFatta = false;
            
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
            
            ReportObject.elementoBase ebTrovato=schemaAttuale.getElementoBase(idSelezionato);
            if(ebTrovato!=null)
            {
                ReportObject.elementoBase hr=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();

                //System.out.println("Entro in MODIFICA="+ebTrovato.OBJECT_TAG);
                //System.out.println("SUB_TYPE="+ebTrovato.SUB_TYPE);

                //if(ebTrovato.SUB_TYPE!=-1)
                //    return;

                jf_confReport = new ADAMS_JF_Report(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Report Object Editor",config_NAME_ALIAS,hr.TAG,ADAMS_GlobalParam.REPORT_OBJECT_EDITOR,idObj,ebTrovato,schemaAttuale);
                //System.out.println("Entro in MODIFICA="+ebTrovato.OBJECT_TAG);
                setInterface2();

            }
        }else if(evt.getSource() == jM_break)
        {
            //System.out.println("Aggiunto un nuovo elemento di break idObj="+idObj);
            
            
            ReportObject.elementoBase ebNUOVO=new ReportObject.elementoBase();
            ebNUOVO.TYPE=idObj;
            ebNUOVO.SUB_TYPE=ReportObject.LINE_BREAK;
            
            String strSelectPos="select POSIZIONE_seq.nextVal from dual";
            int posizione  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectPos);
            ebNUOVO.POSIZIONE=posizione;
            
            String strSelect="select IDOBJECT_seq.nextVal from dual";
            idGUI  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
            ebNUOVO.idGUI=idGUI;
            
            ebNUOVO.IDOBJECT=-1;
            ebNUOVO.OBJECT_TAG="break";
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=1;


            if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            {
                schemaAttuale.addElement(ebNUOVO);
                setInterface2();
                //System.out.println("Aggiunto un nuovo elemento di break");
            }
            
        }else if(evt.getSource() == jM_blank)
        {
            //System.out.println("Aggiunto un nuovo elemento di break idObj="+idObj);
            
            ReportObject.elementoBase ebNUOVO=new ReportObject.elementoBase();
            ebNUOVO.TYPE=idObj;
            ebNUOVO.SUB_TYPE=ReportObject.LINE_BLANK;
            
            String strSelectPos="select POSIZIONE_seq.nextVal from dual";
            int posizione  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectPos);
            ebNUOVO.POSIZIONE=posizione;
            
            String strSelect="select IDOBJECT_seq.nextVal from dual";
            idGUI  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
            ebNUOVO.idGUI=idGUI;
            
            ebNUOVO.IDOBJECT=-1;
            ebNUOVO.OBJECT_TAG="blank";
            
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=1;


            if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            {
                schemaAttuale.addElement(ebNUOVO);
                setInterface2();
                //System.out.println("Aggiunto un nuovo elemento di break");
            }
            
        }else if(evt.getSource() == jM_separetor)
        {
            //System.out.println("Aggiunto un nuovo elemento di break idObj="+idObj);
            
            ReportObject.elementoBase ebNUOVO=new ReportObject.elementoBase();
            ebNUOVO.TYPE=idObj;
            ebNUOVO.SUB_TYPE=ReportObject.LINE_SEPARETOR;
            
            String strSelectPos="select POSIZIONE_seq.nextVal from dual";
            int posizione  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectPos);
            ebNUOVO.POSIZIONE=posizione;
            
            String strSelect="select IDOBJECT_seq.nextVal from dual";
            idGUI  = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
            ebNUOVO.idGUI=idGUI;
            
            ebNUOVO.IDOBJECT=-1;
            ebNUOVO.OBJECT_TAG="separetor";
            
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=1;


            if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            {
                schemaAttuale.addElement(ebNUOVO);
                setInterface2();
                //System.out.println("Aggiunto un nuovo elemento di break");
            }
        }else if(evt.getSource() == jM_up)
        {
            
            commitFatta=false;
            ReportObject.elementoBase ebTrovato=schemaAttuale.getElementoBase(idSelezionato);
            int idElementoTrovato=ebTrovato.idGUI;
            
            System.out.println("MOVE UP famiglia="+idObj+"  IDOBJECT="+idElementoTrovato);
            
            schemaAttuale.moveUP(idElementoTrovato,idObj);
            
            setInterface2();
            this.jpButton.enableCommit();
            //lavorare su schemaAttuale per fare l'up 
        }else if(evt.getSource() == jM_down)
        {
            commitFatta=false;
            ReportObject.elementoBase ebTrovato=schemaAttuale.getElementoBase(idSelezionato);
            int idElementoTrovato=ebTrovato.idGUI;//IDOBJECT;
            
            //System.out.println("MOVE DOWN famiglia="+idObj+"  IDOBJECT="+idElementoTrovato);
            
            schemaAttuale.moveDown(idElementoTrovato,idObj);
            
            setInterface2();
            this.jpButton.enableCommit();
        }
    }//GEN-LAST:event_jM_addMouseReleased

    private void jC_report2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_report2ActionPerformed
        // Add your handling code here:
        
        if( (jC_report1.getSelectedIndex()>1) && (schemaAttuale.presenteQualcheObj()) )
        {
            int prima=ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA;
            int dopo=getIDAnalisi(jC_report2.getSelectedIndex());
            if(prima!=dopo)
            {
                if(prima!=0)
                {
                    //System.out.println("Devo cancellare pure il padre terno");
                    ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Warning: All configured data in Body, Totalizer and Footer will be lost ! Please confirm operation.","Warning",6);
                    int Yes_No = op.getAnswer();

                    if(Yes_No == 1)// faccio il remove
                    {
                        flagCLEARELEMENTS=true;
                        //schemaAttuale.resetObject(ReportObject.HEADER);
                        schemaAttuale.resetObject(ReportObject.BODY);
                        schemaAttuale.resetObject(ReportObject.TOTALIZER);
                        schemaAttuale.resetObject(ReportObject.FOOTER);
                        
                        
                        ReportObject.elementoBase hrAPPO=new ReportObject.elementoBase();
                        hrAPPO.TIPO_DI_CONFIGURAZIONE=hrINIZIALE.TIPO_DI_CONFIGURAZIONE;
                        hrAPPO.TYPE=hrINIZIALE.TYPE;
                        hrAPPO.TAG=hrINIZIALE.TAG;
                        hrAPPO.OBJECT_IDANALISYS=hrINIZIALE.OBJECT_IDANALISYS;
                        hrAPPO.OBJECT_USEPLUGIN=hrINIZIALE.OBJECT_USEPLUGIN;
                        hrAPPO.OBJECT_PLUGINNAME=hrINIZIALE.OBJECT_PLUGINNAME;
                        hrAPPO.OBJECT_SIMPLEBODY=hrINIZIALE.OBJECT_SIMPLEBODY;
                        hrAPPO.OBJECT_HASBORDER=hrINIZIALE.OBJECT_HASBORDER;
                        hrAPPO.OBJECT_EXCELCSV=hrINIZIALE.OBJECT_EXCELCSV;
                        hrAPPO.OBJECT_DEFAULT_BACKGROUND_R=hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_R;
                        hrAPPO.OBJECT_DEFAULT_BACKGROUND_G=hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_G;
                        hrAPPO.OBJECT_DEFAULT_BACKGROUND_B=hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_B;
                        hrAPPO.OBJECT_DEFAULT_FOREGROUND_R=hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_R;
                        hrAPPO.OBJECT_DEFAULT_FOREGROUND_G=hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_G;
                        hrAPPO.OBJECT_DEFAULT_FOREGROUND_B=hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_B;
        
                        ReportObject.elementoBase hr=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
                        hr.OBJECT_IDANALISYS=dopo;
                        setInterface();
                        
                        hrINIZIALE.TIPO_DI_CONFIGURAZIONE=hrAPPO.TIPO_DI_CONFIGURAZIONE;
                        hrINIZIALE.TYPE=hrAPPO.TYPE;
                        hrINIZIALE.TAG=hrAPPO.TAG;
                        hrINIZIALE.OBJECT_IDANALISYS=hrAPPO.OBJECT_IDANALISYS;
                        hrINIZIALE.OBJECT_USEPLUGIN=hrAPPO.OBJECT_USEPLUGIN;
                        hrINIZIALE.OBJECT_PLUGINNAME=hrAPPO.OBJECT_PLUGINNAME;
                        hrINIZIALE.OBJECT_SIMPLEBODY=hrAPPO.OBJECT_SIMPLEBODY;
                        hrINIZIALE.OBJECT_HASBORDER=hrAPPO.OBJECT_HASBORDER;
                        hrINIZIALE.OBJECT_EXCELCSV=hrAPPO.OBJECT_EXCELCSV;
                        hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_R=hrAPPO.OBJECT_DEFAULT_BACKGROUND_R;
                        hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_G=hrAPPO.OBJECT_DEFAULT_BACKGROUND_G;
                        hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_B=hrAPPO.OBJECT_DEFAULT_BACKGROUND_B;
                        hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_R=hrAPPO.OBJECT_DEFAULT_FOREGROUND_R;
                        hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_G=hrAPPO.OBJECT_DEFAULT_FOREGROUND_G;
                        hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_B=hrAPPO.OBJECT_DEFAULT_FOREGROUND_B;
                        
                        this.jpButton.enableCommit(true);
                    }else
                    {
                        jC_report2.setSelectedIndex(getPosAnalisi(prima));
                    }
                }
            }
        }
        ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA=getIDAnalisi(jC_report2.getSelectedIndex());
        //ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA=jC_report2.getSelectedIndex();
       
    }//GEN-LAST:event_jC_report2ActionPerformed

    private void jB_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_fActionPerformed
        // Add your handling code here:
        fattaDELETE=false;
        Color color = JColorChooser.showDialog(this, "Color Chooser", jB_f.getBackground());
        if (color != null)
        {  
            jB_f.setIcon(null);
            jB_f.setBackground(color);
            jB_f.setForeground(color);
            //R_ColorTextLegend = color.getRed();
            //G_ColorTextLegend = color.getGreen();
            //B_ColorTextLegend = color.getBlue();
        }
        else
        {
            //R_ColorTextLegend = -1;
        }
        this.jpButton.enableCommit(true);
    }//GEN-LAST:event_jB_fActionPerformed

    private void jB_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_bActionPerformed
        // Add your handling code here:
        fattaDELETE=false;
        Color color = JColorChooser.showDialog(this, "Color Chooser", jB_b.getBackground());
        if (color != null)
        {  
            jB_b.setIcon(null);
            jB_b.setBackground(color);
            jB_b.setForeground(color);
            //R_ColorTextLegend = color.getRed();
            //G_ColorTextLegend = color.getGreen();
            //B_ColorTextLegend = color.getBlue();
        }
        else
        {
            //R_ColorTextLegend = -1;
        }
        this.jpButton.enableCommit(true);
    }//GEN-LAST:event_jB_bActionPerformed

    private void jR_use_pluginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_use_pluginActionPerformed
        // Add your handling code here:
        int Yes_No=0;
        fattaDELETE=false;
        if(jR_use_plugin.isSelected()==false)
        {
            jLabel4.setEnabled(false);
            jT_plugin_name.setEnabled(false);
            jB_edit.setEnabled(false);
            jTree.setEnabled(true);
            jTree.setBackground(Color.white);
            TREE_ENABLED=true;
        }else
        {
            jLabel4.setEnabled(true);
            jT_plugin_name.setEnabled(true);
            jB_edit.setEnabled(true);
            jTree.setEnabled(false);
            jTree.setBackground(Color.gray);
            //TREE_ENABLED=false;
            
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Warning: All configured data in Body, Totalizer and Footer will be lost ! Please confirm operation.","Warning",6);
            Yes_No = op.getAnswer();
            if(Yes_No == 1)// faccio la remove dei b,t,f.
            { 
                flagCLEARELEMENTS=true;
                int len=schemaAttuale.size();
                
                Vector appo=new Vector();
                
                for(int i=0;i<len;i++)
                {
                    appo.addElement( (ReportObject.elementoBase)(schemaAttuale.vGetElementoBase()).elementAt(i) );
                }
                
                //System.out.println("appo.size()="+len);
                for(int i=0;i<len;i++)
                {
                    ReportObject.elementoBase ebTrovato=(ReportObject.elementoBase)appo.elementAt(i);
                    //System.out.println("TAG="+ebTrovato.OBJECT_TAG+"   TYPE="+ebTrovato.TYPE);
                    if(ebTrovato.TYPE==ReportObject.HEADER)
                    {

                    }else
                    {
                        
                        schemaAttuale.addElementTOREMOVE(ebTrovato);
                        schemaAttuale.deleteObj(ebTrovato);
                    }
                }
            }else
            {
                jR_use_plugin.setSelected(false);
                jB_edit.setEnabled(false);
            }
            
        }
        setTree();
        setInterface2();
        /*if(Yes_No == 1)// faccio la remove dei b,t,f.
        { 
            this.jpButton.enableCommit();
        }*/
        
    }//GEN-LAST:event_jR_use_pluginActionPerformed

    private void disableInterface(boolean state)
    {
        TREE_ENABLED=state;
        jC_report2.setEnabled(state);
        jT_report_name.setEnabled(state);
        jLabel4.setEnabled(state);
        jR_use_plugin.setEnabled(state);
        jT_plugin_name.setEnabled(state);
        jT_plugin_name.setEnabled(state);
        jTree.setEnabled(state);
        
        
        if(state==false)
        {
            jTree.setBackground(Color.gray);
        }
        else
        {
            jTree.setBackground(Color.white);
        }
        
        jR_plain.setEnabled(state);
        jR_borderer.setEnabled(state);
        //jR_excel.setEnabled(state);
        jC_excel_new.setEnabled(state);
        jLabel1.setEnabled(state);
        jLabel5.setEnabled(state);
        jB_b.setEnabled(state);
        jB_f.setEnabled(state);
    }
    
    private void jC_report1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        this.jpButton.enableCommit(true);
        flagNewReport=false;
        //ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
        primaSTR=ADAMS_GlobalParam.TAG_SELEZIONATO;
        dopoSTR=(String)jC_report1.getSelectedItem();
           
        
        //System.out.println("PrimaSTR="+primaSTR);
        //System.out.println("DopoSTR="+dopoSTR);
        flagCLEARELEMENTS=false;
        if ( (!primaSTR.equals("<none selected>")) ) 
        {
            boolean testMODIFY=false;
            if( !primaSTR.equals("<Add new Report Schema>"))
            {
                ReportObject scAPPO=new ReportObject(config_NAME_ALIAS,primaSTR);
                scAPPO.readHeaderReport();
                //hrINIZIALE=(ReportObject.elementoBase)scAPPO.getHeaderReport();
                hrINIZIALE=new ReportObject.elementoBase();
                ReportObject.elementoBase hr=(ReportObject.elementoBase)scAPPO.getHeaderReport();
                //hrINIZIALE=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
                hrINIZIALE.TIPO_DI_CONFIGURAZIONE=hr.TIPO_DI_CONFIGURAZIONE;
                hrINIZIALE.TYPE=hr.TYPE;
                hrINIZIALE.TAG=hr.TAG;
                hrINIZIALE.OBJECT_IDANALISYS=hr.OBJECT_IDANALISYS;
                hrINIZIALE.OBJECT_USEPLUGIN=hr.OBJECT_USEPLUGIN;
                hrINIZIALE.OBJECT_PLUGINNAME=hr.OBJECT_PLUGINNAME;
                hrINIZIALE.OBJECT_SIMPLEBODY=hr.OBJECT_SIMPLEBODY;
                hrINIZIALE.OBJECT_HASBORDER=hr.OBJECT_HASBORDER;
                hrINIZIALE.OBJECT_EXCELCSV=hr.OBJECT_EXCELCSV;
                hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_R=hr.OBJECT_DEFAULT_BACKGROUND_R;
                hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_G=hr.OBJECT_DEFAULT_BACKGROUND_G;
                hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_B=hr.OBJECT_DEFAULT_BACKGROUND_B;
                hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_R=hr.OBJECT_DEFAULT_FOREGROUND_R;
                hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_G=hr.OBJECT_DEFAULT_FOREGROUND_G;
                hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_B=hr.OBJECT_DEFAULT_FOREGROUND_B;
                
                testMODIFY=testModify();
                //System.out.println("testMODIFY 1)="+testMODIFY);
            }else
            {
                testMODIFY=true;
                //System.out.println("testMODIFY 2)="+testMODIFY);
            }
            
            if( (!primaSTR.equals(dopoSTR)) && (testMODIFY==true))
            {

                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Warning: Any changes made will be lost ! Do you really want to change report ?","Warning",6);
                int Yes_No = op.getAnswer();                
                if(Yes_No == 1)
                {
                    this.jpButton.disableCommit();
                    ADAMS_GlobalParam.MODIFY_ELEMENT=0;
                    ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
                }else
                {
                    jC_report1.removeActionListener(evento2);
                    jC_report1.setSelectedItem(primaSTR);
                    jC_report1.addActionListener(evento2);
                    return;
                } 
            }else
            {
                this.jpButton.disableCommit();
            }
        }
        
        jC_report2.removeActionListener(evento);  
        if(jC_report1.getSelectedIndex()==0)
        {
            
            //System.out.println("disableButton");
            //jpButton.disableButton(false);
            
            resetInterface();
            disableInterface(false);
            
        }else if(jC_report1.getSelectedIndex()==1) //nuovo
        {
            flagNewReport=true;
            disableInterface(true);
            resetInterface();
            
            schemaAttuale=new ReportObject(config_NAME_ALIAS);
            schemaAttuale.creaHeaderReport();
            schemaAttuale.readAllTypeReport();
            report=schemaAttuale;
            jT_report_name.setSelectionStart(0);
            jT_report_name.setSelectionEnd(jT_report_name.getText().length());
            jT_report_name.requestFocus();
        }else
        {
            disableInterface(true);
            jT_report_name.setText((String)jC_report1.getItemAt(jC_report1.getSelectedIndex()));
            if(schemaAttuale!=null)
            {
                schemaAttuale.resetAll();
            }

            //System.out.println("Carico lo schema attuale per il report: "+(String)jC_report1.getItemAt(jC_report1.getSelectedIndex()));
            schemaAttuale=new ReportObject(config_NAME_ALIAS,(String)jC_report1.getItemAt(jC_report1.getSelectedIndex()));

            schemaAttuale.readAllObject();

            schemaAttuale.readHeaderReport();

            schemaAttuale.readAllTypeReport();
            setInterface();
            report=schemaAttuale;
        }
        jC_report2.addActionListener(evento);  
        ADAMS_GlobalParam.TAG_SELEZIONATO=(String)jC_report1.getSelectedItem();
    }
    
    public boolean canClone()
    {
        boolean flag=true;
        
        if(jC_report1.getSelectedIndex()<2)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"You have to select a Report Schema first...","ERROR",1);
            flag=false;
        }

        return flag;
    }
    
    public boolean deleteReport(boolean conConferma)
    {
        boolean flag=false;

        if(conConferma==true)
        {
            System.out.println("Cancella con conferma");
            if(jC_report1.getSelectedIndex()<2)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"You have to select a Report Schema first...","ERROR",1);
                flag=false;
            }else
            {

                String report_name=jT_report_name.getText();
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Delete Report Schema["+config_NAME_ALIAS+"-"+report_name+"]. Please confirm operation.","Info",6);
                int Yes_No = op.getAnswer();

                if(Yes_No == 1)
                {
			ADAMS_JD_Message op1 = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"About to delete Report Schema ["+config_NAME_ALIAS+"-"+report_name+"]. Please confirm operation.","Info",6);
                	int Yes_No1 = op1.getAnswer();
			if(Yes_No1 == 1)
                	{

                     		flag=schemaAttuale.deleteReport();
                     		if(flag==true)
                     		{
                                        
                                        jC_report1.removeActionListener(evento2);
                         		jC_report1.setSelectedIndex(0);
					jC_report1.removeItem((String)report_name);
                                        jC_report1.addActionListener(evento2);
                                        
                                        ADAMS_GlobalParam.TAG_SELEZIONATO=primaSTR="<none selected>";
                                        
                                        resetInterface();
                                        disableInterface(false);
                                        fattaDELETE=true;
                                        
                     		}
			}else
				flag=false;
                }else
                    flag=false;
            }
        }else
        {
            System.out.println("Cancella senza conferma");
            schemaAttuale.deleteReport(config_NAME_ALIAS,jT_report_name.getText());
        }

        return flag;
    }
    
    public int isPresentReport(String str)
    {
        return report.isPresentReport(str);
        /*int len=jC_report1.getItemCount();
        for(int i=0;i<len;i++)
        {
            String appo=(String)jC_report1.getItemAt(i);
            if(appo.equals(str)
                return true;                  
        }
        return false;*/
    }
    
    public boolean commit()
    {
        System.out.println("************ COMMIT ****************");
        String report_name=jT_report_name.getText();
        boolean flag = false;

        if(jC_report2.getSelectedIndex()==0)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"You have to select a Counter Kit first...","ERROR",1);
            return false;
        }

        if( jT_report_name.getText().equals("") )
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"Undefined report name.","ERROR",1);
            return false;
        }

        int lenEB=schemaAttuale.size();

        if(lenEB==0)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"You must define at least one HEADER, BODY, TOTALIZER or FOOTER !","ERROR",1);
            return false;
        }

        String strToAdd=jT_report_name.getText();
        int iRet=report.isPresentReport(jT_report_name.getText());
        //System.out.println("1) isPresentReport("+jT_report_name.getText()+")"+iRet);
        if( iRet!=-1 )
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Report Schema ["+report_name+"] alreay present. Do you want to overwrite ?","Info",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)// faccio la commit.
            {
                //deleteReport(false);
                flag=true;

                //aggiungi elementi base a schema attuale.
                getInterface();

                schemaAttuale.commit(iRet);
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(config_NAME_ALIAS);
                commitFatta=true;
                flagNewReport=false;
                ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
                //setInterface();

                ReportObject.elementoBase hr=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
                //hrINIZIALE=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
                hrINIZIALE.TIPO_DI_CONFIGURAZIONE=hr.TIPO_DI_CONFIGURAZIONE;
                hrINIZIALE.TYPE=hr.TYPE;
                hrINIZIALE.TAG=hr.TAG;
                hrINIZIALE.OBJECT_IDANALISYS=hr.OBJECT_IDANALISYS;
                hrINIZIALE.OBJECT_USEPLUGIN=hr.OBJECT_USEPLUGIN;
                hrINIZIALE.OBJECT_PLUGINNAME=hr.OBJECT_PLUGINNAME;
                hrINIZIALE.OBJECT_SIMPLEBODY=hr.OBJECT_SIMPLEBODY;
                hrINIZIALE.OBJECT_HASBORDER=hr.OBJECT_HASBORDER;
                hrINIZIALE.OBJECT_EXCELCSV=hr.OBJECT_EXCELCSV;
                hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_R=hr.OBJECT_DEFAULT_BACKGROUND_R;
                hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_G=hr.OBJECT_DEFAULT_BACKGROUND_G;
                hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_B=hr.OBJECT_DEFAULT_BACKGROUND_B;
                hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_R=hr.OBJECT_DEFAULT_FOREGROUND_R;
                hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_G=hr.OBJECT_DEFAULT_FOREGROUND_G;
                hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_B=hr.OBJECT_DEFAULT_FOREGROUND_B;
		this.jpButton.disableCommit();

                return true;
            }else
                return false;
        }

        if( (jR_use_plugin.isSelected()) && jT_plugin_name.getText().equals("") )
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"Undefined Plugin name.","ERROR",1);
            return false;
        }

        ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Commit Report Schema["+report_name+"]. Please confirm operation. ?","Info",6);
        int Yes_No = op.getAnswer();

	iRet=report.isPresentReport(ADAMS_GlobalParam.lastReportName);
        //iRet=report.isPresentReport(jT_report_name.getText());
        //System.out.println("2) isPresentReport("+ADAMS_GlobalParam.lastReportName+")"+iRet);
        if(Yes_No >0)// faccio la commit.
        {
            flag=true;

            //aggiungi elementi base a schema attuale.
            getInterface();


            schemaAttuale.commit(iRet);
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(config_NAME_ALIAS);
            commitFatta=true;
            flagNewReport=false;

            /* aggiorno la lista dei report disponibili*/

            report = new ReportObject(config_NAME_ALIAS);
            report.readAllTypeReport();
            vReport=report.getReport();


            jC_report1.removeActionListener(evento2);
            jC_report1.removeAllItems();
            for(int i=0;i<vReport.size();i++)
            {
                ReportObject.elementoBase e=(ReportObject.elementoBase)vReport.elementAt(i);
                jC_report1.addItem(e.TAG);
            }
            jC_report1.setSelectedItem(jT_report_name.getText());
            jC_report1.addActionListener(evento2);
            ADAMS_GlobalParam.TAG_SELEZIONATO=(String)jC_report1.getSelectedItem();
		this.jpButton.disableCommit();
        }else
        {
            flag=false;
        }



        ADAMS_GlobalParam.MODIFY_ELEMENT=0;

        return flag;
    }
    
    public void aggiornaListaReport()
    {
            report = new ReportObject(config_NAME_ALIAS);
            report.readAllTypeReport();
            vReport=report.getReport();

            jT_report_name.setText((String)jC_report1.getSelectedItem());
            jC_report1.removeActionListener(evento2);
            jC_report1.removeAllItems();
            for(int i=0;i<vReport.size();i++)
            {
                ReportObject.elementoBase e=(ReportObject.elementoBase)vReport.elementAt(i);
                jC_report1.addItem(e.TAG);
            }
            jC_report1.setSelectedIndex(0);
            jT_report_name.setEnabled(false);
            jC_report1.addActionListener(evento2);
            ADAMS_GlobalParam.TAG_SELEZIONATO=(String)jC_report1.getSelectedItem();
            this.jpButton.disableCommit();
    }

    public ReportObject.elementoBase getInterfaceAPPO()
    {

        if(schemaAttuale==null)
        {
            return null;
        }
        ReportObject.elementoBase hrAPPO=(ReportObject.elementoBase)schemaAttuale.getHeaderReport();
        
        hrAPPO.TIPO_DI_CONFIGURAZIONE=config_NAME_ALIAS;
        hrAPPO.TYPE=ReportObject.ELEMENTO_COMUNE;
        hrAPPO.TAG=jT_report_name.getText();
        hrAPPO.OBJECT_IDANALISYS=getIDAnalisi(jC_report2.getSelectedIndex());
        
        

        if(jR_use_plugin.isSelected())
        {
            hrAPPO.OBJECT_USEPLUGIN=1;
            hrAPPO.OBJECT_PLUGINNAME=jT_plugin_name.getText();
        }else
        {
            hrAPPO.OBJECT_USEPLUGIN=0;
            hrAPPO.OBJECT_PLUGINNAME="";
            jT_plugin_name.setText("");
        }
        
        if(jR_plain.isSelected())
        {
            hrAPPO.OBJECT_SIMPLEBODY=1;
        }else
        {
            hrAPPO.OBJECT_SIMPLEBODY=0;
        }
        
        if(jR_borderer.isSelected())
        {
            hrAPPO.OBJECT_HASBORDER=1;
        }else
        {
            hrAPPO.OBJECT_HASBORDER=0;
        }
        
        /*if(jR_excel.isSelected()) // Nuova gestione Report in formato excel - Raffo 08-02-2012
        {
            hrAPPO.OBJECT_EXCELCSV=1;
        }else
        {
            hrAPPO.OBJECT_EXCELCSV=0;
        }*/
        hrAPPO.OBJECT_EXCELCSV = jC_excel_new.getSelectedIndex();
        
        hrAPPO.OBJECT_DEFAULT_BACKGROUND_R=jB_b.getBackground().getRed();
        hrAPPO.OBJECT_DEFAULT_BACKGROUND_G=jB_b.getBackground().getGreen();
        hrAPPO.OBJECT_DEFAULT_BACKGROUND_B=jB_b.getBackground().getBlue();
        hrAPPO.OBJECT_DEFAULT_FOREGROUND_R=jB_f.getBackground().getRed();
        hrAPPO.OBJECT_DEFAULT_FOREGROUND_G=jB_f.getBackground().getGreen();
        hrAPPO.OBJECT_DEFAULT_FOREGROUND_B=jB_f.getBackground().getBlue();
        
        return hrAPPO;
    }
    
    public boolean testModify1() // non utilizzato
    {
        //System.out.println("testModify1()");
        boolean testMODIFY=false;
        
        if(flagNewReport==true)
        {
            return true;
        }
        
        if(jC_report1.getSelectedIndex()==0)
        {
            return false;
        }
          
        return testMODIFY;
    }
    
    public boolean testModify() // non utilizzato
    {
        
        if(fattaDELETE==true)
        {
            fattaDELETE=false;
            nTest=23;
            
            return false;
        }
        
        if(flagCLEARELEMENTS==true)
        {
            nTest=22;
            
            return true;
        }
        
        boolean testMODIFY=false;
        
        ReportObject.elementoBase hrAPPO=getInterfaceAPPO();
        
        
        if(hrINIZIALE==null)
        {
            nTest=21;
            
            return false;
        }
        
        if(hrAPPO==null)
        {
            nTest=20;
            
            return false;
        }
            
        
        if(flagNewReport==true)
        {
            nTest=1;
            return true;
        }
        

        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            nTest=2;
            
            return true;
        }
        
        if(hrINIZIALE.TAG==null)
        {
            nTest=3;
            
            return false;
        }
        
        /*
        System.out.println("hrINIZIALE.TAG="+hrINIZIALE.TAG);
        System.out.println("hrAPPO.TAG="+hrAPPO.TAG);
        
        
        System.out.println("hrINIZIALE.TIPO_DI_CONFIGURAZIONE="+hrINIZIALE.TIPO_DI_CONFIGURAZIONE);
        System.out.println("hrAPPO.TIPO_DI_CONFIGURAZIONE="+hrAPPO.TIPO_DI_CONFIGURAZIONE);
        
        
        System.out.println("hrINIZIALE.OBJECT_USEPLUGIN="+hrINIZIALE.OBJECT_USEPLUGIN);
        System.out.println("hrAPPO.OBJECT_USEPLUGIN="+hrAPPO.OBJECT_USEPLUGIN);
        */
        
        if(!hrINIZIALE.TIPO_DI_CONFIGURAZIONE.equals(hrAPPO.TIPO_DI_CONFIGURAZIONE))
        {
            nTest=4;
            
            testMODIFY=true;
            return testMODIFY;
        }

        
        if(!hrINIZIALE.TAG.equals(hrAPPO.TAG))
        {
            nTest=5;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_IDANALISYS!=hrAPPO.OBJECT_IDANALISYS)
        {
            nTest=6;
            
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(hrINIZIALE.OBJECT_USEPLUGIN!=hrAPPO.OBJECT_USEPLUGIN)
        {
            nTest=7;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(!hrINIZIALE.OBJECT_PLUGINNAME.equals(hrAPPO.OBJECT_PLUGINNAME))
        {
            nTest=8;
            
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(hrINIZIALE.OBJECT_SIMPLEBODY!=hrAPPO.OBJECT_SIMPLEBODY)
        {
            nTest=9;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_HASBORDER!=hrAPPO.OBJECT_HASBORDER)
        {
            nTest=10;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_EXCELCSV!=hrAPPO.OBJECT_EXCELCSV)
        {
            nTest=11;
            
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_R!=hrAPPO.OBJECT_DEFAULT_BACKGROUND_R)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_G!=hrAPPO.OBJECT_DEFAULT_BACKGROUND_G)
        {
            nTest=24;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_DEFAULT_BACKGROUND_B!=hrAPPO.OBJECT_DEFAULT_BACKGROUND_B)
        {
            nTest=25;
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_R!=hrAPPO.OBJECT_DEFAULT_FOREGROUND_R)
        {
            nTest=25;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_G!=hrAPPO.OBJECT_DEFAULT_FOREGROUND_G)
        {
            nTest=12;
            
            testMODIFY=true;
            return testMODIFY;
        }
        if(hrINIZIALE.OBJECT_DEFAULT_FOREGROUND_B!=hrAPPO.OBJECT_DEFAULT_FOREGROUND_B)
        {
            nTest=13;
            
            testMODIFY=true;
            return testMODIFY;
        }
        
        
        //System.out.println("return testModify="+testMODIFY);
        return testMODIFY;
    }
    
    public boolean ordinaIndici()
    {
        Vector listaReportV=new Vector();
        int numElement=jC_report1.getItemCount();
        //System.out.println("numElement="+numElement);
        for(int i=0;i<numElement;i++)
        {
            String str=getElement(i);
            listaReportV.addElement(str);
            System.out.println("STR="+str);
        }
        
        int len=numElement;
        
        for(int i=0;i<len-1;i++)
        {
                int min=i;
                for(int j=i+1;j<len;j++)
                        if( (getElement(j)).compareTo(getElement(min))<0 )
                        {
                                System.out.println(getElement(j)+"<"+getElement(min));
                                min=j;	
                        }

                if(i!=min)
                {
                    System.out.println("Prima="+getElement(i)+"<"+getElement(min));
                    String appo=getElement(i);
                    String appo1=getElement(min);

                    //listaReportV.removeElementAt(i);		
                    listaReportV.setElementAt(appo1,i);
                    //listaReportV.removeElementAt(min);
                    listaReportV.setElementAt(appo,min);
                    System.out.println("Dopo="+getElement(i)+"<"+getElement(min));
                    
                }
        }
        jC_report1.removeAllItems();
        
        //jC_report1.addItem("<none selected>");
        //jC_report1.addItem("<Add new Report Schema>");
        for(int i=0;i<numElement;i++)
        {
            
            String str=(String)listaReportV.elementAt(i);
            jC_report1.addItem(str);
            System.out.println("STR1="+str);
        }
        return true;
    }
    
    public String getElement(int pos)
    {
        return ((String)jC_report1.getItemAt(pos));
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenuItem jM_add;
    private javax.swing.JMenuItem jM_mod;
    private javax.swing.JMenuItem jM_del;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem jM_up;
    private javax.swing.JMenuItem jM_down;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenu jM_action;
    private javax.swing.JMenuItem jM_break;
    private javax.swing.JMenuItem jM_blank;
    private javax.swing.JMenuItem jM_separetor;
    private javax.swing.JPanel jP_p5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jP_top;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JComboBox jC_report1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jP_p2;
    private javax.swing.JComboBox jC_report2;
    private javax.swing.JTextField jT_report_name;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jP_p3;
    private javax.swing.JRadioButton jR_use_plugin;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jT_plugin_name;
    private javax.swing.JButton jB_edit;
    private javax.swing.JPanel jP_p4;
    private javax.swing.JRadioButton jR_plain;
    private javax.swing.JRadioButton jR_borderer;
    private javax.swing.JRadioButton jR_excel;
    private javax.swing.JComboBox jC_excel_new;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jB_b;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jB_f;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

}
