import javax.swing.*;
import java.awt.*;
import java.util.*;
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
 * Questa classe che estende un JPanel,è una classe fondamentale, in quanto costruisce dinamicamente 
 * la struttura grafica per l'inserimento delle restrizioni, considerando per ogni elemento di traffico 
 * anche le restrizioni aggregate, il tutto sfruttando una hastable nella quale vengono memorizzati i pannelli
 * già disegnati, in modo da ottimizzare al massimo,il tempo di esecuzione e la memoria usata.
 * I valori inseriti dall'utente, vengono memorizzati in una struttura globale che incapsula la struttura 
 * delle restrizioni.
 * La costruzione dell'iterfaccia è pilotata dal campo <b>guiObject</b> dell'Elemento di Traffico
 *
 * @see BufferRestrizioni
 * @see DATA_DATAELEMENT
 * @see OBJpool
 */
public class paintTrafficElement extends JPanel
{
       
    public static final int JSLIDER         = 4;
    public static final int JTEXTFIELD      = 1;
    public static final int JOPTIONVALUE    = 2;
    public static final int JPLUGINGUI	    = 5;

    private DATA_DATAELEMENT te;
    private JLabel  l       = new JLabel();
    private JLabel  desc2   = new JLabel("Operator");
    private String  appoOperator = new String();
    private JTextField	jtInsertCode    = new JTextField(6);
    //private JListIcon	jl;
    //private JComboBox	cb;
    private JButton b	= new JButton();
    private JButton b1	= new JButton();
    private String strIcona = "";
    private JTextField 	jtfPass;
    private IconPool ip	= new IconPool("/images/");
    private int min = 0;
    private int max = 0;
    //private RestrictionList rList;
    private MDM_JP_RestrictionList rList;
    private String[] currentCodesHelp   = null;
    private insiemi local_i;
    private JListIcon jlistValue;
    private JRadioButton jrb_HexDec = new JRadioButton();
    private JComboBox jcbOperatore  = new JComboBox();

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Riferimento alla struttura globale contenente la struttura dei dati inseriti dall'utente.
     * Sulla base di questa struttura viene creato il sommario e formattata la richiesta da
     * inviare al Master Server alla costruzione grafica dell' interfaccia della restrizione.
     * </font></font></p></pre>  
     */
    public BufferRestrizioni br;
    public javax.swing.JSlider jSlider2;
    public javax.swing.JLabel jlOra = new javax.swing.JLabel(" 00 ");
    public String strAppo[];
    public boolean flag	= false;
    public boolean DEBUG = false;
    public DATA_DATAELEMENT TEprimElem = null;
    public Vector strV = new Vector();
    
    private Color color_default = new Color(230,230,230);
		
	
    /*public void setEnv(RestrictionList rList,String[] currentCodesHelp,insiemi local_i,boolean flag) //nello da cancellare
    {
        this.rList=rList;
        this.currentCodesHelp=currentCodesHelp;
        this.local_i=local_i;
        this.flag=flag;
        setGUI();
    }*/
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Setta le variabili necessarie al corretto funzionamento della classe.
     * alla costruzione grafica del interfaccia della restrizione.
     * </font></font></p></pre> 
     * @parm rList Riferimento alla struttura ad albero che contera il sommario delle restrizioni inserite.
     * @param currentCodesHelp Array di String contenente i codici correnti di selezione.
     * @param local_i Riferimento al pannello per la rappresentagione insiemistica delle restrizioni inserite.
     * @param flag abilitazione per l'accesso di alcune operazioni protette.
     */	
    public void setEnv(MDM_JP_RestrictionList rList,String[] currentCodesHelp,insiemi local_i,boolean flag)
    {
        this.rList=rList;
        this.currentCodesHelp=currentCodesHelp;
        this.local_i=local_i;
        this.flag=flag;
        setGUI();
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore di default della classe.		
     * </font></font></p></pre>  
     */
    public paintTrafficElement()
    {
        super();
        ip = new IconPool("/images/");
        this.setBackground(color_default);
    }
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Overloadind del costruttore della classe.
     * Istanzia il pannello contenete i vari widget della restrizione in esame,
     * ed imposta i riferimenti agli oggetti ti tipo <b>BufferRestrizioni</b> e <b>DATA_DATAELEMENT</b>.	
     * </font></font></p></pre>  
     * @param br oggetto di tipo <b>BufferRestrizioni</b>, verrà popolato man mano che l'utente effettuerà le selezioni.,
     * @param te oggetto di tipo <b>DATA_DATAELEMENT</b>, contenente le informazioni del'Elemento di Traffico specificato.
     */ 
    public paintTrafficElement(BufferRestrizioni br,DATA_DATAELEMENT te)
    {
        super();
        ip=new IconPool("/images/");
        this.te=te;
        this.br=br;
        this.setBackground(color_default);
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruisce il JPanel relativo alla restrizione selezionata la costruzione viene pilotata dal campo 
     * dal campo <b>guiObject</b> del'Elemento di Traffico passato al metodo in questione.		 
     * </font></font></p></pre>  
     * @param te oggetto di tipo <b>DATA_DATAELEMENT</b>, contenente le informazioni del'Elemento di Traffico specificato.
     */
    public void setDataElement(DATA_DATAELEMENT te)
    {
        this.te=te;
        setGUI();
    }
        
    private void setGUI()
    {
        //**************************************************************************
        //			Disegno i vari object sulla base di <guiObject>
        //      	1: TextField
        //			2: List
        //			3: OptionList
        //			3: Slider
        //						
        //			N.B.: Ci sono due eccezioni <Numero cifre utente & Office Code>
        //		
        //**************************************************************************

        //System.out.println("GUI: "+te.guiObject);

        switch (te.guiObject)
        {
            case 0:
                break;

            case JTEXTFIELD:
                setGUI_TextField();
                //System.out.println("Dopo setGUI_TextField");
                b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                    }
                    }
                    );
                jtInsertCode.setFont(staticLib.fontA9);
                //jtInsertCode.setFont(staticLib.fontH10);
                jtInsertCode.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                    }
                    }
                    );
                    //setGUI_plugin();
                break;

            case JOPTIONVALUE:
                setGUI_OptionValue();
                jlistValue.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                    jList1ValueChanged(evt);
                }
                    });
                break;

            case 3:
                // option value
                setGUI_OptionValue();
                jlistValue.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                        jList1ValueChanged(evt);
                }
                        });
                break;
                
            case JPLUGINGUI:
                // Gestito da plugin
                setGUI_plugin();
                break;

            case JSLIDER:
                setGUI_JSlider();
                break;

        }
    }
		
    private void setGUI_OptionValue()
    {
        //da sistemare le dimensioni del pannello;
        //System.out.println("GUI: "+te.guiObject+"  Option Value");
        setLayout(new BorderLayout(0,10));
        /*setBorder(new javax.swing.border.TitledBorder(
        new javax.swing.border.EtchedBorder(), "", 4, 2,
        new java.awt.Font ("Helvetica", 1, 10), java.awt.Color.blue));*/ // nello da togliere

        try
        {
            strIcona=new String(te.pixmapFileName).trim();
            if(strIcona.equals(""))
            {
                strIcona=approssimaIcona(new String(te.shortDescription).trim());
            }
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+strIcona)));
        }catch(Exception e)
        {
        }
        
        
        l.setText(new String(te.longDescription).trim());
        l.setFont(staticLib.fontA9);
        add("North",l);

        jlistValue=new JListIcon(ip);
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jlistValue);

        int i=0;
        jlistValue.addElement("b_blue.gif","b_red.gif","None");
        while(!(new String(te.valueListLabel[i]).trim()).equals(""))
        {                       
            String str_valueList = (new String(te.valueListLabel[i])).trim();
            
            
            //////////////////////////////////// ATTENZIONE ////////////////////////////////////
            //La Stringa che identifica la descrizione inizia con '?' verrà invalidato il codice identificativo numerico, e considerata
            //la Stringa stessa come identificativo".
            ///////////////////////////////////////////////////////////////////////////////////
            if(str_valueList.indexOf("?") == 0 )
            {                
                str_valueList = (str_valueList.replace('?',' ')).trim();
                te.valueList[i] = -1; // La sola descrizione sarà Valida, contrassegno il codice come non valido.                   
            }
            /*else
            {
                System.out.println("NON CONTIENE CARATTERE JOLLY -->" + str_valueList );
            }*/
                        
            jlistValue.addElement("b_blue.gif","b_green.gif",(new String(str_valueList)));             
            //jlistValue.addElement("b_blue.gif","b_green.gif",(new String(te.valueListLabel[i])).trim());
            
            i++;
        }

        jlistValue.setSelectedIndex(0); 
        if(te.guiObject==3) //single selection
            jlistValue.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        jlistValue.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));

        add("Center",jScrollPane1);
        //System.out.println("Selected: "+br.getValueLabelGUI3(te.idElement,0));

        //da tenere in considerazione per quando cancello.
        //System.out.println("Def val iniziale:"+br.getValueLabelGUI3(te.idElement,0));
        jlistValue.setSelectedValue(br.getValueLabelGUI3(te.idElement,0),true);

        try
        {
            int val=(int)te.valueList[(jlistValue.getSelectedIndex()-1)];
            //System.out.println("TE primario: "+TEprimElem.idElement+"   TE Aggregato: "+te.idElement+"    Add Value: "+val);
            if((TEprimElem.idElement==41)||(TEprimElem.idElement==42)||(TEprimElem.idElement==8)||(TEprimElem.idElement==10))
            {
                if((te.idElement==39)||(te.idElement==40)||(te.idElement==28)||(te.idElement==29))
                {
                    if(val==3)
                    {
                        //System.out.println("Disabilita l'HELP");
                        staticLib.rHelp1.disabilita();
                    }
                    else
                    {
                        //System.out.println("Abilita l'HELP");

                        //strAppo=staticLib.descHELP.get_Descrizioni_conId(TEprimElem.LinkToDescStruct);
                        staticLib.rHelp1.setElementList(strAppo,TEprimElem);
                    }
                }
            }
        }catch(Exception e)
        {
           // System.out.println(e); 
        }

        /*
        int val,PD=0;

        while(!(new String(te.valueListLabel[PD]).trim()).equals(""))
        {
                val=(int)te.defaultValue;
                //System.out.println("DEFAULT VAL: "+val+"  Current: "+(int)te.valueList[PD]);
                if(((int)te.valueList[PD])==val)
                {
                        ////System.out.println("DEFAULT VAL: "+(PD+1));
                        jlistValue.setSelectedIndex(PD+1);
                        //appoBR();
                        jList1ValueChanged(null);
                        break;
                }
                PD++;
        }*/
    }
	
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Setta i valori di default del widget tipo Lista, se è presente nella restrizione.
     * </font></font></p></pre>  
     */
    public void validateList()
    {
        String str=null;
        try
        {
            str=br.getValueLabelGUI3(te.idElement,0);
            //System.out.println("validate: "+str);
            if(str==null)
            {
                jlistValue.setSelectedIndex(0);
                //System.out.println("è nullo");
            }
            else
            {				
                //System.out.println("Def val:"+br.getValueLabelGUI3(te.idElement,0));
                jlistValue.setSelectedValue(br.getValueLabelGUI3(te.idElement,0),true);
                try
                {
                    int val=(int)te.valueList[(jlistValue.getSelectedIndex()-1)];
                    //System.out.println("TE primario: "+TEprimElem.idElement+"   TE Aggregato: "+te.idElement+"    Add Value: "+val);
                    if((TEprimElem.idElement==41)||(TEprimElem.idElement==42)||(TEprimElem.idElement==8)||(TEprimElem.idElement==10))
                    {
                        if((te.idElement==39)||(te.idElement==40)||(te.idElement==28)||(te.idElement==29))
                        {
                            if(val==3)
                            {
                                staticLib.rHelp1.disabilita();
                            }
                            else
                            {
                                //System.out.println("Abilita l'HELP");
                                //strAppo=staticLib.descHELP.get_Descrizioni_conId(TEprimElem.LinkToDescStruct);
                                staticLib.rHelp1.setElementList(strAppo,TEprimElem);
                            }
                        }
                    }
                }catch(Exception e)
                {
                        //System.out.println("ERRORE TEPRIMARIO1");
                };
            }
        }catch(Exception e){}
        try
        {
            if((br.getSize(te.idElement)==0)&&(te.compareSelection>0))
            {
                desc2.setVisible(true);
                jcbOperatore.setVisible(true);
            }
        }catch(Exception e){}
    }
		
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Forza la selezione del widget lista al primo valore.
     * </font></font></p></pre>  
     */
    public void refreshList(int id)
    {
        //System.out.println("refresh List id: "+id);
        try
        {
            if((id==te.idElement)||(id==-1))
            {
                jlistValue.setSelectedIndex(0);	
            }
        }catch(Exception e){}
    }
		
    private void setGUI_ListValue()
    {
        //da sistemare le dimensioni del pannello;
        setLayout(new BorderLayout());
        /*setBorder(new javax.swing.border.TitledBorder(
        new javax.swing.border.EtchedBorder(), "", 4, 2,
        new java.awt.Font ("Helvetica", 1, 10), java.awt.Color.blue));*/ //nello da togliere
    }
		
    private void setGUI_JSlider()
    {
        //da sistemare le dimensioni del pannello;
        setLayout(new BorderLayout(5,5));
        /*setBorder(new javax.swing.border.TitledBorder(
        new javax.swing.border.EtchedBorder(), "", 4, 2,
        new java.awt.Font ("Helvetica", 1, 10), java.awt.Color.blue));*/ //nello da togliere

        try
        {
            strIcona = new String(te.pixmapFileName).trim();
            if(strIcona.equals(""))
            {
                    strIcona=approssimaIcona(new String(te.shortDescription).trim());
            }
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+strIcona)));    
        }
        catch(Exception e)
        {
        }

        l.setText(new String(te.longDescription).trim());
        l.setFont(staticLib.fontA9);
        l.setBounds(2, 2, 130, 32);

        JPanel pOption2 = new JPanel();
        pOption2.setBackground(color_default);
        pOption2.setLayout(null);
        pOption2.setPreferredSize(new java.awt.Dimension(180, 34));
        pOption2.setMinimumSize(new java.awt.Dimension(180, 34));
        pOption2.setMaximumSize(new java.awt.Dimension(180, 34));
        pOption2.add(l);

        //if(staticLib.jsl.size()<1)
        if(staticLib.disegnaApply)
        {
            
            b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
            b1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
            b1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
            b1.setFocusPainted(false);
            b1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            b1.setContentAreaFilled(false);
            b1.setMinimumSize(new java.awt.Dimension(60, 22));
            b1.setBorderPainted(false);
            b1.setBounds(170, 2, 60, 22);
            
            /*b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exe.gif")));
            b1.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            b1.setFocusPainted(false);

            b1.setFont(staticLib.fontA9);
            b1.setText("Set");
            b1.setBounds(140, 10, 80, 20);*/ // nello da cancellare
            pOption2.add(b1);


            b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
                      }
            }
            );				
        }

        add("North",pOption2);

        JPanel pOption = new JPanel();
        pOption.setBackground(color_default);
        pOption.setLayout(new BorderLayout());

        String strRange=new String(te.valueRange).trim();
        //System.out.println("strRange: "+strRange);
        if(!strRange.equals(""))
        {
                        //calcolo il min_value ed il max_value
            StringTokenizer token;
            token=new StringTokenizer(strRange,"-");

            String strMIN="",strMAX="";

            strMIN=token.nextToken();
            strMAX=token.nextToken();
            min=Integer.parseInt(strMIN);
            max=Integer.parseInt(strMAX);

            //System.out.println("min: "+min);
            //System.out.println("max: "+max);
        }

        jSlider2 = new javax.swing.JSlider();
        jSlider2.setMinimum(min);
        jSlider2.setMaximum(max);
        //System.out.println("setMaximum(max) "+max);
        
        jSlider2.setPaintTicks(true); 
        jSlider2.setMajorTickSpacing(12); 
        jSlider2.setMinorTickSpacing(1); 
        jSlider2.setPaintLabels( true ); 
        jSlider2.setSnapToTicks( true );        
        
        if(max == 23)
        {
            //////////// FONT SLIDER per HOUR
            JLabel labeljSlider1 =  new JLabel(new Integer(min).toString(), JLabel.CENTER); 
            labeljSlider1.setFont(staticLib.fontA9);
            jSlider2.getLabelTable().put(new Integer(min),labeljSlider1);        
            jSlider2.setLabelTable( jSlider2.getLabelTable() ); 

            JLabel labeljSlider =  new JLabel(new Integer(max).toString(), JLabel.CENTER); 
            labeljSlider.setFont(staticLib.fontA9);
            jSlider2.getLabelTable().put(new Integer(max),labeljSlider);        
            jSlider2.setLabelTable( jSlider2.getLabelTable() ); 

            JLabel labeljSlider2 =  new JLabel((""+12), JLabel.CENTER); 
            labeljSlider2.setFont(staticLib.fontA9);
            jSlider2.getLabelTable().put(new Integer(12),labeljSlider2);        
            jSlider2.setLabelTable( jSlider2.getLabelTable() ); 
            ////////////////////
        }
             
        jSlider2.putClientProperty("JSlider.isFilled", Boolean.TRUE );
        jSlider2.setBounds(10, 30, 240, 68);
        jSlider2.setBackground(color_default);

        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
        jSlider1StateChanged(evt);
        }
        }
        );

        jSlider2.setValue((int)te.defaultValue);

        staticLib.jsl.addElement(jSlider2);
        staticLib.jslTE.addElement(te);

        //System.out.println("SIZE: "+staticLib.jsl.size());
        pOption.add(jSlider2,java.awt.BorderLayout.CENTER);

        //jlOra.setBorder(new javax.swing.border.SoftBevelBorder(1));
        jlOra.setForeground(java.awt.Color.black);        
        jlOra.setFont(staticLib.fontA10);

        if((int)te.defaultValue<10)
            jlOra.setText(" 0"+(int)te.defaultValue+" ");
        else
            jlOra.setText(" "+(int)te.defaultValue+" ");

        JPanel pOption1=new JPanel();
        pOption1.setBackground(color_default);
        pOption1.setLayout(new FlowLayout());
        pOption1.add(jlOra);
        pOption.add(pOption1,java.awt.BorderLayout.EAST);


        add(pOption,java.awt.BorderLayout.CENTER); 


        /*br.addRestriction(te.idElement,
                                new String(te.longDescription).trim(),
                                (int)te.defaultValue,
                                ""+(int)te.defaultValue,
                                true,
                                te.compareSelection,
                                te.priority);*/
        //System.out.println("Default Value: "+(int)te.defaultValue);
        rList.refreshList();

    }
    
    private void setGUI_plugin()
    {
        //da sistemare le dimensioni del pannello;

        setLayout(new BorderLayout());
        //l.setBounds(2, 2, 130, 32);        
        try
        {
            strIcona=new String(te.pixmapFileName).trim();
            if(strIcona.equals(""))
            {
                strIcona=approssimaIcona(new String(te.shortDescription).trim());
            }
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+strIcona)));
        }catch(Exception e)
        {
        }

        try
        {
            l.setText((new String(te.longDescription).trim()));
        }catch(Exception e)
        {
            System.out.println("Errore nella LongDesc.");
            l.setText("");
        }

        l.setFont(staticLib.fontA9);		        
        add("South",l);
        
        /*JLabel l1=new JLabel("La restrizione viene gestita da un plugin esterno.");
        l1.setFont(staticLib.fontA9);		        
        add("South",l1);*/

    }
		
    private void setGUI_TextField()
    {
        //da sistemare le dimensioni del pannello;

        setLayout(new BorderLayout());
        /*setBorder(new javax.swing.border.TitledBorder(
        new javax.swing.border.EtchedBorder(), "", 4, 2,
        new java.awt.Font ("Helvetica", 1, 10), java.awt.Color.blue));*/ //nello da togliere

        try
        {
            strIcona=new String(te.pixmapFileName).trim();
            if(strIcona.equals(""))
            {
                strIcona=approssimaIcona(new String(te.shortDescription).trim());
            }
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/"+strIcona)));
        }catch(Exception e)
        {
        }

        try
        {
            l.setText(new String(te.longDescription).trim());
        }catch(Exception e)
        {
            System.out.println("Errore nella LongDesc.");
            l.setText("");
        }

        l.setFont(staticLib.fontA9);		        
        add("North",l);

        JPanel pOption = new JPanel();
        pOption.setBackground(color_default);
        pOption.setLayout(null);

        // Label
        JLabel desc=new JLabel("Insert Value    ");
        desc.setFont(staticLib.fontA9);
        desc.setBounds(10, 10, 90, 20);
        pOption.add(desc);

        // Text Field
        jtInsertCode.setHorizontalAlignment(JTextField.CENTER);
        jtInsertCode.setFont(staticLib.fontA9);

        //*****************************************************
        // Calcolo il Range di valori
        //*****************************************************

        String strRange="";
        //System.out.println("E1."+te.idElement);
        //System.out.println("TE: "+new String(te.shortDescription).trim()+"  Default="+(int)te.compareSelection);
        try
        {
            strRange=new String(te.valueRange).trim();
            if(!strRange.equals(""))
            {
                //calcolo il min_value ed il max_value
                StringTokenizer token;
                token=new StringTokenizer(strRange,"-");

                String strMIN="",strMAX="";

                strMIN=token.nextToken();
                strMAX=token.nextToken();
                min=Integer.parseInt(strMIN);
                max=Integer.parseInt(strMAX);

                JTextFieldFilter tf;

		//System.out.println("te.idElement="+te.idElement);

		if((te.idElement==126)||(te.idElement==125)) // abilito il carattere jolly '*'
                {
                    //System.out.println("E2."+te.idElement);
                        tf=new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC+"*._",strMAX.length());
                }else
                {
                    //System.out.println("E3."+te.idElement);
                        tf=new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC+"._",strMAX.length());
                }

                jtInsertCode.setDocument(tf);
            }
            else
            {
                //System.out.println("E4."+te.idElement);
                jtInsertCode.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC+"._",25));
                min=0;
                max=1000000000;
            }			

        }catch(Exception e)
        {
                System.out.println("Errore nel Range.");
                jtInsertCode.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC+"._",25));
                min=0;
                max=1000000000;
        }

        jtInsertCode.setBounds(100, 10, 40, 20);
        pOption.add(jtInsertCode);

        try
        {
            if((new String(te.shortDescription)).trim().equals("OCO")||(new String(te.shortDescription)).trim().equals("OCE"))
            {
                    JTextFieldFilter tf=new JTextFieldFilter(JTextFieldFilter.NUMERIC+"._",21);
                jtInsertCode.setDocument(tf);
            }
        }catch(Exception e)
        {
            System.out.println("Errore nella ShortDesc.");
        }

        //JButton SET
        
        b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        b.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        b.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        b.setFocusPainted(false);
        b.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        b.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setBounds(170, 10, 60, 22);
        
        /*b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exe.gif")));
        b.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        b.setFocusPainted(false);

        b.setFont(staticLib.fontA9);
        b.setText("Set");
        b.setBounds(140, 10, 80, 20);*/ //nello da cancellare
        
        pOption.add(b);

        //**********************************************************************
        //System.out.println("acceptHex: "+te.acceptHex);
        if(te.acceptHex)
        {
           /* jrb_HexDec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rb.gif")));
            jrb_HexDec.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rbp.gif")));
            jrb_HexDec.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rbg.gif")));
            jrb_HexDec.setFocusPainted(false);
            jrb_HexDec.setFont(staticLib.fontA9);
            jrb_HexDec.setText("Decimal");*/ //nello da cancellare
            
            jrb_HexDec.setText("Decimal");
            jrb_HexDec.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
            jrb_HexDec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
            jrb_HexDec.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
            jrb_HexDec.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
            jrb_HexDec.setFocusPainted(false);
            jrb_HexDec.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
            jrb_HexDec.setContentAreaFilled(false);
            
            jrb_HexDec.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_HexDecActionPerformed(evt);
                }
                }
            );
            JLabel desc1=new JLabel("Input Format");
            desc1.setFont(staticLib.fontA9);
            desc1.setBounds(10, 40, 90, 20);
            pOption.add(desc1);
            jrb_HexDec.setBounds(100, 40, 120, 20);
            pOption.add(jrb_HexDec);
        }

        //System.out.println("compareSelection: "+te.compareSelection);
        if(te.compareSelection>0)
        {
            // Operatore da applicare restrizioni
            jcbOperatore.setBackground(color_default);
            jcbOperatore.setRequestFocusEnabled(false);
            jcbOperatore.addItem(" == ");
            jcbOperatore.addItem(" > ");
            jcbOperatore.addItem(" < ");
            jcbOperatore.addItem(" >= ");
            jcbOperatore.addItem(" <= ");	

            desc2.setFont(staticLib.fontA9);                    

            if(te.acceptHex)
            {
                desc2.setBounds(10, 70, 90, 20);
                jcbOperatore.setBounds(100, 70, 60, 20);
            }
            else
            {
                desc2.setBounds(10, 40, 90, 20);	
                jcbOperatore.setBounds(100, 40, 60, 20);
                
            }

            pOption.add(desc2);
            pOption.add(jcbOperatore);
            
            jcbOperatore.setSelectedIndex(te.compareSelection-1);
        }


        //**********************************************************************		
        //if(((staticLib.struc_paramsCURRENT.Relation==28)||(staticLib.struc_paramsCURRENT.Relation==5))&&(te.idElement==11))
        /*if( (te.idElement==168) || (te.idElement==167) )
        {
            //System.out.println("Relazione di default: "+staticLib.struc_paramsCURRENT.Relation);

            jtfPass=new JPasswordField(15);
            jtfPass.setDocument(new JTextFieldFilter(JTextFieldFilter.ALPHA_NUMERIC,15));
            jtfPass.setBounds(10, 80, 120, 20);
            pOption.add(jtfPass);

            JButton setPaswd = new JButton();
            setPaswd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/password.jpg")));
            setPaswd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/password_over.jpg")));
            setPaswd.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/password_press.jpg")));
            setPaswd.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            setPaswd.setFocusPainted(false);
            setPaswd.setFocusPainted(false);
            setPaswd.setContentAreaFilled(false);
            setPaswd.setBorderPainted(false);
            setPaswd.setFont(staticLib.fontA9);
            setPaswd.setBounds(170, 80,80, 22);
            pOption.add(setPaswd);

            setPaswd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            setPaswdActionPerformed(evt);
                                }
                            }
                            );
            jtfPass.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            setPaswdActionPerformed(evt);
                                    }
                            }
                                    );

        }*/

        //System.out.println("defaultValue: "+te.defaultValue);
        if((int)te.defaultValue!=0)
        {

                jtInsertCode.setText(""+(int)te.defaultValue);
                br.removeElem(te.idElement);
                br.addRestriction(te.idElement,
                                new String(te.longDescription).trim(),
                                ""+(int)te.defaultValue,
                                ""+(int)te.defaultValue,
                                true,
                                0,
                                te.priority,te.exceptions[0]);
                                rList.refreshList();
                                jtInsertCode.setText("");
                br.applicaEccezioni(te.idElement);       
        }
        //Metto il pannello globale
        add(pOption,java.awt.BorderLayout.CENTER); 

    }
		
    private void jrb_HexDecActionPerformed(java.awt.event.ActionEvent evt) 
    {
        if(evt.getSource()==jrb_HexDec)
        {
            if(jrb_HexDec.isSelected())
            {
                jrb_HexDec.setText("Hexadecimal");
                try
                {
                    int tmp=Integer.parseInt(jtInsertCode.getText());
                    jtInsertCode.setDocument(new JTextFieldFilter(JTextFieldFilter.HEX,4));
                    jtInsertCode.setText(Integer.toHexString(tmp));
                }catch(Exception e)
                {
                    jtInsertCode.setDocument(new JTextFieldFilter(JTextFieldFilter.HEX,4));
                };
            }
            else
            {
                String strTmp=jtInsertCode.getText();
                jrb_HexDec.setText("Decimal");
                jtInsertCode.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,4));
                try
                {
                        jtInsertCode.setText(""+Integer.parseInt(strTmp,16));
                }
                catch(Exception e)
                {
                    };
            }
        }
    }

    private void setPaswdActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String pass=jtfPass.getText();

        if(pass.equals("cspu"))
        {
            //staticLib.flagPass=true;
        }
        else
        {
            staticLib.optionPanel("Unknown password", "Error",JOptionPane.ERROR_MESSAGE);
            //staticLib.flagPass=false;
            //System.out.println("Disattivo la password...1");
        }
        jtfPass.setText("");
    }
		

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        //System.out.println("Setto l'ora: "+staticLib.jsl.size());
        //System.out.println("Setto l'ora: "+staticLib.jslTE.size());
        DATA_DATAELEMENT TEapp=null;
        for(int i=0;i<staticLib.jsl.size();i++)
        {
            TEapp=(DATA_DATAELEMENT)staticLib.jslTE.elementAt(i);
            if(br.isPresentEB(TEapp.idElement)==null)
            {
                    local_i.addInsieme(TEapp.idElement,(new String(TEapp.longDescription)).trim(),br.getSize());
            }
            //System.out.println("Value: "+TEapp.idElement);
            br.removeElem(TEapp.idElement);
            String appoORA="";
            /*if(TEapp.idElement==4)
            {
                    appoORA=":00";
            }
            else if(TEapp.idElement==44)
            {
                    appoORA=":59";
            }*/
	    //System.out.println("TEapp.shortDescription="+(new String(TEapp.shortDescription)).trim());
	    if((new String(TEapp.shortDescription)).trim().equals("ORE"))
            {
                    appoORA=":00";
            }
            else if( (new String(TEapp.shortDescription)).trim().equals("ORE_STOP"))
            {
                    appoORA=":59";
            }
            br.addRestriction(TEapp.idElement,
                            new String(TEapp.longDescription).trim(),
                            ""+(int)((JSlider)staticLib.jsl.elementAt(i)).getValue(),
                            ""+((JSlider)staticLib.jsl.elementAt(i)).getValue()+appoORA,
                            true,
                            TEapp.compareSelection,
                            TEapp.priority,
                            TEapp.exceptions[0]);
            br.applicaEccezioni(te.idElement);       
        }
        //System.out.println("GRAFICO:              "+br.isPresentEB(TEapp.idElement));

        rList.refreshList();

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        int codeInsert,oper;
        boolean exist=false;
        String[] strGetText = new String[1];
        //*************************************************
        //Controllare i range di valori
        //*************************************************
        if(jrb_HexDec.isSelected())
        {
            try
            {
                    strGetText[0]=""+Integer.parseInt(jtInsertCode.getText(),16);
            }catch(Exception e)
            {
                    strGetText[0]="";
            }
        }
        else
        {
            strGetText[0]=jtInsertCode.getText();
        }
        try
        {
            try
            {
                if(strGetText[0].indexOf("*")==-1)
                {
                    if( (Integer.parseInt(strGetText[0])<min) || (Integer.parseInt(strGetText[0])>max))
                    {
                        staticLib.optionPanel("Value out of range","WARNING",JOptionPane.WARNING_MESSAGE);
                        jtInsertCode.setText("");
                        return;
                    }
                }
            }catch(Exception e)
            {

            }

            /*if((new String(te.shortDescription)).trim().equals("NUMCIFREU"))
            {
                try
                {
                    //System.out.println("NUMCIFREU   "+new String(te.longDescription).trim()+"   "+new String(te.shortDescription).trim());
                    br.removeElem(te.idElement);
                    //addRestriction(int idTE,String teDesc,String value,String desc,boolean flag,int op,int pri)

                    br.addRestriction(te.idElement,
                            new String(te.longDescription).trim(),
                            //Integer.parseInt(strGetText[0]),
                            strGetText[0],
                            new String(te.shortDescription).trim(),
                            false,
                            1,
                            te.priority,
                            te.exceptions[0]);
                            rList.refreshList();
                            jtInsertCode.setText("");
                }catch(Exception e)
                {
                    System.out.println("Errore 1: "+e);
                    e.printStackTrace();
                }
            }*/
            /*else if((new String(te.shortDescription)).trim().equals("OCO")||(new String(te.shortDescription)).trim().equals("OCE"))
            {
                try
                {
                    //System.out.println("OCE of OCO");
                    br.removeElem(te.idElement);
                    staticLib.OCO=strGetText[0];
                    br.addRestriction(te.idElement,
                            new String(te.longDescription).trim(),
                            //Integer.parseInt(strGetText[0]),
                            strGetText[0],
                            strGetText[0].trim(),
                            //new String(te.shortDescription).trim(),
                            true,
                            1,
                            te.priority,
                            te.exceptions[0]);
                            rList.refreshList();
                            jtInsertCode.setText("");
                }catch(Exception e)
                {
                    System.out.println("Errore 2: "+e);
                }
            }*/
            //else // tutti gli altri casi
            //{
            if((br.isPresentEB(te.idElement)==null)&&(!strGetText[0].equals("")))
            {
                if( (strGetText[0].indexOf("*")!=0) || (strGetText[0].indexOf("*")==-1) )
                {
                    local_i.addInsieme(te.idElement,(new String(te.longDescription)).trim(),br.getSize());
                }
            }
            jtInsertCode.setText("");
            try
            {
                try
                {
                    if((oper=jcbOperatore.getSelectedIndex())==-1)
                    {
                        oper=0;
                    }
                }catch(Exception e)
                {
                    oper=0;
                    //System.out.println("Errore 3: "+e);
                }
                oper=oper+1;
                
                
                String strOperatorSelected ="";
                
                try
                {
                    strOperatorSelected = new String(jcbOperatore.getSelectedItem().toString().trim());                 
                }catch(Exception e)
                {
                    //System.out.println("jcbOperatore non istanziato");
                }                   
                if(appoOperator.length() == 0)
                    appoOperator = strOperatorSelected;
                                
                //System.out.println("jcbOperatore --> "+strOperatorSelected);
                                
                if(strGetText[0].indexOf("*")==-1)
                {
                    if(flag)
                    {
                        br.removeElem(te.idElement);
                    }
                    
                    if( !strGetText[0].equals("") )
                    {
                        
                        if(te.compareSelection>0)
                        {
                            if( (strOperatorSelected.equals("==") == false) || ( appoOperator.equals(strOperatorSelected) == false) )
                            {     
                                br.removeElem(te.idElement);
                                //System.out.println("Rimuovo elementi ");
                            }                        
                        }
                        
                        br.addRestriction(te.idElement,
                                new String(te.longDescription).trim(),
                                //Integer.parseInt(strGetText[0]),
                                strGetText[0],
                                strGetText[0].trim(),
                                true,
                                oper,
                                te.priority,
                                te.exceptions[0]);
                        br.applicaEccezioni(te.idElement);       
                        
                        appoOperator = strOperatorSelected;
                    }
                }else
                {
                    //System.out.println("POS: "+strGetText[0].indexOf("*"));
                    /*if(strGetText[0].indexOf("*")==0)
                    {
                        staticLib.optionPanel("Impossible insert '*' at first position.","WARNING",JOptionPane.WARNING_MESSAGE);
                        jtInsertCode.setText("");
                        return;
                    }*/

                    strV=new Vector();
                    parse(strGetText[0].trim(),0);

                    for(int i_s=0;i_s<strV.size();i_s++)
                    {
                        String appoS=(String)strV.elementAt(i_s);
                        if(flag)
                        {
                            br.removeElem(te.idElement);
                        }
                        
                        if(te.compareSelection>0)
                        {
                            if( (strOperatorSelected.equals("==") == false) || ( appoOperator.equals(strOperatorSelected) == false) )
                            {     
                                br.removeElem(te.idElement);
                                //System.out.println("Rimuovo elementi 2");
                            }
                        }
                        
                        br.addRestriction(te.idElement,
                                new String(te.longDescription).trim(),
                                //Integer.parseInt(appoS),
                                appoS,
                                appoS.trim(),
                                true,
                                oper,
                                te.priority,
                                te.exceptions[0]);
                        br.applicaEccezioni(te.idElement);       
                    }
                    
                    appoOperator = strOperatorSelected;
                }

                if((br.getSize(te.idElement)>0)&&(te.compareSelection<=0))
                {
                    desc2.setVisible(false);
                    jcbOperatore.setVisible(false);
                }
                rList.refreshList();
            }catch(Exception e)
            {
                System.out.println(" Errore 4: "+e);
            }
            //}
            //}else
            //{
            //	staticLib.optionPanel("Value out of range","WARNING",JOptionPane.WARNING_MESSAGE);
            //	jtInsertCode.setText("");
            //}
        }catch(Exception e)
        {
                System.out.println("Errore 5: "+e);
        }
    }

    public JButton getSetButton()
    {
        return (JButton)b;
    }

    public JTextField getTextField()
    {
        return (JTextField)jtInsertCode;
    }

    public String getDescElement()
    {
        return (new String(te.longDescription)).trim();
    }

    public int getIdElements()
    {
        return te.idElement;
    }

    public void setPrimaryElem(DATA_DATAELEMENT i)
    {
        this.TEprimElem=i;
    }
		
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) 
    {
        //System.out.println((String)jlistValue.getSelectedValue());
        if(((String)jlistValue.getSelectedValue()).equals("None"))
        {
                br.removeElem(te.idElement);
                local_i.removeInsieme(te.idElement);
        }
        else
        {       
            if(br.isPresentEB(te.idElement)==null)
            {
                local_i.addInsieme(te.idElement,(new String(te.longDescription)).trim(),br.getSize());
            } 
            
            int val = 0;
            if( (te.guiObject == 3) || ( (jlistValue.getSelectedIndices()).length == 1) )//single selection
            {            
                val = (int)te.valueList[(jlistValue.getSelectedIndex()-1)];
                
                
                //System.out.println("val = "+val);
                /*if(br.isPresentEB(te.idElement)==null)
                {
                    local_i.addInsieme(te.idElement,(new String(te.longDescription)).trim(),br.getSize());
                }*/
                
                ///// invalidazione Codice numerico, Abilitata la Stringa Descrizione come Codice identificativo
                String value_List = ""+val;
                if(val == -1)
                    value_List = (String)jlistValue.getSelectedValue(); // il valore del codice non è valido, inserisco la descrizione come codice Stringa
                /////    
                
                br.addRestrictionGUI3(te.idElement,new String(te.longDescription).trim(),value_List,(String)jlistValue.getSelectedValue(),true,1,te.priority,te.exceptions[0]);	
                br.applicaEccezioni(te.idElement);         
            }
            else //multi selection
            {
                br.removeElem(te.idElement);
                local_i.removeInsieme(te.idElement);
                
                int indexSel[]      = jlistValue.getSelectedIndices();
                Object stringSel[]  = jlistValue.getSelectedValues();
                
                int[] valMul = new int[indexSel.length];                
                                
                for(int i=0; i<indexSel.length; i++)
                {                
                    valMul[i] = (int)te.valueList[indexSel[i]-1];
                    
                   /* System.out.println();
                    System.out.println("valMul["+i+"] = "+valMul[i]);
                    System.out.println("te.idElement "+te.idElement);
                    System.out.println("te.longDescription "+new String(te.longDescription).trim());
                    System.out.println("(String)stringSel[i] "+(String)stringSel[i]);*/
                    
                    ///// invalidazione Codice numerico, Abilitata la Stringa Descrizione come Codice identificativo
                    String value_List = ""+valMul[i];
                    if(valMul[i] == -1) // il valore del codice non è valido, inserisco la descrizione come codice Stringa
                        value_List = (String)stringSel[i];                    
                    /////
                                        
                    br.addRestriction(te.idElement,new String(te.longDescription).trim(),value_List,(String)stringSel[i],true,1,te.priority,te.exceptions[0]);	  
                    br.applicaEccezioni(te.idElement);       
                }
                
            }            
            
            
            try
            {
                //System.out.println("TE primario: "+TEprimElem.idElement+"   TE Aggregato: "+te.idElement+"    Add Value: "+val);
                if((TEprimElem.idElement==41)||(TEprimElem.idElement==42)||(TEprimElem.idElement==8)||(TEprimElem.idElement==10))
                {
                    if((te.idElement==39)||(te.idElement==40)||(te.idElement==28)||(te.idElement==29))
                    {
                        if(val == 3)
                        {
                            staticLib.rHelp1.disabilita();
                        }	
                        else
                        {
                            //System.out.println("Abilita l'HELP");
                            //strAppo=staticLib.descHELP.get_Descrizioni_conId(TEprimElem.LinkToDescStruct);
                            staticLib.rHelp1.setElementList(strAppo,TEprimElem);
                        }
                     }
                }
            }catch(Exception e)
            {
                //System.out.println("ERRORE TEPRIMARIO2");
            };
        }
        rList.refreshList();
    }
		
   /* private void appoBR() 
    {
        if(((String)jlistValue.getSelectedValue()).equals("None"))
        {
            br.removeElem(te.idElement);
        }
        else
        {
            int val=(int)te.valueList[(jlistValue.getSelectedIndex()-1)];
            br.addRestrictionGUI3(te.idElement,new String(te.longDescription).trim(),""+val,(String)jlistValue.getSelectedValue(),true,1,te.priority,te.exceptions[0]);	  
        }
        rList.refreshList();
    }*/

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) 
    {
        if(evt.getSource()==jSlider2)
        {
            if(jSlider2.getValue()<10)
            {
                jlOra.setText(" 0"+jSlider2.getValue()+" ");
            }
            else
            {
                jlOra.setText(" "+jSlider2.getValue()+" ");                                   
            }
        }
    }
		
    public  void parse(String str_sorg,int pos)
    {	
        if (str_sorg.indexOf("*")==-1)
        {
            strV.addElement(str_sorg);
            return;
        }
        else
        {
            String appo=str_sorg.substring(pos,pos+1);

            //System.out.println("STR: "+str_sorg+"  POS: "+pos+"  CHAR: "+appo);
            char strAPPO[]=new char[str_sorg.length()];
            strAPPO=str_sorg.toCharArray();

            if (appo.equals("*"))
            {
                for(int i=0;i<10;i++)
                {
                        strAPPO[pos]=(char)(48+i);	
                        parse(new String(strAPPO),pos+1);
                }
            }else
            {
                parse(new String(strAPPO),pos+1);
            }
        }
    }
		
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Metodo incaricato a settare le icone di default delle varie restrizioni, in caso ci siano errori nella configurazione.
     * </font></font></p></pre>  
     * @param str stringa descrittiva dell'Elemento di Traffico caratterizzante la restrizione selezionata.
     */
    public String approssimaIcona(String str)
    {
            //*******************************
            //	Gestione icone di default
            //*******************************
            //System.out.println("Desc_: "+str);

        if(str.equals("FE")||str.equals("FU")||str.equals("TE")||str.equals("TU"))
            return "trunk.gif";
        if(str.equals("CEE")||str.equals("CEU"))
            return "ctrlelem.gif";
        if(str.equals("CCE")||str.equals("CCO"))
            return "called_ac.gif";
        if(str.equals("CAU"))
           return "inc.gif";
        if(str.equals("HOUR")||str.equals("ORA_STOP"))
            return "hour.gif";
        if(str.equals("SRV_FL_NAZ")||str.equals("FL_NAZ")||str.equals("FL_NAZ_CTO")||str.equals("FL_NAZ_CTE")||str.equals("FL_INT_CTO")||str.equals("FL_INT_CTE"))
            return "domestic.gif";
        if(str.equals("IRI"))
            return "iri.gif";
        if(str.equals("OCO")||str.equals("NUMCIFREU")||str.equals("NUM_U_CTE")||str.equals("NUM_U"))
            return "office_code.gif";
        if(str.equals("CAE"))
            return "inc.gif";
        if(str.equals("ISTR"))
            return "inter_link.gif";
        if(str.equals("RFI"))
            return "anomalie.gif";
        if(str.equals("ISDN"))
            return "isdn.gif";
        if(str.equals("TELESRV"))
            return "fax.gif";
        if(str.equals("SRV")||str.equals("SRVEXT"))
            return "service1.gif";
        if(str.equals("BEARER"))
            return "64.gif";
        if(str.equals("ACE")||str.equals("ACO")||str.equals("ACOI")||str.equals("ACEI"))
            return "ac.gif";
        if(str.equals("OVFL"))
            return "inter_link.gif";

        return "under.gif";
    }
}
