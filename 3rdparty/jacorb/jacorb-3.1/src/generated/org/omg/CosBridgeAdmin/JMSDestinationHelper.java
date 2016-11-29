package org.omg.CosBridgeAdmin;


/**
 * Generated from IDL struct "JMSDestination".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class JMSDestinationHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(JMSDestinationHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosBridgeAdmin.JMSDestinationHelper.id(),"JMSDestination",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("destination_type", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.JMSDestinationTypeHelper.id(),"JMSDestinationType",new String[]{"QUEUE","TOPIC"}), null),new org.omg.CORBA.StructMember("destination_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("factory_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosBridgeAdmin.JMSDestination s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosBridgeAdmin.JMSDestination extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosBridgeAdmin/JMSDestination:1.0";
	}
	public static org.omg.CosBridgeAdmin.JMSDestination read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosBridgeAdmin.JMSDestination result = new org.omg.CosBridgeAdmin.JMSDestination();
		result.destination_type=org.omg.CosBridgeAdmin.JMSDestinationTypeHelper.read(in);
		result.destination_name=in.read_string();
		result.factory_name=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosBridgeAdmin.JMSDestination s)
	{
		org.omg.CosBridgeAdmin.JMSDestinationTypeHelper.write(out,s.destination_type);
		java.lang.String tmpResult1168 = s.destination_name;
out.write_string( tmpResult1168 );
		java.lang.String tmpResult1169 = s.factory_name;
out.write_string( tmpResult1169 );
	}
}
