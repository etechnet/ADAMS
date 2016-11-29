/**
 *
 * @author  luca
 */
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.util.Vector;
import java.sql.*;
public class ADAMS_JD_ALARM extends javax.swing.JDialog 
{
    public ADAMS_JD_ALARM(boolean modal, String title,String TipoDiConfigurazione) 
    {
        super(ADAMS_GlobalParam.JFRAME_TAB, modal);
        initComponents();
        
        this.TIPO_DI_CONFIGURAZIONE = TipoDiConfigurazione;


        
         // *** Costruzione JTABLE TE ***/
        myTableModel_Alarm = new MyTableModel(all_columnNames);
        sorter_Alarm = new TableSorter(myTableModel_Alarm);
        
        jTable_Alarm = new javax.swing.JTable(sorter_Alarm);
        JTableHeader header2 = jTable_Alarm.getTableHeader();
        header2.setFont(ADAMS_GlobalParam.font_B10);
        header2.setReorderingAllowed(false);

        sorter_Alarm.setTableHeader(header2);        
        
        jTable_Alarm.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Alarm.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Alarm.setFont(new java.awt.Font("Verdana", 0, 9));
        jTable_Alarm.setRowHeight(20);
        jTable_Alarm.setRowMargin(2);
        jTable_Alarm.setAutoCreateColumnsFromModel(false);
        jTable_Alarm.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jTable_Alarm.setSelectionBackground(java.awt.Color.yellow);
        //jTable_Alarm.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
         jTable_Alarm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_AlarmMouseReleased(evt);
            }
        });
        
        jScrollPane_Alarm.setViewportView(jTable_Alarm); 
        
        SetTable(jTable_Alarm,30,cellDimen_Alarm);
        
        jT_Tag.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,ADAMS_GlobalParam.SHORT_DESC_LEN));        
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,ADAMS_GlobalParam.LONG_DESC_LEN));
        
        this.setTitle(title);
        jL_TITLE.setText(title); 
        
   
        // -------- LISTS Thresholds  ---------- 
        IcPool = new IconPool("/images/");
        jList_Disable_th = new JListIcon(IcPool);
        jList_Enable_th  = new JListIcon(IcPool); 
        
        /*jList_Disable_th.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListMousePressed(evt);
            }
        });

        jList_Enable_th.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListMousePressed(evt); 
            }
        });*/
        
        jList_Enable_th.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_Disable_th.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //SINGLE_INTERVAL_SELECTION
        
        V_Disable_th = new java.util.Vector();
        V_Enable_th = new java.util.Vector();
  
        jScroll1.setViewportView(jList_Disable_th);
        jScroll2.setViewportView(jList_Enable_th);

        // --------  LISTS Thresholds ----------
        
        
        //Font Globali
        jL_TITLE.setFont(ADAMS_GlobalParam.font_B12);
        jL_1.setFont(ADAMS_GlobalParam.font_B10);
        jL_2.setFont(ADAMS_GlobalParam.font_B10);
        jL_3.setFont(ADAMS_GlobalParam.font_B10);
        jL_4.setFont(ADAMS_GlobalParam.font_B10);
        jL_ID.setFont(ADAMS_GlobalParam.font_B10);
        jT_Tag.setFont(ADAMS_GlobalParam.font_B10);
        jText_pluginName.setFont(ADAMS_GlobalParam.font_B10);
        jB_pluginName.setFont(ADAMS_GlobalParam.font_B10);
        jT_desc.setFont(ADAMS_GlobalParam.font_B10);
        jChek_Threshold.setFont(ADAMS_GlobalParam.font_B10);
        jLabel1.setFont(ADAMS_GlobalParam.font_B10);
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);
        jList_Enable_th.setFont(new java.awt.Font("Verdana", 0, 9));
        jList_Disable_th.setFont(new java.awt.Font("Verdana", 0, 9));
        
        //Set Cursor
        jTable_Alarm.setCursor(Cur_hand);
        jB_pluginName.setCursor(Cur_hand);
        jChek_Threshold.setCursor(Cur_hand);
        jB_ThresholdManag.setCursor(Cur_hand);
        jList_Enable_th.setCursor(Cur_hand);
        jList_Disable_th.setCursor(Cur_hand);
        jB_Add.setCursor(Cur_hand);
        jB_AddAll.setCursor(Cur_hand);
        jB_Rem.setCursor(Cur_hand);
        jB_RemAll.setCursor(Cur_hand);
        jR_ADD.setCursor(Cur_hand);
        jR_MOD.setCursor(Cur_hand);
        jR_DEL.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);        
           
        //Cursor
        //.setCursor(Cur_hand);        
        
        
        //Aggiorna TUTTO
        V_ALL_ALARM_GENERATOR = read_AllAlarms();
        AddRowjTable_Alarm(V_ALL_ALARM_GENERATOR);
        
        V_ALL_THRESHOLD = read_AllThreshold();
        refresh_ListEnableAlarm_TH(V_ALL_THRESHOLD,new Vector());
        
        setGUI_Op_Alarm();
        ////Aggiorna TUTTO
        
        setCenteredFrame(680,720);
        this.setVisible(true);
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
    
    private void AddRowjTable_Alarm(Vector V_AllAlarms)
    {        
        Object[][] dataValues = new Object[0][all_columnNames.length];
        
        if(V_AllAlarms != null)
        {
            int size_V_ALL_RA = V_AllAlarms.size();            
            dataValues = new Object[size_V_ALL_RA][all_columnNames.length];
            
            for(int i=0; i<size_V_ALL_RA; i++)
            {
                ADAMS_TAB_ALARM_GENERATOR row_Alarm = (ADAMS_TAB_ALARM_GENERATOR)V_AllAlarms.elementAt(i);
                
                dataValues[i][0] = row_Alarm.ID_ALARM_GENERATOR;
                dataValues[i][1] = row_Alarm.SHORT_DESCRIPTION;
                dataValues[i][2] = row_Alarm.STR_PLUGIN;
                
                boolean threshold = false;
                if(row_Alarm.THRESHOLD_MANAGEMENT == 'Y')
                    threshold = true;                  
                dataValues[i][3] = threshold;
            }               
            
        }
            
        myTableModel_Alarm.setDataValues(dataValues);
        sorter_Alarm.setTableModel(myTableModel_Alarm);
        jScrollPane_Alarm.setViewportView(jTable_Alarm);        
    }
    
    
    private Vector read_AllAlarms()
    {            
        Vector V_ALARMS = new Vector();
                 
        String itemName =("ID_ALARM_GENERATOR,SHORT_DESCRIPTION,LONG_DESCRIPTION,STR_PLUGIN,THRESHOLD_MANAGEMENT");                
        String sel_read_Alarms = ("select "+itemName+" from tab_alarm_generator where TIPO_DI_CONFIGURAZIONE = '"+this.TIPO_DI_CONFIGURAZIONE+"' order by SHORT_DESCRIPTION");
       
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
                    ADAMS_TAB_ALARM_GENERATOR row_Alarms = new ADAMS_TAB_ALARM_GENERATOR(this.TIPO_DI_CONFIGURAZIONE);
                    
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
                                                        " where TIPO_DI_CONFIGURAZIONE = '"+this.TIPO_DI_CONFIGURAZIONE+"'"+
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
                    
                    try
                    {
                       SQLStatement_Th.close();
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
        return V_ALARMS;
    }
    
    private Vector read_AllThreshold()
    {            
        Vector V_TH = new Vector();

        //String itemName =("ID_THRESHOLD_GENERATOR, DESCRIPTION, TYPE_THRESHOLD, ENABLE_HOLYDAY_THRESHOLD, STR_PLUGIN");
        String itemName =("ID_THRESHOLD_GENERATOR, DESCRIPTION");     
        String sel_read_th = ("select "+itemName+" from tab_alarm_threshold_generator where TIPO_DI_CONFIGURAZIONE = '"+this.TIPO_DI_CONFIGURAZIONE+"' order by DESCRIPTION");
       
       //System.out.println();
       //System.out.println("sel_read_th --> "+sel_read_th);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_read_th,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {                      
                    ADAMS_TAB_ALARM_THRESHOLD row_th = new ADAMS_TAB_ALARM_THRESHOLD(this.TIPO_DI_CONFIGURAZIONE);
                                        
                    row_th.ID_THRESHOLD_GENERATOR = rs.getInt("ID_THRESHOLD_GENERATOR"); 
                                                            
                    String s_desc = rs.getString("DESCRIPTION");
                        if(s_desc != null)
                            row_th.DESCRIPTION = s_desc;

                    /*row_th.TYPE_THRESHOLD = rs.getInt("TYPE_THRESHOLD"); 
                    
                    char H = rs.getString("ENABLE_HOLYDAY_THRESHOLD").charAt(0);
                    if(H != ' ')
                        row_th.ENABLE_HOLYDAY_THRESHOLD = H; 
                                   
                    String pl = rs.getString("STR_PLUGIN");
                        if(pl != null)
                            row_th.STR_PLUGIN = pl;*/

                    V_TH.addElement(row_th);
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
        return V_TH;
    }
    
    private void refresh_ListEnableAlarm_TH(Vector V_all_th, Vector V_alarm_TH)
    {    
        int size_alarm_th = V_alarm_TH.size();
        
        V_Disable_th.removeAllElements();
        V_Enable_th.removeAllElements();
        jList_Enable_th.removeAll();
        jList_Disable_th.removeAll();

        if(size_alarm_th == 0)
        {
            for(int x=0; x<V_all_th.size(); x++)
            {
                
                ADAMS_TAB_ALARM_THRESHOLD appo_Th = ((ADAMS_TAB_ALARM_THRESHOLD)V_all_th.elementAt(x));
                jList_Disable_th.addElement("b_vcanc.png","b_vcanc.png",appo_Th.DESCRIPTION);
                V_Disable_th.addElement(appo_Th.DESCRIPTION);
            }
        }
        else
        {
            for(int x=0; x<V_all_th.size(); x++)
            {
                ADAMS_TAB_ALARM_THRESHOLD appo_Th = ((ADAMS_TAB_ALARM_THRESHOLD)V_all_th.elementAt(x));
                boolean find = false;
                for(int i=0; i<size_alarm_th; i++ )
                {
                    int id_alarm_th = ((Integer)(V_alarm_TH.elementAt(i))).intValue();
                    if(appo_Th.ID_THRESHOLD_GENERATOR == id_alarm_th)  
                        find = true;              
                } 
                
                if(find)
                {
                    jList_Enable_th.addElement("b_vok.png","b_vok.png",appo_Th.DESCRIPTION);
                    V_Enable_th.addElement(appo_Th.DESCRIPTION);
                }                    
                else
                {
                    jList_Disable_th.addElement("b_vcanc.png","b_vcanc.png",appo_Th.DESCRIPTION);
                    V_Disable_th.addElement(appo_Th.DESCRIPTION);
                }
            }
        }
        jList_Enable_th.repaint();
        jList_Disable_th.repaint();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jL_TITLE = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane_Alarm = new javax.swing.JScrollPane();
        jP_option = new javax.swing.JPanel();
        jL_1 = new javax.swing.JLabel();
        jL_ID = new javax.swing.JLabel();
        jL_2 = new javax.swing.JLabel();
        jT_Tag = new javax.swing.JTextField();
        jL_3 = new javax.swing.JLabel();
        jT_desc = new javax.swing.JTextField();
        jL_4 = new javax.swing.JLabel();
        jP_pl = new javax.swing.JPanel();
        jText_pluginName = new javax.swing.JTextField();
        jB_pluginName = new javax.swing.JButton();
        jP_Threshold = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScroll1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jB_Add = new javax.swing.JButton();
        jB_AddAll = new javax.swing.JButton();
        jB_Rem = new javax.swing.JButton();
        jB_RemAll = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScroll2 = new javax.swing.JScrollPane();
        jChek_Threshold = new javax.swing.JCheckBox();
        jB_ThresholdManag = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jR_ADD = new javax.swing.JRadioButton();
        jR_MOD = new javax.swing.JRadioButton();
        jR_DEL = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_TITLE.setBackground(new java.awt.Color(230, 230, 230));
        jL_TITLE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_TITLE.setText("Alarm Management");
        jL_TITLE.setMaximumSize(new java.awt.Dimension(11, 16));
        jL_TITLE.setMinimumSize(new java.awt.Dimension(45, 25));
        jL_TITLE.setPreferredSize(new java.awt.Dimension(45, 25));
        jL_TITLE.setOpaque(true);
        getContentPane().add(jL_TITLE, java.awt.BorderLayout.NORTH);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(null, " Alarm Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jPanel1.setPreferredSize(new java.awt.Dimension(20, 130));
        jScrollPane_Alarm.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_Alarm.setPreferredSize(new java.awt.Dimension(3, 350));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jPanel1.add(jScrollPane_Alarm, gridBagConstraints1);
        
        jP_option.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_option.setBackground(new java.awt.Color(230, 230, 230));
        jP_option.setMinimumSize(new java.awt.Dimension(400, 350));
        jP_option.setPreferredSize(new java.awt.Dimension(370, 350));
        jL_1.setBackground(new java.awt.Color(230, 230, 230));
        jL_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_1.setText("ID");
        jL_1.setToolTipText("ID");
        jL_1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_1.setMaximumSize(new java.awt.Dimension(11, 20));
        jL_1.setMinimumSize(new java.awt.Dimension(11, 20));
        jL_1.setPreferredSize(new java.awt.Dimension(11, 20));
        jL_1.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_option.add(jL_1, gridBagConstraints2);
        
        jL_ID.setBackground(java.awt.Color.white);
        jL_ID.setForeground(java.awt.Color.darkGray);
        jL_ID.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_ID.setMaximumSize(new java.awt.Dimension(70, 20));
        jL_ID.setMinimumSize(new java.awt.Dimension(50, 20));
        jL_ID.setPreferredSize(new java.awt.Dimension(50, 20));
        jL_ID.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_option.add(jL_ID, gridBagConstraints2);
        
        jL_2.setBackground(new java.awt.Color(230, 230, 230));
        jL_2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_2.setText("Tag Alarm");
        jL_2.setToolTipText("");
        jL_2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_2.setMaximumSize(new java.awt.Dimension(150, 20));
        jL_2.setMinimumSize(new java.awt.Dimension(150, 20));
        jL_2.setPreferredSize(new java.awt.Dimension(150, 20));
        jL_2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_2.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        jP_option.add(jL_2, gridBagConstraints2);
        
        jT_Tag.setDisabledTextColor(java.awt.Color.gray);
        jT_Tag.setMinimumSize(new java.awt.Dimension(150, 20));
        jT_Tag.setPreferredSize(new java.awt.Dimension(150, 20));
        jT_Tag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_TagKeyReleased(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_option.add(jT_Tag, gridBagConstraints2);
        
        jL_3.setBackground(new java.awt.Color(230, 230, 230));
        jL_3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_3.setText("Alarm Description");
        jL_3.setToolTipText("");
        jL_3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_3.setMaximumSize(new java.awt.Dimension(150, 20));
        jL_3.setMinimumSize(new java.awt.Dimension(150, 20));
        jL_3.setPreferredSize(new java.awt.Dimension(150, 20));
        jL_3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_3.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.gridwidth = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        jP_option.add(jL_3, gridBagConstraints2);
        
        jT_desc.setDisabledTextColor(java.awt.Color.gray);
        jT_desc.setMinimumSize(new java.awt.Dimension(150, 20));
        jT_desc.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.gridwidth = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        jP_option.add(jT_desc, gridBagConstraints2);
        
        jL_4.setBackground(new java.awt.Color(230, 230, 230));
        jL_4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_4.setText("Alarm Plugin");
        jL_4.setToolTipText("");
        jL_4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_4.setMaximumSize(new java.awt.Dimension(41, 20));
        jL_4.setMinimumSize(new java.awt.Dimension(41, 20));
        jL_4.setPreferredSize(new java.awt.Dimension(41, 20));
        jL_4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_4.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        jP_option.add(jL_4, gridBagConstraints2);
        
        jP_pl.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_pl.setBackground(new java.awt.Color(230, 230, 230));
        jP_pl.setMinimumSize(new java.awt.Dimension(160, 21));
        jP_pl.setPreferredSize(new java.awt.Dimension(160, 21));
        jText_pluginName.setBackground(new java.awt.Color(230, 230, 230));
        jText_pluginName.setEditable(false);
        jText_pluginName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_pluginName.setToolTipText("");
        jText_pluginName.setDisabledTextColor(java.awt.Color.darkGray);
        jText_pluginName.setFocusable(false);
        jText_pluginName.setMinimumSize(new java.awt.Dimension(65, 20));
        jText_pluginName.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_pl.add(jText_pluginName, gridBagConstraints3);
        
        jB_pluginName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_pluginName.setToolTipText("Edit Plugin (Active in modification)");
        jB_pluginName.setBorderPainted(false);
        jB_pluginName.setContentAreaFilled(false);
        jB_pluginName.setFocusPainted(false);
        jB_pluginName.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_pluginName.setMaximumSize(new java.awt.Dimension(35, 20));
        jB_pluginName.setMinimumSize(new java.awt.Dimension(35, 20));
        jB_pluginName.setPreferredSize(new java.awt.Dimension(35, 20));
        jB_pluginName.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_pluginName.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_pluginName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_pluginNameActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        jP_pl.add(jB_pluginName, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_option.add(jP_pl, gridBagConstraints2);
        
        jP_Threshold.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_Threshold.setBackground(new java.awt.Color(230, 230, 230));
        jP_Threshold.setBorder(new javax.swing.border.TitledBorder(null, " Alarm Threshold ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_Threshold.setMinimumSize(new java.awt.Dimension(320, 220));
        jP_Threshold.setPreferredSize(new java.awt.Dimension(320, 220));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setText("Disabled Alarm Thresholds");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints5);
        
        jScroll1.setBackground(new java.awt.Color(230, 230, 230));
        jScroll1.setMaximumSize(new java.awt.Dimension(200, 200));
        jScroll1.setMinimumSize(new java.awt.Dimension(200, 200));
        jScroll1.setPreferredSize(new java.awt.Dimension(200, 200));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        jPanel2.add(jScroll1, gridBagConstraints5);
        
        jPanel5.setLayout(new java.awt.GridLayout(4, 1));
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
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
                jB_ListsActionPerformed(evt);
            }
        });
        
        jPanel5.add(jB_Add);
        
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
                jB_ListsActionPerformed(evt);
            }
        });
        
        jPanel5.add(jB_AddAll);
        
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
                jB_ListsActionPerformed(evt);
            }
        });
        
        jPanel5.add(jB_Rem);
        
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
                jB_ListsActionPerformed(evt);
            }
        });
        
        jPanel5.add(jB_RemAll);
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints5.weighty = 1.0;
        jPanel2.add(jPanel5, gridBagConstraints5);
        
        jLabel2.setText("Enabled Alarm Thresholds");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 2;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints5);
        
        jScroll2.setBackground(new java.awt.Color(230, 230, 230));
        jScroll2.setMaximumSize(new java.awt.Dimension(200, 200));
        jScroll2.setMinimumSize(new java.awt.Dimension(200, 200));
        jScroll2.setPreferredSize(new java.awt.Dimension(200, 200));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 2;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        jPanel2.add(jScroll2, gridBagConstraints5);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.gridwidth = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.weighty = 1.0;
        jP_Threshold.add(jPanel2, gridBagConstraints4);
        
        jChek_Threshold.setBackground(new java.awt.Color(230, 230, 230));
        jChek_Threshold.setText("Enable Alarm Thresholds");
        jChek_Threshold.setToolTipText("(True/False)");
        jChek_Threshold.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jChek_Threshold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jChek_Threshold.setMaximumSize(new java.awt.Dimension(220, 22));
        jChek_Threshold.setMinimumSize(new java.awt.Dimension(220, 22));
        jChek_Threshold.setPreferredSize(new java.awt.Dimension(220, 22));
        jChek_Threshold.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChek_Threshold.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jChek_Threshold.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChek_Threshold.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jChek_Threshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChek_ThresholdActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jP_Threshold.add(jChek_Threshold, gridBagConstraints4);
        
        jB_ThresholdManag.setText("Threshold Management");
        jB_ThresholdManag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ThresholdManagActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
        jP_Threshold.add(jB_ThresholdManag, gridBagConstraints4);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.gridwidth = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_option.add(jP_Threshold, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        jPanel1.add(jP_option, gridBagConstraints1);
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setMinimumSize(new java.awt.Dimension(200, 40));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 40));
        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(100, 30));
        jB_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_commit.setPreferredSize(new java.awt.Dimension(80, 30));
        jB_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jB_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jB_commit.setEnabled(false);
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jB_commit, gridBagConstraints6);
        
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setToolTipText("Close");
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_close.setMaximumSize(new java.awt.Dimension(56, 30));
        jB_close.setMinimumSize(new java.awt.Dimension(56, 30));
        jB_close.setPreferredSize(new java.awt.Dimension(56, 30));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 2;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jB_close, gridBagConstraints6);
        
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 30));
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 30));
        jR_ADD.setBackground(new java.awt.Color(230, 230, 230));
        jR_ADD.setSelected(true);
        buttonGroup1.add(jR_ADD);
        jR_ADD.setContentAreaFilled(false);
        jR_ADD.setFocusPainted(false);
        jR_ADD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_ADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID.jpg")));
        jR_ADD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jR_ADD.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_over.jpg")));
        jR_ADD.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jR_ADD.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jR_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ADDActionPerformed(evt);
            }
        });
        
        jPanel4.add(jR_ADD);
        
        jR_MOD.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jR_MOD);
        jR_MOD.setContentAreaFilled(false);
        jR_MOD.setFocusPainted(false);
        jR_MOD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_MOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID.jpg")));
        jR_MOD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jR_MOD.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_over.jpg")));
        jR_MOD.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jR_MOD.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jR_MOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_MODActionPerformed(evt);
            }
        });
        
        jPanel4.add(jR_MOD);
        
        jR_DEL.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jR_DEL);
        jR_DEL.setContentAreaFilled(false);
        jR_DEL.setFocusPainted(false);
        jR_DEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_DEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID.jpg")));
        jR_DEL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jR_DEL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_over.jpg")));
        jR_DEL.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jR_DEL.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jR_DEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_DELActionPerformed(evt);
            }
        });
        
        jPanel4.add(jR_DEL);
        
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg")));
        jB_cancel.setToolTipText("Cancel");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        
        jPanel4.add(jB_cancel);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        jPanel3.add(jPanel4, gridBagConstraints6);
        
        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jB_ThresholdManagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ThresholdManagActionPerformed
        ADAMS_JD_Threshold jd_threshold = new ADAMS_JD_Threshold(true," Threshold Management ",this.TIPO_DI_CONFIGURAZIONE);
        
        //Aggiorna TUTTO
        V_ALL_ALARM_GENERATOR = read_AllAlarms();
        AddRowjTable_Alarm(V_ALL_ALARM_GENERATOR);
        
        V_ALL_THRESHOLD = read_AllThreshold();
        refresh_ListEnableAlarm_TH(V_ALL_THRESHOLD,new Vector());
        
        setGUI_Op_Alarm();
        ////Aggiorna TUTTO
        
    }//GEN-LAST:event_jB_ThresholdManagActionPerformed

    private void jT_TagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_TagKeyReleased
        enabledButtonCommit();
    }//GEN-LAST:event_jT_TagKeyReleased

    private void jB_pluginNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pluginNameActionPerformed
        ADAMS_JD_setPlugin JD_PluginSelect = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,TIPO_DI_CONFIGURAZIONE,local_AlarmGenerator.STR_PLUGIN);
        
        JD_PluginSelect.setGuiEnable(true);          
        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        local_AlarmGenerator.STR_PLUGIN = JD_PluginSelect.getPluginEnabled();
        JD_PluginSelect.dispose();
        
        if( local_AlarmGenerator.STR_PLUGIN.length()>0 )        
            setGuiPlugin_Alarm(true);
        else
            setGuiPlugin_Alarm(false);       

       //System.out.println("local_AlarmGenerator.STR_PLUGIN ="+local_AlarmGenerator.STR_PLUGIN);
    }//GEN-LAST:event_jB_pluginNameActionPerformed

    private void jChek_ThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChek_ThresholdActionPerformed
        setGUI_Op_Threshold();
    }//GEN-LAST:event_jChek_ThresholdActionPerformed

    private void jB_ListsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ListsActionPerformed
        Object target = evt.getSource();         
        if(target == jB_Add)
        {
            move(jList_Disable_th,jList_Enable_th,"b_vok.png",V_Disable_th,V_Enable_th,false);
        }
        else if(target == jB_AddAll)
        {
            move(jList_Disable_th,jList_Enable_th,"b_vok.png",V_Disable_th,V_Enable_th,true);   
        }
        else if(target == jB_Rem)
        {
            move(jList_Enable_th,jList_Disable_th,"b_vcanc.png",V_Enable_th,V_Disable_th,false);
        }
        else if(target == jB_RemAll)
        {
	    move(jList_Enable_th,jList_Disable_th,"b_vcanc.png",V_Enable_th,V_Disable_th,true);
        }
        
        //debug_Vector_th();
    }//GEN-LAST:event_jB_ListsActionPerformed

    private void move(JListIcon l1,JListIcon l2,String icon,java.util.Vector V1,java.util.Vector V2,boolean all) 
    {
       //System.out.println("move --- > flag all => "+all);
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
    
    private void debug_Vector_th()
    {
         // DEBUG --- V_Enable_th ---- //
        int SIZE_ENABLED = V_Enable_th.size();
        System.out.println("Vettore Alarm ON size() "+SIZE_ENABLED);
        for (int i=0; i<SIZE_ENABLED; i++)
            System.out.println("Alarm ENABLED --> "+((String)V_Enable_th.elementAt(i)));
        
         // DEBUG --- V_Disable_th ---- //
        int SIZE_DISABLED = V_Disable_th.size();
        System.out.println("Vettore Alarm OFF size() "+SIZE_DISABLED);
        for (int i=0; i<SIZE_DISABLED; i++)
            System.out.println("Alarm DISABLE --> "+((String)V_Disable_th.elementAt(i)));
    }
    
    /*private void jListMousePressed(java.awt.event.MouseEvent evt) 
    {
         System.out.println("jListMousePressed");
    }*/
    
    public void enabledButtonCommit()
    {
        if( (jT_Tag.getText().trim()).length() == 0 )
        {
            jB_commit.setEnabled(false);
        }
        else
            jB_commit.setEnabled(true);
    }
    
        
    private void setGuiPlugin_Alarm(boolean flag)
    {
        if(flag)        
        {
            jText_pluginName.setText(strDefined);
            jText_pluginName.setForeground(java.awt.Color.green.darker());
        }
        else
        {
            jText_pluginName.setText(StrNotDefined);
            jText_pluginName.setForeground(java.awt.Color.red);            
        }        
    }
    
    
    private void setGUI_Op_Alarm()
    {
        
        /*System.out.println("setGUI_Op_Alarm()");
        if(local_AlarmGenerator != null)
            System.out.println("Selected ----- >local_AlarmGenerator.ID_ALARM_GENERATOR "+local_AlarmGenerator.ID_ALARM_GENERATOR );
        else
            System.out.println("local_AlarmGenerator NULL");*/
        
        if(jR_ADD.isSelected())
        {
            local_AlarmGenerator = new ADAMS_TAB_ALARM_GENERATOR(TIPO_DI_CONFIGURAZIONE);
                        
            jTable_Alarm.setEnabled(false);
            jTable_Alarm.clearSelection();
            jTable_Alarm.setSelectionBackground(java.awt.Color.yellow);
            jL_TITLE.setBackground(java.awt.Color.yellow);
            
            jL_ID.setEnabled(true);
            jL_ID.setText("#");
            
            jT_Tag.setEnabled(true);
            jT_Tag.setText("");
            
            jT_desc.setEnabled(true);
            jT_desc.setText("");
            
            jText_pluginName.setEnabled(true);
            jB_pluginName.setEnabled(true);
            setGuiPlugin_Alarm(false);
            
            jChek_Threshold.setEnabled(true);
            jChek_Threshold.setSelected(false);
            setGUI_Op_Threshold();
            
        }
        else if(jR_MOD.isSelected())
        {   
                      
            jTable_Alarm.setEnabled(true);
            jTable_Alarm.setSelectionBackground(java.awt.Color.green.darker());
            jL_TITLE.setBackground(java.awt.Color.green.darker());
            
            int selectedRow = jTable_Alarm.getSelectedRow();        
            if(selectedRow >= 0)
            { 
                if(local_AlarmGenerator == null)
                    return;
                
                jL_ID.setEnabled(true);
                jL_ID.setText(""+local_AlarmGenerator.ID_ALARM_GENERATOR);

                jT_Tag.setEnabled(true);
                jT_Tag.setText(local_AlarmGenerator.SHORT_DESCRIPTION);

                jT_desc.setEnabled(true);
                jT_desc.setText(local_AlarmGenerator.LONG_DESCRIPTION);

                jText_pluginName.setEnabled(true);
                jB_pluginName.setEnabled(true);
                
                if(local_AlarmGenerator.STR_PLUGIN.length() > 0)
                    setGuiPlugin_Alarm(true);
                else
                    setGuiPlugin_Alarm(false);
                
                jChek_Threshold.setEnabled(true);
                if(local_AlarmGenerator.THRESHOLD_MANAGEMENT == 'Y')
                    jChek_Threshold.setSelected(true);
                else
                    jChek_Threshold.setSelected(false);
                
                setGUI_Op_Threshold();
                
            }
            else
            {
                jL_ID.setEnabled(false);
                jL_ID.setText("");

                jT_Tag.setEnabled(false);
                jT_Tag.setText("");

                jT_desc.setEnabled(false);
                jT_desc.setText("");

                jText_pluginName.setEnabled(false);
                jB_pluginName.setEnabled(false);
                setGuiPlugin_Alarm(false);

                jChek_Threshold.setEnabled(false);
                jChek_Threshold.setSelected(false);
                setGUI_Op_Threshold();
            }          
        }
        else // (jR_DEL.isSelected())
        {
            jTable_Alarm.setEnabled(true);
            jTable_Alarm.setSelectionBackground(java.awt.Color.red);
            jL_TITLE.setBackground(java.awt.Color.red);
            
            jL_ID.setEnabled(false);
            jT_Tag.setEnabled(false);
            jT_desc.setEnabled(false);
            jText_pluginName.setEnabled(false);
            jB_pluginName.setEnabled(false);
            
            jChek_Threshold.setEnabled(false);
            setGUI_Op_Threshold();
            
            int selectedRow = jTable_Alarm.getSelectedRow();        
            if(selectedRow >= 0)
            {   
                if(local_AlarmGenerator == null)
                    return;
                
                jL_ID.setText(""+local_AlarmGenerator.ID_ALARM_GENERATOR);                
                jT_Tag.setText(local_AlarmGenerator.SHORT_DESCRIPTION);                
                jT_desc.setText(local_AlarmGenerator.LONG_DESCRIPTION);
                
                if( local_AlarmGenerator.STR_PLUGIN.length() > 0)
                    setGuiPlugin_Alarm(true);
                else
                    setGuiPlugin_Alarm(false);
                
                if(local_AlarmGenerator.THRESHOLD_MANAGEMENT == 'Y')
                    jChek_Threshold.setSelected(true);
                else
                    jChek_Threshold.setSelected(false);
                
            }
            else
            {
                jL_ID.setText("");                
                jT_Tag.setText("");                
                jT_desc.setText("");                
                setGuiPlugin_Alarm(false);
            }
        }
        refresh_ListEnableAlarm_TH(V_ALL_THRESHOLD,local_AlarmGenerator.V_ALARM_THRESHOLD);
        enabledButtonCommit(); 
    }
    
    private void setGUI_Op_Threshold()
    {
        boolean flag;
        if( jR_ADD.isSelected() || jR_MOD.isSelected() )
        {
            flag = jChek_Threshold.isSelected();
        }
        else //jR_DEL.isSelected())
        {
           flag = jChek_Threshold.isEnabled();
        }
                
        jList_Disable_th.setEnabled(flag);
        jList_Enable_th.setEnabled(flag);
        jB_Add.setEnabled(flag);
        jB_AddAll.setEnabled(flag);
        jB_Rem.setEnabled(flag);
        jB_RemAll.setEnabled(flag);
    }
    
    
    private void jTable_AlarmMouseReleased(java.awt.event.MouseEvent evt) 
    {
        this.setCursor(Cur_wait);
        
        int selectedRow = jTable_Alarm.getSelectedRow();
        if(selectedRow >= 0)
        {            
            int id_A_Selected = new Integer(""+jTable_Alarm.getValueAt(selectedRow,0)).intValue();
           //System.out.println("id_Selected ---> "+id_A_Selected);
            
            if(V_ALL_ALARM_GENERATOR != null)
            {
                for(int i=0; i<V_ALL_ALARM_GENERATOR.size(); i++ )
                {
                    ADAMS_TAB_ALARM_GENERATOR appo_AG = ((ADAMS_TAB_ALARM_GENERATOR)V_ALL_ALARM_GENERATOR.elementAt(i));
                    if( appo_AG.ID_ALARM_GENERATOR == id_A_Selected  )
                    {                    
                        local_AlarmGenerator = appo_AG;
                       //System.out.println("Selected ----- >local_AlarmGenerator.ID_ALARM_GENERATOR "+local_AlarmGenerator.ID_ALARM_GENERATOR );
                                   
                        refresh_ListEnableAlarm_TH(V_ALL_THRESHOLD,local_AlarmGenerator.V_ALARM_THRESHOLD);
                        
                        setGUI_Op_Alarm();
                        
                        this.setCursor(Cur_default);
                        return;
                    }
                }
            }
            else
            {
                local_AlarmGenerator = null;
                System.out.println("Er. V_ALL_ALARM_GENERATOR == null");
            }   
        }
        this.setCursor(Cur_default);
    }
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        setGUI_Op_Alarm();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jR_DELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_DELActionPerformed
        setGUI_Op_Alarm();
    }//GEN-LAST:event_jR_DELActionPerformed

    private void jR_MODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_MODActionPerformed
        setGUI_Op_Alarm();
    }//GEN-LAST:event_jR_MODActionPerformed

    private void jR_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_ADDActionPerformed
        setGUI_Op_Alarm();
    }//GEN-LAST:event_jR_ADDActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        setCloseDialog();
    }//GEN-LAST:event_jB_closeActionPerformed

    private int isPresentTagAlarm(String TagAlarm)
    {
        for(int i=0; i<V_ALL_ALARM_GENERATOR.size(); i++)
        {
            ADAMS_TAB_ALARM_GENERATOR appo_AG = ((ADAMS_TAB_ALARM_GENERATOR)V_ALL_ALARM_GENERATOR.elementAt(i));
            if( ((appo_AG.SHORT_DESCRIPTION.toLowerCase()).compareTo(TagAlarm.toLowerCase())) == 0)
                return appo_AG.ID_ALARM_GENERATOR;
        }
        return -1;
    }
    
    public int getIdThreshold(String str_threshold)
    {
        for(int i=0; i<V_ALL_THRESHOLD.size(); i++)
        {            
            ADAMS_TAB_ALARM_THRESHOLD a_TH = (ADAMS_TAB_ALARM_THRESHOLD)V_ALL_THRESHOLD.elementAt(i);
            if( ((a_TH.DESCRIPTION.toLowerCase()).compareTo(str_threshold.toLowerCase())) == 0)
                return a_TH.ID_THRESHOLD_GENERATOR;
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
    
    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
    
        this.setCursor(Cur_wait);
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            this.setCursor(Cur_default);
            return;
        }
        
        if(local_AlarmGenerator == null)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
            this.setCursor(Cur_default);
            return;
        }
        
       //System.out.println("Selected per Commit ----- >local_AlarmGenerator.ID_ALARM_GENERATOR "+local_AlarmGenerator.ID_ALARM_GENERATOR );
        
        if(jR_DEL.isSelected())
        {
           //System.out.println(" COMMIT -- DELETE ");
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: the following Alarm Generator will be deleted. Please verify and confirm operation.","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {       
                int answer_del = local_AlarmGenerator.delete_ALARM_GENERATOR();
                                
                if(answer_del >= 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Generator Id # "+local_AlarmGenerator.ID_ALARM_GENERATOR+" has been deleted.","INFO",3);
                    V_ALL_ALARM_GENERATOR = read_AllAlarms();
                    AddRowjTable_Alarm(V_ALL_ALARM_GENERATOR);
                    setGUI_Op_Alarm();
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
            }
            else
            {
                this.setCursor(Cur_default);
                return;
            }
        }
        else
        {
             
            String tag = jT_Tag.getText().trim();
            
            int id_tag_crtl = isPresentTagAlarm(tag);
            if(id_tag_crtl != -1)
            {                
                if(jR_ADD.isSelected())
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Warning: Field Alarm Threshold Description < "+tag+" > already present.","WARNING",4);
                    this.setCursor(Cur_default);
                    return;
                }
                else if( (jR_MOD.isSelected()) &&  (id_tag_crtl != local_AlarmGenerator.ID_ALARM_GENERATOR) ) //se sono in modifica e non è lui stesso
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Warning: Field TAG ALARM < "+tag+" > already present.","WARNING",4);
                    this.setCursor(Cur_default);
                    return;
                }    
            }    
            
            local_AlarmGenerator.SHORT_DESCRIPTION = tag;
            local_AlarmGenerator.LONG_DESCRIPTION  = jT_desc.getText().trim();
            
            if(local_AlarmGenerator.STR_PLUGIN.length() == 0)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an Alarm Plugin.","INFO",3);
                this.setCursor(Cur_default);
                return;
            }
            
            //******************
           //System.out.println("V_Enable_th ----> SIZE() "+V_Enable_th.size());
            local_AlarmGenerator.V_ALARM_THRESHOLD.removeAllElements();
            
            for(int i=0; i<V_Enable_th.size(); i++)
            {                       
                int id_TH = getIdThreshold((String)V_Enable_th.elementAt(i));

               //System.out.print(" is ALARM ENABLED ==> ID = "+V_Enable_th.elementAt(i));
               //System.out.println(" desc = "+V_Enable_th.elementAt(i));

                if(id_TH != -1)
                    local_AlarmGenerator.V_ALARM_THRESHOLD.addElement(new Integer(id_TH));
                else
                {
                    System.out.println("err. Id Threshold not found....  getIdThreshold(...)");
                    return;
                }
            }
            //**********************
            
            int size_THRESHOLD = 0;
            if(local_AlarmGenerator.V_ALARM_THRESHOLD != null)
                size_THRESHOLD = local_AlarmGenerator.V_ALARM_THRESHOLD.size();
            
            if(jChek_Threshold.isSelected())
            {
                local_AlarmGenerator.THRESHOLD_MANAGEMENT = 'Y';
                if(size_THRESHOLD == 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Enable an item from the list < Enabled Alarm Thresholds >.","INFO",3);
                    this.setCursor(Cur_default);
                    return;
                }
            }
            else
                local_AlarmGenerator.THRESHOLD_MANAGEMENT = 'N';
              
            if(jR_ADD.isSelected())
            {
               //System.out.println(" COMMIT -- ADD ");
                int answer_ins = local_AlarmGenerator.insert_ALARM_GENERATOR();
                if(answer_ins >= 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Generator "+local_AlarmGenerator.SHORT_DESCRIPTION+" has been added.","INFO",3);
                    V_ALL_ALARM_GENERATOR = read_AllAlarms();
                    AddRowjTable_Alarm(V_ALL_ALARM_GENERATOR);
                    setGUI_Op_Alarm();
                    
                    jTable_Alarm.setSelectionBackground(java.awt.Color.yellow);
                    
                    //seleziona riga JTable
                    int ID_MAX = 0;
                    int indexSelected = 0;
                    for(int i=0; i<jTable_Alarm.getRowCount(); i++)
                    {
                        int ID = new Integer(""+jTable_Alarm.getValueAt(i,0)).intValue();
                        if(ID > ID_MAX)
                        {
                            ID_MAX = ID;
                            indexSelected = i;
                        }
                    }
                    
                    try
                    {   
                        jTable_Alarm.setRowSelectionInterval(0,indexSelected);
                        jScrollPane_Alarm.validate();
                    }
                    catch(Exception e){}
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Alarm Generator: "+local_AlarmGenerator.SHORT_DESCRIPTION+".","ERROR",1);
            }
            else //(jR_MOD.isSelected())
            {
               //System.out.println(" COMMIT -- MODIFY ");
                
                int answer_up = local_AlarmGenerator.update_ALARM_GENERATOR();
                if(answer_up >= 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Generator "+local_AlarmGenerator.SHORT_DESCRIPTION+" has been changed.","INFO",3);
                    V_ALL_ALARM_GENERATOR = read_AllAlarms();
                    AddRowjTable_Alarm(V_ALL_ALARM_GENERATOR);
                    setGUI_Op_Alarm();
                       
                    try
                    {   //seleziona riga JTable
                        for(int i=0; i<jTable_Alarm.getRowCount(); i++)
                        {
                            int ID_TABLE = new Integer(""+jTable_Alarm.getValueAt(i,0)).intValue();
                            if( ID_TABLE == local_AlarmGenerator.ID_ALARM_GENERATOR )
                            {
                                jTable_Alarm.setRowSelectionInterval(i,i);
                                jScrollPane_Alarm.validate();
                                break;
                            }
                        }
                    }catch(Exception e){}
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot change Alarm Generator: "+local_AlarmGenerator.SHORT_DESCRIPTION+".","ERROR",1);
            }    
        }
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_commitActionPerformed
   
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
       setCloseDialog();
    }//GEN-LAST:event_closeDialog

    private void setCloseDialog()
    {
         //System.out.println("Frame width ="+this.getSize().width+" Frame width ="+this.getSize().height );
         dispose();
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
        
//////////////////////////////////////////// END Internal CLASS ////////////////////////////////////////////  
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jL_TITLE;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane_Alarm;
    private javax.swing.JPanel jP_option;
    private javax.swing.JLabel jL_1;
    private javax.swing.JLabel jL_ID;
    private javax.swing.JLabel jL_2;
    private javax.swing.JTextField jT_Tag;
    private javax.swing.JLabel jL_3;
    private javax.swing.JTextField jT_desc;
    private javax.swing.JLabel jL_4;
    private javax.swing.JPanel jP_pl;
    private javax.swing.JTextField jText_pluginName;
    private javax.swing.JButton jB_pluginName;
    private javax.swing.JPanel jP_Threshold;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScroll1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jB_Add;
    private javax.swing.JButton jB_AddAll;
    private javax.swing.JButton jB_Rem;
    private javax.swing.JButton jB_RemAll;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScroll2;
    private javax.swing.JCheckBox jChek_Threshold;
    private javax.swing.JButton jB_ThresholdManag;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_close;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jR_ADD;
    private javax.swing.JRadioButton jR_MOD;
    private javax.swing.JRadioButton jR_DEL;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    
    private String TIPO_DI_CONFIGURAZIONE = "";
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_Alarm;
    private TableSorter sorter_Alarm;
    private MyTableModel myTableModel_Alarm;
    private String[] all_columnNames ={"ID","Alarm Tag","Plugin","Threshold"};
    private int [] cellDimen_Alarm ={30,330,140,80};
    
    
    private JListIcon jList_Disable_th      = null;
    private JListIcon jList_Enable_th       = null;
    private IconPool IcPool                 = null;
    private java.util.Vector V_Disable_th   = null;
    private java.util.Vector V_Enable_th    = null;
    //private java.awt.event.MouseAdapter eventList   = null;
    
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined";
    
    private ADAMS_TAB_ALARM_GENERATOR local_AlarmGenerator = null;
    private Vector V_ALL_ALARM_GENERATOR    = null;
    private Vector V_ALL_THRESHOLD          = null;
    
}
