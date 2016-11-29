import java.awt.*; 

/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> PROCESS MONITOR client </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (HP Invent) Created on 01/07/2004 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 *
 * Questa  classe viene utilizzata per effettuare l'accesso all'applicativo. 
 * L'interfaccia, mette a disposizione componenti grafici per effettuare l'operazione di logon, viene richiesto all'utente
 * l'inserimento di una user-login e password, la classe tramite chiamata al server richiede se l'operazione di logon
 * effettuata risulta idonea per l'accesso all'applicativo PROCESS MONITOR. 
 * La richiesta di controllo user-login e password viene effettuata tramite il metodo getUserProfile(user-login,password) 
 * della classe SSM_CORBAConnection (dichiarata e istanziate nella classe globale SSM_GlobalParam); se il metodo ritorna con esito positivo, 
 * viene effettuato un cotrollo per rilevare il livello di accesso dell'utente "ADMINISTRATOR" o "GENERIC USER".
 * Se l'utente loggato risulta "ADMINISTRATOR" , oltre alla visualizzazione del Monitor in modalita monitoraggio, l'applicativo consentirï¿½ l'accesso 
 * alle form dedicate alla configurazione, l'utente "GENERIC USER" avrï¿½ accesso solo alla modalita di monitoraggio.
 * Il profilo utente identificato verrï¿½ memorizzato nella classe globale SSM_GlobalParam (USER_PROFILE local_user_profile).
 * Inoltre l'interfaccia SSM_JF_LoginStart mette a disposizione un form per effettuare il cambio della password dell'utente,
 * la classe SSM_D_ChangePWD viene richiamata tramite specifico pulsante "Change password". 
 * La classe implementa l'intefaccia runnable, con la ridefinizione dei metodi start()e run().
 * rilascio IDM
 */
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

//idFrame=0;
//start();  
        
public class SSM_JF_LoginStart extends javax.swing.JFrame implements Runnable {

    public static int GRANT_ALL             = 1;
    public static int GRANT_ADMIN           = 2;
    public static int GRANT_DISABLE         = 0;
    
    public final int PROCESS_PSMONITOR          = 1;
    public final int PROCESS_CFMONITOR          = 2;
    public final int PROCESS_CFLOG              = 3;
    public final int PROCESS_ACQPMANAGE         = 4;
    public final int PROCESS_GARBAGE            = 5;
    public final int PROCESS_ORBIX              = 6;
    public final int PROCESS_ORBIX_MANAGE       = 7;
    
    private boolean flagSUPERUSER		= false;
    private java.awt.event.ItemListener           evento                      = null;

    //private String  processName[]   = {"Process Monitor","Process Manage","Config Log Message","ACQPeb Manage","Garbage Manage","Process Orbix","Process Orbix Manage"};
    //private int     processID[]   = {PROCESS_PSMONITOR,PROCESS_CFMONITOR,PROCESS_CFLOG,PROCESS_ACQPMANAGE,PROCESS_GARBAGE,PROCESS_ORBIX,PROCESS_ORBIX_MANAGE};
    //private int     processGrant[]  = {GRANT_ALL,GRANT_ADMIN,GRANT_ADMIN,GRANT_ADMIN,GRANT_ADMIN,GRANT_ADMIN,GRANT_ADMIN};
    
    private String  processName[];
    private int  processID[];
    private int  processGrant[];
    
    //private int processId[]={0,1,2,3,4,5};
    /** 
     * Costruttore di default della classe.
     */
    public SSM_JF_LoginStart() {
        initComponents();
        
        //jB_start.setCursor(Cur_hand);
        //jB_startConfig.setCursor(Cur_hand);
        //jB_startLog.setCursor(Cur_hand);
        
        jBstart.setCursor(Cur_hand);
        jbClose.setCursor(Cur_hand);
        

        event_KEY = (new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_KeyPressed(evt);
            }
            });
              
        int iRet = check_Login(true); System.out.println("check_Login --> TRUE MANUAL");
        //int iRet=check_Login();
        if (iRet==-1)
            return;
        
        setCenteredFrame(335,280);
        jBstart.setEnabled(false);
        
        evento=(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        
        jComboBox1.addItemListener(evento);
        
        //show();
        this.setVisible(true);

    }
    
    private void warningProblem(String str1,String str2)
    {
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE);

            //warning.showConfirmDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE,JOptionPane.QUESTION_MESSAGE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_Tot = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbClose = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jBstart = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jProgBar = new javax.swing.JProgressBar();

        setTitle("ProcessMonitor: User Authentication");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jP_Tot.setBackground(new java.awt.Color(230, 230, 230));
        jP_Tot.setMaximumSize(new java.awt.Dimension(300, 200));
        jP_Tot.setMinimumSize(new java.awt.Dimension(300, 200));
        jP_Tot.setPreferredSize(new java.awt.Dimension(300, 200));
        jP_Tot.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_auth.jpg"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_Tot.add(jLabel1);
        jLabel1.setBounds(20, 10, 280, 60);

        jbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_close.jpg"))); // NOI18N
        jbClose.setBorderPainted(false);
        jbClose.setContentAreaFilled(false);
        jbClose.setFocusPainted(false);
        jbClose.setPreferredSize(new java.awt.Dimension(100, 22));
        jbClose.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_close_press.jpg"))); // NOI18N
        jbClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_close_over.jpg"))); // NOI18N
        jbClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCloseActionPerformed(evt);
            }
        });
        jP_Tot.add(jbClose);
        jbClose.setBounds(130, 170, 60, 22);

        jComboBox1.setBackground(new java.awt.Color(230, 230, 230));
        jComboBox1.setFocusable(false);
        jP_Tot.add(jComboBox1);
        jComboBox1.setBounds(30, 120, 210, 20);

        jBstart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start.jpg"))); // NOI18N
        jBstart.setBorderPainted(false);
        jBstart.setContentAreaFilled(false);
        jBstart.setFocusPainted(false);
        jBstart.setPreferredSize(new java.awt.Dimension(100, 22));
        jBstart.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start_press.jpg"))); // NOI18N
        jBstart.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start_over.jpg"))); // NOI18N
        jBstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBstartActionPerformed(evt);
            }
        });
        jP_Tot.add(jBstart);
        jBstart.setBounds(240, 120, 60, 22);

        jLabel3.setText(" ");
        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Select Process to execute", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        jP_Tot.add(jLabel3);
        jLabel3.setBounds(15, 85, 290, 80);

        getContentPane().add(jP_Tot, java.awt.BorderLayout.CENTER);

        jProgBar.setBackground(new java.awt.Color(230, 230, 230));
        jProgBar.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        jProgBar.setPreferredSize(new java.awt.Dimension(148, 16));
        getContentPane().add(jProgBar, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // Add your handling code here:
        if(jComboBox1.getSelectedIndex()==0)
        {
            jBstart.setEnabled(false);
        }else
        {
            jBstart.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    
    private void jBstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBstartActionPerformed
        // Add your handling code here:
        String str=(String)jComboBox1.getSelectedItem();
        idFrame=0;
        for(int i=0;i<processName.length;i++)
        {
            //System.out.println(processName[i]+"= "+str);
            if(str.equals(processName[i]))
            {
                idFrame=processID[i];
                break;
            }
        }
        start();
    }//GEN-LAST:event_jBstartActionPerformed

    private void jbCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCloseActionPerformed
        // Add your handling code here:
        CloseFrame();
    }//GEN-LAST:event_jbCloseActionPerformed

    private void jT_KeyPressed(java.awt.event.KeyEvent evt) {
        setGui_enabled(GRANT_DISABLE,"");
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        CloseFrame();
    }//GEN-LAST:event_exitForm

    private void CloseFrame()
    {
        this.dispose();
    }
    
    
    private void setGui_enabled(int state, String str)
    { 
        jComboBox1.removeItemListener(evento);
        jComboBox1.removeAllItems();
        
        if(state==1)
        {
            //jB_start.setEnabled(true);
        }
        else  if(state==2)
        {
            /*jB_start.setEnabled(true);
            jB_startConfig.setEnabled(true);
            jB_startLog.setEnabled(true);
            jbClose.setEnabled(true);*/
        }
        else  if(state==0)
        {
            /*jB_start.setEnabled(false);
            jB_startConfig.setEnabled(false);
            jB_startLog.setEnabled(false);
            jbClose.setEnabled(false);*/
            jComboBox1.addItem("<User NOT allowed>");
            jComboBox1.addItemListener(evento);
            return;
        }
        //System.out.println("STATE="+state);
        jComboBox1.addItem("<Select one>");
        for(int i=0;i<processName.length;i++)
        {
            //System.out.println("state="+state+"    "+processName[i]+"= "+processGrant[i]);
            //if( (processGrant[i]==GRANT_ALL) || (processGrant[i]==state) )
            //{
                jComboBox1.addItem((String)(processName[i]));
            //}
        }
        jComboBox1.addItemListener(evento);    
    }
    
    private int check_Login(boolean bypass)
    {
        if(bypass)
            return 1;
        else
            return -1;
    }

    private int check_Login()
    {
        this.setCursor(Cur_wait);
        str_UserLogon = "Uknow User";
        flagSUPERUSER=false;
        String str_Login = SSM_GlobalParam.utenza;//jT_insertUser.getText();
	String str_PWD = SSM_GlobalParam.token;//new String(jT_insertPassword.getPassword()).trim();	

	
        if ( (str_Login.compareTo("")!=0) && (str_PWD.compareTo("")!=0) )
	{       

            boolean Authentication = SSM_GlobalParam.CORBAConn.LoginUser(str_Login,str_PWD,false);
            //System.out.println("Authentication ==> "+Authentication +" ----- RUOLO ==>"+SSM_GlobalParam.RUOLO);

            if(Authentication == true)
            {
                S_USER sUser=SSM_GlobalParam.CORBAConn.getUserConfiguration(str_Login,SSM_GlobalParam.ID_MOD_PROCESSMONITOR);

                for(int i=0;i<sUser.profileSeq.length;i++)
                {
                    //System.out.println("Profile: "+new String(sUser.profileSeq[i].profile).trim());
                    //System.out.println("nFunzioni: "+sUser.profileSeq[i].functionSeq.length);

                    processName=new String[sUser.profileSeq[i].functionSeq.length];
                    processID=new int[sUser.profileSeq[i].functionSeq.length];


                    for(int j=0;j<sUser.profileSeq[i].functionSeq.length;j++)
                    {
                        //System.out.println("Function: "+new String(sUser.profileSeq[i].functionSeq[j].nameFunction).trim()+" ID: "+sUser.profileSeq[i].functionSeq[j].idFunction);

                        processName[j]=new String(sUser.profileSeq[i].functionSeq[j].nameFunction).trim();
                        processID[j]=sUser.profileSeq[i].functionSeq[j].idFunction;
                    }
                }

                str_UserLogon = ("Welcome "+str_Login);

		flagSUPERUSER=true;
                setGui_enabled(GRANT_ADMIN,str_UserLogon);

                /*if(SSM_GlobalParam.RUOLO==2)
                {
                    flagSUPERUSER=true;
                    System.out.println("GRANT_ADMIN");
                    setGui_enabled(GRANT_ADMIN,str_UserLogon);
                }else
                {
                    System.out.println("GRANT_ALL");
                    setGui_enabled(GRANT_ALL,str_UserLogon);
                }*/
		this.setCursor(Cur_default);
                return 1;
            }else
            {
                warningProblem("User not allowed. ","Error Message");
		this.setCursor(Cur_default);
                return -1;
            }

            /*local_user_profile = null;
            local_user_profile = SSM_GlobalParam.CORBAConn.getUserProfile(str_Login,str_PWD);
            if(local_user_profile != null)
            {
                for(int i=0;i<local_user_profile.ModuleEnalbled.length;i++)
                {
                    //System.out.println("id="+local_user_profile.ModuleEnalbled[i].ID_Module);
                    //System.out.println("level="+local_user_profile.ModuleEnalbled[i].levelUser);
                    if(SSM_GlobalParam.ID_MOD_PROCESSMONITOR==local_user_profile.ModuleEnalbled[i].ID_Module)
                        if(local_user_profile.ModuleEnalbled[i].levelUser==1)
                        {
                            flagSUPERUSER=true;
                            //System.out.println("Super User");
                            break;
                        }

                }
                //System.out.println("User_ID:"		+local_user_profile.ID_User);
                System.out.println("Login:"		+(new String(local_user_profile.login)).trim());
                //System.out.println("Nome:"		+(new String(local_user_profile.nomeUtente)).trim());
                //System.out.println("Password:"	+(new String(local_user_profile.password)).trim());
                //System.out.println("Descrizione:"	+(new String(local_user_profile.descUser)).trim());
                System.out.println("Level User:"        +(local_user_profile.levelUser));

                str_UserLogon = ("Welcome "+(new String(local_user_profile.nomeUtente)).trim());

                if(local_user_profile.levelUser==0)
                {
                    setGui_enabled(GRANT_ADMIN,str_UserLogon); //superutente
                }else
                {
                    if(flagSUPERUSER==true)
                        setGui_enabled(GRANT_ADMIN,str_UserLogon); //superutente
                    else
                        setGui_enabled(GRANT_ALL,str_UserLogon);
                }
                this.setCursor(Cur_default);
                return 1;

            }else
            {
                warningProblem("User not allowed. ","Error Message");
                return -1;
            }*/
	}
        this.setCursor(Cur_default);
        setGui_enabled(GRANT_DISABLE,str_UserLogon);
        return 1;
    }


    private void start()
    {
        th = null;
        th = new Thread(this,"startProcessMonitor");
        th.start();
    }
   /**
    * In questo metodo viene definito il codice del Thread.
    * Nel metodo viene effettuata la chiamata al metodo openConnection_psMonitorMasterServer() per la connessione 
    * al "psMonitorMasterServer" ed inoltre viene invocato il metodo Launch_PsMonitorMasterServer() per richiedere le informazioni
    * relative alle centrali, necessarie all'applicativo,i metodi vengono invocati mediante l'oggetto di tipo SSM_CORBAConnection 
    * dichiarato nella classe globale SSM_GlobalParam.
    * Il metodo inoltre si prende carico di istanziare l'0ggetto SSM_JF_MonitoringSetup (start effettiva dell'interfaccia dell'applicativo).
    *
    *@see SSM_CORBAConnection
    *@see SSM_GlobalParam
    *@see SSM_JF_MonitoringSetup
    */
    public void run()
    {
        this.setCursor(Cur_wait);
        //setGui_enabled(0,str_UserLogon);

        jProgBar.setValue(10);
        
        boolean stato_psMonitor = SSM_GlobalParam.CORBAConn.openConnection_psMonitorMasterServer();
        System.out.println("Connection Server psMonitorMasterServer   ==>  "+stato_psMonitor);
        
        jProgBar.setValue(20);
        
        if(stato_psMonitor)
        {
            jProgBar.setValue(50);
            
            DATA_CENTRALI[] ALLcentrali = null;
            ALLcentrali = SSM_GlobalParam.CORBAConn.Launch_PsMonitorMasterServer();
           

            jProgBar.setValue(70);
            
            if(ALLcentrali == null)
            {
                jProgBar.setValue(0);
                setGui_enabled(GRANT_DISABLE,"Insert User and Password");
                this.toFront();
                this.setCursor(Cur_default);
                return;
            }
            else
            {
                //**************************** DEBUG CENTRALI
                //System.out.println("ALLcentrali.length "+ALLcentrali.length);

                //for(int i=0; i<ALLcentrali.length; i++)
                //{
                //    System.out.print("IdCentrale ==> "+ALLcentrali[i].IdCentrale);
                //    System.out.println(" - Descrizione ==> "+new String(ALLcentrali[i].Descrizione).trim());
                //}
                //**************************** END DEBUG CENTRALI

                jProgBar.setValue(90);
                

                switch(idFrame)
                {
                    
                    case 1:
                    {
                        JF_PMSetup = new SSM_JF_MonitoringSetup(ALLcentrali,SSM_GlobalParam.NO_ORBIX);
                        JF_PMSetup.setLocation(100,100);
                        //JF_PMSetup.show();
                        JF_PMSetup.setVisible(true);
                    }break;
                    
                    case 2:
                    {
                        JF_PMConfig = new SSM_JF_ConfigSetup(ALLcentrali);
                        //JF_PMConfig.show();
                        JF_PMConfig.setVisible(true);
                    }break;
                    
                    case 3:
                    {
                         JF_PMLog = new SSM_JF_ConfigLog(ALLcentrali);
                         //JF_PMLog.show();
                         JF_PMLog.setVisible(true);
                    }break;
                    
                    case 4:
                    {
                        JF_ACQPebMgr = new SSM_JF_ACQPebMgr(ALLcentrali);
                        //JF_ACQPebMgr.show();
                        JF_ACQPebMgr.setVisible(true);
                    }break;
                    
                    case 5:
                    {
                        JF_GarbageMonitor = new SSM_JF_GarbageMonitor(ALLcentrali);
                        //JF_GarbageMonitor.show();
                        JF_GarbageMonitor.setVisible(true);
                    }break;
                    
                    case 6:
                    {
                        JF_PMSetup = new SSM_JF_MonitoringSetup(ALLcentrali,SSM_GlobalParam.ORBIX);
                        JF_PMSetup.setLocation(100,100);
                        //JF_PMSetup.show();
                        JF_PMSetup.setVisible(true);
                    }break;
                    
                    case 7:
                    {
                        JF_PMConfigOrbix = new SSM_JF_ManageOrbix(ALLcentrali);
                        //JF_PMConfigOrbix.show();
                        JF_PMConfigOrbix.setVisible(true);
                    }break;
                    
                    default:
                    {
                         System.out.println("Interfaccia mancante");
                    }break;
                }
                
                
                
                
                
                jProgBar.setValue(100); 
                
                //CloseFrame();   
            }
        }
        else
        {
            jProgBar.setValue(0);
            setGui_enabled(GRANT_DISABLE,"Insert User and Password");
        }        
        this.setCursor(Cur_default);
        
   
        String str_UserLogon = ("Welcome "+SSM_GlobalParam.utenza);

        if(SSM_GlobalParam.RUOLO==2)
        {
                    setGui_enabled(GRANT_ADMIN,str_UserLogon); //superutente
        }else
        {
        	    setGui_enabled(GRANT_ALL,str_UserLogon);
        }
        jProgBar.setValue(0);
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBstart;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jP_Tot;
    private javax.swing.JProgressBar jProgBar;
    private javax.swing.JButton jbClose;
    // End of variables declaration//GEN-END:variables

    private Cursor Cur_default  = new Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(java.awt.Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(java.awt.Cursor.HAND_CURSOR);
    
    private java.awt.event.KeyListener event_KEY;
    private SSM_JF_MonitoringSetup JF_PMSetup;
    private SSM_JF_ConfigSetup JF_PMConfig;
    private SSM_JF_ConfigLog JF_PMLog;
    private SSM_JF_ACQPebMgr JF_ACQPebMgr;
    private SSM_JF_GarbageMonitor JF_GarbageMonitor;
    private SSM_JF_ManageOrbix JF_PMConfigOrbix;
    
    //private USER_PROFILE local_user_profile = null;
    private String str_UserLogon            = null;
    private Thread th = null;
    private int idFrame = 0;
    private boolean firstToBack = true;
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
}

