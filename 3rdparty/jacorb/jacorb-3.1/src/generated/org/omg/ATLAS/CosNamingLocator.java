package org.omg.ATLAS;

/**
 * Generated from IDL struct "CosNamingLocator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CosNamingLocator
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CosNamingLocator(){}
	public org.omg.CosNaming.NamingContext name_service;
	public org.omg.CosNaming.NameComponent[] the_name;
	public CosNamingLocator(org.omg.CosNaming.NamingContext name_service, org.omg.CosNaming.NameComponent[] the_name)
	{
		this.name_service = name_service;
		this.the_name = the_name;
	}
}
