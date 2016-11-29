package org.omg.CSIIOP;


/**
 * Generated from IDL struct "ServiceConfiguration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ServiceConfigurationHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ServiceConfigurationHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.ServiceConfigurationHelper.id(),"ServiceConfiguration",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("syntax", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.ServiceConfigurationSyntaxHelper.id(), "ServiceConfigurationSyntax",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.ServiceSpecificNameHelper.id(), "ServiceSpecificName",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CSIIOP.ServiceConfiguration s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CSIIOP.ServiceConfiguration extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CSIIOP/ServiceConfiguration:1.0";
	}
	public static org.omg.CSIIOP.ServiceConfiguration read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CSIIOP.ServiceConfiguration result = new org.omg.CSIIOP.ServiceConfiguration();
		result.syntax=in.read_ulong();
		result.name = org.omg.CSIIOP.ServiceSpecificNameHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CSIIOP.ServiceConfiguration s)
	{
		out.write_ulong(s.syntax);
		org.omg.CSIIOP.ServiceSpecificNameHelper.write(out,s.name);
	}
}
