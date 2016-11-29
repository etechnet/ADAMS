import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: JComboCheck </b> </p>
 * 
 *  Questa classe estende un JComboBox implementata con un ListCellRenderer, rendendo l'interfaccia del
 *  widget completamente personalizzabile tramite un Icona, l'oggetto mantiene il menÃ¹ a discesa con item
 *  di tipo Stringa, con la possibilitÃ  di affiancare un'icona per lo stato di ON e una per lo stato di OFF per
 *  singolo item.
 *
 * <p align="center">&nbsp;</p>
 */
public class JComboCheck extends JComboBox 
{

    
    /** Creates new JListIcon */
    private Vector[] dataListIcon	= new Vector[3];
  
    private ImageIcon imgIcon		= null;
    private Color colDef		= Color.black;
    private String str			= "";
    
    
    /**
     * 
     * Costruttore della classe.
     *
     *@param strDefault Stringa di default dell'oggetto.
     *@param iconDefault icona di default dell'oggetto. 
     */
    public JComboCheck(String strDefault, ImageIcon iconDefault)
    {
            super();
		for(int i=0;i<3;i++)
			dataListIcon[i]=new Vector();
		
		this.str = strDefault;
		this.imgIcon = iconDefault;

		//setRenderer(new TestCellRenderer());
    }
	
    /**
     *
     *@return Un'array di stringhe dei singoli Item.
     */
    public String[] getStringValues()
    {
        Object[] items=getSelectedObjects();
        String[] app;
        if(items==null)
               return null;
        app=new String[items.length];
        
        for(int i=0;i<items.length;i++) 
            app[i]=items[i].toString();
        return app;
    }
    
    /**
     *
     * Permette di inserire una stringa ed il relativo stato di un singolo item. 
     *@param AssociateText Stringa dell'item.
     *@param state Stato dell'Item True/false. 
     */
    public void addElement(String AssociateText,boolean state)
    {
        dataListIcon[0].addElement(AssociateText);
     	dataListIcon[1].addElement(new Boolean(state));
	dataListIcon[2].addElement(colDef);	//Colore di default
    }
    
    /**
     * Permette di inserire dinamicamente una stringa ed il relativo colore di foreground e lo stato di un singolo item. 
     *@param AssociateText Stringa dell'item.
     *@param c Colore di foreground della stringa dell'item.
     *@param state Stato dell'Item True/false. 
     */
    public void addElement(String AssociateText,Color c,boolean state)
    {
        dataListIcon[0].addElement(AssociateText);
     	dataListIcon[1].addElement(new Boolean(state));
	dataListIcon[2].addElement(c);
    }
	
    /**
     * Questo metodo convalida l'iserimento degli items, ed inoltre effettuta il setRenderer 
     * del''oggetto JComboCheck. 
     */
    public void convalida()
    {

	//setRenderer(new TestCellRenderer());
        for (int x=0;x<dataListIcon[0].size();x++)
		addItem(dataListIcon[0].elementAt(x));
        setRenderer(new TestCellRenderer());
    }
	
	
    /**
     * Questo metodo permette di rimuovere tutti gli items.
     */
    public void removeAll()
    {
	dataListIcon[0].removeAllElements();	
	dataListIcon[1].removeAllElements();
	dataListIcon[2].removeAllElements();
       this.removeAllItems();
    }
	
    /**
     * Questo metodo permette con un unica chiamata di valorizzare  lo stato di tutti 
     * gli items a true o a false.
     *@param stato Il valore true/false che si vuole assegnare a tutti items. 
     */
    public void set_AllStato(boolean stato)
    {
        for (int x=0;x<dataListIcon[1].size();x++)
            dataListIcon[1].setElementAt(new Boolean(stato),x);
    
    }
    
    /**
     * Questo metodo permette di valorizzare  lo stato di un singolo items.
     *@param INDEX L'indice dell'item.
     *@param stato Il valore true/false che si vuole assegnare all'item. 
     */
    public void setStato(int INDEX, boolean stato) 
    { 
	  // System.out.println("Pressed "+INDEX);
        if(INDEX != -1)
	    dataListIcon[1].setElementAt(new Boolean(stato),INDEX);                    
    }
	
    /**
     * Questo metodo ritorna lo stato di un singlo item.
     *@param INDEX L'indice dell'item.
     *@return Lo stato dell'item true/false  
     */
    public boolean getStato(int INDEX) 
    {
        
        return ( (Boolean)dataListIcon[1].elementAt(INDEX)).booleanValue();	                    
    }
    
    /**
     *@return Vettore contenente le stringhe contenute negli Items.
     */
    public Vector getAllSelected() 
    {  
        Vector appo = new Vector();
        for (int x=0;x<dataListIcon[0].size();x++)
        {    
            if ( ((Boolean)dataListIcon[1].elementAt(x)).booleanValue() )
                appo.addElement(dataListIcon[0].elementAt(x));
        }
        return(appo);	                    
    }
    
    
   /**
    * Questo metodo rintorna il numero di items presenti nell'oggetto.
    *@return Il numero di Items presenti.
    */
    public int NumItem()
    {
    	return dataListIcon[1].size();
    }
    
  
    
    class TestCellRenderer extends JCheckBox implements ListCellRenderer    
    {
        public Component getListCellRendererComponent( JList listbox, Object value, int index, boolean isSelected,boolean cellHasFocus) 
        {
            
            if (dataListIcon[1].size()>0)           
            {
                String s = value.toString();
                setText(s);
                if(index != -1)
                {
                    setFont(new java.awt.Font ("Arial", 0, 10));
                    setIcon(null);
                    setSelected(((Boolean)dataListIcon[1].elementAt(index)).booleanValue());			
                }

                if (index == -1)
                {				
                    setText(str);
                    setIcon(imgIcon);
                    setSelected(false);			
                }
                if(isSelected) 
                {    		
                    setBackground(listbox.getSelectionBackground());
		    try
		    {	
		    	setForeground((Color)dataListIcon[2].elementAt(index));		  	  
		    }catch(Exception e)
		    {
		    	setForeground(listbox.getSelectionForeground());	
		    }
                }
                else 
                {
                    setBackground(listbox.getBackground());
		    try
		    {  
		    	setForeground((Color)dataListIcon[2].elementAt(index));         
		    }catch(Exception e)
		    {
		    	setForeground(listbox.getSelectionForeground());	
		    }
                }
            }
           
	    return this;
        }
    }//end class TestCellRenderer	

}//end public class JComboCheck
