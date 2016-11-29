package org.omg.ATLAS;


/**
 * Generated from IDL interface "AuthTokenDispenser".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthTokenDispenserHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AuthTokenDispenserHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/ATLAS/AuthTokenDispenser:1.0", "AuthTokenDispenser");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.ATLAS.AuthTokenDispenser s)
	{
			any.insert_Object(s);
	}
	public static org.omg.ATLAS.AuthTokenDispenser extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/ATLAS/AuthTokenDispenser:1.0";
	}
	public static AuthTokenDispenser read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.ATLAS._AuthTokenDispenserStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.ATLAS.AuthTokenDispenser s)
	{
		_out.write_Object(s);
	}
	public static org.omg.ATLAS.AuthTokenDispenser narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.ATLAS.AuthTokenDispenser)
		{
			return (org.omg.ATLAS.AuthTokenDispenser)obj;
		}
		else if (obj._is_a("IDL:omg.org/ATLAS/AuthTokenDispenser:1.0"))
		{
			org.omg.ATLAS._AuthTokenDispenserStub stub;
			stub = new org.omg.ATLAS._AuthTokenDispenserStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.ATLAS.AuthTokenDispenser unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.ATLAS.AuthTokenDispenser)
		{
			return (org.omg.ATLAS.AuthTokenDispenser)obj;
		}
		else
		{
			org.omg.ATLAS._AuthTokenDispenserStub stub;
			stub = new org.omg.ATLAS._AuthTokenDispenserStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
