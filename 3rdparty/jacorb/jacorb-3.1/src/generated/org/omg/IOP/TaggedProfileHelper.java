package org.omg.IOP;


/**
 * Generated from IDL struct "TaggedProfile".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class TaggedProfileHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TaggedProfileHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.TaggedProfileHelper.id(),"TaggedProfile",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("tag", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ProfileIdHelper.id(), "ProfileId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("profile_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IOP.TaggedProfile s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IOP.TaggedProfile extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IOP/TaggedProfile:1.0";
	}
	public static org.omg.IOP.TaggedProfile read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IOP.TaggedProfile result = new org.omg.IOP.TaggedProfile();
		result.tag=in.read_ulong();
		int _lresult_profile_data55 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_profile_data55 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_profile_data55);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.profile_data = new byte[_lresult_profile_data55];
		in.read_octet_array(result.profile_data,0,_lresult_profile_data55);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IOP.TaggedProfile s)
	{
		out.write_ulong(s.tag);
		
		out.write_long(s.profile_data.length);
		out.write_octet_array(s.profile_data,0,s.profile_data.length);
	}
}
