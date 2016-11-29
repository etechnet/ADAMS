package org.omg.CONV_FRAME;


/**
 * Generated from IDL struct "CodeSetComponent".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CodeSetComponentHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CodeSetComponentHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CONV_FRAME.CodeSetComponentHelper.id(),"CodeSetComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("native_code_set", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CONV_FRAME.CodeSetIdHelper.id(), "CodeSetId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("conversion_code_sets", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CONV_FRAME.CodeSetIdHelper.type()), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CONV_FRAME.CodeSetComponent s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CONV_FRAME.CodeSetComponent extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CONV_FRAME/CodeSetComponent:1.0";
	}
	public static org.omg.CONV_FRAME.CodeSetComponent read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CONV_FRAME.CodeSetComponent result = new org.omg.CONV_FRAME.CodeSetComponent();
		result.native_code_set=in.read_ulong();
		int _lresult_conversion_code_sets85 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_conversion_code_sets85 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_conversion_code_sets85);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.conversion_code_sets = new int[_lresult_conversion_code_sets85];
		in.read_ulong_array(result.conversion_code_sets,0,_lresult_conversion_code_sets85);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CONV_FRAME.CodeSetComponent s)
	{
		out.write_ulong(s.native_code_set);
		
		out.write_long(s.conversion_code_sets.length);
		out.write_ulong_array(s.conversion_code_sets,0,s.conversion_code_sets.length);
	}
}
