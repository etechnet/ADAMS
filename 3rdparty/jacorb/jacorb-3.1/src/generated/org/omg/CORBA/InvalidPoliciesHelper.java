package org.omg.CORBA;


/**
 * Generated from IDL exception "InvalidPolicies".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InvalidPoliciesHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidPoliciesHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CORBA.InvalidPoliciesHelper.id(),"InvalidPolicies",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("indicies", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.InvalidPolicies s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.InvalidPolicies extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CORBA/InvalidPolicies:1.0";
	}
	public static org.omg.CORBA.InvalidPolicies read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		short[] x0;
		int _lx02 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lx02 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lx02);
				}
		}
		catch (java.io.IOException e)
		{
		}
		x0 = new short[_lx02];
		in.read_ushort_array(x0,0,_lx02);
		final org.omg.CORBA.InvalidPolicies result = new org.omg.CORBA.InvalidPolicies(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CORBA.InvalidPolicies s)
	{
		out.write_string(id());
		
		out.write_long(s.indicies.length);
		out.write_ushort_array(s.indicies,0,s.indicies.length);
	}
}
