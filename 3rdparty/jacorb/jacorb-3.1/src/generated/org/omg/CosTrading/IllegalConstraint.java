package org.omg.CosTrading;

/**
 * Generated from IDL exception "IllegalConstraint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalConstraint
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalConstraint()
	{
		super(org.omg.CosTrading.IllegalConstraintHelper.id());
	}

	public java.lang.String constr;
	public IllegalConstraint(java.lang.String _reason,java.lang.String constr)
	{
		super(_reason);
		this.constr = constr;
	}
	public IllegalConstraint(java.lang.String constr)
	{
		super(org.omg.CosTrading.IllegalConstraintHelper.id());
		this.constr = constr;
	}
}
