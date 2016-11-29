/*
 * daCancellare.java
 *
 * Created on 3 agosto 2005, 16.31
 */

/**
 *
 * @author  root
 */
import java.awt.Cursor;
public class ADAMS_JTab_TE_EXC_REL extends javax.swing.JPanel {

    /** Creates new form daCancellare */
    public ADAMS_JTab_TE_EXC_REL(ADAMS_TAB_INFO_CONFIG INFO_CONFIG,ADAMS_JF_AddMod_conf parent) {
        this.local_INFO_CONFIG = INFO_CONFIG;
        this.Parent = parent;
        initComponents();
        
        JP_DataElement = new ADAMS_JP_DataElement(local_INFO_CONFIG);
        jP_TE.add(JP_DataElement);
        
        JP_GUI_Event = new ADAMS_JP_Exceptions(local_INFO_CONFIG);
        jP_GUI_E.add(JP_GUI_Event);
        
        JP_Relations = new ADAMS_JP_Relations(local_INFO_CONFIG);
        jP_REL.add(JP_Relations);
        
        JP_Ghost_Relations = new ADAMS_JP_Ghost_Relations(local_INFO_CONFIG);
        jP_Ghost_REL.add(JP_Ghost_Relations);
        
        JP_Analisi = new ADAMS_JP_Analisi(local_INFO_CONFIG);
        jP_Analysis.add(JP_Analisi);
        
        JP_Counters = new ADAMS_JP_Counters(local_INFO_CONFIG);
        jP_CountersKit.add(JP_Counters);
        
        //JP_PluginHelp = new ADAMS_JP_PluginHelp(local_INFO_CONFIG);
        //jP_help.add(JP_PluginHelp);
        
        JP_Alarm_Kit = new ADAMS_JP_Alarm(local_INFO_CONFIG);
        jp_alarm.add(JP_Alarm_Kit);
        
        
        jTabbedPane1.addTab("Traffic Elements", new javax.swing.ImageIcon(getClass().getResource("/images/te_22.png")), jP_TE);
        jTabbedPane1.addTab("GUI Event", new javax.swing.ImageIcon(getClass().getResource("/images/gui_22.png")), jP_GUI_E);
        jTabbedPane1.addTab("Relations", new javax.swing.ImageIcon(getClass().getResource("/images/rel_22.png")), jP_REL);
        jTabbedPane1.addTab("Ghost Relations", new javax.swing.ImageIcon(getClass().getResource("/images/ghost_rel_22.png")), jP_Ghost_REL);
        jTabbedPane1.addTab("Analysis", new javax.swing.ImageIcon(getClass().getResource("/images/analysis_22.png")), jP_Analysis);
        jTabbedPane1.addTab("Counters", new javax.swing.ImageIcon(getClass().getResource("/images/counters_22.png")), jP_CountersKit);
        //jTabbedPane1.addTab("Plugin Help", new javax.swing.ImageIcon(getClass().getResource("/images/help_22.png")), jP_help);
        jTabbedPane1.addTab("Alarms", new javax.swing.ImageIcon(getClass().getResource("/images/alarm_min.gif")), jp_alarm);
        
        jTabbedPane1.setToolTipTextAt(0,"Traffic Elements");
        jTabbedPane1.setToolTipTextAt(1,"GUI Event");
        jTabbedPane1.setToolTipTextAt(2,"Relations");
        jTabbedPane1.setToolTipTextAt(3,"Ghost Relations");
        jTabbedPane1.setToolTipTextAt(4,"Analysis");
        jTabbedPane1.setToolTipTextAt(5,"Counters");
        jTabbedPane1.setToolTipTextAt(6,"Alarms");
        //jTabbedPane1.setToolTipTextAt(6,"Plugin Help");
        
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setCursor(Cur_hand);
        
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        }
        );
        
        int tabcount = jTabbedPane1.getTabCount();
        for(int i=0; i<tabcount; i++)
            jTabbedPane1.setEnabledAt(i,false);
    }

    ////// Metodi ADAMS_JP_DataElement
    public void setTableConfig()
    {
        jTabbedPane1.setSelectedIndex(0);
        Parent.setStatusIcon(Parent.jB_next,"b_next_tab.jpg","b_next_tab_over.jpg","b_next_tab_press.jpg");        
        JP_DataElement.setTableConfig(JP_GUI_Event.read_getEventGUI());
        
        int tabcount = jTabbedPane1.getTabCount();
        for(int i=0; i<tabcount; i++)
            jTabbedPane1.setEnabledAt(i,true);
    }
    
    public int getShowTab()
    {
        return jTabbedPane1.getSelectedIndex();
    }
    
    public void setShowTab(int indextab)
    {
        jTabbedPane1.setSelectedIndex(indextab);
    }
    
    public int getTableCount()
    {
        return jTabbedPane1.getTabCount();
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jP_TE = new javax.swing.JPanel();
        jP_GUI_E = new javax.swing.JPanel();
        jP_REL = new javax.swing.JPanel();
        jP_Ghost_REL = new javax.swing.JPanel();
        jP_Analysis = new javax.swing.JPanel();
        jP_CountersKit = new javax.swing.JPanel();
        jp_alarm = new javax.swing.JPanel();
        
        setLayout(new java.awt.BorderLayout());
        
        jP_TE.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("Traffic Elements", jP_TE);
        
        jP_GUI_E.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("GUI Events", jP_GUI_E);
        
        jP_REL.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("Relation", jP_REL);
        
        jP_Ghost_REL.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("Ghost Relations", jP_Ghost_REL);
        
        jP_Analysis.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("Analysis", jP_Analysis);
        
        jP_CountersKit.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("Counters", jP_CountersKit);
        
        jp_alarm.setLayout(new java.awt.GridLayout(1, 0));
        
        jTabbedPane1.addTab("Alarms", jp_alarm);
        
        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt)
    {
        this.setCursor(Cur_wait);
        
        int tabcount = jTabbedPane1.getTabCount();
        for(int i=0; i<tabcount; i++)
            jTabbedPane1.setEnabledAt(i,false);
       
                
        /*jTabbedPane1.setForegroundAt(indexTab,java.awt.Color.yellow);
        indexTab = jTabbedPane1.getSelectedIndex();
        jTabbedPane1.setForegroundAt(indexTab,java.awt.Color.black);*/
        
        indexTab = jTabbedPane1.getSelectedIndex();
        
        if(indexTab == 0) //Traffic Element
        {
            JP_DataElement.setV_TAB_EVENTI_GUI(JP_GUI_Event.getEventGUI());
            
            Parent.setStatusIcon(Parent.jB_back,"b_back.jpg","b_back_over.jpg","b_back_press.jpg");
            Parent.setStatusIcon(Parent.jB_next,"b_next_tab.jpg","b_next_tab_over.jpg","b_next_tab_press.jpg");
            Parent.jB_next.setVisible(true);
        }
        else if(indexTab == 1) // Gui Events
        {
            JP_GUI_Event.setTableConfig(JP_DataElement.getV_TAB_CONFIG());
            
            Parent.setStatusIcon(Parent.jB_back,"b_back_tab.jpg","b_back_tab_over.jpg","b_back_tab_press.jpg");
            Parent.setStatusIcon(Parent.jB_cancel,"close.jpg","close_over.jpg","close_press.jpg");
            Parent.jB_next.setVisible(true);
        }
        else if(indexTab == 2) // Releations
        {
            JP_Relations.setTableConfig(JP_DataElement.getV_TAB_CONFIG());
            Parent.setStatusIcon(Parent.jB_cancel,"close.jpg","close_over.jpg","close_press.jpg");
            Parent.jB_next.setVisible(true);
            
        }
        else if(indexTab == 3) // Ghost Releations
        {
            JP_Ghost_Relations.setTableConfig(JP_DataElement.getV_TAB_CONFIG());
            Parent.setStatusIcon(Parent.jB_cancel,"close.jpg","close_over.jpg","close_press.jpg");
            Parent.jB_next.setVisible(true);
        }
        else if(indexTab == 4) // Analysis
        {
            JP_Analisi.setTableConfig(JP_DataElement.getV_TAB_CONFIG());
            Parent.setStatusIcon(Parent.jB_cancel,"close.jpg","close_over.jpg","close_press.jpg");
            Parent.jB_next.setVisible(true);
        }
        else if(indexTab == 5) // Counters
        {
            JP_Counters.initCounters(JP_DataElement.getV_TAB_CONFIG());
            Parent.setStatusIcon(Parent.jB_cancel,"close.jpg","close_over.jpg","close_press.jpg");
            Parent.jB_next.setVisible(true);
            //Parent.setStatusIcon(Parent.jB_cancel,"b_finish.jpg","b_finish_over.jpg","b_finish_press.jpg");
            //Parent.jB_next.setVisible(false);
        }
        else if(indexTab == 6) //Alarms
        {
            //System.out.println("JP_Alarm_Kit.setTableConfig() ... prima ");
            JP_Alarm_Kit.setTableConfig(JP_DataElement.getV_TAB_CONFIG());
            //System.out.println("JP_Alarm_Kit.setTableConfig() ... dopo ");
            Parent.setStatusIcon(Parent.jB_cancel,"b_finish.jpg","b_finish_over.jpg","b_finish_press.jpg");
            Parent.jB_next.setVisible(false);
        }
        /*
        else if(indexTab == 6) // Plugin Help
        {
            Parent.setStatusIcon(Parent.jB_cancel,"b_finish.jpg","b_finish_over.jpg","b_finish_press.jpg");
            Parent.jB_next.setVisible(false);
        }*/
        
        for(int i=0; i<tabcount; i++)
            jTabbedPane1.setEnabledAt(i,true);
        
        this.setCursor(Cur_default);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jP_TE;
    private javax.swing.JPanel jP_GUI_E;
    private javax.swing.JPanel jP_REL;
    private javax.swing.JPanel jP_Ghost_REL;
    private javax.swing.JPanel jP_Analysis;
    private javax.swing.JPanel jP_CountersKit;
    private javax.swing.JPanel jp_alarm;
    // End of variables declaration//GEN-END:variables

    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    private ADAMS_JF_AddMod_conf Parent = null;
    
    private ADAMS_JP_DataElement  JP_DataElement   = null;
    private ADAMS_JP_Exceptions      JP_GUI_Event        = null;
    private ADAMS_JP_Relations       JP_Relations        = null;
    private ADAMS_JP_Ghost_Relations JP_Ghost_Relations  = null;
    private ADAMS_JP_Analisi         JP_Analisi          = null;
    private ADAMS_JP_Counters        JP_Counters         = null;
    private ADAMS_JP_PluginHelp      JP_PluginHelp       = null;
    private ADAMS_JP_Alarm           JP_Alarm_Kit        = null;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private int indexTab = 0;
}
