package org.omg.CosCollection;


/**
 * Generated from IDL interface "OrderedIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface OrderedIteratorOperations
	extends org.omg.CosCollection.IteratorOperations
{
	/* constants */
	/* operations  */
	boolean set_to_last_element();
	boolean set_to_previous_element() throws org.omg.CosCollection.IteratorInvalid;
	boolean set_to_nth_previous_element(int n) throws org.omg.CosCollection.IteratorInvalid;
	void set_to_position(int position) throws org.omg.CosCollection.PositionInvalid;
	int position() throws org.omg.CosCollection.IteratorInvalid;
	boolean retrieve_element_set_to_previous(org.omg.CORBA.AnyHolder element, org.omg.CORBA.BooleanHolder more) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean retrieve_previous_n_elements(int n, org.omg.CosCollection.AnySequenceHolder result, org.omg.CORBA.BooleanHolder more) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean not_equal_retrieve_element_set_to_previous(org.omg.CosCollection.Iterator test, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean remove_element_set_to_previous() throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean remove_previous_n_elements(int n, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean not_equal_remove_element_set_to_previous(org.omg.CosCollection.Iterator test) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean replace_element_set_to_previous(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean replace_previous_n_elements(org.omg.CORBA.Any[] elements, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean not_equal_replace_element_set_to_previous(org.omg.CosCollection.Iterator test, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean is_first();
	boolean is_last();
	boolean is_for_same(org.omg.CosCollection.Iterator test);
	boolean is_reverse();
}
