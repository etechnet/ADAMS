/*
 * appo.java
 *
 * Created on 10 luglio 2007, 12.45
 */

/**
 *
 * @author  luca
 */
import java.util.Vector;
import java.awt.Cursor;
import net.etech.*;
import net.etech.MDM.*;
import net.etech.ASP.*;
import net.etech.SSM.*;

/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_JP_AccessManager </b> </p>
 * 
 * <p> La classe estende un javax.swing.JPanel ed implementa l'interfaccia Runnable per l'attivazione di un Thread. </p> 
 * L'oggetto mette a diposizione un' interfaccia grafica con specifici widgets, per la creazione/configurazione dei Profili di accesso agli applicativi STS, mediante 
 * l'associazione di utenti pre-definiti per l'identificazione del personale addetto all'utilizzo del sistema STS.
 * La classe utilizza la classe 'conf_GlobalParam' contenente oggetti/metodi statici di uso generale per alcune funzionalitÃ  dell'applicativo, 
 * rilevante Ã¨ l'utilizzo dell'istanza della classe conf_CORBAConnection, specifica per la comunicazione remota con i server mediante protocollo CORBA.
 * <pre>
 *  Metodi utilizzati della classe conf_CORBAConnection:
 *
 *      - getConfigLib()
 *      - getProfileConfiguration(<profile>)
 *      - setProfileConfiguration(...)
 *      - getUserConfiguration(...)
 *      - setUserConfiguration(...)
 * * </pre>
 *
 *@see conf_GlobalParam
 *@see conf_CORBAConnection
 */
public class conf_JP_AccessManager extends javax.swing.JPanel implements Runnable {

    /** 
     *Costruttore della classe.
     *Istanzia ed inizializza gli oggetti necessari all'interfaccia grafica e la loro funzionalitÃ .
     *@param parent javax.swing.JFrame padre della classe chiamante.
     */
    public conf_JP_AccessManager(javax.swing.JFrame parent) {
        
        this.frameParent = parent;       
        System.out.println("prima initComponents");
        initComponents();
        System.out.println("dopo initComponents");
                
        jTF_nameProfile.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,conf_GlobalParam.MAX_NAME_PROFILE));

        jT_Login_PSWD.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,conf_GlobalParam.DIM_PSWD_LOGIN));
        jPass.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,conf_GlobalParam.DIM_PSWD_PASSWORD));
        jPass2.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,conf_GlobalParam.DIM_PSWD_PASSWORD));
        
        jTF_NameNewHost.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,conf_GlobalParam.DIM_PSWD_NOME_NODO));
        jTF_host_1.setDocument(new conf_JTextFieldFilter(conf_JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,conf_GlobalParam.DIM_PSWD_NOME_NODO));
        
        IcPool = new conf_IconPool("/images_conf/");
        
        jList_Profile = new conf_JListIcon(IcPool);
        jList_Profile.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_profile = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_ProfileMousePressed(evt);
            }
        });
        jScrollP_Profile.setViewportView(jList_Profile);
        
        
        jList_Class = new conf_JListIcon(IcPool);
        jList_Class.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_Class = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_ClassMousePressed(evt);
            }
        });
        jScroll_Class.setViewportView(jList_Class);
        
        jList_Function_en = new conf_JListIcon(IcPool);
        jList_Function_en.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_function = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_Function_enMousePressed(evt);
            }
        });
        jScroll_Function_en.setViewportView(jList_Function_en);
        
        
        jList_Function_dis = new conf_JListIcon(IcPool);
        jList_Function_dis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScroll_Function_dis.setViewportView(jList_Function_dis);
        
        
        // **************************
        // ---- User
        
        jList_User = new conf_JListIcon(IcPool);
        jList_User.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                
        eventList_user = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_UserMousePressed(evt);
            }
        });
        jScroll_user.setViewportView(jList_User);
        
        jList_Profile_dis = new conf_JListIcon(IcPool);
        jList_Profile_dis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_Prof_dis = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_Profile_disMousePressed(evt);
            }
        });        
        jScroll_ProfDis_2.setViewportView(jList_Profile_dis);
        
        jList_Profile_en = new conf_JListIcon(IcPool);
        jList_Profile_en.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_Prof_en = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_Profile_enMousePressed(evt);
            }
        });        
        jScroll_ProfEna_2.setViewportView(jList_Profile_en);
        
        // EVENTI jComboBox        
        event_jCBox_class = (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_class_ActionPerformed(evt);
            }
        });
            
        event_jCBox_Role = (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_Role_ActionPerformed(evt);
            }
        });
        
        //EVENTO TAB
        event_jTab_Access_Manager = (new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabAccessManager_StateChanged(evt);
            }
        });
        
        //******************* PSWD
        jList_Hosts = new conf_JListIcon(IcPool);
        jList_Hosts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eventList_Hosts = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList_HostsMousePressed(evt);
            }
        });        
        
        //jScroll_Hosts.setViewportView(jList_Hosts);
        //jList_Hosts.updateUI();
        
        //System.out.println("repaint 1");
        jList_Hosts.validate();   
    	jList_Hosts.updateUI();
    	jScroll_Hosts.validate();       	
    	jScroll_Hosts.setViewportView(jList_Hosts);
        //System.out.println("repaint 1");
        
                        
        // FONT GLOBALI  
        jList_User.set_Font(conf_GlobalParam.Font1_bold);
        jList_Profile.set_Font(conf_GlobalParam.Font1_bold);
        jList_Function_en.set_Font(conf_GlobalParam.Font1_bold);
        jList_Function_dis.set_Font(conf_GlobalParam.Font1_bold);
        jList_Class.set_Font(conf_GlobalParam.Font1_bold);
        jList_Profile_dis.set_Font(conf_GlobalParam.Font1_bold);
        jList_Profile_en.set_Font(conf_GlobalParam.Font1_bold);
        jList_Hosts.set_Font(conf_GlobalParam.Font1_bold);
        
        jLabel1.setFont(conf_GlobalParam.Font1_bold);
        jLabel2.setFont(conf_GlobalParam.Font1_bold);
        jLabel4.setFont(conf_GlobalParam.Font1_bold);
        jLabel5.setFont(conf_GlobalParam.Font1_bold);
        jLabel6.setFont(conf_GlobalParam.Font1_bold);
        jLabel7.setFont(conf_GlobalParam.Font1_bold);
        jLabel8.setFont(conf_GlobalParam.Font1_bold);
        jLabel9.setFont(conf_GlobalParam.Font1_bold);
        jLabel10.setFont(conf_GlobalParam.Font1_bold);
        jLabel11.setFont(conf_GlobalParam.Font1_bold);
        jLabel12.setFont(conf_GlobalParam.Font1_bold);
        jLabel13.setFont(conf_GlobalParam.Font1_bold);
        jLabel14.setFont(conf_GlobalParam.Font1_bold);
        jLabel15.setFont(conf_GlobalParam.Font1_bold);
        jLabel16.setFont(conf_GlobalParam.Font1_bold);
        jLabel17.setFont(conf_GlobalParam.Font1_bold);
        jLabel18.setFont(conf_GlobalParam.Font1_bold);        
        jLabel20.setFont(conf_GlobalParam.Font1_bold);
        jLabel21.setFont(conf_GlobalParam.Font1_bold);
        jL_nodes.setFont(conf_GlobalParam.Font1_bold);
        jL_desc.setFont(conf_GlobalParam.Font1_bold);
        jLabel27.setFont(conf_GlobalParam.Font1_bold);
        jLabel28.setFont(conf_GlobalParam.Font1_bold);
        jLabel24.setFont(conf_GlobalParam.Font1_bold);
        jLabel25.setFont(conf_GlobalParam.Font1_bold);
        jLabel26.setFont(conf_GlobalParam.Font1_bold);
        jL_newHost.setFont(conf_GlobalParam.Font1_bold);
        jB_reload_PSWD.setFont(conf_GlobalParam.Font1_bold);
        jCheck_edit_PSWD.setFont(conf_GlobalParam.Font1_bold);
        jCheck_del_PSWD.setFont(conf_GlobalParam.Font1_bold);
        
        jTF_nameFunction.setFont(conf_GlobalParam.Font1_plain);
        jTF_descFunction.setFont(conf_GlobalParam.Font1_plain);
        jTF_verFunction.setFont(conf_GlobalParam.Font1_plain);
        jTF_releaseFunction.setFont(conf_GlobalParam.Font1_plain);
        jTF_authorFunction.setFont(conf_GlobalParam.Font1_plain);
        jTF_vendorFunction.setFont(conf_GlobalParam.Font1_plain);        
        jTF_login.setFont(conf_GlobalParam.Font1_plain);
        jTF_nomeUtente.setFont(conf_GlobalParam.Font1_plain);
        jTF_descUser.setFont(conf_GlobalParam.Font1_plain);
        jCBox_UserRoleDetail.setFont(conf_GlobalParam.Font1_plain);
        jCBox_Role.setFont(conf_GlobalParam.Font0);
        jCBox_class.setFont(conf_GlobalParam.Font0);
        jB_enabledPro.setFont(conf_GlobalParam.Font0);
        jB_disablePro.setFont(conf_GlobalParam.Font0);
        jL_Enabled.setFont(conf_GlobalParam.Font1_plain);
        jB_reload.setFont(conf_GlobalParam.Font0);
        jCB_edit_profile.setFont(conf_GlobalParam.Font0);
        jCB_edit_user.setFont(conf_GlobalParam.Font0);
        jL_nameP.setFont(conf_GlobalParam.Font1_bold);
        jL_descP.setFont(conf_GlobalParam.Font1_bold);
        jTF_nameProfile.setFont(conf_GlobalParam.Font1_plain);
        jTA_descProfile.setFont(conf_GlobalParam.Font1_plain);
        
        jCBox_TypePSWD.setFont(conf_GlobalParam.Font1_plain);
        jT_Login_PSWD.setFont(conf_GlobalParam.Font1_plain);
        jTF_NameNewHost.setFont(conf_GlobalParam.Font1_plain);
        jTF_host_1.setFont(conf_GlobalParam.Font1_plain);
        
        jB_reloadAll_user.setFont(conf_GlobalParam.Font0);
        
        //SetCursor
        jList_User.setCursor(Cur_hand);
        jList_Profile.setCursor(Cur_hand);
        jList_Function_en.setCursor(Cur_hand);
        jList_Function_dis.setCursor(Cur_hand);
        jList_Class.setCursor(Cur_hand);
        jList_Profile_dis.setCursor(Cur_hand);
        jList_Profile_en.setCursor(Cur_hand);
        jB_reload.setCursor(Cur_hand);
        jB_Add.setCursor(Cur_hand);
        jB_AddAll.setCursor(Cur_hand);
        jB_Rem.setCursor(Cur_hand);
        jB_RemAll.setCursor(Cur_hand);
        jRB_ADD_P.setCursor(Cur_hand);
        jRB_DEL_P.setCursor(Cur_hand);
        jRB_MOD_P.setCursor(Cur_hand);
        jRB_MOD_U.setCursor(Cur_hand);
        jCB_edit_profile.setCursor(Cur_hand);
        jCB_edit_user.setCursor(Cur_hand);
        jB_commit.setCursor(Cur_hand);
        jB_commit_user.setCursor(Cur_hand);
        jCBox_Role.setCursor(Cur_hand);
        jCBox_class.setCursor(Cur_hand);
        jB_enabledPro.setCursor(Cur_hand);
        jB_disablePro.setCursor(Cur_hand);
        
        jCBox_TypePSWD.setCursor(Cur_hand);
        jList_Hosts.setCursor(Cur_hand);
        jB_reload_PSWD.setCursor(Cur_hand);
        jCheck_edit_PSWD.setCursor(Cur_hand);
        jCheck_del_PSWD.setCursor(Cur_hand);
        jB_commit_PSWD.setCursor(Cur_hand);
        jB_addHost.setCursor(Cur_hand);
               
        
        jB_reloadAll_user.setCursor(Cur_hand);
 
 System.out.println("prima initLoadAll");
        initLoadAll();
  System.out.println("dopo initLoadAll");       
        jCB_edit_user.setSelected(false);
          System.out.println("1");  
        setEnabledGuiEdit_U(false);
                  System.out.println("2");  
        jCB_edit_profile.setSelected(false);
                  System.out.println("3");  
        setEnabledGuiEdit_P(false);
                  System.out.println("4");  
        
        if(conf_GlobalParam.RUOLO == 1)
        {
            jTab_Access_Manager.setSelectedIndex(1);
            jTab_Access_Manager.setEnabledAt(0,false);
        }
                  System.out.println("5");  
       /* else if(conf_GlobalParam.RUOLO == 777) // luca ultimo - commenta la else if
        {
            jB_enabledPro.setEnabled(false);
            jB_disablePro.setEnabled(false);
        }*/

        jTab_Access_Manager.setEnabledAt(2,false);
        System.out.println("conf_GlobalParam.RUOLO="+conf_GlobalParam.RUOLO); 
        System.out.println("getProfile_Enabled_PSWD()="+getProfile_Enabled_PSWD());   
        if( (conf_GlobalParam.RUOLO == 777) || (getProfile_Enabled_PSWD() == true) ) //PSWD
        {
            jTab_Access_Manager.setEnabledAt(2,true);
            System.out.println("6");  
            //stato_psMonitor = conf_GlobalParam.CORBAConn.openConnection_psMonitorMasterServer();
            System.out.println("7");  
            System.out.println("Connection Server psMonitorMasterServer   ==>  "+stato_psMonitor);
            //Init_PSWD();
            System.out.println("10");  
            for(int i=0; i<OGGETTO_PSWD.length; i++)
            {
                jCBox_TypePSWD.addItem(OGGETTO_PSWD[i]);
            }
            System.out.println("11");              
            jCheck_edit_PSWD.setSelected(false);
            setEnabledGuiEdit_PSWD(false);            
            
        } 
         
    }
    
    private void read_Profile(int ID_CLASS)
    {
        //System.out.println("read_Profile IDclasse ---> " + ID_CLASS);
        
        ID_STR_PROFILE = "";
                
        jList_Profile.removeAll();        
        jList_Profile.removeMouseListener(eventList_profile);
        
        if(jCB_edit_profile.isSelected() == false)
        {
            jTF_nameProfile.setText("");
            jTA_descProfile.setText("");
        }
        else if( jRB_DEL_P.isSelected() || jRB_MOD_P.isSelected() )
        {
            jB_commit.setEnabled(false);
        }
             
        if(ID_CLASS == -1)
        {
            for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
                jList_Profile.addElement("profile.png", "profile.png", new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim());
        }
        else
        {
            for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
            {                
                if(ID_CLASS == conf_GlobalParam.config_lib.profileSeq[i].idClass)
                    jList_Profile.addElement("profile.png", "profile.png", new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim());
            }
        }
        jScrollP_Profile.setViewportView(jList_Profile);
        jList_Profile.addMouseListener(eventList_profile);
    }
    
    private void read_ProfileUser(int ID_CLASS,S_USER s_user)
    {
        //System.out.println("read_ProfileUser IDclasse ---> " + ID_CLASS);
        
        jList_Profile_en.removeAll();
        jList_Profile_en.removeMouseListener(eventList_Prof_en);
        jList_Profile_dis.removeAll();        
        jList_Profile_dis.removeMouseListener(eventList_Prof_dis);
        
        Vect_Profile_Disabled.removeAllElements();
        Vect_Profile_Enabled.removeAllElements();
        Vect_Profile_Enabled_otherClass.removeAllElements();
        
        for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
        {
           String str_profile = new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim();
           //System.out.println("str_profile ------------------------- "+str_profile);

           boolean enabled = false;
           if(s_user != null)
           {
                for(int x=0; x<s_user.profileSeq.length; x++)
                {
                    String str_profile_user = new String(s_user.profileSeq[x].profile).trim();
                    
                    //System.out.println("str_profile_user ------------------------- "+str_profile_user);
                    if( str_profile.equals(str_profile_user) )
                    {
                        enabled = true;                            

                        if (ID_CLASS == -1)
                        {
                            jList_Profile_en.addElement("profile_en.png", "profile_en.png", new String(str_profile_user).trim());
                            Vect_Profile_Enabled.addElement(s_user.profileSeq[x]);
                        }
                        else if (ID_CLASS == s_user.profileSeq[x].idClass)
                        {
                            jList_Profile_en.addElement("profile_en.png", "profile_en.png", new String(str_profile_user).trim());
                            Vect_Profile_Enabled.addElement(s_user.profileSeq[x]);
                        }
                        else
                        {
                            Vect_Profile_Enabled_otherClass.addElement(s_user.profileSeq[x]);
                            //System.out.println("Vect_Profile_Enabled_otherClass "+new String(s_user.profileSeq[x].profile).trim());
                        }                        
                        break;
                    }
                }
            }
           
            if(enabled == false)
            {
                if (ID_CLASS == -1)
                {
                    jList_Profile_dis.addElement("profile_dis.png", "profile_dis.png",str_profile);
                    Vect_Profile_Disabled.addElement(conf_GlobalParam.config_lib.profileSeq[i]);
                }
                else if (ID_CLASS == conf_GlobalParam.config_lib.profileSeq[i].idClass)
                {
                    jList_Profile_dis.addElement("profile_dis.png", "profile_dis.png",str_profile);
                    Vect_Profile_Disabled.addElement(conf_GlobalParam.config_lib.profileSeq[i]);
                }
            }
        }
        
        jScroll_ProfDis_2.setViewportView(jList_Profile_dis);
        jList_Profile_dis.addMouseListener(eventList_Prof_dis);
        jScroll_ProfEna_2.setViewportView(jList_Profile_en);
        jList_Profile_en.addMouseListener(eventList_Prof_en);
    }
    
    
    private void read_Profile(int ID_CLASS, int ID_FUNCTION)
    {
        //System.out.println("read_Profile ID_CLASS ---> " + ID_CLASS +" ID_FUNCTION "+ID_FUNCTION);
        
        ID_STR_PROFILE = "";
        jList_Profile.removeAll();
        jList_Profile.removeMouseListener(eventList_profile);
        
        if(jCB_edit_profile.isSelected() == false)
        {
            jTF_nameProfile.setText("");
            jTA_descProfile.setText("");
        }
             
        if(ID_CLASS == -1)
        {
            for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
                jList_Profile.addElement("profile.png", "profile.png", new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim());
        }
        else
        {
            for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
            {
                if(ID_CLASS == conf_GlobalParam.config_lib.profileSeq[i].idClass)
                {
                    for(int x=0; x<conf_GlobalParam.config_lib.profileSeq[i].functionSeq.length; x++)
                    {
                        if(ID_FUNCTION == conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].idFunction)
                            jList_Profile.addElement("profile.png", "profile.png", new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim());
                    }
                }
            }
        }
        jScrollP_Profile.setViewportView(jList_Profile);
        jList_Profile.addMouseListener(eventList_profile);
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
    
    
    private boolean isProfilePresent(String profile)
    {
        for(int i=0;i<conf_GlobalParam.config_lib.profileSeq.length; i++)
        {            
            String strAppo_profile = new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim();            
            if( (strAppo_profile.toUpperCase()).equals(profile.toUpperCase()) )
                return true;
        }
        return false;
    }
    
    private void read_Class(int ID_CLASS)
    {
        //System.out.println("read_Class profile_name ---> " + ID_CLASS);
        jList_Class.removeAll();
        jList_Class.removeMouseListener(eventList_Class);
        ID_ClassSelected = -1;
               
        if(ID_CLASS == -1)
        {
            for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
                jList_Class.addElement("package.png","package.png",(new String(conf_GlobalParam.config_lib.classSeq[j].descClass)).trim());
        }
        else
        {
            if( jRB_MOD_P.isSelected() && jCB_edit_profile.isSelected() )
            {
                int indexClassSel = -1;
                for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
                {
                    String descClass = new String(conf_GlobalParam.config_lib.classSeq[j].descClass).trim();
                    jList_Class.addElement("package.png","package.png",descClass);
                    
                    if(ID_CLASS == conf_GlobalParam.config_lib.classSeq[j].idClass)
                    {
                        indexClassSel = j;
                        ID_ClassSelected = read_ID_Class(descClass);
                    }
                }
                jList_Class.setSelectedIndex(indexClassSel);
            }
            else
            {
                for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
                {
                    if(ID_CLASS == conf_GlobalParam.config_lib.classSeq[j].idClass)
                    {
                        String str_class = new String(conf_GlobalParam.config_lib.classSeq[j].descClass).trim();
                        if(jRB_DEL_P.isSelected())
                            ID_ClassSelected = read_ID_Class(str_class);
                        
                        jList_Class.addElement("package.png","package.png",str_class);
                    }
                }            
            }
        }
                     
        jScroll_Class.setViewportView(jList_Class);
        jList_Class.addMouseListener(eventList_Class);
        
        
        /// ---- TAB User  
        jCBox_class.removeActionListener(event_jCBox_class);
        jCBox_class.removeAllItems();
        jCBox_class.addItem("All Class");
        for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
            jCBox_class.addItem(new String(conf_GlobalParam.config_lib.classSeq[j].descClass).trim());
        jCBox_class.addActionListener(event_jCBox_class);
    }
    
    private int read_ID_Class(String desc_class)
    {
         for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
         {
            String dcs = new String(conf_GlobalParam.config_lib.classSeq[j].descClass).trim();
            if(dcs.equals(desc_class))
            {
                return conf_GlobalParam.config_lib.classSeq[j].idClass;
            }
         }
         return -1;    
    }
    
    private String read_desc_Class(int id_class)
    {
         for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
         {
            int idC = conf_GlobalParam.config_lib.classSeq[j].idClass;
            if(idC == id_class )
            {
                return new String(conf_GlobalParam.config_lib.classSeq[j].descClass).trim();
            }
         }
         return "";    
    }
    
    
    private void read_Function(int ID_CLASS)
    {
        //System.out.println("read_Function ID_CLASS ---> " + ID_CLASS);
        jList_Function_en.removeAll();
        jList_Function_en.removeMouseListener(eventList_function);
        jList_Function_dis.removeAll();
        jList_Function_dis.removeMouseListener(eventList_function);
        
        Vect_Function_Enabled.removeAllElements();        
        Vect_Function_Disable.removeAllElements();
        
        if(ID_CLASS == -1)
        {
            for(int i = 0; i<conf_GlobalParam.config_lib.functionSeq.length; i++)
            {
                jList_Function_dis.addElement("function_dis.png", "function_dis.png", (new String(conf_GlobalParam.config_lib.functionSeq[i].nameFunction)).trim());
                Vect_Function_Disable.addElement(conf_GlobalParam.config_lib.functionSeq[i]);
            }
        }
        else
        {            
            for(int i = 0; i<conf_GlobalParam.config_lib.functionSeq.length; i++)
            {
                if(ID_CLASS == conf_GlobalParam.config_lib.functionSeq[i].idClass)
                {
                    jList_Function_dis.addElement("function_dis.png", "function_dis.png", (new String(conf_GlobalParam.config_lib.functionSeq[i].nameFunction)).trim());
                    Vect_Function_Disable.addElement(conf_GlobalParam.config_lib.functionSeq[i]);
                }
            }    
        }
        jScroll_Function_en.setViewportView(jList_Function_en);        
        jList_Function_en.addMouseListener(eventList_function);
        jScroll_Function_dis.setViewportView(jList_Function_dis);
        jList_Function_dis.addMouseListener(eventList_function);
    }
    
    
    private boolean getProfile_Enabled_PSWD()
    {
        //System.out.println("getProfile_Enabled_PSWD(...)");
        try
        {
            if(conf_GlobalParam.COMMON_USER_PROFILE_LOGON.compareTo("") != 0)
            {

                S_PROFILE s_profile_appo = conf_GlobalParam.CORBAConn.getProfileConfiguration(conf_GlobalParam.COMMON_USER_PROFILE_LOGON);

                if(s_profile_appo != null)
                {                
                    for(int i=0; i<s_profile_appo.functionSeq.length; i++)
                    {
                        // ID=2 --> Password Management
                        if(s_profile_appo.functionSeq[i].idFunction == 2)
                        {
                            //System.out.println("OK --> "+new String(s_profile_appo.functionSeq[i].nameFunction).trim());
                            return true;
                        }
                    }

                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception --> getProfile_Enabled_PSWD()");
            return false;
        }
        return false;
    }
    
    private void read_Function(String STR_PROFILO)
    {
        //System.out.println("read_Function --> ID_PROFILO "+STR_PROFILO);
        jList_Function_en.removeAll();
        jList_Function_en.removeMouseListener(eventList_function);
        jList_Function_dis.removeAll();
        jList_Function_dis.removeMouseListener(eventList_function);
        
        Vect_Function_Enabled.removeAllElements();
        Vect_Function_Disable.removeAllElements();

        for(int y=0; y<conf_GlobalParam.config_lib.profileSeq.length; y++)
        {
            // Funzioni abilitate del Profilo
            if( new String(conf_GlobalParam.config_lib.profileSeq[y].profile).trim().equals(STR_PROFILO) )    
            {
                for(int z=0; z<conf_GlobalParam.config_lib.profileSeq[y].functionSeq.length; z++)
                {
                    jList_Function_en.addElement("function_en.png", "function_en.png", (new String(conf_GlobalParam.config_lib.profileSeq[y].functionSeq[z].nameFunction)).trim());
                    Vect_Function_Enabled.addElement(conf_GlobalParam.config_lib.profileSeq[y].functionSeq[z]);
                }
                break;
            }
        }
        
        //Tutte le funzioni NON abilitate dal profilo selezionato.
        for(int i=0;i<conf_GlobalParam.config_lib.functionSeq.length; i++)
        {
            boolean addDisableFunction = false;
            for(int x=0; x<Vect_Function_Enabled.size(); x++)
            {

                S_FUNCTION funcEnab_appo = (S_FUNCTION)Vect_Function_Enabled.elementAt(x);
                if(funcEnab_appo.idClass == conf_GlobalParam.config_lib.functionSeq[i].idClass)
                {
                    //System.out.println("funcEnab_appo.idClass --> "+funcEnab_appo.idClass);
                    //System.out.println(funcEnab_appo.idFunction+" == "+conf_GlobalParam.config_lib.functionSeq[i].idFunction);

                    addDisableFunction = true;
                    if(funcEnab_appo.idFunction == conf_GlobalParam.config_lib.functionSeq[i].idFunction)
                    {
                        addDisableFunction = false;
                        break;
                    }
                    //System.out.println("addDisableFunction "+ addDisableFunction);
                }
            }                
            if(addDisableFunction == true)
            {
                jList_Function_dis.addElement("function_dis.png", "function_dis.png", (new String(conf_GlobalParam.config_lib.functionSeq[i].nameFunction)).trim());
                Vect_Function_Disable.addElement(conf_GlobalParam.config_lib.functionSeq[i]);
            }
        }

        jScroll_Function_en.setViewportView(jList_Function_en);        
        jList_Function_en.addMouseListener(eventList_function);
        jScroll_Function_dis.setViewportView(jList_Function_dis);
        jList_Function_dis.addMouseListener(eventList_function);
    }
    
    private void read_LoginUser(int ID_ROLE)
    {
        jList_User.removeAll();
        jList_User.removeMouseListener(eventList_user);
        s_user = null;
        if(ID_ROLE == -1)
        {
            for(int j = 0; j < conf_GlobalParam.config_lib.userSeq.length; j++)
            {
                if(conf_GlobalParam.config_lib.userSeq[j].abilitazioneUtente)
                    jList_User.addElement("user_green.png", "user_green.png", (new String(conf_GlobalParam.config_lib.userSeq[j].login)).trim());
                else
                    jList_User.addElement("user_red.png", "user_red.png", (new String(conf_GlobalParam.config_lib.userSeq[j].login)).trim());
            }
        } 
        else
        {
            for(int k = 0; k < conf_GlobalParam.config_lib.userSeq.length; k++)
            {
                if(ID_ROLE == conf_GlobalParam.config_lib.userSeq[k].idRole)
                {
                    if(conf_GlobalParam.config_lib.userSeq[k].abilitazioneUtente)
                        jList_User.addElement("user_green.png", "user_green.png", (new String(conf_GlobalParam.config_lib.userSeq[k].login)).trim());
                    else
                        jList_User.addElement("user_red.png", "user_red.png", (new String(conf_GlobalParam.config_lib.userSeq[k].login)).trim());
                }
            }

        }
        jScroll_user.setViewportView(jList_User);
        jList_User.addMouseListener(eventList_user);
    
    }
    
    private void load_User_on_Profile( String str_profile, int ID_ROLE )
    {    
        jList_User.removeAll();
        jList_User.removeMouseListener(eventList_user);
        s_user = null;
        
        S_PROFILE s_profile_appo = conf_GlobalParam.CORBAConn.getProfileConfiguration(str_profile);
               
        if(s_profile_appo != null)
        {
            if(ID_ROLE == -1)
            {
                for(int j = 0; j < s_profile_appo.loginSeq.length; j++)
                {
                    String str_login = (new String(s_profile_appo.loginSeq[j].login)).trim();
                    if(getUserAbility(str_login))
                        jList_User.addElement("user_green.png", "user_green.png", str_login);
                    else
                        jList_User.addElement("user_red.png", "user_red.png", str_login);
                }
            }
            else
            {
                for(int k = 0; k < conf_GlobalParam.config_lib.userSeq.length; k++)
                {
                    String str_LOGIN = new String(conf_GlobalParam.config_lib.userSeq[k].login).trim();

                    for(int z=0; z<s_profile_appo.loginSeq.length; z++)
                    {
                        if ( (ID_ROLE == conf_GlobalParam.config_lib.userSeq[k].idRole) && 
                                (  (new String(s_profile_appo.loginSeq[z].login).trim()).equals(str_LOGIN)) )
                        {
                            if(conf_GlobalParam.config_lib.userSeq[k].abilitazioneUtente)
                                jList_User.addElement("user_green.png","user_green.png",str_LOGIN);
                            else
                                jList_User.addElement("user_red.png","user_red.png",str_LOGIN);
                            
                            break;
                        }
                    }
                }

            }
        }
        jScroll_user.setViewportView(jList_User);
        jList_User.addMouseListener(eventList_user);
    }
    
    
   /**
     *Questo metodo restituisce la descrizione estesa annessa ad un determinato <Profilo>. 
     *@param profile <Profile> d'interesse per il quale si intende conoscere la sua descrizione.
     *@return In Esito positivo il metodo ritorna la descrizione del profilo, in caso contrario il seguente messaggio:"NO Description";
     */ 
   public String getDescProfile(String profile)
   {
        for(int i=0; i<conf_GlobalParam.config_lib.profileSeq.length; i++)
        {
            String appo_profile = new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim();
            if(appo_profile.equals(profile))
            {
                return (new String(conf_GlobalParam.config_lib.profileSeq[i].descProfile).trim());
            }
        }
        return "NO Description";
   }
   
   /**
     *Questo metodo restituisce lo stato di abilitazione di una determinata <login>.
     *@param login <login> d'interesse per il quale si intende conoscere la sua abilitazione.
     *@return Se true, login abilitata. Se False abilitazione negata.
     */ 
   public boolean getUserAbility(String login)
   {
        for(int i=0; i<conf_GlobalParam.config_lib.userSeq.length; i++)
        {
            if( (new String(conf_GlobalParam.config_lib.userSeq[i].login).trim()).equals(login) )
            {
                return conf_GlobalParam.config_lib.userSeq[i].abilitazioneUtente;
            }
        }
        return false;
   }
    
    
    /** This method is called from within the constructor to
     * initialize the form.S
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   private void initComponents() {//GEN-BEGIN:initComponents
       buttonGroup1 = new javax.swing.ButtonGroup();
       buttonGroup2 = new javax.swing.ButtonGroup();
       jLabel21 = new javax.swing.JLabel();
       jTab_Access_Manager = new javax.swing.JTabbedPane();
       jP_TotProfile = new javax.swing.JPanel();
       jP_PROFILE = new javax.swing.JPanel();
       jLabel11 = new javax.swing.JLabel();
       jScrollP_Profile = new javax.swing.JScrollPane();
       jP_CLASS = new javax.swing.JPanel();
       jLabel2 = new javax.swing.JLabel();
       jScroll_Class = new javax.swing.JScrollPane();
       jP_FunctionDetail = new javax.swing.JPanel();
       jLabel4 = new javax.swing.JLabel();
       jLabel5 = new javax.swing.JLabel();
       jLabel6 = new javax.swing.JLabel();
       jLabel7 = new javax.swing.JLabel();
       jLabel8 = new javax.swing.JLabel();
       jLabel9 = new javax.swing.JLabel();
       jTF_nameFunction = new javax.swing.JTextField();
       jTF_descFunction = new javax.swing.JTextField();
       jTF_verFunction = new javax.swing.JTextField();
       jTF_releaseFunction = new javax.swing.JTextField();
       jTF_authorFunction = new javax.swing.JTextField();
       jTF_vendorFunction = new javax.swing.JTextField();
       jLabel10 = new javax.swing.JLabel();
       jLabel1 = new javax.swing.JLabel();
       jScroll_Function_en = new javax.swing.JScrollPane();
       jScroll_Function_dis = new javax.swing.JScrollPane();
       jLabel3 = new javax.swing.JLabel();
       jP_buttonFunctions = new javax.swing.JPanel();
       jB_Add = new javax.swing.JButton();
       jB_Rem = new javax.swing.JButton();
       jB_RemAll = new javax.swing.JButton();
       jB_AddAll = new javax.swing.JButton();
       jP_button = new javax.swing.JPanel();
       jB_reload = new javax.swing.JButton();
       jB_commit = new javax.swing.JButton();
       jP_bOper = new javax.swing.JPanel();
       jCB_edit_profile = new javax.swing.JCheckBox();
       jRB_ADD_P = new javax.swing.JRadioButton();
       jRB_MOD_P = new javax.swing.JRadioButton();
       jRB_DEL_P = new javax.swing.JRadioButton();
       jP_operation = new javax.swing.JPanel();
       jL_nameP = new javax.swing.JLabel();
       jTF_nameProfile = new javax.swing.JTextField();
       jL_descP = new javax.swing.JLabel();
       jScrollPane1 = new javax.swing.JScrollPane();
       jTA_descProfile = new javax.swing.JTextArea();
       jP_TotUser = new javax.swing.JPanel();
       jPanel8 = new javax.swing.JPanel();
       jScroll_user = new javax.swing.JScrollPane();
       jLabel13 = new javax.swing.JLabel();
       jLabel22 = new javax.swing.JLabel();
       jCBox_Role = new javax.swing.JComboBox();
       jPanel10 = new javax.swing.JPanel();
       jPanel12 = new javax.swing.JPanel();
       jLabel14 = new javax.swing.JLabel();
       jTF_login = new javax.swing.JTextField();
       jLabel15 = new javax.swing.JLabel();
       jTF_nomeUtente = new javax.swing.JTextField();
       jLabel16 = new javax.swing.JLabel();
       jTF_descUser = new javax.swing.JTextField();
       jLabel17 = new javax.swing.JLabel();
       jCBox_UserRoleDetail = new javax.swing.JComboBox();
       jLabel18 = new javax.swing.JLabel();
       jL_Enabled = new javax.swing.JLabel();
       jPanel13 = new javax.swing.JPanel();
       jLabel20 = new javax.swing.JLabel();
       jPanel1 = new javax.swing.JPanel();
       jLabel12 = new javax.swing.JLabel();
       jCBox_class = new javax.swing.JComboBox();
       jLabel19 = new javax.swing.JLabel();
       jScroll_ProfDis_2 = new javax.swing.JScrollPane();
       jPanel3 = new javax.swing.JPanel();
       jLabel23 = new javax.swing.JLabel();
       jScroll_ProfEna_2 = new javax.swing.JScrollPane();
       jPanel2 = new javax.swing.JPanel();
       jB_reloadAll_user = new javax.swing.JButton();
       jB_commit_user = new javax.swing.JButton();
       jPanel4 = new javax.swing.JPanel();
       jCB_edit_user = new javax.swing.JCheckBox();
       jRB_MOD_U = new javax.swing.JRadioButton();
       jPanel5 = new javax.swing.JPanel();
       jB_enabledPro = new javax.swing.JButton();
       jB_disablePro = new javax.swing.JButton();
       jScrollP_descProf = new javax.swing.JScrollPane();
       jTArea_descProfile = new javax.swing.JTextArea();
       jP_PSWD = new javax.swing.JPanel();
       jPanel6 = new javax.swing.JPanel();
       jPanel7 = new javax.swing.JPanel();
       jLabel24 = new javax.swing.JLabel();
       jT_Login_PSWD = new javax.swing.JTextField();
       jLabel25 = new javax.swing.JLabel();
       jLabel26 = new javax.swing.JLabel();
       jLabel27 = new javax.swing.JLabel();
       jCBox_TypePSWD = new javax.swing.JComboBox();
       jPanel9 = new javax.swing.JPanel();
       jL_desc = new javax.swing.JLabel();
       jPass = new javax.swing.JPasswordField();
       jPass2 = new javax.swing.JPasswordField();
       jCheck_del_PSWD = new javax.swing.JCheckBox();
       jLabel28 = new javax.swing.JLabel();
       jTF_host_1 = new javax.swing.JTextField();
       jScroll_Hosts = new javax.swing.JScrollPane();
       jL_nodes = new javax.swing.JLabel();
       jP_addHosts = new javax.swing.JPanel();
       jTF_NameNewHost = new javax.swing.JTextField();
       jB_addHost = new javax.swing.JButton();
       jL_newHost = new javax.swing.JLabel();
       jPanel11 = new javax.swing.JPanel();
       jB_reload_PSWD = new javax.swing.JButton();
       jB_commit_PSWD = new javax.swing.JButton();
       jPanel14 = new javax.swing.JPanel();
       jCheck_edit_PSWD = new javax.swing.JCheckBox();
       jB_apply_PSWD = new javax.swing.JButton();
       jP_empty = new javax.swing.JPanel();
       
       
       setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints1;
       
       setBackground(new java.awt.Color(230, 230, 230));
       jLabel21.setBackground(new java.awt.Color(230, 230, 230));
       jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/management.png")));
       jLabel21.setText("ACCESS MANAGEMENT");
       jLabel21.setMaximumSize(new java.awt.Dimension(177, 34));
       jLabel21.setMinimumSize(new java.awt.Dimension(177, 34));
       jLabel21.setPreferredSize(new java.awt.Dimension(74, 34));
       gridBagConstraints1 = new java.awt.GridBagConstraints();
       gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
       add(jLabel21, gridBagConstraints1);
       
       jTab_Access_Manager.setBackground(new java.awt.Color(230, 230, 230));
       jTab_Access_Manager.setTabPlacement(javax.swing.JTabbedPane.LEFT);
       jP_TotProfile.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints2;
       
       jP_TotProfile.setBackground(new java.awt.Color(230, 230, 230));
       jP_TotProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jP_PROFILE.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints3;
       
       jP_PROFILE.setBackground(new java.awt.Color(230, 230, 230));
       jP_PROFILE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jP_PROFILE.setMinimumSize(new java.awt.Dimension(10, 100));
       jP_PROFILE.setPreferredSize(new java.awt.Dimension(80, 100));
       jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_job.png")));
       jLabel11.setText("Profiles");
       gridBagConstraints3 = new java.awt.GridBagConstraints();
       gridBagConstraints3.gridx = 0;
       gridBagConstraints3.gridy = 0;
       gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints3.insets = new java.awt.Insets(0, 2, 2, 2);
       gridBagConstraints3.weightx = 1.0;
       jP_PROFILE.add(jLabel11, gridBagConstraints3);
       
       gridBagConstraints3 = new java.awt.GridBagConstraints();
       gridBagConstraints3.gridx = 0;
       gridBagConstraints3.gridy = 1;
       gridBagConstraints3.gridwidth = 2;
       gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints3.weightx = 1.0;
       gridBagConstraints3.weighty = 1.0;
       jP_PROFILE.add(jScrollP_Profile, gridBagConstraints3);
       
       gridBagConstraints2 = new java.awt.GridBagConstraints();
       gridBagConstraints2.gridx = 0;
       gridBagConstraints2.gridy = 0;
       gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints2.weightx = 1.0;
       gridBagConstraints2.weighty = 1.0;
       jP_TotProfile.add(jP_PROFILE, gridBagConstraints2);
       
       jP_CLASS.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints4;
       
       jP_CLASS.setBackground(new java.awt.Color(230, 230, 230));
       jP_CLASS.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jP_CLASS.setMinimumSize(new java.awt.Dimension(80, 50));
       jP_CLASS.setPreferredSize(new java.awt.Dimension(80, 50));
       jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/packages.png")));
       jLabel2.setText("Class");
       gridBagConstraints4 = new java.awt.GridBagConstraints();
       gridBagConstraints4.gridx = 0;
       gridBagConstraints4.gridy = 0;
       gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
       jP_CLASS.add(jLabel2, gridBagConstraints4);
       
       gridBagConstraints4 = new java.awt.GridBagConstraints();
       gridBagConstraints4.gridx = 0;
       gridBagConstraints4.gridy = 1;
       gridBagConstraints4.gridwidth = 2;
       gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints4.weightx = 1.0;
       gridBagConstraints4.weighty = 1.0;
       jP_CLASS.add(jScroll_Class, gridBagConstraints4);
       
       gridBagConstraints2 = new java.awt.GridBagConstraints();
       gridBagConstraints2.gridx = 0;
       gridBagConstraints2.gridy = 2;
       gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints2.weightx = 1.0;
       gridBagConstraints2.weighty = 1.0;
       jP_TotProfile.add(jP_CLASS, gridBagConstraints2);
       
       jP_FunctionDetail.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints5;
       
       jP_FunctionDetail.setBackground(new java.awt.Color(230, 230, 230));
       jP_FunctionDetail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jP_FunctionDetail.setMinimumSize(new java.awt.Dimension(110, 200));
       jP_FunctionDetail.setPreferredSize(new java.awt.Dimension(300, 200));
       jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/function_sel.png")));
       jLabel4.setText("Function :");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 4;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       jP_FunctionDetail.add(jLabel4, gridBagConstraints5);
       
       jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/desc.png")));
       jLabel5.setText("Description :");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 5;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       jP_FunctionDetail.add(jLabel5, gridBagConstraints5);
       
       jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/text.png")));
       jLabel6.setText("Version :");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 6;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       jP_FunctionDetail.add(jLabel6, gridBagConstraints5);
       
       jLabel7.setBackground(new java.awt.Color(230, 230, 230));
       jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/info.png")));
       jLabel7.setText("Vendor :");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 9;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       jP_FunctionDetail.add(jLabel7, gridBagConstraints5);
       
       jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/date.png")));
       jLabel8.setText("Release Date :");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 7;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       jP_FunctionDetail.add(jLabel8, gridBagConstraints5);
       
       jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/miniuser.png")));
       jLabel9.setText("Author :");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 8;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       jP_FunctionDetail.add(jLabel9, gridBagConstraints5);
       
       jTF_nameFunction.setDisabledTextColor(java.awt.Color.darkGray);
       jTF_nameFunction.setEnabled(false);
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 4;
       gridBagConstraints5.gridwidth = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       gridBagConstraints5.weightx = 1.0;
       jP_FunctionDetail.add(jTF_nameFunction, gridBagConstraints5);
       
       jTF_descFunction.setDisabledTextColor(java.awt.Color.darkGray);
       jTF_descFunction.setEnabled(false);
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 5;
       gridBagConstraints5.gridwidth = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       gridBagConstraints5.weightx = 1.0;
       jP_FunctionDetail.add(jTF_descFunction, gridBagConstraints5);
       
       jTF_verFunction.setDisabledTextColor(java.awt.Color.darkGray);
       jTF_verFunction.setEnabled(false);
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 6;
       gridBagConstraints5.gridwidth = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       gridBagConstraints5.weightx = 1.0;
       jP_FunctionDetail.add(jTF_verFunction, gridBagConstraints5);
       
       jTF_releaseFunction.setDisabledTextColor(java.awt.Color.darkGray);
       jTF_releaseFunction.setEnabled(false);
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 7;
       gridBagConstraints5.gridwidth = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       gridBagConstraints5.weightx = 1.0;
       jP_FunctionDetail.add(jTF_releaseFunction, gridBagConstraints5);
       
       jTF_authorFunction.setDisabledTextColor(java.awt.Color.darkGray);
       jTF_authorFunction.setEnabled(false);
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 8;
       gridBagConstraints5.gridwidth = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       gridBagConstraints5.weightx = 1.0;
       jP_FunctionDetail.add(jTF_authorFunction, gridBagConstraints5);
       
       jTF_vendorFunction.setDisabledTextColor(java.awt.Color.darkGray);
       jTF_vendorFunction.setEnabled(false);
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 9;
       gridBagConstraints5.gridwidth = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(1, 2, 1, 2);
       gridBagConstraints5.weightx = 1.0;
       jP_FunctionDetail.add(jTF_vendorFunction, gridBagConstraints5);
       
       jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
       jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/functions_dis.png")));
       jLabel10.setText("Disabled Class Functions ");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 1;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
       jP_FunctionDetail.add(jLabel10, gridBagConstraints5);
       
       jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/det_package.png")));
       jLabel1.setText("Class Function");
       jLabel1.setMinimumSize(new java.awt.Dimension(100, 20));
       jLabel1.setPreferredSize(new java.awt.Dimension(100, 20));
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 3;
       gridBagConstraints5.gridwidth = 3;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints5.insets = new java.awt.Insets(0, 2, 2, 2);
       gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
       jP_FunctionDetail.add(jLabel1, gridBagConstraints5);
       
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 2;
       gridBagConstraints5.gridy = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints5.ipadx = 200;
       gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
       gridBagConstraints5.weightx = 1.0;
       gridBagConstraints5.weighty = 1.0;
       jP_FunctionDetail.add(jScroll_Function_en, gridBagConstraints5);
       
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 0;
       gridBagConstraints5.gridy = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints5.ipadx = 200;
       gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints5.weightx = 1.0;
       gridBagConstraints5.weighty = 1.0;
       jP_FunctionDetail.add(jScroll_Function_dis, gridBagConstraints5);
       
       jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
       jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/functions_en.png")));
       jLabel3.setText("Enabled Class Functions");
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 2;
       gridBagConstraints5.gridy = 1;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints5.insets = new java.awt.Insets(2, 2, 2, 2);
       jP_FunctionDetail.add(jLabel3, gridBagConstraints5);
       
       jP_buttonFunctions.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints6;
       
       jP_buttonFunctions.setBackground(new java.awt.Color(230, 230, 230));
       jB_Add.setBackground(new java.awt.Color(230, 230, 230));
       jB_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_sx.gif")));
       jB_Add.setBorderPainted(false);
       jB_Add.setContentAreaFilled(false);
       jB_Add.setFocusPainted(false);
       jB_Add.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_Add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_sx_press.gif")));
       jB_Add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_sx_over.gif")));
       jB_Add.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_FunctionActionPerformed(evt);
           }
       });
       
       gridBagConstraints6 = new java.awt.GridBagConstraints();
       jP_buttonFunctions.add(jB_Add, gridBagConstraints6);
       
       jB_Rem.setBackground(new java.awt.Color(230, 230, 230));
       jB_Rem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_dx.gif")));
       jB_Rem.setBorderPainted(false);
       jB_Rem.setContentAreaFilled(false);
       jB_Rem.setFocusPainted(false);
       jB_Rem.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_Rem.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_dx_press.gif")));
       jB_Rem.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_dx_over.gif")));
       jB_Rem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_FunctionActionPerformed(evt);
           }
       });
       
       gridBagConstraints6 = new java.awt.GridBagConstraints();
       gridBagConstraints6.gridx = 0;
       gridBagConstraints6.gridy = 1;
       jP_buttonFunctions.add(jB_Rem, gridBagConstraints6);
       
       jB_RemAll.setBackground(new java.awt.Color(230, 230, 230));
       jB_RemAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_dxdx.gif")));
       jB_RemAll.setBorderPainted(false);
       jB_RemAll.setContentAreaFilled(false);
       jB_RemAll.setFocusPainted(false);
       jB_RemAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_RemAll.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_dxdx_press.gif")));
       jB_RemAll.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_dxdx_over.gif")));
       jB_RemAll.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_FunctionActionPerformed(evt);
           }
       });
       
       gridBagConstraints6 = new java.awt.GridBagConstraints();
       gridBagConstraints6.gridx = 0;
       gridBagConstraints6.gridy = 2;
       jP_buttonFunctions.add(jB_RemAll, gridBagConstraints6);
       
       jB_AddAll.setBackground(new java.awt.Color(230, 230, 230));
       jB_AddAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_sxsx.gif")));
       jB_AddAll.setBorderPainted(false);
       jB_AddAll.setContentAreaFilled(false);
       jB_AddAll.setFocusPainted(false);
       jB_AddAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_AddAll.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_sxsx_press.gif")));
       jB_AddAll.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/arr_sxsx_over.gif")));
       jB_AddAll.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_FunctionActionPerformed(evt);
           }
       });
       
       gridBagConstraints6 = new java.awt.GridBagConstraints();
       gridBagConstraints6.gridx = 0;
       gridBagConstraints6.gridy = 3;
       jP_buttonFunctions.add(jB_AddAll, gridBagConstraints6);
       
       gridBagConstraints5 = new java.awt.GridBagConstraints();
       gridBagConstraints5.gridx = 1;
       gridBagConstraints5.gridy = 2;
       gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints5.insets = new java.awt.Insets(2, 0, 2, 0);
       jP_FunctionDetail.add(jP_buttonFunctions, gridBagConstraints5);
       
       gridBagConstraints2 = new java.awt.GridBagConstraints();
       gridBagConstraints2.gridx = 1;
       gridBagConstraints2.gridy = 0;
       gridBagConstraints2.gridheight = 3;
       gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints2.weightx = 1.0;
       gridBagConstraints2.weighty = 1.0;
       jP_TotProfile.add(jP_FunctionDetail, gridBagConstraints2);
       
       jP_button.setLayout(new java.awt.BorderLayout());
       
       jP_button.setBackground(new java.awt.Color(230, 230, 230));
       jP_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jP_button.setPreferredSize(new java.awt.Dimension(80, 46));
       jB_reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/result.png")));
       jB_reload.setText("Reload All");
       jB_reload.setBorderPainted(false);
       jB_reload.setContentAreaFilled(false);
       jB_reload.setFocusPainted(false);
       jB_reload.setMargin(new java.awt.Insets(2, 2, 2, 2));
       jB_reload.setMaximumSize(new java.awt.Dimension(120, 34));
       jB_reload.setMinimumSize(new java.awt.Dimension(120, 30));
       jB_reload.setPreferredSize(new java.awt.Dimension(120, 30));
       jB_reload.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_reloadActionPerformed(evt);
           }
       });
       
       jP_button.add(jB_reload, java.awt.BorderLayout.WEST);
       
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
       
       jP_button.add(jB_commit, java.awt.BorderLayout.EAST);
       
       jP_bOper.setBackground(new java.awt.Color(230, 230, 230));
       jCB_edit_profile.setBackground(new java.awt.Color(230, 230, 230));
       jCB_edit_profile.setText("Enable Edit Profile");
       jCB_edit_profile.setToolTipText("Enabled Modify (True/False)");
       jCB_edit_profile.setBorderPaintedFlat(true);
       jCB_edit_profile.setContentAreaFilled(false);
       jCB_edit_profile.setFocusPainted(false);
       jCB_edit_profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jCB_edit_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off.gif")));
       jCB_edit_profile.setMaximumSize(new java.awt.Dimension(150, 30));
       jCB_edit_profile.setMinimumSize(new java.awt.Dimension(150, 30));
       jCB_edit_profile.setPreferredSize(new java.awt.Dimension(150, 30));
       jCB_edit_profile.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCB_edit_profile.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off_over.gif")));
       jCB_edit_profile.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCB_edit_profile.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on.gif")));
       jCB_edit_profile.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseReleased(java.awt.event.MouseEvent evt) {
               jCB_edit_profileMouseReleased(evt);
           }
       });
       
       jP_bOper.add(jCB_edit_profile);
       
       jRB_ADD_P.setBackground(new java.awt.Color(230, 230, 230));
       jRB_ADD_P.setSelected(true);
       buttonGroup2.add(jRB_ADD_P);
       jRB_ADD_P.setContentAreaFilled(false);
       jRB_ADD_P.setFocusPainted(false);
       jRB_ADD_P.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jRB_ADD_P.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_create_ID.jpg")));
       jRB_ADD_P.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_create_ID_press.jpg")));
       jRB_ADD_P.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_create_ID_over.jpg")));
       jRB_ADD_P.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_create_ID_press.jpg")));
       jRB_ADD_P.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_create_ID_press.jpg")));
       jRB_ADD_P.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jRB_ADD_PActionPerformed(evt);
           }
       });
       
       jP_bOper.add(jRB_ADD_P);
       
       jRB_MOD_P.setBackground(new java.awt.Color(230, 230, 230));
       buttonGroup2.add(jRB_MOD_P);
       jRB_MOD_P.setContentAreaFilled(false);
       jRB_MOD_P.setFocusPainted(false);
       jRB_MOD_P.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jRB_MOD_P.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID.jpg")));
       jRB_MOD_P.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_press.jpg")));
       jRB_MOD_P.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_over.jpg")));
       jRB_MOD_P.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_press.jpg")));
       jRB_MOD_P.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_press.jpg")));
       jRB_MOD_P.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jRB_MOD_PActionPerformed(evt);
           }
       });
       
       jP_bOper.add(jRB_MOD_P);
       
       jRB_DEL_P.setBackground(new java.awt.Color(230, 230, 230));
       buttonGroup2.add(jRB_DEL_P);
       jRB_DEL_P.setContentAreaFilled(false);
       jRB_DEL_P.setFocusPainted(false);
       jRB_DEL_P.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jRB_DEL_P.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_delete_ID.jpg")));
       jRB_DEL_P.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_delete_ID_press.jpg")));
       jRB_DEL_P.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_delete_ID_over.jpg")));
       jRB_DEL_P.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_delete_ID_press.jpg")));
       jRB_DEL_P.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_delete_ID_press.jpg")));
       jRB_DEL_P.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jRB_DEL_PActionPerformed(evt);
           }
       });
       
       jP_bOper.add(jRB_DEL_P);
       
       jP_button.add(jP_bOper, java.awt.BorderLayout.CENTER);
       
       gridBagConstraints2 = new java.awt.GridBagConstraints();
       gridBagConstraints2.gridx = 0;
       gridBagConstraints2.gridy = 3;
       gridBagConstraints2.gridwidth = 2;
       gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints2.weightx = 1.0;
       jP_TotProfile.add(jP_button, gridBagConstraints2);
       
       jP_operation.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints7;
       
       jP_operation.setBackground(new java.awt.Color(230, 230, 230));
       jL_nameP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/profile_name.png")));
       jL_nameP.setText("Profile Name");
       gridBagConstraints7 = new java.awt.GridBagConstraints();
       gridBagConstraints7.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;
       jP_operation.add(jL_nameP, gridBagConstraints7);
       
       jTF_nameProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
       jTF_nameProfile.setDisabledTextColor(new java.awt.Color(204, 204, 204));
       jTF_nameProfile.setMinimumSize(new java.awt.Dimension(100, 20));
       jTF_nameProfile.setPreferredSize(new java.awt.Dimension(100, 20));
       jTF_nameProfile.setEnabled(false);
       jTF_nameProfile.addKeyListener(new java.awt.event.KeyAdapter() {
           public void keyReleased(java.awt.event.KeyEvent evt) {
               jTF_nameProfileKeyReleased(evt);
           }
       });
       
       gridBagConstraints7 = new java.awt.GridBagConstraints();
       gridBagConstraints7.gridx = 0;
       gridBagConstraints7.gridy = 1;
       gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints7.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints7.weightx = 1.0;
       jP_operation.add(jTF_nameProfile, gridBagConstraints7);
       
       jL_descP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/profile_desc.png")));
       jL_descP.setText("Profile Decription");
       gridBagConstraints7 = new java.awt.GridBagConstraints();
       gridBagConstraints7.gridx = 0;
       gridBagConstraints7.gridy = 2;
       gridBagConstraints7.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;
       jP_operation.add(jL_descP, gridBagConstraints7);
       
       jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 60));
       jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 60));
       jTA_descProfile.setLineWrap(true);
       jTA_descProfile.setRows(2);
       jTA_descProfile.setWrapStyleWord(true);
       jTA_descProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
       jTA_descProfile.setDisabledTextColor(java.awt.Color.darkGray);
       jTA_descProfile.addKeyListener(new java.awt.event.KeyAdapter() {
           public void keyReleased(java.awt.event.KeyEvent evt) {
               jTA_descProfileKeyReleased(evt);
           }
       });
       
       jScrollPane1.setViewportView(jTA_descProfile);
       
       gridBagConstraints7 = new java.awt.GridBagConstraints();
       gridBagConstraints7.gridx = 0;
       gridBagConstraints7.gridy = 3;
       gridBagConstraints7.fill = java.awt.GridBagConstraints.BOTH;
       jP_operation.add(jScrollPane1, gridBagConstraints7);
       
       gridBagConstraints2 = new java.awt.GridBagConstraints();
       gridBagConstraints2.gridx = 0;
       gridBagConstraints2.gridy = 1;
       gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
       jP_TotProfile.add(jP_operation, gridBagConstraints2);
       
       jTab_Access_Manager.addTab("Profile", jP_TotProfile);
       
       jP_TotUser.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints8;
       
       jP_TotUser.setBackground(new java.awt.Color(230, 230, 230));
       jP_TotUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jPanel8.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints9;
       
       jPanel8.setBackground(new java.awt.Color(230, 230, 230));
       gridBagConstraints9 = new java.awt.GridBagConstraints();
       gridBagConstraints9.gridx = 0;
       gridBagConstraints9.gridy = 3;
       gridBagConstraints9.gridwidth = 2;
       gridBagConstraints9.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints9.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints9.weightx = 1.0;
       gridBagConstraints9.weighty = 1.0;
       jPanel8.add(jScroll_user, gridBagConstraints9);
       
       jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/users.png")));
       jLabel13.setText("Users");
       jLabel13.setMaximumSize(new java.awt.Dimension(100, 32));
       jLabel13.setMinimumSize(new java.awt.Dimension(100, 32));
       jLabel13.setPreferredSize(new java.awt.Dimension(100, 32));
       gridBagConstraints9 = new java.awt.GridBagConstraints();
       gridBagConstraints9.gridx = 0;
       gridBagConstraints9.gridy = 2;
       gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints9.weightx = 1.0;
       jPanel8.add(jLabel13, gridBagConstraints9);
       
       jLabel22.setText("Role");
       jLabel22.setMinimumSize(new java.awt.Dimension(100, 20));
       jLabel22.setPreferredSize(new java.awt.Dimension(100, 20));
       gridBagConstraints9 = new java.awt.GridBagConstraints();
       gridBagConstraints9.gridwidth = 2;
       gridBagConstraints9.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints9.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints9.weightx = 1.0;
       jPanel8.add(jLabel22, gridBagConstraints9);
       
       jCBox_Role.setBackground(new java.awt.Color(230, 230, 230));
       gridBagConstraints9 = new java.awt.GridBagConstraints();
       gridBagConstraints9.gridx = 0;
       gridBagConstraints9.gridy = 1;
       gridBagConstraints9.gridwidth = 2;
       gridBagConstraints9.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints9.insets = new java.awt.Insets(0, 2, 4, 2);
       jPanel8.add(jCBox_Role, gridBagConstraints9);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 0;
       gridBagConstraints8.gridy = 0;
       gridBagConstraints8.gridheight = 2;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints8.weightx = 1.0;
       gridBagConstraints8.weighty = 1.0;
       jP_TotUser.add(jPanel8, gridBagConstraints8);
       
       jPanel10.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints10;
       
       jPanel10.setBackground(new java.awt.Color(230, 230, 230));
       jPanel12.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints11;
       
       jPanel12.setBackground(new java.awt.Color(230, 230, 230));
       jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_login.png")));
       jLabel14.setText("Login:");
       jLabel14.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel14.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 0;
       gridBagConstraints11.gridy = 1;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jLabel14, gridBagConstraints11);
       
       jTF_login.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jTF_login.setDisabledTextColor(java.awt.Color.black);
       jTF_login.setMaximumSize(new java.awt.Dimension(200, 20));
       jTF_login.setMinimumSize(new java.awt.Dimension(200, 20));
       jTF_login.setPreferredSize(new java.awt.Dimension(200, 20));
       jTF_login.setEnabled(false);
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 1;
       gridBagConstraints11.gridy = 1;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jTF_login, gridBagConstraints11);
       
       jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_name.png")));
       jLabel15.setText("User Name:");
       jLabel15.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel15.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 0;
       gridBagConstraints11.gridy = 2;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jLabel15, gridBagConstraints11);
       
       jTF_nomeUtente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jTF_nomeUtente.setDisabledTextColor(java.awt.Color.black);
       jTF_nomeUtente.setMaximumSize(new java.awt.Dimension(200, 20));
       jTF_nomeUtente.setMinimumSize(new java.awt.Dimension(200, 20));
       jTF_nomeUtente.setPreferredSize(new java.awt.Dimension(200, 20));
       jTF_nomeUtente.setEnabled(false);
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 1;
       gridBagConstraints11.gridy = 2;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jTF_nomeUtente, gridBagConstraints11);
       
       jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_desc.png")));
       jLabel16.setText("Description:");
       jLabel16.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel16.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 0;
       gridBagConstraints11.gridy = 3;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jLabel16, gridBagConstraints11);
       
       jTF_descUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jTF_descUser.setDisabledTextColor(java.awt.Color.black);
       jTF_descUser.setMaximumSize(new java.awt.Dimension(200, 20));
       jTF_descUser.setMinimumSize(new java.awt.Dimension(200, 20));
       jTF_descUser.setPreferredSize(new java.awt.Dimension(200, 20));
       jTF_descUser.setEnabled(false);
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 1;
       gridBagConstraints11.gridy = 3;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jTF_descUser, gridBagConstraints11);
       
       jLabel17.setForeground(java.awt.Color.red);
       jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_role.png")));
       jLabel17.setText("Role:");
       jLabel17.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel17.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 0;
       gridBagConstraints11.gridy = 4;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jLabel17, gridBagConstraints11);
       
       jCBox_UserRoleDetail.setBackground(new java.awt.Color(230, 230, 230));
       jCBox_UserRoleDetail.setMaximumSize(new java.awt.Dimension(200, 22));
       jCBox_UserRoleDetail.setMinimumSize(new java.awt.Dimension(200, 22));
       jCBox_UserRoleDetail.setPreferredSize(new java.awt.Dimension(200, 22));
       jCBox_UserRoleDetail.addItemListener(new java.awt.event.ItemListener() {
           public void itemStateChanged(java.awt.event.ItemEvent evt) {
               jCBox_UserRoleDetailItemStateChanged(evt);
           }
       });
       
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 1;
       gridBagConstraints11.gridy = 4;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jCBox_UserRoleDetail, gridBagConstraints11);
       
       jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_access.png")));
       jLabel18.setText("Access:");
       jLabel18.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel18.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 0;
       gridBagConstraints11.gridy = 5;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel12.add(jLabel18, gridBagConstraints11);
       
       jL_Enabled.setMaximumSize(new java.awt.Dimension(200, 20));
       jL_Enabled.setMinimumSize(new java.awt.Dimension(200, 20));
       jL_Enabled.setPreferredSize(new java.awt.Dimension(200, 20));
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridx = 1;
       gridBagConstraints11.gridy = 5;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;
       jPanel12.add(jL_Enabled, gridBagConstraints11);
       
       jPanel13.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints12;
       
       jPanel13.setBackground(new java.awt.Color(230, 230, 230));
       jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_details.png")));
       jLabel20.setText("User Details");
       jLabel20.setMinimumSize(new java.awt.Dimension(100, 32));
       jLabel20.setPreferredSize(new java.awt.Dimension(100, 32));
       gridBagConstraints12 = new java.awt.GridBagConstraints();
       gridBagConstraints12.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints12.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints12.weightx = 1.0;
       jPanel13.add(jLabel20, gridBagConstraints12);
       
       gridBagConstraints11 = new java.awt.GridBagConstraints();
       gridBagConstraints11.gridwidth = 2;
       gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints11.insets = new java.awt.Insets(2, 2, 4, 2);
       gridBagConstraints11.weightx = 1.0;
       jPanel12.add(jPanel13, gridBagConstraints11);
       
       gridBagConstraints10 = new java.awt.GridBagConstraints();
       gridBagConstraints10.gridx = 0;
       gridBagConstraints10.gridy = 0;
       gridBagConstraints10.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints10.weightx = 1.0;
       gridBagConstraints10.weighty = 1.0;
       jPanel10.add(jPanel12, gridBagConstraints10);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 0;
       gridBagConstraints8.gridy = 2;
       gridBagConstraints8.gridheight = 3;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints8.weightx = 1.0;
       jP_TotUser.add(jPanel10, gridBagConstraints8);
       
       jPanel1.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints13;
       
       jPanel1.setBackground(new java.awt.Color(230, 230, 204));
       jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jPanel1.setMinimumSize(new java.awt.Dimension(10, 350));
       jPanel1.setPreferredSize(new java.awt.Dimension(10, 350));
       jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/packages.png")));
       jLabel12.setText("Class");
       jLabel12.setMinimumSize(new java.awt.Dimension(100, 32));
       jLabel12.setPreferredSize(new java.awt.Dimension(100, 32));
       gridBagConstraints13 = new java.awt.GridBagConstraints();
       gridBagConstraints13.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints13.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints13.weightx = 1.0;
       jPanel1.add(jLabel12, gridBagConstraints13);
       
       jCBox_class.setBackground(new java.awt.Color(230, 230, 230));
       gridBagConstraints13 = new java.awt.GridBagConstraints();
       gridBagConstraints13.gridx = 0;
       gridBagConstraints13.gridy = 1;
       gridBagConstraints13.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints13.insets = new java.awt.Insets(0, 2, 4, 2);
       jPanel1.add(jCBox_class, gridBagConstraints13);
       
       jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/profiles_dis.png")));
       jLabel19.setText("Disabled Profiles");
       gridBagConstraints13 = new java.awt.GridBagConstraints();
       gridBagConstraints13.gridx = 0;
       gridBagConstraints13.gridy = 2;
       gridBagConstraints13.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints13.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints13.weightx = 1.0;
       jPanel1.add(jLabel19, gridBagConstraints13);
       
       gridBagConstraints13 = new java.awt.GridBagConstraints();
       gridBagConstraints13.gridx = 0;
       gridBagConstraints13.gridy = 3;
       gridBagConstraints13.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints13.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints13.weightx = 1.0;
       gridBagConstraints13.weighty = 1.0;
       jPanel1.add(jScroll_ProfDis_2, gridBagConstraints13);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 1;
       gridBagConstraints8.gridy = 0;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints8.weightx = 1.0;
       gridBagConstraints8.weighty = 1.0;
       jP_TotUser.add(jPanel1, gridBagConstraints8);
       
       jPanel3.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints14;
       
       jPanel3.setBackground(new java.awt.Color(230, 230, 230));
       jPanel3.setMaximumSize(new java.awt.Dimension(10, 150));
       jPanel3.setMinimumSize(new java.awt.Dimension(10, 150));
       jPanel3.setPreferredSize(new java.awt.Dimension(10, 150));
       jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/profiles_en.png")));
       jLabel23.setText("Enabled User Profiles");
       gridBagConstraints14 = new java.awt.GridBagConstraints();
       gridBagConstraints14.gridx = 0;
       gridBagConstraints14.gridy = 2;
       gridBagConstraints14.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints14.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints14.weightx = 1.0;
       jPanel3.add(jLabel23, gridBagConstraints14);
       
       gridBagConstraints14 = new java.awt.GridBagConstraints();
       gridBagConstraints14.gridx = 0;
       gridBagConstraints14.gridy = 3;
       gridBagConstraints14.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints14.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints14.weightx = 1.0;
       gridBagConstraints14.weighty = 1.0;
       jPanel3.add(jScroll_ProfEna_2, gridBagConstraints14);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 1;
       gridBagConstraints8.gridy = 2;
       gridBagConstraints8.gridheight = 2;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints8.weightx = 1.0;
       gridBagConstraints8.weighty = 1.0;
       jP_TotUser.add(jPanel3, gridBagConstraints8);
       
       jPanel2.setLayout(new java.awt.BorderLayout());
       
       jPanel2.setBackground(new java.awt.Color(230, 230, 230));
       jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jB_reloadAll_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/result.png")));
       jB_reloadAll_user.setText("Reload All");
       jB_reloadAll_user.setBorderPainted(false);
       jB_reloadAll_user.setContentAreaFilled(false);
       jB_reloadAll_user.setFocusPainted(false);
       jB_reloadAll_user.setMargin(new java.awt.Insets(2, 2, 2, 2));
       jB_reloadAll_user.setMaximumSize(new java.awt.Dimension(120, 34));
       jB_reloadAll_user.setMinimumSize(new java.awt.Dimension(120, 30));
       jB_reloadAll_user.setPreferredSize(new java.awt.Dimension(120, 30));
       jB_reloadAll_user.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_reloadAll_userActionPerformed(evt);
           }
       });
       
       jPanel2.add(jB_reloadAll_user, java.awt.BorderLayout.WEST);
       
       jB_commit_user.setBackground(new java.awt.Color(230, 230, 230));
       jB_commit_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit.jpg")));
       jB_commit_user.setBorderPainted(false);
       jB_commit_user.setContentAreaFilled(false);
       jB_commit_user.setFocusPainted(false);
       jB_commit_user.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_commit_user.setMaximumSize(new java.awt.Dimension(0, 0));
       jB_commit_user.setMinimumSize(new java.awt.Dimension(0, 0));
       jB_commit_user.setPreferredSize(new java.awt.Dimension(80, 25));
       jB_commit_user.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit_press.jpg")));
       jB_commit_user.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit_over.jpg")));
       jB_commit_user.setEnabled(false);
       jB_commit_user.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_commit_userActionPerformed(evt);
           }
       });
       
       jPanel2.add(jB_commit_user, java.awt.BorderLayout.EAST);
       
       jPanel4.setBackground(new java.awt.Color(230, 230, 230));
       jCB_edit_user.setBackground(new java.awt.Color(230, 230, 230));
       jCB_edit_user.setText("Enable Edit User");
       jCB_edit_user.setToolTipText("Enabled Modify (True/False)");
       jCB_edit_user.setBorderPaintedFlat(true);
       jCB_edit_user.setContentAreaFilled(false);
       jCB_edit_user.setFocusPainted(false);
       jCB_edit_user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jCB_edit_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off.gif")));
       jCB_edit_user.setMaximumSize(new java.awt.Dimension(150, 30));
       jCB_edit_user.setMinimumSize(new java.awt.Dimension(150, 30));
       jCB_edit_user.setPreferredSize(new java.awt.Dimension(150, 30));
       jCB_edit_user.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCB_edit_user.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off_over.gif")));
       jCB_edit_user.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCB_edit_user.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on.gif")));
       jCB_edit_user.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseReleased(java.awt.event.MouseEvent evt) {
               jCB_edit_userMouseReleased(evt);
           }
       });
       
       jPanel4.add(jCB_edit_user);
       
       jRB_MOD_U.setBackground(new java.awt.Color(230, 230, 230));
       jRB_MOD_U.setSelected(true);
       buttonGroup2.add(jRB_MOD_U);
       jRB_MOD_U.setContentAreaFilled(false);
       jRB_MOD_U.setFocusPainted(false);
       jRB_MOD_U.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jRB_MOD_U.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID.jpg")));
       jRB_MOD_U.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_press.jpg")));
       jRB_MOD_U.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_over.jpg")));
       jRB_MOD_U.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_press.jpg")));
       jRB_MOD_U.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_modify_ID_press.jpg")));
       jPanel4.add(jRB_MOD_U);
       
       jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 0;
       gridBagConstraints8.gridy = 5;
       gridBagConstraints8.gridwidth = 2;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints8.weightx = 1.0;
       jP_TotUser.add(jPanel2, gridBagConstraints8);
       
       jPanel5.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints15;
       
       jPanel5.setBackground(new java.awt.Color(230, 230, 230));
       jB_enabledPro.setBackground(new java.awt.Color(230, 230, 230));
       jB_enabledPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/down_green.png")));
       jB_enabledPro.setText("Enabled Profile");
       jB_enabledPro.setBorderPainted(false);
       jB_enabledPro.setFocusPainted(false);
       jB_enabledPro.setMargin(new java.awt.Insets(2, 2, 2, 2));
       jB_enabledPro.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_en_disProActionPerformed(evt);
           }
       });
       
       gridBagConstraints15 = new java.awt.GridBagConstraints();
       gridBagConstraints15.insets = new java.awt.Insets(1, 4, 1, 4);
       jPanel5.add(jB_enabledPro, gridBagConstraints15);
       
       jB_disablePro.setBackground(new java.awt.Color(230, 230, 230));
       jB_disablePro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/up_red.png")));
       jB_disablePro.setText("Disabled Profile");
       jB_disablePro.setBorderPainted(false);
       jB_disablePro.setFocusPainted(false);
       jB_disablePro.setMargin(new java.awt.Insets(2, 2, 2, 2));
       jB_disablePro.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_en_disProActionPerformed(evt);
           }
       });
       
       gridBagConstraints15 = new java.awt.GridBagConstraints();
       gridBagConstraints15.insets = new java.awt.Insets(1, 4, 1, 4);
       jPanel5.add(jB_disablePro, gridBagConstraints15);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 1;
       gridBagConstraints8.gridy = 1;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints8.weightx = 1.0;
       jP_TotUser.add(jPanel5, gridBagConstraints8);
       
       jScrollP_descProf.setMinimumSize(new java.awt.Dimension(100, 60));
       jScrollP_descProf.setPreferredSize(new java.awt.Dimension(100, 60));
       jTArea_descProfile.setEditable(false);
       jTArea_descProfile.setLineWrap(true);
       jTArea_descProfile.setRows(2);
       jTArea_descProfile.setWrapStyleWord(true);
       jTArea_descProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
       jTArea_descProfile.setDisabledTextColor(java.awt.Color.darkGray);
       jScrollP_descProf.setViewportView(jTArea_descProfile);
       
       gridBagConstraints8 = new java.awt.GridBagConstraints();
       gridBagConstraints8.gridx = 1;
       gridBagConstraints8.gridy = 4;
       gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints8.insets = new java.awt.Insets(2, 2, 2, 2);
       jP_TotUser.add(jScrollP_descProf, gridBagConstraints8);
       
       jTab_Access_Manager.addTab("User", jP_TotUser);
       
       jP_PSWD.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints16;
       
       jP_PSWD.setBackground(new java.awt.Color(230, 230, 230));
       jP_PSWD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jPanel6.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints17;
       
       jPanel6.setBackground(new java.awt.Color(230, 230, 230));
       jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jPanel6.setMinimumSize(new java.awt.Dimension(474, 400));
       jPanel6.setPreferredSize(new java.awt.Dimension(474, 400));
       jPanel7.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints18;
       
       jPanel7.setBackground(new java.awt.Color(230, 230, 230));
       jPanel7.setMinimumSize(new java.awt.Dimension(378, 250));
       jPanel7.setPreferredSize(new java.awt.Dimension(378, 250));
       jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/user_blue.png")));
       jLabel24.setText("Login:");
       jLabel24.setMinimumSize(new java.awt.Dimension(170, 22));
       jLabel24.setPreferredSize(new java.awt.Dimension(170, 22));
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 0;
       gridBagConstraints18.gridy = 3;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jLabel24, gridBagConstraints18);
       
       jT_Login_PSWD.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jT_Login_PSWD.setDisabledTextColor(java.awt.Color.black);
       jT_Login_PSWD.setMaximumSize(new java.awt.Dimension(200, 20));
       jT_Login_PSWD.setMinimumSize(new java.awt.Dimension(200, 20));
       jT_Login_PSWD.setPreferredSize(new java.awt.Dimension(200, 20));
       jT_Login_PSWD.setEnabled(false);
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 1;
       gridBagConstraints18.gridy = 3;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jT_Login_PSWD, gridBagConstraints18);
       
       jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/key_s.gif")));
       jLabel25.setText("Password:");
       jLabel25.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel25.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 0;
       gridBagConstraints18.gridy = 4;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jLabel25, gridBagConstraints18);
       
       jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/key_s.gif")));
       jLabel26.setText("Confirm Password:");
       jLabel26.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel26.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 0;
       gridBagConstraints18.gridy = 5;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jLabel26, gridBagConstraints18);
       
       jLabel27.setForeground(java.awt.Color.red);
       jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/insert_on.gif")));
       jLabel27.setText(" Type:");
       jLabel27.setMinimumSize(new java.awt.Dimension(110, 22));
       jLabel27.setPreferredSize(new java.awt.Dimension(110, 22));
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 0;
       gridBagConstraints18.gridy = 2;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jLabel27, gridBagConstraints18);
       
       jCBox_TypePSWD.setBackground(new java.awt.Color(230, 230, 230));
       jCBox_TypePSWD.setMaximumSize(new java.awt.Dimension(200, 22));
       jCBox_TypePSWD.setMinimumSize(new java.awt.Dimension(200, 22));
       jCBox_TypePSWD.setPreferredSize(new java.awt.Dimension(200, 22));
       jCBox_TypePSWD.setEnabled(false);
       jCBox_TypePSWD.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jCBox_TypePSWDActionPerformed(evt);
           }
       });
       
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 1;
       gridBagConstraints18.gridy = 2;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jCBox_TypePSWD, gridBagConstraints18);
       
       jPanel9.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints19;
       
       jPanel9.setBackground(new java.awt.Color(230, 230, 230));
       jPanel9.setMinimumSize(new java.awt.Dimension(104, 34));
       jPanel9.setPreferredSize(new java.awt.Dimension(104, 34));
       jL_desc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/lock.png")));
       jL_desc.setText("Access Details");
       jL_desc.setMinimumSize(new java.awt.Dimension(100, 32));
       jL_desc.setPreferredSize(new java.awt.Dimension(100, 32));
       gridBagConstraints19 = new java.awt.GridBagConstraints();
       gridBagConstraints19.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints19.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints19.weightx = 1.0;
       jPanel9.add(jL_desc, gridBagConstraints19);
       
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridwidth = 2;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 4, 2);
       gridBagConstraints18.weightx = 1.0;
       jPanel7.add(jPanel9, gridBagConstraints18);
       
       jPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jPass.setDisabledTextColor(java.awt.Color.black);
       jPass.setEnabled(false);
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 1;
       gridBagConstraints18.gridy = 4;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jPass, gridBagConstraints18);
       
       jPass2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jPass2.setDisabledTextColor(java.awt.Color.black);
       jPass2.setEnabled(false);
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 1;
       gridBagConstraints18.gridy = 5;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jPass2, gridBagConstraints18);
       
       jCheck_del_PSWD.setBackground(new java.awt.Color(230, 230, 230));
       jCheck_del_PSWD.setForeground(java.awt.Color.red);
       jCheck_del_PSWD.setText("Delete Access");
       jCheck_del_PSWD.setToolTipText("Delete Access (True/False)");
       jCheck_del_PSWD.setBorderPaintedFlat(true);
       jCheck_del_PSWD.setContentAreaFilled(false);
       jCheck_del_PSWD.setFocusPainted(false);
       jCheck_del_PSWD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jCheck_del_PSWD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off.gif")));
       jCheck_del_PSWD.setMaximumSize(new java.awt.Dimension(150, 30));
       jCheck_del_PSWD.setMinimumSize(new java.awt.Dimension(150, 30));
       jCheck_del_PSWD.setPreferredSize(new java.awt.Dimension(150, 30));
       jCheck_del_PSWD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCheck_del_PSWD.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off_over.gif")));
       jCheck_del_PSWD.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCheck_del_PSWD.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on.gif")));
       jCheck_del_PSWD.setEnabled(false);
       jCheck_del_PSWD.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseReleased(java.awt.event.MouseEvent evt) {
               jCheck_del_PSWDMouseReleased(evt);
           }
       });
       
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 0;
       gridBagConstraints18.gridy = 7;
       gridBagConstraints18.gridwidth = 2;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(3, 3, 3, 3);
       jPanel7.add(jCheck_del_PSWD, gridBagConstraints18);
       
       jLabel28.setForeground(java.awt.Color.red);
       jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/plugin_active.png")));
       jLabel28.setText("Host:");
       jLabel28.setMinimumSize(new java.awt.Dimension(170, 22));
       jLabel28.setPreferredSize(new java.awt.Dimension(170, 22));
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 0;
       gridBagConstraints18.gridy = 1;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jLabel28, gridBagConstraints18);
       
       jTF_host_1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
       jTF_host_1.setDisabledTextColor(java.awt.Color.black);
       jTF_host_1.setMaximumSize(new java.awt.Dimension(200, 20));
       jTF_host_1.setMinimumSize(new java.awt.Dimension(200, 20));
       jTF_host_1.setPreferredSize(new java.awt.Dimension(200, 20));
       jTF_host_1.setEnabled(false);
       gridBagConstraints18 = new java.awt.GridBagConstraints();
       gridBagConstraints18.gridx = 1;
       gridBagConstraints18.gridy = 1;
       gridBagConstraints18.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints18.insets = new java.awt.Insets(2, 2, 2, 2);
       jPanel7.add(jTF_host_1, gridBagConstraints18);
       
       gridBagConstraints17 = new java.awt.GridBagConstraints();
       gridBagConstraints17.gridx = 1;
       gridBagConstraints17.gridy = 0;
       gridBagConstraints17.gridheight = 2;
       gridBagConstraints17.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints17.insets = new java.awt.Insets(2, 6, 2, 6);
       gridBagConstraints17.weightx = 1.0;
       gridBagConstraints17.weighty = 1.0;
       jPanel6.add(jPanel7, gridBagConstraints17);
       
       jScroll_Hosts.setMinimumSize(new java.awt.Dimension(300, 200));
       jScroll_Hosts.setPreferredSize(new java.awt.Dimension(300, 200));
       gridBagConstraints17 = new java.awt.GridBagConstraints();
       gridBagConstraints17.gridx = 0;
       gridBagConstraints17.gridy = 1;
       gridBagConstraints17.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints17.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints17.weightx = 1.0;
       jPanel6.add(jScroll_Hosts, gridBagConstraints17);
       
       jL_nodes.setBackground(new java.awt.Color(230, 230, 230));
       jL_nodes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/image.png")));
       jL_nodes.setText("Hosts");
       jL_nodes.setOpaque(true);
       gridBagConstraints17 = new java.awt.GridBagConstraints();
       gridBagConstraints17.gridx = 0;
       gridBagConstraints17.gridy = 0;
       gridBagConstraints17.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints17.insets = new java.awt.Insets(0, 2, 2, 2);
       jPanel6.add(jL_nodes, gridBagConstraints17);
       
       jP_addHosts.setLayout(new java.awt.GridBagLayout());
       java.awt.GridBagConstraints gridBagConstraints20;
       
       jP_addHosts.setBackground(new java.awt.Color(230, 230, 230));
       jP_addHosts.setMinimumSize(new java.awt.Dimension(83, 40));
       jP_addHosts.setPreferredSize(new java.awt.Dimension(0, 40));
       gridBagConstraints20 = new java.awt.GridBagConstraints();
       gridBagConstraints20.gridx = 0;
       gridBagConstraints20.gridy = 1;
       gridBagConstraints20.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints20.weightx = 1.0;
       jP_addHosts.add(jTF_NameNewHost, gridBagConstraints20);
       
       jB_addHost.setBackground(new java.awt.Color(230, 230, 230));
       jB_addHost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_add.jpg")));
       jB_addHost.setBorderPainted(false);
       jB_addHost.setContentAreaFilled(false);
       jB_addHost.setFocusPainted(false);
       jB_addHost.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_addHost.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_add_press.jpg")));
       jB_addHost.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_add_over.jpg")));
       jB_addHost.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_addHostActionPerformed(evt);
           }
       });
       
       gridBagConstraints20 = new java.awt.GridBagConstraints();
       gridBagConstraints20.gridx = 1;
       gridBagConstraints20.gridy = 1;
       gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
       jP_addHosts.add(jB_addHost, gridBagConstraints20);
       
       jL_newHost.setText("Add New Host");
       gridBagConstraints20 = new java.awt.GridBagConstraints();
       gridBagConstraints20.gridwidth = 2;
       gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints20.weightx = 1.0;
       jP_addHosts.add(jL_newHost, gridBagConstraints20);
       
       gridBagConstraints17 = new java.awt.GridBagConstraints();
       gridBagConstraints17.gridx = 0;
       gridBagConstraints17.gridy = 2;
       gridBagConstraints17.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints17.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints17.weightx = 1.0;
       gridBagConstraints17.weighty = 1.0;
       jPanel6.add(jP_addHosts, gridBagConstraints17);
       
       gridBagConstraints16 = new java.awt.GridBagConstraints();
       gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints16.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints16.anchor = java.awt.GridBagConstraints.NORTHWEST;
       gridBagConstraints16.weightx = 1.0;
       jP_PSWD.add(jPanel6, gridBagConstraints16);
       
       jPanel11.setLayout(new java.awt.BorderLayout());
       
       jPanel11.setBackground(new java.awt.Color(230, 230, 230));
       jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       jB_reload_PSWD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/result.png")));
       jB_reload_PSWD.setText("Reload All");
       jB_reload_PSWD.setBorderPainted(false);
       jB_reload_PSWD.setContentAreaFilled(false);
       jB_reload_PSWD.setFocusPainted(false);
       jB_reload_PSWD.setMargin(new java.awt.Insets(2, 2, 2, 2));
       jB_reload_PSWD.setMaximumSize(new java.awt.Dimension(120, 34));
       jB_reload_PSWD.setMinimumSize(new java.awt.Dimension(120, 30));
       jB_reload_PSWD.setPreferredSize(new java.awt.Dimension(120, 30));
       jB_reload_PSWD.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_reload_PSWDActionPerformed(evt);
           }
       });
       
       jPanel11.add(jB_reload_PSWD, java.awt.BorderLayout.WEST);
       
       jB_commit_PSWD.setBackground(new java.awt.Color(230, 230, 230));
       jB_commit_PSWD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit.jpg")));
       jB_commit_PSWD.setBorderPainted(false);
       jB_commit_PSWD.setContentAreaFilled(false);
       jB_commit_PSWD.setFocusPainted(false);
       jB_commit_PSWD.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_commit_PSWD.setMaximumSize(new java.awt.Dimension(0, 0));
       jB_commit_PSWD.setMinimumSize(new java.awt.Dimension(0, 0));
       jB_commit_PSWD.setPreferredSize(new java.awt.Dimension(80, 25));
       jB_commit_PSWD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit_press.jpg")));
       jB_commit_PSWD.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/b_commit_over.jpg")));
       jB_commit_PSWD.setEnabled(false);
       jB_commit_PSWD.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_commit_PSWDActionPerformed(evt);
           }
       });
       
       jPanel11.add(jB_commit_PSWD, java.awt.BorderLayout.EAST);
       
       jPanel14.setBackground(new java.awt.Color(230, 230, 230));
       jCheck_edit_PSWD.setBackground(new java.awt.Color(230, 230, 230));
       jCheck_edit_PSWD.setText("Enable Edit");
       jCheck_edit_PSWD.setToolTipText("Enabled Modify (True/False)");
       jCheck_edit_PSWD.setBorderPaintedFlat(true);
       jCheck_edit_PSWD.setContentAreaFilled(false);
       jCheck_edit_PSWD.setFocusPainted(false);
       jCheck_edit_PSWD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jCheck_edit_PSWD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off.gif")));
       jCheck_edit_PSWD.setMaximumSize(new java.awt.Dimension(150, 30));
       jCheck_edit_PSWD.setMinimumSize(new java.awt.Dimension(150, 30));
       jCheck_edit_PSWD.setPreferredSize(new java.awt.Dimension(150, 30));
       jCheck_edit_PSWD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCheck_edit_PSWD.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_off_over.gif")));
       jCheck_edit_PSWD.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on_over.gif")));
       jCheck_edit_PSWD.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/check_on.gif")));
       jCheck_edit_PSWD.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseReleased(java.awt.event.MouseEvent evt) {
               jCheck_edit_PSWDMouseReleased(evt);
           }
       });
       
       jPanel14.add(jCheck_edit_PSWD);
       
       jB_apply_PSWD.setBackground(new java.awt.Color(230, 230, 230));
       jB_apply_PSWD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/apply.gif")));
       jB_apply_PSWD.setBorderPainted(false);
       jB_apply_PSWD.setContentAreaFilled(false);
       jB_apply_PSWD.setFocusPainted(false);
       jB_apply_PSWD.setMargin(new java.awt.Insets(0, 0, 0, 0));
       jB_apply_PSWD.setMaximumSize(new java.awt.Dimension(0, 0));
       jB_apply_PSWD.setMinimumSize(new java.awt.Dimension(0, 0));
       jB_apply_PSWD.setPreferredSize(new java.awt.Dimension(80, 25));
       jB_apply_PSWD.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/apply_press.gif")));
       jB_apply_PSWD.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images_conf/apply_over.gif")));
       jB_apply_PSWD.setEnabled(false);
       jB_apply_PSWD.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_apply_PSWDActionPerformed(evt);
           }
       });
       
       jPanel14.add(jB_apply_PSWD);
       
       jPanel11.add(jPanel14, java.awt.BorderLayout.CENTER);
       
       gridBagConstraints16 = new java.awt.GridBagConstraints();
       gridBagConstraints16.gridx = 0;
       gridBagConstraints16.gridy = 1;
       gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
       gridBagConstraints16.insets = new java.awt.Insets(2, 2, 2, 2);
       gridBagConstraints16.anchor = java.awt.GridBagConstraints.NORTHWEST;
       gridBagConstraints16.weightx = 1.0;
       jP_PSWD.add(jPanel11, gridBagConstraints16);
       
       jP_empty.setBackground(new java.awt.Color(230, 230, 230));
       gridBagConstraints16 = new java.awt.GridBagConstraints();
       gridBagConstraints16.gridx = 0;
       gridBagConstraints16.gridy = 2;
       gridBagConstraints16.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints16.weighty = 1.0;
       jP_PSWD.add(jP_empty, gridBagConstraints16);
       
       jTab_Access_Manager.addTab("Passwords", jP_PSWD);
       
       gridBagConstraints1 = new java.awt.GridBagConstraints();
       gridBagConstraints1.gridx = 0;
       gridBagConstraints1.gridy = 1;
       gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints1.weightx = 1.0;
       gridBagConstraints1.weighty = 1.0;
       add(jTab_Access_Manager, gridBagConstraints1);
       
   }//GEN-END:initComponents

   private void jB_addHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_addHostActionPerformed
        String str_newHosts  = jTF_NameNewHost.getText().trim();

        if(str_newHosts.length() == 0)
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"The 'New Host' field is empty.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( isPresent_str(V_Hosts,str_newHosts) == false )
        {
            //System.out.println("V_Hosts.addElement");
            V_Hosts.addElement(str_newHosts);
        }
        else
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Attention: Host name < "+str_newHosts+" > already exist!","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
            
        refresh_ListHosts_toApply(V_modify_IDListHosts);
        
        jTF_NameNewHost.setText(""); 

   }//GEN-LAST:event_jB_addHostActionPerformed

   private void jCBox_TypePSWDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_TypePSWDActionPerformed
       //System.out.println("jCBox_TypePSWDActionPerformed");
       setSelectedHosts_Or_Type();
       //print_GESTIONE_PSWD(STRUCT_GESTIONE_PSWD);
   }//GEN-LAST:event_jCBox_TypePSWDActionPerformed

   private void jCheck_del_PSWDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheck_del_PSWDMouseReleased
        boolean enabled = jCheck_del_PSWD.isSelected();          
           
        jTF_host_1.setEnabled(!enabled);
        jCBox_TypePSWD.setEnabled(!enabled);
        jT_Login_PSWD.setEnabled(!enabled);
        jPass.setEnabled(!enabled);
        jPass2.setEnabled(!enabled);
     
   }//GEN-LAST:event_jCheck_del_PSWDMouseReleased

   private void jB_apply_PSWDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_apply_PSWDActionPerformed
        int indexItemSelected = jList_Hosts.getSelectedIndex();
        if( indexItemSelected >= 0)
        {
            String str_Hostselected = new String(jList_Hosts.get_ID_Value(indexItemSelected));
            
            //System.out.println("str_Hostselected PRIMA del ReplaceAll (symbolModify)= "+str_Hostselected);
            if(str_Hostselected.indexOf(symbolModify) != -1)
            {
                str_Hostselected = str_Hostselected.replace(symbolModify,' ').trim();
                //System.out.println("str_Hostselected DOPO del ReplaceAll (symbolModify) = -"+str_Hostselected+"-");
            }
                        
            String type_selected = (String)(jCBox_TypePSWD.getSelectedItem());
            
            int index_PSWD = -1;          
            if(STRUCT_GESTIONE_PSWD != null)
            {
                int size_pswd = STRUCT_GESTIONE_PSWD.length;               

                for(int i=0; i<size_pswd; i++)
                {
                    String appoNodo = new String(STRUCT_GESTIONE_PSWD[i].nomeNodo).trim();
                    
                    String appoOggetto = new String(STRUCT_GESTIONE_PSWD[i].oggetto).trim();
                    appoOggetto = appoOggetto.toUpperCase();
                    
                    if( (str_Hostselected.compareTo(appoNodo) == 0) && (type_selected.compareTo(appoOggetto) == 0) )
                    {
                        index_PSWD = i;
                        break;
                    }
                }
            }
            
            if(jCheck_del_PSWD.isSelected()) // DELETE
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                int YES_NO = warning.showConfirmDialog(frameParent,"Attention, setting confirm the deletion of Access:" +str_Hostselected +" ?" ,"Info Message",javax.swing.JOptionPane.YES_NO_OPTION);
                
                if(YES_NO == 0)
                {                    
                    if(index_PSWD != -1)
                    {                                        
                        Vector v_appo_struct = copyIntoVector_PSWD(STRUCT_GESTIONE_PSWD);
                                               
                        v_appo_struct.removeElementAt(index_PSWD);       
                        int newSize = v_appo_struct.size();

                        STRUCT_GESTIONE_PSWD = new GESTIONE_PSWD[newSize];                    
                        for(int i=0; i<newSize; i++)
                            STRUCT_GESTIONE_PSWD[i] = (GESTIONE_PSWD)(v_appo_struct.elementAt(i));
                        
                    }
                }    
                else
                    return;
 
            }
            else // ADD and MODIFY
            {                          
                
                String strHosts = jTF_host_1.getText().trim();
                if(strHosts.length() == 0)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"The 'Host' field is empty.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if(strHosts.compareTo(str_Hostselected) != 0)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    int YES_NO = warning.showConfirmDialog(frameParent,"Attention, The host has changed from "+str_Hostselected+" in "+strHosts+", confirm ?","Info Message" ,javax.swing.JOptionPane.YES_NO_OPTION);
                    
                    if(YES_NO == 0)
                        str_Hostselected = strHosts;
                    else
                    {
                        return;
                    }
                }
                
                
                String strLogin = jT_Login_PSWD.getText().trim();
                if(strLogin.length() == 0)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"The 'Login' field is empty.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String strPass  = jPass.getText().trim();
                String strPass2 = jPass2.getText().trim();

                if(strPass.length() == 0)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"The 'Password' field is empty.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if(strPass2.length() == 0)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"The 'Confirm Password' field is empty.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if(strPass.compareTo(strPass2) != 0)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"Passwords do not match.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
   
                char[] nomeNodo = conf_GlobalParam.set_String_toChar(str_Hostselected,conf_GlobalParam.DIM_PSWD_NOME_NODO);
                char[] oggetto = conf_GlobalParam.set_String_toChar(type_selected,conf_GlobalParam.DIM_PSWD_OGGETTO);
                char[] login = conf_GlobalParam.set_String_toChar(strLogin,conf_GlobalParam.DIM_PSWD_LOGIN);                               
                char[] pswd = conf_GlobalParam.set_String_toChar(strPass,conf_GlobalParam.DIM_PSWD_PASSWORD);
                char validita = 'A';
                char[] dataScadenza = conf_GlobalParam.set_String_toChar("",conf_GlobalParam.DIM_PSWD_DATA_SCADENZA);
                char flag = 'A'; 
            
            
                if(index_PSWD != -1) // Gestione_PSWD Esistente, modifico all'indice selezionato
                {   
                      
                    STRUCT_GESTIONE_PSWD[index_PSWD] = new GESTIONE_PSWD(nomeNodo,oggetto,login,pswd,validita,dataScadenza,flag);
                }
                else 
                {                     
                    if(STRUCT_GESTIONE_PSWD == null) // Struttura Gestione_PSWD null
                    {
                        STRUCT_GESTIONE_PSWD = new GESTIONE_PSWD[1];
                        STRUCT_GESTIONE_PSWD[0] = new GESTIONE_PSWD(nomeNodo,oggetto,login,pswd,validita,dataScadenza,flag);
                    }
                    else // Aggiungo nuova Gestione_PSWD 
                    {

                        Vector v_appo_struct = copyIntoVector_PSWD(STRUCT_GESTIONE_PSWD);

                        GESTIONE_PSWD NEW_gestione_PSWD = new GESTIONE_PSWD(nomeNodo,oggetto,login,pswd,validita,dataScadenza,flag);
                        v_appo_struct.addElement(NEW_gestione_PSWD);

                        int newSize = v_appo_struct.size();

                        STRUCT_GESTIONE_PSWD = new GESTIONE_PSWD[newSize];                    
                        for(int i=0; i<newSize; i++)
                            STRUCT_GESTIONE_PSWD[i] = (GESTIONE_PSWD)(v_appo_struct.elementAt(i));
                    }                  
                }
            }
                    
            // ***********
            
            if( isPresent_int(V_modify_IDListHosts,indexItemSelected) == false )
                V_modify_IDListHosts.addElement(new Integer(indexItemSelected));

            refresh_ListHosts_toApply(V_modify_IDListHosts);
            
            //for(int i=0; i<V_modify_IDListHosts.size(); i++)
            //{
            //    System.out.println();
            //    System.out.println("Indici della lista modificati -->"+ ((Integer)V_modify_IDListHosts.elementAt(i)).intValue());   
            //}
            
            // ***********
            
            jB_commit_PSWD.setEnabled(true);

            //print_GESTIONE_PSWD(STRUCT_GESTIONE_PSWD);

            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Done, you can proceed with other operations or press 'COMMIT' to confirm.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
   }//GEN-LAST:event_jB_apply_PSWDActionPerformed

   private boolean isPresent_str(Vector vect, String str_value)
    {        
        if(vect != null)
        {
            for(int i=0; i<vect.size(); i++)
            {
                String str_appo = ((String)vect.elementAt(i));
                if(str_value.compareTo(str_appo) == 0)
                    return true;
            }
        }
        return false;
    }
   
   
    private boolean isPresent_int(Vector vect, int value)
    {        
        if(vect != null)
        {
            for(int i=0; i<vect.size(); i++)
            {
                int appo_index = ((Integer)vect.elementAt(i)).intValue();
                if(value == appo_index)
                    return true;
            }
        }
        return false;
    }
   
   
    private Vector copyIntoVector_PSWD(GESTIONE_PSWD[] get_PSWD)
    {
        if(get_PSWD == null)
            return (new Vector());
        
        int SIZE = STRUCT_GESTIONE_PSWD.length;
        Vector v_struct = new Vector(SIZE);
        
        for(int i=0; i<SIZE; i++)
        {                        
            GESTIONE_PSWD appo_gestione_PSWD = new GESTIONE_PSWD (STRUCT_GESTIONE_PSWD[i].nomeNodo,
                                                                    STRUCT_GESTIONE_PSWD[i].oggetto,
                                                                    STRUCT_GESTIONE_PSWD[i].login,
                                                                    STRUCT_GESTIONE_PSWD[i].pswd,
                                                                    STRUCT_GESTIONE_PSWD[i].validita,
                                                                    STRUCT_GESTIONE_PSWD[i].dataScadenza,
                                                                    STRUCT_GESTIONE_PSWD[i].flag);

            v_struct.addElement(appo_gestione_PSWD);
        }
        
        return v_struct;
    }
   
   private void print_GESTIONE_PSWD(GESTIONE_PSWD[] gestPSWD)
   {
        if(gestPSWD != null)
        {
            System.out.println();
            System.out.println("DEBUG print_GESTIONE_PSWD");
            
            for(int i=0; i<gestPSWD.length; i++)
            {
                System.out.println();
                System.out.println("nomeNodo 	-> "+new String(gestPSWD[i].nomeNodo).trim());
                System.out.println("oggetto	-> "+new String(gestPSWD[i].oggetto).trim());
                System.out.println("login	-> "+new String(gestPSWD[i].login).trim());                
                System.out.println("pswd	-> "+new String(gestPSWD[i].pswd).trim());
                System.out.println("validita	-> "+gestPSWD[i].validita);
                System.out.println("d.Scadenza	-> "+new String(gestPSWD[i].dataScadenza).trim());
                System.out.println("flag	-> "+gestPSWD[i].flag);
                System.out.println();
                
                
            }
            System.out.println();
        }
   }
   
   private void jB_commit_PSWDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commit_PSWDActionPerformed
       commit_PSWD();
       setClearGuiEdit_PSWD();
       setEnabledGuiEdit_PSWD(false);
       V_modify_IDListHosts.removeAllElements();
   }//GEN-LAST:event_jB_commit_PSWDActionPerformed

   private void jCheck_edit_PSWDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheck_edit_PSWDMouseReleased
       setEnabledGuiEdit_PSWD(jCheck_edit_PSWD.isSelected());
   }//GEN-LAST:event_jCheck_edit_PSWDMouseReleased

   private void jB_reload_PSWDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reload_PSWDActionPerformed
        Init_PSWD();
        jTF_NameNewHost.setText("");
        jCheck_edit_PSWD.setSelected(false);
        setEnabledGuiEdit_PSWD(false);
        setClearGuiEdit_PSWD();
        V_modify_IDListHosts.removeAllElements();
   }//GEN-LAST:event_jB_reload_PSWDActionPerformed

   private void setSelectedHosts_Or_Type()
   {
        int indexItemSelected = jList_Hosts.getSelectedIndex();
        if( indexItemSelected >= 0)
        {
            String str_Hostselected = new String(jList_Hosts.get_ID_Value(indexItemSelected));

            //System.out.println("str_Hostselected PRIMA del ReplaceAll (symbolModify)= "+str_Hostselected);
            if(str_Hostselected.indexOf(symbolModify) != -1)
            {
                str_Hostselected = str_Hostselected.replace(symbolModify,' ').trim();
                //System.out.println("str_Hostselected DOPO del ReplaceAll (symbolModify) = -"+str_Hostselected+"-");
            }

            if(STRUCT_GESTIONE_PSWD != null)
            {
                int size_pswd = STRUCT_GESTIONE_PSWD.length;               

                for(int i=0; i<size_pswd; i++)
                {
                    String appoNodo = new String(STRUCT_GESTIONE_PSWD[i].nomeNodo).trim();
                    String appoType = new String(STRUCT_GESTIONE_PSWD[i].oggetto).trim();

                    if( (str_Hostselected.compareTo(appoNodo) == 0) && (((String)jCBox_TypePSWD.getSelectedItem()).compareTo(appoType)==0) ) 
                    {                                          
                        jTF_host_1.setText(str_Hostselected);
                        jT_Login_PSWD.setText(new String(STRUCT_GESTIONE_PSWD[i].login).trim());
                                           
                        jPass.setText(new String(STRUCT_GESTIONE_PSWD[i].pswd).trim());
                        jPass2.setText(new String(STRUCT_GESTIONE_PSWD[i].pswd).trim());
                        
                        jCheck_del_PSWD.setEnabled(false);
                        jCheck_del_PSWD.setSelected(false);
                        return;
                    }
                }
                
                jTF_host_1.setText(str_Hostselected);
                jT_Login_PSWD.setText("");
                jPass.setText("");
                jPass2.setText("");
                jCheck_del_PSWD.setEnabled(false);
                jCheck_del_PSWD.setSelected(false);
            }
        }
   }
   
    private void jList_HostsMousePressed(java.awt.event.MouseEvent evt)
    {
       //System.out.println("jList_HostsMousePressed");
       setSelectedHosts_Or_Type();
       jCBox_TypePSWD.setEnabled(true);
       jCheck_edit_PSWD.setSelected(false);
       setEnabledGuiEdit_PSWD(false);
       //print_GESTIONE_PSWD(STRUCT_GESTIONE_PSWD);
    }
   
    
   
    private void jCBox_UserRoleDetailItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBox_UserRoleDetailItemStateChanged
        if(jCBox_UserRoleDetail.isEnabled() && jCBox_UserRoleDetail.getComponentCount() > 0)
        {
            jB_commit_user.setEnabled(jCB_edit_user.isSelected());
        }
    }//GEN-LAST:event_jCBox_UserRoleDetailItemStateChanged

    private void jTabAccessManager_StateChanged(javax.swing.event.ChangeEvent evt) 
    {
        int indexTab = jTab_Access_Manager.getSelectedIndex();        
        //System.out.println("jTab_Access_ManagerStateChanged -- Reload TAB --> "+indexTab);
        
        if(indexTab == 0)
        {         
            jCB_edit_profile.setSelected(false);
            setEnabledGuiEdit_P(false);
        
            read_Profile(-1);
            read_Class(-1);
            read_Function(-1);
            clearSelectionFunction();
        }
        else if(indexTab == 1)
        {
            jCB_edit_user.setSelected(false);
            setEnabledGuiEdit_U(false);
            
            jCBox_class.removeActionListener(event_jCBox_class);
            jCBox_class.setSelectedIndex(0);
            jCBox_class.addActionListener(event_jCBox_class);
            
            jCBox_Role.removeActionListener(event_jCBox_Role);
            jCBox_Role.setSelectedIndex(0);
            jCBox_Role.addActionListener(event_jCBox_Role);
            
            read_LoginUser(-1);            
            read_ProfileUser(-1,null);   
        }
        //else if(indexTab == 2)
        //{
            //System.out.println("Tab Passwords...");
        //}
    }
    
    private void jB_commit_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commit_userActionPerformed
        
        if( (s_user != null) && (jCB_edit_user.isSelected()) )
        {
            int num_enabled_Pro             = Vect_Profile_Enabled.size();
            int num_enabled_Pro_other_class = Vect_Profile_Enabled_otherClass.size();
            
            s_user.profileSeq = new S_PROFILE[num_enabled_Pro+num_enabled_Pro_other_class];
            
            int index;
            for(index =0 ; index<num_enabled_Pro; index++)
                s_user.profileSeq[index] = (S_PROFILE)Vect_Profile_Enabled.elementAt(index);
            
            for(int i=0; i<num_enabled_Pro_other_class; i++)
            {
                s_user.profileSeq[index++] = (S_PROFILE)Vect_Profile_Enabled_otherClass.elementAt(i);
            }
            
            /*for(int c=0; c<s_user.profileSeq.length; c++)
                System.out.println("Profili da abilitare --> "+new String(s_user.profileSeq[c].profile).trim());*/
            
            
            s_user.idRole = conf_GlobalParam.config_lib.roleSeq[jCBox_UserRoleDetail.getSelectedIndex()].idRole;
            
            boolean stat = conf_GlobalParam.CORBAConn.setUserConfiguration(conf_GlobalParam.OP_MODIFY,s_user);
               
            if(stat)
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(frameParent,"Modification operation correctly executed.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                reload_config();
                jB_commit.setEnabled(false);
            }
            else
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(frameParent,"Error: operation failed 1.","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE); 
            }    
            
        }
        else
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Notice: select the interest user.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
        }
            

        
    }//GEN-LAST:event_jB_commit_userActionPerformed

    private void jB_en_disProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_en_disProActionPerformed
        Object target = evt.getSource();
        
        boolean enableCommit = false;
        if(target == jB_enabledPro)
        {              
            
            int[] itemsSelected =  jList_Profile_dis.getSelectedIndices();
            
            String[] itemsValueSelected = new String[itemsSelected.length];
            for(int z=0; z<itemsSelected.length; z++)
                itemsValueSelected[z] = jList_Profile_dis.get_ID_Value(itemsSelected[z]);
            
            for(int x=0; x<itemsValueSelected.length; x++)
            {
                String itemAdd = itemsValueSelected[x];
                //System.out.println("itemsAdd --> "+itemAdd);

                int ClassProfilePresent = isPresentProfileClass(Vect_Profile_Disabled, Vect_Profile_Enabled, itemAdd);
                jList_Profile_dis.clearSelection();
                jList_Profile_dis.setSelectedValue(itemAdd,true);
                
                if(ClassProfilePresent == -1)
                {
                    move(jList_Profile_dis, jList_Profile_en,"profile_en.png",Vect_Profile_Disabled,Vect_Profile_Enabled,false);
                    enableCommit = true;
                    jTArea_descProfile.setText("");
                }
                else
                {
                    String nameClass = read_desc_Class(ClassProfilePresent);
                    if(nameClass.length() != 0)
                        nameClass = " <"+nameClass+" > ";
                    else
                        nameClass ="";

                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"Notice: user already has an enabled profile for the class: "+nameClass+".","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                }
            }
                
        }      
        else if(target == jB_disablePro)
        {
            
            if(conf_GlobalParam.RUOLO != 777)
            {
                int[] itemsSelected =  jList_Profile_en.getSelectedIndices();
                String[] itemsValueSelected = new String[itemsSelected.length];
                for(int z=0; z<itemsSelected.length; z++)
                    itemsValueSelected[z] = jList_Profile_en.get_ID_Value(itemsSelected[z]);
                
                for(int x=0; x<itemsValueSelected.length; x++)
                {
                    String itemAdd = itemsValueSelected[x];
                    //System.out.println("itemsAdd --> "+itemAdd);

                    int ClassProfile = getClassProfile(Vect_Profile_Enabled, itemAdd);
                    jList_Profile_en.clearSelection();
                    jList_Profile_en.setSelectedValue(itemAdd,true);

                    if(ClassProfile != 5)
                    {
                        move(jList_Profile_en, jList_Profile_dis,"profile_dis.png",Vect_Profile_Enabled,Vect_Profile_Disabled, false);
                        enableCommit = true;
                        jTArea_descProfile.setText("");
                    }
                    else
                    {
                        String nameClass = read_desc_Class(ClassProfile);
                        if(nameClass.length() != 0)
                            nameClass = " <"+nameClass+" > ";
                        else
                            nameClass ="";

                        javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                        warning.showMessageDialog(frameParent,"Attention: you haven't the grant to disable the profile <"+itemAdd+">.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                    }
                }                
            }
            else
            {
                move(jList_Profile_en, jList_Profile_dis,"profile_dis.png",Vect_Profile_Enabled,Vect_Profile_Disabled, false);
                enableCommit = true;
            }
             
        }
          
        if(jCB_edit_user.isSelected())
            jB_commit_user.setEnabled(enableCommit);
    }//GEN-LAST:event_jB_en_disProActionPerformed

    private void jCB_edit_userMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCB_edit_userMouseReleased
        setEnabledGuiEdit_U(jCB_edit_user.isSelected());
    }//GEN-LAST:event_jCB_edit_userMouseReleased

    private void jB_reloadAll_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reloadAll_userActionPerformed
        reloadAllUser_Server();
    }//GEN-LAST:event_jB_reloadAll_userActionPerformed

    private void jCBox_Role_ActionPerformed(java.awt.event.ActionEvent evt) {
        //System.out.println("jCBox_Role_ActionPerformed");
        
        if(jCBox_Role.getSelectedIndex() > 0)
        {
            read_LoginUser(conf_GlobalParam.config_lib.roleSeq[jCBox_Role.getSelectedIndex()-1].idRole);
            
            if(jCB_edit_user.isSelected())
                jList_User.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            else
                jList_User.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        }
        else
        {
            jList_User.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            read_LoginUser(-1);
        }
    }
    
    
    private void jRB_DEL_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_DEL_PActionPerformed
        setGui_DEL();
    }//GEN-LAST:event_jRB_DEL_PActionPerformed

    private void setGui_DEL()
    {
        read_Profile(-1);
        read_Class(-1);
        
        jList_Function_en.removeAll();
        jList_Function_dis.removeAll();
        Vect_Function_Enabled.removeAllElements();
        Vect_Function_Disable.removeAllElements();

        clearSelectionFunction();
        jTF_nameProfile.setText("");
        jTA_descProfile.setText("");
        
        jTF_nameProfile.setEnabled(false);
        jTA_descProfile.setEnabled(false);      
        jB_Add.setEnabled(false);
        jB_AddAll.setEnabled(false);
        jB_Rem.setEnabled(false);
        jB_RemAll.setEnabled(false);
        jList_Profile.setEnabled(true);
        jList_Class.setEnabled(true);
        jList_Function_en.setEnabled(false);
        jList_Function_dis.setEnabled(false);
        jB_commit.setEnabled(false);
    }
    
    private void jRB_MOD_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_MOD_PActionPerformed
        setGui_MOD();
    }//GEN-LAST:event_jRB_MOD_PActionPerformed

    private void setGui_MOD()
    {
        read_Profile(-1);
        read_Class(-1);
        
        jList_Function_en.removeAll();
        jList_Function_dis.removeAll();
        Vect_Function_Enabled.removeAllElements();
        Vect_Function_Disable.removeAllElements();
        
        clearSelectionFunction();
        jTF_nameProfile.setText("");
        jTA_descProfile.setText("");
        
        jTF_nameProfile.setEnabled(false);
        jTA_descProfile.setEnabled(true);      
        jB_Add.setEnabled(true);
        jB_AddAll.setEnabled(true);
        jB_Rem.setEnabled(true);
        jB_RemAll.setEnabled(true);
        jList_Class.setEnabled(true);
        jList_Function_en.setEnabled(true);
        jList_Function_dis.setEnabled(true);
        jList_Profile.setEnabled(true);
        
        jRB_MOD_P.setSelected(true);
        jB_commit.setEnabled(false);
    }
        
    private void setGui_ADD()
    {        
        read_Profile(-1);
        read_Class(-1);

        jList_Function_en.removeAll();
        jList_Function_dis.removeAll();
        Vect_Function_Enabled.removeAllElements();
        Vect_Function_Disable.removeAllElements();

        clearSelectionFunction();
        jTF_nameProfile.setText("");
        jTA_descProfile.setText("");
        
        jTF_nameProfile.setEnabled(true);
        jTA_descProfile.setEnabled(true);      
        jB_Add.setEnabled(true);
        jB_AddAll.setEnabled(true);
        jB_Rem.setEnabled(true);
        jB_RemAll.setEnabled(true);
        jList_Class.setEnabled(true);
        jList_Function_en.setEnabled(true);
        jList_Function_dis.setEnabled(true);
        jList_Profile.setEnabled(false);

        jRB_ADD_P.setSelected(true);
        jTF_nameProfile.requestFocus();
        jB_commit.setEnabled(false);
    }
    
    
    private void jRB_ADD_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_ADD_PActionPerformed
        setGui_ADD();
    }//GEN-LAST:event_jRB_ADD_PActionPerformed

    private void jTF_nameProfileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_nameProfileKeyReleased
        
        jTF_nameProfile.setText(jTF_nameProfile.getText().toUpperCase());
        if(jCB_edit_profile.isSelected())
        {
            jB_commit.setEnabled(true);
        }
    }//GEN-LAST:event_jTF_nameProfileKeyReleased

    private void jTA_descProfileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTA_descProfileKeyReleased
        
        String strDesc = jTA_descProfile.getText().trim();   
        
        if( strDesc.length() >= conf_GlobalParam.MAX_DESC_PROFILE )
        {       
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Profile Description max "+conf_GlobalParam.MAX_DESC_PROFILE+" characters.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            jTA_descProfile.setText(strDesc.substring(0,conf_GlobalParam.MAX_DESC_PROFILE));
        }
        if(jCB_edit_profile.isSelected())
            jB_commit.setEnabled(true);
    }//GEN-LAST:event_jTA_descProfileKeyReleased

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        
        String strProfile = jTF_nameProfile.getText().trim();
        
        if( strProfile.length() == 0)
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Profile name is empty! Insert it!","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
            return;            
        }        
        else if(jRB_ADD_P.isSelected())
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            
            warning.showMessageDialog(frameParent,"The profile name < "+strProfile+" > will get as a primary Key. It will not be possible modify it any more." ,"Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
        }
        
        
        if( jRB_ADD_P.isSelected() || jRB_MOD_P.isSelected() )
        {        
            if(jRB_ADD_P.isSelected())
            {
                boolean profileExist    = isProfilePresent(strProfile);            

                if(profileExist)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"Attention: profile name < "+strProfile+" > already exist!","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                    return;  
                }
            }
            
            if(ID_ClassSelected < 0)
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(frameParent,"Attention: select a class and link to the profile.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                return;  
            }     

            int EnablefunctionSize = Vect_Function_Enabled.size();
            if(EnablefunctionSize <= 0)
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(frameParent,"Attention: Enable the < Function Class > to the profile of interest.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                return;             
            }
        
            S_PROFILE s_profile_ADD = new S_PROFILE();

            //if(jRB_MOD_P.isSelected())
            //    s_profile_ADD.profile       = conf_GlobalParam.set_String_toChar(ID_STR_PROFILE,conf_GlobalParam.MAX_NAME_PROFILE);
            //else
                s_profile_ADD.profile       = conf_GlobalParam.set_String_toChar(strProfile,conf_GlobalParam.MAX_NAME_PROFILE);                

            String strDescProfile       = jTA_descProfile.getText().trim();
            strDescProfile = strDescProfile.replaceAll("'"," ");
            
            s_profile_ADD.descProfile   = conf_GlobalParam.set_String_toChar(strDescProfile,conf_GlobalParam.MAX_DESC_PROFILE);

            s_profile_ADD.idClass       = ID_ClassSelected;

            s_profile_ADD.functionSeq = new S_FUNCTION[EnablefunctionSize];
            for(int i=0; i<EnablefunctionSize; i++)
            {
                s_profile_ADD.functionSeq[i] = (S_FUNCTION)Vect_Function_Enabled.elementAt(i);
            }

            // ---- vuoto
            s_profile_ADD.loginSeq = new S_LOGIN[1];
            s_profile_ADD.loginSeq[0] = new S_LOGIN(conf_GlobalParam.set_String_toChar("",conf_GlobalParam.MAX_LOGIN_LEN));
            // ---- vuoto

            //////////////////////////////////////////////////////////////////////////////

            /*System.out.println(" profile = "+new String(s_profile_ADD.profile).trim());
            System.out.println(" nameProfile = "+new String(s_profile_ADD.profile).trim());
            System.out.println(" descProfile = "+new String(s_profile_ADD.descProfile).trim());
            System.out.println(" idClass = "+(s_profile_ADD.idClass));

            for(int x=0; x<s_profile_ADD.functionSeq.length; x++)
            {
                System.out.println(" S_PROFILE idClass = "+s_profile_ADD.functionSeq[x].idClass);
                System.out.println(" S_PROFILE nameFunction = "+new String(s_profile_ADD.functionSeq[x].nameFunction).trim());
                System.out.println(" S_PROFILE descFunction = "+new String(s_profile_ADD.functionSeq[x].descFunction).trim());
                System.out.println(" S_PROFILE version = "+new String(s_profile_ADD.functionSeq[x].version).trim());
                System.out.println(" S_PROFILE releasedData = "+new String(s_profile_ADD.functionSeq[x].releasedData).trim());
                System.out.println(" S_PROFILE author = "+new String(s_profile_ADD.functionSeq[x].author).trim());
                System.out.println(" S_PROFILE vendor = "+new String(s_profile_ADD.functionSeq[x].vendor).trim());            
            }*/

            //////////////////////////////////////////////////////////////////////////////

            boolean stat_OP_Profile = false;
            String strMessage = "";

            if(jRB_ADD_P.isSelected())
            {
                 stat_OP_Profile = conf_GlobalParam.CORBAConn.setProfileConfiguration(conf_GlobalParam.OP_INSERT,s_profile_ADD);
                 strMessage = "was correctly inserted.";
            }
            else
            {
                 stat_OP_Profile = conf_GlobalParam.CORBAConn.setProfileConfiguration(conf_GlobalParam.OP_MODIFY,s_profile_ADD);
                 strMessage = "was correctly modified.";
            }

            if(stat_OP_Profile)
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(frameParent,"The profile < "+strProfile+" > "+strMessage,"Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                reloadAllProfile_Server();
                setEnabledGuiEdit_P(true);
                return;  
            }
            else
            {
                javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                warning.showMessageDialog(frameParent,"Error: failed operation.","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE); 
                return;
            }         
        }          
        else if (jRB_DEL_P.isSelected())
        {        
            
            javax.swing.JOptionPane warning_1 = new javax.swing.JOptionPane();
            int YES_NO  = warning_1.showConfirmDialog(frameParent,"Do you really want to eliminate the profile < "+ID_STR_PROFILE+ "> and its own configured relation?","Info Message",javax.swing.JOptionPane.YES_NO_OPTION); 
                    
            if(YES_NO == 0)
            { 
                S_PROFILE s_profile_DEL = new S_PROFILE();
                s_profile_DEL.profile       = conf_GlobalParam.set_String_toChar(ID_STR_PROFILE,conf_GlobalParam.MAX_NAME_PROFILE);
                s_profile_DEL.descProfile   = conf_GlobalParam.set_String_toChar("",conf_GlobalParam.MAX_DESC_PROFILE);
                s_profile_DEL.idClass       = ID_ClassSelected;
                s_profile_DEL.functionSeq = new S_FUNCTION[0];
                // ---- vuoto
                s_profile_DEL.loginSeq = new S_LOGIN[1];
                s_profile_DEL.loginSeq[0] = new S_LOGIN(conf_GlobalParam.set_String_toChar("",conf_GlobalParam.MAX_LOGIN_LEN));
                // ---- vuoto

                boolean DEL_OP_Profile = conf_GlobalParam.CORBAConn.setProfileConfiguration(conf_GlobalParam.OP_DELETE,s_profile_DEL);


                if(DEL_OP_Profile)
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"The profile was correctly deleted.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    reloadAllProfile_Server();
                    setEnabledGuiEdit_P(true);
                    return;              
                }
                else
                {
                    javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
                    warning.showMessageDialog(frameParent,"Error: operation failed 2.","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE); 
                    return;
                }
            }
        }
        
    }//GEN-LAST:event_jB_commitActionPerformed
    
    private void setEnabledGuiEdit_U(boolean enabled)
    {                
        jCB_edit_user.setSelected(enabled);
        jRB_MOD_U.setEnabled(enabled);
        
        if(conf_GlobalParam.RUOLO == 1) //Luca ultimo -- commenta la if
        {
            jB_enabledPro.setEnabled(enabled);
            jB_disablePro.setEnabled(enabled);
        }
        
        jCBox_UserRoleDetail.setEnabled(false);
        
        clearSelectionUser();
        
        if(enabled)
        {
                        
            setGui_MOD_user();
            if(jCBox_Role.getSelectedIndex() > 0)
                jList_User.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            jList_Profile_dis.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            jList_Profile_en.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
        else
        {
            jList_User.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jList_Profile_dis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jList_Profile_en.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jB_commit_user.setEnabled(false);
            
            /// reload interfaceGUI
            /*try
            {
                jCBox_class.removeActionListener(event_jCBox_class);
                jCBox_class.setSelectedIndex(0);
                jCBox_class.addActionListener(event_jCBox_class);

                jCBox_Role.removeActionListener(event_jCBox_Role);
                jCBox_Role.setSelectedIndex(0);
                jCBox_Role.addActionListener(event_jCBox_Role);
                
                System.out.println("read_LoginUser 1");
                read_LoginUser(-1); 
                System.out.println("read_LoginUser 2");
                read_ProfileUser(-1,null); 
                System.out.println("read_LoginUser 3");
            }
            catch (Exception e){
		  System.out.println("puccio metti le system sulle cathc(1)");
            }*/
            
             
        }
            
    }
    
    private void setGui_MOD_user()
    {  
        jRB_MOD_U.setSelected(true);
        
        if(jCBox_Role.getSelectedIndex() > 0)
            read_LoginUser(conf_GlobalParam.config_lib.roleSeq[jCBox_Role.getSelectedIndex()-1].idRole);
        else
            read_LoginUser(-1);

       jList_Profile_dis.removeAll();
       jList_Profile_en.removeAll();
       Vect_Profile_Disabled.removeAllElements();
       Vect_Profile_Enabled.removeAllElements();
    }
    
    
    
    private void setEnabledGuiEdit_P(boolean enabled)
    {                
        jCB_edit_profile.setSelected(enabled);
        jRB_ADD_P.setEnabled(enabled);
        jRB_DEL_P.setEnabled(enabled);
        jRB_MOD_P.setEnabled(enabled);
        jTF_nameProfile.setEnabled(enabled);
        jTA_descProfile.setEnabled(enabled);
        jB_Add.setEnabled(enabled);
        jB_AddAll.setEnabled(enabled);
        jB_Rem.setEnabled(enabled);
        jB_RemAll.setEnabled(enabled);
        
        if(enabled == false)
        {
            jB_commit.setEnabled(false);
            jList_Profile.setEnabled(true);
            jList_Class.setEnabled(true);
            jList_Function_en.setEnabled(true);
        }
        else
        {
            if(jRB_ADD_P.isSelected())
                setGui_ADD();
            else if(jRB_MOD_P.isSelected())
                setGui_MOD();
            else
                setGui_DEL();
        }
        
    }
    
    /*private String cript_xor(char[] str, char[] key)
    {
      for(int i=0; i<str.length; i++)
        str[i] = (char)(str[i]^key[i%key.length]);
      return new String(str);
    }*/
    
    
   /*private String cript_xor2(char[] data, char[] key, int block_size)
    {           
        int y=0;
        for(int i=0; i<=data.length;)
        {    
            for (int x=0; x<=block_size; x++)
            {                 
                if(i < data.length)
                {
                    data[i] ^= key[y];
                }
                i++;
            }
            y++;
            if(y == key.length)
                y=0;
        }
        return (new String(data).trim());
    } */
    
    private void  setEnabledGuiEdit_PSWD (boolean enabled)
    {
        jTF_host_1.setEnabled(enabled);
        jCBox_TypePSWD.setEnabled(enabled);
        jT_Login_PSWD.setEnabled(enabled);
        jPass.setEnabled(enabled);
        jPass2.setEnabled(enabled);
        jB_apply_PSWD.setEnabled(enabled);
        jCheck_del_PSWD.setEnabled(enabled);
    }
    
    private void setClearGuiEdit_PSWD()
    {
        jTF_host_1.setText("");
        jTF_NameNewHost.setText("");
        jT_Login_PSWD.setText("");
        jPass.setText("");
        jPass2.setText("");
        jCheck_del_PSWD.setSelected(false);
    }
    
    
    
    private void jCB_edit_profileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCB_edit_profileMouseReleased
       
        setEnabledGuiEdit_P(jCB_edit_profile.isSelected());
    }//GEN-LAST:event_jCB_edit_profileMouseReleased

    private void jB_FunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_FunctionActionPerformed
        Object target = evt.getSource();
        
        if(target == jB_Add)
        {  
            move(jList_Function_en, jList_Function_dis,"function_dis.png",Vect_Function_Enabled,Vect_Function_Disable, false);            
        }
        else if(target == jB_AddAll)
        {	
	    move(jList_Function_en, jList_Function_dis,"function_dis.png",Vect_Function_Enabled,Vect_Function_Disable, true);
        }
        else if(target == jB_Rem)
        {
	    move(jList_Function_dis, jList_Function_en,"function_en.png",Vect_Function_Disable,Vect_Function_Enabled,false);
        }
        else if(target == jB_RemAll)
        {	    
            move(jList_Function_dis, jList_Function_en,"function_en.png",Vect_Function_Disable,Vect_Function_Enabled,true);
        }
                
        if(jCB_edit_profile.isSelected())
            jB_commit.setEnabled(true);
    }//GEN-LAST:event_jB_FunctionActionPerformed

    
    
    private void jB_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reloadActionPerformed
        reloadAllProfile_Server();
    }//GEN-LAST:event_jB_reloadActionPerformed

    private void jCBox_class_ActionPerformed(java.awt.event.ActionEvent evt) {
       
       //System.out.println("jCBox_class_ActionPerformed");
        
        int indexCB = jCBox_class.getSelectedIndex();
        if( indexCB >= 0)
        {       
            if(indexCB == 0)
                read_ProfileUser(-1,s_user);
            else
            {
                int id_class_sel = read_ID_Class((String)jCBox_class.getSelectedItem());
                read_ProfileUser(id_class_sel,s_user);
            }
        }
        else
        {
            read_ProfileUser(-1,null);
        }
    }
        
    private int isPresentProfileClass(Vector vett, Vector vett1 ,String itemAdd )
    {
        for(int i=0; i<vett.size(); i++)
        {
            S_PROFILE s_profile = (S_PROFILE)vett.elementAt(i);            
            String profile_name = new String(s_profile.profile).trim();
                        
            if( profile_name.equals(itemAdd) )
            {
                for(int x=0; x<vett1.size();x++)
                {
                    //System.out.println(s_profile.idClass+" == "+((S_PROFILE)vett1.elementAt(x)).idClass);
                    if( s_profile.idClass == ((S_PROFILE)vett1.elementAt(x)).idClass )
                    {
                        return s_profile.idClass;
                    }
                }
            }
        }
        return -1;    
    }
    
    private int getClassProfile(Vector vett,String profile)
    {
        for(int i=0; i<vett.size(); i++)
        {
            S_PROFILE s_profile = (S_PROFILE)vett.elementAt(i);            
            String profile_name = new String(s_profile.profile).trim();
            if( profile_name.equals(profile) )
                    return s_profile.idClass;
        }
        return -1; 
    }
    
    
    private void move(conf_JListIcon l1, conf_JListIcon l2, String icon_2,Vector V1,Vector V2,boolean all) 
    {
        if (all) 
        {
            /*for (int i=0; i<l1.getItemCount(); i++) 
            {
                l2.add(l1.getItem(i));
            }
            l1.removeAll();*/
            
            //gestione vettori
            for (int i=0; i<V1.size(); i++) 
            {                    
                Class obj_class = V1.elementAt(i).getClass();                
                //System.out.println("obj_class "+obj_class.toString());
                
                if( obj_class == (new S_FUNCTION()).getClass() )
                {
                    S_FUNCTION appoFunction = ( (S_FUNCTION)V1.elementAt(i) );
                    V2.addElement(appoFunction);
                    l2.addElement(icon_2,icon_2,new String(appoFunction.nameFunction).trim());
                }
                else if( obj_class == ( new S_PROFILE()).getClass() )
                {
                    S_PROFILE appoProfile = ( (S_PROFILE)V1.elementAt(i) );
                    V2.addElement(appoProfile);
                    l2.addElement(icon_2,icon_2,new String(appoProfile.profile).trim());
                }
                else
                    System.out.println("ERRORE Cast Object >> metod - move(...) A ");  
            }
            V1.removeAllElements();
            l1.removeAll();
            
        } 
        else 
        {
                        
            String[] items = l1.getStringValues();
            int[] itemIndexes = l1.getSelectedIndices();

            l2.clearSelection();
            for (int i=0; i<items.length; i++) 
            {                  
                //gestione vettori
                V2.addElement(V1.elementAt(itemIndexes[i]));
                
                Class obj_class = V1.elementAt(i).getClass();                          
                //System.out.println("obj_class "+obj_class.toString());
                
                if( obj_class == (new S_FUNCTION()).getClass() )
                {
                    S_FUNCTION appoFunction = ((S_FUNCTION)(V1.elementAt(itemIndexes[i])));
                    l2.addElement(icon_2,icon_2,new String(appoFunction.nameFunction).trim());                 
                }
                else if( obj_class == (new S_PROFILE()).getClass() )
                {
                    S_PROFILE appoProfile = ((S_PROFILE)(V1.elementAt(itemIndexes[i])));                    
                    l2.addElement(icon_2,icon_2,new String(appoProfile.profile).trim());  
                }
                else
                    System.out.println("ERRORE Cast Object >> metod - move(...) B ");
                
                l2.setSelectedIndex(l2.getItemCount()-1); 
                if (i == 0) 
                {
                    l2.setVisibleRowCount(l2.getItemCount()-1);
                }
                
            }
            for (int i=itemIndexes.length-1; i>=0; i--) 
            {
                l1.remElement(itemIndexes[i]);
                //gestione vettori 
                V1.removeElementAt(itemIndexes[i]);
            }
        }
        l1.clearSelection();
        l2.clearSelection();
        
        
        clearSelectionFunction();
        
        /*System.out.println(" ************************ ");
        System.out.println("V1.size() >> "+V1.size());
        System.out.println("V2.size() >> "+V2.size());
        System.out.println(" ************************ ");*/
    }
 
    private void jList_Function_enMousePressed(java.awt.event.MouseEvent evt)
    {        
        Object obj = evt.getSource();
        Vector AppoVectorFunction = new Vector();
        
        int indexItemSelected = -1;
        if(obj == jList_Function_en)
        {
            indexItemSelected = jList_Function_en.getSelectedIndex();
            jList_Function_dis.clearSelection();
            AppoVectorFunction = Vect_Function_Enabled;
        }
        else
        {
            indexItemSelected = jList_Function_dis.getSelectedIndex();
            jList_Function_en.clearSelection();
            AppoVectorFunction = Vect_Function_Disable;
        }
        
        if( indexItemSelected >= 0)
        {            
            S_FUNCTION s_function_appo = (S_FUNCTION)AppoVectorFunction.elementAt(indexItemSelected);

            jTF_nameFunction.setText((new String(s_function_appo.nameFunction)).trim());
            jTF_descFunction.setText((new String(s_function_appo.descFunction)).trim());
            jTF_verFunction.setText((new String(s_function_appo.version)).trim());
            jTF_releaseFunction.setText((new String(s_function_appo.releasedData)).trim());
            jTF_authorFunction.setText((new String(s_function_appo.author)).trim());
            jTF_vendorFunction.setText((new String(s_function_appo.vendor)).trim());

            //System.out.println(" --> idClass = "+s_function_appo.idClass);
            //System.out.println(" --> idFunction = "+s_function_appo.idFunction);  
            
            if(jCB_edit_profile.isSelected())
            {
                if(jRB_ADD_P.isSelected() == false)
                    read_Class(s_function_appo.idClass);
            }
            else
                read_Class(s_function_appo.idClass);
            
            read_Profile(s_function_appo.idClass,s_function_appo.idFunction);
    
        }
        else
        {
            clearSelectionFunction();
        }        
    }
    
    private void jList_Profile_enMousePressed(java.awt.event.MouseEvent evt)
    {
        int indexItemSelected = jList_Profile_en.getSelectedIndex();        
        if(indexItemSelected >= 0)
        {
            String str_profile = jList_Profile_en.get_ID_Value(indexItemSelected).trim();
            //System.out.println(" jList_Profile_enMousePressed str_profile ---> "+str_profile);

            if( jCB_edit_user.isSelected() == false ) 
            {            
                int ID_ROLE = -1;
                if(jCBox_Role.getSelectedIndex() > 0)
                    ID_ROLE = conf_GlobalParam.config_lib.roleSeq[jCBox_Role.getSelectedIndex()-1].idRole;

                load_User_on_Profile(str_profile,ID_ROLE);
            }

            jTArea_descProfile.setText(getDescProfile(str_profile));
        }
    }
    
    private void jList_Profile_disMousePressed(java.awt.event.MouseEvent evt)
    {
        int indexItemSelected = jList_Profile_dis.getSelectedIndex();
        if(indexItemSelected >= 0)
        {
            String str_profile = jList_Profile_dis.get_ID_Value(indexItemSelected).trim();
            //System.out.println(" jList_Profile_disMousePressed str_profile ---> "+str_profile);

            if( jCB_edit_user.isSelected() == false )
            {       
                int ID_ROLE = -1;
                if(jCBox_Role.getSelectedIndex() > 0)
                    ID_ROLE = conf_GlobalParam.config_lib.roleSeq[jCBox_Role.getSelectedIndex()-1].idRole;

                load_User_on_Profile(str_profile,ID_ROLE);
            }

            jTArea_descProfile.setText(getDescProfile(str_profile));
        }
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
                read_Class(s_profile_selected.idClass);

                ID_STR_PROFILE = new String(s_profile_selected.profile).trim();
                read_Function(ID_STR_PROFILE);
                jTF_nameProfile.setText(new String(s_profile_selected.profile).trim());
                jTA_descProfile.setText(new String(s_profile_selected.descProfile).trim());

                if( jRB_DEL_P.isSelected() && jCB_edit_profile.isSelected() )
                    jB_commit.setEnabled(true);
            }
            else
            {
                ID_STR_PROFILE = "";
                jList_Function_en.removeAll(); 
                jList_Class.removeAll();
                ID_ClassSelected = -1;
                clearSelectionFunction();
                jTF_nameProfile.setText("");
                jTA_descProfile.setText("");
                jB_commit.setEnabled(false);
            }       
        }
    }   
    
    private void jList_UserMousePressed(java.awt.event.MouseEvent evt)
    {
        
        //for(int i=0; i<conf_GlobalParam.config_lib.userSeq.length; i++)
            //{
                //String str_login_appo = new String(conf_GlobalParam.config_lib.userSeq[i].login).trim();
        
        /*javax.swing.JList l = new javax.swing.JList();
        l.getSelectedIndices()*/
        
        int[] selectedIndices = jList_User.getSelectedIndices();
        
        int sizeItemsSel = selectedIndices.length;
        
        if( sizeItemsSel == 1)
        {
            String str_login = jList_User.get_ID_Value(selectedIndices[0]);            
                           
            s_user = conf_GlobalParam.CORBAConn.getUserConfiguration(str_login,-1);

            int id_class_sel = read_ID_Class((String)jCBox_class.getSelectedItem());
            read_ProfileUser(id_class_sel,s_user);


            jTF_login.setText(str_login);
            jTF_nomeUtente.setText((new String(s_user.nomeUtente)).trim());
            jTF_descUser.setText((new String(s_user.descUser)).trim());
            jTArea_descProfile.setText("");

            int PosRoleUser = 0;
            jCBox_UserRoleDetail.removeAllItems();
            for(int x=0; x<conf_GlobalParam.config_lib.roleSeq.length; x++)
            {                
                jCBox_UserRoleDetail.addItem((new String(conf_GlobalParam.config_lib.roleSeq[x].descRole)).trim());
                if(s_user.idRole == conf_GlobalParam.config_lib.roleSeq[x].idRole)
                    PosRoleUser =x;
               
            }
            jCBox_UserRoleDetail.setSelectedIndex(PosRoleUser);

            if(s_user.abilitazioneUtente)
            {
                jL_Enabled.setText("Allowed");
                jL_Enabled.setForeground(java.awt.Color.green.darker());
            } 
            else
            {
                jL_Enabled.setText("Not Allowed");
                jL_Enabled.setForeground(java.awt.Color.red);
            }
              
            if( (conf_GlobalParam.RUOLO == 777) && (jCB_edit_user.isSelected()) )
                jCBox_UserRoleDetail.setEnabled(true);
        }
        /*else if(sizeItemsSel > 1)
        {
            System.out.println("Multi USER SELEZINATO");
        }*/
        else
            clearSelectionUser();
    }
    
    
    
    private void jList_ClassMousePressed(java.awt.event.MouseEvent evt)
    {
        //System.out.println("jList_ClassMousePressed");
        int indexItemSelected = jList_Class.getSelectedIndex();
        if( indexItemSelected >= 0)
        {            
            String ItemSelected = jList_Class.get_ID_Value(indexItemSelected);
                        
            ID_ClassSelected = read_ID_Class(ItemSelected);

            /*if (jCB_edit_profile.isSelected() == false )
            {
                read_Profile(ID_ClassSelected);
            }
            else if (jRB_MOD_P.isSelected() == false)*/
            read_Profile(ID_ClassSelected);                
            read_Function(ID_ClassSelected);

            /*if( (jRB_ADD_P.isSelected() || jRB_MOD_P.isSelected()) && (jCB_edit_profile.isSelected()) )
                move(jList_Function_en, jList_Function_dis,Vect_Function_Enabled,Vect_Function_Disable, true);*/ 
            
        }
        else
            ID_ClassSelected = -1;
    }
    
    private void clearSelectionUser()
    {
        jList_User.clearSelection();
        jTF_login.setText("");
        jTF_nomeUtente.setText("");
        jTF_descUser.setText("");
        jCBox_UserRoleDetail.removeAllItems();
        jL_Enabled.setText("");
        jTArea_descProfile.setText("");
    }
    
    private void clearSelectionFunction()
    {
        jList_Function_en.clearSelection();
        jList_Function_dis.clearSelection();
        jTF_nameFunction.setText("");
        jTF_descFunction.setText("");
        jTF_verFunction.setText("");
        jTF_releaseFunction.setText("");
        jTF_authorFunction.setText("");
        jTF_vendorFunction.setText("");
    }
        
    private void initLoadAll()
    {
        TH_EXIT = false;
        OperationTH = 0;
        th = null;
        th = new Thread(this,"initLoadAll()");
        th.start();
    }
         
    private void reloadAllProfile_Server()
    {
        TH_EXIT = false;
        OperationTH = 1;
        th = null;
        th = new Thread(this,"reloadAllProfile_Server()");
        th.start();
    
    }
    
    private void reloadAllUser_Server()
    {
        TH_EXIT = false;
        OperationTH = 2;
        th = null;
        th = new Thread(this,"reloadAllUser_Server()");
        th.start();
    }
    
    private void reload_config()
    {
        TH_EXIT = false;
        OperationTH = 3;
        th = null;
        th = new Thread(this,"reload_config()");
        th.start();
    }
    
    
    private void Init_PSWD()
    {
        TH_EXIT = false;
        OperationTH = 4;
        th = null;
        th = new Thread(this,"Init_PSWD()");
        th.start();
    }
        
    private void commit_PSWD()
    {
        TH_EXIT = false;
        OperationTH = 5;
        th = null;
        th = new Thread(this,"commit_PSWD()");
        th.start();
    
    }
    
    
    private void refresh_ListHosts_toApply(Vector V_index_Modify)
    {        
        jList_Hosts.removeAll();
        jList_Hosts.removeMouseListener(eventList_Hosts);            
                
        boolean flag_conf = false;

        for(int i=0; i<V_Hosts.size(); i++)
        {
            String descHosts = (String)V_Hosts.elementAt(i);

            flag_conf = false;
            if(STRUCT_GESTIONE_PSWD != null)
            {
                for(int x=0; x<STRUCT_GESTIONE_PSWD.length; x++)
                {
                    String appoHosts = new String(STRUCT_GESTIONE_PSWD[x].nomeNodo).trim();

                    if( (descHosts).compareTo(appoHosts) == 0)
                    {                              
                        if(isPresent_int(V_index_Modify,i) == true)                                
                            jList_Hosts.addElement("key_s.gif","key_s.gif",descHosts + symbolModify);
                        else
                            jList_Hosts.addElement("key_s.gif","key_s.gif",descHosts);
                        flag_conf = true;
                        break;
                    }
                }
            }
            if(flag_conf == false)
            {
                if(isPresent_int(V_index_Modify,i) == true)
                    jList_Hosts.addElement("exec_sync.png","exec_sync.png",descHosts+symbolModify);
                else
                    jList_Hosts.addElement("exec_sync.png","exec_sync.png",descHosts);
            }
        }
               
        //System.out.println("repaint 3");
        jList_Hosts.validate();   
    	jList_Hosts.updateUI();
    	jScroll_Hosts.validate();       	
    	jScroll_Hosts.setViewportView(jList_Hosts);
        //System.out.println("repaint 4");
        
        jList_Hosts.addMouseListener(eventList_Hosts);

        setClearGuiEdit_PSWD();
        jCheck_edit_PSWD.setSelected(false);
        setEnabledGuiEdit_PSWD(false);           
    }
       
    private void getHosts()
    {        
    	V_Hosts = new Vector();
        jList_Hosts.removeAll(); 
        jList_Hosts.removeMouseListener(eventList_Hosts);               

        if(STRUCT_GESTIONE_PSWD != null)
        {
            for(int x=0; x<STRUCT_GESTIONE_PSWD.length; x++)
            {
                String appoHosts = new String(STRUCT_GESTIONE_PSWD[x].nomeNodo).trim();                                
                if(isPresent_str(V_Hosts,appoHosts) == false)
                {
                    V_Hosts.addElement(appoHosts);
                    jList_Hosts.addElement("key_s.gif","key_s.gif",appoHosts);
                    //System.out.println("jList_Hosts.addElement ----> "+appoHosts);
                }
            }
        }
        else
            System.out.println("STRUCT_GESTIONE_PSWD == null");
        
       System.out.println("refresh 1");
        try
        {
            
            System.out.println("repaint 5");
            jList_Hosts.validate();   
        	jList_Hosts.updateUI();
        	jScroll_Hosts.validate();       	
        	jScroll_Hosts.setViewportView(jList_Hosts);        	
			System.out.println("repaint 6");
            
            jList_Hosts.addMouseListener(eventList_Hosts);
        }
        catch (Exception e)
        {
            System.out.println("catch refresh");
        }       
    }
    
    
    
    private GESTIONE_PSWD[] getFile_PSWD()
    {
        GESTIONE_PSWD[] gestPSWD = conf_GlobalParam.CORBAConn.getFilePSWD(conf_GlobalParam.MACHINE_NAME,strNomeFile,0);
        
        if(gestPSWD != null)
        {            
            //print_GESTIONE_PSWD(gestPSWD);
            return gestPSWD;
        }
        return null;
    }
    
    private void putFile_PSWD(GESTIONE_PSWD[] struct_GESTIONE_PSWD)
    {
            
	int esito = conf_GlobalParam.CORBAConn.putFilePSWD("ALL",strNomeFile,0,struct_GESTIONE_PSWD);
        
        //System.out.println("  putFilePSWD return --> "+ esito);
        //if(esito == 1)
        //	System.out.println("  putFilePSWD return ERRORE --> ");
        //      
        
        if(esito != 1)
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Operation correctly executed.","Info Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            reload_config();
            jB_commit.setEnabled(false);
        }
        else
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(frameParent,"Error: operation failed 3.","Error Message",javax.swing.JOptionPane.ERROR_MESSAGE); 
        }    

    }
    
    
    
     /**
     *Metodo run, per la gestione del thread della classe.
     *Il Thread della classe Ã¨ utilizzato per migliorare le performance dell'interfaccia grafica durante il recupero/elaborazioni dei dati.
     */ 
    public void run()
    {
	
        this.setCursor(this.Cur_wait);      
        
        jTab_Access_Manager.setCursor(this.Cur_wait);
        jTab_Access_Manager.setEnabled(false);
                
        jList_Class.setCursor(this.Cur_wait);
        jList_Function_dis.setCursor(this.Cur_wait);
        jList_Function_en.setCursor(this.Cur_wait);
        jList_Profile.setCursor(this.Cur_wait);
        jList_User.setCursor(this.Cur_wait);
        jList_Profile_dis.setCursor(this.Cur_wait);
        jList_Profile_en.setCursor(this.Cur_wait);
        
        jB_reloadAll_user.setEnabled(false);
        jB_reload.setEnabled(false);
        
         System.out.println("OperationTH="+OperationTH);
        if(OperationTH == 0) // initLoadAll
        {  
            conf_GlobalParam.config_lib = conf_GlobalParam.CORBAConn.getConfigLib();
            System.out.println("1 prima debugPrint");
        debugPrint();
         System.out.println("1 dopo debugPrint");
            //Profile
            read_Profile(-1);
            read_Class(-1);
            read_Function(-1);
                        
                                  
            jCBox_Role.removeActionListener(event_jCBox_Role);
            jCBox_Role.removeAllItems();
            jCBox_Role.addItem("None");
            for(int i = 0; i < conf_GlobalParam.config_lib.roleSeq.length; i++)
                jCBox_Role.addItem((new String(conf_GlobalParam.config_lib.roleSeq[i].descRole)).trim());
            jCBox_Role.addActionListener(event_jCBox_Role);
            

            if(jCBox_Role.getSelectedIndex() > 0)
                read_LoginUser(conf_GlobalParam.config_lib.roleSeq[jCBox_Role.getSelectedIndex()-1].idRole);
            else
                read_LoginUser(-1);
            
            read_ProfileUser(-1,null);
                
            jTab_Access_Manager.addChangeListener(event_jTab_Access_Manager);
            
        }  
        else if(OperationTH == 1) // reloadAllProfile_Server()
        {  
            conf_GlobalParam.config_lib = conf_GlobalParam.CORBAConn.getConfigLib();        
            System.out.println("2 prima debugPrint");
        debugPrint();
         System.out.println("2 dopo debugPrint");
            if(jCB_edit_profile.isSelected())
            {
                if(jRB_ADD_P.isSelected())
                    setGui_ADD();
                else if(jRB_MOD_P.isSelected())
                    setGui_MOD();
                else
                    setGui_DEL();
            }
            else
            {
                read_Profile(-1);
                read_Class(-1);
                read_Function(-1);
                clearSelectionFunction();
            }
            jB_commit.setEnabled(false);
        }
        else if(OperationTH == 2) // reloadAllUser_Server()
        {
            conf_GlobalParam.config_lib = conf_GlobalParam.CORBAConn.getConfigLib();
            System.out.println("3 prima debugPrint");
        debugPrint();
         System.out.println("3 dopo debugPrint");
         
            clearSelectionUser();
            
            jCBox_class.removeActionListener(event_jCBox_class);
            jCBox_class.removeAllItems();
            jCBox_class.addItem("All Class");
            for(int j = 0; j < conf_GlobalParam.config_lib.classSeq.length; j++)
                jCBox_class.addItem(new String(conf_GlobalParam.config_lib.classSeq[j].descClass).trim());
            jCBox_class.addActionListener(event_jCBox_class);
            
            jCBox_Role.removeActionListener(event_jCBox_Role);
            jCBox_Role.removeAllItems();
            jCBox_Role.addItem("None");
            for(int i = 0; i < conf_GlobalParam.config_lib.roleSeq.length; i++)
                jCBox_Role.addItem((new String(conf_GlobalParam.config_lib.roleSeq[i].descRole)).trim());
            jCBox_Role.addActionListener(event_jCBox_Role);
            

            if(jCBox_Role.getSelectedIndex() > 0)
                read_LoginUser(conf_GlobalParam.config_lib.roleSeq[jCBox_Role.getSelectedIndex()-1].idRole);
            else
                read_LoginUser(-1);
            
            read_ProfileUser(-1,null);
            jB_commit_user.setEnabled(false);
        }
        else if(OperationTH == 3) // reload_config()
        {
            conf_GlobalParam.config_lib = conf_GlobalParam.CORBAConn.getConfigLib(); 
            System.out.println("4 prima debugPrint");
        debugPrint();
         System.out.println("4 dopo debugPrint");
        }
        else if(OperationTH == 4) //Init_PSWD()
        {            
            STRUCT_GESTIONE_PSWD = getFile_PSWD();
            getHosts();            
        }
        else if(OperationTH == 5) //commit_PSWD
        {    
            jB_commit_PSWD.setEnabled(false);
            putFile_PSWD(STRUCT_GESTIONE_PSWD);
            STRUCT_GESTIONE_PSWD = getFile_PSWD();
            getHosts();
        }
        
        System.out.println("8");
        jB_reload.setEnabled(true);
        jB_reloadAll_user.setEnabled(true);
        OperationTH = -1;
        
        jList_Class.setCursor(this.Cur_default);
        jList_Function_dis.setCursor(this.Cur_default);
        jList_Function_en.setCursor(this.Cur_default);
        jList_Profile.setCursor(this.Cur_default);
        jList_User.setCursor(this.Cur_default);
        jList_Profile_dis.setCursor(this.Cur_hand);
        jList_Profile_en.setCursor(this.Cur_hand);
        
        jTab_Access_Manager.setCursor(this.Cur_default);
        jTab_Access_Manager.setEnabled(true);
        this.setCursor(this.Cur_default);
        System.out.println("9");
    }
   
    /*private void setEnableGUI_interface(boolean enabled)
    {
        jCB_edit_profile.setSelected(enabled);
        jRB_ADD_P.setEnabled(enabled);
        jRB_DEL_P.setEnabled(enabled);
        jRB_MOD_P.setEnabled(enabled);
        
        jTF_nameProfile.setEnabled(enabled);
        jTA_descProfile.setEnabled(enabled);
        jB_Add.setEnabled(enabled);
        jB_AddAll.setEnabled(enabled);
        jB_Rem.setEnabled(enabled);
        jB_RemAll.setEnabled(enabled);
        
        jList_Class.setEnabled(enabled);
        jList_Profile.setEnabled(enabled);
        jList_Function_en.setEnabled(enabled);
        jList_Function_dis.setEnabled(enabled);

    }*/
    
    
    private void debugPrint()
    {
        System.out.println("---- S_ROLE ---> # "+conf_GlobalParam.config_lib.roleSeq.length);
        for(int i=0;i<conf_GlobalParam.config_lib.roleSeq.length; i++)
        {
            System.out.print(" idRole = "+conf_GlobalParam.config_lib.roleSeq[i].idRole);
            System.out.println(" descRole = "+new String(conf_GlobalParam.config_lib.roleSeq[i].descRole).trim());
        }
        
        System.out.println("---- S_CLASS ---> # "+conf_GlobalParam.config_lib.classSeq.length);
        for(int i=0;i<conf_GlobalParam.config_lib.classSeq.length; i++)
        {
            System.out.print(" idClass = "+conf_GlobalParam.config_lib.classSeq[i].idClass);
            System.out.println(" descClass = "+new String(conf_GlobalParam.config_lib.classSeq[i].descClass).trim());
        }
        
        System.out.println("---- S_FUNCTION ---> # "+conf_GlobalParam.config_lib.functionSeq.length);
        for(int i=0;i<conf_GlobalParam.config_lib.functionSeq.length; i++)
        {
            System.out.println(" idClass = "+conf_GlobalParam.config_lib.functionSeq[i].idClass);
            System.out.println(" idFunction = "+conf_GlobalParam.config_lib.functionSeq[i].idFunction); 
            System.out.println(" nameFunction = "+new String(conf_GlobalParam.config_lib.functionSeq[i].nameFunction).trim());
            System.out.println(" descFunction = "+new String(conf_GlobalParam.config_lib.functionSeq[i].descFunction).trim());
            System.out.println(" version = "+new String(conf_GlobalParam.config_lib.functionSeq[i].version).trim());
            System.out.println(" releasedData = "+new String(conf_GlobalParam.config_lib.functionSeq[i].releasedData).trim());
            System.out.println(" author = "+new String(conf_GlobalParam.config_lib.functionSeq[i].author).trim());
            System.out.println(" vendor = "+new String(conf_GlobalParam.config_lib.functionSeq[i].vendor).trim());
        }
                
        System.out.println("---- S_PROFILE ---> # "+conf_GlobalParam.config_lib.profileSeq.length);
        for(int i=0;i<conf_GlobalParam.config_lib.profileSeq.length; i++)
        {            
            System.out.println(" profile = "+new String(conf_GlobalParam.config_lib.profileSeq[i].profile).trim());
            System.out.println(" descProfile = "+new String(conf_GlobalParam.config_lib.profileSeq[i].descProfile).trim());
            System.out.println(" idClass = "+(conf_GlobalParam.config_lib.profileSeq[i].idClass));
                        
            for(int x=0; x<conf_GlobalParam.config_lib.profileSeq[i].functionSeq.length; x++)
            {
                System.out.println(" S_PROFILE idClass = "+conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].idClass);
                System.out.println(" S_PROFILE nameFunction = "+new String(conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].nameFunction).trim());
                System.out.println(" S_PROFILE descFunction = "+new String(conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].descFunction).trim());
                System.out.println(" S_PROFILE version = "+new String(conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].version).trim());
                System.out.println(" S_PROFILE releasedData = "+new String(conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].releasedData).trim());
                System.out.println(" S_PROFILE author = "+new String(conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].author).trim());
                System.out.println(" S_PROFILE vendor = "+new String(conf_GlobalParam.config_lib.profileSeq[i].functionSeq[x].vendor).trim());            
            }
            System.out.println(" ------------ ");
        }
        
        
    System.out.println("---- S_USER ---> # "+conf_GlobalParam.config_lib.userSeq.length);
        for(int i=0; i<conf_GlobalParam.config_lib.userSeq.length; i++)
        {
            System.out.println(" login = "+new String(conf_GlobalParam.config_lib.userSeq[i].login).trim());
            System.out.println(" password = "+new String(conf_GlobalParam.config_lib.userSeq[i].password).trim());
            System.out.println(" nomeUtente = "+new String(conf_GlobalParam.config_lib.userSeq[i].nomeUtente).trim());
            System.out.println(" descUser = "+new String(conf_GlobalParam.config_lib.userSeq[i].descUser).trim());
            System.out.println(" idRole = "+conf_GlobalParam.config_lib.userSeq[i].idRole);
            System.out.println(" abilitazioneUtente = "+conf_GlobalParam.config_lib.userSeq[i].abilitazioneUtente);
            
            System.out.println("---- S_USER - S_PROFILE ---> # "+conf_GlobalParam.config_lib.userSeq[i].profileSeq.length);
        }        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JTabbedPane jTab_Access_Manager;
    private javax.swing.JPanel jP_TotProfile;
    private javax.swing.JPanel jP_PROFILE;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollP_Profile;
    private javax.swing.JPanel jP_CLASS;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScroll_Class;
    private javax.swing.JPanel jP_FunctionDetail;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTF_nameFunction;
    private javax.swing.JTextField jTF_descFunction;
    private javax.swing.JTextField jTF_verFunction;
    private javax.swing.JTextField jTF_releaseFunction;
    private javax.swing.JTextField jTF_authorFunction;
    private javax.swing.JTextField jTF_vendorFunction;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScroll_Function_en;
    private javax.swing.JScrollPane jScroll_Function_dis;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jP_buttonFunctions;
    private javax.swing.JButton jB_Add;
    private javax.swing.JButton jB_Rem;
    private javax.swing.JButton jB_RemAll;
    private javax.swing.JButton jB_AddAll;
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jB_reload;
    private javax.swing.JButton jB_commit;
    private javax.swing.JPanel jP_bOper;
    private javax.swing.JCheckBox jCB_edit_profile;
    private javax.swing.JRadioButton jRB_ADD_P;
    private javax.swing.JRadioButton jRB_MOD_P;
    private javax.swing.JRadioButton jRB_DEL_P;
    private javax.swing.JPanel jP_operation;
    private javax.swing.JLabel jL_nameP;
    private javax.swing.JTextField jTF_nameProfile;
    private javax.swing.JLabel jL_descP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTA_descProfile;
    private javax.swing.JPanel jP_TotUser;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScroll_user;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JComboBox jCBox_Role;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JTextField jTF_login;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JTextField jTF_nomeUtente;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JTextField jTF_descUser;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JComboBox jCBox_UserRoleDetail;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jL_Enabled;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JComboBox jCBox_class;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScroll_ProfDis_2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JScrollPane jScroll_ProfEna_2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jB_reloadAll_user;
    private javax.swing.JButton jB_commit_user;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JCheckBox jCB_edit_user;
    private javax.swing.JRadioButton jRB_MOD_U;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jB_enabledPro;
    private javax.swing.JButton jB_disablePro;
    private javax.swing.JScrollPane jScrollP_descProf;
    private javax.swing.JTextArea jTArea_descProfile;
    private javax.swing.JPanel jP_PSWD;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JTextField jT_Login_PSWD;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JComboBox jCBox_TypePSWD;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jL_desc;
    private javax.swing.JPasswordField jPass;
    private javax.swing.JPasswordField jPass2;
    private javax.swing.JCheckBox jCheck_del_PSWD;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JTextField jTF_host_1;
    private javax.swing.JScrollPane jScroll_Hosts;
    private javax.swing.JLabel jL_nodes;
    private javax.swing.JPanel jP_addHosts;
    private javax.swing.JTextField jTF_NameNewHost;
    private javax.swing.JButton jB_addHost;
    private javax.swing.JLabel jL_newHost;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JButton jB_reload_PSWD;
    private javax.swing.JButton jB_commit_PSWD;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JCheckBox jCheck_edit_PSWD;
    private javax.swing.JButton jB_apply_PSWD;
    private javax.swing.JPanel jP_empty;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JFrame frameParent;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private conf_IconPool IcPool                            = null;
    
    private conf_JListIcon jList_User                       = null;
    private java.awt.event.MouseAdapter eventList_user      = null;

    private conf_JListIcon jList_Profile                    = null;
    private java.awt.event.MouseAdapter eventList_profile   = null;
    
    private conf_JListIcon jList_Class                      = null;
    private java.awt.event.MouseAdapter eventList_Class     = null;

    private conf_JListIcon jList_Function_en                = null;
    private java.awt.event.MouseAdapter eventList_function  = null;
    private Vector Vect_Function_Enabled                    = new Vector();
    
    private conf_JListIcon jList_Function_dis               = null;
    private Vector Vect_Function_Disable                    = new Vector();
    
    private conf_JListIcon jList_Profile_en                 = null;
    private Vector Vect_Profile_Enabled                     = new Vector();
    private Vector Vect_Profile_Enabled_otherClass          = new Vector();
    
    private java.awt.event.MouseAdapter eventList_Prof_en   = null;
    
    private conf_JListIcon jList_Profile_dis                = null;
    private Vector Vect_Profile_Disabled                    = new Vector();
    private java.awt.event.MouseAdapter eventList_Prof_dis  = null;
    
    private conf_JListIcon jList_Hosts                      = null;
    private java.awt.event.MouseAdapter eventList_Hosts     = null;
    
    private java.awt.event.ActionListener event_jCBox_Role              = null;
    private java.awt.event.ActionListener event_jCBox_class             = null;
    private javax.swing.event.ChangeListener event_jTab_Access_Manager = null;
    
    private Thread th        = null;
    private boolean TH_EXIT = false;
    private int OperationTH   = -1; 
    
    private String ID_STR_PROFILE   = "";
    private int ID_ClassSelected    = -1;
    private S_USER s_user           = null;
    
    private boolean stato_psMonitor = false;
    private String strNomeFile = "file_PSWD.dat";
    
    //private String strNomeFile = "file_PSWD_test.dat";
    private GESTIONE_PSWD[] STRUCT_GESTIONE_PSWD = null;
    private char symbolModify = '*';
    private String[] OGGETTO_PSWD = {"SFTP-SEND","SFTP-ACQ","ORACLE","TYPE1","TYPE2","TYPE3"};
      
    private Vector V_Hosts = new Vector();
    private Vector V_modify_IDListHosts = new Vector();
        // tab --> setIconAt(0,new javax.swing.ImageIcon(getClass().getResource("/images_conf/tab_selections.png")));
}