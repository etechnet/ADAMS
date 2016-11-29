package net.etech.loadconfig;

import java.awt.Cursor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_Vector_TAB_CONFIG extends java.util.Vector implements Runnable 
{

    private String  itemName_NTM_InData_TE  = ("TIPO_DI_CONFIGURAZIONE,"+
                                                "POSIZIONE_ELEMENTO,"+      //ID Univoco progressivo contatore  // --- INPUT DATA
                                                "POSIZIONE_CAMPO_DR,"+     //field     se -1 no DR            // --- INPUT DATA  
                                                "TIPO_CAMPO,"+              //type                              // --- INPUT DATA
                                                "SIZE_CAMPO_DR,"+          //type size                         // --- INPUT DATA
                                                "FLAG_ARRAY,"+              //is array                          // --- INPUT DATA
                                                "NUMERO_ELEM_ARRAY,"+       //array size                        // --- INPUT DATA
                                                "LABEL_DR_NORMALIZZ,"+     //description DR                   // --- INPUT DATA
                                                "SE_INDICE,"+               //Identifica se Indice              // --- INPUT DATA    
                                                "VALORE_AGGREGAZIONE_DEF,"+ //Per Configurazione Indice         // --- INPUT DATA   
                                                "VALORE_AGGREGAZIONE,"+     //Per Configurazione Indice         // --- INPUT DATA         
                                                "LENGTH_VALORE_START,"+     //Per Configurazione Indice         // --- INPUT DATA         
                                                "SUFF_VALORE_INDICE,"+      //Per Configurazione Indice         // --- INPUT DATA    
                                                "DATA_INIZIO_INDICE,"+      //Per Configurazione Indice         // --- INPUT DATA         
                                                "DATA_FINE_INDICE,"+        //Per Configurazione Indice         // --- INPUT DATA       
                                                "TAG,"+                     //TAG                               // --- TRAFFIC ELEMENT
                                                "DESCRIPTION,"+             //description TE                    // --- TRAFFIC ELEMENT
                                                "TIPO_OGGETTO_GUI,"+        //GUI Object                        // --- TRAFFIC ELEMENT
                                                "RANGE_VAL,"+                   //Value Range                       // --- TRAFFIC ELEMENT
                                                "VALORE_DEFAULT,"+          //Default Value                     // --- TRAFFIC ELEMENT
                                                "ICONA,"+                   //Pixmap URL                        // --- TRAFFIC ELEMENT
                                                "RAPPRESENTAZ_ESADECIMALE,"+//Hex.Input                         // --- TRAFFIC ELEMENT
                                                "PRIORITA,"+                //Prioriry                          // --- TRAFFIC ELEMENT
                                                "OPERATORI_RESTRIZIONE,"+   //Compare Selection                 // --- TRAFFIC ELEMENT
                                                "LUNG_ELEMENTO_CHIAVE,"+    //Subkey Length                     // --- TRAFFIC ELEMENT
                                                "PLUGIN,"+                  //PATH                              // --- TRAFFIC ELEMENT
                                                "PLUGIN_PATH,"+             //PLUGIN_PATH                       // --- TRAFFIC ELEMENT
                                                "PLUGIN_GUI,"+              //PLUGIN_GUI                              // --- TRAFFIC ELEMENT
                                                "DEFAULT_RESTRICTION,"+     //default restriction               // --- TRAFFIC ELEMENT
                                                "FFENABLED,"+               //enabled Free Format Relation      // --- TRAFFIC ELEMENT 
                                                "ALWAYS_SHOW_REPORT,"+      //Always show in thr report         // --- TRAFFIC ELEMENT
                                                "LINK_TE");                 //Link                              // --- TRAFFIC ELEMENT
    
    public ADAMS_Vector_TAB_CONFIG() {
        super();
    }
    
    public ADAMS_Vector_TAB_CONFIG(String nameConfig) {
        super();
        this.TIPO_DI_CONFIG=nameConfig;
    }
    
    public ADAMS_Vector_TAB_CONFIG(javax.swing.JPanel panle_parent) {
        super();
        this.ADAMS_IData_parent = panle_parent;
    }
    
    public boolean isAlive()
    {
        try
        {
            return th.isAlive();
        }
        catch(Exception e)
        {
           // System.out.println("TH null :: isAlive");
            return false;
        }
    }

    public boolean read_AllItem(int operation)
    {
        OperationTH = operation;
        
        th = null;
        th = new Thread(this,"read_AllItem RS");
        Thread_Work = true;
        th.start();
        
        while(Thread_Work)
        {
            try
            {
                this.th.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("*************** ritorno RUN -4- ");
        return true;
    }
    
    public void run()
    {     
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("**** Entro RUN -2- ");

        if(ADAMS_IData_parent != null)
        {
            ADAMS_IData_parent.setCursor(Cur_wait);
        }
        
        if( (OperationTH == ADAMS_GlobalParam.read_DR) || (OperationTH == ADAMS_GlobalParam.read_TE) || (OperationTH == ADAMS_GlobalParam.read_GHOST) 
            || (OperationTH == ADAMS_GlobalParam.read_DR_TE_for_INDEX) || (OperationTH == ADAMS_GlobalParam.read_ONLY_TRUE_INDEX) ) //read_AllItem(int operation)
        {      
            String OnlyDR ="and POSIZIONE_ELEMENTO !=-2 and POSIZIONE_CAMPO_DR > 0 order by POSIZIONE_CAMPO_DR";
            if(OperationTH == ADAMS_GlobalParam.read_TE)
                OnlyDR = "and POSIZIONE_ELEMENTO !=-2 order by TAG";
            else if(OperationTH == ADAMS_GlobalParam.read_GHOST)
                OnlyDR = "and POSIZIONE_ELEMENTO =-2 order by TAG";
            else if (OperationTH == ADAMS_GlobalParam.read_DR_TE_for_INDEX)
                OnlyDR = "and POSIZIONE_ELEMENTO !=-2 order by POSIZIONE_CAMPO_DR";
            else if (OperationTH == ADAMS_GlobalParam.read_ONLY_TRUE_INDEX)
                OnlyDR = "and ( POSIZIONE_CAMPO_DR > 0  or (POSIZIONE_CAMPO_DR= -1 and SE_INDICE='Y')) order by POSIZIONE_CAMPO_DR";

            //NON LEGGE TE_GHOST_RELATION --> ADAMS_GlobalParam.TE_GHOST_POSIZIONE_ELEMENTO = -2
            
            String sel_ConfigNtm = "select "+itemName_NTM_InData_TE+" from tab_config"+
                                    " where TIPO_DI_CONFIGURAZIONE = '"+TIPO_DI_CONFIG+"' "+OnlyDR;
                              
	    System.out.println("sel ----> "+sel_ConfigNtm);
            
            Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_ConfigNtm,SQLStatement);

            //System.out.println("-------------------");
            
            this.removeAllElements(); //elimino tutti i Input Data
            
            if(rs != null)
            {
                try
                {
                    while ( rs.next ( ) ) 
                    {
                        
                        ADAMS_TAB_CONFIG row_TAB_CONFIG= new ADAMS_TAB_CONFIG();
                    //*** Campi input DATA -- NOT Null sul DB ***//
                        row_TAB_CONFIG.TIPO_DI_CONFIGURAZIONE     = rs.getString ("TIPO_DI_CONFIGURAZIONE");
                        row_TAB_CONFIG.POSIZIONE_ELEMENTO         = rs.getInt ("POSIZIONE_ELEMENTO");
                        row_TAB_CONFIG.POSIZIONE_CAMPO_DR        = rs.getInt ("POSIZIONE_CAMPO_DR");
                        row_TAB_CONFIG.TIPO_CAMPO                 = rs.getInt ("TIPO_CAMPO");
                        row_TAB_CONFIG.SIZE_CAMPO_DR             = rs.getInt ("SIZE_CAMPO_DR");
                        row_TAB_CONFIG.FLAG_ARRAY                 = rs.getString ("FLAG_ARRAY").charAt(0); 
                        row_TAB_CONFIG.NUMERO_ELEM_ARRAY          = rs.getInt ("NUMERO_ELEM_ARRAY");
                        row_TAB_CONFIG.LABEL_DR_NORMALIZZ        = rs.getString ("LABEL_DR_NORMALIZZ");
                        
                        
                    //*** Campi Traffic Element - i dati potrebbero non essere presenti sul DB ***//
                       
                        if(OperationTH == ADAMS_GlobalParam.read_TE)
                        {
                            if(rs.getString ("TAG") != null)
                                row_TAB_CONFIG.TAG = rs.getString("TAG");

                            if(rs.getString ("DESCRIPTION") != null)
                                row_TAB_CONFIG.DESCRIPTION = rs.getString ("DESCRIPTION");

                            row_TAB_CONFIG.TIPO_OGGETTO_GUI = rs.getInt ("TIPO_OGGETTO_GUI");

                            if(rs.getString ("RANGE_VAL") != null)
                                row_TAB_CONFIG.RANGE_VAL = rs.getString ("RANGE_VAL");

                            row_TAB_CONFIG.VALORE_DEFAULT = rs.getInt ("VALORE_DEFAULT");

                            if(rs.getString ("ICONA") != null)
                                row_TAB_CONFIG.ICONA = rs.getString ("ICONA");

                            if(rs.getString ("RAPPRESENTAZ_ESADECIMALE") != null)
                                row_TAB_CONFIG.RAPPRESENTAZ_ESADECIMALE = rs.getString ("RAPPRESENTAZ_ESADECIMALE").charAt(0);

                            row_TAB_CONFIG.PRIORITA = rs.getInt ("PRIORITA");

                            if(rs.getString ("OPERATORI_RESTRIZIONE") != null)
                                row_TAB_CONFIG.OPERATORI_RESTRIZIONE = rs.getInt ("OPERATORI_RESTRIZIONE");

                            row_TAB_CONFIG.LUNG_ELEMENTO_CHIAVE = rs.getInt ("LUNG_ELEMENTO_CHIAVE");

                            if(rs.getString ("PLUGIN") != null)
                                row_TAB_CONFIG.PLUGIN = rs.getString ("PLUGIN");

                            if(rs.getString ("PLUGIN_PATH") != null)
                                row_TAB_CONFIG.PLUGIN_PATH = rs.getString ("PLUGIN_PATH");
                            
                            if(rs.getString ("PLUGIN_GUI") != null)
                                row_TAB_CONFIG.PLUGIN_GUI = rs.getString ("PLUGIN_GUI");
                            
                            if(rs.getString ("DEFAULT_RESTRICTION") != null)
                                row_TAB_CONFIG.DEFAULT_RESTRICTION = rs.getString ("DEFAULT_RESTRICTION").charAt(0);
                            
                            if(rs.getString ("FFENABLED") != null)
                                row_TAB_CONFIG.FFENABLED = rs.getString ("FFENABLED").charAt(0);
                                        
                            if(rs.getString ("ALWAYS_SHOW_REPORT") != null)
                                row_TAB_CONFIG.ALWAYS_SHOW_REPORT = rs.getString ("ALWAYS_SHOW_REPORT").charAt(0);
                            
                            row_TAB_CONFIG.LINK_TE      = rs.getInt ("LINK_TE");
                            
                        }
                        else if((OperationTH == ADAMS_GlobalParam.read_DR_TE_for_INDEX) || (OperationTH == ADAMS_GlobalParam.read_ONLY_TRUE_INDEX))
                        {
                        
                              /***** PER CONF INDICE ****/
                        
                            row_TAB_CONFIG.SE_INDICE = false;
                            if(rs.getString ("SE_INDICE") != null)
                            {                            
                                char is_index = rs.getString ("SE_INDICE").charAt(0);
                                if(is_index == 'Y') 
                                    row_TAB_CONFIG.SE_INDICE = true;
                            }

                            row_TAB_CONFIG.VALORE_AGGREGAZIONE_DEF    = rs.getInt ("VALORE_AGGREGAZIONE_DEF");
                            row_TAB_CONFIG.VALORE_AGGREGAZIONE        = rs.getInt ("VALORE_AGGREGAZIONE");
                            row_TAB_CONFIG.LENGTH_VALORE_START        = rs.getInt ("LENGTH_VALORE_START");

                            if(rs.getString ("SUFF_VALORE_INDICE") != null)
                                row_TAB_CONFIG.SUFF_VALORE_INDICE         = rs.getString("SUFF_VALORE_INDICE").trim();


                            if(rs.getDate("DATA_INIZIO_INDICE") != null)
                                row_TAB_CONFIG.DATA_INIZIO_INDICE = (rs.getDate("DATA_INIZIO_INDICE").toString()).replaceAll("-","");

                            row_TAB_CONFIG.DATA_INIZIO_INDICE = row_TAB_CONFIG.DATA_INIZIO_INDICE.replaceAll("-","");
                            //System.out.println("row_TAB_CONFIG.DATA_INIZIO_INDICE "+row_TAB_CONFIG.DATA_INIZIO_INDICE);

                            if(rs.getString("DATA_FINE_INDICE") != null)
                                row_TAB_CONFIG.DATA_FINE_INDICE = (rs.getDate("DATA_FINE_INDICE").toString()).replaceAll("-","");
                            //System.out.println("row_TAB_CONFIG.DATA_FINE_INDICE "+row_TAB_CONFIG.DATA_FINE_INDICE);
                            
                            
                            // ---- ELEMENTI del TRAFFIC ELEMENT ------
                            
                             if(rs.getString ("TAG") != null)
                                row_TAB_CONFIG.TAG = rs.getString("TAG");

                            if(rs.getString ("DESCRIPTION") != null)
                                row_TAB_CONFIG.DESCRIPTION = rs.getString ("DESCRIPTION");
                            
                        }
                        
                       /*System.out.println();
                        System.out.println("--- Input DATA ---");
                        System.out.println("TIPO CONFIGURAZIONE ->"+row_TAB_CONFIG.TIPO_DI_CONFIGURAZIONE);
                        System.out.println("ID Univoco elemento ->"+row_TAB_CONFIG.POSIZIONE_ELEMENTO);
                        System.out.println("field               ->"+row_TAB_CONFIG.POSIZIONE_CAMPO_DR);        
                        System.out.println("type                ->"+row_TAB_CONFIG.TIPO_CAMPO);   
                        System.out.println("type size           ->"+row_TAB_CONFIG.SIZE_CAMPO_DR);
                        System.out.println("is array            ->"+row_TAB_CONFIG.FLAG_ARRAY);          
                        System.out.println("array size          ->"+row_TAB_CONFIG.NUMERO_ELEM_ARRAY);               
                        System.out.println("description         ->"+row_TAB_CONFIG.LABEL_DR_NORMALIZZ);
                        System.out.println("--- Traffic Element ---");
                        System.out.println("TAG                 ->"+row_TAB_CONFIG.TAG);
                        System.out.println();*/
                        
                        this.addElement(row_TAB_CONFIG);
                    }
                }catch (Exception ex) 
                { 
                    System.out.println(ex.toString());
                }
            }
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_Vector_TAB_CONFIG-1) SQL Operation " + exc.toString());
            }
        }
        
        
        if(ADAMS_GlobalParam.DEBUG)        
            System.out.println("**** Esco RUN -3- ");
        
        OperationTH = -1;
        
        if(ADAMS_IData_parent != null)
            ADAMS_IData_parent.setCursor(Cur_default);
        
        
        Thread_Work = false;
    }

    
    public int getPOSIZIONE_ELEMENTO(String POSIZIONE_ELEMENTO_space_TAG)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG appo_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)this.elementAt(i);
            String StrCtrl = new String(appo_row_TAB_CONFIG.POSIZIONE_ELEMENTO+" "+appo_row_TAB_CONFIG.TAG);
            if(POSIZIONE_ELEMENTO_space_TAG.compareTo(StrCtrl) == 0)
                return appo_row_TAB_CONFIG.POSIZIONE_ELEMENTO;
        }
        return -1; // getSelectedIndex(0) == str_none("<none>)
    }

    public String getPOSIZIONE_ELEMENTO_space_TAG(int POSIZIONE_ELEMENTO,String no_present)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG appo_row_TAB_CONFIG = (ADAMS_TAB_CONFIG)this.elementAt(i);
            if(POSIZIONE_ELEMENTO == appo_row_TAB_CONFIG.POSIZIONE_ELEMENTO)
                return (new String(appo_row_TAB_CONFIG.POSIZIONE_ELEMENTO+" "+appo_row_TAB_CONFIG.TAG));
        }
        return no_present;
    }
    
    public String getTagTE(int posiz_elem)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)this.elementAt(i);
            if( TE.POSIZIONE_ELEMENTO == posiz_elem)
                return (new String(TE.TAG));
        }
        return "error code";
    }
    
    public String getDescTE(int posiz_elem)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)this.elementAt(i);
            if( TE.POSIZIONE_ELEMENTO == posiz_elem)
            {
                String Desc_or_Tag = "";
                
                if(TE.DESCRIPTION.length() > 0)
                    Desc_or_Tag = new String(TE.DESCRIPTION);
                else
                    Desc_or_Tag = new String(TE.TAG);
                
                return (Desc_or_Tag);
            }
        }
        return "";
    }
    
    public int getPosCampDR_WithID(int posiz_elem)
    {
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)this.elementAt(i);
            if( TE.POSIZIONE_ELEMENTO == posiz_elem)
                return TE.POSIZIONE_CAMPO_DR;
        }
        return -1;
    }
    
    public int countElementwith_DEFAULT_RESTRICTION()
    {
        int countElement_NO_DEF_REST = 0;
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)this.elementAt(i);
            if( TE.DEFAULT_RESTRICTION == 'Y')
                countElement_NO_DEF_REST ++;
        }
        return countElement_NO_DEF_REST;
    }
    
    public Vector getIDELEMENTO_DEFAULT_RESTRICTION()
    {
        Vector V_IDELEMENTO_DEF_REST = new Vector();
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_CONFIG TE = (ADAMS_TAB_CONFIG)this.elementAt(i);
            if( TE.DEFAULT_RESTRICTION == 'Y')
                V_IDELEMENTO_DEF_REST.addElement(new Integer(TE.POSIZIONE_ELEMENTO));
        }
        return V_IDELEMENTO_DEF_REST;
    }
    
    private Cursor Cur_default  			= new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     			= new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     			= new Cursor(Cursor.HAND_CURSOR);   
    private javax.swing.JPanel ADAMS_IData_parent 	= null;
    private Thread th           			= null;
    private boolean Thread_Work  			= false;
    private int OperationTH   				= -1;
    private String TIPO_DI_CONFIG			= "";
   

    
}

