package org.jacorb.imr;

/**
 * Generated from IDL struct "POAInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class POAInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.POAInfo value;

	public POAInfoHolder ()
	{
	}
	public POAInfoHolder(final org.jacorb.imr.POAInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.POAInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.POAInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.POAInfoHelper.write(_out, value);
	}
}
