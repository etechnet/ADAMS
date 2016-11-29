
import javax.swing.JOptionPane;
import java.util.Vector;
import java.awt.Color;
import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;
import net.etech.MDM.mdm_server_serverFactoryPackage.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_JP_MonitorManager </b> </p>
 * 
 * La classe estende un javax.swing.JPanel specifico di un'istanza di un javax.swing.JTabbedPane, contenitore grafico
 * per gli oggetti utilizzati per la configurazione del modulo applicativo MONITOR per l'associazione dei profili di accesso e configurazione dei privilegi.
 * L'interfaccia mette a disposizione due form principali:
 * <pre> * 
 * 1 - Wizard guidato di configurazione che permette la selezione di plugins, funzioni e parametri specifici dell'applicatico, nonchÃÂ¨ settaggio dei Template dei grafici.
 * 2 - Form di configurazione dei profili specifici di accesso per l'applicativo.
 *</pre>
 *
 *@see MON_JP_EnablePlugin
 *@see MON_JP_ConfPLugin
 *@see MON_JP_FunctionGUI
 *@see MON_JP_ConfSubFunction
 *@see MON_JP_FunctionGUI
 *@see MON_JP_ChartGUI
 *
 */ 
public class conf_JP_MonitorManager extends javax.swing.JPanel implements Runnable{

    /** 
     * Costruttore della classe con i seguenti parametri in ingresso: javax.swing.JFrame, boolean, boolean.
     * Istanzia ed inizializza gli oggetti necessari all'interfaccia grafica e la loro funzionalitÃÂ .
     *
     *@param parent javax.swing.JFrame padre della classe chiamante.
     *@param enableFunction variabile booleana, se true l'interfaccia permetterÃÂ  l'interattivitÃÂ  degli oggetti dedicati alla configurazione del modulo applicativo.
     *
     */
    public conf_JP_MonitorManager(javax.swing.JFrame pframe,boolean enableFunction) {
        
        this.frameParent = pframe;
        initComponents();
                
        jB_back.setCursor(Cur_hand);
        jB_next.setCursor(Cur_hand);
        jB_reloadWizard.setCursor(Cur_hand);
        jB_back.setEnabled(false);
        jB_next.setEnabled(false);
        jB_reloadWizard.setEnabled(false);
               
     
        if(enableFunction)
        {
            //System.out.println("---------- istanzio --> MON_JP_EnablePlugin");
            initWizard();
        }
        else
        {
            jTabbed_MonitorManager.setEnabledAt(0,false);
            jTabbed_MonitorManager.setSelectedIndex(1);
        }
        
        
        //icon_done = new javax.swing.ImageIcon(getClass().getResource("/images_conf/op_done.png"));
        //icon_failed = new javax.swing.ImageIcon(getClass().getResource("/images_conf/op_failed.png"));
        
        eventJButton = new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActionPerformed(evt);
            }
        };
        jB_next.addActionListener(eventJButton);
        jB_back.addActionListener(eventJButton);
        jB_reloadWizard.addActionListener(eventJButton);
        
        ////////  Monitor Profiles
        
        jTF_chart.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.NUMERIC,conf_GlobalParam.MAX_CHART_USER));
        
        IcPool = new conf_IconPool("/images_conf/");
        
        jList_Profile = new conf_JListIcon(IcPool);
        jList_Profile.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_profile = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_ProfileMousePressed(evt);
            }
        });
        jScrollP_Profile.setViewportView(jList_Profile);
        
        jList_Function = new conf_JListIcon(IcPool);
        jList_Function.setSelectionBackColor(java.awt.Color.white); //lista cone selezione non visibile
        jList_Function.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_Function.setViewportView(jList_Function);
        
        //Font
        jTF_nameProfile.setFont(conf_GlobalParam.Font1_plain);
        jTA_descProfile.setFont(conf_GlobalParam.Font1_plain);
        jList_Profile.set_Font(conf_GlobalParam.Font1_bold);
        jList_Function.set_Font(conf_GlobalParam.Font1_bold);
        jCB_RealTimeInteract.setFont(conf_GlobalParam.Font1_bold);
        jTF_chart.setFont(conf_GlobalParam.Font1_bold);
        jLabel_maxChart.setFont(conf_GlobalParam.Font1_bold);
        jB_reload.setFont(conf_GlobalParam.Font1_bold);
        jB_commit.setFont(conf_GlobalParam.Font1_bold);
        jB_TemplateEditor.setFont(conf_GlobalParam.Font1_bold);
        
        //Cursor
        jB_reload.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jList_Profile.setCursor(Cur_hand);
        jList_Function.setCursor(Cur_hand);
        jCB_RealTimeInteract.setCursor(Cur_hand);
        jB_TemplateEditor.setCursor(Cur_hand);
        
        read_Profile_MON();
 
    }

   /*private void closeWizard()
   {
        if( (JP_EnablePlugin == objShow) ) 
        {
            JP_EnablePlugin.CloseDescripPlugIn();
            if (JP_EnablePlugin.getModified() == true)// nel caso siano stati abilitati o disabilitati i plugin.
            {
                JOptionPane jOptionPane1 = new JOptionPane(); 
                int YES_NO = jOptionPane1.showConfirmDialog(null,"Do you want to save last modification?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                // YES_NO = 0 ===> YES -- YES_NO = 1 ===> NO
                if(YES_NO == 0) // YES
                {
                    boolean stat = conf_GlobalParam.CORBAConn.setValidatePlugIn(JP_EnablePlugin.getAllPlugiIn());
                }
            }
        }      
        
        Object objShow          = null;    
        
        // Configuration Plugin
        JP_EnablePlugin         = null;
        JP_ConfPLugin           = null;
        JP_ConfSubFunction      = null;
        JP_FunctionGUI          = null;
        JP_ChartGUI             = null;
        JP_endPL                = null;
        
        //conf_GlobalParam.CORBAConn.shutDown_Conf();
        //System.out.println("shutDown_Conf");
        //this.frameParent.dispose(); 
        
    }*/
    
    
    private void reloadWizard()
    {
        Object objShow          = null;  // pannello step corrente Wizard        
        
        //User CONF PLUG
        JP_EnablePlugin         = null;
        JP_ConfPLugin           = null;
        JP_ConfSubFunction      = null;
        JP_FunctionGUI          = null;
        JP_ChartGUI             = null;
        JP_endPL                = null;

        jB_back.setEnabled(false);
        jB_next.setEnabled(false);
        jB_reloadWizard.setEnabled(false);
        
        //reload
        //System.out.println("istanzio reload Wizard --> MON_JP_EnablePlugin");
        initWizard();
        
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_Title = new javax.swing.JLabel();
        jTabbed_MonitorManager = new javax.swing.JTabbedPane();
        jP_MON_funGraph = new javax.swing.JPanel();
        jP_Tot = new javax.swing.JPanel();
        jP_south = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jB_back = new javax.swing.JButton();
        jB_next = new javax.swing.JButton();
        jB_reloadWizard = new javax.swing.JButton();
        jP_templateStart = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jP_button = new javax.swing.JPanel();
        jB_TemplateEditor = new javax.swing.JButton();
        jP_MON_AllowedProfile = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollP_Profile = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTF_nameProfile = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTA_descProfile = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScroll_Function = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jB_reload = new javax.swing.JButton();
        jPanel_sc = new javax.swing.JPanel();
        jCB_RealTimeInteract = new javax.swing.JCheckBox();
        jTF_chart = new javax.swing.JTextField();
        jLabel_maxChart = new javax.swing.JLabel();
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setBackground(new java.awt.Color(230, 230, 230));
        jL_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/monitor.png")));
        jL_Title.setText("MONITOR MANAGEMENT");
        jL_Title.setMaximumSize(new java.awt.Dimension(189, 34));
        jL_Title.setMinimumSize(new java.awt.Dimension(189, 34));
        jL_Title.setPreferredSize(new java.awt.Dimension(74, 34));
        add(jL_Title, java.awt.BorderLayout.NORTH);
        
        jTabbed_MonitorManager.setBackground(new java.awt.Color(230, 230, 230));
        jTabbed_MonitorManager.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jP_MON_funGraph.setLayout(new java.awt.BorderLayout());
        
        jP_Tot.setLayout(new java.awt.BorderLayout());
        
        jP_Tot.setBackground(new java.awt.Color(230, 230, 230));
        jP_Tot.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Function Configurations ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jP_MON_funGraph.add(jP_Tot, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setMinimumSize(new java.awt.Dimension(400, 100));
        jP_south.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel5.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jPanel5.setMinimumSize(new java.awt.Dimension(234, 30));
        jPanel5.setPreferredSize(new java.awt.Dimension(240, 30));
        jB_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/back.gif")));
        jB_back.setBorderPainted(false);
        jB_back.setContentAreaFilled(false);
        jB_back.setFocusPainted(false);
        jB_back.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_back.setPreferredSize(new java.awt.Dimension(70, 20));
        jB_back.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/back_press.gif")));
        jB_back.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/back_over.gif")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        jPanel5.add(jB_back, gridBagConstraints1);
        
        jB_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/next.gif")));
        jB_next.setBorderPainted(false);
        jB_next.setContentAreaFilled(false);
        jB_next.setFocusPainted(false);
        jB_next.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_next.setPreferredSize(new java.awt.Dimension(70, 20));
        jB_next.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/next_press.gif")));
        jB_next.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/next_over.gif")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        jPanel5.add(jB_next, gridBagConstraints1);
        
        jB_reloadWizard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/reload_w.gif")));
        jB_reloadWizard.setBorderPainted(false);
        jB_reloadWizard.setContentAreaFilled(false);
        jB_reloadWizard.setFocusPainted(false);
        jB_reloadWizard.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_reloadWizard.setMaximumSize(new java.awt.Dimension(100, 20));
        jB_reloadWizard.setMinimumSize(new java.awt.Dimension(100, 20));
        jB_reloadWizard.setPreferredSize(new java.awt.Dimension(100, 20));
        jB_reloadWizard.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/reload_w_press.gif")));
        jB_reloadWizard.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/reload_w_over.gif")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        jPanel5.add(jB_reloadWizard, gridBagConstraints1);
        
        jP_south.add(jPanel5, java.awt.BorderLayout.CENTER);
        
        jP_templateStart.setBackground(new java.awt.Color(230, 230, 204));
        jP_templateStart.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Template Configurations ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12)));
        jP_templateStart.setMinimumSize(new java.awt.Dimension(10, 70));
        jP_templateStart.setPreferredSize(new java.awt.Dimension(10, 70));
        jLabel5.setText("Configure Logical Function default template");
        jP_templateStart.add(jLabel5);
        
        jP_button.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 3, 3));
        
        jP_button.setBackground(new java.awt.Color(230, 230, 230));
        jP_button.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        jB_TemplateEditor.setBackground(java.awt.Color.blue);
        jB_TemplateEditor.setForeground(java.awt.Color.white);
        jB_TemplateEditor.setText("Template Editor");
        jB_TemplateEditor.setFocusPainted(false);
        jB_TemplateEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_TemplateEditorActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_TemplateEditor);
        
        jP_templateStart.add(jP_button);
        
        jP_south.add(jP_templateStart, java.awt.BorderLayout.SOUTH);
        
        jP_MON_funGraph.add(jP_south, java.awt.BorderLayout.SOUTH);
        
        jTabbed_MonitorManager.addTab("Function Graph", jP_MON_funGraph);
        
        jP_MON_AllowedProfile.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_MON_AllowedProfile.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 10));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_job.png")));
        jLabel2.setText("Profiles Monitor");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 2, 2);
        gridBagConstraints3.weightx = 1.0;
        jPanel4.add(jLabel2, gridBagConstraints3);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jPanel4.add(jScrollP_Profile, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_MON_AllowedProfile.add(jPanel4, gridBagConstraints2);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 150));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/profile_name.png")));
        jLabel1.setText("Profile Name");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints4);
        
        jTF_nameProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTF_nameProfile.setDisabledTextColor(java.awt.Color.black);
        jTF_nameProfile.setEnabled(false);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.weightx = 1.0;
        jPanel1.add(jTF_nameProfile, gridBagConstraints4);
        
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/profile_desc.png")));
        jLabel3.setText("Profile Decription");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints4);
        
        jScrollPane1.setMaximumSize(new java.awt.Dimension(100, 60));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 60));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 60));
        jTA_descProfile.setLineWrap(true);
        jTA_descProfile.setRows(2);
        jTA_descProfile.setWrapStyleWord(true);
        jTA_descProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTA_descProfile.setDisabledTextColor(java.awt.Color.black);
        jTA_descProfile.setEnabled(false);
        jScrollPane1.setViewportView(jTA_descProfile);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 3;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jScrollPane1, gridBagConstraints4);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        jP_MON_AllowedProfile.add(jPanel1, gridBagConstraints2);
        
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/functions_en.png")));
        jLabel4.setText("Functions Profile");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        jP_MON_AllowedProfile.add(jLabel4, gridBagConstraints2);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_MON_AllowedProfile.add(jScroll_Function, gridBagConstraints2);
        
        jPanel3.setLayout(new java.awt.BorderLayout());
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit.jpg")));
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_commit.setPreferredSize(new java.awt.Dimension(80, 25));
        jB_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit_press.jpg")));
        jB_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit_over.jpg")));
        jB_commit.setEnabled(false);
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });
        
        jPanel3.add(jB_commit, java.awt.BorderLayout.EAST);
        
        jB_reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/result.png")));
        jB_reload.setText("Reload All");
        jB_reload.setBorderPainted(false);
        jB_reload.setContentAreaFilled(false);
        jB_reload.setFocusPainted(false);
        jB_reload.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jB_reload.setMinimumSize(new java.awt.Dimension(140, 30));
        jB_reload.setPreferredSize(new java.awt.Dimension(140, 30));
        jB_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_reloadActionPerformed(evt);
            }
        });
        
        jPanel3.add(jB_reload, java.awt.BorderLayout.WEST);
        
        jPanel_sc.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jPanel_sc.setBackground(new java.awt.Color(230, 230, 230));
        jPanel_sc.setMinimumSize(new java.awt.Dimension(320, 50));
        jPanel_sc.setPreferredSize(new java.awt.Dimension(320, 50));
        jCB_RealTimeInteract.setBackground(new java.awt.Color(230, 230, 230));
        jCB_RealTimeInteract.setText("Interactive Real Time");
        jCB_RealTimeInteract.setContentAreaFilled(false);
        jCB_RealTimeInteract.setFocusPainted(false);
        jCB_RealTimeInteract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off.gif")));
        jCB_RealTimeInteract.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
        jCB_RealTimeInteract.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off_over.gif")));
        jCB_RealTimeInteract.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
        jCB_RealTimeInteract.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on.gif")));
        jCB_RealTimeInteract.setEnabled(false);
        jCB_RealTimeInteract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCB_RealTimeInteractMouseReleased(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 15);
        jPanel_sc.add(jCB_RealTimeInteract, gridBagConstraints5);
        
        jTF_chart.setMinimumSize(new java.awt.Dimension(60, 20));
        jTF_chart.setPreferredSize(new java.awt.Dimension(60, 20));
        jTF_chart.setEnabled(false);
        jTF_chart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_chartKeyReleased(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.insets = new java.awt.Insets(5, 15, 5, 5);
        jPanel_sc.add(jTF_chart, gridBagConstraints5);
        
        jLabel_maxChart.setText("Max Chart");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel_sc.add(jLabel_maxChart, gridBagConstraints5);
        
        jPanel3.add(jPanel_sc, java.awt.BorderLayout.CENTER);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_MON_AllowedProfile.add(jPanel3, gridBagConstraints2);
        
        jTabbed_MonitorManager.addTab("Grant Profiles", jP_MON_AllowedProfile);
        
        add(jTabbed_MonitorManager, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    private void jB_TemplateEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_TemplateEditorActionPerformed
        if(frameMonitor == null)
            frameMonitor = new MON_JF_SelSession(false);
        else if(frameMonitor.isShowing() == false)
        {
            frameMonitor = null;
            frameMonitor = new MON_JF_SelSession(false);
        }
        else
            frameMonitor.toFront();
            
        
    }//GEN-LAST:event_jB_TemplateEditorActionPerformed

    private void jTF_chartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_chartKeyReleased
        jB_commit.setEnabled(true);
    }//GEN-LAST:event_jTF_chartKeyReleased

    private void jCB_RealTimeInteractMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCB_RealTimeInteractMouseReleased
        jB_commit.setEnabled(true);     
    }//GEN-LAST:event_jCB_RealTimeInteractMouseReleased

    private void jB_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reloadActionPerformed
        reloadAll();
    }//GEN-LAST:event_jB_reloadActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        set_ProfileGrant();
    }//GEN-LAST:event_jB_commitActionPerformed

    private void jButton_ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        
        Object jb_source = evt.getSource();
        
        if(jb_source == jB_back)                // @@@@@@@@ BACK @@@@@@@@ //
        {
            run_pressBack();     
        } 
        else if(jb_source == jB_next)           // @@@@@@@@ NEXT @@@@@@@@ //           
        {
            run_pressNext();
        }
        else if(jb_source == jB_reloadWizard)
        {    
            this.setCursor(Cur_wait);
            if( (JP_EnablePlugin == objShow) ) 
            {
                if (JP_EnablePlugin.getModified() == true)// nel caso siano stati abilitati o disabilitati i plugin.
                {
                    JOptionPane jOptionPane1 = new JOptionPane(); 
                    int YES_NO = jOptionPane1.showConfirmDialog(null,"Do you want to save last modification?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    // YES_NO = 0 ===> YES -- YES_NO = 1 ===> NO
                    if(YES_NO == 0) // YES
                    {
                        boolean stat = conf_GlobalParam.CORBAConn.setValidatePlugIn(JP_EnablePlugin.getAllPlugiIn());
                    }
                }
            }
            this.setCursor(Cur_default);
            reloadWizard();
        }
       
    }
    
    private void pressBack()
    {
        
        if( objShow.equals(JP_ConfPLugin) )
        {
            
            JP_ConfPLugin.CloseDescripPlugIn();
            JP_EnablePlugin.readPlugIn();
            
            
            jP_south.add(jP_templateStart, java.awt.BorderLayout.SOUTH);
            jP_templateStart.repaint();
            jP_south.repaint();
            
            reloadJP(JP_EnablePlugin);
            
            jB_back.setEnabled(false);
            return;
        }
        if(objShow.equals(JP_ConfSubFunction)) 
        {
                        
            //System.out.println("****** Assegno  conf_GlobalParam.APPOSubFunctionList******");
            FunctionDescription functionDesc = JP_ConfPLugin.getPluginDesc();
            // ristabilisco le sub function tolte nel MARGE
            functionDesc.SubFunctionList = conf_GlobalParam.APPOSubFunctionList;

            JP_ConfPLugin.readPluginDesc(functionDesc);
            reloadJP(JP_ConfPLugin);
            
            return;
        }
        
        if(objShow.equals(JP_FunctionGUI))
        {
            
            JP_ConfSubFunction.BACK_MargeUSE();
            reloadJP(JP_ConfSubFunction);
            
            return;
        }
        
        if(objShow.equals(JP_ChartGUI))
        {
           
            reloadJP(JP_FunctionGUI);
            return;
        }
             
    }
 
    private void pressNext()
    {        
        if (objShow == JP_EnablePlugin)
        {
            JP_EnablePlugin.CloseDescripPlugIn();
            
            // nel caso siano stati abilitati o disabilitati i plugin.
            if (JP_EnablePlugin.getModified() == true)
            {
                JOptionPane jOptionPane1 = new JOptionPane(); 
                int YES_NO = jOptionPane1.showConfirmDialog(null,"Do you want to save last modification?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                // YES_NO = 0 ===> YES -- YES_NO = 1 ===> NO
                if(YES_NO == 0) // YES
                {
                    boolean stat = conf_GlobalParam.CORBAConn.setValidatePlugIn(JP_EnablePlugin.getAllPlugiIn());
                }
                else
                {
                    reloadWizard();
                    return;
                }
                
            }
            if (JP_ConfPLugin == null)
                JP_ConfPLugin = new MON_JP_ConfPLugin(JP_EnablePlugin.getSelectedPlugiIn(),jB_next,this.frameParent);
            else
                JP_ConfPLugin.readPluginDesc(JP_EnablePlugin.getSelectedPlugiIn());

            
            jP_south.remove(jP_templateStart);
            jP_templateStart.repaint();
            jP_south.repaint();
            
            reloadJP(JP_ConfPLugin);
            
            jB_back.setEnabled(true);
            return;
        }
        
        if(objShow == JP_ConfPLugin)
        {    
            JP_ConfPLugin.CloseDescripPlugIn();   
          
            // chiamata Marge
            FunctionDescription functionDesc_MARGE = Ctrl_Merge_subFunction(JP_ConfPLugin.getPluginDesc());
            if(functionDesc_MARGE == null)
            {    
                return;
            }
          
            if (JP_ConfSubFunction == null)
                JP_ConfSubFunction = new MON_JP_ConfSubFunction(functionDesc_MARGE);
            else
                JP_ConfSubFunction.readSubFuncEnabled(functionDesc_MARGE);
            
            reloadJP(JP_ConfSubFunction);
            return;
        }
        if(objShow == JP_ConfSubFunction)  
        {    
            FunctionDescription functionDesc = JP_ConfSubFunction.getFunctionDesc();
            if(functionDesc == null)
            {
                return;
            }
            
            FunctionDescription functionDesc_UNMARGE =  UnMarge_subFunction(functionDesc);
            
            if (JP_FunctionGUI == null)
                JP_FunctionGUI = new MON_JP_FunctionGUI(functionDesc_UNMARGE);
            else
                JP_FunctionGUI.readFuncDefault(functionDesc_UNMARGE);
           
            reloadJP(JP_FunctionGUI);
            return;
        }
        if(objShow == JP_FunctionGUI)
        {            
            if (JP_ChartGUI == null)
                JP_ChartGUI = new MON_JP_ChartGUI(JP_FunctionGUI.getFunctionDesc()); 
            else
                JP_ChartGUI.readFuncDefault(JP_FunctionGUI.getFunctionDesc());
            
            reloadJP(JP_ChartGUI);
            return;
        }
        
        if (objShow == JP_ChartGUI)
        {
            
            FunctionDescription Fdescr = JP_ChartGUI.getFunctDesc();
            if(Fdescr == null)
                return;
            
            boolean stat = conf_GlobalParam.CORBAConn.setConfigurePlugIn(Fdescr);
            
            //javax.swing.ImageIcon icon;
            String str_operation;
            java.awt.Color color = java.awt.Color.green.darker();
            if(stat == true)
            {
                //icon = icon_done;
                str_operation = str_done;
            }
            else
            {
                //icon = icon_failed;
                str_operation = str_failed;
                color = java.awt.Color.red;
            }
            //JP_endPL = new MON_JP_ConfPL_end(icon);
            JP_endPL = new MON_JP_ConfPL_end(str_operation,color);
            
            jP_south.add(jP_templateStart, java.awt.BorderLayout.SOUTH);
            jP_south.repaint();
            
            reloadJP(JP_endPL);
            
            JP_endPL.setTextLabel("");
            jB_back.setEnabled(false);
            jB_next.setEnabled(false);
            return;
        }
    }

    
    private FunctionDescription Ctrl_Merge_subFunction(FunctionDescription functionDescAppo)
    {
        //System.out.println("INIZIO Merge");
        
// ----------------- CONTROLLO DEI DATI SEMPLICI    
            
        int subFunctionEnabled = 0;
           
        for(int i=0; i<functionDescAppo.SubFunctionList.length;i++)
        {
            if(functionDescAppo.SubFunctionList[i].enabled == 1)
            {
                subFunctionEnabled++;
                if(functionDescAppo.SubFunctionList[0].pollIntervalUnits.value() != functionDescAppo.SubFunctionList[i].pollIntervalUnits.value())
                {
                    System.out.println("********** NON COMPATIBILE ******** pollIntervalUnits differenti ");
                    return null;
                }  
                
                if(functionDescAppo.SubFunctionList[0].dataAvailability != functionDescAppo.SubFunctionList[i].dataAvailability)
                {
                    System.out.println("********** NON COMPATIBILE ******** dataAvailability differenti ");
                    return null;
                }  
            }
        }
// ----------------- carico tutti i pollIntervalList presenti nelle subfunction abilitate una sola volta.
        
        Vector V_pollValueValidate = new Vector();
        Vector V_OutputTypeValidate = new Vector();
        Vector V_all_inputItemsList = new Vector();
        
        if(subFunctionEnabled > 1) // solo nel caso siano  abilitate piÃÂ¹ subfunzione
        {    
            Vector V_allValuePolling = new Vector();
            for(int i=0; i<functionDescAppo.SubFunctionList.length;i++)
            {
                if(functionDescAppo.SubFunctionList[i].enabled == 1)
                {
                    for(int y=0; y<functionDescAppo.SubFunctionList[i].pollIntervalList.length;y++)
                    {
                        // value di polling della subFunc successiva
                        int newValue = functionDescAppo.SubFunctionList[i].pollIntervalList[y].value;                      
                        // addElement se non ÃÂ¨ giÃÂ  presente
                            addOneTime(V_allValuePolling,newValue);
                    }
                }
            }
            // controllo se tutti i ValuePolling sono presenti nelle diverse subfunction (per la compatibilitÃÂ  e merge)
            for(int i=0; i<V_allValuePolling.size(); i++)
            {
                int archiveValue = ((Integer) V_allValuePolling.elementAt(i)).intValue();
                Polling appoPolling = null;
                boolean okFind = false;
                for(int y=0; y<functionDescAppo.SubFunctionList.length;y++)
                {
                    if(functionDescAppo.SubFunctionList[y].enabled == 1)
                    {
                        for(int z=0; z<functionDescAppo.SubFunctionList[y].pollIntervalList.length;z++)
                        { 
                            if (archiveValue == functionDescAppo.SubFunctionList[y].pollIntervalList[z].value)
                            {
                                appoPolling = functionDescAppo.SubFunctionList[y].pollIntervalList[z];
                                okFind = true;
                                break;
                            } 
                            else
                            {
                                okFind = false;
                            }
                        } 
                        if(okFind == false)
                        {
                            break;
                        }
                    }//End enabled == 1 
                }
                if(okFind == true)
                {
                    V_pollValueValidate.addElement(appoPolling);
                }
            }
            if(V_pollValueValidate.size() == 0)
            {
                System.out.println("********** NON COMPATIBILE ******** causa V_pollValueValidate differenti");
                return null;
            }
            //else
            //{
            //    System.out.println("********** OK COMPATIBILE V_pollValueValidate ******** ");
            //}
            //System.out.println("poll OK minimo comune sono :");
            //for(int i=0; i<V_pollValueValidate.size(); i++) 
            //{   
            //    System.out.print(((Integer) V_pollValueValidate.elementAt(i))+" - ");  
            //}

// ----------------- carico tutti i admittedOutputType presenti nelle subfunction abilitate una sola volta.
            
            Vector V_allOutputType = new Vector();
            for(int i=0; i<functionDescAppo.SubFunctionList.length;i++)
            {
                if(functionDescAppo.SubFunctionList[i].enabled == 1)
                {
                    for(int y=0; y<functionDescAppo.SubFunctionList[i].admittedOutputType.length;y++)
                    {
                        // value di polling della subFunc successiva
                        int newOutputType = (functionDescAppo.SubFunctionList[i].admittedOutputType[y].value.value());                      
                        // addElement se non ÃÂ¨ giÃÂ  presente
                        
                        if(newOutputType != OutputType._Counter)
                            addOneTime(V_allOutputType,newOutputType);
                    }
                    
                }
            }
            // controllo se tutti i OutputType sono presenti nelle diverse subfunction (per la compatibilitÃÂ  e merge)
            for(int i=0; i<V_allOutputType.size(); i++)
            {
                int archiv_outType = ((Integer) V_allOutputType.elementAt(i)).intValue();
                Output_Type Appo_OutputType = null;
                boolean okFind1 = false;
                       
                    for(int y=0; y<functionDescAppo.SubFunctionList.length;y++)
                    {
                        if(functionDescAppo.SubFunctionList[y].enabled == 1)
                        {
                            for(int z=0; z<functionDescAppo.SubFunctionList[y].admittedOutputType.length;z++)
                            {  
                                int localadmittedOutputType = functionDescAppo.SubFunctionList[y].admittedOutputType[z].value.value();
                                
                                if(localadmittedOutputType == OutputType._Counter)
                                {
                                    okFind1 = true;
                                }
                                else if(archiv_outType == localadmittedOutputType)
                                {
                                    Appo_OutputType = functionDescAppo.SubFunctionList[y].admittedOutputType[z];
                                    okFind1 = true;
                                    break;
                                } 
                                else
                                    okFind1 = false;  
                            } 
                            if(okFind1 == false)
                            {
                                break;
                            }
                        }//End enabled == 1 
                    }
                    if(okFind1 == true)
                    {
                        V_OutputTypeValidate.addElement(Appo_OutputType);
                    }
               
            }
            
            if(V_OutputTypeValidate.size() == 0)
            {
                System.out.println("********** NON COMPATIBILE ******** causa V_OutputTypeValidate differenti");
                return null;
            }
            else
            {
                System.out.println("********** OK COMPATIBILE V_OutputTypeValidate ******** ");
            }
            //System.out.println("OutputType OK minimo comune sono :");
            /*   for(int i=0; i<V_OutputTypeValidate.size(); i++) 
               {   
                   System.out.print(conf_GlobalParam.getOutputType( ((Output_Type)V_OutputTypeValidate.elementAt(i)).value.value() ) +" - "); 
               }*/
    
//////////////////////// inputItemsList PRENDO TUTTI I COMUNI
            for(int i=0; i<functionDescAppo.SubFunctionList.length;i++)
            {
                if(functionDescAppo.SubFunctionList[i].enabled == 1)
                {
                    for(int y=0; y<functionDescAppo.SubFunctionList[i].inputItemsList.length;y++)
                    {
                        // addElement se non ÃÂ¨ giÃÂ  presente
                        addOneTime_inputItems(V_all_inputItemsList,functionDescAppo.SubFunctionList[i].inputItemsList[y]);
                    }
                }
            }
        } //END if(subFunctionEnabled > 1) 
           
            //***** Copia Appoggio delle SUBFunction
            conf_GlobalParam.APPOSubFunctionList = new SubFunction[functionDescAppo.SubFunctionList.length];
            for(int i=0; i<conf_GlobalParam.APPOSubFunctionList.length; i++) 
            {
                conf_GlobalParam.APPOSubFunctionList[i] = (SubFunction)functionDescAppo.SubFunctionList[i].clone();
                for(int x=0; x<conf_GlobalParam.APPOSubFunctionList[i].pollIntervalList.length; x++)
                {
                    conf_GlobalParam.APPOSubFunctionList[i].pollIntervalList[x] = (Polling)functionDescAppo.SubFunctionList[i].pollIntervalList[x].clone();
                }
                for(int x=0; x<conf_GlobalParam.APPOSubFunctionList[i].admittedOutputType.length; x++)
                {
                    conf_GlobalParam.APPOSubFunctionList[i].admittedOutputType[x] = (Output_Type)functionDescAppo.SubFunctionList[i].admittedOutputType[x].clone();
                }
                for(int x=0; x<conf_GlobalParam.APPOSubFunctionList[i].inputItemsList.length; x++)
                {                     
                    conf_GlobalParam.APPOSubFunctionList[i].inputItemsList[x] = (InputItem)functionDescAppo.SubFunctionList[i].inputItemsList[x].clone();
                }
            }
            //***** FINE Copia Appoggio delle SUBFunction
   
            SubFunction subFunctionMARGE[] = new SubFunction[1];
            for(int i=0; i<functionDescAppo.SubFunctionList.length; i++)
            {    
                if(functionDescAppo.SubFunctionList[i].enabled == 1)
                {
                    subFunctionMARGE[0] = (SubFunction)functionDescAppo.SubFunctionList[i].clone();
                    if(subFunctionEnabled <= 1) // nel caso sia abilitata una sola subfunzione
                    {
                        //System.out.println("nessun controllo UNA sola subFunzione abilitata");
                        //Copia della Marge sottofunzioni Effettuato -- ÃÂ¨ una sola--
                        conf_GlobalParam.MARGESubFun = subFunctionMARGE;
                        
                        functionDescAppo.SubFunctionList = subFunctionMARGE;
                        return(functionDescAppo);
                    }
                    
                    break;
                }
            } 
            //pollIntervalList
            subFunctionMARGE[0].pollIntervalList = new Polling[V_pollValueValidate.size()];
            for(int i=0; i<subFunctionMARGE[0].pollIntervalList.length; i++)
            {
                Polling AppoPolling = (Polling)(V_pollValueValidate.elementAt(i));
                subFunctionMARGE[0].pollIntervalList[i] = new Polling(AppoPolling.value,AppoPolling.enabled); 
            }
            
            //admittedOutputType
            subFunctionMARGE[0].admittedOutputType = new Output_Type[V_OutputTypeValidate.size()];
            for(int i=0; i<subFunctionMARGE[0].admittedOutputType.length; i++)
            {
                Output_Type AppoOutType = (Output_Type)(V_OutputTypeValidate.elementAt(i));
                subFunctionMARGE[0].admittedOutputType[i] = new Output_Type(AppoOutType.value,AppoOutType.enabled);                
            }
            
            //inputItemsList
            subFunctionMARGE[0].inputItemsList = new InputItem[V_all_inputItemsList.size()];
            for(int i=0; i<subFunctionMARGE[0].inputItemsList.length; i++)
            {
                InputItem AppoInputItem = (InputItem)V_all_inputItemsList.elementAt(i);
                subFunctionMARGE[0].inputItemsList[i] = (InputItem)AppoInputItem.clone();
            }
            
           //Copia della Marge sottofunzioni Effettuato -- ÃÂ¨ una sola--
            conf_GlobalParam.MARGESubFun = subFunctionMARGE;
           
            functionDescAppo.SubFunctionList = subFunctionMARGE;
            return(functionDescAppo); 
    }
    
    private void addOneTime(Vector Vappo,int newValue)
    {
         // se il valore newValue non ÃÂ¨ presente nell vettore viene inserito.
        boolean isPresent = false;
        for(int z=0; z<Vappo.size(); z++)
        {
            int archiveValue = ((Integer) Vappo.elementAt(z)).intValue();  
            if(archiveValue == newValue)
            {
                isPresent = true;
                break;
            }
        }
        if(isPresent == false)
        {
            Vappo.addElement(new Integer(newValue));
        }
    }
    
    private void addOneTime_inputItems(Vector Vappo,InputItem InputITEM )
    {
        boolean isPresent = false;
        for(int z=0; z<Vappo.size(); z++)
        {
            int uniqueId_ITEM = ((InputItem)Vappo.elementAt(z)).uniqueId;   
            if(uniqueId_ITEM == InputITEM.uniqueId)
            {
                isPresent = true;
                break;
            }
        }
        if(isPresent == false)
        {
            Vappo.addElement(InputITEM);
        }
    }
    
      
    private FunctionDescription UnMarge_subFunction (FunctionDescription functionDesc)
    {
        for(int i=0; i<functionDesc.SubFunctionList.length; i++)
        {
            SubFunction subFunMARGE = functionDesc.SubFunctionList[i]; 
            for (int x=0; x<conf_GlobalParam.APPOSubFunctionList.length; x++)
            {
                if(conf_GlobalParam.APPOSubFunctionList[x].enabled == 1)
                {
                    SubFunction subFunEnabled = conf_GlobalParam.APPOSubFunctionList[x];
                    ///// ****** pollIntervalList ******//////
                    for(int z=0; z<subFunEnabled.pollIntervalList.length; z++)
                    {
                        subFunEnabled.pollIntervalList[z].enabled = 0;
                        for (int y=0; y<subFunMARGE.pollIntervalList.length; y++)
                        {
                            if(subFunEnabled.pollIntervalList[z].value == subFunMARGE.pollIntervalList[y].value)
                            {                         
                                subFunEnabled.pollIntervalList[z].enabled = subFunMARGE.pollIntervalList[y].enabled;
                                break;
                            }
                        }
                    }
                    ///// ****** admittedOutputType ******//////
                    for(int c=0; c<subFunEnabled.admittedOutputType.length; c++)
                    {
                        subFunEnabled.admittedOutputType[c].enabled = 0;
                        if(subFunEnabled.admittedOutputType[c].value.value() == OutputType._Counter)
                        {
                           subFunEnabled.admittedOutputType[c].enabled = 1;
                        }
                        else
                        {
                            for (int a=0; a<subFunMARGE.admittedOutputType.length; a++)
                            {
                                if(subFunEnabled.admittedOutputType[c].value.value() == subFunMARGE.admittedOutputType[a].value.value())
                                {
                                    subFunEnabled.admittedOutputType[c].enabled = subFunMARGE.admittedOutputType[a].enabled; 
                                    break;
                                }
                            } 
                        }
                    }
                    ///// ****** inputItemsList ******//////
                    for(int c=0; c<subFunEnabled.inputItemsList.length; c++)
                    {
                        for (int xx=0; xx<subFunMARGE.inputItemsList.length; xx++)
                        {
                           if(subFunEnabled.inputItemsList[c].uniqueId == subFunMARGE.inputItemsList[xx].uniqueId)
                           {
                               //flag per parametro da visualizzare su titolo.
                                subFunEnabled.inputItemsList[c].flagInputItem = subFunMARGE.inputItemsList[xx].flagInputItem;
                                break;
                           }
                        }
                        subFunEnabled.inputItemsList[c].enabled = 1;
                    }
                }
            } 
        }
        
        /////////////
        
        /*for (int x=0; x<conf_GlobalParam.APPOSubFunctionList.length; x++)
        {
            if(conf_GlobalParam.APPOSubFunctionList[x].enabled == 1)
            {
                System.out.println();
                System.out.println(new String(conf_GlobalParam.APPOSubFunctionList[x].subFunctionName).trim()+" ==> ENABLE");
                System.out.println("  pollIntervalList");
                for(int y=0; y<conf_GlobalParam.APPOSubFunctionList[x].pollIntervalList.length; y++)
                {
                    System.out.print("    - Value="+conf_GlobalParam.APPOSubFunctionList[x].pollIntervalList[y].value);
                    System.out.print("      Enabled="+conf_GlobalParam.APPOSubFunctionList[x].pollIntervalList[y].enabled);
                    System.out.println();
                }
                
                System.out.println("  admittedOutputType");
                for(int y=0; y<conf_GlobalParam.APPOSubFunctionList[x].admittedOutputType.length; y++)
                {
                    System.out.print("    - Out Chart="+conf_GlobalParam.getOutputType( conf_GlobalParam.APPOSubFunctionList[x].admittedOutputType[y].value.value()));
                    System.out.print("      Enabled="+conf_GlobalParam.APPOSubFunctionList[x].admittedOutputType[y].enabled);
                    System.out.println();
                } 
            
                System.out.println("  inputItemsList");
                for(int c=0; c<conf_GlobalParam.APPOSubFunctionList[x].inputItemsList.length; c++)
                {
                    System.out.print("    - ItemName="+new String(conf_GlobalParam.APPOSubFunctionList[x].inputItemsList[c].inputItemName).trim() );
                    System.out.print("      Enabled="+conf_GlobalParam.APPOSubFunctionList[x].inputItemsList[c].enabled);
                    System.out.print("      flagInputItem="+conf_GlobalParam.APPOSubFunctionList[x].inputItemsList[c].flagInputItem);
                    System.out.println();
                }
            
            }
            else
            {
                System.out.println(new String(conf_GlobalParam.APPOSubFunctionList[x].subFunctionName).trim()+" ==> DISABLE");
            }
        }*/
        ////////////////
        
        functionDesc.SubFunctionList = conf_GlobalParam.APPOSubFunctionList;
        return(functionDesc);
    }
    
    
    private void reloadJP(javax.swing.JPanel JPSHOW)
    {
        jP_Tot.removeAll();
        jP_Tot.add(JPSHOW, java.awt.BorderLayout.CENTER);
        jP_Tot.validate();
        repaint();
        objShow = JPSHOW;
    }
   
    
    private void initWizard()
    {
        //TH_EXIT = false;
        OperationTH = 1;
        TH = null;
        TH = new Thread(this,"initWizard()");
        TH.start();
    }
    
    private void reloadAll()
    {
        //TH_EXIT = false;
        OperationTH = 2;
        TH = null;
        TH = new Thread(this,"reloadAll()");
        TH.start();
    }
    
    public void load_ProfileGrant()
    {
         //TH_EXIT = false;
        OperationTH = 3;
        TH = null;
        TH = new Thread(this,"load_ProfileGrant");
        TH.start();
    }
    
    public void set_ProfileGrant()
    {
        //TH_EXIT = false;
        OperationTH = 4;
        TH = null;
        TH = new Thread(this,"set_ProfileGrant");
        TH.start();
    }
    
    private void run_pressNext()
    {
        //TH_EXIT = false;
        OperationTH = 5;
        TH = null;
        TH = new Thread(this,"run_pressNext");
        TH.start();
    }
    
    private void run_pressBack()
    {
        //TH_EXIT = false;
        OperationTH = 6;
        TH = null;
        TH = new Thread(this,"run_pressBack");
        TH.start();
    }
    
    
    public void run()
    {    
        //System.out.println("RUN OperationTH --> "+OperationTH);
        
        try
        {
            this.setCursor(Cur_wait);
            this.frameParent.setCursor(Cur_wait);
            this.jList_Function.setCursor(Cur_wait);
            this.jList_Profile.setCursor(Cur_wait);
            this.jList_Function.setEnabled(false);
            this.jList_Profile.setEnabled(false);
            this.jB_reload.setEnabled(false);
            this.jB_reload.setCursor(Cur_wait);
            this.jCB_RealTimeInteract.setCursor(Cur_wait);
            this.jTF_chart.setCursor(Cur_wait);
            this.jTA_descProfile.setCursor(Cur_wait);
            this.jTF_nameProfile.setCursor(Cur_wait);
            
            jB_back.setCursor(Cur_wait);
            jB_next.setCursor(Cur_wait);
            jB_reloadWizard.setCursor(Cur_wait);
        }
        catch(Exception e){}
        
        switch(OperationTH)
        {
            case 1: //initWizard
            {
                jB_next.setEnabled(false);
                jB_back.setEnabled(false);

                if (JP_EnablePlugin == null)
                     JP_EnablePlugin = new MON_JP_EnablePlugin(jB_next);
                else
                     JP_EnablePlugin.readPlugIn();

                
                if(jP_templateStart.isShowing() == false)
                {
                    jP_south.add(jP_templateStart, java.awt.BorderLayout.SOUTH);
                    jP_templateStart.repaint();
                    jP_south.repaint();
                }
                    
                reloadJP(JP_EnablePlugin);

                jB_back.setEnabled(false);
                jB_reloadWizard.setEnabled(true);
            }
            break;
            
            case 2: //reloadAll
            {
                conf_GlobalParam.config_lib = conf_GlobalParam.CORBAConn.getConfigLib();
                read_Profile_MON();
            }
            break;
            
            case 3://load_ProfileGrant
            {
                String Profile_Selected = jTF_nameProfile.getText().trim();
                local_MUGrant = conf_GlobalParam.CORBAConn.getUserGrant(Profile_Selected);
            
                if (local_MUGrant != null) 
                {
                    jCB_RealTimeInteract.setEnabled(true);           
                    jLabel_maxChart.setEnabled(true);            

                    if(local_MUGrant.configRT == 1)
                        jCB_RealTimeInteract.setSelected(true);
                    else
                        jCB_RealTimeInteract.setSelected(false);
                    
                    jTF_chart.setEnabled(true);
                    jTF_chart.setText(""+local_MUGrant.numMaxChart); 
                    
                    jB_commit.setEnabled(false);
                }
                else
                {
                    jCB_RealTimeInteract.setEnabled(true);           
                    jLabel_maxChart.setEnabled(true);
                    jCB_RealTimeInteract.setSelected(false);
                    
                    jTF_chart.setEnabled(true);
                    jTF_chart.setText("20");
                    
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();                    
                    warning.showMessageDialog(frameParent,"Attention: the profile <"+Profile_Selected+"> doesn't get grant to access to the monitor.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    
                    javax.swing.JOptionPane warning1 = new javax.swing.JOptionPane();
                    warning1.showMessageDialog(frameParent,"Information: enable grant to the profile <"+Profile_Selected+"> and commit.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                    
                    jB_commit.setEnabled(true);
                }
            }
            break;
            
            case 4: //set_ProfileGrant
            {
                if(local_MUGrant == null)
                {
                    local_MUGrant = new MONITOR_USER_GRANT();
                    local_MUGrant.profile = conf_GlobalParam.set_String_toChar(jTF_nameProfile.getText().trim(),conf_GlobalParam.MAX_NAME_PROFILE);
                    local_MUGrant.FunctionEnabled   = new FUNCTION_ENABLED[1];
                    local_MUGrant.FunctionDisabled  = new FUNCTION_ENABLED[1];
                    local_MUGrant.FunctionEnabled[0] = new FUNCTION_ENABLED(-1);
                    local_MUGrant.FunctionDisabled[0] = new FUNCTION_ENABLED(-1); 
                }
                
                if(jCB_RealTimeInteract.isSelected() == true)
                    local_MUGrant.configRT = 1;
                else
                    local_MUGrant.configRT = 0;

                try
                {
                    local_MUGrant.numMaxChart = (Integer.valueOf((jTF_chart.getText()).trim()).intValue());
                }
                catch(NumberFormatException e)
                {
                    local_MUGrant.numMaxChart = 0;
                }

                boolean stat = conf_GlobalParam.CORBAConn.setUserGrant(local_MUGrant);
                if(stat)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"Operation correctly executed.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);                 
                    jB_commit.setEnabled(false);
                }
                else
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"Error: operation failed 4.","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE); 
                }
                
            }
            break;
            
            case 5: //run_pressNext
            {
                
                jB_next.removeActionListener(eventJButton);
                jB_back.removeActionListener(eventJButton);
                jB_reloadWizard.removeActionListener(eventJButton);
                
                pressNext();
                
                jB_next.addActionListener(eventJButton);
                jB_back.addActionListener(eventJButton);
                jB_reloadWizard.addActionListener(eventJButton);
            }
            break;
            
            case 6: //run_pressBack
            {
                jB_next.removeActionListener(eventJButton);
                jB_back.removeActionListener(eventJButton);
                jB_reloadWizard.removeActionListener(eventJButton);
                
                pressBack();
                
                jB_next.addActionListener(eventJButton);
                jB_back.addActionListener(eventJButton);
                jB_reloadWizard.addActionListener(eventJButton);
            }
            break;
        }
        
        
        try
        {
            this.setCursor(Cur_default);
            this.frameParent.setCursor(Cur_default);
            this.jList_Function.setCursor(Cur_hand);
            this.jList_Profile.setCursor(Cur_hand);
            this.jList_Function.setEnabled(true);
            this.jList_Profile.setEnabled(true);        
            this.jCB_RealTimeInteract.setCursor(Cur_hand);
            this.jTF_chart.setCursor(Cur_hand);
            this.jTA_descProfile.setCursor(Cur_default);
            this.jTF_nameProfile.setCursor(Cur_default);
            this.jB_reload.setEnabled(true);
            this.jB_reload.setCursor(Cur_hand);
            
            jB_back.setCursor(Cur_hand);
            jB_next.setCursor(Cur_hand);
            jB_reloadWizard.setCursor(Cur_hand);
        }
        catch(Exception e){}
        
        OperationTH = -1;
        
    }

    
    private void read_Profile_MON()
    {        
        jList_Profile.removeAll();        
        jList_Profile.removeMouseListener(eventList_profile);
        jList_Function.removeAll();
        
        jTF_nameProfile.setText("");
        jTA_descProfile.setText("");

        for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
        {                
            if(conf_GlobalParam.ID_MOD_MONITOR == conf_GlobalParam.config_lib.profileSeq[i].idClass)
                jList_Profile.addElement("profile.png", "profile.png", new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim());
        }

        jCB_RealTimeInteract.setSelected(false);
        jCB_RealTimeInteract.setEnabled(false);
        jTF_chart.setEnabled(false);
        jTF_chart.setText("");
        jLabel_maxChart.setEnabled(false);
        jB_commit.setEnabled(false);
        local_MUGrant = null;
        
        jScrollP_Profile.setViewportView(jList_Profile);
        jList_Profile.addMouseListener(eventList_profile);
    }
    
    
    private void jList_ProfileMousePressed(java.awt.event.MouseEvent evt)
    {
        //System.out.println("jList_ProfileMousePressed");
        int indexItemSelected = jList_Profile.getSelectedIndex();
        if( indexItemSelected >= 0)
        {           
            String str_profile = jList_Profile.get_ID_Value(indexItemSelected);               
            S_PROFILE s_profile_selected = get_S_PROFILE(str_profile);
            
            if(s_profile_selected != null)
            {     
                String STR_PROFILE = new String(s_profile_selected.profile).trim();
                read_Function(STR_PROFILE);
                jTF_nameProfile.setText(new String(STR_PROFILE).trim());
                jTA_descProfile.setText(new String(s_profile_selected.descProfile).trim());
                
                load_ProfileGrant();
            }
            else
            {
                jList_Function.removeAll();
                jTF_nameProfile.setText("");
                jTA_descProfile.setText("");
            }       
        }
    }   
    
    private S_PROFILE get_S_PROFILE(String profile)
    {
        //System.out.println("get_S_PROFILE -------------> "+profile);
        for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
        {
            if(profile.equals( new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim() ))
                return conf_GlobalParam.config_lib.profileSeq[i];                
        }
        return null;
    }
    
    private void read_Function(String STR_PROFILO)
    {
        //System.out.println("read_Function --> ID_PROFILO "+STR_PROFILO);
        jList_Function.removeAll();        

        for(int y=0; y<conf_GlobalParam.config_lib.profileSeq.length; y++)
        {
            // Funzioni abilitate del Profilo
            if( new String(conf_GlobalParam.config_lib.profileSeq[y].profile).trim().equals(STR_PROFILO) )    
            {
                for(int z=0; z<conf_GlobalParam.config_lib.profileSeq[y].functionSeq.length; z++)
                {
                    jList_Function.addElement("function_en.png", "function_en.png", (new String(conf_GlobalParam.config_lib.profileSeq[y].functionSeq[z].nameFunction)).trim());
                }
            }
        }
        jScroll_Function.setViewportView(jList_Function);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_Title;
    private javax.swing.JTabbedPane jTabbed_MonitorManager;
    private javax.swing.JPanel jP_MON_funGraph;
    private javax.swing.JPanel jP_Tot;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jB_back;
    private javax.swing.JButton jB_next;
    private javax.swing.JButton jB_reloadWizard;
    private javax.swing.JPanel jP_templateStart;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jB_TemplateEditor;
    private javax.swing.JPanel jP_MON_AllowedProfile;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollP_Profile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTF_nameProfile;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTA_descProfile;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScroll_Function;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_reload;
    private javax.swing.JPanel jPanel_sc;
    private javax.swing.JCheckBox jCB_RealTimeInteract;
    private javax.swing.JTextField jTF_chart;
    private javax.swing.JLabel jLabel_maxChart;
    // End of variables declaration//GEN-END:variables

    
    private java.awt.Cursor Cur_hand                    = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Cursor Cur_wait                    = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor Cur_default                 = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);

    private Object objShow                              = null;  // pannello step corrente Wizard        
    
    // USER CONF
    //private MON_JP_StartWizard JP_StartW                = null;
    //private MON_JP_UserGrant JP_UsGrant                 = null;
    //private MON_JP_UGrand_end JP_UG_end                 = null;
    
    // Enable and Configuration
    private MON_JP_EnablePlugin JP_EnablePlugin         = null;
    private MON_JP_ConfPLugin JP_ConfPLugin             = null;
    private MON_JP_ConfSubFunction JP_ConfSubFunction   = null;
    private MON_JP_FunctionGUI JP_FunctionGUI           = null;
    private MON_JP_ChartGUI JP_ChartGUI                 = null;
    private MON_JP_ConfPL_end JP_endPL                  = null;
    
    private javax.swing.ImageIcon icon_done             = null;
    private javax.swing.ImageIcon icon_failed           = null;
    private String str_done                             = new String("OPERATION DONE.");
    private String str_failed                           = new String("OPERATION FAILDED.");

    //Event Button
    private java.awt.event.ActionListener eventJButton  = null;
    
    private Thread TH           = null;
    private int OperationTH     = -1;
    //private boolean TH_EXIT     = false;
    
    javax.swing.JFrame frameParent;
    
    private conf_IconPool IcPool                            = null;
    private conf_JListIcon jList_Profile                    = null;
    private java.awt.event.MouseAdapter eventList_profile   = null;
    
    private conf_JListIcon jList_Function                   = null;    
    private MONITOR_USER_GRANT local_MUGrant                = null;
    
    private MON_JF_SelSession frameMonitor                  = null;
    
}