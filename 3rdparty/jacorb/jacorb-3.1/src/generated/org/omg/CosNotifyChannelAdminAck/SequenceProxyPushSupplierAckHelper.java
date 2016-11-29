package org.omg.CosNotifyChannelAdminAck;


/**
 * Generated from IDL interface "SequenceProxyPushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SequenceProxyPushSupplierAckHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SequenceProxyPushSupplierAckHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyChannelAdminAck/SequenceProxyPushSupplierAck:1.0", "SequenceProxyPushSupplierAck");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyChannelAdminAck/SequenceProxyPushSupplierAck:1.0";
	}
	public static SequenceProxyPushSupplierAck read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyChannelAdminAck._SequenceProxyPushSupplierAckStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck)
		{
			return (org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyChannelAdminAck/SequenceProxyPushSupplierAck:1.0"))
		{
			org.omg.CosNotifyChannelAdminAck._SequenceProxyPushSupplierAckStub stub;
			stub = new org.omg.CosNotifyChannelAdminAck._SequenceProxyPushSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck)
		{
			return (org.omg.CosNotifyChannelAdminAck.SequenceProxyPushSupplierAck)obj;
		}
		else
		{
			org.omg.CosNotifyChannelAdminAck._SequenceProxyPushSupplierAckStub stub;
			stub = new org.omg.CosNotifyChannelAdminAck._SequenceProxyPushSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
