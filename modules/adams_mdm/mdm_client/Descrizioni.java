/**
 * Classe Relation.class
 * Questa classe implementa la definizione e i metodi per la gestione delle relazioni
 * tra gli elementi di traffico.
 *
 * @author  Luca Beltrame & Raffaele Ficcadenti 
 * @version 
 */


 
import java.util.*;

public class Descrizioni
{
	
	
	public DATA_DESC[] localDescrizioni;
	
	
	public Descrizioni(DATA_DESC[] appo)
	{
		this.localDescrizioni=appo;
	}
	
	protected void finalize(){}
	
	
	public int num_Descrizioni()
	{
		try
		{
			return localDescrizioni.length;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public String[] get_Descrizioni_conId(int idDesc)
	{
		
		/*
		StringTokenizer token;
                token=new StringTokenizer(str,"/");
                int i=0;
                int[] data=new int[3];
                while (token.hasMoreTokens())
                {
                        data[i]=(Integer.valueOf(token.nextToken())).intValue();
                        i++;
                }
		*/

		DATA_DESC appo=new DATA_DESC();
		Vector v=new Vector();
		StringTokenizer token;
		boolean valido=true;
		String appoSTR,appoSTR2;

		for(int i=0;i<num_Descrizioni();i++)
		{
			appo=(DATA_DESC)localDescrizioni[i];
			//////System.out.println("Cerco: "+appo.idDataElement);
			if (appo.idDataElement == idDesc)
			{
				//////System.out.println("TROVATO");
				for(int j=0;j<appo.Descrizioni.length;j++)
				{
					String temp=new String(appo.Descrizioni[j]).trim();
					token=new StringTokenizer(temp," ");
					
					valido=true;

					while (token.hasMoreTokens())
	                {
                        appoSTR=token.nextToken();
						if(appoSTR.equals("Unknown")||appoSTR.equals("NOT")||appoSTR.equals("Non"))
						{	
							valido=false;
							break;
						}	
    	            }

					token=new StringTokenizer(temp," ");
					
					if((token.hasMoreTokens()))
					{
	                    appoSTR=token.nextToken();
						
						if(appoSTR.equals("N^"))
						{	
							appoSTR2=""; 
							while (token.hasMoreTokens())
							{
								appoSTR2=appoSTR2+token.nextToken()+"  ";
							}
							temp=appoSTR2;
	    	            }	
					}

					if(!temp.equals("")&&valido)
					{
						v.addElement(temp);	
					}
				}
				//////System.out.println("V.Size: "+v.size());
				if(v.size()>0)
				{
					String[] s=new String[v.size()];
					for(int j=0;j<v.size();j++)
				    {
						s[j]=(String)v.elementAt(j);
					}
					return s;
				}else
					return null;
			}
		}
		return null;
	}

}
