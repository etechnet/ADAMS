/*
 * ADAMS_JF_WIZARDBASE.java
 *
 * Created on 4 agosto 2005, 11.17
 */

/**
 *
 * @author Raffaele Ficcadenti raffaele.ficcadenti@e-tech.net
 */

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.table.*;
import javax.swing.JPanel;
import javax.swing.DefaultCellEditor;
import java.util.Vector;
import java.sql.*;
import net.etech.*;
import net.etech.MDM.*;

public class ADAMS_JF_WIZARDBASE extends javax.swing.JDialog {


    /******************************************************************************/
    /*    costanti da prendere dall'idl attualmente definite in ntmlimits.h       */

    private int                         _SCRIPT_MAX_VAR                         = ADAMS_GlobalParam._SCRIPT_MAX_VAR;
    private int                         _SCRIPT_MAX_TEXT                        = ADAMS_GlobalParam._SCRIPT_MAX_TEXT;
    private int                         _SHORT_DESC_LEN                         = mdm_server_server.eSHORT_DESC_LEN;
    private int                         _SCRIPT_VARNAME_LEN                     = mdm_server_server.eSCRIPT_VARNAME_LEN;
    private int                         _SCRIPT_TEXT_LEN                        = mdm_server_server.eSCRIPT_TEXT_LEN;


    /** Creates new form ADAMS_JF_WIZARDBASE */

    private boolean                                 DEBUG                       = true;
    private ADAMS_TAB_CONFIG                       Selected_row_TAB_CONFIG   = null;
    private ADAMS_JP_DataElement                  jpTe                        = null;
    private ADAMS_Vector_TAB_CONFIG                V_TAB_CONFIG              = new ADAMS_Vector_TAB_CONFIG();
    private int                                     stato_attuale               = -1;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private JPanel                                  listaPan[]                  = new JPanel[6];
    private int                                     STEP_INIZ                   = 0;
    private int                                     STEP_FINE                   = 5;
    public String                                   config_NAME_ALIAS           = "";

    public ADAMS_JP_Script_p1                        jP_Container_p1             = null;
    public ADAMS_JP_Script_p2                        jP_Container_p2             = null;
    public ADAMS_JP_Script_p3                        jP_Container_p3             = null;
    public ADAMS_JP_Script_p4                        jP_Container_p4             = null;
    public ADAMS_JP_Script_p5                        jP_Container_p5             = null;
    public ADAMS_JP_Script_p6                        jP_Container_p6             = null;
    private int                                     MAXNCAR                     = 159;
    public  int                                     ID_SCRIPT                   = -1;
    public boolean                                  flagCOMMIT                  = false;
    public int                                      flagREPORT                  = -1;
    public int                                      flagREPORT2                  = -1;
    public boolean                                  flagCOMMITREPORT            = false;
    public boolean                                  flagDELETE                  = false;

    private int Answercommit_Alarm  =-1;

    private ADAMS_TAB_ALARM_RELATION Local_AlarmRetation = null;

    // costruttore Per ALLARMI
    //public ADAMS_JF_WIZARDBASE(javax.swing.JDialog parent,int flag,String config_NAME_ALIAS,int idCounter,String Title,boolean modal,ADAMS_TAB_CONFIG te,ADAMS_Vector_TAB_CONFIG v_TAB_CONFIG)
    public ADAMS_JF_WIZARDBASE(javax.swing.JDialog parent,int flag,String config_NAME_ALIAS,int idCounter,String Title,boolean modal,ADAMS_TAB_ALARM_RELATION AlarmRetation ,ADAMS_Vector_TAB_CONFIG v_TAB_CONFIG)
    {
        // costruttore Per ALLARMI
        super(parent,true);
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        ADAMS_GlobalParam.JFRAME_SCRIPT=this;

        Local_AlarmRetation = AlarmRetation;

        ID_SCRIPT = Local_AlarmRetation.ID_CONDITION_SCRIPT;
        //System.out.println();
        //System.out.println("script ALLARMI ---- ID_SCRIPT="+ID_SCRIPT);
        //System.out.println();


        //Selected_row_TAB_CONFIG=te;
        Selected_row_TAB_CONFIG = null;

        this.jpTe = null;
        this.flagREPORT = ADAMS_GlobalParam.SCRIPT_ALARM;
        this.V_TAB_CONFIG= v_TAB_CONFIG;
        this.Answercommit_Alarm  =-1;

        /*if(Selected_row_TAB_CONFIG != null)
        {
            Vector listaScript = Selected_row_TAB_CONFIG.get_LISTA_SCRIPTS();

            if(true)
            {
                for(int i=0;i<listaScript.size();i++)
                {
                    ADAMS_TAB_SCRIPTS_LISTA appo=(ADAMS_TAB_SCRIPTS_LISTA)listaScript.elementAt(i);
                    System.out.println("[ADAMS_JF_WIZARDBASE]-Scripts("+i+") -> ID="+appo.get_SCRIPT()+"  TYPE="+appo.get_SCRIPT_TYPE());
                }
            }
        }*/

        initComponents();
        setWizardIcon("/images/WIZARD.png");
        setTitle("NTM Basic Wizard for: "+Title);
        setCursorWidget();
        setFont();

        setCenteredFrame(850,680);

        jP_Container_p1=new ADAMS_JP_Script_p1(this,Title);
        jP_Container_p2=new ADAMS_JP_Script_p2(flag,this);
        jP_Container_p3=new ADAMS_JP_Script_p3(config_NAME_ALIAS,idCounter,this,V_TAB_CONFIG);
        jP_Container_p4=new ADAMS_JP_Script_p4(this);
        jP_Container_p5=new ADAMS_JP_Script_p5(this);
        jP_Container_p6=new ADAMS_JP_Script_p6(flag,this);

       /* if(listaScript.size()>0)
        {
            jP_Container_p2.enableButtonDelete(true);
        }else
        {
            jP_Container_p2.enableButtonDelete(false);
        }*/
        jP_Container_p2.enableButtonDelete(false); // aggiunto Alarm

        if(this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM)
        {
            jP_Container_p2.setEnabledAlarm(true);
            jP_Container_p3.setEnabledAlarm(true);
        }

        listaPan[0]=(JPanel)jP_Container_p1;
        listaPan[1]=(JPanel)jP_Container_p2;
        listaPan[2]=(JPanel)jP_Container_p3;
        listaPan[3]=(JPanel)jP_Container_p4;
        listaPan[4]=(JPanel)jP_Container_p5;
        listaPan[5]=(JPanel)jP_Container_p6;

        stato_attuale=0;
        getContentPane().add(listaPan[stato_attuale],java.awt.BorderLayout.CENTER);
    }

    public ADAMS_JF_WIZARDBASE(javax.swing.JDialog parent,int flag,int flagREPORT,String config_NAME_ALIAS,int idCounter,String Title,boolean modal,ADAMS_TAB_CONFIG te,ADAMS_JP_DataElement jpTe) {
        super(parent,true);
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        ADAMS_GlobalParam.JFRAME_SCRIPT=this;
        Selected_row_TAB_CONFIG=te;
        this.jpTe=jpTe;
        this.flagREPORT=flagREPORT;
        //System.out.println("[ADAMS_JF_WIZARDBASE]-flagREPORT="+flagREPORT);
        if(jpTe!=null)
            V_TAB_CONFIG=jpTe.getV_TAB_CONFIG();


        Vector listaScript=Selected_row_TAB_CONFIG.get_LISTA_SCRIPTS();

        if(true)
        {
            for(int i=0;i<listaScript.size();i++)
            {
                ADAMS_TAB_SCRIPTS_LISTA appo=(ADAMS_TAB_SCRIPTS_LISTA)listaScript.elementAt(i);
                //System.out.println("[ADAMS_JF_WIZARDBASE]-Scripts("+i+") -> ID="+appo.get_SCRIPT()+"  TYPE="+appo.get_SCRIPT_TYPE());
            }
        }

        initComponents();
        setWizardIcon("/images/WIZARD.png");
        setTitle(Title);
        setCursorWidget();
        setFont();

        setCenteredFrame(850,680);


        jP_Container_p1=new ADAMS_JP_Script_p1(this,Selected_row_TAB_CONFIG.TAG + "  ["+Selected_row_TAB_CONFIG.DESCRIPTION+"]");
        jP_Container_p2=new ADAMS_JP_Script_p2(flag,this);
        jP_Container_p3=new ADAMS_JP_Script_p3(config_NAME_ALIAS,idCounter,this,V_TAB_CONFIG);
        jP_Container_p4=new ADAMS_JP_Script_p4(this);
        jP_Container_p5=new ADAMS_JP_Script_p5(this);
        jP_Container_p6=new ADAMS_JP_Script_p6(flag,this);

        if(listaScript.size()>0)
        {
            jP_Container_p2.enableButtonDelete(true);
        }else
        {
            jP_Container_p2.enableButtonDelete(false);
        }

        if(idCounter==-1)
        {
            if(this.flagREPORT==ADAMS_GlobalParam.SCRIPT_REPORT)
            {
                jP_Container_p2.setEnabledReport(true);
                jP_Container_p3.setEnabledReport(true);
            }else
            {
                jP_Container_p2.setEnabledScript(true);
                jP_Container_p3.setEnabledScript(true);
            }
        }else
        {
            jP_Container_p2.setEnabledReport(true);
            jP_Container_p3.setEnabledReport(true);
        }



        listaPan[0]=(JPanel)jP_Container_p1;
        listaPan[1]=(JPanel)jP_Container_p2;
        listaPan[2]=(JPanel)jP_Container_p3;
        listaPan[3]=(JPanel)jP_Container_p4;
        listaPan[4]=(JPanel)jP_Container_p5;
        listaPan[5]=(JPanel)jP_Container_p6;

        stato_attuale=0;
        getContentPane().add(listaPan[stato_attuale],java.awt.BorderLayout.CENTER);
    }


    public ADAMS_JF_WIZARDBASE(javax.swing.JFrame parent,int flag,int flagREPORT,String config_NAME_ALIAS,int idCounter,String Title,boolean modal,ADAMS_TAB_CONFIG te,ADAMS_JP_DataElement jpTe) {
        super((java.awt.Frame)parent,true);
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        ADAMS_GlobalParam.JFRAME_SCRIPT=this;
        Selected_row_TAB_CONFIG=te;
        this.jpTe=jpTe;
        this.flagREPORT=flagREPORT;
        //System.out.println("[ADAMS_JF_WIZARDBASE]-flagREPORT="+this.flagREPORT);
        if(jpTe!=null)
            V_TAB_CONFIG=jpTe.getV_TAB_CONFIG();


        Vector listaScript=Selected_row_TAB_CONFIG.get_LISTA_SCRIPTS();

        if(DEBUG)
        {
            for(int i=0;i<listaScript.size();i++)
            {
                ADAMS_TAB_SCRIPTS_LISTA appo=(ADAMS_TAB_SCRIPTS_LISTA)listaScript.elementAt(i);
                System.out.println("[ADAMS_JF_WIZARDBASE]-Scripts("+i+") -> ID="+appo.get_SCRIPT()+"  TYPE="+appo.get_SCRIPT_TYPE());
            }
        }

        initComponents();
        setWizardIcon("/images/WIZARD.png");
        setTitle(Title);
        setCursorWidget();
        setFont();

        setCenteredFrame(850,680);


        jP_Container_p1=new ADAMS_JP_Script_p1(this,Selected_row_TAB_CONFIG.TAG + "  ["+Selected_row_TAB_CONFIG.DESCRIPTION+"]");
        jP_Container_p2=new ADAMS_JP_Script_p2(flag,this);
        jP_Container_p3=new ADAMS_JP_Script_p3(config_NAME_ALIAS,idCounter,this,V_TAB_CONFIG);
        jP_Container_p4=new ADAMS_JP_Script_p4(this);
        jP_Container_p5=new ADAMS_JP_Script_p5(this);
        jP_Container_p6=new ADAMS_JP_Script_p6(flag,this);

        if(listaScript.size()>0)
        {
            jP_Container_p2.enableButtonDelete(true);
        }else
        {
            jP_Container_p2.enableButtonDelete(false);
        }

        if(idCounter==-1)
        {
            jP_Container_p2.setEnabledScript(true);
            jP_Container_p3.setEnabledScript(true);
        }else
        {
            jP_Container_p2.setEnabledReport(true);
            jP_Container_p3.setEnabledReport(true);
        }

        listaPan[0]=(JPanel)jP_Container_p1;
        listaPan[1]=(JPanel)jP_Container_p2;
        listaPan[2]=(JPanel)jP_Container_p3;
        listaPan[3]=(JPanel)jP_Container_p4;
        listaPan[4]=(JPanel)jP_Container_p5;
        listaPan[5]=(JPanel)jP_Container_p6;

        stato_attuale=0;
        getContentPane().add(listaPan[stato_attuale],java.awt.BorderLayout.CENTER);
    }

    public boolean readScript()
    {
        ScriptObject scriptObject = new ScriptObject();
        //jP_Container_p2.getTAG()
        boolean flagSCRIPT=false;
        int s_type=jP_Container_p2.getID_TAG();

        Vector appo;

        if(Selected_row_TAB_CONFIG != null)
            appo=Selected_row_TAB_CONFIG.V_LISTA_SCRIPTS;
        else
        {

            if(this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM)
            {
                if(ID_SCRIPT != -1)
                {
                    flagSCRIPT = true;

                    boolean bRet=scriptObject.readScript(config_NAME_ALIAS,ID_SCRIPT);
                    jP_Container_p3.setInterface(scriptObject);
                    jP_Container_p4.setInterface(scriptObject);
                    jP_Container_p5.setInterface(scriptObject);
                }
            }

            //System.out.println("readScript allarme esistente  "+flagSCRIPT);
            return flagSCRIPT;
        }

        for(int i=0;i<appo.size();i++)
        {
            ADAMS_TAB_SCRIPTS_LISTA elem=(ADAMS_TAB_SCRIPTS_LISTA)appo.elementAt(i);
            ID_SCRIPT=elem.get_SCRIPT();
            //System.out.println("Script ID="+ID_SCRIPT+" TYPE="+elem.get_SCRIPT_TYPE());
        }

        if((appo.size()>0)&&(flagREPORT==ADAMS_GlobalParam.SCRIPT_REPORT))
        {
            flagSCRIPT=true;
        }else
        {
            for(int i=0;i<appo.size();i++)
            {
                ADAMS_TAB_SCRIPTS_LISTA elem=(ADAMS_TAB_SCRIPTS_LISTA)appo.elementAt(i);
                if(elem.get_SCRIPT_TYPE()==s_type)
                {
                    flagSCRIPT=true;
                    ID_SCRIPT=elem.get_SCRIPT();
                    break;
                }
            }
        }

        if(DEBUG)
        {
            System.out.println("[readScript] Script_type="+s_type);
            System.out.println("[readScript] Script_id="+ID_SCRIPT);
        }

        if(flagSCRIPT==false)
        {
            ADAMS_GlobalParam.optionPanel(this,"INFO: Sript"+jP_Container_p2.getTAG()+" not present.","INFO",3);
            flagREPORT2=1;
            ID_SCRIPT=-1;
        }
        else
        {
            boolean bRet=scriptObject.readScript(config_NAME_ALIAS,ID_SCRIPT);
            jP_Container_p3.setInterface(scriptObject);
            jP_Container_p4.setInterface(scriptObject);
            jP_Container_p5.setInterface(scriptObject);
            flagREPORT2=0;
        }


        return flagSCRIPT;
    }

    public void setFont()
    {

    }

    public void setCursorWidget()
    {
        jB_commit.setCursor(Cur_hand);
        jB_back.setCursor(Cur_hand);
        jB_next.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_WizardButtons = new javax.swing.JPanel();
        jB_commit = new javax.swing.JButton();
        jB_back = new javax.swing.JButton();
        jB_next = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        jP_WizardIcon = new javax.swing.JPanel();
        jL_WizardIcon = new javax.swing.JLabel();
        jP_WizardHeader = new javax.swing.JPanel();
        jL_Header = new javax.swing.JLabel();
        jL_Filler = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jP_WizardButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jP_WizardButtons.setBackground(new java.awt.Color(230, 230, 230));
        jP_WizardButtons.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_WizardButtons.setAlignmentX(0.0F);
        jP_WizardButtons.setAlignmentY(0.0F);
        jP_WizardButtons.setMinimumSize(new java.awt.Dimension(10, 90));
        jP_WizardButtons.setName("[10, 30]");
        jP_WizardButtons.setPreferredSize(new java.awt.Dimension(10, 50));
        jB_commit.setBackground(new java.awt.Color(230, 230, 230));
        jB_commit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_applyv.jpg")));
        jB_commit.setBorderPainted(false);
        jB_commit.setContentAreaFilled(false);
        jB_commit.setFocusPainted(false);
        jB_commit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_commit.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_commit.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_commit.setPreferredSize(new java.awt.Dimension(80, 30));
        jB_commit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_applyv_press.jpg")));
        jB_commit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_applyv_over.jpg")));
        jB_commit.setEnabled(false);
        jB_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_commitActionPerformed(evt);
            }
        });

        jP_WizardButtons.add(jB_commit);

        jB_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back.jpg")));
        jB_back.setBorderPainted(false);
        jB_back.setContentAreaFilled(false);
        jB_back.setFocusPainted(false);
        jB_back.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_back.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back_press.jpg")));
        jB_back.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back_over.jpg")));
        jB_back.setEnabled(false);
        jB_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_backActionPerformed(evt);
            }
        });

        jP_WizardButtons.add(jB_back);

        jB_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_next.jpg")));
        jB_next.setBorderPainted(false);
        jB_next.setContentAreaFilled(false);
        jB_next.setFocusPainted(false);
        jB_next.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_next.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_next_press.jpg")));
        jB_next.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_next_over.jpg")));
        jB_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_nextActionPerformed(evt);
            }
        });

        jP_WizardButtons.add(jB_next);

        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
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

        jP_WizardButtons.add(jB_close);

        getContentPane().add(jP_WizardButtons, java.awt.BorderLayout.SOUTH);

        jP_WizardIcon.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jP_WizardIcon.setBackground(new java.awt.Color(230, 230, 230));
        jP_WizardIcon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_WizardIcon.setMaximumSize(new java.awt.Dimension(214, 530));
        jP_WizardIcon.setMinimumSize(new java.awt.Dimension(214, 530));
        jP_WizardIcon.setPreferredSize(new java.awt.Dimension(214, 530));
        jP_WizardIcon.add(jL_WizardIcon);

        getContentPane().add(jP_WizardIcon, java.awt.BorderLayout.WEST);

        jP_WizardHeader.setLayout(new java.awt.BorderLayout());

        jP_WizardHeader.setBackground(new java.awt.Color(230, 230, 230));
        jP_WizardHeader.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_WizardHeader.setPreferredSize(new java.awt.Dimension(10, 30));
        jL_Header.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_Header.setText("NTM WIZARD");
        jP_WizardHeader.add(jL_Header, java.awt.BorderLayout.CENTER);

        jL_Filler.setMaximumSize(new java.awt.Dimension(50, 15));
        jL_Filler.setMinimumSize(new java.awt.Dimension(50, 15));
        jL_Filler.setPreferredSize(new java.awt.Dimension(50, 15));
        jP_WizardHeader.add(jL_Filler, java.awt.BorderLayout.NORTH);

        getContentPane().add(jP_WizardHeader, java.awt.BorderLayout.NORTH);

        pack();
    }//GEN-END:initComponents

    private void jB_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_backActionPerformed
        backActionPerformed(evt);
    }//GEN-LAST:event_jB_backActionPerformed

    private void jB_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_nextActionPerformed
        nextActionPerformed(evt);
    }//GEN-LAST:event_jB_nextActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        closeActionPerformed(evt);
    }//GEN-LAST:event_jB_closeActionPerformed

    private void jB_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_commitActionPerformed
        commitActionPerformed(evt);
    }//GEN-LAST:event_jB_commitActionPerformed

    private void refreshPanel()
    {
        switch(stato_attuale)
        {
            case 0:
                jP_Container_p1.refreshPanel();
            break;
            case 1:
                jP_Container_p2.refreshPanel();
            break;
            case 2:
                jP_Container_p3.refreshPanel();
            break;
            case 3:
                jP_Container_p4.refreshPanel();
            break;
            case 4:
                jP_Container_p5.refreshPanel();
            break;
            case 5:
                jP_Container_p6.refreshPanel();
            break;
        }
    }

    protected void backActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        if(stato_attuale>STEP_INIZ)
        {
            commitButtonSetEnabled(false);
            getContentPane().remove(listaPan[stato_attuale]);
            stato_attuale=stato_attuale-1;
            getContentPane().add(listaPan[stato_attuale],java.awt.BorderLayout.CENTER);
            validate();
            repaint();

            jB_next.setEnabled(true);
            if(stato_attuale==STEP_INIZ)
            {
                jB_back.setEnabled(false);
            }
            /*if(stato_attuale==STEP_FINE)
            {
                jB_commit.setEnabled(true);
            }else
            {
                jB_commit.setEnabled(false);
            }*/

        }else
        {
            jB_back.setEnabled(false);
        }

        refreshPanel();
    }

    protected void nextActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        if(stato_attuale<STEP_FINE)
        {
            commitButtonSetEnabled(false);
            getContentPane().remove(listaPan[stato_attuale]);
            stato_attuale=stato_attuale+1;
            getContentPane().add(listaPan[stato_attuale],java.awt.BorderLayout.CENTER);

            validate();
            repaint();
            jB_back.setEnabled(true);
            if(stato_attuale==STEP_FINE)
            {
                jB_next.setEnabled(false);
                jP_Container_p6.setTextVerify("");
                //jB_commit.setEnabled(true);
            }
        }else
        {
            jB_next.setEnabled(false);
        }

        refreshPanel();
    }

    protected void closeActionPerformed(java.awt.event.ActionEvent evt) {
        flagCOMMITREPORT=false;

        if(this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM)
            closeFrame_Alarm();
        else
            closeFrame();
        // Add your handling code here:
    }

    public boolean deleteScripts()
    {
        boolean cancellato=false;

        int s_type=jP_Container_p2.getID_TAG();

        if(Selected_row_TAB_CONFIG != null)
        {
            cancellato=Selected_row_TAB_CONFIG.delete_LISTA_SCRIPTS(ID_SCRIPT,s_type);

            //System.out.println("cancellato="+cancellato);
            if(cancellato==true)
            {
                Selected_row_TAB_CONFIG.update_LISTA_SCRIPTS();
            }
        }
        else
            cancellato=true;

        String strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+ID_SCRIPT;
        //System.out.println("strSelect="+strSelect);
        int iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);

        if((iRet > 0) && (this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM))
        {
             String str_update_AL = "update tab_alarm_relation set ID_CONDITION_SCRIPT=-1 "+
                                    "where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and "+
                                    "ID_CONDITION_SCRIPT="+ID_SCRIPT;

             iRet = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(str_update_AL);
             ID_SCRIPT = -1;
        }

        return cancellato;
        //cancellare anche la nested Associata.
    }

    public boolean verifyScript()
    {
        boolean flagVERIFYSCRIPT=false;
        CORBAConnection CORBA = new CORBAConnection(ADAMSConf.machine,ADAMSConf.port);
        CORBA.setJApplet(ADAMSConf.local_applet);

        boolean connOK_mdm_server_server = CORBA.openConnection_mdm_server_server();


        if(connOK_mdm_server_server)
        {
            System.out.println("Connection mdm_server_server... ok.");
            SCRIPT_VALIDATE scriptData=new SCRIPT_VALIDATE();

            /******************************************** SISTEMO LE VARIABILI **********************************/
            Vector vVARIABLES1=jP_Container_p3.getV1();
            Vector vVARIABLES2=jP_Container_p3.getV3();
            int len1=vVARIABLES1.size();
            int len2=vVARIABLES2.size();


            Vector strV=new Vector();

            for(int i=0;i<len1;i++)
            {
                String var1 = ((ADAMS_JP_Script_p3.vectorTab)vVARIABLES1.elementAt(i)).st1;
                strV.addElement(var1);
                //System.out.println("var1="+var1);
            }
            for(int i=0;i<len2;i++)
            {
                String var2 = ((ADAMS_JP_Script_p3.vectorTab)vVARIABLES2.elementAt(i)).st1;
                strV.addElement(var2);
                //System.out.println("var2="+var2);
            }
            //strV.addElement(jP_Container_p4.getResult());

            int len=strV.size();
            scriptData.variables=new char[_SCRIPT_MAX_VAR][_SCRIPT_VARNAME_LEN];
            if(len>0)
            {
                    for(int i=0;i<_SCRIPT_MAX_VAR;i++)
                    {
                        if(i<len)
                        {
                            String str=(String)strV.elementAt(i);
                            scriptData.variables[i]=set_String_toChar(str,_SCRIPT_VARNAME_LEN);
                            if(DEBUG)
                            {
                                System.out.println("VAR="+str);
                            }
                        }else
                        {
                            scriptData.variables[i]=set_String_toChar("",_SCRIPT_VARNAME_LEN);
                        }
                    }
            }else
            {
                for(int i=0;i<_SCRIPT_MAX_VAR;i++)
                {
                    scriptData.variables[i]=set_String_toChar("",_SCRIPT_VARNAME_LEN);
                }
            }

            /******************************************** SISTEMO IL TESTO DELLE VARIABILI **********************************/
            String strVAR=jP_Container_p6.getTextVar();
            scriptData.variablesText=new char[_SCRIPT_MAX_TEXT * _SCRIPT_TEXT_LEN];
            if(DEBUG)
            {
                System.out.println("STR_VAR="+strVAR);
            }
            if(strVAR.length()>0)
            {
                scriptData.variablesText=set_String_toChar(strVAR,_SCRIPT_MAX_TEXT * _SCRIPT_TEXT_LEN);
            }else
            {
                scriptData.variablesText=set_String_toChar("",_SCRIPT_MAX_TEXT * _SCRIPT_TEXT_LEN);
            }

            /******************************************** SISTEMO IL TESTO DELLO SCRIPT **********************************/
            String strSCRIPT=jP_Container_p5.getTextScript();
            scriptData.scriptText=new char[_SCRIPT_MAX_TEXT * _SCRIPT_TEXT_LEN];
            if(DEBUG)
            {
                System.out.println("STR_SCRIPT="+strSCRIPT);
            }
            if(strSCRIPT.length()>0)
            {
                scriptData.scriptText=set_String_toChar(strSCRIPT,_SCRIPT_MAX_TEXT * _SCRIPT_TEXT_LEN);
            }else
            {
                scriptData.scriptText=set_String_toChar("",_SCRIPT_MAX_TEXT * _SCRIPT_TEXT_LEN);
            }

            /******************************************** SISTEMO LA RESUL VARIABLE NAME **********************************/
            String strRESULT=jP_Container_p4.getResult();
            scriptData.resultVariableName=new char[_SCRIPT_VARNAME_LEN];
            if(DEBUG)
            {
                System.out.println("STR_RESULT="+strRESULT);
            }
            if(strRESULT.length()>0)
            {
                scriptData.resultVariableName=set_String_toChar(strRESULT,_SCRIPT_VARNAME_LEN);
            }else
            {
                scriptData.resultVariableName=set_String_toChar("",_SCRIPT_VARNAME_LEN);
            }

            /******************************************** SISTEMO IL RESUL TYPE **********************************/
            int resultType=jP_Container_p4.getIDType();

            scriptData.resultType=resultType;
            if(DEBUG)
            {
                System.out.println("RESULT_TYPE="+resultType);
            }

            /******************************************** CONTROLLO LO SCRIPT **********************************/
            ERROR_TEXTHolder errorText=new ERROR_TEXTHolder();
            boolean bRet=CORBA.verifyScript(scriptData,errorText);


            String strERRORE=(new String(errorText.value.text).trim());
            //System.out.println("strERRORE="+strERRORE);
            jP_Container_p6.setTextVerify(strERRORE);

            if(bRet==true)
            {
                flagVERIFYSCRIPT=true;
            }else
            {
                flagVERIFYSCRIPT=false;
                warningProblem(strERRORE);
            }
        }
        else
        {
            System.out.println(" - Error Connection CORBA.");
            flagVERIFYSCRIPT=false;
        }

        return flagVERIFYSCRIPT;
    }

    public int getID_SCRIPT()
    {
        return ID_SCRIPT;
    }

    public int getAnswercommitSCRIPT_Alarm()
    {
        return Answercommit_Alarm;
    }

    public void commit_Alarm_NoDB_COMMIT()
    {
       //System.out.println("commit_Alarm_NoDB_COMMIT()");

        if(ID_SCRIPT == -1)
        {
            String strSelect_seq = "select IDSCRIPT_seq.nextVal from dual";
            ID_SCRIPT = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect_seq);

            if(ID_SCRIPT == -1)
            {
                System.out.println("err. IDSCRIPT_seq.nextVal");
                return;
             }
        }

        Vector vVARIABLES1=jP_Container_p3.getV1();
        Vector vVARIABLES2=jP_Container_p3.getV3();

        String str=(jP_Container_p5.getTextScript()).trim();
        Vector strV=creaRighe(str);
        int numRighe=strV.size();

        String strSelect="insert intotab_scripts (TIPO_DI_CONFIGURAZIONE,IDSCRIPT,TAG,NUMVARIABLE1,NUMVARIABLE2,NUMSCRIPTTEXT,RESULTVARNAME,RESULTTYPE,FLAG_VALIDATE) values (";
        strSelect+="'"+config_NAME_ALIAS+"',";
        strSelect+=""+ID_SCRIPT+",";
        strSelect+="'"+jP_Container_p2.getTAG()+"',";
        strSelect+=""+vVARIABLES1.size()+",";
        strSelect+=""+vVARIABLES2.size()+",";
        strSelect+=""+numRighe+",";
        strSelect+="'"+jP_Container_p4.getResult()+"',";
        strSelect+=""+jP_Container_p4.getIDType()+",";
        strSelect+=""+flagREPORT2+"";
        strSelect+=")";


        //System.out.println("commit_Alarm_NoDB_COMMIT strSelect "+strSelect);

        try
        {
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
        }catch(java.sql.SQLException exc){}


        Answercommit_Alarm = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);

        if((vVARIABLES1.size()>0)&&(Answercommit_Alarm >= 0))
        {
            if(DEBUG)
                System.out.println("tab_script_var1-UPDATE()");
            Answercommit_Alarm = update_VAR_SCRIPTS("tab_script_var1");
            //System.out.println("4 Answercommit_Alarm= "+Answercommit_Alarm);
        }

        if((vVARIABLES2.size()>0)&&(Answercommit_Alarm >= 0))
        {
            if(DEBUG)
                System.out.println("tab_script_var2-UPDATE()");
            Answercommit_Alarm = update_VAR_SCRIPTS("tab_script_var2");
            //System.out.println("5 Answercommit_Alarm= "+Answercommit_Alarm);
        }

        if((numRighe>0)&&(Answercommit_Alarm >= 0))
        {
            //update_STR_SCRIPTS(numRighe,str);
            Answercommit_Alarm = update_STR_SCRIPTS(strV);
            //System.out.println("6 Answercommit_Alarm= "+Answercommit_Alarm);
        }

        //System.out.println("commit_Alarm_NoDB_COMMIT --> "+Answercommit_Alarm);

        if(Answercommit_Alarm >= 0)
        {
            flagCOMMIT = true;
            closeFrame_Alarm();
        }
    }

   public void commit()
    {
        boolean inserito=false;
        String strSelect="";
        int s_type=jP_Container_p2.getID_TAG();

        //System.out.println("ID_SCRIPT_prima= "+ID_SCRIPT);
        if(ID_SCRIPT==-1)
        {
            strSelect="select IDSCRIPT_seq.nextVal from dual";
            ID_SCRIPT = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
            //System.out.println("ID_SCRIPT_dopo= "+ID_SCRIPT);
            //System.out.println("s_type= "+s_type);
            inserito=Selected_row_TAB_CONFIG.add_LISTA_SCRIPTS(ID_SCRIPT,s_type); //lo inserisce solo se non esiste

            if(ID_SCRIPT == -1)
                return;
        }

        //System.out.println("inserito= "+inserito);

        try
        {
            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
            //System.out.println("setAutoCommit(false)");
        }catch(java.sql.SQLException exc){}

        int Answercommit = -1;

        if((inserito==true)&&(flagREPORT==ADAMS_GlobalParam.SCRIPT_TE))
        {
            Answercommit = Selected_row_TAB_CONFIG.update_LISTA_SCRIPTS();
            //System.out.println("1 Answercommit= "+Answercommit);
        }else
        {
            Answercommit=0;
        }

        Vector vVARIABLES1=jP_Container_p3.getV1();
        Vector vVARIABLES2=jP_Container_p3.getV3();

        String str=(jP_Container_p5.getTextScript()).trim();
        Vector strV=creaRighe(str);

        //int numRighe=str.length()/MAXNCAR;
        //int resto=str.length()%MAXNCAR;

        //if(resto>0)
        //{
        //    numRighe++;
        //}

        int numRighe=strV.size();


        if((inserito==false)&&(Answercommit >= 0))
        {
            strSelect="delete from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDSCRIPT="+ID_SCRIPT;
            Answercommit = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
            //System.out.println("2 Answercommit= "+Answercommit);
        }

        if(DEBUG)
        {
               System.out.println("delete(SCRIPT)= "+strSelect);

        }
        if(flagREPORT==ADAMS_GlobalParam.SCRIPT_TE)
            flagREPORT2=0;

        strSelect="insert intotab_scripts (TIPO_DI_CONFIGURAZIONE,IDSCRIPT,TAG,NUMVARIABLE1,NUMVARIABLE2,NUMSCRIPTTEXT,RESULTVARNAME,RESULTTYPE,FLAG_VALIDATE) values (";
        strSelect+="'"+config_NAME_ALIAS+"',";
        strSelect+=""+ID_SCRIPT+",";
        strSelect+="'"+jP_Container_p2.getTAG()+"',";
        strSelect+=""+vVARIABLES1.size()+",";
        strSelect+=""+vVARIABLES2.size()+",";
        strSelect+=""+numRighe+",";
        strSelect+="'"+jP_Container_p4.getResult()+"',";
        strSelect+=""+jP_Container_p4.getIDType()+",";
        strSelect+=""+flagREPORT2+"";
        strSelect+=")";

        if(Answercommit >= 0)
        {
            Answercommit = ADAMS_GlobalParam.connect_Oracle.Query_rs_update(strSelect);
            //System.out.println("3 Answercommit= "+Answercommit);
        }
        if(DEBUG)
        {
               System.out.println("insert(SCRIPT)= "+strSelect);
        }


        if((vVARIABLES1.size()>0)&&(Answercommit >= 0))
        {
            if(DEBUG)
                System.out.println("tab_script_var1-UPDATE()");
            Answercommit = update_VAR_SCRIPTS("tab_script_var1");
            //System.out.println("4 Answercommit= "+Answercommit);
        }

        if((vVARIABLES2.size()>0)&&(Answercommit >= 0))
        {
            if(DEBUG)
                System.out.println("tab_script_var2-UPDATE()");
            Answercommit = update_VAR_SCRIPTS("tab_script_var2");
            //System.out.println("5 Answercommit= "+Answercommit);
        }

        if((numRighe>0)&&(Answercommit >= 0))
        {
            //update_STR_SCRIPTS(numRighe,str);
            Answercommit = update_STR_SCRIPTS(strV);
            //System.out.println("6 Answercommit= "+Answercommit);
        }



        try
        {
            if(Answercommit >= 0)
            {
                ADAMS_GlobalParam.setLAST_MODIFICATION_TIME(config_NAME_ALIAS);
                ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);

                //System.out.println("Commit OK");

                if(flagREPORT==ADAMS_GlobalParam.SCRIPT_TE)
                {
                    ADAMS_GlobalParam.optionPanel(this,"INFO: Script text for "+jP_Container_p2.getTAG()+" ID:"+ID_SCRIPT+" saved correctly.","INFO",3);
                }
                flagCOMMIT=true;

                closeFrame();
            }
            else
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                //System.out.println("rollback()");
            }

            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
        }
        catch(java.sql.SQLException exc)
        {
            System.out.println("Errore ADAMS_JF_WIZARDBASE.java 1");
            try
            {
                ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
            }
             catch(java.sql.SQLException exc1){System.out.println("Errore ADAMS_JF_WIZARDBASE.java 1A");}
        }

    }

    protected void commitActionPerformed(java.awt.event.ActionEvent evt) {

        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
        {
            return;
        }
        // Add your handling code here:
        //fare la COMMIT:

        if(this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM)
            commit_Alarm_NoDB_COMMIT();
        else
            commit();

        /*if(flagREPORT==ADAMS_GlobalParam.SCRIPT_TE)
        {
            commit();
        }else
        {
            flagCOMMIT=true;
            if(ID_SCRIPT==-1)//stò in creazione
            {
                String strSelect="select IDSCRIPT_seq.nextVal from dual";
                ID_SCRIPT = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelect);
            }
            ADAMS_GlobalParam.optionPanel("INFO: La COMMIT verrà effettuata al COMMIT del report.","INFO",3);
            hide();
        }*/
    }

    public int update_STR_SCRIPTS(Vector strV) // Nested Campo STRING
    {

        int p1			= 0;
        int p2			= MAXNCAR;
        int rowCount		= strV.size();
        String tableConfig      = "tab_script_text";
        String strSelect	= "";
	int iRet		= 0;
        String strDelete	= "";

        { // DELETE before INSERT (TAB_SCRIPT_VAR1/2)
		strDelete="delete from "+tableConfig+" where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"'"+" and IDSCRIPT="+ID_SCRIPT;
	        try
	        {
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_STR_SCRIPTS(0): "+exc);
	        }

	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete);

	       	try
	        {
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("update_STR_SCRIPTS::rollback()(1)");
			}
	        }
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_STR_SCRIPTS::rollback()(2): "+exc);
	        }

	       	try
	        {
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_STR_SCRIPTS(3): "+exc);
	        }
	}

        if(rowCount>0)
        {
		strSelect="INSERT ALL ";
	        for(int i=0;i<strV.size();i++)
	        {
			strSelect=strSelect+" into "+ tableConfig + "(TIPO_DI_CONFIGURAZIONE,IDSCRIPT,VRBTEXT) values ";
			String var1 = (String)strV.elementAt(i);
			strSelect=strSelect+" ('"+config_NAME_ALIAS+"',"+ID_SCRIPT+",'"+var1+"') ";
	        }
	        strSelect=strSelect+" SELECT * from DUAL";

	        iRet = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect);

	        if(DEBUG)
	        {
	            System.out.println("update_STR_SCRIPTS ="+strSelect);
	            System.out.println("iRet ="+iRet);
	        }
	}else
	{
		System.out.println("la strV(TAB_SCRIPT_TEXT) è vuota!!!");
	}
        return iRet;
    }



    public int update_VAR_SCRIPTS(String link) // Nested Campo STRING
    {
        Vector vVARIABLES1=jP_Container_p3.getV1();
        Vector vVARIABLES2=jP_Container_p3.getV3();

        int size_1		= vVARIABLES1.size();
        int size_2		= vVARIABLES2.size();
        int rowCount 		= 0;
        String linkTable	= link;
        String strSelect	= "";
        int iRet		= 1;
        String strDelete	= "";

        { // DELETE before INSERT (TAB_SCRIPT_VAR1/2)
		strDelete="delete from "+linkTable+" where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"'"+" and IDSCRIPT="+ID_SCRIPT;
	        try
	        {
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(false);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_VAR_SCRIPTS(0): "+exc);
	        }

	        int Answer_del = ADAMS_GlobalParam.connect_Oracle.Operation(strDelete);

	       	try
	        {
		       	if(Answer_del >= 0)
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.commit();
			}
			else
			{
				ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
				System.out.println("update_VAR_SCRIPTS::rollback()(1)");
			}
	        }
	        catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_VAR_SCRIPTS::rollback()(2): "+exc);
	        }

	       	try
	        {
	            ADAMS_GlobalParam.connect_Oracle.DBConnection.setAutoCommit(true);
	        }catch(java.sql.SQLException exc)
	        {
	        	System.out.println("update_VAR_SCRIPTS(3): "+exc);
	        }
	}

	if(link.equals("tab_script_var1"))
	{
		rowCount=size_1;
	}
	else if(link.equals("tab_script_var2"))
	{
		rowCount=size_2;
	}

        if(rowCount>0)
	{
		strSelect="INSERT ALL ";

	        if(link.equals("tab_script_var1"))
	        {
	            for(int i=0;i<size_1;i++)
	            {
	            	strSelect=strSelect+" into "+ link + "(TIPO_DI_CONFIGURAZIONE,IDSCRIPT,VRBNAME) values ";
	                String var1 = ((ADAMS_JP_Script_p3.vectorTab)vVARIABLES1.elementAt(i)).st1;
			strSelect=strSelect+" ('"+config_NAME_ALIAS+"',"+ID_SCRIPT+",'"+var1+"') ";
	            }
	        }


	        if(link.equals("tab_script_var2"))
	        {
	            for(int i=0;i<size_2;i++)
	            {
	            	strSelect=strSelect+" into "+ link + "(TIPO_DI_CONFIGURAZIONE,IDSCRIPT,VRBNAME) values ";
	                String var2 = ((ADAMS_JP_Script_p3.vectorTab)vVARIABLES2.elementAt(i)).st1;
			strSelect=strSelect+" ('"+config_NAME_ALIAS+"',"+ID_SCRIPT+",'"+var2+"') ";
	            }
	        }

	        strSelect=strSelect+" SELECT * from DUAL";

	        if(DEBUG)
	        {
	            System.out.println("update_VAR_SCRIPTS ="+strSelect);
	        }

	        iRet = ADAMS_GlobalParam.connect_Oracle.Operation(strSelect);

	        if(DEBUG)
	        {
	            System.out.println("iRet ="+iRet);
	        }
     	}
     	else
	{

		if(link.equals("tab_script_var1"))
		{
			System.out.println("la vVARIABLES1 è vuota!!!");
		}
		else if(link.equals("tab_script_var2"))
		{
			System.out.println("la vVARIABLES2 è vuota!!!");
		}
	}

        return iRet;
    }

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm

        if(this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM)
            closeFrame_Alarm();
        else
            closeFrame();
    }//GEN-LAST:event_exitForm

    protected void commitButtonSetEnabled(boolean mode)
    {
        jB_commit.setEnabled(mode);
    }

    protected void backButtonSetEnabled(boolean mode)
    {
        jB_back.setEnabled(mode);
    }

    protected void nextButtonSetEnabled(boolean mode)
    {
        jB_next.setEnabled(mode);
    }

    protected void closeButtonSetEnabled(boolean mode)
    {
        jB_close.setEnabled(mode);
    }

    protected void setCentralWidget(java.awt.Component w)
    {
        //jP_Container.add(w);
    }

    public void setHeader(String title)
    {
        jL_Header.setText(title);
    }

    public void setWizardIcon(String iconName)
    {
        jL_WizardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconName)));
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_WizardButtons;
    private javax.swing.JButton jB_commit;
    private javax.swing.JButton jB_back;
    private javax.swing.JButton jB_next;
    private javax.swing.JButton jB_close;
    private javax.swing.JPanel jP_WizardIcon;
    private javax.swing.JLabel jL_WizardIcon;
    private javax.swing.JPanel jP_WizardHeader;
    private javax.swing.JLabel jL_Header;
    private javax.swing.JLabel jL_Filler;
    // End of variables declaration//GEN-END:variables

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }


    public void closeFrame_Alarm()
    {
        if(flagCOMMIT)
        {
            if(this.flagREPORT == ADAMS_GlobalParam.SCRIPT_ALARM)
            {
                ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"L'operazione eseguita ha dato esito positivo, confermare l'operazione? ","Info",6);
                int Yes_No = op.getAnswer();

                if(Yes_No == 1)
                {
                    //System.out.println("La commit verrà confermata successivamente dalla classe parent....");
                    ADAMS_GlobalParam.optionPanel(this,"L'operazione di COMMIT definitiva verrà effettuata alla conferma dell'Alarm Relation.","INFO",3);

                }
                else
                {

                    try
                    {
                        //System.out.println("La commit no è stata confermata rollback....");
                        ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                    }
                    catch(java.sql.SQLException exc)
                    { System.out.println("Er. La commit no è stata confermata rollback....");}

                   return;
                }
            }
        }
        else
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"No COMMIT has been done. Do you really want to exit ?","Info",6);
            int Yes_No = op.getAnswer();

            if(Yes_No == 1)
            {
                try
                {
                    //System.out.println("La commit no è stata confermata rollback....");
                    ADAMS_GlobalParam.connect_Oracle.DBConnection.rollback();
                }
                catch(java.sql.SQLException exc)
                { System.out.println("Er. La commit no è stata confermata rollback....");}

            }
            else
            {
                return;
            }
        }

        dispose();
    }


    public void closeFrame()
    {
        if(flagCOMMIT==false)
        {
            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"No COMMIT has been done. Do you really want to exit ?","Info",6);
            int Yes_No = op.getAnswer();

            if(Yes_No == 1)
            {


            }else
            {
                return;
            }
        }


        Vector listaScript = null;
        if(Selected_row_TAB_CONFIG != null)
            listaScript=Selected_row_TAB_CONFIG.get_LISTA_SCRIPTS();

        //System.out.println("flagREPORT="+flagREPORT);
        //System.out.println("listaScript.size()="+listaScript.size());
        if(flagREPORT==ADAMS_GlobalParam.SCRIPT_TE)
        {
            if (listaScript != null)
            {
                if(listaScript.size()>0)
                {
                    if(jpTe!=null)
                        jpTe.refreshListaScript(true);
                }else
                {
                    if(jpTe!=null)
                        jpTe.refreshListaScript(false);
                }
            }
            dispose();
        }
        else if(flagREPORT==ADAMS_GlobalParam.SCRIPT_REPORT)
        {
            //hide();
            this.setVisible(false);
        }
        else
            dispose();
    }

    private static char[] set_String_toChar(String str, int length) //Raffo
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

    private void warningProblem(String str)
    {
        /*JOptionPane warning = new JOptionPane();
        warning.showMessageDialog(local_applet,"Comunication problem... please start the application again. "+e,"Error Message",JOptionPane.ERROR_MESSAGE);
        */
        ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,str,"Error Message",1);
    }

    private Vector creaRighe(String str) //Raffo
    {
        Vector appo=new Vector();
        int start=0;
    	int stop=0;

        while(stop!=-1)
        {
            stop=str.indexOf('\n');
            //System.out.println("START=["+start+"]");
            //System.out.println("STOP=["+stop+"]");
            String riga1="";
            if(stop==-1)
            {
                riga1=str.substring(start,str.length());
            }else
            {
                riga1=str.substring(start,stop);
            }

            str=str.substring(stop+1,str.length());
            if(DEBUG)
            {
                System.out.println("["+riga1+"]");
            }
            start=0;
            appo.addElement(riga1+'\n');
        }
        return appo;
    }

}
