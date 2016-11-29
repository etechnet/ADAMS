package org.omg.CORBA;

/**
 * Generated from IDL abstract value type "Pollable".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */


public interface Pollable
	extends org.omg.CORBA.portable.ValueBase 
{
	/* operations  */
	boolean is_ready(int timeout);
	org.omg.CORBA.PollableSet create_pollable_set();
}
