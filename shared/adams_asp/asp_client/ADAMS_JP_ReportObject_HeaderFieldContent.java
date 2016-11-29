/*
 * ADAMS_JP_ReportObject_HeaderFieldContent.java
 *
 * Created on 8 agosto 2005, 13.57
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

public class ADAMS_JP_ReportObject_HeaderFieldContent extends javax.swing.JPanel {

    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private ReportObject.elementoBase               eb                          = null;
    private ADAMS_JP_ReportObjectEditor              parent                      = null;
    private ReportObject.elementoBase               ebINIZIALE                  = null;
    
    /** Creates new form ADAMS_JP_ReportObject_HeaderFieldContent */
    public ADAMS_JP_ReportObject_HeaderFieldContent(ReportObject.elementoBase eb,ADAMS_JP_ReportObjectEditor parent) {
        //System.out.println("EB="+eb);
        this.eb=eb;
        this.parent=parent;
        initComponents();
        setFont();
        setCursorWidget();
        jC_c.addItem("<select one>");
        jC_c.addItem("Logo");
        jC_c.addItem("Title");
        jC_c.addItem("Elaboration Data");
        jC_c.addItem("Nodes");
        jC_c.addItem("Report Name");
        jC_c.addItem("Processed Key #");
        jC_c.addItem("Analisys Name");
        jC_c.addItem("Data Format");
        jC_c.addItem("Sort Type");
        jC_c.addItem("Data Type");
        jC_c.addItem("Network Analisys");
        jC_c.addItem("Relation Name");
        jC_c.addItem("Restriction List");
        jC_c.addItem("User Defined");
        jC_c.setSelectedIndex(0);
        
        jT_ul.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS+" ",30));
        jT_uvs.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS+" ",81));
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            resetInterface();
        else
            setInterface();
        
        ebINIZIALE=eb;
    }
    
    public void commit()
    {
        // DA METTERE I CONTROLLI SUI CAMPI
        getInterface();
    }
    
    public void getInterface()
    {
        eb.HEADER_USERLABEL=jT_ul.getText();
        eb.HEADER_VALUE=jC_c.getSelectedIndex();
        
        if(jR_r1.isSelected())
        {
            eb.HEADER_ISURL=1;
            eb.HEADER_USERVALUE=jT_uvs.getText();
        }else
        {
            eb.HEADER_ISURL=0;
            eb.HEADER_USERVALUE="";
        }
    }
    
    private ReportObject.elementoBase getInterfaceAPPO()
    {
        
        ReportObject.elementoBase ebAPPO=new ReportObject.elementoBase();
        ebAPPO.HEADER_USERLABEL=jT_ul.getText();
        ebAPPO.HEADER_VALUE=jC_c.getSelectedIndex();
        
        if(jR_r1.isSelected())
        {
            ebAPPO.HEADER_ISURL=1;
            ebAPPO.HEADER_USERVALUE=jT_uvs.getText();
        }else
        {
            ebAPPO.HEADER_ISURL=0;
            ebAPPO.HEADER_USERVALUE="";
        }
        
        return ebAPPO;
    }
    
    public boolean testModify()
    {
        boolean testMODIFY=false;
        if(DEBUG)
        {
            System.out.println("Test MODIFY HEADER");
        }
        
        ReportObject.elementoBase ebAPPO=getInterfaceAPPO();

        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(!ebINIZIALE.HEADER_USERLABEL.equals(ebAPPO.HEADER_USERLABEL))
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.HEADER_VALUE!=ebAPPO.HEADER_VALUE)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.HEADER_ISURL!=ebAPPO.HEADER_ISURL)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(!ebINIZIALE.HEADER_USERVALUE.equals(ebAPPO.HEADER_USERVALUE))
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        return testMODIFY;
        
    }
    
    public void resetInterface()
    {}
    
    public void setInterface()
    {
        jT_ul.setText(""+eb.HEADER_USERLABEL);
        jC_c.setSelectedIndex(eb.HEADER_VALUE);
        
        if(eb.HEADER_ISURL==1)
            jR_r1.setSelected(true);
        else
            jR_r1.setSelected(false);
        
        jT_uvs.setText(""+eb.HEADER_USERVALUE);
        
    }
    
    public void setFont()
    {
        jLabel4.setFont(ADAMS_GlobalParam.font_B11);
        jLabel5.setFont(ADAMS_GlobalParam.font_B11);
        jLabel6.setFont(ADAMS_GlobalParam.font_B11);
        
        jT_ul.setFont(ADAMS_GlobalParam.font_B11);
        jC_c.setFont(ADAMS_GlobalParam.font_B11);
        jT_uvs.setFont(ADAMS_GlobalParam.font_B11);
        jR_r1.setFont(ADAMS_GlobalParam.font_B11);

    }
    
    public void setCursorWidget()
    {    
        jT_ul.setCursor(Cur_hand);
        jC_c.setCursor(Cur_hand);
        jT_uvs.setCursor(Cur_hand);
        jR_r1.setCursor(Cur_hand);
        jB_edit.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_icon = new javax.swing.JLabel();
        jP_p1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jT_ul = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jC_c = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jT_uvs = new javax.swing.JTextField();
        jR_r1 = new javax.swing.JRadioButton();
        jB_edit = new javax.swing.JButton();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header_cell.png")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 1.0;
        add(jL_icon, gridBagConstraints1);
        
        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Header Field Content"));
        jLabel4.setText("User label");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.ipadx = 1;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel4, gridBagConstraints2);
        
        jT_ul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_ulKeyReleased(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jT_ul, gridBagConstraints2);
        
        jLabel5.setText("Contents");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel5, gridBagConstraints2);
        
        jC_c.setBackground(java.awt.Color.white);
        jC_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jC_cActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jC_c, gridBagConstraints2);
        
        jLabel6.setText("User value string");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jLabel6, gridBagConstraints2);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jT_uvs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_uvsActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 6.0;
        jPanel1.add(jT_uvs, gridBagConstraints3);
        
        jR_r1.setText("URL");
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
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jPanel1.add(jR_r1, gridBagConstraints3);
        
        jB_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_edit.setToolTipText("Edit Plugin (Active in modification)");
        jB_edit.setBorderPainted(false);
        jB_edit.setContentAreaFilled(false);
        jB_edit.setFocusPainted(false);
        jB_edit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_edit.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_edit.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_edit.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_edit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_edit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_edit.setEnabled(false);
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_editActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jB_edit, gridBagConstraints3);
        
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
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_p1, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jB_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_editActionPerformed
        // Add your handling code here:
        ADAMS_JD_ViewSetImage SetImage;
        String NTM_PATH="../../ntm4/";
        
        String str=jT_uvs.getText();
        int pos=str.lastIndexOf('/');
        if(pos>=0)
            str=str.substring(pos+1);
        
        SetImage = new ADAMS_JD_ViewSetImage(null, true, 400,300,str);
        SetImage.setGuiEnable(true);
       
        //SetImage.show();
        SetImage.setVisible(true);
        
        
        str = SetImage.getNameImage();
        if(str.length()>0)
        {
            jT_uvs.setText(NTM_PATH+str);
        }
        
        if(DEBUG)
        {
            System.out.println("STRINGA="+str);
        }
        
        SetImage.dispose();
    }//GEN-LAST:event_jB_editActionPerformed

    private void jT_uvsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_uvsActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jT_uvsActionPerformed

    private void jT_ulKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_ulKeyReleased
        // Add your handling code here:
        parent.setTextDescription(jT_ul.getText());
    }//GEN-LAST:event_jT_ulKeyReleased

    private void jC_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_cActionPerformed
        // Add your handling code here:
        if(jC_c.getSelectedIndex()==0) //none selected
        {
            jLabel6.setEnabled(false);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setEnabled(false);
        }else if(jC_c.getSelectedIndex()==1) //Logo
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(true);
            //jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(true);
            jB_edit.setEnabled(true);
        }else if(jC_c.getSelectedIndex()==2) //title
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(true);
            //jT_uvs.setEnabled(false);
            jR_r1.setEnabled(true);
            jR_r1.setSelected(true);
            jB_edit.setEnabled(true);
        }
        else if(jC_c.getSelectedIndex()==3) //eleboration date
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setEnabled(false);
        }
        else if(jC_c.getSelectedIndex()==4) //nodes
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setSelected(false);
        }
        else if(jC_c.getSelectedIndex()==5) //report name
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setEnabled(false);
        }
        else if(jC_c.getSelectedIndex()==6) //processed key
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setEnabled(false);
        }
        else if(jC_c.getSelectedIndex()==7) //analisys name
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setSelected(false);
        }
        else if(jC_c.getSelectedIndex()==8) //data format
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
        }else if(jC_c.getSelectedIndex()==9) //sort type
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
        }else if(jC_c.getSelectedIndex()==10) //data type
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setEnabled(false);
        }else if(jC_c.getSelectedIndex()==11) //nt analisys
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setEnabled(false);
        }else if(jC_c.getSelectedIndex()==12) //relation name
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
        }else if(jC_c.getSelectedIndex()==13) //restriction list
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r1.setEnabled(false);
            jR_r1.setSelected(false);
            jB_edit.setEnabled(false);
        }else if(jC_c.getSelectedIndex()==14) //User Defined
        {
            jLabel6.setEnabled(true);
            jT_uvs.setEnabled(true);
            jR_r1.setEnabled(true);
            jR_r1.setSelected(true);
            jB_edit.setEnabled(false);
        }
        
        
        
        
            
    }//GEN-LAST:event_jC_cActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_icon;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jT_ul;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox jC_c;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jT_uvs;
    private javax.swing.JRadioButton jR_r1;
    private javax.swing.JButton jB_edit;
    // End of variables declaration//GEN-END:variables

}
