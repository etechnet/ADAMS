package org.omg.SecurityReplaceable;

/**
 * Generated from IDL interface "ServerSecurityContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class ServerSecurityContextHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServerSecurityContext value;
	public ServerSecurityContextHolder()
	{
	}
	public ServerSecurityContextHolder (final ServerSecurityContext initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServerSecurityContextHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServerSecurityContextHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServerSecurityContextHelper.write (_out,value);
	}
}
