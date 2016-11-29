package org.omg.CosCollection;


/**
 * Generated from IDL interface "Iterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface IteratorOperations
{
	/* constants */
	/* operations  */
	boolean set_to_first_element();
	boolean set_to_next_element() throws org.omg.CosCollection.IteratorInvalid;
	boolean set_to_next_nth_element(int n) throws org.omg.CosCollection.IteratorInvalid;
	boolean retrieve_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean retrieve_element_set_to_next(org.omg.CORBA.AnyHolder element, org.omg.CORBA.BooleanHolder more) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean retrieve_next_n_elements(int n, org.omg.CosCollection.AnySequenceHolder result, org.omg.CORBA.BooleanHolder more) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean not_equal_retrieve_element_set_to_next(org.omg.CosCollection.Iterator test, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	void remove_element() throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean remove_element_set_to_next() throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean remove_next_n_elements(int n, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean not_equal_remove_element_set_to_next(org.omg.CosCollection.Iterator test) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	void replace_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean replace_element_set_to_next(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean replace_next_n_elements(org.omg.CORBA.Any[] elements, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean not_equal_replace_element_set_to_next(org.omg.CosCollection.Iterator test, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean add_element_set_iterator(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean add_n_elements_set_iterator(org.omg.CORBA.Any[] elements, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.ElementInvalid;
	void invalidate();
	boolean is_valid();
	boolean is_in_between();
	boolean is_for(org.omg.CosCollection.Collection collector);
	boolean is_const();
	boolean is_equal(org.omg.CosCollection.Iterator test) throws org.omg.CosCollection.IteratorInvalid;
	org.omg.CosCollection.Iterator _clone();
	void assign(org.omg.CosCollection.Iterator from_where) throws org.omg.CosCollection.IteratorInvalid;
	void destroy();
}
