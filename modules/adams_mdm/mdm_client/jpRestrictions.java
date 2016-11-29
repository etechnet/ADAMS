/*
 * jpRestrictions.java
 *
 * Created on 5 marzo 2001, 14.10
 *
 *
 * @author  Raffaele Ficcadenti
 * @version 
 */

import javax.swing.*;
import java.util.*;
import java.awt.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class jpRestrictions extends javax.swing.JPanel {
   
    //public RestrictionList rList; // nello da cancellare
    private MDM_JP_RestrictionList rList; 
    public String[] currentCodesHelp=null;
    public insiemi local_i;
    public DATA_DATAELEMENT appoDataElement;
    public paintTrafficElement[] p;
    public TrafficElement trafficElements;
    public BufferRestrizioni br;

    /*public jpRestrictions(DataElement trafficElements,RestrictionList rList,insiemi local_i,BufferRestrizioni br) 
    {
        this.trafficElements=trafficElements;
        this.rList=rList;
        this.local_i=local_i;
        this.br=br;

        setBorder(new javax.swing.border.TitledBorder(
                    new javax.swing.border.EtchedBorder(), " Restrictions Value ", 4, 2,
                    staticLib.fontA9, java.awt.Color.blue));		

        setLayout(new BorderLayout());

        globalPanel=new JPanel();
        jsp=new JScrollPane();
        jsp.setViewportView(globalPanel);
        jsp.setBorder(null);
        add(jsp,"Center");
		
    }*/
    
    // nello Costruttore aggiunto Luca da valutare e cancellare l'altro
    public jpRestrictions(TrafficElement trafficElements,MDM_JP_RestrictionList rList,insiemi local_i,BufferRestrizioni br) 
    {
        this.trafficElements=trafficElements;
        this.rList=rList;
        this.local_i=local_i;
        this.br=br;

        setLayout(new BorderLayout());

        globalPanel=new JPanel();
        
        jsp=new JScrollPane();
        jsp.setViewportView(globalPanel);
        jsp.setBorder(null);
        add(jsp,"Center");
		
    }
    

    public void clearText()
    {
        //jtInsertCode.setText("");
    }

    public void setIconRestriction(Icon icona)
    {
        //jlSelected.setIcon(icona);
    }

    public void setListHelp(String[] listHelp)
    {
        this.currentCodesHelp=listHelp;
    }

    private void setTextRestriction(String str)
    {
        jlSelected.setText("   "+str);
    }

    public void setDataElement(int idTE)
    {
        this.appoDataElement=trafficElements.get_Traffic_Element(idTE);
    }

    public void validateRest()
    {
        int flag=0;

        paintTrafficElement temp=null;
        //***************************************************


        //System.out.println("****************ID Element: "+appoDataElement.idElement);
        try
        {
            for(int i=0;i<p.length;i++)
            {
                try
                {
                    globalPanel.remove(p[i]);
                }
                catch(Exception e){}			
            }
        }
        catch(Exception e){}

        //***************************************************
        // conto le aggregate restrictions
        //***************************************************
        cont=0;
        while(appoDataElement.aggregateRestrictions[cont]!=0)
        {
                //System.out.println("ID aggregate: "+appoDataElement.aggregateRestrictions[cont]);
                cont++;
        }
        
        //**************************************************	

        int idStart=0;
        staticLib.disegnaApply=true;
        //Traffic Element di default
        if(appoDataElement.guiObject!=0)
        {
            //System.out.println("\nQUAAAAAAAA:  "+cont);
            cont=cont+1;
            //System.out.println("TE: "+appoDataElement.idElement+"   Cont: "+cont);
            p=new paintTrafficElement[cont];
            globalPanel.setLayout(new GridLayout(cont,1));
            p[idStart]=new paintTrafficElement(br,appoDataElement);
            //System.out.println("Dopo paintTrafficElement");	
            if(p[idStart]==null)
            {
                System.out.println("Errore....!!!!");
            }
            p[idStart].setPrimaryElem(appoDataElement);	
            //System.out.println("Dopo setPrimaryElem");		
            p[idStart].setEnv(rList,null,local_i,false);
            //System.out.println("Dopo setEnv");
            globalPanel.add(p[idStart]);
            idStart=idStart+1;
        }
        else
        {
            if(cont>=1)
            {
                p=new paintTrafficElement[cont];
                globalPanel.setLayout(new GridLayout(cont,1));
            }
        }

         // ridimensionamento pannello delle restrizioni.
        if(cont==1) //nello da cancellare
        {
            setBounds(0,0,270,145);
            globalPanel.setPreferredSize(new java.awt.Dimension(270,145));
            globalPanel.setMinimumSize(new java.awt.Dimension(270,145));
            globalPanel.setMaximumSize(new java.awt.Dimension(270,145));
        }
        /*else if(cont<3)			
        {
            setBounds(0,0,270,145*cont);
            globalPanel.setPreferredSize(new java.awt.Dimension(270,145*cont));
            globalPanel.setMinimumSize(new java.awt.Dimension(270,145*cont));
            globalPanel.setMaximumSize(new java.awt.Dimension(270,145*cont));
        }*/
        else		
        {
           
            setBounds(0,0,270,145*2);			
            globalPanel.setPreferredSize(new java.awt.Dimension(270,145*cont));
            globalPanel.setMinimumSize(new java.awt.Dimension(270,145*cont));
            globalPanel.setMaximumSize(new java.awt.Dimension(270,145*cont));
        }

        if(cont>=1)
        {
            //System.out.println("cont="+cont);
            //System.out.println("idStart="+cont);
            // ci sono aggregate restrictions
            staticLib.disegnaApply=false;

            for(int i=idStart;i<cont;i++)
            {

                //if(rList==null)				
                        //System.out.println("   RLIST: "+rList);
                //System.out.println("ID aggregate: "+appoDataElement.aggregateRestrictions[i-idStart]);

                p[i]=new paintTrafficElement(br,trafficElements.get_Traffic_Element(appoDataElement.aggregateRestrictions[i-idStart]));
                p[i].setPrimaryElem(appoDataElement);				
                p[i].setEnv(rList,null,local_i,true);
                globalPanel.add(p[i]);

            }	
        }
        super.validate();
    }

    public void refreshList(int id)
    {
        for(int i=0;i<cont;i++)
        {
            p[i].refreshList(id);
            //p[i].resetPass();
        }
    }

    public void validateList()
    {
        for(int i=0;i<cont;i++)
        {
            p[i].validateList();
            //p[i].resetPass();
        }
    }
		
  private javax.swing.JLabel 		jlSelected;
  private JPanel globalPanel;
  private JScrollPane jsp;
  private int cont=0;
 
}
