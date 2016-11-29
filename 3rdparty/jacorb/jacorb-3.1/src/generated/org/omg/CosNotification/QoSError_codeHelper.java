package org.omg.CosNotification;
/**
 * Generated from IDL enum "QoSError_code".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class QoSError_codeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(QoSError_codeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNotification.QoSError_codeHelper.id(),"QoSError_code",new String[]{"UNSUPPORTED_PROPERTY","UNAVAILABLE_PROPERTY","UNSUPPORTED_VALUE","UNAVAILABLE_VALUE","BAD_PROPERTY","BAD_TYPE","BAD_VALUE"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotification.QoSError_code s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotification.QoSError_code extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotification/QoSError_code:1.0";
	}
	public static QoSError_code read (final org.omg.CORBA.portable.InputStream in)
	{
		return QoSError_code.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final QoSError_code s)
	{
		out.write_long(s.value());
	}
}
