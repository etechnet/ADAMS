package org.omg.CosNotifyComm;


/**
 * Generated from IDL interface "NotifySubscribe".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class NotifySubscribeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NotifySubscribeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0", "NotifySubscribe");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyComm.NotifySubscribe s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyComm.NotifySubscribe extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0";
	}
	public static NotifySubscribe read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyComm._NotifySubscribeStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyComm.NotifySubscribe s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyComm.NotifySubscribe narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyComm.NotifySubscribe)
		{
			return (org.omg.CosNotifyComm.NotifySubscribe)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyComm/NotifySubscribe:1.0"))
		{
			org.omg.CosNotifyComm._NotifySubscribeStub stub;
			stub = new org.omg.CosNotifyComm._NotifySubscribeStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyComm.NotifySubscribe unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyComm.NotifySubscribe)
		{
			return (org.omg.CosNotifyComm.NotifySubscribe)obj;
		}
		else
		{
			org.omg.CosNotifyComm._NotifySubscribeStub stub;
			stub = new org.omg.CosNotifyComm._NotifySubscribeStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
