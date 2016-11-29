package org.omg.CosTrading;
/**
 * Generated from IDL enum "FollowOption".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class FollowOptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(FollowOptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosTrading.FollowOptionHelper.id(),"FollowOption",new String[]{"local_only","if_no_local","always"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.FollowOption s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.FollowOption extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/FollowOption:1.0";
	}
	public static FollowOption read (final org.omg.CORBA.portable.InputStream in)
	{
		return FollowOption.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final FollowOption s)
	{
		out.write_long(s.value());
	}
}
