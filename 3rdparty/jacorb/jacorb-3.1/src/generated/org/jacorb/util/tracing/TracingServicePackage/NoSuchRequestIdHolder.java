package org.jacorb.util.tracing.TracingServicePackage;

/**
 * Generated from IDL exception "NoSuchRequestId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class NoSuchRequestIdHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId value;

	public NoSuchRequestIdHolder ()
	{
	}
	public NoSuchRequestIdHolder(final org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestId initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestIdHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestIdHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.util.tracing.TracingServicePackage.NoSuchRequestIdHelper.write(_out, value);
	}
}
