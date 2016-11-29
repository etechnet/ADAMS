package org.omg.CosCollection;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class CollectionIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("add_element_set_iterator", "(in:element ,in:where )");
		irInfo.put("add_element", "(in:element )");
		irInfo.put("is_empty", "()");
		irInfo.put("element_type", "attribute;org.omg.CORBA.TypeCode");
		irInfo.put("replace_element_at", "(in:where ,in:element )");
		irInfo.put("number_of_elements", "()");
		irInfo.put("remove_element_at", "(in:where )");
		irInfo.put("add_all_from", "(in:collector )");
		irInfo.put("retrieve_element_at", "(in:where ,out:element )");
		irInfo.put("remove_all", "()");
		irInfo.put("create_iterator", "(in:read_only )");
		irInfo.put("destroy", "()");
		irInfo.put("all_elements_do", "(in:what )");
	}
}
