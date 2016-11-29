package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "InterfaceTypeMismatch".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InterfaceTypeMismatch
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InterfaceTypeMismatch()
	{
		super(org.omg.CosTrading.RegisterPackage.InterfaceTypeMismatchHelper.id());
	}

	public java.lang.String type;
	public org.omg.CORBA.Object reference;
	public InterfaceTypeMismatch(java.lang.String _reason,java.lang.String type, org.omg.CORBA.Object reference)
	{
		super(_reason);
		this.type = type;
		this.reference = reference;
	}
	public InterfaceTypeMismatch(java.lang.String type, org.omg.CORBA.Object reference)
	{
		super(org.omg.CosTrading.RegisterPackage.InterfaceTypeMismatchHelper.id());
		this.type = type;
		this.reference = reference;
	}
}
