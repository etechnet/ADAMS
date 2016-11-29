package org.jacorb.imr;

/**
 * Generated from IDL exception "UnknownServerName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class UnknownServerName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UnknownServerName()
	{
		super(org.jacorb.imr.UnknownServerNameHelper.id());
	}

	public java.lang.String name;
	public UnknownServerName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public UnknownServerName(java.lang.String name)
	{
		super(org.jacorb.imr.UnknownServerNameHelper.id());
		this.name = name;
	}
}
