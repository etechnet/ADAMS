/**
 * <p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *
 * Questa Ã¨ la classe principale e di presentazione per ADAMS.
 * La classe viene richiamata dal file HTML (jmatrix.html) dal quale preleva i parametri di configurazione
 * necessari alla coonnessione tramite lo starto CORBA al master Server.
 * Inoltre provvede ad inizializzare ed istanziare gli oggetti principali di ADAMS.
 * La classe implementa Runnable per la visualizzazione grafica dello stato di avanzamento 
 * della costruzione grafica dell'interfaccia
 * </font></font></p></pre>
 * <p align="center">&nbsp;</p>
 * @see ADAMS_JF_Frame   
 */
import javax.swing.*;
import java.awt.Image;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;

public class ADAMSConf extends javax.swing.JApplet{

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe atto alla costruzione e posizionamento del layout grafico.
     * </font></font></p></pre>  
     */
    private String IP_CLIENT;
    private String HOSTNAME_CLIENT;
    private Logger logger;
    
    
    public ADAMSConf() {
        initComponents();
        jLabel2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD + java.awt.Font.ITALIC , 14));               
    }
    
    public void init()
    {
        machine      = this.getParameter("machine_name");
        port         = this.getParameter("locator_port"); 
        oracle_SID   = this.getParameter("oracle_SID");
        oracle_port  = this.getParameter("oracle_port");
        local_applet = this;
        pathName     = this.getParameter("path_name");
        
        System.out.println("Prova");  
        System.out.println("Prova");   
        System.out.println("Prova");     
        System.out.println("Prova");   
                                   
        //*************

        this.HOSTNAME_CLIENT    = get_HostName();
        this.IP_CLIENT          = get_HostAddress();

        if(this.IP_CLIENT == null)
        {        
            warningProblem("Access Denied: IP Address not defined.","Accesso Denied.");
            return;
        }
             
	logger = LoggerFactory.getLogger(ADAMSConf.class.getName());
	
	logger.info("Machine Name: "+this.HOSTNAME_CLIENT);
	logger.info("Machine IP Address: " +this.IP_CLIENT);

        
        //*************
            
        ADAMS_GlobalParam.IP_CLIENT = this.IP_CLIENT;
        ADAMS_GlobalParam.HOSTNAME_CLIENT = this.HOSTNAME_CLIENT;
        
        
        
        ADAMS_JF_LoginStart adamsFrameLogin = new ADAMS_JF_LoginStart(this);
        
        conf_GlobalParam.MACHINE_NAME   = machine;
        conf_GlobalParam.LOCATOR_PORT   = port;
        conf_GlobalParam.oracle_SID     = oracle_SID;
        conf_GlobalParam.oracle_port    = oracle_port;
        conf_GlobalParam.FLAG_BUILD_JOB = false;    
        conf_GlobalParam.token =  "Luca";
        conf_GlobalParam.utenza = "raffo";
        conf_GlobalParam.idm =    "no";
        conf_GlobalParam.RUOLO = 777;
        
        conf_GlobalParam.CORBAConn = new conf_CORBAConnection(conf_GlobalParam.MACHINE_NAME,conf_GlobalParam.LOCATOR_PORT,this.IP_CLIENT,this.HOSTNAME_CLIENT);
        conf_GlobalParam.CORBAConn.setJApplet(this);
        conf_GlobalParam.rifApplet = this;
        
       /* boolean statoConnConf = false;
        // Connessione al Server Configurazione
        try
        {
            statoConnConf = conf_GlobalParam.CORBAConn.openConnectionASPServer();
            System.out.println("Connection Config Server   ==>  "+statoConnConf);
        }
        catch (Exception e)
        {
            conf_GlobalParam.optionPanel("Connection Server failure.", "Errore",JOptionPane.ERROR_MESSAGE);
        }
        */
        conf_GlobalParam.V_enabledClass = new java.util.Vector();
        //if(statoConnConf)
        //{        
            boolean Authentication = true;//conf_GlobalParam.CORBAConn.LoginUser(conf_GlobalParam.utenza,conf_GlobalParam.token,false);
            System.out.println("Authentication ==> "+Authentication +" ----- RUOLO ==>"+conf_GlobalParam.RUOLO);
            
            if( (Authentication) && ( ((conf_GlobalParam.RUOLO > 0) && (conf_GlobalParam.RUOLO <= 3)) || (conf_GlobalParam.RUOLO == 777)) )
            {
                S_USER sUser = conf_GlobalParam.CORBAConn.getUserConfiguration(conf_GlobalParam.utenza,-1);
                // TUTTI I PROFILI dell'utente Connesso
		System.out.println("  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
		System.out.println("UTENTE CONNESSO ----------- >>> "+conf_GlobalParam.utenza);
			
		for(int i=0;i<sUser.profileSeq.length;i++)
		{ 
		    System.out.println();
		    System.out.println("Class:     "+sUser.profileSeq[i].idClass);
		    System.out.println("Profile:   "+new String(sUser.profileSeq[i].profile).trim());
		    System.out.println("nFunzioni: "+sUser.profileSeq[i].functionSeq.length);

		    for(int j=0;j<sUser.profileSeq[i].functionSeq.length;j++)
		    {
			System.out.println("Function: "+new String(sUser.profileSeq[i].functionSeq[j].nameFunction).trim()+" ID: "+sUser.profileSeq[i].functionSeq[j].idFunction);
		    }
		}
		System.out.println("  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
		// PROFILI dell'utente Connesso
        
                for(int i=0;i<sUser.profileSeq.length;i++)
                {      
                    conf_GlobalParam.V_enabledClass.addElement(new Integer(sUser.profileSeq[i].idClass));
                    
                    if( conf_GlobalParam.ID_MOD_IMAGE == sUser.profileSeq[i].idClass )
                        conf_GlobalParam.IMAGE_USER_PROFILE_LOGON = new String(sUser.profileSeq[i].profile).trim();
                    
                    if( conf_GlobalParam.ID_MOD_MONITOR == sUser.profileSeq[i].idClass )
                        conf_GlobalParam.MONITOR_USER_PROFILE_LOGON = new String(sUser.profileSeq[i].profile).trim();
                    
                    if( conf_GlobalParam.ID_MOD_STSCONFIG == sUser.profileSeq[i].idClass )
                        conf_GlobalParam.COMMON_USER_PROFILE_LOGON = new String(sUser.profileSeq[i].profile).trim();
                }
                
                conf_JF_Global jF_Global = new conf_JF_Global();
            }
            else
            {
                conf_GlobalParam.optionPanel("User not allowed. ","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        /*}
        else
        {
            conf_GlobalParam.optionPanel("Connection Server failure.", "Errore",JOptionPane.ERROR_MESSAGE);
        }*/
        
       
        
        
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Overloading del metodo destroy() della JApplet preposto alla corretta chiusra dell'applicativo NTM.
     * </font></font></p></pre>  
     */
    public void destroy()
    {
        logger.info("Exit ADAMS Configurator");
        ADAMS_GlobalParam.clearLockTable_onExit();        
        ADAMS_GlobalParam.connect_Oracle.Close();        
    }
    
    
     private String get_HostName()
    {
        try
        {
            java.net.InetAddress machine = java.net.InetAddress.getLocalHost();
            System.out.println("Machine Name : " + machine.getHostName());            
            return machine.getHostName();
        }
        catch(Exception e)
        {
            System.out.println(" get_HostName --> Exception " +e);
            logger.error(" get_HostName --> Exception " +e);
            return "N/A";
        }   
    }
    
    private String get_HostAddress()
    {            
        String str_hostAddress = null;
        
        try
        {
            java.util.Enumeration ifEnum = NetworkInterface.getNetworkInterfaces();
            java.net.InetAddress machine = java.net.InetAddress.getLocalHost();
            
           //System.out.println("---- ---- ----\n");

            if(ifEnum != null)
            {
                //System.out.println("*** Local Interfaces ETH ***\n");
                while(ifEnum.hasMoreElements())
                {
                    NetworkInterface localIf = (NetworkInterface)(ifEnum.nextElement());
                    //System.out.println("Interface Name : " +localIf.getName());
                    if(localIf.getName().equals("eth0"))
                    {
                        //get the addresses of this interface
                        Enumeration ifAddrEnum = localIf.getInetAddresses();
                        //System.out.print("IP Addresses : ");

                        while(ifAddrEnum.hasMoreElements())
                        {
                           InetAddress ifAddr = (InetAddress)(ifAddrEnum.nextElement());

                           //System.out.print(ifAddr.toString() + " ");                               
                           str_hostAddress = ifAddr.toString();
                        }
                    }
                    //System.out.println("\n");
                }
                //System.out.println("------------------------------------------\n");
            }
            else
            {
                //if the enumeration is null, mean no network interfaceswere found
                //System.out.println("No Local network interfaces were found");                    
                str_hostAddress = null;
            }                   
        }
        catch(Exception e)
        {
            str_hostAddress = null;
        }

        try
        {
            java.net.InetAddress machine2 = java.net.InetAddress.getLocalHost();
            
            // nel caso ETH fallisca 
            if(str_hostAddress == null)
                str_hostAddress = machine2.getHostAddress();
            else if(str_hostAddress.length() == 0)
                str_hostAddress = machine2.getHostAddress();
            else
                str_hostAddress = str_hostAddress.replaceAll("/",""); // nel caso Eth ok elimino il carattere "/"
            
            //System.out.println("--> Machine IP Address [getHostAddress]: " +machine2.getHostAddress()); //***** per le macchine linux non ïnsufficente ****
        }
        catch(Exception e)
        {
            str_hostAddress = null;
        }
        
        if(str_hostAddress.length() == 0)
            str_hostAddress = null;
        
        //System.out.println(" --> Machine IP Address: "+str_hostAddress);
        return str_hostAddress;
    }
    
    private void warningProblem(String str1,String str2)
    {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is 
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));
        
        setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.gray, 2, true));
        jLabel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_sparkle_ok.png")));
        jPanel1.add(jLabel1);
        
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADAMS Configurator");
        jPanel1.add(jLabel2);
        
        getContentPane().add(jPanel1);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    public static String machine                        = "";
    public static String port                           = "";
    public static String oracle_SID                     = "";
    public static String oracle_port                    = "";
    public static String pathName                       = "";
    public static javax.swing.JApplet local_applet      = null;
    
}
