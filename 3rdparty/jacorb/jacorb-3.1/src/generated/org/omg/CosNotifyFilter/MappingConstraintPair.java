package org.omg.CosNotifyFilter;

/**
 * Generated from IDL struct "MappingConstraintPair".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class MappingConstraintPair
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public MappingConstraintPair(){}
	public org.omg.CosNotifyFilter.ConstraintExp constraint_expression;
	public org.omg.CORBA.Any result_to_set;
	public MappingConstraintPair(org.omg.CosNotifyFilter.ConstraintExp constraint_expression, org.omg.CORBA.Any result_to_set)
	{
		this.constraint_expression = constraint_expression;
		this.result_to_set = result_to_set;
	}
}
