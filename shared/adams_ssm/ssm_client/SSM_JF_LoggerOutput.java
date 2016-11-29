/*
 * SSM_JF_LoggerOutput.java
 *
 * Created on 4 maggio 2005, 11.01
 */

/**
 *
 * @author  root
 */

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_JF_LoggerOutput extends javax.swing.JFrame
{
    /** Creates new form SSM_JF_LoggerOutput */
    private CS_BLOCK_LOG[] Local_AllBlock       = null;
    private Vector vRow                         = new Vector();
    private String msgDef[]                     = new String[100];
    private int attDef[]                        = new int[100];
    private int priDef[]                        = new int[100];
    private String Local_switch                 = "";
    private Vector vSwitch                      = new Vector();
    private DATA_CENTRALI[] Local_ALLcentrali   = null;
    int flagNewBlock                            = 0;
    private SSM_JF_ConfigLog parente             = null;
    boolean flagButton                          = true;
    
    public SSM_JF_LoggerOutput(CS_BLOCK_LOG[] AllBlock,String str,DATA_CENTRALI[] ALLcentrali,int flag,boolean flag2) {
        Local_AllBlock=AllBlock;
        Local_ALLcentrali=ALLcentrali;
        Local_switch=str;
        flagNewBlock=flag;
        flagButton=flag2;
        initComponents();
        
        
        //aggiungo lengthswitch
        int len=Local_ALLcentrali.length;
        
        for(int i=0;i<len;i++)
        {
          String strSwitch=new String(ALLcentrali[i].Descrizione).trim();
          javax.swing.JRadioButton jRb=new javax.swing.JRadioButton();
          jRb.setBackground(new java.awt.Color(230, 230, 230));
          jRb.setFont(new java.awt.Font("Dialog", 0, 11));
          jRb.setText(strSwitch);
          jRb.setFocusPainted(false);
          jRb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_mini.gif")));
          jRb.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
          jRb.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_off_over_mini.gif")));
          jRb.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_over_mini.gif")));
          jRb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_on_mini.gif")));

          if(SSM_GlobalParam.idOccupati[ALLcentrali[i].IdCentrale]==true)
          {
              jRb.setForeground(new Color(255,0,0));
          }else
              jRb.setForeground(new Color(0,0,0));
          
          jPanel3.add(jRb);
          vSwitch.addElement(jRb);
          jRb.setCursor(cursor_hand);
          if(strSwitch.equals(str))
          {
              jRb.setSelected(true);
          }
          else
          {
              jRb.setSelected(false);
          }
        }
        if(flagButton==false)
        {
            jPanel2.remove(jButton3);
            jPanel2.remove(jButton4);
            jPanel2.validate();
            jPanel9.remove(jPanel3);
            jPanel9.validate();
        }
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(25);
        jPanel4.add(new SSM_JF_LoggerRow(true,null));//, java.awt.BorderLayout.NORTH); //ADD Intestazione ROWS
        
        jLabel2.setText(""+str);
        jLabel3.setText(""+new String(AllBlock[0].nome).trim());
        
        jButton3.setCursor(cursor_hand);
        jButton4.setCursor(cursor_hand);
        jButton2.setCursor(cursor_hand);
        
        jLabel3.setFont(SSM_GlobalParam.font_B12);
        jLabel2.setFont(SSM_GlobalParam.font_B12);
        jLabel1.setFont(SSM_GlobalParam.font_B12);
        insertRow();
        setCenteredFrame(900,600);
        
        
        if(flagNewBlock==-1)
            len=1;
        else
            len=Local_AllBlock.length;
        
        for(int i=0;i<100;i++)
        {
            msgDef[i]= "NOT DEFINED";
            attDef[i]= 0;  
            priDef[i]= 0; 
        }
        
        for(int i=0;i<len;i++)
        { 
            msgDef[Local_AllBlock[i].cod_err]= new String(Local_AllBlock[i].msg).trim();
            attDef[Local_AllBlock[i].cod_err]= Local_AllBlock[i].urgenza;  
            priDef[Local_AllBlock[i].cod_err]= Local_AllBlock[i].priorita;
        }
    }
    
    public void resetCentrali()
    {
        
        for(int i=0;i<vSwitch.size();i++)
        {
            javax.swing.JRadioButton appo=(javax.swing.JRadioButton)vSwitch.elementAt(i);
            appo.setForeground(new Color(0,0,0));
        }
    }

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;

        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
    
    
    void insertRow()
    {
        int len;
        if(flagNewBlock==-1)
            len=1;
        else
            len=Local_AllBlock.length;
        for(int i=0;i<100;i++)
        {
            CS_BLOCK_LOG appoBlock=new CS_BLOCK_LOG();
            
            appoBlock.item=Local_AllBlock[0].item;
            appoBlock.block=Local_AllBlock[0].block;
            appoBlock.cod_err=i;
            appoBlock.urgenza=0;
            appoBlock.priorita=0;
            appoBlock.msg=SSM_GlobalParam.set_String_toChar("NOT DEFINED",132);
            appoBlock.nome=SSM_GlobalParam.set_String_toChar(new String(Local_AllBlock[0].nome).trim(),40);
            appoBlock.idFep=Local_AllBlock[0].idFep;
            
            SSM_JF_LoggerRow riga=new SSM_JF_LoggerRow(false,appoBlock);
            if(flagButton==false)
                riga.disableAll();
            
            jpRow.add(riga);
            vRow.addElement(riga);
        }

        for(int i=0;i<len;i++)
        {
                SSM_JF_LoggerRow appo=(SSM_JF_LoggerRow)vRow.elementAt(Local_AllBlock[i].cod_err);
                //SSM_JF_LoggerRow riga=new SSM_JF_LoggerRow(false,Local_AllBlock[i]);
                //jpRow.add(riga);
                //vRow.addElement(riga);
                appo.setMsg(new String(Local_AllBlock[i].msg).trim());
                appo.setAttention(Local_AllBlock[i].urgenza);
                appo.setPriority(Local_AllBlock[i].priorita);
                
                
                appo.setColor(new java.awt.Color(0,0,0));
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpRow = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        
        setTitle("Msg Log Setup");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jPanel1.setLayout(new java.awt.BorderLayout(0, 2));
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setBackground(new java.awt.Color(230, 230, 230));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configuration.png")));
        jLabel1.setText("Msg Log Setup");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 42));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 42));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 42));
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
        
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);
        
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));
        
        jPanel11.setBackground(new java.awt.Color(230, 230, 230));
        jPanel11.setBorder(new javax.swing.border.EtchedBorder());
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/item_switch_off.png")));
        jLabel2.setText("jLabel2");
        jPanel11.add(jLabel2);
        
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run_proc.png")));
        jLabel3.setText("jLabel3");
        jPanel11.add(jLabel3);
        
        jPanel1.add(jPanel11, java.awt.BorderLayout.CENTER);
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        
        jpRow.setLayout(new java.awt.GridLayout(0, 1));
        
        jpRow.setBackground(new java.awt.Color(230, 230, 230));
        jpRow.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane1.setViewportView(jpRow);
        
        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        
        jPanel9.setLayout(new java.awt.BorderLayout());
        
        jPanel9.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jButton3.setBackground(new java.awt.Color(230, 230, 230));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        
        jPanel2.add(jButton3);
        
        jButton4.setForeground(java.awt.Color.red);
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_def_message.jpg")));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.setPreferredSize(new java.awt.Dimension(100, 22));
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_def_message_press.jpg")));
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_def_message_over.jpg")));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        
        jPanel2.add(jButton4);
        
        jButton2.setForeground(java.awt.Color.red);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setPreferredSize(new java.awt.Dimension(100, 22));
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        jPanel2.add(jButton2);
        
        jPanel9.add(jPanel2, java.awt.BorderLayout.CENTER);
        
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel3.setBackground(new java.awt.Color(230, 230, 230));
        jPanel3.setBorder(new javax.swing.border.TitledBorder("Select switch"));
        jPanel9.add(jPanel3, java.awt.BorderLayout.NORTH);
        
        getContentPane().add(jPanel9, java.awt.BorderLayout.SOUTH);
        
        pack();
    }//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Add your handling code here:
        //apply
        int errore=-1;
        
        JOptionPane jOptionPane1 = new JOptionPane(); 
        String str="";
        for(int i=0;i<vSwitch.size();i++)
        {
                JRadioButton jrb=(JRadioButton)vSwitch.elementAt(i);
                if(jrb.isSelected()==true)
                {
                    str=str+=new String(Local_ALLcentrali[i].Descrizione).trim()+", ";
                }
        }
        if(str.equals(""))
        {
                warningProblem("No switch selected","Error Message");
                if(parente!=null)
                    parente.refreshDopoLog();
                return;
        }
        
        int YES_NO = jOptionPane1.showConfirmDialog(this,"Confirm modify messages for "+str+" ?","Question Messagge",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(YES_NO == 0) //YES_NO=0=>YES --------  YES_NO=1=>NO
        {
            SSM_JF_LoggerRow appo;
            
            Vector vMsg=new Vector();
            boolean flagDel=true;
            
            for(int i=0;i<vRow.size();i++)
            {
                appo=(SSM_JF_LoggerRow)vRow.elementAt(i);
                String msg=appo.getMsg();
                
                if(msg.indexOf("NOT DEFINED")!=-1)
                {
                    continue;
                }else
                {
                     flagDel=false;   
                     break;
                }
            }
            
            if(flagDel)
            {
                System.out.println("devo cancellare il blocco");
                appo=(SSM_JF_LoggerRow)vRow.elementAt(0);
                appo.setMsg("TO BE DEFINED");
                appo.setColor(new Color(0,0,0));
            }
            
            
            for(int i=0;i<vRow.size();i++)
            {
                appo=(SSM_JF_LoggerRow)vRow.elementAt(i);
                String msg=appo.getMsg();
                int att=appo.getAttention();
                int pri=appo.getPriority();
                
                msgDef[i]=msg;
                attDef[i]=att;
                priDef[i]=pri;
                
                //CS_BLOCK_LOG msgInVector=(CS_BLOCK_LOG)appo.getMsgBlock().clone(); --> DA SISTEMARE per ADAMS
                CS_BLOCK_LOG msgInVector=(CS_BLOCK_LOG)appo.getMsgBlock();
                //if(flagDel)
                //    msgInVector.nome=SSM_GlobalParam.set_String_toChar("PROC",7);
                
                msgInVector.msg=SSM_GlobalParam.set_String_toChar(msg,132);
                msgInVector.urgenza=att;
                msgInVector.priorita=pri;
                vMsg.addElement(msgInVector);
                
            }
            int lenMsg=vMsg.size();
            System.out.println("lenMsg="+lenMsg);
            CS_BLOCK_LOG[] msgOK=new CS_BLOCK_LOG[lenMsg];
            for(int i=0;i<lenMsg;i++)
            {
                
                msgOK[i]=(CS_BLOCK_LOG)vMsg.elementAt(i);
                
            }
            
            if(lenMsg==0)
            {
                    jOptionPane1.showMessageDialog(this,"No Messages modified.","Information Messagge",JOptionPane.INFORMATION_MESSAGE);
                    if(parente!=null)
                        parente.refreshDopoLog();
                    return;
            }
            for(int i=0;i<vSwitch.size();i++)
            {
                JRadioButton jrb=(JRadioButton)vSwitch.elementAt(i);
                if(jrb.isSelected()==true)
                {
                    for(int j=0;j<vMsg.size();j++)
                    {
                        msgOK[j].idFep=Local_ALLcentrali[i].IdCentrale;
                    }
                    errore=SSM_GlobalParam.CORBAConn.writeModifyLog(0,new String(Local_ALLcentrali[i].Descrizione).trim(), msgOK);
                    System.out.println("Errore in writeModifyLog["+new String(Local_ALLcentrali[i].Descrizione).trim()+"]="+errore);
                    if(errore==18)
                    {
                        //devo ricreare il file
                        System.out.println("devo ricreare il file.");
                        int errore1=SSM_GlobalParam.CORBAConn.createLogFile(-2,new String(Local_ALLcentrali[i].Descrizione).trim());
                        return;
                    }


                    if(errore!=1)
                    {
                        warningProblem("Block msg unmodified in "+new String(Local_ALLcentrali[i].Descrizione).trim(),"Error Message");
                    }
                }
            }
            if(errore==1)
            {
                jOptionPane1.showMessageDialog(this,"Messages modified.","Information Messagge",JOptionPane.INFORMATION_MESSAGE);
            }else
                warningProblem("Block msg unmodified .","Error Message");
        }
        else
        {
            if(parente!=null)
                parente.refreshDopoLog();
            return;
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Add your handling code here:
        //default msg;
        SSM_JF_LoggerRow appo;
        for(int i=0;i<vRow.size();i++)
        {
                appo=(SSM_JF_LoggerRow)vRow.elementAt(i);
                appo.setMsg(msgDef[i]);
                appo.setAttention(attDef[i]);
                appo.setPriority(priDef[i]);
                
                //System.out.println("msgDef[i]="+msgDef[i]);
                
                if(msgDef[i].indexOf("NOT DEFINED")!=-1)
                {
                    appo.setColor(new java.awt.Color(9,126,165));
                }else
                    appo.setColor(new java.awt.Color(0,0,0)); 
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    public void setParente(SSM_JF_ConfigLog p)
    {
       parente=p;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Add your handling code here:
        closeFrame();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Add your handling code here:
        closeFrame();
    }//GEN-LAST:event_jButton1ActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
       closeFrame();
    }//GEN-LAST:event_exitForm

    private void closeFrame()
    {
        if(parente!=null)
        {
            parente.refreshDopoLog();
            parente.repaint();
        }
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpRow;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    
    private void warningProblem(String str1,String str2)
    {
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE);

            //warning.showConfirmDialog(this,""+str1,""+str2,JOptionPane.ERROR_MESSAGE,JOptionPane.QUESTION_MESSAGE);
    }
}
