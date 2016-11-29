package org.omg.CosNotification;


/**
 * Generated from IDL interface "QoSAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class QoSAdminHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(QoSAdminHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotification/QoSAdmin:1.0", "QoSAdmin");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotification.QoSAdmin s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotification.QoSAdmin extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotification/QoSAdmin:1.0";
	}
	public static QoSAdmin read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotification._QoSAdminStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotification.QoSAdmin s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotification.QoSAdmin narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotification.QoSAdmin)
		{
			return (org.omg.CosNotification.QoSAdmin)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotification/QoSAdmin:1.0"))
		{
			org.omg.CosNotification._QoSAdminStub stub;
			stub = new org.omg.CosNotification._QoSAdminStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotification.QoSAdmin unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotification.QoSAdmin)
		{
			return (org.omg.CosNotification.QoSAdmin)obj;
		}
		else
		{
			org.omg.CosNotification._QoSAdminStub stub;
			stub = new org.omg.CosNotification._QoSAdminStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
