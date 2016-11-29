/*
 * ADAMS_JP_ReportObject_TotalizerRow.java
 *
 * Created on 8 agosto 2005, 15.01
 */

/**
 *
 * @author  root
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.sql.*;

public class ADAMS_JP_ReportObject_TotalizerRow extends javax.swing.JPanel {

    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private ReportObject.elementoBase               eb                          = null;
    private Vector                                  listaBodyCell               = new Vector();
    private String                                  config_NAME_ALIAS           = "";
    private String                                  config_NAME_SCHEMA          = "";
    private ReportObject                            robj                        = null;
    private ADAMS_JP_ReportObjectEditor              parent                      = null;
    private ADAMS_JF_WIZARDBASE                      wizard                      = null;
    private ReportObject.elementoBase               ebINIZIALE                  = null;
    private class eBodyCell
    {
        public int     idBodyCell       = -1;
        public String  nomeBodyCell     = "";
        public int     pos              = -1;
        
        public eBodyCell(int p,int id,String str)
        {
            pos=p;
            idBodyCell=id;
            nomeBodyCell=str;
        }
    }
    
    public int getPosBodyCell(int idBC)
    {
        int len=listaBodyCell.size();
        
        for(int i=0;i<len;i++)
        {
            eBodyCell appo=(eBodyCell)listaBodyCell.elementAt(i);
            if(appo.idBodyCell==idBC)
            {  
                return i;
            }
        }
        
        return -1;
    }
    
    public int getIDBodyCell(int p)
    {
        int len=listaBodyCell.size();
        
        for(int i=0;i<len;i++)
        {
            eBodyCell appo=(eBodyCell)listaBodyCell.elementAt(i);
            if(appo.pos==p)
            {  
                return appo.idBodyCell;
            }
        }
        
        return -1;
    }
    
    public void getBodyCell()
    {
        int pos=1;
        /************** test*****************/
        /************************************/
        Vector v=robj.vGetElementoBase();
        for(int i=0;i<v.size();i++)
        {
            ReportObject.elementoBase el=(ReportObject.elementoBase)v.elementAt(i);
            if(el.TYPE==ReportObject.BODY)
            {
                int idBodyCell=el.IDOBJECT;
                String nomeBodyCell=el.OBJECT_TAG;
                
                eBodyCell eBC=new eBodyCell(pos,idBodyCell,nomeBodyCell);
                listaBodyCell.addElement(eBC);
                jC_breaker.addItem(nomeBodyCell);

                pos++;

                if(DEBUG)
                {
                    System.out.println("IDOBJECT ->"+idBodyCell);
                    System.out.println("OBJECT_TAG ->"+nomeBodyCell);
                    System.out.println();
                }
            }
        }
        
        /*String strSelect="select IDOBJECT,OBJECT_TAG from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND TAG='"+config_NAME_SCHEMA+"' AND TYPE="+ReportObject.BODY;
        
        if(DEBUG)
            System.out.println("ADAMS_JP_ReportObject_FooterRow [getBodyCell] ----> "+strSelect);

        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_rs(strSelect);

        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    int idBodyCell=rs.getInt("IDOBJECT");
                    String nomeBodyCell=rs.getString("OBJECT_TAG");
                    
                    eBodyCell eBC=new eBodyCell(pos,idBodyCell,nomeBodyCell);
                    listaBodyCell.addElement(eBC);
                    jC_breaker.addItem(nomeBodyCell);
                    
                    pos++;
                    
                    if(DEBUG)
                    {
                        System.out.println("IDOBJECT ->"+idBodyCell);
                        System.out.println("OBJECT_TAG ->"+nomeBodyCell);
                        System.out.println();
                    }
                    
                    
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_JP_ReportObject_FooterRow [getBodyCell] ----> rs==null");
        }*/
    }
    
    /** Creates new form ADAMS_JP_ReportObject_TotalizerRow */
    public ADAMS_JP_ReportObject_TotalizerRow(String config_NAME_ALIAS,String config_NAME_SCHEMA,ReportObject.elementoBase eb,ReportObject robj,ADAMS_JP_ReportObjectEditor parent) {
        this.eb=eb;
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        this.config_NAME_SCHEMA=config_NAME_SCHEMA;
        this.robj=robj;
        this.parent=parent;
        
        initComponents();
        setFont();
        setCursorWidget();  
        
        jT_label.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,41));
        
        eBodyCell eBC;
        eBC=new eBodyCell(0,-1,"<none selected>");
        listaBodyCell.addElement(eBC);
        jC_breaker.addItem("<none selected>");
        
        getBodyCell();
        jC_breaker.setSelectedIndex(0);
        
        /*if(ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA!=0)
        {
            getCounterKit();
        }*/
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            resetInterface();
        else
            setInterface();
        
        ebINIZIALE=eb;
    }
    
    private void getCounterKit()
    {
        /*
         * per leggere i contatori:
         * select IDCOUNTERKIT from tab_analysis_type where IDANALISI=10;
         * select TAG,DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='voice' and IDCOUNTER=10;
         *
         */
        
        String strSelect="select IDCOUNTERKIT from tab_analysis_type where IDANALISI="+ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA;
        
        if(DEBUG)
            System.out.println("ADAMS_JP_ReportObject_FooterRow [getCounterKit] ----> "+strSelect);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        int idCounterKit=-1;
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    idCounterKit=rs.getInt("IDCOUNTERKIT");

                    if(DEBUG)
                    {
                        System.out.println("idCounterKit ->"+idCounterKit);
                        System.out.println();
                    } 
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_JP_ReportObject_FooterRow [getCounterKit] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
        
        if(DEBUG)
            System.out.println("idCounterKit="+idCounterKit);
        
        
        if(idCounterKit!=-1)
        {
            strSelect="select TAG,DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDCOUNTER="+idCounterKit+" order by TAG";
            if(DEBUG)
                System.out.println("ADAMS_JP_ReportObject_FooterRow [getCounterKit2] ----> "+strSelect);

            SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs1  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);

            if(rs1 != null)
            {
                try
                {
                    while ( rs1.next ( ) ) 
                    {
                        String tag_counter=rs1.getString("TAG");
                        jC_breaker.addItem(tag_counter);
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
                System.out.println("ADAMS_JP_ReportObject_FooterRow [getCounterKit] ----> rs1==null");
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
    
    public void commit()
    {
        // DA METTERE I CONTROLLI SUI CAMPI
        getInterface();
        
    }
    
    public void getInterface()
    {
        
        //System.out.println("jC_breaker.getSelectedIndex()="+jC_breaker.getSelectedIndex());
        //System.out.println("getIDBodyCell(jC_breaker.getSelectedIndex()="+getIDBodyCell(jC_breaker.getSelectedIndex()));
        
        eb.TOTALIZER_TRIGGER=getIDBodyCell(jC_breaker.getSelectedIndex());
        eb.TOTALIZER_LABEL=jT_label.getText();
        
        if(jR_r1.isSelected())
        {
            eb.TOTALIZER_REDRAWHEADER=1;
        }else
        {
            eb.TOTALIZER_REDRAWHEADER=0;
        }
        
        if(jR_r2.isSelected())
        {
            eb.TOTALIZER_BORDERAROUND=1;
        }else
        {
            eb.TOTALIZER_BORDERAROUND=0;
        }
        
        if(jR_r3.isSelected())
        {
            eb.TOTALIZER_USESCRIPT=1;
        }else
        {
            eb.TOTALIZER_USESCRIPT=0;
        }
        if(wizard!=null)
            wizard.dispose();
        
    }
    
    public boolean testModify()
    {
        boolean testMODIFY=false;
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(wizard!=null)
        {
            if(wizard.flagCOMMIT==true)
            {
                testMODIFY=true;
                return testMODIFY;
            }

            if (wizard.flagDELETE==true)
            {
                testMODIFY=true;
                return testMODIFY;
            }
        }
        
        if(DEBUG)
        {
            System.out.println("Test MODIFY FOOTER");
        }
        
        ReportObject.elementoBase ebAPPO=getInterfaceAPPO();

        
        if(!ebINIZIALE.TOTALIZER_LABEL.equals(ebAPPO.TOTALIZER_LABEL))
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.TOTALIZER_TRIGGER!=ebAPPO.TOTALIZER_TRIGGER)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.TOTALIZER_REDRAWHEADER!=ebAPPO.TOTALIZER_REDRAWHEADER)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.TOTALIZER_BORDERAROUND!=ebAPPO.TOTALIZER_BORDERAROUND)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.TOTALIZER_USESCRIPT!=ebAPPO.TOTALIZER_USESCRIPT)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        return testMODIFY;
        
    }
    
    private ReportObject.elementoBase getInterfaceAPPO()
    {
        
        ReportObject.elementoBase ebAPPO=new ReportObject.elementoBase();
        
        ebAPPO.TOTALIZER_TRIGGER=getIDBodyCell(jC_breaker.getSelectedIndex());
        ebAPPO.TOTALIZER_LABEL=jT_label.getText();
        
        if(jR_r1.isSelected())
        {
            ebAPPO.TOTALIZER_REDRAWHEADER=1;
        }else
        {
            ebAPPO.TOTALIZER_REDRAWHEADER=0;
        }
        
        if(jR_r2.isSelected())
        {
            ebAPPO.TOTALIZER_BORDERAROUND=1;
        }else
        {
            ebAPPO.TOTALIZER_BORDERAROUND=0;
        }
        
        if(jR_r3.isSelected())
        {
            ebAPPO.TOTALIZER_USESCRIPT=1;
        }else
        {
            ebAPPO.TOTALIZER_USESCRIPT=0;
        }
        if(wizard!=null)
            wizard.dispose();
        
        return ebAPPO;
        
    }
    
    public void resetInterface()
    {
        jC_breaker.setSelectedIndex(0);
        jR_r3.setSelected(false);
    }
    
    public void setInterface()
    {
        System.out.println("SET eb.TOTALIZER_TRIGGER="+eb.TOTALIZER_TRIGGER);
        System.out.println("SET getPosBodyCell(eb.TOTALIZER_TRIGGER)="+getPosBodyCell(eb.TOTALIZER_TRIGGER));
        
        jC_breaker.setSelectedIndex(getPosBodyCell(eb.TOTALIZER_TRIGGER));
        jT_label.setText(""+eb.TOTALIZER_LABEL);
        if(eb.TOTALIZER_REDRAWHEADER==1)
            jR_r1.setSelected(true);
        else
            jR_r1.setSelected(false);
        
        if(eb.TOTALIZER_BORDERAROUND==1)
            jR_r2.setSelected(true);
        else
            jR_r2.setSelected(false);
        
        if(eb.TOTALIZER_USESCRIPT==1)
        {
            jR_r3.setSelected(true);
        }
        else
        {
            jR_r3.setSelected(false);
        }
    }
    
    public void setFont()
    {
        jLabel2.setFont(ADAMS_GlobalParam.font_B11);
        jLabel3.setFont(ADAMS_GlobalParam.font_B11);
        jC_breaker.setFont(ADAMS_GlobalParam.font_B11);
        jT_label.setFont(ADAMS_GlobalParam.font_B11);
        jR_r1.setFont(ADAMS_GlobalParam.font_B11);
        jR_r2.setFont(ADAMS_GlobalParam.font_B11);
        jR_r3.setFont(ADAMS_GlobalParam.font_B11);
        jB_b1.setFont(ADAMS_GlobalParam.font_B11); 
    }
    
    public void setCursorWidget()
    {    
        jC_breaker.setCursor(Cur_hand);
        jT_label.setCursor(Cur_hand);
        jR_r1.setCursor(Cur_hand);
        jR_r2.setCursor(Cur_hand);
        jR_r3.setCursor(Cur_hand);
        jB_b1.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_icon = new javax.swing.JLabel();
        jP_p1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jC_breaker = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jT_label = new javax.swing.JTextField();
        jR_r1 = new javax.swing.JRadioButton();
        jR_r2 = new javax.swing.JRadioButton();
        jR_r3 = new javax.swing.JRadioButton();
        jB_b1 = new javax.swing.JButton();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tot_cell.png")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 1.0;
        add(jL_icon, gridBagConstraints1);
        
        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Totalizer Setup"));
        jLabel2.setText("Breaker Counter/Kit");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel2, gridBagConstraints2);
        
        jC_breaker.setBackground(java.awt.Color.white);
        jC_breaker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jC_breakerActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jC_breaker, gridBagConstraints2);
        
        jLabel3.setText("Totalizer Label");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel3, gridBagConstraints2);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jT_label, gridBagConstraints2);
        
        jR_r1.setText("Draw Body Header On Next Line");
        jR_r1.setToolTipText("Direct");
        jR_r1.setContentAreaFilled(false);
        jR_r1.setFocusPainted(false);
        jR_r1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r1, gridBagConstraints2);
        
        jR_r2.setText("Draw Border Around Fields");
        jR_r2.setToolTipText("Direct");
        jR_r2.setContentAreaFilled(false);
        jR_r2.setFocusPainted(false);
        jR_r2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r2, gridBagConstraints2);
        
        jR_r3.setText("Use Scripting");
        jR_r3.setToolTipText("Direct");
        jR_r3.setContentAreaFilled(false);
        jR_r3.setFocusPainted(false);
        jR_r3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_r3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jR_r3StateChanged(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r3, gridBagConstraints2);
        
        jB_b1.setBackground(new java.awt.Color(230, 230, 230));
        jB_b1.setText("Edit Script");
        jB_b1.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_b1.setFocusPainted(false);
        jB_b1.setEnabled(false);
        jB_b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_b1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jB_b1, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_p1, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jB_b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_b1ActionPerformed
        // Add your handling code here:
        
        String strSelect="select IDCOUNTERKIT from tab_analysis_type where IDANALISI="+ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA;
        
        if(DEBUG)
            System.out.println("ADAMS_JP_ReportObject_BodyCell [getIDAnalisi] ----> "+strSelect);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        int idCounterKit=-2;
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    idCounterKit=rs.getInt("IDCOUNTERKIT");

                    if(DEBUG)
                    {
                        System.out.println("idCounterKit ->"+idCounterKit);
                        System.out.println();
                    } 
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_JP_ReportObject_BodyCell [getCounterKit] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
            
        
        if(DEBUG)
            System.out.println("idCounterKit="+idCounterKit);
        
        ADAMS_TAB_CONFIG appo=new ADAMS_TAB_CONFIG();
        appo.TAG=parent.getTextDescription();
        appo.DESCRIPTION="Report Schema";
        
        String strSelectSeq="select IDSCRIPT from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND IDSCRIPT="+eb.TOTALIZER_IDSCRIPT;
        int id = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectSeq);

        if((eb.TOTALIZER_IDSCRIPT>0)&&(id>0))
        {
            appo.add_LISTA_SCRIPTS(eb.TOTALIZER_IDSCRIPT,4);
        }
        
        wizard = new ADAMS_JF_WIZARDBASE(ADAMS_GlobalParam.ADAMS_JD_Report,1,ADAMS_GlobalParam.SCRIPT_REPORT,config_NAME_ALIAS,idCounterKit,"NTM Basic Wizard for "+appo.TAG, true, appo, null);
        //wizard.show();
        wizard.setVisible(true);
        
        //System.out.println("flagCOMMIT="+wizard.flagCOMMIT);
        //System.out.println("flagDELETE="+wizard.flagDELETE);
        
        if(wizard.flagCOMMIT==false)
        {
            eb.TOTALIZER_IDSCRIPT=0;
        }else if(wizard.flagCOMMIT==true)
        {
            eb.TOTALIZER_IDSCRIPT=wizard.getID_SCRIPT();
        }
        
        if (wizard.flagDELETE==true)
        {
            eb.TOTALIZER_IDSCRIPT=0;
        }
        
        
    }//GEN-LAST:event_jB_b1ActionPerformed

    private void jC_breakerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_breakerActionPerformed
        // Add your handling code here:
        parent.setTextDescription(((String)jC_breaker.getSelectedItem())+" SubTotal");
    }//GEN-LAST:event_jC_breakerActionPerformed

    private void jR_r3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jR_r3StateChanged
        // Add your handling code here:
        if(jR_r3.isSelected()==false)
        {
            jB_b1.setEnabled(false);
        }else
        {
            jB_b1.setEnabled(true);
        }
    }//GEN-LAST:event_jR_r3StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_icon;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox jC_breaker;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jT_label;
    private javax.swing.JRadioButton jR_r1;
    private javax.swing.JRadioButton jR_r2;
    private javax.swing.JRadioButton jR_r3;
    private javax.swing.JButton jB_b1;
    // End of variables declaration//GEN-END:variables

}
