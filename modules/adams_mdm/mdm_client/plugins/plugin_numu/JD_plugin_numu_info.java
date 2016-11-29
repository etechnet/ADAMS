import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.Cursor;


public class JD_plugin_numu_info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton JB_close;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JEditorPane editorPane_1 = new JEditorPane();
	private plugin_numu pluginPadre = null;
	
	private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    	private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    	private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);

	public void open_dialog()
	    {        
	        setCenteredFrame(400,390);
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
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    }
    
	public JD_plugin_numu_info(javax.swing.JDialog parent,plugin_numu plugin) {
		super(parent,true);
		this.pluginPadre=plugin;
		//pluginPadre.info(plugin_info.NTM_TYPE_TXT);
		setTitle("Plugin NUMU - Info");
		setBounds(100, 100, 400, 389);
		
	        editorPane_1.setEditable(false);
		editorPane_1.setContentType("text/html");                
		editorPane_1.setText(pluginPadre.info(plugin_info.NTM_TYPE_HTML).toString());
        
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			contentPanel.add(scrollPane, BorderLayout.CENTER);
		}
		{
			editorPane_1.setBackground(new Color(255, 255, 204));
			scrollPane.setViewportView(editorPane_1);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JB_close = new JButton();
				JB_close.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				JB_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.jpg")));
				JB_close.setToolTipText("Close info.");
				JB_close.setBorderPainted(false);
				JB_close.setContentAreaFilled(false);
				JB_close.setFocusPainted(false);
				JB_close.setMargin(new java.awt.Insets(0, 0, 0, 0));
				JB_close.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_press.jpg")));
				JB_close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_over.jpg")));
				buttonPane.add(JB_close);
			}
		}
		
		JB_close.setCursor(Cur_hand);
		
	}

}
