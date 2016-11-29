/*
 * ADAMS_JF_ListValue.java
 *
 * Created on 20 luglio 2005, 12.25
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

public class ADAMS_JF_ListValue extends javax.swing.JDialog {

    /** Creates new form ADAMS_JF_ListValue */
    
    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private int[]                                   CellDimension               = {30,200};
    private String[]                                nomeCampi                   = {"COD_VALORE","DESC_VALORE"};
    private String[]                                descCampi                   = {"Value","Description"};
    private int                                     minCellDimension            = 30;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general   = null;
    private javax.swing.JTable                      jTable_general              = null;
    
    Vector vElements;
    
    private String  linkNestedTable     = "LISTA_VALORI";
    private String  tableConfig         = "tab_config";
    private String  nestedTable         = "TAB_VALORI_ELEMENTO";
    private String  nestedTableType     = "TAB_VALORI_E";
    private String  typeConfig          = "ProvaLuca";
    private int     posElemento         = 201;
    
    public ADAMS_JF_ListValue(String Title,boolean modal) {
        super((java.awt.Frame)null,modal);
        
        javax.swing.JDialog j = new javax.swing.JDialog();
        
        initComponents();
        Vector vElements = new Vector();
        
        jT_id.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,3));
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));

        setCenteredFrame(330,400);
        setTitle(Title);
        setFont();
        setCursorWidget();
        
        setTable();
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBackground(new java.awt.Color(255,255,255));
       
    }
    
    public void setDescFilter(int numChar)
    {
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,numChar));
    }
    
    public void setEnableButtons(boolean f)
    {
        jR_add.setEnabled(f);
        jR_mod.setEnabled(f);
        jR_del.setEnabled(f);
        jT_id.setEnabled(f);
        jT_desc.setEnabled(f);
    }
    
    public Vector getElements()
    {
        //System.out.println("getElements() vector size "+vElements.size());
        return vElements;
    }
    
    public void setElements(Vector v)
    {
        vElements = v;
        //System.out.println("setElements(v) vector size "+vElements.size());
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
        jT_id.setFont(ADAMS_GlobalParam.font_B11);
        jT_desc.setFont(ADAMS_GlobalParam.font_B11);
        jL_id.setFont(ADAMS_GlobalParam.font_B11);
        jL_desc.setFont(ADAMS_GlobalParam.font_B11);
    }

    public void setCursorWidget()
    {
        jB_cancel.setCursor(Cur_hand);
        jR_add.setCursor(Cur_hand);
        jR_mod.setCursor(Cur_hand);
        jR_del.setCursor(Cur_hand);
        jT_id.setCursor(Cur_hand);
        jT_desc.setCursor(Cur_hand);
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
          "Value", "Description"
        }
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
        jTable_general.clearSelection();
        defaultTableModel_general.getDataVector().removeAllElements();
        
        for(int i=0;i<vElements.size();i++)
        {
            
            ADAMS_TAB_VALORI_ELEMENTO elementoRiga = (ADAMS_TAB_VALORI_ELEMENTO)vElements.elementAt(i);
            
            Vector row=new Vector();
            row.addElement(new Integer(elementoRiga.get_COD_VALORE()));
            row.addElement(new String(elementoRiga.get_DESC_VALORE()));
            
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
        jL_Icon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jB_cancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jR_add = new javax.swing.JButton();
        jR_mod = new javax.swing.JButton();
        jR_del = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jL_id = new javax.swing.JLabel();
        jT_id = new javax.swing.JTextField();
        jL_desc = new javax.swing.JLabel();
        jT_desc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jL_Icon.setBackground(new java.awt.Color(230, 230, 230));
        jL_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Icon.setText("ICON");
        jL_Icon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Icon.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 30;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_Icon, gridBagConstraints1);
        
        jPanel2.setLayout(new java.awt.BorderLayout());
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(10, 20));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 20));
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
        
        jPanel2.add(jB_cancel, java.awt.BorderLayout.EAST);
        
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setMinimumSize(new java.awt.Dimension(230, 38));
        jPanel3.setPreferredSize(new java.awt.Dimension(230, 38));
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
        
        jPanel3.add(jR_add);
        
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
        
        jPanel3.add(jR_mod);
        
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
        
        jPanel3.add(jR_del);
        
        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setBorder(new javax.swing.border.EtchedBorder());
        jL_id.setText("Value");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jL_id, gridBagConstraints2);
        
        jT_id.setText("       ");
        jT_id.setToolTipText("Identificativo univoco del'help");
        jT_id.setMinimumSize(new java.awt.Dimension(26, 19));
        jT_id.setPreferredSize(new java.awt.Dimension(26, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 0);
        gridBagConstraints2.weightx = 0.5;
        jPanel4.add(jT_id, gridBagConstraints2);
        
        jL_desc.setText("Description");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel4.add(jL_desc, gridBagConstraints2);
        
        jT_desc.setText("                ");
        jT_desc.setToolTipText("Descrizione del tipo di help.");
        jT_desc.setMinimumSize(new java.awt.Dimension(110, 19));
        jT_desc.setPreferredSize(new java.awt.Dimension(110, 19));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 3, 0, 3);
        gridBagConstraints2.weightx = 1.0;
        jPanel4.add(jT_desc, gridBagConstraints2);
        
        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 50;
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 370;
        gridBagConstraints1.ipady = 200;
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
        int id=-1;
        String desc="";
        
        int rowSelected=jTable_general.getSelectedRow();

        if (evt.getSource() == jB_cancel) //insert new element
        {
            closeFrame();
        }else if (evt.getSource() == jR_add) //insert new element
        {

            if(jT_id.getText().equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[0]+" is empty.","ERROR",1);
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
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[1]+" is empty.","ERROR",1);
                return;
            }


            if(isPresent(jT_id.getText(),0))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[0]+" is empty.","ERROR",1);
                return;
            }

            if(isPresent(desc,1))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[1]+" is empty.","ERROR",1);
                return;
            }
            
            //String strSelect=creaSelectDaTable(id,desc,jTable_general.getRowCount()+1);
            
            addElemento(id,desc);
            
            //ResultSet rs = ADAMS_GlobalParam.connect_Oracle.Query_rs(strSelect);
            
            updateTable();
            
            jT_id.setText("");
            jT_desc.setText("");
            
        }else if (evt.getSource() == jR_del)
        {
            if(rowSelected==-1)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: No row selectd.","ERROR",1);
                return;
            }
            
            try
            {
                id = Integer.valueOf(jT_id.getText()).intValue();
            }catch(NumberFormatException e)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: NumberFormatException.","ERROR",1);
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
                cancellaElemento(id,desc);
                
                //defaultTableModel_general.removeRow(rowSelected);
               
                //String strSelect=creaSelectDaTable(id,desc,jTable_general.getRowCount());

                //ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_rs(strSelect);

                updateTable();
                jT_id.setText("");
                jT_desc.setText("");
            }
        }else if (evt.getSource() == jR_mod)
        {
            if(rowSelected==-1)
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: No row selectd.","ERROR",1);
                return;
            }  
            
            if(jT_id.getText().equals(""))
            {
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[0]+" is empty.","ERROR",1);
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
                ADAMS_GlobalParam.optionPanel(this,"ERROR: Field "+descCampi[1]+" is empty.","ERROR",1);
                return;
            }
            
            if(DEBUG)
            {
		System.out.println("2) Row seleted="+rowSelected);
        	System.out.println("2) del id="+jTable_general.getValueAt(rowSelected,0));
        	System.out.println("2) del desc="+jTable_general.getValueAt(rowSelected,1));
            }
            
            //defaultTableModel_general.setValueAt(new Integer(id),rowSelected,0);
            //defaultTableModel_general.setValueAt(desc,rowSelected,1);
            
            //String strSelect=creaSelectDaTable(id,desc,jTable_general.getRowCount());
            Integer idNew=Integer.valueOf(jTable_general.getValueAt(rowSelected,0).toString());
            int id_c=idNew.intValue();
            String str_c=(String)jTable_general.getValueAt(rowSelected,1);
            
            if(DEBUG)
            {
		System.out.println("2) per il canc="+rowSelected);
        	System.out.println("2) del id="+id_c);
        	System.out.println("2) del desc="+str_c);
            }
            cancellaElemento(id_c,str_c);
            addElemento(id,desc);
            //ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_rs(strSelect);
           
            updateTable();
        }
    }//GEN-LAST:event_jR_addActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

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
    
    private void addElemento(int id,String desc)
    {
        vElements.addElement(new ADAMS_TAB_VALORI_ELEMENTO(id,desc));
    }
    
    private void cancellaElemento(int id,String desc)
    {
        int trovato=-1;
        
        for(int i=0;i<vElements.size();i++)
        {
            int appoId=((ADAMS_TAB_VALORI_ELEMENTO)vElements.elementAt(i)).get_COD_VALORE();
            //System.out.println("id="+id+" appoid="+appoId);
            if(appoId==id)
            {
                trovato=i;
                break;
            }
        }
        
        //System.out.println("trovato"+trovato);
            if(trovato!=-1)
                vElements.removeElementAt(trovato);
    }
    
    /*public String creaSelectDaTable()
    {

	int rowCount=vElements.size();

        String strSelect="update "+ tableConfig + " set "+linkNestedTable+"="+nestedTable+"(";
        for(int i=0;i<rowCount;i++)
        {
            Integer idNew    = Integer.valueOf(jTable_general.getValueAt(i,0).toString());
            String  descNew  = jTable_general.getValueAt(i,1).toString();
            
            if(i!=rowCount-1)
                strSelect=strSelect+nestedTableType+"("+idNew+",'"+descNew+"'),";
            else
                strSelect=strSelect+nestedTableType+"("+idNew+",'"+descNew+"')";
        }
        strSelect=strSelect+")";
        strSelect=strSelect+" where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and POSIZIONE_ELEMENTO="+posElemento;
        
        if(DEBUG)
        {
            System.out.println("strSelect="+strSelect);
        }
        
        return strSelect;
    }*/


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jR_add;
    private javax.swing.JButton jR_mod;
    private javax.swing.JButton jR_del;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jL_id;
    private javax.swing.JTextField jT_id;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JTextField jT_desc;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
