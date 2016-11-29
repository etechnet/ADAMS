import javax.swing.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.SSM.*;
import net.etech.loadconfig.*;

/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> PROCESS MONITOR client </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Raffaele Ficcadenti (E-TECH) Created on 13/05/2005 - <a href="mailto:raffaele.ficcadenti@e-tech.net">raffaele.ficcadenti@e-tech.net</a></p>
 *
 *
 *
 * Questa  classe permette di effettuare le connessioni ai Server remoti tramite l'architettura standard CORBA.
 * Il client dell'applicativo, scritto interamente in JAVA, tramite questa architettura ï¿½ in grado di richiamare
 * i metodi di un oggetto server remoto sviluppato con un linguaggio differente.
 * rilascio IDM.
 */
public class SSM_CORBAConnection
{
	/**
	* Riferimento all'JApplet di start-up dell'applicativo.
	* </font></font></p>
	*/
	public JApplet local_applet                             = null;
	private asp_entry asp_entryRef                           = null;
        private PsMonitorMasterServer PsMonitorMasterServerRef  = null;
	private boolean status_Conf                             = false;
        private boolean status_PsMonitor                        = false;
        org.omg.CORBA.ORB orbSTSConf                            = null;
        org.omg.CORBA.ORB orbMonMasterServer                    = null;
        private boolean DEBUG                                   = false;
	private S_APP_LOG AppLog                                = null;

        private  String IP_CLIENT = "";
        private  String HOSTNAME_CLIENT = "";
        private  String LOGIN = "";
        private  String PASSWORD = "";

	//private ADAMS_loadConfig testLoadConfig = new ADAMS_loadConfig();

        private String ns_ref = "corbaloc::" + SSM_GlobalParam.machine_name + ":" + SSM_GlobalParam.locator_port + "/NameService";

	/**
	* Costruttore della classe.
	* Per istanziare un'oggetto SSM_CORBAConnection di Default.
	*/

        public void setInfoClient(String ipClient, String hostNameClient)
        {
            this.IP_CLIENT = ipClient;
            this.HOSTNAME_CLIENT = hostNameClient;
        }

	public SSM_CORBAConnection(String ipClient, String hostNameClient,String login,String password)
	{
            this.IP_CLIENT = ipClient;
            this.HOSTNAME_CLIENT = hostNameClient;
            this.LOGIN = login;
            this.PASSWORD = password;

            AppLog = new S_APP_LOG( SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_TIME_STAMP),
                                SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_IP_GENERATORE),
                                SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_HOSTNAME_GENERATORE),
                                SSM_GlobalParam.set_String_toChar( this.IP_CLIENT ,SSM_GlobalParam.MAX_IP_CLIENT),
                                SSM_GlobalParam.set_String_toChar( this.HOSTNAME_CLIENT ,SSM_GlobalParam.MAX_HOSTNAME_CLIENT),
                                SSM_GlobalParam.set_String_toChar( this.LOGIN ,SSM_GlobalParam.MAX_UTENZA_APPLICATIVA),
                                SSM_GlobalParam.set_String_toChar( this.LOGIN ,SSM_GlobalParam.MAX_UTENZA_CLIENT),
                                SSM_GlobalParam.set_String_toChar( "PROCESS MONITOR" ,SSM_GlobalParam.MAX_APPLICATIVO_CLIENT),
                                SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_AZIONE),
                                SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_OGGETTO),
                                SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_PARAMETRI),
                                SSM_GlobalParam.set_String_toChar( "" ,SSM_GlobalParam.MAX_ESITO),
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

// ################# Connessione con il sever di Configurazione dei processi ################### //

    /**
     * Questo metodo permette di effettuare la connessione al Server remoto di configurazione "STS_ConfigServer".
     * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     *@return Ritorna true a connessione effettuata con esito positivo.
     */
    public boolean openConnection_config()
    {
        try
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

                //System.out.println("SERVER NARROW OK ==> STSConfigServer");
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
            System.out.println("===> catch - openConnection_config ");
            status_Conf = false;
	    JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(local_applet,"STS_ConfigServer: Connection refused.\n"+e,"Error Message",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    // ################# Connessione con il sever di Configurazione ################### //

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


// ################# METODI SERVER DI CONFIGURAZIONE STS ################### //

	/**
	* Questo metodo preleva il profilo utente connesso, tramite chiamata al server di configurazione (server : STS_ConfigServer).
	*
	*@param login Stringa che identifica la Login assocciata all'utente.
	*@param password Stringa che identifica la Password legata alla login.
	*
	*@return Ritorna un oggetto USER_PROFILE.
	*/

    public boolean LoginUser(String login, String password, boolean disablePassCheck)
    {
        SSM_GlobalParam.RUOLO=-1;

        boolean FLAG_disablePassCheck=disablePassCheck;
        if((SSM_GlobalParam.idm).toUpperCase().equals("NO"))
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
			stat = asp_entryRef.LoginUser(SSM_GlobalParam.ID_MOD_PROCESSMONITOR,login,password,FLAG_disablePassCheck,AppLog,ruolo);
                        //System.out.println("LoginUser() = "+stat);
                }
		catch (Exception e)
		{
                        e.printStackTrace();
                        System.out.println("EXCEPTION "+e);
			warningProblem(e);
		}
		if(stat)
                {
                    SSM_GlobalParam.RUOLO = ruolo.value;
                    return true;
                }
          	else
                {
                    SSM_GlobalParam.RUOLO=-1;
                    return false;
                }
	}
	else
            return false;
    }


    public S_USER getUserConfiguration(String login,int idClass)
    {
        //System.out.println("asp_entryRef.getUserConfiguration()");
        S_USERHolder sUser = null;
        boolean flagIDM=false;

        if (status_Conf)
        {
            boolean stat = false;
            try
            {
                sUser = new S_USERHolder();
                //stat = asp_entryRef.getUserConfiguration(1,login,idClass,sUser,AppLog);
            }
            catch (Exception e)
            {
                if(count_getUserConfiguration < 3)
                {
                    System.out.println("------ getUserConfiguration() Ritento Connessione al STSConfigServerNEW -----");
                    count_getUserConfiguration++;
                    openConnection_config();
                    getUserConfiguration(login,idClass);
                    return null;
                }
                else
                {
                    System.out.println("catch getUserProfile()");
                    warningProblem(e);
                }
            }
            if(stat)
            {
                count_getUserConfiguration = 0;
                return (sUser.value);
            }
            else
                return null;
        }
        else
            return null;
    }
    private int count_getUserConfiguration = 0;

	/**
	* Metodo che tramite chiamata al server di configurazione permette di cambiare la password
	* della login associata ad un Utente (server : STS_ConfigServer).
	*
	*@param ID_User indice identificativo dell'utente,
	*@param old_Password Stringa della password da sostituire.
	*@param password Stringa della nuova password.
	*
	*@return ritorna true se l'operazione di cambio password ha esito positivo, altrimentoti ritorna false.
	*/
	public boolean changeUserPASSWD(int ID_User,String old_Password,String password)
	{
            if (status_Conf)
            {
                boolean stat=false;
                try
                {
                        stat=false; //asp_entryRef.changeUserPASSWD(ID_User,old_Password,password);
                }
                catch (Exception e)
                {
                    if( count_changeUserPASSWD < 3)
                    {
                        System.out.println("------ changeUserPASSWD() Ritento Connessione STSConfigServer -----");
                        count_changeUserPASSWD++;
                        openConnection_config();
                        changeUserPASSWD(ID_User,old_Password,password);
                        return false;
                    }
                    else
                    {
                        System.out.println("catch changeUserPASSWD()");
                        warningProblem(e);
                    }
                }
                count_changeUserPASSWD = 0;
                return  stat;
            }
            return false;
	}
	int count_changeUserPASSWD = 0;


// ################# METODI SERVER DI CONFIGURAZIONE PsMonitorMasterServer ################### //
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
                stat = PsMonitorMasterServerRef.LaunchPsMonitorMasterServer(CentralSeq);
            }
            catch(Exception e)
            {
                if(count_Launch_PsMonitorMasterServer <3)
                {
                    System.out.println("------ Launch_PsMonitorMasterServer() Ritento Connessione al PsMonitorMasterServer -----");
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

    /**
     * Il metodo fa il refresh dei processi del nodo sul quale viene invocato.
     * @param nameSwitch Nome della centrale d'interesse
     * @return un array contenente una struttura di tipo ProcesDetail, contenente le specifiche che caraterizzano un processo.
     */
    public ProcesDetail[] refresh_MonitorProcess(String nameSwitch,int flag)
    {
        ProcesDetailSeqHolder ProcesDetailSeq = null;
        if(status_PsMonitor)
        {
            boolean stat = false;
            try
            {
                ProcesDetailSeq = new ProcesDetailSeqHolder();
                stat = PsMonitorMasterServerRef.refreshMonitorProcess(nameSwitch,flag,ProcesDetailSeq);
            }
            catch(Exception e)
            {
                if(count_refreshMonitorProcess <3)
                {
                    System.out.println("------ refresh_MonitorProcess() Ritento Connessione al PsMonitorMasterServer -----");
                    count_refreshMonitorProcess++;
                    openConnection_psMonitorMasterServer();
                    refresh_MonitorProcess(nameSwitch,flag);
                    return null;
                }
                else
                {
                    System.out.println("catch refresh_MonitorProcess()");
                    warningProblem(e);
                }
            }
            if(stat)
            {
                count_refreshMonitorProcess = 0;
                return (ProcesDetailSeq.value);
            }
            else
                return null;
        }
        else
            return null;
    }
    private int count_refreshMonitorProcess = 0;

    /**
     * Il metodo ritorna il file di help relativo al processo selezionato
     * @param nomeProcesso nome del processo sul quale si vuol vedere il log.
     * @param logProcesso log associato al processo selezionato.
     * @param flagDETAIL descrimina il tipo di dettaglio che viene richiesto sul processo
     * flagDETAIL=HELP   ==> dettaglio di help,
     * flagDETAIL=MACRO  ==> dettaglio del Macro Allarme
     * @return ritorna <true> se nell'operazione di refresh non si sono verificati errori <false> altrimenti.
     */
    public logProcess[] getDeteilProcess(int flag, String nameSwitch, String nameProcess)
    {
        LogProcessSeqHolder LogProcess = null;
        if(status_PsMonitor)
        {
            boolean stat = false;
            try
            {
                LogProcess = new LogProcessSeqHolder();
                stat = PsMonitorMasterServerRef.getDeteilProcess(flag,nameSwitch,nameProcess,LogProcess);
            }
            catch(Exception e)
            {
                if(count_getDeteilProcess <3)
                {
                    System.out.println("------ getDeteilProcess() Ritento Connessione al PsMonitorMasterServer -----");
                    count_getDeteilProcess++;
                    openConnection_psMonitorMasterServer();
                    getDeteilProcess(flag,nameSwitch,nameProcess);
                    return null;
                }
                else
                {
                    System.out.println("catch getDeteilProcess()");
                    warningProblem(e);
                }
            }

            if(stat)
            {
                count_getDeteilProcess = 0;
                return (LogProcess.value);
            }
            else
                return null;
        }
        else
            return null;
    }
    int count_getDeteilProcess = 0;


    public int resetField(String nameSwitch, int field, int flagTIPORESET, String nomeProcesso)
    {
        int stat = 0;
        if(status_PsMonitor)
        {
            try
            {
                stat =  PsMonitorMasterServerRef.resetField(nameSwitch,field,flagTIPORESET,nomeProcesso);
            }
            catch(Exception e)
            {
                if(count_resetField <3)
                {
                    System.out.println("------ resetField() Ritento Connessione al PsMonitorMasterServer -----");
                    count_resetField++;
                    openConnection_psMonitorMasterServer();
                    resetField(nameSwitch,field,flagTIPORESET,nomeProcesso);
                    return 0;
                }
                else
                {
                    System.out.println("catch resetField()");
                    warningProblem(e);
                }
            }
        }
        return stat;
    }
    int count_resetField = 0;


    public logProcess[] get_HistoryProcess(int flag_work,int single_all_process,boolean flag,String nameSwitch, String nameProcess,int day)
    {
        LogProcessSeqHolder LogProcess = null;
        if(status_PsMonitor)
        {
            int stat = 0;
            try
            {
                LogProcess = new LogProcessSeqHolder();
                stat = PsMonitorMasterServerRef.getHistoryProcess(flag_work,single_all_process,flag,nameSwitch,nameProcess,day,LogProcess);
            }
            catch(Exception e)
            {
                if(count_get_HistoryProcess <3)
                {
                    System.out.println("------ get_HistoryProcess() Ritento Connessione al PsMonitorMasterServer -----");
                    count_get_HistoryProcess++;
                    openConnection_psMonitorMasterServer();
                    get_HistoryProcess(flag_work,single_all_process,flag,nameSwitch,nameProcess,day);
                    return null;
                }
                else
                {
                    System.out.println("catch get_HistoryProcess()");
                    warningProblem(e);
                }
            }

            if(stat == 1)
            {
                count_get_HistoryProcess = 0;
                return (LogProcess.value);
            }
            else
                return null;
        }
        else
            return null;
    }
    int count_get_HistoryProcess = 0;

    public boolean KillOrbixProces(String nameSwitch, int flag, int num_pid)
    {
        if(status_PsMonitor)
        {
            boolean stat=false;
            try
            {
                stat = PsMonitorMasterServerRef.KillOrbixProcess(nameSwitch,flag,num_pid);
            }
            catch(Exception e)
            {
                if(count_KillOrbixProcess <3)
                {
                    System.out.println("------ KillOrbixProcess() Ritento Connessione al PsMonitorMasterServer -----");
                    count_KillOrbixProcess++;
                    openConnection_psMonitorMasterServer();
                    KillOrbixProces(nameSwitch,flag,num_pid);
                    return false;
                }
                else
                {
                    System.out.println("catch get_HistoryProcess()");
                    warningProblem(e);
                }
            }

            if(stat == true)
            {
                count_KillOrbixProcess = 0;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    int count_KillOrbixProcess = 0;

    public String get_SystemTime(String nameSwitch)
    {
        LogProcessSeqHolder str_DATA = null;
        if(status_PsMonitor)
        {
            boolean stat = false;
            try
            {
                str_DATA = new LogProcessSeqHolder();
                stat = PsMonitorMasterServerRef.getSystemTime(nameSwitch,str_DATA);
            }
            catch(Exception e)
            {
                if(count_get_SystemTime <3)
                {
                    System.out.println("------ get_SystemTime() Ritento Connessione al PsMonitorMasterServer -----");
                    count_get_SystemTime++;
                    openConnection_psMonitorMasterServer();
                    get_SystemTime(nameSwitch);
                    return "";
                }
                else
                {
                    System.out.println("catch get_SystemTime()"+e);
                }
            }

            if(stat)
            {
                count_get_SystemTime = 0;
                return (new String(str_DATA.value[0].descr).trim());
            }
            else
                return "";
        }
        else
            return "";
    }
    int count_get_SystemTime = 0;


    //refreshConfigProcess
    int count_refreshConfigProcess = 0;
    public CS_INFO_PROC[] refreshConfigProcess(int flag, String nameSwitch)
    {
        CS_INFO_PROCSeqHolder InfoProc = null;
        if(status_PsMonitor)
        {
            boolean stat = false;
            try
            {
                InfoProc = new CS_INFO_PROCSeqHolder();
                stat = PsMonitorMasterServerRef.refreshConfigProcess(nameSwitch,flag,InfoProc);
            }
            catch(Exception e)
            {
                if(count_refreshConfigProcess <3)
                {
                    System.out.println("------ count_refreshConfigProcess() Ritento Connessione al PsMonitorMasterServer -----");
                    count_refreshConfigProcess++;
                    openConnection_psMonitorMasterServer();
                    refreshConfigProcess(flag,nameSwitch);
                    return null;
                }
                else
                {
                    System.out.println("catch refreshConfigProcess()");
                    warningProblem(e);
                }
            }

            if(stat)
            {
                count_refreshConfigProcess = 0;
                return (InfoProc.value);
            }
            else
                return null;
        }
        else
            return null;
    }

    //refreshConfigBloc
    int count_refreshConfigBloc = 0;
    public CS_BLOCK_LOG[] refreshConfigBloc(int flag, String nameSwitch)
    {
        CS_BLOCK_LOGSeqHolder InfoBlock = null;
        if(status_PsMonitor)
        {
            boolean stat = false;
            try
            {
                InfoBlock = new CS_BLOCK_LOGSeqHolder();
                stat = PsMonitorMasterServerRef.refreshConfigBloc(nameSwitch,flag,InfoBlock);
                System.out.println("STAT="+stat);
                System.out.println("STAT="+InfoBlock);
            }
            catch(Exception e)
            {
                if(count_refreshConfigBloc <3)
                {
                    System.out.println("------ refreshConfigBloc() Ritento Connessione al PsMonitorMasterServer -----");
                    count_refreshConfigBloc++;
                    openConnection_psMonitorMasterServer();
                    refreshConfigBloc(flag,nameSwitch);
                    return null;
                }
                else
                {
                    System.out.println("catch refreshConfigBloc()");
                    warningProblem(e);
                }
            }

            if(stat)
            {
                count_refreshConfigBloc = 0;
                //System.out.println(InfoBlock.value.length);
                return (InfoBlock.value);
            }
            else
                return null;
        }
        else
            return null;
    }

    //writeModifyProcess

    int count_writeModifyProcess = 0;
    int writeModifyProcess(String nomeProcesso,int flag, String nameSwitch,CS_INFO_PROC configProcess)
    {
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.writeModifyProcess(nomeProcesso,nameSwitch,flag,configProcess);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_writeModifyProcess <3)
                {
                    System.out.println("------ writeModifyProcess() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_writeModifyProcess++;
                    openConnection_psMonitorMasterServer();
                    writeModifyProcess(nomeProcesso,flag,nameSwitch,configProcess);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_writeModifyProcess()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_writeModifyProcess = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    //boolean
    //deleteProcess(String nameSwitch,int flag,tring nomeProcesso,org.omg.CORBA.IntHolder errore);

    int count_deleteProcess = 0;
    int deleteProcess(int flag, String nameSwitch,String nomeProcesso)
    {
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.deleteProcess(nameSwitch,flag,nomeProcesso);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_deleteProcess <3)
                {
                    System.out.println("------ deleteProcess() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_deleteProcess++;
                    openConnection_psMonitorMasterServer();
                    deleteProcess(flag,nameSwitch,nomeProcesso);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_deleteProcess()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_deleteProcess = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    //delete blocklog
    int count_deleteBlockLog = 0;
    int deleteBlockLog(int flag, String nameSwitch,int idBlock,int idFep)
    {
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.deleteBlockLog(nameSwitch,flag,idBlock,idFep);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_deleteBlockLog <3)
                {
                    System.out.println("------ deleteBlockLog() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_deleteBlockLog++;
                    openConnection_psMonitorMasterServer();
                    deleteBlockLog(flag,nameSwitch,idBlock,idFep);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_deleteBlockLog()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_deleteBlockLog = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    //SystemStart
    int count_SystemStart = 0;
    int SystemStart(int flag, String nameSwitch)
    {
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.SystemStart(nameSwitch,flag);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_SystemStart <3)
                {
                    System.out.println("------ SystemStart() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_SystemStart++;
                    openConnection_psMonitorMasterServer();
                    SystemStart(flag,nameSwitch);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_SystemStart()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_SystemStart = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    //SystemStart
    int count_SystemStop = 0;
    int SystemStop(int flag, String nameSwitch)
    {
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.SystemStop(nameSwitch,flag);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_SystemStop <3)
                {
                    System.out.println("------ SystemStop() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_SystemStop++;
                    openConnection_psMonitorMasterServer();
                    SystemStop(flag,nameSwitch);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_SystemStop()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_SystemStop = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    private int count_writeModifyLog = 0;
    /**
     * Questo metodo richiama il master server il quale richiamerï¿½ il metodo sull'opportuno config server
     * sull fep specificato nel paramtero nameSwitch.
     * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     * @param flag parametro inpostato per default a MODIFICA_FILE_LOG.
     * @param nameSwitch nome della centrale sulla quale deve essere ricostruito il file di log.
     * @param configBloclList array contenente elementi di tipo <CS_BLOCK_LOG>.Contiene tutti i messaggi di log del blocco da modificare.
     * @return Ritorna un valore positivo se l'operazione ï¿½ andata a buon fine.
     */
    public int writeModifyLog(int flag, String nameSwitch,CS_BLOCK_LOG[] configBloclList)
    {
        for(int i=0;i<SSM_GlobalParam.idOccupati.length;i++)
        {
            SSM_GlobalParam.idOccupati[i]=false;
        }
        CentralSeqHolder appo=new CentralSeqHolder();
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.writeModifyLog(nameSwitch,flag,configBloclList,appo);
                if(appo!=null)
                {
                    for(int i=0;i<appo.value.length;i++)
                    {
                        //System.out.println("free="+appo.value[i].IdCentrale);
                        SSM_GlobalParam.idOccupati[appo.value[i].IdCentrale]=true;
                    }
                }
                //System.out.println("STAT writeModifyLog="+stat);
            }
            catch(Exception e)
            {
                if(count_writeModifyLog <3)
                {
                    System.out.println("------ writeModifyLog() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_writeModifyLog++;
                    openConnection_psMonitorMasterServer();
                    writeModifyLog(flag,nameSwitch,configBloclList);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_writeModifyLog()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_writeModifyLog = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    private int count_createLogFile = 0;

    /**
     * Questo metodo richiama il master server e ricrea il file di log se sul fep non ï¿½ presente.
     * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     * @param flag parametro inpostato per default a RICREA_FILE_LOG.
     * @param nameSwitch nome della centrale sulla quale deve essere ricostruito il file di log.
     * @return Ritorna un valore positivo se l'operazione ï¿½ andata a buon fine.
     */
    public int createLogFile(int flag, String nameSwitch)
    {
        int stat = -1;
        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.createLogFile(nameSwitch,flag);
                //System.out.println("STAT writeModifyLog="+stat);
            }
            catch(Exception e)
            {
                if(count_createLogFile <3)
                {
                    System.out.println("------ createLogFile() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_createLogFile++;
                    openConnection_psMonitorMasterServer();
                    createLogFile(flag,nameSwitch);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_createLogFile()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_createLogFile = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }


    // chiamate per ACQPebMGR
    private int refreshACQPebMgr = 0;
    public ACQ_PEB_MGR refreshACQPebMGR(String nomeSwitch)
    {
        if(DEBUG)
        {
            System.out.println("PsMonitorMasterServerRef.refreshACQPebMgr("+nomeSwitch+")");
        }
        ACQ_PEB_MGRHolder acqPebMgr = null;

        if (status_PsMonitor)
        {
            int stat = -1;
            try
            {
                acqPebMgr = new ACQ_PEB_MGRHolder();
                stat = PsMonitorMasterServerRef.refreshACQPebMGR(nomeSwitch,0,acqPebMgr);
            }
            catch (Exception e)
            {
                if(refreshACQPebMgr < 3)
                {
                    System.out.println("------ getUserProfile() Ritento Connessione al PsMonitorMasterServerRef -----");
                    refreshACQPebMgr++;
                    openConnection_psMonitorMasterServer();
                    refreshACQPebMGR(nomeSwitch);
                    return null;
                }
                else
                {
                    System.out.println("catch refreshACQPebMgr()");
                    warningProblem(e);
                }
            }
            if(stat<0)
            {
                refreshACQPebMgr = 0;
                return null;
            }
            else
                return acqPebMgr.value;
        }
        else
            return null;
    }

    //writeModifyProcess

    int count_writeACQPebMGR = 0;
    int writeACQPebMGR(String nameSwitch,boolean cleanAll,ACQ_PEB_MGR acqPebMgr)
    {
        int stat = -1;

        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.writeACQPebMGR(nameSwitch,cleanAll,acqPebMgr);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_writeACQPebMGR <3)
                {
                    System.out.println("------ writeACQPebMGR() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_writeACQPebMGR++;
                    openConnection_psMonitorMasterServer();
                    writeACQPebMGR(nameSwitch,cleanAll,acqPebMgr);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_writeACQPebMGR()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_writeACQPebMGR = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    /**
     *  Metodi per GarbageServer
     */

    private int refreshGarbage = 0;
    public GARBAGE_INFO[] refreshGarbage(String nomeSwitch)
    {
        if(DEBUG)
        {
            System.out.println("PsMonitorMasterServerRef.refreshGarbage("+nomeSwitch+")");
        }
        GARBAGE_INFOSeqHolder GARBAGE_INFOSeq = null;

        if (status_PsMonitor)
        {
            int stat = -1;
            try
            {
                GARBAGE_INFOSeq = new GARBAGE_INFOSeqHolder();
                stat = PsMonitorMasterServerRef.refreshGarbage(nomeSwitch, GARBAGE_INFOSeq);
            }
            catch (Exception e)
            {
                if(refreshGarbage < 3)
                {
                    System.out.println("------ refreshGarbage() Ritento Connessione al PsMonitorMasterServerRef -----");
                    refreshGarbage++;
                    openConnection_psMonitorMasterServer();
                    refreshGarbage(nomeSwitch);
                    return null;
                }
                else
                {
                    System.out.println("catch refreshGarbage()");
                    warningProblem(e);
                }
            }
            if(stat<0)
            {
                refreshGarbage = 0;
                return null;
            }
            else
                return GARBAGE_INFOSeq.value;
        }
        else
            return null;
    }

    //writeModifyProcess

    int count_writeGarbage = 0;
    int writeGarbage(String nameSwitch, boolean modify, GARBAGE_INFO[] garbageInfo)
    {
        int stat = -1;

        if(status_PsMonitor)
        {

            try
            {
                stat = PsMonitorMasterServerRef.writeGarbage(nameSwitch, modify, garbageInfo);
                //System.out.println("STAT="+stat);
            }
            catch(Exception e)
            {
                if(count_writeGarbage <3)
                {
                    System.out.println("------ writeGarbage() Ritento Connessione al PsMonitorMasterServer -----"+e);
                    count_writeGarbage++;
                    openConnection_psMonitorMasterServer();
                    writeGarbage(nameSwitch, modify, garbageInfo);
                    return stat;
                }
                else
                {
                    System.out.println("--> catch count_writeGarbage()");
                    warningProblem(e);
                }
            }

            if(stat==0)
            {
                count_writeGarbage = 0;
                return 0;
            }
            else
                return stat;
        }
        else
            return stat;
    }

    private void warningProblem(Exception e)
    {
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(local_applet,"Comunication problem... please start the application again.\n"+" "+e,"Error Message",JOptionPane.ERROR_MESSAGE);
    }

}
