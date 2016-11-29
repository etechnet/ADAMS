
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

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
public class SSM_Vector_TAB_T_NODE extends java.util.Vector implements Runnable
{
    SSM_Vector_TAB_T_NODE()
    {
        super();
    }
    
    public boolean readNodes()
    {
        OperationTH = READ_NODES;
        
        th = null;
        th = new Thread(this,"READ_NODES");
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
        if(OperationTH == READ_NODES)
        {
            String select_read_Node = "select "+All_itemName+" from t_node";
            System.out.println("select_read_Node --> "+select_read_Node);
            
            Statement SQLStatement = SSM_GlobalParam.db_Connection.createLocalStatement();
            ResultSet rs  = SSM_GlobalParam.db_Connection.Query_RS(select_read_Node,SQLStatement);

            this.removeAllElements();
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {                        
                        SSM_TAB_T_NODE row_t_node = new SSM_TAB_T_NODE();
                        
                        row_t_node.setNode_id(rs.getInt("node_id"));
                        row_t_node.setName(rs.getString("name"));  
                        row_t_node.setDescription(rs.getString("description"));  
                        row_t_node.setProcess_group_id(rs.getInt("process_group_id"));
                        row_t_node.setActive(rs.getByte("active"));
                        row_t_node.setLocation(rs.getString("location"));
                        row_t_node.setPort_range_min(rs.getInt("port_range_min"));
                        row_t_node.setPort_range_max(rs.getInt("port_range_max"));
                        
                        // Add Vector
                        this.addElement(row_t_node);
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
                System.out.println("(SSM_Vector_TAB_T_NODE-1) SQL Operation " + exc.toString());
            }
                        
        }
        
        OperationTH = -1;
        Thread_Work = false;
    }
    
    public String getNode_name(int node_id)
    {
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_T_NODE node = (SSM_TAB_T_NODE)this.elementAt(i);
                if( node.getNode_id()== node_id ) 
                    return node.getName();
            }
        }
        return null;
    }
    
    public int getNode_id(String Node_name)
    {
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_T_NODE node = (SSM_TAB_T_NODE)this.elementAt(i);
                if(node.getName().equalsIgnoreCase(Node_name) == true)
                    return node.getNode_id();
            }
        }
        return -1;
    }
    
    public int num_NodeWithProcessGroupId(int process_group_id)
    {
        int numNode = 0;
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_T_NODE node = (SSM_TAB_T_NODE)this.elementAt(i);
                if(node.getProcess_group_id() == process_group_id) {
                    numNode++;
                }
            }
        }
        return numNode;
    }
    
    
    // ------ Thread
    private Thread th = null;
    private boolean Thread_Work = false;
    private int OperationTH = -1;    
    // ------ Thread
    
    private final int READ_NODES = 0; 
    private String All_itemName =("node_id,"+   
                                    "name,"+
                                    "description,"+
                                    "process_group_id,"+
                                    "active,"+
                                    "location,"+
                                    "port_range_min,"+
                                    "port_range_max"); 
}

/*TABELLA t_node
        
Colonna                 Tipo            Null 	Predefinito     Collegamenti a 	Commenti

node_id                 int(4)          No  	  	  	 
name                    varchar(30) 	No  	  	  	 
description             varchar(128) 	No  	  	  	 
process_group_id        int(3)          No                      t_process_groups -> process_group_id  	 
active                  tinyint(1) 	No  	  	  	 
location                text            No  	  	  	 
port_range_min          int(5)          No  	1024  	  	 
port_range_max          int(5)          No  	65535  	  	 
*/