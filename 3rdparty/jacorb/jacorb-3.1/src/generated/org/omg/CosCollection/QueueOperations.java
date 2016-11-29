package org.omg.CosCollection;


/**
 * Generated from IDL interface "Queue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface QueueOperations
	extends org.omg.CosCollection.RestrictedAccessCollectionOperations
{
	/* constants */
	/* operations  */
	void enqueue(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	void dequeue() throws org.omg.CosCollection.EmptyCollection;
	boolean element_dequeue(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
}
