package org.omg.CosCollection;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class KeyCollectionIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("retrieve_element_with_key", "(in:key ,out:element )");
		irInfo.put("remove_all_elements_with_key", "(in:key )");
		irInfo.put("locate_next_element_with_key", "(in:key ,in:where )");
		irInfo.put("replace_element_with_key_set_iterator", "(in:element ,in:where )");
		irInfo.put("locate_or_add_element_with_key_set_iterator", "(in:element ,in:where )");
		irInfo.put("replace_element_with_key", "(in:element )");
		irInfo.put("contains_element_with_key", "(in:key )");
		irInfo.put("locate_element_with_key", "(in:key ,in:where )");
		irInfo.put("locate_or_add_element_with_key", "(in:element )");
		irInfo.put("number_of_elements_with_key", "(in:key )");
		irInfo.put("contains_all_keys_from", "(in:collector )");
		irInfo.put("keys", "(in:elements org.omg.CosCollection.AnySequence,out:keys org.omg.CosCollection.AnySequence)");
		irInfo.put("key_type", "attribute;org.omg.CORBA.TypeCode");
		irInfo.put("add_or_replace_element_with_key", "(in:element )");
		irInfo.put("number_of_different_keys", "()");
		irInfo.put("add_or_replace_element_with_key_set_iterator", "(in:element ,in:where )");
		irInfo.put("remove_element_with_key", "(in:key )");
		irInfo.put("locate_next_element_with_different_key", "(in:where )");
		irInfo.put("key", "(in:element ,out:key )");
	}
}
