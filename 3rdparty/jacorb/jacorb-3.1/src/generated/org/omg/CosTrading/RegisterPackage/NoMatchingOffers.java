package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "NoMatchingOffers".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NoMatchingOffers
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NoMatchingOffers()
	{
		super(org.omg.CosTrading.RegisterPackage.NoMatchingOffersHelper.id());
	}

	public java.lang.String constr;
	public NoMatchingOffers(java.lang.String _reason,java.lang.String constr)
	{
		super(_reason);
		this.constr = constr;
	}
	public NoMatchingOffers(java.lang.String constr)
	{
		super(org.omg.CosTrading.RegisterPackage.NoMatchingOffersHelper.id());
		this.constr = constr;
	}
}
