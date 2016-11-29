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

public class ADAMS_JD_RegisterServerAlarm_DB extends javax.swing.JDialog {

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_RegisterServerAlarm_DB(java.awt.Frame parent, boolean modal, int width, int height, String str_Tipo_Conf) {
        //super(parent, modal);        
        super(parent, "- Handler Server -", java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        
        initComponents();
                
        for(int i=0; i<31; i++)
            jCBox_ServerID.addItem(""+i);
        
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames2 ={"Handler Server ID"};
        myTableModel_SA = new MyTableModel(all_columnNames2);
        sorter_SA = new TableSorter(myTableModel_SA);        
        jTable_SA = new javax.swing.JTable(sorter_SA);
        jTable_SA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            
        JTableHeader header = jTable_SA.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);
        sorter_SA.setTableHeader(header); 
        
        // int [] columnsNoSort ={1};
        //sorter_SA.setDisabledColumnSort(columnsNoSort); //disabilito il sort alla colonna columnsNoSort[...]
        
        jTable_SA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_SA.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_SA.setRowHeight(20);
        jTable_SA.setRowMargin(2);
        jTable_SA.setAutoCreateColumnsFromModel(false);
        jTable_SA.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        
        jTable_SA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_SAMouseReleased(evt);
            }
        });
        
        jScrollPane_PL.setViewportView(jTable_SA);
                
        this.str_TIPO_DI_CONFIGURAZIONE = str_Tipo_Conf;         
        jL_Title.setText("Alarm Server Declares - "+str_TIPO_DI_CONFIGURAZIONE+" -");
        
        jTextArea_AS_Desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,256));
        
        jL_Title.setFont(ADAMS_GlobalParam.font_B12);
        jTextArea_AS_Desc.setFont(ADAMS_GlobalParam.font_P10);
        jText_ID_Relation.setFont(ADAMS_GlobalParam.font_P10);
        jCBox_ServerID.setFont(ADAMS_GlobalParam.font_B11);
        jB_MasterToProduction.setFont(ADAMS_GlobalParam.font_B11);

        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);        
        jB_cancel.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        jb_ViewAll.setCursor(Cur_hand);
        jB_MasterToProduction.setCursor(Cur_hand);
        
        SetTable();       
        AddRowJTabel();
        setSelected_jTableRow();
        
        setCenteredFrame(width,height);
        
        
        // LOCK        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            jTextArea_AS_Desc.setEnabled(false);
            jText_ID_Relation.setEnabled(false);
            jCBox_ServerID.setEnabled(false);
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
        for(int i=0; i<jTable_SA.getColumnCount(); i++)
        {
            column = jTable_SA.getColumnModel().getColumn(i);     

            column.setMinWidth(30);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        jScrollPane_PL.setViewportView(jTable_SA);
        this.setCursor(Cur_default);
    }
    
    
    public void read_AlarmServer()
    {
        this.setCursor(Cur_wait);
        V_ALL_SERVER = new Vector();        
        String typeConfig       = str_TIPO_DI_CONFIGURAZIONE;
        
        //Costruzione Stringa
        String sel_AlarmServerRead = "select distinct SERVER_ID,DESCRIPTION";
        sel_AlarmServerRead = sel_AlarmServerRead+ " from tab_alarm_server_master";        
        sel_AlarmServerRead = sel_AlarmServerRead+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' order by SERVER_ID";
        
        //System.out.println("sel sel_AlarmServerRead ----> "+sel_AlarmServerRead);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_AlarmServerRead,SQLStatement);
        
        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {   
                    int serverID = rs_simple.getInt("SERVER_ID"); 
                    String desc = rs_simple.getString("DESCRIPTION"); 
                    
                    if(desc == null)
                        desc = "";
                                  
                    String str_select_IdAlarm = "select distinct ID_ALARM_RELATION from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and SERVER_ID="+serverID+" order by ID_ALARM_RELATION";
                    //System.out.println("str_select_IdAlarm ----> "+str_select_IdAlarm);
                    Vector v_ID_AlarmRelation = ADAMS_GlobalParam.connect_Oracle.Query(str_select_IdAlarm);
                    
                    AlarmServerRow AL_Row = new AlarmServerRow(typeConfig,serverID,desc,v_ID_AlarmRelation);
                    V_ALL_SERVER.addElement(AL_Row);
                 }
            }
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JD_RegisterServerAlarm_DB-1) SQL Operation " + exc.toString());
            }
            
            
        }else
        {
            System.out.println("rs==null");
        }
        this.setCursor(Cur_default);
    }
    
    private void AddRowJTabel()
    {
        jTable_SA.setCursor(Cur_wait);

        read_AlarmServer();
        int NUM_ROWS = V_ALL_SERVER.size();
              
        Object[][] dataValues = new Object[NUM_ROWS][1];
        for(int x=0; x<NUM_ROWS; x++)
        {            
            AlarmServerRow as_Row = (AlarmServerRow)V_ALL_SERVER.elementAt(x);       
            dataValues[x][0] = new Integer(as_Row.SERVER_ID);         
        }
        
        myTableModel_SA.setDataValues(dataValues);
        sorter_SA.setTableModel(myTableModel_SA);
        //sorter_SA.setSortingStatus(0,1); // ordino per tag
        
        jScrollPane_PL.setViewportView(jTable_SA);
        jTable_SA.setCursor(Cur_default);
    }
       
    private void jTable_SAMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jTable_SA.isEnabled())
        {
            if(jrb_add.isSelected() == false)
                setSelected_jTableRow();
        }
    }
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jP_north = new javax.swing.JPanel();
        jL_Title = new javax.swing.JLabel();
        jb_ViewAll = new javax.swing.JButton();
        jB_MasterToProduction = new javax.swing.JButton();
        jP_Center = new javax.swing.JPanel();
        jScrollPane_PL = new javax.swing.JScrollPane();
        jScroll_DESC = new javax.swing.JScrollPane();
        jTextArea_AS_Desc = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCBox_ServerID = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jText_ID_Relation = new javax.swing.JTextArea();
        jP_option = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jB_commit = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jP_north.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_north.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exec.png")));
        jL_Title.setText("Handler Server");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 40));
        jL_Title.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        jP_north.add(jL_Title, gridBagConstraints1);
        
        jb_ViewAll.setBackground(new java.awt.Color(230, 230, 230));
        jb_ViewAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png")));
        jb_ViewAll.setText("View All");
        jb_ViewAll.setToolTipText("View All");
        jb_ViewAll.setBorderPainted(false);
        jb_ViewAll.setContentAreaFilled(false);
        jb_ViewAll.setFocusPainted(false);
        jb_ViewAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jb_ViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ViewAllActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        jP_north.add(jb_ViewAll, gridBagConstraints1);
        
        jB_MasterToProduction.setBackground(new java.awt.Color(230, 230, 230));
        jB_MasterToProduction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/conf.png")));
        jB_MasterToProduction.setText("Set to Production");
        jB_MasterToProduction.setFocusPainted(false);
        jB_MasterToProduction.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jB_MasterToProduction.setMinimumSize(new java.awt.Dimension(160, 24));
        jB_MasterToProduction.setPreferredSize(new java.awt.Dimension(160, 24));
        jB_MasterToProduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_MasterToProductionActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        jP_north.add(jB_MasterToProduction, gridBagConstraints1);
        
        getContentPane().add(jP_north, java.awt.BorderLayout.NORTH);
        
        jP_Center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jP_Center.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_PL.setPreferredSize(new java.awt.Dimension(90, 200));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridheight = 3;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 0);
        gridBagConstraints6.weightx = 0.3;
        gridBagConstraints6.weighty = 1.0;
        jP_Center.add(jScrollPane_PL, gridBagConstraints6);
        
        jScroll_DESC.setBackground(new java.awt.Color(230, 230, 230));
        jTextArea_AS_Desc.setLineWrap(true);
        jTextArea_AS_Desc.setWrapStyleWord(true);
        jTextArea_AS_Desc.setBorder(new javax.swing.border.TitledBorder(" Description "));
        jTextArea_AS_Desc.setDisabledTextColor(java.awt.Color.gray);
        jTextArea_AS_Desc.setPreferredSize(new java.awt.Dimension(250, 15));
        jScroll_DESC.setViewportView(jTextArea_AS_Desc);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 0.5;
        jP_Center.add(jScroll_DESC, gridBagConstraints6);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder("Handler Server ID "));
        jLabel1.setText("Server ID");
        jLabel1.setMaximumSize(new java.awt.Dimension(80, 22));
        jLabel1.setMinimumSize(new java.awt.Dimension(80, 22));
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanel1.add(jLabel1, gridBagConstraints3);
        
        jCBox_ServerID.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_ServerID.setMaximumSize(new java.awt.Dimension(60, 22));
        jCBox_ServerID.setMinimumSize(new java.awt.Dimension(60, 22));
        jCBox_ServerID.setPreferredSize(new java.awt.Dimension(60, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanel1.add(jCBox_ServerID, gridBagConstraints3);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 3, 5, 5);
        jP_Center.add(jPanel1, gridBagConstraints6);
        
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jText_ID_Relation.setBackground(new java.awt.Color(230, 230, 230));
        jText_ID_Relation.setEditable(false);
        jText_ID_Relation.setLineWrap(true);
        jText_ID_Relation.setWrapStyleWord(true);
        jText_ID_Relation.setBorder(new javax.swing.border.TitledBorder("Managed Relations "));
        jText_ID_Relation.setDisabledTextColor(java.awt.Color.gray);
        jText_ID_Relation.setPreferredSize(new java.awt.Dimension(250, 15));
        jScrollPane1.setViewportView(jText_ID_Relation);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 2;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        jP_Center.add(jScrollPane1, gridBagConstraints6);
        
        jP_option.setBackground(new java.awt.Color(230, 230, 230));
        jP_option.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        
        jSeparator1.setMinimumSize(new java.awt.Dimension(50, 40));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 4));
        jP_option.add(jSeparator1);
        
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
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });
        
        jP_option.add(jB_commit);
        
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
        
        jP_option.add(jB_close);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 3;
        gridBagConstraints6.gridwidth = 3;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        jP_Center.add(jP_option, gridBagConstraints6);
        
        getContentPane().add(jP_Center, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private void jB_MasterToProductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_MasterToProductionActionPerformed
                
        ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"ATTENZIONE: la modifica della configurazione degli Handler Server comporta l'annullamento degli allarmi.\n"+
                                                 "\nSei Sicuro di voler esportare in produzione gli Handler Server della configurazione: "+this.str_TIPO_DI_CONFIGURAZIONE+"?","Warning",6,300,300);
        
        
        int Yes_No = op.getAnswer();
        if(Yes_No == 1)
        { 
            //System.out.println("Copia Tabella ---> PRODUCTION");
            
            try
            {        
               ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false); 
                //System.out.println(" setAutoCommit(false) ");
            }
            catch(java.sql.SQLException exc){exc.printStackTrace();}
            
            //**************
            
            String str_delete = "delete from tab_alarm_server_production where TIPO_DI_CONFIGURAZIONE='"+this.str_TIPO_DI_CONFIGURAZIONE+"'";
            //System.out.println("str_delete --> "+str_delete);
            int answer_sql = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);

            if(answer_sql == 0)
            {
                String str_delete_1 = "delete from tab_alarm_server_production where TIPO_DI_CONFIGURAZIONE not in(select distinct TIPO_DI_CONFIGURAZIONE from tab_alarm_server_master)";
               //System.out.println("str_delete_1 --> "+str_delete_1);
                answer_sql = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete_1);
            }
            
            String str_ins = "insert into tab_alarm_server_production select * from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+this.str_TIPO_DI_CONFIGURAZIONE+"'";
           //System.out.println("str_ins --> "+str_ins);
            answer_sql = ADAMS_GlobalParam.connect_Oracle.Operation(str_ins);
        
            //**************
            try
            {         
                if(answer_sql >= 0)
                {                
                    ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();                    
                    ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(this.str_TIPO_DI_CONFIGURAZIONE);                     
                   //System.out.println("Commit OK");
                }
                else
                {
                    ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                   //System.out.println("rollback()");
                }

                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true); 
            }
            catch(java.sql.SQLException exc)
            {
                exc.printStackTrace();
                try
                { 
                    ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
                }
                catch(java.sql.SQLException exc1){System.out.println("Errore setAutoCommit(true)");}
            }
        }
    }//GEN-LAST:event_jB_MasterToProductionActionPerformed

    private void jb_ViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ViewAllActionPerformed
        // Add your handling code here:
        
        if(view == null)
            view = new ADAMS_JF_ViewServerAlarm_DB();
        else
            view.show_frame();
        
    }//GEN-LAST:event_jb_ViewAllActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
   
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            AddRowJTabel();
            setSelected_jTableRow();  
            return;
        }

        if(AS_ROW_SELECTED == null)
        {
            ADAMS_GlobalParam.optionPanel(this,"You have to select an item first.","INFO",3);
            return;
        }
        
        if(jrb_delete.isSelected())
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"About to delete Alarm Server "+AS_ROW_SELECTED.SERVER_ID+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                int answer_del = AS_ROW_SELECTED.deleteAS();
                if(answer_del >= 0)
                {
                    String strRow = "row.";
                    if(answer_del > 1)
                        strRow = "rows.";
                    
                    ADAMS_GlobalParam.optionPanel(this,"Alarm Server "+AS_ROW_SELECTED.SERVER_ID+" has been deleted ("+answer_del+" "+strRow+").","INFO",3);
                    AddRowJTabel();
                    setSelected_jTableRow();
                    setEnabledClean_AllGui(false,true);

                    //System.out.println("Cancellare Alarm Server abilitati nei TE?????" - NO è solo un HELP);                
                }
                else
                    ADAMS_GlobalParam.optionPanel(this,"Operation failed.","ERROR",1);
            }// end Yes_No == 1 Delete            
        }// end jrb_delete.isSelected()
        else
        {
            int old_Server_ID = AS_ROW_SELECTED.SERVER_ID;
            
            AS_ROW_SELECTED.SERVER_ID = jCBox_ServerID.getSelectedIndex();
            AS_ROW_SELECTED.DESCRIPTION = jTextArea_AS_Desc.getText().trim();
            AS_ROW_SELECTED.TIPO_DI_CONFIGURAZIONE = str_TIPO_DI_CONFIGURAZIONE;
            
            if(jrb_add.isSelected())
            {                   
                int Answer_InsertTE = AS_ROW_SELECTED.insertAS();
                
                if(Answer_InsertTE == -10)
                {
                    ADAMS_GlobalParam.optionPanel(this,"Alarm Server "+AS_ROW_SELECTED.SERVER_ID+" is already in this and other configurations.","WARNING",4);                    
                    
                }
                else if(Answer_InsertTE == 1)
                {
                    ADAMS_GlobalParam.optionPanel(this,"Alarm Server "+AS_ROW_SELECTED.SERVER_ID+" has been added.","INFO",3);
                    AddRowJTabel();
                    setSelected_jTableRow();                    
                }
                else
                    ADAMS_GlobalParam.optionPanel(this,"Cannot insert Alarm Server "+AS_ROW_SELECTED.SERVER_ID+".","ERROR",1);
            }
            else //jrb_modify.isSelected()
            {                
                ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"About to change Alarm Server "+AS_ROW_SELECTED.SERVER_ID+". Confirm ?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    int answer_up = AS_ROW_SELECTED.updateAs(old_Server_ID);
                    if(answer_up == -10)
                    {
                        ADAMS_GlobalParam.optionPanel(this,"Alarm Server "+AS_ROW_SELECTED.SERVER_ID+" is already in this and other configurations.","WARNING",4);
                        AS_ROW_SELECTED.SERVER_ID = old_Server_ID;
                    }                    
                    else if(answer_up >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(this,"Alarm Server "+AS_ROW_SELECTED.SERVER_ID+" has been changed.","INFO",3);
                        AddRowJTabel();                           
                        jTable_SA.setEnabled(true);
                        setSelected_jTableRow();
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(this,"Operation failed.","ERROR",1);
                }
            }
        }
        
        
        
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        jTable_SA.setEnabled(true);
        jTable_SA.setSelectionBackground(java.awt.Color.red);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
        jTable_SA.setEnabled(true);
        jTable_SA.setSelectionBackground(java.awt.Color.green.darker());
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_modifyActionPerformed
  
    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        jTable_SA.setSelectionBackground(java.awt.Color.white);
        jTable_SA.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addActionPerformed

    private void setSelected_jTableRow()
    {        
        this.setCursor(Cur_wait); 
        int selectedRow = jTable_SA.getSelectedRow();
        
        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                int serverID = ((Integer)jTable_SA.getValueAt(selectedRow,0)).intValue();

                jText_ID_Relation.setText("");
                for(int i=0; i<V_ALL_SERVER.size();i++)
                {
                    AS_ROW_SELECTED = (AlarmServerRow)V_ALL_SERVER.elementAt(i);
                    if(AS_ROW_SELECTED.SERVER_ID == serverID)
                    {
                        jCBox_ServerID.setSelectedIndex(AS_ROW_SELECTED.SERVER_ID);
                        jTextArea_AS_Desc.setText(AS_ROW_SELECTED.DESCRIPTION);

                        int SIZE = AS_ROW_SELECTED.V_ID_ALARM_RELATION.size();
                        for(int x=0; x<SIZE; x++)
                        {
                            try
                            {
                                int id_REL = new Integer((String)AS_ROW_SELECTED.V_ID_ALARM_RELATION.elementAt(x)).intValue();
                                if(id_REL <= 0)
                                {
                                    jText_ID_Relation.setText(" - NOT FOUND -");
                                    break;
                                }
                                else
                                {
                                    // ***********

                                    //select a.TAG,b.ID_ELEMENT,b.POSITION_ELEMENT from tab_config a,TAB_ALARM_RELATION_ELEMENTS b where
                                    /*select a.TAG from tab_config a,TAB_ALARM_RELATION_ELEMENTS b where
                                    a.TIPO_DI_CONFIGURAZIONE='N.T.M.-Voce-Standard' and
                                    a.TIPO_DI_CONFIGURAZIONE=b.TIPO_DI_CONFIGURAZIONE and
                                    b.ID_ALARM_RELATION=23 and
                                    a.POSIZIONE_ELEMENTO=b.ID_ELEMENT
                                    order by b.POSITION_ELEMENT;*/
                                    
                                    
                                    String sel_REL_elemName =   "select a.TAG from tab_config a,TAB_ALARM_RELATION_ELEMENTS b where "+
                                                                "a.TIPO_DI_CONFIGURAZIONE='"+str_TIPO_DI_CONFIGURAZIONE+"' and "+
                                                                "a.TIPO_DI_CONFIGURAZIONE=b.TIPO_DI_CONFIGURAZIONE and "+
                                                                "b.ID_ALARM_RELATION="+id_REL+" and "+
                                                                "a.POSIZIONE_ELEMENTO=b.ID_ELEMENT "+
                                                                "order by b.POSITION_ELEMENT";
                                    
                                    //System.out.println(sel_REL_elemName);                                    
                                    Vector vRel_ElementName = ADAMS_GlobalParam.connect_Oracle.Query(sel_REL_elemName);
                                    
                                    String symbol_rel = "::";
                                    String str_relName = "";
                                    int num_TE = vRel_ElementName.size();
                                    for(int t=0; t<num_TE; t++)
                                    {
                                        if(t == (num_TE-1))
                                            symbol_rel = "";

                                         str_relName = str_relName+((new String( ((String)vRel_ElementName.elementAt(t)))).trim())+symbol_rel;

                                    }                                    
                                    // ***********
                                    jText_ID_Relation.append("["+id_REL+"] "+str_relName+"\n");
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                jText_ID_Relation.setText(" - ERROR ID RELATION ALARM ASSOCIATES -");
                            }
                        }                         
                        break;
                    }
                }
                jText_ID_Relation.setCaretPosition(0); 
                jTextArea_AS_Desc.setCaretPosition(0);             
            }
            else
            {
                AS_ROW_SELECTED = null;
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
            jTable_SA.setEnabled(false);
            setEnabledClean_AllGui(true,true);
            AS_ROW_SELECTED = new AlarmServerRow();
        }
        this.setCursor(Cur_default);        
    }
    
    private void setEnabledClean_AllGui(boolean enable,boolean clean)
    {        
        jCBox_ServerID.setEnabled(enable);
        jTextArea_AS_Desc.setEnabled(enable);
        jText_ID_Relation.setEnabled(enable);
        
        if(clean)
        {
            jCBox_ServerID.setSelectedIndex(0);
            jTextArea_AS_Desc.setText("");
            jText_ID_Relation.setText("");
        }
    }
    
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
       jTable_SA.clearSelection();
       setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
       close_Window();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
       close_Window();
    }//GEN-LAST:event_closeDialog
    
    private void  close_Window()
    {
        //System.out.println("Dim Windows width="+this.getSize().width+" height="+this.getSize().height);
        if(view != null)
        {
            view.setVisible(false);
            view.dispose();
            view = null;
        }
        
        setVisible(false);
        dispose();        
    }
    
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

    class AlarmServerRow
    {               
        String TIPO_DI_CONFIGURAZIONE;        
        int SERVER_ID;
        String DESCRIPTION;
        Vector V_ID_ALARM_RELATION;
        
        AlarmServerRow()
        {
            TIPO_DI_CONFIGURAZIONE  = "";
            SERVER_ID               = -1;  
            DESCRIPTION             = "";
            V_ID_ALARM_RELATION     = new Vector(); 
        }
        
        AlarmServerRow(String ConfName, int Server_ID, String Desc, Vector V_ID_AR)
        {
            TIPO_DI_CONFIGURAZIONE = new String(ConfName);
            SERVER_ID           = Server_ID;  
            DESCRIPTION         = new String(Desc);
            V_ID_ALARM_RELATION = V_ID_AR;
        }

        private int ctrl_existServer()
        {
            String sel_AlarmServerExist = "select count(*) from tab_alarm_server_master where SERVER_ID="+SERVER_ID+"";
            int num_server = ADAMS_GlobalParam.connect_Oracle.Query_int(sel_AlarmServerExist);        
            return num_server;
        }
        
        private int ctrl_existServer(int serverID)
        {
            String sel_AlarmServerExist = "select count(*) from tab_alarm_server_master where SERVER_ID="+serverID+"";
            int num_server = ADAMS_GlobalParam.connect_Oracle.Query_int(sel_AlarmServerExist);        
            return num_server;
        }
        
        
        public int deleteAS()
        {        
            String str_delete = "delete from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and SERVER_ID="+SERVER_ID+"";

            int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);
        
            if(Answer_del > 0)
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);
        
            return Answer_del;
        }
        public int insertAS()
        {
            int num_server = ctrl_existServer();
            
            if(num_server == 0)
            {            
                String descrizione = DESCRIPTION.replaceAll("'","''"); //se presente l'apice va messo doppio per Sql Oracle

                String str_Insert = ("insert into tab_alarm_server_master (TIPO_DI_CONFIGURAZIONE,SERVER_ID,DESCRIPTION,ID_ALARM_RELATION)"+
                                        " values ('"+TIPO_DI_CONFIGURAZIONE+"',"+SERVER_ID+",'"+DESCRIPTION+"',-1)");
                //System.out.println(str_Insert);
                int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert);

                if(Answer_Ins > 0)
                    ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE); 

                return Answer_Ins;
            }
            else
                return -10;

                
        }
        public int updateAs(int old_Server_ID)
        {               
            if( old_Server_ID != SERVER_ID )
            {
                 if(ctrl_existServer(SERVER_ID) > 0)
                     return -10;
            }
            
            String descrizione = DESCRIPTION.replaceAll("'","''"); //se presente l'apice va messo doppio per Sql Oracle
            
            String str_update =  "update tab_alarm_server_master set SERVER_ID="+SERVER_ID+","+
                                    "DESCRIPTION='"+descrizione+"' "+                                        
                                    "where TIPO_DI_CONFIGURAZIONE='"+TIPO_DI_CONFIGURAZIONE+"' and SERVER_ID="+old_Server_ID+"";

            //System.out.println(str_update);
            int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);

            if(Answer_update > 0)
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(TIPO_DI_CONFIGURAZIONE);

            return Answer_update;         
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jP_north;
    private javax.swing.JLabel jL_Title;
    private javax.swing.JButton jb_ViewAll;
    private javax.swing.JButton jB_MasterToProduction;
    private javax.swing.JPanel jP_Center;
    private javax.swing.JScrollPane jScrollPane_PL;
    private javax.swing.JScrollPane jScroll_DESC;
    private javax.swing.JTextArea jTextArea_AS_Desc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jCBox_ServerID;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jText_ID_Relation;
    private javax.swing.JPanel jP_option;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables
    private boolean DEBUG = false;
    
    private String str_TIPO_DI_CONFIGURAZIONE = null;
        
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_SA;
    private TableSorter sorter_SA;
    private MyTableModel myTableModel_SA;
    private Vector V_ALL_SERVER;
    private AlarmServerRow AS_ROW_SELECTED;
    
    private ADAMS_JF_ViewServerAlarm_DB view = null;
}
