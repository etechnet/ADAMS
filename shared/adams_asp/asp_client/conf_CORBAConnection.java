import javax.swing.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;

import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;
import net.etech.loadior.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_CORBAConnection </b> </p>
 *
 * Questa classe permette di effettuare le connessioni ai Server remoti tramite l'architettura standard CORBA. 
 * Il client dell'applicativo scritto interamente in JAVA, tramite questa architettura Ã¨ in grado di richiamare 
 * i metodi di un oggetto server remoto sviluppato con un linguaggio differente.
 */
public class conf_CORBAConnection 
{
    private boolean DEBUG = false;
     /**
     * Riferimento all'JApplet di start-up dell'applicativo.	
     */ 
    public JApplet local_applet     = null;

    private asp_entry asp_entryRef    = null; 
    private asp_entry   asp_entry_ref		=null; // nuova connessione - Test Raffo
    private boolean status_Conf     = false;
    private String ns_ref = "";
    private String IP_CLIENT = "";
    private String HOSTNAME_CLIENT = "";
    private S_APP_LOG AppLog = null;
    
    
    org.omg.CORBA.ORB orbMonMasterServer      = null;
    
    ///////////////////////
    private PsMonitorMasterServer PsMonitorMasterServerRef  = null;
    private boolean status_PsMonitor                        = false;
    //////////////////////
    
    /**
     * Costruttore della classe.
     *@param machine_name Nome identificativo della macchina remota per la comunicazione dati.
     *@param locator_port Identificativo della porta di comunicazione dell'applicativo.
     *@param ipClient Indirizzo IP della macchina client dove viene eseguito l'applicativo.
     *@param hostNameClient Nome identificativo della macchina client dove viene eseguito l'applicativo.
     */
    public conf_CORBAConnection(String machine_name, String locator_port,String ipClient, String hostNameClient) 
    {
	//System.out.println("");
	//System.out.println("-------- conf_CORBAConnection(.....)");
	
        ns_ref = "corbaloc::" + machine_name + ":" + locator_port + "/NameService";
        this.IP_CLIENT = ipClient;
        this.HOSTNAME_CLIENT = hostNameClient;
        /*
        	char timeStamp[MAX_TIME_STAMP];
	char ip_server[MAX_IP_GENERATORE];
	char hostname_server[MAX_HOSTNAME_GENERATORE];
	char ip_client[MAX_IP_CLIENT];
	char hostname_client[MAX_HOSTNAME_CLIENT];
	char application_user[MAX_UTENZA_APPLICATIVA];
	char client_user[MAX_UTENZA_CLIENT];
	char application[MAX_APPLICATIVO_CLIENT];
	char action[MAX_AZIONE];
	char action_object[MAX_OGGETTO];
	char parameter[MAX_PARAMETRI];
	char successful[MAX_ESITO];
	long return_code;
        */
        AppLog = new S_APP_LOG( set_String_toChar( "" ,conf_GlobalParam.MAX_TIME_STAMP),
                                set_String_toChar( "" ,conf_GlobalParam.MAX_IP_GENERATORE),
                                set_String_toChar( "" ,conf_GlobalParam.MAX_HOSTNAME_GENERATORE),
                                set_String_toChar( this.IP_CLIENT ,conf_GlobalParam.MAX_IP_CLIENT),
                                set_String_toChar( this.HOSTNAME_CLIENT ,conf_GlobalParam.MAX_HOSTNAME_CLIENT),
                                set_String_toChar( "-" ,conf_GlobalParam.MAX_UTENZA_APPLICATIVA),
                                set_String_toChar( conf_GlobalParam.utenza ,conf_GlobalParam.MAX_UTENZA_CLIENT),
                                set_String_toChar( "STS_CONF" ,conf_GlobalParam.MAX_APPLICATIVO_CLIENT),
                                set_String_toChar( "" ,conf_GlobalParam.MAX_AZIONE),
                                set_String_toChar( "" ,conf_GlobalParam.MAX_OGGETTO),
                                set_String_toChar( "" ,conf_GlobalParam.MAX_PARAMETRI),
                                set_String_toChar( "" ,conf_GlobalParam.MAX_ESITO),
                                0);           
    }
    
    
    /**
     * Metodo specifico che permette di settare l'indirizzo IP e hostname della macchina client.
     *@param ipClient Indirizzo IP della macchina client dove viene eseguito l'applicativo.
     *@param hostNameClient Nome identificativo della macchina client dove viene eseguito l'applicativo.
     */
    public void setInfoClient(String ipClient, String hostNameClient)
    {
        //System.out.println("");
        //System.out.println("-------- setInfoClient(.....))");
        
        this.IP_CLIENT = ipClient;
        this.HOSTNAME_CLIENT = hostNameClient;
        
    }
    
    /**
     * Questo metodo accetta come parametro di input il riferimanto all'JApplet di start-up dell'applicativo,
     * la classe utilizza tale riferimento per prelevare i parametri necessari  alle connesioni con i Server.
     *
     *@param   applet JApplet di start-up del client.
     */
    public void setJApplet(JApplet applet)
    {
    	//System.out.println("");
        //System.out.println("-------- setJApplet(.....))");
        
    	this.local_applet =applet;
    }
    
       
// ################# Connessione con il sever di Configurazione ################### //
    
    public boolean openConnectionASPServer()
	{
		boolean flagConnectionASPServer=false;
		System.out.println("openConnectionASPServer");
		ORB orb = ORB.init(local_applet, null);
		
		ADAMS_IOR adams_ior=new ADAMS_IOR();
		String nome_server="asp_server";
		String ior="";
		ior=adams_ior.getIor(nome_server);
		if (ior=="")
		{
			JOptionPane warning = new JOptionPane();
			warning.showMessageDialog(local_applet,"Error load IOR.","Error Message",JOptionPane.ERROR_MESSAGE); 
			flagConnectionASPServer=false;
		}else
		{
			System.out.println("IOR("+nome_server+")="+ior);
		}
	
		// Destringify object reference
		org.omg.CORBA.Object       obj = orb.string_to_object(ior);
		System.out.println("Destringify object reference");
		
		    // Narrow the CORBA object to its proper type, so that the client can invoke it
		asp_entry_ref = asp_entryHelper.narrow(obj);


		if (asp_entry_ref == null)
		{
		    JOptionPane warning = new JOptionPane();
		    warning.showMessageDialog(local_applet,"Cannot resolved object ref mdm_server_server.","Error Message",JOptionPane.ERROR_MESSAGE);
		    flagConnectionASPServer=false;
		}
		else
		{
		    System.out.println("***JavaClient:Resolved object reference");
		    flagConnectionASPServer=true;
		    asp_entry_ref.dummy();
		}
		return flagConnectionASPServer;
        }
        
   /**
    * Questo metodo permette di effettuare la connessione al Server remoto di configurazione "STS_ConfigServer".
    * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
    *@return Ritorna true a connessione effettuata con esito positivo.
    */
    public boolean openConnection_config()
    { 
        //System.out.println("");
        //System.out.println("-------- openConnection_config(.....))");
               
        //lancia il MS
        org.omg.CORBA.StringHolder Url=new org.omg.CORBA.StringHolder();
        
	try 
        {       
		//System.out.println("Prima ORBInit");
        	org.omg.CORBA.ORB orb =org.omg.CORBA.ORB.init(local_applet,null);
		//System.out.println("Prima string_to_object");
        	org.omg.CORBA.Object objRef = orb.string_to_object(ns_ref);
		//System.out.println("Dopo string_to_object" );
		
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		//System.out.println("Dopo NamingContextExt" );       	
            //********************************** MultiThread
                                
                NameComponent nc2 = new NameComponent("STS","");
                NameComponent nc3 = new NameComponent("STSConfigServerNew","");                
                
                NameComponent path[] ={nc2,nc3};
                org.omg.CORBA.Object obj_res=ncRef.resolve(path);

		//System.out.println("prima della narrow" );
                asp_entryRef = asp_entryHelper.narrow(obj_res);
                //System.out.println("dopo della narrow" );
                    
            //********************************* 
		
                if (asp_entryRef == null) 
                {
                    System.out.println("asp_entryRef NULL");
                    return false;
		}                 
                status_Conf = true;
		return true;        
        } 
        catch (Exception e)  
        {
            System.out.println("===> catch - openConnection_config CORBAConnection.java "+e);  
	    e.printStackTrace();
	    //System.exit(0); 
            status_Conf = false;
	    JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(local_applet,"STS_ConfigServer: Connection refused ["+e+"]","Error Message",JOptionPane.ERROR_MESSAGE); 
            return false;
            
        }               
    }
    
   
    
    /**
     * Questo metodo permette di effettuare la connessione al Server remoto di configurazione "PsMonitorMasterServer".
     * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     *@return Ritorna true a connessione effettuata con esito positivo.
     */
    public boolean openConnection_psMonitorMasterServer()
    {
        try
        {  
            try 
            {                    
                if(orbMonMasterServer != null)
                    orbMonMasterServer.destroy();                                        
            }
            catch (Exception e)
            {    
                System.out.println("ex orbMonMasterServer.destroy()");
            } 
            
            orbMonMasterServer =org.omg.CORBA.ORB.init(local_applet,null);
            org.omg.CORBA.Object objRef = orbMonMasterServer.string_to_object(ns_ref);
            
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            //System.out.println("NS Narrow OK");

            NameComponent nc1 = new NameComponent("STS","");
            NameComponent nc2 = new NameComponent("PSMONITOR","");
            NameComponent nc3 = new NameComponent("PsMonitorMasterServer","");
                      
            NameComponent path[] = {nc1,nc2,nc3};
            
            org.omg.CORBA.Object obj_res = ncRef.resolve(path);
            PsMonitorMasterServerRef = PsMonitorMasterServerHelper.narrow(obj_res);
            
            if (PsMonitorMasterServerRef == null)
            {
                    System.out.println("ERROR narrowing PsMonitorMasterServerRef !!");
            }
                
            status_PsMonitor = true;
            return true;
        }
        catch (Exception e)
        {
            System.out.println("===> catch - openConnection_psMonitorMasterServer ");
            status_PsMonitor = false;
	    JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(local_applet,"PsMonitorMasterServer: Connection refused.\n"+e,"Error Message",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public GESTIONE_PSWD[] getFilePSWD (String nameSwitch, String nomefile, int flag)
    {    	
    	GESTIONE_PSWDSeqHolder pswdSequence = null;
    	    	
        int req = -1;
        if(status_PsMonitor)
        {
            try
            {         
            	pswdSequence = new GESTIONE_PSWDSeqHolder();
                //System.out.println("prima getFilePSWD");
                req = PsMonitorMasterServerRef.getFilePSWD(nameSwitch.toUpperCase() ,nomefile,flag,pswdSequence);
                //System.out.println("dopo getFilePSWD");
            }   
            catch (Exception e) 
            {
                    warningProblem(e);
                    e.printStackTrace();
                    return null;
            }
            
            String esito = "false";
            if(req != 1)
            	esito = "true";
            
            boolean f_log = writeAPP_LOG("Get file Password Management.","PsMonitorMasterServerRef.getFilePSWD(...)","Get File Password: "+nomefile,esito,req);           
            
        }
        //se req == 1 esito negativo.
        if( (req != 1) && (pswdSequence != null) )
        {   
       		return pswdSequence.value;
        }
        else    
        	return null;
    }
            
    
    /*private String cript_xor(char[] str, char[] key)
    {
      for(int i=0; i<str.length; i++)
        str[i] = (char)(str[i]^key[i%key.length]);
      return str;
    }*/
    
   /* private String cript_xor2(char[] data, char[] key, int block_size)
    {           
        int y=0;
        for(int i=0; i<=data.length;)
        {    
            for (int x=0; x<=block_size; x++)
            {                 
                if(i < data.length)
                {
                    data[i] ^= key[y];
                }
                i++;
            }
            y++;
            if(y == key.length)
                y=0;
        }
        return (new String(data));
    }*/
    
    public int putFilePSWD (String nameSwitch, String nomefile, int flag, GESTIONE_PSWD[] pswdSequence )
    {
        int req = 1;
        if(status_PsMonitor)
        {
            try
            {         
                //System.out.println("prima putFilePSWD");
                req = PsMonitorMasterServerRef.putFilePSWD(nameSwitch,nomefile,flag,pswdSequence);
                //System.out.println("dopo putFilePSWD");    
                
                String esito = "false";
            	if(req != 1)
            		esito = "true";
            		
                boolean f_log = writeAPP_LOG("Put file Password Management.","PsMonitorMasterServerRef.putFilePSWD(...)","Put File Password: "+nomefile,esito,req);
                           
            }   
            catch (Exception e) 
            {
                    warningProblem(e);
                    e.printStackTrace();
            }
        }        
        return req; //se req == 1 esito negativo.
    }
    
    public boolean writeAPP_LOG(String descAzione, String metodo, String descParametro, String esito, int retCode)
    {
     	if(status_Conf)
    	{
    		
    		S_APP_LOG AppLog_1 = new S_APP_LOG( set_String_toChar( "" ,conf_GlobalParam.MAX_TIME_STAMP),
		                                set_String_toChar( "" ,conf_GlobalParam.MAX_IP_GENERATORE),
		                                set_String_toChar( "" ,conf_GlobalParam.MAX_HOSTNAME_GENERATORE),
		                                set_String_toChar( this.IP_CLIENT ,conf_GlobalParam.MAX_IP_CLIENT),
		                                set_String_toChar( this.HOSTNAME_CLIENT ,conf_GlobalParam.MAX_HOSTNAME_CLIENT),
		                                set_String_toChar( "corba" ,conf_GlobalParam.MAX_UTENZA_APPLICATIVA),
		                                set_String_toChar( conf_GlobalParam.utenza ,conf_GlobalParam.MAX_UTENZA_CLIENT),
		                                set_String_toChar( "STS_CONF" ,conf_GlobalParam.MAX_APPLICATIVO_CLIENT),
		                                set_String_toChar( descAzione ,conf_GlobalParam.MAX_AZIONE),
		                                set_String_toChar( metodo ,conf_GlobalParam.MAX_OGGETTO),
		                                set_String_toChar( descParametro ,conf_GlobalParam.MAX_PARAMETRI),
		                                set_String_toChar( esito ,conf_GlobalParam.MAX_ESITO),
		                                retCode);      
    		
	    	try
	      	{	    	             
			boolean stat = false;		    
		        //System.out.println("Prima writeAPP_LOG()");
			//stat = asp_entryRef.writeAPP_LOG(AppLog_1);
		        //System.out.println("Dopo writeAPP_LOG() ====> "+stat);
		                                	
			if(stat == false)
		    		System.out.println("Attenzione: LOG write ERROR!!");
			//else		    		
		    		//System.out.println("LOG write OK!");
		    	
		    	return stat;
		}   
		catch (Exception e) 
		{
			warningProblem(e);
			e.printStackTrace();
		}
    	}
    	
    	System.out.println("Attenzione: LOG write ERROR!!");    	
    	return false;
    }
    
    
    
    
    /**
     * Il metodo richiede le informazioni relative alle centrali.
     * @return un array di strutture di tipo DATA_CENTRALI, contenente le informazioni che caraterizzano una centrale. 
     */
    public DATA_CENTRALI[] Launch_PsMonitorMasterServer()
    {
        CentralSeqHolder CentralSeq = null;
        
        if(status_PsMonitor)
        {        
            boolean stat = false;
            try
            {
                CentralSeq = new CentralSeqHolder();
                //System.out.println("Prima LaunchPsMonitorMasterServer()");
                stat = PsMonitorMasterServerRef.LaunchPsMonitorMasterServer(CentralSeq);
                //System.out.println("Prima LaunchPsMonitorMasterServer() --> "+stat);
            }
            catch(Exception e)
            {
                if(count_Launch_PsMonitorMasterServer <3)
                {
                    //System.out.println("------ Launch_PsMonitorMasterServer() Ritento Connessione al PsMonitorMasterServer -----");
                    count_Launch_PsMonitorMasterServer++;
                    openConnection_psMonitorMasterServer();
                    Launch_PsMonitorMasterServer();
                    return null; 
                }
                else
                {
                    System.out.println("catch Launch_PsMonitorMasterServer()");
                    warningProblem(e);
                }
            }
            if(stat)
            {
                count_Launch_PsMonitorMasterServer = 0;
                return (CentralSeq.value);
            }
            else
                return null;
        }
        else
            return null;
    }
    private int count_Launch_PsMonitorMasterServer = 0;
    
    
// --------------------------------------- STS_CONFIG -------------
    
        
    /**
     *Metodo dedicato all'autenticazione degli accessi all'applicativo (server: STSConfigServerNew). Se true accesso autorizzato.
     *@param login Stringa identificativa di login.
     *@param password Stringa contenente password associata alla login
     *@param disablePassCheck Specifica di controllo per IDM.
     */    
    public boolean LoginUser(String login, String password, boolean disablePassCheck)
    {
    	//System.out.println("");
        //System.out.println("-------- LoginUser(.....))");
    	
        boolean FLAG_disablePassCheck=disablePassCheck;
        if((conf_GlobalParam.idm).toUpperCase().equals("NO"))
        {
                FLAG_disablePassCheck=true;
        }
	
        if (status_Conf)
	{
                org.omg.CORBA.IntHolder ruolo = new org.omg.CORBA.IntHolder();
                               
		boolean stat = false;
		try
		{       
                        //System.out.println("---------------- AppLog.ipClient ---> "+new String(AppLog.ipClient).trim());
			//stat = asp_entryRef.LoginUser(conf_GlobalParam.ID_MOD_STSCONFIG,login,password,FLAG_disablePassCheck,AppLog,ruolo);
                        //System.out.println("LoginUser() = "+stat);
                }   
		catch (Exception e)
		{
                        System.out.println("===> catch - asp_entryRef.LoginUser() ");
                        //e.printStackTrace();
			warningProblem(e);
		}
		if(stat)
                {
                    conf_GlobalParam.RUOLO = ruolo.value;
                    return true;
                }
          	else
                    return false;
	}
	else
            return false;
    }
    
    
    /**
     *Il Metodo mediante chiamata al server remoto (server: STSConfigServerNew) richiede le informazioni di base necessarie allo startup finzionale dell'applicativo.
     */
    public S_CONFIG_LIB getConfigLib()
    {
    	System.out.println("");
        System.out.println("-------- getConfigLib(.....)");
       
        
        boolean flagConnectionASPServer=openConnectionASPServer();
        System.out.println("-------- flagConnectionASPServer="+status_Conf);
        
        
        if (flagConnectionASPServer)
	{
                S_CONFIG_LIBHolder s_config_Lib = new S_CONFIG_LIBHolder();             
		
		
		boolean stat = false;
		try
		{       
                        System.out.println("Chiamo getConfigLib()");
			//stat = asp_entry_ref.getConfigLib(0,s_config_Lib,AppLog);
			stat = asp_entry_ref.getConfigLib(0,s_config_Lib);
                        //System.out.println("getConfigLib() ====> "+stat);
                        
                }   
		catch (Exception e)
		{
                        System.out.println("===> catch - asp_entryRef.getConfigLib() ");
                        e.printStackTrace();
                        //System.out.println("EXCEPTION "+e);
			warningProblem(e);
		}
                
		if(stat)
                    return s_config_Lib.value;
          	else
                    return null;
          }
	  else
            return null;          
    }
    
    /**
     * Il Metodo mediante chiamata al server remoto (server: STSConfigServerNew) richiede le informazioni relative all'oggetto S_PROFILE contenente informazioni
     * descrittive e funzionali dei profili associati all'utente.
     *@profileName Identificativo del profilo.
     */
    public S_PROFILE getProfileConfiguration(String profileName)
    {
    	System.out.println("");
        System.out.println("-------- getProfileConfiguration(.....))");
        
        if (status_Conf)
	{
                S_PROFILEHolder profile_conf = new S_PROFILEHolder();             
		boolean stat = false;
		try
		{       
                        System.out.println("Chiamo getProfileConfiguration()");
			stat = asp_entry_ref.getProfileConfiguration(-1,profileName,profile_conf,AppLog);
                        System.out.println("getProfileConfiguration() ====> "+stat);
                }   
		catch (Exception e)
		{
                    
                        System.out.println("===> catch - asp_entryRef.getProfileConfiguration() ");
                        e.printStackTrace();
                        System.out.println("EXCEPTION "+e);
			warningProblem(e);
		}
                
		if(stat)
                    return profile_conf.value;
          	else
                    return null;
	}
	else
            return null;
    }
       
    
    /**
     * Il Metodo mediante chiamata al server remoto (server: STSConfigServerNew) permette la creazione, modifica o cancellazione di un'oggetto S_PROFILE
     * mediante la variabile in ingresso flag_Action cosÃ¬ interpretata:
     *<pre>
     *
     * CREAZIONE     --> STSBasicConfiguration.eOP_INSERT
     * MODIFICA      --> STSBasicConfiguration.eOP_MODIFY
     * CANCELLAZIONE --> STSBasicConfiguration.eOP_DELETE
     *
     *</pre>
     *
     *@flag_Action Identificativo di azione esecutiva del metodo: creazione/modifica/cancellazione
     *@s_profile Identificativo del profilo.
     *
     *@see conf_GlobalParam.OP_INSERT
     *@see conf_GlobalParam.OP_MODIFY
     *@see conf_GlobalParam.OP_INSERT
     */
    public boolean setProfileConfiguration(int flag_Action, S_PROFILE s_profile)
    {
    	//System.out.println("");
        //System.out.println("-------- setProfileConfiguration(.....))");
    	
        //boolean setProfileConfiguration(in long flag,in S_PROFILE profile, in S_APP_LOG AppLog);
        if (status_Conf)
	{           
		boolean stat = false;
		try
		{       
                        //System.out.println("Chiamo setProfileConfiguration()");                         
                        //stat = asp_entryRef.setProfileConfiguration(flag_Action,s_profile, AppLog);
                        //System.out.println("setProfileConfiguration() ====> "+stat);
                }   
		catch (Exception e)
		{
                        System.out.println("===> catch - asp_entryRef.setProfileConfiguration() ");
                        //e.printStackTrace();
                        //System.out.println("EXCEPTION "+e);
			warningProblem(e);
		}                
                return stat;
	}
	else
            return false;
    }
    
    
    
    /**
     * Il Metodo mediante chiamata al server remoto (server: STSConfigServerNew) richiede le informazioni relative all'oggetto S_USER contenente informazioni
     * descrittive e funzionali sull'utente tipo.
     *@param login Stringa identificativa di login.
     *@IdClass Identificativo di classe applicativa.
     */
    public S_USER getUserConfiguration(String login,int IdClass)
    {
    	System.out.println("");
        System.out.println("-------- getUserConfiguration(.....))");
    	
    	boolean flagConnectionASPServer=openConnectionASPServer();

        if (flagConnectionASPServer)
	{           
            boolean stat = false;
            try
            {       int flag = -1; // se -1 non vengono caricate da server le funzioni abilitate dell'utente.
                    S_USERHolder s_user = new S_USERHolder();
                    System.out.println("Chiamo getUserConfiguration()");                         
                    stat = asp_entry_ref.getUserConfiguration(flag,login,IdClass,s_user,AppLog);
                    System.out.println("getUserConfiguration() ====> "+stat);

                    if(stat)
                        return s_user.value;
                    else 
                        return null;
            }   
            catch (Exception e)
            {
                    System.out.println("===> catch - asp_entryRef.getUserConfiguration() ");
                    //e.printStackTrace();
                    //System.out.println("EXCEPTION "+e);
                    warningProblem(e);
                    return null;
            }                
	}
	else
            return null;
    }
    
    /**
     * Il Metodo mediante chiamata al server remoto (server: STSConfigServerNew) permette la creazione, modifica o cancellazione di un'oggetto S_USER (informazioni utente)
     * mediante la variabile in ingresso flag_Action cosÃ¬ interpretata:
     *<pre>
     *
     * CREAZIONE     --> STSBasicConfiguration.eOP_INSERT
     * MODIFICA      --> STSBasicConfiguration.eOP_MODIFY
     * CANCELLAZIONE --> STSBasicConfiguration.eOP_DELETE
     *
     *</pre>
     *
     *@flag_Action Identificativo di azione esecutiva del metodo: creazione/modifica/cancellazione
     *@s_user Oggetto Identificativo dell'utente d'interesse.
     *
     *@see conf_GlobalParam.OP_INSERT
     *@see conf_GlobalParam.OP_MODIFY
     *@see conf_GlobalParam.OP_INSERT
     */
    public boolean setUserConfiguration(int flagAction, S_USER s_user)
    {
    	System.out.println("");
        System.out.println("-------- setUserConfiguration(.....))");
    	
    	boolean flagConnectionASPServer=openConnectionASPServer();
    	
        if (flagConnectionASPServer)
	{           
            boolean stat = false;
            try
            {                           
                    System.out.println("CORBAConnection: Chiamo setUserConfiguration()");                                        
                    stat = asp_entry_ref.setUserConfiguration(flagAction,s_user,AppLog);
                    System.out.println("dopo setUserConfiguration() ====> "+stat);
                    
                    return stat;
            }   
            catch (Exception e)
            {
                    System.out.println("===> catch - asp_entryRef.setUserConfiguration() ");
                    e.printStackTrace();
                    System.out.println("EXCEPTION "+e);
                    warningProblem(e);
                    return false;
            }                
	}
	else
            return false;
        
    }
    
    public String getPswd (String nomeNodo,String typePassword,String userLogin ) 
    {
            org.omg.CORBA.StringHolder str_output = null;

            boolean stat = false;
            try {
                    if ( status_Conf ) {
                            //System.out.println("Chiamata getPswd(...)");				
                            str_output = new org.omg.CORBA.StringHolder();
                            //stat = asp_entryRef.getPswd(nomeNodo,typePassword,userLogin, str_output);				
                            //System.out.println("DOPO getPswd(...)");

                            count_getPswd = 0;
                    }
            }
            catch ( Exception e ) {
                    if ( count_getPswd  < 3 ) {
                            System.out.println ( "------ getPswd(...)  Ritento Connessione al ConfigServer -----" );
                            count_getPswd++;
                            openConnection_config();
                            getPswd ( nomeNodo,typePassword,userLogin );
                            return null;
                    }
                    else {
                            System.out.println ( "catch getPswd(...)" );
                            warningProblem(e);
                    }
            }
            if(stat)
            {
                    //System.out.println ( "getPswd(...) PWD str_output.value -->"+str_output.value );
                    return ( str_output.value );			
            }
            else
            {
                    System.out.println ( "getPswd(...) --> null" );
                    return null;
            }
    }
    int count_getPswd = 0;
    
    
    /**
     * Effettua lo shutDown del Server di configurazione (server: STSConfigServerNew).
     */
    public void shutDown_Conf()
    {
    	//System.out.println(" ************** asp_entryRef.shutDown() ************** ");
    	if (status_Conf)
	{
		try
		{
			//asp_entryRef.shutDown();
                }
		catch (Exception e)
		{
			warningProblem(e);
		}
	}
    }
    
// --------------------------------------- END STS_CONFIG -------------
    
    
        
    /**
    * Metodo che permette di trasformare una stringa in un array di caratteri.
    *@param str String il cui contenuto sarÃ  copiato nell'array di char.
    *@param length dimensione dell'array di char.
    *@return Un'array di char dalle dimensione passata "length" contenente i caratteri della stringa passata in input "str". 
    */
    public static char[] set_String_toChar(String str, int length)
    {

        char[] appo = str.toCharArray();
        char[] appo1 = new char[length];

        if (appo.length > length)
            return appo1;


        for (int i=0; i<appo.length; i++)
            appo1[i] = appo[i];

        for (int i=appo.length; i<length; i++) 
            appo1[i] =0;

        return(appo1);
    }

//@@@@@@@@@@@@@    warningProblem   @@@@@@@@@@@@@@@/
    
    private void warningProblem(Exception e)
    {
    	 JOptionPane warning = new JOptionPane();
         warning.showMessageDialog(local_applet,"Comunication problem... please start the application again.\n"+e.toString(),"Error Message",JOptionPane.ERROR_MESSAGE);
    }
    
    private void warningProblem()
    {
    	 JOptionPane warning = new JOptionPane();
         warning.showMessageDialog(local_applet,"Comunication problem... please start the application again.","Error Message",JOptionPane.ERROR_MESSAGE); 
    } 
    
    
    
}
