import java.util.Hashtable;
import javax.swing.*;
import java.io.*;
import java.awt.Image;
import java.awt.*;
/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: ImagePool </b> </p>
 *
 * Classe che crea un Hashtable utilizzata per memorizzare le varie immagini che vengono utilizzate dall'applicativo.
 * In questo modo viene risparmiato spazio per leimmagini che vengono utilizzate piÃ¹ volte.
 *
 *<p align="center">&nbsp;</p>     
 */
public class ImagePool
{
	private String 		path;
	private Hashtable 	icons;
        private JApplet local_applet;
	
	/**
	  * Costruttore della classe: istanzia una Hashtable ed imposta il path da dove prelevare le immagini.
     	  * @param path percorso nel quale si trovano le immagini,
	  * @param applet riferimento all'applet di partenza dell'applicativo.	
     	  */
	public ImagePool(String path,JApplet applet)
	{
		setPath(path);
		icons=new Hashtable();
                this.local_applet =applet;
	}

	/**
	  * Costruttore della classe: istanzia una Hashtable ed imposta il path da dove prelevare le immagini.
	  * @param applet riferimento all'applet di partenza dell'applicativo.
     	  */
	public ImagePool(JApplet applet)
	{
		this("",applet);
        }
	
	/**
	  * Imposta il path da dove prelevare le immagini.
     	  * @param str percorso nel quale si trovano le immagini. 	
     	  */
	public void set_Path(String str)
        {
            setPath(str);
        }
        
	/**
	  * Preleva dall'Hashtable l'immagine specificata. 
     	  * @param iconName nome dell'immagine da prelevare dall'Hashtable.	
     	  */
        public Image getIcon(String iconName)
	{
		
            if(iconName.compareTo("")==0)
                return null;
            
            Image icon=(Image)icons.get(iconName);
            if (icon==null)
            {
                loadIcon(iconName);
                icon=(Image)icons.get(iconName);
            }
            
            try
            {
            	MediaTracker MT = new MediaTracker(local_applet);
		MT.addImage(icon,0);
		MT.waitForID(0);
               
            }	
            catch(InterruptedException e)
            {
            	e.printStackTrace();
            }
            
            
            return icon;
	}
        
        
	
	private void loadIcon(String icon)
	{
		loadIcon(path,icon);
	}
	
	private void loadIcon(String path, String icon)
	{
		try
		{
                    
			icons.put(icon, ext_image(path+icon));
		}catch(Exception ex)
		{
			System.err.println("ImagePool.getIcon(): icona non trovata "+path+icon);	
		}
	}
	
	private  Image ext_image (String str_image) //str_image :il nome del file con l'estenzione (ex: nome.gif)
  	{
    		//System.out.println("ext_image: "+str_image);
		Image image = null;
    		InputStream img;
    		ByteArrayOutputStream baos;
  
     		img = this.getClass().getResourceAsStream(str_image); 
		if (img == null)
	  		System.out.println("no resource found");
		baos = new ByteArrayOutputStream();
		try
		{
			int c;
			while ((c = img.read()) >=0)
				baos.write(c);
			image =local_applet.getToolkit().createImage(baos.toByteArray());
		}
		catch (IOException e)
		{
			e.printStackTrace();
                        return null;
		} 
     		return(image);
  	}
	
	private void setPath(String path)
	{
		this.path=path;
	}
	
	/**
	  * Ritorna il path impostato.
     	  * @return path da dove vengono prelevate le immagini.
     	  */
	public String getPath()
	{
		return path;
	}
}
