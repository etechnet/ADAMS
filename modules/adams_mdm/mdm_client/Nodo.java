import javax.swing.tree.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe rappresenta semplicemente un Nodo dell'albero che costituirà la struttura delle Relazioni.
 * <pre>
 * N.B.: ogni nodo è cosi definito:
 * - un identificatico del nodo,
 * - un identificatico della relazione che rappresenta,
 * - descrizione completa della relazione che rappresenta,
 * </pre>
 *<p align="center">&nbsp;</p>    
 */
public class Nodo extends DefaultMutableTreeNode
{
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Variabile intera che identifica univocamente il nodo.
         * </font></font></p></pre> 
         */
        public int id_Nodo=-1;
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Variabile intera che identifica univocamente la Relazione che il nodo rappresenta.
         * </font></font></p></pre> 
         */
        public int id_Relation=-1;
        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Variabile Stringa che descrive la relazione che il nodo rappresenta.
         * </font></font></p></pre> 
         */
        public String textNode;

        /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Crea un nodo da inserire nell'albero.
         * </font></font></p></pre>  
         * @param id_Relation identificatico della relazione che rappresenta,
         * @parma identificatico identificatico del nodo,
         * @param text descrizione completa della relazione che rappresenta,
         */
        public Nodo(int id_Relation,int identificatico, String text)
        {
                //super(text);
                setUserObject(text);
                id_Nodo=identificatico;
                this.id_Relation=id_Relation;
                textNode = text;
        }       
}
