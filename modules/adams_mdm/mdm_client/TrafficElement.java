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
 * Questa classe incapsula la struttura dei Traffic Element inviate dal Master Server
 *
 *<p align="center">&nbsp;</p>    
 * @see connCORBA
 * @see DATA_DATAELEMENT
 */
public class TrafficElement
{
	/**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Array di tipo DATA_RELATIONS, contenente la configurazione delle realzioni.
	 * I valori presenti nel vettore dipendono dalla configurazione, passata tramite CORBA dal Master Server.
	 * </font></font></p></pre>  
	 */
	public DATA_DATAELEMENT[] localElement;
	
	public boolean Delete;
	
	/**
         * Tipo operatore su elemento telefonico.
	 */
	public String[] operatorType={ "FieldLink","SimpleCounter","SumCounter","FormulaField","Unmanaged"};
//	public String[] enum={	"TextField","ValueList","OptionList"};
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Costruttore che crea un riferimento locale nella classe alla struttura deli Traffic Element
	 * </font></font></p></pre> 
	 * @param appo array di Traffic Element.
	 */
	public TrafficElement(DATA_DATAELEMENT[] appo)
	{
		this.localElement=appo;
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il numero di Elementi di Traffico caricati.
	 * </font></font></p></pre> 
	 */
	public int num_Traffic_Element()
	{
		return localElement.length;
	}

	/**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce un Elemento di Traffico avent l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public DATA_DATAELEMENT get_Traffic_Element(int idElement)
	
	// RITORNA L'ELEMENTO DI TRAFFICO AVENTE idElement == al'id DEFINITO NEL RELATIVO 
	// CAMPO NELLA STRUTTURA 'RELAZIONI'
	{
		DATA_DATAELEMENT appo=new DATA_DATAELEMENT();
		for(int i=0;i<num_Traffic_Element();i++)
		{
			appo=(DATA_DATAELEMENT)localElement[i];
			if (appo.idElement == idElement)
				break;
		}
		return appo;
	}
	
	/**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce un la dimensione in byte dell'Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public int get_BiteSize_TE(int idElement)
	{
		DATA_DATAELEMENT appo=new DATA_DATAELEMENT();
		for(int i=0;i<num_Traffic_Element();i++)
		{
			appo=(DATA_DATAELEMENT)localElement[i];
			if (appo.idElement == idElement)
                        {
				return appo.LengthInRelation;
                        }
		}
		return -1;
	}
        
        
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce la descrizione breve di un  Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public String get_shortDescription(int idElement)
	{
		return (new String(get_Traffic_Element(idElement).shortDescription).trim());
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce la descrizione completa di un  Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public String get_longDescription(int idElement)
	{
		return (new String(get_Traffic_Element(idElement).longDescription).trim());
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il widget associato all'Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public int get_guiObject(int idElement)
	{
		return (get_Traffic_Element(idElement).guiObject);
	}

        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce l'URL dell icona assoaciata all' Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public String get_pixmapFileName(int idElement)
	{
		return (new String(get_Traffic_Element(idElement).pixmapFileName));
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce il range di valori associati ad un Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */	
	public String get_valueRange(int idElement)
	{
		return (new String(get_Traffic_Element(idElement).valueRange));
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce la lista di valori associati ad un Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public double[] get_valueList(int idElement)
	{
		return (get_Traffic_Element(idElement).valueList);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce valore di dafault associato ad un Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public double get_defaultValue(int idElement)
	{
		return (get_Traffic_Element(idElement).defaultValue);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce un array di valori identificativi per le restrizioni aggregate all'Elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public int[] get_aggregateRestrictions(int idElement)
	{
		return (get_Traffic_Element(idElement).aggregateRestrictions);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce un array di valori identificativi per le eccezioni dell'elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public int[] get_exceptions(int idElement)
	{
		return (get_Traffic_Element(idElement).exceptions);
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Restituisce un array di valori identificativi per i DR Link dell'elemento di Traffico avente l'id specificato
	 * </font></font></p></pre> 
	 * @param idElement id dell'Elemento di Traffico da cercare.
	 */
	public int get_idDRLink(int idElement)
	{
		return (get_Traffic_Element(idElement).idDRLink);
	}
	
	/*public int get_linkType(int idElement)
	{
		return (get_Traffic_Element(idElement).linkType);
	}
	
	public String get_formula(int idElement)
	{
		return (new String(get_Traffic_Element(idElement).formula));
	}
	
	public String get_condition(int idElement)
	{
		return (new String(get_Traffic_Element(idElement).condition));
	}*/
}
