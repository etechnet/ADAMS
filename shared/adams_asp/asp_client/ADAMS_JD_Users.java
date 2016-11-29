import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.util.Vector;
import net.etech.*;
import net.etech.MDM.*;

public class ADAMS_JD_Users extends javax.swing.JDialog implements Runnable {

    /** Creates new form ADAMS_JD_Message */
    public ADAMS_JD_Users(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        JP_UsersJob = new ADAMS_JP_UsersJob(this);
        jP_userJob_box.add(JP_UsersJob);

        jTabbedPane1.addTab("Users Management", new javax.swing.ImageIcon(getClass().getResource("/images/user_22.png")), jP_usersManagement);
        jTabbedPane1.addTab("Users Job", new javax.swing.ImageIcon(getClass().getResource("/images/user_job_22.png")),jP_userJob_box );

        jTabbedPane1.setToolTipTextAt(0,"Users Management");
        jTabbedPane1.setToolTipTextAt(1,"Users Job");

        jTabbedPane1.setSelectedIndex(0);

        V_activeConfigs     = new Vector();
        V_Available_files   = new Vector();

        IcPool = new IconPool("/images/");

        jL_activeConfigs = new JListIcon(IcPool);
        jL_activeConfigs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jL_Available_files = new JListIcon(IcPool);
        jL_Available_files.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//SINGLE_SELECTION

        jScroll2.setViewportView(jL_activeConfigs);
        jScroll1.setViewportView(jL_Available_files);

        //Font
        jL_icon.setFont(ADAMS_GlobalParam.font_B12);
        jL_desc.setFont(ADAMS_GlobalParam.font_B10);
        jL_scroll1.setFont(ADAMS_GlobalParam.font_B10);
        jL_scroll2.setFont(ADAMS_GlobalParam.font_B10);
        jL_activeConfigs.setFont(ADAMS_GlobalParam.font_B10);
        jL_Available_files.setFont(ADAMS_GlobalParam.font_B10);
        //Cursor

        jButton_Add.setCursor(cursor_hand);
        jButton_AddAll.setCursor(cursor_hand);
        jButton_Remove.setCursor(cursor_hand);
        jButton_RemoveAll.setCursor(cursor_hand);
        jL_activeConfigs.setCursor(cursor_hand);
        jL_Available_files.setCursor(cursor_hand);
        jCheckBox2.setCursor(cursor_hand);
        jCheckBox1.setCursor(cursor_hand);

        jB_Commit.setCursor(cursor_hand);
        jB_Close.setCursor(cursor_hand);
        jB_loadUsers.setCursor(cursor_hand);

        this.setTitle("Users Management");
        setEnabledGUI(false);

        jL_desc.setText("Push-button \"Load Users\" order to begin.");

        CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);

        CONNECTION_CORBA_STS = connectionCorba_STSConfig();

        if (CONNECTION_CORBA_STS == false)
        {
            jB_loadUsers.setEnabled(false);
            jB_Close.setEnabled(true);
        }

        setCenteredFrame(650,600);
        //show();
        this.setVisible(true);

    }

    private boolean connectionCorba_STSConfig()
    {
        boolean flag_conn_config = CORBA.openConnection_config();

        if(flag_conn_config)
            System.out.println("Connection STSConfigServer... ok.");
        else
        {
            System.out.println(" - Error Connection CORBA STSConfigServer.");
            ADAMS_GlobalParam.optionPanel(this,"Error Connection CORBA STSConfigServer.","ERROR",1);
        }

        return flag_conn_config;
    }

    private boolean connectionCorba_mdm_server_server()
    {
        boolean flag_conn_NTM = CORBA.openConnection_mdm_server_server();

        if(flag_conn_NTM)
            System.out.println("Connection mdm_server_server... ok.");
        else
        {
            System.out.println(" - Error Connection CORBA mdm_server_server.");
            ADAMS_GlobalParam.optionPanel(this,"Error Connection CORBA mdm_server_server.","ERROR",1);
        }

        return flag_conn_NTM;
    }

    private void readAllUser()
    {
        setEnabledGUI(false);
        jB_Close.setEnabled(false);

        OPERATION_TH = READ_ALL_USER;

        TH = null;
        TH = new Thread(this,"readAllUser");
        TH.start();

    }

    private void readUsers()
    {
        ntmUser = null;
        ntmUser = CORBA.readUsers("");

        /*System.out.println("Utenti Configurati  ==>"+ntmUser.length);
        for(int i=0; i<ntmUser.length; i++)
        {
            System.out.println("Utente ==>"+new String(ntmUser[i].login).trim());

        }*/
    }

    private void directoryListing()
    {
        dir_entry = CORBA.directoryListing();

        /*System.out.println("Configurazioni presenti ==>"+dir_entry.length);
        for(int i=0; i<dir_entry.length; i++)
            System.out.println("config ==>"+new String(dir_entry[i].name).trim());*/

    }


    private void excecuteCommit()
    {
        boolean flag = false;
        String str = "";

        boolean flag_connection_ntm  = connectionCorba_mdm_server_server();

        if(flag_connection_ntm == true)
        {
           /* if(jL_activeConfigs.getItemCount()==0)
            {
                ADAMS_GlobalParam.optionPanel(this,"Operation failed, select at least one configuration."+str+".","ERROR",1);
                flag=false;
                jComboBox_users.setSelectedIndex(0);
                return;
            }*/
            ADAMS_USER[] ntmUser_forcommit = getDataForCommit();

            if(ntmUser != null)
            {

                // Controllo eventuali Utenti Sconfigurati
                Vector V_ADAMS_USER = new Vector();
                for (int i=0;i<ntmUser_forcommit.length;i++)
                {
                    if( !(new String(ntmUser_forcommit[i].enabledConfigurations[0]).trim()).equals("") )
                    {
                        V_ADAMS_USER.addElement(ntmUser_forcommit[i]);
                    }
                }

                int SizeEnabledConf = V_ADAMS_USER.size();
                ntmUser = new ADAMS_USER[SizeEnabledConf];
                for(int i=0; i<SizeEnabledConf; i++)
                {
                    ntmUser[i] = (ADAMS_USER)V_ADAMS_USER.elementAt(i);
                }
                // END Controllo eventuali Utenti Sconfigurati

                // *********** STAMPA CONFIG
                /*for (int z=0; z<ntmUser.length; z++)
                {
                    String appoLogin_1 = new String(ntmUser[z].login).trim();
                    System.out.println();
                    System.out.println(appoLogin_1);
                    int j=0;
                    while( ! ((new String(ntmUser[z].enabledConfigurations[j])).trim()).equals("") )
                    {
                        System.out.println("   enabledConfigurations ----> "+new String(ntmUser[z].enabledConfigurations[j]).trim());
                        j++;
                    }
                }*/
                // *********** END  STAMPA CONFIG

                flag = CORBA.writeUsers(ntmUser);
                CORBA.shutDown_Conf();
            }
            else
                str = " (DATA NULL [ ADAMS_USER ])";


            if(flag)
            {
                ADAMS_GlobalParam.optionPanel(this,"Operation has been done.","INFO",3);
                jCheckBox2.setSelected(false);
                jCheckBox1.setSelected(false);
                jB_Commit.setEnabled(false);
            }
            else
                ADAMS_GlobalParam.optionPanel(this,"Operation failed"+str+".","ERROR",1);

        }

        jComboBox_users.setSelectedIndex(0);
    }

    private ADAMS_USER[] getDataForCommit()
    {
        if(jComboBox_users.getSelectedIndex() == 0)
            return null;


        String userSelected = (String)jComboBox_users.getSelectedItem();

        if(ntmUser != null)
        {

            int SIZE_V_activeConfigs = V_activeConfigs.size();

            // Utente PRECEDENDEMENTE CONFIGURATO


            for (int i=0;i<ntmUser.length;i++)
            {
                String appoLogin = new String(ntmUser[i].login).trim();


                if(appoLogin.equals(userSelected))
                {


                    ntmUser[i].userAdmin=flagAdmin;
                    ntmUser[i].configAdmin=flagCalendar;
                    //System.out.println("user="+appoLogin);
                    //System.out.println("flagAdmin="+flagAdmin);
                    //System.out.println("configAdmin="+flagCalendar);
                    ntmUser[i].enabledConfigurations = new char[ADAMS_GlobalParam.MAX_ENABLE_CONFIGS][ADAMS_GlobalParam.MAX_CONFIG_FILENAME];
                    for(int j=0; j<ADAMS_GlobalParam.MAX_ENABLE_CONFIGS; j++)
                    {
                        if((j < SIZE_V_activeConfigs) && (SIZE_V_activeConfigs != 0))
                        {
                            String strActiveConf = (String)V_activeConfigs.elementAt(j);
                            ntmUser[i].enabledConfigurations[j] = set_String_toChar(strActiveConf,ADAMS_GlobalParam.MAX_CONFIG_FILENAME);
                        }
                        else
                            ntmUser[i].enabledConfigurations[j] = set_String_toChar("",ADAMS_GlobalParam.MAX_CONFIG_FILENAME);
                    }

                    //System.out.println("userSelected="+userSelected+"  flag="+ntmUser[i].userAdmin);

                    return ntmUser;
                }
            }

            //Utente NON CONFIGURATO PRECEDENDEMENTE
            ADAMS_USER[] NEW_ntmUser = new ADAMS_USER[ntmUser.length+1];
            //System.out.println("new user");

            for (int i=0;i<NEW_ntmUser.length;i++)
            {
                NEW_ntmUser[i] = new ADAMS_USER();
                if(i < (NEW_ntmUser.length-1))
                {

                    NEW_ntmUser[i].login = ntmUser[i].login;
                    NEW_ntmUser[i].passwd = ntmUser[i].passwd;
                    NEW_ntmUser[i].userAdmin = ntmUser[i].userAdmin;
                    NEW_ntmUser[i].configAdmin = ntmUser[i].configAdmin;

                    //System.out.println("login="+new String(NEW_ntmUser[i].login).trim());

                    NEW_ntmUser[i].enabledConfigurations = ntmUser[i].enabledConfigurations;
                    for(int j=0; j<ntmUser[i].enabledConfigurations.length; j++)
                        NEW_ntmUser[i].enabledConfigurations[j] = ntmUser[i].enabledConfigurations[j];
                }
                else
                {
                    NEW_ntmUser[i].login = set_String_toChar(userSelected,ADAMS_GlobalParam.USR_LOGIN_LEN);
                    NEW_ntmUser[i].passwd = set_String_toChar(userSelected,ADAMS_GlobalParam.USR_PASSWD_LEN);
                    NEW_ntmUser[i].userAdmin=flagAdmin;
                    NEW_ntmUser[i].configAdmin=flagCalendar;

                    //System.out.println("login="+new String(NEW_ntmUser[i].login).trim());
                    //System.out.println("userAdmin="+NEW_ntmUser[i].userAdmin);
                    //System.out.println("configAdmin="+NEW_ntmUser[i].configAdmin);

                    NEW_ntmUser[i].enabledConfigurations = new char[ADAMS_GlobalParam.MAX_ENABLE_CONFIGS][ADAMS_GlobalParam.MAX_CONFIG_FILENAME];
                    for(int j=0; j<ADAMS_GlobalParam.MAX_ENABLE_CONFIGS; j++)
                    {
                        if((j < SIZE_V_activeConfigs) && (SIZE_V_activeConfigs != 0))
                        {
                            String strActiveConf = (String)V_activeConfigs.elementAt(j);
                            NEW_ntmUser[i].enabledConfigurations[j] = set_String_toChar(strActiveConf,ADAMS_GlobalParam.MAX_CONFIG_FILENAME);
                        }
                        else
                            NEW_ntmUser[i].enabledConfigurations[j] = set_String_toChar("",ADAMS_GlobalParam.MAX_CONFIG_FILENAME);
                    }
                    return NEW_ntmUser;
                }
            }
        }

        return null;
    }


    private void setEnabledGUI(boolean flag)
    {
        jComboBox_users.setEnabled(flag);

        jButton_Add.setEnabled(flag);
        jButton_AddAll.setEnabled(flag);
        jButton_Remove.setEnabled(flag);
        jButton_RemoveAll.setEnabled(flag);
        jL_activeConfigs.setEnabled(flag);
        jL_Available_files.setEnabled(flag);
        if(jComboBox_users.getSelectedItem()!=null)
        {
            if(((String)jComboBox_users.getSelectedItem()).equals("< select >"))
            {
                jCheckBox2.setEnabled(false);
                jCheckBox1.setEnabled(false);
            }else
            {
                jCheckBox2.setEnabled(flag);
                jCheckBox1.setEnabled(flag);
            }
        }


    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jP_usersManagement = new javax.swing.JPanel();
        jL_icon = new javax.swing.JLabel();
        JP_center = new javax.swing.JPanel();
        jP_user_Profile = new javax.swing.JPanel();
        jComboBox_users = new javax.swing.JComboBox();
        jL_desc = new javax.swing.JLabel();
        jB_loadUsers = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jP_confRepository = new javax.swing.JPanel();
        jL_scroll1 = new javax.swing.JLabel();
        jScroll1 = new javax.swing.JScrollPane();
        jP_buttonChooser = new javax.swing.JPanel();
        jButton_Add = new javax.swing.JButton();
        jButton_AddAll = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_RemoveAll = new javax.swing.JButton();
        jL_scroll2 = new javax.swing.JLabel();
        jScroll2 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jL_iconDesc = new javax.swing.JLabel();
        jL_selUser = new javax.swing.JLabel();
        jB_Commit = new javax.swing.JButton();
        jP_userJob_box = new javax.swing.JPanel();
        jP_close = new javax.swing.JPanel();
        jB_Close = new javax.swing.JButton();

        setBackground(new java.awt.Color(230, 230, 230));
        setModal(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(230, 230, 230));
        jP_usersManagement.setLayout(new java.awt.BorderLayout());

        jP_usersManagement.setBackground(new java.awt.Color(230, 230, 230));
        jP_usersManagement.setBorder(new javax.swing.border.EtchedBorder());
        jL_icon.setBackground(new java.awt.Color(230, 230, 230));
        jL_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png")));
        jL_icon.setText("Users Management");
        jL_icon.setPreferredSize(new java.awt.Dimension(40, 40));
        jL_icon.setOpaque(true);
        jP_usersManagement.add(jL_icon, java.awt.BorderLayout.NORTH);

        JP_center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        JP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_user_Profile.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;

        jP_user_Profile.setBackground(new java.awt.Color(230, 230, 230));
        jP_user_Profile.setBorder(new javax.swing.border.TitledBorder(null, " User Profiles ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10)));
        jP_user_Profile.setMinimumSize(new java.awt.Dimension(20, 90));
        jP_user_Profile.setPreferredSize(new java.awt.Dimension(20, 90));
        jComboBox_users.setMinimumSize(new java.awt.Dimension(60, 24));
        jComboBox_users.setPreferredSize(new java.awt.Dimension(80, 24));
        jComboBox_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_usersActionPerformed(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints2.weightx = 1.0;
        jP_user_Profile.add(jComboBox_users, gridBagConstraints2);

        jL_desc.setText("Select User ");
        jL_desc.setMinimumSize(new java.awt.Dimension(40, 30));
        jL_desc.setPreferredSize(new java.awt.Dimension(60, 30));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints2.weightx = 1.0;
        jP_user_Profile.add(jL_desc, gridBagConstraints2);

        jB_loadUsers.setBackground(new java.awt.Color(230, 230, 230));
        jB_loadUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_loadUsers.jpg")));
        jB_loadUsers.setToolTipText("Load Users");
        jB_loadUsers.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_loadUsers.setBorderPainted(false);
        jB_loadUsers.setContentAreaFilled(false);
        jB_loadUsers.setFocusPainted(false);
        jB_loadUsers.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jB_loadUsers.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_loadUsers_press.jpg")));
        jB_loadUsers.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_loadUsers_over.jpg")));
        jB_loadUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_loadUsersActionPerformed(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new java.awt.Insets(4, 4, 4, 4);
        jP_user_Profile.add(jB_loadUsers, gridBagConstraints2);

        jCheckBox2.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox2.setText("Privileged User/Profile");
        jCheckBox2.setToolTipText("(True/False)");
        jCheckBox2.setFocusPainted(false);
        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCheckBox2.setMaximumSize(new java.awt.Dimension(210, 35));
        jCheckBox2.setMinimumSize(new java.awt.Dimension(210, 35));
        jCheckBox2.setPreferredSize(new java.awt.Dimension(210, 35));
        jCheckBox2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCheckBox2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCheckBox2.setEnabled(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_user_Profile.add(jCheckBox2, gridBagConstraints2);

        jCheckBox1.setBackground(new java.awt.Color(230, 230, 230));
        jCheckBox1.setText("Unlock calendar selection");
        jCheckBox1.setToolTipText("(True/False)");
        jCheckBox1.setFocusPainted(false);
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jCheckBox1.setMaximumSize(new java.awt.Dimension(210, 35));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(210, 35));
        jCheckBox1.setPreferredSize(new java.awt.Dimension(210, 35));
        jCheckBox1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jCheckBox1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jCheckBox1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jCheckBox1.setEnabled(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_user_Profile.add(jCheckBox1, gridBagConstraints2);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        JP_center.add(jP_user_Profile, gridBagConstraints1);

        jP_confRepository.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;

        jP_confRepository.setBackground(new java.awt.Color(230, 230, 230));
        jP_confRepository.setBorder(new javax.swing.border.TitledBorder(null, " Configuration Repositories ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10)));
        jP_confRepository.setMinimumSize(new java.awt.Dimension(20, 200));
        jP_confRepository.setPreferredSize(new java.awt.Dimension(20, 200));
        jL_scroll1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_scroll1.setText("Available files");
        jL_scroll1.setMinimumSize(new java.awt.Dimension(100, 20));
        jL_scroll1.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 0, 5);
        gridBagConstraints3.weightx = 1.0;
        jP_confRepository.add(jL_scroll1, gridBagConstraints3);

        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.ipadx = 250;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_confRepository.add(jScroll1, gridBagConstraints3);

        jP_buttonChooser.setLayout(new java.awt.GridLayout(4, 0));

        jButton_Add.setBackground(new java.awt.Color(230, 230, 230));
        jButton_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forward.png")));
        jButton_Add.setToolTipText("Add an item to selection");
        jButton_Add.setFocusPainted(false);
        jButton_Add.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionLists_ActionPerformed(evt);
            }
        });

        jP_buttonChooser.add(jButton_Add);

        jButton_AddAll.setBackground(new java.awt.Color(230, 230, 230));
        jButton_AddAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finish.png")));
        jButton_AddAll.setToolTipText("Add all items to selection");
        jButton_AddAll.setFocusPainted(false);
        jButton_AddAll.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_AddAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionLists_ActionPerformed(evt);
            }
        });

        jP_buttonChooser.add(jButton_AddAll);

        jButton_Remove.setBackground(new java.awt.Color(230, 230, 230));
        jButton_Remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png")));
        jButton_Remove.setToolTipText("Remove an item from selection");
        jButton_Remove.setFocusPainted(false);
        jButton_Remove.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionLists_ActionPerformed(evt);
            }
        });

        jP_buttonChooser.add(jButton_Remove);

        jButton_RemoveAll.setBackground(new java.awt.Color(230, 230, 230));
        jButton_RemoveAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start.png")));
        jButton_RemoveAll.setToolTipText("Remove all items from selection");
        jButton_RemoveAll.setFocusPainted(false);
        jButton_RemoveAll.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jButton_RemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionLists_ActionPerformed(evt);
            }
        });

        jP_buttonChooser.add(jButton_RemoveAll);

        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.ipadx = 40;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        jP_confRepository.add(jP_buttonChooser, gridBagConstraints3);

        jL_scroll2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_scroll2.setText("Active Configs");
        jL_scroll2.setMinimumSize(new java.awt.Dimension(100, 20));
        jL_scroll2.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 0, 5);
        gridBagConstraints3.weightx = 1.0;
        jP_confRepository.add(jL_scroll2, gridBagConstraints3);

        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.ipadx = 250;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_confRepository.add(jScroll2, gridBagConstraints3);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weighty = 1.0;
        JP_center.add(jP_confRepository, gridBagConstraints1);

        jP_usersManagement.add(JP_center, java.awt.BorderLayout.CENTER);

        jP_south.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;

        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setMinimumSize(new java.awt.Dimension(10, 40));
        jP_south.setPreferredSize(new java.awt.Dimension(10, 40));
        jL_iconDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_20.png")));
        jL_iconDesc.setText("Selected User: ");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        jP_south.add(jL_iconDesc, gridBagConstraints4);

        jL_selUser.setBackground(java.awt.Color.white);
        jL_selUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_selUser.setMinimumSize(new java.awt.Dimension(200, 20));
        jL_selUser.setPreferredSize(new java.awt.Dimension(200, 20));
        jL_selUser.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 15);
        gridBagConstraints4.weightx = 1.0;
        jP_south.add(jL_selUser, gridBagConstraints4);

        jB_Commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_Commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jB_Commit.setBorderPainted(false);
        jB_Commit.setContentAreaFilled(false);
        jB_Commit.setFocusPainted(false);
        jB_Commit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jB_Commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_Commit.setMaximumSize(new java.awt.Dimension(32768, 32768));
        jB_Commit.setMinimumSize(new java.awt.Dimension(80, 30));
        jB_Commit.setPreferredSize(new java.awt.Dimension(80, 30));
        jB_Commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jB_Commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jB_Commit.setEnabled(false);
        jB_Commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_CommitActionPerformed(evt);
            }
        });

        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.insets = new java.awt.Insets(4, 15, 4, 4);
        jP_south.add(jB_Commit, gridBagConstraints4);

        jP_usersManagement.add(jP_south, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("User Management", jP_usersManagement);

        jP_userJob_box.setLayout(new java.awt.GridLayout(1, 1));

        jTabbedPane1.addTab("User Job", jP_userJob_box);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jP_close.setBackground(new java.awt.Color(230, 230, 230));
        jB_Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_Close.setToolTipText("Close this windows");
        jB_Close.setBorderPainted(false);
        jB_Close.setContentAreaFilled(false);
        jB_Close.setFocusPainted(false);
        jB_Close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_Close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_Close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_CloseActionPerformed(evt);
            }
        });

        jP_close.add(jB_Close);

        getContentPane().add(jP_close, java.awt.BorderLayout.SOUTH);

        pack();
    }//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // Add your handling code here:
        if(jCheckBox1.isSelected()==true)
        {
            flagCalendar=true;
        }
        else
        {
            flagCalendar=false;
        }

        if(jL_activeConfigs.getItemCount()==0)
        {
            ADAMS_GlobalParam.optionPanel(this,"Operation failed, select at least one configuration.","ERROR",1);
            flagCalendar=!jCheckBox1.isSelected();
            jCheckBox1.setSelected(flagCalendar);
            return;
        }
        //System.out.println("flagAdmin="+flagAdmin);
        //System.out.println("flagCalendar="+flagCalendar);
        jB_Commit.setEnabled(true);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // Add your handling code here:
        if(jCheckBox2.isSelected()==true)
        {
            flagAdmin=true;
        }else
        {
            flagAdmin=false;
        }

        //System.out.println("flagAdmin="+flagAdmin);
        //System.out.println("flagCalendar="+flagCalendar);
        if(jL_activeConfigs.getItemCount()==0)
        {
            ADAMS_GlobalParam.optionPanel(this,"Operation failed, select at least one configuration.","ERROR",1);
            flagAdmin=!jCheckBox2.isSelected();
            jCheckBox2.setSelected(flagAdmin);
            return;
        }

        jB_Commit.setEnabled(true);

    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        JP_UsersJob.setTableRowHeigth();
    }//GEN-LAST:event_formComponentResized

    private void jB_loadUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_loadUsersActionPerformed
        jB_Commit.setEnabled(false);
        readAllUser();
    }//GEN-LAST:event_jB_loadUsersActionPerformed

    private void jComboBox_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_usersActionPerformed
        if(jComboBox_users.getSelectedItem()!=null)
        {
            if(((String)jComboBox_users.getSelectedItem()).equals("< select >"))
            {
                jCheckBox2.setEnabled(false);
                jCheckBox1.setEnabled(false);
            }else
            {
                jCheckBox2.setEnabled(true);
                jCheckBox1.setEnabled(true);
            }
        }

        if(jB_Commit.isEnabled() == true)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"No COMMIT has been done. Do you really want to proceed?","Info",6);
            int Yes_No = op.getAnswer();

            if(Yes_No == 1)
            {
                jB_Commit.setEnabled(false);
                jCheckBox2.setSelected(false);
                jCheckBox1.setSelected(false);
            }
            else
            {
                jComboBox_users.setSelectedItem(jL_selUser.getText());
                return;
            }

        }

        V_activeConfigs.removeAllElements();
        V_Available_files.removeAllElements();
        jL_activeConfigs.removeAll();
        jL_Available_files.removeAll();
        jL_selUser.setText("");

        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);


        if(jComboBox_users.getSelectedIndex() != 0)
        {
            String userSelected = (String)jComboBox_users.getSelectedItem();
            jL_selUser.setText(userSelected);


            if(ntmUser != null)
            {
                for (int i=0;i<ntmUser.length;i++)
                {
                    String appoLogin = new String(ntmUser[i].login).trim();
                    if(appoLogin.equals(userSelected))
                    {
                        //System.out.println("userSelected="+userSelected+ "   User Adimn="+ntmUser[i].userAdmin);
                        jCheckBox2.setSelected(ntmUser[i].userAdmin);
                        flagAdmin=ntmUser[i].userAdmin;
                        jCheckBox1.setSelected(ntmUser[i].configAdmin);
                        flagCalendar=ntmUser[i].configAdmin;
                        int j=0;

                        while( ! ((new String(ntmUser[i].enabledConfigurations[j])).trim()).equals("") )
                        {
                            String confActive = new String(ntmUser[i].enabledConfigurations[j]).trim();

                            //System.out.println("confActive "+confActive);
                            jL_activeConfigs.addElement("conf_sel.png","conf_sel.png",new String(confActive));
                            V_activeConfigs.addElement(new String(confActive));
                            j++;
                        }
                    }
                }
            }


            if(dir_entry != null)
            {
                for (int i=0;i<dir_entry.length;i++)
                {
                    String str_availableFile = new String(dir_entry[i].name).trim();
                    boolean find = false;
                    for(int j=0; j<V_activeConfigs.size(); j++)
                    {
                        String confActive_present =(String)V_activeConfigs.elementAt(j);
                        if(confActive_present.equals(str_availableFile))
                        {
                            find = true;
                            break;
                        }
                    }

                    if(find == false)
                    {
                        jL_Available_files.addElement("conf.png","conf.png",new String(str_availableFile));
                        V_Available_files.addElement(new String(str_availableFile));
                    }
                }
            }
        }

        jL_activeConfigs.repaint();
        jL_Available_files.repaint();
        jScroll1.validate();
        jScroll2.validate();
    }//GEN-LAST:event_jComboBox_usersActionPerformed

    private void jB_CommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_CommitActionPerformed

        OPERATION_TH = OP_COMMIT;

        TH = null;
        TH = new Thread(this,"Users Management - COMMIT -");
        TH.start();
    }//GEN-LAST:event_jB_CommitActionPerformed

    private void actionLists_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionLists_ActionPerformed

        if((V_Available_files.size() == 0)&&(V_activeConfigs.size() == 0))
        {
            return;
        }


        Object target = evt.getSource();

        boolean flag_move = false;
        if(target == jButton_Add)
        {
            flag_move = move(jL_Available_files,jL_activeConfigs,"conf_sel.png",V_Available_files,V_activeConfigs,false);
        }
        else if(target == jButton_AddAll)
        {
            flag_move = move(jL_Available_files,jL_activeConfigs,"conf_sel.png",V_Available_files,V_activeConfigs,true);
        }
        else if(target == jButton_Remove)
        {
            flag_move = move(jL_activeConfigs,jL_Available_files,"conf.png",V_activeConfigs,V_Available_files,false);
        }
        else if(target == jButton_RemoveAll)
        {
            flag_move = move(jL_activeConfigs,jL_Available_files,"conf.png",V_activeConfigs,V_Available_files,true);
        }

        if(flag_move == true)
        {
            jL_activeConfigs.repaint();
            jL_Available_files.repaint();
            jScroll1.validate();
            jScroll2.validate();
            jB_Commit.setEnabled(true);
        }

        /*System.out.println(" V_activeConfigs ");
        for(int i=0; i<V_activeConfigs.size(); i++)
            System.out.println(" Item : "+ (String)V_activeConfigs.elementAt(i));

        System.out.println(" V_Available_files ");
        for(int i=0; i<V_Available_files.size(); i++)
            System.out.println(" Item : "+ (String)V_Available_files.elementAt(i));*/

    }//GEN-LAST:event_actionLists_ActionPerformed

    private void jB_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_CloseActionPerformed
        closeFrame();
    }//GEN-LAST:event_jB_CloseActionPerformed

    private void closeFrame()
    {
        if(TH_WORKING == false)
        {
            if(jB_Commit.isEnabled() == true)
            {
                ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"No COMMIT has been done. Do you really want to exit?","Info",6);
                int Yes_No = op.getAnswer();

                if(Yes_No == 0)
                    return;
            }
            dispose();
        }
    }


    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeFrame();
    }//GEN-LAST:event_closeDialog

    public void run()
    {
        TH_WORKING = true;
        this.setCursor(cursor_wait);

        //Lock Table
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            this.setCursor(cursor_default);
            OPERATION_TH = -1;
            TH_WORKING = false;
            return;
        }

        if(OPERATION_TH == READ_ALL_USER) //readAllUser()
        {
            /*jL_desc.setText("WAIT... Load All User...");
            jL_desc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_animated.gif")));
            jB_loadUsers.setEnabled(false);

            //System.out.println("Connection = "+CONNECTION_CORBA_STS);

            if(CONNECTION_CORBA_STS)
                local_ALLUSER = CORBA.getAllUser(ADAMS_GlobalParam.ID_MOD_ADAMS);

            if(local_ALLUSER != null)
            {
                jComboBox_users.removeAllItems();
                jComboBox_users.addItem("< select >");
                for (int i=0; i<local_ALLUSER.length; i++)
                {
                    String loginUser = new String(local_ALLUSER[i].login).trim();
                    jComboBox_users.addItem(loginUser);
                }
            }
            else
                jComboBox_users.addItem("User Not Found");

            if( connectionCorba_mdm_server_server() )
            {
                readUsers();
                directoryListing();
                CORBA.shutDown_Conf();
            }

            jL_desc.setText("Select User");
            jL_desc.setIcon(null);
            jB_loadUsers.setEnabled(true);
            setEnabledGUI(true);
            jB_Close.setEnabled(true);*/
        }

        if(OPERATION_TH == OP_COMMIT)
        {
            excecuteCommit();
        }

        this.setCursor(cursor_default);
        OPERATION_TH = -1;
        TH_WORKING = false;
    }


    private synchronized boolean move(JListIcon l1,JListIcon l2,String icon,java.util.Vector V1,java.util.Vector V2,boolean all)
    {
        boolean Action_move = false;
        try
        {

            if (all)
            {
                int itemCount = l1.getItemCount();
                if(itemCount > 0)
                    Action_move = true;

                for (int i=0; i<itemCount; i++)
                {
                    l2.addElement(icon,icon,l1.get_ID_Value(i));
                    V2.add(V1.elementAt(i));//gestione vettori
                }
                l1.removeAll();
                if(V1 != null)
                    V1.removeAllElements();//gestione vettori
            }
            else
            {
                String[] items = l1.getStringValues();
                int[] itemIndexes = l1.getSelectedIndices();

                if(items.length > 0)
                    Action_move = true;

                l2.clearSelection();
                for (int i=0; i<items.length; i++)
                {
                    l2.addElement(icon,icon,items[i]); // add it
                    V2.addElement(V1.elementAt(itemIndexes[i]));//gestione vettori

                    l2.setSelectedIndex(l2.getItemCount()-1); // and select it
                    if (i == 0)
                    {
                        l2.setVisibleRowCount(l2.getItemCount()-1);
                    }
                }
                for (int i=itemIndexes.length-1; i>=0; i--)
                {
                    l1.remElement(itemIndexes[i]);
                    V1.removeElementAt(itemIndexes[i]); //gestione vettori
                }
            }
        }catch(Exception e)
        {
            Action_move=false;
        }

        if(jL_activeConfigs.getItemCount()==0)
        {
            flagCalendar=false;
            flagAdmin=false;
            jCheckBox1.setSelected(flagCalendar);
            jCheckBox2.setSelected(flagAdmin);
        }

        return Action_move;

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jP_usersManagement;
    private javax.swing.JLabel jL_icon;
    private javax.swing.JPanel JP_center;
    private javax.swing.JPanel jP_user_Profile;
    private javax.swing.JComboBox jComboBox_users;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JButton jB_loadUsers;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jP_confRepository;
    private javax.swing.JLabel jL_scroll1;
    private javax.swing.JScrollPane jScroll1;
    private javax.swing.JPanel jP_buttonChooser;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_AddAll;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_RemoveAll;
    private javax.swing.JLabel jL_scroll2;
    private javax.swing.JScrollPane jScroll2;
    private javax.swing.JPanel jP_south;
    private javax.swing.JLabel jL_iconDesc;
    private javax.swing.JLabel jL_selUser;
    private javax.swing.JButton jB_Commit;
    private javax.swing.JPanel jP_userJob_box;
    private javax.swing.JPanel jP_close;
    private javax.swing.JButton jB_Close;
    // End of variables declaration//GEN-END:variables

    private Vector V_activeConfigs               = null;
    private Vector V_Available_files             = null;

    private JListIcon jL_activeConfigs          = null;
    private JListIcon jL_Available_files        = null;
    private IconPool IcPool                     = null;

    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Cursor cursor_wait         = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor cursor_default      = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);

    private Thread TH           = null;
    private boolean TH_WORKING  = false;
    private int OPERATION_TH    = -1;

    private boolean CONNECTION_CORBA_STS = false;

    private CORBAConnection CORBA           = null;
    //private USER_PROFILE local_ALLUSER[]    = null;  // Tutti gli utenti
    private ADAMS_USER[] ntmUser              = null;
    private Vector V_ADAMS_USER               = null;

    private DIR_ENTRY[] dir_entry           = null; // Available files

    private final int READ_ALL_USER = 0;
    private final int OP_COMMIT     = 1;
    private boolean flagAdmin       = false;
    private boolean flagCalendar    = false;
    private boolean flagCommit      = true;
    ADAMS_JP_UsersJob JP_UsersJob = null;

}




