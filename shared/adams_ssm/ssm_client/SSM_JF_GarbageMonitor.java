/*
 * SSM_JF_GarbageMonitor.java
 *
 * Created on 21 febbraio 2006, 11.04
 */

/**
 *
 * @author  Alessandra Pau - Telecom Italia S.p.A.
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.Vector;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.SSM.*;

public class SSM_JF_GarbageMonitor extends javax.swing.JFrame {

    public boolean                                  DEBUG               = true;
    public boolean                                  modify              = false;
    public int                                      selectedRow         = -1;
    public String                                   User                = "";
    public String                                   Path                = "";
    public String                                   LogPath             = "";
    public String                                   sFilter             = "";
    public String                                   Frequence           = "";
    public String                                   NDays               = "";
    public String                                   Days                = "";
    public String[]                                 DayOfWeekSubstr     = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
    public String[]                                 DayOfWeek           = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    public String[]                                 FrequenceT          = {"Z","D","W","M"};
    public String[]                                 FrequenceType       = {"Last days","Daily","Weekly","Montly"};
    private DATA_CENTRALI[]                         Local_ALLcentrali   = null;
    private String                                  SWITCH_SELECTED     = "";
    private String                                  FREQ_SELECTED       = "";
    private java.awt.event.ActionListener           JCFreqEvent         = null;
    private GARBAGE_INFO[]                          GarbageInfo         = null;
    private int                                     _USER_DIM           = GarbageMonitorServer.eGB_LENUSER;
    private int                                     _PATH_DIM           = GarbageMonitorServer.eGB_LENPATH;
    private int                                     _FILT_DIM           = GarbageMonitorServer.eGB_LENFILTER;
    private int                                     _FREQ_DIM           = GarbageMonitorServer.eGB_LENFREQUENCE;
    private int                                     _PATH_LOG_DIM       = GarbageMonitorServer.eGB_LENPATH_LOG;


    /** Creates new form SSM_JF_GarbageMonitor */
    public SSM_JF_GarbageMonitor(DATA_CENTRALI[] ALLcentrali) {
        Local_ALLcentrali=ALLcentrali;
        initComponents();

        jTItems = new javax.swing.JTable();
        jTItems.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "User", "Physical Path", "Filter", "Frequence", "Log Path"
        }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTItems.setFont(new java.awt.Font("Verdana", 0, 10));
        jTItems.setRowHeight(20);
        jTItems.setRowMargin(2);
        jTItems.setAutoCreateColumnsFromModel(false);
        jTItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTItemsMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTItems);
        jTItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader JTheader = jTItems.getTableHeader();
        JTheader.setFont(new java.awt.Font("Verdana", 0, 10));
        JTheader.setReorderingAllowed(false);
        jTItemsModel = (javax.swing.table.DefaultTableModel)jTItems.getModel();

        TableColumn column = null;
        for(int i=0; i<jTItems.getColumnCount(); i++)
        {
            column = jTItems.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);
        }

        jTextUser.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SPECIAL,10));
        jTextPath.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SPECIAL,257));
        jTextLogPath.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SPECIAL,257));
        jTextFilter.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.ALPHA_NUMERIC_SPECIAL,15));
        jTextNDays.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NUMERIC,3));

        JCFreqEvent = (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboFreqActionPerformed(evt);
            }
        });
        jComboFreq.addActionListener(JCFreqEvent);

        for(int i=0; i<ALLcentrali.length; i++)
        {
//            if ((SSM_GlobalParam.rmp3i).equals("YES")) {
//               if (!((new String(ALLcentrali[i].Descrizione).trim()).equals("RMP3I")))	// scarta tutto tranne RMP3I
//            			continue;
//
//               jComboSwitch.addItem(new String(ALLcentrali[i].Descrizione).trim());
//            }
//            else
//            {
//                if (((new String(ALLcentrali[i].Descrizione).trim()).equals("RMP3I")))	// scarta RMP3I
//            			continue;
//                jComboSwitch.addItem(new String(ALLcentrali[i].Descrizione).trim());
//            }

            jComboSwitch.addItem(new String(ALLcentrali[i].Descrizione).trim());
        }

        setFixedData();

        jRBAdd.setSelected(true);
        StartEnable();

        getContentPane().setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(SSM_GlobalParam.font_B10);
        jLabel2.setFont(SSM_GlobalParam.font_B10);
        jLabelUser.setFont(SSM_GlobalParam.font_B10);
        jLabelPath.setFont(SSM_GlobalParam.font_B10);
        jLabelLogPath.setFont(SSM_GlobalParam.font_B10);
        jLabelFilter.setFont(SSM_GlobalParam.font_B10);
        jLabelFreq.setFont(SSM_GlobalParam.font_B10);
        jRadioButton1.setFont(SSM_GlobalParam.font_B10);

        SSM_GlobalParam.setCenteredFrame(this,860,650);
        jTextLogPath.setEnabled(false);

    }

    // This method is called to enable or disable fields
    private void enableFields(boolean state){
        jTextUser.setEnabled(state);
        jTextPath.setEnabled(state);
        jTextLogPath.setEnabled(state);
        jTextFilter.setEnabled(state);
        jComboFreq.setEnabled(state);
        jComboDays.setEnabled(state);
        jTextNDays.setEnabled(state);
        jTItems.setEnabled(state);
    }

    // This method is called to set default values for text fields
    private void setFixedData() {
        jComboFreq.addItem("<Select one>");
        jComboFreq.addItem(FrequenceType[0]);
        jComboFreq.addItem(FrequenceType[1]);
        jComboFreq.addItem(FrequenceType[2]);
        jComboFreq.addItem(FrequenceType[3]);
        jComboDays.addItem("<Select one>");
        jComboDays.addItem(DayOfWeek[0]);
        jComboDays.addItem(DayOfWeek[1]);
        jComboDays.addItem(DayOfWeek[2]);
        jComboDays.addItem(DayOfWeek[3]);
        jComboDays.addItem(DayOfWeek[4]);
        jComboDays.addItem(DayOfWeek[5]);
        jComboDays.addItem(DayOfWeek[6]);
    }

    // This method is called to clear all fields
    private void setFieldsNull() {
        jTextUser.setText("");
        jTextFilter.setText("");
        jTextPath.setText("");
        jTextLogPath.setText("");
        jTextNDays.setText("");
        try {
            jComboDays.setSelectedIndex(0);
        }
        catch(Exception e) {}
        try {
            jComboFreq.setSelectedIndex(0);
        }
        catch(Exception e) {}
        jTItems.clearSelection();
        jScrollPane1.getVerticalScrollBar().setValue(0);
        selectedRow = -1;
    }

    // This method is called to enable or disable fields at start or reset
    private void StartEnable(){
        enableFields(true);
        setFieldsNull();
        jComboDays.setEnabled(false);
        jTextNDays.setEnabled(false);
        jTItems.setEnabled(false);
        jRadioButton1.setSelected(false);
        jTextLogPath.setEnabled(false);
        jScrollPane1.getVerticalScrollBar().setValue(0);
    }

    // This method is called to show a message of error or warning
    private void warningProblem(String str1, String str2) {
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE);
    }

    // This method is called to show a confirming message
    private int confirmMessage(String str1, String str2) {
            int option = 0;
            JOptionPane confirm = new JOptionPane();
            option = confirm.showConfirmDialog(this, ""+str1, ""+str2, JOptionPane.YES_NO_OPTION);
            return option;
    }

    // This method is called to show a confirming message if user didn't save changes
    private void saveBeforeClosing() {
        int option = 0;
        JOptionPane confirm = new JOptionPane();
        option = confirm.showConfirmDialog(this, "You have made changes. Do you want to save changed data?", "Select an option", JOptionPane.YES_NO_OPTION);
        if(option == 1)
            return;
        else
            setGarbage();
    }

    // This method is called to get values from table's row and to set text fields with this values
    private void getTableValue() {
        if(selectedRow != -1)
        {
            User = (String)(jTItems.getValueAt(selectedRow,0));
            Path = (String)(jTItems.getValueAt(selectedRow,1));
            sFilter = (String)(jTItems.getValueAt(selectedRow,2));
            Frequence = (String)(jTItems.getValueAt(selectedRow,3));
            LogPath = (String)(jTItems.getValueAt(selectedRow,4));

            decodeFrequenceTable(Frequence);

            jTextUser.setText(User);
            jTextFilter.setText(sFilter);
            jTextPath.setText(Path);
            jTextLogPath.setText(LogPath);
            if(LogPath.equals(""))
            {
                jRadioButton1.setSelected(false);
                jTextLogPath.setEnabled(false);
            }else
            {
                jRadioButton1.setSelected(true);
                jTextLogPath.setEnabled(true);
            }
        }
    }

    // This method is called to decode the value of Frequence from table to fields
    private void decodeFrequenceTable(String str) {
        boolean findDay = false;
        String c_freq = "";
        String s_freq = "";
        int indexStr = 0;
        if((indexStr = str.indexOf(" - ")) > 0) {
            try{
                c_freq = str.substring(0,indexStr);
            }catch(Exception e) {
            }
            try{
                s_freq = str.substring(indexStr + 3);
            }catch(Exception e) {
            }
        }
        else
            c_freq = str;

        for( int id = 0; id < 4; id++) {
            if(c_freq.equals(FrequenceType[id])) {
                jComboFreq.setSelectedItem(c_freq);
                if(s_freq.equals("")) {
                    jTextNDays.setText("");
                }
                else {
                    for( int idx = 0; idx < 7; idx++) {
                        if(s_freq.equals(DayOfWeek[idx])) {
                            jComboDays.setSelectedItem(s_freq);
                            findDay = true;
                            break;
                        }
                    }
                    if(!findDay)
                        jTextNDays.setText(s_freq);
                }
                break;
            }
        }
    }

    // This method is called to decode the value of Frequence from fields to string
    private String decodeFrequenceCombo(String Freq, String Ndays, String Day) {
        String frequence = "";
        int add_zero = 0;

        for( int id = 0; id < 4; id++) {
            if(Freq.equals(FrequenceType[id])) {
                if(Ndays.equals("")) {
                    if(Day.equals("<Select one>"))
                        frequence = Freq;
                    else
                        frequence = Freq + " - " + Day;
                }
                else {
                    frequence = Freq + " - ";
                    add_zero = 3 - Ndays.length();
                    if(add_zero > 0) {
                        for(int ilen = 0; ilen < add_zero; ilen ++){
                            frequence += "0";
                        }
                    }
                    frequence += Ndays;
                }
            }
        }

        return frequence;
    }

    // This method is called to decode the value of Frequence for table data
    private void decodeFrequenceForTable(String str) {
        String c_freq = "";
        String s_freq = "";

        c_freq = str.substring(0,1);
        s_freq = str.substring(1,4);
        for( int id = 0; id < 4; id++) {
            if(c_freq.equals(FrequenceT[id])) {
                Frequence = FrequenceType[id];
                if(c_freq.equals("W")) {
                    Frequence += " - ";
                    for( int idx = 0; idx < 7; idx++) {
                        if(s_freq.equals(DayOfWeekSubstr[idx])) {
                            //if(s_freq.equals(""))
                            Frequence += DayOfWeek[idx];
                            break;
                        }
                    }
                }
                else if(c_freq.equals("Z")) {
                    Frequence += " - ";
                    Frequence += s_freq;
                }
                break;
            }
        }
    }

    // This method is called to decode the value of Frequence for file data
    private void decodeFrequenceForFile(String str) {
        boolean findDay = false;
        String c_freq = "";
        String s_freq = "";
        int indexStr = 0;
        if((indexStr = str.indexOf(" - ")) > 0) {
            try{
                c_freq = str.substring(0,indexStr);
            }catch(Exception e) {
            }
            try{
                s_freq = str.substring(indexStr + 3);
            }catch(Exception e) {
            }
        }
        else
            c_freq = str;

        for( int id = 0; id < 4; id++) {
            if(c_freq.equals(FrequenceType[id])) {
                Frequence = FrequenceT[id];
                if(s_freq.equals("")) {
                    Frequence += "---" ;
                }
                else {
                    for( int idx = 0; idx < 7; idx++) {
                        if(s_freq.equals(DayOfWeek[idx])) {
                            Frequence += DayOfWeekSubstr[idx];
                            findDay = true;
                            break;
                        }
                    }
                    if(!findDay)
                        Frequence += s_freq;
                }
                break;
            }
        }
    }

    // This method is called to get data file for table
    private void getGarbage() {
        if(DEBUG) {
            System.out.println("Centrale: " + SWITCH_SELECTED);
        }
        GarbageInfo = SSM_GlobalParam.CORBAConn.refreshGarbage(SWITCH_SELECTED);
        if(GarbageInfo!=null) {
            int garbageLen = GarbageInfo.length;
            for(int k = 0; k < garbageLen; k++) {
            	Vector V_addRow = new Vector();
            	V_addRow.addElement(new String(GarbageInfo[k].user).trim());
            	V_addRow.addElement(new String(GarbageInfo[k].path).trim());
            	V_addRow.addElement(new String(GarbageInfo[k].filter).trim());
                decodeFrequenceForTable(new String(GarbageInfo[k].frequence).trim());
            	V_addRow.addElement(Frequence);
                V_addRow.addElement(new String(GarbageInfo[k].path_log).trim());
            	jTItemsModel.addRow(V_addRow);
            }
            jScrollPane1.validate();
            jScrollPane1.repaint();
        }
    }

    // This method is called to set data from table to file
    private void setGarbage() {
        if(DEBUG) {
            System.out.println("Centrale: " + SWITCH_SELECTED);
        }
        int rowCount = jTItems.getRowCount();
        GARBAGE_INFO[] GarbageInfoOut = new GARBAGE_INFO[rowCount];
        for(int y = 0; y < rowCount; y++) {
            GarbageInfoOut[y] = new GARBAGE_INFO();
            User = (String)(jTItems.getValueAt(y,0));
            Path = (String)(jTItems.getValueAt(y,1));
            sFilter = (String)(jTItems.getValueAt(y,2));
            Frequence = (String)(jTItems.getValueAt(y,3));
            LogPath = (String)(jTItems.getValueAt(y,4));
            GarbageInfoOut[y].user = SSM_GlobalParam.set_String_toChar_WhitSpace(User, _USER_DIM);
            GarbageInfoOut[y].path = SSM_GlobalParam.set_String_toChar_WhitSpace(Path, _PATH_DIM);
            GarbageInfoOut[y].filter = SSM_GlobalParam.set_String_toChar_WhitSpace(sFilter, _FILT_DIM);
            decodeFrequenceForFile(Frequence);
            GarbageInfoOut[y].frequence = SSM_GlobalParam.set_String_toChar_WhitSpace(Frequence, _FREQ_DIM);
            GarbageInfoOut[y].path_log = SSM_GlobalParam.set_String_toChar_WhitSpace(LogPath, _PATH_LOG_DIM);
        }
        int choose = confirmMessage("Do you really want to save changes?","Select an option");
        if(choose == 1)
            return;
        else {
            int retVal = SSM_GlobalParam.CORBAConn.writeGarbage(SWITCH_SELECTED, modify, GarbageInfoOut);
            if(retVal>=0) {
                SSM_GlobalParam.optionPanel(this,"Operation done.","INFO",JD_Message.INFO);
            }
            else {
                System.out.println("Error in commit: " + retVal);
                warningProblem("Error in commit operation","Error Message");
                return;
            }
        }
    }
    // ************************************

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        JPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jRBAdd = new javax.swing.JRadioButton();
        jRBMod = new javax.swing.JRadioButton();
        jRBDel = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jTextUser = new javax.swing.JTextField();
        jLabelUser = new javax.swing.JLabel();
        jLabelPath = new javax.swing.JLabel();
        jLabelFilter = new javax.swing.JLabel();
        jTextFilter = new javax.swing.JTextField();
        jTextPath = new javax.swing.JTextField();
        jLabelFreq = new javax.swing.JLabel();
        jComboDays = new javax.swing.JComboBox();
        jBApply = new javax.swing.JButton();
        jBReset = new javax.swing.JButton();
        jComboFreq = new javax.swing.JComboBox();
        jTextNDays = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelLogPath = new javax.swing.JLabel();
        jTextLogPath = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jBClose = new javax.swing.JButton();
        jBCommit = new javax.swing.JButton();
        jLTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLSwitch = new javax.swing.JLabel();
        jComboSwitch = new javax.swing.JComboBox();


        getContentPane().setLayout(null);

        setTitle("Garbage Manager");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        JPanel1.setLayout(null);

        JPanel1.setBackground(new java.awt.Color(230, 230, 230));
        JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "List of items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 10)));
        JPanel1.setFont(new java.awt.Font("Verdana", 0, 10));
        JPanel1.setPreferredSize(new java.awt.Dimension(850, 250));
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane1.setBorder(new javax.swing.border.EtchedBorder());
        JPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 17, 830, 230);

        jRBAdd.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup2.add(jRBAdd);
        jRBAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID.jpg")));
        jRBAdd.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jRBAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_over.jpg")));
        jRBAdd.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jRBAdd.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_create_ID_press.jpg")));
        jRBAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBAddActionPerformed(evt);
            }
        });

        JPanel1.add(jRBAdd);
        jRBAdd.setBounds(270, 250, 78, 28);

        jRBMod.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup2.add(jRBMod);
        jRBMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID.jpg")));
        jRBMod.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jRBMod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_over.jpg")));
        jRBMod.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jRBMod.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_ID_press.jpg")));
        jRBMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBModActionPerformed(evt);
            }
        });

        JPanel1.add(jRBMod);
        jRBMod.setBounds(360, 250, 78, 28);

        jRBDel.setBackground(new java.awt.Color(230, 230, 230));
        buttonGroup2.add(jRBDel);
        jRBDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID.jpg")));
        jRBDel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jRBDel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_over.jpg")));
        jRBDel.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jRBDel.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_ID_press.jpg")));
        jRBDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBDelActionPerformed(evt);
            }
        });

        JPanel1.add(jRBDel);
        jRBDel.setBounds(450, 250, 78, 28);

        getContentPane().add(JPanel1);
        JPanel1.setBounds(0, 110, 850, 290);

        jPanel3.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Selected item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 10)));
        jPanel3.setPreferredSize(new java.awt.Dimension(850, 190));
        jTextUser.setFont(new java.awt.Font("Verdana", 0, 10));
        jTextUser.setPreferredSize(new java.awt.Dimension(24, 280));
        jPanel3.add(jTextUser);
        jTextUser.setBounds(10, 40, 110, 24);

        jLabelUser.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabelUser.setText("User");
        jLabelUser.setPreferredSize(new java.awt.Dimension(30, 24));
        jPanel3.add(jLabelUser);
        jLabelUser.setBounds(10, 20, 30, 20);

        jLabelPath.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabelPath.setText("Physical Path");
        jLabelPath.setPreferredSize(new java.awt.Dimension(80, 24));
        jPanel3.add(jLabelPath);
        jLabelPath.setBounds(120, 20, 80, 20);

        jLabelFilter.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabelFilter.setText("Filter");
        jLabelFilter.setPreferredSize(new java.awt.Dimension(30, 24));
        jPanel3.add(jLabelFilter);
        jLabelFilter.setBounds(470, 20, 60, 20);

        jTextFilter.setFont(new java.awt.Font("Verdana", 0, 10));
        jTextFilter.setPreferredSize(new java.awt.Dimension(24, 440));
        jPanel3.add(jTextFilter);
        jTextFilter.setBounds(470, 40, 200, 24);

        jTextPath.setFont(new java.awt.Font("Verdana", 0, 10));
        jTextPath.setPreferredSize(new java.awt.Dimension(24, 600));
        jPanel3.add(jTextPath);
        jTextPath.setBounds(120, 40, 350, 24);

        jLabelFreq.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabelFreq.setText("Frequence");
        jLabelFreq.setPreferredSize(new java.awt.Dimension(70, 24));
        jPanel3.add(jLabelFreq);
        jLabelFreq.setBounds(10, 70, 70, 24);

        jComboDays.setBackground(new java.awt.Color(230, 230, 230));
        jComboDays.setFont(new java.awt.Font("Verdana", 0, 10));
        jComboDays.setPreferredSize(new java.awt.Dimension(100, 24));
        jPanel3.add(jComboDays);
        jComboDays.setBounds(170, 90, 110, 24);

        jBApply.setBackground(new java.awt.Color(230, 230, 230));
        jBApply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        jBApply.setBorderPainted(false);
        jBApply.setContentAreaFilled(false);
        jBApply.setFocusPainted(false);
        jBApply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        jBApply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        jBApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBApplyActionPerformed(evt);
            }
        });

        jPanel3.add(jBApply);
        jBApply.setBounds(690, 60, 70, 30);

        jBReset.setBackground(new java.awt.Color(230, 230, 230));
        jBReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset.jpg")));
        jBReset.setBorderPainted(false);
        jBReset.setContentAreaFilled(false);
        jBReset.setFocusPainted(false);
        jBReset.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset_press.jpg")));
        jBReset.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reset_over.jpg")));
        jBReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBResetActionPerformed(evt);
            }
        });

        jPanel3.add(jBReset);
        jBReset.setBounds(760, 60, 70, 30);

        jComboFreq.setBackground(new java.awt.Color(230, 230, 230));
        jComboFreq.setFont(new java.awt.Font("Verdana", 0, 10));
        jPanel3.add(jComboFreq);
        jComboFreq.setBounds(10, 90, 110, 24);

        jTextNDays.setFont(new java.awt.Font("Verdana", 0, 10));
        jTextNDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNDaysActionPerformed(evt);
            }
        });

        jPanel3.add(jTextNDays);
        jTextNDays.setBounds(120, 90, 50, 24);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabel1.setText("N. Days");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(120, 70, 50, 24);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabel2.setText("Days");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(170, 70, 110, 24);

        jLabelLogPath.setFont(new java.awt.Font("Verdana", 1, 10));
        jLabelLogPath.setText("Log Path");
        jLabelLogPath.setPreferredSize(new java.awt.Dimension(80, 24));
        jPanel3.add(jLabelLogPath);
        jLabelLogPath.setBounds(380, 70, 130, 20);

        jTextLogPath.setFont(new java.awt.Font("Verdana", 0, 10));
        jTextLogPath.setPreferredSize(new java.awt.Dimension(24, 600));
        jTextLogPath.setEnabled(false);
        jPanel3.add(jTextLogPath);
        jTextLogPath.setBounds(380, 90, 290, 24);

        jRadioButton1.setBackground(new java.awt.Color(230, 230, 230));
        jRadioButton1.setFont(new java.awt.Font("Dialog", 1, 10));
        jRadioButton1.setText("Enable Log");
        jRadioButton1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jRadioButton1.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jRadioButton1.setFocusPainted(false);
        jRadioButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
        jRadioButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jRadioButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
        jRadioButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
        jRadioButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jPanel3.add(jRadioButton1);
        jRadioButton1.setBounds(290, 90, 90, 21);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 410, 850, 140);

        jPanel4.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setPreferredSize(new java.awt.Dimension(850, 50));
        jBClose.setBackground(new java.awt.Color(230, 230, 230));
        jBClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jBClose.setBorderPainted(false);
        jBClose.setContentAreaFilled(false);
        jBClose.setFocusPainted(false);
        jBClose.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jBClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jBClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCloseActionPerformed(evt);
            }
        });

        jPanel4.add(jBClose);
        jBClose.setBounds(740, 10, 70, 30);

        jBCommit.setBackground(new java.awt.Color(230, 230, 230));
        jBCommit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
        jBCommit.setBorderPainted(false);
        jBCommit.setContentAreaFilled(false);
        jBCommit.setFocusPainted(false);
        jBCommit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
        jBCommit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
        jBCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCommitActionPerformed(evt);
            }
        });

        jPanel4.add(jBCommit);
        jBCommit.setBounds(650, 10, 90, 30);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 550, 850, 50);

        jLTitle.setFont(new java.awt.Font("Verdana", 1, 14));
        jLTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cestino.png")));
        jLTitle.setText("Garbage Manager");
        jLTitle.setPreferredSize(new java.awt.Dimension(165, 32));
        getContentPane().add(jLTitle);
        jLTitle.setBounds(330, 10, 200, 32);

        jPanel2.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBorder(new javax.swing.border.EtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 42));
        jLSwitch.setBackground(new java.awt.Color(230, 230, 230));
        jLSwitch.setFont(new java.awt.Font("Verdana", 1, 12));
        jLSwitch.setText("Select a Switch");
        jLSwitch.setPreferredSize(new java.awt.Dimension(100, 24));
        jPanel2.add(jLSwitch);
        jLSwitch.setBounds(10, 10, 110, 24);

        jComboSwitch.setBackground(new java.awt.Color(230, 230, 230));
        jComboSwitch.setPreferredSize(new java.awt.Dimension(100, 24));
        jComboSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboSwitchActionPerformed(evt);
            }
        });

        jPanel2.add(jComboSwitch);
        jComboSwitch.setBounds(140, 10, 100, 24);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(300, 60, 260, 42);

        pack();
    }//GEN-END:initComponents

    private void jTextNDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNDaysActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jTextNDaysActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // Add your handling code here:
            jTextLogPath.setEnabled(jRadioButton1.isSelected());
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTItemsMouseReleased(java.awt.event.MouseEvent evt) {
        selectedRow = jTItems.getSelectedRow();
        if((jRBMod.isSelected() || jRBDel.isSelected()) && (selectedRow != -1)) {
            if(jRBMod.isSelected()) {
                enableFields(true);
            }
            getTableValue();
        }
    }

    private void jComboFreqActionPerformed(java.awt.event.ActionEvent evt) {
        FREQ_SELECTED=(String)jComboFreq.getSelectedItem();
        if(!FREQ_SELECTED.equals("<Select one>")) {
            if(jRBMod.isSelected() || jRBAdd.isSelected()) {
                if(FREQ_SELECTED.equals(FrequenceType[2])) {
                    jComboDays.setEnabled(true);
                    jComboDays.setSelectedIndex(0);
                    jTextNDays.setText("");
                    jTextNDays.setEnabled(false);
                }
                else if(FREQ_SELECTED.equals(FrequenceType[0])) {
                    jTextNDays.setEnabled(true);
                    try {
                        jComboDays.setSelectedIndex(0);
                    }catch(Exception e) {
                        //System.out.println("jComboDays.setSelectedIndex(0): "+e.getMessage());
                    }
                    jComboDays.setEnabled(false);
                }
                else {
                    jTextNDays.setText("");
                    jTextNDays.setEnabled(false);
                    try {
                        jComboDays.setSelectedIndex(0);
                    }catch(Exception e) {
                        //System.out.println("jComboDays.setSelectedIndex(0): "+e.getMessage());
                    }
                    jComboDays.setEnabled(false);
                }
            }
        }
    }

    private void jRBDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBDelActionPerformed
        setFieldsNull();
        enableFields(false);
        jTItems.setEnabled(true);

        /*
        if(selectedRow == -1) {
            warningProblem("Please, select a row from List of items","Message");
            return;
        }
        */
    }//GEN-LAST:event_jRBDelActionPerformed

    private void jRBModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBModActionPerformed
        setFieldsNull();
        enableFields(false);
        jComboDays.setSelectedIndex(0);
        jComboDays.setEnabled(false);
        jComboFreq.setSelectedIndex(0);
        jTItems.setEnabled(true);

        /*
        if(selectedRow == -1) {
            warningProblem("Please, select a row from List of items","Message");
            return;
        }
        */
    }//GEN-LAST:event_jRBModActionPerformed

    private void jRBAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBAddActionPerformed
        StartEnable();
    }//GEN-LAST:event_jRBAddActionPerformed

    private void jBApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBApplyActionPerformed

        System.out.println("jRadioButton1: "+jRadioButton1.isSelected());

        User = jTextUser.getText().trim();
        Path = jTextPath.getText().trim();

        if(jRadioButton1.isSelected())
                LogPath = jTextLogPath.getText().trim();
            else
                LogPath = "";
        System.out.println("LogPath: "+LogPath);
        sFilter = jTextFilter.getText().trim();
        FREQ_SELECTED = (String)jComboFreq.getSelectedItem();
        NDays = jTextNDays.getText().trim();
        Days = (String)jComboDays.getSelectedItem();

        // String controls
        if(!jRBDel.isSelected()) {
            if( User.equals("") ||
                Path.equals("") ||
                sFilter.equals("") ||
                FREQ_SELECTED.equals("<Select one>") ) {
                warningProblem("Insert all items","Error Message");
                return;
            }
            else {
                if( (NDays.equals("") && FREQ_SELECTED.equals(FrequenceType[0])) ||
                    (Days.equals("<Select one>") && FREQ_SELECTED.equals(FrequenceType[2])) ) {
                    warningProblem("Insert all items","Error Message");
                    return;
                }
            }
        }

         if( jRadioButton1.isSelected() && LogPath.equals("") ) {
            warningProblem("Insert log path","Error Message");
            return;
         }

        Frequence = decodeFrequenceCombo(FREQ_SELECTED, NDays, Days);

        if(jRBAdd.isSelected()){
            // Add Table Row
            jTItemsModel.addRow(new String[]{User, Path, sFilter, Frequence,LogPath});
        }
        else if (jRBMod.isSelected()){
            // Modify Table Row
            jTItems.setValueAt(User, selectedRow, 0);  //User
            jTItems.setValueAt(Path, selectedRow, 1);  //Path
            jTItems.setValueAt(sFilter, selectedRow, 2);  //Filter
            jTItems.setValueAt(Frequence, selectedRow, 3);  //Frequence
            jTItems.setValueAt(LogPath, selectedRow, 4);  //Frequence
        }
        else {
            // Remove Table Row
            jTItemsModel.removeRow(selectedRow);
        }

        setFieldsNull();
        if(!jRBAdd.isSelected()) {
            enableFields(false);
            jTItems.setEnabled(true);
        }
        modify = true;

    }//GEN-LAST:event_jBApplyActionPerformed

    private void jComboSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboSwitchActionPerformed
        if(modify)
            saveBeforeClosing();

        SWITCH_SELECTED=(String)jComboSwitch.getSelectedItem();
        jTItemsModel.getDataVector().removeAllElements();
        jRBAdd.setSelected(true);
        StartEnable();
        modify = false;
        // Get data from file garbage.dat and set list of items
        getGarbage();
    }//GEN-LAST:event_jComboSwitchActionPerformed

    private void jBCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCommitActionPerformed
        if(modify)
            setGarbage();
        modify = false;
    }//GEN-LAST:event_jBCommitActionPerformed

    private void jBResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBResetActionPerformed
        setFieldsNull();
        jRBAdd.setSelected(true);
        StartEnable();

    }//GEN-LAST:event_jBResetActionPerformed

    private void jBCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCloseActionPerformed
        if(modify)
            saveBeforeClosing();
        closeFrame();
    }//GEN-LAST:event_jBCloseActionPerformed
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame() {
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel JPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jRBAdd;
    private javax.swing.JRadioButton jRBMod;
    private javax.swing.JRadioButton jRBDel;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextUser;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelPath;
    private javax.swing.JLabel jLabelFilter;
    private javax.swing.JTextField jTextFilter;
    private javax.swing.JTextField jTextPath;
    private javax.swing.JLabel jLabelFreq;
    private javax.swing.JComboBox jComboDays;
    private javax.swing.JButton jBApply;
    private javax.swing.JButton jBReset;
    private javax.swing.JComboBox jComboFreq;
    private javax.swing.JTextField jTextNDays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelLogPath;
    private javax.swing.JTextField jTextLogPath;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jBClose;
    private javax.swing.JButton jBCommit;
    private javax.swing.JLabel jLTitle;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jLSwitch;
    private javax.swing.JComboBox jComboSwitch;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTable jTItems;
    private javax.swing.table.DefaultTableModel jTItemsModel;

    private int[] CellDimension = {70,250,160,110,250};
    private int minCellDimension = 30;
}
