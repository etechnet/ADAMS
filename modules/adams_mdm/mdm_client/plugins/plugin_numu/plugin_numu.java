import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.sql.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.SSM.*;
import net.etech.loadconfig.*;

public class plugin_numu extends plugin_info implements plugin_base 
{
	private Vector<String> elementHelp = null;
	private pluginInfo plInfo = null;
	private static final boolean DEBUG=false;
	private boolean CONN_ORACLE = false;
	private elementoLista elementoListaDB=null;
	private	elementoLista listaNumeri = null;
	
	private String login="ops$rdaexec";
	private String nodo="RDA";
	private String type_pswd="ORACLE";
	
	private String loginPL="pluginnumu";
	private String nodoPL="RDA";
	private String type_pswdPL="TYPE1";
	
	private String password="";
    
    	// CONNESSIONE ORACLE
	private static NTM_OracleConnection connect_Oracle = null;
    	private String oracle_host="";
    	
    	private JFrame 			jFramePadre = null;
    	private BufferRestrizioni 	br = null;
	private DATA_DATAELEMENT  	te = null;
    	
    	// Per formattazione DateTime
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public plugin_numu() 
	{		
		plInfo=new pluginInfo();
		
		plInfo.globalTypeID = NTM_CLIENT_PLUGIN;
		plInfo.name = "plugin_numu.class";
		plInfo.description = "Crea una lista di numerazioni utente per NTM";
		plInfo.majorVersionNumber = 1;
		plInfo.minorVersionNumber = 0;
		try {
			plInfo.releaseDate = df.parse("19/01/2012 15:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		plInfo.vendorName = "e-Tech s.r.l.";
		plInfo.author = "Raffaele Ficcadenti <rficcad@e-tech.net>";
	}
	    
	public void pluginSetupConfig(Object o1,Object o2,Object o3)
	{
		jFramePadre = (JFrame)o1;
    		br = (BufferRestrizioni)o2;
	  	te = (DATA_DATAELEMENT)o3;
	
		if(DEBUG) System.out.println("plugin_numu::pluginSetupConfig() START");
		
		//oracle_SID="DBRDAT"; 	//oracle_SID 	= this.getParameter("oracle_SID");
		//oracle_port="1531";  	//oracle_port 	= this.getParameter("oracle_port");
		oracle_host="rda";	//oracle_host		= this.getParameter("machine_name");
		
		password = staticLib.CORBA.getPswd(nodo,type_pswd,login); 
		if(DEBUG)
		{ 
		    	System.out.println("Login	= "+login);   
		    	System.out.println("Pswd	= "+password); 
		    	System.out.println("oracle_SID	= "+staticLib.oracle_SID);   
		    	System.out.println("oracle_port	= "+staticLib.oracle_port); 
		    	System.out.println("oracle_host	= "+oracle_host);
		}
		connect_Oracle = new NTM_OracleConnection();
		connect_Oracle.setupParam(staticLib.oracle_SID,staticLib.oracle_port,oracle_host);
		
		if(DEBUG) System.out.println("plugin_numu::pluginSetupConfig() STOP"); 
	}

	private int delete_T_NTM_NUMU()
    	{                        
		int Answer_del = 0;    
		String str_delete_T_NTM_NUMU = "delete from T_NTM_NUMU";
		
		try
		{ 
			connect_Oracle.DBConnection.setAutoCommit(false);
			
			Answer_del = connect_Oracle.Operation(str_delete_T_NTM_NUMU);
			
			if(Answer_del >= 0)
			{
				connect_Oracle.DBConnection.commit();
			}
			
			connect_Oracle.DBConnection.setAutoCommit(true);
		}
		catch(java.sql.SQLException exc)
		{
			System.out.println("Errore delete_T_NTM_NUMU.");
			try
			{ 
				connect_Oracle.DBConnection.setAutoCommit(true);
			}
			catch(java.sql.SQLException exc1)
			{
				System.out.println("Errore ORACLE.setAutoCommit(true).");
				
			}  
		}      
		return Answer_del;
    	}
    	
    	public void addDataElement()
    	{
    		/* devo controllare se già esiste */
    		
    		if(listaNumeri==null)
    		{
    			br.removeElem(te.idElement);
    			System.out.println("La BlackList è vuota.");
    			return;
    			
    		}
    		
    		if(listaNumeri.size()==0)
    		{
    			br.removeElem(te.idElement);
    			System.out.println("La BlackList è vuota.");
    			return;
    			
    		}
    		
    		if(br.isPresentEB(te.idElement)==null)
    		{
    			br.addRestriction(te.idElement,
	                                new String(te.longDescription).trim(),
	                                "",
	                                "Plugin managed",
	                                true,
	                                0,
	                                te.priority,te.exceptions[0]);
		}
		else
		{
			System.out.println("Restrizione presente.");
		}
		
	}
	public boolean pluginWorker() 
	{
		
		String strUserPswd=null;
		if(DEBUG) System.out.println("plugin_numu::pluginWorker() START");
		
	                                
		/* Verifica password */
	
		try {
			JD_pswd dialog = new JD_pswd(null,2);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.open_dialog();
			strUserPswd=dialog.getPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(strUserPswd=="") // esco senza password
		{
			return true;
		}else // tento autenticazione password
		{
			String passwordPL = staticLib.CORBA.getPswd(nodoPL,type_pswdPL,loginPL); 
			if(DEBUG)
			{ 
			    	System.out.println("Login	= "+loginPL);   
			    	System.out.println("Pswd Plugin	= "+passwordPL); 
			    	System.out.println("Pswd User	= "+strUserPswd); 
			}
			if( !passwordPL.equals(strUserPswd) )
			{
				JD_Message op = new JD_Message(null,true,"Wrong Password.","Info",JD_Message.INFO);
				//addDataElement();
				return false;
			}
		}
		
		/* inizio lavoro plugin */
		CONN_ORACLE = connect_Oracle.DBConnect(login,password);
		
		if(DEBUG)
		{
			System.out.println("	CONN_ORACLE="+CONN_ORACLE);
			System.out.println("	Inizio gestione ORACLE");
		}
		
		try {
			
			String strSelect = "select NUM_TYPE,CC,AC,NUMU from T_NTM_NUMU order by 1";
			
			Statement SQLStatement = connect_Oracle.createLocalStatement();
			ResultSet rs  = connect_Oracle.Query_RS_Statement(strSelect,SQLStatement);
			
			if(rs != null)
			{
				elementoListaDB=new elementoLista();                 
				try
				{
				    while ( rs.next ( ) ) 
				    {    
				    	int num_type = rs.getInt("NUM_TYPE");                        
				        String cc = rs.getString("CC");
				        String ac = rs.getString("AC");
				        String numu = rs.getString("NUMU");
				        elementoListaDB.addElement(num_type,cc,ac,numu);   
				    }
				}catch (Exception ex) 
				{ 
				    System.out.println(ex.toString());
				}
			}
			
			try
			{
				SQLStatement.close();
			} 
			catch(java.sql.SQLException exc) 
			{
				System.out.println("T_NTM_NUMU - SQL Operation " + exc.toString());
			}
                    
			JD_plugin_numu dialog = new JD_plugin_numu(jFramePadre,this,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setElementi(elementoListaDB);
			dialog.open_dialog();
			
			if(dialog.isCommit()==0)
			{
				System.out.println("NON ci sono state modifiche.");
				addDataElement();
				return true;
			}
			
			int iRetDel = delete_T_NTM_NUMU();
			
			if(iRetDel>=0)
			{
				listaNumeri = dialog.getElementi();
				
				if(listaNumeri.size()>0)
				{
					String strInsert="INSERT ALL ";
					for(int i=0;i<listaNumeri.size();i++)
					{
						//strInsert=strInsert+"INTO T_NTM_NUMU(CC) VALUES('"+listaNumeri.elementAt(i).getCC()+"') ";
						strInsert=strInsert+"INTO T_NTM_NUMU(NUM_TYPE,CC,AC,NUMU) VALUES('"+listaNumeri.elementAt(i).getNumType()+"','"+listaNumeri.elementAt(i).getCC()+"','"+listaNumeri.elementAt(i).getAC()+"','"+listaNumeri.elementAt(i).getNUMU()+"') ";
						
					}
					strInsert=strInsert+" select * from DUAL"; 
					
					if(DEBUG)
					{	
						System.out.println("Insert="+strInsert);
					}
					
					try
				        {        
				            connect_Oracle.DBConnection.setAutoCommit(false); 
				        }
				        catch(java.sql.SQLException exc){
				        	System.out.println("Errore ORACLE.setAutoCommit(false).");
				        }  
				        
				        int iRetIns = connect_Oracle.Operation(strInsert);
				        if(iRetIns >= 0)
			                {                
			                    connect_Oracle.DBConnection.commit();
			                }
			                else
			                {
			                    connect_Oracle.DBConnection.rollback();
			                }
	                
				        
				        try
			                { 
			                    connect_Oracle.DBConnection.setAutoCommit(true);
			                }
			                catch(java.sql.SQLException exc1)
			                {
			                	System.out.println("Errore ORACLE.setAutoCommit(true).");
			                }   
        			}
			}
		}catch (Exception e) {
			System.out.println("Errore Apertura Dialog");
			e.printStackTrace();
		}
			
		
		if(DEBUG) System.out.println("plugin_numu::pluginWorker() STOP");
		addDataElement();
		return true;
	}
	
	public boolean startup()
	{
		return true;		
	}
	
	public boolean shutdown() 
	{
		if((connect_Oracle!=null) && (CONN_ORACLE==true))
		{
			connect_Oracle.Close();
		}
		
		return true;
	}
	
	public boolean verifyType() 
	{
		return true;
	}
	
	public StringBuffer info(int type) 
	{
		return plugin_info.info(plInfo,type);
	}
	
	
}