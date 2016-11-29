import java.util.Vector;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;
/**
 * <p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *
 *
 * La sequente classe viene utilizzata per la creazione dell'interfaccia relativa all'help di ogni elemento 
 * di traffico.
 * @see HelpDescVector
 */
public class HelpDescBASE
{
    private int ID_Help         = -1;
    private Vector V_descDESC   = null;
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe cha accetta in ingresso l'identificativo dell'help ed una struttura contenente la coppia
     * di valori (codice, dicitura).
     * N.B.: I vari ogetti help sono inclusi nella configurazione, la quale viene caricata mediante lo starto CORBA sottostante all'intero progetto client.
     * </font></font></p></pre> 
     * 
     * @param ID_help identificativo univoco per l'helemento help.
     * @param dataHelp array di tipo <DATA_HELP> contenente le coppie di valori (codice, dicitura).
     */
    public HelpDescBASE(int ID_help, DATA_HELP[] dataHelp) 
    {
        ID_Help = ID_help;
        if(dataHelp != null)
        {
            V_descDESC = new Vector();
        
            for(int i=0;i<dataHelp.length;i++)
            {
                V_descDESC.addElement(dataHelp[i]);
            }
        }
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Il metodo restituisce l'identificativo dell'ogetto Help.
     * </font></font></p></pre>  
     * @return ritorna il codice identificativo dell'ogetto help.
     */
    public int getIDHelp()
    {
        return ID_Help;
    }
    
    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Il metodo restituisce un arrai di tipo <DATA_HELP[]> contenente coppie di valori (codice, dicitura)
     * </font></font></p></pre>  
     * @return ritorna un'array di tipo <DATA_HELP[]> contenente coppie di valori (codice, dicitura)
     */
    public DATA_HELP[] getDataDESC()
    {
        if(V_descDESC != null)
        {
            DATA_HELP[] dateDESC = new DATA_HELP[V_descDESC.size()];
            for(int i=0; i<V_descDESC.size();i++)
            {
                dateDESC[i] = (DATA_HELP)V_descDESC.elementAt(i);
            }
            return dateDESC;
        }
        return null;
    }
}
