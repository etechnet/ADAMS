package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "StructuredProxyPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class StructuredProxyPushConsumerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(StructuredProxyPushConsumerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyChannelAdmin/StructuredProxyPushConsumer:1.0", "StructuredProxyPushConsumer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyChannelAdmin/StructuredProxyPushConsumer:1.0";
	}
	public static StructuredProxyPushConsumer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyChannelAdmin._StructuredProxyPushConsumerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer)
		{
			return (org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyChannelAdmin/StructuredProxyPushConsumer:1.0"))
		{
			org.omg.CosNotifyChannelAdmin._StructuredProxyPushConsumerStub stub;
			stub = new org.omg.CosNotifyChannelAdmin._StructuredProxyPushConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer)
		{
			return (org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer)obj;
		}
		else
		{
			org.omg.CosNotifyChannelAdmin._StructuredProxyPushConsumerStub stub;
			stub = new org.omg.CosNotifyChannelAdmin._StructuredProxyPushConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
