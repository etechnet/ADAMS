
    

/*
 * JListIcon.java
 *
 * Created on 1 febbraio 2001, 15.32
 */


/**
 *
 * @author  Beltrame Luca, Ficcadenti Raffaele
 * @version 
 */

//package ntmlib;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class JComboIcon extends JComboBox 
{

    final static int icon = 0;
    final static int iconSelected = 1;
    final static int text = 2;

    private Vector[] dataListIcon=new Vector[3];
    private Vector dataListIconColor=new Vector();
	
    private Color col =new Color(255,255,255);
    private ImageIcon imgIcon;
    private ImageIcon imgIconSelected;
    private IconPool iconPool;
    private boolean selezione=true;
    private boolean flagColor=false;
	
    public void setFlagColor(boolean f)
    {
            this.flagColor=f;
    }
	
    public JComboIcon(IconPool iconPool)
    {
        super();
        //super((Vector)null);
        for(int i=0;i<3;i++)
            dataListIcon[i]=new Vector();
        this.iconPool=iconPool;
        this.setBackground(new Color(230,230,230));
    }
        
    public JComboIcon(String[] icon, String[] iconSelected,String[] text)
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
        setRenderer(new TestCellRenderer());
        this.setBackground(new Color(230,230,230));
    }

    public void setSelectionColor(Color c)
    {
        this.col=c;
    }
    
    public String[] getStringValues()
    {
        /*Object[] items=getSelectedValues();
        String[] app;
        
        if(items==null)
               return null;
        app=new String[items.length];
        
        for(int i=0;i<items.length;i++) 
            app[i]=items[i].toString();
        
        return app;*/
        return null;
    }
    
    public void addElement(String PathIcon , String PathIconSelected , String AssociateText)
    {
        dataListIcon[0].addElement(PathIcon);
        dataListIcon[1].addElement(PathIconSelected);
        dataListIcon[2].addElement(AssociateText); 
        
        //System.out.println(AssociateText);
        addItem(AssociateText);
        //setRenderer(new TestCellRenderer());
        //setListData(dataListIcon[2]);
    }
	
    public void addElement(String PathIcon , String PathIconSelected , String AssociateText,Color c)
    {
        dataListIcon[0].addElement(PathIcon);
        dataListIcon[1].addElement(PathIconSelected);
        dataListIcon[2].addElement(AssociateText); 
	dataListIconColor.addElement(c);
        
        //setListData(dataListIcon[2]);
    }
    
    public void convalida()
    {
    	removeAllItems();
    	for (int x=0;x<dataListIcon[2].size();x++)
        addItem(dataListIcon[2].elementAt(x));
        setRenderer(new TestCellRenderer());
    }
    
        
        public void removeAll()
        {
            dataListIcon[0].removeAllElements();    
            dataListIcon[1].removeAllElements();
            dataListIcon[2].removeAllElements();
        }
        
        public void removeAt(int i)
        {
            dataListIcon[0].removeElementAt(i);    
            dataListIcon[1].removeElementAt(i);
            dataListIcon[2].removeElementAt(i);
        }
        
        public void setSelection(boolean set)
        {
            selezione=set;
        }

        public void changeColorAt(String PathIcon , String PathIconSelected, int id)
        {
            dataListIcon[0].setElementAt(PathIcon,id);
            dataListIcon[1].setElementAt(PathIconSelected,id);	
            convalida();
        }

        public Icon getIconAt(int index)
        {
            return iconPool.getIcon((String)dataListIcon[0].elementAt(index));
        }

	public void reset(String PathIcon , String PathIconSelected)
	{
            int len=dataListIcon[0].size();
            dataListIcon[0].removeAllElements();
            dataListIcon[1].removeAllElements();
            for(int i=0;i<len;i++)
            {
                dataListIcon[0].addElement(PathIcon);
                dataListIcon[1].addElement(PathIconSelected);
                //System.out.println(PathIcon);
            }
            convalida();
	}
		
    class TestCellRenderer extends JLabel implements ListCellRenderer    
    {
        public Component getListCellRendererComponent (JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)
        {
            String s="";
            try
            {
             s = value.toString();
            }
            catch(Exception e){}
            if (index==-1)
            {
                setText(s);
                try
                {
                    setIcon(iconPool.getIcon((String)dataListIcon[1].elementAt(0)));
						//setIcon(iconPool.getIcon("b_green.gif"));
                }
                catch(Exception err)
                {
                    setIcon(iconPool.getIcon((String)dataListIcon[0].elementAt(0)));
                }
                return this;
            }
            else
            {
                setText(s);
                /*try
                {
                    setIcon(iconPool.getIcon((String)dataListIcon[1].elementAt(index)));
                }catch(Exception err)
                {
                    setIcon(iconPool.getIcon((String)dataListIcon[0].elementAt(index)));
                }*/
            }

            if (isSelected) 
            {
                setFont(new java.awt.Font ("Verdana", 1, 10));    
                if (selezione)
                {
                    //System.out.println("Value:"+s+"Index selected: "+index);
                    setBackground(col);
                    if (flagColor)
                    {
                        try
                        {
                            setForeground((Color)dataListIconColor.elementAt(index));
                        }catch(Exception e)
                        {
                            //System.out.println("Catch 1");
                        }
                    }
                    else
                        setForeground(list.getSelectionForeground());
                }           
                try
               	{
                    setIcon(iconPool.getIcon((String)dataListIcon[1].elementAt(index)));
               	}catch(Exception err)
               	{
                    setIcon(iconPool.getIcon((String)dataListIcon[0].elementAt(index)));
               	}   
            }
            else 
            {
                setBackground(list.getBackground());
                if (flagColor)
		{
                    //System.out.println("Indice non selected: "+index);
                    setForeground((Color)dataListIconColor.elementAt(index));
		}
		else
                    setForeground(list.getForeground());
                
                setIcon(iconPool.getIcon((String)dataListIcon[0].elementAt(index)));
                setFont(new java.awt.Font ("Verdana", 0, 10));
            }
            setEnabled(list.isEnabled());
                        
            return this;
        }
    }

}
