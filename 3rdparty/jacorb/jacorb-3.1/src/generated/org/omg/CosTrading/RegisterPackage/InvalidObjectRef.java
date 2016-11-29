package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "InvalidObjectRef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InvalidObjectRef
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InvalidObjectRef()
	{
		super(org.omg.CosTrading.RegisterPackage.InvalidObjectRefHelper.id());
	}

	public org.omg.CORBA.Object ref;
	public InvalidObjectRef(java.lang.String _reason,org.omg.CORBA.Object ref)
	{
		super(_reason);
		this.ref = ref;
	}
	public InvalidObjectRef(org.omg.CORBA.Object ref)
	{
		super(org.omg.CosTrading.RegisterPackage.InvalidObjectRefHelper.id());
		this.ref = ref;
	}
}
