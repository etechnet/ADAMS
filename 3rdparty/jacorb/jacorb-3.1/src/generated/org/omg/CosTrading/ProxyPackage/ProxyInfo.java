package org.omg.CosTrading.ProxyPackage;

/**
 * Generated from IDL struct "ProxyInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProxyInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ProxyInfo(){}
	public java.lang.String type;
	public org.omg.CosTrading.Lookup target;
	public org.omg.CosTrading.Property[] properties;
	public boolean if_match_all;
	public java.lang.String recipe;
	public org.omg.CosTrading.Policy[] policies_to_pass_on;
	public ProxyInfo(java.lang.String type, org.omg.CosTrading.Lookup target, org.omg.CosTrading.Property[] properties, boolean if_match_all, java.lang.String recipe, org.omg.CosTrading.Policy[] policies_to_pass_on)
	{
		this.type = type;
		this.target = target;
		this.properties = properties;
		this.if_match_all = if_match_all;
		this.recipe = recipe;
		this.policies_to_pass_on = policies_to_pass_on;
	}
}
