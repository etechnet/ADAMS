/*
 * ADAMS_JD_ADD_DEL_CounterKit.java
 *
 * Created on 14 ottobre 2005, 12.21
 */

/**
 *
 * @author  luca
 */
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;

public class ADAMS_JD_ADD_DEL_CounterKit extends javax.swing.JDialog implements Runnable{

    /** Creates new form ADAMS_JD_ADD_DEL_CounterKit */
    public ADAMS_JD_ADD_DEL_CounterKit(boolean modal,String title,ADAMS_TAB_KIT_CONTATORI KIT_Counter,int Add1_OR_Delete2) 
    {
        super(ADAMS_GlobalParam.JFRAME_TAB, modal);
        initComponents();
        
        jTF_TagCountersKit.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,30));        
        jTF_PLName.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,256)); 
        jTF_SpecPath.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,1540));
        
        this.KIT_COUNTER = KIT_Counter;
        this.setTitle(title);
        jL_TITLE.setText(title); 
        
        this.ADD1_OR_DELETE2 = Add1_OR_Delete2;
        if(ADD1_OR_DELETE2 == ADD1)
        {
            jL_TITLE.setBackground(java.awt.Color.green.darker());
            jl_IDCounterSel.setText("Auto");
            
            jLabel1.setEnabled(false);
            jTF_PLName.setEnabled(false);            
            jrb_default.setSelected(true);
            jrb_default.setEnabled(false);
            jrb_specify.setEnabled(false);
            jTF_SpecPath.setEnabled(false);
            
            jTF_TagCountersKit.requestFocus();            
        }
        else if (ADD1_OR_DELETE2 == DELETE2)
        {
            jL_TITLE.setBackground(java.awt.Color.red);
                        
            jl_IDCounterSel.setText(""+KIT_COUNTER.IDCOUNTER);
            jTF_TagCountersKit.setText(KIT_COUNTER.TAG);
                                                
            if(KIT_COUNTER.USEPLUGIN == 'Y')
            {
                jCBox_UsePL.setSelected(true);            
                jTF_PLName.setText(KIT_COUNTER.PLUGINNAME);
                
                if(KIT_COUNTER.USEPATH == 'Y')
                {
                    jrb_specify.setSelected(true);
                    jTF_SpecPath.setText(KIT_COUNTER.PATHNAME);
                }
                else
                    jrb_default.setSelected(true); 
            }                       
            setEnableGUI_pluginActive(false);
            jB_commit.setEnabled(true);
        }
              
        //Font Globali
        jL_TITLE.setFont(ADAMS_GlobalParam.font_B12);
        jl_IDCounterSel.setFont(ADAMS_GlobalParam.font_B10);
        jTF_TagCountersKit.setFont(ADAMS_GlobalParam.font_B10);
        jTF_PLName.setFont(ADAMS_GlobalParam.font_B10);
        jTF_SpecPath.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_UsePL.setFont(ADAMS_GlobalParam.font_B10);
        jrb_default.setFont(ADAMS_GlobalParam.font_B10);
        jrb_specify.setFont(ADAMS_GlobalParam.font_B10);
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);
        jLabel1.setFont(ADAMS_GlobalParam.font_B10);
        
        //Cursor
        jCBox_UsePL.setCursor(Cur_hand);        
        jrb_default.setCursor(Cur_hand); 
        jrb_specify.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
       
        setCenteredFrame(700,220);
        //show();
        this.setVisible(true);
    }

    private void setEnableGUI_pluginActive(boolean flag)
    {        
        jLabel1.setEnabled(flag);
        jTF_TagCountersKit.setEnabled(flag);
        jTF_PLName.setEnabled(flag);
        jTF_SpecPath.setEnabled(flag);
        jCBox_UsePL.setEnabled(flag);
        jrb_default.setEnabled(flag);
        jrb_specify.setEnabled(flag);    
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup2 = new javax.swing.ButtonGroup();
        jL_TITLE = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jCBox_UsePL = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTF_PLName = new javax.swing.JTextField();
        jTF_SpecPath = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTF_TagCountersKit = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jrb_default = new javax.swing.JRadioButton();
        jrb_specify = new javax.swing.JRadioButton();
        jl_IDCounterSel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jL_TITLE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_TITLE.setText("TITLE");
        jL_TITLE.setMinimumSize(new java.awt.Dimension(45, 25));
        jL_TITLE.setPreferredSize(new java.awt.Dimension(45, 25));
        jL_TITLE.setOpaque(true);
        getContentPane().add(jL_TITLE, java.awt.BorderLayout.NORTH);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(null, " Plugin Activation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jPanel1.setPreferredSize(new java.awt.Dimension(20, 130));
        jCBox_UsePL.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_UsePL.setText("Use Plugin");
        jCBox_UsePL.setToolTipText("(True/False)");
        jCBox_UsePL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCBox_UsePL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCBox_UsePL.setMinimumSize(new java.awt.Dimension(40, 22));
        jCBox_UsePL.setPreferredSize(new java.awt.Dimension(300, 22));
        jCBox_UsePL.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_UsePL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCBox_UsePL.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCBox_UsePL.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCBox_UsePL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_UsePLActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jCBox_UsePL, gridBagConstraints1);
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Plugin Name");
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 22));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel1, gridBagConstraints1);
        
        jTF_PLName.setDisabledTextColor(java.awt.Color.gray);
        jTF_PLName.setMinimumSize(new java.awt.Dimension(150, 22));
        jTF_PLName.setPreferredSize(new java.awt.Dimension(300, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints1.weightx = 1.0;
        jPanel1.add(jTF_PLName, gridBagConstraints1);
        
        jTF_SpecPath.setDisabledTextColor(java.awt.Color.gray);
        jTF_SpecPath.setMinimumSize(new java.awt.Dimension(150, 22));
        jTF_SpecPath.setPreferredSize(new java.awt.Dimension(300, 22));
        jTF_SpecPath.setEnabled(false);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints1.weightx = 1.0;
        jPanel1.add(jTF_SpecPath, gridBagConstraints1);
        
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Counter Tag");
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 22));
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jLabel2, gridBagConstraints1);
        
        jTF_TagCountersKit.setDisabledTextColor(java.awt.Color.gray);
        jTF_TagCountersKit.setMinimumSize(new java.awt.Dimension(180, 22));
        jTF_TagCountersKit.setPreferredSize(new java.awt.Dimension(280, 22));
        jTF_TagCountersKit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_TagCountersKitKeyReleased(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jTF_TagCountersKit, gridBagConstraints1);
        
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jrb_default.setBackground(new java.awt.Color(230, 230, 230));
        jrb_default.setSelected(true);
        jrb_default.setText("Default Path");
        jrb_default.setToolTipText("Default Path (True/False)");
        buttonGroup2.add(jrb_default);
        jrb_default.setContentAreaFilled(false);
        jrb_default.setFocusPainted(false);
        jrb_default.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jrb_default.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jrb_default.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_default.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_default.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_default.setMinimumSize(new java.awt.Dimension(100, 22));
        jrb_default.setPreferredSize(new java.awt.Dimension(100, 22));
        jrb_default.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_default.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_default.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_default.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_default.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jrb_specifyMouseReleased(evt);
            }
        });
        
        jPanel2.add(jrb_default);
        
        jrb_specify.setBackground(new java.awt.Color(230, 230, 230));
        jrb_specify.setText("Specify Path");
        jrb_specify.setToolTipText("Specify Path (True/False)");
        buttonGroup2.add(jrb_specify);
        jrb_specify.setContentAreaFilled(false);
        jrb_specify.setFocusPainted(false);
        jrb_specify.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jrb_specify.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jrb_specify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off.gif")));
        jrb_specify.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrb_specify.setMaximumSize(new java.awt.Dimension(21, 20));
        jrb_specify.setMinimumSize(new java.awt.Dimension(100, 22));
        jrb_specify.setPreferredSize(new java.awt.Dimension(100, 22));
        jrb_specify.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_specify.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_off_over.gif")));
        jrb_specify.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on_over.gif")));
        jrb_specify.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb_on.gif")));
        jrb_specify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jrb_specifyMouseReleased(evt);
            }
        });
        
        jPanel2.add(jrb_specify);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel2, gridBagConstraints1);
        
        jl_IDCounterSel.setBackground(java.awt.Color.white);
        jl_IDCounterSel.setForeground(java.awt.Color.gray);
        jl_IDCounterSel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jl_IDCounterSel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jl_IDCounterSel.setMinimumSize(new java.awt.Dimension(40, 22));
        jl_IDCounterSel.setPreferredSize(new java.awt.Dimension(40, 22));
        jl_IDCounterSel.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        jPanel1.add(jl_IDCounterSel, gridBagConstraints1);
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, -3));
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setMinimumSize(new java.awt.Dimension(72, 20));
        jPanel3.setPreferredSize(new java.awt.Dimension(152, 25));
        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_commit.setPreferredSize(new java.awt.Dimension(80, 30));
        jB_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jB_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jB_commit.setEnabled(false);
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });
        
        jPanel3.add(jB_commit);
        
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setToolTipText("Close");
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jPanel3.add(jB_close);
        
        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jCBox_UsePLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_UsePLActionPerformed
        if(jCBox_UsePL.isSelected())
        {    
            jLabel1.setEnabled(true);
            jTF_PLName.setEnabled(true);
            jrb_default.setEnabled(true);
            jrb_default.setSelected(true);
            jrb_specify.setEnabled(true);
            jTF_SpecPath.setEnabled(false);
            
            jTF_PLName.requestFocus();
        }
        else
        {
            jLabel1.setEnabled(false);
            jTF_PLName.setEnabled(false);            
            jrb_default.setSelected(true);
            jrb_default.setEnabled(false);
            jrb_specify.setEnabled(false);
            jTF_SpecPath.setEnabled(false);
        }
    }//GEN-LAST:event_jCBox_UsePLActionPerformed

    private void jrb_specifyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrb_specifyMouseReleased
        if(jrb_specify.isSelected())
        {
            jTF_SpecPath.setEnabled(true);
            jTF_SpecPath.requestFocus();
        }
        else
            jTF_SpecPath.setEnabled(false);
    }//GEN-LAST:event_jrb_specifyMouseReleased

    private void jTF_TagCountersKitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_TagCountersKitKeyReleased
        if (jTF_TagCountersKit.getText().trim().length() > 0)
            jB_commit.setEnabled(true);
        else
            jB_commit.setEnabled(false);
    }//GEN-LAST:event_jTF_TagCountersKitKeyReleased

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        result = -1; 
        setVisible(false);
    }//GEN-LAST:event_jB_closeActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
               
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
            return;
        
        
        if(ADD1_OR_DELETE2 == ADD1)
        {
            KIT_COUNTER.TAG = jTF_TagCountersKit.getText().trim();

            if(jCBox_UsePL.isSelected())
            {
                KIT_COUNTER.USEPLUGIN = 'Y';                
                
                String strPLName = jTF_PLName.getText().trim();
                if(strPLName.length() == 0)
                {
                    ADAMS_GlobalParam.optionPanel(this,"'Use Plugin' is enabled but there is no Plugin Name !","WARNING",4);
                    return;
                }                
                KIT_COUNTER.PLUGINNAME = strPLName;
                
                if(jrb_specify.isSelected())
                {
                    KIT_COUNTER.USEPATH = 'Y';
                    
                    String strPathName = jTF_SpecPath.getText().trim();
                    if(strPathName.length() == 0)
                    {
                        ADAMS_GlobalParam.optionPanel(this,"'Specify Path' is enabled but there is no Path !","WARNING",4);
                        return;
                    }
                    KIT_COUNTER.PATHNAME = strPathName;
                }
                else
                {
                    KIT_COUNTER.USEPATH  = 'N';
                    KIT_COUNTER.PATHNAME ="";
                }
            }
            else
            {
                KIT_COUNTER.USEPLUGIN   = 'N';
                KIT_COUNTER.USEPATH     = 'N';
                KIT_COUNTER.PLUGINNAME  = "";
                KIT_COUNTER.PATHNAME    = "";
            }
            add_Counter();
        }
        else if (ADD1_OR_DELETE2 == DELETE2) 
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"About to delete Counter Kit: "+KIT_COUNTER.IDCOUNTER+" "+KIT_COUNTER.TAG+". Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 1)
                delete_Counter();
        }
    }//GEN-LAST:event_jB_commitActionPerformed

    private void delete_IdCounter_AggAnalisi()
    {
        //System.out.println("Cancellare Associazioni  del contatore cancellato nelle Analisi");

        String str_update = "update tab_analysis_type set IDCOUNTERKIT=-1 where IDCOUNTERKIT ="+KIT_COUNTER.IDCOUNTER+
                                " and TIPO_DI_CONFIGURAZIONE='"+KIT_COUNTER.TIPO_DI_CONFIGURAZIONE+"'";  
        
        int Answer_up = ADAMS_GlobalParam.connect_Oracle.Operation(str_update);
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
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        result = -1;
        setVisible(false);
                
    }//GEN-LAST:event_closeDialog

    public int getResult()
    {
        return result;
    }
    
    private void add_Counter()
    {
        OperThread = ADD1;
        result = 0;
        
        th = null;
        th = new Thread(this,"add_Counter");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
        setVisible(false);
    }
    
    private void delete_Counter()
    {
        OperThread = DELETE2;
        result = 0;
        
        th = null;
        th = new Thread(this,"delete_Counter");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
        setVisible(false);
    }
    
        
    public void run()
    { 
        setEnableGUI_pluginActive(false);
        jB_commit.setEnabled(false);
        jB_close.setEnabled(false);
        
        if(KIT_COUNTER == null)
            Thread_Work = false;
        else
        {        
            if(OperThread == ADD1) //add_Counter()
            {   
                String item_KIT_CONTATORI = "TIPO_DI_CONFIGURAZIONE,IDCOUNTER,TAG,USEPLUGIN,PLUGINNAME,USEPATH,PATHNAME";

                String itemValue_KIT_CONTATORI = ( "'"+KIT_COUNTER.TIPO_DI_CONFIGURAZIONE+"',"+
                                                    "IDCOUNTER_seq.nextVal,"+
                                                    "'"+KIT_COUNTER.TAG+"',"+
                                                    "'"+KIT_COUNTER.USEPLUGIN+"',"+                                
                                                    "'"+KIT_COUNTER.PLUGINNAME+"',"+
                                                    "'"+KIT_COUNTER.USEPATH+"',"+
                                                    "'"+KIT_COUNTER.PATHNAME+"'");
                
                
                String str_Insert_KIT_CONTATORI = ("insert into "+name_TabCounters+" ("+item_KIT_CONTATORI+") values ( "+itemValue_KIT_CONTATORI+" )");
                //System.out.println("str_Insert_KIT_CONTATORI --> "+str_Insert_KIT_CONTATORI);
                
                int Answer_Ins = ADAMS_GlobalParam.connect_Oracle.Operation(str_Insert_KIT_CONTATORI);
        
                if(Answer_Ins == 1)
                {
                    ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(KIT_COUNTER.TIPO_DI_CONFIGURAZIONE);
                    result = 1;                    
                }   
            }
            else if(OperThread == DELETE2) //delete_Counter()
            {
                
                String str_delete = "delete from "+name_TabCounters+" where TIPO_DI_CONFIGURAZIONE='"+KIT_COUNTER.TIPO_DI_CONFIGURAZIONE+"'"+
                                 " and IDCOUNTER="+KIT_COUNTER.IDCOUNTER;
        
                int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(str_delete);                
                if(Answer_del > 0)
                {
                    delete_IdCounter_AggAnalisi();
                    ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(KIT_COUNTER.TIPO_DI_CONFIGURAZIONE);
                    result = 1;                    
                }
            }
        }
        Thread_Work = false;        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jL_TITLE;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox jCBox_UsePL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTF_PLName;
    private javax.swing.JTextField jTF_SpecPath;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTF_TagCountersKit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jrb_default;
    private javax.swing.JRadioButton jrb_specify;
    private javax.swing.JLabel jl_IDCounterSel;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private ADAMS_TAB_KIT_CONTATORI KIT_COUNTER = null;
    private int ADD1_OR_DELETE2 = 0;
    private int ADD1    = 1;
    private int DELETE2 = 2;
    
    private int result = -1;
    
     // ------ Thread
    private Thread th           = null;
    private boolean Thread_Work = false;
    private int OperThread      = -1;
    
    // Info DB Counters
    private final String name_TabCounters = "tab_counters_kit";
}