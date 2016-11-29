/*
 * ADAMS_JF_Wizard.java
 *
 * Created on 14 aprile 2005, 12.04
 */

/**
 *
 * @author  root
 */
import java.util.Vector;
import java.awt.Cursor;
import javax.swing.JOptionPane;

public class ADAMS_JF_Wizard extends javax.swing.JFrame implements Runnable {

    /** Creates new form ADAMS_JF_Wizard */
    public ADAMS_JF_Wizard() {
        initComponents();
        
        //jrb_users_mang.setVisible(false);
        
        ADAMS_GlobalParam.JFRAME_WIZARD=this;
        
        ip = new IconPool("/images/");
        jListConfName = new JListIcon(ip);
        jListConfName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_List.setViewportView(jListConfName);        
        
        jrb_noSelected = new javax.swing.JRadioButton();
        buttonGroup1.add(jrb_noSelected);
        jrb_noSelected.setSelected(true);
    
        // Font Globali
        jL_desc.setFont(ADAMS_GlobalParam.font_B11);
        jB_reload.setFont(ADAMS_GlobalParam.font_B11);
        jrb_addConf.setFont(ADAMS_GlobalParam.font_B11);
        jrb_modConf.setFont(ADAMS_GlobalParam.font_B11);
        jrb_delConf.setFont(ADAMS_GlobalParam.font_B11);
        jrb_confHelp.setFont(ADAMS_GlobalParam.font_B11);
        jrb_report.setFont(ADAMS_GlobalParam.font_B11);
        jrb_plugins.setFont(ADAMS_GlobalParam.font_B11);
        jrb_AlarmServer.setFont(ADAMS_GlobalParam.font_B11);
        jrb_backup_restore.setFont(ADAMS_GlobalParam.font_B11);
        jrb_clone_conf.setFont(ADAMS_GlobalParam.font_B11);
        jrb_users_mang.setFont(ADAMS_GlobalParam.font_B11);
        jrb_sendConf.setFont(ADAMS_GlobalParam.font_B11);
        jrb_createIndex.setFont(ADAMS_GlobalParam.font_B11);
        jCBox_index_lib.setFont(ADAMS_GlobalParam.font_B11);
        
        // Cursor
        jB_reload.setCursor(Cur_hand);
        jListConfName.setCursor(Cur_hand);
        jrb_addConf.setCursor(Cur_hand);
        jrb_modConf.setCursor(Cur_hand);
        jrb_delConf.setCursor(Cur_hand);
        jrb_confHelp.setCursor(Cur_hand);
        jrb_report.setCursor(Cur_hand);
        jrb_sendConf.setCursor(Cur_hand);
        jB_next.setCursor(Cur_hand);
        jrb_plugins.setCursor(Cur_hand);
        jrb_AlarmServer.setCursor(Cur_hand);
        jrb_backup_restore.setCursor(Cur_hand);
        jrb_clone_conf.setCursor(Cur_hand);
        jrb_users_mang.setCursor(Cur_hand);
        jrb_createIndex.setCursor(Cur_hand);
        jCBox_index_lib.setCursor(Cur_hand);
                
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));

        
        //-------------
        jCBox_index_lib.addItem(new String("None"));
        
        String select1 = "select DESC_INDICE from  l_index_lib order by DESC_INDICE";
        V_Desc_index  = ADAMS_GlobalParam.connect_Oracle.Query(select1);
        String select2 = "select ID_INDICE from  l_index_lib order by DESC_INDICE";
        V_ID_index  = ADAMS_GlobalParam.connect_Oracle.Query(select2);
        
        for(int i=0; i<V_Desc_index.size(); i++)
            jCBox_index_lib.addItem((String) V_Desc_index.elementAt(i));
        //-------------
        
        
        getNameAnalisi();        
        
        setCenteredFrame(470,630);
        //show();
        this.setVisible(true);
                
        //System.out.println("prima CHECK LOCK");
        boolean flag = ADAMS_GlobalParam.ctrl_LOCK_TABLE(true);
        //System.out.println("dopo CHECK LOCK"); 
        //per test 
        /*System.out.println("***********************************************************");
        
        ADAMS_storeConfig ntmMasterServer=new ADAMS_storeConfig();
        
        ntmMasterServer.set_config_NAME_ALIAS("N.T.M.-Voce-Standard");
        //ntmMasterServer.set_config_NAME_ALIAS("Test_Luca");
        ntmMasterServer.get_REPORTCONFIG();       //Raffo
        
        ntmMasterServer.get_SCRIPTCONFIG();       //Raffo
        ntmMasterServer.get_EXCEPTIONCONFIG();    //Raffo
        ntmMasterServer.get_USERCONFIG();         //Raffo
        ntmMasterServer.get_DRCONFIG();          //Raffo
        ntmMasterServer.get_COUNTERKIT();     //Luca
        ntmMasterServer.get_GLOBALOPT();      //Luca
        ntmMasterServer.get_ADAMSConfIG();         //Luca
        ntmMasterServer.get_ANALISICONFIG();      //Raffo
        ntmMasterServer.get_PLUGINCONFIG();       //Raffo
        System.out.println("*********************** STORE CONFIG **********************");
        ntmMasterServer.store_ADAMSConfIG();          //Raffo
        System.out.println("***********************************************************");*/
        
        if(flag)
        {
            String strSelect="delete from tab_scripts where FLAG_VALIDATE=1";
            int iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
            //System.out.println(strSelect+"   RET="+iRet);
            if(iRet>0)
            {
                System.out.println("La tabella degli script Ã¨ stata ripulita.");
            }
        }else
        {
            System.out.println("il client e usato da un'altro utente.");
        }
    }

    
            
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jL_desc = new javax.swing.JLabel();
        jScroll_List = new javax.swing.JScrollPane();
        jP_jrb = new javax.swing.JPanel();
        jrb_backup_restore = new javax.swing.JRadioButton();
        jrb_addConf = new javax.swing.JRadioButton();
        jrb_createIndex = new javax.swing.JRadioButton();
        jrb_delConf = new javax.swing.JRadioButton();
        jrb_clone_conf = new javax.swing.JRadioButton();
        jrb_modConf = new javax.swing.JRadioButton();
        jrb_confHelp = new javax.swing.JRadioButton();
        jrb_plugins = new javax.swing.JRadioButton();
        jrb_AlarmServer = new javax.swing.JRadioButton();
        jrb_report = new javax.swing.JRadioButton();
        jrb_sendConf = new javax.swing.JRadioButton();
        jrb_users_mang = new javax.swing.JRadioButton();
        jB_reload = new javax.swing.JButton();
        jB_next = new javax.swing.JButton();
        jCBox_index_lib = new javax.swing.JComboBox();
        
        
        getContentPane().setLayout(null);
        
        setTitle("NTM Configurator");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/title_conf.gif")));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 8, 440, 50);
        
        jL_desc.setText("Configuration Name Alias filter:");
        getContentPane().add(jL_desc);
        jL_desc.setBounds(10, 70, 220, 20);
        
        getContentPane().add(jScroll_List);
        jScroll_List.setBounds(10, 100, 440, 150);
        
        jP_jrb.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
        
        jP_jrb.setBackground(new java.awt.Color(230, 230, 230));
        jP_jrb.setMinimumSize(new java.awt.Dimension(312, 650));
        jP_jrb.setPreferredSize(new java.awt.Dimension(312, 670));
        jrb_backup_restore.setBackground(new java.awt.Color(230, 230, 230));
        jrb_backup_restore.setText("Backup/Restore Configurations");
        jrb_backup_restore.setToolTipText("Procedure to Backup or Restore Configurations");
        buttonGroup1.add(jrb_backup_restore);
        jrb_backup_restore.setContentAreaFilled(false);
        jrb_backup_restore.setFocusPainted(false);
        jrb_backup_restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_backup_restore.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_backup_restore.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_backup_restore.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_backup_restore.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_backup_restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_backup_restoreActionPerformed(evt);
            }
        });
        
        jP_jrb.add(jrb_backup_restore);
        
        jrb_addConf.setBackground(new java.awt.Color(230, 230, 230));
        jrb_addConf.setText("Create a new configuration.");
        jrb_addConf.setToolTipText("Use this procedure to create and edit a new configuration from scratch.");
        buttonGroup1.add(jrb_addConf);
        jrb_addConf.setContentAreaFilled(false);
        jrb_addConf.setFocusPainted(false);
        jrb_addConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_addConf.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_addConf.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_addConf.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_addConf.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_addConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_addConfActionPerformed(evt);
            }
        });
        
        jP_jrb.add(jrb_addConf);
        
        jrb_createIndex.setBackground(new java.awt.Color(230, 230, 230));
        jrb_createIndex.setText("Create a new configuration Index");
        jrb_createIndex.setToolTipText("Create a new configuration Index");
        buttonGroup1.add(jrb_createIndex);
        jrb_createIndex.setContentAreaFilled(false);
        jrb_createIndex.setFocusPainted(false);
        jrb_createIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_createIndex.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_createIndex.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_createIndex.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_createIndex.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_createIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_createIndexActionPerformed(evt);
            }
        });
        
        jP_jrb.add(jrb_createIndex);
        
        jrb_delConf.setForeground(java.awt.Color.red);
        jrb_delConf.setText("Completely destroy an existing configuration.");
        jrb_delConf.setToolTipText("This procedure completely erase an existing configuration...\nBeware what you do!");
        buttonGroup1.add(jrb_delConf);
        jrb_delConf.setContentAreaFilled(false);
        jrb_delConf.setFocusPainted(false);
        jrb_delConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_delConf.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_delConf.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_delConf.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_delConf.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_jrb.add(jrb_delConf);
        
        jrb_clone_conf.setBackground(new java.awt.Color(230, 230, 230));
        jrb_clone_conf.setText("Clone Configurations");
        jrb_clone_conf.setToolTipText("Procedure to Clone Configurations");
        buttonGroup1.add(jrb_clone_conf);
        jrb_clone_conf.setContentAreaFilled(false);
        jrb_clone_conf.setFocusPainted(false);
        jrb_clone_conf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_clone_conf.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_clone_conf.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_clone_conf.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_clone_conf.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_clone_conf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_clone_confActionPerformed(evt);
            }
        });
        
        jP_jrb.add(jrb_clone_conf);
        
        jrb_modConf.setBackground(new java.awt.Color(230, 230, 230));
        jrb_modConf.setText("Edit an existing configuration.");
        jrb_modConf.setToolTipText("Here you can edit an existing configuration values.");
        buttonGroup1.add(jrb_modConf);
        jrb_modConf.setContentAreaFilled(false);
        jrb_modConf.setFocusPainted(false);
        jrb_modConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_modConf.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_modConf.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_modConf.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_modConf.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_jrb.add(jrb_modConf);
        
        jrb_confHelp.setBackground(new java.awt.Color(230, 230, 230));
        jrb_confHelp.setText("Edit configurator helper tables.");
        jrb_confHelp.setToolTipText("Procedure to edit internal helper tables.");
        buttonGroup1.add(jrb_confHelp);
        jrb_confHelp.setContentAreaFilled(false);
        jrb_confHelp.setFocusPainted(false);
        jrb_confHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_confHelp.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_confHelp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_confHelp.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_confHelp.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_confHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_confHelpActionPerformed(evt);
            }
        });
        
        jP_jrb.add(jrb_confHelp);
        
        jrb_plugins.setBackground(new java.awt.Color(230, 230, 230));
        jrb_plugins.setText("Edit configurator plugins table.");
        jrb_plugins.setToolTipText("Procedure to edit plugin table.");
        buttonGroup1.add(jrb_plugins);
        jrb_plugins.setContentAreaFilled(false);
        jrb_plugins.setFocusPainted(false);
        jrb_plugins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_plugins.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_plugins.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_plugins.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_plugins.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_jrb.add(jrb_plugins);
        
        jrb_AlarmServer.setBackground(new java.awt.Color(230, 230, 230));
        jrb_AlarmServer.setText("Edit configurator Alarm Server table.");
        jrb_AlarmServer.setToolTipText("Procedure to edit Alarm Server table.");
        buttonGroup1.add(jrb_AlarmServer);
        jrb_AlarmServer.setContentAreaFilled(false);
        jrb_AlarmServer.setFocusPainted(false);
        jrb_AlarmServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_AlarmServer.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_AlarmServer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_AlarmServer.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_AlarmServer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_jrb.add(jrb_AlarmServer);
        
        jrb_report.setBackground(new java.awt.Color(230, 230, 230));
        jrb_report.setText("Edit report schema.");
        jrb_report.setToolTipText("Procedure to edit report schema.");
        buttonGroup1.add(jrb_report);
        jrb_report.setContentAreaFilled(false);
        jrb_report.setFocusPainted(false);
        jrb_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_report.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_report.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_report.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_report.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_jrb.add(jrb_report);
        
        jrb_sendConf.setBackground(new java.awt.Color(230, 230, 230));
        jrb_sendConf.setText("Transfer Configuration");
        jrb_sendConf.setToolTipText("Procedure to send Configuration");
        buttonGroup1.add(jrb_sendConf);
        jrb_sendConf.setContentAreaFilled(false);
        jrb_sendConf.setFocusPainted(false);
        jrb_sendConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_sendConf.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_sendConf.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_sendConf.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_sendConf.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_jrb.add(jrb_sendConf);
        
        jrb_users_mang.setBackground(new java.awt.Color(230, 230, 230));
        jrb_users_mang.setText("Users Management/Users Job");
        jrb_users_mang.setToolTipText("Procedure to Users Management or Users Job");
        buttonGroup1.add(jrb_users_mang);
        jrb_users_mang.setContentAreaFilled(false);
        jrb_users_mang.setFocusPainted(false);
        jrb_users_mang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_users_mang.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_users_mang.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_users_mang.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_users_mang.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_users_mang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_users_mangActionPerformed(evt);
            }
        });
        
        jP_jrb.add(jrb_users_mang);
        
        getContentPane().add(jP_jrb);
        jP_jrb.setBounds(10, 260, 440, 300);
        
        jB_reload.setBackground(new java.awt.Color(230, 230, 230));
        jB_reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload.jpg")));
        jB_reload.setToolTipText("Reload Configuration Name Alias");
        jB_reload.setBorderPainted(false);
        jB_reload.setContentAreaFilled(false);
        jB_reload.setFocusPainted(false);
        jB_reload.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_reload.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_press.jpg")));
        jB_reload.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_over.jpg")));
        jB_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_reloadActionPerformed(evt);
            }
        });
        
        getContentPane().add(jB_reload);
        jB_reload.setBounds(392, 70, 60, 20);
        
        jB_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_next.jpg")));
        jB_next.setBorderPainted(false);
        jB_next.setContentAreaFilled(false);
        jB_next.setFocusPainted(false);
        jB_next.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_next.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_next_press.jpg")));
        jB_next.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_next_over.jpg")));
        jB_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_nextActionPerformed(evt);
            }
        });
        
        getContentPane().add(jB_next);
        jB_next.setBounds(390, 565, 60, 20);
        
        jCBox_index_lib.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_index_lib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_index_libActionPerformed(evt);
            }
        });
        
        getContentPane().add(jCBox_index_lib);
        jCBox_index_lib.setBounds(232, 70, 160, 20);
        
        pack();
    }//GEN-END:initComponents

    private void jCBox_index_libActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_index_libActionPerformed
        getNameAnalisi();
    }//GEN-LAST:event_jCBox_index_libActionPerformed

    private void jrb_createIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_createIndexActionPerformed
        jListConfName.clearSelection();
    }//GEN-LAST:event_jrb_createIndexActionPerformed

    private void jrb_users_mangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_users_mangActionPerformed
        jListConfName.clearSelection();
    }//GEN-LAST:event_jrb_users_mangActionPerformed

    private void jrb_clone_confActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_clone_confActionPerformed
         jListConfName.clearSelection();
    }//GEN-LAST:event_jrb_clone_confActionPerformed

    private void jrb_backup_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_backup_restoreActionPerformed
        jListConfName.clearSelection();
    }//GEN-LAST:event_jrb_backup_restoreActionPerformed

    private void jrb_confHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_confHelpActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jrb_confHelpActionPerformed

    private void jrb_addConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_addConfActionPerformed
        jListConfName.clearSelection();

    }//GEN-LAST:event_jrb_addConfActionPerformed

    private void jB_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_nextActionPerformed

        ADAMS_GlobalParam.MODIFY_ELEMENT=0;
        if( jrb_addConf.isSelected() )
        {
            if(jf_addConf == null)            
                jf_addConf = new ADAMS_JF_AddMod_conf(this,"Create new Configuration",0,"");
            else 
            {
                jf_addConf.dispose();
                jf_addConf = new ADAMS_JF_AddMod_conf(this,"Create new Configuration",0,"");
            }                
            return;
        }
        
        
        /* Cre interfaccia per la gestione degli help. */
        if( jrb_confHelp.isSelected() )
        {
            if(jf_confHelp == null)
            {
                jf_confHelp = new ADAMS_JF_ConfigHelp(this,"Editing Configuration Help",0,"");
            }
            else
            {
                jf_confHelp.show_JF_LOCK();
            }
            return;
        }
        
        if (jrb_backup_restore.isSelected())
        {
            ADAMS_JD_BackupRestore JD_BackupRestore = new ADAMS_JD_BackupRestore(this,true);
            return;
        }
        
        if( V_Conf_name.size() == 0 ) //(RS_EMPTY) // nel caso non ci sia nessuna configurazione.
        {   
            ADAMS_GlobalParam.optionPanel(this,"Configuration NOT FOUND", "WARNING",4);
            return;
        }      
        
        if(jrb_users_mang.isSelected())
        {
            ADAMS_JD_Users JD_Users = new ADAMS_JD_Users(this,true);
            return;
        }
        
        if( jrb_clone_conf.isSelected())
        {        
            ADAMS_JD_Configuration_Clone JD_Configuration_Clone = new ADAMS_JD_Configuration_Clone(this,true);
            return;
        }
        
        
        if(jrb_createIndex.isSelected())
        {      
            ADAMS_JD_Configuration_Clone JD_Configuration_Clone_Index = new ADAMS_JD_Configuration_Clone(this,true,"IndexConfiguration",true);
            return;
        }
        
        
        if( jListConfName.getSelectedValue() == null )
        {
            ADAMS_GlobalParam.optionPanel(this,"Select an item from the List.", "INFORMATION",3);
            return;
        }
        
        if(jrb_noSelected.isSelected())
        {
            ADAMS_GlobalParam.optionPanel(this,"Select a Procedure to continue.", "INFORMATION",3);
            return;
        }
        
        String configName = jListConfName.getSelectedValue().toString().trim();        
                
        if( jrb_report.isSelected() )
        {

            if(jf_confReport==null)
            {
                jf_confReport = new ADAMS_JF_Report(ADAMS_GlobalParam.JFRAME_WIZARD,true,"Editing Report Schema",configName,"no slect",ADAMS_GlobalParam.REPORT_SCHEMA,0,null,null);
            }
            else
            {
                jf_confReport.closeFrame(true);
                jf_confReport=null;
                jf_confReport = new ADAMS_JF_Report(ADAMS_GlobalParam.JFRAME_WIZARD,true,"Editing Report Schema",configName,"no select",ADAMS_GlobalParam.REPORT_SCHEMA,0,null,null);
            }
            return;
        }
        
        if(jrb_sendConf.isSelected())
        {           
            boolean isConfig_INDEX = false;
            for(int i=0; i<V_NameConfDefault.size(); i++)
            {
                if( ((String)V_NameConfDefault.elementAt(i)).equals(configName) )
                {
                    isConfig_INDEX = true;
                    break;
                }
            }
                        
            ADAMS_JF_TransferConf transfer = new ADAMS_JF_TransferConf(this,configName,isConfig_INDEX);
            return;
        }
        
        if( jrb_modConf.isSelected() )
        {            
            if(jf_addConf == null)            
                jf_addConf = new ADAMS_JF_AddMod_conf(this,"Modify Configuration",1,configName); 
            else 
            {
                jf_addConf.dispose();
                jf_addConf = new ADAMS_JF_AddMod_conf(this,"Modify Configuration",1,configName); 
            }                   
        }
        else if( jrb_delConf.isSelected() )
        {
            if(jf_addConf == null)            
                jf_addConf = new ADAMS_JF_AddMod_conf(this,"Detete Configuration",2,configName);
            else
            {
                jf_addConf.dispose();
                jf_addConf = new ADAMS_JF_AddMod_conf(this,"Detete Configuration",2,configName);
            }
        }
        else if(jrb_plugins.isSelected())
        {
            ADAMS_JD_RegisterPlugin_DB RegisterPlugin_DB = new ADAMS_JD_RegisterPlugin_DB(this,true,700,400,configName);
        }
        else if(jrb_AlarmServer.isSelected())
        {
            ADAMS_JD_RegisterServerAlarm_DB RegisterAlarmServer_DB = new ADAMS_JD_RegisterServerAlarm_DB(this,false,800,470,configName);
        }
        
        
    }//GEN-LAST:event_jB_nextActionPerformed

    
    private void jB_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reloadActionPerformed
        getNameAnalisi();
    }//GEN-LAST:event_jB_reloadActionPerformed
    
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;
        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
    
    public void setCenteredFrame()
    {       
        int width = this.getSize().width;
        int height = this.getSize().height;

        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;
        
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
        
        if( this.isShowing() )
            this.toFront();
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        
        //System.out.println("Dim Windows width="+this.getSize().width+" height="+this.getSize().height);
        this.dispose();
        
    }//GEN-LAST:event_exitForm

    private void set_buttonEnabled(boolean f)
    {
        jB_reload.setEnabled(f);            
        jrb_noSelected.setSelected(!f);
        jB_next.setSelected(f);
    }
    
    
    public void run()
    {
        //System.out.println("RUN TH");

        if(OperationTH == 0)
        {
            
            //--------------------------------------------------- Query

            int ID_GroupINDEX_selected = jCBox_index_lib.getSelectedIndex();
            
            String str_where1 = "";
            String str_where2 = "";
            if(ID_GroupINDEX_selected > 0)
            {
                str_where1 = "where ID_INDICE = "+(String)V_ID_index.elementAt(ID_GroupINDEX_selected-1)+" ";
                str_where2 = " and ID_INDICE = "+(String)V_ID_index.elementAt(ID_GroupINDEX_selected-1);
            }

            String sel_ConfName = "select TIPO_DI_CONFIGURAZIONE from tab_info_config "+str_where1+"order by TIPO_DI_CONFIGURAZIONE";
            V_Conf_name  = ADAMS_GlobalParam.connect_Oracle.Query(sel_ConfName);
            
            String sel_ConfNameDefault = "select TIPO_DI_CONFIGURAZIONE from tab_info_config where CONF_DEFAULT='Y'"+str_where2;
            V_NameConfDefault = ADAMS_GlobalParam.connect_Oracle.Query(sel_ConfNameDefault);
            
            
           /* int SIZE_V_NameConfDefault = V_NameConfDefault.size();
            
            String Name_CONF_Index = "";
                        
            if(SIZE_V_NameConfDefault == 1)
            {
                Name_CONF_Index = new String((String)V_NameConfDefault.elementAt(0));
            }
            else if(SIZE_V_NameConfDefault >1)
            {
                System.out.println("ERRORE: Ci sono piu configurazioni indice controllare");
            }*/
            
            set_buttonEnabled(false);           
            jListConfName.removeAll(); 
                         
            int SIZE = V_Conf_name.size();
            if( SIZE == 0 )
            {
                jListConfName.addElement("no_conf.png","no_conf.png","Configuration Not Found");
                jrb_modConf.setEnabled(false);
                jrb_delConf.setEnabled(false);
                jrb_addConf.setSelected(true);
            }
            else
            {
                for(int i=0; i<SIZE; i++)
                {
                    String nameConf = (String)V_Conf_name.elementAt(i);
                    
                    String Name_CONF_Index = "";
                    boolean indexConf = false;
                    for(int z=0;z<V_NameConfDefault.size(); z++)
                    {
                        Name_CONF_Index = (String)V_NameConfDefault.elementAt(z);
                        if(Name_CONF_Index.equals(nameConf))
                        {
                            indexConf = true;
                            break;
                        }
                            
                    }                    
                    
                    if(indexConf == true)
                        jListConfName.addElement("analysis_22.png","analysis_22.png",nameConf);
                    else
                        jListConfName.addElement("conf.png","conf_sel.png",nameConf);                    
                }
                jrb_modConf.setEnabled(true);
                jrb_delConf.setEnabled(true);
            }            
            set_buttonEnabled(true);
            //------------------------------------------------------- Query end
            
            try
            {
                jScroll_List.setViewportView(jListConfName);
            }
            catch (Exception e)
            {
                jListConfName.repaint();
                jScroll_List.setViewportView(jListConfName);
            }
        } 
        OperationTH = -1;
    }
    
    public void getNameAnalisi()
    {
        OperationTH = 0;
        
        t = null;
        t = new Thread(this,"getNameAnalisi");
        t.start();        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JScrollPane jScroll_List;
    private javax.swing.JPanel jP_jrb;
    private javax.swing.JRadioButton jrb_backup_restore;
    private javax.swing.JRadioButton jrb_addConf;
    private javax.swing.JRadioButton jrb_createIndex;
    private javax.swing.JRadioButton jrb_delConf;
    private javax.swing.JRadioButton jrb_clone_conf;
    private javax.swing.JRadioButton jrb_modConf;
    private javax.swing.JRadioButton jrb_confHelp;
    private javax.swing.JRadioButton jrb_plugins;
    private javax.swing.JRadioButton jrb_AlarmServer;
    private javax.swing.JRadioButton jrb_report;
    private javax.swing.JRadioButton jrb_sendConf;
    private javax.swing.JRadioButton jrb_users_mang;
    private javax.swing.JButton jB_reload;
    private javax.swing.JButton jB_next;
    private javax.swing.JComboBox jCBox_index_lib;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JRadioButton jrb_noSelected;
    private ADAMS_JF_ConfigHelp      jf_confHelp         = null;
    private ADAMS_JF_Report          jf_confReport       = null;
    private IconPool ip = null;
    private JListIcon jListConfName                     = null;
    
    private Thread t        = null;
    //private boolean TH_EXIT = false;
    private int OperationTH   = -1;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private Vector V_Conf_name = null;
    
    boolean flagAppoggio = true;    
    
    ADAMS_JF_AddMod_conf jf_addConf = null;
    
    private Vector V_NameConfDefault;
    
    private Vector V_Desc_index = null;
    private Vector V_ID_index = null;
}
    //private boolean RS_EMPTY = true;

