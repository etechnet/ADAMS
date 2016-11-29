/*
 * ADAMS_JP_Script_p5.java
 *
 * Created on 25 ottobre 2005, 9.42
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */
import java.awt.Cursor;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.table.*;
import java.awt.event.InputEvent;
import java.awt.Component;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.Toolkit;

public class ADAMS_JP_Script_p5 extends javax.swing.JPanel 
{

    private ADAMS_JF_WIZARDBASE                      parent                      = null;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private ADAMSBasicFuntionList                     listaFunction               = new ADAMSBasicFuntionList();
    private javax.swing.JTable                      jTable_general1             = null;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general1  = null;
    private javax.swing.JTable                      jTable_general2             = null;
    private javax.swing.table.DefaultTableModel     defaultTableModel_general2  = null;
    private int[]                                   CellDimension               = {180};
    private int                                     minCellDimension            = 180;
    private int                                     tableSelected               = -1;     
    private String                                  NOME_HOST                   = "t_rda";
    /** Creates new form ADAMS_JP_Script_p5 */
    public ADAMS_JP_Script_p5(ADAMS_JF_WIZARDBASE parent) 
    {
        this.parent=parent;
        initComponents();
        
        jL_1.setText("<html><center><b>Now is time to edit your "
                        +"<b><font color=#0000ff>script. </font>"
                        +"fUse the lists below for a rapid access to "
                        +"<b><font color=#ff0000>variables </font>"
                        +"or "
                        +"<b><font color=#00ff00>functions. </font>"
                        +"Press "
                        +"<b><font color=#ffff00>[Add] </font>"
                        +"button to copy them in editor or"
                        +"<b><font color=#ffff00>[Help] </font>"
                        +"for a more detailed guide. "
                        +"</b><br>");
        
        
        setTable1();
        setTable2();
        
        setFont();
        setCursorWidget();
        
        updateTable(2);
    }
    
    public String getTextScript()
    {
        return vista.getText();
    }
    
    public void setInterface(ScriptObject scriptObject)
    {
        vista.setText("");
        Vector appo=scriptObject.sc.SCRIPTTEXT;
        if(appo.size()>0)
        {
            for(int i=0;i<appo.size();i++)
            {
                vista.setText(vista.getText()+(String)scriptObject.sc.SCRIPTTEXT.elementAt(i));
            }
        }
    }
    
    public void refreshPanel()
    {
        //vista.setText(parent.jP_Container_p4.getResult());
        updateTable(1);
    }
    
    public void setFont()
    {
    }
    
    public void setCursorWidget()
    {
        jB_1.setCursor(Cur_hand);
        jB_2.setCursor(Cur_hand);
        jTable_general1.setCursor(Cur_hand);
        jTable_general2.setCursor(Cur_hand);
        vista.setCursor(Cur_hand);
    }
    
    public void setTable1()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general1 = new javax.swing.JTable();
        jTable_general1.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {"Descrizione"
        }
        ) {
          Class[] types = new Class [] {
              java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general1.setAutoCreateColumnsFromModel(false);
        jTable_general1.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general1.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general1.setRowHeight(20);
        jTable_general1.setRowMargin(2);
        jTable_general1.setSelectionBackground(java.awt.Color.green);
        jTable_general1.setVerifyInputWhenFocusTarget(false);
        jTable_general1.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_general_1_MouseReleased(evt);
          }
        });

        jScrollPane1.setViewportView(jTable_general1);

        jTable_general1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jTable_general1.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general1.getColumnCount(); i++)
        {
            column = jTable_general1.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general1 = (javax.swing.table.DefaultTableModel)jTable_general1.getModel();
        
        this.setCursor(Cur_default);
    }
    
    public void setTable2()
    {    
        this.setCursor(Cur_wait);
        
        jTable_general2 = new javax.swing.JTable();
        jTable_general2.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_general2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
         "Descrizione"
        }
        ) {
          Class[] types = new Class [] {
               java.lang.String.class
          };
          boolean[] canEdit = new boolean [] {
              false
          };

          public Class getColumnClass(int columnIndex) {
              return types [columnIndex];
          }

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        jTable_general2.setAutoCreateColumnsFromModel(false);
        jTable_general2.setMinimumSize(new java.awt.Dimension(200, 200));
        jTable_general2.setPreferredScrollableViewportSize(new java.awt.Dimension(100, 100));
        jTable_general2.setRowHeight(20);
        jTable_general2.setRowMargin(2);
        jTable_general2.setSelectionBackground(java.awt.Color.green);
        jTable_general2.setVerifyInputWhenFocusTarget(false);
        jTable_general2.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseReleased(java.awt.event.MouseEvent evt) {
              jTable_general_2_MouseReleased(evt);
          }
        });

        jScrollPane2.setViewportView(jTable_general2);

        jTable_general2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jTable_general2.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);        
               
                
        TableColumn column = null;
        for(int i=0; i<jTable_general2.getColumnCount(); i++)
        {
            column = jTable_general2.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }
        
        defaultTableModel_general2 = (javax.swing.table.DefaultTableModel)jTable_general2.getModel();
        
        this.setCursor(Cur_default);
    }
    
    private void updateTable(int idTable)
    {
        javax.swing.JTable                      jTabple_appo = null;
        javax.swing.table.DefaultTableModel     defaultTableModel_appo  = null;
        javax.swing.JScrollPane                 jScroll_appo = null;
        switch(idTable)
        {
            case 1:
                    jTabple_appo=jTable_general1;
                    defaultTableModel_appo=defaultTableModel_general1;
                    jScroll_appo=jScrollPane1;
                break;
            case 2:
                    jTabple_appo=jTable_general2;
                    defaultTableModel_appo=defaultTableModel_general2;
                    jScroll_appo=jScrollPane2;
                break;
        }
        
        defaultTableModel_appo.getDataVector().removeAllElements();
        jTabple_appo.clearSelection();
        
        switch(idTable)
        {
            case 1:
                
                Vector rowRES=new Vector();
                rowRES.addElement(parent.jP_Container_p4.getResult());
                defaultTableModel_appo.addRow(rowRES);
                
                //ADAMS_JP_Script_p3.vectorTab
                Vector appoV=parent.jP_Container_p3.getV1();
                for(int i=0;i<appoV.size();i++)
                {   
                    ADAMS_JP_Script_p3.vectorTab eleme=(ADAMS_JP_Script_p3.vectorTab)appoV.elementAt(i);
                    
                    Vector row=new Vector();
                    row.addElement(new String(eleme.st1));
                    defaultTableModel_appo.addRow(row);
                }
                
                appoV=parent.jP_Container_p3.getV3();
                for(int i=0;i<appoV.size();i++)
                {   
                    ADAMS_JP_Script_p3.vectorTab eleme=(ADAMS_JP_Script_p3.vectorTab)appoV.elementAt(i);
                    
                    Vector row=new Vector();
                    row.addElement(new String(eleme.st1));
                    defaultTableModel_appo.addRow(row);
                }
                break;
                
            case 2:
                for(int i=0;i<listaFunction.lista.length;i++)
                {   
                    Vector row=new Vector();
                    row.addElement(new String(listaFunction.lista[i]));
                    defaultTableModel_appo.addRow(row);
                }
                break; 
        }
        
        
        
        jTabple_appo.validate();
        jTabple_appo.repaint();
        
        jScroll_appo.setViewportView(jTabple_appo);        
    }
    
    
    private void jTable_general_1_MouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here
        tableSelected=1;
    }
    
    private void jTable_general_2_MouseReleased(java.awt.event.MouseEvent evt) {
        // Add your handling code here
        tableSelected=2;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPM_1 = new javax.swing.JPopupMenu();
        Annulla = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        Taglia = new javax.swing.JMenuItem();
        Copia = new javax.swing.JMenuItem();
        Incolla = new javax.swing.JMenuItem();
        jL_1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jB_1 = new javax.swing.JButton();
        jB_2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        vista = new javax.swing.JEditorPane();
        
        Annulla.setText("Exit");
        Annulla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo.png")));
        Annulla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AnnullaMouseReleased(evt);
            }
        });
        
        jPM_1.add(Annulla);
        jPM_1.add(jSeparator1);
        Taglia.setText("Cut");
        Taglia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editcut.png")));
        Taglia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AnnullaMouseReleased(evt);
            }
        });
        
        jPM_1.add(Taglia);
        Copia.setText("Copy");
        Copia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editcopy.png")));
        Copia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AnnullaMouseReleased(evt);
            }
        });
        
        jPM_1.add(Copia);
        Incolla.setText("Paste");
        Incolla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editpaste.png")));
        Incolla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AnnullaMouseReleased(evt);
            }
        });
        
        jPM_1.add(Incolla);
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_1.setText("jLabel1");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.2;
        add(jL_1, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Editor Helpers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "External Variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), java.awt.Color.red));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints2);
        
        jScrollPane2.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBorder(new javax.swing.border.TitledBorder(null, "NTMBasic Functions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(23, 117, 23)));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints2);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jB_1.setBackground(new java.awt.Color(230, 230, 230));
        jB_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down_1.png")));
        jB_1.setToolTipText("Add an item to selection");
        jB_1.setFocusPainted(false);
        jB_1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jB_1.setMinimumSize(new java.awt.Dimension(56, 36));
        jB_1.setPreferredSize(new java.awt.Dimension(56, 36));
        jB_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jB_1, gridBagConstraints3);
        
        jB_2.setBackground(new java.awt.Color(230, 230, 230));
        jB_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        jB_2.setToolTipText("Add all items to selection");
        jB_2.setFocusPainted(false);
        jB_2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jB_2.setMinimumSize(new java.awt.Dimension(56, 36));
        jB_2.setPreferredSize(new java.awt.Dimension(56, 36));
        jB_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_2ActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jB_2, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel4, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jPanel3, gridBagConstraints1);
        
        jPanel2.setLayout(new java.awt.BorderLayout());
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Script Editor"));
        vista.setDoubleBuffered(true);
        vista.setDragEnabled(true);
        vista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vistaMousePressed(evt);
            }
        });
        
        jScrollPane3.setViewportView(vista);
        
        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jPanel2, gridBagConstraints1);
        
    }//GEN-END:initComponents

    String str="";
    private void AnnullaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnnullaMouseReleased
        // Add your handling code here:
        if (evt.getSource() == Annulla) 
        {
            return;
        }else if (evt.getSource() == Taglia) 
        {
            doCopy(vista.getSelectedText());
            vista.replaceSelection("");
        }else if (evt.getSource() == Copia) 
        {
            doCopy(vista.getSelectedText());
        }else if (evt.getSource() == Incolla) 
        {
            vista.replaceSelection(doPaste());
        }
    }//GEN-LAST:event_AnnullaMouseReleased

    private void vistaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vistaMousePressed
        // Add your handling code here:
        int mouseID=evt.getModifiers();
        if( (mouseID & InputEvent.BUTTON3_MASK)!=0 ) 
        {
            jPM_1.show((Component)evt.getSource(),evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_vistaMousePressed

    private void jB_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_1ActionPerformed
        // Add your handling code here:
        javax.swing.JTable                      jTabple_appo = null;

        switch(tableSelected)
        {
            case 1:
                    jTabple_appo=jTable_general1;

                break;
            case 2:
                    jTabple_appo=jTable_general2;

                break;
        }
        
        try
        {
            int rowSelected=jTabple_appo.getSelectedRow();
            
            String str=jTabple_appo.getValueAt(rowSelected,0).toString();
            //vista.setText(vista.getText()+str);
            vista.replaceSelection(str);
        }catch(Exception e)
        {
            ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.JFRAME_SCRIPT,"ERROR: No item selected.","ERROR",1);
        }

    }//GEN-LAST:event_jB_1ActionPerformed

    private void jB_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_2ActionPerformed
        // Add your handling code here:
        ADAMS_JF_HelpBasic jf=new ADAMS_JF_HelpBasic(true,"http://"+ADAMSConf.machine+".di.telecomitalia.it/"+ADAMSConf.pathName+"/scriptbasic-user-guide-sourcedoc.html");
    }//GEN-LAST:event_jB_2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPM_1;
    private javax.swing.JMenuItem Annulla;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem Taglia;
    private javax.swing.JMenuItem Copia;
    private javax.swing.JMenuItem Incolla;
    private javax.swing.JLabel jL_1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jB_1;
    private javax.swing.JButton jB_2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JEditorPane vista;
    // End of variables declaration//GEN-END:variables

    
    public void doCopy(String str)
    {
            TText t = new TText(str);
            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
            c.setContents(t, null); 
    }

    public String doPaste()
    {
            String s="";
            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = c.getContents(this);

            try {
                    s = (String)t.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Throwable exc) {
                            System.err.println(exc);
            }
            
            return s;
    }
        
}
