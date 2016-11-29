package org.omg.CosTransactions;


/**
 * Generated from IDL interface "SubtransactionAwareResource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SubtransactionAwareResourceHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SubtransactionAwareResourceHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/SubtransactionAwareResource:1.0", "SubtransactionAwareResource");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTransactions.SubtransactionAwareResource s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTransactions.SubtransactionAwareResource extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTransactions/SubtransactionAwareResource:1.0";
	}
	public static SubtransactionAwareResource read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTransactions._SubtransactionAwareResourceStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTransactions.SubtransactionAwareResource s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTransactions.SubtransactionAwareResource narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTransactions.SubtransactionAwareResource)
		{
			return (org.omg.CosTransactions.SubtransactionAwareResource)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTransactions/SubtransactionAwareResource:1.0"))
		{
			org.omg.CosTransactions._SubtransactionAwareResourceStub stub;
			stub = new org.omg.CosTransactions._SubtransactionAwareResourceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTransactions.SubtransactionAwareResource unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTransactions.SubtransactionAwareResource)
		{
			return (org.omg.CosTransactions.SubtransactionAwareResource)obj;
		}
		else
		{
			org.omg.CosTransactions._SubtransactionAwareResourceStub stub;
			stub = new org.omg.CosTransactions._SubtransactionAwareResourceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
