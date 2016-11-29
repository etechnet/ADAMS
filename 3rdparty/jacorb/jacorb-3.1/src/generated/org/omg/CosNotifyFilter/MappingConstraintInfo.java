package org.omg.CosNotifyFilter;

/**
 * Generated from IDL struct "MappingConstraintInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class MappingConstraintInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public MappingConstraintInfo(){}
	public org.omg.CosNotifyFilter.ConstraintExp constraint_expression;
	public int constraint_id;
	public org.omg.CORBA.Any value;
	public MappingConstraintInfo(org.omg.CosNotifyFilter.ConstraintExp constraint_expression, int constraint_id, org.omg.CORBA.Any value)
	{
		this.constraint_expression = constraint_expression;
		this.constraint_id = constraint_id;
		this.value = value;
	}
}
