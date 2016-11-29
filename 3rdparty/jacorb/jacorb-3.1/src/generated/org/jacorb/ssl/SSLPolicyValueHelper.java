package org.jacorb.ssl;
/**
 * Generated from IDL enum "SSLPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class SSLPolicyValueHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SSLPolicyValueHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.jacorb.ssl.SSLPolicyValueHelper.id(),"SSLPolicyValue",new String[]{"SSL_NOT_REQUIRED","SSL_REQUIRED"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.ssl.SSLPolicyValue s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.ssl.SSLPolicyValue extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/ssl/SSLPolicyValue:1.0";
	}
	public static SSLPolicyValue read (final org.omg.CORBA.portable.InputStream in)
	{
		return SSLPolicyValue.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final SSLPolicyValue s)
	{
		out.write_long(s.value());
	}
}
