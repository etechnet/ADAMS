/*
 * ADAMS_JP_ReportObject_FooterRow.java
 *
 * Created on 8 agosto 2005, 14.48
 */

/**
 *
 * @author  Raffaele Ficcadenti
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.sql.*;

public class ADAMS_JP_ReportObject_FooterRow extends javax.swing.JPanel {
    
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
        int pos=3;
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
                jC_data_source.addItem(nomeBodyCell);

                pos++;

                if(true)
                {
                    System.out.println("IDOBJECT ->"+idBodyCell);
                    System.out.println("OBJECT_TAG ->"+nomeBodyCell);
                    System.out.println();
                }
            }
        }
        
        /*int pos=1;

        
        String strSelect="select IDOBJECT,OBJECT_TAG from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND TAG='"+config_NAME_SCHEMA+"' AND TYPE="+ReportObject.BODY;
        
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
                    jC_data_source.addItem(nomeBodyCell);
                    
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
    
    /** Creates new form ADAMS_JP_ReportObject_FooterRow */
    public ADAMS_JP_ReportObject_FooterRow(String config_NAME_ALIAS,String config_NAME_SCHEMA,ReportObject.elementoBase eb,ReportObject robj,ADAMS_JP_ReportObjectEditor parent) {
        this.eb=eb;
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        this.config_NAME_SCHEMA=config_NAME_SCHEMA;
        this.robj=robj;
        this.parent=parent;
        
        initComponents();
        setFont();
        setCursorWidget(); 
        
        jT_label.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,41));
        jT_uvs.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,81));
        
        eBodyCell eBC;
        eBC=new eBodyCell(0,-1,"<none selected>");
        listaBodyCell.addElement(eBC);
        jC_data_source.addItem("<none selected>");
        
        eBC=new eBodyCell(1,-2,"[key #]");
        listaBodyCell.addElement(eBC);
        jC_data_source.addItem("[key #]");
        
        eBC=new eBodyCell(2,-3,"[User Defined]");
        listaBodyCell.addElement(eBC);
        jC_data_source.addItem("[User Defined]");
        
        getBodyCell();
        jC_data_source.setSelectedIndex(0);
        
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
         * select b.TAG,b.DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='voice' and IDCOUNTER=10;
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
            strSelect="select TAG,DESCRIPTION from tab_type_counters b where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDCOUNTER="+idCounterKit+" order by TAG";
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
                        jC_data_source.addItem(tag_counter);
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
        if(true)
        {
            System.out.println("FOOTER_SOURCE="+getIDBodyCell(jC_data_source.getSelectedIndex()));
        }
        eb.FOOTER_SOURCE=getIDBodyCell(jC_data_source.getSelectedIndex());
        eb.FOOTER_LABEL=jT_label.getText();
        eb.FOOTER_USERVALUE=jT_uvs.getText();
        if(jR_r1.isSelected())
        {
            eb.FOOTER_ISURL=1;
        }else
        {
            eb.FOOTER_ISURL=0;
        }
    }
    
    private ReportObject.elementoBase getInterfaceAPPO()
    {
        
        ReportObject.elementoBase ebAPPO=new ReportObject.elementoBase();
        
        ebAPPO.FOOTER_SOURCE=getIDBodyCell(jC_data_source.getSelectedIndex());
        ebAPPO.FOOTER_LABEL=jT_label.getText();
        ebAPPO.FOOTER_USERVALUE=jT_uvs.getText();
        if(jR_r1.isSelected())
        {
            ebAPPO.FOOTER_ISURL=1;
        }else
        {
            ebAPPO.FOOTER_ISURL=0;
        }
        
        return ebAPPO;
    }
    
    public boolean testModify()
    {
        boolean testMODIFY=false;
        if(DEBUG)
        {
            System.out.println("Test MODIFY FOOTER");
        }
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        ReportObject.elementoBase ebAPPO=getInterfaceAPPO();

        if(!ebINIZIALE.FOOTER_LABEL.equals(ebAPPO.FOOTER_LABEL))
        {
            //System.out.println("1");
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.FOOTER_SOURCE!=ebAPPO.FOOTER_SOURCE)
        {
            //System.out.println("2");
            //System.out.println("ebINIZIALE.FOOTER_SOURCE="+ebINIZIALE.FOOTER_SOURCE);
            //System.out.println("ebAPPO.FOOTER_SOURCE="+ebAPPO.FOOTER_SOURCE);
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.FOOTER_ISURL!=ebAPPO.FOOTER_ISURL)
        {
            //System.out.println("3");
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(!ebINIZIALE.FOOTER_USERVALUE.equals(ebAPPO.FOOTER_USERVALUE))
        {
            //System.out.println("4");
            testMODIFY=true;
            return testMODIFY;
        }
        
        return testMODIFY;
        
    }
    
    public void resetInterface()
    {
        jC_data_source.setSelectedIndex(0);
    }
    
    public void setInterface()
    {
        if(DEBUG)
        {
            System.out.println("eb.FOOTER_SOURCE="+eb.FOOTER_SOURCE);
        }
        jC_data_source.setSelectedIndex(getPosBodyCell(eb.FOOTER_SOURCE));
        jT_label.setText(""+eb.FOOTER_LABEL);
        jT_uvs.setText(""+eb.FOOTER_USERVALUE);
        if(eb.FOOTER_ISURL==1)
            jR_r1.setSelected(true);
        else
            jR_r1.setSelected(false);
    }
    
    public void setFont()
    {
        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jLabel2.setFont(ADAMS_GlobalParam.font_B11);
        jLabel4.setFont(ADAMS_GlobalParam.font_B11);
        
        jC_data_source.setFont(ADAMS_GlobalParam.font_B11);
        jT_label.setFont(ADAMS_GlobalParam.font_B11);
        jT_uvs.setFont(ADAMS_GlobalParam.font_B11);
        jR_r1.setFont(ADAMS_GlobalParam.font_B11);
        
    }
    
    public void setCursorWidget()
    {    
        jC_data_source.setCursor(Cur_hand);
        jT_label.setCursor(Cur_hand);
        jT_uvs.setCursor(Cur_hand);
        jR_r1.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jL_icon = new javax.swing.JLabel();
        jP_p1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jC_data_source = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jT_label = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jT_uvs = new javax.swing.JTextField();
        jR_r1 = new javax.swing.JCheckBox();
        
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/foot_cell.png")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 1.0;
        add(jL_icon, gridBagConstraints1);
        
        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Footer Row"));
        jLabel1.setText("Data Source");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel1, gridBagConstraints2);
        
        jC_data_source.setBackground(java.awt.Color.white);
        jC_data_source.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jC_data_sourceActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jC_data_source, gridBagConstraints2);
        
        jLabel2.setText("Label");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel2, gridBagConstraints2);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jT_label, gridBagConstraints2);
        
        jLabel4.setText("User value string");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel4, gridBagConstraints2);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 2.0;
        gridBagConstraints3.weighty = 1.0;
        jPanel1.add(jT_uvs, gridBagConstraints3);
        
        jR_r1.setBackground(new java.awt.Color(230, 230, 230));
        jR_r1.setText("URL");
        jR_r1.setToolTipText("(True/False)");
        jR_r1.setFocusPainted(false);
        jR_r1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_r1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jPanel1.add(jR_r1, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jPanel1, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_p1, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jC_data_sourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_data_sourceActionPerformed
        // Add your handling code here:
        if(jC_data_source.getSelectedIndex()==2)
        {
            jLabel4.setEnabled(true);
            jT_uvs.setEnabled(true);
            jR_r1.setEnabled(true);
        }else
        {
            jLabel4.setEnabled(false);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
        }
        parent.setTextDescription(((String)jC_data_source.getSelectedItem())+" GrandTotal");
        
    }//GEN-LAST:event_jC_data_sourceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jL_icon;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jC_data_source;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jT_label;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jT_uvs;
    private javax.swing.JCheckBox jR_r1;
    // End of variables declaration//GEN-END:variables

}
