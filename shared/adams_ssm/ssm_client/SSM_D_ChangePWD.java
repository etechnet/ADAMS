/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> PROCESS MONITOR client </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Raffaele Ficcadenti (E-TECH) Created on 13/05/2005 - <a href="mailto:raffaele.ficcadenti@e-tech.net">raffaele.ficcadenti@e-tech.net</a></p>
 *
 *
 * La classe estende una javax.swing.JDialog specificata come modale, la finestra risulta bloccante
 * sino all'interazione esplicita dell'utente.
 * La classe viene impiegata nell'applicativo per permettere all'utente di cambiare la propria password
 * di login (interazione tramite la classe SSM_JF_LoginStart ), la form dell'oggetto richede le informazione necessarie 
 * per effettuare un'operazione di tale entità di sicurezza. L'oggetto rende possibile l'operazione di "Change Password" 
 * tramite chiamata al server di configurazione tramite il metodo changeUserPASSWD(int ID_User,String old_Password,String password) 
 * della classe SSM_CORBAConnection (istanziate nella classe globale SSM_GlobalParam).
 *
 */
public class SSM_D_ChangePWD extends javax.swing.JDialog {

   
   /**
    * Costruttore della classe.
    *@param parent La finestra parente necessaria ad attivare la specifica modale.
    *@param idUser Indice identificativo dell'utente.
    *@param str_login Nome utente "login".
    */
    public SSM_D_ChangePWD(java.awt.Frame parent, int idUser, String str_login) 
    {
        super(parent, true);
        initComponents();
        
        ID_USER = idUser;
        jL_user.setText(str_login);
        
        jT_oldPasswd.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALLCHAR,SSM_GlobalParam.MAX_PASSWORD_LEN)); 
        jT_newPasswd.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALLCHAR,SSM_GlobalParam.MAX_PASSWORD_LEN)); 
        jT_confirm_newPasswd.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALLCHAR,SSM_GlobalParam.MAX_PASSWORD_LEN));
        
        // FONT GLOBALI
        jT_oldPasswd.setFont(SSM_GlobalParam.font_B11);
        jT_newPasswd.setFont(SSM_GlobalParam.font_B11);
        jT_confirm_newPasswd.setFont(SSM_GlobalParam.font_B11);
        
        this.setLocation(parent.getLocationOnScreen().x+60,parent.getLocationOnScreen().y+10);
        pack();
        //show();
        this.setVisible(true);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
          jP_tot = new javax.swing.JPanel();
          jL_desc1 = new javax.swing.JLabel();
          jL_user = new javax.swing.JLabel();
          jL_desc2 = new javax.swing.JLabel();
          jT_oldPasswd = new javax.swing.JPasswordField();
          jL_desc3 = new javax.swing.JLabel();
          jT_newPasswd = new javax.swing.JPasswordField();
          jL_desc4 = new javax.swing.JLabel();
          jT_confirm_newPasswd = new javax.swing.JPasswordField();
          jLabel10 = new javax.swing.JLabel();
          jB_apply = new javax.swing.JButton();
          jB_cancel = new javax.swing.JButton();
          
          setTitle("Change PASSWORD");
          addWindowListener(new java.awt.event.WindowAdapter() {
              public void windowClosing(java.awt.event.WindowEvent evt) {
                  closeDialog(evt);
              }
          });
          
          jP_tot.setLayout(null);
          
          jP_tot.setBackground(new java.awt.Color(230, 230, 230));
          jP_tot.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
          jP_tot.setMinimumSize(new java.awt.Dimension(220, 280));
          jP_tot.setPreferredSize(new java.awt.Dimension(220, 280));
          jL_desc1.setText("User");
          jP_tot.add(jL_desc1);
          jL_desc1.setBounds(20, 70, 180, 20);
          
          jL_user.setBackground(new java.awt.Color(230, 230, 230));
          jL_user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
          jP_tot.add(jL_user);
          jL_user.setBounds(20, 90, 180, 20);
          
          jL_desc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key_s.gif")));
          jL_desc2.setText("Old password");
          jP_tot.add(jL_desc2);
          jL_desc2.setBounds(20, 110, 180, 20);
          
          jP_tot.add(jT_oldPasswd);
          jT_oldPasswd.setBounds(20, 130, 180, 20);
          
          jL_desc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key_s.gif")));
          jL_desc3.setText("New Password");
          jP_tot.add(jL_desc3);
          jL_desc3.setBounds(20, 160, 180, 20);
          
          jP_tot.add(jT_newPasswd);
          jT_newPasswd.setBounds(20, 180, 180, 20);
          
          jL_desc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key_s.gif")));
          jL_desc4.setText("Confirm New Password");
          jP_tot.add(jL_desc4);
          jL_desc4.setBounds(20, 200, 180, 20);
          
          jP_tot.add(jT_confirm_newPasswd);
          jT_confirm_newPasswd.setBounds(20, 220, 180, 20);
          
          jLabel10.setForeground(java.awt.Color.red);
          jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/change_pwd.jpg")));
          jLabel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
          jP_tot.add(jLabel10);
          jLabel10.setBounds(24, 12, 170, 55);
          
          jB_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.gif")));
          jB_apply.setBorderPainted(false);
          jB_apply.setContentAreaFilled(false);
          jB_apply.setFocusPainted(false);
          jB_apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
          jB_apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.gif")));
          jB_apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.gif")));
          jB_apply.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton_ActionPerformed(evt);
              }
          });
          
          jP_tot.add(jB_apply);
          jB_apply.setBounds(30, 250, 70, 20);
          
          jB_cancel.setForeground(java.awt.Color.red);
          jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.gif")));
          jB_cancel.setBorderPainted(false);
          jB_cancel.setContentAreaFilled(false);
          jB_cancel.setFocusPainted(false);
          jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
          jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.gif")));
          jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.gif")));
          jB_cancel.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton_ActionPerformed(evt);
              }
          });
          
          jP_tot.add(jB_cancel);
          jB_cancel.setBounds(120, 250, 70, 20);
          
          getContentPane().add(jP_tot, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private void jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActionPerformed
        // Add your handling code here:
        if(evt.getSource() == jB_apply)
        {
           
            String str_old_Pass =new String(jT_oldPasswd.getPassword()).trim();
            String str_new_Pass =new String(jT_newPasswd.getPassword()).trim();
            String str_conf_Pass =new String(jT_confirm_newPasswd.getPassword()).trim();
            
            if (str_old_Pass.compareTo("")==0)
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(null,"Insert old password.","Information Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                jT_oldPasswd.requestFocus();
                return;
            
            }
            
            
            if ( (str_new_Pass.compareTo("")!=0) && (str_conf_Pass.compareTo("")!=0) )
            {
                if(str_new_Pass.compareTo(str_conf_Pass)!=0)
                {    
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(null,"Inserted new password Does not match.","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE);
                    jT_newPasswd.setText("");
                    jT_confirm_newPasswd.setText("");
                    jT_newPasswd.requestFocus();
                    return;
                }
            }
            else
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(null,"Insert new password than confirm.","Information Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                jT_newPasswd.requestFocus();
                return;
            
            }

           // System.out.println("ID USER = "+ID_USER);
           // System.out.println("OLD USER = "+str_old_Pass);
           // System.out.println("NEW pwd = "+str_new_Pass);
            
            boolean stat = SSM_GlobalParam.CORBAConn.changeUserPASSWD(ID_USER,str_old_Pass,str_new_Pass);
            if(stat == true)
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(null,"Password properly changed.","Information Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            else
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(null,"Password NOT changed, retry... ","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE);
                jT_oldPasswd.setText("");
                jT_newPasswd.setText("");
                jT_confirm_newPasswd.setText("");
                jT_oldPasswd.requestFocus();
            }
      
        }
        else
        {
            dispose();
        }
    }//GEN-LAST:event_jButton_ActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        dispose();
    }//GEN-LAST:event_closeDialog

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_tot;
    private javax.swing.JLabel jL_desc1;
    private javax.swing.JLabel jL_user;
    private javax.swing.JLabel jL_desc2;
    private javax.swing.JPasswordField jT_oldPasswd;
    private javax.swing.JLabel jL_desc3;
    private javax.swing.JPasswordField jT_newPasswd;
    private javax.swing.JLabel jL_desc4;
    private javax.swing.JPasswordField jT_confirm_newPasswd;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables

    private int ID_USER;
}
