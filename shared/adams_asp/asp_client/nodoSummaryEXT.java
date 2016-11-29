import javax.swing.tree.*;

/**
 *
 * @author   Administrator
 * @version 
 */
public class nodoSummaryEXT extends DefaultMutableTreeNode
{
    public String textNode;      
    public int id;
    
    public nodoSummaryEXT(Object o)
    {
            super(o);
            textNode=((SampleData)o).string;
            id=((SampleData)o).id;
    }

    public nodoSummaryEXT(String str)
    {
            super(str);
            textNode=str;
    }  
}
