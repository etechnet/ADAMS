/*
 * P_JF_ConfigSetup.java
 *
 * Created on 3 maggio 2005, 10.08
 */

/**
 *
 * @author  root
 */
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_JF_ConfigSetup extends javax.swing.JFrame implements Runnable {

    /** Creates new form P_JF_ConfigSetup */
    private CS_BLOCK_LOG[] Local_AllBlock   = null;
    private checkSystem CS                  = null;
    public boolean sistemstart              = false;
    public boolean DEBUG                    = false;
    public String strCentrale               = "";
    private int lenPName                    = 40;
    int flagOperazione                      = 0;
    private java.awt.event.KeyAdapter evento=new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listProcessKeyReleased(evt);
            }
        };
    private java.awt.event.MouseAdapter evento1=new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listProcessMousePressed(evt);
            }
        };
    
    public SSM_JF_ConfigSetup(DATA_CENTRALI[] ALLcentrali) 
    {
        Local_ALLcentrali=ALLcentrali;
        
        
        initComponents();
        
        for(int i=0;i<7;i++)
        {
            jcProcessType.addItem(SSM_GlobalParam.typeProc[i]);
        }
        
        
        jtOra.setEnabled(false);
        jtMin.setEnabled(false);
            
        jtProcessName.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALLSPECIALCHAR,10));
        jtOra.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,2));
        jtMin.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,2));
        jtProcessCmd.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALLCHAR,49));
        
        
        IcPool = new SSM_IconPool("/images/");
        listProcess = new SSM_JListIcon(IcPool);
        listProcess.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //.MULTIPLE_INTERVAL_SELECTION);
        jScroll_process.setViewportView(listProcess);
        
        listProcess.addKeyListener(evento);
        listProcess.addMouseListener(evento1);
        
        /*for(int i=0; i<ALLcentrali.length; i++)        
        {
            //if((i!=8)&&(i!=7))
            //    continue;
            jCB_switch.addItem(new String(ALLcentrali[i].Descrizione).trim());
        } */
        
        for(int i=0; i<ALLcentrali.length; i++)        
        {
//            if ((SSM_GlobalParam.rmp3i).equals("YES")) {
//               if (!((new String(ALLcentrali[i].Descrizione).trim()).equals("RMP3I")))	// scarta tutto tranne RMP3I
//            			continue;
//
//               jCB_switch.addItem(new String(ALLcentrali[i].Descrizione).trim());
//            }else
//            {
//                if (((new String(ALLcentrali[i].Descrizione).trim()).equals("RMP3I")))	// scarta RMP3I
//            			continue;
//                jCB_switch.addItem(new String(ALLcentrali[i].Descrizione).trim());
//            }

            jCB_switch.addItem(new String(ALLcentrali[i].Descrizione).trim());
            
        }
        
        
        
        //Font Globali
        listProcess.set_Font(SSM_GlobalParam.font_V11);
        jL_title.setFont(SSM_GlobalParam.font_B12);
        
        jtProcessName.setFont(SSM_GlobalParam.font_B12);
        jcProcessType.setFont(SSM_GlobalParam.font_B12);
        jtOra.setFont(SSM_GlobalParam.font_B12);
        jtMin.setFont(SSM_GlobalParam.font_B12);
        jcProcessLink.setFont(SSM_GlobalParam.font_B12);
        jtProcessCmd.setFont(SSM_GlobalParam.font_B12);
        jrTrue.setFont(SSM_GlobalParam.font_B12);
        jrFalse.setFont(SSM_GlobalParam.font_B12);
        jCB_switch.setFont(SSM_GlobalParam.font_B12);
        //jB_searchProc.setFont(SSM_GlobalParam.font_B12);
        
        //Cursors
        jB_close.setCursor(cursor_hand);
        jCB_switch.setCursor(cursor_hand);
        listProcess.setCursor(cursor_hand);
        //jB_searchProc.setCursor(cursor_hand);
        
        jtProcessName.setCursor(cursor_hand);
        jcProcessType.setCursor(cursor_hand);
        jtOra.setCursor(cursor_hand);
        jtMin.setCursor(cursor_hand);
        jcProcessLink.setCursor(cursor_hand);
        jtProcessCmd.setCursor(cursor_hand);
        jrTrue.setCursor(cursor_hand);
        jrFalse.setCursor(cursor_hand);
        jButton1.setCursor(cursor_hand);
        jButton2.setCursor(cursor_hand);
        jB_reset.setCursor(cursor_hand);
        jButton3.setCursor(cursor_hand);
        jButton4.setCursor(cursor_hand);
        jButton6.setCursor(cursor_hand);
        jButton5.setCursor(cursor_hand);
        
        
        getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        setCenteredFrame(430,760);
        //show();
        this.setVisible(true);
            
        strCentrale=SWITCH_SELECTED;
        //CS=new checkSystem(this,SWITCH_SELECTED);
        //CS.start();
    }
    
    public void enableRestartAll(boolean flag)
    {
        jButton5.setEnabled(flag);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jL_title = new javax.swing.JLabel();
        jScroll_process = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtProcessName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jcProcessType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jtOra = new javax.swing.JTextField();
        jtMin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcProcessLink = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jtProcessCmd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jrTrue = new javax.swing.JRadioButton();
        jrFalse = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jCB_switch = new javax.swing.JComboBox();
        jB_close = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jB_reset = new javax.swing.JButton();
        jP_system = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jP_process = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("Monitorig Setup");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(null);

        jL_title.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configuration.png"))); // NOI18N
        jL_title.setText("Monitoring Setup");
        jL_title.setPreferredSize(new java.awt.Dimension(300, 32));
        getContentPane().add(jL_title);
        jL_title.setBounds(50, 10, 300, 32);

        jScroll_process.setBackground(new java.awt.Color(230, 230, 230));
        jScroll_process.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List of Process"));
        jScroll_process.setFont(new java.awt.Font("Courier", 0, 12)); // NOI18N
        jScroll_process.setMinimumSize(new java.awt.Dimension(300, 200));
        jScroll_process.setPreferredSize(new java.awt.Dimension(300, 220));
        getContentPane().add(jScroll_process);
        jScroll_process.setBounds(10, 240, 400, 220);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Selected Process"));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Process Name");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 30, 130, 15);

        jtProcessName.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtProcessName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtProcessNameActionPerformed(evt);
            }
        });
        jPanel1.add(jtProcessName);
        jtProcessName.setBounds(140, 30, 90, 20);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Process Tipology");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 60, 130, 15);

        jcProcessType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcProcessTypeActionPerformed(evt);
            }
        });
        jPanel1.add(jcProcessType);
        jcProcessType.setBounds(140, 60, 240, 20);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Schedulate Time");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 90, 130, 15);

        jtOra.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtOra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtOra.setText("00");
        jPanel1.add(jtOra);
        jtOra.setBounds(140, 90, 30, 19);

        jtMin.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtMin.setText("00");
        jPanel1.add(jtMin);
        jtMin.setBounds(170, 90, 30, 19);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Process Link");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 180, 130, 15);
        jPanel1.add(jcProcessLink);
        jcProcessLink.setBounds(140, 180, 100, 20);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("Executable");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 120, 130, 15);

        jtProcessCmd.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jPanel1.add(jtProcessCmd);
        jtProcessCmd.setBounds(140, 120, 240, 19);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("Process Schedulate");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 150, 130, 15);

        jrTrue.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrTrue);
        jrTrue.setText("True");
        jrTrue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jrTrue.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrTrue.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jrTrue.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrTrue.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jPanel1.add(jrTrue);
        jrTrue.setBounds(140, 150, 60, 25);

        jrFalse.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup1.add(jrFalse);
        jrFalse.setText("False");
        jrFalse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jrFalse.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrFalse.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jrFalse.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrFalse.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jrFalse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrFalseActionPerformed(evt);
            }
        });
        jPanel1.add(jrFalse);
        jrFalse.setBounds(210, 150, 60, 25);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 480, 400, 180);

        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Select Switch"));
        jPanel2.setLayout(null);

        jCB_switch.setBackground(new java.awt.Color(230, 230, 230));
        jCB_switch.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jCB_switch.setMaximumSize(new java.awt.Dimension(32767, 20));
        jCB_switch.setMinimumSize(new java.awt.Dimension(100, 20));
        jCB_switch.setPreferredSize(new java.awt.Dimension(100, 20));
        jCB_switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_switchActionPerformed(evt);
            }
        });
        jPanel2.add(jCB_switch);
        jCB_switch.setBounds(150, 20, 100, 20);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 170, 400, 60);

        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg"))); // NOI18N
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setPreferredSize(new java.awt.Dimension(100, 22));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg"))); // NOI18N
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg"))); // NOI18N
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        getContentPane().add(jB_close);
        jB_close.setBounds(350, 670, 60, 22);

        jButton3.setBackground(new java.awt.Color(230, 230, 230));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(90, 670, 50, 20);

        jButton4.setBackground(new java.awt.Color(230, 230, 230));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete.jpg"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_press.jpg"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_press.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(160, 670, 50, 20);

        jButton6.setBackground(new java.awt.Color(230, 230, 230));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_new.jpg"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_new_press.jpg"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_new_over.jpg"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(10, 670, 70, 20);

        jB_reset.setBackground(new java.awt.Color(230, 230, 230));
        jB_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset.jpg"))); // NOI18N
        jB_reset.setBorder(null);
        jB_reset.setBorderPainted(false);
        jB_reset.setContentAreaFilled(false);
        jB_reset.setFocusPainted(false);
        jB_reset.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset_press.jpg"))); // NOI18N
        jB_reset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset_over.jpg"))); // NOI18N
        jB_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_resetActionPerformed(evt);
            }
        });
        getContentPane().add(jB_reset);
        jB_reset.setBounds(220, 670, 80, 20);

        jP_system.setBackground(new java.awt.Color(230, 230, 230));
        jP_system.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "System"));
        jP_system.setLayout(null);

        jButton5.setBackground(new java.awt.Color(230, 230, 230));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start_system.jpg"))); // NOI18N
        jButton5.setToolTipText("Restart system");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setEnabled(false);
        jButton5.setFocusPainted(false);
        jButton5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start_system_press.jpg"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start_system_over.jpg"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jP_system.add(jButton5);
        jButton5.setBounds(140, 20, 120, 22);

        getContentPane().add(jP_system);
        jP_system.setBounds(10, 50, 400, 50);

        jP_process.setBackground(new java.awt.Color(230, 230, 230));
        jP_process.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Process"));
        jP_process.setLayout(null);

        jButton1.setBackground(new java.awt.Color(230, 230, 230));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start1.jpg"))); // NOI18N
        jButton1.setToolTipText("Start all process");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start1_press.jpg"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_start1_over.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jP_process.add(jButton1);
        jButton1.setBounds(240, 20, 120, 22);

        jButton2.setBackground(new java.awt.Color(230, 230, 230));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_stop_schema1.jpg"))); // NOI18N
        jButton2.setToolTipText("Stop all process");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_stop_schema3.jpg"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_stop_schema2.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jP_process.add(jButton2);
        jButton2.setBounds(60, 20, 130, 20);

        getContentPane().add(jP_process);
        jP_process.setBounds(10, 110, 400, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_resetActionPerformed
        // Add your handling code here:
        reset();
    }//GEN-LAST:event_jB_resetActionPerformed

    private void jCB_switchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_switchActionPerformed

        flagButton=0;
       
        if( !((String)jCB_switch.getSelectedItem()).equals(strCentrale) )
        {
            enableRestartAll(false);
            strCentrale=(String)jCB_switch.getSelectedItem();
            if(CS!=null)
            {
                CS.stop();
            }
                
            CS=new checkSystem(this,strCentrale);
            CS.start();
            //System.out.println("START Check at: "+(String)jCB_switch.getSelectedItem());
        }
        setProcessforSwitch((String)jCB_switch.getSelectedItem());
    }//GEN-LAST:event_jCB_switchActionPerformed

    private void jcProcessTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcProcessTypeActionPerformed
        // Add your handling code here:
        if(jcProcessType.getSelectedIndex()==5)
        {
            jtOra.setEnabled(true);
            jtMin.setEnabled(true);
        }else
        {
            jtOra.setEnabled(false);
            jtMin.setEnabled(false);
        }    
    }//GEN-LAST:event_jcProcessTypeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Add your handling code here:
        if(evt.getSource()==jButton2)
        {
            JOptionPane jOptionPane1 = new JOptionPane(); 
            int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm all process STOP ?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
            {
                SSM_GlobalParam.CORBAConn.SystemStop(1,SWITCH_SELECTED);
                jOptionPane1.showMessageDialog(this,"All Process stopped.","Information Messagge",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                return;
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Add your handling code here:
        //delete
        int id=listProcess.getSelectedIndex();
        if(listProcess.getSelectedIndex()==-1)
        {
                warningProblem("Process not selected","Error Message");
                return;
        }
        
        if(jrTrue.isSelected())
        {
                warningProblem("Process Running - Stop first","Error Message");
                return;
        }
        
        JOptionPane jOptionPane1 = new JOptionPane(); 
        int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm delete process "+new String(Local_AllInfoProc[id].nome_proc).trim()+"?.","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
        {
            //OK
        }
        else
        {
            return;
        }
        
        //System.out.println("nomeBlocco to delete("+id+")="+new String(Local_AllInfoProc[id].nome_proc).trim());
         
        int errore=SSM_GlobalParam.CORBAConn.deleteProcess(0,SWITCH_SELECTED, new String(Local_AllInfoProc[id].nome_proc).trim());
        
        if(errore!=1)
        {
            warningProblem2(errore,"Error Message");
        }if(errore==1)
        {
                
                
                Local_AllBlock = SSM_GlobalParam.CORBAConn.refreshConfigBloc(-1,SWITCH_SELECTED);
                String nomeBlocco = new String(Local_AllInfoProc[id].nome_proc).trim();
                //System.out.println("nomeBlocco("+id+")="+nomeBlocco);
                int index=-1;
                for(int i=0;i<Local_AllBlock.length;i++)
                {
                    String nomeBloccoAppo = new String(Local_AllBlock[i].nome).trim();
                    if(nomeBlocco.equals(nomeBloccoAppo))
                    {
                        index=Local_AllBlock[i].block;
                        break;
                    }
                }
                
            /*    
            if(index!=-1)
            {
                
                jOptionPane1 = new JOptionPane(); 
                YES_NO = jOptionPane1.showConfirmDialog(this,"Delete "+nomeBlocco+" block["+index+"] message?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            }*/
                
            flagButton=0;
            setProcessforSwitch((String)jCB_switch.getSelectedItem());
            jtProcessName.setText("");
            jcProcessType.setSelectedIndex(0);
            jtProcessCmd.setText("");
            jrFalse.setSelected(true);
            jtOra.setText("");
            jtMin.setText("");
            flagNew=false;
              
            if(index!=-1)
            {    
                //if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
                //{
                    int errorDel=SSM_GlobalParam.CORBAConn.deleteBlockLog(0,SWITCH_SELECTED,index,Local_ALLcentrali[jCB_switch.getSelectedIndex()].IdCentrale); //
                    
                    System.out.println("errorDel="+errorDel);
                    //delete block=index;    
                //}
                //else
                //{
                //    return;
                //}
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Add your handling code here: Apply
        
        flagOperazione=1;
        start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Add your handling code here:
        jPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(java.awt.Color.red, java.awt.Color.red), "Selected Process", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), java.awt.Color.red));
        flagNew=true;
        
        jtProcessName.setText("");
        jcProcessType.setSelectedIndex(0);
        jtProcessCmd.setText("");
        jrFalse.setSelected(true);
        jtOra.setText("00");
        jtMin.setText("00");
    }//GEN-LAST:event_jButton6ActionPerformed

    public void reset()
    {
        jPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Selected Process"));
        flagNew=true;
        
        jtProcessName.setText("");
        jcProcessType.setSelectedIndex(0);
        jtProcessCmd.setText("");
        jrFalse.setSelected(true);
        jtOra.setText("00");
        jtMin.setText("00");
        
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Add your handling code here:
        if(evt.getSource()==jButton1)
        {
            JOptionPane jOptionPane1 = new JOptionPane(); 
            int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm all process START?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
            {
                int iRet=SSM_GlobalParam.CORBAConn.SystemStart(1,SWITCH_SELECTED);
		setCursor(cursor_wait);

                if(iRet==1)
                {
                    jOptionPane1.showMessageDialog(this,"Process started.","Information Messagge",JOptionPane.INFORMATION_MESSAGE);
                }else
                {
                    String errore="Errore Start Process";
                    jOptionPane1.showMessageDialog(this,errore,"Error Messagge",JOptionPane.ERROR_MESSAGE);
                }
		setCursor(cursor_hand);
            }
            else
            {
                return;
            }
        }else if(evt.getSource()==jButton5)
        {
            JOptionPane jOptionPane1 = new JOptionPane(); 
            int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm system RESTART?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            //System.out.println("YES_NO="+YES_NO);
            if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
            {
	    	setCursor(cursor_wait);

		int iRet=SSM_GlobalParam.CORBAConn.SystemStart(0,SWITCH_SELECTED);
                //System.out.println("iRat="+iRet);
                if(iRet==1)
                {
                    jOptionPane1.showMessageDialog(this,"System RESTARTED. Wait some minutes. ","Information Messagge",JOptionPane.INFORMATION_MESSAGE);
                    sistemstart=true;
                    enableRestartAll(false);
                }else
                {
                    String errore="Error: Indeterminate";
                    switch(iRet)
                    {
                        case 26:
                            errore="Error: RESTART System";
                            break;
                            
                        case 5:
                            errore="Error: Process NOT present";
                            break;
                        
                        case 27:
                            errore="Error: STOP System";
                            break;
                    }

                    jOptionPane1.showMessageDialog(this,errore,"Error Messagge",JOptionPane.ERROR_MESSAGE);
                }
		setCursor(cursor_hand);
            }
            else
            {
                return;
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void jrFalseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrFalseActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jrFalseActionPerformed

    private void jtProcessNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtProcessNameActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jtProcessNameActionPerformed

    private void listProcessKeyReleased(java.awt.event.KeyEvent evt) {
        // Add your handling code here:
        setFillProcessMask(listProcess.getSelectedIndex());
    }

    private void listProcessMousePressed(java.awt.event.MouseEvent evt) {
        // Add your handling code here:
        setFillProcessMask(listProcess.getSelectedIndex());
    }
    
    private void setFillProcessMask(int id)
    {
        //System.out.println("ID: "+id);
        if(id==-1)
        {
            warningProblem("No selected switch","Error Message");
            return;
        }
        
        if(flagNew)
        {
            flagNew=false;
            jPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Selected Process"));
        }
        
        
        jtProcessName.setText(new String(Local_AllInfoProc[id].nome_proc).trim());
	if(Local_AllInfoProc[id].tipo_proc<=5)
        {
		jcProcessType.setSelectedIndex(Local_AllInfoProc[id].tipo_proc);
	}else
	{
		jcProcessType.setSelectedIndex(6);
	}

        jtProcessCmd.setText(new String(Local_AllInfoProc[id].cmd_start).trim());
        if(Local_AllInfoProc[id].flag_sched==1)
        {
            jrTrue.setSelected(true);
        }
        else
        {
            jrFalse.setSelected(true);
        }
        jtOra.setText(""+Local_AllInfoProc[id].tempo_proc.ore);
        jtMin.setText(""+Local_AllInfoProc[id].tempo_proc.min);
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
    
    private void start()
    {    
        th = null;        
        th = new Thread(this,"refresh_ConfigProcess");
        th.start();       
    }
    
    public void run()
    {
        int numBlockFree=-1;
        JOptionPane jOptionPane1 = new JOptionPane(); 
        Color c;
        
        this.setCursor(cursor_wait);
        setEnabled_GUI(false,cursor_wait);
        
        switch(flagOperazione)
        {
            case 0:
            {
                if(flagButton==0)
                {
                jcProcessLink.removeAllItems();
                jcProcessLink.addItem("");

                Local_AllInfoProc = SSM_GlobalParam.CORBAConn.refreshConfigProcess(0,SWITCH_SELECTED);

                Local_AllBlock = SSM_GlobalParam.CORBAConn.refreshConfigBloc(-1,SWITCH_SELECTED);

                if(Local_AllInfoProc !=null)
                {
                    for(int i=0;i<Local_AllInfoProc.length;i++)
                    {
                        String NomeProcesso = new String(Local_AllInfoProc[i].nome_proc).trim();

                        if(DEBUG)
                        {
				System.out.println("NomeProcesso= "+NomeProcesso);
				System.out.println("Tipo Processo= "+Local_AllInfoProc[i].tipo_proc);
				System.out.println("BLOCCO Processo= "+Local_AllInfoProc[i].tipo_proc);
                        }

                        int len=NomeProcesso.length();
                        for(int j=len;j<8;j++)
                        {
                            NomeProcesso=NomeProcesso+" ";
                        }

			String tipoProcesso = "Undefined";
			if(Local_AllInfoProc[i].tipo_proc<=5)
			{
				tipoProcesso = ""+SSM_GlobalParam.typeProc[Local_AllInfoProc[i].tipo_proc];
			}
                        c=Color.red;

                        for(int j=0;j<Local_AllBlock.length;j++)
                        {
                            String nomeBloccoAppo = new String(Local_AllBlock[j].nome).trim();
                            //System.out.println(""+NomeProcesso.trim()+"=="+nomeBloccoAppo);
                            if(NomeProcesso.trim().equals(nomeBloccoAppo))
                            {
                                //System.out.println("Trovato");
                                c=Color.black;
                                break;
                            }
                        }

                        if(Local_AllInfoProc[i].flag_sched==1)
                        {
                            jcProcessLink.addItem(NomeProcesso);
                            listProcess.addElement("run_proc.png","run_proc.png","("+SWITCH_SELECTED+") - "+NomeProcesso+"  "+tipoProcesso,c);
                        }
                        else
                            listProcess.addElement("stop_proc.png","stop_proc.png","("+SWITCH_SELECTED+") - "+NomeProcesso+"  "+tipoProcesso,c);
                    }
                }else
                    System.out.println("******* Local_AllInfoProc[] is null ******** ");
                }
            }break;
            
            case 1:
            {
                jPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Selected Process"));
                String s1="";
                String s2="";
                int id=listProcess.getSelectedIndex();
                int index=0;
                int idProcessoDaModificare=-1;
                
                int flag_sched=0;
                int tipo_proc=0;
                int ore=0;
                int min=0;
                String nome_proc="";
                String cmd_start="";
                    
                if(id!=-1)
                {
                    flag_sched=Local_AllInfoProc[id].flag_sched;
                    tipo_proc=Local_AllInfoProc[id].tipo_proc;
                    ore=Local_AllInfoProc[id].tempo_proc.ore;
                    min=Local_AllInfoProc[id].tempo_proc.min;
                    nome_proc=new String(Local_AllInfoProc[id].nome_proc).trim();
                    cmd_start=new String(Local_AllInfoProc[id].cmd_start).trim();
                }
                
                for(int i=0;i<Local_AllInfoProc.length;i++)
                {
                        String NomeProcesso = new String(Local_AllInfoProc[i].nome_proc).trim();

                        if(NomeProcesso.equals(jtProcessName.getText()))
                        {
                            idProcessoDaModificare=i;
                            break;
                        }
                }

                //System.out.println("New["+id+"]= "+jtProcessName.getText() );
                //System.out.println("idProcessoDaModificare= "+idProcessoDaModificare );

                for(int i=0;i<Local_AllBlock.length;i++)
                {
                    String nomeBloccoAppo = new String(Local_AllBlock[i].nome).trim();
                    if(nomeBloccoAppo.equals(jtProcessName.getText()))
                    {
                        if(id!=idProcessoDaModificare)
                        {
                            warningProblem("Process name is present","Error Message");
                            setEnabled_GUI(true,cursor_hand);
                            this.setCursor(cursor_default);
                            
                            Local_AllInfoProc[id].flag_sched=flag_sched;
                            Local_AllInfoProc[id].tipo_proc=tipo_proc;
                            Local_AllInfoProc[id].tempo_proc.ore=ore;
                            Local_AllInfoProc[id].tempo_proc.min=min;
                            Local_AllInfoProc[id].nome_proc=SSM_GlobalParam.set_String_toChar(nome_proc,11);
                            Local_AllInfoProc[id].cmd_start=SSM_GlobalParam.set_String_toChar(cmd_start,50);
                            setFillProcessMask(id);
                        
                            return;
                        }
                    }
                }

                if(flagNew==false)
                {
                    if(id==-1)
                    {
                        warningProblem("Process not selected","Error Message");
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                            
                        return;
                    }

                    s1=jtProcessName.getText();
                    s2=new String(Local_AllInfoProc[id].nome_proc).trim();

                    if(!s1.equals(s2))
                    {

                        String nomeBlocco = new String(Local_AllInfoProc[id].nome_proc).trim();
                        for(int i=0;i<Local_AllBlock.length;i++)
                        {
                            //System.out.println("ID BLOCCO ["+i+"]= "+Local_AllBlock[i].block );
                            String nomeBloccoAppo = new String(Local_AllBlock[i].nome).trim();
                            if(nomeBlocco.equals(nomeBloccoAppo))
                            {
                                index=Local_AllBlock[i].block;
                                break;
                            }
                        }

                        if(DEBUG)
                        {
                            System.out.println("SORG = "+s2);
                            System.out.println("DEST = "+s1);
                            System.out.println("BLOCCO DA MODIFICARE = "+index);
                        }
                    }

                }

                if(jtProcessName.getText().equals(""))
                {
                        warningProblem("Insert Name Process","Error Message");
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                        return;
                }

                if(jtOra.getText().equals(""))
                {
                        warningProblem("Insert Hour","Error Message");
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                        
                        Local_AllInfoProc[id].flag_sched=flag_sched;
                        Local_AllInfoProc[id].tipo_proc=tipo_proc;
                        Local_AllInfoProc[id].tempo_proc.ore=ore;
                        Local_AllInfoProc[id].tempo_proc.min=min;
                        Local_AllInfoProc[id].nome_proc=SSM_GlobalParam.set_String_toChar(nome_proc,11);
                        Local_AllInfoProc[id].cmd_start=SSM_GlobalParam.set_String_toChar(cmd_start,50);
                        setFillProcessMask(id);
                        
                        return;
                }

                if(jtMin.getText().equals(""))
                {
                        warningProblem("Insert Minute","Error Message");
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                        
                        Local_AllInfoProc[id].flag_sched=flag_sched;
                        Local_AllInfoProc[id].tipo_proc=tipo_proc;
                        Local_AllInfoProc[id].tempo_proc.ore=ore;
                        Local_AllInfoProc[id].tempo_proc.min=min;
                        Local_AllInfoProc[id].nome_proc=SSM_GlobalParam.set_String_toChar(nome_proc,11);
                        Local_AllInfoProc[id].cmd_start=SSM_GlobalParam.set_String_toChar(cmd_start,50);
                        setFillProcessMask(id);
                        
                        return;
                }

                if(jtProcessCmd.getText().equals(""))
                {
                        warningProblem("Insert Command Line","Error Message");
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                        
                        Local_AllInfoProc[id].flag_sched=flag_sched;
                        Local_AllInfoProc[id].tipo_proc=tipo_proc;
                        Local_AllInfoProc[id].tempo_proc.ore=ore;
                        Local_AllInfoProc[id].tempo_proc.min=min;
                        Local_AllInfoProc[id].nome_proc=SSM_GlobalParam.set_String_toChar(nome_proc,11);
                        Local_AllInfoProc[id].cmd_start=SSM_GlobalParam.set_String_toChar(cmd_start,50);
                        setFillProcessMask(id);
                        
                        return;
                }




                int modalita;
                int errore; 
                if(flagNew==false)
                {            
                    modalita=1;

                    if(jrTrue.isSelected())
                        Local_AllInfoProc[id].flag_sched=1;
                    else
                        Local_AllInfoProc[id].flag_sched=0;
                    
                    Local_AllInfoProc[id].tipo_proc=jcProcessType.getSelectedIndex();
                    Local_AllInfoProc[id].tempo_proc.ore=Integer.valueOf(jtOra.getText()).intValue();
                    Local_AllInfoProc[id].tempo_proc.min=Integer.valueOf(jtMin.getText()).intValue();
                    Local_AllInfoProc[id].nome_proc=SSM_GlobalParam.set_String_toChar(jtProcessName.getText(),11);
                    Local_AllInfoProc[id].cmd_start=SSM_GlobalParam.set_String_toChar(jtProcessCmd.getText(),50);


                    if(DEBUG)
                    {
                        System.out.println("Local_AllInfoProc["+id+"].flag_sched="+Local_AllInfoProc[id].flag_sched );
                        System.out.println("Local_AllInfoProc["+id+"].flag_attiv="+Local_AllInfoProc[id].flag_attiv );
                        System.out.println("Local_AllInfoProc["+id+"].num_proc="+Local_AllInfoProc[id].num_proc );
                        for(int i=0;i<Local_AllInfoProc[id].legami.length;i++)
                        {
                            System.out.println("Local_AllInfoProc["+id+"].legami="+Local_AllInfoProc[id].legami[i] );
                        }
                        System.out.println("Local_AllInfoProc["+id+"].tipo_proc="+Local_AllInfoProc[id].tipo_proc );
                        System.out.println("Local_AllInfoProc["+id+"].LastRestartTime="+Local_AllInfoProc[id].LastRestartTime );
                        System.out.println("Local_AllInfoProc["+id+"].NumRestartTimes="+Local_AllInfoProc[id].NumRestartTimes );
                        System.out.println("Local_AllInfoProc["+id+"].ProcessPID=" +Local_AllInfoProc[id].ProcessPID );
                        System.out.println("Local_AllInfoProc["+id+"].nome_proc,=" +new String(Local_AllInfoProc[id].nome_proc).trim() );
                        System.out.println("Local_AllInfoProc["+id+"].cmd_start=" + new String(Local_AllInfoProc[id].cmd_start).trim());
                        System.out.println("Local_AllInfoProc["+id+"].nome_fep=" + new String(Local_AllInfoProc[id].nome_fep).trim() );
                        System.out.println("Local_AllInfoProc["+id+"].idFep=" + Local_AllInfoProc[id].idFep );
                    }


                    jOptionPane1 = new JOptionPane(); 
                    int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm applay changes?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);



                    if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
                    {
                        //OK
                        errore=SSM_GlobalParam.CORBAConn.writeModifyProcess(s2,modalita,SWITCH_SELECTED, Local_AllInfoProc[id]);

                        if(DEBUG)
                        {
                            System.out.println("Errore= "+errore);
                        }

                        if(index!=0)
                        {
                            CS_BLOCK_LOG[] AllBlockModify = SSM_GlobalParam.CORBAConn.refreshConfigBloc(index,SWITCH_SELECTED);
                            for(int i=0;i<AllBlockModify.length;i++)
                            {
                                String appo=new String(AllBlockModify[i].nome).trim();
                                if(DEBUG)
                                {
                                    System.out.println("----> AllBlockModify["+i+"].nome=" + appo +"    MSG= "+new String(AllBlockModify[i].msg).trim()+" ID="+AllBlockModify[i].cod_err);
                                }
                            }
                            int idCEN=0;
                            for(int i=0;i<Local_ALLcentrali.length;i++)
                            {
                                String strCEN=new String(Local_ALLcentrali[i].Descrizione).trim();
                                if(strCEN.equals(SWITCH_SELECTED))
                                {
                                    idCEN=Local_ALLcentrali[i].IdCentrale;
                                    break;
                                }

                            }

                            CS_BLOCK_LOG[] AllBlockNEW=new CS_BLOCK_LOG[100]; 


                            for(int i=0;i<AllBlockNEW.length;i++)
                            {
                                    AllBlockNEW[i]=new CS_BLOCK_LOG();
                                    AllBlockNEW[i].item=AllBlockModify[0].item;
                                    AllBlockNEW[i].block=AllBlockModify[0].block;
                                    AllBlockNEW[i].cod_err=i;
                                    AllBlockNEW[i].urgenza=0;
                                    AllBlockNEW[i].priorita=0;
                                    AllBlockNEW[i].msg=SSM_GlobalParam.set_String_toChar("NOT DEFINED",132);
                                    AllBlockNEW[i].nome=SSM_GlobalParam.set_String_toChar(jtProcessName.getText(),lenPName);
                                    AllBlockNEW[i].idFep=AllBlockNEW[0].idFep;
                            }

                            for(int i=0;i<AllBlockModify.length;i++)
                            {
                                    AllBlockNEW[AllBlockModify[i].cod_err]=AllBlockModify[i];
                                    AllBlockNEW[AllBlockModify[i].cod_err].nome=SSM_GlobalParam.set_String_toChar(jtProcessName.getText(),lenPName);
                            }

                            if(DEBUG)
                            {
                                for(int i=0;i<AllBlockNEW.length;i++)
                                {
                                    System.out.println("AllBlockNEW["+i+"].nome=" + new String(AllBlockNEW[i].nome).trim() +"    MSG= "+new String(AllBlockNEW[i].msg).trim()+" ID="+AllBlockNEW[i].cod_err);
                                }
                            }

                            if(DEBUG)
                            {
                                System.out.println("Cancello blocco = "+index);
                            }

                            int errorDel=SSM_GlobalParam.CORBAConn.deleteBlockLog(0,SWITCH_SELECTED,index,idCEN); 


                            if(DEBUG)
                            {
                                System.out.println("Ricreo blocco = "+index);
                            }
                            int erroreModify=SSM_GlobalParam.CORBAConn.writeModifyLog(0,SWITCH_SELECTED, AllBlockNEW);

                        }else
                        {
                            if(DEBUG)
                            {
                                System.out.println("Blocco messaggi non definito");
                            }
                        }
                    }
                    else
                    {
                        Local_AllInfoProc[id].flag_sched=flag_sched;
                        Local_AllInfoProc[id].tipo_proc=tipo_proc;
                        Local_AllInfoProc[id].tempo_proc.ore=ore;
                        Local_AllInfoProc[id].tempo_proc.min=min;
                        Local_AllInfoProc[id].nome_proc=SSM_GlobalParam.set_String_toChar(nome_proc,11);
                        Local_AllInfoProc[id].cmd_start=SSM_GlobalParam.set_String_toChar(cmd_start,50);
                        setFillProcessMask(id);
                        
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                        return;
                    }
                }
                else   
                {
                    modalita=2;

                    CS_INFO_PROC process=new CS_INFO_PROC();

                    if(jrTrue.isSelected())
                        process.flag_sched=1;
                    else
                        process.flag_sched=0;

                    process.tempo_proc=new tempoRis();
                    process.tipo_proc=jcProcessType.getSelectedIndex();
                    process.tempo_proc.ore=Integer.valueOf(jtOra.getText()).intValue();
                    process.tempo_proc.min=Integer.valueOf(jtMin.getText()).intValue();
                    process.nome_proc=SSM_GlobalParam.set_String_toChar(jtProcessName.getText(),11);
                    process.cmd_start=SSM_GlobalParam.set_String_toChar(jtProcessCmd.getText(),50);
                    process.nome_fep=SSM_GlobalParam.set_String_toChar(SWITCH_SELECTED,10);
                    process.legami=new int[10];
                    process.flag_attiv=1;

                    jOptionPane1 = new JOptionPane(); 
                    int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm insert process?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
                    {
                        errore=SSM_GlobalParam.CORBAConn.writeModifyProcess(jtProcessName.getText(),modalita,SWITCH_SELECTED, process);
                    }
                    else
                    {
                        reset();
                        setEnabled_GUI(true,cursor_hand);
                        this.setCursor(cursor_default);
                        return;

                    }



                }
                
                if(errore!=1)
                {
                    warningProblem2(errore,"Error Message");
                }

                flagButton=0;
                setProcessforSwitch((String)jCB_switch.getSelectedItem());
                jtProcessName.setText("");
                jcProcessType.setSelectedIndex(0);
                jtProcessCmd.setText("");
                jrFalse.setSelected(true);
                jtOra.setText("");
                jtMin.setText("");
                flagNew=false; 
                
            }break;
        }
	

	listProcess.removeKeyListener(evento);
	listProcess.removeMouseListener(evento1);
        listProcess.updateUI();
	listProcess.addKeyListener(evento);
        listProcess.addMouseListener(evento1);

	//listProcess.setSelectedIndex(0);

        setEnabled_GUI(true,cursor_hand);
        this.setCursor(cursor_default);
    }
    
    private void setEnabled_GUI(boolean flag,java.awt.Cursor curAppo)
    {        
        jCB_switch.setCursor(curAppo);
        jB_close.setCursor(curAppo);
        //jB_searchProc.setCursor(curAppo);
        listProcess.setCursor(curAppo);
        
        jButton3.setCursor(curAppo);
        jButton4.setCursor(curAppo);  
        jButton6.setCursor(curAppo);
        jB_reset.setCursor(curAppo);
        
        
        jCB_switch.setEnabled(flag);
        jB_close.setEnabled(flag);
        //jB_searchProc.setEnabled(flag);   
        
        jButton3.setEnabled(flag);   
        jButton4.setEnabled(flag);     
        jButton6.setEnabled(flag);   
        jB_reset.setEnabled(flag);   
        jScroll_process.setEnabled(flag);   
        listProcess.setEnabled(flag);  
    }
    
    private void setProcessforSwitch(String SW)
    {
        //jScroll_process.setEnabled(false);
        //listProcess.setEnabled(false);
        //listProcess.updateUI();
        if(listProcess != null)
        {
            SWITCH_SELECTED = SW;

            	listProcess.removeKeyListener(evento);
		listProcess.removeMouseListener(evento1);
		listProcess.removeAll();
        	listProcess.updateUI();
		listProcess.addKeyListener(evento);
        	listProcess.addMouseListener(evento1);
            
            //processDetails = null;
            
            jScroll_process.getVerticalScrollBar().setValue(0);
            jScroll_process.getHorizontalScrollBar().setValue(0);
            if(CS!=null)
                CS.setSwitch(SWITCH_SELECTED);
            flagOperazione=0;
            start();
        }
        
        jtProcessName.setText("");
        jcProcessType.setSelectedIndex(0);
        jtProcessCmd.setText("");
        jrFalse.setSelected(true);
        jtOra.setText("00");
        jtMin.setText("00");
    }
    
    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        // Add your handling code here:
        closeFrame();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm
    
    private void closeFrame()
    {
        CS.stop();
        
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jB_close;
    private javax.swing.JButton jB_reset;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jCB_switch;
    private javax.swing.JLabel jL_title;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jP_process;
    private javax.swing.JPanel jP_system;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScroll_process;
    private javax.swing.JComboBox jcProcessLink;
    private javax.swing.JComboBox jcProcessType;
    private javax.swing.JRadioButton jrFalse;
    private javax.swing.JRadioButton jrTrue;
    private javax.swing.JTextField jtMin;
    private javax.swing.JTextField jtOra;
    private javax.swing.JTextField jtProcessCmd;
    private javax.swing.JTextField jtProcessName;
    // End of variables declaration//GEN-END:variables
    private DATA_CENTRALI[] Local_ALLcentrali   = null;
    private String SWITCH_SELECTED              = "";
    private Thread th                           = null;
    private CS_INFO_PROC[] Local_AllInfoProc    = null;
    
    private SSM_JListIcon listProcess            = null;
    private SSM_IconPool IcPool                  = null;
    
    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Cursor cursor_wait         = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor cursor_default      = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    
    
    private boolean flagNew                     = false;
    private int flagButton                      = 0;
    
    private void warningProblem(String str1,String str2)
    {
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE);

            //warning.showConfirmDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE,JOptionPane.QUESTION_MESSAGE);
    }
    
    private void warningProblem2(int id,String str2)
    {
            String str1="Error: NOT DEFINED";
            switch(id)
            {
                case 5: 
                {
                    str1="Error process not present";
                }break;
                
                case 2: 
                {
                    str1="Error process active";
                }break;
                
                case 3: 
                {
                    str1="Error process not present";
                }break;
                
                case 6: 
                {
                    str1="Error process command";
                }break;
                
                case 7: 
                {
                    str1="Error process time";
                }break;
                
                case 8: 
                {
                    str1="Error process type";
                }break;
                
                case 9: 
                {
                    str1="Error process name";
                }break;
                
                case 10: 
                {
                    str1="Error configmonitorserver: memory error";
                }break;
                
                case 11: 
                {
                    str1="Error configmonitorserver: executable not present";
                }break;
            }
            
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE);

            //warning.showConfirmDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE,JOptionPane.QUESTION_MESSAGE);
    }
    
}
