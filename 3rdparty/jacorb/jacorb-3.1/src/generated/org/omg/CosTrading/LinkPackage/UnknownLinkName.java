package org.omg.CosTrading.LinkPackage;

/**
 * Generated from IDL exception "UnknownLinkName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnknownLinkName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UnknownLinkName()
	{
		super(org.omg.CosTrading.LinkPackage.UnknownLinkNameHelper.id());
	}

	public java.lang.String name;
	public UnknownLinkName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public UnknownLinkName(java.lang.String name)
	{
		super(org.omg.CosTrading.LinkPackage.UnknownLinkNameHelper.id());
		this.name = name;
	}
}
