package org.omg.PortableGroup;

/**
 * Generated from IDL alias "GroupIIOPProfile".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class GroupIIOPProfileHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public GroupIIOPProfileHolder ()
	{
	}
	public GroupIIOPProfileHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return GroupIIOPProfileHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = GroupIIOPProfileHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		GroupIIOPProfileHelper.write (out,value);
	}
}
