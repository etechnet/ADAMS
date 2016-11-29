package org.omg.PortableGroup;

/**
 * Generated from IDL exception "ObjectNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ObjectNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.ObjectNotFound value;

	public ObjectNotFoundHolder ()
	{
	}
	public ObjectNotFoundHolder(final org.omg.PortableGroup.ObjectNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.ObjectNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.ObjectNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.ObjectNotFoundHelper.write(_out, value);
	}
}
