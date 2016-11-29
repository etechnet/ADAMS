/*
 * ADAMS_JP_Script_p2.java
 *
 * Created on 24 ottobre 2005, 11.33
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */

import java.awt.Cursor;

public class ADAMS_JP_Script_p2 extends javax.swing.JPanel {

    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private ADAMS_JF_WIZARDBASE                      parent                      = null;
    private String                                  vTag[]                      = {"SCRIPT_TYPE1","SCRIPT_TYPE2","SCRIPT_TYPE3","SCRIPT_TYPE4","SCRIPT_TYPE5","SCRIPT_TYPE6"};
    private int                                     flagSCRIPT                  = -1;
    /** Creates new form ADAMS_JP_Script_p2 */
    public ADAMS_JP_Script_p2(int flag,ADAMS_JF_WIZARDBASE parent) {
        this.parent=parent;
        this.flagSCRIPT=flag;
        initComponents();
        jR_1.setText("<html><center><b>Evaluate the item to give it a value (or modify it's value) before analysis start. This means you can use it to modify user restrictions values. (use carefully!!)</b><br>");
        jR_2.setText("<html><center><b>Evaluate the item to give it a value (or modify it's value) just after data has been read from DRs. Here you can valorize element derived from DR fields.</b><br>");
        jR_3.setText("<html><center><b>This option enable your script to manipulate item value in key building. In other words the it's result will be inserted in the item key part. (Please note that not all Analysis types makes use of binary trees)</b><br>");
        jR_4.setText("<html><center><b>This option makes the result of your script to be utilized ad a data filter during DR analysis. Note that script's result must be a boolean</b><br>");
        jR_5.setText("<html><center><b>Report Creation. The result of your script will be directly inserted in field.</b><br>");
        jR_6.setText("<html><center><b>Alarm Creation. The result of your script will be directly inserted in field.</b><br>");
        jl_1.setText("<html><center><b><font color=#ffff00>If you do not want to add or edit a script, but just to </font></b>" + "<font color=#ffffdd>DELETE</font>"+"<b><font color=#ffff00> it, than press the button on the right. </font></b><br>");
        
        setCursorWidget();
        
        if(flag==0)
        {
            //jR_1.setSelected(true);
        }else if(flag==1)
        {
            //jR_5.setSelected(true);  --> da vedere come gli piace
        }
        
        
        //parent.nextButtonSetEnabled(false);
    }
    
    public void enableButtonDelete(boolean flag)
    {
        jB_1.setEnabled(flag);
    }
    
    public String getTAG()
    {
        if(jR_1.isSelected())
        {
            return vTag[0];
        }else if(jR_2.isSelected())
        {
            return vTag[1];
        }else if(jR_3.isSelected())
        {
            return vTag[2];
        }else if(jR_4.isSelected())
        {
            return vTag[3];
        }else if(jR_5.isSelected())
        {
            return vTag[4];
        }
        else if(jR_6.isSelected())
        {
            return vTag[5];
        }
        
        return "";
    }
    
    public int getID_TAG()
    {
        if(jR_1.isSelected())
        {
            return 0;
        }else if(jR_2.isSelected())
        {
            return 1;
        }else if(jR_3.isSelected())
        {
            return 2;
        }else if(jR_4.isSelected())
        {
            return 3;
        }else if(jR_5.isSelected())
        {
            return 4;
        }
        else if(jR_6.isSelected())
        {
            return 5;
        }
        
        return -1;
    }
    
    public int getValue()
    {
        if(jR_1.isSelected()==true)
        {
            return 1;
        }else if(jR_2.isSelected()==true)
        {
            return 2;
        }else if(jR_3.isSelected()==true)
        {
            return 3;
        }else if(jR_4.isSelected()==true)
        {
            return 4;
        }else if(jR_5.isSelected()==true)
        {
            return 5;
        }
        return -1;
    }
    
    public void refreshPanel()
    {
        
        if(jR_1.isSelected())
        {
            parent.nextButtonSetEnabled(true);
        }else if(jR_2.isSelected())
        {
            parent.nextButtonSetEnabled(true);
        }else if(jR_3.isSelected())
        {
            parent.nextButtonSetEnabled(true);
        }else if(jR_4.isSelected())
        {
            parent.nextButtonSetEnabled(true);
        }else if(jR_5.isSelected())
        {
            parent.nextButtonSetEnabled(true);
        }else
            parent.nextButtonSetEnabled(false);
    }
    
    public void setCursorWidget()
    {
        jR_1.setCursor(Cur_hand);
        jR_2.setCursor(Cur_hand);
        jR_3.setCursor(Cur_hand);
        jR_4.setCursor(Cur_hand);
        jR_5.setCursor(Cur_hand);
        jR_6.setCursor(Cur_hand);
        jB_1.setCursor(Cur_hand);
        
    }
    
    public void setFont()
    {
        jR_1.setFont(ADAMS_GlobalParam.font_B11);
        jR_2.setFont(ADAMS_GlobalParam.font_B11);
        jR_3.setFont(ADAMS_GlobalParam.font_B11);
        jR_4.setFont(ADAMS_GlobalParam.font_B11);
        jR_5.setFont(ADAMS_GlobalParam.font_B11);
        jR_6.setFont(ADAMS_GlobalParam.font_B11);
        jB_1.setCursor(Cur_hand);
        jLabel2.setFont(ADAMS_GlobalParam.font_B11);
        jLabel3.setFont(ADAMS_GlobalParam.font_B11);
        jLabel4.setFont(ADAMS_GlobalParam.font_B11);       
    }
    
    public void setEnabledAlarm(boolean flag)
    {
        jR_1.setEnabled(!flag);
        jR_2.setEnabled(!flag);
        jR_3.setEnabled(!flag);
        jR_4.setEnabled(!flag);
        jR_5.setEnabled(!flag);
        jR_6.setEnabled(flag);
    }
    
    public void setEnabledReport(boolean flag)
    {
        jR_1.setEnabled(!flag);
        jR_2.setEnabled(!flag);
        jR_3.setEnabled(!flag);
        jR_4.setEnabled(!flag);
        jR_5.setEnabled(flag);
        jR_6.setEnabled(!flag);
    }
    
    public void setEnabledScript(boolean flag)
    {
        jR_1.setEnabled(flag);
        jR_2.setEnabled(flag);
        jR_3.setEnabled(flag);
        jR_4.setEnabled(flag);
        jR_5.setEnabled(flag);//prima era in !
        jR_6.setEnabled(!flag);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jR_1 = new javax.swing.JRadioButton();
        jR_2 = new javax.swing.JRadioButton();
        jR_3 = new javax.swing.JRadioButton();
        jR_4 = new javax.swing.JRadioButton();
        jR_5 = new javax.swing.JRadioButton();
        jR_6 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jl_1 = new javax.swing.JLabel();
        jB_1 = new javax.swing.JButton();
        
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setText("Your script could be applied in more than one part of package. You must now select ");
        jPanel1.add(jLabel2);
        
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("where ");
        jPanel1.add(jLabel3);
        
        jLabel4.setText("to apply it...");
        jPanel1.add(jLabel4);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.3;
        add(jPanel1, gridBagConstraints1);
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jR_1.setText("Draw Body Header On Next Line");
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
        
        jR_2.setText("Draw Body Header On Next Line");
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
        
        jR_3.setText("Draw Body Header On Next Line");
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
        
        jR_4.setText("Draw Body Header On Next Line");
        jR_4.setToolTipText("Direct");
        buttonGroup1.add(jR_4);
        jR_4.setContentAreaFilled(false);
        jR_4.setFocusPainted(false);
        jR_4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jR_4, gridBagConstraints2);
        
        jR_5.setText("Draw Body Header On Next Line");
        jR_5.setToolTipText("Direct");
        buttonGroup1.add(jR_5);
        jR_5.setContentAreaFilled(false);
        jR_5.setFocusPainted(false);
        jR_5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jR_5, gridBagConstraints2);
        
        jR_6.setText("Draw Body Header On Next Line");
        jR_6.setToolTipText("Direct");
        buttonGroup1.add(jR_6);
        jR_6.setContentAreaFilled(false);
        jR_6.setFocusPainted(false);
        jR_6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 5;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jR_6, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 1.0;
        add(jPanel2, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel3.setBackground(new java.awt.Color(212, 76, 35));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jl_1.setText("jLabel1");
        jl_1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jl_1, gridBagConstraints3);
        
        jB_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cestino.png")));
        jB_1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_1.setFocusPainted(false);
        jB_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jB_1, gridBagConstraints3);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 0.2;
        add(jPanel3, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jR_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_1ActionPerformed
        // Add your handling code here:
        boolean bRet=parent.readScript();
        parent.nextButtonSetEnabled(true);
        jB_1.setEnabled(bRet);
    }//GEN-LAST:event_jR_1ActionPerformed

    private void jB_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_1ActionPerformed
        // Add your handling code here:
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            return;
        }
        
        boolean bRet=parent.readScript(); 
        if(bRet==false)
            return;
        
        String descr=getTAG();
        if(flagSCRIPT==ADAMS_GlobalParam.SCRIPT_REPORT)
        {
            descr=vTag[4];
        }
        
        ADAMS_JD_Message op = new ADAMS_JD_Message(parent,true,"Delete Script ["+descr+"]. Please confirm operation.","Info",6);
        int Yes_No = op.getAnswer();

        
        if(Yes_No == 1)
        {
               
            if(parent.deleteScripts())
            {
                ADAMS_GlobalParam.optionPanel(parent,"INFO: Script  "+descr+" has been deleted.","Info",3);
                parent.flagDELETE=true;
                parent.flagCOMMIT=true;
                parent.closeFrame();
            }else
            {
                ADAMS_GlobalParam.optionPanel(parent,"ERROR: Script "+descr+" not deleted.","ERROR",1);
                parent.flagDELETE=false;
                parent.flagCOMMIT=false;
            }
        }else
        {
            return;
        }
    }//GEN-LAST:event_jB_1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jR_1;
    private javax.swing.JRadioButton jR_2;
    private javax.swing.JRadioButton jR_3;
    private javax.swing.JRadioButton jR_4;
    private javax.swing.JRadioButton jR_5;
    private javax.swing.JRadioButton jR_6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jl_1;
    private javax.swing.JButton jB_1;
    // End of variables declaration//GEN-END:variables

}
