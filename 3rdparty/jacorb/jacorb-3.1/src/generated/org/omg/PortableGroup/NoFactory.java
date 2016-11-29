package org.omg.PortableGroup;

/**
 * Generated from IDL exception "NoFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NoFactory
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NoFactory()
	{
		super(org.omg.PortableGroup.NoFactoryHelper.id());
	}

	public org.omg.CosNaming.NameComponent[] the_location;
	public java.lang.String type_id;
	public NoFactory(java.lang.String _reason,org.omg.CosNaming.NameComponent[] the_location, java.lang.String type_id)
	{
		super(_reason);
		this.the_location = the_location;
		this.type_id = type_id;
	}
	public NoFactory(org.omg.CosNaming.NameComponent[] the_location, java.lang.String type_id)
	{
		super(org.omg.PortableGroup.NoFactoryHelper.id());
		this.the_location = the_location;
		this.type_id = type_id;
	}
}
