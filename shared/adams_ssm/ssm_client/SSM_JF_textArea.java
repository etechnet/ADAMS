/*
 * SSM_JF_textArea.java
 *
 * Created on 15 luglio 2004, 11.17
 */

/**
 *
 * @author  root
 */
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;

public class SSM_JF_textArea extends javax.swing.JFrame implements Runnable {

    /** Creates new form SSM_JF_textArea */
    public int type                         = 0;
    
    public SSM_JF_textArea(String Switch, String nameProcess,int type) 
    {
        this.type=type;
        initComponents();
        jB_close.setCursor(cursor_hand);
        setTitle("("+Switch+") MACRO Alarm Information "+nameProcess);
        jL_north.setText(""+Switch+" "+nameProcess);
    }
    
    public SSM_JF_textArea(int Single_ALL,String SWITCHSELECTED,String nameProcess,int day_selected,int type) 
    {
        this.type=type;
        initComponents();
        setTitle("("+SWITCHSELECTED+") MACRO Alarm Information "+nameProcess);
        jL_north.setText(""+SWITCHSELECTED+" "+nameProcess);
        jB_close.setCursor(cursor_hand);
        
        local_Single_ALL        = Single_ALL;
        local_SWITCHSELECTED    = SWITCHSELECTED;
        local_nameProc          = nameProcess;
        local_day_selected      = day_selected;
        
        useThread = true;
    }
    

    public javax.swing.JTextArea getJTextArea()
    {
        return jTextArea1;
    }
    
    
    public void start()
    {
        Thread_RUN = true;
        
        th = new Thread(this);
        th.start();
    }
    
    /**
     *Il metodo interrompe l'esecuzione del Thread.
     */
    private void stop()
    {        
        Thread_RUN = false; 
    }   
    
    
    public void run()
    {
        this.setCursor(cursor_wait);
        jTextArea1.setCursor(cursor_wait);
        
        jTextArea1.setText("Please Wait...");
        //System.out.println("Type:"+type);
        
        if(useThread == true)
        {
            logProcess[] processLOG = SSM_GlobalParam.CORBAConn.get_HistoryProcess(this.type,local_Single_ALL,false,local_SWITCHSELECTED,local_nameProc,local_day_selected);
            
            if(Thread_RUN == false)
            {
                this.setCursor(cursor_default);
                jTextArea1.setCursor(cursor_default);
                return;
            }
            
            if(processLOG != null)
            {
                jTextArea1.setText("");
                for(int i=0; i<processLOG.length; i++)
                {
                   jTextArea1.append( new String(processLOG[i].descr).trim() +"\n" );
                }
                jTextArea1.select(0,1);
            }
            else
            {
                jTextArea1.setText("Process "+local_nameProc+" LOG not found.");
            }
            
            if(this.isShowing() )
                this.toFront();
        }
        this.setCursor(cursor_default);
        jTextArea1.setCursor(cursor_default);
   }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   private void initComponents() {//GEN-BEGIN:initComponents
       jP_north = new javax.swing.JPanel();
       jL_north = new javax.swing.JLabel();
       jScrollPane1 = new javax.swing.JScrollPane();
       jTextArea1 = new javax.swing.JTextArea();
       jP_south = new javax.swing.JPanel();
       jB_close = new javax.swing.JButton();
       
       setBackground(new java.awt.Color(230, 230, 230));
       addWindowListener(new java.awt.event.WindowAdapter() {
           public void windowClosing(java.awt.event.WindowEvent evt) {
               exitForm(evt);
           }
       });
       
       jP_north.setBackground(new java.awt.Color(230, 230, 230));
       jP_north.setPreferredSize(new java.awt.Dimension(550, 30));
       jL_north.setFont(new java.awt.Font("Dialog", 1, 14));
       jL_north.setForeground(new java.awt.Color(9, 126, 165));
       jL_north.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       jL_north.setPreferredSize(new java.awt.Dimension(400, 20));
       jP_north.add(jL_north);
       
       getContentPane().add(jP_north, java.awt.BorderLayout.NORTH);
       
       jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
       jScrollPane1.setPreferredSize(new java.awt.Dimension(550, 250));
       jTextArea1.setEditable(false);
       jScrollPane1.setViewportView(jTextArea1);
       
       getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
       
       jP_south.setBackground(new java.awt.Color(230, 230, 230));
       jP_south.setPreferredSize(new java.awt.Dimension(550, 30));
       jB_close.setForeground(java.awt.Color.red);
       jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.gif")));
       jB_close.setBorderPainted(false);
       jB_close.setContentAreaFilled(false);
       jB_close.setFocusPainted(false);
       jB_close.setPreferredSize(new java.awt.Dimension(100, 22));
       jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.gif")));
       jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.gif")));
       jB_close.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jB_closeActionPerformed(evt);
           }
       });
       
       jP_south.add(jB_close);
       
       getContentPane().add(jP_south, java.awt.BorderLayout.SOUTH);
       
       pack();
   }//GEN-END:initComponents

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        stop();
        dispose();
    }//GEN-LAST:event_jB_closeActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        stop();
        dispose();
    }//GEN-LAST:event_exitForm

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jP_north;
    private javax.swing.JLabel jL_north;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel jP_south;
    private javax.swing.JButton jB_close;
    // End of variables declaration//GEN-END:variables
    private Thread th               = null;
    private boolean Thread_RUN      = false;
    private boolean useThread       = false;
    
    private int local_Single_ALL        = 1;
    private String local_SWITCHSELECTED = "";
    private String local_nameProc       = "";
    int local_day_selected              = 1;
    
    private java.awt.Cursor cursor_hand         = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Cursor cursor_wait         = new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor cursor_default      = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
}
