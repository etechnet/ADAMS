package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL interface "EventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EventChannelHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EventChannelHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0", "EventChannel");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosEventChannelAdmin.EventChannel s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosEventChannelAdmin.EventChannel extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0";
	}
	public static EventChannel read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosEventChannelAdmin._EventChannelStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosEventChannelAdmin.EventChannel s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosEventChannelAdmin.EventChannel narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosEventChannelAdmin.EventChannel)
		{
			return (org.omg.CosEventChannelAdmin.EventChannel)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0"))
		{
			org.omg.CosEventChannelAdmin._EventChannelStub stub;
			stub = new org.omg.CosEventChannelAdmin._EventChannelStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosEventChannelAdmin.EventChannel unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosEventChannelAdmin.EventChannel)
		{
			return (org.omg.CosEventChannelAdmin.EventChannel)obj;
		}
		else
		{
			org.omg.CosEventChannelAdmin._EventChannelStub stub;
			stub = new org.omg.CosEventChannelAdmin._EventChannelStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
