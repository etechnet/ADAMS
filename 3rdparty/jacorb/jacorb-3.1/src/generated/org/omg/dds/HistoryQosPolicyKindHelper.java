package org.omg.dds;
/**
 * Generated from IDL enum "HistoryQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class HistoryQosPolicyKindHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(HistoryQosPolicyKindHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.HistoryQosPolicyKindHelper.id(),"HistoryQosPolicyKind",new String[]{"KEEP_LAST_HISTORY_QOS","KEEP_ALL_HISTORY_QOS"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.HistoryQosPolicyKind s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.HistoryQosPolicyKind extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/dds/HistoryQosPolicyKind:1.0";
	}
	public static HistoryQosPolicyKind read (final org.omg.CORBA.portable.InputStream in)
	{
		return HistoryQosPolicyKind.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final HistoryQosPolicyKind s)
	{
		out.write_long(s.value());
	}
}
