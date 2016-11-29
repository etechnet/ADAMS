import java.awt.Cursor;
import java.util.Vector;
import java.sql.*;

public class ADAMS_JF_ViewServerAlarm_DB extends javax.swing.JFrame
{

    public ADAMS_JF_ViewServerAlarm_DB() 
    {     
        super();
        initComponents();       
        jB_close.setCursor(Cur_hand);
        
        this.setTitle("Handler Server");
        
        setCenteredFrame(frame_width,frame_height);

	this.setVisible(true);
        this.toFront();
        
        setHtmlText();
    }
        
    
    private void setHtmlText()
    {
        this.setCursor(Cur_wait);
        jEditor.setCursor(Cur_wait);
   
        StringBuffer strHtmlText = new StringBuffer();
        strHtmlText.append("<html>");
        strHtmlText.append("<body>");

        // **************************
        
        strHtmlText.append(setTableServer(read_AllAlarmServer("MASTER"),"MASTER","#FF9900"));
        strHtmlText.append("<p>&nbsp;</p>");
        strHtmlText.append(setTableServer(read_AllAlarmServer("PRODUCTION"),"PRODUCTION","#57B37C"));

        // **************************
        
        strHtmlText.append("</body>");
        strHtmlText.append("</html>");
        //System.out.println("strHtmlText -->"+strHtmlText);
        
        try
        {
            jEditor.setContentType("text/html");                
            jEditor.setText(strHtmlText.toString());
        }
        catch (Exception e1)
        {   
            //System.out.println("--------- CORREGGI PROBLEMA: javax.swing.text.html.parser.Parser.errorContext --------");

            jEditor.setEditorKit(new javax.swing.text.html.HTMLEditorKit() { protected Parser getParser(){
                try 
                {
                    Class c = Class.forName("javax.swing.text.html.parser.ParserDelegator");
                    Parser defaultParser = (Parser) c.newInstance();
                 return defaultParser;
                }
                catch (Throwable e) {}
                return null;}
             });

              jEditor.setText(jEditor.getText());
        }
        
        try
        {                
            jEditor.select(0,0);
            jEditor.requestFocus();
        }
        catch(Exception e){System.out.println("ex focus.");}

        jScroll.invalidate();
        jScroll.validate();
        
        this.setCursor(Cur_default);
        jEditor.setCursor(Cur_default);
    }
    
    
    private String setTableServer(Vector v_allServer, String Title,String color_Title)
    {
        StringBuffer strHtmlText = new StringBuffer("");
        
        String Color_white = "#FFFFFF"; //WHITE
        String Color_gray = "#E4E4E4"; //GRAY                
               
        String style_Header = "style=\"font-weight: bold; font-size: 10px; font-family: Arial, Helvetica, sans-serif; color: #000000;\"";
        String style_rows = "style=\"font-weight: bold; font-size: 8px; font-family: Arial, Helvetica, sans-serif; color: #000000;\""; 
        
        strHtmlText.append("<table width=\"100%\" border=\"1\" cellspacing=\"1\">");
        strHtmlText.append("<tr "+style_Header+">");
        strHtmlText.append("<th colspan=\"3\" nowrap=\"nowrap\" bgcolor=\""+color_Title+"\" scope=\"col\">"+Title+"</th>");
        strHtmlText.append("</tr>");
        strHtmlText.append("<tr "+style_Header+">");
        strHtmlText.append("<th width=\"20%\" nowrap=\"nowrap\" bgcolor=\"#CCCC00\" scope=\"col\">Configuration</th>");
        strHtmlText.append("<th width=\"10%\" nowrap=\"nowrap\" bgcolor=\"#CCCC99\" scope=\"col\">Server ID</th>");
        strHtmlText.append("<th width=\"70%\" nowrap=\"nowrap\" bgcolor=\"#CCCCFF\" scope=\"col\">Relations</th>");
        strHtmlText.append("</tr>");
          
        String same_nameConf = "";
        String color_bk_row = Color_white;
        for(int rx=0; rx<v_allServer.size(); rx++)
        {
            AlarmServerRow_view server_row = (AlarmServerRow_view)v_allServer.elementAt(rx);
            //server_row.printDebug();
            
          
            if(same_nameConf.compareTo(server_row.TIPO_DI_CONFIGURAZIONE)!= 0)
            {
                if(rx !=0)
                {
                    if(color_bk_row.compareTo(Color_white)==0 )
                        color_bk_row = Color_gray;
                    else
                        color_bk_row = Color_white;
                }
            }
                
            strHtmlText.append("<tr bgcolor=\""+color_bk_row+"\" "+style_rows+" > ");
            strHtmlText.append("<td scope=\"col\">"+server_row.TIPO_DI_CONFIGURAZIONE+"</td>");

            same_nameConf = server_row.TIPO_DI_CONFIGURAZIONE;                

            strHtmlText.append("<td align=\"center\" scope=\"col\">"+server_row.SERVER_ID+"</td>");

            for(int rxx=0; rxx<server_row.V_ID_Desc_ALARM_RELATION.size(); rxx++)
            {
                if(rxx == 0)
                    strHtmlText.append("<td scope=\"col\">"+((String)server_row.V_ID_Desc_ALARM_RELATION.elementAt(rxx))+"<br />");
                else
                    strHtmlText.append(((String)server_row.V_ID_Desc_ALARM_RELATION.elementAt(rxx))+"<br />");
            }
            strHtmlText.append("</td>");
            strHtmlText.append("</tr>");
        }          
        strHtmlText.append("</table>");
        return strHtmlText.toString();
    }
    
    public Vector read_AllAlarmServer(String MASTER_or_PRODUCTION)
    {
        this.setCursor(Cur_wait);
        Vector V_ALL_SERVER = new Vector();        
        //String typeConfig       = "N.T.M.-Voce-Standard";

        //Costruzione Stringa
        //String sel_AlarmServerRead = "select distinct SERVER_ID,DESCRIPTION";
        //sel_AlarmServerRead = sel_AlarmServerRead+ " from tab_alarm_server_master";        
        //sel_AlarmServerRead = sel_AlarmServerRead+ " where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' order by SERVER_ID";
        
        String TABLE_MASTER_or_PRODUCTION = "";
        if(MASTER_or_PRODUCTION.compareTo("MASTER") == 0)
            TABLE_MASTER_or_PRODUCTION = "tab_alarm_server_master";
        else if(MASTER_or_PRODUCTION.compareTo("PRODUCTION") == 0)
            TABLE_MASTER_or_PRODUCTION = "tab_alarm_server_production";
        else
        {
            System.out.println("ERRORE read_AllAlarmServer MASTER_or_PRODUCTION");
            return V_ALL_SERVER;
        }
        
        String sel_AlarmServerRead = "select TIPO_DI_CONFIGURAZIONE,SERVER_ID,ID_ALARM_RELATION,DESCRIPTION";
        sel_AlarmServerRead = sel_AlarmServerRead+ " from "+TABLE_MASTER_or_PRODUCTION+"";        
        sel_AlarmServerRead = sel_AlarmServerRead+ " order by  TIPO_DI_CONFIGURAZIONE,SERVER_ID,ID_ALARM_RELATION";
        
        
       //System.out.println("sel sel_AlarmServerRead ----> "+sel_AlarmServerRead);
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        java.sql.ResultSet rs_simple  = ADAMS_GlobalParam.connect_Oracle.Query_RS(sel_AlarmServerRead,SQLStatement);
        
        if(rs_simple != null)
        {
            try
            {
                while(rs_simple.next()) 
                {   
                    String typeConfig   = rs_simple.getString("TIPO_DI_CONFIGURAZIONE");
                    int serverID    = rs_simple.getInt("SERVER_ID");
                    int idREL       = rs_simple.getInt("ID_ALARM_RELATION");
                    
                    String desc     = rs_simple.getString("DESCRIPTION");                     
                    if(desc == null)
                        desc = "";
                                  
                    //String str_select_IdAlarm = "select distinct ID_ALARM_RELATION from tab_alarm_server_master where TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and SERVER_ID="+serverID+" order by ID_ALARM_RELATION";
                    //System.out.println("str_select_IdAlarm ----> "+str_select_IdAlarm);
                    //Vector v_ID_AlarmRelation = ADAMS_GlobalParam.connect_Oracle.Query(str_select_IdAlarm);
                    
                    //Vector v_ID_AlarmRelation = new Vector();
                    //Vector v_Id_Desc_AlarmRelation = new Vector();
                    //for(int a=0; a<v_ID_AlarmRelation.size(); a++)
                    //{                        
                        //int id_REL = new Integer((String)v_ID_AlarmRelation.elementAt(a)).intValue();
                        String str_relName = "";
                        if(idREL > 0)
                        {                       
                            String sel_REL_elemName =   "select a.TAG from tab_config a,TAB_ALARM_RELATION_ELEMENTS b where "+
                                                                    "a.TIPO_DI_CONFIGURAZIONE='"+typeConfig+"' and "+
                                                                    "a.TIPO_DI_CONFIGURAZIONE=b.TIPO_DI_CONFIGURAZIONE and "+
                                                                    "b.ID_ALARM_RELATION="+idREL+" and "+
                                                                    "a.POSIZIONE_ELEMENTO=b.ID_ELEMENT "+
                                                                    "order by b.POSITION_ELEMENT";

                           //System.out.println(sel_REL_elemName);                                    
                            Vector vRel_ElementName = ADAMS_GlobalParam.connect_Oracle.Query(sel_REL_elemName);

                            String symbol_rel = "::";
                            str_relName = "";
                            int num_TE = vRel_ElementName.size();
                            for(int t=0; t<num_TE; t++)
                            {
                                if(t == (num_TE-1))
                                    symbol_rel = "";

                                 str_relName = str_relName+((new String( ((String)vRel_ElementName.elementAt(t)))).trim())+symbol_rel;
                            }                            
                            //v_Id_Desc_AlarmRelation.addElement(new String("["+id_REL+"] "+str_relName));
                        }
                        else
                        {                           
                            //v_Id_Desc_AlarmRelation.addElement(new String("< none >"));
                            str_relName = "- none - ";
                        }
                   // }
                    
                    //AlarmServerRow_view AL_Row = new AlarmServerRow_view(typeConfig,serverID,desc,v_Id_AlarmRelation,v_Id_Desc_AlarmRelation);
                    
                    
                    //***************
                    AlarmServerRow_view AL_Row = null;
                    for (int v=0; v<V_ALL_SERVER.size(); v++)
                    {
                        AL_Row = (AlarmServerRow_view)V_ALL_SERVER.elementAt(v);
                        if((AL_Row.TIPO_DI_CONFIGURAZIONE.compareTo(typeConfig) == 0) && (AL_Row.SERVER_ID == serverID ))
                        {
                            AL_Row.addAlarmRelation(idREL, str_relName);
                            break;
                        }
                        else
                            AL_Row = null;
                            
                    }
                    
                    if(AL_Row == null)
                    {
                        AL_Row = new AlarmServerRow_view(typeConfig,serverID,desc);
                        AL_Row.addAlarmRelation(idREL, str_relName);
                        V_ALL_SERVER.addElement(AL_Row);
                    }
                    // ***************
                 }
            }
            catch (Exception ex) 
            {
                System.out.println("[A] err read_AllAlarmServer" + ex.toString());
                ex.printStackTrace();
            }
            
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("[B] err read_AllAlarmServer" +  exc.toString());
                exc.printStackTrace();
            }
        }else
        {
            System.out.println("rs==null");
        }
        
        return V_ALL_SERVER;        
    }
       
    private void initComponents() {//GEN-BEGIN:initComponents
        jScroll = new javax.swing.JScrollPane();
        jEditor = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jB_close = new javax.swing.JButton();
        jB_reload = new javax.swing.JButton();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setBackground(new java.awt.Color(230, 230, 230));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        jEditor.setEditable(false);
        jScroll.setViewportView(jEditor);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(4, 4, 0, 4);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 0.7;
        getContentPane().add(jScroll, gridBagConstraints1);
        
        jPanel1.setLayout(new java.awt.BorderLayout());
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
        jB_close.setBorderPainted(false);
        jB_close.setContentAreaFilled(false);
        jB_close.setFocusPainted(false);
        jB_close.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
        jB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
        jB_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_closeActionPerformed(evt);
            }
        });
        
        jPanel1.add(jB_close, java.awt.BorderLayout.EAST);
        
        jB_reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload.jpg")));
        jB_reload.setBorderPainted(false);
        jB_reload.setContentAreaFilled(false);
        jB_reload.setFocusPainted(false);
        jB_reload.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_reload.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_press.jpg")));
        jB_reload.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_over.jpg")));
        jB_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_reloadActionPerformed(evt);
            }
        });
        
        jPanel1.add(jB_reload, java.awt.BorderLayout.WEST);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints1.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jB_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_reloadActionPerformed
        setHtmlText();
    }//GEN-LAST:event_jB_reloadActionPerformed

    private void jB_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_closeActionPerformed
        close_frame();
    }//GEN-LAST:event_jB_closeActionPerformed

    private void close_frame()
    {
        //System.out.println("Dim Windows width="+this.getSize().width+" height="+this.getSize().height);
        setVisible(false);
    }
    public void show_frame()
    {
        if (this.getState() == java.awt.Frame.ICONIFIED)
            this.setState(java.awt.Frame.NORMAL);
        
        if( isVisible() == false )
        {
            setCenteredFrame(frame_width,frame_height);
            setHtmlText();
            try
            {                
                jEditor.select(0,0);
                jEditor.requestFocus();
            }
            catch(Exception e){System.out.println("ex focus.");}
        }
        jScroll.invalidate();
        jScroll.validate();
        setVisible(true);
        this.toFront();
    }
    
  
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        close_frame();
    }//GEN-LAST:event_exitForm

    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;
        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));
    }
   
    class AlarmServerRow_view
    {               
        String TIPO_DI_CONFIGURAZIONE;        
        int SERVER_ID;
        String DESCRIPTION;
        Vector V_ID_ALARM_RELATION;
        Vector V_ID_Desc_ALARM_RELATION;
        
        /*AlarmServerRow_view()
        {
            TIPO_DI_CONFIGURAZIONE  = "";
            SERVER_ID               = -1;  
            DESCRIPTION             = "";
            V_ID_ALARM_RELATION     = new Vector();
            V_ID_Desc_ALARM_RELATION    = new Vector();            
        }*/
        
        /*AlarmServerRow_view(String ConfName, int Server_ID, String Desc,Vector V_ID_AR,Vector V_ID_Desc_AR)
        {
            TIPO_DI_CONFIGURAZIONE = new String(ConfName);
            SERVER_ID           = Server_ID;  
            DESCRIPTION         = new String(Desc);
            Vector V_ID_ALARM_RELATION  = V_ID_AR;
            V_ID_Desc_ALARM_RELATION    = V_Desc_AR;
        }*/
        
        AlarmServerRow_view(String ConfName, int Server_ID, String Desc)
        {
            TIPO_DI_CONFIGURAZIONE = new String(ConfName);
            SERVER_ID = Server_ID;  
            DESCRIPTION = new String(Desc);
            V_ID_ALARM_RELATION         = new Vector();
            V_ID_Desc_ALARM_RELATION    = new Vector();
        }
        
        void addAlarmRelation(int ID_REL, String REL_DESC)
        {
            int size = V_ID_ALARM_RELATION.size();
            boolean find = false;
            for(int i=0; i<size; i++)
            {
                int id_appo =  ((Integer)V_ID_ALARM_RELATION.elementAt(i)).intValue();
                if(id_appo == ID_REL)
                {
                    find = true;
                    break;
                }
            }    
            if(find == false)
            {
                V_ID_ALARM_RELATION.addElement(new Integer(ID_REL));
                V_ID_Desc_ALARM_RELATION.addElement(new String("["+ID_REL+"] "+REL_DESC));
            }
        }
        
        void printDebug()
        {
            System.out.println();
            System.out.println("TIPO_DI_CONFIGURAZIONE ==> "+TIPO_DI_CONFIGURAZIONE);
            System.out.println("SERVER_ID ==> "+SERVER_ID);
            System.out.println("DESCRIPTION ==> "+DESCRIPTION);

            for(int i=0; i<V_ID_Desc_ALARM_RELATION.size(); i++)
            {
                System.out.println("ID ALARM RELATION ==> "+((Integer)V_ID_ALARM_RELATION.elementAt(i)).intValue());
                System.out.println("ID-TAG ALARM RELATION ==> "+(String)V_ID_Desc_ALARM_RELATION.elementAt(i));
            }
            System.out.println();
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScroll;
    private javax.swing.JEditorPane jEditor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jB_close;
    private javax.swing.JButton jB_reload;
    // End of variables declaration//GEN-END:variables
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private int frame_width = 1000;
    private int frame_height = 800;

}

