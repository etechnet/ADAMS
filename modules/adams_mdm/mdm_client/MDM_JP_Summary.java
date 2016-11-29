import java.awt.Cursor;
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
 * Classe di interfaccia grafica destinata creazione del sommario relativo alle selezioni effettuate dal'utente.
 * Questa classe inoltre contiene i metodi che premettono l'invio della richiesta di elaborazione al Master Server o allo schedulatore.
 * <p align="center">&nbsp;</p> 
 * @see STRUCT_PARAMS
 * @see BufferRestrizioni
 */

public class MDM_JP_Summary extends javax.swing.JPanel {

   
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe che inizializza il sommario delle selezioni.
     * </font></font></p></pre>  
     * @param ja riferimento all'Applet dalla quale ï¿½ stato lanciato il client.
     * @param configuration riferimento ad un oggetto di tipo <b>getConfigFiltro</b> contenente la configurazione globale.
     * @param br Oggetto di tipo BufferRestrizioni, contenente le restrizioni selezionate dall'utente.
     */	   
    public MDM_JP_Summary(javax.swing.JApplet JA, getConfigFiltro Configuration, BufferRestrizioni BR) {
        
        current_idRelations=-1;
        this.br = BR;
        this.ja = JA;
        this.configuration = Configuration;
        localAnalisi    = new Analisi(configuration.get_TipoAnalisi());
        localCounters   = new Counters(configuration.get_Counters());
        localReports    = new Reports(configuration.get_ReportsSchema());

        initComponents();
        //jbView.setEnabled(true); System.out.println("---------------->> DA TOGLIRE");
        
        jScroll_Selections.getVerticalScrollBar().setUnitIncrement(20);
        jScroll_Day.getVerticalScrollBar().setUnitIncrement(20);
        jScroll_Restrictions.getVerticalScrollBar().setUnitIncrement(20);
        
        jListIconReport = new JListIcon(ip);
        jListIconReport.setSelectionColor(new java.awt.Color(230,230,230));
        jListIconReport.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_listReport.setViewportView(jListIconReport); 
        
        jListIconReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListIconReportMouseClicked(evt);
            }
        });
        
        staticLib.summary_jListIconReport = jListIconReport;
        
        /*jp_centrali = new MDM_JP_Centrali(jbSchedule,jbSend,jB_pivot,jbReset,jProgProcess,jL_status,jbView,
                                            jCbx_listCriteria,jCkbox_increasing,jCkbox_decreasing,jCkbox_percValue,jCkbox_absValue);*/
        
        
        jp_centrali = new MDM_JP_Centrali(this);
        jp_centrali.setConfiguration(configuration);
        
        jP_Status_SW.add(jp_centrali,java.awt.BorderLayout.CENTER);
        
        
        
        // Sort CRITERIA
        jCbx_listCriteria.addItem(staticLib.sortBy[0]);
        try
        {
            java.util.Vector appoSort = localCounters.getDicitureSort(staticLib.TipoAnalisiTAG);
            
            if(appoSort.size()>0)
            {
                for(int i=0;i<appoSort.size();i++)
                {
                    jCbx_listCriteria.addItem((String)appoSort.elementAt(i));
                }
            }	
        }
        catch(Exception e)
        {
            //System.out.println("Non ci sono contatori associati");	
        }
        
            eventjCbx_listCriteria = (new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbx_listCriteriaActionPerformed(evt);
            }
        });
        
        
        // End sort Criteria
        
        //FONT
        jL_status.setFont(staticLib.fontA10);
        jL_process.setFont(staticLib.fontA10);
        jL_listReport.setFont(staticLib.fontA10);
        jListIconReport.setFont(staticLib.fontA9);
        jProgProcess.setFont(staticLib.fontA10);
        jCbx_listCriteria.setFont(staticLib.fontA10);
        jCkbox_increasing.setFont(staticLib.fontA10);
        jCkbox_decreasing.setFont(staticLib.fontA10);
        jCkbox_absValue.setFont(staticLib.fontA10);
        jCkbox_percValue.setFont(staticLib.fontA10);
        jLabel1.setFont(staticLib.fontA10);
        jLabel2.setFont(staticLib.fontA10);
        jLabel3.setFont(staticLib.fontA10);
        jLabel7.setFont(staticLib.fontA10);
        
        //Cursor
        jListIconReport.setCursor(Cur_hand);
        jbSend.setCursor(Cur_hand);
        jB_pivot.setCursor(Cur_hand);
        jbSchedule.setCursor(Cur_hand);
        jbView.setCursor(Cur_hand);
        jbReset.setCursor(Cur_hand);
        jCbx_listCriteria.setCursor(Cur_hand);
        jCkbox_increasing.setCursor(Cur_hand);
        jCkbox_decreasing.setCursor(Cur_hand);
        jCkbox_absValue.setCursor(Cur_hand);
        jCkbox_percValue.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScroll_Selections = new javax.swing.JScrollPane();
        jP_Selections = new javax.swing.JPanel();
        jScroll_Day = new javax.swing.JScrollPane();
        jP_SelectedDay = new javax.swing.JPanel();
        jScroll_Restrictions = new javax.swing.JScrollPane();
        jP_SelRestrictions = new javax.swing.JPanel();
        jP_request_status = new javax.swing.JPanel();
        jbSend = new javax.swing.JButton();
        jbSchedule = new javax.swing.JButton();
        jScroll_listReport = new javax.swing.JScrollPane();
        jbView = new javax.swing.JButton();
        jbReset = new javax.swing.JButton();
        jL_listReport = new javax.swing.JLabel();
        jP_Status_SW = new javax.swing.JPanel();
        jP_status_TOT = new javax.swing.JPanel();
        jL_process = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jProgProcess = new javax.swing.JProgressBar();
        jL_status = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jB_pivot = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jCbx_listCriteria = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCkbox_increasing = new javax.swing.JCheckBox();
        jCkbox_decreasing = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCkbox_absValue = new javax.swing.JCheckBox();
        jCkbox_percValue = new javax.swing.JCheckBox();
        
        
        setLayout(null);
        
        setBackground(new java.awt.Color(230, 230, 230));
        jScroll_Selections.setBackground(new java.awt.Color(230, 230, 230));
        jScroll_Selections.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selected Operations", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 11)));
        jP_Selections.setLayout(new javax.swing.BoxLayout(jP_Selections, javax.swing.BoxLayout.Y_AXIS));
        
        jP_Selections.setBackground(java.awt.Color.white);
        jP_Selections.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jScroll_Selections.setViewportView(jP_Selections);
        
        add(jScroll_Selections);
        jScroll_Selections.setBounds(0, 0, 390, 330);
        
        jScroll_Day.setBackground(new java.awt.Color(230, 230, 230));
        jScroll_Day.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Selected Days", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 11)));
        jP_SelectedDay.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        
        jP_SelectedDay.setBackground(java.awt.Color.white);
        jP_SelectedDay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jScroll_Day.setViewportView(jP_SelectedDay);
        
        add(jScroll_Day);
        jScroll_Day.setBounds(390, 0, 157, 330);
        
        jScroll_Restrictions.setBackground(new java.awt.Color(230, 230, 230));
        jScroll_Restrictions.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Selected Restrictions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 11)));
        jP_SelRestrictions.setLayout(new javax.swing.BoxLayout(jP_SelRestrictions, javax.swing.BoxLayout.Y_AXIS));
        
        jP_SelRestrictions.setBackground(java.awt.Color.white);
        jP_SelRestrictions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jScroll_Restrictions.setViewportView(jP_SelRestrictions);
        
        add(jScroll_Restrictions);
        jScroll_Restrictions.setBounds(548, 135, 404, 195);
        
        jP_request_status.setLayout(null);
        
        jP_request_status.setBackground(new java.awt.Color(230, 230, 230));
        jP_request_status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbSend.setBackground(new java.awt.Color(230, 230, 230));
        jbSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request.jpg")));
        jbSend.setToolTipText("Report Request");
        jbSend.setBorderPainted(false);
        jbSend.setContentAreaFilled(false);
        jbSend.setFocusPainted(false);
        jbSend.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jbSend.setMaximumSize(new java.awt.Dimension(110, 26));
        jbSend.setMinimumSize(new java.awt.Dimension(110, 26));
        jbSend.setPreferredSize(new java.awt.Dimension(110, 26));
        jbSend.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_press.jpg")));
        jbSend.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_over.jpg")));
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });
        
        jP_request_status.add(jbSend);
        jbSend.setBounds(10, 10, 110, 22);
        
        jbSchedule.setBackground(new java.awt.Color(230, 230, 230));
        jbSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sched_request.jpg")));
        jbSchedule.setToolTipText(" Schedule request ");
        jbSchedule.setBorderPainted(false);
        jbSchedule.setContentAreaFilled(false);
        jbSchedule.setFocusPainted(false);
        jbSchedule.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jbSchedule.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sched_request_press.jpg")));
        jbSchedule.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sched_request_over.jpg")));
        jbSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbScheduleActionPerformed(evt);
            }
        });
        
        jP_request_status.add(jbSchedule);
        jbSchedule.setBounds(420, 10, 130, 22);
        
        jP_request_status.add(jScroll_listReport);
        jScroll_listReport.setBounds(550, 40, 390, 180);
        
        jbView.setBackground(new java.awt.Color(230, 230, 230));
        jbView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view_report.jpg")));
        jbView.setToolTipText(" View report in Web Browser ");
        jbView.setBorderPainted(false);
        jbView.setContentAreaFilled(false);
        jbView.setFocusPainted(false);
        jbView.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jbView.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view_report_press.jpg")));
        jbView.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view_report_over.jpg")));
        jbView.setEnabled(false);
        jbView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbViewActionPerformed(evt);
            }
        });
        
        jP_request_status.add(jbView);
        jbView.setBounds(550, 10, 100, 22);
        
        jbReset.setBackground(new java.awt.Color(230, 230, 230));
        jbReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/purge_report.jpg")));
        jbReset.setBorderPainted(false);
        jbReset.setContentAreaFilled(false);
        jbReset.setFocusPainted(false);
        jbReset.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jbReset.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/purge_report_press.jpg")));
        jbReset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/purge_report_over.jpg")));
        jbReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbResetActionPerformed(evt);
            }
        });
        
        jP_request_status.add(jbReset);
        jbReset.setBounds(840, 10, 100, 22);
        
        jL_listReport.setBackground(new java.awt.Color(230, 230, 230));
        jL_listReport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_listReport.setText("List Report");
        jP_request_status.add(jL_listReport);
        jL_listReport.setBounds(660, 10, 140, 20);
        
        jP_Status_SW.setLayout(new java.awt.BorderLayout());
        
        jP_Status_SW.setBackground(new java.awt.Color(230, 230, 230));
        jP_Status_SW.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jP_request_status.add(jP_Status_SW);
        jP_Status_SW.setBounds(85, 40, 460, 150);
        
        jP_status_TOT.setLayout(new java.awt.BorderLayout());
        
        jP_status_TOT.setBackground(new java.awt.Color(230, 230, 230));
        jP_status_TOT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jL_process.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_process.setText("Process");
        jL_process.setMaximumSize(new java.awt.Dimension(65, 18));
        jL_process.setMinimumSize(new java.awt.Dimension(65, 18));
        jL_process.setPreferredSize(new java.awt.Dimension(65, 18));
        jP_status_TOT.add(jL_process, java.awt.BorderLayout.SOUTH);
        
        jPanel8.setLayout(new java.awt.CardLayout(10, 5));
        
        jPanel8.setBackground(new java.awt.Color(230, 230, 230));
        jProgProcess.setBackground(new java.awt.Color(230, 230, 230));
        jProgProcess.setForeground(java.awt.Color.blue);
        jProgProcess.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jProgProcess.setToolTipText("");
        jProgProcess.setStringPainted(true);
        jPanel8.add(jProgProcess, "jProgressBar2");
        
        jP_status_TOT.add(jPanel8, java.awt.BorderLayout.CENTER);
        
        jP_request_status.add(jP_status_TOT);
        jP_status_TOT.setBounds(10, 40, 70, 180);
        
        jL_status.setText(" Status :");
        jL_status.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jP_request_status.add(jL_status);
        jL_status.setBounds(85, 195, 460, 25);
        
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Send Request and Status");
        jP_request_status.add(jLabel7);
        jLabel7.setBounds(232, 10, 185, 20);
        
        jB_pivot.setBackground(new java.awt.Color(230, 230, 230));
        jB_pivot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot.jpg")));
        jB_pivot.setToolTipText("Pivot Request");
        jB_pivot.setBorderPainted(false);
        jB_pivot.setContentAreaFilled(false);
        jB_pivot.setFocusPainted(false);
        jB_pivot.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_pivot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_press.jpg")));
        jB_pivot.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_over.jpg")));
        jB_pivot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_pivotActionPerformed(evt);
            }
        });
        
        jP_request_status.add(jB_pivot);
        jB_pivot.setBounds(125, 10, 105, 22);
        
        add(jP_request_status);
        jP_request_status.setBounds(0, 335, 950, 230);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true), " Metrics: Sort & Format", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 11)));
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCbx_listCriteria.setBackground(new java.awt.Color(230, 230, 230));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(1, 5, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        jPanel4.add(jCbx_listCriteria, gridBagConstraints2);
        
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Sort Criteria");
        jPanel5.add(jLabel2);
        
        jCkbox_increasing.setBackground(new java.awt.Color(230, 230, 230));
        jCkbox_increasing.setSelected(true);
        jCkbox_increasing.setText("Increasing");
        jCkbox_increasing.setToolTipText("Order Criteria : Increasing");
        buttonGroup1.add(jCkbox_increasing);
        jCkbox_increasing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCkbox_increasing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCkbox_increasing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCkbox_increasing.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbox_increasing.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_increasing.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCkbox_increasing.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_increasing.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCkbox_increasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCkbox_incr_decActionPerformed(evt);
            }
        });
        
        jPanel5.add(jCkbox_increasing);
        
        jCkbox_decreasing.setBackground(new java.awt.Color(230, 230, 230));
        jCkbox_decreasing.setText("Decreasing");
        jCkbox_decreasing.setToolTipText("Order Criteria : Decreasing");
        buttonGroup1.add(jCkbox_decreasing);
        jCkbox_decreasing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCkbox_decreasing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCkbox_decreasing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCkbox_decreasing.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbox_decreasing.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_decreasing.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCkbox_decreasing.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_decreasing.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCkbox_decreasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCkbox_incr_decActionPerformed(evt);
            }
        });
        
        jPanel5.add(jCkbox_decreasing);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jPanel5, gridBagConstraints2);
        
        jLabel1.setText("Global Order ");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(0, 1, 3, 1);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Format Value");
        jPanel3.add(jLabel3);
        
        jCkbox_absValue.setBackground(new java.awt.Color(230, 230, 230));
        jCkbox_absValue.setText("Abs Value");
        jCkbox_absValue.setToolTipText("Format Value: Absolute");
        buttonGroup2.add(jCkbox_absValue);
        jCkbox_absValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCkbox_absValue.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCkbox_absValue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCkbox_absValue.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbox_absValue.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_absValue.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCkbox_absValue.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_absValue.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCkbox_absValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCkbox_percValueActionPerformed(evt);
            }
        });
        
        jPanel3.add(jCkbox_absValue);
        
        jCkbox_percValue.setBackground(new java.awt.Color(230, 230, 230));
        jCkbox_percValue.setSelected(true);
        jCkbox_percValue.setText("Perc. Value");
        jCkbox_percValue.setToolTipText("Format Value: Percent");
        buttonGroup2.add(jCkbox_percValue);
        jCkbox_percValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCkbox_percValue.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCkbox_percValue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCkbox_percValue.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbox_percValue.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_percValue.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCkbox_percValue.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCkbox_percValue.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCkbox_percValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCkbox_percValueActionPerformed(evt);
            }
        });
        
        jPanel3.add(jCkbox_percValue);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints1);
        
        add(jPanel1);
        jPanel1.setBounds(549, 0, 401, 130);
        
    }//GEN-END:initComponents

    public javax.swing.JButton get_jbSend()
     {
        return this.jbSend;
    }
    public javax.swing.JButton get_jbSchedule()
    {
        return this.jbSchedule;
    }
    public javax.swing.JButton get_jB_pivot()
    {
        return this.jB_pivot;
    } 
    public javax.swing.JButton get_jbReset()
    {
        return this.jbReset;
    } 
    public javax.swing.JButton get_jbView()
    {
        return this.jbView;
    } 
    
    public javax.swing.JComboBox get_jCbx_listCriteria()
    {
        return this.jCbx_listCriteria;
    } 
    public javax.swing.JCheckBox get_jCkbox_increasing()
    {
        return this.jCkbox_increasing;
    } 
    public javax.swing.JCheckBox get_jCkbox_decreasing()
    {
        return this.jCkbox_decreasing;
    } 
    public javax.swing.JCheckBox get_jCkbox_percValue()
    {
        return this.jCkbox_percValue;
    } 
    public javax.swing.JCheckBox get_jCkbox_absValue()
    {
        return this.jCkbox_absValue;
    } 
    
    public javax.swing.JProgressBar get_jProgProcess()
    {
        return this.jProgProcess;
    } 
    
    public javax.swing.JLabel get_jL_status()
    {
        return this.jL_status;
    } 

    public void setCriteriaEnabled(boolean f)
    {        
        jCbx_listCriteria.removeActionListener(eventjCbx_listCriteria);
        
        jCbx_listCriteria.setEnabled(f);
        jCkbox_increasing.setEnabled(f);
        jCkbox_decreasing.setEnabled(f);
        jCkbox_percValue.setEnabled(f);
        jCkbox_absValue.setEnabled(f);
        
        jCbx_listCriteria.addActionListener(eventjCbx_listCriteria);
    }
    
    public void refreshOrderCriteria()
    {
        jCbx_listCriteria.removeActionListener(eventjCbx_listCriteria);
        
        jCbx_listCriteria.removeAllItems();
        
        
        jCbx_listCriteria.addItem(staticLib.sortBy[0]);
        try
        {
            java.util.Vector appoSort = localCounters.getDicitureSort(staticLib.TipoAnalisiTAG);
            
            if(appoSort.size()>0)
            {
                for(int i=0;i<appoSort.size();i++)
                {
                    jCbx_listCriteria.addItem((String)appoSort.elementAt(i));
                }
            }	
        }
        catch(Exception e)
        {
            //System.out.println("Non ci sono contatori associati");	
        }
        resetSelectSort();        
        
        jCbx_listCriteria.addActionListener(eventjCbx_listCriteria);
    }
    
    private void jCbx_listCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbx_listCriteriaActionPerformed
        //System.out.println("jCbx_listCriteriaActionPerformed");
        int indexSel = jCbx_listCriteria.getSelectedIndex();
        if(indexSel == 0)
        {
            staticLib.struc_paramsCURRENT.ElementToSort = 0;
            staticLib.struc_paramsCURRENT.FlagSort = false;

            resetSelectSort(); 
        }
        else
        {
            staticLib.struc_paramsCURRENT.FlagSort = true;
            staticLib.struc_paramsCURRENT.ElementToSort = localCounters.getIdSort(staticLib.TipoAnalisiTAG,(String)jCbx_listCriteria.getSelectedItem());//jListSortBy.getSelectedIndex();

            jCkbox_increasing.setEnabled(true);
            jCkbox_decreasing.setEnabled(true);

            //System.out.println("TAG AnalisiID: "+staticLib.TipoAnalisiTAG);
            //System.out.println("SORT ID: "+staticLib.struc_paramsCURRENT.ElementToSort);
            //System.out.println("TAG: "+localCounters.getDicituraSort(staticLib.TipoAnalisiTAG,staticLib.struc_paramsCURRENT.ElementToSort-1));
        }
        //staticLib.sortPanel1.setSelectSort(staticLib.struc_paramsCURRENT.FlagSort,staticLib.struc_paramsCURRENT.ElementToSort,staticLib.struc_paramsCURRENT.Ascendent,indexSel);
        refreshCellSummary_Order_Cr();
    }//GEN-LAST:event_jCbx_listCriteriaActionPerformed

    public void resetSelectSort()
    {
        jCkbox_increasing.setSelected(true);
        //jCkbox_percValue.setSelected(true);
        
        jCkbox_increasing.setEnabled(false);
        jCkbox_decreasing.setEnabled(false);
        
        //DEFAULT VALUE;
        //System.out.println("INDICE:"+jListSortBy.getSelectedIndex()); 
        staticLib.struc_paramsCURRENT.ElementToSort = 0;
        //staticLib.struc_paramsCURRENT.IsPercent = true;
        staticLib.struc_paramsCURRENT.Ascendent = true;
        staticLib.struc_paramsCURRENT.FlagSort = false;
    } 
    
    
    private void jCkbox_incr_decActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCkbox_incr_decActionPerformed
       // System.out.println("jCkbox_incr_decActionPerformed");
        if(evt.getSource()==jCkbox_increasing)
            staticLib.struc_paramsCURRENT.Ascendent=true;
        else 
            staticLib.struc_paramsCURRENT.Ascendent=false;
        
        int indexSel = jCbx_listCriteria.getSelectedIndex();
        //staticLib.sortPanel1.setSelectSort(staticLib.struc_paramsCURRENT.FlagSort,staticLib.struc_paramsCURRENT.ElementToSort,staticLib.struc_paramsCURRENT.Ascendent,indexSel);
        refreshCellSummary_Order_Cr();
    }//GEN-LAST:event_jCkbox_incr_decActionPerformed

    private void jCkbox_percValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCkbox_percValueActionPerformed
        //System.out.println("jCkbox_percValueActionPerformed");
        
        if(evt.getSource()==jCkbox_percValue)
            staticLib.struc_paramsCURRENT.IsPercent=true;
        else
            staticLib.struc_paramsCURRENT.IsPercent=false;            
        
        refreshCellSummary_Order_Cr();
    }//GEN-LAST:event_jCkbox_percValueActionPerformed

    private void refreshCellSummary_Order_Cr()
    {    
        if(CellSummary_Order_Cr != null)
        {
            CellSummary_Order_Cr.resetValueCell();

            if(staticLib.struc_paramsCURRENT.FlagSort)
            {
                //CellSummary_Order_Cr.setValueCell("by "+localCounters.getDicituraSort(staticLib.TipoAnalisiTAG,staticLib.struc_paramsCURRENT.ElementToSort-1),"item_order_on.png");
                CellSummary_Order_Cr.setValueCell("by "+localCounters.getDicSort(staticLib.TipoAnalisiTAG,staticLib.struc_paramsCURRENT.ElementToSort),"item_order_on.png");


                if(staticLib.struc_paramsCURRENT.Ascendent)
                    CellSummary_Order_Cr.setValueCell("Sort Crit. Increasing","check_on_mini.gif");
                else
                    CellSummary_Order_Cr.setValueCell("Sort Crit. Decreasing","check_on_mini.gif");

            }
            else
            {
                CellSummary_Order_Cr.setValueCell("Deactivated","check_off_mini.gif");
                CellSummary_Order_Cr.setValueCell("Sort Criteria none","check_off_mini.gif");           
            }

            
            if(staticLib.struc_paramsCURRENT.IsPercent)
            {
                CellSummary_Order_Cr.setValueCell("Format Percent value","check_on_mini.gif");
                staticLib.AbsoluteValue = new String("false");
                //if(staticLib.struc_paramsCURRENT.FlagSort)
                  //  staticLib.optionPanel("With 'Global Order Criteria' selected it's recommended the 'abs value' format (For Pivot Request).", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }
            else
            {
                CellSummary_Order_Cr.setValueCell("Format Absolute value","check_on_mini.gif");
                staticLib.AbsoluteValue = new String("true");
            }
            
            CellSummary_Order_Cr.validate();
            CellSummary_Order_Cr.repaint();
            
        }
    }
    
    private void jB_pivotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pivotActionPerformed
        
        //int indexSel = jListIconReport.getSelectedIndex();
        //String[] URL_and_RELNAME = jp_centrali.getURL(indexSel);  
        //String Def_Url  = URL_and_RELNAME[0];
            
        
        if(BLOCK_ANALISI == true)
        {   
            staticLib.optionPanel(msg_BLOCK_ANALISI,"Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        //System.out.println("Chiamo con Flag 1 ---- PIVOT ---");
        if(staticLib.validateSTRUCT_PARAMS(staticLib.struc_paramsCURRENT,new TrafficElement(configuration.get_Traffic_Elements()),br)==1)//richiesta valida
        {
            if(!jp_centrali.isAlive())
            {                
                //System.out.println("Il Report selezionato Ã¨ fi tipo --> "+staticLib.selectReport1.getSelectedReportExcel());
                //Integer typeReport=(Integer)staticLib.hTableReport.get((String)Def_Url);
                //System.out.println(Def_Url+" typeReport="+typeReport.intValue());
                
                if(staticLib.selectReport1.getSelectedReportExcel() == MDM_exportReport.REPORT_TO_CSV) //true (1=CSV, 2=EXCEL) //1
                {
                    staticLib.optionPanel("It's no possible to execute Pivot for the Report Types selected.","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                //staticLib.selectReport1.getSelectedReportExcel()
                if(staticLib.selectReport1.getSelectedReportExcel() == MDM_exportReport.REPORT_TO_EXCEL) //true (1=CSV, 2=EXCEL)
                {
                    staticLib.optionPanel("It's no possible to execute Pivot for the Report Types selected.","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if(staticLib.selectReport1.getSelectedReport_UsePlugin() == true)
                {
                    staticLib.optionPanel("It's no possible to execute Pivot for the Report Types selected.","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                
                if(staticLib.struc_paramsCURRENT.FlagSort)
                {
                    if(staticLib.struc_paramsCURRENT.IsPercent)
                    {                        
                        if(staticLib.struc_paramsCURRENT.FlagSort)
                        {
                            int confimed = staticLib.confirmPanel("With 'Global Order Criteria' selected it's recommended the 'abs value' format. Confirm Request?","Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                            if(confimed != 0)
                            {
                                return;
                            }
                        }
                    }
                   
                }
                
                
                //stampaStruc();
                staticLib.struc_paramsCURRENT.RelationDirection = 0; //nel pivot ï¿½ sempre Direct
                //System.out.println("dopo relDirection "+staticLib.relDirection[staticLib.struc_paramsCURRENT.RelationDirection]);
                
                jp_centrali.startElaboration(1);
                //staticLib.flagPass = false;
                
                jB_pivot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_request.jpg")));
                jB_pivot.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_request_over.jpg")));
                jB_pivot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_request_press.jpg")));
                jB_pivot.setToolTipText(" Stop Pivot Request ");
                jbSend.setEnabled(false);                
            }
            else 
            {
                int risp1 = staticLib.confirmPanel("Confirm request ?","Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                if(risp1==0)
                { 
                    jB_pivot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot.jpg")));
                    jB_pivot.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_over.jpg")));
                    jB_pivot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_press.jpg")));
                    jB_pivot.setToolTipText(" Pivot Request ");
                    jbSend.setEnabled(true); 
                    jp_centrali.stopElaboration(); 
                }
            }
        }
        //System.out.println("Fine Flag 1 ---- PIVOT ---");

    }//GEN-LAST:event_jB_pivotActionPerformed

    private void jbScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbScheduleActionPerformed
        
        if(BLOCK_ANALISI == true)
        {   
            staticLib.optionPanel(msg_BLOCK_ANALISI,"Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (staticLib.struc_paramsCURRENT.Relation == ID_FREE_RELATION )
        {
            staticLib.optionPanel("No Schedule Request for Free Format selection.","Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        //System.out.println("staticLib.struc_paramsCURRENT: " +(new String(staticLib.struc_paramsCURRENT.Name)).trim());   
        
        staticLib.struc_paramsCURRENT.ElaborationData = new DATE_INFO[1];
        staticLib.struc_paramsCURRENT.ElaborationData[0] = new DATE_INFO(("19760814"+"\0").toCharArray());

        if(staticLib.validateSTRUCT_PARAMS(staticLib.struc_paramsCURRENT,new TrafficElement(configuration.get_Traffic_Elements()),br) == 1)
        {            
            
            if(staticLib.richiestaPronta)
            {
                    staticLib.optionPanel("Delete previous Job request.", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                staticLib.richiestaPronta = true;
                staticLib.l = new javax.swing.JLabel("WARNING: sheduled Job not yet submitted ");
                staticLib.Tabella.setSelectedIndex(4);
                staticLib.selectJobs1.Tabella.setSelectedIndex(1);
                staticLib.selectJobs1.validateOra();
                staticLib.selectJobs1.selectCalendar2.resetCalendar();
                //staticLib.selectJobs1.utenteAbilitato(); //nello
                staticLib.selectJobs1.Tabella.setEnabledAt(1,true);                
            }
        }
        
        if(staticLib.struc_paramsCURRENT.DataType==0)
             staticLib.mainFRAME.ridisegnaCalendario(false);
        else  if(staticLib.struc_paramsCURRENT.DataType==1)
             staticLib.mainFRAME.ridisegnaCalendario(true);
        
        
        creaSummary(new String[0]);
        jP_Validdate.repaint();
        
    }//GEN-LAST:event_jbScheduleActionPerformed

    private void jListIconReportMouseClicked(java.awt.event.MouseEvent evt) 
    {
        //Tasto destro resetta le selezioni
        if( (evt.getModifiers() & java.awt.event.InputEvent.BUTTON3_MASK)!=0 )
            jListIconReport.clearSelection(); 
        else if(((evt.getModifiers() & java.awt.event.InputEvent.BUTTON1_MASK)!=0) && (evt.getClickCount()>1))
        {
            viewReport();
        }
    }
   
    private void jbResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbResetActionPerformed

        int index = jListIconReport.getSelectedIndex();
        //System.out.println("index report = "+index);
        try
        {
            if(index == -1)
            {
                jp_centrali.resetVettURL();
                jListIconReport.removeAll();
                
                /*System.out.println("Hashtable Size :"+staticLib.hashT_StructParams.size());
                System.out.println("Clear Hashtable");
                System.out.println("Hashtable Size :"+staticLib.hashT_StructParams.size());*/
                if(staticLib.hashT_StructParams.size()>0)
                {
                	staticLib.hashT_StructParams.clear();      
                }          
            }
            else
            {
                

                //System.out.println("Hashtable Size :"+staticLib.hashT_StructParams.size());
                if( (staticLib.hashT_StructParams!=null) && (jListIconReport!=null) )
                {
	                if(staticLib.hashT_StructParams.size()>0)
	                {
	                	//System.out.println("---> Hashtable Size :"+staticLib.hashT_StructParams.size());
                		//System.out.println("---> jListIconReport.getSelectedValue() :"+(String)jListIconReport.getSelectedValue());
	                	STRUCT_PARAMS removed_structParam = staticLib.hashT_StructParams.remove( ((String)jListIconReport.getSelectedValue()) );
	                	//System.out.println("elimanata dalla Hashtable: "+new String(removed_structParam.Name).trim());
	                	//System.out.println("Hashtable Size :"+staticLib.hashT_StructParams.size());
	                }
	        }
	        
	        jListIconReport.remElement(index);
                jp_centrali.resetVettURL_pos(index); 
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); // NELLO le eccezioni ogni tanto stampale... altrimenti nse capisce il problema
            jp_centrali.resetVettURL();
            jListIconReport.removeAll();
        }
        
        if(jListIconReport.getItemCount() == 0)
        {
            jp_centrali.resetBar();
            jbView.setEnabled(false);
        }
        
        jListIconReport.clearSelection(); 
        jListIconReport.repaint();
        jScroll_listReport.validate();
        jScroll_listReport.repaint();
    }//GEN-LAST:event_jbResetActionPerformed
    
    public void viewReport()
    {
        this.setCursor(Cur_wait);
        if(jListIconReport.getSelectedIndex() >= 0) //ATTIVARE
        {
            int indexSel = jListIconReport.getSelectedIndex();
            
            String[] URL_and_RELNAME = jp_centrali.getURL(indexSel);
            
            String Def_Url  = URL_and_RELNAME[0];
            String REL_NAME = URL_and_RELNAME[1];            
            
            /*int TypeCounter = 0;
            try 
            {
                TypeCounter = new Integer(URL_and_RELNAME[2]).intValue();
            }
            catch (Exception e)
            {
                System.out.println("errore TypeCounter MDM_JP_Summary.java");
            }*/
            
            jListIconReport.setIconAt(indexSel,"item_report_on.png","item_report_on.png");
            
            Integer typeReport=(Integer)staticLib.hTableReport.get((String)Def_Url);
            
            //System.out.println("staticLib.selectReport1.getSelectedReportExcel()="+staticLib.selectReport1.getSelectedReportExcel());
            //System.out.println(Def_Url+"typeReport="+typeReport.intValue());
            
            if(typeReport.intValue() == MDM_exportReport.REPORT_TO_EXCEL) //TYPE REPORT=EXCEL(2) // per TEST poi spostare a 2
            {
            	/* CHIAMARE METODO getReportExcel */
            	/*System.out.println("DEVO CHIAMARE UN METODO ADOC per il report EXCEL");
            	System.out.println("defaultURL="+defaultURL);
            	System.out.println("Def_Url="+Def_Url);*/
            	
            	EXPORT_ROW[] exportData = staticLib.CORBA.getExportReport(Def_Url);  
            	if(exportData==null)
            	{
            		JD_Message op = new JD_Message(null,true,"The report is empty.","Info",JD_Message.INFO);
            	}else
            	{
            		MDM_exportReport exportReport = new MDM_exportReport("EXCEL_MDM_"+Def_Url,this.ja,exportData,typeReport.intValue());
            	}
            	
            	//MDM_exportReport exportReport = new MDM_exportReport("EXCEL_MDM_"+Def_Url,this.ja,exportData,typeReport.intValue());
            }
            else
            {
	            if(Def_Url.indexOf("PIVOT") == -1)
	            {            
	                try
	                {
	                    java.net.URL wwwEli = new java.net.URL(defaultURL+Def_Url);
	                    java.applet.AppletContext myContext = ja.getAppletContext();
	                    myContext.showDocument( wwwEli, "_blank");
	                    //HTMLViewer appo=new HTMLViewer(defaultURL+Def_Url);
	                }
	                catch (java.net.MalformedURLException canNotHappen ) 
	                {
	                    System.out.println("Error canNotHappen");
	                }
	            }
	            else
	            { 
	                /*System.out.println("E' un PIVOT......................");                
	                System.out.println(" Def_Url "+Def_Url);
	                System.out.println(" REL_NAME "+REL_NAME); */   
	                                
	                if(initPivot != null)
	                {                    
	                    initPivot.destroyPivot();
	                    initPivot.setVisible(false);
	                    initPivot.dispose();
	                    initPivot = null;
	                }
	                
	                /*boolean Report_AbsoluteValue = false;
	                
	                if(URL_and_RELNAME[2].equals("true"))
	                    Report_AbsoluteValue = true;  */              
	                                
                        String nameReportKey = (String)jListIconReport.getSelectedValue();                        
	                initPivot = new MDM_JD_InitPivot(staticLib.mainFRAME,Def_Url,REL_NAME,this.ja,nameReportKey);
                        initPivot.setConfiguration(configuration);                        
	                //System.out.println("Def_Url Pivot "+Def_Url);   
                        
                        
	             }
	     }       
        }        
        this.setCursor(Cur_default);
    }
    
    private void jbViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbViewActionPerformed
        viewReport();
    }//GEN-LAST:event_jbViewActionPerformed

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed
       
        if(BLOCK_ANALISI == true)
        {   
            staticLib.optionPanel(msg_BLOCK_ANALISI,"Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(staticLib.validateSTRUCT_PARAMS(staticLib.struc_paramsCURRENT,new TrafficElement(configuration.get_Traffic_Elements()),br)==1)//richiesta valida
        {
            if(!jp_centrali.isAlive())
            {
                //stampaStruc(); 
                staticLib.struc_paramsCURRENT.RelationDirection = Local_DirezioneRelazione; //risabilisco la direzione selezionata (nel pivot ï¿½ sempre Direct).
                //System.out.println("SEND DirezioneRelazione -->"+staticLib.relDirection[staticLib.struc_paramsCURRENT.RelationDirection]);
                
                int flagElaboration=0;
                
                switch((int)staticLib.selectReport1.getSelectedReportExcel())
                {
			case MDM_exportReport.REPORT_TO_HTML: // HTML
				{
					flagElaboration=2;
				}break;
			case MDM_exportReport.REPORT_TO_CSV: // CSV
				{
					flagElaboration=2;
				}break;
			case MDM_exportReport.REPORT_TO_PDF: // PDF
				{
					flagElaboration=2;
				}break;
			case MDM_exportReport.REPORT_TO_EXCEL: // EXCEL
				{
					flagElaboration=2;
				}break;
                }
                jp_centrali.startElaboration(flagElaboration);
                //staticLib.flagPass = false;
                
                jbSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_request.jpg")));
                jbSend.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_request_over.jpg")));
                jbSend.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_request_press.jpg")));
                jbSend.setToolTipText(" Stop Request ");
                jB_pivot.setEnabled(false);
            }
            else 
            {
                int risp1 = staticLib.confirmPanel("Confirm request ?","Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                if(risp1==0)
                { 
                    jbSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request.jpg")));
                    jbSend.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_over.jpg")));
                    jbSend.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_press.jpg")));
                    jbSend.setToolTipText(" Send Request ");
                    jB_pivot.setEnabled(true);
                    jp_centrali.stopElaboration(); 
                }
            }
        }
    }//GEN-LAST:event_jbSendActionPerformed

    private void stampaStruc()
    {
            System.out.println("NAME Config: "+(new String(staticLib.struc_paramsCURRENT.Name)).trim());
            System.out.println("ID_REL: "+staticLib.struc_paramsCURRENT.Relation);
            System.out.println("# Rest: "+staticLib.struc_paramsCURRENT.Filters.length);
            REST_INFO r;
            for(int i=0;i<staticLib.struc_paramsCURRENT.Filters.length;i++)
            {
                    r=staticLib.struc_paramsCURRENT.Filters[i];
                    System.out.println("ID_TrafficElement:"+r.Element);
                    System.out.println(" Operatore:"+r.Operator);
                    System.out.println("  Priority:"+r.Priority);
                    int j=0;
                    while(r.Value[j]!=-1)
                    {
                            System.out.println("Value["+j+"]"+r.Value[j]);
                            j++;
                    }
                    System.out.println("Value["+j+"]"+r.Value[j]);		
            }
    }
    
    /**
     *Questo metodo abilita/disabilita il pulsante jbSchedule "Schedule Request".
     *@param flag Se true abilita il pusante altrimenti lo disabilitï¿½.
     */
    public void setEnabled_jbSchedule(boolean flag)
    {
        jbSchedule.setEnabled(flag);
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ridisegna e convalida il sommario data la struttura passatagli.
     * </font></font></p></pre>  
     * @param strSelectedDays riferimento ad un oggetto di tipo <b>String[]</b>.
     */
    public void validateSummary(String[] strSelectedDays)
    {  
        
        if((staticLib.oldIDREL!=staticLib.struc_paramsCURRENT.Relation)&&(staticLib.oldIDREL!=-1))
        {
            //System.out.println("Clear All");
            //System.out.println("Changed ID Rel="+staticLib.struc_paramsCURRENT.Relation);
            br.celarAll();
        }
        staticLib.oldIDREL=staticLib.struc_paramsCURRENT.Relation;
        
        creaSummary(strSelectedDays);
        
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo mediante il quale viene generato il sommario data la struttura passatagli in ingresso.
     * </font></font></p></pre>  
     * @param strSelectedDays riferimento ad un oggetto di tipo <b>String[]</b>.
     */
    private void creaSummary(String[] strSelectedDays)
    {
        //****************************************************************************
        //***************************   Selected Operations   ************************
        //****************************************************************************
      
        ////////////////////// Level - Swiching Center // 
        
        if(CellSummary_Level_SW == null)
        {
            CellSummary_Level_SW = new MDM_JP_CellSummary();
            CellSummary_Level_SW.setDescCell("Switching Center",null);
            jP_Selections.add(CellSummary_Level_SW);
        }
        else
            CellSummary_Level_SW.resetValueCell();
        
        boolean find = false;
        for(int i=0;i<staticLib.centrali.length;i++)
        {
            int ID_Cent = staticLib.idCentrali[i];
            
            if(ID_Cent != -1)
            {
                if(staticLib.struc_paramsCURRENT.Fep[ID_Cent] == 1)
                {
                    CellSummary_Level_SW.setValueCell(staticLib.centrali[i],"item_switch_on.png");
                    find = true;
                }
            }
        }        
        if(find == false)
        {
            CellSummary_Level_SW.setValueCell("None",null);
            CellSummary_Level_SW.setDescCell_icon("no_mini.png");
        }

        ////////////////////// Level - Type Date //        
        if(CellSummary_Level_TD == null)
        {
            CellSummary_Level_TD = new MDM_JP_CellSummary();
            CellSummary_Level_TD.setDescCell("Data Type",null);
            jP_Selections.add(CellSummary_Level_TD);
        }
        else
            CellSummary_Level_TD.resetValueCell();
        
        CellSummary_Level_TD.setValueCell(staticLib.tipoDati[staticLib.struc_paramsCURRENT.DataType],"check_on_mini.gif");
        
        
        ////////////////////// Level Network Reports //
        if(CellSummary_Level_NR == null)
        {
            CellSummary_Level_NR = new MDM_JP_CellSummary();
            CellSummary_Level_NR.setDescCell("Network Reports",null);
            jP_Selections.add(CellSummary_Level_NR);
        }
        else
            CellSummary_Level_NR.resetValueCell();
        
        if(staticLib.struc_paramsCURRENT.NetworkRealTime == 1)
            CellSummary_Level_NR.setValueCell("Activated","check_on_mini.gif");
        else
            CellSummary_Level_NR.setValueCell("Deactivated","check_off_mini.gif");
                    
        
        ////////////////////// Level - Analisys //
        if(CellSummary_Analisys == null)
        {
            CellSummary_Analisys = new MDM_JP_CellSummary();
            CellSummary_Analisys.setDescCell("Analisys Type",null);
            jP_Selections.add(CellSummary_Analisys);        
        }
        else 
            CellSummary_Analisys.resetValueCell();
            
        CellSummary_Analisys.setValueCell(localAnalisi.get_LongDescription(staticLib.struc_paramsCURRENT.AnalysisType),"item_analys_on.png");
                
        ////////////////////// Level - Order Criteria //
        if(CellSummary_Order_Cr == null)
        {
            CellSummary_Order_Cr = new MDM_JP_CellSummary();
            CellSummary_Order_Cr.setDescCell("Order Criteria",null);
            jP_Selections.add(CellSummary_Order_Cr);        
        }
        else
            CellSummary_Order_Cr.resetValueCell();
          
        jCbx_listCriteria.removeActionListener(eventjCbx_listCriteria);
        
        if(staticLib.struc_paramsCURRENT.FlagSort)
        {
            //CellSummary_Order_Cr.setValueCell("by "+localCounters.getDicituraSort(staticLib.TipoAnalisiTAG,staticLib.struc_paramsCURRENT.ElementToSort-1),"item_order_on.png");
            
            String counter = localCounters.getDicSort(staticLib.TipoAnalisiTAG,staticLib.struc_paramsCURRENT.ElementToSort);
            CellSummary_Order_Cr.setValueCell("by "+counter,"item_order_on.png");
            
            jCbx_listCriteria.setSelectedItem(counter);
            
            jCkbox_increasing.setEnabled(true);
            jCkbox_decreasing.setEnabled(true);
            
            if(staticLib.struc_paramsCURRENT.Ascendent)
            {
                CellSummary_Order_Cr.setValueCell("Sort Crit. Increasing","check_on_mini.gif");
                jCkbox_increasing.setSelected(true);
                jCkbox_decreasing.setSelected(false);
            }
            else
            {
                CellSummary_Order_Cr.setValueCell("Sort Crit. Decreasing","check_on_mini.gif");
                jCkbox_decreasing.setSelected(true);
                jCkbox_increasing.setSelected(false);
            }

        }
        else
        {
            jCbx_listCriteria.setSelectedIndex(0);
            jCkbox_increasing.setEnabled(false);
            jCkbox_decreasing.setEnabled(false);
            CellSummary_Order_Cr.setValueCell("Deactivated","check_off_mini.gif");
            CellSummary_Order_Cr.setValueCell("Sort Criteria none","check_off_mini.gif");           
        }
        
        jCbx_listCriteria.addActionListener(eventjCbx_listCriteria);
        
        if(staticLib.struc_paramsCURRENT.IsPercent)
        {
            jCkbox_percValue.setSelected(true);
            CellSummary_Order_Cr.setValueCell("Format Percent value","check_on_mini.gif");
            staticLib.AbsoluteValue = new String("false");
        }
        else
        {
            jCkbox_absValue.setSelected(true);
            CellSummary_Order_Cr.setValueCell("Format Absolute value","check_on_mini.gif");
            staticLib.AbsoluteValue = new String("true");
        }
                
         ////////////////////// Level - Report Type //
        if(CellSummary_Report_T == null)
        {
            CellSummary_Report_T = new MDM_JP_CellSummary();
            CellSummary_Report_T.setDescCell("Report Type",null);
            jP_Selections.add(CellSummary_Report_T);         
        }
        else
            CellSummary_Report_T.resetValueCell();    
        CellSummary_Report_T.setValueCell(localReports.get_DescReport(staticLib.struc_paramsCURRENT.idReportSchema),"item_report_on.png");
        
        
        ////////////////////// Relations - Selected Relation //
        
        if(CellSummary_Relation == null)
        {
            CellSummary_Relation = new MDM_JP_CellSummary();
            CellSummary_Relation.removeDesc();
            jP_Selections.add(CellSummary_Relation);
        } 
        else
            CellSummary_Relation.resetValueCell();
            
               
        
        if( (staticLib.struc_paramsCURRENT.Relation > 0 ) || (staticLib.struc_paramsCURRENT.Relation == ID_FREE_RELATION ) )
        {
            CellSummary_Relation.setValueCell(staticLib.descRelation,"foglia.png");  
        }
        else
        {
            CellSummary_Relation.setValueCell("Relation not selected","no_mini.png");  
        }
        
        
        ////////////////////// Relations - Relation Direct //

        if(CellSummary_Rela_Dir == null)
        {
            CellSummary_Rela_Dir = new MDM_JP_CellSummary();
            CellSummary_Rela_Dir.setDescCell("Relation Direction",null);
            jP_Selections.add(CellSummary_Rela_Dir);
        }
        else
            CellSummary_Rela_Dir.resetValueCell();
        
        if(staticLib.struc_paramsCURRENT.Relation > 0)
        {
            boolean appoGhost = false;
            for(int i=0;i<staticLib.idGhostRel.length;i++)
            {
                if(staticLib.idGhostRel[i] == staticLib.struc_paramsCURRENT.Relation)
                {
                    appoGhost = true;
                    break;
                }
            }

            if(!appoGhost)
            {
                //Local_DirezioneRelazione -- Memorizzo la selezione utente della direzione di relazione (nella richiesta pivot e resettata a DIRECT)
                Local_DirezioneRelazione = staticLib.struc_paramsCURRENT.RelationDirection;
                CellSummary_Rela_Dir.setValueCell(staticLib.relDirection[staticLib.struc_paramsCURRENT.RelationDirection],"check_on_mini.gif");
            }
            else
                CellSummary_Rela_Dir.setValueCell("Deactivated","check_off_mini.gif");
        }
        
        ////////////////////// Relations - Value Output Format //
        
        if(CellSummary_ValueOut == null)
        {
            CellSummary_ValueOut = new MDM_JP_CellSummary();
            CellSummary_ValueOut.setDescCell("Data Format",null);
            jP_Selections.add(CellSummary_ValueOut);
        }
        else
            CellSummary_ValueOut.resetValueCell();
        
        if( (staticLib.struc_paramsCURRENT.Relation > 0) || (staticLib.struc_paramsCURRENT.Relation == ID_FREE_RELATION) )
        {
            String str_dataFormat = "";

            if(staticLib.struc_paramsCURRENT.HexValue == 0)
                str_dataFormat = "Decimal";
            else if(staticLib.struc_paramsCURRENT.HexValue == 1)
                str_dataFormat = "Hexadecimal first";
            else if(staticLib.struc_paramsCURRENT.HexValue == 2)
                str_dataFormat = "Hexadecimal second";
            else if(staticLib.struc_paramsCURRENT.HexValue == 3)
                str_dataFormat = "Hexadecimal both";        

            CellSummary_ValueOut.setValueCell(str_dataFormat,"check_on_mini.gif");
        }
        
        //****************************************************************************
        //***************************  Selected Days   *******************************
        //****************************************************************************
        
        
        /*if(staticLib.struc_paramsCURRENT.ElaborationData != null)
        {
            int LENGTH_Data_Process = staticLib.struc_paramsCURRENT.ElaborationData.length;
            System.out.println("sommario - LENGTH ElaborationData ==> "+LENGTH_Data_Process);
            for(int i=0; i<LENGTH_Data_Process; i++)
            {
                System.out.println("sommario - ElaborationData --> "+ (new String(staticLib.struc_paramsCURRENT.ElaborationData[i].dateString)).trim() );
            }           
        }else
            System.out.println("sommario - LENGTH ElaborationData ==> NULL");
           */ 
          
        
        int LENGTH_strSelectedDays = strSelectedDays.length;
        
        BLOCK_ANALISI = false;
        if(LENGTH_strSelectedDays > 15)
        {
           if( staticLib.struc_paramsCURRENT.Filters.length < 3 )
           {                
               BLOCK_ANALISI = true;              
           }
        }
        
        if(jP_Validdate != null)
            jP_SelectedDay.remove(jP_Validdate);
        
        jP_Validdate = new javax.swing.JPanel();
        jP_Validdate.setLayout(new java.awt.FlowLayout(0));
            
        int rows = 0;
        for(int i=0; i<LENGTH_strSelectedDays;i++)
        {
            if(strSelectedDays[i].length() != 0)
                rows = rows +1;
        }
        if(rows < 9 )
            rows = 9;

        jP_Validdate.setLayout(new java.awt.GridLayout((rows),1));
        jP_Validdate.setOpaque(false);

        boolean find_SelectedDays = false;
        for(int i=0;i<LENGTH_strSelectedDays;i++)
        {    
            if(strSelectedDays[i].length() != 0)
            {                
                javax.swing.JLabel JL_Validdate = new javax.swing.JLabel(strSelectedDays[i]);
                JL_Validdate.setIcon(iconDateSel);
                JL_Validdate.setForeground(java.awt.Color.black);
                JL_Validdate.setBackground(java.awt.Color.white);
                JL_Validdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                JL_Validdate.setOpaque(true);
                JL_Validdate.setFont(staticLib.fontA10);
                jP_Validdate.add(JL_Validdate);
                find_SelectedDays = true;
            }
        } 

        if(find_SelectedDays == false)
        {
            javax.swing.JLabel JL_Validdate = new javax.swing.JLabel("No selected day");
            JL_Validdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/no_mini.png")));
            JL_Validdate.setForeground(java.awt.Color.black);
            JL_Validdate.setBackground(java.awt.Color.white);
            JL_Validdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            JL_Validdate.setOpaque(true);
            JL_Validdate.setFont(staticLib.fontA10);
            jP_Validdate.add(JL_Validdate);      
        }
        
        jP_SelectedDay.add(jP_Validdate);
        
        //*****************************************************************************
        //*********************  Selected Restrictions   ******************************
        //*****************************************************************************	
 
        jP_SelRestrictions.removeAll();
        jP_SelRestrictions.setLayout(new java.awt.BorderLayout(4,4));
        
        javax.swing.JPanel JP_North = new javax.swing.JPanel();
        JP_North.setLayout(new javax.swing.BoxLayout(JP_North, javax.swing.BoxLayout.Y_AXIS));
        
        if((staticLib.struc_paramsCURRENT.Relation > 0) || (staticLib.struc_paramsCURRENT.Relation == ID_FREE_RELATION))// e' stata selezionata una relazione.
        {                    
            //System.out.println(" ----------- ï¿½ stata selezionata una relazione.  ------------- "); 
            
            ////////////////////// Normal/Single Dimension
            if(CellSummary_Restr_Dimension == null) 
            {
                CellSummary_Restr_Dimension = new MDM_JP_CellSummary(220);
                CellSummary_Restr_Dimension.setDescCell("Normal/Single Dimension","b_mini_vok.png");
            }
            else
                CellSummary_Restr_Dimension.resetValueCell();
            
            if(staticLib.struc_paramsCURRENT.SingleRelation == true)
                CellSummary_Restr_Dimension.setValueCell("Single",null);
            else
                CellSummary_Restr_Dimension.setValueCell("Normal",null);
            
            JP_North.add(CellSummary_Restr_Dimension);
            
            ////////////////////// Normal/Exclusive Restriction
            if(CellSummary_Restr_Exclusive == null)
            {
                CellSummary_Restr_Exclusive = new MDM_JP_CellSummary(220);
                CellSummary_Restr_Exclusive.setDescCell("Normal/Exclusive Restriction","b_mini_vok.png");
            }
            else
                CellSummary_Restr_Exclusive.resetValueCell();

            if(staticLib.struc_paramsCURRENT.OppositeRestriction == true)
                CellSummary_Restr_Exclusive.setValueCell("Exclusive",null);
            else
                CellSummary_Restr_Exclusive.setValueCell("Normal",null); 
            
            JP_North.add(CellSummary_Restr_Exclusive);
            
            jP_SelRestrictions.add(JP_North,java.awt.BorderLayout.NORTH);            
            
            if(staticLib.dallaTabJpJobs) //nello
            {
                //System.out.println("Da Controllare flag --> dallaTabJpJobs");
                int cont = 0;
                staticLib.dallaTabJpJobs = false;
                BufferRestrizioni appoBR = new BufferRestrizioni();

                TrafficElement localTrafficElements = new TrafficElement(configuration.get_Traffic_Elements());
                for(int i=0;i<staticLib.struc_paramsCURRENT.Filters.length;i++)
                //ciclo tutte le restrizioni
                {
                    REST_INFO rest=staticLib.struc_paramsCURRENT.Filters[i];
                    cont=0;
                    while(rest.Value[cont]!=0)
                    {
                        appoBR.addRestriction(rest.Element,
                                localTrafficElements.get_longDescription(rest.Element),
                                //rest.Value[cont],
                                new String(rest.AsciiValue[cont]).trim(),
                                new String(""+rest.Value[cont]).trim(),
                                false,
                                0,
                                rest.Priority,
                                -1);
                        cont++;
                    }
                }
            }
            else
            {
                if(staticLib.struc_paramsCURRENT.Relation != current_idRelations) 
                {  
                    current_idRelations = staticLib.struc_paramsCURRENT.Relation;
                }
                
                //br.creaAlbero();
                jP_SelRestrictions.add(br.creaSummaryRestriction(),java.awt.BorderLayout.CENTER); //sostituisce br.creaAlbero
            }
        }
        else
        {
            //System.out.println(" ----------- CellSummary_NO_Restrictios ------------- ");
            if(CellSummary_NO_Restrictios == null)
            {
                CellSummary_NO_Restrictios = new MDM_JP_CellSummary(190);
                CellSummary_NO_Restrictios.removeDesc();
                CellSummary_NO_Restrictios.setValueCell("Relation not selected","no_mini.png");
            }  
            jP_SelRestrictions.add(CellSummary_NO_Restrictios);
        }
        jP_SelRestrictions.validate();
    }

    public void resetJBar()
    {
        jp_centrali.resetBar();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JScrollPane jScroll_Selections;
    private javax.swing.JPanel jP_Selections;
    private javax.swing.JScrollPane jScroll_Day;
    private javax.swing.JPanel jP_SelectedDay;
    private javax.swing.JScrollPane jScroll_Restrictions;
    private javax.swing.JPanel jP_SelRestrictions;
    private javax.swing.JPanel jP_request_status;
    private javax.swing.JButton jbSend;
    private javax.swing.JButton jbSchedule;
    private javax.swing.JScrollPane jScroll_listReport;
    private javax.swing.JButton jbView;
    private javax.swing.JButton jbReset;
    private javax.swing.JLabel jL_listReport;
    private javax.swing.JPanel jP_Status_SW;
    private javax.swing.JPanel jP_status_TOT;
    private javax.swing.JLabel jL_process;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgProcess;
    private javax.swing.JLabel jL_status;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jB_pivot;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox jCbx_listCriteria;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox jCkbox_increasing;
    private javax.swing.JCheckBox jCkbox_decreasing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox jCkbox_absValue;
    private javax.swing.JCheckBox jCkbox_percValue;
    // End of variables declaration//GEN-END:variables
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private IconPool ip                = new IconPool("/images/");
    private JListIcon jListIconReport  = null;
    
    private MDM_JP_Centrali     jp_centrali;
    
    private getConfigFiltro     configuration;
    private javax.swing.JApplet ja;
    private BufferRestrizioni   br;
    private Analisi             localAnalisi;
    private Counters            localCounters;
    private Reports             localReports;
    private int                 current_idRelations;
    
    private MDM_JP_CellSummary CellSummary_Level_SW;
    private MDM_JP_CellSummary CellSummary_Level_TD;
    private MDM_JP_CellSummary CellSummary_Level_NR;
    private MDM_JP_CellSummary CellSummary_Analisys;
    private MDM_JP_CellSummary CellSummary_Order_Cr;
    private MDM_JP_CellSummary CellSummary_Report_T;
    private MDM_JP_CellSummary CellSummary_Relation;
    private MDM_JP_CellSummary CellSummary_Rela_Dir;
    private MDM_JP_CellSummary CellSummary_ValueOut;
    
    private javax.swing.JPanel jP_Validdate;
    private javax.swing.ImageIcon iconDateSel =  new javax.swing.ImageIcon(getClass().getResource("/images/date.png"));
    
    private MDM_JP_CellSummary CellSummary_Restr_Dimension;
    private MDM_JP_CellSummary CellSummary_Restr_Exclusive;
    private MDM_JP_CellSummary CellSummary_NO_Restrictios;
 
    private final String defaultURL = staticLib.path_report;
    
    private MDM_JD_InitPivot initPivot = null;
    private int Local_DirezioneRelazione = 0;
    
    private java.awt.event.ActionListener eventjCbx_listCriteria = null;
    private boolean BLOCK_ANALISI = false;
    //private String msg_BLOCK_ANALISI = "Attenzione: æ£onsentito effettuare l'analisi su una base dati maggiore di 15 giorni, solo se assegnate almeno due restrizioni.";
    private String msg_BLOCK_ANALISI =  "Analysis greater than 15 days allowed only for 3 or more restrictions.";
    
    private int ID_FREE_RELATION = staticLib.RELATION_FREEFORMAT_ID;
    
    /* per gestione report multipli */
    
}

