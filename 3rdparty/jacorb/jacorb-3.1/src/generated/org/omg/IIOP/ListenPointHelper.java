package org.omg.IIOP;


/**
 * Generated from IDL struct "ListenPoint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ListenPointHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ListenPointHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.ListenPointHelper.id(),"ListenPoint",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IIOP.ListenPoint s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IIOP.ListenPoint extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IIOP/ListenPoint:1.0";
	}
	public static org.omg.IIOP.ListenPoint read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IIOP.ListenPoint result = new org.omg.IIOP.ListenPoint();
		result.host=in.read_string();
		result.port=in.read_ushort();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IIOP.ListenPoint s)
	{
		java.lang.String tmpResult1007 = s.host;
out.write_string( tmpResult1007 );
		out.write_ushort(s.port);
	}
}
