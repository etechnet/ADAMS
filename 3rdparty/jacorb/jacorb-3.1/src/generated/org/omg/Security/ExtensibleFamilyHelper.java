package org.omg.Security;


/**
 * Generated from IDL struct "ExtensibleFamily".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExtensibleFamilyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ExtensibleFamilyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.ExtensibleFamilyHelper.id(),"ExtensibleFamily",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("family_definer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("family", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.ExtensibleFamily s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.ExtensibleFamily extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/ExtensibleFamily:1.0";
	}
	public static org.omg.Security.ExtensibleFamily read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.ExtensibleFamily result = new org.omg.Security.ExtensibleFamily();
		result.family_definer=in.read_ushort();
		result.family=in.read_ushort();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.ExtensibleFamily s)
	{
		out.write_ushort(s.family_definer);
		out.write_ushort(s.family);
	}
}
