package org.omg.IOP;

/**
 * Generated from IDL struct "TaggedProfile".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class TaggedProfile
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TaggedProfile(){}
	public int tag;
	public byte[] profile_data;
	public TaggedProfile(int tag, byte[] profile_data)
	{
		this.tag = tag;
		this.profile_data = profile_data;
	}
}
