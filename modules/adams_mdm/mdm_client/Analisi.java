/**
 *
 * @author  Luca Beltrame & Raffaele Ficcadenti 
 * @version 
 */

import java.util.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class Analisi
{
	// Insieme delle analisi che mi arriva dal MS
	public DATA_ANALYSISTYPE[] localAnalisi;
	
	
	public Analisi(DATA_ANALYSISTYPE[] appo)
	{
		this.localAnalisi=appo;
	}
	
	public int num_Analisi()
	{
		return localAnalisi.length;
	}
	
	public DATA_ANALYSISTYPE get_Analisi_conId(int idAnalisi)
	{
		DATA_ANALYSISTYPE appo=new DATA_ANALYSISTYPE();
		for(int i=0;i<num_Analisi();i++)
		{
			appo=(DATA_ANALYSISTYPE)localAnalisi[i];
			if (appo.idAnalysis == idAnalisi)
				break;
		}
		return appo;
	}
        
        public int getPOS_Analisi_conId(int idAnalisi)
	{
		DATA_ANALYSISTYPE appo=new DATA_ANALYSISTYPE();
		for(int i=0;i<num_Analisi();i++)
		{
			appo=(DATA_ANALYSISTYPE)localAnalisi[i];
			if (appo.idAnalysis == idAnalisi)
				return i;
		}
		return -1;
	}
        
	
	public int get_idAnalisi(String desc)
	{
		DATA_ANALYSISTYPE appo=new DATA_ANALYSISTYPE();
		for(int i=0;i<num_Analisi();i++)
		{
			appo=(DATA_ANALYSISTYPE)localAnalisi[i];
			if (new String(appo.LongDescription).trim().equals(desc))
			{
				return appo.idAnalysis;
			}
		}
		return -1;
	}
	
	public boolean get_FlagSort(String desc)
	{
		DATA_ANALYSISTYPE appo=new DATA_ANALYSISTYPE();
		for(int i=0;i<num_Analisi();i++)
		{
			appo=(DATA_ANALYSISTYPE)localAnalisi[i];
			if (new String(appo.LongDescription).trim().equals(desc))
			{
				//System.out.println("DATA_ANALYSISTYPE: "+new String(appo.LongDescription).trim()+"    FlagSort: "+appo.FlagSort);
				return appo.FlagSort;
			}
		}
		return false;
	}
	
	public int[] get_relationList(int idAnalisi)
	{
		return(get_Analisi_conId(idAnalisi).relations);
	}
	       
        public String get_LongDescription(int idAnalisi)
	{
		return(new String(get_Analisi_conId(idAnalisi).LongDescription).trim());
	}

	public String get_ShortDescription(int idAnalisi)
	{
		return(new String(get_Analisi_conId(idAnalisi).ShortDescription).trim());
	}
	
	public String get_AnalisiLongDesc(int pos)
	{
		return( new String(localAnalisi[pos].LongDescription).trim());
	}
	
	public int get_countersKitTag(int idAnalisi)
	{
		return get_Analisi_conId(idAnalisi).countersKitId;	
	}
	
	public int[] getReportsId(int idAnalisi)
	{
		return get_Analisi_conId(idAnalisi).reportsId;	
	}
	
}
