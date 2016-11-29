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
import java.util.Vector;
import java.awt.Dimension;

public class ADAMS_JD_Configuration_Clone extends javax.swing.JDialog implements Runnable{

    /** Creates new form ADAMS_JD_ViewRelations */
    public ADAMS_JD_Configuration_Clone(ADAMS_JF_Wizard parent, boolean modal) {
        super(parent, modal);
        this.Local_parent = parent;
        initComponents();
        
        jScroll_List.setBorder(new javax.swing.border.TitledBorder(null, " Present Configurations ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, ADAMS_GlobalParam.font_B11));
      
        jT_nameclone.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        this.setTitle("Clone Configurations");
        
        jL_Title.setFont(ADAMS_GlobalParam.font_B12);
        jL_Name_conf.setFont(ADAMS_GlobalParam.font_B11);
        jL_1.setFont(ADAMS_GlobalParam.font_B11);
        jL_2.setFont(ADAMS_GlobalParam.font_B11);
        jT_nameclone.setFont(ADAMS_GlobalParam.font_B11);
        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jCBox_indexDATA.setFont(ADAMS_GlobalParam.font_B11);
        
        
        ip = new IconPool("/images/");
        jListConfName = new JListIcon(ip);
        jListConfName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_List.setViewportView(jListConfName);   
        
        event_jListConfNam = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListConfNameMousePressed(evt);
            }
        }
        );
        
        //-------------
        jCBox_indexDATA.addItem(new String("None"));
        
        String select1 = "select DESC_INDICE from  l_index_lib order by DESC_INDICE";
        V_Desc_index  = ADAMS_GlobalParam.connect_Oracle.Query(select1);
        String select2 = "select ID_INDICE from  l_index_lib order by DESC_INDICE";
        V_ID_index  = ADAMS_GlobalParam.connect_Oracle.Query(select2);
        
        for(int i=0; i<V_Desc_index.size(); i++)
            jCBox_indexDATA.addItem((String) V_Desc_index.elementAt(i));
        //-------------
        

        jB_clone.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        jCBox_indexDATA.setCursor(Cur_hand);
        NAME_CONF_INDEX ="";
        
        setCenteredFrame(610,450); 
        read_TableImage();
        
        //show();
        this.setVisible(true);       
    }
    
    public ADAMS_JD_Configuration_Clone(ADAMS_JF_Wizard parent, boolean modal, String Title, boolean createIndexconf) 
    {
        super(parent, modal);
        this.Local_parent = parent;
        initComponents();
        
        jScroll_List.setBorder(new javax.swing.border.TitledBorder(null, " Present Configurations ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, ADAMS_GlobalParam.font_B11));
      
        jT_nameclone.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        
        ////////
        this.setTitle(Title);
        this.jL_Title.setText(Title);
        NAME_CONF_INDEX ="";
        INDEX_CONFIGURATION = createIndexconf;
        if(INDEX_CONFIGURATION == true)        
            jT_nameclone.setEditable(false);
        ///////
            
        jL_Title.setFont(ADAMS_GlobalParam.font_B12);
        jL_Name_conf.setFont(ADAMS_GlobalParam.font_B11);
        jL_1.setFont(ADAMS_GlobalParam.font_B11);
        jL_2.setFont(ADAMS_GlobalParam.font_B11);
        jT_nameclone.setFont(ADAMS_GlobalParam.font_B11);
        jLabel1.setFont(ADAMS_GlobalParam.font_B11);
        jCBox_indexDATA.setFont(ADAMS_GlobalParam.font_B11);
        
        ip = new IconPool("/images/");
        jListConfName = new JListIcon(ip);
        jListConfName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_List.setViewportView(jListConfName);   
        
        event_jListConfNam = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListConfNameMousePressed(evt);
            }
        }
        );
        
        //-------------
        jCBox_indexDATA.addItem(new String("None"));
        
        String select1 = "select DESC_INDICE from  l_index_lib order by DESC_INDICE";
        V_Desc_index  = ADAMS_GlobalParam.connect_Oracle.Query(select1);
        String select2 = "select ID_INDICE from  l_index_lib order by DESC_INDICE";
        V_ID_index  = ADAMS_GlobalParam.connect_Oracle.Query(select2);
        
        for(int i=0; i<V_Desc_index.size(); i++)
            jCBox_indexDATA.addItem((String) V_Desc_index.elementAt(i));
        //-------------

        jB_clone.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        jCBox_indexDATA.setCursor(Cur_hand);

        setCenteredFrame(600,450); 
        read_TableImage();
        
        //show();
        this.setVisible(true);
       
    }
    
   
    public void read_TableImage()
    {
        this.setCursor(Cur_wait);        
        jListConfName.removeMouseListener(event_jListConfNam);
        
        V_Conf_name.removeAllElements();
        V_Conf_Group_ID.removeAllElements();
        V_Conf_name_INDICE.removeAllElements();
        
        int ID_GroupINDEX_selected = jCBox_indexDATA.getSelectedIndex();
            
        String str_where1 = "";
        if(ID_GroupINDEX_selected > 0)
        {
            str_where1 = " and ID_INDICE = "+(String)V_ID_index.elementAt(ID_GroupINDEX_selected-1)+" ";
        }
                
        String sel_ConfName = "select TIPO_DI_CONFIGURAZIONE from tab_info_config where CONF_DEFAULT !='Y' "+str_where1+"order by TIPO_DI_CONFIGURAZIONE";
        V_Conf_name  = ADAMS_GlobalParam.connect_Oracle.Query(sel_ConfName);
        
        if(INDEX_CONFIGURATION == true)
        {
            String sel_Conf_GroupID = "select ID_INDICE from tab_info_config where CONF_DEFAULT !='Y' "+str_where1+"order by TIPO_DI_CONFIGURAZIONE";
            V_Conf_Group_ID = ADAMS_GlobalParam.connect_Oracle.Query(sel_Conf_GroupID);
            
            String sel_ConfName_INDICED = "select TIPO_DI_CONFIGURAZIONE from tab_info_config where CONF_DEFAULT ='Y' "+str_where1+"order by TIPO_DI_CONFIGURAZIONE";
            V_Conf_name_INDICE = ADAMS_GlobalParam.connect_Oracle.Query(sel_ConfName_INDICED);
        }
            
        NAME_SELECTED = "";
        jT_nameclone.setText("");
        jL_Name_conf.setText(NAME_SELECTED);
        jB_clone.setEnabled(false);

        jListConfName.removeAll();            
        int SIZE = V_Conf_name.size();
        if( SIZE == 0 )
        {
            jListConfName.addElement("no_conf.png","no_conf.png",strConfNotFound);     
        }
        else
        {
            for(int i=0; i<SIZE; i++)
            {
                jListConfName.addElement("conf.png","conf_sel.png",(String)V_Conf_name.elementAt(i));
            }
        }            
             
        jListConfName.addMouseListener(event_jListConfNam);
        this.setCursor(Cur_default);
    }
    
    
      
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_Title = new javax.swing.JLabel();
        jScroll_List = new javax.swing.JScrollPane();
        jL_Name_conf = new javax.swing.JLabel();
        jL_1 = new javax.swing.JLabel();
        jL_2 = new javax.swing.JLabel();
        jT_nameclone = new javax.swing.JTextField();
        jP_button = new javax.swing.JPanel();
        jB_clone = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        jP_south = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        jL_status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCBox_indexDATA = new javax.swing.JComboBox();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clone.png")));
        jL_Title.setText("Clone Configuration ");
        jL_Title.setMinimumSize(new java.awt.Dimension(149, 48));
        jL_Title.setPreferredSize(new java.awt.Dimension(150, 48));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jL_Title, gridBagConstraints1);
        
        jScroll_List.setMinimumSize(new java.awt.Dimension(300, 200));
        jScroll_List.setPreferredSize(new java.awt.Dimension(300, 200));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.gridheight = 6;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        getContentPane().add(jScroll_List, gridBagConstraints1);
        
        jL_Name_conf.setBackground(new java.awt.Color(230, 230, 230));
        jL_Name_conf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Name_conf.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Name_conf.setMinimumSize(new java.awt.Dimension(150, 24));
        jL_Name_conf.setPreferredSize(new java.awt.Dimension(170, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(1, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_Name_conf, gridBagConstraints1);
        
        jL_1.setBackground(new java.awt.Color(230, 230, 230));
        jL_1.setText("Configuration to Clone");
        jL_1.setMinimumSize(new java.awt.Dimension(180, 24));
        jL_1.setPreferredSize(new java.awt.Dimension(200, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 1, 5);
        getContentPane().add(jL_1, gridBagConstraints1);
        
        jL_2.setText("New name Configuration");
        jL_2.setMinimumSize(new java.awt.Dimension(150, 24));
        jL_2.setPreferredSize(new java.awt.Dimension(170, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 1, 5);
        getContentPane().add(jL_2, gridBagConstraints1);
        
        jT_nameclone.setToolTipText("(Max 30 Characters)");
        jT_nameclone.setMinimumSize(new java.awt.Dimension(150, 24));
        jT_nameclone.setPreferredSize(new java.awt.Dimension(170, 24));
        jT_nameclone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_namecloneKeyReleased(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(1, 5, 5, 5);
        getContentPane().add(jT_nameclone, gridBagConstraints1);
        
        jP_button.setBackground(new java.awt.Color(230, 230, 230));
        jP_button.setOpaque(false);
        jB_clone.setBackground(new java.awt.Color(230, 230, 230));
        jB_clone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_clone.jpg")));
        jB_clone.setToolTipText("Clone Configuration");
        jB_clone.setBorderPainted(false);
        jB_clone.setContentAreaFilled(false);
        jB_clone.setFocusPainted(false);
        jB_clone.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_clone.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_clone.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_clone_press.jpg")));
        jB_clone.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_clone_over.jpg")));
        jB_clone.setEnabled(false);
        jB_clone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cloneActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_clone);
        
        jB_cancel.setBackground(new java.awt.Color(230, 230, 230));
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.jpg")));
        jB_cancel.setToolTipText("Cancel");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setMinimumSize(new java.awt.Dimension(56, 28));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_cancel);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 6;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jP_button, gridBagConstraints1);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setOpaque(false);
        jB_close.setBackground(new java.awt.Color(230, 230, 230));
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMaximumSize(new java.awt.Dimension(100, 22));
        jB_close.setMinimumSize(new java.awt.Dimension(100, 30));
        jB_close.setPreferredSize(new java.awt.Dimension(100, 30));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_south.add(jB_close, java.awt.BorderLayout.SOUTH);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 8;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jP_south, gridBagConstraints1);
        
        jL_status.setBackground(new java.awt.Color(230, 230, 230));
        jL_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_status.setMinimumSize(new java.awt.Dimension(45, 30));
        jL_status.setPreferredSize(new java.awt.Dimension(45, 30));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(10, 4, 5, 4);
        getContentPane().add(jL_status, gridBagConstraints1);
        
        jLabel1.setText("Configuration Name Alias filter:");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 4, 0, 4);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jLabel1, gridBagConstraints1);
        
        jCBox_indexDATA.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_indexDATA.setMinimumSize(new java.awt.Dimension(150, 22));
        jCBox_indexDATA.setPreferredSize(new java.awt.Dimension(150, 22));
        jCBox_indexDATA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_indexDATAActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(0, 4, 0, 4);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jCBox_indexDATA, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jCBox_indexDATAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_indexDATAActionPerformed
        read_TableImage();
    }//GEN-LAST:event_jCBox_indexDATAActionPerformed

    private void jT_namecloneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_namecloneKeyReleased
        if((jL_Name_conf.getText().length() == 0) || (jT_nameclone.getText().trim().length() == 0))
            jB_clone.setEnabled(false);
        else
            jB_clone.setEnabled(true);
    }//GEN-LAST:event_jT_namecloneKeyReleased

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        NAME_SELECTED = "";
        jL_Name_conf.setText(NAME_SELECTED);        
        jListConfName.clearSelection();
        jL_status.setOpaque(false);
        jL_status.setText("");
        jT_nameclone.setText("");
        jB_clone.setEnabled(false);
       
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_cloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cloneActionPerformed
        
        String NameClone = jT_nameclone.getText().trim().toLowerCase();
        
        for(int i=0; i<V_Conf_name.size(); i++)
        {
            String name_conf = (String)V_Conf_name.elementAt(i);
            if(NameClone.equals(name_conf.toLowerCase()))
            {
                ADAMS_GlobalParam.optionPanel(this,"Configuration Name already Exists.", "WARNING",4);
                return;
            } 
        }
                
        if(INDEX_CONFIGURATION == true)
        {
            for(int x=0; x<V_Conf_name_INDICE.size(); x++)
            {
                String NameConfIndice = (String)V_Conf_name_INDICE.elementAt(x);
                if(NameClone.equals(NameConfIndice.toLowerCase()))
                {
                    ADAMS_GlobalParam.optionPanel(this,"Configuration INDEX already Exists.", "WARNING",4);
                    return;
                } 
            }
        }
        
        jL_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_animated.gif")));
        jL_status.setOpaque(false);
        jL_status.setText("Start Clone...");
            
        OPERATION_TH = RUN_CLONE;
        
        TH = null;
        TH = new Thread(this,"cloneConf()");
        TH.start();
    }//GEN-LAST:event_jB_cloneActionPerformed

    
    public void run()
    {
        TH_WORKING = true;        
        
        this.setCursor(Cur_wait);
        jB_clone.setEnabled(false);
        jB_cancel.setEnabled(false);   

        //Lock Table
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            TH_WORKING = false;
            jL_status.setIcon(null);
            
            this.setCursor(Cur_default);
            return;
        }
        
        boolean conn = connectionCorba();
        
        if(OPERATION_TH == RUN_CLONE)
        {
            jL_status.setText("RUN Backup...");
            
            boolean f_clone = false;
            if(conn == true)
                f_clone = CORBA.cloneOracleConf(NAME_SELECTED,jT_nameclone.getText().trim());
            
            if(f_clone == true)
            {
                String str_ok = "OK Clone Configurations.";
                String INDEX_CONF_FLAG = " set CONF_DEFAULT='N' ";
                if(INDEX_CONFIGURATION == true)
                {
                    INDEX_CONF_FLAG = " set CONF_DEFAULT='Y',FILE_NAME_CONF ='' ";
                    str_ok = "OK Clone for Index Configurations.";
                }
                
                String str_update_CONF_DEFAUL = ("update tab_info_config "+ INDEX_CONF_FLAG +"where TIPO_DI_CONFIGURAZIONE='"+NAME_CONF_INDEX+"'");

                int Answer_update = ADAMS_GlobalParam.connect_Oracle.Operation(str_update_CONF_DEFAUL);
                
                //System.out.println(" str_update_CONF_DEFAUL "+str_update_CONF_DEFAUL);
                //System.out.println(" Answer_update "+Answer_update);
                
                
                /*try
                { 
                    if(Answer_update >= 0)
                        ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                    else
                        System.out.println("ERR. non settata CONF_DEFAULT");
                }
                catch(java.sql.SQLException exc)
                {
                    System.out.println("Errore ADAMS_TAB_INFO_CONFIG.java IndexConf");
                    try
                    { 
                        ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
                    }
                    catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_TAB_INFO_CONFIG.java IndexConf_A");}
                }    
                 */
                jL_status.setOpaque(true);
                jL_status.setBackground(java.awt.Color.green.darker());
                jL_status.setText(str_ok);
                
                read_TableImage();
                this.Local_parent.getNameAnalisi();
            }
            else
            {
                jL_status.setOpaque(true);
                jL_status.setBackground(java.awt.Color.red);
                
                if(INDEX_CONFIGURATION == true)
                    jL_status.setText("FAILURE Clone for Index Configurations.");
                else
                    jL_status.setText("FAILURE Clone Configurations.");
                jB_clone.setEnabled(true);
            }
        }
        
        CORBA.shutDown_Conf();
        
        jB_cancel.setEnabled(true);
        this.setCursor(Cur_default);        
        jL_status.setIcon(null);
        TH_WORKING = false;
    }
    
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
    
    private void jListConfNameMousePressed(java.awt.event.MouseEvent evt)
    {
        NAME_SELECTED = jListConfName.getSelectedValue().toString().trim();
        
        if(NAME_SELECTED.equals(strConfNotFound))
            return;
        
        jL_Name_conf.setText(NAME_SELECTED);
        jL_status.setText("");
        jL_status.setOpaque(false);
        
        if(INDEX_CONFIGURATION == true)
        {
            String name_index = "";
            boolean flag_break = false;
            for(int i=0; i<V_Conf_name.size(); i++)
            {
                String nameConfAppo = (String)V_Conf_name.elementAt(i);
                if(nameConfAppo.equals(NAME_SELECTED))
                {
                    String ID_INDICE_GROUP = (String)V_Conf_Group_ID.elementAt(i);
                    for(int x=0; x<V_ID_index.size(); x++)
                    {
                        if(ID_INDICE_GROUP.equals((String)V_ID_index.elementAt(x)))
                        {
                            name_index = (String)V_Desc_index.elementAt(x);
                            flag_break = true;
                            break;
                        }
                    }
                    if(flag_break)
                        break;
                }
            }
            NAME_CONF_INDEX = "INDEX_"+name_index; 
            jT_nameclone.setText(NAME_CONF_INDEX);
        }       
        
        if((jL_Name_conf.getText().length() == 0) || (jT_nameclone.getText().trim().length() == 0))
            jB_clone.setEnabled(false);
        else
            jB_clone.setEnabled(true);
    }
        
    
    public String getNameImage()
    {
        return this.NAME_SELECTED;
    }
        
    
    private void closeFrame()
    {        
        if(TH_WORKING == false)
        {
            setVisible(false);
            dispose();
        }
    }
    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        closeFrame();      
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeFrame();         
    }//GEN-LAST:event_closeDialog

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Title;
    private javax.swing.JScrollPane jScroll_List;
    private javax.swing.JLabel jL_Name_conf;
    private javax.swing.JLabel jL_1;
    private javax.swing.JLabel jL_2;
    private javax.swing.JTextField jT_nameclone;
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jB_clone;
    private javax.swing.JButton jB_cancel;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_close;
    private javax.swing.JLabel jL_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jCBox_indexDATA;
    // End of variables declaration//GEN-END:variables
    
    private CORBAConnection CORBA = null; 
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
   
    private IconPool ip = null;
    private JListIcon jListConfName = null;
    private java.awt.event.MouseAdapter event_jListConfNam;
    
    private String strConfNotFound = "Configuration Not Found";
    private String NAME_SELECTED = "";
    
    private Thread TH           = null;
    private boolean TH_WORKING  = false;
    private int OPERATION_TH    = -1;    
    private final  int RUN_CLONE  =  1;
    
    private Vector V_Conf_name      = new Vector();
    private Vector V_Conf_Group_ID  = new Vector();
    private Vector V_Conf_name_INDICE = new Vector();
    
    private boolean INDEX_CONFIGURATION = false;
    private String NAME_CONF_INDEX ="";
    
    private Vector V_Desc_index = null;
    private Vector V_ID_index   = null;
    
    private ADAMS_JF_Wizard Local_parent = null;
    
}
