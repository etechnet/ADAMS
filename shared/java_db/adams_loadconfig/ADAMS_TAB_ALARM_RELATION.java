package net.etech.loadconfig;

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
    
   
}


    
