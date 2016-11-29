import java.util.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe incapsula la struttura delle Relazioni inviate dal Master Server
 *
 *<p align="center">&nbsp;</p>
 * @see connCORBA
 * @see DATA_RELATIONS
 */
public class Relation
{
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Array di tipo DATA_RELATIONS, contenente la configurazione delle realzioni.
	 * I valori presenti nel vettore dipendono dalla configurazione, passata tramite CORBA dal Master Server.
	 * </font></font></p></pre>
	 */
	private String[] tagDirections={
		"Direct",
		"Inverse",
		"Both"
	};

	//private Vector v_Relation;
	public DATA_RELATIONS[] localRelationInt;
	private boolean Delete;

        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Costruttore che crea un riferimento locale nella classe alla struttura delle Relazioni
	 * </font></font></p></pre>
	 * @param appo array di Relazioni.
	 */
	public Relation(DATA_RELATIONS[] appo)
	{
		this.localRelationInt=appo;
	}

	protected void finalize(){}


	// AGGIUNGE DATI RELAZIONE PER RELAZIONE PROVENIENTI DA ESTERNO per ora la cosa è temporanea
	/*
	public void add_Relation(DATA_Relation_local item)
	{
		v_Relation.addElement((DATA_Relation_local)item);

	}
	*/
        
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il numero di Relazioni caricate. Il numero delle Relazioni varia al variare della configurazione.
	 * </font></font></p></pre> 
	 */
	public int num_Relation()
	{
		return localRelationInt.length;
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce una relazione avente l'id delezionato.
	 * </font></font></p></pre> 
	 * @param idRelation identificativo della relazione
	 */
	public DATA_RELATIONS get_Relation_conId(int idRelation)
	{
		DATA_RELATIONS appo=new DATA_RELATIONS();
		for(int i=0;i<num_Relation();i++)
		{
			appo=(DATA_RELATIONS)localRelationInt[i];
			if (appo.idRelation == idRelation)
				return appo;
		}
		return null;
	}

        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce una relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 */
	public DATA_RELATIONS get_Relation(int pos)
	{
		return (DATA_RELATIONS)localRelationInt[pos];
	}	 
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce l'id della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int get_idRelation(int pos)
	{
		return(get_Relation(pos).idRelation);
	}	
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo ParentRelation della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int get_idParentRelation(int pos)
	{
		return(get_Relation(pos).idParentRelation);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo FirstElement della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre>
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int get_idFirstElement(int pos)
	{
		return(get_Relation(pos).idFirstElement);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo SecondElement della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int get_idSecondElement(int pos)
	{
		return(get_Relation(pos).idSecondElement);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo admittedDirections della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int admittedDirections(int pos)
	{
		return(get_Relation(pos).admittedDirections);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo ghostRelation della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre>
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public boolean get_ghostRelation(int pos)
	{
		return(get_Relation(pos).ghostRelation);	
	}

	public boolean get_admitNetworkAnalisys(int pos)
	{
		return(get_Relation(pos).admitNetworkAnalisys);	
	}

        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo nextLevelRelations della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int[] get_nextLevelRelations(int pos)
	{
		return(get_Relation(pos).nextLevelRelations);	
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il valore del campo restrictions della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */	
	public int[] get_restrictions(int pos)
	{
		return(get_Relation(pos).restrictions);	
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Controlla se l'analisi caricata è una Analisi fantasma.
	 * Il metodo restituisce <b>true</b> se l'analisi è fantasma, altrimenti restituisce <b>false</b>.
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public boolean isGhost()
	{
		boolean risp=true;
		try
		{
			//////System.out.println("Num rel: "+num_Relation());
			for(int i=0;i<num_Relation();i++)
			{
				//////System.out.println("GHOST: "+localRelationInt[i].ghostRelation);
				if(!localRelationInt[i].ghostRelation)
				{
					risp=false;
					break;
				}
			}
			return risp;
		}catch(Exception ex)
		{
			System.out.println("Errore in: <isGhost>="+ex);
			return false;
		}
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce indice del campo ghostRelation della relazione nella posizione specificata all'interno del l'array delle relazioni..
	 * </font></font></p></pre> 
	 * @param pos posizione dell'array.
	 * @see DATA_RELATIONS
	 */
	public int idGhostRelation()
	{
		int  risp=-1;
		try
		{
			for(int i=0;i<num_Relation();i++)
			{
				if(!localRelationInt[i].ghostRelation)
				{
					risp=get_idRelation(i);
					break;
				}
			}
			return risp;
		}catch(Exception ex)
		{
			return -1;
		}
	}
	
        public int idAdmittedNetwork()
	{
		int  risp=-1;
		try
		{
			for(int i=0;i<num_Relation();i++)
			{
				if(localRelationInt[i].admitNetworkAnalisys)
				{
					risp=get_idRelation(i);
					break;
				}
			}
			return risp;
		}catch(Exception ex)
		{
			return -1;
		}
	}
}
