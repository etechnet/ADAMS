/*
 * ADAMS_JF_ConfigHelp.java
 *
 * Created on 14 luglio 2005, 13.26
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

public class ADAMS_JF_ConfigHelp extends javax.swing.JFrame {

    /** Creates new form ADAMS_JF_ConfigHelp */
    private boolean                                 DEBUG                       = false;
    private ADAMS_JF_Wizard                          localParent                 = null;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private int[]                                   CellDimension               = {30,200};
    private int                                     minCellDimension            = 30;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general   = null;
    private javax.swing.JTable                      jTable_general              = null;
    private Vector                                  vID                         = new Vector();

    public ADAMS_JF_ConfigHelp(ADAMS_JF_Wizard Parent,String Title,int Modality,String config_NAME_ALIAS) {
        this.localParent = Parent;
        
        initComponents();
        
        jT_id.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,3));
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));
        
        setCenteredFrame(330,400);
        setTitle(Title);
        setCursorWidget();
        setTable();
        setFont();

        for(int i=0;i<ADAMS_GlobalParam.type_help.length;i++)
        {
            jC_type.addItem(ADAMS_GlobalParam.type_help[i]);
        }
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBackground(new java.awt.Color(255,255,255));
        
        show_JF_LOCK();
        
        
        
        /*ADAMS_JF_ListaValoriSostituzione appo=new ADAMS_JF_ListaValoriSostituzione("Lista Valori sostituzione",true);
        Vector v=new Vector();
        v.addElement("000010000200005");
        v.addElement("000020000300006");
        v.addElement("000030000400007");
        appo.setElements(v);
        appo.show();
        Vector v1=appo.getElements();
        for(int i=0;i<v1.size();i++)
            System.out.println((String)v1.elementAt(i));*/
         
        /*ADAMS_JF_ListValue appo=new ADAMS_JF_ListValue(localParent,"Lista Valori",1);
        appo.setNameTable("tab_config","ProvaLuca",201,"LISTA_VALORI","TAB_VALORI_ELEMENTO","TAB_VALORI_E");
        String[] nomeCampi = {"COD_VALORE","DESC_VALORE"};
        String[] descCampi = {"Valore","Descrizione"};
        appo.setNomeCampi(nomeCampi);
        appo.setDescCampi(descCampi);
        appo.updateTable();
        appo.show();*/
        
        /*VectorHelp appo=new VectorHelp(ADAMS_GlobalParam.ALL_CONF_forhelp,2);
        appo.stampaAll();
        System.out.println("Value="+appo.getValue(1));
        System.out.println("Desc="+appo.getDesc(1));*/
    }

    public void show_JF_LOCK()
    {
        jR_add.setEnabled(false);
        jR_mod.setEnabled(false);
        jR_del.setEnabled(false);       
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == true)
        {
            jR_add.setEnabled(true);
            jR_mod.setEnabled(true);
            jR_del.setEnabled(true);
        }
        //show();
        this.setVisible(true);
    }
    
    public void setDescFilter(int numChar)
    {
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,numChar));
    }
    
    public void updateTable(int idHelp)
    {
        jTable_general.clearSelection();
        vID.removeAllElements();
        defaultTableModel_general.getDataVector().removeAllElements();
        
        String sel_ConfigNtm = "select ID,HELPVALUE,DESCRIPTION from tab_help where IDHELP="+idHelp;
                              
        if(DEBUG)
            System.out.println("sel ----> "+sel_ConfigNtm);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {

                    if(DEBUG)
                    {
                        System.out.println("HELPVALUE ->"+rs.getInt("HELPVALUE"));
                        System.out.println("DESCRIPTION ->"+rs.getString("DESCRIPTION"));
                        System.out.println();
                    }
                    
                    Vector row=new Vector();
                    row.addElement(new Integer(rs.getInt("HELPVALUE")));
                    row.addElement(rs.getString("DESCRIPTION"));
                    
                    defaultTableModel_general.addRow(row);
                    vID.addElement(new Integer(rs.getInt("ID")));
                }
            }catch (Exception ex) 
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
            System.out.println("(ADAMS_JF_ConfigHelp-updateTable(---)) SQL Operation " + exc.toString());
        }
        
        jTable_general.validate();
        jTable_general.repaint();        
        jScrollPane2.setViewportView(jTable_general);
        
    }
    
    public void setTable()
    {    
        
        jTable_general = new javax.swing.JTable();
        jTable_general.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
          "Value", "Description"}
        ) {
          Class[] types = new Class [] {
              java.lang.Integer.class, java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false, false
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
        
        this.setCursor(Cur_wait);

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
        
        this.setCursor(Cur_default);
        
        defaultTableModel_general = (javax.swing.table.DefaultTableModel)jTable_general.getModel();
    }
    
    public void setFont()
    {
        jL_1.setFont(ADAMS_GlobalParam.font_B11);
        jC_type.setFont(ADAMS_GlobalParam.font_B11);
        jT_id.setFont(ADAMS_GlobalParam.font_B11);
        jT_desc.setFont(ADAMS_GlobalParam.font_B11);
        jL_id.setFont(ADAMS_GlobalParam.font_B11);
        jL_desc.setFont(ADAMS_GlobalParam.font_B11);
    }
    
    public void setCursorWidget()
    {
        jB_cancel.setCursor(Cur_hand);
        jC_type.setCursor(Cur_hand);
        jR_add.setCursor(Cur_hand);
        jR_mod.setCursor(Cur_hand);
        jR_del.setCursor(Cur_hand);
        jT_id.setCursor(Cur_hand);
        jT_desc.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_button = new javax.swing.JPanel();
        jB_cancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jR_add = new javax.swing.JButton();
        jR_mod = new javax.swing.JButton();
        jR_del = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jL_id = new javax.swing.JLabel();
        jT_id = new javax.swing.JTextField();
        jL_desc = new javax.swing.JLabel();
        jT_desc = new javax.swing.JTextField();
        jL_Icon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jL_1 = new javax.swing.JLabel();
        jC_type = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jP_button.setLayout(new java.awt.BorderLayout());
        
        jP_button.setBackground(new java.awt.Color(230, 230, 230));
        jP_button.setMinimumSize(new java.awt.Dimension(10, 20));
        jP_button.setPreferredSize(new java.awt.Dimension(10, 20));
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
                jB_cancelActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_cancel, java.awt.BorderLayout.EAST);
        
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(230, 38));
        jPanel2.setPreferredSize(new java.awt.Dimension(230, 38));
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
        
        jPanel2.add(jR_add);
        
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
        
        jPanel2.add(jR_mod);
        
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
        
        jPanel2.add(jR_del);
        
        jP_button.add(jPanel2, java.awt.BorderLayout.CENTER);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.EtchedBorder());
        jL_id.setText("Value");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel3.add(jL_id, gridBagConstraints2);
        
        jT_id.setText("       ");
        jT_id.setToolTipText("Identificativo univoco del'help");
        jT_id.setMinimumSize(new java.awt.Dimension(26, 19));
        jT_id.setPreferredSize(new java.awt.Dimension(26, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 0.5;
        jPanel3.add(jT_id, gridBagConstraints2);
        
        jL_desc.setText("Description");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 10, 0, 3);
        jPanel3.add(jL_desc, gridBagConstraints2);
        
        jT_desc.setText("                ");
        jT_desc.setToolTipText("Descrizione del tipo di help.");
        jT_desc.setMinimumSize(new java.awt.Dimension(110, 19));
        jT_desc.setPreferredSize(new java.awt.Dimension(110, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 3);
        gridBagConstraints2.weightx = 1.0;
        jPanel3.add(jT_desc, gridBagConstraints2);
        
        jP_button.add(jPanel3, java.awt.BorderLayout.NORTH);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 50;
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jP_button, gridBagConstraints1);
        
        jL_Icon.setBackground(new java.awt.Color(230, 230, 230));
        jL_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/title_help.gif")));
        jL_Icon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Icon.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_Icon, gridBagConstraints1);
        
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(10, 20));
        jPanel1.setPreferredSize(new java.awt.Dimension(10, 20));
        jL_1.setText("Select help category      ");
        jPanel1.add(jL_1);
        
        jC_type.setBackground(java.awt.Color.white);
        jC_type.setToolTipText("List of type help.");
        jC_type.setMinimumSize(new java.awt.Dimension(60, 24));
        jC_type.setPreferredSize(new java.awt.Dimension(120, 24));
        jC_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jC_typeActionPerformed(evt);
            }
        });
        
        jPanel1.add(jC_type);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 28;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 350;
        gridBagConstraints1.ipady = 160;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        getContentPane().add(jScrollPane2, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jTable_generalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_generalMouseReleased
        // Add your handling code here:
        int rowSelected=jTable_general.getSelectedRow();
        if(rowSelected==-1)
            return;
        
	if(DEBUG)
	{
		System.out.println("Row seleted="+rowSelected);
        	System.out.println("id="+jTable_general.getValueAt(rowSelected,0));
        	System.out.println("desc="+jTable_general.getValueAt(rowSelected,1));
	}
	jT_id.setText(""+jTable_general.getValueAt(rowSelected,0));
        jT_desc.setText(""+jTable_general.getValueAt(rowSelected,1));
        
    }//GEN-LAST:event_jTable_generalMouseReleased

    private void jR_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_addActionPerformed
        // Add your handling code here:
        
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
            return;

        int id=-1;
        String desc="";
        int rowSelected=jTable_general.getSelectedRow();
        
        if (evt.getSource() == jR_add) //insert new element
        {

            if(jT_id.getText().equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field ID is empty.","ERROR",1);
                return;
            }

            try
            {
                id = Integer.valueOf(jT_id.getText()).intValue();
            }catch(NumberFormatException e)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Number Format Exception.","ERROR",1);
                return;
            }

            desc = jT_desc.getText();

            if(desc.equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field Description is empty.","ERROR",1);
                return;
            }
        
            if(isPresent(desc,1))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field Description already present.","ERROR",1);
                return;
            }
            
            if(isPresent(jT_id.getText(),0))
            {
                if(isPresent(desc,1))
                {
                    ADAMS_GlobalParam.optionPanel(this,"ERROR: Field ID-Description already present.","ERROR",1);
                    return;
                }
                //ADAMS_GlobalParam.optionPanel(this,"ERROR: Il campo ID è presente.","ERROR",1);
                //return;
            }

            /*if(isPresent(desc,1))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Il campo Descrizione è presente.","ERROR",1);
                return;
            }*/
            String strSelect="insert intotab_help values(ID_TAB_HELP_seq.nextVal,'"+ADAMS_GlobalParam.ALL_CONF_forhelp+"',"+jC_type.getSelectedIndex()+","+id+",'"+desc+"')";
            if(DEBUG)
            {
                System.out.println("strSelect="+strSelect);
            }
            
            Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
            
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JF_ConfigHelp-1) SQL Operation " + exc.toString());
            }
            
            
            updateTable(jC_type.getSelectedIndex());
            jT_id.setText("");
            jT_desc.setText("");
            
        }else if (evt.getSource() == jR_mod)
        {
            if(rowSelected==-1)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: No row selectd.","ERROR",1);
                return;
            }
            if(jT_id.getText().equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field ID is empty.","ERROR",1);
                return;
            }

            try
            {
                id = Integer.valueOf(jT_id.getText()).intValue();
            }catch(NumberFormatException e)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Number Format Exception.","ERROR",1);
                return;
            }

            desc = jT_desc.getText();

            if(desc.equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field Description is empty.","ERROR",1);
                return;
            }
            
            int idH=((Integer)vID.elementAt(rowSelected)).intValue();
            //String strSelect="update tab_help set HELPVALUE="+jT_id.getText()+",DESCRIPTION='"+desc+"' where IDHELP="+jC_type.getSelectedIndex()+" AND HELPVALUE="+jTable_general.getValueAt(rowSelected,0);
            String strSelect="update tab_help set HELPVALUE="+jT_id.getText()+",DESCRIPTION='"+desc+"' where ID="+idH;
            if(DEBUG)
            {
                System.out.println("strSelect="+strSelect);
            }
            
            Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
           
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JF_ConfigHelp-2) SQL Operation " + exc.toString());
            }
            
            updateTable(jC_type.getSelectedIndex());
            
        }else if (evt.getSource() == jR_del) //del element
        {
            
            if(rowSelected==-1)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: No row selectd.","ERROR",1);
                return;
            }
                
            if(DEBUG)
            {
		System.out.println("Row seleted="+rowSelected);
        	System.out.println("del id="+jTable_general.getValueAt(rowSelected,0));
        	System.out.println("del desc="+jTable_general.getValueAt(rowSelected,1));
            }
            
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"Delete element "+jTable_general.getValueAt(rowSelected,0)+"-"+jTable_general.getValueAt(rowSelected,1)+".  Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            
            if(Yes_No == 1)
            {    
                String strSelect="delete from tab_help where IDHELP="+jC_type.getSelectedIndex()+" AND HELPVALUE="+jTable_general.getValueAt(rowSelected,0)+ " AND DESCRIPTION='"+jTable_general.getValueAt(rowSelected,1)+"'";
                if(DEBUG)
                {
                    System.out.println("strSelect="+strSelect);
                }

                Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
                ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
                try
                {
                    SQLStatement.close();
                }
                catch(java.sql.SQLException exc) 
                {
                    System.out.println("(ADAMS_JF_ConfigHelp-2) SQL Operation " + exc.toString());
                }

                updateTable(jC_type.getSelectedIndex());
                jT_id.setText("");
                jT_desc.setText("");
                
            }
            
        }
        
        if(DEBUG)
        {
               System.out.println("ID="+id);
               System.out.println("DESC="+desc);
        }
    }//GEN-LAST:event_jR_addActionPerformed

    private void jC_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_typeActionPerformed
        // Add your handling code here:
        updateTable(jC_type.getSelectedIndex());
        jT_id.setText("");
        jT_desc.setText("");
    }//GEN-LAST:event_jC_typeActionPerformed

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        // Add your handling code here:
        closeFrame();
    }//GEN-LAST:event_jB_cancelActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame()
    {
        dispose();
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jR_add;
    private javax.swing.JButton jR_mod;
    private javax.swing.JButton jR_del;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jL_id;
    private javax.swing.JTextField jT_id;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JTextField jT_desc;
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jL_1;
    private javax.swing.JComboBox jC_type;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
