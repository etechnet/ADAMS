package org.jacorb.imr;

/**
 * Generated from IDL interface "ServerStartupDaemon".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ServerStartupDaemonHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServerStartupDaemon value;
	public ServerStartupDaemonHolder()
	{
	}
	public ServerStartupDaemonHolder (final ServerStartupDaemon initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServerStartupDaemonHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServerStartupDaemonHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServerStartupDaemonHelper.write (_out,value);
	}
}
