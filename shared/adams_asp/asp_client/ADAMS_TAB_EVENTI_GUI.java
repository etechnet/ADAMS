/*
 * ADAMS_TAB_EVENT_GUI.java
 *
 * Created on 4 agosto 2005, 12.04
 */

/**
 *
 * @author  root
 * @version 
 */
public class ADAMS_TAB_EVENTI_GUI {

    /** Creates new ADAMS_TAB_EVENT_GUI */
    public ADAMS_TAB_EVENTI_GUI() 
    {
        TIPO_DI_CONFIGURAZIONE  = new String(); //NOT NULL VARCHAR2(30)       
        IDEXCEPTION             = -1;           //NOT NULL NUMBER
        TAG                     = new String(); //VARCHAR2(30)
        DESCRIPTION             = new String(); //VARCHAR2(160)
        IDTRIGGERRESTRICTION    = -1;           //NUMBER
        TRIGGEREDSTATUS         = -1;           //NUMBER
        TRIGGEREDVALUE          = new String(); //VARCHAR2(26)
        ACTION                  = -1;           //NUMBER
        TARGETVALUE             = new String(); //VARCHAR2(26)
        IDAGGREGATEEXCEPTION    = -1;           //NUMBER
    }

    public int insert_GUI_EVENT()
    {
        String itemName_GE =("TIPO_DI_CONFIGURAZIONE,"+   
                            "IDEXCEPTION,"+
                            "TAG,"+
                            "DESCRIPTION,"+
                            "IDTRIGGERRESTRICTION,"+
                            "TRIGGEREDSTATUS,"+
                            "TRIGGEREDVALUE,"+
                            "ACTION,"+
                            "TARGETVALUE,"+
                            "IDAGGREGATEEXCEPTION");

        String itemValue_GE = ( "'"+TIPO_DI_CONFIGURAZIONE+"',"+
                                "IDEXCEPTION_seq.nextVal,"+
                                "'"+TAG+"',"+
                                "'"+DESCRIPTION+"',"+
                                IDTRIGGERRESTRICTION+","+
                                TRIGGEREDSTATUS+","+
                                "'"+TRIGGEREDVALUE+"',"+
                                ACTION+","+
                                "'"+TARGETVALUE+"',"+
                                IDAGGREGATEEXCEPTION);
               
        String str_Insert_GE = ("insert intotab_gui_events ("+itemName_GE+") values ( "+itemValue_GE+" )");
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_GE);
        
        //System.out.println("insert_GUI_EVENT --> "+str_Insert_GE);
        
        if(Answer_Ins == 1)
            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
        return Answer_Ins;
    }
    
    public int update_GUI_EVENT()
    {
        String str_update_GE =  "update tab_gui_events set TAG='"+TAG+"',"+
                                "DESCRIPTION='"+DESCRIPTION+"',"+
                                "IDTRIGGERRESTRICTION="+IDTRIGGERRESTRICTION+","+
                                "TRIGGEREDSTATUS="+TRIGGEREDSTATUS+","+
                                "TRIGGEREDVALUE='"+TRIGGEREDVALUE+"',"+
                                "ACTION="+ACTION+","+
                                "TARGETVALUE='"+TARGETVALUE+"',"+
                                "IDAGGREGATEEXCEPTION="+IDAGGREGATEEXCEPTION+" "+
                                "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                                "IDEXCEPTION="+IDEXCEPTION;
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update_GE);
        
        if(Answer_update > 0)
            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
        return Answer_update;
    }
    
    public int delete_GUI_EVENT()
    {         
        //System.out.println("delete_GUI_EVENT 1 -- cancello la righa con IDEXCEPTION ="+IDEXCEPTION); 
        String str_delete = "delete from tab_gui_events where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and IDEXCEPTION="+IDEXCEPTION;
        
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
        
        // resetto le ventuali aggregazioni IDEXCEPTION  eliminato
        //System.out.println("delete_GUI_EVENT 2 -- resetto le righe con IDAGGREGATEEXCEPTION ="+IDEXCEPTION);
        String str_update_GE =  "update tab_gui_events set IDAGGREGATEEXCEPTION = -1 "+
                                "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                                "IDAGGREGATEEXCEPTION="+IDEXCEPTION;

        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_update_GE);

        //System.out.println("esito delete= "+Answer_del+" "+str_delete);
        
        if(Answer_del > 0)
            ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
        return Answer_del;
    }    
    
 // Campi della Tabella TAB_EVENT_GUI
 public String      TIPO_DI_CONFIGURAZIONE;      //NOT NULL VARCHAR2(30)      
 public int         IDEXCEPTION;                 //NOT NULL NUMBER
 public String      TAG;                         //VARCHAR2(30)
 public String      DESCRIPTION;                 //VARCHAR2(160)
 public int         IDTRIGGERRESTRICTION;        //NUMBER
 public int         TRIGGEREDSTATUS;             //NUMBER
 public String      TRIGGEREDVALUE;              //VARCHAR2(26)
 public int         ACTION;                      //NUMBER
 public String      TARGETVALUE;                 //VARCHAR2(26)
 public int         IDAGGREGATEEXCEPTION;        //NUMBER
 
}
/*desc tab_EVENTI_GUI
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 TIPO_DI_CONFIGURAZIONE                    NOT NULL VARCHAR2(30)
 IDEXCEPTION                               NOT NULL NUMBER
 TAG                                                VARCHAR2(30)
 DESCRIPTION                                        VARCHAR2(160)
 IDTRIGGERRESTRICTION                               NUMBER
 TRIGGEREDSTATUS                                    NUMBER
 TRIGGEREDVALUE                                     VARCHAR2(26)
 ACTION                                             NUMBER
 TARGETVALUE                                        VARCHAR2(26)
 IDAGGREGATEEXCEPTION                               NUMBER
*/