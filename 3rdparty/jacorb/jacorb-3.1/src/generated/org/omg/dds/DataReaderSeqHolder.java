package org.omg.dds;

/**
 * Generated from IDL alias "DataReaderSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DataReaderSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.DataReader[] value;

	public DataReaderSeqHolder ()
	{
	}
	public DataReaderSeqHolder (final org.omg.dds.DataReader[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DataReaderSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DataReaderSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DataReaderSeqHelper.write (out,value);
	}
}
