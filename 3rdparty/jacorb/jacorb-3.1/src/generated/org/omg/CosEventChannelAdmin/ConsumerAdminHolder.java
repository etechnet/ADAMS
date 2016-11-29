package org.omg.CosEventChannelAdmin;

/**
 * Generated from IDL interface "ConsumerAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ConsumerAdminHolder	implements org.omg.CORBA.portable.Streamable{
	 public ConsumerAdmin value;
	public ConsumerAdminHolder()
	{
	}
	public ConsumerAdminHolder (final ConsumerAdmin initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ConsumerAdminHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConsumerAdminHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ConsumerAdminHelper.write (_out,value);
	}
}
