import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Classe che estende un Object, utilizzato per la costruzione del JTree
 * Questa classe permette la personalizzazione grafica del nodo.
 * <pre>
 * N.B.: ogni nodo è cosi definito:
 * - Tipo font,
 * - colore,
 * - descrizione,
 * - tipo di font su selezione,
 * - colre su selezione,
 * - descrizione  su selezione,
 * - Icone rappresentative: radice/radice selezionata, ramo/ramo selezionato, foglia/foglia selezionata.
 * </pre>
 *	
 * <p align="center">&nbsp;</p> 
 * @see #font
 * @see #color
 * @see #color
 * @see #fontSel
 * @see #fontSel
 * @see #stringSel
 * @see #iconSel
 * @see #collapsedIcon
 * @see #expandedIcon
 * @see #leafIcon
 * @see #selIcon
 */
public class SampleData extends Object
{
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Tipo di font del nodo.
     * </font></font></p></pre>  
     */
    protected Font          font;

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Colore della descrizione del nodo.
     * </font></font></p></pre>  
     */
    protected Color         color;

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Stringa descrittiva del nodo.
     * </font></font></p></pre>  
     */
    protected String        string;


    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Tipo di font del nodo selezionato.
     * </font></font></p></pre>  
     */
    protected Font          fontSel;

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Colore del nodo selezionato.
     * </font></font></p></pre>  
     */
    protected Color         colorSel;

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Stringa descrittiva del nodo selezionato.
     * </font></font></p></pre>  
     */
    protected String        stringSel;

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Immagine dell nodo selezionato.
     * </font></font></p></pre>  
     */
    protected ImageIcon             iconSel;
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Immagine del nodo chiuso.
     * </font></font></p></pre>  
     */
    protected ImageIcon        collapsedIcon;
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Immagine del nodo aperto.
     * </font></font></p></pre>  
     */
    protected ImageIcon        expandedIcon;
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Immagine della foglia.
     * </font></font></p></pre>  
     */
    protected ImageIcon        leafIcon;
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Immagine del foglia selezionata.
     * </font></font></p></pre>  
     */
    protected ImageIcon        selIcon;
        
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore di classe:
     * il seguente costrutture accetta in ingresso tre parametri,necessari per costruzione del nodo. 		
     * </font></font></p></pre>  
     * @param newFont tipo di font del nodo,
     * @param newColor colore della descrizione del nodo,
     * @param newString descrizione del nodo.
     * @see nodoSummaryEXT
     */ 
    public SampleData(Font newFont, Color newColor, String newString) 
    {
        font = newFont;
        color = newColor;
        string = newString;

        fontSel=font;
        colorSel=color;
        stringSel=string;

        collapsedIcon=null;
        expandedIcon=null;
        leafIcon=null;

    }
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore di classe:
     * il seguente costrutture accetta in ingresso sei parametri,necessari per costruzione del nodo. 		
     * </font></font></p></pre>  
     * @param newFont tipo di font del nodo,
     * @param newColor colore della descrizione del nodo,
     * @param newString descrizione del nodo.
     * @param newFontSel tipo di font del nodo selezionato,
     * @param newColorSel colore della descrizione del nodo selezionato,
     * @param newStringSel descrizione del nodo selezionato.
     * @see nodoSummaryEXT
     */      
    public SampleData(Font newFont, Color newColor, String newString,
                                        Font newFontSel, Color newColorSel, String newStringSel) 
    {
        font = newFont;
        color = newColor;
        string = newString;

        fontSel=newFontSel;
        colorSel=newColorSel;
        stringSel=newStringSel;

        collapsedIcon=null;
        expandedIcon=null;
        leafIcon=null;
    }
        
        
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Questo metodo setta le varie icone che carratterizzano il nodo.
     * </font></font></p></pre>  
     * @param collapsedIcon Immagine del nodo chiuso,
     * @param expandedIcon Immagine del nodo chiuso,
     * @param leafIcon Immagine della foglia,
     * @param selIcon Immagine della foglia selezionata.
     * @see nodoSummaryEXT
     */  
    public void setIcon(ImageIcon collapsedIcon,ImageIcon expandedIcon,ImageIcon leafIcon,ImageIcon selIcon) 
    {
            this.collapsedIcon=collapsedIcon;
            this.expandedIcon=expandedIcon;
            this.leafIcon=leafIcon;
            this.selIcon=selIcon;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Setta il font per la stringa descrittiva della foglia.
     * </font></font></p></pre>  
     * @param newFont tipo di font utilizzato per la descrizione della foglia.
     * @see nodoSummaryEXT
     */  
    public void setFont(Font newFont) {
        font = newFont;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Restituisce il font della stringa descrittiva della foglia.
     * </font></font></p></pre>  
     * @see nodoSummaryEXT
     */ 
    public Font getFont() {
        return font;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Setta il colore per la stringa descrittiva della foglia.
     * </font></font></p></pre>  
     * @param newColor tipo di colore utilizzato per la descrizione della foglia.
     * @see nodoSummaryEXT
     */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Restituisce  il colore della la stringa descrittiva della foglia.
     * </font></font></p></pre>  
     * @see nodoSummaryEXT
     */
    public Color getColor() {
        return color;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Setta la descrizione della foglia.
     * </font></font></p></pre>
     * @param newString descrizione della foglia.
     * @see nodoSummaryEXT
     */
    public void setString(String newString) {
        string = newString;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Ritorna la descrizione della foglia.
     * </font></font></p></pre>
     * @see nodoSummaryEXT
     */
    public String string() {
        return string;
    }

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Overloading del metodo di default <b>toString()</b>.
     * </font></font></p></pre>
     * @see nodoSummaryEXT
     */
    public String toString() {
        return string;
    }
}
