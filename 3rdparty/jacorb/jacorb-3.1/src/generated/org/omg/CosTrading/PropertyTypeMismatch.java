package org.omg.CosTrading;

/**
 * Generated from IDL exception "PropertyTypeMismatch".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PropertyTypeMismatch
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PropertyTypeMismatch()
	{
		super(org.omg.CosTrading.PropertyTypeMismatchHelper.id());
	}

	public java.lang.String type;
	public org.omg.CosTrading.Property prop;
	public PropertyTypeMismatch(java.lang.String _reason,java.lang.String type, org.omg.CosTrading.Property prop)
	{
		super(_reason);
		this.type = type;
		this.prop = prop;
	}
	public PropertyTypeMismatch(java.lang.String type, org.omg.CosTrading.Property prop)
	{
		super(org.omg.CosTrading.PropertyTypeMismatchHelper.id());
		this.type = type;
		this.prop = prop;
	}
}
