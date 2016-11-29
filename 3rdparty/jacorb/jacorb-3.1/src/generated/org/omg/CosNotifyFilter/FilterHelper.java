package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "Filter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class FilterHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(FilterHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyFilter/Filter:1.0", "Filter");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyFilter.Filter s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyFilter.Filter extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyFilter/Filter:1.0";
	}
	public static Filter read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyFilter._FilterStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyFilter.Filter s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyFilter.Filter narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyFilter.Filter)
		{
			return (org.omg.CosNotifyFilter.Filter)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyFilter/Filter:1.0"))
		{
			org.omg.CosNotifyFilter._FilterStub stub;
			stub = new org.omg.CosNotifyFilter._FilterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyFilter.Filter unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyFilter.Filter)
		{
			return (org.omg.CosNotifyFilter.Filter)obj;
		}
		else
		{
			org.omg.CosNotifyFilter._FilterStub stub;
			stub = new org.omg.CosNotifyFilter._FilterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
