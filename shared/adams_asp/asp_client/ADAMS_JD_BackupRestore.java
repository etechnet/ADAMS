/*
 * ADAMS_JD_BackupRestore.java
 *
 * Created on 14 febbraio 2006, 11.24
 */

/**
 *
 * @author  luca
 */

import java.awt.Cursor;
public class ADAMS_JD_BackupRestore extends javax.swing.JDialog implements Runnable {

    /** Creates new form ADAMS_JD_BackupRestore */
    public ADAMS_JD_BackupRestore(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        jL_status.setFont(ADAMS_GlobalParam.font_B10);
        jB_backup.setCursor(Cur_hand);
        jB_restore.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        
        setCenteredFrame(300,220);
        //show();
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jB_backup = new javax.swing.JButton();
        jB_restore = new javax.swing.JButton();
        jL_status = new javax.swing.JLabel();
        jL_icon1 = new javax.swing.JLabel();
        jL_icon2 = new javax.swing.JLabel();
        jB_close = new javax.swing.JButton();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Backup/Restore Configurations");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jB_backup.setBackground(new java.awt.Color(230, 230, 230));
        jB_backup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_backup.jpg")));
        jB_backup.setBorderPainted(false);
        jB_backup.setContentAreaFilled(false);
        jB_backup.setFocusPainted(false);
        jB_backup.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jB_backup.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_backup_press.jpg")));
        jB_backup.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_backup_over.jpg")));
        jB_backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_backupActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jB_backup, gridBagConstraints1);
        
        jB_restore.setBackground(new java.awt.Color(230, 230, 230));
        jB_restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_restore.jpg")));
        jB_restore.setBorderPainted(false);
        jB_restore.setContentAreaFilled(false);
        jB_restore.setFocusPainted(false);
        jB_restore.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_restore_press.jpg")));
        jB_restore.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_restore_over.jpg")));
        jB_restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_restoreActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jB_restore, gridBagConstraints1);
        
        jL_status.setBackground(new java.awt.Color(230, 230, 230));
        jL_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_status.setText("Backup or Restore Configuration ?");
        jL_status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_status.setMinimumSize(new java.awt.Dimension(60, 30));
        jL_status.setPreferredSize(new java.awt.Dimension(16, 30));
        jL_status.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 15, 4, 15);
        getContentPane().add(jL_status, gridBagConstraints1);
        
        jL_icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backup_db.png")));
        jL_icon1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_icon1, gridBagConstraints1);
        
        jL_icon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/restore_db.png")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_icon2, gridBagConstraints1);
        
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
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jB_close, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private boolean connectionCorba()
    {
          
        CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);
        
        boolean connOK_mdm_server_server  = false;
        connOK_mdm_server_server = CORBA.openConnection_mdm_server_server();
        
        if(connOK_mdm_server_server)
            System.out.println("Connection mdm_server_server... ok.");
        else
            System.out.println(" - Error Connection CORBA.");
        
        return connOK_mdm_server_server;

    }

    private void jB_backupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_backupActionPerformed
        
        jL_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_animated.gif")));
        jL_status.setText("Start Backup...");
        jL_status.setBackground(new java.awt.Color(230, 230, 230));
        
        OPERATION_TH = RUN_BACKUP;
        
        TH = null;
        TH = new Thread(this,"backupOracleConf");
        TH.start();  
    }//GEN-LAST:event_jB_backupActionPerformed

    private void jB_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_restoreActionPerformed

        jL_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_animated.gif")));
        jL_status.setBackground(new java.awt.Color(230, 230, 230));
        jL_status.setText("Start Restore...");
            
        OPERATION_TH = RUN_RESTORE;
        
        TH = null;
        TH = new Thread(this,"restoreOracleConf()");
        TH.start();  
    }//GEN-LAST:event_jB_restoreActionPerformed

    public void run()
    {
        TH_WORKING = true;        
        
        this.setCursor(Cur_wait);
        jB_backup.setEnabled(false);
        jB_close.setEnabled(false);
        jB_restore.setEnabled(false);     

               
        
        boolean conn = connectionCorba();
        
        if(OPERATION_TH == RUN_BACKUP)
        {            
             //Lock Table
            if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
            {
                TH_WORKING = false;
                jL_status.setIcon(null);

                jB_backup.setEnabled(true);
                jB_close.setEnabled(true);
                jB_restore.setEnabled(true);        
                this.setCursor(Cur_default);
                jL_status.setText("FAILURE - Lock Table.");
                return;
            }
                        
            jL_status.setText("RUN Backup...");
            
            boolean f_backup = false;
            if(conn == true)
                f_backup = CORBA.backupOracleConf();
            
            if(f_backup == true)
            {
                jL_status.setBackground(java.awt.Color.green.darker());
                jL_status.setText("OK Backup Configurations.");
            }
            else
            {
                jL_status.setBackground(java.awt.Color.red);
                jL_status.setText("FAILURE Backup Configurations.");
            }
        }
        else if(OPERATION_TH == RUN_RESTORE)
        {           
            jL_status.setText("RUN Restore...");
            
            boolean f_restore = false;        
            if(conn == true)
                f_restore = CORBA.restoreOracleConf();
            
            // reimposto il lock table essatto al client in run.
            ADAMS_GlobalParam.Force_LockTable();
            
            if(f_restore == true)
            {
                jL_status.setBackground(java.awt.Color.green.darker());
                jL_status.setText("OK Restore Configurations.");
            }
            else
            {
                jL_status.setBackground(java.awt.Color.red);
                jL_status.setText("FAILURE Restore Configurations.");
            }
        }

        CORBA.shutDown_Conf();
        
        jB_backup.setEnabled(true);
        jB_restore.setEnabled(true); 
        jB_close.setEnabled(true);
        this.setCursor(Cur_default);        
        jL_status.setIcon(null);
        TH_WORKING = false;
    }
    
    
    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        closeFrame();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeFrame();
    }//GEN-LAST:event_closeDialog

    private void closeFrame()
    {        
        if(TH_WORKING == false)
        {
            setVisible(false);
            dispose();
        }
    }
    
    
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
    private javax.swing.JButton jB_backup;
    private javax.swing.JButton jB_restore;
    private javax.swing.JLabel jL_status;
    private javax.swing.JLabel jL_icon1;
    private javax.swing.JLabel jL_icon2;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private CORBAConnection CORBA = null;
    
    private Thread TH           = null;
    private boolean TH_WORKING  = false;
    private int OPERATION_TH    = -1;
    private final  int RUN_BACKUP  =  1;
    private final  int RUN_RESTORE =  2;
    
}