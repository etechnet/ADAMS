package org.omg.dds;

/**
 * Generated from IDL struct "PresentationQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PresentationQosPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.PresentationQosPolicy value;

	public PresentationQosPolicyHolder ()
	{
	}
	public PresentationQosPolicyHolder(final org.omg.dds.PresentationQosPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.PresentationQosPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.PresentationQosPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.PresentationQosPolicyHelper.write(_out, value);
	}
}
