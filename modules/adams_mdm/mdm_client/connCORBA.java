
import java.awt.*;
import java.io.*;
import java.applet.*;
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
import net.etech.loadconfig.*;
import net.etech.loadior.*;


/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
 * Questa classe istanzia un oggetto mediante il quale ï¿½ possibile istaurare una connessione 
 * con il Master Server sfruttanto la tecnologia CORBA.
 * Una volta stabilita la connessione con il Master Server, mediante il metodo <b>getConfiguration</b>
 * viene caricata la configurazzione necessaria ad autoconfigurare l'interfaccia del client.
 * </font></font></p></pre>
 * <p align="center">&nbsp;</p>     
 */
public class connCORBA
{
        private ADAMS_loadConfig testLoadConfig = new ADAMS_loadConfig();
        String ns_ref;

        org.omg.CORBA.ORB orbSTSConf                            = null;
        org.omg.CORBA.ORB orbMonMasterServer                    = null;
        
	private ADAMS_COMPLETE_CONFIG ntmConfig				= new ADAMS_COMPLETE_CONFIG();
 	public CentralSeqHolder centraliTemp				= new CentralSeqHolder();
	private mdm_server_server mdm_server_serverRef 			= null;
        private mdm_server_server mdm_server_serverRef_Pivot			= null;
        
	public mdm_server_factory_server mdm_server_serverFactoryRef		= null;
	private mdm_server_job_server MDM_JobActivatorRef			= null;
	private boolean connOK						= false;
	private boolean connOK_BJ					= false;
        
        public int USR_LOGIN_LEN = mdm_server_serverRef.eUSR_LOGIN_LEN;
        public int IP_STRING_LEN = mdm_server_serverRef.eIP_STRING_LEN;
        public int MAX_PIVOT_KEYS = mdm_server_serverRef_Pivot.eMAX_PIVOT_KEYS;
        

	public int local_ID_MOD_ADAMS					= 1;

	private JApplet ja;
	private getConfigFiltro configuration;
	private boolean DEBUG						= false;
	int cont=1;
        
        private PivotDataNodeSeqHolder pivotDataHolder  = null;
        private ExportDataSeqHolder    reportData = null;
        private IDListSeqHolder IDCounters_PIVOT        = null;
        private org.omg.CORBA.IntHolder PIVOT_TOTAL_ROWS = null;
        
        //NtmUserSeqHolder NtmUser = new NtmUserSeqHolder();
        
        private S_APP_LOG AppLog                                = null;
        
        private  String IP_CLIENT = "";
        private  String HOSTNAME_CLIENT = "";
        private  String LOGIN = "";
        private  String PASSWORD = "";
        
        public void setInfoClient(String ipClient, String hostNameClient)
        {
            this.IP_CLIENT = ipClient;
            this.HOSTNAME_CLIENT = hostNameClient;
        }
        
        public connCORBA(String ipClient, String hostNameClient,String login,String password)
	{
            this.IP_CLIENT = ipClient;
            this.HOSTNAME_CLIENT = hostNameClient;
            this.LOGIN = login;
            this.PASSWORD = password;
        
            AppLog = new S_APP_LOG( set_String_toChar( "" ,staticLib.MAX_TIME_STAMP),
                                set_String_toChar( "" ,staticLib.MAX_IP_GENERATORE),
                                set_String_toChar( "" ,staticLib.MAX_HOSTNAME_GENERATORE),
                                set_String_toChar( this.IP_CLIENT ,staticLib.MAX_IP_CLIENT),
                                set_String_toChar( this.HOSTNAME_CLIENT ,staticLib.MAX_HOSTNAME_CLIENT),
                                set_String_toChar( this.LOGIN ,staticLib.MAX_UTENZA_APPLICATIVA),
                                set_String_toChar( this.LOGIN ,staticLib.MAX_UTENZA_CLIENT),
                                set_String_toChar( "NTM" ,staticLib.MAX_APPLICATIVO_CLIENT),
                                set_String_toChar( "" ,staticLib.MAX_AZIONE),
                                set_String_toChar( "" ,staticLib.MAX_OGGETTO),
                                set_String_toChar( "" ,staticLib.MAX_PARAMETRI),
                                set_String_toChar( "" ,staticLib.MAX_ESITO),
                                0);           
	}
	
	public String getPswd (String nomeNodo,String typePassword,String userLogin ) 
    {
            org.omg.CORBA.StringHolder str_output = null;

            boolean stat = false;
            try {
                    if ( status_Conf ) {
                            //System.out.println("Chiamata getPswd(...)");				
                            str_output = new org.omg.CORBA.StringHolder();
                            stat = asp_entryRef.getPswd(nomeNodo,typePassword,userLogin, str_output);				
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
        
	int cont6=1;
	public boolean runMDMServerNonBlocking(STRUCT_PARAMS struct_params)
	{
		//System.out.println("FLAG="+struct_params.Reserved);
		if (connOK)
		{
			try
			{
				//System.out.println("1 mdm_server_serverRef.runMDMServerNonBlocking");
                                boolean stat = true;//mdm_server_serverRef.runMDMServerNonBlocking(struct_params);
                                //System.out.println("2 mdm_server_serverRef.runMDMServerNonBlocking "+stat);
				cont6=1;
				return stat;
			}
			catch(Exception e)
			{
				//System.out.println("Loading B: "+cont6);
				if(cont6<4)
				{
					cont6++;
					openConnection();
					runMDMServerNonBlocking(struct_params);
					cont6=1;
					return false;
				}else
				{
					warningProblem(e);
					cont6=1;
					return false;
				}

			}
		}
		else
		{
			cont6=1;
			return false;
		}
	}

	int cont7=1;
	public boolean getRunningStatus(INVOKE_STATUSHolder invokeSTATUS)
	{
		if (connOK)
		{
			try
			{
				boolean stat = true; //mdm_server_serverRef.getRunningStatus(invokeSTATUS);
				cont7=1;
				return stat;
			}
			catch(Exception e)
			{
				System.out.println("Loading C: "+cont7);
				if(cont7<4)
				{
					cont7++;
					openConnection();
					getRunningStatus(invokeSTATUS);
					cont7=1;
					return false;
				}else
				{
					warningProblem(e);
					cont7=1;
					return false;
				}
			}
		}
		else
		{
			cont7=1;
			return false;
		}
	}
        
        
        public String[] getCounterPivot() // chiamare solo dopo il metodo getPivotData(...) con esito positivo.
        {
            //System.out.println("IDCounters_PIVOT obj -->"+IDCounters_PIVOT.value);
            //System.out.println("IDCounters_PIVOT.value.length "+IDCounters_PIVOT.value.length);
            return IDCounters_PIVOT.value;
        }
        
        public int getTotalRowsPivot() // chiamare solo dopo il metodo getPivotData(...) con esito positivo.
        {
            //System.out.println("PIVOT_TOTAL_ROWS == "+PIVOT_TOTAL_ROWS.value);
            return PIVOT_TOTAL_ROWS.value;
        }
        
         
        int contPivot=1;
        
        // Servono al  Server nel metodo getPivotData
        int lastRow = 0;
        String lastKey = "";
        // Servono al  Server nel metodo getPivotData
        
        
	public PIVOTDATANODE[] getPivotData(boolean[] array_key_enabled,String name, int startLine, int maxLines, int[] indexReq,int sortColumn ,boolean decreasing, String str_focus,int elementToSort,boolean percent)
	{
            //System.out.println("getPivotData ("+(new String(name).trim())+","+startLine+")");
            //System.out.println("lastRow="+lastRow+" lastKey="+lastKey);
                        
            /*System.out.println("*********** getPivotData *************");
            System.out.println("elementToSort -->"+elementToSort);
            System.out.println("decreasing    -->"+decreasing);
            System.out.println("percent       -->"+!percent);*/
            
            openConnection_pivot();
            
           /*for(int i=0;i<array_key_enabled.length; i++)
                System.out.println("array_key_enabled ["+i+"] -->"+array_key_enabled[i]);*/
            
            
            PIVOTREQ pivotReq = new PIVOTREQ(array_key_enabled, new int[mdm_server_serverRef_Pivot.eCNT_NUM],sortColumn,decreasing,str_focus,lastRow,lastKey,elementToSort,!percent); //(la percentuale ï¿½ negata "richiesta dal server")
            
            if (indexReq != null)
            {     
                for(int i=0; i<indexReq.length; i++)
                {
                    pivotReq.enabledColumns[i] = indexReq[i];
                    
                    if(i<indexReq.length)
                        pivotReq.enabledColumns[i+1] = -1;
                }
                
                /*for(int x=0; x<pivotReq.enabledColumns.length; x++)
                {
                    System.out.println("enabledColumns -->"+pivotReq.enabledColumns[x]);
                }*/
            }
            /*else
                System.out.println("getPivotData(...) --->Error indexReq "+indexReq);*/
            
            PIVOTREQHolder pivotReqHolder = new PIVOTREQHolder(pivotReq);
            pivotDataHolder = new PivotDataNodeSeqHolder();
            IDCounters_PIVOT = new IDListSeqHolder();                                                                                                              
            PIVOT_TOTAL_ROWS = new org.omg.CORBA.IntHolder();         
            
            if (connOK)
            {
                try
                {
                    //System.out.println("startLine="+startLine+" maxLines="+maxLines); 
                    //System.out.println("Chiamo mdm_server_serverRef_Pivot.getPivotData(.....)");
                    //System.out.println("sortColumn="+sortColumn+" decreasing="+decreasing+" str_focus="+str_focus); 
                    boolean stat = mdm_server_serverRef_Pivot.getPivotData(name, startLine, maxLines, pivotReqHolder,PIVOT_TOTAL_ROWS, pivotDataHolder, IDCounters_PIVOT);       
                    
                    //System.out.println("Return mdm_server_serverRef_Pivot.getPivotData(.....) ---> "+stat);
                                       
                    //System.out.println("pivotDataHolder.value.length =" +pivotDataHolder.value.length);
              
                    if(stat)
                    {             
                        shutDown_pivot();                        
                        contPivot=1;
                        
                        this.lastRow = pivotReqHolder.value.last_row; 
                        this.lastKey = pivotReqHolder.value.last_key;
                        
                        return pivotDataHolder.value;
                    }
                    else
                    {
                        shutDown_pivot();
                        contPivot=1;
                        return null;
                    }
                    
                }
                catch(Exception e)
                {
                    System.out.println("Exception -----------> "+e);
                    e.printStackTrace();
                    System.out.println("Loading D: "+contPivot);
                    if(contPivot<4)
                    {
                            shutDown_pivot();    
                            contPivot++;
                            
                            //getPivotData(array_key_enabled,name,startLine,maxLines,indexReq,sortColumn,decreasing,str_focus,skip_rows,MASTER_Order,First_QuestPage);
                            getPivotData(array_key_enabled,name,startLine,maxLines,indexReq,sortColumn,decreasing,str_focus,elementToSort,!percent);
                            contPivot=1;
                            
                            //System.out.println("RITORNO ---------> "+contPivot);
                            this.lastRow = pivotReqHolder.value.last_row; 
                            this.lastKey = pivotReqHolder.value.last_key;
                            
                            return pivotDataHolder.value;
                    }else
                    {
                            warningProblem(e);
                            contPivot=1;
                            shutDown_pivot();
                            IDCounters_PIVOT = null;
                            return null;
                    }
                }
            }
            else
            {
                contPivot=1;
                IDCounters_PIVOT = null;
                return null;
            }
	}
        
        int contExport=0;
        
	public EXPORT_ROW[] getExportReport(String name)
	{
		openConnection();
		if(DEBUG)
		{
			System.out.println("\n\n**********  connOK ("+connOK+")**********");
			System.out.println("getExportReport("+name+")");
		}
		if (connOK)
		{
			reportData = new ExportDataSeqHolder();
			
			try
			{
				boolean stat = mdm_server_serverRef.getExportReport(name, reportData);   
				shutDown();
				                       
				if(DEBUG)
				{
					System.out.println("getExportReport return="+stat);
				}
				
				if(stat)
				{                                    
					contExport=1;
					return reportData.value;
				}
				else
				{
					contExport=1;
					return null;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Exception(getExportReport) -----------> "+e);
				e.printStackTrace();
				System.out.println("Loading(getExportReport) D: "+contExport);
				shutDown(); 
				if(contExport<4)
				{ 
				    contExport++;
				    getExportReport(name);
				    return reportData.value;
				}else
				{
				    warningProblem(e);
				    return null;
				}
			}
		}
		else
		{
			contExport=1;
			return null;
		}
		
	}
        

        /**
	 *
         **/
	public void setValue(JApplet ja)
	{
		this.ja=ja;

		try
		{
			staticLib.machine_name = ja.getParameter("machine_name");
        		staticLib.locator_port = ja.getParameter("locator_port");
			ns_ref = "corbaloc::" + staticLib.machine_name + ":" + staticLib.locator_port + "/NameService";

		}catch(Exception e)
		{
			staticLib.optionPanel("Mancano i parametri <machine_name> <locator_port> in <jmatrix.html>", "Errore",JOptionPane.ERROR_MESSAGE);
		}
	}

	int cont2=1;
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Una volta istaurata la connessione con il Master Server, questo metodo provvede al caricamento della
	 * configurazione. Il valore restituito ï¿½ una struttura complessa contenente tutti i dati necessari
	 * per l'autoconfigurazione del client.
	 * </font></font></p></pre>  
	 */
	
	public getConfigFiltro getConfiguration(String nameCFG)
	{
		System.out.println("connCORBA --> nameCFG ="+nameCFG);
		testLoadConfig.load_ADAMSConfIG(nameCFG);

		DATA_CENTRALI[] centra=(DATA_CENTRALI[])testLoadConfig.getADAMSExternal(ADAMS_TAB_EXTERNAL.EXTERNAL_CENTRALI);
		connOK=true;
		
		if (connOK)
		{
			try
			{
			boolean stat = true; //mdm_server_serverRef.startMDMServer(ntmConfig, centraliTemp, nameCFG);
			boolean confOK=true;


			if(testLoadConfig.getADAMSComplete().relationSequence.length==0)
			{
				confOK=false;
				staticLib.optionPanel("Errore nella ricezione della Configurazione. <relationSequence>", "Errore",JOptionPane.ERROR_MESSAGE);
			}

			if(testLoadConfig.getADAMSComplete().elementSequence.length==0)
			{
				confOK=false;
				staticLib.optionPanel("Errore nella ricezione della Configurazione. <elementSequence>", "Errore",JOptionPane.ERROR_MESSAGE);
			}


			if(testLoadConfig.getADAMSComplete().analisysSequence.length==0)
			{
				confOK=false;
				staticLib.optionPanel("Errore nella ricezione della Configurazione. <analisysSequence>", "Errore",JOptionPane.ERROR_MESSAGE);
			}

			if(centra==null)
			{
				confOK=false;
				staticLib.optionPanel("Errore nella ricezione della Configurazione. <centra is null>", "Errore",JOptionPane.ERROR_MESSAGE);
			}else if(centra.length==0)
			{
				confOK=false;
				staticLib.optionPanel("Errore nella ricezione della Configurazione. <centra is empty>", "Errore",JOptionPane.ERROR_MESSAGE);
			}
			

			if(confOK==false)
			{
				cont2=1;
				return null;
			}
			//******************************************************
			//  	Carico le centrali
			//******************************************************
      System.out.println("connCORBA --> nameCFG ="+nameCFG);
                        
			staticLib.NUMERO_CENTRALI= centra.length;
      System.out.println(" connCORBA --> NUMERO_CENTRALI = "+centra.length);
			System.out.println(" connCORBA --> Numero Centrali lette = "+centra.length);

			Vector appoCentrali=new Vector();	// appoggio per le centrali.descrizione
			Vector appoIdCentrali=new Vector(); 	// appoggio pe le centrali.idCentrale

			for(int i=0;i<staticLib.NUMERO_CENTRALI;i++)
			{
				//if(new String(centra[i].Descrizione).trim().length()!=0)
				//{
                                    appoCentrali.addElement((new String(centra[i].Descrizione)).trim());
                                    appoIdCentrali.addElement(new Integer(centra[i].IdCentrale));
                                    System.out.println("pos ==> "+i+" id ==>"+new Integer(centra[i].IdCentrale)+" desc ==>"+(new String(centra[i].Descrizione)).trim());
				/*}
				else
				{
                                    //////System.out.println("campo non valorizzato");
				}*/
			}

			staticLib.centrali  = new String[appoCentrali.size()];  //Descrizione Centrali
			staticLib.idCentrali= new int[appoIdCentrali.size()]; //idCentrali;

			/*for(int i=0;i<staticLib.centrali.length;i++)
			{
				staticLib.centrali[i]=(String)appoCentrali.elementAt(i);
				staticLib.idCentrali[i]=((Integer)appoIdCentrali.elementAt(i)).intValue();
				System.out.println(" connCORBA --> Centrale: ["+staticLib.centrali[i]+"]  ID: "+staticLib.idCentrali[i]);
			}*/
                        
                        
                        //////////////////// PER DISABILITARE DALL'INTERFACCIA SBC e TISM
                                                             
                        //System.out.println("connCORBA --> nameCFG ="+nameCFG);
                        for(int i=0;i<staticLib.centrali.length;i++)
			{
                            staticLib.centrali[i]=(String)appoCentrali.elementAt(i);

                            if(nameCFG.equals("N.T.M. CDR SBC") == true) // SOLO SBC
                            {
                                if(staticLib.centrali[i].equals("SBC") == true)
                                    staticLib.idCentrali[i]=((Integer)appoIdCentrali.elementAt(i)).intValue();
                                else
                                    staticLib.idCentrali[i]= -1;
                            }
                            else
                            {
                                if( (staticLib.centrali[i].equals("SBC") == false) && (staticLib.centrali[i].equals("TSM0I") == false) ) // SOLO le PEB
                                    staticLib.idCentrali[i]=((Integer)appoIdCentrali.elementAt(i)).intValue();
                                else
                                    staticLib.idCentrali[i]= -1;
                            }                               
                            //System.out.println(" connCORBA --> Centrale: ["+staticLib.centrali[i]+"]  ID: "+staticLib.idCentrali[i]);
			}                        
                        ///////////////////
                        
                        

			//*************************************************
			//	Carico i tipi di analisi.
			//*************************************************
			//*************************************************
			//	Controllo subito se c'ï¿½ qualche ghost relations
			//*************************************************

                        
                        //Ordinamento Analisi per ID
                        orderAnalisysForID();    // ---> testLoadConfig.getADAMSComplete().analisysSequence                   
                        
			staticLib.ghostAnalisi=new boolean[testLoadConfig.getADAMSComplete().analisysSequence.length];
			staticLib.idGhostRel=new int[testLoadConfig.getADAMSComplete().analisysSequence.length];

			Relation tempRelation;
			Analisi localAnalisi=new Analisi(testLoadConfig.getADAMSComplete().analisysSequence);

                        /*for(int f=0; f<localAnalisi.localAnalisi.length; f++)
                        {
                            DATA_ANALYSISTYPE datoAnalisi = localAnalisi.localAnalisi[f];
                            
                            System.out.println("ANALISI IN CORBA --> "+new String(datoAnalisi.LongDescription).trim());
                        }*/                        
                        
			//System.out.println("Num Analisi:"+testLoadConfig.getADAMSComplete().analisysSequence.length);
			for(int i=0;i<testLoadConfig.getADAMSComplete().analisysSequence.length;i++)
			{
				DATA_RELATIONS[] appo_rel=get_Relations(testLoadConfig.getADAMSComplete().relationSequence,localAnalisi.localAnalisi[i].relations);
				if(appo_rel!=null)
				{
					tempRelation=new Relation(appo_rel);
					if(tempRelation.isGhost())
					{
						staticLib.idGhostRel[i]=tempRelation.get_idRelation(0);
						staticLib.ghostAnalisi[i]=tempRelation.isGhost();
					}
				}
			}

			configuration=new getConfigFiltro(	testLoadConfig.getADAMSComplete().reportSequence,
								testLoadConfig.getADAMSComplete().relationSequence,
								testLoadConfig.getADAMSComplete().elementSequence,
								testLoadConfig.getADAMSComplete().exceptionSequence,
								testLoadConfig.getADAMSComplete().analisysSequence,
								testLoadConfig.getADAMSComplete().counterSequence,
								testLoadConfig.getADAMSComplete().pluginRegistrySequence,
								/*seqHelp,*/
								centraliTemp);
			shutDown();

			cont2=1;
			return configuration;
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Loading F: "+cont2);
				if(cont2<4)
				{
					cont2++;
					openConnection();
					getConfiguration(nameCFG);
					cont2=1;
					return null;
				}else
				{
					cont2=1;
					return null;
				}
			}
		}
		else
		{
			cont2=1;
			return null;
		}
	}

	public void ordina(DATA_HELP[] lista,int key)
	{
		//System.out.println("Key: "+key);   
            int numElement = lista.length;
            String sj="",smin="";
            int indiceSj,indiceSmin;

            for(int i=0;i<numElement-1;i++)
            {
                int min=i;
                for(int j=i+1;j<numElement;j++)
                {
                    if (key == 2)
                    {
                        sj = new String(lista[j].data);
                        indiceSj = sj.indexOf(" ");
                        sj = (sj.substring(indiceSj+1)).trim();

                        smin = new String(lista[min].data);
                        indiceSmin = smin.indexOf(" ");
                        smin = (smin.substring(indiceSmin+1)).trim();
                    }
                    else if (key == 1)
                    {
                        sj = new String(lista[j].key).trim();
                        smin = new String(lista[min].key).trim();
                    }

                    if( sj.compareTo(smin) < 0 )
                            min = j;
                }
                String tempKey  = new String(lista[i].key);
                lista[i].key    = lista[min].key;
                lista[min].key  = tempKey.toCharArray();
                String tempData = new String(lista[i].data);
                lista[i].data   = lista[min].data;
                lista[min].data = tempData.toCharArray();
            }
	}

        
        /**
        * Metodo che permette di trasformare una stringa in un array di caratteri.
        *@param str Stringa il cui contenuto sarï¿½ copiato nell'array di char.
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
        
	int cont3=1;
	boolean stat1 = false;
	HelperSeqHolder helpS = null;

	public DATA_HELP[] getHelp(String idHelp)
	{                
        	openConnection();
		//STRUCT_PARAMS struct_params_per_help=new STRUCT_PARAMS();
	        //struct_params_per_help.user_ip = set_String_toChar(staticLib.IP_ADDRESS,staticLib.CORBA.IP_STRING_LEN);
	        //staticLib.struc_paramsCURRENT;

		if(DEBUG)
		{
			System.out.println("\n\n**********  connOK ("+idHelp+")**********");
			System.out.println("getHelp("+idHelp+")");
		}
		DATE_INFO appo[]=null;
		int num;
        	if (connOK)
		{
			if(staticLib.struc_paramsCURRENT.ElaborationData != null)
			{
				num=staticLib.struc_paramsCURRENT.ElaborationData.length;
			}else
			{
				num=0;
			}
			if(num>0)
			{
				appo=new DATE_INFO[num];
				for(int i=0;i<num;i++)
				{
					appo[i]=new DATE_INFO();
					appo[i].dateString=staticLib.struc_paramsCURRENT.ElaborationData[i].dateString;
				}
			}

			try
			{

				helpS = new HelperSeqHolder();

                                staticLib.struc_paramsCURRENT.Description=new char[mdm_server_server.eJQ_DESC_SIZE];
                                if(num==0)
                                {
                                    staticLib.struc_paramsCURRENT.ElaborationData=new DATE_INFO[1];
                                    staticLib.struc_paramsCURRENT.ElaborationData[0]= new DATE_INFO();
                                    staticLib.struc_paramsCURRENT.ElaborationData[0].dateString = new char[9];//("19760814"+"\0").toCharArray();
                                }

				if(DEBUG)
				{
					System.out.println("PRIMA di getHelperValues: getHelp("+stat1+")");
				}
                
		                //System.out.println("PRIMA di getHelperValues");
		                stat1 = mdm_server_serverRef.getHelperValues(staticLib.NameConfig,idHelp,staticLib.struc_paramsCURRENT,helpS);
		                //System.out.println("DOPO di getHelperValues");
		                shutDown();

				if(DEBUG)
				{
					System.out.println("DOPO di getHelperValues: getHelp("+stat1+")");
					if(stat1==true)
					{
						System.out.println("helpS.value.length="+helpS.value.length);
						for(int i=0;i<helpS.value.length;i++)
						{
							System.out.println("Key="+new String(helpS.value[i].key).trim()+ " data="+new String(helpS.value[i].data).trim());
						}
					}
				}
			}
			catch (Exception e)
			{                            
		                System.out.println("Exception: "+e);
						
		                System.out.println("Loading G: "+cont3);
				if(cont3<4)
				{
					cont3++;
					openConnection();
					getHelp(idHelp);
				}else
					warningProblem(e);
                
                		e.printStackTrace();
			}
			
			if(num>0)
			{
				staticLib.struc_paramsCURRENT.ElaborationData=new DATE_INFO[num];
				for(int i=0;i<num;i++)
				{
					staticLib.struc_paramsCURRENT.ElaborationData[i]=new DATE_INFO();
					staticLib.struc_paramsCURRENT.ElaborationData[i].dateString=appo[i].dateString;
				}
			}else
			{
				staticLib.struc_paramsCURRENT.ElaborationData=null;
			}
			if(stat1)
			{
				//ordina(helpS.value,1);
				cont3=1;
                		return helpS.value;
			}else
			{
				cont3=1;
				return null;
			}
		}else
		{
			cont3=1;
			return null;
		}
	}


	public boolean openConnection_BuildJob()
	{
		/*try
		{
			//System.out.println("Prima ORBInit BuildJob");
			org.omg.CORBA.ORB orb =org.omg.CORBA.ORB.init(ja,null);
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
			MDM_JobActivatorRef = MDM_JobActivatorHelper.narrow(obj_res);


			if (MDM_JobActivatorRef == null)
			{
				//System.out.println("ERROR narrowing MDM_JobActivator !!");
				connOK_BJ = false;
				staticLib.MDM_JobActivatorRef=MDM_JobActivatorRef;
			}
			else
			{
				staticLib.MDM_JobActivatorRef=MDM_JobActivatorRef;
				//System.out.println("SERVER NARROW OK BuildJob");
				connOK_BJ = true;
			}
		}
		catch (Exception e)
		{
			staticLib.optionPanel("Errore nell'instaurazione della connessione con i il BuildJob.", "Errore",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(System.out);
			connOK_BJ=false;
		}

		return connOK_BJ;*/
		
		return true;
	}


	public boolean openConnectionMasterServer()
	{
		System.out.println("openConnectionMasterServer");
		ORB orb = ORB.init(ja, null);
		
		
		ADAMS_IOR adams_ior=new ADAMS_IOR();
		String nome_server="mdm_masterserver";
		String ior="";
		ior=adams_ior.getIor(nome_server);
		if (ior=="")
		{
			staticLib.optionPanel("Error load IOR.", "Error",JOptionPane.ERROR_MESSAGE);
			connOK=false;
		}else
		{
			System.out.println("IOR("+nome_server+")="+ior);
		}
	
		// Destringify object reference
		org.omg.CORBA.Object       obj = orb.string_to_object(ior);
		System.out.println("Destringify object reference");
		
		    // Narrow the CORBA object to its proper type, so that the client can invoke it
		mdm_server_server   mdm_server_server_ref = mdm_server_serverHelper.narrow(obj);


		if (mdm_server_server_ref == null)
		{
		    staticLib.optionPanel("Cannot resolved object ref mdm_server_server.", "Error",JOptionPane.ERROR_MESSAGE);
		    connOK=false;
		}
		else
		{
		    System.out.println("***JavaClient:Resolved object reference");
		    connOK=true;
		}
		
		return connOK;
        }
           
           
        /**
	 * <pre>
	 * Crea una connessione con il Master Server, se la connessione viene stabilita il metodo ritorna <b>true</b>,
	 * </font></font></p></pre>  
	 */
	public boolean openConnection()
	{
		try
		{	
			System.out.println("TRY openConnection MasterServer");
			ORB orb = ORB.init(ja, null);
			
			ADAMS_IOR adams_ior=new ADAMS_IOR();
			String nome_server="mdm_server_factory";
			String ior="";
			ior=adams_ior.getIor(nome_server);
			if (ior=="")
			{
				JOptionPane warning = new JOptionPane();
				warning.showMessageDialog(ja,"Error load IOR.","Error Message",JOptionPane.ERROR_MESSAGE); 
				connOK=false;
			}else
			{
				System.out.println("IOR("+nome_server+")="+ior);
				// Destringify object reference
				org.omg.CORBA.Object       obj = orb.string_to_object(ior);
				System.out.println("Destringify object reference");
				
				    // Narrow the CORBA object to its proper type, so that the client can invoke it
				mdm_server_serverFactoryRef = mdm_server_factory_serverHelper.narrow(obj);

				if (mdm_server_serverFactoryRef == null)
				{
				    JOptionPane warning = new JOptionPane();
				    warning.showMessageDialog(ja,"Cannot resolved object ref mdm_server_server.","Error Message",JOptionPane.ERROR_MESSAGE);
				    connOK=false;
				}
				else
				{
				    System.out.println("***JavaClient:Resolved object reference");
				    
				    mdm_server_serverRef = mdm_server_serverFactoryRef.get_server();
				    //System.out.println("SERVER NARROW OK MasterServer");
				    if (mdm_server_serverRef == null)
				    {
					    System.out.println("ERROR narrowing mdm_server_serverRef !!");
				    }
				    
				    staticLib.mdm_server_serverRef=mdm_server_serverRef;
				    connOK=true;

				}
			}
		
			
		}
		/*catch (net.etech.MDM.mdm_server_factory_server.LaunchFailure lf)
		{
			staticLib.optionPanel("TIMEOUT nel lancio del MS.", "Errore",JOptionPane.ERROR_MESSAGE);
                	System.out.println("Executable launch failure :- " + lf.reason );
			connOK=false;
        	}*/
		catch (Exception e)
		{
			staticLib.optionPanel("Errore nell'instaurazione della connessione con i il MS.", "Errore",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(System.out);
			connOK=false;
		}
		
		return connOK;
		//lancia il MS
		/*try
		{		


			//System.out.println("Prima ORBInit");
       	 		org.omg.CORBA.ORB orb =org.omg.CORBA.ORB.init(ja,null);
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

			mdm_server_serverFactoryRef = mdm_server_serverFactoryHelper.narrow(obj_res);

			//System.out.println("SERVER RESOLVE OK MasterServer");

			if (mdm_server_serverFactoryRef == null)
			{
				System.out.println("ERROR narrowing mdm_server_serverFactoryRef !!");
			}
			connOK=true;
			try
			{
				Thread.sleep(2);
			}catch(Exception e)
			{}

			mdm_server_serverRef = mdm_server_serverFactoryRef.get_config_server();
			//System.out.println("SERVER NARROW OK MasterServer");
			if (mdm_server_serverRef == null)
			{
				System.out.println("ERROR narrowing mdm_server_serverRef !!");
			}

			staticLib.mdm_server_serverRef=mdm_server_serverRef;

			connOK=true;
			return connOK;
		}
		catch (LaunchFailure lf)
		{
			staticLib.optionPanel("TIMEOUT nel lancio del MS.", "Errore",JOptionPane.ERROR_MESSAGE);
                	System.out.println("Executable launch failure :- " + lf.reason );
			connOK=false;
			return connOK;
        	}
		catch (Exception e)
		{
			staticLib.optionPanel("Errore nell'instaurazione della connessione con i il MS.", "Errore",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(System.out);
			connOK=false;
			return connOK;
		}
		
	    
		
		return true;*/
        }
                
            //--------------------------------------- LUCA    
	public boolean openConnection_pivot()
            {
		//lancia il MS
		/*try
		{		


			//System.out.println("Prima ORBInit");
       	 		org.omg.CORBA.ORB orb =org.omg.CORBA.ORB.init(ja,null);
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

			mdm_server_serverFactoryRef = mdm_server_serverFactoryHelper.narrow(obj_res);

			//System.out.println("SERVER RESOLVE OK MasterServer");

			if (mdm_server_serverFactoryRef == null)
			{
				System.out.println("ERROR narrowing mdm_server_serverFactoryRef !!");
			}
			connOK=true;
			try
			{
				Thread.sleep(2);
			}catch(Exception e)
			{}

			mdm_server_serverRef_Pivot = mdm_server_serverFactoryRef.get_config_server();
			//System.out.println("SERVER NARROW OK MasterServer");
			if (mdm_server_serverRef_Pivot == null)
			{
				System.out.println("ERROR narrowing mdm_server_serverRef_Pivot !!");
			}

			//staticLib.mdm_server_serverRef_Pivot=mdm_server_serverRef_Pivot;

			connOK=true;
			return connOK;
		}
		catch (LaunchFailure lf)
		{
			staticLib.optionPanel("TIMEOUT nel lancio del MS 2.", "Errore",JOptionPane.ERROR_MESSAGE);
                	System.out.println("Executable launch failure :- " + lf.reason );
			connOK=false;
			return connOK;
        	}
		catch (Exception e)
		{
			staticLib.optionPanel("Errore nell'instaurazione della connessione con i il MS.", "Errore",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(System.out);
			connOK=false;
			return connOK;
		}*/ 
		return true;
	}
        
        //--------------------------------------- LUCA
        
//STS.NTM.BuildJob --> server per il Job
	public void shutDown()
	{
		try
		{
                    //System.out.println("-------------------- shutDown() -------------------- ");
                    //mdm_server_serverRef.shutDown();
		}
		catch(Exception e)
		{}
	}
        
        public void shutDown_pivot()
	{
		try
		{
                    //System.out.println("-------------------- shutDown_pivot() -------------------- ");
                    //mdm_server_serverRef_Pivot.shutDown();
		}
		catch(Exception e)
		{}
	}
        

	private DATA_RELATIONS[] get_Relations(DATA_RELATIONS[] relationTemp1,int[] relSel)
	{
        	int i=0;
	        //System.out.print("[ ");
        	while(relSel[i]>0)
	        {
        		i++;
	        }
		boolean trovato=false;
        	DATA_RELATIONS[] appo=new DATA_RELATIONS[i];
	        for(int k=0;k<i;k++)
        	{
        		for(int j=0;j<relationTemp1.length;j++)
	        	{
        			//controllo l'ID
        			if(relationTemp1[j].idRelation==relSel[k])
        			{
	        			//Relazione trovata
        				appo[k]=relationTemp1[j];
        				//System.out.print(" "+relSel[k]);
					trovato=true;
	        		}
        		}
	        }

        	//System.out.println(" ]");
		if(trovato==false)
			appo=null;
		return appo;

	}


	// ################# Connessione con il sever di Configurazione ################### //
    	public asp_entry asp_entryRef					= null;
	private boolean status_Conf 					= false;
	//public ConfigServerFactory ConfigServerFactoryRef		= null;
    	/**
     	 * Questo metodo permette di effettuare la connessione al Server remoto di configurazione "STS_ConfigServer".
     	 * In caso di connessione rifiutata il metodo visualizza una Warning Dialog con ERROR_MESSAGE.
     	 *@return Ritorna true a connessione effettuata con esito positivo.
     	 */
	public boolean openConnection_config()
    	{
    		System.out.println("Connessione CORBA al configuratore saltata.");
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
            
            orbSTSConf = org.omg.CORBA.ORB.init(ja,null);
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
            warning.showMessageDialog(ja,"STS_ConfigServer: Connection refused.\n"+e,"Error Message",JOptionPane.ERROR_MESSAGE);
            return false;
        }*/
        
        return true;
	}

        public boolean LoginUser(String login, String password, boolean disablePassCheck)
        {                
            staticLib.RUOLO=-1;
		
        	boolean FLAG_disablePassCheck=disablePassCheck;
        	if((staticLib.idm).toUpperCase().equals("NO"))
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
                            stat = asp_entryRef.LoginUser(staticLib.ID_MOD_MDM,login,password,FLAG_disablePassCheck,AppLog,ruolo);
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
                        staticLib.RUOLO = ruolo.value;
                        return true;
                    }
                    else
                    {
                        staticLib.RUOLO=-1;
                        return false;
                    }
            }
            else
                return false;
        }
            
        public S_USER getUserConfiguration(String login,int idClass)
        {
            S_USERHolder sUser = null;
            boolean flagIDM=false;

            if (status_Conf)
            {
                boolean stat = false;
                try
                {
                    sUser = new S_USERHolder();
                    stat = asp_entryRef.getUserConfiguration(1,login,idClass,sUser,AppLog);
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
        
        public ADAMS_PROFILE_GRANT getProfileConfigurationNTM(String profileName)
        {
            ADAMS_PROFILE_GRANTHolder pGrant = null;
            boolean flagIDM=false;

            if (status_Conf)
            {
                boolean stat = false;
                try
                {
                    pGrant = new ADAMS_PROFILE_GRANTHolder();
                    stat = asp_entryRef.getProfileConfigurationMDM(profileName,pGrant,AppLog);
                }
                catch (Exception e)
                {
                    if(count_getProfileConfigurationNTM < 3)
                    {
                        System.out.println("------ getProfileConfigurationNTM() Ritento Connessione al STSConfigServerNEW -----");
                        count_getProfileConfigurationNTM++;
                        openConnection_config();
                        getProfileConfigurationNTM(profileName);
                        return null;
                    }
                    else
                    {
                        System.out.println("catch getProfileConfigurationNTM()");
                        warningProblem(e);
                    }
                }
                if(stat)
                {
                    count_getProfileConfigurationNTM = 0;
                    return (pGrant.value);
                }
                else
                    return null;
            }
            else
                return null;
        }
        private int count_getProfileConfigurationNTM = 0;
        

	private void warningProblem(Exception e)
	{
		e.printStackTrace();
		JOptionPane warning = new JOptionPane();
		warning.showMessageDialog(ja,"Comunication problem... please start the application again.","Error Message",JOptionPane.ERROR_MESSAGE);
        }
        
        
        private void orderAnalisysForID()
        {            
            //Ordinamento Analisi per ID
            for(int i=testLoadConfig.getADAMSComplete().analisysSequence.length-1; i>=0;i--)
            {
                for(int j=1;j<=i;j++)
                {
                    if(testLoadConfig.getADAMSComplete().analisysSequence[j].idAnalysis < testLoadConfig.getADAMSComplete().analisysSequence[j-1].idAnalysis)
                    {
                        DATA_ANALYSISTYPE tmp    = new DATA_ANALYSISTYPE();   
                        tmp.idAnalysis          = testLoadConfig.getADAMSComplete().analisysSequence[j-1].idAnalysis;
                        tmp.FlagArchiveData     = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagArchiveData;
                        tmp.FlagCentrale        = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagCentrale;
                        tmp.FlagDate            = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagDate;
                        tmp.FlagOutputType      = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagOutputType;
                        tmp.FlagTimeRes         = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagTimeRes;
                        tmp.FlagTrafficType     = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagTrafficType;
                        tmp.FlagSort            = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagSort;
                        tmp.FlagCumulativeData  = testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagCumulativeData;
                        tmp.LongDescription     = testLoadConfig.getADAMSComplete().analisysSequence[j-1].LongDescription;
                        tmp.ShortDescription    = testLoadConfig.getADAMSComplete().analisysSequence[j-1].ShortDescription;
                        tmp.relations           = testLoadConfig.getADAMSComplete().analisysSequence[j-1].relations;  
                        tmp.countersKitId       = testLoadConfig.getADAMSComplete().analisysSequence[j-1].countersKitId;
                        tmp.reportsId           = testLoadConfig.getADAMSComplete().analisysSequence[j-1].reportsId;

                        //*****************

                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].idAnalysis         = testLoadConfig.getADAMSComplete().analisysSequence[j].idAnalysis;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagArchiveData    = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagArchiveData;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagCentrale       = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagCentrale;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagDate           = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagDate;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagOutputType     = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagOutputType;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagTimeRes        = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagTimeRes;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagTrafficType    = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagTrafficType;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagSort           = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagSort;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].FlagCumulativeData = testLoadConfig.getADAMSComplete().analisysSequence[j].FlagCumulativeData;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].LongDescription    = testLoadConfig.getADAMSComplete().analisysSequence[j].LongDescription;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].ShortDescription   = testLoadConfig.getADAMSComplete().analisysSequence[j].ShortDescription;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].relations          = testLoadConfig.getADAMSComplete().analisysSequence[j].relations;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].countersKitId      = testLoadConfig.getADAMSComplete().analisysSequence[j].countersKitId;
                        testLoadConfig.getADAMSComplete().analisysSequence[j-1].reportsId          = testLoadConfig.getADAMSComplete().analisysSequence[j].reportsId;

                        //*****************

                        testLoadConfig.getADAMSComplete().analisysSequence[j].idAnalysis         = tmp.idAnalysis;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagArchiveData    = tmp.FlagArchiveData;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagCentrale       = tmp.FlagCentrale;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagDate           = tmp.FlagDate;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagOutputType     = tmp.FlagOutputType;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagTimeRes        = tmp.FlagTimeRes;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagTrafficType    = tmp.FlagTrafficType;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagSort           = tmp.FlagSort;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].FlagCumulativeData = tmp.FlagCumulativeData;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].LongDescription    = tmp.LongDescription;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].ShortDescription   = tmp.ShortDescription;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].relations          = tmp.relations;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].countersKitId      = tmp.countersKitId;
                        testLoadConfig.getADAMSComplete().analisysSequence[j].reportsId          = tmp.reportsId;

                    }      
                }
            }              
        }
        
        
}


