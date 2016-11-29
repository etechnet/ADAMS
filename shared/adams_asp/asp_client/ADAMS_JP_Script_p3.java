/*
 * ADAMS_JP_Script_p3.java
 *
 * Created on 24 ottobre 2005, 13.03
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */

import java.awt.Cursor;
import java.util.Vector;
import java.sql.*;
import javax.swing.DefaultCellEditor;
import javax.swing.table.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class ADAMS_JP_Script_p3 extends javax.swing.JPanel {

    public static class vectorTab
    {
        public String st1="";
        public String st2="";
        
        public vectorTab(String st1, String st2)
        {
            this.st1=st1;
            this.st2=st2;
        }
    }
    
    private boolean                                 DEBUG                       = false;
    private int[]                                   CellDimension               = {50};
    private String[]                                nomeCampi                   = {"COD_VALORE"};
    private String[]                                descCampi                   = {"Valore"};
    private int                                     minCellDimension            = 50;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private javax.swing.JTable                      jTable_general1             = null;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general1  = null;
    private javax.swing.JTable                      jTable_general2             = null;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general2  = null;
    private javax.swing.JTable                      jTable_general3             = null;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general3  = null;
    private javax.swing.JTable                      jTable_general4             = null;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general4  = null;
    private ADAMS_Vector_TAB_CONFIG                teList                      = null;
    private ADAMS_JF_WIZARDBASE                      parent                      = null;
    private int                                     tableSelected               = -1;
    private Vector                                  vTab1                       = new Vector();
    private Vector                                  vTab3                       = new Vector();
    private Vector                                  vTab2                       = new Vector();
    private Vector                                  vTab4                       = new Vector();
    private int                                     idCounterKit                = -1;
    private String                                  config_NAME_ALIAS           = "";
    
    public  Vector getV1()
    {
        return vTab1;
    }
    
    public  Vector getV3()
    {
        return vTab3;
    }
    
    public void setInterface(ScriptObject scriptObject)
    {
        vTab1.removeAllElements();
        vTab2.removeAllElements();
        vTab3.removeAllElements();
        vTab4.removeAllElements();
        
        Vector v1,v2;
        v1=scriptObject.sc.VARIABLES1;
        v2=scriptObject.sc.VARIABLES2;
        
        for(int i=0;i<v1.size();i++)
        {
            vTab1.addElement(new vectorTab((String)v1.elementAt(i),""));
        }
        
        boolean trovato = false;
        for(int i=0;i<teList.size();i++)
        {
            trovato=false;
            ADAMS_TAB_CONFIG elementoRiga = (ADAMS_TAB_CONFIG)teList.elementAt(i);
            for(int j=0;j<v1.size();j++)
            {
                String e1,e2;
                e1=(String)elementoRiga.TAG;
                e2=(String)v1.elementAt(j);
                if(e1.equals(e2))
                {
                    trovato=true;
                    break;
                }
            }
            if(trovato==false)
                vTab2.addElement(new vectorTab(new String(elementoRiga.TAG),new String(elementoRiga.DESCRIPTION)));
        }
        
        for(int i=0;i<v2.size();i++)
        {
            vTab3.addElement(new vectorTab((String)v2.elementAt(i),""));
        }
        
        getCounterKit();
        Vector appo=new Vector();
        for(int i=0;i<vTab4.size();i++)
        {
            appo.addElement((vectorTab)vTab4.elementAt(i));
        }
        vTab4.removeAllElements();
        
        trovato = false;
        for(int i=0;i<appo.size();i++)
        {
            trovato=false;
            vectorTab elementoRiga = (vectorTab)appo.elementAt(i);
            for(int j=0;j<v2.size();j++)
            {
                String e1,e2;
                e1=(String)elementoRiga.st1;
                e2=(String)v2.elementAt(j);
                if(e1.equals(e2))
                {
                    trovato=true;
                    break;
                }
            }
            if(trovato==false)
                vTab4.addElement(new vectorTab(new String(elementoRiga.st1),new String(elementoRiga.st2)));
        }
        
        
        updateTable(1);
        updateTable(2);
        updateTable(3);
        updateTable(4);
        
        
    }
    
    private boolean isPresent(int id_vTab,String s1,String s2)
    {
        boolean flag=false;
        Vector appoV=new Vector();
        switch(id_vTab)
        {
            case 1:
                appoV=vTab1;
                break;
            case 3:
                appoV=vTab3;
                break;
        }
        
        for(int i=0;i<appoV.size();i++)
        {
            vectorTab eleme=(vectorTab)appoV.elementAt(i);
            String st1=eleme.st1;
            String st2=eleme.st2;
            
            if( (st1.equals(s1)) && (st2.equals(s2)) )
            {
                flag=true;
                break;
            }
        }
        return flag;
    }
    /** Creates new form ADAMS_JP_Script_p3 */
    public ADAMS_JP_Script_p3(String config_NAME_ALIAS,int idCounterKit,ADAMS_JF_WIZARDBASE parent,ADAMS_Vector_TAB_CONFIG teList) 
    {
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        this.idCounterKit=idCounterKit;
        this.parent=parent;
        this.teList=teList;
        initComponents();
        
        
            
        jL_1.setText("<html><center><b>In order to use your script you must define a list of external variables involved with it. External variables will be valorized automagically "+"<b><font color=#0000ff>before</font>"+" script execution.</b><br>");
        
        jScrollPane1.setBackground(new java.awt.Color(230,230,230));
        jScrollPane2.setBackground(new java.awt.Color(230,230,230));
        
        jScrollPane3.setBackground(new java.awt.Color(230,230,230));
        jScrollPane4.setBackground(new java.awt.Color(230,230,230));
              
        
        for(int i=0;i<teList.size();i++)
        {

            ADAMS_TAB_CONFIG elementoRiga = (ADAMS_TAB_CONFIG)teList.elementAt(i);
            vTab2.addElement(new vectorTab(new String(elementoRiga.TAG),new String(elementoRiga.DESCRIPTION)));
        }
        
        
        
        setTable1();
        setTable2();
        setTable3();
        setTable4();
        
        //if(idCounterKit!=-1)
            getCounterKit();
        
        setFont();
        setCursorWidget();
        
        if(parent.flagREPORT==ADAMS_GlobalParam.SCRIPT_TE)
        {
            vTab2.addElement(new vectorTab("keypart",""));
            vTab2.addElement(new vectorTab("currentkey",""));
        }
        
        updateTable(2);
        
        
        //defaul di partenza
        enableGUI(false);
        
        if(idCounterKit!=-1)
        {
            jR_3.setEnabled(true);
            setEnabled_t1(true);
            setEnabled_t2(true);
        }else
            setEnabled_t1(true);
            
    }
    
    public void refreshPanel()
    {
        //parent.nextButtonSetEnabled(true);
    }
    
    public void enableGUI(boolean flag)
    {
        jScrollPane1.setEnabled(flag);
        jScrollPane2.setEnabled(flag);
        jScrollPane3.setEnabled(flag);
        jScrollPane4.setEnabled(flag);
        jButton1.setEnabled(flag);
        jButton2.setEnabled(flag);
        jButton3.setEnabled(flag);
        jButton5.setEnabled(flag);
        jButton6.setEnabled(flag);
        jButton7.setEnabled(flag);
        jTable_general1.setEnabled(flag);
        jTable_general2.setEnabled(flag);
        jTable_general3.setEnabled(flag);
        jTable_general4.setEnabled(flag);
    }
    
    public void setEnabled_t1(boolean flag)
    {
        jScrollPane1.setEnabled(flag);
        jScrollPane2.setEnabled(flag);
        
        jButton1.setEnabled(flag);
        jButton2.setEnabled(flag);
        jButton3.setEnabled(flag); 
        
        jTable_general1.setEnabled(flag);
        jTable_general2.setEnabled(flag);
    }
    
    public void setEnabled_t2(boolean flag)
    {
        jScrollPane3.setEnabled(flag);
        jScrollPane4.setEnabled(flag);
        
        jButton5.setEnabled(flag);
        jButton6.setEnabled(flag);
        jButton7.setEnabled(flag);

        jTable_general3.setEnabled(flag);
        jTable_general4.setEnabled(flag);
    }
    
    public void setEnabledAlarm(boolean flag)
    {        
        jR_1.setEnabled(!flag);
        jR_2.setEnabled(!flag);
        jR_3.setEnabled(flag);
        jR_3.setSelected(flag);  
    }
    
    public void setEnabledScript(boolean flag)
    {
        
        jR_1.setSelected(flag);
        jR_1.setEnabled(flag);
        jR_2.setEnabled(!flag);
        jR_3.setEnabled(!flag);  
    }
    
    
    public void setEnabledReport(boolean flag)
    {
        jR_1.setEnabled(!flag);
        jR_2.setEnabled(flag);
        jR_3.setEnabled(!flag);
        jR_2.setSelected(flag);
        
        enableGUI(false);
        setEnabled_t2(true);
    }
    
    
    
    public void setFont()
    {
    }
    
    public void setCursorWidget()
    {
        jR_1.setCursor(Cur_hand);
        jR_2.setCursor(Cur_hand);
        jR_3.setCursor(Cur_hand);
        jButton1.setCursor(Cur_hand);
        jButton2.setCursor(Cur_hand);
        jButton3.setCursor(Cur_hand);
        
        jButton5.setCursor(Cur_hand);
        jButton6.setCursor(Cur_hand);
        jButton7.setCursor(Cur_hand);

        jTable_general1.setCursor(Cur_hand);
        jTable_general2.setCursor(Cur_hand);
        jTable_general3.setCursor(Cur_hand);
        jTable_general4.setCursor(Cur_hand);
    }
    
    public void setTable1()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general1 = new javax.swing.JTable();
        jTable_general1.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
          "Valore"
        }
        ) {
          Class[] types = new Class [] {
              java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general1.setAutoCreateColumnsFromModel(false);
        jTable_general1.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general1.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general1.setRowHeight(20);
        jTable_general1.setRowMargin(2);
        jTable_general1.setSelectionBackground(java.awt.Color.green);
        jTable_general1.setVerifyInputWhenFocusTarget(false);
        jTable_general1.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_general_1_MouseReleased(evt);
          }
        });

        jScrollPane1.setViewportView(jTable_general1);

        jTable_general1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JTableHeader header = jTable_general1.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general1.getColumnCount(); i++)
        {
            column = jTable_general1.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general1 = (javax.swing.table.DefaultTableModel)jTable_general1.getModel();
        
        this.setCursor(Cur_default);
    }
    
    public void setTable2()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general2 = new javax.swing.JTable();
        jTable_general2.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
          "Valore"
        }
        ) {
          Class[] types = new Class [] {
              java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general2.setAutoCreateColumnsFromModel(false);
        jTable_general2.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general2.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general2.setRowHeight(20);
        jTable_general2.setRowMargin(2);
        jTable_general2.setSelectionBackground(java.awt.Color.green);
        jTable_general2.setVerifyInputWhenFocusTarget(false);
        jTable_general2.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_general_2_MouseReleased(evt);
          }
        });

        jScrollPane2.setViewportView(jTable_general2);

        jTable_general2.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JTableHeader header = jTable_general2.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general2.getColumnCount(); i++)
        {
            column = jTable_general2.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general2 = (javax.swing.table.DefaultTableModel)jTable_general2.getModel();
        
        this.setCursor(Cur_default);
    }
    
    public void setTable3()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general3 = new javax.swing.JTable();
        jTable_general3.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general3.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
          "Values"
        }
        ) {
          Class[] types = new Class [] {
              java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general3.setAutoCreateColumnsFromModel(false);
        jTable_general3.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general3.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general3.setRowHeight(20);
        jTable_general3.setRowMargin(2);
        jTable_general3.setSelectionBackground(java.awt.Color.green);
        jTable_general3.setVerifyInputWhenFocusTarget(false);
        jTable_general3.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_general_3_MouseReleased(evt);
          }
        });

        jScrollPane3.setViewportView(jTable_general3);

        jTable_general3.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JTableHeader header = jTable_general3.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general3.getColumnCount(); i++)
        {
            column = jTable_general3.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general3 = (javax.swing.table.DefaultTableModel)jTable_general3.getModel();
        
        this.setCursor(Cur_default);
    }
    
    public void setTable4()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general4 = new javax.swing.JTable();
        jTable_general4.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general4.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
          "Values"
        }
        ) {
          Class[] types = new Class [] {
              java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general4.setAutoCreateColumnsFromModel(false);
        jTable_general4.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general4.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general4.setRowHeight(20);
        jTable_general4.setRowMargin(2);
        jTable_general4.setSelectionBackground(java.awt.Color.green);
        jTable_general4.setVerifyInputWhenFocusTarget(false);
        jTable_general4.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_general_4_MouseReleased(evt);
          }
        });

        jScrollPane4.setViewportView(jTable_general4);

        jTable_general4.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JTableHeader header = jTable_general4.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general4.getColumnCount(); i++)
        {
            column = jTable_general4.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general4 = (javax.swing.table.DefaultTableModel)jTable_general4.getModel();
        
        this.setCursor(Cur_default);
    }
    
    private void updateTable(int idTable)
    {
        javax.swing.JTable                      jTabple_appo = null;
        javax.swing.table.DefaultTableModel     defaultTableModel_appo  = null;
        javax.swing.JScrollPane                 jScroll_appo = null;
        //System.out.println("idTable="+idTable+"   tableSelected="+tableSelected);
        switch(idTable)
        {
            case 1:
                    jTabple_appo=jTable_general1;
                    defaultTableModel_appo=defaultTableModel_general1;
                    jScroll_appo=jScrollPane1;
                break;
            case 2:
                    jTabple_appo=jTable_general2;
                    defaultTableModel_appo=defaultTableModel_general2;
                    jScroll_appo=jScrollPane2;
                break;
            case 3:
                    jTabple_appo=jTable_general3;
                    defaultTableModel_appo=defaultTableModel_general3;
                    jScroll_appo=jScrollPane3;
                break;
            case 4:
                    jTabple_appo=jTable_general4;
                    defaultTableModel_appo=defaultTableModel_general4;
                    jScroll_appo=jScrollPane4;
                    
                break;
        }
        
        defaultTableModel_appo.getDataVector().removeAllElements();
        jTabple_appo.clearSelection();
        
        switch(idTable)
        {
            case 1:
            {
                 for(int i=0;i<vTab1.size();i++)
                {

                    vectorTab elementoRiga = (vectorTab)vTab1.elementAt(i);

                    Vector row=new Vector();
                    row.addElement(new String(elementoRiga.st1));
                    //row.addElement(new String(elementoRiga.st2));

                    defaultTableModel_appo.addRow(row);
                }   
            }break;
            
            case 2:
            {
                for(int i=0;i<vTab2.size();i++)
                {

                    vectorTab elementoRiga=(vectorTab)vTab2.elementAt(i);

                    Vector row=new Vector();
                    row.addElement(elementoRiga.st1);
                    //row.addElement(elementoRiga.st2);

                    defaultTableModel_appo.addRow(row);
                }
            }break;
            
            case 3:
            {
                 for(int i=0;i<vTab3.size();i++)
                {

                    vectorTab elementoRiga = (vectorTab)vTab3.elementAt(i);

                    Vector row=new Vector();
                    row.addElement(new String(elementoRiga.st1));
                    //row.addElement(new String(elementoRiga.st2));

                    defaultTableModel_appo.addRow(row);
                }   
            }break;
            
            case 4:
            {
                for(int i=0;i<vTab4.size();i++)
                {

                    vectorTab elementoRiga=(vectorTab)vTab4.elementAt(i);

                    Vector row=new Vector();
                    row.addElement(elementoRiga.st1);
                    //row.addElement(elementoRiga.st2);

                    defaultTableModel_appo.addRow(row);
                }
            }break;
        }    
        
        jTabple_appo.validate();
        jTabple_appo.repaint();
        
        jScroll_appo.setViewportView(jTabple_appo);        
    }
    
    
    private void jTable_general_1_MouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here
        tableSelected=1;
    }
    
    private void jTable_general_2_MouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here
        tableSelected=2;
    }
    
    private void jTable_general_3_MouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here
        tableSelected=3;
    }
    
    private void jTable_general_4_MouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here
        tableSelected=4;
    }
    
    private void getCounterKit()
    {
        /*
         * per leggere i contatori:
         * select IDANALISI,IDCOUNTERKIT from tab_analysis_type where IDANALISI=10;
         * select TAG,DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='voice' and IDCOUNTER=10;
         *
         */
        if(parent.flagREPORT==ADAMS_GlobalParam.SCRIPT_REPORT)
        {
            vTab4.addElement(new vectorTab("tNUM_ROWS",""));
            vTab4.addElement(new vectorTab("tRELATION_STR",""));
            vTab4.addElement(new vectorTab("tTOT_REFID",""));
            vTab4.addElement(new vectorTab("PIVOT_RUNNING",""));
        }
        
        String strSelect="";
        

        if(idCounterKit!=-1)
        {
            strSelect="select TAG,DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDCOUNTER="+idCounterKit;
            if(DEBUG)
                System.out.println("ADAMS_JP_Script_p3 [getCounterKit2] ----> "+strSelect);

            Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs1  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);

            if(rs1 != null)
            {
                try
                {
                    while ( rs1.next ( ) ) 
                    {
                        String tag_counter=rs1.getString("TAG");
                        vTab4.addElement(new vectorTab(tag_counter,""));
                        if(DEBUG)
                        {
                            System.out.println("TAG ->"+tag_counter);
                            System.out.println();
                        } 
                    }
                }catch (Exception ex) 
                {
                    System.out.println(ex);
                }  
            }else
            {
                System.out.println("ADAMS_JP_Script_p3 [getCounterKit] ----> rs1==null");
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
        updateTable(4);
        
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jR_1 = new javax.swing.JRadioButton();
        jR_2 = new javax.swing.JRadioButton();
        jR_3 = new javax.swing.JRadioButton();
        jL_1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Feed Selection"));
        jR_1.setText("Traffic Elements");
        jR_1.setToolTipText("Direct");
        buttonGroup1.add(jR_1);
        jR_1.setContentAreaFilled(false);
        jR_1.setFocusPainted(false);
        jR_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jR_1, gridBagConstraints2);
        
        jR_2.setText("Counters");
        jR_2.setToolTipText("Direct");
        buttonGroup1.add(jR_2);
        jR_2.setContentAreaFilled(false);
        jR_2.setFocusPainted(false);
        jR_2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jR_2, gridBagConstraints2);
        
        jR_3.setSelected(true);
        jR_3.setText("Both");
        jR_3.setToolTipText("Direct");
        buttonGroup1.add(jR_3);
        jR_3.setContentAreaFilled(false);
        jR_3.setFocusPainted(false);
        jR_3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jR_3, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.2;
        add(jPanel2, gridBagConstraints1);
        
        jL_1.setText("jLabel4");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.2;
        add(jL_1, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Editor Helpers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "External Variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), java.awt.Color.red));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridheight = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints3);
        
        jButton1.setBackground(new java.awt.Color(230, 230, 230));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forward.png")));
        jButton1.setToolTipText("Add an item to selection");
        jButton1.setFocusPainted(false);
        jButton1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton1.setMinimumSize(new java.awt.Dimension(56, 28));
        jButton1.setPreferredSize(new java.awt.Dimension(56, 28));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jButton1, gridBagConstraints3);
        
        jButton3.setBackground(new java.awt.Color(230, 230, 230));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png")));
        jButton3.setToolTipText("Remove an item from selection");
        jButton3.setFocusPainted(false);
        jButton3.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton3.setMinimumSize(new java.awt.Dimension(56, 28));
        jButton3.setPreferredSize(new java.awt.Dimension(56, 28));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jButton3, gridBagConstraints3);
        
        jScrollPane2.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBorder(new javax.swing.border.TitledBorder(null, "Available items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(23, 117, 23)));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridheight = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints3);
        
        jButton2.setBackground(new java.awt.Color(230, 230, 230));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload_1.png")));
        jButton2.setToolTipText("Add an item to selection");
        jButton2.setFocusPainted(false);
        jButton2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton2.setMinimumSize(new java.awt.Dimension(56, 28));
        jButton2.setPreferredSize(new java.awt.Dimension(56, 28));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jButton2, gridBagConstraints3);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jPanel3, gridBagConstraints1);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Counters Kit variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jScrollPane3.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Activated Variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), java.awt.Color.red));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridheight = 3;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jScrollPane3, gridBagConstraints4);
        
        jButton5.setBackground(new java.awt.Color(230, 230, 230));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forward.png")));
        jButton5.setToolTipText("Add an item to selection");
        jButton5.setFocusPainted(false);
        jButton5.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton5.setMinimumSize(new java.awt.Dimension(56, 28));
        jButton5.setPreferredSize(new java.awt.Dimension(56, 28));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jButton5, gridBagConstraints4);
        
        jButton6.setBackground(new java.awt.Color(230, 230, 230));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload_1.png")));
        jButton6.setToolTipText("Add all items to selection");
        jButton6.setFocusPainted(false);
        jButton6.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton6.setMinimumSize(new java.awt.Dimension(56, 28));
        jButton6.setPreferredSize(new java.awt.Dimension(56, 28));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jButton6, gridBagConstraints4);
        
        jButton7.setBackground(new java.awt.Color(230, 230, 230));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png")));
        jButton7.setToolTipText("Remove an item from selection");
        jButton7.setFocusPainted(false);
        jButton7.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton7.setMinimumSize(new java.awt.Dimension(56, 28));
        jButton7.setPreferredSize(new java.awt.Dimension(56, 28));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jButton7, gridBagConstraints4);
        
        jScrollPane4.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane4.setBorder(new javax.swing.border.TitledBorder(null, "Available items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(23, 117, 23)));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridheight = 3;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jScrollPane4, gridBagConstraints4);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jPanel4, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Add your handling code here:
        if (evt.getSource() == jButton6) //reload
        {
            //System.out.println("RELOAD");
            vTab3=new Vector();
            vTab4=new Vector();
            
            getCounterKit();
            
            updateTable(3);
            updateTable(4);
            return;
        }
        
        javax.swing.JTable                      jTabple_sorg = null;

        if(evt.getSource() == jButton5)
        {
            jTabple_sorg=jTable_general3;
        }else 
        {
            jTabple_sorg=jTable_general4;
        }
        /*
        switch(tableSelected)
        {
            case 1:
                    jTabple_sorg=jTable_general1;
    
                break;
            case 2:
                    jTabple_sorg=jTable_general2;

                break;
            case 3:
                    jTabple_sorg=jTable_general3;


                break;
            case 4:
                    jTabple_sorg=jTable_general4;
                break;
        }*/
        
        int rowSelected[]=null;
        int len=0;
        
        if(tableSelected!=-1)
        {
            rowSelected=jTabple_sorg.getSelectedRows();
            len=rowSelected.length;
        }
        
        if(len==0)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_SCRIPT,"ERROR: No item selected.","ERROR",1);
            return;
        }
        
        if (evt.getSource() == jButton5)    
        {
            for(int i=0;i<len;i++)
            {
                
                String s1=(String)jTabple_sorg.getValueAt(rowSelected[i],0);
                //String s2=(String)jTabple_sorg.getValueAt(rowSelected[i],1);
                vTab4.addElement(new vectorTab(s1,""));
                //System.out.println("remove="+rowSelected[(len-i)-1]);
                vTab3.removeElementAt(rowSelected[(len-i)-1]); 
            }    
            updateTable(3);
            updateTable(4);
        }else if (evt.getSource() == jButton7) 
        {
            for(int i=0;i<len;i++)
            {
                
                String s1=(String)jTabple_sorg.getValueAt(rowSelected[i],0);
                //String s2=(String)jTabple_sorg.getValueAt(rowSelected[i],1);
                vTab3.addElement(new vectorTab(s1,""));
                //System.out.println("remove="+rowSelected[(len-i)-1]);
                vTab4.removeElementAt(rowSelected[(len-i)-1]); 
            }    
           
            updateTable(3);
            updateTable(4);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Add your handling code here:
        
        if (evt.getSource() == jButton2) //reload
        {
            //System.out.println("RELOAD");
            vTab1=new Vector();
            vTab2=new Vector();
            for(int i=0;i<teList.size();i++)
            {
                ADAMS_TAB_CONFIG elementoRiga = (ADAMS_TAB_CONFIG)teList.elementAt(i);
                vTab2.addElement(new vectorTab(new String(elementoRiga.TAG),new String(elementoRiga.DESCRIPTION)));
            }
            updateTable(1);
            updateTable(2);
            return;
        }
        
        javax.swing.JTable                      jTabple_sorg = null;

        if(evt.getSource() == jButton1)
        {
            jTabple_sorg=jTable_general1;
        }else 
        {
            jTabple_sorg=jTable_general2;
        }
        /*switch(tableSelected)
        {
            case 1:
                    if(evt.getSource() == jButton1)
                    {
                        jTabple_sorg=jTable_general1;
                    }else
                    {
                        
                    }
                break;
            case 2:
                    jTabple_sorg=jTable_general2;

                break;
            case 3:
                    jTabple_sorg=jTable_general3;


                break;
            case 4:
                    jTabple_sorg=jTable_general4;
                break;
        }*/
        
        int rowSelected[]=null;
        int len=0;
        
        if(tableSelected!=-1)
        {
            rowSelected=jTabple_sorg.getSelectedRows();
            len=rowSelected.length;
        }
        
        if(len==0)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_SCRIPT,"ERROR: No item selected.","ERROR",1);
            return;
        }
        
        if (evt.getSource() == jButton1)    
        {
            for(int i=0;i<len;i++)
            {
                
                String s1=(String)jTabple_sorg.getValueAt(rowSelected[i],0);
                //String s2=(String)jTabple_sorg.getValueAt(rowSelected[i],1);
                vTab2.addElement(new vectorTab(s1,""));
                //System.out.println("remove="+rowSelected[(len-i)-1]);
                vTab1.removeElementAt(rowSelected[(len-i)-1]); 
            }    
            updateTable(1);
            updateTable(2);
        }else if (evt.getSource() == jButton3) 
        {
            for(int i=0;i<len;i++)
            {
                
                String s1=(String)jTabple_sorg.getValueAt(rowSelected[i],0);
                //String s2=(String)jTabple_sorg.getValueAt(rowSelected[i],1);
                vTab1.addElement(new vectorTab(s1,""));
                //System.out.println("remove="+rowSelected[(len-i)-1]);
                vTab2.removeElementAt(rowSelected[(len-i)-1]); 
            }    
           
            updateTable(1);
            updateTable(2);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jR_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_1ActionPerformed
        // Add your handling code here:
        enableGUI(false);
        if (evt.getSource() == jR_1) 
        {
            setEnabled_t1(true);
        }else if (evt.getSource() == jR_2) 
        {
            setEnabled_t2(true);
        }else if (evt.getSource() == jR_3) 
        {
            setEnabled_t1(true);
            setEnabled_t2(true);
        }
    }//GEN-LAST:event_jR_1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jR_1;
    private javax.swing.JRadioButton jR_2;
    private javax.swing.JRadioButton jR_3;
    private javax.swing.JLabel jL_1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

}
