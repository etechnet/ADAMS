/*
 * ADAMS_JF_Report.java
 *
 * Created on 9 agosto 2005, 11.52
 */

/**
 *
 * @author  Raffaele Ficcadenti raffaele.ficcadenti@e-tech.net
 */


import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.table.*;
import javax.swing.DefaultCellEditor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_JF_Report extends javax.swing.JDialog {

    private boolean                                 DEBUG                       = false;
    private ADAMS_JF_Wizard                          localParent                 = null;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private javax.swing.JPanel                      jpCenter                    = null;
    private int                                     type                        = 0;
    
    /** Creates new form ADAMS_JF_Report */
    public ADAMS_JF_Report(javax.swing.JFrame parent,boolean modal,String Title,String config_NAME_ALIAS,String config_NAME_SCHEMA,int type,int subType,ReportObject.elementoBase eb,ReportObject rs) {
        //this.localParent = Parent;
        super((java.awt.Frame)parent,true);
        ADAMS_GlobalParam.ADAMS_JD_Report=this;
        initComponents();
        this.type=type;
        
        switch(type)
        {
            case ADAMS_GlobalParam.REPORT_SCHEMA:
            {
                jpCenter = new ADAMS_JP_ReportSchema(config_NAME_ALIAS,config_NAME_SCHEMA);
                ADAMS_JP_Report_b1 jpB1=new ADAMS_JP_Report_b1(this,jpCenter);
                getContentPane().add(jpCenter, java.awt.BorderLayout.CENTER);
                getContentPane().add(jpB1, java.awt.BorderLayout.SOUTH);
                setCenteredFrame(900,700);
                ((ADAMS_JP_ReportSchema)jpCenter).setAreaHelp(jpB1.getAreaHelp());
                ((ADAMS_JP_ReportSchema)jpCenter).setButtonPanel(jpB1);
            }break;
            case ADAMS_GlobalParam.REPORT_OBJECT_EDITOR:
            {
                jpCenter = new ADAMS_JP_ReportObjectEditor(config_NAME_ALIAS,config_NAME_SCHEMA,subType,eb,rs);
                ADAMS_JP_Report_b2 jpB2=new ADAMS_JP_Report_b2(this,jpCenter);
                getContentPane().add(jpCenter, java.awt.BorderLayout.CENTER);
                getContentPane().add(jpB2, java.awt.BorderLayout.SOUTH);
                setCenteredFrame(1000,750);
                ((ADAMS_JP_ReportObjectEditor)jpCenter).setAreaHelp(jpB2.getAreaHelp());
            }break;   
        }
        
        
        setTitle(Title);
        setCursorWidget();
        setFont();
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        //show();
        this.setVisible(true);
    }
    
    public ADAMS_JF_Report(javax.swing.JDialog parent,boolean modal,String Title,String config_NAME_ALIAS,String config_NAME_SCHEMA,int type,int subType,ReportObject.elementoBase eb,ReportObject rs) {
        //this.localParent = Parent;
        super(parent,true);
        ADAMS_GlobalParam.ADAMS_JD_Report=this;
        initComponents();
        this.type=type;
        
        switch(type)
        {
            case ADAMS_GlobalParam.REPORT_SCHEMA:
            {
                jpCenter = new ADAMS_JP_ReportSchema(config_NAME_ALIAS,config_NAME_SCHEMA);
                ADAMS_JP_Report_b1 jpB1=new ADAMS_JP_Report_b1(this,jpCenter);
                getContentPane().add(jpCenter, java.awt.BorderLayout.CENTER);
                getContentPane().add(jpB1, java.awt.BorderLayout.SOUTH);
                setCenteredFrame(900,700);
                ((ADAMS_JP_ReportSchema)jpCenter).setAreaHelp(jpB1.getAreaHelp());
                ((ADAMS_JP_ReportSchema)jpCenter).setButtonPanel(jpB1);
            }break;
            case ADAMS_GlobalParam.REPORT_OBJECT_EDITOR:
            {
                jpCenter = new ADAMS_JP_ReportObjectEditor(config_NAME_ALIAS,config_NAME_SCHEMA,subType,eb,rs);
                ADAMS_JP_Report_b2 jpB2=new ADAMS_JP_Report_b2(this,jpCenter);
                getContentPane().add(jpCenter, java.awt.BorderLayout.CENTER);
                getContentPane().add(jpB2, java.awt.BorderLayout.SOUTH);
                setCenteredFrame(1000,750);
                ((ADAMS_JP_ReportObjectEditor)jpCenter).setAreaHelp(jpB2.getAreaHelp());
            }break;   
        }
        
        
        setTitle(Title);
        setCursorWidget();
        setFont();
        
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        //show();
        this.setVisible(true);
    }
    
    public void setFont()
    {
    }
    
    public void setCursorWidget()
    {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        pack();
    }//GEN-END:initComponents

    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        ADAMS_GlobalParam.INSERT_NEW_ELEMENT=0;
        closeFrame(false);
    }//GEN-LAST:event_exitForm

    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
    
    public boolean testModify()
    {
        boolean testMODIFY=((ADAMS_JP_ReportObjectEditor)jpCenter).testModify();
        return testMODIFY;
    }
    
    /*private boolean testModify2() // da controllare eventuali cavolate
    {
        
        boolean testMODIFY1=((ADAMS_JP_ReportSchema)jpCenter).testModify1();
        System.out.println("ADAMS_JP_Report_b3 -->  testMODIFY1="+testMODIFY1);
        
        if(testMODIFY1==false)
        {
            return false;
        }else
        {
            boolean testMODIFY=((ADAMS_JP_ReportSchema)jpCenter).testModify();
            System.out.println("ADAMS_JP_Report_b3 -->  testMODIFY="+testMODIFY);
            return testMODIFY;
        }
    }*/
    
    public boolean testModify3()
    {
        
        boolean testMODIFY1=((ADAMS_JP_ReportSchema)jpCenter).testModify1();
        if(DEBUG)
        {
            System.out.println("ADAMS_JF_Report [testModify3()] -->  testMODIFY1="+testMODIFY1);
        }
        
        if(testMODIFY1==true)
        {
            return true;
        }else
        {
            boolean testMODIFY=((ADAMS_JP_ReportSchema)jpCenter).testModify();
            if(DEBUG)
            {
                System.out.println("ADAMS_JF_Report [testModify3()] -->  testMODIFY="+testMODIFY);
            }
            return testMODIFY;
        }
    }
    
    public void aggiornaListaReport()
    {
        ((ADAMS_JP_ReportSchema)jpCenter).aggiornaListaReport();
    }
    
    
    public void closeFrame(boolean flag)
    {
        switch(type)
        {
            case ADAMS_GlobalParam.REPORT_SCHEMA:
            {
                if(flag==true)
                {
                    ((ADAMS_JP_ReportSchema)jpCenter).commitFatta=true;
                    ((ADAMS_JP_ReportSchema)jpCenter).fattaDELETE=false;
                    dispose();
                }else
                {
                    boolean flagMODIFY=testModify3();
                    if(DEBUG)
                    {
                        System.out.println("ADAMS_JF_Report [closeFrame()] -->  flagMODIFY="+flagMODIFY);
                        System.out.println("ADAMS_JF_Report [closeFrame()] -->  fattaDELETE="+((ADAMS_JP_ReportSchema)jpCenter).fattaDELETE);
                        if(DEBUG)
                        {
                            System.out.println("TEST N."+((ADAMS_JP_ReportSchema)jpCenter).nTest+" è fallito.");
                        }
                    }
                    if( ((ADAMS_JP_ReportSchema)jpCenter).fattaDELETE==false)
                    {
                        if(flagMODIFY==true)
                        {
                            ADAMS_JD_Message op = new ADAMS_JD_Message(this,true,"No COMMIT has been done. Do you really want to exit ?","Info",6);
                            int Yes_No = op.getAnswer();

                            if(Yes_No == 1)
                            {
                                ((ADAMS_JP_ReportSchema)jpCenter).commitFatta=true;
                                ((ADAMS_JP_ReportSchema)jpCenter).fattaDELETE=false;

                                dispose();
                            }else
                            {
                                return;
                            }             

                        }else
                        {

                            ((ADAMS_JP_ReportSchema)jpCenter).commitFatta=true;
                            ((ADAMS_JP_ReportSchema)jpCenter).fattaDELETE=false;
                            dispose();
                        }
                    }else
                    {

                        ((ADAMS_JP_ReportSchema)jpCenter).commitFatta=true;
                        ((ADAMS_JP_ReportSchema)jpCenter).fattaDELETE=false;
                        dispose();
                    }
                }
                
            }break;
            case ADAMS_GlobalParam.REPORT_OBJECT_EDITOR:
            {
                /*if(((ADAMS_JP_ReportObjectEditor)jpCenter).commitFatta==false)
                {
                    ADAMS_JD_Message op = new ADAMS_JD_Message(null,true,"Non è stata effettuata la COMMIT. Vuoi uscire??","Info",6);
                    int Yes_No = op.getAnswer();
                    
                    if(Yes_No == 1)
                    {
                         dispose();
                    }else
                    {
                        return;
                    }       
                }*/
                dispose();
            }break;   
        }
        
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
