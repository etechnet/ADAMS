package org.omg.CosCollection;


/**
 * Generated from IDL interface "KeySortedCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface KeySortedCollectionOperations
	extends org.omg.CosCollection.KeyCollectionOperations , org.omg.CosCollection.SortedCollectionOperations
{
	/* constants */
	/* operations  */
	boolean locate_first_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_last_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_previous_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean locate_previous_element_with_different_key(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
}
