/*
 * JF_Search.java
 *
 * Created on 6 ottobre 2006, 10.46
 */

/**
 *
 * @author  luca
 */
import java.awt.Cursor;
import javax.swing.table.*;

public class JF_Search extends javax.swing.JFrame {

    /** Creates new form JF_Search */
    public JF_Search(javax.swing.JTable jTable_Pivot,String InitTagHTMLColumn) 
    {
        this.TABLE = jTable_Pivot;
               
        initComponents();
        
        //Cursor
        jB_find.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jCBox_UpLow.setCursor(Cur_hand);
        jR_up.setCursor(Cur_hand);
        jR_dw.setCursor(Cur_hand);
        jCBox_column.setFont(staticLib.fontA10);
        
        //Font
        jB_find.setFont(staticLib.fontA10);
        jB_cancel.setFont(staticLib.fontA10);
        jTextsearch.setFont(staticLib.fontA10);
        jCBox_UpLow.setFont(staticLib.fontA10);
        jR_up.setFont(staticLib.fontA10);
        jR_dw.setFont(staticLib.fontA10);
        jLdescSearch.setFont(staticLib.fontA10);
        jCBox_column.setFont(staticLib.fontA10);
        

        for(int i=0; i<jTable_Pivot.getColumnCount(); i++)
        {
            //System.out.println("Name Column = "+jTable_Pivot.getColumnName(i));
            
            String NameColumn_HTML = jTable_Pivot.getColumnName(i);
            String NameColumn = new String(NameColumn_HTML.trim());
            
            if(NameColumn_HTML.indexOf(InitTagHTMLColumn) != -1) // se contiene TAG HTML 
            {
                NameColumn_HTML = NameColumn_HTML.replaceAll(InitTagHTMLColumn,""); //Rimuovo Tag HTML
            
                java.util.StringTokenizer tokenStrName = new java.util.StringTokenizer(NameColumn_HTML,"<");
                NameColumn = tokenStrName.nextToken();
            }
                
            TableColumn hideColumn = (TableColumn)TABLE.getColumnModel().getColumn(i);
            if(hideColumn.getPreferredWidth() > 0 )
                jCBox_column.addItem(NameColumn);
        }
        
        setCenteredFrame(400,180);
        //show();
	this.setVisible(true);
        
        jTextsearch.requestFocus();

    } 
      
    private void search(int start_r,int start_c, String str_search)
    {
        int rows = 0;
        int column = 0;      
        
        rows = TABLE.getRowCount();
        Object[][] DATA = new Object[rows][1];
        
        String nameColumn = ""+jCBox_column.getSelectedItem();
        int index_Column = jCBox_column.getSelectedIndex();
        for(int i=0; i<rows; i++)
            DATA[i][0] = TABLE.getValueAt(i,index_Column);

        if(DATA != null)
        {
           // System.out.println("ENTRO "+start_r+" "+start_c);  
            if(rows > 0)
            {                
                if(str_search.length() > 0)
                {                    
                    column = DATA[0].length;                    
                    
                    if(jR_dw.isSelected())
                    {
                        for(int r=start_r; r<rows; r++)
                        {
                            for(int c=start_c; c<column; c++)
                            {
                                String str_cell = new String(""+DATA[r][c]);

                                if(!jCBox_UpLow.isSelected())
                                {
                                    str_cell = str_cell.toUpperCase();
                                    str_search = str_search.toUpperCase();
                                }

                                int ctrl_find = str_cell.indexOf(str_search);
                                if(ctrl_find >= 0)
                                {
                                    //System.out.println("Trovato riga = "+(r)+" colonna ="+(c));
                                    showCell(r,c);
                                    begin_r = r+1;
                                    begin_c = c;                                    
                                    jLdescSearch.setText(" FOUND: "+(r+1)+"$"+nameColumn);                                   
                                    return;
                                }
                            }             
                        }
                        begin_r = 0;
                        begin_c = 0;
                    }
                    else
                    {
                        
                        if((start_r == 0)&&(start_c == 0))
                        {
                            start_r = rows -1;
                            start_c = column-1;
                        }
                        
                        for(int r=start_r; r>=0; r--)
                        {
                            for(int c=start_c; c>=0; c--)
                            {
                                String str_cell = new String(""+DATA[r][c]);

                                if(!jCBox_UpLow.isSelected())
                                {
                                    str_cell = str_cell.toUpperCase();
                                    str_search = str_search.toUpperCase();
                                }

                                int ctrl_find = str_cell.indexOf(str_search);
                                if(ctrl_find >= 0)
                                {
                                    //System.out.println("Trovato riga = "+(r)+" colonna ="+(c));
                                    showCell(r,c);
                                    begin_r = r-1;
                                    begin_c = c;
                                    jLdescSearch.setText(" FOUND: "+(r+1)+"$"+nameColumn);
                                    return;
                                }
                            }             
                        }
                        begin_r = rows-1;
                        begin_c = column-1;
                    }                    
                }
            }
        }        
        staticLib.optionPanel("Finished search.", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    private void showCell(int row, int column)
    {     
        java.awt.Rectangle rect = TABLE.getCellRect(row,column,true);
        
        TABLE.scrollRectToVisible(rect);
        TABLE.clearSelection();        
        TABLE.setRowSelectionInterval(row,row);
        TABLE.getParent().validate();
    }
    
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jP_Tot = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextsearch = new javax.swing.JTextField();
        jP_buttons = new javax.swing.JPanel();
        jB_find = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        jCBox_UpLow = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jR_up = new javax.swing.JRadioButton();
        jR_dw = new javax.swing.JRadioButton();
        jCBox_column = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLdescSearch = new javax.swing.JLabel();
        
        
        setTitle("Search");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jP_Tot.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_Tot.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png")));
        jLabel1.setText("Search:");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(15, 4, 4, 4);
        jP_Tot.add(jLabel1, gridBagConstraints1);
        
        jTextsearch.setMinimumSize(new java.awt.Dimension(4, 22));
        jTextsearch.setPreferredSize(new java.awt.Dimension(4, 22));
        jTextsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextsearchActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(15, 4, 4, 4);
        gridBagConstraints1.weightx = 1.0;
        jP_Tot.add(jTextsearch, gridBagConstraints1);
        
        jP_buttons.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_buttons.setBackground(new java.awt.Color(230, 230, 230));
        jB_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_find.jpg")));
        jB_find.setToolTipText("Find");
        jB_find.setBorderPainted(false);
        jB_find.setContentAreaFilled(false);
        jB_find.setFocusPainted(false);
        jB_find.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_find_press.jpg")));
        jB_find.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_find_over.jpg")));
        jB_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_findActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 5, 0);
        gridBagConstraints2.weightx = 1.0;
        jP_buttons.add(jB_find, gridBagConstraints2);
        
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_cancel.setToolTipText("Close");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 0, 0, 0);
        gridBagConstraints2.weightx = 1.0;
        jP_buttons.add(jB_cancel, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 5, 5, 5);
        jP_Tot.add(jP_buttons, gridBagConstraints1);
        
        jCBox_UpLow.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_UpLow.setText("Uppercase/Lowercase");
        jCBox_UpLow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCBox_UpLow.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_UpLow.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCBox_UpLow.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_UpLow.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.insets = new java.awt.Insets(12, 4, 0, 0);
        jP_Tot.add(jCBox_UpLow, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.TitledBorder(null, "Direction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 10)));
        jR_up.setBackground(new java.awt.Color(230, 230, 230));
        jR_up.setText("Up");
        buttonGroup1.add(jR_up);
        jR_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_up.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_up.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jR_up.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_up.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jPanel3.add(jR_up);
        
        jR_dw.setBackground(new java.awt.Color(230, 230, 230));
        jR_dw.setSelected(true);
        jR_dw.setText("down");
        buttonGroup1.add(jR_dw);
        jR_dw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_dw.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_dw.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jR_dw.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_dw.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jPanel3.add(jR_dw);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        jP_Tot.add(jPanel3, gridBagConstraints1);
        
        jCBox_column.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_column.setMaximumRowCount(4);
        jCBox_column.setToolTipText("Select Column");
        jCBox_column.setMinimumSize(new java.awt.Dimension(50, 22));
        jCBox_column.setPreferredSize(new java.awt.Dimension(50, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 4;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.insets = new java.awt.Insets(15, 0, 4, 4);
        jP_Tot.add(jCBox_column, gridBagConstraints1);
        
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/columns.png")));
        jLabel3.setToolTipText("Select Column");
        jLabel3.setMinimumSize(new java.awt.Dimension(22, 22));
        jLabel3.setPreferredSize(new java.awt.Dimension(22, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.insets = new java.awt.Insets(14, 4, 4, 0);
        jP_Tot.add(jLabel3, gridBagConstraints1);
        
        getContentPane().add(jP_Tot, java.awt.BorderLayout.CENTER);
        
        jLdescSearch.setBackground(new java.awt.Color(230, 230, 230));
        jLdescSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLdescSearch.setMinimumSize(new java.awt.Dimension(50, 18));
        jLdescSearch.setPreferredSize(new java.awt.Dimension(50, 18));
        jLdescSearch.setOpaque(true);
        getContentPane().add(jLdescSearch, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jTextsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextsearchActionPerformed
        go_search();
    }//GEN-LAST:event_jTextsearchActionPerformed

    private void jB_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_findActionPerformed
        go_search();
    }//GEN-LAST:event_jB_findActionPerformed

    public void go_search()
    {        
        String str_search = jTextsearch.getText().trim();
        
        if(oldName.compareTo(str_search) == 0)
            search(begin_r,begin_c,str_search);        
        else
        {   
            begin_r = 0;
            begin_c = 0;
            search(0,0,str_search);
        }
        
        oldName = new String(str_search);
    }
    
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        closeFrame();        
    }//GEN-LAST:event_jB_cancelActionPerformed
  
    private void closeFrame()
    {
        begin_r = 0;
        begin_c = 0;
        this.setVisible(false);
        this.dispose();
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jP_Tot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextsearch;
    private javax.swing.JPanel jP_buttons;
    private javax.swing.JButton jB_find;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JCheckBox jCBox_UpLow;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jR_up;
    private javax.swing.JRadioButton jR_dw;
    private javax.swing.JComboBox jCBox_column;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLdescSearch;
    // End of variables declaration//GEN-END:variables

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable TABLE = null;
    
    String oldName = "";
    int begin_r = 0;
    int begin_c = 0;
                              
}
