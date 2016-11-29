package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "UnknownPropertyName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnknownPropertyName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UnknownPropertyName()
	{
		super(org.omg.CosTrading.RegisterPackage.UnknownPropertyNameHelper.id());
	}

	public java.lang.String name;
	public UnknownPropertyName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public UnknownPropertyName(java.lang.String name)
	{
		super(org.omg.CosTrading.RegisterPackage.UnknownPropertyNameHelper.id());
		this.name = name;
	}
}
