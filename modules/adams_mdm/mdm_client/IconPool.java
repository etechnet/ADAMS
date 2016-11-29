import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *
 * Questa classe è una semplice classe di utilità, creata ed utilizzata 
 * per il semplice scopo di ottimizzare la gestione delle immagini, 
 * oggetti che nell'interfaccia di NTm sono molto frequenti. La classe IconPool
 * crea un Hastable nella quale mette le immagini, che utilizza il client,
 * in questo caso, anche se un pulsante viene usato piu di una volta all'interno
 * dell'interfaccia, esso verrà caricato una sola volta nell'Hastable.
 *
 *<p align="center">&nbsp;</p>    
 */
public class IconPool
{
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Variabile Stringa indicante la directory in cuoi andare a prelevare 
         * le immagini per la costruzione dell'interfaccia grafica.
         * </font></font></p></pre>  
         */
        private String          path;
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Hastable in cui vengono memorizzate le immagini.
         * </font></font></p></pre>  
         * @see #IconPool()
         * @see #getIcon(String)
         */
        private Hashtable       icons;
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Setta la directory da cui caricare le immagini alla directory passata 
         * come parametro nella variabile <b>path</b>, e inizializza <b>icons</b> ad una Hastable.
         * </font></font></p></pre>  
         * @param path directory in cui sono presenti le immagini.
         * @see #path
         * @see #icons
         */
        public IconPool(String path)
        {
                setPath(path);
                icons=new Hashtable();
        }

        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Costruttore di default. Non fa altro che settare la directory in cui prelevare
         * le immagini alla directory corrente.
         * </font></font></p></pre>  
         * @see #path
         * @see #icons
         */
        public IconPool()
        {
                this("");
        }
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Restituisce l'immagine <iconName> nella forma di Icon, questo metodo va
         * a cercare la sequente immagine: <b>path</b>/iconName, se l'immagine non è presente
         * nella Hastable il metodo provvede a caricarla nella stesse e restituirla.
         * Se l'immagine è presente nella Hastable, viene restituita immediatamente.
         * </font></font></p></pre>  
         * @param iconName nome dell'immagine da caricare.
         * @see #path
         * @see #icons
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
                //StringBuffer fileName=new StringBuffer();
                //File iconFile;
                //fileName.append(path);
                //fileName.append(File.separator);
                //fileName.append(icon);
                //iconFile=new File(fileName.toString());
                //if(iconFile.exists())
                try
                {
                        icons.put(icon, new ImageIcon(getClass().getResource(path+icon)));
                }catch(Exception ex)
                {
                        System.err.println("IconPool.getIcon(): icona non trovata "+path+icon); 
                }
        }
        
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Questo metodo, setta la directory impostata da dove vengono prelecate le immagini.
         * le immagini alla directory corrente.
         * </font></font></p></pre> 
         * @param path directory in cui sono presenti le immagini.
         * @see #path		
         */
        public void setPath(String path)
        {
                this.path=path;
        }
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Questo metodo, ritorna la directory impostata da dove vengono prelecate le immagini.
         * le immagini alla directory corrente.
         * </font></font></p></pre> 
         * @see #path
         */
        public String getPath()
        {
                return path;
        }
}
