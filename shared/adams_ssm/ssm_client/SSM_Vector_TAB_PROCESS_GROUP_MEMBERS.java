
import java.sql.ResultSet;
import java.sql.Statement;

/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Luca Beltrame <luca.beltrame@e-tech.net>                                                                                                           
#                                                                                                                                                 
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory                                                                    
#                                                                                                                                                 
#  HISTORY:                                                                                                                                       
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/
public class SSM_Vector_TAB_PROCESS_GROUP_MEMBERS extends java.util.Vector implements Runnable
{
    SSM_Vector_TAB_PROCESS_GROUP_MEMBERS()
    {
        super();
    }
    
    //processGroupID == -1 ALL Process_Group_Members
    //processGroupID == olnly process_group_id 
    public boolean readProcess_Group_Members(int processGroupID)
    {
        this.OperationTH = READ_PROCESS_GROUP_MEMBERS;
        this.PROCESS_GROUP_ID = processGroupID;
        
        th = null;
        th = new Thread(this,"READ_PROCESS_GROUP_MEMBERS");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
        return true;
    }
    
    public int insert_Process_Group_Members()
    {
        this.OperationTH = INSERT_PROCESS_GROUP_MEMBERS;   
        
        th = null;
        th = new Thread(this,"INSERT_PROCESS_GROUP_MEMBERS");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
        return answer_INSERT_PROCESS_GROUP_MEMBERS;   
    }
    
    public int delete_All_Process_Group_Members(int PROCESS_GROUP_ID)
    {
        this.OperationTH = DELETE_ALL_PROCESS_GROUP_MEMBERS; 
        this.del_PROCESS_GROUP_ID = PROCESS_GROUP_ID;
        
        th = null;
        th = new Thread(this,"delete_All_Process_Group_Members");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
        this.del_PROCESS_GROUP_ID = -1;
        return answer_DELETE_ALL_PROCESS_GROUP_MEMBERS;   
    }
    
    public void run() 
    {
        //processGroupID == -1 ALL Process_Group_Members
        //processGroupID == olnly process_group_id 
        if(OperationTH == READ_PROCESS_GROUP_MEMBERS)
        {
            String str_where = "";
            if(PROCESS_GROUP_ID != -1)
                str_where = " where `process_group_id`="+PROCESS_GROUP_ID;
            
            String select_read_pgm = "select "+All_itemName+" from t_process_group_members"+str_where;
            System.out.println("select_read_pgm --> "+select_read_pgm);
            
            Statement SQLStatement = SSM_GlobalParam.db_Connection.createLocalStatement();
            ResultSet rs  = SSM_GlobalParam.db_Connection.Query_RS(select_read_pgm,SQLStatement);

            this.removeAllElements();
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {                        
                        SSM_TAB_PROCESS_GROUP_MEMBERS row_t_pgm = new SSM_TAB_PROCESS_GROUP_MEMBERS();
                        
                        row_t_pgm.setProcess_group_id(rs.getInt("process_group_id"));
                        row_t_pgm.setProcess_id(rs.getInt("process_id"));
                        
                        // Add Vector
                        this.addElement(row_t_pgm);
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
                System.out.println("(SSM_Vector_TAB_PROCESS_GROUP_MEMBERS) SQL Operation " + exc.toString());
            }                        
        }
        else if(OperationTH == INSERT_PROCESS_GROUP_MEMBERS)
        {
            int SIZE = size();
            int Answer_Ins = -1;
            if(size()> 0)
            {
                String str_values_delete = "(";
                String str_values_insert = "";
                String point = ",";
                for(int i=0; i<SIZE; i++)
                {                   
                    if(i == SIZE-1)
                        point ="";
                    
                    SSM_TAB_PROCESS_GROUP_MEMBERS row_t_pgm = (SSM_TAB_PROCESS_GROUP_MEMBERS)elementAt(i);
                    
                    if(str_values_delete.indexOf("'"+row_t_pgm.getProcess_group_id()+"'") == -1)
                        str_values_delete += "'"+row_t_pgm.getProcess_group_id()+"'"+point;
                    
                    str_values_insert += "('"+row_t_pgm.getProcess_group_id()+"','"+row_t_pgm.getProcess_id()+"')"+point;
                }
                
                if(str_values_delete.endsWith(","))
                    str_values_delete = str_values_delete.substring(0,str_values_delete.length()-1)+(")");
                else
                    str_values_delete += (")");
                                
                String str_delete = "DELETE FROM `t_process_group_members` WHERE `process_group_id`= "+str_values_delete;
                System.out.println("str_delete --> "+str_delete);
                
                try{        
                    SSM_GlobalParam.db_Connection.DBConnection.setAutoCommit(false);}
                catch(java.sql.SQLException exc){ 
                    exc.printStackTrace();}        
                
                int Answer_delete = SSM_GlobalParam.db_Connection.Operation(str_delete);     
                if(Answer_delete >= 0)
                {
                    String str_insert = "INSERT INTO `t_process_group_members` (`process_group_id`,`process_id`) VALUES "+str_values_insert;
                    System.out.println("str_insert --> "+str_insert);

                    Answer_Ins = SSM_GlobalParam.db_Connection.Operation(str_insert);
                    try
                    {
                        if(Answer_Ins > 0)
                        {
                            SSM_GlobalParam.db_Connection.DBConnection.commit();
                            System.out.println("Commit OK");
                        }
                        else
                        {
                            SSM_GlobalParam.db_Connection.DBConnection.rollback();
                            System.out.println("rollback()");
                        }
                        SSM_GlobalParam.db_Connection.DBConnection.setAutoCommit(true); 
                    }
                    catch(java.sql.SQLException exc)
                    {                       
                        try{SSM_GlobalParam.db_Connection.DBConnection.setAutoCommit(true);}
                         catch(java.sql.SQLException exc1){exc1.printStackTrace();}
                    }
                }
            }                
            answer_INSERT_PROCESS_GROUP_MEMBERS = Answer_Ins;
        }
        else if(OperationTH == DELETE_ALL_PROCESS_GROUP_MEMBERS)
        {              
            String str_delete = "DELETE FROM `t_process_group_members` where process_group_id="+del_PROCESS_GROUP_ID;
            System.out.println("str_delete --> "+str_delete);

            answer_DELETE_ALL_PROCESS_GROUP_MEMBERS = SSM_GlobalParam.db_Connection.Operation(str_delete);
          
        }
        
        OperationTH = -1;
        Thread_Work = false;
    }// end run
      
    
     // ------ Thread
    private Thread th = null;
    private boolean Thread_Work = false;
    private int OperationTH = -1;    
    // ------ Thread
    
    private final int READ_PROCESS_GROUP_MEMBERS = 0;
    private int PROCESS_GROUP_ID = -1;
    
    
    private final int INSERT_PROCESS_GROUP_MEMBERS = 1;
    private int answer_INSERT_PROCESS_GROUP_MEMBERS = 0;
    
    
    private final int DELETE_ALL_PROCESS_GROUP_MEMBERS = 2;
    private int del_PROCESS_GROUP_ID = -1;
    private int answer_DELETE_ALL_PROCESS_GROUP_MEMBERS = 0;
    
    
    private String All_itemName =("process_group_id,process_id"); 
}

/*t_process_group_members
Colonna                 Tipo 	Null 	Predefinito 	Collegamenti a 	Commenti

process_group_id 	int(3) 	No  	  	t_process_groups -> process_group_id  	 
process_id              int(6) 	No  	  	t_process -> process_id 
*/
