/*
 * ADAMS_JF_ListaValoriSostituzione.java
 *
 * Created on 1 agosto 2005, 12.46
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.table.*;
import javax.swing.DefaultCellEditor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_JF_ListaValoriSostituzione extends javax.swing.JDialog {

    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private int[]                                   CellDimension               = {70,70,70};
    private String[]                                nomeCampi                   = {"From","To","Shifted value"};
    private String[]                                descCampi                   = {"From","To","Shifted value"};
    private int                                     minCellDimension            = 30;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general   = null;
    private javax.swing.JTable                      jTable_general              = null;
    private Vector                                  vElements                   = null;
    
    private String  linkNestedTable     = "LISTA_VALORI_SOSTITUZ";
    private String  tableConfig         = "tab_config";
    private String  nestedTable         = "TAB_VALORI_SOSTITUZIONE";
    private String  nestedTableType     = "TAB_VALORI_SOSTITUZIONE";
    private String  typeConfig          = "General";
    private int     posElemento         = -1;
    
    /** Creates new form ADAMS_JF_ListaValoriSostituzione */
    /*public ADAMS_JF_ListaValoriSostituzione(String Title,boolean modal) {
        super((java.awt.Frame)null,modal);
        
        javax.swing.JDialog j = new javax.swing.JDialog();
        
        initComponents();
        Vector vElements = new Vector();
        
        jT_from.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,8));
        jT_to.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,8));
        jT_shift.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,8));

        setCenteredFrame(390,380);
        setTitle(Title);
        setFont();
        setCursorWidget();
        
        setTable();
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBackground(new java.awt.Color(255,255,255));        
    }*/
    
    public ADAMS_JF_ListaValoriSostituzione(javax.swing.JFrame Parent,String Title,boolean modal) {
        super((java.awt.Frame)Parent,modal);
        
        javax.swing.JDialog j = new javax.swing.JDialog();
        
        initComponents();
        Vector vElements = new Vector();
        
        jT_from.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,8));
        jT_to.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,8));
        jT_shift.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,8));

        setCenteredFrame(390,380);
        setTitle(Title);
        setFont();
        setCursorWidget();
        
        setTable();
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBackground(new java.awt.Color(255,255,255));        
    }
    
    
    public Vector getElements()
    {
        return vElements;
    }
    
    public void setElements(Vector v)
    {
        vElements = v;
        updateTable();
    }
    
    public void setNameTable(String tableConfig,String typeConfig,int posElemento,String linkNestedTable,String nestedTable,String nestedTableType)
    {
        this.linkNestedTable        = linkNestedTable;
        this.tableConfig            = tableConfig;
        this.nestedTable            = nestedTable;
        this.nestedTableType        = nestedTableType;
        this.typeConfig             = typeConfig;
        this.posElemento            = posElemento; 
    }
    
    public boolean setNomeCampi(String[] newNomeCampi)
    {
        if(newNomeCampi.length<2)
        {
            return false;
        }
        
        for(int i=0;i<2;i++)
        {
            nomeCampi[i]=newNomeCampi[i];
        }
        return true;
    }
    
    public boolean setDescCampi(String[] newDescCampi)
    {
        if(newDescCampi.length<2)
        {
            return false;
        }
        
        for(int i=0;i<2;i++)
        {
            descCampi[i]=newDescCampi[i];
        }
        return true;
    }
    
    public boolean setDeimension(int[] newD)
    {
        if(newD.length<2)
        {
            return false;
        }
        
        for(int i=0;i<2;i++)
        {
            CellDimension[i]=newD[i];
        }
        return true;
    }

    
    public void setFont()
    {
        jT_from.setFont(ADAMS_GlobalParam.font_B11);
        jT_to.setFont(ADAMS_GlobalParam.font_B11);
        jT_shift.setFont(ADAMS_GlobalParam.font_B11);
        jL_from.setFont(ADAMS_GlobalParam.font_B11);
        jL_to.setFont(ADAMS_GlobalParam.font_B11);
        jL_shift.setFont(ADAMS_GlobalParam.font_B11);
    }

    public void setCursorWidget()
    {
        jB_cancel.setCursor(Cur_hand);
        jR_add.setCursor(Cur_hand);
        jR_mod.setCursor(Cur_hand);
        jR_del.setCursor(Cur_hand);
        jT_from.setCursor(Cur_hand);
        jT_to.setCursor(Cur_hand);
        jT_shift.setCursor(Cur_hand);
    }
    
    public void setTable()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general = new javax.swing.JTable();
        jTable_general.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
          "From", "To", "Shifted value"
        }
        ) {
          Class[] types = new Class [] {
              java.lang.String.class, java.lang.String.class, java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false, false, false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general.setAutoCreateColumnsFromModel(false);
        jTable_general.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general.setRowHeight(20);
        jTable_general.setRowMargin(2);
        jTable_general.setSelectionBackground(java.awt.Color.green);
        jTable_general.setVerifyInputWhenFocusTarget(false);
        jTable_general.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_generalMouseReleased(evt);
          }
        });

        jScrollPane2.setViewportView(jTable_general);

        jTable_general.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jTable_general.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general.getColumnCount(); i++)
        {
            column = jTable_general.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general = (javax.swing.table.DefaultTableModel)jTable_general.getModel();
        
        this.setCursor(Cur_default);
    }
    
    private void updateTable()
    {
        String appoStr  = "";
        Integer appoInt = null;
        
        if(vElements==null)
                return;
        
        jTable_general.clearSelection();
        defaultTableModel_general.getDataVector().removeAllElements();
        
        for(int i=0;i<vElements.size();i++)
        {
            
            String elementoRiga = (String)vElements.elementAt(i);
            
            Vector row=new Vector();
            
            appoStr=elementoRiga.substring(8,16);
            appoInt=new Integer(appoStr);
            row.addElement(""+appoInt);
            
            appoStr=elementoRiga.substring(16,24);
            appoInt=new Integer(appoStr);
            row.addElement(""+appoInt); 
            
            appoStr=elementoRiga.substring(0,8);
            appoInt=new Integer(appoStr);
            row.addElement(""+appoInt);
   
            defaultTableModel_general.addRow(row);
        }
        
        jTable_general.validate();
        jTable_general.repaint();
        
        jScrollPane2.setViewportView(jTable_general);
    }

    private boolean isPresent(String val, int col)
    {
        int rowCount=jTable_general.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
                String str=jTable_general.getValueAt(i,col).toString();
                if(str.equals(val))
                {
                    return true;
                }
        }
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jB_cancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jR_add = new javax.swing.JButton();
        jR_mod = new javax.swing.JButton();
        jR_del = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jL_from = new javax.swing.JLabel();
        jT_from = new javax.swing.JTextField();
        jL_to = new javax.swing.JLabel();
        jT_to = new javax.swing.JTextField();
        jL_shift = new javax.swing.JLabel();
        jT_shift = new javax.swing.JTextField();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jLabel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ICON");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 30;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jLabel1, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 200;
        getContentPane().add(jScrollPane2, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.BorderLayout());
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setMinimumSize(new java.awt.Dimension(10, 20));
        jPanel3.setPreferredSize(new java.awt.Dimension(10, 20));
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_cancel.setToolTipText("Close this windows");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_addActionPerformed(evt);
            }
        });
        
        jPanel3.add(jB_cancel, java.awt.BorderLayout.EAST);
        
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(230, 38));
        jPanel4.setPreferredSize(new java.awt.Dimension(230, 38));
        jR_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add.jpg")));
        jR_add.setToolTipText("Insert new element.");
        jR_add.setBorderPainted(false);
        jR_add.setContentAreaFilled(false);
        jR_add.setFocusPainted(false);
        jR_add.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jR_add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_press.jpg")));
        jR_add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_over.jpg")));
        jR_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_addActionPerformed(evt);
            }
        });
        
        jPanel4.add(jR_add);
        
        jR_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify.jpg")));
        jR_mod.setToolTipText("Modify element.");
        jR_mod.setBorderPainted(false);
        jR_mod.setContentAreaFilled(false);
        jR_mod.setFocusPainted(false);
        jR_mod.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jR_mod.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_press.jpg")));
        jR_mod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_over.jpg")));
        jR_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_addActionPerformed(evt);
            }
        });
        
        jPanel4.add(jR_mod);
        
        jR_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete.jpg")));
        jR_del.setToolTipText("Delete element.");
        jR_del.setBorderPainted(false);
        jR_del.setContentAreaFilled(false);
        jR_del.setFocusPainted(false);
        jR_del.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jR_del.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_press.jpg")));
        jR_del.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_over.jpg")));
        jR_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_addActionPerformed(evt);
            }
        });
        
        jPanel4.add(jR_del);
        
        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);
        
        jPanel5.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jPanel5.setBorder(new javax.swing.border.EtchedBorder());
        jL_from.setText("From");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel5.add(jL_from, gridBagConstraints2);
        
        jT_from.setText("       ");
        jT_from.setToolTipText("Identificativo univoco del'help");
        jT_from.setMinimumSize(new java.awt.Dimension(45, 19));
        jT_from.setPreferredSize(new java.awt.Dimension(45, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 8);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jT_from, gridBagConstraints2);
        
        jL_to.setText("To");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(0, 8, 0, 3);
        jPanel5.add(jL_to, gridBagConstraints2);
        
        jT_to.setText("                ");
        jT_to.setToolTipText("Descrizione del tipo di help.");
        jT_to.setMinimumSize(new java.awt.Dimension(45, 19));
        jT_to.setPreferredSize(new java.awt.Dimension(45, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 8);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jT_to, gridBagConstraints2);
        
        jL_shift.setText("Shifted value");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(0, 8, 0, 3);
        jPanel5.add(jL_shift, gridBagConstraints2);
        
        jT_shift.setText("                ");
        jT_shift.setToolTipText("Descrizione del tipo di help.");
        jT_shift.setMinimumSize(new java.awt.Dimension(45, 19));
        jT_shift.setPreferredSize(new java.awt.Dimension(45, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 8);
        gridBagConstraints2.weightx = 1.0;
        jPanel5.add(jT_shift, gridBagConstraints2);
        
        jPanel3.add(jPanel5, java.awt.BorderLayout.NORTH);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 50;
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jR_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_addActionPerformed
        // Add your handling code here:
        String from="",to="",shift="";
        
        int rowSelected=jTable_general.getSelectedRow();

        if (evt.getSource() == jB_cancel) //insert new element
        {
            closeFrame();
        }else if (evt.getSource() == jR_add) //insert new element
        {
            from = jT_from.getText();
            if(from.equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[0]+" is empty.","ERROR",1);
                return;
            }
            
            to = jT_to.getText();
            if(to.equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[1]+" is empty.","ERROR",1);
                return;
            }
            
            shift = jT_shift.getText();
            if(shift.equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[2]+" is empty.","ERROR",1);
                return;
            }

            /*if(isPresent(from,0))
            {
                ADAMS_GlobalParam.optionPanel("ERROR: Il campo  "+descCampi[0]+" è presente.","ERROR",1);
                return;
            }

            if(isPresent(to,1))
            {
                ADAMS_GlobalParam.optionPanel("ERROR: Il campo "+descCampi[1]+" è presente.","ERROR",1);
                return;
            }
            
            if(isPresent(shift,1))
            {
                ADAMS_GlobalParam.optionPanel("ERROR: Il campo "+descCampi[1]+" è presente.","ERROR",1);
                return;
            }*/
           
            addElemento(from,to,shift);

            updateTable();
            
            jT_from.setText("");
            jT_to.setText("");
            jT_shift.setText("");
            
        }else if (evt.getSource() == jR_del)
        {
            if(rowSelected==-1)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: No row selectd.","ERROR",1);
                return;
            }
            
            from = jT_from.getText();
            to = jT_to.getText();
            shift = jT_shift.getText();
                
            if(DEBUG)
            {
		System.out.println("Row seleted="+rowSelected);
        	System.out.println("del from="+jTable_general.getValueAt(rowSelected,0));
        	System.out.println("del to="+jTable_general.getValueAt(rowSelected,1));
                System.out.println("del shift="+jTable_general.getValueAt(rowSelected,2));
            }
            
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"Delete element "+from+"-"+to+"-"+shift+".  Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            
            if(Yes_No == 1)
            {    
                cancellaElemento(from,to,shift);
              

                jT_from.setText("");
                jT_to.setText("");
                jT_shift.setText("");
                
                updateTable();
            }
        }else if (evt.getSource() == jR_mod)
        {
            if(rowSelected==-1)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: No row selectd.","ERROR",1);
                return;
            }  
            
            from=(String)jTable_general.getValueAt(rowSelected,0);
            to=(String)jTable_general.getValueAt(rowSelected,1);
            shift=(String)jTable_general.getValueAt(rowSelected,2);
            
            if(DEBUG)
            {
		System.out.println("Row seleted="+rowSelected);
        	System.out.println("mod from="+from);
        	System.out.println("mod to="+to);
                System.out.println("mod shift="+shift);
            }
            
            
            
            cancellaElemento(from,to,shift);
            
                
            from = jT_from.getText();
            to = jT_to.getText();
            shift = jT_shift.getText();
            
            if(DEBUG)
            {
		System.out.println("Row seleted="+rowSelected);
        	System.out.println("in from="+from);
        	System.out.println("in to="+to);
                System.out.println("in shift="+shift);
            }
            
            
            addElemento(from,to,shift);

           
            updateTable();
        }
    }//GEN-LAST:event_jR_addActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm
    
    private void jTable_generalMouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here:
        int rowSelected=jTable_general.getSelectedRow();
        if(rowSelected==-1)
            return;
        
	if(DEBUG)
	{
		System.out.println("Row seleted="+rowSelected);
        	System.out.println("From="+jTable_general.getValueAt(rowSelected,0));
        	System.out.println("To="+jTable_general.getValueAt(rowSelected,1));
                System.out.println("Shifted value="+jTable_general.getValueAt(rowSelected,2));
	}
	jT_from.setText(""+jTable_general.getValueAt(rowSelected,0));
        jT_to.setText(""+jTable_general.getValueAt(rowSelected,1));
        jT_shift.setText(""+jTable_general.getValueAt(rowSelected,2));
    }
    
    private void closeFrame()
    {
        setVisible(false);
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
    
    private void addElemento(String from,String to,String shift)
    {
        
        //vElements.addElement(fillString(from,8)+fillString(to,8)+fillString(shift,8));
        vElements.addElement(fillString(shift,8)+fillString(from,8)+fillString(to,8));
    }
    
    private void cancellaElemento(String from,String to,String shift)
    {
        int trovato=-1;
        
        for(int i=0;i<vElements.size();i++)
        {
            String elelemnto=(String)vElements.elementAt(i);
            if(elelemnto.equals(fillString(shift,8)+fillString(from,8)+fillString(to,8)))
            {
                trovato=i;
                break;
            }
        }
        
     
        if(trovato!=-1)
           vElements.removeElementAt(trovato);
    }
    
    private String fillString(String str, int numChar)
    {
        if(str.length()>numChar)
            return "";
        
        String appo="";
        for (int i=0;i<numChar-str.length();i++)
        {
            appo=appo+"0";
        }
        appo=appo+str;
        
        return appo;
    }

    public void setEnableButtons(boolean f)
    {
        jR_add.setEnabled(f);
        jR_mod.setEnabled(f);
        jR_del.setEnabled(f);
        jT_from.setEnabled(f);
        jT_to.setEnabled(f);
        jT_shift.setEnabled(f);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jR_add;
    private javax.swing.JButton jR_mod;
    private javax.swing.JButton jR_del;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jL_from;
    private javax.swing.JTextField jT_from;
    private javax.swing.JLabel jL_to;
    private javax.swing.JTextField jT_to;
    private javax.swing.JLabel jL_shift;
    private javax.swing.JTextField jT_shift;
    // End of variables declaration//GEN-END:variables

}



