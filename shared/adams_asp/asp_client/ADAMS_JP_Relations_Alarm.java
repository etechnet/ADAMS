/**
 *
 * @author  root
 */
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.util.StringTokenizer;
import java.sql.*;

public class ADAMS_JP_Relations_Alarm extends javax.swing.JPanel {

    public ADAMS_JP_Relations_Alarm(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {

        initComponents();
        
        this.local_INFO_CONFIG = INFO_CONFIG;

         // *** Costruzione JTABLE TE ***/
        String[] all_columnNames2 ={"ID","Tag Relation","Handler Server"};
        int [] cellDimen_REL ={15,600,100};
        myTableModel_REL = new MyTableModel(all_columnNames2);
        sorter_REL = new TableSorter(myTableModel_REL);
        
        jTable_REL = new javax.swing.JTable(sorter_REL);
        JTableHeader header2 = jTable_REL.getTableHeader();
        header2.setFont(ADAMS_GlobalParam.font_B10);
        header2.setReorderingAllowed(false);

        sorter_REL.setTableHeader(header2);        
        
        jTable_REL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_REL.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_REL.setFont(new java.awt.Font("Verdana", 0, 9));
        jTable_REL.setRowHeight(20);
        jTable_REL.setRowMargin(2);
        jTable_REL.setAutoCreateColumnsFromModel(false);
        jTable_REL.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jTable_REL.setSelectionBackground(java.awt.Color.yellow);
        //jTable_REL.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        jScrollPane1_REL_A.setViewportView(jTable_REL); 
        
        jTable_REL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_RELMouseReleased(evt);
            }
        });
        // **************************** //
        
        SetTable(jTable_REL,15,cellDimen_REL);
        
        // -------- LISTS ALARMS ---------- 
        IcPool = new IconPool("/images/");
        jList_Disable_Alarm = new JListIcon(IcPool);
        jList_Enable_Alarm  = new JListIcon(IcPool); 
        
        /*jList_Disable_Alarm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListMousePressed(evt);
            }
        });

        jList_Enable_Alarm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListMousePressed(evt); 
            }
        }); */
        
        jList_Enable_Alarm.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_Disable_Alarm.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //SINGLE_INTERVAL_SELECTION
        
        V_Disable_Alarms = new java.util.Vector();
        V_Enable_Alarms = new java.util.Vector();
  
        jScroll1.setViewportView(jList_Disable_Alarm);
        jScroll2.setViewportView(jList_Enable_Alarm);

        // -------- LISTS ALARMS ----------
        
        
        //set Font
        jL_title.setFont(ADAMS_GlobalParam.font_B12);
        jList_Enable_Alarm.setFont(new java.awt.Font("Verdana", 0, 9));
        jList_Disable_Alarm.setFont(new java.awt.Font("Verdana", 0, 9));
        
        //Set Cursor
        jTable_REL.setCursor(Cur_hand);
        jB_Add_REL_A.setCursor(Cur_hand);
        jB_Mod_REL_A.setCursor(Cur_hand);
        jB_Remove_REL_A.setCursor(Cur_hand);
        jB_AlarmManagement.setCursor(Cur_hand);
        jB_commitAlarm.setCursor(Cur_hand);
        jList_Enable_Alarm.setCursor(Cur_hand);
        jList_Disable_Alarm.setCursor(Cur_hand);
        jB_Add.setCursor(Cur_hand);
        jB_AddAll.setCursor(Cur_hand);
        jB_Rem.setCursor(Cur_hand);
        jB_RemAll.setCursor(Cur_hand);
    }
    
    public void setTableConfig(ADAMS_Vector_TAB_CONFIG Parent_V_TAB_CONFIG)
    {
        this.parent_V_TAB_CONFIG = Parent_V_TAB_CONFIG;
        //System.out.println("parent_V_TAB_CONFIG size() ----> "+parent_V_TAB_CONFIG.size());

        // AGGIORNA TUTTO //
        read_RelationsAlarm();
        V_ALL_ALARM_GENERATOR = read_AllAlarms();
        refresh_ListEnableAlarm(V_ALL_ALARM_GENERATOR,new Vector());
        setGui();
       // AGGIORNA TUTTO //
    }
    
    private void SetTable(javax.swing.JTable jtable, int minCellDimension, int[] CellDimension)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jtable.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        TableColumn column = null;
        for(int i=0; i<jtable.getColumnCount(); i++)
        {
            column = jtable.getColumnModel().getColumn(i);            
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);            
        }
        
        if((jtable.isShowing())&&(jtable.isVisible()))
            jtable.updateUI();
                        
        this.setCursor(Cur_default);
    }
    
    private void read_RelationsAlarm()
    {
        jTable_REL.setCursor(Cur_wait);
        
        V_ALL_RELATION_ALARM = new Vector();
               
        String itemName =("ID_ALARM_RELATION,DESCRIPTION,ID_CONDITION_SCRIPT,STR_CONDITION_PLUGIN,TIPO_DI_CONFIGURAZIONE,COUNTERS_KIT_ID,TIME_FRACTION_ELEMENT_ID");       
        String sel_read_RelationsAlarm = ("select "+itemName+" from "+tab_alarm_relation+
                                            " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by DESCRIPTION" );
       
        //System.out.println();
        //System.out.println("sel_read_RelationsAlarm --> "+sel_read_RelationsAlarm);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_RelationsAlarm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    
            // lettura tab_alarm_relation
                    ADAMS_TAB_ALARM_RELATION row_AlarmRelation = new ADAMS_TAB_ALARM_RELATION();
                    row_AlarmRelation.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
                    
                    row_AlarmRelation.ID_ALARM_RELATION          = rs.getInt("ID_ALARM_RELATION"); 
                    String desc = rs.getString("DESCRIPTION");
                        if(desc != null)
                            row_AlarmRelation.DESCRIPTION = desc;
                    
                    row_AlarmRelation.ID_CONDITION_SCRIPT   = rs.getInt("ID_CONDITION_SCRIPT"); 
                    
                    String desc_pl = rs.getString("STR_CONDITION_PLUGIN");
                        if(desc_pl != null)
                            row_AlarmRelation.STR_CONDITION_PLUGIN = desc_pl;
                                        
                    row_AlarmRelation.COUNTERS_KIT_ID = rs.getInt("COUNTERS_KIT_ID");
                    row_AlarmRelation.TIME_FRACTION_ELEMENT_ID  = rs.getInt("TIME_FRACTION_ELEMENT_ID");
                    //row_AlarmRelation.FAMILY_ID = rs.getInt("FAMILY_ID");
                    
            // lettura tab_alarm_relation_elements
                    
                    String itemName_RE =("ID_ELEMENT,ENABLED_DETAIL");                    
                    String sel_read_RE =("select "+itemName_RE+" from "+tab_alarm_relation_elements+
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+
                                                        "' and ID_ALARM_RELATION = '"+row_AlarmRelation.ID_ALARM_RELATION+
                                                        "' order by POSITION_ELEMENT");
                    
                    Statement SQLStatement_RE = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rs_RE  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_RE,SQLStatement_RE);
                    
                    if(rs_RE != null)
                    {
                        while ( rs_RE.next ( ) ) 
                        {
                            row_AlarmRelation.V_ALARM_REL_ELEMENTS.addElement(new Integer(rs_RE.getInt("ID_ELEMENT")));
                            row_AlarmRelation.V_ENABLE_DETAIL_ELEMENTS.addElement(new Integer(rs_RE.getInt("ENABLED_DETAIL")));
                        }
                        
                    }
                    
             // lettura tab_alarm_relation_handlers

                    String itemName_Rh =("ID_ALARM_GENERATOR");                    
                    String sel_read_Rh =("select "+itemName_Rh+" from "+tab_alarm_relation_handlers+
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'"+
                                                        " and ID_ALARM_RELATION = '"+row_AlarmRelation.ID_ALARM_RELATION+"'");
                    
                    Statement SQLStatement_Rh = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rs_Rh  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_Rh,SQLStatement_Rh);
                    
                    if(rs_Rh != null)
                    {
                        while ( rs_Rh.next ( ) ) 
                        {
                            row_AlarmRelation.V_ALARM_HANDLERS.addElement(new Integer(rs_Rh.getInt("ID_ALARM_GENERATOR")));
                        }
                    }
              //--------------------- 
                    
                    V_ALL_RELATION_ALARM.addElement(row_AlarmRelation);
                    
                    try
                    {
                       SQLStatement_RE.close();
                       SQLStatement_Rh.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("(read_RelationsAlarm) SQL Operation " + exc.toString());
                    }
                }
            }
            catch (Exception ex) 
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
            System.out.println("(read_RelationsAlarm) SQL Operation " + exc.toString());
        }
        
        // Read All Server
        Vector V_SERVER_ALL_DB = getAlarmServer(null);
                                
        Object[][] dataValues = new Object[0][0];
        String[] all_columnNames2={"ID","Tag Relation (present #0)","Handler Server"};
        if(V_ALL_RELATION_ALARM != null)
        {
            int size_V_ALL_RA = V_ALL_RELATION_ALARM.size();
            all_columnNames2[1] = "Tag Relation (present #"+size_V_ALL_RA+")";
            
            dataValues = new Object[size_V_ALL_RA][3];
            for(int i=0; i<size_V_ALL_RA; i++)
            {
                ADAMS_TAB_ALARM_RELATION row_AlarmRelation = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);
                
                dataValues[i][0] = row_AlarmRelation.ID_ALARM_RELATION;
                
                String symbol_rel = "::";
                String str_Relation_RA = new String();
                int size_RE = row_AlarmRelation.V_ALARM_REL_ELEMENTS.size();
                for(int x=0; x<size_RE; x++)
                {
                    if(x == (size_RE-1))
                        symbol_rel = "";

                    String str_TAG_Element = parent_V_TAB_CONFIG.getTagTE(((Integer)row_AlarmRelation.V_ALARM_REL_ELEMENTS.elementAt(x)).intValue());
                    str_Relation_RA += str_TAG_Element +symbol_rel;
                    
                }
                
                dataValues[i][1] = str_Relation_RA;
                
                //Server 
                int serverID = get_ServerID(V_SERVER_ALL_DB,row_AlarmRelation.ID_ALARM_RELATION);
                if(serverID < 0)
                    dataValues[i][2] = ("< none >");
                else
                    dataValues[i][2] = ("Server ID: "+serverID);
                //Server
                
                //System.out.println("Relation Alarm --> "+str_Relation_RA);
            }
        }
       
        myTableModel_REL.setColumnName(all_columnNames2);
        myTableModel_REL.setDataValues(dataValues);
        sorter_REL.setTableModel(myTableModel_REL);          
        
        jScrollPane1_REL_A.setViewportView(jTable_REL);        
        jTable_REL.setCursor(Cur_hand);
   
    }
    
    public void read_SimpleCounterKit()
    {
        Vector_CountersKit_ID.removeAllElements();
        Vector_CountersKit_TAG.removeAllElements();
                
        String sel_SimpleReadCounters = ("select IDCOUNTER,TAG from tab_counters_kit "+
                                         "where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'");
        
        //System.out.println("----------- read_SimpleCounterKit() ----------- ");
        //System.out.println("select SimpleReadCounters "+sel_SimpleReadCounters );
        //System.out.println();
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_SimpleReadCounters,SQLStatement);

        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {                   
                    int IDCOUNTER  = rs.getInt("IDCOUNTER");          //not null sil DB

                    String TAG = rs.getString("TAG");
                    if(TAG == null)
                        TAG = "not defined";
                    
                    Vector_CountersKit_ID.addElement(new Integer(IDCOUNTER));
                    Vector_CountersKit_TAG.addElement(new String(TAG));
                }
            }
            catch (Exception ex) 
            { 
                System.out.println(ex.toString());
            }
        }
        
        try
        { SQLStatement.close(); }
        catch(java.sql.SQLException exc) 
        { System.out.println("read_SimpleCounterKit()" + exc.toString()); }
        
       /* for(int i=0; i<Vector_CountersKit_ID.size(); i++)
        {
            System.out.println("ID Counters = "+((Integer)(Vector_CountersKit_ID.elementAt(i))).toString()+" ");
            System.out.print(" TAG = "+((String)(Vector_CountersKit_TAG.elementAt(i))) );
            System.out.println();
        }*/
        
    }
        
    private void refresh_ListEnableAlarm(Vector V_all_alarm_generator, Vector V_alarm_handlers)
    {    
        int size_alarm_handlers = V_alarm_handlers.size();
        
        V_Disable_Alarms.removeAllElements();
        V_Enable_Alarms.removeAllElements();
        jList_Enable_Alarm.removeAll();
        jList_Disable_Alarm.removeAll();

        if(size_alarm_handlers == 0)
        {
            for(int x=0; x<V_all_alarm_generator.size(); x++)
            {
                
                ADAMS_TAB_ALARM_GENERATOR appo_AG = ((ADAMS_TAB_ALARM_GENERATOR)V_all_alarm_generator.elementAt(x));
                jList_Disable_Alarm.addElement("b_vcanc.png","b_vcanc.png",appo_AG.SHORT_DESCRIPTION);
                V_Disable_Alarms.addElement(appo_AG.SHORT_DESCRIPTION);
            }
        }
        else
        {
            for(int x=0; x<V_all_alarm_generator.size(); x++)
            {
                ADAMS_TAB_ALARM_GENERATOR appo_AG = ((ADAMS_TAB_ALARM_GENERATOR)V_all_alarm_generator.elementAt(x));
                boolean find = false;
                for(int i=0; i<size_alarm_handlers; i++ )
                {
                    int id_alarm_handlers = ((Integer)(V_alarm_handlers.elementAt(i))).intValue();
                    if(appo_AG.ID_ALARM_GENERATOR == id_alarm_handlers)  
                        find = true;              
                } 
                
                if(find)
                {
                    jList_Enable_Alarm.addElement("b_vok.png","b_vok.png",appo_AG.SHORT_DESCRIPTION);
                    V_Enable_Alarms.addElement(appo_AG.SHORT_DESCRIPTION);
                }                    
                else
                {
                    jList_Disable_Alarm.addElement("b_vcanc.png","b_vcanc.png",appo_AG.SHORT_DESCRIPTION);
                    V_Disable_Alarms.addElement(appo_AG.SHORT_DESCRIPTION);
                }
            }
        
        }
        jList_Enable_Alarm.repaint();
        jList_Disable_Alarm.repaint();
    }

    private Vector read_AllAlarms()
    {            
        Vector V_ALARMS = new Vector();
                 
        String itemName =("ID_ALARM_GENERATOR,SHORT_DESCRIPTION,LONG_DESCRIPTION,STR_PLUGIN,THRESHOLD_MANAGEMENT");                
        String sel_read_Alarms = ("select "+itemName+" from tab_alarm_generator where TIPO_DI_CONFIGURAZIONE = '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by SHORT_DESCRIPTION");
       
        //System.out.println();
        //System.out.println("sel_read_Alarms --> "+sel_read_Alarms);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_Alarms,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {                      
                    ADAMS_TAB_ALARM_GENERATOR row_Alarms = new ADAMS_TAB_ALARM_GENERATOR(this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
                    
                    row_Alarms.ID_ALARM_GENERATOR = rs.getInt("ID_ALARM_GENERATOR"); 
                                                            
                    String s_desc = rs.getString("SHORT_DESCRIPTION");
                        if(s_desc != null)
                            row_Alarms.SHORT_DESCRIPTION = s_desc;
                    
                    String desc = rs.getString("LONG_DESCRIPTION");
                        if(desc != null)
                            row_Alarms.LONG_DESCRIPTION = desc;
                    
                    String pl = rs.getString("STR_PLUGIN");
                        if(pl != null)
                            row_Alarms.STR_PLUGIN = pl;
                    
                    char TM = rs.getString("THRESHOLD_MANAGEMENT").charAt(0);
                    if(TM != ' ')
                        row_Alarms.THRESHOLD_MANAGEMENT = TM;                                        
                    
                    
                 // lettura tab_alarm_generator_thresholds

                    String itemName_Th =("ID_THRESHOLD_GENERATOR");                    
                    String sel_read_Th =("select "+itemName_Th+" from tab_alarm_generator_thresholds"+
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'"+
                                                        " and ID_ALARM_GENERATOR = '"+row_Alarms.ID_ALARM_GENERATOR+"'");
                    
                    Statement SQLStatement_Th = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    ResultSet rs_Th  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_Th,SQLStatement_Th);
                    
                    if(rs_Th != null)
                    {
                        while ( rs_Th.next ( ) ) 
                        {
                            row_Alarms.V_ALARM_THRESHOLD.addElement(new Integer(rs_Th.getInt("ID_THRESHOLD_GENERATOR")));
                        }
                    }
              //--------------------- 
                    
                    V_ALARMS.addElement(row_Alarms);
  
                }
            }
            catch (Exception ex) 
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
            System.out.println("(read_RelationsAlarm) SQL Operation " + exc.toString());
        }
        return V_ALARMS;
    }
    
    private void debug_VectorAlarm(Vector V_Disable_Alarms, Vector V_Enable_Alarms)
    {
         // DEBUG --- V_Enable_Alarms ---- //
        int SIZE_ENABLED = V_Enable_Alarms.size();
        System.out.println("Vettore Alarm ON size() "+SIZE_ENABLED);
        for (int i=0; i<SIZE_ENABLED; i++)
        {
            System.out.println("Alarm ENABLED --> "+(V_Enable_Alarms.elementAt(i)));
        }
        
         // DEBUG --- V_Disable_Alarms ---- //
        int SIZE_DISABLED = V_Disable_Alarms.size();
        System.out.println("Vettore Alarm OFF size() "+SIZE_DISABLED);
        for (int i=0; i<SIZE_DISABLED; i++)
        {
            System.out.println("Alarm DISABLE --> "+(V_Disable_Alarms.elementAt(i)));   
        }
    }
    
    
    private boolean ctrl_move()
    {        
        //String msg = ("Attenzione operazione di conferma non completata: premi < SI > per confermare l'abilitazione/disabilitazione degli allarmi. < NO > per ignorare.");
        
        String msg = ("Press < YES > to confirm your changes.");
        if(this.MOVE)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,msg,"Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                commitAlarms();
                return false;
            }
            else
                refreshAlarm_SelREL();
            
            this.MOVE = false;
        }
        return true;
    }
    
    
    
    private void move(JListIcon l1,JListIcon l2,String icon,java.util.Vector V1,java.util.Vector V2,boolean all) 
    {
        //System.out.println("move --- > flag all => "+all);
        if( (l1.getItemCount() > 0) || (l1.getSelectedIndices().length >0) )
            this.MOVE = true;
        
        if (all) 
        {
            for (int i=0; i<l1.getItemCount(); i++) 
            {
                l2.addElement(icon,icon,l1.get_ID_Value(i));                
                V2.add(V1.elementAt(i));//gestione vettori 
            }
            l1.removeAll();
            if(V1 != null)
                V1.removeAllElements();//gestione vettori 
            
        } 
        else 
        {
            try
            {
                String[] items = l1.getStringValues();
                if(items == null)
                    return;
                
                int[] itemIndexes = l1.getSelectedIndices();

                l2.clearSelection();
                for (int i=0; i<items.length; i++) 
                {
                    l2.addElement(icon,icon,items[i]); // add it
                    V2.addElement(V1.elementAt(itemIndexes[i]));//gestione vettori 

                    l2.setSelectedIndex(l2.getItemCount()-1); // and select it
                    if (i == 0) 
                    {
                        l2.setVisibleRowCount(l2.getItemCount()-1);
                    }
                }
                for (int i=itemIndexes.length-1; i>=0; i--) 
                {
                    l1.remElement(itemIndexes[i]);
                    V1.removeElementAt(itemIndexes[i]); //gestione vettori 
                    l1.setSelectedIndex(-1);
                    l1.removeSelectionInterval(0,l1.getItemCount()-1);
                }
            }
            catch(Exception e)
            {
                System.out.println("Exception move");
            }
        }
        l1.repaint();
        l2.repaint();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_north = new javax.swing.JPanel();
        jL_title = new javax.swing.JLabel();
        jP_Center = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1_REL_A = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jB_Add_REL_A = new javax.swing.JButton();
        jB_Mod_REL_A = new javax.swing.JButton();
        jB_Remove_REL_A = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScroll1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jB_Add = new javax.swing.JButton();
        jB_AddAll = new javax.swing.JButton();
        jB_Rem = new javax.swing.JButton();
        jB_RemAll = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScroll2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jB_AlarmManagement = new javax.swing.JButton();
        jB_commitAlarm = new javax.swing.JButton();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 5));
        jP_north.setLayout(new java.awt.BorderLayout());
        
        jP_north.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rel_32.png")));
        jL_title.setText("Relation Alarms Build");
        jL_title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(150, 40));
        jP_north.add(jL_title, java.awt.BorderLayout.CENTER);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        add(jP_north, gridBagConstraints1);
        
        jP_Center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_Center.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(null, "Alarm Relations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jPanel1.setMinimumSize(new java.awt.Dimension(232, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(213, 300));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jPanel1.add(jScrollPane1_REL_A, gridBagConstraints3);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setMinimumSize(new java.awt.Dimension(200, 10));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 10));
        jB_Add_REL_A.setText("New Alarm Relation");
        jB_Add_REL_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_Add_Mod_REL_AActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.weightx = 1.0;
        jPanel3.add(jB_Add_REL_A, gridBagConstraints4);
        
        jB_Mod_REL_A.setText("Modify Alarm Relation");
        jB_Mod_REL_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_Add_Mod_REL_AActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.weightx = 1.0;
        jPanel3.add(jB_Mod_REL_A, gridBagConstraints4);
        
        jB_Remove_REL_A.setText("Delete Alarm Relation");
        jB_Remove_REL_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_Remove_REL_AActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.weightx = 1.0;
        jPanel3.add(jB_Remove_REL_A, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel3, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_Center.add(jPanel1, gridBagConstraints2);
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(new javax.swing.border.TitledBorder(" Alarms "));
        jPanel2.setMinimumSize(new java.awt.Dimension(862, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(862, 400));
        jPanel6.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jPanel6.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 11));
        jLabel2.setText("Disabled Alarms");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel6.add(jLabel2, gridBagConstraints6);
        
        jScroll1.setBackground(new java.awt.Color(230, 230, 230));
        jScroll1.setMaximumSize(new java.awt.Dimension(200, 200));
        jScroll1.setMinimumSize(new java.awt.Dimension(200, 200));
        jScroll1.setPreferredSize(new java.awt.Dimension(200, 200));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        jPanel6.add(jScroll1, gridBagConstraints6);
        
        jPanel7.setLayout(new java.awt.GridLayout(4, 1));
        
        jPanel7.setBackground(new java.awt.Color(230, 230, 230));
        jB_Add.setBackground(new java.awt.Color(230, 230, 230));
        jB_Add.setFont(new java.awt.Font("Helvetica", 1, 12));
        jB_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_dx.gif")));
        jB_Add.setBorderPainted(false);
        jB_Add.setContentAreaFilled(false);
        jB_Add.setFocusPainted(false);
        jB_Add.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_Add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_dx_press.gif")));
        jB_Add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_dx_over.gif")));
        jB_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ListActionPerformed(evt);
            }
        });
        
        jPanel7.add(jB_Add);
        
        jB_AddAll.setBackground(new java.awt.Color(230, 230, 230));
        jB_AddAll.setFont(new java.awt.Font("Helvetica", 1, 12));
        jB_AddAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_dxdx.gif")));
        jB_AddAll.setBorderPainted(false);
        jB_AddAll.setContentAreaFilled(false);
        jB_AddAll.setFocusPainted(false);
        jB_AddAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_AddAll.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_dxdx_press.gif")));
        jB_AddAll.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_dxdx_over.gif")));
        jB_AddAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ListActionPerformed(evt);
            }
        });
        
        jPanel7.add(jB_AddAll);
        
        jB_Rem.setBackground(new java.awt.Color(230, 230, 230));
        jB_Rem.setFont(new java.awt.Font("Helvetica", 1, 12));
        jB_Rem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_sx.gif")));
        jB_Rem.setBorderPainted(false);
        jB_Rem.setContentAreaFilled(false);
        jB_Rem.setFocusPainted(false);
        jB_Rem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_Rem.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_sx_press.gif")));
        jB_Rem.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_sx_over.gif")));
        jB_Rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ListActionPerformed(evt);
            }
        });
        
        jPanel7.add(jB_Rem);
        
        jB_RemAll.setBackground(new java.awt.Color(230, 230, 230));
        jB_RemAll.setFont(new java.awt.Font("Helvetica", 1, 12));
        jB_RemAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_sxsx.gif")));
        jB_RemAll.setBorderPainted(false);
        jB_RemAll.setContentAreaFilled(false);
        jB_RemAll.setFocusPainted(false);
        jB_RemAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_RemAll.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_sxsx_press.gif")));
        jB_RemAll.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arr_sxsx_over.gif")));
        jB_RemAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ListActionPerformed(evt);
            }
        });
        
        jPanel7.add(jB_RemAll);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints6.weighty = 1.0;
        jPanel6.add(jPanel7, gridBagConstraints6);
        
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 11));
        jLabel3.setText("Enabled Alarms");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 2;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel6.add(jLabel3, gridBagConstraints6);
        
        jScroll2.setBackground(new java.awt.Color(230, 230, 230));
        jScroll2.setMaximumSize(new java.awt.Dimension(200, 200));
        jScroll2.setMinimumSize(new java.awt.Dimension(200, 200));
        jScroll2.setPreferredSize(new java.awt.Dimension(200, 200));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 2;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        jPanel6.add(jScroll2, gridBagConstraints6);
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        jPanel2.add(jPanel6, gridBagConstraints5);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints7;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 10));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 10));
        jB_AlarmManagement.setText("Alarm Management");
        jB_AlarmManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_AlarmManagementActionPerformed(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints7.weightx = 1.0;
        jPanel4.add(jB_AlarmManagement, gridBagConstraints7);
        
        jB_commitAlarm.setText("Confirm Alarm");
        jB_commitAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitAlarmActionPerformed(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 1;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel4.add(jB_commitAlarm, gridBagConstraints7);
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jPanel4, gridBagConstraints5);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_Center.add(jPanel2, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_Center, gridBagConstraints1);
        
    }//GEN-END:initComponents

    
    private void setGui()
    {
         //System.out.println("--- setGui() ---");
        
        if(jTable_REL.getSelectedRow() >= 0)
        {
            jB_Add_REL_A.setEnabled(true);
            jB_Mod_REL_A.setEnabled(true);
            jB_Remove_REL_A.setEnabled(true);
            jB_AlarmManagement.setEnabled(true);
            jB_commitAlarm.setEnabled(true);
            
            setGuiAlarm(true);
            
        }
        else
        {
            jB_Add_REL_A.setEnabled(true);
            jB_Mod_REL_A.setEnabled(false);
            jB_Remove_REL_A.setEnabled(false);
            jB_AlarmManagement.setEnabled(true);
            jB_commitAlarm.setEnabled(false);
            
            setGuiAlarm(false);
        }
    }
    private void setGuiAlarm(boolean flag)
    {
        // Enabel Alarm
        jList_Disable_Alarm.setEnabled(flag);
        jList_Enable_Alarm.setEnabled(flag);

        jB_Add.setEnabled(flag);
        jB_AddAll.setEnabled(flag);
        jB_Rem.setEnabled(flag);
        jB_RemAll.setEnabled(flag);

    }
    
    
    public int getIdAlarm(String str_alarm)
    {
        for(int i=0; i<V_ALL_ALARM_GENERATOR.size(); i++)
        {            
            ADAMS_TAB_ALARM_GENERATOR ag = (ADAMS_TAB_ALARM_GENERATOR)V_ALL_ALARM_GENERATOR.elementAt(i);
            if(ag.SHORT_DESCRIPTION.compareTo(str_alarm) == 0)
                return ag.ID_ALARM_GENERATOR;
        }
        return -1;
    }

    private Vector get_ID_THRESHOLD_GENERATOR(int ID_ALARM_GENERATOR)
    {
        if(V_ALL_ALARM_GENERATOR != null)
        {
            for(int i=0; i<V_ALL_ALARM_GENERATOR.size(); i++)
            {            
                ADAMS_TAB_ALARM_GENERATOR ag = (ADAMS_TAB_ALARM_GENERATOR)V_ALL_ALARM_GENERATOR.elementAt(i);
                if(ag.ID_ALARM_GENERATOR == ID_ALARM_GENERATOR)
                    return ag.V_ALARM_THRESHOLD;
            }
        }
        return null;
    }
    
    private void commitAlarms()
    {
        this.setCursor(Cur_wait);
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            this.setCursor(Cur_default);
            return;
        }
        
        //System.out.println("AlarmRelation_Selected.ID_ALARM_RELATION = "+AlarmRelation_Selected.ID_ALARM_RELATION);
        
        //System.out.println("V_Enable_Alarms ----> SIZE() "+V_Enable_Alarms.size());
        
        AlarmRelation_Selected.V_ALARM_HANDLERS.removeAllElements();
        for(int i=0; i<V_Enable_Alarms.size(); i++)
        {                       
            int idAlarm = getIdAlarm((String)V_Enable_Alarms.elementAt(i));
            
            //System.out.print(" is ALARM ENABLED ==> ID = "+V_Enable_Alarms.elementAt(i));
            //System.out.println(" desc = "+V_Enable_Alarms.elementAt(i));
            
            if(idAlarm != -1)
                AlarmRelation_Selected.V_ALARM_HANDLERS.addElement(new Integer(idAlarm));
            else
            {
                System.out.println("err. Id Alarm not found....  getIdAlarm(...)");
                return;
            }
            
        }
        
        int id_alarmRelation = AlarmRelation_Selected.ID_ALARM_RELATION;
        int Answer = refresh_DB_ALARM(AlarmRelation_Selected);
        
        String msg =("< "+id_alarmRelation+" - "+AlarmRelation_Selected.DESCRIPTION+" >");
        
        if(Answer >= 0)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Enable Alarms Relation "+msg+" has been changed.","INFO",3);
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"-- INFO -- \nAlarm Relation "+msg+" has been changed , remember to update Alarm Policy.","INFO",3,400,200);
        }
        else
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert/change Enable Alarms Relation: "+msg+".","ERROR",1);
        
        read_RelationsAlarm();
        V_ALL_ALARM_GENERATOR = read_AllAlarms();
        
        if(V_ALL_RELATION_ALARM != null)
        {                                               
            for(int i=0; i<V_ALL_RELATION_ALARM.size(); i++)
            {
                AlarmRelation_Selected = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);                    
                if(AlarmRelation_Selected.ID_ALARM_RELATION == id_alarmRelation)
                    break;
            }
        }
        refresh_ListEnableAlarm(V_ALL_ALARM_GENERATOR,AlarmRelation_Selected.V_ALARM_HANDLERS);
        
        try
        {   //seleziona riga JTable
            for(int i=0; i<jTable_REL.getRowCount(); i++)
            {
                int ID_TABLE = new Integer(""+jTable_REL.getValueAt(i,0)).intValue();
                if( ID_TABLE == id_alarmRelation)
                {
                    jTable_REL.setRowSelectionInterval(i,i);
                    jScrollPane1_REL_A.validate();
                    break;
                }
            }
        }catch(Exception e){}
        
        setGui();
        this.MOVE = false;
        this.setCursor(Cur_default);
    }
    
    private void jB_commitAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitAlarmActionPerformed
        commitAlarms();
    }//GEN-LAST:event_jB_commitAlarmActionPerformed

    private int refresh_DB_ALARM(ADAMS_TAB_ALARM_RELATION alarm_Relation)
    {
        //System.out.println("refresh_DB_ALARM alarm_Relation.id -->"+alarm_Relation.ID_ALARM_RELATION);
        
        String str_delete_1 = "delete from "+tab_alarm_relation_handlers+" where TIPO_DI_CONFIGURAZIONE='"+alarm_Relation.TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and ID_ALARM_RELATION="+alarm_Relation.ID_ALARM_RELATION;
        
        //System.out.println("esito delete -->> = "+str_delete_1);
        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_1);
        int Answer_Ins_AR   = -1;

        // ++++++++++++++++++++++++ tab_alarm_relation_handlers +++++++++++++++++++++++++++++++++++

        
        int size_AL = alarm_Relation.V_ALARM_HANDLERS.size();
        if(size_AL > 0)
        {
            for(int i=0; i<size_AL; i++)
            {
                int id_alarmGenerator = ((Integer)alarm_Relation.V_ALARM_HANDLERS.elementAt(i)).intValue();

                String itemName_AL =("ID_ALARM_RELATION,ID_ALARM_GENERATOR,TIPO_DI_CONFIGURAZIONE");

                String itemValue_AL = (alarm_Relation.ID_ALARM_RELATION+","+id_alarmGenerator+","+ 
                                       "'"+alarm_Relation.TIPO_DI_CONFIGURAZIONE+"'");

                String str_Insert_AL = ("insert into "+tab_alarm_relation_handlers+" ("+itemName_AL+") values ( "+itemValue_AL+" )");
                //System.out.println("str_Insert_AL --> "+str_Insert_AL);

                Answer_Ins_AR = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_AL);

                if(Answer_Ins_AR < 0)
                {
                    System.out.println("ERROR insert into tab_alarm_relation_handlers");
                    break;
                }
            }
        }
        else
        {
            //System.out.println("Non Ci sono Allarmi abilitati");
            Answer_Ins_AR = 0;
        }
        
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        //T_POLICY - T_TRAFFIC_RELATION ----- gestione KPI 
       
        Vector v_relationPolcy = readRelationsPolicy(alarm_Relation.ID_ALARM_RELATION,alarm_Relation.TIPO_DI_CONFIGURAZIONE);
        int num_relationPolcy = v_relationPolcy.size();
                        
        // RESET T_POLITICHE e T_TRAF_REL
        String str_delete_T_POLITICHE = "delete from T_POLICY where TRAFF_ID="+alarm_Relation.ID_ALARM_RELATION;
        String str_delete_T_TRAF_REL = "delete from T_TRAFFIC_RELATION where TRAFF_ID="+alarm_Relation.ID_ALARM_RELATION+" and TIPO_DI_CONFIGURAZIONE='"+alarm_Relation.TIPO_DI_CONFIGURAZIONE+"'";
       

        ADAMS_OracleConnection CONNECT_ORA_2 = new ADAMS_OracleConnection();
        boolean CONN_ORACLE = CONNECT_ORA_2.DBConnect("RDA","ORACLE","ops$rdaexec");
        
        //System.out.println("str_delete_T_POLITICHE --> "+str_delete_T_POLITICHE);
        //System.out.println("str_delete_T_POLITICHE --> "+str_delete_T_TRAF_REL);
        
        int Answer_del_P  = -1;
        int Answer_del_RT = -1;
        int Answer_del_THR = -1;
        int insert_P_TR = -1;
        if(CONN_ORACLE)
        {
            try
            {        
                CONNECT_ORA_2.DBConnection.setAutoCommit(false);               
            }catch(java.sql.SQLException exc){exc.printStackTrace();}


            Answer_del_P  = CONNECT_ORA_2.Operation(str_delete_T_POLITICHE);
            Answer_del_RT = CONNECT_ORA_2.Operation(str_delete_T_TRAF_REL);

            // RESET T_POLITICHE e T_TRAF_REL  

            //System.out.println("Answer_del_P "+Answer_del_P);
            //System.out.println("Answer_del_RT "+Answer_del_RT);


            try
            { 
                if ((Answer_del_P == Answer_del_RT))
                    CONNECT_ORA_2.DBConnection.commit();
                else
                    CONNECT_ORA_2.DBConnection.rollback();

                CONNECT_ORA_2.DBConnection.setAutoCommit(false);
             }catch(java.sql.SQLException exc){exc.printStackTrace();}       

            ADAMS_T_POLITICHE rowPolicy = null;
            if(size_AL > 0)
            {
                for(int x=0; x<size_AL; x++)
                {
                    int id_alarmGenerator = ((Integer)alarm_Relation.V_ALARM_HANDLERS.elementAt(x)).intValue();

                    rowPolicy = null;
                    for(int y=0; y<num_relationPolcy; y++)
                    {
                        ADAMS_T_POLITICHE appoRP = (ADAMS_T_POLITICHE)v_relationPolcy.elementAt(y);
                        if(id_alarmGenerator == (appoRP.ID_KPI))
                        {
                            rowPolicy = appoRP;
                            break;
                        }                    
                    }

                    if(rowPolicy != null)
                    {
                        rowPolicy.set_OracleConnection(CONNECT_ORA_2);

                        insert_P_TR = rowPolicy.insert_T_POLITICHE() + insert_P_TR;
                    }
                    else
                    {
                        rowPolicy = new ADAMS_T_POLITICHE(CONNECT_ORA_2);

                        rowPolicy.TRAFF_ID    = alarm_Relation.ID_ALARM_RELATION;
                        rowPolicy.POL_ID      = 'M';      
                        rowPolicy.INTERVALLO  = 1;       
                        rowPolicy.PERSISTENZA = 2;       
                        rowPolicy.COEFF       = 1.0F;     
                        rowPolicy.ID_KPI      = id_alarmGenerator;       
                        rowPolicy.DESCR       = alarm_Relation.DESCRIPTION;        
                        rowPolicy.DESC_COMP   ="Not Defined";
                        rowPolicy.TIPO_DI_CONFIGURAZIONE = alarm_Relation.TIPO_DI_CONFIGURAZIONE;

                        insert_P_TR = rowPolicy.insert_T_POLITICHE() + insert_P_TR;
                    }
                }
            }
            else
            {
                rowPolicy  = new ADAMS_T_POLITICHE(CONNECT_ORA_2);

                rowPolicy.TRAFF_ID    = alarm_Relation.ID_ALARM_RELATION;
                rowPolicy.POL_ID      = 'M';      
                rowPolicy.INTERVALLO  = 1;       
                rowPolicy.PERSISTENZA = 2;       
                rowPolicy.COEFF       = 1.0F;     
                rowPolicy.ID_KPI      = -1;       
                rowPolicy.DESCR       = alarm_Relation.DESCRIPTION;        
                rowPolicy.DESC_COMP   ="Not Defined";
                rowPolicy.TIPO_DI_CONFIGURAZIONE = alarm_Relation.TIPO_DI_CONFIGURAZIONE;

                insert_P_TR = rowPolicy.insert_T_POLITICHE() + insert_P_TR;
            }

            // -------- cancellazione eventuali T_THR_OVERRIDE       
            Answer_del_THR = delete_T_THR_OVERRIDE(alarm_Relation,CONNECT_ORA_2); // Connessione e Commit la DataBase ESTERNA AL DB.   
            // END -------- T_THR_OVERRIDE
        }
        /////////////
        
       //System.out.println("Answer_del_P="+Answer_del_P+" Answer_del_RT="+Answer_del_RT);
       //System.out.println("insert_P_TR "+insert_P_TR);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                   
        try
        {         
            if( (Answer_del >= 0) && (Answer_Ins_AR >= 0) && (Answer_del_P == Answer_del_RT) && (insert_P_TR >= 0) )
            {                
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(alarm_Relation.TIPO_DI_CONFIGURAZIONE);
                
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                CONNECT_ORA_2.DBConnection.commit();
                //System.out.println("Commit OK");
            }
            else
            {                
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                CONNECT_ORA_2.DBConnection.rollback();
                Answer_Ins_AR = -1;                
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            CONNECT_ORA_2.DBConnection.setAutoCommit(true);
            CONNECT_ORA_2.Close();
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 2");
            try
            { 
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
            catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_ALARM_RELATION.java 2A");}
        }
        
        return Answer_Ins_AR;
    }
    
    private int delete_T_THR_OVERRIDE(ADAMS_TAB_ALARM_RELATION alarm_Relation,ADAMS_OracleConnection connectDB)
    {
        int size_AL = alarm_Relation.V_ALARM_HANDLERS.size();
        
         // -------- cancellazione eventuali T_THR_OVERRIDE
        int Answer_del_THR = -1;
        String delete_THR_Override = "";
        if(size_AL > 0)
        {
            String str_id_th = "";
            String dot = ",";
            for(int x=0; x<size_AL; x++)
            {
                Vector v_id_Thresholds = get_ID_THRESHOLD_GENERATOR( ((Integer)alarm_Relation.V_ALARM_HANDLERS.elementAt(x)).intValue() );
                if(v_id_Thresholds != null)
                {                    
                    int size_th = v_id_Thresholds.size();                    
                    for(int z=0; z<size_th; z++)
                    {
                        int idTh = ((Integer)v_id_Thresholds.elementAt(z)).intValue();                        
                        if(str_id_th.indexOf(" "+idTh+dot) == -1)
                                str_id_th += " "+idTh+dot;
                        //else
                            //System.out.println("-----> gia' presente ="+" "+idTh+dot);
                        
                       // System.out.println("str_id_th ["+x+"]["+z+"] = "+str_id_th);
                    }
                }
                else
                    System.out.println("Errore...... get_ID_THRESHOLD_GENERATOR(.....) --> null");
            }
           
            if( str_id_th.endsWith(",") )
                str_id_th = str_id_th.substring(0,str_id_th.length()-1);
            
            //System.out.println("FINALE str_id_th "+str_id_th);
            
            if(str_id_th.length() > 0)
                delete_THR_Override = "delete from T_THR_OVERRIDE where ID_RELATION="+alarm_Relation.ID_ALARM_RELATION+" and ID_THRESHOLD not in ("+str_id_th+")";
            else
            {
                delete_THR_Override = "delete from T_THR_OVERRIDE where ID_RELATION="+alarm_Relation.ID_ALARM_RELATION;
                //System.out.println(" >>>>>>>>>>> str_id_th.length() == 0) >>>>>>>>>>>> "+str_id_th);
            }
        }
        else
            delete_THR_Override = "delete from T_THR_OVERRIDE where ID_RELATION="+alarm_Relation.ID_ALARM_RELATION;

        
        //System.out.println("delete_THR_Override ---> "+delete_THR_Override);
        Answer_del_THR = connectDB.Operation(delete_THR_Override);
        //System.out.println("Cancellate su T_THR_OVERRIDE num rows --> "+Answer_del_THR);
        
        return Answer_del_THR;
    }
        
    private int ctrl_All_T_THR_OVERRIDE(Vector v_all_ReleationAlarm,ADAMS_OracleConnection connectDB)
    {
        int Answer_del_THR = 0;
        int size_REL = v_all_ReleationAlarm.size();
        for(int i=0; i<size_REL;i++)
        {
            ADAMS_TAB_ALARM_RELATION alarm_Relation = (ADAMS_TAB_ALARM_RELATION)v_all_ReleationAlarm.elementAt(i);
            if(Answer_del_THR >= 0)
                Answer_del_THR = delete_T_THR_OVERRIDE(alarm_Relation,connectDB);
        }
        return Answer_del_THR;
    }
    
    
    private Vector readRelationsPolicy(int TRAFF_ID,String TIPO_DI_CONFIGURAZIONE)
    {
        Vector v_RelationPolicy = new Vector();
        
        String sel_ReadPolicy = "select distinct a.TRAFF_ID, a.POL_ID, a.INTERVALLO, a.PERSISTENZA, a.COEFF, a.ID_KPI as idKPI, "+
                                            "b.DESCR, b.DESC_COMP, b.TIPO_DI_CONFIGURAZIONE from "+
                                            "T_POLICY a, T_TRAFFIC_RELATION b "+
                                            "where a.TRAFF_ID=b.TRAFF_ID and a.TRAFF_ID="+TRAFF_ID+" and b.TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'";
        
        ADAMS_OracleConnection CONNECT_ORA_2 = new ADAMS_OracleConnection();
        boolean CONN_ORACLE = CONNECT_ORA_2.DBConnect("RDA","ORACLE","ops$rdaexec");
           
        if(CONN_ORACLE)
        {
            //System.out.println("sel_ReadPolicy >>>>>>>>> "+sel_ReadPolicy);
            Statement SQLStatement = CONNECT_ORA_2.createLocalStatement();
            ResultSet rs  = CONNECT_ORA_2.Query_RS_Statement(sel_ReadPolicy,SQLStatement);
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {
                        ADAMS_T_POLITICHE row_t_politiche = new ADAMS_T_POLITICHE();

                    //*** Campi Politiche  -- alcuni campi sono NOT Null sul DB ***//
                        row_t_politiche.TRAFF_ID    = rs.getInt("TRAFF_ID");                    //not null sil DB
                        row_t_politiche.POL_ID      = rs.getString("POL_ID").charAt(0);         //not null sil DB
                        row_t_politiche.INTERVALLO  = rs.getInt("INTERVALLO");                  //not null sil DB
                        
                        if (rs.getObject("PERSISTENZA") == null)
                            row_t_politiche.PERSISTENZA = -1;
                        else
                            row_t_politiche.PERSISTENZA = rs.getInt("PERSISTENZA");
                        
                        row_t_politiche.COEFF = rs.getFloat("COEFF");                      //not null sil DB
                        
                        //*****************
                        row_t_politiche.DESCR = rs.getString("DESCR");       //not null sil DB

                        String descComp = rs.getString("DESC_COMP");
                        if(descComp != null)
                            row_t_politiche.DESC_COMP = descComp;
                        ////////////////////////
                        row_t_politiche.ID_KPI  = rs.getInt("idKPI");
                        row_t_politiche.TIPO_DI_CONFIGURAZIONE  = rs.getString("TIPO_DI_CONFIGURAZIONE");

                        v_RelationPolicy.addElement(row_t_politiche);                       
                        
                    }
                }catch (Exception ex) 
                { 
                    ex.printStackTrace();
                }
            }                        
            try
            {
                SQLStatement.close();
                CONNECT_ORA_2.Close();
            }
            catch(java.sql.SQLException exc) 
            {
                 exc.printStackTrace();
            }
        }
        return v_RelationPolicy;
    }
    
    private void jB_AlarmManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_AlarmManagementActionPerformed
        
        if(ctrl_move() == false)
            return;
                             
         ADAMS_JD_ALARM jd_Alarm = new ADAMS_JD_ALARM(true," Alarm Management ",this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
         
         // AGGIORNA TUTTO //
        read_RelationsAlarm();
        V_ALL_ALARM_GENERATOR = read_AllAlarms();
        refresh_ListEnableAlarm(V_ALL_ALARM_GENERATOR,new Vector());
        setGui();
       // AGGIORNA TUTTO //
        
        
        /////////// ---------- T_THR_OVERRIDE
        ADAMS_OracleConnection connect = new ADAMS_OracleConnection();
        boolean CONN_ORACLE = connect.DBConnect("RDA","ORACLE","ops$rdaexec");
          
        if(CONN_ORACLE)
        {
            try
            {        
                connect.DBConnection.setAutoCommit(false);               
            }catch(java.sql.SQLException exc){exc.printStackTrace();}

            int Answer_del = 0;
            Answer_del = ctrl_All_T_THR_OVERRIDE(V_ALL_RELATION_ALARM,connect);
            
            try
            {         
                if( (Answer_del >= 0) )            
                    connect.DBConnection.commit();
                else               
                    connect.DBConnection.rollback();

                connect.Close();
            }
            catch(java.sql.SQLException exc)
            {                
                connect.Close();
            }
        }
        else
            System.out.println("-- Connessione oracle fallita --");
        /////////// ---------- T_THR_OVERRIDE
         
    }//GEN-LAST:event_jB_AlarmManagementActionPerformed

    private void jB_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ListActionPerformed
        Object target = evt.getSource();         
               
        if(target == jB_Add)
        {
            move(jList_Disable_Alarm,jList_Enable_Alarm,"b_vok.png",V_Disable_Alarms,V_Enable_Alarms,false);
        }
        else if(target == jB_AddAll)
        {
            move(jList_Disable_Alarm,jList_Enable_Alarm,"b_vok.png",V_Disable_Alarms,V_Enable_Alarms,true);   
        }
        else if(target == jB_Rem)
        {
            move(jList_Enable_Alarm,jList_Disable_Alarm,"b_vcanc.png",V_Enable_Alarms,V_Disable_Alarms,false);
        }
        else if(target == jB_RemAll)
        {
	    move(jList_Enable_Alarm,jList_Disable_Alarm,"b_vcanc.png",V_Enable_Alarms,V_Disable_Alarms,true);
        }
        
        //debug_VectorAlarm(V_Disable_Alarms,V_Enable_Alarms);
    }//GEN-LAST:event_jB_ListActionPerformed

       
   /* private void jListMousePressed(java.awt.event.MouseEvent evt) 
    {
         System.out.println("jListMousePressed");
    } */
    
    private void jB_Remove_REL_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_Remove_REL_AActionPerformed
       this.setCursor(Cur_wait);
        
        if(ctrl_move() == false)
            return;

        //System.out.println("REMOVE RELATION");
        
        int selectedRow = jTable_REL.getSelectedRow();
        if(selectedRow >= 0)
        {     
            int id_AR_deleted = ((Integer)jTable_REL.getValueAt(selectedRow,0)).intValue();
            
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: the following Alarm Relation ID #"+id_AR_deleted+" will be deleted and all associated thresholds.\nPlease verify and confirm operation.","Warning",6,320,200);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {       
                String str_TAG_AR = ""+jTable_REL.getValueAt(selectedRow,1);

               //System.out.println("Id relazione da Cancellare "+id_AR_deleted);

                int Answer_del = -1;
                if(V_ALL_RELATION_ALARM != null)
                {                                               
                    for(int i=0; i<V_ALL_RELATION_ALARM.size(); i++)
                    {
                        ADAMS_TAB_ALARM_RELATION row_AlarmRelation_delete = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);

                        if(row_AlarmRelation_delete.ID_ALARM_RELATION == id_AR_deleted)
                        {
                            Answer_del = row_AlarmRelation_delete.delete_ALARM_RELATION();
                            
                            if(Answer_del >= 0)
                            {
                                // ************* RESET SERVER  
                                
                                int sql_operation = 0;
                                Vector V_SERVER_ROW_DB = getAlarmServer(null);
                                int serverID_reset = get_ServerID(V_SERVER_ROW_DB,row_AlarmRelation_delete.ID_ALARM_RELATION);
                                if(serverID_reset != -1)
                                {
                                    if(countRow_ForServerID(V_SERVER_ROW_DB,serverID_reset) == 1)
                                    {                                                           
                                        String str_reset_1 =   "update tab_alarm_server_master set ID_ALARM_RELATION=-1"+                                     
                                                                " where TIPO_DI_CONFIGURAZIONE='"+row_AlarmRelation_delete.TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+serverID_reset+" and ID_ALARM_RELATION ="+row_AlarmRelation_delete.ID_ALARM_RELATION+"";

                                       //System.out.println("str_reset_1 " +str_reset_1);
                                        sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_reset_1);
                                    }
                                    else
                                    {                                        
                                        String str_delete_1 = "delete from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+row_AlarmRelation_delete.TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+serverID_reset+" and ID_ALARM_RELATION ="+row_AlarmRelation_delete.ID_ALARM_RELATION+"";
                                        
                                       //System.out.println("str_delete_1 " +str_delete_1);
                                        sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_1);
                                    }                                
                                }
                                //else
                                    //System.out.println("--- RESET SERVER non devo fare nulla non era impostato!! ---");
                                // END ************* RESET  SERVER
                            }
                            
                            break;
                        }
                    }

                    if(Answer_del >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Relation ID #"+id_AR_deleted+" has been deleted.","INFO",3);
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Relation ID #"+id_AR_deleted+" has been deleted in ALARM POLICY and Override Alarm Thresholds.","INFO",3,400,200);
                    }         
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);

                    read_RelationsAlarm();
                    V_ALL_ALARM_GENERATOR = read_AllAlarms();
                    refresh_ListEnableAlarm(V_ALL_ALARM_GENERATOR,new Vector());

                    setGui();
                }
            }
        }
        else
        {
            ADAMS_GlobalParam.optionPanel("Select an < Alarm Relation > from the list.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE); 
        }
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_Remove_REL_AActionPerformed

    private Vector getAlarmServer(String filterSERVER_ID)
    {
        Vector vServer = new Vector();

        String where_Server ="";
        if(filterSERVER_ID != null)
            where_Server = " and SERVER_ID="+filterSERVER_ID+" "; 


        String sel_AlarmServerRead = "select ID_ALARM_RELATION,SERVER_ID,DESCRIPTION from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'"+where_Server;

        //System.out.println("sel_AlarmServerRead "+sel_AlarmServerRead);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_AlarmServerRead,SQLStatement);

        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {   
                    int ID_AlarmRelation = rs_simple.getInt("ID_ALARM_RELATION");
                    int serverID = rs_simple.getInt("SERVER_ID"); 

                    String desc = rs_simple.getString("DESCRIPTION");
                    if(desc == null)
                        desc = "";

                    AlarmServerRow AL_Row = new AlarmServerRow(local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,ID_AlarmRelation,serverID,desc);
                    vServer.addElement(AL_Row);
                 }
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }

            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JD_RegisterServerAlarm_DB-1) SQL Operation " + exc.toString());
            }
        }
        else
        {
            System.out.println("rs==null");
        }

        return vServer;
    }
    
    private int countRow_ForServerID(Vector vAllServer,int serverID)
    {
        int count = 0;
        for(int i=0; i<vAllServer.size(); i++)
        {
            AlarmServerRow serverRow = (AlarmServerRow)vAllServer.elementAt(i);
            if(serverRow.SERVER_ID == serverID)
                count++;
        }
        return count;
    }
    
    private int get_ServerID(Vector vAllServer,int id_AlarmRelation)
    {
        for(int i=0; i<vAllServer.size(); i++)
        {
            AlarmServerRow serverRow = (AlarmServerRow)vAllServer.elementAt(i);
            if(serverRow.ID_ALARM_RELATION == id_AlarmRelation)
                return serverRow.SERVER_ID;
        }
        return -1;
    }
    
    private void jB_Add_Mod_REL_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_Add_Mod_REL_AActionPerformed
          
        this.setCursor(Cur_wait);
        
        if(ctrl_move() == false)
            return;
        
        Object target = evt.getSource();
        int modality = 0; // ADD NEW RELATION
        ADAMS_TAB_ALARM_RELATION row_AlarmRelation_Modify = null;
                
        int ID_ALARM_RELATION_MODIFY = -1;
        if(target == jB_Mod_REL_A)
        {
            int selectedRow = jTable_REL.getSelectedRow();
            if(selectedRow >= 0)
            {
                modality = 1;
                
                ID_ALARM_RELATION_MODIFY = ((Integer)jTable_REL.getValueAt(selectedRow,0)).intValue();
               //System.out.println("Id relazione da Modificare "+ID_ALARM_RELATION_MODIFY);

                if(V_ALL_RELATION_ALARM != null)
                {                                               
                    for(int i=0; i<V_ALL_RELATION_ALARM.size(); i++)
                    {
                        row_AlarmRelation_Modify = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);                    
                        if(row_AlarmRelation_Modify.ID_ALARM_RELATION == ID_ALARM_RELATION_MODIFY)
                            break;
                    }
                }
            }
            else
            {                
                ADAMS_GlobalParam.optionPanel("Select an < Alarm Relation > from the list.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);
                this.setCursor(Cur_default);
                return;
            }
        }
        else
        {
            jTable_REL.clearSelection();
            row_AlarmRelation_Modify = null;
        }

        if(jD_RelationBuild == null)
        {
           jD_RelationBuild = new ADAMS_JD_RelationBuild(ADAMS_GlobalParam.JFRAME_TAB,local_INFO_CONFIG,this.parent_V_TAB_CONFIG);           
           jD_RelationBuild.setTableConfig();
        } 
        
        jD_RelationBuild.setModality(modality,row_AlarmRelation_Modify);
        
        //rileggo i contatori dal DB
        read_SimpleCounterKit();
        jD_RelationBuild.initCounters(Vector_CountersKit_ID,Vector_CountersKit_TAG);
        //---------
        jD_RelationBuild.open_RelationBuild();
        
        if(jD_RelationBuild.get_ConfirmFreeReleation())
        {
                        
            Vector V_RelationsName = jD_RelationBuild.get_V_RELATION_NAME();
            Vector V_RelationsID = jD_RelationBuild.get_V_RELATION_ID();
            Vector V_Detail = jD_RelationBuild.get_V_DETAIL();
            
            int counterKitID_selected       = jD_RelationBuild.getcounterKitID();
            String str_description          = jD_RelationBuild.getDescription();
            
            
           //System.out.println("La select Script Alarm è andata ---> "+jD_RelationBuild.getAnswercommitSCRIPT_Alarm());
            
            int id_ConditionScript_selected = jD_RelationBuild.getidConditionScript();        
           //System.out.println("id_ConditionScript_selected ---> "+id_ConditionScript_selected);
            
            String str_ConditionPlugin = jD_RelationBuild.getstrConditionPlugin();
           //System.out.println("str_ConditionPlugin ---> "+str_ConditionPlugin);
            
            int timeFractionElementId = jD_RelationBuild.get_timeFractionElementId();
            //int familyId = jD_RelationBuild.get_familyId(); NON Utilizzato
            
            int AlarmServerID = jD_RelationBuild.getServerID();
            
            //CTRL se la relazione gia esiste ----------------------------
            
            boolean find = false; 
            for(int i=0; i<V_ALL_RELATION_ALARM.size(); i++)
            {
                ADAMS_TAB_ALARM_RELATION row_AlarmRelation = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);
            
                int size_RE = row_AlarmRelation.V_ALARM_REL_ELEMENTS.size();
                int size_ID_ReletionIDs = V_RelationsID.size();
                
                if(size_RE == size_ID_ReletionIDs)
                {
                    for(int x=0;x<size_RE;x++)
                    {
                        int ID_RelElement = ((Integer)row_AlarmRelation.V_ALARM_REL_ELEMENTS.elementAt(x)).intValue();
                        int ID_Element_ctrl = ((Integer)V_RelationsID.elementAt(x)).intValue();
                            
                        if(ID_RelElement != ID_Element_ctrl)
                        {                                                      
                            find = false;
                            break;
                        }
                       
                        if(modality == 1) // in modify l'elemento in modifica può mantenere gli stessi elementi.
                        {
                            if (row_AlarmRelation.ID_ALARM_RELATION  == ID_ALARM_RELATION_MODIFY)
                            {
                                find = false;
                                break;
                            }
                        }                                     
                       
                        find = true;
                   }
                }
            }
           
            if(find == true)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"This Relation is already on the list of Alarm Relations.","ERROR",1);
                this.setCursor(Cur_default);
                return;
            }  
            
            /////////////////////////////// ----------------------------------

           /* String dots = "::";
            int size_Rname = V_RelationsName.size();
            for( int i=0; i<size_Rname; i++ )
            {
                if(i == (size_Rname-1))
                    dots = "";
                System.out.print(V_RelationsName.elementAt(i)+dots);
            }
            System.out.println();
            
            dots = "::";
            int size_Rid = V_RelationsID.size();
            for( int i=0; i<size_Rid; i++ )
            {
                if(i == (size_Rid-1))
                    dots = "";
                System.out.print(V_RelationsID.elementAt(i)+dots);
            }
            System.out.println();*/
            
            /////////////////////////////// ----------------------------------

            int Answer = -1;
            String info = "added";
            if(modality == 0) // M_ADD = 0 
            {
                ADAMS_TAB_ALARM_RELATION row_insert  = new ADAMS_TAB_ALARM_RELATION();             
                row_insert.DESCRIPTION              = str_description;                   
                row_insert.ID_CONDITION_SCRIPT      = id_ConditionScript_selected;
                row_insert.STR_CONDITION_PLUGIN     = str_ConditionPlugin;
                row_insert.TIPO_DI_CONFIGURAZIONE   = new String(local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
                row_insert.COUNTERS_KIT_ID          = counterKitID_selected;
                row_insert.TIME_FRACTION_ELEMENT_ID = timeFractionElementId;
                //row_insert.FAMILY_ID                = familyId; NON UTILIZZATO
                
                for( int i=0; i<V_RelationsID.size(); i++ )
                {
                    row_insert.V_ALARM_REL_ELEMENTS.addElement(new Integer( ((Integer)V_RelationsID.elementAt(i)).intValue() ));
                    row_insert.V_ENABLE_DETAIL_ELEMENTS.addElement(new Integer( ((Integer)V_Detail.elementAt(i)).intValue() ));
                }
             
                Answer = row_insert.insert_ALARM_RELATION();
                
                // ***********

                if(Answer == 1)
                {                       
                    if(AlarmServerID >= 0)
                    {      
                        int sql_operation = -1;
                        Vector V_SERVER_ROW_DB = getAlarmServer(""+AlarmServerID);
                        int count_server = V_SERVER_ROW_DB.size();                        
                        if(count_server > 0)
                        {
                            AlarmServerRow serverRow_DB = (AlarmServerRow)V_SERVER_ROW_DB.elementAt(0);
                            if(count_server == 1)
                            {
                                //System.out.println("serverRow_DB.ID_ALARM_RELATION  --> "+serverRow_DB.ID_ALARM_RELATION );
                                if(serverRow_DB.ID_ALARM_RELATION == -1)
                                {                                    
                                    String str_update =  "update tab_alarm_server_master set ID_ALARM_RELATION="+row_insert.ID_ALARM_RELATION+                                     
                                                                " where TIPO_DI_CONFIGURAZIONE='"+row_insert.TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+AlarmServerID+" and ID_ALARM_RELATION =-1";

                                   //System.out.println("str_update " +str_update);
                                    sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
                                }
                                else
                                {   
                                    String str_Insert = ("insert into tab_alarm_server_master (TIPO_DI_CONFIGURAZIONE,SERVER_ID,DESCRIPTION,ID_ALARM_RELATION)"+
                                                        " values ('"+row_insert.TIPO_DI_CONFIGURAZIONE+"',"+AlarmServerID+",'"+serverRow_DB.DESCRIPTION+"',"+row_insert.ID_ALARM_RELATION+")");

                                   //System.out.println("str_Insert " +str_Insert);
                                    sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);
                                }
                            }
                            else
                            {                            
                                String str_Insert = ("insert into tab_alarm_server_master (TIPO_DI_CONFIGURAZIONE,SERVER_ID,DESCRIPTION,ID_ALARM_RELATION)"+
                                                        " values ('"+row_insert.TIPO_DI_CONFIGURAZIONE+"',"+AlarmServerID+",'"+serverRow_DB.DESCRIPTION+"',"+row_insert.ID_ALARM_RELATION+")");

                               //System.out.println("str_Insert " +str_Insert);
                                sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);
                            }

                            if(sql_operation > 0)
                                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(row_insert.TIPO_DI_CONFIGURAZIONE);
                            else
                                System.out.println("ERRORE ADD SERVER_ID= "+AlarmServerID+" con ID_ALARM_RELATION="+row_insert.ID_ALARM_RELATION);

                        }
                        else
                            System.out.println("ERRORE SERVER_ID ="+AlarmServerID+" non TROVATO sul DB!! [A].");
                    }
                    //else
                        //System.out.println(" ++ ADD ++ SERVER non IMPOSTATO non fare nulla --> AlarmServerID ="+AlarmServerID);
                    
                }
                
                
                // *****
                
                //seleziona riga JTable
                int ID_MAX = 0;
                int indexSelected = 0;
                for(int i=0; i<jTable_REL.getRowCount(); i++)
                {
                    int ID = new Integer(""+jTable_REL.getValueAt(i,0)).intValue();
                    if(ID > ID_MAX)
                    {
                        ID_MAX = ID;
                        indexSelected = i;
                    }
                }

                try
                {   
                    jTable_REL.setRowSelectionInterval(0,indexSelected);
                    jScrollPane1_REL_A.validate();
                }
                catch(Exception e){}
                
            }
            else //---- M_MODIFY = 1;
            {
                row_AlarmRelation_Modify.DESCRIPTION = str_description;
                row_AlarmRelation_Modify.ID_CONDITION_SCRIPT    = id_ConditionScript_selected;
                row_AlarmRelation_Modify.STR_CONDITION_PLUGIN   = str_ConditionPlugin;
                row_AlarmRelation_Modify.TIPO_DI_CONFIGURAZIONE = new String(local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
                row_AlarmRelation_Modify.COUNTERS_KIT_ID        = counterKitID_selected;
                row_AlarmRelation_Modify.TIME_FRACTION_ELEMENT_ID = timeFractionElementId;
                //row_AlarmRelation_Modify.FAMILY_ID                = familyId; NON UTILIZZATO
                
                row_AlarmRelation_Modify.V_ALARM_REL_ELEMENTS.removeAllElements();
                row_AlarmRelation_Modify.V_ENABLE_DETAIL_ELEMENTS.removeAllElements();
                
                for( int i=0; i<V_RelationsID.size(); i++ )
                {
                    row_AlarmRelation_Modify.V_ALARM_REL_ELEMENTS.addElement(new Integer( ((Integer)V_RelationsID.elementAt(i)).intValue() ));
                    row_AlarmRelation_Modify.V_ENABLE_DETAIL_ELEMENTS.addElement(new Integer( ((Integer)V_Detail.elementAt(i)).intValue() ));
                }
                
                Answer = row_AlarmRelation_Modify.update_ALARM_RELATION();                
                
                if(Answer == 1)
                {
                    //System.out.println("MODIFY -----> ID_ALARM_RELATION="+row_AlarmRelation_Modify.ID_ALARM_RELATION+" - AlarmServerID="+AlarmServerID);                    
                    Vector V_SERVER_ROW_DB = getAlarmServer(null);
                    
                    int count_server = V_SERVER_ROW_DB.size();                    
                    if(count_server > 0)
                    {  
                        boolean resetDone = false;
                        for(int r=0; r<count_server; r++)
                        {
                            AlarmServerRow serverRow_DB = (AlarmServerRow)V_SERVER_ROW_DB.elementAt(r);
                            if(serverRow_DB.ID_ALARM_RELATION == row_AlarmRelation_Modify.ID_ALARM_RELATION)                               
                            {                                                                
                                if(serverRow_DB.SERVER_ID != AlarmServerID)
                                {
                                    // ************* RESET OLD SERVER
                                    int sql_operation = 0;
                                    int countRow_ServerRESET = countRow_ForServerID(V_SERVER_ROW_DB,serverRow_DB.SERVER_ID);
                                    if(countRow_ServerRESET == 1)
                                    {                
                                        String str_reset =   "update tab_alarm_server_master set ID_ALARM_RELATION=-1"+                                     
                                                                " where TIPO_DI_CONFIGURAZIONE='"+row_AlarmRelation_Modify.TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+serverRow_DB.SERVER_ID+" and ID_ALARM_RELATION ="+row_AlarmRelation_Modify.ID_ALARM_RELATION+"";

                                       //System.out.println("str_reset " +str_reset);
                                        sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_reset);
                                    }
                                    else
                                    {
                                        String str_delete = "delete from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+row_AlarmRelation_Modify.TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+serverRow_DB.SERVER_ID+" and ID_ALARM_RELATION ="+row_AlarmRelation_Modify.ID_ALARM_RELATION+"";
                                       //System.out.println("str_reset " +str_delete);
                                        sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
                                    }
                                    resetDone = true;
                                    break;
                                    // END ************* RESET OLD SERVER
                                }
                                else
                                {
                                    //Stesso ID_ALARM_RELATION e stesso SERVER nel DB -> NON FARE NULLA.... niente e' cambiato!!
                                    resetDone = false;
                                    break;
                                } 
                            }
                            else
                            {
                                //ID_ALARM_RELATION potrebbe non essere presente ---> NO-RESET ma SOLO ADD
                                resetDone = true;
                            }
                            
                        }
                        
                        if(resetDone)
                        {
                            for(int a=0; a<count_server; a++)
                            {
                                AlarmServerRow serverRow_DB = (AlarmServerRow)V_SERVER_ROW_DB.elementAt(a);
                                if(serverRow_DB.SERVER_ID == AlarmServerID)
                                {
                                    int sql_operation = 0;
                                                                        
                                    // ************* ASSOCIA NUOVO SERVER
                                    boolean flag_insert = true;
                                    
                                    int countRow_ServerADD = countRow_ForServerID(V_SERVER_ROW_DB,AlarmServerID);
                                    if(countRow_ServerADD == 1)
                                    {
                                        if(serverRow_DB.ID_ALARM_RELATION == -1)
                                        {
                                            String str_update_1 =  "update tab_alarm_server_master set ID_ALARM_RELATION="+row_AlarmRelation_Modify.ID_ALARM_RELATION+                                     
                                                                        " where TIPO_DI_CONFIGURAZIONE='"+row_AlarmRelation_Modify.TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+AlarmServerID+" and ID_ALARM_RELATION =-1";

                                           //System.out.println("str_update_1 " +str_update_1);
                                            sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_update_1);                                            
                                            flag_insert = false;
                                        }                                        
                                    }
                                    
                                    if(flag_insert)
                                    {
                                        String str_insert_1 = ("insert into tab_alarm_server_master (TIPO_DI_CONFIGURAZIONE,SERVER_ID,DESCRIPTION,ID_ALARM_RELATION)"+
                                                            " values ('"+row_AlarmRelation_Modify.TIPO_DI_CONFIGURAZIONE+"',"+AlarmServerID+",'"+serverRow_DB.DESCRIPTION+"',"+row_AlarmRelation_Modify.ID_ALARM_RELATION+")");                      
                                        
                                       //System.out.println("str_insert_1 " +str_insert_1);
                                        sql_operation = ADAMS_GlobalParam.connect_Oracle.Operation(str_insert_1);
                                        break;
                                    }
                                }
                                
                            }
                        }
                        //else
                             //System.out.println(" ++ Modify ADD ++ Stesso Sever NON FARE NIENTE");
                    }
                    //else
                        //System.out.println("ERRORE non Ci sono SERVER DICHIARATI!!");
                    
                }
                
                info = "changed";          
                
                try
                {   //seleziona riga JTable
                    for(int i=0; i<jTable_REL.getRowCount(); i++)
                    {
                        int ID_TABLE = new Integer(""+jTable_REL.getValueAt(i,0)).intValue();
                        if( ID_TABLE == row_AlarmRelation_Modify.ID_ALARM_RELATION)
                        {
                            jTable_REL.setRowSelectionInterval(i,i);
                            jScrollPane1_REL_A.validate();
                            break;
                        }
                    }
                }catch(Exception e){}
                
            }   
            
            
            if(Answer == 1)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Relation "+str_description+" has been "+info+".","INFO",3,400,200);
                
                if(modality == 0) //ADD
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"-- INFO -- \nAlarm Relation "+str_description+" has been "+info+", remember to update alarm Policy.","INFO",3,400,200);
            }
            else
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert/change Alarm Relation: "+str_description+".","ERROR",1);
            
            read_RelationsAlarm();
            V_ALL_ALARM_GENERATOR = read_AllAlarms();
            refresh_ListEnableAlarm(V_ALL_ALARM_GENERATOR,new Vector());
            setGui();            
        }
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_Add_Mod_REL_AActionPerformed

    private void refreshAlarm_SelREL()
    {
        int selectedRow = jTable_REL.getSelectedRow();
        if(selectedRow >= 0)
        {            
            int id_REL_Selected = new Integer(""+jTable_REL.getValueAt(selectedRow,0)).intValue();
            //System.out.println("id_Selected ---> "+id_REL_Selected);
            
            if(V_ALL_RELATION_ALARM != null)
            {                                               
                for(int i=0; i<V_ALL_RELATION_ALARM.size(); i++)
                {
                    AlarmRelation_Selected = (ADAMS_TAB_ALARM_RELATION)V_ALL_RELATION_ALARM.elementAt(i);                    
                    if(AlarmRelation_Selected.ID_ALARM_RELATION == id_REL_Selected)
                        break;
                }
            }
            refresh_ListEnableAlarm(V_ALL_ALARM_GENERATOR,AlarmRelation_Selected.V_ALARM_HANDLERS);
        }
        else
            AlarmRelation_Selected = null;
        
        setGui();
    }
    
    private void jTable_RELMouseReleased(java.awt.event.MouseEvent evt) 
    {
        this.setCursor(Cur_wait);
        
        if(ctrl_move() == false)
            return;
        
        refreshAlarm_SelREL();
        
        this.setCursor(Cur_default);
    }

//////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames;
        MyTableModel(String[] all_columnNames)
        {
            columnNames = all_columnNames;
        }

        private Object[][] data = {};   
        
        public void setColumnName(String[] all_columnNames)
        {
            columnNames = all_columnNames;
        }
        
        public int getColumnCount() {
            return columnNames.length;
        }
        public int getRowCount() 
        {
            return data.length;
        }    
        public String getColumnName(int col) 
        {
            return columnNames[col];
        }
        public java.lang.Object getValueAt(int row, int col) 
        {
            return data[row][col];
        }
        public void setDataValues(Object[][] datavalues)
        {
            data = datavalues;
        }
        
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) 
        {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) 
        {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            /*if (col < 2) {
                return false;
            } else {
                return true;
            }*/
            return false; //nessuna cella editabile
        }
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) 
        {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
        private void printDebugData() 
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
        
    
    class AlarmServerRow
    {               
        String TIPO_DI_CONFIGURAZIONE;
        int ID_ALARM_RELATION;
        int SERVER_ID;
        String DESCRIPTION;
                
        AlarmServerRow(String ConfName, int ID_AlarmRelation, int Server_ID, String Desc)
        {
            TIPO_DI_CONFIGURAZIONE = new String(ConfName);
            ID_ALARM_RELATION   = ID_AlarmRelation;
            SERVER_ID           = Server_ID;
            DESCRIPTION         = new String(Desc);            
        }
     }
//////////////////////////////////////////// END Internal CLASS ////////////////////////////////////////////  
    
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JPanel jP_north;
     private javax.swing.JLabel jL_title;
     private javax.swing.JPanel jP_Center;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JScrollPane jScrollPane1_REL_A;
     private javax.swing.JPanel jPanel3;
     private javax.swing.JButton jB_Add_REL_A;
     private javax.swing.JButton jB_Mod_REL_A;
     private javax.swing.JButton jB_Remove_REL_A;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JPanel jPanel6;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JScrollPane jScroll1;
     private javax.swing.JPanel jPanel7;
     private javax.swing.JButton jB_Add;
     private javax.swing.JButton jB_AddAll;
     private javax.swing.JButton jB_Rem;
     private javax.swing.JButton jB_RemAll;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JScrollPane jScroll2;
     private javax.swing.JPanel jPanel4;
     private javax.swing.JButton jB_AlarmManagement;
     private javax.swing.JButton jB_commitAlarm;
     // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    
    private javax.swing.JTable jTable_REL;
    private TableSorter sorter_REL;
    private MyTableModel myTableModel_REL;
        
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;    
    
    private JListIcon jList_Disable_Alarm   = null;
    private JListIcon jList_Enable_Alarm    = null;
    private IconPool IcPool                 = null;
    private java.util.Vector V_Disable_Alarms   = null;
    private java.util.Vector V_Enable_Alarms    = null;
    //private java.awt.event.MouseAdapter eventList   = null;
    
    private ADAMS_JD_RelationBuild jD_RelationBuild  = null;
    
    private final String tab_alarm_relation = "tab_alarm_relation";
    private final String tab_alarm_relation_elements = "tab_alarm_relation_elements";
    private final String tab_alarm_relation_handlers = "tab_alarm_relation_handlers";
    
    private Vector V_ALL_RELATION_ALARM = null;
    private Vector V_ALL_ALARM_GENERATOR = null;
    
    private Vector Vector_CountersKit_ID    = new Vector(10);
    private Vector Vector_CountersKit_TAG   = new Vector(10);
    
    private ADAMS_TAB_ALARM_RELATION AlarmRelation_Selected = null;
    
    private boolean MOVE = false;
}