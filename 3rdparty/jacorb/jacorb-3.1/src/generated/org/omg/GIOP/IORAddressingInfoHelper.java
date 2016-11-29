package org.omg.GIOP;


/**
 * Generated from IDL struct "IORAddressingInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class IORAddressingInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IORAddressingInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.IORAddressingInfoHelper.id(),"IORAddressingInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("selected_profile_index", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("ior", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.IORHelper.id(),"IOR",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("type_id", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("profiles", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.TaggedProfileHelper.id(),"TaggedProfile",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("tag", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ProfileIdHelper.id(), "ProfileId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("profile_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)})), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.IORAddressingInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.IORAddressingInfo extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/IORAddressingInfo:1.0";
	}
	public static org.omg.GIOP.IORAddressingInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.GIOP.IORAddressingInfo result = new org.omg.GIOP.IORAddressingInfo();
		result.selected_profile_index=in.read_ulong();
		result.ior=org.omg.IOP.IORHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.GIOP.IORAddressingInfo s)
	{
		out.write_ulong(s.selected_profile_index);
		org.omg.IOP.IORHelper.write(out,s.ior);
	}
}
