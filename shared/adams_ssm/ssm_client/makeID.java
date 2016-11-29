import java.util.*;

/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Luca Beltrame <luca.beltrame@e-tech.net>                                                                                                           
#                                                                                                                                                 
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory                                                                    
#                                                                                                                                                 
#  HISTORY:                                                                                                                                       
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/


/* 
 * <p align="center"> <b> Class: makeID </b> </p>
 *
 * Classe che estende un vettore, questa classe viene utilizzata per la generazione di indici univoci.
 *
 *<p align="center">&nbsp;</p>     
 */
class makeID
{
	private Vector index	= null;
        
        private int MIN_ID	= 0;
 	private int MAX_ID	= 9999;
        private String IDENTIFICATION = "";
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Costruttore della classe: istanzia un vettore contenete tutti gli indici generati.
	  * N.B.: gli indigi generati sono univoci.
     	  * </font></font></p> 	
     	  */
	public makeID()
	{
            index=new Vector();
	}
	
        public makeID(int min_id,int max_id)
	{
            index=new Vector();
            MIN_ID = min_id;
            MAX_ID = max_id;
	}
        
        public makeID(String identification, int min_id,int max_id)
	{
            index=new Vector();
            MIN_ID = min_id;
            MAX_ID = max_id;
            this.IDENTIFICATION = identification;
	}

        public String getIdentification()
        {
            return this.IDENTIFICATION;
        }
        public void setIdentification(String identification)
        {
            this.IDENTIFICATION = identification;
        }
        
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Restituisce il numero degli indici generati.
	  * @retrun numero di indici.
     	  * </font></font></p> 	
     	  */
	public int numIndex()
	{
            return index.size();
	}
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Contralla se un idice Ã¨ giÃ  stato generato.
	  * @param id valore dell'indice da controllare.
	  * @retrun ritorna l'indice generato
     	  * </font></font></p> 	
     	  */
	public Integer isPresent(int id)
	{
            // System.out.println("makeID numIndex "+numIndex());
             for(int i=0;i<numIndex();i++)
             {
                Integer appo=(Integer)index.elementAt(i);
                if(appo.intValue()==id)
                        return appo;
             }
             return null;
	}
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Aggiunge un indice al vettore.
	  * @param i valore dell'indice da inserire.
     	  * </font></font></p> 	
     	  */
	public void addIndex(int i)
	{
            if( i< MIN_ID)
            {
                System.out.println("addIndex "+i+" < "+MIN_ID+" --> generaindex() Automatic");
                generaIndex();
                return;
            }
            
            if( i >MAX_ID)
            {
                System.out.println("addIndex "+i+" > "+MAX_ID+" --> generaindex() Automatic");
                generaIndex();
                return;
            }
            
            if(isPresent(i) != null)
            {
                return;
            }
            else
            {
                index.addElement(new Integer(i));	
            }
	}
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Rimuove tutti gli indici dal vettore.
     	  * </font></font></p> 	
     	  */
	public void reset() 
	{
            index.removeAllElements();
	}
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Calcola il valore massimo tra gli indici
	  * @return indice con valore massimo.
     	  * </font></font></p> 	
     	  */
	private int getMax()
	{
            if(numIndex()==0)
                return 0;

            int max=((Integer)index.elementAt(0)).intValue();

            for(int i=0;i<numIndex();i++)
            {
                Integer appo=(Integer)index.elementAt(i);
                if(appo.intValue()>max)
                    max=appo.intValue();
            }
            return max;
		
	}
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Ordina il vettore degli indici.
     	  * </font></font></p> 	
     	  */
	public boolean ordinaIndici()
	{
            int len=numIndex();
            for(int i=0;i<len-1;i++)
            {
                int min=i;
                for(int j=i+1;j<len;j++)
                    if(getIndex(j)<getIndex(min))
                        min=j;	

                int appo=getIndex(i);
                int appo1=getIndex(min);

                index.removeElementAt(i);		
                index.insertElementAt(new Integer(appo1),i);
                index.removeElementAt(min);
                index.insertElementAt(new Integer(appo),min);
            }

            return true;
	}

	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Genera un nuovo indice univoco.
	  * N.B.: questo metodo non inserisce l'indice generato nel vettore
     	  * </font></font></p> 	
     	  */
	private int _generaIndice()
	{
            ordinaIndici();
            int len=numIndex();
            if(len == 0)
                  return MIN_ID;
                                   
            if(getIndex(0) != MIN_ID)
                return MIN_ID;
            
            if(MIN_ID-getIndex(0) > 1)
                return getIndex(0)+MIN_ID -1;	
            
            for(int i=0;i<len-1;i++)
            {	
                if(getIndex(i+1)-getIndex(i) > 1)
                {			
                    return getIndex(i)+1;        	                
                }
            }
            if(getIndex(len-1) < MAX_ID)
                return getIndex(len-1)+1;
            else	
                return -1;		
	}
	        
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Genera un nuovo indice univoco.
	  * N.B.: questo metodo inserisce l'indice generato nel vettore
     	  * </font></font></p> 	
     	  */
	public int generaIndex()
	{
            int appo=_generaIndice();
            if(appo!=-1)
            {
                    addIndex(appo);
                    return appo;
            }else
                    return -1;
	}	
	
	private int generaIndex2()
	{
            int appo = getMax()+1;
            addIndex(appo);
            return appo;
	}
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Preleva dal vettore l'indice presente alla posizione specificata.
	  * @param pos posizione dell'indice all'interno del vettore.
     	  * </font></font></p> 	
     	  */
	public int getIndex(int pos)
	{
            return ((Integer)index.elementAt(pos)).intValue();
	}
	
	
	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Cancella dal vettore l'indice specificato.
	  * @param id indice da cancellare.
     	  * </font></font></p> 	
     	  */
        public boolean deleteIndex(int id)
        {
            Integer appo = isPresent(id);
            if (appo != null)
                return index.removeElement(appo);
            else
                return false;
        }

	/**
     	  * <p align="left"><font size="2"><font size="2" face="Times New Roman, Times, serif">
	  * Stampa gli indici generati.
     	  * </font></font></p> 	
     	  */
	public void stampaIndex()
	{
            System.out.println(" ******** make Id ********");
            System.out.println("IDENTIFICATION: '"+IDENTIFICATION+"' ");
            for(int i=0;i<numIndex();i++)
            {
                System.out.println(" -> ID: "+(Integer)index.elementAt(i));
            }
            System.out.println(" ******** ");
	}
        
}
