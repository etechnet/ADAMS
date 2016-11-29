import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

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

public class SSM_TAB_T_PROCESS 
{ 
    public SSM_TAB_T_PROCESS()
    {
        process_name    = new String(""); // varchar(128)
        process_type    = 0;
        process_id      = 0;
        schedule_ignore   = 0;
        wake_time       = null;
        start_cmd       = new String(); //varchar(512)
        log_output      = "";
    }
            
    public int insert_Process()
    {       
        String str_wake_time_format = "'00:00:00'";
        if(wake_time != null)
        {
            Date time = wake_time.getTime();                    
            SimpleDateFormat ftime = new SimpleDateFormat ("HH:mm");
            str_wake_time_format = "'"+ftime.format(time)+":00'";
            System.out.println("str_wake_time_format: " + str_wake_time_format);
                    
        }
        String itemName     = ("`process_name`,"
                                + "`process_type`,"
                                + "`process_id`,"
                                + "`schedule_ignore`,"
                                + "`wake_time`,"
                                + "`start_cmd`,"
                                + "`log_output`");
        
        String itemValue    = ("'"+process_name+"',"
                                + "'"+process_type+"',"
                                + "NULL,"
                                + "'"+schedule_ignore+"',"
                                + str_wake_time_format+","
                                + "'"+start_cmd+"',"
                                + "'"+getLog_output()+"'");
        
        String str_Insert = ("INSERT INTO t_process ("+itemName+") VALUES ( "+itemValue+" )");
        System.out.println("insert_Process -------> "+str_Insert);        
       
        int Answer_Ins = SSM_GlobalParam.db_Connection.Operation(str_Insert);        
        return Answer_Ins;
    }
    
    public int update_Process()
    {
        String str_wake_time_format = "'00:00:00'";
        if(wake_time != null)
        {   
            Date time = wake_time.getTime();
            SimpleDateFormat ftime = new SimpleDateFormat ("HH:mm");
            str_wake_time_format = "'"+ftime.format(time)+":00'";
            System.out.println("str_wake_time_format: " + str_wake_time_format);
                    
        }
            String str_update = ("UPDATE t_process SET "
                            + "process_name='"+process_name+"',"
                            + "process_type="+process_type+","
                            + "schedule_ignore="+schedule_ignore+","
                            + "wake_time="+str_wake_time_format+","
                            + "start_cmd='"+start_cmd+"',"
                            + "log_output='"+getLog_output()+"' "
                            + "where process_id="+process_id);      
        
        
        System.out.println("update_Process -------> "+str_update);               
    
        int Answer_update = SSM_GlobalParam.db_Connection.Operation(str_update);     
        return Answer_update;
    }
    
    public int delete_process()
    {       
        String str_delete = "DELETE FROM `t_process` WHERE `process_id`="+process_id;
        
        System.out.println("delete_process -------> "+str_delete);
        int Answer_delete = SSM_GlobalParam.db_Connection.Operation(str_delete);     
        return Answer_delete;
    }
    
    /**
     * @return the process_name
     */
    public String getProcess_name() {
        return process_name;
    }

    /**
     * @param process_name the process_name to set
     */
    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    /**
     * @return the process_type
     */
    public int getProcess_type() {
        return process_type;
    }

    /**
     * @param process_type the process_type to set
     */
    public void setProcess_type(int process_type) {
        this.process_type = process_type;
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

    /**
     * @return the schedule_ignore
     */
    public int getSchedule_ignore() {
        return schedule_ignore;
    }

    /**
     * @param schedule_ignore the schedule_ignore to set
     */
    public void setSchedule_ignore(int schedule_ignore) {
        this.schedule_ignore = schedule_ignore;
    }

    /**
     * @return the wake_time
     */
    public GregorianCalendar getWake_time() {
        return wake_time;
    }

    /**
     * @param wake_time the wake_time to set
     */
    public void setWake_time(GregorianCalendar wake_time) {
        this.wake_time = wake_time;
    }

    /**
     * @return the start_cmd
     */
    public String getStart_cmd() {
        return start_cmd;
    }

    /**
     * @param start_cmd the start_cmd to set
     */
    public void setStart_cmd(String start_cmd) {
        this.start_cmd = start_cmd;
    }
    
     /**
     * @return the log_output
     */
    public String getLog_output() {
        return log_output;
    }

    /**
     * @param log_output the log_output to set
     */
    public void setLog_output(String log_output) {
        this.log_output = log_output;
    }


     
    private String process_name;    // varchar(128)
    private int process_type;
    private String log_output;
    private int process_id;
    private int schedule_ignore;
    private GregorianCalendar wake_time;         
    private String start_cmd;       //varchar(512)	 

   
    
}

/*
Colonna         Tipo	Null	Predefinito	Commenti
* 
process_name 	varchar(128)	No 	 	 
process_type 	int(1)	No 	0 	 
process_id 	int(6)	No 	 	 
schedule_ignore 	int(1)	No 	1 	 
wake_time 	time	No 	00:00:00 	 
start_cmd 	varchar(512)	No              none 	 
*/