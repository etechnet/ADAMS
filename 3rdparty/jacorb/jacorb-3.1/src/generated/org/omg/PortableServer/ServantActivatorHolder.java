package org.omg.PortableServer;

/**
 * Generated from IDL interface "ServantActivator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ServantActivatorHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServantActivator value;
	public ServantActivatorHolder()
	{
	}
	public ServantActivatorHolder (final ServantActivator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServantActivatorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServantActivatorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServantActivatorHelper.write (_out,value);
	}
}
