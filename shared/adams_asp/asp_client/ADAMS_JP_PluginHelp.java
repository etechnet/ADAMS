/*
 * ADAMS_JP_PluginHelp.java
 *
 * Created on 8 novembre 2005, 9.45
 */

/**
 *
 * @author  Raffo raffaele.ficcadenti@e-tech.net
 */
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.*; 
import javax.swing.event.*; 
import javax.swing.text.*; 
import javax.swing.text.html.*; 
import javax.swing.border.*; 
import javax.swing.colorchooser.*; 
import javax.swing.filechooser.*; 
import javax.accessibility.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.beans.*; 
import java.util.*; 
import java.io.*; 
import java.applet.*; 
import java.net.*; 

public class ADAMS_JP_PluginHelp extends javax.swing.JPanel {

    private boolean                                 DEBUG                       = false;
    private ADAMS_TAB_INFO_CONFIG                    local_INFO_CONFIG           = null;
    private Cursor                                  Cur_default                 = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor                                  Cur_wait                    = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor                                  Cur_hand                    = new Cursor(Cursor.HAND_CURSOR);
    private String                                  strURL                      = "";
    
    /** Creates new form ADAMS_JP_PluginHelp */
    public ADAMS_JP_PluginHelp(ADAMS_TAB_INFO_CONFIG local_INFO_CONFIG) {
        this.local_INFO_CONFIG=local_INFO_CONFIG;
        initComponents();
        
        setFont();
        setCursorWidget();
        
        strURL="http://t_rda.di.telecomitalia.it/ntm_luca/ADAMSConf_raffo/Plugin%20Help%20Informations.html";
            
        vista.setContentType("text/html");
        vista.setEditable(false);
        
        javax.swing.text.html.HTMLEditorKit he = new javax.swing.text.html.HTMLEditorKit();
        vista.setEditorKit(he);
        
        try{
          URL newURL = new URL(strURL);
          setURL(newURL);
        }
        catch (MalformedURLException mue){
          System.out.println("L' URL inserito non è corretto:"+mue);
          vista.setText("Il file di Help non è stato trovato.");
        }
        
        jL_title.setText(jL_title.getText()+"  ["+strURL+"]");
    }
    
    public void setFont()
    {
        jL_title.setFont(ADAMS_GlobalParam.font_B11);
    }
    
    public void setCursorWidget()
    {    
        //.setCursor(Cur_hand);
    }
    
    public void setURL(URL newURL)
    {
        try{
            vista.setPage(newURL);
        }catch (IOException ioe){
            System.out.println("Error :"+ioe);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jL_title = new javax.swing.JLabel();
        jS_p1 = new javax.swing.JScrollPane();
        vista = new javax.swing.JEditorPane();
        
        setLayout(new java.awt.BorderLayout());
        
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        jL_title.setText("Plugin Help");
        jL_title.setMinimumSize(new java.awt.Dimension(186, 40));
        jL_title.setPreferredSize(new java.awt.Dimension(186, 40));
        add(jL_title, java.awt.BorderLayout.NORTH);
        
        jS_p1.setViewportView(vista);
        
        add(jS_p1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jL_title;
    private javax.swing.JScrollPane jS_p1;
    private javax.swing.JEditorPane vista;
    // End of variables declaration//GEN-END:variables

}
