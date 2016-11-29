package org.omg.CosTrading.LookupPackage;

/**
 * Generated from IDL exception "IllegalPolicyName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalPolicyName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalPolicyName()
	{
		super(org.omg.CosTrading.LookupPackage.IllegalPolicyNameHelper.id());
	}

	public java.lang.String name;
	public IllegalPolicyName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public IllegalPolicyName(java.lang.String name)
	{
		super(org.omg.CosTrading.LookupPackage.IllegalPolicyNameHelper.id());
		this.name = name;
	}
}
