package net.etech.loadconfig;

import java.awt.Toolkit;
import java.awt.Dimension;

public class ADAMS_JD_Message extends javax.swing.JDialog {
    
    /** Creates new form ADAMS_JD_Message */    
    public ADAMS_JD_Message(javax.swing.JDialog parent, boolean modal, String message, String title ,int Value_type) {
        super(parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);

        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jTextArea1.setFont(ADAMS_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        setCenteredFrame(300,200);
        //show();
        this.setVisible(true);                
    }
      
    
    
    /** Creates new form ADAMS_JD_Message */    
    public ADAMS_JD_Message(javax.swing.JDialog parent, boolean modal, String message, String title ,int Value_type,int width,int height ) {
        super(parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);

        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jTextArea1.setFont(ADAMS_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        setCenteredFrame(width,height);
        //show();
        this.setVisible(true); 
    }
    
    /** Creates new form ADAMS_JD_Message */    
    public ADAMS_JD_Message(javax.swing.JFrame parent, boolean modal, String message, String title ,int Value_type) {
        super((java.awt.Frame)parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);

        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jTextArea1.setFont(ADAMS_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        setCenteredFrame(300,200);
        //show();
        this.setVisible(true);                
    }
         
    
    /** Creates new form ADAMS_JD_Message */    
    public ADAMS_JD_Message(javax.swing.JFrame parent, boolean modal, String message, String title ,int Value_type,int width,int height ) {
        super((java.awt.Frame)parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);

        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jTextArea1.setFont(ADAMS_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        setCenteredFrame(width,height);
        //show();
        this.setVisible(true); 
    }
    
    
    public void setTitleIcon_Type(String str,int IconValue_type)
    {
        jLabel1.setText(str);
        switch(IconValue_type)
        {
            case CRITICAL:  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/critical.png"))); 
                            break;  //1
            
            case HELP:      jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));        
                            break;  //2  
                            
            case INFO:      jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png")));            
                            break;  //3
            
            case WARNING:   jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/warning.png")));  
                            break;  //4
                            
            case QUESTION_YES_NO :  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));        
                                    jB_yes.setVisible(true);
                                    jB_no.setVisible(true);
                                    jB_ok.setVisible(false);
                                    break;  //5
                                    
            case WARNING_YES_NO :   jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/warning.png")));        
                                    jB_yes.setVisible(true);
                                    jB_no.setVisible(true);
                                    jB_ok.setVisible(false);
                                    break;  //6 
            
            case LOCK_WAR_YES_NO :  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock.png")));        
                                    jB_yes.setVisible(true);
                                    jB_no.setVisible(true);
                                    jB_ok.setVisible(false);
                                    break;  //7 
                                    
            case LOCK_WARNING:      jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock.png")));  
                                    break;  //8
        }
    }
    
    public void setMessage(String str)
    {
        jTextArea1.setText(str);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JP_center = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        jP_south = new javax.swing.JPanel();
        jB_ok = new javax.swing.JButton();
        jB_yes = new javax.swing.JButton();
        jB_no = new javax.swing.JButton();
        
        
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jPanel1.setLayout(new java.awt.BorderLayout());
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.EtchedBorder());
        jLabel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 40));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
        
        JP_center.setLayout(new java.awt.GridLayout(1, 0));
        
        jTextArea1.setBackground(new java.awt.Color(230, 230, 230));
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        JP_center.add(jTextArea1);
        
        jPanel1.add(JP_center, java.awt.BorderLayout.CENTER);
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jB_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok.jpg")));
        jB_ok.setToolTipText("");
        jB_ok.setBorderPainted(false);
        jB_ok.setContentAreaFilled(false);
        jB_ok.setFocusPainted(false);
        jB_ok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_ok.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_press.jpg")));
        jB_ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_over.jpg")));
        jB_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_okActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_ok);
        
        jB_yes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_yes.jpg")));
        jB_yes.setToolTipText("");
        jB_yes.setBorderPainted(false);
        jB_yes.setContentAreaFilled(false);
        jB_yes.setFocusPainted(false);
        jB_yes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_yes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_yes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_yes_press.jpg")));
        jB_yes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_yes_over.jpg")));
        jB_yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_yesActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_yes);
        
        jB_no.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_no.jpg")));
        jB_no.setToolTipText("");
        jB_no.setBorderPainted(false);
        jB_no.setContentAreaFilled(false);
        jB_no.setFocusPainted(false);
        jB_no.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_no.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_no.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_no_press.jpg")));
        jB_no.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_no_over.jpg")));
        jB_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_noActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_no);
        
        jPanel1.add(jP_south, java.awt.BorderLayout.SOUTH);
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private void jB_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_noActionPerformed
        YES_NO = 0;
        closeFrame();        
    }//GEN-LAST:event_jB_noActionPerformed

    private void jB_yesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_yesActionPerformed
        YES_NO = 1;
        closeFrame();       
    }//GEN-LAST:event_jB_yesActionPerformed

    private void jB_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_okActionPerformed
        closeFrame();
    }//GEN-LAST:event_jB_okActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeFrame();
    }//GEN-LAST:event_closeDialog

    public int getAnswer()
    {
        return YES_NO;
    }
    
    private void closeFrame()
    {
        setVisible(false);
        
        if(( JD_TYPE == CRITICAL ) || ( JD_TYPE == HELP) || ( JD_TYPE == INFO) || ( JD_TYPE ==  WARNING) || ( JD_TYPE ==  LOCK_WARNING)) // OK
            this.dispose();
    }
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel JP_center;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_ok;
    private javax.swing.JButton jB_yes;
    private javax.swing.JButton jB_no;
    // End of variables declaration//GEN-END:variables

    private int JD_TYPE;
    
    private final int CRITICAL          = 1;
    private final int HELP              = 2;
    private final int INFO              = 3;
    private final int WARNING           = 4;
    private final int QUESTION_YES_NO   = 5;
    private final int WARNING_YES_NO    = 6;
    private final int LOCK_WAR_YES_NO   = 7;
    private final int LOCK_WARNING      = 8;
    
    private int YES_NO = 0;
    
    
    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    //private java.awt.Cursor cursor_wait         = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    //private java.awt.Cursor cursor_default      = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    
}
