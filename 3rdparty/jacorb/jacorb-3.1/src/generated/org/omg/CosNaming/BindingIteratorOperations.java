package org.omg.CosNaming;


/**
 * Generated from IDL interface "BindingIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface BindingIteratorOperations
{
	/* constants */
	/* operations  */
	boolean next_one(org.omg.CosNaming.BindingHolder b);
	boolean next_n(int how_many, org.omg.CosNaming.BindingListHolder bl);
	void destroy();
}
