package org.omg.PortableGroup;

/**
 * Generated from IDL struct "FactoryInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class FactoryInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public FactoryInfo(){}
	public org.omg.PortableGroup.GenericFactory the_factory;
	public org.omg.CosNaming.NameComponent[] the_location;
	public org.omg.PortableGroup.Property[] the_criteria;
	public FactoryInfo(org.omg.PortableGroup.GenericFactory the_factory, org.omg.CosNaming.NameComponent[] the_location, org.omg.PortableGroup.Property[] the_criteria)
	{
		this.the_factory = the_factory;
		this.the_location = the_location;
		this.the_criteria = the_criteria;
	}
}
