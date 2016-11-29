package org.omg.BridgeTransactionMgmt;


/**
 * Generated from IDL exception "TransactionAlreadyActive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransactionAlreadyActiveHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TransactionAlreadyActiveHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.BridgeTransactionMgmt.TransactionAlreadyActiveHelper.id(),"TransactionAlreadyActive",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.BridgeTransactionMgmt.TransactionAlreadyActive s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.BridgeTransactionMgmt.TransactionAlreadyActive extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/BridgeTransactionMgmt/TransactionAlreadyActive:1.0";
	}
	public static org.omg.BridgeTransactionMgmt.TransactionAlreadyActive read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final org.omg.BridgeTransactionMgmt.TransactionAlreadyActive result = new org.omg.BridgeTransactionMgmt.TransactionAlreadyActive(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.BridgeTransactionMgmt.TransactionAlreadyActive s)
	{
		out.write_string(id());
	}
}
