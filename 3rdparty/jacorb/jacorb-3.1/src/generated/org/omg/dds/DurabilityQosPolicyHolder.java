package org.omg.dds;

/**
 * Generated from IDL struct "DurabilityQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DurabilityQosPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.DurabilityQosPolicy value;

	public DurabilityQosPolicyHolder ()
	{
	}
	public DurabilityQosPolicyHolder(final org.omg.dds.DurabilityQosPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.DurabilityQosPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.DurabilityQosPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.DurabilityQosPolicyHelper.write(_out, value);
	}
}
