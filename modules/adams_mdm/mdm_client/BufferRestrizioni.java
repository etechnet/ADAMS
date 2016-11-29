import java.util.*;
import javax.swing.tree.*;
import java.awt.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.*;
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
 * Questa classe definisce una struttura dati di lavoro per l'incapsulamento delle restrizioni selezionate
 * dall'utente, inoltre permette la formattazione della richiesta di elaborazione da inviare al Master Server,
 * è provvede alla creazione del resoconto delle suddette restrizioni mediante un albero JTree (dinamico).
 * L'elemento base di questa struttura è caratterizzato da un oggetto di tipo <b>elementoBase</b>
 * <p align="center">&nbsp;</p> 
 * @see elementoBase
 */
public class BufferRestrizioni extends Vector
{

	final Color cRoot=Color.black;
	final Color cRamo1=Color.white;
	final Color cRamo2=Color.white;
	final Color cSfondoTree=Color.gray;
	
	final int MAX_N_VALUE = staticLib.mdm_server_serverRef.eMAX_N_VALUE;
        final int ASCII_REST_LEN = staticLib.mdm_server_serverRef.eASCII_REST_LEN;

	public getConfigFiltro configuration;
	public Eccezioni localEccezioni;
	public Relation localRelation;
	public TrafficElement localDataElement;
	private Vector vEcc;
        private boolean enableMessage = true;
	    	
	/**
         * Costruttore di default
         */
	public BufferRestrizioni()
    	{
            super();
        }
	
        public BufferRestrizioni(boolean enable_Message)
    	{
            super();
            enableMessage = enable_Message;
        }
        
        /**
         * Questo metodo permette di settare il riferimento alla configurazione.
         *@param configuration 
         */
	public void setConfiguration(getConfigFiltro configuration)
	{
		this.configuration=configuration;
		this.localEccezioni=new Eccezioni(configuration.get_Exceptions());
		this.localRelation=new Relation(configuration.get_AllRelations());
		this.localDataElement=new TrafficElement(configuration.get_Traffic_Elements());
		
		/*System.out.println("Numero Eccezioni TOT: "+localEccezioni.localEccezioni.length);
		for(int i=0; i<localEccezioni.localEccezioni.length ;i++)
		{
			System.out.println("ID: "+localEccezioni.localEccezioni[i].idException+ "   TAG: "+(new String(localEccezioni.localEccezioni[i].tag)).trim()+ " con TRIGGER su TE="+localEccezioni.localEccezioni[i].idTriggerRestriction);
		}*/
		
		vEcc=localEccezioni.getEccezioni();
		
		/*System.out.println("Numero Eccezioni PADRE: "+vEcc.size());
		for(int i=0; i<vEcc.size() ;i++)
		{
			DATA_EXCEPTIONS tmpEcc=(DATA_EXCEPTIONS)vEcc.elementAt(i);
			System.out.println("ID: "+tmpEcc.idException+ "   TAG: "+(new String(tmpEcc.tag)).trim()+ " con TRIGGER su TE="+tmpEcc.idTriggerRestriction);
		}*/
		
	}
    
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Ritorna il numero di elementi presenti nell'oggetto stesso.		
         * </font></font></p></pre>  
         */
    	public int getSize()
    	{
        	return size();
    	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Overloading del metodo getSize().
	 * Ritorna il numero valori inseriti nella restrizione avente id specificato.		
	 * </font></font></p></pre>  
	 * @param idTE identificativo della restrizione.
	 */
	public int getSize(int idTE)
    	{
            	elementoBase eb=isPresentEB(idTE);
    		if (eb!=null)
	        {
                    	return eb.getSize();
	        }else
	        {
	            	return 0;
            	}
    	}
            
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Inserisce un valore alla restrizione avente id specificato.		
	 * </font></font></p></pre>  
	 * @param idTE identificativo della Restrizione,
	 * @param teDesc descrizione dell'Elemento di Traffico della restrizione in questione,
	 * @param value valore di specifica della restrizione,
	 * @param desc descrizione del valore di specifica della restrizione,
	 * @param flag identifiva la validità della restrizione(gestione eccezioni),
	 * @param op parametro utilizzato dal Master Server,
	 * @param pri parametro utilizzato dal Master Server.	 
	 */ 
    	public void addRestriction(int idTE,String teDesc,String value,String desc,boolean flag,int op,int pri,int idEcc)
    	{
            
            //System.out.println("BF-OP: "+op);
            elementoBase eb=isPresentEB(idTE);
            if (eb==null)
            {
                eb=new elementoBase(idTE,teDesc,flag,op,pri,idEcc);
                eb.addValue(value,desc,enableMessage);
                addElement(eb);
            }
            else
            {
                eb.op=1;
                eb.addValue(value,desc,enableMessage);
            }
        }
               
        public void addRestriction(REST_INFO rest_info)
    	{
            DATA_DATAELEMENT DATA_TE =localDataElement.get_Traffic_Element(rest_info.Element);  

            for(int i=0; i<rest_info.AsciiValue.length; i++)
            {
                String value = new String(rest_info.AsciiValue[i]).trim();                
                if(value.length() >0)
                {
                    addRestriction(rest_info.Element,
                                    (new String(DATA_TE.longDescription).trim()),
                                    value,
                                    value,
                                    true,
                                    rest_info.Operator,
                                    rest_info.Priority,
                                    DATA_TE.exceptions[0]);
                }
            }
        }
        
        
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Inserisce un valore alla restrizione avente id specificato, per restrizioni a lista.		
	 * </font></font></p></pre>  
	 * @param idTE identificativo della Restrizione,
	 * @param teDesc descrizione dell'Elemento di Traffico della restrizione in questione,
	 * @param value valore di specifica della restrizione,
	 * @param desc descrizione del valore di specifica della restrizione,
	 * @param flag identifiva la validitò della restrizione(gestione eccezioni),
	 * @param op parametro utilizzato dal Master Server,
	 * @param pri parametro utilizzato dal Master Server.	 
	 */       
    	public void addRestrictionGUI3(int idTE,String teDesc,String value,String desc,boolean flag,int op,int pri,int idEcc)
    	{
            	elementoBase eb=isPresentEB(idTE);
            	if (eb == null)
            	{
                    	eb=new elementoBase(idTE,teDesc,flag,op,pri,idEcc);
                    	eb.addValue(value,desc,enableMessage);
                    	addElement(eb);
            	}
		else
            	{
                    	eb.sostValue(value,desc);
            	}
    	}
            
                            
         /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Rimuove la restrizione avente id specificato.
         * </font></font></p></pre>  
         * 
         * @param idTE identificativo della restrizione.	 
         */                    
        public void removeElem(int idTE)
    	{
            	elementoBase eb=isPresentEB(idTE);
            	if (eb!=null)
            	{
            		removeElement(eb);
        	}
    	}
        
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Rimuove la restrizione avente descrizione specificata.
	 * </font></font></p></pre>  
	 * 
	 * @param desc stringa descrittiva della restrizione.	 
	 */   
     	public void removeElem(String desc)
    	{
            	elementoBase eb=isPresentEB(desc);
            	if (eb!=null)
            	{
            		removeElement(eb);
        	}
    	}
	
    	public boolean isRestriction(int idTE)
	{
		elementoBase eb=isPresentEB(idTE);
		if (eb==null)
            	{
               		return false;
        	}
		else
		{
			return eb.isRestriction;
		}
	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Rimuove il valore specificato dalla restrizione specificata.
	 * </font></font></p></pre>  
	 * 
	 * @param idTE identificativo della restrizione,
	 * @param val valore da rimuovere.	 
	 */ 
    	public void removeValue(int idTE,String val)
    	{
		elementoBase eb=isPresentEB(idTE);
                if (eb!=null)
                {
			eb.removeValueAscii(val);
			if(eb.isEmpty())
                        {
				removeElement(eb);      
			}
		}
	}
         
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Rimuove il valore specificato dalla restrizione specificata.
	 * La restrizione ed il valore vengono ricercati tramite la stringa descrittiva che li identifica.
	 * </font></font></p></pre>  
	 * 
	 * @param descTE descrizione identificativa della restrizione,
	 * @param descVal descrizione identificativo del valore da rimuovere.
	 */      
    	public void removeValue(String descTE,String descVal)
    	{
		elementoBase eb=isPresentEB(descTE);
                if (eb!=null)
                {
			eb.removeValue(descVal);
			if(eb.isEmpty())
                            {
                                    removeElement(eb);      
                            }
                    }
    	}
    
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Rimuove tutte le restrizioni dall'oggetto stesso.
	 * </font></font></p></pre>  
	 */ 
    	public void celarAll()
    	{
        	removeAllElements();
    	}
    
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Ritorna la descrizione del valore presente alla posizione specificata, della restrizione richiesta.
	 * </font></font></p></pre>  
	 * 
	 * @param idTE identificativo della restrizione,
	 * @param pos posizione del valore da esaminare.
	 */ 
	public String getValueLabelGUI3(int idTE,int pos)
    	{
        	elementoBase eb=isPresentEB(idTE);
   		if (eb!=null)
    		{
        		return eb.getDescValueGUI3(pos);
        	}
        	return null;
    	}
    
                    
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Ritorna la descrizione del valore specificato, della restrizione richiesta.
	 * </font></font></p></pre>  
	 * 
	 * @param idTE identificativo della restrizione,
	 * @param val valore da esaminare.
	 */                
    	public String getValueLabel(int idTE,String val)
    	{
    		elementoBase eb=isPresentEB(idTE);
    		if (eb!=null)
    		{
        		return eb.getDescValue(val);
        	}
        	return null;
    	}
             
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Ritorna l'identificativo della restrizione specificata.
	 * </font></font></p></pre>  
	 * 
	 * @param TE  stringa identificativa della restrizione,
	 */   
    	public int getIdTE(String TE)
    	{
        	elementoBase eb=isPresentEB(TE);
        	if (eb!=null)
        	{
            		return eb.idTE;
        	}
        	return -1;
    	}
	
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Ritorna il flag di validità della restrizione specificata.
	 * </font></font></p></pre>  
	 * 
	 * @param TE  stringa identificativa della restrizione,
	 */ 
	public boolean getFlag(String TE)
    	{
        	elementoBase eb=isPresentEB(TE);
        	if (eb!=null)
        	{
            		return eb.isRestriction;
       	 	}
       	 	return false;
    	}
                    
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Ritorna il valore a specifica descrizione di restrizione e specifica descrizione del valore.
	 * </font></font></p></pre>  
	 * 
	 * @param descTE  stringa identificativa della restrizione,
	 * @param descVal  stringa identificativa della restrizione.
	 */                 
    	public String getValue(String descTE,String descVal)
    	{
        	elementoBase eb=isPresentEB(descTE);
        	if (eb!=null)
        	{
			return eb.getValue(descVal.trim());
        	}
        	return null;
    	}
                            
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Controlla sel la specifica restrizione è presente.
	 * </font></font></p></pre>  
	 * 
	 * @param idTE  identificativo della restrizione.
	 */     
    	public elementoBase isPresentEB(int idTE)
    	{
        	elementoBase temp;
            	for(int i=0;i<size();i++)
            	{
                	temp=(elementoBase)elementAt(i);
                    	if(temp.idTE==idTE)
                            	return temp;
            	}
            	return null;
    	}
           
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Controlla sel la specifica restrizione è presente.
	 * </font></font></p></pre>  
	 * 
	 * @param desc stringa identificativa della restrizione.
	 */    
    	public elementoBase isPresentEB(String desc)
    	{
        	elementoBase temp;
            	for(int i=0;i<size();i++)
            	{
                	temp=(elementoBase)elementAt(i);
                	if(temp.teDesc.equals(desc))
                            	return temp;
            	}
            	return null;
    	}
            
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Controlla sel il valore specificato e presente all'interno della specifica restrizione.
	 * </font></font></p></pre>  
	 * 
	 * @param idTE identificativo della restrizione.
	 * @param val identificativo del valore da controllare.	 
	 */      
    	public int isPresentValue(int idTE,String val)
    	{
                            
        	elementoBase eb=isPresentEB(idTE);
        	if (eb!=null)
            	{
			return eb.isPresentVal(val);
		}
		return -1;
	}

    	//************************************************************
    	//  Stampa alberatura
    	//************************************************************  
	public void stampaAll()
	{
		elementoBase tempEB;
		for(int i=0;i<size();i++)
		{
			tempEB=(elementoBase)elementAt(i);
			System.out.println("idTE="+tempEB.idTE+"   Desc="+tempEB.teDesc+"  OP: "+tempEB.op+"  Priority: "+tempEB.pri);
			System.out.print("Dicitura: [");
			Vector v=tempEB.valueLabel;  
			int j=0;
			
			for(j=0;j<v.size();j++)
			{
				System.out.print(" "+(String)v.elementAt(j));
                        }
			
			System.out.print("]\n");
			System.out.print("Valore: [");
			Vector v1=tempEB.value;  
			
			for(j=0;j<v1.size();j++)
			{
				System.out.print(" "+(String)v1.elementAt(j));
			}
			System.out.print("]\n");
		}
	}
	
	public String getDescLabel(DATA_DATAELEMENT te,DATA_EXCEPTIONS eccezione)
	{
		int PD=0;
		String actionVal=(new String(eccezione.targetValue)).trim();	
		String actionValLabel=(new String(eccezione.targetValue)).trim();					                        
		while((te.valueList[PD]!=0)||(new String(te.valueListLabel[PD]).trim().length() != 0))
		{
			/*
			System.out.println("actionVal: ["+actionVal+"]");
			System.out.println("VAL: ["+(int)te.valueList[PD]+"]");
		        System.out.println("VAL_LABEL: ["+new String(te.valueListLabel[PD]).trim()+"]");
		          */  
			if(actionVal.compareTo(""+(int)te.valueList[PD])==0)
			{
		            //System.out.println("VAL: "+(int)te.valueList[PD]);
		            //System.out.println("VAL_LABEL: "+new String(te.valueListLabel[PD]).trim());					                                
		            actionValLabel=new String(te.valueListLabel[PD]).trim();
		            break;
		        }
		        PD++;
		}
		
		return actionValLabel;
	}
	    
	public boolean verificaSeTETrigger(int idTE)
	{
		boolean FLAG=false;
		
		//System.out.println("verificaSeTETrigger("+idTE+")");

		for(int i=0; i<localEccezioni.localEccezioni.length ;i++)
		{
			
			if(localEccezioni.localEccezioni[i].idTriggerRestriction == idTE)
			{
				//System.out.println("TE="+idTE+" è un TRIGGER associato all'Eccezzione: " +localEccezioni.localEccezioni[i].idException+"-"+(new String(localEccezioni.localEccezioni[i].tag)).trim());	
				FLAG=true;
				break;
			}
		}

		return FLAG;
	}
	
	
	public boolean controllaEccezioni(DATA_EXCEPTIONS appoEcc)
	{	
		//System.out.println("**************** controllaEccezioni() ********************* ");	
		elementoBase tempEB;
		boolean flagTriggerTrovato=false;
		boolean FLAG_verifyEcc=false;
					
		/* prendo l'eccezzione */	
		int idTriggerRestriction=appoEcc.idTriggerRestriction;
		int status=appoEcc.triggeredStatus;
		String triggeredValue=(new String(appoEcc.triggeredValue)).trim();
		int action=appoEcc.action;
		/************************************************/
	
		/*System.out.println("1) ID_ECC: "+appoEcc.idException+
			"\n1) ECC_TAG: "+(new String(appoEcc.tag)).trim()+
			"\n1) TriggerID: "+appoEcc.idTriggerRestriction+
			"\n1) TriggerStatus:"+appoEcc.triggeredStatus+
			"\n1) TriggerValue:"+(new String(appoEcc.triggeredValue)).trim()+
			"\n1) Action: "+appoEcc.action+
			"\n1) Action da settare: "+(new String(appoEcc.targetValue)).trim()+
			"\n1) Eccezione Aggregata: "+appoEcc.idAggregateException);*/
		
		switch(status) // controllo in che stato deve essere la restrizione 
		{
			case 0:{ // se è attivo il trigger 
					for(int i=0;i<size();i++)
					{
						tempEB=(elementoBase)elementAt(i);
						if(tempEB.idTE==idTriggerRestriction)
						{
							//System.out.println("Il trigger è attivo: "+idTriggerRestriction);
							FLAG_verifyEcc=true;
							break;
						}
					}
				} break;
			case 1:{ // se è disattivo il trigger 
					for(int i=0;i<size();i++)
					{
						tempEB=(elementoBase)elementAt(i);
						if(tempEB.idTE==idTriggerRestriction)
						{
							flagTriggerTrovato=true;
							break;
						}
					}
					if(flagTriggerTrovato==false)
					{
						//System.out.println("Il trigger non è attivo: "+idTriggerRestriction);
						FLAG_verifyEcc=true;
					}
				} break;
			case 2:{ // se è attivato il trigger con uno specifico valore
					for(int i=0;i<size();i++)
					{
						tempEB=(elementoBase)elementAt(i);
						//tempEB.stampaValue();
						//tempEB.stampaValueLabel();
						if(tempEB.idTE==idTriggerRestriction)
						{
							if(tempEB.isPresentValAscii(triggeredValue)!=-1)
							{
								//System.out.println("Il trigger è attivo sul Traffic element: "+idTriggerRestriction+"  con action:"+triggeredValue);
								FLAG_verifyEcc=true;
								break;
							}
						}
					}
				} break;
		}
		return FLAG_verifyEcc;		
	}
	
    	public void applicaEccezioni(int idTE)
	{

		//System.out.println("applicaEccezioni("+idTE+")");

		if(localEccezioni!=null)
		{
			if(localEccezioni.localEccezioni.length==0)
			{
				//System.out.println("NON CI SONO ECCEZIONI");
				return;
			}
		}
		else
		{
			//System.out.println("NON CI SONO ECCEZIONI");
			return;
		}
		
		if(verificaSeTETrigger(idTE)==false)
		{
			//System.out.println("TE="+idTE+" NON è un TRIGGER.");
			return;
		}
		
		elementoBase tempEB;
		
            	//System.out.println("\n\n Numero restrizione selezionate = "+size());
            	//System.out.println("\n\n Numero eccezioni attive = "+vEcc.size());

		if(vEcc.size()>0)
		{
			boolean flagTriggerTrovato=false;
			for(int h=0;h<vEcc.size();h++)
			{
				
				/* prendo l'eccezzione */
				DATA_EXCEPTIONS appoEcc=(DATA_EXCEPTIONS)vEcc.elementAt(h);
				
				if(controllaEccezioni(appoEcc)==true)
				{
					if(appoEcc.idAggregateException == 0)
					{
						//System.out.println("ECCEZIONE VERIFICATA.");
					}
					else
					{
						boolean flagEccezioniFiglie=true;
						//System.out.println("Controllo eventuali aggregate");
						DATA_EXCEPTIONS nextEcc=(DATA_EXCEPTIONS)vEcc.elementAt(h);
						
						while(nextEcc.idAggregateException!=0)
						{
							nextEcc=localEccezioni.get_Eccezione_conId(nextEcc.idAggregateException);
							if(controllaEccezioni(nextEcc)==false)
							{
								flagEccezioniFiglie=false;
								break;
							}	
						}

						if(flagEccezioniFiglie==false)
						{
							//System.out.println("ECCEZIONE NON VERIFICATA.");
							continue;
						}else
						{
							//System.out.println("ECCEZIONE VERIFICATA.");	
						}
					}
				}
				else
				{
					//System.out.println("ECCEZIONE NON VERIFICATA.");
					continue;
				}
						
				int idTriggerRestriction=appoEcc.idTriggerRestriction;
				int status=appoEcc.triggeredStatus;
				String triggeredValue=(new String(appoEcc.triggeredValue)).trim();
				int action=appoEcc.action;
				/************************************************/
				
				/* mi becco l'eventuale restrizione da attivare */
				
				int idAlarmElemenDaAttivare=-1;
				for(int i=0;i<localDataElement.num_Traffic_Element();i++)
				{
					DATA_DATAELEMENT appoTRAFFIC_ELEMENT=(DATA_DATAELEMENT)localDataElement.localElement[i];

					int k=0;
					
					while(appoTRAFFIC_ELEMENT.exceptions[k]!=0)
					{
						if(appoTRAFFIC_ELEMENT.exceptions[k]==appoEcc.idException)
						{	
							idAlarmElemenDaAttivare=appoTRAFFIC_ELEMENT.idElement;
							break;
						}
						k++;
					} 
				}
				
				/****************************************************/
				
				
				/*System.out.println("ID_ECC: "+appoEcc.idException+
					"\nECC_TAG: "+(new String(appoEcc.tag)).trim()+
					"\nTriggerID: "+appoEcc.idTriggerRestriction+
					"\nTriggerStatus:"+appoEcc.triggeredStatus+
					"\nTriggerValue:"+(new String(appoEcc.triggeredValue)).trim()+
					"\nAction: "+appoEcc.action+
					"\nAction da settare: "+(new String(appoEcc.targetValue)).trim()+
					"\nEccezione Aggregata: "+appoEcc.idAggregateException);*/
				
				switch(status) // controllo in che stato deve essere la restrizione 
				{
					case 0:{ // se è attivo il trigger 
							for(int i=0;i<size();i++)
        						{
        							tempEB=(elementoBase)elementAt(i);
        							if(tempEB.idTE==idTriggerRestriction)
								{
									//System.out.println("Il trigger è attivo: "+idTriggerRestriction);
									
									switch(action) 
									{
										case 0:{
											//System.out.println("Devo attivare la restrizione: "+idAlarmElemenDaAttivare);

											DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
											
											removeElem(newTRAFFIC_ELEMENT.idElement);
											
											if (newTRAFFIC_ELEMENT.defaultValue != 0)
											{
									                        int val,PD=0;
									                        val=(int)newTRAFFIC_ELEMENT.defaultValue;							                        
									                        while((newTRAFFIC_ELEMENT.valueList[PD]!=0)||(new String(newTRAFFIC_ELEMENT.valueListLabel[PD]).trim().length() != 0))
									                        {
									                            if(((int)newTRAFFIC_ELEMENT.valueList[PD])==val)
									                            {
									                                addRestriction(newTRAFFIC_ELEMENT.idElement,
									                                        new String(newTRAFFIC_ELEMENT.longDescription).trim(),
									                                        ""+(int)newTRAFFIC_ELEMENT.valueList[PD],
									                                        new String(newTRAFFIC_ELEMENT.valueListLabel[PD]).trim(),
									                                        true,
									                                        newTRAFFIC_ELEMENT.compareSelection,
									                                        newTRAFFIC_ELEMENT.priority,
									                                        newTRAFFIC_ELEMENT.exceptions[0]);
									                                break;
									                            }
									                            PD++;
									                        }
									                }
									                else
									                {	
									                	/* prendo un eventuale descrizione */
												String actionValLabel=getDescLabel(newTRAFFIC_ELEMENT,appoEcc);
												/*****************************************/	
											
												addRestriction(	newTRAFFIC_ELEMENT.idElement,
												new String(newTRAFFIC_ELEMENT.longDescription).trim(),
												(new String(appoEcc.targetValue)).trim(),
												actionValLabel,
												true,
												newTRAFFIC_ELEMENT.compareSelection,
												newTRAFFIC_ELEMENT.priority,
												newTRAFFIC_ELEMENT.exceptions[0]);
											}
											} break; // attiva la restrizione
										case 1:{//System.out.println("Devo disattivare la restrizione: "+idAlarmElemenDaAttivare);
											DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
											removeElem(newTRAFFIC_ELEMENT.idElement);
											} break; // disattiva la restrizione
										case 2:{//System.out.println("Devo attivare/settare la restrizione: "+idAlarmElemenDaAttivare);
											DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
											
											/* prendo un eventuale descrizione */
											String actionValLabel=getDescLabel(newTRAFFIC_ELEMENT,appoEcc);
											/*****************************************/	
											
										
											removeElem(newTRAFFIC_ELEMENT.idElement);
											addRestriction(	newTRAFFIC_ELEMENT.idElement,
												new String(newTRAFFIC_ELEMENT.longDescription).trim(),
												(new String(appoEcc.targetValue)).trim(),
												actionValLabel,
												true,
												newTRAFFIC_ELEMENT.compareSelection,
												newTRAFFIC_ELEMENT.priority,
												newTRAFFIC_ELEMENT.exceptions[0]);
											} break; // attiva e setta la restrizione
									}
									
									break;
								}
        						}
						} break;
					case 1:{ // se è disattivo il trigger 
							for(int i=0;i<size();i++)
        						{
        							tempEB=(elementoBase)elementAt(i);
        							if(tempEB.idTE==idTriggerRestriction)
        							{
        								flagTriggerTrovato=true;
        								break;
        							}
        						}
        						if(flagTriggerTrovato==false)
        						{
        							//System.out.println("Il trigger non è attivo: "+idTriggerRestriction);
        							switch(action) 
								{
									case 0:{
										//System.out.println("Devo attivare la restrizione: "+idAlarmElemenDaAttivare);

										DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
										
										removeElem(newTRAFFIC_ELEMENT.idElement);
										
										if (newTRAFFIC_ELEMENT.defaultValue != 0)
										{
								                        int val,PD=0;
								                        val=(int)newTRAFFIC_ELEMENT.defaultValue;							                        
								                        while((newTRAFFIC_ELEMENT.valueList[PD]!=0)||(new String(newTRAFFIC_ELEMENT.valueListLabel[PD]).trim().length() != 0))
								                        {
								                            if(((int)newTRAFFIC_ELEMENT.valueList[PD])==val)
								                            {
								                                addRestriction(newTRAFFIC_ELEMENT.idElement,
								                                        new String(newTRAFFIC_ELEMENT.longDescription).trim(),
								                                        ""+(int)newTRAFFIC_ELEMENT.valueList[PD],
								                                        new String(newTRAFFIC_ELEMENT.valueListLabel[PD]).trim(),
								                                        true,
								                                        newTRAFFIC_ELEMENT.compareSelection,
								                                        newTRAFFIC_ELEMENT.priority,
								                                        newTRAFFIC_ELEMENT.exceptions[0]);
								                                break;
								                            }
								                            PD++;
								                        }
								                }
								                else
								                {	
							                		/* prendo un eventuale descrizione */
											String actionValLabel=getDescLabel(newTRAFFIC_ELEMENT,appoEcc);
											/*****************************************/	
											
											addRestriction(	newTRAFFIC_ELEMENT.idElement,
											new String(newTRAFFIC_ELEMENT.longDescription).trim(),
											(new String(appoEcc.targetValue)).trim(),
											actionValLabel,
											true,
											newTRAFFIC_ELEMENT.compareSelection,
											newTRAFFIC_ELEMENT.priority,
											newTRAFFIC_ELEMENT.exceptions[0]);
										}
										} break; // attiva la restrizione
									case 1:{//System.out.println("Devo disattivare la restrizione: "+idAlarmElemenDaAttivare);
										DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
										removeElem(newTRAFFIC_ELEMENT.idElement);
										} break; // disattiva la restrizione
									case 2:{//System.out.println("Devo attivare/settare la restrizione: "+idAlarmElemenDaAttivare);
										DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
										
										/* prendo un eventuale descrizione */
										String actionValLabel=getDescLabel(newTRAFFIC_ELEMENT,appoEcc);
										/*****************************************/
										
										removeElem(newTRAFFIC_ELEMENT.idElement);
										addRestriction(	newTRAFFIC_ELEMENT.idElement,
											new String(newTRAFFIC_ELEMENT.longDescription).trim(),
											(new String(appoEcc.targetValue)).trim(),
											actionValLabel,
											true,
											newTRAFFIC_ELEMENT.compareSelection,
											newTRAFFIC_ELEMENT.priority,
											newTRAFFIC_ELEMENT.exceptions[0]);
										} break; // attiva e setta la restrizione
								}
        						}
						} break;
					case 2:{ // se è attivato il trigger con uno specifico valore
							for(int i=0;i<size();i++)
        						{
        							tempEB=(elementoBase)elementAt(i);
        							//tempEB.stampaValue();
        							//tempEB.stampaValueLabel();
        							if(tempEB.idTE==idTriggerRestriction)
								{
									if(tempEB.isPresentValAscii(triggeredValue)!=-1)
									{
										//System.out.println("Il trigger è attivo sul Traffic element: "+idTriggerRestriction+"  con action:"+triggeredValue);
										switch(action) 
										{
											
											case 0:{
												//System.out.println("Devo attivare la restrizione: "+idAlarmElemenDaAttivare);
		
												DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
												
												removeElem(newTRAFFIC_ELEMENT.idElement);
												
												if (newTRAFFIC_ELEMENT.defaultValue != 0)
												{
										                        int val,PD=0;
										                        val=(int)newTRAFFIC_ELEMENT.defaultValue;							                        
										                        while((newTRAFFIC_ELEMENT.valueList[PD]!=0)||(new String(newTRAFFIC_ELEMENT.valueListLabel[PD]).trim().length() != 0))
										                        {
										                            if(((int)newTRAFFIC_ELEMENT.valueList[PD])==val)
										                            {
										                                addRestriction(newTRAFFIC_ELEMENT.idElement,
										                                        new String(newTRAFFIC_ELEMENT.longDescription).trim(),
										                                        ""+(int)newTRAFFIC_ELEMENT.valueList[PD],
										                                        new String(newTRAFFIC_ELEMENT.valueListLabel[PD]).trim(),
										                                        true,
										                                        newTRAFFIC_ELEMENT.compareSelection,
										                                        newTRAFFIC_ELEMENT.priority,
										                                        newTRAFFIC_ELEMENT.exceptions[0]);
										                                break;
										                            }
										                            PD++;
										                        }
										                }
										                else
										                {	
										                	/* prendo un eventuale descrizione */
													String actionValLabel=getDescLabel(newTRAFFIC_ELEMENT,appoEcc);
													/*****************************************/
												
													addRestriction(	newTRAFFIC_ELEMENT.idElement,
													new String(newTRAFFIC_ELEMENT.longDescription).trim(),
													(new String(appoEcc.targetValue)).trim(),
													actionValLabel,
													true,
													newTRAFFIC_ELEMENT.compareSelection,
													newTRAFFIC_ELEMENT.priority,
													newTRAFFIC_ELEMENT.exceptions[0]);
												}
												} break; // attiva la restrizione
											case 1:{//System.out.println("Devo disattivare la restrizione: "+idAlarmElemenDaAttivare);
												DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
												removeElem(newTRAFFIC_ELEMENT.idElement);
												} break; // disattiva la restrizione
											case 2:{//System.out.println("Devo attivare/settare la restrizione: "+idAlarmElemenDaAttivare);
												DATA_DATAELEMENT newTRAFFIC_ELEMENT=localDataElement.get_Traffic_Element(idAlarmElemenDaAttivare);
												
												/* prendo un eventuale descrizione */
												String actionValLabel=getDescLabel(newTRAFFIC_ELEMENT,appoEcc);
												/*****************************************/
												
												removeElem(newTRAFFIC_ELEMENT.idElement);
												addRestriction(	newTRAFFIC_ELEMENT.idElement,
													new String(newTRAFFIC_ELEMENT.longDescription).trim(),
													(new String(appoEcc.targetValue)).trim(),
													actionValLabel,
													true,
													newTRAFFIC_ELEMENT.compareSelection,
													newTRAFFIC_ELEMENT.priority,
													newTRAFFIC_ELEMENT.exceptions[0]);
												} break; // attiva e setta la restrizione
										}
										break;
									}
								}
        						}
						} break;
				}
			}
		}
	}
	
    	//************************************************************
    	// Mi creo la struttura delle restrizioni fino ad ora immesse
    	//************************************************************
        
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Questo metodo formatta le restrizioni selezionate dall'utente in un formato comprensibile al Master Server.
	 * Ritorna un array ti tipo Rest.
	 * </font></font></p></pre>
	 * @see Rest
	 */
	public REST_INFO[] creaRest()
    	{
		
		/*System.out.println("\n\n*************** prima del controllo delle eccezioni ******************");
		stampaAll();
		System.out.println("**********************************************************************\n\n");
		
		System.out.println("\n\n*************** dopo del controllo delle eccezioni ******************");
		stampaAll();
		System.out.println("**********************************************************************\n\n");*/
		
		//************************************
		// azzero le restrizioni statiche
		//************************************

		//staticLib.struc_paramsCURRENT.OfficeCode=new char[21];
		//staticLib.struc_paramsCURRENT.NumeroCifreUtente=0;


		/* questa parte è per la cablatura del 999 sulle restrizione per indirizzi IP */
                /*elementoBase tempEB_forzatura;
                boolean forzatura=false;
		for(int i=0;i<size();i++)
		{
			tempEB_forzatura=(elementoBase)elementAt(i);
                        if((tempEB_forzatura.idTE==77)||(tempEB_forzatura.idTE==78))
                        {
                            System.out.println("Aggiungo [999-ALL] per la restrizione: ="+tempEB_forzatura.idTE);
                            forzatura=true;
			    tempEB_forzatura.addValue("999","ALL",enableMessage);
                            break;
                        }
                }*/
                /* ****************************************************************************/

		elementoBase tempEB1;
		int contALL=0;
		for(int i=0;i<size();i++)
            	{
			tempEB1=(elementoBase)elementAt(i);
			if(tempEB1.isRestriction==true)
			contALL++;
		}//ho contato le rest valide;
		
		if(contALL>0)
		{
			REST_INFO[] r=new REST_INFO[contALL];
			int cont=0;
            		elementoBase tempEB;

                    	for(int i=0;i<size();i++)
                    	{
                            	tempEB=(elementoBase)elementAt(i);
				
                            	Vector v=tempEB.value;
                            	int[] val=new int[MAX_N_VALUE];
				char[][] valAscii=new char[MAX_N_VALUE][ASCII_REST_LEN];

				String descAPP=(String)(tempEB.valueLabel.elementAt(0));

				if(tempEB.isRestriction==true)
				{
					int j=0,appoV=v.size();

					for(j=0;j<appoV;j++)
					{
                                    		//val[j]=((Integer)v.elementAt(j)).intValue();
						try
						{
							val[j]=(Integer.valueOf(((String)v.elementAt(j)).trim())).intValue();
						}catch(Exception e)
						{
							val[j]=0;
						}
						valAscii[j]=set_String_toChar((String)v.elementAt(j),ASCII_REST_LEN);
						//System.out.println("Valore Ascii: "+new String(valAscii[j]));

        	            		}

					val[j]=-1;

					r[cont]=new REST_INFO(1,tempEB.idTE,val,valAscii,tempEB.op,tempEB.pri);
					cont++;
								
					/*if(descAPP.equals("OCO")||descAPP.equals("OCE"))
					{
						char OC_app[]=staticLib.OCO.toCharArray();
						System.out.println("Crea rest OCO/OCE:  "+(new String(OC_app)).trim());
    						for(int ii=0;ii<staticLib.OCO.length();ii++)
						{
    							staticLib.struc_paramsCURRENT.OfficeCode[ii]=OC_app[ii];
						}
									
					}*/
				}
				else
				{
					//***************************************************************
					// gestione restrizioni statiche presenti in STRUCT_PARAMS
					//***************************************************************
							
					//String descAPP=(String)(tempEB.valueLabel.elementAt(0));
					if(descAPP.equals("NUMCIFREU"))
					{
						//staticLib.struc_paramsCURRENT.NumeroCifreUtente=((String)v.elementAt(0)).intValue();
						//staticLib.struc_paramsCURRENT.NumeroCifreUtente=(Integer.valueOf(((String)v.elementAt(0)).trim())).intValue();
					}
					/*else if(descAPP.equals("OCO")||descAPP.equals("OCE"))
					{
						char OC_app[]=staticLib.OCO.toCharArray();
    						for(int ii=0;ii<staticLib.OCO.length();ii++)
						{
    							staticLib.struc_paramsCURRENT.OfficeCode[ii]=OC_app[ii];
						}
					}*/
				}
                                 
			}

			return r;
		}
		else
		{
			REST_INFO[] RestNULL = new REST_INFO[1];
                        RestNULL[0]=new REST_INFO();
	                RestNULL[0].Level=0;
	                RestNULL[0].Element=0;
    	                RestNULL[0].Operator=0;
        	        RestNULL[0].Value = new int[MAX_N_VALUE];
			RestNULL[0].AsciiValue = new char[MAX_N_VALUE][ASCII_REST_LEN];
            	        RestNULL[0].Value[0]=-1; 
                	return RestNULL;	
		}                
	}
    
        
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Questo metodo crea un sommario delle restrizioni selezionate dall'utente.
	 * Ritorna un oggetto ti tipo javax.swing.JPanel.
	 * </font></font></p></pre
	 */
        public javax.swing.JPanel creaSummaryRestriction() //sostituisce br.creaAlbero
        {
        
            javax.swing.JPanel jP_ALLRestrictions = new javax.swing.JPanel();
            jP_ALLRestrictions.setBackground(Color.white);

            int SIZE = size();
            int rows = SIZE;
            if(rows < 6 )
                rows = 6;
            
            jP_ALLRestrictions.setLayout(new java.awt.GridLayout((rows+1),1));
            
            if (SIZE > 0)
            {
                elementoBase tempEB;
                String valori= ""; 
                String[] valori_array;
                
                MDM_JP_CellSummary CellSummary_Title = new MDM_JP_CellSummary(190);
                CellSummary_Title.removeDesc();
                CellSummary_Title.setValueCell("RESTRICTION SELECTED","tab_restrictions.png");
                jP_ALLRestrictions.add(CellSummary_Title);
                
                for(int i=0; i< SIZE; i++)
                {
                    tempEB = (elementoBase)elementAt(i);
                    
                    MDM_JP_CellSummary CellSummary_rest = new MDM_JP_CellSummary(190);
                    CellSummary_rest.setDescCell(tempEB.teDesc,"b_mini_vok.png");
                    
                    if(tempEB.isRestriction)
                    {
                        Vector v = tempEB.valueLabel;
                        int v_SIZE = v.size();
                        if(v_SIZE > 4)
                        {
                            valori_array = new String[v_SIZE];
                            for(int j=0;j<v_SIZE;j++)
                            {
                               valori_array[j] = staticLib.operator[tempEB.op] + (String)v.elementAt(j); 
                            }
                            CellSummary_rest.setValueCell(valori_array,null);
                        }
                        else
                        {                             
                            String str;
                            if (v_SIZE>0) 
                            {
                                String blank = "-";
                                for(int j=0;j<v_SIZE;j++)
                                {
                                    if(j == (v_SIZE-1))
                                        blank = "";
                                    
                                    str = (String)v.elementAt(j);
                                    valori = valori + staticLib.operator[tempEB.op]+ str + blank;
                                }
                            }
                            else
                                valori= valori +" null ";
                            
                            CellSummary_rest.setValueCell(valori,null);
                        }
                    }
                    else
                    {
                        Vector v = tempEB.value;
                        Vector v1 = tempEB.valueLabel; 
                        int v_SIZE = v.size();
                        String intVal;      
                	
                        if (v_SIZE>0) 
                        {
                            String desc = (String)v1.elementAt(0);
                            if(desc.trim().equals("OCO")||desc.trim().equals("OCE"))
                            {
                                valori = valori+" "+staticLib.operator[tempEB.op]+staticLib.OCO;	
                            }
                            else
                            {
                                String blank = "-";
                                for(int j=0;j<v_SIZE;j++)
                                {
                                    if(j == (v_SIZE-1))
                                        blank = "";
                                    
                                    intVal=(String)v.elementAt(j);
                                    
                                    if(intVal != null)
                                        valori= valori + staticLib.operator[tempEB.op] + intVal + blank;
                                }
                            }
                        }
                        else
                            valori=valori+" null ";
                        
                        CellSummary_rest.setValueCell(valori,null);                    
                    }
                    
                    jP_ALLRestrictions.add(CellSummary_rest);
                    valori = "";
                }
            }
            else
            {
                MDM_JP_CellSummary CellSummary_Title = new MDM_JP_CellSummary(190);
                CellSummary_Title.removeDesc();
                CellSummary_Title.setValueCell("Restrictions not selected","no_mini.png");
                jP_ALLRestrictions.add(CellSummary_Title);
            }
            
            return jP_ALLRestrictions;
        }
        
        
	//*************************************************
	//      creo l'albero;
	//*************************************************
        /**
	 * <pre>
	 * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
	 * Questo metodo crea un sommario delle restrizioni selezionate dall'utente.
	 * Ritorna un oggetto ti tipo nodoSummaryEXT.
	 * </font></font></p></pre>
	 * @see nodoSummaryEXT
	 */
        public nodoSummaryEXT creaAlbero()
    	{
            nodoSummaryEXT radice,figlio1,figlio2;
            SampleData sd;
            ImageIcon icon;
			
            radice = new nodoSummaryEXT(new SampleData( staticLib.fontA10,cRamo1,"Restriction"));
            
            if (size()>0)
            {
                //ciclo tutte le restrizioni
                elementoBase tempEB;
                String valori="";
                    
                for(int i=0;i<size();i++)
                {
                    tempEB=(elementoBase)elementAt(i);
                    sd=new SampleData( staticLib.fontA10,cRamo2,tempEB.teDesc);

                    figlio1=new nodoSummaryEXT(sd);
                    if(tempEB.isRestriction)
                    {
                        Vector v=tempEB.valueLabel; 
                        String str;
                    	valori=valori+"[";      
                        if (v.size()>0) 
            	        {
                            for(int j=0;j<v.size();j++)
                            {
                                str=(String)v.elementAt(j);
                              	//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx: "+str);
                                if(j==0)
                                    valori=valori+" "+staticLib.operator[tempEB.op]+str;
                                else
                            	    valori=valori+"; "+staticLib.operator[tempEB.op]+str;
                            }
                            valori=valori+" ]";
                        }
                        else
                            valori=valori+" null ]";  
								
                        sd=new SampleData( staticLib.fontA10,cRamo2,valori);
                    }
                    else
                    {
                        Vector v=tempEB.value;
                        Vector v1=tempEB.valueLabel;  
                        String intVal;
                   	valori=valori+"[";      
                	if (v.size()>0) 
                        {
                            String desc=(String)v1.elementAt(0);
                            //System.out.println("Sono qua........: "+desc.trim());
                            if(desc.trim().equals("OCO")||desc.trim().equals("OCE"))
                            {
                                valori=valori+" "+staticLib.operator[tempEB.op]+staticLib.OCO+" ]";	
                            }
                            else
                            {
                                for(int j=0;j<v.size();j++)
                                {
                                    intVal=(String)v.elementAt(j);											
                                    //if(intVal.intValue()!=null)
                                    if(intVal!=null)
                                        if(j==0)
                                            valori=valori+" "+staticLib.operator[tempEB.op]+intVal;
                                        else
                                            valori=valori+"; "+staticLib.operator[tempEB.op]+intVal;
                                }
                                valori=valori+" ]";
                            }
                        }
                        else
                            valori=valori+" null ]";  
                        sd=new SampleData( staticLib.fontA10,cRamo2,valori);
                    }
                    icon=new ImageIcon(getClass().getResource("/images/1847.gif"));
                    sd.setIcon(null,null,icon,icon);
                    figlio2=new nodoSummaryEXT(sd);

                    figlio1.add(figlio2);
                    radice.add(figlio1); 
                    valori="";   
                }   
            }
            else
            {
                //System.out.println("Restriction NULL");
                sd=new SampleData( staticLib.fontA10,cRamo2,"Not selected restriction ");
                icon=new ImageIcon(getClass().getResource("/images/1854.gif"));
                sd.setIcon(null,null,icon,icon);
                figlio1=new nodoSummaryEXT(sd);
                radice.add(figlio1);
            }
            return radice;
    	}
            
	//*************************************************
    	//      creo l'albero2;
    	//*************************************************
    	//summary2.add(creaAlbero());
    	public  DefaultMutableTreeNode creaAlbero1()
    	{
            	DefaultMutableTreeNode radice,figlio1,figlio2;
            
            	SampleData sd=new SampleData( staticLib.fontA10, Color.white,"Restriction",staticLib.fontA10,Color.black,"Restriction");				
		sd.setIcon(null,null,null,null);
		radice=createNewNode(sd);
            
            	if (size()>0)
            	{
                    	//ciclo tutte le restrizioni
                    	elementoBase tempEB;
                    
                    
                    	for(int i=0;i<size();i++)
                    	{
                            	tempEB=(elementoBase)elementAt(i);
                            	figlio1=createNewNode(tempEB.teDesc,Color.white,Color.white);
				if(tempEB.isRestriction)
				{
                            	
					Vector v=tempEB.valueLabel; 
	                            	if (v.size()>0) 
   		                        {
						for(int j=0;j<v.size();j++)
                                        	{
    	                                		//System.out.println("+++++++++++Sono qua: "+(String)v.elementAt(j));
							figlio2=createNewNode(staticLib.operator[tempEB.op]+(String)v.elementAt(j),Color.white,Color.white);
	    	                                	figlio1.add(figlio2);
            	                        	}
                            		}
				}
				else
				{
					Vector v=tempEB.value;
					Vector v1=tempEB.valueLabel; 
	                            	if (v.size()>0) 
   		                        {
						for(int j=0;j<v.size();j++)
                                        	{
    	                                		String desc=(String)v1.elementAt(j);
							//System.out.println("------------Sono qua: "+(String)v1.elementAt(j));
							if(desc.trim().equals("OCO")||desc.trim().equals("OCE"))
							{
								figlio2=createNewNode(staticLib.operator[tempEB.op]+staticLib.OCO,Color.white,Color.white);
							}
							else
							{
								figlio2=createNewNode(staticLib.operator[tempEB.op]+(String)v.elementAt(j),Color.white,Color.white);
							}
	    	                                	figlio1.add(figlio2);
            	                        	}
                            		}
				}
                        	radice.add(figlio1);    
			}
                    
		}
		else
           	{
                    	//System.out.println("Restriction NULL");
                    	figlio1=createNewNode("Not selected restriction",Color.white,Color.white);
                    	radice.add(figlio1);
            	}
		return radice;
    	} 
	
	protected DefaultMutableTreeNode createNewNode(String name) 
	{
		return new nodoSummaryEXT( new SampleData(staticLib.fontA10, Color.black, name) );
    	}
	
	protected DefaultMutableTreeNode createNewNode(String name,Color c1,Color c2) 
	{
		return new nodoSummaryEXT( new SampleData(staticLib.fontA10 , c1, name,staticLib.fontA10,c2,name) );
    	} 
	
	protected DefaultMutableTreeNode createNewNode(SampleData sd) 
	{
		return new nodoSummaryEXT(sd);
    	}
	
        
        /**
         * Metodo che permette di trasformare una stringa in un array di caratteri.
         *@param str Stringa il cui contenuto sarà copiato nell'array di char.
         *@param length dimensione dell'array di char.
         *@return Un'array di char dalle dimensione passata "length" contenente i caratteri della stringa passata in input "str".
         */ 
	public static char[] set_String_toChar(String str, int length)
    	{
    
    		char[] appo = str.toCharArray();
    		char[] appo1 = new char[length];
    
    		if (appo.length > length)
    	    		return appo1;
    
    
    		for (int i=0; i<appo.length; i++)
    	    		appo1[i] = appo[i];
    
    		for (int i=appo.length; i<length; i++) 
    	    		appo1[i] =0;
    
    		return(appo1);
    	}      
}
