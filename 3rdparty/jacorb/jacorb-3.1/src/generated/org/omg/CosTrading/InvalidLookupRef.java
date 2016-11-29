package org.omg.CosTrading;

/**
 * Generated from IDL exception "InvalidLookupRef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InvalidLookupRef
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InvalidLookupRef()
	{
		super(org.omg.CosTrading.InvalidLookupRefHelper.id());
	}

	public org.omg.CosTrading.Lookup target;
	public InvalidLookupRef(java.lang.String _reason,org.omg.CosTrading.Lookup target)
	{
		super(_reason);
		this.target = target;
	}
	public InvalidLookupRef(org.omg.CosTrading.Lookup target)
	{
		super(org.omg.CosTrading.InvalidLookupRefHelper.id());
		this.target = target;
	}
}
