/*
 * ADAMS_JP_Report_b2.java
 *
 * Created on 10 agosto 2005, 10.49
 */

/**
 *
 * @author  Raffaele Ficcadenti
 */

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.table.*;
import javax.swing.DefaultCellEditor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_JP_Report_b2 extends javax.swing.JPanel {
    
    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private ADAMS_JP_ReportObjectEditor              reportObjectEditor          = null;
    private ADAMS_JF_Report                          frame_parent                = null; 
    
    
    /** Creates new form ADAMS_JP_Report_b2 */
    public ADAMS_JP_Report_b2(ADAMS_JF_Report f,javax.swing.JPanel p) {
        reportObjectEditor=(ADAMS_JP_ReportObjectEditor)p;
        frame_parent=f;
        initComponents();
        setFont();
        setCursorWidget();
    }
    
    public javax.swing.JTextArea getAreaHelp()
    {
        return jTextArea1;
    }
    
    public void setFont()
    {
        jTextArea1.setFont(ADAMS_GlobalParam.font_B11);
        
    }
    
    public void setCursorWidget()
    {    
        jB_apply.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jB_apply = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        
        setLayout(new java.awt.BorderLayout());
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(new javax.swing.border.TitledBorder("Help"));
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel2.add(jTextArea1, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jB_apply.setBackground(new java.awt.Color(230, 230, 230));
        jB_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        jB_apply.setBorder(null);
        jB_apply.setBorderPainted(false);
        jB_apply.setContentAreaFilled(false);
        jB_apply.setFocusPainted(false);
        jB_apply.setMaximumSize(new java.awt.Dimension(80, 20));
        jB_apply.setMinimumSize(new java.awt.Dimension(80, 20));
        jB_apply.setPreferredSize(new java.awt.Dimension(80, 20));
        jB_apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        jB_apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        jB_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_applyActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 10, 5);
        jPanel3.add(jB_apply, gridBagConstraints3);
        
        jB_close.setForeground(java.awt.Color.red);
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMaximumSize(new java.awt.Dimension(80, 20));
        jB_close.setMinimumSize(new java.awt.Dimension(80, 20));
        jB_close.setPreferredSize(new java.awt.Dimension(80, 20));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_applyActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 10, 5);
        jPanel3.add(jB_close, gridBagConstraints3);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel1.add(jPanel3, gridBagConstraints1);
        
        add(jPanel1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
        // Add your handling code here:
        
        //System.out.println("ADAMS_JP_Report_b2 -->  Eseguo il test modify");
        
        
        if (evt.getSource() == jB_close) 
        {
            
                
            boolean flagMODIFY=frame_parent.testModify();
            //System.out.println("ADAMS_JP_Report_b2 -->  flagMODIFY="+flagMODIFY);

            if(flagMODIFY==true)
            {
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Warning: Any changes made will be lost ! Do you really want to exit ?","Warning",6);
                int Yes_No = op.getAnswer();

                if(Yes_No == 1)
                {
                    //System.out.println("Prima ADAMS_GlobalParam.MODIFY_ELEMENT="+ADAMS_GlobalParam.MODIFY_ELEMENT);
                    if(ADAMS_GlobalParam.MODIFY_ELEMENT==0)
                    {
                        ADAMS_GlobalParam.MODIFY_ELEMENT=0;
                    }
                    //System.out.println("Dopo ADAMS_GlobalParam.MODIFY_ELEMENT="+ADAMS_GlobalParam.MODIFY_ELEMENT);
                }else
                {
                    return;
                } 
            } 
            frame_parent.closeFrame(false);
            ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
        }else if (evt.getSource() == jB_apply) 
        {
            boolean flagMODIFY=frame_parent.testModify();
            

            if(flagMODIFY==true)
            {
                ADAMS_GlobalParam.MODIFY_ELEMENT=1;
            }

            boolean bRet=reportObjectEditor.commit();
            if(bRet)
            {
                frame_parent.closeFrame(false);
                ADAMS_GlobalParam.INSERT_NEW_ELEMENT=1;
            }else
            {
                ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
            }
        }
    }//GEN-LAST:event_jB_applyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables

}
