import java.applet.AppletContext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;


public class AppletAspetto3 extends JPanel {

    // icone cartelle
    //private static final ImageIcon ICON_FOLDER_FULL = new ImageIcon("C:/Documents and Settings/Adolfo/Documenti/Immagini/folder_full_ico.gif");
    //private static final ImageIcon ICON_FOLDER_EMPTY = new ImageIcon("C:/Documents and Settings/Adolfo/Documenti/Immagini/folder_empty_ico.gif");
    private ImageIcon ICON_FOLDER_FULL;
    private ImageIcon ICON_FOLDER_FULL_over;
    private ImageIcon ICON_DOC;
    private ImageIcon ICON_DOC_ON;
    private ImageIcon ICON_FOLDER_EMPTY;
    private java.awt.Cursor Cur_hand     = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    
    // url principale
    //private static final String ROOTURL = "http://localhost:8080/rda/dir3.php";
     private String ROOTURL;
    // ultimo url eseguito
    private String execUrl = null;

    private JScrollPane jsp = new JScrollPane();
    private JPanel PannelloRoot = new JPanel(new BorderLayout());
    private JPanel PannelloMenu = new JPanel( new FlowLayout(FlowLayout.CENTER) );
    private JButton refresh = new JButton();
    private JButton indietro = new JButton();
    private String str_desc_Button = "° Directory";
    private String openFolder;   
  
    private java.util.Vector V_Date_and_Report;
    
    //private JMenuBar jmb = new JMenuBar();

    // colore
    Color grigio = new Color(230, 230, 230);

    public AppletAspetto3() {
    }
    
    public void init() {
        try {
            ICON_FOLDER_FULL        = new javax.swing.ImageIcon(getClass().getResource("/images/folder_blue_big.png"));
            ICON_FOLDER_FULL_over   = new javax.swing.ImageIcon(getClass().getResource("/images/folder_blue_doc.png"));
            ICON_FOLDER_EMPTY       = new javax.swing.ImageIcon(getClass().getResource("/images/folder_red.png"));  
            ICON_DOC                = new javax.swing.ImageIcon(getClass().getResource("/images/document.png"));
            ICON_DOC_ON             = new javax.swing.ImageIcon(getClass().getResource("/images/document_on.png"));
            ROOTURL = staticLib.path_report_job+"dir3.php?root_queue="+staticLib.codaNumber;
        //System.out.println("urljob-->" +ROOTURL);
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
    }

    public void stop() {
    }

    public void destroy() {
    }
    
    static {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //this.getContentPane().setLayout( null );
        // imposto colore all'applet
        refresh.setBackground(grigio);
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_refresh.jpg")));
        refresh.setToolTipText("refresh");
        refresh.setBorderPainted(false);
        refresh.setContentAreaFilled(false);
        refresh.setFocusPainted(false);
        refresh.setMargin(new java.awt.Insets(0, 0, 0, 0));
        refresh.setMaximumSize(new java.awt.Dimension(100, 22));
        refresh.setMinimumSize(new java.awt.Dimension(100, 22));
        refresh.setPreferredSize(new java.awt.Dimension(100, 22));
        refresh.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_refresh_press.jpg")));
        refresh.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_refresh_over.jpg")));
        refresh.setCursor(Cur_hand);
        
        indietro.setBackground(grigio);
        indietro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back.jpg")));
        indietro.setToolTipText("Indietro");
        indietro.setBorderPainted(false);
        indietro.setContentAreaFilled(false);
        indietro.setFocusPainted(false);
        indietro.setMargin(new java.awt.Insets(0, 0, 0, 0));
        indietro.setMaximumSize(new java.awt.Dimension(100, 22));
        indietro.setMinimumSize(new java.awt.Dimension(100, 22));
        indietro.setPreferredSize(new java.awt.Dimension(100, 22));
        indietro.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back_press.jpg")));
        indietro.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_back_over.jpg")));
        indietro.setCursor(Cur_hand);
        
        this.setBackground(grigio);

        this.execUrl = this.ROOTURL;

        this.impostaPannelloCartelle(this.execUrl);

        this.PannelloRoot.setBackground(grigio);
        this.PannelloRoot.add(this.jsp, BorderLayout.CENTER);
        
        // imposto il menu
        this.impostaMenu();
        this.PannelloMenu.setBackground(grigio);
        this.PannelloRoot.add(this.PannelloMenu, BorderLayout.NORTH);
        this.indietro.setVisible(false);
        
        //imposto il pannello root nell'applet
        this.setLayout(new BorderLayout());
        this.add(PannelloRoot,BorderLayout.CENTER);
    }

    /********************** Gestione Eventi *****************************/

    /**
     * gestione evento pressione pulsante cartella
     * 
     * @param e
     */
    private void buttonCartella_actionPerformed(ActionEvent e) 
    {       
        String nomeCartella = e.getActionCommand();        
        
        openFolder = nomeCartella;
        
        nomeCartella = nomeCartella.replaceAll(str_desc_Button,"").trim();

        //?utenza=mauro&sessionid=xsf4shxA
        // rimuovo i pannelli nell'applet
        this.PannelloRoot.remove(this.jsp);         
        this.execUrl = this.ROOTURL + "&dir=" + nomeCartella;        


        try {
        
            this.impostaPannelloDettaglio(this.execUrl);
            
        } catch (SAXException f) {
            f.printStackTrace();
        } catch (IOException f) {
             f.printStackTrace();
        } catch (ParserConfigurationException f) {
             f.printStackTrace();
        }
        
        //imposto il pannello principale nell'applet
        this.PannelloRoot.add(this.jsp);
        
        this.indietro.setVisible(true);
        
        // refresh applet
        this.PannelloRoot.validate();
                
    }
    
    /**
     * gestione evento selezione file
     * 
     * @param e
     * @param url
     * 
     */
    private void buttonLink_actionPerformed(ActionEvent e, URL url) {
        //AppletContext context = getAppletContext();
        //context.showDocument(url, "_blank");
       
        ((JRadioButton)e.getSource()).setIcon(ICON_DOC_ON);
        java.applet.AppletContext myContext = staticLib.applet_corrente.getAppletContext();
        myContext.showDocument( url, "_blank");
    }

    /**
     * gestione evento pressione pulsante refresh
     * 
     * @param e
     */
    private void buttonRefresh_actionPerformed(ActionEvent e) {
                
        // rimuovo i pannelli nell'applet
        this.PannelloRoot.remove(this.jsp);
         
        //this.execUrl = this.ROOTURL + "&dir=" + nomeCartella;
        
        if ( this.execUrl.lastIndexOf("&dir") > 0 )
        {
            try {
            
                this.impostaPannelloDettaglio(this.execUrl);
                
            } catch (SAXException f) {
                f.printStackTrace();
            } catch (IOException f) {
                 f.printStackTrace();
            } catch (ParserConfigurationException f) {
                 f.printStackTrace();
            }
        } else {
            try {
                this.impostaPannelloCartelle(this.execUrl);
            } catch (SAXException f) {
                 f.printStackTrace();
            } catch (IOException f) {
                 f.printStackTrace();
            } catch (ParserConfigurationException f) {
                 f.printStackTrace();
            }
        }
        
        //imposto il pannello principale nell'applet
        this.PannelloRoot.add(this.jsp);
        
        // refresh applet
        this.PannelloRoot.validate();
    }

    /**
     * gestione evento pressione pulsante indietro
     * 
     * @param e
     */
    private void buttonIndietro_actionPerformed(ActionEvent e) {
    
        // rimuovo i pannelli nell'applet
        this.PannelloRoot.remove(this.jsp);
        
        this.execUrl = this.ROOTURL;

        try {
            this.impostaPannelloCartelle(this.execUrl);
        } catch (SAXException f) {
             f.printStackTrace();
        } catch (IOException f) {
             f.printStackTrace();
        } catch (ParserConfigurationException f) {
             f.printStackTrace();
        }

        //imposto il pannello principale nell'applet
        this.PannelloRoot.add(this.jsp);
        
        this.indietro.setVisible(false);
        
        // refresh applet
        this.PannelloRoot.validate();
    }

    /********************** Fine Gestione Eventi *****************************/

    /********************** Fine Gestione Menu *****************************/
    
    private void impostaMenu() {

        
        javax.swing.JLabel label_queue = new javax.swing.JLabel("Directory Queue "+staticLib.codaNumber);
        this.PannelloMenu.add(label_queue);
        
        this.refresh.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonRefresh_actionPerformed(e);
                    }
                });
        this.PannelloMenu.add(this.refresh);
        
        this.indietro.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonIndietro_actionPerformed(e);
                    }
                });
        this.PannelloMenu.add(this.indietro);
        
    }
     
    /********************** Fine Gestione Menu *****************************/

    /******************* Gestione Pannello Dettaglio ***************************/
    
    private void impostaPannelloDettaglio(String url) throws ParserConfigurationException, 
                                                     SAXException, 
                                                     IOException {
    
        V_Date_and_Report = new java.util.Vector();
        //System.out.println("V_Date_and_Report istanzio");
                                         
        
        // ricavo la mappa delle cartelle
        HashMap hm = this.ricavaRamiDoc(url);
        
        JPanel jp_TotDettaglio = new JPanel();
        jp_TotDettaglio.setLayout(new java.awt.BorderLayout());
        jp_TotDettaglio.setBackground(grigio);
                    
        JPanel p_box = new JPanel();
        p_box.setLayout(new javax.swing.BoxLayout(p_box, javax.swing.BoxLayout.Y_AXIS));
        p_box.setBackground(grigio);
        
        ButtonGroup group = new ButtonGroup(); 
        
        JLabel namefolder = new JLabel(openFolder);
        namefolder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        namefolder.setIcon(ICON_FOLDER_FULL_over);                
        jp_TotDettaglio.add(namefolder, java.awt.BorderLayout.NORTH);        
        
        //*******************************
        /*for(int i=0; i<V_Date_and_Report.size(); i++)
        {            
            String[] appo;
            
            appo = (String[])V_Date_and_Report.elementAt(i);
            System.out.println("Vet  "+appo[0]+" - "+appo[1]);
        }*/
        //*******************************
       
        if ( hm != null )  
        {
            
            java.util.Vector v_AppoButtonOrder = new java.util.Vector();        
            Iterator itRami = hm.keySet().iterator();
            
            while (itRami.hasNext()) 
            {
                String keyLink = (String)itRami.next();
                Object obj = hm.get(keyLink);
                
                if (obj instanceof FileHtml) 
                {
                    FileHtml fh = (FileHtml)obj;
                    final URL link = fh.getUrl();
                    
                    JRadioButton button = new JRadioButton(keyLink);
                    button.setIcon(ICON_DOC);
                    button.setFont(staticLib.fontA10);
                    button.setBorderPainted(false);
                    button.setVerticalTextPosition(SwingConstants.BOTTOM);
                    button.setBackground(grigio);
                    button.setBorderPainted(false);
                    button.setContentAreaFilled(false);
                    button.setFocusPainted(false);
                    //button.setMargin(new java.awt.Insets(10, 0, 0, 0));
                   // button.setMaximumSize(new java.awt.Dimension(300, 30));
                   // button.setMinimumSize(new java.awt.Dimension(300, 30));
                   // button.setPreferredSize(new java.awt.Dimension(300, 30));
                    button.setCursor(Cur_hand);                                       
                    
                    javax.swing.JLabel label_data = new javax.swing.JLabel();
                    label_data.setText(" "+getDateReport(keyLink)+" ");
                    
                    //label_data.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                    label_data.setEnabled(false);
                                                            
                    JPanel jp_date_report = new JPanel();
                    jp_date_report.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
                    jp_date_report.setMaximumSize(new java.awt.Dimension(800, 35));
                    jp_date_report.setMinimumSize(new java.awt.Dimension(800, 35));
                    jp_date_report.setPreferredSize(new java.awt.Dimension(800, 35));
                    
                    jp_date_report.add(label_data);
                    jp_date_report.add(button);
                                        
                    jp_date_report.setBackground(grigio);                    
                    group.add(button);
                    
                    button.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    buttonLink_actionPerformed(e, link);
                                }
                            });
                            
                    //p_box.add(jp_date_report);
                    v_AppoButtonOrder.addElement(jp_date_report);
                }
            }
            
            ordinaVector_STR(v_AppoButtonOrder);
            
            for(int c=0; c<v_AppoButtonOrder.size(); c++)
                p_box.add((JPanel)v_AppoButtonOrder.elementAt(c));
        } 
        else {
            JLabel l = new JLabel("- Empty Folder! -");
            l.setFont(staticLib.fontA10);            
            p_box.add(l);
        }
        
        // creo pannello con le scrollbar che contiene il pannello delle cartelle
        JScrollPane PannelloPrincipale = new JScrollPane();        
        PannelloPrincipale.setViewportView(p_box);
        PannelloPrincipale.setMaximumSize(new java.awt.Dimension(500, 400));
        PannelloPrincipale.setMinimumSize(new java.awt.Dimension(500, 400));
        PannelloPrincipale.setPreferredSize(new java.awt.Dimension(500, 400));
        PannelloPrincipale.getVerticalScrollBar().setUnitIncrement(30);
        
        jp_TotDettaglio.add(PannelloPrincipale, java.awt.BorderLayout.CENTER); 
                
        JScrollPane jsp_dettaglio = new JScrollPane();
        jsp_dettaglio.setViewportView(jp_TotDettaglio);
        this.jsp = jsp_dettaglio;
        
    }
    
    private String getDateReport(String nameReport)
    {
        for(int i=0; i<V_Date_and_Report.size(); i++)
        {        
            String[] appoDataReport = (String[])V_Date_and_Report.elementAt(i);
            
            if(nameReport.equals(appoDataReport[1]))
                return appoDataReport[0];
                           
        }
        return ""; 
    }
    
    public boolean ordinaVector_STR(java.util.Vector vect)
    {
        if(vect != null)
        {
            
            int len=vect.size();
            for(int i=0;i<len-1;i++)
            {
                    int min=i;
                    
                    for(int j=i+1;j<len;j++)
                    {
                        JPanel jpAppo1 = (JPanel)vect.elementAt(j);  
                        String str1 = ((JLabel)(jpAppo1.getComponent(0))).getText();
                        
                        JPanel jpAppo2 = (JPanel)vect.elementAt(min);
                        String str2 = ((JLabel)(jpAppo2.getComponent(0))).getText();
                            
                        if( str1.compareTo(str2) > 0 )
                            min=j;          
                    }

                    JPanel appo=(JPanel)vect.elementAt(i);
                    JPanel appo1=(JPanel)vect.elementAt(min);

                    vect.removeElementAt(i);		
                    vect.insertElementAt(appo1,i);
                    vect.removeElementAt(min);
                    vect.insertElementAt(appo,min);
            }

        }
        return true;
        
    }
    
    /******************* Fine Gestione Pannello Dettaglio ***************************/

    /******************* Gestione Pannello Cartelle ***************************/
    
    private void impostaPannelloCartelle(String url) throws ParserConfigurationException, 
                                                    SAXException, IOException {

        // ricavo la mappa delle cartelle
        //System.out.println("URL:"+url);
        HashMap hm = this.ricavaRamiDoc(url);

        // creo il pannello contenente le cartelle
        if(hm != null)
        	this.jsp = this.creaPannelloCartelle(hm);

    }

    private JScrollPane creaPannelloCartelle(HashMap rami) {
        JPanel PannelloCentrale = new JPanel();

        // stabilisco dimensione griglia
        int numRami = rami.size();
        int numRows = numRami / 5;
        if ((numRami % 5) != 0)
            numRows++;

        // creo la griglia
        GridLayout gl = new GridLayout(numRows, 5);

        // imposto al pannello centrale un GridLayout
        PannelloCentrale.setLayout(gl);
        PannelloCentrale.setBackground(grigio);

        // aggiungo bottoni nelle caselle del pannello centrale
        this.setContentGridLayoutCells(rami, PannelloCentrale);

        // creo pannello con le scrollbar che contiene il pannello delle cartelle
        JScrollPane PannelloPrincipale = 
            new JScrollPane(PannelloCentrale, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return PannelloPrincipale;
    }


    /**
     * riempie il GridLayout del pannello centrale con le cartelle
     * ottenute dal documento xml
     * 
     * @param rami
     * @param PannelloCentrale
     */
    private void setContentGridLayoutCells(HashMap rami, 
                                           JPanel PannelloCentrale) {
        
        // faccio il sort delle chiavi numeriche per avere un ordinamento
        Object[] arrSKeys = rami.keySet().toArray();
        
        int[] arrIKeys = new int[arrSKeys.length];
        
        //System.out.println("arrIKeys length "+ arrIKeys.length);
        
        for ( int i = 0 ; i < arrIKeys.length ; i++) {
            String nome = (String)arrSKeys[i];
            
            arrIKeys[i] = Integer.parseInt(nome);
        }
        
        Arrays.sort( arrIKeys );
        
        // carico il PannelloCentrale con le directory
        for ( int i = 0 ; i < arrIKeys.length ; i++ )  {
             String nome = "" + arrIKeys[i];
            
            //System.out.println("rami.get(nome) "+ nome);
            Object obj = rami.get(nome);
            
            if ( obj instanceof Directory ) {
                Directory nd = (Directory)obj;
                                
                JButton button = new JButton(nome+" "+str_desc_Button);
                button.setFont(staticLib.fontA10);
                button.setBorderPainted(false);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setBackground(grigio);
                button.setToolTipText("Indietro");
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setMargin(new java.awt.Insets(0, 0, 0, 0));
                button.setCursor(Cur_hand);
                
                
                if (nd != null && nd.isEmpty()) 
                {
                    button.setIcon(ICON_FOLDER_EMPTY);
                    button.setDisabledIcon(ICON_FOLDER_EMPTY);
                    button.setEnabled(false);
                    button.setToolTipText("- Empty dir. -");
                }
                else 
                {
                    button.setToolTipText("Click to open.");
                    button.setIcon(ICON_FOLDER_FULL);
                    button.setPressedIcon(ICON_FOLDER_FULL_over);
                    button.setRolloverIcon(ICON_FOLDER_FULL_over);
                    button.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    buttonCartella_actionPerformed(e);
                                }
                            });
                }

                JPanel PannelloCella = new JPanel();
                PannelloCella.setLayout(new FlowLayout());
                PannelloCella.setBackground(grigio);

                PannelloCella.add(button);

                // aggiungo pulsante al PannelloPrincipale
                PannelloCentrale.add(PannelloCella);
            }
        }
    }

    /**************** Fine Gestione Pannello Cartelle ************************/

    /******************* Parser XML *******************************/
    private HashMap ricavaRamiDoc(String url) throws ParserConfigurationException, 
                                             SAXException, IOException {

        try 
        {
        	DOMTreeModel documento = this.ricavaDocumento(url);
        	return getNodeChilds(documento, (Node)documento.getRoot());
	}
	catch (Exception e) {
            System.out.println("err. ricavaRamiDoc ");
            return null;
        }
    }

    /**
     * 
     * @param url del documento xml
     * @return DOMTreeModel: oggetto gestione documento
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private DOMTreeModel ricavaDocumento(String url) throws ParserConfigurationException, 
                                                            SAXException, 
                                                            IOException {

	try
	{
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	
	        //System.out.println("URL:"+url);
	        Document doc = builder.parse(url);
	
	        DOMTreeModel documento = new DOMTreeModel(doc);
	
	        return documento;
	}
	catch (Exception e) {
            System.out.println("");
            return null;
        }

    }

    /**
     * 
     * @param documento : documento xml ottenuto
     * @param currentNode : nodo da cui estrarre i sottoelementi
     * @return HashMap contenente gli oggetti che rappresentano i nodi
     *          null nel caso il nodo in input sia una foglia
     */
    private HashMap getNodeChilds(DOMTreeModel documento, 
                                  Node currentNode) throws MalformedURLException {

        // controllo se il nodo corrente non ï¿½ una foglia
        if (!documento.isLeaf(currentNode)) {

            HashMap hm = new HashMap();

            int iChildNumber = documento.getChildCount(currentNode);

            // per ogni figlio del nodo corrente
            for (int iChild = 0; iChild < iChildNumber; iChild++) 
            {
                Node nChild = (Node)documento.getChild(currentNode, iChild);

                // verifico che sia un nodo
                short sNodeType = nChild.getNodeType();
                if (sNodeType == Node.ELEMENT_NODE) 
                {
                    // ricavo il nome del nodo
                    
                    String sNodeValue = (String)(documento.getAttributes("name", nChild));   
                    
                    String data = (String)documento.getAttributes("data", nChild);
                    if(sNodeValue != null)
                    {
                        if(data != null)
                        {
                            //System.out.println(data+" --- "+sNodeValue);                            
                            String[] dateReport = {data,sNodeValue};
                            if(V_Date_and_Report != null)
                                V_Date_and_Report.addElement(dateReport);
                        }
                    }
                                           
                    //System.out.println(iChild+" DATA creazione "+(String)documento.getAttributes("data", nChild));
                    //System.out.println(iChild+" "+sNodeValue);
                    
                    String sNodeName = nChild.getNodeName();
                    if (sNodeName.equals("directory")) 
                    {
                        HashMap hmSub = getNodeChilds(documento, nChild);
                        if (hmSub != null) {
                            Directory d = new Directory(sNodeValue, hmSub);
                            hm.put(sNodeValue, d);
                        } else {
                            Directory d = new Directory(sNodeValue, true);
                            hm.put(sNodeValue, d);
                        }
                    } 
                    else 
                    {
                        String url = documento.searchTextInElement(nChild);
                        FileHtml fh = new FileHtml(sNodeValue, url);
                        hm.put(sNodeValue, fh);                        
                    }
                }
            }
            
            if (hm.size() > 0)
                return hm;
            else
                return null;

        } else {
            return null;
        }

    }

    /******************** Fine Parser XML ******************************/
    
}
