package org.omg.CosCollection;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class OrderedIteratorIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("not_equal_replace_element_set_to_previous", "(in:test ,in:element )");
		irInfo.put("is_for_same", "(in:test )");
		irInfo.put("not_equal_retrieve_element_set_to_previous", "(in:test ,out:element )");
		irInfo.put("not_equal_remove_element_set_to_previous", "(in:test )");
		irInfo.put("remove_previous_n_elements", "(in:n ,out:actual_number )");
		irInfo.put("replace_previous_n_elements", "(in:elements org.omg.CosCollection.AnySequence,out:actual_number )");
		irInfo.put("retrieve_previous_n_elements", "(in:n ,out:result org.omg.CosCollection.AnySequence,out:more )");
		irInfo.put("replace_element_set_to_previous", "(in:element )");
		irInfo.put("retrieve_element_set_to_previous", "(out:element ,out:more )");
		irInfo.put("remove_element_set_to_previous", "()");
		irInfo.put("is_reverse", "()");
		irInfo.put("set_to_last_element", "()");
		irInfo.put("set_to_position", "(in:position )");
		irInfo.put("set_to_nth_previous_element", "(in:n )");
		irInfo.put("is_first", "()");
		irInfo.put("is_last", "()");
		irInfo.put("set_to_previous_element", "()");
		irInfo.put("position", "()");
	}
}
