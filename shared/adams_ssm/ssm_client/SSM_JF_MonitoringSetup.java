/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> PROCESS MONITOR client </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (HP Invent) Created on 01/07/2004 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 * Questa classe estende in javax.swing.JFrame ed implementa l'interfaccia Runnable.
 * La personalizzazione dell'oggetto SSM_JF_MonitoringSetup, ï¿½ stata necessaria per collocare tutti
 * quei componenti necessari alla selezione di parametri di selezione.
 * L'oggetto SSM_JF_MonitoringSetup ï¿½ implementato con un layout di tipo java.awt.GridBagLayout.
 * La classe implementa l'intefaccia runnable, con la ridefinizione dei metodi start() e run(), dove in quest'ultimo
 * viene definito il codice necessario all'aggiornamento delle informazioni dei processi relativi alla centrale selezionata.
 * La classe inoltre si prende carico di instanziare l'oggetto SSM_JF_MonitoringOutput con tutti i parametri di selezione
 * necessari.
 *
 *@see SSM_JF_MonitoringOutput
 */
import java.awt.Color;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_JF_MonitoringSetup extends javax.swing.JFrame implements Runnable {

    public int type = 0;
    private String  oldSwitch[]   = {"MIP1I","MIP2I","MIM1I","MIM2I","MIM3I","MIR1I","RMA1I","RMAUI","RMA1I","RMN1I","RMP3I"};
    /**
     * Costruttore della classe.
     * Il costruttore riceve in ingresso un'array di strutture di tipo DATA_CENTRALI, la quale 
     * contiene le informazioni relative alle centrali, parametro di selezione indispensabili all'interfaccia GUI.
     *
     *@param ALLcentrali array di strutture di tipo DATA_CENTRALI, contenente le informazioni che caraterizzano una centrale. 
     */
    public SSM_JF_MonitoringSetup(DATA_CENTRALI[] ALLcentrali,int type) 
    {
        this.Local_ALLcentrali = ALLcentrali;
        this.type=type;
        initComponents();       
        
        IcPool = new SSM_IconPool("/images/");
        listProcess = new SSM_JListIcon(IcPool);
        listProcess.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScroll_process.setViewportView(listProcess);
        
        jL_time.setText("Refresh time "+jSlider1.getValue()+" seconds.");
        
        
        /*
         //new String(ALLcentrali[i].Descrizione).trim();
             if ((SSM_GlobalParam.rmp3i).equals("YES")) {
                 
            		if (!((staticLib.centrali[i]).equals("RMP3I")))	// scarta tutto tranne RMP3I
            			continue;
			jListIconLevel.addElement("item_switch_off.png","item_switch_on.png",staticLib.centrali[i]);
			indiciSW_lista[count_SW_Active++] = i;
			//Ripulisco le centrali selezionate
			for(int j=0;j<staticLib.NUMERO_CENTRALI;j++)
				struct_params.Centrali[j]=0;
	                struct_params.Centrali[staticLib.idCentrali[indiciSW_lista[0]]] = 1;
            	}
            	else  {
            		if ((staticLib.centrali[i]).equals ("RMP3I"))	// scarta RMP3I
            			continue;
			jListIconLevel.addElement("item_switch_off.png","item_switch_on.png",staticLib.centrali[i]);
			indiciSW_lista[count_SW_Active++] = i;
            	}
         */
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
//
            jCB_switch.addItem(new String(ALLcentrali[i].Descrizione).trim());
        }
        
        /*if(this.type==SSM_GlobalParam.ORBIX)
        {
            jCB_switch.addItem("ALL PROCESS");
        }*/
        
        
        //Font Globali
        listProcess.set_Font(SSM_GlobalParam.font_P11);
        jL_title.setFont(SSM_GlobalParam.font_B12);
        jL_lprocess.setFont(SSM_GlobalParam.font_B12);
        jL_time.setFont(SSM_GlobalParam.font_B12); 
        //jB_searchProc.setFont(SSM_GlobalParam.font_B12);
        //Cursors
        jB_selectAll.setCursor(cursor_hand);
        jB_apply.setCursor(cursor_hand);
        jB_close.setCursor(cursor_hand);
        jCB_switch.setCursor(cursor_hand);
        listProcess.setCursor(cursor_hand);
        jSlider1.setCursor(cursor_hand);
        //jB_searchProc.setCursor(cursor_hand);
        
        getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        
        if(this.type==SSM_GlobalParam.ORBIX)
        {
            jSlider1.setMinimum(45);
            jSlider1.setValue(45);
        }
        
        pack();       
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCB_switch = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jL_lprocess = new javax.swing.JLabel();
        jB_selectAll = new javax.swing.JButton();
        jScroll_process = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jL_time = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jP_button = new javax.swing.JPanel();
        jB_apply = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setTitle("Process Monitor - Setup -");
        setBackground(new java.awt.Color(230, 230, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jL_title.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configuration.png")));
        jL_title.setText("Monitoring Setup");
        jL_title.setPreferredSize(new java.awt.Dimension(300, 32));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 0, 10, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jL_title, gridBagConstraints1);
        
        jLabel1.setText("Selected Switch:");
        jLabel1.setMaximumSize(new java.awt.Dimension(120, 18));
        jLabel1.setMinimumSize(new java.awt.Dimension(120, 18));
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 18));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 5, 2, 2);
        getContentPane().add(jLabel1, gridBagConstraints1);
        
        jCB_switch.setBackground(new java.awt.Color(230, 230, 230));
        jCB_switch.setMaximumSize(new java.awt.Dimension(32767, 20));
        jCB_switch.setMinimumSize(new java.awt.Dimension(100, 20));
        jCB_switch.setPreferredSize(new java.awt.Dimension(100, 20));
        jCB_switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_switchActionPerformed(evt);
            }
        });
        
        jCB_switch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_switchItemStateChanged(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 5);
        getContentPane().add(jCB_switch, gridBagConstraints1);
        
        jPanel1.setLayout(new java.awt.BorderLayout(2, 5));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(160, 22));
        jL_lprocess.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_lprocess.setText("List of Process");
        jPanel1.add(jL_lprocess, java.awt.BorderLayout.CENTER);
        
        jB_selectAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select_all.png")));
        jB_selectAll.setText("Select all");
        jB_selectAll.setToolTipText("Select all Processes");
        jB_selectAll.setBorderPainted(false);
        jB_selectAll.setContentAreaFilled(false);
        jB_selectAll.setFocusPainted(false);
        jB_selectAll.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jB_selectAll.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jB_selectAll.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_selectAll.setMinimumSize(new java.awt.Dimension(18, 18));
        jB_selectAll.setPreferredSize(new java.awt.Dimension(100, 18));
        jB_selectAll.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select_all_green.png")));
        jB_selectAll.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select_all_blu.png")));
        jB_selectAll.setEnabled(false);
        jB_selectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_selectAllActionPerformed(evt);
            }
        });
        
        jPanel1.add(jB_selectAll, java.awt.BorderLayout.EAST);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 5, 2, 5);
        getContentPane().add(jPanel1, gridBagConstraints1);
        
        jScroll_process.setMinimumSize(new java.awt.Dimension(300, 200));
        jScroll_process.setPreferredSize(new java.awt.Dimension(300, 220));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(0, 5, 2, 5);
        getContentPane().add(jScroll_process, gridBagConstraints1);
        
        jPanel3.setLayout(new java.awt.BorderLayout(0, 5));
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setPreferredSize(new java.awt.Dimension(10, 80));
        jL_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clock.png")));
        jPanel3.add(jL_time, java.awt.BorderLayout.NORTH);
        
        jSlider1.setBackground(new java.awt.Color(230, 230, 230));
        jSlider1.setFont(new java.awt.Font("Helvetica", 0, 10));
        jSlider1.setForeground(java.awt.Color.black);
        jSlider1.setMajorTickSpacing(20);
        jSlider1.setMaximum(120);
        jSlider1.setMinimum(5);
        jSlider1.setMinorTickSpacing(5);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(20);
        jSlider1.setPreferredSize(new java.awt.Dimension(450, 50));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        
        jPanel3.add(jSlider1, java.awt.BorderLayout.SOUTH);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 5, 2, 5);
        getContentPane().add(jPanel3, gridBagConstraints1);
        
        jSeparator2.setPreferredSize(new java.awt.Dimension(2, 2));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 6;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(2, 6, 2, 6);
        getContentPane().add(jSeparator2, gridBagConstraints1);
        
        jSeparator3.setPreferredSize(new java.awt.Dimension(2, 2));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(6, 5, 6, 5);
        getContentPane().add(jSeparator3, gridBagConstraints1);
        
        jP_button.setBackground(new java.awt.Color(230, 230, 230));
        jP_button.setMinimumSize(new java.awt.Dimension(10, 30));
        jP_button.setPreferredSize(new java.awt.Dimension(10, 30));
        jB_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.gif")));
        jB_apply.setBorderPainted(false);
        jB_apply.setContentAreaFilled(false);
        jB_apply.setFocusPainted(false);
        jB_apply.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_apply.setMaximumSize(new java.awt.Dimension(56, 27));
        jB_apply.setPreferredSize(new java.awt.Dimension(100, 22));
        jB_apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.gif")));
        jB_apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.gif")));
        jB_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_applyActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_apply);
        
        jB_close.setForeground(java.awt.Color.red);
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.gif")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setPreferredSize(new java.awt.Dimension(100, 22));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.gif")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.gif")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_button.add(jB_close);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 7;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jP_button, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jCB_switchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_switchActionPerformed
        // Add your handling code here:
        //setProcessforSwitch((String)jCB_switch.getSelectedItem());
        //System.out.println("SWITCH CHANGED");
        listProcess.removeAll();
        jB_selectAll.setEnabled(false);
        setProcessforSwitch((String)jCB_switch.getSelectedItem());
    }//GEN-LAST:event_jCB_switchActionPerformed

    private void jCB_switchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_switchItemStateChanged
        /*System.out.println("*******************+ eccoci");
        listProcess.removeAll();
        jB_selectAll.setEnabled(false);
        setProcessforSwitch((String)jCB_switch.getSelectedItem());*/
    }//GEN-LAST:event_jCB_switchItemStateChanged

    private void setProcessforSwitch(String SW)
    {

        if(listProcess != null)
        {
            SWITCH_SELECTED = SW;
            
            listProcess.removeAll();
            processDetails = null;
            
            jScroll_process.getVerticalScrollBar().setValue(0);
            jScroll_process.getHorizontalScrollBar().setValue(0);
            
            start();
        }
    }

    private void start()
    {    
        th = null;        
        th = new Thread(this,"refresh_MonitorProcess");
        th.start();       
    }

    /**
    * In questo metodo viene definito il codice del Thread.
    * Nel metodo viene effettuata la chiamata al metodo "refresh_MonitorProcess(...)" per richiedere le informazioni
    * relative ai processi della centrale di interesse, necessari alla selezione (mediante widget lista) per il monitoring; 
    * il metodo viene invocato mediante l'oggetto di tipo SSM_CORBAConnection dichiarato nella classe globale SSM_GlobalParam.
    *@see SSM_CORBAConnection
    *@see SSM_GlobalParam
    */
    public void run()
    {        
        this.setCursor(cursor_wait);
        setEnabled_GUI(false,cursor_wait);
        
        if(this.type==SSM_GlobalParam.NO_ORBIX)
        {
            processDetails = SSM_GlobalParam.CORBAConn.refresh_MonitorProcess(SWITCH_SELECTED,1);
        }
        else if(this.type==SSM_GlobalParam.ORBIX)
        {
            processDetails = SSM_GlobalParam.CORBAConn.refresh_MonitorProcess(SWITCH_SELECTED,2);
        }
        
        if(processDetails !=null)
        {
            //System.out.println("refresh_MonitorProcess processDetails.length => "+processDetails.length);
            jB_selectAll.setEnabled(true);
            for(int i=0; i<processDetails.length; i++)
            {     
                
                /*System.out.println("ID Processo         ==> "+processDetails[i].idProcesso);	
                System.out.println("Tipologia Processo  ==> "+new String(processDetails[i].tipoProcesso).trim());
                System.out.println("Nome Processo       ==> "+new String(processDetails[i].nomeProcesso).trim());
                System.out.println("Colore BK Processo  ==> "+new String(processDetails[i].colorBKnomeProcesso).trim());
	
                System.out.println();
                for(int x=0; x<processDetails[i].param.length; x++)
                {
                    System.out.println("Parametri di output ==> "+processDetails[i].param[x]+" indice==>"+x);
                }
                
                System.out.println("Status del Processo     ==> "+processDetails[i].statusProcesso);
                System.out.println("Numero di restart       ==> "+processDetails[i].numRestartTime);
                System.out.println("Flag di schedulazione   ==> "+processDetails[i].flagSched);
                System.out.println("Messaggio               ==> "+new String(processDetails[i].msgProcesso).trim());
                System.out.println("Colore Messaggio        ==> "+new String(processDetails[i].colorBKmsgProcesso).trim());*/
                
                
                String NomeProcesso=new String(processDetails[i].nomeProcesso).trim();
                int id=-1;
                
                if( (this.type==SSM_GlobalParam.ORBIX) && (SWITCH_SELECTED.compareTo("ALL PROCESS")!=0) )
                {
                    
                    //System.out.println("Nome Processo       ==> "+NomeProcesso);
                    
                    id=NomeProcesso.indexOf(SWITCH_SELECTED);
                    int posDot=NomeProcesso.lastIndexOf('.');
                    if(posDot!=-1)
                    {
                        String appoNomeProcesso=NomeProcesso.substring(0,posDot);
                        //System.out.println("appoNomeProcesso       ==> "+appoNomeProcesso);
                        posDot=appoNomeProcesso.lastIndexOf('.');
                    }
                    
                    if(posDot!=-1)
                    {
                        NomeProcesso=NomeProcesso.substring(posDot+1);
                    }
                    
                    
                    
                    
                    
                    //System.out.println("Nome Processo NEW       ==> "+NomeProcesso);
                    //System.out.println("ID ["+SWITCH_SELECTED+"]="+id);

                    if(SWITCH_SELECTED.compareTo("RDA")==0)
                    {
                        //System.out.println("CASO RDA");
                        int id_rda=-1;
                        boolean flag=false;
                        for(int j=0;j<oldSwitch.length;j++)
                        {
                            String appoSW=oldSwitch[j];
                            id_rda=NomeProcesso.indexOf(appoSW);
                            //System.out.println("id_rda ["+appoSW+"]="+id_rda);
                            if(id_rda!=-1)
                            {
                                flag=true;
                                break;
                            }
                        }

                        if(flag==true)
                        {
                            continue;
                        }

                    }else if(id==-1) 
                    {
                        continue;
                    }

                    /*String appoNP = new String(processDetails[i].nomeProcesso).trim();
                    id=appoNP.indexOf('.');
                    if(id==-1)
                    {
                        NomeProcesso=appoNP;
                    }
                    else 
                    {
                        NomeProcesso=appoNP.substring(id+1);
                    }   */
                }
                
                if( this.type==SSM_GlobalParam.NO_ORBIX )
                {
                
                    if(processDetails[i].param.length > 2)
                    {
                        if(processDetails[i].param[2] == 0)
                        {
                            listProcess.addElement("run_proc.png","run_proc.png","("+SWITCH_SELECTED+") - "+NomeProcesso,Color.black);
                        }
                        else
                        {
                            listProcess.addElement("stop_proc.png","stop_proc.png","("+SWITCH_SELECTED+") - "+NomeProcesso,Color.black);
                        }
                    }
                    else
                    {
                            listProcess.addElement("stop_proc.png","stop_proc.png","("+SWITCH_SELECTED+") - "+NomeProcesso,Color.black);
                            System.out.println("er... NOT Found III PARAM");
                    }
                }
                else
                {
                    if(processDetails[i].param.length > 2)
                    {
                        if(processDetails[i].param[2] == 0)
                        {
                            listProcess.addElement("run_proc.png","run_proc.png",""+NomeProcesso,Color.black);
                        }
                        else
                        {
                            listProcess.addElement("stop_proc.png","stop_proc.png",""+NomeProcesso,Color.black);
                        }
                    }
                    else
                    {
                            listProcess.addElement("stop_proc.png","stop_proc.png",""+NomeProcesso,Color.black);
                            System.out.println("er... NOT Found III PARAM");
                    }
                }

            }            
        }
        else
            System.out.println("******* processDetails[] is null ******** ");
        
        try
        {
        	//System.out.println("1 Repaint");
                        
            listProcess.validate();  
    		listProcess.updateUI();    		
    		jScroll_process.validate();       	
    		jScroll_process.setViewportView(listProcess);            
            
            //System.out.println("2 Repaint");
            

        }
        catch (Exception e)
        {
            System.out.println("catch refresh");
        }     
        
        setEnabled_GUI(true,cursor_hand);
        this.setCursor(cursor_default);
    }
    
    private void setEnabled_GUI(boolean flag,java.awt.Cursor curAppo)
    {        
        jCB_switch.setCursor(curAppo);
        jB_selectAll.setCursor(curAppo);
        jSlider1.setCursor(curAppo);
        jB_apply.setCursor(curAppo);
        jB_close.setCursor(curAppo);
        //jB_searchProc.setCursor(curAppo);
        listProcess.setCursor(curAppo);

        jLabel1.setEnabled(flag);    
        jCB_switch.setEnabled(flag);
        jL_lprocess.setEnabled(flag);
        jB_selectAll.setEnabled(flag);
        jL_time.setEnabled(flag);
        jSlider1.setEnabled(flag);
        jB_apply.setEnabled(flag);
        jB_close.setEnabled(flag);
        //jB_searchProc.setEnabled(flag);        
    }
    
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jL_time.setText("Refresh time "+jSlider1.getValue()+" seconds.");
    }//GEN-LAST:event_jSlider1StateChanged

    private void jB_selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_selectAllActionPerformed
        try
        {
            listProcess.setSelectedIndices(listProcess.getAllIndexs());
        }
        catch(Exception e)
        {
        }
    }//GEN-LAST:event_jB_selectAllActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        closeFrame();
    }//GEN-LAST:event_jB_closeActionPerformed

    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
         
        int[] selectedIndex = listProcess.getSelectedIndices();
        int num_selectedIndex = selectedIndex.length;    
        
        if(num_selectedIndex == 0)
        {
            javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
            warning.showMessageDialog(this,"No Process selected.","Information Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }      
        
        int[] ID_ProcessSelected = new int[num_selectedIndex];
        for(int i=0; i<num_selectedIndex; i++)
        {
            ID_ProcessSelected[i] = processDetails[selectedIndex[i]].idProcesso;            
            //System.out.println("ID e nome Processo Selezionato ==> "+ID_ProcessSelected[i]+" "+new String(processDetails[selectedIndex[i]].nomeProcesso).trim()); 
        }
        //System.out.println("Refresh time ==> "+jSlider1.getValue());
        
        
        if(this.type==SSM_GlobalParam.NO_ORBIX)
        {
            SSM_JF_MonitoringOutput Mon_output = new SSM_JF_MonitoringOutput(ID_ProcessSelected,SWITCH_SELECTED,jSlider1.getValue(),this.type);
            Mon_output.pack();
            //Mon_output.show();
            Mon_output.setVisible(true);
        }
        else if(this.type==SSM_GlobalParam.ORBIX)
        {
            SSM_JF_MonitoringOrbix Mon_output = new SSM_JF_MonitoringOrbix(ID_ProcessSelected,SWITCH_SELECTED,jSlider1.getValue(),this.type);
            Mon_output.pack();
            //Mon_output.show();
            Mon_output.setVisible(true);
        }

        
    }//GEN-LAST:event_jB_applyActionPerformed
    private void closeFrame()
    {
        this.dispose();
    }
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        //System.out.println("w="+this.getSize().getWidth() +" h="+this.getSize().getHeight());
        closeFrame();
    }//GEN-LAST:event_exitForm

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_title;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox jCB_switch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jL_lprocess;
    private javax.swing.JButton jB_selectAll;
    private javax.swing.JScrollPane jScroll_process;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jL_time;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel jP_button;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables

    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Cursor cursor_wait         = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor cursor_default      = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    private SSM_JListIcon listProcess            = null;
    private SSM_IconPool IcPool                  = null;
    
    private ProcesDetail[] processDetails       = null;
    private DATA_CENTRALI[] Local_ALLcentrali   = null;
    private String SWITCH_SELECTED              = "";
    private Thread th                           = null;
    
}
