package org.jacorb.imr.RegistrationPackage;


/**
 * Generated from IDL exception "IllegalHostName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class IllegalHostNameHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IllegalHostNameHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.jacorb.imr.RegistrationPackage.IllegalHostNameHelper.id(),"IllegalHostName",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostNameHelper.id(), "HostName",org.omg.CORBA.ORB.init().create_string_tc(0)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.RegistrationPackage.IllegalHostName s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.imr.RegistrationPackage.IllegalHostName extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/imr/Registration/IllegalHostName:1.0";
	}
	public static org.jacorb.imr.RegistrationPackage.IllegalHostName read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		java.lang.String x0;
		x0=in.read_string();
		final org.jacorb.imr.RegistrationPackage.IllegalHostName result = new org.jacorb.imr.RegistrationPackage.IllegalHostName(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.imr.RegistrationPackage.IllegalHostName s)
	{
		out.write_string(id());
		java.lang.String tmpResult1206 = s.name;
out.write_string( tmpResult1206 );
	}
}
