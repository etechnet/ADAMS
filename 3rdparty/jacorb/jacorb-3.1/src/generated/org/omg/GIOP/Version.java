package org.omg.GIOP;

/**
 * Generated from IDL struct "Version".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class Version
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Version(){}
	public byte major;
	public byte minor;
	public Version(byte major, byte minor)
	{
		this.major = major;
		this.minor = minor;
	}
}
