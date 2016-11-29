
import javax.swing.*;
import java.util.*;
import java.net.*;

/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Luca Beltrame <luca.beltrame@e-tech.net>                                                                                                           
#                                                                                                                                                 
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory                                                                    
#                                                                                                                                                 
#  HISTORY:                                                                                                                                       
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

public class SystemServiceManager extends javax.swing.JApplet {

    //private boolean TEST_localhost = true;
    
    
    private String IP_CLIENT;
    private String HOSTNAME_CLIENT;
    
    /**
     * Costruttore di default della classe.	
     */
    public SystemServiceManager() {
        //initComponents();
        
    }
 
    /**
     * Il metodo init() viene richiamato dal sistema di esecuzione del browser quando l'applet viene caricato,
     * ed esegue le procedure di inizializzazione necessarie prima che cominci l'esecuzione della parte
     * principale dell'applet.
     * Inoltre si prende carico di chiamre i metodi per le connessioni ai Server e istanziare la classe SSM_JF_LoginStart
     * con la login form di accesso per l'applicativo.	
     *@see SSM_JF_LoginStart
     *@see SSM_GlobalParam
     *@see SSM_CORBAConnection
     */
    public void init()
    {
        // Leggo i parametri dal file SystemServiceManager.html
        SSM_GlobalParam.machine_name = this.getParameter("machine_name");
        SSM_GlobalParam.locator_port = this.getParameter("locator_port");
        //System.out.println("machine_name : "+ SSM_GlobalParam.machine_name);
        //System.out.println("locator_port : "+ SSM_GlobalParam.locator_port);
        
        
        this.HOSTNAME_CLIENT    = get_HostName();
        this.IP_CLIENT          = get_HostAddress();
                
        
        if(this.IP_CLIENT == null)
        {        
            warningProblem("Access Denied: IP Address not defined.","Accesso Denied.");
            return;
        }
         
        System.out.println();
        System.out.println("Machine Name : " +this.HOSTNAME_CLIENT);
        System.out.println("Machine IP Address: " +this.IP_CLIENT);    
        System.out.println();
        
        try
        {
            SSM_GlobalParam.token=this.getParameter("token");
            SSM_GlobalParam.utenza=this.getParameter("utenza");
            SSM_GlobalParam.idm=this.getParameter("idm");
            //System.out.println("token="+SSM_GlobalParam.token);
            System.out.println("utenza="+SSM_GlobalParam.utenza);
            System.out.println("idm="+SSM_GlobalParam.idm);
//            SSM_GlobalParam.rmp3i=this.getParameter("rmp3i");
//	    if (SSM_GlobalParam.rmp3i == null)
//	    	SSM_GlobalParam.rmp3i = "NO";
//            if (SSM_GlobalParam.rmp3i == "")
//	    	SSM_GlobalParam.rmp3i = "NO";
//            System.out.println("rmp3i="+SSM_GlobalParam.rmp3i);
            
            if(( SSM_GlobalParam.token==null)||( SSM_GlobalParam.utenza==null))
            {
                warningProblem("Errore nei paramteri passati da IDM","Error Message");
                return;
            }
        }catch(Exception e )
        {
                warningProblem("Errore nei paramteri passati da IDM","Error Message");
                return;
        }
        
        SSM_GlobalParam.IP_CLIENT = this.IP_CLIENT;
        SSM_GlobalParam.HOSTNAME_CLIENT = this.HOSTNAME_CLIENT;
        SSM_GlobalParam.CORBAConn = new SSM_CORBAConnection(this.IP_CLIENT,this.HOSTNAME_CLIENT,SSM_GlobalParam.utenza,SSM_GlobalParam.token);
        SSM_GlobalParam.CORBAConn.setJApplet(this);
        
   
        if(SSM_GlobalParam.db_Connection == null)
               SSM_GlobalParam.db_Connection = new SSM_DBConnection();
        
        boolean DB_CONN = SSM_GlobalParam.db_Connection.DBConnect("","","");

        if(DB_CONN == true)
                System.out.println("DB_CONN ==> OK");
        else
        {
            System.out.println("DB_CONN ==> failed");            
            warningProblem("Communications failure with Database.","Communications Database");
            return;
        }
       
        // ----------------- ----------------------- ****** 
        System.out.println("Connessione Al SERVER CONFIGURATION disabilitata");  
        
        //SSM_JF_LoginStart JfLogin = new SSM_JF_LoginStart();        
        SSM_JF_Global jFGlobal = new SSM_JF_Global();
        
        System.out.println("Connessione Al SERVER CONFIGURATION disabilitata");
        // -----------------
        
        /*boolean statoConnConf = SSM_GlobalParam.CORBAConn.openConnection_config();      // Connessione al Server Configurazione
        System.out.println("Connection Server Configuration   ==>  "+statoConnConf);

        if (statoConnConf)
        {
            SSM_JF_LoginStart JfLogin = new SSM_JF_LoginStart();
        }
        else
            System.out.println("Connection CORBA failure");
        */
         // ----------------- ----------------------- ****** 
    }

    
    private String get_HostName()
    {
        try
        {
            java.net.InetAddress machine = java.net.InetAddress.getLocalHost();
            System.out.println("Machine Name : " + machine.getHostName());
            
            if(machine.getHostName() != null)
                return machine.getHostName();
            else
                return "N/A";
        }
        catch(Exception e)
        {
            System.out.println(" get_HostName ------> Exception " +e);
            e.printStackTrace();
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
                str_hostAddress = "No Local network interfaces were found";
            }                   
        }
        catch(Exception e)
        {
            System.out.println(" get_HostAddress [1] ------> Exception " +e);
            e.printStackTrace();
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
            
           //System.out.println("--> Machine IP Address [getHostAddress]: " +machine2.getHostAddress()); //***** per le macchine linux non e sufficente ****
        }
        catch(Exception e)
        {
            System.out.println(" get_HostAddress [2] ------> Exception " +e);
            str_hostAddress = null;
            
        }
        
        if(str_hostAddress.length() == 0)
            str_hostAddress = null;
        
       //System.out.println(" --> Machine IP Address: "+str_hostAddress);
        return str_hostAddress;
    }
    
    
    private void initComponents() {//GEN-BEGIN:initComponents
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
     
    private void warningProblem(String str1,String str2)
    {
            //JOptionPane warning = new JOptionPane();
            //warning.showMessageDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE);
            
            JD_Message message = null;
            message = new JD_Message((JFrame)null,true,str1,str2,message.CRITICAL);

            //warning.showConfirmDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE,JOptionPane.QUESTION_MESSAGE);
    }
}


/*try
        {                      
            //System.out.println("------------------------------------------\n");

            java.util.Enumeration ifEnum = NetworkInterface.getNetworkInterfaces();
            java.net.InetAddress machine = java.net.InetAddress.getLocalHost();
            
            System.out.println("Machine IP Address : " +machine.getHostAddress()); //***** per le macchine linux non �sufficente ****
            System.out.println("---- ---- ----\n");

            this.HOSTNAME_CLIENT = machine.getHostName();

            if(ifEnum != null)
            {
                System.out.println("*** Local Interfaces ***\n");
                while(ifEnum.hasMoreElements())
                {
                    NetworkInterface localIf = (NetworkInterface)(ifEnum.nextElement());
                    System.out.println("Interface Name : " +localIf.getName());
                    if(localIf.getName().equals("eth0"))
                    {
                        //get the addresses of this interface
                        Enumeration ifAddrEnum = localIf.getInetAddresses();
                        System.out.print("IP Addresses : ");

                        while(ifAddrEnum.hasMoreElements())
                        {
                           InetAddress ifAddr = (InetAddress)(ifAddrEnum.nextElement());

                           System.out.print(ifAddr.toString() + " ");
                           this.IP_CLIENT = ifAddr.toString();
                        }
                    }
                    System.out.println("\n");
                }
                System.out.println("------------------------------------------\n");
            }
            else
            {
                //if the enumeration is null, mean no network interfaceswere found
                System.out.println("No Local network interfaces were found");
                this.IP_CLIENT = "N/A";
            }
           
        }
        catch(Exception e)
        {
            this.IP_CLIENT = "----------------LoginUserN/A";
            System.out.println("Exception " +e);
        }*/
        
