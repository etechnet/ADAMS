package org.omg.Security;


/**
 * Generated from IDL struct "AuditEventType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuditEventTypeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AuditEventTypeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.AuditEventTypeHelper.id(),"AuditEventType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("event_family", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.ExtensibleFamilyHelper.id(),"ExtensibleFamily",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("family_definer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("family", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)}), null),new org.omg.CORBA.StructMember("event_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.EventTypeHelper.id(), "EventType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.AuditEventType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.AuditEventType extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/AuditEventType:1.0";
	}
	public static org.omg.Security.AuditEventType read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.AuditEventType result = new org.omg.Security.AuditEventType();
		result.event_family=org.omg.Security.ExtensibleFamilyHelper.read(in);
		result.event_type=in.read_ushort();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.AuditEventType s)
	{
		org.omg.Security.ExtensibleFamilyHelper.write(out,s.event_family);
		out.write_ushort(s.event_type);
	}
}
