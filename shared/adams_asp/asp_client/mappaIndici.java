import java.util.*;

import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: mappaIndici </b> </p>
 *
 * Questa classe estende un vettore ed Ã¨ impiegata come struttura per la gestione della tabella degli allarmi "Analysis Table" 
 * classe Monitor_TAB.
 * La classe utilizza  ALARM_INFO classe generata dall'OrbixWeb IDL compiler, struttura specifica per la rappresentazione dell'allarme.
 *<p align="center">&nbsp;</p>  
 *@see Monitor_TAB   
 */
public class mappaIndici extends Vector
{
	private ALARM_INFO[] ai;
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 *  Costruttore di default della classe. 
	 * </font></font></p> 	
         */
	public mappaIndici()
	{
		super();
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Metodo che permette alla classe di ricevere il riferimento ad una struttura di tipo ALARM_INFO[] (informazioni allarmi).
	 *@param ai Array di ALARM_INFO classe generata dall'OrbixWeb IDL compiler. 
	 * </font></font></p> 	
         */
	public void setEnv(ALARM_INFO[] ai)
	{
		this.ai=ai;
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Questo metodo verifica se una determinata cella Ã¨ presente nella tabella, nella specifiche coordinate r=riga e c=colonna. 
	 *@param r valore identificativo della riga della tabella.
	 *@param c valore identificativo della colonna della tabella.
	 *@return Uno specifico oggetto di tipo cella presente nella tabella, nella specifiche coordinate r=riga e c=colonna; altrimenti ritorna null.
	 *</font></font></p> 
	 *@see cella
         */
	public cella isPresent(int r,int c)
	{
		for(int i=0;i<size();i++)
		{
			cella appo=(cella)elementAt(i);
			if(appo.isPresent(r,c))
				return appo;
		}
		return null;
	}
	
	/**
     	 *<p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Questo metodo date specifiche coordinate r=riga e c=colonna ritorna l'indice identificativo dell'allarme presente.
	 *@param r valore identificativo della riga della tabella.
	 *@param c valore identificativo della colonna della tabella. 
	 *@return Indice identificativo dell'allarme presente.
	 *</font></font></p> 
         */
	public int numIndici(int r,int c)
	{
		cella appo=isPresent(r,c);
		if (appo!=null) // Ã¨ presente
		{
			return appo.numIndici();
		}else // non Ã¨ presente
		{
			return -1;
		}
	}
	
	/**
     	 *<p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Questo metodo permette di inserire un valore ad una determinata cella alle coordinate r=riga e c=colonna se esiste, altrimenti viene 
	 * creato un oggetto di tipo cella alle specifiche posizioni e ne setta il valore.
	 *@param r valore identificativo della riga della tabella.
	 *@param c valore identificativo della colonna della tabella.
	 *@param val valore della cella.
	 *</font></font></p> 
         */
	public void put(int r,int c,int val)
	{
		cella appo=isPresent(r,c);
		if (appo!=null) // Ã¨ presente
		{
			appo.put(val);
		}else // non Ã¨ presente
		{
			appo=new cella(r,c);
			appo.put(val);
			addElement(appo);
		}
	}
	
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Ritorna il vettore degli indici associati alla cella specifica alle coordinate r=riga e c=colonna se esiste, altrimenti ritorna null.
	 * @return vettore di indici.
	 * </font></font></p> 	
         */
	public Vector get(int r,int c)
	{
		cella appo=isPresent(r,c);
		if (appo!=null) // Ã¨ presente
		{
			return appo.get();
		}else // non Ã¨ presente
		{
			return null;
		}
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Ritorna la struttura array ALARM_INFO associata alla cella specifica alle coordinate r=riga e c=colonna se esiste, altrimenti ritorna null.
	 * @return ALARM_INFO classe generata dall'OrbixWeb IDL compiler, struttura specifica per la rappresentazione dell'allarme.
	 * </font></font></p> 	
         */
	public ALARM_INFO[] getAI(int r,int c)
	{
		Vector v=get(r,c);
		if(v!=null)
		{
			int len=v.size();
		
			if (len>0)
			{
				ALARM_INFO[] appoAI=new ALARM_INFO[len];
				
				for(int i=0;i<len;i++)
				{
					int pos=((Integer)v.elementAt(i)).intValue();
					appoAI[i]=ai[pos];
				}
				//System.out.println("Ritorno appoAI");
				return appoAI;
			}
			
		}
		//System.out.println("Ritorno NULL");
		return null;
		
	}
	
	
}
