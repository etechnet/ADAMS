/*
 * ADAMS_JP_UsersJob.java
 *
 * Created on 10 marzo 2006, 11.11
 */

//   $GARBAGE

/**
 *
 * @author  luca
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import net.etech.*;
import net.etech.MDM.*;

public class ADAMS_JP_UsersJob extends javax.swing.JPanel implements Runnable {

    /** Creates new form ADAMS_JP_UsersJob */
    public ADAMS_JP_UsersJob(ADAMS_JD_Users JD_Users) {

        JOB_USR_LOGIN_LEN   = mdm_server_job_server.eJOB_USR_LOGIN_LEN;
        JOB_USR_PASSWD_LEN  = mdm_server_job_server.eJOB_USR_PASSWD_LEN;

        parent_JD_Users = JD_Users;

        initComponents();

        jTF_user.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,JOB_USR_LOGIN_LEN));
        jPasswd.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,JOB_USR_PASSWD_LEN));
        jPasswd_confirm.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,JOB_USR_PASSWD_LEN));
        jTF_ident.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,1));
        jTF_jobTime.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,6));

        jTF_user.setToolTipText("(Max "+JOB_USR_LOGIN_LEN+" Characters)");
        jPasswd.setToolTipText("(Max "+JOB_USR_PASSWD_LEN+" Characters)");
        jPasswd_confirm.setToolTipText("(Max "+JOB_USR_PASSWD_LEN+" Characters)");

        jL_north.setFont(ADAMS_GlobalParam.font_B12);
        jL_user.setFont(ADAMS_GlobalParam.font_B10);
        jTF_user.setFont(ADAMS_GlobalParam.font_B10);
        jL_passwd.setFont(ADAMS_GlobalParam.font_B10);
        jPasswd.setFont(ADAMS_GlobalParam.font_B10);
        jL_ident.setFont(ADAMS_GlobalParam.font_B10);
        jTF_ident.setFont(ADAMS_GlobalParam.font_B10);
        jL_jobTime.setFont(ADAMS_GlobalParam.font_B10);
        jTF_jobTime.setFont(ADAMS_GlobalParam.font_B10);
        jLdesc.setFont(ADAMS_GlobalParam.font_B10);
        jPasswd_confirm.setFont(ADAMS_GlobalParam.font_B10);
        jL_confirmPwd.setFont(ADAMS_GlobalParam.font_B10);

        jBreset.setCursor(Cur_hand);
        jBcommit.setCursor(Cur_hand);

         // *** Costruzione JTABLE ***/
        myTableModel = new MyTableModel();
        sorter = new TableSorter(myTableModel);

        jTable_job = new javax.swing.JTable(sorter);
        JTableHeader header = jTable_job.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        sorter.setTableHeader(header);
        sorter.setSortingStatus(0,1);

        jTable_job.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_job.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_job.setFont(new java.awt.Font("Verdana", 0, 10));
        //jTable_job.setRowHeight(20);
        jTable_job.setRowMargin(2);
        jTable_job.setAutoCreateColumnsFromModel(false);
        jScrollPane1.setViewportView(jTable_job);
        jScrollPane1.getViewport().setBackground(Color.white);
        // *** END Costruzione JTABLE ***/

        SetTable(jTable_job);
        setEnableGui(false);

        CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);
        connectionCorba_BuilJob();

          jTable_job.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_jobMouseReleased(evt);
            }
        });

    }

    public void setTableRowHeigth()
    {
        try
        {
            int numRow =10;
            int HEIGHT_ROW = (int)((jScrollPane1.getHeight())/numRow);

            if(HEIGHT_ROW > 20)
                jTable_job.setRowHeight((int)(HEIGHT_ROW-1.3));
            else
                jTable_job.setRowHeight(20);

            if((jTable_job.isShowing())&&(jTable_job.isVisible()))
            {
                //System.out.println("updateUI 1");
                jTable_job.updateUI();
            }
        }
        catch (Exception e)
        {
        }
    }

    private void SetTable(javax.swing.JTable jtable)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jtable.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_B10);
        header.setReorderingAllowed(false);

        TableColumn column = null;
        for(int i=0; i<jtable.getColumnCount(); i++)
        {
            column = jtable.getColumnModel().getColumn(i);

            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);

            //if(CellDimension[i] == minCellDimension)
                //column.setMaxWidth(50);
        }

        //sorter.setSortingStatus(1,1); // ordino per tag

        try
        {
            if((jtable.isShowing())&&(jtable.isVisible()))
            {
                //System.out.println("updateUI 2");
                jTable_job.updateUI();
            }
        }
        catch (Exception e)
        {
        }

        this.setCursor(Cur_default);
    }


    private void AddRowJTabel_general()
    {
        jTable_job.setCursor(Cur_wait);

        if(mdm_user_job != null)
        {
            int SIZE = mdm_user_job.length;
            Object[][] dataValues = new Object[SIZE][5];

            for(int i=0; i<SIZE; i++)
            {
                dataValues[i][0] = new String(""+mdm_user_job[i].iID_User);

                dataValues[i][1] = new String(mdm_user_job[i].user).trim();

                String pwd = new String(mdm_user_job[i].password).trim();
                String str_pwd_defined = "";
                for(int j=0; j<pwd.length(); j++)
                    str_pwd_defined += "*";
                dataValues[i][2] = str_pwd_defined;

                int userJob = new Integer(""+mdm_user_job[i].job).intValue();
                if(userJob > 0)
                    dataValues[i][3] = new String(""+userJob);
                else
                    dataValues[i][3] = new String("");
            }

            myTableModel.setDataValues(dataValues);
            sorter.setTableModel(myTableModel);

            resetGui();
            setEnableGui(false);

            try
            {
                if((jTable_job.isShowing())&&(jTable_job.isVisible()))
                {
                    //System.out.println("updateUI 3");
                    jTable_job.updateUI();
                }
            }
            catch(Exception e)
            {
            }
        }

        jTable_job.setCursor(Cur_default);
    }


    private void setEnableGui(boolean flag)
    {
        jTF_user.setEnabled(flag);
        jPasswd.setEnabled(flag);
        jPasswd_confirm.setEnabled(flag);
    }

    private void resetGui()
    {
        jTF_user.setText("");
        jPasswd.setText("");
        jPasswd_confirm.setText("");
        jTF_ident.setText("");
        jTF_jobTime.setText("");
    }


    private boolean connectionCorba_BuilJob()
    {
        boolean flag_conn_config = CORBA.openConnection_BuildJob();

        if(flag_conn_config)
            System.out.println("Connection BuilJob ok.");
        else
        {
            System.out.println(" - Error Connection CORBA BuilJob.");
            ADAMS_GlobalParam.optionPanel(parent_JD_Users,"Error Connection CORBA BuilJob.","ERROR",1);
        }

        return flag_conn_config;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jP_north = new javax.swing.JPanel();
        jL_north = new javax.swing.JLabel();
        jB_LoadUser = new javax.swing.JButton();
        jLdesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jL_user = new javax.swing.JLabel();
        jTF_user = new javax.swing.JTextField();
        jL_passwd = new javax.swing.JLabel();
        jPasswd = new javax.swing.JTextField();
        jL_confirmPwd = new javax.swing.JLabel();
        jPasswd_confirm = new javax.swing.JTextField();
        jL_ident = new javax.swing.JLabel();
        jTF_ident = new javax.swing.JTextField();
        jL_jobTime = new javax.swing.JLabel();
        jTF_jobTime = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jBreset = new javax.swing.JButton();
        jBcommit = new javax.swing.JButton();


        setLayout(new java.awt.BorderLayout());

        setBackground(new java.awt.Color(230, 230, 230));
        jP_north.setLayout(new java.awt.BorderLayout(5, 5));

        jP_north.setBackground(new java.awt.Color(230, 230, 230));
        jL_north.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_north.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_job.png")));
        jL_north.setText("Users Job ");
        jL_north.setMinimumSize(new java.awt.Dimension(254, 40));
        jL_north.setPreferredSize(new java.awt.Dimension(254, 40));
        jP_north.add(jL_north, java.awt.BorderLayout.NORTH);

        jB_LoadUser.setBackground(new java.awt.Color(230, 230, 230));
        jB_LoadUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_loadUsers.jpg")));
        jB_LoadUser.setToolTipText("Load Users");
        jB_LoadUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_LoadUser.setBorderPainted(false);
        jB_LoadUser.setContentAreaFilled(false);
        jB_LoadUser.setFocusPainted(false);
        jB_LoadUser.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jB_LoadUser.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_loadUsers_press.jpg")));
        jB_LoadUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_loadUsers_over.jpg")));
        jB_LoadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_LoadUserActionPerformed(evt);
            }
        });

        jP_north.add(jB_LoadUser, java.awt.BorderLayout.WEST);

        jLdesc.setText(" and it selects a user to visualize the property.");
        jP_north.add(jLdesc, java.awt.BorderLayout.CENTER);

        add(jP_north, java.awt.BorderLayout.NORTH);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setOpaque(false);
        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jP_south.setLayout(new java.awt.BorderLayout());

        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setBorder(new javax.swing.border.TitledBorder(null, " User Job Single Operation ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jP_south.setMinimumSize(new java.awt.Dimension(82, 120));
        jP_south.setPreferredSize(new java.awt.Dimension(10, 120));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(510, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(510, 30));
        jL_user.setBackground(new java.awt.Color(230, 230, 230));
        jL_user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_user.setText("User");
        jL_user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_user.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_user.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jL_user, gridBagConstraints1);

        jTF_user.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTF_user.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_user.setMinimumSize(new java.awt.Dimension(100, 22));
        jTF_user.setPreferredSize(new java.awt.Dimension(100, 22));
        jTF_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_KeyReleased(evt);
            }
        });

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jTF_user, gridBagConstraints1);

        jL_passwd.setBackground(new java.awt.Color(230, 230, 230));
        jL_passwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_passwd.setText("Password");
        jL_passwd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_passwd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_passwd.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jL_passwd, gridBagConstraints1);

        jPasswd.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPasswd.setDisabledTextColor(java.awt.Color.darkGray);
        jPasswd.setMinimumSize(new java.awt.Dimension(100, 22));
        jPasswd.setPreferredSize(new java.awt.Dimension(100, 22));
        jPasswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_KeyReleased(evt);
            }
        });

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jPasswd, gridBagConstraints1);

        jL_confirmPwd.setBackground(new java.awt.Color(230, 230, 230));
        jL_confirmPwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_confirmPwd.setText("Confirm Password");
        jL_confirmPwd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_confirmPwd.setMinimumSize(new java.awt.Dimension(100, 22));
        jL_confirmPwd.setPreferredSize(new java.awt.Dimension(100, 22));
        jL_confirmPwd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_confirmPwd.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jL_confirmPwd, gridBagConstraints1);

        jPasswd_confirm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPasswd_confirm.setDisabledTextColor(java.awt.Color.darkGray);
        jPasswd_confirm.setMinimumSize(new java.awt.Dimension(100, 22));
        jPasswd_confirm.setPreferredSize(new java.awt.Dimension(100, 22));
        jPasswd_confirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_KeyReleased(evt);
            }
        });

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jPasswd_confirm, gridBagConstraints1);

        jL_ident.setBackground(new java.awt.Color(230, 230, 230));
        jL_ident.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_ident.setText("Identifier");
        jL_ident.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_ident.setMinimumSize(new java.awt.Dimension(45, 22));
        jL_ident.setPreferredSize(new java.awt.Dimension(45, 22));
        jL_ident.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_ident.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jL_ident, gridBagConstraints1);

        jTF_ident.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_ident.setDisabledTextColor(java.awt.Color.black);
        jTF_ident.setPreferredSize(new java.awt.Dimension(20, 21));
        jTF_ident.setEnabled(false);
        jTF_ident.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_KeyReleased(evt);
            }
        });

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jTF_ident, gridBagConstraints1);

        jL_jobTime.setBackground(new java.awt.Color(230, 230, 230));
        jL_jobTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_jobTime.setText("Job Limit");
        jL_jobTime.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_jobTime.setMinimumSize(new java.awt.Dimension(45, 22));
        jL_jobTime.setPreferredSize(new java.awt.Dimension(45, 22));
        jL_jobTime.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_jobTime.setOpaque(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 4;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jL_jobTime, gridBagConstraints1);

        jTF_jobTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTF_jobTime.setDisabledTextColor(java.awt.Color.darkGray);
        jTF_jobTime.setMinimumSize(new java.awt.Dimension(4, 21));
        jTF_jobTime.setPreferredSize(new java.awt.Dimension(4, 21));
        jTF_jobTime.setEnabled(false);
        jTF_jobTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_KeyReleased(evt);
            }
        });

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 4;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints1.weightx = 1.0;
        jPanel2.add(jTF_jobTime, gridBagConstraints1);

        jP_south.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(72, 20));
        jPanel4.setPreferredSize(new java.awt.Dimension(152, 40));
        jBreset.setBackground(new java.awt.Color(230, 230, 230));
        jBreset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset.jpg")));
        jBreset.setBorderPainted(false);
        jBreset.setContentAreaFilled(false);
        jBreset.setFocusPainted(false);
        jBreset.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jBreset.setMaximumSize(new java.awt.Dimension(0, 0));
        jBreset.setMinimumSize(new java.awt.Dimension(0, 0));
        jBreset.setPreferredSize(new java.awt.Dimension(80, 30));
        jBreset.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset_press.jpg")));
        jBreset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset_over.jpg")));
        jBreset.setEnabled(false);
        jBreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBresetActionPerformed(evt);
            }
        });

        jPanel4.add(jBreset);

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
        jBcommit.setEnabled(false);
        jBcommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcommitActionPerformed(evt);
            }
        });

        jPanel4.add(jBcommit);

        jP_south.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(jP_south, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents

    private int getSelectedIndexCorrect(int indexSelTab)
    {
        int id_identifier = new Integer(""+jTable_job.getValueAt(indexSelTab,0)).intValue();

        for(int i=0; i<mdm_user_job.length; i++)
        {
            if(id_identifier == mdm_user_job[i].iID_User )
                return i;
        }
        return -1;
    }


    private void setSelected_jTableRow()
    {
        this.setCursor(Cur_wait);

        if(mdm_user_job != null)
        {
            int selectedRow = getSelectedIndexCorrect(jTable_job.getSelectedRow());

            if(selectedRow >= 0)
            {
                jTF_user.setText(new String(mdm_user_job[selectedRow].user).trim());
                jPasswd.setText(new String(mdm_user_job[selectedRow].password).trim());
                jPasswd_confirm.setText(new String(mdm_user_job[selectedRow].password).trim());
                jTF_ident.setText(""+mdm_user_job[selectedRow].iID_User);
                jTF_jobTime.setText(""+mdm_user_job[selectedRow].job);

                jBcommit.setEnabled(false);
                jBreset.setEnabled(true);
                setEnableGui(true);
            }
            else
            {
                setEnableGui(false);
            }
        }
        else
            System.out.println("- Errore getSelectedIndexCorrect(....) -");
        this.setCursor(Cur_default);
     }


    private void jTable_jobMouseReleased(java.awt.event.MouseEvent evt)
    {
        setSelected_jTableRow();
    }

    private void jB_LoadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_LoadUserActionPerformed

        setEnableGui(false);
        jBreset.setEnabled(false);
        jB_LoadUser.setEnabled(false);
        jBcommit.setEnabled(false);

        OPERATION_TH = READ_ALL_USER;

        TH = null;
        TH = new Thread(this,"readUserJob");
        TH.start();
    }//GEN-LAST:event_jB_LoadUserActionPerformed

    private void jTF_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_KeyReleased

        int user_len = jTF_user.getText().length();
        int pasw_len = jPasswd.getText().length();
        int paswConf_lem = jPasswd_confirm.getText().length();
        int iden_len =jTF_ident.getText().length();
        int j_time_len =jTF_jobTime.getText().length();

        if( (user_len <= 0) || (pasw_len <= 0) || (paswConf_lem <= 0) || (iden_len <= 0) || ( j_time_len <= 0))
            jBcommit.setEnabled(false);
        else
            jBcommit.setEnabled(true);

    }//GEN-LAST:event_jTF_KeyReleased

    private void jBresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBresetActionPerformed

        int selectedRow = jTable_job.getSelectedRow();
        if(selectedRow >= 0)
        {
            resetGui();
            jBcommit.setEnabled(true);
        }
        else
        {
            ADAMS_GlobalParam.optionPanel(parent_JD_Users,"You have to select an item first.","INFO",3);
        }
    }//GEN-LAST:event_jBresetActionPerformed

    private void jBcommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcommitActionPerformed

        setEnableGui(false);
        jBreset.setEnabled(false);
        jB_LoadUser.setEnabled(false);
        jBcommit.setEnabled(false);

        OPERATION_TH = OP_COMMIT;

        TH = null;
        TH = new Thread(this,"commitUserJob");
        TH.start();

    }//GEN-LAST:event_jBcommitActionPerformed

    private void readUserJob()
    {
        //System.out.println(" ------>>> readUserJob()");
        this.setCursor(Cur_wait);

        mdm_user_job = CORBA.getQueueList();

        if(mdm_user_job != null)
        {
            //System.out.println("mdm_user_job.length "+mdm_user_job.length);
            AddRowJTabel_general();
        }
        else
        {
            ADAMS_GlobalParam.optionPanel(parent_JD_Users,"Error: Users not Found.","ERROR",1);
        }

        this.setCursor(Cur_default);
    }

    private void commitUserJob()
    {
        //System.out.println(" ------>>> commitUserJob()");
        this.setCursor(Cur_wait);

        int selectedRow = getSelectedIndexCorrect(jTable_job.getSelectedRow());
        if(selectedRow >= 0)
        {
            String str_pwd = jPasswd.getText();
            String str_pwd_confirm = jPasswd_confirm.getText();

            if(str_pwd.equals(str_pwd_confirm) == false)
            {
                ADAMS_GlobalParam.optionPanel(parent_JD_Users,"Inserted password didn't match.","INFO",3);
                jPasswd_confirm.setText("");
                jPasswd_confirm.requestFocus();
                jBcommit.setEnabled(false);
                this.setCursor(Cur_default);
                return;
            }

            ADAMS_JD_Message op = new ADAMS_JD_Message(parent_JD_Users,true,"About to change selected row. Confirm ?","Warning",6);
            int Yes_No = op.getAnswer();
            if(Yes_No == 0)
            {
                this.setCursor(Cur_default);
                return;
            }

            mdm_user_job[selectedRow].user             = set_String_toChar(jTF_user.getText().trim() ,JOB_USR_LOGIN_LEN);
            mdm_user_job[selectedRow].password            = set_String_toChar(str_pwd ,JOB_USR_PASSWD_LEN);

            if(jTF_ident.getText().trim().length() != 0)
                mdm_user_job[selectedRow].iID_User = new Integer(jTF_ident.getText().trim()).intValue();
            else
                mdm_user_job[selectedRow].iID_User = 0;

            if(jTF_jobTime.getText().trim().length() != 0)
                mdm_user_job[selectedRow].job = new Integer(jTF_jobTime.getText().trim()).intValue();
            else
                mdm_user_job[selectedRow].job = 0;

            //mdm_user_job[selectedRow].flag = 0;

            /*for(int i=0; i<mdm_user_job.length; i++)
            {
                System.out.println("mdm_user_job["+i+"].user           "+new String(mdm_user_job[i].user).trim());
                System.out.println("mdm_user_job["+i+"].password          "+new String(mdm_user_job[i].password).trim());
                System.out.println("mdm_user_job["+i+"].iID_User          "+mdm_user_job[i].iID_User);
                System.out.println("mdm_user_job["+i+"].scheduleTime    "+mdm_user_job[i].scheduleTime);
                System.out.println("mdm_user_job["+i+"].job        "+mdm_user_job[i].job);
                System.out.println("mdm_user_job["+i+"].flag            "+mdm_user_job[i].flag);
            }*/

            this.setCursor(Cur_default);

            boolean flag =  CORBA.putQueueList(mdm_user_job);

            if(flag)
            {
                ADAMS_GlobalParam.optionPanel(parent_JD_Users,"Operation has been done.","INFO",3);
                AddRowJTabel_general();
            }
            else
                ADAMS_GlobalParam.optionPanel(parent_JD_Users,"Operation failed.","ERROR",1);
        }
        else
            System.out.println("- Errore getSelectedIndexCorrect(...) -");

     }


    public void run()
    {
        TH_WORKING = true;
        this.setCursor(Cur_wait);

        if(OPERATION_TH == READ_ALL_USER) //readUserJob()
        {
            readUserJob();
            setEnableGui(true);
            jBreset.setEnabled(true);
            jB_LoadUser.setEnabled(true);
        }
        else if(OPERATION_TH == OP_COMMIT) //commitUserJob()
        {
            commitUserJob();

            setEnableGui(true);
            jBreset.setEnabled(true);
            jB_LoadUser.setEnabled(true);
        }

        this.setCursor(Cur_default);
        OPERATION_TH = -1;
        TH_WORKING = false;
    }

    private static char[] set_String_toChar(String str, int length)
    {

    	char[] appo = str.toCharArray();
    	char[] appo1 = new char[length];

    	if (appo.length > length)
    	    return appo1;


    	for (int i=0; i<appo.length; i++)
    	    appo1[i] = appo[i];

    	for (int i=appo.length; i<length; i++)
    	    appo1[i] ='\0';

        appo1[length-1] ='\0';

    	return(appo1);
    }

//////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////
    class MyTableModel extends AbstractTableModel
    {
        private String[] columnNames ={ "Identifier",
                                        "User",
                                        "Password",
                                        "Job Limit"};
        private Object[][] data = {};

        public int getColumnCount() {
            return columnNames.length;
        }
        public int getRowCount()
        {
            return data.length;
        }
        public String getColumnName(int col)
        {
            return columnNames[col];
        }
        public java.lang.Object getValueAt(int row, int col)
        {
            return data[row][col];
        }
        public void setDataValues(Object[][] datavalues)
        {
            data = datavalues;
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c)
        {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col)
        {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            /*if (col < 2) {
                return false;
            } else {
                return true;
            }*/
            return false; //nessuna cella editabile
        }
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col)
        {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
        private void printDebugData()
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
//////////////////////////////////////////// END Internal CLASS ////////////////////////////////////////////
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jP_north;
    private javax.swing.JLabel jL_north;
    private javax.swing.JButton jB_LoadUser;
    private javax.swing.JLabel jLdesc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jL_user;
    private javax.swing.JTextField jTF_user;
    private javax.swing.JLabel jL_passwd;
    private javax.swing.JTextField jPasswd;
    private javax.swing.JLabel jL_confirmPwd;
    private javax.swing.JTextField jPasswd_confirm;
    private javax.swing.JLabel jL_ident;
    private javax.swing.JTextField jTF_ident;
    private javax.swing.JLabel jL_jobTime;
    private javax.swing.JTextField jTF_jobTime;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jBreset;
    private javax.swing.JButton jBcommit;
    // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    private javax.swing.JTable jTable_job;
    private TableSorter sorter;
    private MyTableModel myTableModel;
    private int[] CellDimension = {40,150,150,50};
    private int minCellDimension = 30;

    private Vector V_pwd = null;

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);

    private Thread TH           = null;
    private boolean TH_WORKING  = false;
    private int OPERATION_TH    = -1;

    private CORBAConnection CORBA           = null;
    private ADAMS_JD_Users parent_JD_Users   = null;

    private STRUCT_USER[] mdm_user_job       = null;

    private final int READ_ALL_USER = 0;
    private final int OP_COMMIT     = 1;

    int JOB_USR_LOGIN_LEN;
    int JOB_USR_PASSWD_LEN;
}




