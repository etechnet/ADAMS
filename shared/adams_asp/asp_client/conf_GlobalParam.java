import java.util.*;
import java.awt.*;
import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_GlobalParam </b> </p>
 * 
 * La classe Ã¨ costituita da una serie di variabili e classi di utilizzo comune per l'applicativo, dalle
 * define alle strutture dati rilevate dalle classi generate dal compilatore IDL di Orbix, alle struttre locali di dati 
 * di uso comune ,rilvante Ã¨ l'istanza della classe conf_CORBAConnection che permette di effettuare tramite metodi proprietari 
 * dell'oggetto le chiamate ai server remoti tramite CORBA. 
 *
 *@see conf_GlobalParam
 *@see conf_CORBAConnection
 */

public class conf_GlobalParam
{
    public static String token      = "";
    public static String utenza     = "";
    public static String idm        = "";
   
    public static String HOSTNAME_CLIENT;
    public static String IP_CLIENT;
    public static int RUOLO = -1;
    public static java.util.Vector V_enabledClass = null;
    
    
    public static String MACHINE_NAME = "";
    public static String LOCATOR_PORT = "";
    
    // flag legata all'attivazione del server BUILD_JOB ---> this.getParameter("build_job") ---- "true" or "false"
    public static Boolean FLAG_BUILD_JOB = true;
    
    public static String oracle_SID = "";
    public static String oracle_port= "";
    public static int ID_LAYOUT_ROUTINGALARMS = 1;
    
    /**
     * Istanza della classe conf_CORBAConnection che permette di effettuare tramite metodi proprietari le chiamate ai server remoti tramite CORBA
     *@see conf_CORBAConnection
     */
    public static conf_CORBAConnection CORBAConn = null;
    public static S_CONFIG_LIB config_lib = null;
    

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
    
    public static int MAX_DESC_FUNCTION         = asp_usermanagement_server.eMAX_DESC_FUNCTION;
    public static int MAX_NAME_FUNCTION         = asp_usermanagement_server.eMAX_NAME_FUNCTION;
    
    public static String COMMON_USER_PROFILE_LOGON ="";
    
    /**
     * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
     * Variablile specifica per l'applicativo MONITOR, identifica il nome del profilo associato all'utente connesso.
     * </font></font></p> 	
     */ 
    public static String MONITOR_USER_PROFILE_LOGON ="";
    
    /**
     * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
     * Variablile specifica per l'applicativo IMAGE, identifica il nome del profilo associato all'utente connesso.
     * </font></font></p> 	
     */ 
    public static String IMAGE_USER_PROFILE_LOGON ="";
    
    /**
     * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
     * Variablile specifica che identifica l'applicativo IMAGE
     * </font></font></p> 	
     */
    public static int ID_MOD_IMAGE              = IMAGEServerConfiguration.eID_MOD_IMAGE;
    
    /**
     * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
     * Variablile specifica che identifica l'applicativo MONITOR
     * </font></font></p> 	
     */
    public static int ID_MOD_MONITOR                       = MONITORConfiguration.eID_MOD_MONITOR;
    
    /**
     * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
     * Variabile contenente il riferimento dell'Applet in running.
     * </font></font></p> 	
     */
    public static javax.swing.JApplet  rifApplet = null;
        
    //UserProfile
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
    * Costante identificativa del modulo STS Configurator
    */
    public static int ID_MOD_STSCONFIG                	= asp_usermanagement_server.eID_MOD_STSCONFIG;
    
    /**
    * Costante identificativa del modulo STS Configurator
    */
    public static int MAX_NAME_PROFILE                	= asp_usermanagement_server.eMAX_NAME_PROFILE;
    
    /**
    * Costante identificativa del modulo STS Configurator
    */
    public static int MAX_DESC_PROFILE                	= asp_usermanagement_server.eMAX_DESC_PROFILE;
    
    /**
    * Costante identificativa per la modalitÃ  d'inserimento dei dati, parametro utilizzato per le chiamate ai server remoti.
    */
    public static int OP_INSERT = asp_usermanagement_server.eOP_INSERT;
    /**
    * Costante identificativa per la modalitÃ  di modifica dei dati, parametro utilizzato per le chiamate ai server remoti.
    */
    public static int OP_MODIFY = asp_usermanagement_server.eOP_MODIFY;
   /**
    * Costante identificativa per la modalitÃ  di cancellazione dei dati, parametro utilizzato per le chiamate ai server remoti.
    */
    public static int OP_DELETE = asp_usermanagement_server.eOP_DELETE;
        
    
    //******************
    
    public static int DIM_PSWD_NOME_NODO = DBConfigurationServer.eDIM_PSWD_NOME_NODO;
    public static int DIM_PSWD_OGGETTO 	= DBConfigurationServer.eDIM_PSWD_OGGETTO;
    public static int DIM_PSWD_LOGIN 	= DBConfigurationServer.eDIM_PSWD_LOGIN;
    public static int DIM_PSWD_PASSWORD = DBConfigurationServer.eDIM_PSWD_PASSWORD;
    public static int DIM_PSWD_DATA_SCADENZA = DBConfigurationServer.eDIM_PSWD_DATA_SCADENZA;
    
    //******************
      
    
    /**
     * Costante indicante il numero massimo di cifre, applicato al numero di grafici per sessione (Max value 999).
     */
    public static int MAX_CHART_USER                    = 3; //Numero a tre cifre MAX 999
    
    /**
     * Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *<pre>
     *  Name Font = "Helvetica".
     *  Type Font = "BOLD".
     *  Size Font = "10".
     *</pre>
     */
    public static Font Font0                            = new java.awt.Font("Verdana", Font.BOLD, 10);
    
     /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "12".
     */
    public static java.awt.Font font_B12                = new java.awt.Font("Verdana",java.awt.Font.BOLD,12);
    
    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */
    public static java.awt.Font font_B11 = new java.awt.Font("Verdana",java.awt.Font.BOLD,11);
    // FONT
    /**
     * Oggetto di tipo Font utilizzato per la costruzioen dell'interfacci GUI:
     *<pre>
     *  Name Font = "Courier".
     *  Type Font = "BOLD".
     *  Size Font = "11".
     *</pre>
     */
    public static Font FontNOTType                      = new java.awt.Font("Courier", Font.BOLD, 11);
    
    /**
     * Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *<pre>
     *  Name Font = "Helvetica".
     *  Type Font = "BOLD".
     *  Size Font = "11".
     *</pre>
     */
    public static Font Font1_bold                           = new java.awt.Font("Verdana", Font.BOLD, 11);
    public static Font Font1                                = new java.awt.Font("Verdana", Font.BOLD, 11);
    public static Font Font1_plain                          = new java.awt.Font("Verdana", Font.PLAIN, 11);
    
     /**
     * Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *<pre>
     *  Name Font = "Helvetica".
     *  Type Font = "BOLD+ITALIC".
     *  Size Font = "13".
     *</pre>
     */
    public static Font Font2                            = new java.awt.Font("Verdana", Font.BOLD+Font.ITALIC, 13);
    
       
    
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
    
    
    public static void optionPanel(String msg,String title,int type)
    // Option panel
    {
            javax.swing.JOptionPane op = new javax.swing.JOptionPane();
            op.setBackground(new java.awt.Color(230,230,230));

            op.showMessageDialog(null, msg,title,type,null);
    }
    
    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type)
    {        
        conf_JD_Message op1 = new conf_JD_Message(Parent,true,msg,title,type);
    }
    
    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type,int width,int height)
    {
         conf_JD_Message op2 = new conf_JD_Message(Parent,true,msg,title,type,width,height);
    }
    
    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type)
    {        
        conf_JD_Message op1;
        
        if(Parent == null)
            op1 = new conf_JD_Message((javax.swing.JFrame)null,true,msg,title,type); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op1 = new conf_JD_Message(Parent,true,msg,title,type);
    }
    
    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type,int width,int height)
    {
        conf_JD_Message op2;
        
        if(Parent == null)
            op2 = new conf_JD_Message((javax.swing.JFrame)null,true,msg,title,type,width,height); // nel caso la javax.swing.JDialog == null genera eccezione
        else  
            op2 = new conf_JD_Message(Parent,true,msg,title,type,width,height);
    }
}
