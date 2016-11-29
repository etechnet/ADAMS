package org.omg.PortableGroup;

/**
 * Generated from IDL exception "InvalidProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InvalidProperty
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InvalidProperty()
	{
		super(org.omg.PortableGroup.InvalidPropertyHelper.id());
	}

	public org.omg.CosNaming.NameComponent[] nam;
	public org.omg.CORBA.Any val;
	public InvalidProperty(java.lang.String _reason,org.omg.CosNaming.NameComponent[] nam, org.omg.CORBA.Any val)
	{
		super(_reason);
		this.nam = nam;
		this.val = val;
	}
	public InvalidProperty(org.omg.CosNaming.NameComponent[] nam, org.omg.CORBA.Any val)
	{
		super(org.omg.PortableGroup.InvalidPropertyHelper.id());
		this.nam = nam;
		this.val = val;
	}
}
