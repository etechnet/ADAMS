package org.omg.CosTime;
/**
 * Generated from IDL enum "TimeComparison".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TimeComparisonHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TimeComparisonHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosTime.TimeComparisonHelper.id(),"TimeComparison",new String[]{"TCEqualTo","TCLessThan","TCGreaterThan","TCIndeterminate"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTime.TimeComparison s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTime.TimeComparison extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTime/TimeComparison:1.0";
	}
	public static TimeComparison read (final org.omg.CORBA.portable.InputStream in)
	{
		return TimeComparison.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final TimeComparison s)
	{
		out.write_long(s.value());
	}
}
