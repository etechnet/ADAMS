/*
 * JListChooser.java
 *
 * Created on 19 luglio 2005, 16.28
 */

/**
 *
 * @author  pbetti
 */
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
public class JListChooser extends javax.swing.JDialog {

    /** Creates new form JListChooser */
    public JListChooser(java.awt.Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initComponents();
       
        // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);
        
        jTable_Available = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_Available.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);        
        
        jTable_Available.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Available.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Available.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_Available.setRowHeight(20);
        //jTable_Available.setSelectionBackground(java.awt.Color.green);
        //jTable_Available.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_Available.setRowMargin(2);
        jTable_Available.setAutoCreateColumnsFromModel(false);
        
        jScrollPane1.setViewportView(jTable_Available);
        jTable_Available.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseReleased(java.awt.event.MouseEvent evt) {
                  jTable_AvailableMouseReleased(evt);
              }
         });

        
        myTableModel2 = new MyTableModel();
        sorter2 = new TableSorter(myTableModel2);
        jTable_Selected = new javax.swing.JTable(sorter2);
        JTableHeader header2 = jTable_Selected.getTableHeader();
        header2.setFont(ADAMS_GlobalParam.font_B10);
        header2.setReorderingAllowed(false);

        sorter2.setTableHeader(header2);        
        
        jTable_Selected.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Selected.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Selected.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_Selected.setRowHeight(20);
        //jTable_Selected.setSelectionBackground(java.awt.Color.green);
        //jTable_Selected.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_Selected.setRowMargin(2);
        jTable_Selected.setAutoCreateColumnsFromModel(false);
        
        jScrollPane2.setViewportView(jTable_Selected);
        jTable_Selected.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseReleased(java.awt.event.MouseEvent evt) {
                  jTable_SelectedMouseReleased(evt);
              }
         });
        // **************************** //
   
        SetTable(jTable_Available);
        SetTable(jTable_Selected);

        //setSize(700,500);
        setCenteredFrame(700,500);

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
        jtable.repaint();
        
        this.setCursor(Cur_default);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel_Available = new javax.swing.JLabel();
        jLabel_TitleIcon = new javax.swing.JLabel();
        jLabel_Selected = new javax.swing.JLabel();
        jPanel_ExitButtons = new javax.swing.JPanel();
        jButton_Ok = new javax.swing.JButton();
        jButton_Cancel = new javax.swing.JButton();
        jPanel_LeftButtons = new javax.swing.JPanel();
        jButton_Add = new javax.swing.JButton();
        jButton_AddAll = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_RemoveAll = new javax.swing.JButton();
        jPanel_RightButtons = new javax.swing.JPanel();
        jButton_MoveUp = new javax.swing.JButton();
        jButton_MoveDown = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jLabel_Available.setText("Available Items");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 5, 3, 0);
        getContentPane().add(jLabel_Available, gridBagConstraints1);
        
        jLabel_TitleIcon.setText("Item Selector");
        jLabel_TitleIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TitleIcon.setPreferredSize(new java.awt.Dimension(22, 22));
        jLabel_TitleIcon.setMinimumSize(new java.awt.Dimension(1, 1));
        jLabel_TitleIcon.setMaximumSize(new java.awt.Dimension(472, 120));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = 4;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(1, 5, 1, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.1;
        getContentPane().add(jLabel_TitleIcon, gridBagConstraints1);
        
        jLabel_Selected.setText("Selected Items");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel_Selected, gridBagConstraints1);
        
        jPanel_ExitButtons.setLayout(new java.awt.GridLayout(1, 0));
        
        jButton_Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok.jpg")));
        jButton_Ok.setToolTipText("");
        jButton_Ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_over.jpg")));
        jButton_Ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_press.jpg")));
        jButton_Ok.setFocusPainted(false);
        jButton_Ok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton_Ok.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_Ok.setContentAreaFilled(false);
        jButton_Ok.setBorderPainted(false);
        jButton_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OkActionPerformed(evt);
            }
        });
        
        jPanel_ExitButtons.add(jButton_Ok);
        
        jButton_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg")));
        jButton_Cancel.setToolTipText("");
        jButton_Cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg")));
        jButton_Cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg")));
        jButton_Cancel.setFocusPainted(false);
        jButton_Cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton_Cancel.setContentAreaFilled(false);
        jButton_Cancel.setBorderPainted(false);
        jButton_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelActionPerformed(evt);
            }
        });
        
        jPanel_ExitButtons.add(jButton_Cancel);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.insets = new java.awt.Insets(6, 0, 6, 3);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jPanel_ExitButtons, gridBagConstraints1);
        
        jPanel_LeftButtons.setLayout(new java.awt.GridLayout(4, 0));
        
        jButton_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forward.png")));
        jButton_Add.setToolTipText("Add an item to selection");
        jButton_Add.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });
        
        jPanel_LeftButtons.add(jButton_Add);
        
        jButton_AddAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finish.png")));
        jButton_AddAll.setToolTipText("Add all items to selection");
        jButton_AddAll.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_AddAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddAllActionPerformed(evt);
            }
        });
        
        jPanel_LeftButtons.add(jButton_AddAll);
        
        jButton_Remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png")));
        jButton_Remove.setToolTipText("Remove an item from selection");
        jButton_Remove.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveActionPerformed(evt);
            }
        });
        
        jPanel_LeftButtons.add(jButton_Remove);
        
        jButton_RemoveAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start.png")));
        jButton_RemoveAll.setToolTipText("Remove all items from selection");
        jButton_RemoveAll.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_RemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveAllActionPerformed(evt);
            }
        });
        
        jPanel_LeftButtons.add(jButton_RemoveAll);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(jPanel_LeftButtons, gridBagConstraints1);
        
        jPanel_RightButtons.setLayout(new java.awt.GridLayout(2, 0));
        
        jButton_MoveUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up.png")));
        jButton_MoveUp.setToolTipText("Move up selected item");
        jButton_MoveUp.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_MoveUp.setEnabled(false);
        jButton_MoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MoveUpActionPerformed(evt);
            }
        });
        
        jPanel_RightButtons.add(jButton_MoveUp);
        
        jButton_MoveDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down.png")));
        jButton_MoveDown.setToolTipText("Move down selected item");
        jButton_MoveDown.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_MoveDown.setEnabled(false);
        jButton_MoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MoveDownActionPerformed(evt);
            }
        });
        
        jPanel_RightButtons.add(jButton_MoveDown);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets(0, 5, 0, 5);
        getContentPane().add(jPanel_RightButtons, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(0, 5, 0, 0);
        gridBagConstraints1.weightx = 0.5;
        gridBagConstraints1.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 0.5;
        getContentPane().add(jScrollPane2, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    public void setListChooserData(Object[][] leftData, Object[][] rightData)
    {
        dataSize = leftData.length;     // arrays cannot exceeds this size!!
        
        availableData = leftData;
        myAvailableData = new Object[dataSize][2];
        selectedData = new Object[dataSize][2];
        restoreSelectedData = new Object[dataSize][2];
        
        for (int i = 0; i < rightData.length; i++) {
            selectedData[i][0] = rightData[i][0];
            selectedData[i][1] = rightData[i][1];
            restoreSelectedData[i][0] = rightData[i][0];
            restoreSelectedData[i][1] = rightData[i][1];
        }
        
        syncTables();
    }
    
    public Object[][] getListChooserData()
    {
        int length = rowCount(selectedData);
        Object[][] retData = new Object[length][2];
        
        for (int i = 0; i < length; i++) {
            retData[i][0] = selectedData[i][0];
            retData[i][1] = selectedData[i][1];
        }
        
        return retData;
    }
    
    private void syncTables()
    {
        syncAvailableData();
        
        updateTableAvailable(myAvailableData);
        updateTableSelected(selectedData);
    }
    
    private void syncAvailableData()
    {
        int myDataCount = 0;
        
        boolean toCopy;
        for (int i = 0; i < dataSize; i++) {
            toCopy = true;
            
            for (int j = 0; j < selectedData.length; j++) {
                if (selectedData[j][0] == null) {
                    break;
                }
                if (((Integer)(availableData[i][0])).intValue() == ((Integer)(selectedData[j][0])).intValue()) {
                    toCopy = false;
                    break;
                }
            }
            
            if (!toCopy)
                continue;
            myAvailableData[myDataCount][0] = availableData[i][0];
            myAvailableData[myDataCount++][1] = availableData[i][1];
        }
        
        for (int i = myDataCount; i < dataSize; i++) {
            myAvailableData[i][0] = null;
            myAvailableData[i][1] = null;
        }
    }

    private void updateTableAvailable(Object[][] tdata)
    {
        jTable_Available.setCursor(Cur_wait);

        myTableModel.setDataValues(tdata);
        sorter.setTableModel(myTableModel);
        
        if (jTable_Available.isShowing())
        {
            //System.out.println("3 ctrl updateUI");
            jTable_Available.updateUI();
        }
        jTable_Available.setCursor(Cur_default);
    }
    
    private void updateTableSelected(Object[][] tdata)
    {
        jTable_Selected.setCursor(Cur_wait);

        myTableModel2.setDataValues(tdata);
        sorter2.setTableModel(myTableModel2);
        
        if (jTable_Selected.isShowing())
        {
            //System.out.println("4 ctrl updateUI");
            jTable_Selected.updateUI();
        }
        
        jTable_Selected.setCursor(Cur_default);
    }
        
    private void jTable_AvailableMouseReleased(java.awt.event.MouseEvent evt) {
        leftCurRow = jTable_Available.getSelectedRow();
        
        if (leftCurRow < 0)
            return;
    }

    private void jTable_SelectedMouseReleased(java.awt.event.MouseEvent evt) {
        rightCurRow = jTable_Selected.getSelectedRow();
        
        if (rightCurRow < 0)
            return;
    }

    private int rowCount(Object[][] tdata) 
    {
        int i = 0;
        while (i < tdata.length) {
            if (tdata[i][0] == null) break;
            ++i;
        }
        return i;
    }    

    private void closeANDCancel()
    {
        selectedData = restoreSelectedData;
        this.setVisible(false);
    }
    
    private void jButton_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelActionPerformed
        closeANDCancel();
    }//GEN-LAST:event_jButton_CancelActionPerformed

    private void jButton_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OkActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton_OkActionPerformed

    private void jButton_MoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MoveDownActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton_MoveDownActionPerformed

    private void jButton_MoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MoveUpActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton_MoveUpActionPerformed

    private void jButton_RemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveAllActionPerformed
        if (selectedData[0][0] == null)
            return;

        for (int i = 0; i < selectedData.length; i++) {
            selectedData[i][0] = null;
            selectedData[i][1] = null;
        }

        rightCurRow = -1;
        syncTables();
    }//GEN-LAST:event_jButton_RemoveAllActionPerformed

    private void jButton_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveActionPerformed
        if (rightCurRow < 0)
            return;
        
        int lastRow = rowCount(selectedData);
        if (rightCurRow > lastRow) {
            System.out.println("REMOVE ERROR: rightCurRow:"+rightCurRow+" lastRow:"+lastRow);
        }
        
        int rval = ((Integer)(jTable_Selected.getValueAt(rightCurRow, 0))).intValue();
        int srch = 0;
        
        for (int i = 0; i < lastRow; i++) {
            if (((Integer)(selectedData[i][0])).intValue() == rval)
                break;
            else
                srch = i+1;
        }
        
        for (int i = srch; i < lastRow; i++) {
            if ((i+1) == dataSize) {
                selectedData[i][0] = null;
                selectedData[i][1] = null;
                break;
            }
            selectedData[i][0] = selectedData[i+1][0];
            selectedData[i][1] = selectedData[i+1][1];
        }

        rightCurRow = -1;
        syncTables();
    }//GEN-LAST:event_jButton_RemoveActionPerformed

    private void jButton_AddAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddAllActionPerformed
        if (myAvailableData[0][0] == null)
            return;
        
        for (int i = 0; i < availableData.length; i++) {
            selectedData[i][0] = availableData[i][0];
            selectedData[i][1] = availableData[i][1];
        }
        
        leftCurRow = -1;
        syncTables();
    }//GEN-LAST:event_jButton_AddAllActionPerformed

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        if (leftCurRow < 0)
            return;
        
        int lastRow = rowCount(selectedData);
        if (lastRow >= dataSize) {
            System.out.println("ADD ERROR: lastRow:"+lastRow+" dataSize:"+dataSize);
        }
        selectedData[lastRow][0] = jTable_Available.getValueAt(leftCurRow, 0);
        selectedData[lastRow][1] = jTable_Available.getValueAt(leftCurRow, 1);
        
        leftCurRow = -1;
        syncTables();
    }//GEN-LAST:event_jButton_AddActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeANDCancel();
    }//GEN-LAST:event_closeDialog

    

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames ={ "Id",
                                        "Description"
        };
                                        
        private Object[][] data = {};   
        
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            int i = 0;
            while (i < data.length) {
                if (data[i][0] == null) break;
                ++i;
            }
            return i;
        }    

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public java.lang.Object getValueAt(int row, int col) {
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
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
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
        public void setValueAt(Object value, int row, int col) {
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

        private void printDebugData() {
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

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
    
    
    public void setEnableButtons(boolean f)
    {
        jButton_Add.setEnabled(f);
        jButton_AddAll.setEnabled(f);
        jButton_Remove.setEnabled(f);
        jButton_RemoveAll.setEnabled(f);
        jButton_Ok.setEnabled(f);
    }
    
    private javax.swing.JTable jTable_Available;
    private javax.swing.JTable jTable_Selected;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private TableSorter sorter2;
    private MyTableModel myTableModel2;

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
   
    private boolean DEBUG = false;
    private int[] CellDimension = {40,160};   // really needed ? yes
    private int minCellDimension = 40;

    private Object[][] availableData = {};   
    private Object[][] myAvailableData = {};   
    private Object[][] selectedData = {};   
    private Object[][] restoreSelectedData = {};   
    private int dataSize = 0;
    
    private int leftCurRow = -1;
    private int rightCurRow = -1;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_Available;
    private javax.swing.JLabel jLabel_TitleIcon;
    private javax.swing.JLabel jLabel_Selected;
    private javax.swing.JPanel jPanel_ExitButtons;
    private javax.swing.JButton jButton_Ok;
    private javax.swing.JButton jButton_Cancel;
    private javax.swing.JPanel jPanel_LeftButtons;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_AddAll;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_RemoveAll;
    private javax.swing.JPanel jPanel_RightButtons;
    private javax.swing.JButton jButton_MoveUp;
    private javax.swing.JButton jButton_MoveDown;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}