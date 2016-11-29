import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> PROCESS MONITOR client </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (HP Invent) Created on 01/07/2004 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 * Classe che crea un Hashtable utilizzata per memorizzare le varie icone che vengono utilizzate dall'applicativo.
 * In questo modo viene risparmiato spazio per le icone che vengono utilizzate più volte.   
 */
public class SSM_IconPool
{
	private String 		path;
	private Hashtable 	icons;
	
	/**
     	  * Costruttore della classe: istanzia una Hashtable ed imposta il path da dove prelevare le icone.
     	  * @param path percorso nel quale si trovano le icone,
  	  */
	public SSM_IconPool(String path)
	{
		setPath(path);
		icons=new Hashtable();
	}

	/**
     	  * Costruttore della classe: istanzia una Hashtable.
     	  */
	public SSM_IconPool()
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
