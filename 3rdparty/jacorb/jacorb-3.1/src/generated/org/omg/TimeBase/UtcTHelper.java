package org.omg.TimeBase;


/**
 * Generated from IDL struct "UtcT".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class UtcTHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UtcTHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.TimeBase.UtcTHelper.id(),"UtcT",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("time", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TimeTHelper.id(), "TimeT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null),new org.omg.CORBA.StructMember("inacclo", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("inacchi", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("tdf", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TdfTHelper.id(), "TdfT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.TimeBase.UtcT s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.TimeBase.UtcT extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/TimeBase/UtcT:1.0";
	}
	public static org.omg.TimeBase.UtcT read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.TimeBase.UtcT result = new org.omg.TimeBase.UtcT();
		result.time=in.read_ulonglong();
		result.inacclo=in.read_ulong();
		result.inacchi=in.read_ushort();
		result.tdf=in.read_short();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.TimeBase.UtcT s)
	{
		out.write_ulonglong(s.time);
		out.write_ulong(s.inacclo);
		out.write_ushort(s.inacchi);
		out.write_short(s.tdf);
	}
}
