package org.omg.CSIIOP;

/**
 * Generated from IDL struct "ServiceConfiguration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ServiceConfiguration
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ServiceConfiguration(){}
	public int syntax;
	public byte[] name;
	public ServiceConfiguration(int syntax, byte[] name)
	{
		this.syntax = syntax;
		this.name = name;
	}
}
