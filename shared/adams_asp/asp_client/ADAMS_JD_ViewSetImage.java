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
import java.io.InputStream;
import java.io.IOException;
import javax.swing.ImageIcon;

public class ADAMS_JD_ViewSetImage extends javax.swing.JDialog {

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_ViewSetImage(java.awt.Frame parent, boolean modal, int width, int height,String nameImage) {
        super(parent, modal);
        initComponents();
        
        
        this.NAME_SELECTED = nameImage;
        jL_Name.setBackground(Color.green.darker());
        jL_Name.setText(NAME_SELECTED);
      
        this.setTitle("IMAGE Library");
        
        jL_Title.setFont(ADAMS_GlobalParam.font_B12);
        
        ip = new IconPool("/images/");
        jListName = new JListIcon(ip);
        jListName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_List.setViewportView(jListName);   

        jB_apply.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);

        setCenteredFrame(width,height); 

        read_TableImage();
        load_ListName();
    }
    
    public void  setGuiEnable(boolean flag)
    {
        jB_apply.setEnabled(flag);
        jB_cancel.setEnabled(flag);
    }


    public void read_TableImage()
    {
        this.setCursor(Cur_wait);
        V_l_img_library = new Vector();

        //Costruzione Stringa
        //String sel_readPlugins = "select TIPO_DI_PICTURE,NOME_FILE from  l_img_library";
        String sel_readPlugins = "select NOME_FILE from  l_img_library";

         //System.out.println("sel PluginRow ----> "+sel_readPlugins);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
	//ResultSetMetaData rsmd;
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_readPlugins,SQLStatement);

        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next())
                {
                    //oracle.sql.BFILE tipo_di_picture  = ((oracle.jdbc.driver.OracleResultSet)rs_simple).getBFILE("TIPO_DI_PICTURE");
                    String nome_file = rs_simple.getString("NOME_FILE");                 
                    
                   /* rsmd = rs_simple.getMetaData();
                    String strTipo = rsmd.getColumnClassName(1);
                    System.out.println("strTipo="+strTipo+", Nome File="+nome_file);*/
                    
                         
                    //*************** BFILE  *************
                   /*System.out.println("BFILE.getName()     ->"+tipo_di_picture.getName());
                    System.out.println("BFILE.isfileOpen()  ->"+tipo_di_picture.isFileOpen());
                    System.out.println("BFILE.fileExists()  ->"+tipo_di_picture.fileExists());
                    
                    System.out.println("1");
                    tipo_di_picture.openFile();
                    
                    System.out.println("2");
                    int length_bfile = (int)tipo_di_picture.length();               
                    
                    java.io.InputStream stream = tipo_di_picture.getBinaryStream();
                    byte[] buffer = new byte[length_bfile];                  
                    stream.read(buffer);
                    ImageIcon image = new ImageIcon(buffer); 
                    // BFILE Test */
                    
                    //ImageRow row = new ImageRow(tipo_di_picture,nome_file);
                    ImageRow row = new ImageRow(null,nome_file);
                    V_l_img_library.addElement(row);
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
                System.out.println("(ADAMS_JD_ViewSetImage-1) SQL Operation " + exc.toString());
            }

        }else
        {
            System.out.println("rs==null");
        }
        this.setCursor(Cur_default);
    }
    
    private void load_ListName()
    {
        jListName.removeAll();   
        for(int i=0; i<V_l_img_library.size(); i++)
            jListName.addElement("image.png","image.png",((ImageRow)V_l_img_library.elementAt(i)).NOME_FILE );

       
    }
      
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_Title = new javax.swing.JLabel();
        jP_Center = new javax.swing.JPanel();
        jScroll_List = new javax.swing.JScrollPane();
        jP_option_read = new javax.swing.JPanel();
        jB_apply = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        jL_Name = new javax.swing.JLabel();
        jP_south = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder_image.png")));
        jL_Title.setText("ICONS LIBRARY");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 40));
        jL_Title.setOpaque(true);
        getContentPane().add(jL_Title, java.awt.BorderLayout.NORTH);
        
        jP_Center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_Center.setBackground(new java.awt.Color(230, 230, 230));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_Center.add(jScroll_List, gridBagConstraints1);
        
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
        
        jL_Name.setBackground(new java.awt.Color(230, 230, 230));
        jL_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Name.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Name.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_Name.setPreferredSize(new java.awt.Dimension(250, 22));
        jL_Name.setOpaque(true);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(3, 22, 3, 22);
        gridBagConstraints2.weightx = 1.0;
        jP_option_read.add(jL_Name, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
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
        NAME_SELECTED = "";
        jL_Name.setText(NAME_SELECTED);        
        jListName.clearSelection();
       
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
        if(jListName.getSelectedValue() != null)
        {
           NAME_SELECTED = jListName.getSelectedValue().toString().trim();
           jL_Name.setText(NAME_SELECTED);
        }
    }//GEN-LAST:event_jB_applyActionPerformed

    public String getNameImage()
    {
        return this.NAME_SELECTED;
    }
        
    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        setVisible(false);        
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);        
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
    


    class ImageRow
    {
        /*ImageRow(oracle.sql.BFILE tipo_di_picture, String nome_file)
        {
            TIPO_DI_PICTURE = tipo_di_picture;
            NOME_FILE       = nome_file;
        }*/
        
        ImageRow(String tipo_di_picture, String nome_file)
        {
            TIPO_DI_PICTURE = tipo_di_picture;
            NOME_FILE       = nome_file;
        }
        
        //oracle.sql.BFILE TIPO_DI_PICTURE;
        String TIPO_DI_PICTURE;
        String NOME_FILE;     
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Title;
    private javax.swing.JPanel jP_Center;
    private javax.swing.JScrollPane jScroll_List;
    private javax.swing.JPanel jP_option_read;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JLabel jL_Name;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables
            
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);

    private Vector V_l_img_library;
    
    private IconPool ip = null;
    private JListIcon jListName = null;
    private String NAME_SELECTED = "";
}
