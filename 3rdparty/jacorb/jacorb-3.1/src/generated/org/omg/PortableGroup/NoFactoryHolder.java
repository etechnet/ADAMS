package org.omg.PortableGroup;

/**
 * Generated from IDL exception "NoFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NoFactoryHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.NoFactory value;

	public NoFactoryHolder ()
	{
	}
	public NoFactoryHolder(final org.omg.PortableGroup.NoFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.NoFactoryHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.NoFactoryHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.NoFactoryHelper.write(_out, value);
	}
}
