package org.omg.TimeBase;


/**
 * Generated from IDL struct "IntervalT".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class IntervalTHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IntervalTHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.TimeBase.IntervalTHelper.id(),"IntervalT",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("lower_bound", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TimeTHelper.id(), "TimeT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null),new org.omg.CORBA.StructMember("upper_bound", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TimeTHelper.id(), "TimeT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.TimeBase.IntervalT s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.TimeBase.IntervalT extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/TimeBase/IntervalT:1.0";
	}
	public static org.omg.TimeBase.IntervalT read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.TimeBase.IntervalT result = new org.omg.TimeBase.IntervalT();
		result.lower_bound=in.read_ulonglong();
		result.upper_bound=in.read_ulonglong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.TimeBase.IntervalT s)
	{
		out.write_ulonglong(s.lower_bound);
		out.write_ulonglong(s.upper_bound);
	}
}
