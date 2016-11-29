package org.omg.CosTrading;

/**
 * Generated from IDL exception "MissingMandatoryProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class MissingMandatoryProperty
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public MissingMandatoryProperty()
	{
		super(org.omg.CosTrading.MissingMandatoryPropertyHelper.id());
	}

	public java.lang.String type;
	public java.lang.String name;
	public MissingMandatoryProperty(java.lang.String _reason,java.lang.String type, java.lang.String name)
	{
		super(_reason);
		this.type = type;
		this.name = name;
	}
	public MissingMandatoryProperty(java.lang.String type, java.lang.String name)
	{
		super(org.omg.CosTrading.MissingMandatoryPropertyHelper.id());
		this.type = type;
		this.name = name;
	}
}
