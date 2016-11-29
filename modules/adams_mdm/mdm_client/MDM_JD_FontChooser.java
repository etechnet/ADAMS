import java.awt.Font;
/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> N.T.M. /font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (e-Tech s.r.l.) Created on Marzo 2007 - <a href="mailto:lube@e-tech.net">lube@e-tech.net</a></p>
 *
 * La classe estende una javax.swing.JDialog con la possibilità di specificarla come modale,(se modale: la finestra risulta bloccante
 * sino all'interazione esplicita dell'utente).
 * La classe, viene impiegata nell'applicativo per dare all'utente la possibilità di creare un Font specifico, 
 * l'interazione della classe, tramite interfaccia grafica messa a disposizione consente la scelta delle
 * seguenti propietà che caratterizzano un oggetto Font:
 *<pre>
 *  Font Name   ==> (Es: "Arial")
 *  Font Style  ==> (Es: "Font.BOLD")
 *  Font Size   ==> (Es: "12")
 *</pre>
 * Le selezioni effettuate mettono a disposizione dell'oggetto le informazioni necessarie per creare un oggetto Font di
 * ritorno utilizzabile (tramite il metodo getFontSelected() ) .
 *
 */
public class MDM_JD_FontChooser extends javax.swing.JDialog {

    /** 
     * Costruttore della classe di default.
     * Il costruttore della classe richiama come prima istruzione la parola chiave "super(parent,modal)" della classe che estende javax.swing.JDialog,
     * sfruttando il processo dell'ereditarietà degli oggetti.
     * Il costruttore della classe si prende carico della costruzione dell'interfaccia grafica.
     *
     *@param parent è il propietario della finestra di MDM_JD_FontChooser (Frame genitore).
     *@param modal Se true la finestra MDM_JD_FontChooser sarà in modalità modal attiva, tutto l'input viene diretto a essa 
     *finchè non verrà chiusa esplicitamente, il che significa che non sarà possibile accedere ad altre parti del programma finchè
     *non si effetuerà la chiusura. Se false modalità modal disattiva.
     */
    public MDM_JD_FontChooser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTF_size.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,2));
        
        IcPool = new IconPool("/images/");
        jList_fontName = new JListIcon(IcPool);
        
        jList_fontName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//SINGLE_SELECTION
        jScrollList.setViewportView(jList_fontName);
        
        //Font Globali
        //jList_fontName.set_Font(staticLib.fontA9,staticLib.fontA10);        
        jL1.setFont(staticLib.fontA10);
        jLab1.setFont(staticLib.fontA10);
        jLab2.setFont(staticLib.fontA10);
        jCBox_Style.setFont(staticLib.fontA10);
        jTF_size.setFont(staticLib.fontA10);
    
        //CURSOR
        jList_fontName.setCursor(Cur_hand);
        jB_apply.setCursor(Cur_hand);
        jB_close.setCursor(Cur_hand);
        jCBox_Style.setCursor(Cur_hand);
        
        // font item name
        java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontName = ge.getAvailableFontFamilyNames();
                
        readFont();
        pack();
    }
    
    private void readFont()
    {
        if(fontName != null)
        {
            for(int i=0; i<fontName.length; i++)
            {
                jList_fontName.addElement("font_type1.png","font_type1.png",fontName[i].trim());
            }
            
            // font item style
            fontStyle   = new int[4];
            fontStyle[0]= Font.PLAIN;
            fontStyle[1]= Font.BOLD;
            fontStyle[2]= Font.ITALIC;
            fontStyle[3]= Font.BOLD+Font.ITALIC;
            jCBox_Style.addItem("Plain");
            jCBox_Style.addItem("Bold");
            jCBox_Style.addItem("Italic");
            jCBox_Style.addItem("Bold Italic");
            
            jList_fontName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListMousePressed(evt);
                }
            });
            
            
            event_CBox = new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                JCBoxStyleSizeActionPerformed(evt);
                                }
                        };
                        
            jCBox_Style.addActionListener(event_CBox);
        }
    }
    private void setDefault(Font startFont)
    { 
        if(startFont == null)
        {
            if(jList_fontName.getItemCount()>0)
                jList_fontName.setSelectedIndex(0);
        
            jCBox_Style.setSelectedIndex(0);
            jTF_size.setText("10");
        }
        else
        {
            // cerco se il fontName è presente per selezionarlo nella lista
            String strfontName = startFont.getName().trim();
            
            int posfontName = 0;
            for(int i=0; i<fontName.length; i++)
            {
                
                if(strfontName.equalsIgnoreCase(fontName[i].trim()) == true)
                {
                    posfontName = i;
                    break;
                }
            }
            jList_fontName.setSelectedIndex(posfontName);
            jList_fontName.ensureIndexIsVisible(posfontName);
         
            // cerco il fontStyle per selezionarlo nella combo
            int Fontstyle = startFont.getStyle();
            int posFontStyle = 0;
            for(int x=0; x<fontStyle.length; x++)
            {
                if(Fontstyle == fontStyle[x])
                {
                    posFontStyle = x;
                    break;
                }
            }
            jCBox_Style.setSelectedIndex(posFontStyle);
            
            // setto il size
            jTF_size.setText(""+startFont.getSize());
            jL_preview.setFont(startFont);
        }
        createFont();
    }
    
    
    private void createFont() 
    {
        NAME = jList_fontName.get_ID_Value(jList_fontName.getSelectedIndex());
        STYLE   = fontStyle[jCBox_Style.getSelectedIndex()];
        String Size = jTF_size.getText().trim();
        if(Size.length() == 0)
        {
            SIZE = 10;
            jTF_size.setText(""+SIZE);
        }
        else
        {
            SIZE    = Integer.valueOf(Size).intValue();
        }
        LiveFont = new Font(NAME,STYLE,SIZE); 
        jL_preview.setFont(LiveFont);
    }
    
    /**
     * Questo metodo apre la JDialog MDM_JD_FontChooser.
     *Il metodo permette di settare le coordinate di apertura della MDM_JD_FontChooser e di impostare un Font di Default.
     *@param xpos La coordinata X di apertura della MDM_JD_FontChooser. 
     *@param ypos La coordinata Y di apertura della MDM_JD_FontChooser. 
     *@param fontDefault Oggetto Font di default impostato nell'interfaccia Gui all'apertura della MDM_JD_FontChooser.
     */
    public void openFontChooser(int xpos,int ypos, Font fontDefault)
    {
        setDefault(fontDefault);
        this.setLocation(xpos,ypos);
        setVisible(true);
    } 
    
    public void openFontChooser(Font fontDefault)
    {
        setDefault(fontDefault);
        setCenteredFrame(550,450);
        setVisible(true);
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
    
    
    /**
     * Il metodo restituisce il font selezionato nell'interfaccia.
     *@return Restituisce il font selezionato nell'interfaccia.
     */     
    public Font getFontSelected()
    {
        return LiveFont; 
    }
    
    /**
     * Il metodo restituisce il solo nome del font selezionato nell'interfaccia (es: "Arial").
     *@return Restituisce il nome del font selezionato nell'interfaccia.
     */ 
    public String getfontName()
    {
        return NAME;
    }
     /**
     * Il metodo restituisce il solo Style del font selezionato nell'interfaccia (es. Font.BOLD).
     *@return Restituisce lo Sytle del font selezionato nell'interfaccia.
     */ 
    public int getFontStyle()
    {
        return STYLE;
    }
     
    /**
     * Il metodo restituisce la sola dimensione in punti del font selezionato nell'interfaccia.
     *@return Restituisce la dimensione in punti del font selezionato nell'interfaccia.
     */ 
    public int getFontSize()
    {
        return SIZE;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jP_west = new javax.swing.JPanel();
        jP_Center = new javax.swing.JPanel();
        jL1 = new javax.swing.JLabel();
        jScrollList = new javax.swing.JScrollPane();
        jP_east = new javax.swing.JPanel();
        jCBox_Style = new javax.swing.JComboBox();
        jLab1 = new javax.swing.JLabel();
        jLab2 = new javax.swing.JLabel();
        jTF_size = new javax.swing.JTextField();
        jL_preview = new javax.swing.JLabel();
        jP_South = new javax.swing.JPanel();
        jB_apply = new javax.swing.JButton();
        jB_close = new javax.swing.JButton();
        
        setBackground(new java.awt.Color(230, 230, 230));
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jLabel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setFont(new java.awt.Font("Arial", 3, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/font.png")));
        jLabel1.setText("Chooser Font");
        jLabel1.setPreferredSize(new java.awt.Dimension(147, 55));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
        
        jP_west.setBackground(new java.awt.Color(230, 230, 230));
        jP_west.setPreferredSize(new java.awt.Dimension(30, 210));
        getContentPane().add(jP_west, java.awt.BorderLayout.WEST);
        
        jP_Center.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_Center.setBackground(new java.awt.Color(230, 230, 230));
        jP_Center.setPreferredSize(new java.awt.Dimension(300, 210));
        jL1.setText("Font Name");
        jL1.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        jP_Center.add(jL1, gridBagConstraints1);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        jP_Center.add(jScrollList, gridBagConstraints1);
        
        getContentPane().add(jP_Center, java.awt.BorderLayout.CENTER);
        
        jP_east.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_east.setBackground(new java.awt.Color(230, 230, 230));
        jP_east.setPreferredSize(new java.awt.Dimension(180, 210));
        jCBox_Style.setMaximumRowCount(4);
        jCBox_Style.setMaximumSize(new java.awt.Dimension(120, 20));
        jCBox_Style.setMinimumSize(new java.awt.Dimension(120, 20));
        jCBox_Style.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new java.awt.Insets(0, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_east.add(jCBox_Style, gridBagConstraints2);
        
        jLab1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLab1.setText("Font Style");
        jLab1.setMaximumSize(new java.awt.Dimension(100, 20));
        jLab1.setMinimumSize(new java.awt.Dimension(100, 20));
        jLab1.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_east.add(jLab1, gridBagConstraints2);
        
        jLab2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLab2.setText("Font Size");
        jLab2.setMaximumSize(new java.awt.Dimension(100, 20));
        jLab2.setMinimumSize(new java.awt.Dimension(100, 20));
        jLab2.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_east.add(jLab2, gridBagConstraints2);
        
        jTF_size.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTF_size.setMinimumSize(new java.awt.Dimension(35, 20));
        jTF_size.setPreferredSize(new java.awt.Dimension(35, 20));
        jTF_size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTF_sizeKeyReleased(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_east.add(jTF_size, gridBagConstraints2);
        
        jL_preview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/font_smart.png")));
        jL_preview.setText("Aa,Bb - 1,2,3,4");
        jL_preview.setToolTipText("Preview Font");
        jL_preview.setPreferredSize(new java.awt.Dimension(170, 90));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jP_east.add(jL_preview, gridBagConstraints2);
        
        getContentPane().add(jP_east, java.awt.BorderLayout.EAST);
        
        jP_South.setBackground(new java.awt.Color(230, 230, 230));
        jB_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        jB_apply.setBorderPainted(false);
        jB_apply.setContentAreaFilled(false);
        jB_apply.setFocusPainted(false);
        jB_apply.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_apply.setMaximumSize(new java.awt.Dimension(56, 20));
        jB_apply.setMinimumSize(new java.awt.Dimension(63, 20));
        jB_apply.setPreferredSize(new java.awt.Dimension(63, 20));
        jB_apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        jB_apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        jB_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_applyActionPerformed(evt);
            }
        });
        
        jP_South.add(jB_apply);
        
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_close.setMaximumSize(new java.awt.Dimension(63, 20));
        jB_close.setMinimumSize(new java.awt.Dimension(63, 20));
        jB_close.setPreferredSize(new java.awt.Dimension(63, 20));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jP_South.add(jB_close);
        
        getContentPane().add(jP_South, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jTF_sizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_sizeKeyReleased
        
        boolean enabled = true;
        if(jTF_size.getText().trim().length() == 0)
        {
            enabled = false;
            LiveFont = null;
            NAME    = null;
            STYLE   = -1;
            SIZE    = -1;
        }
        else
        {
            enabled = true;
            createFont();
        }
        jB_apply.setEnabled(enabled);
    }//GEN-LAST:event_jTF_sizeKeyReleased

    private void jListMousePressed(java.awt.event.MouseEvent evt) 
    {
        createFont();
    }
    
    private void JCBoxStyleSizeActionPerformed(java.awt.event.ActionEvent evt) {
        createFont();
    }
    
    private void jB_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_applyActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jB_applyActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        LiveFont = null;
        NAME    = null;
        STYLE   = -1;
        SIZE    = -1;
        this.setVisible(false);
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        LiveFont = null;
        NAME    = null;
        STYLE   = -1;
        SIZE    = -1;
        this.setVisible(false);
    }//GEN-LAST:event_closeDialog

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_west;
    private javax.swing.JPanel jP_Center;
    private javax.swing.JLabel jL1;
    private javax.swing.JScrollPane jScrollList;
    private javax.swing.JPanel jP_east;
    private javax.swing.JComboBox jCBox_Style;
    private javax.swing.JLabel jLab1;
    private javax.swing.JLabel jLab2;
    private javax.swing.JTextField jTF_size;
    private javax.swing.JLabel jL_preview;
    private javax.swing.JPanel jP_South;
    private javax.swing.JButton jB_apply;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables

    private JListIcon jList_fontName                = null;
    private IconPool IcPool                         = null;
    private java.awt.Cursor Cur_hand                = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private String[] fontName                       = null;
    private int[] fontStyle                         = null;
    
    private Font LiveFont   = null;
    private String NAME     = null;
    private int STYLE       = -1;
    private int SIZE        = -1;
    
    private java.awt.event.ActionListener event_CBox   = null;
    
}
