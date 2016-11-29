/*
 * SSM_ProcessRowNew.java
 *
 * Created on 6 novembre 2006, 13.27
 */

/**
 *
 * @author  root
 * @version 
 */
import javax.swing.*;
import java.awt.Cursor;
import java.awt.Color;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_ProcessRowNew implements Runnable {

    public int                                          type                            = 0;
    private Thread                                      th                              = null;
    private javax.swing.JTextArea                       local_jTextArea                 = null;
    private int                                         Local_numRestartTime            = 0;
    private int                                         param1Value                     = 0;
    private int                                         CaseConversion1                 = 1;
    private int                                         param2Value                     = 0;
    private int                                         CaseConversion2                 = 1;
    private boolean                                     flagReverse                     = false;
    private int                                         IDProcesso                      = -2;
    private String                                      local_switch                    = null; 
    private SSM_JF_MonitoringOrbix                       rifParent                       = null;
    private Cursor                                      Cur_default                     = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                      Cur_wait                        = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                      Cur_hand                        = new Cursor(Cursor.HAND_CURSOR);
    
    public JButton                                      jB_process;
    public String                                       name_process_completo;
    public JButton                                      jB_status;
    public JButton                                      jB_type1Param;
    public JButton                                      jB_type2Param;
    public JLabel                                       jL_type1ParamDesc;
    public JLabel                                       jL_type2ParamDesc;
    public JLabel                                       jL_message;
    public JLabel                                       jL_type;
    
    private int                                         RUN_OPERATION                   =-1;
    private final int                                   RUN_jB_process                  = 0;
    private final int                                   RUN_jB_status                   = 1;

    private SSM_JF_textArea JF_TArea          = null;
    
    /** Creates new SSM_ProcessRowNew */
    public SSM_ProcessRowNew(boolean rowHead,int ID_Processo,String SWITCH,SSM_JF_MonitoringOrbix Parent,int type) {
        
        this.IDProcesso         = ID_Processo;
        this.local_switch       = SWITCH;
        this.rifParent          = Parent;
        this.local_jTextArea    = Parent.get_JTextArea();
        this.type               = type;
        initComponents();
        setCursorGUI(Cur_hand);
        
    }
    
    public void setCursorGUI(Cursor c)
    {
        jB_process.setCursor(c);
        jB_status.setCursor(c);
        jB_type1Param.setCursor(c);
        jB_type2Param.setCursor(c);
        jL_type1ParamDesc.setCursor(c);
        jL_type2ParamDesc.setCursor(c);
        jL_message.setCursor(c);
        jL_type.setCursor(c);;
    }
    
    
    private void initComponents() 
    {
        
        jL_type = new JLabel("Run"); 
        jL_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_proc.png")));
        jL_type.setBackground(new java.awt.Color(230, 230, 230));
        jL_type.setText("1 Parameter");
        jL_type.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        //jL_type.setMaximumSize(new java.awt.Dimension(130, 24));
        //jL_type.setMinimumSize(new java.awt.Dimension(130, 24));
        //jL_type.setPreferredSize(new java.awt.Dimension(130, 24));
        jL_type.setOpaque(true);
        
        
        jB_process = new JButton("PROVA"); 
        jB_process.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jB_process.setBackground(new java.awt.Color(230, 230, 230));
        jB_process.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_process.setFocusPainted(false);
        jB_process.setMargin(new java.awt.Insets(2, 2, 2, 2));
        //jB_process.setMaximumSize(new java.awt.Dimension(150, 24));
        //jB_process.setMinimumSize(new java.awt.Dimension(150, 24));
        //jB_process.setPreferredSize(new java.awt.Dimension(150, 24));
        /*jB_process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_process_ActionPerformed(evt);
            }
        });*/
        jB_process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_processActionPerformed(evt);
            }
        });
              
        jB_status = new JButton(); 
        jB_status.setBackground(new java.awt.Color(230, 230, 230));
        jB_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        jB_status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_status.setBorderPainted(false);
        jB_status.setFocusPainted(false);
        jB_status.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_statusActionPerformed(evt);
            }
        });
        //jB_status.setMaximumSize(new java.awt.Dimension(80, 24));
        //jB_status.setMinimumSize(new java.awt.Dimension(80, 24));
        //jB_status.setPreferredSize(new java.awt.Dimension(80, 24));
        
        jB_type1Param = new JButton(); 
        jB_type1Param.setBackground(new java.awt.Color(230, 230, 230));
        jB_type1Param.setText("D");
        jB_type1Param.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type1Param.setFocusPainted(false);
        jB_type1Param.setMargin(new java.awt.Insets(0, 0, 0, 0));
        //jB_type1Param.setMaximumSize(new java.awt.Dimension(40, 24));
        //jB_type1Param.setMinimumSize(new java.awt.Dimension(40, 24));
        //jB_type1Param.setPreferredSize(new java.awt.Dimension(40, 24));
        
        jL_type1ParamDesc = new JLabel("prova 1"); 
        jL_type1ParamDesc.setBackground(new java.awt.Color(230, 230, 230));
        jL_type1ParamDesc.setText("1 Parameter");
        jL_type1ParamDesc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        //jL_type1ParamDesc.setMaximumSize(new java.awt.Dimension(80, 24));
        //jL_type1ParamDesc.setMinimumSize(new java.awt.Dimension(80, 24));
        //jL_type1ParamDesc.setPreferredSize(new java.awt.Dimension(80, 24));
        jL_type1ParamDesc.setOpaque(true);
        jB_type1Param.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_type1ParamActionPerformed(evt);
            }
        });
        
        
        jB_type2Param = new JButton(); 
        jB_type2Param.setBackground(new java.awt.Color(230, 230, 230));
        jB_type2Param.setText("D");
        jB_type2Param.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type2Param.setFocusPainted(false);
        jB_type2Param.setMargin(new java.awt.Insets(0, 0, 0, 0));
        //jB_type2Param.setMaximumSize(new java.awt.Dimension(40, 24));
        //jB_type2Param.setMinimumSize(new java.awt.Dimension(40, 24));
        //jB_type2Param.setPreferredSize(new java.awt.Dimension(40, 24));
        
        jL_type2ParamDesc = new JLabel("prova 2"); 
        jL_type2ParamDesc.setBackground(new java.awt.Color(230, 230, 230));
        jL_type2ParamDesc.setText("1 Parameter");
        jL_type2ParamDesc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        //jL_type2ParamDesc.setMaximumSize(new java.awt.Dimension(80, 24));
        //jL_type2ParamDesc.setMinimumSize(new java.awt.Dimension(80, 24));
        //jL_type2ParamDesc.setPreferredSize(new java.awt.Dimension(80, 24));
        jL_type2ParamDesc.setOpaque(true);
        jB_type2Param.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_type2ParamActionPerformed(evt);
            }
        });

        jL_message = new JLabel("message"); 
        jL_message.setBackground(new java.awt.Color(230, 230, 230));
        jL_message.setText("1 Parameter");
        jL_message.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        //jL_message.setMaximumSize(new java.awt.Dimension(420, 24));
        //jL_message.setMinimumSize(new java.awt.Dimension(420, 24));
        //jL_message.setPreferredSize(new java.awt.Dimension(420, 24));
        jL_message.setOpaque(true);
    }
    
    private void start()
    {        
        th = null;
        th = new Thread(this);
        th.start();
    }
    
    
    /**
     *Questo metodo ritorna indice univoco del processo.
     *@return indice univoco del processo.
     */
    public int getIDProcess()
    {
        return this.IDProcesso;
    }
    
    /**
     *Questo metodo ritorna indice univoco del processo.
     *@return indice univoco del processo.
     */
    public String getNameProcess()
    {
        return jB_process.getText().trim();
    }
    
    public String getNameProcessCompleto()
    {
        return name_process_completo;
    }
    
    
        
    /**
     *Questo metodo ritorna il numero di restart del processo.
     *@return numero di restart del processo.
     */
    public int getnumRestartTime()
    {
        return this.Local_numRestartTime;
    }
    /**
     * Setta il testo della label della tipologia del processo.
     *@param strType Tipologia del processo.
     */
    public void setTypeProcess(String strType)
    {
        jL_type.setText(strType);
    }
    /**
     * Setta il testo della label del nome processo.
     *@param strName Nome processo.
     */
    public void setNameProcess(String strName)
    {
        int posDot=strName.lastIndexOf('.');
        String appo=strName;
        
        if(posDot>=0)
        {
            appo=strName.substring(posDot+1);
        }
        
        jB_process.setText(appo);
        name_process_completo=strName;
    }
    /**
     * Setta il Colore di Backgroud della label dove ï¿½ visualizzato il nome processo.
     *@param strName Nome processo.
     */
    public void setColorBG_NameProcess(java.awt.Color colorBG)
    {
        if(flagReverse==false)
        {
            jB_process.setBackground(colorBG);
        }
        else
        {
            jB_process.setForeground(colorBG);
        }
    }
    
    /**
     * Setta lo stato del processo, mediante un'icona definita.
     *@param status Stato del processo.
     */
    public void setStatus(int status)
    {
        if(status == 0)
        {
            jB_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png")));
        }
        else
        {
            jB_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop22.png")));
        }
    }
    
    /**
     *Il metodo permette di settare le label indicanti "1 Parameter" e "2 Parameter"
     *@param param1 Valore del "1 Parameter".
     *@param param2 Valore del "2 Parameter".
     */
    public void setParams(int param1,int param2)
    {
        jL_type1ParamDesc.setText(""+param1);
        jL_type1ParamDesc.setToolTipText("Decimal Value");
        jB_type1Param.setText("D");
        jB_type1Param.setToolTipText("Convert to Hex Value");
        param1Value = param1;
        
        jL_type2ParamDesc.setText(""+param2);
        jL_type2ParamDesc.setToolTipText("Decimal Value");
        jB_type2Param.setText("D");
        jB_type2Param.setToolTipText("Convert to Hex Value");
        param2Value = param2;
    }
    
    /**
     *Il metodo permette di settare l'oggetto JTextField indicante il "Message" inerente al processo rappresentato.
     *@param strMessage testo del Messaggio. 
     */
    public void setMessage(String strMessage)
    {
        jL_message.setText(strMessage);
    }
    
    /**
     *Il metodo permette di settare il colore di background delll'oggetto JTextField indicante il "Message" inerente al processo rappresentato.
     *@param colorBG Colore da rappresentare. 
     */
    public void setColorBG_Message(java.awt.Color colorBG)
    {
        if(flagReverse==false)
        {
            jL_message.setBackground(colorBG);
        }
        else
        {
            jL_message.setForeground(colorBG);
        }
    }
    
    /**
     *setta il numero di restart del processo.
     *@param valore che identifica il numero di restart del processo.
     */
    public void setnumRestartTime(int numRestart)
    {
        this.Local_numRestartTime = numRestart;
    }
    
    private void jB_processActionPerformed(java.awt.event.ActionEvent evt) {

        rifParent.setID_PROCESS_SELECTED(this.IDProcesso);
        
        RUN_OPERATION = RUN_jB_process;
        start();     
    }
    
    private void jB_statusActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        
        RUN_OPERATION = RUN_jB_status;
        start();
    }
    
    private void jB_type1ParamActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:        
        String valueConverted   = "";
        String toolTipvalue     = "";
        String labelButton      = "";
        String toolTipButton    = "";
        
        Integer AppoInteger = new Integer(1);
        switch(CaseConversion1)
        {
            case 0: // decimale
                    CaseConversion1 = 1;
                    valueConverted   = ""+param1Value;
                    toolTipvalue     = "Decimal Value";
                    
                    labelButton      = "D";
                    toolTipButton    = "Convert to Hex Value";           
                    break;
            case 1: // Hex
                    CaseConversion1 = 2;
                    valueConverted = AppoInteger.toHexString(param1Value);
                    toolTipvalue     = "Hex Value";
                    
                    labelButton      = "H";
                    toolTipButton    = "Convert to Octal Value";           
                    break;
            case 2: // Octal
                    CaseConversion1 = 0;
                    valueConverted = AppoInteger.toOctalString(param1Value);
                    toolTipvalue     = "Octal Value";
                    
                    labelButton      = "O";
                    toolTipButton    = "Convert to Decimal Value";           
                    break;
        }        
        jL_type1ParamDesc.setText(valueConverted);
        jL_type1ParamDesc.setToolTipText(toolTipvalue);
        jB_type1Param.setText(labelButton);
        jB_type1Param.setToolTipText(toolTipButton);        
    }
    
    private void jB_type2ParamActionPerformed(java.awt.event.ActionEvent evt) {

        String valueConverted   = "";
        String toolTipvalue     = "";
        String labelButton      = "";
        String toolTipButton    = "";
        
        Integer AppoInteger = new Integer(1);
        switch(CaseConversion2)
        {
            case 0: // decimale
                    CaseConversion2 = 1;
                    valueConverted   = ""+param2Value;
                    toolTipvalue     = "Decimal Value";
                    
                    labelButton      = "H";
                    toolTipButton    = "Convert to Hex Value";           
                    break;
            case 1: // Hex
                    CaseConversion2 = 2;
                    valueConverted = AppoInteger.toHexString(param2Value);
                    toolTipvalue     = "Hex Value";
                    
                    labelButton      = "O";
                    toolTipButton    = "Convert to Octal Value";           
                    break;
            case 2: // Octal
                    CaseConversion2 = 0;
                    valueConverted = AppoInteger.toOctalString(param2Value);
                    toolTipvalue     = "Octal Value";
                    
                    labelButton      = "D";
                    toolTipButton    = "Convert to Decimal Value";           
                    break;
        }        
        jL_type2ParamDesc.setText(valueConverted);
        jL_type2ParamDesc.setToolTipText(toolTipvalue);
        jB_type2Param.setText(labelButton);
        jB_type2Param.setToolTipText(toolTipButton);   
    }
    
    /**
    * In questo metodo viene definito il codice del Thread.
    */
    public void run()
    {     
        setCursorGUI(Cur_wait);
        if(RUN_OPERATION == RUN_jB_process)
        {
            String str_nameProcess = name_process_completo;
            jB_process.setEnabled(false);

            int flagDP=SSM_GlobalParam.HELP;
            
            if(this.type==SSM_GlobalParam.ORBIX)
            {
                flagDP=SSM_GlobalParam.PS_ORBIX_HELP;
            }
            
            //System.out.println("(RUN_jB_process) getDeteilProcess (flag HELP=>"+SSM_GlobalParam.HELP+" switch =>"+local_switch+" Processo=>"+jB_process.getText().trim()+")" );  
            logProcess[] processLOG = SSM_GlobalParam.CORBAConn.getDeteilProcess(flagDP,local_switch,name_process_completo);

            local_jTextArea.setText("");
            
            if(processLOG != null)
            {            
                if(this.type==SSM_GlobalParam.ORBIX)
                {
                    for(int i=0; i<processLOG.length; i++)
                    {
                        if(i>4) break;
                        local_jTextArea.append( new String(processLOG[i].descr).trim() +"\n" );
                    }  
                     
                }else
                {
                    for(int i=0; i<processLOG.length; i++)
                    {
                        local_jTextArea.append( new String(processLOG[i].descr).trim() +"\n" );
                    }  
                    
                }
            }            

            local_jTextArea.moveCaretPosition(0); 

            jB_process.setEnabled(true);
        }
        else if (RUN_OPERATION == RUN_jB_status)
        {
            jB_status.setEnabled(false);
            String str_nameProcess = name_process_completo;
            
            int flagDP=SSM_GlobalParam.MACRO;
            
            if(this.type==SSM_GlobalParam.NO_ORBIX)
            {
                flagDP=SSM_GlobalParam.MACRO;
            }
            else if(this.type==SSM_GlobalParam.ORBIX)
            {
                flagDP=SSM_GlobalParam.PS_ORBIX;
            }
            
            System.out.println("(RUN_jB_status) getDeteilProcess (flag MACRO=>"+flagDP+" switch =>"+local_switch+" Processo=>"+jB_process.getText().trim()+")" );  
            logProcess[] processLOG = SSM_GlobalParam.CORBAConn.getDeteilProcess(flagDP,local_switch,str_nameProcess);
            if(processLOG != null)
            {
                if(JF_TArea != null)
                    JF_TArea.dispose();
                
                JF_TArea = new SSM_JF_textArea(local_switch,str_nameProcess,0);
                JF_TArea.setLocation(this.rifParent.getX()+120,this.rifParent.getY()+100);
                
                javax.swing.JTextArea textArea_Rif = JF_TArea.getJTextArea();
                for(int i=0; i<processLOG.length; i++)
                {
                   textArea_Rif.append( new String(processLOG[i].descr).trim() +"\n" );
                }
                textArea_Rif.select(0,1);
                
                JF_TArea.pack();
                //JF_TArea.show();
                JF_TArea.setVisible(true);
            }
            jB_status.setEnabled(true);
        }
    setCursorGUI(Cur_default);
    }
    
    public void reverseColor()
    {
        flagReverse=!flagReverse;
        Color cf=jL_type.getForeground();
        Color cb=jL_type.getBackground();
        
        jL_type.setForeground(cb);
        jL_type.setBackground(cf);
        
        cf=jB_process.getForeground();
        cb=jB_process.getBackground();
        
        jB_process.setForeground(cb);
        jB_process.setBackground(cf);
        
        cf=jB_status.getForeground();
        cb=jB_status.getBackground();
        
        jB_status.setForeground(cb);
        jB_status.setBackground(cf);
        
        cf=jB_type1Param.getForeground();
        cb=jB_type1Param.getBackground();
        
        jB_type1Param.setForeground(cb);
        jB_type1Param.setBackground(cf);
        
        cf=jL_type1ParamDesc.getForeground();
        cb=jL_type1ParamDesc.getBackground();
        
        jL_type1ParamDesc.setForeground(cb);
        jL_type1ParamDesc.setBackground(cf);
        
        cf=jB_type2Param.getForeground();
        cb=jB_type2Param.getBackground();
        
        jB_type2Param.setForeground(cb);
        jB_type2Param.setBackground(cf);
        
        cf=jL_type2ParamDesc.getForeground();
        cb=jL_type2ParamDesc.getBackground();
        
        jL_type2ParamDesc.setForeground(cb);
        jL_type2ParamDesc.setBackground(cf);
        
        cf=jL_message.getForeground();
        cb=jL_message.getBackground();
        
        jL_message.setForeground(cb);
        jL_message.setBackground(cf);

    }
}
