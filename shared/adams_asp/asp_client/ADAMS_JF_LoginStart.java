import java.util.Vector;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *
 * Questa classe viene utilizzata per effettuare l'accesso all'applicativo. 
 * L'interfaccia, mette a disposizione componenti grafici per effettuare l'operazione di logon, viene richiesto all'utente
 * l'inserimento di una user-login e password, la classe tramite chiamata al server richiede se l'operazione di logon
 * effettuata risulta idonea per l'accesso all'applicativo ADAMS. 
 * La richiesta di controllo user-login e password viene effettuata tramite il metodo getUserProfile(user-login,password) 
 * della classe connCORBA (dichiarata e istanziate nella classe globale staticLib); se il metodo ritorna con esito positivo, 
 * viene effettuato un cotrollo per rilevare il livello di accesso dell'utente (abilitazioni consentite).
 *  * 
 * Inoltre l'interfaccia ADAMS_JD_ChangePWD mette a disposizione un form per effettuare il cambio della password dell'utente,
 * la classe MON_D_ChangePWD viene richiamata tramite specifico pulsante "Change password". 
 *
 *@see ADAMS_JD_ChangePWD
 */
public class ADAMS_JF_LoginStart extends javax.swing.JFrame implements Runnable {


    /** 
     * Costruttore di default della classe.
     */
    public ADAMS_JF_LoginStart(javax.swing.JApplet applet) 
    {        
        /*CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(applet);
        connOK_STSConfig = CORBA.openConnection_config();*/
        
        if(connOK_MASTERSERVER)
            System.out.println("Connection Master Server Factory... ok.");
        else
            System.out.println(" - Error Connection CORBA.");
        
        ADAMS_GlobalParam.STSConn = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        ADAMS_GlobalParam.STSConn.setJApplet(applet);
        connOK_STSConfig=ADAMS_GlobalParam.STSConn.openConnection_config();
        
        if(connOK_STSConfig)
            System.out.println("Connection STSConfig... ok.");
        else
            System.out.println(" - Error Connection CORBA.");
        
        if(connOK_STSConfig == true)
        {
            initComponents();

            jB_OK.setEnabled(false);

            event_KEY = (new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                    jT_KeyPressed(evt);
            }
            });

            jB_changePWD.setCursor(Cur_hand);
            jB_Apply.setCursor(Cur_hand);
            jB_close.setCursor(Cur_hand);
            jB_OK.setCursor(Cur_hand);
            
            jL_icon.setFont(ADAMS_GlobalParam.font_B11);
            jL_desc.setFont(ADAMS_GlobalParam.font_B11);
            jL_user.setFont(ADAMS_GlobalParam.font_B11);
            jTF_login.setFont(ADAMS_GlobalParam.font_B11);
            jL_pwd.setFont(ADAMS_GlobalParam.font_B11);
            jPF_pwd.setFont(ADAMS_GlobalParam.font_B11);
            jlBar.setFont(ADAMS_GlobalParam.font_B11);
                        
            //this.setSize(new java.awt.Dimension(298,438));
            setCenteredFrame(298,370);
            
            //show();
            this.setVisible(true);
            this.toFront();
            this.validate();
            this.repaint();
         }
         else
         {
            ADAMS_GlobalParam.optionPanel(this,"Connection failure.", "Critical Error.",1);
         }
        
        //setGui_enabled(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_TOT = new javax.swing.JPanel();
        jL_icon = new javax.swing.JLabel();
        jL_desc = new javax.swing.JLabel();
        jL_user = new javax.swing.JLabel();
        jTF_login = new javax.swing.JTextField();
        jL_pwd = new javax.swing.JLabel();
        jPF_pwd = new javax.swing.JPasswordField();
        jB_changePWD = new javax.swing.JButton();
        jP_button1 = new javax.swing.JPanel();
        jB_Apply = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        jP_south = new javax.swing.JPanel();
        jB_OK = new javax.swing.JButton();
        jlBar = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        
        setTitle("ADAMS Authentication");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        
        jP_TOT.setLayout(null);
        
        jP_TOT.setBackground(new java.awt.Color(230, 230, 230));
        jL_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_auth.jpg")));
        jL_icon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_TOT.add(jL_icon);
        jL_icon.setBounds(5, 5, 280, 60);
        
        jL_desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_desc.setText("Insert User and Password");
        jP_TOT.add(jL_desc);
        jL_desc.setBounds(30, 80, 230, 20);
        
        jL_user.setText("User");
        jP_TOT.add(jL_user);
        jL_user.setBounds(30, 110, 200, 20);
        
        jTF_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextF_ActionPerformed(evt);
            }
        });
        
        jP_TOT.add(jTF_login);
        jTF_login.setBounds(30, 130, 230, 20);
        
        jL_pwd.setText("Password");
        jP_TOT.add(jL_pwd);
        jL_pwd.setBounds(30, 160, 170, 20);
        
        jPF_pwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextF_ActionPerformed(evt);
            }
        });
        
        jP_TOT.add(jPF_pwd);
        jPF_pwd.setBounds(30, 180, 200, 20);
        
        jB_changePWD.setBackground(new java.awt.Color(230, 230, 230));
        jB_changePWD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key_s.gif")));
        jB_changePWD.setToolTipText("Change password");
        jB_changePWD.setFocusPainted(false);
        jB_changePWD.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_changePWD.setEnabled(false);
        jB_changePWD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ActionPerformed(evt);
            }
        });
        
        jP_TOT.add(jB_changePWD);
        jB_changePWD.setBounds(230, 180, 30, 20);
        
        jP_button1.setBackground(new java.awt.Color(230, 230, 230));
        jB_Apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        jB_Apply.setToolTipText("Login");
        jB_Apply.setBorderPainted(false);
        jB_Apply.setContentAreaFilled(false);
        jB_Apply.setFocusPainted(false);
        jB_Apply.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_Apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_Apply.setMaximumSize(new java.awt.Dimension(100, 22));
        jB_Apply.setMinimumSize(new java.awt.Dimension(100, 22));
        jB_Apply.setPreferredSize(new java.awt.Dimension(100, 22));
        jB_Apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        jB_Apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        jB_Apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ActionPerformed(evt);
            }
        });
        
        jP_button1.add(jB_Apply);
        
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMaximumSize(new java.awt.Dimension(100, 22));
        jB_close.setMinimumSize(new java.awt.Dimension(100, 22));
        jB_close.setPreferredSize(new java.awt.Dimension(100, 22));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ActionPerformed(evt);
            }
        });
        
        jP_button1.add(jB_close);
        
        jP_TOT.add(jP_button1);
        jP_button1.setBounds(30, 210, 230, 30);
        
        jP_south.setLayout(null);
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204)));
        jB_OK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok.jpg")));
        jB_OK.setToolTipText("Start N.T.M.");
        jB_OK.setBorderPainted(false);
        jB_OK.setContentAreaFilled(false);
        jB_OK.setFocusPainted(false);
        jB_OK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_OK.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_OK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_press.jpg")));
        jB_OK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_over.jpg")));
        jB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_OK);
        jB_OK.setBounds(220, 37, 50, 22);
        
        jlBar.setText("Status Loading");
        jlBar.setToolTipText("");
        jP_south.add(jlBar);
        jlBar.setBounds(10, 10, 170, 20);
        
        jP_south.add(jProgressBar1);
        jProgressBar1.setBounds(10, 40, 200, 15);
        
        jP_TOT.add(jP_south);
        jP_south.setBounds(5, 250, 280, 80);
        
        getContentPane().add(jP_TOT, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
       if(firstToBack) 
        {          
            final javax.swing.JFrame f = this;
            new Thread() 
            { 
                public void run() 
                {
                    try { Thread.sleep(300); } catch(InterruptedException ie) {}  
                    f.toFront();
                }
            }.start();
            
            firstToBack = false;
        }
    }//GEN-LAST:event_formWindowDeactivated

    private void jTextF_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextF_ActionPerformed
        check_Login();
    }//GEN-LAST:event_jTextF_ActionPerformed

    private void jB_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ActionPerformed
        this.setCursor(Cur_wait);
        Object target = evt.getSource();

        if (target == jB_changePWD)
        {
            //System.out.println("changePWD");
            //ChangePWD changePWD = new ChangePWD(this,idUser,str_login);
            //ADAMS_JD_ChangePWD changePWD = new ADAMS_JD_ChangePWD(this,idUser,str_login);
        }
        else if (target == jB_Apply)
        {
            //System.out.println("jB_Apply");
            check_Login();
        }
        else if (target == jB_close)
        {
            //System.out.println("jB_close");
            closeFrame();
        }
        else // button jB_OK
        {            
            t = null;
            TH_EXIT = false;
            t = new Thread(this,"ProgressBar");
            t.start();
        }
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_ActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        this.closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame()
    {
        TH_EXIT = true;
        ADAMS_GlobalParam.STSConn.shutDown_Conf();
        dispose();
    }
 
   
    public void run()
    {               
        setGui_enabled(false);
        this.setCursor(Cur_wait);
        
        int count_progressStep = 5;
        setProgresBar(count_progressStep,"Status Loading: "+count_progressStep+"%");
        count_progressStep = 15;
        
        ADAMS_GlobalParam.connect_Oracle = new ADAMS_OracleConnection();
            setProgresBar(count_progressStep,"Status Loading: "+count_progressStep+"%");
            count_progressStep = 35;
        
        CONN_ORACLE = ADAMS_GlobalParam.connect_Oracle.DBConnect("ORBIX","ORACLE","ops$orbix");
            setProgresBar(count_progressStep,"Status Loading: "+count_progressStep+"%");
            count_progressStep = 100;

        if(CONN_ORACLE == true)
        {
            System.out.println("Connessione Oracle ==> OK");
                      
            ADAMS_JF_Wizard jf_wizard = new ADAMS_JF_Wizard();
            setProgresBar(count_progressStep,"Status Loading: "+count_progressStep+"%");
            closeFrame();
           
        }
        else
        {
            System.out.println("Connessione Oracle ==> failed");

            setProgresBar(0,"");
            ADAMS_GlobalParam.optionPanel(this,"Connection False.", "Critical Error.",1);
        }             
            
        this.setCursor(Cur_default);
        setGui_enabled(true);        
     }
         
    private void setGui_enabled(boolean state)
    {
        jTF_login.setEnabled(state);
        jPF_pwd.setEnabled(state);
        jB_changePWD.setEnabled(false);
        jB_Apply.setEnabled(state);
        jB_close.setEnabled(state);
        jB_OK.setEnabled(state);  
        this.repaint();
    }
     
    private void setGui_enabled(boolean state, String str)
    {	 
        if (state == false)
        {   
            jTF_login.setText("");
            jPF_pwd.setText("");
            jTF_login.requestFocus();
            jL_desc.setText(str);               
            jTF_login.removeKeyListener(event_KEY);
            jPF_pwd.removeKeyListener(event_KEY);
        }
        else
        {    
            jL_desc.setText(str);
            jTF_login.addKeyListener(event_KEY);	
            jPF_pwd.addKeyListener(event_KEY); 
        }
        jB_OK.setEnabled(state);
        jB_changePWD.setEnabled(false);
    }
       
    private void jT_KeyPressed(java.awt.event.KeyEvent evt) 
    {
        setGui_enabled(false,"");
    }
    
    private void check_Login()
    {
        this.setCursor(Cur_wait);
        String str = "Uknow User";

        str_login = jTF_login.getText();
        String str_PWD = new String(jPF_pwd.getPassword()).trim();
        
        /*String NODO     = "ORBIX";
        String TYPE_PWD = "TYPE1";
        String STR_USER     = "adams";*/                
        
        /*	
        STR_PWD = ADAMS_GlobalParam.STSConn.getPswd(NODO,TYPE_PWD,STR_USER);
        System.out.println("STR_PWD="+STR_PWD);
        System.out.println("STR_USER="+STR_USER);	
        if ( (str_login.compareTo("")!=0) && (str_PWD.compareTo("")!=0) )
        {
           
            if ( (str_login.compareTo(STR_USER)==0) && (str_PWD.compareTo(STR_PWD)==0) )
            {
                str = ("Welcome "+(new String(str_login)).trim());
                setGui_enabled(true,str);               
            }
            else
            {
                str = ("Unknown User");
                setGui_enabled(false,str);
            }
        }
        */
        
        str = ("Welcome "+(new String(str_login)).trim());
        setGui_enabled(true,str);   
        
        this.setCursor(Cur_default);
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
    
    private void setProgresBar(int value,String str)		
    {
    	jProgressBar1.setValue(value);
        jlBar.setText(str);
       
        jProgressBar1.repaint();
        jlBar.repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_TOT;
    private javax.swing.JLabel jL_icon;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JLabel jL_user;
    private javax.swing.JTextField jTF_login;
    private javax.swing.JLabel jL_pwd;
    private javax.swing.JPasswordField jPF_pwd;
    private javax.swing.JButton jB_changePWD;
    private javax.swing.JPanel jP_button1;
    private javax.swing.JButton jB_Apply;
    private javax.swing.JButton jB_close;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_OK;
    private javax.swing.JLabel jlBar;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

      
    public Vector strConfig                 = new Vector();	
    public String str_login;
    
    private boolean connOK_STSConfig;
    private boolean connOK_MASTERSERVER;

    /*
    * Thread per la gestione della status bar
    */
    private Thread t = null;    
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private java.awt.event.KeyListener event_KEY;
    private boolean TH_EXIT = false;
    
    private boolean CONN_ORACLE	= false;
    private String STR_USER   	= "adams";
    private String STR_PWD    	= "";
    private String NODO     	= "ORBIX";
    private String TYPE_PWD 	= "TYPE1";
    
    private CORBAConnection CORBA;    
    private boolean firstToBack = true;
}
