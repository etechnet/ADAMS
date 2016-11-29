/*
 * ADAMS_JP_Analisi.java
 *
 * Created on 16 settembre 2005, 12.08
 */

/**
 *
 * @author Luca 
 */
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.sql.*;

public class ADAMS_JP_Analisi extends javax.swing.JPanel implements Runnable{

    /** Creates new form ADAMS_JP_Analisi */
    public ADAMS_JP_Analisi(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {
        initComponents();
             
        this.local_INFO_CONFIG = INFO_CONFIG;
        jL_title.setText("Analysis Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        
        jTF_descAnalysis.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));
        
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_Analysis = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_Analysis.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_Analysis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Analysis.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Analysis.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_Analysis.setRowHeight(20);
        jTable_Analysis.setRowMargin(2);
        jTable_Analysis.setAutoCreateColumnsFromModel(false);
        
        jScrollPane1.setViewportView(jTable_Analysis);
        
        jTable_Analysis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_AnalysisMouseReleased(evt);
            }
        });
        
        // **************************** //
        
        SetTable(jTable_Analysis);
        setGlobalFont();
        
        //Cursor
        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);

        jB_reportList.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
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
    
    private void AddRowJTabel_general()
    {
        jTable_Analysis.setCursor(Cur_wait);
        V_Analysis = read_Analysis();
        
        jL_title.setText("Analysis - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);

        int SIZE = V_Analysis.size();
        Object[][] dataValues = new Object[SIZE][8];
       
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_TYPE_ANALISI row_TypeAnalsys = (ADAMS_TAB_TYPE_ANALISI)V_Analysis.elementAt(i);
             
            dataValues[i][0] = new Integer(row_TypeAnalsys.IDANALISI);
            dataValues[i][1] = new String(row_TypeAnalsys.NOMEANALISI);
            dataValues[i][2] = new String(get_IDCounterKit_space_TAG(row_TypeAnalsys.IDCOUNTERKIT,str_none));
            
            if(row_TypeAnalsys.FLAGSORT == 'Y')
                dataValues[i][3] = new Boolean(true);                                   
            else
                dataValues[i][3] = new Boolean(false);
            
            if(row_TypeAnalsys.FLAGDATA == 'Y')
                dataValues[i][4] = new Boolean(true);                                   
            else
                dataValues[i][4] = new Boolean(false);
            
            if(row_TypeAnalsys.FLAGCENTRALE == 'Y')
                dataValues[i][5] = new Boolean(true);                                   
            else
                dataValues[i][5] = new Boolean(false);
            
            if(row_TypeAnalsys.FLAGCUMULAZIONE == 'Y')
                dataValues[i][6] = new Boolean(true);                                   
            else
                dataValues[i][6] = new Boolean(false);
            
            String strListaValori = StrNotDefined;
            if(row_TypeAnalsys.LISTA_REPORT.size() != 0) 
                strListaValori = strDefined;
            dataValues[i][7] = new String(strListaValori);                              //  - Values
            
        }        
        
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);
        
        jScrollPane1.setViewportView(jTable_Analysis);
        jTable_Analysis.setCursor(Cur_default);
    }
    
    
    private void setSelected_jTableRow()
    {
        this.setCursor(Cur_wait);
        JF_ReportsList = null;
        
        int selectedRow = jTable_Analysis.getSelectedRow();        
        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow >= 0)
            {
                boolean ctrl_row = false;
                int ID_selected = new Integer(""+jTable_Analysis.getValueAt(selectedRow,0)).intValue();
                //System.out.println("ID Selezionato => "+ID_selected);
                
                // cerco l'oggetto ADAMS_TAB_TYPE_ANALISI tramite ID (Non rispetta l'ordine del vettore nel caso del Sorter della tabella)
                for(int i=0; i<V_Analysis.size(); i++) 
                {
                    Selected_row_Analysis = (ADAMS_TAB_TYPE_ANALISI)V_Analysis.elementAt(i);      
                    if(Selected_row_Analysis.IDANALISI == ID_selected)
                    {
                        ctrl_row = true;
                        break;
                    }
                }
                if(ctrl_row == false)
                {
                    Selected_row_Analysis = null;
                    System.out.println("----- ERRORE Selected_row...");
                    this.setCursor(Cur_default);
                    return;
                }
                
                jTF_IdAnalysis.setText(""+Selected_row_Analysis.IDANALISI);
                jTF_descAnalysis.setText(Selected_row_Analysis.NOMEANALISI);
                                
                jCBox_counters.setSelectedItem(get_IDCounterKit_space_TAG(Selected_row_Analysis.IDCOUNTERKIT,str_none));

                if(Selected_row_Analysis.FLAGSORT == 'Y')
                    jCheckBox_sort.setSelected(true);
                else
                    jCheckBox_sort.setSelected(false); 
                
                if(Selected_row_Analysis.FLAGDATA == 'Y')
                    jCheckBox_date.setSelected(true);
                else
                    jCheckBox_date.setSelected(false); 
                
                if(Selected_row_Analysis.FLAGCENTRALE == 'Y')
                    jCheckBox_nodes.setSelected(true);
                else
                    jCheckBox_nodes.setSelected(false); 
                
                if(Selected_row_Analysis.FLAGCUMULAZIONE == 'Y')
                    jCheckBox_CumulDate.setSelected(true);
                else
                    jCheckBox_CumulDate.setSelected(false); 
                
                //LISTA_REPORT
                if(Selected_row_Analysis.LISTA_REPORT.size() != 0)
                {
                    jTF_reportList.setText(strDefined);
                    jTF_reportList.setForeground(Color.green.darker());
                }
                else
                {
                    jTF_reportList.setText(StrNotDefined);
                    jTF_reportList.setForeground(Color.red);
                }              
            }
            else
            {
                Selected_row_Analysis = null;
                setEnabledClean_AllGui(false,true);
                this.setCursor(Cur_default);
                return;
            }          
                        
            if(jrb_modify.isSelected())
               setEnabledClean_AllGui(true,false);
            else 
               setEnabledClean_AllGui(false,false);

        }
        else // (jrb_add.isSelected())
        {
            jTable_Analysis.setEnabled(false);
            setEnabledClean_AllGui(true,true);
            jTF_IdAnalysis.setText("(Auto)");
            Selected_row_Analysis = new ADAMS_TAB_TYPE_ANALISI();
            Selected_row_Analysis.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
        }
        this.setCursor(Cur_default);
    }
    
    public void setTableConfig(ADAMS_Vector_TAB_CONFIG Parent_V_TAB_CONFIG)
    {   
        read_CounterKit();
        this.parent_V_TAB_CONFIG = Parent_V_TAB_CONFIG;
        
        AddRowJTabel_general();
        setSelected_jTableRow();
    }
    
    
    
    //******************
    private void read_CounterKit()
    {
        V_CountersKit = new Vector();
        
        String sel_ReadCounters = "select IDCOUNTER,TAG from "+name_TabCounters+
                                        " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by TAG";
        
        //System.out.println("sel_ReadCounters --> "+sel_ReadCounters);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ReadCounters,SQLStatement);
            
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    ADAMS_TAB_KIT_CONTATORI row_Contatori = new ADAMS_TAB_KIT_CONTATORI();
                    row_Contatori.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;

                //*** Campi Contatore -- alcuni campi sono NOT Null sul DB ***//
                    row_Contatori.IDCOUNTER  = rs.getInt("IDCOUNTER");          //not null sil DB

                    String tagCounter = rs.getString("TAG");
                    if(tagCounter != null)
                        row_Contatori.TAG = tagCounter;
                    
                    V_CountersKit.addElement(row_Contatori);
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
            System.out.println("(ADAMS_JP_Analisi-1) SQL Operation " + exc.toString());
        }
            
        jCBox_counters.removeAllItems();
        jCBox_counters.addItem(str_none);
        for(int i=0;i<V_CountersKit.size(); i++)
        {
            ADAMS_TAB_KIT_CONTATORI appo_KIT_CONTATORI = (ADAMS_TAB_KIT_CONTATORI)V_CountersKit.elementAt(i);
            jCBox_counters.addItem(appo_KIT_CONTATORI.IDCOUNTER+" "+appo_KIT_CONTATORI.TAG);
        }    
    }    
    
    private int get_IDCounterKIT(String IDCOUNTER_space_TAG)
    {
        for(int i=0; i<V_CountersKit.size(); i++)
        {
            ADAMS_TAB_KIT_CONTATORI appo_KIT_CONTATORI = (ADAMS_TAB_KIT_CONTATORI)V_CountersKit.elementAt(i);
            String StrCtrl = new String(appo_KIT_CONTATORI.IDCOUNTER+" "+appo_KIT_CONTATORI.TAG);
            if(IDCOUNTER_space_TAG.compareTo(StrCtrl) == 0)
                return appo_KIT_CONTATORI.IDCOUNTER;
        }
        return -1; // getSelectedIndex(0) == str_none("<none>)
    }

    private String get_IDCounterKit_space_TAG(int IDCOUNTER,String no_present)
    {
        for(int i=0; i<V_CountersKit.size(); i++)
        {
            ADAMS_TAB_KIT_CONTATORI appo_KIT_CONTATORI = (ADAMS_TAB_KIT_CONTATORI)V_CountersKit.elementAt(i);
            if(IDCOUNTER == appo_KIT_CONTATORI.IDCOUNTER)
                return (new String(appo_KIT_CONTATORI.IDCOUNTER+" "+appo_KIT_CONTATORI.TAG));
        }
        return no_present;
    }
    
    //********************
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jL_title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jL_id = new javax.swing.JLabel();
        jTF_IdAnalysis = new javax.swing.JTextField();
        jL_desc = new javax.swing.JLabel();
        jTF_descAnalysis = new javax.swing.JTextField();
        jL_counter = new javax.swing.JLabel();
        jCBox_counters = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jCheckBox_sort = new javax.swing.JCheckBox();
        jCheckBox_date = new javax.swing.JCheckBox();
        jCheckBox_nodes = new javax.swing.JCheckBox();
        jCheckBox_CumulDate = new javax.swing.JCheckBox();
        jL_reportList = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTF_reportList = new javax.swing.JTextField();
        jB_reportList = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jB_cancel = new javax.swing.JButton();
        
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analysis_32.png")));
        jL_title.setText("Analysis Build");
        jL_title.setMinimumSize(new java.awt.Dimension(186, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(186, 40));
        add(jL_title, java.awt.BorderLayout.NORTH);
        
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setOpaque(false);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setBorder(new javax.swing.border.TitledBorder(null, " Analysis Single Operation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_south.setPreferredSize(new java.awt.Dimension(10, 165));
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 100));
        jL_id.setBackground(new java.awt.Color(230, 230, 230));
        jL_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_id.setText("Id Analysis");
        jL_id.setToolTipText("ID Analysis");
        jL_id.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_id.setMaximumSize(new java.awt.Dimension(18, 20));
        jL_id.setMinimumSize(new java.awt.Dimension(30, 22));
        jL_id.setPreferredSize(new java.awt.Dimension(30, 22));
        jL_id.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_id.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel3.add(jL_id, gridBagConstraints1);
        
        jTF_IdAnalysis.setBackground(new java.awt.Color(230, 230, 230));
        jTF_IdAnalysis.setEditable(false);
        jTF_IdAnalysis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_IdAnalysis.setToolTipText("ID Element");
        jTF_IdAnalysis.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_IdAnalysis.setMinimumSize(new java.awt.Dimension(40, 22));
        jTF_IdAnalysis.setPreferredSize(new java.awt.Dimension(40, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel3.add(jTF_IdAnalysis, gridBagConstraints1);
        
        jL_desc.setBackground(new java.awt.Color(230, 230, 230));
        jL_desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_desc.setText("Description");
        jL_desc.setToolTipText("(Max 160 Characters)");
        jL_desc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_desc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_desc.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel3.add(jL_desc, gridBagConstraints1);
        
        jTF_descAnalysis.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_descAnalysis.setToolTipText("(Max 160 Characters)");
        jTF_descAnalysis.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_descAnalysis.setMinimumSize(new java.awt.Dimension(160, 22));
        jTF_descAnalysis.setPreferredSize(new java.awt.Dimension(380, 22));
        jTF_descAnalysis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_descAnalysisKeyReleased(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel3.add(jTF_descAnalysis, gridBagConstraints1);
        
        jL_counter.setBackground(new java.awt.Color(230, 230, 230));
        jL_counter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_counter.setText("Counters Kit");
        jL_counter.setToolTipText("Counters Kit");
        jL_counter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_counter.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_counter.setPreferredSize(new java.awt.Dimension(100, 22));
        jL_counter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_counter.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel3.add(jL_counter, gridBagConstraints1);
        
        jCBox_counters.setBackground(java.awt.Color.white);
        jCBox_counters.setMaximumRowCount(6);
        jCBox_counters.setMinimumSize(new java.awt.Dimension(100, 22));
        jCBox_counters.setPreferredSize(new java.awt.Dimension(180, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel3.add(jCBox_counters, gridBagConstraints1);
        
        jPanel5.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_sort.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_sort.setText("Sort Selection");
        jCheckBox_sort.setToolTipText("(True/False)");
        jCheckBox_sort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox_sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCheckBox_sort.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheckBox_sort.setPreferredSize(new java.awt.Dimension(40, 22));
        jCheckBox_sort.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_sort.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCheckBox_sort.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_sort.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jCheckBox_sort, gridBagConstraints2);
        
        jCheckBox_date.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_date.setText("Date Selection");
        jCheckBox_date.setToolTipText("(True/False)");
        jCheckBox_date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox_date.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCheckBox_date.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheckBox_date.setPreferredSize(new java.awt.Dimension(40, 22));
        jCheckBox_date.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_date.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCheckBox_date.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_date.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jCheckBox_date, gridBagConstraints2);
        
        jCheckBox_nodes.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_nodes.setText("Nodes Selection");
        jCheckBox_nodes.setToolTipText("(True/False)");
        jCheckBox_nodes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox_nodes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCheckBox_nodes.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheckBox_nodes.setPreferredSize(new java.awt.Dimension(40, 22));
        jCheckBox_nodes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_nodes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCheckBox_nodes.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_nodes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jCheckBox_nodes, gridBagConstraints2);
        
        jCheckBox_CumulDate.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox_CumulDate.setText("Cumulate Data");
        jCheckBox_CumulDate.setToolTipText("(True/False)");
        jCheckBox_CumulDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox_CumulDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCheckBox_CumulDate.setMinimumSize(new java.awt.Dimension(40, 22));
        jCheckBox_CumulDate.setPreferredSize(new java.awt.Dimension(40, 22));
        jCheckBox_CumulDate.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_CumulDate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCheckBox_CumulDate.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox_CumulDate.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jCheckBox_CumulDate, gridBagConstraints2);
        
        jL_reportList.setBackground(new java.awt.Color(230, 230, 230));
        jL_reportList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_reportList.setText("Reports List");
        jL_reportList.setToolTipText("");
        jL_reportList.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_reportList.setPreferredSize(new java.awt.Dimension(120, 22));
        jL_reportList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_reportList.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jL_reportList, gridBagConstraints2);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jTF_reportList.setBackground(new java.awt.Color(230, 230, 230));
        jTF_reportList.setEditable(false);
        jTF_reportList.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_reportList.setToolTipText("");
        jTF_reportList.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_reportList.setMinimumSize(new java.awt.Dimension(65, 22));
        jTF_reportList.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jPanel1.add(jTF_reportList, gridBagConstraints3);
        
        jB_reportList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_reportList.setToolTipText("Edit Exceptions");
        jB_reportList.setBorderPainted(false);
        jB_reportList.setContentAreaFilled(false);
        jB_reportList.setFocusPainted(false);
        jB_reportList.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_reportList.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_reportList.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_reportList.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_reportList.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_reportList.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_reportList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_reportListActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        jPanel1.add(jB_reportList, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 1, 1, 1);
        jPanel5.add(jPanel1, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(10, 1, 1, 1);
        jPanel3.add(jPanel5, gridBagConstraints1);
        
        jP_south.add(jPanel3, java.awt.BorderLayout.CENTER);
        
        jPanel11.setLayout(new java.awt.BorderLayout());
        
        jPanel11.setBackground(new java.awt.Color(230, 230, 230));
        jPanel11.setPreferredSize(new java.awt.Dimension(152, 40));
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
        
        jPanel11.add(jB_commit, java.awt.BorderLayout.EAST);
        
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
        
        jPanel2.add(jB_cancel);
        
        jPanel11.add(jPanel2, java.awt.BorderLayout.CENTER);
        
        jP_south.add(jPanel11, java.awt.BorderLayout.SOUTH);
        
        add(jP_south, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private void jB_reportListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reportListActionPerformed
        // Add your handling code here:
        this.setCursor(Cur_wait);

        String title =" ( New Analysis )";
        if(Selected_row_Analysis.NOMEANALISI.compareTo("")!=0)
            title = " of Analysis with name: "+Selected_row_Analysis.NOMEANALISI;
        
        JListChooser listChooser_ReportList = new JListChooser(ADAMS_GlobalParam.JFRAME_TAB, "Reports List "+title, true);
     
        
        // ----------------- Lettura DB 
        
        Vector V_ALL_ID_Report  = new Vector();
        Vector V_All_TAG_Report = new Vector();
        String sel_ReadALLAnalysis = ("select distinct IDREPORTSCHEMA,TAG from tab_report "+
                                    " where TIPO_DI_CONFIGURAZIONE = '"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by TAG");

        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ReadALLAnalysis,SQLStatement);
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {                    
                    V_ALL_ID_Report.addElement(new Integer(rs.getInt("IDREPORTSCHEMA")));
                    V_All_TAG_Report.addElement(rs.getString("TAG"));
                }
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            } 
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_JP_Analisi-2) SQL Operation " + exc.toString());
        }

        // END ----------------- Lettura DB 
        
       // Available Items //////// 
        Object[][] All_reportList = new Object[V_ALL_ID_Report.size()][2];
        int SIZE_ALL = All_reportList.length;
        //System.out.println("SIZE_ALL  ......-----.... "+SIZE_ALL);
        
        Object[][] l1= new Object[SIZE_ALL][2];
        for(int i=0; i<SIZE_ALL; i++)
        {            
            l1[i][0] = (Integer)V_ALL_ID_Report.elementAt(i);   
            l1[i][1] = (String)V_All_TAG_Report.elementAt(i); 
        }
        
        // Selected Items  ////////
        int SIZE_SELECTED = Selected_row_Analysis.LISTA_REPORT.size();
        //System.out.println("SIZE_SELECTED  ......-----.... "+SIZE_SELECTED);
        Object[][] l2 = new Object[SIZE_SELECTED][2];

        for(int i=0; i<SIZE_SELECTED; i++)
        {
            Integer Id_selected = (Integer)Selected_row_Analysis.LISTA_REPORT.elementAt(i);
            String tag_selected ="";
            for(int x=0; x<V_ALL_ID_Report.size(); x++)
            {
                if(Id_selected.intValue() == ((Integer)V_ALL_ID_Report.elementAt(x)).intValue())
                {
                    tag_selected = (String)V_All_TAG_Report.elementAt(x);
                    break;
                }
            }
            l2[i][0] = Id_selected;
            l2[i][1] = tag_selected;
        }
        
        listChooser_ReportList.setListChooserData(l1, l2);
        //listChooser_ReportList.show();
        listChooser_ReportList.setVisible(true);

        // read selected Items
        boolean empty = true;
        Selected_row_Analysis.LISTA_REPORT.removeAllElements();
        
        l2 = listChooser_ReportList.getListChooserData();
        for(int i=0; i<l2.length; i++)
        {
            Integer ID_ReportSel = ((Integer)(l2[i][0]));
            Selected_row_Analysis.LISTA_REPORT.addElement(ID_ReportSel);
            empty = false;
            //System.out.println("USER DATA:"+l2[i][0]+" - "+l2[i][1]);            
        }
        listChooser_ReportList.dispose();
        
        if(empty)
        {
            jTF_reportList.setText(StrNotDefined);
            jTF_reportList.setForeground(Color.red);
        }
        else
        {
            jTF_reportList.setText(strDefined);
            jTF_reportList.setForeground(Color.green.darker());
        }
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_reportListActionPerformed

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        jTable_Analysis.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        //System.out.println("COMMIT");
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            AddRowJTabel_general();
            setSelected_jTableRow();
            this.setCursor(Cur_default);
            return;
        }
        
        if(Selected_row_Analysis == null)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
            return;
        }
        
        if(jrb_delete.isSelected())
        {           
            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete Analysis: "+Selected_row_Analysis.IDANALISI+" "+Selected_row_Analysis.NOMEANALISI+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
            {
                int answer_del = Selected_row_Analysis.delete_Analysis();
                if(answer_del >= 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Analysis Id # "+Selected_row_Analysis.IDANALISI+" has been deleted.","INFO",3);
                    
                    //rimuovo i link all'analisi eliminata
                    boolean removeLinkAnalysis = parent_V_TAB_CONFIG.deleteAllAnalysys_withID(Selected_row_Analysis.IDANALISI);
                    
                     if(removeLinkAnalysis)
                           ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"The requested Analysis and any linkage to it has been deleted.","INFO",3);
                                  
                    AddRowJTabel_general();
                    setSelected_jTableRow();                    
                    setEnabledClean_AllGui(false,true);
                }
                else
                {
                    if(answer_del == -1)
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                }
            }// end Yes_No == 1 Delete            
        }// end jrb_delete.isSelected()
        else
        {   
            Selected_row_Analysis.NOMEANALISI   = jTF_descAnalysis.getText().trim();
            Selected_row_Analysis.IDCOUNTERKIT  = get_IDCounterKIT((String)jCBox_counters.getSelectedItem()); //NUMBER                                                

            char ck = 'N';
            if(jCheckBox_sort.isSelected())
                ck = 'Y';
            Selected_row_Analysis.FLAGSORT = ck;

            char ck1 = 'N';
            if(jCheckBox_date.isSelected())
                ck1 = 'Y';
            Selected_row_Analysis.FLAGDATA = ck1;

            char ck2 = 'N';
            if(jCheckBox_nodes.isSelected())
                ck2 = 'Y';
            Selected_row_Analysis.FLAGCENTRALE = ck2;

            char ck3 = 'N';
            if(jCheckBox_CumulDate.isSelected())
                ck3 = 'Y';
            Selected_row_Analysis.FLAGCUMULAZIONE = ck3;
            
            if(jrb_add.isSelected())
            {              
                int Answer_InsertAnalysis = Selected_row_Analysis.insert_Analysis();
                if(Answer_InsertAnalysis == 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Analysis "+Selected_row_Analysis.NOMEANALISI+" has been added.","INFO",3);
                    AddRowJTabel_general();
                    jTable_Analysis.setSelectionBackground(java.awt.Color.yellow);
                    
                    //seleziona riga JTable
                    int ID_MAX = 0;
                    int indexSelected = 0;
                    for(int i=0; i<jTable_Analysis.getRowCount(); i++)
                    {
                        int ID = new Integer(""+jTable_Analysis.getValueAt(i,0)).intValue();
                        if(ID > ID_MAX)
                        {
                            ID_MAX = ID;
                            indexSelected = i;
                        }
                    }
                    
                    try
                    {   
                        jTable_Analysis.setRowSelectionInterval(0,indexSelected);
                        jScrollPane1.validate();
                    }
                    catch(Exception e){}
                    
                    setSelected_jTableRow();                    
                }
                else
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Analysis: "+Selected_row_Analysis.NOMEANALISI+".","ERROR",1);
            }
            else //jrb_modify.isSelected()
            {                
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change Analysis id # "+Selected_row_Analysis.IDANALISI+".     Confirm ?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    int answer_up = Selected_row_Analysis.update_Analysis();
                    
                    if(answer_up >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Analysis id # "+Selected_row_Analysis.IDANALISI+" has been changed.","INFO",3);
                        AddRowJTabel_general();   
                        
                        try
                        {   //seleziona riga JTable
                            for(int i=0; i<jTable_Analysis.getRowCount(); i++)
                            {
                                int ID_TABLE = new Integer(""+jTable_Analysis.getValueAt(i,0)).intValue();
                                if( ID_TABLE == Selected_row_Analysis.IDANALISI )
                                {
                                    jTable_Analysis.setRowSelectionInterval(i,i);
                                    jScrollPane1.validate();
                                    break;
                                }
                            }
                        }catch(Exception e){}
                        
                        setSelected_jTableRow();
                        jB_commit.setEnabled(false);
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                }
            }
        }
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jrb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteActionPerformed
        jTable_Analysis.setEnabled(true);
        jTable_Analysis.setSelectionBackground(java.awt.Color.red);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteActionPerformed

    private void jrb_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_modifyActionPerformed
        jTable_Analysis.setEnabled(true);
        jTable_Analysis.setSelectionBackground(java.awt.Color.green.darker());
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_modifyActionPerformed

    private void jrb_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addActionPerformed
        jTable_Analysis.setSelectionBackground(java.awt.Color.white);
        jTable_Analysis.clearSelection();
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addActionPerformed

    private void jTF_descAnalysisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_descAnalysisKeyReleased
        if(jTF_descAnalysis.getText().length() > 0)
            jB_commit.setEnabled(true);
        else
            jB_commit.setEnabled(false);
    }//GEN-LAST:event_jTF_descAnalysisKeyReleased


    private void jTable_AnalysisMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jrb_add.isSelected() == false)
            setSelected_jTableRow();
    }
    
    //Permette di resettare e ripulire tutti i widget di input dell'interfaccia.
    public void setEnabledClean_AllGui(boolean enable,boolean clean)
    {
        jTF_descAnalysis.setEnabled(enable);
        jCBox_counters.setEnabled(enable);
        jCheckBox_sort.setEnabled(enable);
        jCheckBox_date.setEnabled(enable);
        jCheckBox_nodes.setEnabled(enable);
        jCheckBox_CumulDate.setEnabled(enable);
        jTF_reportList.setEnabled(enable);
        jB_reportList.setEnabled(enable);

        if(clean)
        {      
            jTF_IdAnalysis.setText("");
            jTF_descAnalysis.setText("");
            try
            {
                jCBox_counters.setSelectedIndex(0);
            }
            catch(Exception e){}
            
            jCheckBox_sort.setSelected(false);
            jCheckBox_date.setSelected(false);
            jCheckBox_nodes.setSelected(false);
            jCheckBox_CumulDate.setSelected(false);
            jTF_reportList.setText(StrNotDefined);
            jTF_reportList.setForeground(Color.red);
        }
        if(jTF_descAnalysis.getText().length() == 0)
            jB_commit.setEnabled(false);
        else
            jB_commit.setEnabled(true);
    }
    private void setGlobalFont()
    {
        //Font Globali
        jL_title.setFont(ADAMS_GlobalParam.font_B12);
        jL_id.setFont(ADAMS_GlobalParam.font_B10);
        jTF_IdAnalysis.setFont(ADAMS_GlobalParam.font_B10);
        jL_desc.setFont(ADAMS_GlobalParam.font_B10);
        jTF_descAnalysis.setFont(ADAMS_GlobalParam.font_B10);
        jL_counter.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_counters.setFont(ADAMS_GlobalParam.font_B10);        
        jCheckBox_sort.setFont(ADAMS_GlobalParam.font_B10);        
        jCheckBox_date.setFont(ADAMS_GlobalParam.font_B10);        
        jCheckBox_nodes.setFont(ADAMS_GlobalParam.font_B10);        
        jCheckBox_CumulDate.setFont(ADAMS_GlobalParam.font_B10);
        jL_reportList.setFont(ADAMS_GlobalParam.font_B10);
        jTF_reportList.setFont(ADAMS_GlobalParam.font_B10);
    }
    
    
    public Vector read_Analysis()
    {
        V_Analysis.removeAllElements();
        
        OperThread = READ_ANALYSIS;
        
        th = null;
        th = new Thread(this,"read_Analysis");
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
        return V_Analysis;
    }
    
    
    public void run()
    {     
        if(OperThread == READ_ANALYSIS) //read_Analysis()
        {            

            
            String sel_ReadAnalysis = "select "+item_TAB_TYPE_ANALISI+" from "+name_TabAnalisi+
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
                        row_Analysis.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;

                        //"IDANALISI,NOMEANALISI,IDCOUNTERKIT,FLAGSORT,FLAGDATA,FLAGCENTRALE,FLAGCUMULAZIONE,LISTA_REPORT";

                    //*** Campi Analisi -- alcuni campi sono NOT Null sul DB ***//
                        row_Analysis.IDANALISI          = rs.getInt("IDANALISI");                   //not null sil DB

                        String nomeAnalisi = rs.getString("NOMEANALISI");
                        if(nomeAnalisi != null)
                            row_Analysis.NOMEANALISI = nomeAnalisi;

                        row_Analysis.IDCOUNTERKIT       = rs.getInt("IDCOUNTERKIT");
                        row_Analysis.FLAGSORT           = rs.getString("FLAGSORT").charAt(0);       //not null sil DB
                        row_Analysis.FLAGDATA           = rs.getString("FLAGDATA").charAt(0);       //not null sil DB
                        row_Analysis.FLAGCENTRALE       = rs.getString("FLAGCENTRALE").charAt(0);   //not null sil DB
                        row_Analysis.FLAGCUMULAZIONE    = rs.getString("FLAGCUMULAZIONE").charAt(0);//not null sil DB

                        // ------------- Nested LISTA_REPORT ------------- //
                        String[] nomeCampi      = {"IDREPORT"}; 
                        String linkTable  = "tab_list_report";
                        
                        String selectReadNested = "select ";
            
                        for(int i=0;i<nomeCampi.length;i++)
                        {
                            if(i!=nomeCampi.length-1)
                                selectReadNested += nomeCampi[i]+",";
                            else
                                selectReadNested += nomeCampi[i]+" ";  
                        }

                        selectReadNested += " from " + linkTable;
                        selectReadNested += " where ";
                        selectReadNested += " TIPO_DI_CONFIGURAZIONE='"+row_Analysis.TIPO_DI_CONFIGURAZIONE+"' and IDANALISI="+row_Analysis.IDANALISI;

                        //System.out.println(" --> selectReadNested : "+selectReadNested);
                        Statement SQLStatement_1 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                        ResultSet rsNested  = ADAMS_GlobalParam.connect_Oracle.Query_RS(selectReadNested,SQLStatement_1);
                        if(rsNested != null)
                        {
                            try
                            {
                                while (rsNested.next())
                                {
                                    row_Analysis.LISTA_REPORT.addElement(new Integer(rsNested.getInt(nomeCampi[0])));
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
                            System.out.println("(ADAMS_JP_Analisi-4) SQL Operation " + exc.toString());
                        }
                        
                        
                        // ------------- End Nested LISTA_REPORT ------------- //
                        
                       /* System.out.println();
                        System.out.println("--- Analisi TIPO_DI_CONFIGURAZIONE :"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"---");
                        System.out.println("IDANALISI       ->"+row_Analysis.IDANALISI);
                        System.out.println("NOMEANALISI     ->"+row_Analysis.NOMEANALISI);        
                        System.out.println("IDCOUNTERKIT    ->"+row_Analysis.IDCOUNTERKIT);   
                        System.out.println("FLAGSORT        ->"+row_Analysis.FLAGSORT);
                        System.out.println("FLAGDATA        ->"+row_Analysis.FLAGDATA);          
                        System.out.println("FLAGCENTRALE    ->"+row_Analysis.FLAGCENTRALE);               
                        System.out.println("FLAGCUMULAZIONE ->"+row_Analysis.FLAGCUMULAZIONE);
                        System.out.println();*/

                        V_Analysis.addElement(row_Analysis);
                    }
                }catch (Exception ex) 
                { 
                    System.out.println(ex.toString());
                    Thread_Work = false;
                }
            }
            
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JP_Analisi-3) SQL Operation " + exc.toString());
            }
            
            //System.out.println("V_Analysis SIZE "+V_Analysis.size());
            Thread_Work = false;
        }
    }
    
    //////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames ={ "Id",
                                        "Description",
                                        "Counter Kit",
                                        "Sort Sel.",
                                        "Date Sel.",
                                        "Nodes Sel.",
                                        "Cumulate Data",
                                        "Reports List"};
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
        public Object getValueAt(int row, int col) 
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
    private javax.swing.JLabel jL_title;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jL_id;
    private javax.swing.JTextField jTF_IdAnalysis;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JTextField jTF_descAnalysis;
    private javax.swing.JLabel jL_counter;
    private javax.swing.JComboBox jCBox_counters;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JCheckBox jCheckBox_sort;
    private javax.swing.JCheckBox jCheckBox_date;
    private javax.swing.JCheckBox jCheckBox_nodes;
    private javax.swing.JCheckBox jCheckBox_CumulDate;
    private javax.swing.JLabel jL_reportList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTF_reportList;
    private javax.swing.JButton jB_reportList;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JButton jB_commit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_modify;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables
    
    private boolean DEBUG = false;
    private javax.swing.JTable jTable_Analysis;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private int[] CellDimension = {30,160,120,30,30,30,50,60};
    private int minCellDimension = 30;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined";
    private String str_none         = "<none>";
    
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    private Vector V_Analysis = new Vector();
    private ADAMS_JF_ListValue JF_ReportsList = null;
    private ADAMS_TAB_TYPE_ANALISI Selected_row_Analysis = null;
    
    // ------ Thread
    private Thread th           = null;
    private boolean Thread_Work = false;
    private int OperThread      = -1;
    private int READ_ANALYSIS   = 0;
        
    // Info DB Analisi
    private final String name_TabAnalisi = "tab_analysis_type";
    private final String item_TAB_TYPE_ANALISI = "IDANALISI,NOMEANALISI,IDCOUNTERKIT,FLAGSORT,FLAGDATA,FLAGCENTRALE,FLAGCUMULAZIONE"; 
    
    private final String name_TabCounters = "tab_counters_kit";
    
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;
    private Vector V_CountersKit = null;
}


