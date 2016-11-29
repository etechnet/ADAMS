package org.omg.CosNotifyFilter;


/**
 * Generated from IDL exception "InvalidConstraint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidConstraintHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidConstraintHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosNotifyFilter.InvalidConstraintHelper.id(),"InvalidConstraint",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("constr", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyFilter.ConstraintExpHelper.id(),"ConstraintExp",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("event_types", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.EventTypeSeqHelper.id(), "EventTypeSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.EventTypeHelper.id(),"EventType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("domain_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)}))), null),new org.omg.CORBA.StructMember("constraint_expr", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyFilter.InvalidConstraint s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotifyFilter.InvalidConstraint extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotifyFilter/InvalidConstraint:1.0";
	}
	public static org.omg.CosNotifyFilter.InvalidConstraint read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosNotifyFilter.ConstraintExp x0;
		x0=org.omg.CosNotifyFilter.ConstraintExpHelper.read(in);
		final org.omg.CosNotifyFilter.InvalidConstraint result = new org.omg.CosNotifyFilter.InvalidConstraint(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotifyFilter.InvalidConstraint s)
	{
		out.write_string(id());
		org.omg.CosNotifyFilter.ConstraintExpHelper.write(out,s.constr);
	}
}
