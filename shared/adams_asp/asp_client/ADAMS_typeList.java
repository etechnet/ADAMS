/*
 * ADAMS_typeList.java
 *
 * Created on 14 dicembre 2005, 13.14
 */

/**
 *
 * @author  Raffo
 * @version 
 */

import java.sql.*;
import java.util.*;
import java.sql.*;

public class ADAMS_typeList extends Vector{

    private boolean             DEBUG = false;
    public class eBase
    {
        int id;
        String desc;
        
        public eBase(int id,String desc)
        {
            this.id=id;
            this.desc=desc;
        }
    }
    /** Creates new ADAMS_typeList */
    public ADAMS_typeList() {
        eBase appo=new eBase(0,"Char");
        addElement(appo);
         appo=new eBase(0,"Filler");
         addElement(appo);
         appo=new eBase(1,"Unsigned Char");
         addElement(appo);
         appo=new eBase(2,"Short");
         addElement(appo);
         appo=new eBase(3,"Unsigned Short");
         addElement(appo);
         appo=new eBase(4,"Int ");
         addElement(appo);
         appo=new eBase(5,"Unsigned Int");
         addElement(appo);
         appo=new eBase(6,"Long");
         addElement(appo);
         appo=new eBase(7,"Unsigned Long");
         addElement(appo);
         appo=new eBase(8,"Float");
         addElement(appo);
         appo=new eBase(9,"Double");
         addElement(appo);
         appo=new eBase(10,"Binary Coded Decimal");
         addElement(appo);
         appo=new eBase(11,"C-Type Char String");
         addElement(appo);
         appo=new eBase(12,"Byte String");
         addElement(appo);
        
        
    }
    
    public int idType(int idElement)
    {
        String desc="";
        
        String strSelect="select DESCRIPTION from tab_help where IDHELP=0 and ID="+idElement;
        if(DEBUG)
            System.out.println("ADAMS_typeList [idType] ----> "+strSelect);
        
        Statement SQLStatement = ADAMS_GlobalParam.connect_Oracle.createLocalStatement();
        ResultSet rs  = ADAMS_GlobalParam.connect_Oracle.Query_RS(strSelect,SQLStatement);
        
        if(rs != null)
        {
            try
            {
                while ( rs.next ( ) ) 
                {
                    desc=rs.getString("DESCRIPTION");
                }
            }catch (Exception ex) 
            {
                System.out.println(ex);
            }  
        }else
        {
            System.out.println("ADAMS_typeList [idType] ----> rs==null");
        }
        
        try
        {
            SQLStatement.close();
        }
        catch(java.sql.SQLException exc) 
        {
            System.out.println("ADAMS_typeList [idType] ----> ERROR-SQL Operation " + exc.toString());
        }
        
        int ret=-1;
        for(int i=0;i<size();i++)
        {
            eBase appo=(eBase)elementAt(i);
            if(appo.desc.equals(desc))
            {
                ret=appo.id;
                break;
            }
        }
        return ret;
    }
    
    

}
