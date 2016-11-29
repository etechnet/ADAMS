package org.omg.CosEventChannelAdmin;

/**
 * Generated from IDL exception "TypeError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TypeErrorHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosEventChannelAdmin.TypeError value;

	public TypeErrorHolder ()
	{
	}
	public TypeErrorHolder(final org.omg.CosEventChannelAdmin.TypeError initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosEventChannelAdmin.TypeErrorHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosEventChannelAdmin.TypeErrorHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosEventChannelAdmin.TypeErrorHelper.write(_out, value);
	}
}
