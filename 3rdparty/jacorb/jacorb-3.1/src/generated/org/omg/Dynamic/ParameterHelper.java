package org.omg.Dynamic;


/**
 * Generated from IDL struct "Parameter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ParameterHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ParameterHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Dynamic.ParameterHelper.id(),"Parameter",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("argument", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11)), null),new org.omg.CORBA.StructMember("mode", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CORBA.ParameterModeHelper.id(),"ParameterMode",new String[]{"PARAM_IN","PARAM_OUT","PARAM_INOUT"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Dynamic.Parameter s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Dynamic.Parameter extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Dynamic/Parameter:1.0";
	}
	public static org.omg.Dynamic.Parameter read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Dynamic.Parameter result = new org.omg.Dynamic.Parameter();
		result.argument=in.read_any();
		result.mode=org.omg.CORBA.ParameterModeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Dynamic.Parameter s)
	{
		out.write_any(s.argument);
		org.omg.CORBA.ParameterModeHelper.write(out,s.mode);
	}
}
