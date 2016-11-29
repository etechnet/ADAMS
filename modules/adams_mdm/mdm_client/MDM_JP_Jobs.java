import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;
/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe istanzia un oggetto grafico di tipo JPanel contenente tutti i widget necessari per
 * una corretta schedulazione di una richiesta formulata dall'utente.
 * La lista degli utenti adibiti all'uso di quest'oggetto grafico(e quindi aventi possibilitï¿½ di 
 * schedulare una richiesta)viene passata al client dal Master Server, tramite lo strato CORBA.
 * La lista degli utenti ï¿½ disponibile nell'array <b>STRUCT_USER[]</b>.
 * N.B.: il primo utente della array contenente oggetti di tipo STRUCT_USER ï¿½ quello che possiede il massimo dei grant.
 * </font></font></p></pre>
 * <p align="center">&nbsp;</p>
 * @see jpJobValue
 * @see jpLabelValue
 * @see STRUCT_JOB
 * @see STRUCT_USER   
 */ 

public class MDM_JP_Jobs extends javax.swing.JPanel {

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore di classe.
     * Il costruttore in questione si incarica di istanziare inizializzare e posizionare i vari widget che
     * costituiscono l'interfaccia.
     * </font></font></p></pre>  
     */
    public MDM_JP_Jobs(getConfigFiltro Configuration) {
        
        this.configuration = Configuration;
        jobPUT = new net.etech.STRUCT_JOB();
        
        initComponents();
        
        if( staticLib.codaNumber !=-1 )
        {
            AppletAspetto3 jpBROWSER = new AppletAspetto3();
            jpBROWSER.init();        
            jP_Reports.add(jpBROWSER, java.awt.BorderLayout.CENTER);
        }
        
        Tabella.addTab("Show Jobs", new javax.swing.ImageIcon(getClass().getResource("/images/show_job.png")), jpSHOW, "Show Jobs");
        Tabella.addTab("Insert Jobs", new javax.swing.ImageIcon(getClass().getResource("/images/insert_job.png")), jpINS, "Insert Jobs");
        Tabella.addTab("View Reports", new javax.swing.ImageIcon(getClass().getResource("/images/insert_job.png")), jP_Reports, "View Reports");
        
        //Tabella.addTab("View Reports", jpBROWSER);
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        
        ////jText_user.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC,11));
        ////jPassword.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC,11));
        
        jtf = new JTextFieldFilter(JTextFieldFilter.NUMERIC,2);
        jTF_days.setDocument(jtf);
                
        jtf1 =new JTextFieldFilter(JTextFieldFilter.NUMERIC,2); 
        jTF_StartHour.setDocument(jtf1);
                
        jSlider_start.putClientProperty("JSlider.isFilled", Boolean.TRUE );
        jL_valueSliderStart.setText("00");
        jSlider_stop.putClientProperty("JSlider.isFilled", Boolean.TRUE );
        jL_valueSliderStop.setText("00");
        
        selectCalendar2 = new MDM_JP_Calendar(staticLib.struc_paramsCURRENT,true,null,false);
        selectCalendar2.setBounds(10,10,420,330);
        jpINS.add(selectCalendar2);
        
        staticLib.l = new javax.swing.JLabel("WARNING: sheduled job not yet submitted    ");
        staticLib.l.setFont(staticLib.fontA11);
        staticLib.l.setBounds(10,360,420,20);
        jpINS.add(staticLib.l);
        
        //Label Globale (ereditata dal vecchio NTM BOOHH!! che finezza!)
        staticLib.startDATE = new javax.swing.JLabel();
        staticLib.startDATE.setForeground(java.awt.Color.black);
        staticLib.startDATE.setBackground(java.awt.Color.lightGray);
        staticLib.startDATE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        staticLib.startDATE.setOpaque(true);
        jP_options.add(staticLib.startDATE);
        staticLib.startDATE.setFont(staticLib.fontA11);
        staticLib.startDATE.setBounds(90, 30, 150, 20);
        
        //Label Globale (ereditata dal vecchio NTM BOOHH!! che finezza!)
        staticLib.endDATE = new javax.swing.JLabel();
        staticLib.endDATE.setForeground(java.awt.Color.black);
        staticLib.endDATE.setBackground(java.awt.Color.lightGray);
        staticLib.endDATE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        staticLib.endDATE.setOpaque(true);
        jP_options.add(staticLib.endDATE);
        staticLib.endDATE.setFont(staticLib.fontA11);
        staticLib.endDATE.setBounds(320, 30, 150, 20);
        
        
        //Font
        //jL_icon.setFont(staticLib.fontA11);
        //jL_user.setFont(staticLib.fontA11);
        //jText_user.setFont(staticLib.fontA11);
    //jL_pass.setFont(staticLib.fontA11);
        //jPassword.setFont(staticLib.fontA11);
        Tabella.setFont(staticLib.fontA11);
        jB_refresh_Jobs.setFont(staticLib.fontA11); 
        //jB_info_job.setFont(staticLib.fontA11);
        jB_edit_job.setFont(staticLib.fontA11);
        jB_del_job.setFont(staticLib.fontA11);
        jB_del_all_job.setFont(staticLib.fontA11);
        jLabel1.setFont(staticLib.fontA11);
        jLabel2.setFont(staticLib.fontA11);
        jLabel5.setFont(staticLib.fontA11);
        jCbox_modality.setFont(staticLib.fontA11);
        jLabel10.setFont(staticLib.fontA11);
        
        jLabel_days.setFont(staticLib.fontA11);
        jLabel_ValueR0_7.setFont(staticLib.fontA11);
        jTF_days.setFont(staticLib.fontA11);
        
        jLabel_ValueR0_24.setEnabled(false);
        jLabel_start_at_h.setEnabled(false);
        jTF_StartHour.setFont(staticLib.fontA11);
        
        jTF_Desc.setFont(staticLib.fontA11);
        jRB_15.setFont(staticLib.fontA11);
        jRB_30.setFont(staticLib.fontA11);
        jRB_1hour.setFont(staticLib.fontA11);
        jSlider_start.setFont(staticLib.fontA11);
        jSlider_stop.setFont(staticLib.fontA11);
        jL_valueSliderStart.setFont(staticLib.fontA11);
        jL_valueSliderStop.setFont(staticLib.fontA11);
        jB_resetHour.setFont(staticLib.fontA11);
        jbIns.setFont(staticLib.fontA11);
        jbCanc.setFont(staticLib.fontA11);
        
        //Cursor
        jB_refresh_Jobs.setCursor(Cur_hand);
        //jB_info_job.setCursor(Cur_hand);
        jB_edit_job.setCursor(Cur_hand);
        jB_del_job.setCursor(Cur_hand);
        jB_del_all_job.setCursor(Cur_hand);
        //jB_apply.setCursor(Cur_hand);
        //jB_cancel.setCursor(Cur_hand);
        jRB_15.setCursor(Cur_hand);
        jRB_30.setCursor(Cur_hand);
        jRB_1hour.setCursor(Cur_hand);
        jB_resetHour.setCursor(Cur_hand);
        jbIns.setCursor(Cur_hand);
        jbCanc.setCursor(Cur_hand);
   
        // utenteAbilitato(); nello
        
        jCbox_modality.removeAllItems();
        if(staticLib.utentePrivileggiato)
        {
            jCbox_modality.addItem("Daily");
            jCbox_modality.addItem("Weekly cumulative");
            jCbox_modality.addItem("Last n days");
            jCbox_modality.addItem("Real time");
        }else
        {
            jCbox_modality.addItem("Daily");
            jCbox_modality.addItem("Weekly cumulative");
            jCbox_modality.addItem("Last n days of week");
        }
        
        creaSTRUCT_USER();
        
        Tabella.setEnabledAt(1,false); //nello
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jP_center = new javax.swing.JPanel();
        Tabella = new javax.swing.JTabbedPane();
        jpSHOW = new javax.swing.JPanel();
        jP_east = new javax.swing.JPanel();
        jB_refresh_Jobs = new javax.swing.JButton();
        jB_edit_job = new javax.swing.JButton();
        jB_del_job = new javax.swing.JButton();
        jB_del_all_job = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpINS = new javax.swing.JPanel();
        jP_options = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCbox_modality = new javax.swing.JComboBox();
        jLabel_days = new javax.swing.JLabel();
        jLabel_start_at_h = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTF_days = new javax.swing.JTextField();
        jTF_StartHour = new javax.swing.JTextField();
        jLabel_ValueR0_7 = new javax.swing.JLabel();
        jLabel_ValueR0_24 = new javax.swing.JLabel();
        jTF_Desc = new javax.swing.JTextField();
        jP_hourRestrict = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jRB_15 = new javax.swing.JRadioButton();
        jRB_30 = new javax.swing.JRadioButton();
        jRB_1hour = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSlider_start = new javax.swing.JSlider();
        jSlider_stop = new javax.swing.JSlider();
        jL_valueSliderStart = new javax.swing.JLabel();
        jL_valueSliderStop = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jB_resetHour = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jbIns = new javax.swing.JButton();
        jbCanc = new javax.swing.JButton();
        jP_Reports = new javax.swing.JPanel();
        
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setLayout(new java.awt.BorderLayout());
        
        jP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Schedule Jobs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11)));
        Tabella.setBackground(new java.awt.Color(230, 230, 230));
        jpSHOW.setLayout(new java.awt.BorderLayout());
        
        jpSHOW.setBackground(new java.awt.Color(230, 230, 230));
        jP_east.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_east.setBackground(new java.awt.Color(230, 230, 230));
        jP_east.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11)));
        jP_east.setPreferredSize(new java.awt.Dimension(120, 10));
        jB_refresh_Jobs.setBackground(new java.awt.Color(230, 230, 230));
        jB_refresh_Jobs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_jobs.jpg")));
        jB_refresh_Jobs.setToolTipText("Refresh Jobs");
        jB_refresh_Jobs.setBorderPainted(false);
        jB_refresh_Jobs.setContentAreaFilled(false);
        jB_refresh_Jobs.setFocusPainted(false);
        jB_refresh_Jobs.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_refresh_Jobs.setMaximumSize(new java.awt.Dimension(100, 25));
        jB_refresh_Jobs.setMinimumSize(new java.awt.Dimension(100, 25));
        jB_refresh_Jobs.setPreferredSize(new java.awt.Dimension(100, 25));
        jB_refresh_Jobs.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_jobs_press.jpg")));
        jB_refresh_Jobs.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_jobs_over.jpg")));
        jB_refresh_Jobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_refresh_JobsActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(20, 0, 20, 0);
        jP_east.add(jB_refresh_Jobs, gridBagConstraints1);
        
        jB_edit_job.setBackground(new java.awt.Color(230, 230, 230));
        jB_edit_job.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_job.jpg")));
        jB_edit_job.setToolTipText("Edit Job");
        jB_edit_job.setBorderPainted(false);
        jB_edit_job.setContentAreaFilled(false);
        jB_edit_job.setFocusPainted(false);
        jB_edit_job.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_edit_job.setMaximumSize(new java.awt.Dimension(100, 25));
        jB_edit_job.setMinimumSize(new java.awt.Dimension(100, 25));
        jB_edit_job.setPreferredSize(new java.awt.Dimension(100, 25));
        jB_edit_job.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_job_press.jpg")));
        jB_edit_job.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_job_over.jpg")));
        jB_edit_job.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_edit_jobActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets(20, 0, 20, 0);
        jP_east.add(jB_edit_job, gridBagConstraints1);
        
        jB_del_job.setBackground(new java.awt.Color(230, 230, 230));
        jB_del_job.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_job.jpg")));
        jB_del_job.setToolTipText("Delete Job");
        jB_del_job.setBorderPainted(false);
        jB_del_job.setContentAreaFilled(false);
        jB_del_job.setFocusPainted(false);
        jB_del_job.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_del_job.setMaximumSize(new java.awt.Dimension(100, 25));
        jB_del_job.setMinimumSize(new java.awt.Dimension(100, 25));
        jB_del_job.setPreferredSize(new java.awt.Dimension(100, 25));
        jB_del_job.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_job_press.jpg")));
        jB_del_job.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_job_over.jpg")));
        jB_del_job.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_del_jobActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.insets = new java.awt.Insets(20, 0, 20, 0);
        jP_east.add(jB_del_job, gridBagConstraints1);
        
        jB_del_all_job.setBackground(new java.awt.Color(230, 230, 230));
        jB_del_all_job.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_all.jpg")));
        jB_del_all_job.setToolTipText("Delete All");
        jB_del_all_job.setBorderPainted(false);
        jB_del_all_job.setContentAreaFilled(false);
        jB_del_all_job.setFocusPainted(false);
        jB_del_all_job.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_del_all_job.setMaximumSize(new java.awt.Dimension(100, 25));
        jB_del_all_job.setMinimumSize(new java.awt.Dimension(100, 25));
        jB_del_all_job.setPreferredSize(new java.awt.Dimension(100, 25));
        jB_del_all_job.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_all_press.jpg")));
        jB_del_all_job.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_all_over.jpg")));
        jB_del_all_job.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_del_all_jobActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.insets = new java.awt.Insets(20, 0, 20, 0);
        jP_east.add(jB_del_all_job, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        jP_east.add(jSeparator1, gridBagConstraints1);
        
        jpSHOW.add(jP_east, java.awt.BorderLayout.EAST);
        
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " List Jobs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11)));
        jpSHOW.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        
        Tabella.addTab("Show Jobs", jpSHOW);
        
        jpINS.setLayout(null);
        
        jpINS.setBackground(new java.awt.Color(230, 230, 230));
        jP_options.setLayout(null);
        
        jP_options.setBackground(new java.awt.Color(230, 230, 230));
        jP_options.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11)));
        jLabel1.setText("Start Data ");
        jP_options.add(jLabel1);
        jLabel1.setBounds(10, 30, 80, 20);
        
        jLabel2.setText("End Data");
        jP_options.add(jLabel2);
        jLabel2.setBounds(250, 30, 70, 20);
        
        jLabel5.setText("Modality");
        jP_options.add(jLabel5);
        jLabel5.setBounds(10, 60, 80, 20);
        
        jCbox_modality.setBackground(new java.awt.Color(230, 230, 230));
        jCbox_modality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbox_modalityActionPerformed(evt);
            }
        });
        
        jP_options.add(jCbox_modality);
        jCbox_modality.setBounds(90, 60, 240, 20);
        
        jLabel_days.setText("Days");
        jP_options.add(jLabel_days);
        jLabel_days.setBounds(10, 90, 40, 20);
        
        jLabel_start_at_h.setText("Start at hour");
        jP_options.add(jLabel_start_at_h);
        jLabel_start_at_h.setBounds(230, 90, 90, 20);
        
        jLabel10.setText("Description");
        jP_options.add(jLabel10);
        jLabel10.setBounds(10, 120, 90, 20);
        
        jTF_days.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_daysKeyReleased(evt);
            }
        });
        
        jP_options.add(jTF_days);
        jTF_days.setBounds(50, 90, 30, 20);
        
        jTF_StartHour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_StartHourKeyReleased(evt);
            }
        });
        
        jP_options.add(jTF_StartHour);
        jTF_StartHour.setBounds(320, 90, 30, 20);
        
        jLabel_ValueR0_7.setText(" (Value range: 0-7)");
        jP_options.add(jLabel_ValueR0_7);
        jLabel_ValueR0_7.setBounds(80, 90, 140, 20);
        
        jLabel_ValueR0_24.setText(" (Value range: 0-24)");
        jP_options.add(jLabel_ValueR0_24);
        jLabel_ValueR0_24.setBounds(350, 90, 130, 20);
        
        jP_options.add(jTF_Desc);
        jTF_Desc.setBounds(100, 120, 380, 20);
        
        jpINS.add(jP_options);
        jP_options.setBounds(440, 10, 490, 150);
        
        jP_hourRestrict.setLayout(new java.awt.BorderLayout(0, 2));
        
        jP_hourRestrict.setBackground(new java.awt.Color(230, 230, 230));
        jP_hourRestrict.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Hour Restriction ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11)));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jRB_15.setBackground(new java.awt.Color(230, 230, 230));
        jRB_15.setSelected(true);
        jRB_15.setText("15 min.");
        buttonGroup1.add(jRB_15);
        jRB_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRB_15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jRB_15.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jRB_15.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jRB_15.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jRB_15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_ActionPerformed(evt);
            }
        });
        
        jPanel5.add(jRB_15);
        
        jRB_30.setBackground(new java.awt.Color(230, 230, 230));
        jRB_30.setText("30 min.");
        buttonGroup1.add(jRB_30);
        jRB_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRB_30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jRB_30.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jRB_30.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jRB_30.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jRB_30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_ActionPerformed(evt);
            }
        });
        
        jPanel5.add(jRB_30);
        
        jRB_1hour.setBackground(new java.awt.Color(230, 230, 230));
        jRB_1hour.setText("1 hour");
        buttonGroup1.add(jRB_1hour);
        jRB_1hour.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRB_1hour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jRB_1hour.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jRB_1hour.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jRB_1hour.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jRB_1hour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_ActionPerformed(evt);
            }
        });
        
        jPanel5.add(jRB_1hour);
        
        jP_hourRestrict.add(jPanel5, java.awt.BorderLayout.NORTH);
        
        jPanel6.setLayout(null);
        
        jPanel6.setBackground(new java.awt.Color(230, 230, 230));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jPanel6.setPreferredSize(new java.awt.Dimension(0, 100));
        jLabel14.setText("Start");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(10, 20, 40, 20);
        
        jLabel15.setText("Stop");
        jPanel6.add(jLabel15);
        jLabel15.setBounds(10, 80, 40, 20);
        
        jSlider_start.setBackground(new java.awt.Color(230, 230, 230));
        jSlider_start.setForeground(java.awt.Color.black);
        jSlider_start.setMajorTickSpacing(12);
        jSlider_start.setMaximum(24);
        jSlider_start.setMinorTickSpacing(1);
        jSlider_start.setPaintLabels(true);
        jSlider_start.setPaintTicks(true);
        jSlider_start.setValue(0);
        jSlider_start.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider_startStateChanged(evt);
            }
        });
        
        jPanel6.add(jSlider_start);
        jSlider_start.setBounds(70, 10, 360, 50);
        
        jSlider_stop.setBackground(new java.awt.Color(230, 230, 230));
        jSlider_stop.setForeground(java.awt.Color.black);
        jSlider_stop.setMajorTickSpacing(12);
        jSlider_stop.setMaximum(24);
        jSlider_stop.setMinorTickSpacing(1);
        jSlider_stop.setPaintLabels(true);
        jSlider_stop.setPaintTicks(true);
        jSlider_stop.setValue(0);
        jSlider_stop.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider_stopStateChanged(evt);
            }
        });
        
        jPanel6.add(jSlider_stop);
        jSlider_stop.setBounds(70, 70, 360, 50);
        
        jL_valueSliderStart.setBackground(java.awt.Color.lightGray);
        jL_valueSliderStart.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_valueSliderStart.setOpaque(true);
        jPanel6.add(jL_valueSliderStart);
        jL_valueSliderStart.setBounds(440, 20, 30, 20);
        
        jL_valueSliderStop.setBackground(java.awt.Color.lightGray);
        jL_valueSliderStop.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_valueSliderStop.setOpaque(true);
        jPanel6.add(jL_valueSliderStop);
        jL_valueSliderStop.setBounds(440, 80, 30, 20);
        
        jP_hourRestrict.add(jPanel6, java.awt.BorderLayout.CENTER);
        
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 2));
        
        jPanel7.setBackground(new java.awt.Color(230, 230, 230));
        jPanel7.setPreferredSize(new java.awt.Dimension(74, 25));
        jB_resetHour.setBackground(new java.awt.Color(230, 230, 230));
        jB_resetHour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hour_reset.jpg")));
        jB_resetHour.setToolTipText("Hour Reset ");
        jB_resetHour.setBorderPainted(false);
        jB_resetHour.setContentAreaFilled(false);
        jB_resetHour.setFocusPainted(false);
        jB_resetHour.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_resetHour.setMaximumSize(new java.awt.Dimension(90, 22));
        jB_resetHour.setMinimumSize(new java.awt.Dimension(90, 22));
        jB_resetHour.setPreferredSize(new java.awt.Dimension(90, 22));
        jB_resetHour.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hour_reset_press.jpg")));
        jB_resetHour.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hour_reset_over.jpg")));
        jB_resetHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_resetHourActionPerformed(evt);
            }
        });
        
        jPanel7.add(jB_resetHour);
        
        jP_hourRestrict.add(jPanel7, java.awt.BorderLayout.SOUTH);
        
        jpINS.add(jP_hourRestrict);
        jP_hourRestrict.setBounds(440, 180, 490, 220);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jbIns.setBackground(new java.awt.Color(230, 230, 230));
        jbIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert_job.jpg")));
        jbIns.setBorderPainted(false);
        jbIns.setContentAreaFilled(false);
        jbIns.setFocusPainted(false);
        jbIns.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jbIns.setMaximumSize(new java.awt.Dimension(100, 22));
        jbIns.setMinimumSize(new java.awt.Dimension(100, 22));
        jbIns.setPreferredSize(new java.awt.Dimension(100, 22));
        jbIns.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert_job_press.jpg")));
        jbIns.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert_job_over.jpg")));
        jbIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        jPanel4.add(jbIns, gridBagConstraints2);
        
        jbCanc.setBackground(new java.awt.Color(230, 230, 230));
        jbCanc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_job.jpg")));
        jbCanc.setToolTipText("Cancel Job");
        jbCanc.setBorderPainted(false);
        jbCanc.setContentAreaFilled(false);
        jbCanc.setFocusPainted(false);
        jbCanc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jbCanc.setMaximumSize(new java.awt.Dimension(100, 22));
        jbCanc.setMinimumSize(new java.awt.Dimension(100, 22));
        jbCanc.setPreferredSize(new java.awt.Dimension(100, 22));
        jbCanc.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_job_press.jpg")));
        jbCanc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_job_over.jpg")));
        jbCanc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        jPanel4.add(jbCanc, gridBagConstraints2);
        
        jpINS.add(jPanel4);
        jPanel4.setBounds(10, 400, 930, 90);
        
        Tabella.addTab("Insert Jobs", jpINS);
        
        jP_Reports.setLayout(new java.awt.BorderLayout());
        
        jP_Reports.setBackground(new java.awt.Color(230, 230, 230));
        Tabella.addTab("View Reports", jP_Reports);
        
        jP_center.add(Tabella, java.awt.BorderLayout.CENTER);
        
        add(jP_center, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents

    private void jTF_StartHourKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_StartHourKeyReleased
       
        String str_hour = jTF_StartHour.getText().trim();
        
        if(str_hour.length() > 0)
        {
            int numberHour = new Integer(str_hour).intValue();
            int min = 0;
            int max = 24;
            if((numberHour < min) || (numberHour > max))
            {
                staticLib.optionPanel("Value Range: "+min+" - "+max+".", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                jTF_StartHour.setText("");
            }
        }
        
    }//GEN-LAST:event_jTF_StartHourKeyReleased

    private void jTF_daysKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_daysKeyReleased
        
        String str_numberDay = jTF_days.getText().trim();
        
        if(str_numberDay.length() > 0)
        {
            int numberDay = new Integer(str_numberDay).intValue();
            int min = 0;
            int max = 30;
            if((numberDay < min) || (numberDay > max))
            {
                staticLib.optionPanel("Value Range: "+min+" - "+max+".", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                jTF_days.setText("");
            }
        }
                
    }//GEN-LAST:event_jTF_daysKeyReleased

    private void jRB_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_ActionPerformed
        
        Object target = evt.getSource();
        
        if(target == jRB_15)
        {
            jobPUT.period = (short)0;
        }
        else if(target == jRB_30)
        {
            jobPUT.period = (short)1;
        }
        else if(target == jRB_1hour)
        {
            jobPUT.period = (short)2;
        }
    }//GEN-LAST:event_jRB_ActionPerformed

    public void validateOra()
    {
        jSlider_start.setValue(0);
        jSlider_stop.setValue(0);
        jobPUT.start_hour = (short)jSlider_start.getValue(); 
        jobPUT.end_hour = (short)jSlider_stop.getValue(); 
        jobPUT.period = (short)0;
    }
    
    private void jB_resetHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_resetHourActionPerformed
        validateOra();
    }//GEN-LAST:event_jB_resetHourActionPerformed

    private void jSlider_stopStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider_stopStateChanged
         if(jSlider_stop.getValue()<10)
            jL_valueSliderStop.setText(" 0"+jSlider_stop.getValue()+" ");
        else
            jL_valueSliderStop.setText(" "+jSlider_stop.getValue()+" ");
         
        jobPUT.end_hour = (short)jSlider_stop.getValue();
    }//GEN-LAST:event_jSlider_stopStateChanged

    private void jSlider_startStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider_startStateChanged

        if(jSlider_start.getValue()<10)
            jL_valueSliderStart.setText(" 0"+jSlider_start.getValue()+" ");
        else
            jL_valueSliderStart.setText(" "+jSlider_start.getValue()+" ");
        
        jobPUT.start_hour = (short)jSlider_start.getValue();
    }//GEN-LAST:event_jSlider_startStateChanged

    private void jCbox_modalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbox_modalityActionPerformed
        //////System.out.println("Index: "+jCbox_modality.getSelectedIndex());
        if(jCbox_modality.getSelectedIndex()==3)
        {
            //******************************************************
            // Modalitï¿½ Real-Time
            //******************************************************
            setGUI_hourRestrict(true);
            
            jLabel_days.setEnabled(false);
            jLabel_ValueR0_7.setEnabled(false);
            jTF_days.setEnabled(false);
            
            jLabel_ValueR0_24.setEnabled(false);
            jLabel_start_at_h.setEnabled(false);
            jTF_StartHour.setEnabled(false);
        }
        else if(jCbox_modality.getSelectedIndex()==2)
        {
            //**********************************************
            // Modalitï¿½ Last n days
            //**********************************************
            setGUI_hourRestrict(false);
            
            jLabel_days.setEnabled(true);
            jLabel_ValueR0_7.setEnabled(true);
            jTF_days.setEnabled(true);
            
            jLabel_ValueR0_24.setEnabled(true);
            jLabel_start_at_h.setEnabled(true);
            jTF_StartHour.setEnabled(true);
        }
        else if(jCbox_modality.getSelectedIndex()==1)
        {
            //**********************************************
            // Modalitï¿½ weekly
            //**********************************************								
            setGUI_hourRestrict(false);
            
            jLabel_days.setEnabled(false);
            jLabel_ValueR0_7.setEnabled(false);
            jTF_days.setEnabled(false);
            
            jLabel_ValueR0_24.setEnabled(true);
            jLabel_start_at_h.setEnabled(true);
            jTF_StartHour.setEnabled(true);
        }
        else if(jCbox_modality.getSelectedIndex()==0)
        {
            //**********************************************
            // Modalitï¿½ Daily
            // a partire da <Start_data> fino ad <end_data>
            // faccio il report giornaliero che perte alle ore <start_at>
            //**********************************************						
            setGUI_hourRestrict(false);
            
            jLabel_days.setEnabled(false);
            jLabel_ValueR0_7.setEnabled(false);
            jTF_days.setEnabled(false);
            
            jLabel_ValueR0_24.setEnabled(true);
            jLabel_start_at_h.setEnabled(true);
            jTF_StartHour.setEnabled(true);
        }
    }//GEN-LAST:event_jCbox_modalityActionPerformed

    private void jbCancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancActionPerformed
        staticLib.optionPanel("Request deleted", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        staticLib.richiestaPronta = false;
        Tabella.setEnabledAt(1,false);
        Tabella.setSelectedIndex(0);
        staticLib.l.setText("");
        resetGUI(); 
    }//GEN-LAST:event_jbCancActionPerformed

    private void resetGUI()
    {
    	staticLib.startDATE.setText("");
    	staticLib.endDATE.setText("");
    	jTF_days.setText("");
    	jTF_StartHour.setText("");
    	jTF_Desc.setText("");
        try
        {
            jCbox_modality.setSelectedIndex(0);
        }catch(Exception e)
        {}
    }
    
    private  short getOra()
    {
        try
        {
                return (short)Integer.parseInt(jTF_StartHour.getText());
        }catch(Exception e)
        {
                return 0;
        }
    }
    private int getModality()
    {
        if(jCbox_modality.getSelectedIndex()==0)
                return 3;
        else if(jCbox_modality.getSelectedIndex()==1)
                return 2;
        else if(jCbox_modality.getSelectedIndex()==2)
                return 1;
        else if(jCbox_modality.getSelectedIndex()==3)
                return 7;

        return -1;
    }
    
    private int getDays()
    {
        try
        {
            return Integer.parseInt(jTF_days.getText());	
        }catch(Exception e)
        {
            return 0;
        }
    }

    private  char[] getDesc()
    {
        return jTF_Desc.getText().toCharArray();
    }
    
    /*private void validateJob()
    {
        jCbox_modality.removeAllItems();
        if(staticLib.utentePrivileggiato)
        {
            jCbox_modality.addItem("Daily");
            jCbox_modality.addItem("Weekly cumulative");
            jCbox_modality.addItem("Last n days of week");
            jCbox_modality.addItem("Real time");
        }else
        {
            jCbox_modality.addItem("Daily");
            jCbox_modality.addItem("Weekly cumulative");
            jCbox_modality.addItem("Last n days of week");
        }
    }*/
    
    
    private void jbInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsActionPerformed
        //***************************************************
        //				INSERT JOB
        //***************************************************
        //////System.out.println("Number JOB: "+numJOB);
        
        if (staticLib.struc_paramsCURRENT.Relation == staticLib.RELATION_FREEFORMAT_ID )
        {
            staticLib.optionPanel("No Schedule Request for Free Format selection.","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            staticLib.richiestaPronta=false;
            Tabella.setEnabledAt(1,false);
            Tabella.setSelectedIndex(0);
            staticLib.l.setText("");
            resetGUI();
            return;
        }
        
        
                
        String domanda = "";
        if(numJOB>=20)
        {
            staticLib.optionPanel("The queue is full.", "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            staticLib.richiestaPronta=false;
            Tabella.setEnabledAt(1,false);
            Tabella.setSelectedIndex(0);
            staticLib.l.setText("");
            resetGUI();
            return;
        }
        if(!flagEDIT)
                domanda = "Insert job ? ";
        else
                domanda = "Change job ? ";
        int risp1 = staticLib.confirmPanel(domanda,"Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
        if(risp1==0)
        {
            //boolean risp=UserIsValid(jtfUser.getText().trim(),jtfPass.getText().trim());		
            //if(risp)
            //{
            //******************************************
            // INVIO LA RICHIESTA 
            //******************************************
            jobPUT.new_job=1;
            //jobPUT.job_id=1;				
            jobPUT.user_str=stu.user;
           //System.out.println("PUT USER: "+staticLib.startDATE_ghost);
            //jobPUT.user_id=stu.iID_User;
            jobPUT.user_id=staticLib.codaNumber;
           //System.out.println("Coda Number: "+jobPUT.user_id);
           //System.out.println("stu.user =>"+new String(stu.user).trim());
            
            jobPUT.data_start=staticLib.DataToInteger(staticLib.startDATE_ghost);
            jobPUT.data_end=staticLib.DataToInteger(staticLib.endDATE_ghost);
            jobPUT.hour=getOra();
            
            //jobPUT.modality = jpValue.jCbox_modality.getSelectedIndex(); //era giï¿½ commentato
            
            jobPUT.modality=getModality();
            jobPUT.qnt=getDays();
            jobPUT.dest_fep=new char[20];
            //jobPUT.last_day=1;
            //jobPUT.last_month=1;
            //jobPUT.last_quarter=1;

            //*************************** DATA di immissione *********** //nello da vedere 
            java.util.GregorianCalendar APPO_TODAY = new java.util.GregorianCalendar();

            int aa = (APPO_TODAY.get(java.util.Calendar.YEAR)); 
            int mm = (APPO_TODAY.get(java.util.Calendar.MONTH));
            int gg = (APPO_TODAY.get(java.util.Calendar.DATE));
            int hh = (APPO_TODAY.get(java.util.Calendar.HOUR_OF_DAY));
            int min = (APPO_TODAY.get(java.util.Calendar.MINUTE));
            int ss = (APPO_TODAY.get(java.util.Calendar.SECOND));

            String dir_dest = ""+aa+""+(mm+1)+""+gg+""+hh+""+min+""+ss;
            jobPUT.dest_dir = new char[256];
            for(int i=0;i<dir_dest.length();i++)
                    jobPUT.dest_dir[i]=dir_dest.charAt(i);

            //**********************************************************

            jobPUT.locked=0;
            
            jobPUT.description = new char[61];
            char[] appo_desc = getDesc();

            for(int i=0;i<appo_desc.length;i++)
                jobPUT.description[i]=appo_desc[i];

            boolean jobValidate=staticLib.validateSTRUCT_JOB(jobPUT);
            //////System.out.println("Validate JOBS: "+jobValidate);
            if(jobValidate)
            {
                try
                {
                    boolean result;
                    if(flagEDIT)
                    {
                       //System.out.println("---- 1 --- modifyJob");
                        jobPUT.job_id = (short)job.value[getCheckSet()].job_id;	
                        result = staticLib.MDM_JobActivatorRef.modifyJob(jobPUT);
                       //System.out.println("---- 2 --- modifyJob");
                    }
                    else 
                    {
                       //System.out.println("---- 1 --- insertJob");
                        result=staticLib.MDM_JobActivatorRef.insertJob(staticLib.struc_paramsCURRENT,jobPUT);
                       //System.out.println("---- 2 --- insertJob");
                    }
                    flagEDIT = false;	
                    staticLib.struc_paramsCURRENT.ElaborationData = null;
                    
                    if(result)
                            staticLib.optionPanel("Submitted request.", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    else
                            staticLib.optionPanel("Request not submitted.", "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception ex)
                {                    
                    staticLib.optionPanel("- Request not submitted. -", "Error",javax.swing.JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    return;
                }

                staticLib.richiestaPronta=false; // non ci sono richieste
                Tabella.setEnabledAt(1,false); // disabilito la tab dell'inserimento
                Tabella.setSelectedIndex(0); //ritornmo alla tab dei jobs 
                staticLib.l.setText(""); //resetto la label
                resetGUI();
                getJobs();
            }
        }
    }//GEN-LAST:event_jbInsActionPerformed

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
        //utenteDisabilitato();
        Tabella.setSelectedIndex(0);
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
      
        /*boolean risp = true;//UserIsValid(//jText_user.getText().trim(),//jPassword.getText().trim());
        if(risp)
        {
            try
            {
                validateJob();
            }catch(Exception e)
            {
                    ////System.out.println("Errore <Sto in modalitï¿½ show>");
            }
            //////System.out.println("Utente privileggiato: "+staticLib.utentePrivileggiato);
            //creaSTRUCT_USER();
            staticLib.optionPanel("User allowed", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            utenteAbilitato();
            getJobs();
            
        }
        else
        {
            staticLib.optionPanel1("Unknown user", "Error",javax.swing.JOptionPane.ERROR_MESSAGE,new javax.swing.ImageIcon(getClass().getResource("/images/pericolo.gif")));
            utenteDisabilitato();
            if( staticLib.RichiestaValida==1 && staticLib.richiestaPronta )
            {
                Tabella.setEnabledAt(1,true);
            }else
                Tabella.setEnabledAt(0,true);
        }*/
    }//GEN-LAST:event_jB_applyActionPerformed

    private void jB_del_all_jobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_del_all_jobActionPerformed
        int risp = staticLib.confirmPanel("Delete all submitted job of user <"+new String(stu.user).trim()+">","Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
        if(risp == 0)
        {
            try
            {
               //System.out.println("P removeAllJobs()");
                boolean result=staticLib.MDM_JobActivatorRef.removeAllJobs(stu);
               //System.out.println("D removeAllJobs()--> "+result);
                if(result)
                {
                    staticLib.optionPanel("All submitted job of user <"+new String(stu.user).trim()+"> are deleted","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    getJobs();
                }
                else
                    staticLib.optionPanel("Error occurs","ERROR",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception ex)
            {
                    System.out.println("removeAllJobs: "+ex);
            }
        }
    }//GEN-LAST:event_jB_del_all_jobActionPerformed

    private void jB_del_jobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_del_jobActionPerformed
        //**********************************************
        //					DELETE
        //**********************************************
        int index = getCheckSet();
        //System.out.println("DELETE: "+getCheckSet()+ "delet jons"+job.value[index].job_id);
        int risp = staticLib.confirmPanel("Delete job "+new String(job.value[index].description).trim() +" of user: <"+new String(stu.user).trim()+">","Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
        if(risp==0)
        {
            try
            {
                stu.job = job.value[index].job_id;
               //System.out.println("removeJob()");
                boolean result = staticLib.MDM_JobActivatorRef.removeJob(stu);
               //System.out.println("removeJob() ===> "+result);
                if(result)
                {
                    staticLib.optionPanel("Job "+new String(job.value[index].description).trim()+" of user <"+new String(stu.user).trim()+"> deleted","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    getJobs();
                }
                else
                  staticLib.optionPanel("Error occurs","ERROR",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception ex)
            {
                System.out.println("removeJob: "+ex);
            }
        }
    }//GEN-LAST:event_jB_del_jobActionPerformed

    private void jB_edit_jobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_edit_jobActionPerformed
        flagEDIT = true;
        Tabella.setEnabledAt(1,true);
        Tabella.setSelectedIndex(1);
        selectCalendar2.resetCalendar();
        editJob();
    }//GEN-LAST:event_jB_edit_jobActionPerformed
/*
    private void jB_info_jobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_info_jobActionPerformed
        //*********************************************
        // INFO JOB
        //*********************************************
        int index = getCheckSet();
        stu.job = job.value[index].job_id;
        STRUCT_PARAMSHolder stph = new STRUCT_PARAMSHolder();
        try
        {
            staticLib.MDM_JobActivatorRef.getJob(stph,stu);
        }
        catch(Exception ex)
        {
            ////System.out.println("JPJOBS: "+ex);
        }
        //che mi restituisce se non ci sono Jobs?????
        
        STRUCT_PARAMS tmp = new STRUCT_PARAMS();
      	tmp = staticLib.struc_paramsCURRENT;
       	staticLib.struc_paramsCURRENT = stph.value;
        staticLib.dallaTabJpJobs = true;
        staticLib.struc_paramsCURRENT.ElaborationData = null;
        staticLib.selectSummary1.setEnabled_jbSchedule(false);
        staticLib.Tabella.setSelectedIndex(3);
        staticLib.struc_paramsCURRENT = tmp;
        staticLib.struc_paramsCURRENT.user_ip = set_String_toChar(staticLib.IP_ADDRESS,staticLib.CORBA.IP_STRING_LEN);
        
    }//GEN-LAST:event_jB_info_jobActionPerformed
*/
    /*private void jB_info_jobActionPerformed(java.awt.event.ActionEvent evt) {
        //*********************************************
        // INFO JOB
        //*********************************************
        int index = getCheckSet();
        stu.job = job.value[index].job_id;
        STRUCT_PARAMSHolder stph = new STRUCT_PARAMSHolder();
        try
        {
            staticLib.MDM_JobActivatorRef.getJob(stph,stu);
        }
        catch(Exception ex)
        {
            ////System.out.println("JPJOBS: "+ex);
        }
        //che mi restituisce se non ci sono Jobs?????
        
        STRUCT_PARAMS tmp = new STRUCT_PARAMS();
      	tmp = staticLib.struc_paramsCURRENT;
       	staticLib.struc_paramsCURRENT = stph.value;
        staticLib.dallaTabJpJobs = true;
        staticLib.struc_paramsCURRENT.ElaborationData = null;
        staticLib.selectSummary1.setEnabled_jbSchedule(false);
        staticLib.Tabella.setSelectedIndex(3);
        staticLib.struc_paramsCURRENT = tmp;
        staticLib.struc_paramsCURRENT.user_ip = set_String_toChar(staticLib.IP_ADDRESS,staticLib.CORBA.IP_STRING_LEN);
        
    }*/
    
    
    
        /**
        * Metodo che permette di trasformare una stringa in un array di caratteri.
        *@param str Stringa il cui contenuto sarï¿½ copiato nell'array di char.
        *@param length dimensione dell'array di char.
        *@return Un'array di char dalle dimensione passata "length" contenente i caratteri della stringa passata in input "str".
        */ 
        private static char[] set_String_toChar(String str, int length)
        {
            char[] appo = str.toCharArray();
            char[] appo1 = new char[length];

            if (appo.length > length)
                return appo1;

            for (int i=0; i<appo.length; i++)
                appo1[i] = appo[i];

            for (int i=appo.length; i<length; i++) 
                appo1[i] =0;
            return(appo1);
        }
    
    private void jB_refresh_JobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_refresh_JobsActionPerformed
        //*********************************************
        // SHOWS JOBS
        //*********************************************
        //////System.out.println("GET JOBS");
        
        jB_refresh_Jobs.setEnabled(false);
        getJobs();
        jB_refresh_Jobs.setEnabled(true);        
        
    }//GEN-LAST:event_jB_refresh_JobsActionPerformed

     //*****************************************
    // Controllo se l'utente ï¿½ autorizzato
    //*****************************************
    /*private boolean UserIsValid(String usr, String pass)
    {
        
        for(int i=0;i<users.length;i++)
	{
            try
            {
                if((new String(users[i].user)).trim().equals(usr) && (new String(users[i].password)).trim().equals(pass))
                {
                    if(users[i].iID_User == 0)
                        staticLib.utentePrivileggiato = true;
                    else
                        staticLib.utentePrivileggiato = false;

                    return true;
                }
            }catch(Exception e)
            {
                    ////System.out.println("Error: <UserIsValid>");
            }
	}
	return false;    	
    }*/
    
    private int getCheckSet()
    {
        for(int i=0;i<jrb.length;i++)
        {
            if(jrb[i].isSelected())
                return i;
        }
        return -1;
    }
    
    /*public void utenteAbilitato()
    {
    	jB_refresh_Jobs.setEnabled(true);
    	//jB_cancel.setEnabled(true);
        jbIns.setEnabled(true);
        setGUI_Options(true);
    }/*
    
    /**
    * <pre>
    * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
    * Questo metodo disabilita o meno dei widget a seconda se l'utente ï¿½ abilitato alla schedulazione di job.
    * </font></font></p></pre>  
    */
    /*public void utenteDisabilitato()
    {
    	staticLib.utentePrivileggiato=false;
    	jB_refresh_Jobs.setEnabled(false);
    	jB_info_job.setEnabled(false);
    	jB_edit_job.setEnabled(false);
    	jB_del_job.setEnabled(false);
    	jB_del_all_job.setEnabled(false);
    	//jB_cancel.setEnabled(false);
        jbIns.setEnabled(false);
    	//jPassword.setText("");
        //jText_user.setText("");
        panelJob = null;
        jScrollPane1.setViewportView(panelNULL);
        Tabella.setEnabledAt(1,false);

        setGUI_Options(false);
        setGUI_hourRestrict(false);
    
        repaint();	
    }*/
    
    private void setGUI_Options(boolean flag)
    {
        jLabel1.setEnabled(flag);
        jLabel2.setEnabled(flag);
        jLabel5.setEnabled(flag);
        jCbox_modality.setEnabled(flag);
        jLabel10.setEnabled(flag);
        jLabel_days.setEnabled(flag);
        jLabel_ValueR0_7.setEnabled(flag);
        jTF_days.setEnabled(flag);
        jTF_StartHour.setEnabled(flag);
        jLabel_ValueR0_24.setEnabled(false);
        jLabel_start_at_h.setEnabled(false);
        jTF_Desc.setEnabled(flag);
        
        repaint();	
    }
    
    private void setGUI_hourRestrict(boolean flag)
    {
        jP_hourRestrict.setEnabled(flag);
        jPanel5.setEnabled(flag);
        jRB_15.setEnabled(flag);
        jRB_30.setEnabled(flag);
        jRB_1hour.setEnabled(flag);
        jPanel6.setEnabled(flag);
        jLabel14.setEnabled(flag);
        jLabel15.setEnabled(flag);
        jSlider_start.setEnabled(flag);
        jSlider_stop.setEnabled(flag);
        jL_valueSliderStart.setEnabled(flag);
        jL_valueSliderStop.setEnabled(flag);
        
        jPanel7.setEnabled(flag);
        jB_resetHour.setEnabled(flag);  
    }
    
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Carica la struttura dati contenente la lista degli utenti attualmente abilitati all'uso dello schedulatore.
     * N.B.: il primo utente della lista ï¿½ quello che possiede il massimo dei grant.
     * </font></font></p></pre>  
     */
    /*public void getUSER()
    {
        //******************************************
        // mi pappo la lista degli utenti
        //******************************************
        flagEDIT = false;
        try
        {
                boolean result2 = staticLib.MDM_JobActivatorRef.GetUsersList(userTemp);
                users = userTemp.value;

                //**************************************************
                //	Visualizzo gli utenti registrati.
                //**************************************************
                jB_apply.setEnabled(true);
        }
        catch(Exception e)
        {
                //System.out.println("ERRORE USERS: "+e);
                staticLib.optionPanel("Error receiving User List", "ERROR",javax.swing.JOptionPane.ERROR_MESSAGE);
                jB_apply.setEnabled(false);
        }
    }*/

    private void creaSTRUCT_USER()
    {
    	stu = new STRUCT_USER();
      	char user_app[];
        char pass_app[];
    	
    	//**************************************************************
    	//   Le limitazione della l'unghezza del vettore di caratteri 
    	//   per le due variabili User e Login, sono imposte dal MS
    	//**************************************************************
    	//user_app = ("STS").toCharArray();
        //pass_app = ("STS").toCharArray();
        user_app = (""+staticLib.codaNumber).toCharArray();
        pass_app = (""+staticLib.codaNumber).toCharArray();
    	
    	stu.user = new char[11];
    	stu.password = new char[11];
    	
    	for(int i=0;i<user_app.length;i++)
    		stu.user[i] = user_app[i];
    
    	for(int i=0;i<pass_app.length;i++)
    		stu.password[i] = pass_app[i];
    }
    
    private void getJobs()
    {
        job = new JOBSeqHolder();
        try
        {
           //System.out.println("I getAllJobs -");
            boolean result = staticLib.MDM_JobActivatorRef.getAllJobs(job,stu);                       
         //System.out.println("II getAllJobs - "+result);
            numJOB = job.value.length; 
            
          //System.out.println("-------- +++++++ stu.user="+new String(stu.user).trim());
          //System.out.println("-------- +++++++ Num Job="+job.value.length);
           for(int i=0;i<job.value.length;i++)
           {
           //System.out.println("JOBS: "+job.value[i].job_id+"   "+job.value[i].locked+"   "+job.value[i].data_start+"   "+job.value[i].data_end+"  "+job.value[i].modalita+"     "+new String(job.value[i].description).trim());
            }

            if(job.value.length>0)
            {
                ShowJobs(job.value);
                //jB_info_job.setEnabled(true);
                jB_edit_job.setEnabled(true);
                jB_del_job.setEnabled(true);
                jB_del_all_job.setEnabled(true);
                stu.job = job.value[0].job_id;
            }
            else
            {
                //jB_info_job.setEnabled(false);
                jB_edit_job.setEnabled(false);
                jB_del_job.setEnabled(false);
                jB_del_all_job.setEnabled(false);
                panelJob = null;
                jScrollPane1.setViewportView(panelNULL);
                repaint();	
                staticLib.optionPanel("No jobs submitted for this user","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception ex)
        {
                System.out.println("- C -Errore <JPJOBS>: "+ex);
                ex.printStackTrace();
        }
    }
    
    private void ShowJobs(STRUCT_JOB[] jobs)
    {
	//*************************************
	// CREO la griglia 
	//*************************************
	String[] righe = {" ","Dir","STATUS","START Date","STOP Date","MODALITY","DESCRIPTION"};
	int colonne = 22;
	int[] x = {21,50,120,129,130,160,170};
	int y = 470;
	java.awt.Color c = new java.awt.Color (230, 230, 255);
	
	javax.swing.JPanel[] p = new javax.swing.JPanel[righe.length];	
	panelJob = new javax.swing.JPanel();
        panelJob.setBackground(new java.awt.Color (230, 230, 230));
	panelJob.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,0,0));

	//////System.out.println("Num JOB: "+jobs.length);
	for(int i=0;i<righe.length;i++)
	{
		p[i]=new javax.swing.JPanel();                
		p[i].setLayout(new java.awt.GridLayout(colonne,1));
		p[i].setPreferredSize(new java.awt.Dimension(x[i], y));
		p[i].setMinimumSize(new java.awt.Dimension(x[i], y));
		p[i].setMaximumSize(new java.awt.Dimension(x[i], y));
		panelJob.add(p[i]);
	}
        //***********************************************
	// setto la scroll panel
	//***********************************************	
		
	//TITOLO
	for(int i=0;i<righe.length;i++)
		setLabelTotolo(righe[i],p[i]);
	
	jScrollPane1.setViewportView(panelJob);
	
	//************************************************
	//  RIEMPIO LA GRIGLIA
	//************************************************
	for(int i=0;i<righe.length;i++)
		p[i].setVisible(false);
	
	jrb = new javax.swing.JRadioButton[jobs.length];

	//System.out.println("ShowJobs jobs.length: "+jobs.length);
	for(int i=0;i<jobs.length;i++)
	{
		// ogni ciclo riempie una singola riga della tabella
                jrb[i] = new javax.swing.JRadioButton();
                jrb[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_blue.gif")));
                jrb[i].setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_green.gif")));
                jrb[i].setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_green.gif")));
                jrb[i].setFocusPainted(false);
                jrb[i].addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                    jrbActionPerformed(evt);
                                            }
                                        });
                jrb[i].setCursor(Cur_hand);
        	
		try
		{
                    jrb[i].setPreferredSize(new java.awt.Dimension(x[0], 10));
                    jrb[i].setMinimumSize(new java.awt.Dimension(x[0], 10));
                    jrb[i].setMaximumSize(new java.awt.Dimension(x[0], 10));
                    p[0].add(jrb[i]);
                    group1.add(jrb[i]);

                    setLabel(""+((jobs[i].job_id)+(1)),p[1],c,x[0]);
                    setLabel(staticLib.status[jobs[i].locked],p[2],c,x[1]);
                    
                    //System.out.println("ShowJobs START_DATA: "+jobs[i].data_start);
                    //System.out.println("ShowJobs STOP_DATA:  "+jobs[i].data_end);
                    setLabel(""+staticLib.formatData(Integer.toString(jobs[i].data_start)),p[3],c,x[2]);
                    setLabel(""+staticLib.formatData(Integer.toString(jobs[i].data_end)),p[4],c,x[3]);
                    setLabel(staticLib.modalita[jobs[i].modality],p[5],c,x[5]);
                    setLabel(new String(jobs[i].description).trim(),p[6],c,x[6]);

                }
                catch(Exception e)
                {
                        staticLib.optionPanel("Error receiving jobs", "ERROR",javax.swing.JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                }
	}
	jrb[0].setSelected(true);

	for(int i=0;i<righe.length;i++)
		p[i].setVisible(true);
	for(int i=0;i<righe.length;i++)
		p[i].validate();
		
	repaint();
	
	for(int i=0;i<righe.length;i++)
		p[i]=null;
		
    }
    
    private void setLabel(String str, javax.swing.JPanel p, java.awt.Color c,int x)	
    {
        javax.swing.JLabel jl = new javax.swing.JLabel();
    	jl.setBorder(new javax.swing.border.SoftBevelBorder(1));
    	jl.setText(str);
    	jl.setFont(staticLib.fontA9);
    	jl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	jl.setForeground(java.awt.Color.black);
    	jl.setBackground(c);
    	jl.setOpaque(true);
        //jl.setPreferredSize(new java.awt.Dimension(x, 40));
        //jl.setMinimumSize(new java.awt.Dimension(x, 40));
        //jl.setMaximumSize(new java.awt.Dimension(x, 40));
    	p.add(jl);
    }
    
    private void setLabelTotolo(String str, javax.swing.JPanel p)	
    {
        javax.swing.JLabel jl = new javax.swing.JLabel();
    	jl.setBorder(new javax.swing.border.SoftBevelBorder(0));
    	jl.setText(str);
    	jl.setFont(staticLib.fontA9);
    	jl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	jl.setForeground(java.awt.Color.black);
    	jl.setBackground(java.awt.Color.lightGray);
    	jl.setOpaque(true);
    	p.add(jl);
    }
    
    //******************************************
    //     Gestione eventi pulsanti Tab
    //******************************************
    private void jrbActionPerformed(java.awt.event.ActionEvent evt) 
    {
        for(int i=0; i<jrb.length; i++)
        {
            if(evt.getSource() == jrb[i])
            {
                jobSelected=i;
                break;
            }
        }
    }
    
    

    private void editJob()
    {
        job = new JOBSeqHolder();
        try
        {
           //System.out.println("P getAllJobs()");
            boolean result = staticLib.MDM_JobActivatorRef.getAllJobs(job,stu);
           //System.out.println("D getAllJobs()");

            int i = getCheckSet();
            jobEDIT = job.value[i];
            initValueJob(jobEDIT,flagEDIT);
            
            //System.out.println("JOBS: "+job.value[i].job_id+"   "+job.value[i].locked+"   "+job.value[i].data_start+"   "+job.value[i].data_end+"  "+job.value[i].modalita+"     "+new String(job.value[i].description).trim());

            /*for(int i=0;i<job.value.length;i++)
            {
                    if(job.value[i].job_id==getCheckSet())
                    {

                            jobEDIT=job.value[i];
                            initValueJob(jobEDIT,flagEDIT);
                            //System.out.println("JOBS: "+job.value[i].job_id+"   "+job.value[i].locked+"   "+job.value[i].data_start+"   "+job.value[i].data_end+"  "+job.value[i].modalita+"     "+new String(job.value[i].description).trim());

                            break;
                    }
            }*/

            // RIEMPI I CAMPI prendendo i valori dalla struttura jobEDIT
            //////System.out.println("RIEMPI CAMPI del JOB: "+getCheckSet());

        }catch(Exception ex)
        {
                ////System.out.println("JPJOBS: "+ex);
        }
    }
    
    private void initValueJob(STRUCT_JOB jobEDIT,boolean flag)
    {
        //System.out.println("SONO quaaaaaaa "+staticLib.formatData(""+jobEDIT.data_start));
        //System.out.println("SONO quaaaaaaa "+staticLib.formatData(""+jobEDIT.data_start));	

        staticLib.startDATE.setText(staticLib.formatData(""+jobEDIT.data_start));
        staticLib.endDATE.setText(staticLib.formatData(""+jobEDIT.data_end));

        staticLib.startDATE_ghost=""+jobEDIT.data_start;
        staticLib.endDATE_ghost=""+jobEDIT.data_end;

        jTF_days.setText(""+jobEDIT.qnt);	//days
        jTF_StartHour.setText(""+jobEDIT.hour);	//Dtart at
        jTF_Desc.setText((new String(jobEDIT.description)).trim());	 //descrizione
        //////System.out.println("EDIT"+jobEDIT.modality);
        switch(jobEDIT.modality)
        {
            case 3:
                        jCbox_modality.setSelectedIndex(0);
                        
                        jLabel_days.setEnabled(false);
                        jLabel_ValueR0_7.setEnabled(false);
                        jTF_days.setEnabled(false);
                        
                        jLabel_ValueR0_24.setEnabled(true);
                        jLabel_start_at_h.setEnabled(true);
                        jTF_StartHour.setEnabled(true);
                    break;
            case 2:
                        jCbox_modality.setSelectedIndex(1);
                        
                        jLabel_days.setEnabled(false);
                        jLabel_ValueR0_7.setEnabled(false);
                        jTF_days.setEnabled(false);
                        
                        jLabel_ValueR0_24.setEnabled(true);
                        jLabel_start_at_h.setEnabled(true);
                        jTF_StartHour.setEnabled(true);
                    break;
            case 1:
                        jCbox_modality.setSelectedIndex(2);
                        
                        jLabel_days.setEnabled(true);
                        jLabel_ValueR0_7.setEnabled(true);
                        jTF_days.setEnabled(true);
                        
                        jLabel_ValueR0_24.setEnabled(true);
                        jLabel_start_at_h.setEnabled(true);
                        jTF_StartHour.setEnabled(true);
                    break;
            case 7:
                    if(flag)
                    {
                        initValueJob(jobEDIT);
                        setGUI_hourRestrict(true);
                        jCbox_modality.setSelectedIndex(3);
                        
                        jLabel_days.setEnabled(false);
                        jLabel_ValueR0_7.setEnabled(false);
                        jTF_days.setEnabled(false);
                        
                        jLabel_ValueR0_24.setEnabled(false);
                        jLabel_start_at_h.setEnabled(false);
                        jTF_StartHour.setEnabled(false);
                    }
                    break;
        }	
        if(flag)
        { //Utente privileggiato

        }
    }

    private void initValueJob(STRUCT_JOB jobEDIT)
    {
        switch(jobEDIT.period)
        {
            case 0:
                    jRB_15.setSelected(true);
                    break;
            case 1:
                    jRB_30.setSelected(true);
                    break;
            case 2:
                    jRB_1hour.setSelected(true);
                    break;
        }

        jSlider_start.setValue((int)jobEDIT.start_hour);
        jSlider_stop.setValue((int)jobEDIT.end_hour);
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jP_center;
    public javax.swing.JTabbedPane Tabella;
    private javax.swing.JPanel jpSHOW;
    private javax.swing.JPanel jP_east;
    private javax.swing.JButton jB_refresh_Jobs;
    private javax.swing.JButton jB_edit_job;
    private javax.swing.JButton jB_del_job;
    private javax.swing.JButton jB_del_all_job;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpINS;
    private javax.swing.JPanel jP_options;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox jCbox_modality;
    private javax.swing.JLabel jLabel_days;
    private javax.swing.JLabel jLabel_start_at_h;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JTextField jTF_days;
    private javax.swing.JTextField jTF_StartHour;
    private javax.swing.JLabel jLabel_ValueR0_7;
    private javax.swing.JLabel jLabel_ValueR0_24;
    private javax.swing.JTextField jTF_Desc;
    private javax.swing.JPanel jP_hourRestrict;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRB_15;
    private javax.swing.JRadioButton jRB_30;
    private javax.swing.JRadioButton jRB_1hour;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JSlider jSlider_start;
    private javax.swing.JSlider jSlider_stop;
    private javax.swing.JLabel jL_valueSliderStart;
    private javax.swing.JLabel jL_valueSliderStop;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton jB_resetHour;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbIns;
    private javax.swing.JButton jbCanc;
    private javax.swing.JPanel jP_Reports;
    // End of variables declaration//GEN-END:variables

    private javax.swing.ButtonGroup group1 = new javax.swing.ButtonGroup();
    private JTextFieldFilter jtf,jtf1;
    private javax.swing.JRadioButton[] jrb;
    private getConfigFiltro configuration;
    private STRUCT_USER stu;
    private STRUCT_JOB[] jobs;
    private JOBSeqHolder job;
    private int numJOB=0;
    private javax.swing.JPanel panelJob;
    private int jobSelected = 0;
    private javax.swing.JPanel panelNULL;
    private JobUserSeqHolder userTemp = new JobUserSeqHolder(); 
    private STRUCT_USER[] users;
    
    private boolean flagEDIT = false;
    private java.awt.Cursor Cur_default  = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    private java.awt.Cursor Cur_wait     = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor Cur_hand     = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    
    private  net.etech.STRUCT_JOB jobPUT,jobEDIT;
    public MDM_JP_Calendar selectCalendar2 = null;    
}









