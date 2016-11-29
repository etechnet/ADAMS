package org.omg.CosCollection;


/**
 * Generated from IDL interface "Stack".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface StackOperations
	extends org.omg.CosCollection.RestrictedAccessCollectionOperations
{
	/* constants */
	/* operations  */
	void push(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	void pop() throws org.omg.CosCollection.EmptyCollection;
	boolean element_pop(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
	boolean top(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection;
}
