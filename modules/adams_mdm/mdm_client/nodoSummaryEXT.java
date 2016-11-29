import javax.swing.tree.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Questa classe viene utilizzato per rappresentare un nodo che costituisce l'oggetto
 * base dei JTree (alberi) utilizzati per la costruzione del sommario.
 * L'oggetto nodo è caratterizato da una stringa che lo identifica univocamente.
 * Il nodo e correlato all'oggetto SampleData che ne definisce l'aspetto grafico.
 * <p align="center">&nbsp;</p> 
 * @see SampleData
 */
public class nodoSummaryEXT extends DefaultMutableTreeNode
{
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Forza la selezione al valore di default <b>Screen</b>.
     * </font></font></p></pre>  
     */
    public String textNode;      
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe che valorizza le specifiche grafiche del nodo.
     * </font></font></p></pre>
     * @param o Oggetto ti tipo SampleData
     * @see SampleData
     */   
    public nodoSummaryEXT(Object o)
    {
        super(o);
        textNode=((SampleData)o).string;
    }
        
    /*public nodoSummaryEXT(String str)
    {
        super(str);
        textNode=str;
    }*/
}
