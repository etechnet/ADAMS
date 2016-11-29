package org.omg.CSI;

/**
 * Generated from IDL alias "OIDList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class OIDListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[][] value;

	public OIDListHolder ()
	{
	}
	public OIDListHolder (final byte[][] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return OIDListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OIDListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		OIDListHelper.write (out,value);
	}
}
