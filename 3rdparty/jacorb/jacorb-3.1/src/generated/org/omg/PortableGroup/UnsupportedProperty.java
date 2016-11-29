package org.omg.PortableGroup;

/**
 * Generated from IDL exception "UnsupportedProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class UnsupportedProperty
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UnsupportedProperty()
	{
		super(org.omg.PortableGroup.UnsupportedPropertyHelper.id());
	}

	public org.omg.CosNaming.NameComponent[] nam;
	public UnsupportedProperty(java.lang.String _reason,org.omg.CosNaming.NameComponent[] nam)
	{
		super(_reason);
		this.nam = nam;
	}
	public UnsupportedProperty(org.omg.CosNaming.NameComponent[] nam)
	{
		super(org.omg.PortableGroup.UnsupportedPropertyHelper.id());
		this.nam = nam;
	}
}
