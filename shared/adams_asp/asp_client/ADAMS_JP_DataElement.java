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

public class ADAMS_JP_DataElement extends javax.swing.JPanel {

    /** Creates new form JPanel */
    public ADAMS_JP_DataElement(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {
        initComponents();
        this.local_INFO_CONFIG = INFO_CONFIG;        
        
        jLabel1.setText("Traffic Element Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
         
        //filtri TextField 
        jText_tag.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        jText_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));
        jText_valueRange.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC_RANGE,15));
        jText_defaultVal.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,10));
        jText_priority.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,10));
        jText_SkeyLength.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,10));
        jText_PluginPath.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,1536));
        
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_TE = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_TE.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_TE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_TE.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_TE.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_TE.setRowHeight(20);
        //jTable_TE.setSelectionBackground(java.awt.Color.green);
        //jTable_TE.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_TE.setRowMargin(2);
        jTable_TE.setAutoCreateColumnsFromModel(false);
        
        jScrollPane1.setViewportView(jTable_TE);
        
        jTable_TE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TEMouseReleased(evt);
            }
        });
        
        // **************************** //
        
        event_jCBox_LinkTE = (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_LinkTEActionPerformed(evt);
            }
        });
                       
        //Font Globali widget
        setGlobalFont();
        
        //Cursor mouse
        jCBox_LinkTE.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jCBox_GUIObj.setCursor(Cur_hand);
        jB_edit_values.setCursor(Cur_hand);
        jCheckBox_hexInput.setCursor(Cur_hand);
        jCBox_compSel.setCursor(Cur_hand);
        jB_aggrRes.setCursor(Cur_hand);
        jB_except.setCursor(Cur_hand);
        jB_shifter.setCursor(Cur_hand);
        jB_evalScr.setCursor(Cur_hand);
        jCBox_StartModify.setCursor(Cur_hand);
        jB_pluginName.setCursor(Cur_hand);
        jB_editPixMap.setCursor(Cur_hand);
        jCheckBox_showReport.setCursor(Cur_hand);
        jCBox_StartModify.setVisible(false);
        jCBox_StartModify.setBorder(borderRed);
        jCheck_Rest.setCursor(Cur_hand);
        jCheck_ffEnabled.setCursor(Cur_hand);
        
        SetTable(jTable_TE);
        setGUIObject();         //riempe la combo GUI Object
        setCompareSelection();  //riempe la combo Compare Selection
    }
    
    private void SetTable(javax.swing.JTable jtable)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jtable.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
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
    
    public void setTableConfig(ADAMS_Vector_TAB_EVENTI_GUI v_tab_evento_gui)
    {
        this.V_TAB_EVENTI_GUI = v_tab_evento_gui; 
        
        AddRowJTabel_general();
        setSelected_jTableRow();
    }
    
    public void setV_TAB_EVENTI_GUI(ADAMS_Vector_TAB_EVENTI_GUI v_tab_evento_gui)
    {
        this.V_TAB_EVENTI_GUI = v_tab_evento_gui; 
    }

    public ADAMS_Vector_TAB_CONFIG getV_TAB_CONFIG()
    {
        return V_TAB_CONFIG;
    }
    
    public ADAMS_TAB_INFO_CONFIG get_INFO_CONFIG()
    {
        return local_INFO_CONFIG;
    }
    
    private void AddRowJTabel_general()
    {
        jTable_TE.setCursor(Cur_wait);
        V_TAB_CONFIG = new ADAMS_Vector_TAB_CONFIG(this);
        boolean ok_read = V_TAB_CONFIG.read_AllItem(ADAMS_GlobalParam.read_TE);  
             
        /*System.out.println("******* PER RIMUOVERE DALLE RELAZIONE tutte LE RESTRIZIONI  **********");
        System.out.println();        
        
        
        for(int i=0; i<V_TAB_CONFIG.size(); i++)
        {
            ADAMS_TAB_CONFIG appoTE = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);            
                        
            appoTE.read_LISTA_RELAZIONI();
            System.out.println("V_LISTA_RELAZIONI.size() "+appoTE.V_LISTA_RELAZIONI.size());
            
            if(appoTE.V_LISTA_RELAZIONI.size() > 0)
            {
                for(int rx=0; rx<appoTE.V_LISTA_RELAZIONI.size();rx++ )
                {
                    ADAMS_TAB_RELAZIONI_ELEMENTO AppoREL = (ADAMS_TAB_RELAZIONI_ELEMENTO)appoTE.V_LISTA_RELAZIONI.elementAt(rx);

                    //****************
                    System.out.println();
                    System.out.println(" REST di --- "+AppoREL.RELATION_NAME +" --> num Rest "+AppoREL.V_RESTRIZIONI.size());
                    AppoREL.V_RESTRIZIONI.removeAllElements();
                    System.out.println(" DOPO - REST di --- "+AppoREL.RELATION_NAME +" --> num Rest "+AppoREL.V_RESTRIZIONI.size());
                    
                    //****************
                }
               System.out.println("START update_LISTA_RELAZIONI");
               appoTE.update_LISTA_RELAZIONI(false);
               System.out.println("END update_LISTA_RELAZIONI");
            }
        }
        
        System.out.println();
        System.out.println("******* PER RIMUOVERE DALLE RELAZIONE tutte LE RESTRIZIONI  **********");*/
        
        
        jLabel1.setText("Traffic Element Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);

        int SIZE = V_TAB_CONFIG.size();
        Object[][] dataValues = new Object[SIZE][10];
        
        // **** Link TE        
        jCBox_LinkTE.removeActionListener(event_jCBox_LinkTE);
        jCBox_LinkTE.removeAllItems();
        jCBox_LinkTE.addItem(str_none);
        for(int x=0; x<SIZE; x++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG1 = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(x);
            //System.out.println("row_TAB_CONFIG1.LINK_TE "+row_TAB_CONFIG1.TAG);
            if(row_TAB_CONFIG1.LINK_TE <= 0)
                jCBox_LinkTE.addItem(""+row_TAB_CONFIG1.TAG);
        }
        jCBox_LinkTE.setSelectedItem(str_none);        
        jCBox_LinkTE.addActionListener(event_jCBox_LinkTE);
        // ****
       
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);
                        
            row_TAB_CONFIG.read_LISTA_VALORI(); // nested interna
            
            if(row_TAB_CONFIG.POSIZIONE_CAMPO_DR >0)
                dataValues[i][0] = new Boolean(true);
            else
                dataValues[i][0] = new Boolean(false);
            
            dataValues[i][1] = new Integer(row_TAB_CONFIG.POSIZIONE_ELEMENTO);                                //  - Id
            dataValues[i][2] = new String(row_TAB_CONFIG.TAG);                                                //  - Tag
            dataValues[i][3] = new String(row_TAB_CONFIG.DESCRIPTION);                                        //  - Description
            
            try{
                dataValues[i][4] = new String(VectorTypeGuiObj.getDesc(row_TAB_CONFIG.TIPO_OGGETTO_GUI));}    //  - GUI Object
            catch(Exception e){
                dataValues[i][4] = new String(StrNotDefined);}
            
            dataValues[i][5] = new String(row_TAB_CONFIG.RANGE_VAL);                                              //  - Value Range

            String strListaValori = StrNotDefined;
            if(row_TAB_CONFIG.V_LISTA_VALORI.size() != 0) //|| (row_TAB_CONFIG.V_LISTA_VALORI == null))
                strListaValori = strDefined;
            dataValues[i][6] = new String(strListaValori);                              //  - Values
            
            dataValues[i][7] = new Integer(row_TAB_CONFIG.VALORE_DEFAULT);            //  - Default Val.
            
            if(row_TAB_CONFIG.DEFAULT_RESTRICTION == 'Y')
                dataValues[i][8] = new Boolean(true);                                   //  - Restriction Default 
            else
                dataValues[i][8] = new Boolean(false);
            
            if(row_TAB_CONFIG.FFENABLED == 'Y')
                dataValues[i][9] = new Boolean(true);                                   //  - Enabled for Free Format Ralation
            else
                dataValues[i][9] = new Boolean(false);
        }        
        
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);
        
        jScrollPane1.setViewportView(jTable_TE);
        
        jTable_TE.setCursor(Cur_default);
    }
    
    private void setGUIObject()
    {
        jCBox_GUIObj.removeAllItems();
        VectorTypeGuiObj = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_GUI);
        
        Vector AllDesc = VectorTypeGuiObj.vgetDesc();
        
        for(int i=0; i<AllDesc.size(); i++)
            jCBox_GUIObj.addItem((String)AllDesc.elementAt(i));
        
        //VectorTypeGuiObj.stampaAll();
    }
    
    private void setCompareSelection()
    {
        jCBox_compSel.removeAllItems();
        VectorCompareSel = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_GENERIC);
        
        Vector AllDesc = VectorCompareSel.vgetDesc();        
        for(int i=0; i<AllDesc.size(); i++)
            jCBox_compSel.addItem((String)AllDesc.elementAt(i));
        
       // VectorCompareSel.stampaAll();
    }
       
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jPs_c = new javax.swing.JPanel();
        jL_desc = new javax.swing.JLabel();
        jText_desc = new javax.swing.JTextField();
        jCheck_Rest = new javax.swing.JCheckBox();
        jL_priority = new javax.swing.JLabel();
        jText_priority = new javax.swing.JTextField();
        jL_SkeyLength = new javax.swing.JLabel();
        jText_SkeyLength = new javax.swing.JTextField();
        jP_tag = new javax.swing.JPanel();
        jL_tag = new javax.swing.JLabel();
        jText_tag = new javax.swing.JTextField();
        jCBox_LinkTE = new javax.swing.JComboBox();
        jL_LinkTE = new javax.swing.JLabel();
        jP_plugin = new javax.swing.JPanel();
        jText_pluginName = new javax.swing.JTextField();
        jB_pluginName = new javax.swing.JButton();
        jPanel_GUI = new javax.swing.JPanel();
        jText_pluginName_GUI = new javax.swing.JTextField();
        jB_pluginName_GUI = new javax.swing.JButton();
        jText_PluginPath = new javax.swing.JTextField();
        jP_evalScr = new javax.swing.JPanel();
        jText_evalScr = new javax.swing.JTextField();
        jB_evalScr = new javax.swing.JButton();
        jP_shifter = new javax.swing.JPanel();
        jText_shifter = new javax.swing.JTextField();
        jB_shifter = new javax.swing.JButton();
        jP_except = new javax.swing.JPanel();
        jText_except = new javax.swing.JTextField();
        jB_except = new javax.swing.JButton();
        jP_aggrRes = new javax.swing.JPanel();
        jTextAggrRes = new javax.swing.JTextField();
        jB_aggrRes = new javax.swing.JButton();
        jL_PluginPath = new javax.swing.JLabel();
        jL_Plugin = new javax.swing.JLabel();
        jL_evalScr = new javax.swing.JLabel();
        jL_shifter = new javax.swing.JLabel();
        jL_except = new javax.swing.JLabel();
        jL_aggrRes = new javax.swing.JLabel();
        jText_defaultVal = new javax.swing.JTextField();
        jL_defaultVal = new javax.swing.JLabel();
        jText_valueRange = new javax.swing.JTextField();
        jLvalueRange = new javax.swing.JLabel();
        jP_LB_values = new javax.swing.JPanel();
        jB_edit_values = new javax.swing.JButton();
        jText_values = new javax.swing.JTextField();
        jL_values = new javax.swing.JLabel();
        jCBox_compSel = new javax.swing.JComboBox();
        jL_compSel = new javax.swing.JLabel();
        jCheckBox_hexInput = new javax.swing.JCheckBox();
        jL_pixURL = new javax.swing.JLabel();
        jCBox_GUIObj = new javax.swing.JComboBox();
        jL_GUIObj = new javax.swing.JLabel();
        jP_Pixmap = new javax.swing.JPanel();
        jT_pixmap = new javax.swing.JTextField();
        jB_editPixMap = new javax.swing.JButton();
        jCheckBox_showReport = new javax.swing.JCheckBox();
        jCheck_ffEnabled = new javax.swing.JCheckBox();
        jL_Plugin_GUI = new javax.swing.JLabel();
        jPs_s = new javax.swing.JPanel();
        jP_South_east = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jP_South_west = new javax.swing.JPanel();
        jP_center = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jCBox_StartModify = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 5));
        setLayout(new java.awt.BorderLayout(2, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/te_32.png"))); // NOI18N
        jLabel1.setText("Traffic Element Build");
        jLabel1.setMinimumSize(new java.awt.Dimension(186, 40));
        jLabel1.setPreferredSize(new java.awt.Dimension(186, 40));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setOpaque(false);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Traffic Element Single Operation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11))); // NOI18N
        jP_south.setPreferredSize(new java.awt.Dimension(10, 220));
        jP_south.setLayout(new java.awt.BorderLayout());

        jPs_c.setBackground(new java.awt.Color(230, 230, 230));
        jPs_c.setPreferredSize(new java.awt.Dimension(834, 150));
        jPs_c.setLayout(new java.awt.GridBagLayout());

        jL_desc.setBackground(new java.awt.Color(230, 230, 230));
        jL_desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_desc.setText("Description");
        jL_desc.setToolTipText("(Max 160 Characters)");
        jL_desc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_desc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_desc.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_desc, gridBagConstraints);

        jText_desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_desc.setToolTipText("(Max 160 Characters)");
        jText_desc.setMinimumSize(new java.awt.Dimension(190, 22));
        jText_desc.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jText_desc, gridBagConstraints);

        jCheck_Rest.setBackground(new java.awt.Color(230, 230, 230));
        jCheck_Rest.setText("Default Rest.");
        jCheck_Rest.setToolTipText("Default Restriction");
        jCheck_Rest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheck_Rest.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheck_Rest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCheck_Rest.setIconTextGap(2);
        jCheck_Rest.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheck_Rest.setMinimumSize(new java.awt.Dimension(55, 22));
        jCheck_Rest.setPreferredSize(new java.awt.Dimension(55, 22));
        jCheck_Rest.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheck_Rest.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCheck_Rest.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheck_Rest.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jCheck_Rest, gridBagConstraints);

        jL_priority.setBackground(new java.awt.Color(230, 230, 230));
        jL_priority.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_priority.setText("Priority Key");
        jL_priority.setToolTipText("Only Numbers");
        jL_priority.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_priority.setMinimumSize(new java.awt.Dimension(40, 22));
        jL_priority.setPreferredSize(new java.awt.Dimension(30, 22));
        jL_priority.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_priority.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_priority, gridBagConstraints);

        jText_priority.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jText_priority.setToolTipText("Only Numbers");
        jText_priority.setMinimumSize(new java.awt.Dimension(40, 22));
        jText_priority.setPreferredSize(new java.awt.Dimension(30, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jText_priority, gridBagConstraints);

        jL_SkeyLength.setBackground(new java.awt.Color(230, 230, 230));
        jL_SkeyLength.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_SkeyLength.setText("SubKey Length");
        jL_SkeyLength.setToolTipText("Only Numbers");
        jL_SkeyLength.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_SkeyLength.setMinimumSize(new java.awt.Dimension(40, 21));
        jL_SkeyLength.setPreferredSize(new java.awt.Dimension(40, 21));
        jL_SkeyLength.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_SkeyLength.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_SkeyLength, gridBagConstraints);

        jText_SkeyLength.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jText_SkeyLength.setToolTipText("Only Numbers");
        jText_SkeyLength.setMinimumSize(new java.awt.Dimension(4, 22));
        jText_SkeyLength.setPreferredSize(new java.awt.Dimension(4, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jText_SkeyLength, gridBagConstraints);

        jP_tag.setBackground(new java.awt.Color(230, 230, 230));
        jP_tag.setMinimumSize(new java.awt.Dimension(270, 47));
        jP_tag.setPreferredSize(new java.awt.Dimension(270, 47));
        jP_tag.setLayout(new java.awt.GridBagLayout());

        jL_tag.setBackground(new java.awt.Color(230, 230, 230));
        jL_tag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_tag.setText("Tag");
        jL_tag.setToolTipText("(Max 30 Characters)");
        jL_tag.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_tag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_tag.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jP_tag.add(jL_tag, gridBagConstraints);

        jText_tag.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_tag.setToolTipText("(Max 30 Characters)");
        jText_tag.setMinimumSize(new java.awt.Dimension(140, 22));
        jText_tag.setPreferredSize(new java.awt.Dimension(140, 22));
        jText_tag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jText_tagKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_tag.add(jText_tag, gridBagConstraints);

        jCBox_LinkTE.setMaximumRowCount(6);
        jCBox_LinkTE.setMinimumSize(new java.awt.Dimension(140, 22));
        jCBox_LinkTE.setPreferredSize(new java.awt.Dimension(140, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 2, 1);
        jP_tag.add(jCBox_LinkTE, gridBagConstraints);

        jL_LinkTE.setBackground(new java.awt.Color(230, 230, 230));
        jL_LinkTE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_LinkTE.setText("Alias Traffic Element");
        jL_LinkTE.setToolTipText("Alias Traffic Element");
        jL_LinkTE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_LinkTE.setMaximumSize(new java.awt.Dimension(18, 20));
        jL_LinkTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_LinkTE.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jP_tag.add(jL_LinkTE, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPs_c.add(jP_tag, gridBagConstraints);

        jP_plugin.setBackground(new java.awt.Color(230, 230, 230));
        jP_plugin.setLayout(new java.awt.GridBagLayout());

        jText_pluginName.setBackground(new java.awt.Color(230, 230, 230));
        jText_pluginName.setEditable(false);
        jText_pluginName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_pluginName.setToolTipText("");
        jText_pluginName.setFocusable(false);
        jText_pluginName.setMinimumSize(new java.awt.Dimension(65, 22));
        jText_pluginName.setPreferredSize(new java.awt.Dimension(65, 22));
        jText_pluginName.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_plugin.add(jText_pluginName, gridBagConstraints);

        jB_pluginName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_pluginName.setToolTipText("Edit Plugin (Active in modification)");
        jB_pluginName.setBorderPainted(false);
        jB_pluginName.setContentAreaFilled(false);
        jB_pluginName.setFocusPainted(false);
        jB_pluginName.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_pluginName.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_pluginName.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_pluginName.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_pluginName.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_pluginName.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_pluginName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_pluginNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_plugin.add(jB_pluginName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_plugin, gridBagConstraints);

        jPanel_GUI.setBackground(new java.awt.Color(230, 230, 230));
        jPanel_GUI.setLayout(new java.awt.GridBagLayout());

        jText_pluginName_GUI.setBackground(new java.awt.Color(230, 230, 230));
        jText_pluginName_GUI.setEditable(false);
        jText_pluginName_GUI.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_pluginName_GUI.setToolTipText("");
        jText_pluginName_GUI.setFocusable(false);
        jText_pluginName_GUI.setMinimumSize(new java.awt.Dimension(65, 22));
        jText_pluginName_GUI.setPreferredSize(new java.awt.Dimension(65, 22));
        jText_pluginName_GUI.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPanel_GUI.add(jText_pluginName_GUI, gridBagConstraints);

        jB_pluginName_GUI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_pluginName_GUI.setToolTipText("Edit Plugin (Active in modification)");
        jB_pluginName_GUI.setBorderPainted(false);
        jB_pluginName_GUI.setContentAreaFilled(false);
        jB_pluginName_GUI.setFocusPainted(false);
        jB_pluginName_GUI.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_pluginName_GUI.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_pluginName_GUI.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_pluginName_GUI.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_pluginName_GUI.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_pluginName_GUI.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_pluginName_GUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_pluginName_GUIActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel_GUI.add(jB_pluginName_GUI, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jPanel_GUI, gridBagConstraints);

        jText_PluginPath.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_PluginPath.setToolTipText("(Max 1536 Characters)");
        jText_PluginPath.setMinimumSize(new java.awt.Dimension(120, 22));
        jText_PluginPath.setPreferredSize(new java.awt.Dimension(4, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jText_PluginPath, gridBagConstraints);

        jP_evalScr.setBackground(new java.awt.Color(230, 230, 230));
        jP_evalScr.setLayout(new java.awt.GridBagLayout());

        jText_evalScr.setBackground(new java.awt.Color(230, 230, 230));
        jText_evalScr.setEditable(false);
        jText_evalScr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_evalScr.setToolTipText("");
        jText_evalScr.setFocusable(false);
        jText_evalScr.setMinimumSize(new java.awt.Dimension(65, 22));
        jText_evalScr.setPreferredSize(new java.awt.Dimension(65, 22));
        jText_evalScr.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_evalScr.add(jText_evalScr, gridBagConstraints);

        jB_evalScr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_evalScr.setToolTipText("Edit Evaluation Script (Active in modification)");
        jB_evalScr.setBorderPainted(false);
        jB_evalScr.setContentAreaFilled(false);
        jB_evalScr.setFocusPainted(false);
        jB_evalScr.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_evalScr.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_evalScr.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_evalScr.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_evalScr.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_evalScr.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_evalScr.setEnabled(false);
        jB_evalScr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_evalScrActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_evalScr.add(jB_evalScr, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_evalScr, gridBagConstraints);

        jP_shifter.setBackground(new java.awt.Color(230, 230, 230));
        jP_shifter.setLayout(new java.awt.GridBagLayout());

        jText_shifter.setBackground(new java.awt.Color(230, 230, 230));
        jText_shifter.setEditable(false);
        jText_shifter.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_shifter.setToolTipText("");
        jText_shifter.setMinimumSize(new java.awt.Dimension(65, 22));
        jText_shifter.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_shifter.add(jText_shifter, gridBagConstraints);

        jB_shifter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_shifter.setToolTipText("Edit Shifter");
        jB_shifter.setBorderPainted(false);
        jB_shifter.setContentAreaFilled(false);
        jB_shifter.setFocusPainted(false);
        jB_shifter.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_shifter.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_shifter.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_shifter.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_shifter.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_shifter.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_shifter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_shifterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_shifter.add(jB_shifter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_shifter, gridBagConstraints);

        jP_except.setBackground(new java.awt.Color(230, 230, 230));
        jP_except.setLayout(new java.awt.GridBagLayout());

        jText_except.setBackground(new java.awt.Color(230, 230, 230));
        jText_except.setEditable(false);
        jText_except.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_except.setToolTipText("");
        jText_except.setMinimumSize(new java.awt.Dimension(65, 22));
        jText_except.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_except.add(jText_except, gridBagConstraints);

        jB_except.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_except.setToolTipText("Edit Exceptions");
        jB_except.setBorderPainted(false);
        jB_except.setContentAreaFilled(false);
        jB_except.setFocusPainted(false);
        jB_except.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_except.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_except.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_except.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_except.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_except.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_except.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_exceptActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_except.add(jB_except, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_except, gridBagConstraints);

        jP_aggrRes.setBackground(new java.awt.Color(230, 230, 230));
        jP_aggrRes.setMinimumSize(new java.awt.Dimension(60, 23));
        jP_aggrRes.setLayout(new java.awt.GridBagLayout());

        jTextAggrRes.setBackground(new java.awt.Color(230, 230, 230));
        jTextAggrRes.setEditable(false);
        jTextAggrRes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextAggrRes.setToolTipText("");
        jTextAggrRes.setMinimumSize(new java.awt.Dimension(65, 22));
        jTextAggrRes.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_aggrRes.add(jTextAggrRes, gridBagConstraints);

        jB_aggrRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_aggrRes.setToolTipText("Edit Combined GUI");
        jB_aggrRes.setBorderPainted(false);
        jB_aggrRes.setContentAreaFilled(false);
        jB_aggrRes.setFocusPainted(false);
        jB_aggrRes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_aggrRes.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_aggrRes.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_aggrRes.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_aggrRes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_aggrRes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_aggrRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_aggrResActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_aggrRes.add(jB_aggrRes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_aggrRes, gridBagConstraints);

        jL_PluginPath.setBackground(new java.awt.Color(230, 230, 230));
        jL_PluginPath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_PluginPath.setText("Plugin Path");
        jL_PluginPath.setToolTipText("(Max 1536 Characters)");
        jL_PluginPath.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_PluginPath.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_PluginPath.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_PluginPath, gridBagConstraints);

        jL_Plugin.setBackground(new java.awt.Color(230, 230, 230));
        jL_Plugin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Plugin.setText("Plugin");
        jL_Plugin.setToolTipText("");
        jL_Plugin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Plugin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_Plugin.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_Plugin, gridBagConstraints);

        jL_evalScr.setBackground(new java.awt.Color(230, 230, 230));
        jL_evalScr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_evalScr.setText("Evaluation Script");
        jL_evalScr.setToolTipText("");
        jL_evalScr.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_evalScr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_evalScr.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_evalScr, gridBagConstraints);

        jL_shifter.setBackground(new java.awt.Color(230, 230, 230));
        jL_shifter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_shifter.setText("Shifter");
        jL_shifter.setToolTipText("");
        jL_shifter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_shifter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_shifter.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_shifter, gridBagConstraints);

        jL_except.setBackground(new java.awt.Color(230, 230, 230));
        jL_except.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_except.setText("GUI Event");
        jL_except.setToolTipText("");
        jL_except.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_except.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_except.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_except, gridBagConstraints);

        jL_aggrRes.setBackground(new java.awt.Color(230, 230, 230));
        jL_aggrRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_aggrRes.setText("Aggregate Restriction");
        jL_aggrRes.setToolTipText("Combined GUI");
        jL_aggrRes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_aggrRes.setMaximumSize(new java.awt.Dimension(60, 21));
        jL_aggrRes.setMinimumSize(new java.awt.Dimension(60, 21));
        jL_aggrRes.setPreferredSize(new java.awt.Dimension(120, 21));
        jL_aggrRes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_aggrRes.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 1);
        jPs_c.add(jL_aggrRes, gridBagConstraints);

        jText_defaultVal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jText_defaultVal.setToolTipText("Only Numbers");
        jText_defaultVal.setMinimumSize(new java.awt.Dimension(35, 22));
        jText_defaultVal.setPreferredSize(new java.awt.Dimension(4, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 1, 1);
        jPs_c.add(jText_defaultVal, gridBagConstraints);

        jL_defaultVal.setBackground(new java.awt.Color(230, 230, 230));
        jL_defaultVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_defaultVal.setText("Default Value");
        jL_defaultVal.setToolTipText("Only Numbers");
        jL_defaultVal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_defaultVal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_defaultVal.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 1);
        jPs_c.add(jL_defaultVal, gridBagConstraints);

        jText_valueRange.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jText_valueRange.setToolTipText("ValueRange(min-max)");
        jText_valueRange.setMinimumSize(new java.awt.Dimension(30, 22));
        jText_valueRange.setPreferredSize(new java.awt.Dimension(4, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jText_valueRange, gridBagConstraints);

        jLvalueRange.setBackground(new java.awt.Color(230, 230, 230));
        jLvalueRange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLvalueRange.setText("Range");
        jLvalueRange.setToolTipText("ValueRange(min-max)");
        jLvalueRange.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLvalueRange.setMinimumSize(new java.awt.Dimension(20, 21));
        jLvalueRange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLvalueRange.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jLvalueRange, gridBagConstraints);

        jP_LB_values.setBackground(new java.awt.Color(230, 230, 230));
        jP_LB_values.setMinimumSize(new java.awt.Dimension(70, 23));
        jP_LB_values.setLayout(new java.awt.GridBagLayout());

        jB_edit_values.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_edit_values.setToolTipText("Edit Values");
        jB_edit_values.setBorderPainted(false);
        jB_edit_values.setContentAreaFilled(false);
        jB_edit_values.setFocusPainted(false);
        jB_edit_values.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_edit_values.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_edit_values.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_edit_values.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_edit_values.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_edit_values.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_edit_values.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_edit_valuesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_LB_values.add(jB_edit_values, gridBagConstraints);

        jText_values.setBackground(new java.awt.Color(230, 230, 230));
        jText_values.setEditable(false);
        jText_values.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_values.setToolTipText("");
        jText_values.setMinimumSize(new java.awt.Dimension(65, 22));
        jText_values.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_LB_values.add(jText_values, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_LB_values, gridBagConstraints);

        jL_values.setBackground(new java.awt.Color(230, 230, 230));
        jL_values.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_values.setText("List Values");
        jL_values.setToolTipText("");
        jL_values.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_values.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_values.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_values, gridBagConstraints);

        jCBox_compSel.setMaximumRowCount(6);
        jCBox_compSel.setMinimumSize(new java.awt.Dimension(32, 22));
        jCBox_compSel.setPreferredSize(new java.awt.Dimension(32, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jCBox_compSel, gridBagConstraints);

        jL_compSel.setBackground(new java.awt.Color(230, 230, 230));
        jL_compSel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_compSel.setText("Compare Selection");
        jL_compSel.setToolTipText("(Select Item)");
        jL_compSel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_compSel.setMinimumSize(new java.awt.Dimension(110, 21));
        jL_compSel.setPreferredSize(new java.awt.Dimension(110, 21));
        jL_compSel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_compSel.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_compSel, gridBagConstraints);

        jCheckBox_hexInput.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_hexInput.setText("Hexadecimal input");
        jCheckBox_hexInput.setToolTipText("(True/False)");
        jCheckBox_hexInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox_hexInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCheckBox_hexInput.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox_hexInput.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheckBox_hexInput.setPreferredSize(new java.awt.Dimension(120, 21));
        jCheckBox_hexInput.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheckBox_hexInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCheckBox_hexInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheckBox_hexInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jCheckBox_hexInput, gridBagConstraints);

        jL_pixURL.setBackground(new java.awt.Color(230, 230, 230));
        jL_pixURL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_pixURL.setText("Pixmap URL");
        jL_pixURL.setToolTipText("(Max 30 Characters)");
        jL_pixURL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_pixURL.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_pixURL.setPreferredSize(new java.awt.Dimension(100, 21));
        jL_pixURL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_pixURL.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_pixURL, gridBagConstraints);

        jCBox_GUIObj.setMaximumRowCount(6);
        jCBox_GUIObj.setMinimumSize(new java.awt.Dimension(32, 22));
        jCBox_GUIObj.setPreferredSize(new java.awt.Dimension(32, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jCBox_GUIObj, gridBagConstraints);

        jL_GUIObj.setBackground(new java.awt.Color(230, 230, 230));
        jL_GUIObj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_GUIObj.setText("GUI Object");
        jL_GUIObj.setToolTipText("(Select Item)");
        jL_GUIObj.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_GUIObj.setMaximumSize(new java.awt.Dimension(60, 21));
        jL_GUIObj.setMinimumSize(new java.awt.Dimension(60, 21));
        jL_GUIObj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_GUIObj.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_GUIObj, gridBagConstraints);

        jP_Pixmap.setBackground(new java.awt.Color(230, 230, 230));
        jP_Pixmap.setLayout(new java.awt.GridBagLayout());

        jT_pixmap.setBackground(new java.awt.Color(230, 230, 230));
        jT_pixmap.setEditable(false);
        jT_pixmap.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jT_pixmap.setToolTipText("");
        jT_pixmap.setFocusable(false);
        jT_pixmap.setMinimumSize(new java.awt.Dimension(65, 22));
        jT_pixmap.setPreferredSize(new java.awt.Dimension(65, 22));
        jT_pixmap.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jP_Pixmap.add(jT_pixmap, gridBagConstraints);

        jB_editPixMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg"))); // NOI18N
        jB_editPixMap.setToolTipText("Edit Plugin (Active in modification)");
        jB_editPixMap.setBorderPainted(false);
        jB_editPixMap.setContentAreaFilled(false);
        jB_editPixMap.setFocusPainted(false);
        jB_editPixMap.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_editPixMap.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_editPixMap.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_editPixMap.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_editPixMap.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg"))); // NOI18N
        jB_editPixMap.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg"))); // NOI18N
        jB_editPixMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_editPixMapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jP_Pixmap.add(jB_editPixMap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jP_Pixmap, gridBagConstraints);

        jCheckBox_showReport.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_showReport.setText("Always Show Value");
        jCheckBox_showReport.setToolTipText("(True/False)");
        jCheckBox_showReport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox_showReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCheckBox_showReport.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox_showReport.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheckBox_showReport.setPreferredSize(new java.awt.Dimension(120, 21));
        jCheckBox_showReport.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheckBox_showReport.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCheckBox_showReport.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheckBox_showReport.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jCheckBox_showReport, gridBagConstraints);

        jCheck_ffEnabled.setBackground(new java.awt.Color(230, 230, 230));
        jCheck_ffEnabled.setText("Free Format");
        jCheck_ffEnabled.setToolTipText("Free Format Relation");
        jCheck_ffEnabled.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheck_ffEnabled.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheck_ffEnabled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCheck_ffEnabled.setIconTextGap(2);
        jCheck_ffEnabled.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheck_ffEnabled.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheck_ffEnabled.setPreferredSize(new java.awt.Dimension(45, 22));
        jCheck_ffEnabled.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheck_ffEnabled.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCheck_ffEnabled.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCheck_ffEnabled.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPs_c.add(jCheck_ffEnabled, gridBagConstraints);

        jL_Plugin_GUI.setBackground(new java.awt.Color(230, 230, 230));
        jL_Plugin_GUI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Plugin_GUI.setText("Plugin GUI");
        jL_Plugin_GUI.setToolTipText("");
        jL_Plugin_GUI.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Plugin_GUI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_Plugin_GUI.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 1);
        jPs_c.add(jL_Plugin_GUI, gridBagConstraints);

        jP_south.add(jPs_c, java.awt.BorderLayout.CENTER);

        jPs_s.setBackground(new java.awt.Color(230, 230, 230));
        jPs_s.setMinimumSize(new java.awt.Dimension(40, 40));
        jPs_s.setPreferredSize(new java.awt.Dimension(40, 40));
        jPs_s.setLayout(new java.awt.BorderLayout());

        jP_South_east.setBackground(new java.awt.Color(230, 230, 230));
        jP_South_east.setPreferredSize(new java.awt.Dimension(100, 40));
        jP_South_east.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg"))); // NOI18N
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_commit.setPreferredSize(new java.awt.Dimension(80, 28));
        jB_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg"))); // NOI18N
        jB_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg"))); // NOI18N
        jB_commit.setEnabled(false);
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });
        jP_South_east.add(jB_commit);

        jPs_s.add(jP_South_east, java.awt.BorderLayout.EAST);

        jP_South_west.setBackground(new java.awt.Color(230, 230, 230));
        jP_South_west.setPreferredSize(new java.awt.Dimension(305, 40));
        jP_South_west.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setLayout(new java.awt.GridBagLayout());

        jrb_add.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_add);
        jrb_add.setSelected(true);
        jrb_add.setContentAreaFilled(false);
        jrb_add.setFocusPainted(false);
        jrb_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID.jpg"))); // NOI18N
        jrb_add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg"))); // NOI18N
        jrb_add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_over.jpg"))); // NOI18N
        jrb_add.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg"))); // NOI18N
        jrb_add.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg"))); // NOI18N
        jrb_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_addActionPerformed(evt);
            }
        });
        jP_center.add(jrb_add, new java.awt.GridBagConstraints());

        jrb_modify.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_modify);
        jrb_modify.setContentAreaFilled(false);
        jrb_modify.setFocusPainted(false);
        jrb_modify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_modify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID.jpg"))); // NOI18N
        jrb_modify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg"))); // NOI18N
        jrb_modify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_over.jpg"))); // NOI18N
        jrb_modify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg"))); // NOI18N
        jrb_modify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg"))); // NOI18N
        jrb_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_modifyActionPerformed(evt);
            }
        });
        jP_center.add(jrb_modify, new java.awt.GridBagConstraints());

        jrb_delete.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrb_delete);
        jrb_delete.setContentAreaFilled(false);
        jrb_delete.setFocusPainted(false);
        jrb_delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID.jpg"))); // NOI18N
        jrb_delete.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg"))); // NOI18N
        jrb_delete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_over.jpg"))); // NOI18N
        jrb_delete.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg"))); // NOI18N
        jrb_delete.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg"))); // NOI18N
        jrb_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_deleteActionPerformed(evt);
            }
        });
        jP_center.add(jrb_delete, new java.awt.GridBagConstraints());

        jP_South_west.add(jP_center);

        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg"))); // NOI18N
        jB_cancel.setToolTipText("Cancel");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setMaximumSize(new java.awt.Dimension(56, 28));
        jB_cancel.setMinimumSize(new java.awt.Dimension(56, 28));
        jB_cancel.setPreferredSize(new java.awt.Dimension(56, 28));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg"))); // NOI18N
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg"))); // NOI18N
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        jP_South_west.add(jB_cancel);

        jPs_s.add(jP_South_west, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setToolTipText("Enabled Modify (True/False)");
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 5));

        jCBox_StartModify.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_StartModify.setText("Enabled Modify");
        jCBox_StartModify.setToolTipText("Enabled Modify (True/False)");
        jCBox_StartModify.setBorderPainted(true);
        jCBox_StartModify.setBorderPaintedFlat(true);
        jCBox_StartModify.setFocusPainted(false);
        jCBox_StartModify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCBox_StartModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCBox_StartModify.setMaximumSize(new java.awt.Dimension(150, 30));
        jCBox_StartModify.setMinimumSize(new java.awt.Dimension(150, 30));
        jCBox_StartModify.setPreferredSize(new java.awt.Dimension(150, 30));
        jCBox_StartModify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCBox_StartModify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCBox_StartModify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCBox_StartModify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jCBox_StartModify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCBox_StartModifyMouseReleased(evt);
            }
        });
        jPanel1.add(jCBox_StartModify);

        jPs_s.add(jPanel1, java.awt.BorderLayout.CENTER);

        jP_south.add(jPs_s, java.awt.BorderLayout.SOUTH);

        add(jP_south, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jB_pluginName_GUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pluginName_GUIActionPerformed
        ADAMS_JD_setPlugin  JD_PluginSelect_GUI;
        if(SELECTED_PLUGIN_GUI.length() == 0)
            JD_PluginSelect_GUI = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,Selected_row_TAB_CONFIG.PLUGIN);
        else
            JD_PluginSelect_GUI = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,SELECTED_PLUGIN_GUI);
        
                        
        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                JD_PluginSelect_GUI.setGuiEnable(true);
            else
                JD_PluginSelect_GUI.setGuiEnable(false);
        }        
                
        //JD_PluginSelect.show();
        JD_PluginSelect_GUI.setVisible(true);
        
        SELECTED_PLUGIN_GUI = JD_PluginSelect_GUI.getPluginEnabled();
        JD_PluginSelect_GUI.dispose();
        
        if(SELECTED_PLUGIN_GUI.length() >0)        
        {
            jText_pluginName_GUI.setText(strDefined);
            jText_pluginName_GUI.setForeground(Color.green.darker());
        }
        else
        {
            jText_pluginName_GUI.setText(StrNotDefined);
            jText_pluginName_GUI.setForeground(Color.red);       
        }
    }//GEN-LAST:event_jB_pluginName_GUIActionPerformed

    private void jB_editPixMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_editPixMapActionPerformed

        ADAMS_JD_ViewSetImage SetImage;
        
        if(Selected_ICONA.length() == 0)
            SetImage = new ADAMS_JD_ViewSetImage(ADAMS_GlobalParam.JFRAME_TAB, true, 400,300,Selected_row_TAB_CONFIG.ICONA);
        else
            SetImage = new ADAMS_JD_ViewSetImage(ADAMS_GlobalParam.JFRAME_TAB, true, 400,300,Selected_ICONA);
                
        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                SetImage.setGuiEnable(true);
            else
                SetImage.setGuiEnable(false);
        }        
        //SetImage.show();
        SetImage.setVisible(true);
        
        
        Selected_ICONA = SetImage.getNameImage();
        SetImage.dispose();
        
        if(Selected_ICONA.length() >0)        
        {
            jT_pixmap.setText(strDefined);
            jT_pixmap.setForeground(Color.green.darker());
        }
        else
        {
            jT_pixmap.setText(StrNotDefined);
            jT_pixmap.setForeground(Color.red);       
        }

    }//GEN-LAST:event_jB_editPixMapActionPerformed

    private void jB_pluginNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pluginNameActionPerformed
      
        ADAMS_JD_setPlugin  JD_PluginSelect;
        if(SELECTED_PLUGIN.length() == 0)
            JD_PluginSelect = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,Selected_row_TAB_CONFIG.PLUGIN);
        else
            JD_PluginSelect = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,SELECTED_PLUGIN);
        
                        
        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                JD_PluginSelect.setGuiEnable(true);
            else
                JD_PluginSelect.setGuiEnable(false);
        }        
                
        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        SELECTED_PLUGIN = JD_PluginSelect.getPluginEnabled();
        JD_PluginSelect.dispose();
        
        if(SELECTED_PLUGIN.length() >0)        
        {
            jText_pluginName.setText(strDefined);
            jText_pluginName.setForeground(Color.green.darker());
        }
        else
        {
            jText_pluginName.setText(StrNotDefined);
            jText_pluginName.setForeground(Color.red);       
        }
        
    }//GEN-LAST:event_jB_pluginNameActionPerformed

    private void jCBox_LinkTEActionPerformed(java.awt.event.ActionEvent evt) {
       //System.out.println("jCBox_LinkTEActionPerformed "+getIDTE((String)jCBox_LinkTE.getSelectedItem()));       
       
       if(jrb_add.isSelected())
       {   
            String itemSel = (String)jCBox_LinkTE.getSelectedItem();            
            if(itemSel.compareTo(str_none) != 0)
                setGuiforDuplicate( getIDTE(itemSel) );            
       } 
    }
    
    private void setGuiforDuplicate(int read_ID_element)
    {
        // Setto l'interfaccia per il duplicato TE selezionato
        // *********************
        this.setCursor(Cur_wait);
        
        ADAMS_TAB_CONFIG Duplicate_row_TAB_CONFIG = null;
        
        boolean ctrl_row = false;
                
        // cerco l'oggetto ADAMS_TAB_CONFIG tramite POSIZIONE_ELEMENTO (Non rispetta l'ordine del vettore nel caso del Sorter della tabella)
        for(int i=0; i<V_TAB_CONFIG.size(); i++) 
        {
            Duplicate_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);      
            if(Duplicate_row_TAB_CONFIG.POSIZIONE_ELEMENTO == read_ID_element)
            {
                ctrl_row = true;
                break;
            }
        }
        if(ctrl_row == false)
        {
            Duplicate_row_TAB_CONFIG = null;
            System.out.println("----- ERRORE setGuiforDuplicate ...");
            this.setCursor(Cur_default);
            return;
        }
                               
        jText_tag.setText(Duplicate_row_TAB_CONFIG.TAG+"(D)");
        jText_desc.setText(Duplicate_row_TAB_CONFIG.DESCRIPTION+"(D)");                
        jText_valueRange.setText(Duplicate_row_TAB_CONFIG.RANGE_VAL); 
        jText_defaultVal.setText(""+Duplicate_row_TAB_CONFIG.VALORE_DEFAULT); 
        
        Selected_row_TAB_CONFIG.ICONA = Duplicate_row_TAB_CONFIG.ICONA;
        if(Selected_row_TAB_CONFIG.ICONA.length() >0)        
        {
            jT_pixmap.setText(strDefined);
            jT_pixmap.setForeground(Color.green.darker());
        }
        else
        {
            jT_pixmap.setText(StrNotDefined);
            jT_pixmap.setForeground(Color.red);       
        }
        
        jText_priority.setText(""+Duplicate_row_TAB_CONFIG.PRIORITA);
        jText_SkeyLength.setText(""+Duplicate_row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE);             
                
        jCheckBox_hexInput.setSelected(false);
        if(Duplicate_row_TAB_CONFIG.RAPPRESENTAZ_ESADECIMALE == 'Y')
            jCheckBox_hexInput.setSelected(true);
        else
            jCheckBox_hexInput.setSelected(false);                    
                                 
        jCBox_GUIObj.setSelectedItem(VectorTypeGuiObj.getDesc(Duplicate_row_TAB_CONFIG.TIPO_OGGETTO_GUI));
        jCBox_compSel.setSelectedItem(VectorCompareSel.getDesc(Duplicate_row_TAB_CONFIG.OPERATORI_RESTRIZIONE));

        
        Selected_row_TAB_CONFIG.PLUGIN = Duplicate_row_TAB_CONFIG.PLUGIN;        
        if(Selected_row_TAB_CONFIG.PLUGIN.length() >0)        
        {
            jText_pluginName.setText(strDefined);
            jText_pluginName.setForeground(Color.green.darker());
        }
        else
        {
            jText_pluginName.setText(StrNotDefined);
            jText_pluginName.setForeground(Color.red);       
        }
        
        Selected_row_TAB_CONFIG.PLUGIN_GUI = Duplicate_row_TAB_CONFIG.PLUGIN_GUI;        
        if(Selected_row_TAB_CONFIG.PLUGIN_GUI.length() >0)        
        {
            jText_pluginName_GUI.setText(strDefined);
            jText_pluginName_GUI.setForeground(Color.green.darker());
        }
        else
        {
            jText_pluginName_GUI.setText(StrNotDefined);
            jText_pluginName_GUI.setForeground(Color.red);       
        }
        
        
        jText_PluginPath.setText(Duplicate_row_TAB_CONFIG.PLUGIN_PATH);

        if(Duplicate_row_TAB_CONFIG.DEFAULT_RESTRICTION == 'Y')
            jCheck_Rest.setSelected(true);
        else
            jCheck_Rest.setSelected(false);

        if(Duplicate_row_TAB_CONFIG.ALWAYS_SHOW_REPORT == 'Y')
            jCheckBox_showReport.setSelected(true);
        else
            jCheckBox_showReport.setSelected(false);
        
        if(Duplicate_row_TAB_CONFIG.FFENABLED == 'Y')
            jCheck_ffEnabled.setSelected(true);
        else
            jCheck_ffEnabled.setSelected(false);
                

        //////////// Duplico  LA V_LISTA_VALORI e setto GUI
        Selected_row_TAB_CONFIG.V_LISTA_VALORI.removeAllElements();
        int size_LV = Duplicate_row_TAB_CONFIG.V_LISTA_VALORI.size();
        if(size_LV != 0)
        {
            for(int i=0;i<size_LV; i++)
                Selected_row_TAB_CONFIG.V_LISTA_VALORI.addElement(Duplicate_row_TAB_CONFIG.V_LISTA_VALORI.elementAt(i));
            
            jText_values.setText(strDefined);
            jText_values.setForeground(Color.green.darker());
        }
        else
        {
            jText_values.setText(StrNotDefined);
            jText_values.setForeground(Color.red);
        }        
        //////////////////////////////
        
        
        //////////// Duplico  LA V_AGGREGAZ_ELEM_RESTRIZ e setto GUI
        Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.removeAllElements();
        
        Duplicate_row_TAB_CONFIG.read_AGGREGAZ_ELEM_RESTRIZ();
        int size_AG = Duplicate_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.size();
        if(size_AG != 0)
        {
            for(int i=0;i<size_AG; i++)
                Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.addElement(Duplicate_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.elementAt(i));
            
            jTextAggrRes.setText(strDefined);
            jTextAggrRes.setForeground(Color.green.darker());
        }
        else
        {
            jTextAggrRes.setText(StrNotDefined);
            jTextAggrRes.setForeground(Color.red);
        }        
        //////////////////////////////
        
                
        //////////// Duplico  LA V_LISTA_EXCEPTION e setto GUI
        Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.removeAllElements();
        Duplicate_row_TAB_CONFIG.read_LISTA_EXCEPTION();
        int size_EX = Duplicate_row_TAB_CONFIG.V_LISTA_EXCEPTION.size();
        if(size_EX != 0)
        {
            for(int i=0;i<size_EX; i++)
                Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.addElement(Duplicate_row_TAB_CONFIG.V_LISTA_EXCEPTION.elementAt(i));
            
            jText_except.setText(strDefined);
            jText_except.setForeground(Color.green.darker());
        }
        else
        {
            jText_except.setText(StrNotDefined);
            jText_except.setForeground(Color.red);
        }        
        //////////////////////////////
        
                  
        //////////// Duplico  LA V_LISTA_VALORI_SOSTITUZ e setto GUI
        Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.removeAllElements();
        Duplicate_row_TAB_CONFIG.read_LISTA_VALORI_SOSTITUZ();
        int size_LVS = Duplicate_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.size();
        if(size_LVS != 0)
        {
            for(int i=0;i<size_LVS; i++)
                Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.addElement(Duplicate_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.elementAt(i));
            
            jText_shifter.setText(strDefined);
            jText_shifter.setForeground(Color.green.darker());
        }
        else
        {
            jText_shifter.setText(StrNotDefined);
            jText_shifter.setForeground(Color.red);
        }        
        //////////////////////////////
        
        
        //////////// Duplico  LA V_LISTA_SCRIPTS e setto GUI
        /*Selected_row_TAB_CONFIG.V_LISTA_SCRIPTS.removeAllElements();
        Duplicate_row_TAB_CONFIG.read_LISTA_SCRIPTS();
        int size_SCR = Duplicate_row_TAB_CONFIG.V_LISTA_SCRIPTS.size();
        if(size_SCR != 0)
        {
            for(int i=0;i<size_SCR; i++)
                Selected_row_TAB_CONFIG.V_LISTA_SCRIPTS.addElement(Duplicate_row_TAB_CONFIG.V_LISTA_SCRIPTS.elementAt(i));
            
            jText_evalScr.setText(strDefined);
            jText_evalScr.setForeground(Color.green.darker());
        }
        else
        {
            jText_evalScr.setText(StrNotDefined);
            jText_evalScr.setForeground(Color.red);
        }        
        //////////////////////////////*/
                
        jB_commit.setEnabled(true);
        this.setCursor(Cur_wait);
    }
    
    
    
    private void jCBox_StartModifyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBox_StartModifyMouseReleased
        if(jCBox_StartModify.isSelected())
        {            
            jCBox_StartModify.setBorder(borderGreen);
            jTable_TE.setEnabled(false);
            setEnabledClean_AllGui(true,false);
            jB_commit.setEnabled(true);
            
            if(Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR > 0)
                jText_tag.setEnabled(false);
        }
        else
        {            
            jCBox_StartModify.setBorder(borderRed);
            jTable_TE.setEnabled(true);
            setEnabledClean_AllGui(false,false);
            jB_commit.setEnabled(false);
        }
    }//GEN-LAST:event_jCBox_StartModifyMouseReleased

    private void jB_evalScrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_evalScrActionPerformed
        
        this.setCursor(Cur_wait);

        String title =" ( New Traffic Element )";
        if(Selected_row_TAB_CONFIG.TAG.compareTo("")!=0)
            title = " of Traffic Element with Tag: "+Selected_row_TAB_CONFIG.TAG;
        
        Selected_row_TAB_CONFIG.read_LISTA_SCRIPTS();
        ADAMS_JF_WIZARDBASE wizard = new ADAMS_JF_WIZARDBASE(ADAMS_GlobalParam.JFRAME_TAB,0,ADAMS_GlobalParam.SCRIPT_TE,Selected_row_TAB_CONFIG.TIPO_DI_CONFIGURAZIONE,-1,"NTM Basic Wizard for "+title, true, Selected_row_TAB_CONFIG, this);

        //wizard.show();
	wizard.setVisible(true);
        wizard.dispose();

        this.setCursor(Cur_default);

    }//GEN-LAST:event_jB_evalScrActionPerformed

    public void refreshListaScript(boolean flag)
    {
        //System.out.println("refreshListaScript("+flag+")");
        if(flag)
        {
            jText_evalScr.setText(strDefined);
            jText_evalScr.setForeground(Color.green.darker());
        }
        else
        {
            jText_evalScr.setText(StrNotDefined);
            jText_evalScr.setForeground(Color.red);
        }
    }


    private void jB_exceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_exceptActionPerformed
        this.setCursor(Cur_wait);

        String title =" ( New Traffic Element )";
        if(Selected_row_TAB_CONFIG.TAG.compareTo("")!=0)
            title = " of Traffic Element with Tag: "+Selected_row_TAB_CONFIG.TAG;

        JListChooser listChooserEX = new JListChooser(ADAMS_GlobalParam.JFRAME_TAB, "Exceptions"+title, true);

        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                listChooserEX.setEnableButtons(true);
            else
                listChooserEX.setEnableButtons(false);
        }
        
        // Available Items ////////
        int SIZE_ALL_EX = V_TAB_EVENTI_GUI.size();

        Object[][] l1= new Object[SIZE_ALL_EX][2];
        for(int i=0; i<SIZE_ALL_EX; i++)
        {
            ADAMS_TAB_EVENTI_GUI elem_Row_EX = (ADAMS_TAB_EVENTI_GUI)V_TAB_EVENTI_GUI.elementAt(i);
            
            l1[i][0] = new Integer(elem_Row_EX.IDEXCEPTION);         // IDEXCEPTION
            l1[i][1] = new String(elem_Row_EX.TAG);                 //  TAG
        }
        
        // Selected Items  ////////
        int SIZE_SELECTED = Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.size();
        Object[][] l2 = new Object[SIZE_SELECTED][2];

        for(int i=0; i<SIZE_SELECTED; i++)
        {
            Integer elem_Row_EX  = (Integer)Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.elementAt(i);
            l2[i][0] = elem_Row_EX;                                                 // IDEXCEPTION
            l2[i][1] = V_TAB_EVENTI_GUI.getTAG(elem_Row_EX.intValue());             //  TAG
        }

        listChooserEX.setListChooserData(l1, l2);
        //listChooserEX.show();
        listChooserEX.setVisible(true);

        // read selected Items
        boolean empty = true;
        Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.removeAllElements();
        l2 = listChooserEX.getListChooserData();
        for(int i=0; i<l2.length; i++)
        {
            Integer IDEXCEPTION = ((Integer)(l2[i][0]));
            Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.addElement(IDEXCEPTION);
            empty = false;
            //System.out.println("USER DATA:"+l2[i][0]+" - "+l2[i][1]);            
        }

        listChooserEX.dispose();
        
        if(empty)
        {
            jText_except.setText(StrNotDefined);
            jText_except.setForeground(Color.red);
        }
        else
        {
            jText_except.setText(strDefined);
            jText_except.setForeground(Color.green.darker());
        }
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_exceptActionPerformed

    private void jB_shifterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_shifterActionPerformed
        this.setCursor(Cur_wait);
       
        String title =" ( New Traffic Element )";
        if(Selected_row_TAB_CONFIG.TAG.compareTo("")!=0)
            title = " of Traffic Element with Tag: "+Selected_row_TAB_CONFIG.TAG;
        
        ADAMS_JF_ListaValoriSostituzione listaValoriSostituzione = new ADAMS_JF_ListaValoriSostituzione(ADAMS_GlobalParam.JFRAME_TAB,"Shifter"+title,true);
        
        
        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                listaValoriSostituzione.setEnableButtons(true);
            else
                listaValoriSostituzione.setEnableButtons(false);
        }
        
        listaValoriSostituzione.setElements(Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ);
        //listaValoriSostituzione.show();
        listaValoriSostituzione.setVisible(true);
        
        
        Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ = listaValoriSostituzione.getElements();      
        
        int SIZE = Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.size();
        
        if(SIZE > 0)
        {
            jText_shifter.setText(strDefined);
            jText_shifter.setForeground(Color.green.darker());
        }
        else
        {
            jText_shifter.setText(StrNotDefined);
            jText_shifter.setForeground(Color.red);
        }
        
        this.setCursor(Cur_default);        
    }//GEN-LAST:event_jB_shifterActionPerformed

    private boolean ctrlValueRange()
    {
        boolean flagCtrlRange = true;
        String strctrlRANGE = jText_valueRange.getText().trim();
        if(strctrlRANGE.length()>0)
        {
            if((strctrlRANGE.startsWith("-")) || (strctrlRANGE.endsWith("-")) )
                flagCtrlRange = false;
            else
            {
                java.util.StringTokenizer ctrlRANGE_STK = new java.util.StringTokenizer(strctrlRANGE,"-");
                if(ctrlRANGE_STK.countTokens() > 2)
                    flagCtrlRange = false;
                else
                {
                    int min = new Integer(ctrlRANGE_STK.nextToken()).intValue();
                    if(ctrlRANGE_STK.hasMoreTokens())
                    {
                        int max = new Integer(ctrlRANGE_STK.nextToken()).intValue();
                        //System.out.println("min-max "+min+"-"+max);
                        if((min > max)||(min == max))
                            flagCtrlRange = false;
                        else
                        {
                            strctrlRANGE = min+"-"+max;
                            jText_valueRange.setText(strctrlRANGE);
                        }
                    }
                    else
                        flagCtrlRange = false;

                }
            }

            if(flagCtrlRange == false)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Insert value range using the format 'MIN-MAX' (ex.: 0-100).","WARNING",4);
                jText_valueRange.requestFocus();
                jText_valueRange.selectAll();
            }
        }
        return flagCtrlRange;
    }
    private void jText_tagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_tagKeyReleased
        if(jText_tag.getText().length() > 0)
            jB_commit.setEnabled(true);
        else
            jB_commit.setEnabled(false);
    }//GEN-LAST:event_jText_tagKeyReleased

    private void jB_edit_valuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_edit_valuesActionPerformed
       
        if( Selected_row_TAB_CONFIG == null )
            return;
        
        if(JF_ListValue == null)
        {    
            String title =" ( New Traffic Element )";
            if(Selected_row_TAB_CONFIG.TAG.compareTo("")!=0)
                title = " of Traffic Element with Tag: "+Selected_row_TAB_CONFIG.TAG;
            
            JF_ListValue = new ADAMS_JF_ListValue("List Values"+title,true);
            if(Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO >= 0)
                Selected_row_TAB_CONFIG.read_LISTA_VALORI();
            //else
                //System.out.println("New Traffic ELEMENT");
            
            JF_ListValue.setDescFilter(60);
        }
        
        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                JF_ListValue.setEnableButtons(true);
            else
                JF_ListValue.setEnableButtons(false);
        }
        else
            JF_ListValue.setEnableButtons(true);
        
        JF_ListValue.setElements(Selected_row_TAB_CONFIG.V_LISTA_VALORI);
        JF_ListValue.setVisible(true);        
        Selected_row_TAB_CONFIG.V_LISTA_VALORI = JF_ListValue.getElements();
  
        //V_LISTA_VALORI
        if(Selected_row_TAB_CONFIG.V_LISTA_VALORI.size() != 0)
        {
            jText_values.setText(strDefined);
            jText_values.setForeground(Color.green.darker());
        }
        else
        {
            jText_values.setText(StrNotDefined);
            jText_values.setForeground(Color.red);
        }

    }//GEN-LAST:event_jB_edit_valuesActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
       // System.out.println("COMMIT");
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            AddRowJTabel_general();
            setSelected_jTableRow();
            this.setCursor(Cur_default);
            return;
        }
        
        if(Selected_row_TAB_CONFIG == null)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
            this.setCursor(Cur_default);
            return;
        }
        
        if(jrb_delete.isSelected())
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete Traffic Element Id # "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                int RemoveAggreRes_PosElem = Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO;
                
                if(Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR == -1)
                {
                    int answer_del = Selected_row_TAB_CONFIG.delete_ROW();
                    if(answer_del >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Traffic Element Id # "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO+" has been deleted.","INFO",3);
                        AddRowJTabel_general();
                        setSelected_jTableRow();
                        setEnabledClean_AllGui(false,true);
                        
                        //System.out.println("Devo Cancellare tutti gli Aggregate Restricion con POSIZIONE_ELEMENTO "+RemoveAggreRes_PosElem);
                        boolean flagDeleteAgg = V_TAB_CONFIG.deleteAgregateRestriction(RemoveAggreRes_PosElem);
                        
                        flagDeleteAgg = V_TAB_CONFIG.deleteAllRelation_withID(RemoveAggreRes_PosElem);
                        
                        if(flagDeleteAgg)
                           ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"The requested Relation and any chain linked to it has been deleted.","INFO",3);
                                            
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                }
                else
                {
                    ADAMS_JD_Message op1 = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: Selected Traffic Element is marked as Input Data. It cannot be deleted but just resetted to initial values. Beware of the fact that doing this will REMOVE ALL configuration items that are currently using it. Do you really want to proceed ?","Warning",6,290,220);
                    int Yes_No1 = op1.getAnswer();
                    if(Yes_No1 == 1)
                    {
                        String str_empty = "";
                        
                        // Sconfiguro TRAFFIC ELEMENT (non puÃ¨ essere eliminata l'intera riga perchÃ¨ di base Ã¨ un InputData)
                        Selected_row_TAB_CONFIG.TAG                       = Selected_row_TAB_CONFIG.LABEL_DR_NORMALIZZ;
                        Selected_row_TAB_CONFIG.DESCRIPTION               = str_empty;
                        Selected_row_TAB_CONFIG.TIPO_OGGETTO_GUI          = 0; 
                        Selected_row_TAB_CONFIG.RANGE_VAL                 = str_empty;
                        Selected_row_TAB_CONFIG.VALORE_DEFAULT            = 0;
                        Selected_row_TAB_CONFIG.ICONA                     = str_empty;
                        Selected_row_TAB_CONFIG.RAPPRESENTAZ_ESADECIMALE  = 'N';
                        Selected_row_TAB_CONFIG.PRIORITA                  = 0;
                        Selected_row_TAB_CONFIG.OPERATORI_RESTRIZIONE     = 0;
                        Selected_row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE      = 0;
                        Selected_row_TAB_CONFIG.PLUGIN                    = str_empty;
                        Selected_row_TAB_CONFIG.PLUGIN_PATH               = str_empty;
                        Selected_row_TAB_CONFIG.DEFAULT_RESTRICTION       = 'N';
                        Selected_row_TAB_CONFIG.FFENABLED                 = 'N'; 
                        Selected_row_TAB_CONFIG.ALWAYS_SHOW_REPORT        = 'N';
                        Selected_row_TAB_CONFIG.LINK_TE                   = -1;
                        
                        Selected_row_TAB_CONFIG.V_LISTA_VALORI.removeAllElements();
                        Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.removeAllElements();
                        Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.removeAllElements();
                        Selected_row_TAB_CONFIG.V_LISTA_SCRIPTS.removeAllElements();
                        Selected_row_TAB_CONFIG.V_LISTA_RELAZIONI.removeAllElements();
                        Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.removeAllElements();
                        
                        int answer_RemoveTEUpdate = Selected_row_TAB_CONFIG.update_TRAFFIC_ELEMENT();
                        
                        if(answer_RemoveTEUpdate >= 0)
                        {
                            AddRowJTabel_general(); 
                            try
                            {   //seleziona riga JTable
                                for(int i=0; i<jTable_TE.getRowCount(); i++)
                                {
                                    int ID_TABLE = new Integer(""+jTable_TE.getValueAt(i,1)).intValue();
                                    if( ID_TABLE == Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO )
                                    {
                                        jTable_TE.setRowSelectionInterval(i,i);
                                        jScrollPane1.validate();
                                        break;
                                    }
                                }
                            }catch(Exception e){}
                            
                             //System.out.println("Devo Cancellare tutti gli Aggregate Restricion con POSIZIONE_ELEMENTO "+RemoveAggreRes_PosElem);
                            boolean flagDeleteAgg = V_TAB_CONFIG.deleteAgregateRestriction(RemoveAggreRes_PosElem);
                        
                            //System.out.println();
                            //System.out.println("Devo Cancellare tutte le Relazioni che contengono "+RemoveAggreRes_PosElem+" "+Selected_row_TAB_CONFIG.TAG);
                            flagDeleteAgg = V_TAB_CONFIG.deleteAllRelation_withID(RemoveAggreRes_PosElem);

                            if(flagDeleteAgg)
                               ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"The requested Relation and any chain linked to it has been deleted.","INFO",3);
                                            
                            setSelected_jTableRow();
                        }
                        else
                        {
                            AddRowJTabel_general();   
                            setSelected_jTableRow();
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                        }
                    }
                }
            }// end Yes_No == 1 Delete            
        }// end jrb_delete.isSelected()
        else
        {   
            Selected_row_TAB_CONFIG.TAG                   = jText_tag.getText().trim();
            Selected_row_TAB_CONFIG.DESCRIPTION           = jText_desc.getText().trim(); 
            
            if(ctrlValueRange())
                Selected_row_TAB_CONFIG.RANGE_VAL = jText_valueRange.getText().trim();
            else
            {
                this.setCursor(Cur_default);
                return;
            }
            
            try{
                Selected_row_TAB_CONFIG.VALORE_DEFAULT        = new Integer(jText_defaultVal.getText().trim()).intValue();  
            }catch(java.lang.NumberFormatException e){
                Selected_row_TAB_CONFIG.VALORE_DEFAULT = 0;
            }
            
            Selected_row_TAB_CONFIG.ICONA = Selected_ICONA;
            
            try{
                Selected_row_TAB_CONFIG.PRIORITA              = new Integer(jText_priority.getText().trim()).intValue();        
            }catch(java.lang.NumberFormatException e){
                Selected_row_TAB_CONFIG.PRIORITA = 0;
            }  
            
            try{
                Selected_row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE  = new Integer(jText_SkeyLength.getText().trim()).intValue();
            }catch(java.lang.NumberFormatException e){
                Selected_row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE = 0;
            }
            
            if(Selected_row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE == 0)
            {
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Subkey Length is empty. Proceed anyway ?","Warning",6);
                int Yes_No1 = op.getAnswer();
                if(Yes_No1 == 0)
                {
                    this.setCursor(Cur_default);
                    return;
                }
            }
                
            char ck = 'N';
            if(jCheckBox_hexInput.isSelected())
                ck = 'Y';
            Selected_row_TAB_CONFIG.RAPPRESENTAZ_ESADECIMALE = ck;

            //******* Assegno TIPO_OGGETTO_GUI  *****//
            String str_item_GUI = (String)jCBox_GUIObj.getSelectedItem();
            int TypeGUIObj = VectorTypeGuiObj.getValue(str_item_GUI);
            int ID_HELP_GUI = VectorTypeGuiObj.getID(str_item_GUI,TypeGUIObj);
            Selected_row_TAB_CONFIG.TIPO_OGGETTO_GUI = ID_HELP_GUI;
            
            if( (TypeGUIObj == 2) || (TypeGUIObj == 3)) //ValueList and OptionList
            {
                if(Selected_row_TAB_CONFIG.V_LISTA_VALORI.size() == 0)
                {
                    ADAMS_JD_Message opGUI = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: GUI Object of type '"+str_item_GUI+"' has been specified but corresponding Value List is empty.\nProceed anyway ?","Warning",6,350,180);
                    int Yes_No_GUI = opGUI.getAnswer();
                    if(Yes_No_GUI == 0)
                    {
                        this.setCursor(Cur_default);
                        return;
                    }
                }
            }          
            //System.out.println("ID Help univoco "+ID_HELP_GUI);
            //*************
            
            //******* Assegno OPERATORI_RESTRIZIONE *****//
            String str_item_compsel = (String)jCBox_compSel.getSelectedItem();
            int TypeComp = VectorCompareSel.getValue(str_item_compsel);
            int ID_HELP_COMP = VectorCompareSel.getID(str_item_compsel,TypeComp);
            Selected_row_TAB_CONFIG.OPERATORI_RESTRIZIONE = ID_HELP_COMP;
            
            if( (Selected_row_TAB_CONFIG.V_LISTA_VALORI.size() > 0)  &&(TypeComp == 0) ) //Disable
            {
                ADAMS_JD_Message opCmp = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: Value List has been defined. You should select a valid Compare operator.\nProceed anyway ?","Warning",6,300,200);
                int Yes_No_Cmp = opCmp.getAnswer();
                if(Yes_No_Cmp == 0)
                {
                    this.setCursor(Cur_default);
                    return; 
                }
            }
            
            
            //System.out.println("ID COMPsel univoco "+ID_HELP_COMP);
            //*************            
                        
            Selected_row_TAB_CONFIG.PLUGIN = SELECTED_PLUGIN;
            //System.out.println("Selected_row_TAB_CONFIG.PLUGIN "+Selected_row_TAB_CONFIG.PLUGIN);
            Selected_row_TAB_CONFIG.PLUGIN_PATH   = jText_PluginPath.getText().trim();
            
            //// ****** SELECTED_PLUGIN_GUI
            Selected_row_TAB_CONFIG.PLUGIN_GUI =  SELECTED_PLUGIN_GUI;
            //// ****** 
            
            //****************** DEFAULT DEFAULT_RESTRICTION ***********************
            
            if ( (Selected_row_TAB_CONFIG.DEFAULT_RESTRICTION != 'Y') && (jCheck_Rest.isSelected()) && (jrb_modify.isSelected()) )
            {    
                boolean FLAG_OPTION     = false;
                String str_REL_name_Option  = "";
                Vector V_APPO_TE_UPDATE = new Vector();                
                
                ///////////////////// Add relation GHOST -- ATTENZIONE DOPO IL CONTROLLO VANNO RIMOSSI ///////////////////
                
                ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG_GHOST = new ADAMS_Vector_TAB_CONFIG(this);
                boolean ok_read = V_TAB_CONFIG_GHOST.read_AllItem(ADAMS_GlobalParam.read_GHOST);
                
                for(int g=0; g<V_TAB_CONFIG_GHOST.size(); g++)
                {   
                    //System.out.println("ADD GHOST "+((ADAMS_TAB_CONFIG)V_TAB_CONFIG_GHOST.elementAt(g)).POSIZIONE_ELEMENTO );
                    V_TAB_CONFIG.addElement((ADAMS_TAB_CONFIG)V_TAB_CONFIG_GHOST.elementAt(g));
                    //System.out.println("V_TAB_CONFIG.size() "+V_TAB_CONFIG.size());
                }                                                                                            //
                ///////////////////// Add relation GHOST -- ATTENZIONE DOPO IL CONTROLLO VANNO RIMOSSI ///////////////////
                
                for(int i=0; i<V_TAB_CONFIG.size(); i++)
                {
                    ADAMS_TAB_CONFIG appoTE = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);            

                    appoTE.read_LISTA_RELAZIONI();
                    if(appoTE.V_LISTA_RELAZIONI.size() > 0)
                    {
                        for(int rx=0; rx<appoTE.V_LISTA_RELAZIONI.size();rx++ )
                        {
                            ADAMS_TAB_RELAZIONI_ELEMENTO AppoREL = (ADAMS_TAB_RELAZIONI_ELEMENTO)appoTE.V_LISTA_RELAZIONI.elementAt(rx);
                            //System.out.print(" REST di --- "+AppoREL.RELATION_NAME +"--> ");
                           
                            for(int x=0; x<AppoREL.V_RESTRIZIONI.size(); x++)
                            {
                                Integer ID_rest = ((Integer)AppoREL.V_RESTRIZIONI.elementAt(x));
                                if(ID_rest.intValue() == Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO)
                                {
                                    FLAG_OPTION = true;
                                    if(appoTE.POSIZIONE_ELEMENTO != -2) //se non Ã¨ GHOST
                                        str_REL_name_Option += " -"+getDescriptionREL(AppoREL.RELATION_NAME);
                                    else
                                        str_REL_name_Option += " -"+AppoREL.RELATION_NAME+"(Ghost)";
                                    
                                    V_APPO_TE_UPDATE.addElement(appoTE);
                                    break;
                                }
                            }
                        }
                    }
                }
                
                ///////////////////// REMOVE relation GHOST --  ///////////////////
                for(int g=0; g<V_TAB_CONFIG_GHOST.size(); g++)
                {
                    //System.out.println("rimuovo GHOST "+((ADAMS_TAB_CONFIG)V_TAB_CONFIG_GHOST.elementAt(g)).POSIZIONE_ELEMENTO );
                    V_TAB_CONFIG.removeElement((ADAMS_TAB_CONFIG)V_TAB_CONFIG_GHOST.elementAt(g));
                    //System.out.println("V_TAB_CONFIG.size() "+V_TAB_CONFIG.size());
                }
                ///////////////////// REMOVE relation GHOST --  ///////////////////
                
                if(FLAG_OPTION)
                {
                    str_REL_name_Option += ".";
                    ADAMS_JD_Message opD = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Warning: About to remove a DEFAULT RESTRICTION.\n"+Selected_row_TAB_CONFIG.TAG+" is used as a restriction item for the following Relations:\n"+str_REL_name_Option+"\nDo you really want to proceed ?","Warning",6,600,300);
                    int Yes_NoD = opD.getAnswer();
                    if(Yes_NoD == 1)
                    {                       
                        for(int k=0; k<V_APPO_TE_UPDATE.size(); k++)
                        {
                            ADAMS_TAB_CONFIG appoTE_1 = (ADAMS_TAB_CONFIG)V_APPO_TE_UPDATE.elementAt(k);
                            
                            for(int rx=0; rx<appoTE_1.V_LISTA_RELAZIONI.size();rx++ )
                            {
                                ADAMS_TAB_RELAZIONI_ELEMENTO AppoREL_1 = (ADAMS_TAB_RELAZIONI_ELEMENTO)appoTE_1.V_LISTA_RELAZIONI.elementAt(rx);
                                //System.out.println("Ripulisco Restrizioni di relazione... "+AppoREL_1.RELATION_NAME);
                                
                                for(int j=0; j<AppoREL_1.V_RESTRIZIONI.size(); j++ )
                                {
                                    int ID_el = ((Integer)AppoREL_1.V_RESTRIZIONI.elementAt(j)).intValue();
                                    if(ID_el == Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO)
                                    {
                                        //System.out.println(AppoREL_1.RELATION_NAME+" elimino restrizione con ID= "+ID_el);
                                        AppoREL_1.V_RESTRIZIONI.removeElementAt(j);
                                        break;
                                    }                                    
                                }
                            }                           
                            appoTE_1.update_LISTA_RELAZIONI(false);
                        }
                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Commit will be ABORTED !","Warning",4);
                        this.setCursor(Cur_default);
                        return;
                    }
                }
            
            }
            
            char ck1 = 'N';
            if(jCheck_Rest.isSelected())
                ck1 = 'Y';
            Selected_row_TAB_CONFIG.DEFAULT_RESTRICTION = ck1;
            //****************** END DEFAULT DEFAULT_RESTRICTION *******************
            
            char ck_F = 'N';
            if(jCheck_ffEnabled.isSelected())
                ck_F = 'Y';
            Selected_row_TAB_CONFIG.FFENABLED = ck_F;
            
            
            char ck2 = 'N';
            if(jCheckBox_showReport.isSelected())
                ck2 = 'Y';
            Selected_row_TAB_CONFIG.ALWAYS_SHOW_REPORT = ck2;
                      
            Selected_row_TAB_CONFIG.LINK_TE = getIDTE((String)jCBox_LinkTE.getSelectedItem());
                                    
            if(jrb_add.isSelected())
            {
                //*****************************************************************************************************//
                //ATTENZIONE NEW TRAFFIC ELEMENT -- E' identificato SOLO come un TRAFFIC ELEMENT POSIZIONE_CAMPO_DR -1 
                //ADAMS_TAB_CONFIG.insert_TRAFFIC_ELEMENT()
                //*****************************************************************************************************//                
                int Answer_InsertTE = Selected_row_TAB_CONFIG.insert_TRAFFIC_ELEMENT(false);
                if(Answer_InsertTE == 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Traffic Element Tag # "+Selected_row_TAB_CONFIG.TAG+" has been added.","INFO",3);
                    AddRowJTabel_general();
                    jTable_TE.setSelectionBackground(java.awt.Color.yellow);
                    
                    //seleziona riga JTable
                    int ID_MAX = 0;
                    int indexSelected = 0;
                    for(int i=0; i<jTable_TE.getRowCount(); i++)
                    {
                        int ID = new Integer(""+jTable_TE.getValueAt(i,1)).intValue();
                        if(ID > ID_MAX)
                        {
                            ID_MAX = ID;
                            indexSelected = i;
                        }
                    }
                    
                    try
                    {   
                        jTable_TE.setRowSelectionInterval(0,indexSelected);
                        jScrollPane1.validate();
                    }
                    catch(Exception e){}
                    
                    setSelected_jTableRow();                    
                }
                else
                {
                    AddRowJTabel_general();   
                    setSelected_jTableRow();
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Traffic Element Tag # "+Selected_row_TAB_CONFIG.TAG+".","ERROR",1);
                    this.setCursor(Cur_default);
                }
            }
            else //jrb_modify.isSelected()
            {                
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change Traffic Element id # "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO+". Confirm ?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    Selected_row_TAB_CONFIG.read_LISTA_RELAZIONI();
                    int answer_up = Selected_row_TAB_CONFIG.update_TRAFFIC_ELEMENT();
                    
                    if(answer_up >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Traffic Element id # "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO+" has been changed.","INFO",3);
                        AddRowJTabel_general();   
                        jCBox_StartModify.setSelected(false);
                        jCBox_StartModify.setBorder(borderRed);
                        jTable_TE.setEnabled(true);
                        
                        try
                        {   //seleziona riga JTable
                            for(int i=0; i<jTable_TE.getRowCount(); i++)
                            {
                                int ID_TABLE = new Integer(""+jTable_TE.getValueAt(i,1)).intValue();
                                if( ID_TABLE == Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO )
                                {
                                    jTable_TE.setRowSelectionInterval(i,i);
                                    jScrollPane1.validate();
                                    break;
                                }
                            }
                        }catch(Exception e){}
                        
                        setSelected_jTableRow();
                    }
                    else
                    {
                        AddRowJTabel_general();   
                        setSelected_jTableRow();
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                    }
                }
            }
        }
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
       if(jrb_modify.isSelected())
       {
           jCBox_StartModify.setVisible(false);
           jCBox_StartModify.setSelected(false);
           jCBox_StartModify.setBorder(borderRed);
           jTable_TE.setEnabled(true);
       }
        
       jTable_TE.clearSelection();
       setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jTable_TEMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jTable_TE.isEnabled())
        {
            if(jrb_add.isSelected() == false)
                setSelected_jTableRow();
        }
    }
    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        jCBox_StartModify.setVisible(false);
        jCBox_StartModify.setSelected(false);
        jCBox_StartModify.setBorder(borderRed);
        
        jTable_TE.setEnabled(true);
        jTable_TE.setSelectionBackground(java.awt.Color.red);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
       
        jCBox_StartModify.setVisible(false);
        jCBox_StartModify.setSelected(false);
        jCBox_StartModify.setBorder(borderRed);
        
        jTable_TE.setEnabled(true);
        jTable_TE.setSelectionBackground(java.awt.Color.green.darker());
        setSelected_jTableRow();

    }//GEN-LAST:event_jrb_modifyActionPerformed

    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        jCBox_StartModify.setVisible(false);
        jCBox_StartModify.setSelected(false);
        jCBox_StartModify.setBorder(borderRed);
        
        jTable_TE.setSelectionBackground(java.awt.Color.white);
        jTable_TE.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addActionPerformed

    private void jB_aggrResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_aggrResActionPerformed
        
        this.setCursor(Cur_wait);

        String title =" ( New Traffic Element )";
        if(Selected_row_TAB_CONFIG.TAG.compareTo("")!=0)
            title = " of Traffic Element with Tag: "+Selected_row_TAB_CONFIG.TAG;
        
        JListChooser listChooser = new JListChooser(ADAMS_GlobalParam.JFRAME_TAB, "Aggregate Restrictions"+title, true);

        if(jrb_modify.isSelected())
        {
            if(jCBox_StartModify.isSelected())
                listChooser.setEnableButtons(true);
            else
                listChooser.setEnableButtons(false);
        }
        
        // Available Items ////////   
        int SIZE_ALL_TE = V_TAB_CONFIG.size();
        
        Object[][] l1;
        if(jrb_add.isSelected())
            l1 = new Object[SIZE_ALL_TE][2];
        else
            l1 = new Object[SIZE_ALL_TE-1][2];
        
        int count = 0;
        for(int i=0; i<SIZE_ALL_TE; i++)
        {
            int PosElem = ((ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i)).POSIZIONE_ELEMENTO;
            if(PosElem != Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO)
            {
                l1[count][0] = new Integer(PosElem);   //ID
                l1[count][1] = new String(((ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i) ).TAG); //  - Tag
                count++;
            }
        }
        
        // Selected Items  ////////
        int SIZE_SELECTED = Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.size();
        Object[][] l2 = new Object[SIZE_SELECTED][2];

        for(int i=0; i<SIZE_SELECTED; i++)
        {
            int pos_ele = ((Integer)(Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.elementAt(i))).intValue();
            l2[i][0] = new Integer(pos_ele);                 //  - Id
            l2[i][1] = V_TAB_CONFIG.getTagTE(pos_ele);                   //  - Tag
        }

        listChooser.setListChooserData(l1, l2);
        //listChooser.show();
        listChooser.setVisible(true);

        // read selected Items
        boolean empty = true;
        Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.removeAllElements();
        l2 = listChooser.getListChooserData();
        for(int i=0; i<l2.length; i++)
        {
            Integer posEle = (Integer)(l2[i][0]);
            Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.addElement(posEle);
            empty = false;
            //System.out.println("USER DATA:"+l2[i][0]+" - "+l2[i][1]);            
        }

        listChooser.dispose();
        
        if(empty)
        {
            jTextAggrRes.setText(StrNotDefined);
            jTextAggrRes.setForeground(Color.red);
        }
        else
        {
            jTextAggrRes.setText(strDefined);
            jTextAggrRes.setForeground(Color.green.darker());
        }
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_aggrResActionPerformed

    private String getTagTE(int posiz_elem)
    {
        for(int i=0; i<V_TAB_CONFIG.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);
            if( TE.POSIZIONE_ELEMENTO == posiz_elem)
                return (new String(TE.TAG));
        }
        return "";
    }
    
    private int getIDTE(String tag_elem)
    {
        for(int i=0; i<V_TAB_CONFIG.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);
            if( TE.TAG.compareTo(tag_elem) == 0)
                return TE.POSIZIONE_ELEMENTO;
        }
        return 0;
    }
    
    private void setSelected_jTableRow()
    {
        this.setCursor(Cur_wait);
        //System.out.println("OK --> JF_ListValue = null");
        JF_ListValue = null;
        Selected_ICONA  = "";
        SELECTED_PLUGIN = "";
        SELECTED_PLUGIN_GUI = "";
        
        int selectedRow = jTable_TE.getSelectedRow();
        
        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                boolean ctrl_row = false;
                int ID_selected = new Integer(""+jTable_TE.getValueAt(selectedRow,1)).intValue();
                //System.out.println("ID Selezionato => "+ID_selected);
                
                // cerco l'oggetto ADAMS_TAB_CONFIG tramite POSIZIONE_ELEMENTO (Non rispetta l'ordine del vettore nel caso del Sorter della tabella)
                for(int i=0; i<V_TAB_CONFIG.size(); i++) 
                {
                    Selected_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);      
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
                    return;
                }
                
                //jText_id.setText(""+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO);                
                jText_tag.setText(Selected_row_TAB_CONFIG.TAG);
                jText_desc.setText(Selected_row_TAB_CONFIG.DESCRIPTION);                
                jText_valueRange.setText(Selected_row_TAB_CONFIG.RANGE_VAL);                 
                jText_defaultVal.setText(""+Selected_row_TAB_CONFIG.VALORE_DEFAULT); 
                
                if(Selected_row_TAB_CONFIG.ICONA.length() >0)        
                {
                    Selected_ICONA = Selected_row_TAB_CONFIG.ICONA;
                    jT_pixmap.setText(strDefined);
                    jT_pixmap.setForeground(Color.green.darker());
                }
                else
                {
                    jT_pixmap.setText(StrNotDefined);
                    jT_pixmap.setForeground(Color.red);       
                }                
                
                jText_priority.setText(""+Selected_row_TAB_CONFIG.PRIORITA);
                jText_SkeyLength.setText(""+Selected_row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE);             
                
                jCheckBox_hexInput.setSelected(false);
                if(Selected_row_TAB_CONFIG.RAPPRESENTAZ_ESADECIMALE == 'Y')
                    jCheckBox_hexInput.setSelected(true);
                else
                    jCheckBox_hexInput.setSelected(false);                    
                                 
                jCBox_GUIObj.setSelectedItem(VectorTypeGuiObj.getDesc(Selected_row_TAB_CONFIG.TIPO_OGGETTO_GUI));
                jCBox_compSel.setSelectedItem(VectorCompareSel.getDesc(Selected_row_TAB_CONFIG.OPERATORI_RESTRIZIONE));

                if(Selected_row_TAB_CONFIG.PLUGIN.length() >0)        
                {
                    SELECTED_PLUGIN = Selected_row_TAB_CONFIG.PLUGIN;
                    jText_pluginName.setText(strDefined);
                    jText_pluginName.setForeground(Color.green.darker());
                }
                else
                {
                    jText_pluginName.setText(StrNotDefined);
                    jText_pluginName.setForeground(Color.red);       
                }             
                
                jText_PluginPath.setText(Selected_row_TAB_CONFIG.PLUGIN_PATH);
                                                
                
                // *****  PLUGIN_GUI
                 if(Selected_row_TAB_CONFIG.PLUGIN_GUI.length() >0)        
                {
                    SELECTED_PLUGIN_GUI = Selected_row_TAB_CONFIG.PLUGIN_GUI;
                    jText_pluginName_GUI.setText(strDefined);
                    jText_pluginName_GUI.setForeground(Color.green.darker());
                }
                else
                {
                    jText_pluginName_GUI.setText(StrNotDefined);
                    jText_pluginName_GUI.setForeground(Color.red);       
                }             
                // ***** end PLUGIN_GUI
                
                
                
                if(Selected_row_TAB_CONFIG.DEFAULT_RESTRICTION == 'Y')
                    jCheck_Rest.setSelected(true);
                else
                    jCheck_Rest.setSelected(false);
                
                if(Selected_row_TAB_CONFIG.FFENABLED == 'Y')
                    jCheck_ffEnabled.setSelected(true);
                else
                    jCheck_ffEnabled.setSelected(false);
                
                    
                if(Selected_row_TAB_CONFIG.ALWAYS_SHOW_REPORT == 'Y')
                    jCheckBox_showReport.setSelected(true);
                else
                    jCheckBox_showReport.setSelected(false);
                

                String str_Link_te = getTagTE(Selected_row_TAB_CONFIG.LINK_TE);
                if(str_Link_te.length() == 0)
                    jCBox_LinkTE.setSelectedItem(str_none);
                else
                    jCBox_LinkTE.setSelectedItem(str_Link_te);

                
                //V_LISTA_VALORI
                if(Selected_row_TAB_CONFIG.V_LISTA_VALORI.size() != 0)
                {
                    jText_values.setText(strDefined);
                    jText_values.setForeground(Color.green.darker());
                }
                else
                {
                    jText_values.setText(StrNotDefined);
                    jText_values.setForeground(Color.red);
                }
                
                //V_AGGREGAZ_ELEM_RESTRIZ
                Selected_row_TAB_CONFIG.read_AGGREGAZ_ELEM_RESTRIZ();
                if(Selected_row_TAB_CONFIG.V_AGGREGAZ_ELEM_RESTRIZ.size() != 0) 
                {
                    jTextAggrRes.setText(strDefined);
                    jTextAggrRes.setForeground(Color.green.darker());
                }
                else
                {
                    jTextAggrRes.setText(StrNotDefined);
                    jTextAggrRes.setForeground(Color.red);
                }
                                
                //V_LISTA_EXCEPTION
                Selected_row_TAB_CONFIG.read_LISTA_EXCEPTION();
                if(Selected_row_TAB_CONFIG.V_LISTA_EXCEPTION.size() != 0)
                {
                    jText_except.setText(strDefined);
                    jText_except.setForeground(Color.green.darker());
                }
                else
                {
                    jText_except.setText(StrNotDefined);
                    jText_except.setForeground(Color.red);
                }
                
                //V_LISTA_VALORI_SOSTITUZ
                Selected_row_TAB_CONFIG.read_LISTA_VALORI_SOSTITUZ();
                if(Selected_row_TAB_CONFIG.V_LISTA_VALORI_SOSTITUZ.size() != 0)
                {
                    jText_shifter.setText(strDefined);
                    jText_shifter.setForeground(Color.green.darker());
                }
                else
                {
                    jText_shifter.setText(StrNotDefined);
                    jText_shifter.setForeground(Color.red);
                }
                
                //V_LISTA_SCRIPTS
                Selected_row_TAB_CONFIG.read_LISTA_SCRIPTS();
                if(Selected_row_TAB_CONFIG.V_LISTA_SCRIPTS.size() != 0)
                {
                    jText_evalScr.setText(strDefined);
                    jText_evalScr.setForeground(Color.green.darker());
                }
                else
                {
                    jText_evalScr.setText(StrNotDefined);
                    jText_evalScr.setForeground(Color.red);
                }

            }
            else
            {
                Selected_row_TAB_CONFIG = null;
                setEnabledClean_AllGui(false,true);                
                if(jrb_modify.isSelected())
                {
                    jCBox_StartModify.setVisible(false);
                    jCBox_StartModify.setSelected(false);
                    jCBox_StartModify.setBorder(borderRed);

                    jTable_TE.setEnabled(true);
                    jTable_TE.setSelectionBackground(java.awt.Color.green.darker());
                }
                this.setCursor(Cur_default);
                return;
            }          
                        
            if(jrb_modify.isSelected())
            {
                jCBox_StartModify.setVisible(true);                
                setEnabledClean_AllGui(false,false);
                jText_values.setEnabled(true); 
                jTextAggrRes.setEnabled(true);
                jText_except.setEnabled(true);
                jText_shifter.setEnabled(true);
                jText_pluginName.setEnabled(true);
                jText_pluginName_GUI.setEnabled(true);
                jT_pixmap.setEnabled(true);
                jB_edit_values.setEnabled(true);
                jB_aggrRes.setEnabled(true);
                jB_except.setEnabled(true);
                jB_shifter.setEnabled(true);
                jB_pluginName.setEnabled(true);
                jB_editPixMap.setEnabled(true);

                if(Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR > 0)
                {
                    jText_tag.setEnabled(false);
                    jCBox_LinkTE.setSelectedIndex(0);
                    jCBox_LinkTE.setEnabled(false);
                }
            }
            else //jrb.isSelected()
                setEnabledClean_AllGui(false,false);

        }
        else // (jrb_add.isSelected())
        {
            jTable_TE.setEnabled(false);
            setEnabledClean_AllGui(true,true);
            //jText_id.setText("(Auto)");
            Selected_ICONA  = "";
            SELECTED_PLUGIN = "";
            SELECTED_PLUGIN_GUI = "";
            Selected_row_TAB_CONFIG = new ADAMS_TAB_CONFIG();
            Selected_row_TAB_CONFIG.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
            Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO  = -1; // NEW TRAFFIC ELEMENT
            Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR = -1; // ONLY TRAFFIC ELEMENT - NO INPUT DATA
        }
        this.setCursor(Cur_default);
    }
    
    //Permette di resettare e ripulire tutti i widget di input dell'interfaccia.
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {
        jText_tag.setEnabled(enable);
        jCBox_LinkTE.setEnabled(enable);
        jText_desc.setEnabled(enable);        
        jText_valueRange.setEnabled(enable);
        jText_defaultVal.setEnabled(enable); 

        jT_pixmap.setEnabled(enable);  
        jText_priority.setEnabled(enable);        
        jText_SkeyLength.setEnabled(enable);  
        jText_values.setEnabled(enable); 
        jTextAggrRes.setEnabled(enable);
        jText_except.setEnabled(enable);
        jText_shifter.setEnabled(enable);
        jText_pluginName.setEnabled(enable);
        jText_pluginName_GUI.setEnabled(enable);
        
        jText_PluginPath.setEnabled(enable);
        jCheckBox_hexInput.setEnabled(enable);
        jCBox_GUIObj.setEnabled(enable);
        jCBox_compSel.setEnabled(enable);              
        jB_edit_values.setEnabled(enable);
        jB_aggrRes.setEnabled(enable);
        jB_except.setEnabled(enable);
        jB_shifter.setEnabled(enable);
        jB_pluginName.setEnabled(enable);
        jB_editPixMap.setEnabled(enable);
        jCheckBox_showReport.setEnabled(enable);
        jCheck_Rest.setEnabled(enable);
        jCheck_ffEnabled.setEnabled(enable);
        
        if(jrb_add.isSelected() == false)
        {
            jText_evalScr.setEnabled(enable);
            jB_evalScr.setEnabled(enable);
        }
        
        if(clean)
        {
            //jText_id.setText("");            
            jText_tag.setText("");
            jCBox_LinkTE.setSelectedIndex(0);
            jText_desc.setText("");        
            jText_valueRange.setText("");   
            jText_defaultVal.setText("0"); 
            
            jText_priority.setText("0");        
            jText_SkeyLength.setText("0"); 
            jText_PluginPath.setText(""); 

            jT_pixmap.setText(StrNotDefined);
            jT_pixmap.setForeground(Color.red);
            jText_values.setText(StrNotDefined);
            jText_values.setForeground(Color.red);
            jTextAggrRes.setText(StrNotDefined);
            jTextAggrRes.setForeground(Color.red);
            jText_except.setText(StrNotDefined);
            jText_except.setForeground(Color.red);
            jText_shifter.setText(StrNotDefined);
            jText_shifter.setForeground(Color.red);
            jText_evalScr.setText(StrNotDefined);
            jText_evalScr.setForeground(Color.red);
            jText_pluginName.setText(StrNotDefined);
            jText_pluginName.setForeground(Color.red);
            jText_pluginName_GUI.setText(StrNotDefined);
            jText_pluginName_GUI.setForeground(Color.red);
                
            jCheckBox_hexInput.setSelected(false);
            jCheckBox_showReport.setSelected(false);
            jCheck_Rest.setSelected(false);
            jCheck_ffEnabled.setSelected(false);
            
            try
            {
                jCBox_GUIObj.setSelectedIndex(0);
                jCBox_compSel.setSelectedIndex(0);
            }
            catch(Exception e){}
        }
        if(!jrb_modify.isSelected())
        {
            if(jText_tag.getText().length() == 0)
                jB_commit.setEnabled(false);
            else
                jB_commit.setEnabled(true);
        }
        else
            jB_commit.setEnabled(false);
    }
    
    private void setGlobalFont()
    {
        //Font Globali
        jLabel1.setFont(ADAMS_GlobalParam.font_B12);
        //jL_id.setFont(ADAMS_GlobalParam.font_B10);
        //jText_id.setFont(ADAMS_GlobalParam.font_B10);
        jL_tag.setFont(ADAMS_GlobalParam.font_B10);
        jText_tag.setFont(ADAMS_GlobalParam.font_B10);
        jL_LinkTE.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_LinkTE.setFont(ADAMS_GlobalParam.font_B10);
        jL_desc.setFont(ADAMS_GlobalParam.font_B10);
        jText_desc.setFont(ADAMS_GlobalParam.font_B10);
        jL_GUIObj.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_GUIObj.setFont(ADAMS_GlobalParam.font_B10);
        jLvalueRange.setFont(ADAMS_GlobalParam.font_B10);
        jText_valueRange.setFont(ADAMS_GlobalParam.font_B10);
        jL_values.setFont(ADAMS_GlobalParam.font_B10);
        jText_values.setFont(ADAMS_GlobalParam.font_B10);
        jL_defaultVal.setFont(ADAMS_GlobalParam.font_B10);
        jText_defaultVal.setFont(ADAMS_GlobalParam.font_B10);
        jL_pixURL.setFont(ADAMS_GlobalParam.font_B10);        
        jT_pixmap.setFont(ADAMS_GlobalParam.font_B10);        
        jCheckBox_hexInput.setFont(ADAMS_GlobalParam.font_B10);
        jL_priority.setFont(ADAMS_GlobalParam.font_B10);
        jText_priority.setFont(ADAMS_GlobalParam.font_B10);
        jL_compSel.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_compSel.setFont(ADAMS_GlobalParam.font_B10);
        jL_SkeyLength.setFont(ADAMS_GlobalParam.font_B10);
        jText_SkeyLength.setFont(ADAMS_GlobalParam.font_B10);
        jL_aggrRes.setFont(ADAMS_GlobalParam.font_B10);
        jTextAggrRes.setFont(ADAMS_GlobalParam.font_B10);
        jL_except.setFont(ADAMS_GlobalParam.font_B10);
        jText_except.setFont(ADAMS_GlobalParam.font_B10);
        jL_shifter.setFont(ADAMS_GlobalParam.font_B10);
        jText_shifter.setFont(ADAMS_GlobalParam.font_B10);
        jL_evalScr.setFont(ADAMS_GlobalParam.font_B10);
        jText_evalScr.setFont(ADAMS_GlobalParam.font_B10);
        jL_Plugin.setFont(ADAMS_GlobalParam.font_B10);
        jL_Plugin_GUI.setFont(ADAMS_GlobalParam.font_B10);
        jText_pluginName.setFont(ADAMS_GlobalParam.font_B10);
        jText_pluginName_GUI.setFont(ADAMS_GlobalParam.font_B10);        
        jL_PluginPath.setFont(ADAMS_GlobalParam.font_B10);
        jText_PluginPath.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_StartModify.setFont(ADAMS_GlobalParam.font_B10);
        jCheck_Rest.setFont(ADAMS_GlobalParam.font_B10);
        jCheckBox_showReport.setFont(ADAMS_GlobalParam.font_B10);
        jCheck_ffEnabled.setFont(ADAMS_GlobalParam.font_B10);
        
    }
    
    private String getDescriptionREL(String RELATION_NAME_Indeces)
    {    
        // Costruisco e ricerco relazione con ID
        java.util.StringTokenizer STKrelationName = new java.util.StringTokenizer(RELATION_NAME_Indeces,"::");
        String dot = "";
        String RelationNameTag = "";
        while(STKrelationName.hasMoreTokens())
        {     
            int id_Relation = new Integer(STKrelationName.nextToken()).intValue();
            RelationNameTag += dot+V_TAB_CONFIG.getTagTE(id_Relation);
            dot = "::";
        }    
        return RelationNameTag;
    }
    
//////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames ={ "I.Data",
                                        "Id",
                                        "Tag",
                                        "Description",
                                        "GUI Object",
                                        "Range",
                                        "Values List",
                                        "Default Value",
                                        "Default Rest.",
                                        "Free Format"};
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
    private javax.swing.JButton jB_aggrRes;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_editPixMap;
    private javax.swing.JButton jB_edit_values;
    private javax.swing.JButton jB_evalScr;
    private javax.swing.JButton jB_except;
    private javax.swing.JButton jB_pluginName;
    private javax.swing.JButton jB_pluginName_GUI;
    private javax.swing.JButton jB_shifter;
    private javax.swing.JComboBox jCBox_GUIObj;
    private javax.swing.JComboBox jCBox_LinkTE;
    private javax.swing.JCheckBox jCBox_StartModify;
    private javax.swing.JComboBox jCBox_compSel;
    private javax.swing.JCheckBox jCheckBox_hexInput;
    private javax.swing.JCheckBox jCheckBox_showReport;
    private javax.swing.JCheckBox jCheck_Rest;
    private javax.swing.JCheckBox jCheck_ffEnabled;
    private javax.swing.JLabel jL_GUIObj;
    private javax.swing.JLabel jL_LinkTE;
    private javax.swing.JLabel jL_Plugin;
    private javax.swing.JLabel jL_PluginPath;
    private javax.swing.JLabel jL_Plugin_GUI;
    private javax.swing.JLabel jL_SkeyLength;
    private javax.swing.JLabel jL_aggrRes;
    private javax.swing.JLabel jL_compSel;
    private javax.swing.JLabel jL_defaultVal;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JLabel jL_evalScr;
    private javax.swing.JLabel jL_except;
    private javax.swing.JLabel jL_pixURL;
    private javax.swing.JLabel jL_priority;
    private javax.swing.JLabel jL_shifter;
    private javax.swing.JLabel jL_tag;
    private javax.swing.JLabel jL_values;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLvalueRange;
    private javax.swing.JPanel jP_LB_values;
    private javax.swing.JPanel jP_Pixmap;
    private javax.swing.JPanel jP_South_east;
    private javax.swing.JPanel jP_South_west;
    private javax.swing.JPanel jP_aggrRes;
    private javax.swing.JPanel jP_center;
    private javax.swing.JPanel jP_evalScr;
    private javax.swing.JPanel jP_except;
    private javax.swing.JPanel jP_plugin;
    private javax.swing.JPanel jP_shifter;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jP_tag;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_GUI;
    private javax.swing.JPanel jPs_c;
    private javax.swing.JPanel jPs_s;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jT_pixmap;
    private javax.swing.JTextField jTextAggrRes;
    private javax.swing.JTextField jText_PluginPath;
    private javax.swing.JTextField jText_SkeyLength;
    private javax.swing.JTextField jText_defaultVal;
    private javax.swing.JTextField jText_desc;
    private javax.swing.JTextField jText_evalScr;
    private javax.swing.JTextField jText_except;
    private javax.swing.JTextField jText_pluginName;
    private javax.swing.JTextField jText_pluginName_GUI;
    private javax.swing.JTextField jText_priority;
    private javax.swing.JTextField jText_shifter;
    private javax.swing.JTextField jText_tag;
    private javax.swing.JTextField jText_valueRange;
    private javax.swing.JTextField jText_values;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JRadioButton jrb_modify;
    // End of variables declaration//GEN-END:variables


    private boolean DEBUG = false;
    private javax.swing.JTable jTable_TE;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private int[] CellDimension = {30,20,140,190,80,40,50,60,80,70};
    private int minCellDimension = 20;
    
    private ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG = null;
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG  = null;
    
    private ADAMS_Vector_TAB_EVENTI_GUI V_TAB_EVENTI_GUI = null;
    
    private ADAMS_TAB_CONFIG Selected_row_TAB_CONFIG;
    private String Selected_ICONA   = "";
    private String SELECTED_PLUGIN  = "";
    private String SELECTED_PLUGIN_GUI  = "";
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined";
    private String str_none         = "<none>";
    
    private ADAMS_JF_ListValue JF_ListValue;
    private VectorHelp VectorTypeGuiObj;
    private VectorHelp VectorCompareSel;
    
    private java.awt.event.ActionListener event_jCBox_LinkTE;
    
    private  javax.swing.border.LineBorder borderRed    = new javax.swing.border.LineBorder(Color.red, 2, true);
    private  javax.swing.border.LineBorder borderGreen  = new javax.swing.border.LineBorder(Color.green.darker(), 2, true);

}