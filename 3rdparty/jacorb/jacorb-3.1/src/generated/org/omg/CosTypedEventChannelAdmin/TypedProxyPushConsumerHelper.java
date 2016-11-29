package org.omg.CosTypedEventChannelAdmin;


/**
 * Generated from IDL interface "TypedProxyPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class TypedProxyPushConsumerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TypedProxyPushConsumerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTypedEventChannelAdmin/TypedProxyPushConsumer:1.0", "TypedProxyPushConsumer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTypedEventChannelAdmin/TypedProxyPushConsumer:1.0";
	}
	public static TypedProxyPushConsumer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTypedEventChannelAdmin._TypedProxyPushConsumerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer)
		{
			return (org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTypedEventChannelAdmin/TypedProxyPushConsumer:1.0"))
		{
			org.omg.CosTypedEventChannelAdmin._TypedProxyPushConsumerStub stub;
			stub = new org.omg.CosTypedEventChannelAdmin._TypedProxyPushConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer)
		{
			return (org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer)obj;
		}
		else
		{
			org.omg.CosTypedEventChannelAdmin._TypedProxyPushConsumerStub stub;
			stub = new org.omg.CosTypedEventChannelAdmin._TypedProxyPushConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
