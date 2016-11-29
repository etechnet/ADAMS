/*
 * MDM_JP_RestrictionMode.java
 *
 * Created on 1 dicembre 2004, 14.54
 */

/**
 *
 * @author  root
 */
public class MDM_JP_RestrictionMode extends javax.swing.JPanel {

    /** Creates new form MDM_JP_RestrictionMode */
    public MDM_JP_RestrictionMode(insiemi i) {
        this.local_i=i;
        
        initComponents();
        jLabel1.setFont(staticLib.fontA10);
        jrb_Normal.setFont(staticLib.fontA9);
        jrb_Exclusive.setFont(staticLib.fontA9);
        jrb_Normal.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        jrb_Exclusive.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        
        jrb_Normal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mode.gif")));
        //jrb_Normal.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mode_press_off.gif")));
        jrb_Normal.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mode_press.gif")));
        
        jrb_Exclusive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mode1.gif")));
       // jrb_Exclusive.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mode1_press_off.gif")));
        jrb_Exclusive.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mode1_press.gif")));
        
        validateMode();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jrb_Normal = new javax.swing.JRadioButton();
        jrb_Exclusive = new javax.swing.JRadioButton();
        
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setText("Select Restriction Mode");
        jLabel1.setForeground(java.awt.Color.black);
        jLabel1.setPreferredSize(new java.awt.Dimension(250, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(250, 20));
        jLabel1.setMaximumSize(new java.awt.Dimension(250, 20));
        add(jLabel1, java.awt.BorderLayout.NORTH);
        
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jrb_Normal.setToolTipText("Attive Exclusive Mode");
        jrb_Normal.setText("Normal Mode");
        jrb_Normal.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_Normal);
        jrb_Normal.setPreferredSize(new java.awt.Dimension(250, 40));
        jrb_Normal.setMaximumSize(new java.awt.Dimension(250, 40));
        jrb_Normal.setFocusPainted(false);
        jrb_Normal.setContentAreaFilled(false);
        jrb_Normal.setMinimumSize(new java.awt.Dimension(250, 40));
        jrb_Normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_ActionPerformed(evt);
            }
        });
        
        jPanel1.add(jrb_Normal);
        
        jrb_Exclusive.setToolTipText("Attive Exclusive Mode");
        jrb_Exclusive.setText("Exclusive Mode");
        jrb_Exclusive.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_Exclusive);
        jrb_Exclusive.setFocusPainted(false);
        jrb_Exclusive.setContentAreaFilled(false);
        jrb_Exclusive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_ActionPerformed(evt);
            }
        });
        
        jPanel1.add(jrb_Exclusive);
        
        add(jPanel1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    public void validateMode()
    {
    	if(staticLib.struc_paramsCURRENT.OppositeRestriction)
    	{	
            jrb_Exclusive.setSelected(true);
        }else
        {
            jrb_Normal.setSelected(true);
        }
    }
    
    private void jrb_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_ActionPerformed
             
        if (evt.getSource()!=jrb_Normal)
        {
        //inverse
          staticLib.struc_paramsCURRENT.OppositeRestriction=true;
          if(local_i.returnStatus())
                local_i.inverseIntersect();
        }
        else
        {
        //normal
          staticLib.struc_paramsCURRENT.OppositeRestriction=false;
          if(!local_i.returnStatus())
                local_i.inverseIntersect();
        }	  
    }//GEN-LAST:event_jrb_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jrb_Normal;
    private javax.swing.JRadioButton jrb_Exclusive;
    // End of variables declaration//GEN-END:variables

    private insiemi local_i;
}