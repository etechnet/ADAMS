package org.omg.Security;


/**
 * Generated from IDL struct "SecAttribute".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecAttributeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SecAttributeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.SecAttributeHelper.id(),"SecAttribute",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("attribute_type", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.AttributeTypeHelper.id(),"AttributeType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("attribute_family", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.ExtensibleFamilyHelper.id(),"ExtensibleFamily",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("family_definer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("family", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)}), null),new org.omg.CORBA.StructMember("attribute_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.SecurityAttributeTypeHelper.id(), "SecurityAttributeType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)}), null),new org.omg.CORBA.StructMember("defining_authority", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.OpaqueHelper.id(), "Opaque",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.OpaqueHelper.id(), "Opaque",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.SecAttribute s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.SecAttribute extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/SecAttribute:1.0";
	}
	public static org.omg.Security.SecAttribute read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.SecAttribute result = new org.omg.Security.SecAttribute();
		result.attribute_type=org.omg.Security.AttributeTypeHelper.read(in);
		result.defining_authority = org.omg.Security.OpaqueHelper.read(in);
		result.value = org.omg.Security.OpaqueHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.SecAttribute s)
	{
		org.omg.Security.AttributeTypeHelper.write(out,s.attribute_type);
		org.omg.Security.OpaqueHelper.write(out,s.defining_authority);
		org.omg.Security.OpaqueHelper.write(out,s.value);
	}
}
