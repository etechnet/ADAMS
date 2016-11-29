/*
 * ADAMS_TAB_SCRIPTS_LISTA.java
 *
 * Created on 9 novembre 2005, 10.16
 */

/**
 *
 * @author  Raffaele Ficcadenti
 */

public class ADAMS_TAB_SCRIPTS_LISTA {

    private int     SCRIPT              = -1;                                        
    private int     SCRIPT_TYPE         = -1;  

    public ADAMS_TAB_SCRIPTS_LISTA(int script, int script_type) 
    {
        SCRIPT      = script;
        SCRIPT_TYPE = script_type;  
    }
    
    public int get_SCRIPT() 
    { 
        return SCRIPT; 
    }
    public int get_SCRIPT_TYPE()
    {
        return SCRIPT_TYPE;
    }
    
    public void set_SCRIPT(int script) 
    { 
        SCRIPT = script; 
    }
    public void set_SCRIPT_TYPE(int script_type)
    {
        SCRIPT_TYPE = script_type; 
    }   
}
