package org.omg.PortableServer;

/**
 * Generated from IDL interface "LifespanPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class LifespanPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public LifespanPolicy value;
	public LifespanPolicyHolder()
	{
	}
	public LifespanPolicyHolder (final LifespanPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return LifespanPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LifespanPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LifespanPolicyHelper.write (_out,value);
	}
}
