/*
 * ADAMS_JP_ReportObject_BodyCell.java
 *
 * Created on 8 agosto 2005, 14.30
 */

/**
 *Luca
 * @author  Raffaele Ficcadenti
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.sql.*;

public class ADAMS_JP_ReportObject_BodyCell extends javax.swing.JPanel { 
    
    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private ReportObject.elementoBase               eb                          = null;
    private String                                  config_NAME_ALIAS           = "";
    private ADAMS_JP_ReportObjectEditor              parent                      = null;
    private int                                     cont1                       = 0;
    private int                                     cont2                       = 0;
    private ADAMS_JF_WIZARDBASE                      wizard                      = null;
    private int                                     old_BODY_SOURCE             = 0;
    private ReportObject.elementoBase               ebINIZIALE                  = null;
    /** Creates new form ADAMS_JP_ReportObject_BodyCell */
    public ADAMS_JP_ReportObject_BodyCell(String config_NAME_ALIAS,ReportObject.elementoBase eb,ADAMS_JP_ReportObjectEditor parent) {
        this.config_NAME_ALIAS=config_NAME_ALIAS;
        this.eb=eb;
        this.parent=parent;
        initComponents();
        
        jT_k.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,2));
        jT_tag.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,15));
        jT_handler.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,40));
        jT_uvs.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,81));
        jT_nd.setDocument(new JTextFieldFilter("01234567",1));
        setFont();
        setCursorWidget();   
        
        if(DEBUG)
            System.out.println("ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA="+ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA);
        
        jC_counter.addItem("<none>");
        jC_counter.setSelectedIndex(0);
        
        if(ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA!=0)
        {
            getCounterKit();
        }
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            resetInterface();
        else
            setInterface();
        
        old_BODY_SOURCE=eb.BODY_SOURCE;
        
        ebINIZIALE=eb;
    }
    
    private void getCounterKit()
    {
        /*
         * per leggere i contatori:
         * select IDCOUNTERKIT from tab_analysis_type where IDANALISI=10;
         * select TAG,DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='voice' and IDCOUNTER=10;
         *
         */
        
        String strSelect="select IDCOUNTERKIT from tab_analysis_type where IDANALISI="+ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA;
        
        if(DEBUG)
            System.out.println("ADAMS_JP_ReportObject_BodyCell [getCounterKit] ----> "+strSelect);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        int idCounterKit=-1;
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    idCounterKit=rs.getInt("IDCOUNTERKIT");

                    if(DEBUG)
                    {
                        System.out.println("idCounterKit ->"+idCounterKit);
                        System.out.println();
                    } 
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_JP_ReportObject_BodyCell [getCounterKit] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
        
        if(DEBUG)
            System.out.println("idCounterKit="+idCounterKit);
        
        
        if(idCounterKit!=-1)
        {
            strSelect="select TAG,DESCRIPTION from tab_type_counters where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' and IDCOUNTER="+idCounterKit+" order by TAG";
            if(DEBUG)
                System.out.println("ADAMS_JP_ReportObject_BodyCell [getCounterKit2] ----> "+strSelect);

            SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
            ResultSet rs1  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);

            if(rs1 != null)
            {
                try
                {
                    while ( rs1.next ( ) ) 
                    {
                        String tag_counter=rs1.getString("TAG");
                        jC_counter.addItem(tag_counter);
                        if(DEBUG)
                        {
                            System.out.println("TAG ->"+tag_counter);
                            System.out.println();
                        } 
                    }
                }catch (Exception ex) 
                {
                    System.out.println(ex);
                }  
            }else
            {
                System.out.println("ADAMS_JP_ReportObject_BodyCell [getCounterKit] ----> rs1==null");
            }
            
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("ERROR-SQL Operation " + exc.toString());
            }
        }
        
    }
    
    public void commit()
    {
        // DA METTERE I CONTROLLI SUI CAMPI
        getInterface();
        
        
    }
    
    public boolean testCommit()
    {
        Vector vBody_appo=parent.robj.getVectorObject(ReportObject.BODY);
        for(int i=0;i<vBody_appo.size();i++)
        {
            ReportObject.elementoBase el_appo=(ReportObject.elementoBase)vBody_appo.elementAt(i);
            //System.out.println("el_appo.BODY_IDKEY="+el_appo.BODY_IDKEY);
        }
        
        if(eb.BODY_IDKEY==cont1)
        {
            //System.out.println("Modifico la stessa key");
            return true;
        }
        
        for(int i=0;i<vBody_appo.size();i++)
        {
            ReportObject.elementoBase el_appo=(ReportObject.elementoBase)vBody_appo.elementAt(i);
            if(el_appo.BODY_IDKEY==cont1)
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"Keypart already present.","ERROR",1);
                return false;
            }
        }
        return true;
    }
    
    public void getInterface()
    {       
        //eb.BODY_IDSCRIPT=-1;
        if(jR_r1.isSelected())
        {
            eb.BODY_SOURCE=0;
        }else if(jR_r2.isSelected())
        {
            eb.BODY_SOURCE=1;
        }else if(jR_r3.isSelected())
        {        
            if(wizard!=null)
            {
                if(wizard.flagCOMMIT==true)
                {
                    eb.BODY_IDSCRIPT=wizard.getID_SCRIPT();
                    eb.BODY_SOURCE=2;
                }

                if (wizard.flagDELETE==true)
                {
                    eb.BODY_SOURCE=0;
                    eb.BODY_IDSCRIPT=-1;
                }
            }else
            {
                eb.BODY_SOURCE=2;
            }
        }else if(jR_r4.isSelected())
        {
            eb.BODY_SOURCE=3;
        }else if(jR_r5.isSelected())
        {
            eb.BODY_SOURCE=4;
        }
        
        eb.BODY_IDKEY=cont1;
        
        if(jC_sr.isSelected()==true)
            eb.BODY_REPEATKEY=1;
        else
            eb.BODY_REPEATKEY=0;
        
        eb.BODY_PLUGIN_TAG=jT_tag.getText();
        eb.BODY_PLUGIN_NAME=jT_handler.getText();
        eb.BODY_USERVALUE=jT_uvs.getText();
        
        if(jR_r6.isSelected())
        {
            eb.BODY_ISURL=1;
        }else
        {
            eb.BODY_ISURL=0;
        }
        
        if(jR_r7.isSelected())
        {
            eb.BODY_TOTALIZER=1;
        }else
        {
            eb.BODY_TOTALIZER=0;
        }
        
        eb.BODY_TAGCOUNTER=(String)jC_counter.getSelectedItem();
        eb.BODY_MAXDECIMALDIGIT=cont2;
        
        
        
        if(DEBUG)
            System.out.println("BODY_IDSCRIPT="+eb.BODY_IDSCRIPT);
        
        if(wizard!=null)
            wizard.dispose();
    }
    
    private ReportObject.elementoBase getInterfaceAPPO()
    {
        ReportObject.elementoBase ebAPPO=new ReportObject.elementoBase();
        if(jR_r1.isSelected())
        {
            ebAPPO.BODY_SOURCE=0;
            ebAPPO.BODY_IDSCRIPT=-1;
        }else if(jR_r2.isSelected())
        {
            ebAPPO.BODY_SOURCE=1;
            ebAPPO.BODY_IDSCRIPT=-1;
        }else if(jR_r3.isSelected())
        {    
            ebAPPO.BODY_SOURCE=2;
            if(wizard!=null)
            {
                if(wizard.flagCOMMIT==true)
                {
                    ebAPPO.BODY_IDSCRIPT=wizard.getID_SCRIPT();
                    ebAPPO.BODY_SOURCE=2;
                }

                if (wizard.flagDELETE==true)
                {
                    ebAPPO.BODY_SOURCE=0;
                    ebAPPO.BODY_IDSCRIPT=-1;
                }
            }else
            {
                ebAPPO.BODY_SOURCE=2;
            }
        }else if(jR_r4.isSelected())
        {
            ebAPPO.BODY_SOURCE=3;
            ebAPPO.BODY_IDSCRIPT=-1;
        }else if(jR_r5.isSelected())
        {
            ebAPPO.BODY_SOURCE=4;
            ebAPPO.BODY_IDSCRIPT=-1;
        }
        
        ebAPPO.BODY_IDKEY=((new Integer(jT_k.getText())).intValue());
        
        if(jC_sr.isSelected()==true)
            ebAPPO.BODY_REPEATKEY=1;
        else
            ebAPPO.BODY_REPEATKEY=0;
        
        ebAPPO.BODY_PLUGIN_TAG=jT_tag.getText();
        ebAPPO.BODY_PLUGIN_NAME=jT_handler.getText();
        ebAPPO.BODY_USERVALUE=jT_uvs.getText();
        
        if(jR_r6.isSelected())
        {
            ebAPPO.BODY_ISURL=1;
        }else
        {
            ebAPPO.BODY_ISURL=0;
        }
        
        if(jR_r7.isSelected())
        {
            ebAPPO.BODY_TOTALIZER=1;
        }else
        {
            ebAPPO.BODY_TOTALIZER=0;
        }
        
        ebAPPO.BODY_TAGCOUNTER=(String)jC_counter.getSelectedItem();
        ebAPPO.BODY_MAXDECIMALDIGIT=((new Integer(jT_nd.getText())).intValue());
        
        
        
        if(DEBUG)
            System.out.println("BODY_IDSCRIPT="+ebAPPO.BODY_IDSCRIPT);
        
        if(wizard!=null)
            wizard.dispose();
        
        return ebAPPO;
    }
    
    public boolean testModify()
    {
        boolean testMODIFY=false;
        
        if(DEBUG)
        {
            System.out.println("Test MODIFY BODY");
        }
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(wizard!=null)
        {
            if(wizard.flagCOMMIT==true)
            {
                //System.out.println("Test MODIFY 01");
                testMODIFY=true;
                return testMODIFY;
            }

            if (wizard.flagDELETE==true)
            {
                //System.out.println("Test MODIFY 02");
                testMODIFY=true;
                return testMODIFY;
            }
        }
        
        
        
        
        ReportObject.elementoBase ebAPPO=getInterfaceAPPO();

        if(ebINIZIALE.BODY_SOURCE!=ebAPPO.BODY_SOURCE)
        {
            //System.out.println("ebINIZIALE.BODY_SOURCE="+ebINIZIALE.BODY_SOURCE);
            //System.out.println("ebAPPO.BODY_SOURCE="+ebAPPO.BODY_SOURCE);
            //System.out.println("Test MODIFY 1");
            testMODIFY=true;
            return testMODIFY;
        }
        /*if(ebINIZIALE.BODY_IDSCRIPT!=ebAPPO.BODY_IDSCRIPT)
        {
            System.out.println("Test MODIFY 2");
            System.out.println("ebINIZIALE.BODY_IDSCRIPT="+ebINIZIALE.BODY_IDSCRIPT);
            System.out.println("ebAPPO.BODY_IDSCRIPT="+ebAPPO.BODY_IDSCRIPT);
            testMODIFY=true;
            return testMODIFY;
        }*/
        if(ebINIZIALE.BODY_IDKEY!=ebAPPO.BODY_IDKEY)
        {
            //System.out.println("ebINIZIALE.BODY_IDKEY="+ebINIZIALE.BODY_IDKEY);
            //System.out.println("ebAPPO.BODY_IDKEY="+ebAPPO.BODY_IDKEY);
            //System.out.println("Test MODIFY 3");
            testMODIFY=true;
            return testMODIFY;
        }
        if(ebINIZIALE.BODY_REPEATKEY!=ebAPPO.BODY_REPEATKEY)
        {
            //System.out.println("Test MODIFY 4");
            testMODIFY=true;
            return testMODIFY;
        }
        
        
        if(!ebINIZIALE.BODY_PLUGIN_TAG.equals(ebAPPO.BODY_PLUGIN_TAG))
        {
            //System.out.println("Test MODIFY 5");
            testMODIFY=true;
            return testMODIFY;
        }
        if(!ebINIZIALE.BODY_PLUGIN_NAME.equals(ebAPPO.BODY_PLUGIN_NAME))
        {
            //System.out.println("Test MODIFY 5.1");
            testMODIFY=true;
            return testMODIFY;
        }
        if(!ebINIZIALE.BODY_USERVALUE.equals(ebAPPO.BODY_USERVALUE))
        {
            //System.out.println("Test MODIFY 6");
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.BODY_ISURL!=ebAPPO.BODY_ISURL)
        {
            //System.out.println("Test MODIFY 7");
            testMODIFY=true;
            return testMODIFY;
        }
        if(ebINIZIALE.BODY_TOTALIZER!=ebAPPO.BODY_TOTALIZER)
        {
            //System.out.println("Test MODIFY 8");
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(!ebINIZIALE.BODY_TAGCOUNTER.equals(ebAPPO.BODY_TAGCOUNTER))
        {
            //System.out.println("Test MODIFY 9");
            testMODIFY=true;
            return testMODIFY;
        }
        
        if(ebINIZIALE.BODY_MAXDECIMALDIGIT!=ebAPPO.BODY_MAXDECIMALDIGIT)
        {
            //System.out.println("Test MODIFY 10");
            testMODIFY=true;
            return testMODIFY;
        }
        
        return testMODIFY;
        
    }
    
    private void resetInterface()
    {
        cont2=0;
        jT_nd.setText(""+cont2);
        cont1=0;
        jT_k.setText(""+cont1);
        jB_up1.setEnabled(true);
        jB_down1.setEnabled(true);
        jC_sr.setEnabled(true);
        jT_k.setEnabled(true);
        jLabel2.setEnabled(true);
        jT_nd.setEnabled(false);
        jLabel9.setEnabled(false);
        jB_up.setEnabled(false);
        jB_down.setEnabled(false);
        jB_edit.setEnabled(false);
        parent.setTextDescription("Key part: 0");
        
    }
    
    private void setInterface()
    {
        //System.out.println("BODY_SOURCE="+eb.BODY_SOURCE);
        //System.out.println("BODY_IDSCRIPT="+eb.BODY_IDSCRIPT);
        switch(eb.BODY_SOURCE)
        {
            case 0:
                {
                    jR_r1.setSelected(true);
                    jLabel2.setEnabled(true);
                    jLabel3.setEnabled(false);
                    jLabel4.setEnabled(false);
                    jLabel5.setEnabled(false);
                    jLabel6.setEnabled(false);
                    jLabel7.setEnabled(false);
                    jB_up1.setEnabled(true);
                    jB_down1.setEnabled(true);
                    jC_counter.setEnabled(false);
                    jB_script.setEnabled(false);
                    jT_tag.setEnabled(false);
                    jT_handler.setEnabled(false);
                    jT_uvs.setEnabled(false);
                    jR_r6.setEnabled(false);
                    jT_k.setEnabled(true);
                    jT_nd.setEnabled(false);
                    jLabel9.setEnabled(false);
                    jB_up.setEnabled(false);
                    jB_down.setEnabled(false);
                    jC_sr.setEnabled(true);
                }break;
            case 1:
                {
                    jR_r2.setSelected(true);
                    jLabel2.setEnabled(false);
                    jLabel3.setEnabled(true);
                    jLabel4.setEnabled(false);
                    jLabel5.setEnabled(false);
                    jLabel6.setEnabled(false);
                    jLabel7.setEnabled(false);
                    jB_up1.setEnabled(false);
                    jB_down1.setEnabled(false);
                    jC_counter.setEnabled(true);
                    jB_script.setEnabled(false);
                    jT_tag.setEnabled(false);
                    jT_handler.setEnabled(false);
                    jT_uvs.setEnabled(false);
                    jR_r6.setEnabled(false);
                    jT_k.setEnabled(false);
                    jT_nd.setEnabled(true);
                    jLabel9.setEnabled(true);
                    jB_up.setEnabled(true);
                    jB_down.setEnabled(true);
                    jC_sr.setEnabled(false);
                }break;
            case 2:
                {
                    jR_r3.setSelected(true);
                    jLabel2.setEnabled(false);
                    jLabel3.setEnabled(false);
                    jLabel4.setEnabled(true);
                    jLabel5.setEnabled(false);
                    jLabel6.setEnabled(false);
                    jLabel7.setEnabled(false);
                    jB_up1.setEnabled(false);
                    jB_down1.setEnabled(false);
                    jC_counter.setEnabled(false);
                    jB_script.setEnabled(true);
                    jT_tag.setEnabled(false);
                    jT_handler.setEnabled(false);
                    jT_uvs.setEnabled(false);
                    jR_r6.setEnabled(false);
                    jT_k.setEnabled(false);
                    jT_nd.setEnabled(true);
                    jLabel9.setEnabled(true);
                    jB_up.setEnabled(true);
                    jB_down.setEnabled(true);
                    jC_sr.setEnabled(false);
                }break;
            case 3:
                {
                    jR_r4.setSelected(true);
                    jLabel2.setEnabled(false);
                    jLabel3.setEnabled(false);
                    jLabel4.setEnabled(false);
                    jLabel5.setEnabled(true);
                    jLabel6.setEnabled(true);
                    jLabel7.setEnabled(false);
                    jB_up1.setEnabled(false);
                    jB_down1.setEnabled(false);
                    jC_counter.setEnabled(false);
                    jB_script.setEnabled(false);
                    jT_tag.setEnabled(true);
                    jT_handler.setEnabled(true);
                    jT_uvs.setEnabled(false);
                    jR_r6.setEnabled(false);
                    jT_k.setEnabled(false);
                    jT_nd.setEnabled(true);
                    jLabel9.setEnabled(true);
                    jB_up.setEnabled(true);
                    jB_down.setEnabled(true);
                    jC_sr.setEnabled(false);
                }break;
            case 4:
                {
                    jR_r5.setSelected(true);
                    jLabel2.setEnabled(false);
                    jLabel3.setEnabled(false);
                    jLabel4.setEnabled(false);
                    jLabel5.setEnabled(false);
                    jLabel6.setEnabled(false);
                    jLabel7.setEnabled(true);
                    jB_up1.setEnabled(false);
                    jB_down1.setEnabled(false);
                    jC_counter.setEnabled(false);
                    jB_script.setEnabled(false);
                    jT_tag.setEnabled(false);
                    jT_handler.setEnabled(false);
                    jT_uvs.setEnabled(true);
                    jR_r6.setEnabled(true);
                    jT_k.setEnabled(false);
                    jT_nd.setEnabled(false);
                    jLabel9.setEnabled(false);
                    jB_up.setEnabled(false);
                    jB_down.setEnabled(false);
                    jC_sr.setEnabled(false);
                }break;
        }
        
        jT_k.setText(""+eb.BODY_IDKEY);
        
        if(eb.BODY_REPEATKEY==1)
            jC_sr.setSelected(true);
        else
            jC_sr.setSelected(false);
        
        cont1=eb.BODY_IDKEY;
        jT_tag.setText(""+eb.BODY_PLUGIN_TAG);
        jT_handler.setText(""+eb.BODY_PLUGIN_NAME);
        jT_uvs.setText(""+eb.BODY_USERVALUE);
        if(eb.BODY_ISURL==1)
            jR_r6.setSelected(true);
        else
            jR_r6.setSelected(false);
        if(eb.BODY_TOTALIZER==1)
            jR_r7.setSelected(true);
        else
            jR_r7.setSelected(false);
        
        jC_counter.setSelectedItem((String)eb.BODY_TAGCOUNTER);
        
        cont2=eb.BODY_MAXDECIMALDIGIT;
        jT_nd.setText(""+cont2);      
    }
    
    public void setFont()
    {
        jR_r1.setFont(ADAMS_GlobalParam.font_B11);
        jR_r2.setFont(ADAMS_GlobalParam.font_B11);
        jR_r3.setFont(ADAMS_GlobalParam.font_B11);
        jR_r4.setFont(ADAMS_GlobalParam.font_B11);
        jR_r5.setFont(ADAMS_GlobalParam.font_B11);
        jR_r6.setFont(ADAMS_GlobalParam.font_B11);
        jR_r7.setFont(ADAMS_GlobalParam.font_B11);
        
        jLabel2.setFont(ADAMS_GlobalParam.font_B11);
        jLabel3.setFont(ADAMS_GlobalParam.font_B11);
        jLabel4.setFont(ADAMS_GlobalParam.font_B11);
        jLabel5.setFont(ADAMS_GlobalParam.font_B11);
        jLabel6.setFont(ADAMS_GlobalParam.font_B11);
        jLabel7.setFont(ADAMS_GlobalParam.font_B11);
        jC_counter.setFont(ADAMS_GlobalParam.font_B11);
        jB_script.setFont(ADAMS_GlobalParam.font_B11);
        jT_tag.setFont(ADAMS_GlobalParam.font_B11);
        jT_handler.setFont(ADAMS_GlobalParam.font_B11);
        jT_uvs.setFont(ADAMS_GlobalParam.font_B11);
        jT_k.setFont(ADAMS_GlobalParam.font_B11);
        jT_nd.setFont(ADAMS_GlobalParam.font_B11);
        jLabel9.setFont(ADAMS_GlobalParam.font_B11);
        
    }
    
    public void setCursorWidget()
    {    
        jR_r1.setCursor(Cur_hand);
        jR_r2.setCursor(Cur_hand);
        jR_r3.setCursor(Cur_hand);
        jR_r4.setCursor(Cur_hand);
        jR_r5.setCursor(Cur_hand);
        jR_r6.setCursor(Cur_hand);
        jR_r7.setCursor(Cur_hand);
        jB_up1.setCursor(Cur_hand);
        jB_down1.setCursor(Cur_hand);
        jC_counter.setCursor(Cur_hand);
        jB_script.setCursor(Cur_hand);
        jT_tag.setCursor(Cur_hand);
        jT_handler.setCursor(Cur_hand);
        jT_uvs.setCursor(Cur_hand);
        jT_k.setCursor(Cur_hand);
        jT_nd.setCursor(Cur_hand);
        jB_up.setCursor(Cur_hand);
        jB_down.setCursor(Cur_hand);
        jB_edit.setCursor(Cur_hand);
        jC_sr.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        buttonGroup1 = new javax.swing.ButtonGroup();
        jL_icon = new javax.swing.JLabel();
        jP_p1 = new javax.swing.JPanel();
        jR_r1 = new javax.swing.JRadioButton();
        jR_r2 = new javax.swing.JRadioButton();
        jR_r3 = new javax.swing.JRadioButton();
        jR_r4 = new javax.swing.JRadioButton();
        jR_r5 = new javax.swing.JRadioButton();
        jP_p2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jC_counter = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jB_script = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jT_tag = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jT_uvs = new javax.swing.JTextField();
        jR_r6 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jT_k = new javax.swing.JTextField();
        jB_up1 = new javax.swing.JButton();
        jB_down1 = new javax.swing.JButton();
        jC_sr = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jT_nd = new javax.swing.JTextField();
        jB_up = new javax.swing.JButton();
        jB_down = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jR_r7 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jT_handler = new javax.swing.JTextField();
        jB_edit = new javax.swing.JButton();
        
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        jL_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/body_cell.png")));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints1.weighty = 1.0;
        add(jL_icon, gridBagConstraints1);
        
        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Data Source"));
        jR_r1.setSelected(true);
        jR_r1.setText("Key Part");
        jR_r1.setToolTipText("Direct");
        buttonGroup1.add(jR_r1);
        jR_r1.setContentAreaFilled(false);
        jR_r1.setFocusPainted(false);
        jR_r1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r1, gridBagConstraints2);
        
        jR_r2.setText("Counter Kit");
        jR_r2.setToolTipText("Direct");
        buttonGroup1.add(jR_r2);
        jR_r2.setContentAreaFilled(false);
        jR_r2.setFocusPainted(false);
        jR_r2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ActionPerformed(evt);
            }
        });
        
        jR_r2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jR_r1StateChanged(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r2, gridBagConstraints2);
        
        jR_r3.setText("Script");
        jR_r3.setToolTipText("Direct");
        buttonGroup1.add(jR_r3);
        jR_r3.setContentAreaFilled(false);
        jR_r3.setFocusPainted(false);
        jR_r3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_r3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ActionPerformed(evt);
            }
        });
        
        jR_r3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jR_r1StateChanged(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r3, gridBagConstraints2);
        
        jR_r4.setText("Plugin");
        jR_r4.setToolTipText("Direct");
        buttonGroup1.add(jR_r4);
        jR_r4.setContentAreaFilled(false);
        jR_r4.setFocusPainted(false);
        jR_r4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_r4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ActionPerformed(evt);
            }
        });
        
        jR_r4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jR_r1StateChanged(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r4, gridBagConstraints2);
        
        jR_r5.setText("User Defined");
        jR_r5.setToolTipText("Direct");
        buttonGroup1.add(jR_r5);
        jR_r5.setContentAreaFilled(false);
        jR_r5.setFocusPainted(false);
        jR_r5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_r5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_r5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_r5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_ActionPerformed(evt);
            }
        });
        
        jR_r5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jR_r1StateChanged(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        jP_p1.add(jR_r5, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.VERTICAL;
        add(jP_p1, gridBagConstraints1);
        
        jP_p2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_p2.setBackground(new java.awt.Color(230, 230, 230));
        jP_p2.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Data Selection"));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Key Part");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jLabel2, gridBagConstraints3);
        
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Counter Kit");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jLabel3, gridBagConstraints3);
        
        jC_counter.setBackground(java.awt.Color.white);
        jC_counter.setEnabled(false);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jC_counter, gridBagConstraints3);
        
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Script");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jLabel4, gridBagConstraints3);
        
        jB_script.setBackground(new java.awt.Color(230, 230, 230));
        jB_script.setText("Edit");
        jB_script.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_script.setFocusPainted(false);
        jB_script.setEnabled(false);
        jB_script.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_scriptActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jB_script, gridBagConstraints3);
        
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Tag (optional)");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jLabel5, gridBagConstraints3);
        
        jT_tag.setEnabled(false);
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jT_tag, gridBagConstraints3);
        
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Handler Plugin Filename");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jLabel6, gridBagConstraints3);
        
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("User value string");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jLabel7, gridBagConstraints3);
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jT_uvs.setEnabled(false);
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.weightx = 1.0;
        jPanel3.add(jT_uvs, gridBagConstraints4);
        
        jR_r6.setBackground(new java.awt.Color(230, 230, 230));
        jR_r6.setText("URL");
        jR_r6.setToolTipText("(True/False)");
        jR_r6.setFocusPainted(false);
        jR_r6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_r6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r6.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.weighty = 1.0;
        jPanel3.add(jR_r6, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jPanel3, gridBagConstraints3);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jT_k.setMaximumSize(new java.awt.Dimension(26, 19));
        jT_k.setMinimumSize(new java.awt.Dimension(26, 19));
        jT_k.setPreferredSize(new java.awt.Dimension(26, 19));
        jT_k.setEnabled(false);
        jT_k.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_kKeyReleased(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridheight = 2;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jT_k, gridBagConstraints5);
        
        jB_up1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/su.gif")));
        jB_up1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_up1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_up1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints5.weighty = 1.0;
        jPanel1.add(jB_up1, gridBagConstraints5);
        
        jB_down1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/giu.gif")));
        jB_down1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_down1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_up1ActionPerformed(evt);
            }
        });
        
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints5.weighty = 1.0;
        jPanel1.add(jB_down1, gridBagConstraints5);
        
        jC_sr.setBackground(new java.awt.Color(230, 230, 230));
        jC_sr.setText("Do not hide column");
        jC_sr.setToolTipText("(True/False)");
        jC_sr.setFocusPainted(false);
        jC_sr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jC_sr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jC_sr.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jC_sr.setMinimumSize(new java.awt.Dimension(40, 22));
        jC_sr.setPreferredSize(new java.awt.Dimension(120, 21));
        jC_sr.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jC_sr.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jC_sr.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jC_sr.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 2;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.gridheight = 2;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        jPanel1.add(jC_sr, gridBagConstraints5);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jPanel1, gridBagConstraints3);
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel4.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints7;
        
        jPanel4.setBackground(new java.awt.Color(230, 230, 230));
        jT_nd.setMaximumSize(new java.awt.Dimension(26, 19));
        jT_nd.setMinimumSize(new java.awt.Dimension(26, 19));
        jT_nd.setPreferredSize(new java.awt.Dimension(26, 19));
        jT_nd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_ndKeyReleased(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridheight = 2;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints7.weighty = 1.0;
        jPanel4.add(jT_nd, gridBagConstraints7);
        
        jB_up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/su.gif")));
        jB_up.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_upActionPerformed(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints7.weighty = 1.0;
        jPanel4.add(jB_up, gridBagConstraints7);
        
        jB_down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/giu.gif")));
        jB_down.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_upActionPerformed(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 1;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints7.weighty = 1.0;
        jPanel4.add(jB_down, gridBagConstraints7);
        
        jLabel9.setBackground(new java.awt.Color(230, 230, 230));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("  Max Decimal Digits");
        jLabel9.setOpaque(true);
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridheight = 2;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints7.weightx = 1.0;
        gridBagConstraints7.weighty = 1.0;
        jPanel4.add(jLabel9, gridBagConstraints7);
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.weightx = 1.0;
        jPanel2.add(jPanel4, gridBagConstraints6);
        
        jR_r7.setBackground(new java.awt.Color(230, 230, 230));
        jR_r7.setText("No Totalizers for this column");
        jR_r7.setToolTipText("(True/False)");
        jR_r7.setFocusPainted(false);
        jR_r7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_r7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_r7.setPreferredSize(new java.awt.Dimension(40, 22));
        jR_r7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_r7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_r7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.weighty = 1.0;
        jPanel2.add(jR_r7, gridBagConstraints6);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 7;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jPanel2, gridBagConstraints3);
        
        jPanel5.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints8;
        
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jT_handler.setEditable(false);
        jT_handler.setEnabled(false);
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 0;
        gridBagConstraints8.gridy = 0;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints8.weightx = 2.0;
        gridBagConstraints8.weighty = 1.0;
        jPanel5.add(jT_handler, gridBagConstraints8);
        
        jB_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit.jpg")));
        jB_edit.setToolTipText("Edit Plugin (Active in modification)");
        jB_edit.setBorderPainted(false);
        jB_edit.setContentAreaFilled(false);
        jB_edit.setFocusPainted(false);
        jB_edit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_edit.setMaximumSize(new java.awt.Dimension(35, 22));
        jB_edit.setMinimumSize(new java.awt.Dimension(35, 22));
        jB_edit.setPreferredSize(new java.awt.Dimension(35, 22));
        jB_edit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_press.jpg")));
        jB_edit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_edit_over.jpg")));
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_editActionPerformed(evt);
            }
        });
        
        gridBagConstraints8 = new java.awt.GridBagConstraints();
        gridBagConstraints8.gridx = 1;
        gridBagConstraints8.gridy = 0;
        gridBagConstraints8.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints8.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints8.weighty = 1.0;
        jPanel5.add(jB_edit, gridBagConstraints8);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p2.add(jPanel5, gridBagConstraints3);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        add(jP_p2, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jB_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_editActionPerformed
        // Add your handling code here:
        ADAMS_JD_setPlugin  JD_PluginSelect = new ADAMS_JD_setPlugin(null,true,700,400,config_NAME_ALIAS,jT_handler.getText());     
        JD_PluginSelect.setGuiEnable(true);
        //JD_PluginSelect.show();
        JD_PluginSelect.setVisible(true);
        
        
        jT_handler.setText(JD_PluginSelect.getPluginEnabled());
    }//GEN-LAST:event_jB_editActionPerformed

    private void jB_scriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_scriptActionPerformed
        // Add your handling code here:
        
        
        String strSelect="select IDCOUNTERKIT from tab_analysis_type where IDANALISI="+ADAMS_GlobalParam.ID_ANALISI_SELEZIONATA;
        
        if(DEBUG)
            System.out.println("ADAMS_JP_ReportObject_BodyCell [getIDAnalisi] ----> "+strSelect);

        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        int idCounterKit=-2;
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    idCounterKit=rs.getInt("IDCOUNTERKIT");

                    if(DEBUG)
                    {
                        System.out.println("idCounterKit ->"+idCounterKit);
                        System.out.println();
                    } 
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_JP_ReportObject_BodyCell [getCounterKit] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ERROR-SQL Operation " + exc.toString());
        }
        
        
        if(DEBUG)
            System.out.println("idCounterKit="+idCounterKit);
        
        ADAMS_TAB_CONFIG appo=new ADAMS_TAB_CONFIG();
        appo.TAG=parent.getTextDescription();
        appo.DESCRIPTION="Report Schema";
        
        //controllare se esiste :
        String strSelectSeq="select IDSCRIPT from tab_scripts where TIPO_DI_CONFIGURAZIONE='"+config_NAME_ALIAS+"' AND IDSCRIPT="+eb.BODY_IDSCRIPT;
        int id = ADAMS_GlobalParam.connect_Oracle.Query_int(strSelectSeq);
        //System.out.println("strSelectSeq " + strSelectSeq);
        System.out.println("IDSCRIPT " + id);
            
        if((eb.BODY_IDSCRIPT>0)&&(id>0))
        {
            appo.add_LISTA_SCRIPTS(eb.BODY_IDSCRIPT,4);
        }
        
        wizard = new ADAMS_JF_WIZARDBASE(ADAMS_GlobalParam.ADAMS_JD_Report,1,ADAMS_GlobalParam.SCRIPT_REPORT,config_NAME_ALIAS,idCounterKit,"NTM Basic Wizard for "+appo.TAG, true, appo, null);
        //wizard.show();
        wizard.setVisible(true);

        /*
        System.out.println("old_BODY_SOURCE="+old_BODY_SOURCE);
        System.out.println("flagCOMMIT="+wizard.flagCOMMIT);
        System.out.println("flagDELETE="+wizard.flagDELETE);
        */
        
        if(wizard.flagCOMMIT==false)
        {
            eb.BODY_SOURCE=old_BODY_SOURCE;
            setInterface();
        }

        if (wizard.flagDELETE==true)
        {
            eb.BODY_SOURCE=0;
            eb.BODY_IDSCRIPT=-1;
            setInterface();
        }
        
    }//GEN-LAST:event_jB_scriptActionPerformed

    private void jB_up1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_up1ActionPerformed
        // Add your handling code here:
        if (evt.getSource() == jB_up1)
        {
            if(cont1<99)
                cont1=cont1+1;
            
            
        }else if (evt.getSource() == jB_down1)
        {
            if(cont1>0)
                cont1=cont1-1;
        }
        jT_k.setText(""+cont1);
        jT_k.repaint();
        parent.setTextDescription("Key part: "+cont1);
    }//GEN-LAST:event_jB_up1ActionPerformed

    private void jT_ndKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_ndKeyReleased
        // Add your handling code here:
        try
        {
            cont2=((new Integer(jT_nd.getText())).intValue());
        }catch(Exception e){}; 
    }//GEN-LAST:event_jT_ndKeyReleased

    private void jB_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_upActionPerformed
        // Add your handling code here:
        if (evt.getSource() == jB_up)
        {
            if(cont2<7)
                cont2=cont2+1;
            
            
        }else if (evt.getSource() == jB_down)
        {
            if(cont2>0)
                cont2=cont2-1;
        }
        jT_nd.setText(""+cont2);
        jT_nd.repaint();
    }//GEN-LAST:event_jB_upActionPerformed

    private void jR_r1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jR_r1StateChanged
        // Add your handling code here:
        
    }//GEN-LAST:event_jR_r1StateChanged

    private void jT_kKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_kKeyReleased
        // Add your handling code here:
        try
        {
            cont1=((new Integer(jT_k.getText())).intValue());
        }catch(Exception e){}; 
    }//GEN-LAST:event_jT_kKeyReleased

    private void jR_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_ActionPerformed
        // Add your handling code here:
        if (evt.getSource() == jR_r1) //Key Part
        {
            parent.setTextDescription("Key part: "+cont1);
            jLabel2.setEnabled(true);
            jLabel3.setEnabled(false);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
            jB_up1.setEnabled(true);
            jB_down1.setEnabled(true);
            jC_counter.setEnabled(false);
            jB_script.setEnabled(false);
            jT_tag.setEnabled(false);
            jT_handler.setEnabled(false);
            jB_edit.setEnabled(false);
            jT_uvs.setEnabled(false);
            jR_r6.setEnabled(false);
            jT_k.setEnabled(true);
            jT_nd.setEnabled(false);
            jLabel9.setEnabled(false);
            jB_up.setEnabled(false);
            jB_down.setEnabled(false);
            jC_sr.setEnabled(true);
        }else if (evt.getSource() == jR_r2) //Counter/Kit
        {
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(true);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
            jB_up1.setEnabled(false);
            jB_down1.setEnabled(false);
            jC_counter.setEnabled(true);
            jB_script.setEnabled(false);
            jT_tag.setEnabled(false);
            jT_handler.setEnabled(false);
            jB_edit.setEnabled(false);
            jT_uvs.setEnabled(false);
            jR_r6.setEnabled(false);
            jT_k.setEnabled(false);
            jT_nd.setEnabled(true);
            jLabel9.setEnabled(true);
            jB_up.setEnabled(true);
            jB_down.setEnabled(true);
            jC_sr.setEnabled(false);
        }else if (evt.getSource() == jR_r3) //Script
        {
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel4.setEnabled(true);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
            jB_up1.setEnabled(false);
            jB_down1.setEnabled(false);
            jC_counter.setEnabled(false);
            jB_script.setEnabled(true);
            jT_tag.setEnabled(false);
            jT_handler.setEnabled(false);
            jB_edit.setEnabled(false);
            jT_uvs.setEnabled(false);
            jR_r6.setEnabled(false);
            jT_k.setEnabled(false);
            jT_nd.setEnabled(true);
            jLabel9.setEnabled(true);
            jB_up.setEnabled(true);
            jB_down.setEnabled(true);
            jC_sr.setEnabled(false);
        }else if (evt.getSource() == jR_r4) //Plugin
        {
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(true);
            jLabel6.setEnabled(true);
            jLabel7.setEnabled(false);
            jB_up1.setEnabled(false);
            jB_down1.setEnabled(false);
            jC_counter.setEnabled(false);
            jB_script.setEnabled(false);
            jT_tag.setEnabled(true);
            jT_handler.setEnabled(true);
            jB_edit.setEnabled(true);
            jT_uvs.setEnabled(false);
            jR_r6.setEnabled(false);
            jT_k.setEnabled(false);
            jT_nd.setEnabled(true);
            jLabel9.setEnabled(true);
            jB_up.setEnabled(true);
            jB_down.setEnabled(true);
            jC_sr.setEnabled(false);
        }
        else if (evt.getSource() == jR_r5) //Plugin
        {
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(true);
            jB_up1.setEnabled(false);
            jB_down1.setEnabled(false);
            jC_counter.setEnabled(false);
            jB_script.setEnabled(false);
            jT_tag.setEnabled(false);
            jT_handler.setEnabled(false);
            jB_edit.setEnabled(false);
            jT_uvs.setEnabled(true);
            jR_r6.setEnabled(true);
            jT_k.setEnabled(false);
            jT_nd.setEnabled(false);
            jLabel9.setEnabled(false);
            jB_up.setEnabled(false);
            jB_down.setEnabled(false);
            jC_sr.setEnabled(false);
        }
        
    }//GEN-LAST:event_jR_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jL_icon;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JRadioButton jR_r1;
    private javax.swing.JRadioButton jR_r2;
    private javax.swing.JRadioButton jR_r3;
    private javax.swing.JRadioButton jR_r4;
    private javax.swing.JRadioButton jR_r5;
    private javax.swing.JPanel jP_p2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox jC_counter;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jB_script;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jT_tag;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jT_uvs;
    private javax.swing.JCheckBox jR_r6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jT_k;
    private javax.swing.JButton jB_up1;
    private javax.swing.JButton jB_down1;
    private javax.swing.JCheckBox jC_sr;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jT_nd;
    private javax.swing.JButton jB_up;
    private javax.swing.JButton jB_down;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jR_r7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jT_handler;
    private javax.swing.JButton jB_edit;
    // End of variables declaration//GEN-END:variables

}
