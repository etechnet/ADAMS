package org.jacorb.imr.AdminPackage;

/**
 * Generated from IDL exception "IllegalServerName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class IllegalServerName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalServerName()
	{
		super(org.jacorb.imr.AdminPackage.IllegalServerNameHelper.id());
	}

	public java.lang.String name;
	public IllegalServerName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public IllegalServerName(java.lang.String name)
	{
		super(org.jacorb.imr.AdminPackage.IllegalServerNameHelper.id());
		this.name = name;
	}
}
