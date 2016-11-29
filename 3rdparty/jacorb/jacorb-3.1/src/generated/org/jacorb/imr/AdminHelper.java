package org.jacorb.imr;


/**
 * Generated from IDL interface "Admin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class AdminHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AdminHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/imr/Admin:1.0", "Admin");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.Admin s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.imr.Admin extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/imr/Admin:1.0";
	}
	public static Admin read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jacorb.imr._AdminStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.imr.Admin s)
	{
		_out.write_Object(s);
	}
	public static org.jacorb.imr.Admin narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.Admin)
		{
			return (org.jacorb.imr.Admin)obj;
		}
		else if (obj._is_a("IDL:org/jacorb/imr/Admin:1.0"))
		{
			org.jacorb.imr._AdminStub stub;
			stub = new org.jacorb.imr._AdminStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.imr.Admin unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.Admin)
		{
			return (org.jacorb.imr.Admin)obj;
		}
		else
		{
			org.jacorb.imr._AdminStub stub;
			stub = new org.jacorb.imr._AdminStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
