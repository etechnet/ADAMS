package org.omg.CosBridgeAdmin;


/**
 * Generated from IDL struct "ExternalEndpointError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointErrorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ExternalEndpointErrorHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.id(),"ExternalEndpointError",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("role", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.id(),"ExternalEndpointRole",new String[]{"SOURCE","SINK"}), null),new org.omg.CORBA.StructMember("code", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorCodeHelper.id(),"ExternalEndpointErrorCode",new String[]{"INVALID_CHANNELID","INVALID_JMSDESTINATION","MISMATCH_ENDPOINTROLE_NOTIFSTYLE"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosBridgeAdmin.ExternalEndpointError s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosBridgeAdmin.ExternalEndpointError extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosBridgeAdmin/ExternalEndpointError:1.0";
	}
	public static org.omg.CosBridgeAdmin.ExternalEndpointError read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosBridgeAdmin.ExternalEndpointError result = new org.omg.CosBridgeAdmin.ExternalEndpointError();
		result.role=org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.read(in);
		result.code=org.omg.CosBridgeAdmin.ExternalEndpointErrorCodeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosBridgeAdmin.ExternalEndpointError s)
	{
		org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.write(out,s.role);
		org.omg.CosBridgeAdmin.ExternalEndpointErrorCodeHelper.write(out,s.code);
	}
}
