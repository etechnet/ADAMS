package org.omg.Security;


/**
 * Generated from IDL struct "Right".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class RightHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RightHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.RightHelper.id(),"Right",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("rights_family", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.ExtensibleFamilyHelper.id(),"ExtensibleFamily",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("family_definer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("family", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)}), null),new org.omg.CORBA.StructMember("right", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.Right s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.Right extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/Right:1.0";
	}
	public static org.omg.Security.Right read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.Right result = new org.omg.Security.Right();
		result.rights_family=org.omg.Security.ExtensibleFamilyHelper.read(in);
		result.right=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.Right s)
	{
		org.omg.Security.ExtensibleFamilyHelper.write(out,s.rights_family);
		java.lang.String tmpResult1181 = s.right;
out.write_string( tmpResult1181 );
	}
}
