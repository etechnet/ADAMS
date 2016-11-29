package org.omg.CosCollection;


/**
 * Generated from IDL interface "Deque".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface DequeOperations
	extends org.omg.CosCollection.RestrictedAccessCollectionOperations
{
	/* constants */
	/* operations  */
	void enqueue_as_first(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	void enqueue_as_last(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	void dequeue_first() throws org.omg.CosCollection.EmptyCollection;
	boolean element_dequeue_first(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
	void dequeue_last() throws org.omg.CosCollection.EmptyCollection;
	boolean element_dequeue_last(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
}
