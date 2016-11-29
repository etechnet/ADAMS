package org.omg.dds;

/**
 * Generated from IDL struct "DataReaderQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DataReaderQosHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.DataReaderQos value;

	public DataReaderQosHolder ()
	{
	}
	public DataReaderQosHolder(final org.omg.dds.DataReaderQos initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.DataReaderQosHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.DataReaderQosHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.DataReaderQosHelper.write(_out, value);
	}
}
