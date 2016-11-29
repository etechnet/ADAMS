package org.omg.CosTransactions;


/**
 * Generated from IDL interface "Synchronization".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SynchronizationHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SynchronizationHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Synchronization:1.0", "Synchronization");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTransactions.Synchronization s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTransactions.Synchronization extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTransactions/Synchronization:1.0";
	}
	public static Synchronization read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTransactions._SynchronizationStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTransactions.Synchronization s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTransactions.Synchronization narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTransactions.Synchronization)
		{
			return (org.omg.CosTransactions.Synchronization)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTransactions/Synchronization:1.0"))
		{
			org.omg.CosTransactions._SynchronizationStub stub;
			stub = new org.omg.CosTransactions._SynchronizationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTransactions.Synchronization unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTransactions.Synchronization)
		{
			return (org.omg.CosTransactions.Synchronization)obj;
		}
		else
		{
			org.omg.CosTransactions._SynchronizationStub stub;
			stub = new org.omg.CosTransactions._SynchronizationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
