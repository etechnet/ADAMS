package org.omg.CosTransactions;


/**
 * Generated from IDL struct "PropagationContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PropagationContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PropagationContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.PropagationContextHelper.id(),"PropagationContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("timeout", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("current", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.TransIdentityHelper.id(),"TransIdentity",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("coord", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Coordinator:1.0", "Coordinator"), null),new org.omg.CORBA.StructMember("term", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Terminator:1.0", "Terminator"), null),new org.omg.CORBA.StructMember("otid", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.otid_tHelper.id(),"otid_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("formatID", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("bqual_length", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("tid", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null)}), null),new org.omg.CORBA.StructMember("parents", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.TransIdentityHelper.id(),"TransIdentity",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("coord", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Coordinator:1.0", "Coordinator"), null),new org.omg.CORBA.StructMember("term", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTransactions/Terminator:1.0", "Terminator"), null),new org.omg.CORBA.StructMember("otid", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTransactions.otid_tHelper.id(),"otid_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("formatID", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("bqual_length", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("tid", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null)})), null),new org.omg.CORBA.StructMember("implementation_specific_data", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTransactions.PropagationContext s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTransactions.PropagationContext extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTransactions/PropagationContext:1.0";
	}
	public static org.omg.CosTransactions.PropagationContext read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosTransactions.PropagationContext result = new org.omg.CosTransactions.PropagationContext();
		result.timeout=in.read_ulong();
		result.current=org.omg.CosTransactions.TransIdentityHelper.read(in);
		int _lresult_parents99 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_parents99 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_parents99);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.parents = new org.omg.CosTransactions.TransIdentity[_lresult_parents99];
		for (int i=0;i<result.parents.length;i++)
		{
			result.parents[i]=org.omg.CosTransactions.TransIdentityHelper.read(in);
		}

		result.implementation_specific_data=in.read_any();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTransactions.PropagationContext s)
	{
		out.write_ulong(s.timeout);
		org.omg.CosTransactions.TransIdentityHelper.write(out,s.current);
		
		out.write_long(s.parents.length);
		for (int i=0; i<s.parents.length;i++)
		{
			org.omg.CosTransactions.TransIdentityHelper.write(out,s.parents[i]);
		}

		out.write_any(s.implementation_specific_data);
	}
}
