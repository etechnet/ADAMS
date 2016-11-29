package org.omg.PortableGroup;

/**
 * Generated from IDL struct "Property".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class Property
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Property(){}
	public org.omg.CosNaming.NameComponent[] nam;
	public org.omg.CORBA.Any val;
	public Property(org.omg.CosNaming.NameComponent[] nam, org.omg.CORBA.Any val)
	{
		this.nam = nam;
		this.val = val;
	}
}
