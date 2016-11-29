package org.omg.CosTrading.LookupPackage;

/**
 * Generated from IDL exception "InvalidPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InvalidPolicyValue
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InvalidPolicyValue()
	{
		super(org.omg.CosTrading.LookupPackage.InvalidPolicyValueHelper.id());
	}

	public org.omg.CosTrading.Policy the_policy;
	public InvalidPolicyValue(java.lang.String _reason,org.omg.CosTrading.Policy the_policy)
	{
		super(_reason);
		this.the_policy = the_policy;
	}
	public InvalidPolicyValue(org.omg.CosTrading.Policy the_policy)
	{
		super(org.omg.CosTrading.LookupPackage.InvalidPolicyValueHelper.id());
		this.the_policy = the_policy;
	}
}
