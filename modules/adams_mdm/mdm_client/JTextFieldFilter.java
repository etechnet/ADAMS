import javax.swing.text.*;
import java.util.*;


/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe non fa altro che estendere il componente standard JTextField ed impostare filtri 
 * personalizzati per l'immisione dei dati.
 *
 *<p align="center">&nbsp;</p>     
 */
public class JTextFieldFilter extends PlainDocument 
{
	// costanti per la definizione del filtro del JTextField
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (abcdefghijklmnopqrstuvwxyz)
      */
    public static final String LOWERCASE     	= "abcdefghijklmnopqrstuvwxyz";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (ABCDEFGHIJKLMNOPQRSTUVWXYZ)  
      */
    public static final String UPPERCASE     	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ)
      */
    public static final String ALPHA         	= LOWERCASE + UPPERCASE;
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (0123456789) 
      */
    public static final String NUMERIC       	= "0123456789";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (123456789) 
      */
    public static final String NUMERIC_NOZERO   = "123456789";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (123456789 .).
      */
    public static final String FLOAT         	= NUMERIC + ".";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789) 
      */
    public static final String ALPHA_NUMERIC 	= ALPHA + NUMERIC + " ";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo alcuni dei seguenti simboli:
      * "|!£$%&/\(){}?ìèéòçà°ù§.-_^:;@#+[]"
      */
    public static final String SOMESYMBOLS     = "<>~|!£$%&/(){}?§.,-_:;@#+[]\\=* "; //no simbolo ^
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi tutti i caratteri.
      */
    public static final String ALLCHAR  = "allchar";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (0123456789ABCDEFabcdef) 
      */
    public static final String HEX      = NUMERIC + "ABCDEFabcdef";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789) 
      */
    public static final String ALPHA_NUMERIC_SOMESYMBOLS 	= ALPHA + NUMERIC + SOMESYMBOLS + " ";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso nessun carattere è ammesso.  
      */
    protected String caratteriAmmessi = null;
    private int limitMIN;
    private int limitMAX;
    private int limit;
   
    /**
      *Costruttore di default imposta un filtro di tipo ALPHA_NUMERIC.  	     
      *@see #ALPHA_NUMERIC
      */
    public JTextFieldFilter() 
    {
        this(ALPHA_NUMERIC,10);
    }
    
    /**
      * Questo costruttore imposta il filtro per il JTextField e il limite da applicare, passato come argomento.
      * @param str filtro da impostare per il JTextField
      */
    public JTextFieldFilter(String str) 
    {
        caratteriAmmessi=str;
        this.limitMIN = 0;
        this.limitMAX = Integer.MAX_VALUE;
    }
      
    /**
     * Questo costruttore imposta il filtro per il JTextField e il range di valori che 
     * esso puo assumere che va da 0 a limit. 
     * @param str filtro da impostare per il JTextField.
     * @param limit limite superiore del range di valori che il JTextField puo assumere.
     */ 
    public JTextFieldFilter(String str,int limit) 
    {
        caratteriAmmessi=str;
        this.limit = limit;
        this.limitMIN = 0;
        this.limitMAX = Integer.MAX_VALUE;
    }
     
    /**
     * Questo metodo imposta il limite inferiore e superiore del range di valori che il filtro per il JTextField 
     * puo assumere. 
     * @param limitMIN limite inferiore del range di valori che il JTextField puo assumere.
     * @param limitMAX limite superiore del range di valori che il JTextField puo assumere.
     */
    public void setLimit(int limitMIN,int limitMAX) 
    {
        this.limitMIN = limitMIN;
	this.limitMAX = limitMAX;
    }
    

     /**
      * Ridefinizione del metodo insertString di PlainDocument, questo metodo imposta il filtro selezionato per al JTextField
      * @param offset - the offset into the document to insert the content >= 0. All positions that track change at or after the given location will move.
      * @param str - the string to insert
      * @param attr - the attributes to associate with the inserted content. This may be null if there are no attributes.
      */
    public void insertString(int offset, String  str, AttributeSet attr)
          throws BadLocationException 
    {
                
    	if ( (getLength() + str.length() <= limit) )
//&& (Integer.parseInt(str) >= limitMIN) && (Integer.parseInt(str) <= limitMAX) ) 
        {
			if (!caratteriAmmessi.equals(ALLCHAR))
            {
                if (str == null) return;
                                        
                if (caratteriAmmessi.equals(UPPERCASE))
		            str = str.toUpperCase();
        	    else if (caratteriAmmessi.equals(LOWERCASE))    
        	        str = str.toLowerCase();

                for (int i=0; i < str.length(); i++) 
    	            if (caratteriAmmessi.indexOf(str.valueOf(str.charAt(i))) == -1)
                        return;

                if (caratteriAmmessi.equals(FLOAT)) 
                if (str.indexOf(".") != -1)
                	if (getText(0, getLength()).indexOf(".") != -1) 
                    	return;
            }
            super.insertString(offset, str, attr);
        }
    }
}
