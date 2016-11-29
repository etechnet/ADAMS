package org.omg.CosTransactions;


/**
 * Generated from IDL interface "Resource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ResourceHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ResourceHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Resource:1.0", "Resource");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTransactions.Resource s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTransactions.Resource extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTransactions/Resource:1.0";
	}
	public static Resource read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTransactions._ResourceStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTransactions.Resource s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTransactions.Resource narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTransactions.Resource)
		{
			return (org.omg.CosTransactions.Resource)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTransactions/Resource:1.0"))
		{
			org.omg.CosTransactions._ResourceStub stub;
			stub = new org.omg.CosTransactions._ResourceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTransactions.Resource unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTransactions.Resource)
		{
			return (org.omg.CosTransactions.Resource)obj;
		}
		else
		{
			org.omg.CosTransactions._ResourceStub stub;
			stub = new org.omg.CosTransactions._ResourceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
