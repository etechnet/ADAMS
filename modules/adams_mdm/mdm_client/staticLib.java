/*
 * staticLib.java
 *
 * Created on 16 aprile 2001, 19.08
 *
 * @author  Raffaele Ficcadenti
 *          raffo76@j-linux.com
 *
 * @version 1.0
 */

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;



public class staticLib
{
    
        //******************************************
        // parametri per STS Registries Client
	//******************************************
	        
        public static boolean STS_RC_GRANT_READ = false;
        public static boolean STS_RC_GRANT_EDIT = false;
        
        public static String nameFunction_Read      = "Registries Read";
        public static String nameFunction_Read_Edit = "Registries Read and Edit";
        
        //******************************************
    
    
    
        //******************************************
        // parametri per drill-down Pivot
	//******************************************
	public static int dd_TipoAnalisi     = -1; // TipoAnalisi
    	public static int dd_idReportSchema  = -1; // idReportSchema
        //******************************************
        
	//******************************************
        // parametri per connessione DB
	//******************************************
	public static String oracle_SID     ="";
    	public static String oracle_port    ="";
    
        public static JApplet applet_corrente = null;
        public static int ID_CLIENT = -1;
        public static int oldIDREL=-1;
        public static boolean disegnaApply;
	//******************************************
        // metodi di servizio
	//******************************************
        public static String machine_name = "";
        public static String locator_port = "";
        public static String path_report = "";
        public static String path_report_job = "";
        public static String token      = "";
        public static String utenza     = "";
        public static String idm        = "";
        public static String rmp3i      = "";		// TODO: temporaneo !!
        
        // In eleborazione report la flag è true ed impedisce il TIME_OUT della sessione
        public static boolean FLAG_ELABORATION = false;
        
        public static javax.swing.JTabbedPane jTabbe_Tot = null;
        
        public static MDM_JF_Frame frame_principale = null;
        
        public static void resetSTRUC_PARAMS()
        {
            descRelation="";
                    AbsoluteValue = "false";
                richiestaPronta=false;
                struc_paramsCURRENT.Relation=-1;
                struc_paramsCURRENT.AnalysisType=0;
        }

        public static void info()
        {
                System.out.println("NTM Network Traffic Matrix");
                System.out.println();
                System.out.println("@author:  	Ficcadenti Raffaele & Beltrame Luca");
                System.out.println("		e-Tech 18-Novembre-2004");
                System.out.println("@version: 	2.0");
        }

        public static void optionPanel(String msg,String title,int type)
        // Option panel
        {
                JOptionPane op = new JOptionPane();
                op.setBackground(new java.awt.Color(230,230,230));

                op.showMessageDialog(null, msg,title,type,null);
        }

	public static void optionPanel1(String msg,String title,int type,ImageIcon img)
        // Option panel
        {
                JOptionPane op = new JOptionPane();
                op.setBackground(new java.awt.Color(230,230,230));
                op.showMessageDialog(frame_principale, msg,title,type,img);
        }

	public static int confirmPanel(String msg,String title,int type1,int type2)
        // Option panel
        {
                JOptionPane op = new JOptionPane();
                op.setBackground(new java.awt.Color(230,230,230));
                return op.showConfirmDialog(null, msg,title,type1,type2);
        }


	public static int DataToInteger(String str)
        {
            try
            {
               //System.out.println("DataToInteger:"+str.substring(0,8));
               //System.out.println("DataToInteger1"+Integer.valueOf(str.substring(0,8)).intValue());
               return Integer.valueOf(str.substring(0,8)).intValue();
            }catch(Exception e)
            {
               //System.out.println("ERROR <DataToInteger>");
               return -1;
            }

        }

        public static String formatData(String str) //nello controllare
        {
            // formattazione della data nel formato: gg-mm-aaaa
            try
            {
                String appo;
                char[] elencoGiorni=str.toCharArray();
                char[] anno=new char[4];
                char[] mese=new char[2];
                char[] giorno=new char[2];

                anno[0]=elencoGiorni[0];
                anno[1]=elencoGiorni[1];
                anno[2]=elencoGiorni[2];
                anno[3]=elencoGiorni[3];
                mese[0]=elencoGiorni[4];
                mese[1]=elencoGiorni[5];
                giorno[0]=elencoGiorni[6];
                giorno[1]=elencoGiorni[7];
                appo=new String(giorno)+"-"+month[(Integer.valueOf(new String(mese))).intValue()]+"-"+new String(anno);
                return appo;
            }catch(Exception ex)
            {
                System.err.println("Error in <formatData>"+ex);
                //ex.printStackTrace();
                return null;
            }
        }

	public static int validateSTRUCT_PARAMS(STRUCT_PARAMS param,TrafficElement localDataElement,BufferRestrizioni br)
         {
            String str="\n";
            for(int i=0;i<struc_paramsCURRENT.Filters.length;i++)
            {
                REST_INFO r=struc_paramsCURRENT.Filters[i];
                //System.out.println("<STATIC_LIB> IdTE: "+r.Element);
            }

            int c=0;
            try
            {
                boolean[] vinc = new boolean[vincoli.length];
                while(vincoli[c] != 0)
                {
                    boolean vincoloTrovato = false;
                    int vTemp = vincoli[c];

                    //System.out.println("Vincolo: "+(new String(localDataElement.get_Traffic_Element(vTemp).shortDescription)).trim());

                    if(br.isPresentEB(vTemp)==null)
                        vincoloTrovato=false;
                    else
                        vincoloTrovato=true;

                    vinc[c]=vincoloTrovato;
                    c++;
                }

                String strVincoli="\n";
                for(int i=0;i<c;i++)
                {
                    //System.out.println("vincolo["+vincoli[i]+"]: "+vinc[i]);
                    if(vinc[i]==false)
                    {
                        strVincoli=strVincoli+"<"+(new String(localDataElement.get_Traffic_Element(vincoli[i]).longDescription)).trim()+">\n";
                    }
                }

                if(!strVincoli.equals("\n"))
                {
                    optionPanel("Missing restriction: "+strVincoli, "Error",JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            }catch(Exception e)
            {
                //System.out.println("Non ci sono vincoli");
            }
                //***********************************************
                //	Controllo i vincoli
                //***********************************************

            if(param.ElaborationData==null || struc_paramsCURRENT.Relation==-1)
            {
                if(param.ElaborationData==null)
                    str=str+"<DATE MISSING>\n";
                if(struc_paramsCURRENT.Relation==-1)
                    str=str+"<RELATION MISSING>\n";

                optionPanel("Invalid request: "+str, "Error",JOptionPane.ERROR_MESSAGE);
                return -1; //richiesta non valida -1: Data Mancante
            }
            else return 1;
         }


         public static boolean validateSTRUCT_JOB(STRUCT_JOB job)
         {
             boolean flag=true;
             String str="";

             if(startDATE.getText().equals(""))
             {
                 str=str+"<Start Date> \n";
                 flag=false;
             }
             if(endDATE.getText().equals(""))
             {
                 str=str+"<End Date> \n";
                 flag=false;
             }
             if(!flag)
             {
                 staticLib.optionPanel("Please fill mandatory fields:\n"+str, "Error",JOptionPane.ERROR_MESSAGE);
                 return false;
             }

             return true;
         }
         
        public static void ordinaStr(Vector vett)
        {
            //System.out.println("ordinaDate");
            int len = vett.size();
            for(int i=0;i<len-1;i++)
            {
                int min=i;
                for(int j=i+1;j<len;j++)
                {
                    String str1 = (String)vett.elementAt(j);
                    String str2 = (String)vett.elementAt(min);

                    if(str1.compareTo(str2)<0)
                        min=j;
                }
                String appo = (String)vett.elementAt(i);
                String appo1 = (String)vett.elementAt(min);          

                vett.removeElementAt(i);
                vett.insertElementAt(appo1,i);
                vett.removeElementAt(min);
                vett.insertElementAt(appo,min);    
            }
        }

    public static STRUCT_PARAMS cloneSTRUCT_PARAMS(STRUCT_PARAMS structParam_IN)
    {
        if(structParam_IN != null)
        {
            STRUCT_PARAMS structParam_CLONE = new STRUCT_PARAMS();

            structParam_CLONE.Name = new char[structParam_IN.Name.length];
            for(int i=0; i<structParam_CLONE.Name.length; i++)
                    structParam_CLONE.Name[i] = structParam_IN.Name[i];

            structParam_CLONE.AnalysisType     = structParam_IN.AnalysisType;
            structParam_CLONE.NetworkRealTime = structParam_IN.NetworkRealTime;
            structParam_CLONE.DataType        = structParam_IN.DataType;
            structParam_CLONE.OutputType      = structParam_IN.OutputType;

            structParam_CLONE.ElaborationData = new DATE_INFO[structParam_IN.ElaborationData.length];
            for(int d=0; d<structParam_CLONE.ElaborationData.length; d++)
            {
                structParam_CLONE.ElaborationData[d] = new DATE_INFO();
                structParam_CLONE.ElaborationData[d].dateString = new char[structParam_IN.ElaborationData[d].dateString.length];
                for(int s=0; s<structParam_CLONE.ElaborationData[d].dateString.length; s++)
                    structParam_CLONE.ElaborationData[d].dateString[s] = structParam_IN.ElaborationData[d].dateString[s];        
            }

            structParam_CLONE.Fep = new int[structParam_IN.Fep.length];
            for(int c=0; c<structParam_CLONE.Fep.length; c++)
                structParam_CLONE.Fep[c] = structParam_IN.Fep[c];

            structParam_CLONE.Relation           = structParam_IN.Relation;
            structParam_CLONE.RelationDirection  = structParam_IN.RelationDirection;

            structParam_CLONE.Filters = new REST_INFO[structParam_IN.Filters.length];
            for(int r=0; r<structParam_CLONE.Filters.length; r++)
            {
                structParam_CLONE.Filters[r] = new REST_INFO();
                structParam_CLONE.Filters[r].Level    = structParam_IN.Filters[r].Level;
                structParam_CLONE.Filters[r].Element  = structParam_IN.Filters[r].Element;

                structParam_CLONE.Filters[r].Value = new int[structParam_IN.Filters[r].Value.length];
                for(int v=0; v<structParam_CLONE.Filters[r].Value.length; v++)
                    structParam_CLONE.Filters[r].Value[v] = structParam_IN.Filters[r].Value[v];
                
                int size_a1 = structParam_IN.Filters[r].AsciiValue.length;
                structParam_CLONE.Filters[r].AsciiValue = new char[size_a1][];
                for(int a1=0; a1<size_a1; a1++)
                {
                    int size_a2 = structParam_IN.Filters[r].AsciiValue[a1].length;
                    structParam_CLONE.Filters[r].AsciiValue[a1] = new char[size_a2];
                    for(int a2=0; a2<size_a2; a2++)
                        structParam_CLONE.Filters[r].AsciiValue[a1][a2] = structParam_IN.Filters[r].AsciiValue[a1][a2];
                    
                    //System.out.println("structParam_CLONE.Filters[r].AsciiValue["+a1+"] --> "+new String(structParam_CLONE.Filters[r].AsciiValue[a1]) );
                    //System.out.println("structParam_IN.Filters[r].AsciiValue["+a1+"] --> "+new String(structParam_IN.Filters[r].AsciiValue[a1]) );
                }

                structParam_CLONE.Filters[r].Operator  = structParam_IN.Filters[r].Operator;
                structParam_CLONE.Filters[r].Priority   = structParam_IN.Filters[r].Priority;
            }

            structParam_CLONE.OppositeRestriction   = structParam_IN.OppositeRestriction;
            structParam_CLONE.FlagSort              = structParam_IN.FlagSort;
            structParam_CLONE.ElementToSort         = structParam_IN.ElementToSort;
            structParam_CLONE.Ascendent            = structParam_IN.Ascendent;
            structParam_CLONE.Reserved              = structParam_IN.Reserved;
            structParam_CLONE.SingleRelation        = structParam_IN.SingleRelation;
            structParam_CLONE.IsPercent             = structParam_IN.IsPercent;
            structParam_CLONE.HexValue              = structParam_IN.HexValue;
            structParam_CLONE.isScheduled           = structParam_IN.isScheduled;
            structParam_CLONE.idUser                = structParam_IN.idUser;
            structParam_CLONE.idJob                 = structParam_IN.idJob;

            structParam_CLONE.Description = new char[structParam_IN.Description.length];
            for(int d=0; d<structParam_CLONE.Description.length; d++)
                structParam_CLONE.Description[d] = structParam_IN.Description[d];

            structParam_CLONE.idReportSchema = structParam_IN.idReportSchema;

            structParam_CLONE.user = new char[structParam_IN.user.length];
            for(int u=0; u<structParam_CLONE.user.length; u++)
                structParam_CLONE.user[u] = structParam_IN.user[u];

            structParam_CLONE.user_ip = new char[structParam_IN.user_ip.length];
            for(int u1=0; u1<structParam_CLONE.user_ip.length; u1++)
                structParam_CLONE.user_ip[u1] = structParam_IN.user_ip[u1];

            structParam_CLONE.freeFormat = structParam_IN.freeFormat;

            structParam_CLONE.ffRelation = new int[structParam_IN.ffRelation.length];
            for(int ff=0; ff<structParam_CLONE.ffRelation.length; ff++)
                structParam_CLONE.ffRelation[ff] = structParam_IN.ffRelation[ff];
      
            return structParam_CLONE;
        }
        System.out.println("Errore cloneSTRUCT_PARAMS(null)");
        return null;
    }
        
        
	//***********************************************************************
	// 	 ENVIRONMENT
	//***********************************************************************

	//public static String[] month			= {"","Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre"};
	public static String[] month			= {"","January","February","March","April","May","June","July","August","September","October","November","December"};
	//public static String[] output			= {"Screen","Web Server","Print"};
	public static String[] centrali;
	public static int[] idCentrali;
	public static boolean[] ghostAnalisi;
	public static String[] sortBy			= {"None","Bids","Abr","Abandoned","Pre-Abandoned","No Trunk","Internal C. Congestion","External Congestion","Other","Conversation Erlang","Occupation Erlang"}; // 0=Bids
	public static String[] relDirection		= {"Direct","Invers","Both"};
	public static String[] tipoDati			= {"On Line","Stored"};
	public static String[] distribuzioneOraria	= {"None","AM","PM"};

	//relazioni
        public static String descRelation		= "";
        public static String AbsoluteValue              = new String("false");

	//Riferimento CORBA al MS
	public static int NUMERO_CENTRALI;
	public static String[] status			= {"To be submitted","Submitted","Done","Error"};
	public static String[] modalita			= {"","Last n day of week","Weekly cumulative","Daily","","","","Real time"};

	// FONTS
        public static Font fontA8			= new Font ("Verdana", Font.BOLD, 9);
        public static Font fontA9			= new Font ("Verdana", Font.BOLD, 9);
        public static Font fontA10a			= new Font ("Verdana", Font.BOLD, 10);
	public static Font fontA10			= new Font ("Verdana", Font.BOLD, 11);
	public static Font fontA11			= new Font ("Verdana", Font.BOLD, 11);
	public static Font fontA12			= new Font ("Verdana", Font.BOLD, 12);
	public static Font fontA13			= new Font ("Verdana", Font.BOLD+Font.ITALIC, 13);

	public static Font fontH10			= new Font ("Verdana", 1, 10);
	public static Font fontH12			= new Font ("Verdana", 1, 12);

	//ENVIROMENT SCHEDULE
        public static MDM_JP_Jobs selectJobs1;

	public static boolean richiestaPronta		= false;
	public static int RichiestaValida;  		//Indica se la richiesta ï¿½ valida.

        // PER JOB ///////////
	public static String startDATE_ghost		= "";
	public static String endDATE_ghost		= "";
        public static JLabel startDATE;
        public static JLabel l;
        public static JLabel endDATE;
        ////////////////////

	// ENVIRONMET Restriction
	public static int[] idGhostRel;
	public static int[] vincoli;

	// ENVIRONMENT GLOBAL
	public static JTabbedPane Tabella;
	public static STRUCT_PARAMS struc_paramsCURRENT;
	public static mdm_server_server mdm_server_serverRef = null;
        public static int MAX_PIVOT_KEYS = mdm_server_serverRef.eMAX_PIVOT_KEYS;
        public static int MAX_DIMENSION = mdm_server_serverRef.eMAX_DIMENSION;
	public static mdm_server_job_server MDM_JobActivatorRef = null;
        
        //ID FREE Relation
        public static int RELATION_FREEFORMAT_ID = mdm_server_serverRef.eRELATION_FREEFORMAT_ID;

	public static String DATA_CONIGURAZIONE_CORRENTE="";
	public static int MAX_COLOR=16;
	public static boolean utentePrivileggiato;

	public static boolean dallaTabJpJobs;
	public static javax.swing.JLabel titoloFrontEnd;
	public static javax.swing.JRadioButton jrbMERGE;
	public static MDM_JF_Frame mainFRAME;
  	public static Vector jsl=new Vector();
	public static Vector jslTE;
  	public static final int MAX_VALUE=10;
        
        public static int MAX_KEY_LENGTH = mdm_server_serverRef.eMAX_KEY_LENGTH;
        

	// CORBA
	public static connCORBA CORBA = null;
	public static String[] operator={" "," == "," > "," < "," >= "," <= "};
	public static boolean NTenabled=false;

    public static MDM_JP_SelectRelations selectRelations1;
    public static MDM_JP_SelectRestrictions selectRestrictions1;
    public static MDM_JP_SelectSort sortPanel1;
    public static MDM_JP_SelectReport selectReport1;
    public static MDM_JP_Summary selectSummary1;
    public static MDM_JP_RestrictionsHelp rHelp1;

    public static boolean flagPass=false;
    public static boolean flagCalendar=false;
    public static int codaNumber =-1;
    public static int jobSched =-1;

    public static String OCO="";
    public static Vector strV=new Vector();
    public static boolean DEBUG=false;
    public static int TipoAnalisiTAG;
    public static String NameConfig;
    public static HelpDescVector helpVector=new HelpDescVector();

    public static String IP_ADDRESS = "";

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

    public static int ID_MOD_MDM                = mdm_configuration_server.eID_MOD_MDM;
    public static int SESSION_TIME              = 20; // 20 min
    public static Hashtable<String,Integer> hTableReport=new Hashtable<String,Integer>();
    public static final int MAX_ROW_EXCEL_2007	= 65000;
     
    public static Hashtable<String,STRUCT_PARAMS> hashT_StructParams = new Hashtable<String,STRUCT_PARAMS>();
    public static JListIcon summary_jListIconReport  = null;
    public static Vector vettURL = new Vector();
}
