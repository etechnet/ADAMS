package org.omg.dds;

/**
 * Generated from IDL struct "RequestedIncompatibleQosStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class RequestedIncompatibleQosStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.RequestedIncompatibleQosStatus value;

	public RequestedIncompatibleQosStatusHolder ()
	{
	}
	public RequestedIncompatibleQosStatusHolder(final org.omg.dds.RequestedIncompatibleQosStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.RequestedIncompatibleQosStatusHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.RequestedIncompatibleQosStatusHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.RequestedIncompatibleQosStatusHelper.write(_out, value);
	}
}
