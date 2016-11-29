package org.omg.CSIIOP;

/**
 * Generated from IDL alias "ServiceSpecificName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ServiceSpecificNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public ServiceSpecificNameHolder ()
	{
	}
	public ServiceSpecificNameHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ServiceSpecificNameHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServiceSpecificNameHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ServiceSpecificNameHelper.write (out,value);
	}
}
