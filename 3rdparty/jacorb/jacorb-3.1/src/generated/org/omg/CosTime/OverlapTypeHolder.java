package org.omg.CosTime;
/**
 * Generated from IDL enum "OverlapType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OverlapTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public OverlapType value;

	public OverlapTypeHolder ()
	{
	}
	public OverlapTypeHolder (final OverlapType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return OverlapTypeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OverlapTypeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		OverlapTypeHelper.write (out,value);
	}
}
