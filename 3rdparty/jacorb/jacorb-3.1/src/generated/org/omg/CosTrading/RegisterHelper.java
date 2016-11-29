package org.omg.CosTrading;


/**
 * Generated from IDL interface "Register".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class RegisterHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RegisterHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTrading/Register:1.0", "Register");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.Register s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTrading.Register extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTrading/Register:1.0";
	}
	public static Register read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTrading._RegisterStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTrading.Register s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTrading.Register narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.Register)
		{
			return (org.omg.CosTrading.Register)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTrading/Register:1.0"))
		{
			org.omg.CosTrading._RegisterStub stub;
			stub = new org.omg.CosTrading._RegisterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTrading.Register unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.Register)
		{
			return (org.omg.CosTrading.Register)obj;
		}
		else
		{
			org.omg.CosTrading._RegisterStub stub;
			stub = new org.omg.CosTrading._RegisterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
