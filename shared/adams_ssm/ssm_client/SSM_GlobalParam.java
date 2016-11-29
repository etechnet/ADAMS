/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> MONITOR CLIENT</font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (HP Invent) Created on 24/06/2003 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 * La classe  ï¿½ costituita da una serie di variabili e classi di utilizzo comune per l'applicativo, dalle
 * define alle strutture dati rilevate dalle classi generate dal compilatore IDL di  Orbix, alle struttre locali di dati
 * di uso comune , come l'istanza della classe SSM_CORBAConnection che permette di effettuare tramite metodi proprietari
 * dell'oggetto le chiamate ai server remoti tramite CORBA.
 * rilascio IDM
 */
import java.awt.Toolkit;
import java.awt.Dimension;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.SSM.*;

public class SSM_GlobalParam
{
    
    public static SSM_DBConnection db_Connection = null;
    
    public static int ORBIX             = 1;
    public static int NO_ORBIX          = 0;
    /**
     * Array di booleani indicante len lengthcentrali in cui un determinato blocco ï¿½ presente.
     * Questo array viene utilizzato durante la fase di inserimento di nuovi blocchi di messagi.
     */
    public static boolean[] idOccupati=new boolean[15];

    /**
     *Costruttore di default della classe.
     */
    public SSM_GlobalParam()
    {

    }

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Helvetica". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_P11 = new java.awt.Font("helvetica",java.awt.Font.PLAIN,11);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Helvetica". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_V11 = new java.awt.Font("Courier",java.awt.Font.PLAIN,12);

   /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Helvetica". Type Font = "BOLD". Size Font = "11".
     */
    public static java.awt.Font font_B11 = new java.awt.Font("helvetica",java.awt.Font.BOLD,11);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Helvetica". Type Font = "BOLD". Size Font = "12".
     */
    public static java.awt.Font font_B12 = new java.awt.Font("helvetica",java.awt.Font.BOLD,12);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_P12 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,12);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */
    public static java.awt.Font font_B10 = new java.awt.Font("Verdana",java.awt.Font.BOLD,10);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_P10 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,10);

     /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */
    public static java.awt.Font font_B9 = new java.awt.Font("Verdana",java.awt.Font.BOLD,9);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "8".
     */
    public static java.awt.Font font_B8 = new java.awt.Font("Verdana",java.awt.Font.BOLD,8);

    /**
     *Variabile Stringa contenente il nome della macchina remota dove viene effettuata la connesione CORBA.
     */
    public static String machine_name = "";
    /**
     *Variabile Stringa contenente il numero della porta dove viene effettuata la connesione CORBA.
     */
    public static String locator_port = "";

    /**
     * Costante utilizzata dal ProcessMonitorServer, richiede la lista dei macro processi(se presenti).
     */
    public static int PS_ORBIX          = ProcessMonitorServer.ePS_ORBIX;

    /**
     * Costante utilizzata dal ProcessMonitorServer, richiede la lista dei macro processi(se presenti).
     */
    public static int PS_ORBIX_HELP    = ProcessMonitorServer.ePS_ORBIX_HELP;

    /**
     * Costante utilizzata dal ProcessMonitorServer, richiede la lista dei macro processi(se presenti).
     */
    public static int MACRO             = ProcessMonitorServer.eMACRO;

    /**
     * Costante utilizzata dal ProcessMonitorServer, richiede lengthinformazioni aggiuntive di un processo.
     */
    public static int HELP              = ProcessMonitorServer.eHELP;

    /**
     * Costante utilizzata dal ProcessMonitorServer, richiede lo stato di un singolo processo.
     */
    public static int SINGLE_PROCESS    = ProcessMonitorServer.eSINGLE_PROCESS;

    /**
     * Costante utilizzata dal ProcessMonitorServer richiede lo stato di tutti i singolo processo.
     */
    public static int ALL_PROCESS       = ProcessMonitorServer.eALL_PROCESS;

    /**
     * Lunghezza massima della stringa di login.
     */
    public static int MAX_LOGIN_LEN                     = asp_usermanagement_server.eMAX_LOGIN_LEN;

    /**
     * Lunghezza massima della stringa password.
     */
    public static int MAX_PASSWORD_LEN                  = asp_usermanagement_server.eMAX_PASSWORD_LEN;
    /**
     * Lunghezza massima della stringa descrizione utente
     */
    public static int MAX_DESC_USER_LEN                 = asp_usermanagement_server.eMAX_DESC_USER_LEN;

    /**
     * Lunghezza massima della stringa nome utente
     */
    public static int MAX_NOMEUTENTE_LEN                = asp_usermanagement_server.eMAX_NOMEUTENTE_LEN;

    /**
     *Istanza della classe SSM_CORBAConnection che permette di effettuare tramite metodi proprietari le chiamate ai server remoti tramite CORBA.
     */
    public static SSM_CORBAConnection CORBAConn  = null;

    /**
     * Metodo che converte una stringa in un array di char di dimensioni specificate nel parametro length, se length ï¿½ maggiore della lunghezza
     * della stringa allora l'array verrï¿½ riempito con spazi vuoti alla fine.
     * @param str stringa da convertire.
     * @param length lunghezza dell'array di char.
     * @return ritorna un array di char contenente la stringa str.
     */
    public static char[] set_String_toChar(String str, int length)
    {
    	char[] appo = str.toCharArray();
    	char[] appo1 = new char[length];

    	if (appo.length > length)
    	    return appo1;

    	for (int i=0; i<appo.length; i++)
        {
    	    appo1[i] = appo[i];
        }
    	for (int i=appo.length; i<length; i++)
        {
            appo1[i] =0;
        }
    	return(appo1);
    }

    public static char[] set_String_toChar_WhitSpace(String str, int length)
    {
        char[] appo = str.toCharArray();
        char[] appo1 = new char[length];
        if (appo.length > length)
            return appo1;
        for (int i=0; i<appo.length; i++)
        {
            appo1[i] = appo[i];
        }
        for (int i=appo.length; i<length; i++)
        {
            appo1[i] = ' ';
        }
        /*appo1[length-1] = 0;*/
        return(appo1);
    }

    /**
     * Elenco di stringhe associate hai tipo di processo attualmente disponibili,
     * sono: "Process always running","Awake five minutes process","Awake fifteen minutes process","Awake one hour process","Awake one day process","Awake hour daily process"
     */
    public static String[] typeProc = {"Process always running","Awake five minutes process","Awake fifteen minutes process","Awake one hour process","Awake one day process","Awake hour daily process","Undefined"};

    /**
     * Variabile di tipo Stringa contenente la password passata all'applet dal sistema di autenticazione IDM.
     */
    public static String token      = "";
    /**
     * Variabile di tipo Stringa contenente l'utenza passata all'applet dal sistema di autenticazione IDM.
     */
    public static String utenza     = "";

    public static String idm        = "";

    public static String rmp3i      = "";

    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type)
    {
        JD_Message op1 = new JD_Message(Parent,true,msg,title,type);
    }

    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type,int width,int height)
    {
         JD_Message op2 = new JD_Message(Parent,true,msg,title,type,width,height);
    }

    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type)
    {
        JD_Message op1;

        if(Parent == null)
            op1 = new JD_Message((javax.swing.JFrame)null,true,msg,title,type); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op1 = new JD_Message(Parent,true,msg,title,type);
    }

    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type,int width,int height)
    {
        JD_Message op2;

        if(Parent == null)
            op2 = new JD_Message((javax.swing.JFrame)null,true,msg,title,type,width,height); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op2 = new JD_Message(Parent,true,msg,title,type,width,height);
    }

    public static void setCenteredFrame(javax.swing.JFrame parent,int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        parent.setSize(width,height);
        parent.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }

    public static String HOSTNAME_CLIENT;
    public static String IP_CLIENT;
    public static int RUOLO = -1;

    public static int MAX_TIME_STAMP            = asp_usermanagement_server.eMAX_TIME_STAMP;
    public static int MAX_IP_GENERATORE         = asp_usermanagement_server.eMAX_IP_GENERATORE;
    public static int MAX_HOSTNAME_GENERATORE   = asp_usermanagement_server.eMAX_HOSTNAME_GENERATORE;
    public static int MAX_IP_CLIENT             = asp_usermanagement_server.eMAX_IP_CLIENT;
    public static int MAX_HOSTNAME_CLIENT       = asp_usermanagement_server.eMAX_HOSTNAME_CLIENT;
    public static int MAX_UTENZA_APPLICATIVA    = asp_usermanagement_server.eMAX_UTENZA_APPLICATIVA;
    public static int MAX_UTENZA_CLIENT         = asp_usermanagement_server.eMAX_UTENZA_CLIENT;
    public static int MAX_APPLICATIVO_CLIENT    = asp_usermanagement_server.eMAX_APPLICATIVO_CLIENT;
    public static int MAX_AZIONE                = asp_usermanagement_server.eMAX_AZIONE;
    public static int MAX_OGGETTO               = asp_usermanagement_server.eMAX_OGGETTO;
    public static int MAX_PARAMETRI             = asp_usermanagement_server.eMAX_PARAMETRI;
    public static int MAX_ESITO                 = asp_usermanagement_server.eMAX_ESITO;

    public static int ID_MOD_PROCESSMONITOR           = asp_usermanagement_server.eID_MOD_PMON;

}
