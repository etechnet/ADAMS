import java.util.Vector;
import java.awt.Cursor;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
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
 * Classe che estende un JPanel ed implementa Runnable. 
 * La classe invia la richiesta di elaborazione al Master Server, e crea dei widget rappresentanti
 * lo stato di avanzamento dell'elaborazione.
 * Nella classe vengono gestite eventuali eccezzioni dovute alla mancata eleborazione del Master Server 
 * o errori anomali dello stesso.
 * In più la classe estendendo la superclasse Runnable fa si che l'invio della richiesta sia completamente gestito da un thread
 * staccato dal client.
 * </font></font></p>
 * <p align="center">&nbsp;</p>     
 */
public class MDM_JP_Centrali extends javax.swing.JPanel implements Runnable{

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe, istanzia e setta l'oggetto stesso.
     * </font></font></p></pre>  
     * @param jListIcon_Rep riferimento ad una JComboBox, che conterra le URL dei report generati dall Master Server,
     * @param jbSched riferimento ad un oggetto di tipo <b>JButton</b>,nel caso particolare è un riferimento al bottone bSchedule istanziato nella classe MDM_JP_Summary,
     * @param jbSend riferimento ad un oggetto di tipo <b>JButton</b>,nel caso particolare è un riferimento al bottone bSend istanziato nella classe jpSummary.
     * @param jbPurge riferimento ad un oggetto di tipo <b>JButton</b>,nel caso particolare è un riferimento al bottone Purge Report istanziato nella classe jpSummary.
     * @param jProgProcess riferimento ad un oggetto di tipo <b>JProgressBar</b>, per la visualizzazione dell'avenzamento totale della richiesta report.
     * @param jL_status riferimento ad un oggetto di tipo <b>JLabel</b>, per la descrizione in valore percentuale dell'avenzamento totale della richiesta report.
     * @param jbView riferimento ad un oggetto di tipo <b>JButton</b>, nel caso particolare è un riferimento al bottone View Report istanziato nella classe jpSummary.
     */
    /*public MDM_JP_Centrali(JButton jbSched, JButton jbSend,JButton jbPivot, JButton jbPurge,JProgressBar jProgProcess,JLabel jL_status,JButton jbView,
                            JComboBox jCbx_listCriteria,JCheckBox jCkbox_increasing,JCheckBox jCkbox_decreasing,JCheckBox jCkbox_percValue,JCheckBox jCkbox_absValue) 
    {
        
        this.local_struct_param = staticLib.struc_paramsCURRENT; // STRUCT_PARAM interfaccia PRINCIPALE
        
        this.jListIcon_Report = staticLib.summary_jListIconReport;
        this.bSchedule  = jbSched;
        this.bSend      = jbSend;
        this.bPivot     = jbPivot;
        this.bPurge     = jbPurge;
        this.Cbx_listCriteria = jCbx_listCriteria;
        this.Ckbox_increasing = jCkbox_increasing;
        this.Ckbox_decreasing = jCkbox_decreasing;
        this.Ckbox_percValue = jCkbox_percValue;
        this.Ckbox_absValue = jCkbox_absValue;
        this.jProgProcessTOT = jProgProcess;
        this.JL_status_Tot = jL_status;
        this.jbViewReport = jbView;
        
        initComponents();
        setProgressBar_GUI();
    }*/

    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe, istanzia e setta l'oggetto stesso.
     * </font></font></p></pre>  
     * @param jListIcon_Rep riferimento ad una JComboBox, che conterra le URL dei report generati dall Master Server,
     * @param jbSched riferimento ad un oggetto di tipo <b>JButton</b>,nel caso particolare è un riferimento al bottone bSchedule istanziato nella classe MDM_JP_Summary,
     * @param jbSend riferimento ad un oggetto di tipo <b>JButton</b>,nel caso particolare è un riferimento al bottone bSend istanziato nella classe jpSummary.
     * @param jbPurge riferimento ad un oggetto di tipo <b>JButton</b>,nel caso particolare è un riferimento al bottone Purge Report istanziato nella classe jpSummary.
     * @param jProgProcess riferimento ad un oggetto di tipo <b>JProgressBar</b>, per la visualizzazione dell'avenzamento totale della richiesta report.
     * @param jL_status riferimento ad un oggetto di tipo <b>JLabel</b>, per la descrizione in valore percentuale dell'avenzamento totale della richiesta report.
     * @param jbView riferimento ad un oggetto di tipo <b>JButton</b>, nel caso particolare è un riferimento al bottone View Report istanziato nella classe jpSummary.
     */
    public MDM_JP_Centrali(MDM_JP_Summary jp_summary) 
    {
        
        this.local_struct_param = staticLib.struc_paramsCURRENT; // STRUCT_PARAM interfaccia PRINCIPALE
        this.jListIcon_Report = staticLib.summary_jListIconReport;
        this.AutoViewReport = false;
        
        this.bSchedule  = jp_summary.get_jbSchedule();
        this.bSend      = jp_summary.get_jbSend();
        this.bPivot     = jp_summary.get_jB_pivot();
        this.bPurge     = jp_summary.get_jbReset();
        this.jbViewReport = jp_summary.get_jbView();
        this.Cbx_listCriteria = jp_summary.get_jCbx_listCriteria();
        this.Ckbox_increasing = jp_summary.get_jCkbox_increasing();
        this.Ckbox_decreasing = jp_summary.get_jCkbox_decreasing();
        this.Ckbox_percValue = jp_summary.get_jCkbox_percValue();
        this.Ckbox_absValue = jp_summary.get_jCkbox_absValue();
        this.jProgProcessTOT = jp_summary.get_jProgProcess();
        this.JL_status_Tot = jp_summary.get_jL_status();
        
        initComponents();
        setProgressBar_GUI();
    }
    
    
    public MDM_JP_Centrali(JButton jbSend,JProgressBar jProgProcess,JLabel jL_status,STRUCT_PARAMS struct_param,boolean flag_AutoViewReport) 
    {
        this.local_struct_param = struct_param;        
        this.jListIcon_Report = staticLib.summary_jListIconReport;
        this.AutoViewReport = flag_AutoViewReport;
        
        this.bSend = jbSend;
        this.jProgProcessTOT = jProgProcess;
        this.JL_status_Tot = jL_status;
        initComponents();
        setProgressBar_GUI();
    }
    
    
    public void setConfiguration(getConfigFiltro Configuration)
    {
        this.configuration = Configuration;
        this.localReports = new Reports(configuration.get_ReportsSchema());
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        
        setLayout(new java.awt.GridLayout(0, 2));
        
        setBackground(new java.awt.Color(230, 230, 230));
    }//GEN-END:initComponents

    /**
     * Metodo utilizzato per resettare lengthbarre di avenzamento analisi.
     */
    public void resetBar()
    {
        JL_status_Tot.setText("");
        SW_ATTIVE = 0;
        for(int i=0;i<staticLib.centrali.length;i++)
        {            
            if(staticLib.idCentrali[i] != -1)
            {
                jProgProcessTOT.setString("0%");
                jProgProcessTOT.setValue(0);
                jProgProcessTOT.setForeground(java.awt.Color.blue);

                ((JLabel)vLabel.elementAt(i)).setForeground(java.awt.Color.black);
                ((JLabel)vPbar_Label_icon.elementAt(i)).setIcon(ledoff);
                ((JProgressBar)vPbar.elementAt(i)).setString("0%");
                ((JProgressBar)vPbar.elementAt(i)).setValue(0);
                ((JProgressBar)vPbar.elementAt(i)).setForeground(java.awt.Color.blue);
            }
        }
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo ritorna l'URL presente nella lista Report alla posozione specificata.
     * </font></font></p></pre>  
     * @param pos indica la posizione all'interno della JComboBox
     */
    public String[] getURL(int pos)
    {
        return (String[])staticLib.vettURL.elementAt(pos);
    }
    
    public void resetVettURL()
    {
        staticLib.vettURL.removeAllElements();
        staticLib.hTableReport.clear();
    }
    
    public void resetVettURL_pos(int index)
    {
    	String[] appo=getURL(index);
        //System.out.println("Remove URL to hTableReport= "+appo[0]);
        staticLib.hTableReport.remove(appo[0]);
        
        staticLib.vettURL.remove(index);
    }

    private int getPos(int id)
    {
        int appo;
        for(int i=0;i<vLabel.size();i++)
        {
            appo=((Integer)vID.elementAt(i)).intValue();			
            if(appo==id)		
                return i;
        }

        return -1;
    }
    
    private void setProgressBar_GUI()
    {
        int count_sw_Active_GUI = 0;
        for (int n=0; n<staticLib.idCentrali.length; n++)
            if (staticLib.idCentrali[n] != -1)
                count_sw_Active_GUI++;
        
        if(count_sw_Active_GUI < 5)
            count_sw_Active_GUI = 5;
        
        setLayout(new java.awt.GridLayout(0, count_sw_Active_GUI));
        
        for(int i=0;i<staticLib.centrali.length;i++)
        {            
            MDM_JP_ProgBar JP_ProgBar = new MDM_JP_ProgBar();
            //Progress Bar
            JProgressBar jProgrBar_appo = JP_ProgBar.get_jProgressBar();
            JLabel jL_Label_icon = JP_ProgBar.get_JLabelIcon();

            //Label nome Centrale
            JLabel jL_desc_appo = JP_ProgBar.get_JLabelDesc();
            jL_desc_appo.setText(staticLib.centrali[i].trim());

            if(staticLib.idCentrali[i] != -1)
                add(JP_ProgBar);

            vPbar_Label_icon.addElement(jL_Label_icon);
            vPbar.addElement(jProgrBar_appo);
            vLabel.addElement(jL_desc_appo);   
            vID.addElement(new Integer(staticLib.idCentrali[i]));             
        }
    
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Controlla, in caso si fosse verificata un anomalia dal lato del MAster Server, se il thread è ancora attivo:
     * in caso affermativo restituisce <b>true</b>,
     * in caso negativo restituisce <b>false</b>.
     * </font></font></p></pre>  
     */
    public boolean isAlive()
    {
        try
        {
            //System.out.println("TH.isAlive() --> "+TH.isAlive());
            return TH.isAlive();            
        }
        catch(Exception e)
        {   
            return false;
        }
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Avvia l'elaborazione, attivando il metodo run(), il quale provvede ad inviare la richiesta al Master Server, ed avviare 
     * realmente l'elaborazione.
     * </font></font></p></pre>  
     */
    public void startElaboration(int flagElaboration)
    {
        local_struct_param.OutputType = flagElaboration;
	//System.out.println("Elaborazione di tipo: "+flagElaboration);

        resetBar();
        SW_ATTIVE = 0;
        value_ProcessTOT = new int[local_struct_param.Fep.length];

        for(int i=0;i<local_struct_param.Fep.length;i++)
        {
            if(local_struct_param.Fep[i] == 1)
            {
                int ID = staticLib.idCentrali[i];
                if(ID != -1)
                {
                    ((JLabel)vPbar_Label_icon.elementAt(getPos(ID))).setIcon(ledgreen_anim);
                    SW_ATTIVE++;
                }
            }
            value_ProcessTOT[i] = 0;
        }
        
        JL_status_Tot.setText(" Loading...");
        
        TH = new Thread(this,"startElaboration");
        
        TH_EXIT = false;
        TH.start();
        
        if(bSchedule != null)
            bSchedule.setEnabled(false);
        
        if(bPurge != null)
            bPurge.setEnabled(false);

        if(Cbx_listCriteria != null)
        {
            b_statusSort = Ckbox_increasing.isEnabled();
            
            this.Cbx_listCriteria.setEnabled(false);
            this.Ckbox_increasing.setEnabled(false);
            this.Ckbox_decreasing.setEnabled(false);
            this.Ckbox_percValue.setEnabled(false);
            this.Ckbox_absValue.setEnabled(false);
        }
        
        staticLib.Tabella.setEnabledAt(0,false);
        staticLib.Tabella.setEnabledAt(1,false);
        staticLib.Tabella.setEnabledAt(2,false);
        staticLib.Tabella.setEnabledAt(4,false);
    }
    
    /*public void startElaboration(int flagPivot)
    {       
        local_struct_param.OutputType = flagPivot; //1
        resetBar();
        SW_ATTIVE = 0;
        value_ProcessTOT = new int[local_struct_param.Fep.length];
        
        for(int i=0;i<local_struct_param.Fep.length;i++)
        {
            if(local_struct_param.Fep[i] == 1)
            {
                int ID = staticLib.idCentrali[i];
                if(ID != -1)
                {                    
                    ((JLabel)vPbar_Label_icon.elementAt(getPos(ID))).setIcon(ledgreen_anim);
                    SW_ATTIVE++;
                }
            }
            value_ProcessTOT[i] = 0;
        }
        
        JL_status_Tot.setText(" Loading...");
        
        TH = new Thread(this,"startElaboration");
        
        TH_EXIT = false;
        TH.start();
        
        if(bSchedule != null)
            bSchedule.setEnabled(false);
        if(bPurge != null)
            bPurge.setEnabled(false);
        
        
        if(Cbx_listCriteria != null)
        {
            b_statusSort = Ckbox_increasing.isEnabled();
            this.Cbx_listCriteria.setEnabled(false);
            this.Ckbox_increasing.setEnabled(false);
            this.Ckbox_decreasing.setEnabled(false);
            this.Ckbox_percValue.setEnabled(false);
            this.Ckbox_absValue.setEnabled(false);
        }  
        
        staticLib.Tabella.setEnabledAt(0,false);
        staticLib.Tabella.setEnabledAt(1,false);
        staticLib.Tabella.setEnabledAt(2,false);
        staticLib.Tabella.setEnabledAt(4,false);
    }*/
    
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Arresta l'elaborazione, forzando l'arresto del thred avviato, e comunicando al Master server di arrestare il processo 
     * di elaborazione attivato.
     * </font></font></p></pre>  
     */
    public void stopElaboration()
    {
        try
        {
            JL_status_Tot.setText(" Process abort.");
            jProgProcessTOT.setForeground(java.awt.Color.red);
                   
            if(bPurge != null)
                bPurge.setEnabled(true);
            
            if(Cbx_listCriteria != null)
            {
                this.Cbx_listCriteria.setEnabled(true);            
                this.Ckbox_increasing.setEnabled(b_statusSort);
                this.Ckbox_decreasing.setEnabled(b_statusSort);
                this.Ckbox_percValue.setEnabled(true);
                this.Ckbox_absValue.setEnabled(true);
            }
            
            //TH.stop();
            
            TH_EXIT = true;
            try
            {
            	TH.interrupt();
            	TH = null;
            }
            catch (Exception e)
            {
            	System.out.println("err. TH.interrupt() MDM_JP_Centrali.java");	
            }
            
            
            staticLib.CORBA.shutDown();
            staticLib.optionPanel("Abort", "Information",1);
            resetBar();	
            staticLib.Tabella.setEnabledAt(0,true);
            staticLib.Tabella.setEnabledAt(1,true);
            staticLib.Tabella.setEnabledAt(2,true);
            
            if( staticLib.codaNumber != -1 )
            {
                staticLib.Tabella.setEnabledAt(4,true);
                if(bSchedule != null)
                    bSchedule.setEnabled(true);
            }
            else
            {
                staticLib.Tabella.setEnabledAt(4,false);
                if(bSchedule != null)
                    bSchedule.setEnabled(false);
            }

        }catch(Exception ex)
        {
            //System.out.println("Errore nel shutDown");
        }
        setCursor(Cur_default);
    }
    

    
    
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Overloading del metodo run() della superclasse Runnable, questo metodo controlla che la
     * richiesta effettuata dall utente sia valida ed in caso affermativo la inoltra al Master Server
     * aspettando che lo stesso risponda,generando l'URL che contien il report finale.
     * </font></font></p></pre>  
     */
    public void run()
    {
        setCursor(Cur_wait);
        try
        {
            //per TIME_OUT sospeso
            staticLib.FLAG_ELABORATION = true;
            
            Url=new org.omg.CORBA.StringHolder();

            //***************************************************
            //      invio la richiesta al MS.
            //***************************************************
                       
            
            // ******* DEBUG TIME ******
            /*System.out.println();
            System.out.println(" ---------- Send runMDMServerBlocking ---------");
            System.out.println("DATE ANALISYS:");
            if( local_struct_param.ElaborationData != null )
            {                
                for(int i=0; i<local_struct_param.ElaborationData.length; i++)
                {
                    if(local_struct_param.ElaborationData[i]!= null)
                        System.out.println(" -Date["+(i+1)+"]= "+(new String(local_struct_param.ElaborationData[i].dateString).trim())+";");
                    else
                        System.out.println(" -Date["+(i+1)+"]= null;");
                } 
            }
            else
                System.out.print(" - Date = "+local_struct_param.ElaborationData+";");
                
            System.out.println(" ---------------------------------");
            System.out.println();*/
            // ******* END DEBUG TIME ******
            
            
            flagt = staticLib.CORBA.runMDMServerNonBlocking(local_struct_param);
            
            //System.out.println("-------- ID Relazione ---------->"+local_struct_param.Relazione);
            //System.out.println("-------- idReportSchema ----->"+local_struct_param.idReportSchema);

            /*controllo le percentuali */
            boolean finito = false;
            String strURL = "", nameHTML = "", nameCENTRALE= "";
            String name_report_list = null;
            int flagCENTRALI[] = new int[local_struct_param.Fep.length];

            for(int i=0;i<local_struct_param.Fep.length;i++)
            {
                flagCENTRALI[i] = local_struct_param.Fep[i];
            }

            flag2 = false;  // per la gestione NT
            while((finito == false) && (TH_EXIT == false))
            {
                //System.out.println("************* Giro ************* ");
                finito = true;

                //flagt1 = staticLib.mdm_server_serverRef.getRunningStatus(invokeSTATUS);
                flagt1 = staticLib.CORBA.getRunningStatus(invokeSTATUS);
                statusINV = invokeSTATUS.value;

                
                for(int i=0;i<staticLib.centrali.length;i++)
                {
                    int ID_cent = staticLib.idCentrali[i];
                    String nameSW = ((JLabel)vLabel.elementAt(i)).getText()+" ";
                    
                    if(ID_cent == -1)
                        continue;
                    
                    if(flagCENTRALI[i] == 1)
                    {
                        if(staticLib.NTenabled)
                        {
                           // System.out.println("------- ENTRO 1 ------");
                            
                            if(statusINV.percent[0]<999)
                            {
                                if(statusINV.percent[i] == -1) /* errore */
                                {
                                    //((JLabel)vLabel.elementAt(getPos(ID_cent))).setForeground(java.awt.Color.red);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(100);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("100%");
                                    value_ProcessTOT[i] = 100;
                                    
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setForeground(java.awt.Color.red);
                                    ((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledred);
                                    flagCENTRALI[i] = 0;
                                    
                                }
                                else
                                {
                                    int levelBar = statusINV.percent[i];
                                    
                                    if(levelBar == 100)
                                    {
                                        //JL_status_Tot.setText(nameSW+"Format Report ");
                                        levelBar = 98;
                                    }
                                    
                                    if( ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).getValue() < levelBar)
                                    {
                                        //((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledgreen_anim);
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(levelBar);
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString(levelBar+"%");
                                        value_ProcessTOT[i] = levelBar;
                                    }
                                    
                                    finito = false;
                                }
                            }
                            else /* centrale finita */
                            {
                                //((JLabel)vLabel.elementAt(getPos(ID_cent))).setForeground(new java.awt.Color(5,101,5));
                                ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(99);
                                ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("99%");
                                value_ProcessTOT[i] = 99;
                                						
                                flagCENTRALI[i] = 0;
                                
                                if(!flag2) //Report Unico  Network 
                                {
                                    flag2 = true;
                                    strURL = "";	
                                    nameCENTRALE = (new String(statusINV.desc[0])).trim();
                                    nameHTML = (new String(statusINV.url[0])).trim();
                                                                        
                                    if(nameHTML.indexOf("PIVOT") == -1)
                                    {
                                        jListIcon_Report.addElement("item_report_off.png","item_report_off.png",nameCENTRALE+" [ "+nameHTML+" ]");                                      
                                    }
                                    else
                                    {
                                        String namePivotKey = nameCENTRALE+"_"+count_Pivot_file;                                        
                                        jListIcon_Report.addElement("item_report_off.png","item_report_off.png",namePivotKey);
                                        count_Pivot_file++;

                                        // ****** 
                                       // System.out.println("---- CLONE local_struct_param ---- ");
                                       // System.out.println("---- Memorizzo Struct param con  KEY ---->"+namePivotKey);

                                        STRUCT_PARAMS clone_structParam = staticLib.cloneSTRUCT_PARAMS(local_struct_param);   
                                        staticLib.hashT_StructParams.put(namePivotKey,clone_structParam);
                                        // *******
                                    }
                                    
                                    //staticLib.hTableReport.put(nameHTML,new Integer((int)staticLib.selectReport1.getSelectedReportExcel()));
                                    //System.out.println(nameHTML+ " typeReport=" +staticLib.selectReport1.getSelectedReportExcel());
                                                                        
                                    staticLib.hTableReport.put(nameHTML,new Integer((int)localReports.get_ReportExcel(local_struct_param.idReportSchema)));
                                    //System.out.println(nameHTML+ " typeReport=" +localReports.get_ReportExcel(local_struct_param.idReportSchema));
                                    
                                    if(jbViewReport != null)
                                        jbViewReport.setEnabled(true);

                                    strURL = strURL +"\n"+"Centrale: "+nameCENTRALE+ "    Report: "+nameHTML;				
                                    
                                    String[] str_nameHTML_REL = new String[3];
                                    str_nameHTML_REL[0] = new String(nameHTML);
                                    str_nameHTML_REL[1] = new String(staticLib.descRelation);
                                    str_nameHTML_REL[2] = new String(staticLib.AbsoluteValue);
                                    
                                    staticLib.vettURL.addElement(str_nameHTML_REL);
                                    
                                    if(!strURL.equals(""))
                                    {
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(100);
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("100%");
                                        value_ProcessTOT[i] = 100;
                                        
                                        ((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledgreen);
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setForeground(new java.awt.Color(5,101,5));
                                    }
                                }
                                else
                                {
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(100);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("100%");
                                    value_ProcessTOT[i] = 100;

                                    ((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledgreen);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setForeground(new java.awt.Color(5,101,5));
                                }
                                
                            }
                        }
                        else
                        {	    
                          // System.out.println("------- ENTRO 2 ------");
                            
                            if(statusINV.percent[i]<999)
                            {
                                if(statusINV.percent[i] == -1) /* errore */
                                {                                    
                                    //((JLabel)vLabel.elementAt(getPos(ID_cent))).setForeground(java.awt.Color.red);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(100);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("100%");
                                    value_ProcessTOT[i] = 100;
                                    
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setForeground(java.awt.Color.red);
                                    ((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledred);
                                    flagCENTRALI[i] = 0;
                                                                         
                                }
                                else
                                {                   
                                    int levelBar = statusINV.percent[i];
                                    if(levelBar == 100)
                                    {
                                        //JL_status_Tot.setText(nameSW+"Format Report ");
                                        levelBar = 98;
                                    }
                                    
                                    if( ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).getValue() < levelBar )
                                    {
                                        //((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledgreen_anim);
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(levelBar);
                                        ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString(levelBar+"%");
                                        value_ProcessTOT[i] = levelBar;
                                    }
                                    finito = false;
                                }
                            }
                            else /* centrale finita */
                            {
                                //((JLabel)vLabel.elementAt(getPos(ID_cent))).setForeground(new java.awt.Color(5,100,5));
                                ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(99);
                                ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("99%");
                                value_ProcessTOT[i] = 99;
                                
                                flagCENTRALI[i] = 0;
                                                             
                                strURL = "";
                                nameCENTRALE = (new String(statusINV.desc[i])).trim();
                                nameHTML = (new String(statusINV.url[i])).trim();

                                if(nameHTML.indexOf("PIVOT") == -1)
                                {
                                    name_report_list = nameCENTRALE+" [ "+nameHTML+" ]";
                                    jListIcon_Report.addElement("item_report_off.png","item_report_off.png",name_report_list);
                                }
                                else
                                {
                                    name_report_list = nameCENTRALE+"_"+count_Pivot_file;
                                    jListIcon_Report.addElement("item_report_off.png","item_report_off.png",name_report_list);
                                    count_Pivot_file++;
                                    
                                     //******                                     
                                    //System.out.println("---- CLONE local_struct_param ---- ");
                                    //System.out.println("---- Memorizzo Struct param con  KEY ---->"+name_report_list);
                                   
                                    STRUCT_PARAMS clone_structParam = staticLib.cloneSTRUCT_PARAMS(local_struct_param);   
                                    staticLib.hashT_StructParams.put(name_report_list,clone_structParam);
                                    //****** 
                                }
                                
                                //staticLib.hTableReport.put(nameHTML,new Integer((int)staticLib.selectReport1.getSelectedReportExcel()));
                                //System.out.println(nameHTML+ " typeReport(*)=" +staticLib.selectReport1.getSelectedReportExcel());
                                
                                staticLib.hTableReport.put(nameHTML,new Integer((int)localReports.get_ReportExcel(local_struct_param.idReportSchema)));
                                //System.out.println(nameHTML+ " typeReport=" +localReports.get_ReportExcel(local_struct_param.idReportSchema));
                                
                                if(jbViewReport != null)
                                    jbViewReport.setEnabled(true);

                                strURL = strURL+"\n"+"Centrale: "+nameCENTRALE+ "    Report: "+nameHTML;
                                
                                String[] str_nameHTML_REL = new String[3];
                                str_nameHTML_REL[0] = new String(nameHTML);
                                str_nameHTML_REL[1] = new String(staticLib.descRelation);
                                str_nameHTML_REL[2] = new String(staticLib.AbsoluteValue);
                                staticLib.vettURL.addElement(str_nameHTML_REL);

                                if(!strURL.equals(""))
                                {
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setValue(100);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setString("100%");
                                    value_ProcessTOT[i] = 100;
                                    ((JLabel)vPbar_Label_icon.elementAt(getPos(ID_cent))).setIcon(ledgreen);
                                    ((JProgressBar)vPbar.elementAt(getPos(ID_cent))).setForeground(new java.awt.Color(5,101,5));
                                }
                            }	
                        }
                    }
                }
                
                jListIcon_Report.repaint();
                jListIcon_Report.validate();
                jListIcon_Report.getParent().repaint();
                jListIcon_Report.getParent().validate();
                //System.out.println("jListIcon_Report.getParent() "+jListIcon_Report.getParent().toString());
                
                //System.out.println("num centrali/JBar "+value_ProcessTOT.length);
                int valueTOT = 0;
                for(int x=0; x<value_ProcessTOT.length; x++)
                {
                    //System.out.println("valori delle JBar" +value_ProcessTOT[x]);
                    valueTOT = valueTOT + value_ProcessTOT[x];
                }
                
                try
                {
                	valueTOT = (int)(valueTOT/SW_ATTIVE);
                }
                catch(java.lang.ArithmeticException e)
                {
                	//In caso di interruzione TH del report.
                }	
                    
                jProgProcessTOT.setString(valueTOT+"%");
                jProgProcessTOT.setValue(valueTOT);
                JL_status_Tot.setText(" Processing... ["+valueTOT+"%]");
                               
                try
                {
                    if(valueTOT != 100)
                    {
                        //System.out.println("------- SLEEP  ------");
                        Thread.sleep(5000);
                    }
                    //else
                        //System.out.println("Non tardare hai finito... ");
                    
                }catch(InterruptedException e)
                {
                }
            }
            
            if(TH_EXIT == true)
            {
            	//System.out.println("Richiesta -> STOP REQUEST  !! --> "+TH_EXIT  );
                staticLib.FLAG_ELABORATION = false;
            	return;
            }
		
            bSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request.jpg")));
            bSend.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_over.jpg")));
            bSend.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_press.jpg")));
            bSend.setToolTipText(" Send Request ");
            bSend.setEnabled(true);
            
            if(bPivot != null)
            {
                bPivot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot.jpg"))); 
                bPivot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_press.jpg")));
                bPivot.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_over.jpg")));
                bPivot.setToolTipText("Pivot Request");
                bPivot.setEnabled(true);
            }
            
            if(bSchedule != null)
                bSchedule.setEnabled(true);
            
            if(bPurge != null)
                bPurge.setEnabled(true);
            
            if(Cbx_listCriteria != null)
            {
                this.Cbx_listCriteria.setEnabled(true);
                this.Ckbox_increasing.setEnabled(b_statusSort);
                this.Ckbox_decreasing.setEnabled(b_statusSort);
                this.Ckbox_percValue.setEnabled(true);
                this.Ckbox_absValue.setEnabled(true);
            }
            staticLib.CORBA.shutDown();
            
            if(AutoViewReport)
            {   
                try
                {
                    if(name_report_list !=null)
                    {                
                        int risp1 = staticLib.confirmPanel("Report done < "+name_report_list+" >.\nWant to open now?","Information",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.INFORMATION_MESSAGE); 
                        if(risp1 == 0)
                        {
                            jListIcon_Report.setSelectedValue(name_report_list,true);
                            staticLib.selectSummary1.viewReport();
                        }
                    }
                }catch(Exception exx)
                { 
                    System.out.println("- errore AutoViewReport -"); 
                    exx.printStackTrace(); 
                }
            }

        }catch(Exception ex)
        {
            ex.printStackTrace(System.out);
            //***************************************************
            // Elaborazione finita senza successo.
            //***************************************************
            
            //per TIME_OUT riattivato
            staticLib.FLAG_ELABORATION = false;
            
            jProgProcessTOT.setString("100%");
            jProgProcessTOT.setValue(100);
            jProgProcessTOT.setForeground(java.awt.Color.red);
            JL_status_Tot.setText(" Process Failed."); 
            
            setCursor(Cur_default);
            staticLib.optionPanel("Errore nella comunicazione", "Errore",javax.swing.JOptionPane.ERROR_MESSAGE);
            bSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request.jpg")));
            bSend.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_over.jpg")));
            bSend.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send_request_press.jpg")));
            bSend.setToolTipText(" Send Request ");
            bSend.setEnabled(true);
            
            if(bPivot != null)
            {
                bPivot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot.jpg"))); 
                bPivot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_press.jpg")));
                bPivot.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pivot_over.jpg")));
                bPivot.setToolTipText("Pivot Request");
                bPivot.setEnabled(true);  
            }
            
            if(bPurge != null)
                bPurge.setEnabled(true);
            
            if(Cbx_listCriteria != null)
            {
                this.Cbx_listCriteria.setEnabled(true);
                this.Ckbox_increasing.setEnabled(b_statusSort);
                this.Ckbox_decreasing.setEnabled(b_statusSort);
                this.Ckbox_percValue.setEnabled(true);
                this.Ckbox_absValue.setEnabled(true);
            }
        }
        staticLib.Tabella.setEnabledAt(0,true);
        staticLib.Tabella.setEnabledAt(1,true);
        staticLib.Tabella.setEnabledAt(2,true); 

        if( staticLib.codaNumber !=-1 )	
        {
            staticLib.Tabella.setEnabledAt(4,true);
            if(bSchedule != null)
                bSchedule.setEnabled(true);
        }
        else
        {
            staticLib.Tabella.setEnabledAt(4,false);
            if(bSchedule != null)
                bSchedule.setEnabled(false);
        }

        jProgProcessTOT.setString("100%");
        jProgProcessTOT.setValue(100);
        jProgProcessTOT.setForeground( new java.awt.Color(5,101,5) );
        JL_status_Tot.setText(" Ok Done.");
        
        //per TIME_OUT riattivato
        staticLib.FLAG_ELABORATION = false;        
        setCursor(Cur_default);		              

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    
    private JListIcon jListIcon_Report;
    private JButton bSchedule;
    private JButton bSend;
    private JButton bPivot;
    private JButton bPurge;
    
    private JComboBox Cbx_listCriteria;
    private JCheckBox Ckbox_increasing;
    private JCheckBox Ckbox_decreasing;
    private JCheckBox Ckbox_percValue;
    private JCheckBox Ckbox_absValue;

    private JProgressBar jProgProcessTOT;
    private JLabel JL_status_Tot;
    private JButton jbViewReport;
    int[] value_ProcessTOT;
    int SW_ATTIVE = 0;
    
    private Vector vLabel           = new Vector();
    private Vector vID              = new Vector();
    private Vector vPbar            = new Vector();
    private Vector vPbar_Label_icon = new Vector();
    //private Vector vettURL          = new Vector();
    
    private int count_Pivot_file = 0;
    
    private Thread TH = null;
    private boolean TH_EXIT = false;  
    
    private org.omg.CORBA.StringHolder Url;
    private INVOKE_STATUS statusINV;
    public INVOKE_STATUSHolder invokeSTATUS = new INVOKE_STATUSHolder();
    
    private boolean flagt   = false;
    private boolean flagt1  = false;
    private boolean flag2   = false;
    
    private boolean b_statusSort = false;
    
    private javax.swing.ImageIcon ledoff            = new javax.swing.ImageIcon(getClass().getResource("/images/ledoff.gif"));
    private javax.swing.ImageIcon ledgreen          = new javax.swing.ImageIcon(getClass().getResource("/images/ledgreen.gif"));
    private javax.swing.ImageIcon ledgreen_anim     = new javax.swing.ImageIcon(getClass().getResource("/images/ledgreen_anim.gif"));
    private javax.swing.ImageIcon ledred            = new javax.swing.ImageIcon(getClass().getResource("/images/ledred.gif"));
    
    private STRUCT_PARAMS local_struct_param = null;
    
    private Reports localReports;
    private getConfigFiltro configuration;
    private boolean AutoViewReport = false;
}
