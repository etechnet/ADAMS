
import java.awt.Color;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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

public class SSM_JP_ProcessConfiguration extends javax.swing.JPanel {

    /**
     * Creates new form SSM_JP_ProcessConfiguration
     */

    @SuppressWarnings("unchecked")
    public SSM_JP_ProcessConfiguration(javax.swing.JFrame parent) {
                
        this.frame_parent = parent;
        
        initComponents();
        
        jTB_add.setCursor(Cur_hand);
        jTB_mod.setCursor(Cur_hand);
        jTB_del.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_apply.setCursor(Cur_hand);
        jCBox_ptype.setCursor(Cur_hand);
        jCBox_log_output.setCursor(Cur_hand);
        jB_decrease_hh.setCursor(Cur_hand);
        jB_decrease_mm.setCursor(Cur_hand);
        jB_increase_hh.setCursor(Cur_hand);
        jB_increase_mm.setCursor(Cur_hand);
        jTB_schedIgnore_off.setCursor(Cur_hand);
        jTB_schedIgnore_on.setCursor(Cur_hand);
        
        jTF_pname.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,128));
        jTF_start_cmd.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,512));
        jTF_hh.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,2));
        jTF_mm.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,2));
            
        for(int i=0; i<str_process_type.length;i++) {
            jCBox_ptype.addItem(str_process_type[i]);
        }
        jCBox_ptype.setSelectedIndex(0);
        
        for(int i=0; i<str_log_output.length;i++) {
            jCBox_log_output.addItem(str_log_output[i]);
        }
        jCBox_log_output.setSelectedIndex(1);
        
        setEnabled_GUI_WakeTime(false);
                
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_process = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_process.getTableHeader();
        header.setFont(SSM_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_process.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_process.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_process.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_process.setRowHeight(20);
        //jTable_process.setSelectionBackground(java.awt.Color.green);
        //jTable_process.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_process.setRowMargin(2);
        jTable_process.setAutoCreateColumnsFromModel(false);
        jTable_process.setCursor(Cur_wait);
        
        jScrollPane1.setViewportView(jTable_process);
        
        jTable_process.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_processMouseReleased(evt);
            }
        });       

        jTB_add.setSelected(true);
        setTable(jTable_process);        
        addRowJTable();        
        setSelected_jTableRow();
        
        
        // **************************** //
    }
    private void setTable(javax.swing.JTable jtable)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jtable.getTableHeader();
        header.setFont(SSM_GlobalParam.font_B10);
        header.setReorderingAllowed(false);
        //header.setPreferredSize(200,40);
        
        TableColumn column = null;
        for(int i=0; i<jtable.getColumnCount(); i++)
        {
            column = jtable.getColumnModel().getColumn(i);
            
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);       
            
            //if(CellDimension[i] == minCellDimension)
                //column.setMaxWidth(50);

        }        
        //sorter.setSortingStatus(1,1); 
        
        if((jtable.isShowing())&&(jtable.isVisible()))
        {
            //System.out.println("1 ctrl updateUI");
            jtable.updateUI();
        }               
        this.setCursor(Cur_default);
    }
    
    private void addRowJTable()
    {
        jTable_process.setCursor(Cur_wait);
        boolean ok_read = V_TAB_T_PROCESS.readProcess();
                
        int SIZE = V_TAB_T_PROCESS.size();
        Object[][] dataValues = new Object[SIZE][myTableModel.getColumnCount()];       
        
        
        System.out.println("size Process --> "+SIZE);
        for(int i=0; i<SIZE; i++)
        {
            SSM_TAB_T_PROCESS row_t_process = (SSM_TAB_T_PROCESS)V_TAB_T_PROCESS.elementAt(i);            
            
            dataValues[i][0] = row_t_process.getProcess_id();
            dataValues[i][1] = row_t_process.getProcess_name();
            
            try{
                dataValues[i][2] = str_process_type[row_t_process.getProcess_type()];
            }
            catch(Exception e) {e.printStackTrace();}
            
            dataValues[i][3] = row_t_process.getStart_cmd();
            dataValues[i][4] = row_t_process.getLog_output();
            
            
            if(ABILITY_WAKE_TIME == row_t_process.getProcess_type())
            {
                //////// Format DATE
                Date time = (row_t_process.getWake_time()).getTime();                    
                SimpleDateFormat ftime = new SimpleDateFormat ("HH:mm");
                String str_time_format = ftime.format(time);
                //////// Format DATE            
                dataValues[i][5] = str_time_format;      
            }
            else
               dataValues[i][5] = "--";

            String str_OFF_ON = "NO";          
            if(row_t_process.getSchedule_ignore() == 1)
                str_OFF_ON = "YES";          
            dataValues[i][6] = str_OFF_ON;
        }
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);

        jScrollPane1.setViewportView(jTable_process);

        jTable_process.setCursor(Cur_default);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
private void setSelected_jTableRow()
{
    this.setCursor(Cur_wait);
    int selectedRow = jTable_process.getSelectedRow();

    if( (jTB_mod.isSelected()) || (jTB_del.isSelected()) )
    {
        if(selectedRow >= 0)
        {
            boolean ctrl_row = false;
            int process_id_selected = new Integer(""+jTable_process.getValueAt(selectedRow,0)).intValue();
            System.out.println("process_id_selected => "+process_id_selected);
            
            
            for(int i=0; i<V_TAB_T_PROCESS.size(); i++) 
            {
                Selected_row_TAB_T_PROCESS = (SSM_TAB_T_PROCESS)V_TAB_T_PROCESS.elementAt(i);      
                if(Selected_row_TAB_T_PROCESS.getProcess_id() == process_id_selected)
                {
                    ctrl_row = true;
                    break;
                }
            }
            if(ctrl_row == false)
            {
                Selected_row_TAB_T_PROCESS = null;
                System.out.println("----- ERRORE Selected_row...");
                this.setCursor(Cur_default);
                return;
            }
            
            jTF_pname.setText(Selected_row_TAB_T_PROCESS.getProcess_name());             
            jTF_process_id.setText(""+Selected_row_TAB_T_PROCESS.getProcess_id());
            jTF_start_cmd.setText(Selected_row_TAB_T_PROCESS.getStart_cmd());
           
            GregorianCalendar calendar = Selected_row_TAB_T_PROCESS.getWake_time();
            if(calendar != null)
            {
                //////// Format DATE
                Date time = calendar.getTime();                    
                SimpleDateFormat f_hour = new SimpleDateFormat ("HH");
                SimpleDateFormat f_minute = new SimpleDateFormat ("mm");
                String str_hour_format = f_hour.format(time);
                String str_minute_format = f_minute.format(time);
                //////// Format DATE

                jTF_hh.setText(str_hour_format);
                jTF_mm.setText(str_minute_format);
                
            }
            else
            {
                jTF_hh.setText("00");
                jTF_mm.setText("00");
            }
            
            jCBox_ptype.setSelectedItem(str_process_type[Selected_row_TAB_T_PROCESS.getProcess_type()]);            
            jCBox_log_output.setSelectedItem(Selected_row_TAB_T_PROCESS.getLog_output());
                        
            if(Selected_row_TAB_T_PROCESS.getSchedule_ignore() == 1)
            {
                jTB_schedIgnore_on.setSelected(true);
                jTB_schedIgnore_off.setSelected(false);
            }
            else
            {
                jTB_schedIgnore_on.setSelected(false);
                jTB_schedIgnore_off.setSelected(true);
            }
            
        }
        else
        {         
            Selected_row_TAB_T_PROCESS = null;
            setEnabledClean_AllGui(false,true);
            
            jTF_hh.setText("00");
            jTF_mm.setText("00");
          
            jTable_process.setEnabled(true);            
         
            this.setCursor(Cur_default);
            return;
        }
        
        if(jTB_mod.isSelected())
        {               
            setEnabledClean_AllGui(true,false);
                        
            if( jCBox_ptype.getSelectedIndex() == ABILITY_WAKE_TIME )  
                setEnabled_GUI_WakeTime(true);
            else
                setEnabled_GUI_WakeTime(false);
        }
        else //jrb.isSelected()
            setEnabledClean_AllGui(false,false);

    }
    else //jTB_add.isSelected())
    {
        jTable_process.setEnabled(false);
        setEnabledClean_AllGui(true,true);
        Selected_row_TAB_T_PROCESS = new SSM_TAB_T_PROCESS();
    }
    this.setCursor(Cur_default);
}  
    
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {
        jTF_process_id.setEnabled(enable);
        jTF_pname.setEnabled(enable);
        jTF_start_cmd.setEnabled(enable);
        jCBox_ptype.setEnabled(enable);
        jCBox_log_output.setEnabled(enable);
        jB_decrease_hh.setEnabled(enable);
        jB_decrease_mm.setEnabled(enable);
        jB_increase_hh.setEnabled(enable);
        jB_increase_mm.setEnabled(enable);
        jTF_hh.setEnabled(enable);
        jTF_mm.setEnabled(enable);
        jTB_schedIgnore_off.setEnabled(enable);
        jTB_schedIgnore_on.setEnabled(enable);
                
        
        if(clean)
        {            
            jTF_process_id.setText("");
            jTF_pname.setText("");
            jTF_start_cmd.setText("");        
            jCBox_ptype.setSelectedIndex(0);
            jCBox_log_output.setSelectedIndex(1);
            setEnabled_GUI_WakeTime(false);
            jTB_schedIgnore_off.setSelected(true);
        }       
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        bGroup_activeF = new javax.swing.ButtonGroup();
        bGroup_schedF = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jP_edit = new javax.swing.JPanel();
        jL_pname = new javax.swing.JLabel();
        jTF_pname = new javax.swing.JTextField();
        jL_ptype = new javax.swing.JLabel();
        jCBox_ptype = new javax.swing.JComboBox();
        jL_process_id = new javax.swing.JLabel();
        jTF_process_id = new javax.swing.JTextField();
        jL_sched_Ignore = new javax.swing.JLabel();
        jP_schedF = new javax.swing.JPanel();
        jTB_schedIgnore_off = new javax.swing.JToggleButton();
        jTB_schedIgnore_on = new javax.swing.JToggleButton();
        jL_waketime = new javax.swing.JLabel();
        jP_waketime = new javax.swing.JPanel();
        jB_increase_hh = new javax.swing.JButton();
        jB_increase_mm = new javax.swing.JButton();
        jL_hh = new javax.swing.JLabel();
        jTF_hh = new javax.swing.JTextField();
        jL_mm = new javax.swing.JLabel();
        jTF_mm = new javax.swing.JTextField();
        jB_decrease_hh = new javax.swing.JButton();
        jB_decrease_mm = new javax.swing.JButton();
        jL_start_cmd = new javax.swing.JLabel();
        jTF_start_cmd = new javax.swing.JTextField();
        jL_log_output = new javax.swing.JLabel();
        jCBox_log_output = new javax.swing.JComboBox();
        jPs_s = new javax.swing.JPanel();
        jTB_add = new javax.swing.JToggleButton();
        jTB_mod = new javax.swing.JToggleButton();
        jTB_del = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JSeparator();
        jB_cancel = new javax.swing.JButton();
        jB_apply = new javax.swing.JButton();

        setBackground(new java.awt.Color(145, 181, 255));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Process Configuration");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 60));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 60));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 60));
        add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jP_south.setBackground(new java.awt.Color(145, 181, 255));
        jP_south.setMinimumSize(new java.awt.Dimension(800, 210));
        jP_south.setPreferredSize(new java.awt.Dimension(800, 210));
        jP_south.setLayout(new java.awt.GridBagLayout());

        jP_edit.setBackground(new java.awt.Color(145, 181, 255));
        jP_edit.setMinimumSize(new java.awt.Dimension(1074, 100));
        jP_edit.setPreferredSize(new java.awt.Dimension(1074, 100));
        jP_edit.setLayout(new java.awt.GridBagLayout());

        jL_pname.setBackground(new java.awt.Color(230, 230, 230));
        jL_pname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_pname.setText("Process Name");
        jL_pname.setToolTipText("");
        jL_pname.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_pname.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_pname.setMaximumSize(new java.awt.Dimension(230, 24));
        jL_pname.setMinimumSize(new java.awt.Dimension(230, 24));
        jL_pname.setOpaque(true);
        jL_pname.setPreferredSize(new java.awt.Dimension(230, 24));
        jL_pname.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_pname, gridBagConstraints);

        jTF_pname.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_pname.setToolTipText("");
        jTF_pname.setMinimumSize(new java.awt.Dimension(300, 24));
        jTF_pname.setPreferredSize(new java.awt.Dimension(300, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jTF_pname, gridBagConstraints);

        jL_ptype.setBackground(new java.awt.Color(230, 230, 230));
        jL_ptype.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_ptype.setText("Process Type");
        jL_ptype.setToolTipText("");
        jL_ptype.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_ptype.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_ptype.setMaximumSize(new java.awt.Dimension(240, 24));
        jL_ptype.setMinimumSize(new java.awt.Dimension(240, 24));
        jL_ptype.setOpaque(true);
        jL_ptype.setPreferredSize(new java.awt.Dimension(240, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_ptype, gridBagConstraints);

        jCBox_ptype.setMaximumRowCount(6);
        jCBox_ptype.setToolTipText("");
        jCBox_ptype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_ptypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jCBox_ptype, gridBagConstraints);

        jL_process_id.setBackground(new java.awt.Color(230, 230, 230));
        jL_process_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_process_id.setText("Process ID");
        jL_process_id.setToolTipText("");
        jL_process_id.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_process_id.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_process_id.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_process_id, gridBagConstraints);

        jTF_process_id.setEditable(false);
        jTF_process_id.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_process_id.setToolTipText("");
        jTF_process_id.setMinimumSize(new java.awt.Dimension(4, 24));
        jTF_process_id.setPreferredSize(new java.awt.Dimension(4, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jTF_process_id, gridBagConstraints);

        jL_sched_Ignore.setBackground(new java.awt.Color(230, 230, 230));
        jL_sched_Ignore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_sched_Ignore.setText("Schedule Ignore");
        jL_sched_Ignore.setToolTipText("");
        jL_sched_Ignore.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_sched_Ignore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_sched_Ignore.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_sched_Ignore.setOpaque(true);
        jL_sched_Ignore.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_sched_Ignore, gridBagConstraints);

        jP_schedF.setMinimumSize(new java.awt.Dimension(100, 24));
        jP_schedF.setName(""); // NOI18N
        jP_schedF.setPreferredSize(new java.awt.Dimension(100, 24));
        jP_schedF.setLayout(new java.awt.GridLayout(1, 0));

        bGroup_schedF.add(jTB_schedIgnore_off);
        jTB_schedIgnore_off.setSelected(true);
        jTB_schedIgnore_off.setText("NO");
        jTB_schedIgnore_off.setFocusPainted(false);
        jTB_schedIgnore_off.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_schedIgnore_off.setMaximumSize(new java.awt.Dimension(40, 22));
        jTB_schedIgnore_off.setMinimumSize(new java.awt.Dimension(40, 22));
        jTB_schedIgnore_off.setPreferredSize(new java.awt.Dimension(40, 22));
        jP_schedF.add(jTB_schedIgnore_off);

        bGroup_schedF.add(jTB_schedIgnore_on);
        jTB_schedIgnore_on.setText("YES");
        jTB_schedIgnore_on.setFocusPainted(false);
        jTB_schedIgnore_on.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_schedIgnore_on.setMaximumSize(new java.awt.Dimension(40, 22));
        jTB_schedIgnore_on.setMinimumSize(new java.awt.Dimension(40, 22));
        jTB_schedIgnore_on.setPreferredSize(new java.awt.Dimension(40, 22));
        jP_schedF.add(jTB_schedIgnore_on);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_edit.add(jP_schedF, gridBagConstraints);

        jL_waketime.setBackground(new java.awt.Color(230, 230, 230));
        jL_waketime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_waketime.setText("Wake Time");
        jL_waketime.setToolTipText("");
        jL_waketime.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_waketime.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_waketime.setMaximumSize(null);
        jL_waketime.setMinimumSize(new java.awt.Dimension(150, 24));
        jL_waketime.setName(""); // NOI18N
        jL_waketime.setOpaque(true);
        jL_waketime.setPreferredSize(new java.awt.Dimension(150, 24));
        jL_waketime.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_waketime, gridBagConstraints);

        jP_waketime.setMinimumSize(new java.awt.Dimension(116, 20));
        jP_waketime.setOpaque(false);
        jP_waketime.setPreferredSize(new java.awt.Dimension(100, 20));
        jP_waketime.setLayout(new java.awt.GridBagLayout());

        jB_increase_hh.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_increase_hh.setText("+");
        jB_increase_hh.setToolTipText("");
        jB_increase_hh.setFocusPainted(false);
        jB_increase_hh.setMargin(null);
        jB_increase_hh.setMaximumSize(new java.awt.Dimension(22, 22));
        jB_increase_hh.setMinimumSize(new java.awt.Dimension(22, 22));
        jB_increase_hh.setPreferredSize(new java.awt.Dimension(22, 22));
        jB_increase_hh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_increase_hhActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jP_waketime.add(jB_increase_hh, gridBagConstraints);

        jB_increase_mm.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_increase_mm.setText("+");
        jB_increase_mm.setFocusPainted(false);
        jB_increase_mm.setMargin(null);
        jB_increase_mm.setMaximumSize(new java.awt.Dimension(22, 22));
        jB_increase_mm.setMinimumSize(new java.awt.Dimension(22, 22));
        jB_increase_mm.setPreferredSize(new java.awt.Dimension(22, 22));
        jB_increase_mm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_increase_mmActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jP_waketime.add(jB_increase_mm, gridBagConstraints);

        jL_hh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_hh.setText("Hour");
        jL_hh.setMinimumSize(new java.awt.Dimension(60, 20));
        jL_hh.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_waketime.add(jL_hh, gridBagConstraints);

        jTF_hh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTF_hh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_hh.setText("00");
        jTF_hh.setToolTipText("");
        jTF_hh.setMinimumSize(new java.awt.Dimension(36, 22));
        jTF_hh.setPreferredSize(new java.awt.Dimension(36, 22));
        jTF_hh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTF_hhFocusLost(evt);
            }
        });
        jTF_hh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_hhKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jP_waketime.add(jTF_hh, gridBagConstraints);

        jL_mm.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_mm.setText("Minute");
        jL_mm.setMinimumSize(new java.awt.Dimension(60, 20));
        jL_mm.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_waketime.add(jL_mm, gridBagConstraints);

        jTF_mm.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTF_mm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_mm.setText("00");
        jTF_mm.setToolTipText("");
        jTF_mm.setMinimumSize(new java.awt.Dimension(36, 22));
        jTF_mm.setPreferredSize(new java.awt.Dimension(36, 22));
        jTF_mm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTF_mmFocusLost(evt);
            }
        });
        jTF_mm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_mmKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jP_waketime.add(jTF_mm, gridBagConstraints);

        jB_decrease_hh.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_decrease_hh.setText("-");
        jB_decrease_hh.setFocusPainted(false);
        jB_decrease_hh.setMargin(null);
        jB_decrease_hh.setMaximumSize(new java.awt.Dimension(22, 22));
        jB_decrease_hh.setMinimumSize(new java.awt.Dimension(22, 22));
        jB_decrease_hh.setPreferredSize(new java.awt.Dimension(22, 22));
        jB_decrease_hh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_decrease_hhActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jP_waketime.add(jB_decrease_hh, gridBagConstraints);

        jB_decrease_mm.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_decrease_mm.setText("-");
        jB_decrease_mm.setFocusPainted(false);
        jB_decrease_mm.setMargin(null);
        jB_decrease_mm.setMaximumSize(new java.awt.Dimension(22, 22));
        jB_decrease_mm.setMinimumSize(new java.awt.Dimension(22, 22));
        jB_decrease_mm.setPreferredSize(new java.awt.Dimension(22, 22));
        jB_decrease_mm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_decrease_mmActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jP_waketime.add(jB_decrease_mm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_edit.add(jP_waketime, gridBagConstraints);

        jL_start_cmd.setBackground(new java.awt.Color(230, 230, 230));
        jL_start_cmd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_start_cmd.setText("Start cmd");
        jL_start_cmd.setToolTipText("");
        jL_start_cmd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_start_cmd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_start_cmd.setMaximumSize(new java.awt.Dimension(300, 24));
        jL_start_cmd.setMinimumSize(new java.awt.Dimension(300, 24));
        jL_start_cmd.setOpaque(true);
        jL_start_cmd.setPreferredSize(new java.awt.Dimension(300, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_start_cmd, gridBagConstraints);

        jTF_start_cmd.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_start_cmd.setToolTipText("");
        jTF_start_cmd.setMinimumSize(new java.awt.Dimension(300, 24));
        jTF_start_cmd.setPreferredSize(new java.awt.Dimension(300, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 4, 1);
        jP_edit.add(jTF_start_cmd, gridBagConstraints);

        jL_log_output.setBackground(new java.awt.Color(230, 230, 230));
        jL_log_output.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_log_output.setText("Log Output");
        jL_log_output.setToolTipText("");
        jL_log_output.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_log_output.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_log_output.setMaximumSize(new java.awt.Dimension(240, 24));
        jL_log_output.setMinimumSize(new java.awt.Dimension(240, 24));
        jL_log_output.setOpaque(true);
        jL_log_output.setPreferredSize(new java.awt.Dimension(240, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_log_output, gridBagConstraints);

        jCBox_log_output.setMaximumRowCount(6);
        jCBox_log_output.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 4, 1);
        jP_edit.add(jCBox_log_output, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jP_south.add(jP_edit, gridBagConstraints);

        jPs_s.setBackground(new java.awt.Color(145, 181, 255));
        jPs_s.setMinimumSize(new java.awt.Dimension(505, 100));
        jPs_s.setPreferredSize(new java.awt.Dimension(595, 100));
        jPs_s.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jTB_add);
        jTB_add.setSelected(true);
        jTB_add.setText("ADD");
        jTB_add.setFocusPainted(false);
        jTB_add.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_add.setMaximumSize(new java.awt.Dimension(70, 70));
        jTB_add.setMinimumSize(new java.awt.Dimension(70, 70));
        jTB_add.setPreferredSize(new java.awt.Dimension(70, 70));
        jTB_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTB_addMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPs_s.add(jTB_add, gridBagConstraints);

        buttonGroup1.add(jTB_mod);
        jTB_mod.setText("MODIFY");
        jTB_mod.setFocusPainted(false);
        jTB_mod.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_mod.setMaximumSize(new java.awt.Dimension(70, 70));
        jTB_mod.setMinimumSize(new java.awt.Dimension(70, 70));
        jTB_mod.setPreferredSize(new java.awt.Dimension(70, 70));
        jTB_mod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTB_modMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPs_s.add(jTB_mod, gridBagConstraints);

        buttonGroup1.add(jTB_del);
        jTB_del.setText("DELETE");
        jTB_del.setFocusPainted(false);
        jTB_del.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_del.setMaximumSize(new java.awt.Dimension(70, 70));
        jTB_del.setMinimumSize(new java.awt.Dimension(70, 70));
        jTB_del.setPreferredSize(new java.awt.Dimension(70, 70));
        jTB_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTB_delMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPs_s.add(jTB_del, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPs_s.add(jSeparator2, gridBagConstraints);

        jB_cancel.setText("CANCEL");
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setMaximumSize(new java.awt.Dimension(70, 70));
        jB_cancel.setMinimumSize(new java.awt.Dimension(70, 70));
        jB_cancel.setPreferredSize(new java.awt.Dimension(70, 70));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPs_s.add(jB_cancel, gridBagConstraints);

        jB_apply.setText("APPLY");
        jB_apply.setFocusPainted(false);
        jB_apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_apply.setMaximumSize(new java.awt.Dimension(70, 70));
        jB_apply.setMinimumSize(new java.awt.Dimension(70, 70));
        jB_apply.setPreferredSize(new java.awt.Dimension(70, 70));
        jB_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_applyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPs_s.add(jB_apply, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jP_south.add(jPs_s, gridBagConstraints);

        add(jP_south, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jTF_hhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_hhKeyReleased
        String str_hh = jTF_hh.getText().trim();
        
        if(str_hh.isEmpty() == false)
        {
            try
            {
                int hour = new  Integer(str_hh).intValue();
                if (hour > 23)
                    jTF_hh.setText("23");            
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
                
    }//GEN-LAST:event_jTF_hhKeyReleased

    private void jTF_mmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_mmKeyReleased
        String str_mm = jTF_mm.getText().trim();
        
        if(str_mm.isEmpty() == false)
        {
            try
            {
                int mm = new  Integer(str_mm).intValue();
                if (mm > 59)
                  jTF_mm.setText("59");            
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTF_mmKeyReleased

    private void jTF_hhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_hhFocusLost
        String str_hh = jTF_hh.getText().trim();
        
        int lenght = str_hh.length();
        if( (lenght < 2) && (lenght > 0) )
            jTF_hh.setText("0"+str_hh);
    }//GEN-LAST:event_jTF_hhFocusLost

    private void jTF_mmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_mmFocusLost
        String str_mm = jTF_mm.getText().trim();
        
        int lenght = str_mm.length();
        if( (lenght < 2)&& (lenght > 0) )
            jTF_mm.setText("0"+str_mm);
    }//GEN-LAST:event_jTF_mmFocusLost

    private void jTB_addMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB_addMouseReleased
        if(jTB_add.isSelected())
        {        
            jTable_process.setSelectionBackground(java.awt.Color.WHITE);
            jTable_process.clearSelection();
            setSelected_jTableRow();
        }
    }//GEN-LAST:event_jTB_addMouseReleased

    private void jTB_modMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB_modMouseReleased
       if(jTB_mod.isSelected())
       {  
            jTable_process.setEnabled(true);
            jTable_process.setSelectionBackground(java.awt.Color.YELLOW);
            setSelected_jTableRow();
       }
    }//GEN-LAST:event_jTB_modMouseReleased

    private void jTB_delMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB_delMouseReleased
       if(jTB_del.isSelected())
       {
            jTable_process.setEnabled(true);
            jTable_process.setSelectionBackground(java.awt.Color.RED);
            setSelected_jTableRow();
       }
    }//GEN-LAST:event_jTB_delMouseReleased

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
              
       jTable_process.clearSelection();
       setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void setProcessActivate(int process_id, int Operation)
    {                
        if(Operation <= 0) // ADD and DELETE
        {
            V_PROCESS_ID_ACTIVATE.addElement(new PROCESS_ID_ACTIVATE(process_id,Operation));
            System.out.println("ADD or DELETE NEW -->"+process_id);
            return;
        }

        for(int i=0; i<V_PROCESS_ID_ACTIVATE.size(); i++)
        {                
            if( process_id == ((PROCESS_ID_ACTIVATE)V_PROCESS_ID_ACTIVATE.elementAt(i)).getProcess_id() )
            {
                //((PROCESS_ID_ACTIVATE)V_PROCESS_ID_ACTIVATE.elementAt(i)).setOperation(Operation);
                return;
            }
        }        
        V_PROCESS_ID_ACTIVATE.addElement(new PROCESS_ID_ACTIVATE(process_id,Operation));
        
    }
             
    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
        
        this.setCursor(Cur_wait);
        
        System.out.println("jB_applyActionPerformed");
        
        if(Selected_row_TAB_T_PROCESS == null)
        {
            System.out.println("You have to select an item first.");
            this.setCursor(Cur_default);
            return;
        }
        
        if(jTB_del.isSelected())
        {
            //JOptionPane op_YES_NO = new JOptionPane();  
            //int YES_NO = op_YES_NO.showConfirmDialog(null,"About to delete Process Name: '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' (#"+Selected_row_TAB_T_PROCESS.getProcess_id()+"). Confirm ?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);     
            
            JD_Message op_YES_NO = null;
            op_YES_NO = new JD_Message(frame_parent,true,"About to delete Process Name: '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' (#"+Selected_row_TAB_T_PROCESS.getProcess_id()+"). Confirm ?","Question Messagge",op_YES_NO.QUESTION_YES_NO);
            
            if(op_YES_NO.YES_NO == 1)           
            {
                int answer_del = Selected_row_TAB_T_PROCESS.delete_process();
                if(answer_del >= 0)
                {
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Process Name: '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' (#"+Selected_row_TAB_T_PROCESS.getProcess_id()+") has been deleted.","DELETE PROCESS",JOptionPane.INFORMATION_MESSAGE);
                    
                    setProcessActivate(Selected_row_TAB_T_PROCESS.getProcess_id(),-1); //DELETE
                    
                    addRowJTable();
                    setSelected_jTableRow();
                    setEnabledClean_AllGui(false,true);
                }
                else
                {
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation failed.","DELETE PROCESS",JOptionPane.ERROR_MESSAGE);
                    //JD_Message(javax.swing.JFrame parent, boolean modal, String message, String title, int Value_type)
                    
                    JD_Message message = null;
                    message = new JD_Message(frame_parent,true,"Operation failed.","DELETE PROCESS",message.CRITICAL);
                }
            }
        }
        else
        {
            String str_title_msg ="MODIFY PROCESS";
            if(jTB_add.isSelected())
                str_title_msg ="ADD PROCESS";
            
            String pname = jTF_pname.getText().trim();
            if(pname.isEmpty())
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Process name is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                JD_Message message = null;
                message = new JD_Message(frame_parent,true,"Process name is empty.",str_title_msg,message.INFO);
                
                jTF_pname.requestFocus();
                this.setCursor(Cur_default);
                return;
            }             
            String s_cmd = jTF_start_cmd.getText().trim();
            if(s_cmd.isEmpty())
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Start Cmd is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                JD_Message message = null;
                message = new JD_Message(frame_parent,true,"Start Cmd is empty.",str_title_msg,message.INFO);
                
                jTF_start_cmd.requestFocus();
                this.setCursor(Cur_default);
                return;
            }        
                     
            if(jTB_add.isSelected())
            {
                
                //*************
                Selected_row_TAB_T_PROCESS.setProcess_name(pname);
                Selected_row_TAB_T_PROCESS.setProcess_type(jCBox_ptype.getSelectedIndex());
                Selected_row_TAB_T_PROCESS.setLog_output((String)jCBox_log_output.getSelectedItem());                

                int scheduleIgnore = 0;
                if (jTB_schedIgnore_on.isSelected())
                    scheduleIgnore = 1;
                Selected_row_TAB_T_PROCESS.setSchedule_ignore(scheduleIgnore);

                String hh = jTF_hh.getText();
                String mm = jTF_mm.getText();
                if( (hh.isEmpty()) && (mm.isEmpty()) )
                    Selected_row_TAB_T_PROCESS.setWake_time(null);
                else
                {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.getInstance();
                    calendar.set(calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH, new Integer(hh).intValue(), new Integer(mm).intValue());     
                    Selected_row_TAB_T_PROCESS.setWake_time(calendar);
                }       
                Selected_row_TAB_T_PROCESS.setStart_cmd(jTF_start_cmd.getText().trim());   
                //*************
                
                if(V_TAB_T_PROCESS.getProcess_id(pname) == -1)
                {                       
                    int Answer_Insert = Selected_row_TAB_T_PROCESS.insert_Process();
                    if(Answer_Insert == 1)
                    {
                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Process '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' has been added.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);

                        addRowJTable();
                        jTable_process.setSelectionBackground(java.awt.Color.GREEN.darker());

                        //seleziona riga JTable
                        int ID_MAX = 0;
                        int indexSelected = 0; // == id_PROCESS
                        for(int i=0; i<jTable_process.getRowCount(); i++)
                        {
                            int ID = new Integer(""+jTable_process.getValueAt(i,0)).intValue();
                            if(ID > ID_MAX)
                            {
                                ID_MAX = ID;
                                indexSelected = i;
                            }
                        }
                        
                        /////////////
                        int process_id_selected = new Integer(""+jTable_process.getValueAt(indexSelected,0)).intValue();
                        setProcessActivate(process_id_selected,0); //ADD
                        /////////////
                        
                        try
                        {   
                            jTable_process.setRowSelectionInterval(0,indexSelected);
                            jScrollPane1.validate();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                        setSelected_jTableRow();                    
                    }
                    else
                    {
                        addRowJTable();   
                        setSelected_jTableRow();
                        System.out.println("Cannot insert process");

                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Cannot insert Process '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"'.",str_title_msg,JOptionPane.ERROR_MESSAGE);
                         
                        JD_Message message = null;
                        message = new JD_Message(frame_parent,true,"Cannot insert Process '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"'.",str_title_msg,message.CRITICAL);

                    }
                }                 
                else
                {   
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation Failed, \nthe name of '"+pname+"' Process exists.","Add group",JOptionPane.WARNING_MESSAGE);
                    
                    JD_Message message = null;
                    message = new JD_Message(frame_parent,true,"Operation Failed, \nthe name of '"+pname+"' Process exists.","Add group",message.WARNING);
                }
            
            }
            else //jTB_mod.isSelected()
            {
                
                int proc_ID = V_TAB_T_PROCESS.getProcess_id(pname);           

                if( ( proc_ID == -1) || ( proc_ID == Selected_row_TAB_T_PROCESS.getProcess_id()) )
                {               
                    //JOptionPane op_YES_NO = new JOptionPane();
                    //int YES_NO = op_YES_NO.showConfirmDialog(null,"About to change Process Name: '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' (#"+Selected_row_TAB_T_PROCESS.getProcess_id()+"). Confirm ?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    
                    JD_Message op_YES_NO = null;
                    op_YES_NO = new JD_Message(frame_parent,true, "About to change Process Name: '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' (#"+Selected_row_TAB_T_PROCESS.getProcess_id()+"). Confirm ?","Question Messagge",op_YES_NO.QUESTION_YES_NO);
            
                    if(op_YES_NO.YES_NO == 1)
                    {
                        //*************
                        Selected_row_TAB_T_PROCESS.setProcess_name(pname);
                        Selected_row_TAB_T_PROCESS.setProcess_type(jCBox_ptype.getSelectedIndex());
                        Selected_row_TAB_T_PROCESS.setLog_output((String)jCBox_log_output.getSelectedItem());

                        int scheduleIgnore = 0;
                        if (jTB_schedIgnore_on.isSelected())
                            scheduleIgnore = 1;
                        Selected_row_TAB_T_PROCESS.setSchedule_ignore(scheduleIgnore);

                        String hh = jTF_hh.getText();
                        String mm = jTF_mm.getText();
                        if( (hh.isEmpty()) && (mm.isEmpty()) )
                            Selected_row_TAB_T_PROCESS.setWake_time(null);
                        else
                        {
                            GregorianCalendar calendar = new GregorianCalendar();
                            calendar.getInstance();
                            calendar.set(calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH, new Integer(hh).intValue(), new Integer(mm).intValue());     
                            Selected_row_TAB_T_PROCESS.setWake_time(calendar);
                        }       
                        Selected_row_TAB_T_PROCESS.setStart_cmd(jTF_start_cmd.getText().trim());   
                        //*************
                            
                        int answer_up = Selected_row_TAB_T_PROCESS.update_Process();

                        if(answer_up >= 0)
                        {
                            //JOptionPane op = new JOptionPane();
                            //op.showMessageDialog(null,"Process '"+Selected_row_TAB_T_PROCESS.getProcess_name()+"' has been changed.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                            
                            setProcessActivate(Selected_row_TAB_T_PROCESS.getProcess_id(),1); // MODIFY
                            
                            addRowJTable();   
                            jTable_process.setEnabled(true);

                            try
                            {   //seleziona riga JTable
                                for(int i=0; i<jTable_process.getRowCount(); i++)
                                {
                                    int proc_id = new Integer(""+jTable_process.getValueAt(i,0)).intValue();
                                    if( proc_id == Selected_row_TAB_T_PROCESS.getProcess_id() )
                                    {
                                        jTable_process.setRowSelectionInterval(i,i);
                                        jScrollPane1.validate();
                                        break;
                                    }
                                }
                            }catch(Exception e)
                            { 
                                e.printStackTrace(); 
                            }
                        }
                        else
                        {
                            addRowJTable();
                            System.out.println("Cannot modify process");                        

                            //JOptionPane op = new JOptionPane();
                            //op.showMessageDialog(null,"Operation failed.",str_title_msg,JOptionPane.ERROR_MESSAGE);
                            
                            JD_Message message = null;
                            message = new JD_Message(frame_parent,true,"Operation failed.",str_title_msg,message.CRITICAL);
                        }
                    }
                }
                else
                {                    
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation Failed, the name of '"+pname+"' Process exists.","Modify group",JOptionPane.WARNING_MESSAGE);
                    
                    JD_Message message = null;
                    message = new JD_Message(frame_parent,true,"Operation Failed, the name of '"+pname+"' Process exists.","Modify group",message.WARNING);
                }  
            }           
        }
        setSelected_jTableRow();
        this.setCursor(Cur_default);
        
    }//GEN-LAST:event_jB_applyActionPerformed
    
    private void jB_increase_hhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_increase_hhActionPerformed
               
        String str_hh = jTF_hh.getText();        
        if(str_hh.isEmpty())
        {
            jTF_hh.setText("00");
            return;
        }
        
        int hh = new Integer(str_hh).intValue();        
        if(hh<23)
        {
            hh = hh +1;
            String zero = "0";
            if( hh>9 )
                zero = "";            
            jTF_hh.setText(zero+hh);
        }
        else if(hh == 23)
            jTF_hh.setText("00");
    }//GEN-LAST:event_jB_increase_hhActionPerformed

    private void jB_increase_mmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_increase_mmActionPerformed
         
        String str_mm = jTF_mm.getText();        
        if(str_mm.isEmpty())
        {
            jTF_mm.setText("00");
            return;
        }
            
        int mm = new Integer(str_mm).intValue();
        if(mm<59)
        {
            mm = mm +1;
            String zero = "0";
            if( mm>9 )
                zero = "";            
            jTF_mm.setText(zero+mm);
        }        
        else if(mm == 59)
            jTF_mm.setText("00");
    }//GEN-LAST:event_jB_increase_mmActionPerformed

    private void jB_decrease_hhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_decrease_hhActionPerformed
               
        String str_hh = jTF_hh.getText();        
        if(str_hh.isEmpty())
        {
            jTF_hh.setText("00");
            return;
        }
        
        int hh = new Integer(str_hh).intValue();
        if(hh>0)
        {
            hh = hh-1;
            String zero = "0";
            if( hh>9 )
                zero = "";            
            jTF_hh.setText(zero+hh);
        }
        if(hh == 0)
            jTF_hh.setText("23");
    }//GEN-LAST:event_jB_decrease_hhActionPerformed

    private void jB_decrease_mmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_decrease_mmActionPerformed
        
        String str_mm = jTF_mm.getText();        
        if(str_mm.isEmpty())
        {
            jTF_mm.setText("00");
            return;
        }
        
        int mm = new Integer(str_mm).intValue();
        if(mm>0)
        {
            mm = mm-1;
            String zero = "0";
            if( mm>9 )
                zero = "";
            jTF_mm.setText(zero+mm);
        }
        if(mm == 0)
            jTF_mm.setText("59");
    }//GEN-LAST:event_jB_decrease_mmActionPerformed

    private void setEnabled_GUI_WakeTime(boolean flag)
    {
        if(flag == false)
        {
            jTF_hh.setText("00");
            jTF_mm.setText("00");
        }

        jTF_hh.setEnabled(flag);
        jTF_mm.setEnabled(flag);
        jB_decrease_hh.setEnabled(flag);
        jB_decrease_mm.setEnabled(flag);
        jB_increase_hh.setEnabled(flag);
        jB_increase_mm.setEnabled(flag);

    }
    
    private void jCBox_ptypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_ptypeActionPerformed

        if (jCBox_ptype.getSelectedIndex() == ABILITY_WAKE_TIME)
            setEnabled_GUI_WakeTime(true);
        else
            setEnabled_GUI_WakeTime(false);      
    }//GEN-LAST:event_jCBox_ptypeActionPerformed

    private void jTable_processMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jTable_process.isEnabled())
        {
            System.out.println("jTable_TEMouseReleased");
            if(jTB_add.isSelected() == false)
                setSelected_jTableRow();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGroup_activeF;
    private javax.swing.ButtonGroup bGroup_schedF;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JButton jB_decrease_hh;
    private javax.swing.JButton jB_decrease_mm;
    private javax.swing.JButton jB_increase_hh;
    private javax.swing.JButton jB_increase_mm;
    private javax.swing.JComboBox jCBox_log_output;
    private javax.swing.JComboBox jCBox_ptype;
    private javax.swing.JLabel jL_hh;
    private javax.swing.JLabel jL_log_output;
    private javax.swing.JLabel jL_mm;
    private javax.swing.JLabel jL_pname;
    private javax.swing.JLabel jL_process_id;
    private javax.swing.JLabel jL_ptype;
    private javax.swing.JLabel jL_sched_Ignore;
    private javax.swing.JLabel jL_start_cmd;
    private javax.swing.JLabel jL_waketime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_edit;
    private javax.swing.JPanel jP_schedF;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jP_waketime;
    private javax.swing.JPanel jPs_s;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToggleButton jTB_add;
    private javax.swing.JToggleButton jTB_del;
    private javax.swing.JToggleButton jTB_mod;
    private javax.swing.JToggleButton jTB_schedIgnore_off;
    private javax.swing.JToggleButton jTB_schedIgnore_on;
    private javax.swing.JTextField jTF_hh;
    private javax.swing.JTextField jTF_mm;
    private javax.swing.JTextField jTF_pname;
    private javax.swing.JTextField jTF_process_id;
    private javax.swing.JTextField jTF_start_cmd;
    // End of variables declaration//GEN-END:variables


    
     //////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private boolean DEBUG = false;
        private String[] columnNames ={ "Process Id",
                                        "Process Name",
                                        "Process Type",
                                        "Start Cmd",
                                        "Log Output",
                                        "Wake Time",
                                        "Schedule Ignore"};
                
        private Object[][] data = {};   
        
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
    
    private class PROCESS_ID_ACTIVATE
    {
        /**
         * @param process_id the process_id to set.
         * @param operation the operation to set. 
         * operation == 0   // ADD
         * operation == 1   // MODIFY
         * operation == -1  // DELETE
         */
        PROCESS_ID_ACTIVATE(int idProc,int op)
        {
            process_id = idProc;
            operation = op;
        }
        
        /**
         * @return the operation
         * operation == 0   // ADD
         * operation == 1   // MODIFY
         * operation == -1  // DELETE
         */
        public int getOperation() {
            return operation;
        }

         /**
         * operation == 0   // ADD
         * operation == 1   // MODIFY
         * operation == -1  // DELETE
         */
        public void setOperation(int operation) {
            this.operation = operation;
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
         * operation == 0   // ADD
         * operation == 1   // MODIFY
         * operation == -1  // DELETE
         */
        private int operation; 
        
        /** Process ID */
        private int process_id;
       
    }
    
    private Vector V_PROCESS_ID_ACTIVATE = new Vector();
     
    private javax.swing.JTable jTable_process;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private int[] CellDimension = {35,100,100,320,100,40,50};
    private int minCellDimension = 35;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private SSM_Vector_TAB_T_PROCESS V_TAB_T_PROCESS = new SSM_Vector_TAB_T_PROCESS();
    
    private  String[] str_process_type = {"Process always running","Awake five minutes process","Awake fifteen minutes process","Awake one hour process","Awake one day process","Awake hour daily process","Privileged always running","Undefined"};
    private final int ABILITY_WAKE_TIME = 5; // index str_process_type
    
    //private String[] str_log_level = {"level 0","level 1","level 2","level 3"};
    private String[] str_log_output = {"Table","File","Pipe"};
    
    private SSM_TAB_T_PROCESS Selected_row_TAB_T_PROCESS;
    
    private javax.swing.JFrame frame_parent = null;
}
