package org.omg.ATLAS;

/**
 * Generated from IDL alias "UTF8String".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class UTF8StringHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public UTF8StringHolder ()
	{
	}
	public UTF8StringHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return UTF8StringHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = UTF8StringHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		UTF8StringHelper.write (out,value);
	}
}
