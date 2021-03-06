/*
 * MDM_JP_wait.java
 *
 * Created on 25 gennaio 2005, 11.21
 */

/**
 *
 * @author  root
 */
public class MDM_JP_wait extends javax.swing.JPanel {

    /** Creates new form MDM_JP_wait */
    public MDM_JP_wait(String Message, String str_iconName) {
        
        initComponents();
        jL_Text.setFont(staticLib.fontA10);
        jL_Text.setText(Message);
        
        jL_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+str_iconName)));
        jL_Icon.validate();
        jL_Icon.repaint();
        repaint();
    }
    
    public MDM_JP_wait(String Message, javax.swing.ImageIcon iconWait) {
        
        initComponents();
        jL_Text.setFont(staticLib.fontA10);
        jL_Text.setText(Message);
        
        jL_Icon.setIcon(iconWait); 
        jL_Icon.validate();
        jL_Icon.repaint();
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_Icon = new javax.swing.JLabel();
        jL_Text = new javax.swing.JLabel();
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        setPreferredSize(new java.awt.Dimension(230, 100));
        setMinimumSize(new java.awt.Dimension(230, 100));
        setMaximumSize(new java.awt.Dimension(230, 100));
        jL_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clock_anim.gif")));
        jL_Icon.setForeground(java.awt.Color.black);
        jL_Icon.setBackground(new java.awt.Color(230, 230, 230));
        jL_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Icon.setOpaque(true);
        add(jL_Icon, java.awt.BorderLayout.CENTER);
        
        jL_Text.setForeground(java.awt.Color.black);
        jL_Text.setBackground(new java.awt.Color(230, 230, 230));
        jL_Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Text.setPreferredSize(new java.awt.Dimension(30, 20));
        jL_Text.setOpaque(true);
        add(jL_Text, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    public void setMessage(String Message)
    {
        jL_Text.setText(Message);
    }
    public void setIconMessage(String str_iconName)
    {
        jL_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+str_iconName)));
    }

    public void setIconMessage(javax.swing.ImageIcon iconWait)
    {
        jL_Icon.setIcon(iconWait);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JLabel jL_Text;
    // End of variables declaration//GEN-END:variables

}
