package org.omg.PortableGroup;

/**
 * Generated from IDL exception "ObjectNotAdded".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ObjectNotAddedHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.ObjectNotAdded value;

	public ObjectNotAddedHolder ()
	{
	}
	public ObjectNotAddedHolder(final org.omg.PortableGroup.ObjectNotAdded initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.ObjectNotAddedHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.ObjectNotAddedHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.ObjectNotAddedHelper.write(_out, value);
	}
}
