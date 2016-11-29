/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> PROCESS MONITOR client </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Raffaele Ficcadenti (E-TECH) Created on 13/05/2005 - <a href="mailto:raffaele.ficcadenti@e-tech.net">raffaele.ficcadenti@e-tech.net</a></p>
 *
 */
import java.awt.Color;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_JP_ProcessRow extends javax.swing.JPanel implements Runnable
{
    public int type                         = 0;
    /**
     * Costruttore della classe.
     * @param rowHead Se true viene rappresentata l'interfaccia di intestazione, altrimenti interfaccia per la visualizzazione
     * dei campi pronti a ricevere dati da visualizzare.
     * @param ID_Processo Indice identificativo del processo.   
     */
    public SSM_JP_ProcessRow(boolean rowHead,int ID_Processo,String SWITCH,SSM_JF_MonitoringOutput Parent,int type) 
    {
        this.IDProcesso         = ID_Processo;
        this.local_switch       = SWITCH;
        this.rifParent          = Parent;
        this.local_jTextArea    = Parent.get_JTextArea();
        this.type               = type;
        initComponents();

        jTF_message.setText("Testo di prova per il message. Testo di prova per il message. Testo di prova per il message. Testo di prova per il message. ");
        jTF_message.select(0,0);

        if(rowHead) // Solo label intestazione
        {
            // Gli oggetti vengono lasciati ma ridotti in altezza(0) per mantenere la linearitï¿½ in base alle colonne
            jL_type.setPreferredSize(new java.awt.Dimension(60, 0)); 
            jB_process.setPreferredSize(new java.awt.Dimension(100, 0));
            jB_status.setPreferredSize(new java.awt.Dimension(24, 0));
            jB_type1Param.setPreferredSize(new java.awt.Dimension(24, 0));
            jL_type1ParamDesc.setPreferredSize(new java.awt.Dimension(100, 0));
            jB_type2Param.setPreferredSize(new java.awt.Dimension(24, 0));
            jL_type2ParamDesc.setPreferredSize(new java.awt.Dimension(100, 0));
            jTF_message.setPreferredSize(new java.awt.Dimension(500, 0));            
        }
        else
        {
            this.remove(jL1);
            this.remove(jL2);
            this.remove(jL3);
            this.remove(jL4);
            jB_process.setCursor(cursor_hand);
            jB_status.setCursor(cursor_hand);
            jB_type1Param.setCursor(cursor_hand);
            jB_type2Param.setCursor(cursor_hand);
        }        
        //jTF_message.setDocument(new SSM_JTextFieldFilter(SSM_JTextFieldFilter.NONE,100));        
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
        jB_process.setText(strName);
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
        jTF_message.setText(strMessage);
    }
    
    /**
     *Il metodo permette di settare il colore di background delll'oggetto JTextField indicante il "Message" inerente al processo rappresentato.
     *@param colorBG Colore da rappresentare. 
     */
    public void setColorBG_Message(java.awt.Color colorBG)
    {
        if(flagReverse==false)
        {
            jTF_message.setBackground(colorBG);
        }
        else
        {
            jTF_message.setForeground(colorBG);
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
        
        cf=jTF_message.getForeground();
        cb=jTF_message.getBackground();
        
        jTF_message.setForeground(cb);
        jTF_message.setBackground(cf);
        
        setBackground(cf);
    }
    
    /**
     *Questo metodo accetta in ingresso un oggetto esterno di tipo javax.swing.JTextArea,
     *per avere la possibilitï¿½ di referenziarlo.
     *@param Oggetto da referenziare.
     */
    /*public void setExternal_JTextArea(javax.swing.JTextArea jta)
    {
        this.local_jTextArea = jta;
    }*/
        
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jL1 = new javax.swing.JLabel();
        jL2 = new javax.swing.JLabel();
        jL3 = new javax.swing.JLabel();
        jL4 = new javax.swing.JLabel();
        jL_type = new javax.swing.JLabel();
        jB_process = new javax.swing.JButton();
        jB_status = new javax.swing.JButton();
        jB_type1Param = new javax.swing.JButton();
        jL_type1ParamDesc = new javax.swing.JLabel();
        jB_type2Param = new javax.swing.JButton();
        jL_type2ParamDesc = new javax.swing.JLabel();
        jTF_message = new javax.swing.JTextField();

        setBackground(new java.awt.Color(230, 230, 230));
        setMinimumSize(new java.awt.Dimension(750, 20));
        setPreferredSize(new java.awt.Dimension(964, 35));
        setLayout(new java.awt.GridBagLayout());

        jL1.setBackground(new java.awt.Color(9, 126, 165));
        jL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run_proc.png"))); // NOI18N
        jL1.setText("Process");
        jL1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL1.setMaximumSize(new java.awt.Dimension(46, 20));
        jL1.setMinimumSize(new java.awt.Dimension(46, 20));
        jL1.setPreferredSize(new java.awt.Dimension(46, 20));
        jL1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        add(jL1, gridBagConstraints);

        jL2.setBackground(new java.awt.Color(9, 126, 165));
        jL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL2.setText("1 Parameter");
        jL2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL2.setMaximumSize(new java.awt.Dimension(69, 20));
        jL2.setMinimumSize(new java.awt.Dimension(69, 20));
        jL2.setPreferredSize(new java.awt.Dimension(69, 20));
        jL2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        add(jL2, gridBagConstraints);

        jL3.setBackground(new java.awt.Color(9, 126, 165));
        jL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3.setText("2 Parameter");
        jL3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL3.setMaximumSize(new java.awt.Dimension(69, 20));
        jL3.setMinimumSize(new java.awt.Dimension(69, 20));
        jL3.setPreferredSize(new java.awt.Dimension(69, 20));
        jL3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        add(jL3, gridBagConstraints);

        jL4.setBackground(new java.awt.Color(9, 126, 165));
        jL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/message.png"))); // NOI18N
        jL4.setText("Message");
        jL4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL4.setMaximumSize(new java.awt.Dimension(50, 20));
        jL4.setMinimumSize(new java.awt.Dimension(50, 20));
        jL4.setPreferredSize(new java.awt.Dimension(50, 20));
        jL4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        add(jL4, gridBagConstraints);

        jL_type.setBackground(new java.awt.Color(230, 230, 230));
        jL_type.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_type.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time_proc.png"))); // NOI18N
        jL_type.setText("type");
        jL_type.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_type.setOpaque(true);
        jL_type.setPreferredSize(new java.awt.Dimension(65, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jL_type, gridBagConstraints);

        jB_process.setBackground(new java.awt.Color(230, 230, 230));
        jB_process.setText("Process");
        jB_process.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_process.setFocusPainted(false);
        jB_process.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_process.setMaximumSize(new java.awt.Dimension(100, 24));
        jB_process.setMinimumSize(new java.awt.Dimension(100, 24));
        jB_process.setPreferredSize(new java.awt.Dimension(100, 24));
        jB_process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_processActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jB_process, gridBagConstraints);

        jB_status.setBackground(new java.awt.Color(230, 230, 230));
        jB_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run22.png"))); // NOI18N
        jB_status.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_status.setBorderPainted(false);
        jB_status.setFocusPainted(false);
        jB_status.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_status.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_status.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_status.setPreferredSize(new java.awt.Dimension(24, 24));
        jB_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jB_status, gridBagConstraints);

        jB_type1Param.setBackground(new java.awt.Color(230, 230, 230));
        jB_type1Param.setText("D");
        jB_type1Param.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type1Param.setFocusPainted(false);
        jB_type1Param.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_type1Param.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_type1Param.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_type1Param.setPreferredSize(new java.awt.Dimension(24, 24));
        jB_type1Param.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_type1ParamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jB_type1Param, gridBagConstraints);

        jL_type1ParamDesc.setBackground(new java.awt.Color(230, 230, 230));
        jL_type1ParamDesc.setText("1 Parameter");
        jL_type1ParamDesc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_type1ParamDesc.setMaximumSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc.setPreferredSize(new java.awt.Dimension(100, 24));
        jL_type1ParamDesc.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jL_type1ParamDesc, gridBagConstraints);

        jB_type2Param.setBackground(new java.awt.Color(230, 230, 230));
        jB_type2Param.setText("D");
        jB_type2Param.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jB_type2Param.setFocusPainted(false);
        jB_type2Param.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_type2Param.setMaximumSize(new java.awt.Dimension(24, 24));
        jB_type2Param.setMinimumSize(new java.awt.Dimension(24, 24));
        jB_type2Param.setPreferredSize(new java.awt.Dimension(24, 24));
        jB_type2Param.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_type2ParamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jB_type2Param, gridBagConstraints);

        jL_type2ParamDesc.setBackground(new java.awt.Color(230, 230, 230));
        jL_type2ParamDesc.setText("2 Parametr");
        jL_type2ParamDesc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jL_type2ParamDesc.setMaximumSize(new java.awt.Dimension(100, 24));
        jL_type2ParamDesc.setMinimumSize(new java.awt.Dimension(100, 24));
        jL_type2ParamDesc.setPreferredSize(new java.awt.Dimension(100, 24));
        jL_type2ParamDesc.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jL_type2ParamDesc, gridBagConstraints);

        jTF_message.setBackground(new java.awt.Color(230, 230, 230));
        jTF_message.setEditable(false);
        jTF_message.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTF_message.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTF_message.setMinimumSize(new java.awt.Dimension(400, 24));
        jTF_message.setPreferredSize(new java.awt.Dimension(500, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        add(jTF_message, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jB_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_statusActionPerformed
        // Add your handling code here:
        
        RUN_OPERATION = RUN_jB_status;
        start();
    }//GEN-LAST:event_jB_statusActionPerformed

    private void jB_processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_processActionPerformed

        rifParent.setID_PROCESS_SELECTED(this.IDProcesso);
        
        RUN_OPERATION = RUN_jB_process;
        start();     
    }//GEN-LAST:event_jB_processActionPerformed

    private void jB_type2ParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_type2ParamActionPerformed

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
    }//GEN-LAST:event_jB_type2ParamActionPerformed

    private void jB_type1ParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_type1ParamActionPerformed
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
    }//GEN-LAST:event_jB_type1ParamActionPerformed

    private void start()
    {        
        th = null;
        th = new Thread(this);
        th.start();
    }

    /**
    * In questo metodo viene definito il codice del Thread.
    */
    public void run()
    {     
        this.setCursor(cursor_wait);
        
        if(RUN_OPERATION == RUN_jB_process)
        {
            jB_process.setEnabled(false);

            //System.out.println("getDeteilProcess (flag HELP=>"+SSM_GlobalParam.HELP+" switch =>"+local_switch+" Processo=>"+jB_process.getText().trim()+")" );  
            logProcess[] processLOG = SSM_GlobalParam.CORBAConn.getDeteilProcess(SSM_GlobalParam.HELP,local_switch,jB_process.getText().trim());

            local_jTextArea.setText("");
            if(processLOG != null)
            {            
                for(int i=0; i<processLOG.length; i++)
                {
                   local_jTextArea.append( new String(processLOG[i].descr).trim() +"\n" );
                }
            }            

            local_jTextArea.moveCaretPosition(0); 

            jB_process.setEnabled(true);
        }
        else //if (RUN_OPERATION == RUN_jB_status)
        {
            jB_status.setEnabled(false);
            String str_nameProcess = jB_process.getText().trim();
            
            int flagDP=SSM_GlobalParam.MACRO;
            
            if(this.type==SSM_GlobalParam.NO_ORBIX)
            {
                flagDP=SSM_GlobalParam.MACRO;
            }
            else if(this.type==SSM_GlobalParam.ORBIX)
            {
                flagDP=SSM_GlobalParam.PS_ORBIX;
            }
            
            System.out.println("getDeteilProcess (flag MACRO=>"+flagDP+" switch =>"+local_switch+" Processo=>"+jB_process.getText().trim()+")" );  
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
        
        this.setCursor(cursor_default);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_process;
    private javax.swing.JButton jB_status;
    private javax.swing.JButton jB_type1Param;
    private javax.swing.JButton jB_type2Param;
    private javax.swing.JLabel jL1;
    private javax.swing.JLabel jL2;
    private javax.swing.JLabel jL3;
    private javax.swing.JLabel jL4;
    private javax.swing.JLabel jL_type;
    private javax.swing.JLabel jL_type1ParamDesc;
    private javax.swing.JLabel jL_type2ParamDesc;
    private javax.swing.JTextField jTF_message;
    // End of variables declaration//GEN-END:variables
    private java.awt.Cursor cursor_hand     = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Cursor cursor_wait     = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor cursor_default  = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    
    private int IDProcesso      = -2;
    private String local_switch = null;      
   
    
    private int param1Value     = 0;
    private int CaseConversion1 = 1;
    private int param2Value     = 0;
    private int CaseConversion2 = 1;
    
    private Thread th = null;
    private javax.swing.JTextArea local_jTextArea = null;
    private int Local_numRestartTime = 0;
    
    private int RUN_OPERATION           =-1;
    private final int RUN_jB_process    = 0;
    private final int RUN_jB_status     = 1;

    private SSM_JF_MonitoringOutput rifParent = null;
    private SSM_JF_textArea JF_TArea          = null;
    private boolean flagReverse     = false;
}