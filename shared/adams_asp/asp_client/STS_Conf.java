import javax.swing.*;
import java.awt.Image;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;

import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: STS_Conf </b> </p>
 *
 * Questa classe Ã¨ l'Applet di partenza richiamata dal file html "STS_Conf.html";
 * STS_Conf si prende carico di istanziare quelle classi e variabili dichiarate 
 * pubbliche e statiche, fondamentali all'applicativo, inoltre istanzia quelle classi per stabilire 
 * la connessione al Server remoto tramite metodi CORBA.  
 */
public class STS_Conf extends javax.swing.JApplet 
{
    /**
     * Costruttore di default della classe.	
     */
    public STS_Conf() 
    {
        initComponents();
    }
    
    /**
     * Il metodo init() viene richiamato dal sistema di esecuzione del browser quando l'applet viene caricato,
     * ed esegue le procedure di inizializzazione necessarie prima che cominci l'esecuzione della parte
     * principale dell'applet.
     * Inoltre si prende carico di chiamare i metodi per le connessioni ai Server Corba, verificando l'autenticazione di accesso.
     *
     * @see conf_JF_Global
     */
    public void init()
    {             
        conf_GlobalParam.MACHINE_NAME   = this.getParameter("machine_name");
        conf_GlobalParam.LOCATOR_PORT   = this.getParameter("locator_port");
        conf_GlobalParam.oracle_SID     = this.getParameter("oracle_SID");
        conf_GlobalParam.oracle_port    = this.getParameter("oracle_port");
       
        try
        {        
            //System.out.println("--- > getParameter(build_job) "+this.getParameter("build_job"));            
            if ( ((this.getParameter("build_job")).compareToIgnoreCase("true")) == 0)
                conf_GlobalParam.FLAG_BUILD_JOB = true;
            else
                conf_GlobalParam.FLAG_BUILD_JOB = false;    
        }
        catch(Exception e ){}
        
        
        try
        {
            conf_GlobalParam.ID_LAYOUT_ROUTINGALARMS = new Integer(this.getParameter("ID_ROUTING")).intValue();
        }
        catch(Exception e)
        {
            System.out.println("err --- this.getParameter(ID_ROUTING)");
        }
        
        //System.out.println("machine_name = "+conf_GlobalParam.MACHINE_NAME);
        //System.out.println("locator_port = "+conf_GlobalParam.LOCATOR_PORT);
                  
        try
        {
            conf_GlobalParam.token =  this.getParameter("token");
            conf_GlobalParam.utenza = this.getParameter("utenza");
            conf_GlobalParam.idm =    this.getParameter("idm");
            
            //System.out.println("token ="+conf_GlobalParam.token);
            //System.out.println("utenza ="+conf_GlobalParam.utenza);
            //System.out.println("idm ="+conf_GlobalParam.idm);
            
        }catch(Exception e )
        {
            System.out.println("Errore nei paramteri passati da IDM Error Message");
        }
    
                
        //*************

        this.HOSTNAME_CLIENT    = get_HostName();
        this.IP_CLIENT          = get_HostAddress();

        if(this.IP_CLIENT == null)
        {        
            warningProblem("Access Denied: IP Address not defined.","Accesso Denied.");
            return;
        }
                
        System.out.println();
        System.out.println("->-> Machine Name : " +this.HOSTNAME_CLIENT);
        System.out.println("->-> Machine IP Address: " +this.IP_CLIENT);    
        System.out.println();
        
        //*************
        
               
        conf_GlobalParam.IP_CLIENT = IP_CLIENT;
        conf_GlobalParam.HOSTNAME_CLIENT = HOSTNAME_CLIENT;
        conf_GlobalParam.CORBAConn = new conf_CORBAConnection(conf_GlobalParam.MACHINE_NAME,conf_GlobalParam.LOCATOR_PORT,this.IP_CLIENT,this.HOSTNAME_CLIENT);
        conf_GlobalParam.CORBAConn.setJApplet(this);
        conf_GlobalParam.rifApplet = this;
        
        boolean statoConnConf = false;
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
        
        conf_GlobalParam.V_enabledClass = new java.util.Vector();
        if(statoConnConf)
        {        
            boolean Authentication = conf_GlobalParam.CORBAConn.LoginUser(conf_GlobalParam.utenza,conf_GlobalParam.token,false);
            //System.out.println("Authentication ==> "+Authentication +" ----- RUOLO ==>"+conf_GlobalParam.RUOLO);
            
            if( (Authentication) && ( ((conf_GlobalParam.RUOLO > 0) && (conf_GlobalParam.RUOLO <= 3)) || (conf_GlobalParam.RUOLO == 777)) )
            {
                S_USER sUser = conf_GlobalParam.CORBAConn.getUserConfiguration(conf_GlobalParam.utenza,-1);
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
        }
        else
        {
            conf_GlobalParam.optionPanel("Connection Server failure.", "Errore",JOptionPane.ERROR_MESSAGE);
        }
        
       
        
        // TUTTI I PROFILI dell'utente Connesso
        /*System.out.println("  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
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
        // PROFILI dell'utente Connesso*/
    }
    
   /* public void destroy()
    {        
    }*/
    
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        
        jLabel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_sparkle_ok.png")));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);
        
        jLabel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 10));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("STS GLOBAL CONFIGURATOR");
        jLabel2.setMinimumSize(new java.awt.Dimension(180, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(180, 30));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private String get_HostName()
    {
        try
        {
            java.net.InetAddress machine = java.net.InetAddress.getLocalHost();
            //System.out.println("Machine Name : " + machine.getHostName());            
            return machine.getHostName();
        }
        catch(Exception e)
        {
            System.out.println(" get_HostName --> Exception " +e);
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
            
            //System.out.println("--> Machine IP Address [getHostAddress]: " +machine2.getHostAddress()); //***** per le macchine linux non Ã¯nsufficente ****
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
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
    private String IP_CLIENT;
    private String HOSTNAME_CLIENT;
}
 
