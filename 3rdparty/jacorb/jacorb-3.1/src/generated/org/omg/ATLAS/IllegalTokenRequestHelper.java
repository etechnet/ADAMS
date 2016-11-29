package org.omg.ATLAS;


/**
 * Generated from IDL exception "IllegalTokenRequest".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class IllegalTokenRequestHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IllegalTokenRequestHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.ATLAS.IllegalTokenRequestHelper.id(),"IllegalTokenRequest",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_errnum", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("the_reason", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.ATLAS.IllegalTokenRequest s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.ATLAS.IllegalTokenRequest extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/ATLAS/IllegalTokenRequest:1.0";
	}
	public static org.omg.ATLAS.IllegalTokenRequest read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		int x0;
		x0=in.read_ulong();
		java.lang.String x1;
		x1=in.read_string();
		final org.omg.ATLAS.IllegalTokenRequest result = new org.omg.ATLAS.IllegalTokenRequest(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.ATLAS.IllegalTokenRequest s)
	{
		out.write_string(id());
		out.write_ulong(s.the_errnum);
		java.lang.String tmpResult1172 = s.the_reason;
out.write_string( tmpResult1172 );
	}
}
