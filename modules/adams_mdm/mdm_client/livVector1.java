import java.util.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe estende un vettore e viene utilizzata per tenere traccia delle relazioni lette 
 * al momento della creazione dinamica dell'albero rappresentante le relazioni stesse.
 * Dato che l'albero viene costriuito in maniera ricorsiva, questa classe si occupa di tenere traccia dei nodi a vari livelli analizzati.
 * 
 * @see DATA_RELATIONS
 * @see SelectRelations  
 * @see Nodo 
 */
public class livVector1 extends Vector
{
    //RICORDARSI CHE IL VETTORE NELLA CLASSE INIZIA DA 0
    //MA NELLA SOTTOCLASSE PARTE DA 1.
    private int numLevel;

    /**
    * <pre>
    * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
    * Costruttore della classe che istanzia un vettore, contenente i vari livelli di relazioni.		
    * </font></font></p></pre>  
    */
    public livVector1()
    {
        super();
    }

    /**
    * <pre>
    * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
    * Questo metodo aggiunge un livello al vettore.
    * </font></font></p></pre>  
    */
    public void add_Level()
    {
        Vector vLivello=new Vector();
        addElement(vLivello);
        numLevel=size();
    }

    /**
    * <pre>
    * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
    * Questo metodo rimuove l'ultimo livello del vettore.
    * </font></font></p></pre>  
    */
    public void remove_LastLevel()
    {
        Vector v=(Vector)lastElement();
        removeElement(v);
        numLevel=size();
    }

    /**
    * <pre>
    * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
    * Questo metodo restituisce il numero di livelli presenti nel vettore.
    * </font></font></p></pre>  
    */
    public int num_Livelli()
    {
        return numLevel;
    }
        
    /**
    * <pre>
    * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
    * Questo metodo restituisce il numero di nodi presenti al livello specificato.
    * </font></font></p></pre>  
    * @param numLevel identificativo univoco del livello.
    */
    public int num_NodiAtLevel(int numLevel)
    {
        try
        {
            numLevel=numLevel-1;
            Vector v;
            v=(Vector)elementAt(numLevel);
            return v.size();
        }catch(ArrayIndexOutOfBoundsException ex)
        {
            ////System.out.println("Errore (Livello inesistente): <num_NodiAtLevel>");  
            return -1;
        }
    }
        
    public void add_NodoAtLevel(int numLevel,Nodo n)
    {
        try
        {
            numLevel=numLevel-1;
            Vector v;
            v=(Vector)elementAt(numLevel);
            v.addElement(n);
        }catch(ArrayIndexOutOfBoundsException ex)
        {
            ////System.out.println("Errore (Livello inesistente): <add_NodoAtLevel>");  
            return;
        }
    }
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo aggiunge un nodo al livello specificato.
     * </font></font></p></pre>  
     * @param numLevel identificativo univoco del livello,
     * @param n nodo da aggiungere al livello specificato.
     * @see Nodo
     */       
    public Nodo get_NodoOfLevel(int numLevel,Nodo n)
    {
        try
        {
            Vector v;
            numLevel=numLevel-1;
            v=(Vector)elementAt(numLevel);
            Nodo appo;
            for(int i=0;i<v.size();i++)
            {
                appo=(Nodo)v.elementAt(i);
                if ( appo.id_Nodo==n.id_Nodo )
                {
                    return appo;
                }
            }
            return null;
        }
        catch(NoSuchElementException ex)
        {
            ////System.out.println("Errore: <get_NodoOfLevel>");
            return null;
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            ////System.out.println("Errore (Livello inesistente)o(Posizione nodo inesistente): <get_NodoOfLevel>");
            return null;
        }
    }
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo ritorna il nodo alla posizione ed al livello specificati
     * in caso affermativo restituisce il <b>nodo</b>, altrimenti restituisce <b>null</b>.
     * </font></font></p></pre>  
     * @param numLevel identificativo univoco del livello,
     * @param pos posizione da dove prelevare il nodo.
     * @see Nodo
     */ 
    public Nodo get_NodoOfLevel(int numLevel,int pos)
    {
        try
        {
            Vector v;
            numLevel=numLevel-1;
            v=(Vector)elementAt(numLevel);
            Nodo n=(Nodo)v.elementAt(pos);
            return n;
        }catch(NoSuchElementException ex)
        {
            ////System.out.println("Errore: <get_NodoOfLevel>");
            return null;
        }catch(ArrayIndexOutOfBoundsException ex)
        {
            ////System.out.println("Errore (Livello inesistente)o(Posizione nodo inesistente): <get_NodoOfLevel>");
            return null;
        }
    }
     
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo controlla se al livello specificato esiste il nodo specificato:
     * in caso affermativo restituisce <b>true</b>, altrimenti restituisce <b>false</b>.
     * </font></font></p></pre>  
     * @param numLevel identificativo univoco del livello,
     * @param n nodo da aggiungere al livello specificato.
     * @see Nodo
     */
    public boolean is_PresentNodo(int numLevel,Nodo n)
    {
        try
        {
            Vector v;
            numLevel=numLevel-1;
            v=(Vector)elementAt(numLevel);

            for(int i=0;i<v.size();i++)
                    if ( ((Nodo)v.elementAt(i)).id_Nodo==n.id_Nodo )
                            return true;
            return false;
        }catch(ArrayIndexOutOfBoundsException ex)
        {
            ////System.out.println("Errore (Livello inesistente): <is_PresentNodo>");
            return false;
        }
    }    
}
