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

public class ADAMS_JD_Threshold extends javax.swing.JDialog 
{

    /** Creates new form ADAMS_JD_Threshold */
    public ADAMS_JD_Threshold(boolean modal, String title, String TipoDiConfigurazione) 
    {
        super(ADAMS_GlobalParam.JFRAME_TAB, modal);
        initComponents();
        
        this.TIPO_DI_CONFIGURAZIONE = TipoDiConfigurazione;
        
         // *** Costruzione JTABLE TE ***/
        myTableModel_T = new MyTableModel(all_columnNames);
        sorter_T = new TableSorter(myTableModel_T);
        
        jTable_T = new javax.swing.JTable(sorter_T);
        JTableHeader header2 = jTable_T.getTableHeader();
        header2.setFont(ADAMS_GlobalParam.font_B10);
        header2.setReorderingAllowed(false);

        sorter_T.setTableHeader(header2);        
        
        jTable_T.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_T.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_T.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_T.setRowHeight(20);
        jTable_T.setRowMargin(2);
        jTable_T.setAutoCreateColumnsFromModel(false);
        jTable_T.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jTable_T.setSelectionBackground(java.awt.Color.yellow);
        //jTable_T.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        jTable_T.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TMouseReleased(evt);
            }
        });
        
        jScrollPane_T.setViewportView(jTable_T); 
        
        SetTable(jTable_T,30,cellDimen_T);
                
        jT_descThreshold.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,ADAMS_GlobalParam.LONG_DESC_LEN));
        jT_persistence.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,3));
        
        this.setTitle(title);
        jL_TITLE.setText(title); 
        
   
        //Font Globali
        jL_TITLE.setFont(ADAMS_GlobalParam.font_B12);
        jL_6.setFont(ADAMS_GlobalParam.font_B10);
        jL_5.setFont(ADAMS_GlobalParam.font_B10);
        jL_8.setFont(ADAMS_GlobalParam.font_B10);
        jL_7.setFont(ADAMS_GlobalParam.font_B10);
        jL_10.setFont(ADAMS_GlobalParam.font_B10);
        jL_9.setFont(ADAMS_GlobalParam.font_B10);
        jL_days.setFont(ADAMS_GlobalParam.font_B10);           
        jL_ID_Threshold.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_Holiday.setFont(ADAMS_GlobalParam.font_B10);
        jT_descThreshold.setFont(ADAMS_GlobalParam.font_B10);
        jText_pluginName.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_TypeThreshold.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_HoursAgg.setFont(ADAMS_GlobalParam.font_B10);
        jT_persistence.setFont(ADAMS_GlobalParam.font_B10);
        
        //Set Cursor
        jTable_T.setCursor(Cur_hand);
        jB_pluginName.setCursor(Cur_hand);
        jCBox_TypeThreshold.setCursor(Cur_hand);
        jCBox_HoursAgg.setCursor(Cur_hand);
        jCBox_Holiday.setCursor(Cur_hand);
        jR_ADD.setCursor(Cur_hand);
        jR_MOD.setCursor(Cur_hand);
        jR_DEL.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        
        jCBox_TypeThreshold.removeAllItems();
        for(int i=0; i<arr_type_threshold.length; i++)
        {
            jCBox_TypeThreshold.addItem(new String(arr_type_threshold[i]));
        }

        jCBox_HoursAgg.removeAllItems();
        for(int i=0; i<arr_hoursAgg.length; i++)
        {
            jCBox_HoursAgg.addItem(new String(""+arr_hoursAgg[i]));
        }

        V_ALL_THRESHOLD = read_AllThreshold();
        AddRowjTable_Th(V_ALL_THRESHOLD);
        
        setGUI_Op_Threshold();
               
        setCenteredFrame(660,540);
        this.setVisible(true);
    }

    private Vector read_AllThreshold()
    {            
        Vector V_TH = new Vector();

        String itemName =("ID_THRESHOLD_GENERATOR, DESCRIPTION, TYPE_THRESHOLD, ENABLE_HOLYDAY_THRESHOLD, STR_PLUGIN, THRESHOLD_PERSISTENCE, HOURS_AGGREGATE");                
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

                    row_th.TYPE_THRESHOLD = rs.getInt("TYPE_THRESHOLD"); 
                    
                    char H = rs.getString("ENABLE_HOLYDAY_THRESHOLD").charAt(0);
                    if(H != ' ')
                        row_th.ENABLE_HOLYDAY_THRESHOLD = H; 
                                   
                    String pl = rs.getString("STR_PLUGIN");
                        if(pl != null)
                            row_th.STR_PLUGIN = pl;
                    
                    row_th.THRESHOLD_PERSISTENCE = rs.getInt("THRESHOLD_PERSISTENCE");
                    row_th.HOURS_AGGREGATE = rs.getInt("HOURS_AGGREGATE");                    

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
    
    private void AddRowjTable_Th(Vector V_All_Th)
    {        
        Object[][] dataValues = new Object[0][all_columnNames.length];
        
        if(V_All_Th != null)
        {
            int size_V_ALL_RA = V_All_Th.size();            
            dataValues = new Object[size_V_ALL_RA][all_columnNames.length];
            
            for(int i=0; i<size_V_ALL_RA; i++)
            {
                ADAMS_TAB_ALARM_THRESHOLD row_Alarm = (ADAMS_TAB_ALARM_THRESHOLD)V_All_Th.elementAt(i);
                
                dataValues[i][0] = row_Alarm.ID_THRESHOLD_GENERATOR;
                dataValues[i][1] = row_Alarm.DESCRIPTION;
                dataValues[i][2] = row_Alarm.STR_PLUGIN;
            }               
        }
            
        myTableModel_T.setDataValues(dataValues);
        sorter_T.setTableModel(myTableModel_T);
        jScrollPane_T.setViewportView(jTable_T);   
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
     

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jL_TITLE = new javax.swing.JLabel();
        jP_table = new javax.swing.JPanel();
        jScrollPane_T = new javax.swing.JScrollPane();
        jP_Threshold = new javax.swing.JPanel();
        jL_5 = new javax.swing.JLabel();
        jT_descThreshold = new javax.swing.JTextField();
        jL_6 = new javax.swing.JLabel();
        jL_ID_Threshold = new javax.swing.JLabel();
        jL_7 = new javax.swing.JLabel();
        jCBox_TypeThreshold = new javax.swing.JComboBox();
        jL_8 = new javax.swing.JLabel();
        jP_pl_2 = new javax.swing.JPanel();
        jText_pluginName = new javax.swing.JTextField();
        jB_pluginName = new javax.swing.JButton();
        jCBox_Holiday = new javax.swing.JCheckBox();
        jL_9 = new javax.swing.JLabel();
        jL_10 = new javax.swing.JLabel();
        jCBox_HoursAgg = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jT_persistence = new javax.swing.JTextField();
        jL_days = new javax.swing.JLabel();
        jP_Button = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jR_ADD = new javax.swing.JRadioButton();
        jR_MOD = new javax.swing.JRadioButton();
        jR_DEL = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_TITLE.setBackground(new java.awt.Color(230, 230, 230));
        jL_TITLE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_TITLE.setText("ALARM THRESHOLD");
        jL_TITLE.setMinimumSize(new java.awt.Dimension(45, 25));
        jL_TITLE.setPreferredSize(new java.awt.Dimension(45, 25));
        jL_TITLE.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_TITLE, gridBagConstraints1);
        
        jP_table.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_table.setBackground(new java.awt.Color(230, 230, 230));
        jP_table.setBorder(new javax.swing.border.TitledBorder(null, " Alarm Threshold Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_table.setPreferredSize(new java.awt.Dimension(20, 130));
        jScrollPane_T.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_T.setPreferredSize(new java.awt.Dimension(3, 350));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_table.add(jScrollPane_T, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        getContentPane().add(jP_table, gridBagConstraints1);
        
        jP_Threshold.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_Threshold.setBackground(new java.awt.Color(230, 230, 230));
        jP_Threshold.setBorder(new javax.swing.border.TitledBorder(null, " Alarm Threshold ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_Threshold.setMinimumSize(new java.awt.Dimension(320, 140));
        jP_Threshold.setPreferredSize(new java.awt.Dimension(320, 140));
        jL_5.setBackground(new java.awt.Color(230, 230, 230));
        jL_5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_5.setText("Alarm Threshold Description");
        jL_5.setToolTipText("");
        jL_5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_5.setMaximumSize(new java.awt.Dimension(150, 20));
        jL_5.setMinimumSize(new java.awt.Dimension(150, 20));
        jL_5.setPreferredSize(new java.awt.Dimension(150, 20));
        jL_5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_5.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 1.0;
        jP_Threshold.add(jL_5, gridBagConstraints3);
        
        jT_descThreshold.setDisabledTextColor(java.awt.Color.gray);
        jT_descThreshold.setMinimumSize(new java.awt.Dimension(150, 20));
        jT_descThreshold.setPreferredSize(new java.awt.Dimension(150, 20));
        jT_descThreshold.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_descThresholdKeyReleased(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints3.weightx = 1.0;
        jP_Threshold.add(jT_descThreshold, gridBagConstraints3);
        
        jL_6.setBackground(new java.awt.Color(230, 230, 230));
        jL_6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_6.setText("ID");
        jL_6.setToolTipText("ID");
        jL_6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_6.setMaximumSize(new java.awt.Dimension(11, 20));
        jL_6.setMinimumSize(new java.awt.Dimension(11, 20));
        jL_6.setPreferredSize(new java.awt.Dimension(11, 20));
        jL_6.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_Threshold.add(jL_6, gridBagConstraints3);
        
        jL_ID_Threshold.setBackground(java.awt.Color.white);
        jL_ID_Threshold.setForeground(java.awt.Color.darkGray);
        jL_ID_Threshold.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_ID_Threshold.setMaximumSize(new java.awt.Dimension(70, 20));
        jL_ID_Threshold.setMinimumSize(new java.awt.Dimension(50, 20));
        jL_ID_Threshold.setPreferredSize(new java.awt.Dimension(50, 20));
        jL_ID_Threshold.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_Threshold.add(jL_ID_Threshold, gridBagConstraints3);
        
        jL_7.setBackground(new java.awt.Color(230, 230, 230));
        jL_7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_7.setText("Threshold Type");
        jL_7.setToolTipText("");
        jL_7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_7.setMaximumSize(new java.awt.Dimension(69, 20));
        jL_7.setMinimumSize(new java.awt.Dimension(69, 20));
        jL_7.setPreferredSize(new java.awt.Dimension(69, 20));
        jL_7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_7.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(3, 2, 1, 2);
        jP_Threshold.add(jL_7, gridBagConstraints3);
        
        jCBox_TypeThreshold.setBackground(java.awt.Color.white);
        jCBox_TypeThreshold.setMinimumSize(new java.awt.Dimension(120, 20));
        jCBox_TypeThreshold.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 0);
        jP_Threshold.add(jCBox_TypeThreshold, gridBagConstraints3);
        
        jL_8.setBackground(new java.awt.Color(230, 230, 230));
        jL_8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_8.setText("Alarm Threshold Plugin");
        jL_8.setToolTipText("");
        jL_8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_8.setMaximumSize(new java.awt.Dimension(41, 20));
        jL_8.setMinimumSize(new java.awt.Dimension(41, 20));
        jL_8.setPreferredSize(new java.awt.Dimension(41, 20));
        jL_8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_8.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        jP_Threshold.add(jL_8, gridBagConstraints3);
        
        jP_pl_2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_pl_2.setBackground(new java.awt.Color(230, 230, 230));
        jP_pl_2.setMinimumSize(new java.awt.Dimension(160, 21));
        jP_pl_2.setPreferredSize(new java.awt.Dimension(160, 21));
        jText_pluginName.setBackground(new java.awt.Color(230, 230, 230));
        jText_pluginName.setEditable(false);
        jText_pluginName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_pluginName.setToolTipText("");
        jText_pluginName.setDisabledTextColor(java.awt.Color.darkGray);
        jText_pluginName.setFocusable(false);
        jText_pluginName.setMinimumSize(new java.awt.Dimension(65, 20));
        jText_pluginName.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_pl_2.add(jText_pluginName, gridBagConstraints4);
        
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
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        jP_pl_2.add(jB_pluginName, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_Threshold.add(jP_pl_2, gridBagConstraints3);
        
        jCBox_Holiday.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_Holiday.setForeground(java.awt.Color.red);
        jCBox_Holiday.setText("<html><b>Enable Holiday</b><br>Threshold</html>");
        jCBox_Holiday.setToolTipText("(True/False)");
        jCBox_Holiday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCBox_Holiday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCBox_Holiday.setMaximumSize(new java.awt.Dimension(80, 25));
        jCBox_Holiday.setMinimumSize(new java.awt.Dimension(160, 22));
        jCBox_Holiday.setPreferredSize(new java.awt.Dimension(160, 22));
        jCBox_Holiday.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_Holiday.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCBox_Holiday.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_Holiday.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 5;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridheight = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_Threshold.add(jCBox_Holiday, gridBagConstraints3);
        
        jL_9.setBackground(new java.awt.Color(230, 230, 230));
        jL_9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_9.setText("Persistence");
        jL_9.setToolTipText("");
        jL_9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_9.setMaximumSize(new java.awt.Dimension(69, 20));
        jL_9.setMinimumSize(new java.awt.Dimension(80, 20));
        jL_9.setPreferredSize(new java.awt.Dimension(80, 20));
        jL_9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_9.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(3, 2, 1, 2);
        jP_Threshold.add(jL_9, gridBagConstraints3);
        
        jL_10.setBackground(new java.awt.Color(230, 230, 230));
        jL_10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_10.setText("Hours Aggregate");
        jL_10.setToolTipText("");
        jL_10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_10.setMaximumSize(new java.awt.Dimension(120, 20));
        jL_10.setMinimumSize(new java.awt.Dimension(120, 20));
        jL_10.setPreferredSize(new java.awt.Dimension(120, 20));
        jL_10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_10.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_Threshold.add(jL_10, gridBagConstraints3);
        
        jCBox_HoursAgg.setBackground(java.awt.Color.white);
        jCBox_HoursAgg.setMinimumSize(new java.awt.Dimension(120, 20));
        jCBox_HoursAgg.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 0);
        jP_Threshold.add(jCBox_HoursAgg, gridBagConstraints3);
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 2, 2));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(90, 22));
        jPanel1.setPreferredSize(new java.awt.Dimension(90, 22));
        jT_persistence.setMinimumSize(new java.awt.Dimension(40, 20));
        jT_persistence.setPreferredSize(new java.awt.Dimension(40, 20));
        jT_persistence.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_persistenceKeyReleased(evt);
            }
        });
        
        jPanel1.add(jT_persistence);
        
        jL_days.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_days.setText("Days");
        jL_days.setToolTipText("Days");
        jL_days.setMaximumSize(new java.awt.Dimension(40, 20));
        jL_days.setMinimumSize(new java.awt.Dimension(40, 20));
        jL_days.setPreferredSize(new java.awt.Dimension(40, 20));
        jPanel1.add(jL_days);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_Threshold.add(jPanel1, gridBagConstraints3);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jP_Threshold, gridBagConstraints1);
        
        jP_Button.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_Button.setBackground(new java.awt.Color(230, 230, 230));
        jP_Button.setMinimumSize(new java.awt.Dimension(200, 40));
        jP_Button.setPreferredSize(new java.awt.Dimension(200, 40));
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
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        jP_Button.add(jB_commit, gridBagConstraints5);
        
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
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 2;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        jP_Button.add(jB_close, gridBagConstraints5);
        
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
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        jP_Button.add(jPanel4, gridBagConstraints5);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jP_Button, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jT_persistenceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_persistenceKeyReleased
        try
        {
            jT_persistence.setText(""+new Integer(jT_persistence.getText()).intValue());
        }
        catch(Exception e){}
    }//GEN-LAST:event_jT_persistenceKeyReleased

    private void jB_pluginNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pluginNameActionPerformed
        
        ADAMS_JD_setPlugin JD_PluginSelect = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,TIPO_DI_CONFIGURAZIONE,local_AlarmThreshold.STR_PLUGIN);
        
        JD_PluginSelect.setGuiEnable(true);          
        JD_PluginSelect.setVisible(true);
        
        local_AlarmThreshold.STR_PLUGIN = JD_PluginSelect.getPluginEnabled();
        JD_PluginSelect.dispose();
        
        if( local_AlarmThreshold.STR_PLUGIN.length()>0 )        
            setGuiPlugin_Th(true);
        else
            setGuiPlugin_Th(false);       

        //System.out.println("local_AlarmThreshold.STR_PLUGIN ="+local_AlarmThreshold.STR_PLUGIN);
    }//GEN-LAST:event_jB_pluginNameActionPerformed

    private void jT_descThresholdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_descThresholdKeyReleased
        enabledButtonCommit();
    }//GEN-LAST:event_jT_descThresholdKeyReleased

    public void enabledButtonCommit()
    {
        if( (jT_descThreshold.getText().trim()).length() == 0 )
        {
            jB_commit.setEnabled(false);
        }
        else
            jB_commit.setEnabled(true);
    }

    private void setGuiPlugin_Th(boolean flag)
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
    
    private void setGUI_Op_Threshold()
    {
        //System.out.println("setGUI_Op_Threshold()");
        //if(local_AlarmThreshold != null)
        //    System.out.println("Selected ----- >local_AlarmThreshold.ID_THRESHOLD_GENERATOR "+local_AlarmThreshold.ID_THRESHOLD_GENERATOR );
        //else
        //    System.out.println("local_AlarmThreshold NULL");
        
        if(jR_ADD.isSelected())
        {            
            local_AlarmThreshold = new ADAMS_TAB_ALARM_THRESHOLD(TIPO_DI_CONFIGURAZIONE);
                        
            jTable_T.setEnabled(false);
            jTable_T.clearSelection();
            jTable_T.setSelectionBackground(java.awt.Color.yellow);
            jL_TITLE.setBackground(java.awt.Color.yellow);

            jL_ID_Threshold.setEnabled(true);
            jL_ID_Threshold.setText("#");

            jT_descThreshold.setEnabled(true);
            jT_descThreshold.setText("");

            jCBox_TypeThreshold.setEnabled(true);
            jCBox_TypeThreshold.setSelectedIndex(0);

            jCBox_HoursAgg.setEnabled(true);
            jCBox_HoursAgg.setSelectedIndex(0);
            
            jT_persistence.setEnabled(true);
            jT_persistence.setText("");
            
            jText_pluginName.setEnabled(true);
            jB_pluginName.setEnabled(true);
            setGuiPlugin_Th(false);

            jCBox_Holiday.setEnabled(true);
            jCBox_Holiday.setSelected(false);
        }
        else if(jR_MOD.isSelected())
        {                         
            jTable_T.setEnabled(true);
            jTable_T.setSelectionBackground(java.awt.Color.green.darker());
            jL_TITLE.setBackground(java.awt.Color.green.darker());

            int selectedRow = jTable_T.getSelectedRow();        
            if(selectedRow >= 0)
            { 
                if(local_AlarmThreshold == null)
                    return;
                
                jL_ID_Threshold.setEnabled(true);
                jL_ID_Threshold.setText(""+local_AlarmThreshold.ID_THRESHOLD_GENERATOR);

                jT_descThreshold.setEnabled(true);
                jT_descThreshold.setText(local_AlarmThreshold.DESCRIPTION);

                jCBox_TypeThreshold.setEnabled(true);
                jCBox_TypeThreshold.setSelectedIndex(local_AlarmThreshold.TYPE_THRESHOLD);

                jCBox_HoursAgg.setEnabled(true);
                int index_jcb_HoursAgg = 0;
                for(int a=0; a<arr_hoursAgg.length; a++)
                    if(local_AlarmThreshold.HOURS_AGGREGATE == arr_hoursAgg[a])
                        index_jcb_HoursAgg = a;
                jCBox_HoursAgg.setSelectedIndex(index_jcb_HoursAgg);
                
                jT_persistence.setEnabled(true);
                jT_persistence.setText(""+local_AlarmThreshold.THRESHOLD_PERSISTENCE);
                
                jText_pluginName.setEnabled(true);
                jB_pluginName.setEnabled(true);
                
                 if(local_AlarmThreshold.STR_PLUGIN.length() > 0)
                    setGuiPlugin_Th(true);
                else
                    setGuiPlugin_Th(false);
                
                jCBox_Holiday.setEnabled(true);
                if(local_AlarmThreshold.ENABLE_HOLYDAY_THRESHOLD == 'Y')
                    jCBox_Holiday.setSelected(true);
                else
                    jCBox_Holiday.setSelected(false);
                
            }
            else
            {
                jL_ID_Threshold.setEnabled(false);
                jL_ID_Threshold.setText("");

                jT_descThreshold.setEnabled(false);
                jT_descThreshold.setText("");

                jCBox_TypeThreshold.setEnabled(false);
                jCBox_TypeThreshold.setSelectedIndex(0);
                
                jCBox_HoursAgg.setEnabled(false);
                jCBox_HoursAgg.setSelectedIndex(0);
                
                jT_persistence.setEnabled(false);
                jT_persistence.setText("");

                jText_pluginName.setEnabled(false);
                jB_pluginName.setEnabled(false);
                setGuiPlugin_Th(false);

                jCBox_Holiday.setEnabled(false);
                jCBox_Holiday.setSelected(false);
            }          
        }
        else // (jR_DEL.isSelected())
        {
            jTable_T.setEnabled(true);
            jTable_T.setSelectionBackground(java.awt.Color.red);
            jL_TITLE.setBackground(java.awt.Color.red);
            
            jL_ID_Threshold.setEnabled(false);
            jT_descThreshold.setEnabled(false);
            jCBox_TypeThreshold.setEnabled(false);
            jCBox_HoursAgg.setEnabled(false);
                
            jT_persistence.setEnabled(false);
            jText_pluginName.setEnabled(false);
            jCBox_Holiday.setEnabled(false);
                        
            int selectedRow = jTable_T.getSelectedRow();        
            if(selectedRow >= 0)
            {   
                if(local_AlarmThreshold == null)
                    return;
                
                jL_ID_Threshold.setText(""+local_AlarmThreshold.ID_THRESHOLD_GENERATOR);                
                jT_descThreshold.setText(local_AlarmThreshold.DESCRIPTION);                
                jCBox_TypeThreshold.setSelectedIndex(local_AlarmThreshold.TYPE_THRESHOLD);

                int index_jcb_HoursAgg = 0;
                for(int a=0; a<arr_hoursAgg.length; a++)
                    if(local_AlarmThreshold.HOURS_AGGREGATE == arr_hoursAgg[a])
                        index_jcb_HoursAgg = a;
                jCBox_HoursAgg.setSelectedIndex(index_jcb_HoursAgg);
                
                jT_persistence.setText(""+local_AlarmThreshold.THRESHOLD_PERSISTENCE);
                
                if(local_AlarmThreshold.STR_PLUGIN.length() > 0)
                    setGuiPlugin_Th(true);
                else
                    setGuiPlugin_Th(false);
                
                if(local_AlarmThreshold.ENABLE_HOLYDAY_THRESHOLD == 'Y')
                    jCBox_Holiday.setSelected(true);
                else
                    jCBox_Holiday.setSelected(false);
            }
            else
            {
                jL_ID_Threshold.setText("");                
                jT_descThreshold.setText("");                
                jCBox_TypeThreshold.setSelectedIndex(0);
                jCBox_HoursAgg.setSelectedIndex(0);
                jT_persistence.setText("");
                setGuiPlugin_Th(false);
            }
        }
        enabledButtonCommit(); 
    }

    private void jTable_TMouseReleased(java.awt.event.MouseEvent evt) 
    {
         this.setCursor(Cur_wait);
        
        int selectedRow = jTable_T.getSelectedRow();
        if(selectedRow >= 0)
        {            
            int id_Th_Selected = new Integer(""+jTable_T.getValueAt(selectedRow,0)).intValue();
            //System.out.println("id_Selected ---> "+id_Th_Selected);
            
            if(V_ALL_THRESHOLD != null)
            {
                for(int i=0; i<V_ALL_THRESHOLD.size(); i++ )
                {
                    ADAMS_TAB_ALARM_THRESHOLD appo_Th = ((ADAMS_TAB_ALARM_THRESHOLD)V_ALL_THRESHOLD.elementAt(i));
                    if( appo_Th.ID_THRESHOLD_GENERATOR == id_Th_Selected  )
                    {                    
                        local_AlarmThreshold = appo_Th;
                        //System.out.println("Selected ----- >local_AlarmThreshold.ID_THRESHOLD_GENERATOR "+local_AlarmThreshold.ID_THRESHOLD_GENERATOR );
                        setGUI_Op_Threshold();
                        this.setCursor(Cur_default);
                        return;
                    }
                }
            }
            else
            {
                local_AlarmThreshold = null;
                System.out.println("Er. V_ALL_THRESHOLD == null");
            }   
        }
        this.setCursor(Cur_default);
    }
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        setGUI_Op_Threshold();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jR_DELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_DELActionPerformed
        setGUI_Op_Threshold();
    }//GEN-LAST:event_jR_DELActionPerformed

    private void jR_MODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_MODActionPerformed
        setGUI_Op_Threshold();
    }//GEN-LAST:event_jR_MODActionPerformed

    private void jR_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_ADDActionPerformed
        setGUI_Op_Threshold();
    }//GEN-LAST:event_jR_ADDActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        setCloseDialog();
    }//GEN-LAST:event_jB_closeActionPerformed

    private int isPresentTagThreshold(String Tag_th)
    {
        for(int i=0; i<V_ALL_THRESHOLD.size(); i++)
        {
            ADAMS_TAB_ALARM_THRESHOLD appo_Th = ((ADAMS_TAB_ALARM_THRESHOLD)V_ALL_THRESHOLD.elementAt(i));
            if( ((appo_Th.DESCRIPTION.toLowerCase()).compareTo(Tag_th.toLowerCase())) == 0 )
                return appo_Th.ID_THRESHOLD_GENERATOR;
        }
        return -1;
    }
    
    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
    
        this.setCursor(Cur_wait);
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            this.setCursor(Cur_default);
            return;
        }
        
        if(local_AlarmThreshold == null)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
            this.setCursor(Cur_default);
            return;
        }
        
        //System.out.println("Selected per Commit ----- >local_AlarmThreshold.ID_THRESHOLD_GENERATOR "+local_AlarmThreshold.ID_THRESHOLD_GENERATOR );
        if(jR_DEL.isSelected())
        {
            //System.out.println(" COMMIT -- DELETE ");
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: the following Alarm Threshold will be deleted. Please verify and confirm operation.","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {       
                int answer_del = local_AlarmThreshold.delete_ALARM_THRESHOLD();
                if(answer_del >= 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Threshold Id # "+local_AlarmThreshold.ID_THRESHOLD_GENERATOR+" has been deleted.","INFO",3);
                    
                    V_ALL_THRESHOLD = read_AllThreshold();
                    AddRowjTable_Th(V_ALL_THRESHOLD);        
                    setGUI_Op_Threshold();
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
            String tag = jT_descThreshold.getText().trim();
            int id_tag_crtl = isPresentTagThreshold(tag);
            //System.out.println(" id_tag_crtl "+id_tag_crtl);
            
            if( id_tag_crtl != -1 )
            {
                if(jR_ADD.isSelected())
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Warning: Field Alarm Threshold Description < "+tag+" > already present.","WARNING",4);
                    this.setCursor(Cur_default);
                    return;
                }
                else if( (jR_MOD.isSelected()) && (id_tag_crtl != local_AlarmThreshold.ID_THRESHOLD_GENERATOR) ) //se sono in modifica e non è lui stesso
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Warning: Field Alarm Threshold Description < "+tag+" > already present.","WARNING",4);
                    this.setCursor(Cur_default);
                    return;
                }
            }   

            if(jT_persistence.getText().trim().length() == 0)
            {
                ADAMS_GlobalParam.optionPanel("Please insert Days Persistence. ", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);
                this.setCursor(Cur_default);
                return;
            }

            local_AlarmThreshold.DESCRIPTION = tag;
            local_AlarmThreshold.TYPE_THRESHOLD = jCBox_TypeThreshold.getSelectedIndex();
            local_AlarmThreshold.THRESHOLD_PERSISTENCE = new Integer(jT_persistence.getText()).intValue();
            local_AlarmThreshold.HOURS_AGGREGATE = arr_hoursAgg[jCBox_HoursAgg.getSelectedIndex()];
            
            
            if(local_AlarmThreshold.STR_PLUGIN.length() == 0)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an Alarm Theshold Plugin.","INFO",3);
                this.setCursor(Cur_default);
                return;
            }
            
            if(jCBox_Holiday.isSelected())
                local_AlarmThreshold.ENABLE_HOLYDAY_THRESHOLD = 'Y';
            else
                local_AlarmThreshold.ENABLE_HOLYDAY_THRESHOLD = 'N';
               
            if(jR_ADD.isSelected())
            {
                //System.out.println(" COMMIT -- ADD ");
                int answer_ins = local_AlarmThreshold.insert_ALARM_THRESHOLD();
                if(answer_ins >= 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Threshold "+local_AlarmThreshold.DESCRIPTION+" has been added.","INFO",3);
                    V_ALL_THRESHOLD = read_AllThreshold();
                    AddRowjTable_Th(V_ALL_THRESHOLD);        
                    setGUI_Op_Threshold();
                    
                    jTable_T.setSelectionBackground(java.awt.Color.yellow);
                    
                    //seleziona riga JTable
                    int ID_MAX = 0;
                    int indexSelected = 0;
                    for(int i=0; i<jTable_T.getRowCount(); i++)
                    {
                        int ID = new Integer(""+jTable_T.getValueAt(i,0)).intValue();
                        if(ID > ID_MAX)
                        {
                            ID_MAX = ID;
                            indexSelected = i;
                        }
                    }                    
                    try
                    {   
                        jTable_T.setRowSelectionInterval(0,indexSelected);
                        jScrollPane_T.validate();
                    }
                    catch(Exception e){}
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Alarm Threshold: "+local_AlarmThreshold.DESCRIPTION+".","ERROR",1);
            }
            else //(jR_MOD.isSelected())
            {
                //System.out.println(" COMMIT -- MODIFY ");
                
                int answer_up = local_AlarmThreshold.update_ALARM_THRESHOLD();
                if(answer_up >= 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Alarm Threshold "+local_AlarmThreshold.DESCRIPTION+" has been changed.","INFO",3);
                    V_ALL_THRESHOLD = read_AllThreshold();
                    AddRowjTable_Th(V_ALL_THRESHOLD);        
                    setGUI_Op_Threshold();
                       
                    try
                    {   //seleziona riga JTable
                        for(int i=0; i<jTable_T.getRowCount(); i++)
                        {
                            int ID_TABLE = new Integer(""+jTable_T.getValueAt(i,0)).intValue();
                            if( ID_TABLE == local_AlarmThreshold.ID_THRESHOLD_GENERATOR )
                            {
                                jTable_T.setRowSelectionInterval(i,i);
                                jScrollPane_T.validate();
                                break;
                            }
                        }
                    }catch(Exception e){}
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot change Alarm Generator: "+local_AlarmThreshold.DESCRIPTION+".","ERROR",1);
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
    private javax.swing.JPanel jP_table;
    private javax.swing.JScrollPane jScrollPane_T;
    private javax.swing.JPanel jP_Threshold;
    private javax.swing.JLabel jL_5;
    private javax.swing.JTextField jT_descThreshold;
    private javax.swing.JLabel jL_6;
    private javax.swing.JLabel jL_ID_Threshold;
    private javax.swing.JLabel jL_7;
    private javax.swing.JComboBox jCBox_TypeThreshold;
    private javax.swing.JLabel jL_8;
    private javax.swing.JPanel jP_pl_2;
    private javax.swing.JTextField jText_pluginName;
    private javax.swing.JButton jB_pluginName;
    private javax.swing.JCheckBox jCBox_Holiday;
    private javax.swing.JLabel jL_9;
    private javax.swing.JLabel jL_10;
    private javax.swing.JComboBox jCBox_HoursAgg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jT_persistence;
    private javax.swing.JLabel jL_days;
    private javax.swing.JPanel jP_Button;
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
    
    private javax.swing.JTable jTable_T;
    private TableSorter sorter_T;
    private MyTableModel myTableModel_T;
    private String[] all_columnNames ={"ID","Description","Plungin"};
    private int [] cellDimen_T ={30,360,100};
    
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined";
      
    //attenzione l'indice dell'array arr_type_threshold è il vaolore identificativo;
    // 0 = "Minimum";
    // 1 = "Maximum"
    private String[] arr_type_threshold = {"Minimum","Maximum"};
    private int[] arr_hoursAgg = {1,2,3,4,6,8,12,24};
    
    private Vector V_ALL_THRESHOLD = null;
    
    ADAMS_TAB_ALARM_THRESHOLD local_AlarmThreshold = null;
    
}
