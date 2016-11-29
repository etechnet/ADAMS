package org.jacorb.notification;


/**
 * Generated from IDL interface "JacORBEventChannelFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class JacORBEventChannelFactoryHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(JacORBEventChannelFactoryHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/notification/JacORBEventChannelFactory:1.0", "JacORBEventChannelFactory");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.notification.JacORBEventChannelFactory s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.notification.JacORBEventChannelFactory extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/notification/JacORBEventChannelFactory:1.0";
	}
	public static JacORBEventChannelFactory read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jacorb.notification._JacORBEventChannelFactoryStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.notification.JacORBEventChannelFactory s)
	{
		_out.write_Object(s);
	}
	public static org.jacorb.notification.JacORBEventChannelFactory narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.notification.JacORBEventChannelFactory)
		{
			return (org.jacorb.notification.JacORBEventChannelFactory)obj;
		}
		else if (obj._is_a("IDL:org/jacorb/notification/JacORBEventChannelFactory:1.0"))
		{
			org.jacorb.notification._JacORBEventChannelFactoryStub stub;
			stub = new org.jacorb.notification._JacORBEventChannelFactoryStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.notification.JacORBEventChannelFactory unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.notification.JacORBEventChannelFactory)
		{
			return (org.jacorb.notification.JacORBEventChannelFactory)obj;
		}
		else
		{
			org.jacorb.notification._JacORBEventChannelFactoryStub stub;
			stub = new org.jacorb.notification._JacORBEventChannelFactoryStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
