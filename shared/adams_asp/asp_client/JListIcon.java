import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe estende la classe JList, e crea una lista personalizzata sulla base dell'oggetto JList.
 * Nell'oggetto JListIcon è possibile inserire delle voci con associate delle immagini.
 * La classe eredita tutte le proprietà dell'oggetto JList.
 * Ogni elemento della lista è un oggetto di tipo: <b>JRadioButton</b>
 */
public class JListIcon extends JList 
{
	final static int icon=0;
    	final static int iconSelected=1;
	final static int text=2;
    
    	/** Creates new JListIcon */
    	private Vector[] dataListIcon	= new Vector[3];
	private Vector dataListIconColor= new Vector();
    	private Color col 		= new Color(240,240,240);
	private boolean selezione	= true;
    	private boolean flagColor	= false;
    	private ImageIcon imgIcon;
    	private ImageIcon imgIconSelected;
    	private IconPool iconPool;
        private Font fontSel = new java.awt.Font ("Verdana", 1, 11);
        private Font fontNoSel = new java.awt.Font ("Verdana", 0, 11);

	
	public void setFlagColor(boolean f)
	{
		this.flagColor=f;
	}
	
        /**
          * Costruttore della lista: crea un vettore vuoto contenente le varie voci della lista, ed imposta
          * il cellRenderer della JList con il cellRenderer ridefinito all'interno della classe JListIcon.
          * @param iconPool oggetto di tipo <b>IconPool</b>, riferimento ad un Pool di icone.
          * @see IconPool
          */
	public JListIcon(IconPool iconPool)
    	{
        	super();
		
                for(int i=0;i<3;i++)
                        dataListIcon[i]=new Vector();
                
                setCellRenderer(new MyCellRenderer());
                this.iconPool=iconPool;
    	}
        
        /**
          * Costruttore della lista: crea la lista impostando ogni singola voce, ed imposta
          * il cellRenderer della JList con il cellRenderer ridefinito all'interno della classe JListIcon.
          * @param icon array di stringe contenente i nomi delle icone di default di ogni singola voce della lista,
          * @param iconSelected array di stringe contenente i nomi delle icone di ogni singola voce della lista, quando ques'ultime vengono selezionate,
          * @param text array di stringe contenente il testo di ogni singola voce della lista.
          * @see IconPool
          */
    	public JListIcon(String[] icon, String[] iconSelected,String[] text)
    	{   
                super(text);
                for(int i=0;i<3;i++)
            	dataListIcon[i]=new Vector();
        
            	for(int i=0;i<icon.length;i++)
            	{
                	imgIcon = new ImageIcon(getClass().getResource(icon[i]));
                	dataListIcon[0].addElement(imgIcon);
                
                	imgIconSelected = new ImageIcon(getClass().getResource(iconSelected[i]));
                	dataListIcon[1].addElement(imgIconSelected);
                        
                	dataListIcon[2].addElement(text[i]);
            	}
            
            	setCellRenderer(new MyCellRenderer());
    	}

        /**
        * Imposta il colore di background da utilizzare quando viene selezionata una voce dalla lista.
        * @param c colore di background da utilizzare per caratterizzare una voce della lista selezionata.
        */
   	public void setSelectionColor(Color c)
    	{
        	this.col=c;
    	}
    
       /**
        * Restituisce un array di stringhe contenente tutte le voci della lista.
        * @return rray di stringhe contenente tutte le voci della lista.
        * @see IconPool
        */
    	public String[] getStringValues()
    	{
        	Object[] items=getSelectedValues();
        	String[] app;
        
        	if(items==null)
               		return null;
        	app=new String[items.length];
        
        	for(int i=0;i<items.length;i++) 
            		app[i]=items[i].toString();
        
       		return app;
    	}
    
        /**
         * Aggiunge un elemento alla lista.
         * @param PathIcon stringa che identifica l'icona di default associata all'elemento da inserire nella lista.
         * @param PathIconSelected stringa che identifica l'icona (in caso di elemento selezionato) associata all'elemento da inserire nella lista,
         * @param AssociateText testo associato all'elemento da inserire nella lista.
         */
    	public synchronized void addElement(String PathIcon , String PathIconSelected , String AssociateText)
    	{
        	dataListIcon[0].addElement(PathIcon);
        	dataListIcon[1].addElement(PathIconSelected);
        	dataListIcon[2].addElement(AssociateText); 
        	setListData(dataListIcon[2]);
    	}
	
        /**
         * Aggiunge un elemento alla lista.
         * @param PathIcon stringa che identifica l'icona di default associata all'elemento da inserire nella lista.
         * @param PathIconSelected stringa che identifica l'icona (in caso di elemento selezionato) associata all'elemento da inserire nella lista,
         * @param AssociateText testo associato all'elemento da inserire nella lista.
         * @param  Color colore.
         */
	public synchronized void addElement(String PathIcon , String PathIconSelected , String AssociateText,Color c)
    	{
        	dataListIcon[0].addElement(PathIcon);
        	dataListIcon[1].addElement(PathIconSelected);
        	dataListIcon[2].addElement(AssociateText); 
		dataListIconColor.addElement(c);
        	setListData(dataListIcon[2]);
    	}
    
    	public String get_ID_Value(int ID)
    	{
            try
            {
                return( (dataListIcon[2].elementAt(ID)).toString()); 
            }
            catch(Exception e)
            {
                return "";
            }
    	}	
    
       /**
        * Rimuove l'elemento alla posizione specificata.
        * @param index indice della voce all'interno della lista.
        */
        public synchronized void remElement(int Index)
        {        
            try
            {
                dataListIcon[0].removeElementAt(Index);
                dataListIcon[1].removeElementAt(Index);
                dataListIcon[2].removeElementAt(Index);
            }
            catch (Exception e)
            {
                System.out.println("error remElement() "); 
            } 
        }
        
        /**
         * Svuota tutta la lista.
         */
        public synchronized void removeAll()
        {
            try
            {
                dataListIcon[0].removeAllElements();    
                dataListIcon[1].removeAllElements();
                dataListIcon[2].removeAllElements();
            }
            catch (Exception e)
            {
                System.out.println("error removeAll() "); 
            } 
        }
        
        
        /**
         * Indica se gli elementi della lista possono essere selezionati o meno.
         * @param set   un valore 'true' indica che gli elementi della lista possono essere selezionati,
         *  un valore 'false' indica che gli elementi della lista non possono essere selezionati.
         */
        public void setSelection(boolean set)
        {
                selezione=set;
        }

        /**
         * Ritorna il nome dell'icona di default associata all'elemento della lista specificato.
         * @param index indice della voce all'interno della lista.
         */
        public Icon getIconAt(int index)
        {
        	return iconPool.getIcon((String)dataListIcon[0].elementAt(index));
        }
        
        public void setIconAt(int index,String PathIcon,String PathIconSelected)
        {
            dataListIcon[0].removeElementAt(index);
            dataListIcon[0].insertElementAt(""+PathIcon,index);
            
            dataListIcon[1].removeElementAt(index);
            dataListIcon[1].insertElementAt(""+PathIconSelected,index);
            
            repaint();
        }
	
        /**
         * Setta i fonts da utilizzare per le voci della lista, se i fonts non sono specificati, verranno utilizzati
         * i fonts di default: <b>("Courier", 1, 10)</b>.
         * @param f_Sel font da utilizzare per le voci della lista selezionati.
         * @param f_noSel font da utilizzare per le voci della lista non selezionati.
         */
        public void set_Font(Font f_Sel,Font f_noSel)
        {
            this.fontSel = f_Sel; 
            this.fontNoSel = f_noSel;         
        }
        
        /**
         * Calcola il numero di elementi all'interno della lista.
         * @return intero che indica il numero di elementi presenti nella lista.
         */
        public int getItemCount()
        {
            try
            {
                return(dataListIcon[0].size());
            }
            catch(Exception e)
            {
                System.out.println("error getItemCount() ");
                return 0;
            }
        }
        
        /**
         *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
         *<p align="center"> <b>Author:</b></p>
         *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
         *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
         * 
         * Questa classe ridefinisce un nuovo cellRenderer da utilizzare per la JListIcon, in particolare stabilische che una singola voce della lista sarà rappresentata
         * da un oggetto di tipo <b>JRadioButton</b>, e quindi avente un immagine di default, un immagine in caso di elemento selezionato, ed un testo.
         *
         */ 
	public class MyCellRenderer extends JRadioButton implements ListCellRenderer 
	{
    	public Component getListCellRendererComponent (JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)
    	{
                                    
    		String s = value.toString();
    		setText(s);
            if (isSelected) 
            {
				setFont(fontSel);    
            	if (selezione)
       			{
					//setBackground(list.getSelectionBackground());
                	setBackground(col);
					if (flagColor)
					{
                    	setForeground((Color)dataListIconColor.elementAt(index));
					}else
					{
						setForeground(list.getSelectionForeground());
						//setForeground(java.awt.Color.red);
					}
				}           
            	try
            	{
                	setIcon(iconPool.getIcon((String)dataListIcon[1].elementAt(index)));
            	}
				catch(Exception err)
            	{
                	setIcon(iconPool.getIcon((String)dataListIcon[0].elementAt(index)));
        		}   
           	}
            else 
            {
            	setBackground(list.getBackground());
            	if (flagColor)
				{
					//////System.out.println("Indice: "+index);
					setForeground((Color)dataListIconColor.elementAt(index));
				}
				else
				{
					setForeground(list.getForeground());
				}
				setIcon(iconPool.getIcon((String)dataListIcon[0].elementAt(index)));
            	setFont(fontNoSel);
        	}	
                    
            if(list.isEnabled() == false)
                setDisabledIcon(null);
                
            Icon icon_appo = iconPool.getIcon((String)dataListIcon[0].elementAt(index));
            if(icon_appo != null)
            {
            	int h_icon = icon_appo.getIconHeight();
            	int gapIconMIN = 2;                 	
                
                this.setMaximumSize(new Dimension(100, (h_icon+gapIconMIN) ));
                this.setPreferredSize(new Dimension(20,(h_icon+gapIconMIN) ));            	
            }   
            validate();
            setEnabled(list.isEnabled());                    
                    
            //setFont(new java.awt.Font ("Helvetica", 0, 10));
    		return this;
		}
	}
}
