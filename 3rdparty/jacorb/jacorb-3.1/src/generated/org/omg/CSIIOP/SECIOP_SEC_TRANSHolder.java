package org.omg.CSIIOP;

/**
 * Generated from IDL struct "SECIOP_SEC_TRANS".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SECIOP_SEC_TRANSHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.SECIOP_SEC_TRANS value;

	public SECIOP_SEC_TRANSHolder ()
	{
	}
	public SECIOP_SEC_TRANSHolder(final org.omg.CSIIOP.SECIOP_SEC_TRANS initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSIIOP.SECIOP_SEC_TRANSHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSIIOP.SECIOP_SEC_TRANSHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSIIOP.SECIOP_SEC_TRANSHelper.write(_out, value);
	}
}
