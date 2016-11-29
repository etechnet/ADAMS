/*
 * ADAMS_JP_Ghost_Relations.java
 *
 * Created on 8 agosto 2005, 10.38
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
import java.util.StringTokenizer;
import java.sql.*;

public class ADAMS_JP_Ghost_Relations extends javax.swing.JPanel {

    /** Creates new form ADAMS_JP_Ghost_Relations */
    public ADAMS_JP_Ghost_Relations(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {

        initComponents();
        
        this.local_INFO_CONFIG = INFO_CONFIG;
        jLabel1.setText("Relation Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        
        jTF_text_Rel.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,256));
        
        // *** Costruzione JTABLE TE Rel ***/
        String[] all_columnNames2 ={"Tag Ghost Relations"};
        int [] cellDimen_REL ={100};
        myTableModel_REL = new MyTableModel(all_columnNames2);
        sorter_REL = new TableSorter(myTableModel_REL);
        
        jTable_REL = new javax.swing.JTable(sorter_REL);
        JTableHeader header2 = jTable_REL.getTableHeader();
        header2.setFont(ADAMS_GlobalParam.font_B10);
        header2.setReorderingAllowed(false);

        sorter_REL.setTableHeader(header2);        
        
        jTable_REL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_REL.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_REL.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_REL.setRowHeight(20);
        jTable_REL.setRowMargin(2);
        jTable_REL.setAutoCreateColumnsFromModel(false);
        jTable_REL.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jTable_REL.setSelectionBackground(java.awt.Color.yellow);
        
        jScrollPane_REL.setViewportView(jTable_REL);
        
        jTable_REL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_RELMouseReleased(evt);
            }
        });
        // **************************** //
                      
        //font Globali
        setGlobalFont();
       
         //Cursor mouse    
        jChBox_hexout.setCursor(Cur_hand);
        jChBox_network.setCursor(Cur_hand);
        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jB_analysis.setCursor(Cur_hand);
        jB_restriction.setCursor(Cur_hand);
        jB_ties.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);

        SetTable(jTable_REL,30,cellDimen_REL);
        
        str_help_add = ("Select an Element to add to your relation from the Traffic Element list. "+
                        "Click 'Add Element' button to concatenate it or press 'Delete Last' to revert operations. "+
                        "Please note that a relation is made by at least two (2) Traffic Elements. "+
                        "******** Aggiungere set parametri e Commit.");
        jTA_help.setText(str_help_add);
        
        jTable_REL.setSelectionBackground(Color.yellow);
        //setGuiInit();
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
    
    public void setTableConfig(ADAMS_Vector_TAB_CONFIG Parent_V_TAB_CONFIG)
    {
        if(ctrl_RelationGhost())
        {
            this.parent_V_TAB_CONFIG = Parent_V_TAB_CONFIG;
            jrb_add.setSelected(true);
            AddRowJTabel_REL();
            setGuiInit();
        }
        jB_commit.setEnabled(false);
    }

    private boolean ctrl_RelationGhost()
    {
        if(row_TAB_CONFIG_GHOST == null)
        {
            //System.out.println("Creo e inserisco TE_Ghost");
            row_TAB_CONFIG_GHOST = new ADAMS_TAB_CONFIG();

            row_TAB_CONFIG_GHOST.TIPO_DI_CONFIGURAZIONE   = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
            row_TAB_CONFIG_GHOST.POSIZIONE_ELEMENTO       = ADAMS_GlobalParam.TE_GHOST_POSIZIONE_ELEMENTO;
            row_TAB_CONFIG_GHOST.POSIZIONE_CAMPO_DR      = -1;
            row_TAB_CONFIG_GHOST.TAG                      = "TRAFFIC ELEMENT GHOST";
            row_TAB_CONFIG_GHOST.DESCRIPTION              = "TRAFFIC ELEMENT GHOST for RELATION GHOST";

            int Answer_Insert_GHOST_TE = row_TAB_CONFIG_GHOST.insert_TRAFFIC_ELEMENT(true);
            if(Answer_Insert_GHOST_TE != 1)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"ERROR insert GHOST Traffic Element","ERROR",1);
                return false;
            }
        }
        
        return true;
    }
    
    private void AddRowJTabel_REL()
    {               
        jTable_REL.setCursor(Cur_wait);
        
        row_TAB_CONFIG_GHOST.read_LISTA_RELAZIONI();
        
        Object[][] dataValues;
        if(row_TAB_CONFIG_GHOST == null)
        {
             dataValues = new Object[0][0];
        }
        else
        {          
            int SIZE = row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.size();               
            dataValues = new Object[SIZE][1];
            for(int i=0; i<SIZE; i++)
            {            
                ADAMS_TAB_RELAZIONI_ELEMENTO row_RELATION = (ADAMS_TAB_RELAZIONI_ELEMENTO)row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.elementAt(i);
                dataValues[i][0] = new String(row_RELATION.RELATION_NAME);
            }
            
        }
        myTableModel_REL.setDataValues(dataValues);
        sorter_REL.setTableModel(myTableModel_REL);
        
        jScrollPane_REL.setViewportView(jTable_REL);     
        jTable_REL.setCursor(Cur_default);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jP_center = new javax.swing.JPanel();
        jP_c1 = new javax.swing.JPanel();
        jScrollPane_REL = new javax.swing.JScrollPane();
        jP_c2 = new javax.swing.JPanel();
        jTA_help = new javax.swing.JTextArea();
        jPanel_south = new javax.swing.JPanel();
        jP_sc = new javax.swing.JPanel();
        jP_scs = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jP_analysis = new javax.swing.JPanel();
        jTF_analysis = new javax.swing.JTextField();
        jB_analysis = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jP_restriction = new javax.swing.JPanel();
        jTF_restriction = new javax.swing.JTextField();
        jB_restriction = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jP_ties = new javax.swing.JPanel();
        jTF_ties = new javax.swing.JTextField();
        jB_ties = new javax.swing.JButton();
        jChBox_network = new javax.swing.JCheckBox();
        jChBox_hexout = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTF_text_Rel = new javax.swing.JTextField();
        jP_ss = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jP_sn = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        
        
        setLayout(new java.awt.BorderLayout(2, 2));
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 5));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ghost_rel_32.png")));
        jLabel1.setText("Ghost Relation Build");
        jLabel1.setMinimumSize(new java.awt.Dimension(149, 40));
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 40));
        add(jLabel1, java.awt.BorderLayout.NORTH);
        
        jP_center.setLayout(new java.awt.GridLayout(1, 0));
        
        jP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setBorder(new javax.swing.border.TitledBorder(null, " Traffic Elements for Relation Build", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jP_c1.setLayout(new java.awt.GridLayout(1, 1));
        
        jP_c1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_REL.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_REL.setBorder(new javax.swing.border.TitledBorder(null, " Present Ghost Relations ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jP_c1.add(jScrollPane_REL);
        
        jP_center.add(jP_c1);
        
        jP_c2.setLayout(new java.awt.GridLayout(1, 0));
        
        jTA_help.setBackground(new java.awt.Color(230, 230, 230));
        jTA_help.setEditable(false);
        jTA_help.setLineWrap(true);
        jTA_help.setWrapStyleWord(true);
        jTA_help.setBorder(new javax.swing.border.TitledBorder(null, " Help ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jP_c2.add(jTA_help);
        
        jP_center.add(jP_c2);
        
        add(jP_center, java.awt.BorderLayout.CENTER);
        
        jPanel_south.setLayout(new java.awt.BorderLayout());
        
        jPanel_south.setBackground(new java.awt.Color(230, 230, 230));
        jPanel_south.setBorder(new javax.swing.border.TitledBorder(null, " Ghost Relation Single Operation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jPanel_south.setPreferredSize(new java.awt.Dimension(10, 165));
        jP_sc.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_sc.setBackground(new java.awt.Color(230, 230, 230));
        jP_scs.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_scs.setBackground(new java.awt.Color(230, 230, 230));
        jLabel8.setBackground(new java.awt.Color(230, 230, 230));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Owner Analysis");
        jLabel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_scs.add(jLabel8, gridBagConstraints2);
        
        jP_analysis.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_analysis.setBackground(new java.awt.Color(230, 230, 230));
        jTF_analysis.setBackground(new java.awt.Color(230, 230, 230));
        jTF_analysis.setEditable(false);
        jTF_analysis.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_analysis.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_analysis.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_analysis.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_analysis.add(jTF_analysis, gridBagConstraints3);
        
        jB_analysis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_analysis.setToolTipText("Owner Analysis");
        jB_analysis.setBorderPainted(false);
        jB_analysis.setContentAreaFilled(false);
        jB_analysis.setFocusPainted(false);
        jB_analysis.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_analysis.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_analysis.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_analysis.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_analysis.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_analysis.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_analysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_analysisActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        jP_analysis.add(jB_analysis, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_scs.add(jP_analysis, gridBagConstraints2);
        
        jLabel13.setBackground(new java.awt.Color(230, 230, 230));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Restrictions");
        jLabel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel13.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_scs.add(jLabel13, gridBagConstraints2);
        
        jP_restriction.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_restriction.setBackground(new java.awt.Color(230, 230, 230));
        jTF_restriction.setBackground(new java.awt.Color(230, 230, 230));
        jTF_restriction.setEditable(false);
        jTF_restriction.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_restriction.setToolTipText("");
        jTF_restriction.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_restriction.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_restriction.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_restriction.add(jTF_restriction, gridBagConstraints4);
        
        jB_restriction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_restriction.setToolTipText("Restriction");
        jB_restriction.setBorderPainted(false);
        jB_restriction.setContentAreaFilled(false);
        jB_restriction.setFocusPainted(false);
        jB_restriction.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_restriction.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_restriction.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_restriction.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_restriction.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_restriction.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_restriction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_restrictionActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        jP_restriction.add(jB_restriction, gridBagConstraints4);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_scs.add(jP_restriction, gridBagConstraints2);
        
        jLabel12.setBackground(new java.awt.Color(230, 230, 230));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ties Restrictions");
        jLabel12.setToolTipText("");
        jLabel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel12.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_scs.add(jLabel12, gridBagConstraints2);
        
        jP_ties.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_ties.setBackground(new java.awt.Color(230, 230, 230));
        jTF_ties.setBackground(new java.awt.Color(230, 230, 230));
        jTF_ties.setEditable(false);
        jTF_ties.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_ties.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_ties.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_ties.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints5.weightx = 1.0;
        jP_ties.add(jTF_ties, gridBagConstraints5);
        
        jB_ties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_ties.setToolTipText("Ties");
        jB_ties.setBorderPainted(false);
        jB_ties.setContentAreaFilled(false);
        jB_ties.setFocusPainted(false);
        jB_ties.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_ties.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_ties.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_ties.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_ties.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_ties.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_ties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_tiesActionPerformed(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        jP_ties.add(jB_ties, gridBagConstraints5);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_scs.add(jP_ties, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(6, 1, 1, 1);
        jP_sc.add(jP_scs, gridBagConstraints1);
        
        jChBox_network.setBackground(new java.awt.Color(230, 230, 230));
        jChBox_network.setText("Network");
        jChBox_network.setToolTipText("(True/False)");
        jChBox_network.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChBox_network.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jChBox_network.setMinimumSize(new java.awt.Dimension(40, 22));
        jChBox_network.setPreferredSize(new java.awt.Dimension(40, 22));
        jChBox_network.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_network.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jChBox_network.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_network.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridheight = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jP_sc.add(jChBox_network, gridBagConstraints1);
        
        jChBox_hexout.setBackground(new java.awt.Color(230, 230, 230));
        jChBox_hexout.setText("Hexadecimal Output");
        jChBox_hexout.setToolTipText("(True/False)");
        jChBox_hexout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jChBox_hexout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jChBox_hexout.setMinimumSize(new java.awt.Dimension(40, 22));
        jChBox_hexout.setPreferredSize(new java.awt.Dimension(40, 22));
        jChBox_hexout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_hexout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jChBox_hexout.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_hexout.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridheight = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jP_sc.add(jChBox_hexout, gridBagConstraints1);
        
        jLabel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ghost Relation Name");
        jLabel2.setToolTipText("");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jP_sc.add(jLabel2, gridBagConstraints1);
        
        jTF_text_Rel.setToolTipText("(Max 256 Characters)");
        jTF_text_Rel.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_text_Rel.setMinimumSize(new java.awt.Dimension(150, 22));
        jTF_text_Rel.setPreferredSize(new java.awt.Dimension(400, 22));
        jTF_text_Rel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_text_RelKeyReleased(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        jP_sc.add(jTF_text_Rel, gridBagConstraints1);
        
        jPanel_south.add(jP_sc, java.awt.BorderLayout.CENTER);
        
        jP_ss.setLayout(new java.awt.BorderLayout());
        
        jP_ss.setBackground(new java.awt.Color(230, 230, 230));
        jP_ss.setMinimumSize(new java.awt.Dimension(72, 20));
        jP_ss.setPreferredSize(new java.awt.Dimension(152, 40));
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
        
        jP_ss.add(jB_commit, java.awt.BorderLayout.EAST);
        
        jP_sn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jP_sn.setBackground(new java.awt.Color(230, 230, 230));
        jP_sn.setMinimumSize(new java.awt.Dimension(0, 30));
        jP_sn.setPreferredSize(new java.awt.Dimension(0, 30));
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
        jrb_add.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jrb_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_addActionPerformed(evt);
            }
        });
        
        jP_sn.add(jrb_add);
        
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
        
        jP_sn.add(jrb_modify);
        
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
        
        jP_sn.add(jrb_delete);
        
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
        
        jP_sn.add(jB_cancel);
        
        jP_ss.add(jP_sn, java.awt.BorderLayout.CENTER);
        
        jPanel_south.add(jP_ss, java.awt.BorderLayout.SOUTH);
        
        add(jPanel_south, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private void jTF_text_RelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_text_RelKeyReleased
        if(jTF_text_Rel.getText().trim().length() > 0)
            this.jB_commit.setEnabled(true);
        else
            this.jB_commit.setEnabled(false);
    }//GEN-LAST:event_jTF_text_RelKeyReleased

   
    private void jB_tiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_tiesActionPerformed
        // Add your handling code here:
        this.setCursor(Cur_wait);

        String title =" ( New Ghost Relation )";
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")!=0)
            title = " of Relation with name: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
        
        JListChooser listChooserTies = new JListChooser(ADAMS_GlobalParam.JFRAME_TAB, "Ties Restrictions"+title, true);
             
        // ******* Tutti i TE  ******
        int SIZE_ALL = parent_V_TAB_CONFIG.size();
        Object[][] l1= new Object[SIZE_ALL][2];
        for(int i=0; i<SIZE_ALL; i++)
        {            
            ADAMS_TAB_CONFIG appoRow = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);
            l1[i][0] = new Integer(appoRow.POSIZIONE_ELEMENTO); // ID
            l1[i][1] = appoRow.TAG;                             // Desc
        }
        // ******* END Tutti i TE  ******        
        
        /*int SIZE_ALL = row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.size();
        Object[][] l1= new Object[SIZE_ALL][2];
        for(int i=0; i<SIZE_ALL; i++)
        {            
            Integer pos_TE = (Integer)row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.elementAt(i);
            l1[i][0] = pos_TE;                                       // ID
            l1[i][1] = parent_V_TAB_CONFIG.getTagTE(pos_TE.intValue());      // Desc
        }*/
        
        // Selected Items  ////////
        int SIZE_SELECTED = row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.size();
        Object[][] l2 = new Object[SIZE_SELECTED][2];

        for(int i=0; i<SIZE_SELECTED; i++)
        {
            Integer elem_Row_Ties  = (Integer)row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.elementAt(i);
            l2[i][0] = elem_Row_Ties;                                       // ID
            l2[i][1] = parent_V_TAB_CONFIG.getTagTE(elem_Row_Ties.intValue());       // Desc
        }
        
        listChooserTies.setListChooserData(l1, l2);
        //listChooserTies.show();
        listChooserTies.setVisible(true);

        // read selected Items
        boolean empty = true;
        row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.removeAllElements();
        
        l2 = listChooserTies.getListChooserData();
        for(int i=0; i<l2.length; i++)
        {
            Integer ID_ties = ((Integer)(l2[i][0]));
            row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.addElement(ID_ties);
            empty = false;
            //System.out.println("USER DATA:"+l2[i][0]+" - "+l2[i][1]);            
        }
        
        listChooserTies.dispose();
        
        if(empty)
        {
            jTF_ties.setText(StrNotDefined);
            jTF_ties.setForeground(Color.red);
        }
        else
        {
            jTF_ties.setText(strDefined);
            jTF_ties.setForeground(Color.green.darker());
        }
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_tiesActionPerformed

    private void jB_restrictionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_restrictionActionPerformed
       this.setCursor(Cur_wait);

        String title =" ( New Ghost Relation )";
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")!=0)
            title = " of Relation with name: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
        
        JListChooser listChooserRestr = new JListChooser(ADAMS_GlobalParam.JFRAME_TAB, "Restrictions"+title, true);
     
       // Available Items //////// 
        Object[][] AllRel = new Object[parent_V_TAB_CONFIG.size()][2];
        int SIZE_ALL = AllRel.length;
                
        Object[][] l1= new Object[(SIZE_ALL-parent_V_TAB_CONFIG.countElementwith_DEFAULT_RESTRICTION())][2];
        int count = 0;
        for(int i=0; i<SIZE_ALL; i++)
        {    
            ADAMS_TAB_CONFIG AppoELEM = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);
            
            if(AppoELEM.DEFAULT_RESTRICTION != 'Y')
            {
                l1[count][0] = new Integer(AppoELEM.POSIZIONE_ELEMENTO);   //ID 
                l1[count][1] = new String(AppoELEM.TAG); //  - Tag
                count++;
            }
           // else
             //    System.out.println("NON METTO la restrinzione DEFAULT --> "+AppoELEM.TAG);
        }
        
        // Selected Items  ////////
        int SIZE_SELECTED = row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.size();
        Object[][] l2 = new Object[SIZE_SELECTED][2];

        for(int i=0; i<SIZE_SELECTED; i++)
        {
            Integer elem_Row_Restr  = (Integer)row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.elementAt(i);
            
            l2[i][0] = elem_Row_Restr;                                  // ID
            l2[i][1] = parent_V_TAB_CONFIG.getTagTE(elem_Row_Restr.intValue());  // DescRestriction
        }
        
        //System.out.println("l1.length "+l1.length);
        //System.out.println("l2.length "+l2.length);
             
        listChooserRestr.setListChooserData(l1, l2);
        //listChooserRestr.show();
        listChooserRestr.setVisible(true);

        // read selected Items
        boolean empty = true;
        row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.removeAllElements();
        
        l2 = listChooserRestr.getListChooserData();
        for(int i=0; i<l2.length; i++)
        {
            Integer ID_restr = ((Integer)(l2[i][0]));
            row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.addElement(ID_restr);
            empty = false;
            //System.out.println("USER DATA:"+l2[i][0]+" - "+l2[i][1]);            
        }
        listChooserRestr.dispose();
        
        if(empty)
        {
            jTF_restriction.setText(StrNotDefined);
            jTF_restriction.setForeground(Color.red);
        }
        else
        {
            jTF_restriction.setText(strDefined);
            jTF_restriction.setForeground(Color.green.darker());
        }
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_restrictionActionPerformed

    
    private void jB_analysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_analysisActionPerformed
        // Add your handling code here:
       // Add your handling code here:
        this.setCursor(Cur_wait);

        String title =" ( New Ghost Relation )";
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")!=0)
            title = " of Relation with name: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
        
        JListChooser listChooserAn = new JListChooser(ADAMS_GlobalParam.JFRAME_TAB, "Owner Analysis"+title, true);
     
        // Available Items //////// 
        Vector V_AllAnalysis = new Vector();
        //******** Leggo lengthAnalisi dal DB
        String sel_ReadAnalysis = "select IDANALISI, NOMEANALISI from tab_analysis_type"+
                                        " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by NOMEANALISI";
        
        //System.out.println("sel_ReadAnalysis --> "+sel_ReadAnalysis);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ReadAnalysis,SQLStatement);
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    ADAMS_TAB_TYPE_ANALISI row_Analysis = new ADAMS_TAB_TYPE_ANALISI();
                    
                    row_Analysis.IDANALISI          = rs.getInt("IDANALISI");                   //not null sil DB

                    String nomeAnalisi = rs.getString("NOMEANALISI");
                    if(nomeAnalisi != null)
                        row_Analysis.NOMEANALISI = nomeAnalisi;
                    
                    V_AllAnalysis.addElement(row_Analysis);
                }
            }catch (Exception ex) 
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
            System.out.println("(ADAMS_JP_Ghost_Relations-1) SQL Operation " + exc.toString());
        }
        
        // end ******** Leggo lengthAnalisi dal DB
        
        int SIZE_ALL = V_AllAnalysis.size();
        Object[][] l1= new Object[SIZE_ALL][2];
        for(int i=0; i<SIZE_ALL; i++)
        {            
            ADAMS_TAB_TYPE_ANALISI appo_row_Analysis = (ADAMS_TAB_TYPE_ANALISI)V_AllAnalysis.elementAt(i);
            
            l1[i][0] = new Integer(appo_row_Analysis.IDANALISI);    // IDAnalisys    
            l1[i][1] = appo_row_Analysis.NOMEANALISI;               // DescAnalysis       
        }
        
        // Selected Items  ////////
        int SIZE_SELECTED = row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.size();
        Object[][] l2 = new Object[SIZE_SELECTED][2];

        for(int i=0; i<SIZE_SELECTED; i++)
        {
            Integer elem_Row_Analy  = (Integer)row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.elementAt(i);
            l2[i][0] = elem_Row_Analy; // IDAnalisys 
            
            String strDescAnalysis = "";
            for(int x=0; x<V_AllAnalysis.size(); x++)
            {
                ADAMS_TAB_TYPE_ANALISI appo_row_Analysis = (ADAMS_TAB_TYPE_ANALISI)V_AllAnalysis.elementAt(x);
                if(appo_row_Analysis.IDANALISI == elem_Row_Analy.intValue() )
                {
                    strDescAnalysis = appo_row_Analysis.NOMEANALISI;
                    break;
                }
            }
            l2[i][1] = strDescAnalysis; //DescAnalysis  
        }
        
        listChooserAn.setListChooserData(l1, l2);
        //listChooserAn.show();
        listChooserAn.setVisible(true);

        // read selected Items
        boolean empty = true;
        row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.removeAllElements();
        
        l2 = listChooserAn.getListChooserData();
        for(int i=0; i<l2.length; i++)
        {
            Integer IDE_analys = ((Integer)(l2[i][0]));
            row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.addElement(IDE_analys);
            empty = false;
            //System.out.println("USER DATA:"+l2[i][0]+" - "+l2[i][1]);            
        }

        listChooserAn.dispose();
        
        if(empty)
        {
            jTF_analysis.setText(StrNotDefined);
            jTF_analysis.setForeground(Color.red);
        }
        else
        {
            jTF_analysis.setText(strDefined);
            jTF_analysis.setForeground(Color.green.darker());       
        }
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_analysisActionPerformed

    private void setGuiProperties()
    {
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")==0)
        {
            setEnabledClean_AllGui(false,false);
            row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME = "";
            jTF_text_Rel.setText("");
            jB_commit.setEnabled(false);
            return;
        }
        
        jTF_text_Rel.setText(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
        
        if(row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT == 1)
            jChBox_hexout.setSelected(true);
        else
            jChBox_hexout.setSelected(false);

        if(row_TAB_RELAZIONI_ELEMENTO.NETWORK == 1)
            jChBox_network.setSelected(true);
        else
            jChBox_network.setSelected(false);

        // ANALYSIS
        if(row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.size() > 0)
        {
            jTF_analysis.setText(strDefined);
            jTF_analysis.setForeground(Color.green.darker());
        }
        else
        {
            jTF_analysis.setText(StrNotDefined);
            jTF_analysis.setForeground(Color.red);
        }

        // RESTRICTIONS
        if(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.size() > 0)
        {
            jTF_restriction.setText(strDefined);
            jTF_restriction.setForeground(Color.green.darker());
        }
        else
        {
            jTF_restriction.setText(StrNotDefined);
            jTF_restriction.setForeground(Color.red);
        }

        // V_RESTRIZIONI_OBBL
        if(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.size() > 0)
        {
            jTF_ties.setText(strDefined);
            jTF_ties.setForeground(Color.green.darker());
        }
        else
        {
            jTF_ties.setText(StrNotDefined);
            jTF_ties.setForeground(Color.red);
        }
        
        if(jrb_add.isSelected())
            setEnabledClean_AllGui(true,false);
        else if(jrb_modify.isSelected())
        {
            setEnabledClean_AllGui(true,false);
        }
        else
            setEnabledClean_AllGui(false,false);
        
        jB_commit.setEnabled(true);
    }
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        setEnabledClean_AllGui(false,true);
        jB_commit.setEnabled(false);
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        //System.out.println("Commit");
        this.setCursor(Cur_wait);
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            AddRowJTabel_REL();
            this.setCursor(Cur_default);
            return;
        }
        
        
        if(row_TAB_RELAZIONI_ELEMENTO != null)
        {
            if(jrb_delete.isSelected())
            {                
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete Ghost Relation "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+". Confirm ?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    boolean Removed_row = row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.removeElement(row_TAB_RELAZIONI_ELEMENTO);
                    if(Removed_row)
                    {
                        int AnswerDelete = row_TAB_CONFIG_GHOST.update_LISTA_RELAZIONI(true);
                        if(AnswerDelete >= 0)
                        {                            
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Ghost Relation "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+" has been deleted.","INFO",3);
                            AddRowJTabel_REL();
                        }
                        else
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"(Error) Operation failed.","ERROR",1);
                    
                    setEnabledClean_AllGui(false,true);
                    jB_commit.setEnabled(false);
                        
                }// end Yes_No == 1 Delete 

            }// end jrb_delete.isSelected()
            else
            {   
                String nameText = jTF_text_Rel.getText().trim();
                //Controllo se esiste già la relazione ed eventualmente in modifica si replica una relazione esistente.
                for (int i=0; i<row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.size(); i++)
                {
                    ADAMS_TAB_RELAZIONI_ELEMENTO ctrl_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.elementAt(i));
                    String NameRelationCtrl = ctrl_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
                    //System.out.println(NameRelationCtrl+" == "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                    
                    if((NameRelationCtrl.compareTo(nameText) == 0)&&(ctrl_TAB_RELAZIONI_ELEMENTO != row_TAB_RELAZIONI_ELEMENTO))
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Ghost Relation exist for: "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,"WARNING",4);
                        this.setCursor(Cur_default);
                        return;
                    }
                }
                
                row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME = nameText;
                
                row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT = -1;
                if(jChBox_hexout.isSelected())
                    row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT = 1;
                
                row_TAB_RELAZIONI_ELEMENTO.NETWORK = -1;
                if(jChBox_network.isSelected())
                    row_TAB_RELAZIONI_ELEMENTO.NETWORK = 1;
                    
                row_TAB_RELAZIONI_ELEMENTO.GHOST = 1;
                
                if (row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.size() == 0)
                {
                    ADAMS_JD_Message op_AN= new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: No Owner Analysis selected.\nContinue ?","Warning",6);
                    int Yes_No_AN = op_AN.getAnswer();
                    if(Yes_No_AN == 0) 
                    {
                        this.setCursor(Cur_default);
                        return;
                    }
                }
                
                if(jrb_add.isSelected())
                {
                    row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO);
                    int AnswerUpdate = row_TAB_CONFIG_GHOST.update_LISTA_RELAZIONI(true);
                    
                    if(AnswerUpdate == 1)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Ghost Relation: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+" has been added.","INFO",3);                    
                        setEnabledClean_AllGui(true,true);
                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Ghost Relation: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+".","ERROR",1);
                        boolean NotRemoved = row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.removeElement(row_TAB_RELAZIONI_ELEMENTO);
                        if(!NotRemoved)
                            System.out.println("--- error removeNoAdded");
                        this.setCursor(Cur_default);
                        return;
                    }
                    
                }
                else //jrb_modify.isSelected()
                {
                    ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change Ghost Relation: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+". Confirm ?","Warning",6);
                    int Yes_No = op.getAnswer();
                    if(Yes_No == 1)
                    {
                        int AnswerUpdate = row_TAB_CONFIG_GHOST.update_LISTA_RELAZIONI(true);

                        if(AnswerUpdate >= 0)
                        {
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Ghost Relation: "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+" has been changed.","INFO",3);
                            setEnabledClean_AllGui(false,true);
                        }
                        else
                        {
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                            this.setCursor(Cur_default);
                            return;
                        }
                    }
                }
                
                AddRowJTabel_REL();
                try
                {   //seleziona riga JTable
                    for(int i=0; i<jTable_REL.getRowCount(); i++)
                    {
                        String name = (String)jTable_REL.getValueAt(i,0);
                        if( name.compareTo(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)==0)
                        {
                            jTable_REL.setRowSelectionInterval(i,i);
                            jScrollPane_REL.validate();
                            break;
                        }
                    }
                }catch(Exception e){}
                jB_commit.setEnabled(false);    
            }
        }
        else
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        setGuiInit();
        jTable_REL.setSelectionBackground(Color.red);
        jTA_help.setText("HELP Delete... Scrivere!!");
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
        setGuiInit();
        jTable_REL.setSelectionBackground(Color.green.darker());
        jTA_help.setText("HELP Modify... Scrivere!!");
    }//GEN-LAST:event_jrb_modifyActionPerformed

    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        setGuiInit();
        jTable_REL.setSelectionBackground(Color.yellow);
        jTA_help.setText(str_help_add);        
    }//GEN-LAST:event_jrb_addActionPerformed

    private void setGuiInit()
    {
        jTable_REL.clearSelection();
        if(jrb_add.isSelected())
        {
            setEnabledClean_AllGui(true,true);
            jTable_REL.setEnabled(false);
            row_TAB_RELAZIONI_ELEMENTO = new ADAMS_TAB_RELAZIONI_ELEMENTO("");
            
            /*for(int i=0; i<parent_V_TAB_CONFIG.size(); i++)
            {
                ADAMS_TAB_CONFIG appoTE = (ADAMS_TAB_CONFIG)(parent_V_TAB_CONFIG.elementAt(i));
                if ( appoTE.DEFAULT_RESTRICTION == 'Y' )
                {
                    row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.addElement(new Integer(appoTE.POSIZIONE_ELEMENTO));
                }
            }*/
            
             // RESTRICTIONS
            if(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.size() > 0)
            {
                jTF_restriction.setText(strDefined);
                jTF_restriction.setForeground(Color.green.darker());
            }
            else
            {
                jTF_restriction.setText(StrNotDefined);
                jTF_restriction.setForeground(Color.red);
            }
        }
        else
        {
            row_TAB_RELAZIONI_ELEMENTO = null;
            setEnabledClean_AllGui(false,true);
            jTable_REL.setEnabled(true);
        }
        
    }
        
    private void jTable_RELMouseReleased(java.awt.event.MouseEvent evt) 
    {
        this.setCursor(Cur_wait);
        
        int selectedRow = jTable_REL.getSelectedRow();
        if(selectedRow >= 0)
        {
            boolean ctrl_row = false;
            String str_RelationName = new String(""+jTable_REL.getValueAt(selectedRow,0));
            for(int i=0; i<row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.size(); i++) 
            {
                row_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.elementAt(i);         
                if(str_RelationName.compareTo(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME) == 0)
                {
                    //System.out.println("Selezionate e trovata relazione "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                    ctrl_row = true;
                    break;
                }                
            }
            
            if(ctrl_row == false)
            {
                row_TAB_RELAZIONI_ELEMENTO = null;
                System.out.println("----- ERRORE Relation selected row_TAB_RELAZIONI_ELEMENTO");
                this.setCursor(Cur_default);
                return;
            }
            
                       
        // Relation Single Operation 
            jTF_text_Rel.setText(str_RelationName);
            setGuiProperties();
        }
        this.setCursor(Cur_default);       
    } 
    
    
    //Permette di resettare e ripulire tutti i widget di input dell'interfaccia.
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {           
        jTF_text_Rel.setEnabled(enable);
        jChBox_hexout.setEnabled(enable);
        jChBox_network.setEnabled(enable);
        
        jTF_analysis.setEnabled(enable);
        jTF_restriction.setEnabled(enable);
        jTF_ties.setEnabled(enable);
        
        jB_analysis.setEnabled(enable);
        jB_restriction.setEnabled(enable);
        jB_ties.setEnabled(enable);

        if(clean)
        {                               
            jTF_text_Rel.setText("");
            
            jTF_analysis.setText(StrNotDefined);
            jTF_analysis.setForeground(Color.red);
            jTF_restriction.setText(StrNotDefined);
            jTF_restriction.setForeground(Color.red);
            jTF_ties.setText(StrNotDefined);
            jTF_ties.setForeground(Color.red);
            
            jChBox_hexout.setSelected(false);
            jChBox_network.setSelected(false);    
        }
        if(jTF_text_Rel.getText().trim().length() > 0)
            this.jB_commit.setEnabled(true);
        else
            this.jB_commit.setEnabled(false);
            
  
    }
    
    private void setGlobalFont()
    {
        //Font Globali
        jLabel1.setFont(ADAMS_GlobalParam.font_B12);
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);        
        jChBox_hexout.setFont(ADAMS_GlobalParam.font_B10); 
        jChBox_network.setFont(ADAMS_GlobalParam.font_B10); 
        jLabel8.setFont(ADAMS_GlobalParam.font_B10);
        jLabel13.setFont(ADAMS_GlobalParam.font_B10);
        jLabel12.setFont(ADAMS_GlobalParam.font_B10);
        jTF_analysis.setFont(ADAMS_GlobalParam.font_B10);
        jTF_restriction.setFont(ADAMS_GlobalParam.font_B10);
        jTF_ties.setFont(ADAMS_GlobalParam.font_B10);
        
        jTA_help.setFont(ADAMS_GlobalParam.font_P10);
        jTF_text_Rel.setFont(ADAMS_GlobalParam.font_B11);
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
    
    private void printLISTA_RELAZIONI()
    {
        System.out.println("V_LISTA_RELAZIONI.size() -> "+row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.size());
        for(int i=0; i<row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.size(); i++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO rel_elemento = (ADAMS_TAB_RELAZIONI_ELEMENTO)row_TAB_CONFIG_GHOST.V_LISTA_RELAZIONI.elementAt(i);
            System.out.println("RELATION_NAME -> "+rel_elemento.RELATION_NAME);
            System.out.println("DIREZIONE   -> "+rel_elemento.DIREZIONE);
            System.out.println("HEXOUTPUT   -> "+rel_elemento.HEXOUTPUT);
            System.out.println("NETWORK     -> "+rel_elemento.NETWORK);
            System.out.println("GHOST       -> "+rel_elemento.GHOST); 

            for(int x=0;x<rel_elemento.V_LISTA_ANALISI.size(); x++)
                System.out.println("index="+x+" Analisi     -> "+rel_elemento.V_LISTA_ANALISI.elementAt(x));
            for(int x=0;x<rel_elemento.V_RESTRIZIONI.size(); x++)
                System.out.println("index="+x+" Restrizioni -> "+rel_elemento.V_RESTRIZIONI.elementAt(x));
            for(int x=0;x<rel_elemento.V_RESTRIZIONI_OBBL.size(); x++)
                System.out.println("index="+x+" Restrizioni_obbl -> "+rel_elemento.V_RESTRIZIONI_OBBL.elementAt(x));
        }
    }
//////////////////////////////////////////// END Internal CLASS ////////////////////////////////////////////  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_center;
    private javax.swing.JPanel jP_c1;
    private javax.swing.JScrollPane jScrollPane_REL;
    private javax.swing.JPanel jP_c2;
    private javax.swing.JTextArea jTA_help;
    private javax.swing.JPanel jPanel_south;
    private javax.swing.JPanel jP_sc;
    private javax.swing.JPanel jP_scs;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jP_analysis;
    private javax.swing.JTextField jTF_analysis;
    private javax.swing.JButton jB_analysis;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jP_restriction;
    private javax.swing.JTextField jTF_restriction;
    private javax.swing.JButton jB_restriction;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jP_ties;
    private javax.swing.JTextField jTF_ties;
    private javax.swing.JButton jB_ties;
    private javax.swing.JCheckBox jChBox_network;
    private javax.swing.JCheckBox jChBox_hexout;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTF_text_Rel;
    private javax.swing.JPanel jP_ss;
    private javax.swing.JButton jB_commit;
    private javax.swing.JPanel jP_sn;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
           
    private javax.swing.JTable jTable_REL;
    private TableSorter sorter_REL;
    private MyTableModel myTableModel_REL;
     
    private String str_help_add = null;
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined";
    
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    
    private ADAMS_TAB_CONFIG row_TAB_CONFIG_GHOST = null;
    
    private ADAMS_TAB_RELAZIONI_ELEMENTO row_TAB_RELAZIONI_ELEMENTO;
    
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;
    
}

