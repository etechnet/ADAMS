/*
 * OracleConnection.java
 *
 * Created on 30 aprile 2001, 15.25
 */


/**
 *
 * @author  gundam
 * @version
 */
import java.awt.*;
import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> IMAGE Client </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *
 *Questa classe contiene tutti metodi relativi alle operazioni di connessione,interrogazione e 
 *chiusura Database.
 */
public class NTM_OracleConnection
{
    private final boolean DEBUG = false;
    
    // The driver to load
    private final String driver_class ="oracle.jdbc.OracleDriver";	//Oracle 9
    
    /**
    *Sid del Database.
    */ 
    private String sid;  
    
    /**
    *Sid del Database.
    */ 
    private String oracle_PORT;
    
    /**
     *Host dove risiedeil Database. 
     */  
     private  String host;
    
    	
    Connection DBConnection = null;      // database connection
    //Statement SQLStatement = null;       // SQL statement
    //ResultSet rsQuery = null;            // query result set
    
    private final String ITEM_TOKEN ="^";
    
    private String CONNECT_STRING = null;
    
    public void setupParam(String h_sid, String h_oracle_PORT,String h_host)
    {
    	sid=h_sid;
    	oracle_PORT=h_oracle_PORT;
    	host=h_host;
    }
  /**
   *Metodo che compie le operazioni di "Insert","Delete","Update" sul Database.
   *@return il valore "1" per esito positivo dell'operazione,valore "0" esito negativo.
   */
    public synchronized int Operation(String sqlstm)
    {
        Statement SQLStatement_OP = null;

        //System.out.println("Operation ----> "+sqlstm);
        
        int x=-1;
        try
        { 
            SQLStatement_OP = DBConnection.createStatement();           
            x =  SQLStatement_OP.executeUpdate(sqlstm); 
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Operation) SQL Operation " + exc.toString());
            System.out.println("(Operation) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"ORACLE ERROR:"+exc.toString(),"ERROR",1);
            x = -1;
        }
        try
        {
            SQLStatement_OP.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Operation) SQL Operation " + exc.toString());
            exc.printStackTrace();
        }
        
        return(x);
    }
    
    public synchronized Statement createLocalStatement() 
    {     
        Statement SQLStatement_Qrs = null;
        try
        {
            SQLStatement_Qrs = DBConnection.createStatement();
            
        } 
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_rs) SQL Operation " + exc.toString());
            System.out.println("(Query_rs) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"createLocalStatement ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
        
        return SQLStatement_Qrs;
    }
 
    public synchronized ResultSet Query_RS(String sqlstm, Statement SQLStatement_Qrs) 
    {      
        ResultSet rsQuery = null;
        try
        {            
            rsQuery = SQLStatement_Qrs.executeQuery(sqlstm);
        } 
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_rs) SQL Operation " + exc.toString());
            System.out.println("(Query_rs) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"Query_rs ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
        
        //System.out.println("SQLStatement_Qrs "+SQLStatement_Qrs);
        return (ResultSet)rsQuery;
    }
    
    
    public synchronized ResultSet Query_RS_Statement(String sqlstm,Statement SQLStatement_1) 
    {        
        ResultSet rsQuery_1         = null; 
        try
        {            
            rsQuery_1 = SQLStatement_1.executeQuery(sqlstm);
        } 
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_rs_Statement) SQL Operation " + exc.toString());
            System.out.println("(Query_rs_Statement) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"Query_rs_Statement ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
         
        return (ResultSet)rsQuery_1;
    }

    public synchronized int Query_rs_update(String sqlstm) 
    {
        Statement SQLStatement_1    = null;
        int rsQuery=-1;
        
        try
        {
            SQLStatement_1 = DBConnection.createStatement();
            rsQuery = SQLStatement_1.executeUpdate(sqlstm);
        } 
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_rs_update) SQL Operation " + exc.toString());
            System.out.println("(Query_rs_update) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"Query_rs_update ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
        
        try
        {
            SQLStatement_1.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_rs_update) SQL Operation " + exc.toString());
        }
        
        return (int)rsQuery;
    }
    
  
        /**
    *Questo metodo riceve una stringa di comandi "sql",la esegue e ritorna un vettore di stringhe come risultato.
    *@return il risultato in un vettore di stringhe.
    */
    public synchronized Vector Query(String sqlstm) 
    {
        // System.out.println("Query *1* ");
        
      //System.out.println("&&&&&&&&& Query rsQuery "+rsQuery);
       //System.out.println("&&&&&&&&& Query SQLStatement "+SQLStatement);
       //System.out.println("&&&&&&&&& Query DBConnection "+DBConnection);
       
       //System.out.println("Query => " +sqlstm);
        
       String str;
       Vector res = new Vector();
       
       Statement SQLStatement    = null;
       ResultSet rsQuery         = null; 
       
        try
        {
            SQLStatement = DBConnection.createStatement();
            
            rsQuery = SQLStatement.executeQuery(sqlstm);
            ResultSetMetaData rsmd = rsQuery.getMetaData();
            int nCols = rsmd.getColumnCount();
            while(rsQuery.next()) 
            {
               str = "";
                for(int ctCol = 0; ctCol < nCols; ctCol++) 
                {
                    String dot = ITEM_TOKEN;
                    if(ctCol == nCols-1 )
                        dot ="";
                    
                    str = str + rsQuery.getString(ctCol+1)+dot;
                    if(str == null) 
                        str = "NULL";
                }
            res.addElement(str);

            }
        } 
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query) SQL Operation " + exc.toString());
            System.out.println("(Query) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"Query ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
        
        try
        {
            SQLStatement.close();            
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query) SQL Operation " + exc.toString());
        }
        
        return(res);
    }
    
    
    /**
    *Questo metodo riceve una stringa di comandi "sql",la esegue e ritorna una stringa come risultato.
    *@return il risultato in un stringa.
    */
    public synchronized String Query_s(String sqlstm) 
    {    
        Statement SQLStatement    = null;
        ResultSet rsQuery         = null; 
       
        //System.out.println("Query_s => " +sqlstm);
        String res = new String("");
        try
        {
                        
            SQLStatement = DBConnection.createStatement();
            rsQuery = SQLStatement.executeQuery(sqlstm);
           
            if ( rsQuery.next() )
                res = rsQuery.getString(1);
            
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("(Query_s) SQL Operation " + exc.toString());
            System.out.println("(Query_s) SQL Operation " + exc.getErrorCode());
            
            //staticLib.optionPanel((javax.swing.JFrame)null,"Query_s ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_s) SQL Operation " + exc.toString());
        }
        if(res == null)
            return("");
        else
            return(res);
    }
    
     /**
    *Questo metodo riceve una stringa di comandi "sql",la esegue e ritorna una stringa come risultato.
    *@return il risultato in un stringa.
    */
    public synchronized int Query_int(String sqlstm) 
    {    
        ResultSet rsQuery         = null;
        Statement SQLStatement    = null;
        //System.out.println("Query_int => " +sqlstm);
        int res = -1;
        try
        {
            SQLStatement = DBConnection.createStatement();
            rsQuery = SQLStatement.executeQuery(sqlstm);

            if ( rsQuery.next() )
                res = rsQuery.getInt(1);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("(Query_int) SQL Operation " + exc.toString());
            System.out.println("(Query_int) SQL Operation " + exc.getErrorCode());
            
            exc.printStackTrace();
            //staticLib.optionPanel((javax.swing.JFrame)null,"Query_int ORACLE ERROR:"+exc.toString(),"ERROR",1);
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(Query_int) SQL Operation " + exc.toString());
        }
        return(res);
    }
  

    /**
     * Chiude la connessione al Database.
     */
    public void Close() 
    {
         //System.out.println("Chiudo Connessione **");
         //System.out.println("Close rsQuery "+rsQuery);
         //System.out.println("Close SQLStatement "+SQLStatement);
         //System.out.println("Close DBConnection "+DBConnection);
        try
        {
            /*if(rsQuery != null) 
                rsQuery.close();
            if(SQLStatement != null) 
                SQLStatement.close();*/
            if(DBConnection != null) 
                DBConnection.close();
            
            //System.out.println("Close Oracle Connection");
        } 
        catch(java.sql.SQLException exc) 
        {
            //System.out.println("Db Close " + exc.toString());
        }
    }

    public boolean DBConnect(String login,String password)
    {    	       
        try
        {
        	Class.forName(driver_class);            	
                this.CONNECT_STRING = new String("jdbc:oracle:thin:"+login+"/"+password+"@"+host+":"+oracle_PORT+":"+sid);                
                //System.out.println("CONNECT_STRING --> "+CONNECT_STRING); 
                
        	DBConnection = DriverManager.getConnection(CONNECT_STRING);                                        
               
                if(DEBUG) 
                {
                	displayDbProperties(DBConnection);
                }
                
		DatabaseMetaData DBMetadata = DBConnection.getMetaData();
            	//create a statement
           	//SQLStatement = DBConnection.createStatement();
        }
        catch(Exception exc)
        {
        	System.out.println("(DBConnect) SQL Operation " + exc.toString());
                exc.printStackTrace();
                
                //staticLib.optionPanel((javax.swing.JFrame)null,"ORACLE ERROR:"+exc.toString(),"ERROR",1);
                return false;
        }
        return true;
    } 
    
    public boolean reload_DBConnect()
    {    
        if(CONNECT_STRING != null)
        {
            Close();
            try
            {
                Class.forName(driver_class);
                //System.out.println("CONNECT_STRING --> "+CONNECT_STRING); 
                DBConnection = DriverManager.getConnection(CONNECT_STRING);                                        

                DatabaseMetaData DBMetadata = DBConnection.getMetaData();
            }
            catch(Exception exc)
            {
                System.out.println("(DBConnect) SQL Operation " + exc.toString());
                exc.printStackTrace();

                //staticLib.optionPanel((javax.swing.JFrame)null,"ORACLE ERROR:"+exc.toString(),"ERROR",1);
                return false;
            }
            return true;
        }
        else
        {
            //staticLib.optionPanel((javax.swing.JFrame)null,"ORACLE CONNECTION FAILED.","ERROR",1);
            return false;
        }
    } 
    
    
    public static void displayDbProperties(Connection con)
    { 
	java.sql.DatabaseMetaData dm = null; 
	java.sql.ResultSet rs = null; 
	try
        { 
            if(con!=null)
            { 
                dm = con.getMetaData(); 
                System.out.println("\nDriver Information"); 
                System.out.println("\tDriver Name: "+ dm.getDriverName()); 
                System.out.println("\tDriver Version: "+ dm.getDriverVersion ()); 
                System.out.println("\nDatabase Information "); 
                System.out.println("\tDatabase Name: "+ dm.getDatabaseProductName());
                System.out.println("\tDatabase Version: "+ dm.getDatabaseProductVersion()); 
                System.out.println("\tMaximum Connection (If zero--> no limit): "+dm.getMaxConnections()); 
                System.out.println("\tNumeric Functions: "+dm.getNumericFunctions()); 
                System.out.println("Avalilable Catalogs "); 
                rs = dm.getCatalogs();
                
                while(rs.next())
                { 
                        System.out.println("\tcatalog: "+ rs.getString(1)); 
                } 
                rs.close(); 
                rs = null; 
            }
            else 
               System.out.println("Error: No active Connection");
        }
        catch(Exception e)
        { 
            e.printStackTrace(); 
        } 
        dm=null; 
    }  
    
    
    public String getItemToken()
    {
        return this.ITEM_TOKEN;
    }
}
