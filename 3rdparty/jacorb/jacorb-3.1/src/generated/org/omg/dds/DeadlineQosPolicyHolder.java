package org.omg.dds;

/**
 * Generated from IDL struct "DeadlineQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DeadlineQosPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.DeadlineQosPolicy value;

	public DeadlineQosPolicyHolder ()
	{
	}
	public DeadlineQosPolicyHolder(final org.omg.dds.DeadlineQosPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.DeadlineQosPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.DeadlineQosPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.DeadlineQosPolicyHelper.write(_out, value);
	}
}
