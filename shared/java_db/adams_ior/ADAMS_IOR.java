package net.etech.loadior;

/*
 *
 * ADAMS_loadConfig.java
 *
 * Created on 30 novembre 2012, 8.25
 */

/**
 *
 * @author  Raffaele
 * @email   raffaele.ficcadenti@e-tech.net
 * Classe utilizzata per la conversione dei dati da ORACLE a ADAMS_COMPLETE_CONFIG.
 *
 */

import java.util.*;
import java.sql.*;

public class ADAMS_IOR {

	private boolean	DB_CONN		= false;
	
    	/** Creates new ADAMS_loadConfig */
	public ADAMS_IOR()
	{
		
			System.out.println("ADAMS_IOR_loadConfig");
			
			if(ADAMS_IOR_GlobalParam.db_connect==null)
			{
				ADAMS_IOR_GlobalParam.db_connect = new ADAMS_IOR_DBConnection();

			}

			DB_CONN = ADAMS_IOR_GlobalParam.db_connect.DBConnect("","","");

			if(DB_CONN == true)
			{
				System.out.println("Connessione Database ==> OK");
			}
			else
			{
				System.out.println("Connessione Database ==> failed");
			}

	}
	
	public String getIor(String processName)
	{
		String ior="";
		String[] nomeCampi      = {"node_id","process_name","ior"};
		String tableConfig      = "t_iors";
		String str_select = "select ";
		
		if(DB_CONN == true)
		{
			for(int i=0;i<nomeCampi.length;i++)
			{
			    if(i!=nomeCampi.length-1)
				str_select = str_select+nomeCampi[i]+",";
			    else
				str_select = str_select+nomeCampi[i]+" ";  
			}
			str_select=str_select+" from " + tableConfig ;
			str_select=str_select+" where ";
			str_select=str_select+" process_name='"+processName+"'";
			
			if(ADAMS_IOR_GlobalParam.DEBUG)
			    System.out.println("getIor() select ----> "+str_select);

			Statement SQLStatement = ADAMS_IOR_GlobalParam.db_connect.createLocalStatement();
			java.sql.ResultSet rs  = ADAMS_IOR_GlobalParam.db_connect.Query_RS(str_select,SQLStatement);
			
			if(rs != null)
			{
			    try
			    {
				while ( rs.next ( ) )
				{
				    ior=new String(rs.getString(nomeCampi[2]));
				    
				    if(ADAMS_IOR_GlobalParam.DEBUG)
				    {
					System.out.println("getIor="+ior);
				    }
				}
			    }catch (Exception ex) 
			    {
				System.out.println(ex);
				ior="";
			    }  
			}else
			{
			    System.out.println("rs == null");
			    ior="";
			}
			
			try
			{
			    SQLStatement.close();
			}
			catch(java.sql.SQLException exc) 
			{
			    System.out.println("ERROR-SQL Operation " + exc.toString());
			}
		}else
		{
			ior="";
		}
		
		return ior;
	}

}

