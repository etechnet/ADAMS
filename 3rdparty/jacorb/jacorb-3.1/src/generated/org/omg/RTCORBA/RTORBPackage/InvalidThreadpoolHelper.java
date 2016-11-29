package org.omg.RTCORBA.RTORBPackage;


/**
 * Generated from IDL exception "InvalidThreadpool".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InvalidThreadpoolHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidThreadpoolHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.RTCORBA.RTORBPackage.InvalidThreadpoolHelper.id(),"InvalidThreadpool",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.RTCORBA.RTORBPackage.InvalidThreadpool s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.RTCORBA.RTORBPackage.InvalidThreadpool extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/RTCORBA/RTORB/InvalidThreadpool:1.0";
	}
	public static org.omg.RTCORBA.RTORBPackage.InvalidThreadpool read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final org.omg.RTCORBA.RTORBPackage.InvalidThreadpool result = new org.omg.RTCORBA.RTORBPackage.InvalidThreadpool(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.RTCORBA.RTORBPackage.InvalidThreadpool s)
	{
		out.write_string(id());
	}
}
