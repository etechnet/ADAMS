import java.util.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class Counters
{
	// Insieme dei Contatori
	public COUNTERS[] localCcounter;
		
	public Counters(COUNTERS[] cont)
	{
		this.localCcounter=cont;
	}
	
	public int num_Counters()
	{
		return localCcounter.length;
	}
	//public COUNTER_KIT[] counterKit;
	
        public int getIdSort(int idCounterKit,String contatore) //Raffo 25-10-2006: anomalia su id contatori
        {
                //System.out.println("Cerco: "+contatore);
		for(int i=0;i<num_Counters();i++)
		{
			String tag=(new String(localCcounter[i].tag)).trim();
			
			//System.out.println("Counter.sort TAG: "+tag+" ID: "+localCcounter[i].idCounter);
			if(idCounterKit==localCcounter[i].idCounter)
			{
				// Ho trovato il Counter giusto, prenso la seguenza di counterKit;
				for(int j=0;j<localCcounter[i].counterKit.length;j++)
				{
					String appo1=(new String(localCcounter[i].counterKit[j].description)).trim();
					String tag1=(new String(localCcounter[i].counterKit[j].tag)).trim();
					if(!tag1.equals(""))
					{
                                            if(appo1.equals(contatore))
                                            {
                                                return localCcounter[i].counterKit[j].counterIndex;
                                            }
					}
				}
				
			}
		}
		
		return -1;
        }
        
        public String getDicSort(int idCounterKit,int idContatore) //Raffo 25-10-2006: anomalia su id contatori
        {
		for(int i=0;i<num_Counters();i++)
		{
			String tag=(new String(localCcounter[i].tag)).trim();
			
			//System.out.println("Counter.sort TAG: "+tag+" ID: "+idContatore);
			if(idCounterKit==localCcounter[i].idCounter)
			{
				// Ho trovato il Counter giusto, prenso la seguenza di counterKit;
				for(int j=0;j<localCcounter[i].counterKit.length;j++)
				{
					int appo1=localCcounter[i].counterKit[j].counterIndex;
					String tag1=(new String(localCcounter[i].counterKit[j].tag)).trim();
					if(!tag1.equals(""))
					{
                                            if(appo1==idContatore)
                                            {
                                                return (new String(localCcounter[i].counterKit[j].description)).trim();
                                            }
					}
				}
				
			}
		}
		
		return "None";
        }
        
	public Vector getDicitureSort(int idCounter)
	{
		Vector tmp=new Vector();
		for(int i=0;i<num_Counters();i++)
		{
			String tag=(new String(localCcounter[i].tag)).trim();
			
			//System.out.println("Counter.sort TAG: "+tag+" ID: "+localCcounter[i].idCounter);
			if(idCounter==localCcounter[i].idCounter)
			{
				// Ho trovato il Counter giusto, prenso la seguenza di counterKit;
				for(int j=0;j<localCcounter[i].counterKit.length;j++)
				{
					String appo1=(new String(localCcounter[i].counterKit[j].description)).trim();
					String tag1=(new String(localCcounter[i].counterKit[j].tag)).trim();
					if(!tag1.equals(""))
					{
						//System.out.println("TAG: ["+ tag1+"]   DESCRIZIONE: "+appo1+"  ID: "+localCcounter[i].counterKit[j].counterIndex);
						tmp.addElement(appo1);
					}
				}
				
			}
		}
		
		return tmp;
	}
	
	public String getDicituraSort(int idAnalisi,int idSort)
	{
		String tmp=null;
		for(int i=0;i<num_Counters();i++)
		{
			String tag=(new String(localCcounter[i].tag)).trim();
			
			//System.out.println("Counter TAG: "+tag+" ID: "+localCcounter[i].idCounter);
			if(idAnalisi==localCcounter[i].idCounter)
			{
				// Ho trovato il Counter giusto, prenso la seguenza di counterKit;
				String appo1=(new String(localCcounter[i].counterKit[idSort].description)).trim();
				return appo1;	
			}
		}
		
		return tmp;
	}
	
}
