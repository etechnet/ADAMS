/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_JF_Global </b> </p>
 *
 * Questa classe che estende una JFrame, contiene l'intera interfaccia dell'applicativo, strutturata con una JTable.
 * La classe Ã¨ preposta all'istanziamento e all'inizializzazione di tutti gli oggetti costituenti l'interfaccia principale dell'applicatitivo:
 * <pre>
 *      - conf_JP_AccessManager     (classe specifica per la gestione Profili e Utenti)
 *      - conf_JP_ImageManager      (classe specifica per la gestione di controllo delle specifiche dell'applicativo esterno IMAGE)
 *      - conf_JP_MonitorManager    (classe specifica per la gestione di controllo delle specifiche dell'applicativo esterno MONITOR)
 *      - conf_JP_NtmManager        (classe specifica per la gestione di controllo delle specifiche dell'applicativo esterno NTM)
 * </pre>
 *
 * L'interfaccia gestisce dinamicamente l'abilitazione/disabilitazione dei moduli che la compongono mediante un parametro specifico (RUOLO) che identifica i grant
 * dell'utente autenticato all'utilizzo dell'applicativo stesso.
 * 
 *@see STS_GlobalParam
 *@see conf_JP_AccessManager
 *@see conf_JP_ImageManager
 *@see conf_JP_MonitorManager
 *@see conf_JP_NtmManager
 */
public class conf_JF_Global extends javax.swing.JFrame {

    /** Creates new form conf_JF_Global */
    public conf_JF_Global() {
        initComponents();
           
        switch(conf_GlobalParam.RUOLO)
        {
            case 777:
            {
                jp_AccessManager = new conf_JP_AccessManager(this);
                jP_Tab_AccessManager.add(jp_AccessManager, java.awt.BorderLayout.CENTER);

                /*jp_ImageManager = new conf_JP_ImageManager(this,true,true);
                jP_Tab_ImageManager.add(jp_ImageManager, java.awt.BorderLayout.CENTER);

                jp_MonitorManager = new conf_JP_MonitorManager(this,true);
                jP_Tab_MonitorManager.add(jp_MonitorManager, java.awt.BorderLayout.CENTER);

                jp_NtmManager = new conf_JP_NtmManager(this);   
                jP_Tab_NtmManager.add(jp_NtmManager, java.awt.BorderLayout.CENTER);*/
                
                break;
            }           
            case 1:
            {
                jp_AccessManager = new conf_JP_AccessManager(this);
                jP_Tab_AccessManager.add(jp_AccessManager, java.awt.BorderLayout.CENTER);
                
                /*jp_NtmManager = new conf_JP_NtmManager(this);   
                jP_Tab_NtmManager.add(jp_NtmManager, java.awt.BorderLayout.CENTER);
                
                jTabbedPane1.remove(jP_Tab_ImageManager);
                jTabbedPane1.remove(jP_Tab_MonitorManager);*/
                break;
            }           
            case 2:
            {
                jTabbedPane1.remove(jP_Tab_AccessManager);
                
                /*jp_ImageManager = new conf_JP_ImageManager(this,true,true);
                jP_Tab_ImageManager.add(jp_ImageManager, java.awt.BorderLayout.CENTER);

                jp_MonitorManager = new conf_JP_MonitorManager(this,true);
                jP_Tab_MonitorManager.add(jp_MonitorManager, java.awt.BorderLayout.CENTER);

                jp_NtmManager = new conf_JP_NtmManager(this);   
                jP_Tab_NtmManager.add(jp_NtmManager, java.awt.BorderLayout.CENTER);*/
                break;
            }
            case 3:
            {
                jTabbedPane1.setEnabledAt(0,false);
                jTabbedPane1.setEnabledAt(1,false);
                jTabbedPane1.setEnabledAt(2,false);
                jTabbedPane1.setEnabledAt(3,false);
               
                for(int i=0; i<conf_GlobalParam.V_enabledClass.size(); i++)
                {                
                    int enabledClass = ((Integer)conf_GlobalParam.V_enabledClass.elementAt(i)).intValue();
                    
                    if(enabledClass == conf_GlobalParam.ID_MOD_IMAGE)
                    {
                        //System.out.println("** ID_MOD_IMAGE ** ");
                        //jp_ImageManager = new conf_JP_ImageManager(this,false,true);
                        //jP_Tab_ImageManager.add(jp_ImageManager, java.awt.BorderLayout.CENTER);
                        jTabbedPane1.setEnabledAt(1,true);
                    }
                    else if(enabledClass == conf_GlobalParam.ID_MOD_MONITOR)
                    {
                        //System.out.println("** ID_MOD_MONITOR ** ");
                        //jp_MonitorManager = new conf_JP_MonitorManager(this,false);
                        //jP_Tab_MonitorManager.add(jp_MonitorManager, java.awt.BorderLayout.CENTER);
                        jTabbedPane1.setEnabledAt(2,true);

                    }
                    /*else if(enabledClass == conf_GlobalParam.ID_MOD_NTM)
                    {
                        System.out.println("** ID_MOD_NTM ** ");
                        jp_NtmManager = new conf_JP_NtmManager(this);   
                        jP_Tab_NtmManager.add(jp_NtmManager, java.awt.BorderLayout.CENTER);
                        jTabbedPane1.setEnabledAt(3,true);
                    }*/
                }                
                jTabbedPane1.remove(jP_Tab_AccessManager);
                jTabbedPane1.remove(jP_Tab_NtmManager);
                
                break;
            }       
            default:
            {
                jTabbedPane1.removeAll();
            }
            
        }
        
        String str_ruolo ="";
        if(conf_GlobalParam.config_lib != null)
        {
            for(int i=0; i<conf_GlobalParam.config_lib.roleSeq.length; i++)
            {
                if(conf_GlobalParam.RUOLO == conf_GlobalParam.config_lib.roleSeq[i].idRole)
                {
                    str_ruolo = new String(conf_GlobalParam.config_lib.roleSeq[i].descRole).trim();
                    break;
                }
            }
        }
        this.setTitle(conf_GlobalParam.utenza+" with Role --> "+ str_ruolo +" < LOG INTERCETTED IP HOST "+conf_GlobalParam.IP_CLIENT+" >");
        
        try
        {
        	pack();
        }
        catch (Exception e)
        {}
        this.setCenteredFrame(960,780);    
        //show();
	setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_nord = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jP_Tab_AccessManager = new javax.swing.JPanel();
        jP_Tab_ImageManager = new javax.swing.JPanel();
        jP_Tab_MonitorManager = new javax.swing.JPanel();
        jP_Tab_NtmManager = new javax.swing.JPanel();
        jP_south = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("c");
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        
        jL_nord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_nord.setText("PROFILE MANAGEMENT");
        getContentPane().add(jL_nord, java.awt.BorderLayout.NORTH);
        
        jTabbedPane1.setBackground(new java.awt.Color(230, 230, 230));
        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_Tab_AccessManager.setLayout(new java.awt.BorderLayout());
        
        jP_Tab_AccessManager.setBackground(new java.awt.Color(230, 230, 230));
        jP_Tab_AccessManager.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.addTab("Access Management", jP_Tab_AccessManager);
        
        jP_Tab_ImageManager.setLayout(new java.awt.BorderLayout());
        
        jP_Tab_ImageManager.setBackground(new java.awt.Color(230, 230, 230));
        jP_Tab_ImageManager.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.addTab("Image Management", jP_Tab_ImageManager);
        
        jP_Tab_MonitorManager.setLayout(new java.awt.BorderLayout());
        
        jP_Tab_MonitorManager.setBackground(new java.awt.Color(230, 230, 230));
        jP_Tab_MonitorManager.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.addTab("Monitor Management", jP_Tab_MonitorManager);
        
        jP_Tab_NtmManager.setLayout(new java.awt.BorderLayout());
        
        jP_Tab_NtmManager.setBackground(new java.awt.Color(230, 230, 230));
        jP_Tab_NtmManager.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.addTab("NTM Management", jP_Tab_NtmManager);
        
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 2));
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setPreferredSize(new java.awt.Dimension(10, 30));
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/close.gif")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMinimumSize(new java.awt.Dimension(65, 30));
        jB_close.setPreferredSize(new java.awt.Dimension(65, 30));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/close_press.gif")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/close_over.gif")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_close);
        
        getContentPane().add(jP_south, java.awt.BorderLayout.SOUTH);
        
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

    private void closeFrame()
    {
        this.setVisible(false);
        this.dispose();
    }
    
    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        closeFrame();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_nord;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jP_Tab_AccessManager;
    private javax.swing.JPanel jP_Tab_ImageManager;
    private javax.swing.JPanel jP_Tab_MonitorManager;
    private javax.swing.JPanel jP_Tab_NtmManager;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables

    private conf_JP_AccessManager   jp_AccessManager    = null;
    //private conf_JP_ImageManager    jp_ImageManager     = null;
    //private conf_JP_MonitorManager  jp_MonitorManager   = null;
    //private conf_JP_NtmManager      jp_NtmManager       = null;
    
    private boolean firstToBack = true;
}
