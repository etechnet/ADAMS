package org.omg.PortableGroup;

/**
 * Generated from IDL exception "InvalidProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InvalidPropertyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.InvalidProperty value;

	public InvalidPropertyHolder ()
	{
	}
	public InvalidPropertyHolder(final org.omg.PortableGroup.InvalidProperty initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.InvalidPropertyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.InvalidPropertyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.InvalidPropertyHelper.write(_out, value);
	}
}
