package org.omg.CORBA;


/**
 * Generated from IDL struct "ExceptionDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExceptionDescriptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ExceptionDescriptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.ExceptionDescriptionHelper.id(),"ExceptionDescription",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.IdentifierHelper.id(), "Identifier",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("defined_in", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("version", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.VersionSpecHelper.id(), "VersionSpec",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().get_primitive_tc( org.omg.CORBA.TCKind.tk_TypeCode), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.ExceptionDescription s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.ExceptionDescription extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CORBA/ExceptionDescription:1.0";
	}
	public static org.omg.CORBA.ExceptionDescription read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CORBA.ExceptionDescription result = new org.omg.CORBA.ExceptionDescription();
		result.name=in.read_string();
		result.id=in.read_string();
		result.defined_in=in.read_string();
		result.version=in.read_string();
		result.type=in.read_TypeCode();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CORBA.ExceptionDescription s)
	{
		java.lang.String tmpResult16 = s.name;
out.write_string( tmpResult16 );
		java.lang.String tmpResult17 = s.id;
out.write_string( tmpResult17 );
		java.lang.String tmpResult18 = s.defined_in;
out.write_string( tmpResult18 );
		java.lang.String tmpResult19 = s.version;
out.write_string( tmpResult19 );
		out.write_TypeCode(s.type);
	}
}
