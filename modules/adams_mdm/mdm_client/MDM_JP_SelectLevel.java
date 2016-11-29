import java.awt.Cursor;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Classe di interfaccia grafica destinata alle selezioni delle centrali.
 * La classe estende un JPanel, strutturata da una JListIcon per le selezioni.
 * La lista viene popolata da una struttura dati di tipo <b>DATA_CENTRALI</b>.
 * Inoltre la classe contiene widget atti alla selezione di analisi per singola centrale
 * o Netwwork.
 * <p align="center">&nbsp;</p>
 * @see DATA_CENTRALI
 * @see JListIcon
 */
public class MDM_JP_SelectLevel extends javax.swing.JPanel {

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore di classe:
     * Il seguente costrutture accetta in ingresso una classe necessaria per la popolazione
     * parametrica della lista di selezione.
     * </font></font></p></pre>
     * @param struct_params riferimento all'oggetto <b>STRUCT_PARAMS</b>,
     * @see STRUCT_PARAMS
     */
    private MDM_JF_Frame parent;
    public MDM_JP_SelectLevel(STRUCT_PARAMS StructParams,MDM_JF_Frame parent) {

        this.struct_params  = StructParams;
        this.parent=parent;
        
        initComponents();

        jListIconLevel = new JListIcon(ip);
        jScroll_List.setViewportView(jListIconLevel);

        struct_params.NetworkRealTime=0;
        staticLib.jrbMERGE = jrb_MergeRep; // Network Reports

        int count_SW_Active = 0;
        indiciSW_lista = new int[staticLib.centrali.length];
        for(int i=0;i<staticLib.centrali.length;i++) //Aggiungo le centrali
        {
            if(staticLib.idCentrali[i] != -1)
            {
            	/*if ((staticLib.rmp3i).equals("YES")) {
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
            	}*/
                
                
                // - SBC -                
               /* System.out.println("NameConfig "+staticLib.NameConfig);
                if(staticLib.NameConfig.equals("N.T.M. CDR SBC"))
                {
                    if(staticLib.centrali[i].equals("SBC") == true)
                    {
                        jListIconLevel.addElement("item_switch_off.png","item_switch_on.png",staticLib.centrali[i]);
                        indiciSW_lista[count_SW_Active++] = i;
                    }
                }
                else
                {
                    System.out.println("staticLib.centrali[i]= "+staticLib.centrali[i]);	
                    if( (staticLib.centrali[i].equals("SBC") == false) && (staticLib.centrali[i].equals("TSM0I") == false) )
                    {
                        jListIconLevel.addElement("item_switch_off.png","item_switch_on.png",staticLib.centrali[i]);
                        indiciSW_lista[count_SW_Active++] = i;
                    }
                }*/
                
                          
                jListIconLevel.addElement("item_switch_off.png","item_switch_on.png",staticLib.centrali[i]);
                indiciSW_lista[count_SW_Active++] = i;
                
                //Ripulisco le centrali selezionate
		for(int j=0;j<staticLib.NUMERO_CENTRALI;j++)
			struct_params.Fep[j] = 0;                 
                                 
            }

        }
        struct_params.Fep[staticLib.idCentrali[indiciSW_lista[0]]] = 1;
        
        jListIconLevel.setSelectedIndex(0);
	setDefaultCentrale();

        // Evento Lista
        jListIconLevel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        }
        );

        group.add(jrb_OnLine);
        group.add(jrb_Stored);
        jrb_OnLine.setSelected(true);

        //Cursor
        jrb_OnLine.setCursor(Cur_hand);
        jrb_Stored.setCursor(Cur_hand);
        jrb_MergeRep.setCursor(Cur_hand);
        jListIconLevel.setCursor(Cur_hand);

        //Font
        jrb_OnLine.setFont(staticLib.fontA10);
        jrb_Stored.setFont(staticLib.fontA10);
        jrb_MergeRep.setFont(staticLib.fontA10);
        jL_descList.setFont(staticLib.fontA10);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jL_descList = new javax.swing.JLabel();
        jScroll_List = new javax.swing.JScrollPane();
        jP_jrb = new javax.swing.JPanel();
        jrb_OnLine = new javax.swing.JRadioButton();
        jrb_Stored = new javax.swing.JRadioButton();
        jrb_MergeRep = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(183, 206, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Level ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11), new java.awt.Color(0, 0, 102))); // NOI18N
        setForeground(new java.awt.Color(0, 0, 102));
        setLayout(new java.awt.GridBagLayout());

        jL_descList.setForeground(new java.awt.Color(0, 0, 102));
        jL_descList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list.png"))); // NOI18N
        jL_descList.setText("List of Switcing Center");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jL_descList, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScroll_List, gridBagConstraints);

        jP_jrb.setBackground(new java.awt.Color(230, 230, 230));
        jP_jrb.setLayout(new java.awt.GridLayout(1, 0));

        jrb_OnLine.setBackground(new java.awt.Color(183, 206, 255));
        jrb_OnLine.setForeground(new java.awt.Color(0, 0, 102));
        jrb_OnLine.setText("On Line");
        jrb_OnLine.setToolTipText("On Line");
        jrb_OnLine.setContentAreaFilled(false);
        jrb_OnLine.setFocusPainted(false);
        jrb_OnLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jrb_OnLine.setOpaque(true);
        jrb_OnLine.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrb_OnLine.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jrb_OnLine.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrb_OnLine.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jrb_OnLine.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_OnLineItemStateChanged(evt);
            }
        });
        jP_jrb.add(jrb_OnLine);

        jrb_Stored.setBackground(new java.awt.Color(183, 206, 255));
        jrb_Stored.setForeground(new java.awt.Color(0, 0, 102));
        jrb_Stored.setText("Stored");
        jrb_Stored.setToolTipText("Stored");
        jrb_Stored.setContentAreaFilled(false);
        jrb_Stored.setFocusPainted(false);
        jrb_Stored.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jrb_Stored.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jrb_Stored.setOpaque(true);
        jrb_Stored.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrb_Stored.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jrb_Stored.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrb_Stored.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jrb_Stored.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrb_StoredItemStateChanged(evt);
            }
        });
        jP_jrb.add(jrb_Stored);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(jP_jrb, gridBagConstraints);

        jrb_MergeRep.setText("Network Analysis");
        jrb_MergeRep.setToolTipText("Network Analysis");
        jrb_MergeRep.setContentAreaFilled(false);
        jrb_MergeRep.setFocusPainted(false);
        jrb_MergeRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jrb_MergeRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif"))); // NOI18N
        jrb_MergeRep.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrb_MergeRep.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif"))); // NOI18N
        jrb_MergeRep.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif"))); // NOI18N
        jrb_MergeRep.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif"))); // NOI18N
        jrb_MergeRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_MergeRepActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(jrb_MergeRep, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jrb_StoredItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_StoredItemStateChanged
        if (jrb_Stored.isSelected())
        {
            struct_params.DataType=1;
            parent.ridisegnaCalendario(true);
        }
    }//GEN-LAST:event_jrb_StoredItemStateChanged

    private void jrb_OnLineItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrb_OnLineItemStateChanged
        if (jrb_OnLine.isSelected())
        {
            struct_params.DataType=0;
            parent.ridisegnaCalendario(false);
        }
    }//GEN-LAST:event_jrb_OnLineItemStateChanged

    private void jrb_MergeRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_MergeRepActionPerformed
        if(jrb_MergeRep.isSelected())
        {
            struct_params.NetworkRealTime=1;
            staticLib.NTenabled=true;
        }
        else
        {
            struct_params.NetworkRealTime=0;
            staticLib.NTenabled=false;
        }

        //Relation localRelation=new Relation(configuration.get_Relations_prova());
        //DATA_RELATIONS appoRel=localRelation.get_Relation_conId(struct_paramsOK.Relazione);

        boolean appoGhost=false;
        for(int i=0;i<staticLib.idGhostRel.length;i++)
        {
            if(staticLib.idGhostRel[i]==struct_params.Relation)
            {
                    appoGhost=true;
                    break;
            }
        }

        if(appoGhost==false)
        {
            //System.out.println("Da valutare MDM_JP_SelectLevel"); //nello
            staticLib.selectRelations1.resetRelations();
            staticLib.selectRestrictions1.resetRestrictions();
            staticLib.selectRestrictions1.current_idRelations = -2;  // nello DA vedere importante
        }
        else
        {
            staticLib.selectRestrictions1.current_idRelations=-1;
        }
    }//GEN-LAST:event_jrb_MergeRepActionPerformed


    private void setDefaultCentrale()
    {
	int[] n = jListIconLevel.getSelectedIndices();

            //Ripulisco le centrali selezionate
            for(int j=0;j<staticLib.NUMERO_CENTRALI;j++)
                struct_params.Fep[j]=0;

            //Imposto le centrali selezionate
            for(int j=0;j<n.length;j++)
            {
                struct_params.Fep[staticLib.idCentrali[indiciSW_lista[n[j]]]] = 1;
            }
    }

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        if (!evt.getValueIsAdjusting())
        {
            int[] n = jListIconLevel.getSelectedIndices();

            //Ripulisco le centrali selezionate
            for(int j=0;j<staticLib.NUMERO_CENTRALI;j++)
                struct_params.Fep[j]=0;

            //Imposto le centrali selezionate
            for(int j=0;j<n.length;j++)
            {
                struct_params.Fep[staticLib.idCentrali[indiciSW_lista[n[j]]]] = 1;
            }
        }

/*        for (int i=0; i<struct_params.Fep.length; i++)
        {
            System.out.println("Nome cen =>"+staticLib.centrali[i]+" attiva ==>"+staticLib.idCentrali[i]+" selezionata ==>"+struct_params.Fep[i]);
        }*/
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metoso ripristina la selezione della lista al valore di default.
     * il valore di default è il primo elemento della Lista "item(0)".
     * </font></font></p></pre>
     */
    public void resetLevel()
    {
        jListIconLevel.clearSelection();
        jListIconLevel.setSelectedIndex(0);
        jrb_MergeRep.setSelected(false);
        jrb_OnLine.setSelected(true);
        struct_params.NetworkRealTime=0;
        staticLib.NTenabled=false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_descList;
    private javax.swing.JPanel jP_jrb;
    private javax.swing.JScrollPane jScroll_List;
    private javax.swing.JRadioButton jrb_MergeRep;
    private javax.swing.JRadioButton jrb_OnLine;
    private javax.swing.JRadioButton jrb_Stored;
    // End of variables declaration//GEN-END:variables

    private javax.swing.ButtonGroup group       = new javax.swing.ButtonGroup();
    private IconPool ip                         = new IconPool("/images/");
    private JListIcon jListIconLevel            = null;

    private Cursor Cur_default      = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_hand         = new Cursor(Cursor.HAND_CURSOR);

    private STRUCT_PARAMS struct_params = null;
    private int[] indiciSW_lista;
}
