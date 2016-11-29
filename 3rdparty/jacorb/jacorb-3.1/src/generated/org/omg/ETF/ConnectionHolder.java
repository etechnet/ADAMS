package org.omg.ETF;

/**
 * Generated from IDL interface "Connection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ConnectionHolder	implements org.omg.CORBA.portable.Streamable{
	 public Connection value;
	public ConnectionHolder()
	{
	}
	public ConnectionHolder (final Connection initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ConnectionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConnectionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ConnectionHelper.write (_out,value);
	}
}
