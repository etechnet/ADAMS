/*
 * ADAMS_JP_Script_p1.java
 *
 * Created on 24 ottobre 2005, 9.54
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */
public class ADAMS_JP_Script_p1 extends javax.swing.JPanel {

    /** Creates new form ADAMS_JP_Script_p1 */
    private ADAMS_JF_WIZARDBASE                      parent                      = null;
    public ADAMS_JP_Script_p1(ADAMS_JF_WIZARDBASE parent,String teDesc) {
        this.parent=parent;
        initComponents();
            jl_titolo.setText(teDesc);
        
    }
   
    public void refreshPanel()
    {
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jl_titolo = new javax.swing.JLabel();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel8.setText("Welcome to the");
        jPanel1.add(jLabel8);
        
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("NTMBAsic Wizard");
        jPanel1.add(jLabel9);
        
        jLabel10.setText("Here you will be guided to ");
        jPanel1.add(jLabel10);
        
        jLabel11.setForeground(java.awt.Color.blue);
        jLabel11.setText("Add/Modify/Delete");
        jPanel1.add(jLabel11);
        
        jLabel13.setText("NTMBasic script to customize the");
        jPanel1.add(jLabel13);
        
        jLabel14.setText("element you are working on.");
        jPanel1.add(jLabel14);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 0.1;
        add(jPanel1, gridBagConstraints1);
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel15.setText("If this");
        jPanel2.add(jLabel15);
        
        jLabel16.setForeground(java.awt.Color.blue);
        jLabel16.setText("NOT");
        jPanel2.add(jLabel16);
        
        jLabel17.setText("want you want press");
        jPanel2.add(jLabel17);
        
        jLabel18.setForeground(new java.awt.Color(153, 51, 0));
        jLabel18.setText("[Cancel]");
        jPanel2.add(jLabel18);
        
        jLabel19.setText("button now.");
        jPanel2.add(jLabel19);
        
        jLabel20.setText("Elsewhere press the");
        jPanel2.add(jLabel20);
        
        jLabel21.setForeground(new java.awt.Color(153, 51, 0));
        jLabel21.setText("[Next]");
        jPanel2.add(jLabel21);
        
        jLabel22.setText("button to go on.");
        jPanel2.add(jLabel22);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 0.1;
        add(jPanel2, gridBagConstraints1);
        
        jPanel3.setLayout(null);
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jLabel25.setText("The item is:");
        jPanel3.add(jLabel25);
        jLabel25.setBounds(250, 70, 73, 15);
        
        jl_titolo.setFont(new java.awt.Font("Dialog", 0, 18));
        jl_titolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/te_32.png")));
        jl_titolo.setText("jLabel26");
        jPanel3.add(jl_titolo);
        jl_titolo.setBounds(160, 110, 590, 40);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jPanel3, gridBagConstraints1);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jl_titolo;
    // End of variables declaration//GEN-END:variables

}
