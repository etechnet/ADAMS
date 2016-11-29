package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL exception "AlreadyConnected".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class AlreadyConnectedHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AlreadyConnectedHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosEventChannelAdmin.AlreadyConnectedHelper.id(),"AlreadyConnected",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosEventChannelAdmin.AlreadyConnected s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosEventChannelAdmin.AlreadyConnected extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosEventChannelAdmin/AlreadyConnected:1.0";
	}
	public static org.omg.CosEventChannelAdmin.AlreadyConnected read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final org.omg.CosEventChannelAdmin.AlreadyConnected result = new org.omg.CosEventChannelAdmin.AlreadyConnected(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosEventChannelAdmin.AlreadyConnected s)
	{
		out.write_string(id());
	}
}
