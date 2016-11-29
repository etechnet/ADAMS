package net.etech.loadconfig;
/*
 * ADAMS_TAB_EXTERNAL.java
 *
 * Created on 10 giugno 2005, 10.09
 */

/**
 *
 * @author  Raffo
 * @version 
 */

import java.util.Vector;
import java.sql.*;

public class ADAMS_TAB_EXTERNAL 
{
	public static final int EXTERNAL_CENTRALI	= 1;
	private Vector vCENTRALI			= null;
	
	public void readCentrali()
	{
		
		String[] nomeCampi      = {"IND_NODE","FLAG_ATT","PFX_NODE","NODE","NAME_NODE","SUF_NODE"};
		String tableConfig      = "tab_inputnode";
		String sel_ConfigNtm 	= "select ";
		
		vCENTRALI 		= new Vector();
		
		for(int i=0;i<nomeCampi.length;i++)
		{
			if(i!=nomeCampi.length-1)
			{
				sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+",";
			}
			else
			{
				sel_ConfigNtm = sel_ConfigNtm+nomeCampi[i]+" ";  
			}
		}
		sel_ConfigNtm=sel_ConfigNtm+" from " + tableConfig;
		sel_ConfigNtm=sel_ConfigNtm+" ORDER BY NODE ";
		
		if(ADAMS_GlobalParam.DEBUG)
			System.out.println("sel ----> "+sel_ConfigNtm);
		
		Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
		java.sql.ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        
		if(rs != null)
	        {
	            try
	            {
	                while ( rs.next ( ) ) 
	                {
	                	TAB_CENTRALI_ROW rowCENTRALI=new TAB_CENTRALI_ROW();    
	                	
				rowCENTRALI.IND_CEN=rs.getInt("IND_NODE");		
				rowCENTRALI.FLAG_ATT=rs.getInt("FLAG_ATT");	
				rowCENTRALI.PFX_CEN=rs.getString("PFX_NODE");		
				rowCENTRALI.CENT=rs.getString("NODE");	
				rowCENTRALI.CITTA=rs.getString("NAME_NODE");		
				rowCENTRALI.SUF=rs.getString("SUF_NODE");;		
		                
	                   	vCENTRALI.addElement(rowCENTRALI);	
	                }
	            }catch (Exception ex) 
	            {
	                System.out.println("ADAMS_TAB_EXTERNAL::readCentrali(1) SQL Operation " +ex);
	            }  
	        }else
	        {
	            System.out.println("rs==null");
	        }
        
	        try
	        {
	            SQLStatement.close();
	        }
	        catch(java.sql.SQLException exc) 
	        {
	            System.out.println("ADAMS_TAB_EXTERNAL::readCentrali(2) SQL Operation " + exc.toString());
	        }		
	}
	
	public Vector getCentrali()
	{
		return vCENTRALI;
	}
	
	public int numCentraliAttive()
	{
		int len=0;
		
		for(int i=0;i<vCENTRALI.size();i++)
		{
			TAB_CENTRALI_ROW appo=(TAB_CENTRALI_ROW)vCENTRALI.elementAt(i);
			
			if(appo.FLAG_ATT==1) // considero solo le centrali attive.
			{
				len++;
			}
		}
		
		return len;
	}
	
	
	public int numCentrali()
	{
		return vCENTRALI.size();
	}
	
	public void stampaCentrali()
	{
		for(int i=0;i<numCentrali();i++)
		{
			TAB_CENTRALI_ROW appo=(TAB_CENTRALI_ROW)vCENTRALI.elementAt(i);
			
			System.out.println("IND_CEN="+appo.IND_CEN);
			System.out.println("FLAG_ATT="+appo.FLAG_ATT);
			System.out.println("PFX_CEN="+appo.PFX_CEN);
			System.out.println("CENT="+appo.CENT);
			System.out.println("CITTA="+appo.CITTA);
			System.out.println("SUF="+appo.SUF);
			System.out.println("CEN_CMT="+appo.CEN_CMT);
			System.out.println();
			
		}
	}
	
	public class TAB_CENTRALI_ROW
	{
		public TAB_CENTRALI_ROW()
		{
			CODN7_NAZ	= -1;
			CODN7_INT	= -1;
			IND_CEN		= -1;
			FLAG_ATT	= -1;
			PFX_CEN		= new String();
			CENT		= new String();
			CITTA		= new String();
			SUF		= new String();
			TIPO_CEN	= new String();
			CEN_CMT		= new String();
		}
    
   
		public int CODN7_NAZ;
		public int CODN7_INT;
		public int IND_CEN;
		public int FLAG_ATT;
		public String PFX_CEN;
		public String CENT;
		public String CITTA;
		public String SUF;
		public String TIPO_CEN;
		public String CEN_CMT;
    
		//Tutti i campi della tabella tab_centrali

		private String itemName  = "CODN7_NAZ,CODN7_INT,IND_CEN,FLAG_ATT,PFX_CEN,CENT,CITTA,SUF,TIPO_CEN,CEN_CMT ";
	}
	
}