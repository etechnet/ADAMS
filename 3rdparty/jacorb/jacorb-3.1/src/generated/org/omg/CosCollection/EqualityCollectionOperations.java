package org.omg.CosCollection;


/**
 * Generated from IDL interface "EqualityCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface EqualityCollectionOperations
	extends org.omg.CosCollection.CollectionOperations
{
	/* constants */
	/* operations  */
	boolean contains_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean contains_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid;
	boolean locate_or_add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean locate_or_add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_next_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_next_different_element(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean remove_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	int remove_all_occurrences(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	int number_of_different_elements();
	int number_of_occurrences(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
}
