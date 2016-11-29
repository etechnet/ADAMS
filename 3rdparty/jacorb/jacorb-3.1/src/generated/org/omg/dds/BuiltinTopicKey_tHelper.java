package org.omg.dds;

/**
 * Generated from IDL alias "BuiltinTopicKey_t".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class BuiltinTopicKey_tHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, int[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static int[] extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(BuiltinTopicKey_tHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.BuiltinTopicKey_tHelper.id(), "BuiltinTopicKey_t",org.omg.CORBA.ORB.init().create_array_tc(3,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/dds/BuiltinTopicKey_t:1.0";
	}
	public static int[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		int[] _result;
		_result = new int[3];
		_in.read_long_array(_result,0,3);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, int[] _s)
	{
				if (_s.length<3)
			throw new org.omg.CORBA.MARSHAL("Incorrect array size "+_s.length+", expecting 3");
		_out.write_long_array(_s,0,3);
	}
}
