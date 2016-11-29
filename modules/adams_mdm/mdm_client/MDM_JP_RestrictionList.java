import java.awt.Cursor;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.*;
import com.sun.java.swing.plaf.windows.*;
import java.awt.event.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe che estende un JPanel, tiene traccia dei valori inseriti nelle varie restrizioni.
 * tutti i valori inseriti vengono graficati a video mediante una struttura JTree 
 * che preleva i dati da una struttura complessa ti tipo BufferRestrizioni
 *
 * <p align="center">&nbsp;</p> 
 * @see BufferRestrizioni
 */
public class MDM_JP_RestrictionList extends javax.swing.JPanel {

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo costruttore istanzia il pannello richiama la fuonzione che posiziona i widget collegandoli
     * hai vari eventi,e copia nella variabile locale <b>br</b> il riferimento alla struttura BufferRestrizioni
     * globale per tutto il client.
     * </font></font></p></pre>  
     * @param BR buffer restiozioni (BufferRestrizioni)
     */
    public MDM_JP_RestrictionList(BufferRestrizioni BR) 
    {
        this.br = BR;
        
        initComponents();
        
        initAlbero();
        jScrollPane1.setViewportView(jTree);
        
        //Cursor
        jrbExpand.setCursor(Cur_hand);
        jrbClose.setCursor(Cur_hand);
                
        //Font
        jL_help.setFont(staticLib.fontA9);
        jLabel2.setFont(staticLib.fontA9);
        jrbExpand.setFont(staticLib.fontA10);
        jrbClose.setFont(staticLib.fontA10);
    }   
    
    public MDM_JP_RestrictionList(BufferRestrizioni BR,boolean interactivePopup,boolean flagExpandTree) 
    {
        this.br = BR;
        
        initComponents();
        
        // --------
        this.interactive_Popup = interactivePopup;
        if(interactive_Popup == false)
            this.remove(jL_help);
        // --------

        initAlbero();
        jScrollPane1.setViewportView(jTree);
        
        if(flagExpandTree)
            expandTree(jTree);
        else
            collapseTree(jTree);
        
        //Cursor
        jrbExpand.setCursor(Cur_hand);
        jrbClose.setCursor(Cur_hand);
                
        //Font
        jL_help.setFont(staticLib.fontA9);
        jLabel2.setFont(staticLib.fontA9);
        jrbExpand.setFont(staticLib.fontA10);
        jrbClose.setFont(staticLib.fontA10);
    } 
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem_delete = new javax.swing.JMenuItem();
        jMenuItem_deleteAll = new javax.swing.JMenuItem();
        jL_help = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP_south = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jrbExpand = new javax.swing.JButton();
        jrbClose = new javax.swing.JButton();
        
        jMenuItem_delete.setBackground(new java.awt.Color(230, 230, 230));
        jMenuItem_delete.setText("Delete");
        jMenuItem_delete.setToolTipText("Delete Item");
        jMenuItem_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_deleteActionPerformed(evt);
            }
        });
        
        jPopupMenu1.add(jMenuItem_delete);
        jMenuItem_deleteAll.setBackground(new java.awt.Color(230, 230, 230));
        jMenuItem_deleteAll.setText("Delete all");
        jMenuItem_deleteAll.setToolTipText("Delete all items");
        jMenuItem_deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_deleteAllActionPerformed(evt);
            }
        });
        
        jPopupMenu1.add(jMenuItem_deleteAll);
        
        setLayout(new java.awt.BorderLayout());
        
        setBackground(new java.awt.Color(230, 230, 230));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jL_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        jL_help.setText("Press right mouse button for delete item");
        jL_help.setMaximumSize(new java.awt.Dimension(298, 22));
        jL_help.setMinimumSize(new java.awt.Dimension(298, 22));
        jL_help.setPreferredSize(new java.awt.Dimension(298, 22));
        add(jL_help, java.awt.BorderLayout.NORTH);
        
        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        
        jP_south.setLayout(new java.awt.BorderLayout());
        
        jP_south.setBackground(new java.awt.Color(230, 230, 230));
        jP_south.setPreferredSize(new java.awt.Dimension(80, 50));
        jLabel2.setText(" None");
        jP_south.add(jLabel2, java.awt.BorderLayout.NORTH);
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jrbExpand.setBackground(new java.awt.Color(230, 230, 230));
        jrbExpand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/expands_tree.jpg")));
        jrbExpand.setToolTipText("Expand tree");
        jrbExpand.setBorderPainted(false);
        jrbExpand.setContentAreaFilled(false);
        jrbExpand.setFocusPainted(false);
        jrbExpand.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrbExpand.setMaximumSize(new java.awt.Dimension(100, 22));
        jrbExpand.setMinimumSize(new java.awt.Dimension(100, 22));
        jrbExpand.setPreferredSize(new java.awt.Dimension(100, 22));
        jrbExpand.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/expands_tree_press.jpg")));
        jrbExpand.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/expands_tree_over.jpg")));
        jrbExpand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbExpandActionPerformed(evt);
            }
        });
        
        jPanel2.add(jrbExpand);
        
        jrbClose.setBackground(new java.awt.Color(230, 230, 230));
        jrbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_tree.jpg")));
        jrbClose.setToolTipText("Close tree");
        jrbClose.setBorderPainted(false);
        jrbClose.setContentAreaFilled(false);
        jrbClose.setFocusPainted(false);
        jrbClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrbClose.setMaximumSize(new java.awt.Dimension(100, 22));
        jrbClose.setMinimumSize(new java.awt.Dimension(100, 22));
        jrbClose.setPreferredSize(new java.awt.Dimension(100, 22));
        jrbClose.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_tree_press.jpg")));
        jrbClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_tree_over.jpg")));
        jrbClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCloseActionPerformed(evt);
            }
        });
        
        jPanel2.add(jrbClose);
        
        jP_south.add(jPanel2, java.awt.BorderLayout.CENTER);
        
        add(jP_south, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private void jMenuItem_deleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_deleteAllActionPerformed
        resetRest();  
        if(local_i != null)
        {
            local_i.removeAllInsiemi();
            local_i.finezza(); 
        }
        //insertRestriction.refreshList(-1);
        
        if(insertRestriction != null)
            insertRestriction.validateList();
        
        jTree.removeSelectionPath(path);
        levelSelected=-1;
        jLabel2.setText(" None");
        //refreshList();      
    }//GEN-LAST:event_jMenuItem_deleteAllActionPerformed

    private void jMenuItem_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_deleteActionPerformed
        int i=2,id;
        switch (levelSelected)
        {
            case 0:
                //////System.out.println("Celar All");
                resetRest();
                if(local_i != null)
                {
                    local_i.removeAllInsiemi();
                    local_i.finezza(); 
                }
                idSelected=-1;
                break;
            case 1:
                //////System.out.println("Value: "+(path.getPathComponent(i)).toString());
                id=br.getIdTE((path.getPathComponent(i)).toString());
                br.removeElem((path.getPathComponent(i)).toString());
                if(local_i != null)
                {
                    local_i.removeInsieme(id);
                }
                break;
            case 2:
                i=2;
                //////System.out.println("Value: "+(path.getPathComponent(i)).toString());    
                valueSelected[0]=(path.getPathComponent(i)).toString();
                i=3;
                //////System.out.println("Value: "+(path.getPathComponent(i)).toString());    
                valueSelected[1]=(path.getPathComponent(i)).toString();
                id=br.getIdTE(valueSelected[0]);

                //fare il tokenize

                if(br.isRestriction(br.getIdTE((path.getPathComponent(2)).toString())))
                {
                    java.util.StringTokenizer token;
                    token = new java.util.StringTokenizer(valueSelected[1]," ");
                    String valueSenzaOp="";
                    while (token.hasMoreTokens())
                    valueSenzaOp=token.nextToken();
                    ////System.out.println("****************: "+valueSelected[0]);
                    ////System.out.println("****************: "+valueSenzaOp);
                    br.removeValue(valueSelected[0],valueSenzaOp);
                }
                else
                {
                    br.removeElem(br.getIdTE((path.getPathComponent(2)).toString()));
                }			
                if(br.isPresentEB(valueSelected[0])==null)
                if(local_i != null)
                {
                    local_i.removeInsieme(id);
                }
                break;
        } 
        if(insertRestriction != null)
            insertRestriction.validateList();
        
        jTree.removeSelectionPath(path);
        levelSelected=-1;
        jLabel2.setText(" None");
        refreshList();        
        idSelected=-1;       
    }//GEN-LAST:event_jMenuItem_deleteActionPerformed
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo permette l'abilitazione/disabilitazione dell'interfaccia grafica.
     * </font></font></p></pre>  
     * @param flag Se true abilita l'interfaccia.
     */
    public void setGuiEnabled(boolean flag)
    {
        jrbExpand.setEnabled(flag);
        jrbClose.setEnabled(flag);
        jLabel2.setEnabled(flag);
        jL_help.setEnabled(flag);
        jTree.setEnabled(flag);
        jScrollPane1.setEnabled(flag);
    }
   
    private void jrbExpandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbExpandActionPerformed
        expandTree(jTree);
    }//GEN-LAST:event_jrbExpandActionPerformed

    private void jrbCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCloseActionPerformed
        collapseTree(jTree);
    }//GEN-LAST:event_jrbCloseActionPerformed

    private void initAlbero()
    {
        //////System.out.println("INIT ALBERO...");
        SampleData sd = new SampleData(staticLib.fontA10,Color.white,"TREE OF SELECTED RESTRICTION",staticLib.fontA10,Color.white,"TREE OF RESTRICTION SELECTED");				
        sd.setIcon(null,null,null,null);
        summary = createNewNode(sd);
        summary.add(br.creaAlbero1());
        jTree = new javax.swing.JTree(summary);

        setTree(jTree);
        expandTree(jTree);

        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mousePressed(java.awt.event.MouseEvent evt) {
                JTreeMousePressed(evt);}});

        jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeValueChanged(evt);}});
    }

    private void expandTree(JTree tree)
    {
        for(int i=0;i<=tree.getRowCount();i++)
        tree.expandRow(i);
            jTree.removeSelectionPath(path);        
    }   
        
    private void collapseTree(JTree tree)
    {
        for(int i=tree.getRowCount(); i>1;i--)
            tree.collapseRow(i);
                jTree.removeSelectionPath(path);        
    }
    
    private void setTree(JTree tree)
    {
        SampleTreeCellRenderer renderer = new SampleTreeCellRenderer();
        tree.setBackground(Color.gray);
        tree.setCellRenderer(renderer);        
        //tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tree.setUI(new WindowsTreeUI());
    }
	
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo che crea un nodo dell'albero
     * </font></font></p></pre>  
     * @param name descrizione del nodo,non è altro che la descrizione del valore della restrizione inserita se esiste, altrimenti il valore stesso.
     */
    public DefaultMutableTreeNode createNewNode(String name) 
    {
        return new nodoSummaryEXT(new SampleData(staticLib.fontA10, Color.white, name));
    }
	
    protected DefaultMutableTreeNode createNewNode(SampleData sd) 
    {
            return new nodoSummaryEXT(sd);
    }
    
    private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) 
    {
        String text[]={"",""};
        path=evt.getPath();
        pathCount = path.getPathCount();

        levelSelected=pathCount-2;
        if (pathCount >3)
        {
            text[0]=(path.getPathComponent(2)).toString();
            text[1]=(path.getPathComponent(3)).toString(); 
            
            //System.out.println("-- text[0] <"+text[0]+">");
            //System.out.println("-- text[1] <"+text[1]+">");
            
            for(int i=staticLib.operator.length-1; i>=0; i--)
            {
                //System.out.println("<"+staticLib.operator[i]+">");
                if(text[1].indexOf(staticLib.operator[i]) != -1)
                {
                    text[1] = text[1].replace(staticLib.operator[i],"");
                    break;
                }
            }

            jLabel2.setText(" RESTRICTION: "+text[0]+"    CODE: "+br.getValue(text[0],text[1]));
        }
        else            
            jLabel2.setText(" RESTRICTION: "+text[0]);

        try
        {
            if(br.getValue(text[0],text[1]) == null)
                delete = ((path.getPathComponent(2)).toString());
            else
                delete = " "+text[0]+" CODE: "+br.getValue(text[0],text[1]);
            
            if (delete.compareTo("Not selected restriction") == 0)
                jLabel2.setText("None");
        }
        catch(Exception e){}
        
        try
        {
            idSelected=br.getIdTE(path.getPathComponent(2).toString());
        }
        catch(Exception e)
        {
            idSelected=-1;
        }
        //////System.out.println("ID RESTR: "+idSelected);
    }
    
    private void JTreeMousePressed(java.awt.event.MouseEvent evt) 
    { 
        if(interactive_Popup)
        {
            if (levelSelected>=0)
            {
                jMenuItem_delete.setEnabled(true);
                int mouseID=evt.getModifiers();
                if((mouseID & InputEvent.BUTTON3_MASK)!=0)
                {
                    jMenuItem_delete.setText("Delete "+delete);
                    if (delete.compareTo("Not selected restriction") != 0)
                        jPopupMenu1.show((java.awt.Component)evt.getSource(),(evt.getX()-30),evt.getY());  
                }
            }
        }       
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo che setta il pannello delle restrizioni correntemente in uso
     * </font></font></p></pre>  
     * @param insertRestriction pannello delle restrizioni
     */
    public void setJR(jpRestrictions insertRestriction)
    {
        this.insertRestriction = insertRestriction;
    } 
		
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo che setta la struttura contenente i valori selezionati dall'utente por 
     * ogni singola restrizione.
     * </font></font></p></pre>  
     * @param br struttura di tipo BufferRestrizioni.
     * @see BufferRestrizioni
     */
    public void setBufferRestrizioni(BufferRestrizioni br)
    {
        this.br=br;
    }
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo che setta il pannello insiemi attualmente istanziato nel client.
     * </font></font></p></pre>  
     * @param ins pannello contenete la rappresentazione insiemistica delle relazioni selezionate dall'utente.
     * @see insiemi
     */
    public void setInsieme(insiemi ins)
    {
        this.local_i=ins;
    }
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo aggiorna la lista, ridisegna l'albero e aggiorna il grafico degli insiemi.
     * </font></font></p></pre>  
     * @see insiemi
     */
    public void refreshList()
    {
        setVisible(false);
        initAlbero();
        jScrollPane1.setViewportView(jTree);
        setVisible(true);
    }
      
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo provvede ad azzerare tutte le selezioni fatte.
     * </font></font></p></pre>  
     */
    public void resetRest()
    {
        br.celarAll();
        refreshList();			
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenuItem jMenuItem_delete;
    private javax.swing.JMenuItem jMenuItem_deleteAll;
    private javax.swing.JLabel jL_help;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jrbExpand;
    private javax.swing.JButton jrbClose;
    // End of variables declaration//GEN-END:variables

    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private javax.swing.JTree jTree;
    private BufferRestrizioni br;
    private DefaultMutableTreeNode summary;
    private insiemi local_i = null;
    
    private int levelSelected = -1;
    private int idSelected = -1;
    private String[] valueSelected= {"",""};
    private TreePath path;          
    private int pathCount;
    
    private jpRestrictions insertRestriction = null;
    private String delete;
    private boolean interactive_Popup = true;
}