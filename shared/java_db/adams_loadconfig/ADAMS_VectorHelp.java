package net.etech.loadconfig;
/*
 * ADAMS_VectorHelp.java
 *
 * Created on 26 luglio 2005, 15.35
 */

/**
 *
 * @author  Raffaele Ficcadenti
 * @version 
 */
import java.util.Vector;
import java.sql.*;

public class ADAMS_VectorHelp extends Vector {

    /*
        ID                                        NOT NULL NUMBER
        TIPO_DI_CONFIGURAZIONE                    VARCHAR2(30)
        IDHELP                                    NOT NULL NUMBER
        HELPVALUE                                 NOT NULL NUMBER
        DESCRIPTION                               VARCHAR2(160)
     */
    
    public int          idFamiglia      = -1;
    public String       descFamiglia    = "";
    
    class ElmentoBase
    {
        private int         id          = -1;
        private Integer     helpValue   = null;
        private String      desc        = null;
        
        public ElmentoBase(Integer helpValue,String desc,int id)
        {
                this.helpValue=helpValue;
                this.desc=desc;
                this.id=id;
        }
        
        public Integer getValue()
        {
            return helpValue;
        }

        public String getDesc()
        {
            return desc;
        }
        
        public int getID()
        {
            return id;
        }
    }

    /** Creates new ADAMS_VectorHelp */
    public ADAMS_VectorHelp(String config,int idFamiglia) 
    {
        super();
        
        this.idFamiglia=idFamiglia;
        this.descFamiglia=ADAMS_GlobalParam.type_help[idFamiglia];
        
        //carico gli help della amiglia <idFamiglia>
        
        String strSelect="select ID,HELPVALUE,DESCRIPTION from tab_help where TIPO_DI_CONFIGURAZIONE='"+config+"' AND IDHELP="+idFamiglia+" order by HELPVALUE";
        
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("Select -> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        if(rs==null)
        {
            System.out.println("Errore ORACLE");
        }else
        {
            creaADAMS_VectorHelp(rs);
            try
            {
                SQLStatement.close();
            }
            catch(java.sql.SQLException exc) 
            {
                System.out.println("(ADAMS_VectorHelp-1) SQL Operation " + exc.toString());
            }
        }
        
        
    }
    
    public ADAMS_VectorHelp(String config,String desc) 
    {
        super();
        idFamiglia = -1;
        
        //carico gli help della amiglia <idFamiglia>
        for(int i=0;i<ADAMS_GlobalParam.type_help.length;i++)
        {
            if(ADAMS_GlobalParam.type_help[i].equals(desc))
            {
                idFamiglia=i;
                break;
            }
        }
        if(idFamiglia==-1)
        {   
            System.out.println("Famiglia <"+desc+"> non trovata");
            return;
        }
        
        this.descFamiglia=ADAMS_GlobalParam.type_help[idFamiglia];
        
        
        String strSelect="select ID,HELPVALUE,DESCRIPTION from tab_help where TIPO_DI_CONFIGURAZIONE='"+config+"' AND IDHELP="+idFamiglia+" order by HELPVALUE";
        
        if(ADAMS_GlobalParam.DEBUG)
            System.out.println("Select -> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        if(rs==null)
        {
            System.out.println("Errore ORACLE");
        }else
        {
            creaADAMS_VectorHelp(rs);
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("(ADAMS_VectorHelp-) SQL Operation " + exc.toString());
        }
    }
    

    private void creaADAMS_VectorHelp(ResultSet rs)
    {

        try
        {
            while ( rs.next ( ) ) 
            {

                if(ADAMS_GlobalParam.DEBUG)
                {
                    System.out.println("ID ---->"+rs.getInt("ID"));
                    System.out.println("HELPVALUE ---->"+rs.getInt("HELPVALUE"));
                    System.out.println("DESCRIPTION -->"+rs.getString("DESCRIPTION"));
                    System.out.println();
                }

                ElmentoBase eb=new ElmentoBase(new Integer(rs.getInt("HELPVALUE")),rs.getString("DESCRIPTION"),rs.getInt("ID"));


                addElement(eb);

            }
        }catch (Exception ex) 
        {
            ADAMS_GlobalParam.optionPanel((javax.swing.JFrame)null,"ERROR:"+ex.toString(),"ERROR",1);
        }  
    }
    
    public int getValue(String desc)
    {
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            if(eb.getDesc().equals(desc))
            {
                return eb.getValue().intValue();
            }
        }
        return -1;
    }
    
    public int getID(String desc,int value)
    {
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            if(eb.getDesc().equals(desc))
            {
                if( eb.getValue().intValue()==value )
                {
                    return eb.getID();
                }
            }
        }
        return -1;
    }
    
    public int getValue(int id)
    {
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            if(eb.getID()==id)
            {
                return eb.getValue().intValue();
            }
        }
        return -1;
    }
    
    public String getDesc(int id)
    {
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            if(eb.getID()==id)
            {
                return eb.getDesc();
            }
        }
        return null;
    }
    
    public String getDescDalValue(int value)
    {
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            if(eb.getValue().intValue()==value)
            {
                return eb.getDesc();
            }
        }
        return null;
    }


    
    public static int getIdFamiglia(String desc)
    {
        int idFamiglia = -1;
        
        //carico gli help della amiglia <idFamiglia>
        for(int i=0;i<ADAMS_GlobalParam.type_help.length;i++)
        {
            if(ADAMS_GlobalParam.type_help[i].equals(desc))
            {
                idFamiglia=i;
                break;
            }
        }
        
        return idFamiglia;
    }
    
    public static String getDescFamiglia(int id)
    {
        if(id>ADAMS_GlobalParam.type_help.length)
            return null;
        else
            return ADAMS_GlobalParam.type_help[id];
    }
    
    public int numElement()
    {
        return size();
    }
    
    public Vector vgetValue()
    {
        Vector appo=new Vector();
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            appo.addElement(eb.getValue());
        }
        return appo;
    }
    
    public Vector vgetDesc()
    {
        Vector appo=new Vector();
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            appo.addElement(eb.getDesc());
        }
        return appo;
    }
    
    public void stampaAll()
    {
        if(size()==0)
        {
            System.out.println("<NO ELEMENTS>");
            return;
        }
        
        System.out.println("<"+idFamiglia+"-"+descFamiglia+">");
        
        for(int i=0;i<size();i++)
        {
            ElmentoBase eb=(ElmentoBase)elementAt(i);
            System.out.println("ID="+eb.getID()+" ->"+eb.getValue()+"-"+eb.getDesc());
        }
        
    }

}
