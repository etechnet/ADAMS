package org.omg.BridgeTransactionMgmt;


/**
 * Generated from IDL interface "TransactionManagement".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransactionManagementHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TransactionManagementHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/BridgeTransactionMgmt/TransactionManagement:1.0", "TransactionManagement");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.BridgeTransactionMgmt.TransactionManagement s)
	{
			any.insert_Object(s);
	}
	public static org.omg.BridgeTransactionMgmt.TransactionManagement extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/BridgeTransactionMgmt/TransactionManagement:1.0";
	}
	public static TransactionManagement read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.BridgeTransactionMgmt._TransactionManagementStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.BridgeTransactionMgmt.TransactionManagement s)
	{
		_out.write_Object(s);
	}
	public static org.omg.BridgeTransactionMgmt.TransactionManagement narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.BridgeTransactionMgmt.TransactionManagement)
		{
			return (org.omg.BridgeTransactionMgmt.TransactionManagement)obj;
		}
		else if (obj._is_a("IDL:omg.org/BridgeTransactionMgmt/TransactionManagement:1.0"))
		{
			org.omg.BridgeTransactionMgmt._TransactionManagementStub stub;
			stub = new org.omg.BridgeTransactionMgmt._TransactionManagementStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.BridgeTransactionMgmt.TransactionManagement unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.BridgeTransactionMgmt.TransactionManagement)
		{
			return (org.omg.BridgeTransactionMgmt.TransactionManagement)obj;
		}
		else
		{
			org.omg.BridgeTransactionMgmt._TransactionManagementStub stub;
			stub = new org.omg.BridgeTransactionMgmt._TransactionManagementStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
