package net.etech.loadconfig;
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