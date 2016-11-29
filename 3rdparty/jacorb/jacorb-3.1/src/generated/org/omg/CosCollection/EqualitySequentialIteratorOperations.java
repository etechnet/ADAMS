package org.omg.CosCollection;


/**
 * Generated from IDL interface "EqualitySequentialIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface EqualitySequentialIteratorOperations
	extends org.omg.CosCollection.EqualityIteratorOperations , org.omg.CosCollection.SequentialIteratorOperations
{
	/* constants */
	/* operations  */
	boolean set_to_first_element_with_value(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean set_to_last_element_with_value(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
	boolean set_to_previous_element_with_value(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid;
}
