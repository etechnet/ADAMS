import java.util.HashMap;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DOMTreeModel implements TreeModel {

    private Document doc;

    public DOMTreeModel(Document aDoc) {
        doc = aDoc;
    }

    public Object getRoot() {
        return doc.getDocumentElement();
    }

    public Object getChild(Object parent, int index) {
        Node node = (Node)parent;
        NodeList list = node.getChildNodes();
        return list.item(index);
    }

    public int getChildCount(Object parent) {
        Node node = (Node)parent;
        NodeList list = node.getChildNodes();
        return list.getLength();
    }

    /**
     * mi verifica se è una foglia
     * 
     * @param node
     * @return boolean
     */
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public int getIndexOfChild(Object parent, Object child) {
        Node node = (Node)parent;
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (getChild(node, i) == child)
                return i;
        }
        return -1;
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
    
    public String searchTextInElement(Object current) {
        Node node = (Node)current;
        //NodeList list = node.getChildNodes();

        String sText = "";
         
        if (node.hasChildNodes()) {


            //Il child node di tipo testo è il primo (e unico nel nostro caso)

            Node nTextChild = node.getChildNodes().item(0);

            sText = nTextChild.getNodeValue();
        }
        return sText;
    }

    /**
     * 
     * @param current : nodo a cui richiedere gli attributi
     * @return HashMap contenente gli attributi del nodo
     * null in caso il nodo non avesse attributi
     */
    public HashMap getAttributes(Object current) {
        Node node = (Node)current;

        HashMap hmOut = new HashMap();

        NamedNodeMap nnm = node.getAttributes();

        if (nnm != null && nnm.getLength() > 0) {

            for (int iAttr = 0; iAttr < nnm.getLength(); iAttr++) {

                String attrName = nnm.item(iAttr).getNodeName();
                String attrValue = nnm.item(iAttr).getNodeValue();

                hmOut.put(attrName, attrValue);
            }

            return hmOut;
        } else {
            return null;
        }
    }

    public Object getAttributes(String nome, Object current) {

        HashMap hm = getAttributes(current);

        if (hm.containsKey(nome)) {
            return hm.get(nome);
        } else {
            return null;
        }

    }

}
