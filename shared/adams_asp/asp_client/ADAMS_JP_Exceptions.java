/*
 * JPanel.java
 *
 * Created on 18 luglio 2005, 15.27
 */

/**
 *
 * @author  root
 */
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;

public class ADAMS_JP_Exceptions extends javax.swing.JPanel {

    /** Creates new form JPanel */
    public ADAMS_JP_Exceptions(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {
        initComponents();
        this.local_INFO_CONFIG = INFO_CONFIG;
        
        jLabel1.setText("GUI Event Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        
        //filtri TextField 
        jText_tag.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        jText_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));
        jText_triggerValue.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,26));
        jText_setValue.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,26));
  
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_GE = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_GE.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_GE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_GE.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_GE.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_GE.setRowHeight(20);
        jTable_GE.setRowMargin(2);
        jTable_GE.setAutoCreateColumnsFromModel(false);
        
        jScrollPane1.setViewportView(jTable_GE);
        
        jTable_GE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_GEMouseReleased(evt);
            }
        });
        
        // **************************** //
        
        //Font Globali widget
        setGlobalFont();
        
        //Cursor mouse
        jB_commit.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jCBox_triggerTE.setCursor(Cur_hand);
        jCBox_triggerStatus.setCursor(Cur_hand);
        jCBox_action.setCursor(Cur_hand);
        jCBox_aggregate_GuiE.setCursor(Cur_hand);
        
        SetTable(jTable_GE);
        setJCBox_StatusAction();
        ReadEventGUI_external = false;
    }
    
    private void SetTable(javax.swing.JTable jtable)
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
            
            //if(CellDimension[i] == minCellDimension)
                //column.setMaxWidth(50);
        }
        
        //sorter.setSortingStatus(1,1); // ordino per tag
        
        if((jtable.isShowing())&&(jtable.isVisible()))
            jtable.updateUI();
                        
        this.setCursor(Cur_default);
    }
    
    public void setTableConfig(ADAMS_Vector_TAB_CONFIG Parent_V_TAB_CONFIG)
    {
        this.parent_V_TAB_CONFIG = Parent_V_TAB_CONFIG;
        jCBox_triggerTE.removeAllItems();
        jCBox_triggerTE.addItem(str_none);
        for(int i=0;i<parent_V_TAB_CONFIG.size(); i++)
        {
            ADAMS_TAB_CONFIG appo_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);
            jCBox_triggerTE.addItem(appo_row_TAB_CONFIG.POSIZIONE_ELEMENTO+" "+appo_row_TAB_CONFIG.TAG);
        }
        
        AddRowJTabel_general();
        setSelected_jTableRow();
    }
    
    public ADAMS_TAB_INFO_CONFIG get_INFO_CONFIG()
    {
        return local_INFO_CONFIG;
    }
    
    public ADAMS_Vector_TAB_EVENTI_GUI read_getEventGUI()
    {
        V_TAB_EVENTI_GUI = new ADAMS_Vector_TAB_EVENTI_GUI(this);
        boolean ok_read = V_TAB_EVENTI_GUI.read_items();
        ReadEventGUI_external = true;
        return V_TAB_EVENTI_GUI;
    }
    public ADAMS_Vector_TAB_EVENTI_GUI getEventGUI()
    {
        return V_TAB_EVENTI_GUI;
    }
    
    private void AddRowJTabel_general()
    {
        jTable_GE.setCursor(Cur_wait);
        jLabel1.setText("GUI Event Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);

        if(ReadEventGUI_external == false)
        {
            V_TAB_EVENTI_GUI = new ADAMS_Vector_TAB_EVENTI_GUI(this);
            boolean ok_read = V_TAB_EVENTI_GUI.read_items();
            //System.out.println("RILETTO Event GUI");
        }
        //else
            //System.out.println("NON RILEGGO Event GUI");
        ReadEventGUI_external = false;

        int SIZE = V_TAB_EVENTI_GUI.size();
        Object[][] dataValues = new Object[SIZE][9];
       
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_EVENTI_GUI row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)V_TAB_EVENTI_GUI.elementAt(i);
            
            dataValues[i][0] = new Integer(row_TAB_EVENTI_GUI.IDEXCEPTION);             //IDEXCEPTION
            dataValues[i][1] = new String(row_TAB_EVENTI_GUI.TAG);                      //TAG
            dataValues[i][2] = new String(row_TAB_EVENTI_GUI.DESCRIPTION);              //DESCRIPTION
            
            dataValues[i][3] = new String(parent_V_TAB_CONFIG.getPOSIZIONE_ELEMENTO_space_TAG(row_TAB_EVENTI_GUI.IDTRIGGERRESTRICTION,str_none));    //IDTRIGGERRESTRICTION
            
            try{
                dataValues[i][4] = new String(VectorStatusAction.getDesc(row_TAB_EVENTI_GUI.TRIGGEREDSTATUS));}    //TRIGGEREDSTATUS
            catch(Exception e){
                dataValues[i][4] = new String("Not Defined");}
  
            dataValues[i][5] = new String(row_TAB_EVENTI_GUI.TRIGGEREDVALUE);           //TRIGGEREDVALUE
            
            try{
                dataValues[i][6] = new String(VectorStatusAction.getDesc(row_TAB_EVENTI_GUI.ACTION));}    //ACTION
            catch(Exception e){
                dataValues[i][6] = new String("Not Defined");}
            
            dataValues[i][7] = new String(row_TAB_EVENTI_GUI.TARGETVALUE);              //TARGETVALUE            
            
            dataValues[i][8] = new String(V_TAB_EVENTI_GUI.getIDEXCEPTION_space_TAG(row_TAB_EVENTI_GUI.IDAGGREGATEEXCEPTION,str_none)); //IDAGGREGATEEXCEPTION
        }
        
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);
        
        if((jTable_GE.isShowing())&&(jTable_GE.isVisible()))
            jTable_GE.updateUI();
        
        jTable_GE.setCursor(Cur_default);
    }
    
    private void setJCBox_StatusAction()
    {
        jCBox_triggerStatus.removeAllItems();
        jCBox_action.removeAllItems();

        VectorStatusAction = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_TRIGGER);
        Vector AllDesc = VectorStatusAction.vgetDesc();
        
        for(int i=0; i<AllDesc.size(); i++)
        {
            jCBox_triggerStatus.addItem((String)AllDesc.elementAt(i));
            jCBox_action.addItem((String)AllDesc.elementAt(i));
        }
        //VectorStatusAction.stampaAll();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jPs_c = new javax.swing.JPanel();
        jL_id = new javax.swing.JLabel();
        jText_id = new javax.swing.JTextField();
        jL_tag = new javax.swing.JLabel();
        jText_tag = new javax.swing.JTextField();
        jL_desc = new javax.swing.JLabel();
        jText_desc = new javax.swing.JTextField();
        jP_otherSet = new javax.swing.JPanel();
        jL_triggerTE = new javax.swing.JLabel();
        jCBox_triggerTE = new javax.swing.JComboBox();
        jL_triggerStatus = new javax.swing.JLabel();
        jCBox_triggerStatus = new javax.swing.JComboBox();
        jL_triggerValue = new javax.swing.JLabel();
        jText_triggerValue = new javax.swing.JTextField();
        jL_action = new javax.swing.JLabel();
        jCBox_action = new javax.swing.JComboBox();
        jL_setValue = new javax.swing.JLabel();
        jText_setValue = new javax.swing.JTextField();
        jCBox_aggregate_GuiE = new javax.swing.JComboBox();
        jL_aggregateGuiE = new javax.swing.JLabel();
        jPs_s = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jPs_n = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        
        
        setLayout(new java.awt.BorderLayout(2, 2));
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 5));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui_32.png")));
        jLabel1.setText("- GUI Events - Configuration: ");
        jLabel1.setMinimumSize(new java.awt.Dimension(254, 40));
        jLabel1.setPreferredSize(new java.awt.Dimension(254, 40));
        add(jLabel1, java.awt.BorderLayout.NORTH);
        
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setOpaque(false);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setBorder(new javax.swing.border.TitledBorder(null, " GUI Event Single Operation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_south.setPreferredSize(new java.awt.Dimension(10, 165));
        jPs_c.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPs_c.setBackground(new java.awt.Color(230, 230, 230));
        jL_id.setBackground(new java.awt.Color(230, 230, 230));
        jL_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_id.setText("Id");
        jL_id.setToolTipText("ID Exception");
        jL_id.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_id.setMaximumSize(new java.awt.Dimension(18, 20));
        jL_id.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_id.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jL_id, gridBagConstraints1);
        
        jText_id.setBackground(new java.awt.Color(230, 230, 230));
        jText_id.setEditable(false);
        jText_id.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jText_id.setToolTipText("ID Exception");
        jText_id.setDisabledTextColor(java.awt.Color.darkGray);
        jText_id.setMinimumSize(new java.awt.Dimension(40, 22));
        jText_id.setPreferredSize(new java.awt.Dimension(40, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jText_id, gridBagConstraints1);
        
        jL_tag.setBackground(new java.awt.Color(230, 230, 230));
        jL_tag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_tag.setText("Tag");
        jL_tag.setToolTipText("(Max 30 Characters)");
        jL_tag.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_tag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_tag.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jL_tag, gridBagConstraints1);
        
        jText_tag.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_tag.setToolTipText("(Max 30 Characters)");
        jText_tag.setDisabledTextColor(java.awt.Color.darkGray);
        jText_tag.setMinimumSize(new java.awt.Dimension(160, 22));
        jText_tag.setPreferredSize(new java.awt.Dimension(160, 22));
        jText_tag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jText_tagKeyReleased(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jText_tag, gridBagConstraints1);
        
        jL_desc.setBackground(new java.awt.Color(230, 230, 230));
        jL_desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_desc.setText("Description");
        jL_desc.setToolTipText("(Max 160 Characters)");
        jL_desc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_desc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_desc.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jL_desc, gridBagConstraints1);
        
        jText_desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_desc.setToolTipText("(Max 160 Characters)");
        jText_desc.setDisabledTextColor(java.awt.Color.darkGray);
        jText_desc.setMinimumSize(new java.awt.Dimension(240, 22));
        jText_desc.setPreferredSize(new java.awt.Dimension(240, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jText_desc, gridBagConstraints1);
        
        jP_otherSet.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_otherSet.setBackground(new java.awt.Color(230, 230, 230));
        jL_triggerTE.setBackground(new java.awt.Color(230, 230, 230));
        jL_triggerTE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_triggerTE.setText("Trigger TE");
        jL_triggerTE.setToolTipText("(Select Item)");
        jL_triggerTE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_triggerTE.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_triggerTE.setPreferredSize(new java.awt.Dimension(100, 22));
        jL_triggerTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_triggerTE.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jL_triggerTE, gridBagConstraints2);
        
        jCBox_triggerTE.setBackground(java.awt.Color.white);
        jCBox_triggerTE.setMaximumRowCount(6);
        jCBox_triggerTE.setMinimumSize(new java.awt.Dimension(160, 22));
        jCBox_triggerTE.setPreferredSize(new java.awt.Dimension(160, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jCBox_triggerTE, gridBagConstraints2);
        
        jL_triggerStatus.setBackground(new java.awt.Color(230, 230, 230));
        jL_triggerStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_triggerStatus.setText("Trigger Status");
        jL_triggerStatus.setToolTipText("(Select Item)");
        jL_triggerStatus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_triggerStatus.setMaximumSize(new java.awt.Dimension(100, 22));
        jL_triggerStatus.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_triggerStatus.setPreferredSize(new java.awt.Dimension(100, 22));
        jL_triggerStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_triggerStatus.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jL_triggerStatus, gridBagConstraints2);
        
        jCBox_triggerStatus.setBackground(java.awt.Color.white);
        jCBox_triggerStatus.setMaximumRowCount(6);
        jCBox_triggerStatus.setMinimumSize(new java.awt.Dimension(100, 22));
        jCBox_triggerStatus.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jCBox_triggerStatus, gridBagConstraints2);
        
        jL_triggerValue.setBackground(new java.awt.Color(230, 230, 230));
        jL_triggerValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_triggerValue.setText("Trigger Value");
        jL_triggerValue.setToolTipText("(Max 26 Characters)");
        jL_triggerValue.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_triggerValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_triggerValue.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jL_triggerValue, gridBagConstraints2);
        
        jText_triggerValue.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_triggerValue.setToolTipText("(Max 26 Characters)");
        jText_triggerValue.setDisabledTextColor(java.awt.Color.darkGray);
        jText_triggerValue.setMinimumSize(new java.awt.Dimension(160, 22));
        jText_triggerValue.setPreferredSize(new java.awt.Dimension(160, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jText_triggerValue, gridBagConstraints2);
        
        jL_action.setBackground(new java.awt.Color(230, 230, 230));
        jL_action.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_action.setText("Action");
        jL_action.setToolTipText("(Select Item)");
        jL_action.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_action.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_action.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jL_action, gridBagConstraints2);
        
        jCBox_action.setBackground(java.awt.Color.white);
        jCBox_action.setMaximumRowCount(6);
        jCBox_action.setMinimumSize(new java.awt.Dimension(100, 22));
        jCBox_action.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jCBox_action, gridBagConstraints2);
        
        jL_setValue.setBackground(new java.awt.Color(230, 230, 230));
        jL_setValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_setValue.setText("SetValue");
        jL_setValue.setToolTipText("(Max 26 Characters)");
        jL_setValue.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_setValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_setValue.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jL_setValue, gridBagConstraints2);
        
        jText_setValue.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_setValue.setToolTipText("(Max 26 Characters)");
        jText_setValue.setDisabledTextColor(java.awt.Color.darkGray);
        jText_setValue.setMinimumSize(new java.awt.Dimension(160, 22));
        jText_setValue.setPreferredSize(new java.awt.Dimension(160, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_otherSet.add(jText_setValue, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 4;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(6, 1, 1, 1);
        jPs_c.add(jP_otherSet, gridBagConstraints1);
        
        jCBox_aggregate_GuiE.setBackground(java.awt.Color.white);
        jCBox_aggregate_GuiE.setMaximumRowCount(6);
        jCBox_aggregate_GuiE.setMinimumSize(new java.awt.Dimension(150, 22));
        jCBox_aggregate_GuiE.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jCBox_aggregate_GuiE, gridBagConstraints1);
        
        jL_aggregateGuiE.setBackground(new java.awt.Color(230, 230, 230));
        jL_aggregateGuiE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_aggregateGuiE.setText("Aggregate GUI Event");
        jL_aggregateGuiE.setToolTipText("(Select Item)");
        jL_aggregateGuiE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_aggregateGuiE.setMinimumSize(new java.awt.Dimension(150, 22));
        jL_aggregateGuiE.setPreferredSize(new java.awt.Dimension(150, 22));
        jL_aggregateGuiE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_aggregateGuiE.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPs_c.add(jL_aggregateGuiE, gridBagConstraints1);
        
        jP_south.add(jPs_c, java.awt.BorderLayout.CENTER);
        
        jPs_s.setLayout(new java.awt.BorderLayout());
        
        jPs_s.setBackground(new java.awt.Color(230, 230, 230));
        jPs_s.setMinimumSize(new java.awt.Dimension(72, 20));
        jPs_s.setPreferredSize(new java.awt.Dimension(152, 40));
        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(0, 0));
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
        
        jPs_s.add(jB_commit, java.awt.BorderLayout.EAST);
        
        jPs_n.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jPs_n.setBackground(new java.awt.Color(230, 230, 230));
        jPs_n.setMinimumSize(new java.awt.Dimension(0, 30));
        jPs_n.setPreferredSize(new java.awt.Dimension(0, 30));
        jrb_add.setBackground(new java.awt.Color(230, 230, 230));
        jrb_add.setSelected(true);
        buttonGroup1.add(jrb_add);
        jrb_add.setContentAreaFilled(false);
        jrb_add.setFocusPainted(false);
        jrb_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID.jpg")));
        jrb_add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jrb_add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_over.jpg")));
        jrb_add.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jrb_add.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jrb_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_addActionPerformed(evt);
            }
        });
        
        jPs_n.add(jrb_add);
        
        jrb_modify.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_modify);
        jrb_modify.setContentAreaFilled(false);
        jrb_modify.setFocusPainted(false);
        jrb_modify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_modify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID.jpg")));
        jrb_modify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jrb_modify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_over.jpg")));
        jrb_modify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jrb_modify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jrb_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_modifyActionPerformed(evt);
            }
        });
        
        jPs_n.add(jrb_modify);
        
        jrb_delete.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_delete);
        jrb_delete.setContentAreaFilled(false);
        jrb_delete.setFocusPainted(false);
        jrb_delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID.jpg")));
        jrb_delete.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jrb_delete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_over.jpg")));
        jrb_delete.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jrb_delete.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jrb_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_deleteActionPerformed(evt);
            }
        });
        
        jPs_n.add(jrb_delete);
        
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
        
        jPs_n.add(jB_cancel);
        
        jPs_s.add(jPs_n, java.awt.BorderLayout.CENTER);
        
        jP_south.add(jPs_s, java.awt.BorderLayout.SOUTH);
        
        add(jP_south, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private void jText_tagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_tagKeyReleased
        if(jText_tag.getText().length() > 0)
            jB_commit.setEnabled(true);
        else
            jB_commit.setEnabled(false);
    }//GEN-LAST:event_jText_tagKeyReleased

    
    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        //System.out.println("COMMIT");
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            AddRowJTabel_general();
            setSelected_jTableRow();
            return;
        }
        
        if(Selected_row_TAB_EVENTI_GUI == null)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
            return;
        }
        
        if(jrb_delete.isSelected())
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete GUI EVENT Id # "+Selected_row_TAB_EVENTI_GUI.IDEXCEPTION+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                int Removed_IDEXCEPTION = Selected_row_TAB_EVENTI_GUI.IDEXCEPTION;
                int answer_del = Selected_row_TAB_EVENTI_GUI.delete_GUI_EVENT();
                if(answer_del >= 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"GUI EVENT Id # "+Selected_row_TAB_EVENTI_GUI.IDEXCEPTION+" has been deleted.","INFO",3);
                    AddRowJTabel_general();
                    setSelected_jTableRow();
                    setEnabledClean_AllGui(false,true);
                    
                    boolean flagDeleteAgg = parent_V_TAB_CONFIG.deleteNestedException(Removed_IDEXCEPTION);
                    if(flagDeleteAgg)
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"The requested GUI event and any linkage to it has been deleted.","INFO",3);
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
            }
   
        }// end jrb_delete.isSelected()
        else
        {                             
            Selected_row_TAB_EVENTI_GUI.TAG                     = jText_tag.getText().trim();               //VARCHAR2(30)
            Selected_row_TAB_EVENTI_GUI.DESCRIPTION             = jText_desc.getText().trim();              //VARCHAR2(160)
            
            Selected_row_TAB_EVENTI_GUI.IDTRIGGERRESTRICTION = parent_V_TAB_CONFIG.getPOSIZIONE_ELEMENTO((String)jCBox_triggerTE.getSelectedItem()); //NUMBER
             
            /******* Assegno TRIGGEREDSTATUS  *****////
            String str_type_S = (String)jCBox_triggerStatus.getSelectedItem();
            int ID_STATUS = VectorStatusAction.getID(str_type_S,VectorStatusAction.getValue(str_type_S));            
            Selected_row_TAB_EVENTI_GUI.TRIGGEREDSTATUS = ID_STATUS;                                        //NUMBER
            
            //System.out.println("Selected_row_TAB_EVENTI_GUI.TRIGGEREDSTATUS "+Selected_row_TAB_EVENTI_GUI.TRIGGEREDSTATUS);
            // ************* ///

            Selected_row_TAB_EVENTI_GUI.TRIGGEREDVALUE          = jText_triggerValue.getText().trim();      //VARCHAR2(26)
                       
            /******* Assegno ACTION  *****////
            String str_type_A = (String)jCBox_action.getSelectedItem();
            int ID_ACTION = VectorStatusAction.getID(str_type_A,VectorStatusAction.getValue(str_type_A));            
            Selected_row_TAB_EVENTI_GUI.ACTION = ID_ACTION;                                                 //NUMBER
            
            //System.out.println("Selected_row_TAB_EVENTI_GUI.ACTION "+Selected_row_TAB_EVENTI_GUI.ACTION);
            // ************* ///

            Selected_row_TAB_EVENTI_GUI.TARGETVALUE             = jText_setValue.getText().trim();          //VARCHAR2(26)
            
            Selected_row_TAB_EVENTI_GUI.IDAGGREGATEEXCEPTION = V_TAB_EVENTI_GUI.getIDEXCEPTION((String)jCBox_aggregate_GuiE.getSelectedItem());  //NUMBER
            
           
            if(jrb_add.isSelected())
            {                               
                int Answer_InsertGUI_E = Selected_row_TAB_EVENTI_GUI.insert_GUI_EVENT();
                
                if(Answer_InsertGUI_E == 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"GUI Event Tag # "+Selected_row_TAB_EVENTI_GUI.TAG+" has been added.","INFO",3);
                    AddRowJTabel_general();
                    jTable_GE.setSelectionBackground(java.awt.Color.yellow);
                    
                    //seleziona riga JTable
                    int ID_MAX = 0;
                    int indexSelected = 0;
                    for(int i=0; i<jTable_GE.getRowCount(); i++)
                    {
                        int ID = new Integer(""+jTable_GE.getValueAt(i,0)).intValue();
                        if(ID > ID_MAX)
                        {
                            ID_MAX = ID;
                            indexSelected = i;
                        }
                    }
                    
                    try
                    {   
                        jTable_GE.setRowSelectionInterval(0,indexSelected);
                        jScrollPane1.validate();
                    }
                    catch(Exception e){}
                    
                    setSelected_jTableRow();                    
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert GUI Event Tag # "+Selected_row_TAB_EVENTI_GUI.TAG+".","ERROR",1);
            }
            else //jrb_modify.isSelected()
            {               
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change GUI EVENT id # "+Selected_row_TAB_EVENTI_GUI.IDEXCEPTION+". Confirm ?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    int answer_up = Selected_row_TAB_EVENTI_GUI.update_GUI_EVENT();
                    
                    if(answer_up >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"GUI Event id # "+Selected_row_TAB_EVENTI_GUI.IDEXCEPTION+" has been changed.","INFO",3);
                        AddRowJTabel_general();   
                        
                        try
                        {   //seleziona riga JTable
                            for(int i=0; i<jTable_GE.getRowCount(); i++)
                            {
                                int ID_TABLE = new Integer(""+jTable_GE.getValueAt(i,0)).intValue();
                                if( ID_TABLE == Selected_row_TAB_EVENTI_GUI.IDEXCEPTION )
                                {
                                    jTable_GE.setRowSelectionInterval(i,i);
                                    jScrollPane1.validate();
                                    break;
                                }
                            }
                        }catch(Exception e){}
                        
                        setSelected_jTableRow();
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                }
            }
        }
    }//GEN-LAST:event_jB_commitActionPerformed

    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
       jTable_GE.clearSelection();
       setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jTable_GEMouseReleased(java.awt.event.MouseEvent evt) 
    {
       if(jrb_add.isSelected() == false)
            setSelected_jTableRow();   
    }
    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        jTable_GE.setEnabled(true);
        jTable_GE.setSelectionBackground(java.awt.Color.red);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
        jTable_GE.setEnabled(true);
        jTable_GE.setSelectionBackground(java.awt.Color.green.darker());
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_modifyActionPerformed

    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        jTable_GE.setSelectionBackground(java.awt.Color.white);
        jTable_GE.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addActionPerformed

    
    private void setSelected_jTableRow()
    {
        this.setCursor(Cur_wait);
        int selectedRow = jTable_GE.getSelectedRow();
        
        //--- reset jCBox_aggregate_GuiE
        jCBox_aggregate_GuiE.removeAllItems();
        jCBox_aggregate_GuiE.addItem(str_none);
        //---
        
        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                boolean ctrl_row = false;
                int ID_selected = new Integer(""+jTable_GE.getValueAt(selectedRow,0)).intValue();

                // cerco l'oggetto ADAMS_TAB_EVENTI_GUI tramite IDEXCEPTION (Non rispetta l'ordine del vettore nel caso del Sorter della tabella)
                for(int i=0; i<V_TAB_EVENTI_GUI.size(); i++) 
                {
                    Selected_row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)V_TAB_EVENTI_GUI.elementAt(i);      
                    if(Selected_row_TAB_EVENTI_GUI.IDEXCEPTION == ID_selected)
                    {
                        ctrl_row = true;
                        break;
                    }
                }
                if(ctrl_row == false)
                {
                    Selected_row_TAB_EVENTI_GUI = null;
                    System.out.println("----- ERRORE GUI EVENT Selected_row...");
                    this.setCursor(Cur_default);
                    return;
                }
                
                //--- set jCBox_aggregate_GuiE and SelectedItem for CBX
                String item_aggregate_GuiE_selected = str_none;
                for(int i=0;  i<V_TAB_EVENTI_GUI.size(); i++)
                {
                    ADAMS_TAB_EVENTI_GUI appo_row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)V_TAB_EVENTI_GUI.elementAt(i);
                    if(appo_row_TAB_EVENTI_GUI.IDEXCEPTION != Selected_row_TAB_EVENTI_GUI.IDEXCEPTION)
                    {
                        String idEX_Tag = new String(appo_row_TAB_EVENTI_GUI.IDEXCEPTION+" "+appo_row_TAB_EVENTI_GUI.TAG);
                        jCBox_aggregate_GuiE.addItem(idEX_Tag);
                        if(appo_row_TAB_EVENTI_GUI.IDEXCEPTION == Selected_row_TAB_EVENTI_GUI.IDAGGREGATEEXCEPTION)
                            item_aggregate_GuiE_selected = idEX_Tag;
                    }
                }
                //---
                
                ////////////////
                jText_id.setText(""+Selected_row_TAB_EVENTI_GUI.IDEXCEPTION);             
                jText_tag.setText(Selected_row_TAB_EVENTI_GUI.TAG);            
                jText_desc.setText(Selected_row_TAB_EVENTI_GUI.DESCRIPTION);
                jText_triggerValue.setText(Selected_row_TAB_EVENTI_GUI.TRIGGEREDVALUE);
                jText_setValue.setText(Selected_row_TAB_EVENTI_GUI.TARGETVALUE);                
                                
                jCBox_triggerStatus.setSelectedItem(VectorStatusAction.getDesc(Selected_row_TAB_EVENTI_GUI.TRIGGEREDSTATUS));
                jCBox_action.setSelectedItem(VectorStatusAction.getDesc(Selected_row_TAB_EVENTI_GUI.ACTION));
                
                jCBox_triggerTE.setSelectedItem(parent_V_TAB_CONFIG.getPOSIZIONE_ELEMENTO_space_TAG(Selected_row_TAB_EVENTI_GUI.IDTRIGGERRESTRICTION,str_none));

                jCBox_aggregate_GuiE.setSelectedItem(item_aggregate_GuiE_selected);
                
                //////////////////
            }
            else
            {
                Selected_row_TAB_EVENTI_GUI = null;
                setEnabledClean_AllGui(false,false);
                this.setCursor(Cur_default);
                return;
            }
            
            if(jrb_modify.isSelected())
               setEnabledClean_AllGui(true,false);
            else //jrb_delete.isSelected()
                setEnabledClean_AllGui(false,false);
        }
        else // (jrb_add.isSelected())
        {
            jTable_GE.setEnabled(false);
            
            jText_id.setText("(Auto)");

            Selected_row_TAB_EVENTI_GUI = new ADAMS_TAB_EVENTI_GUI();
            Selected_row_TAB_EVENTI_GUI.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
            Selected_row_TAB_EVENTI_GUI.IDEXCEPTION  = -1; // NEW GUI EVENT
            
            //--- set jCBox_aggregate_GuiE
            for(int i=0;  i<V_TAB_EVENTI_GUI.size(); i++)
            {
                ADAMS_TAB_EVENTI_GUI appo_row_TAB_EVENTI_GUI = (ADAMS_TAB_EVENTI_GUI)V_TAB_EVENTI_GUI.elementAt(i);
                jCBox_aggregate_GuiE.addItem(new String(appo_row_TAB_EVENTI_GUI.IDEXCEPTION+" "+appo_row_TAB_EVENTI_GUI.TAG));
            }
            //---       
            setEnabledClean_AllGui(true,true);
        }
        this.setCursor(Cur_default);
    }
    
    //Permette di resettare e ripulire tutti i widget di input dell'interfaccia.
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {     
        jText_id.setEnabled(enable);
        jText_tag.setEnabled(enable);
        jText_desc.setEnabled(enable);
        jCBox_triggerTE.setEnabled(enable);
        jCBox_triggerStatus.setEnabled(enable);
        jText_triggerValue.setEnabled(enable);
        jCBox_action.setEnabled(enable);
        jText_setValue.setEnabled(enable);
        jCBox_aggregate_GuiE.setEnabled(enable);
        
        
        if(clean)
        {
            jText_id.setText("");
            jText_tag.setText("");
            jText_desc.setText("");
            jText_triggerValue.setText("");
            jText_setValue.setText("");

            try
            {
                jCBox_triggerTE.setSelectedIndex(0);
                jCBox_triggerStatus.setSelectedIndex(0);
                jCBox_action.setSelectedIndex(0);
                jCBox_aggregate_GuiE.setSelectedIndex(0);
            }
            catch(Exception e){}
        }
        if(jText_tag.getText().length() == 0)
            jB_commit.setEnabled(false);
        else
            jB_commit.setEnabled(true);
    }
    
    private void setGlobalFont()
    {
        //Font Globali
        jLabel1.setFont(ADAMS_GlobalParam.font_B12);
        jL_id.setFont(ADAMS_GlobalParam.font_B10);
        jText_id.setFont(ADAMS_GlobalParam.font_B10);
        jL_tag.setFont(ADAMS_GlobalParam.font_B10);
        jText_tag.setFont(ADAMS_GlobalParam.font_B10);
        jL_desc.setFont(ADAMS_GlobalParam.font_B10);
        jText_desc.setFont(ADAMS_GlobalParam.font_B10);
        jL_triggerTE.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_triggerTE.setFont(ADAMS_GlobalParam.font_B10);
        jL_triggerStatus.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_triggerStatus.setFont(ADAMS_GlobalParam.font_B10);
        jL_triggerValue.setFont(ADAMS_GlobalParam.font_B10);
        jText_triggerValue.setFont(ADAMS_GlobalParam.font_B10);
        jL_action.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_action.setFont(ADAMS_GlobalParam.font_B10);
        jL_setValue.setFont(ADAMS_GlobalParam.font_B10);
        jText_setValue.setFont(ADAMS_GlobalParam.font_B10);
        jL_aggregateGuiE.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_aggregate_GuiE.setFont(ADAMS_GlobalParam.font_B10);
    }
   
    
//////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames ={ "Id",
                                        "Tag",
                                        "Description",
                                        "Trigger TE",
                                        "Trigger Status",
                                        "Trigger Value",
                                        "Action",
                                        "Set Value",
                                        "Aggregate GUI"};
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jPs_c;
    private javax.swing.JLabel jL_id;
    private javax.swing.JTextField jText_id;
    private javax.swing.JLabel jL_tag;
    private javax.swing.JTextField jText_tag;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JTextField jText_desc;
    private javax.swing.JPanel jP_otherSet;
    private javax.swing.JLabel jL_triggerTE;
    private javax.swing.JComboBox jCBox_triggerTE;
    private javax.swing.JLabel jL_triggerStatus;
    private javax.swing.JComboBox jCBox_triggerStatus;
    private javax.swing.JLabel jL_triggerValue;
    private javax.swing.JTextField jText_triggerValue;
    private javax.swing.JLabel jL_action;
    private javax.swing.JComboBox jCBox_action;
    private javax.swing.JLabel jL_setValue;
    private javax.swing.JTextField jText_setValue;
    private javax.swing.JComboBox jCBox_aggregate_GuiE;
    private javax.swing.JLabel jL_aggregateGuiE;
    private javax.swing.JPanel jPs_s;
    private javax.swing.JButton jB_commit;
    private javax.swing.JPanel jPs_n;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables


    private boolean DEBUG = false;
    private javax.swing.JTable jTable_GE;
    private TableSorter sorter;
    
    private MyTableModel myTableModel;
    private int[] CellDimension = {30,90,150,100,65,60,65,60,100};
    private int minCellDimension = 30;
   
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG  = null;
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);

    private ADAMS_Vector_TAB_EVENTI_GUI V_TAB_EVENTI_GUI = null;
    private ADAMS_TAB_EVENTI_GUI Selected_row_TAB_EVENTI_GUI;
    
    private VectorHelp VectorStatusAction = null;
    private String str_none = "<none>";
    
    private boolean ReadEventGUI_external = false;
}