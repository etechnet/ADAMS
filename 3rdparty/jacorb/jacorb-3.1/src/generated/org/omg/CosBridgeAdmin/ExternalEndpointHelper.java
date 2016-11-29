package org.omg.CosBridgeAdmin;


/**
 * Generated from IDL struct "ExternalEndpoint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ExternalEndpointHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosBridgeAdmin.ExternalEndpointHelper.id(),"ExternalEndpoint",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("role", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.id(),"ExternalEndpointRole",new String[]{"SOURCE","SINK"}), null),new org.omg.CORBA.StructMember("connector", org.omg.CosBridgeAdmin.ExternalEndpointConnectorHelper.type(), null),new org.omg.CORBA.StructMember("style", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.FlowStyleHelper.id(),"FlowStyle",new String[]{"PUSH","PULL"}), null),new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.MessageTypeHelper.id(),"MessageType",new String[]{"JMS_MESSAGE","STRUCTURED_EVENT","SEQUENCE_EVENT"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosBridgeAdmin.ExternalEndpoint s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosBridgeAdmin.ExternalEndpoint extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosBridgeAdmin/ExternalEndpoint:1.0";
	}
	public static org.omg.CosBridgeAdmin.ExternalEndpoint read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosBridgeAdmin.ExternalEndpoint result = new org.omg.CosBridgeAdmin.ExternalEndpoint();
		result.role=org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.read(in);
		result.connector=org.omg.CosBridgeAdmin.ExternalEndpointConnectorHelper.read(in);
		result.style=org.omg.CosBridgeAdmin.FlowStyleHelper.read(in);
		result.type=org.omg.CosBridgeAdmin.MessageTypeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosBridgeAdmin.ExternalEndpoint s)
	{
		org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.write(out,s.role);
		org.omg.CosBridgeAdmin.ExternalEndpointConnectorHelper.write(out,s.connector);
		org.omg.CosBridgeAdmin.FlowStyleHelper.write(out,s.style);
		org.omg.CosBridgeAdmin.MessageTypeHelper.write(out,s.type);
	}
}
