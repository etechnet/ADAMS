package org.omg.PortableServer;

/**
 * Generated from IDL interface "ServantLocator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ServantLocatorHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServantLocator value;
	public ServantLocatorHolder()
	{
	}
	public ServantLocatorHolder (final ServantLocator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServantLocatorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServantLocatorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServantLocatorHelper.write (_out,value);
	}
}
