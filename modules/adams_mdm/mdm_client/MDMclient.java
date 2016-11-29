
import java.util.*;
import java.net.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import net.etech.loadior.*;



public class MDMclient extends javax.swing.JApplet {

    private String HOSTNAME_CLIENT;
    
    /** 
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe atto alla costruzione e posizionamento del layout grafico.
     * </font></font></p></pre>
     */
    public MDMclient() {
    	
        System.out.println("MDM START Costruttore prova.");
        initComponents();

        
        if(ntmFrameLogin != null)
            ntmFrameLogin.toFront();
    }

 
    public void init()
    {       
        System.out.println("MDM STAR Init.");
        
        // test getIor - Raffo (importare net.etech.loadior.*
        ADAMS_IOR adams_ior=new ADAMS_IOR();
        String nome_server="ssm_logger";
        String ior="";
        ior=adams_ior.getIor(nome_server);
        if (ior=="")
        {
		System.out.println("ERRORE getIOR");
	}else
	{
		System.out.println("IOR("+nome_server+")="+ior);
	}
	
	// test connessione CORBA
	/*staticLib.token	 	= this.getParameter("token");
	staticLib.utenza		= this.getParameter("utenza");
	staticLib.IP_CLIENT = staticLib.IP_ADDRESS;
        staticLib.HOSTNAME_CLIENT = this.HOSTNAME_CLIENT;
        staticLib.CORBA = new connCORBA(staticLib.IP_CLIENT,this.HOSTNAME_CLIENT,staticLib.utenza,staticLib.token);
        staticLib.CORBA.setValue(this);
        
        staticLib.applet_corrente=this;      
        
	staticLib.CORBA.openConnectionMasterServer();*/
        // -----------------------------------------------------------
        
        
        /*System.out.println("staticLib.ID_CLIENT "+staticLib.ID_CLIENT);
        System.out.println("local_ID_CLIENT "+local_ID_CLIENT);
        */
        if(staticLib.ID_CLIENT == -1)
        {        
            staticLib.ID_CLIENT = (int)( 10000+ java.lang.Math.random()*90000 ); //numero random
            local_ID_CLIENT = staticLib.ID_CLIENT;
        }
        else if(local_ID_CLIENT != staticLib.ID_CLIENT)
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(this,"Attenzione: Impossibile lanciare nuovamente N.T.M.\n Un'istanza di N.T.M risulta gia' in esecuzione, chiudere il browser e rilanciare l'applicativo.","Error Message",javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }  
        
        
        try
        {
            staticLib.token	 	= this.getParameter("token");
            staticLib.utenza		= this.getParameter("utenza");
            staticLib.idm 		= this.getParameter("idm");
            staticLib.path_report 	= this.getParameter("path_report");
            staticLib.path_report_job 	= this.getParameter("path_report_job");
            staticLib.oracle_SID   	= this.getParameter("oracle_SID");
            staticLib.oracle_port  	= this.getParameter("oracle_port");
            
            
            ///////// ------- PARAMETRI DRILL DOWN
            try{staticLib.dd_TipoAnalisi = new Integer(this.getParameter("dd_TipoAnalisi")).intValue();} // DRILL-DOWN --> ID TIPO ANALISI
            catch (Exception n)
            {
                staticLib.dd_TipoAnalisi = 23; //Call Monitoring
                System.out.println("ATTENZIONE errore getParameter(dd_TipoAnalisi) -- DRILL DOWN -- ID TipoAnalisi DEFAULT ==> "+staticLib.dd_TipoAnalisi);
            }
            
            try{staticLib.dd_idReportSchema = new Integer(this.getParameter("dd_idReportSchema")).intValue();} // DRILL-DOWN --> ID REPORT SCHEMA
            catch (Exception n)
            {
                staticLib.dd_idReportSchema = 487; //Call Monitoring XLS
                System.out.println("ATTENZIONE errore getParameter(dd_idReportSchema)  DRILL DOWN -- ID idReportSchema DEFAULT ==> "+staticLib.dd_idReportSchema);
            }
                    
            //System.out.println("dd_TipoAnalisi    --> "+staticLib.dd_TipoAnalisi);
            //System.out.println("dd_idReportSchema --> "+staticLib.dd_idReportSchema);            
            ///////// end ------- PARAMETRI DRILL DOWN
            
            try
            {
            	staticLib.SESSION_TIME	= Integer.parseInt(this.getParameter("SESSION_TIME"));
            	//System.out.println("SET SESSION_TIME ->"+staticLib.SESSION_TIME);
     	    }
     	    catch(Exception e )
	    	{
                staticLib.SESSION_TIME = 20;
                //System.out.println("DEFAULT SESSION_TIME ->"+staticLib.SESSION_TIME);
	    	}
     			        
            //System.out.println("IDM="+staticLib.idm);
//	    staticLib.rmp3i=this.getParameter("rmp3i");
//	    if (staticLib.rmp3i == "")
//	    	staticLib.rmp3i = "NO";
            
            //System.out.println("rmp3i="+staticLib.rmp3i);
            if( staticLib.SESSION_TIME == 0)
            {
                staticLib.SESSION_TIME = 20;
            }
            
            if(( staticLib.token==null)||( staticLib.utenza==null))
            {
                staticLib.optionPanel("Errore nei paramteri passati da IDM","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch(Exception e )
        {
                staticLib.optionPanel("Errore nei paramteri passati da IDM","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
        }
          
        
        //*************

        this.HOSTNAME_CLIENT   = get_HostName();
        staticLib.IP_ADDRESS   = get_HostAddress();

        
        if(staticLib.IP_ADDRESS == null)
        {        
            warningProblem("Access Denied: IP Address not defined.","Accesso Denied.");
            return;
        }
                
        System.out.println();
        System.out.println("->> Machine Name : " +this.HOSTNAME_CLIENT);
        System.out.println("->> Machine IP Address: " +staticLib.IP_ADDRESS);  
        System.out.println("->> SESSION_TIME : " +staticLib.SESSION_TIME);  
        System.out.println();
        
        //*************
                      
        staticLib.IP_CLIENT = staticLib.IP_ADDRESS;
        staticLib.HOSTNAME_CLIENT = this.HOSTNAME_CLIENT;
        staticLib.CORBA = new connCORBA(staticLib.IP_CLIENT,this.HOSTNAME_CLIENT,staticLib.utenza,staticLib.token);
        staticLib.CORBA.setValue(this);
        
        staticLib.applet_corrente=this;        
        
        ntmFrameLogin = null;        
        ntmFrameLogin = new MDM_JF_LoginStart_1(this);
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Overloading del metodo destroy() della JApplet preposto alla corretta chiusra dell'applicativo NTM.
     * </font></font></p></pre>
     */
    public void destroy()
    {
        if(local_ID_CLIENT == staticLib.ID_CLIENT)
        {
            staticLib.ID_CLIENT = -1;        
            //System.out.println("destroy staticLib.ID_CLIENT "+staticLib.ID_CLIENT);
            //System.out.println("destroy da local_ID_CLIENT "+local_ID_CLIENT);
            ntmFrameLogin.closeFrame();
            ntmFrameLogin = null;
        }    
            
        System.out.println("Exit NTM");        
    }
 
    private String get_HostName()
    {                
        //System.out.println(" -->>>>>>> get_HostName"); 
        try
        {
            java.net.InetAddress machine = java.net.InetAddress.getLocalHost();
            //System.out.println("Machine Name : " + machine.getHostName());
            
            if(machine.getHostName() != null)
                return machine.getHostName();
            else
                return "N/A";     
            
        }
        catch(Exception e)
        {
            System.out.println(" get_HostName -- Exception: --> " +e);
            e.printStackTrace();
            return "";
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
            //System.out.println(" get_HostAddress [1] ------> Exception " +e);
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
            
            //System.out.println("--> Machine IP Address [getHostAddress]: " +machine2.getHostAddress()); //***** per le macchine linux non Ã¯nsufficente ****
        }
        catch(Exception e)
        {
            //System.out.println(" get_HostAddress [2] ------> Exception " +e);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.LineBorder(null, 2, true));
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brends/mdm_180x55_g.png"))); // NOI18N
        jPanel1.add(jLabel3);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brends/adams_etech_154x65.png"))); // NOI18N
        jPanel1.add(jLabel4);

        getContentPane().add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private int local_ID_CLIENT = -2;
    private MDM_JF_LoginStart_1 ntmFrameLogin;
    
    
}
