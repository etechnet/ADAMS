
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
public class SSM_Vector_TAB_PROCESS_GROUPS extends java.util.Vector implements Runnable
{
    SSM_Vector_TAB_PROCESS_GROUPS()
    {
        super();
    }
    
    public boolean readPreocessGroups()
    {
        OperationTH = READ_PROCESS_GROUPS;
        
        th = null;
        th = new Thread(this,"READ_PROCESS_GROUPS");
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
    
    public void run() 
    {
        if(OperationTH == READ_PROCESS_GROUPS)
        {
            String select_read_process_groups = "select "+All_itemName+" from t_process_groups";
            System.out.println("select_read_process_groups --> "+select_read_process_groups);
            
            Statement SQLStatement = SSM_GlobalParam.db_Connection.createLocalStatement();
            ResultSet rs  = SSM_GlobalParam.db_Connection.Query_RS(select_read_process_groups,SQLStatement);

            this.removeAllElements();
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {                        
                        SSM_TAB_PROCESS_GROUPS row_process_groups = new SSM_TAB_PROCESS_GROUPS();
                        
                        row_process_groups.setProcess_group_id(rs.getInt("process_group_id"));
                        row_process_groups.setGroup_name(rs.getString("group_name")); 
                        
                        // Add Vector
                        this.addElement(row_process_groups);
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
                System.out.println("(SSM_Vector_TAB_PROCESS_GROUPS) SQL Operation " + exc.toString());
            }
                        
        }
        
        OperationTH = -1;
        Thread_Work = false;
    }
    
    public int getProcess_group_id(String group_name)
    {
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_PROCESS_GROUPS process_groups = (SSM_TAB_PROCESS_GROUPS)this.elementAt(i);
                if( process_groups.getGroup_name().equalsIgnoreCase(group_name)) 
                    return process_groups.getProcess_group_id();
            }
        }
        return -1;
    }
    
    public String getGroup_name(int process_group_id)
    {
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_PROCESS_GROUPS process_groups = (SSM_TAB_PROCESS_GROUPS)this.elementAt(i);
                if( process_groups.getProcess_group_id() == process_group_id ) 
                    return process_groups.getGroup_name();
            }
        }
        return null;
    }
    
    
    // ------ Thread
    private Thread th = null;
    private boolean Thread_Work = false;
    private int OperationTH = -1;    
    // ------ Thread
    
    private final int READ_PROCESS_GROUPS = 0; 
    private String All_itemName =("process_group_id,group_name");
}

/*t_process_groups
Colonna                 Tipo            Null 	Predefinito 	Commenti

process_group_id 	int(3)          No  	  	 
group_name              varchar(128) 	No
*/