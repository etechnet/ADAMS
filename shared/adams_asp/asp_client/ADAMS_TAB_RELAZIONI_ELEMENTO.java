/*
 * ADAMS_TAB_RELAZIONI_ELEMENTO.java
 *
 * Created on 12 maggio 2005, 15.35
 */

/**
 *
 * @author  root
 * @version 
 */

import java.util.Vector;

public class ADAMS_TAB_RELAZIONI_ELEMENTO {

    /** Creates new ADAMS_TAB_RELAZIONI_ELEMENTO */
    public ADAMS_TAB_RELAZIONI_ELEMENTO(String relation_name)
    {   
        RELATION_NAME       = relation_name.trim();        
        V_LISTA_ANALISI     = new Vector(); // Vector of Integer
        V_RESTRIZIONI       = new Vector(); // Vector of Integer
        V_RESTRIZIONI_OBBL  = new Vector(); // Vector of Integer
        DIREZIONE   = -1;
        HEXOUTPUT   = -1;
        NETWORK     = -1;
        GHOST       = -1;
        
        for(int i=0; i<nextLevelRelations.length; i++)
            nextLevelRelations[i] = 0;
    }
    
    
    public String RELATION_NAME;       // Varchar2 (256)
    public Vector V_LISTA_ANALISI;     // Vector of Integer
    public Vector V_RESTRIZIONI;       // Vector of Integer
    public Vector V_RESTRIZIONI_OBBL;  // Vector of Integer
    public int DIREZIONE;
    public int HEXOUTPUT;
    public int NETWORK;
    public int GHOST;
    
    // ****** Per la conversione CORBA  ******  
    public int idRelation;
    public int idParentRelation;			                // identificativo relazione padre alternativo alla selezione del 2^ el. traf.
    public int idFirstElement;			                	// link all'elemento di traffico primario
    public int idSecondElement;
    //int _NEXTLEVEL_RELATIONS = 150;
    public int[] nextLevelRelations = new int[ADAMS_GlobalParam._NEXTLEVEL_RELATIONS];
    
}