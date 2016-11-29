/*
 *
 * ADAMS_storeConfig.java
 *
 * Created on 4 novembre 2005, 8.25
 */

/**
 *
 * @author  Raffo
 * @email   raffaele.ficcadenti@e-tech.net
 * Classe utilizzata per la conversione dei dati da ORACLE a CORBA.
 *
 */

import java.util.*;
import java.sql.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class ADAMS_storeConfig {

    private boolean                     DEBUG                   = false;
    private boolean                     DEBUG_ALARM             = false;


    public ADAMS_COMPLETE_CONFIG                 ntmComplete             = new ADAMS_COMPLETE_CONFIG();
   // private String                      ADAMSConfigName           = "prova.ADAMSConfig";
    private CORBAConnection             CORBA                   = null;
    private boolean                     connOK_mdm_server_server  = false;
    private String                      config_NAME_ALIAS       = null;
    private Vector                      vReport                 = null;
    private Vector                      vScript                 = new Vector();
    private Vector                      vAnalysis               = new Vector();
    private ADAMS_Plugin                 vPlugin                 = null;
    private ADAMS_Relation               vRelation               = new ADAMS_Relation();
    private Vector                      V_Counters              = new Vector();


    //private ADAMS_typeList               tList                   = new ADAMS_typeList();

    /******************************************************************************/
    /*    costanti da prendere dall'idl attualmente definite in ntmlimits.h       */

    //------------------Usati anche nel VERIFY LIMITS
    // ANALISI
    private int          _MAX_RELATION           = ADAMS_GlobalParam._MAX_RELATION;           // - RELAZIONI ABILITATE
    private int          _MAX_ANALYSIS_REPORTS   = ADAMS_GlobalParam._MAX_ANALYSIS_REPORTS;    // - REPORT ABILITATI

    // CONTATORI
    private int          _CNT_NUM                = ADAMS_GlobalParam._CNT_NUM;                 // - MAX CONTATORI PER KIT

    // RELAZIONI
    private int          _MAX_RESTRICTIONS       = ADAMS_GlobalParam._MAX_RESTRICTIONS;        // - MAX RELAZIONI ABILITATE - MAX RESTRIZIONI OBBLIGATORIE
    private int          _NEXTLEVEL_RELATIONS    = ADAMS_GlobalParam._NEXTLEVEL_RELATIONS;     // - MAX RELAZIONI LIVELLO SUCC.

    // SCRIPT
    private int          _SCRIPT_MAX_VAR         = ADAMS_GlobalParam._SCRIPT_MAX_VAR;         // - MAX LISTA VARIABILI
    private int          _SCRIPT_MAX_TEXT        = ADAMS_GlobalParam._SCRIPT_MAX_TEXT;        // - MAX LINEE TESTO SCRIPT

    // TRAFFIC ELEMENT
    private int          _MAX_OPTIONS            = ADAMS_GlobalParam._MAX_OPTIONS;             // - MAX OPZIONI IN VALUE LIST
    private int          _MAX_AGGREGATE_RESTR    = ADAMS_GlobalParam._MAX_AGGREGATE_RESTR;     // - MAX LISTA AGGREGATE
    private int          _MAX_EXCEPTIONS         = ADAMS_GlobalParam._MAX_EXCEPTIONS;          // - MAX LISTA EVENTI GUI
    private int          _VALSHIFTER_MAX         = ADAMS_GlobalParam._VALSHIFTER_MAX;          // - MAX VALORI SHIFTER
    private int          _MAX_TE_SCRIPTS         = ADAMS_GlobalParam._MAX_TE_SCRIPTS;          // - SCRIPTS
    //------------------ end anche nel VERIFY LIMITS

    private int     _REPO_LABEL_LEN                 = mdm_server_server.eREPO_LABEL_LEN;
    private int     _REPO_USER_LEN                  = mdm_server_server.eREPO_USER_LEN;
    private int     _SHORT_PLUGIN_NAME              = mdm_server_server.eSHORT_PLUGIN_NAME;
    private int     _SHORT_DESC_LEN                 = mdm_server_server.eSHORT_DESC_LEN;
    private int     _PLR_PLUGINNAME_LEN             = mdm_server_server.ePLR_PLUGINNAME_LEN;
    private int     _SCRIPT_VARNAME_LEN             = mdm_server_server.eSCRIPT_VARNAME_LEN;
    private int     _SCRIPT_TEXT_LEN                = mdm_server_server.eSCRIPT_TEXT_LEN;
    private int     _LONG_DESC_LEN                  = mdm_server_server.eLONG_DESC_LEN;
    private int     _ASCII_REST_LEN                 = mdm_server_server.eASCII_REST_LEN;
    private int     _USR_LOGIN_LEN                  = mdm_server_server.eUSR_LOGIN_LEN;
    private int     _USR_PASSWD_LEN                 = mdm_server_server.eUSR_PASSWD_LEN;
    private int     _MAX_ENABLE_CONFIGS             = mdm_server_server.eMAX_ENABLE_CONFIGS;
    private int     _MAX_CONFIG_FILENAME            = mdm_server_server.eMAX_CONFIG_FILENAME;
    private int     _DR_FIELDSDESCRIPTIONLENGHT    = mdm_server_server.eDR_FIELDSDESCRIPTIONLENGHT;
    private int     _MAX_RESTR_LEVELS               = mdm_server_server.eMAX_RESTR_LEVELS;
    private int     _CNT_PLUGINNAME_LEN             = mdm_server_server.eCNT_PLUGINNAME_LEN;
    private int     _CNT_PATH_LEN                   = mdm_server_server.eCNT_PATH_LEN;
    private int     _CNT_TAG_LEN                    = mdm_server_server.eCNT_TAG_LEN;
    private int     _CNT_DESC_LEN                   = mdm_server_server.eCNT_DESC_LEN;
    private int     _DR_PLUGINNAME_LEN             = mdm_server_server.eDR_PLUGINNAME_LEN;
    private int     _DR_PATH_LEN                   = mdm_server_server.eDR_PATH_LEN;
    private int     _GLB_INFO_LEN                   = mdm_server_server.eGLB_INFO_LEN;
    private int     _OPTION_LABEL_LEN               = mdm_server_server.eOPTION_LABEL_LEN;
    private int     _VALSHIFTER_LEN                 = mdm_server_server.eVALSHIFTER_LEN;
    private int     _TE_SUFFIX_LEN                  = mdm_server_server.eTE_SUFFIX_LEN;
    private int     _PIXMAP_FILENAME_LEN            = mdm_server_server.ePIXMAP_FILENAME_LEN;
    private int     _VALUE_RANGE_LEN                = mdm_server_server.eVALUE_RANGE_LEN;
    private int     _DR_DATE_LEN                   = mdm_server_server.eDR_DATE_LEN;
//private int
    /******************************************************************************/


    private int _MAX_DIMENSION              = mdm_server_server.eMAX_DIMENSION;
    private int _MAX_ALARM_GENERATOR        = mdm_server_server.eMAX_ALARM_GENERATOR;
    private int _MAX_THRESHOLD_GENERATOR    = mdm_server_server.eMAX_THRESHOLD_GENERATOR;



    /** Creates new ADAMS_storeConfig */
    public ADAMS_storeConfig()
    {
       /* CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);

        connOK_mdm_server_server = CORBA.openConnection_mdm_server_server();

        if(connOK_mdm_server_server)
            System.out.println("Connection mdm_server_server... ok.");
        else
            System.out.println(" - Error Connection CORBA.");
        */
        if(ADAMS_GlobalParam.connect_Oracle==null)
            ADAMS_GlobalParam.connect_Oracle = new ADAMS_OracleConnection();

        System.out.println(" Load Config ");
        vPlugin=new ADAMS_Plugin();

    }


    public void set_config_NAME_ALIAS(String str) //Raffo
    {
        config_NAME_ALIAS=str;
    }

    /*public void set_ADAMSConfigName(String str) //Raffo
    {
        ADAMSConfigName=str;
    }*/


    /* va richiamato necessariamente dopo get_ADAMSConfIG, e get_REPORTCONFIG*/
    public boolean get_PLUGINCONFIG() //Raffo
    {
        boolean flag = false;
        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_PLUGINCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        if(vPlugin==null)
        {
            System.out.println("ADAMS_storeConfig [get_PLUGINCONFIG] ----> NON SONO STATI CARICATI I PLUGIN.");
            return false;
        }

        Vector pl=vPlugin.getVPlugin();

        int len_pl=pl.size();

        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_PLUGINCONFIG] ---> sono presenti "+len_pl+" Plugin");
        }


        //len_pl=0;

        if(len_pl>0)
        {
            int nDATA_PLUGINREGISTRY=len_pl;
            ntmComplete.pluginRegistrySequence = new DATA_PLUGINREGISTRY[nDATA_PLUGINREGISTRY];

            for(int i=0;i<len_pl;i++)
            {
                ADAMS_Plugin.plugin p=(ADAMS_Plugin.plugin)pl.elementAt(i);

                if(DEBUG)
                {
                   System.out.println();
                   System.out.println("ADAMS_storeConfig [get_PLUGINCONFIG] ---> Plugin Nome: "+p.NOME_PLUGIN);
                   System.out.println("p.ID "+p.ID);
                   System.out.println("p.DESCRIZIONE "+p.DESCRIZIONE);
                }

                ntmComplete.pluginRegistrySequence[i]=new DATA_PLUGINREGISTRY(  p.ID,
                                                                                set_String_toChar(p.DESCRIZIONE,_SHORT_DESC_LEN),
                                                                                set_String_toChar(p.NOME_PLUGIN,_PLR_PLUGINNAME_LEN),
                                                                                0,
                                                                                true);



            }
            flag =true;
        }else
        {
            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_PLUGINCONFIG] ----> NON CI SONO PLUGIN.");

            ntmComplete.pluginRegistrySequence = new DATA_PLUGINREGISTRY[1];
            ntmComplete.pluginRegistrySequence[0] = new DATA_PLUGINREGISTRY();
            ntmComplete.pluginRegistrySequence[0].tag=new char[_SHORT_DESC_LEN];
            ntmComplete.pluginRegistrySequence[0].pluginName=new char[_PLR_PLUGINNAME_LEN];

            flag = false;
        }

        if(DEBUG)
            System.out.println("END get_PLUGINCONFIG --> "+flag);

        return flag;

    }

    public boolean get_USERCONFIG() //Raffo
    {
        boolean flag=true;
        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_USERCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        int nADAMS_USER=0;

        if(nADAMS_USER>0)
        {
            //da completare per un eventuale lista di utenti.
            flag=true;
        }else
        {
            char appo[][]=new char[_MAX_ENABLE_CONFIGS][_MAX_CONFIG_FILENAME];

            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_USERCONFIG] ----> NON CI SONO UTENTI.");

            ntmComplete.userSequence            = new ADAMS_USER[1];
            ntmComplete.userSequence[0]=new ADAMS_USER(   set_String_toChar("",_USR_LOGIN_LEN),
                                                        set_String_toChar("",_USR_PASSWD_LEN),
                                                        toBoolean(0),
                                                        toBoolean(0),
                                                        appo);
            flag=false;
        }

        return flag;
    }

    public boolean get_DRCONFIG()
    {
        boolean flag=true;
        //VectorHelp vHELP = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_DR);
        ADAMS_typeList typeList= new ADAMS_typeList();

        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_DRCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG=new ADAMS_Vector_TAB_CONFIG(config_NAME_ALIAS);

        /***************** leggo la configurazione completa ****************/
        V_TAB_CONFIG.read_AllItem(ADAMS_GlobalParam.read_DR);
        int dr_len = V_TAB_CONFIG.size();
        int nDATA_DR=dr_len;

        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_DRCONFIG] ----> sono presenti "+nDATA_DR+" DR");
        }

        //nDATA_DR=0;

        if(nDATA_DR>0)
        {
            ntmComplete.drSequence             = new DATA_DR[nDATA_DR];
            int offset=0;
            for(int i=0;i<nDATA_DR;i++)
            {

                ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);
                if(row_TAB_CONFIG!=null)
                {
                    int size=0;

                    if(toBoolean(row_TAB_CONFIG.FLAG_ARRAY)==true)
                    {
                        size=row_TAB_CONFIG.SIZE_CAMPO_DR*row_TAB_CONFIG.NUMERO_ELEM_ARRAY;
                    }
                    else
                    {
                        size=row_TAB_CONFIG.SIZE_CAMPO_DR;
                    }

                    if(DEBUG)
                    {
                       System.out.println("DR---> LABEL_DR_NORMALIZZ: "+row_TAB_CONFIG.LABEL_DR_NORMALIZZ);
                       System.out.println("DR---> FLAG_ARRAY: "+toBoolean(row_TAB_CONFIG.FLAG_ARRAY));
                       System.out.println("DR---> POSIZIONE_ELEMENTO: "+row_TAB_CONFIG.POSIZIONE_ELEMENTO);
                       System.out.println("DR---> TIPO_CAMPO: "+row_TAB_CONFIG.TIPO_CAMPO);
                       System.out.println("DR---> ID: "+typeList.idType(row_TAB_CONFIG.TIPO_CAMPO));
                    }

                    /*DATA_DR(int position,
                                    int fieldtype,
                                    int offset,
                                    int size,
                                    int type_size,
                                    boolean is_array,
                                    int array_size,
                                    char[] description,
                                    char isIndex,
                                    int indexBlockSize,
                                    int startSize,
                                    char[] indexSuffix,
                                    char[] startTime,
                                    char[] endTime);*/

                    ntmComplete.drSequence[i]=new DATA_DR(    row_TAB_CONFIG.POSIZIONE_CAMPO_DR,
                                                                typeList.idType(row_TAB_CONFIG.TIPO_CAMPO),
                                                                offset,
                                                                size,
                                                                row_TAB_CONFIG.SIZE_CAMPO_DR,
                                                                toBoolean(row_TAB_CONFIG.FLAG_ARRAY),
                                                                row_TAB_CONFIG.NUMERO_ELEM_ARRAY,
                                                                set_String_toChar(row_TAB_CONFIG.LABEL_DR_NORMALIZZ,_DR_FIELDSDESCRIPTIONLENGHT),
                                                                row_TAB_CONFIG.SE_INDICE,
                                                                false,
                                                                row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF,
                                                                row_TAB_CONFIG.VALORE_AGGREGAZIONE,
                                                                row_TAB_CONFIG.LENGTH_VALORE_START ,
                                                                set_String_toChar(row_TAB_CONFIG.SUFF_VALORE_INDICE,_TE_SUFFIX_LEN),
                                                                set_String_toChar(row_TAB_CONFIG.DATA_INIZIO_INDICE,_DR_DATE_LEN),
                                                                set_String_toChar(row_TAB_CONFIG.DATA_FINE_INDICE,_DR_DATE_LEN));

                    offset+=size;
                }
            }
            flag=true;
        }else
        {
            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_DRCONFIG] ----> NON CI SONO DR.");

            ntmComplete.drSequence=new DATA_DR[1];
            ntmComplete.drSequence[0]=new DATA_DR(    0,
                                                        0,
                                                        0,
                                                        0,
                                                        0,
                                                        toBoolean(0),
                                                        0,
                                                        set_String_toChar("",_DR_FIELDSDESCRIPTIONLENGHT),
                                                        false,
                                                        false,
                                                        0,
                                                        0,
                                                        0,
                                                        set_String_toChar("",_TE_SUFFIX_LEN),
                                                        set_String_toChar("",_DR_DATE_LEN),
                                                        set_String_toChar("",_DR_DATE_LEN));
            flag=false;
        }




        return flag;
    }


    public boolean get_DRCONFIG_INDEX()
    {
        boolean flag=true;
        //VectorHelp vHELP = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_DR);
        ADAMS_typeList typeList= new ADAMS_typeList();

        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_DRCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG = new ADAMS_Vector_TAB_CONFIG(config_NAME_ALIAS);

        /***************** leggo la configurazione completa ****************/
        V_TAB_CONFIG.read_AllItem(ADAMS_GlobalParam.read_ONLY_TRUE_INDEX);
        int dr_len = V_TAB_CONFIG.size();
        int nDATA_DR = dr_len;

        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_DRCONFIG] ----> sono presenti "+nDATA_DR+" DR");
        }


        if(nDATA_DR>0)
        {
            ntmComplete.drSequence             = new DATA_DR[nDATA_DR];
            int offset=0;
            for(int i=0;i<nDATA_DR;i++)
            {
                ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);
                if(row_TAB_CONFIG!=null)
                {
                    if(row_TAB_CONFIG.POSIZIONE_CAMPO_DR != -1)
                    {
                        int size=0;

                        if(toBoolean(row_TAB_CONFIG.FLAG_ARRAY)==true)
                        {
                            size=row_TAB_CONFIG.SIZE_CAMPO_DR*row_TAB_CONFIG.NUMERO_ELEM_ARRAY;
                        }
                        else
                        {
                            size=row_TAB_CONFIG.SIZE_CAMPO_DR;
                        }

                        if(DEBUG)
                        {
                           System.out.println("DR---> LABEL_DR_NORMALIZZ: "+row_TAB_CONFIG.LABEL_DR_NORMALIZZ);
                           System.out.println("DR---> FLAG_ARRAY: "+toBoolean(row_TAB_CONFIG.FLAG_ARRAY));
                           System.out.println("DR---> POSIZIONE_ELEMENTO: "+row_TAB_CONFIG.POSIZIONE_ELEMENTO);
                           System.out.println("DR---> TIPO_CAMPO: "+row_TAB_CONFIG.TIPO_CAMPO);
                           System.out.println("DR---> ID: "+typeList.idType(row_TAB_CONFIG.TIPO_CAMPO));
                        }

                        ntmComplete.drSequence[i] = new DATA_DR(  row_TAB_CONFIG.POSIZIONE_CAMPO_DR,
                                                                    typeList.idType(row_TAB_CONFIG.TIPO_CAMPO),
                                                                    offset,
                                                                    size,
                                                                    row_TAB_CONFIG.SIZE_CAMPO_DR,
                                                                    toBoolean(row_TAB_CONFIG.FLAG_ARRAY),
                                                                    row_TAB_CONFIG.NUMERO_ELEM_ARRAY,
                                                                    set_String_toChar(row_TAB_CONFIG.LABEL_DR_NORMALIZZ,_DR_FIELDSDESCRIPTIONLENGHT),
                                                                    row_TAB_CONFIG.SE_INDICE,
                                                                    false,
                                                                    row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF,
                                                                    row_TAB_CONFIG.VALORE_AGGREGAZIONE,
                                                                    row_TAB_CONFIG.LENGTH_VALORE_START ,
                                                                    set_String_toChar(row_TAB_CONFIG.SUFF_VALORE_INDICE,_TE_SUFFIX_LEN),
                                                                    set_String_toChar(row_TAB_CONFIG.DATA_INIZIO_INDICE,_DR_DATE_LEN),
                                                                    set_String_toChar(row_TAB_CONFIG.DATA_FINE_INDICE,_DR_DATE_LEN));

                        offset+=size;
                    }
                    else
                    {
                        boolean indexByPlugin = false;
                        if(row_TAB_CONFIG.SE_INDICE == true)
                            indexByPlugin = true;

                         ntmComplete.drSequence[i] = new DATA_DR( -1,
                                                                    -1,
                                                                    -1,
                                                                    -1,
                                                                    -1,
                                                                    false,
                                                                    -1,
                                                                    set_String_toChar(row_TAB_CONFIG.LABEL_DR_NORMALIZZ,_DR_FIELDSDESCRIPTIONLENGHT),
                                                                    row_TAB_CONFIG.SE_INDICE,
                                                                    indexByPlugin,
                                                                    row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF,
                                                                    row_TAB_CONFIG.VALORE_AGGREGAZIONE,
                                                                    row_TAB_CONFIG.LENGTH_VALORE_START ,
                                                                    set_String_toChar(row_TAB_CONFIG.SUFF_VALORE_INDICE,_TE_SUFFIX_LEN),
                                                                    set_String_toChar(row_TAB_CONFIG.DATA_INIZIO_INDICE,_DR_DATE_LEN),
                                                                    set_String_toChar(row_TAB_CONFIG.DATA_FINE_INDICE,_DR_DATE_LEN));

                    }

                    flag=true;
                }
                else
                {
                    if(DEBUG)
                        System.out.println("ADAMS_storeConfig [get_DRCONFIG] ----> NON CI SONO DR.");

                    ntmComplete.drSequence=new DATA_DR[1];
                    ntmComplete.drSequence[0]=new DATA_DR(    0,
                                                                0,
                                                                0,
                                                                0,
                                                                0,
                                                                toBoolean(0),
                                                                0,
                                                                set_String_toChar("",_DR_FIELDSDESCRIPTIONLENGHT),
                                                                false,
                                                                false,
                                                                0,
                                                                0,
                                                                0,
                                                                set_String_toChar("",_TE_SUFFIX_LEN),
                                                                set_String_toChar("",_DR_DATE_LEN),
                                                                set_String_toChar("",_DR_DATE_LEN));
                    flag=false;
                }
            }
        }
        return flag;
    }



    public DATA_DR[] getDRSequence()
    {
        return ntmComplete.drSequence;
    }

    // ****** ****** ****** ****** ****** ****** ****** ****** ****** //
    // ******  get_ANALISICONFIG() -- ATTENZIONE va chimato dopo get_ADAMSConfIG() ****** //
    public boolean get_ANALISICONFIG()  // ****** get_ANALISICONFIG() -- ATTENZIONE va chimato dopo get_ADAMSConfIG() ****** //
    {
        boolean flag=false;

        String name_TabAnalisi = "tab_analysis_type";
        String item_TAB_TYPE_ANALISI = "IDANALISI,NOMEANALISI,IDCOUNTERKIT,FLAGSORT,FLAGDATA,FLAGCENTRALE,FLAGCUMULAZIONE";

        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_ANALISI] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return flag;
        }

        String sel_ReadAnalysis = "select "+item_TAB_TYPE_ANALISI+" from "+name_TabAnalisi+" where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"' order by NOMEANALISI";

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ReadAnalysis,SQLStatement);


        if(rs != null)
        {
            try
            {
                while ( rs.next() )
                {
                    ADAMS_TAB_TYPE_ANALISI row_Analysis = new ADAMS_TAB_TYPE_ANALISI();
                    row_Analysis.TIPO_DI_CONFIGURAZIONE = config_NAME_ALIAS;

                    //"IDANALISI,NOMEANALISI,IDCOUNTERKIT,FLAGSORT,FLAGDATA,FLAGCENTRALE,FLAGCUMULAZIONE,LISTA_REPORT";

                    //*** Campi Analisi -- alcuni campi sono NOT Null sul DB ***//
                    row_Analysis.IDANALISI          = rs.getInt("IDANALISI");                   //not null sil DB
                    String nomeAnalisi = rs.getString("NOMEANALISI");
                    if(nomeAnalisi != null)
                        row_Analysis.NOMEANALISI = nomeAnalisi;
                    row_Analysis.IDCOUNTERKIT       = rs.getInt("IDCOUNTERKIT");
                    row_Analysis.FLAGSORT           = rs.getString("FLAGSORT").charAt(0);       //not null sil DB
                    row_Analysis.FLAGDATA           = rs.getString("FLAGDATA").charAt(0);       //not null sil DB
                    row_Analysis.FLAGCENTRALE       = rs.getString("FLAGCENTRALE").charAt(0);   //not null sil DB
                    row_Analysis.FLAGCUMULAZIONE    = rs.getString("FLAGCUMULAZIONE").charAt(0);//not null sil DB

                    // ------------- Nested LISTA_REPORT ------------- //
                    String[] nomeCampi      = {"IDREPORT"};
                    String linkTable  = "tab_list_report";

                    String selectReadNested = "select ";

                    for(int i=0;i<nomeCampi.length;i++)
                    {
                        if(i!=nomeCampi.length-1)
                            selectReadNested += nomeCampi[i]+",";
                        else
                            selectReadNested += nomeCampi[i]+" ";
                    }

                    selectReadNested += " from " + linkTable ;
                    selectReadNested += " where ";
                    selectReadNested += "TIPO_DI_CONFIGURAZIONE='"+row_Analysis.TIPO_DI_CONFIGURAZIONE+"' and IDANALISI="+row_Analysis.IDANALISI;

                    if(DEBUG)
                    {
                        System.out.println("ADAMS_storeConfig [get_ANALISI] ----> selectRead : "+selectReadNested);
                    }
                    Statement SQLStatement_1 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rsNested  = ADAMS_GlobalParam.connect_Oracle.Query_RS(selectReadNested,SQLStatement_1);
                    if(rsNested != null)
                    {
                        try
                        {
                            while (rsNested.next())
                            {
                                row_Analysis.LISTA_REPORT.addElement(new Integer(rsNested.getInt(nomeCampi[0])));
                                if(DEBUG)
                                {
                                    System.out.println("LISTA_REPORT    ->"+rsNested.getInt(nomeCampi[0]));
                                }
                            }
                        }catch (Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }else
                    {
                        System.out.println("ADAMS_storeConfig [get_ANALISI] ----> rsNested == null");
                    }

                    try
                    {
                        SQLStatement_1.close();
                    }
                    catch(java.sql.SQLException exc)
                    {
                        System.out.println("ADAMS_storeConfig [get_ANALISI] ----> SQL Operation(Nested analisi) " + exc.toString());
                    }
                    // ------------- End Nested LISTA_REPORT ------------- //

                    if(DEBUG)
                    {
                        System.out.println();
                        System.out.println("ADAMS_storeConfig [get_ANALISI] ----> Analisi TIPO_DI_CONFIGURAZIONE :"+row_Analysis.TIPO_DI_CONFIGURAZIONE+"---");
                        System.out.println("IDANALISI       ->"+row_Analysis.IDANALISI);
                        System.out.println("NOMEANALISI     ->"+row_Analysis.NOMEANALISI);
                        System.out.println("IDCOUNTERKIT    ->"+row_Analysis.IDCOUNTERKIT);
                        System.out.println("FLAGSORT        ->"+row_Analysis.FLAGSORT);
                        System.out.println("FLAGDATA        ->"+row_Analysis.FLAGDATA);
                        System.out.println("FLAGCENTRALE    ->"+row_Analysis.FLAGCENTRALE);
                        System.out.println("FLAGCUMULAZIONE ->"+row_Analysis.FLAGCUMULAZIONE);
                        System.out.println();
                    }
                    vAnalysis.addElement(row_Analysis);
                }
            }catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }

        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("ADAMS_storeConfig [get_ANALISI] ---->  SQL Operation " + exc.toString());
        }


        int nDATA_ANALYSISTYPE=vAnalysis.size();

        //nDATA_ANALYSISTYPE=0;
        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_ANALISI] ----> sono presenti "+nDATA_ANALYSISTYPE+" Analisi");
        }

        if(nDATA_ANALYSISTYPE>0)
        {
            ntmComplete.analisysSequence        = new DATA_ANALYSISTYPE[nDATA_ANALYSISTYPE];
            for(int i=0;i<nDATA_ANALYSISTYPE;i++)
            {


                    ADAMS_TAB_TYPE_ANALISI row_Analysis=(ADAMS_TAB_TYPE_ANALISI)vAnalysis.elementAt(i);

                    if(DEBUG)
                    {
                        System.out.println("ADAMS_storeConfig [get_ANALISI] ---> "+row_Analysis.NOMEANALISI+","+row_Analysis.IDANALISI);
                    }

                    ntmComplete.analisysSequence[i]= new DATA_ANALYSISTYPE();
                    ntmComplete.analisysSequence[i].idAnalysis=row_Analysis.IDANALISI;
                    ntmComplete.analisysSequence[i].FlagArchiveData=toBoolean(1);   // Manca in tabella ORACLE
                    ntmComplete.analisysSequence[i].FlagCentrale=toBoolean(row_Analysis.FLAGCENTRALE);
                    ntmComplete.analisysSequence[i].FlagDate=toBoolean(row_Analysis.FLAGDATA);
                    ntmComplete.analisysSequence[i].FlagOutputType=toBoolean(1); // Manca in tabella ORACLE
                    ntmComplete.analisysSequence[i].FlagTimeRes=toBoolean(1); // Manca in tabella ORACLE
                    ntmComplete.analisysSequence[i].FlagTrafficType=toBoolean(1); // Manca in tabella ORACLE
                    ntmComplete.analisysSequence[i].FlagSort=toBoolean(row_Analysis.FLAGSORT);
                    ntmComplete.analisysSequence[i].FlagCumulativeData=toBoolean(row_Analysis.FLAGCUMULAZIONE);
                    ntmComplete.analisysSequence[i].LongDescription=set_String_toChar(row_Analysis.NOMEANALISI,_LONG_DESC_LEN);
                    ntmComplete.analisysSequence[i].ShortDescription=set_String_toChar(row_Analysis.NOMEANALISI,_SHORT_DESC_LEN);		// Descrizione breve che manca in tabella ORACLE
                    ntmComplete.analisysSequence[i].relations=new int[_MAX_RELATION];

                    Vector vRel = vRelation.getVRelation(ntmComplete.analisysSequence[i].idAnalysis);
                    for(int j=0;j<_MAX_RELATION;j++)
                    {
                        if(j<vRel.size())
                        {
                            int idRel = ((Integer)vRel.elementAt(j)).intValue();
                            ntmComplete.analisysSequence[i].relations[j]=idRel;
                            //da prendere da un eventuale classe che associa un ID-Temporaneo <--> Relazione
                        }else
                        {
                            ntmComplete.analisysSequence[i].relations[j]=0;
                        }
                    }
                    ntmComplete.analisysSequence[i].countersKitId=row_Analysis.IDCOUNTERKIT;
                    ntmComplete.analisysSequence[i].reportsId=new int[_MAX_ANALYSIS_REPORTS];
                    for(int j=0;j<_MAX_ANALYSIS_REPORTS;j++)
                    {
                        if(j<row_Analysis.LISTA_REPORT.size())
                        {
                            int appo=((Integer)row_Analysis.LISTA_REPORT.elementAt(j)).intValue();
                            ntmComplete.analisysSequence[i].reportsId[j]=appo;
                        }
                        else
                        {
                            ntmComplete.analisysSequence[i].reportsId[j]=-1;
                        }
                    }
                    flag=true;
            }
        }else
        {
            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_ANALISI] ----> NON CI SONO ANALISI.");

            ntmComplete.analisysSequence=new DATA_ANALYSISTYPE[1];
            ntmComplete.analisysSequence[0]=new DATA_ANALYSISTYPE();
            ntmComplete.analisysSequence[0].LongDescription=new char[_LONG_DESC_LEN];
            ntmComplete.analisysSequence[0].ShortDescription=new char[_SHORT_DESC_LEN];
            ntmComplete.analisysSequence[0].relations=new int[_MAX_RELATION];
            for(int j=0;j<_MAX_RELATION;j++)
            {
                ntmComplete.analisysSequence[0].relations[j]=-1;
            }
            ntmComplete.analisysSequence[0].reportsId=new int[_MAX_ANALYSIS_REPORTS];
            for(int j=0;j<_MAX_ANALYSIS_REPORTS;j++)
            {
                ntmComplete.analisysSequence[0].reportsId[j]=-1;
            }

            flag = false;
        }

        return flag;
    }


    public boolean get_EXCEPTIONCONFIG() //Raffo
    {
        boolean flag=false;
        VectorHelp vHELP = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_TRIGGER);
        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_EXCEPTIONCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }
        ADAMS_Vector_TAB_EVENTI_GUI exception=new ADAMS_Vector_TAB_EVENTI_GUI(config_NAME_ALIAS);
        exception.read_items();

        int nDATA_EXCEPTIONS=exception.size();

        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_EXCEPTIONCONFIG] ---> sono presenti "+nDATA_EXCEPTIONS+" Eccezioni");
        }

        //nDATA_EXCEPTIONS=0;

        if(nDATA_EXCEPTIONS>0)
        {
            ntmComplete.exceptionSequence       = new DATA_EXCEPTIONS[nDATA_EXCEPTIONS];
            for(int i=0;i<nDATA_EXCEPTIONS;i++)
            {
                ADAMS_TAB_EVENTI_GUI ex=(ADAMS_TAB_EVENTI_GUI)exception.elementAt(i);

                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_EXCEPTIONCONFIG] ---> "+ex.TAG+","+ex.IDEXCEPTION);
                }

                int idTriggerRestriction=0;
                if(ex.IDTRIGGERRESTRICTION==-1)
                {
                    idTriggerRestriction=0;
                }else
                {
                    idTriggerRestriction=ex.IDTRIGGERRESTRICTION;
                }

                int idAggregateRestriction=0;
                if(ex.IDAGGREGATEEXCEPTION==-1)
                {
                    idAggregateRestriction=0;
                }else
                {
                    idAggregateRestriction=ex.IDAGGREGATEEXCEPTION;
                }

                ntmComplete.exceptionSequence[i]=new DATA_EXCEPTIONS(    ex.IDEXCEPTION,
                                                                        set_String_toChar(ex.TAG,_SHORT_DESC_LEN),
                                                                        set_String_toChar(ex.DESCRIPTION,_LONG_DESC_LEN),
                                                                        idTriggerRestriction,
                                                                        vHELP.getValue(ex.TRIGGEREDSTATUS),
                                                                        set_String_toChar(ex.TRIGGEREDVALUE,_ASCII_REST_LEN),
                                                                        vHELP.getValue(ex.ACTION),
                                                                        set_String_toChar(ex.TARGETVALUE,_ASCII_REST_LEN),
                                                                        idAggregateRestriction);
            }

            flag=true;
        }else
        {
            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_EXCEPTIONCONFIG] --->  NON CI SONO ECCEZIONI.");

            ntmComplete.exceptionSequence       = new DATA_EXCEPTIONS[1];
            ntmComplete.exceptionSequence[0]    = new DATA_EXCEPTIONS(    0,
                                                                        set_String_toChar("",_SHORT_DESC_LEN),
                                                                        set_String_toChar("",_LONG_DESC_LEN),
                                                                        0,
                                                                        0,
                                                                        set_String_toChar("",_ASCII_REST_LEN),
                                                                        0,
                                                                        set_String_toChar("",_ASCII_REST_LEN),
                                                                        0);

        }

        return flag;
    }
    public boolean get_SCRIPTCONFIG() //Raffo
    {
        /* Ricordarsi di scartare gli SCRIP con FLAG_VALIDATE=1 */
        boolean flag = false;
        VectorHelp vHELP = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_RESULT_SCTRIPT);
        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        int nDATA_SCRIPT=10;
        int nDATA_SCRIPT_VARLIST=10;
        int nDATA_SCRIPT_TEXT=10;

        /* leggo gli script per la configurazione impostata */
        String strSelect="select distinct IDSCRIPT from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"'";
        if(DEBUG)
        {
            System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ----> "+strSelect);
        }

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next() )
                {
                    int idScript=rs.getInt("IDSCRIPT");
                    ScriptObject scriptLetto=new ScriptObject();
                    scriptLetto.readScript(config_NAME_ALIAS,idScript);
                    vScript.addElement(scriptLetto);
                }
            }catch (Exception ex)
            {
                System.out.println(ex);
            }
        }else
        {
            System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> rs==null");
        }

        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> ERROR-SQL Operation " + exc.toString());
        }

        /****************************************************************/

        int len_script=vScript.size();

        //len_script=0;

        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> sono presenti "+len_script+" Script");
        }


        if(len_script>0)
        {
            nDATA_SCRIPT=len_script;
            ntmComplete.scriptSequence          = new DATA_SCRIPT[nDATA_SCRIPT];

            for(int i=0;i<nDATA_SCRIPT;i++)
            {
                ScriptObject sc=(ScriptObject)vScript.elementAt(i);

                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> "+sc.sc.TAG+","+sc.sc.IDSCRIPT);
                }
                ntmComplete.scriptSequence[i]=new DATA_SCRIPT();
                ntmComplete.scriptSequence[i].idScript=sc.sc.IDSCRIPT;
                ntmComplete.scriptSequence[i].tag=set_String_toChar(sc.sc.TAG,_SHORT_DESC_LEN);
                ntmComplete.scriptSequence[i].dataOrigin=2;
                ntmComplete.scriptSequence[i].resultVariableName=set_String_toChar(sc.sc.RESULTVARNAME,_SCRIPT_VARNAME_LEN);
                ntmComplete.scriptSequence[i].resultType=sc.sc.RESULTTYPE;

                nDATA_SCRIPT_VARLIST=sc.sc.NUMVARIABLE1+sc.sc.NUMVARIABLE2;
                if(DEBUG)
                {
                    //System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> sc.sc.RESULTTYPE="+sc.sc.RESULTTYPE);
                    //System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> vHELP.getValue(sc.sc.RESULTTYPE)="+vHELP.getValue(sc.sc.RESULTTYPE));
                    System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> nDATA_SCRIPT_VARLIST="+nDATA_SCRIPT_VARLIST);
                }
                if(nDATA_SCRIPT_VARLIST>0)
                {
                    ntmComplete.scriptSequence[i].variables=new DATA_SCRIPT_VARLIST[nDATA_SCRIPT_VARLIST];
                    int cont=0;

                    for(int k=0;k<sc.sc.NUMVARIABLE1;k++)
                    {
                        String appo=(String)sc.sc.VARIABLES1.elementAt(k);
                        ntmComplete.scriptSequence[i].variables[cont]=new DATA_SCRIPT_VARLIST();
                        ntmComplete.scriptSequence[i].variables[cont].d=set_String_toChar(appo,_SCRIPT_VARNAME_LEN);
                        if(DEBUG)
                        {
                            System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> nome Var="+appo);
                        }
                        cont++;
                    }

                    for(int k=0;k<sc.sc.NUMVARIABLE2;k++)
                    {
                        String appo=(String)sc.sc.VARIABLES2.elementAt(k);
                        ntmComplete.scriptSequence[i].variables[cont]=new DATA_SCRIPT_VARLIST();
                        ntmComplete.scriptSequence[i].variables[cont].d=set_String_toChar(appo,_SCRIPT_VARNAME_LEN);
                        if(DEBUG)
                        {
                            System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> nome Var="+appo);
                        }
                        cont++;
                    }
                }
                else
                {
                    ntmComplete.scriptSequence[i].variables=new DATA_SCRIPT_VARLIST[1];
                    ntmComplete.scriptSequence[i].variables[0]=new DATA_SCRIPT_VARLIST();
                    ntmComplete.scriptSequence[i].variables[0].d= set_String_toChar("",_SCRIPT_VARNAME_LEN);
                }

                nDATA_SCRIPT_TEXT=sc.sc.NUMSCRIPTTEXT;
                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ---> nDATA_SCRIPT_TEXT="+nDATA_SCRIPT_TEXT);
                }


                if(nDATA_SCRIPT_TEXT>0)
                {
                    String strCompleta="";
                    for(int k=0;k<nDATA_SCRIPT_TEXT;k++)
                    {
                        strCompleta=strCompleta+(String)sc.sc.SCRIPTTEXT.elementAt(k);
                    }

                    strCompleta=strCompleta.trim();
                    Vector vRighe=creaRighe(strCompleta);
                    nDATA_SCRIPT_TEXT=vRighe.size();

                    ntmComplete.scriptSequence[i].scriptText=new DATA_SCRIPT_TEXT[nDATA_SCRIPT_TEXT];
                    for(int k=0;k<nDATA_SCRIPT_TEXT;k++)
                    {
                        //String appo=(String)sc.sc.SCRIPTTEXT.elementAt(k);
                        String appo=(String)vRighe.elementAt(k);
                        ntmComplete.scriptSequence[i].scriptText[k]= new DATA_SCRIPT_TEXT();
                        ntmComplete.scriptSequence[i].scriptText[k].d=set_String_toChar(appo,_SCRIPT_TEXT_LEN);
			//System.out.println("SCRIPT["+new String(ntmComplete.scriptSequence[i].scriptText[k].d).trim()+"]");
                    }
                }else
                {
                    ntmComplete.scriptSequence[i].scriptText=new DATA_SCRIPT_TEXT[1];
                    ntmComplete.scriptSequence[i].scriptText[0]=new DATA_SCRIPT_TEXT();
                    ntmComplete.scriptSequence[i].scriptText[0].d=set_String_toChar("",_SCRIPT_TEXT_LEN);
                }
            }
            flag=true;
        }else
        {
            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_SCRIPTCONFIG] ----> NON CI SONO SCRIPT.");

            flag=false;

            ntmComplete.scriptSequence= new DATA_SCRIPT[1];
            ntmComplete.scriptSequence[0]= new DATA_SCRIPT();
            ntmComplete.scriptSequence[0].tag                   = new char[_SHORT_DESC_LEN];
            ntmComplete.scriptSequence[0].resultVariableName    = new char[_SCRIPT_VARNAME_LEN];
            ntmComplete.scriptSequence[0].variables=new DATA_SCRIPT_VARLIST[1];
            ntmComplete.scriptSequence[0].variables[0]=new DATA_SCRIPT_VARLIST();
            ntmComplete.scriptSequence[0].variables[0].d= new char[_SCRIPT_VARNAME_LEN];
            ntmComplete.scriptSequence[0].scriptText=new DATA_SCRIPT_TEXT[1];
            ntmComplete.scriptSequence[0].scriptText[0]=new DATA_SCRIPT_TEXT();
            ntmComplete.scriptSequence[0].scriptText[0].d=new char[_SCRIPT_TEXT_LEN];
        }
        return flag;
    }

    public boolean get_REPORTCONFIG() //Raffo
    {
    	VectorHelp vHELP_COUNTER_INDEX = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_COUNTER_INDEX);

        //DEBUG=true;
        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        int nDATA_REPORTSCHEMA=10;
        int nDATA_RSO_HEADER=10;
        int nDATA_RSO_BODY=10;
        int nDATA_RSO_TOTALIZER=10;
        int nDATA_RSO_FOOTER=10;

        vReport=new Vector();

        String strSelect="select distinct TAG from tab_report where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"'";
        if(DEBUG)
            System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ----> "+strSelect);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next() )
                {
                    String nomeSchema=rs.getString("TAG");
                    if(nomeSchema!=null)
                    {
                        ReportObject schemaAttuale=new ReportObject(config_NAME_ALIAS,nomeSchema);
                        schemaAttuale.readAllObject();
                        schemaAttuale.readHeaderReport();
                        vReport.addElement(schemaAttuale);
                        if(DEBUG)
                        {
                            System.out.println(config_NAME_ALIAS+"-"+nomeSchema);
                        }
                    }
                }
            }catch (Exception ex)
            {
                System.out.println(ex);
            }
        }else
        {
            System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> rs==null");
        }

        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> ERROR-SQL Operation " + exc.toString());
        }

        /**/
        int nSchemi=vReport.size();
        int numRighe=0;
        for(int i=0;i<nSchemi;i++)
        {
             ReportObject appo=(ReportObject)vReport.elementAt(i);
             Vector vEB=appo.vGetElementoBase();
             numRighe=numRighe+vEB.size();
        }

        if(DEBUG)
        {
           System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> sono presenti "+nSchemi+" Schemi");
           System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> sono presenti "+numRighe+" Righe");
        }

        if(numRighe>0)
        {
            nDATA_REPORTSCHEMA=nSchemi;

            ntmComplete.reportSequence= new DATA_REPORTSCHEMA[nDATA_REPORTSCHEMA];

            for(int i=0;i<nSchemi;i++)
            {
                ReportObject schema=(ReportObject)vReport.elementAt(i);



                ReportObject.elementoBase reportObj=schema.getHeaderReport();

                ntmComplete.reportSequence[i]=new DATA_REPORTSCHEMA();

                ntmComplete.reportSequence[i].idReportSchema=reportObj.IDREPORTSCHEMA;
                ntmComplete.reportSequence[i].tag=set_String_toChar(reportObj.TAG,_SHORT_DESC_LEN);

                ntmComplete.reportSequence[i].objO=new DATA_RSO_OBJECT();
                ntmComplete.reportSequence[i].objO.c=new DATA_RS_COMMON(reportObj.IDOBJECT,
                                                                        reportObj.IDREPORTSCHEMA,
                                                                        0,
                                                                        set_String_toChar(reportObj.TAG,_REPO_LABEL_LEN));

                ntmComplete.reportSequence[i].objO.html=new DATA_RS_HTML(   reportObj.HTML_VSIZE,
                                                                            reportObj.HTML_HSIZE,
                                                                            reportObj.HTML_VALIGN,
                                                                            reportObj.HTML_HALIGN,
                                                                            toBoolean(reportObj.HTML_WRAP),
                                                                            reportObj.HTML_FOREGROUND_R,
                                                                            reportObj.HTML_FOREGROUND_G,
                                                                            reportObj.HTML_FOREGROUND_B,
                                                                            reportObj.HTML_BACKGROUND_R,
                                                                            reportObj.HTML_BACKGROUND_G,
                                                                            reportObj.HTML_BACKGROUND_B,
                                                                            reportObj.HTML_STYLE,
                                                                            reportObj.HTML_FONTSIZE,
                                                                            toBoolean(reportObj.HTML_BOLD),
                                                                            toBoolean(reportObj.HTML_ITALIC),
                                                                            toBoolean(reportObj.HTML_UNDERLINE));

                ntmComplete.reportSequence[i].objO.u=new DATA_RS_OBJECT(    set_String_toChar(reportObj.TAG,_SHORT_DESC_LEN),
                                                                            toBoolean(reportObj.OBJECT_HASBORDER),
                                                                            reportObj.OBJECT_EXCELCSV,
                                                                            toBoolean(reportObj.OBJECT_SIMPLEBODY),
                                                                            reportObj.OBJECT_IDANALISYS,
                                                                            toBoolean(reportObj.OBJECT_USEPLUGIN),
                                                                            set_String_toChar(reportObj.OBJECT_PLUGINNAME,_SHORT_PLUGIN_NAME),
                                                                            reportObj.OBJECT_DEFAULT_FOREGROUND_R,
                                                                            reportObj.OBJECT_DEFAULT_FOREGROUND_G,
                                                                            reportObj.OBJECT_DEFAULT_FOREGROUND_B,
                                                                            reportObj.OBJECT_DEFAULT_BACKGROUND_R,
                                                                            reportObj.OBJECT_DEFAULT_BACKGROUND_G,
                                                                            reportObj.OBJECT_DEFAULT_BACKGROUND_B);

                if(DEBUG)
                {
                    System.out.println("\n\n *********************      SCHEMA[]: "+new String(ntmComplete.reportSequence[i].objO.c.tag).trim()+"      ************************");
                    System.out.println("TYPE OBG: "+ntmComplete.reportSequence[i].objO.c.type);
                }

                /**********************************  PRENDO GLI HEADER **********************************************************/

                Vector vHeader=schema.getVectorObject(ReportObject.HEADER);
                int len_vHeader=vHeader.size();
                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> sono presenti "+len_vHeader+" HEADER");
                }
                if(len_vHeader>0)
                {
                    nDATA_RSO_HEADER=len_vHeader;
                    ntmComplete.reportSequence[i].hdrV=new DATA_RSO_HEADER[nDATA_RSO_HEADER];
                    for(int j=0;j<nDATA_RSO_HEADER;j++)
                    {
                        ReportObject.elementoBase eb=(ReportObject.elementoBase)vHeader.elementAt(j);
                        ntmComplete.reportSequence[i].hdrV[j]=new DATA_RSO_HEADER();

                        ntmComplete.reportSequence[i].hdrV[j].c=new DATA_RS_COMMON( eb.IDOBJECT,
                                                                                    eb.IDREPORTSCHEMA,
                                                                                    eb.TYPE,
                                                                                    set_String_toChar(eb.OBJECT_TAG,_REPO_LABEL_LEN));

                        ntmComplete.reportSequence[i].hdrV[j].html=new DATA_RS_HTML(eb.HTML_VSIZE,
                                                                                    eb.HTML_HSIZE,
                                                                                    eb.HTML_VALIGN,
                                                                                    eb.HTML_HALIGN,
                                                                                    toBoolean(eb.HTML_WRAP),
                                                                                    eb.HTML_FOREGROUND_R,
                                                                                    eb.HTML_FOREGROUND_G,
                                                                                    eb.HTML_FOREGROUND_B,
                                                                                    eb.HTML_BACKGROUND_R,
                                                                                    eb.HTML_BACKGROUND_G,
                                                                                    eb.HTML_BACKGROUND_B,
                                                                                    eb.HTML_STYLE,
                                                                                    eb.HTML_FONTSIZE,
                                                                                    toBoolean(eb.HTML_BOLD),
                                                                                    toBoolean(eb.HTML_ITALIC),
                                                                                    toBoolean(eb.HTML_UNDERLINE));
                        int typeSeparetor=0;
                        switch(eb.SUB_TYPE)
                        {
                            case ReportObject.LINE_BLANK:
                                typeSeparetor=1;
                                break;

                            case ReportObject.LINE_BREAK:
                                typeSeparetor=2;
                                break;

                            case ReportObject.LINE_SEPARETOR:
                                typeSeparetor=3;
                                break;
                        }

                        ntmComplete.reportSequence[i].hdrV[j].u=new DATA_RS_HEADER( set_String_toChar(eb.HEADER_USERLABEL,_REPO_LABEL_LEN),
                                                                                    eb.HEADER_VALUE,
                                                                                    set_String_toChar(eb.HEADER_USERVALUE,_REPO_USER_LEN),
                                                                                    toBoolean(eb.HEADER_ISURL),
                                                                                    typeSeparetor);

                        if(DEBUG)
                        {
                            System.out.println("-->Header: "+eb.OBJECT_TAG);
                            System.out.println("-->Type: "+eb.TYPE);
                        }
                    }
                }else
                {
                    if(DEBUG)
                        System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> NON CI SONO HEADER");

                    headerNULL(i);
                }

                /**********************************  PRENDO I BODY **********************************************************/
                /* 29-04-2011 Fix Kit Contatori associato al Report - Raffo*/

                int numeroDATA_ANALYSISTYPE=vAnalysis.size();
		int idReportKitContatori=-1;
		boolean flagTrovato=false;
		if(DEBUG)
	        {
			System.out.println("numeroDATA_ANALYSISTYPE: "+numeroDATA_ANALYSISTYPE);
		}
		for(int hTA=0;hTA<numeroDATA_ANALYSISTYPE;hTA++)
		{
			ADAMS_TAB_TYPE_ANALISI row_Analysis=(ADAMS_TAB_TYPE_ANALISI)vAnalysis.elementAt(hTA);
			if(DEBUG)
	        	{
				System.out.println("NOMEANALISI: "+row_Analysis.NOMEANALISI);
			}
			int numeroReport=row_Analysis.LISTA_REPORT.size();

			for(int y=0;y<numeroReport;y++)
			{
				int idReport=((Integer)row_Analysis.LISTA_REPORT.elementAt(y)).intValue();
				if(DEBUG)
	        		{
					System.out.println("idReport: "+idReport);
				}
				if(idReport==ntmComplete.reportSequence[i].idReportSchema)
				{
					/* Tò beccato e mi prendo l'ID del counter KIT*/
					idReportKitContatori=row_Analysis.IDCOUNTERKIT;
					flagTrovato=true;
					break;
				}
			}
			if(flagTrovato==true)
				break;
		}
		if(DEBUG)
	        {
			System.out.println("idReportKitContatori: "+idReportKitContatori);
		}
		/**************************************************************/

                Vector vBody=schema.getVectorObject(ReportObject.BODY);
                int len_vBody=vBody.size();
                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> sono presenti "+len_vBody+" BODY");
                }
                if(len_vBody>0)
                {
                    nDATA_RSO_BODY=len_vBody;
                    ntmComplete.reportSequence[i].bdyV=new DATA_RSO_BODY[nDATA_RSO_BODY];
                    for(int j=0;j<nDATA_RSO_BODY;j++)
                    {
                        ReportObject.elementoBase eb=(ReportObject.elementoBase)vBody.elementAt(j);
                        ntmComplete.reportSequence[i].bdyV[j]=new DATA_RSO_BODY();

                        ntmComplete.reportSequence[i].bdyV[j].c=new DATA_RS_COMMON( eb.IDOBJECT,
                                                                                    eb.IDREPORTSCHEMA,
                                                                                    eb.TYPE,
                                                                                    set_String_toChar(eb.OBJECT_TAG,_REPO_LABEL_LEN));

                        ntmComplete.reportSequence[i].bdyV[j].html=new DATA_RS_HTML(eb.HTML_VSIZE,
                                                                                    eb.HTML_HSIZE,
                                                                                    eb.HTML_VALIGN,
                                                                                    eb.HTML_HALIGN,
                                                                                    toBoolean(eb.HTML_WRAP),
                                                                                    eb.HTML_FOREGROUND_R,
                                                                                    eb.HTML_FOREGROUND_G,
                                                                                    eb.HTML_FOREGROUND_B,
                                                                                    eb.HTML_BACKGROUND_R,
                                                                                    eb.HTML_BACKGROUND_G,
                                                                                    eb.HTML_BACKGROUND_B,
                                                                                    eb.HTML_STYLE,
                                                                                    eb.HTML_FONTSIZE,
                                                                                    toBoolean(eb.HTML_BOLD),
                                                                                    toBoolean(eb.HTML_ITALIC),
                                                                                    toBoolean(eb.HTML_UNDERLINE));

                        /* controllo id plugin */
                        int idPlugin=-1;
                        if(!eb.BODY_PLUGIN_NAME.equals(""))
                        {
                            idPlugin=vPlugin.isPresentPlugin(eb.BODY_PLUGIN_NAME,eb.TIPO_DI_CONFIGURAZIONE,"");

                            if(idPlugin==-1)
                            {
                                idPlugin=vPlugin.addPlugin(eb.BODY_PLUGIN_NAME,config_NAME_ALIAS,eb.BODY_PLUGIN_TAG);
                            }
                        }

                        int typeSeparetor=0;
                        switch(eb.SUB_TYPE)
                        {
                            case ReportObject.LINE_BLANK:
                                typeSeparetor=1;
                                break;

                            case ReportObject.LINE_BREAK:
                                typeSeparetor=2;
                                break;

                            case ReportObject.LINE_SEPARETOR:
                                typeSeparetor=3;
                                break;
                        }

                        /* lettura kit contatori */
                        //System.out.println("devo cercare: "+eb.BODY_TAGCOUNTER);
                        int idCounter=0;
                        boolean trovato=false;

                        for(int k=0;k<V_Counters.size();k++)
                        {
                            ADAMS_TAB_KIT_CONTATORI appo=(ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(k);
				if(idReportKitContatori!=appo.IDCOUNTER)
				{
					/* lo salto */
					continue;
				}

				/*if(DEBUG)
	                        {
	                            System.out.println("-->COUNTER_KIT.TAG: "+appo.TAG+"  -->COUNTER_KIT.IDCOUNTER: "+appo.IDCOUNTER);
	                        }*/
                            for(int l=0;l<appo.V_COUNTERKIT.size();l++)
                            {
                                ADAMS_TAB_COUNTERKIT cKit=(ADAMS_TAB_COUNTERKIT)appo.V_COUNTERKIT.elementAt(l);
                                /*if(DEBUG)
	                        {
	                            System.out.println("	-->CONUTER_TAG: "+cKit.TAG+"	-->CONUTER_INDEX_CLIENT: "+cKit.COUNTERINDEX+"	-->CONUTER_INDEX_CLIENT_su VHelp: "+(short)vHELP_COUNTER_INDEX.getValue(cKit.COUNTERINDEX)+"	-->CONUTER_INDEX_SERVER: "+cKit.index+1);
	                        }*/
                                if(cKit.TAG.equals(eb.BODY_TAGCOUNTER))
                                {
                                    idCounter=cKit.index;
                                    //System.out.println("eccolo: "+idCounter);
                                    trovato=true;
                                    break;
                                }
                                if(trovato)
                                {
                                    break;
                                }
                            }
                        }

                        idCounter+=1;

                        ntmComplete.reportSequence[i].bdyV[j].u=new DATA_RS_BODY(eb.BODY_SOURCE+1,
                                                                                    eb.BODY_IDKEY,
                                                                                    idCounter,
                                                                                    eb.BODY_IDSCRIPT,
                                                                                    idPlugin,
                                                                                    set_String_toChar(eb.BODY_USERVALUE,_REPO_USER_LEN),
                                                                                    toBoolean(eb.BODY_ISURL),
                                                                                    toBoolean(eb.BODY_TOTALIZER),
                                                                                    toBoolean(eb.BODY_REPEATKEY),
                                                                                    eb.BODY_MAXDECIMALDIGIT,
                                                                                    typeSeparetor);

                        if(DEBUG)
                        {
                            System.out.println("-->Body BODY_TAGCOUNTER: "+eb.BODY_TAGCOUNTER);
                            System.out.println("-->Body: "+eb.OBJECT_TAG);
                            System.out.println("-->Body.idCounter: "+idCounter);
                        }
                    }
                }else
                {
                    if(DEBUG)
                        System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> NON CI SONO BODY");

                    bodyNULL(i);
                }

                /**********************************  PRENDO I TOTALIZER **********************************************************/
                Vector vTot=schema.getVectorObject(ReportObject.TOTALIZER);
                int len_vTot=vTot.size();
                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> sono presenti "+len_vTot+" TOTALIZER");
                }
                if(len_vTot>0)
                {
                    nDATA_RSO_TOTALIZER=len_vTot;
                    ntmComplete.reportSequence[i].totV=new DATA_RSO_TOTALIZER[nDATA_RSO_TOTALIZER];
                    for(int j=0;j<nDATA_RSO_TOTALIZER;j++)
                    {
                        ReportObject.elementoBase eb=(ReportObject.elementoBase)vTot.elementAt(j);
                        ntmComplete.reportSequence[i].totV[j]=new DATA_RSO_TOTALIZER();

                        ntmComplete.reportSequence[i].totV[j].c=new DATA_RS_COMMON( eb.IDOBJECT,
                                                                                    eb.IDREPORTSCHEMA,
                                                                                    eb.TYPE,
                                                                                    set_String_toChar(eb.OBJECT_TAG,_REPO_LABEL_LEN));

                        ntmComplete.reportSequence[i].totV[j].html=new DATA_RS_HTML(eb.HTML_VSIZE,
                                                                                    eb.HTML_HSIZE,
                                                                                    eb.HTML_VALIGN,
                                                                                    eb.HTML_HALIGN,
                                                                                    toBoolean(eb.HTML_WRAP),
                                                                                    eb.HTML_FOREGROUND_R,
                                                                                    eb.HTML_FOREGROUND_G,
                                                                                    eb.HTML_FOREGROUND_B,
                                                                                    eb.HTML_BACKGROUND_R,
                                                                                    eb.HTML_BACKGROUND_G,
                                                                                    eb.HTML_BACKGROUND_B,
                                                                                    eb.HTML_STYLE,
                                                                                    eb.HTML_FONTSIZE,
                                                                                    toBoolean(eb.HTML_BOLD),
                                                                                    toBoolean(eb.HTML_ITALIC),
                                                                                    toBoolean(eb.HTML_UNDERLINE));

                        int typeSeparetor=0;
                        switch(eb.SUB_TYPE)
                        {
                            case ReportObject.LINE_BLANK:
                                typeSeparetor=1;
                                break;

                            case ReportObject.LINE_BREAK:
                                typeSeparetor=2;
                                break;

                            case ReportObject.LINE_SEPARETOR:
                                typeSeparetor=3;
                                break;
                        }

                        ntmComplete.reportSequence[i].totV[j].u=new DATA_RS_TOTALIZER(  eb.TOTALIZER_TRIGGER,
                                                                                        set_String_toChar(eb.TOTALIZER_LABEL,_REPO_LABEL_LEN),
                                                                                        toBoolean(eb.TOTALIZER_REDRAWHEADER),
                                                                                        toBoolean(eb.TOTALIZER_BORDERAROUND),
                                                                                        typeSeparetor,
                                                                                        toBoolean(eb.TOTALIZER_USESCRIPT),
											eb.TOTALIZER_IDSCRIPT);
											//typeSeparetor);

                        if(DEBUG)
                        {
                            System.out.println("-->Totalizer: "+eb.OBJECT_TAG);
                        }

                    }
                }
                else
                {

                    if(DEBUG)
                        System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> NON CI SONO TOTALIZER");

                    totalizerNULL(i);
                }

                /**********************************  PRENDO I FOOTER **********************************************************/
                Vector vFooter=schema.getVectorObject(ReportObject.FOOTER);
                int len_vFooter=vFooter.size();
                if(DEBUG)
                {
                    System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> sono presenti "+len_vFooter+" FOOTER");
                }
                if(len_vFooter>0)
                {
                    nDATA_RSO_FOOTER=len_vFooter;
                    ntmComplete.reportSequence[i].fooV=new DATA_RSO_FOOTER[nDATA_RSO_FOOTER];
                    for(int j=0;j<nDATA_RSO_FOOTER;j++)
                    {
                        ReportObject.elementoBase eb=(ReportObject.elementoBase)vFooter.elementAt(j);
                        ntmComplete.reportSequence[i].fooV[j]=new DATA_RSO_FOOTER();

                        ntmComplete.reportSequence[i].fooV[j].c=new DATA_RS_COMMON( eb.IDOBJECT,
                                                                                    eb.IDREPORTSCHEMA,
                                                                                    eb.TYPE,
                                                                                    set_String_toChar(eb.OBJECT_TAG,_REPO_LABEL_LEN));

                        ntmComplete.reportSequence[i].fooV[j].html=new DATA_RS_HTML(eb.HTML_VSIZE,
                                                                                    eb.HTML_HSIZE,
                                                                                    eb.HTML_VALIGN,
                                                                                    eb.HTML_HALIGN,
                                                                                    toBoolean(eb.HTML_WRAP),
                                                                                    eb.HTML_FOREGROUND_R,
                                                                                    eb.HTML_FOREGROUND_G,
                                                                                    eb.HTML_FOREGROUND_B,
                                                                                    eb.HTML_BACKGROUND_R,
                                                                                    eb.HTML_BACKGROUND_G,
                                                                                    eb.HTML_BACKGROUND_B,
                                                                                    eb.HTML_STYLE,
                                                                                    eb.HTML_FONTSIZE,
                                                                                    toBoolean(eb.HTML_BOLD),
                                                                                    toBoolean(eb.HTML_ITALIC),
                                                                                    toBoolean(eb.HTML_UNDERLINE));

                        int typeSeparetor=0;
                        switch(eb.SUB_TYPE)
                        {
                            case ReportObject.LINE_BLANK:
                                typeSeparetor=1;
                                break;

                            case ReportObject.LINE_BREAK:
                                typeSeparetor=2;
                                break;

                            case ReportObject.LINE_SEPARETOR:
                                typeSeparetor=3;
                                break;
                        }

                        ntmComplete.reportSequence[i].fooV[j].u=new DATA_RS_FOOTER( eb.FOOTER_SOURCE,
                                                                                    set_String_toChar(eb.FOOTER_LABEL,_REPO_LABEL_LEN),
                                                                                    set_String_toChar(eb.FOOTER_USERVALUE,_REPO_USER_LEN),
                                                                                    toBoolean(eb.FOOTER_ISURL),
                                                                                    typeSeparetor);

                        if(DEBUG)
                        {
                            System.out.println("-->Footer: "+eb.OBJECT_TAG);
                        }
                    }
                }else
                {
                    if(DEBUG)
                        System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> NON CI SONO FOOTER");

                    footerNULL(i);
                }
            }
        }
        else
        {
            if(DEBUG)
                System.out.println("ADAMS_storeConfig [get_REPORTCONFIG] ---> NON CI SONO REPORT");

            ntmComplete.reportSequence= new DATA_REPORTSCHEMA[1];
            ntmComplete.reportSequence[0]= new DATA_REPORTSCHEMA();
            ntmComplete.reportSequence[0].idReportSchema=-1;
            ntmComplete.reportSequence[0].tag=set_String_toChar("",_SHORT_DESC_LEN);
            ntmComplete.reportSequence[0].objO=new DATA_RSO_OBJECT();
            ntmComplete.reportSequence[0].objO.c=new DATA_RS_COMMON(0,
                                                                    0,
                                                                    0,
                                                                    set_String_toChar("",_REPO_LABEL_LEN));
            ntmComplete.reportSequence[0].objO.html=new DATA_RS_HTML(); //non utilizzato;
            ntmComplete.reportSequence[0].objO.u=new DATA_RS_OBJECT(    set_String_toChar("",_SHORT_DESC_LEN),
                                                                        toBoolean(0),
                                                                        0,
                                                                        toBoolean(0),
                                                                        0,
                                                                        toBoolean(0),
                                                                        set_String_toChar("",_SHORT_PLUGIN_NAME),
                                                                        0,
                                                                        0,
                                                                        0,
                                                                        0,
                                                                        0,
                                                                        0);
            headerNULL(0);
            bodyNULL(0);
            totalizerNULL(0);
            footerNULL(0);

        }
        //DEBUG=false;
        return true;
    }

    private void headerNULL(int i) //Raffo
    {
        ntmComplete.reportSequence[i].hdrV=new DATA_RSO_HEADER[1];
        ntmComplete.reportSequence[i].hdrV[0]=new DATA_RSO_HEADER();

        ntmComplete.reportSequence[i].hdrV[0].c=new DATA_RS_COMMON(0,
                                                                0,
                                                                -5,
                                                                set_String_toChar("",_REPO_LABEL_LEN));
        ntmComplete.reportSequence[i].hdrV[0].html=new DATA_RS_HTML();
        ntmComplete.reportSequence[i].hdrV[0].u=new DATA_RS_HEADER( set_String_toChar("",_REPO_LABEL_LEN),
                                                                    0,
                                                                    set_String_toChar("",_REPO_USER_LEN),
                                                                    toBoolean(0),
                                                                    0);
    }

    private void bodyNULL(int i) //Raffo
    {
        ntmComplete.reportSequence[i].bdyV=new DATA_RSO_BODY[1];
        ntmComplete.reportSequence[i].bdyV[0]=new DATA_RSO_BODY();

        ntmComplete.reportSequence[i].bdyV[0].c=new DATA_RS_COMMON(0,
                                                                0,
                                                                -5,
                                                                set_String_toChar("",_REPO_LABEL_LEN));
        ntmComplete.reportSequence[i].bdyV[0].html=new DATA_RS_HTML();
        ntmComplete.reportSequence[i].bdyV[0].u=new DATA_RS_BODY(   0,
                                                                    0,
                                                                    0,
                                                                    0,
                                                                    0,
                                                                    set_String_toChar("",_REPO_USER_LEN),
                                                                    toBoolean(0),
                                                                    toBoolean(0),
                                                                    toBoolean(0),
                                                                    0,
                                                                    0);
    }

    private void totalizerNULL(int i) //Raffo
    {
        ntmComplete.reportSequence[i].totV=new DATA_RSO_TOTALIZER[1];
        ntmComplete.reportSequence[i].totV[0]=new DATA_RSO_TOTALIZER();

        ntmComplete.reportSequence[i].totV[0].c=new DATA_RS_COMMON(0,
                                                                0,
                                                                -5,
                                                                set_String_toChar("",_REPO_LABEL_LEN));
        ntmComplete.reportSequence[i].totV[0].html=new DATA_RS_HTML();
        ntmComplete.reportSequence[i].totV[0].u=new DATA_RS_TOTALIZER(  0,
                                                                        set_String_toChar("",_REPO_LABEL_LEN),
                                                                        toBoolean(0),
                                                                        toBoolean(0),
                                                                        0,
                                                                        toBoolean(0),
                                                                        0);
    }

    private void footerNULL(int i) //Raffo
    {
        ntmComplete.reportSequence[i].fooV=new DATA_RSO_FOOTER[1];
        ntmComplete.reportSequence[i].fooV[0]=new DATA_RSO_FOOTER();
        ntmComplete.reportSequence[i].fooV[0].c=new DATA_RS_COMMON(0,
                                                                0,
                                                                -5,
                                                                set_String_toChar("",_REPO_LABEL_LEN));
        ntmComplete.reportSequence[i].fooV[0].html=new DATA_RS_HTML();
        ntmComplete.reportSequence[i].fooV[0].u=new DATA_RS_FOOTER( 0,
                                                                        set_String_toChar("",_REPO_LABEL_LEN),
                                                                        set_String_toChar("",_REPO_USER_LEN),
                                                                        toBoolean(0),
                                                                        0);
    }



   public boolean get_AlarmCONFIG() //Luca
   {
       //System.out.println("get_AlarmCONFIG( ...... )");

        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_ADAMSConfIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        if(vPlugin==null)
        {
            System.out.println("ADAMS_storeConfig [get_ADAMSConfIG] ----> NON SONO STATI CARICATI I PLUGIN.");
            return false;
        }


// ****************************************************************** Lettura ALARM_RELATION

        Vector V_ALL_RELATION_ALARM = new Vector();
        String itemName =("ID_ALARM_RELATION,DESCRIPTION,ID_CONDITION_SCRIPT,STR_CONDITION_PLUGIN,TIPO_DI_CONFIGURAZIONE,COUNTERS_KIT_ID,TIME_FRACTION_ELEMENT_ID,FAMILY_ID");

        String sel_read_RelationsAlarm = ("select "+itemName+" from tab_alarm_relation "+
                                            " where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"'" );

       //System.out.println();
       //System.out.println("get_AlarmCONFIG( ...... ) sel_read_RelationsAlarm --> "+sel_read_RelationsAlarm);
       //System.out.println();

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_RelationsAlarm,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next() )
                {

            // lettura tab_alarm_relation
                    ADAMS_TAB_ALARM_RELATION row_AlarmRelation = new ADAMS_TAB_ALARM_RELATION();
                    row_AlarmRelation.TIPO_DI_CONFIGURAZIONE = config_NAME_ALIAS;

                    row_AlarmRelation.ID_ALARM_RELATION = rs.getInt("ID_ALARM_RELATION");
                    String desc = rs.getString("DESCRIPTION");
                        if(desc != null)
                            row_AlarmRelation.DESCRIPTION = desc;

                    row_AlarmRelation.ID_CONDITION_SCRIPT   = rs.getInt("ID_CONDITION_SCRIPT");

                    String desc_pl = rs.getString("STR_CONDITION_PLUGIN");
                        if(desc_pl != null)
                            row_AlarmRelation.STR_CONDITION_PLUGIN = desc_pl;

                    row_AlarmRelation.COUNTERS_KIT_ID = rs.getInt("COUNTERS_KIT_ID");
                    row_AlarmRelation.TIME_FRACTION_ELEMENT_ID  = rs.getInt("TIME_FRACTION_ELEMENT_ID");
                    row_AlarmRelation.FAMILY_ID = rs.getInt("FAMILY_ID");


            // lettura tab_alarm_relation_elements

                    String itemName_RE =("ID_ELEMENT,ENABLED_DETAIL");
                    String sel_read_RE =("select "+itemName_RE+" from tab_alarm_relation_elements"+
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+
                                                        "' and ID_ALARM_RELATION = '"+row_AlarmRelation.ID_ALARM_RELATION+
                                                        "' order by POSITION_ELEMENT");

                    Statement SQLStatement_RE = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rs_RE  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_RE,SQLStatement_RE);

                    if(rs_RE != null)
                    {
                        while ( rs_RE.next() )
                        {
                            row_AlarmRelation.V_ALARM_REL_ELEMENTS.addElement(new Integer(rs_RE.getInt("ID_ELEMENT")));
                            row_AlarmRelation.V_ENABLE_DETAIL_ELEMENTS.addElement(new Integer(rs_RE.getInt("ENABLED_DETAIL")));
                        }
                    }

            // lettura tab_alarm_relation_handlers

                    String itemName_Rh =("ID_ALARM_GENERATOR");
                    String sel_read_Rh =("select "+itemName_Rh+" from tab_alarm_relation_handlers"+
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"'"+
                                                        " and ID_ALARM_RELATION = '"+row_AlarmRelation.ID_ALARM_RELATION+"'");

                    Statement SQLStatement_Rh = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rs_Rh  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_Rh,SQLStatement_Rh);

                    if(rs_Rh != null)
                    {
                        while ( rs_Rh.next() )
                        {
                            row_AlarmRelation.V_ALARM_HANDLERS.addElement(new Integer(rs_Rh.getInt("ID_ALARM_GENERATOR")));
                        }
                    }
                    //---------------------

                    V_ALL_RELATION_ALARM.addElement(row_AlarmRelation);

                    try
                    {
                       SQLStatement_RE.close();
                       SQLStatement_Rh.close();
                    }
                    catch(java.sql.SQLException exc)
                    {
                        System.out.println("SQL Operation " + exc.toString());
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

        }
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("(get_AlarmCONFIG) SQL Operation " + exc.toString());
        }

// ****************************************************************** END  Lettura ALARM_RELATION
// ======   Popolo Struttura ALARM_RELATION

        int SIZE_REL_ALARM = V_ALL_RELATION_ALARM.size();

        if(SIZE_REL_ALARM > 0)
        {
            ntmComplete.alarmRelationSequence = new ALARM_RELATION[SIZE_REL_ALARM];

            for(int i=0; i< SIZE_REL_ALARM; i++ )
            {
                ADAMS_TAB_ALARM_RELATION row_AlarmRelation = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);

                //Relation Elements e relationElementsEnabled
                int[] relationElements          = new int[_MAX_DIMENSION];
                int[] relationElementsEnabled   = new int[_MAX_DIMENSION];

                //inizializzazione relationElements e relationElementsEnabled
                for(int z=0; z<relationElements.length; z++)
                {
                    relationElements[z] = 0;
                    relationElementsEnabled[z] = 0;
                }

                int SIZE_A_RelElem = row_AlarmRelation.V_ALARM_REL_ELEMENTS.size();

                if( SIZE_A_RelElem > _MAX_DIMENSION )
                {
                    SIZE_A_RelElem = _MAX_DIMENSION;
                    System.out.println("Attenzione  ----> superato il limite degli elementi della Alarm Relation, _MAX_DIMENSION ="+_MAX_DIMENSION);
                }

                for(int x=0; x<SIZE_A_RelElem; x++)
                {
                    relationElements[x]         = (((Integer)row_AlarmRelation.V_ALARM_REL_ELEMENTS.elementAt(x)).intValue());
                    relationElementsEnabled[x]  = (((Integer)row_AlarmRelation.V_ENABLE_DETAIL_ELEMENTS.elementAt(x)).intValue());
                }

        //*******************************************************************************
        // RELATION_HANDLERS
                int[] alarmHandlers    = new int[_MAX_ALARM_GENERATOR];

                // inizializzazione alarmHandlers
                for(int z=0; z<alarmHandlers.length; z++)
                    alarmHandlers[z] = -1;

                int SIZE_V_alarmHandlers = row_AlarmRelation.V_ALARM_HANDLERS.size();
                if( SIZE_V_alarmHandlers > _MAX_ALARM_GENERATOR )
                {
                    SIZE_A_RelElem = _MAX_ALARM_GENERATOR;
                    System.out.println("Attenzione  ----> superato il limite degli alarmi per Alarm Relation, _MAX_ALARM_GENERATOR ="+_MAX_ALARM_GENERATOR);
                }

                for(int z=0; z<SIZE_V_alarmHandlers; z++)
                {
                    alarmHandlers[z] = (((Integer)row_AlarmRelation.V_ALARM_HANDLERS.elementAt(z)).intValue());
                }

        //*******************************************************************************


                /* controllo id plugin */
                int idPlugin=-1;
                if(!row_AlarmRelation.STR_CONDITION_PLUGIN.equals(""))
                {
                    idPlugin=vPlugin.isPresentPlugin(row_AlarmRelation.STR_CONDITION_PLUGIN,row_AlarmRelation.TIPO_DI_CONFIGURAZIONE,"");
                    if(idPlugin == -1)
                    {
                        idPlugin=vPlugin.addPlugin(row_AlarmRelation.STR_CONDITION_PLUGIN,row_AlarmRelation.TIPO_DI_CONFIGURAZIONE,"");
                    }
                }


                ntmComplete.alarmRelationSequence[i] =  new ALARM_RELATION ( row_AlarmRelation.ID_ALARM_RELATION,
                                                                            set_String_toChar(row_AlarmRelation.DESCRIPTION,_LONG_DESC_LEN),
                                                                            relationElements,
                                                                            relationElementsEnabled,
                                                                            alarmHandlers,
                                                                            row_AlarmRelation.TIME_FRACTION_ELEMENT_ID,
                                                                            row_AlarmRelation.FAMILY_ID,
                                                                            row_AlarmRelation.COUNTERS_KIT_ID,
                                                                            row_AlarmRelation.ID_CONDITION_SCRIPT,
                                                                            idPlugin);
            }
        }
        else
            ntmComplete.alarmRelationSequence = new ALARM_RELATION[0];

        if(DEBUG_ALARM)
        {
            System.out.println("************ ALARM_RELATION Sequence length************* ==> "+ntmComplete.alarmRelationSequence.length);
            for(int i=0; i<ntmComplete.alarmRelationSequence.length; i++)
            {
                ALARM_RELATION alarm_print = ntmComplete.alarmRelationSequence[i];

                System.out.println("alarm_R_print.idAlarmRelation = "+alarm_print.idAlarmRelation);
                System.out.println("alarm_R_print.description = "+new String(alarm_print.description).trim());
                System.out.println("alarm_R_print.countersKitId = "+alarm_print.countersKitId);
                System.out.println("alarm_R_print.idConditionScript = "+alarm_print.idConditionScript);
                System.out.println("alarm_R_print.idConditionPlugin = "+alarm_print.idConditionPlugin);
                System.out.println("alarm_R_print.timeFractionElementId = "+alarm_print.timeFractionElementId);
                System.out.println("alarm_R_print.familyId = "+alarm_print.familyId);

                String symbol_rel = "::";
                System.out.print("relationElements = ");
                for(int y=0; y<alarm_print.relationElements.length;y++)
                {
                    if(alarm_print.relationElements[y] != 0)
                        System.out.print(alarm_print.relationElements[y]+symbol_rel);
                }
                System.out.println();

                String symbol_rel_1 = ",";
                System.out.print("alarmHandlers = ");
                for(int y=0; y<alarm_print.alarmHandlers.length;y++)
                {
                    if(alarm_print.alarmHandlers[y] != -1)
                        System.out.print(alarm_print.alarmHandlers[y]+symbol_rel_1);
                }
                System.out.println();
                System.out.println();
            }
        }

// ======  END Popolo Struttura ALARM_RELATION

// *************** Lettura ALARM_GENERATOR ********************************************************

        Vector V_ALARMS = new Vector();
        String itemName_2 =("ID_ALARM_GENERATOR,SHORT_DESCRIPTION,LONG_DESCRIPTION,STR_PLUGIN,THRESHOLD_MANAGEMENT");
        String sel_read_Alarms = ("select "+itemName_2+" from tab_alarm_generator where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"'");

        //System.out.println();
       //System.out.println("sel_read_Alarms --> "+sel_read_Alarms);

        Statement SQLStatement_AG = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs_a  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_Alarms,SQLStatement_AG);

        if(rs_a != null)
        {
            try
            {
                while ( rs_a.next() )
                {
                    ADAMS_TAB_ALARM_GENERATOR row_Alarms = new ADAMS_TAB_ALARM_GENERATOR(config_NAME_ALIAS);

                    row_Alarms.ID_ALARM_GENERATOR = rs_a.getInt("ID_ALARM_GENERATOR");

                    String s_desc = rs_a.getString("SHORT_DESCRIPTION");
                        if(s_desc != null)
                            row_Alarms.SHORT_DESCRIPTION = s_desc;

                    String desc = rs_a.getString("LONG_DESCRIPTION");
                        if(desc != null)
                            row_Alarms.LONG_DESCRIPTION = desc;

                    String pl = rs_a.getString("STR_PLUGIN");
                        if(pl != null)
                            row_Alarms.STR_PLUGIN = pl;

                    char TM = rs_a.getString("THRESHOLD_MANAGEMENT").charAt(0);
                    if(TM != ' ')
                        row_Alarms.THRESHOLD_MANAGEMENT = TM;

                    // lettura tab_alarm_generator_thresholds

                    String itemName_Th =("ID_THRESHOLD_GENERATOR");
                    String sel_read_Th =("select "+itemName_Th+" from tab_alarm_generator_thresholds"+
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"'"+
                                                        " and ID_ALARM_GENERATOR = '"+row_Alarms.ID_ALARM_GENERATOR+"'");

                    Statement SQLStatement_Th = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rs_Th  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_Th,SQLStatement_Th);

                    if(rs_Th != null)
                    {
                        while ( rs_Th.next() )
                        {
                            row_Alarms.V_ALARM_THRESHOLD.addElement(new Integer(rs_Th.getInt("ID_THRESHOLD_GENERATOR")));
                        }
                    }

                    V_ALARMS.addElement(row_Alarms);

                    try
                    {
                       SQLStatement_Th.close();
                    }
                    catch(java.sql.SQLException exc)
                    {
                        System.out.println("(read_RelationsAlarm) SQL Operation " + exc.toString());
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
        try
        {
            SQLStatement_AG.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("(read_RelationsAlarm) SQL Operation " + exc.toString());
        }

//*******************************************************
// ======   Popolo Struttura ALARM_GENERATOR

        int SIZE_ALARM = V_ALARMS.size();
        if(SIZE_ALARM > 0)
        {
            ntmComplete.alarmGeneratorSequence = new ALARM_GENERATOR[SIZE_ALARM];

            for(int i=0; i<SIZE_ALARM; i++)
            {
                ADAMS_TAB_ALARM_GENERATOR row_AlarmGenerator = (ADAMS_TAB_ALARM_GENERATOR)V_ALARMS.elementAt(i);

                //ThresholdGenerator
                int[] idThresholdGenerator = new int[_MAX_THRESHOLD_GENERATOR];

                // inizializzazione ThresholdGenerator
                for(int z=0; z<idThresholdGenerator.length; z++)
                    idThresholdGenerator[z] = -1;

                int SIZE_A_Th = row_AlarmGenerator.V_ALARM_THRESHOLD.size();

                if( SIZE_A_Th > _MAX_THRESHOLD_GENERATOR )
                {
                    SIZE_A_Th = _MAX_THRESHOLD_GENERATOR;
                    System.out.println("Attenzione  ----> superato il limite delle soglie per alarm , _MAX_THRESHOLD_GENERATOR ="+_MAX_THRESHOLD_GENERATOR);
                }

                for(int x=0; x<SIZE_A_Th; x++)
                    idThresholdGenerator[x] = (((Integer)row_AlarmGenerator.V_ALARM_THRESHOLD.elementAt(x)).intValue());

                /* controllo id plugin */
                int idPlugin_AG=-1;
                if(!row_AlarmGenerator.STR_PLUGIN.equals(""))
                {
                    idPlugin_AG = vPlugin.isPresentPlugin(row_AlarmGenerator.STR_PLUGIN,row_AlarmGenerator.TIPO_DI_CONFIGURAZIONE,"");
                    if(idPlugin_AG == -1)
                    {
                        idPlugin_AG = vPlugin.addPlugin(row_AlarmGenerator.STR_PLUGIN,row_AlarmGenerator.TIPO_DI_CONFIGURAZIONE,"");
                    }
                }

                boolean threshold_man = false;
                if ((row_AlarmGenerator.THRESHOLD_MANAGEMENT == 'Y') && (SIZE_A_Th > 0)) //non ci sono Soglie Configurate.... nel caso cancellate nella delete delle soglie resetto thresholdManagement;
                    threshold_man = true;

                ntmComplete.alarmGeneratorSequence[i] = new ALARM_GENERATOR(row_AlarmGenerator.ID_ALARM_GENERATOR,
                                                                            set_String_toChar(row_AlarmGenerator.SHORT_DESCRIPTION,_SHORT_DESC_LEN),
                                                                            set_String_toChar(row_AlarmGenerator.LONG_DESCRIPTION,_LONG_DESC_LEN),
                                                                            idPlugin_AG,
                                                                            threshold_man,
                                                                            idThresholdGenerator);
            }
        }
        else
            ntmComplete.alarmGeneratorSequence = new ALARM_GENERATOR[0];

        if(DEBUG_ALARM)
        {
            System.out.println("************ ALARM_GENERATOR Sequence length ************* => " +ntmComplete.alarmGeneratorSequence.length);

            for(int i=0; i<ntmComplete.alarmGeneratorSequence.length; i++)
            {
                ALARM_GENERATOR alarm_print = ntmComplete.alarmGeneratorSequence[i];

                System.out.println("alarm_G_print.idAlarmGenerator = "+alarm_print.idAlarmGenerator);
                System.out.println("alarm_G_print.shortDescription = "+new String(alarm_print.shortDescription).trim());
                System.out.println("alarm_G_print.longDescription = "+new String(alarm_print.longDescription).trim());
                System.out.println("alarm_G_print.idPlugin = "+alarm_print.idPlugin);
                System.out.println("alarm_G_print.thresholdManagement = "+alarm_print.thresholdManagement);

                String symbol_rel_1 = ",";
                System.out.print("idThresholdGenerator = ");
                for(int y=0; y<alarm_print.idThresholdGenerator.length;y++)
                {
                    if(alarm_print.idThresholdGenerator[y] != -1)
                        System.out.print(alarm_print.idThresholdGenerator[y]+symbol_rel_1);
                }
                System.out.println();
                System.out.println();
            }
        }

// ======   END Popolo Struttura ALARM_GENERATOR

// *************** Lettura THRESHOLD_GENERATOR

        Vector V_TH = new Vector();

        String itemName_th =("ID_THRESHOLD_GENERATOR, DESCRIPTION, TYPE_THRESHOLD, ENABLE_HOLYDAY_THRESHOLD, STR_PLUGIN, THRESHOLD_PERSISTENCE, HOURS_AGGREGATE");
        String sel_read_th = ("select "+itemName_th+" from tab_alarm_threshold_generator where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"'");

       //System.out.println();
       //System.out.println("sel_read_th --> "+sel_read_th);

        Statement SQLStatement_th = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs_th  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_th,SQLStatement_th);

        if(rs_th != null)
        {
            try
            {
                while ( rs_th.next() )
                {
                    ADAMS_TAB_ALARM_THRESHOLD row_th = new ADAMS_TAB_ALARM_THRESHOLD(config_NAME_ALIAS);

                    row_th.ID_THRESHOLD_GENERATOR = rs_th.getInt("ID_THRESHOLD_GENERATOR");

                    String s_desc = rs_th.getString("DESCRIPTION");
                        if(s_desc != null)
                            row_th.DESCRIPTION = s_desc;

                    row_th.TYPE_THRESHOLD = rs_th.getInt("TYPE_THRESHOLD");

                    char H = rs_th.getString("ENABLE_HOLYDAY_THRESHOLD").charAt(0);
                    if(H != ' ')
                        row_th.ENABLE_HOLYDAY_THRESHOLD = H;

                    String pl = rs_th.getString("STR_PLUGIN");
                        if(pl != null)
                            row_th.STR_PLUGIN = pl;

                    row_th.THRESHOLD_PERSISTENCE = rs_th.getInt("THRESHOLD_PERSISTENCE");
                    row_th.HOURS_AGGREGATE = rs_th.getInt("HOURS_AGGREGATE");

                    V_TH.addElement(row_th);
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        try
        {
            SQLStatement_th.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("(read_RelationsAlarm) SQL Operation " + exc.toString());
        }

// ======  Popolo Struttura THRESHOLD_GENERATOR

        int SIZE_TH = V_TH.size();
        if(SIZE_TH > 0)
        {
            ntmComplete.thresholdGeneratorSequence = new THRESHOLD_GENERATOR[SIZE_TH];

            for(int i=0; i<SIZE_TH; i++)
            {
                ADAMS_TAB_ALARM_THRESHOLD row_Threshold = (ADAMS_TAB_ALARM_THRESHOLD)V_TH.elementAt(i);

                /* controllo id plugin */
                int idPlugin_TH=-1;
                if(!row_Threshold.STR_PLUGIN.equals(""))
                {
                    idPlugin_TH = vPlugin.isPresentPlugin(row_Threshold.STR_PLUGIN,row_Threshold.TIPO_DI_CONFIGURAZIONE,"");
                    if(idPlugin_TH == -1)
                    {
                        idPlugin_TH = vPlugin.addPlugin(row_Threshold.STR_PLUGIN,row_Threshold.TIPO_DI_CONFIGURAZIONE,"");
                    }
                }

                boolean holiday = false;
                if( row_Threshold.ENABLE_HOLYDAY_THRESHOLD == 'Y')
                    holiday = true;

                ntmComplete.thresholdGeneratorSequence[i] = new THRESHOLD_GENERATOR(row_Threshold.ID_THRESHOLD_GENERATOR,
                                                                                    set_String_toChar(row_Threshold.DESCRIPTION,_LONG_DESC_LEN),
                                                                                    row_Threshold.TYPE_THRESHOLD,
                                                                                    holiday,
                                                                                    idPlugin_TH,
                                                                                    row_Threshold.THRESHOLD_PERSISTENCE,
                                                                                    row_Threshold.HOURS_AGGREGATE);
            }
        }
        else
            ntmComplete.thresholdGeneratorSequence = new THRESHOLD_GENERATOR[0];

        if(DEBUG_ALARM)
        {
            System.out.println("************ THRESHOLD_GENERATOR Sequence length ************* => "+ntmComplete.thresholdGeneratorSequence.length);

            for(int i=0; i<ntmComplete.thresholdGeneratorSequence.length; i++)
            {
                THRESHOLD_GENERATOR th_print = ntmComplete.thresholdGeneratorSequence[i];

                System.out.println("th_print.idThresholdGenerator = "+th_print.idThresholdGenerator);
                System.out.println("th_print.description = "+new String(th_print.description).trim());
                System.out.println("th_print.typeThreshold = "+th_print.typeThreshold);
                System.out.println("th_print.enableHolydayThreshold = "+th_print.enableHolydayThreshold);
                System.out.println("th_print.idPlugin = "+th_print.idPlugin);
                System.out.println("th_print.thresholdPersistence = "+th_print.thresholdPersistence);
                System.out.println("th_print.hoursAggregate = "+th_print.hoursAggregate);

                System.out.println();
            }
        }
// ====== END Popolo Struttura THRESHOLD_GENERATOR

        //System.out.println("fine  get_AlarmCONFIG ----");

        return true;
   }



   public boolean get_ADAMSConfIG(javax.swing.JLabel labelStatus,boolean ADD_FREE_RELATION) //Luca
    {
        VectorHelp vHELP_GUI = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_GUI);
        VectorHelp vHELP_COMPARE = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_GENERIC);

        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_ADAMSConfIG] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        if(vPlugin==null)
        {
            System.out.println("ADAMS_storeConfig [get_ADAMSConfIG] ----> NON SONO STATI CARICATI I PLUGIN.");
            return false;
        }

         /// *************   Relazioni *****************************/

        ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG_1 = new ADAMS_Vector_TAB_CONFIG(config_NAME_ALIAS);

        V_TAB_CONFIG_1.read_AllItem(ADAMS_GlobalParam.read_TE);
        int SIZE = V_TAB_CONFIG_1.size();

        for(int i=0;i<SIZE;i++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG_1.elementAt(i);
            if(row_TAB_CONFIG != null)
            {
                labelStatus.setText("Load Configuration Traffic Elements... ["+i+"]");
                row_TAB_CONFIG.read_LISTA_RELAZIONI();

                for(int x=0; x<row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); x++)
                {
                    vRelation.addRelation((ADAMS_TAB_RELAZIONI_ELEMENTO)row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(x),row_TAB_CONFIG.V_LISTA_RELAZIONI,false);
                }
            }
        }

        // ********************* Relazioni GHOST
        ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG_GHOST = new ADAMS_Vector_TAB_CONFIG(config_NAME_ALIAS);
        V_TAB_CONFIG_GHOST.read_AllItem(ADAMS_GlobalParam.read_GHOST);

        int SIZE_GHOST = V_TAB_CONFIG_GHOST.size();
        for(int i=0;i<SIZE_GHOST;i++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG_ghost = (ADAMS_TAB_CONFIG)V_TAB_CONFIG_GHOST.elementAt(i);
            if(row_TAB_CONFIG_ghost != null)
            {
                row_TAB_CONFIG_ghost.read_LISTA_RELAZIONI();
                for(int x=0; x<row_TAB_CONFIG_ghost.V_LISTA_RELAZIONI.size(); x++)
                    vRelation.addRelation((ADAMS_TAB_RELAZIONI_ELEMENTO)row_TAB_CONFIG_ghost.V_LISTA_RELAZIONI.elementAt(x),row_TAB_CONFIG_ghost.V_LISTA_RELAZIONI,true);
            }
        }

        // ********************* END  Relazioni GHOST

        //vRelation.printALLRelazioni();

        /// *************  END Relazioni *****************************/

        int nDATA_RELATIONS;

        if (ADD_FREE_RELATION == true)
        	nDATA_RELATIONS             = vRelation.size()+1;
        else
        	nDATA_RELATIONS             = vRelation.size();

        ntmComplete.relationSequence    = new DATA_RELATIONS[nDATA_RELATIONS];

        Vector V_ID_DEFAULT_REST = V_TAB_CONFIG_1.getIDELEMENTO_DEFAULT_RESTRICTION();

        for(int c=0; c<vRelation.size(); c++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO elem_relation = (ADAMS_TAB_RELAZIONI_ELEMENTO)vRelation.elementAt(c);

            // ********  restrinzioni relazioni ********
            int[] restrictions = new int[_MAX_RESTRICTIONS];


            //AGGIUNGO ---- Restrizioni di default ************************

            for(int dr=0; dr<V_ID_DEFAULT_REST.size(); dr++ )
                elem_relation.V_RESTRIZIONI.addElement((Integer)V_ID_DEFAULT_REST.elementAt(dr));

            //////////////// ---- ************************

            int SIZE_RESTRIZIONI = elem_relation.V_RESTRIZIONI.size();

            //System.out.println();
            //System.out.println("RELAZIONE ==>"+elem_relation.RELATION_NAME);

            //AGGIUNGO ---- Restrizioni selezionate  ************************
            for(int rs=0; rs<restrictions.length; rs++)
            {
                if(rs < SIZE_RESTRIZIONI)
                    restrictions[rs] = ((Integer)elem_relation.V_RESTRIZIONI.elementAt(rs)).intValue();
                else
                    restrictions[rs] = -1;

                /*if(restrictions[rs] != -1)
                    System.out.print(" - "+restrictions[rs]);*/
            }

            // ********  Aggiungo restrinzioni obbligatorie  *******
            int[] tiedRestrictions = new int[_MAX_RESTRICTIONS];
            int SIZE_RESTRIZIONI_OBBL = elem_relation.V_RESTRIZIONI_OBBL.size();
            for(int rsb=0; rsb<tiedRestrictions.length; rsb++)
            {
                if(rsb < SIZE_RESTRIZIONI_OBBL)
                    tiedRestrictions[rsb] = ((Integer)elem_relation.V_RESTRIZIONI_OBBL.elementAt(rsb)).intValue();
                else
                    tiedRestrictions[rsb] = 0;

            }

            // --- AGGIUNGO se non presenti nelle restrictions gli ID tiedRestrictions.
            for(int rsb1=0; rsb1<tiedRestrictions.length; rsb1++)
            {
                int ID_REST_OBB = tiedRestrictions[rsb1];
                if(ID_REST_OBB == 0) // tiedRestrictions finite.
                    break;

                for(int rs1=0; rs1<restrictions.length; rs1++)
                {
                    int ID_REST = restrictions[rs1];
                    if(ID_REST == -1)//aggiungo ID_REST_OBB. a restrictions[..]
                    {
                        restrictions[rs1] = ID_REST_OBB;
                        break;
                    }
                    else if(ID_REST == ID_REST_OBB) // ID_REST_OBB e' gia presente in restrictions[..]
                        break;
                }
            }
            // END --- AGGIUNGO se non presenti le tiedRestrictions presenti nelle restrictions


            //System.out.println("PRIMA elem_relation.DIREZIONE="+elem_relation.DIREZIONE);
            int idDirezione=0;
            if(elem_relation.DIREZIONE>=40) //Raffo, per gestione id direzione su restruizioni
            {
                idDirezione=elem_relation.DIREZIONE-40;
                //il 40 perchÃ¨ attualmente gli id delle direzioni partono da 40 anzichÃ¨ 1.
            }else
            {
                idDirezione=elem_relation.DIREZIONE;
            }
            //System.out.println("DOPO elem_relation.DIREZIONE="+idDirezione);
            ntmComplete.relationSequence[c]     = new DATA_RELATIONS(   elem_relation.idRelation,
                                                                    elem_relation.idParentRelation,
                                                                    elem_relation.idFirstElement,
                                                                    elem_relation.idSecondElement,
                                                                    idDirezione,
                                                                    toBoolean(elem_relation.HEXOUTPUT),
                                                                    toBoolean(elem_relation.NETWORK),
                                                                    toBoolean(elem_relation.GHOST),
                                                                    elem_relation.nextLevelRelations,
                                                                    restrictions,
                                                                    tiedRestrictions,
                                                                    false);

        }
        /// **********************************************************/

        // FREE FORMAT RELATION
        //System.out.println("AGGIUNGO FREE FORMAT RELATION -->"+ADD_FREE_RELATION);
        if (ADD_FREE_RELATION == true)
        {
        	int[] restrictions = new int[_MAX_RESTRICTIONS];
        	int[] nextLevelRelations = new int[_NEXTLEVEL_RELATIONS];
        	int[] tiedRestrictions = new int[_MAX_RESTRICTIONS];

        	for(int i=0; i<tiedRestrictions.length; i++)
        		tiedRestrictions[i] = 0;

        	for(int i=0; i<nextLevelRelations.length; i++)
                nextLevelRelations[i] = 0;

        	for(int i=0; i<_MAX_RESTRICTIONS; i++ )
        		restrictions[i] = -1;

        	//Aggiungo Restrizioni di DEFAULT
        	for(int dr=0; dr<V_ID_DEFAULT_REST.size(); dr++ )
        	{
        		restrictions[dr] = ((Integer)V_ID_DEFAULT_REST.elementAt(dr));
        		//System.out.println("Restrizione di DEFAULT --> "+restrictions[dr]);
        	}


        	DATA_RELATIONS data_relations_FREE = new DATA_RELATIONS(ADAMS_GlobalParam._RELATION_FREEFORMAT_ID,
												                    0,
												                    0,
												                    0,
												                    0,
												                    false,
												                    false,
												                    false,
												                    nextLevelRelations,
												                    restrictions,
												                    tiedRestrictions,
												                    true);

        	ntmComplete.relationSequence[nDATA_RELATIONS-1]     = data_relations_FREE;

        }


        // ****** Traffic Element ******

        if(DEBUG)
            System.out.println("   ****** i TRAFFIC Elements sono "+SIZE+"  ******");

        int nDATA_DATAELEMENT    = V_TAB_CONFIG_1.size();

        if(nDATA_DATAELEMENT > 0)
        {
            ntmComplete.elementSequence = new DATA_DATAELEMENT[nDATA_DATAELEMENT];
            for(int i=0; i<nDATA_DATAELEMENT; i++)
            {

                ADAMS_TAB_CONFIG row_TE = (ADAMS_TAB_CONFIG)V_TAB_CONFIG_1.elementAt(i);

                if(row_TE != null)
                {


                    // -------------------- LISTA VALORI ------------------------------

                    double[] valueList      = new double[_MAX_OPTIONS];
                    char[][] valueListLabel = new char[_MAX_OPTIONS][_OPTION_LABEL_LEN];

                    row_TE.read_LISTA_VALORI(); //System.out.println("read_LISTA_VALORI()");
                    int SIZE_ListaValori = row_TE.V_LISTA_VALORI.size();

                    for(int x=0; x<_MAX_OPTIONS;x++)
                    {
                        if(x < SIZE_ListaValori)
                        {
                            ADAMS_TAB_VALORI_ELEMENTO Val_Elem = (ADAMS_TAB_VALORI_ELEMENTO)(row_TE.V_LISTA_VALORI.elementAt(x));
                            valueList[x] = (double)Val_Elem.get_COD_VALORE();
                            valueListLabel[x] = set_String_toChar(Val_Elem.get_DESC_VALORE(),_OPTION_LABEL_LEN);

                            //System.out.println("Val_Elem --->"+Val_Elem.get_DESC_VALORE());
                        }
                        else
                        {
                            valueList[x] = 0.0;
                            valueListLabel[x] = set_String_toChar("",_OPTION_LABEL_LEN);
                        }
                    }

                    // -------------------- Aggregate Restrictions ------------------------------
                    int[] aggregateRestrictions = new int[_MAX_AGGREGATE_RESTR];
                    row_TE.read_AGGREGAZ_ELEM_RESTRIZ(); //System.out.println("read_AGGREGAZ_ELEM_RESTRIZ()");
                    int SIZE_aggreg = row_TE.V_AGGREGAZ_ELEM_RESTRIZ.size();

                    for(int y=0; y<_MAX_AGGREGATE_RESTR; y++)
                    {
                        if(y < SIZE_aggreg)
                            aggregateRestrictions[y] = ((Integer)row_TE.V_AGGREGAZ_ELEM_RESTRIZ.elementAt(y)).intValue();
                        else
                            aggregateRestrictions[y] = 0;
                    }


                    // -------------------- Exceptions ------------------------------
                    int[] exceptions = new int[_MAX_EXCEPTIONS];
                    row_TE.read_LISTA_EXCEPTION();  //System.out.println("read_LISTA_EXCEPTION()");
                    int SIZE_exception = row_TE.V_LISTA_EXCEPTION.size();

                    for(int z=0; z<_MAX_EXCEPTIONS; z++)
                    {
                        if(z < SIZE_exception)
                            exceptions[z] = ((Integer)row_TE.V_LISTA_EXCEPTION.elementAt(z)).intValue();
                        else
                            exceptions[z] = 0;
                    }

                    //-------------------- valueShifter ------------------------------
                    char[][] valueShifter = new char[_VALSHIFTER_MAX][_VALSHIFTER_LEN*3];
                    row_TE.read_LISTA_VALORI_SOSTITUZ(); //System.out.println("read_LISTA_VALORI_SOSTITUZ()");
                    int SIZE_valueShifter = row_TE.V_LISTA_VALORI_SOSTITUZ.size();

                    for(int q=0; q<_MAX_EXCEPTIONS; q++)
                    {
                        if(q < SIZE_valueShifter)
                            valueShifter[q] = set_String_toChar_noEND(((String)row_TE.V_LISTA_VALORI_SOSTITUZ.elementAt(q)),_VALSHIFTER_LEN*3);
                        else
                            valueShifter[q] = set_String_toChar_noEND("000000000000000000000000",_VALSHIFTER_LEN*3);
                    }


                    // -------------------- Scripts ------------------------------
                    int[] scripts = new int[_MAX_TE_SCRIPTS];
                    row_TE.read_LISTA_SCRIPTS();
                    int SIZE_Scripts = row_TE.V_LISTA_SCRIPTS.size();

                    //System.out.println("_MAX_TE_SCRIPTS="+_MAX_TE_SCRIPTS);

		    for(int j=0; j<_MAX_TE_SCRIPTS; j++)
                    {
			scripts[j]=0;
		    }

                    for(int j=0; j<_MAX_TE_SCRIPTS; j++)
                    {
                        if(j < SIZE_Scripts)
                        {
                            ADAMS_TAB_SCRIPTS_LISTA script_appo =(ADAMS_TAB_SCRIPTS_LISTA)(row_TE.V_LISTA_SCRIPTS.elementAt(j));
                            int type=script_appo.get_SCRIPT_TYPE();
                            int id=script_appo.get_SCRIPT();
                            scripts[type]=id;
                            //System.out.println("type="+type);
                            //System.out.println("id="+id);
                        }else
                            break;
                        /*if(j < SIZE_Scripts)
                        {
                            ADAMS_TAB_SCRIPTS_LISTA script_appo =(ADAMS_TAB_SCRIPTS_LISTA)(row_TE.V_LISTA_SCRIPTS.elementAt(j));
                            scripts[j] = script_appo.get_SCRIPT();
                        }
                        else
                        {
                            scripts[j] = 0;
                        }*/
                    }

                    // -------------------- ID PLUGIN ------------------------------

                    int idPlugin=-1;
                    if(!row_TE.PLUGIN.equals(""))
                    {
                        idPlugin= vPlugin.isPresentPlugin(row_TE.PLUGIN,config_NAME_ALIAS,"");
                        if(idPlugin == -1)
                        {
                            idPlugin= vPlugin.addPlugin(row_TE.PLUGIN,config_NAME_ALIAS,"");
                            //System.out.println("addPlugin row_TE.PLUGIN ="+row_TE.PLUGIN);
                        }
                    }

                    int idPluginGUI = -1;
                    if(!row_TE.PLUGIN_GUI.equals(""))
                    {
                        idPluginGUI= vPlugin.isPresentPlugin(row_TE.PLUGIN_GUI,config_NAME_ALIAS,"");
                        if(idPluginGUI == -1)
                        {
                            idPluginGUI= vPlugin.addPlugin(row_TE.PLUGIN_GUI,config_NAME_ALIAS,"");
                            //System.out.println("addPluginGUI row_TE.PLUGIN_GUI ="+row_TE.PLUGIN_GUI);
                            //System.out.println(" idPluginGUI -->"+idPluginGUI);
                        }
                    }



                    int posDR = row_TE.POSIZIONE_CAMPO_DR;

                    if((row_TE.POSIZIONE_CAMPO_DR == -1)&&(row_TE.LINK_TE > 0))
                    {
                        posDR = V_TAB_CONFIG_1.getPosCampDR_WithID(row_TE.LINK_TE);
                        //System.out.println(row_TE.TAG+"= "+row_TE.POSIZIONE_ELEMENTO+" E' un TE e link INPUTDATA con ID="+row_TE.LINK_TE+" POSIZIONE_CAMPO_DR ="+posDR);
                    }

                    if(posDR != -1)
                        posDR--;

                    ntmComplete.elementSequence[i] = new DATA_DATAELEMENT(   row_TE.POSIZIONE_ELEMENTO,                              //int idElement - // identificativo univoco
                                                                                set_String_toChar(row_TE.TAG,_SHORT_DESC_LEN),          //char[] shortDescription - // descrizione breve (tag)
                                                                                set_String_toChar(row_TE.DESCRIPTION,_LONG_DESC_LEN),   //char[] longDescription - // descrizione
                                                                                vHELP_GUI.getValue(row_TE.TIPO_OGGETTO_GUI),                                //int guiObject - // tipo di oggetto GUI
                                                                                set_String_toChar(row_TE.ICONA,_PIXMAP_FILENAME_LEN),   //char[] pixmapFileName - // pixmap associata
                                                                                set_String_toChar(row_TE.RANGE_VAL,_VALUE_RANGE_LEN),       //char[] valueRange - // range di valori
                                                                                valueList,                                              // valori in lista
                                                                                valueListLabel,                                         // etichette associate ai valori (valueList)
                                                                                row_TE.VALORE_DEFAULT,                                  //double defaultValue - // selezione di default
                                                                                toBoolean(row_TE.RAPPRESENTAZ_ESADECIMALE),             //boolean acceptHex - // accetta valori esadecimali;
                                                                                row_TE.PRIORITA,                                        //int priority - // priorita' restrizione
                                                                                aggregateRestrictions,
                                                                                exceptions,
                                                                                posDR,                                                 //int idDRLink - // link base dati
                                                                                0,                                                      //int elementType,                    // tipologia dati (SOLO se idDRlink == 0)
                                                                                vHELP_COMPARE.getValue(row_TE.OPERATORI_RESTRIZIONE),      //int compareSelection - // abil. selezione operatore
                                                                                set_String_toChar("",_TE_SUFFIX_LEN),
                                                                                row_TE.LUNG_ELEMENTO_CHIAVE,                            // int LengthInRelation - // Lunghezza dell'elemento in chiave
                                                                                valueShifter,
                                                                                scripts,
                                                                                idPlugin,                                               // Id of the handler plugin
                                                                                idPluginGUI,                                            // Id of the GUI handler plugin
                                                                                toBoolean(row_TE.ALWAYS_SHOW_REPORT),
                                                                                toBoolean(row_TE.FFENABLED));
                }

                if(DEBUG)
                {
                    System.out.println("   ntmComplete.elementSequence[i].idElement         "+ntmComplete.elementSequence[i].idElement);
                    System.out.println("   ntmComplete.elementSequence[i].shortDescription  "+new String(ntmComplete.elementSequence[i].shortDescription).trim());
                    System.out.println("   ntmComplete.elementSequence[i].longDescription   "+new String(ntmComplete.elementSequence[i].longDescription).trim());
                    System.out.println("   ntmComplete.elementSequence[i].guiObject         "+ntmComplete.elementSequence[i].guiObject);
                    System.out.println();
                }

            }
        }
        else
        {
            if(DEBUG)
            {
                System.out.println("**** V_TAB_CONFIG_1 SIZE VUOTO ****");
            }


            double[] valueList      = new double[_MAX_OPTIONS];
            char[][] valueListLabel = new char[_MAX_OPTIONS][_OPTION_LABEL_LEN];
            for(int x=0; x<_MAX_OPTIONS;x++)
            {
                valueList[x] = 0.0;
                valueListLabel[x] = set_String_toChar("",_OPTION_LABEL_LEN);
            }

            int[] aggregateRestrictions = new int[_MAX_AGGREGATE_RESTR];
            for(int y=0; y<_MAX_AGGREGATE_RESTR; y++)
                    aggregateRestrictions[y] = 0;

            int[] exceptions = new int[_MAX_EXCEPTIONS];
            for(int z=0; z<_MAX_EXCEPTIONS; z++)
                    exceptions[z] = 0;

            char[][] valueShifter = new char[_VALSHIFTER_MAX][_VALSHIFTER_LEN*3];
            for(int q=0; q<_MAX_EXCEPTIONS; q++)
                valueShifter[q] = set_String_toChar_noEND("000000000000000000000000",_VALSHIFTER_LEN*3);


            int[] scripts = new int[_MAX_TE_SCRIPTS];
            for(int j=0; j<_MAX_TE_SCRIPTS; j++)
                scripts[j] = 0;

            ntmComplete.elementSequence = new DATA_DATAELEMENT[1];
            ntmComplete.elementSequence[0] = new DATA_DATAELEMENT(   -1,                                             //int idElement - // identificativo univoco
                                                                        set_String_toChar("",_SHORT_DESC_LEN),          //char[] shortDescription - // descrizione breve (tag)
                                                                        set_String_toChar("",_LONG_DESC_LEN),           //char[] longDescription - // descrizione
                                                                        -1,                                             //int guiObject - // tipo di oggetto GUI
                                                                        set_String_toChar("",_PIXMAP_FILENAME_LEN),     //char[] pixmapFileName - // pixmap associata
                                                                        set_String_toChar("",_VALUE_RANGE_LEN),         //char[] valueRange - // range di valori
                                                                        valueList,                                      // valori in lista
                                                                        valueListLabel,                                 // etichette associate ai valori (valueList)
                                                                        -1,                                             //double defaultValue - // selezione di default
                                                                        false,                                          //boolean acceptHex - // accetta valori esadecimali;
                                                                        -1,                                             //int priority - // priorita' restrizione
                                                                        aggregateRestrictions,
                                                                        exceptions,
                                                                        -1,                                             //int idDRLink - // link base dati
                                                                        0,                                              //int elementType,                    // tipologia dati (SOLO se idDRlink == 0)
                                                                        -1,                                             //int compareSelection - // abil. selezione operatore
                                                                        set_String_toChar("",_TE_SUFFIX_LEN),
                                                                        -1,                                             // int LengthInRelation - // Lunghezza dell'elemento in chiave
                                                                        valueShifter,
                                                                        scripts,
                                                                        -1,                                             // Id of the handler plugin
                                                                        -1,                                             // Id of the GUI handler plugin
                                                                        false,
                                                                        false);
        }


        return true;
    }

    public boolean get_COUNTERKIT() //Luca
    {
        VectorHelp vHELP_COUNTER_INDEX = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_COUNTER_INDEX);
        VectorHelp vHELP_COUNTER_TYPE = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_COUNTER_TYPE);
        VectorHelp vHELP_PERCENT_OF = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_PERCENT_OF);

        if(DEBUG)
            System.out.println(" **** get_COUNTERKIT() **** ");


        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_COUNTERKIT] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        String sel_AllReadCounters = "select IDCOUNTER from tab_counters_kit where TIPO_DI_CONFIGURAZIONE = '"+config_NAME_ALIAS+"'";

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_AllReadCounters,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next() )
                {
                    ADAMS_TAB_KIT_CONTATORI row_Contatori = new ADAMS_TAB_KIT_CONTATORI();
                    row_Contatori.TIPO_DI_CONFIGURAZIONE = config_NAME_ALIAS;
                    row_Contatori.IDCOUNTER = rs.getInt("IDCOUNTER");
                    V_Counters.addElement(row_Contatori);
                }
            }catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }

        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("(get_COUNTERKIT) SQL Operation " + exc.toString());
        }

        int nCOUNTERS = V_Counters.size();
        //nCOUNTERS=0;
        if(nCOUNTERS > 0)
        {
            ntmComplete.counterSequence = new COUNTERS[nCOUNTERS];
            for(int i=0;i<nCOUNTERS;i++)
            {
                ADAMS_TAB_KIT_CONTATORI appo_counters = (ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(i);
                appo_counters.readCounterKit(); // leggo campi completi e Nested

                int nCOUNTER_KIT = appo_counters.V_COUNTERKIT.size();
                COUNTER_KIT[] counterKit_seq = new COUNTER_KIT[nCOUNTER_KIT];
                for(int x=0; x<nCOUNTER_KIT;x++)
                {
                    ADAMS_TAB_COUNTERKIT appo_COUNTERKIT = (ADAMS_TAB_COUNTERKIT)(appo_counters.V_COUNTERKIT.elementAt(x));

                    short triggerField=0;
                    if((short)appo_COUNTERKIT.TRIGGERFIELD==-1)
                    {
                        triggerField=0;
                    }
                    else
                    {
                        triggerField=(short)appo_COUNTERKIT.TRIGGERFIELD;
                    }

                    int  triggerValue=0;
                    if(appo_COUNTERKIT.TRIGGERVALUE==-1)
                    {
                        triggerValue=0;
                    }else
                    {
                        triggerValue=appo_COUNTERKIT.TRIGGERVALUE;
                    }
                    appo_COUNTERKIT.index=x;

                    int triggerCounter = vHELP_PERCENT_OF.getValue(appo_COUNTERKIT.TRIGGERCOUNTER);
                    if( triggerCounter == -1 )
                        triggerCounter = 0;

                    counterKit_seq[x] = new COUNTER_KIT(triggerField,
                                                        triggerValue,
                                                        (short)vHELP_COUNTER_INDEX.getValue(appo_COUNTERKIT.COUNTERINDEX),
                                                        (short)vHELP_COUNTER_TYPE.getValue(appo_COUNTERKIT.COUNTERTYPE),
                                                        (short)vHELP_PERCENT_OF.getValue(appo_COUNTERKIT.PERCENTOF),
                                                        (short)triggerCounter,
                                                        set_String_toChar(appo_COUNTERKIT.TAG,_CNT_TAG_LEN),
                                                        set_String_toChar(appo_COUNTERKIT.DESCRIPTION,_CNT_DESC_LEN));

                    if(DEBUG)
                    {
                        System.out.println("   ****** COUNTER_KIT "+x+" of "+appo_counters.TAG+" ******");

                        System.out.println("   COUNTER_KIT.TRIGGERFIELD    "+counterKit_seq[x].triggerField);
                        System.out.println("   COUNTER_KIT.TRIGGERVALUE    "+counterKit_seq[x].triggerValue);
                        System.out.println("   COUNTER_KIT.COUNTERINDEX    "+counterKit_seq[x].counterIndex);
                        System.out.println("   COUNTER_KIT.COUNTERTYPE     "+counterKit_seq[x].counterType);
                        System.out.println("   COUNTER_KIT.PERCENTOF       "+counterKit_seq[x].percentOf);
                        System.out.println("   COUNTER_KIT.TRIGGERCOUNTER  "+counterKit_seq[x].triggerCounter);
                        System.out.println("   COUNTER_KIT.TAG             "+new String(counterKit_seq[x].tag).trim());
                        System.out.println("   COUNTER_KIT.DESCRIPTION     "+new String(counterKit_seq[x].description).trim());
                        System.out.println("   COUNTER_KIT.index           "+appo_COUNTERKIT.index);
                    }
                }

                ntmComplete.counterSequence[i] = new COUNTERS(  appo_counters.IDCOUNTER,
                                                                set_String_toChar(appo_counters.TAG,_SHORT_DESC_LEN),
                                                                toBoolean(appo_counters.USEPLUGIN),
                                                                set_String_toChar(appo_counters.PLUGINNAME,_CNT_PLUGINNAME_LEN),
                                                                toBoolean(appo_counters.USEPATH),
                                                                set_String_toChar(appo_counters.PATHNAME,_CNT_PATH_LEN),
                                                                counterKit_seq);
                if(DEBUG)
                {
                    System.out.println("**** COUNTERS ****");

                    System.out.println("COUNTERS.IDCOUNTER  "+ntmComplete.counterSequence[i].idCounter);
                    System.out.println("COUNTERS.TAG        "+new String(ntmComplete.counterSequence[i].tag).trim());
                    System.out.println("COUNTERS.USEPLUGIN  "+ntmComplete.counterSequence[i].usePlugin);
                    System.out.println("COUNTERS.PLUGINNAME "+new String(ntmComplete.counterSequence[i].pluginName).trim());
                    System.out.println("COUNTERS.USEPATH    "+ntmComplete.counterSequence[i].usePath);
                    System.out.println("COUNTERS.PATHNAME   "+new String(ntmComplete.counterSequence[i].pathName).trim());
                    System.out.println("counterKit_seq size -----> "+ntmComplete.counterSequence[i].counterKit.length);

                    System.out.println();
                }

            }
        }
        else
        {
            COUNTER_KIT[] counterKit_seq = new COUNTER_KIT[1];
            counterKit_seq[0] = new COUNTER_KIT((short)0,0,(short)0,(short)0,(short)0,(short)0,
                                                set_String_toChar("",_CNT_TAG_LEN),
                                                set_String_toChar("",_CNT_DESC_LEN));

            ntmComplete.counterSequence = new COUNTERS[1];
            ntmComplete.counterSequence[0] = new COUNTERS(0,
                                                        set_String_toChar("",_SHORT_DESC_LEN),
                                                        false,
                                                        set_String_toChar("",_CNT_PLUGINNAME_LEN),
                                                        false,
                                                        set_String_toChar("",_CNT_PATH_LEN),
                                                        counterKit_seq);


            if(DEBUG)
            {
                System.out.println("**** COUNTERS SIZE VUOTA ****");
            }
        }
        return true;
    }

	/*public test(String nomeClasse)
	{
		try
		{
		 	String classeDaInvocare = nomeClasse;
		      	Class classeInvocata = Class.forName(classeDaInvocare);


		      	if(classeDaInvocare.equals("GLOBALOPT"));
		      	{
		 		ntmComplete.globalOptions=(GLOBALOPT)classeInvocata.newInstance();
			}

		}catch(Exception e)
		{}
	}*/

    public boolean get_GLOBALOPT() //Luca
    {
        if(DEBUG)
            System.out.println(" **** get_GLOBALOPT() **** ");

        if(config_NAME_ALIAS==null)
        {
            System.out.println("ADAMS_storeConfig [get_GLOBALOPT] ----> MANCA IL NOME DELLA CONFIGURAZIONE.");
            return false;
        }

        ADAMS_TAB_INFO_CONFIG infoConfig = new ADAMS_TAB_INFO_CONFIG("");
        infoConfig.InfoConfigName(config_NAME_ALIAS);



        ntmComplete.globalOptions = new GLOBALOPT();

        ntmComplete.globalOptions.drUsePlugin          = toBoolean(infoConfig.DR_USE_PLUGIN);
        ntmComplete.globalOptions.drPluginName         = set_String_toChar(infoConfig.DR_PLUGIN_NAME,_DR_PLUGINNAME_LEN);
        ntmComplete.globalOptions.drUsePath            = toBoolean(infoConfig.DR_FLAG_USE_PATH);
        ntmComplete.globalOptions.drPathName           = set_String_toChar(infoConfig.DR_SPECIFY_PL_PATH,_DR_PATH_LEN);
        ntmComplete.globalOptions.glbDefaultPluginPath  = set_String_toChar(infoConfig.GLB_DEFAULT_PATH_PL,_GLB_INFO_LEN);
        ntmComplete.globalOptions.glbAlias              = set_String_toChar(infoConfig.TIPO_DI_CONFIGURAZIONE,_GLB_INFO_LEN);
        ntmComplete.globalOptions.glbAuthor             = set_String_toChar(infoConfig.AUTHOR,_GLB_INFO_LEN);
        ntmComplete.globalOptions.glbLastModified       = set_String_toChar(infoConfig.LAST_MODIFICATION_TIME,_GLB_INFO_LEN);
        ntmComplete.globalOptions.indexFileName         = set_String_toChar(infoConfig.indexFileName,_MAX_CONFIG_FILENAME);


        if(DEBUG)
        {
            System.out.println(" **** GLOBALOPT **** ");

            System.out.println("globalOptions.drUsePlugin          "+ntmComplete.globalOptions.drUsePlugin);
            System.out.println("globalOptions.drPluginName         "+new String(ntmComplete.globalOptions.drPluginName).trim());
            System.out.println("globalOptions.drUsePath            "+ntmComplete.globalOptions.drUsePath);
            System.out.println("globalOptions.drPathName           "+new String(ntmComplete.globalOptions.drPathName).trim());
            System.out.println("globalOptions.glbDefaultPluginPath  "+new String(ntmComplete.globalOptions.glbDefaultPluginPath).trim());
            System.out.println("globalOptions.glbAlias              "+new String(ntmComplete.globalOptions.glbAlias).trim());
            System.out.println("globalOptions.glbAuthor             "+new String(ntmComplete.globalOptions.glbAuthor).trim());
            System.out.println("globalOptions.glbLastModified       "+new String(ntmComplete.globalOptions.glbLastModified).trim());
            System.out.println("globalOptions.indexFileName         "+new String(ntmComplete.globalOptions.indexFileName).trim());
            System.out.println();
        }

        return true;
    }


    public boolean store_ADAMSConfIG(String NameFileConf)
    {
        //System.out.println("- store_ADAMSConfIG --> "+NameFileConf);

        boolean bRet=false;

        CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);

        //System.out.println("TRY OPEN Connection on mdm_server_server");
        connOK_mdm_server_server = CORBA.openConnection_mdm_server_server();

	    if(connOK_mdm_server_server)
            {
                    System.out.println("Connection mdm_server_server... ok.");

                    //System.out.println("PRIMA --- CORBA.storeConfiguration(.......)");
                    bRet = CORBA.storeConfiguration(ntmComplete,NameFileConf);
                    //System.out.println("DOPO  --- CORBA.storeConfiguration(.......) --> "+bRet);

                    CORBA.shutDown_Conf();
            }
            else
            {
                System.out.println(" - Error Connection CORBA.");
                bRet=false;
            }

        if(bRet == false)
            return bRet;

        String sel_fileName = "select ID_FUNCTION from tab_function_lib where ID_CLASS="+ADAMS_GlobalParam.ID_MOD_MDM+" AND FUNCTION_NAME='"+ADAMS_GlobalParam.strLastFileName+"' ";
        int idFunction = ADAMS_GlobalParam.connect_Oracle.Query_int(sel_fileName);

        //System.out.println("STORE CONFIG ON DB ("+ADAMS_GlobalParam.strLastFileName+","+idFunction+","+NameFileConf+")**********************");

        S_FUNCTION newConfig = new S_FUNCTION();
        newConfig.idClass         = ADAMS_GlobalParam.ID_MOD_MDM;
        newConfig.idFunction      = idFunction;
        newConfig.nameFunction    = set_String_toChar(NameFileConf,asp_usermanagement_server.eMAX_NAME_FUNCTION);
        newConfig.descFunction    = set_String_toChar(NameFileConf,asp_usermanagement_server.eMAX_DESC_FUNCTION);
        newConfig.version         = set_String_toChar("1.0",asp_usermanagement_server.eMAX_VERSION);
        newConfig.releasedData    = set_String_toChar("",asp_usermanagement_server.eMAX_RELEASED_DATA);
        newConfig.author          = set_String_toChar("ADAMSConf",asp_usermanagement_server.eMAX_AUTHOR);
        newConfig.vendor          = set_String_toChar("ADAMSConf",asp_usermanagement_server.eMAX_VENDOR);

        bRet=ADAMS_GlobalParam.STSConn.setConfiguration(newConfig);

        return bRet;

    }

    public boolean store_ADAMSConfIG_INDEX(String fileNameConfIndex,String dataStart,String dataEnd)
    {
        boolean bRet=false;

        CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);


        ////////////////// MonitorMasterServer
        boolean connOK_MonServer = CORBA.openConnection_psMonitorMasterServer();
        if(connOK_MonServer)
        {
                System.out.println("Connection MonitorMasterServ... ok.");

                if(ntmComplete.drSequence != null)
                {
                    if( (dataStart.length() > 0) || (dataEnd.length() > 0) )
                    {
                        for(int i=0; i<ntmComplete.drSequence.length; i++)
                        {
                            if((ntmComplete.drSequence[i].isIndex == true)&&(ntmComplete.drSequence[i].position != -1))
                            {
                                ntmComplete.drSequence[i].startTime    = set_String_toChar(dataStart,_DR_DATE_LEN);
                                ntmComplete.drSequence[i].endTime      = set_String_toChar(dataEnd,_DR_DATE_LEN);
                                break;
                            }
                        }
                    }
                }

	     	int flag = CORBA.putConfigurationDRonFEP("All",fileNameConfIndex,0,ntmComplete.drSequence);
        	//CORBA.shutDown_psMonitorMasterServer();
        }
        else
        {
            System.out.println(" - Error Connection CORBA psMonitorMasterServer.");
            bRet=false;
        }

        ////////////////// MonitorMasterServer

        return bRet;
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

    private Vector creaRighe(String str) //Raffo
    {
        Vector appo=new Vector();
        int start=0;
    	int stop=0;

        while(stop!=-1)
        {
            stop=str.indexOf('\n');
            //System.out.println("START=["+start+"]");
            //System.out.println("STOP=["+stop+"]");
            String riga1="";
            if(stop==-1)
            {
                riga1=str.substring(start,str.length());
            }else
            {
                riga1=str.substring(start,stop);
            }

            str=str.substring(stop+1,str.length());
            if(DEBUG)
            {
                System.out.println("["+riga1+"]");
            }
            start=0;
            appo.addElement(riga1);
        }
        return appo;
    }

    private static char[] set_String_toChar2(String str, int length) //Raffo
    {

    	char[] appo = str.toCharArray();
    	char[] appo1 = new char[length];

    	if (appo.length > length)
    	    return appo1;


    	for (int i=0; i<appo.length; i++)
    	    appo1[i] = appo[i];

    	for (int i=appo.length; i<length; i++)
    	    appo1[i] ='\0';

        String temp=new String(appo1);

        for(int i=0;i<length;i++)
        {
            //System.out.println("ch_"+i+" =["+appo1[i]+"] , ["+(int)appo1[i]+"]");
        }

        int k=0;
        while(true)
        {
            k=1;
            if (appo1[length-k]=='\n')
            {
                appo1[length-k]='.';
                k++;
            }else
            {
                break;
            }
        }
        appo1[length-1] ='\0';

    	return(appo1);
    }


    private static char[] set_String_toChar_noEND(String str, int length) //Raffo
    {

    	char[] appo = str.toCharArray();
    	char[] appo1 = new char[length];

    	if (appo.length > length)
    	    return appo1;


    	for (int i=0; i<appo.length; i++)
    	    appo1[i] = appo[i];

    	for (int i=appo.length; i<length; i++)
    	    appo1[i] ='\0';

    	return(appo1);
    }

    private boolean toBoolean(int value) //Raffo
    {
        if(value>0)
            return true;
        else
            return false;
    }

    private boolean toBoolean(char ch) //Luca
    {
        if(ch == 'Y')
            return true;
        else
            return false;
    }
}

