
import java.awt.Cursor;
import java.util.Vector;
//import javax.swing.JOptionPane;
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

public class SSM_JP_NodeConfiguration extends javax.swing.JPanel {

    /**
     * Creates new form SSM_JP_NodeConfiguration
     */
    public SSM_JP_NodeConfiguration(javax.swing.JFrame parent) {    
        
        this.frame_parent = parent;
        
        initComponents();
        
        jTB_add.setCursor(Cur_hand);
        jTB_mod.setCursor(Cur_hand);
        jTB_del.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_apply.setCursor(Cur_hand);
        jCBox_process_group.setCursor(Cur_hand);
        jTB_node_active.setCursor(Cur_hand);
        jTB_node_inactive.setCursor(Cur_hand);
        
        jTF_node_name.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        jTF_node_desc.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,128));
        jTF_port_min.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,5));
        jTF_port_max.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,5));
        
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_node = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_node.getTableHeader();
        header.setFont(SSM_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_node.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_node.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_node.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_node.setRowHeight(20);
        //jTable_node.setSelectionBackground(java.awt.Color.green);
        //jTable_node.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_node.setRowMargin(2);
        jTable_node.setAutoCreateColumnsFromModel(false);
        jTable_node.setCursor(Cur_wait);
        jScrollPane1.setViewportView(jTable_node);
        
        jTable_node.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_nodeMouseReleased(evt);
            }
        });
        
        //*********************
        V_TAB_PROCESS_GROUPS = new SSM_Vector_TAB_PROCESS_GROUPS();
        loadProcessGroups();

        jTB_add.setSelected(true);
        
        setTable(jTable_node);
        addRowJTable();
        setSelected_jTableRow();
        
        // **************************** //
        
    }
    
    private void loadProcessGroups()
    {
        //*********************         
         V_TAB_PROCESS_GROUPS.readPreocessGroups();         
         jCBox_process_group.removeAllItems();         
         jCBox_process_group.addItem(str_undefined);
         for(int i=0; i<V_TAB_PROCESS_GROUPS.size(); i++)
         {
             String str_group_name = ((SSM_TAB_PROCESS_GROUPS)V_TAB_PROCESS_GROUPS.elementAt(i)).getGroup_name();
             jCBox_process_group.addItem(str_group_name);
         }
        //*********************
    }
    
    public void loadProcessGroups(SSM_Vector_TAB_PROCESS_GROUPS v_Process_groups)
    {
        //*********************
         V_TAB_PROCESS_GROUPS = v_Process_groups;
         jCBox_process_group.removeAllItems();         
         jCBox_process_group.addItem(str_undefined);
         for(int i=0; i<v_Process_groups.size(); i++)
         {
             String str_group_name = ((SSM_TAB_PROCESS_GROUPS)v_Process_groups.elementAt(i)).getGroup_name();
             jCBox_process_group.addItem(str_group_name);
         }
        //*********************
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
        jTable_node.setCursor(Cur_wait);
        
        V_TAB_T_NODE = new SSM_Vector_TAB_T_NODE();
        boolean ok_read = V_TAB_T_NODE.readNodes();
        
        
        int SIZE = V_TAB_T_NODE.size();
        Object[][] dataValues = new Object[SIZE][myTableModel.getColumnCount()];       
        
        System.out.println("#NODES --> "+SIZE);
        for(int i=0; i<SIZE; i++)
        {
            SSM_TAB_T_NODE row_t_node = (SSM_TAB_T_NODE)V_TAB_T_NODE.elementAt(i);

            dataValues[i][0] = row_t_node.getNode_id();            
            dataValues[i][1] = row_t_node.getName();
            
            String group_name = V_TAB_PROCESS_GROUPS.getGroup_name(row_t_node.getProcess_group_id());
            dataValues[i][2] = group_name;
            
           
            dataValues[i][3] = row_t_node.getPort_range_min();          
            dataValues[i][4] = row_t_node.getPort_range_max();
            
            String str_STATUS= "INACTIVE";          
            if(row_t_node.getActive() == 1)
                str_STATUS = "ACTIVE";          
            dataValues[i][5] = str_STATUS;          
        }
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);

        jScrollPane1.setViewportView(jTable_node);
        jTable_node.setCursor(Cur_default);
        
    }
    
    private void setSelected_jTableRow()
    {
        this.setCursor(Cur_wait);
        int selectedRow = jTable_node.getSelectedRow();

        if( (jTB_mod.isSelected()) || (jTB_del.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                boolean ctrl_row = false;
                int node_id_selected = new Integer(""+jTable_node.getValueAt(selectedRow,0)).intValue();
                System.out.println("node_id_selected => "+node_id_selected);

                for(int i=0; i<V_TAB_T_NODE.size(); i++) 
                {
                    Selected_row_TAB_T_Node = (SSM_TAB_T_NODE)V_TAB_T_NODE.elementAt(i);      
                    if(Selected_row_TAB_T_Node.getNode_id() == node_id_selected)
                    {
                        ctrl_row = true;
                        break;
                    }
                }
                if(ctrl_row == false)
                {
                    Selected_row_TAB_T_Node = null;
                    System.out.println("----- ERRORE Selected_row_node...");
                    this.setCursor(Cur_default);
                    return;
                }

                jTF_node_id.setText(""+Selected_row_TAB_T_Node.getNode_id());
                jTF_node_name.setText(Selected_row_TAB_T_Node.getName());
                
                String group_name = V_TAB_PROCESS_GROUPS.getGroup_name(Selected_row_TAB_T_Node.getProcess_group_id());
                if(group_name != null)
                    jCBox_process_group.setSelectedItem(group_name);
                else
                    jCBox_process_group.setSelectedIndex(0);
                                        
                jTF_node_desc.setText(Selected_row_TAB_T_Node.getDescription());
                jTF_node_location.setText(Selected_row_TAB_T_Node.getLocation());

                jTF_port_min.setText(""+Selected_row_TAB_T_Node.getPort_range_min());
                jTF_port_max.setText(""+Selected_row_TAB_T_Node.getPort_range_max());
                        

                if(Selected_row_TAB_T_Node.getActive() == 1)
                {
                    jTB_node_active.setSelected(true);
                    jTB_node_inactive.setSelected(false);
                }
                else
                {
                    jTB_node_active.setSelected(false);
                    jTB_node_inactive.setSelected(true);
                }

            }
            else
            {   
                Selected_row_TAB_T_Node = null;                
                setEnabledClean_AllGui(false,true);                
                jTable_node.setEnabled(true);
                this.setCursor(Cur_default);
                return;
            }
            
            if(jTB_mod.isSelected())
            {               
                setEnabledClean_AllGui(true,false);
            }
            else //jTB_del.isSelected()
                setEnabledClean_AllGui(false,false);
            
        }
        else //jTB_add.isSelected())
        {
            jTable_node.setEnabled(false);
            setEnabledClean_AllGui(true,true);
            Selected_row_TAB_T_Node = new SSM_TAB_T_NODE();            
        }
        this.setCursor(Cur_default);
    }
    
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {
        jTF_node_desc.setEnabled(enable);
        jTF_node_id.setEnabled(enable);
        jTF_node_location.setEnabled(enable);
        jTF_node_name.setEnabled(enable);
        jTF_port_max.setEnabled(enable);
        jTF_port_min.setEnabled(enable);
        
        jCBox_process_group.setEnabled(enable);
        jTB_node_active.setEnabled(enable);
        jTB_node_inactive.setEnabled(enable);     
        
        if(clean)
        {            
            jTF_node_desc.setText("");
            jTF_node_id.setText("");
            jTF_node_location.setText("");
            jTF_node_name.setText("");
            
            if(jTB_add.isSelected())
            {
                jTF_port_min.setText("1024");
                jTF_port_max.setText("65535");
            }
            else
            {
                jTF_port_max.setText("");
                jTF_port_min.setText("");
            }

            jCBox_process_group.setSelectedIndex(0);
        }       
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jP_edit = new javax.swing.JPanel();
        jL_node_id = new javax.swing.JLabel();
        jTF_node_id = new javax.swing.JTextField();
        jL_node_name = new javax.swing.JLabel();
        jTF_node_name = new javax.swing.JTextField();
        jL_process_group = new javax.swing.JLabel();
        jCBox_process_group = new javax.swing.JComboBox();
        jL_node_desc = new javax.swing.JLabel();
        jTF_node_desc = new javax.swing.JTextField();
        jL_node_location = new javax.swing.JLabel();
        jTF_node_location = new javax.swing.JTextField();
        jL_node_status = new javax.swing.JLabel();
        jP_node_status = new javax.swing.JPanel();
        jTB_node_inactive = new javax.swing.JToggleButton();
        jTB_node_active = new javax.swing.JToggleButton();
        jP_port = new javax.swing.JPanel();
        jL_port_min = new javax.swing.JLabel();
        jTF_port_min = new javax.swing.JTextField();
        jL_port_max = new javax.swing.JLabel();
        jTF_port_max = new javax.swing.JTextField();
        jPs_s = new javax.swing.JPanel();
        jTB_add = new javax.swing.JToggleButton();
        jTB_mod = new javax.swing.JToggleButton();
        jTB_del = new javax.swing.JToggleButton();
        jB_cancel = new javax.swing.JButton();
        jB_apply = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setName("[400, 300]"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(145, 181, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Node Configuration");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 60));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 60));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 60));
        add(jLabel1, java.awt.BorderLayout.PAGE_START);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jP_south.setBackground(new java.awt.Color(145, 181, 255));
        jP_south.setMinimumSize(new java.awt.Dimension(400, 300));
        jP_south.setPreferredSize(new java.awt.Dimension(400, 300));
        jP_south.setLayout(new java.awt.GridBagLayout());

        jP_edit.setBackground(new java.awt.Color(145, 181, 255));
        jP_edit.setPreferredSize(new java.awt.Dimension(400, 400));
        jP_edit.setLayout(new java.awt.GridBagLayout());

        jL_node_id.setBackground(new java.awt.Color(230, 230, 230));
        jL_node_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_node_id.setText("Node ID");
        jL_node_id.setToolTipText("");
        jL_node_id.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_node_id.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_node_id.setMaximumSize(new java.awt.Dimension(60, 24));
        jL_node_id.setMinimumSize(new java.awt.Dimension(60, 24));
        jL_node_id.setOpaque(true);
        jL_node_id.setPreferredSize(new java.awt.Dimension(60, 24));
        jL_node_id.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_node_id, gridBagConstraints);

        jTF_node_id.setEditable(false);
        jTF_node_id.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_node_id.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jTF_node_id, gridBagConstraints);

        jL_node_name.setBackground(new java.awt.Color(230, 230, 230));
        jL_node_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_node_name.setText("Node Name");
        jL_node_name.setToolTipText("");
        jL_node_name.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_node_name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_node_name.setMaximumSize(new java.awt.Dimension(180, 24));
        jL_node_name.setMinimumSize(new java.awt.Dimension(180, 24));
        jL_node_name.setOpaque(true);
        jL_node_name.setPreferredSize(new java.awt.Dimension(180, 24));
        jL_node_name.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_node_name, gridBagConstraints);

        jTF_node_name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_node_name.setToolTipText("");
        jTF_node_name.setMinimumSize(new java.awt.Dimension(60, 24));
        jTF_node_name.setPreferredSize(new java.awt.Dimension(60, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jTF_node_name, gridBagConstraints);

        jL_process_group.setBackground(new java.awt.Color(230, 230, 230));
        jL_process_group.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_process_group.setText("Process Group");
        jL_process_group.setToolTipText("");
        jL_process_group.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_process_group.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_process_group.setMaximumSize(new java.awt.Dimension(240, 24));
        jL_process_group.setMinimumSize(new java.awt.Dimension(240, 24));
        jL_process_group.setOpaque(true);
        jL_process_group.setPreferredSize(new java.awt.Dimension(240, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_process_group, gridBagConstraints);

        jCBox_process_group.setMaximumRowCount(6);
        jCBox_process_group.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jCBox_process_group, gridBagConstraints);

        jL_node_desc.setBackground(new java.awt.Color(230, 230, 230));
        jL_node_desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_node_desc.setText("Node Description");
        jL_node_desc.setToolTipText("");
        jL_node_desc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_node_desc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_node_desc.setMaximumSize(new java.awt.Dimension(200, 24));
        jL_node_desc.setMinimumSize(new java.awt.Dimension(200, 24));
        jL_node_desc.setOpaque(true);
        jL_node_desc.setPreferredSize(new java.awt.Dimension(200, 24));
        jL_node_desc.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_node_desc, gridBagConstraints);

        jTF_node_desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_node_desc.setToolTipText("");
        jTF_node_desc.setMinimumSize(new java.awt.Dimension(300, 24));
        jTF_node_desc.setPreferredSize(new java.awt.Dimension(300, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jTF_node_desc, gridBagConstraints);

        jL_node_location.setBackground(new java.awt.Color(230, 230, 230));
        jL_node_location.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_node_location.setText("Node Location");
        jL_node_location.setToolTipText("");
        jL_node_location.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_node_location.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_node_location.setMaximumSize(new java.awt.Dimension(440, 24));
        jL_node_location.setMinimumSize(new java.awt.Dimension(440, 24));
        jL_node_location.setOpaque(true);
        jL_node_location.setPreferredSize(new java.awt.Dimension(440, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_node_location, gridBagConstraints);

        jTF_node_location.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_node_location.setToolTipText("");
        jTF_node_location.setMinimumSize(new java.awt.Dimension(4, 24));
        jTF_node_location.setPreferredSize(new java.awt.Dimension(4, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 3, 1);
        jP_edit.add(jTF_node_location, gridBagConstraints);

        jL_node_status.setBackground(new java.awt.Color(230, 230, 230));
        jL_node_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_node_status.setText("Node Status");
        jL_node_status.setToolTipText("");
        jL_node_status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_node_status.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_node_status.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_node_status.setOpaque(true);
        jL_node_status.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_edit.add(jL_node_status, gridBagConstraints);

        jP_node_status.setMinimumSize(new java.awt.Dimension(100, 24));
        jP_node_status.setName(""); // NOI18N
        jP_node_status.setPreferredSize(new java.awt.Dimension(100, 24));
        jP_node_status.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroup1.add(jTB_node_inactive);
        jTB_node_inactive.setSelected(true);
        jTB_node_inactive.setText("INACTIVE");
        jTB_node_inactive.setFocusPainted(false);
        jTB_node_inactive.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_node_inactive.setMaximumSize(new java.awt.Dimension(40, 22));
        jTB_node_inactive.setMinimumSize(new java.awt.Dimension(40, 22));
        jTB_node_inactive.setPreferredSize(new java.awt.Dimension(40, 22));
        jP_node_status.add(jTB_node_inactive);

        buttonGroup1.add(jTB_node_active);
        jTB_node_active.setText("ACTIVE");
        jTB_node_active.setFocusPainted(false);
        jTB_node_active.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_node_active.setMaximumSize(new java.awt.Dimension(40, 22));
        jTB_node_active.setMinimumSize(new java.awt.Dimension(40, 22));
        jTB_node_active.setPreferredSize(new java.awt.Dimension(40, 22));
        jP_node_status.add(jTB_node_active);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_edit.add(jP_node_status, gridBagConstraints);

        jP_port.setBackground(new java.awt.Color(145, 181, 255));
        jP_port.setLayout(new java.awt.GridBagLayout());

        jL_port_min.setBackground(new java.awt.Color(230, 230, 230));
        jL_port_min.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_port_min.setText("Port Range Min");
        jL_port_min.setToolTipText("");
        jL_port_min.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_port_min.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_port_min.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_port_min.setOpaque(true);
        jL_port_min.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_port.add(jL_port_min, gridBagConstraints);

        jTF_port_min.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_port_min.setToolTipText("");
        jTF_port_min.setMinimumSize(new java.awt.Dimension(60, 24));
        jTF_port_min.setPreferredSize(new java.awt.Dimension(60, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_port.add(jTF_port_min, gridBagConstraints);

        jL_port_max.setBackground(new java.awt.Color(230, 230, 230));
        jL_port_max.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_port_max.setText("Port Range Max");
        jL_port_max.setToolTipText("");
        jL_port_max.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_port_max.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_port_max.setMaximumSize(null);
        jL_port_max.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_port_max.setOpaque(true);
        jL_port_max.setPreferredSize(new java.awt.Dimension(100, 24));
        jL_port_max.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_port.add(jL_port_max, gridBagConstraints);

        jTF_port_max.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_port_max.setToolTipText("");
        jTF_port_max.setMaximumSize(null);
        jTF_port_max.setMinimumSize(null);
        jTF_port_max.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jP_port.add(jTF_port_max, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        jP_edit.add(jP_port, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jP_south.add(jP_edit, gridBagConstraints);

        jPs_s.setBackground(new java.awt.Color(145, 181, 255));
        jPs_s.setLayout(new java.awt.GridBagLayout());

        buttonGroup2.add(jTB_add);
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

        buttonGroup2.add(jTB_mod);
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

        buttonGroup2.add(jTB_del);
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
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPs_s.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jP_south.add(jPs_s, gridBagConstraints);

        add(jP_south, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_nodeMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jTable_node.isEnabled())
        {
            System.out.println("jTable_nodeMouseReleased");
            if(jTB_add.isSelected() == false)
                setSelected_jTableRow();
        }
    }
    
    private void jTB_addMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB_addMouseReleased
        if(jTB_add.isSelected())
        {                
            jTable_node.setSelectionBackground(java.awt.Color.WHITE);
            jTable_node.clearSelection();
            jTF_port_min.setText("1024");
            jTF_port_max.setText("65535");
            setSelected_jTableRow();
        }
    }//GEN-LAST:event_jTB_addMouseReleased

    private void jTB_modMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB_modMouseReleased
        if(jTB_mod.isSelected())
        {
            jTable_node.setEnabled(true);
            jTable_node.setSelectionBackground(java.awt.Color.YELLOW);
            setSelected_jTableRow();
        }
    }//GEN-LAST:event_jTB_modMouseReleased

    private void jTB_delMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTB_delMouseReleased
        if(jTB_del.isSelected())
        {
            jTable_node.setEnabled(true);
            jTable_node.setSelectionBackground(java.awt.Color.RED);
            setSelected_jTableRow();
        }
    }//GEN-LAST:event_jTB_delMouseReleased

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        jTable_node.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed

        this.setCursor(Cur_wait);
        if(Selected_row_TAB_T_Node == null)
        {
            System.out.println("You have to select an item first.");
            this.setCursor(Cur_default);
            return;
        }
        
        if(jTB_del.isSelected())
        {
            //JOptionPane op_YES_NO = new JOptionPane();            
            //int YES_NO = op_YES_NO.showConfirmDialog(null,"About to delete Node Name: '"+Selected_row_TAB_T_Node.getName()+"' (#"+Selected_row_TAB_T_Node.getNode_id()+"). Confirm ?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);     
            
            JD_Message op_YES_NO = null;
            op_YES_NO = new JD_Message(frame_parent,true,"About to delete Node Name: '"+Selected_row_TAB_T_Node.getName()+"' (#"+Selected_row_TAB_T_Node.getNode_id()+"). Confirm ?","Question Messagge",op_YES_NO.QUESTION_YES_NO);
            
            if(op_YES_NO.YES_NO == 1)        
            {
                int answer_del = Selected_row_TAB_T_Node.delete_node();
                if(answer_del >= 0)
                {
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Node Name: '"+Selected_row_TAB_T_Node.getName()+"' (#"+Selected_row_TAB_T_Node.getNode_id()+") has been deleted.","DELETE NODE",JOptionPane.INFORMATION_MESSAGE);
                    
                    addRowJTable();
                    setSelected_jTableRow();
                    setEnabledClean_AllGui(false,true);
                }
                else{
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation failed.","DELETE NODE",JOptionPane.ERROR_MESSAGE);
                    
                    JD_Message message = null;
                    message = new JD_Message(frame_parent,true,"Operation failed.","DELETE NODE",message.CRITICAL);
                }
            }
        }
        else
        {
            String str_title_msg ="MODIFY NODE";
            if(jTB_add.isSelected())
                str_title_msg ="ADD NODE";
            
            JD_Message message = null;                
            
            String node_name = jTF_node_name.getText().trim();
            if(node_name.isEmpty())
            {
                //OptionPane op = new JOptionPane();
                //p.showMessageDialog(null,"Node Name is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Node Name is empty.",str_title_msg,message.INFO);
                
                jTF_node_name.requestFocus();
                this.setCursor(Cur_default);
                return;
            }
            
            String node_description = jTF_node_desc.getText().trim();
            if(node_description.isEmpty())
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Node Description is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Node Description is empty.",str_title_msg,message.INFO);
                
                jTF_node_desc.requestFocus();
                this.setCursor(Cur_default);
                return;
            }             
            
            String node_location = jTF_node_location.getText().trim();
            if(node_location.isEmpty())
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Node Location is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Node Location is empty.",str_title_msg,message.INFO);
                
                jTF_node_location.requestFocus();
                this.setCursor(Cur_default);
                return;
            }
            String port_range_min = jTF_port_min.getText().trim();
            if(port_range_min.isEmpty())
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Port Range Min is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Port Range Min is empty.",str_title_msg,message.INFO);                
                
                jTF_port_min.requestFocus();
                this.setCursor(Cur_default);
                return;
            }
                        
            String port_range_max = jTF_port_max.getText().trim();
            if(port_range_max.isEmpty())
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Port Range Max is empty.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Port Range Max is empty.",str_title_msg,message.INFO);
                
                jTF_port_max.requestFocus();
                this.setCursor(Cur_default);
                return;
            }
            
            try
            {
                int range_min = new Integer(port_range_min).intValue();
                int range_max = new Integer(port_range_max).intValue();
                
                if((range_min < 1024)||(range_min > 65534))
                {
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Invalid port namber (range min 1024-65534)",str_title_msg,JOptionPane.WARNING_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Invalid port namber (range min 1024-65534).",str_title_msg,message.WARNING);
                    
                    jTF_port_min.requestFocus();
                    this.setCursor(Cur_default);
                    return;
                }
                        
                if((range_max < 1025)||(range_max > 65535))
                {
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Invalid port namber (range max 1025-65535)",str_title_msg,JOptionPane.WARNING_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Invalid port namber (range max 1025-65535).",str_title_msg,message.WARNING);
                    
                    jTF_port_max.requestFocus();
                    this.setCursor(Cur_default);
                    return;
                }
                
                if((range_min >= range_max))
                {
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Port Range Min greater Port Range Max.",str_title_msg,JOptionPane.WARNING_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Port Range Min greater Port Range Max.",str_title_msg,message.WARNING);
                    
                    jTF_port_max.requestFocus();
                    this.setCursor(Cur_default);
                    return;
                }
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            
            int group_id = V_TAB_PROCESS_GROUPS.getProcess_group_id((String)jCBox_process_group.getSelectedItem());
            if(group_id == -1)
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Is mandatory to select a group. Create new group if you need.","PROCESS GROUP",JOptionPane.INFORMATION_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Is mandatory to select a group. Create new group if you need.","PROCESS GROUP",message.INFO);
                
                jCBox_process_group.requestFocus();
                this.setCursor(Cur_default);
                return;
            }
            
                     
            if(jTB_add.isSelected())
            {
                // ***********
                Selected_row_TAB_T_Node.setName(node_name);
                Selected_row_TAB_T_Node.setDescription(node_description);
                Selected_row_TAB_T_Node.setLocation(node_location);
                Selected_row_TAB_T_Node.setPort_range_min(new Integer(port_range_min).intValue());
                Selected_row_TAB_T_Node.setPort_range_max(new Integer(port_range_max).intValue());
                Selected_row_TAB_T_Node.setProcess_group_id(group_id);

                byte nodeStatus = 0;
                if (jTB_node_active.isSelected())
                    nodeStatus = 1;
                Selected_row_TAB_T_Node.setActive(nodeStatus);            
                // ***********
                if(V_TAB_T_NODE.getNode_id(node_name) == -1)
                {
                    int Answer_Insert = Selected_row_TAB_T_Node.insert_Node();
                    if(Answer_Insert == 1)
                    {
                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Node '"+Selected_row_TAB_T_Node.getName()+"' has been added.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);

                        addRowJTable();
                        jTable_node.setSelectionBackground(java.awt.Color.GREEN.darker());

                        //seleziona riga JTable
                        int ID_MAX = 0;
                        int indexSelected = 0;
                        for(int i=0; i<jTable_node.getRowCount(); i++)
                        {
                            int ID = new Integer(""+jTable_node.getValueAt(i,0)).intValue();
                            if(ID > ID_MAX)
                            {
                                ID_MAX = ID;
                                indexSelected = i;
                            }
                        }

                        try
                        {   
                            jTable_node.setRowSelectionInterval(0,indexSelected);
                            jScrollPane1.validate();
                        }
                        catch(Exception e){}                    
                        setSelected_jTableRow();                    
                    }
                    else
                    {
                        addRowJTable();   
                        setSelected_jTableRow();
                        System.out.println("Cannot insert node");
                        
                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Cannot insert Node '"+Selected_row_TAB_T_Node.getName()+"'.",str_title_msg,JOptionPane.ERROR_MESSAGE);
                        
                        message = new JD_Message(frame_parent,true,"Cannot insert Node '"+Selected_row_TAB_T_Node.getName()+"'.",str_title_msg,message.CRITICAL);

                    }
                }
                else
                {         
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation Failed, \nthe Name of '"+node_name+"' Node exists.","Add group",JOptionPane.WARNING_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Operation Failed, \nthe Name of '"+node_name+"' Node exists.","Add group",message.WARNING);
                }
            
            }
            else //jTB_mod.isSelected()
            {
                int NODO_ID =  V_TAB_T_NODE.getNode_id(node_name);
                
                if( ( NODO_ID == -1) || ( NODO_ID == Selected_row_TAB_T_Node.getNode_id()) )
                { 
                    //JOptionPane op_YES_NO = new JOptionPane();
                    //int YES_NO = op_YES_NO.showConfirmDialog(null,"About to change Node Name: '"+Selected_row_TAB_T_Node.getName()+"' (#"+Selected_row_TAB_T_Node.getNode_id()+"). Confirm ?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    
                    JD_Message op_YES_NO = null;
                    op_YES_NO = new JD_Message(frame_parent,true, "About to change Node Name: '"+Selected_row_TAB_T_Node.getName()+"' (#"+Selected_row_TAB_T_Node.getNode_id()+"). Confirm ?","Question Messagge",op_YES_NO.QUESTION_YES_NO);
            
                    if(op_YES_NO.YES_NO == 1)
                    {       
                        // ***********
                        Selected_row_TAB_T_Node.setName(node_name);
                        Selected_row_TAB_T_Node.setDescription(node_description);
                        Selected_row_TAB_T_Node.setLocation(node_location);
                        Selected_row_TAB_T_Node.setPort_range_min(new Integer(port_range_min).intValue());
                        Selected_row_TAB_T_Node.setPort_range_max(new Integer(port_range_max).intValue());

                        Selected_row_TAB_T_Node.setProcess_group_id(V_TAB_PROCESS_GROUPS.getProcess_group_id((String)jCBox_process_group.getSelectedItem()));

                        byte nodeStatus = 0;
                        if (jTB_node_active.isSelected())
                            nodeStatus = 1;
                        Selected_row_TAB_T_Node.setActive(nodeStatus);            
                        // ***********

                        int answer_up = Selected_row_TAB_T_Node.update_Node();

                        if(answer_up >= 0)
                        {
                            //JOptionPane op = new JOptionPane();
                            //op.showMessageDialog(null,"Node '"+Selected_row_TAB_T_Node.getName()+"' has been changed.",str_title_msg,JOptionPane.INFORMATION_MESSAGE);

                            addRowJTable();   
                            jTable_node.setEnabled(true);

                            try
                            {   //seleziona riga JTable
                                for(int i=0; i<jTable_node.getRowCount(); i++)
                                {
                                    int node_id = new Integer(""+jTable_node.getValueAt(i,0)).intValue();
                                    if( node_id == Selected_row_TAB_T_Node.getNode_id() )
                                    {
                                        jTable_node.setRowSelectionInterval(i,i);
                                        jScrollPane1.validate();
                                        break;
                                    }
                                }
                            }catch(Exception e){}

                            setSelected_jTableRow();

                        }
                        else
                        {
                            addRowJTable();   
                            setSelected_jTableRow();
                            
                            //JOptionPane op = new JOptionPane();
                            //op.showMessageDialog(null,"Operation failed.",str_title_msg,JOptionPane.ERROR_MESSAGE);
                            
                            message = new JD_Message(frame_parent,true,"Operation failed.",str_title_msg,message.CRITICAL);
                        }
                    }
                }
                else
                {         
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation Failed, the name of '"+node_name+"' Node exists.","Modify group",JOptionPane.WARNING_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Operation Failed, the name of '"+node_name+"' Node exists.","Modify group",message.WARNING);                  
                }  
            }
            
        }
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_applyActionPerformed
     //////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private boolean DEBUG = false;
        private String[] columnNames ={ "Node ID",
                                        "Node Name",
                                        "Process Group ",                                        
                                        "port Range Min",
                                        "port Range Max",
                                        "Node Status"};
                
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JComboBox jCBox_process_group;
    private javax.swing.JLabel jL_node_desc;
    private javax.swing.JLabel jL_node_id;
    private javax.swing.JLabel jL_node_location;
    private javax.swing.JLabel jL_node_name;
    private javax.swing.JLabel jL_node_status;
    private javax.swing.JLabel jL_port_max;
    private javax.swing.JLabel jL_port_min;
    private javax.swing.JLabel jL_process_group;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_edit;
    private javax.swing.JPanel jP_node_status;
    private javax.swing.JPanel jP_port;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jPs_s;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jTB_add;
    private javax.swing.JToggleButton jTB_del;
    private javax.swing.JToggleButton jTB_mod;
    private javax.swing.JToggleButton jTB_node_active;
    private javax.swing.JToggleButton jTB_node_inactive;
    private javax.swing.JTextField jTF_node_desc;
    private javax.swing.JTextField jTF_node_id;
    private javax.swing.JTextField jTF_node_location;
    private javax.swing.JTextField jTF_node_name;
    private javax.swing.JTextField jTF_port_max;
    private javax.swing.JTextField jTF_port_min;
    // End of variables declaration//GEN-END:variables
   
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_node;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private int[] CellDimension = {40,120,100,80,80,60};
    private int minCellDimension = 20;
    
    private SSM_Vector_TAB_T_NODE V_TAB_T_NODE = null;
    private SSM_TAB_T_NODE Selected_row_TAB_T_Node;
    
    private String str_undefined = "undefined";
    private SSM_Vector_TAB_PROCESS_GROUPS V_TAB_PROCESS_GROUPS = null;
    
    private javax.swing.JFrame frame_parent = null;
    
}
