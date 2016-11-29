package org.omg.PortableGroup;

/**
 * Generated from IDL exception "BadReplicationStyle".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class BadReplicationStyleHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.BadReplicationStyle value;

	public BadReplicationStyleHolder ()
	{
	}
	public BadReplicationStyleHolder(final org.omg.PortableGroup.BadReplicationStyle initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.BadReplicationStyleHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.BadReplicationStyleHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.BadReplicationStyleHelper.write(_out, value);
	}
}
