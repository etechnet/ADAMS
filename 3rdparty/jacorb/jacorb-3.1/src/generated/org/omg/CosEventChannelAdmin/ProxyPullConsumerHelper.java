package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL interface "ProxyPullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProxyPullConsumerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ProxyPullConsumerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0", "ProxyPullConsumer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosEventChannelAdmin.ProxyPullConsumer s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosEventChannelAdmin.ProxyPullConsumer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0";
	}
	public static ProxyPullConsumer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosEventChannelAdmin._ProxyPullConsumerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosEventChannelAdmin.ProxyPullConsumer s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosEventChannelAdmin.ProxyPullConsumer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosEventChannelAdmin.ProxyPullConsumer)
		{
			return (org.omg.CosEventChannelAdmin.ProxyPullConsumer)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0"))
		{
			org.omg.CosEventChannelAdmin._ProxyPullConsumerStub stub;
			stub = new org.omg.CosEventChannelAdmin._ProxyPullConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosEventChannelAdmin.ProxyPullConsumer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosEventChannelAdmin.ProxyPullConsumer)
		{
			return (org.omg.CosEventChannelAdmin.ProxyPullConsumer)obj;
		}
		else
		{
			org.omg.CosEventChannelAdmin._ProxyPullConsumerStub stub;
			stub = new org.omg.CosEventChannelAdmin._ProxyPullConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
