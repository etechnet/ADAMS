/*
 * SSM_JF_ACQPebMgr.java
 *
 * Created on 7 febbraio 2006, 14.34
 *
 *
 * @author   Raffaele Ficcadenti raffaele.ficcadenti@e-tech.net
 */

import javax.swing.*;
import java.awt.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.SSM.*;

public class SSM_JF_ACQPebMgr extends javax.swing.JFrame implements Runnable {

    public boolean                                  DEBUG               = false;
    public boolean                                  ONLY_MIP            = true;
    private Cursor                                  Cur_default         = new Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait            = new Cursor(java.awt.Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand            = new Cursor(java.awt.Cursor.HAND_CURSOR);
    private DATA_CENTRALI[]                         Local_ALLcentrali   = null;
    private String                                  SWITCH_SELECTED     = "";
    private Thread                                  th                  = null;
    private java.awt.event.ActionListener           evento              = null;
    private ACQ_PEB_MGR                             acqPebMgr           = null;
    private ACQ_PEB_MGR                             acqPebMgrOUT        = new ACQ_PEB_MGR();

    private boolean                                 startWithRestart        = false;
    private boolean                                 cleanAll                = false;
    private boolean                                 restartWithCacheFlush   = false;
    private boolean                                 getInterfaceERROR       = false;

    private int                                     _CAT_NAME_DIM           = ACQPebMGRServer.eCAT_NAME_DIM;
    private int                                     _PEB_NAME_DIM           = ACQPebMGRServer.ePEB_NAME_DIM;
    private int                                     FLAG_THREAD             = 0;

    /** Creates new form SSM_JF_ACQPebMgr */
    public SSM_JF_ACQPebMgr(DATA_CENTRALI[] ALLcentrali)
    {
        Local_ALLcentrali=ALLcentrali;

        initComponents();

        jT_cdr.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,10));
        jT_renaming.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,10));

        for(int i=0; i<Local_ALLcentrali.length; i++)
        {
	      String nomeCen=(new String(Local_ALLcentrali[i].Descrizione).trim()).toUpperCase();
		if (nomeCen.indexOf("P")!=-1)
		{
	      		jCsw.addItem(nomeCen);
	 	}
//            if ((SSM_GlobalParam.rmp3i).equals("YES"))
//            {
//                if (!((new String(ALLcentrali[i].Descrizione).trim()).equals("RMP3I")))	// scarta tutto tranne RMP3I
//            			continue;
//
//                String nomeCen=(new String(Local_ALLcentrali[i].Descrizione).trim()).toUpperCase();
//                if(DEBUG)
//                {
//                    System.out.println(nomeCen);
//                }
//                if(ONLY_MIP)
//                {
//                    if(nomeCen.indexOf("MIP")>=0)
//                        jCsw.addItem(nomeCen);
//                }else
//                {
//                    jCsw.addItem(nomeCen);
//                }
//            }
//            else
//            {
//                if (((new String(ALLcentrali[i].Descrizione).trim()).equals("RMP3I")))	// scarta RMP3I
//            			continue;
//
//                String nomeCen=(new String(Local_ALLcentrali[i].Descrizione).trim()).toUpperCase();
//                if(DEBUG)
//                {
//                    System.out.println(nomeCen);
//                }
//                if(ONLY_MIP)
//                {
//                    if(nomeCen.indexOf("MIP")>=0)
//                        jCsw.addItem(nomeCen);
//                }else
//                {
//                    jCsw.addItem(nomeCen);
//                }
//            }
        }

        getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        setCursor2(Cur_hand);
        setFont();
        SSM_GlobalParam.setCenteredFrame(this,630,480);

        evento=(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCcatActionPerformed(evt);
                }
            });

        jCcat.addActionListener(evento);
    }


    public void enableGUI(boolean state)
    {
        if(state==true)
            setCursor2(Cur_hand);
        else
            setCursor2(Cur_wait);

        jbRefresh.setEnabled(state);
        if(jR_r1.isSelected())
        {
            jbApply.setEnabled(state);
        }else
        {
            jbApply.setEnabled(false);
        }
        jbClose.setEnabled(state);
        jR_r1.setEnabled(state);
        jR_r2.setEnabled(false);
        jR_r3.setEnabled(false);
        jCcat.setEnabled(state);
        jCsw.setEnabled(state);
        jT_cdr.setEnabled(state);
        //jR_AutoRefresh.setEnabled(state);
        //jT_renaming.setEnabled(state);

        this.repaint();
    }

    public void resetInterface()
    {
        jR_r1.setSelected(false);
        jR_r2.setSelected(false);
        jR_r3.setSelected(false);
        jR_AutoRefresh.setSelected(false);
        jR_r2.setEnabled(false);
        jR_r3.setEnabled(false);
    }

    public void setFont()
    {
    }

    public void setCursor2(Cursor c)
    {
        jR_r1.setCursor(c);
        jR_r2.setCursor(c);
        jR_r3.setCursor(c);
        jbApply.setCursor(c);
        jbRefresh.setCursor(c);
        jbClose.setCursor(c);
        jCsw.setCursor(c);
        jCcat.setCursor(c);
        jT_processing.setCursor(c);
        jT_cdr.setCursor(c);
        jT_renaming.setCursor(c);
        jR_AutoRefresh.setCursor(Cur_hand);
    }

    private void start()
    {
        th = null;
        th = new Thread(this,"SSM_JF_ACQPebMgr");
        th.start();
    }

    private void stop()
    {
		    if(th!=null)
				{
						try
						{
			    		//th.stop();
			    		th.interrupt();
		    		}
		    		catch (Exception e)
		    		{
		    			System.out.println("err. stop del Thread SSM_JF_ACQPebMgr.java");
		    		}

		        	th = null;
				}
    }

    public void run()
    {
        setCursor(Cur_wait);
        enableGUI(false);

        switch(FLAG_THREAD)
        {
            case 0:
            {
                jL_help.setText("PLEASE WAIT, Dawnloading catalog ...");

                String str="<Select One>";

                SWITCH_SELECTED=(String)jCsw.getSelectedItem();
                if(DEBUG)
                {
                    System.out.println(SWITCH_SELECTED);
                }

                acqPebMgr=SSM_GlobalParam.CORBAConn.refreshACQPebMGR(SWITCH_SELECTED);  //per test

                int len=acqPebMgr.processedVector.length;

                jCcat.removeActionListener(evento);
                jCcat.removeAllItems();
                jCcat.addItem(str);
                if(acqPebMgr!=null)
                {
                    for(int i=0;i<len;i++)
                    {
                        str=(new String(acqPebMgr.processedVector[i].name)).trim();
                        //System.out.println("CAT: "+str);
                        jCcat.addItem(str);
                    }

                    jT_processing.setText((new String(acqPebMgr.processing).trim()));
                    jT_cdr.setText(""+acqPebMgr.drFileProg);
                    jT_renaming.setText(""+acqPebMgr.drFileRemaining);
                    jR_r1.setSelected(acqPebMgr.startWithRestart);
                    jR_r3.setSelected(acqPebMgr.restartWithCacheFlush);
                    startWithRestart=acqPebMgr.startWithRestart;
                    restartWithCacheFlush=acqPebMgr.restartWithCacheFlush;

                }
                jCcat.addActionListener(evento);

                if(acqPebMgr.acqCatPid>0)
                {
                    if(acqPebMgr.pauseAcqCat)
                    {
                        jLabel14.setText("Paused");
                    }else
                    {
                        jLabel14.setText("<html><center><b><font color=#00FF00>Running (maybe)</font></b>");
                    }
                }else
                {
                    jLabel14.setText("<html><center><b><font color=#ff0000>Halted</font></b>");
                }

                jL_help.setText("Catalog downloaded for switch "+SWITCH_SELECTED+" ...");

                th=null;
            }break;

            case 1:
            {
                jR_AutoRefresh.setForeground(java.awt.Color.green);
                while(jR_AutoRefresh.isSelected())
                {

                    System.out.println("Auto refresh.");

                    //****************************************************************

                    jL_help.setText("PLEASE WAIT, Dawnloading catalog ...");

                    String str="<Select One>";

                    SWITCH_SELECTED=(String)jCsw.getSelectedItem();
                    if(DEBUG)
                    {
                        System.out.println(SWITCH_SELECTED);
                    }

                    acqPebMgr=SSM_GlobalParam.CORBAConn.refreshACQPebMGR(SWITCH_SELECTED);  //per test

                    int len=acqPebMgr.processedVector.length;

                    jCcat.removeActionListener(evento);
                    jCcat.removeAllItems();
                    jCcat.addItem(str);
                    if(acqPebMgr!=null)
                    {
                        for(int i=0;i<len;i++)
                        {
                            str=(new String(acqPebMgr.processedVector[i].name)).trim();
                            //System.out.println("CAT: "+str);
                            jCcat.addItem(str);
                        }

                        jT_processing.setText((new String(acqPebMgr.processing).trim()));
                        jT_cdr.setText(""+acqPebMgr.drFileProg);
                        jT_renaming.setText(""+acqPebMgr.drFileRemaining);
                        jR_r1.setSelected(acqPebMgr.startWithRestart);
                        jR_r3.setSelected(acqPebMgr.restartWithCacheFlush);
                        startWithRestart=acqPebMgr.startWithRestart;
                        restartWithCacheFlush=acqPebMgr.restartWithCacheFlush;

                    }
                    jCcat.addActionListener(evento);

                    if(acqPebMgr.acqCatPid>0)
                    {
                        if(acqPebMgr.pauseAcqCat)
                        {
                            jLabel14.setText("Paused");
                        }else
                        {
                            jLabel14.setText("<html><center><b><font color=#00FF00>Running (maybe)</font></b>");
                        }
                    }else
                    {
                        jLabel14.setText("<html><center><b><font color=#ff0000>Halted</font></b>");
                    }

                    jL_help.setText("Catalog downloaded for switch "+SWITCH_SELECTED+" ...");

                    //****************************************************************

                    try
                    {
                        Thread.sleep(10*1000);
                    }catch(Exception e)
                    {}
                }
                jR_AutoRefresh.setForeground(java.awt.Color.black);
                FLAG_THREAD=0;
            }
        }
        setCursor(Cur_hand);
        enableGUI(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jP_p1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jCsw = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jT_cdr = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jCcat = new javax.swing.JComboBox();
        jT_processing = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jT_renaming = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jP_p2 = new javax.swing.JPanel();
        jR_r1 = new javax.swing.JRadioButton();
        jR_r2 = new javax.swing.JRadioButton();
        jR_r3 = new javax.swing.JRadioButton();
        jP_p3 = new javax.swing.JPanel();
        jbRefresh = new javax.swing.JButton();
        jbApply = new javax.swing.JButton();
        jbClose = new javax.swing.JButton();
        jR_AutoRefresh = new javax.swing.JRadioButton();
        jL_help = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ACQPebMGR");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.EtchedBorder());
        jLabel5.setBackground(new java.awt.Color(230, 230, 230));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configuration.png")));
        jLabel5.setText("AcqPEB Shared Memory Manager ");
        jLabel5.setPreferredSize(new java.awt.Dimension(300, 32));
        jLabel5.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.ipadx = 200;
        gridBagConstraints1.ipady = 20;
        jPanel1.add(jLabel5, gridBagConstraints1);

        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;

        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.EtchedBorder());
        jLabel6.setText("PEB ID");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_p1.add(jLabel6, gridBagConstraints2);

        jCsw.setBackground(new java.awt.Color(230, 230, 230));
        jCsw.setFocusable(false);
        jCsw.setMaximumSize(new java.awt.Dimension(200, 24));
        jCsw.setMinimumSize(new java.awt.Dimension(200, 24));
        jCsw.setPreferredSize(new java.awt.Dimension(200, 24));
        jCsw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCswActionPerformed(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridwidth = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        jP_p1.add(jCsw, gridBagConstraints2);

        jLabel7.setText("Processing");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel7, gridBagConstraints2);

        jLabel10.setText("CDR file number");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel10, gridBagConstraints2);

        jT_cdr.setEditable(false);
        jT_cdr.setMaximumSize(new java.awt.Dimension(100, 19));
        jT_cdr.setMinimumSize(new java.awt.Dimension(100, 19));
        jT_cdr.setPreferredSize(new java.awt.Dimension(100, 19));
        jT_cdr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jT_cdr, gridBagConstraints2);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("MSEM Catalog History");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints2.weightx = 0.5;
        jP_p1.add(jLabel11, gridBagConstraints2);

        jCcat.setBackground(java.awt.Color.white);
        jCcat.setFocusable(false);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        jP_p1.add(jCcat, gridBagConstraints2);

        jT_processing.setEditable(false);
        jT_processing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_processingActionPerformed(evt);
            }
        });

        jT_processing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        jP_p1.add(jT_processing, gridBagConstraints2);

        jLabel12.setText("Remaining CDR");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel12, gridBagConstraints2);

        jT_renaming.setEditable(false);
        jT_renaming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jT_renaming, gridBagConstraints2);

        jLabel13.setText("Process Status");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel13, gridBagConstraints2);

        jLabel14.setBackground(java.awt.Color.white);
        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel14.setText("   ");
        jLabel14.setBorder(new javax.swing.border.EtchedBorder());
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel14, gridBagConstraints2);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jPanel1.add(jP_p1, gridBagConstraints1);

        jP_p2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;

        jP_p2.setBackground(new java.awt.Color(230, 230, 230));
        jP_p2.setBorder(new javax.swing.border.EtchedBorder());
        jR_r1.setBackground(new java.awt.Color(230, 230, 230));
        jR_r1.setText("Perform Restart from given CDR");
        jR_r1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_r1.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_r1.setFocusPainted(false);
        jR_r1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_r1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_r1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jR_r1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_r1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_r1ActionPerformed(evt);
            }
        });

        jR_r1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jR_r1, gridBagConstraints3);

        jR_r2.setBackground(new java.awt.Color(230, 230, 230));
        jR_r2.setForeground(java.awt.Color.red);
        jR_r2.setText("Clean Catalog cache and force download");
        jR_r2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_r2.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_r2.setFocusPainted(false);
        jR_r2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_r2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_r2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jR_r2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_r2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_r2.setEnabled(false);
        jR_r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_r2ActionPerformed(evt);
            }
        });

        jR_r2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jR_r2, gridBagConstraints3);

        jR_r3.setBackground(new java.awt.Color(230, 230, 230));
        jR_r3.setForeground(java.awt.Color.red);
        jR_r3.setText("Force SHM re-initialization (zeroes all)");
        jR_r3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_r3.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_r3.setFocusPainted(false);
        jR_r3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_r3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_r3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jR_r3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_r3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_r3.setEnabled(false);
        jR_r3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_r3ActionPerformed(evt);
            }
        });

        jR_r3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jR_r3, gridBagConstraints3);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        jPanel1.add(jP_p2, gridBagConstraints1);

        jP_p3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;

        jP_p3.setBackground(new java.awt.Color(230, 230, 230));
        jP_p3.setBorder(new javax.swing.border.EtchedBorder());
        jbRefresh.setForeground(java.awt.Color.red);
        jbRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.jpg")));
        jbRefresh.setBorderPainted(false);
        jbRefresh.setContentAreaFilled(false);
        jbRefresh.setFocusPainted(false);
        jbRefresh.setMaximumSize(new java.awt.Dimension(160, 30));
        jbRefresh.setMinimumSize(new java.awt.Dimension(160, 30));
        jbRefresh.setPreferredSize(new java.awt.Dimension(160, 30));
        jbRefresh.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_press.jpg")));
        jbRefresh.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_over.jpg")));
        jbRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRefreshActionPerformed(evt);
            }
        });

        jbRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p3.add(jbRefresh, gridBagConstraints4);

        jbApply.setForeground(java.awt.Color.red);
        jbApply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_c.jpg")));
        jbApply.setBorderPainted(false);
        jbApply.setContentAreaFilled(false);
        jbApply.setFocusPainted(false);
        jbApply.setMaximumSize(new java.awt.Dimension(160, 30));
        jbApply.setMinimumSize(new java.awt.Dimension(160, 30));
        jbApply.setPreferredSize(new java.awt.Dimension(160, 30));
        jbApply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_c_press.jpg")));
        jbApply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_c_over.jpg")));
        jbApply.setEnabled(false);
        jbApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbApplyActionPerformed(evt);
            }
        });

        jbApply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p3.add(jbApply, gridBagConstraints4);

        jbClose.setForeground(java.awt.Color.red);
        jbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_close.jpg")));
        jbClose.setBorderPainted(false);
        jbClose.setContentAreaFilled(false);
        jbClose.setFocusPainted(false);
        jbClose.setPreferredSize(new java.awt.Dimension(100, 22));
        jbClose.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_close_press.jpg")));
        jbClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_close_over.jpg")));
        jbClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCloseActionPerformed(evt);
            }
        });

        jbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p3.add(jbClose, gridBagConstraints4);

        jR_AutoRefresh.setBackground(new java.awt.Color(230, 230, 230));
        jR_AutoRefresh.setText("Enable auto refresh");
        jR_AutoRefresh.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_AutoRefresh.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_AutoRefresh.setFocusPainted(false);
        jR_AutoRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jR_AutoRefresh.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_AutoRefresh.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jR_AutoRefresh.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jR_AutoRefresh.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jR_AutoRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_AutoRefreshActionPerformed(evt);
            }
        });

        jR_AutoRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCswMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCswMouseExited(evt);
            }
        });

        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jP_p3.add(jR_AutoRefresh, gridBagConstraints4);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jP_p3, gridBagConstraints1);

        jL_help.setBackground(java.awt.Color.white);
        jL_help.setFont(new java.awt.Font("Dialog", 0, 10));
        jL_help.setText("Select operation.");
        jL_help.setBorder(new javax.swing.border.TitledBorder(""));
        jL_help.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weightx = 1.0;
        jPanel1.add(jL_help, gridBagConstraints1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void jR_AutoRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_AutoRefreshActionPerformed
        // Add your handling code here:
        if(jR_AutoRefresh.isSelected())
        {
            jbRefresh.setEnabled(false);
            FLAG_THREAD=1;
            start();
        }else
        {
            jbRefresh.setEnabled(true);
            jR_AutoRefresh.setForeground(java.awt.Color.black);
	    if(FLAG_THREAD==1)
	    {
  		stop();
		setCursor(Cur_hand);
        	enableGUI(true);
	    }
            FLAG_THREAD=0;
        }
    }//GEN-LAST:event_jR_AutoRefreshActionPerformed

    private void jR_r2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_r2ActionPerformed
        // Add your handling code here:

    }//GEN-LAST:event_jR_r2ActionPerformed

    private void jbRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRefreshActionPerformed
        // Add your handling code here:
        start();
        resetInterface();
    }//GEN-LAST:event_jbRefreshActionPerformed

    private void jCswMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCswMouseExited
        // Add your handling code here:
        if(th!=null)
            return;
        jL_help.setText("Select operation");
    }//GEN-LAST:event_jCswMouseExited

    private void jCswMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCswMouseEntered
        // Add your handling code here:
        if(th!=null)
            return;
        Object target = evt.getSource();

        //System.out.println(target);
        if (target == jR_AutoRefresh)
        {
            jL_help.setText("Enable/DIsable auto refresh.");
        }else
        if (target == jbClose)
        {
            jL_help.setText("Close AcqPEB Shared Memory Manager.");
        }
        else if (target == jbApply)
        {
            jL_help.setText("Apply Changes to switch "+SWITCH_SELECTED+" ***** REMEBER TO DELETE CENTRA.DAT FILE AND TO STOP TIMES *****");
        }
        else if (target == jbRefresh)
        {
            jL_help.setText("Refresh to switch "+SWITCH_SELECTED);
        }else if (target == jR_r1)
        {
            jL_help.setText("Perform Restart from given CDR to switch "+SWITCH_SELECTED);
        }
        else if (target == jR_r2)
        {
            jL_help.setText("Clean Catalog cache and force download to switch "+SWITCH_SELECTED);
        }
        else if (target == jR_r3)
        {
            jL_help.setText("Force SHM re-initialization (zeroes all) to switch "+SWITCH_SELECTED);
        }
        else if (target == jCsw)
        {
            jL_help.setText("Switch list ");
        }
        else if (target == jCcat)
        {
            jL_help.setText("Catalog list for switch"+SWITCH_SELECTED);
        }else if (target == jT_processing)
        {
            jL_help.setText("Last processing catalog for switch "+SWITCH_SELECTED);
        }
        else if (target == jLabel14)
        {
            jL_help.setText("ACQPeb status for switch "+SWITCH_SELECTED);
        }



    }//GEN-LAST:event_jCswMouseEntered

    /**/
    private void jT_processingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_processingActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jT_processingActionPerformed

    private void jR_r3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_r3ActionPerformed
        // Add your handling code here:
        if(jR_r3.isSelected())
        {
            jR_r2.setSelected(true);
            jT_processing.setText("");
        }

    }//GEN-LAST:event_jR_r3ActionPerformed

    public void getInterface()
    {
        getInterfaceERROR=false;

        if(jR_AutoRefresh.isSelected())
        {
            SSM_GlobalParam.optionPanel(this,"Disable Auto Refresh.","ERROR",1);
            getInterfaceERROR=true;
            return;
        }

        if(jR_r3.isSelected()==false)
        {
            if( (jT_processing.getText().equals("<Select One>")) || (jT_processing.getText().equals("")))
            {
                SSM_GlobalParam.optionPanel(this,"Select Catalog.","ERROR",1);
                getInterfaceERROR=true;
                return;
            }
        }


        try
        {
            acqPebMgrOUT.drFileProg=(new Integer(jT_cdr.getText())).intValue();
        }catch(Exception e)
        {
            SSM_GlobalParam.optionPanel(this,"Error in CDR file number.","ERROR",1);
            getInterfaceERROR=true;
            return;
        }

        try
        {
            acqPebMgrOUT.drFileRemaining=(new Integer(jT_renaming.getText())).intValue();
        }catch(Exception e)
        {
            SSM_GlobalParam.optionPanel(this,"Error in Remaining CDR.","ERROR",1);
            getInterfaceERROR=true;
            return;
        }

        startWithRestart=jR_r1.isSelected();
        restartWithCacheFlush=jR_r2.isSelected();
        cleanAll=jR_r3.isSelected();
        if(cleanAll)
        {
            acqPebMgrOUT.processing=SSM_GlobalParam.set_String_toChar("",_CAT_NAME_DIM);
        }else
        {
            acqPebMgrOUT.processing=SSM_GlobalParam.set_String_toChar(jT_processing.getText(),_CAT_NAME_DIM);
        }
        acqPebMgrOUT.startWithRestart=startWithRestart;
        acqPebMgrOUT.restartWithCacheFlush=restartWithCacheFlush;
        acqPebMgrOUT.pauseAcqCat=acqPebMgr.pauseAcqCat;
        acqPebMgrOUT.pauseAcqCat=acqPebMgr.twoDaysRun;
        acqPebMgrOUT.acqCatPid=acqPebMgr.acqCatPid;
        acqPebMgrOUT.PEBName=SSM_GlobalParam.set_String_toChar((new String(acqPebMgr.PEBName)).trim(),_PEB_NAME_DIM);
        acqPebMgrOUT.processedVector=new ACQ_CAT[1];
        acqPebMgrOUT.processedVector[0]=new ACQ_CAT(SSM_GlobalParam.set_String_toChar("",_CAT_NAME_DIM),false);
    }


    private void jbApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbApplyActionPerformed
        // Add your handling code here:
        getInterface();


        if(getInterfaceERROR==false)
        {
            JD_Message op = new JD_Message(this,true,"Info: WRITING TO THE SHARED MEMORY, PLEASE CONFIRM THIS ACTION *ONLY* IF YOU KNOW YOU ARE DOING!.                                                     **** REMEBER TO DELETE CENTRA.DAT FILE AND TO STOP TIMES ****","INFO",JD_Message.QUESTION_YES_NO);
            int Yes_No = op.getAnswer();



            if(Yes_No == 1)// faccio il remove
            {


                    int iRet=SSM_GlobalParam.CORBAConn.writeACQPebMGR(SWITCH_SELECTED,cleanAll,acqPebMgrOUT);
                    resetInterface();
                    jbApply.setEnabled(false);
                    System.out.println("iRet="+iRet);
                    if(iRet>0)
                    {
                        SSM_GlobalParam.optionPanel(this,"Operation done.","INFO",JD_Message.INFO);
                        start();
                    }
            }
        }else
        {
            return;
        }
    }//GEN-LAST:event_jbApplyActionPerformed

    private void jCswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCswActionPerformed
        // Add your handling code here:

        start();

        resetInterface();

    }//GEN-LAST:event_jCswActionPerformed

    private void jCcatActionPerformed(java.awt.event.ActionEvent evt) {
        if(jR_r3.isSelected())
        {
            jT_processing.setText("");
        }else
        {
            jT_processing.setText((String)jCcat.getSelectedItem());
        }
        if(jR_r1.isSelected()==true)
        {
            jT_cdr.setText("0");
        }
    }
    /*

     */
    private void jR_r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_r1ActionPerformed
        // Add your handling code here:
        if(jR_r1.isSelected())
        {
            jR_r2.setEnabled(true);
            jR_r3.setEnabled(true);
            jT_cdr.setEditable(true);
            jbApply.setEnabled(true);
            //jT_processing.setEditable(true);
        }else
        {
            jR_r2.setEnabled(false);
            jR_r3.setEnabled(false);
            jR_r2.setSelected(false);
            jR_r3.setSelected(false);
            jT_cdr.setEditable(false);
            jbApply.setEnabled(false);
            //jT_processing.setEditable(false);
        }
    }//GEN-LAST:event_jR_r1ActionPerformed

    private void jbCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCloseActionPerformed
        // Add your handling code here:
        closeFrame();
    }//GEN-LAST:event_jbCloseActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame()
    {
        this.dispose();
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox jCsw;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JTextField jT_cdr;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JComboBox jCcat;
    private javax.swing.JTextField jT_processing;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JTextField jT_renaming;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jP_p2;
    private javax.swing.JRadioButton jR_r1;
    private javax.swing.JRadioButton jR_r2;
    private javax.swing.JRadioButton jR_r3;
    private javax.swing.JPanel jP_p3;
    private javax.swing.JButton jbRefresh;
    private javax.swing.JButton jbApply;
    private javax.swing.JButton jbClose;
    private javax.swing.JRadioButton jR_AutoRefresh;
    private javax.swing.JLabel jL_help;
    // End of variables declaration//GEN-END:variables

}
