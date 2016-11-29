/*
 * ADAMS_JP_Counters.java
 *
 * Created on 29 settembre 2005, 16.01
 */

/**
 *
 * @author  luca
 */
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.sql.*;

public class ADAMS_JP_Counters extends javax.swing.JPanel implements Runnable{

    /** Creates new form ADAMS_JP_Counters */
    public ADAMS_JP_Counters(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {
        initComponents();
        
        this.local_INFO_CONFIG = INFO_CONFIG;
        jL_title.setText("Counters Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        
        jTF_TagCountersKit.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        jTF_SpecPath.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,1540));
        jTF_Tag.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,20));
        jTF_Description.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));
        jTF_TriggerValue.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,5));
                
        
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_Counters = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_Counters.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_Counters.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Counters.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Counters.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_Counters.setRowHeight(20);
        jTable_Counters.setRowMargin(2);
        jTable_Counters.setAutoCreateColumnsFromModel(false);
        
        jScrollPane1.setViewportView(jTable_Counters);
        
        jTable_Counters.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_CountersMouseReleased(evt);
            }
        });        
        // **************************** //
        
            ///// Type Counter Index
        jCBox_CountersIndex.removeAllItems();
        V_CountersIndex = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_COUNTER_INDEX);
        Vector AllDesc = V_CountersIndex.vgetDesc();
        for(int i=0; i<AllDesc.size(); i++)
            jCBox_CountersIndex.addItem((String)AllDesc.elementAt(i));
        //jCBox_CountersIndex.setSelectedIndex(0);
        //////////     
        
        ///// Type Counter Type
        jCBox_CounterType.removeAllItems();
        V_CountersType = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_COUNTER_TYPE);
        Vector AllDesc1 = V_CountersType.vgetDesc();
        for(int i=0; i<AllDesc1.size(); i++)
            jCBox_CounterType.addItem((String)AllDesc1.elementAt(i));
        //jCBox_CounterType.setSelectedIndex(0);
        //////////   
        
        ///// Type Percent OF and Trigger Counter
        jCBox_Of.removeAllItems();
        jCBox_TrigCounter.removeAllItems();
        V_PercentOf = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_PERCENT_OF);        
        Vector AllDesc2 = V_PercentOf.vgetDesc();
        for(int i=0; i<AllDesc2.size(); i++)
        {
            jCBox_Of.addItem((String)AllDesc2.elementAt(i));
            jCBox_TrigCounter.addItem((String)AllDesc2.elementAt(i));
        }
        //jCBox_Of.setSelectedIndex(0);
        ////////// 
                
        SetTable(jTable_Counters);
        
        //Font Globali
        setGlobalFont();
        
        // Cursor
        jCBox_CountersTag.setCursor(Cur_hand);
        jB_addCounter.setCursor(Cur_hand);
        jB_deleteCounter.setCursor(Cur_hand);
        jCBox_UsePL.setCursor(Cur_hand);
        jrb_specify.setCursor(Cur_hand);
        jrb_default.setCursor(Cur_hand);
        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jCBox_TriggerField.setCursor(Cur_hand);
        jCBox_CountersIndex.setCursor(Cur_hand);
        jCBox_CounterType.setCursor(Cur_hand);
        jCBox_Of.setCursor(Cur_hand);
        jCBox_TrigCounter.setCursor(Cur_hand);
        jb_commit.setCursor(Cur_hand);
        jb_cancel.setCursor(Cur_hand);
        jB_editPL.setCursor(Cur_hand);
        
        event_jCBox_CountersTag = (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_CountersTagActionPerformed(evt);
            }
        });
        
        jTable_Counters.setSelectionBackground(java.awt.Color.white);
        jTable_Counters.setEnabled(false);        
        
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
        }        
        //sorter.setSortingStatus(1,1); 
        
        if((jtable.isShowing())&&(jtable.isVisible()))
        {
            //System.out.println("1 ctrl updateUI");
            jtable.updateUI();
        }               
        this.setCursor(Cur_default);
    }
    
    public void initCounters(ADAMS_Vector_TAB_CONFIG Parent_V_CONFIG_NTM)
    {
       
        this.parent_V_TAB_CONFIG = Parent_V_CONFIG_NTM;
        jCBox_TriggerField.removeAllItems();
        jCBox_TriggerField.addItem(str_none);
        for(int i=0;i<parent_V_TAB_CONFIG.size(); i++)
        {
            ADAMS_TAB_CONFIG appo_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);
            jCBox_TriggerField.addItem(appo_row_TAB_CONFIG.POSIZIONE_ELEMENTO+" "+appo_row_TAB_CONFIG.TAG);
        }
        
        
        setEnableCleanGUI_pluginActive(false,true);
        setEnableGUI_Table(false);
        setEnableCleanGUI_CounterSetup(false,true);
        
        jCBox_CountersTag.removeActionListener(event_jCBox_CountersTag);        
        V_Counters = readALLCountersKit();
        jCBox_CountersTag.removeAllItems();
        jCBox_CountersTag.addItem(str_none);
        jL_IDCounter.setText("");
        
        for(int i=0; i<V_Counters.size(); i++)
        {
            jCBox_CountersTag.addItem(((ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(i)).TAG);
        }
        jCBox_CountersTag.setSelectedIndex(0);        
        jCBox_CountersTag.addActionListener(event_jCBox_CountersTag);
        setEnableCleanGUI_pluginActive(false,true);
        setEnableGUI_Table(false);
        setEnableCleanGUI_CounterSetup(false,true);
        KIT_COUNTER_SELECTED = null;
        jB_deleteCounter.setEnabled(false);
        
        AddRowJTabel_staticCounter(false);
    }
    
    private void initCounters()
    {       
                
        setEnableCleanGUI_pluginActive(false,true);
        setEnableGUI_Table(false);
        setEnableCleanGUI_CounterSetup(false,true);
        
        jCBox_CountersTag.removeActionListener(event_jCBox_CountersTag);        
        V_Counters = readALLCountersKit();
        jCBox_CountersTag.removeAllItems();
        jCBox_CountersTag.addItem(str_none);
        jL_IDCounter.setText("");
        
        for(int i=0; i<V_Counters.size(); i++)
        {
            jCBox_CountersTag.addItem(((ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(i)).TAG);
        }
        jCBox_CountersTag.setSelectedIndex(0);        
        jCBox_CountersTag.addActionListener(event_jCBox_CountersTag);
        setEnableCleanGUI_pluginActive(false,true);
        setEnableGUI_Table(false);
        setEnableCleanGUI_CounterSetup(false,true);
        KIT_COUNTER_SELECTED = null;
        jB_deleteCounter.setEnabled(false);
    }
    
      
    private void AddRowJTabel_staticCounter(boolean reloadStaticCounters)
    {
        jTable_Counters.setCursor(Cur_wait);
        
        int SIZE = 0;
        if(KIT_COUNTER_SELECTED != null)
        {
            boolean f = false;
            if(reloadStaticCounters)
                f = KIT_COUNTER_SELECTED.readCounterKit();
            
            SIZE = KIT_COUNTER_SELECTED.V_COUNTERKIT.size();
        }
        Object[][] dataValues = new Object[SIZE][8];
       
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_COUNTERKIT row_staticCounter = (ADAMS_TAB_COUNTERKIT)KIT_COUNTER_SELECTED.V_COUNTERKIT.elementAt(i);
             
            dataValues[i][0] = new String(row_staticCounter.TAG);
            dataValues[i][1] = new String(row_staticCounter.DESCRIPTION);
            
            dataValues[i][2] = new String(parent_V_TAB_CONFIG.getPOSIZIONE_ELEMENTO_space_TAG(row_staticCounter.TRIGGERFIELD,str_none));    //TRIGGERFIELD
            
            if(row_staticCounter.TRIGGERVALUE >= 0)
                dataValues[i][3] = new String(""+row_staticCounter.TRIGGERVALUE);
            else
                dataValues[i][3] = new String("");
            
            try{dataValues[i][4] = new String(V_CountersIndex.getDesc(row_staticCounter.COUNTERINDEX));
                }catch(Exception ex) {dataValues[i][4] = new String("");}
                
            try{dataValues[i][5] = new String(V_CountersType.getDesc(row_staticCounter.COUNTERTYPE));
                 }catch(Exception ex) {dataValues[i][5] = new String("");}
                 
            try{dataValues[i][6] = new String(V_PercentOf.getDesc(row_staticCounter.PERCENTOF));
                 }catch(Exception ex) {dataValues[i][6] = new String("");}
                 
            try
            {
                dataValues[i][7] = new String(V_PercentOf.getDesc(row_staticCounter.TRIGGERCOUNTER));
            }
            catch( Exception ex) 
            {         
                dataValues[i][7] = new String(""+jCBox_TrigCounter.getItemAt(0));
            }
        }        
        
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);
        
        jScrollPane1.setViewportView(jTable_Counters);
        jTable_Counters.setCursor(Cur_default);
    }
    
    private void setSelected_jTableRow()
    {
        this.setCursor(Cur_wait);
        
        int selectedRow = jTable_Counters.getSelectedRow();        
        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                boolean ctrl_row = false;
                String tag_selected = ""+jTable_Counters.getValueAt(selectedRow,0);                
                //System.out.println("tag_selected => "+tag_selected);
                                
                // cerco l'oggetto ADAMS_TAB_COUNTERKIT tramite TAG (Non rispetta l'ordine del vettore nel caso del Sorter della tabella)
                for(int i=0; i<KIT_COUNTER_SELECTED.V_COUNTERKIT.size(); i++) 
                {
                    ST_COUNTERKIT_SEL = (ADAMS_TAB_COUNTERKIT)KIT_COUNTER_SELECTED.V_COUNTERKIT.elementAt(i);      
                    if(ST_COUNTERKIT_SEL.TAG.compareTo(tag_selected) == 0)
                    {
                        ctrl_row = true;
                        break;
                    }
                }
                if(ctrl_row == false)
                {
                    ST_COUNTERKIT_SEL = null;
                    System.out.println("----- ERRORE Selected_row...");
                    this.setCursor(Cur_default);
                    return;
                }               
                
                jTF_Tag.setText(ST_COUNTERKIT_SEL.TAG);
                jTF_Description.setText(ST_COUNTERKIT_SEL.DESCRIPTION);            
                jTF_TriggerValue.setText(""+ST_COUNTERKIT_SEL.TRIGGERVALUE);
                
                jCBox_TriggerField.setSelectedItem(parent_V_TAB_CONFIG.getPOSIZIONE_ELEMENTO_space_TAG(ST_COUNTERKIT_SEL.TRIGGERFIELD,str_none));
                
                
                jCBox_CountersIndex.setSelectedItem(V_CountersIndex.getDesc(ST_COUNTERKIT_SEL.COUNTERINDEX));                 
                jCBox_CounterType.setSelectedItem(V_CountersType.getDesc(ST_COUNTERKIT_SEL.COUNTERTYPE));    
                jCBox_Of.setSelectedItem(V_PercentOf.getDesc(ST_COUNTERKIT_SEL.PERCENTOF));
                

                String item = V_PercentOf.getDesc(ST_COUNTERKIT_SEL.TRIGGERCOUNTER);
                if(item != null)
                    jCBox_TrigCounter.setSelectedItem(item); 
                else
                    jCBox_TrigCounter.setSelectedIndex(0); 

                
                jb_commit.setEnabled(true);
            }
            else
            {
                ST_COUNTERKIT_SEL = null;
                setEnableCleanGUI_CounterSetup(false,true);
                this.setCursor(Cur_default);
                return;
            }          
                        
            if(jrb_modify.isSelected())
               setEnableCleanGUI_CounterSetup(true,false);
            else 
               setEnableCleanGUI_CounterSetup(false,false);

        }
        else // (jrb_add.isSelected())
        {
            jTable_Counters.setEnabled(false);
            setEnableCleanGUI_CounterSetup(true,true);
            ST_COUNTERKIT_SEL = new ADAMS_TAB_COUNTERKIT();            
        }
        this.setCursor(Cur_default);
    }
    
    private Vector readALLCountersKit()
    {
    
        V_Counters.removeAllElements();
        
        OperThread = READ_ALLCOUNTERS;
        
        th = null;
        th = new Thread(this,"readALLCountersKit");
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
        return V_Counters;
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jP_NORTH = new javax.swing.JPanel();
        jL_title = new javax.swing.JLabel();
        jP_centerN = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jCBox_CountersTag = new javax.swing.JComboBox();
        jB_addCounter = new javax.swing.JButton();
        jB_deleteCounter = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jL_IDCounter = new javax.swing.JLabel();
        jP_centerS = new javax.swing.JPanel();
        jCBox_UsePL = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTF_SpecPath = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTF_TagCountersKit = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jrb_default = new javax.swing.JRadioButton();
        jrb_specify = new javax.swing.JRadioButton();
        jl_IDCounterSel = new javax.swing.JLabel();
        jB_editPL = new javax.swing.JButton();
        jL_PLName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_SOUTH = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTF_TriggerValue = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jCBox_CountersIndex = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jCBox_CounterType = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jCBox_Of = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTF_Tag = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTF_Description = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCBox_TriggerField = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jCBox_TrigCounter = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jb_commit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jb_cancel = new javax.swing.JButton();
        
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        jP_NORTH.setLayout(new java.awt.BorderLayout());
        
        jP_NORTH.setBackground(new java.awt.Color(230, 230, 230));
        jP_NORTH.setPreferredSize(new java.awt.Dimension(186, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/counters_32.png")));
        jL_title.setText("Counters");
        jL_title.setMinimumSize(new java.awt.Dimension(186, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(186, 40));
        jP_NORTH.add(jL_title, java.awt.BorderLayout.NORTH);
        
        jP_centerN.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_centerN.setBackground(new java.awt.Color(230, 230, 230));
        jP_centerN.setBorder(new javax.swing.border.TitledBorder(null, " Counter Kit Selection ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jP_centerN.setPreferredSize(new java.awt.Dimension(20, 80));
        jLabel10.setText("Counters Tag");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_centerN.add(jLabel10, gridBagConstraints1);
        
        jCBox_CountersTag.setBackground(java.awt.Color.white);
        jCBox_CountersTag.setMinimumSize(new java.awt.Dimension(180, 22));
        jCBox_CountersTag.setPreferredSize(new java.awt.Dimension(280, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_centerN.add(jCBox_CountersTag, gridBagConstraints1);
        
        jB_addCounter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_count.jpg")));
        jB_addCounter.setToolTipText("Add a new counters kit slot");
        jB_addCounter.setBorderPainted(false);
        jB_addCounter.setContentAreaFilled(false);
        jB_addCounter.setFocusPainted(false);
        jB_addCounter.setPreferredSize(new java.awt.Dimension(130, 28));
        jB_addCounter.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_count_press.jpg")));
        jB_addCounter.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_count_over.jpg")));
        jB_addCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_addCounterActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 4;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_centerN.add(jB_addCounter, gridBagConstraints1);
        
        jB_deleteCounter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_count.jpg")));
        jB_deleteCounter.setToolTipText("Delete current counter kit slot use CAREFULLY!");
        jB_deleteCounter.setBorderPainted(false);
        jB_deleteCounter.setContentAreaFilled(false);
        jB_deleteCounter.setFocusPainted(false);
        jB_deleteCounter.setPreferredSize(new java.awt.Dimension(130, 28));
        jB_deleteCounter.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_count_press.jpg")));
        jB_deleteCounter.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_count_over.jpg")));
        jB_deleteCounter.setEnabled(false);
        jB_deleteCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_deleteCounterActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 5;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_centerN.add(jB_deleteCounter, gridBagConstraints1);
        
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 1));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        jP_centerN.add(jLabel11, gridBagConstraints1);
        
        jL_IDCounter.setBackground(java.awt.Color.white);
        jL_IDCounter.setForeground(java.awt.Color.gray);
        jL_IDCounter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_IDCounter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_IDCounter.setMinimumSize(new java.awt.Dimension(22, 22));
        jL_IDCounter.setPreferredSize(new java.awt.Dimension(40, 22));
        jL_IDCounter.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        jP_centerN.add(jL_IDCounter, gridBagConstraints1);
        
        jP_NORTH.add(jP_centerN, java.awt.BorderLayout.CENTER);
        
        jP_centerS.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_centerS.setBackground(new java.awt.Color(230, 230, 230));
        jP_centerS.setBorder(new javax.swing.border.TitledBorder(null, " Plugin Activation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jP_centerS.setPreferredSize(new java.awt.Dimension(20, 130));
        jCBox_UsePL.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_UsePL.setText("Use Plugin");
        jCBox_UsePL.setToolTipText("(True/False)");
        jCBox_UsePL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCBox_UsePL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCBox_UsePL.setMinimumSize(new java.awt.Dimension(40, 22));
        jCBox_UsePL.setPreferredSize(new java.awt.Dimension(300, 22));
        jCBox_UsePL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_UsePL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCBox_UsePL.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_UsePL.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCBox_UsePL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCBox_UsePLMouseReleased(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_centerS.add(jCBox_UsePL, gridBagConstraints2);
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Plugin Name");
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 22));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_centerS.add(jLabel1, gridBagConstraints2);
        
        jTF_SpecPath.setMinimumSize(new java.awt.Dimension(150, 22));
        jTF_SpecPath.setPreferredSize(new java.awt.Dimension(300, 22));
        jTF_SpecPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        jP_centerS.add(jTF_SpecPath, gridBagConstraints2);
        
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Counter Tag");
        jLabel7.setMinimumSize(new java.awt.Dimension(100, 22));
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_centerS.add(jLabel7, gridBagConstraints2);
        
        jTF_TagCountersKit.setMinimumSize(new java.awt.Dimension(180, 22));
        jTF_TagCountersKit.setPreferredSize(new java.awt.Dimension(280, 22));
        jTF_TagCountersKit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_TagCountersKitKeyReleased(evt);
            }
        });
        
        jTF_TagCountersKit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_centerS.add(jTF_TagCountersKit, gridBagConstraints2);
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jrb_default.setBackground(new java.awt.Color(230, 230, 230));
        jrb_default.setSelected(true);
        jrb_default.setText("Default Path");
        jrb_default.setToolTipText("Default Path (True/False)");
        buttonGroup2.add(jrb_default);
        jrb_default.setContentAreaFilled(false);
        jrb_default.setFocusPainted(false);
        jrb_default.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jrb_default.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jrb_default.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_default.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_default.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_default.setMinimumSize(new java.awt.Dimension(100, 22));
        jrb_default.setPreferredSize(new java.awt.Dimension(100, 22));
        jrb_default.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_default.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_default.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_default.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_default.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jrb_specifyMouseReleased(evt);
            }
        });
        
        jPanel1.add(jrb_default);
        
        jrb_specify.setBackground(new java.awt.Color(230, 230, 230));
        jrb_specify.setText("Specify Path");
        jrb_specify.setToolTipText("Specify Path (True/False)");
        buttonGroup2.add(jrb_specify);
        jrb_specify.setContentAreaFilled(false);
        jrb_specify.setFocusPainted(false);
        jrb_specify.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jrb_specify.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jrb_specify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_specify.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_specify.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_specify.setMinimumSize(new java.awt.Dimension(100, 22));
        jrb_specify.setPreferredSize(new java.awt.Dimension(100, 22));
        jrb_specify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_specify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_specify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_specify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_specify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jrb_specifyMouseReleased(evt);
            }
        });
        
        jPanel1.add(jrb_specify);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        jP_centerS.add(jPanel1, gridBagConstraints2);
        
        jl_IDCounterSel.setBackground(java.awt.Color.white);
        jl_IDCounterSel.setForeground(java.awt.Color.gray);
        jl_IDCounterSel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jl_IDCounterSel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jl_IDCounterSel.setMinimumSize(new java.awt.Dimension(22, 22));
        jl_IDCounterSel.setPreferredSize(new java.awt.Dimension(40, 22));
        jl_IDCounterSel.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        jP_centerS.add(jl_IDCounterSel, gridBagConstraints2);
        
        jB_editPL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_editPL.setToolTipText("Edit Plugin Name");
        jB_editPL.setBorderPainted(false);
        jB_editPL.setContentAreaFilled(false);
        jB_editPL.setFocusPainted(false);
        jB_editPL.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_editPL.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_editPL.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_editPL.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_editPL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_editPL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_editPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_editPLActionPerformed(evt);
            }
        });
        
        jB_editPL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_centerS.add(jB_editPL, gridBagConstraints2);
        
        jL_PLName.setBackground(java.awt.Color.white);
        jL_PLName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_PLName.setMinimumSize(new java.awt.Dimension(150, 22));
        jL_PLName.setPreferredSize(new java.awt.Dimension(300, 22));
        jL_PLName.setOpaque(true);
        jL_PLName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                general_MousePressed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_centerS.add(jL_PLName, gridBagConstraints2);
        
        jP_NORTH.add(jP_centerS, java.awt.BorderLayout.SOUTH);
        
        add(jP_NORTH, java.awt.BorderLayout.NORTH);
        
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setOpaque(false);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        
        jP_SOUTH.setLayout(new java.awt.BorderLayout());
        
        jP_SOUTH.setBackground(new java.awt.Color(230, 230, 230));
        jP_SOUTH.setBorder(new javax.swing.border.TitledBorder(null, " Static Counter Setup ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_SOUTH.setMinimumSize(new java.awt.Dimension(360, 200));
        jP_SOUTH.setPreferredSize(new java.awt.Dimension(10, 165));
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 100));
        jLabel12.setBackground(new java.awt.Color(230, 230, 230));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Trigger Value");
        jLabel12.setToolTipText("Counters Kit");
        jLabel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel12.setMinimumSize(new java.awt.Dimension(70, 22));
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 22));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jLabel12, gridBagConstraints3);
        
        jTF_TriggerValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_TriggerValue.setToolTipText("");
        jTF_TriggerValue.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_TriggerValue.setMinimumSize(new java.awt.Dimension(4, 22));
        jTF_TriggerValue.setPreferredSize(new java.awt.Dimension(70, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jTF_TriggerValue, gridBagConstraints3);
        
        jLabel13.setBackground(new java.awt.Color(230, 230, 230));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Counters Index");
        jLabel13.setToolTipText("Counters Index");
        jLabel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel13.setMinimumSize(new java.awt.Dimension(50, 22));
        jLabel13.setPreferredSize(new java.awt.Dimension(90, 22));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jLabel13, gridBagConstraints3);
        
        jCBox_CountersIndex.setBackground(java.awt.Color.white);
        jCBox_CountersIndex.setMaximumRowCount(6);
        jCBox_CountersIndex.setMinimumSize(new java.awt.Dimension(50, 22));
        jCBox_CountersIndex.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jCBox_CountersIndex, gridBagConstraints3);
        
        jLabel3.setBackground(new java.awt.Color(230, 230, 230));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Counter Type");
        jLabel3.setToolTipText("Counter Type");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jLabel3, gridBagConstraints3);
        
        jCBox_CounterType.setBackground(java.awt.Color.white);
        jCBox_CounterType.setMaximumRowCount(6);
        jCBox_CounterType.setMinimumSize(new java.awt.Dimension(100, 22));
        jCBox_CounterType.setPreferredSize(new java.awt.Dimension(180, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jCBox_CounterType, gridBagConstraints3);
        
        jLabel14.setBackground(new java.awt.Color(230, 230, 230));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Trigger Counter");
        jLabel14.setToolTipText("Trigger Counter");
        jLabel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel14.setMinimumSize(new java.awt.Dimension(50, 22));
        jLabel14.setPreferredSize(new java.awt.Dimension(90, 22));
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jLabel14, gridBagConstraints3);
        
        jCBox_Of.setBackground(java.awt.Color.white);
        jCBox_Of.setMaximumRowCount(6);
        jCBox_Of.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jCBox_Of, gridBagConstraints3);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jLabel6.setBackground(new java.awt.Color(230, 230, 230));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tag");
        jLabel6.setToolTipText("Tag");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jLabel6, gridBagConstraints4);
        
        jTF_Tag.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_Tag.setToolTipText("(Max 160 Characters)");
        jTF_Tag.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_Tag.setMinimumSize(new java.awt.Dimension(100, 22));
        jTF_Tag.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jTF_Tag, gridBagConstraints4);
        
        jLabel5.setBackground(new java.awt.Color(230, 230, 230));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Description");
        jLabel5.setToolTipText("Description");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jLabel5, gridBagConstraints4);
        
        jTF_Description.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_Description.setToolTipText("(Max 160 Characters)");
        jTF_Description.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_Description.setMinimumSize(new java.awt.Dimension(200, 22));
        jTF_Description.setPreferredSize(new java.awt.Dimension(380, 22));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jTF_Description, gridBagConstraints4);
        
        jLabel4.setBackground(new java.awt.Color(230, 230, 230));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Trigger Field");
        jLabel4.setToolTipText("Trigger Field");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setMinimumSize(new java.awt.Dimension(140, 22));
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 22));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jLabel4, gridBagConstraints4);
        
        jCBox_TriggerField.setBackground(java.awt.Color.white);
        jCBox_TriggerField.setMaximumRowCount(6);
        jCBox_TriggerField.setMinimumSize(new java.awt.Dimension(100, 22));
        jCBox_TriggerField.setPreferredSize(new java.awt.Dimension(180, 22));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jPanel4.add(jCBox_TriggerField, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 10, 1);
        jPanel3.add(jPanel4, gridBagConstraints3);
        
        jLabel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("% or Average Of");
        jLabel2.setToolTipText("% or Average Of");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setMinimumSize(new java.awt.Dimension(50, 22));
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 22));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jLabel2, gridBagConstraints3);
        
        jCBox_TrigCounter.setBackground(java.awt.Color.white);
        jCBox_TrigCounter.setMaximumRowCount(6);
        jCBox_TrigCounter.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel3.add(jCBox_TrigCounter, gridBagConstraints3);
        
        jP_SOUTH.add(jPanel3, java.awt.BorderLayout.CENTER);
        
        jPanel6.setLayout(new java.awt.BorderLayout());
        
        jPanel6.setBackground(new java.awt.Color(230, 230, 230));
        jPanel6.setPreferredSize(new java.awt.Dimension(152, 40));
        jb_commit.setBackground(new java.awt.Color(230, 230, 230));
        jb_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jb_commit.setBorderPainted(false);
        jb_commit.setContentAreaFilled(false);
        jb_commit.setFocusPainted(false);
        jb_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jb_commit.setMaximumSize(new java.awt.Dimension(0, 0));
        jb_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jb_commit.setPreferredSize(new java.awt.Dimension(80, 30));
        jb_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jb_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jb_commit.setEnabled(false);
        jb_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_commitActionPerformed(evt);
            }
        });
        
        jPanel6.add(jb_commit, java.awt.BorderLayout.EAST);
        
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 30));
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
        
        jPanel2.add(jrb_add);
        
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
        
        jPanel2.add(jrb_modify);
        
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
        
        jPanel2.add(jrb_delete);
        
        jb_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg")));
        jb_cancel.setToolTipText("Cancel");
        jb_cancel.setBorderPainted(false);
        jb_cancel.setContentAreaFilled(false);
        jb_cancel.setFocusPainted(false);
        jb_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jb_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg")));
        jb_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg")));
        jb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelActionPerformed(evt);
            }
        });
        
        jPanel2.add(jb_cancel);
        
        jPanel6.add(jPanel2, java.awt.BorderLayout.CENTER);
        
        jP_SOUTH.add(jPanel6, java.awt.BorderLayout.SOUTH);
        
        add(jP_SOUTH, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private void general_MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_general_MousePressed
        if(jrb_modify.isSelected())
        {
            if((evt.getSource() == jTF_SpecPath) && (!jTF_SpecPath.isEnabled()))
                return;
            
            jb_commit.setEnabled(true);
        }
    }//GEN-LAST:event_general_MousePressed

    private void jB_editPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_editPLActionPerformed
        ADAMS_JD_setPlugin  JD_PluginSelect = new ADAMS_JD_setPlugin(null,true,700,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,KIT_COUNTER_SELECTED.PLUGINNAME);
        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        KIT_COUNTER_SELECTED.PLUGINNAME = JD_PluginSelect.getPluginEnabled();
        JD_PluginSelect.dispose();
        
        if(KIT_COUNTER_SELECTED.PLUGINNAME.length() >0)    
            jL_PLName.setText(KIT_COUNTER_SELECTED.PLUGINNAME);
        else
            jL_PLName.setText("");
    }//GEN-LAST:event_jB_editPLActionPerformed

    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        
        jTable_Counters.setEnabled(true);
        jTable_Counters.setSelectionBackground(java.awt.Color.red);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed

        jTable_Counters.setEnabled(true);
        jTable_Counters.setSelectionBackground(java.awt.Color.green.darker());
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_modifyActionPerformed

    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
 
        jTable_Counters.setSelectionBackground(java.awt.Color.white);
        jTable_Counters.clearSelection();
        jTable_Counters.setEnabled(false);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addActionPerformed

    private void jb_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelActionPerformed
       jCBox_CountersTag.setSelectedIndex(0);
    }//GEN-LAST:event_jb_cancelActionPerformed

    private void jb_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_commitActionPerformed
     
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            jCBox_CountersTag.setSelectedIndex(0);
            return;
        }
        
        if(KIT_COUNTER_SELECTED == null)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
            return;
        }
                       
        int ID_CounterSelected = KIT_COUNTER_SELECTED.IDCOUNTER;

        KIT_COUNTER_SELECTED.TAG = jTF_TagCountersKit.getText().trim();

        if(jCBox_UsePL.isSelected())
        {
            KIT_COUNTER_SELECTED.USEPLUGIN = 'Y';                

            String strPLName = jL_PLName.getText().trim();
            if(strPLName.length() == 0)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"'Use Plugin' is enabled but there is no Plugin Name !","WARNING",4);
                return;
            }                
            KIT_COUNTER_SELECTED.PLUGINNAME = strPLName;

            if(jrb_specify.isSelected())
            {
                KIT_COUNTER_SELECTED.USEPATH = 'Y';

                String strPathName = jTF_SpecPath.getText().trim();
                if(strPathName.length() == 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"'Specify Path' is enabled but there is no Path !","WARNING",4);
                    return;
                }
                KIT_COUNTER_SELECTED.PATHNAME = strPathName;
            }
            else
            {
                KIT_COUNTER_SELECTED.USEPATH  = 'N';
                KIT_COUNTER_SELECTED.PATHNAME ="";
            }
        }
        else
        {
            KIT_COUNTER_SELECTED.USEPLUGIN   = 'N';
            KIT_COUNTER_SELECTED.USEPATH     = 'N';
            KIT_COUNTER_SELECTED.PLUGINNAME  = "";
            KIT_COUNTER_SELECTED.PATHNAME    = "";
        }
           
        if(jrb_delete.isSelected())
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete Static Counter "+ST_COUNTERKIT_SEL.TAG+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                boolean answer_del = KIT_COUNTER_SELECTED.V_COUNTERKIT.removeElement(ST_COUNTERKIT_SEL);
                
                if(answer_del == false)
                    System.out.println("Errore Delete Vector");
            } 
        }
        else if((jrb_add.isSelected())||(jrb_modify.isSelected()))
        {           
            String tag_STCounter = jTF_Tag.getText().trim();   

            if(tag_STCounter.length() != 0)
            {                    
                ST_COUNTERKIT_SEL.TAG = tag_STCounter;

                if(KIT_COUNTER_SELECTED.ctrl_V_COUNTERKIT_TAG_IsPresent(ST_COUNTERKIT_SEL) == true)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Static Counter "+tag_STCounter+" already exists in database.","Warning",4);
                    return;
                }                          

                ST_COUNTERKIT_SEL.DESCRIPTION     = jTF_Description.getText().trim();

                ST_COUNTERKIT_SEL.TRIGGERFIELD = parent_V_TAB_CONFIG.getPOSIZIONE_ELEMENTO((String)jCBox_TriggerField.getSelectedItem()); //NUMBER

                try{ST_COUNTERKIT_SEL.TRIGGERVALUE    = new Integer((String)jTF_TriggerValue.getText().trim()).intValue();}      //NUMBER
                    catch(java.lang.NumberFormatException e){ST_COUNTERKIT_SEL.TRIGGERVALUE = -1;}

                /******* Assegno COUNTERINDEX *****////
                String str_counterIndex = (String)jCBox_CountersIndex.getSelectedItem();                
                int ID_COUNTERINDEX = V_CountersIndex.getID(str_counterIndex,V_CountersIndex.getValue(str_counterIndex));                
                ST_COUNTERKIT_SEL.COUNTERINDEX = ID_COUNTERINDEX;
                
                boolean f_unused = false;
                if(V_CountersIndex.getValue(str_counterIndex) == 0)
                {
                    ADAMS_JD_Message opctrl = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Counter Index "+str_counterIndex+".\nContinue?","Warning",6);
                    int Yes_No_ctrl = opctrl.getAnswer();
                    if(Yes_No_ctrl == 0)
                        return;
                    f_unused = true;
                }
                
                if(!f_unused)
                {
                    if(KIT_COUNTER_SELECTED.ctrl_V_COUNTERKIT_Counter_IsPresent(ST_COUNTERKIT_SEL) == true)
                    {
                        ADAMS_JD_Message opctrl = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Counter Index '"+str_counterIndex+"' already exists.\nContinue?","Warning",6);
                        int Yes_No_ctrl = opctrl.getAnswer();
                        if(Yes_No_ctrl == 0)
                            return;
                    }
                }
                
                // ************* ///    
                                 
                 /******* Assegno COUNTERTYPE *****////
                String str_counterType = (String)jCBox_CounterType.getSelectedItem();
                int ID_COUNTERTYPE = V_CountersType.getID(str_counterType,V_CountersType.getValue(str_counterType));                
                ST_COUNTERKIT_SEL.COUNTERTYPE = ID_COUNTERTYPE;
                // ************* ///    
                                
                /******* Assegno PERCENTOF *****////
                String str_PERCENTOF = (String)jCBox_Of.getSelectedItem();
                int ID_PERCENTOF = V_PercentOf.getID(str_PERCENTOF,V_PercentOf.getValue(str_PERCENTOF));                
                ST_COUNTERKIT_SEL.PERCENTOF = ID_PERCENTOF;
                // ************* ///   

                /******* Assegno TRIGGERCOUNTER *****////
                String str_TRIGGERCOUNTER = (String)jCBox_TrigCounter.getSelectedItem();
                int ID_TCOUNTER = V_PercentOf.getID(str_TRIGGERCOUNTER,V_PercentOf.getValue(str_TRIGGERCOUNTER));                
                ST_COUNTERKIT_SEL.TRIGGERCOUNTER = ID_TCOUNTER;
                // ************* ///   
                                
                
                if( jrb_add.isSelected() )
                    KIT_COUNTER_SELECTED.V_COUNTERKIT.addElement(ST_COUNTERKIT_SEL);
            }
            else
            {
                if ( (jrb_modify.isSelected()) && (jTable_Counters.getSelectedRow() >= 0) )
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Warning: Static Counter selected while in modify mode. Please specify a TAG.","Warning",4);
                    return;
                }
            }
        }

        int Answer_Update_StCounter = KIT_COUNTER_SELECTED.updateCounters();
        if(Answer_Update_StCounter >= 0)
        {
            String tag = "";
            if(ST_COUNTERKIT_SEL != null)
                tag = ST_COUNTERKIT_SEL.TAG;

            if(jrb_add.isSelected())
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Static Counter "+tag+" has been added.","INFO",3);
                jTable_Counters.setSelectionBackground(java.awt.Color.yellow);
            }
            else if( jrb_modify.isSelected())
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Static Counter "+tag+" has been changed.","INFO",3);
                jTable_Counters.setSelectionBackground(java.awt.Color.green.darker());
            }
            else
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Static Counter "+tag+" has been deleted.","INFO",3);                

            initCounters();
            AddRowJTabel_staticCounter(true);

            for(int i=0; i<V_Counters.size(); i++)
            {
                if( (((ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(i)).IDCOUNTER) == ID_CounterSelected )
                {
                    jCBox_CountersTag.setSelectedItem(((ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(i)).TAG);
                    break;
                }
            }                                        

            //------------- seleziona riga JTable            
            if(!jrb_delete.isSelected())
            {
                int indexSelected = 0;
                for(int i=0; i<jTable_Counters.getRowCount(); i++)
                {
                    String JT_tag = ""+jTable_Counters.getValueAt(i,0);
                    if(JT_tag.compareTo(tag) == 0)
                    {
                        indexSelected = i;
                        break;
                    }
                }

                try
                {   
                    jTable_Counters.setRowSelectionInterval(0,indexSelected);
                    jScrollPane1.validate();
                }
                catch(Exception e){}
            }
            
            if(!jrb_add.isSelected())
                jb_commit.setEnabled(false);
            
            //------------- end seleziona riga JTable 
        }
        else
        {          
            if( jrb_add.isSelected() )
                KIT_COUNTER_SELECTED.V_COUNTERKIT.removeElement(ST_COUNTERKIT_SEL);

            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation Failed.","ERROR",1);
        }
    }//GEN-LAST:event_jb_commitActionPerformed

    private void jrb_specifyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrb_specifyMouseReleased
        if(jrb_specify.isSelected())
        {
            jTF_SpecPath.setEnabled(true);
            jTF_SpecPath.requestFocus();
        }
        else
            jTF_SpecPath.setEnabled(false);
    }//GEN-LAST:event_jrb_specifyMouseReleased

    private void jCBox_UsePLMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBox_UsePLMouseReleased
       if(jCBox_UsePL.isSelected())
        {    
            jLabel1.setEnabled(true);
            jL_PLName.setEnabled(true);
            jB_editPL.setEnabled(true);
            jrb_default.setEnabled(true);
            jrb_default.setSelected(true);
            jrb_specify.setEnabled(true);
            jTF_SpecPath.setEnabled(false);
            
            jL_PLName.requestFocus();
        }
        else
        {
            jLabel1.setEnabled(false);
            jL_PLName.setEnabled(false);
            jB_editPL.setEnabled(false);
            jrb_default.setSelected(true);
            jrb_default.setEnabled(false);
            jrb_specify.setEnabled(false);
            jTF_SpecPath.setEnabled(false);
        }
    }//GEN-LAST:event_jCBox_UsePLMouseReleased

    private void jTF_TagCountersKitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_TagCountersKitKeyReleased
        
        if(jTF_TagCountersKit.getText().trim().length() > 0)
            jb_commit.setEnabled(true);
        else
            jb_commit.setEnabled(false);           
    }//GEN-LAST:event_jTF_TagCountersKitKeyReleased

    private void jB_deleteCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_deleteCounterActionPerformed
               
        setEnableCleanGUI_pluginActive(false,true);                          
        ADAMS_JD_ADD_DEL_CounterKit JD_DELETE_CounterKit = new ADAMS_JD_ADD_DEL_CounterKit(true,"DELETE COUNTER kit for configuration: "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+".",KIT_COUNTER_SELECTED,2);
        
        int result = JD_DELETE_CounterKit.getResult();
        if(result == 1)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Counter #"+KIT_COUNTER_SELECTED.IDCOUNTER+" "+KIT_COUNTER_SELECTED.TAG+" has been deleted.","INFO",3);
            initCounters();
            AddRowJTabel_staticCounter(false);
        }
        else if(result == 0)
           ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot delete Counter: "+KIT_COUNTER_SELECTED.IDCOUNTER+" "+KIT_COUNTER_SELECTED.TAG+".","ERROR",1);
        
        JD_DELETE_CounterKit.dispose();        
        
    }//GEN-LAST:event_jB_deleteCounterActionPerformed

    private void jB_addCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_addCounterActionPerformed
        
        jCBox_CountersTag.setSelectedIndex(0);
        setEnableCleanGUI_pluginActive(false,true);
        
        ADAMS_TAB_KIT_CONTATORI KIT_COUNTER_SELECTED_ADD = new ADAMS_TAB_KIT_CONTATORI();
        KIT_COUNTER_SELECTED_ADD.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
                   
        ADAMS_JD_ADD_DEL_CounterKit JD_ADD_CounterKit = new ADAMS_JD_ADD_DEL_CounterKit(true,"ADD COUNTER KIT for configuration: "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+".",KIT_COUNTER_SELECTED_ADD,1);
        
        int result = JD_ADD_CounterKit.getResult();
        if(result == 1)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Counter "+KIT_COUNTER_SELECTED_ADD.TAG+" has been added.","INFO",3);
            initCounters();
            AddRowJTabel_staticCounter(false);
        }
        else if(result == 0)
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Counter: "+KIT_COUNTER_SELECTED_ADD.TAG+".","ERROR",1);
        
        JD_ADD_CounterKit.dispose();        
         
    }//GEN-LAST:event_jB_addCounterActionPerformed
 
    private void jCBox_CountersTagActionPerformed(java.awt.event.ActionEvent evt) 
    {
        
        int indexSelected = jCBox_CountersTag.getSelectedIndex();
                
        if(indexSelected <= 0)
        {            
            jL_IDCounter.setText("");
            setEnableCleanGUI_pluginActive(false,true);
            setEnableGUI_Table(false);
            setEnableCleanGUI_CounterSetup(false,true);
            KIT_COUNTER_SELECTED = null;
            jB_deleteCounter.setEnabled(false);
        }
        else
        {
            setEnableCleanGUI_pluginActive(true,true);
            setEnableGUI_Table(true);
            setEnableCleanGUI_CounterSetup(true,true);
            
            KIT_COUNTER_SELECTED = (ADAMS_TAB_KIT_CONTATORI)V_Counters.elementAt(indexSelected-1);
            
            jL_IDCounter.setText(""+KIT_COUNTER_SELECTED.IDCOUNTER);
            jl_IDCounterSel.setText(""+KIT_COUNTER_SELECTED.IDCOUNTER);            
            jTF_TagCountersKit.setText(KIT_COUNTER_SELECTED.TAG);                    
            
            jCBox_UsePL.setSelected(false);
            if(KIT_COUNTER_SELECTED.USEPLUGIN == 'Y')
            {
                jCBox_UsePL.setSelected(true);            
                jL_PLName.setText(KIT_COUNTER_SELECTED.PLUGINNAME);
                
                jrb_default.setSelected(true);
                jTF_SpecPath.setEnabled(false);                
                if(KIT_COUNTER_SELECTED.USEPATH == 'Y')
                {
                    jrb_specify.setSelected(true);
                    jTF_SpecPath.setText(KIT_COUNTER_SELECTED.PATHNAME);
                    jTF_SpecPath.setEnabled(true);   
                }
                else
                    jTF_SpecPath.setEnabled(false);
            }
            else
            {
                jLabel1.setEnabled(false);
                jL_PLName.setEnabled(false);
                jB_editPL.setEnabled(false);
                jrb_default.setSelected(true);
                jrb_default.setEnabled(false);
                jrb_specify.setEnabled(false);
                jTF_SpecPath.setEnabled(false);
            }            
            jB_deleteCounter.setEnabled(true);   
        }
        AddRowJTabel_staticCounter(false);
    }
    
    public void run()
    { 
        if(OperThread == READ_ALLCOUNTERS) //readALLCountersKit()
        {            
            
            
            String sel_ReadCounters = "select "+item_KIT_CONTATORI+" from "+name_TabCounters+
                                        " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by TAG";

            //System.out.println("sel_ReadCounters --> "+sel_ReadCounters);
            Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ReadCounters,SQLStatement);
            

            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {
                        ADAMS_TAB_KIT_CONTATORI row_Contatori = new ADAMS_TAB_KIT_CONTATORI();
                        row_Contatori.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;

                        //"IDCOUNTER,TAG,USEPLUGIN,PLUGINNAME,USEPATH,PATHNAME";

                    //*** Campi Contatore -- alcuni campi sono NOT Null sul DB ***//
                        row_Contatori.IDCOUNTER          = rs.getInt("IDCOUNTER");          //not null sil DB

                        String tagCounter = rs.getString("TAG");
                        if(tagCounter != null)
                            row_Contatori.TAG = tagCounter;

                        row_Contatori.USEPLUGIN = rs.getString("USEPLUGIN").charAt(0);      //not null sil DB
                        
                        String plname = rs.getString("PLUGINNAME");
                        if(plname != null)
                            row_Contatori.PLUGINNAME = plname;                        
                        
                        row_Contatori.USEPATH   = rs.getString("USEPATH").charAt(0);        //not null sil DB                        
                        
                        String pathName = rs.getString("PATHNAME");
                        if(pathName != null)
                            row_Contatori.PATHNAME   = pathName;                
                        
                        // ------------- Nested LISTA_REPORT ------------- //
                        String[] nomeCampi = {"TRIGGERFIELD","TRIGGERVALUE","COUNTERINDEX","COUNTERTYPE","PERCENTOF","TRIGGERCOUNTER","TAG","DESCRIPTION"}; 
                        
                        String linkTable  = "tab_type_counters";                        
                        String selectReadNested = "select ";
            
                        for(int i=0;i<nomeCampi.length;i++)
                        {
                            if(i!=nomeCampi.length-1)
                                selectReadNested += nomeCampi[i]+",";
                            else
                                selectReadNested += nomeCampi[i]+" ";  
                        }

                        selectReadNested += " from " + linkTable ;
                        selectReadNested += " where ";
                        selectReadNested += " TIPO_DI_CONFIGURAZIONE='"+row_Contatori.TIPO_DI_CONFIGURAZIONE+"' and IDCOUNTER="+row_Contatori.IDCOUNTER;

                        //System.out.println(" --> selectReadNested_counters : "+selectReadNested);
                        Statement SQLStatement_1 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                        ResultSet rsNested  = ADAMS_GlobalParam.connect_Oracle.Query_RS(selectReadNested,SQLStatement_1);
                        if(rsNested != null)
                        {
                            try
                            {
                                while (rsNested.next())
                                {
                                    ADAMS_TAB_COUNTERKIT counterKit = new ADAMS_TAB_COUNTERKIT();
                                    counterKit.TRIGGERFIELD = rsNested.getInt(nomeCampi[0]); //TRIGGERFIELD
                                    counterKit.TRIGGERVALUE = rsNested.getInt(nomeCampi[1]); //TRIGGERVALUE
                                    counterKit.COUNTERINDEX = rsNested.getInt(nomeCampi[2]); //COUNTERINDEX                                   
                                    counterKit.COUNTERTYPE  = rsNested.getInt(nomeCampi[3]); //COUNTERTYPE
                                    counterKit.PERCENTOF    = rsNested.getInt(nomeCampi[4]); //PERCENTOF
                                    counterKit.TRIGGERCOUNTER  = rsNested.getInt(nomeCampi[5]); //TRIGGERCOUNTER
                                    
                                    String tag = rsNested.getString(nomeCampi[6]);    //TAG
                                    if(tag != null)
                                        counterKit.TAG      = tag;
                                    
                                    String desc = rsNested.getString(nomeCampi[7]);   //DESCRIPTION
                                    if(desc != null)
                                        counterKit.DESCRIPTION  = desc;
                                    
                                    row_Contatori.V_COUNTERKIT.addElement(counterKit);
                                    
                                }
                            }catch (Exception ex) 
                            {
                                System.out.println(ex);
                            } 
                        }else
                        {
                            System.out.println("rs == null");
                        }

                        try
                        {
                            SQLStatement_1.close();
                        }
                        catch(java.sql.SQLException exc) 
                        {
                            System.out.println("(ADAMS_JP_Counters-2) SQL Operation " + exc.toString());
                        }
                        
                        V_Counters.addElement(row_Contatori);
                    }
                }catch (Exception ex) 
                { 
                    System.out.println(ex.toString());
                    Thread_Work = false;
                }
            }
            //System.out.println("V_Counters SIZE "+V_Counters.size());
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JP_Counters-1) SQL Operation " + exc.toString());
            }
        }
        Thread_Work = false;
    }
    
    private void setEnableCleanGUI_pluginActive(boolean flag, boolean clean)
    {               
        if(clean)
        {
            jl_IDCounterSel.setText("");  
            jTF_TagCountersKit.setText("");
            jL_PLName.setText("");
            jTF_SpecPath.setText("");
            jCBox_UsePL.setSelected(false);
            jrb_default.setSelected(true);
            jrb_specify.setSelected(false);
                
        }
         
        jl_IDCounterSel.setEnabled(flag);
        jTF_TagCountersKit.setEnabled(flag);
        jL_PLName.setEnabled(flag);
        jB_editPL.setEnabled(flag);
        jTF_SpecPath.setEnabled(flag);
        jCBox_UsePL.setEnabled(flag);
        jrb_default.setEnabled(flag);
        jrb_specify.setEnabled(flag);
        jl_IDCounterSel.setEnabled(flag);
    
    }
        
    private void setEnableGUI_Table(boolean flag)
    {
        jrb_add.setEnabled(flag);
        jrb_modify.setEnabled(flag);
        jrb_delete.setEnabled(flag);
        jrb_add.setSelected(true);
        jTable_Counters.clearSelection();
        jTable_Counters.setSelectionBackground(java.awt.Color.white);
        jTable_Counters.setEnabled(false);
        
    }
    private void setEnableCleanGUI_CounterSetup(boolean enable,boolean clean)
    {
        jCBox_TriggerField.setEnabled(enable);        
        jTF_TriggerValue.setEnabled(enable);
        jCBox_CountersIndex.setEnabled(enable);
        jCBox_CounterType.setEnabled(enable);
        jCBox_Of.setEnabled(enable);
        jCBox_TrigCounter.setEnabled(enable);
        jTF_Tag.setEnabled(enable);
        jTF_Description.setEnabled(enable);        
        
        if(clean)
        {
            jTF_TriggerValue.setText("");
            jTF_Tag.setText("");
            jTF_Description.setText("");
            
            try{
                jCBox_TriggerField.setSelectedIndex(0);    
            }catch(Exception e){}
            
            try{
                jCBox_CountersIndex.setSelectedIndex(0); 
            }catch(Exception e){}
            
            try{
                jCBox_CounterType.setSelectedIndex(0); 
            }catch(Exception e){}
            
            try{
                    jCBox_Of.setSelectedIndex(0);
                    jCBox_TrigCounter.setSelectedIndex(0);
            }catch(Exception e){}
            
            jb_commit.setEnabled(enable);
      }
        
        
    }
    
    private void setGlobalFont()
    {
        //Font Globali
        jL_title.setFont(ADAMS_GlobalParam.font_B12);
        jLabel10.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_CountersTag.setFont(ADAMS_GlobalParam.font_B10);
        jB_addCounter.setFont(ADAMS_GlobalParam.font_B10);
        jB_deleteCounter.setFont(ADAMS_GlobalParam.font_B10);
        jLabel11.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_UsePL.setFont(ADAMS_GlobalParam.font_B10);
        jLabel1.setFont(ADAMS_GlobalParam.font_B10);
        jL_PLName.setFont(ADAMS_GlobalParam.font_B10);
        jTF_SpecPath.setFont(ADAMS_GlobalParam.font_B10);
        jLabel7.setFont(ADAMS_GlobalParam.font_B10);
        jTF_TagCountersKit.setFont(ADAMS_GlobalParam.font_B10);
        jrb_specify.setFont(ADAMS_GlobalParam.font_B10);
        jrb_default.setFont(ADAMS_GlobalParam.font_B10);
        jrb_add.setFont(ADAMS_GlobalParam.font_B10);
        jrb_modify.setFont(ADAMS_GlobalParam.font_B10);
        jrb_delete.setFont(ADAMS_GlobalParam.font_B10);
        jLabel4.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_TriggerField.setFont(ADAMS_GlobalParam.font_B10);
        jLabel12.setFont(ADAMS_GlobalParam.font_B10);
        jTF_TriggerValue.setFont(ADAMS_GlobalParam.font_B10);
        jLabel13.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_CountersIndex.setFont(ADAMS_GlobalParam.font_B10);
        jLabel3.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_CounterType.setFont(ADAMS_GlobalParam.font_B10);
        jLabel14.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_Of.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_TrigCounter.setFont(ADAMS_GlobalParam.font_B10);
        jLabel6.setFont(ADAMS_GlobalParam.font_B10);
        jTF_Tag.setFont(ADAMS_GlobalParam.font_B10);
        jLabel5.setFont(ADAMS_GlobalParam.font_B10);
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);
        jTF_Description.setFont(ADAMS_GlobalParam.font_B10);
        jb_commit.setFont(ADAMS_GlobalParam.font_B10);
        jb_cancel.setFont(ADAMS_GlobalParam.font_B10);
        jL_IDCounter.setFont(ADAMS_GlobalParam.font_B10);
        jl_IDCounterSel.setFont(ADAMS_GlobalParam.font_B10);
    }

    
    private void jTable_CountersMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jrb_add.isSelected() == false)
            setSelected_jTableRow();
    }
    
    //////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames ={ "Tag",
                                        "Description",
                                        "Trigger Field",
                                        "Trigger Value",
                                        "Counter Index",
                                        "Counter Type",
                                        "% or Average Of",
                                        "Trigger Counter"};
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
    private javax.swing.JPanel jP_NORTH;
    private javax.swing.JLabel jL_title;
    private javax.swing.JPanel jP_centerN;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JComboBox jCBox_CountersTag;
    private javax.swing.JButton jB_addCounter;
    private javax.swing.JButton jB_deleteCounter;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jL_IDCounter;
    private javax.swing.JPanel jP_centerS;
    private javax.swing.JCheckBox jCBox_UsePL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTF_SpecPath;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTF_TagCountersKit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jrb_default;
    private javax.swing.JRadioButton jrb_specify;
    private javax.swing.JLabel jl_IDCounterSel;
    private javax.swing.JButton jB_editPL;
    private javax.swing.JLabel jL_PLName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jP_SOUTH;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JTextField jTF_TriggerValue;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JComboBox jCBox_CountersIndex;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox jCBox_CounterType;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JComboBox jCBox_Of;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTF_Tag;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTF_Description;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox jCBox_TriggerField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox jCBox_TrigCounter;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton jb_commit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jb_cancel;
    // End of variables declaration//GEN-END:variables
    
   
    private boolean DEBUG = false;
    private javax.swing.JTable jTable_Counters;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private int[] CellDimension = {130,190,140,60,70,85,70,70};
    private int minCellDimension = 30;
    
    private Vector V_Counters = new Vector();
    private ADAMS_TAB_KIT_CONTATORI KIT_COUNTER_SELECTED = null;
    private ADAMS_TAB_COUNTERKIT ST_COUNTERKIT_SEL = new ADAMS_TAB_COUNTERKIT();
    
    private VectorHelp V_CountersIndex  = null;
    private VectorHelp V_CountersType   = null;
    private VectorHelp V_PercentOf   = null;
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;
    private String str_none = "<none>";
    
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    
    // Info DB Counters
    private final String name_TabCounters = "tab_counters_kit";
    private final String item_KIT_CONTATORI = "IDCOUNTER,TAG,USEPLUGIN,PLUGINNAME,USEPATH,PATHNAME"; 
    
    // ------ Thread
    private Thread th           = null;
    private boolean Thread_Work = false;
    private int OperThread      = -1;
    private final int READ_ALLCOUNTERS        = 0; //readALLCountersKit
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private java.awt.event.ActionListener event_jCBox_CountersTag;
    
  
    /*jL_title;
    jLabel10;
    jLabel11;
    jLabel1;
    jL_PLName;
    jTF_SpecPath;
    jLabel7;     
    jTF_TagCountersKit;     
    jLabel4;     
    jLabel12;
    jTF_TriggerValue;
    jLabel13;     
    jLabel3;     
    jLabel14;   
    jLabel6;
    jTF_Tag;
    jLabel5;
    jTF_Description;
     jCBox_CountersTag;
    jB_addCounter;
    jB_deleteCounter;
    jCBox_UsePL;
    jrb_specify;
    jrb_default;
    jrb_add;
    jrb_modify;
    jrb_delete;
    jCBox_TriggerField;
    jCBox_CountersIndex;
    jCBox_CounterType;
    jCBox_Of;
    jb_commit;
    jb_cancel;*/

     
}
