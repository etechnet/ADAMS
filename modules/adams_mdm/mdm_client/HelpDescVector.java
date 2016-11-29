import java.util.*;
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
 * @see HelpDescBASE
 */
public class HelpDescVector extends Vector{

    /**
     * <pre>
     * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
     * Costruttore della classe cha accetta in ingresso l'identificativo dell'help ed una struttura contenente la coppia
     * di valori (codice, dicitura).
     * N.B.: I vari ogetti help sono inclusi nella configurazione, la quale viene caricata mediante lo starto CORBA sottostante all'intero progetto client.
     * </font></font></p></pre>  
     * @param ID_help identificativo univoco per l'helemento help.
     * @param dataHelp array di tipo <DATA_HELP> contenente le coppie di valori (codice, dicitura).
     */
    public HelpDescVector()
    {
        super();
    }

    public void add_HelpDescBase(HelpDescBASE HelpDescBase)
    {
        this.addElement(HelpDescBase);
    }
    
    public int HelpDescPresent(int ID_help)
    {
        int find = -1;
        for(int i=0; i<size(); i++)
        {
            int ID_inArchive = ((HelpDescBASE)elementAt(i)).getIDHelp();
            if(ID_inArchive == ID_help)
            {
               find = i; 
            }
        }
        return find;
    }
    
    public HelpDescBASE getHelpDescBase(int ID_help)
    {
        int index = HelpDescPresent(ID_help);
        if(index != -1)
        {
            return (HelpDescBASE)elementAt(index);
        }
        return null;
    }

        public DATA_HELP[] getDataDESC(int ID_help)
        {
                HelpDescBASE appo=getHelpDescBase(ID_help);
                if(appo!=null)
                {
                        return appo.getDataDESC();
                }
                return null;
        }

        public void ordina(DATA_HELP[] appo,int key)
        {
                staticLib.CORBA.ordina(appo,key);
        }

       /**
         * <pre>
         * <p align="left"><font size="2"><font face="Arial, Helvetica, sans-serif">
         * Ritorna l'help opportunamente ordinato.
         * </font></font></p></pre>
         * @param ID_help identificativo univoco per l'helemento help.
         * @param flagOrder indica se è attivato o meno l'ordinamento.
         * @param key indica secondo quuale campo deve essere effettuato l'ordinamento.
         * @return ritorna un array di tipo <String[]> contenente stringhe di topo <key>-<dicitura> opportunamente ordinato
         */
        public String[] get_Descrizioni_conId(int ID_help,boolean flagOrder,int key)
        {
            DATA_HELP[] appo = getDataDESC(ID_help);

            boolean valido = true;
            String appoSTR = "";
            String appoSTR2 = "";
            //StringTokenizer token;
            Vector v  =new Vector();
            String[] descS = null;

            if(appo != null)
            {
                if(flagOrder)
                {
                    ordina(appo,key);
                }

                for(int i=0; i<appo.length; i++)
                {
                    String temp = (new String(appo[i].data)).trim();
                    int indice = temp.indexOf(" ");

                   //  System.out.println(temp);
                   //  System.out.println(indice);
                   
                    if(indice != -1) // Ho trovato lo spazio
                    {
                        if (temp.indexOf("VAL.(001)")!=-1)
                        {
                             v.addElement((new String(appo[i].key)).trim() +" "+(temp.substring(indice+1)).trim());
                        }
                        else if ( (temp.indexOf("Unknown")==-1) && (temp.indexOf("NOT DEFINED")==-1) && (temp.indexOf("Not ")==-1) && (temp.indexOf("Non ")==-1) )
                        {
                                //System.out.println(temp+"|"+(temp.substring(indice+1)).trim());
                            v.addElement((new String(appo[i].key)).trim() +" "+(temp.substring(indice+1)).trim());
                        }                        
                    }
                    else
                    {
                        v.addElement(temp);
                        //System.out.println("Semplicemente: "+temp);
                    }
                }

                if(v.size()>0)
                {
                    descS=new String[v.size()];
                    for(int j=0;j<v.size();j++)
                    {
                        descS[j]=(String)v.elementAt(j);
                    }
                }

                return descS;
            }
            return null;
        }

}
