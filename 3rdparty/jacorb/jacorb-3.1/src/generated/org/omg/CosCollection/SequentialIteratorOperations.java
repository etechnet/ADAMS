package org.omg.CosCollection;


/**
 * Generated from IDL interface "SequentialIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface SequentialIteratorOperations
	extends org.omg.CosCollection.OrderedIteratorOperations
{
	/* constants */
	/* operations  */
	boolean add_element_as_next_set_iterator(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	void add_n_elements_as_next_set_iterator(org.omg.CORBA.Any[] elements) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean add_element_as_previous_set_iterator(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	void add_n_elements_as_previous_set_iterator(org.omg.CORBA.Any[] elements) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
}
