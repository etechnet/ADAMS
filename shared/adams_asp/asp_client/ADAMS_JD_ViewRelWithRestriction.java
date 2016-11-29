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

public class ADAMS_JD_ViewRelWithRestriction extends javax.swing.JDialog {

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_ViewRelWithRestriction(java.awt.Frame parent, boolean modal, int width, int height, String str_Tipo_Conf, ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG) {
        super(parent, modal);
        initComponents();

        
        // *** Costruzione JTABLE TE ***/
        String[] all_columnNames2 ={"Relation with Restrictions Obbl.", "Restrictions Obbl."};
        myTableModel_REL = new MyTableModel(all_columnNames2);
        sorter_REL = new TableSorter(myTableModel_REL);
        
        jTable_REL = new javax.swing.JTable(sorter_REL);
        JTableHeader header2 = jTable_REL.getTableHeader();
        header2.setFont(ADAMS_GlobalParam.font_B10);
        header2.setReorderingAllowed(false);

        sorter_REL.setTableHeader(header2);    
        int [] columnsNoSort ={1};
        sorter_REL.setDisabledColumnSort(columnsNoSort); //disabilito il sort alla colonna columnsNoSort[...]
        
        jTable_REL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_REL.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_REL.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_REL.setRowHeight(20);
        jTable_REL.setRowMargin(2);
        jTable_REL.setAutoCreateColumnsFromModel(false);
        jTable_REL.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        //jTable_REL.setSelectionBackground(java.awt.Color.blue);
        
        jScrollPane_REL.setViewportView(jTable_REL);
                
        this.str_TIPO_DI_CONFIGURAZIONE = str_Tipo_Conf;        
        this.local_V_TAB_CONFIG = parent_V_TAB_CONFIG;
             
        this.setTitle("Relation"+str_TIPO_DI_CONFIGURAZIONE);
        jL_Title.setText("Restrizioni Obbligatorie Associate");
        
        SetTable();
        AddRowJTabel();
        
        setCenteredFrame(width,height);
        //show();
        this.setVisible(true);
    }

    public void SetTable()
    {
        this.setCursor(Cur_wait);

        int [] CellDimension ={550,250};
        jTable_REL.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //jTable_REL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);        
        JTableHeader header = jTable_REL.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);
        
        TableColumn column = null;
        for(int i=0; i<jTable_REL.getColumnCount(); i++)
        {
            column = jTable_REL.getColumnModel().getColumn(i);     

            column.setMinWidth(30);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        jScrollPane_REL.setViewportView(jTable_REL);
        this.setCursor(Cur_default);
    }
    
    
    public Vector read_Relations()
    {
        this.setCursor(Cur_wait);
        Vector V_LISTA_RELAZIONI = new Vector();
        
        String typeConfig       = str_TIPO_DI_CONFIGURAZIONE;

        //Costruzione Stringa
        String sel_ConfigNtm = "select TIPO_DI_CONFIGURAZIONE,POSIZIONE_ELEMENTO,RELATION_NAME ";
        sel_ConfigNtm = sel_ConfigNtm+ " from tab_list_relation ";
        sel_ConfigNtm = sel_ConfigNtm+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO !=-2"; //a.POSIZIONE_ELEMENTO !=-2 ---> NO GHOST
        
         //System.out.println("sel 0 ----> "+sel_ConfigNtm);
        //Query_rs_Statement usato per non perdere il resulset
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS_Statement(sel_ConfigNtm,SQLStatement);
        
        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {   
                    int posElemento = rs_simple.getInt("POSIZIONE_ELEMENTO"); 
                    
                    String RelationName = rs_simple.getString("RELATION_NAME");                    
                    ADAMS_TAB_RELAZIONI_ELEMENTO relazioni_elemento = new ADAMS_TAB_RELAZIONI_ELEMENTO(RelationName);
                    
                    //// ELEMENTI_RESTRIZIONE_OBBL
                    String sel_ConfigNtm4 = "select TIPO_DI_CONFIGURAZIONE,ELEMENTI_RESTRIZIONE_OBBL ";
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " from tab_list_restriction_ob ";
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento;
                    sel_ConfigNtm4 = sel_ConfigNtm4+ " and RELATION_NAME ='"+relazioni_elemento.RELATION_NAME+"'";
                    
                    //System.out.println("sel 4 ----> "+sel_ConfigNtm4);
                    Statement SQLStatement_1 = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                    java.sql.ResultSet rs_nested_4  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm4,SQLStatement_1);
                    if(rs_nested_4 != null)
                    {
                        while (rs_nested_4.next())
                            relazioni_elemento.V_RESTRIZIONI_OBBL.addElement(new Integer(rs_nested_4.getInt("ELEMENTI_RESTRIZIONE_OBBL")));
                    }
                    
                    try
                    {
                        SQLStatement_1.close();
                    }
                    catch(java.sql.SQLException exc) 
                    {
                        System.out.println("(ADAMS_JD_ViewRelWithRestriction-2) SQL Operation " + exc.toString());
                    }
                    
                    V_LISTA_RELAZIONI.addElement(relazioni_elemento);
                 }
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("rs==null");
        }
                
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_JD_ViewRelWithRestriction-1) SQL Operation " + exc.toString());
        }
        
        this.setCursor(Cur_default);
        return V_LISTA_RELAZIONI;
    }
    
    private void AddRowJTabel()
    {
        jTable_REL.setCursor(Cur_wait);

        Vector L_RELAZIONI = read_Relations();
        int V_SIZE = L_RELAZIONI.size();
        
        Object[][] dataValues;       
        Vector V_addRow_Relation_C1 = new Vector();
        Vector V_addRow_Restrict_C2 = new Vector();
        String HTML_TAG_1 = "<html><p><font color=#000000>";
        String HTML_TAG_2 = "<html><p><font color=#FFFFFF>";
        
        for(int i=0; i<V_SIZE; i++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO relazioni_elemento = (ADAMS_TAB_RELAZIONI_ELEMENTO)L_RELAZIONI.elementAt(i);
            
            if(relazioni_elemento.V_RESTRIZIONI_OBBL.size() > 0)
            {
                StringTokenizer STKrelationName = new StringTokenizer( relazioni_elemento.RELATION_NAME,"::");            

                String dot = "";
                String RelationNameTag = "";
                while(STKrelationName.hasMoreTokens())
                {     
                    String strTK = STKrelationName.nextToken();
                    //System.out.println("strTK ==>"+strTK);
                    int id_Relation = new Integer(strTK).intValue();
                    RelationNameTag += dot+local_V_TAB_CONFIG.getTagTE(id_Relation);

                    dot = "::";
                }                
                
                //System.out.println("-");
                
                for(int g = 0; g<relazioni_elemento.V_RESTRIZIONI_OBBL.size(); g++)
                {
                    if(g == 0)
                        V_addRow_Relation_C1.addElement(new String(HTML_TAG_1 + RelationNameTag+ "</font></p></html>"));
                    else
                        V_addRow_Relation_C1.addElement(new String(HTML_TAG_2 + RelationNameTag+ "</font></p></html>"));

                    V_addRow_Restrict_C2.addElement("<html><p><font color=#FF0000>" +local_V_TAB_CONFIG.getTagTE( ((Integer)relazioni_elemento.V_RESTRIZIONI_OBBL.elementAt(g)).intValue())+ "</font></p></html>");
                    
                    //System.out.println(RelationNameTag+" ->> "+local_V_TAB_CONFIG.getTagTE( ((Integer)relazioni_elemento.V_RESTRIZIONI_OBBL.elementAt(g)).intValue()) );
                }
            }
        }
        
        int NUM_ROWS = V_addRow_Relation_C1.size();
        dataValues = new Object[NUM_ROWS][2];
        for(int x=0; x<NUM_ROWS; x++)
        {
            dataValues[x][0] = ((String)V_addRow_Relation_C1.elementAt(x));
            dataValues[x][1] = ((String)V_addRow_Restrict_C2.elementAt(x));
        }
        
        //Metodo che elimina i TAG HTML iniziali nel sorter per una corretta operazione di Ordinamento della Colonna
        sorter_REL.RemoveTagHTMLforCompare(true,HTML_TAG_1,HTML_TAG_2);
        
        myTableModel_REL.setDataValues(dataValues);
        sorter_REL.setTableModel(myTableModel_REL);
        sorter_REL.setSortingStatus(0,1); // ordino per tag
        
        jScrollPane_REL.setViewportView(jTable_REL);
        jTable_REL.setCursor(Cur_default);
    }
    
   
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_Title = new javax.swing.JLabel();
        jP_south = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        jScrollPane_REL = new javax.swing.JScrollPane();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rel_32.png")));
        jL_Title.setText("Relation With Restriction Obbl.");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 40));
        jL_Title.setOpaque(true);
        getContentPane().add(jL_Title, java.awt.BorderLayout.NORTH);
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMaximumSize(new java.awt.Dimension(100, 22));
        jB_close.setMinimumSize(new java.awt.Dimension(100, 22));
        jB_close.setPreferredSize(new java.awt.Dimension(100, 22));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_close);
        
        getContentPane().add(jP_south, java.awt.BorderLayout.SOUTH);
        
        getContentPane().add(jScrollPane_REL, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Title;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_close;
    private javax.swing.JScrollPane jScrollPane_REL;
    // End of variables declaration//GEN-END:variables
    private boolean DEBUG = false;
    
    private String str_TIPO_DI_CONFIGURAZIONE = null;
    private ADAMS_Vector_TAB_CONFIG local_V_TAB_CONFIG = null;
        
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_REL;
    private TableSorter sorter_REL;
    private MyTableModel myTableModel_REL;
    
}
