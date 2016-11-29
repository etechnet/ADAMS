package org.omg.PortableGroup;

/**
 * Generated from IDL alias "IDs".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class IDsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[][] value;

	public IDsHolder ()
	{
	}
	public IDsHolder (final byte[][] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return IDsHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IDsHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		IDsHelper.write (out,value);
	}
}
