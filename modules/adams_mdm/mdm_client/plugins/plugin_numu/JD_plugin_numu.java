import java.awt.BorderLayout;
import java.awt.Cursor;
import java.util.Vector;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;



public class JD_plugin_numu extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JTextField JT_search;
	private JDialog jDialogPadre=this;
	private JButton JB_exit;
	private JButton JB_del;
	private JButton JB_mod;
	private JButton JB_add;
	private JButton JB_apply;
	private JButton JB_clear;
	private JButton JB_reload;
	private JList JL_listanumulist;
	
	private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    	private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    	private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    	//private Vector<String> elementHelp = null;
    	//private Vector<String> elementHelp_originale = null;
    	
    	private elementoLista elementList = null;
    	private elementoLista elementList_originale = null;
    	
    	private plugin_numu pluginPadre = null;
    	private BufferRestrizioni br = null;
    	private DATA_DATAELEMENT trafficElements = null;
    
    	public int flag_modifiche = 0;
    	public int flag_commit = 0; 
    	private JMenuBar menuBar;
    	private JMenu mnPluginNumu;
    	private JMenuItem mntmInfo;
    
    
    public void open_dialog()
    {        
    	JL_listanumulist.setListData(elementList.getNumeriSort());
	JL_listanumulist.setEnabled(true);
	salvaOriginale();
        setCenteredFrame(450,360);
        setVisible(true);
        toFront();
    }
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),120+screenHCenter-(height/2));  
    }
    
    public elementoLista getElementi()
    {
    	return elementList;
    }
    
    public int isModify()
    {
    	return flag_modifiche;
    }
    
    public int isCommit()
    {
    	return flag_commit;
    }
    
    
    public void setElementi(elementoLista el)
    {
    	 elementList=el;
    }
    
    
   public void sostituisci(String vecchio, elementoListaBase nuovo)
    {
    	
    	//elementList.stampaNumeri();
    	//System.out.println("Sostituisci("+vecchio+","+nuovo.getNumero()+")");
    	
    	elementList.elimina(vecchio);
    	elementList.addElement(nuovo);
    	
    	JL_listanumulist.clearSelection();
        JL_listanumulist.setListData(elementList.getNumeriSort());
        flag_modifiche=1;
        
        //elementList.stampaNumeri();
    }
    
    public void addNumero(elementoListaBase nuovo)
	{
		flag_modifiche=1;

		//System.out.println(nuovo.getNumero()+" IndexOf="+elementList.indexOf(nuovo));
		if(elementList.indexOf(nuovo)==-1)
		{
			elementList.addElement(nuovo);
			JL_listanumulist.clearSelection();
			JL_listanumulist.setListData(elementList.getNumeriSort());
		}else
		{
			new JD_Message(null,true,"Il numero è già presente. ","Info",JD_Message.INFO);
		}
	}
    
    public String getNumeroSelezionato()
    {
    	return (String)JL_listanumulist.getSelectedValue();
    }
    
    public elementoLista getListaNumeri()
    {
    	return (elementoLista)elementList;
    }
    
    public elementoLista getListaNumeriOriginali()
    {
    	return (elementoLista)elementList_originale;
    }
    
    private void salvaOriginale()
    {
    	elementList_originale = new elementoLista();
    	for(int i=0;i<elementList.size();i++)
    	{
    		elementList_originale.addElement(elementList.elementAt(i));
    	}	
    }
    
    private void ripristinaOriginale()
    {
    	elementList.removeAllElements();
    	for(int i=0;i<elementList_originale.size();i++)
    	{
    		elementList.addElement(elementList_originale.elementAt(i));
    	}
    	JL_listanumulist.clearSelection();
        JL_listanumulist.setListData(elementList.getNumeriSort());
        JL_listanumulist.setEnabled(true);
    	System.out.println("RELOAD");
    }
    
    
	public JD_plugin_numu(java.awt.Frame parent,plugin_numu plugin,BufferRestrizioni BR,DATA_DATAELEMENT trafficElements) {
		super(parent, true);
		pluginPadre=plugin;
		this.br=BR;
		this.trafficElements=trafficElements;
		setTitle("Gestione Numerazioni Utente");
		setBounds(100, 100, 443, 355);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPluginNumu = new JMenu("Plugin NUMU (Subscriber number plugin)");
		menuBar.add(mnPluginNumu);
		
		mntmInfo = new JMenuItem("Info plugin");
		mntmInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					JD_plugin_numu_info dialog = new JD_plugin_numu_info(jDialogPadre,pluginPadre);
					dialog.open_dialog();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnPluginNumu.add(mntmInfo);
		flag_modifiche=0;
		JLabel lblListaNumerazioniUtentelabel = new JLabel("Subscriber number list");
		lblListaNumerazioniUtentelabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblListaNumerazioniUtentelabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JL_listanumulist = new JList();
		scrollPane.setViewportView(JL_listanumulist);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel JL_search = new JLabel("Search:");
		panel_1.add(JL_search);

		JT_search = new JTextField();
		JT_search.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,20));
		JT_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				jT_searchKeyReleased(arg0);
			}
		});
		JT_search.setColumns(15);
		panel_1.add(JT_search);
		
		JB_clear = new JButton("");
		JB_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_refresh.jpg")));
		JB_clear.setToolTipText("Refresh list");
		JB_clear.setBorderPainted(false);
		JB_clear.setContentAreaFilled(false);
		JB_clear.setFocusPainted(false);
		JB_clear.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_clear.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_refresh_press.jpg")));
		JB_clear.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_refresh_over.jpg")));
		JB_clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		panel_1.add(JB_clear);
				
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));
		panel.add(panel_2, BorderLayout.SOUTH);
				
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		
		JB_add = new JButton();
		JB_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add.jpg")));
		JB_add.setToolTipText("Add number");
		JB_add.setBorderPainted(false);
		JB_add.setContentAreaFilled(false);
		JB_add.setFocusPainted(false);
		JB_add.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_press.jpg")));
		JB_add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_add_over.jpg")));
		JB_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		panel_3.add(JB_add);
					
		JB_mod = new JButton();
		JB_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify.jpg")));
		JB_mod.setToolTipText("Modify selected number");
		JB_mod.setBorderPainted(false);
		JB_mod.setContentAreaFilled(false);
		JB_mod.setFocusPainted(false);
		JB_mod.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_mod.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_press.jpg")));
		JB_mod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_modify_over.jpg")));
		JB_mod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		panel_3.add(JB_mod);
				
		JB_del = new JButton();
		JB_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete.jpg")));
		JB_del.setToolTipText("Delete selected numbers");
		JB_del.setBorderPainted(false);
		JB_del.setContentAreaFilled(false);
		JB_del.setFocusPainted(false);
		JB_del.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_del.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_press.jpg")));
		JB_del.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_delete_over.jpg")));
		JB_del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		panel_3.add(JB_del);
					
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.EAST);
						
		JB_apply = new JButton();
		JB_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit.jpg")));
		JB_apply.setToolTipText("Apply");
		JB_apply.setBorderPainted(false);
		JB_apply.setContentAreaFilled(false);
		JB_apply.setFocusPainted(false);
		JB_apply.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_apply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_press.jpg")));
		JB_apply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_commit_over.jpg")));
		JB_apply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		
		JB_reload = new JButton();
		JB_reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload.jpg")));
		JB_reload.setToolTipText("Refresh number list");
		JB_reload.setBorderPainted(false);
		JB_reload.setContentAreaFilled(false);
		JB_reload.setFocusPainted(false);
		JB_reload.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_reload.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_press.jpg")));
		JB_reload.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_over.jpg")));
		JB_reload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		panel_4.add(JB_reload);
		panel_4.add(JB_apply);
						
		JB_exit = new JButton();
		JB_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
		JB_exit.setToolTipText("Exit");
		JB_exit.setBorderPainted(false);
		JB_exit.setContentAreaFilled(false);
		JB_exit.setFocusPainted(false);
		JB_exit.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_exit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
		JB_exit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
		JB_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				eventi_bottoni(arg0);
			}
		});
		panel_4.add(JB_exit);
		
		JB_clear.setCursor(Cur_hand);
		JB_add.setCursor(Cur_hand);
		JB_mod.setCursor(Cur_hand);
		JB_del.setCursor(Cur_hand);
		JB_apply.setCursor(Cur_hand);
		JB_exit.setCursor(Cur_hand); 
		JB_apply.setCursor(Cur_hand);
		JB_reload.setCursor(Cur_hand);
	 	mnPluginNumu.setCursor(Cur_hand);
	 	mntmInfo.setCursor(Cur_hand);

	}
	
	private void eventi_bottoni(java.awt.event.MouseEvent evt)
	{
		if(evt.getSource()==JB_add)
		{
			try {
				JD_addmod dialogAddMod = new JD_addmod(jDialogPadre,0);
				dialogAddMod.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogAddMod.open_dialog();
				jT_searchKeyReleased(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(evt.getSource()==JB_mod)
		{
			Object[] indiciElementiSelezionati=JL_listanumulist.getSelectedValues();

			if(indiciElementiSelezionati.length==1)
			{
				try {
					JD_addmod dialog = new JD_addmod(jDialogPadre,1);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.open_dialog();
					jT_searchKeyReleased(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(indiciElementiSelezionati.length==0)
			{
				JD_Message op = new JD_Message(null,true,"Select at least one element.","Info",JD_Message.INFO);
			}
			else if(indiciElementiSelezionati.length>1)
			{
				JD_Message op = new JD_Message(null,true,"Select only one element","Info",JD_Message.INFO);
			}
		}else if(evt.getSource()==JB_del)
		{
			Object[] indiciElementiSelezionati=JL_listanumulist.getSelectedValues();

			if(indiciElementiSelezionati.length>0)
			{
				JD_Message op = new JD_Message(null,true,"Confirm delete? ","Info",JD_Message.QUESTION_YES_NO);
				int Yes_No = op.getAnswer();
				if(Yes_No == JD_Message.YES) {
					/* elimina selezionati */
					for(int i=0;i<indiciElementiSelezionati.length;i++)
					{
						elementList.elimina((String)indiciElementiSelezionati[i]);
					}
					
					
					JL_listanumulist.clearSelection();
				        JL_listanumulist.setListData(elementList.getNumeriSort());
				        flag_modifiche=1;
				        jT_searchKeyReleased(null);
				}else
				{
					/* non fare nulla */
				}
			}else
			{
				JD_Message op = new JD_Message(null,true,"Select at least one element.","Info",JD_Message.INFO);
			}
		}else if(evt.getSource()==JB_clear)
		{
			JT_search.setText("");
			jT_searchKeyReleased(null);
		}else if(evt.getSource()==JB_apply)
		{
			if(flag_modifiche==1)
			{
				JD_Message op = new JD_Message(null,true,"Confirm modify? ","Info",JD_Message.QUESTION_YES_NO);
				int Yes_No = op.getAnswer();
				if(Yes_No == JD_Message.YES) {
					flag_modifiche = 0;
					flag_commit = 1;
					salvaOriginale();
					/* fai qualcosa */
				}else
				{
					/* non eseguo commit */
					flag_commit = 0;
				}
			}else
			{
				JD_Message op = new JD_Message(null,true,"No modify.","Info",JD_Message.INFO);
			}
		}else if(evt.getSource()==JB_reload)
		{
			ripristinaOriginale();
		}else if(evt.getSource()==JB_exit)
		{
			if(flag_modifiche==1)
			{
				JD_Message op = new JD_Message(null,true,"Modify is present. Exit without commit? ","Info",JD_Message.QUESTION_YES_NO);
				int Yes_No = op.getAnswer();
				if(Yes_No == JD_Message.YES) 
				{
					/* esco e non eseguo commit */
					flag_commit = 1;
					setVisible(false);
					dispose();
				}else
				{
					/* non uscire*/
				}
			}else
			{
				flag_commit = 1;
				setVisible(false);
				dispose();
			}
		}
	}
	
    	
    	private void jT_searchKeyReleased(java.awt.event.KeyEvent evt) 
    	{
		elementoLista V_appo_searc = new elementoLista();
	        String str_Press = JT_search.getText().toUpperCase();
	        String appo;
	        boolean flag=false;
	        JL_listanumulist.setEnabled(true);
	        for (int i=0; i<elementList.size(); i++)
	        {   
	            appo=(String)elementList.elementAt(i).getNumero();
	            if ( appo.toUpperCase().indexOf(str_Press) >= 0 )
	            {
	                V_appo_searc.addElement(elementList.elementAt(i));
	                flag=true;
	            }
	        }
	        if(!flag)
	        {
	            V_appo_searc.addElement(elementoListaBase.TYPE_UNDEFINED,"NOT FOUND");
	            JL_listanumulist.setEnabled(false);
	        }
	        JL_listanumulist.clearSelection();
	        JL_listanumulist.setListData(V_appo_searc.getNumeriSort());  
    	}
}
