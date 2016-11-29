package org.omg.PortableServer;

/**
 * Generated from IDL interface "ServantManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ServantManagerHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServantManager value;
	public ServantManagerHolder()
	{
	}
	public ServantManagerHolder (final ServantManager initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServantManagerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServantManagerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServantManagerHelper.write (_out,value);
	}
}
