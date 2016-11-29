package org.omg.CosNotifyFilter;


/**
 * Generated from IDL struct "ConstraintInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ConstraintInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyFilter.ConstraintInfoHelper.id(),"ConstraintInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("constraint_expression", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyFilter.ConstraintExpHelper.id(),"ConstraintExp",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("event_types", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.EventTypeSeqHelper.id(), "EventTypeSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.EventTypeHelper.id(),"EventType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("domain_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)}))), null),new org.omg.CORBA.StructMember("constraint_expr", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("constraint_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotifyFilter.ConstraintIDHelper.id(), "ConstraintID",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyFilter.ConstraintInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotifyFilter.ConstraintInfo extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotifyFilter/ConstraintInfo:1.0";
	}
	public static org.omg.CosNotifyFilter.ConstraintInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosNotifyFilter.ConstraintInfo result = new org.omg.CosNotifyFilter.ConstraintInfo();
		result.constraint_expression=org.omg.CosNotifyFilter.ConstraintExpHelper.read(in);
		result.constraint_id=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotifyFilter.ConstraintInfo s)
	{
		org.omg.CosNotifyFilter.ConstraintExpHelper.write(out,s.constraint_expression);
		out.write_long(s.constraint_id);
	}
}
