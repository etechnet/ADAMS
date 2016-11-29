package org.omg.CosNotifyChannelAdminAck;


/**
 * Generated from IDL interface "StructuredProxyPullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class StructuredProxyPullSupplierAckHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(StructuredProxyPullSupplierAckHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyChannelAdminAck/StructuredProxyPullSupplierAck:1.0", "StructuredProxyPullSupplierAck");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyChannelAdminAck/StructuredProxyPullSupplierAck:1.0";
	}
	public static StructuredProxyPullSupplierAck read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyChannelAdminAck._StructuredProxyPullSupplierAckStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck)
		{
			return (org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyChannelAdminAck/StructuredProxyPullSupplierAck:1.0"))
		{
			org.omg.CosNotifyChannelAdminAck._StructuredProxyPullSupplierAckStub stub;
			stub = new org.omg.CosNotifyChannelAdminAck._StructuredProxyPullSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck)
		{
			return (org.omg.CosNotifyChannelAdminAck.StructuredProxyPullSupplierAck)obj;
		}
		else
		{
			org.omg.CosNotifyChannelAdminAck._StructuredProxyPullSupplierAckStub stub;
			stub = new org.omg.CosNotifyChannelAdminAck._StructuredProxyPullSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
