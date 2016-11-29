/*
 * ADAMS_JP_ReportObjectEditor.java
 *
 * Created on 4 agosto 2005, 17.02
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */


import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JColorChooser;

public class ADAMS_JP_ReportObjectEditor extends javax.swing.JPanel {

    private boolean                                 DEBUG                       = false;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private int                                     type                        = -1;
    private ReportObject.elementoBase               eb                          = null;
    private javax.swing.JTextArea                   jT_help                     = null;
    private String                                  config_NAME_SCHEMA          = null;
    private javax.swing.JPanel                      appoPanel                   = null;
    public ReportObject                             robj                        = null;
    //public boolean                                  commitFatta                 = true;
    
    /** Creates new form ADAMS_JP_ReportObjectEditor */
    
    public ADAMS_JP_ReportObjectEditor(String config_NAME_ALIAS,String config_NAME_SCHEMA,int type,ReportObject.elementoBase eb,ReportObject robj) {
        this.type=type;
        this.eb=eb;
        this.robj=robj;
        this.config_NAME_SCHEMA=config_NAME_SCHEMA;
        initComponents();
        setFont();
        setCursorWidget();
        
        jT_v.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,2));
        jT_v.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,2));
        jT_size.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,1));
        jT_desc.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC+JTextFieldFilter.ALPHA+JTextFieldFilter.SOMESYMBOLS,30));
        //System.out.println("TYPE="+type);
        switch(type)
        {
             case ReportObject.HEADER:
                {
                    appoPanel=new ADAMS_JP_ReportObject_HeaderFieldContent(eb,this);
                        
                }break;
                
             case ReportObject.BODY:
                {
                    appoPanel=new ADAMS_JP_ReportObject_BodyCell(config_NAME_ALIAS,eb,this);
                }break;
                
             case ReportObject.TOTALIZER:
                {
                    appoPanel=new ADAMS_JP_ReportObject_TotalizerRow(config_NAME_ALIAS,config_NAME_SCHEMA,eb,robj,this);
                }break;
                
             case ReportObject.FOOTER:
                {
                    appoPanel=new ADAMS_JP_ReportObject_FooterRow(config_NAME_ALIAS,config_NAME_SCHEMA,eb,robj,this);
                }break;
        }
        
        jP_p3.add(appoPanel, java.awt.BorderLayout.CENTER);
        jC_v.addItem("Center");
        jC_v.addItem("Upper");
        jC_v.addItem("Lover");
        jC_v.setSelectedIndex(0);
        
        jC_h.addItem("Center");
        jC_h.addItem("Left");
        jC_h.addItem("Right");
        jC_h.addItem("Justified");
        jC_h.setSelectedIndex(1);
        
        jC_style.addItem("Body Text");
        jC_style.addItem("Paragraph");
        jC_style.addItem("Title 1");
        jC_style.addItem("Title 2");
        jC_style.addItem("Title 3");
        jC_style.addItem("Title 4");
        jC_style.addItem("Title 5");
        jC_style.addItem("Title 6");
        jC_style.addItem("Address");
        jC_style.addItem("Preformatted");
        jC_style.setSelectedIndex(0);
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
            resetInterface();
        else
            setInterface();
        
    }
    
    public boolean testModify() // non utilizzato
    {
        //System.out.println("ADAMS_JP_ReportObjectEditor --> Eseguo il test modify("+type+")");
        
        boolean testMODIFY=false;
        
        ReportObject.elementoBase ebAPPO=getInterfaceAPPO();
        //System.out.println("ADAMS_GlobalParam.INSERT_NEW_ELEMENT="+ADAMS_GlobalParam.INSERT_NEW_ELEMENT);
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            return true;
        }
        if(!eb.OBJECT_TAG.equals(ebAPPO.OBJECT_TAG))
        {
            //System.out.println("ebINIZIALE.OBJECT_TAG="+eb.OBJECT_TAG);
            //System.out.println("ebAPPO.OBJECT_TAG="+ebAPPO.OBJECT_TAG);
            //System.out.println("1");
            testMODIFY=true;
            return testMODIFY;
        }
        if(eb.HTML_VSIZE!=ebAPPO.HTML_VSIZE)
        {
            //System.out.println("2");
            testMODIFY=true;
            return testMODIFY;
        }
        if(eb.HTML_HSIZE!=ebAPPO.HTML_HSIZE)
        {
            //System.out.println("3");
            testMODIFY=true;
            return testMODIFY;
        }   
        if(eb.HTML_VALIGN!=ebAPPO.HTML_VALIGN)
        {
            //System.out.println("4");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_HALIGN!=ebAPPO.HTML_HALIGN)
        {
            //System.out.println("5");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_WRAP!=ebAPPO.HTML_WRAP)
        {
            //System.out.println("6");
            testMODIFY=true;
            return testMODIFY;
        } 
        
        if(eb.HTML_BACKGROUND!=ebAPPO.HTML_BACKGROUND)
        {
            //System.out.println("7");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_BACKGROUND_R!=ebAPPO.HTML_BACKGROUND_R)
        {
            //System.out.println("8");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_BACKGROUND_G!=ebAPPO.HTML_BACKGROUND_G)
        {
            //System.out.println("9");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_BACKGROUND_B!=ebAPPO.HTML_BACKGROUND_B)
        {
            //System.out.println("10");
            testMODIFY=true;
            return testMODIFY;
        } 
        
        if(eb.HTML_FOREGROUND!=ebAPPO.HTML_FOREGROUND)
        {
            //System.out.println("11");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_FOREGROUND_R!=ebAPPO.HTML_FOREGROUND_R)
        {
            //System.out.println("12");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_FOREGROUND_G!=ebAPPO.HTML_FOREGROUND_G)
        {
            //System.out.println("13");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_FOREGROUND_B!=ebAPPO.HTML_FOREGROUND_B)
        {
            //System.out.println("14");
            testMODIFY=true;
            return testMODIFY;
        } 
        
        if(eb.HTML_STYLE!=ebAPPO.HTML_STYLE)
        {
            //System.out.println("15");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_FONTSIZE!=ebAPPO.HTML_FONTSIZE)
        {
            //System.out.println("16");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_BOLD!=ebAPPO.HTML_BOLD)
        {
            //System.out.println("17");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_ITALIC!=ebAPPO.HTML_ITALIC)
        {
            //System.out.println("18");
            testMODIFY=true;
            return testMODIFY;
        } 
        if(eb.HTML_UNDERLINE!=ebAPPO.HTML_UNDERLINE)
        {
            //System.out.println("19");
            testMODIFY=true;
            return testMODIFY;
        } 
        
        switch(type)
        {
             case ReportObject.HEADER:
                {
                    testMODIFY=((ADAMS_JP_ReportObject_HeaderFieldContent)appoPanel).testModify();
                    //System.out.println("HEADER="+testMODIFY);
                }break;
                
             case ReportObject.BODY:
                {
                    testMODIFY=((ADAMS_JP_ReportObject_BodyCell)appoPanel).testModify();
                    //System.out.println("BODY="+testMODIFY);
                }break;
                
             case ReportObject.TOTALIZER:
                {
                    testMODIFY=((ADAMS_JP_ReportObject_TotalizerRow)appoPanel).testModify();
                    //System.out.println("TOTALIZER="+testMODIFY);
                }break;
                
             case ReportObject.FOOTER:
                {
                    testMODIFY=((ADAMS_JP_ReportObject_FooterRow)appoPanel).testModify();
                    //System.out.println("FOOTER="+testMODIFY);
                }break;
        }
        
        return testMODIFY;
    }
    
    public void setTextDescription(String str)
    {
        jT_desc.setText(str);
    }
    
    public String getTextDescription()
    {
        return jT_desc.getText();
    }
    
    public boolean commit()
    {
        // DA METTERE I CONTROLLI SUI CAMPI
        ADAMS_JD_Message op = new ADAMS_JD_Message(ADAMS_GlobalParam.ADAMS_JD_Report,true,"Saving changes on schema ["+config_NAME_SCHEMA+"].  Confirm ?","Info",6);
        int Yes_No = op.getAnswer();

        
        if(Yes_No == 1)// faccio la commit.
        {   
            if(jT_desc.getText().equals(""))
            {
                ADAMS_GlobalParam.optionPanel(ADAMS_GlobalParam.ADAMS_JD_Report,"No Description entered.","ERROR",1);
                return false;
            }
            switch(type)
            {
                 case ReportObject.HEADER:
                    {
                        ((ADAMS_JP_ReportObject_HeaderFieldContent)appoPanel).commit();

                    }break;

                 case ReportObject.BODY:
                    {
                        boolean testCommit=((ADAMS_JP_ReportObject_BodyCell)appoPanel).testCommit();
                        if(testCommit==true)
                        {
                            ((ADAMS_JP_ReportObject_BodyCell)appoPanel).commit();
                        }else
                            return false;
                    }break;

                 case ReportObject.TOTALIZER:
                    {
                        ((ADAMS_JP_ReportObject_TotalizerRow)appoPanel).commit();
                    }break;

                 case ReportObject.FOOTER:
                    {
                        ((ADAMS_JP_ReportObject_FooterRow)appoPanel).commit();
                    }break;
            }
            getInterface();
            //commitFatta=false;
            return true;
        }else
            return false;
    }
    
    public void getInterface()
    {
        //eb.IDOBJECT in automativo
        eb.OBJECT_TAG=jT_desc.getText();
        eb.HTML_VSIZE=jS_s1.getValue();
        eb.HTML_HSIZE=jS_s2.getValue();
        eb.HTML_VALIGN=jC_v.getSelectedIndex();
        eb.HTML_HALIGN=jC_h.getSelectedIndex();
        
        if(jR_ww.isSelected())
        {
            eb.HTML_WRAP=1;
        }else
        {
            eb.HTML_WRAP=0;
        }
        
        if(jR_b.isSelected())
        {
            eb.HTML_BACKGROUND=1;
            eb.HTML_BACKGROUND_R=jB_b.getBackground().getRed();
            eb.HTML_BACKGROUND_G=jB_b.getBackground().getGreen();
            eb.HTML_BACKGROUND_B=jB_b.getBackground().getBlue();
        }else
        {
            eb.HTML_BACKGROUND=0;
            eb.HTML_BACKGROUND_R=255;
            eb.HTML_BACKGROUND_G=255;
            eb.HTML_BACKGROUND_B=255;
        }
        
        if(jR_f.isSelected())
        {
            eb.HTML_FOREGROUND=1;
            eb.HTML_FOREGROUND_R=jB_f.getBackground().getRed();
            eb.HTML_FOREGROUND_G=jB_f.getBackground().getGreen();
            eb.HTML_FOREGROUND_B=jB_f.getBackground().getBlue();
        }else
        {
            eb.HTML_FOREGROUND=0;
            eb.HTML_FOREGROUND_R=0;
            eb.HTML_FOREGROUND_G=0;
            eb.HTML_FOREGROUND_B=0;
        }
        
        eb.HTML_STYLE=jC_style.getSelectedIndex();
        eb.HTML_FONTSIZE=jS_s3.getValue();
        
        if(jR_bold.isSelected())
        {
            eb.HTML_BOLD=1;
        }else
        {
            eb.HTML_BOLD=0;
        }
        
        if(jR_italic.isSelected())
        {
            eb.HTML_ITALIC=1;
        }else
        {
            eb.HTML_ITALIC=0;
        }
        
        if(jR_underlined.isSelected())
        {
            eb.HTML_UNDERLINE=1;
        }else
        {
            eb.HTML_UNDERLINE=0;
        }
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            eb.IDOBJECT=-1;
        }
    }
    
    private ReportObject.elementoBase getInterfaceAPPO()
    {
        ReportObject.elementoBase ebAPPO=new ReportObject.elementoBase();

        ebAPPO.OBJECT_TAG=jT_desc.getText();
        ebAPPO.HTML_VSIZE=jS_s1.getValue();
        ebAPPO.HTML_HSIZE=jS_s2.getValue();
        ebAPPO.HTML_VALIGN=jC_v.getSelectedIndex();
        ebAPPO.HTML_HALIGN=jC_h.getSelectedIndex();
        
        if(jR_ww.isSelected())
        {
            ebAPPO.HTML_WRAP=1;
        }else
        {
            ebAPPO.HTML_WRAP=0;
        }
        
        if(jR_b.isSelected())
        {
            ebAPPO.HTML_BACKGROUND=1;
            ebAPPO.HTML_BACKGROUND_R=jB_b.getBackground().getRed();
            ebAPPO.HTML_BACKGROUND_G=jB_b.getBackground().getGreen();
            ebAPPO.HTML_BACKGROUND_B=jB_b.getBackground().getBlue();
        }else
        {
            ebAPPO.HTML_BACKGROUND=0;
            ebAPPO.HTML_BACKGROUND_R=255;
            ebAPPO.HTML_BACKGROUND_G=255;
            ebAPPO.HTML_BACKGROUND_B=255;
        }
        
        if(jR_f.isSelected())
        {
            ebAPPO.HTML_FOREGROUND=1;
            ebAPPO.HTML_FOREGROUND_R=jB_f.getBackground().getRed();
            ebAPPO.HTML_FOREGROUND_G=jB_f.getBackground().getGreen();
            ebAPPO.HTML_FOREGROUND_B=jB_f.getBackground().getBlue();
        }else
        {
            ebAPPO.HTML_FOREGROUND=0;
            ebAPPO.HTML_FOREGROUND_R=0;
            ebAPPO.HTML_FOREGROUND_G=0;
            ebAPPO.HTML_FOREGROUND_B=0;
        }
        
        ebAPPO.HTML_STYLE=jC_style.getSelectedIndex();
        ebAPPO.HTML_FONTSIZE=jS_s3.getValue();
        
        if(jR_bold.isSelected())
        {
            ebAPPO.HTML_BOLD=1;
        }else
        {
            ebAPPO.HTML_BOLD=0;
        }
        
        if(jR_italic.isSelected())
        {
            ebAPPO.HTML_ITALIC=1;
        }else
        {
            ebAPPO.HTML_ITALIC=0;
        }
        
        if(jR_underlined.isSelected())
        {
            ebAPPO.HTML_UNDERLINE=1;
        }else
        {
            ebAPPO.HTML_UNDERLINE=0;
        }
        
        if(ADAMS_GlobalParam.INSERT_NEW_ELEMENT==1)
        {
            ebAPPO.IDOBJECT=-1;
        }
        
        return ebAPPO;
    }
    
    public void resetInterface()
    {
        jS_s1.setValue(0);
        jS_s2.setValue(0);
        jT_size.setText(""+jS_s3.getValue());
        jT_v.setText(""+jS_s1.getValue());
        jT_h.setText(""+jS_s2.getValue());
        jS_s3.setValue(0);
       
    }
    
    public void setInterface()
    {
        
        /**/
        jT_object_id.setText(""+eb.IDOBJECT);
        jT_desc.setText(""+eb.OBJECT_TAG);
        jT_v.setText(""+eb.HTML_VSIZE);
        jT_h.setText(""+eb.HTML_HSIZE);
        jS_s1.setValue(eb.HTML_VSIZE);
        jS_s2.setValue(eb.HTML_HSIZE);
        /**/
        
        /**/
        jC_v.setSelectedIndex(eb.HTML_VALIGN);
        jC_h.setSelectedIndex(eb.HTML_HALIGN);
        if(eb.HTML_WRAP==1)
            jR_ww.setSelected(true);
        else
            jR_ww.setSelected(false);
        /**/
        
        /**/
        if(eb.HTML_BACKGROUND==1)
        {
            jR_b.setSelected(true);
            jB_b.setBackground(new Color(eb.HTML_BACKGROUND_R,eb.HTML_BACKGROUND_G,eb.HTML_BACKGROUND_B));
        }
        else
        {
            jR_b.setSelected(false);
            jB_b.setBackground(Color.white);
        }
        
        
        if(eb.HTML_FOREGROUND==1)
        {
            jR_f.setSelected(true);
            jB_f.setBackground(new Color(eb.HTML_FOREGROUND_R,eb.HTML_FOREGROUND_G,eb.HTML_FOREGROUND_B));
        }
        else
        {
            jR_f.setSelected(false);
            jB_f.setBackground(Color.black);
        }
        /**/
        
        /**/
        jC_style.setSelectedIndex(eb.HTML_STYLE);
        jT_size.setText(""+eb.HTML_FONTSIZE);
        jS_s3.setValue(eb.HTML_FONTSIZE);
        if(eb.HTML_BOLD==1)
            jR_bold.setSelected(true);
        else
            jR_bold.setSelected(false);
        if(eb.HTML_ITALIC==1)
            jR_italic.setSelected(true);
        else
            jR_italic.setSelected(false);
        if(eb.HTML_UNDERLINE==1)
            jR_underlined.setSelected(true);
        else
            jR_underlined.setSelected(false);
        /**/
    }
    
    
    public void setAreaHelp(javax.swing.JTextArea ta)
    {
            jT_help=ta;
    }
    
    public void setFont()
    {
        jLabel2.setFont(ADAMS_GlobalParam.font_B11);
        jT_object_id.setFont(ADAMS_GlobalParam.font_B11);
        jLabel3.setFont(ADAMS_GlobalParam.font_B11);
        jT_desc.setFont(ADAMS_GlobalParam.font_B11);
        
        jLabel6.setFont(ADAMS_GlobalParam.font_B11);
        jLabel8.setFont(ADAMS_GlobalParam.font_B11);
        jLabel9.setFont(ADAMS_GlobalParam.font_B11);
        jLabel10.setFont(ADAMS_GlobalParam.font_B11);
        
        jLabel4.setFont(ADAMS_GlobalParam.font_B11);
        jLabel5.setFont(ADAMS_GlobalParam.font_B11);
        jC_v.setFont(ADAMS_GlobalParam.font_B11);
        jC_h.setFont(ADAMS_GlobalParam.font_B11);
        jT_v.setFont(ADAMS_GlobalParam.font_B11);
        jT_h.setFont(ADAMS_GlobalParam.font_B11);
        jR_ww.setFont(ADAMS_GlobalParam.font_B11);

        
        jR_b.setFont(ADAMS_GlobalParam.font_B11);
        jB_b.setFont(ADAMS_GlobalParam.font_B11);
        jR_f.setFont(ADAMS_GlobalParam.font_B11);
        jB_f.setFont(ADAMS_GlobalParam.font_B11);
        
        jLabel7.setFont(ADAMS_GlobalParam.font_B11);
        jC_style.setFont(ADAMS_GlobalParam.font_B11);
        jLabel11.setFont(ADAMS_GlobalParam.font_B11);
        jR_bold.setFont(ADAMS_GlobalParam.font_B11);
        jR_italic.setFont(ADAMS_GlobalParam.font_B11);
        jR_underlined.setFont(ADAMS_GlobalParam.font_B11);
        
        jT_size.setFont(ADAMS_GlobalParam.font_B11);
        
    }
    
    public void setCursorWidget()
    {    
        jT_object_id.setCursor(Cur_hand);
        jT_desc.setCursor(Cur_hand);
        
        jS_s1.setCursor(Cur_hand);
        jS_s2.setCursor(Cur_hand);
        
        jT_v.setCursor(Cur_hand);
        jT_h.setCursor(Cur_hand);
        jC_v.setCursor(Cur_hand);
        jC_h.setCursor(Cur_hand);
        jR_ww.setCursor(Cur_hand);
        
        jR_b.setCursor(Cur_hand);
        jB_b.setCursor(Cur_hand);
        jR_f.setCursor(Cur_hand);
        jB_f.setCursor(Cur_hand);
        
        jC_style.setCursor(Cur_hand);
        jR_bold.setCursor(Cur_hand);
        jR_italic.setCursor(Cur_hand);
        jR_underlined.setCursor(Cur_hand);
        
        jT_size.setCursor(Cur_hand);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jP_p1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jT_object_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jT_desc = new javax.swing.JTextField();
        jP_p2 = new javax.swing.JPanel();
        jP_size = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jS_s1 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jS_s2 = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();
        jT_v = new javax.swing.JTextField();
        jT_h = new javax.swing.JTextField();
        jP_allignment = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jC_v = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jC_h = new javax.swing.JComboBox();
        jR_ww = new javax.swing.JRadioButton();
        jP_color = new javax.swing.JPanel();
        jR_b = new javax.swing.JRadioButton();
        jB_b = new javax.swing.JButton();
        jR_f = new javax.swing.JRadioButton();
        jB_f = new javax.swing.JButton();
        jP_font = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jC_style = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jS_s3 = new javax.swing.JSlider();
        jR_bold = new javax.swing.JRadioButton();
        jR_italic = new javax.swing.JRadioButton();
        jR_underlined = new javax.swing.JRadioButton();
        jT_size = new javax.swing.JTextField();
        jP_p3 = new javax.swing.JPanel();
        
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input_data48.png")));
        jLabel1.setText("REPORT SCHEMA");
        jLabel1.setMinimumSize(new java.awt.Dimension(700, 70));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.ipadx = 600;
        gridBagConstraints1.ipady = 10;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 4, 4);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.weightx = 1.0;
        add(jLabel1, gridBagConstraints1);
        
        jP_p1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_p1.setBackground(new java.awt.Color(230, 230, 230));
        jP_p1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Component Identification"));
        jLabel2.setText("Object id");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.ipadx = 20;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel2, gridBagConstraints2);
        
        jT_object_id.setEditable(false);
        jT_object_id.setText("<Auto>");
        jT_object_id.setMinimumSize(new java.awt.Dimension(70, 20));
        jT_object_id.setPreferredSize(new java.awt.Dimension(50, 20));
        jT_object_id.setEnabled(false);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.ipadx = 50;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 0.5;
        jP_p1.add(jT_object_id, gridBagConstraints2);
        
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Description");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.ipadx = 20;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        jP_p1.add(jLabel3, gridBagConstraints2);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints2.weightx = 1.0;
        jP_p1.add(jT_desc, gridBagConstraints2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        add(jP_p1, gridBagConstraints1);
        
        jP_p2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_p2.setBackground(new java.awt.Color(230, 230, 230));
        jP_p2.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Appearance"));
        jP_size.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_size.setBackground(new java.awt.Color(230, 230, 230));
        jP_size.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Size"));
        jLabel6.setText("Vertical");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jLabel6, gridBagConstraints4);
        
        jS_s1.setBackground(new java.awt.Color(230, 230, 230));
        jS_s1.setMaximum(99);
        jS_s1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jS_s1StateChanged(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.ipadx = 50;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jS_s1, gridBagConstraints4);
        
        jLabel8.setText("pixels");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jLabel8, gridBagConstraints4);
        
        jLabel9.setText("Horizontal");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jLabel9, gridBagConstraints4);
        
        jS_s2.setBackground(new java.awt.Color(230, 230, 230));
        jS_s2.setMaximum(99);
        jS_s2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jS_s2StateChanged(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jS_s2, gridBagConstraints4);
        
        jLabel10.setText("%");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 3;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jLabel10, gridBagConstraints4);
        
        jT_v.setMinimumSize(new java.awt.Dimension(25, 20));
        jT_v.setPreferredSize(new java.awt.Dimension(25, 20));
        jT_v.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_vKeyReleased(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jT_v, gridBagConstraints4);
        
        jT_h.setMinimumSize(new java.awt.Dimension(25, 20));
        jT_h.setPreferredSize(new java.awt.Dimension(25, 20));
        jT_h.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_hKeyReleased(evt);
            }
        });
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints4.weightx = 1.0;
        jP_size.add(jT_h, gridBagConstraints4);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jP_size, gridBagConstraints3);
        
        jP_allignment.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;
        
        jP_allignment.setBackground(new java.awt.Color(230, 230, 230));
        jP_allignment.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Allignment"));
        jLabel4.setText("Vertical");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.weightx = 1.0;
        jP_allignment.add(jLabel4, gridBagConstraints5);
        
        jC_v.setBackground(java.awt.Color.white);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.ipadx = 50;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.weightx = 1.0;
        jP_allignment.add(jC_v, gridBagConstraints5);
        
        jLabel5.setText("Horizontal");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.weightx = 1.0;
        jP_allignment.add(jLabel5, gridBagConstraints5);
        
        jC_h.setBackground(java.awt.Color.white);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.weightx = 1.0;
        jP_allignment.add(jC_h, gridBagConstraints5);
        
        jR_ww.setText("Word wrap");
        jR_ww.setToolTipText("Direct");
        jR_ww.setContentAreaFilled(false);
        jR_ww.setFocusPainted(false);
        jR_ww.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_ww.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_ww.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_ww.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_ww.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_ww.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_ww.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints5.weightx = 1.0;
        jP_allignment.add(jR_ww, gridBagConstraints5);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jP_allignment, gridBagConstraints3);
        
        jP_color.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;
        
        jP_color.setBackground(new java.awt.Color(230, 230, 230));
        jP_color.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Colors"));
        jR_b.setText("Background");
        jR_b.setToolTipText("Direct");
        jR_b.setContentAreaFilled(false);
        jR_b.setFocusPainted(false);
        jR_b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_b.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_b.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_b.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_b.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_b.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_bActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.ipadx = 10;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints6.weightx = 1.0;
        jP_color.add(jR_b, gridBagConstraints6);
        
        jB_b.setBackground(java.awt.Color.white);
        jB_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_bActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.ipadx = 40;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        jP_color.add(jB_b, gridBagConstraints6);
        
        jR_f.setText("Forground");
        jR_f.setToolTipText("Direct");
        jR_f.setContentAreaFilled(false);
        jR_f.setFocusPainted(false);
        jR_f.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jR_f.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_f.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_f.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_f.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_f.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_f.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        jR_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR_fActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints6.weightx = 1.0;
        jP_color.add(jR_f, gridBagConstraints6);
        
        jB_f.setBackground(java.awt.Color.black);
        jB_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_fActionPerformed(evt);
            }
        });
        
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints6.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints6.weightx = 1.0;
        jP_color.add(jB_f, gridBagConstraints6);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jP_color, gridBagConstraints3);
        
        jP_font.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints7;
        
        jP_font.setBackground(new java.awt.Color(230, 230, 230));
        jP_font.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Font (HTML relative)"));
        jLabel7.setText("Style");
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jLabel7, gridBagConstraints7);
        
        jC_style.setBackground(java.awt.Color.white);
        jC_style.setMaximumSize(new java.awt.Dimension(110, 24));
        jC_style.setMinimumSize(new java.awt.Dimension(110, 24));
        jC_style.setPreferredSize(new java.awt.Dimension(110, 24));
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jC_style, gridBagConstraints7);
        
        jLabel11.setText("Relative size");
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jLabel11, gridBagConstraints7);
        
        jS_s3.setBackground(new java.awt.Color(230, 230, 230));
        jS_s3.setMaximum(3);
        jS_s3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jS_s3StateChanged(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 4;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jS_s3, gridBagConstraints7);
        
        jR_bold.setText("Bold");
        jR_bold.setToolTipText("Direct");
        jR_bold.setContentAreaFilled(false);
        jR_bold.setFocusPainted(false);
        jR_bold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_bold.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_bold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_bold.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_bold.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_bold.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_bold.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 5;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jR_bold, gridBagConstraints7);
        
        jR_italic.setText("Italic");
        jR_italic.setToolTipText("Direct");
        jR_italic.setContentAreaFilled(false);
        jR_italic.setFocusPainted(false);
        jR_italic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_italic.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_italic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_italic.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_italic.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_italic.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_italic.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 6;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jR_italic, gridBagConstraints7);
        
        jR_underlined.setText("Underlined");
        jR_underlined.setToolTipText("Direct");
        jR_underlined.setContentAreaFilled(false);
        jR_underlined.setFocusPainted(false);
        jR_underlined.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jR_underlined.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jR_underlined.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off.gif")));
        jR_underlined.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_underlined.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over.gif")));
        jR_underlined.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over.gif")));
        jR_underlined.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on.gif")));
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 7;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jR_underlined, gridBagConstraints7);
        
        jT_size.setMinimumSize(new java.awt.Dimension(25, 20));
        jT_size.setPreferredSize(new java.awt.Dimension(25, 20));
        jT_size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_sizeKeyReleased(evt);
            }
        });
        
        gridBagConstraints7 = new java.awt.GridBagConstraints();
        gridBagConstraints7.gridx = 3;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.insets = new java.awt.Insets(5, 5, 5, 5);
        gridBagConstraints7.weightx = 1.0;
        jP_font.add(jT_size, gridBagConstraints7);
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = 3;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        jP_p2.add(jP_font, gridBagConstraints3);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        add(jP_p2, gridBagConstraints1);
        
        jP_p3.setLayout(new java.awt.BorderLayout());
        
        jP_p3.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Specific Properties"));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        add(jP_p3, gridBagConstraints1);
        
    }//GEN-END:initComponents

    private void jR_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_fActionPerformed
        // Add your handling code here:
        if(jR_f.isSelected()==false)
            jB_f.setBackground(Color.black);
    }//GEN-LAST:event_jR_fActionPerformed

    private void jR_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR_bActionPerformed
        // Add your handling code here:
        if(jR_b.isSelected()==false)
            jB_b.setBackground(Color.white);
    }//GEN-LAST:event_jR_bActionPerformed

    private void jB_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_fActionPerformed
        // Add your handling code here:
        Color color = JColorChooser.showDialog(this, "Color Chooser", jB_f.getBackground());
        if (color != null)
        {  
            jB_f.setIcon(null);
            jB_f.setBackground(color);
            jB_f.setForeground(color);
            jR_f.setSelected(true);
            //R_ColorTextLegend = color.getRed();
            //G_ColorTextLegend = color.getGreen();
            //B_ColorTextLegend = color.getBlue();
        }
        else
        {
            //R_ColorTextLegend = -1;
        }
    }//GEN-LAST:event_jB_fActionPerformed

    
    private void jB_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_bActionPerformed
        // Add your handling code here:
        Color color = JColorChooser.showDialog(this, "Color Chooser", jB_b.getBackground());
        if (color != null)
        {  
            jB_b.setIcon(null);
            jB_b.setBackground(color);
            jB_b.setForeground(color);
            jR_b.setSelected(true);
            //R_ColorTextLegend = color.getRed();
            //G_ColorTextLegend = color.getGreen();
            //B_ColorTextLegend = color.getBlue();
        }
        else
        {
            //R_ColorTextLegend = -1;
        }
    }//GEN-LAST:event_jB_bActionPerformed

    private void jT_sizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_sizeKeyReleased
        // Add your handling code here:
        try
        {
            jS_s3.setValue((new Integer(jT_size.getText())).intValue());
            jS_s3.repaint();
        }catch(Exception e){};
    }//GEN-LAST:event_jT_sizeKeyReleased

    private void jT_hKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_hKeyReleased
        // Add your handling code here:
        try
        {
            jS_s2.setValue((new Integer(jT_h.getText())).intValue());
            jS_s2.repaint();
        }catch(Exception e){};
    }//GEN-LAST:event_jT_hKeyReleased

    private void jT_vKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_vKeyReleased
        // Add your handling code here:
        try
        {
            jS_s1.setValue((new Integer(jT_v.getText())).intValue());
            jS_s1.repaint();
        }catch(Exception e){}; 
    }//GEN-LAST:event_jT_vKeyReleased

    private void jS_s3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jS_s3StateChanged
        // Add your handling code here:
        jT_size.setText(""+jS_s3.getValue());
        jT_size.repaint();
    }//GEN-LAST:event_jS_s3StateChanged

    private void jS_s2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jS_s2StateChanged
        // Add your handling code here:
        jT_h.setText(""+jS_s2.getValue());
        jT_h.repaint();
    }//GEN-LAST:event_jS_s2StateChanged

    private void jS_s1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jS_s1StateChanged
        // Add your handling code here:
        jT_v.setText(""+jS_s1.getValue());
        jT_v.repaint();
    }//GEN-LAST:event_jS_s1StateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_p1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jT_object_id;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jT_desc;
    private javax.swing.JPanel jP_p2;
    private javax.swing.JPanel jP_size;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSlider jS_s1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSlider jS_s2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JTextField jT_v;
    private javax.swing.JTextField jT_h;
    private javax.swing.JPanel jP_allignment;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox jC_v;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox jC_h;
    private javax.swing.JRadioButton jR_ww;
    private javax.swing.JPanel jP_color;
    private javax.swing.JRadioButton jR_b;
    private javax.swing.JButton jB_b;
    private javax.swing.JRadioButton jR_f;
    private javax.swing.JButton jB_f;
    private javax.swing.JPanel jP_font;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox jC_style;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JSlider jS_s3;
    private javax.swing.JRadioButton jR_bold;
    private javax.swing.JRadioButton jR_italic;
    private javax.swing.JRadioButton jR_underlined;
    private javax.swing.JTextField jT_size;
    private javax.swing.JPanel jP_p3;
    // End of variables declaration//GEN-END:variables

}
