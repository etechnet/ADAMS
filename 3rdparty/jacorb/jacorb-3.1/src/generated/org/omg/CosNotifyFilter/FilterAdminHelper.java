package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "FilterAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class FilterAdminHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(FilterAdminHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0", "FilterAdmin");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyFilter.FilterAdmin s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyFilter.FilterAdmin extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0";
	}
	public static FilterAdmin read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyFilter._FilterAdminStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyFilter.FilterAdmin s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyFilter.FilterAdmin narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyFilter.FilterAdmin)
		{
			return (org.omg.CosNotifyFilter.FilterAdmin)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0"))
		{
			org.omg.CosNotifyFilter._FilterAdminStub stub;
			stub = new org.omg.CosNotifyFilter._FilterAdminStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyFilter.FilterAdmin unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyFilter.FilterAdmin)
		{
			return (org.omg.CosNotifyFilter.FilterAdmin)obj;
		}
		else
		{
			org.omg.CosNotifyFilter._FilterAdminStub stub;
			stub = new org.omg.CosNotifyFilter._FilterAdminStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
