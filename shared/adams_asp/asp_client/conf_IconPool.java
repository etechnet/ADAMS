import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * <p align="center"> <b>  SPARKLE - Gruppo Telecom Italia - </b> </p>
 * <p align="center"> <b>  Documentazione Software Modulo : STS CONFIGURATOR </b> (2007) </p>
 * <p align="center"> <font face="Arial,Helvetica" size=2> Author: Beltrame Luca (E-TECH) - <a href="mailto:luca.beltrame@e-tech.net">luca.beltrame@e-tech.net - </a> </font></p>
 * 
 * <p align="center"> <b> Class: conf_IconPool </b> </p>
 *
 * Classe che crea un Hashtable utilizzata per memorizzare le varie icone che vengono utilizzate dall'applicativo.
 * In questo modo viene risparmiato spazio per le icone che vengono utilizzate piÃ¹ volte.
 *
 */
public class conf_IconPool
{
	private String 		path;
	private Hashtable 	icons;
	
	/**
	  * Costruttore della classe: istanzia una Hashtable ed imposta il path da dove prelevare le icone.
     	  * @param path percorso nel quale si trovano le icone,
     	  */
	public conf_IconPool(String path)
	{
		setPath(path);
		icons=new Hashtable();
	}

	/**
	  * Costruttore della classe: istanzia una Hashtable.
     	  */
	public conf_IconPool()
	{
		this("");
	}
	
	/**
	  * Preleva dall'Hashtable l'icona specificata. 
     	  * @param iconName nome dell'icona da prelevare dall'Hashtable.	
     	  */
	public Icon getIcon(String iconName)
	{
		Icon icon=(Icon)icons.get(iconName);
		if (icon==null)
		{
			loadIcon(iconName);
			icon=(Icon)icons.get(iconName);
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
			icons.put(icon, new ImageIcon(getClass().getResource(path+icon)));
		}catch(Exception ex)
		{
			System.err.println("IconPool.getIcon(): icona non trovata "+path+icon);	
		}
	}
	
	
	/**
	  * Imposta il path da dove prelevare le icone.
     	  * @param str percorso nel quale si trovano le immagini,	
     	  */
	public void setPath(String path)
	{
		this.path=path;
	}
	/**
	  * Ritorna il path impostato.
     	  * @return path da dove vengono prelevate le icone.
     	  */
	public String getPath()
	{
		return path;
	}
}
