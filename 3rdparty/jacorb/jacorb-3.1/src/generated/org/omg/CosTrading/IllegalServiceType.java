package org.omg.CosTrading;

/**
 * Generated from IDL exception "IllegalServiceType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalServiceType
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalServiceType()
	{
		super(org.omg.CosTrading.IllegalServiceTypeHelper.id());
	}

	public java.lang.String type;
	public IllegalServiceType(java.lang.String _reason,java.lang.String type)
	{
		super(_reason);
		this.type = type;
	}
	public IllegalServiceType(java.lang.String type)
	{
		super(org.omg.CosTrading.IllegalServiceTypeHelper.id());
		this.type = type;
	}
}
