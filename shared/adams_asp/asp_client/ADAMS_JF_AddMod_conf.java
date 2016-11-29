/*
 * ADAMS_JF_AddMod_conf.java
 *
 * Created on 24 maggio 2005, 14.06
 */

/**
 *
 * @author  root
 */
import java.awt.Cursor;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class ADAMS_JF_AddMod_conf extends javax.swing.JFrame implements Runnable {

    /** Creates new form ADAMS_JF_AddMod_conf */
    public ADAMS_JF_AddMod_conf(ADAMS_JF_Wizard Parent,String Title,int Modality,String config_NAME_ALIAS) //Modality - 0 = new; 1 = modify; 2 = delete;
    {        
        initComponents();       
        
        this.localParent = Parent;
        this.local_INFO_CONFIG = new ADAMS_TAB_INFO_CONFIG(config_NAME_ALIAS);
        
        if(config_NAME_ALIAS.length() > 0 )
            this.local_INFO_CONFIG.InfoConfigName(config_NAME_ALIAS);

        JP_InputData_1  = new ADAMS_JP_InputData(this.local_INFO_CONFIG);
        JP_InputData_1.setParentJFrame(this);
        
        JTab_TE_EXC_REL = new ADAMS_JTab_TE_EXC_REL(this.local_INFO_CONFIG,this);
        //JP_Relations    = new ADAMS_JP_Relations();

        currentJPanel = jP_info_0; // pannello attuale wizard
        
        gridBagConstraint_W = new java.awt.GridBagConstraints();
        gridBagConstraint_W.gridx = 0;
        gridBagConstraint_W.gridy = 1;
        gridBagConstraint_W.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraint_W.ipadx = 600;
        gridBagConstraint_W.ipady = 450;
        gridBagConstraint_W.weightx = 1.0;
        gridBagConstraint_W.weighty = 0.5;
        
        jTF_ConName.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));
        JTF_default_path.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,1536));
        jT_pluginName.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,256));
        jTF_specifyPath.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,1536));
        jTF_author.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,160));
        jTF_dataLength.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,5));
        
        
        for(int i=0; i<byteboundary.length; i++)
            jCBox_ByteBoundary.addItem(""+byteboundary[i]);
              
        
        // Font Globali
        jL_desc1.setFont(ADAMS_GlobalParam.font_B11);
        jTF_ConName.setFont(ADAMS_GlobalParam.font_B11);
        jL_desc2.setFont(ADAMS_GlobalParam.font_B11);
        JTF_default_path.setFont(ADAMS_GlobalParam.font_B11);
        jL_desc3.setFont(ADAMS_GlobalParam.font_B11);
        jT_pluginName.setFont(ADAMS_GlobalParam.font_B11);
        jTF_specifyPath.setFont(ADAMS_GlobalParam.font_B11);
        jL_desc4.setFont(ADAMS_GlobalParam.font_B11);
        jTF_author.setFont(ADAMS_GlobalParam.font_B11);
        jL_desc5.setFont(ADAMS_GlobalParam.font_B11);
        jTF_lastTime.setFont(ADAMS_GlobalParam.font_B11);        
        jrb_defaultPath.setFont(ADAMS_GlobalParam.font_B11); 
        jrb_specify.setFont(ADAMS_GlobalParam.font_B11);
        jLabel1.setFont(ADAMS_GlobalParam.font_B9);
        jL_length.setFont(ADAMS_GlobalParam.font_B11);
        jL_length.setFont(ADAMS_GlobalParam.font_B11);
        jTF_dataLength.setFont(ADAMS_GlobalParam.font_B11);
        jCBox_ByteBoundary.setFont(ADAMS_GlobalParam.font_B11);
        jL_ByteBoundary.setFont(ADAMS_GlobalParam.font_B11);
        jL_IndexFileName.setFont(ADAMS_GlobalParam.font_B11);
        jCB_DataGroup.setFont(ADAMS_GlobalParam.font_B11);
        jLabel3.setFont(ADAMS_GlobalParam.font_B11);
        
        // Cursor
        //jCBox_defaultConf.setCursor(Cur_hand);
        jrb_defaultPath.setCursor(Cur_hand);
        jrb_specify.setCursor(Cur_hand);
        jB_back.setCursor(Cur_hand);
        jB_next.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jrb_enable.setCursor(Cur_hand);
        jrb_disable.setCursor(Cur_hand);
        jBcommit.setCursor(Cur_hand);
        jCBox_ByteBoundary.setCursor(Cur_hand);
        jB_editPL.setCursor(Cur_hand);
        jCB_DataGroup.setCursor(Cur_hand);

        setTitle(Title);
        
          //-------------
        jCB_DataGroup.addItem(new String(DataGroup_NotAssigned));
        
        String select1      = "select DESC_INDICE from  l_index_lib order by DESC_INDICE";
        V_DataGroup_desc    = ADAMS_GlobalParam.connect_Oracle.Query(select1);
        
        String select2      = "select ID_INDICE from  l_index_lib order by DESC_INDICE";
        V_DataGroup_ID      = ADAMS_GlobalParam.connect_Oracle.Query(select2);
        
        String select3          = "select NOME_FILE from  l_index_lib order by DESC_INDICE";
        V_DataGroup_NameFile    = ADAMS_GlobalParam.connect_Oracle.Query(select3);
        
        for(int i=0; i<V_DataGroup_desc.size(); i++)
            jCB_DataGroup.addItem((String) V_DataGroup_desc.elementAt(i));
        //-------------
        
        setGUIModality(Modality);        
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        setCenteredFrame(jP_info_0_WIDTH,jP_info_0_HEIGHT);
        //show();
        this.setVisible(true);
        
        ADAMS_GlobalParam.Global_lastTime = jTF_lastTime;
        ADAMS_GlobalParam.JFRAME_TAB = this;
    }

    private void setGUIModality(int Modality)
    {
        this.modality = Modality; //Modality - 0 = new; -1 = modify; 2 = delete;
        
        if(modality == 0) // 0 = new;
        {
            jB_back.setVisible(false);
            jB_next.setVisible(false);          
            jrb_enable.setSelected(true);
            setAbilityAllItem(true);
            
            jBcommit.setEnabled(false);
            jT_pluginName.setEditable(true);
            
            jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/title_new_conf.gif")));
        }
        if(modality == 1) // 1 = modify
        {
            jB_back.setVisible(false);
            jBcommit.setEnabled(false);
            
            jrb_disable.setSelected(true);
            setAbilityAllItem(false);
            jT_pluginName.setEditable(false);
            jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/title_edit_conf.gif")));
            
            if(this.local_INFO_CONFIG.CONF_DEFAULT == 'Y')
            {
                jrb_disable.setEnabled(false);
                jrb_enable.setEnabled(false);
                
                jLabel1.setFont(ADAMS_GlobalParam.font_B11);
                jLabel1.setText("INDEX CONFIGURATION (read only)");
                jLabel1.setToolTipText("INDEX CONFIGURATION (read only)");
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analysis_22.png")));
            }
            
            //System.out.println("-- AddMod_conf local_INFO_CONFIG.CONF_DEFAULT = "+local_INFO_CONFIG.CONF_DEFAULT);
            
        }
        if(modality == 2) // 2 = delete
        {
            jB_back.setVisible(false);
            jB_next.setVisible(false);
            jrb_disable.setEnabled(false);
            jrb_enable.setEnabled(false);
            
            jrb_disable.setSelected(true);
            setAbilityAllItem(false);
           
            jTF_ConName.setEnabled(false);
            JTF_default_path.setEnabled(false);
            jCB_DataGroup.setEnabled(false);
            jT_pluginName.setEnabled(false);
            jB_editPL.setEnabled(false);
            jTF_dataLength.setEnabled(false);
            jTF_specifyPath.setEnabled(false);
            jTF_author.setEnabled(false);
            jTF_lastTime.setEnabled(false);       
            jrb_defaultPath.setEnabled(false);
            jrb_specify.setEnabled(false);
            jCBox_ByteBoundary.setEnabled(false);
            jT_pluginName.setEditable(false);
            
            jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/title_del_conf.gif")));
        }
                
        jT_pluginName.setText(this.local_INFO_CONFIG.DR_PLUGIN_NAME);             
        jTF_dataLength.setText(""+this.local_INFO_CONFIG.DR_REC_LEN);            
        
        jrb_defaultPath.setSelected(false);
        if( this.local_INFO_CONFIG.DR_FLAG_USE_PATH == 1)
        {
            jrb_specify.setSelected(true);
        }     
        
        jTF_specifyPath.setText(this.local_INFO_CONFIG.DR_SPECIFY_PL_PATH);   
        
        jTF_ConName.setText(this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
        JTF_default_path.setText(this.local_INFO_CONFIG.GLB_DEFAULT_PATH_PL);
        jTF_author.setText(this.local_INFO_CONFIG.AUTHOR);
               
        jCB_DataGroup.setSelectedIndex(set_jCBindex_DataGroup(this.local_INFO_CONFIG.ID_INDICE));
        jL_IndexFileName.setText(get_DataGroup_NameFile((String)jCB_DataGroup.getSelectedItem()));

        //System.out.println("-------> ID_INDICE "+local_INFO_CONFIG.ID_INDICE);

        // *** BOUNDARY
        int index_boundSel = 0;
        for(int i=0;i<byteboundary.length; i++)
        {
            if(this.local_INFO_CONFIG.BOUNDARY == byteboundary[i] )
            {
                index_boundSel = i;
                break;
            }
        }        
        jCBox_ByteBoundary.setSelectedIndex(index_boundSel);
        // *** 
        
        
        if(this.local_INFO_CONFIG.LAST_MODIFICATION_TIME.equals(""))
            jTF_lastTime.setText("(automatically updated)");
        else
            jTF_lastTime.setText(this.local_INFO_CONFIG.LAST_MODIFICATION_TIME); 
    
    }
    
    private int set_jCBindex_DataGroup(int id_indice)
    {        
        for(int i=0; i<V_DataGroup_ID.size(); i++)
        {
            if(id_indice == ((new Integer((String)V_DataGroup_ID.elementAt(i))).intValue()) )
                return i+1;
        }
        return 0;
    }
    
    private int get_jCB_DataGroupIndex(String strGroupDesc)
    {
        for(int i=0; i<V_DataGroup_desc.size(); i++)
        {
            if(strGroupDesc.equals((String)V_DataGroup_desc.elementAt(i)))
                return (new Integer((String)V_DataGroup_ID.elementAt(i)).intValue());
        }
        return -1;
    }
    
    private String get_DataGroup_NameFile(String strGroupDesc)
    {
        for(int i=0; i<V_DataGroup_desc.size(); i++)
        {
            if(strGroupDesc.equals((String)V_DataGroup_desc.elementAt(i)))
                return (String)V_DataGroup_NameFile.elementAt(i);
        }
        return "";
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        b_group_specifyPath = new javax.swing.ButtonGroup();
        b_group_ability = new javax.swing.ButtonGroup();
        jL_icon = new javax.swing.JLabel();
        jP_info_0 = new javax.swing.JPanel();
        jP_jrb_ability = new javax.swing.JPanel();
        jP_north = new javax.swing.JPanel();
        jrb_disable = new javax.swing.JRadioButton();
        jrb_enable = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jP_generalnfo = new javax.swing.JPanel();
        jL_desc1 = new javax.swing.JLabel();
        jTF_ConName = new javax.swing.JTextField();
        jL_desc2 = new javax.swing.JLabel();
        JTF_default_path = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCB_DataGroup = new javax.swing.JComboBox();
        jL_IndexFileName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jP_pluginInfo = new javax.swing.JPanel();
        jL_desc3 = new javax.swing.JLabel();
        jTF_specifyPath = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jrb_defaultPath = new javax.swing.JRadioButton();
        jrb_specify = new javax.swing.JRadioButton();
        jL_length = new javax.swing.JLabel();
        jTF_dataLength = new javax.swing.JTextField();
        jL_ByteBoundary = new javax.swing.JLabel();
        jCBox_ByteBoundary = new javax.swing.JComboBox();
        jB_editPL = new javax.swing.JButton();
        jT_pluginName = new javax.swing.JTextField();
        jP_editingInfo = new javax.swing.JPanel();
        jL_desc4 = new javax.swing.JLabel();
        jTF_author = new javax.swing.JTextField();
        jL_desc5 = new javax.swing.JLabel();
        jTF_lastTime = new javax.swing.JTextField();
        jP_button = new javax.swing.JPanel();
        jBcommit = new javax.swing.JButton();
        jB_back = new javax.swing.JButton();
        jB_next = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jL_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_icon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 10;
        gridBagConstraints1.ipady = 10;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jL_icon, gridBagConstraints1);
        
        jP_info_0.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_info_0.setBackground(new java.awt.Color(230, 230, 230));
        jP_info_0.setMaximumSize(new java.awt.Dimension(600, 450));
        jP_info_0.setMinimumSize(new java.awt.Dimension(600, 450));
        jP_info_0.setPreferredSize(new java.awt.Dimension(600, 450));
        jP_jrb_ability.setLayout(new java.awt.BorderLayout());
        
        jP_jrb_ability.setBackground(new java.awt.Color(230, 230, 230));
        jP_jrb_ability.setMinimumSize(new java.awt.Dimension(42, 40));
        jP_jrb_ability.setPreferredSize(new java.awt.Dimension(42, 40));
        jP_north.setLayout(new java.awt.GridLayout(1, 0));
        
        jP_north.setBackground(new java.awt.Color(230, 230, 230));
        jrb_disable.setBackground(new java.awt.Color(230, 230, 230));
        jrb_disable.setText("Disable Settings");
        b_group_ability.add(jrb_disable);
        jrb_disable.setContentAreaFilled(false);
        jrb_disable.setFocusPainted(false);
        jrb_disable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_disable.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jrb_disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_disable.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_disable.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_disable.setMinimumSize(new java.awt.Dimension(21, 25));
        jrb_disable.setPreferredSize(new java.awt.Dimension(21, 25));
        jrb_disable.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_disable.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_disable.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_disable.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jP_north.add(jrb_disable);
        
        jrb_enable.setBackground(new java.awt.Color(230, 230, 230));
        jrb_enable.setText("Enable Settings");
        b_group_ability.add(jrb_enable);
        jrb_enable.setContentAreaFilled(false);
        jrb_enable.setFocusPainted(false);
        jrb_enable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_enable.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jrb_enable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_enable.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_enable.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_enable.setMinimumSize(new java.awt.Dimension(21, 25));
        jrb_enable.setPreferredSize(new java.awt.Dimension(21, 25));
        jrb_enable.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_enable.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_enable.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_enable.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_enable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_enableItemStateChanged(evt);
            }
        });
        
        jP_north.add(jrb_enable);
        
        jP_jrb_ability.add(jP_north, java.awt.BorderLayout.CENTER);
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analysis_22.png")));
        jLabel1.setText("(*) Mandatory Field");
        jLabel1.setToolTipText("Mandatory Field");
        jP_jrb_ability.add(jLabel1, java.awt.BorderLayout.SOUTH);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jP_info_0.add(jP_jrb_ability, gridBagConstraints2);
        
        jP_generalnfo.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_generalnfo.setBackground(new java.awt.Color(230, 230, 230));
        jP_generalnfo.setBorder(new javax.swing.border.TitledBorder(null, " General Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_generalnfo.setMinimumSize(new java.awt.Dimension(350, 100));
        jP_generalnfo.setPreferredSize(new java.awt.Dimension(350, 100));
        jL_desc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/conf_blue.png")));
        jL_desc1.setText("Configuration Name (*)");
        jL_desc1.setMinimumSize(new java.awt.Dimension(110, 20));
        jL_desc1.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 6, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jL_desc1, gridBagConstraints3);
        
        jTF_ConName.setToolTipText("(Max 30 Characters)");
        jTF_ConName.setDisabledTextColor(java.awt.Color.gray);
        jTF_ConName.setMinimumSize(new java.awt.Dimension(130, 22));
        jTF_ConName.setPreferredSize(new java.awt.Dimension(130, 22));
        jTF_ConName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFs_KeyReleased(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 6, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jTF_ConName, gridBagConstraints3);
        
        jL_desc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plugin_default.png")));
        jL_desc2.setText("Default Plugins Path (*)");
        jL_desc2.setMinimumSize(new java.awt.Dimension(110, 20));
        jL_desc2.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jL_desc2, gridBagConstraints3);
        
        JTF_default_path.setDisabledTextColor(java.awt.Color.gray);
        JTF_default_path.setMinimumSize(new java.awt.Dimension(130, 22));
        JTF_default_path.setPreferredSize(new java.awt.Dimension(130, 22));
        JTF_default_path.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFs_KeyReleased(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(JTF_default_path, gridBagConstraints3);
        
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Input Data Group");
        jLabel2.setMinimumSize(new java.awt.Dimension(110, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jLabel2, gridBagConstraints3);
        
        jCB_DataGroup.setBackground(new java.awt.Color(230, 230, 230));
        jCB_DataGroup.setMinimumSize(new java.awt.Dimension(65, 22));
        jCB_DataGroup.setPreferredSize(new java.awt.Dimension(65, 22));
        jCB_DataGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_DataGroupActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 4);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jCB_DataGroup, gridBagConstraints3);
        
        jL_IndexFileName.setBorder(new javax.swing.border.EtchedBorder());
        jL_IndexFileName.setMinimumSize(new java.awt.Dimension(65, 22));
        jL_IndexFileName.setPreferredSize(new java.awt.Dimension(65, 22));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 4, 1, 2);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jL_IndexFileName, gridBagConstraints3);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(7, 0, 7, 0);
        jP_generalnfo.add(jSeparator1, gridBagConstraints3);
        
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Index File Name");
        jLabel3.setMinimumSize(new java.awt.Dimension(110, 20));
        jLabel3.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_generalnfo.add(jLabel3, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.ipadx = 350;
        gridBagConstraints2.ipady = 115;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 0.5;
        gridBagConstraints2.weighty = 1.0;
        jP_info_0.add(jP_generalnfo, gridBagConstraints2);
        
        jP_pluginInfo.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_pluginInfo.setBackground(new java.awt.Color(230, 230, 230));
        jP_pluginInfo.setBorder(new javax.swing.border.TitledBorder(null, " Input Data Plugin Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_pluginInfo.setMaximumSize(new java.awt.Dimension(350, 84));
        jP_pluginInfo.setMinimumSize(new java.awt.Dimension(350, 84));
        jP_pluginInfo.setPreferredSize(new java.awt.Dimension(350, 84));
        jL_desc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plugin_specify.png")));
        jL_desc3.setText("Plugin Name (*)");
        jL_desc3.setMaximumSize(new java.awt.Dimension(164, 20));
        jL_desc3.setMinimumSize(new java.awt.Dimension(164, 20));
        jL_desc3.setPreferredSize(new java.awt.Dimension(164, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.ipadx = 70;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.weighty = 1.0;
        jP_pluginInfo.add(jL_desc3, gridBagConstraints4);
        
        jTF_specifyPath.setEditable(false);
        jTF_specifyPath.setDisabledTextColor(java.awt.Color.gray);
        jTF_specifyPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFs_KeyReleased(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.gridwidth = 3;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.weightx = 1.0;
        jP_pluginInfo.add(jTF_specifyPath, gridBagConstraints4);
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMaximumSize(new java.awt.Dimension(146, 20));
        jPanel1.setMinimumSize(new java.awt.Dimension(146, 20));
        jPanel1.setPreferredSize(new java.awt.Dimension(146, 20));
        jrb_defaultPath.setBackground(new java.awt.Color(230, 230, 230));
        jrb_defaultPath.setSelected(true);
        jrb_defaultPath.setText("Default Path");
        b_group_specifyPath.add(jrb_defaultPath);
        jrb_defaultPath.setContentAreaFilled(false);
        jrb_defaultPath.setFocusPainted(false);
        jrb_defaultPath.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jrb_defaultPath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_defaultPath.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_defaultPath.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_defaultPath.setMinimumSize(new java.awt.Dimension(21, 20));
        jrb_defaultPath.setPreferredSize(new java.awt.Dimension(21, 20));
        jrb_defaultPath.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_defaultPath.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_defaultPath.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_defaultPath.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_defaultPath.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_defaultPathItemStateChanged(evt);
            }
        });
        
        jPanel1.add(jrb_defaultPath);
        
        jrb_specify.setBackground(new java.awt.Color(230, 230, 230));
        jrb_specify.setText("Specify");
        b_group_specifyPath.add(jrb_specify);
        jrb_specify.setContentAreaFilled(false);
        jrb_specify.setFocusPainted(false);
        jrb_specify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_specify.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jrb_specify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_specify.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_specify.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_specify.setMinimumSize(new java.awt.Dimension(21, 20));
        jrb_specify.setPreferredSize(new java.awt.Dimension(21, 20));
        jrb_specify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_specify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_specify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_specify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jPanel1.add(jrb_specify);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.ipadx = 70;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints4.weighty = 1.0;
        jP_pluginInfo.add(jPanel1, gridBagConstraints4);
        
        jL_length.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/null_icon.png")));
        jL_length.setText("Input Data Record Length (*)");
        jL_length.setToolTipText("Record lenght will be used to verify consistency.");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints4.weighty = 1.0;
        jP_pluginInfo.add(jL_length, gridBagConstraints4);
        
        jTF_dataLength.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_dataLength.setText("0");
        jTF_dataLength.setDisabledTextColor(java.awt.Color.gray);
        jTF_dataLength.setMinimumSize(new java.awt.Dimension(90, 20));
        jTF_dataLength.setPreferredSize(new java.awt.Dimension(90, 20));
        jTF_dataLength.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFs_KeyReleased(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jP_pluginInfo.add(jTF_dataLength, gridBagConstraints4);
        
        jL_ByteBoundary.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_ByteBoundary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/null_icon.png")));
        jL_ByteBoundary.setText("Byte Boundary");
        jL_ByteBoundary.setToolTipText("Record lenght will be used to verify consistency.");
        jL_ByteBoundary.setMinimumSize(new java.awt.Dimension(160, 20));
        jL_ByteBoundary.setPreferredSize(new java.awt.Dimension(160, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints4.weightx = 1.0;
        jP_pluginInfo.add(jL_ByteBoundary, gridBagConstraints4);
        
        jCBox_ByteBoundary.setBackground(java.awt.Color.white);
        jCBox_ByteBoundary.setMaximumRowCount(4);
        jCBox_ByteBoundary.setToolTipText("Byte Boundary");
        jCBox_ByteBoundary.setMinimumSize(new java.awt.Dimension(55, 22));
        jCBox_ByteBoundary.setPreferredSize(new java.awt.Dimension(55, 22));
        jCBox_ByteBoundary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_ByteBoundaryActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        jP_pluginInfo.add(jCBox_ByteBoundary, gridBagConstraints4);
        
        jB_editPL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_editPL.setToolTipText("Edit Plugin Name");
        jB_editPL.setBorderPainted(false);
        jB_editPL.setContentAreaFilled(false);
        jB_editPL.setFocusPainted(false);
        jB_editPL.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_editPL.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_editPL.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_editPL.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_editPL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_editPL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_editPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_editPLActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jP_pluginInfo.add(jB_editPL, gridBagConstraints4);
        
        jT_pluginName.setDisabledTextColor(java.awt.Color.gray);
        jT_pluginName.setMinimumSize(new java.awt.Dimension(45, 20));
        jT_pluginName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_pluginNameKeyReleased(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.gridwidth = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints4.weightx = 1.0;
        jP_pluginInfo.add(jT_pluginName, gridBagConstraints4);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.ipadx = 350;
        gridBagConstraints2.ipady = 100;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 0.5;
        gridBagConstraints2.weighty = 1.0;
        jP_info_0.add(jP_pluginInfo, gridBagConstraints2);
        
        jP_editingInfo.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_editingInfo.setBackground(new java.awt.Color(230, 230, 230));
        jP_editingInfo.setBorder(new javax.swing.border.TitledBorder(null, " Editing Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_editingInfo.setMaximumSize(new java.awt.Dimension(350, 84));
        jP_editingInfo.setMinimumSize(new java.awt.Dimension(350, 84));
        jP_editingInfo.setPreferredSize(new java.awt.Dimension(350, 84));
        jL_desc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/author.png")));
        jL_desc4.setText("Author");
        jL_desc4.setMaximumSize(new java.awt.Dimension(164, 20));
        jL_desc4.setMinimumSize(new java.awt.Dimension(164, 20));
        jL_desc4.setPreferredSize(new java.awt.Dimension(164, 20));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints5.ipadx = 70;
        gridBagConstraints5.insets = new java.awt.Insets(5, 2, 5, 2);
        gridBagConstraints5.weighty = 1.0;
        jP_editingInfo.add(jL_desc4, gridBagConstraints5);
        
        jTF_author.setDisabledTextColor(java.awt.Color.gray);
        jTF_author.setMaximumSize(new java.awt.Dimension(350, 20));
        jTF_author.setMinimumSize(new java.awt.Dimension(350, 20));
        jTF_author.setPreferredSize(new java.awt.Dimension(350, 20));
        jTF_author.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFs_KeyReleased(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.ipadx = 350;
        gridBagConstraints5.insets = new java.awt.Insets(5, 2, 5, 2);
        gridBagConstraints5.weightx = 1.0;
        jP_editingInfo.add(jTF_author, gridBagConstraints5);
        
        jL_desc5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/date.png")));
        jL_desc5.setText("Last Modification Time");
        jL_desc5.setMaximumSize(new java.awt.Dimension(164, 20));
        jL_desc5.setMinimumSize(new java.awt.Dimension(164, 20));
        jL_desc5.setPreferredSize(new java.awt.Dimension(164, 20));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints5.ipadx = 70;
        gridBagConstraints5.insets = new java.awt.Insets(5, 2, 5, 2);
        gridBagConstraints5.weighty = 1.0;
        jP_editingInfo.add(jL_desc5, gridBagConstraints5);
        
        jTF_lastTime.setBackground(new java.awt.Color(230, 230, 230));
        jTF_lastTime.setEditable(false);
        jTF_lastTime.setText("(automatically updated)");
        jTF_lastTime.setMaximumSize(new java.awt.Dimension(350, 20));
        jTF_lastTime.setMinimumSize(new java.awt.Dimension(350, 20));
        jTF_lastTime.setPreferredSize(new java.awt.Dimension(350, 20));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.ipadx = 350;
        gridBagConstraints5.insets = new java.awt.Insets(5, 2, 5, 2);
        gridBagConstraints5.weightx = 1.0;
        jP_editingInfo.add(jTF_lastTime, gridBagConstraints5);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.ipadx = 350;
        gridBagConstraints2.ipady = 90;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 0.5;
        gridBagConstraints2.weighty = 1.0;
        jP_info_0.add(jP_editingInfo, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 600;
        gridBagConstraints1.ipady = 450;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.5;
        getContentPane().add(jP_info_0, gridBagConstraints1);
        
        jP_button.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 15));
        
        jP_button.setBackground(new java.awt.Color(230, 230, 230));
        jP_button.setMinimumSize(new java.awt.Dimension(10, 30));
        jP_button.setName("[10, 30]");
        jP_button.setPreferredSize(new java.awt.Dimension(10, 30));
        jBcommit.setBackground(new java.awt.Color(230, 230, 230));
        jBcommit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jBcommit.setBorderPainted(false);
        jBcommit.setContentAreaFilled(false);
        jBcommit.setFocusPainted(false);
        jBcommit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jBcommit.setMaximumSize(new java.awt.Dimension(0, 0));
        jBcommit.setMinimumSize(new java.awt.Dimension(0, 0));
        jBcommit.setPreferredSize(new java.awt.Dimension(80, 30));
        jBcommit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jBcommit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jBcommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcommitActionPerformed(evt);
            }
        });
        
        jP_button.add(jBcommit);
        
        jB_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back.jpg")));
        jB_back.setBorderPainted(false);
        jB_back.setContentAreaFilled(false);
        jB_back.setFocusPainted(false);
        jB_back.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_back.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back_press.jpg")));
        jB_back.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back_over.jpg")));
        jB_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_backActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_back);
        
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
        
        jP_button.add(jB_next);
        
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
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
        
        jP_button.add(jB_cancel);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.ipadx = 600;
        gridBagConstraints1.ipady = 20;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jP_button, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jCB_DataGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_DataGroupActionPerformed
        
        //System.out.println("jCB_DataGroupActionPerformed");
        try
        {
            if(jCB_DataGroup.isEnabled()) 
            {
                //System.out.println("OK -- jCB_DataGroupActionPerformed "+get_DataGroup_NameFile((String)jCB_DataGroup.getSelectedItem()));
                jL_IndexFileName.setText(get_DataGroup_NameFile((String)jCB_DataGroup.getSelectedItem()));
                ctrlMandatoryField();
            }
        }
        catch (java.lang.NullPointerException e)
        {
            // Tempo zero entra nell'ActionPerformed
        }
        
    }//GEN-LAST:event_jCB_DataGroupActionPerformed

    private void jT_pluginNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_pluginNameKeyReleased
        ctrlMandatoryField();
    }//GEN-LAST:event_jT_pluginNameKeyReleased

    private void jB_editPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_editPLActionPerformed
                
        ADAMS_JD_setPlugin  JD_PluginSelect = new ADAMS_JD_setPlugin(this,true,700,400,this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,this.local_INFO_CONFIG.DR_PLUGIN_NAME);

        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        this.local_INFO_CONFIG.DR_PLUGIN_NAME = JD_PluginSelect.getPluginEnabled();
        
        if((modality == 0)&&(this.local_INFO_CONFIG.DR_PLUGIN_NAME.length() == 0)) //nel caso di new Configuration
            this.local_INFO_CONFIG.DR_PLUGIN_NAME = jT_pluginName.getText().trim();
        
        JD_PluginSelect.dispose();
        
        if(this.local_INFO_CONFIG.DR_PLUGIN_NAME.length() >0)    
            jT_pluginName.setText(this.local_INFO_CONFIG.DR_PLUGIN_NAME);
        else
            jT_pluginName.setText("");
            
        ctrlMandatoryField();
    }//GEN-LAST:event_jB_editPLActionPerformed

    private void jCBox_ByteBoundaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_ByteBoundaryActionPerformed
       if(modality > 0)
       {
            if(jCBox_ByteBoundary.isEnabled())
                jBcommit.setEnabled(true);
       }
    }//GEN-LAST:event_jCBox_ByteBoundaryActionPerformed

    private void ctrlMandatoryField()
    {
        int countChar1 = jTF_ConName.getText().length();
        int countChar2 = JTF_default_path.getText().length();
        int countChar3 = jT_pluginName.getText().length();
        
        int countChar4 = 0;
        try{countChar4 = new Integer(jTF_dataLength.getText().trim()).intValue();}
        catch(java.lang.NumberFormatException e){}
        
        String strDataDroup = new String((String)jCB_DataGroup.getSelectedItem());
        
        if( (countChar1 > 0)&&(countChar2 > 0)&&(countChar3 > 0)&&(countChar4 > 0) && (strDataDroup.compareTo(DataGroup_NotAssigned) != 0) )
            jBcommit.setEnabled(true);
        else
            jBcommit.setEnabled(false);  
    
    }
    private void jTFs_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFs_KeyReleased
        ctrlMandatoryField();      
    }//GEN-LAST:event_jTFs_KeyReleased

    private void getGuiItem()
    {
        this.local_INFO_CONFIG.DR_USE_PLUGIN        = 1;                                // - int
        this.local_INFO_CONFIG.DR_PLUGIN_NAME       = jT_pluginName.getText().trim();  // - max length 256

        this.local_INFO_CONFIG.DR_FLAG_USE_PATH = 0;
        this.local_INFO_CONFIG.DR_SPECIFY_PL_PATH = "";
        this.local_INFO_CONFIG.DR_REC_LEN = new Integer(jTF_dataLength.getText().trim()).intValue();
        if( jrb_specify.isSelected() )
        {
            this.local_INFO_CONFIG.DR_FLAG_USE_PATH = 1;
            this.local_INFO_CONFIG.DR_SPECIFY_PL_PATH = jTF_specifyPath.getText().trim(); // - max length 1536
        }

        this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE= jTF_ConName.getText().trim();     // - max length 256
        this.local_INFO_CONFIG.GLB_DEFAULT_PATH_PL   = JTF_default_path.getText().trim();// - max length 1536
        this.local_INFO_CONFIG.AUTHOR                = jTF_author.getText().trim();      // - max length 160
        this.local_INFO_CONFIG.BOUNDARY              = byteboundary[jCBox_ByteBoundary.getSelectedIndex()]; //int
        this.local_INFO_CONFIG.ID_INDICE             = get_jCB_DataGroupIndex(""+jCB_DataGroup.getSelectedItem());
               
        //System.out.println("getGuiItem -> CONF_DEFAULT "+this.local_INFO_CONFIG.CONF_DEFAULT);
    }
    
    private void executeCommit()
    {           
        this.setCursor(Cur_wait);

        ////Modality  0 = new; 1 = modify; 2 = delete; 
        if(modality == 0 ) // 0 = new;
        {
            getGuiItem();
            int Answer_Ins = this.local_INFO_CONFIG.insertInfoConfig();
            
            if(Answer_Ins == 1)
            {
                ADAMS_GlobalParam.optionPanel(this,"New Configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' has been correctly created.","INFO",3);
                this.localParent.getNameAnalisi();
                closeFrame();
                this.localParent.setCenteredFrame();
            }
            else if (Answer_Ins == -1)
                ADAMS_GlobalParam.optionPanel(this,"WARNING: Configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' already exists in database.","Warning",4);
            else
                ADAMS_GlobalParam.optionPanel(this,"Cannot insert configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'.","ERRORE",1);
        }
        else if(modality == 1)
        {    
            ADAMS_JD_Message op1 = new ADAMS_JD_Message(this,true,STR_CommitNext+"Please confirm changing request or data will be restored." ,"Warning",5,300,180);
            int YesNo = op1.getAnswer();
            if(YesNo == 0)            
            {
                setGUIModality(this.modality);                
                this.setCursor(Cur_default);  
                return;
            }
            
            getGuiItem();            
            int Answer_Update = this.local_INFO_CONFIG.updateInfoConfig();
            
            if(Answer_Update >= 0)
            {
                ADAMS_GlobalParam.optionPanel(this,"Configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' has been correctly changed.","INFO",3);
                this.localParent.getNameAnalisi();
                
                jBcommit.setEnabled(false);
                this.local_INFO_CONFIG.InfoConfigName(this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
                setGUIModality(this.modality);
            }
            else
                ADAMS_GlobalParam.optionPanel(this,"Cannot change configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'.","ERRORE",1);
        }
        else if(modality == 2) //2 = delete;
        {  
            String sel_fileName = "select FILE_NAME_CONF from tab_info_config where TIPO_DI_CONFIGURAZIONE = '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' "; 
            String strLastFileName = ADAMS_GlobalParam.connect_Oracle.Query_s(sel_fileName);
            //System.out.println("strLastFileName="+strLastFileName);
            
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"Attention: this operation will destroy configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"'. Are you shure to want this ?" ,"Warning",6,340,200);
            int Yes_No = op.getAnswer();
            
            if(Yes_No == 1)
            {   
                ADAMS_JD_Message op1 = new ADAMS_JD_Message(this,true,"WARNING: This operation will DEFINITELY remove configuration and all related data from database. Continue ?" ,"Warning",5,300,180);
                int YesNo = op1.getAnswer();
                if(YesNo == 1)
                {
                    int Answer_Del = this.local_INFO_CONFIG.deleteInfoConfig();                    

                    if(Answer_Del >= 0)
                    {
                        ADAMS_GlobalParam.optionPanel(this,"Configuration '"+this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' has been deleted.","INFO",3);            
                        this.localParent.getNameAnalisi();
                        closeFrame();
                        this.localParent.setCenteredFrame();

                        if(strLastFileName!="")
                        {
                            sel_fileName = "select ID_FUNCTION from tab_function_lib where ID_CLASS="+ADAMS_GlobalParam.ID_MOD_MDM+" AND FUNCTION_NAME='"+strLastFileName+"' "; 
                            int idFunction = ADAMS_GlobalParam.connect_Oracle.Query_int(sel_fileName);

                            System.out.println("DELETE CONFIG ON DB ("+strLastFileName+","+idFunction+")");

                            S_FUNCTION newConfig = new S_FUNCTION();
                            newConfig.idClass         = ADAMS_GlobalParam.ID_MOD_MDM;
                            newConfig.idFunction      = idFunction;
                            newConfig.nameFunction    = ADAMS_GlobalParam.set_String_toChar(strLastFileName,asp_usermanagement_server.eMAX_NAME_FUNCTION);
                            newConfig.descFunction    = ADAMS_GlobalParam.set_String_toChar(strLastFileName,asp_usermanagement_server.eMAX_DESC_FUNCTION);
                            newConfig.version         = ADAMS_GlobalParam.set_String_toChar("1.0",asp_usermanagement_server.eMAX_VERSION);
                            newConfig.releasedData    = ADAMS_GlobalParam.set_String_toChar("",asp_usermanagement_server.eMAX_RELEASED_DATA);
                            newConfig.author          = ADAMS_GlobalParam.set_String_toChar("ADAMSConf",asp_usermanagement_server.eMAX_AUTHOR);
                            newConfig.vendor          = ADAMS_GlobalParam.set_String_toChar("ADAMSConf",asp_usermanagement_server.eMAX_VENDOR);

                            boolean bRet=ADAMS_GlobalParam.STSConn.imd_Function(asp_usermanagement_server.eOP_DELETE,newConfig);

                            if(bRet==false)
                            {
                                ADAMS_GlobalParam.optionPanel(this,"DELETE Configuration on DB Failed.","ERROR",1);
                            }else
                            {
                                ADAMS_GlobalParam.optionPanel(this,"Configuration '"+strLastFileName+"' has been deleted on DB","INFO",3);
                            }
                        }
                    }
                    else
                        ADAMS_GlobalParam.optionPanel(this,"Operation failed.","ERROR",1);
                }
            }
        }
        this.setCursor(Cur_default);
    }
    private void jBcommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcommitActionPerformed
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == true)
        {            
            executeCommit();           
        }
        else
        {
            this.localParent.getNameAnalisi();
                
            jBcommit.setEnabled(false);
            this.local_INFO_CONFIG.InfoConfigName(this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
            setGUIModality(this.modality);
        }
        
    }//GEN-LAST:event_jBcommitActionPerformed

    private void jB_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_backActionPerformed
       //System.out.println("< BACK");
       if(getJPanelWizard().equals(JP_InputData_1) == true)
       {
            setJp_info_0();
            jL_icon.setVisible(true);
            jBcommit.setVisible(true);
       }
       else if(getJPanelWizard().equals(JTab_TE_EXC_REL) == true)
       {
            int currentTab = JTab_TE_EXC_REL.getShowTab();
           
            if(currentTab == 0) // visualizzato Traffic Element
            {
                setCenteredFrame(InputData_WIDTH,InputData_HEIGHT);
                JP_InputData_1.SetTable();
                setJPanelWizard(JP_InputData_1);
                
                setStatusIcon(jB_back,"b_back.jpg","b_back_over.jpg","b_back_press.jpg");
                setStatusIcon(jB_next,"b_next.jpg","b_next_over.jpg","b_next_press.jpg");
            }
            else   
                JTab_TE_EXC_REL.setShowTab(currentTab-1);
            
            /*else if(JTab_TE_EXC_REL.getShowTab() == 1)
            {
                JTab_TE_EXC_REL.setShowTab(0);
            }
            else if(JTab_TE_EXC_REL.getShowTab() == 2)
            {
                JTab_TE_EXC_REL.setShowTab(1);
            }
            else if(JTab_TE_EXC_REL.getShowTab() == 3)
            {
                JTab_TE_EXC_REL.setShowTab(2);
            }
            else if(JTab_TE_EXC_REL.getShowTab() == 4)
            {
                JTab_TE_EXC_REL.setShowTab(3);
            }*/
       }
       
    }//GEN-LAST:event_jB_backActionPerformed

    private void jrb_enableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_enableItemStateChanged
        setAbilityAllItem(jrb_enable.isSelected());
    }//GEN-LAST:event_jrb_enableItemStateChanged

    private void setAbilityAllItem(boolean f)
    {
        jL_desc1.setEnabled(f);
        jTF_ConName.setEnabled(f);
        //jCBox_defaultConf.setEnabled(f);
        jL_desc3.setEnabled(f);
        jT_pluginName.setEnabled(f);
        jB_editPL.setEnabled(f);
        jL_length.setEnabled(f);
        jTF_dataLength.setEnabled(f);
        jL_desc2.setEnabled(f);
        JTF_default_path.setEnabled(f);
        jCB_DataGroup.setEnabled(f);
        jrb_defaultPath.setEnabled(f);
        jrb_specify.setEnabled(f);
        jTF_specifyPath.setEnabled(f);
        jCBox_ByteBoundary.setEnabled(f);
        jL_ByteBoundary.setEnabled(f);
        jL_desc4.setEnabled(f);
        jTF_author.setEnabled(f);
        
        
    }
    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        closeFrame();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_nextActionPerformed
       //System.out.println("NEXT >");
       
       if(getJPanelWizard().equals(jP_info_0) == true)
       {
            if(jBcommit.isEnabled())
            {
                STR_CommitNext = "Attention some data has been changed. ";
                if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == true)
                {
                    executeCommit(); 
                    STR_CommitNext = "";
                    return;
                }
                else
                {
                    this.localParent.getNameAnalisi();
                
                    jBcommit.setEnabled(false);
                    this.local_INFO_CONFIG.InfoConfigName(this.local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);
                    setGUIModality(this.modality);
                }
            }
           
            setJpInputData_1();
                       
            jL_icon.setVisible(false);
            jBcommit.setVisible(false);
       }
       else if( getJPanelWizard().equals(JP_InputData_1) == true )
       {
            setJpDataElement_02();
       }
       else if(getJPanelWizard().equals(JTab_TE_EXC_REL) == true )
       {
            setJp_TE_EXC_REL();
       }
       
    }//GEN-LAST:event_jB_nextActionPerformed

    private void jrb_defaultPathItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_defaultPathItemStateChanged
        boolean f = jrb_defaultPath.isSelected();
        
        jTF_specifyPath.setEditable(!f);
        if(!f)
            jTF_specifyPath.setText("");
        
    }//GEN-LAST:event_jrb_defaultPathItemStateChanged

    public void setStatusIcon(javax.swing.JButton button,String icon, String iconOver,String iconPress )
    { 
        //System.out.println(icon);
        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+icon)));
        //System.out.println(iconOver);
        button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+iconOver)));
        //System.out.println(iconPress);
        button.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+iconPress)));
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
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame()
    {
        dispose();
    }
    
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Controlla, in caso si fosse verificata un anomalia dal lato del MAster Server, se il thread Ã¨ ancora attivo:
     * in caso affermativo restituisce <b>true</b>,
     * in caso negativo restituisce <b>false</b>.
     * </font></font></p></pre>
     */
    public boolean isAlive()
    {
        try
        {
            return th.isAlive();
        }
        catch(Exception e)
        {
           // System.out.println("TH null :: isAlive");
            return false;
        }
    }
    
    public void run()
    {
        //System.out.println("RUN thread");
        
        jB_back.setEnabled(false);
        jB_next.setEnabled(false);
        jB_cancel.setEnabled(false);
        
        if(OperationTH == 0) // setJp_info_0
        {            
            setCenteredFrame(jP_info_0_WIDTH,jP_info_0_HEIGHT);
            setJPanelWizard(jP_info_0);
            jB_back.setVisible(false);
        }        
        else if(OperationTH == 1) // setJpInputData_1
        {
            setCenteredFrame(InputData_WIDTH,InputData_HEIGHT);
            setJPanelWizard(JP_InputData_1);
            
            JP_InputData_1.SetTable();
            jB_back.setVisible(true);
            
            if(this.local_INFO_CONFIG.CONF_DEFAULT == 'Y')
            {
                jB_next.setVisible(false);
            }
            
        }
        else if(OperationTH == 2) //setJpDataElement_02
        {    
            setCenteredFrame(JTab_TE_EXC_REL_WIDTH,JTab_TE_EXC_REL_HEIGHT);
            setJPanelWizard(JTab_TE_EXC_REL);
            
            JTab_TE_EXC_REL.setTableConfig();
            jB_back.setVisible(true);
                        
        }
        else if(OperationTH == 3) //setJp_TE_EXC_REL
        {          
            int countTab = JTab_TE_EXC_REL.getTableCount();
            int currentTab = JTab_TE_EXC_REL.getShowTab();
            
            if( (countTab-1) != currentTab )
                JTab_TE_EXC_REL.setShowTab(currentTab+1);
            
            /*if(JTab_TE_EXC_REL.getShowTab() == 0)
            {
                JTab_TE_EXC_REL.setShowTab(1);
            }
            else if(JTab_TE_EXC_REL.getShowTab() == 1)
            {
                JTab_TE_EXC_REL.setShowTab(2);
            }
            else if(JTab_TE_EXC_REL.getShowTab() == 2)
            {
                JTab_TE_EXC_REL.setShowTab(3);
            }
            else if(JTab_TE_EXC_REL.getShowTab() == 3)
            {
                JTab_TE_EXC_REL.setShowTab(4);
            }*/
        }
        
        OperationTH = -1;
        jB_back.setEnabled(true);
        jB_next.setEnabled(true);        
        jB_cancel.setEnabled(true);
    }    
    private void setJPanelWizard(javax.swing.JPanel jpanel)
    {
        this.getContentPane().remove(currentJPanel);
        this.validate();
        this.repaint();
        
        getContentPane().add(jpanel, gridBagConstraint_W);

        this.validate();
        this.repaint();
        currentJPanel = jpanel;
    }
    private javax.swing.JPanel getJPanelWizard()
    {
        return currentJPanel;
    }
    
    private void setJp_info_0()
    {
        OperationTH = 0;
        th = null;
        th = new Thread(this,"setJp_info_0");
        th.start();
    }
    
    private void setJpInputData_1()
    {
        OperationTH = 1;
        th = null;
        th = new Thread(this,"setJpInputData_1");
        th.start();
    }
    
    private void setJpDataElement_02()
    {
        OperationTH = 2;
        th = null;
        th = new Thread(this,"setJpDataElement_02");
        th.start();
    }
    
    private void setJp_TE_EXC_REL()
    {
        OperationTH = 3;
        th = null;
        th = new Thread(this,"setJp_TE_EXC_REL");
        th.start();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup b_group_specifyPath;
    private javax.swing.ButtonGroup b_group_ability;
    private javax.swing.JLabel jL_icon;
    private javax.swing.JPanel jP_info_0;
    private javax.swing.JPanel jP_jrb_ability;
    private javax.swing.JPanel jP_north;
    private javax.swing.JRadioButton jrb_disable;
    private javax.swing.JRadioButton jrb_enable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_generalnfo;
    private javax.swing.JLabel jL_desc1;
    private javax.swing.JTextField jTF_ConName;
    private javax.swing.JLabel jL_desc2;
    private javax.swing.JTextField JTF_default_path;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox jCB_DataGroup;
    private javax.swing.JLabel jL_IndexFileName;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jP_pluginInfo;
    private javax.swing.JLabel jL_desc3;
    private javax.swing.JTextField jTF_specifyPath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jrb_defaultPath;
    private javax.swing.JRadioButton jrb_specify;
    private javax.swing.JLabel jL_length;
    private javax.swing.JTextField jTF_dataLength;
    private javax.swing.JLabel jL_ByteBoundary;
    private javax.swing.JComboBox jCBox_ByteBoundary;
    private javax.swing.JButton jB_editPL;
    private javax.swing.JTextField jT_pluginName;
    private javax.swing.JPanel jP_editingInfo;
    private javax.swing.JLabel jL_desc4;
    private javax.swing.JTextField jTF_author;
    private javax.swing.JLabel jL_desc5;
    private javax.swing.JTextField jTF_lastTime;
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jBcommit;
    public javax.swing.JButton jB_back;
    public javax.swing.JButton jB_next;
    public javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables

    private Thread th        = null;
    //private boolean TH_EXIT = false;
    private int OperationTH   = -1;
    
    private String STR_CommitNext = "";
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private ADAMS_JF_Wizard localParent;
    
    private javax.swing.JPanel currentJPanel;
    private java.awt.GridBagConstraints gridBagConstraint_W;
    
    private int jP_info_0_WIDTH     = 600;
    private int jP_info_0_HEIGHT    = 580;
    
    private ADAMS_JP_InputData       JP_InputData_1  = null;
    private int InputData_WIDTH     = 810;
    private int InputData_HEIGHT    = 680;
    
    private ADAMS_JTab_TE_EXC_REL    JTab_TE_EXC_REL = null;
    private int JTab_TE_EXC_REL_WIDTH   = 1024;
    private int JTab_TE_EXC_REL_HEIGHT  = 800;
    
    //private ADAMS_JP_Relations       JP_Relations    = null;
    //private int JP_Relations_WIDTH   = 940;
    //private int JP_Relations_HEIGHT  = 800;
    
    private int modality = -1; //Modality  0 = new; 1 = modify; 2 = delete;
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    
    private int[] byteboundary = {1,2,4,8};
    
    private String DataGroup_NotAssigned = "Not Assigned";
    private java.util.Vector V_DataGroup_desc   = null;
    private java.util.Vector V_DataGroup_ID     = null;
    private java.util.Vector V_DataGroup_NameFile   = null;
}
    
