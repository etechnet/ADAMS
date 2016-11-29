package org.omg.CosCollection;


/**
 * Generated from IDL interface "OrderedCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface OrderedCollectionOperations
	extends org.omg.CosCollection.CollectionOperations
{
	/* constants */
	/* operations  */
	void remove_element_at_position(int position) throws org.omg.CosCollection.PositionInvalid;
	void remove_first_element() throws org.omg.CosCollection.EmptyCollection;
	void remove_last_element() throws org.omg.CosCollection.EmptyCollection;
	boolean retrieve_element_at_position(int position, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.PositionInvalid;
	boolean retrieve_first_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
	boolean retrieve_last_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
	org.omg.CosCollection.OrderedIterator create_ordered_iterator(boolean read_only, boolean reverse_iteration);
}
