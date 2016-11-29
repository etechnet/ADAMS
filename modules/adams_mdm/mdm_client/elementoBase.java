import java.util.*;
import javax.swing.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Classe che rappresenta un elemento base per la struttura <b>BufferRestrizioni</b>
 * un elemento base non è altre che una restrizione che in seguito verrà inviata al Master Server 
 * per essere elaborata.
 * Un elemento base è cosi definito:
 * - idTE:  identificativo del Traffic Element,
 * - op:    operatore da applicare a questa restrizione,
 * - pri:   prioritò del Traffic Element,
 * - teDesc:    descrizione del Traffic Element,
 * - value:     valori inseriti per questa particolare restrizione.
 * - valueLabel:    descrizione dei valori inseriti
 * - isRestriction:	definisce se la restrizione è reale o meno.
 * 
 * @see BufferRestrizioni
 * @see #idTE
 * @see #op
 * @see #teDesc
 * @see #value
 * @see #valueLabel
 * @see #isRestriction     
 */
public class elementoBase
{
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Variabile intera rappresentante l'identificativo del Traffic Element.
     * </font></font></p></pre>  
     */
    public int idTE;
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Variabile intera rappresentante l'operatore da applicare alla particolare restrizione.
     * </font></font></p></pre>  
     */
    public int op;
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Variabile intera rappresentante la priorità del Traffic Element
     * </font></font></p></pre>  
     */
    public int  pri;
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Variabile intera rappresentante l'eccezione del Traffic Element.
     * </font></font></p></pre>  
     */
    public int  idEcc;
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Variabile stringa rappresentante la descrizione del Traffic Element.
     * </font></font></p></pre>  
     */
    public String teDesc;
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Vettore contenente i vari valori da applicare alla restrizione.
     * </font></font></p></pre>  
     */
    public Vector value		= new Vector();
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Vettore contenente le descrizione dei valori da applicare alla restrizione:
     * Es.: se si sceglie una restrizione ad un CTO un valore possibile potra essere
     * 81 con la descrizione Giappone.
     * </font></font></p></pre>  
     */
    
    public Vector valueLabel	= new Vector();
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Indica se la restrizione è fittizia o reale.
     *
     * <b>isRestriction</b> = false: restrizione fittizia, utilizzata solamente dal client per gestire particolari eccezioni.
     * <b>isRestriction</b> = true: restrizione reale, inviata al Master server per essere elaborata.
     * </font></font></p></pre>  
     */
    public boolean isRestriction;

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore che istanzia un elemento base.
     * </font></font></p></pre> 
     * @param idTE
     * @param teDesc
     * @param flag
     * @param op
     * @param pri
     * @param idEcc
     * @see #idTE
     * @see #teDesc
     * @see #isRestriction
     * @see #op
     * @see #pri
     */ 
    public elementoBase(int idTE,String teDesc,boolean flag,int op,int pri,int idEcc)
    {
        this.idTE=idTE;
        this.teDesc=teDesc;
        this.isRestriction=flag;
        this.op=op;
        this.pri=pri;
        this.idEcc=idEcc;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Aggiunge un valore alla restrizione.
     * </font></font></p></pre>  
     * @param val valore numerico da inserire
     * @param valLabel descrizione del valore
     */
    public void addValue(String val,String valLabel,boolean enableMessage)
    {
        if (isPresentValAscii(val)==-1)
        {
            if(value.size() < staticLib.mdm_server_serverRef.eMAX_N_VALUE) //103
            {
                value.addElement(val);
                valueLabel.addElement(valLabel);
            }
        }
        else
        {
            if(enableMessage)
                staticLib.optionPanel("This value is present.","Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
                
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Sostituisce il primo elemento inserito nella restrizione con il valore passato al metodo
     * </font></font></p></pre>  
     * @param val valore numerico da inserire
     * @param valLabel descrizione del valore
     */
    public void sostValue(String val,String valLabel)
    {
        value.removeAllElements();              
        valueLabel.removeAllElements();  
        value.addElement(val);
        valueLabel.addElement(valLabel);                
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Aggiunge un valore alla restrizione.
     * </font></font></p></pre>  
     * @param val valore numerico da inserire
     */
    public void addValue(String val)
    {
        if (isPresentValAscii(val)==-1)
        {
            value.addElement(val);
            valueLabel.addElement(val);
        }
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna il numero di valori inseriti per la restrizione attuale.
     * </font></font></p></pre>  
     */
    public int getSize()
    {
        return value.size();
    }

    public void removeValueAscii(String val)
    {
        int index=isPresentValAscii(val);
        if (index!=-1)
        {
            value.removeElementAt(index);
            valueLabel.removeElementAt(index);
        }
    }
            			
    public void removeValue(String descVal)
    {
        int index=isPresentVal(descVal);
        if (index!=-1)
        {
            value.removeElementAt(index);
            valueLabel.removeElementAt(index);
        }
    }
            			
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna il valore presente nella posizione specificata.
     * </font></font></p></pre>  
     * @param pos indica la posizione all'interno del vettore <b>value</b>
     * @see #value
     */ 
    public String getDescValueGUI3(int pos)
    {
        try
        {
            return (String)valueLabel.elementAt(pos);
        }catch(Exception e)
        {
            return null;
        }
    }
      
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la descrizionde del valore specifivato.
     * </font></font></p></pre>  
     * @param val valore all'interno del vettore <b>value</b>
     * @see #valueLabel
     */ 
    public String getDescValue(String val)
    {
        int index=isPresentValAscii(val);
        if (index!=-1)
        {
            return (String)valueLabel.elementAt(index);
        }else
        return null;
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna il valore relativo alla descrizione specificata.
     * </font></font></p></pre>  
     * @param val descrizione valore all'interno del vettore <b>value</b>
     * @see #value
     */  
    public String getValue(String val)
    {
        int index=isPresentValAscii(val);
        if (index!=-1)
        {
            //return ((Integer)value.elementAt(index)).intValue();
            return (String)value.elementAt(index);
        }
        else
        {
            return null;
        }
    }
		
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Controlla se c'è qualche valore inserito nel vettore di restrizioni.
     * Il metodo restituisce <b>false</b> se non c'è alcun valore, <b>true</b> altrimenti.
     * </font></font></p></pre>  
     * @see #value
     * @see #valueLabel
     */ 
    public boolean isEmpty()
    {
            if(value.size()>0)
                            return false;
            else
                            return true;

    }
        
    public int isPresentValAscii(String val)
    {     
        return value.lastIndexOf(val);
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Controlla se il valore specificato è presente nel vettore.
     * Il metodo torna l'indice della posizione del valore d'interesse se non presente ritorna -1.
     * </font></font></p></pre>  
     * @parma val valore da controllare
     */ 
    public int isPresentVal(String val)
    {
        int v = valueLabel.lastIndexOf(val);
        return v;
    }      
    
    public void stampaValue()
    {
    	System.out.println("**************** VALUE ********************");
       	for(int i=0;i<value.size();i++)
       	{
       		System.out.println((new String((String)value.elementAt(i))).trim());
	}
    }   
    
    public void stampaValueLabel()
    {
    	System.out.println("**************** VALUELABEL ********************");
       	for(int i=0;i<valueLabel.size();i++)
       	{
       		System.out.println((new String((String)valueLabel.elementAt(i))).trim());
	}
    }   
}
