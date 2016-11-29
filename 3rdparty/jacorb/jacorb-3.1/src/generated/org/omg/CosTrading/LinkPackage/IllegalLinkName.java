package org.omg.CosTrading.LinkPackage;

/**
 * Generated from IDL exception "IllegalLinkName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalLinkName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalLinkName()
	{
		super(org.omg.CosTrading.LinkPackage.IllegalLinkNameHelper.id());
	}

	public java.lang.String name;
	public IllegalLinkName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public IllegalLinkName(java.lang.String name)
	{
		super(org.omg.CosTrading.LinkPackage.IllegalLinkNameHelper.id());
		this.name = name;
	}
}
