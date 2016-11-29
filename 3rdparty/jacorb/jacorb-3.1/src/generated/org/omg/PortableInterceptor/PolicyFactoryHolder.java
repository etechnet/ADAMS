package org.omg.PortableInterceptor;

/**
 * Generated from IDL interface "PolicyFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PolicyFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public PolicyFactory value;
	public PolicyFactoryHolder()
	{
	}
	public PolicyFactoryHolder (final PolicyFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PolicyFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PolicyFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PolicyFactoryHelper.write (_out,value);
	}
}
