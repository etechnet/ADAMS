package org.omg.CORBA;

/**
 * Generated from IDL struct "ExtInitializer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtInitializerHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ExtInitializer value;

	public ExtInitializerHolder ()
	{
	}
	public ExtInitializerHolder(final org.omg.CORBA.ExtInitializer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.ExtInitializerHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.ExtInitializerHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.ExtInitializerHelper.write(_out, value);
	}
}
