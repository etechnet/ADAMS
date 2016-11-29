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
public class SSM_TAB_PROCESS_GROUPS 
{
    public SSM_TAB_PROCESS_GROUPS()
    {
        process_group_id    = -1;                   	  	 
        group_name          = new String();                 	 
    }

    public int insert_Process_Groups(String groupName)
    {
        this.group_name = groupName;
        String itemName = ("`process_group_id`,`group_name`");        
        String itemValue = ("NULL,'"+group_name+"'");        
        String str_Insert = ("INSERT INTO t_process_groups ("+itemName+") VALUES ( "+itemValue+" )");
        System.out.println("insert_Process_Groups -------> "+str_Insert);        
       
        int Answer_Ins = SSM_GlobalParam.db_Connection.Operation(str_Insert);        
        return Answer_Ins;        
    }
    
    public int update_Process_Groups(String newGroupName,int processGroupId)
    {
        this.group_name = newGroupName;
        this.process_group_id = processGroupId;
        String str_update = ("UPDATE t_process_groups SET group_name='"+group_name+"' where process_group_id="+process_group_id);     
        
        System.out.println("update_Process_Groups -------> "+str_update);           
    
        int Answer_update = SSM_GlobalParam.db_Connection.Operation(str_update);     
        return Answer_update;
    }
    
    public int delete_Process_Groups(int processGroupId)
    {       
        this.process_group_id = processGroupId;
        String str_delete = "DELETE FROM `t_process_groups` WHERE `process_group_id`="+process_group_id;
        
        System.out.println("delete_Process_Groups -------> "+str_delete);
        int Answer_delete = SSM_GlobalParam.db_Connection.Operation(str_delete);     
        return Answer_delete;
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
     * @return the group_name
     */
    public String getGroup_name() {
        return group_name;
    }

    /**
     * @param group_name the group_name to set
     */
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    
    private int process_group_id;  	 
    private String group_name;
    
}

/*t_process_groups
Colonna                 Tipo            Null 	Predefinito 	Commenti

process_group_id 	int(3)          No  	  	 
group_name              varchar(128) 	No
*/