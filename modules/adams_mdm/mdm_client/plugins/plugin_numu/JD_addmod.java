import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.text.*;


public class JD_addmod extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	
	private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    	private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    	private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    	private JD_plugin_numu JD_pm = null;
    	private int type = 0;
    	private String oldValue = null;
    	private String strPass = "";
    	private JLabel jl_cc;
    	private JTextField tf_cc;
    	private JLabel jl_ac;
    	private JTextField tf_ac;
    	private JLabel jl_numu;
    	private JTextField tf_numu;
    	private JComboBox jcb_num_type;

	public void open_dialog()
    	{        
	        setCenteredFrame(380,110);
	        setVisible(true);
	        toFront();
    	}
    	
    	public String getPassword()
    	{        
	        return strPass;
    	}
    
    	private void setCenteredFrame(int width,int height)
    	{
	        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
	        java.awt.Dimension screenSize = kit.getScreenSize();        
	        int screenWCenter = screenSize.width/2;
	        int screenHCenter = screenSize.height/2;        
	        this.setSize(width,height);
	        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    	}
    
	/**
	 * Create the dialog.
	 */
	public JD_addmod(javax.swing.JDialog parent,int type) 
	{
		super(parent,true);
		JD_pm=(JD_plugin_numu)parent;
		this.type=type;
		if(type==0)
		{
			setTitle("Add new number");
		}else if(type==1)
		{
			setTitle("Modify number");
		}else if(type==2)
		{
			setTitle("Insert Password");
		}
			
		setBounds(100, 100, 303, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			jcb_num_type = new JComboBox();
			jcb_num_type.setToolTipText("Number Type (CTO,CTE,CTOCTE)");
			jcb_num_type.addItem("CTO");
			jcb_num_type.addItem("CTE");
			jcb_num_type.addItem("CTOCTE");
			jcb_num_type.setSelectedIndex(0);
			contentPanel.add(jcb_num_type);
		}
		{
			jl_cc = new JLabel("CC");
			contentPanel.add(jl_cc);
		}
		{
			tf_cc = new JTextField();
			tf_cc.setToolTipText("Country Code");
			contentPanel.add(tf_cc);
			tf_cc.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,3));
			tf_cc.setColumns(3);
		}
		{
			jl_ac = new JLabel("AC");
			contentPanel.add(jl_ac);
		}
		{
			tf_ac = new JTextField();
			tf_ac.setToolTipText("Area Code");
			contentPanel.add(tf_ac);
			tf_ac.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,4));
			tf_ac.setColumns(4);
		}
		{
			jl_numu = new JLabel("NumU");
			contentPanel.add(jl_numu);
		}
		{
			tf_numu = new JTextField();
			tf_numu.setToolTipText("Subscribe Number");
			contentPanel.add(tf_numu);
			tf_numu.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,10));
			tf_numu.setColumns(8);
		}
		{
			if(type==0) //ADD
			{
			}
			else if(type==1)
			{
				//System.out.println("JD_pm.getNumeroSelezionato()="+JD_pm.getNumeroSelezionato());
				elementoLista listanumeri=JD_pm.getListaNumeri();
				elementoListaBase numeroSel=listanumeri.elementAt(JD_pm.getNumeroSelezionato());
				
				jcb_num_type.setSelectedIndex(numeroSel.getNumType()-1);
				tf_cc.setText(numeroSel.getCC());
				tf_ac.setText(numeroSel.getAC());
				tf_numu.setText(numeroSel.getNUMU());
				
				oldValue=JD_pm.getNumeroSelezionato();

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton();
				okButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply.jpg")));
				okButton.setToolTipText("Apply");
				okButton.setBorderPainted(false);
				okButton.setContentAreaFilled(false);
				okButton.setFocusPainted(false);
				okButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
				okButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_press.jpg")));
				okButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apply_over.jpg")));
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						eventi_bottoni(arg0);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton();
				cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
				cancelButton.setToolTipText("Cancel");
				cancelButton.setBorderPainted(false);
				cancelButton.setContentAreaFilled(false);
				cancelButton.setFocusPainted(false);
				cancelButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
				cancelButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
				cancelButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						eventi_bottoni(arg0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		okButton.setCursor(Cur_hand);
		cancelButton.setCursor(Cur_hand);
	}
	
		public String customFormat(String pattern, double value ) {
		      DecimalFormat myFormatter = new DecimalFormat(pattern);
		      String output = myFormatter.format(value);
		      //System.out.println(value + "  " + pattern + "  " + output);
		      return output;
		      
		}

	
	private void eventi_bottoni(java.awt.event.MouseEvent evt)
	{
		if(evt.getSource()==okButton)
		{
			if(type==0)
			{
				if(tf_cc.getText().equals(""))
				{
					JD_Message op = new JD_Message(null,true,"Il campo CC non può essere nullo.Verrà forzato a 000.","Info",JD_Message.INFO);
					tf_cc.setText(customFormat("000",0));
					JD_pm.addNumero(new elementoListaBase(jcb_num_type.getSelectedIndex()+1,tf_cc.getText(),tf_ac.getText(),tf_numu.getText()));
					setVisible(false);
					dispose();
				}
				else
				{
					int aInt = Integer.parseInt(tf_cc.getText());
					//System.out.println("FORMATTATA:"+customFormat("000",aInt));
					tf_cc.setText(customFormat("000",aInt));
					JD_pm.addNumero(new elementoListaBase(jcb_num_type.getSelectedIndex()+1,tf_cc.getText(),tf_ac.getText(),tf_numu.getText()));
					setVisible(false);
					dispose();
				}
			}else if(type==1)
			{
				if(tf_cc.getText().equals(""))
				{
					JD_Message op = new JD_Message(null,true,"Il campo CC non può essere nullo.Verrà forzato a 000.","Info",JD_Message.INFO);
					tf_cc.setText(customFormat("000",0));	
				}
				
				JD_pm.sostituisci(oldValue,new elementoListaBase(jcb_num_type.getSelectedIndex()+1,tf_cc.getText(),tf_ac.getText(),tf_numu.getText()));
				setVisible(false);
				dispose();
				
			}
			
		}
		else if(evt.getSource()==cancelButton)
		{
			strPass="";
			setVisible(false);
			dispose();
		}
	}

}
