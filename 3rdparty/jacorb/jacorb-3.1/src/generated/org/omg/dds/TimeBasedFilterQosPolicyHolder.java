package org.omg.dds;

/**
 * Generated from IDL struct "TimeBasedFilterQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TimeBasedFilterQosPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.TimeBasedFilterQosPolicy value;

	public TimeBasedFilterQosPolicyHolder ()
	{
	}
	public TimeBasedFilterQosPolicyHolder(final org.omg.dds.TimeBasedFilterQosPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.TimeBasedFilterQosPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.TimeBasedFilterQosPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.TimeBasedFilterQosPolicyHelper.write(_out, value);
	}
}
