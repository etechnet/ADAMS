package org.omg.SSLIOP;

/**
 * Generated from IDL struct "SSL".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class SSL
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SSL(){}
	public short target_supports;
	public short target_requires;
	public short port;
	public SSL(short target_supports, short target_requires, short port)
	{
		this.target_supports = target_supports;
		this.target_requires = target_requires;
		this.port = port;
	}
}
