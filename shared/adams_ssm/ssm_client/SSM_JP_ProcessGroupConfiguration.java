
import java.awt.Cursor;
import java.util.List;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
//import javax.swing.JOptionPane;

/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Luca Beltrame <luca.beltrame@e-tech.net>                                                                                                           
#                                                                                                                                                 
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory                                                                    
#                                                                                                                                                 
#  HISTORY:                                                                                                                                       
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/
public class SSM_JP_ProcessGroupConfiguration extends javax.swing.JPanel {

    /**
     * Creates new form SSM_JP_ProcessGroupConfiguration
     */
    public SSM_JP_ProcessGroupConfiguration(javax.swing.JFrame parent) 
    {   
        this.frame_parent = parent;
        initComponents();
        
        jCBL_process.setEnabled(false);
        jB_APPLY.setEnabled(false);
        
        jCBL_groups.setCursor(Cur_hand);
        jCBL_process.setCursor(Cur_hand);
        jB_add.setCursor(Cur_hand);
        jB_mod.setCursor(Cur_hand);
        jB_del.setCursor(Cur_hand);
        
        ///// PROCESS_GROUPS ////               
        loadProcessGroups();
    }
    
    public SSM_JP_ProcessGroupConfiguration(SSM_JP_NodeConfiguration nodeCofiguration, javax.swing.JFrame parent) 
    {
        this.frame_parent = parent;
        this.parent_nodeCofiguration = nodeCofiguration;
                
        initComponents();
        
        jCBL_process.setEnabled(false);
        jB_APPLY.setEnabled(false);
        
        jCBL_groups.setCursor(Cur_hand);
        jCBL_process.setCursor(Cur_hand);
        jB_add.setCursor(Cur_hand);
        jB_mod.setCursor(Cur_hand);
        jB_del.setCursor(Cur_hand);
        
        ///// PROCESS_GROUPS ////               
        loadProcessGroups();     
    }
    
    private void loadProcessGroups()
    {        
        this.setCursor(Cur_wait);
        jCBL_groups.setCursor(Cur_wait);
        
        v_Process_groups.readPreocessGroups();        
        v_cb_group.removeAllElements();   

        buttonGroup_jckGroups = new javax.swing.ButtonGroup();
        
        jCBL_groups.removeMouseListener(event_jCBL_groups);
        for(int i=0; i<v_Process_groups.size(); i++)
        {            
            String group_name = ((SSM_TAB_PROCESS_GROUPS)v_Process_groups.elementAt(i)).getGroup_name();
            
            JCheckBox cb1 = new JCheckBox(group_name);
            cb1.setFont(new java.awt.Font("Dialog", 1, 18));
            
            cb1.setMargin(new java.awt.Insets(8, 8, 8, 8));
            cb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
            cb1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
                               
            buttonGroup_jckGroups.add(cb1);             
            v_cb_group.addElement(cb1);
        }        
        jCBL_groups.addMouseListener(event_jCBL_groups);
        
        jCBL_groups.removeAll();  
        jCBL_groups.setListData(v_cb_group);        
        jCBL_groups.setLayoutOrientation(jCBL_groups.VERTICAL_WRAP); //HORIZONTAL_WRAP //VERTICAL_WRAP // VERTICAL 
        //jCBL_groups.setVisibleRowCount(10); 
        jScroll_groups.setViewportView(jCBL_groups);
        
        setAllSelectedCheckProcess(false);
        jCBL_process.setEnabled(false);
        jB_APPLY.setEnabled(false);
        
        this.setCursor(Cur_default);
        jCBL_groups.setCursor(Cur_default);
    }
    
    
    private void loadProcess()
    {
        this.setCursor(Cur_wait);
        jCBL_process.setCursor(Cur_wait);
        
        v_T_PROCESS.readProcess();
        v_cb_process.removeAllElements();
        
        jCBL_process.removeMouseListener(event_jCBL_process);        
        for(int i=0; i<v_T_PROCESS.size(); i++)
        {
            String t_process_name = ((SSM_TAB_T_PROCESS)v_T_PROCESS.elementAt(i)).getProcess_name();
            
            JCheckBox cb = new JCheckBox(t_process_name);
            cb.setFont(new java.awt.Font("Dialog", 1, 18));
            
            cb.setMargin(new java.awt.Insets(8, 8, 8, 8));
            cb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
            cb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
            
            v_cb_process.addElement(cb);
        }
        jCBL_process.addMouseListener(event_jCBL_process);
        
        jCBL_process.removeAll();
        jCBL_process.setListData(v_cb_process);        
        jCBL_process.setLayoutOrientation(jCBL_process.VERTICAL);        
        jScroll_process.setViewportView(jCBL_process);
        
        this.setCursor(Cur_default);
        jCBL_process.setCursor(Cur_default);
        //////////////////////////////
    }
    
    public void loadProcessAndReset()
    {
        this.setCursor(Cur_wait);
        System.out.println("loadProcessAndReset");
        
        loadProcess();        
        
        buttonGroup_jckGroups.clearSelection();
        jCBL_process.clearSelection();
        jCBL_process.setEnabled(false);
        jB_APPLY.setEnabled(false);

        
        this.setCursor(Cur_default);
        //////////////////////////////
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jB_add = new javax.swing.JButton();
        jB_mod = new javax.swing.JButton();
        jB_del = new javax.swing.JButton();
        jP_groups = new javax.swing.JPanel();
        jScroll_groups = new javax.swing.JScrollPane();
        jP_process = new javax.swing.JPanel();
        jScroll_process = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jB_APPLY = new javax.swing.JButton();

        setBackground(new java.awt.Color(145, 181, 255));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 60));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 60));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("GROUPS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 1, 6);
        jPanel1.add(jLabel1, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(200, 50));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 2, 2));

        jB_add.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_add.setText("ADD");
        jB_add.setToolTipText("Add group");
        jB_add.setFocusPainted(false);
        jB_add.setMargin(null);
        jB_add.setMaximumSize(new java.awt.Dimension(40, 40));
        jB_add.setMinimumSize(new java.awt.Dimension(70, 40));
        jB_add.setPreferredSize(new java.awt.Dimension(70, 40));
        jB_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_addActionPerformed(evt);
            }
        });
        jPanel2.add(jB_add);

        jB_mod.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_mod.setText("MOD");
        jB_mod.setToolTipText("Modify group");
        jB_mod.setFocusPainted(false);
        jB_mod.setMargin(null);
        jB_mod.setMinimumSize(new java.awt.Dimension(70, 40));
        jB_mod.setPreferredSize(new java.awt.Dimension(70, 40));
        jB_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_modActionPerformed(evt);
            }
        });
        jPanel2.add(jB_mod);

        jB_del.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_del.setText("DEL");
        jB_del.setToolTipText("Delete group");
        jB_del.setFocusPainted(false);
        jB_del.setMargin(null);
        jB_del.setMinimumSize(new java.awt.Dimension(70, 40));
        jB_del.setPreferredSize(new java.awt.Dimension(70, 40));
        jB_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_delActionPerformed(evt);
            }
        });
        jPanel2.add(jB_del);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        jP_groups.setBackground(new java.awt.Color(145, 181, 255));
        jP_groups.setMinimumSize(new java.awt.Dimension(400, 180));
        jP_groups.setPreferredSize(new java.awt.Dimension(400, 180));
        jP_groups.setLayout(new java.awt.CardLayout());
        jP_groups.add(jScroll_groups, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.3;
        add(jP_groups, gridBagConstraints);

        jP_process.setBackground(new java.awt.Color(145, 181, 255));
        jP_process.setMinimumSize(new java.awt.Dimension(400, 400));
        jP_process.setPreferredSize(new java.awt.Dimension(400, 400));
        jP_process.setLayout(new java.awt.BorderLayout());
        jP_process.add(jScroll_process, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        add(jP_process, gridBagConstraints);

        jPanel3.setMinimumSize(new java.awt.Dimension(400, 60));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 60));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Process Group");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 1, 6);
        jPanel3.add(jLabel2, gridBagConstraints);

        jPanel4.setMinimumSize(new java.awt.Dimension(70, 50));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(70, 50));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 3, 2));

        jB_APPLY.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jB_APPLY.setText("APPLY");
        jB_APPLY.setToolTipText("Delete group");
        jB_APPLY.setFocusPainted(false);
        jB_APPLY.setMargin(null);
        jB_APPLY.setMaximumSize(new java.awt.Dimension(70, 40));
        jB_APPLY.setMinimumSize(new java.awt.Dimension(70, 40));
        jB_APPLY.setPreferredSize(new java.awt.Dimension(70, 40));
        jB_APPLY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_APPLYActionPerformed(evt);
            }
        });
        jPanel4.add(jB_APPLY);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 2);
        jPanel3.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    private void jB_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_addActionPerformed
        
       //javax.swing.JOptionPane op1 = new javax.swing.JOptionPane();
       //String group_name = (String)op1.showInputDialog(null,"Insert the name of the Process Group.","Add Process Group",javax.swing.JOptionPane.PLAIN_MESSAGE,null,null,"");
        
        JD_Message input_message = null;
        input_message = new JD_Message(frame_parent,true,"Insert the name of the Process Group.","Add Process Group",input_message.INPUT_QUESTION);
        String group_name = input_message.getInputText();
        
        if(group_name != null)
        {
            
            if(group_name.isEmpty() == false)
            {          
                JD_Message message = null;
                
                if( v_Process_groups.getProcess_group_id(group_name) == -1)
                {
                    System.out.println("Add name group" +group_name);
                    SSM_TAB_PROCESS_GROUPS tpg_add = new SSM_TAB_PROCESS_GROUPS();                                        
                    int Answer_Insert =tpg_add.insert_Process_Groups(group_name);
                    
                    if(Answer_Insert == 1)
                    {
                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Process Group '"+group_name+"' has been added.","Add Process Group",JOptionPane.INFORMATION_MESSAGE);
                        
                        message = new JD_Message(frame_parent,true,"Process Group '"+group_name+"' has been added.","Add Process Group",message.INFO);
                        
                        loadProcessGroups();
                        
                        if (parent_nodeCofiguration != null)
                            parent_nodeCofiguration.loadProcessGroups(v_Process_groups);
                        
                    }
                    else{
                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Cannot insert Process Group '"+group_name+"'.","Add Process Group",JOptionPane.ERROR_MESSAGE);
                        
                        message = new JD_Message(frame_parent,true,"Cannot insert Process Group '"+group_name+"'.","Add Process Group",message.CRITICAL);
                    }
                         
                        
                }
                else
                {
                   //JOptionPane op = new JOptionPane();
                   //op.showMessageDialog(null,"Operation Failed, the name of '"+group_name+"' Group exists.","Add group",JOptionPane.WARNING_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Operation Failed, the name of '"+group_name+"' Group exists.","Add Process Group",message.WARNING);
                }
            }
        }
        
    }//GEN-LAST:event_jB_addActionPerformed

    private void jB_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_modActionPerformed
       
        String group_name_sel = null;
        for(int i=0; i<v_cb_group.size(); i++)
        {
            JCheckBox cbg = (JCheckBox)v_cb_group.elementAt(i);
            if(cbg.isSelected())
                group_name_sel = cbg.getText();
        }

        if(group_name_sel != null)
        {             
            //javax.swing.JOptionPane op_in = new javax.swing.JOptionPane();
            //String newgroup_name = (String)op_in.showInputDialog(null,"Modify the name of the group:","Modify group",javax.swing.JOptionPane.PLAIN_MESSAGE,null,null,group_name_sel);

            JD_Message input_message = null;
            input_message = new JD_Message(frame_parent,true,"Modify the name of the group:","Modify Process Group",group_name_sel);
            String newgroup_name = input_message.getInputText();
            
            if(newgroup_name != null)
            {
                if((newgroup_name.isEmpty() == false) && (newgroup_name.compareTo(group_name_sel) != 0))
                {
                    int process_group_id = v_Process_groups.getProcess_group_id(group_name_sel);
                    
                    JD_Message message = null;
                    
                    if( process_group_id != -1)
                    {
                        SSM_TAB_PROCESS_GROUPS tpg_mod = new SSM_TAB_PROCESS_GROUPS();                      
                        int Answer_update = tpg_mod.update_Process_Groups(newgroup_name,process_group_id);

                        if(Answer_update >=0)
                        {
                            //JOptionPane op = new JOptionPane();
                            //op.showMessageDialog(null,"Group '"+group_name_sel+"' has been changed in '"+newgroup_name+"'.","Modify Process group",JOptionPane.INFORMATION_MESSAGE);
                            
                            message = new JD_Message(frame_parent,true,"Group '"+group_name_sel+"' has been changed in '"+newgroup_name+"'.","Modify Process Group",message.INFO);
                            
                            loadProcessGroups();
                            
                            if (parent_nodeCofiguration != null)
                                parent_nodeCofiguration.loadProcessGroups(v_Process_groups);
                        }
                        else
                        {
                            //JOptionPane op = new JOptionPane();
                            //op.showMessageDialog(null,"Operation failed.","Modify Process group",JOptionPane.ERROR_MESSAGE);
                            
                            message = new JD_Message(frame_parent,true,"Operation failed.","Modify Process Group",message.CRITICAL);
                        }
                    }
                    else
                    {
                        //JOptionPane op = new JOptionPane();
                        //op.showMessageDialog(null,"Operation Failed, the name of '"+newgroup_name+"' group exists.","Modify group",JOptionPane.WARNING_MESSAGE);
                        
                        message = new JD_Message(frame_parent,true,"Operation Failed, the name of '"+newgroup_name+"' group exists.","Modify Process Group",message.WARNING);
                    }
                }
                else
                    System.out.println("Identical Name to the previous");

            }
        }
    }//GEN-LAST:event_jB_modActionPerformed

    private void jB_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_delActionPerformed
        this.setCursor(Cur_wait);
        jCBL_groups.setCursor(Cur_wait);
        
        String group_name_sel = null;
        for(int i=0; i<v_cb_group.size(); i++)
        {
            JCheckBox cbg = (JCheckBox)v_cb_group.elementAt(i);
            if(cbg.isSelected())
                group_name_sel = cbg.getText();
        }
        if(group_name_sel != null)
        { 
            JD_Message message = null;
            
            int process_group_id = v_Process_groups.getProcess_group_id(group_name_sel);
            
            /// ************
            SSM_Vector_TAB_T_NODE vTNode = new SSM_Vector_TAB_T_NODE();
            vTNode.readNodes();
            int Num_node_group = vTNode.num_NodeWithProcessGroupId(process_group_id);
            if(Num_node_group > 0)
            {
                //JOptionPane op = new JOptionPane();
                //op.showMessageDialog(null,"Attention, delete is not allowed. \nThere are nodes with this group ("+Num_node_group+").","Node with Process Group id.",JOptionPane.WARNING_MESSAGE);
                
                message = new JD_Message(frame_parent,true,"Attention, delete is not allowed. \nThere are nodes with this group ("+Num_node_group+").","Node with Process Group id.",message.WARNING);
                
                this.setCursor(Cur_default);
                jCBL_groups.setCursor(Cur_default);
                return;
            }
             /// ************
            
            //JOptionPane op_YES_NO = new JOptionPane();
            //int YES_NO = op_YES_NO.showConfirmDialog(null,"Are you sure to delete '"+group_name_sel+"' group?","Delete group",JOptionPane.YES_NO_OPTION);

            message = new JD_Message(frame_parent,true,"Are you sure to delete '"+group_name_sel+"' group?","Delete group",message.QUESTION_YES_NO);
            
            if(message.YES_NO == 1) 
            {
                System.out.println("delete --> " +group_name_sel);
                SSM_TAB_PROCESS_GROUPS tpg_del = new SSM_TAB_PROCESS_GROUPS();                                     


                int answer_del = tpg_del.delete_Process_Groups(process_group_id);
                if(answer_del >= 0)
                {
                    //JOptionPane op = new JOptionPane();                    
                    //op.showMessageDialog(null,"Group Name '"+group_name_sel+"' has been deleted.","Delete group",JOptionPane.INFORMATION_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Group Name '"+group_name_sel+"' has been deleted.","Delete Process Group",message.INFO);
                    
                    loadProcessGroups();
                    
                    if (parent_nodeCofiguration != null)
                            parent_nodeCofiguration.loadProcessGroups(v_Process_groups);
                }
                else{
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation failed.","Delete group",JOptionPane.ERROR_MESSAGE);
                    
                    message = new JD_Message(frame_parent,true,"Operation failed.","Delete Process Group",message.CRITICAL);
                }
            }
        }
        this.setCursor(Cur_default);
        jCBL_groups.setCursor(Cur_default);
    }//GEN-LAST:event_jB_delActionPerformed

    private void jB_APPLYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_APPLYActionPerformed
        
        this.setCursor(Cur_wait);
        jB_APPLY.setCursor(Cur_wait);
         
        // DEBUG Process NOT Selected
        /* System.out.println("---------");
        List listJCheckBoxNoSelected = jCBL_process.getCheckedItems(false);
        String[] nameProcessNoSelected = new String[listJCheckBoxNoSelected.size()];
        for (int i = 0; i<listJCheckBoxNoSelected.size(); ++i) 
        {
            nameProcessNoSelected[i] = ((JCheckBox)listJCheckBoxNoSelected.get(i)).getText();
            System.out.println(nameProcessNoSelected[i]+"-- False");
        }
        System.out.println("---------");
        */
        //////
        
         // DEBUG Process Selected
        System.out.println("---------");
        List listJCheckBoxSelected2 = jCBL_process.getCheckedItems(true);
        String[] nameProcessSelected2 = new String[listJCheckBoxSelected2.size()];
        for (int i = 0; i<listJCheckBoxSelected2.size(); ++i) 
        {
            nameProcessSelected2[i] = ((JCheckBox)listJCheckBoxSelected2.get(i)).getText();
            System.out.println(nameProcessSelected2[i] +"--> true");
        }
        System.out.println("---------");
        //////
        
        
        javax.swing.JCheckBox jcb_group = (javax.swing.JCheckBox)jCBL_groups.getSelectedValue();
        if(jcb_group != null)
        {
            if(jcb_group.isSelected())
            {
                String group_name = jcb_group.getText();
                int process_group_id = v_Process_groups.getProcess_group_id(group_name);
                
                SSM_Vector_TAB_PROCESS_GROUP_MEMBERS v_Process_group_members = new SSM_Vector_TAB_PROCESS_GROUP_MEMBERS();
                
                int answer = 0;
                // Process Selected
                List listJCheckBoxSelected = jCBL_process.getCheckedItems(true);
                if(listJCheckBoxSelected.size() > 0)
                {
                    String[] nameProcessSelected = new String[listJCheckBoxSelected.size()];
                    for (int i = 0; i<listJCheckBoxSelected.size(); ++i) 
                    {
                        nameProcessSelected[i] = ((JCheckBox)listJCheckBoxSelected.get(i)).getText();
                        int process_id = v_T_PROCESS.getProcess_id(nameProcessSelected[i]);                    
                        SSM_TAB_PROCESS_GROUP_MEMBERS pgm = new  SSM_TAB_PROCESS_GROUP_MEMBERS(process_group_id,process_id);

                        v_Process_group_members.addElement(pgm);                    
                    }
                    //////
                    answer = v_Process_group_members.insert_Process_Group_Members();
                }
                else
                    answer = v_Process_group_members.delete_All_Process_Group_Members(process_group_id);
                    
                if(answer >= 0)
                {
                    jB_APPLY.setEnabled(false);
                }
                else
                {                    
                    //JOptionPane op = new JOptionPane();
                    //op.showMessageDialog(null,"Operation failed.","ADD Process group members",JOptionPane.ERROR_MESSAGE);
                    JD_Message message = null;
                    message = new JD_Message(frame_parent,true,"Operation failed.","ADD Process group members",message.CRITICAL);
                }
            }
        }
        
        this.setCursor(Cur_default);
        jB_APPLY.setCursor(Cur_default);
        
    }//GEN-LAST:event_jB_APPLYActionPerformed
   
    private void jCBL_groupsMouseReleased(java.awt.event.MouseEvent evt) 
    {
        if(jCBL_groups.isEnabled())
        {
            System.out.println("jCBL_groupsMouseReleased");

            this.setCursor(Cur_wait);
            jCBL_groups.setCursor(Cur_wait);

            javax.swing.JCheckBox jcbg_appo = (javax.swing.JCheckBox)jCBL_groups.getSelectedValue();
            if(jcbg_appo != null)
            {        
                jCBL_process.setEnabled(true);
                System.out.println(jcbg_appo.getText()+" "+jcbg_appo.isSelected());
                jCBL_groups.repaint();

                if(jcbg_appo.isSelected())
                {
                    String group_name = jcbg_appo.getText();
                    int process_group_id = v_Process_groups.getProcess_group_id(group_name);

                    loadProcessGroupMembers(process_group_id);
                }
            }
            else
            {
                setAllSelectedCheckProcess(false);
                jCBL_process.setEnabled(false);
                jB_APPLY.setEnabled(false);
            }
            this.setCursor(Cur_default);
            jCBL_groups.setCursor(Cur_default);
        }
    }
    
    private void loadProcessGroupMembers(int processGroupId)
    {
        setAllSelectedCheckProcess(false);
        SSM_Vector_TAB_PROCESS_GROUP_MEMBERS v_Process_group_members = new SSM_Vector_TAB_PROCESS_GROUP_MEMBERS();
        v_Process_group_members.readProcess_Group_Members(processGroupId);

        for(int i=0; i<v_Process_group_members.size(); i++)
        {
            SSM_TAB_PROCESS_GROUP_MEMBERS processGroupMembers = (SSM_TAB_PROCESS_GROUP_MEMBERS)v_Process_group_members.elementAt(i);                
            setSelectedCheckProcess(processGroupMembers.getProcess_id(),true);
        }
    }
    
    private void setAllSelectedCheckProcess(boolean flag)
    {
        for( int i=0; i<v_cb_process.size(); i++)
        {
            JCheckBox jcprocess = (JCheckBox)v_cb_process.elementAt(i);
            jcprocess.setSelected(flag);
        }
        jCBL_process.repaint();
    }
    
    
    private void setSelectedCheckProcess(int process_id,boolean flag)
    {
        String process_name = v_T_PROCESS.getProcess_name(process_id);       
        for( int i=0; i<v_cb_process.size(); i++)
        {
            JCheckBox jcprocess = (JCheckBox)v_cb_process.elementAt(i);
            
            if(jcprocess.getText().equalsIgnoreCase(process_name))
                jcprocess.setSelected(flag);
        }
        jCBL_process.repaint();
    }
    
    
    private void jCBL_processMouseReleased(java.awt.event.MouseEvent evt) 
    {   
        if(jCBL_process.isEnabled())       
            jB_APPLY.setEnabled(true);
        
        /*if(jCBL_process.isEnabled())
        {
            System.out.println("jCBL_processMouseReleased");

            this.setCursor(Cur_wait);
            jCBL_groups.setCursor(Cur_wait);

            javax.swing.JCheckBox jcbg_appo = (javax.swing.JCheckBox)jCBL_groups.getSelectedValue();
            if(jcbg_appo == null)
            {
                this.setCursor(Cur_default);
                jCBL_groups.setCursor(Cur_default);
                return;
            }

            if(jcbg_appo.isSelected())
            { 
                String group_name = jcbg_appo.getText();
                int process_group_id = v_Process_groups.getProcess_group_id(group_name);

                JCheckBox jcbp_appo = (JCheckBox)jCBL_process.getSelectedValue();        
                String process_name = jcbp_appo.getText();
                int process_id = v_T_PROCESS.getProcess_id(process_name);

                SSM_TAB_PROCESS_GROUP_MEMBERS pgm = new  SSM_TAB_PROCESS_GROUP_MEMBERS(process_group_id,process_id);

                if(jcbp_appo.isSelected())
                {
                    if(pgm.insert_Process_Group_members() <= 0)
                    {            
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null,"Operation failed.","ADD Process group members",JOptionPane.ERROR_MESSAGE);
                        jcbg_appo.setSelected(false);
                    }
                }
                else
                {
                    if(pgm.delete_Process_Group_members() <= 0)
                    {        
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null,"Operation failed.","DELETE Process group members",JOptionPane.ERROR_MESSAGE);
                        jcbg_appo.setSelected(true);
                    }
                }
            }
            this.setCursor(Cur_default);
            jCBL_groups.setCursor(Cur_default);
        }*/
    }       
         
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_APPLY;
    private javax.swing.JButton jB_add;
    private javax.swing.JButton jB_del;
    private javax.swing.JButton jB_mod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jP_groups;
    private javax.swing.JPanel jP_process;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScroll_groups;
    private javax.swing.JScrollPane jScroll_process;
    // End of variables declaration//GEN-END:variables
   
    private javax.swing.ButtonGroup buttonGroup_jckGroups;   
    private SSM_JCheckBoxList jCBL_groups = new SSM_JCheckBoxList();
    private java.awt.event.MouseAdapter event_jCBL_groups = (new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCBL_groupsMouseReleased(evt);
            }
        });
    
    private SSM_JCheckBoxList jCBL_process = new SSM_JCheckBoxList();
    private java.awt.event.MouseAdapter event_jCBL_process = (new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCBL_processMouseReleased(evt);
            }
        });
    
    private Vector v_cb_group = new Vector();
    private SSM_Vector_TAB_PROCESS_GROUPS  v_Process_groups = new SSM_Vector_TAB_PROCESS_GROUPS();

    private SSM_Vector_TAB_T_PROCESS v_T_PROCESS = new SSM_Vector_TAB_T_PROCESS();
    private Vector v_cb_process = new Vector();
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private SSM_JP_NodeConfiguration parent_nodeCofiguration = null;
    private javax.swing.JFrame frame_parent = null;
      
}
