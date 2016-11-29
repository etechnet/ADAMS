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
public class SSM_TAB_PROCESS_GROUP_MEMBERS 
{
    public SSM_TAB_PROCESS_GROUP_MEMBERS()
    {
        process_group_id    = -1;                   	  	 
        process_id          = -1;                 	 
    }
    
    public SSM_TAB_PROCESS_GROUP_MEMBERS(int group_id, int proc_id)
    {
        process_group_id    = group_id;                   	  	 
        process_id          = proc_id;                 	 
    }
    
    public int insert_Process_Group_members()
    {
        String itemName = ("`process_group_id`,`process_id`");        
        String itemValue = ("'"+process_group_id+"','"+process_id+"'");        
        String str_Insert = ("INSERT INTO t_process_group_members ("+itemName+") VALUES ( "+itemValue+")");
        System.out.println("insert_Process_Group_members -------> "+str_Insert);        
       
        int Answer_Ins = SSM_GlobalParam.db_Connection.Operation(str_Insert);        
        return Answer_Ins;        
    }
    
    public int delete_Process_Group_members()
    {       
        String str_delete = "DELETE FROM `t_process_group_members` "
                            + "WHERE `process_group_id`="+getProcess_group_id()+" and "
                            + "`process_id`="+getProcess_id();
        
        System.out.println("delete_Process_Group_members -------> "+str_delete);
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
     * @return the process_id
     */
    public int getProcess_id() {
        return process_id;
    }

    /**
     * @param process_id the process_id to set
     */
    public void setProcess_id(int process_id) {
        this.process_id = process_id;
    }
    
     private int process_group_id;
    private int process_id;
}

/*t_process_group_members
Colonna                 Tipo 	Null 	Predefinito 	Collegamenti a 	Commenti

process_group_id 	int(3) 	No  	  	t_process_groups -> process_group_id  	 
process_id              int(6) 	No  	  	t_process -> process_id 
*/