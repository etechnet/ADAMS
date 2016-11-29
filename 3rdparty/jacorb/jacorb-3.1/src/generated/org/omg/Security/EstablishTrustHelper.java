package org.omg.Security;


/**
 * Generated from IDL struct "EstablishTrust".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class EstablishTrustHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EstablishTrustHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.EstablishTrustHelper.id(),"EstablishTrust",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("trust_in_client", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("trust_in_target", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.EstablishTrust s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.EstablishTrust extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/EstablishTrust:1.0";
	}
	public static org.omg.Security.EstablishTrust read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.EstablishTrust result = new org.omg.Security.EstablishTrust();
		result.trust_in_client=in.read_boolean();
		result.trust_in_target=in.read_boolean();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.EstablishTrust s)
	{
		out.write_boolean(s.trust_in_client);
		out.write_boolean(s.trust_in_target);
	}
}
