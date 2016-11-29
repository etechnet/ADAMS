import java.util.Vector;
public class ADAMS_TAB_ALARM_GENERATOR {

    /** Creates new ADAMS_TAB_ALARM_GENERATOR */
    public ADAMS_TAB_ALARM_GENERATOR(String TipoDiConfigurazione) {      
               
        ID_ALARM_GENERATOR = -1;               
        SHORT_DESCRIPTION = "" ;
        LONG_DESCRIPTION = "";
        STR_PLUGIN = "";
        THRESHOLD_MANAGEMENT = 'N';
        TIPO_DI_CONFIGURAZIONE = TipoDiConfigurazione;
        
        V_ALARM_THRESHOLD = new Vector();
    }  
    
    public int insert_ALARM_GENERATOR()
    {        
        
        //System.out.println("insert_ALARM_GENERATOR(..)");
        ID_ALARM_GENERATOR = ADAMS_GlobalParam.connect_Oracle.Query_int("select tab_alarm_generator_SEQ.nextVal from dual");
        //System.out.println("Insert tab_alarm_generator_SEQ.nextVal "+ID_ALARM_GENERATOR);
        
        if(ID_ALARM_GENERATOR < 0)
        {
            ADAMS_GlobalParam.optionPanel((javax.swing.JFrame)null,"Operation failed [Error: tab_alarm_generator_SEQ.nextVal].","ERROR",1);
            return -1;
        }
                             
        String itemName =("ID_ALARM_GENERATOR,"+   
                            "SHORT_DESCRIPTION,"+
                            "LONG_DESCRIPTION,"+
                            "STR_PLUGIN,"+
                            "THRESHOLD_MANAGEMENT,"+
                            "TIPO_DI_CONFIGURAZIONE");

        String itemValue = (    ID_ALARM_GENERATOR+","+   
                                "'"+SHORT_DESCRIPTION+"',"+
                                "'"+LONG_DESCRIPTION+"',"+
                                "'"+STR_PLUGIN+"',"+                                 
                                "'"+THRESHOLD_MANAGEMENT+"',"+
                                "'"+TIPO_DI_CONFIGURAZIONE+"'");
        
        String str_Insert = ("insert into "+tab_alarm_generator+" ("+itemName+") values ( "+itemValue+" )");
        //System.out.println("str_Insert --> "+str_Insert);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);
        
        int Answer_Ins_th = -1;
        if(Answer_Ins > 0)
        {
            // ++++++++++++++++++++++++ tab_alarm_generator_thresholds +++++++++++++++++++++++++++++++++++            
            int size_A_th = V_ALARM_THRESHOLD.size();
            if(size_A_th > 0)
            {
                adams_tab_alarm_generator_thresholds row_AlarmThreshold = new adams_tab_alarm_generator_thresholds(this.TIPO_DI_CONFIGURAZIONE);
                row_AlarmThreshold.ID_ALARM_GENERATOR = this.ID_ALARM_GENERATOR;                             

                for(int i=0; i<size_A_th; i++)
                {
                    row_AlarmThreshold.ID_THRESHOLD_GENERATOR = ((Integer)V_ALARM_THRESHOLD.elementAt(i)).intValue();

                    String itemName_th =("ID_ALARM_GENERATOR,ID_THRESHOLD_GENERATOR,TIPO_DI_CONFIGURAZIONE");

                    String itemValue_th = (row_AlarmThreshold.ID_ALARM_GENERATOR+","+ 
                                            row_AlarmThreshold.ID_THRESHOLD_GENERATOR+","+ 
                                           "'"+row_AlarmThreshold.TIPO_DI_CONFIGURAZIONE+"'");

                    String str_Insert_th = ("insert into "+tab_alarm_generator_thresholds+" ("+itemName_th+") values ( "+itemValue_th+" )");
                    //System.out.println("str_Insert_th --> "+str_Insert_th);

                    Answer_Ins_th = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_th);

                    if(Answer_Ins_th < 0)
                    {
                        System.out.println("ERROR insert into "+tab_alarm_generator_thresholds);
                        break;
                    }
                }
            }
            else
            {
                //System.out.println("Non Ci sono THRESHOLD abilitate");
                Answer_Ins_th = 0;
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }
        
       try
        {         
            if( (Answer_Ins >= 0) && (Answer_Ins_th >= 0) )
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                //System.out.println("rollback()");
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
        return Answer_Ins;
    }
    
    public int update_ALARM_GENERATOR()
    {

        //System.out.println("update_ALARM_GENERATOR(..)");
        
        String str_update = "update "+tab_alarm_generator+" set SHORT_DESCRIPTION='"+SHORT_DESCRIPTION+"',LONG_DESCRIPTION='"+LONG_DESCRIPTION+"',"+
                            "STR_PLUGIN='"+STR_PLUGIN+"',THRESHOLD_MANAGEMENT='"+THRESHOLD_MANAGEMENT+"' "+
                            "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                            "ID_ALARM_GENERATOR="+ID_ALARM_GENERATOR;
        
            
        //System.out.println("str_update = "+str_update);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
        
        int Answer_del      = -1;
        int Answer_Ins_th   = -1;
        
        if(Answer_update >= 0 )
        {
            String str_delete = "delete from "+tab_alarm_generator_thresholds+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and ID_ALARM_GENERATOR="+ID_ALARM_GENERATOR;
        
            //System.out.println("esito delete -->> = "+str_delete);
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
            
            if(Answer_del >= 0)
            {            
                // ++++++++++++++++++++++++ tab_alarm_generator_thresholds +++++++++++++++++++++++++++++++++++            
                int size_A_th = V_ALARM_THRESHOLD.size();
                if(size_A_th > 0)
                {
                    adams_tab_alarm_generator_thresholds row_AlarmThreshold = new adams_tab_alarm_generator_thresholds(this.TIPO_DI_CONFIGURAZIONE);
                    row_AlarmThreshold.ID_ALARM_GENERATOR = this.ID_ALARM_GENERATOR;                             

                    for(int i=0; i<size_A_th; i++)
                    {
                        row_AlarmThreshold.ID_THRESHOLD_GENERATOR = ((Integer)V_ALARM_THRESHOLD.elementAt(i)).intValue();

                        String itemName_th =("ID_ALARM_GENERATOR,ID_THRESHOLD_GENERATOR,TIPO_DI_CONFIGURAZIONE");

                        String itemValue_th = (row_AlarmThreshold.ID_ALARM_GENERATOR+","+ 
                                                row_AlarmThreshold.ID_THRESHOLD_GENERATOR+","+ 
                                               "'"+row_AlarmThreshold.TIPO_DI_CONFIGURAZIONE+"'");

                        String str_Insert_th = ("insert into "+tab_alarm_generator_thresholds+" ("+itemName_th+") values ( "+itemValue_th+" )");
                        //System.out.println("str_Insert_th --> "+str_Insert_th);

                        Answer_Ins_th = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_th);

                        if(Answer_Ins_th < 0)
                        {
                            System.out.println("ERROR insert into "+tab_alarm_generator_thresholds);
                            break;
                        }
                    }
                }
                else
                {
                    //System.out.println("Non Ci sono THRESHOLD abilitate");
                    Answer_Ins_th = 0;
                }
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            }        
        }
            
                        
        try
        {         
            if( (Answer_update >= 0) && (Answer_del >= 0) && (Answer_Ins_th >= 0) )
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                //System.out.println("rollback()");
                Answer_update = -1;
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_ALARM_GENERATOR.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_GENERATOR.java 2A");}
        }
        
        return Answer_update; 
    }
    
    
    public int delete_ALARM_GENERATOR()
    {         
        //System.out.println("delete_ALARM_GENERATOR(..)");
                  
        String str_delete = "delete from "+tab_alarm_generator+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_ALARM_GENERATOR="+ID_ALARM_GENERATOR;

        String str_delete_2 = "delete from "+tab_alarm_generator_thresholds+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_ALARM_GENERATOR="+ID_ALARM_GENERATOR;

        String str_delete_3 = "delete from "+tab_alarm_relation_handlers+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                             " and ID_ALARM_GENERATOR="+ID_ALARM_GENERATOR;

      
        //System.out.println("str_delete "+str_delete);

        // delete tabella tab_alarm_generator
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);   

        //System.out.println("str_delete_2 "+str_delete_2);
        // delete tabella tab_alarm_generator_thresholds
        if(Answer_del >= 0)
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_2); 
        
        //System.out.println("str_delete_3 "+str_delete_3);
        // delete tabella tab_alarm_generator_thresholds
        if(Answer_del >= 0)
            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_3); 

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
            System.out.println("Errore ADAMS_TAB_ALARM_GENERATOR.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_GENERATOR.java 2A");}
        }
        
        //System.out.println("delete_ALARM_GENERATOR --->Answer_del="+Answer_del);
        return Answer_del;
    }    
    
            
  //Nome tabelle
    private final String tab_alarm_generator = "tab_alarm_generator";
    private final String tab_alarm_generator_thresholds = "tab_alarm_generator_thresholds";
    private final String tab_alarm_relation_handlers = "tab_alarm_relation_handlers";

    public int ID_ALARM_GENERATOR;               
    public String SHORT_DESCRIPTION;
    public String LONG_DESCRIPTION;
    public String STR_PLUGIN;
    public char THRESHOLD_MANAGEMENT;
    public String TIPO_DI_CONFIGURAZIONE;
    
    public Vector V_ALARM_THRESHOLD = null;
    
    
    class adams_tab_alarm_generator_thresholds
    {        
        adams_tab_alarm_generator_thresholds(String TipoDiConfigurazione)
        {
             ID_ALARM_GENERATOR  = -1;                           
             ID_THRESHOLD_GENERATOR = -1;
             TIPO_DI_CONFIGURAZIONE = new String(TipoDiConfigurazione);  
        }
        public int ID_ALARM_GENERATOR;
        public int ID_THRESHOLD_GENERATOR;  
        public String TIPO_DI_CONFIGURAZIONE;                                                                   
    }
}



