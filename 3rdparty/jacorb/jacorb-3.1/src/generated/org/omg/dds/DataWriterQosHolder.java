package org.omg.dds;

/**
 * Generated from IDL struct "DataWriterQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DataWriterQosHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.DataWriterQos value;

	public DataWriterQosHolder ()
	{
	}
	public DataWriterQosHolder(final org.omg.dds.DataWriterQos initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.DataWriterQosHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.DataWriterQosHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.DataWriterQosHelper.write(_out, value);
	}
}
