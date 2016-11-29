/*
 * ADAMS_TAB_TYPE_ANALISI.java
 *
 * Created on 16 settembre 2005, 11.42
 */

/**
 *
 * @author  root
 * @version 
 */
import java.util.Vector;
public class ADAMS_TAB_TYPE_ANALISI {

    /** Creates new ADAMS_TAB_TYPE_ANALISI */
    public ADAMS_TAB_TYPE_ANALISI() {
        TIPO_DI_CONFIGURAZIONE  = new String();     //NOT NULL VARCHAR2(30)
        IDANALISI               = -1;               //NOT NULL NUMBER
        NOMEANALISI             = new String();     //VARCHAR2(160)
        IDCOUNTERKIT            = -1;               //NUMBER
        FLAGSORT                = 'N';              //NOT NULL CHAR(1)
        FLAGDATA                = 'N';              //NOT NULL CHAR(1)
        FLAGCENTRALE            = 'N';              //NOT NULL CHAR(1)
        FLAGCUMULAZIONE         = 'N';              //NOT NULL CHAR(1)
        LISTA_REPORT            = new Vector();     //Vettore di Integer
    }

    
    public int insert_Analysis()
    {
        IDANALISI = ADAMS_GlobalParam.connect_Oracle.Query_int("select IDANALISI_seq.nextVal from dual");
        //System.out.println("Insert IDANALISI_seq.nextVal "+IDANALISI);
        if(IDANALISI < 0)
        {
            ADAMS_GlobalParam.optionPanel((javax.swing.JFrame)null,"Operation failed [Error: IDANALISI_seq.nextVal].","ERROR",1);
            return -1;
        }

        String itemName_An =("TIPO_DI_CONFIGURAZIONE,"+   
                            "IDANALISI,"+
                            "NOMEANALISI,"+
                            "IDCOUNTERKIT,"+
                            "FLAGSORT,"+
                            "FLAGDATA,"+
                            "FLAGCENTRALE,"+
                            "FLAGCUMULAZIONE");

        String itemValue_An = ( "'"+TIPO_DI_CONFIGURAZIONE+"',"+
                                IDANALISI+","+
                                "'"+NOMEANALISI+"',"+
                                IDCOUNTERKIT+","+                                
                                "'"+FLAGSORT+"',"+
                                "'"+FLAGDATA+"',"+
                                "'"+FLAGCENTRALE+"',"+
                                "'"+FLAGCUMULAZIONE+"'");
        
        String str_Insert_An = ("insert into "+name_TabAnalisi+" ("+itemName_An+") values ( "+itemValue_An+" )");
        //System.out.println("str_Insert_An --> "+str_Insert_An);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_An);
        
        //Nested
        if(Answer_Ins >= 0 )
            Answer_Ins = update_LISTA_REPORT();
        
        try
        {         
            if(Answer_Ins >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_TYPE_ANALISI.java 1");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_TYPE_ANALISI.java 1A");}
        }
        
        
        return Answer_Ins;
    }
    
    public int update_Analysis()
    {
        String str_update = "update "+name_TabAnalisi+" set IDANALISI="+IDANALISI+","+
                            "NOMEANALISI='"+NOMEANALISI+"',"+
                            "IDCOUNTERKIT="+IDCOUNTERKIT+","+
                            "FLAGSORT='"+FLAGSORT+"',"+
                            "FLAGDATA='"+FLAGDATA+"',"+
                            "FLAGCENTRALE='"+FLAGCENTRALE+"',"+
                            "FLAGCUMULAZIONE='"+FLAGCUMULAZIONE+"' "+                                                       
                            "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and "+
                            "IDANALISI="+IDANALISI;
                
        //System.out.println("esito update_Analysis = "+str_update);
        
        try
        {        
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}
        
        int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
        
        if(Answer_update >= 0 )
            Answer_update = update_LISTA_REPORT();
                        
        try
        {         
            if(Answer_update >= 0)
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_TYPE_ANALISI.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_TYPE_ANALISI.java 2A");}
        }
        
        return Answer_update;
    }
    
    
    public int delete_Analysis()
    {         
        String str_delete = "";
        ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: the following Reports will be deleted. Please verify and confirm operation.","Warning",6);
        int Yes_No = op.getAnswer();
        int Answer_del = -1;
        if(Yes_No == 1)
        {            
		/*{
	            String str_delete = "delete from "+name_TabAnalisi+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
	                                 " and IDANALISI="+IDANALISI;
	        
	            try
	            {        
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	                //System.out.println("setAutoCommit(false)");
	            }catch(java.sql.SQLException exc){}
	
	
	            Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);   
	           
	            
	            try
	            {         
	                if(Answer_del >= 0)
	                {                
	                    ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
	                    ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
	                    //System.out.println("Commit OK");
	                }
	                else
	                {
	                    ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
	                    System.out.println("rollback()");
	                }
	
	                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	            }
	            catch(java.sql.SQLException exc)
	            {
	                System.out.println("Errore ADAMS_TAB_TYPE_ANALISI.java 2");
	                try
	                { 
	                    ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	                }
	                catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_TYPE_ANALISI.java 2A");}
	            }
	        }*/
	        
	        { // Delete tab_analysis_type
		        str_delete = "delete from "+ name_TabAnalisi + " where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and IDANALISI="+IDANALISI;
		        try
		        {        
		            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
		            //System.out.println("setAutoCommit(false)");
		        }catch(java.sql.SQLException exc){}
		        
		        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
		        
		        try
		        {         
		            if(Answer_del >= 0)
		            {                
		                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
		                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
		                System.out.println("delete_Analysis(tab_analysis_type)::Commit OK");
		            }
		            else
		            {
		                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
		                System.out.println("delete_Analysis(tab_analysis_type)::rollback()");
		            }
		            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
		        }
		        catch(java.sql.SQLException exc)
		        {
		            System.out.println("Errore delete_Analysis(tab_analysis_type) (1).");
		            try
		            { 
		                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
		            }catch(java.sql.SQLException exc1)
		            {
		            	System.out.println("Errore delete_Analysis(tab_analysis_type) (2)");
		            }
		        }
		}
		
		{ // Delete tab_list_report
		        str_delete = "delete from "+ name_TabAnalisi + " where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and IDANALISI="+IDANALISI;
		        try
		        {        
		            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
		            //System.out.println("setAutoCommit(false)");
		        }catch(java.sql.SQLException exc){}
		        
		        Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
		        
		        try
		        {         
		            if(Answer_del >= 0)
		            {                
		                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
		                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
		                System.out.println("delete_Analysis(tab_list_report)::Commit OK");
		            }
		            else
		            {
		                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
		                System.out.println("delete_Analysis(tab_list_report)::rollback()");
		            }
		            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
		        }
		        catch(java.sql.SQLException exc)
		        {
		            System.out.println("Errore delete_Analysis(tab_list_report) (1).");
		            try
		            { 
		                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
		            }catch(java.sql.SQLException exc1)
		            {
		            	System.out.println("Errore delete_Analysis(tab_list_report) (2)");
		            }
		        }
		}
        }else
        {
            return -3;
        }  
                    
        return Answer_del;
    }    
    
    
    
    private int update_LISTA_REPORT() // Nested Campo NUMBER
    {
        int rowCount 		= LISTA_REPORT.size();
        String tableConfig      = "tab_list_report";
	String strSelect	= "";
	int Answer_update	= 1;
        String strDelete	= "";
	
	{ // DELETE before INSERT (tab_list_report)
		strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+" and IDANALISI="+IDANALISI;
	        try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_LISTA_VALORI: "+exc);
	        }
	        
	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete); 
	       	
	       	try
	        {
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("rollback()");
			}
	        }
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_LISTA_REPORT::rollback(): "+exc);
	        }
	           
	       	try
	        {        
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_LISTA_REPORT: "+exc);	
	        }
	}
        if(rowCount>0)
	{
		strSelect="INSERT ALL ";
		
	        for(int i=0;i<rowCount;i++)
	        {
			strSelect=strSelect+" into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,IDANALISI,IDREPORT) values ";
			int ID_Analysis = ((Integer)LISTA_REPORT.elementAt(i)).intValue();	
			strSelect=strSelect+" ('"+TIPO_DI_CONFIGURAZIONE+"',"+IDANALISI+","+ID_Analysis+") ";
	        }
	        
	        strSelect=strSelect+" SELECT * from DUAL";
	        
	        System.out.println("strSelect update_LISTA_REPORT ="+strSelect);
	        Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect);
	}
        else
        {
        	System.out.println("la LISTA_REPORT è vuota!!!");	
        }
        return Answer_update;
    }
    
    
    
    public String   TIPO_DI_CONFIGURAZIONE;     //NOT NULL VARCHAR2(30)
    public int      IDANALISI;                  //NOT NULL NUMBER
    public String   NOMEANALISI;                //VARCHAR2(160)
    public int      IDCOUNTERKIT;               //NUMBER
    public char     FLAGSORT;                   //NOT NULL CHAR(1)
    public char     FLAGDATA;                   //NOT NULL CHAR(1)
    public char     FLAGCENTRALE;               //NOT NULL CHAR(1)
    public char     FLAGCUMULAZIONE;            //NOT NULL CHAR(1)
    public Vector   LISTA_REPORT;               //TAB_REPORT_ABL --- Vettore di Integer ---
    
    //Nome tabella
    private final String name_TabAnalisi = "tab_analysis_type";

}
