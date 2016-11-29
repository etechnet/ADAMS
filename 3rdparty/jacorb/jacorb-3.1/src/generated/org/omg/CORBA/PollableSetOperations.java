package org.omg.CORBA;


/**
 * Generated from IDL interface "PollableSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface PollableSetOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.DIIPollable create_dii_pollable();
	void add_pollable(org.omg.CORBA.Pollable potential);
	org.omg.CORBA.Pollable get_ready_pollable(int timeout) throws org.omg.CORBA.PollableSetPackage.NoPossiblePollable;
	void remove(org.omg.CORBA.Pollable potential) throws org.omg.CORBA.PollableSetPackage.UnknownPollable;
	short number_left();
}
