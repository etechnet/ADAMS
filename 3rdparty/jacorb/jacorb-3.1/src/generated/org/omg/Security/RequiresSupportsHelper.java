package org.omg.Security;
/**
 * Generated from IDL enum "RequiresSupports".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class RequiresSupportsHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RequiresSupportsHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.Security.RequiresSupportsHelper.id(),"RequiresSupports",new String[]{"SecRequires","SecSupports"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.RequiresSupports s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.RequiresSupports extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/RequiresSupports:1.0";
	}
	public static RequiresSupports read (final org.omg.CORBA.portable.InputStream in)
	{
		return RequiresSupports.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final RequiresSupports s)
	{
		out.write_long(s.value());
	}
}
