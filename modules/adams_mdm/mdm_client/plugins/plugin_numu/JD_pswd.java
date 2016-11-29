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

public class JD_pswd extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField pswdField;
	private JButton okButton;
	private JButton cancelButton;
	
	private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    	private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    	private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    	private JD_plugin_numu JD_pm = null;
    	private int type = 0;
    	private String oldValue = "";
    	private String strPass = "";

	public void open_dialog()
    	{        
	        setCenteredFrame(260,110);
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
	public JD_pswd(javax.swing.JDialog parent,int type) 
	{
		super(parent,true);
		JD_pm=(JD_plugin_numu)parent;
		this.type=type;
		if(type==2)
		{
			setTitle("Insert Password");
		}
			
		setBounds(100, 100, 252, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			if(type==2)
			{
				pswdField = new JPasswordField();
                                pswdField.setMinimumSize(new java.awt.Dimension(100, 20));
                                pswdField.setPreferredSize(new java.awt.Dimension(100, 20));                               
				pswdField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
						{
							strPass=new String(pswdField.getPassword()).trim();
							setVisible(false);
							dispose();
						}
					}
				});
				contentPanel.add(pswdField);
				pswdField.setColumns(10);
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
	
	private void eventi_bottoni(java.awt.event.MouseEvent evt)
	{
		if(evt.getSource()==okButton)
		{
			
			if(type==2)
			{
				strPass=new String(pswdField.getPassword()).trim();
			}
			setVisible(false);
			dispose();
		}else if(evt.getSource()==cancelButton)
		{
			strPass="";
			setVisible(false);
			dispose();
		}
	}

}
