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

public class SSM_TAB_T_NODE 
{
    
    public SSM_TAB_T_NODE()
    {
        node_id             = -1;                   	  	 
        name                = new String();                 	 
        description         = new String();          	  	 
        process_group_id    = -1;       
        active              = '0';            	  	 
        location            = new String();           
        port_range_min      = 1024;     
        port_range_max      = 65535;
    }
    
    public int insert_Node()
    {
        
        String itemName = ("`node_id`,"
                        + "`name`,"
                        + "`description`,"
                        + "`process_group_id`,"
                        + "`active`,"
                        + "`location`,"
                        + "`port_range_min`,"
                        + "`port_range_max`");
        
        String itemValue    = ("NULL,"
                            + "'"+name+"',"
                            + "'"+description+"',"
                            + "'"+process_group_id+"',"
                            + "'"+active+"',"
                            + "'"+location+"',"
                            + "'"+port_range_min+"',"
                            + "'"+port_range_max+"'");
        
        String str_Insert = ("INSERT INTO t_node ("+itemName+") VALUES ( "+itemValue+" )");
        System.out.println("insert_Node -------> "+str_Insert);        
       
        int Answer_Ins = SSM_GlobalParam.db_Connection.Operation(str_Insert);        
        return Answer_Ins;        
        
    }
    
    public int update_Node()
    {
        String str_update = ("UPDATE t_node SET "
                            + "name='"+name+"',"
                            + "description='"+description+"',"                    
                            + "process_group_id="+process_group_id+","
                            + "active='"+active+"',"
                            + "location='"+location+"',"
                            + "port_range_min="+port_range_min+","
                            + "port_range_max="+port_range_max+" "
                            + "where node_id="+node_id);     
        
        
        System.out.println("update_Node -------> "+str_update);               
    
        int Answer_update = SSM_GlobalParam.db_Connection.Operation(str_update);     
        return Answer_update;
    }

    public int delete_node()
    {       
        String str_delete = "DELETE FROM `t_node` WHERE `node_id`="+node_id;
        
        System.out.println("delete_node -------> "+str_delete);
        int Answer_delete = SSM_GlobalParam.db_Connection.Operation(str_delete);     
        return Answer_delete;
    }
    

    /**
     * @return the node_id
     */
    public int getNode_id() {
        return node_id;
    }

    /**
     * @param node_id the node_id to set
     */
    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the process_group_id
     */
    public int getProcess_group_id() {
        return process_group_id;
    }

    /**
     * @param process_group_id the process_group_id to set
     */
    public void setProcess_group_id(int process_group_id) {
        this.process_group_id = process_group_id;
    }

    /**
     * @return the active
     */
    public byte getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(byte active) {
        this.active = active;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the port_range_min
     */
    public int getPort_range_min() {
        return port_range_min;
    }

    /**
     * @param port_range_min the port_range_min to set
     */
    public void setPort_range_min(int port_range_min) {
        this.port_range_min = port_range_min;
    }

    /**
     * @return the port_range_max
     */
    public int getPort_range_max() {
        return port_range_max;
    }

    /**
     * @param port_range_max the port_range_max to set
     */
    public void setPort_range_max(int port_range_max) {
        this.port_range_max = port_range_max;
    }

    private int node_id;                   	  	 
    private String name;                 	 
    private String description;           	  	 
    private int process_group_id;        
    private byte active;                    	  	 
    private String location;           
    private int port_range_min;      
    private int port_range_max;
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
