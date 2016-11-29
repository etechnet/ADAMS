package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "InvalidValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidValue
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InvalidValue()
	{
		super(org.omg.CosNotifyFilter.InvalidValueHelper.id());
	}

	public org.omg.CosNotifyFilter.ConstraintExp constr;
	public org.omg.CORBA.Any value;
	public InvalidValue(java.lang.String _reason,org.omg.CosNotifyFilter.ConstraintExp constr, org.omg.CORBA.Any value)
	{
		super(_reason);
		this.constr = constr;
		this.value = value;
	}
	public InvalidValue(org.omg.CosNotifyFilter.ConstraintExp constr, org.omg.CORBA.Any value)
	{
		super(org.omg.CosNotifyFilter.InvalidValueHelper.id());
		this.constr = constr;
		this.value = value;
	}
}
