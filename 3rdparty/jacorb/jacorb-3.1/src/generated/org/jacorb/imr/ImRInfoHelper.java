package org.jacorb.imr;


/**
 * Generated from IDL struct "ImRInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ImRInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ImRInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.imr.ImRInfoHelper.id(),"ImRInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostNameHelper.id(), "HostName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.PortNumberHelper.id(), "PortNumber",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.ImRInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.imr.ImRInfo extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/imr/ImRInfo:1.0";
	}
	public static org.jacorb.imr.ImRInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.jacorb.imr.ImRInfo result = new org.jacorb.imr.ImRInfo();
		result.host=in.read_string();
		result.port=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.imr.ImRInfo s)
	{
		java.lang.String tmpResult1190 = s.host;
out.write_string( tmpResult1190 );
		out.write_ulong(s.port);
	}
}
