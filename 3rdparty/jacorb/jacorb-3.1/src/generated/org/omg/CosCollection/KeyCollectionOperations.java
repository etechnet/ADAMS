package org.omg.CosCollection;


/**
 * Generated from IDL interface "KeyCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface KeyCollectionOperations
	extends org.omg.CosCollection.CollectionOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.TypeCode key_type();
	boolean contains_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid;
	boolean contains_all_keys_from(org.omg.CosCollection.KeyCollection collector) throws org.omg.CosCollection.KeyInvalid;
	boolean locate_or_add_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean locate_or_add_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean add_or_replace_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean add_or_replace_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean remove_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid;
	int remove_all_elements_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid;
	boolean replace_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean replace_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean retrieve_element_with_key(org.omg.CORBA.Any key, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.KeyInvalid;
	void key(org.omg.CORBA.Any element, org.omg.CORBA.AnyHolder key) throws org.omg.CosCollection.ElementInvalid;
	void keys(org.omg.CORBA.Any[] elements, org.omg.CosCollection.AnySequenceHolder keys) throws org.omg.CosCollection.ElementInvalid;
	boolean locate_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_next_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_next_element_with_different_key(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	int number_of_different_keys();
	int number_of_elements_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid;
}
