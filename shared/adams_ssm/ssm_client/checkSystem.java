/*
 * checkSystem.java
 *
 * Created on 5 aprile 2006, 7.32
 */

/**
 *
 * @author  root
 * @version 
 */
 
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class checkSystem implements Runnable {

    boolean                 DEBUG               = false;
    SSM_JF_ConfigSetup       configSetup         = null;
    private Thread th                           = null;
    private String SWITCH_SELECTED              = "";
    private int cont                            = 0;
    
    /** Creates new checkSystem */
    public checkSystem(SSM_JF_ConfigSetup configSetup,String SWITCH_SELECTED) {
        this.configSetup=configSetup;
        this.SWITCH_SELECTED=SWITCH_SELECTED;
        //System.out.println("SWITCH_SELECTED="+SWITCH_SELECTED);
    }

    public void start()
    {    
        th = null;        
        th = new Thread(this,"checkSystem ["+SWITCH_SELECTED+"]");
        th.start();       
    }
    
    public void stop()
    {    
        if(th!=null)
        {
        	  try
        	  {
            	//th.stop();
            	th.interrupt();
            } 
            catch(Exception e)
            {
                System.out.println("err. interruzione thread checkSystem.java");
            }
        }   
        th=null;
    }
    
    public void setSwitch(String SWITCH_SELECTED)
    {
        this.SWITCH_SELECTED=SWITCH_SELECTED;
    }
    
    public void run() 
    {
        while(true)
        {
            
            ProcesDetail[] processDetails = SSM_GlobalParam.CORBAConn.refresh_MonitorProcess(SWITCH_SELECTED,0);
            if(processDetails !=null)
            {
                for(int i=0; i<processDetails.length; i++)
                {
                    //System.out.println("NOME PROC: "+new String(processDetails[i].nomeProcesso).trim()+" = "+processDetails[i].statusProcesso+" CONT= "+cont);
                    if(processDetails[0].statusProcesso==0)
                    {
                        if((configSetup.sistemstart==true)&&(cont<2))
                        {
                            cont++;
                        }else
                        {
                            configSetup.enableRestartAll(true);
                            cont=0;
                            configSetup.sistemstart=false;
                        }
                    }else
                    {
                        configSetup.enableRestartAll(false);
                    }
                }
            }
            
            try
            {
                th.sleep(30*1000);
            }
            catch(InterruptedException e)
            {
                System.out.println("-- InterruptedException SSM_JF_MonitoringOutput.java ");
            }
        }
    }
}
