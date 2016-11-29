package org.omg.MIOP;

/**
 * Generated from IDL alias "UniqueId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class UniqueIdHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public UniqueIdHolder ()
	{
	}
	public UniqueIdHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return UniqueIdHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = UniqueIdHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		UniqueIdHelper.write (out,value);
	}
}
