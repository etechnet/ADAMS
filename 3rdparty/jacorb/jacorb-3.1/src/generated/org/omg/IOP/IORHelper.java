package org.omg.IOP;


/**
 * Generated from IDL struct "IOR".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class IORHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IORHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.IORHelper.id(),"IOR",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("type_id", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("profiles", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.TaggedProfileHelper.id(),"TaggedProfile",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("tag", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ProfileIdHelper.id(), "ProfileId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("profile_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)})), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IOP.IOR s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IOP.IOR extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IOP/IOR:1.0";
	}
	public static org.omg.IOP.IOR read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IOP.IOR result = new org.omg.IOP.IOR();
		result.type_id=in.read_string();
		int _lresult_profiles56 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_profiles56 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_profiles56);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.profiles = new org.omg.IOP.TaggedProfile[_lresult_profiles56];
		for (int i=0;i<result.profiles.length;i++)
		{
			result.profiles[i]=org.omg.IOP.TaggedProfileHelper.read(in);
		}

		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IOP.IOR s)
	{
		java.lang.String tmpResult985 = s.type_id;
out.write_string( tmpResult985 );
		
		out.write_long(s.profiles.length);
		for (int i=0; i<s.profiles.length;i++)
		{
			org.omg.IOP.TaggedProfileHelper.write(out,s.profiles[i]);
		}

	}
}
