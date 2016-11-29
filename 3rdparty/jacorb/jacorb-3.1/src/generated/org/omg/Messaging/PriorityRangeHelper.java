package org.omg.Messaging;


/**
 * Generated from IDL struct "PriorityRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PriorityRangeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PriorityRangeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Messaging.PriorityRangeHelper.id(),"PriorityRange",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("min", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Messaging.PriorityHelper.id(), "Priority",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null),new org.omg.CORBA.StructMember("max", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Messaging.PriorityHelper.id(), "Priority",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Messaging.PriorityRange s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Messaging.PriorityRange extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Messaging/PriorityRange:1.0";
	}
	public static org.omg.Messaging.PriorityRange read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Messaging.PriorityRange result = new org.omg.Messaging.PriorityRange();
		result.min=in.read_short();
		result.max=in.read_short();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Messaging.PriorityRange s)
	{
		out.write_short(s.min);
		out.write_short(s.max);
	}
}
