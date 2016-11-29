package org.omg.Security;


/**
 * Generated from IDL struct "AttributeType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AttributeTypeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AttributeTypeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.AttributeTypeHelper.id(),"AttributeType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("attribute_family", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.ExtensibleFamilyHelper.id(),"ExtensibleFamily",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("family_definer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("family", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)}), null),new org.omg.CORBA.StructMember("attribute_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.SecurityAttributeTypeHelper.id(), "SecurityAttributeType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.AttributeType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.AttributeType extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/AttributeType:1.0";
	}
	public static org.omg.Security.AttributeType read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.AttributeType result = new org.omg.Security.AttributeType();
		result.attribute_family=org.omg.Security.ExtensibleFamilyHelper.read(in);
		result.attribute_type=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.AttributeType s)
	{
		org.omg.Security.ExtensibleFamilyHelper.write(out,s.attribute_family);
		out.write_ulong(s.attribute_type);
	}
}
