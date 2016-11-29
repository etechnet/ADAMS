package net.etech.loadior;

/**
 * <p align="center"><font size="1"><b><font size="3" face="Times New Roman, Times, serif"> MONITOR CLIENT</font></b></font></p>
 * <p align="center"> <b>Author:</b></p>
 * <p align="center">-  Beltrame Luca (HP Invent) Created on 24/06/2003 - <a href="mailto:lube@lpconsult.net">lube@lpconsult.net</a></p>
 *
 * La classe ï¿½ costituita da una serie di variabili e classi di utilizzo comune per l'applicativo, dalle
 * define alle strutture dati rilevate dalle classi generate dal compilatore IDL di  Orbix, alle struttre locali di dati
 * di uso comune , come l'istanza della classe PM_CORBAConnection che permette di effettuare tramite metodi proprietari
 * dell'oggetto le chiamate ai server remoti tramite CORBA.
 *
 */


public class ADAMS_IOR_GlobalParam
{
    public static boolean DEBUG 		= false;
    

    /**
     *Costruttore di default della classe.
     */
    public ADAMS_IOR_GlobalParam()
    {
    }

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_P11 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,11);
    public static java.awt.Font font_P12 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,12);
   /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */

    public static java.awt.Font font_B11 = new java.awt.Font("Verdana",java.awt.Font.BOLD,11);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */
    public static java.awt.Font font_B10 = new java.awt.Font("Verdana",java.awt.Font.BOLD,10);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "PLAIN". Size Font = "11".
     */
    public static java.awt.Font font_P10 = new java.awt.Font("Verdana",java.awt.Font.PLAIN,10);

     /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "11".
     */
    public static java.awt.Font font_B9 = new java.awt.Font("Verdana",java.awt.Font.BOLD,9);


    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "12".
     */
    public static java.awt.Font font_B12 = new java.awt.Font("Verdana",java.awt.Font.BOLD,12);

    /**
     *Oggetto di tipo Font utilizzato per la costruzione dell'interfacci GUI:
     *Name Font = "Verdana". Type Font = "BOLD". Size Font = "8".
     */
    public static java.awt.Font font_B8 = new java.awt.Font("Verdana",java.awt.Font.BOLD,8);

    /**
     *Variabile Stringa contenente il nome della macchina remota dove viene effettuata la connesione CORBA.
     */
    public static String machine_name = "";
    /**
     *Variabile Stringa contenente il numero della porta dove viene effettuata la connesione CORBA.
     */
    public static String locator_port = "";

    public static ADAMS_IOR_DBConnection db_connect = null;


    public static void optionPanel(String msg,String title,int type)
    // Option panel
    {
            javax.swing.JOptionPane op = new javax.swing.JOptionPane();
            op.setBackground(new java.awt.Color(230,230,230));
            op.showMessageDialog(null, msg,title,type,null);
    }


    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type)
    {
        ADAMS_IOR_JD_Message op1 = new ADAMS_IOR_JD_Message(Parent,true,msg,title,type);
    }

    public static void optionPanel(javax.swing.JFrame Parent, String msg,String title,int type,int width,int height)
    {
         ADAMS_IOR_JD_Message op2 = new ADAMS_IOR_JD_Message(Parent,true,msg,title,type,width,height);
    }

    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type)
    {
        ADAMS_IOR_JD_Message op1;

        if(Parent == null)
            op1 = new ADAMS_IOR_JD_Message((javax.swing.JFrame)null,true,msg,title,type); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op1 = new ADAMS_IOR_JD_Message(Parent,true,msg,title,type);
    }

    public static void optionPanel(javax.swing.JDialog Parent, String msg,String title,int type,int width,int height)
    {
        ADAMS_IOR_JD_Message op2;

        if(Parent == null)
            op2 = new ADAMS_IOR_JD_Message((javax.swing.JFrame)null,true,msg,title,type,width,height); // nel caso la javax.swing.JDialog == null genera eccezione
        else
            op2 = new ADAMS_IOR_JD_Message(Parent,true,msg,title,type,width,height);
    }

    public static char[] set_String_toChar(String str, int length)
    {

    	char[] appo = str.toCharArray();
    	char[] appo1 = new char[length];

    	if (appo.length > length)
    	    return appo1;


    	for (int i=0; i<appo.length; i++)
    	    appo1[i] = appo[i];

    	for (int i=appo.length; i<length; i++)
    	    appo1[i] ='\0';

        appo1[length-1] ='\0';

    	return(appo1);
    }

}