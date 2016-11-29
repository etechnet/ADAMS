/*
 * SSM_JF_MonitoringOrbix.java
 *
 * Created on 25 ottobre 2006, 12.31
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
import javax.swing.*;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_JF_MonitoringOrbix extends javax.swing.JFrame implements Runnable {

    private boolean                     DEBUG               = false;
    public int                          type                = 0;
    private javax.swing.JTable          jt_ProcessiOrbix;
    private TableSorter                 sorter;
    private MyTableModel                myTableModel;
    private Thread                      th                  = null;
    private long                        TIME_SLEEP          = 5;
    private boolean                     Thread_RUN          = false;
    private boolean                     Thread_wakeup       = false;
    private Cursor                      Cur_default         = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                      Cur_wait            = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                      Cur_hand            = new Cursor(Cursor.HAND_CURSOR);
    private int[]                       CellDimension       = {130,150,80,40,80,40,80,420};
    private int                         minCellDimension    = 20;
    private java.awt.event.KeyListener  event_KEY;
    private SSM_JF_textArea              JF_TArea            = null;
    private int                         numRows             = 0;
    /**
     *Contiene aggiornato Indice identificativo univoco del Processo in esame sull'interfaccia (ProcessRow). 
     */
    private int                         ID_PROCESS_SELECTED = -1;   
    private int[]                       IDProcessSelected   = null;
    private String                      SWITCHSELECTED      = null;
    private String                      str_LAST_Data       = "";
    private Color                       c1                  = new Color(230,230,230);
    private Color                       c2                  = Color.black;
    private boolean                     flagReverse         = false;
    private java.awt.Color              ColorDefault        = new java.awt.Color(230,230,230);
    private java.util.Vector            V_ProcessROW        = null;
    
    public class MyRendererLabel extends JLabel implements TableCellRenderer 
    {
        public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) 
        {
            ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/images/time_proc.png"));
            setText(((JLabel)value).getText());
            setIcon(icon);
            return this;
        }
    }
   
    public class MyHeaderRenderer extends DefaultTableCellRenderer 
    {

        JLabel label = null;

        /**
         */
        public MyHeaderRenderer() {
            super();
            label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
        }//constructor

        /**
         * Overwrites DefaultTableCellRenderer.
         */
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row,int column) 
        {

            if (value != null) 
            {
                label.setText("" + value);
                label.setBackground(new java.awt.Color(72, 168, 201));
                label.setForeground(java.awt.Color.white);
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                label.setOpaque(true);
            } 
            else 
            {
                label.setText("");
            }

            return label;
        }//getTableCellRendererComponent()

        /**
         * Overwrires DefaultTableCellRenderer.
         */
        protected void setValue(Object value) 
        {
            if (value != null) 
            {
                label.setText("" + value);
            } 
            else 
            {
                label.setText("");
            }
        }//setValue()

        /**
         * @param toolTip text to use for label's tool tip
         */
        public void setToolTip(String toolTip) {
            if (toolTip != null) {
                label.setToolTipText(toolTip);
            }
        }//setToolTip()

        /**
         * @param icon icon to set for the label
         */
        public void setIcon(Icon icon) 
        {
            if (label != null) 
            {
               label.setIcon(icon);
            }
        }//setIcon()

    }//MyHeaderRenderer
    
    public class MyRenderer  implements TableCellRenderer 
    {
        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
        {
            switch(column)
            {
                case 0:
                {
                    return (JLabel)value;  
                }
                case 1:
                {
                    return (JButton)value;  
                }
                case 2:
                {
                    return (JButton)value;  
                }
                case 3:
                {
                    return (JButton)value;  
                }
                case 4:
                {
                    return (JLabel)value;  
                }
                case 5:
                {
                    return (JButton)value;  
                }
                
                default:
                {
                    return (JLabel)value;
                }
            }
        }
    }
    
    public class MyEditor implements TableCellEditor 
    {
        public Component getTableCellEditorComponent(JTable t, Object value,boolean isSelected, int row, int column)
        {
             switch(column)
            {
                case 0:
                {
                    return (JLabel)value;  
                }
                case 1:
                {
                    return (JButton)value;  
                }
                case 2:
                {
                    return (JButton)value;  
                }
                case 3:
                {
                    return (JButton)value;  
                }
                case 4:
                {
                    return (JLabel)value;  
                }
                case 5:
                {
                    return (JButton)value;  
                }
                
                default:
                {
                    return (JLabel)value;
                }
            }
        }
        
        public Object getCellEditorValue() { return null; }
        public void cancelCellEditing() {}
        public boolean stopCellEditing() { return true; }
        public boolean shouldSelectCell(EventObject o) { return true; }
        public boolean isCellEditable(EventObject o) { return true;     }
        public void addCellEditorListener(CellEditorListener l) {}
        public void removeCellEditorListener(CellEditorListener l) {}
    }

   

    /** Creates new form SSM_JF_MonitoringOrbix */
    public SSM_JF_MonitoringOrbix(int[] ID_ProcessSelected,String SWITCH_SELECTED,int timeSleep,int type) 
    {
        super();
        this.type=type;
        this.setTitle("Process Monitoring -"+SWITCH_SELECTED+" -");
        
        initComponents();
        
        jL_SW.setText(""+SWITCH_SELECTED);
        jl_time_refresh.setText(" Refresh time "+timeSleep+" seconds.");
        

        this.TIME_SLEEP         = timeSleep;   
        this.SWITCHSELECTED     = SWITCH_SELECTED;
        this.IDProcessSelected  = ID_ProcessSelected;
        this.numRows = IDProcessSelected.length;
        
        //System.out.println("numRows="+numRows);
        
        myTableModel = new MyTableModel();
        //sorter = new TableSorter(myTableModel);
        
        jt_ProcessiOrbix = new javax.swing.JTable(myTableModel);
        JTableHeader header = jt_ProcessiOrbix.getTableHeader();
        header.setFont(SSM_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        //sorter.setTableHeader(header);        
        
        jt_ProcessiOrbix.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_ProcessiOrbix.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jt_ProcessiOrbix.setFont(new java.awt.Font("Verdana", 0, 10));
        jt_ProcessiOrbix.setRowHeight(20);
        //jt_ProcessiOrbix.setRowMargin(2);
        jt_ProcessiOrbix.setAutoCreateColumnsFromModel(false);
        
        jScrollrows.setViewportView(jt_ProcessiOrbix);
        
        /*jt_ProcessiOrbix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TEMouseReleased(evt);
            }
        });*/
        
        int heigth_jScrollrows = numRows*35;
        if(heigth_jScrollrows > 450)
            heigth_jScrollrows = 450;
        
        jScrollrows.setPreferredSize(new java.awt.Dimension(100, heigth_jScrollrows));
        
        jScrollrows.getVerticalScrollBar().setUnitIncrement(30);
        
        jTF_number.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,2));
        
        event_KEY = (new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_KeyReleased(evt);
            }
            });
        jTF_number.addKeyListener(event_KEY);
        
        
        
        
        
        setCenteredFrame(1000,950);
        SetTable(jt_ProcessiOrbix);
        SetTableHeader(jt_ProcessiOrbix);
        //AddRowJTabel();
        setGui();
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        
        start();
    }
    
    private void SetTableHeader(javax.swing.JTable jtable)
    {
        MyHeaderRenderer mhr = new MyHeaderRenderer();
        mhr.setToolTip("Type Process");
        //mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        TableColumn col = jtable.getColumnModel().getColumn(0);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Nome Process");
        mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        col = jtable.getColumnModel().getColumn(1);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Statuss Process");
        //mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        col = jtable.getColumnModel().getColumn(2);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Change format param 1");
        //mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        col = jtable.getColumnModel().getColumn(3);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Parama 1");
        //mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        col = jtable.getColumnModel().getColumn(4);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Change format param 1");
        //mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        col = jtable.getColumnModel().getColumn(5);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Param2");
        //mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        col = jtable.getColumnModel().getColumn(6);
        col.setHeaderRenderer(mhr);
        
        mhr = new MyHeaderRenderer();
        mhr.setToolTip("Message Process");
        mhr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/message.png")));
        col = jtable.getColumnModel().getColumn(7);
        col.setHeaderRenderer(mhr);
    }
     
    private void jT_KeyReleased(java.awt.event.KeyEvent evt) 
    {
       boolean flag = false;
       if(jTF_number.getText().length() > 0)
            flag = true;
       
       jB_analize.setEnabled(flag);
       //jB_all.setEnabled(flag);
    }
    
    public void setGui()
    {
        jB_resetFields.setCursor(Cur_hand);
        jTF_number.setCursor(Cur_hand);
        jB_resetAll.setCursor(Cur_hand);
        jB_all.setCursor(Cur_hand);
        jB_analize.setCursor(Cur_hand);
        jB_stop.setCursor(Cur_hand);
        jB_active.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        jb_reverse.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_north = new javax.swing.JPanel();
        jL_SW = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jB_stop = new javax.swing.JButton();
        jB_active = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jl_Time = new javax.swing.JLabel();
        jl_time_refresh = new javax.swing.JLabel();
        jScrollrows = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jScroll_south = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jP_button = new javax.swing.JPanel();
        jB_resetFields = new javax.swing.JButton();
        jB_resetAll = new javax.swing.JButton();
        jB_all = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTF_number = new javax.swing.JTextField();
        jB_close = new javax.swing.JButton();
        jB_analize = new javax.swing.JButton();
        jb_reverse = new javax.swing.JButton();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jP_north.setLayout(new java.awt.BorderLayout());
        
        jL_SW.setBackground(new java.awt.Color(230, 230, 230));
        jL_SW.setFont(new java.awt.Font("Helvetica", 1, 18));
        jL_SW.setForeground(new java.awt.Color(9, 126, 165));
        jL_SW.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_SW.setOpaque(true);
        jP_north.add(jL_SW, java.awt.BorderLayout.CENTER);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setPreferredSize(new java.awt.Dimension(90, 60));
        jB_stop.setBackground(new java.awt.Color(230, 230, 230));
        jB_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ledred.gif")));
        jB_stop.setText("Stop");
        jB_stop.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_stop.setFocusPainted(false);
        jB_stop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jB_stop.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_stop.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jB_stop, gridBagConstraints2);
        
        jB_active.setBackground(new java.awt.Color(230, 230, 230));
        jB_active.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/animled.gif")));
        jB_active.setText("Active");
        jB_active.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_active.setFocusPainted(false);
        jB_active.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jB_active.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_active.setPreferredSize(new java.awt.Dimension(75, 23));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jB_active, gridBagConstraints2);
        
        jP_north.add(jPanel1, java.awt.BorderLayout.EAST);
        
        jPanel2.setLayout(new java.awt.GridLayout(2, 1));
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jl_Time.setBackground(new java.awt.Color(230, 230, 230));
        jl_Time.setForeground(new java.awt.Color(9, 126, 165));
        jl_Time.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jl_Time.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jl_Time.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jl_Time);
        
        jl_time_refresh.setBackground(new java.awt.Color(230, 230, 230));
        jl_time_refresh.setForeground(new java.awt.Color(9, 126, 165));
        jl_time_refresh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jl_time_refresh);
        
        jP_north.add(jPanel2, java.awt.BorderLayout.WEST);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jP_north, gridBagConstraints1);
        
        jScrollrows.setBackground(new java.awt.Color(230, 230, 230));
        jScrollrows.setMinimumSize(new java.awt.Dimension(22, 40));
        jScrollrows.setPreferredSize(new java.awt.Dimension(22, 40));
        jScrollrows.setAutoscrolls(true);
        jScrollrows.setOpaque(false);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 2.0;
        getContentPane().add(jScrollrows, gridBagConstraints1);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setBorder(new javax.swing.border.TitledBorder(null, " Selected Process ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jScroll_south.setBackground(new java.awt.Color(230, 230, 230));
        jScroll_south.setPreferredSize(new java.awt.Dimension(1000, 100));
        jTextArea.setBackground(new java.awt.Color(230, 230, 230));
        jTextArea.setEditable(false);
        jTextArea.setFont(new java.awt.Font("Dialog", 1, 12));
        jScroll_south.setViewportView(jTextArea);
        
        jP_south.add(jScroll_south, java.awt.BorderLayout.CENTER);
        
        jP_button.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_button.setBackground(new java.awt.Color(230, 230, 230));
        jP_button.setPreferredSize(new java.awt.Dimension(1000, 28));
        jB_resetFields.setBackground(new java.awt.Color(230, 230, 230));
        jB_resetFields.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_yellow.png")));
        jB_resetFields.setText("Reset Yellow Fields");
        jB_resetFields.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_resetFields.setFocusPainted(false);
        jB_resetFields.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_resetFields.setMinimumSize(new java.awt.Dimension(170, 22));
        jB_resetFields.setPreferredSize(new java.awt.Dimension(170, 22));
        jB_resetFields.setEnabled(false);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_button.add(jB_resetFields, gridBagConstraints3);
        
        jB_resetAll.setBackground(new java.awt.Color(230, 230, 230));
        jB_resetAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_yellow.png")));
        jB_resetAll.setText("Reset All");
        jB_resetAll.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_resetAll.setFocusPainted(false);
        jB_resetAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_resetAll.setMinimumSize(new java.awt.Dimension(90, 22));
        jB_resetAll.setPreferredSize(new java.awt.Dimension(90, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 15);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_button.add(jB_resetAll, gridBagConstraints3);
        
        jB_all.setBackground(new java.awt.Color(230, 230, 230));
        jB_all.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select_all_blu.png")));
        jB_all.setText("All");
        jB_all.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_all.setDoubleBuffered(true);
        jB_all.setFocusPainted(false);
        jB_all.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_all.setPreferredSize(new java.awt.Dimension(50, 22));
        jB_all.setEnabled(false);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 5;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 17);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_button.add(jB_all, gridBagConstraints3);
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOG Date Number");
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 18));
        jLabel1.setEnabled(false);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 15, 0, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_button.add(jLabel1, gridBagConstraints3);
        
        jTF_number.setFont(new java.awt.Font("Dialog", 1, 12));
        jTF_number.setMinimumSize(new java.awt.Dimension(25, 22));
        jTF_number.setPreferredSize(new java.awt.Dimension(25, 20));
        jTF_number.setEnabled(false);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_button.add(jTF_number, gridBagConstraints3);
        
        jB_close.setBackground(new java.awt.Color(230, 230, 230));
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_x.png")));
        jB_close.setText("Close");
        jB_close.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_close.setFocusPainted(false);
        jB_close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_close.setPreferredSize(new java.awt.Dimension(65, 22));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 8;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.ipady = 1;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;
        jP_button.add(jB_close, gridBagConstraints3);
        
        jB_analize.setBackground(new java.awt.Color(230, 230, 230));
        jB_analize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select_all_blu.png")));
        jB_analize.setText("Analize");
        jB_analize.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_analize.setDoubleBuffered(true);
        jB_analize.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_analize.setMaximumSize(new java.awt.Dimension(80, 25));
        jB_analize.setMinimumSize(new java.awt.Dimension(80, 25));
        jB_analize.setPreferredSize(new java.awt.Dimension(80, 22));
        jB_analize.setEnabled(false);
        jB_analize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Log_ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_button.add(jB_analize, gridBagConstraints3);
        
        jb_reverse.setBackground(new java.awt.Color(230, 230, 230));
        jb_reverse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters_invert.png")));
        jb_reverse.setText("Invert Color");
        jb_reverse.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb_reverse.setFocusPainted(false);
        jb_reverse.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jb_reverse.setPreferredSize(new java.awt.Dimension(125, 22));
        jb_reverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_reverseActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 7;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.ipady = 1;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;
        jP_button.add(jb_reverse, gridBagConstraints3);
        
        jP_south.add(jP_button, java.awt.BorderLayout.SOUTH);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        getContentPane().add(jP_south, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void Log_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Log_ActionPerformed
        // Add your handling code here:
        this.setCursor(Cur_wait);
        Object target = evt.getSource();
        
        int day_selected = new Integer(jTF_number.getText().trim()).intValue();
        int Single_ALL;
        String nameProc = "";
        if(target == jB_all)
        {
            Single_ALL = SSM_GlobalParam.ALL_PROCESS;
        }
        else
        {
            Single_ALL = SSM_GlobalParam.SINGLE_PROCESS;
            //nameProc = (getProcessROW_isPresent(ID_PROCESS_SELECTED)).getNameProcessCompleto();
            nameProc = (getProcessROW_isPresent(ID_PROCESS_SELECTED)).getNameProcess();
        }
        
        if(JF_TArea != null)
                JF_TArea.dispose();
        
        JF_TArea = new SSM_JF_textArea(Single_ALL,SWITCHSELECTED,nameProc,day_selected,SSM_GlobalParam.NO_ORBIX);
        JF_TArea.setLocation(this.getX()+120,this.getY()+100);
        JF_TArea.start();
        JF_TArea.pack();
        //JF_TArea.show();
        JF_TArea.setVisible(true);
        
        this.setCursor(Cur_default);
    }//GEN-LAST:event_Log_ActionPerformed

    private void jb_reverseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_reverseActionPerformed
        // Add your handling code here:
        // Add your handling code here:
        //System.out.println("Scambia");
        flagReverse=!flagReverse;
        for(int i=0;i<V_ProcessROW.size();i++)
        {
            SSM_ProcessRowNew p=(SSM_ProcessRowNew)V_ProcessROW.elementAt(i);
            p.reverseColor();
            Color c=c1;
            c1=c2;
            c2=c;
            //jP_row.setBackground(c);
        }
        
        Color cf=jTextArea.getForeground();
        Color cb=jTextArea.getBackground();
        
        jTextArea.setForeground(cb);
        jTextArea.setBackground(cf);
        jP_south.setForeground(cb);
        jP_south.setBackground(cf);
        
        
        cf=jP_button.getForeground();
        cb=jP_button.getBackground();
        
        jP_button.setForeground(cb);
        jP_button.setBackground(cf);
        
       
        cf=jPanel1.getForeground();
        cb=jPanel1.getBackground();
        jPanel1.setForeground(cb);
        jPanel1.setBackground(cf);
        
        
        cf=jPanel2.getForeground();
        cb=jPanel2.getBackground();
        jPanel2.setForeground(cb);
        jPanel2.setBackground(cf);
        jL_SW.setForeground(cb);
        jL_SW.setBackground(cf);
        
        jt_ProcessiOrbix.updateUI();
        
        

    }//GEN-LAST:event_jb_reverseActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        // Add your handling code here:
        closeFrame();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame()
    {
        stop();
        this.dispose();        
    }
    
/**
    * In questo metodo viene definito il codice del Thread, necessario per la richiesta dei dati aggiornati riguardanti i processi
    * d'interesse, mediante il metodo "refresh_MonitorProcess(SWITCHSELECTED,...)" tramite l'oggetto di tipo SSM_CORBAConnection dichiarato 
    * nella classe globale SSM_GlobalParam.
    * Inoltre viene effettuato l'aggiornamento grafico con nuovi dati da visualizzare.
    */
    public void run()
    {               
        while(Thread_RUN)
        {        
            this.setCursor(Cur_wait);
            ProcesDetail[] processDetails=null;
            
            if(this.type==SSM_GlobalParam.NO_ORBIX)
            {
                processDetails = SSM_GlobalParam.CORBAConn.refresh_MonitorProcess(SWITCHSELECTED,1);
            }
            else if(this.type==SSM_GlobalParam.ORBIX)
            {
                processDetails = SSM_GlobalParam.CORBAConn.refresh_MonitorProcess(SWITCHSELECTED,2);
            }
            
            if(Thread_RUN == false)
            {
                this.setCursor(Cur_default);
                return;
            }
            
            
            if(processDetails !=null)
            {
                //System.out.println("refresh_MonitorProcess processDetails.length => "+processDetails.length);                
             
                for(int x=0; x<IDProcessSelected.length; x++)
                {
                    int id_process_selected = IDProcessSelected[x];                    
                    for(int i=0; i<processDetails.length; i++)
                    {
                        if(id_process_selected == processDetails[i].idProcesso)
                        {                              
                            SSM_ProcessRowNew PRow = getProcessROW_isPresent(id_process_selected);
                            
                            if (PRow == null) //SSM_JP_ProcessRow non presente nel vettore (1ï¿½ giro)-- CREO nuova riga e set .
                            {
                                //System.out.println("Creo nuova Riga per il processo con ID ==> "+id_process_selected);
                                PRow = new SSM_ProcessRowNew(false,id_process_selected,this.SWITCHSELECTED,this,type);
                                V_ProcessROW.addElement(PRow); //ADD Al VETTORE                                
                            }                            
                         ////// Setto PARAMETRI //////
                            PRow.setTypeProcess(new String(processDetails[i].tipoProcesso).trim()); 
                            PRow.setNameProcess(new String(processDetails[i].nomeProcesso).trim());
                            
                            //System.out.println(new String(processDetails[i].nomeProcesso).trim()+" - "+new String(processDetails[i].colorBKnomeProcesso).trim());
                            
                            PRow.setColorBG_NameProcess(ColorExtract(new String(processDetails[i].colorBKnomeProcesso).trim(),ColorDefault));
                            
                            if(processDetails[i].param.length > 2)
                            {
                                PRow.setStatus(processDetails[i].param[2]);
                            }
                            else
                            {
                                PRow.setStatus(1);
                                System.out.println("er. NOT Found III PARAM");
                            }
                            
                            PRow.setParams(processDetails[i].param[0],processDetails[i].param[1]);
                            PRow.setMessage(new String(processDetails[i].msgProcesso).trim());
                            PRow.setColorBG_Message(ColorExtract(new String(processDetails[i].colorBKmsgProcesso).trim(),ColorDefault));
                            PRow.setnumRestartTime(processDetails[i].numRestartTime);
                            break;
                        }
                    }
                }
                
               /*for(int i=0; i<processDetails.length; i++)
                {               
                    System.out.println("ID Processo         ==> "+processDetails[i].idProcesso);	
                    System.out.println("Tipologia Processo  ==> "+new String(processDetails[i].tipoProcesso).trim());
                    System.out.println("Nome Processo       ==> "+new String(processDetails[i].nomeProcesso).trim());
                    System.out.println("Colore BK Processo  ==> "+new String(processDetails[i].colorBKnomeProcesso).trim());

                    System.out.println();
                    for(int x=0; x<processDetails[i].param.length; x++)
                    {
                        System.out.print("Parametri di output ==> "+processDetails[i].param[x]+" ");
                    }

                    System.out.println("Status del Processo     ==> "+processDetails[i].statusProcesso);
                    System.out.println("Numero di restart       ==> "+processDetails[i].numRestartTime);
                    System.out.println("Flag di schedulazione   ==> "+processDetails[i].flagSched);
                    System.out.println("Messaggio               ==> "+new String(processDetails[i].msgProcesso).trim());
                    System.out.println("Colore Messaggio        ==> "+new String(processDetails[i].colorBKmsgProcesso).trim());
                } */
                
                int nRighe = V_ProcessROW.size();
                int nColonne = 8;
                Object[][] dataValues = new Object[nRighe][nColonne];
                for(int i=0;i<nRighe;i++)
                {
                    SSM_ProcessRowNew processRow_APPO = (SSM_ProcessRowNew)V_ProcessROW.elementAt(i);
                    dataValues[i][0] = processRow_APPO.jL_type;
                    dataValues[i][1] = processRow_APPO.jB_process;
                    dataValues[i][2] = processRow_APPO.jB_status;
                    dataValues[i][3] = processRow_APPO.jB_type1Param;
                    dataValues[i][4] = processRow_APPO.jL_type1ParamDesc;
                    dataValues[i][5] = processRow_APPO.jB_type2Param;
                    dataValues[i][6] = processRow_APPO.jL_type2ParamDesc;
                    dataValues[i][7] = processRow_APPO.jL_message;
                }
                
                myTableModel.setDataValues(dataValues);
                jt_ProcessiOrbix.setModel(myTableModel);
                //sorter.setTableModel(myTableModel);
                jScrollrows.setViewportView(jt_ProcessiOrbix);
            }
            else
            {
                System.out.println("******* processDetails[] is null ******** ");
            }
            
            str_LAST_Data = SSM_GlobalParam.CORBAConn.get_SystemTime(SWITCHSELECTED);
            
            if(str_LAST_Data != null)
                if(str_LAST_Data.length() > 0)
                    jl_Time.setText(" Last update: "+str_LAST_Data);            
            try
            {
                this.setCursor(Cur_default);
                for(int i=0; i<TIME_SLEEP; i++)
                {
                    if(Thread_wakeup == true)
                        break;
                    
                    th.sleep(1000);
                }
                Thread_wakeup = false;                
            }
            catch(InterruptedException e)
            {
                System.out.println("-- InterruptedException SSM_JF_MonitoringOutput.java ");
            }
        }        
        this.setCursor(Cur_default);
    }
    
    public void setID_PROCESS_SELECTED(int idProcessSelected)
    {
        this.ID_PROCESS_SELECTED = idProcessSelected;
        
        SSM_ProcessRowNew APPOPRow = getProcessROW_isPresent(ID_PROCESS_SELECTED);
        
        jP_south.setBorder(new javax.swing.border.TitledBorder(null, " Selected Process: "+APPOPRow.getNameProcess() , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), java.awt.Color.black));
        
        if( ( APPOPRow.getnumRestartTime() > 1 ) && (Thread_RUN == true) )
        {
            jB_resetFields.setEnabled(true);
        }
        else
            jB_resetFields.setEnabled(false);
        
        jLabel1.setEnabled(true);
        jTF_number.setEnabled(true);
    }
    
    
    private SSM_ProcessRowNew getProcessROW_isPresent(int idProcesso)
    {
        if(V_ProcessROW != null)
        {
            for(int i=0; i<V_ProcessROW.size(); i++)
            {
                SSM_ProcessRowNew processRow_APPO = (SSM_ProcessRowNew)V_ProcessROW.elementAt(i);
                if(processRow_APPO.getIDProcess() == idProcesso)
                {
                    return  processRow_APPO;
                }
            }
        }
        return null;
    }
    
    /**
     * Questo metodo permette la conversione di una Stringa contenete la descrizione di un colore in RGB (es: "255255255"),
     * in un oggetto Colore. Inoltre il metodo accetta come secondo parametro d'ingresso un colore, da ritornare in caso di 
     * mancata conversione della stringa primaria.
     *@param strColor Stringa contenete la descrizione di un colore in RGB (es: 255000000 == ROSSO),
     *@param ColorException Colore di "Eccezzione" se fallita la conversione della stringa primaria strColor.
     *@return Oggetto Color dalle caratteristiche richieste tramite parametro tipo String.
     **/
    private java.awt.Color ColorExtract(String strColor,java.awt.Color ColorException)
    {
        try
        { 
            int R = (Integer.valueOf(strColor.substring(0,3))).intValue();
            int G = (Integer.valueOf(strColor.substring(3,6))).intValue();
            int B = (Integer.valueOf(strColor.substring(6))).intValue();
              
            return (new java.awt.Color(R,G,B));
        }
        catch(Exception e)
        {
            return (ColorException);
        }
    }
    
    /**
     *Questo permette di referenziare dall'esterno l'oggetto jTextArea utilizzato nell'interfaccia GUI.
     *@return Oggetto di tipo javax.swing.JTextArea.
     */
    public javax.swing.JTextArea get_JTextArea()
    {
        return this.jTextArea;
    }
    
    
    private void jB_ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        System.out.println("4535434");
    }
    
    private void AddRowJTabel()
    {
        jt_ProcessiOrbix.setCursor(Cur_wait);
        
        int nRighe = 2;
        int nColonne = 8;
        Object[][] dataValues = new Object[nRighe][nColonne];
        
        int i=0;

        JLabel jl = new JLabel("Run"); 
        jl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_proc.png")));
            
        JButton jb = new JButton("STSConfigServer"); 
        jb.setBackground(new java.awt.Color(230, 230, 230));
        jb.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb.setFocusPainted(false);
        jb.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ActionPerformed(evt);
            }
        });
              
        JButton jB_status = new JButton(); 
        jB_status.setBackground(new java.awt.Color(230, 230, 230));
        jB_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        jB_status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_status.setBorderPainted(false);
        jB_status.setFocusPainted(false);
        jB_status.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_status.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_status.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_status.setPreferredSize(new java.awt.Dimension(24, 24));
        
        JButton jB_type1Param = new JButton(); 
        jB_type1Param.setBackground(new java.awt.Color(230, 230, 230));
        jB_type1Param.setText("D");
        jB_type1Param.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type1Param.setFocusPainted(false);
        jB_type1Param.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_type1Param.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_type1Param.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_type1Param.setPreferredSize(new java.awt.Dimension(24, 24));
        
        JButton jB_type2Param = new JButton(); 
        jB_type2Param.setBackground(new java.awt.Color(230, 230, 230));
        jB_type2Param.setText("D");
        jB_type2Param.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type2Param.setFocusPainted(false);
        jB_type2Param.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_type2Param.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_type2Param.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_type2Param.setPreferredSize(new java.awt.Dimension(24, 24));
        
        JLabel jL_type1ParamDesc = new JLabel("Run"); 
        jL_type1ParamDesc.setBackground(new java.awt.Color(230, 230, 230));
        jL_type1ParamDesc.setText("1 Parameter");
        jL_type1ParamDesc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_type1ParamDesc.setMaximumSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc.setPreferredSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc.setOpaque(true);
        

        dataValues[i][0] = jl;    
        dataValues[i][1] = jb;
        dataValues[i][2] = jB_status;
        dataValues[i][3] = jB_type1Param;
        dataValues[i][4] = jL_type1ParamDesc;
        dataValues[i][5] = jB_type2Param;
        dataValues[i][6] = new JLabel("1");
        dataValues[i][7] = new JLabel("1");
        
        i=1;

        JButton jB_status1 = new JButton(); 
        
        JLabel jl1 = new JLabel("Run"); 
        jl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_proc.png")));
            
        JButton jb1 = new JButton("PIPPO"); 
        jb1.setBackground(new java.awt.Color(230, 230, 230));
        jb1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jb1.setFocusPainted(false);
        jb1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        
        jB_status1.setBackground(new java.awt.Color(230, 230, 230));
        jB_status1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        jB_status1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_status1.setBorderPainted(false);
        jB_status1.setFocusPainted(false);
        jB_status1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_status1.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_status1.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_status1.setPreferredSize(new java.awt.Dimension(24, 24));
        
        JButton jB_type1Param1 = new JButton(); 
        jB_type1Param1.setBackground(new java.awt.Color(230, 230, 230));
        jB_type1Param1.setText("D");
        jB_type1Param1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type1Param1.setFocusPainted(false);
        jB_type1Param1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_type1Param1.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_type1Param1.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_type1Param1.setPreferredSize(new java.awt.Dimension(24, 24));
        
        JButton jB_type2Param1 = new JButton(); 
        jB_type2Param1.setBackground(new java.awt.Color(230, 230, 230));
        jB_type2Param1.setText("D");
        jB_type2Param1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type2Param1.setFocusPainted(false);
        jB_type2Param1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_type2Param1.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_type2Param1.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_type2Param1.setPreferredSize(new java.awt.Dimension(24, 24));
        
        JLabel jL_type1ParamDesc1 = new JLabel("Run2"); 
        jL_type1ParamDesc1.setBackground(new java.awt.Color(230, 230, 230));
        jL_type1ParamDesc1.setText("1 Parameter2");
        jL_type1ParamDesc1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_type1ParamDesc1.setMaximumSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc1.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc1.setPreferredSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc1.setOpaque(true);
        dataValues[i][0] = jl1;    
        dataValues[i][1] = jb1;
        dataValues[i][2] = jB_status1;
        dataValues[i][3] = jB_type1Param1;
        dataValues[i][4] = jL_type1ParamDesc1;
        dataValues[i][5] = jB_type2Param1;
        dataValues[i][6] = new JLabel("2");
        dataValues[i][7] = new JLabel("2");
        
        
        myTableModel.setDataValues(dataValues);
        //sorter.setTableModel(myTableModel);
        
        jScrollrows.setViewportView(jt_ProcessiOrbix);
        getContentPane().setBackground(new Color(230, 230, 230));
        jt_ProcessiOrbix.setCursor(Cur_default);
    }
    
    
    private void SetTable(javax.swing.JTable jtable)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtable.setEnabled(true);
        jtable.setIntercellSpacing(new java.awt.Dimension(5, 5));
        jtable.setRowHeight(30);
        jtable.setBackground(new java.awt.Color(230, 230, 230));
        
        JTableHeader header = jtable.getTableHeader();
        header.setPreferredSize(new Dimension(200,22));

        
        TableColumn column = null;
        for(int i=0; i<jtable.getColumnCount(); i++)
        {
            column = jtable.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
            
        }        
 
        if((jtable.isShowing())&&(jtable.isVisible()))
        {
            jtable.updateUI();
        }   
        
        for(int i=0;i<jtable.getColumnCount();i++)
        {
            jtable.getColumnModel().getColumn(i).setCellRenderer(new MyRenderer());
            jtable.getColumnModel().getColumn(i).setCellEditor(new MyEditor());
        }
    
        this.setCursor(Cur_default);
    }
    
    /************************************ Classe interna ****************************************/
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames ={ "Type",
                                        "Process Name",
                                        "Status",
                                        "",
                                        "Param 1",
                                        "",
                                        "Param 2",
                                        "Message"};
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
            return true; //nessuna cella editabile
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
            int _numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < _numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
    /************************************ FINE Classe interna ****************************************/

   
     /**
     *Il metodo avvia l'esecuzione del Thread.
     */
    public void start()
    {      
        if(V_ProcessROW == null)
            V_ProcessROW = new java.util.Vector();
        
        jB_resetAll.setEnabled(true);
        Thread_RUN = true;
        Thread_wakeup = false;
        
        th = new Thread(this);
        th.start();
        //System.out.println("START");
    }
    
    /**
     *Il metodo interrompe l'esecuzione del Thread.
     */
    public void stop()
    {
        jB_active.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ledgreen.gif")));
        
        jB_resetAll.setEnabled(false);
        jB_resetFields.setEnabled(false);
        
        Thread_wakeup = true;
        Thread_RUN = false; 

        //System.out.println("stop()");
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_north;
    private javax.swing.JLabel jL_SW;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jB_stop;
    private javax.swing.JButton jB_active;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jl_Time;
    private javax.swing.JLabel jl_time_refresh;
    private javax.swing.JScrollPane jScrollrows;
    private javax.swing.JPanel jP_south;
    private javax.swing.JScrollPane jScroll_south;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jB_resetFields;
    private javax.swing.JButton jB_resetAll;
    private javax.swing.JButton jB_all;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTF_number;
    private javax.swing.JButton jB_close;
    private javax.swing.JButton jB_analize;
    private javax.swing.JButton jb_reverse;
    // End of variables declaration//GEN-END:variables

}
