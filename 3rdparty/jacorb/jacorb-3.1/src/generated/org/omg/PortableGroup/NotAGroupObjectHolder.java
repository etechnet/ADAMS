package org.omg.PortableGroup;

/**
 * Generated from IDL exception "NotAGroupObject".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NotAGroupObjectHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.NotAGroupObject value;

	public NotAGroupObjectHolder ()
	{
	}
	public NotAGroupObjectHolder(final org.omg.PortableGroup.NotAGroupObject initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.NotAGroupObjectHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.NotAGroupObjectHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.NotAGroupObjectHelper.write(_out, value);
	}
}
