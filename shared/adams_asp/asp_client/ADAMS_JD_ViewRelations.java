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
import java.awt.Cursor;

public class ADAMS_JD_ViewRelations extends javax.swing.JDialog {

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_ViewRelations(java.awt.Frame parent, boolean modal,int width,int height,String str_Tipo_Conf,int POS_ELEMENT,ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG) {
        super(parent, modal);
        initComponents();
       
        this.str_TIPO_DI_CONFIGURAZIONE = str_Tipo_Conf;        
        this.local_V_TAB_CONFIG = parent_V_TAB_CONFIG;
                
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
        jTable_REL.setFont(new java.awt.Font("Verdana", 1, 10));
        jTable_REL.setRowHeight(20);
        jTable_REL.setRowMargin(2);
        jTable_REL.setAutoCreateColumnsFromModel(true);
        jTable_REL.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
        //jTable_REL.setSelectionBackground(java.awt.Color.yellow);
        //jTable_REL.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        jScrollPane_REL.setViewportView(jTable_REL);        
        AddRowJTabel_REL(POS_ELEMENT);
        sorter_REL.setSortingStatus(0,1); // ordino per tag
               
        this.setTitle("Relations "+str_TIPO_DI_CONFIGURAZIONE);
        jL_Title.setText("All Relations for "+local_V_TAB_CONFIG.getTagTE(POS_ELEMENT));
        setCenteredFrame(width,height);
        //show();
        this.setVisible(true);
    }

    
    
    public Vector read_RelationsForIdElement(int ID_ELEMENT)
    {
        Vector V_REL_ID_NAME = new Vector();
        String typeConfig       = str_TIPO_DI_CONFIGURAZIONE;
        int posElemento         = ID_ELEMENT;

        //Costruzione Stringa
        String sel_ConfigNtm = "select TIPO_DI_CONFIGURAZIONE,RELATION_NAME ";
        sel_ConfigNtm = sel_ConfigNtm+ " from tab_list_relation";
        sel_ConfigNtm = sel_ConfigNtm+ " where (TIPO_DI_CONFIGURAZIONE='"+typeConfig+"')";
        sel_ConfigNtm = sel_ConfigNtm+ " and  (RELATION_NAME LIKE '"+posElemento+"::%'";
        sel_ConfigNtm = sel_ConfigNtm+ " or  RELATION_NAME LIKE '%::"+posElemento+"::%'";
        sel_ConfigNtm = sel_ConfigNtm+ " or  RELATION_NAME LIKE '%::"+posElemento+"')";
          
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
                    String RelationName = rs_simple.getString("RELATION_NAME");
                    
                    if(RelationName != null)
                        V_REL_ID_NAME.addElement(RelationName);                    
                    
                    //System.out.println("RelationName -------------> "+RelationName);
                    
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("read_RelationsForIdElement(...) rs == null");            
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_JD_ViewRelations-1) SQL Operation " + exc.toString());
        }
        
        return V_REL_ID_NAME;
    }
    
    
    private void AddRowJTabel_REL(int POS_ELEMENT)
    {
        jTable_REL.setCursor(Cur_wait);
        Object[][] dataValues;
                      
        V_RelNameWithID = read_RelationsForIdElement(POS_ELEMENT);
        int SIZE = V_RelNameWithID.size();

        dataValues = new Object[SIZE][1];
        for(int i=0; i<SIZE;i++)
        {
            StringTokenizer STKrelationName = new StringTokenizer( (String)V_RelNameWithID.elementAt(i),"::");            
            
            String dot = "";
            String RelationNameTag = "<html><p>";
            
            while(STKrelationName.hasMoreTokens())
            {     
                int id_Relation = new Integer(STKrelationName.nextToken()).intValue();
                
                if(id_Relation == POS_ELEMENT)
                    RelationNameTag += dot+"<font color=#FFFFFF><span style = background-color:#0000FF>"+local_V_TAB_CONFIG.getTagTE(id_Relation)+"</span></font>";
                else
                    RelationNameTag += dot+local_V_TAB_CONFIG.getTagTE(id_Relation);
                
                dot = "::";
            }                
            
            RelationNameTag += "</p></html>";            
            dataValues[i][0] = new String(RelationNameTag);
        }

        String[] all_columnNames2 ={"Tag Relation (present #"+SIZE+")"};
        myTableModel_REL.setColumnName(all_columnNames2);
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
        jL_Title = new javax.swing.JLabel();
        jScrollPane_REL = new javax.swing.JScrollPane();
        jP_RaltionsName = new javax.swing.JPanel();
        jP_south = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rel_32.png")));
        jL_Title.setText("Relation");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 40));
        jL_Title.setOpaque(true);
        getContentPane().add(jL_Title, java.awt.BorderLayout.NORTH);
        
        jP_RaltionsName.setLayout(new java.awt.GridLayout(0, 1));
        
        jScrollPane_REL.setViewportView(jP_RaltionsName);
        
        getContentPane().add(jScrollPane_REL, java.awt.BorderLayout.CENTER);
        
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
    private javax.swing.JScrollPane jScrollPane_REL;
    private javax.swing.JPanel jP_RaltionsName;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables
    private boolean DEBUG = false;
    
    private String str_TIPO_DI_CONFIGURAZIONE = null;
    private Vector V_RelNameWithID;
    private ADAMS_Vector_TAB_CONFIG local_V_TAB_CONFIG = null;
    
    private javax.swing.JTable jTable_REL;
    private TableSorter sorter_REL;
    private MyTableModel myTableModel_REL;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
}
