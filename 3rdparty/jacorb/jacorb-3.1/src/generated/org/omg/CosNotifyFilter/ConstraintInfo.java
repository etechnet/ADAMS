package org.omg.CosNotifyFilter;

/**
 * Generated from IDL struct "ConstraintInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ConstraintInfo(){}
	public org.omg.CosNotifyFilter.ConstraintExp constraint_expression;
	public int constraint_id;
	public ConstraintInfo(org.omg.CosNotifyFilter.ConstraintExp constraint_expression, int constraint_id)
	{
		this.constraint_expression = constraint_expression;
		this.constraint_id = constraint_id;
	}
}
