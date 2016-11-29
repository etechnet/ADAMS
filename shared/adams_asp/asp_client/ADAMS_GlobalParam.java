/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> MONITOR CLIENT</font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (HP Invent) Created on 24/06/2003 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 * La classe Ã¨ costituita da una serie di variabili e classi di utilizzo comune per l'applicativo, dalle
 * define alle strutture dati rilevate dalle classi generate dal compilatore IDL di  Orbix, alle struttre locali di dati
 * di uso comune , come l'istanza della classe PM_CORBAConnection che permette di effettuare tramite metodi proprietari
 * dell'oggetto le chiamate ai server remoti tramite CORBA.
 *
 */

import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;


public class ADAMS_GlobalParam
{
    //------------------Usati anche nel VERIFY LIMITS
    // ANALISI
    public static int _MAX_RELATION           = mdm_server_server.eMAX_RELATION;           // - RELAZIONI ABILITATE
    public static int _MAX_ANALYSIS_REPORTS   = mdm_server_server.eMAX_ANALYSIS_REPORTS;    // - REPORT ABILITATI

    // CONTATORI
    public static int _CNT_NUM                = mdm_server_server.eCNT_NUM;                 // - MAX CONTATORI PER KIT

    // RELAZIONI
    public static int _MAX_RESTRICTIONS       = mdm_server_server.eMAX_RESTRICTIONS;        // - MAX RELAZIONI ABILITATE - MAX RESTRIZIONI OBBLIGATORIE
    public static int _NEXTLEVEL_RELATIONS    = mdm_server_server.eNEXTLEVEL_RELATIONS;     // - MAX RELAZIONI LIVELLO SUCC.
    public static int _MAX_KEY_LENGTH         = mdm_server_server.eMAX_KEY_LENGTH;          // - MAX DIMENSIONE DELLA RELAZIONE IN BYTE
    //Relazione FREE Format
    public static int _RELATION_FREEFORMAT_ID = mdm_server_server.eRELATION_FREEFORMAT_ID ; // ID Relation FREEFORMAT

    // SCRIPT
    public static int _SCRIPT_MAX_VAR         = mdm_server_server.eSCRIPT_MAX_VAR;         // - MAX LISTA VARIABILI
    public static int _SCRIPT_MAX_TEXT        = mdm_server_server.eSCRIPT_MAX_TEXT;        // - MAX LINEE TESTO SCRIPT

    // TRAFFIC ELEMENT
    public static int _MAX_OPTIONS            = mdm_server_server.eMAX_OPTIONS;             // - MAX OPZIONI IN VALUE LIST
    public static int _MAX_AGGREGATE_RESTR    = mdm_server_server.eMAX_AGGREGATE_RESTR;     // - MAX LISTA AGGREGATE
    public static int _MAX_EXCEPTIONS         = mdm_server_server.eMAX_EXCEPTIONS;          // - MAX LISTA EVENTI GUI
    public static int _VALSHIFTER_MAX         = mdm_server_server.eVALSHIFTER_MAX;          // - MAX VALORI SHIFTER
    public static int _MAX_TE_SCRIPTS         = mdm_server_server.eMAX_TE_SCRIPTS;          // - SCRIPTS

    //------------------ end anche nel VERIFY LIMITS

    public static int ID_MOD_MDM            = mdm_configuration_server.eID_MOD_MDM;
    public static int MAX_ENABLE_CONFIGS    = mdm_server_server.eMAX_ENABLE_CONFIGS;
    public static int MAX_CONFIG_FILENAME   = mdm_server_server.eMAX_CONFIG_FILENAME;
    public static int USR_LOGIN_LEN         = mdm_server_server.eUSR_LOGIN_LEN;
    public static int USR_PASSWD_LEN        = mdm_server_server.eUSR_PASSWD_LEN;

    public static int MAX_DIMENSION     = mdm_server_server.eMAX_DIMENSION;
    public static int LONG_DESC_LEN     = mdm_server_server.eLONG_DESC_LEN;
    public static int SHORT_DESC_LEN    = mdm_server_server.eSHORT_DESC_LEN;

    public static javax.swing.JFrame    JFRAME_TAB      = null;
    public static javax.swing.JFrame    JFRAME_WIZARD   = null;
    public static javax.swing.JDialog   JFRAME_SCRIPT   = null;
    public static javax.swing.JDialog   ADAMS_JD_Report  = null;

    public static javax.swing.JTextField Global_lastTime = null;
    public static String        strGLOBAL                   = "";
    public static boolean       clonato                     = false;
    //********** Script ********//
    public static final int     SCRIPT_TE                   = 0;
    public static final int     SCRIPT_REPORT               = 1;
    public static final int     SCRIPT_ALARM                = 2; // Abilitazione JRbutton

    //********** Report ********//
    public static final int     REPORT_SCHEMA               = 0;
    public static final int     REPORT_OBJECT_EDITOR        = 1;
    public static int           ID_ANALISI_SELEZIONATA      = -1; //Ã¨ stata messa per evitare un giro infinito di variabili tra i vari form.
    public static String        TAG_SELEZIONATO             = "";
    public static int           INSERT_NEW_ELEMENT          = 0; //Ã¨ stata messa per evitare un giro infinito di variabili tra i vari form.
    public static int           MODIFY_ELEMENT              = 0; //Ã¨ stata messa per evitare un giro infinito di variabili tra i vari form.
    public static String        lastReportName              = "";
    //********** TYPE HELP ********//

    public static final String  type_help[]         = {"DR","TE","GUI","Generic","Trigger","Directions","Counter Index","Counter Type","Percent Of","Result Script type"};

    public static final int     TYPE_HELP_DR           = 0;
    public static final int     TYPE_HELP_TE            = 1;
    public static final int     TYPE_HELP_GUI           = 2;
    public static final int     TYPE_HELP_GENERIC       = 3;
    public static final int     TYPE_HELP_TRIGGER       = 4;
    public static final int     TYPE_HELP_DIRECTIONS    = 5;
    public static final int     TYPE_COUNTER_INDEX      = 6;
    public static final int     TYPE_COUNTER_TYPE       = 7;
    public static final int     TYPE_PERCENT_OF         = 8;
    public static final int     TYPE_RESULT_SCTRIPT     = 9;

    //utilizzata per la tabella di help, ALL_CONF inerente a tutte lengthconfigurazioni
    public static final String     ALL_CONF_forhelp = "ALL_CONF";
    //**********************************//

    // Rappresenta il traffic Element per contenere le ghost Relation
    public static final int     TE_GHOST_POSIZIONE_ELEMENTO = -2;

    // Operazioni Thread classe ADAMS_Vector_TAB_CONFIG
    public static final int     read_DR                = 1;
    public static final int     read_TE                 = 2;
    public static final int     DeleteRowOreder         = 3;
    public static final int     DeleteNestedAggRest     = 4;
    public static final int     DeleteNestedException   = 5;
    public static final int     DeleteRelationWithID    = 6;
    public static final int     DeleteAnalysisWithID    = 7;
    public static final int     read_GHOST              = 8;
    public static final int     read_DR_TE_for_INDEX   = 9;
    public static final int     read_ONLY_TRUE_INDEX    = 10;
    //**********************************//



   private static String ACTUAL_LOCK_TABLE  = "";
   private static String LOCAL_LOCK_TABLE   = "";

   public static String HOSTNAME_CLIENT;
   public static String IP_CLIENT;

   public static CORBAConnection STSConn=null;

    /**
     *Costruttore di default della classe.
     */
    public ADAMS_GlobalParam()
    {
    }

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_P11 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,11);
    public static java.awt.Font font_P12 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,12);
   /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */

    public static java.awt.Font font_B11 = new java.awt.Font("Verdana",java.awt.Font.BOLD,11);

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
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "12".
     */
    public static java.awt.Font font_B12 = new java.awt.Font("Verdana",java.awt.Font.BOLD,12);

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

    public static ADAMS_OracleConnection connect_Oracle = null;


     public static void optionPanel(String msg,String title,int type)
    // Option panel
    {
            javax.swing.JOptionPane op = new javax.swing.JOptionPane();
            op.setBackground(new java.awt.Color(230,230,230));
            op.showMessageDialog(null, msg,title,type,null);
    }


    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type)
    {
        ADAMS_JD_Message op1 = new ADAMS_JD_Message(Parent,true,msg,title,type);
    }

    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type,int width,int height)
    {
         ADAMS_JD_Message op2 = new ADAMS_JD_Message(Parent,true,msg,title,type,width,height);
    }

    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type)
    {
        ADAMS_JD_Message op1;

        if(Parent == null)
            op1 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,msg,title,type); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op1 = new ADAMS_JD_Message(Parent,true,msg,title,type);
    }

    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type,int width,int height)
    {
        ADAMS_JD_Message op2;

        if(Parent == null)
            op2 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,msg,title,type,width,height); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op2 = new ADAMS_JD_Message(Parent,true,msg,title,type,width,height);
    }

    /*public static void optionPanel(String msg,String title,int type)
    // Option panel
    {
        ADAMS_JD_Message op1 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,msg,title,type);
    }

    public static void optionPanel(String msg,String title,int type,int width,int height)
    // Option panel
    {
        ADAMS_JD_Message op2 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,msg,title,type,width,height);
    }*/



    public static void setLAST_MODIFICATION_TIME(String tipoConfigurazione)
    {
        String str_updateTIME = "update tab_info_config set LAST_MODIFICATION_TIME = (select  DATE_FORMAT(CURRENT_TIMESTAMP, '%a %d-%M-%Y %H:%i:%S')  from dual) ";
                str_updateTIME += " where TIPO_DI_CONFIGURAZIONE = '"+tipoConfigurazione+"'";

        int Answer_Ins = connect_Oracle.Operation(str_updateTIME);
        if(Answer_Ins == 1)
        {

            if(Global_lastTime != null)
            {
                String selLastTIME = "select LAST_MODIFICATION_TIME from tab_info_config where TIPO_DI_CONFIGURAZIONE='"+tipoConfigurazione+"'";
                String str_time = connect_Oracle.Query_s(selLastTIME);

                if(str_time.compareTo("") != 0)
                {
                    //System.out.println("LAST_MODIFICATION_TIME aggiornato "+str_time);
                    Global_lastTime.setText(str_time);
                }
                //else
                    //System.out.println("NO TEXT LAST_MODIFICATION_TIME aggiornato ");
            }
        }
       // else
            //System.out.println("NO LAST_MODIFICATION_TIME aggiornato ");

    }

    // True UNLOCK --- false LOCK
    public static boolean ctrl_LOCK_TABLE(boolean unblock)
    {
        //String FormatDataLock = "DD/Mon/YYYY HH24:MI (SSSSS)";
        String FormatDataLock = "%d/%M/%Y %H:%i (%S)";
        String queryFor_LockTable = "select distinct LOCK_TABLE from tab_info_config";
        ACTUAL_LOCK_TABLE = connect_Oracle.Query_s(queryFor_LockTable);
        //System.out.println("STR LOCK 1="+queryFor_LockTable+" ="+ACTUAL_LOCK_TABLE);
        if((ACTUAL_LOCK_TABLE.length() == 0)&&(LOCAL_LOCK_TABLE.length() == 0))
        {
            String str_NewLOCK = "update tab_info_config set LOCK_TABLE = (select DATE_FORMAT(CURRENT_TIMESTAMP, '"+FormatDataLock+"' ) from dual)";
            int Answer_Ins = connect_Oracle.Operation(str_NewLOCK);
            //System.out.println("STR LOCK 2 ="+str_NewLOCK);
            if(Answer_Ins > 0)
            {
                ACTUAL_LOCK_TABLE = connect_Oracle.Query_s(queryFor_LockTable);
                LOCAL_LOCK_TABLE = ACTUAL_LOCK_TABLE;
                //System.out.println("Inizializzo LOCK --> "+ACTUAL_LOCK_TABLE);
                return true;
            }
            else
            {

                String CTRL_TAB_INFO_CONFIG = "select count(*) from tab_info_config";

                if(connect_Oracle.Query_int(CTRL_TAB_INFO_CONFIG) == 0)
                    System.out.println("Attenzione: tabellatab_info_config vuota");

                System.out.println("Error Init LOCK");
                return false;
            }

        }
        else
        {
            String msg = "TABLE LOCKED!! \nConfigurator in read-only mode.";
            if(ACTUAL_LOCK_TABLE.length() != 0)
                msg += "\nLock time: ";

            if(ACTUAL_LOCK_TABLE.compareTo(LOCAL_LOCK_TABLE) == 0)
            {
                //System.out.println("ACTUAL_LOCK_TABLE="+ACTUAL_LOCK_TABLE);
                //System.out.println("LOCAL_LOCK_TABLE="+LOCAL_LOCK_TABLE);
                //System.out.println("CRTL LOCK_TABLE.. OK PROSEGUI..");
                return true;
            }
            else
            {
                if(unblock == true) //Sblocca
                {
                    ADAMS_JD_Message op3 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"TABLE LOCKED!!\nLock time: "+ ACTUAL_LOCK_TABLE+ "\nDo you wish to unlock ?","Warning",7);
                    int Yes_No_1 = op3.getAnswer();
                    if(Yes_No_1 == 1)
                    {
                        String str_NewLOCK = "update tab_info_config set LOCK_TABLE = (select DATE_FORMAT(CURRENT_TIMESTAMP, '"+FormatDataLock+"') from dual)";
                        int Answer_Ins = connect_Oracle.Operation(str_NewLOCK);
                        if(Answer_Ins > 0)
                        {
                            ACTUAL_LOCK_TABLE = connect_Oracle.Query_s(queryFor_LockTable);
                            LOCAL_LOCK_TABLE = ACTUAL_LOCK_TABLE;
                            ADAMS_JD_Message op5 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,"TABLE UNLOCKED!!\nNew lock time: "+ACTUAL_LOCK_TABLE,"Warning",8);
                            return true;
                        }
                        else
                        {
                            System.out.println("Error LOCK TABLE update NewLOCK ");
                            return false;
                        }
                    }
                    else
                    {
                        LOCAL_LOCK_TABLE = "NO_UNLOCK";
                        ADAMS_JD_Message op6 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,msg+ACTUAL_LOCK_TABLE,"Warning",8,300,200);
                        return false;
                    }
                }
                else
                {
                    LOCAL_LOCK_TABLE = "NO_UNLOCK";
                    ADAMS_JD_Message op7 = new ADAMS_JD_Message((javax.swing.JFrame)null,true,msg+ACTUAL_LOCK_TABLE,"Warning",8,300,200);
                    return false;
                }
            }
        }
    }

    /*public static void clearLockTable()
    {
        if(ACTUAL_LOCK_TABLE.compareTo(LOCAL_LOCK_TABLE) == 0)
        {
             System.out.println("clearLockTable()");
             String str_clearLOCK = "update tab_info_config set LOCK_TABLE =''";
             connect_Oracle.Operation(str_clearLOCK);
        }
    }*/

    public static void clearLockTable_onExit()
    {
         //System.out.println(" ACTUAL_LOCK_TABLE "+ACTUAL_LOCK_TABLE);
         //System.out.println(" LOCAL_LOCK_TABLE "+LOCAL_LOCK_TABLE);

         //System.out.println("clearLockTable_onExit()");
         String str_clearLOCK = "update tab_info_config set LOCK_TABLE ='' where LOCK_TABLE='"+LOCAL_LOCK_TABLE+"'";
         connect_Oracle.Operation(str_clearLOCK);
         ACTUAL_LOCK_TABLE  ="";
         LOCAL_LOCK_TABLE   ="";
    }



    public static void Force_LockTable()
    {
        //System.out.println("---- Force_LockTable() ---- pulisco LOCK ---- ");
        String str_clearLOCK = "update tab_info_config set LOCK_TABLE =''";
        connect_Oracle.Operation(str_clearLOCK);

        LOCAL_LOCK_TABLE   = "";
        ctrl_LOCK_TABLE(false);
        //System.out.println("REIMPOSTO LOCK...");
    }

    public static char[] set_String_toChar(String str, int length)
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

    public static String strLastFileName        = "";

}