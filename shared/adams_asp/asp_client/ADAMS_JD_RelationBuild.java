/**
 *
 * @author  luca
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_JD_RelationBuild extends javax.swing.JDialog {

    /** Creates new form NTM_JD_RelationBuild */   
    public ADAMS_JD_RelationBuild(java.awt.Frame parent, ADAMS_TAB_INFO_CONFIG INFO_CONFIG,ADAMS_Vector_TAB_CONFIG Parent_V_TAB_CONFIG) {
        //super(parent, true);     
        super(parent, "- Relation Build -", java.awt.Dialog.ModalityType.DOCUMENT_MODAL); 
        
        this.local_INFO_CONFIG = INFO_CONFIG;
        this.parent_V_TAB_CONFIG = Parent_V_TAB_CONFIG;

        initComponents();
        
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC_SOMESYMBOLS,ADAMS_GlobalParam.LONG_DESC_LEN));
        
        str_help_add = ("Select an Element to add to your relation from the Traffic Element list. "+
                        "Click 'Add Relation Element' button to concatenate it or press 'Remove Last Relation Element' to revert operations. "+
                        "Please note that a relation is made by at least two (2) Traffic Elements. "+
                        "Click 'Set Time Fraction Element' button to select aggregation period.");
        
        jTA_help.setFont(ADAMS_GlobalParam.font_P11);
        jTA_help.setText(str_help_add);
               
        myTableModel_TE = new MyTableModel(all_columnNames);
        sorter_TE = new TableSorter(myTableModel_TE);
    
        jTable_TE = new javax.swing.JTable(sorter_TE);
        JTableHeader header = jTable_TE.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_P11);
        header.setReorderingAllowed(false);

        sorter_TE.setTableHeader(header);        
        
        jTable_TE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_TE.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_TE.setFont(new java.awt.Font("Verdana", 0, 10));
        jTable_TE.setRowHeight(20);
        jTable_TE.setRowMargin(2);
        jTable_TE.setAutoCreateColumnsFromModel(false);
        jTable_TE.setBorder(new javax.swing.border.LineBorder(java.awt.Color.black));
             
        jScrollPane_TE.setViewportView(jTable_TE);
        
        /*jTable_TE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_TEMouseReleased(evt);
            }
        });*/
        
        // Set Cursor        
        jB_addElem.setCursor(Cur_hand);
        jB_remLast.setCursor(Cur_hand);
        jB_T_FractionEl.setCursor(Cur_hand);
        jB_evalScr.setCursor(Cur_hand);
        jB_pluginName.setCursor(Cur_hand);
        jB_ok.setCursor(Cur_hand);
        jB_cancel.setCursor(Cur_hand);
        jTable_TE.setCursor(Cur_hand);
        jCBox_CountersTag.setCursor(Cur_hand);
        jCBox_Server.setCursor(Cur_hand);
        jB_ViewServer.setCursor(Cur_hand);
    
        //Set Font         
        jL_title.setFont(ADAMS_GlobalParam.font_B12);
        //----
        jLabel7.setFont(ADAMS_GlobalParam.font_B10);
        jLabel2.setFont(ADAMS_GlobalParam.font_B10);
        jLabel1.setFont(ADAMS_GlobalParam.font_B10);
        jL_CKit.setFont(ADAMS_GlobalParam.font_B10);
        jL_evalScr.setFont(ADAMS_GlobalParam.font_B10);
        jL_Plugin.setFont(ADAMS_GlobalParam.font_B10);
        jLabel3.setFont(ADAMS_GlobalParam.font_B10);
        jLabel4.setFont(ADAMS_GlobalParam.font_B10);
        jL1.setFont(ADAMS_GlobalParam.font_B10);
        jLabel9.setFont(ADAMS_GlobalParam.font_B10);
        jL_ID_REL.setFont(ADAMS_GlobalParam.font_B10);
        jL_relation.setFont(ADAMS_GlobalParam.font_B10);
        jT_desc.setFont(ADAMS_GlobalParam.font_B10);
        jL_IDCounter.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_CountersTag.setFont(ADAMS_GlobalParam.font_B10);
        jCBox_Server.setFont(ADAMS_GlobalParam.font_B10);
        jText_evalScr.setFont(ADAMS_GlobalParam.font_B10);
        jText_pluginName.setFont(ADAMS_GlobalParam.font_B10);
        jL_ID_TFractionE.setFont(ADAMS_GlobalParam.font_B10);
        jL_desc_TFractioE.setFont(ADAMS_GlobalParam.font_B10);
        jL_server.setFont(ADAMS_GlobalParam.font_B10);
        jB_ViewServer.setFont(ADAMS_GlobalParam.font_B10);
        
        SetTable(jTable_TE,30,cellDimen_TE);
        AddRowJTabel_TE();      
        
        init_alarmServer();  
        guiModality();
    }

    
    private void init_alarmServer()
    {
        jCBox_Server.setForeground(java.awt.Color.black);
        jCBox_Server.removeAllItems();      
        jCBox_Server.addItem(str_none);
        
        String sel_AlarmServerRead = "select distinct SERVER_ID from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' order by SERVER_ID";
        
        //System.out.println("sel_AlarmServerRead ----> "+sel_AlarmServerRead);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_AlarmServerRead,SQLStatement);
        
        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {   
                    int serverID = rs_simple.getInt("SERVER_ID");
                    jCBox_Server.addItem(""+serverID);
                }
            }
            catch (Exception ex) 
            {
                ex.printStackTrace();
                jCBox_Server.removeAllItems();    
                jCBox_Server.setForeground(java.awt.Color.red);
                jCBox_Server.addItem(str_none);                
            }
            
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_JD_RelationBuild) SQL Operation " + exc.toString());
            }
        }
        else
        {
            System.out.println("rs==null");
        }
    }
    
    private String getAlarmServer(int ID_RELATION)
    {
        String sel_ServerID = "select SERVER_ID from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE+"' and ID_ALARM_RELATION="+ID_RELATION+"";
        
        //System.out.println("sel_ServerID ----> "+sel_ServerID);
        int serverID = ADAMS_GlobalParam.connect_Oracle.Query_int(sel_ServerID); 
        
        if(serverID == -1)
        {
            return str_none;
        }
        return ""+serverID;
    }
    
    
    
    //M_ADD = 0;
    //M_MODIFY = 1;
    public void setModality(int modality, ADAMS_TAB_ALARM_RELATION ALARM_RELATION_Modify)
    {
        MODALITY = modality;            
        row_AlarmRelation_Modify = ALARM_RELATION_Modify;
        
        //System.out.println("setModality -->"+MODALITY );   
        guiModality();
    }
    
    private void guiModality()
    {
        if(MODALITY == M_ADD)
        {
            jL_ID_REL.setText("#");
            jL_relation.setText("");
            jL_relation.setToolTipText("");
            jT_desc.setText("");
            V_RELATION_ID.removeAllElements();
            V_RELATION_NAME.removeAllElements();
            V_DETAIL.removeAllElements();
            
            jL_ID_TFractionE.setText("");
            jL_desc_TFractioE.setText("");
            
            str_ConditionPlugin = "";
            idConditionScript = -1;
            timeFractionElementId = -1;
            
            setGuiScript(false);
            setGuiPlugin(false);
                       
            if(V_RELATION_ID.size() >= 2)
            {
                jB_ok.setEnabled(true);
                jB_propert_RE.setEnabled(true);
            }
            else
            {
                jB_ok.setEnabled(false);
                jB_propert_RE.setEnabled(false);
            }
            
            // ****************            
            try
            {
                jCBox_Server.setSelectedItem(str_none);                
            }
            catch (Exception e)
            {e.printStackTrace();}
            // ****************

        }
        else if (MODALITY == M_MODIFY)
        {
            jB_ok.setEnabled(true);
            jB_propert_RE.setEnabled(true);
            
            V_RELATION_ID.removeAllElements();
            V_RELATION_NAME.removeAllElements();
            V_DETAIL.removeAllElements();
            
            jL_ID_REL.setText(""+row_AlarmRelation_Modify.ID_ALARM_RELATION);
            jT_desc.setText(row_AlarmRelation_Modify.DESCRIPTION);
            
            for(int i=0; i<row_AlarmRelation_Modify.V_ALARM_REL_ELEMENTS.size(); i++ )
            {
                int id_Elememt = ((Integer)(row_AlarmRelation_Modify.V_ALARM_REL_ELEMENTS.elementAt(i))).intValue();                
                V_RELATION_ID.addElement(new Integer(id_Elememt));
                
                String str_TAG_Element = parent_V_TAB_CONFIG.getTagTE(id_Elememt);
                V_RELATION_NAME.addElement(new String(str_TAG_Element).trim());
                
                int enable_Detail = ((Integer)(row_AlarmRelation_Modify.V_ENABLE_DETAIL_ELEMENTS.elementAt(i))).intValue();
                V_DETAIL.addElement(new Integer(enable_Detail));
                
            }
                        
            int num_TE = V_RELATION_ID.size();
            String symbol_rel = "::";
            String str_relName = "";
            for(int i=0; i<num_TE; i++)
            {
                if(i == (num_TE-1))
                    symbol_rel = "";
                
                 str_relName = str_relName+((new String( ((String)V_RELATION_NAME.elementAt(i)))).trim())+symbol_rel;
                
            }
            jL_relation.setText(str_relName);
            jL_relation.setToolTipText(jL_relation.getText());
            
            idConditionScript = row_AlarmRelation_Modify.ID_CONDITION_SCRIPT;
            str_ConditionPlugin = row_AlarmRelation_Modify.STR_CONDITION_PLUGIN;
            
            if(idConditionScript == -1)
                setGuiScript(false);
            else
                setGuiScript(true);
            
            if(str_ConditionPlugin.length() > 0)
                setGuiPlugin(true);
            else
                setGuiPlugin(false);
            
            timeFractionElementId = row_AlarmRelation_Modify.TIME_FRACTION_ELEMENT_ID;
            jL_ID_TFractionE.setText(""+timeFractionElementId);
            jL_desc_TFractioE.setText(""+parent_V_TAB_CONFIG.getTagTE(timeFractionElementId));
            
            // **************           
            try
            {
                String ServerID_Selected = getAlarmServer(row_AlarmRelation_Modify.ID_ALARM_RELATION);
                jCBox_Server.setSelectedItem(ServerID_Selected);
            }
            catch (Exception e)
            {e.printStackTrace();}
            // **************
        }
        else
        {
            System.out.println("Error setModality(...) -- set AUTO-DEFAULT --> MODALITY = 0");
            MODALITY = 0;
        }
    }
    
    public void open_RelationBuild()
    {        
        CONFIRM_RELATION = false;
        setCenteredFrame(800,680);
        setVisible(true);
        toFront();
    }
    
    private void SetTable(javax.swing.JTable jtable, int minCellDimension, int[] CellDimension)
    {
        this.setCursor(Cur_wait);

        jtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = jtable.getTableHeader();
        header.setFont(ADAMS_GlobalParam.font_P11);
        header.setReorderingAllowed(false);

        TableColumn column = null;
        for(int i=0; i<jtable.getColumnCount(); i++)
        {
            column = jtable.getColumnModel().getColumn(i);
            
            column.setMinWidth(minCellDimension);
            column.setPreferredWidth(CellDimension[i]);            
        }
        
        if((jtable.isShowing())&&(jtable.isVisible()))
            jtable.updateUI();
                        
        this.setCursor(Cur_default);
    }
    
    
    public void setTableConfig()
    {
        AddRowJTabel_TE();
        jTable_TE.clearSelection();        
    }
    
    
    private void AddRowJTabel_TE()
    {    
        jTable_TE.setCursor(Cur_wait); 
        jL_title.setText("Relation Build - "+local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE);

        int SIZE = parent_V_TAB_CONFIG.size();
        Object[][] dataValues = new Object[SIZE][all_columnNames.length];
        for(int i=0; i<SIZE; i++)
        {
            ADAMS_TAB_CONFIG row_TAB_CONFIG = (ADAMS_TAB_CONFIG)parent_V_TAB_CONFIG.elementAt(i);
            
            dataValues[i][0] = new Integer(row_TAB_CONFIG.POSIZIONE_ELEMENTO);
            dataValues[i][1] = new String(row_TAB_CONFIG.TAG).trim();
            dataValues[i][2] = new String(row_TAB_CONFIG.DESCRIPTION).trim();
        }
        myTableModel_TE.setDataValues(dataValues);
        sorter_TE.setTableModel(myTableModel_TE);
        jScrollPane_TE.setViewportView(jTable_TE);
                               
        jTable_TE.setCursor(Cur_default);
    }
    
    public void initCounters(Vector Vector_CountersKit_ID, Vector Vector_CountersKit_TAG)
    {        
        
        //System.out.println("initCounters(....)");
        
        jCBox_CountersTag.removeActionListener(event_jCBox_CountersTag); 
        
        V_CountersKit_ID.removeAllElements();
        jCBox_CountersTag.removeAllItems();
        
        jL_IDCounter.setText("");        
        jCBox_CountersTag.addItem(str_none);
        CountersKit_ID_selected = -1;
        
        int cbox_indexSelected = 0;
        for(int i=0; i<Vector_CountersKit_ID.size(); i++)
        {
            String tag = ((String)(Vector_CountersKit_TAG.elementAt(i))).toString();
            Integer id = ((Integer)Vector_CountersKit_ID.elementAt(i));
            
            jCBox_CountersTag.addItem(tag);
            V_CountersKit_ID.addElement(id);
            
            if( row_AlarmRelation_Modify != null )
            {                
                if (row_AlarmRelation_Modify.COUNTERS_KIT_ID == id.intValue() )
                {
                    jL_IDCounter.setText(id.toString());
                    CountersKit_ID_selected = id.intValue();
                    cbox_indexSelected = i+1;
                }
            }
            //else
            //    System.out.println("row_AlarmRelation_Modify == null");
        }
        
        //System.out.println("cbox_indexSelected = "+cbox_indexSelected);
        //System.out.println("CountersKit_ID_selected = "+CountersKit_ID_selected);
        
        
        enabled_eval_Script();

        jCBox_CountersTag.setSelectedIndex(cbox_indexSelected);        
        jCBox_CountersTag.addActionListener(event_jCBox_CountersTag); 
    }
    
    public void setGuiScript(boolean flag)
    {
        //System.out.println("refreshListaScript("+flag+")");
        if(flag)
        {
            jText_evalScr.setText(strDefined);
            jText_evalScr.setForeground(java.awt.Color.green.darker());
        }
        else
        {
            jText_evalScr.setText(StrNotDefined);
            jText_evalScr.setForeground(java.awt.Color.red);
        }
    }
    
    public void setGuiPlugin(boolean flag)
    {
        if(flag)        
        {
            jText_pluginName.setText(strDefined);
            jText_pluginName.setForeground(java.awt.Color.green.darker());
        }
        else
        {
            jText_pluginName.setText(StrNotDefined);
            jText_pluginName.setForeground(java.awt.Color.red);       
        }        
    }
    
       
    public int getcounterKitID()
    {
        return CountersKit_ID_selected;
    }
    
    public int getidConditionScript()
    {
        return this.idConditionScript;
    }
    
    public String getstrConditionPlugin()
    {
        return this.str_ConditionPlugin;
    }
    
    public int getAnswercommitSCRIPT_Alarm()
    {
        return this.AnswerCommitSCRIPT_Alarm;
    }
    
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jP_TOT = new javax.swing.JPanel();
        jL_title = new javax.swing.JLabel();
        jP_center = new javax.swing.JPanel();
        jScrollPane_TE = new javax.swing.JScrollPane();
        jP_button_build = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jB_addElem = new javax.swing.JButton();
        jB_remLast = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jB_T_FractionEl = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTA_help = new javax.swing.JTextArea();
        jPanel_south = new javax.swing.JPanel();
        jP_sc = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jB_propert_RE = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jL_relation = new javax.swing.JLabel();
        jT_desc = new javax.swing.JTextField();
        jL_ID_REL = new javax.swing.JLabel();
        jL_CKit = new javax.swing.JLabel();
        jP_c3 = new javax.swing.JPanel();
        jL_IDCounter = new javax.swing.JLabel();
        jCBox_CountersTag = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jL_evalScr = new javax.swing.JLabel();
        jP_evalScr = new javax.swing.JPanel();
        jText_evalScr = new javax.swing.JTextField();
        jB_evalScr = new javax.swing.JButton();
        jL_Plugin = new javax.swing.JLabel();
        jP_plugin = new javax.swing.JPanel();
        jText_pluginName = new javax.swing.JTextField();
        jB_pluginName = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jP_TFract = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jL_ID_TFractionE = new javax.swing.JLabel();
        jL_desc_TFractioE = new javax.swing.JLabel();
        jL1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jB_copy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jCBox_Server = new javax.swing.JComboBox();
        jL_server = new javax.swing.JLabel();
        jB_ViewServer = new javax.swing.JButton();
        jP_ss = new javax.swing.JPanel();
        jP_sn = new javax.swing.JPanel();
        jB_ok = new javax.swing.JButton();
        jB_cancel = new javax.swing.JButton();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jP_TOT.setLayout(new java.awt.BorderLayout());
        
        jP_TOT.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setText("Relation Build");
        jL_title.setMinimumSize(new java.awt.Dimension(149, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(150, 40));
        jP_TOT.add(jL_title, java.awt.BorderLayout.NORTH);
        
        jP_center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_center.setBackground(new java.awt.Color(230, 230, 230));
        jP_center.setBorder(new javax.swing.border.TitledBorder(null, " Relation Build ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jScrollPane_TE.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane_TE.setBorder(new javax.swing.border.TitledBorder(null, " Traffic Elements ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jScrollPane_TE.setAutoscrolls(true);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_center.add(jScrollPane_TE, gridBagConstraints1);
        
        jP_button_build.setBackground(new java.awt.Color(230, 230, 230));
        jSeparator1.setMinimumSize(new java.awt.Dimension(50, 2));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 2));
        jP_button_build.add(jSeparator1);
        
        jB_addElem.setBackground(new java.awt.Color(230, 230, 230));
        jB_addElem.setFont(new java.awt.Font("Verdana", 1, 10));
        jB_addElem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_32.png")));
        jB_addElem.setText("Add Relation Element");
        jB_addElem.setBorderPainted(false);
        jB_addElem.setContentAreaFilled(false);
        jB_addElem.setFocusPainted(false);
        jB_addElem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_addElem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_addElem.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_press_32.png")));
        jB_addElem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jB_addElem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_addElemActionPerformed(evt);
            }
        });
        
        jP_button_build.add(jB_addElem);
        
        jB_remLast.setBackground(new java.awt.Color(230, 230, 230));
        jB_remLast.setFont(new java.awt.Font("Verdana", 1, 10));
        jB_remLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove_32.png")));
        jB_remLast.setText("Remove Last Relation Element");
        jB_remLast.setBorderPainted(false);
        jB_remLast.setContentAreaFilled(false);
        jB_remLast.setFocusPainted(false);
        jB_remLast.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_remLast.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_remLast.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove_press_32.png")));
        jB_remLast.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jB_remLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_remLastActionPerformed(evt);
            }
        });
        
        jP_button_build.add(jB_remLast);
        
        jSeparator2.setMinimumSize(new java.awt.Dimension(50, 2));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 2));
        jP_button_build.add(jSeparator2);
        
        jB_T_FractionEl.setBackground(new java.awt.Color(230, 230, 230));
        jB_T_FractionEl.setFont(new java.awt.Font("Verdana", 1, 10));
        jB_T_FractionEl.setForeground(java.awt.Color.blue);
        jB_T_FractionEl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_32.png")));
        jB_T_FractionEl.setText("Set Time Fraction Element");
        jB_T_FractionEl.setBorderPainted(false);
        jB_T_FractionEl.setContentAreaFilled(false);
        jB_T_FractionEl.setFocusPainted(false);
        jB_T_FractionEl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jB_T_FractionEl.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_T_FractionEl.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_press_32.png")));
        jB_T_FractionEl.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jB_T_FractionEl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_T_FractionElActionPerformed(evt);
            }
        });
        
        jP_button_build.add(jB_T_FractionEl);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jP_center.add(jP_button_build, gridBagConstraints1);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.TitledBorder(null, " Help information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10)));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 232));
        jTA_help.setBackground(new java.awt.Color(230, 230, 230));
        jTA_help.setEditable(false);
        jTA_help.setLineWrap(true);
        jTA_help.setWrapStyleWord(true);
        jTA_help.setPreferredSize(new java.awt.Dimension(100, 38));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jPanel1.add(jTA_help, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jP_center.add(jPanel1, gridBagConstraints1);
        
        jP_TOT.add(jP_center, java.awt.BorderLayout.CENTER);
        
        jPanel_south.setLayout(new java.awt.BorderLayout());
        
        jPanel_south.setBackground(new java.awt.Color(230, 230, 230));
        jPanel_south.setBorder(new javax.swing.border.TitledBorder(null, " Alarm Relation Build", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)));
        jPanel_south.setMinimumSize(new java.awt.Dimension(448, 265));
        jPanel_south.setPreferredSize(new java.awt.Dimension(10, 265));
        jP_sc.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_sc.setBackground(new java.awt.Color(230, 230, 230));
        jP_sc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jP_sc.setMinimumSize(new java.awt.Dimension(350, 194));
        jP_sc.setPreferredSize(new java.awt.Dimension(350, 194));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 24));
        jPanel2.setPreferredSize(new java.awt.Dimension(239, 24));
        jB_propert_RE.setBackground(new java.awt.Color(230, 230, 230));
        jB_propert_RE.setText(" Properties Relation Elements ");
        jB_propert_RE.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_propert_RE.setMaximumSize(new java.awt.Dimension(177, 24));
        jB_propert_RE.setMinimumSize(new java.awt.Dimension(177, 24));
        jB_propert_RE.setPreferredSize(new java.awt.Dimension(177, 24));
        jB_propert_RE.setEnabled(false);
        jB_propert_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_propert_REActionPerformed(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel2.add(jB_propert_RE, gridBagConstraints4);
        
        jLabel2.setBackground(new java.awt.Color(230, 230, 230));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Relation");
        jLabel2.setToolTipText("");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setMaximumSize(new java.awt.Dimension(150, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints4.weightx = 1.0;
        jPanel2.add(jLabel2, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jP_sc.add(jPanel2, gridBagConstraints3);
        
        jL_relation.setBackground(java.awt.Color.white);
        jL_relation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_relation.setToolTipText("");
        jL_relation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204)));
        jL_relation.setMinimumSize(new java.awt.Dimension(300, 20));
        jL_relation.setPreferredSize(new java.awt.Dimension(300, 20));
        jL_relation.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jL_relation, gridBagConstraints3);
        
        jT_desc.setMinimumSize(new java.awt.Dimension(300, 20));
        jT_desc.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.gridwidth = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_sc.add(jT_desc, gridBagConstraints3);
        
        jL_ID_REL.setBackground(java.awt.Color.white);
        jL_ID_REL.setForeground(java.awt.Color.darkGray);
        jL_ID_REL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_ID_REL.setMaximumSize(new java.awt.Dimension(70, 20));
        jL_ID_REL.setMinimumSize(new java.awt.Dimension(50, 20));
        jL_ID_REL.setPreferredSize(new java.awt.Dimension(50, 20));
        jL_ID_REL.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_sc.add(jL_ID_REL, gridBagConstraints3);
        
        jL_CKit.setBackground(new java.awt.Color(230, 230, 230));
        jL_CKit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_CKit.setText("Counter Kit");
        jL_CKit.setToolTipText("");
        jL_CKit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_CKit.setMaximumSize(new java.awt.Dimension(69, 20));
        jL_CKit.setMinimumSize(new java.awt.Dimension(69, 20));
        jL_CKit.setPreferredSize(new java.awt.Dimension(69, 20));
        jL_CKit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_CKit.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(3, 2, 1, 2);
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jL_CKit, gridBagConstraints3);
        
        jP_c3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_c3.setBackground(new java.awt.Color(230, 230, 230));
        jP_c3.setMinimumSize(new java.awt.Dimension(260, 22));
        jP_c3.setPreferredSize(new java.awt.Dimension(260, 22));
        jL_IDCounter.setBackground(java.awt.Color.white);
        jL_IDCounter.setForeground(java.awt.Color.gray);
        jL_IDCounter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_IDCounter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_IDCounter.setMaximumSize(new java.awt.Dimension(40, 20));
        jL_IDCounter.setMinimumSize(new java.awt.Dimension(50, 20));
        jL_IDCounter.setPreferredSize(new java.awt.Dimension(50, 20));
        jL_IDCounter.setOpaque(true);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(0, 2, 0, 7);
        jP_c3.add(jL_IDCounter, gridBagConstraints5);
        
        jCBox_CountersTag.setBackground(java.awt.Color.white);
        jCBox_CountersTag.setMinimumSize(new java.awt.Dimension(180, 20));
        jCBox_CountersTag.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 3;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(0, 2, 0, 0);
        gridBagConstraints5.weightx = 1.0;
        jP_c3.add(jCBox_CountersTag, gridBagConstraints5);
        
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ID");
        jLabel3.setMaximumSize(new java.awt.Dimension(20, 20));
        jLabel3.setMinimumSize(new java.awt.Dimension(20, 20));
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_c3.add(jLabel3, gridBagConstraints5);
        
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Tag");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 2;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(0, 7, 0, 2);
        jP_c3.add(jLabel4, gridBagConstraints5);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTH;
        jP_sc.add(jP_c3, gridBagConstraints3);
        
        jL_evalScr.setBackground(new java.awt.Color(230, 230, 230));
        jL_evalScr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_evalScr.setText("Evaluation Script");
        jL_evalScr.setToolTipText("");
        jL_evalScr.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_evalScr.setMaximumSize(new java.awt.Dimension(101, 20));
        jL_evalScr.setMinimumSize(new java.awt.Dimension(60, 20));
        jL_evalScr.setPreferredSize(new java.awt.Dimension(60, 20));
        jL_evalScr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_evalScr.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(3, 2, 1, 2);
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jL_evalScr, gridBagConstraints3);
        
        jP_evalScr.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jP_evalScr.setBackground(new java.awt.Color(230, 230, 230));
        jP_evalScr.setMinimumSize(new java.awt.Dimension(80, 21));
        jP_evalScr.setPreferredSize(new java.awt.Dimension(80, 21));
        jText_evalScr.setBackground(new java.awt.Color(230, 230, 230));
        jText_evalScr.setEditable(false);
        jText_evalScr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_evalScr.setToolTipText("");
        jText_evalScr.setDisabledTextColor(java.awt.Color.darkGray);
        jText_evalScr.setFocusable(false);
        jText_evalScr.setMinimumSize(new java.awt.Dimension(65, 20));
        jText_evalScr.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints6.weightx = 1.0;
        jP_evalScr.add(jText_evalScr, gridBagConstraints6);
        
        jB_evalScr.setFont(new java.awt.Font("Verdana", 1, 10));
        jB_evalScr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_evalScr.setToolTipText("Edit Evaluation Script (Active in modification)");
        jB_evalScr.setBorderPainted(false);
        jB_evalScr.setContentAreaFilled(false);
        jB_evalScr.setFocusPainted(false);
        jB_evalScr.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_evalScr.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_evalScr.setMinimumSize(new java.awt.Dimension(35, 20));
        jB_evalScr.setPreferredSize(new java.awt.Dimension(35, 20));
        jB_evalScr.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_evalScr.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_evalScr.setEnabled(false);
        jB_evalScr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_evalScrActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        jP_evalScr.add(jB_evalScr, gridBagConstraints6);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_sc.add(jP_evalScr, gridBagConstraints3);
        
        jL_Plugin.setBackground(new java.awt.Color(230, 230, 230));
        jL_Plugin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Plugin.setText("Plugin");
        jL_Plugin.setToolTipText("");
        jL_Plugin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_Plugin.setMaximumSize(new java.awt.Dimension(41, 20));
        jL_Plugin.setMinimumSize(new java.awt.Dimension(60, 20));
        jL_Plugin.setPreferredSize(new java.awt.Dimension(60, 20));
        jL_Plugin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_Plugin.setOpaque(true);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 3);
        gridBagConstraints3.weightx = 1.0;
        jP_sc.add(jL_Plugin, gridBagConstraints3);
        
        jP_plugin.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints7;
        
        jP_plugin.setBackground(new java.awt.Color(230, 230, 230));
        jP_plugin.setMinimumSize(new java.awt.Dimension(80, 21));
        jP_plugin.setPreferredSize(new java.awt.Dimension(80, 21));
        jText_pluginName.setBackground(new java.awt.Color(230, 230, 230));
        jText_pluginName.setEditable(false);
        jText_pluginName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jText_pluginName.setToolTipText("");
        jText_pluginName.setDisabledTextColor(java.awt.Color.darkGray);
        jText_pluginName.setFocusable(false);
        jText_pluginName.setMinimumSize(new java.awt.Dimension(65, 20));
        jText_pluginName.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(0, 1, 1, 1);
        gridBagConstraints7.weightx = 1.0;
        jP_plugin.add(jText_pluginName, gridBagConstraints7);
        
        jB_pluginName.setFont(new java.awt.Font("Verdana", 1, 10));
        jB_pluginName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_pluginName.setToolTipText("Edit Plugin (Active in modification)");
        jB_pluginName.setBorderPainted(false);
        jB_pluginName.setContentAreaFilled(false);
        jB_pluginName.setFocusPainted(false);
        jB_pluginName.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_pluginName.setMaximumSize(new java.awt.Dimension(35, 20));
        jB_pluginName.setMinimumSize(new java.awt.Dimension(35, 20));
        jB_pluginName.setPreferredSize(new java.awt.Dimension(35, 20));
        jB_pluginName.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_pluginName.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_pluginName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_pluginNameActionPerformed(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 0;
        jP_plugin.add(jB_pluginName, gridBagConstraints7);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 4;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 1, 1, 3);
        jP_sc.add(jP_plugin, gridBagConstraints3);
        
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ID");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(0, 2, 0, 2);
        jP_sc.add(jLabel7, gridBagConstraints3);
        
        jP_TFract.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints8;
        
        jP_TFract.setBackground(new java.awt.Color(230, 230, 230));
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Time Fraction Element");
        jLabel6.setToolTipText("ID");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setMaximumSize(new java.awt.Dimension(11, 20));
        jLabel6.setMinimumSize(new java.awt.Dimension(11, 20));
        jLabel6.setPreferredSize(new java.awt.Dimension(11, 20));
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 0;
        gridBagConstraints8.gridy = 0;
        gridBagConstraints8.gridwidth = 4;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints8.insets = new java.awt.Insets(1, 2, 1, 2);
        gridBagConstraints8.weightx = 1.0;
        jP_TFract.add(jLabel6, gridBagConstraints8);
        
        jL_ID_TFractionE.setBackground(java.awt.Color.white);
        jL_ID_TFractionE.setForeground(java.awt.Color.darkGray);
        jL_ID_TFractionE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_ID_TFractionE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_ID_TFractionE.setMaximumSize(new java.awt.Dimension(70, 20));
        jL_ID_TFractionE.setMinimumSize(new java.awt.Dimension(50, 20));
        jL_ID_TFractionE.setPreferredSize(new java.awt.Dimension(50, 20));
        jL_ID_TFractionE.setOpaque(true);
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 1;
        gridBagConstraints8.gridy = 1;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints8.insets = new java.awt.Insets(1, 2, 1, 7);
        jP_TFract.add(jL_ID_TFractionE, gridBagConstraints8);
        
        jL_desc_TFractioE.setBackground(java.awt.Color.white);
        jL_desc_TFractioE.setForeground(java.awt.Color.darkGray);
        jL_desc_TFractioE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_desc_TFractioE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_desc_TFractioE.setMaximumSize(new java.awt.Dimension(70, 20));
        jL_desc_TFractioE.setMinimumSize(new java.awt.Dimension(50, 20));
        jL_desc_TFractioE.setPreferredSize(new java.awt.Dimension(50, 20));
        jL_desc_TFractioE.setOpaque(true);
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 3;
        gridBagConstraints8.gridy = 1;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints8.insets = new java.awt.Insets(1, 2, 1, 2);
        jP_TFract.add(jL_desc_TFractioE, gridBagConstraints8);
        
        jL1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL1.setText("ID");
        jL1.setToolTipText("ID");
        jL1.setMaximumSize(new java.awt.Dimension(20, 20));
        jL1.setMinimumSize(new java.awt.Dimension(20, 20));
        jL1.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 0;
        gridBagConstraints8.gridy = 1;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints8.insets = new java.awt.Insets(1, 4, 1, 2);
        jP_TFract.add(jL1, gridBagConstraints8);
        
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Description");
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 2;
        gridBagConstraints8.gridy = 1;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints8.insets = new java.awt.Insets(0, 7, 0, 2);
        jP_TFract.add(jLabel9, gridBagConstraints8);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 6;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(2, 0, 2, 0);
        jP_sc.add(jP_TFract, gridBagConstraints3);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints9;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setText("Description");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setMaximumSize(new java.awt.Dimension(65, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(65, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(65, 20));
        gridBagConstraints9 = new java.awt.GridBagConstraints();
        gridBagConstraints9.gridx = 0;
        gridBagConstraints9.gridy = 0;
        gridBagConstraints9.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints9.insets = new java.awt.Insets(3, 2, 1, 2);
        gridBagConstraints9.weightx = 1.0;
        jPanel3.add(jLabel1, gridBagConstraints9);
        
        jB_copy.setBackground(new java.awt.Color(230, 230, 230));
        jB_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/giu.gif")));
        jB_copy.setToolTipText("Copy Relation like descritpion.");
        jB_copy.setMaximumSize(new java.awt.Dimension(20, 20));
        jB_copy.setMinimumSize(new java.awt.Dimension(20, 20));
        jB_copy.setPreferredSize(new java.awt.Dimension(20, 20));
        jB_copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_copyActionPerformed(evt);
            }
        });
        
        gridBagConstraints9 = new java.awt.GridBagConstraints();
        gridBagConstraints9.insets = new java.awt.Insets(3, 2, 1, 2);
        jPanel3.add(jB_copy, gridBagConstraints9);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridwidth = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        jP_sc.add(jPanel3, gridBagConstraints3);
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints10;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 48));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 53));
        jLabel5.setText("Server ID");
        gridBagConstraints10 = new java.awt.GridBagConstraints();
        gridBagConstraints10.gridx = 0;
        gridBagConstraints10.gridy = 1;
        gridBagConstraints10.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints10.weightx = 1.0;
        jPanel4.add(jLabel5, gridBagConstraints10);
        
        jCBox_Server.setBackground(java.awt.Color.white);
        jCBox_Server.setMinimumSize(new java.awt.Dimension(60, 20));
        jCBox_Server.setPreferredSize(new java.awt.Dimension(60, 25));
        gridBagConstraints10 = new java.awt.GridBagConstraints();
        gridBagConstraints10.gridx = 1;
        gridBagConstraints10.gridy = 1;
        gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints10.weightx = 1.0;
        jPanel4.add(jCBox_Server, gridBagConstraints10);
        
        jL_server.setBackground(new java.awt.Color(230, 230, 230));
        jL_server.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_server.setText("Active on the Handler Server");
        jL_server.setToolTipText("");
        jL_server.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL_server.setMaximumSize(new java.awt.Dimension(69, 20));
        jL_server.setMinimumSize(new java.awt.Dimension(69, 20));
        jL_server.setPreferredSize(new java.awt.Dimension(69, 20));
        jL_server.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jL_server.setOpaque(true);
        gridBagConstraints10 = new java.awt.GridBagConstraints();
        gridBagConstraints10.gridx = 0;
        gridBagConstraints10.gridy = 0;
        gridBagConstraints10.gridwidth = 2;
        gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints10.insets = new java.awt.Insets(3, 2, 1, 2);
        gridBagConstraints10.weightx = 1.0;
        jPanel4.add(jL_server, gridBagConstraints10);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 6;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 2, 2);
        jP_sc.add(jPanel4, gridBagConstraints3);
        
        jB_ViewServer.setBackground(new java.awt.Color(230, 230, 230));
        jB_ViewServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png")));
        jB_ViewServer.setText("View All Handler Server");
        jB_ViewServer.setToolTipText("View All");
        jB_ViewServer.setBorderPainted(false);
        jB_ViewServer.setContentAreaFilled(false);
        jB_ViewServer.setFocusPainted(false);
        jB_ViewServer.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_ViewServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ViewServerActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        gridBagConstraints3.gridy = 6;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(4, 2, 4, 2);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_sc.add(jB_ViewServer, gridBagConstraints3);
        
        jPanel_south.add(jP_sc, java.awt.BorderLayout.NORTH);
        
        jP_ss.setLayout(new java.awt.BorderLayout());
        
        jP_ss.setBackground(new java.awt.Color(230, 230, 230));
        jP_ss.setPreferredSize(new java.awt.Dimension(152, 40));
        jP_sn.setBackground(new java.awt.Color(230, 230, 230));
        jP_sn.setMinimumSize(new java.awt.Dimension(0, 30));
        jP_sn.setPreferredSize(new java.awt.Dimension(0, 30));
        jB_ok.setBackground(new java.awt.Color(230, 230, 230));
        jB_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok.jpg")));
        jB_ok.setBorderPainted(false);
        jB_ok.setContentAreaFilled(false);
        jB_ok.setFocusPainted(false);
        jB_ok.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_ok.setMaximumSize(new java.awt.Dimension(0, 0));
        jB_ok.setMinimumSize(new java.awt.Dimension(0, 0));
        jB_ok.setPreferredSize(new java.awt.Dimension(80, 30));
        jB_ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_press.jpg")));
        jB_ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ok_over.jpg")));
        jB_ok.setEnabled(false);
        jB_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_okActionPerformed(evt);
            }
        });
        
        jP_sn.add(jB_ok);
        
        jB_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_cancel.setToolTipText("Cancel");
        jB_cancel.setBorderPainted(false);
        jB_cancel.setContentAreaFilled(false);
        jB_cancel.setFocusPainted(false);
        jB_cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_cancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_cancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_cancelActionPerformed(evt);
            }
        });
        
        jP_sn.add(jB_cancel);
        
        jP_ss.add(jP_sn, java.awt.BorderLayout.CENTER);
        
        jPanel_south.add(jP_ss, java.awt.BorderLayout.SOUTH);
        
        jP_TOT.add(jPanel_south, java.awt.BorderLayout.SOUTH);
        
        getContentPane().add(jP_TOT, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private void jB_ViewServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ViewServerActionPerformed
        if(viewServer == null)
        {
            viewServer = new ADAMS_JF_ViewServerAlarm_DB();            
        }
        else
            viewServer.show_frame();
    }//GEN-LAST:event_jB_ViewServerActionPerformed

    private void jB_copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_copyActionPerformed
        jT_desc.setText(jL_relation.getText());
    }//GEN-LAST:event_jB_copyActionPerformed

    private void jB_propert_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_propert_REActionPerformed
        
        ADAMS_JD_RelationBuildProperties jD_rbp = new ADAMS_JD_RelationBuildProperties(ADAMS_GlobalParam.JFRAME_TAB,V_RELATION_ID,V_RELATION_NAME,V_DETAIL);
        jD_rbp.open_RelationBuildP();
        
        if( jD_rbp.get_OPERATION_VALID() )
            V_DETAIL = jD_rbp.get_V_DETAIL();

        
        /////////////////////////////////////////////
        //for(int i=0; i<V_DETAIL.size(); i++)
        //{
        //    System.out.println((String)V_RELATION_NAME.elementAt(i)+" enabled =>>>> "+((Integer)V_DETAIL.elementAt(i)).toString());
        //}
        /////////////////////////////////////////////
    }//GEN-LAST:event_jB_propert_REActionPerformed

    private void jB_T_FractionElActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_T_FractionElActionPerformed
        int selectedRow_build = jTable_TE.getSelectedRow();              
        if( selectedRow_build >=0 )
        {            
            timeFractionElementId = new Integer(""+jTable_TE.getValueAt(selectedRow_build,0)).intValue();
            jL_ID_TFractionE.setText(""+timeFractionElementId);
            jL_desc_TFractioE.setText(""+jTable_TE.getValueAt(selectedRow_build,1));
        }
        else
            ADAMS_GlobalParam.optionPanel("Select an item from the list < Traffic Elements >.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE); 
    }//GEN-LAST:event_jB_T_FractionElActionPerformed

    private void jB_pluginNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pluginNameActionPerformed
        
        ADAMS_JD_setPlugin JD_PluginSelect = new ADAMS_JD_setPlugin(ADAMS_GlobalParam.JFRAME_TAB,true,700,400,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,str_ConditionPlugin);
          
        JD_PluginSelect.setGuiEnable(true);
                
        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        str_ConditionPlugin = JD_PluginSelect.getPluginEnabled();
        JD_PluginSelect.dispose();
        
        if(str_ConditionPlugin.length() >0)        
        {
            jText_pluginName.setText(strDefined);
            jText_pluginName.setForeground(java.awt.Color.green.darker());
        }
        else
        {
            jText_pluginName.setText(StrNotDefined);
            jText_pluginName.setForeground(java.awt.Color.red);       
        }
        //System.out.println("getPluginEnabled "+str_ConditionPlugin);
    }//GEN-LAST:event_jB_pluginNameActionPerformed

    private void enabled_eval_Script()
    {
        if(CountersKit_ID_selected != -1)
        {
            jB_evalScr.setEnabled(true);
        }
        else
        {
            jB_evalScr.setEnabled(false);
        }
    }
    
    
    private void jB_evalScrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_evalScrActionPerformed
         this.setCursor(Cur_wait);

       /* ADAMS_TAB_CONFIG appo =new ADAMS_TAB_CONFIG();
        appo.TAG=" Alarm Relation ";
        appo.DESCRIPTION= jL_relation.getText().trim() ;*/
        
        String descriptionTitle = "Alarm Relation ["+jL_relation.getText().trim()+"]";
         
        ADAMS_TAB_ALARM_RELATION AlarmRelation = null;
        
        if(row_AlarmRelation_Modify != null)
        {
            AlarmRelation = row_AlarmRelation_Modify;
            row_AlarmRelation_Modify.ID_CONDITION_SCRIPT = idConditionScript;
        }
        else
        {
            AlarmRelation = new ADAMS_TAB_ALARM_RELATION();
        }
        
        ADAMS_JF_WIZARDBASE wizard = new ADAMS_JF_WIZARDBASE(this,0,local_INFO_CONFIG.TIPO_DI_CONFIGURAZIONE,CountersKit_ID_selected,descriptionTitle, true, AlarmRelation, parent_V_TAB_CONFIG);
        
        wizard.setVisible(true);
        
        this.AnswerCommitSCRIPT_Alarm = wizard.getAnswercommitSCRIPT_Alarm();
       //System.out.println("getAnswercommitSCRIPT_Alarm() = "+this.AnswerCommitSCRIPT_Alarm);
        
        this.idConditionScript = wizard.getID_SCRIPT();        
       //System.out.println("dopo wizard script ---> ID_SCRIPT = "+this.idConditionScript);
        
        if(idConditionScript == -1)
            setGuiScript(false);
        else
            setGuiScript(true);
        
         
        
        wizard.dispose();

        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_evalScrActionPerformed

    private int isPresent_ID(int ID, Vector vector_ID)
    {
        for(int i=0; i<vector_ID.size(); i++)
        {
            if(  (((Integer)vector_ID.elementAt(i)).intValue()) == ID  )
                return i;
        }
        return -1;
    }
      
    private void jB_addElemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_addElemActionPerformed
        
        int selectedRow_build = jTable_TE.getSelectedRow();              
        if( selectedRow_build >=0 )
        {
            int relationElements = V_RELATION_ID.size();            
            if(relationElements >= ADAMS_GlobalParam.MAX_DIMENSION)
            {
                ADAMS_GlobalParam.optionPanel("A maximum of "+ADAMS_GlobalParam.MAX_DIMENSION+" Traffic Element per Relation.","WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);                           
                return;
            }
            
            int ID_TE_selected = ((Integer)(jTable_TE.getValueAt(selectedRow_build,0))).intValue();
            String Desc_TE_selected = ""+jTable_TE.getValueAt(selectedRow_build,1);
           
            int index_TE_Present = isPresent_ID(ID_TE_selected,V_RELATION_ID);
            if(index_TE_Present == -1) //ID_TE non presente e aggiungo.
            {
                //System.out.println("ADD -->"+Desc_TE_selected );
                V_RELATION_ID.addElement(new Integer(ID_TE_selected));
                V_RELATION_NAME.addElement(new String(Desc_TE_selected).trim());
                V_DETAIL.addElement(new Integer(1));
            }
            else // ID_TE
            {
                //System.out.println("NO ADD - is present -->"+Desc_TE_selected );
                ADAMS_GlobalParam.optionPanel("Your Relation already contains: "+ID_TE_selected+" "+Desc_TE_selected+".", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);                           
                return;
            }
                        
            int num_TE = V_RELATION_ID.size();
            
            //System.out.println("V_RELATION_ID.size() ==> "+num_TE );
            String symbol_rel = "::";
            String str_relName = "";
            for(int i=0; i<num_TE; i++)
            {
                if(i == (num_TE-1))
                    symbol_rel = "";
                
                 str_relName = str_relName+((new String( ((String)V_RELATION_NAME.elementAt(i)))).trim())+symbol_rel;
                 //System.out.println("buil Rel -->"+str_relName );
                
            }
            //System.out.println("Relazione  -->"+str_relName );
            jL_relation.setText(str_relName);
            jL_relation.setToolTipText(jL_relation.getText());
            
            if(num_TE > 1)
                jB_propert_RE.setEnabled(true);
            else
                jB_propert_RE.setEnabled(false);
        }
        else
            ADAMS_GlobalParam.optionPanel("Select an item from the list < Traffic Elements >.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);  
        
        ctrl_b_ok();
    }//GEN-LAST:event_jB_addElemActionPerformed

    private void jB_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_cancelActionPerformed
         close_Dialog();
    }//GEN-LAST:event_jB_cancelActionPerformed

    private void close_Dialog()
    {
        if(viewServer != null)
        {
            viewServer.setVisible(false);
            viewServer.dispose();
            viewServer = null;
        }
        
        setVisible(false);
        CONFIRM_RELATION = false;
        //dispose();
    }
    
    private void jB_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_okActionPerformed
       
        if(ADAMS_GlobalParam.ctrl_LOCK_TABLE(false) == false)
            return;
        
        if(jT_desc.getText().trim().length() == 0)
        {
            ADAMS_GlobalParam.optionPanel("Item Descritpion cannot be empty.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }    
        
        if(timeFractionElementId == -1)
        {
            ADAMS_GlobalParam.optionPanel("Select an item from the list < Traffic Elements > and click 'Set Time Fraction Element' button to select aggregation period.", "WARNING",javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        setVisible(false);
        CONFIRM_RELATION = true;
    }//GEN-LAST:event_jB_okActionPerformed

    public int getServerID()
    {
        String serverID = (String)jCBox_Server.getSelectedItem();
        if(serverID.compareTo(str_none) == 0)
            return -1;
        
        return new Integer(serverID).intValue();
    }
    
    public int get_timeFractionElementId()
    {
        return timeFractionElementId;
    }
   
    public boolean get_ConfirmFreeReleation()
    {
        return CONFIRM_RELATION;
    }
    
    public Vector get_V_RELATION_NAME()
    {
        return V_RELATION_NAME;
    }
    
    public Vector get_V_RELATION_ID()
    {
        return V_RELATION_ID;
    }
    
    public Vector get_V_DETAIL()
    {
        return V_DETAIL;
    }
    
    public String getDescription()
    {
        return jT_desc.getText().trim();
    }
    
    private void ctrl_b_ok()
    {
        int num_TE = V_RELATION_ID.size();
        
        if( (num_TE >= 2)&&(CountersKit_ID_selected != -1) )
        {
            jB_ok.setEnabled(true);
        }
        else
        {
            jB_ok.setEnabled(false);            
        }            
    }
    
    
    private void jB_remLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_remLastActionPerformed
        
        //System.out.println(" remove Last TE ");
        
        int num_TE = V_RELATION_ID.size();
        if(num_TE > 0)
        {
            V_RELATION_ID.removeElementAt(num_TE-1);
            V_RELATION_NAME.removeElementAt(num_TE-1);
            V_DETAIL.removeElementAt(num_TE-1);

            num_TE = V_RELATION_ID.size();

            String symbol_rel = "::";
            String str_relName = "";
            for(int i=0; i<num_TE; i++)
            {
                if(i == (num_TE-1))
                    symbol_rel = "";

                str_relName = str_relName+((new String( ((String)V_RELATION_NAME.elementAt(i)))).trim())+symbol_rel;
                //System.out.println("buil Rel -->"+str_relName );

            }
            //System.out.println("Relazione  -->"+str_relName );
            jL_relation.setText(str_relName);
            jL_relation.setToolTipText(jL_relation.getText());
        }
        
        
        if(num_TE > 1)
            jB_propert_RE.setEnabled(true);
        else
            jB_propert_RE.setEnabled(false);
        
        
        ctrl_b_ok();
    }//GEN-LAST:event_jB_remLastActionPerformed
    
    private void jCBox_CountersTagActionPerformed(java.awt.event.ActionEvent evt) 
    {
        int indexSelected = jCBox_CountersTag.getSelectedIndex();
                
        CountersKit_ID_selected = -1;
        if(indexSelected <= 0)
            jL_IDCounter.setText("");
        else
        {            
            CountersKit_ID_selected = ((Integer)(V_CountersKit_ID.elementAt(indexSelected-1))).intValue();
            jL_IDCounter.setText(""+CountersKit_ID_selected);
        }
        
        //System.out.println("CountersKit_ID_selected --> "+CountersKit_ID_selected);
      
        enabled_eval_Script();
        ctrl_b_ok();
    }
        
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
       close_Dialog();
    }//GEN-LAST:event_closeDialog

    //private void jTable_TEMouseReleased(java.awt.event.MouseEvent evt) {}

    //////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames;
        MyTableModel(String[] all_columnNames)
        {
            columnNames = all_columnNames;
        }

        private Object[][] data = {};   
        
        public void setColumnName(String[] all_columnNames)
        {
            columnNames = all_columnNames;
        }
        
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
    private javax.swing.JPanel jP_TOT;
    private javax.swing.JLabel jL_title;
    private javax.swing.JPanel jP_center;
    private javax.swing.JScrollPane jScrollPane_TE;
    private javax.swing.JPanel jP_button_build;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jB_addElem;
    private javax.swing.JButton jB_remLast;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jB_T_FractionEl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea jTA_help;
    private javax.swing.JPanel jPanel_south;
    private javax.swing.JPanel jP_sc;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jB_propert_RE;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jL_relation;
    private javax.swing.JTextField jT_desc;
    private javax.swing.JLabel jL_ID_REL;
    private javax.swing.JLabel jL_CKit;
    private javax.swing.JPanel jP_c3;
    private javax.swing.JLabel jL_IDCounter;
    private javax.swing.JComboBox jCBox_CountersTag;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jL_evalScr;
    private javax.swing.JPanel jP_evalScr;
    private javax.swing.JTextField jText_evalScr;
    private javax.swing.JButton jB_evalScr;
    private javax.swing.JLabel jL_Plugin;
    private javax.swing.JPanel jP_plugin;
    private javax.swing.JTextField jText_pluginName;
    private javax.swing.JButton jB_pluginName;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jP_TFract;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jL_ID_TFractionE;
    private javax.swing.JLabel jL_desc_TFractioE;
    private javax.swing.JLabel jL1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jB_copy;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox jCBox_Server;
    private javax.swing.JLabel jL_server;
    private javax.swing.JButton jB_ViewServer;
    private javax.swing.JPanel jP_ss;
    private javax.swing.JPanel jP_sn;
    private javax.swing.JButton jB_ok;
    private javax.swing.JButton jB_cancel;
    // End of variables declaration//GEN-END:variables

    private boolean DEBUG = false;
    private String str_help_add = null;
        
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTable jTable_TE;
    private TableSorter sorter_TE;
    private MyTableModel myTableModel_TE;
    private String[] all_columnNames ={"ID","TAG","DESCRIPTION"};
    private int [] cellDimen_TE ={30,150,250};
    
    private Vector V_RELATION_NAME = new Vector(ADAMS_GlobalParam.MAX_DIMENSION);
    private Vector V_RELATION_ID   = new Vector(ADAMS_GlobalParam.MAX_DIMENSION);
    private Vector V_DETAIL        = new Vector(ADAMS_GlobalParam.MAX_DIMENSION);
    
    private boolean CONFIRM_RELATION = false;
    
    private ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG = null;
    private ADAMS_Vector_TAB_CONFIG parent_V_TAB_CONFIG = null;
    
    private int MODALITY = 0; // DEFAULT ADD.
    private int M_ADD = 0;
    private int M_MODIFY = 1;
    
    ADAMS_TAB_ALARM_RELATION row_AlarmRelation_Modify = null;

    private Vector V_CountersKit_ID = new Vector();
    private int CountersKit_ID_selected = -1;
    private int idConditionScript       = -1;
    private int AnswerCommitSCRIPT_Alarm = -1;
    
    private int timeFractionElementId = -1;    
    private String str_ConditionPlugin = "";
    
    private String strDefined       = "Defined";
    private String StrNotDefined    = "Not Defined";
    
    private java.awt.event.ActionListener event_jCBox_CountersTag = (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_CountersTagActionPerformed(evt);
            }
        });
               
    private String str_none = "< none >";
    private ADAMS_JF_ViewServerAlarm_DB viewServer = null;
}