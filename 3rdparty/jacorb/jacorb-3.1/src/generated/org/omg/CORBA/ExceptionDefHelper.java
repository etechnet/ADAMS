package org.omg.CORBA;


/**
 * Generated from IDL interface "ExceptionDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExceptionDefHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ExceptionDefHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/ExceptionDef:1.0", "ExceptionDef");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.ExceptionDef s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CORBA.ExceptionDef extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CORBA/ExceptionDef:1.0";
	}
	public static ExceptionDef read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CORBA._ExceptionDefStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CORBA.ExceptionDef s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CORBA.ExceptionDef narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.ExceptionDef)
		{
			return (org.omg.CORBA.ExceptionDef)obj;
		}
		else if (obj._is_a("IDL:omg.org/CORBA/ExceptionDef:1.0"))
		{
			org.omg.CORBA._ExceptionDefStub stub;
			stub = new org.omg.CORBA._ExceptionDefStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CORBA.ExceptionDef unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.ExceptionDef)
		{
			return (org.omg.CORBA.ExceptionDef)obj;
		}
		else
		{
			org.omg.CORBA._ExceptionDefStub stub;
			stub = new org.omg.CORBA._ExceptionDefStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
