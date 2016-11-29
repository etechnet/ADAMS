package org.omg.IIOP;


/**
 * Generated from IDL struct "ProfileBody_1_1".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProfileBody_1_1Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ProfileBody_1_1Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.ProfileBody_1_1Helper.id(),"ProfileBody_1_1",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("iiop_version", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.VersionHelper.id(),"Version",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("major", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("minor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null)}), null),new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("object_key", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null),new org.omg.CORBA.StructMember("components", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.TaggedComponentHelper.id(),"TaggedComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("tag", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ComponentIdHelper.id(), "ComponentId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("component_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)})), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IIOP.ProfileBody_1_1 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IIOP.ProfileBody_1_1 extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IIOP/ProfileBody_1_1:1.0";
	}
	public static org.omg.IIOP.ProfileBody_1_1 read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IIOP.ProfileBody_1_1 result = new org.omg.IIOP.ProfileBody_1_1();
		result.iiop_version=org.omg.IIOP.VersionHelper.read(in);
		result.host=in.read_string();
		result.port=in.read_ushort();
		int _lresult_object_key82 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_object_key82 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_object_key82);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.object_key = new byte[_lresult_object_key82];
		in.read_octet_array(result.object_key,0,_lresult_object_key82);
		int _lresult_components83 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_components83 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_components83);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.components = new org.omg.IOP.TaggedComponent[_lresult_components83];
		for (int i=0;i<result.components.length;i++)
		{
			result.components[i]=org.omg.IOP.TaggedComponentHelper.read(in);
		}

		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IIOP.ProfileBody_1_1 s)
	{
		org.omg.IIOP.VersionHelper.write(out,s.iiop_version);
		java.lang.String tmpResult1006 = s.host;
out.write_string( tmpResult1006 );
		out.write_ushort(s.port);
		
		out.write_long(s.object_key.length);
		out.write_octet_array(s.object_key,0,s.object_key.length);
		
		out.write_long(s.components.length);
		for (int i=0; i<s.components.length;i++)
		{
			org.omg.IOP.TaggedComponentHelper.write(out,s.components[i]);
		}

	}
}
