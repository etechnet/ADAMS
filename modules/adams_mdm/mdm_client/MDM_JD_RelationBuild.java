/*
 * MDM_JD_RelationBuild.java
 *
 * Created on 15 settembre 2010, 16.47
 */

/**
 *
 * @author  luca
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class MDM_JD_RelationBuild extends javax.swing.JDialog {

    /** Creates new form MDM_JD_RelationBuild */
    public MDM_JD_RelationBuild(java.awt.Frame parent,getConfigFiltro Configuration) {
        super(parent, true);
        
        this.configuration = Configuration;        
        localTrafficElement = new TrafficElement(configuration.get_DataElements_FFENABLED());
        
        initComponents();
        
        str_help_add = ("Select an Element to add to your relation from the Traffic Element list. "+
                        "Click 'Add Element' button to concatenate it or press 'Delete Last' to revert operations. "+
                        "Please note that a relation is made by at least two (2) Traffic Elements. ");
        
        jTA_help.setFont(staticLib.fontA11);
        jTA_help.setText(str_help_add);
        
        //jL_title.setText("Free Format Relation - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        // *** Costruzione JTABLE TE ***/
       
        //String[] all_columnNames ={"Id","Tag","Desc"};
        //int [] cellDimen_TE ={30,100,200};
        String[] all_columnNames ={"ID","DESCRIPTION"};
        int [] cellDimen_TE ={30,200};
        
        myTableModel_TE = new MyTableModel(all_columnNames);
        sorter_TE = new TableSorter(myTableModel_TE);
    
        jTable_TE = new javax.swing.JTable(sorter_TE);
        JTableHeader header = jTable_TE.getTableHeader();
        header.setFont(staticLib.fontA11);
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
        
        /*jTable_TE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TEMouseReleased(evt);
            }
        });*/
        
        jB_cancel.setCursor(Cur_hand);
        jB_ok.setCursor(Cur_hand);
        jB_addElem.setCursor(Cur_hand);
        jB_remLast.setCursor(Cur_hand);
        jTable_TE.setCursor(Cur_hand);        
        
        SetTable(jTable_TE,30,cellDimen_TE);
        AddRowJTabel_TE();      
    }

    public void open_RelationBuild()
    {        
        CONFIRM_FFENABLED = false;
        setCenteredFrame(600,600);
        setVisible(true);
        toFront();
    }
    
    private void SetTable(javax.swing.JTable jtable, int minCellDimension, int[] CellDimension)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jtable.getTableHeader();
        header.setFont(staticLib.fontA11);
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
    
    private void AddRowJTabel_TE()
    {
        jTable_TE.setCursor(Cur_wait);        
      
        int SIZE = localTrafficElement.localElement.length;
        Object[][] dataValues = new Object[SIZE][2];
        for(int i=0; i<SIZE; i++)
        {
            DATA_DATAELEMENT data_TE = localTrafficElement.localElement[i];
            
            /*dataValues[i][0] = new Integer(data_TE.idElement);
            dataValues[i][1] = new String(data_TE.shortDescription).trim();
            dataValues[i][2] = new String(data_TE.longDescription).trim();*/
            
            dataValues[i][0] = new Integer(data_TE.idElement);
            dataValues[i][1] = new String(data_TE.longDescription).trim();
        }
        
        myTableModel_TE.setDataValues(dataValues);
        sorter_TE.setTableModel(myTableModel_TE);
        sorter_TE.setSortingStatus(1,1);
        jScrollPane_TE.setViewportView(jTable_TE);
                
        jTable_TE.setCursor(Cur_default);
    }
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_TOT = new javax.swing.JPanel();
        jL_title = new javax.swing.JLabel();
        jP_center = new javax.swing.JPanel();
        jP_c3 = new javax.swing.JPanel();
        jTA_help = new javax.swing.JTextArea();
        jScrollPane_TE = new javax.swing.JScrollPane();
        jP_button_build = new javax.swing.JPanel();
        jB_addElem = new javax.swing.JButton();
        jB_remLast = new javax.swing.JButton();
        jPanel_south = new javax.swing.JPanel();
        jP_sc = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jL_relation = new javax.swing.JLabel();
        jL_RelationSize_MaxSize = new javax.swing.JLabel();
        jP_ss = new javax.swing.JPanel();
        jP_sn = new javax.swing.JPanel();
        jB_ok = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jP_TOT.setLayout(new java.awt.BorderLayout());
        
        jP_TOT.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setText("Free Format Relation");
        jL_title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(150, 40));
        jP_TOT.add(jL_title, java.awt.BorderLayout.NORTH);
        
        jP_center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setBorder(new javax.swing.border.TitledBorder(null, " Relation Build ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10), java.awt.Color.blue));
        jP_c3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_c3.setBackground(new java.awt.Color(230, 230, 230));
        jP_c3.setBorder(new javax.swing.border.TitledBorder(null, " Help information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jP_c3.setMinimumSize(new java.awt.Dimension(200, 200));
        jP_c3.setPreferredSize(new java.awt.Dimension(200, 232));
        jTA_help.setBackground(new java.awt.Color(230, 230, 230));
        jTA_help.setEditable(false);
        jTA_help.setLineWrap(true);
        jTA_help.setWrapStyleWord(true);
        jTA_help.setPreferredSize(new java.awt.Dimension(100, 38));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_c3.add(jTA_help, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jP_center.add(jP_c3, gridBagConstraints1);
        
        jScrollPane_TE.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_TE.setBorder(new javax.swing.border.TitledBorder(null, " Traffic Elements ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jScrollPane_TE.setAutoscrolls(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_center.add(jScrollPane_TE, gridBagConstraints1);
        
        jP_button_build.setBackground(new java.awt.Color(230, 230, 230));
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
        
        jP_button_build.add(jB_addElem);
        
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
        
        jP_button_build.add(jB_remLast);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jP_center.add(jP_button_build, gridBagConstraints1);
        
        jP_TOT.add(jP_center, java.awt.BorderLayout.CENTER);
        
        jPanel_south.setLayout(new java.awt.BorderLayout());
        
        jPanel_south.setBackground(new java.awt.Color(230, 230, 230));
        jPanel_south.setBorder(new javax.swing.border.TitledBorder(null, " Free Format Relation Build", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jPanel_south.setPreferredSize(new java.awt.Dimension(10, 165));
        jP_sc.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_sc.setBackground(new java.awt.Color(230, 230, 230));
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
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jLabel2, gridBagConstraints3);
        
        jL_relation.setBackground(java.awt.Color.white);
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
        jP_sn.setBackground(new java.awt.Color(230, 230, 230));
        jP_sn.setMinimumSize(new java.awt.Dimension(0, 30));
        jP_sn.setPreferredSize(new java.awt.Dimension(0, 30));
        jB_ok.setBackground(new java.awt.Color(230, 230, 230));
        jB_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok.jpg")));
        jB_ok.setBorderPainted(false);
        jB_ok.setContentAreaFilled(false);
        jB_ok.setFocusPainted(false);
        jB_ok.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_ok.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_ok.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_ok.setPreferredSize(new java.awt.Dimension(80, 30));
        jB_ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_press.jpg")));
        jB_ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_over.jpg")));
        jB_ok.setEnabled(false);
        jB_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_okActionPerformed(evt);
            }
        });
        
        jP_sn.add(jB_ok);
        
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_cancel.setToolTipText("Cancel");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        
        jP_sn.add(jB_cancel);
        
        jP_ss.add(jP_sn, java.awt.BorderLayout.CENTER);
        
        jPanel_south.add(jP_ss, java.awt.BorderLayout.SOUTH);
        
        jP_TOT.add(jPanel_south, java.awt.BorderLayout.SOUTH);
        
        getContentPane().add(jP_TOT, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private int isPresent_ID(int ID, Vector vector_ID)
    {
        for(int i=0; i<vector_ID.size(); i++)
        {
            if(  (((Integer)vector_ID.elementAt(i)).intValue()) == ID  )
                return i;
        }
        return -1;
    }
      
    private void jB_addElemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_addElemActionPerformed
        
        int selectedRow_build = jTable_TE.getSelectedRow();              
        if( selectedRow_build >=0 )
        {
            int relationElements = V_FREE_RELATION_ID.size();            
            if(relationElements >= staticLib.MAX_DIMENSION)
            {
                staticLib.optionPanel("A maximum of "+staticLib.MAX_DIMENSION+" Traffic Element per Relation.","WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);                           
                return;
            }
            
            int ID_TE_selected = ((Integer)(jTable_TE.getValueAt(selectedRow_build,0))).intValue();
            String Desc_TE_selected = ""+jTable_TE.getValueAt(selectedRow_build,1);
           
            int index_TE_Present = isPresent_ID(ID_TE_selected,V_FREE_RELATION_ID);
            if(index_TE_Present == -1) //ID_TE non presento e aggiungo.
            {
                //System.out.println("ADD -->"+Desc_TE_selected );
                V_FREE_RELATION_ID.addElement(new Integer(ID_TE_selected));
                V_FREE_RELATION_NAME.addElement(new String(Desc_TE_selected).trim());
            }
            else // ID_TE
            {
                //System.out.println("NO ADD - is present -->"+Desc_TE_selected );
                staticLib.optionPanel("Your Relation already contains: "+ID_TE_selected+" "+Desc_TE_selected+".", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);                           
                return;
            }
                        
            int num_TE = V_FREE_RELATION_ID.size();
            
            //System.out.println("V_FREE_RELATION_ID.size() ==> "+num_TE );
            String symbol_rel = "::";
            String str_relName = "";
            for(int i=0; i<num_TE; i++)
            {
                if(i == (num_TE-1))
                    symbol_rel = "";
                
                 str_relName = str_relName+((new String( ((String)V_FREE_RELATION_NAME.elementAt(i)))).trim())+symbol_rel;
                 //System.out.println("buil Rel -->"+str_relName );
                
            }
            //System.out.println("Relazione  -->"+str_relName );
            jL_relation.setText(str_relName);
            jL_relation.setToolTipText(jL_relation.getText());
        }
        else
            staticLib.optionPanel("Select an item from the list < Traffic Elements >.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);  
        
        ctrl_b_ok();
    }//GEN-LAST:event_jB_addElemActionPerformed

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
         close_Dialog();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void close_Dialog()
    {
         setVisible(false);
         CONFIRM_FFENABLED = false;
        //dispose();
    }
    
    private void jB_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_okActionPerformed
        
        //System.out.println("-- OK --");      
        setVisible(false);
        
        CONFIRM_FFENABLED = true;
    }//GEN-LAST:event_jB_okActionPerformed

    public boolean get_ConfirmFreeReleation()
    {
        return CONFIRM_FFENABLED;
    }
    
    public Vector get_V_RELATION_NAME()
    {
        return V_FREE_RELATION_NAME;
    }
    
    public Vector get_V_FREE_RELATION_ID()
    {
        return V_FREE_RELATION_ID;
    }
    
    private void ctrl_b_ok()
    {
        int num_TE = V_FREE_RELATION_ID.size();
        
        if(num_TE >= 2)
            jB_ok.setEnabled(true);
        else
            jB_ok.setEnabled(false);
    }
    
    
    private void jB_remLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_remLastActionPerformed
        
        //System.out.println(" remove Last TE ");
        
        int num_TE = V_FREE_RELATION_ID.size();
        if(num_TE > 0)
        {
            V_FREE_RELATION_ID.removeElementAt(num_TE-1);
            V_FREE_RELATION_NAME.removeElementAt(num_TE-1);

            num_TE = V_FREE_RELATION_ID.size();

            String symbol_rel = "::";
            String str_relName = "";
            for(int i=0; i<num_TE; i++)
            {
                if(i == (num_TE-1))
                    symbol_rel = "";

                str_relName = str_relName+((new String( ((String)V_FREE_RELATION_NAME.elementAt(i)))).trim())+symbol_rel;
                //System.out.println("buil Rel -->"+str_relName );

            }
            //System.out.println("Relazione  -->"+str_relName );
            jL_relation.setText(str_relName);
            jL_relation.setToolTipText(jL_relation.getText());
        }
        ctrl_b_ok();
    }//GEN-LAST:event_jB_remLastActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
       close_Dialog();
    }//GEN-LAST:event_closeDialog

    //private void jTable_TEMouseReleased(java.awt.event.MouseEvent evt) {}

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_TOT;
    private javax.swing.JLabel jL_title;
    private javax.swing.JPanel jP_center;
    private javax.swing.JPanel jP_c3;
    private javax.swing.JTextArea jTA_help;
    private javax.swing.JScrollPane jScrollPane_TE;
    private javax.swing.JPanel jP_button_build;
    private javax.swing.JButton jB_addElem;
    private javax.swing.JButton jB_remLast;
    private javax.swing.JPanel jPanel_south;
    private javax.swing.JPanel jP_sc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jL_relation;
    private javax.swing.JLabel jL_RelationSize_MaxSize;
    private javax.swing.JPanel jP_ss;
    private javax.swing.JPanel jP_sn;
    private javax.swing.JButton jB_ok;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    private String str_help_add = null;
    
    private getConfigFiltro     configuration;
    private TrafficElement      localTrafficElement;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_TE;
    private TableSorter sorter_TE;
    private MyTableModel myTableModel_TE;
    
    private Vector V_FREE_RELATION_NAME = new Vector(staticLib.MAX_DIMENSION);
    private Vector V_FREE_RELATION_ID   = new Vector(staticLib.MAX_DIMENSION);
    
    private boolean CONFIRM_FFENABLED = false;
}


