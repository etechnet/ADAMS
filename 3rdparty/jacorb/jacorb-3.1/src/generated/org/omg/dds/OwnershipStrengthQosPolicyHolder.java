package org.omg.dds;

/**
 * Generated from IDL struct "OwnershipStrengthQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OwnershipStrengthQosPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.OwnershipStrengthQosPolicy value;

	public OwnershipStrengthQosPolicyHolder ()
	{
	}
	public OwnershipStrengthQosPolicyHolder(final org.omg.dds.OwnershipStrengthQosPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.OwnershipStrengthQosPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.OwnershipStrengthQosPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.OwnershipStrengthQosPolicyHelper.write(_out, value);
	}
}
