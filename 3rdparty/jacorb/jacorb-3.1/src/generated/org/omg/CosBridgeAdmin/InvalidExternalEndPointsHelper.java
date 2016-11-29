package org.omg.CosBridgeAdmin;


/**
 * Generated from IDL exception "InvalidExternalEndPoints".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class InvalidExternalEndPointsHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidExternalEndPointsHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosBridgeAdmin.InvalidExternalEndPointsHelper.id(),"InvalidExternalEndPoints",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("error", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorSeqHelper.id(), "ExternalEndpointErrorSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.id(),"ExternalEndpointError",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("role", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.id(),"ExternalEndpointRole",new String[]{"SOURCE","SINK"}), null),new org.omg.CORBA.StructMember("code", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorCodeHelper.id(),"ExternalEndpointErrorCode",new String[]{"INVALID_CHANNELID","INVALID_JMSDESTINATION","MISMATCH_ENDPOINTROLE_NOTIFSTYLE"}), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosBridgeAdmin.InvalidExternalEndPoints s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosBridgeAdmin.InvalidExternalEndPoints extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosBridgeAdmin/InvalidExternalEndPoints:1.0";
	}
	public static org.omg.CosBridgeAdmin.InvalidExternalEndPoints read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosBridgeAdmin.ExternalEndpointError[] x0;
		x0 = org.omg.CosBridgeAdmin.ExternalEndpointErrorSeqHelper.read(in);
		final org.omg.CosBridgeAdmin.InvalidExternalEndPoints result = new org.omg.CosBridgeAdmin.InvalidExternalEndPoints(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosBridgeAdmin.InvalidExternalEndPoints s)
	{
		out.write_string(id());
		org.omg.CosBridgeAdmin.ExternalEndpointErrorSeqHelper.write(out,s.error);
	}
}
