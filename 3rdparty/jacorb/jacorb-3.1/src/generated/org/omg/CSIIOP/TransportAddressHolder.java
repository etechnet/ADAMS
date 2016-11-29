package org.omg.CSIIOP;

/**
 * Generated from IDL struct "TransportAddress".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransportAddressHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.TransportAddress value;

	public TransportAddressHolder ()
	{
	}
	public TransportAddressHolder(final org.omg.CSIIOP.TransportAddress initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSIIOP.TransportAddressHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSIIOP.TransportAddressHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSIIOP.TransportAddressHelper.write(_out, value);
	}
}
