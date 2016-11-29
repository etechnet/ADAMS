package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "ProxyOfferId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProxyOfferId
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ProxyOfferId()
	{
		super(org.omg.CosTrading.RegisterPackage.ProxyOfferIdHelper.id());
	}

	public java.lang.String id;
	public ProxyOfferId(java.lang.String _reason,java.lang.String id)
	{
		super(_reason);
		this.id = id;
	}
	public ProxyOfferId(java.lang.String id)
	{
		super(org.omg.CosTrading.RegisterPackage.ProxyOfferIdHelper.id());
		this.id = id;
	}
}
