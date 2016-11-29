package org.jacorb.imr;

/**
 * Generated from IDL alias "ServerInfoSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ServerInfoSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.ServerInfo[] value;

	public ServerInfoSeqHolder ()
	{
	}
	public ServerInfoSeqHolder (final org.jacorb.imr.ServerInfo[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ServerInfoSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServerInfoSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ServerInfoSeqHelper.write (out,value);
	}
}
