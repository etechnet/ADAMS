package org.omg.CosConcurrencyControl;

/**
 * Generated from IDL exception "LockNotHeld".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LockNotHeld
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LockNotHeld()
	{
		super(org.omg.CosConcurrencyControl.LockNotHeldHelper.id());
	}

	public LockNotHeld(String value)
	{
		super(value);
	}
}
