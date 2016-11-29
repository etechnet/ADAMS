package org.jacorb.imr.RegistrationPackage;

/**
 * Generated from IDL exception "IllegalPOAName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class IllegalPOAName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalPOAName()
	{
		super(org.jacorb.imr.RegistrationPackage.IllegalPOANameHelper.id());
	}

	public java.lang.String name;
	public IllegalPOAName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public IllegalPOAName(java.lang.String name)
	{
		super(org.jacorb.imr.RegistrationPackage.IllegalPOANameHelper.id());
		this.name = name;
	}
}
