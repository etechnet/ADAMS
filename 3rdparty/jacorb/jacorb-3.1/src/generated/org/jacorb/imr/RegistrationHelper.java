package org.jacorb.imr;


/**
 * Generated from IDL interface "Registration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class RegistrationHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RegistrationHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/imr/Registration:1.0", "Registration");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.Registration s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.imr.Registration extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/imr/Registration:1.0";
	}
	public static Registration read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jacorb.imr._RegistrationStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.imr.Registration s)
	{
		_out.write_Object(s);
	}
	public static org.jacorb.imr.Registration narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.Registration)
		{
			return (org.jacorb.imr.Registration)obj;
		}
		else if (obj._is_a("IDL:org/jacorb/imr/Registration:1.0"))
		{
			org.jacorb.imr._RegistrationStub stub;
			stub = new org.jacorb.imr._RegistrationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.imr.Registration unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.Registration)
		{
			return (org.jacorb.imr.Registration)obj;
		}
		else
		{
			org.jacorb.imr._RegistrationStub stub;
			stub = new org.jacorb.imr._RegistrationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
