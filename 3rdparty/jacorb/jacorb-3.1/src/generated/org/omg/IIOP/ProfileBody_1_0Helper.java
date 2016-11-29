package org.omg.IIOP;


/**
 * Generated from IDL struct "ProfileBody_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProfileBody_1_0Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ProfileBody_1_0Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.ProfileBody_1_0Helper.id(),"ProfileBody_1_0",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("iiop_version", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.VersionHelper.id(),"Version",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("major", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("minor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null)}), null),new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("object_key", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IIOP.ProfileBody_1_0 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IIOP.ProfileBody_1_0 extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IIOP/ProfileBody_1_0:1.0";
	}
	public static org.omg.IIOP.ProfileBody_1_0 read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IIOP.ProfileBody_1_0 result = new org.omg.IIOP.ProfileBody_1_0();
		result.iiop_version=org.omg.IIOP.VersionHelper.read(in);
		result.host=in.read_string();
		result.port=in.read_ushort();
		int _lresult_object_key81 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_object_key81 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_object_key81);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.object_key = new byte[_lresult_object_key81];
		in.read_octet_array(result.object_key,0,_lresult_object_key81);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IIOP.ProfileBody_1_0 s)
	{
		org.omg.IIOP.VersionHelper.write(out,s.iiop_version);
		java.lang.String tmpResult1005 = s.host;
out.write_string( tmpResult1005 );
		out.write_ushort(s.port);
		
		out.write_long(s.object_key.length);
		out.write_octet_array(s.object_key,0,s.object_key.length);
	}
}
