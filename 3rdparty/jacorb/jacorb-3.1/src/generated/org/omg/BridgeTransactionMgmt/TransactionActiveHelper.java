package org.omg.BridgeTransactionMgmt;


/**
 * Generated from IDL exception "TransactionActive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransactionActiveHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TransactionActiveHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.BridgeTransactionMgmt.TransactionActiveHelper.id(),"TransactionActive",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.BridgeTransactionMgmt.TransactionActive s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.BridgeTransactionMgmt.TransactionActive extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/BridgeTransactionMgmt/TransactionActive:1.0";
	}
	public static org.omg.BridgeTransactionMgmt.TransactionActive read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final org.omg.BridgeTransactionMgmt.TransactionActive result = new org.omg.BridgeTransactionMgmt.TransactionActive(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.BridgeTransactionMgmt.TransactionActive s)
	{
		out.write_string(id());
	}
}
