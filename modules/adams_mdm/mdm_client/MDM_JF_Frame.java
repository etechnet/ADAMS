import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
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
 * Questa classe che estende una JFrame, contiene l'intera interfaccia dell'aplicativo NTM, strutturata con Table.
 * La JFrame ï¿½ preposta all'istanziamento e all'inizializzazione di tutti gli oggetti costituenti l'interfaccia NTM.
 *
 */
public class MDM_JF_Frame extends javax.swing.JFrame implements Runnable
{
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe, preposto all'inizializzazione dell'interfaccia NTM.
     * Il costruttore riceve il riferimento alla JProgressBar dello stato di avanzamento della
     * costruzione dell'interfaccia NTM.
     * </font></font></p></pre>
     * @param statusBar riferimento alla JProgressBar,
     * @param jlBar riferimento alla Jlabel descrittiva della JProgressBar,
     * @param ja riferimento all'JApplet lanciata per l'esecuzione dell'apllicativo NTM,
     * @param configuration riferimento all'oggetto di tipo getConfigFiltro contenente l'intera configurazione.
     */
    public MDM_JF_Frame(javax.swing.JProgressBar StatusBar, javax.swing.JLabel jl_Bar, javax.swing.JApplet jA, getConfigFiltro Configuration)
    {
        this.statusBar      = StatusBar;
        this.jlBar          = jl_Bar;
        this.ja             = jA;
        this.configuration  = Configuration;
        br.setConfiguration(configuration);
        staticLib.frame_principale=this;

        staticLib.dallaTabJpJobs = false;

        setProgresBar(30,"Status Loading: 30%");

        struct_params=new STRUCT_PARAMS();
        struct_params.Fep=new int[staticLib.NUMERO_CENTRALI];
        struct_params.Fep[0]=1;
        struct_params.OutputType=0; // eliminato selectOutput
        struct_params.user = set_String_toChar(staticLib.utenza,staticLib.CORBA.USR_LOGIN_LEN);
        struct_params.user_ip = set_String_toChar(staticLib.IP_ADDRESS,staticLib.CORBA.IP_STRING_LEN);

        //System.out.println("MDM_JF_Frame per FreeRelation - struct_params.ffRelation= new int[staticLib.MAX_DIMENSION]; ");
        struct_params.ffRelation= new int[staticLib.MAX_DIMENSION];

        setProgresBar(40,"Status Loading: 40%");

        // MEMORIZZO la STRUCT_PARAMS corrente.
        struct_params.Relation=-1;
        staticLib.struc_paramsCURRENT = struct_params;
        staticLib.struc_paramsCURRENT.Name = set_String_toChar(staticLib.NameConfig,160);

        setProgresBar(45,"Status Loading: 45%");

        initComponents();

        staticLib.mainFRAME=this;
        staticLib.Tabella = jTabbe_Tot;

        setProgresBar(50,"Status Loading: 50%");

        //System.out.println("struct_params: "+struct_params.ElaborationData);
        //imposta la prima centrale come default;
        //System.out.println("staticLib.NUMERO_CENTRALI"+staticLib.NUMERO_CENTRALI);
        struct_params.Fep=new int[staticLib.NUMERO_CENTRALI];
        struct_params.Fep[0]=1;

        setProgresBar(55,"Status Loading: 55%");

        jTabbe_Tot.setEnabledAt(2,false);

        setProgresBar(60,"Status Loading: 60%");

        //Icons/ TAB  (messe a mano per problemi con forte - vengono eliminate le icone quando viene riaperto il file)
        jTabbe_Tot.setIconAt(0,new javax.swing.ImageIcon(getClass().getResource("/images/tab_selections.png")));
        jTabbe_Tot.setIconAt(1,new javax.swing.ImageIcon(getClass().getResource("/images/tab_relations.png")));
        jTabbe_Tot.setIconAt(2,new javax.swing.ImageIcon(getClass().getResource("/images/tab_restrictions.png")));
        jTabbe_Tot.setIconAt(3,new javax.swing.ImageIcon(getClass().getResource("/images/tab_summary.png")));
        jTabbe_Tot.setIconAt(4,new javax.swing.ImageIcon(getClass().getResource("/images/tab_jobs.png")));

        setProgresBar(65,"Status Loading: 65%");

        //ToolTipTex (messe a mano per problemi con forte - vengono eliminati i tooltip quando viene riaperto il file)
        jTabbe_Tot.setToolTipTextAt(0,"List of static operation");
        jTabbe_Tot.setToolTipTextAt(1,"List of Relations");
        jTabbe_Tot.setToolTipTextAt(2,"List of Restriction");
        jTabbe_Tot.setToolTipTextAt(3,"Summary of operation selected");
        jTabbe_Tot.setToolTipTextAt(4,"Scheduler Jobs");

        //setFont
        jTabbe_Tot.setFont(staticLib.fontA11);
        jB_resetParam.setFont(staticLib.fontA11);
        jB_resetRestrictions.setFont(staticLib.fontA11);

        //cursor
        jTabbe_Tot.setCursor(Cur_hand);
        jP_selection.setCursor(Cur_default);
        jP_relations.setCursor(Cur_default);
        jP_restrictions.setCursor(Cur_default);
        jP_summary.setCursor(Cur_default);
        jP_schedJob.setCursor(Cur_default);
        jB_resetParam.setCursor(Cur_hand);
        jB_resetRestrictions.setCursor(Cur_hand);

        // *************** SELECTIONS TAB *************** //

        // --- Select Level ---
          selectLevel = new MDM_JP_SelectLevel(struct_params,this);
       // selectLevel.setBounds(10,10,220,330);
       // jP_selection.add(selectLevel);

        setProgresBar(70,"Status Loading: 70%");

        // --- Select Analisys ---
        selectAnalisys = new MDM_JP_SelectAnalisys(struct_params,configuration);
        selectAnalisys.setBounds(10,350,220,210);
        jP_selection.add(selectAnalisys);

        setProgresBar(75,"Status Loading: 75%");

        // --- Select Sort ---
       /* selectSort = new MDM_JP_SelectSort(struct_params,configuration);
        selectSort.setBounds(240,10,280,330);
        jP_selection.add(selectSort);
        staticLib.sortPanel1 = selectSort;*/

        setProgresBar(80,"Status Loading: 80%");

        // --- Select Report ---
        selectReport = new MDM_JP_SelectReport(struct_params,configuration);
        //selectReport.setBounds(240,350,280,220);
        selectReport.setBounds(240,10,280,330);
        staticLib.selectReport1 = selectReport;
        jP_selection.add(selectReport);

        setProgresBar(85,"Status Loading: 85%");

        // --- Select Calendar ---

        reload_Calendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync_cal.jpg")));
        reload_Calendar.setToolTipText("Sync Calendar");
        reload_Calendar.setBackground(new java.awt.Color(230, 230, 230));
        reload_Calendar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync_cal_over.jpg")));
        reload_Calendar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sync_cal_press.jpg")));
        reload_Calendar.setFocusPainted(false);
        reload_Calendar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        reload_Calendar.setContentAreaFilled(false);
        reload_Calendar.setBorderPainted(false);
        reload_Calendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reload_CalendarActionPerformed(evt);
            }
        });

        selectCalendar = new MDM_JP_Calendar(struct_params,false,reload_Calendar,false);
        selectCalendar.setBounds(530,10,420,330);
        jP_selection.add(selectCalendar);

        setProgresBar(90,"Status Loading: 90%");
        // *************** RELATIONS TAB *************** //

        // --- Relations ---
        selectRelations = new MDM_JP_SelectRelations(configuration,struct_params,br);
        staticLib.selectRelations1 = selectRelations; //nello da valuare
        jP_relations.add(selectRelations,java.awt.BorderLayout.CENTER);

        setProgresBar(95,"Status Loading: 95%");
        // *************** RESTRICTIONS TAB *************** //
        // --- select Restrictions ---
        selectRestrictions = new MDM_JP_SelectRestrictions(configuration,br);
        staticLib.selectRestrictions1 = selectRestrictions;//nello da valuare
        jP_restrictions.add(selectRestrictions,java.awt.BorderLayout.CENTER);

        setProgresBar(95,"Status Loading: 97%");

        // *************** SUMMARY TAB *************** //
        selectSummary = new MDM_JP_Summary(ja,configuration,br);
        staticLib.selectSummary1 = selectSummary;
        jP_summary.add(selectSummary,java.awt.BorderLayout.CENTER);


        setProgresBar(95,"Status Loading: 99%");

        // *************** JOBS TAB *************** //

        selectJobs = new MDM_JP_Jobs(configuration);
        jP_schedJob.add(selectJobs,java.awt.BorderLayout.CENTER);
        staticLib.selectJobs1 = selectJobs;    //nello da controllare

        setCenteredFrame(1115,680);
        //setCenteredFrame(980,730);
        setProgresBar(100,"Status Loading: 100%");
        staticLib.jTabbe_Tot=jTabbe_Tot;


        if( staticLib.codaNumber!=-1 )
        {
            selectSummary.setEnabled_jbSchedule(true);
            jTabbe_Tot.setEnabledAt(4,true);
        }
        else
        {
            //System.out.println("Non ci sono code configurate per il profilo attuale");
            selectSummary.setEnabled_jbSchedule(false);
            jTabbe_Tot.setEnabledAt(4,false);
        }


        //show();

        /* GESTIONE SESSIONI: N.B.: inserire sul'html il parametro SESSION_TIME (espresso in minuti) */

	    SimpleRunner r = new SimpleRunner(this,jTabbe_Tot);
	    Thread sessione_timeout = new Thread(r);
	    //System.out.println("SimpleRunner START");
	    sessione_timeout.start();

        this.setResizable(true);
	this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tbuttonGroup = new javax.swing.ButtonGroup();
        jToggleButton1 = new javax.swing.JToggleButton();
        jP_north = new javax.swing.JPanel();
        jL_icon1 = new javax.swing.JLabel();
        jL_icon2 = new javax.swing.JLabel();
        jP_south = new javax.swing.JPanel();
        jTabbe_Tot = new javax.swing.JTabbedPane();
        jScroll_Selection = new javax.swing.JScrollPane();
        jP_selection = new javax.swing.JPanel();
        jP_option = new javax.swing.JPanel();
        jB_resetParam = new javax.swing.JButton();
        jB_resetRestrictions = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jB_STS_Registries = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScroll_Relations = new javax.swing.JScrollPane();
        jP_relations = new javax.swing.JPanel();
        jScroll_Restrictions = new javax.swing.JScrollPane();
        jP_restrictions = new javax.swing.JPanel();
        jScroll_Summary = new javax.swing.JScrollPane();
        jP_summary = new javax.swing.JPanel();
        jScroll_SchedJob = new javax.swing.JScrollPane();
        jP_schedJob = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTB_Level = new javax.swing.JToggleButton();
        jTB_Analisys = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jP_active = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        jToggleButton1.setText("jToggleButton1");

        setTitle("Ntm Client Main");
        setBackground(new java.awt.Color(145, 206, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jP_north.setBackground(new java.awt.Color(255, 255, 255));
        jP_north.setBorder(new javax.swing.border.LineBorder(null, 2, true));
        jP_north.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jL_icon1.setBackground(new java.awt.Color(255, 255, 255));
        jL_icon1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brends/mdm_381x54.png"))); // NOI18N
        jL_icon1.setOpaque(true);
        jL_icon1.setPreferredSize(new java.awt.Dimension(950, 55));
        jP_north.add(jL_icon1);

        jL_icon2.setBackground(new java.awt.Color(255, 255, 255));
        jL_icon2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brends/adams_160x68.png"))); // NOI18N
        jL_icon2.setText("    ");
        jP_north.add(jL_icon2);

        getContentPane().add(jP_north, java.awt.BorderLayout.NORTH);

        jP_south.setBackground(new java.awt.Color(255, 255, 255));
        jP_south.setLayout(new javax.swing.BoxLayout(jP_south, javax.swing.BoxLayout.LINE_AXIS));

        jTabbe_Tot.setBackground(new java.awt.Color(145, 181, 255));
        jTabbe_Tot.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbe_Tot.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbe_Tot.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbe_TotStateChanged(evt);
            }
        });

        jP_selection.setBackground(new java.awt.Color(145, 181, 255));
        jP_selection.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_selection.setLayout(null);

        jP_option.setBackground(new java.awt.Color(183, 206, 255));
        jP_option.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11))); // NOI18N
        jP_option.setLayout(new java.awt.GridLayout(4, 1));

        jB_resetParam.setBackground(new java.awt.Color(230, 230, 230));
        jB_resetParam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_all_param.jpg"))); // NOI18N
        jB_resetParam.setToolTipText("Reset All Param");
        jB_resetParam.setBorderPainted(false);
        jB_resetParam.setContentAreaFilled(false);
        jB_resetParam.setFocusPainted(false);
        jB_resetParam.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_all_param_press.jpg"))); // NOI18N
        jB_resetParam.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_all_param_over.jpg"))); // NOI18N
        jB_resetParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_resetParamActionPerformed(evt);
            }
        });
        jP_option.add(jB_resetParam);

        jB_resetRestrictions.setBackground(new java.awt.Color(230, 230, 230));
        jB_resetRestrictions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_restrict.jpg"))); // NOI18N
        jB_resetRestrictions.setToolTipText("Reset Restrictions");
        jB_resetRestrictions.setBorderPainted(false);
        jB_resetRestrictions.setContentAreaFilled(false);
        jB_resetRestrictions.setFocusPainted(false);
        jB_resetRestrictions.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_restrict_press.jpg"))); // NOI18N
        jB_resetRestrictions.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_restrict_over.jpg"))); // NOI18N
        jB_resetRestrictions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_resetRestrictionsActionPerformed(evt);
            }
        });
        jP_option.add(jB_resetRestrictions);
        jP_option.add(jSeparator1);

        jPanel2.setBackground(new java.awt.Color(183, 206, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jB_STS_Registries.setBackground(new java.awt.Color(183, 206, 255));
        jB_STS_Registries.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/glass_blue_32.png"))); // NOI18N
        jB_STS_Registries.setText("STS Registries");
        jB_STS_Registries.setToolTipText("Area Code");
        jB_STS_Registries.setBorderPainted(false);
        jB_STS_Registries.setContentAreaFilled(false);
        jB_STS_Registries.setFocusPainted(false);
        jB_STS_Registries.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jB_STS_Registries.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/glass_green_32.png"))); // NOI18N
        jB_STS_Registries.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/glass_orange_32.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jB_STS_Registries, gridBagConstraints);

        jP_option.add(jPanel2);

        jP_selection.add(jP_option);
        jP_option.setBounds(780, 350, 170, 210);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brends/mdm_547x201.png"))); // NOI18N
        jP_selection.add(jLabel1);
        jLabel1.setBounds(230, 350, 550, 205);

        jScroll_Selection.setViewportView(jP_selection);

        jTabbe_Tot.addTab("Selections", null, jScroll_Selection, "");

        jP_relations.setBackground(new java.awt.Color(230, 230, 230));
        jP_relations.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_relations.setLayout(new java.awt.BorderLayout());
        jScroll_Relations.setViewportView(jP_relations);

        jTabbe_Tot.addTab("Relations", null, jScroll_Relations, "");

        jScroll_Restrictions.setBackground(new java.awt.Color(230, 230, 230));

        jP_restrictions.setBackground(new java.awt.Color(230, 230, 230));
        jP_restrictions.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_restrictions.setLayout(new java.awt.BorderLayout());
        jScroll_Restrictions.setViewportView(jP_restrictions);

        jTabbe_Tot.addTab("Restrictions", null, jScroll_Restrictions, "");

        jScroll_Summary.setBackground(new java.awt.Color(230, 230, 230));

        jP_summary.setBackground(new java.awt.Color(230, 230, 230));
        jP_summary.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_summary.setLayout(new java.awt.BorderLayout());
        jScroll_Summary.setViewportView(jP_summary);

        jTabbe_Tot.addTab("Summary", null, jScroll_Summary, "");

        jScroll_SchedJob.setBackground(new java.awt.Color(230, 230, 230));

        jP_schedJob.setBackground(new java.awt.Color(230, 230, 230));
        jP_schedJob.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_schedJob.setLayout(new java.awt.BorderLayout());
        jScroll_SchedJob.setViewportView(jP_schedJob);

        jTabbe_Tot.addTab("Scheduler Jobs", null, jScroll_SchedJob, "");

        jP_south.add(jTabbe_Tot);

        getContentPane().add(jP_south, java.awt.BorderLayout.SOUTH);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel4.setBackground(new java.awt.Color(145, 181, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(120, 10));
        jPanel4.setPreferredSize(new java.awt.Dimension(120, 100));

        tbuttonGroup.add(jTB_Level);
        jTB_Level.setText("Level");
        jTB_Level.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_Level.setMaximumSize(new java.awt.Dimension(70, 70));
        jTB_Level.setMinimumSize(new java.awt.Dimension(70, 70));
        jTB_Level.setPreferredSize(new java.awt.Dimension(70, 70));
        jTB_Level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTB_LevelActionPerformed(evt);
            }
        });
        jPanel4.add(jTB_Level);

        tbuttonGroup.add(jTB_Analisys);
        jTB_Analisys.setText("Analisys");
        jTB_Analisys.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTB_Analisys.setMaximumSize(new java.awt.Dimension(70, 70));
        jTB_Analisys.setMinimumSize(new java.awt.Dimension(70, 70));
        jTB_Analisys.setPreferredSize(new java.awt.Dimension(70, 70));
        jTB_Analisys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTB_AnalisysActionPerformed(evt);
            }
        });
        jPanel4.add(jTB_Analisys);

        jPanel3.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(145, 181, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jP_active.setBackground(new java.awt.Color(255, 255, 51));
        jP_active.setMaximumSize(new java.awt.Dimension(300, 300));
        jP_active.setMinimumSize(new java.awt.Dimension(300, 300));
        jP_active.setPreferredSize(new java.awt.Dimension(300, 300));
        jP_active.setLayout(new java.awt.CardLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jP_active, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(255, 102, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        jPanel1.add(jPanel7, gridBagConstraints);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void warningProblem(String str1,String str2)
    {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    private void reload_CalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reload_CalendarActionPerformed
        if(struct_params.DataType==0)
            ridisegnaCalendario(false);
        else  if(struct_params.DataType==1)
            ridisegnaCalendario(true);
    }//GEN-LAST:event_reload_CalendarActionPerformed

    public void  ridisegnaCalendario(boolean flagStored)
    {
        try
        {
        jP_selection.remove(selectCalendar);
        selectCalendar = new MDM_JP_Calendar(struct_params,false,reload_Calendar,flagStored);
        selectCalendar.setBounds(530,10,420,330);
        jP_selection.add(selectCalendar);

        struct_params.ElaborationData = null;
        //System.out.println("Elaboration data  = null");
        staticLib.startDATE.setText("");
        staticLib.endDATE.setText("");
        staticLib.startDATE_ghost="";
        staticLib.endDATE_ghost="";

        selectCalendar.repaint();
        selectCalendar.validate_pDay();
        jP_selection.repaint();
        }catch(Exception e )
        {}
    }

    private void jB_resetRestrictionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_resetRestrictionsActionPerformed
        this.setCursor(Cur_wait);
        this.jP_selection.setCursor(Cur_wait);

        selectRestrictions.resetRestrictions();
        staticLib.selectJobs1.Tabella.setEnabledAt(1,false); //nello

        this.jP_selection.setCursor(Cur_default);
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_resetRestrictionsActionPerformed

    /**
     *
     */
    public void resetParam_forChangeAnalisys()
    {
        this.setCursor(Cur_wait);
        this.jP_selection.setCursor(Cur_wait);

        br.celarAll();
        selectRelations.resetRelations();
        selectRestrictions.resetRestrictions();
        selectRestrictions.current_idRelations=-2;
        struct_params.IsPercent=true;
        struct_params.FlagSort=false;
        struct_params.ElementToSort=0;
        struct_params.Ascendent=true;
        struct_params.OutputType=0;
        //selectSort.resetSelectSort();
        selectReport.resetSelectReport();
        selectReport.resetSelectFormatValue();
        struct_params.RelationDirection=0;
        struct_params.HexValue=0;

        this.jP_selection.setCursor(Cur_default);

        staticLib.selectJobs1.Tabella.setEnabledAt(1,false); //nello

        this.setCursor(Cur_default);
    }

    private void jB_resetParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_resetParamActionPerformed
       this.setCursor(Cur_wait);
        this.jP_selection.setCursor(Cur_wait);

        br.celarAll();
        selectRelations.resetRelations();
        jTabbe_Tot.setEnabledAt(1,true);
        jTabbe_Tot.setEnabledAt(2,false);
        selectRestrictions.resetRestrictions();
        selectRestrictions.current_idRelations=-2;
        struct_params.IsPercent=true;
        struct_params.FlagSort=false;
        struct_params.ElementToSort=0;
        struct_params.Ascendent=true;
        struct_params.OutputType=0;
        //selectSort.resetSelectSort();
        selectReport.resetSelectReport();
        selectReport.resetSelectFormatValue();

        staticLib.selectJobs1.Tabella.setEnabledAt(1,false); //nello

        //selectOutput.resetOutput();                 //nello ELIMINATO
        selectCalendar.resetCalendar();
        selectAnalisys.resetAnalisi();
        selectLevel.resetLevel();
        struct_params.RelationDirection=0;
        struct_params.HexValue=0;

        this.jP_selection.setCursor(Cur_default);
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_resetParamActionPerformed

    private void jTabbe_TotStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbe_TotStateChanged
        //jTabbe_Tot.setIconAt(indexTab,new javax.swing.ImageIcon(getClass().getResource("/images/folder1.png")));
	//jTabbe_Tot.setIconAt(jTabbe_Tot.getSelectedIndex(),new javax.swing.ImageIcon(getClass().getResource("/images/folder_open1.png")));

        jTabbe_Tot.setForegroundAt(indexTab,java.awt.Color.black);
	jTabbe_Tot.setForegroundAt(jTabbe_Tot.getSelectedIndex(),java.awt.Color.white);

        if(selectRelations != null)
            selectRelations.closeFrameSearch();

        indexTab = jTabbe_Tot.getSelectedIndex();
        if(indexTab==0)
        {
            try
            {
                //selectSummary.setEnabled_jbSchedule(true);
                if( staticLib.codaNumber!=-1 )
                {
                    selectSummary.setEnabled_jbSchedule(true);
                    jTabbe_Tot.setEnabledAt(4,true);
                }
                else
                {
                    //System.out.println("Non ci sono code configurate per il profilo attuale");
                    selectSummary.setEnabled_jbSchedule(false);
                    jTabbe_Tot.setEnabledAt(4,false);
                }

            }
            catch(Exception ex)
            {}

        }
        if(indexTab==1)
        {
            /*try
            {
                    //System.out.println("MDM_FRAME <ID relation>:"+struct_params.Relazione);
                if(struct_params.Relazione==-1)
                    selectRelations.validateRelations();

                //staticLib.selectSummary.jbSchedule.setEnabled(true); //nello da controllare - sostituito
                selectSummary.setEnabled_jbSchedule(true);
            }catch(Exception ex)
            {}*/
            try
            {
                //selectSummary.setEnabled_jbSchedule(true);
                if( staticLib.codaNumber!=-1 )
                {
                    selectSummary.setEnabled_jbSchedule(true);
                    jTabbe_Tot.setEnabledAt(4,true);
                }
                else
                {
                    //System.out.println("Non ci sono code configurate per il profilo attuale");
                    selectSummary.setEnabled_jbSchedule(false);
                    jTabbe_Tot.setEnabledAt(4,false);
                }
            }
            catch(Exception ex)
            {}
        }
        if(indexTab==4)
        {
            //System.out.println("MDM_FRAME <indexTab>: "+indexTab);
            try
            {
                //selectSummary.setEnabled_jbSchedule(true);
                if( staticLib.codaNumber!=-1 )
                {
                    selectSummary.setEnabled_jbSchedule(true);
                    jTabbe_Tot.setEnabledAt(4,true);
                }
                else
                {
                    //System.out.println("Non ci sono code configurate per il profilo attuale");
                    selectSummary.setEnabled_jbSchedule(false);
                    jTabbe_Tot.setEnabledAt(4,false);
                }
            }
            catch(Exception ex)
            {}

        }

        if(indexTab==2)
        {
            Operation_TH = RESTRICTIONS;  //Case Thread
            TH = null;
            TH_EXIT = false;
            TH = new Thread(this,"TH MDM_JF_Frame");
            TH.start();

        }
        if(indexTab==3)
        {
        	//System.out.println("indexTab(3)");
            //******************************
            // SIMULAZIONE se necessaria
            //******************************
            staticLib.struc_paramsCURRENT.Description=new char[61];
            /*if(staticLib.flagPass)
            {
                staticLib.struc_paramsCURRENT.Reserved=true;
                //System.out.println("Password attivata.");
            }
            else
            {
                staticLib.struc_paramsCURRENT.Reserved=false;
                //System.out.println("Password disattivata.");
            }*/
            //br.stampaAll();


            struct_params.Filters = br.creaRest();
            //selectCalendar.caricaData();
            selectSummary.validateSummary(selectCalendar.getDateSelected());

            //selectSummary.jpc.resetBar(); //nello sostituito
            selectSummary.resetJBar();
        }

	if(staticLib.flagPass)
            {
                staticLib.struc_paramsCURRENT.Reserved=true;
                //System.out.println("Password attivata.");
            }
            else
            {
                staticLib.struc_paramsCURRENT.Reserved=false;
                //System.out.println("Password disattivata.");
            }
    }//GEN-LAST:event_jTabbe_TotStateChanged

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        setVisible(false);

        if(selectRelations != null)
            selectRelations.closeFrameSearch();

        jlBar.setText("   Press Button for start NTM ");
        statusBar.setValue(0);
        System.out.println("width => "+this.getSize().width+" height => "+this.getSize().height);
    }//GEN-LAST:event_exitForm

    private void jTB_LevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTB_LevelActionPerformed
        System.out.println("jTB_Level.isSelected() "+jTB_Level.isSelected());

        jP_active.removeAll();
        if(jTB_Level.isSelected())
            jP_active.add(selectLevel);

        jP_active.updateUI();

    }//GEN-LAST:event_jTB_LevelActionPerformed

    private void jTB_AnalisysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTB_AnalisysActionPerformed
        System.out.println("jTB_Analisys.isSelected() "+jTB_Analisys.isSelected());

        jP_active.removeAll();

        if(jTB_Analisys.isSelected())
            jP_active.add(selectAnalisys);

        jP_active.updateUI();
    }//GEN-LAST:event_jTB_AnalisysActionPerformed

    private void setProgresBar(int value,String str)
    {
    	statusBar.setValue(value);
        jlBar.setText(str);
    }

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }

    public void my_dispose()
    {
    }


    /**
     * Metodo che permette di trasformare una stringa in un array di caratteri.
     *@param str Stringa il cui contenuto sarï¿½ copiato nell'array di char.
     *@param length dimensione dell'array di char.
     *@return Un'array di char dalle dimensione passata "length" contenente i caratteri della stringa passata in input "str".
     */
    public static char[] set_String_toChar(String str, int length)
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

    public void run()
    {
        this.setCursor(Cur_wait);

        if(Operation_TH == RESTRICTIONS)
        {
            try
            {
                boolean flagGUIRelation = selectRestrictions.setGUIRelation();
                //br.controllaEccezioni();
            }
            catch (Exception e)
            {
            }
        }
        this.setCursor(Cur_default);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_STS_Registries;
    private javax.swing.JButton jB_resetParam;
    private javax.swing.JButton jB_resetRestrictions;
    private javax.swing.JLabel jL_icon1;
    private javax.swing.JLabel jL_icon2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_active;
    private javax.swing.JPanel jP_north;
    private javax.swing.JPanel jP_option;
    private javax.swing.JPanel jP_relations;
    private javax.swing.JPanel jP_restrictions;
    private javax.swing.JPanel jP_schedJob;
    private javax.swing.JPanel jP_selection;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jP_summary;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScroll_Relations;
    private javax.swing.JScrollPane jScroll_Restrictions;
    private javax.swing.JScrollPane jScroll_SchedJob;
    private javax.swing.JScrollPane jScroll_Selection;
    private javax.swing.JScrollPane jScroll_Summary;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jTB_Analisys;
    private javax.swing.JToggleButton jTB_Level;
    private javax.swing.JTabbedPane jTabbe_Tot;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.ButtonGroup tbuttonGroup;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JProgressBar statusBar;
    private javax.swing.JLabel jlBar;
    private javax.swing.JApplet ja;
    private getConfigFiltro configuration;

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);

    private java.awt.Color color_default = new java.awt.Color(230,230,230);
    private java.awt.Color color_selected = new java.awt.Color(255,0,0);

    private int	indexTab = 0;    //indice dei Tab
    private BufferRestrizioni br = new BufferRestrizioni(); //CDR,Restrizioni,DataElements
    private STRUCT_PARAMS struct_params;                    //Struttura che popolera man mano l'utente, da rimandare al MS, tramite CORBA

    private MDM_JP_SelectLevel          selectLevel         = null;
    private MDM_JP_SelectAnalisys       selectAnalisys      = null;
    //private MDM_JP_SelectSort           selectSort          = null;
    private MDM_JP_SelectReport         selectReport        = null;
    private MDM_JP_Calendar             selectCalendar      = null;
    private MDM_JP_SelectRelations      selectRelations     = null;
    private MDM_JP_SelectRestrictions   selectRestrictions  = null;
    private MDM_JP_Summary              selectSummary       = null;
    //private jpSummary            	selectSummary       = null; //nello da cancellare
    //private jpJobs 			selectJobs          = null; //nello da cancellare
    private MDM_JP_Jobs                 selectJobs          = null;

    private boolean TH_EXIT = false;
    private Thread TH = null;
    private int Operation_TH = -1; //Controllo operazione del thread
    private final int RESTRICTIONS = 1;

    private javax.swing.JButton reload_Calendar = new javax.swing.JButton();


    //private MDM_JD_wait F_wait_Restr = null;

}
