package org.omg.CosTrading;

/**
 * Generated from IDL exception "DuplicatePolicyName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class DuplicatePolicyName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DuplicatePolicyName()
	{
		super(org.omg.CosTrading.DuplicatePolicyNameHelper.id());
	}

	public java.lang.String name;
	public DuplicatePolicyName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public DuplicatePolicyName(java.lang.String name)
	{
		super(org.omg.CosTrading.DuplicatePolicyNameHelper.id());
		this.name = name;
	}
}
