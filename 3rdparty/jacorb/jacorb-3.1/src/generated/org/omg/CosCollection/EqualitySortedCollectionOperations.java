package org.omg.CosCollection;


/**
 * Generated from IDL interface "EqualitySortedCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface EqualitySortedCollectionOperations
	extends org.omg.CosCollection.EqualityCollectionOperations , org.omg.CosCollection.SortedCollectionOperations
{
	/* constants */
	/* operations  */
	boolean locate_first_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_last_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_previous_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_previous_different_element(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid;
}
