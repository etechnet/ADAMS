package org.omg.CosTrading;

/**
 * Generated from IDL exception "IllegalPropertyName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalPropertyName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalPropertyName()
	{
		super(org.omg.CosTrading.IllegalPropertyNameHelper.id());
	}

	public java.lang.String name;
	public IllegalPropertyName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public IllegalPropertyName(java.lang.String name)
	{
		super(org.omg.CosTrading.IllegalPropertyNameHelper.id());
		this.name = name;
	}
}
