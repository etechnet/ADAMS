package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL struct "OfferInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OfferInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public OfferInfo(){}
	public org.omg.CORBA.Object reference;
	public java.lang.String type;
	public org.omg.CosTrading.Property[] properties;
	public OfferInfo(org.omg.CORBA.Object reference, java.lang.String type, org.omg.CosTrading.Property[] properties)
	{
		this.reference = reference;
		this.type = type;
		this.properties = properties;
	}
}
