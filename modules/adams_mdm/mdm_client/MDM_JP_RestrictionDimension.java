/*
 * MDM_JP_RestrictionDimension.java
 *
 * Created on 1 dicembre 2004, 14.35
 */

/**
 *
 * @author  root
 */
public class MDM_JP_RestrictionDimension extends javax.swing.JPanel {

    /** Creates new form MDM_JP_RestrictionDimension */
    public MDM_JP_RestrictionDimension() {
        initComponents();
        
        jLabel1.setFont(staticLib.fontA10);
        jc_Dimension.setFont(staticLib.fontA9);
        jc_Dimension.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dimension.gif")));
        jc_Dimension.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dimension_press.gif")));
        jc_Dimension.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jc_Dimension = new javax.swing.JCheckBox();
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setText("Select Dimension");
        jLabel1.setMaximumSize(new java.awt.Dimension(250, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(250, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(250, 20));
        add(jLabel1, java.awt.BorderLayout.NORTH);
        
        jc_Dimension.setBackground(new java.awt.Color(230, 230, 230));
        jc_Dimension.setText("Normal Dimension");
        jc_Dimension.setContentAreaFilled(false);
        jc_Dimension.setFocusPainted(false);
        jc_Dimension.setMaximumSize(new java.awt.Dimension(250, 60));
        jc_Dimension.setMinimumSize(new java.awt.Dimension(250, 60));
        jc_Dimension.setPreferredSize(new java.awt.Dimension(250, 60));
        jc_Dimension.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jc_DimensionItemStateChanged(evt);
            }
        });
        
        add(jc_Dimension, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    private void jc_DimensionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jc_DimensionItemStateChanged
      if (jc_Dimension.isSelected())
      {
          jc_Dimension.setText("Single Dimension");
          staticLib.struc_paramsCURRENT.SingleRelation=true;
      }
      else
      {
          jc_Dimension.setText("Normal Dimension");
          staticLib.struc_paramsCURRENT.SingleRelation=false;
      }
    }//GEN-LAST:event_jc_DimensionItemStateChanged

    public void validateDimension()
    {
        //////System.out.println("Dimensione: "+staticLib.struc_paramsCURRENT.SingleRelation);
        if(staticLib.struc_paramsCURRENT.SingleRelation)
        {
            jc_Dimension.setSelected(true);
        }else
        {
            jc_Dimension.setSelected(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox jc_Dimension;
    // End of variables declaration//GEN-END:variables

}