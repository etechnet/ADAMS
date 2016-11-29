package org.omg.CosNotifyChannelAdmin;
/**
 * Generated from IDL enum "InterFilterGroupOperator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InterFilterGroupOperatorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InterFilterGroupOperatorHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNotifyChannelAdmin.InterFilterGroupOperatorHelper.id(),"InterFilterGroupOperator",new String[]{"AND_OP","OR_OP"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotifyChannelAdmin/InterFilterGroupOperator:1.0";
	}
	public static InterFilterGroupOperator read (final org.omg.CORBA.portable.InputStream in)
	{
		return InterFilterGroupOperator.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final InterFilterGroupOperator s)
	{
		out.write_long(s.value());
	}
}
