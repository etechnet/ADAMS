import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.image.*;



/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_JListIcon </b> </p>
 *
 * Questa classe estende la classe JList, e crea una lista personalizzata sulla base dell'oggetto JList.
 * Nell'oggetto conf_JListIcon Ã¨ possibile inserire delle voci con associate delle immagini.
 * La classe eredita tutte le proprietÃ  dell'oggetto JList.
 * Ogni elemento della lista Ã¨ un oggetto di tipo: <b>JRadioButton</b>
 *
 */
public class conf_JListIcon extends JList 
{
    /**
      * Costante intera: indica che la prima riga dell'array <b>dataListIcon</b> contiene le icone di default
      */    
    final static int icon=0;
    
    /**
      * Costante intera: indica che la seconda riga dell'array <b>dataListIcon</b> contiene le icone nel caso di voci selezionate.
      */
    final static int iconSelected=1;
    
    /**
      * Costante intera: indica che la terza riga dell'array <b>dataListIcon</b> contiene il testo di ogni singola voce della lista. 
      */
    final static int text=2;

    private Vector[] dataListIcon	= new Vector[3];
   
    private Color col  				= new Color(204,204,255);
    private Color colF 				= Color.black;
    private ImageIcon imgIcon;
    private ImageIcon imgIconSelected;
    private conf_IconPool iconPool;
    private boolean selezione                   = true;
    private boolean lineeMulticolor             = false;
    public Color colorA				= new Color(230,230,230);
    public Color colorB				= new Color(230,230,230);
    private Font font			 	= new java.awt.Font("Helvetica", Font.PLAIN, 10);
    
    /**
      * Costruttore della lista: crea un vettore vuoto contenente le varie voci della lista, ed imposta
      * il cellRenderer della JList con il cellRenderer ridefinito all'interno della classe conf_JListIconconf_JListIcon.
      * @param iconPool oggetto di tipo <b>conf_IconPool</b>, riferimento ad un Pool di icone.
      * @see conf_IconPool	     
      */
    public conf_JListIcon(conf_IconPool iconPool)
    {
    	super();
	//super((Vector)null);
	for(int i=0;i<3;i++)
            dataListIcon[i]=new Vector();
	setCellRenderer(new MyCellRenderer());
	this.iconPool=iconPool;
    }
    
    /**
      * Costruttore della lista: crea la lista impostando ogni singola voce, ed imposta
      * il cellRenderer della JList con il cellRenderer ridefinito all'interno della classe conf_JListIcon.
      * @param icon array di stringe contenente i nomi delle icone di default di ogni singola voce della lista,
      * @param iconSelected array di stringe contenente i nomi delle icone di ogni singola voce della lista, quando ques'ultime vengono selezionate,
      * @param text array di stringe contenente il testo di ogni singola voce della lista.
      * @see conf_IconPool	     
      */
    public conf_JListIcon(String[] icon, String[] iconSelected, String[] text)
    {	
 	super(text);
	for(int i=0;i<3;i++)
            dataListIcon[i]=new Vector();
	
	    for(int i=0;i<icon.length;i++)
	    {
                try
                {
                    imgIcon = new ImageIcon(getClass().getResource(icon[i]));
                    dataListIcon[0].addElement(imgIcon);
     	 	
                    imgIconSelected = new ImageIcon(getClass().getResource(iconSelected[i]));
                    dataListIcon[1].addElement(imgIconSelected);
     		 	
                    dataListIcon[2].addElement(text[i].trim());
                }
                catch(Exception e)
                {
                    System.out.println("conf_JListIcon "+e);
                }
     	    }
           
	    setCellRenderer(new MyCellRenderer());
    }
    
    /**
      * Imposta il colore di background da utilizzare quando viene selezionata una voce dalla lista.
      * @param c colore di background da utilizzare per caratterizzare una voce della lista selezionata.	     
      */
    public void setSelectionBackColor(Color c)
    {
        this.col=c;
    }
    /**
      * Imposta il colore di foreground da utilizzare quando viene selezionata una voce dalla lista.
      * @param c colore di foreground da utilizzare per caratterizzare una voce della lista selezionata.	     
      */
    public void setSelectionForeColor(Color c)
    {
        this.colF=c;
    }
    /**
     * Imposta il colore di background della lista in due colori alternati.
     * La rappresentazione grafica della lista, risulterÃ  evidenziata da colori due colori di background 
     * alternati sugli items presenti (lettura modulistica). 
     *@param flag se true rende attiva la funzionalitÃ 
     *@param c1 colore da altrenare con c2. 
     *@param c2 colore da altrenare con c1.   
     */
    public void setLineeMulticolor(boolean flag,Color c1,Color c2)
    {
	lineeMulticolor=flag;
	colorA=c1;
	colorB=c2;
    }
	
    
    /**
      * Restituisce un array di stringhe contenente tutte le voci della lista.
      * @return rray di stringhe contenente tutte le voci della lista.
      * @see conf_IconPool	     
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
      * Ritorna il testo associato all'elemento della lista nella posizione specificata.
      * @param ID indice della voce all'interno della lista.
      * @return Stringa associata all'elemento della lista in questione.
      */
    public String get_ID_Value(int ID)
    {
        try
        {
            return( (dataListIcon[2].elementAt(ID)).toString()); 
        }
        catch(Exception e)
        {
            //System.out.println("get_ID_Value() "+e);
            return "";
        }
    }
    
    /**
      * Ritorna il testo associato a tutti gli elementi della lista.
      * @return Array di String degl'elementi della lista.
      */
    public String[] getStringAllItems()
    {
        //System.out.println("getStringAllItems -");
        String[] strItems = new String[dataListIcon[2].size()];
        for(int i=0; i<strItems.length; i++)
        {
            try
            {
                strItems[i] = (String)dataListIcon[2].elementAt(i);
            }
            catch (Exception e)
            {
                System.out.println("getStringAllItems() "+e);
            }     
        }
        return strItems;
    }
    
    /**
     *Controlla se l'item Ã¨ presente nella lista.
     *@return True se l'elemento Ã¨ presente.
     */
    public boolean ItemPresent(String str)
    {
       boolean flag = false;
       for(int i=0; i<dataListIcon[2].size(); i++)
       {
           String StrItem ="";
           try
           {
                StrItem = (String)dataListIcon[2].elementAt(i);
           }
           catch (Exception e)
           {
                System.out.println("ItemPresent() "+e);
           }

           if(StrItem.compareTo(str)==0)
           {
                flag = true;
                break;
           }
       }
       return flag;
    }

    /**
      * Aggiunge un elemento alla lista.
      * @param PathIcon stringa che identifica l'icona di default associata all'elemento da inserire nella lista.
      * @param PathIconSelected stringa che identifica l'icona (in caso di elemento selezionato) associata all'elemento da inserire nella lista,
      * @param AssociateText testo associato all'elemento da inserire nella lista.
      */
    public synchronized void addElement(String PathIcon , String PathIconSelected , String AssociateText)
    {
        try
        {
            dataListIcon[0].addElement(PathIcon);
            dataListIcon[1].addElement(PathIconSelected);
            dataListIcon[2].addElement(AssociateText); 
            setListData(dataListIcon[2]);
        }
        catch(Exception e)
        {
            System.out.println("addElement "+e);
        }
    }
    
    public synchronized void addElement(String PathIcon , String PathIconSelected , String AssociateText,int pos)
    {
        try
        {
            dataListIcon[0].add(pos,PathIcon);
            dataListIcon[1].add(pos,PathIconSelected);
            dataListIcon[2].add(pos,AssociateText); 
            setListData(dataListIcon[2]);
        }
        catch(Exception e)
        {
            System.out.println("addElement+ "+e);
        }
            
    }

    public synchronized void addElementWithBulb(int typebulb,Color colBulb,Color colBulbSelected,String AssociateText)
    {
        ImageIcon icona;
        ImageIcon iconaSel;
       
        Image img = createBulbs(typebulb,colBulb);
        icona = new ImageIcon(img);
        if(colBulb != colBulbSelected)
        {
            Image imgSel = createBulbs(typebulb,colBulbSelected);
            iconaSel = new ImageIcon(imgSel);
        }
        else
        {
            iconaSel = icona;
        }
        
        try
        {
            dataListIcon[0].add(icona);
            dataListIcon[1].add(iconaSel);
            dataListIcon[2].add(AssociateText); 
            setListData(dataListIcon[2]);
        }
        catch(Exception e)
        {
            System.out.println("addElementWithBulb() "+e);
        }
    }
    
    public void setColorBulb(int typebulb,Color colBulb,Color colBulbSelected,int position)
    {
        ImageIcon icona;
        ImageIcon iconaSel;
        Image img = createBulbs(typebulb,colBulb);
        icona = new ImageIcon(img);
        
        if(colBulb != colBulbSelected)
        {
            Image imgSel = createBulbs(typebulb,colBulbSelected);
            iconaSel = new ImageIcon(imgSel);
        }
        else
        {
            iconaSel = icona;
        }
        
        try
        {
            dataListIcon[0].remove(position);
            dataListIcon[1].remove(position);
            dataListIcon[0].add(position,icona);
            dataListIcon[1].add(position,iconaSel);
            setListData(dataListIcon[2]);
        }
        catch(Exception e)
        {
            System.out.println("setColorBulb() "+e);
        }
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
            System.out.println("getItemCount() "+e);
            return 0;
        }
    }
    
    
    public int[] getAllIndexs()
    {
        int[] OBJ = new int[getItemCount()];
        for(int i=0; i<OBJ.length; i++)
        {
            OBJ[i] = i;
        }
        return OBJ;
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
            setListData(dataListIcon[2]);
        }
        catch (Exception e)
        {
            System.out.println("remElement() "+e); 
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
            setListData(dataListIcon[2]);
        }
        catch (Exception e)
        {
            System.out.println("removeAll() "+e); 
        } 
    }
	
    /**
     * Indica se gli elementi della lista possono essere selezionati o meno.
     * @param set   un valore 'true' indica che gli elementi della lista possono essere selezionati,
     *  	    un valore 'false' indica che gli elementi della lista non possono essere selezionati.
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
        try
        {
            Object ObjArchive = dataListIcon[0].elementAt(index).getClass().getName();
            Object ObjIcon = new ImageIcon().getClass().getName();

            if(ObjArchive != ObjIcon)
            {   
                return iconPool.getIcon((String)dataListIcon[0].elementAt(index));
            }
            else
            {
                return (ImageIcon)dataListIcon[0].elementAt(index);
            }
        }
        catch(Exception e)
        {
            System.out.println("getIconAt() "+ e);
            return null;
        }
    }
    
    /**
     * Ritorna il nome dell'icona di default associata all'elemento della lista specificato.
     * @param index indice della voce all'interno della lista.
     */
    public String getStringIconAt(int index)
    {
        try
        {
            return (String)(dataListIcon[0].elementAt(index));
        }
        catch (Exception e)
        {
            System.out.println("getStringIconAt() " +e);
            return "";
        }
    }

    /**
     * Setta il font da utilizzare per le voci della lista, se il fon non viene specificato, verrÃ  utilizzato
     * il font di default: <b>("Courier", 1, 10)</b>.
     * @param F font da utilizzare per le voci della lista.
     */
    public void set_Font(Font F)
    {
    	font = F;
    }
      
    
    private Image createBulbs(int type ,Color col)
    {
       int[] pixels=null;
       switch (type)
       {

               case 0: // extra small
               {
        	       pixels=new int[1];
        	       int[] bulbBits={1};

        	       int[] bulbCLUT={0x00000000,col.getRGB(), 0xffffffff};
        	       for(int i=0;i<1;i++)
        		       pixels[i]=bulbCLUT[bulbBits[i%bulbBits.length]];
        	       return createImage(new MemoryImageSource(1,1,pixels,0,1));
               }
               case 1: // small
               {
        	       pixels=new int[3*3];
        	       int[] bulbBits={0,1,0,
        					       1,1,1,
        					       0,1,0};

        	       int[] bulbCLUT={0x00000000,col.getRGB(), 0xffffffff};
        	       for(int i=0;i<3*3;i++)
        		       pixels[i]=bulbCLUT[bulbBits[i%bulbBits.length]];
        	       return createImage(new MemoryImageSource(3,3,pixels,0,3));
               }

               case 2: // medium
               {
        	       pixels=new int[4*4];
        	       int[] bulbBits={0,1,1,0,
        					       1,2,1,1,
        					       1,1,1,1,
        					       0,1,1,0,};

        	       int[] bulbCLUT={0x00000000,col.getRGB(), 0xffffffff};
        	       for(int i=0;i<4*4;i++)
        		       pixels[i]=bulbCLUT[bulbBits[i%bulbBits.length]];
        	       return createImage(new MemoryImageSource(4,4,pixels,0,4));
               }

               case 3: // large
               {
        	       pixels =new int[6*6];
        	       int[] bulbBits={0,0,1,1,0,0,
        					       0,1,2,1,1,0,
        					       1,2,1,1,1,1,
        					       1,1,1,1,1,1,
        					       0,1,1,1,1,0,
        					       0,0,1,1,0,0};

        	       int[] bulbCLUT={0x00000000,col.getRGB(), 0xffffffff};
        	       for(int i=0;i<6*6;i++)
        		       pixels[i]=bulbCLUT[bulbBits[i%bulbBits.length]];
        	       return createImage(new MemoryImageSource(6,6,pixels,0,6));
               }
 
               case 4 : // extra large 
               {
        	       pixels=new int[8*8];
        	       int[] bulbBits={0,0,1,1,1,1,0,0,
        					       0,1,2,1,1,1,1,0,
        					       1,2,1,1,1,1,1,1,
        					       1,1,1,1,1,1,1,1,
        					       1,1,1,1,1,1,1,1,
        					       1,1,1,1,1,1,1,1,
        					       0,1,1,1,1,1,1,0,
        					       0,0,1,1,1,1,0,0};

        	       int[] bulbCLUT={0x00000000,col.getRGB(), 0xffffffff};
        	       for(int i=0;i<8*8;i++)
        		       pixels[i]=bulbCLUT[bulbBits[i%bulbBits.length]];
        	       return createImage(new MemoryImageSource(8,8,pixels,0,8));
               }
       }
       return null;
}
        
/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> MONITOR CLIENT</font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca & Raffaele Ficcadenti (HP Invent) Created on 27/06/2003 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 * Questa classe ridefinisce un nuovo cellRenderer da utilizzare per la conf_JListIcon, in particolare stabilische che una singola voce della lista sarÃ  rappresentata 
 * da un oggetto di tipo <b>JRadioButton</b>, e quindi avente un immagine di default, un immagine in caso di elemento selezionato, ed un testo.
 *    
 */        
    public class MyCellRenderer extends JRadioButton implements ListCellRenderer 
    {
     	public Component getListCellRendererComponent (JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)
     	{           
            String s = value.toString();
            setText(s);
            setFont(font); // messo dopo luca
            
            Object ObjArchive;
            try
            {
                ObjArchive = dataListIcon[0].elementAt(index).getClass().getName();
            }
            catch (Exception e)
            {
                System.out.println("getListCellRendererComponent() "+e);
                return null;
            }
            Object ObjIcon = new ImageIcon().getClass().getName();
            Icon icona = null;
            try
            {
                if(ObjArchive != ObjIcon)
                {                    
                    icona = iconPool.getIcon((String)dataListIcon[0].elementAt(index)); 
                }
                else
                {
                    icona = (ImageIcon)dataListIcon[0].elementAt(index);
                }
            }
            catch(Exception e)
            {
                System.out.println("getListCellRendererComponent "+e);
            }

            if (isSelected) 
            {
                // setFont(new java.awt.Font ("Helvetica", 1, 11));	
                if (selezione)
				{
		                   // setBackground(list.getSelectionBackground());
		                    setBackground(col);
		                   // setForeground(list.getSelectionForeground());
		                    setForeground(colF);
				}		
                try
                {                    
                    Object ObjArchiveS = dataListIcon[1].elementAt(index).getClass().getName();
                    Object ObjIconS = new ImageIcon().getClass().getName();
                    Icon iconaS;
                    if(ObjArchiveS != ObjIconS)
                    {    
                        iconaS = iconPool.getIcon((String)dataListIcon[1].elementAt(index));
                    }
                    else
                    {
                        iconaS = (ImageIcon)dataListIcon[1].elementAt(index);
                    }
                    setIcon(iconaS);
                }
                catch(Exception err)
                {
                    System.out.println("err "+err);
                    setIcon(icona);
                }	
            }
            else 
            {
                if(lineeMulticolor)
                {
                    int i=index%2;
                    if (i==0)
                            setBackground(colorA);
                    else
                            setBackground(colorB);
                }
                else
                {
                    setBackground(list.getBackground());
                }
                setForeground(list.getForeground());
                setIcon(icona);
                //setFont(new java.awt.Font ("Helvetica", 0, 10));
            } 
         
         
            int h_icon = icona.getIconHeight();
            if(h_icon < 18)
                h_icon = 18;
            
            int h_gap = 2;
            setPreferredSize(new java.awt.Dimension(20,h_icon+h_gap));
            setMinimumSize(new java.awt.Dimension(20,h_icon+h_gap));   
            validate();         
            setEnabled(list.isEnabled());
             //setFont(new java.awt.Font ("Helvetica", 0, 10));
            return this;           
        }
    }
 }