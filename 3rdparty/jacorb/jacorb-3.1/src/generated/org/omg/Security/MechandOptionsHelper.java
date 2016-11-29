package org.omg.Security;


/**
 * Generated from IDL struct "MechandOptions".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class MechandOptionsHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(MechandOptionsHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.MechandOptionsHelper.id(),"MechandOptions",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mechanism_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.MechanismTypeHelper.id(), "MechanismType",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("options_supported", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.MechandOptions s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.MechandOptions extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/MechandOptions:1.0";
	}
	public static org.omg.Security.MechandOptions read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.MechandOptions result = new org.omg.Security.MechandOptions();
		result.mechanism_type=in.read_string();
		result.options_supported=in.read_ushort();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.MechandOptions s)
	{
		java.lang.String tmpResult1182 = s.mechanism_type;
out.write_string( tmpResult1182 );
		out.write_ushort(s.options_supported);
	}
}
