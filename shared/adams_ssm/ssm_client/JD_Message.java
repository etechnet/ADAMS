import java.awt.Toolkit;
import java.awt.Dimension;

public class JD_Message extends javax.swing.JDialog {
    
    public JD_Message(javax.swing.JDialog parent, boolean modal, String message, String title, int Value_type) {        
        super(parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);
        jB_Cancel.setVisible(false);
        jTextField1.setVisible(false);

        jLabel1.setFont(SSM_GlobalParam.font_B11);
        jTextArea1.setFont(SSM_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        jB_Cancel.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        int len=message.length();
        int righe=len/40;
        int add=0;
        if(righe>1)
        {
            add=((righe-1)*17);
        }
        
        setCenteredFrame(290,170+add);
        //show();
        this.setAlwaysOnTop(true);
	this.setVisible(true);
        this.toFront();
                
    }
       
    public JD_Message(javax.swing.JDialog parent, boolean modal, String message, String title, int Value_type, int width, int height) {
        super(parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);
        jB_Cancel.setVisible(false);
        jTextField1.setVisible(false);

        jLabel1.setFont(SSM_GlobalParam.font_B11);
        jTextArea1.setFont(SSM_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        jB_Cancel.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        setCenteredFrame(width,height);
        //show();
        this.setAlwaysOnTop(true);
	this.setVisible(true);
        this.toFront();
    }
        
    public JD_Message(javax.swing.JFrame parent, boolean modal, String message, String title, int Value_type) {
        super((java.awt.Frame)parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);
        jB_Cancel.setVisible(false);
        jTextField1.setVisible(false);

        jLabel1.setFont(SSM_GlobalParam.font_B11);
        jTextArea1.setFont(SSM_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        jB_Cancel.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        int len=message.length();
        int righe=len/40;
        int add=0;
        if(righe>1)
        {
            add=((righe-1)*17);
        }
        
        setCenteredFrame(290,170+add);
        //show();
        this.setAlwaysOnTop(true);
        this.setVisible(true);    
        this.toFront();
    }
    
    public JD_Message(javax.swing.JFrame parent, boolean modal, String message, String title,String input_text) {
        super((java.awt.Frame)parent, modal);
        this.JD_TYPE = INPUT_QUESTION;

        initComponents();
        INPUT_TEXT = input_text;
        jTextField1.setText(input_text);
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);
        jB_Cancel.setVisible(false);

        jLabel1.setFont(SSM_GlobalParam.font_B11);
        jTextArea1.setFont(SSM_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        jB_Cancel.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        int len=message.length();
        int righe=len/40;
        int add=0;
        if(righe>1)
        {
            add=((righe-1)*17);
        }
        
        setCenteredFrame(290,170+add);
        //show();
        this.setAlwaysOnTop(true);
        this.setVisible(true);    
        this.toFront();
    }
    
         
        
    public JD_Message(javax.swing.JFrame parent, boolean modal, String message, String title, int Value_type, int width, int height) {
        super((java.awt.Frame)parent, modal);
        this.JD_TYPE = Value_type;
        initComponents();
        
        jB_yes.setVisible(false);
        jB_no.setVisible(false);
        jB_Cancel.setVisible(false);
        jTextField1.setVisible(false);

        jLabel1.setFont(SSM_GlobalParam.font_B11);
        jTextArea1.setFont(SSM_GlobalParam.font_B11);
        
        jB_ok.setCursor(cursor_hand);
        jB_yes.setCursor(cursor_hand);
        jB_no.setCursor(cursor_hand);
        jB_Cancel.setCursor(cursor_hand);
        
        setMessage(message);
        setTitleIcon_Type(title,JD_TYPE);
        setCenteredFrame(width,height);
        //show();
        this.setAlwaysOnTop(true);
	this.setVisible(true);
        this.toFront();
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
                
            case INPUT_QUESTION:    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
                                    jB_Cancel.setVisible(true);
                                    jTextField1.setVisible(true);
                                    jTextField1.requestFocus();
                                    break;  //9
        }
    }
    
    public void setMessage(String str)
    {
        jTextArea1.setText(str);
    }
       
    public String getInputText()
    {
        return INPUT_TEXT;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JP_center = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jP_south = new javax.swing.JPanel();
        jB_ok = new javax.swing.JButton();
        jB_yes = new javax.swing.JButton();
        jB_no = new javax.swing.JButton();
        jB_Cancel = new javax.swing.JButton();

        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                formWindowDeiconified(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(183, 206, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(183, 206, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        JP_center.setBackground(new java.awt.Color(183, 206, 255));
        JP_center.setLayout(new java.awt.GridBagLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(183, 206, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        JP_center.add(jTextArea1, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(4, 24));
        jTextField1.setPreferredSize(new java.awt.Dimension(70, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        JP_center.add(jTextField1, gridBagConstraints);

        jPanel1.add(JP_center, java.awt.BorderLayout.CENTER);

        jP_south.setBackground(new java.awt.Color(183, 206, 255));

        jB_ok.setText("OK");
        jB_ok.setToolTipText("OK");
        jB_ok.setFocusPainted(false);
        jB_ok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_ok.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_ok.setPreferredSize(new java.awt.Dimension(50, 50));
        jB_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_okActionPerformed(evt);
            }
        });
        jP_south.add(jB_ok);

        jB_yes.setText("YES");
        jB_yes.setToolTipText("YES");
        jB_yes.setFocusPainted(false);
        jB_yes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_yes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_yes.setMaximumSize(new java.awt.Dimension(50, 50));
        jB_yes.setMinimumSize(new java.awt.Dimension(50, 50));
        jB_yes.setPreferredSize(new java.awt.Dimension(50, 50));
        jB_yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_yesActionPerformed(evt);
            }
        });
        jP_south.add(jB_yes);

        jB_no.setText("NO");
        jB_no.setToolTipText("NO");
        jB_no.setFocusPainted(false);
        jB_no.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_no.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_no.setMaximumSize(new java.awt.Dimension(50, 50));
        jB_no.setMinimumSize(new java.awt.Dimension(50, 50));
        jB_no.setPreferredSize(new java.awt.Dimension(50, 50));
        jB_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_noActionPerformed(evt);
            }
        });
        jP_south.add(jB_no);

        jB_Cancel.setText("CANCEL");
        jB_Cancel.setToolTipText("CANCEL");
        jB_Cancel.setFocusPainted(false);
        jB_Cancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_Cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_Cancel.setMaximumSize(new java.awt.Dimension(60, 50));
        jB_Cancel.setMinimumSize(new java.awt.Dimension(60, 50));
        jB_Cancel.setPreferredSize(new java.awt.Dimension(60, 50));
        jB_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_CancelActionPerformed(evt);
            }
        });
        jP_south.add(jB_Cancel);

        jPanel1.add(jP_south, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_noActionPerformed
        YES_NO = 0;
        closeFrame();        
    }//GEN-LAST:event_jB_noActionPerformed

    private void jB_yesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_yesActionPerformed
        YES_NO = 1;
        closeFrame();       
    }//GEN-LAST:event_jB_yesActionPerformed

    private void jB_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_okActionPerformed
        
        String text = jTextField1.getText().trim();
        if(text.isEmpty())
            INPUT_TEXT = null;
        else
            INPUT_TEXT = text;
        closeFrame();
    }//GEN-LAST:event_jB_okActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeFrame();
    }//GEN-LAST:event_closeDialog

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        System.out.println("DEBUG JD_Message.java formWindowGainedFocus");
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        System.out.println("DEBUG JD_Message.java formWindowDeiconified");
    }//GEN-LAST:event_formWindowDeiconified

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        System.out.println("DEBUG JD_Message.java formWindowIconified");
    }//GEN-LAST:event_formWindowIconified

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        System.out.println("DEBUG JD_Message.java formWindowLostFocus");
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
       System.out.println("DEBUG D_Message.java formWindowDeactivated");
    }//GEN-LAST:event_formWindowDeactivated

    private void jB_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_CancelActionPerformed
        jTextField1.setText("");
        INPUT_TEXT = null;
        closeFrame();
    }//GEN-LAST:event_jB_CancelActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        System.out.println("DEBUG JD_Message.java formWindowOpened");
    }//GEN-LAST:event_formWindowOpened

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        System.out.println("DEBUG JD_Message.java formWindowStateChanged");
    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.out.println("DEBUG JD_Message.java formWindowClosed");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        System.out.println("DEBUG JD_Message.java formWindowActivated");
    }//GEN-LAST:event_formWindowActivated

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        System.out.println("DEBUG JD_Message.java formPropertyChange");
    }//GEN-LAST:event_formPropertyChange

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
         System.out.println("DEBUG JD_Message.java formFocusLost");
    }//GEN-LAST:event_formFocusLost

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
       System.out.println("DEBUG JD_Message.java formFocusGained");
    }//GEN-LAST:event_formFocusGained

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        System.out.println("DEBUG JD_Message.java formComponentHidden");
    }//GEN-LAST:event_formComponentHidden

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
    private javax.swing.JPanel JP_center;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jB_Cancel;
    private javax.swing.JButton jB_no;
    private javax.swing.JButton jB_ok;
    private javax.swing.JButton jB_yes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private int JD_TYPE;
    
    public static final int CRITICAL          = 1;
    public static final int HELP              = 2;
    public static final int INFO              = 3;
    public static final int WARNING           = 4;
    public static final int QUESTION_YES_NO   = 5;
    public static final int WARNING_YES_NO    = 6;
    public static final int LOCK_WAR_YES_NO   = 7;
    public static final int LOCK_WARNING      = 8;
    public static final int INPUT_QUESTION    = 9;
    
    public int YES_NO = 0;
    public String INPUT_TEXT = null;
    
    
    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    //private java.awt.Cursor cursor_wait         = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    //private java.awt.Cursor cursor_default      = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    
}
