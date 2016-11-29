package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedProxyPullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class TypedProxyPullConsumerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TypedProxyPullConsumerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTypedNotifyChannelAdmin/TypedProxyPullConsumer:1.0", "TypedProxyPullConsumer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTypedNotifyChannelAdmin/TypedProxyPullConsumer:1.0";
	}
	public static TypedProxyPullConsumer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTypedNotifyChannelAdmin._TypedProxyPullConsumerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer)
		{
			return (org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTypedNotifyChannelAdmin/TypedProxyPullConsumer:1.0"))
		{
			org.omg.CosTypedNotifyChannelAdmin._TypedProxyPullConsumerStub stub;
			stub = new org.omg.CosTypedNotifyChannelAdmin._TypedProxyPullConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer)
		{
			return (org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer)obj;
		}
		else
		{
			org.omg.CosTypedNotifyChannelAdmin._TypedProxyPullConsumerStub stub;
			stub = new org.omg.CosTypedNotifyChannelAdmin._TypedProxyPullConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
