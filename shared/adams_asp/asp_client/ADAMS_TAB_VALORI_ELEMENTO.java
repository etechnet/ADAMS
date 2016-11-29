/*
 * ADAMS_TAB_VALORI_ELEMENTO.java
 *
 * Created on 12 maggio 2005, 11.38
 */

/**
 *
 * @author  root
 * @version 
 */
public class ADAMS_TAB_VALORI_ELEMENTO {

    /** Creates new ADAMS_TAB_VALORI_ELEMENTO */
    public ADAMS_TAB_VALORI_ELEMENTO(int cod_valore, String desc_valore) 
    {
        COD_VALORE  = cod_valore;
        DESC_VALORE = desc_valore;  // - max length 30
    }
    
    public int get_COD_VALORE() 
    { 
        return COD_VALORE; 
    }
    public String get_DESC_VALORE()
    {
        return DESC_VALORE;
    }
    
    public void set_COD_VALORE(int cod_valore ) 
    { 
        COD_VALORE = cod_valore; 
    }
    public void set_DESC_VALORE(String desc_valore)
    {
        DESC_VALORE = desc_valore;  // - max length 30
    }
    

 private int     COD_VALORE;                                        
 private String  DESC_VALORE;                             
}
