package org.omg.CosNotifyChannelAdmin;
/**
 * Generated from IDL enum "ClientType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ClientTypeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ClientTypeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNotifyChannelAdmin.ClientTypeHelper.id(),"ClientType",new String[]{"ANY_EVENT","STRUCTURED_EVENT","SEQUENCE_EVENT"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyChannelAdmin.ClientType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotifyChannelAdmin.ClientType extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotifyChannelAdmin/ClientType:1.0";
	}
	public static ClientType read (final org.omg.CORBA.portable.InputStream in)
	{
		return ClientType.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final ClientType s)
	{
		out.write_long(s.value());
	}
}
