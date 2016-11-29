package org.omg.CosTrading.LinkPackage;

/**
 * Generated from IDL struct "LinkInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class LinkInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LinkInfo(){}
	public org.omg.CosTrading.Lookup target;
	public org.omg.CosTrading.Register target_reg;
	public org.omg.CosTrading.FollowOption def_pass_on_follow_rule;
	public org.omg.CosTrading.FollowOption limiting_follow_rule;
	public LinkInfo(org.omg.CosTrading.Lookup target, org.omg.CosTrading.Register target_reg, org.omg.CosTrading.FollowOption def_pass_on_follow_rule, org.omg.CosTrading.FollowOption limiting_follow_rule)
	{
		this.target = target;
		this.target_reg = target_reg;
		this.def_pass_on_follow_rule = def_pass_on_follow_rule;
		this.limiting_follow_rule = limiting_follow_rule;
	}
}
