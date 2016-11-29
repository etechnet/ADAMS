package org.omg.CosTime;


/**
 * Generated from IDL interface "TIO".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TIOHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TIOHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTime/TIO:1.0", "TIO");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTime.TIO s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTime.TIO extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTime/TIO:1.0";
	}
	public static TIO read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTime._TIOStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTime.TIO s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTime.TIO narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTime.TIO)
		{
			return (org.omg.CosTime.TIO)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTime/TIO:1.0"))
		{
			org.omg.CosTime._TIOStub stub;
			stub = new org.omg.CosTime._TIOStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTime.TIO unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTime.TIO)
		{
			return (org.omg.CosTime.TIO)obj;
		}
		else
		{
			org.omg.CosTime._TIOStub stub;
			stub = new org.omg.CosTime._TIOStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
