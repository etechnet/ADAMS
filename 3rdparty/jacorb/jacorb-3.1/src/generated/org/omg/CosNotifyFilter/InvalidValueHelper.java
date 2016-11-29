package org.omg.CosNotifyFilter;


/**
 * Generated from IDL exception "InvalidValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidValueHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidValueHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosNotifyFilter.InvalidValueHelper.id(),"InvalidValue",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("constr", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyFilter.ConstraintExpHelper.id(),"ConstraintExp",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("event_types", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.EventTypeSeqHelper.id(), "EventTypeSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.EventTypeHelper.id(),"EventType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("domain_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)}))), null),new org.omg.CORBA.StructMember("constraint_expr", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyFilter.InvalidValue s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotifyFilter.InvalidValue extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotifyFilter/InvalidValue:1.0";
	}
	public static org.omg.CosNotifyFilter.InvalidValue read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosNotifyFilter.ConstraintExp x0;
		x0=org.omg.CosNotifyFilter.ConstraintExpHelper.read(in);
		org.omg.CORBA.Any x1;
		x1=in.read_any();
		final org.omg.CosNotifyFilter.InvalidValue result = new org.omg.CosNotifyFilter.InvalidValue(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotifyFilter.InvalidValue s)
	{
		out.write_string(id());
		org.omg.CosNotifyFilter.ConstraintExpHelper.write(out,s.constr);
		out.write_any(s.value);
	}
}
