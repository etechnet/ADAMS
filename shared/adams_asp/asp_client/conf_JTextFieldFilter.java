import javax.swing.text.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_JTextFieldFilter </b> </p>
 *
 * Questa classe non fa altro che estendere il componente standard JTextField ed impostare filtri 
 * personalizzati per l'immisione dei dati.
 *    
 */
public class conf_JTextFieldFilter extends PlainDocument 
{
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
      * ammessi solo alcuni dei seguenti simboli:
      * "|!Â£$%&/\(){}?Ã¬Ã¨Ã©Ã²Ã§Ã Â°Ã¹Â§.-_^:;@#+[]"
      */
    public static final String SOMESYMBOLS     = "<>~|!Â£$%&/(){}?Â§.,-_:;@#+[]\\=* "; //no simbolo ^
                                                 
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
      * (0123456789-) 
      */
    public static final String NUMERIC_RANGE       	= "0123456789-";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789) 
      */
    public static final String ALPHA_NUMERIC 	= ALPHA + NUMERIC + " ";
    
    /**
      * Costante che definisce un filtro per il componente JTextField, in questo caso sono
      * ammessi solo i seguenti valori:
      * (abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789) 
      */
    public static final String ALPHA_NUMERIC_SOMESYMBOLS 	= ALPHA + NUMERIC + SOMESYMBOLS + " ";
    
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
      * <pre>
      * Costante che definisce un filtro per il componente JTextField, in questo caso nessun carattere Ã¨ ammesso.
      * </pre>  
      */
    protected String caratteriAmmessi = null;
    
    private int limit;
   
     /**
      * Costruttore di default imposta un filtro di tipo ALPHA_NUMERIC.  	    
      * @see #ALPHA_NUMERIC
      */
    public conf_JTextFieldFilter() 
    {
    	this(ALPHA_NUMERIC,10);
    }
    
     /**
      * Questo costruttore imposta il filtro per il JTextField e il limite da applicare, passato come argomento.	     
      * @param str filtro da impostare per il JTextField
      */
    public conf_JTextFieldFilter(String str) 
    {
    	caratteriAmmessi=str;
		this.limit = Integer.MAX_VALUE;
    }
	
    /**
     * Questo costruttore imposta il filtro per il JTextField e il range di valori che 
     * esso puo assumere che va da 0 a limit.
     * 
     * @param str filtro da impostare per il JTextField.
     * @param limit limite superiore del range di valori che il JTextField puo assumere.
     */ 
    public conf_JTextFieldFilter(String str, int limit) 
    {
    	caratteriAmmessi=str;
		this.limit = limit;
    }
	
    /**
     * Questo metodo imposta il limite inferiore e superiore del range di valori che il filtro per il JTextField 
     * puo assumere.
     *
     * @param limitMIN limite inferiore del range di valori che il JTextField puo assumere.
     * @param limitMAX limite superiore del range di valori che il JTextField puo assumere.
     */
    public void setLimit(int limit) 
    {
	    this.limit = limit;
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
	
		if ((getLength() + str.length()) <= limit) 
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
