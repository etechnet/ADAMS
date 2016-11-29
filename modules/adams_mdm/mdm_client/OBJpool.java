import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe è una semplice classe di utilità, creata ed utilizzata 
 * per il semplice scopo di ottimizzare la gestione dei pannelli delle restrizioni, 
 * che di volta in volta vengono costruiti, in base alla configurazione caricata 
 * La classe OBJpool crea un Hastable nella quale mette le istanze dei vari pannelli delle restrizioni caricati
 * dall'utente in questo caso, anche se un il pannello viene usato piu di una volta all'interno
 * dell'interfaccia, esso verrà creato una sola volta e memorizzato nell'Hastable.
 * il cilo di vita di tale pannello è ristretto al ciclo di vita dell'applet.
 *
 *<p align="center">&nbsp;</p>    
 * @see IconPool
 */
public class OBJpool
{
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Hastable in cui vengono memorizzati i pannelli delle restrizioni,
         * nella forma <chiave><oggetto>.
         * </font></font></p></pre>  
         * @see #OBJpool()
         * @see #getOBJ(int)
         */
        private Hashtable p;
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Costruttore di default. Non fa altro che istanziare la Hastable
         * </font></font></p></pre>  
         * @see #pTable
         */
        public OBJpool()
        {
                p=new Hashtable();
        }
        
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Questo metodo restituisce il pannello che si trova nella Hastable, avente come chiave: <b>id</b>
         * </font></font></p></pre> 
         * @param id Identificativo univodo del pannelo, usato come chiave nella Hastable
         * @see #pTable		
         */
        public Object getOBJ(int id)
        {
                Object temp=(Object)p.get(new Integer(id));
                return temp;
        }

        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Questo metodo, inserisce il pannello istanziato nella Hastable con il suo id univoco.
         * </font></font></p></pre> 
         * @param id Identificativo univodo del pannelo, usato come chiave nella Hastable
         * @param o pannello delle restrizioni da inserire nella Hastable.
         * @see #pTable		
         */
        public void putOBJ(int id, Object o)
        {
               p.put(new Integer(id),o);   
        }
	
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Ritorna le dimensioni della Hastable
         * </font></font></p></pre>  
         * @see #pTable
         */
        public int size()
        {
                return p.size();
        }
}