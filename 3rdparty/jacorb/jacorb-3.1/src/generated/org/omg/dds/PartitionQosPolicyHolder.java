package org.omg.dds;

/**
 * Generated from IDL struct "PartitionQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PartitionQosPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.PartitionQosPolicy value;

	public PartitionQosPolicyHolder ()
	{
	}
	public PartitionQosPolicyHolder(final org.omg.dds.PartitionQosPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.PartitionQosPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.PartitionQosPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.PartitionQosPolicyHelper.write(_out, value);
	}
}
