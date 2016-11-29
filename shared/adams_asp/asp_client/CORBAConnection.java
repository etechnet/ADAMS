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
 * <p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif">Dynamic
 * Traffic Matrix</font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (COMPAQ) - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 * <pre>
 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
 * Questa classe permette di effettuare le connessioni ai Server remoti tramite l'architettura standard CORBA.
 * Il client dell'applicativo, scritto interamente in JAVA, tramite questa architettura Ã¨ in grado di richiamare
 * i metodi di un oggetto server remoto sviluppato con un linguaggio differente.
 *
 */
public class CORBAConnection
{
    /**
     * Riferimento all'JApplet di start-up dell'applicativo.
     * </font></font></p>
     */
    private boolean                                 DEBUG                   = false;
    public JApplet                                  local_applet            = null;
    private asp_entry                                asp_entryRef             = null;
    private mdm_server_server                        mdm_server_serverRef      = null;
    private mdm_server_factory_server                 mdm_server_factory_serverRef  = null;
    private mdm_server_job_server                        mdm_server_job_serverRef      = null;
    private boolean                                 status_Conf             = false;
    private boolean                                 status_mdm_server_server  = false;
    private boolean                                 status_BuildJob         = false;
    public String                                   ns_ref                  = null;
    org.omg.CORBA.ORB                               orbSTSConf              = null;
    org.omg.CORBA.ORB                               orbMonMasterServer      = null;

    private int                                     _ERROR_TEXT_LEN         = 1024;
    private S_APP_LOG                               AppLog                  = null;

    private  String LOGIN = "";
    private  String PASSWORD = "";

    ///////////////////////
    private PsMonitorMasterServer PsMonitorMasterServerRef  = null;
    private boolean status_PsMonitor                        = false;

    //////////////////////

   /**
   * Costruttore della classe.
   * Per istanziare un'oggetto CORBAConnection di Default.
   */


    public CORBAConnection(String machine_name,String locator_port)
    {
        ns_ref = "corbaloc::" + machine_name + ":" + locator_port + "/NameService";
        AppLog = new S_APP_LOG( ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_TIME_STAMP),
                                ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_IP_GENERATORE),
                                ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_HOSTNAME_GENERATORE),
                                ADAMS_GlobalParam.set_String_toChar( ADAMS_GlobalParam.IP_CLIENT ,ADAMS_GlobalParam.MAX_IP_CLIENT),
                                ADAMS_GlobalParam.set_String_toChar( ADAMS_GlobalParam.HOSTNAME_CLIENT ,ADAMS_GlobalParam.MAX_HOSTNAME_CLIENT),
                                ADAMS_GlobalParam.set_String_toChar( "mauro",ADAMS_GlobalParam.MAX_UTENZA_APPLICATIVA),
                                ADAMS_GlobalParam.set_String_toChar( "mauro" ,ADAMS_GlobalParam.MAX_UTENZA_CLIENT),
                                ADAMS_GlobalParam.set_String_toChar( "NTM_CONF" ,ADAMS_GlobalParam.MAX_APPLICATIVO_CLIENT),
                                ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_AZIONE),
                                ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_OGGETTO),
                                ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_PARAMETRI),
                                ADAMS_GlobalParam.set_String_toChar( "" ,ADAMS_GlobalParam.MAX_ESITO),
                                0);
    }

    /**
     * Questo metodo accetta come parametro di input il riferimento alla JApplet di start-up dell'applicativo,
     * la classe utilizza tale riferimento per prelevare i parametri necessari  alle connesioni con i Server.
     *
     *@param   applet JApplet di start-up del client.
     */
    public void setJApplet(JApplet applet)
    {
    	this.local_applet = applet;
    }

           
           
// ################# Connessione con il sever di Configurazione ################### //

    /**
     * Questo metodo permette di effettuare la connessione al Server remoto di configurazione "STS_ConfigServer".
     * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     *@return Ritorna true a connessione effettuata con esito positivo.
     */
    public boolean openConnection_config()
    {
    		return true;
        /*try
        {
            try
            {
                if(orbSTSConf != null)
                    orbSTSConf.destroy();
            }
            catch (Exception e)
            {
                System.out.println("ex orbSTSConf.destroy()");
            }

            orbSTSConf = org.omg.CORBA.ORB.init(local_applet,null);
            org.omg.CORBA.Object objRef = orbSTSConf.string_to_object(ns_ref);

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

    //********************************** MultiThread

                NameComponent nc2 = new NameComponent("STS","");
                NameComponent nc3 = new NameComponent("STSConfigServerNew","");

                NameComponent path[] ={nc2,nc3};
                org.omg.CORBA.Object obj_res=ncRef.resolve(path);

                asp_entryRef = asp_entryHelper.narrow(obj_res);

                System.out.println("SERVER NARROW OK ==> STSConfigServer");
                if (asp_entryRef == null)
                {
                        System.out.println("ERROR narrowing STS_ConfigServer !!");
                }

    //********************************

            status_Conf = true;
            return true;
        }
        catch (Exception e)
        {
           System.out.println("===> catch - openConnection_config CORBAConnection.java "+e);
            status_Conf = false;

            ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"STS_ConfigServer: Connection refused."+" "+e,"Error Message",1);
	    			//JOptionPane warning = new JOptionPane();
            //warning.showMessageDialog(local_applet,"STS_ConfigServer: Connection refused."+" "+e,"Error Message",JOptionPane.ERROR_MESSAGE);
            return false;
        }*/
    }

    public boolean openConnection_mdm_server_server()
    {
    	return true;
    	/*
        try
        {

            //System.out.println("Prima ORBInit");
            org.omg.CORBA.ORB orb =org.omg.CORBA.ORB.init(local_applet,null);
            //System.out.println("Prima string_to_object");
            org.omg.CORBA.Object objRef = orb.string_to_object(ns_ref);
            //System.out.println("Dopo string_to_object" );

            NamingContext ncRef 	= NamingContextHelper.narrow(objRef);
            NameComponent nc1 	= new NameComponent("STS","");
            NameComponent nc2 	= new NameComponent("NTM","");
            NameComponent nc3 	= new NameComponent("MasterServerFactory","");
            NameComponent path[] 	= {nc1,nc2,nc3};

            //System.out.println("SERVER REFERENCE:"+nc1.toString()+"/"+nc2.toString()+"/"+nc3.toString());

            org.omg.CORBA.Object obj_res=ncRef.resolve(path);

            //System.out.println("SERVER RESOLVE OK");

            MasterServerFactoryRef = MasterServerFactoryHelper.narrow(obj_res);

            //System.out.println("SERVER RESOLVE OK MasterServerFactory");

            if (MasterServerFactoryRef == null)
            {
                    ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"NTM_MasterServer: ERROR narrowing MasterServerFactoryRef !!","Error Message",1);
            }
            status_mdm_server_server=true;
            try
            {
                    Thread.sleep(2);
            }catch(Exception e)
            {}

            mdm_server_serverRef = MasterServerFactoryRef.get_config_server();
	    //System.out.println("SERVER RESOLVE OK MasterServer");

	    if (mdm_server_serverRef == null)
            {
                    ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"NTM_MasterServer: ERROR narrowing mdm_server_serverRef !!.","Error Message",1);
            }

            status_mdm_server_server=true;
            return status_mdm_server_server;
        }
        catch (LaunchFailure lf)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"NTM_MasterServer: NTM_MasterServer: TIMEOUT calling MS."+" "+lf,"Error Message",1);
            System.out.println("Executable launch failure :- " + lf.reason );
            lf.printStackTrace(System.out);
            status_mdm_server_server=false;
            return status_mdm_server_server;
        }
        catch (Exception e)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"NTM_MasterServer: Error connecting to MS."+" "+e,"Error Message",1);
            e.printStackTrace(System.out);
            status_mdm_server_server=false;
            return status_mdm_server_server;
        }*/
    }


    public boolean openConnection_BuildJob()
    {
    	return true;
    	/*
        try
        {
            //System.out.println("Prima ORBInit BuildJob");
            org.omg.CORBA.ORB orb =org.omg.CORBA.ORB.init(local_applet,null);
            //System.out.println("Prima string_to_object BuildJob");
            org.omg.CORBA.Object objRes = orb.string_to_object(ns_ref);
            //System.out.println("Dopo string_to_object BuildJob" );

            NamingContextExt ncRefBuildJob = NamingContextExtHelper.narrow(objRes);

            NameComponent nc1 = new NameComponent("STS","");

            NameComponent nc2 = new NameComponent("NTM","");
            NameComponent nc3 = new NameComponent("BuildJob","");      //===> RDA

            NameComponent pathBuildJob [] ={nc1,nc2,nc3};

            //System.out.println("SERVER RESOLVE OK BuildJob");

            org.omg.CORBA.Object obj_res = ncRefBuildJob.resolve(pathBuildJob);
            mdm_server_job_serverRef = NTMJobActivatorHelper.narrow(obj_res);


            if (mdm_server_job_serverRef == null)
            {
                System.out.println("ERROR narrowing NTMJobActivator !!");
                status_BuildJob = false;
            }
            else
                status_BuildJob = true;
        }
        catch (Exception e)
        {
            System.out.println("===> catch - openConnection_BuildJob CORBAConnection.java "+e);
            status_BuildJob = false;

            e.printStackTrace(System.out);

            ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"BuildJob: Connection refused. "+e,"Error Message",1);
            return false;
        }

        return status_BuildJob;
        */
    }


    /**
     * Questo metodo permette di effettuare la connessione al Server remoto di configurazione "PsMonitorMasterServer".
     * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     *@return Ritorna true a connessione effettuata con esito positivo.
     */
    public boolean openConnection_psMonitorMasterServer()
    {
    	return true;
    	/*
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
            ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"PsMonitorMasterServer: Connection refused.\n"+e,"Error Message",1);
            return false;
        }
        */

    }


    public int putConfigurationDRonFEP(String nameSwitch, String nomefileIndex, int flag,DATA_DR[] drSequence)
    {
        int req = -1;
        if(status_PsMonitor)
        {
            try
            {
                /*System.out.println("fileNameConfIndex    -- -- -- --> "+nomefileIndex);

                for(int i=0; i<drSequence.length; i++)
                {
                    System.out.println("position    --> "+drSequence[i].position);
                    System.out.println("fieldtype   --> "+drSequence[i].fieldtype);
                    System.out.println("offset      --> "+drSequence[i].offset);
                    System.out.println("size        --> "+drSequence[i].size);
                    System.out.println("type_size   --> "+drSequence[i].type_size);
                    System.out.println("is_array    --> "+drSequence[i].is_array);
                    System.out.println("array_size  --> "+drSequence[i].array_size);
                    System.out.println("description --> "+new String(drSequence[i].description).trim());
                    System.out.println("isIndex     --> "+drSequence[i].isIndex);
                    System.out.println("indexByPlugin   --> "+drSequence[i].indexByPlugin);
                    System.out.println("indexBlockSize  --> "+drSequence[i].indexBlockSize);
                    System.out.println("indexRealTimeBlockSize  --> "+drSequence[i].indexRealTimeBlockSize);
                    System.out.println("startSize   --> "+drSequence[i].startSize);
                    System.out.println("indexSuffix --> "+new String(drSequence[i].indexSuffix).trim());
                    System.out.println("startTime   --> "+new String(drSequence[i].startTime).trim());
                    System.out.println("endTime     --> "+new String(drSequence[i].endTime).trim());
                    System.out.println();

                }*/


                //System.out.println("prima putConfigurationDRonFEP");
                //req = PsMonitorMasterServerRef.putConfigurationDRonFEP("All",nomefileIndex,0,drSequence);
                //System.out.println("dopo putConfigurationDRonFEP");
            }
            catch (Exception e)
            {
                    warningProblem(e);
                    e.printStackTrace();
            }
        }
        return req;
    }




public boolean storeConfiguration(ADAMS_COMPLETE_CONFIG ntmComplete,String ADAMSConfigName)
{
    boolean bRet=false;
    if (status_mdm_server_server)
    {
        try
        {
        	//System.out.println("CORBAConnection --> storeConfiguration ISTANZIO STRUTTURE Vuote per gli ALLARMI");
	       /*
        	ntmComplete.thresholdGeneratorSequence	= new THRESHOLD_GENERATOR[1];

                char[] description = set_String_toChar(" TEST LUCA ",mdm_server_server.eLONG_DESC_LEN);
                ntmComplete.thresholdGeneratorSequence[0] = new THRESHOLD_GENERATOR (0,
                                                                                    description,
                                                                                    false,
                                                                                    false,
                                                                                    false,
                                                                                    0);

                char[] shortDescription = set_String_toChar("",mdm_server_server.eSHORT_DESC_LEN);
                char[] longDescription = set_String_toChar("",mdm_server_server.eLONG_DESC_LEN);

        	ntmComplete.alarmGeneratorSequence = new ALARM_GENERATOR[1];
        	ntmComplete.alarmGeneratorSequence[0] = new ALARM_GENERATOR(0,
                                                                            shortDescription,
                                                                            longDescription,
                                                                            0,
                                                                            false,
                                                                            0);

                System.out.println("....... SIMULAZIONE per Piergiorgio .....");

        	int[] relationElements_1 = new int[mdm_server_server.eMAX_DIMENSION];
                relationElements_1[0] = 155;
                relationElements_1[1] = 43;
                relationElements_1[2] = 56;

                int[] relationElements_2 = new int[mdm_server_server.eMAX_DIMENSION];
                relationElements_2[0] = 393;
                relationElements_2[1] = 729;
                relationElements_2[2] = 56;


                int[] alarmHandlers    = new int[mdm_server_server.eMAX_ALARM_GENERATOR];

        	ntmComplete.alarmRelationSequence = new ALARM_RELATION[2];
        	ntmComplete.alarmRelationSequence[0] =  new ALARM_RELATION (0,
                                                                            description,
                                                                            relationElements_1,
                                                                            alarmHandlers,
                                                                            85,
                                                                            0,
                                                                            0);

                ntmComplete.alarmRelationSequence[1] =  new ALARM_RELATION (1,
                                                                            description,
                                                                            relationElements_2,
                                                                            alarmHandlers,
                                                                            85,
                                                                            0,
                                                                            0);


        	System.out.println("----");     	*/

                //System.out.println("PRIMA ---> CORBAConnection --> storeConfiguration FileName --> "+ADAMSConfigName);
                bRet = false;// mdm_server_serverRef.storeConfiguration(ntmComplete,ADAMSConfigName);
                //System.out.println("DOPO  ----> CORBAConnection --> storeConfiguration FileName --> "+ADAMSConfigName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            warningProblem(e);
        }
    }
    else
    {
        bRet=false;
    }
    return bRet;
}


    public boolean cloneOracleConf(String nameConf, String nameClone)
    {
        boolean bRet=false;
        if (status_mdm_server_server)
	{
            try
            {
                    bRet = false;//mdm_server_serverRef.cloneConf(nameConf,nameClone);
            }
            catch (Exception e)
            {
                    warningProblem(e);
                    e.printStackTrace();
            }
        }
	else
        {
            bRet=false;
        }
        return bRet;
    }

    public boolean backupOracleConf()
    {
        boolean bRet=false;
        if (status_mdm_server_server)
	{
            try
            {
                    bRet = false;//mdm_server_serverRef.backupConf();
            }
            catch (Exception e)
            {
                    warningProblem(e);
                    e.printStackTrace();
            }
        }
	else
        {
            bRet=false;
        }
        return bRet;
    }

    public boolean restoreOracleConf()
    {
        boolean bRet=false;
        if (status_mdm_server_server)
	{
            try
            {
                    bRet = false;//mdm_server_serverRef.restoreConf();
            }
            catch (Exception e)
            {
                    warningProblem(e);
                    e.printStackTrace();
            }
        }
	else
        {
            bRet=false;
        }
        return bRet;
    }





    public boolean verifyScript(SCRIPT_VALIDATE scriptData,ERROR_TEXTHolder errorText)
    {
        boolean bRet=false;
        String str="";
        if (status_mdm_server_server)
	{
            try
            {
                    bRet = mdm_server_serverRef.verifyScript(scriptData,errorText);
                    //System.out.println("bRet="+bRet);
            }
            catch (Exception e)
            {
                    warningProblem(e);
                    e.printStackTrace();
                    bRet=false;
            }
        }
	else
        {
            bRet=false;
        }

        return bRet;
    }


    public ADAMS_USER[] readUsers(String nomeProfilo)
    {
        AdamsUserSeqHolder AdamsUser = null;
        if (status_mdm_server_server)
        {
            try
            {
                AdamsUser = new AdamsUserSeqHolder();
                //System.out.println("mdm_server_serverRef.readUsers() Prima");
                boolean stat = mdm_server_serverRef.readUsers(nomeProfilo,AdamsUser,true);
                //System.out.println("mdm_server_serverRef.readUsers() dopo");
                if(stat==true)
                {
                        return (AdamsUser.value);
                }
            }
            catch(Exception e)
            {
                    warningProblem(e);
            }
        }
        return null;
    }


    public boolean writeUsers(ADAMS_USER[] ADAMSusers)
    {
        boolean stat = false;

        if (status_mdm_server_server)
        {
            try
            {
                //System.out.println("mdm_server_serverRef.writeUsers() Prima");
                stat = mdm_server_serverRef.writeUsers(ADAMSusers);
                //System.out.println("mdm_server_serverRef.writeUsers() dopo");
            }
            catch(Exception e)
            {
                    warningProblem(e);
                    e.printStackTrace();
            }
        }
        return stat;
    }

    public DIR_ENTRY[] directoryListing()
    {
        String str_altPath = "NTM_General/RepositoryPath";
        DirEntrySeqHolder dirList = null;

        if (status_mdm_server_server)
	{
            try
            {
                    dirList = new DirEntrySeqHolder();
                    //System.out.println("mdm_server_serverRef.directoryListing() Prima");
                    boolean answer   = mdm_server_serverRef.directoryListing(str_altPath,dirList);
                   // System.out.println("mdm_server_serverRef.directoryListing() dopo");

                    if(answer == true)
                        return (dirList.value);
            }
            catch (Exception e)
            {
                    warningProblem(e);
            }
        }

        return null;
    }



/////////////////////////////////////////////

    public boolean imd_Function(int op,S_FUNCTION sConfig)
    {
        boolean stat = false;
        try
        {
                if (status_Conf)
                {
                        //System.out.println("Chiamata imd_Function");
                        //stat = asp_entryRef.imd_Function(op,sConfig,AppLog);
                        count_imd_Function = 0;
                }
        }
        catch (Exception e)
        {
                if( count_imd_Function  < 3)
                {
                        System.out.println("------ imd_Function  Ritento Connessione al ConfigServer -----");
                        count_imd_Function++;
                        openConnection_config();
                        imd_Function(op,sConfig);
                        return false;
                }
                else
                {
                        //System.out.println("catch imd_Function()");
                        warningProblem(e);
                }
        }
        return(stat);
    }
    int count_imd_Function = 0;

    public boolean setConfiguration(S_FUNCTION sConfig)
    {
        boolean stat = false;
        try
        {
                if (status_Conf)
                {
                        System.out.println("Chiamata setConfiguration");
                        //stat = asp_entryRef.setConfiguration(-1,sConfig,AppLog);
                        count_setConfiguration = 0;
                }
        }
        catch (Exception e)
        {
                if( count_setConfiguration  < 3)
                {
                        System.out.println("------ setConfiguration  Ritento Connessione al ConfigServer -----");
                        count_setConfiguration++;
                        openConnection_config();
                        setConfiguration(sConfig);
                        return false;
                }
                else
                {
                        System.out.println("catch setConfiguration()");
                        warningProblem(e);
                }
        }
        return(stat);
    }
    int count_setConfiguration = 0;



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
				warningProblem ( e );
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

// ################# METODI SERVER DI CONFIGURAZIONE STS ################### //

    /**
    * Questo metodo preleva il profilo utente connesso, tramite chiamata al server di configurazione (server : STS_ConfigServer).
    *
    *@param login Stringa che identifica la Login assocciata all'utente.
    *@param password Stringa che identifica la Password legata alla login.
    *
    *@return Ritorna un oggetto USER_PROFILE.
    */
   /*public USER_PROFILE getUserProfile(String login,String password)
   {
        //System.out.println("asp_entryRef.getUserConfiguration()");
        USER_PROFILEHolder UserProfile = null;
        if (status_Conf)
	{
		boolean stat = false;
		try
		{
                        UserProfile = new USER_PROFILEHolder();
			stat = asp_entryRef.getUserConfiguration(STSBasicConfiguration.eID_MOD_DTM,login,password,false,UserProfile);
                }
		catch (Exception e)
		{
			warningProblem(e);
		}
		if(stat)
                {

                        return (UserProfile.value);
                }
          	else
			return null;
	}
	else
		return null;

    }*/



    /**
     * Questo metodo preleva tutti i  profili utente configurati, tramite chiamata al server di configurazione,
     * valorizzando la struttura globale STS_GlobalParam.local_ALLUSER.
     *@param idModule ID modulo (nel caso specifico l'ID identificativo per l'applicatico STSConfigurator)
     *@see STS_GlobalParam
     */
    /*public USER_PROFILE[] getAllUser(int idModule) // ID Module == ritorna tutti gli utenti abilitati al modulo specificato
    {
        USER_PROFILE local_ALLUSER[] = null;


        UserSTSSeqHolder ush = null;
    	if (status_Conf)
	{
		boolean stat = false;
		try
		{
			ush = new UserSTSSeqHolder();
                        //System.out.println("asp_entryRef.getAllUserList() Prima");
                        stat = asp_entryRef.getAllUserList(ush,idModule);
                        //System.out.println("asp_entryRef.getAllUserList() dopo");
		}
		catch (Exception e)
		{
			warningProblem(e);
		}

		if(stat)
		{
                    local_ALLUSER = ush.value;
		}
	}
        return local_ALLUSER;
    }*/

// ################# METODI SERVER DI BUIL JOB ################### //

    public STRUCT_USER[] getQueueList()
    {
    	if (status_BuildJob)
	{
                JobUserSeqHolder MDMJobUserQuee = new JobUserSeqHolder();

		boolean stat = false;
		try
		{
                       // System.out.println("mdm_server_job_serverRef.getQueueList Prima");
                        stat = mdm_server_job_serverRef.getUsersList(MDMJobUserQuee);
                        //System.out.println("mdm_server_job_serverRef.getQueueList dopo");
		}
		catch (Exception e)
		{
			warningProblem(e);
                        e.printStackTrace();
		}

		if(stat)
		{
                    return MDMJobUserQuee.value;
		}
	}
        return null;
    }

    public boolean putQueueList(STRUCT_USER[] mdm_job_user_queue )
    {
        boolean stat = false;
    	if (status_BuildJob)
	{
		try
		{
                       // System.out.println("mdm_server_job_serverRef.putQueueList Prima");
                        stat = mdm_server_job_serverRef.putUsersList(mdm_job_user_queue);
                       // System.out.println("mdm_server_job_serverRef.putQueueList dopo");
		}
		catch (Exception e)
		{
			warningProblem(e);
                        e.printStackTrace();
		}

	}
        return stat;
    }



// ################# END METODI SERVER DI BUIL JOB ################### //


    /**
     * Effettua lo shutDown del Server di configurazione (server : STS_ConfigServer).
     */
    public void shutDown_Conf()
    {
    	//System.out.println("shutDown_Conf");
    	/*if (status_Conf==true)
	{
		try
		{
			asp_entryRef.shutDown();
			System.out.println("asp_entryRef.shutDown()");
                }
		catch (Exception e)
		{
			warningProblem(e);
		}
	}*/

        if (status_mdm_server_server==true)
	{
		try
		{
			mdm_server_serverRef.shutDown();
			System.out.println("mdm_server_serverRef.shutDown()");
                }
		catch (Exception e)
		{
			warningProblem(e);
		}
	}

    }


    /*public void shutDown_psMonitorMasterServer()
    {
        if(status_PsMonitor)
        {
            try
		{
			PsMonitorMasterServerRef.shutDown();
			System.out.println("PsMonitorMasterServerRef.shutDown()");
                }
		catch (Exception e)
		{
			warningProblem(e);
		}
        }

    }*/

    private void warningProblem(Exception e)
    {
        /*JOptionPane warning = new JOptionPane();
        warning.showMessageDialog(local_applet,"Comunication problem... please start the application again. "+e,"Error Message",JOptionPane.ERROR_MESSAGE);
        */
        ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"Comunication problem... please start the application again. "+e,"Error Message",1,350,250);
    }

    private void warningProblem(String str)
    {
        /*JOptionPane warning = new JOptionPane();
        warning.showMessageDialog(local_applet,"Comunication problem... please start the application again. "+e,"Error Message",JOptionPane.ERROR_MESSAGE);
        */
        ADAMS_JD_Message op = new ADAMS_JD_Message((javax.swing.JFrame)null,true,str,"Error Message",1,350,250);
    }
    /**
     * Metodo che permette di trasformare una stringa in un array di caratteri.
     * @param str String il cui contenuto sarÃ¨ copiato nell'array di char.
     * @param length dimensione dell'array di char.
     * @return Un'array di char dalle dimensione passata "length" contenente i caratteri della stringa passata in input "str".
     */
    private static char[] set_String_toChar(String str, int length)
    {

    	char[] appo = str.toCharArray();
    	char[] appo1 = new char[length];

    	if (appo.length > length)
    	    return appo1;


    	for (int i=0; i<appo.length; i++)
    	    appo1[i] = appo[i];

    	for (int i=appo.length; i<length; i++)
    	    appo1[i] ='\0';

        appo1[length-1] ='\0';

    	return(appo1);
    }
}
