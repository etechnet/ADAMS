package org.omg.GSSUP;


/**
 * Generated from IDL struct "InitialContextToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class InitialContextTokenHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InitialContextTokenHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GSSUP.InitialContextTokenHelper.id(),"InitialContextToken",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("username", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.UTF8StringHelper.id(), "UTF8String",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null),new org.omg.CORBA.StructMember("password", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.UTF8StringHelper.id(), "UTF8String",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null),new org.omg.CORBA.StructMember("target_name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.GSS_NT_ExportedNameHelper.id(), "GSS_NT_ExportedName",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GSSUP.InitialContextToken s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GSSUP.InitialContextToken extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GSSUP/InitialContextToken:1.0";
	}
	public static org.omg.GSSUP.InitialContextToken read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.GSSUP.InitialContextToken result = new org.omg.GSSUP.InitialContextToken();
		result.username = org.omg.CSI.UTF8StringHelper.read(in);
		result.password = org.omg.CSI.UTF8StringHelper.read(in);
		result.target_name = org.omg.CSI.GSS_NT_ExportedNameHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.GSSUP.InitialContextToken s)
	{
		org.omg.CSI.UTF8StringHelper.write(out,s.username);
		org.omg.CSI.UTF8StringHelper.write(out,s.password);
		org.omg.CSI.GSS_NT_ExportedNameHelper.write(out,s.target_name);
	}
}
