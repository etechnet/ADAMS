/*
 * ADAMS_JD_ViewRelations.java
 *
 * Created on 28 ottobre 2005, 11.05
 */

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
import java.awt.Color;
import java.util.StringTokenizer;
import java.sql.*;

public class ADAMS_JD_RegisterPlugin_DB extends javax.swing.JDialog {

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_RegisterPlugin_DB(java.awt.Frame parent, boolean modal, int width, int height, String str_Tipo_Conf) {
        super(parent, modal);
        initComponents();
                
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames2 ={"Plugin Name"};
        myTableModel_PL = new MyTableModel(all_columnNames2);
        sorter_PL = new TableSorter(myTableModel_PL);        
        jTable_PL = new javax.swing.JTable(sorter_PL);
        jTable_PL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            
        JTableHeader header = jTable_PL.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);
        sorter_PL.setTableHeader(header); 
        
        // int [] columnsNoSort ={1};
        //sorter_PL.setDisabledColumnSort(columnsNoSort); //disabilito il sort alla colonna columnsNoSort[...]
        
        jTable_PL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_PL.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_PL.setRowHeight(20);
        jTable_PL.setRowMargin(2);
        jTable_PL.setAutoCreateColumnsFromModel(false);
        jTable_PL.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        
        jTable_PL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_PLMouseReleased(evt);
            }
        });
        
        jScrollPane_PL.setViewportView(jTable_PL);
                
        this.str_TIPO_DI_CONFIGURAZIONE = str_Tipo_Conf;         
        this.setTitle("Plugin Library");
        jL_Title.setText("Plugins Declares - "+str_TIPO_DI_CONFIGURAZIONE+" -");
        
        jT_PL_Name.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        
        jL_Title.setFont(ADAMS_GlobalParam.font_B12);
        jTextArea_PL_Desc.setFont(ADAMS_GlobalParam.font_P10); 
        jT_PL_Name.setFont(ADAMS_GlobalParam.font_B11);

        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);        
        jB_cancel.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        
        SetTable();       
        AddRowJTabel();
        setSelected_jTableRow();
        
        setCenteredFrame(width,height);
        
        
        // LOCK        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            jTextArea_PL_Desc.setEnabled(false);
            jT_PL_Name.setEnabled(false);
            jrb_add.setEnabled(false); 
            jrb_modify.setEnabled(false);
            jrb_delete.setEnabled(false);  
            jB_cancel.setEnabled(false);
        }
        
        //show();
        this.setVisible(true);
    }

    public void SetTable()
    {
        this.setCursor(Cur_wait);
        int [] CellDimension ={100};        
        
        TableColumn column = null;
        for(int i=0; i<jTable_PL.getColumnCount(); i++)
        {
            column = jTable_PL.getColumnModel().getColumn(i);     

            column.setMinWidth(30);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        jScrollPane_PL.setViewportView(jTable_PL);
        this.setCursor(Cur_default);
    }
    
    
    public void read_Plugins()
    {
        this.setCursor(Cur_wait);
        V_ALL_PLUGIN = new Vector();        
        String typeConfig       = str_TIPO_DI_CONFIGURAZIONE;
        
        //Costruzione Stringa
        String sel_readPlugins = "select NOME_PLUGIN,DESCRIZIONE";
        sel_readPlugins = sel_readPlugins+ " from  l_plugin_library";        
        sel_readPlugins = sel_readPlugins+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' order by NOME_PLUGIN";
        
         //System.out.println("sel PluginRow ----> "+sel_readPlugins);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_readPlugins,SQLStatement);
        
        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {   
                    String namePl = rs_simple.getString("NOME_PLUGIN"); 
                    String DescPl = rs_simple.getString("DESCRIZIONE");
                               
                    if(DescPl == null)
                        DescPl = "";
                                        
                    PluginRow pluginRow = new PluginRow(namePl,typeConfig,DescPl);
                    V_ALL_PLUGIN.addElement(pluginRow);
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
                System.out.println("(ADAMS_JD_RegisterPlugin_DB-1) SQL Operation " + exc.toString());
            }
            
            
        }else
        {
            System.out.println("rs==null");
        }
        this.setCursor(Cur_default);
    }
    
    private void AddRowJTabel()
    {
        jTable_PL.setCursor(Cur_wait);

        read_Plugins();
        int NUM_ROWS = V_ALL_PLUGIN.size();
              
        Object[][] dataValues = new Object[NUM_ROWS][1];
        for(int x=0; x<NUM_ROWS; x++)
        {            
            PluginRow pl_Row = (PluginRow)V_ALL_PLUGIN.elementAt(x);       
            dataValues[x][0] = (String)pl_Row.NOME_PLUGIN;         
        }
        
        myTableModel_PL.setDataValues(dataValues);
        sorter_PL.setTableModel(myTableModel_PL);
        //sorter_PL.setSortingStatus(0,1); // ordino per tag
        
        jScrollPane_PL.setViewportView(jTable_PL);
        jTable_PL.setCursor(Cur_default);
    }
       
    private void jTable_PLMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jTable_PL.isEnabled())
        {
            if(jrb_add.isSelected() == false)
                setSelected_jTableRow();
        }
    }
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jL_Title = new javax.swing.JLabel();
        jP_Center = new javax.swing.JPanel();
        jScrollPane_PL = new javax.swing.JScrollPane();
        jScroll_DESC = new javax.swing.JScrollPane();
        jTextArea_PL_Desc = new javax.swing.JTextArea();
        jP_option = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jT_PL_Name = new javax.swing.JTextField();
        jP_south = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plugin_32.png")));
        jL_Title.setText("Plugins Declares");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 40));
        jL_Title.setOpaque(true);
        getContentPane().add(jL_Title, java.awt.BorderLayout.NORTH);
        
        jP_Center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_Center.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_PL.setPreferredSize(new java.awt.Dimension(90, 200));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridheight = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 0);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_Center.add(jScrollPane_PL, gridBagConstraints1);
        
        jScroll_DESC.setBackground(new java.awt.Color(230, 230, 230));
        jTextArea_PL_Desc.setLineWrap(true);
        jTextArea_PL_Desc.setWrapStyleWord(true);
        jTextArea_PL_Desc.setBorder(new javax.swing.border.TitledBorder(" Description"));
        jTextArea_PL_Desc.setDisabledTextColor(java.awt.Color.gray);
        jTextArea_PL_Desc.setPreferredSize(new java.awt.Dimension(250, 15));
        jScroll_DESC.setViewportView(jTextArea_PL_Desc);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_Center.add(jScroll_DESC, gridBagConstraints1);
        
        jP_option.setBackground(new java.awt.Color(230, 230, 230));
        jP_option.setPreferredSize(new java.awt.Dimension(10, 40));
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
        
        jP_option.add(jrb_add);
        
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
        
        jP_option.add(jrb_modify);
        
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
        
        jP_option.add(jrb_delete);
        
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg")));
        jB_cancel.setToolTipText("Cancel");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setMaximumSize(new java.awt.Dimension(56, 28));
        jB_cancel.setMinimumSize(new java.awt.Dimension(56, 28));
        jB_cancel.setPreferredSize(new java.awt.Dimension(56, 28));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        
        jP_option.add(jB_cancel);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_Center.add(jP_option, gridBagConstraints1);
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(" Plugin Name "));
        jT_PL_Name.setDisabledTextColor(java.awt.Color.gray);
        jT_PL_Name.setPreferredSize(new java.awt.Dimension(4, 22));
        jT_PL_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_PL_NameKeyReleased(evt);
            }
        });
        
        jPanel1.add(jT_PL_Name);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 3, 5, 5);
        jP_Center.add(jPanel1, gridBagConstraints1);
        
        getContentPane().add(jP_Center, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_commit.setPreferredSize(new java.awt.Dimension(80, 28));
        jB_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jB_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jB_commit.setEnabled(false);
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_commit);
        
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMaximumSize(new java.awt.Dimension(60, 28));
        jB_close.setMinimumSize(new java.awt.Dimension(60, 28));
        jB_close.setPreferredSize(new java.awt.Dimension(60, 28));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_close);
        
        getContentPane().add(jP_south, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jT_PL_NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_PL_NameKeyReleased
        if(jT_PL_Name.getText().length() > 0)
            jB_commit.setEnabled(true);
        else
            jB_commit.setEnabled(false);
    }//GEN-LAST:event_jT_PL_NameKeyReleased

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
   
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            AddRowJTabel();
            setSelected_jTableRow();  
            return;
        }
        
        
        if(PLUGIN_ROW_SELECTED == null)
        {
            ADAMS_GlobalParam.optionPanel(this,"You have to select an item first.","INFO",3);
            return;
        }
        
        if(jrb_delete.isSelected())
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"About to delete Plugin "+PLUGIN_ROW_SELECTED.NOME_PLUGIN+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                int answer_del = PLUGIN_ROW_SELECTED.deletePlugin();
                if(answer_del >= 0)
                {
                    ADAMS_GlobalParam.optionPanel(this,"Plugin "+PLUGIN_ROW_SELECTED.NOME_PLUGIN+" has been deleted.","INFO",3);
                    AddRowJTabel();
                    setSelected_jTableRow();
                    setEnabledClean_AllGui(false,true);

                    //System.out.println("Cancellare Plugin abilitati nei TE?????" - NO è solo un HELP);                
                }
                else
                    ADAMS_GlobalParam.optionPanel(this,"Operation failed.","ERROR",1);
            }// end Yes_No == 1 Delete            
        }// end jrb_delete.isSelected()
        else
        {
            String oldNameForModify = new String(PLUGIN_ROW_SELECTED.NOME_PLUGIN);
            
            PLUGIN_ROW_SELECTED.NOME_PLUGIN = jT_PL_Name.getText().trim();
            PLUGIN_ROW_SELECTED.DESCRIZIONE = jTextArea_PL_Desc.getText().trim();
            PLUGIN_ROW_SELECTED.TIPO_DI_CONFIGURAZIONE = str_TIPO_DI_CONFIGURAZIONE;
            
            if(jrb_add.isSelected())
            {                                
                int Answer_InsertTE = PLUGIN_ROW_SELECTED.insertPlugin();
                if(Answer_InsertTE == 1)
                {
                    ADAMS_GlobalParam.optionPanel(this,"Plugin "+PLUGIN_ROW_SELECTED.NOME_PLUGIN+" has been added.","INFO",3);
                    AddRowJTabel();
                    setSelected_jTableRow();                    
                }
                else
                    ADAMS_GlobalParam.optionPanel(this,"Cannot insert Plugin "+PLUGIN_ROW_SELECTED.NOME_PLUGIN+".","ERROR",1);
            }
            else //jrb_modify.isSelected()
            {                
                ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"About to change Plugin "+PLUGIN_ROW_SELECTED.NOME_PLUGIN+". Confirm ?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    
                    int answer_up = PLUGIN_ROW_SELECTED.updatePlugin(oldNameForModify);
                    
                    if(answer_up >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(this,"Plugin "+PLUGIN_ROW_SELECTED.NOME_PLUGIN+" has been changed.","INFO",3);
                        AddRowJTabel();                           
                        jTable_PL.setEnabled(true);
                        setSelected_jTableRow();
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(this,"Operation failed.","ERROR",1);
                }
            }
        }
        
        
        
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        jTable_PL.setEnabled(true);
        jTable_PL.setSelectionBackground(java.awt.Color.red);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
        jTable_PL.setEnabled(true);
        jTable_PL.setSelectionBackground(java.awt.Color.green.darker());
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_modifyActionPerformed
  
    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        jTable_PL.setSelectionBackground(java.awt.Color.white);
        jTable_PL.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addActionPerformed

    private void setSelected_jTableRow()
    {        
        this.setCursor(Cur_wait); 
        int selectedRow = jTable_PL.getSelectedRow();
        
        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                String plSel = (String)jTable_PL.getValueAt(selectedRow,0);

                for(int i=0; i<V_ALL_PLUGIN.size();i++)
                {
                    PLUGIN_ROW_SELECTED = (PluginRow)V_ALL_PLUGIN.elementAt(i);
                    if(PLUGIN_ROW_SELECTED.NOME_PLUGIN.compareTo(plSel) == 0)
                    {
                         jT_PL_Name.setText(PLUGIN_ROW_SELECTED.NOME_PLUGIN);
                         jTextArea_PL_Desc.setText(PLUGIN_ROW_SELECTED.DESCRIZIONE);
                         break;
                    }
                }
                jTextArea_PL_Desc.setCaretPosition(0);            
            }
            else
            {
                PLUGIN_ROW_SELECTED = null;
                setEnabledClean_AllGui(false,true);
                this.setCursor(Cur_default);
                return;            
            }
            
            if(jrb_modify.isSelected())
                setEnabledClean_AllGui(true,false);
            else 
                setEnabledClean_AllGui(false,false);
            
        }
        else// (jrb_add.isSelected())
        {
            jTable_PL.setEnabled(false);
            setEnabledClean_AllGui(true,true);
            PLUGIN_ROW_SELECTED = new PluginRow();
        }
        this.setCursor(Cur_default);        
    }
    
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {
        jT_PL_Name.setEnabled(enable);
        jTextArea_PL_Desc.setEnabled(enable);
        
        if(clean)
        {
            jT_PL_Name.setText("");
            jTextArea_PL_Desc.setText("");
        }
        
        if(jT_PL_Name.getText().length() == 0)
            jB_commit.setEnabled(false);
        else
            jB_commit.setEnabled(true);
       
    }
    
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
       jTable_PL.clearSelection();
       setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
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

    class PluginRow
    {
        String NOME_PLUGIN;                              
        String TIPO_DI_CONFIGURAZIONE;                             
        String DESCRIZIONE;    
        
        PluginRow()
        {
            NOME_PLUGIN             = "";                             
            TIPO_DI_CONFIGURAZIONE  = "";                              
            DESCRIZIONE             = "";  
        }
        
        PluginRow(String plname,String ConfName, String plDesc)
        {
            NOME_PLUGIN             = new String(plname);                             
            TIPO_DI_CONFIGURAZIONE  = new String(ConfName);                              
            DESCRIZIONE             = new String(plDesc);  
        }

        public int deletePlugin()
        {        
            String str_delete = "delete from  l_plugin_library where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and NOME_PLUGIN='"+NOME_PLUGIN+"'";

            int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
        
            if(Answer_del > 0)
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
            return Answer_del;
        }
        public int insertPlugin()
        {
            String descrizione = DESCRIZIONE.replaceAll("'","''"); //se presente l'apice va messo doppio per Sql Oracle
            
            String str_Insert = ("insert into  l_plugin_library (NOME_PLUGIN,TIPO_DI_CONFIGURAZIONE,DESCRIZIONE)"+
                                    " values ('"+NOME_PLUGIN+"','"+TIPO_DI_CONFIGURAZIONE+"','"+descrizione+"')");
            //System.out.println(str_Insert);
            int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);
            
            if(Answer_Ins > 0)
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE); 
            
            return Answer_Ins;
        }
        public int updatePlugin(String oldNamePL)
        {   
            String descrizione = DESCRIZIONE.replaceAll("'","''"); //se presente l'apice va messo doppio per Sql Oracle
            
            String str_update =  "update  l_plugin_library set NOME_PLUGIN='"+NOME_PLUGIN+"',"+
                                    "DESCRIZIONE='"+descrizione+"' "+                                        
                                    "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and NOME_PLUGIN='"+oldNamePL+"'";;

            //System.out.println(str_update);
            int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);

            if(Answer_update > 0)
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);

            return Answer_update;
            
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jL_Title;
    private javax.swing.JPanel jP_Center;
    private javax.swing.JScrollPane jScrollPane_PL;
    private javax.swing.JScrollPane jScroll_DESC;
    private javax.swing.JTextArea jTextArea_PL_Desc;
    private javax.swing.JPanel jP_option;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jT_PL_Name;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables
    private boolean DEBUG = false;
    
    private String str_TIPO_DI_CONFIGURAZIONE = null;
        
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_PL;
    private TableSorter sorter_PL;
    private MyTableModel myTableModel_PL;
    private Vector V_ALL_PLUGIN;
    private PluginRow PLUGIN_ROW_SELECTED;
}
