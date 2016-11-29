package org.omg.CosTrading;

/**
 * Generated from IDL exception "UnknownServiceType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnknownServiceType
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UnknownServiceType()
	{
		super(org.omg.CosTrading.UnknownServiceTypeHelper.id());
	}

	public java.lang.String type;
	public UnknownServiceType(java.lang.String _reason,java.lang.String type)
	{
		super(_reason);
		this.type = type;
	}
	public UnknownServiceType(java.lang.String type)
	{
		super(org.omg.CosTrading.UnknownServiceTypeHelper.id());
		this.type = type;
	}
}
