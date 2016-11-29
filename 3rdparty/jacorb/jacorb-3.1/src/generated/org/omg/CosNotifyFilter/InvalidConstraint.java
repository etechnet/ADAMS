package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "InvalidConstraint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidConstraint
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InvalidConstraint()
	{
		super(org.omg.CosNotifyFilter.InvalidConstraintHelper.id());
	}

	public org.omg.CosNotifyFilter.ConstraintExp constr;
	public InvalidConstraint(java.lang.String _reason,org.omg.CosNotifyFilter.ConstraintExp constr)
	{
		super(_reason);
		this.constr = constr;
	}
	public InvalidConstraint(org.omg.CosNotifyFilter.ConstraintExp constr)
	{
		super(org.omg.CosNotifyFilter.InvalidConstraintHelper.id());
		this.constr = constr;
	}
}
