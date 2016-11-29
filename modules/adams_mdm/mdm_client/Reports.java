import java.util.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class Reports
{
	//Insieme dei Contatori
	public DATA_REPORTSCHEMA[] localReport;
		
	public Reports(DATA_REPORTSCHEMA[] rep)
	{
		this.localReport=rep;
	}
	
	public int num_Reports()
	{
		return localReport.length;
	}
	
	public Reports()
	{
	}
	
	public Vector getDicitureReport(int id[])
	{
		Vector tmp=new Vector();
		
		int j=0;
		//System.out.println("Len: "+id.length);
		/*for(int i=0;i<id.length;i++)
		{
			System.out.println("ID REPORT: "+id[i]);
		}*/
		
		while((id[j]!=-1)&&(id[j]!=0))
		{
			for(int i=0;i<num_Reports();i++)
			{
				if(id[j]==localReport[i].idReportSchema)
				{
					String tag=(new String(localReport[i].tag)).trim();
					tmp.add(tag);
				}		
			}
			j++;
		}
		
		return tmp;
	}
	
	public int get_idReport(String desc)
	{
		DATA_REPORTSCHEMA appo=new DATA_REPORTSCHEMA();
		for(int i=0;i<num_Reports();i++)
		{
			appo=(DATA_REPORTSCHEMA)localReport[i];
			if (new String(appo.tag).trim().equals(desc))
			{
				return appo.idReportSchema;
			}
		}
		return -1;
	}
	
	public String get_DescReport(int idReport)
	{
		DATA_REPORTSCHEMA appo=new DATA_REPORTSCHEMA();
		
		for(int i=0;i<num_Reports();i++)
		{
			appo=(DATA_REPORTSCHEMA)localReport[i];
			if (appo.idReportSchema==idReport)
			{
				return (new String(appo.tag)).trim();
			}
		}
		return null;
	}
	
        public long get_ReportExcel(int idReport)
        {            
            DATA_REPORTSCHEMA appo=new DATA_REPORTSCHEMA();
            for(int i=0;i<num_Reports();i++)
            {
                appo=(DATA_REPORTSCHEMA)localReport[i];
                if (appo.idReportSchema==idReport)
                {
                    //System.out.println("EXCEL --> "+appo.objO.u.excelCSV);
                    return appo.objO.u.excelCSV;
                }
            }
            return 0;
        }
        
        public boolean get_UsePlugin(int idReport)
        {            
            DATA_REPORTSCHEMA appo=new DATA_REPORTSCHEMA();
            for(int i=0;i<num_Reports();i++)
            {
                appo=(DATA_REPORTSCHEMA)localReport[i];
                if (appo.idReportSchema==idReport)
                {
                    return appo.objO.u.usePlugin;
                }
            }
            return false;
        }
        
        
}
