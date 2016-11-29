package net.etech.loadconfig;

/*
 * ADAMS_Vector_TAB_EVENTI_GUI.java
 *
 * Created on 4 agosto 2005, 12.15
 */

/**
 *
 * @author  root
 * @version 
 */
import java.awt.Cursor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_Vector_TAB_EVENTI_GUI extends java.util.Vector implements Runnable
{

    /** Creates new ADAMS_Vector_TAB_EVENTI_GUI */
    public ADAMS_Vector_TAB_EVENTI_GUI(javax.swing.JPanel Parent) 
    {
        super();
        this.parent = Parent;
        this.All_itemName =("TIPO_DI_CONFIGURAZIONE,"+   
                            "IDEXCEPTION,"+
                            "TAG,"+
                            "DESCRIPTION,"+
                            "IDTRIGGERRESTRICTION,"+
                            "TRIGGEREDSTATUS,"+
                            "TRIGGEREDVALUE,"+
                            "ACTION,"+
                            "TARGETVALUE,"+
                            "IDAGGREGATEEXCEPTION");
    }

    public ADAMS_Vector_TAB_EVENTI_GUI(String TIPO_DI_CONFIG) 
    {
        super();
        this.TIPO_DI_CONFIG=TIPO_DI_CONFIG;
        this.parent = null;
        this.All_itemName =("TIPO_DI_CONFIGURAZIONE,"+   
                            "IDEXCEPTION,"+
                            "TAG,"+
                            "DESCRIPTION,"+
                            "IDTRIGGERRESTRICTION,"+
                            "TRIGGEREDSTATUS,"+
                            "TRIGGEREDVALUE,"+
                            "ACTION,"+
                            "TARGETVALUE,"+
                            "IDAGGREGATEEXCEPTION");
    }
    
    public boolean read_items()
    {
        OperationTH = READ_ITEMS;
        
        th = null;
        th = new Thread(this,"read_items RS");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(1000);
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
        //System.out.println("RUN START");
        
        if(OperationTH == READ_ITEMS)
        {
             String sel_eventGUI = "select "+All_itemName+" from tab_gui_events"+
                                    " where TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIG+"' order by TAG";
                              
            //System.out.println("sel ----> "+sel_eventGUI);
            Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_eventGUI,SQLStatement);

            this.removeAllElements(); //elimino tutti i Eventi GUI
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {                        
                        ADAMS_TAB_EVENTI_GUI row_TAB_EVENTI_GUI = new ADAMS_TAB_EVENTI_GUI();
                        
                        
                    //*** Campi input DATA -- NOT Null sul DB ***//
                        row_TAB_EVENTI_GUI.TIPO_DI_CONFIGURAZIONE   = rs.getString ("TIPO_DI_CONFIGURAZIONE");
                        row_TAB_EVENTI_GUI.IDEXCEPTION              = rs.getInt ("IDEXCEPTION");
                    //***   
                        if(rs.getString ("TAG") != null)
                            row_TAB_EVENTI_GUI.TAG                  = rs.getString("TAG");
                        
                        if(rs.getString ("DESCRIPTION") != null)
                            row_TAB_EVENTI_GUI.DESCRIPTION          = rs.getString ("DESCRIPTION");

                        row_TAB_EVENTI_GUI.IDTRIGGERRESTRICTION     = rs.getInt ("IDTRIGGERRESTRICTION");
                        
                        row_TAB_EVENTI_GUI.TRIGGEREDSTATUS          = rs.getInt ("TRIGGEREDSTATUS");
                        
                        if(rs.getString ("TRIGGEREDVALUE") != null)
                            row_TAB_EVENTI_GUI.TRIGGEREDVALUE          = rs.getString ("TRIGGEREDVALUE");
                        
                        row_TAB_EVENTI_GUI.ACTION                   = rs.getInt ("ACTION");
                        
                        if(rs.getString ("TARGETVALUE") != null)
                            row_TAB_EVENTI_GUI.TARGETVALUE          = rs.getString ("TARGETVALUE");
                        
                        row_TAB_EVENTI_GUI.IDAGGREGATEEXCEPTION     = rs.getInt ("IDAGGREGATEEXCEPTION");

                        // Add Vector
                        this.addElement(row_TAB_EVENTI_GUI);
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
                System.out.println("(ADAMS_Vector_TAB_EVENTI_GUI-1) SQL Operation " + exc.toString());
            }
            
        }
        
        /// 
        OperationTH = -1;
        Thread_Work = false;
        if(parent!=null)
        {
            this.parent.setCursor(Cur_default);
        }
        //System.out.println("RUN END");
    }
    
    public int getIDEXCEPTION(String IDEXCEPTION_space_TAG)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_EVENTI_GUI appo_row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)this.elementAt(i);
            String StrCtrl = new String(appo_row_TAB_EVENTI_GUI.IDEXCEPTION+" "+appo_row_TAB_EVENTI_GUI.TAG);
            if(IDEXCEPTION_space_TAG.compareTo(StrCtrl) == 0)
                return appo_row_TAB_EVENTI_GUI.IDEXCEPTION;
        }
        return -1; // getSelectedIndex(0) == str_none("<none>)
    }

    public String getIDEXCEPTION_space_TAG(int IDEXCEPTION,String no_present)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_EVENTI_GUI appo_row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)this.elementAt(i);
            if(IDEXCEPTION == appo_row_TAB_EVENTI_GUI.IDEXCEPTION)
                return (new String(appo_row_TAB_EVENTI_GUI.IDEXCEPTION+" "+appo_row_TAB_EVENTI_GUI.TAG));
        }
        return no_present;
    }
    
    public String getTAG(int IDEXCEPTION)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_EVENTI_GUI appo_row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)this.elementAt(i);
            if(IDEXCEPTION == appo_row_TAB_EVENTI_GUI.IDEXCEPTION)
                return (appo_row_TAB_EVENTI_GUI.TAG);
        }
        return "";
    }
    
    
    // ------ Thread
    private Thread th           = null;
    private boolean Thread_Work = false;
    private int OperationTH     = -1;
    
    private final int READ_ITEMS = 0;
    
    private javax.swing.JPanel parent 	= null;
    private String TIPO_DI_CONFIG;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private String All_itemName;
}
