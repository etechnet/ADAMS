package org.omg.CosCollection;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class OrderedCollectionIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("retrieve_last_element", "(out:element )");
		irInfo.put("retrieve_first_element", "(out:element )");
		irInfo.put("remove_first_element", "()");
		irInfo.put("remove_last_element", "()");
		irInfo.put("retrieve_element_at_position", "(in:position ,out:element )");
		irInfo.put("create_ordered_iterator", "(in:read_only ,in:reverse_iteration )");
		irInfo.put("remove_element_at_position", "(in:position )");
	}
}
