package org.omg.IOP;

/**
 * Generated from IDL struct "IOR".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class IOR
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IOR(){}
	public java.lang.String type_id = "";
	public org.omg.IOP.TaggedProfile[] profiles;
	public IOR(java.lang.String type_id, org.omg.IOP.TaggedProfile[] profiles)
	{
		this.type_id = type_id;
		this.profiles = profiles;
	}
}
