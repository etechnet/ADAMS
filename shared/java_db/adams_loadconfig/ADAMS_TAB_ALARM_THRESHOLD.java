package net.etech.loadconfig;
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


