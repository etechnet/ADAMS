package org.omg.dds;

/**
 * Generated from IDL interface "DataReader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DataReaderHolder	implements org.omg.CORBA.portable.Streamable{
	 public DataReader value;
	public DataReaderHolder()
	{
	}
	public DataReaderHolder (final DataReader initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DataReaderHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DataReaderHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DataReaderHelper.write (_out,value);
	}
}
