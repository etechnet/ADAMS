package org.omg.GSSUP;


/**
 * Generated from IDL struct "ErrorToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ErrorTokenHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ErrorTokenHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GSSUP.ErrorTokenHelper.id(),"ErrorToken",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("error_code", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GSSUP.ErrorCodeHelper.id(), "ErrorCode",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GSSUP.ErrorToken s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GSSUP.ErrorToken extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GSSUP/ErrorToken:1.0";
	}
	public static org.omg.GSSUP.ErrorToken read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.GSSUP.ErrorToken result = new org.omg.GSSUP.ErrorToken();
		result.error_code=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.GSSUP.ErrorToken s)
	{
		out.write_ulong(s.error_code);
	}
}
