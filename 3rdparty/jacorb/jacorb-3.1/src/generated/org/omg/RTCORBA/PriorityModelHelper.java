package org.omg.RTCORBA;
/**
 * Generated from IDL enum "PriorityModel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityModelHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PriorityModelHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.RTCORBA.PriorityModelHelper.id(),"PriorityModel",new String[]{"CLIENT_PROPAGATED","SERVER_DECLARED"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.RTCORBA.PriorityModel s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.RTCORBA.PriorityModel extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/RTCORBA/PriorityModel:1.0";
	}
	public static PriorityModel read (final org.omg.CORBA.portable.InputStream in)
	{
		return PriorityModel.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final PriorityModel s)
	{
		out.write_long(s.value());
	}
}
