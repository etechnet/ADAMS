import java.util.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class Eccezioni
{
	// Insieme delle Eccezioni
	
	public DATA_EXCEPTIONS[] localEccezioni;
	
	
	/*
	public int idException;
    	public char[] tag;
    	public char[] description;
    	public int idTriggerRestriction;
    	public boolean triggeredStatus;
    	public boolean action;
    	public int idAggregateException;
	*/
		
	public Eccezioni(DATA_EXCEPTIONS[] ecc)
	{
		this.localEccezioni=ecc;
	}
	
	public int num_Eccezioni()
	{
		return localEccezioni.length;
	}
	
	public boolean isAgregate(int idEcc)
	{
		for(int i=0;i<num_Eccezioni();i++)
		{
			DATA_EXCEPTIONS ecc=(DATA_EXCEPTIONS)localEccezioni[i];
			if(ecc.idAggregateException==idEcc)
			{
				return true;
			}
		}
		return false;
	}
	
	public Vector getEccezioni(int id[])
	{
		Vector tmp=new Vector();
		
		int j=0;
		
		while(id[j]!=-1)
		{
			for(int i=0;i<num_Eccezioni();i++)
			{
				if(id[j]==localEccezioni[i].idException)
				{
					DATA_EXCEPTIONS ecc=(DATA_EXCEPTIONS)localEccezioni[i];
					tmp.add(ecc);
				}		
			}
			j++;
		}
		
		return tmp;
	}
	
	public Vector getEccezioni(int idEcc)
	{
		Vector tmp=new Vector();
		
		int idNext;
		idNext=idEcc;
		while(idNext>0)
		{
			for(int i=0;i<num_Eccezioni();i++)
			{
				if(idNext==localEccezioni[i].idException)
				{
					DATA_EXCEPTIONS ecc=(DATA_EXCEPTIONS)localEccezioni[i];
					tmp.add(ecc);
					idNext=localEccezioni[i].idAggregateException;
					break;
				}		
			}
		}
		
		return tmp;
	}
	
	public Vector getEccezioni()
	{
		Vector tmp=new Vector();
		
		for(int i=0;i<num_Eccezioni();i++)
		{
			DATA_EXCEPTIONS ecc=(DATA_EXCEPTIONS)localEccezioni[i];
			if(isAgregate(ecc.idException)==false)
			{	
				tmp.add(ecc);
			}	
		}
		
		return tmp;
	}
	
	public DATA_EXCEPTIONS get_Eccezione_conId(int idEcc)
	{
		DATA_EXCEPTIONS appo=null;
		for(int i=0;i<num_Eccezioni();i++)
		{
			appo=(DATA_EXCEPTIONS)localEccezioni[i];
			if (appo.idException == idEcc)
				break;
		}
		return appo;
	}
	
	public String getTagException(int idEcc)
	{
		DATA_EXCEPTIONS appo=get_Eccezione_conId(idEcc);
		if(appo!=null)
		{
			return (new String(appo.tag)).trim();	
		}
		else
		{
			return "";
		}	
	}
	
	public String getDescriptionException(int idEcc)
	{
		DATA_EXCEPTIONS appo=get_Eccezione_conId(idEcc);
		if(appo!=null)
		{
			return (new String(appo.description)).trim();	
		}
		else
		{
			return "";
		}	
	}
	
	public int getIdAggregateException(int idEcc)
	{
		DATA_EXCEPTIONS appo=get_Eccezione_conId(idEcc);
		if(appo!=null)
		{
			return appo.idAggregateException;	
		}
		else
		{
			return -1;
		}	
	}
	
	public int getIdTriggerRestriction(int idEcc)
	{
		DATA_EXCEPTIONS appo=get_Eccezione_conId(idEcc);
		if(appo!=null)
		{
			return appo.idTriggerRestriction;	
		}
		else
		{
			return -1;
		}	
	}
	
	public int getTriggeredStatus(int idEcc)
	{
		DATA_EXCEPTIONS appo=get_Eccezione_conId(idEcc);
		if(appo!=null)
		{
			return appo.triggeredStatus;	
		}
		else
		{
			return -1;
		}	
	}
	
	public int getAction(int idEcc)
	{
		DATA_EXCEPTIONS appo=get_Eccezione_conId(idEcc);
		if(appo!=null)
		{
			return appo.action;	
		}
		else
		{
			return -1;
		}	
	}

}
