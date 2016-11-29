package org.omg.dds;

/**
 * Generated from IDL interface "DataWriter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DataWriterHolder	implements org.omg.CORBA.portable.Streamable{
	 public DataWriter value;
	public DataWriterHolder()
	{
	}
	public DataWriterHolder (final DataWriter initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DataWriterHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DataWriterHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DataWriterHelper.write (_out,value);
	}
}
