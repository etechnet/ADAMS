package org.omg.CosConcurrencyControl;


/**
 * Generated from IDL interface "LockCoordinator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LockCoordinatorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(LockCoordinatorHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosConcurrencyControl/LockCoordinator:1.0", "LockCoordinator");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosConcurrencyControl.LockCoordinator s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosConcurrencyControl.LockCoordinator extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosConcurrencyControl/LockCoordinator:1.0";
	}
	public static LockCoordinator read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosConcurrencyControl._LockCoordinatorStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosConcurrencyControl.LockCoordinator s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosConcurrencyControl.LockCoordinator narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosConcurrencyControl.LockCoordinator)
		{
			return (org.omg.CosConcurrencyControl.LockCoordinator)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosConcurrencyControl/LockCoordinator:1.0"))
		{
			org.omg.CosConcurrencyControl._LockCoordinatorStub stub;
			stub = new org.omg.CosConcurrencyControl._LockCoordinatorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosConcurrencyControl.LockCoordinator unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosConcurrencyControl.LockCoordinator)
		{
			return (org.omg.CosConcurrencyControl.LockCoordinator)obj;
		}
		else
		{
			org.omg.CosConcurrencyControl._LockCoordinatorStub stub;
			stub = new org.omg.CosConcurrencyControl._LockCoordinatorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
