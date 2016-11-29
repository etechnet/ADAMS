import java.util.Vector;
public class ADAMS_TAB_ALARM_THRESHOLD {

    /** Creates new ADAMS_TAB_ALARM_THRESHOLD */
    public ADAMS_TAB_ALARM_THRESHOLD(String tipoDiConfigurazione) {      
                       
        ID_THRESHOLD_GENERATOR = -1;               
        DESCRIPTION = "";
        TYPE_THRESHOLD = -1;
        ENABLE_HOLYDAY_THRESHOLD = 'N';
        STR_PLUGIN = "";
        THRESHOLD_PERSISTENCE = -1;
        HOURS_AGGREGATE       = -1;
        
        TIPO_DI_CONFIGURAZIONE = tipoDiConfigurazione; 
    }  
    
    public int insert_ALARM_THRESHOLD()
    {
        
        ID_THRESHOLD_GENERATOR = ADAMS_GlobalParam.connect_Oracle.Query_int("select TAB_ALARM_THRESHOLD_SEQ.nextVal from dual");
       //System.out.println("Insert TAB_ALARM_THRESHOLD_SEQ.nextVal "+ID_THRESHOLD_GENERATOR);
        
        if(ID_THRESHOLD_GENERATOR < 0)
        {
            ADAMS_GlobalParam.optionPanel((javax.swing.JFrame)null,"Operation failed [Error: TAB_ALARM_THRESHOLD_SEQ.nextVal].","ERROR",1);
            return -1;
        }

        String itemName =("ID_THRESHOLD_GENERATOR,"+   
                            "DESCRIPTION,"+
                            "TYPE_THRESHOLD,"+
                            "ENABLE_HOLYDAY_THRESHOLD,"+
                            "STR_PLUGIN,"+
                            "THRESHOLD_PERSISTENCE,"+
                            "HOURS_AGGREGATE,"+
                            "TIPO_DI_CONFIGURAZIONE");   
        
        String itemValue = (    ID_THRESHOLD_GENERATOR+","+   
                                "'"+DESCRIPTION+"',"+
                                TYPE_THRESHOLD+","+
                                "'"+ENABLE_HOLYDAY_THRESHOLD+"',"+
                                "'"+STR_PLUGIN+"',"+
                                THRESHOLD_PERSISTENCE+","+
                                HOURS_AGGREGATE+","+
                                "'"+TIPO_DI_CONFIGURAZIONE+"'");
        
        String str_Insert = ("insert into "+TAB_ALARM_THRESHOLD+" ("+itemName+") values ( "+itemValue+" )");
       //System.out.println("str_Insert --> "+str_Insert);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);

        try
        {         
            if( Answer_Ins >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
               //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
                Answer_Ins = -1;
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            Answer_Ins = -1;
            System.out.println("Errore ADAMS_TAB_ALARM_THRESHOLD.java 1");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1)
            {
                System.out.println("Errore ADAMS_TAB_ALARM_THRESHOLD.java 1A");
            }
        }
        return Answer_Ins;
    }
    
    public int update_ALARM_THRESHOLD()
    {
       String str_update = "update "+TAB_ALARM_THRESHOLD+" set DESCRIPTION='"+DESCRIPTION+"',TYPE_THRESHOLD="+TYPE_THRESHOLD+","+
                            "ENABLE_HOLYDAY_THRESHOLD='"+ENABLE_HOLYDAY_THRESHOLD+"',"+
                            "STR_PLUGIN='"+STR_PLUGIN+"',"+
                            "THRESHOLD_PERSISTENCE="+THRESHOLD_PERSISTENCE+","+
                            "HOURS_AGGREGATE="+HOURS_AGGREGATE+" "+
                            "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                            "ID_THRESHOLD_GENERATOR="+ID_THRESHOLD_GENERATOR;
        
       //System.out.println("esito update = "+str_update);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);

        try
        {         
            if(Answer_update >= 0) 
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
                Answer_update = -1;
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_ALARM_THRESHOLD.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_THRESHOLD.java 2A");}
        }
        return Answer_update;
    }
    
    
    public int delete_ALARM_THRESHOLD()
    {          
       //System.out.println("delete_ALARM_THRESHOLD(..)");
                  
        String str_delete = "delete from "+TAB_ALARM_THRESHOLD+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_THRESHOLD_GENERATOR="+ID_THRESHOLD_GENERATOR;
       //System.out.println("str_delete "+str_delete);
        
        
        String str_delete_1 = "delete from "+tab_alarm_generator_thresholds+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_THRESHOLD_GENERATOR="+ID_THRESHOLD_GENERATOR;
        

       //System.out.println("str_delete "+str_delete);
       //System.out.println("str_delete_1 "+str_delete_1);

        // delete tabella TAB_ALARM_THRESHOLD
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete); 
        
         // delete tabella tab_alarm_generator_thresholds
        if(Answer_del >= 0)
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_1); 

        try
        {         
            if(Answer_del >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
               //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
               //System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_ALARM_THRESHOLD.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_THRESHOLD.java 2A");}
        }
        
       //System.out.println("delete_ALARM_GENERATOR --->Answer_del="+Answer_del);
        return Answer_del;
    }    
    
            
  //Nome tabella
    private final String TAB_ALARM_THRESHOLD = "tab_alarm_threshold_generator";
    private final String tab_alarm_generator_thresholds = "tab_alarm_generator_thresholds";

    public int ID_THRESHOLD_GENERATOR;               
    public String DESCRIPTION;
    public int TYPE_THRESHOLD;
    public char ENABLE_HOLYDAY_THRESHOLD;
    public String STR_PLUGIN;
    public String TIPO_DI_CONFIGURAZIONE;
    public int THRESHOLD_PERSISTENCE;
    public int HOURS_AGGREGATE;
    
}


