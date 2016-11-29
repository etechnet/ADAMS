package org.omg.ATLAS;

/**
 * Generated from IDL struct "ATLASProfile".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ATLASProfile
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ATLASProfile(){}
	public org.omg.ATLAS.ATLASLocator the_locator;
	public byte[] the_cache_id;
	public ATLASProfile(org.omg.ATLAS.ATLASLocator the_locator, byte[] the_cache_id)
	{
		this.the_locator = the_locator;
		this.the_cache_id = the_cache_id;
	}
}
