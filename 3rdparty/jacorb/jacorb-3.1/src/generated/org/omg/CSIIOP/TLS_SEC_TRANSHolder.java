package org.omg.CSIIOP;

/**
 * Generated from IDL struct "TLS_SEC_TRANS".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TLS_SEC_TRANSHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.TLS_SEC_TRANS value;

	public TLS_SEC_TRANSHolder ()
	{
	}
	public TLS_SEC_TRANSHolder(final org.omg.CSIIOP.TLS_SEC_TRANS initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSIIOP.TLS_SEC_TRANSHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSIIOP.TLS_SEC_TRANSHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSIIOP.TLS_SEC_TRANSHelper.write(_out, value);
	}
}
