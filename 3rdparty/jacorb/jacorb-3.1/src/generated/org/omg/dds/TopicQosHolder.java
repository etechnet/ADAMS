package org.omg.dds;

/**
 * Generated from IDL struct "TopicQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TopicQosHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.TopicQos value;

	public TopicQosHolder ()
	{
	}
	public TopicQosHolder(final org.omg.dds.TopicQos initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.TopicQosHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.TopicQosHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.TopicQosHelper.write(_out, value);
	}
}
