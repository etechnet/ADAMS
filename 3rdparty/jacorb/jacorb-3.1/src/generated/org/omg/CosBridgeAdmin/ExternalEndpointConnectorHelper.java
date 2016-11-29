package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL union "ExternalEndpointConnector".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointConnectorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ExternalEndpointConnectorHelper.class)
			{
				if (_type == null)
				{
			org.omg.CORBA.UnionMember[] members = new org.omg.CORBA.UnionMember[2];
			org.omg.CORBA.Any label_any;
			label_any = org.omg.CORBA.ORB.init().create_any ();
			org.omg.CosBridgeAdmin.MessageTypeHelper.insert(label_any, org.omg.CosBridgeAdmin.MessageType.JMS_MESSAGE);
			members[0] = new org.omg.CORBA.UnionMember ("destination", label_any, org.omg.CosBridgeAdmin.JMSDestinationHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_octet ((byte)0);
			members[1] = new org.omg.CORBA.UnionMember ("channel_id", label_any, org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotifyChannelAdmin.ChannelIDHelper.id(), "ChannelID",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))),null);
			 _type = org.omg.CORBA.ORB.init().create_union_tc(id(),"ExternalEndpointConnector",org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.MessageTypeHelper.id(),"MessageType",new String[]{"JMS_MESSAGE","STRUCTURED_EVENT","SEQUENCE_EVENT"}), members);
				}
			}
		}
			return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosBridgeAdmin.ExternalEndpointConnector s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosBridgeAdmin.ExternalEndpointConnector extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosBridgeAdmin/ExternalEndpointConnector:1.0";
	}
	public static ExternalEndpointConnector read (org.omg.CORBA.portable.InputStream in)
	{
		ExternalEndpointConnector result = new ExternalEndpointConnector();
		org.omg.CosBridgeAdmin.MessageType disc;
		try
		{
			disc = org.omg.CosBridgeAdmin.MessageType.from_int(in.read_long());
			switch (disc.value ())
			{
				case org.omg.CosBridgeAdmin.MessageType._JMS_MESSAGE:
				{
					org.omg.CosBridgeAdmin.JMSDestination _var;
					_var=org.omg.CosBridgeAdmin.JMSDestinationHelper.read(in);
					result.destination (_var);
					break;
				}
				default:
				{
					int _var;
					_var=in.read_long();
					result.channel_id (_var);
				}
		}
		}
		catch (org.omg.CORBA.BAD_PARAM b)
		{
			// The default value was out-of-bounds for the Enum. Just use the default.
					int _var;
					_var=in.read_long();
					result.channel_id (_var);
		}
		return result;
	}
	public static void write (org.omg.CORBA.portable.OutputStream out, ExternalEndpointConnector s)
	{
		out.write_long (s.discriminator().value ());
		switch (s.discriminator().value ())
		{
				case org.omg.CosBridgeAdmin.MessageType._JMS_MESSAGE:
				{
					org.omg.CosBridgeAdmin.JMSDestinationHelper.write(out,s.destination ());
					break;
				}
				default:
				{
					out.write_long(s.channel_id ());
				}
		}
	}
}
