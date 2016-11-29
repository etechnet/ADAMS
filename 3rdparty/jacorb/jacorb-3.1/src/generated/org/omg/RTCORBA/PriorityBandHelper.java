package org.omg.RTCORBA;


/**
 * Generated from IDL struct "PriorityBand".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityBandHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PriorityBandHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.RTCORBA.PriorityBandHelper.id(),"PriorityBand",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("low", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.RTCORBA.PriorityHelper.id(), "Priority",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null),new org.omg.CORBA.StructMember("high", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.RTCORBA.PriorityHelper.id(), "Priority",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.RTCORBA.PriorityBand s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.RTCORBA.PriorityBand extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/RTCORBA/PriorityBand:1.0";
	}
	public static org.omg.RTCORBA.PriorityBand read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.RTCORBA.PriorityBand result = new org.omg.RTCORBA.PriorityBand();
		result.low=in.read_short();
		result.high=in.read_short();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.RTCORBA.PriorityBand s)
	{
		out.write_short(s.low);
		out.write_short(s.high);
	}
}
