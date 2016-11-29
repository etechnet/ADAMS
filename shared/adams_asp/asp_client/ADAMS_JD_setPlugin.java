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

public class ADAMS_JD_setPlugin extends javax.swing.JDialog {

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_setPlugin(java.awt.Frame parent, boolean modal, int width, int height, String str_Tipo_Conf,String pl_selected) {
        super(parent, modal);
        initComponents();
        
        this.PLUGIN_SELECTED = pl_selected;
        jL_Plugin.setBackground(Color.green.darker());
        jL_Plugin.setText(PLUGIN_SELECTED);
        
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames2 ={"Enabled","Plugin Name"};
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
        jL_Title.setText("All Plugins - "+str_TIPO_DI_CONFIGURAZIONE+" -");
        
        jL_Title.setFont(ADAMS_GlobalParam.font_B12);
        jTextArea_PL_Desc.setFont(ADAMS_GlobalParam.font_P10); 
        jL_Plugin.setFont(ADAMS_GlobalParam.font_B11);

        jB_apply.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        
        SetTable();
        read_Plugins();        
        AddRowJTabel();
        
        setCenteredFrame(width,height);        
    }
    
    public void  setGuiEnable(boolean flag)
    {
        jB_apply.setEnabled(flag);
        jB_cancel.setEnabled(flag);
    }

    public void SetTable()
    {
        this.setCursor(Cur_wait);
        int [] CellDimension ={30,100};        
        
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
                System.out.println("(ADAMS_JD_setPlugin-1) SQL Operation " + exc.toString());
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

        int NUM_ROWS = V_ALL_PLUGIN.size();
              
        Object[][] dataValues = new Object[NUM_ROWS][2];
        for(int x=0; x<NUM_ROWS; x++)
        {            
            PluginRow pl_Row = (PluginRow)V_ALL_PLUGIN.elementAt(x); 
            
            if(PLUGIN_SELECTED.compareTo(pl_Row.NOME_PLUGIN) == 0)
                dataValues[x][0] = new Boolean(true);
            else
                dataValues[x][0] = new Boolean(false);
            
            dataValues[x][1] = (String)pl_Row.NOME_PLUGIN;         
        }
        
        myTableModel_PL.setDataValues(dataValues);
        sorter_PL.setTableModel(myTableModel_PL);
        //sorter_PL.setSortingStatus(0,1); // ordino per tag
        
        jScrollPane_PL.setViewportView(jTable_PL);
        jTable_PL.setCursor(Cur_default);
    }
       
    private void jTable_PLMouseReleased(java.awt.event.MouseEvent evt) 
    {
        int selectedRow = jTable_PL.getSelectedRow();
        if(selectedRow >= 0)
        {
            String plSel = (String)jTable_PL.getValueAt(selectedRow,1);
            
            for(int i=0; i<V_ALL_PLUGIN.size();i++)
            {
                PluginRow pl_Row = (PluginRow)V_ALL_PLUGIN.elementAt(i);
                if(pl_Row.NOME_PLUGIN.compareTo(plSel) == 0)
                {
                     jTextArea_PL_Desc.setText("- "+pl_Row.NOME_PLUGIN+" -\n"+pl_Row.DESCRIZIONE);
                     break;
                }
            }
            jTextArea_PL_Desc.setCaretPosition(0);            
        }
    }
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_Title = new javax.swing.JLabel();
        jP_Center = new javax.swing.JPanel();
        jScrollPane_PL = new javax.swing.JScrollPane();
        jScroll_DESC = new javax.swing.JScrollPane();
        jTextArea_PL_Desc = new javax.swing.JTextArea();
        jP_option_read = new javax.swing.JPanel();
        jB_apply = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        jL_Plugin = new javax.swing.JLabel();
        jP_south = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plugin_32.png")));
        jL_Title.setText("Plugins");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 40));
        jL_Title.setOpaque(true);
        getContentPane().add(jL_Title, java.awt.BorderLayout.NORTH);
        
        jP_Center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_Center.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_PL.setPreferredSize(new java.awt.Dimension(90, 200));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridheight = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 0);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_Center.add(jScrollPane_PL, gridBagConstraints1);
        
        jTextArea_PL_Desc.setEditable(false);
        jTextArea_PL_Desc.setLineWrap(true);
        jTextArea_PL_Desc.setWrapStyleWord(true);
        jTextArea_PL_Desc.setPreferredSize(new java.awt.Dimension(250, 15));
        jScroll_DESC.setViewportView(jTextArea_PL_Desc);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_Center.add(jScroll_DESC, gridBagConstraints1);
        
        jP_option_read.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_option_read.setBackground(new java.awt.Color(230, 230, 230));
        jP_option_read.setPreferredSize(new java.awt.Dimension(10, 40));
        jB_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        jB_apply.setToolTipText("Login");
        jB_apply.setBorderPainted(false);
        jB_apply.setContentAreaFilled(false);
        jB_apply.setFocusPainted(false);
        jB_apply.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_apply.setMaximumSize(new java.awt.Dimension(56, 28));
        jB_apply.setMinimumSize(new java.awt.Dimension(56, 28));
        jB_apply.setPreferredSize(new java.awt.Dimension(56, 28));
        jB_apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        jB_apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        jB_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_applyActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_option_read.add(jB_apply, gridBagConstraints2);
        
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
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_option_read.add(jB_cancel, gridBagConstraints2);
        
        jL_Plugin.setBackground(new java.awt.Color(230, 230, 230));
        jL_Plugin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Plugin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Plugin.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_Plugin.setPreferredSize(new java.awt.Dimension(250, 22));
        jL_Plugin.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(3, 22, 3, 22);
        gridBagConstraints2.weightx = 1.0;
        jP_option_read.add(jL_Plugin, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_Center.add(jP_option_read, gridBagConstraints1);
        
        getContentPane().add(jP_Center, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMaximumSize(new java.awt.Dimension(100, 22));
        jB_close.setMinimumSize(new java.awt.Dimension(100, 22));
        jB_close.setPreferredSize(new java.awt.Dimension(100, 30));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_close, java.awt.BorderLayout.SOUTH);
        
        getContentPane().add(jP_south, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        PLUGIN_SELECTED = "";
        jL_Plugin.setText(PLUGIN_SELECTED);
        jTextArea_PL_Desc.setText("");
        jTable_PL.clearSelection();
        AddRowJTabel();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
        int selectedRow = jTable_PL.getSelectedRow();
        if(selectedRow >= 0)
        {
            PLUGIN_SELECTED = (String)jTable_PL.getValueAt(selectedRow,1);
            jL_Plugin.setText(PLUGIN_SELECTED);
            jTable_PL.clearSelection();
            AddRowJTabel();
        }
    }//GEN-LAST:event_jB_applyActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        setVisible(false);        
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);        
    }//GEN-LAST:event_closeDialog

    public String getPluginEnabled()
    {
        return this.PLUGIN_SELECTED;
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

    class PluginRow
    {
        PluginRow(String plname,String ConfName, String plDesc)
        {
            NOME_PLUGIN             = new String(plname);                             
            TIPO_DI_CONFIGURAZIONE  = new String(ConfName);                              
            DESCRIZIONE             = new String(plDesc);  
        }

        String NOME_PLUGIN = "";                              
        String TIPO_DI_CONFIGURAZIONE = "";                             
        String DESCRIZIONE = "";     
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Title;
    private javax.swing.JPanel jP_Center;
    private javax.swing.JScrollPane jScrollPane_PL;
    private javax.swing.JScrollPane jScroll_DESC;
    private javax.swing.JTextArea jTextArea_PL_Desc;
    private javax.swing.JPanel jP_option_read;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JLabel jL_Plugin;
    private javax.swing.JPanel jP_south;
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
    private String PLUGIN_SELECTED = "";
}
