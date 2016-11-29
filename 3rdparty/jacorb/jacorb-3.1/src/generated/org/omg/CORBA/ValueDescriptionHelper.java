package org.omg.CORBA;


/**
 * Generated from IDL struct "ValueDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ValueDescriptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ValueDescriptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.ValueDescriptionHelper.id(),"ValueDescription",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.IdentifierHelper.id(), "Identifier",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("is_abstract", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("is_custom", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("defined_in", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("version", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.VersionSpecHelper.id(), "VersionSpec",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("supported_interfaces", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdSeqHelper.id(), "RepositoryIdSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.RepositoryIdHelper.type())), null),new org.omg.CORBA.StructMember("abstract_base_values", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdSeqHelper.id(), "RepositoryIdSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.RepositoryIdHelper.type())), null),new org.omg.CORBA.StructMember("is_truncatable", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("base_value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.ValueDescription s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.ValueDescription extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CORBA/ValueDescription:1.0";
	}
	public static org.omg.CORBA.ValueDescription read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CORBA.ValueDescription result = new org.omg.CORBA.ValueDescription();
		result.name=in.read_string();
		result.id=in.read_string();
		result.is_abstract=in.read_boolean();
		result.is_custom=in.read_boolean();
		result.defined_in=in.read_string();
		result.version=in.read_string();
		result.supported_interfaces = org.omg.CORBA.RepositoryIdSeqHelper.read(in);
		result.abstract_base_values = org.omg.CORBA.RepositoryIdSeqHelper.read(in);
		result.is_truncatable=in.read_boolean();
		result.base_value=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CORBA.ValueDescription s)
	{
		java.lang.String tmpResult642 = s.name;
out.write_string( tmpResult642 );
		java.lang.String tmpResult643 = s.id;
out.write_string( tmpResult643 );
		out.write_boolean(s.is_abstract);
		out.write_boolean(s.is_custom);
		java.lang.String tmpResult644 = s.defined_in;
out.write_string( tmpResult644 );
		java.lang.String tmpResult645 = s.version;
out.write_string( tmpResult645 );
		org.omg.CORBA.RepositoryIdSeqHelper.write(out,s.supported_interfaces);
		org.omg.CORBA.RepositoryIdSeqHelper.write(out,s.abstract_base_values);
		out.write_boolean(s.is_truncatable);
		java.lang.String tmpResult646 = s.base_value;
out.write_string( tmpResult646 );
	}
}
