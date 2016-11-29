/*
 * ADAMS_JP_Relations.java
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

public class ADAMS_JP_Relations extends javax.swing.JPanel {

    /** Creates new form ADAMS_JP_Relations */
    public ADAMS_JP_Relations(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {

        initComponents();
        
        this.local_INFO_CONFIG = INFO_CONFIG;
        jL_title.setText("Relation Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames ={"Id","Tag"};
        int [] cellDimen_TE ={30,180};
        myTableModel_TE = new MyTableModel(all_columnNames);
        sorter_TE = new TableSorter(myTableModel_TE);
        
        jTable_TE = new javax.swing.JTable(sorter_TE);
        JTableHeader header = jTable_TE.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter_TE.setTableHeader(header);        
        
        jTable_TE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_TE.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_TE.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_TE.setRowHeight(20);
        jTable_TE.setRowMargin(2);
        jTable_TE.setAutoCreateColumnsFromModel(false);
        jTable_TE.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        
        jScrollPane_TE.setViewportView(jTable_TE);
        
        jTable_TE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TEMouseReleased(evt);
            }
        });
             
        // **************************** //
        
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames2 ={"Tag Relation"};
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
        jTable_REL.setFont(new java.awt.Font("Verdana", 0, 9));
        jTable_REL.setRowHeight(20);
        jTable_REL.setRowMargin(2);
        jTable_REL.setAutoCreateColumnsFromModel(true);
        jTable_REL.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jTable_REL.setSelectionBackground(java.awt.Color.yellow);
        //jTable_REL.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        jScrollPane_REL.setViewportView(jTable_REL);
        
        jTable_REL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_RELMouseReleased(evt);
            }
        });
        // **************************** //
        
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames3 ={"Id","Tag"};
        int [] cellDimen_TE_build ={30,180};
        myTableModel_TE_build = new MyTableModel(all_columnNames3);
        sorter_TE_build = new TableSorter(myTableModel_TE_build);
        
        jTable_TE_build = new javax.swing.JTable(sorter_TE_build);
        JTableHeader header_build = jTable_TE_build.getTableHeader();
        header_build.setFont(ADAMS_GlobalParam.font_B10);
        header_build.setReorderingAllowed(false);

        sorter_TE_build.setTableHeader(header_build);        
        
        jTable_TE_build.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_TE_build.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_TE_build.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_TE_build.setRowHeight(20);
        jTable_TE_build.setRowMargin(2);
        jTable_TE_build.setAutoCreateColumnsFromModel(false);
        jTable_TE_build.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        jTable_TE_build.setSelectionBackground(java.awt.Color.yellow);
        
        //jScrollPane_TE_build.setViewportView(jTable_TE_build);
        
        jTable_TE_build.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TE_buildMouseReleased(evt);
            }
        });
        
        //font Globali
        setGlobalFont();
        jL_relation.setBackground(java.awt.Color.green.darker());
        jL_RelationSize_MaxSize.setText("");
        jL_RelationSize_MaxSize.setBackground(colordefault);
        
         //Cursor mouse        
        jCB_directions.setCursor(Cur_hand);
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
        jB_addElem.setCursor(Cur_hand);
        jB_remLast.setCursor(Cur_hand);

        SetTable(jTable_TE,30,cellDimen_TE);
        SetTable(jTable_REL,30,cellDimen_REL);
        SetTable(jTable_TE_build,30,cellDimen_TE_build);
        
        setJCBox_Directions();
        
        setCounterAllRelation();
        
        str_help_add = ("Select an Element to add to your relation from the Traffic Element list. "+
                        "Click 'Add Element' button to concatenate it or press 'Delete Last' to revert operations. "+
                        "Please note that a relation is made by at least two (2) Traffic Elements. "+
                        "******** Aggiungere set parametri e Commit.");
        jTA_help.setText(str_help_add);
        
        setGuiInit();
    }
    
    private void setCounterAllRelation()
    {
        //System.out.println("setCounterAllRelation()");
        /* OLD NESTED String STR_selCount = "select count(a.TIPO_DI_CONFIGURAZIONE||b.RELATION_NAME) from tab_config a, table (a.LISTA_RELAZIONI) b "+
                                "where a.TIPO_DI_CONFIGURAZIONE='"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'";*/
        String STR_selCount = "select count(1) from tab_list_relation "+
                                "where TIPO_DI_CONFIGURAZIONE='"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'";
        
        
        int NumAllRelations = ADAMS_GlobalParam.connect_Oracle.Query_int(STR_selCount);
        
        //jL_counter.setText("Number relations present : #"+NumAllRelations+"  ");
        
        javax.swing.border.Border border_txt = jP_center.getBorder();
        
        //NumAllRelations = 400;
        
       /* if(NumAllRelations >= ADAMS_GlobalParam._MAX_RELATION)
        {
            border_txt = new javax.swing.border.TitledBorder(null, " WARNIG Number relations : #"+NumAllRelations+" ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11),java.awt.Color.red);
        
        }
        else*/
            border_txt = new javax.swing.border.TitledBorder(null, " Number relations : #"+NumAllRelations+" ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10),java.awt.Color.blue);
        
        jP_center.setBorder(border_txt);
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
        this.parent_V_TAB_CONFIG = Parent_V_TAB_CONFIG;
                
        setEnabledClean_AllGui(false,true);
        AddRowJTabel_TE();
        AddRowJTabel_REL(null);
        jTable_TE.clearSelection();
        setCounterAllRelation();
    }
        
    private void AddRowJTabel_TE()
    {
        jTable_REL.setCursor(Cur_wait);
        
        jL_title.setText("Relation Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);

        int SIZE = parent_V_TAB_CONFIG.size();
        Object[][] dataValues = new Object[SIZE][2];
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);
            
            dataValues[i][0] = new Integer(row_TAB_CONFIG.POSIZIONE_ELEMENTO);
            dataValues[i][1] = new String(row_TAB_CONFIG.TAG);
        }
        myTableModel_TE.setDataValues(dataValues);
        sorter_TE.setTableModel(myTableModel_TE);
        jScrollPane_TE.setViewportView(jTable_TE);
        
        myTableModel_TE_build.setDataValues(dataValues);
        sorter_TE_build.setTableModel(myTableModel_TE_build);
        //jScrollPane_TE_build.setViewportView(jTable_TE_build);
                
        jTable_TE.setCursor(Cur_default);
    }
    
    private void AddRowJTabel_REL(ADAMS_TAB_CONFIG row_TAB_CONFIG)
    {
        jTable_REL.setCursor(Cur_wait);
        int SIZE = 0;
                
        Object[][] dataValues;
        if(row_TAB_CONFIG == null)
        {
             dataValues = new Object[0][0];
        }
        else
        {              
            
            SIZE = row_TAB_CONFIG.V_LISTA_RELAZIONI.size();

            dataValues = new Object[SIZE][1];
            for(int i=0; i<SIZE; i++)
            {            
                ADAMS_TAB_RELAZIONI_ELEMENTO row_RELATION = (ADAMS_TAB_RELAZIONI_ELEMENTO)row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(i);
                String RelationNameTag = getDescriptionREL(row_RELATION.RELATION_NAME);
                dataValues[i][0] = new String(RelationNameTag);                
                
                //System.out.println(RelationNameTag+"  ("+row_RELATION.RELATION_NAME+")");
            }
        }
        String[] all_columnNames2 ={"Tag Relation (present #"+SIZE+")"};
        myTableModel_REL.setColumnName(all_columnNames2);
        myTableModel_REL.setDataValues(dataValues);
        sorter_REL.setTableModel(myTableModel_REL);      
        
        /*java.awt.Dimension dimens = jScrollPane_REL.getSize();
        int dw = new Double(dimens.getWidth()-27).intValue();
        int hw = new Double(dimens.getHeight()-37).intValue();
        
        jTable_REL.setPreferredSize(new java.awt.Dimension(dw,hw));
        jTable_REL.setMinimumSize(jScrollPane_REL.getSize()-60);
        jTable_REL.setMaximumSize(new java.awt.Dimension(2000,250));*/      
        
        jScrollPane_REL.setViewportView(jTable_REL);        
        jTable_REL.setCursor(Cur_default);
   
    }
    
    
    private void setSelected_jTable_TE()
    {
        this.setCursor(Cur_wait);
        jTable_TE.setCursor(Cur_wait);
        int selectedRow = jTable_TE.getSelectedRow();
        
        if(selectedRow >= 0)
        {
            boolean ctrl_row = false;
            int ID_selected = new Integer(""+jTable_TE.getValueAt(selectedRow,0)).intValue();
           
            // cerco l'oggetto ADAMS_TAB_CONFIG tramite POSIZIONE_ELEMENTO (Non rispetta l'ordine del vettore nel caso del Sorter della tabella)
            for(int i=0; i<parent_V_TAB_CONFIG.size(); i++) 
            {
                Selected_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);      
                if(Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO == ID_selected)
                {                    
                    ctrl_row = true;
                    break;
                }
            }
            if(ctrl_row == false)
            {
                Selected_row_TAB_CONFIG = null;
                System.out.println("----- ERRORE Selected_row...");
                this.setCursor(Cur_default);
                jTable_TE.setCursor(Cur_default);
                
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"----- ERROR Selected row ----- . Reload List.","ERROR",1);
                
                setEnabledClean_AllGui(false,true);
                AddRowJTabel_TE();
                AddRowJTabel_REL(null);
                jTable_TE.clearSelection();
                
                return;
            }
            
            setEnabledClean_AllGui(false,true);
            
            //System.out.println("read_LISTA_RELAZIONI row POSIZIONE_ELEMENTO ="+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO);
            Selected_row_TAB_CONFIG.read_LISTA_RELAZIONI();
            AddRowJTabel_REL(Selected_row_TAB_CONFIG);
            
            jTable_TE_build.clearSelection();
            jScrollPane_TE_build.setViewportView(jTable_TE_build);            

            if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
            {                        
                jTable_TE_build.setBackground(colordefault);
                jTable_TE_build.setEnabled(false);
                
                jTable_REL.setEnabled(true);
                jTable_REL.setBackground(java.awt.Color.white);                
                row_TAB_RELAZIONI_ELEMENTO = null;
            }
            else // (jrb_add.isSelected())
            {                
                jTable_TE_build.setEnabled(true);
                jTable_TE_build.setBackground(java.awt.Color.white);
                
                jL_relation.setText(Selected_row_TAB_CONFIG.TAG);
                jL_relation.setToolTipText(jL_relation.getText());
                
                jL_RelationSize_MaxSize.setText("");
                jL_RelationSize_MaxSize.setBackground(colordefault);
                
                row_TAB_RELAZIONI_ELEMENTO = new ADAMS_TAB_RELAZIONI_ELEMENTO(""+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO);
                /*for(int i=0; i<parent_V_TAB_CONFIG.size(); i++)
                {
                    ADAMS_TAB_CONFIG appoTE = (ADAMS_TAB_CONFIG)(parent_V_TAB_CONFIG.elementAt(i));
                    if ( appoTE.DEFAULT_RESTRICTION == 'Y' )
                    {
                        row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.addElement(new Integer(appoTE.POSIZIONE_ELEMENTO));
                    }
                }*/
                
            }
        }
        this.setCursor(Cur_default);
        jTable_TE.setCursor(Cur_default);
    }
    
    
    private void setJCBox_Directions()
    {
        jCB_directions.removeAllItems();

        VectorDirections = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_DIRECTIONS);
        Vector AllDesc = VectorDirections.vgetDesc();
        
        for(int i=0; i<AllDesc.size(); i++)
        {
            jCB_directions.addItem((String)AllDesc.elementAt(i));
        }
        //VectorDirections.stampaAll();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu = new javax.swing.JPopupMenu();
        jMItem_RelAss = new javax.swing.JMenuItem();
        jMItem_Rel_with_RestObl = new javax.swing.JMenuItem();
        jP_center = new javax.swing.JPanel();
        jScrollPane_TE_build = new javax.swing.JScrollPane();
        jP_c3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jB_addElem = new javax.swing.JButton();
        jB_remLast = new javax.swing.JButton();
        jTA_help = new javax.swing.JTextArea();
        jTArea_DescSelected = new javax.swing.JTextArea();
        jScrollPane_REL = new javax.swing.JScrollPane();
        jScrollPane_TE = new javax.swing.JScrollPane();
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
        jLabel10 = new javax.swing.JLabel();
        jCB_directions = new javax.swing.JComboBox();
        jChBox_hexout = new javax.swing.JCheckBox();
        jChBox_network = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jL_relation = new javax.swing.JLabel();
        jL_RelationSize_MaxSize = new javax.swing.JLabel();
        jP_ss = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jP_sn = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        jP_north = new javax.swing.JPanel();
        jL_title = new javax.swing.JLabel();
        
        jMItem_RelAss.setText("Visualizza Relazioni Associate");
        jMItem_RelAss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMItem_RelAssActionPerformed(evt);
            }
        });
        
        jPopupMenu.add(jMItem_RelAss);
        jMItem_Rel_with_RestObl.setText("Restrizioni Obbligatorie Associate");
        jMItem_Rel_with_RestObl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMItem_Rel_with_RestOblActionPerformed(evt);
            }
        });
        
        jPopupMenu.add(jMItem_Rel_with_RestObl);
        
        setLayout(new java.awt.BorderLayout(2, 2));
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 5));
        jP_center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setBorder(new javax.swing.border.TitledBorder(null, " Relation Build ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), java.awt.Color.blue));
        jScrollPane_TE_build.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_TE_build.setBorder(new javax.swing.border.TitledBorder(null, " Traffic Elements for Relation Build ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_center.add(jScrollPane_TE_build, gridBagConstraints1);
        
        jP_c3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_c3.setBackground(new java.awt.Color(230, 230, 230));
        jP_c3.setBorder(new javax.swing.border.TitledBorder(null, " Relation Build ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jP_c3.setMinimumSize(new java.awt.Dimension(200, 200));
        jP_c3.setPreferredSize(new java.awt.Dimension(200, 232));
        jLabel3.setBackground(new java.awt.Color(230, 230, 230));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Description current selection");
        jLabel3.setToolTipText("");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setMinimumSize(new java.awt.Dimension(110, 21));
        jLabel3.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jP_c3.add(jLabel3, gridBagConstraints2);
        
        jB_addElem.setBackground(new java.awt.Color(230, 230, 230));
        jB_addElem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_32.png")));
        jB_addElem.setText("Add Element");
        jB_addElem.setBorderPainted(false);
        jB_addElem.setContentAreaFilled(false);
        jB_addElem.setFocusPainted(false);
        jB_addElem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_addElem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_addElem.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_press_32.png")));
        jB_addElem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jB_addElem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_addElemActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(15, 2, 15, 2);
        gridBagConstraints2.weightx = 1.0;
        jP_c3.add(jB_addElem, gridBagConstraints2);
        
        jB_remLast.setBackground(new java.awt.Color(230, 230, 230));
        jB_remLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove_32.png")));
        jB_remLast.setText("Remove Last");
        jB_remLast.setBorderPainted(false);
        jB_remLast.setContentAreaFilled(false);
        jB_remLast.setFocusPainted(false);
        jB_remLast.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_remLast.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_remLast.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove_press_32.png")));
        jB_remLast.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jB_remLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_remLastActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(15, 2, 15, 2);
        gridBagConstraints2.weightx = 1.0;
        jP_c3.add(jB_remLast, gridBagConstraints2);
        
        jTA_help.setBackground(new java.awt.Color(230, 230, 230));
        jTA_help.setEditable(false);
        jTA_help.setLineWrap(true);
        jTA_help.setWrapStyleWord(true);
        jTA_help.setBorder(new javax.swing.border.TitledBorder(null, " Help ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jTA_help.setPreferredSize(new java.awt.Dimension(100, 38));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(15, 1, 15, 1);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_c3.add(jTA_help, gridBagConstraints2);
        
        jTArea_DescSelected.setBackground(java.awt.Color.yellow);
        jTArea_DescSelected.setLineWrap(true);
        jTArea_DescSelected.setToolTipText("Description");
        jTArea_DescSelected.setWrapStyleWord(true);
        jTArea_DescSelected.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jTArea_DescSelected.setMinimumSize(new java.awt.Dimension(100, 50));
        jTArea_DescSelected.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_c3.add(jTArea_DescSelected, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridheight = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jP_center.add(jP_c3, gridBagConstraints1);
        
        jScrollPane_REL.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_REL.setBorder(new javax.swing.border.TitledBorder(null, " Present Relations ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jScrollPane_REL.setAutoscrolls(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.8;
        jP_center.add(jScrollPane_REL, gridBagConstraints1);
        
        jScrollPane_TE.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_TE.setBorder(new javax.swing.border.TitledBorder(null, " Primary Traffic Elements ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jScrollPane_TE.setAutoscrolls(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        jP_center.add(jScrollPane_TE, gridBagConstraints1);
        
        add(jP_center, java.awt.BorderLayout.CENTER);
        
        jPanel_south.setLayout(new java.awt.BorderLayout());
        
        jPanel_south.setBackground(new java.awt.Color(230, 230, 230));
        jPanel_south.setBorder(new javax.swing.border.TitledBorder(null, " Relation Single Operation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jPanel_south.setPreferredSize(new java.awt.Dimension(10, 165));
        jP_sc.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_sc.setBackground(new java.awt.Color(230, 230, 230));
        jP_scs.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_scs.setBackground(new java.awt.Color(230, 230, 230));
        jLabel8.setBackground(new java.awt.Color(230, 230, 230));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Owner Analysis");
        jLabel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jLabel8, gridBagConstraints4);
        
        jP_analysis.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_analysis.setBackground(new java.awt.Color(230, 230, 230));
        jTF_analysis.setBackground(new java.awt.Color(230, 230, 230));
        jTF_analysis.setEditable(false);
        jTF_analysis.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_analysis.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_analysis.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_analysis.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints5.weightx = 1.0;
        jP_analysis.add(jTF_analysis, gridBagConstraints5);
        
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
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        jP_analysis.add(jB_analysis, gridBagConstraints5);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_scs.add(jP_analysis, gridBagConstraints4);
        
        jLabel13.setBackground(new java.awt.Color(230, 230, 230));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Restrictions");
        jLabel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel13.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 4;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jLabel13, gridBagConstraints4);
        
        jP_restriction.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jP_restriction.setBackground(new java.awt.Color(230, 230, 230));
        jTF_restriction.setBackground(new java.awt.Color(230, 230, 230));
        jTF_restriction.setEditable(false);
        jTF_restriction.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_restriction.setToolTipText("");
        jTF_restriction.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_restriction.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_restriction.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints6.weightx = 1.0;
        jP_restriction.add(jTF_restriction, gridBagConstraints6);
        
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
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        jP_restriction.add(jB_restriction, gridBagConstraints6);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 4;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_scs.add(jP_restriction, gridBagConstraints4);
        
        jLabel12.setBackground(new java.awt.Color(230, 230, 230));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ties Restrictions");
        jLabel12.setToolTipText("");
        jLabel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel12.setPreferredSize(new java.awt.Dimension(0, 22));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 5;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jLabel12, gridBagConstraints4);
        
        jP_ties.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints7;
        
        jP_ties.setBackground(new java.awt.Color(230, 230, 230));
        jTF_ties.setBackground(new java.awt.Color(230, 230, 230));
        jTF_ties.setEditable(false);
        jTF_ties.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_ties.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_ties.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_ties.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints7.weightx = 1.0;
        jP_ties.add(jTF_ties, gridBagConstraints7);
        
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
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 0;
        jP_ties.add(jB_ties, gridBagConstraints7);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 5;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_scs.add(jP_ties, gridBagConstraints4);
        
        jLabel10.setBackground(new java.awt.Color(230, 230, 230));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Directions");
        jLabel10.setToolTipText("(Select Item)");
        jLabel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel10.setPreferredSize(new java.awt.Dimension(22, 22));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jLabel10, gridBagConstraints4);
        
        jCB_directions.setBackground(java.awt.Color.white);
        jCB_directions.setMaximumRowCount(6);
        jCB_directions.setMinimumSize(new java.awt.Dimension(100, 22));
        jCB_directions.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jCB_directions, gridBagConstraints4);
        
        jChBox_hexout.setBackground(new java.awt.Color(230, 230, 230));
        jChBox_hexout.setText("Hexadecimal Output");
        jChBox_hexout.setToolTipText("(True/False)");
        jChBox_hexout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jChBox_hexout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jChBox_hexout.setMinimumSize(new java.awt.Dimension(40, 22));
        jChBox_hexout.setPreferredSize(new java.awt.Dimension(80, 22));
        jChBox_hexout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_hexout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jChBox_hexout.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_hexout.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.gridwidth = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jChBox_hexout, gridBagConstraints4);
        
        jChBox_network.setBackground(new java.awt.Color(230, 230, 230));
        jChBox_network.setText("Network");
        jChBox_network.setToolTipText("(True/False)");
        jChBox_network.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jChBox_network.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jChBox_network.setMinimumSize(new java.awt.Dimension(40, 22));
        jChBox_network.setPreferredSize(new java.awt.Dimension(40, 22));
        jChBox_network.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_network.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jChBox_network.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jChBox_network.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.gridwidth = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints4.weightx = 1.0;
        jP_scs.add(jChBox_network, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridwidth = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(6, 1, 1, 1);
        jP_sc.add(jP_scs, gridBagConstraints3);
        
        jLabel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Relation");
        jLabel2.setToolTipText("");
        jLabel2.setMaximumSize(new java.awt.Dimension(150, 22));
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 22));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 22));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jLabel2, gridBagConstraints3);
        
        jL_relation.setBackground(java.awt.Color.yellow);
        jL_relation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_relation.setToolTipText("");
        jL_relation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204)));
        jL_relation.setMinimumSize(new java.awt.Dimension(300, 25));
        jL_relation.setPreferredSize(new java.awt.Dimension(300, 25));
        jL_relation.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 4, 1);
        jP_sc.add(jL_relation, gridBagConstraints3);
        
        jL_RelationSize_MaxSize.setBackground(new java.awt.Color(230, 230, 230));
        jL_RelationSize_MaxSize.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_RelationSize_MaxSize.setMaximumSize(new java.awt.Dimension(200, 22));
        jL_RelationSize_MaxSize.setMinimumSize(new java.awt.Dimension(200, 22));
        jL_RelationSize_MaxSize.setPreferredSize(new java.awt.Dimension(200, 22));
        jL_RelationSize_MaxSize.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jL_RelationSize_MaxSize, gridBagConstraints3);
        
        jPanel_south.add(jP_sc, java.awt.BorderLayout.CENTER);
        
        jP_ss.setLayout(new java.awt.BorderLayout());
        
        jP_ss.setBackground(new java.awt.Color(230, 230, 230));
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
        
        jP_north.setLayout(new java.awt.BorderLayout());
        
        jP_north.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rel_32.png")));
        jL_title.setText("Relation Build");
        jL_title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(150, 40));
        jP_north.add(jL_title, java.awt.BorderLayout.CENTER);
        
        add(jP_north, java.awt.BorderLayout.NORTH);
        
    }//GEN-END:initComponents

    private void jMItem_Rel_with_RestOblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMItem_Rel_with_RestOblActionPerformed
        ADAMS_JD_ViewRelWithRestriction JD_ViewRelWithRestriction = new ADAMS_JD_ViewRelWithRestriction(ADAMS_GlobalParam.JFRAME_TAB,true,800,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,this.parent_V_TAB_CONFIG);
    }//GEN-LAST:event_jMItem_Rel_with_RestOblActionPerformed

    private void jMItem_RelAssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMItem_RelAssActionPerformed
        int selectedRow = jTable_TE.getSelectedRow();
        int ID_ELEMENTO_SEL = new Integer(""+jTable_TE.getValueAt(selectedRow,0)).intValue();
        ADAMS_JD_ViewRelations JD_ViewRelations = new ADAMS_JD_ViewRelations(ADAMS_GlobalParam.JFRAME_TAB,true,650,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,ID_ELEMENTO_SEL,this.parent_V_TAB_CONFIG);
    }//GEN-LAST:event_jMItem_RelAssActionPerformed

    private void jB_tiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_tiesActionPerformed
        // Add your handling code here:
        this.setCursor(Cur_wait);

        String title =" ( New Relation )";
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")!=0)
            title = " of Relation with name: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
        
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
        // Add your handling code here:
        this.setCursor(Cur_wait);

        String title =" ( New Relation )";
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")!=0)
            title = " of Relation with name: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
        
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
                l1[count][1] = new String(AppoELEM.TAG);                    //  - Tag
                count++;
            }
            //else
              //  System.out.println("NON METTO la restrinzione DEFAULT --> "+AppoELEM.TAG);
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
        this.setCursor(Cur_wait);

        String title =" ( New Relation )";
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")!=0)
            title = " of Relation with name: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
        
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
            System.out.println("(ADAMS_JP_Relations-1) SQL Operation " + exc.toString());
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

    private void jB_remLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_remLastActionPerformed
        
        StringTokenizer STKrelationID   = new StringTokenizer(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME,"::");
        StringTokenizer STKrelationName = new StringTokenizer(jL_relation.getText(),"::");
        
        String cutRelationID = "";
        String cutRelationName = "";
        
        String dot ="";
        int tokenId     = STKrelationID.countTokens();
        int tokenName   = STKrelationName.countTokens();
        
        if(tokenId == 1 )
        {
            if(tokenId != tokenName)
                System.out.println("errore token jB_remLastActionPerformed(...)");
            return;
        }
        
        for(int i=0; i<tokenId; i++)
        {                        
            cutRelationID   += dot+STKrelationID.nextToken();
            cutRelationName += dot+STKrelationName.nextToken();
            
            dot="::";
            
            if(i == tokenId-2)
                break;           
        }
        
        //System.out.println("jB_remLast  Name"+cutRelationName +"   ("+cutRelationID+")");

        row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME = cutRelationID;
        
        StringTokenizer STKrelationID_forSize  = new StringTokenizer(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME,"::");
        int asize = 0;
        while(STKrelationID_forSize.hasMoreTokens())
        {
            int ID_TE_RelationElements = new Integer(STKrelationID_forSize.nextToken()).intValue();
            
            //System.out.println();
            //System.out.println("Rimozione --- Controllo l'elemento della relazione "+ ID_TE_RelationElements);                
            int REAL_SIZE = getElementRealSize(ID_TE_RelationElements);
            asize += REAL_SIZE;                
        }          
        jL_RelationSize_MaxSize.setText("Real Size: "+asize +" byte /"+ADAMS_GlobalParam._MAX_KEY_LENGTH+" byte");
            
        if( asize > ADAMS_GlobalParam._MAX_KEY_LENGTH )
            jL_RelationSize_MaxSize.setBackground(Color.red); 
        else
            jL_RelationSize_MaxSize.setBackground(colordefault);
        
        //***
        
        jL_relation.setText(cutRelationName);
        jL_relation.setToolTipText(jL_relation.getText());
        //jL_text_Rel.setText(cutRelationName);
        setGuiProperties();
        if(cutRelationName.indexOf("::") == -1)
            this.jB_commit.setEnabled(false);
        
    }//GEN-LAST:event_jB_remLastActionPerformed

    private void jB_addElemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_addElemActionPerformed
        
        int selectedRow_build = jTable_TE_build.getSelectedRow();
        if((jTable_TE.getSelectedRow() >=0)&&(selectedRow_build >=0))
        {        
            int ID_TE_Build_selected    = ((Integer)(jTable_TE_build.getValueAt(selectedRow_build,0))).intValue();
            String TAG_TE_Build_selected   = ""+jTable_TE_build.getValueAt(selectedRow_build,1);
        
            StringTokenizer STKrelationName = new StringTokenizer(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME,"::");
            
            //System.out.println("RELATION_NAME "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME); 
            int asize = 0;
            //int offset = 0;
            while(STKrelationName.hasMoreTokens())
            {
                int ID_TE_RelationElements = new Integer(STKrelationName.nextToken()).intValue();
                if( ID_TE_Build_selected == ID_TE_RelationElements )
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Your Relation already contains: "+ID_TE_Build_selected+" "+TAG_TE_Build_selected,"WARNING",4);                  
                    return;
                }
                
                //System.out.println();
                //System.out.println("Controllo l'elemento della relazione "+ ID_TE_RelationElements);                
                int REAL_SIZE = getElementRealSize(ID_TE_RelationElements);
                asize += REAL_SIZE;             
            }
            
            asize += getElementRealSize(ID_TE_Build_selected);            
            //System.out.println("Dimensione Totale della Relazione ---> "+asize+" byte");
            
            jL_RelationSize_MaxSize.setText("Real Size: "+asize +" byte /"+ADAMS_GlobalParam._MAX_KEY_LENGTH+" byte");
            
            if( asize > ADAMS_GlobalParam._MAX_KEY_LENGTH )
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"La relazione che si vuol creare supera la dimensione massima (max "+ADAMS_GlobalParam._MAX_KEY_LENGTH+" byte).","WARNING",4);                  
                jL_RelationSize_MaxSize.setBackground(Color.red); 
                return;
            }            
            jL_RelationSize_MaxSize.setBackground(colordefault); 
            
            row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME += "::"+ID_TE_Build_selected;
            jL_relation.setText(jL_relation.getText()+"::"+TAG_TE_Build_selected);
            jL_relation.setToolTipText(jL_relation.getText());
            
            //jL_text_Rel.setText(jL_relation.getText());
            //System.out.println(jL_relation.getText()+"   ("+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME+")");
            setGuiProperties();
        }
        
    }//GEN-LAST:event_jB_addElemActionPerformed

    private int getElementRealSize(int ID_Element)
    {        
        int SIZE = parent_V_TAB_CONFIG.size();
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);

            if(row_TAB_CONFIG.POSIZIONE_ELEMENTO == ID_Element)
            {
               // System.out.println("TROVATO --> "+row_TAB_CONFIG.POSIZIONE_ELEMENTO+" "+new String(row_TAB_CONFIG.TAG).trim());
                if(row_TAB_CONFIG.POSIZIONE_CAMPO_DR != -1)
                {
                    //System.out.println("E' un campo del Cartellino --> FIELD "+row_TAB_CONFIG.POSIZIONE_CAMPO_DR);

                    int typeSize = row_TAB_CONFIG.SIZE_CAMPO_DR;
                    int ArraySize = row_TAB_CONFIG.NUMERO_ELEM_ARRAY;
                    int realSize  = 0;
                    if (row_TAB_CONFIG.FLAG_ARRAY == 'Y')
                    {
                        realSize = (typeSize*ArraySize);
                        //offset += realSize;
                    }
                    else
                    {
                        //offset += typeSize;
                        realSize = typeSize;
                    }
                    //System.out.println("getElementRealSize() --> Dimensione Elemento --->"+realSize);
                    return realSize;
                }                    
            }
        }
        return 0;
        
    }
    
    private void setGuiProperties()
    {
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo("")==0)
        {
            setEnabledClean_AllGui(false,false);
            row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME = "";
            //jL_text_Rel.setText("");
            jB_commit.setEnabled(false);
            return;
        }
        
        if(row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT == 1)
            jChBox_hexout.setSelected(true);
        else
            jChBox_hexout.setSelected(false);

        if(row_TAB_RELAZIONI_ELEMENTO.NETWORK == 1)
            jChBox_network.setSelected(true);
        else
            jChBox_network.setSelected(false);
        
        String strDirection = VectorDirections.getDesc(row_TAB_RELAZIONI_ELEMENTO.DIREZIONE);
        if(strDirection == null)
            jCB_directions.setSelectedIndex(0);
        else
            jCB_directions.setSelectedItem(strDirection);

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
            jTable_TE_build.setEnabled(true);
            jTable_TE_build.setBackground(java.awt.Color.white);
            setEnabledClean_AllGui(true,false);
        }
        else
            setEnabledClean_AllGui(false,false);
        
        jB_commit.setEnabled(true);
    }
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        setEnabledClean_AllGui(false,true);
        AddRowJTabel_TE();
        AddRowJTabel_REL(null);
        jTable_TE.clearSelection();
    }//GEN-LAST:event_jB_cancelActionPerformed

    
    private String getDescriptionREL(String RELATION_NAME_Indeces)
    {    
        // Costruisco e ricerco relazione con ID
        StringTokenizer STKrelationName = new StringTokenizer(RELATION_NAME_Indeces,"::");
        String dot = "";
        String RelationNameTag = "";
        while(STKrelationName.hasMoreTokens())
        {     
            int id_Relation = new Integer(STKrelationName.nextToken()).intValue();
            RelationNameTag += dot+parent_V_TAB_CONFIG.getTagTE(id_Relation);
            dot = "::";
        }    
        return RelationNameTag;
    }
    
    
    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        //System.out.println("Commit");
        this.setCursor(Cur_wait);
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            setEnabledClean_AllGui(false,true);
            setSelected_jTable_TE();
            this.setCursor(Cur_default);
            return;
        }
        
        if(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.length() != 0)
        {
            if(jrb_delete.isSelected())
            {
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete Relation "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+". Confirm ?","Warning",6,300,250);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    String strMessage = "Warning: the following Relations will be deleted. \n";
                    String strMessage1= "";
                    int op1_H = 180;
                    int op1_W = 350;
                    boolean Removed_row = Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.removeElement(row_TAB_RELAZIONI_ELEMENTO);
                    
                    if(Removed_row)
                    {
                        //----- rimuove relazioni dipendenti da quella eliminata;
                        Vector V_Removed_Rows = new Vector();
                        for(int s=0; s<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); s++)
                        {                        
                            ADAMS_TAB_RELAZIONI_ELEMENTO appo_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(s));
                            if(appo_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.startsWith(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME))
                                V_Removed_Rows.addElement(appo_TAB_RELAZIONI_ELEMENTO);
                        }
                        
                        for(int t=0; t<V_Removed_Rows.size(); t++)
                        {                                       
                            ADAMS_TAB_RELAZIONI_ELEMENTO removed_row = (ADAMS_TAB_RELAZIONI_ELEMENTO)(V_Removed_Rows.elementAt(t));
                            strMessage1 += getDescriptionREL(removed_row.RELATION_NAME)+"\n";
                            if(removed_row.RELATION_NAME.length() <= 20 )
                                op1_W = 350;
                            else if (removed_row.RELATION_NAME.length() <= 30 )
                                op1_W = 550;
                            else if (removed_row.RELATION_NAME.length() <= 40 )
                                op1_W = 650;
                            else if (removed_row.RELATION_NAME.length() >= 40 )
                                op1_W = 750;
                            
                            op1_H = op1_H+20;
                            Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.removeElement(removed_row);
                        }
                        
                        if(strMessage1.length() > 0)
                        {
                            ADAMS_JD_Message op1 = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,strMessage+strMessage1+"\nPlease verify and confirm operation.","Warning",6,op1_W,op1_H);
                            int Yes_No1 = op1.getAnswer();
                            if(Yes_No1 == 1)
                            {                            
                                int AnswerDelete1 = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);

                                if(AnswerDelete1 >= 0)
                                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Relation "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+" has been deleted.","INFO",3,300,250);
                                else
                                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);                            
                            }
                            
                        }
                        else
                        {
                            int AnswerDelete = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);

                            if(AnswerDelete >= 0)
                                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Relation "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+" has been deleted.","INFO",3,300,250);
                            else
                                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                        }
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"(Error) Operation failed.","ERROR",1);
                                        
                    setSelected_jTable_TE();
                    setEnabledClean_AllGui(false,true);
                        
                }// end Yes_No == 1 Delete 

            }// end jrb_delete.isSelected()
            else
            {
                    
             //Controllo se esiste già la relazione ed eventualmente in modifica si replica una relazione esistente.
                for (int i=0; i<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); i++)
                {
                    ADAMS_TAB_RELAZIONI_ELEMENTO ctrl_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(i));
                    String NameRelationCtrl = ctrl_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
                    if((NameRelationCtrl.compareTo(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME) == 0)&&(ctrl_TAB_RELAZIONI_ELEMENTO != row_TAB_RELAZIONI_ELEMENTO))
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Relation exist for Traffic Element: "+Selected_row_TAB_CONFIG.TAG,"WARNING",4);
                        this.setCursor(Cur_default);
                        return;
                    }
                }
                
                row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT = -1;
                if(jChBox_hexout.isSelected())
                    row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT = 1;
                
                row_TAB_RELAZIONI_ELEMENTO.NETWORK = -1;
                if(jChBox_network.isSelected())
                    row_TAB_RELAZIONI_ELEMENTO.NETWORK = 1;
                    
                row_TAB_RELAZIONI_ELEMENTO.GHOST = -1;

                /******* Assegno DIRECTIONS  *****////
                String str_type_S = (String)jCB_directions.getSelectedItem();
                int ID_STATUS = VectorDirections.getID(str_type_S,VectorDirections.getValue(str_type_S));  
                //int ID_STATUS = VectorDirections.getValue(str_type_S);  
                row_TAB_RELAZIONI_ELEMENTO.DIREZIONE = ID_STATUS;    //NUMBER
                // ************* ///
                
                if (row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.size() == 0)
                {
                    //ADAMS_JD_Message op_AN= new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: No Owner Analysis selected.\nContinue ?","Warning",6);
                    //int Yes_No_AN = op_AN.getAnswer();
                    /*if(Yes_No_AN == 0) 
                    {
                        this.setCursor(Cur_default);
                        return;
                    }*/
                    
                     ADAMS_JD_Message op_AN= new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: No Owner Analysis selected.","Warning",4);
                     this.setCursor(Cur_default);
                     return;
                }
                                
                if(jrb_add.isSelected())
                {
                    
                    //************                        
                    StringTokenizer STKrelationName = new StringTokenizer(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME,"::");
                    int NumTokens = STKrelationName.countTokens();
                    
                    /*System.out.print("-- "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                    System.out.println(" -> countTokens =" +NumTokens);*/
                                       
                    int AnswerUpdate = 0;                      
                    if(NumTokens == 2) // Una relazione con due elementi
                    {
                        Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO);
                        AnswerUpdate = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);
                    }
                    else // Relazione composta da più elementi --- va scomposta ed inserita per livelli A::B::C::D --> A::B - A::B::C - A::B::C::D
                    {
                        
                        boolean propagaTies = false;
                        ADAMS_JD_Message op_REL_OBB = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"New Relation chains will be automatically created. Please specify if current Ties list has to be propagated.","Warning",6,300,200);
                        int Yes_No_obb = op_REL_OBB.getAnswer();
                        if(Yes_No_obb == 1)
                            propagaTies = true;
                        
                        String[] strNameRelationsLevel = new String[NumTokens-1];
                        String AppoNameRel = STKrelationName.nextToken();
                        for(int z=0; z<strNameRelationsLevel.length; z++)
                        {
                            AppoNameRel += "::"+STKrelationName.nextToken();
                            strNameRelationsLevel[z] = new String(AppoNameRel);
                            //System.out.println("Composto da strNameRelationsLevel["+z+"] ==>" +strNameRelationsLevel[z]);
                            
                            boolean ADD_REL = true;
                            for (int y=0; y<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); y++)
                            {
                                ADAMS_TAB_RELAZIONI_ELEMENTO ctrl_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(y));
                                String NameRelationCtrl = ctrl_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
                                if(NameRelationCtrl.compareTo(strNameRelationsLevel[z]) == 0)
                                    ADD_REL = false;                                
                            }
                            if(ADD_REL)
                            {                                
                                ADAMS_TAB_RELAZIONI_ELEMENTO row_TAB_RELAZIONI_ELEMENTO_MultiCopia = new ADAMS_TAB_RELAZIONI_ELEMENTO(strNameRelationsLevel[z]);
                                
                                for(int a=0; a<row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.size(); a++)
                                    row_TAB_RELAZIONI_ELEMENTO_MultiCopia.V_LISTA_ANALISI.addElement(row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.elementAt(a));
                                
                                for(int b=0; b<row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.size(); b++)
                                    row_TAB_RELAZIONI_ELEMENTO_MultiCopia.V_RESTRIZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.elementAt(b));
                                                                
                                if((propagaTies)||(z == 0))
                                {
                                    for(int c=0; c<row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.size(); c++)
                                        row_TAB_RELAZIONI_ELEMENTO_MultiCopia.V_RESTRIZIONI_OBBL.addElement(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.elementAt(c));
                                }
                                
                                row_TAB_RELAZIONI_ELEMENTO_MultiCopia.DIREZIONE = row_TAB_RELAZIONI_ELEMENTO.DIREZIONE;
                                row_TAB_RELAZIONI_ELEMENTO_MultiCopia.HEXOUTPUT = row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT;
                                row_TAB_RELAZIONI_ELEMENTO_MultiCopia.NETWORK   = row_TAB_RELAZIONI_ELEMENTO.NETWORK;
                                row_TAB_RELAZIONI_ELEMENTO_MultiCopia.GHOST     = row_TAB_RELAZIONI_ELEMENTO.GHOST;                                
                                
                                Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO_MultiCopia);
                                //System.out.println("Inserisco nel DB ==>" +strNameRelationsLevel[z]);
                            }
                        }
                        AnswerUpdate = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);
                    }
                    //************
                    
                    //Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO);
                    //int AnswerUpdate = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);
                    
                    if(AnswerUpdate >= 1)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB, "Relation: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+" has been added.","INFO",3,300,250);
                                                
                        setEnabledClean_AllGui(false,true);
                        setSelected_jTable_TE();
                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Relation: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+".","ERROR",1,300,250);
                        boolean NotRemoved = Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.removeElement(row_TAB_RELAZIONI_ELEMENTO);
                        if(!NotRemoved)
                            System.out.println("--- error removeNoAdded");
                        this.setCursor(Cur_default);
                        setCounterAllRelation();
                        return;
                    }
                    
                }
                else //jrb_modify.isSelected()
                {
                    String strMessage_UP = "Warning: the following Relations will be deleted. \n";
                    String strMessage_UP1= "";
                    int op1_H = 180;
                    int op1_W = 350;
                     
                    ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change Relation: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+". Confirm ?","Warning",6,300,250);
                    int Yes_No = op.getAnswer();
                    if(Yes_No == 1)
                    {
                        //System.out.println("RELATION_NAME_SELECTED prima della modifica" +RELATION_NAME_SELECTED);
                        boolean Removed_row = Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.removeElement(row_TAB_RELAZIONI_ELEMENTO);
                       
                        //----- rimuove relazioni dipendenti da quella eliminata;
                        Vector V_Removed_Rows = new Vector();
                        for(int s=0; s<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); s++)
                        {                        
                            ADAMS_TAB_RELAZIONI_ELEMENTO appo_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(s));
                            if( (appo_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.startsWith(RELATION_NAME_SELECTED))&&(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME.compareTo(RELATION_NAME_SELECTED)!=0) )
                            {
                                //System.out.println("Rimuovo -->"+appo_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                                V_Removed_Rows.addElement(appo_TAB_RELAZIONI_ELEMENTO);
                            }
                        }
                        
                        for(int t=0; t<V_Removed_Rows.size(); t++)
                        {                                       
                            ADAMS_TAB_RELAZIONI_ELEMENTO removed_row = (ADAMS_TAB_RELAZIONI_ELEMENTO)(V_Removed_Rows.elementAt(t));
                            strMessage_UP1 += getDescriptionREL(removed_row.RELATION_NAME)+"\n";
                            if(removed_row.RELATION_NAME.length() <= 20 )
                                op1_W = 400;
                            else if (removed_row.RELATION_NAME.length() <= 30 )
                                op1_W = 550;
                            else if (removed_row.RELATION_NAME.length() <= 40 )
                                op1_W = 650;
                            else if (removed_row.RELATION_NAME.length() >= 40 )
                                op1_W = 750;
                            
                            op1_H = op1_H+20;
                            Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.removeElement(removed_row);
                        }
                        
                        boolean UpdateFlag = false;
                        if(strMessage_UP1.length() > 0)
                        {
                            ADAMS_JD_Message op1 = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,strMessage_UP+strMessage_UP1+"\nPlease verify and confirm operation.","Warning",6,op1_W,op1_H);
                            int Yes_No1 = op1.getAnswer();
                            if(Yes_No1 == 1)                         
                                UpdateFlag = true;
                            else                            
                                UpdateFlag = false;
                        }
                        else
                            UpdateFlag = true;
                        
                        
                        if(UpdateFlag == true)
                        {                        
                            //************                        
                            StringTokenizer STKrelationName = new StringTokenizer(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME,"::");
                            int NumTokens = STKrelationName.countTokens();

                            /*System.out.print("-- "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                            System.out.println(" -> countTokens =" +NumTokens);*/

                            int AnswerUpdate = 0;                      
                            if(NumTokens == 2) // Una relazione con due elementi
                            {
                                //System.out.println("2 Modify -- ricreo "+row_TAB_RELAZIONI_ELEMENTO);
                                Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO);
                                AnswerUpdate = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);
                            }
                            else // Relazione composta da più elementi --- va scomposta ed inserita per livelli A::B::C::D --> A::B - A::B::C - A::B::C::D
                            {

                                String[] strNameRelationsLevel = new String[NumTokens-1];
                                String AppoNameRel = STKrelationName.nextToken();
                                for(int z=0; z<strNameRelationsLevel.length; z++)
                                {
                                    AppoNameRel += "::"+STKrelationName.nextToken();
                                    strNameRelationsLevel[z] = new String(AppoNameRel);
                                    //System.out.println("Composto da strNameRelationsLevel["+z+"] ==>" +strNameRelationsLevel[z]);

                                    boolean ADD_REL = true;
                                    for (int y=0; y<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); y++)
                                    {
                                        ADAMS_TAB_RELAZIONI_ELEMENTO ctrl_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)(Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(y));
                                        String NameRelationCtrl = ctrl_TAB_RELAZIONI_ELEMENTO.RELATION_NAME;
                                        if(NameRelationCtrl.compareTo(strNameRelationsLevel[z]) == 0)
                                            ADD_REL = false;                                
                                    }
                                    if(ADD_REL)
                                    {                                
                                        ADAMS_TAB_RELAZIONI_ELEMENTO row_TAB_RELAZIONI_ELEMENTO_MultiCopia = new ADAMS_TAB_RELAZIONI_ELEMENTO(strNameRelationsLevel[z]);

                                        for(int a=0; a<row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.size(); a++)
                                            row_TAB_RELAZIONI_ELEMENTO_MultiCopia.V_LISTA_ANALISI.addElement(row_TAB_RELAZIONI_ELEMENTO.V_LISTA_ANALISI.elementAt(a));

                                        for(int b=0; b<row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.size(); b++)
                                            row_TAB_RELAZIONI_ELEMENTO_MultiCopia.V_RESTRIZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI.elementAt(b));
                                        
                                        for(int c=0; c<row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.size(); c++)
                                            row_TAB_RELAZIONI_ELEMENTO_MultiCopia.V_RESTRIZIONI_OBBL.addElement(row_TAB_RELAZIONI_ELEMENTO.V_RESTRIZIONI_OBBL.elementAt(c));

                                        row_TAB_RELAZIONI_ELEMENTO_MultiCopia.DIREZIONE = row_TAB_RELAZIONI_ELEMENTO.DIREZIONE;
                                        row_TAB_RELAZIONI_ELEMENTO_MultiCopia.HEXOUTPUT = row_TAB_RELAZIONI_ELEMENTO.HEXOUTPUT;
                                        row_TAB_RELAZIONI_ELEMENTO_MultiCopia.NETWORK   = row_TAB_RELAZIONI_ELEMENTO.NETWORK;
                                        row_TAB_RELAZIONI_ELEMENTO_MultiCopia.GHOST     = row_TAB_RELAZIONI_ELEMENTO.GHOST;                                

                                        Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.addElement(row_TAB_RELAZIONI_ELEMENTO_MultiCopia);
                                        //System.out.println("Inserisco nel DB ==>" +strNameRelationsLevel[z]);
                                    }
                                }
                                AnswerUpdate = Selected_row_TAB_CONFIG.update_LISTA_RELAZIONI(true);
                            }

                            if(AnswerUpdate >= 0)
                                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Relation: "+getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME)+" has been changed.","INFO",3,300,250);
                            else
                                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                        }
                        
                        setSelected_jTable_TE();  
                        setEnabledClean_AllGui(false,true);                        
                    }
                }
            }
        }
        setCounterAllRelation();
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        jB_addElem.setEnabled(false);
        jB_remLast.setEnabled(false);
        
        setGuiInit();
        jL_relation.setBackground(Color.red);        
        jTable_REL.setSelectionBackground(Color.red);
        
        jL_RelationSize_MaxSize.setText("");
        jL_RelationSize_MaxSize.setBackground(colordefault);
        
        jTA_help.setText("HELP Delete... ");
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
        jB_addElem.setEnabled(false); // se true funziona tutto
        jB_remLast.setEnabled(false); // se true funziona tutto
        
        setGuiInit();
        jL_relation.setBackground(Color.green.darker());        
        jTable_REL.setSelectionBackground(Color.yellow);
        
        jL_RelationSize_MaxSize.setText("");
        jL_RelationSize_MaxSize.setBackground(colordefault);
        
        
        jTA_help.setText("HELP Modify... ");
    }//GEN-LAST:event_jrb_modifyActionPerformed

    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        jB_addElem.setEnabled(true);
        jB_remLast.setEnabled(true);
        
        setGuiInit();
        jL_relation.setBackground(Color.green.darker());        
        jTable_REL.setSelectionBackground(Color.yellow);
        
        jL_RelationSize_MaxSize.setText("");
        jL_RelationSize_MaxSize.setBackground(colordefault);
        
        jTA_help.setText(str_help_add);        
    }//GEN-LAST:event_jrb_addActionPerformed

    private void setGuiInit()
    {
        setEnabledClean_AllGui(false,true);
        jTable_TE.clearSelection();
        
        jTable_REL.setEnabled(false);
        jTable_REL.setBackground(colordefault);
        AddRowJTabel_REL(null);
        
        jTable_TE_build.setEnabled(false);
        jTable_TE_build.setBackground(colordefault);
    }
    
    private void jTable_TEMouseReleased(java.awt.event.MouseEvent evt) 
    {
        this.setCursor(Cur_wait);
        
        
        int m = evt.getModifiers();         
        if ((m & java.awt.event.InputEvent.BUTTON3_MASK)!=0)
        {
            jPopupMenu.setCursor(Cur_wait);
            //System.out.println("jTable_TEMouseReleased tasto DESTRO");
            int selectedRow = jTable_TE.getSelectedRow();            
            if(selectedRow >= 0)
            {
                jMItem_RelAss.setVisible(true);
                jMItem_RelAss.setText(jTable_TE.getValueAt(selectedRow,1)+ ": Visualizza Relazioni Associate.");                
                jPopupMenu.show((java.awt.Component)evt.getSource(),evt.getX(),evt.getY());
            }
            else
            {
                jMItem_RelAss.setVisible(false);
                jPopupMenu.show((java.awt.Component)evt.getSource(),evt.getX(),evt.getY());
            }
            jPopupMenu.setCursor(Cur_default);
        }
        else
        {
            //System.out.println("jTable_TEMouseReleased tasto SINISTRO");
            setSelected_jTable_TE();
        }
        this.setCursor(Cur_default);
    }  
    
    private void jTable_RELMouseReleased(java.awt.event.MouseEvent evt) 
    {
        this.setCursor(Cur_wait);
        
        int selectedRow = jTable_REL.getSelectedRow();
        if(selectedRow >= 0)
        {
            boolean ctrl_row = false;
            String str_RelationName = new String(""+jTable_REL.getValueAt(selectedRow,0));
            String RelationNameTag = "";
            for(int i=0; i<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); i++) 
            {
                row_TAB_RELAZIONI_ELEMENTO = (ADAMS_TAB_RELAZIONI_ELEMENTO)Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(i);         
                
                // Costruisco e ricerco relazione con ID
                RelationNameTag = getDescriptionREL(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                  
                if(RelationNameTag.compareTo(str_RelationName) == 0)
                {
                    //System.out.println("Selezionate e trovata relazione "+row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
                    ctrl_row = true;
                    break;
                }                
            }
            
            if(ctrl_row == false)
            {
                row_TAB_RELAZIONI_ELEMENTO = null;
                System.out.println("----- ******* ERRORE Relation selected row_TAB_RELAZIONI_ELEMENTO");
                
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"----- ERROR Relation selected----- . Reload List.","ERROR",1);
                
                this.setCursor(Cur_default);
                
                setEnabledClean_AllGui(false,true);
                AddRowJTabel_TE();
                AddRowJTabel_REL(null);
                jTable_TE.clearSelection();
                
                return;
                
            }
            
            RELATION_NAME_SELECTED = new String(row_TAB_RELAZIONI_ELEMENTO.RELATION_NAME);
            jL_relation.setText(RelationNameTag);
            jL_relation.setToolTipText(jL_relation.getText());
            
            jL_RelationSize_MaxSize.setText("");
            jL_RelationSize_MaxSize.setBackground(colordefault);
            
        // Relation Single Operation 
            //jL_text_Rel.setText(RelationNameTag);
            setGuiProperties();
        }
        this.setCursor(Cur_default);       
    } 
    private void jTable_TE_buildMouseReleased(java.awt.event.MouseEvent evt) 
    {
        this.setCursor(Cur_wait);
        
        int TE_Selection = jTable_TE.getSelectedRow();
        int selectedRow_build = jTable_TE_build.getSelectedRow();
        
        if((selectedRow_build < 0)||(TE_Selection < 0))
        {            
            this.setCursor(Cur_default);
            return;
        }
        
        //System.out.println(((Integer)(jTable_TE_build.getValueAt(selectedRow_build,0))).intValue()+" == "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO);
        int ID_TE_Build_selected    = ((Integer)(jTable_TE_build.getValueAt(selectedRow_build,0))).intValue();
        String TAG_TE_Build_selected   = ""+jTable_TE_build.getValueAt(selectedRow_build,1);
        
        if( ID_TE_Build_selected == Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO )
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Your Relation already contains: "+Selected_row_TAB_CONFIG.TAG,"WARNING",4);
            jTable_TE_build.clearSelection();
            jTArea_DescSelected.setText("");
            ID_TE_Build_selected = -1;
            TAG_TE_Build_selected = "";
            
            this.setCursor(Cur_default);
            return;
        }
        else
        {
            jTArea_DescSelected.setText(parent_V_TAB_CONFIG.getDescTE(ID_TE_Build_selected));
        }
        this.setCursor(Cur_default);
    }
    
    //Permette di resettare e ripulire tutti i widget di input dell'interfaccia.
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {            
        //jL_text_Rel.setEnabled(enable);
        jChBox_hexout.setEnabled(enable);
        jChBox_network.setEnabled(enable);
        
        jTF_analysis.setEnabled(enable);
        jTF_restriction.setEnabled(enable);
        jTF_ties.setEnabled(enable);
        jCB_directions.setEnabled(enable);
        
        jB_analysis.setEnabled(enable);
        jB_restriction.setEnabled(enable);
        jB_ties.setEnabled(enable);

        if(clean)
        {            
            //jTable_TE.clearSelection();
            //jTable_REL.clearSelection();
            
            jTArea_DescSelected.setText("");
            jL_relation.setText("");
            jL_relation.setToolTipText(jL_relation.getText());
            
            jL_RelationSize_MaxSize.setText("");
            jL_RelationSize_MaxSize.setBackground(colordefault);
            
            //jL_text_Rel.setText("");
            
            jTF_analysis.setText(StrNotDefined);
            jTF_analysis.setForeground(Color.red);
            jTF_restriction.setText(StrNotDefined);
            jTF_restriction.setForeground(Color.red);
            jTF_ties.setText(StrNotDefined);
            jTF_ties.setForeground(Color.red);
            
            jChBox_hexout.setSelected(false);
            jChBox_network.setSelected(false);
            
            this.jB_commit.setEnabled(false);

            try
            {
                jCB_directions.setSelectedIndex(0);
            }
            catch(Exception e){}
        }
  
    }
    
    private void setGlobalFont()
    {
        //Font Globali
        jL_title.setFont(ADAMS_GlobalParam.font_B12);
        //jL_counter.setFont(ADAMS_GlobalParam.font_B10);
        
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);
        jLabel10.setFont(ADAMS_GlobalParam.font_B10);
        jChBox_hexout.setFont(ADAMS_GlobalParam.font_B10);
        jChBox_network.setFont(ADAMS_GlobalParam.font_B10);
        jLabel8.setFont(ADAMS_GlobalParam.font_B10);
        jLabel13.setFont(ADAMS_GlobalParam.font_B10);
        jLabel12.setFont(ADAMS_GlobalParam.font_B10);
        jTF_analysis.setFont(ADAMS_GlobalParam.font_B10);
        jTF_restriction.setFont(ADAMS_GlobalParam.font_B10);
        jTF_ties.setFont(ADAMS_GlobalParam.font_B10);
        jCB_directions.setFont(ADAMS_GlobalParam.font_B10);
        
        jTArea_DescSelected.setFont(ADAMS_GlobalParam.font_B10);
        jL_relation.setFont(ADAMS_GlobalParam.font_B10);
        jL_RelationSize_MaxSize.setFont(ADAMS_GlobalParam.font_B10);
        
        jLabel3.setFont(ADAMS_GlobalParam.font_B10);
        jB_addElem.setFont(ADAMS_GlobalParam.font_B10);
        jB_remLast.setFont(ADAMS_GlobalParam.font_B10);
        //jLabel9.setFont(ADAMS_GlobalParam.font_B10);
        jTA_help.setFont(ADAMS_GlobalParam.font_P10);
        //jL_text_Rel.setFont(ADAMS_GlobalParam.font_B10);
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
    
    private void printLISTA_RELAZIONI()
    {
        System.out.println("V_LISTA_RELAZIONI.size() -> "+Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size());
        for(int i=0; i<Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.size(); i++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO rel_elemento = (ADAMS_TAB_RELAZIONI_ELEMENTO)Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.elementAt(i);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JMenuItem jMItem_RelAss;
    private javax.swing.JMenuItem jMItem_Rel_with_RestObl;
    private javax.swing.JPanel jP_center;
    private javax.swing.JScrollPane jScrollPane_TE_build;
    private javax.swing.JPanel jP_c3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jB_addElem;
    private javax.swing.JButton jB_remLast;
    private javax.swing.JTextArea jTA_help;
    private javax.swing.JTextArea jTArea_DescSelected;
    private javax.swing.JScrollPane jScrollPane_REL;
    private javax.swing.JScrollPane jScrollPane_TE;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JComboBox jCB_directions;
    private javax.swing.JCheckBox jChBox_hexout;
    private javax.swing.JCheckBox jChBox_network;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jL_relation;
    private javax.swing.JLabel jL_RelationSize_MaxSize;
    private javax.swing.JPanel jP_ss;
    private javax.swing.JButton jB_commit;
    private javax.swing.JPanel jP_sn;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JPanel jP_north;
    private javax.swing.JLabel jL_title;
    // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_TE;
    private TableSorter sorter_TE;
    private MyTableModel myTableModel_TE;
    
    private javax.swing.JTable jTable_REL;
    private TableSorter sorter_REL;
    private MyTableModel myTableModel_REL;
    
    private javax.swing.JTable jTable_TE_build;
    private TableSorter sorter_TE_build;
    private MyTableModel myTableModel_TE_build;
    
    private String str_help_add = null;
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined"; 
    private Color colordefault      = new java.awt.Color(230,230,230);
    
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;
    private ADAMS_TAB_CONFIG Selected_row_TAB_CONFIG;
    
    private ADAMS_TAB_RELAZIONI_ELEMENTO row_TAB_RELAZIONI_ELEMENTO;
    
    private VectorHelp VectorDirections = null;
    private String RELATION_NAME_SELECTED = "";
    
}

