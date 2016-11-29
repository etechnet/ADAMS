package org.omg.CORBA;


/**
 * Generated from IDL interface "Policy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/Policy:1.0", "Policy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.Policy s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CORBA.Policy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CORBA/Policy:1.0";
	}
	public static Policy read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CORBA._PolicyStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CORBA.Policy s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CORBA.Policy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.Policy)
		{
			return (org.omg.CORBA.Policy)obj;
		}
		else if (obj._is_a("IDL:omg.org/CORBA/Policy:1.0"))
		{
			org.omg.CORBA._PolicyStub stub;
			stub = new org.omg.CORBA._PolicyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CORBA.Policy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.Policy)
		{
			return (org.omg.CORBA.Policy)obj;
		}
		else
		{
			org.omg.CORBA._PolicyStub stub;
			stub = new org.omg.CORBA._PolicyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
