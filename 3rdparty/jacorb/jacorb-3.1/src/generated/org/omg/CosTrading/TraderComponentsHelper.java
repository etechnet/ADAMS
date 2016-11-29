package org.omg.CosTrading;


/**
 * Generated from IDL interface "TraderComponents".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TraderComponentsHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TraderComponentsHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTrading/TraderComponents:1.0", "TraderComponents");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.TraderComponents s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTrading.TraderComponents extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTrading/TraderComponents:1.0";
	}
	public static TraderComponents read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTrading._TraderComponentsStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTrading.TraderComponents s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTrading.TraderComponents narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.TraderComponents)
		{
			return (org.omg.CosTrading.TraderComponents)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTrading/TraderComponents:1.0"))
		{
			org.omg.CosTrading._TraderComponentsStub stub;
			stub = new org.omg.CosTrading._TraderComponentsStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTrading.TraderComponents unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.TraderComponents)
		{
			return (org.omg.CosTrading.TraderComponents)obj;
		}
		else
		{
			org.omg.CosTrading._TraderComponentsStub stub;
			stub = new org.omg.CosTrading._TraderComponentsStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
