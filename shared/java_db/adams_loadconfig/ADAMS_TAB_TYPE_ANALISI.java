package net.etech.loadconfig;
/*
 * ADAMS_TAB_TYPE_ANALISI.java
 *
 * Created on 16 settembre 2005, 11.42
 */

/**
 *
 * @author  root
 * @version 
 */
import java.util.Vector;
public class ADAMS_TAB_TYPE_ANALISI {

    /** Creates new ADAMS_TAB_TYPE_ANALISI */
    public ADAMS_TAB_TYPE_ANALISI() {
        TIPO_DI_CONFIGURAZIONE  = new String();     //NOT NULL VARCHAR2(30)
        IDANALISI               = -1;               //NOT NULL NUMBER
        NOMEANALISI             = new String();     //VARCHAR2(160)
        IDCOUNTERKIT            = -1;               //NUMBER
        FLAGSORT                = 'N';              //NOT NULL CHAR(1)
        FLAGDATA                = 'N';              //NOT NULL CHAR(1)
        FLAGCENTRALE            = 'N';              //NOT NULL CHAR(1)
        FLAGCUMULAZIONE         = 'N';              //NOT NULL CHAR(1)
        LISTA_REPORT            = new Vector();     //Vettore di Integer
    }

    public String   TIPO_DI_CONFIGURAZIONE;     //NOT NULL VARCHAR2(30)
    public int      IDANALISI;                  //NOT NULL NUMBER
    public String   NOMEANALISI;                //VARCHAR2(160)
    public int      IDCOUNTERKIT;               //NUMBER
    public char     FLAGSORT;                   //NOT NULL CHAR(1)
    public char     FLAGDATA;                   //NOT NULL CHAR(1)
    public char     FLAGCENTRALE;               //NOT NULL CHAR(1)
    public char     FLAGCUMULAZIONE;            //NOT NULL CHAR(1)
    public Vector   LISTA_REPORT;               //TAB_REPORT_ABL --- Vettore di Integer ---
    
    //Nome tabella
    private final String name_TabAnalisi = "tab_analysis_type";

}
