/*
 *
 * ADAMS_JP_InputData.java
 *
 * Created on 26 maggio 2005, 11.18
 */

/**
 *
 * @author  root
 */
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.table.*;
import javax.swing.DefaultCellEditor;
import java.util.Vector;
import java.awt.Cursor;
import net.etech.*;
import net.etech.MDM.*;

 public class ADAMS_JP_InputData extends javax.swing.JPanel {

    /** Creates new form ADAMS_JP_InputData */
    public ADAMS_JP_InputData(ADAMS_TAB_INFO_CONFIG INFO_CONFIG) {
        initComponents();

        setMinimumSize(new java.awt.Dimension(800, 600));
        // *** Create JTABLE *** //

        jTable_general = new javax.swing.JTable();
        jTable_general.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Field N.", "Type", "Offset", "Type Size", "Is Array", "Array Size", "Real Size", "Tag", "Is Index"
        }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class,java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jTable_general.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general.setRowHeight(20);
        //jTable_general.setSelectionBackground(java.awt.Color.green);
        jTable_general.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general.setRowMargin(2);
        jTable_general.setAutoCreateColumnsFromModel(false);
        jTable_general.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_generalMouseReleased(evt);
            }
        });

        jScrollPane2.setViewportView(jTable_general);
        defaultTableModel_general = (javax.swing.table.DefaultTableModel)jTable_general.getModel();

        // *** END // *** Create JTABLE *** // *** //

        this.local_INFO_CONFIG = INFO_CONFIG;
         V_TAB_CONFIG = new ADAMS_Vector_TAB_CONFIG(this);

        jLabel1.setText("INPUT DATA of "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        jT_fsize.setText(""+local_INFO_CONFIG.DR_REC_LEN);

        jTF_ArraySize.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,10));
        jTF_TagMess.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));

        jTF_indexSuffix.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS, mdm_server_server.eTE_SUFFIX_LEN));

        jTF_AggrValue_RT.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,4));
        jTF_AggrValue_DF.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,4));
        jTF_startValue.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,5));




        ///// Type DR
        jCB_Type.removeAllItems();
        VectorType = new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,ADAMS_GlobalParam.TYPE_HELP_DR);
        Vector AllDesc = VectorType.vgetDesc();
        for(int i=0; i<AllDesc.size(); i++)
            jCB_Type.addItem((String)AllDesc.elementAt(i));
        //////////

        jTF_asize.setBackground(java.awt.Color.red);

        //Font
        jLabel1.setFont(ADAMS_GlobalParam.font_B12);
        jTF_Field.setFont(ADAMS_GlobalParam.font_B10);
        jCB_Type.setFont(ADAMS_GlobalParam.font_B10);
        jTF_Offset.setFont(ADAMS_GlobalParam.font_B10);
        jTF_TypeSize.setFont(ADAMS_GlobalParam.font_B10);
        jTF_ArraySize.setFont(ADAMS_GlobalParam.font_B10);
        jTF_RealSize.setFont(ADAMS_GlobalParam.font_B10);
        jTF_TagMess.setFont(ADAMS_GlobalParam.font_B10);
        jL1.setFont(ADAMS_GlobalParam.font_B10);
        jL2.setFont(ADAMS_GlobalParam.font_B10);
        jL3.setFont(ADAMS_GlobalParam.font_B10);
        jL4.setFont(ADAMS_GlobalParam.font_B10);
        jL5.setFont(ADAMS_GlobalParam.font_B10);
        jL6.setFont(ADAMS_GlobalParam.font_B10);
        jL7.setFont(ADAMS_GlobalParam.font_B10);
        jL8.setFont(ADAMS_GlobalParam.font_B10);
        jCB_isArray.setFont(ADAMS_GlobalParam.font_B10);
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);
        jT_fsize.setFont(ADAMS_GlobalParam.font_B10);
        jLabel3.setFont(ADAMS_GlobalParam.font_B10);
        jTF_asize.setFont(ADAMS_GlobalParam.font_B10);
        jLabel4.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_Index.setFont(ADAMS_GlobalParam.font_B10);
        jLabel5.setFont(ADAMS_GlobalParam.font_B10);
        jTF_AggrValue_RT.setFont(ADAMS_GlobalParam.font_B10);
        jTF_AggrValue_DF.setFont(ADAMS_GlobalParam.font_B10);
        jLabel14.setFont(ADAMS_GlobalParam.font_B10);
        jLabel6.setFont(ADAMS_GlobalParam.font_B10);
        jTF_startValue.setFont(ADAMS_GlobalParam.font_B10);
        jLabel7.setFont(ADAMS_GlobalParam.font_B10);
        jTF_indexSuffix.setFont(ADAMS_GlobalParam.font_B10);

        // Cursor
        //jB_Cal_start.setCursor(Cur_hand);
        //jB_Cal_end.setCursor(Cur_hand);
        jCBox_Index.setCursor(Cur_hand);
        jrb_add.setCursor(Cur_hand);
        jrb_modify.setCursor(Cur_hand);
        jrb_delete.setCursor(Cur_hand);
        jBcommit.setCursor(Cur_hand);
        jBcancel.setCursor(Cur_hand);
        jCB_Type.setCursor(Cur_hand);

        //System.out.println("-InputData - > CONF_DEFAULT " +local_INFO_CONFIG.CONF_DEFAULT);

        if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
        {
            jrb_modify.setSelected(true);
            jP_butt1.remove(jrb_add);
            jP_butt1.remove(jrb_delete);
            setEnabledGUI_index(false,true);
        }
        else
        {
            jP_operation.remove(jP_indexProperties);
            jP_operation.setMinimumSize(new java.awt.Dimension(463, 200));
            jP_operation.setPreferredSize(new java.awt.Dimension(100, 200));
            jrb_add.setSelected(true);
        }

       /* ADAMS_InsertRowInpData rowInputData =  new ADAMS_InsertRowInpData();

        java.awt.GridBagConstraints gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 5;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 600;
        gridBagConstraints1.ipady = 75;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        add(rowInputData, gridBagConstraints1);*/
    }

    public void setParentJFrame( javax.swing.JFrame jframe)
    {
        this.parentJFrame = jframe;
    }

    public void SetTable()
    {
        this.setCursor(Cur_wait);

        jTable_general.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jTable_general.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        TableColumn column = null;
        for(int i=0; i<jTable_general.getColumnCount(); i++)
        {
            column = jTable_general.getColumnModel().getColumn(i);
            /*if(jTable_general.getColumnClass(i) == java.lang.Boolean.class)
            {
                column.setCellEditor(new DefaultCellEditor(new javax.swing.JCheckBox()));
            }*/

            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
            //if(CellDimension[i] == minCellDimension)
               //column.setMaxWidth(50);

        }

        //if((jTable_general.isShowing()) && (jTable_general.isVisible()))
            //jTable_general.updateUI();
        jScrollPane2.setViewportView(jTable_general);


        AddRowJTabel_general();
        setSelected_jTableRow();

        this.setCursor(Cur_default);
    }


    private boolean AddRowJTabel_general()
    {

        jTable_general.setCursor(Cur_wait);

        jLabel1.setText("INPUT DATA of "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        jT_fsize.setText(""+local_INFO_CONFIG.DR_REC_LEN);
        //Rimuovo tutte le righe della tabella

        defaultTableModel_general.getDataVector().removeAllElements();

        // Vettore Ordinato nel caso Configurazione Indice
        ADAMS_Vector_TAB_CONFIG V_OrderRow_for_INDEX = null;

        boolean ok_read = false;
        if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
        {
            V_OrderRow_for_INDEX = new ADAMS_Vector_TAB_CONFIG();
            ok_read= V_TAB_CONFIG.read_AllItem(ADAMS_GlobalParam.read_DR_TE_for_INDEX);
        }
        else
            ok_read= V_TAB_CONFIG.read_AllItem(ADAMS_GlobalParam.read_DR);

        boolean ERRORE = false;

        int ctrl_numberField = 1;
        int offset = 0;
        int V_SIZE = V_TAB_CONFIG.size();

        boolean error = false;
        int idDR_error = -1;

        if(V_SIZE == 0)
        {
            Last_row_TAB_CONFIG = null;
            Last_offset = 0;
        }
        else
        {
            int asize = 0;

            for(int i=0; i<V_SIZE; i++)
            {
                ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);

                int CAMPO_DR = row_TAB_CONFIG.POSIZIONE_CAMPO_DR;

                if(CAMPO_DR >= 1) // INPUT DATA VALIDI
                {
                    //*************************** I campi validi (!=-1) devono essere sequenziali progressivi di 1.
                    if(ctrl_numberField != CAMPO_DR)
                    {
                        System.out.println("ERRORE -- POSIZIONE_CAMPO_DR non sequenziale !!! "+ctrl_numberField+" != "+CAMPO_DR);
                        ERRORE = true;
                    }
                    ctrl_numberField++;
                    //***************************

                    int typeSize = row_TAB_CONFIG.SIZE_CAMPO_DR;

                    //if( typeSize>1 && ((offset%2) != 0) && !error )
                    if( typeSize>1 && ((offset%local_INFO_CONFIG.BOUNDARY) != 0) && !error )
                    {
                        error = true;
                        idDR_error = CAMPO_DR;
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Byte Boundary error at Row "+CAMPO_DR,"ERROR",1);
                    }

                    //int lastRow = jTable_general.getRowCount();
                    Vector V_addRow = new Vector();
                    V_addRow.addElement(new Integer(CAMPO_DR));                                 //Field N.

                    V_addRow.addElement(VectorType.getDesc(row_TAB_CONFIG.TIPO_CAMPO));
                    //V_addRow.addElement(new Integer(row_TAB_CONFIG.TIPO_CAMPO));              //Type

                    V_addRow.addElement(new Integer(offset));                                   //Offset


                    V_addRow.addElement(new Integer(typeSize));                                 //Type Size


                    int ArraySize = row_TAB_CONFIG.NUMERO_ELEM_ARRAY;
                    int realSize  = 0;
                    if (row_TAB_CONFIG.FLAG_ARRAY == 'Y') //Is Array
                    {
                         V_addRow.addElement(new Boolean(true));
                         realSize = (typeSize*ArraySize);
                         offset += realSize;
                    }
                    else
                    {
                        V_addRow.addElement(new Boolean(false));
                        offset += typeSize;
                        realSize = typeSize;
                    }

                    asize += realSize;
                    jTF_asize.setText(""+asize);
                    if (asize == local_INFO_CONFIG.DR_REC_LEN)
                        jTF_asize.setBackground(java.awt.Color.green.darker());
                    else
                        jTF_asize.setBackground(java.awt.Color.red);


                    V_addRow.addElement(new Integer(ArraySize));                                //Array Size
                    V_addRow.addElement(new Integer(realSize));                                 //Real Size
                    V_addRow.addElement(new String(row_TAB_CONFIG.LABEL_DR_NORMALIZZ));      //Tag-Description

                    V_addRow.addElement(new Boolean(row_TAB_CONFIG.SE_INDICE));

                    //Add RIGA
                    defaultTableModel_general.addRow(V_addRow);

                    //Nel Caso Configurazione Indice... seguo l'ordine di sequenzialità della tabella
                    if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
                        V_OrderRow_for_INDEX.addElement(row_TAB_CONFIG);

                    if(i == (V_SIZE-1))
                    {
                        Last_row_TAB_CONFIG = row_TAB_CONFIG;
                        Last_offset = offset;
                    }
                }
                /*else
                {
                    System.out.println(CAMPO_DR+" TAG = "+new String(row_TAB_CONFIG.TAG).trim()+" DESCRIPTION = "+new String(row_TAB_CONFIG.DESCRIPTION).trim());
                }*/
            }



            for(int i=0; i<V_SIZE; i++)
            {

                ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);

                int CAMPO_DR = row_TAB_CONFIG.POSIZIONE_CAMPO_DR;
                if(CAMPO_DR == -1)
                {
                    Vector V_addRow = new Vector();
                    V_addRow.addElement(new Integer(-1));                                       //Field N.
                    V_addRow.addElement("- Undefined - ");                                                  //Tipo Campo
                    V_addRow.addElement(new Integer(-1));                                       //Offset
                    V_addRow.addElement(new Integer(-1));                                       //Type Size
                    V_addRow.addElement(new Boolean(false));                                    //Is Array
                    V_addRow.addElement(new Integer(-1));                                       //Array Size
                    V_addRow.addElement(new Integer(-1));                                       //Real Size
                    V_addRow.addElement(new String(row_TAB_CONFIG.TAG).trim());               //Tag-Description
                    V_addRow.addElement(new Boolean(row_TAB_CONFIG.SE_INDICE));

                     //Add RIGA
                    defaultTableModel_general.addRow(V_addRow);

                    //Nel Caso Configurazione Indice... seguo l'ordine di sequenzialità della tabella
                    if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
                        V_OrderRow_for_INDEX.addElement(row_TAB_CONFIG);
                }
            }

        }

        if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
        {
            V_TAB_CONFIG.removeAllElements();
            for(int i=0; i<V_OrderRow_for_INDEX.size(); i++)
                V_TAB_CONFIG.addElement(V_OrderRow_for_INDEX.elementAt(i));
        }

        if(ERRORE == true)
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"ERROR: Fields numbering is NOT sequential. Check and correct.","ERROR",1);

        //if((jTable_general.isShowing()) && (jTable_general.isVisible()))
           // jTable_general.updateUI();
        jScrollPane2.setViewportView(jTable_general);
        jTable_general.setCursor(Cur_default);

        if(idDR_error > 0)
        {
            jTable_general.setSelectionBackground(java.awt.Color.red);
            try
            {   //seleziona riga JTable
                for(int i=0; i<jTable_general.getRowCount(); i++)
                {
                    int CamDR_TABLE = new Integer(""+jTable_general.getValueAt(i,0)).intValue();
                    if( CamDR_TABLE == idDR_error )
                    {
                        jTable_general.setRowSelectionInterval(i,i);
                        jScrollPane2.validate();
                        break;
                    }
                }
            }
            catch(Exception e){}
        }
        else
        {
            //jTable_general.setSelectionBackground(java.awt.Color.white);
            CLEARSELECTION = false;

            if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
                jrb_modify.setSelected(true);
            else
                jrb_add.setSelected(true);
        }

        return error;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bGroup_oper = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jP_operation = new javax.swing.JPanel();
        jL1 = new javax.swing.JLabel();
        jTF_Field = new javax.swing.JTextField();
        jL2 = new javax.swing.JLabel();
        jCB_Type = new javax.swing.JComboBox();
        jL3 = new javax.swing.JLabel();
        jTF_Offset = new javax.swing.JTextField();
        jL4 = new javax.swing.JLabel();
        jTF_TypeSize = new javax.swing.JTextField();
        jL5 = new javax.swing.JLabel();
        jCB_isArray = new javax.swing.JCheckBox();
        jL6 = new javax.swing.JLabel();
        jTF_ArraySize = new javax.swing.JTextField();
        jL7 = new javax.swing.JLabel();
        jTF_RealSize = new javax.swing.JTextField();
        jL8 = new javax.swing.JLabel();
        jTF_TagMess = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jL_helpText = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jBcommit = new javax.swing.JButton();
        jP_butt1 = new javax.swing.JPanel();
        jrb_add = new javax.swing.JRadioButton();
        jrb_modify = new javax.swing.JRadioButton();
        jrb_delete = new javax.swing.JRadioButton();
        jBcancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jT_fsize = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTF_asize = new javax.swing.JTextField();
        jP_indexProperties = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jCBox_Index = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jTF_AggrValue_RT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTF_startValue = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTF_indexSuffix = new javax.swing.JTextField();
        jTF_AggrValue_DF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        setMinimumSize(new java.awt.Dimension(800, 650));
        setPreferredSize(new java.awt.Dimension(800, 650));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input_data32.png"))); // NOI18N
        jLabel1.setText("INPUT DATA");
        jLabel1.setMinimumSize(new java.awt.Dimension(700, 40));
        jLabel1.setPreferredSize(new java.awt.Dimension(122, 40));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jScrollPane2.setAutoscrolls(true);
        add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jP_operation.setBackground(new java.awt.Color(230, 230, 230));
        jP_operation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Input Data Single Operation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11))); // NOI18N
        jP_operation.setMinimumSize(new java.awt.Dimension(463, 270));
        jP_operation.setPreferredSize(new java.awt.Dimension(100, 270));
        jP_operation.setLayout(new java.awt.GridBagLayout());

        jL1.setBackground(new java.awt.Color(230, 230, 230));
        jL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL1.setText("Field N.");
        jL1.setToolTipText("(Auto Insert)");
        jL1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL1.setMinimumSize(new java.awt.Dimension(20, 22));
        jL1.setPreferredSize(new java.awt.Dimension(20, 22));
        jL1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 2, 1);
        jP_operation.add(jL1, gridBagConstraints);

        jTF_Field.setBackground(new java.awt.Color(230, 230, 230));
        jTF_Field.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_Field.setToolTipText("(Auto Insert)");
        jTF_Field.setMinimumSize(new java.awt.Dimension(20, 22));
        jTF_Field.setPreferredSize(new java.awt.Dimension(20, 22));
        jTF_Field.setEnabled(false);
        jTF_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTF_FieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTF_FieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 1);
        jP_operation.add(jTF_Field, gridBagConstraints);

        jL2.setBackground(new java.awt.Color(230, 230, 230));
        jL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL2.setText("Type");
        jL2.setToolTipText("(Select Item)");
        jL2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL2.setMinimumSize(new java.awt.Dimension(100, 22));
        jL2.setPreferredSize(new java.awt.Dimension(100, 22));
        jL2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_operation.add(jL2, gridBagConstraints);

        jCB_Type.setMaximumRowCount(6);
        jCB_Type.setMinimumSize(new java.awt.Dimension(120, 22));
        jCB_Type.setPreferredSize(new java.awt.Dimension(120, 22));
        jCB_Type.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCB_TypeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCB_TypeFocusLost(evt);
            }
        });
        jCB_Type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_TypeItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_operation.add(jCB_Type, gridBagConstraints);

        jL3.setBackground(new java.awt.Color(230, 230, 230));
        jL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3.setText("Offset");
        jL3.setToolTipText("(Auto Insert)");
        jL3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL3.setMinimumSize(new java.awt.Dimension(25, 22));
        jL3.setPreferredSize(new java.awt.Dimension(25, 22));
        jL3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_operation.add(jL3, gridBagConstraints);

        jTF_Offset.setBackground(new java.awt.Color(230, 230, 230));
        jTF_Offset.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_Offset.setToolTipText("(Auto Insert)");
        jTF_Offset.setMinimumSize(new java.awt.Dimension(25, 22));
        jTF_Offset.setPreferredSize(new java.awt.Dimension(25, 22));
        jTF_Offset.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_operation.add(jTF_Offset, gridBagConstraints);

        jL4.setBackground(new java.awt.Color(230, 230, 230));
        jL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL4.setText("Type Size");
        jL4.setToolTipText("(Only Numbers)");
        jL4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL4.setMaximumSize(new java.awt.Dimension(30, 21));
        jL4.setMinimumSize(new java.awt.Dimension(20, 22));
        jL4.setPreferredSize(new java.awt.Dimension(20, 22));
        jL4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_operation.add(jL4, gridBagConstraints);

        jTF_TypeSize.setBackground(new java.awt.Color(230, 230, 230));
        jTF_TypeSize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_TypeSize.setToolTipText("(Only Numbers)");
        jTF_TypeSize.setMinimumSize(new java.awt.Dimension(20, 22));
        jTF_TypeSize.setPreferredSize(new java.awt.Dimension(20, 22));
        jTF_TypeSize.setEnabled(false);
        jTF_TypeSize.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTF_TypeSizeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTF_TypeSizeFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_operation.add(jTF_TypeSize, gridBagConstraints);

        jL5.setBackground(new java.awt.Color(230, 230, 230));
        jL5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL5.setText("Is Array");
        jL5.setToolTipText("(True/False)");
        jL5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL5.setMinimumSize(new java.awt.Dimension(20, 22));
        jL5.setPreferredSize(new java.awt.Dimension(20, 22));
        jL5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL5.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_operation.add(jL5, gridBagConstraints);

        jCB_isArray.setBackground(new java.awt.Color(230, 230, 230));
        jCB_isArray.setToolTipText("(True/False)");
        jCB_isArray.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCB_isArray.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCB_isArray.setPreferredSize(new java.awt.Dimension(20, 22));
        jCB_isArray.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCB_isArray.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCB_isArray.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCB_isArray.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jCB_isArray.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCB_isArrayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCB_isArrayFocusLost(evt);
            }
        });
        jCB_isArray.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_isArrayItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_operation.add(jCB_isArray, gridBagConstraints);

        jL6.setBackground(new java.awt.Color(230, 230, 230));
        jL6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL6.setText("Array Size");
        jL6.setToolTipText("(Only Numbers)");
        jL6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL6.setMaximumSize(new java.awt.Dimension(68, 22));
        jL6.setMinimumSize(new java.awt.Dimension(40, 22));
        jL6.setPreferredSize(new java.awt.Dimension(40, 22));
        jL6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL6.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_operation.add(jL6, gridBagConstraints);

        jTF_ArraySize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_ArraySize.setToolTipText("(Only Numbers)");
        jTF_ArraySize.setMinimumSize(new java.awt.Dimension(40, 22));
        jTF_ArraySize.setPreferredSize(new java.awt.Dimension(40, 22));
        jTF_ArraySize.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTF_ArraySizeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTF_ArraySizeFocusLost(evt);
            }
        });
        jTF_ArraySize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_ArraySizeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_operation.add(jTF_ArraySize, gridBagConstraints);

        jL7.setBackground(new java.awt.Color(230, 230, 230));
        jL7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL7.setText("Real Size");
        jL7.setToolTipText("(Auto Insert)");
        jL7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL7.setMaximumSize(new java.awt.Dimension(40, 21));
        jL7.setMinimumSize(new java.awt.Dimension(20, 22));
        jL7.setPreferredSize(new java.awt.Dimension(20, 22));
        jL7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL7.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_operation.add(jL7, gridBagConstraints);

        jTF_RealSize.setBackground(new java.awt.Color(230, 230, 230));
        jTF_RealSize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_RealSize.setToolTipText("(Auto Insert)");
        jTF_RealSize.setMinimumSize(new java.awt.Dimension(30, 22));
        jTF_RealSize.setPreferredSize(new java.awt.Dimension(30, 22));
        jTF_RealSize.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_operation.add(jTF_RealSize, gridBagConstraints);

        jL8.setBackground(new java.awt.Color(230, 230, 230));
        jL8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL8.setText("Tag");
        jL8.setToolTipText("(Max 30 Characters)");
        jL8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL8.setMinimumSize(new java.awt.Dimension(60, 22));
        jL8.setPreferredSize(new java.awt.Dimension(60, 22));
        jL8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL8.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 4);
        jP_operation.add(jL8, gridBagConstraints);

        jTF_TagMess.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_TagMess.setToolTipText("(Max 30 Characters)");
        jTF_TagMess.setMinimumSize(new java.awt.Dimension(60, 22));
        jTF_TagMess.setPreferredSize(new java.awt.Dimension(60, 22));
        jTF_TagMess.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTF_TagMessFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTF_TagMessFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jP_operation.add(jTF_TagMess, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setMinimumSize(new java.awt.Dimension(72, 20));
        jPanel3.setPreferredSize(new java.awt.Dimension(152, 100));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Help"));
        jPanel2.setPreferredSize(new java.awt.Dimension(110, 65));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jL_helpText.setEditable(false);
        jL_helpText.setLineWrap(true);
        jL_helpText.setWrapStyleWord(true);
        jL_helpText.setBorder(null);
        jPanel2.add(jL_helpText, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(90, 30));
        jPanel4.setPreferredSize(new java.awt.Dimension(90, 30));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        jBcommit.setBackground(new java.awt.Color(230, 230, 230));
        jBcommit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg"))); // NOI18N
        jBcommit.setBorderPainted(false);
        jBcommit.setContentAreaFilled(false);
        jBcommit.setFocusPainted(false);
        jBcommit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jBcommit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jBcommit.setMaximumSize(new java.awt.Dimension(32768, 32768));
        jBcommit.setMinimumSize(new java.awt.Dimension(80, 30));
        jBcommit.setPreferredSize(new java.awt.Dimension(80, 30));
        jBcommit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg"))); // NOI18N
        jBcommit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg"))); // NOI18N
        jBcommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcommitActionPerformed(evt);
            }
        });
        jPanel4.add(jBcommit);

        jPanel3.add(jPanel4, java.awt.BorderLayout.EAST);

        jP_butt1.setBackground(new java.awt.Color(230, 230, 230));
        jP_butt1.setMinimumSize(new java.awt.Dimension(250, 30));
        jP_butt1.setPreferredSize(new java.awt.Dimension(250, 30));
        jP_butt1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jrb_add.setBackground(new java.awt.Color(230, 230, 230));
        bGroup_oper.add(jrb_add);
        jrb_add.setContentAreaFilled(false);
        jrb_add.setFocusPainted(false);
        jrb_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID.jpg"))); // NOI18N
        jrb_add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg"))); // NOI18N
        jrb_add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_over.jpg"))); // NOI18N
        jrb_add.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg"))); // NOI18N
        jrb_add.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg"))); // NOI18N
        jrb_add.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_addItemStateChanged(evt);
            }
        });
        jP_butt1.add(jrb_add);

        jrb_modify.setBackground(new java.awt.Color(230, 230, 230));
        bGroup_oper.add(jrb_modify);
        jrb_modify.setContentAreaFilled(false);
        jrb_modify.setFocusPainted(false);
        jrb_modify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_modify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID.jpg"))); // NOI18N
        jrb_modify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg"))); // NOI18N
        jrb_modify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_over.jpg"))); // NOI18N
        jrb_modify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg"))); // NOI18N
        jrb_modify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg"))); // NOI18N
        jrb_modify.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_modifyItemStateChanged(evt);
            }
        });
        jP_butt1.add(jrb_modify);

        jrb_delete.setBackground(new java.awt.Color(230, 230, 230));
        bGroup_oper.add(jrb_delete);
        jrb_delete.setContentAreaFilled(false);
        jrb_delete.setFocusPainted(false);
        jrb_delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID.jpg"))); // NOI18N
        jrb_delete.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg"))); // NOI18N
        jrb_delete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_over.jpg"))); // NOI18N
        jrb_delete.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg"))); // NOI18N
        jrb_delete.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg"))); // NOI18N
        jrb_delete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_deleteItemStateChanged(evt);
            }
        });
        jP_butt1.add(jrb_delete);

        jBcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg"))); // NOI18N
        jBcancel.setToolTipText("Cancel");
        jBcancel.setBorderPainted(false);
        jBcancel.setContentAreaFilled(false);
        jBcancel.setFocusPainted(false);
        jBcancel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jBcancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jBcancel.setMaximumSize(new java.awt.Dimension(0, 0));
        jBcancel.setMinimumSize(new java.awt.Dimension(56, 30));
        jBcancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg"))); // NOI18N
        jBcancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg"))); // NOI18N
        jBcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelActionPerformed(evt);
            }
        });
        jP_butt1.add(jBcancel);

        jPanel3.add(jP_butt1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jP_operation.add(jPanel3, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(430, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(426, 30));

        jLabel2.setText("Final Record Size");
        jPanel1.add(jLabel2);

        jT_fsize.setEditable(false);
        jT_fsize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jT_fsize.setText("0");
        jT_fsize.setMinimumSize(new java.awt.Dimension(100, 19));
        jT_fsize.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel1.add(jT_fsize);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Actual Size");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 18));
        jPanel1.add(jLabel3);

        jTF_asize.setEditable(false);
        jTF_asize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_asize.setText("0");
        jTF_asize.setMinimumSize(new java.awt.Dimension(100, 19));
        jTF_asize.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel1.add(jTF_asize);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 2);
        jP_operation.add(jPanel1, gridBagConstraints);

        jP_indexProperties.setBackground(new java.awt.Color(230, 230, 230));
        jP_indexProperties.setBorder(javax.swing.BorderFactory.createTitledBorder(" Index Properties "));
        jP_indexProperties.setMinimumSize(new java.awt.Dimension(200, 72));
        jP_indexProperties.setPreferredSize(new java.awt.Dimension(200, 72));
        jP_indexProperties.setLayout(new java.awt.GridBagLayout());

        jLabel4.setBackground(new java.awt.Color(230, 230, 230));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Index");
        jLabel4.setToolTipText("Is Index (True/False)");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setMaximumSize(new java.awt.Dimension(50, 21));
        jLabel4.setMinimumSize(new java.awt.Dimension(50, 22));
        jLabel4.setPreferredSize(new java.awt.Dimension(50, 22));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 2, 50);
        jP_indexProperties.add(jLabel4, gridBagConstraints);

        jCBox_Index.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_Index.setToolTipText("(True/False)");
        jCBox_Index.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCBox_Index.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jCBox_Index.setMinimumSize(new java.awt.Dimension(23, 22));
        jCBox_Index.setPreferredSize(new java.awt.Dimension(20, 22));
        jCBox_Index.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCBox_Index.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jCBox_Index.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jCBox_Index.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jCBox_Index.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCBox_IndexMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        jP_indexProperties.add(jCBox_Index, gridBagConstraints);

        jLabel5.setBackground(new java.awt.Color(230, 230, 230));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ag. R.T. Value ");
        jLabel5.setToolTipText("(Only Numbers)");
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setMinimumSize(new java.awt.Dimension(75, 22));
        jLabel5.setPreferredSize(new java.awt.Dimension(75, 22));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_indexProperties.add(jLabel5, gridBagConstraints);

        jTF_AggrValue_RT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_AggrValue_RT.setToolTipText("(Only Numbers)");
        jTF_AggrValue_RT.setMinimumSize(new java.awt.Dimension(40, 22));
        jTF_AggrValue_RT.setPreferredSize(new java.awt.Dimension(40, 22));
        jTF_AggrValue_RT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_AggrValue_RTKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_indexProperties.add(jTF_AggrValue_RT, gridBagConstraints);

        jLabel6.setBackground(new java.awt.Color(230, 230, 230));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Start Value Len.");
        jLabel6.setToolTipText("(Only Numbers)");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setMinimumSize(new java.awt.Dimension(65, 22));
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 22));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_indexProperties.add(jLabel6, gridBagConstraints);

        jTF_startValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_startValue.setToolTipText("(Only Numbers)");
        jTF_startValue.setMinimumSize(new java.awt.Dimension(40, 22));
        jTF_startValue.setPreferredSize(new java.awt.Dimension(40, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_indexProperties.add(jTF_startValue, gridBagConstraints);

        jLabel7.setBackground(new java.awt.Color(230, 230, 230));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Index Suffix");
        jLabel7.setToolTipText("(Max 30 Characters)");
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.setMinimumSize(new java.awt.Dimension(50, 22));
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 22));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 50);
        jP_indexProperties.add(jLabel7, gridBagConstraints);

        jTF_indexSuffix.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_indexSuffix.setToolTipText("(Max 30 Characters)");
        jTF_indexSuffix.setMinimumSize(new java.awt.Dimension(4, 22));
        jTF_indexSuffix.setPreferredSize(new java.awt.Dimension(4, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jP_indexProperties.add(jTF_indexSuffix, gridBagConstraints);

        jTF_AggrValue_DF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_AggrValue_DF.setToolTipText("(Only Numbers)");
        jTF_AggrValue_DF.setMinimumSize(new java.awt.Dimension(40, 22));
        jTF_AggrValue_DF.setPreferredSize(new java.awt.Dimension(40, 22));
        jTF_AggrValue_DF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_AggrValue_DFKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jP_indexProperties.add(jTF_AggrValue_DF, gridBagConstraints);

        jLabel14.setBackground(new java.awt.Color(230, 230, 230));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ag. Deferred Value ");
        jLabel14.setToolTipText("(Only Numbers)");
        jLabel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel14.setMinimumSize(new java.awt.Dimension(82, 22));
        jLabel14.setPreferredSize(new java.awt.Dimension(82, 22));
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 1);
        jP_indexProperties.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jP_operation.add(jP_indexProperties, gridBagConstraints);

        add(jP_operation, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jTF_AggrValue_RTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_AggrValue_RTKeyReleased
        String value_str = jTF_AggrValue_RT.getText().trim();
        int MAX_VALUE = 3000;

        if(value_str.length() >= 4)
        {
            int value = MAX_VALUE;
            try
            {
                 value = (new Integer(value_str)).intValue();
            }
            catch(NumberFormatException e)
            {

            }
            //System.out.println("value "+value);
            if(value > 3000)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"  Max value < 3000 >.","INFO",3);
                jTF_AggrValue_RT.setText(""+MAX_VALUE);
            }
            else
                jTF_AggrValue_RT.setText(""+value);
        }
        else if (value_str.startsWith("0"))
        {
            int value = 0;
            try
            {
                 value = (new Integer(value_str)).intValue();
            }
            catch(NumberFormatException e)
            {
                value = 0;
            }
            jTF_AggrValue_RT.setText(""+value);
        }
    }//GEN-LAST:event_jTF_AggrValue_RTKeyReleased

    private void jTF_AggrValue_DFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_AggrValue_DFKeyReleased
        // Add your handling code here:

        String value_str = jTF_AggrValue_DF.getText().trim();
        int MAX_VALUE = 3000;
        if(value_str.length() >= 4)
        {
            int value = MAX_VALUE;
            try
            {
                value = (new Integer(value_str)).intValue();
            }
            catch(NumberFormatException e){

            }
            //System.out.println("value "+value);
            if(value > 3000)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"  Max value < 3000 >.","INFO",3);
                jTF_AggrValue_DF.setText(""+MAX_VALUE);
            }
            else
                jTF_AggrValue_DF.setText(""+value);
        }
        else if (value_str.startsWith("0"))
        {
            int value = 0;
            try
            {
                value = (new Integer(value_str)).intValue();
            }
            catch(NumberFormatException e)
            {
                value = 0;
            }
            jTF_AggrValue_DF.setText(""+value);
        }

    }//GEN-LAST:event_jTF_AggrValue_DFKeyReleased

    private void jCBox_IndexMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBox_IndexMouseReleased
        setEnabledGUI_index(jCBox_Index.isSelected(),false);
    }//GEN-LAST:event_jCBox_IndexMouseReleased


    private void setEnabledGUI_index(boolean enable, boolean clear)
    {
        //jB_Cal_end.setEnabled(enable);
        //jB_Cal_start.setEnabled(enable);
        jTF_AggrValue_RT.setEnabled(enable);
        jTF_AggrValue_DF.setEnabled(enable);
        jTF_startValue.setEnabled(enable);
        jTF_indexSuffix.setEnabled(enable);
        //jTF_DD_Start.setEnabled(enable);
        //jTF_MM_Start.setEnabled(enable);
        //jTF_YY_Start.setEnabled(enable);
        //jTF_DD_End.setEnabled(enable);
        //jTF_MM_End.setEnabled(enable);
        //jTF_YY_End.setEnabled(enable);

        if(clear)
        {
            jTF_AggrValue_RT.setText("");
            jTF_AggrValue_DF.setText("");
            jTF_startValue.setText("");
            jTF_indexSuffix.setText("");
            //jTF_DD_Start.setText("");
            //jTF_MM_Start.setText("");
            //jTF_YY_Start.setText("");
            //jTF_DD_End.setText("");
            //jTF_MM_End.setText("");
            //jTF_YY_End.setText("");
        }
    }

    /*
    private void jB_Cal_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_Cal_endActionPerformed
        if(cal_end != null)
            cal_end.dispose();

        cal_end = new ADAMS_Calendar("Select End Date",jTF_DD_End,jTF_MM_End,jTF_YY_End,parentJFrame);
    }//GEN-LAST:event_jB_Cal_endActionPerformed

    private void jB_Cal_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_Cal_startActionPerformed
        if(cal_start != null)
            cal_start.dispose();

        cal_start = new ADAMS_Calendar("Select Start Date",jTF_DD_Start,jTF_MM_Start,jTF_YY_Start,parentJFrame);
    }//GEN-LAST:event_jB_Cal_startActionPerformed
*/
    private void jTF_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_FieldFocusLost
        jL1.setForeground(java.awt.Color.black);
        jL1.setBackground(colorDefault);

        jL_helpText.setText("");
    }//GEN-LAST:event_jTF_FieldFocusLost

    private void jTF_FieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_FieldFocusGained
        jL1.setForeground(java.awt.Color.white);
        jL1.setBackground(java.awt.Color.darkGray);

        jL_helpText.setText("Indicate the position of the field into the record and is automatically assigned. You can also change it to place/move the field along tha record.");
    }//GEN-LAST:event_jTF_FieldFocusGained

    private void jTable_generalMouseReleased(java.awt.event.MouseEvent evt) {
        if(jrb_add.isSelected() == false)
        {
            setSelected_jTableRow();
            if( jrb_modify.isSelected() == true )
            {
                jTF_Field.setEnabled(true);
                jTF_Field.setBackground(java.awt.Color.white);
            }
        }
    }
    private void jBcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelActionPerformed
        jTable_general.clearSelection();
        setSelected_jTableRow();

        if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
            setEnabledGUI_index(jCBox_Index.isSelected(),true);

    }//GEN-LAST:event_jBcancelActionPerformed

    private void jBcommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcommitActionPerformed

        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            jTable_general.setEnabled(false);
            jTable_general.setSelectionBackground(java.awt.Color.white);
            jTable_general.clearSelection();
            AddRowJTabel_general();
            setSelected_jTableRow();
            return;
        }

        if(jrb_delete.isSelected())
        {
            if(Selected_row_TAB_CONFIG == null)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
                return;
            }
            else
            {
                ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to delete Input Data Field # "+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+"' Confirm?","Warning",6);
                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    int RemoveAggreRes_PosElem = Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO;
                    int answer_del = Selected_row_TAB_CONFIG.delete_ROW();
                    if(answer_del >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Input Data Field # "+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+" has been deleted.","INFO",3);

                        //ordinamento POSIZIONE_CAMPO_DR
                        V_TAB_CONFIG.DeleteRow_andOrderAllPosDR(Selected_row_TAB_CONFIG);

                        jTable_general.clearSelection();
                        AddRowJTabel_general();
                        setSelected_jTableRow();

                        boolean flagDeleteAgg = V_TAB_CONFIG.deleteAgregateRestriction(RemoveAggreRes_PosElem);
                        flagDeleteAgg = V_TAB_CONFIG.deleteAllRelation_withID(RemoveAggreRes_PosElem);

                        if(flagDeleteAgg)
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"The requested Element and any linkage to it has been deleted.","INFO",3);
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                }
            }
        }
        else
        {
            try
            {
                int actualSize  = (new Integer(jTF_asize.getText())).intValue();
                int NewrealSize = (new Integer(jTF_RealSize.getText())).intValue();
                int maxSize     = (new Integer(jT_fsize.getText())).intValue();

                int SizeElement_Selected = 0;
                if(jrb_modify.isSelected())
                {
                    SizeElement_Selected = Selected_row_TAB_CONFIG.SIZE_CAMPO_DR;
                    if (Selected_row_TAB_CONFIG.FLAG_ARRAY == 'Y')
                        SizeElement_Selected = SizeElement_Selected*Selected_row_TAB_CONFIG.NUMERO_ELEM_ARRAY;
                }

                if((actualSize+NewrealSize-SizeElement_Selected)>maxSize)
                {
                     ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"The operation is not possible. The real size of the Record will be greater of the size defined record.","Warning",4);
                     return;
                }
            }
            catch (Exception ex)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Error Record Size.","Error",1);
                //System.out.println("Errore Record SIZE");
                return;
            }

            if( jTF_Field.getText().compareTo("") == 0)
            {
                if(jrb_add.isSelected())
                {
                    if(Last_row_TAB_CONFIG != null) // tab vuota no INPUT_DATA
                        jTF_Field.setText(""+(Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+1));
                    else
                        jTF_Field.setText("1");
                }
                else if(Selected_row_TAB_CONFIG == null)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"You have to select an item first.","INFO",3);
                    return;
                }
            }

            ADAMS_TAB_CONFIG row_TAB_CONFIG = new ADAMS_TAB_CONFIG();

            row_TAB_CONFIG.TIPO_DI_CONFIGURAZIONE = local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE;
            row_TAB_CONFIG.POSIZIONE_CAMPO_DR = new Integer(jTF_Field.getText()).intValue();

            /******* Assegno TIPO_OGGETTO_GUI  *****////
            String str_type = (String)jCB_Type.getSelectedItem();
            int ID_TYPE = VectorType.getID(str_type,VectorType.getValue(str_type));
            row_TAB_CONFIG.TIPO_CAMPO = ID_TYPE;
            //System.out.println("Type DR --> ID Help univoco = "+ID_TYPE);
            // ************* ///

            row_TAB_CONFIG.SIZE_CAMPO_DR      = new Integer(jTF_TypeSize.getText()).intValue();

            if(jCB_isArray.isSelected())
            {
                row_TAB_CONFIG.FLAG_ARRAY          = 'Y';
                row_TAB_CONFIG.NUMERO_ELEM_ARRAY   = new Integer(jTF_ArraySize.getText()).intValue();

                if(row_TAB_CONFIG.NUMERO_ELEM_ARRAY == 0)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Selected Input Data has 'Is Array' property set, in this case 'Array Size' must be greater than zero.", "WARNING",4);
                    return;
                }
            }
            else
            {
                row_TAB_CONFIG.FLAG_ARRAY          = 'N';
                row_TAB_CONFIG.NUMERO_ELEM_ARRAY   = 0;
            }

            String LABEL_DR = jTF_TagMess.getText().trim();
            if(LABEL_DR.compareTo("")==0)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Insert Tag field.", "WARNING",4);
                return;
            }
            else
                row_TAB_CONFIG.LABEL_DR_NORMALIZZ = LABEL_DR;


            if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
            {
                boolean isIndex = jCBox_Index.isSelected();

                if(isIndex)
                {
                    row_TAB_CONFIG.SE_INDICE = true;

                    String strAggV_def = jTF_AggrValue_DF.getText().trim();
                    if(strAggV_def.length() > 0)
                    {
                        int aggVdef = (new Integer(strAggV_def)).intValue();
                        if(aggVdef > 0)
                            row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF = aggVdef;
                        else
                        {
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione il campo 'Ag. Deferred Value' deve sessere maggiore di 0 (zero). ", "WARNING",4);
                            return;
                        }

                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione hai impostato Input Data come campo Indice, manca l'inserimento del campo 'Ag. Deferred Value'", "WARNING",4);
                        return;
                    }

                    String strAggV_rt = jTF_AggrValue_RT.getText().trim();
                    if(strAggV_rt.length() > 0)
                    {
                        int AggV_rt = (new Integer(strAggV_rt)).intValue();
                        if(AggV_rt >0)
                            row_TAB_CONFIG.VALORE_AGGREGAZIONE = AggV_rt;
                        else
                        {
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione il campo 'Ag. Actual Value' deve sessere maggiore di 0 (zero). ", "WARNING",4);
                            return;
                        }
                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione hai impostato Input Data come campo Indice, manca l'inserimento del campo 'Ag. Actual Value'", "WARNING",4);
                        return;
                    }

                    String strStartValue = jTF_startValue.getText().trim();
                    if(strStartValue.length() > 0)
                    {
                        int StartValue = (new Integer(strStartValue)).intValue();
                        if(StartValue >0)
                            row_TAB_CONFIG.LENGTH_VALORE_START = StartValue;
                        else
                        {
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione il campo 'Start Value len.' deve sessere maggiore di 0 (zero). ", "WARNING",4);
                            return;
                        }
                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione hai impostato Input Data come campo Indice, manca l'inserimento del campo 'Start Value len.'", "WARNING",4);
                        return;
                    }

                    String strIndexSuffix = jTF_indexSuffix.getText().trim();
                    if(strIndexSuffix.length() > 0)
                        row_TAB_CONFIG.SUFF_VALORE_INDICE = new String(strIndexSuffix);
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Attenzione hai impostato Input Data come campo Indice, manca l'inserimento del campo 'Index Suffix'", "WARNING",4);
                        return;
                    }


                    /*if(jTF_DD_Start.getText().length() > 0 )
                        row_TAB_CONFIG.DATA_INIZIO_INDICE = jTF_YY_Start.getText()+""+jTF_MM_Start.getText()+""+jTF_DD_Start.getText();



                    if(jTF_DD_End.getText().length() > 0 )
                        row_TAB_CONFIG.DATA_FINE_INDICE = jTF_YY_End.getText()+""+jTF_MM_End.getText()+""+jTF_DD_End.getText();*/


                }
                else
                {
                    row_TAB_CONFIG.SE_INDICE = false;
                    row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF = 0;
                    row_TAB_CONFIG.VALORE_AGGREGAZIONE = 0;
                    row_TAB_CONFIG.LENGTH_VALORE_START = 0;
                    row_TAB_CONFIG.SUFF_VALORE_INDICE = "";
                    row_TAB_CONFIG.DATA_INIZIO_INDICE ="";
                    row_TAB_CONFIG.DATA_FINE_INDICE="";
                }
            }

            boolean flagOrderDR = false;

            if(jrb_add.isSelected())
            {
                jTable_general.clearSelection();
                jTable_general.setSelectionBackground(java.awt.Color.yellow);
                if(Last_row_TAB_CONFIG != null)
                {
                    if(row_TAB_CONFIG.POSIZIONE_CAMPO_DR != (Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+1))
                    {
                        if(row_TAB_CONFIG.POSIZIONE_CAMPO_DR >(Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+1) )
                        {
                            row_TAB_CONFIG.POSIZIONE_CAMPO_DR = (Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+1);
                            jTF_Field.setText(""+row_TAB_CONFIG.POSIZIONE_CAMPO_DR);
                        }
                        else
                            flagOrderDR = true;
                    }
                }

                ///// ALLINIAMENTO
                int now_OffSet      = (new Integer(jTF_Offset.getText())).intValue();
                int now_TypeSyze    = (new Integer(jTF_TypeSize.getText())).intValue();

                if(flagOrderDR == false)
                {
                    if(now_TypeSyze > 1)
                    {
                        int ctrl_Alignment = (now_OffSet + now_TypeSyze)%local_INFO_CONFIG.BOUNDARY;
                        //System.out.println("ctrl_Alignment "+ctrl_Alignment);
                        if( ctrl_Alignment != 0)
                        {
                            ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Byte Boundary error. Proceed anyway?","Warning",6);
                            int Yes_No = op.getAnswer();
                            if(Yes_No != 1)
                            {
                                return;
                            }
                        }
                    }
                }
                else
                {
                    int now_RealSize = (new Integer(jTF_RealSize.getText())).intValue();
                    int now_nextOffSet = now_OffSet+now_RealSize;

                    ADAMS_TAB_CONFIG appo_ctrl_ROW;
                    for(int i=0; i<V_TAB_CONFIG.size(); i++)
                    {
                        appo_ctrl_ROW = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(i);
                        if((row_TAB_CONFIG.POSIZIONE_CAMPO_DR) == appo_ctrl_ROW.POSIZIONE_CAMPO_DR)
                        {

                            if(appo_ctrl_ROW.SIZE_CAMPO_DR >1)
                            {
                                int ctrl_Alignment = now_nextOffSet%local_INFO_CONFIG.BOUNDARY;
                               // System.out.println("ctrl_Alignment  "+ctrl_Alignment);
                                if( ctrl_Alignment != 0)
                                {
                                    ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"Byte Boundary error. Proceed anyway?","Warning",6);
                                    int Yes_No = op.getAnswer();
                                    if(Yes_No != 1)
                                    {
                                        return;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }


                int answer_ins = row_TAB_CONFIG.insert_InputData();

                if(answer_ins == 1)
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Input Data Field # "+row_TAB_CONFIG.POSIZIONE_CAMPO_DR+" has been added.","INFO",3);
                }
                else
                {
                    ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Cannot insert Input Data Field # "+row_TAB_CONFIG.POSIZIONE_CAMPO_DR+".","ERROR",1);
                    return;
                }

                if(flagOrderDR == true) //ordina i DR
                {
                    //System.out.println("**** OK **** ORDINAMENTO NECESSARIO INSERT ");
                    V_TAB_CONFIG.OrderDR_after_Insert(row_TAB_CONFIG.POSIZIONE_CAMPO_DR);
                }
                //else
                    //System.out.println("**** NO **** ORDINAMENTO INSERT ");

            }
            else if(jrb_modify.isSelected())
            {
                //jTable_general.setSelectionBackground(java.awt.Color.green.darker());

                ADAMS_JD_Message op;
                if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
                    op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change item "+Selected_row_TAB_CONFIG.TAG+"' Confirm?","Warning",6);
                else
                    op = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"About to change Input Data Field # "+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+"' Confirm?","Warning",6);

                int Yes_No = op.getAnswer();
                if(Yes_No == 1)
                {
                    if(row_TAB_CONFIG.POSIZIONE_CAMPO_DR != Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR)
                    {
                        if( row_TAB_CONFIG.POSIZIONE_CAMPO_DR > (Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR) )
                        {
                            ADAMS_JD_Message op1 = new ADAMS_JD_Message(ADAMS_GlobalParam.JFRAME_TAB,true,"WARNING: You are moving an item from position  "+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+" to position "+ row_TAB_CONFIG.POSIZIONE_CAMPO_DR+". Confirm?","Warning",6);
                            int Yes_No_1 = op1.getAnswer();
                            if(Yes_No_1 == 1)
                            {
                                row_TAB_CONFIG.POSIZIONE_CAMPO_DR = (Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR);
                                jTF_Field.setText(""+row_TAB_CONFIG.POSIZIONE_CAMPO_DR);
                            }
                            else
                                return;

                        }
                        flagOrderDR = true;
                    }
                    /////////////// Codice univoco del Input Data, ---> elemento esistente da modificare -- clausula where!
                    row_TAB_CONFIG.POSIZIONE_ELEMENTO = Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO;
                    int answer_up = row_TAB_CONFIG.update_InputData();
                    ///////////////
                    if(answer_up >= 0)
                    {
                        if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Item "+Selected_row_TAB_CONFIG.TAG+" has been changed.","INFO",3);
                        else
                            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Input Data Field N. "+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+" has been changed.","INFO",3);
                    }
                    else
                    {
                        ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_TAB,"Operation failed.","ERROR",1);
                        return;
                    }

                    if(local_INFO_CONFIG.CONF_DEFAULT != 'Y')
                    {
                        if(flagOrderDR == true) //ordina i DR
                        {
                            //System.out.println("**** OK **** ORDINAMENTO NECESSARIO MODIFY ");
                            V_TAB_CONFIG.OrderDR_after_Update(row_TAB_CONFIG);
                        }
                        //else
                            //System.out.println("**** NO **** ORDINAMENTO MODIFY ");
                    }
                }
                else
                    return;

            }

            if(!AddRowJTabel_general())
            {
                try
                {   //seleziona riga JTable
                    for(int i=0; i<jTable_general.getRowCount(); i++)
                    {
                        int CamDR_TABLE = new Integer(""+jTable_general.getValueAt(i,0)).intValue();
                        if( CamDR_TABLE == row_TAB_CONFIG.POSIZIONE_CAMPO_DR )
                        {
                            jTable_general.setRowSelectionInterval(i,i);
                            jScrollPane2.validate();
                            break;
                        }
                    }
                }
                catch(Exception e){}
            }

            //setSelected_jTableRow();
        }
    }//GEN-LAST:event_jBcommitActionPerformed

    private void Elab_RealSize(int typeSize)
    {
        int ArraySize = 0;
        int realSize = 1;
        try
        {
            realSize  = new Integer(jTF_TypeSize.getText()).intValue();
        }
        catch (NumberFormatException e){}

        try
        {
            ArraySize = new Integer(jTF_ArraySize.getText()).intValue();
        }
        catch (NumberFormatException e){}

        if(jCB_isArray.isSelected() == true)
        {
            jTF_ArraySize.setEnabled(true);
            jTF_ArraySize.setBackground(java.awt.Color.white);
            if(ArraySize > 0)
                realSize = (typeSize*ArraySize);
        }
        else
        {
            jTF_ArraySize.setEnabled(false);
            jTF_ArraySize.setBackground(colorDefault);
        }
        jTF_RealSize.setText(""+realSize);
    }

    private void jTF_ArraySizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_ArraySizeKeyReleased
        String str_type = (String)jCB_Type.getSelectedItem();
        int typeSize = VectorType.getValue(str_type);
        Elab_RealSize(typeSize);
    }//GEN-LAST:event_jTF_ArraySizeKeyReleased

    private void jCB_isArrayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_isArrayItemStateChanged
        String str_type = (String)jCB_Type.getSelectedItem();
        int typeSize = VectorType.getValue(str_type);
        Elab_RealSize(typeSize);
    }//GEN-LAST:event_jCB_isArrayItemStateChanged

    private void jCB_TypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_TypeItemStateChanged
        String str_type = (String)jCB_Type.getSelectedItem();
        int typeSize = VectorType.getValue(str_type);

        jTF_TypeSize.setText(""+typeSize);

        Elab_RealSize(typeSize);
    }//GEN-LAST:event_jCB_TypeItemStateChanged

    public ADAMS_TAB_INFO_CONFIG get_INFO_CONFIG()
    {
        return local_INFO_CONFIG;
    }

    public ADAMS_Vector_TAB_CONFIG getVector_TAB_CONFIG()
    {
        return V_TAB_CONFIG;
    }
    private void jTF_TagMessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_TagMessFocusLost
        jL8.setForeground(java.awt.Color.black);
        jL8.setBackground(colorDefault);

        jL_helpText.setText("");
    }//GEN-LAST:event_jTF_TagMessFocusLost

    private void jTF_TagMessFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_TagMessFocusGained
        jL8.setForeground(java.awt.Color.white);
        jL8.setBackground(java.awt.Color.darkGray);

        jL_helpText.setText("Assign a name to field. Better result are achived if you use names matching the real record's fields.");
    }//GEN-LAST:event_jTF_TagMessFocusGained

    private void jTF_ArraySizeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_ArraySizeFocusLost
        jL6.setForeground(java.awt.Color.black);
        jL6.setBackground(colorDefault);

        jL_helpText.setText("");
    }//GEN-LAST:event_jTF_ArraySizeFocusLost

    private void jTF_ArraySizeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_ArraySizeFocusGained
        jL6.setForeground(java.awt.Color.white);
        jL6.setBackground(java.awt.Color.darkGray);

        jL_helpText.setText("Here you set the size of the array in number of elements (not bytes).");
    }//GEN-LAST:event_jTF_ArraySizeFocusGained

    private void jCB_isArrayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCB_isArrayFocusLost
        jL5.setForeground(java.awt.Color.black);
        jL5.setBackground(colorDefault);

        jL_helpText.setText("");
    }//GEN-LAST:event_jCB_isArrayFocusLost

    private void jCB_isArrayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCB_isArrayFocusGained
        jL5.setForeground(java.awt.Color.white);
        jL5.setBackground(java.awt.Color.darkGray);

        jL_helpText.setText("This field will be treated as an array if you activate the checkbox.");
    }//GEN-LAST:event_jCB_isArrayFocusGained

    private void jTF_TypeSizeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_TypeSizeFocusLost
        jL4.setForeground(java.awt.Color.black);
        jL4.setBackground(colorDefault);

        jL_helpText.setText("");
    }//GEN-LAST:event_jTF_TypeSizeFocusLost

    private void jTF_TypeSizeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTF_TypeSizeFocusGained
        jL4.setForeground(java.awt.Color.white);
        jL4.setBackground(java.awt.Color.darkGray);

        jL_helpText.setText("This is the size of field type you've choosed expressed in bytes.");
    }//GEN-LAST:event_jTF_TypeSizeFocusGained

    private void jCB_TypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCB_TypeFocusLost
        jL2.setForeground(java.awt.Color.black);
        jL2.setBackground(colorDefault);

        jL_helpText.setText("");
    }//GEN-LAST:event_jCB_TypeFocusLost

    private void jCB_TypeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCB_TypeFocusGained
        jL2.setForeground(java.awt.Color.white);
        jL2.setBackground(java.awt.Color.darkGray);

        jL_helpText.setText("Select here the data type of the field.");
    }//GEN-LAST:event_jCB_TypeFocusGained

    private void jrb_deleteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_deleteItemStateChanged
        jTable_general.setEnabled(true);
        jTable_general.setSelectionBackground(java.awt.Color.red);
        jTF_Field.setEnabled(false);
        jTF_Field.setBackground(colorDefault);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_deleteItemStateChanged

    private void jrb_modifyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_modifyItemStateChanged
        jTable_general.setEnabled(true);
        jTable_general.setSelectionBackground(java.awt.Color.green.darker());

        if(jTable_general.getSelectedRow() != -1)
            jTF_Field.setEnabled(true);
        else
            jTF_Field.setEnabled(false);

        jTF_Field.setBackground(java.awt.Color.white);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_modifyItemStateChanged

    private void jrb_addItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_addItemStateChanged
        //System.out.println("jrb_addItemStateChanged");

        if(CLEARSELECTION)
        {
            jTable_general.setEnabled(false);
            jTable_general.setSelectionBackground(java.awt.Color.white);
            jTable_general.clearSelection();
        }
        else
        {
            CLEARSELECTION = true;
        }

        jTF_Field.setEnabled(true);
        jTF_Field.setBackground(java.awt.Color.white);
        setSelected_jTableRow();
    }//GEN-LAST:event_jrb_addItemStateChanged

    private void setSelected_jTableRow()
    {
        int selectedRow = jTable_general.getSelectedRow();
        //System.out.println("******** selectedRow "+selectedRow);

        String str_field        = "";
        String str_Type         = "";
        String str_offSet       = "";
        String str_typeSize     = "";
        Boolean isArray         = new Boolean(false);
        String str_ArraySize    = "0";
        String str_realSize     = "";
        String str_TagMess      = "";

        if( (jrb_modify.isSelected()) || (jrb_delete.isSelected()) )
        {
            if(selectedRow < 0)
            {
                jTF_Field.setText("");
                jTF_Offset.setText("");
                jTF_TagMess.setText("");

                Selected_row_TAB_CONFIG = null;
            }
            else
            {
                str_field       = new String(jTable_general.getValueAt(selectedRow,0)+"");
                str_Type        = new String(jTable_general.getValueAt(selectedRow,1)+"");
                str_offSet      = new String(jTable_general.getValueAt(selectedRow,2)+"");
                str_typeSize    = new String(jTable_general.getValueAt(selectedRow,3)+"");
                isArray         = new Boolean(jTable_general.getValueAt(selectedRow,4).toString());
                str_ArraySize   = new String(jTable_general.getValueAt(selectedRow,5)+"");
                str_realSize    = new String(jTable_general.getValueAt(selectedRow,6)+"");
                str_TagMess     = new String(jTable_general.getValueAt(selectedRow,7)+"");

                Selected_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)V_TAB_CONFIG.elementAt(selectedRow);

                /*System.out.println(" TAG --> "+Selected_row_TAB_CONFIG.TAG);
                System.out.println(" POSIZIONE_CAMPO_DR --> "+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR);
                System.out.println(" POSIZIONE_ELEMENTO --> "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO);*/

                if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
                {
                    // Per Configurazioni Indice"
                    jCBox_Index.setSelected(Selected_row_TAB_CONFIG.SE_INDICE);


                    jTF_AggrValue_DF.setText(""+Selected_row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF);
                    jTF_AggrValue_RT.setText(""+Selected_row_TAB_CONFIG.VALORE_AGGREGAZIONE);
                    jTF_startValue.setText(""+Selected_row_TAB_CONFIG.LENGTH_VALORE_START);
                    jTF_indexSuffix.setText(Selected_row_TAB_CONFIG.SUFF_VALORE_INDICE);


                    /*if(Selected_row_TAB_CONFIG.DATA_INIZIO_INDICE.length() > 0)
                    {
                        //System.out.println(" DATA_INIZIO_INDICE "+Selected_row_TAB_CONFIG.DATA_INIZIO_INDICE);

                        jTF_DD_Start.setText(Selected_row_TAB_CONFIG.DATA_INIZIO_INDICE.substring(6,8));
                        jTF_MM_Start.setText(Selected_row_TAB_CONFIG.DATA_INIZIO_INDICE.substring(4,6));
                        jTF_YY_Start.setText(Selected_row_TAB_CONFIG.DATA_INIZIO_INDICE.substring(0,4));
                    }
                    else
                    {
                        jTF_DD_Start.setText("");
                        jTF_MM_Start.setText("");
                        jTF_YY_Start.setText("");
                    }

                    if(Selected_row_TAB_CONFIG.DATA_FINE_INDICE.length() > 0)
                    {
                        //System.out.println(" DATA_FINE_INDICE "+Selected_row_TAB_CONFIG.DATA_FINE_INDICE);

                        jTF_DD_End.setText(Selected_row_TAB_CONFIG.DATA_FINE_INDICE.substring(6,8));
                        jTF_MM_End.setText(Selected_row_TAB_CONFIG.DATA_FINE_INDICE.substring(4,6));
                        jTF_YY_End.setText(Selected_row_TAB_CONFIG.DATA_FINE_INDICE.substring(0,4));
                    }
                    else
                    {
                        jTF_DD_End.setText("");
                        jTF_MM_End.setText("");
                        jTF_YY_End.setText("");

                    }*/

                    setEnabledGUI_index(jCBox_Index.isSelected(),false);
                }

                // System.out.println("Selezionato Field ="+Selected_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+"con ID-> "+Selected_row_TAB_CONFIG.POSIZIONE_ELEMENTO);
            }
        }
        else //ADD
        {
            try
            {
                jCB_Type.setSelectedIndex(0);
            }catch(Exception e)
            {
                System.out.println("jCB_Type.setSelectedIndex(0)");
            }

            str_Type = (String)jCB_Type.getSelectedItem();

            Selected_row_TAB_CONFIG = null;

            if(Last_row_TAB_CONFIG != null) // tab vuota no INPUT_DATA
                str_field =  new String( ""+ (Last_row_TAB_CONFIG.POSIZIONE_CAMPO_DR+1) );
            else
                str_field = "1";

            int type_Size = VectorType.getValue(str_Type);

            str_typeSize = ""+type_Size;

            str_offSet =(""+Last_offset);
            str_realSize =""+type_Size;

        }

        jTF_Field.setText(str_field);
        jCB_Type.setSelectedItem(str_Type);

        jTF_Offset.setText(str_offSet);
        jTF_TypeSize.setText(str_typeSize);
        jCB_isArray.setSelected(isArray.booleanValue());
        if(isArray.booleanValue() == true)
        {
            jTF_ArraySize.setEnabled(true);
            jTF_ArraySize.setBackground(java.awt.Color.white);
        }
        else
        {
            jTF_ArraySize.setBackground(colorDefault);
            jTF_ArraySize.setEnabled(false);
        }

        jTF_ArraySize.setText(str_ArraySize);
        jTF_RealSize.setText(str_realSize);
        jTF_TagMess.setText(str_TagMess);

        boolean flagEnabledGui =  !jrb_delete.isSelected();

        if(local_INFO_CONFIG.CONF_DEFAULT == 'Y')
        {
            flagEnabledGui = false;
            jTF_Field.setEditable(false);
        }

        jCB_Type.setEnabled(flagEnabledGui);
        jCB_isArray.setEnabled(flagEnabledGui);
        jTF_ArraySize.setEditable(flagEnabledGui);
        jTF_TagMess.setEditable(flagEnabledGui);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGroup_oper;
    private javax.swing.JButton jBcancel;
    private javax.swing.JButton jBcommit;
    private javax.swing.JComboBox jCB_Type;
    private javax.swing.JCheckBox jCB_isArray;
    private javax.swing.JCheckBox jCBox_Index;
    private javax.swing.JLabel jL1;
    private javax.swing.JLabel jL2;
    private javax.swing.JLabel jL3;
    private javax.swing.JLabel jL4;
    private javax.swing.JLabel jL5;
    private javax.swing.JLabel jL6;
    private javax.swing.JLabel jL7;
    private javax.swing.JLabel jL8;
    private javax.swing.JTextArea jL_helpText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jP_butt1;
    private javax.swing.JPanel jP_indexProperties;
    private javax.swing.JPanel jP_operation;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTF_AggrValue_DF;
    private javax.swing.JTextField jTF_AggrValue_RT;
    private javax.swing.JTextField jTF_ArraySize;
    private javax.swing.JTextField jTF_Field;
    private javax.swing.JTextField jTF_Offset;
    private javax.swing.JTextField jTF_RealSize;
    private javax.swing.JTextField jTF_TagMess;
    private javax.swing.JTextField jTF_TypeSize;
    private javax.swing.JTextField jTF_asize;
    private javax.swing.JTextField jTF_indexSuffix;
    private javax.swing.JTextField jTF_startValue;
    private javax.swing.JTextField jT_fsize;
    private javax.swing.JRadioButton jrb_add;
    private javax.swing.JRadioButton jrb_delete;
    private javax.swing.JRadioButton jrb_modify;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JTable jTable_general;
    private java.awt.Color colorDefault = new java.awt.Color(230,230,230);
    private javax.swing.table.DefaultTableModel defaultTableModel_general;

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);

    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG  = null;

    private ADAMS_Vector_TAB_CONFIG V_TAB_CONFIG = null;
    private ADAMS_TAB_CONFIG Last_row_TAB_CONFIG;
    private ADAMS_TAB_CONFIG Selected_row_TAB_CONFIG;
    private int Last_offset = 0;

    private int[] CellDimension = {30,90,25,30,30,30,30,135,30};
    private int minCellDimension = 30;

    private String[] ALL_TYPE = {"Char","Unsigned Char","Short","Unsigned Short","Int","Unsigned Int","Long","Unsigned Long","Float","Double","Binary Coded Decimal","C-Type Char String","Byte String"};
    private int[] ALL_TYPE_VALUE = {1,1,2,2,4,4,8,8,4,8,1,1,1};

    private VectorHelp VectorType;
    boolean CLEARSELECTION = true;

  /*  private ADAMS_Calendar cal_start = null;
    private ADAMS_Calendar cal_end   = null;*/
    private javax.swing.JFrame parentJFrame = null;

 }

 /*jLabel4;
 jCBox_Index;
 jLabel5;
 jTF_AggrValue;
 jLabel6;
 jTF_startValue;
 jLabel7;
 jTF_indexSuffix;
 jLabel8;
 jTF_DD_Start;
 jTF_MM_Start;
 jTF_YY_Start;
 jLabel9;
 jTF_DD_End;
 jTF_MM_End;
 jTF_YY_End;*/