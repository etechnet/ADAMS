package org.omg.CosCollection;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class IteratorIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("remove_next_n_elements", "(in:n ,out:actual_number )");
		irInfo.put("remove_element_set_to_next", "()");
		irInfo.put("add_element_set_iterator", "(in:element )");
		irInfo.put("retrieve_element", "(out:element )");
		irInfo.put("is_equal", "(in:test )");
		irInfo.put("is_valid", "()");
		irInfo.put("set_to_next_nth_element", "(in:n )");
		irInfo.put("invalidate", "()");
		irInfo.put("_clone", "()");
		irInfo.put("set_to_first_element", "()");
		irInfo.put("remove_element", "()");
		irInfo.put("replace_element", "(in:element )");
		irInfo.put("replace_element_set_to_next", "(in:element )");
		irInfo.put("retrieve_element_set_to_next", "(out:element ,out:more )");
		irInfo.put("assign", "(in:from_where )");
		irInfo.put("is_const", "()");
		irInfo.put("add_n_elements_set_iterator", "(in:elements org.omg.CosCollection.AnySequence,out:actual_number )");
		irInfo.put("is_for", "(in:collector )");
		irInfo.put("is_in_between", "()");
		irInfo.put("not_equal_remove_element_set_to_next", "(in:test )");
		irInfo.put("set_to_next_element", "()");
		irInfo.put("not_equal_replace_element_set_to_next", "(in:test ,in:element )");
		irInfo.put("retrieve_next_n_elements", "(in:n ,out:result org.omg.CosCollection.AnySequence,out:more )");
		irInfo.put("destroy", "()");
		irInfo.put("replace_next_n_elements", "(in:elements org.omg.CosCollection.AnySequence,out:actual_number )");
		irInfo.put("not_equal_retrieve_element_set_to_next", "(in:test ,out:element )");
	}
}
