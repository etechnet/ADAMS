package org.omg.dds;

/**
 * Generated from IDL struct "RequestedDeadlineMissedStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class RequestedDeadlineMissedStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.RequestedDeadlineMissedStatus value;

	public RequestedDeadlineMissedStatusHolder ()
	{
	}
	public RequestedDeadlineMissedStatusHolder(final org.omg.dds.RequestedDeadlineMissedStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.RequestedDeadlineMissedStatusHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.RequestedDeadlineMissedStatusHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.RequestedDeadlineMissedStatusHelper.write(_out, value);
	}
}
