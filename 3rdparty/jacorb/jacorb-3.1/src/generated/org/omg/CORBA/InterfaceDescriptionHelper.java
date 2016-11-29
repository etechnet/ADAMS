package org.omg.CORBA;


/**
 * Generated from IDL struct "InterfaceDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InterfaceDescriptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InterfaceDescriptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.InterfaceDescriptionHelper.id(),"InterfaceDescription",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.IdentifierHelper.id(), "Identifier",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("defined_in", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("version", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.VersionSpecHelper.id(), "VersionSpec",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("base_interfaces", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdSeqHelper.id(), "RepositoryIdSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.RepositoryIdHelper.type())), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.InterfaceDescription s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.InterfaceDescription extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CORBA/InterfaceDescription:1.0";
	}
	public static org.omg.CORBA.InterfaceDescription read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CORBA.InterfaceDescription result = new org.omg.CORBA.InterfaceDescription();
		result.name=in.read_string();
		result.id=in.read_string();
		result.defined_in=in.read_string();
		result.version=in.read_string();
		result.base_interfaces = org.omg.CORBA.RepositoryIdSeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CORBA.InterfaceDescription s)
	{
		java.lang.String tmpResult487 = s.name;
out.write_string( tmpResult487 );
		java.lang.String tmpResult488 = s.id;
out.write_string( tmpResult488 );
		java.lang.String tmpResult489 = s.defined_in;
out.write_string( tmpResult489 );
		java.lang.String tmpResult490 = s.version;
out.write_string( tmpResult490 );
		org.omg.CORBA.RepositoryIdSeqHelper.write(out,s.base_interfaces);
	}
}
