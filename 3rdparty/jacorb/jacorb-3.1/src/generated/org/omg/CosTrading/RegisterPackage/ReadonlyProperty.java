package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "ReadonlyProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ReadonlyProperty
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ReadonlyProperty()
	{
		super(org.omg.CosTrading.RegisterPackage.ReadonlyPropertyHelper.id());
	}

	public java.lang.String type;
	public java.lang.String name;
	public ReadonlyProperty(java.lang.String _reason,java.lang.String type, java.lang.String name)
	{
		super(_reason);
		this.type = type;
		this.name = name;
	}
	public ReadonlyProperty(java.lang.String type, java.lang.String name)
	{
		super(org.omg.CosTrading.RegisterPackage.ReadonlyPropertyHelper.id());
		this.type = type;
		this.name = name;
	}
}
