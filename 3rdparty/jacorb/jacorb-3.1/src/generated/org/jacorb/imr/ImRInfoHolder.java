package org.jacorb.imr;

/**
 * Generated from IDL struct "ImRInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ImRInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.ImRInfo value;

	public ImRInfoHolder ()
	{
	}
	public ImRInfoHolder(final org.jacorb.imr.ImRInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.ImRInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.ImRInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.ImRInfoHelper.write(_out, value);
	}
}
