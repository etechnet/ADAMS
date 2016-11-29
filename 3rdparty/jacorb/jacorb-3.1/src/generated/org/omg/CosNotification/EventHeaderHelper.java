package org.omg.CosNotification;


/**
 * Generated from IDL struct "EventHeader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EventHeaderHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EventHeaderHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.EventHeaderHelper.id(),"EventHeader",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("fixed_header", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.FixedEventHeaderHelper.id(),"FixedEventHeader",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("event_type", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.EventTypeHelper.id(),"EventType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("domain_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("event_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("variable_header", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.OptionalHeaderFieldsHelper.id(), "OptionalHeaderFields",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertySeqHelper.id(), "PropertySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)})))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotification.EventHeader s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotification.EventHeader extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotification/EventHeader:1.0";
	}
	public static org.omg.CosNotification.EventHeader read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosNotification.EventHeader result = new org.omg.CosNotification.EventHeader();
		result.fixed_header=org.omg.CosNotification.FixedEventHeaderHelper.read(in);
		result.variable_header = org.omg.CosNotification.PropertySeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotification.EventHeader s)
	{
		org.omg.CosNotification.FixedEventHeaderHelper.write(out,s.fixed_header);
		org.omg.CosNotification.PropertySeqHelper.write(out,s.variable_header);
	}
}
