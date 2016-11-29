package org.omg.CosCollection;


/**
 * Generated from IDL interface "Collection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface CollectionOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.TypeCode element_type();
	boolean add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	void add_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid;
	void remove_element_at(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	int remove_all();
	void replace_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean retrieve_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean all_elements_do(org.omg.CosCollection.Command what);
	int number_of_elements();
	boolean is_empty();
	void destroy();
	org.omg.CosCollection.Iterator create_iterator(boolean read_only);
}
