
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
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

public class SSM_Vector_TAB_T_PROCESS extends java.util.Vector implements Runnable
{
    
    public SSM_Vector_TAB_T_PROCESS()
    {
        super();
    }       
    
    public boolean readProcess()
    {
        OperationTH = READ_PROCESS;
        
        th = null;
        th = new Thread(this,"READ_PROCESS");
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
    
    @Override
    public void run() 
    {
        if(OperationTH == READ_PROCESS)
        {
            String select_read_Process = "select "+All_itemName+" from t_process";
            System.out.println("select_read_Process --> "+select_read_Process);
            
            Statement SQLStatement = SSM_GlobalParam.db_Connection.createLocalStatement();
            ResultSet rs  = SSM_GlobalParam.db_Connection.Query_RS(select_read_Process,SQLStatement);

            this.removeAllElements();
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {                        
                        SSM_TAB_T_PROCESS row_t_process = new SSM_TAB_T_PROCESS();
                        
                        row_t_process.setProcess_name(rs.getString("process_name"));  
                        row_t_process.setProcess_type(rs.getInt("process_type"));
                        row_t_process.setProcess_id(rs.getInt("process_id"));
                        row_t_process.setSchedule_ignore(rs.getInt("schedule_ignore"));
                        
                        Time time = rs.getTime("wake_time");
                        Date data_time = new Date(time.getTime());
                                           
                        if(time != null)
                        {
                            GregorianCalendar calendar = new GregorianCalendar();
                            calendar.setTime(data_time);
                            row_t_process.setWake_time(calendar);
                        }
                        else
                        {System.out.println("err. wake_time Time null");}
                        
                        row_t_process.setStart_cmd(rs.getString("start_cmd"));
                        row_t_process.setLog_output(rs.getString("log_output"));


                        // Add Vector
                        this.addElement(row_t_process);
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
                System.out.println("(SSM_Vector_TAB_T_PROCESS-1) SQL Operation " + exc.toString());
            }
                        
        }
        
        OperationTH = -1;
        Thread_Work = false;
    }
    
    
    public String getProcess_name(int process_id)
    {
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_T_PROCESS process = (SSM_TAB_T_PROCESS)this.elementAt(i);
                if( process.getProcess_id()== process_id ) 
                    return process.getProcess_name();
            }
        }
        return null;
    }
    
    public int getProcess_id(String process_name)
    {
        int SIZE = size();
        if(SIZE != 0)
        {
            for(int i=0; i<SIZE; i++)
            {
                SSM_TAB_T_PROCESS process = (SSM_TAB_T_PROCESS)this.elementAt(i);
                if(process.getProcess_name().equalsIgnoreCase(process_name) == true)
                {
                    return process.getProcess_id();
                }
            }
        }
        return -1;
    }
    
    
    // ------ Thread
    private Thread th = null;
    private boolean Thread_Work = false;
    private int OperationTH = -1;    
    // ------ Thread
    
    private final int READ_PROCESS = 0; 
    private String All_itemName =("process_name,"+   
                                    "process_type,"+
                                    "process_id,"+
                                    "schedule_ignore,"+
                                    "wake_time,"+
                                    "start_cmd,"+
                                    "log_output ");  
}
