import java.applet.*;
import javax.swing.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
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
 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
 * Questa classe provvede al caricamento della configurazione passatagli dal Master Server.
 * La classe estende un Appltet per usufruire dei metodi ereditari dell'Applet
 * </font></font></p></pre>
 * <p align="center">&nbsp;</p> 
 */
public class getConfigFiltro extends Applet
{

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe cha accetta in ingresso le strutture globali della configurazione per farne una copia locale,
     * atte alla configurazione dinamica degli oggetti del client.
     * Le strutture in ingresso sono sterttamente dipendenti dal Master Server esterno.
     * N.B.: La configurazione viene caricata mediante lo starto CORBA sottostante all'intero progetto client.
     * </font></font></p></pre>  
     */
    public STRUCT_USER[] appo2;
        
    //**********************      remote object  ***************************

    private DATA_RELATIONS[] relationTemp;   	
    private DATA_DATAELEMENT[] remoteElement ;    	
    private DATA_ANALYSISTYPE[] analisiTemp;
    private COUNTERS[] counterTemp;
    private DATA_EXCEPTIONS[] exceptionTemp;
    private DATA_REPORTSCHEMA[] reportTemp;
    private DATA_PLUGINREGISTRY[] pluginTemp;

    //private UserSTSSeqHolder userTemp		= new UserSTSSeqHolder();
    //private DescSeqHolder helpTemp		= new DescSeqHolder();
    private CentralSeqHolder centraliTemp       = new CentralSeqHolder();
		
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe cha accetta in ingresso le strutture globali della configurazione per farne una copia locale,
     * atte alla configurazione dinamica degli oggetti del client.
     * Le strutture in ingresso sono sterttamente dipendenti dal Master Server esterno.
     * N.B.: La configurazione viene caricata mediante lo starto CORBA sottostante all'intero progetto client.
     * </font></font></p></pre>  
     *
     */
    public getConfigFiltro(DATA_REPORTSCHEMA[] reportTemp,DATA_RELATIONS[] relationTemp,DATA_DATAELEMENT[] remoteElement,DATA_EXCEPTIONS[] exceptionTemp,DATA_ANALYSISTYPE[] analisiTemp,COUNTERS[]counterTemp,DATA_PLUGINREGISTRY[]pluginTemp,/*DescSeqHolder helpTemp,*/CentralSeqHolder centraliTemp)
    {
        this.reportTemp=reportTemp;
        this.relationTemp=relationTemp;
        this.remoteElement=remoteElement;
        this.centraliTemp=centraliTemp;
        this.analisiTemp=analisiTemp;
        this.counterTemp=counterTemp;
        this.exceptionTemp=exceptionTemp;
        this.pluginTemp=pluginTemp;
    }
        
        
    /*
    public DATA_DESC[] get_descHelp()
    {
            return (DATA_DESC[])helpTemp.value;	
    }
    */

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente gli Elementi di Traffico.		
     * </font></font></p></pre> 
     * @see DATA_DATAELEMENT 
     */
    public DATA_DATAELEMENT[] get_Traffic_Elements()
    {
            return (DATA_DATAELEMENT[])remoteElement;
    }
    
    public DATA_DATAELEMENT getDataElement(int idElement)
    {
    // RITORNA L'ELEMENTO DI TRAFFICO AVENTE idElement == al'id DEFINITO NEL RELATIVO 
    // CAMPO NELLA STRUTTURA 'RELAZIONI'

        DATA_DATAELEMENT appo = new DATA_DATAELEMENT();
        for(int i=0;i<remoteElement.length;i++)
        {
                appo = (DATA_DATAELEMENT)remoteElement[i];
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
    public int get_ByteSize_TE(DATA_DATAELEMENT D_TE)
    {
        //System.out.println("DATA_DATAELEMENT  shortDescription --> "+new String(D_TE.shortDescription).trim());
        //System.out.println("DATA_DATAELEMENT  longDescription --> "+new String(D_TE.longDescription).trim());
        //System.out.println("DATA_DATAELEMENT  Byte Size --> "+D_TE.LengthInRelation);
        return D_TE.LengthInRelation;
    }

    public DATA_PLUGINREGISTRY[] get_Plugins()
    {
            return (DATA_PLUGINREGISTRY[])pluginTemp;
    }
    
    public DATA_PLUGINREGISTRY get_Plugin(int idPlugin)
    {
    	DATA_PLUGINREGISTRY plAppo=null;
    	/*for(int i=0;i<pluginTemp.length;i++)
    	{
		plAppo=(DATA_PLUGINREGISTRY)pluginTemp[i];
		System.out.println(new String(plAppo.pluginName).trim()+" ID="+plAppo.idPlugin+"   idPlugin="+idPlugin);
		if(plAppo.idPlugin==idPlugin) System.out.println("TROVATOOOOOOOOOOOOOOOO");
    	}*/
    	
    	for(int i=0;i<pluginTemp.length;i++)
    	{
    		plAppo=(DATA_PLUGINREGISTRY)pluginTemp[i];
    		if(plAppo.idPlugin==idPlugin)
    		{
    			break;
    		}
    	}
    	
    	return plAppo;
    }
    
    public String get_NamePlugin(int idPlugin)
    {
    	DATA_PLUGINREGISTRY plAppo=null;
    	
    	/*for(int i=0;i<pluginTemp.length;i++)
    	{
		plAppo=(DATA_PLUGINREGISTRY)pluginTemp[i];
		System.out.println(new String(plAppo.pluginName).trim()+" ID="+plAppo.idPlugin+"   idPlugin="+idPlugin);
		if(plAppo.idPlugin==idPlugin) System.out.println("TROVATOOOOOOOOOOOOOOOO");
    	}*/
    	
    	String strPlugin="";
    	for(int i=0;i<pluginTemp.length;i++)
    	{
		plAppo=(DATA_PLUGINREGISTRY)pluginTemp[i];
		if(plAppo.idPlugin==idPlugin) 
		{
			strPlugin=new String(plAppo.pluginName).trim();
			break;
		}
    	}
    	
    	
    	return strPlugin;
    }

    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente gli Elementi di Traffico configurati per la Free Format Relation.		
     * </font></font></p></pre> 
     * @see DATA_DATAELEMENT 
     */
    public DATA_DATAELEMENT[] get_DataElements_FFENABLED()
    {
            Vector V_Data_TE_ffEnabled = new Vector();
            
            for(int i=0; i<remoteElement.length; i++)
            {
                if(remoteElement[i].ffEnabled == true)
                    V_Data_TE_ffEnabled.addElement(remoteElement[i]);
            }
            
            DATA_DATAELEMENT[] Data_TE_ffEnabled = null;
            int SIZE = V_Data_TE_ffEnabled.size();
            
            if(SIZE > 0)
            {
                Data_TE_ffEnabled = new DATA_DATAELEMENT[SIZE];
                for(int i=0; i<SIZE; i++)
                    Data_TE_ffEnabled[i] = (DATA_DATAELEMENT)V_Data_TE_ffEnabled.elementAt(i);
            }
        
            return Data_TE_ffEnabled;
    }
    
    
    
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente tutte caratteristiche rappresentanti la struttura Reports.		
     * </font></font></p></pre> 
     * @see DATA_RELATIONS 
     */
    public DATA_REPORTSCHEMA[] get_ReportsSchema()
    {
            return (DATA_REPORTSCHEMA[])reportTemp;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente tutte le Relazioni.		
     * </font></font></p></pre> 
     * @see DATA_RELATIONS 
     */
    public DATA_RELATIONS[] get_AllRelations()
    {
            return (DATA_RELATIONS[])relationTemp;
    }
    
    public DATA_RELATIONS get_Relations_FreeFormat()
    {
            
        for(int i=0; i<relationTemp.length; i++ )
        {
            if( relationTemp[i].idRelation == staticLib.RELATION_FREEFORMAT_ID )
            {
                //System.out.println("TROVATA ---> idRelation == "+staticLib.RELATION_FREEFORMAT_ID;);
                return (DATA_RELATIONS)relationTemp[i];
            }
        }                
        return null;
    }
    
	
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente le n-Relazioni specificate nel array d'ingresso del metodo.		
     * </font></font></p></pre>  
     * @param relSel array di relazioni da selezionare.
     * @see DATA_RELATIONS
     */
    public DATA_RELATIONS[] get_Relations(int[] relSel)
    {
        int i=0;
        //System.out.print("[ ");
        //////System.out.println("getConfig.get_Relations");

        while(relSel[i]>0)
        {
            //System.out.print(" "+relSel[i]);
            i++;
        }
        //DATA_RELATIONS[] appo=new DATA_RELATIONS[i];
        Vector appoRELAZIONI=new Vector();
        for(int k=0;k<i;k++)
        {
            for(int j=0;j<relationTemp.length;j++)
            {
                //controllo l'ID
                if(relationTemp[j].idRelation==relSel[k])
                {
                    //Relazione trovata
                    appoRELAZIONI.addElement(relationTemp[j]);
                    //appo[k]=relationTemp[j];
                    //System.out.print(" "+relSel[k]);
                }
            }
        }
        DATA_RELATIONS[] appo=new DATA_RELATIONS[appoRELAZIONI.size()];

        for(int h=0;h<appoRELAZIONI.size();h++)
        {
            appo[h]=(DATA_RELATIONS)appoRELAZIONI.elementAt(h);	
        }
        return appo;
    }
    
    private DATA_RELATIONS get_Relation(int idReleation)
    {
        for(int j=0; j<relationTemp.length; j++)
        {
            if(relationTemp[j].idRelation == idReleation)
                return relationTemp[j];
        }
        return null;
    }
    
    public Vector getIDDataElement_Relation(int idReleation)
    {
        Vector v_idTE = new Vector();        
        DATA_RELATIONS dataRelations = get_Relation(idReleation);

        while(true)
        {
            //debug_DATA_RELATIONS(dataRelations);
            if(dataRelations.idParentRelation == 0)
            {                
                v_idTE.addElement(new Integer(dataRelations.idSecondElement));
                v_idTE.addElement(new Integer(dataRelations.idFirstElement));
                break;
            }
            else
            {
                v_idTE.addElement(new Integer(dataRelations.idFirstElement));
                dataRelations = get_Relation(dataRelations.idParentRelation);
            }
        }        
        v_idTE.trimToSize();
        java.util.Collections.reverse(v_idTE);
        //System.out.println("OK reverse TE ="+v_idTE);
        return v_idTE;
    }
    
    public DATA_DATAELEMENT[] getDataElement_Relation(int idReleation)
    {
        Vector v_idTE = getIDDataElement_Relation(idReleation);        
        int size = v_idTE.size();
        DATA_DATAELEMENT[] dateTE = new DATA_DATAELEMENT[size];
        
        for(int i=0; i<size; i++)
            dateTE[i] = getDataElement( ((Integer)v_idTE.elementAt(i)).intValue());
        
        return dateTE;
    }

    
    public void debug_DATA_RELATIONS(DATA_RELATIONS dataRelations)
    {
        System.out.println("----");
        System.out.println("idRelation       = "+dataRelations.idRelation);
        System.out.println("idParentRelation = "+dataRelations.idParentRelation);
        System.out.println("idFirstElement   = "+dataRelations.idFirstElement);
        System.out.println("idSecondElement  = "+dataRelations.idSecondElement);
        System.out.println("admittedDirections = "+dataRelations.admittedDirections);
        System.out.println("admitHexValues     = "+dataRelations.admitHexValues);
        System.out.println("admitNetworkAnalisys = "+dataRelations.admitNetworkAnalisys);
        System.out.println("ghostRelation = "+dataRelations.ghostRelation);
        System.out.println("nextLevelRelations length ==> "+dataRelations.nextLevelRelations.length);

        int j=0;
        while(dataRelations.nextLevelRelations[j]!=0)
        {
            if(j == 0)
                System.out.print("Next Level: ");
            
            System.out.print("  "+dataRelations.nextLevelRelations[j]);
            j++;
        }
        System.out.println("");


        System.out.println("restrictions length ==> "+dataRelations.restrictions.length);
        System.out.println("tiedRestrictions length ==> "+dataRelations.tiedRestrictions.length);
        System.out.println("freeFormat = "+dataRelations.freeFormat);
        System.out.println("----");
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente la lista delle centrali attive.
     * </font></font></p></pre>  
     * @see DATA_CENTRALI
     */
    public DATA_CENTRALI[] get_Centrali()
    {
        return (DATA_CENTRALI[])centraliTemp.value;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente la lista delle eccezzioni ammesse.
     * </font></font></p></pre>  
     * @see DATA_EXCEPTIONS
     */
    public DATA_EXCEPTIONS[] get_Exceptions()
    {
        return (DATA_EXCEPTIONS[])exceptionTemp;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la struttura dati contenente la lista delle Analisi attualmente configurate.
     * </font></font></p></pre>  
     * @see DATA_ANALYSISTYPE
     */
    public DATA_ANALYSISTYPE[] get_TipoAnalisi()
    {            
        return (DATA_ANALYSISTYPE[])analisiTemp;
    }


    public COUNTERS[] get_Counters()
    {
        return (COUNTERS[])counterTemp;
    }
		  
}


