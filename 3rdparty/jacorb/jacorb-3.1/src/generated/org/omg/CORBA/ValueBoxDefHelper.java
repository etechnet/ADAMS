package org.omg.CORBA;


/**
 * Generated from IDL interface "ValueBoxDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ValueBoxDefHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ValueBoxDefHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/ValueBoxDef:1.0", "ValueBoxDef");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.ValueBoxDef s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CORBA.ValueBoxDef extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CORBA/ValueBoxDef:1.0";
	}
	public static ValueBoxDef read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CORBA._ValueBoxDefStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CORBA.ValueBoxDef s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CORBA.ValueBoxDef narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.ValueBoxDef)
		{
			return (org.omg.CORBA.ValueBoxDef)obj;
		}
		else if (obj._is_a("IDL:omg.org/CORBA/ValueBoxDef:1.0"))
		{
			org.omg.CORBA._ValueBoxDefStub stub;
			stub = new org.omg.CORBA._ValueBoxDefStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CORBA.ValueBoxDef unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.ValueBoxDef)
		{
			return (org.omg.CORBA.ValueBoxDef)obj;
		}
		else
		{
			org.omg.CORBA._ValueBoxDefStub stub;
			stub = new org.omg.CORBA._ValueBoxDefStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
