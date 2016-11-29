import java.util.Vector;
public class ADAMS_TAB_ALARM_RELATION {

    /** Creates new ADAMS_TAB_ALARM_RELATION */
    public ADAMS_TAB_ALARM_RELATION() {      
               
        ID_ALARM_RELATION           = -1;              
        DESCRIPTION                 = new String();
        ID_CONDITION_SCRIPT         = -1;
        STR_CONDITION_PLUGIN        = new String();
        TIPO_DI_CONFIGURAZIONE      = new String();
        COUNTERS_KIT_ID             = -1;
        TIME_FRACTION_ELEMENT_ID    = -1;
        FAMILY_ID                   = -1;
        
        V_ALARM_REL_ELEMENTS        = new Vector();
        V_ALARM_HANDLERS            = new Vector();
        V_ENABLE_DETAIL_ELEMENTS    = new Vector();
    }  
    
    public int insert_ALARM_RELATION()
    {
        ID_ALARM_RELATION = ADAMS_GlobalParam.connect_Oracle.Query_int("select tab_alarm_relation_SEQ.nextVal from dual");
       //System.out.println("Insert tab_alarm_relation_SEQ.nextVal "+ID_ALARM_RELATION);
        
        if(ID_ALARM_RELATION < 0)
        {
            ADAMS_GlobalParam.optionPanel((javax.swing.JFrame)null,"Operation failed [Error: tab_alarm_relation_SEQ.nextVal].","ERROR",1);
            return -1;
        }

        String itemName =("ID_ALARM_RELATION,"+   
                            "DESCRIPTION,"+
                            "ID_CONDITION_SCRIPT,"+
                            "STR_CONDITION_PLUGIN,"+
                            "TIPO_DI_CONFIGURAZIONE,"+
                            "COUNTERS_KIT_ID,"+
                            "TIME_FRACTION_ELEMENT_ID,"+
                            "FAMILY_ID");

        String itemValue = (    ID_ALARM_RELATION+","+   
                                "'"+DESCRIPTION+"',"+
                                ID_CONDITION_SCRIPT+","+
                                "'"+STR_CONDITION_PLUGIN+"',"+
                                "'"+TIPO_DI_CONFIGURAZIONE+"',"+
                                COUNTERS_KIT_ID+","+
                                TIME_FRACTION_ELEMENT_ID+","+
                                FAMILY_ID);
        
        String str_Insert = ("insert into "+tab_alarm_relation+" ("+itemName+") values ( "+itemValue+" )");
       //System.out.println("str_Insert --> "+str_Insert);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);
        int Answer_Ins_AR = -1;
        int Answer_Ins_AL = -1;
        
        int Answer_T_POLITICHE = -1;
        
        if(Answer_Ins > 0)
        {
            //++++++++++++++++++++ tab_alarm_relation_elements +++++++++++++++++++++++
            int size_REL_Elements = V_ALARM_REL_ELEMENTS.size();
            if(size_REL_Elements > 1)
            {
                adams_tab_alarm_relation_elements row_AlarmRelationElements = new adams_tab_alarm_relation_elements(this.TIPO_DI_CONFIGURAZIONE);

                row_AlarmRelationElements.ID_ALARM_RELATION = this.ID_ALARM_RELATION;                 

                for(int i=0; i<size_REL_Elements; i++)
                {
                    row_AlarmRelationElements.ID_ELEMENT = ((Integer)V_ALARM_REL_ELEMENTS.elementAt(i)).intValue();
                    row_AlarmRelationElements.POSITION_ELEMENT = i;
                    row_AlarmRelationElements.ENABLED_DETAIL = ((Integer)V_ENABLE_DETAIL_ELEMENTS.elementAt(i)).intValue();

                    String itemName_AR =("ID_ALARM_RELATION,"+   
                                            "TIPO_DI_CONFIGURAZIONE,"+
                                            "ID_ELEMENT,"+
                                            "POSITION_ELEMENT,"+
                                            "ENABLED_DETAIL");

                    String itemValue_AR = (row_AlarmRelationElements.ID_ALARM_RELATION+","+   
                                           "'"+row_AlarmRelationElements.TIPO_DI_CONFIGURAZIONE+"',"+
                                            row_AlarmRelationElements.ID_ELEMENT+","+
                                            row_AlarmRelationElements.POSITION_ELEMENT+","+
                                            row_AlarmRelationElements.ENABLED_DETAIL);

                    String str_Insert_AR = ("insert into "+tab_alarm_relation_elements+" ("+itemName_AR+") values ( "+itemValue_AR+" )");
                   //System.out.println("str_Insert_AR --> "+str_Insert_AR);

                    Answer_Ins_AR = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_AR);

                    if(Answer_Ins_AR < 0)
                    {
                        System.out.println("ERROR insert into "+tab_alarm_relation_elements);
                        break;
                    }
                }
            }
            //+++++++++++++++++++++++++++++++++++++++++++
            
            Answer_Ins_AL = 0;
            // ++++++++++++++++++++++++ tab_alarm_relation_handlers +++++++++++++++++++++++++++++++++++
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }
        
        // #### ************************** T_POLICY e T_TRAFFIC_RELATION *************
        ADAMS_OracleConnection CONNECT_ORA_rda = new ADAMS_OracleConnection();
        boolean CONN_ORACLE = CONNECT_ORA_rda.DBConnect("RDA","ORACLE","ops$rdaexec");
   
        if(CONN_ORACLE)
        {
            try
            {        
                CONNECT_ORA_rda.DBConnection.setAutoCommit(false);               
            }catch(java.sql.SQLException exc){}
            
            ADAMS_T_POLITICHE t_politiche = new ADAMS_T_POLITICHE(CONNECT_ORA_rda);

            t_politiche.TRAFF_ID    = ID_ALARM_RELATION;
            t_politiche.POL_ID      = 'M';      
            t_politiche.INTERVALLO  = 1;       
            t_politiche.PERSISTENZA = 2;       
            t_politiche.COEFF       = 1.0F;     
            t_politiche.ID_KPI      = -1;       
            t_politiche.DESCR       = DESCRIPTION;        
            t_politiche.DESC_COMP   ="Not Defined";
            t_politiche.TIPO_DI_CONFIGURAZIONE = TIPO_DI_CONFIGURAZIONE;

            Answer_T_POLITICHE = t_politiche.insert_T_POLITICHE();
        }
        // #### ************************** T_POLICY e T_TRAFFIC_RELATION *************     
        
        try
        {         
            if( (Answer_Ins >= 0) && (Answer_Ins_AR >= 0) && (Answer_Ins_AL >= 0) && (Answer_T_POLITICHE >=0))
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                CONNECT_ORA_rda.DBConnection.commit();
               //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                CONNECT_ORA_rda.DBConnection.rollback();
                System.out.println("rollback()");
                Answer_Ins = -1;
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            Answer_Ins = -1;
            System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 1");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1)
            {
                System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 1A");
            }
        }
        CONNECT_ORA_rda.Close();
        return Answer_Ins;
    }
    
    public int update_ALARM_RELATION()
    {
          
        String str_update = "update "+tab_alarm_relation+" set DESCRIPTION='"+DESCRIPTION+"',COUNTERS_KIT_ID="+COUNTERS_KIT_ID+","+
                            "ID_CONDITION_SCRIPT="+ID_CONDITION_SCRIPT+",STR_CONDITION_PLUGIN='"+STR_CONDITION_PLUGIN+"',"+
                            "TIME_FRACTION_ELEMENT_ID="+TIME_FRACTION_ELEMENT_ID+","+
                            "FAMILY_ID="+FAMILY_ID+" "+                            
                            "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                            "ID_ALARM_RELATION="+ID_ALARM_RELATION;
        
            
       //System.out.println("esito update = "+str_update);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
        
        int Answer_del      = -1;
        int Answer_Ins_AR   = -1;
        
        if(Answer_update >= 0 )
        {
            String str_delete = "delete from "+tab_alarm_relation_elements+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and ID_ALARM_RELATION="+ID_ALARM_RELATION;
        
           //System.out.println("esito delete -->> = "+str_delete);
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
            
            if(Answer_del >= 0)
            {            
                int size_REL_Elements = V_ALARM_REL_ELEMENTS.size();
                if(size_REL_Elements > 1)
                {
                    adams_tab_alarm_relation_elements row_AlarmRelationElements = new adams_tab_alarm_relation_elements(this.TIPO_DI_CONFIGURAZIONE);

                    row_AlarmRelationElements.ID_ALARM_RELATION = this.ID_ALARM_RELATION;                  

                    for(int i=0; i<size_REL_Elements; i++)
                    {
                        row_AlarmRelationElements.ID_ELEMENT = ((Integer)V_ALARM_REL_ELEMENTS.elementAt(i)).intValue();
                        row_AlarmRelationElements.POSITION_ELEMENT = i;
                        row_AlarmRelationElements.ENABLED_DETAIL = ((Integer)V_ENABLE_DETAIL_ELEMENTS.elementAt(i)).intValue();

                        String itemName_AR =("ID_ALARM_RELATION,"+   
                                                "TIPO_DI_CONFIGURAZIONE,"+
                                                "ID_ELEMENT,"+
                                                "POSITION_ELEMENT,"+
                                                "ENABLED_DETAIL");

                        String itemValue_AR = (row_AlarmRelationElements.ID_ALARM_RELATION+","+   
                                               "'"+row_AlarmRelationElements.TIPO_DI_CONFIGURAZIONE+"',"+
                                                row_AlarmRelationElements.ID_ELEMENT+","+
                                                row_AlarmRelationElements.POSITION_ELEMENT+","+
                                                row_AlarmRelationElements.ENABLED_DETAIL);

                        String str_Insert_AR = ("insert into "+tab_alarm_relation_elements+" ("+itemName_AR+") values ( "+itemValue_AR+" )");
                       //System.out.println("str_Insert_AR  --> "+str_Insert_AR);

                        Answer_Ins_AR = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_AR);

                        if(Answer_Ins_AR < 0)
                        {
                            System.out.println("ERROR insert into "+tab_alarm_relation_elements);
                            break;
                        }
                    }
                }

            }
                             
        }
        
        // #### ************************** T_POLICY e T_TRAFFIC_RELATION *************
        
        ADAMS_OracleConnection CONNECT_ORA_rda = new ADAMS_OracleConnection();
        boolean CONN_ORACLE = CONNECT_ORA_rda.DBConnect("RDA","ORACLE","ops$rdaexec");
        int Answer_T_TRAF_REL = -1;
        if(CONN_ORACLE)
        {
            try
            {        
                CONNECT_ORA_rda.DBConnection.setAutoCommit(false);               
            }catch(java.sql.SQLException exc){}
            
            ADAMS_T_POLITICHE t_politiche = new ADAMS_T_POLITICHE(CONNECT_ORA_rda);

            t_politiche.TRAFF_ID    = ID_ALARM_RELATION;                 
            t_politiche.DESCR       = DESCRIPTION;        
            t_politiche.TIPO_DI_CONFIGURAZIONE = TIPO_DI_CONFIGURAZIONE;

            Answer_T_TRAF_REL = t_politiche.update_T_TRAF_REL();
        }
        // #### ************************** T_POLICY e T_TRAFFIC_RELATION *************    
        
                        
        try
        {         
            if( (Answer_update >= 0) && (Answer_del >= 0) /*&& (Answer_Ins_AR >= 0) */  && (Answer_T_TRAF_REL >=0))
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                CONNECT_ORA_rda.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                CONNECT_ORA_rda.DBConnection.rollback();
                System.out.println("rollback()");
                Answer_update = -1;
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            CONNECT_ORA_rda.Close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 2A");}
        }
        
        return Answer_update;

    }
    
    
    public int delete_ALARM_RELATION()
    {     
        String str_delete = "delete from "+tab_alarm_relation+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_ALARM_RELATION="+ID_ALARM_RELATION;

        String str_delete_2 = "delete from "+tab_alarm_relation_elements+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_ALARM_RELATION="+ID_ALARM_RELATION;

        String str_delete_3 = "delete from "+tab_alarm_relation_handlers+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_ALARM_RELATION="+ID_ALARM_RELATION;


        String str_delete_Script = "";
        if(ID_CONDITION_SCRIPT != -1)
        {
            str_delete_Script ="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and IDSCRIPT="+ID_CONDITION_SCRIPT;
           //System.out.println("str_delete_Script ="+str_delete_Script);
        }

        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}


        // delete tabella tab_alarm_relation
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);   

        // delete tabella tab_alarm_relation_elements
        if(Answer_del >= 0)
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_2);

        //delete tabella tab_alarm_relation_handlers
        if(Answer_del >= 0)
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_3);

        if(Answer_del >= 0)
        {
            if(ID_CONDITION_SCRIPT != -1)
                Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_Script); 
        }

        
// *********** T_POLITICHE -- T_TRAF_REL

        ADAMS_OracleConnection CONNECT_ORA_rda = new ADAMS_OracleConnection();
        boolean CONN_ORACLE = CONNECT_ORA_rda.DBConnect("RDA","ORACLE","ops$rdaexec");

        if(CONN_ORACLE)
        {
            try
            {        
                CONNECT_ORA_rda.DBConnection.setAutoCommit(false);
            }catch(java.sql.SQLException exc){}

            String str_delete_T_POLITICHE = "delete from T_POLICY where TRAFF_ID="+ID_ALARM_RELATION;
            //System.out.println("str_delete_T_POLITICHE = "+str_delete_T_POLITICHE);
            
            Answer_del = CONNECT_ORA_rda.Operation(str_delete_T_POLITICHE);            
            //System.out.println("ESITO str_delete_T_POLITICHE = "+Answer_del);

            if(Answer_del >= 0)
            {                
                String str_delete_T_TRAF_REL = "delete from T_TRAFFIC_RELATION where TRAFF_ID="+ID_ALARM_RELATION+"and TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'";
                //System.out.println("str_delete_T_TRAF_REL = "+str_delete_T_TRAF_REL);

                Answer_del = CONNECT_ORA_rda.Operation(str_delete_T_TRAF_REL);
                //System.out.println("ESITO str_delete_T_POLITICHE = "+Answer_del);
            }
            
            if(Answer_del >= 0)                
            {
                String delete_THR_Override = "delete from T_THR_OVERRIDE where ID_RELATION="+ID_ALARM_RELATION;
                //System.out.println("delete_THR_Override = "+delete_THR_Override);
                
                Answer_del = CONNECT_ORA_rda.Operation(delete_THR_Override);
                //System.out.println("ESITO delete_THR_Override = "+Answer_del);
            }
            
            if(Answer_del >= 0)                
            {
                String delete_THR_history = "delete from T_THR_HISTORY_ALARM where ID_RELATION="+ID_ALARM_RELATION;
                //System.out.println("delete_THR_history = "+delete_THR_history);
                
                Answer_del = CONNECT_ORA_rda.Operation(delete_THR_history);
                //System.out.println("ESITO delete_THR_history = "+Answer_del);
            }
            
        }
        else
            Answer_del = -1;

// *********** T_POLITICHE -- T_TRAF_REL
        
        try
        {         
            if(Answer_del >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                CONNECT_ORA_rda.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                CONNECT_ORA_rda.DBConnection.rollback();
                System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            CONNECT_ORA_rda.Close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 2A");}
        }
       
                    
        return Answer_del;
    }    
    
            
  //Nome tabella
    private final String tab_alarm_relation = "tab_alarm_relation";
    private final String tab_alarm_relation_elements = "tab_alarm_relation_elements";
    private final String tab_alarm_relation_handlers = "tab_alarm_relation_handlers";
    
    
    public int ID_ALARM_RELATION;               
    public String DESCRIPTION;
    public int ID_CONDITION_SCRIPT;
    public String STR_CONDITION_PLUGIN;
    public String TIPO_DI_CONFIGURAZIONE;
    public int COUNTERS_KIT_ID;
    public int TIME_FRACTION_ELEMENT_ID;
    public int FAMILY_ID = -1; // NON utilizzato
    
    public Vector V_ALARM_REL_ELEMENTS;
    public Vector V_ALARM_HANDLERS;
    public Vector V_ENABLE_DETAIL_ELEMENTS;  
    
    class adams_tab_alarm_relation_elements
    {        
        adams_tab_alarm_relation_elements(String TipoDiConfigurazione)
        {
             ID_ALARM_RELATION  = -1;                    
             TIPO_DI_CONFIGURAZIONE = new String(TipoDiConfigurazione);                   
             ID_ELEMENT         = -1;                                
             POSITION_ELEMENT   = -1;
             ENABLED_DETAIL      = 1;
        }

        public int ID_ALARM_RELATION;                    
        public String TIPO_DI_CONFIGURAZIONE;                   
        public int ID_ELEMENT;                                
        public int POSITION_ELEMENT;
        public int ENABLED_DETAIL;
    
    }
    
    public class adams_tab_alarm_relation_handlers
    {        
        adams_tab_alarm_relation_handlers(String TipoDiConfigurazione)
        {
             ID_ALARM_RELATION  = -1;                
             ID_ALARM_GENERATOR   = -1;
             TIPO_DI_CONFIGURAZIONE = new String(TipoDiConfigurazione); 
        }

        public int ID_ALARM_RELATION;
        public int ID_ALARM_GENERATOR;
        public String TIPO_DI_CONFIGURAZIONE; 
    }
}


    
/*  desc tab_alarm_relation
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID_ALARM_RELATION                         NOT NULL VARCHAR2(30)
 DESCRIPTION                                        VARCHAR2(256)
 ID_CONDITION_SCRIPT                                NUMBER(6)
 STR_CONDITION_PLUGIN                               VARCHAR2(30)
 TIPO_DI_CONFIGURAZIONE                    NOT NULL VARCHAR2(30)
 COUNTERS_KIT_ID                           NOT NULL NUMBER(6)
 TIME_FRACTION_ELEMENT_ID                  NOT NULL NUMBER(6)
 FAMILY_ID                                 NOT NULL NUMBER(6)

desc tab_alarm_relation_elements
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID_ALARM_RELATION                         NOT NULL NUMBER(6)
 TIPO_DI_CONFIGURAZIONE                    NOT NULL VARCHAR2(30)
 ID_ELEMENT                                NOT NULL NUMBER(6)
 POSITION_ELEMENT                                   NUMBER
 ENABLED_DETAIL                                     NUMBER(2)

desc tab_alarm_relation_handlers
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID_ALARM_RELATION                         NOT NULL NUMBER(6)
 ID_ALARM_GENERATOR                        NOT NULL NUMBER(6)
 TIPO_DI_CONFIGURAZIONE                    NOT NULL VARCHAR2(30)*/
