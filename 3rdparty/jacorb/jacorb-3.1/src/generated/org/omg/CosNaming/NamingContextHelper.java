package org.omg.CosNaming;


/**
 * Generated from IDL interface "NamingContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NamingContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NamingContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNaming/NamingContext:1.0", "NamingContext");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNaming.NamingContext s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNaming.NamingContext extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNaming/NamingContext:1.0";
	}
	public static NamingContext read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNaming._NamingContextStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNaming.NamingContext s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNaming.NamingContext narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNaming.NamingContext)
		{
			return (org.omg.CosNaming.NamingContext)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNaming/NamingContext:1.0"))
		{
			org.omg.CosNaming._NamingContextStub stub;
			stub = new org.omg.CosNaming._NamingContextStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNaming.NamingContext unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNaming.NamingContext)
		{
			return (org.omg.CosNaming.NamingContext)obj;
		}
		else
		{
			org.omg.CosNaming._NamingContextStub stub;
			stub = new org.omg.CosNaming._NamingContextStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
