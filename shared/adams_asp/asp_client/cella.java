import java.util.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: cella </b> </p>
 *
 * Classe utilizza per rappresentare una singola cella delle tabelle che rappresentano gli alarm element.
 * Ogni cella Ã¨ individuata dalla coppia (riga,colonna).Ad ogni cella Ã¨ associato un vettore di indici, ogni
 * indice identifica il tipo di allarme da rappresentare su quella cella.
 * <p align="center">&nbsp;</p> 
 * @see JTable_View    
 */
public class cella
{
	private int riga;
	private int colonna;
	private Vector indici;
	
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Costruttore della classe: istanzia una cella alla posizione riga=r, colonna=c;
	 * @param r riga che individua la posizione della cella,
	 * @param c colonna che individua la posizione della cella.
	 * </font></font></p> 	
         */
	public cella(int r,int c)
	{
			this.indici=new Vector();
			this.riga=r;
			this.colonna=c;
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Calcola il numero di indici presenti nella cella corrente.
	 * @return numero indici.
	 * </font></font></p> 	
         */
	public int numIndici()
	{
		return indici.size();
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Controlla se la cella alla posizione (r,c) Ã¨ presente.
	 * @param r riga che individua la posizione della cella,
	 * @param c colonna che individua la posizione della cella.
	 * @return <b>true</b> se la cella Ã¨ stata trovata, <b>false</b> altrimenti.
	 * </font></font></p> 	
         */
	public boolean isPresent(int r,int c)
	{
		if((r==riga)&&(c==colonna))
			return true;
		else
			return false;	
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Controlla se nella cella Ã¨ presente l'indice specificato
	 * @param id indice da controllare.
	 * @return <b>true</b> in caso affermativo, <b>false</b> altrimenti.
	 * </font></font></p> 	
         */
	public boolean isPresent(int id)
	{
		for(int i=0;i<indici.size();i++)
		{
			int appo=((Integer)indici.elementAt(i)).intValue();
			if(appo==id)
				return true;
		}
		return false;
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Inserisce l'indice specificato all'interno della cella.
	 * @param val indice da inserire.
	 * </font></font></p> 	
         */
	 
	public void put(int val)
	{
		if (!isPresent(val))
		{
			indici.addElement(new Integer(val));
		}else
			System.out.println("Il valore <"+val+"> Ã¨ gia presente.");
	}
	
	/**
     	 * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	 * Ritorna il vettore degli indici associati all cella corrente.
	 * @return vettore di indici.
	 * </font></font></p> 	
         */
	public Vector get()
	{
		return this.indici;
	}
	
	
	
}
