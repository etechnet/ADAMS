package org.omg.dds;


/**
 * Generated from IDL interface "DataReader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DataReaderHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DataReaderHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/dds/DataReader:1.0", "DataReader");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.DataReader s)
	{
			any.insert_Object(s);
	}
	public static org.omg.dds.DataReader extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/dds/DataReader:1.0";
	}
	public static DataReader read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.dds._DataReaderStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.dds.DataReader s)
	{
		_out.write_Object(s);
	}
	public static org.omg.dds.DataReader narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.DataReader)
		{
			return (org.omg.dds.DataReader)obj;
		}
		else if (obj._is_a("IDL:omg.org/dds/DataReader:1.0"))
		{
			org.omg.dds._DataReaderStub stub;
			stub = new org.omg.dds._DataReaderStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.dds.DataReader unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.DataReader)
		{
			return (org.omg.dds.DataReader)obj;
		}
		else
		{
			org.omg.dds._DataReaderStub stub;
			stub = new org.omg.dds._DataReaderStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
