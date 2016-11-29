package net.etech.loadconfig;

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

}



