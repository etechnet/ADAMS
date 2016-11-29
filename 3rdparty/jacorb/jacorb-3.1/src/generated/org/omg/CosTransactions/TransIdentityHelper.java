package org.omg.CosTransactions;


/**
 * Generated from IDL struct "TransIdentity".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TransIdentityHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TransIdentityHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.TransIdentityHelper.id(),"TransIdentity",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("coord", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Coordinator:1.0", "Coordinator"), null),new org.omg.CORBA.StructMember("term", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Terminator:1.0", "Terminator"), null),new org.omg.CORBA.StructMember("otid", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.otid_tHelper.id(),"otid_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("formatID", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("bqual_length", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("tid", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTransactions.TransIdentity s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTransactions.TransIdentity extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTransactions/TransIdentity:1.0";
	}
	public static org.omg.CosTransactions.TransIdentity read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosTransactions.TransIdentity result = new org.omg.CosTransactions.TransIdentity();
		result.coord=org.omg.CosTransactions.CoordinatorHelper.read(in);
		result.term=org.omg.CosTransactions.TerminatorHelper.read(in);
		result.otid=org.omg.CosTransactions.otid_tHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTransactions.TransIdentity s)
	{
		org.omg.CosTransactions.CoordinatorHelper.write(out,s.coord);
		org.omg.CosTransactions.TerminatorHelper.write(out,s.term);
		org.omg.CosTransactions.otid_tHelper.write(out,s.otid);
	}
}
