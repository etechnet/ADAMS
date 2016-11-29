import java.awt.*;


	
public class SimpleRunner implements Runnable
{
	int 				i;
	public javax.swing.JFrame  	local_jf;
	public javax.swing.JTabbedPane 	local_tab;

	public void addEvent(Component cPadre)
	{
		cPadre.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() 
				{
					public void mouseMoved(java.awt.event.MouseEvent evt) 
					{
						i=0;
					}
				});
		
		Container c=(Container)cPadre;  
		Component[] componentFigli=c.getComponents();  
		
		if(componentFigli!=null)
		{
			for(int j=0;j<componentFigli.length;j++)  
			{  
				addEvent(componentFigli[j]);	
			}  
		}			
	}

	public SimpleRunner(javax.swing.JFrame jf, javax.swing.JTabbedPane tab)
	{
		local_jf=jf;
		local_tab=tab;
    
		/*local_tab.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() 
		{
			public void mouseMoved(java.awt.event.MouseEvent evt) 
			{
				i=0;
			}
		});
		*/
		
		Component[] componentsGlobal=local_jf.getContentPane().getComponents();  
             
	        for(int k=0;k<componentsGlobal.length;k++)  
	        {
	        	addEvent(componentsGlobal[k]);
	        	
	         	/*components1[k].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() 
				{
					public void mouseMoved(java.awt.event.MouseEvent evt) 
					{
						i=0;
						System.out.println("Reset Session");
					}
				});
			Container c=(Container)components1[k];  
			Component[] components2=c.getComponents();  
			for(int j=0;j<components2.length;j++)  
			{  
				components2[j].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() 
				{
					public void mouseMoved(java.awt.event.MouseEvent evt) 
					{
						i=0;
						System.out.println("Reset Session");
					}
				});
			}  */
		}  


		/*local_jf.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() 
		{
			public void mouseMoved(java.awt.event.MouseEvent evt) 
			{
				i=0;
			}
		});*/
	}
	
	public void run()
	{
		i = 0;
		
		while (true)
		{
                    
                        if(staticLib.FLAG_ELABORATION == true)
                        {
                            i = 0;
                            //System.out.println("TIME-OUT sospeso: Elaborazione Report in corso... "+ i);
                        }
                        //else
                            //System.out.println("TIME-OUT Attivo " + i);
                    
			i++;
			
			try
			{
				Thread.sleep(1000*30); //30 sec
			}
			catch(Exception e)
			{}
					
			if ( i == staticLib.SESSION_TIME * 2 ) // 20 minuti
			{
				javax.swing.JOptionPane warning = new javax.swing.JOptionPane();
				warning.showMessageDialog(local_jf,"Attenzione: La sessione di N.T.M. è scaduta.\nN.T.M. verrà chiuso.\nChiudere il browser e rilanciare l'applicativo.","Error Message",javax.swing.JOptionPane.WARNING_MESSAGE);
				local_jf.dispose();
                                try
                                {
                                    MDM_JF_Frame ntmFrame = (MDM_JF_Frame)local_jf;
                                    ntmFrame.my_dispose();
                                }
                                catch (Exception e) {}
				break;
			}
		}
	}
	

	
} 