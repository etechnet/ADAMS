package org.omg.CosCollection;


/**
 * Generated from IDL interface "KeyIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface KeyIteratorOperations
	extends org.omg.CosCollection.IteratorOperations
{
	/* constants */
	/* operations  */
	boolean set_to_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid;
	boolean set_to_next_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid;
	boolean set_to_next_element_with_different_key() throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean retrieve_key(org.omg.CORBA.AnyHolder key) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
	boolean retrieve_next_n_keys(org.omg.CosCollection.AnySequenceHolder keys) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween;
}
